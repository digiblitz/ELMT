/*
 * AnnConRegInsertAction.java
 *
 * Created on November 2, 2006, 6:33 PM
 */
package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.HLCAnnualUserVO;
import com.hlcmeeting.util.Debug;
import com.mee.actionform.AnnualRegActionForm;
import com.hlcmee.ann.HLCAnnualMeetingStore;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.*;

/**
 *
 * @author punitha
 * @version
 */
public class AnnConRegInsertAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HLCAnnualUserVO annualUser = new HLCAnnualUserVO();
        HLCKaveryMembershipTypeSessionRemote memRemote = initializeKaveryEJB(request);
        HLCVaigaiSessionRemote vaiRemote = initializeVaigaiEJB(request);
        try {
            String annProcess = request.getParameter("annProcess");
            AnnualRegActionForm annForm = (AnnualRegActionForm) form;

            HttpSession session = request.getSession(true);
            if (annProcess.equals("insertAnnual")) {
                String butName = request.getParameter("annButName");

                Debug.print("Insert Annual() is calling AnnconregInsert Servlet.............");
                /*
                 * HLC Member start
                 */
                String firstName = "";
                String lastName = "";
                String gender = "";
                String birthday = "";
                String birthmonth = "";
                String birthyear = "";
                String address = "";
                String city = "";
                String country = "";
                String state = "";
                String zip = "";
                String fax = "";
                String phone = "";
                String email = "";
                Date dob = null;

                String vals = annForm.getNewRegTypeId();
                String[] split_ids = vals.split("#");
                if (split_ids.length != 0) {
                    Debug.print("split_ids[0]   " + split_ids[0]);
                    Debug.print("split_ids[1]   " + split_ids[1]);
                    if (split_ids[1] != null && split_ids[1].trim().length() > 0) {
                        Debug.print("Seted the values in sesssion " + split_ids[1]);
                        session.setAttribute("specTransId", split_ids[1]);
                    }
                }


                String finalTypId = request.getParameter("finalTypId");
                if (finalTypId != null && finalTypId.trim().length() != 0) {
                    session.setAttribute("finalTypId", finalTypId);
                }

                String activityId = request.getParameter("activityId");
                if (activityId != null && activityId.trim().length() != 0) {
                    session.setAttribute("activityId", activityId);
                }

                // String memName = request.getParameter("memberName");
                //Debug.print("memName" + memName);
                       /*  String userId = request.getParameter("userId");
                String userdob = request.getParameter("dob");
                String userage = request.getParameter("age");
                Debug.print("userId" + userId);
                Debug.print("userdob" + userdob);
                Debug.print("userage" + userage);*/
                boolean ponyStatus = false;
                String badge = annForm.getNewBadge();
                String pony = annForm.getPclub();
                String ponyclubId = "";
                String ponyclubName = "";
                String accom = annForm.getAccom();
                String accomodation = "";
                String memName = annForm.getMemberName();
                String hlcmember = annForm.getUserName();
                String hlcUserId = annForm.getUserId();
                String memDob = annForm.getDob();
                String age = annForm.getAge();
                String memId = annForm.getMembershipTypeId();
                String regPriceId = annForm.getRegPriceId();
                String areaiv = annForm.getAreaIv();
                String lanuch = annForm.getLuncheon();
                String fameDinner = annForm.getFameDinner();
                String meetingDates = getAsString(annForm.getMeetingDates());
                //String firstname="";
                // String lastname="";
                String webusername = "";
                String weblastname = "";
                double regamount = annForm.getRegAmount();
                double otheramount = annForm.getActivityAmount();
                double amt = 0.00;
                amt = regamount + otheramount;

                // Debug.print(memId);

                if (memName != null && memName.equals("Member")) {
                    Debug.print("Member Name:" + memName);
                    firstName = annForm.getFirstName();
                    lastName = annForm.getLastName();

                    Debug.print("firstName" + firstName);
                    Debug.print("lastName" + lastName);

                }
                if (memName != null && memName.equals("Non Member")) {
                    Debug.print("Member Name:" + memName);
                    firstName = request.getParameter("firstNameId1");
                    lastName = request.getParameter("lastNameId1");
                    Debug.print("firstName" + firstName);
                    Debug.print("lastName" + lastName);
                    Debug.print("pony:" + pony);


                }

                /*
                 * New User Start
                 */
                Debug.print("AnnualRegActionForm1 =" + annForm);
                try {
                    if (memName != null && memName.equals("New User")) {
                        //memName ="New User";
                        firstName = annForm.getNewFirstName();
                        lastName = annForm.getNewLastName();
                        gender = annForm.getSex();
                        birthday = annForm.getNewBirthday();
                        birthmonth = annForm.getNewBirthmonth();
                        birthyear = annForm.getNewBirthyear();
                        address = annForm.getNewAddress();
                        Debug.print("address" + address);
                        city = annForm.getNewCity();
                        country = annForm.getNewCountry();
                        state = annForm.getNewState();
                        zip = annForm.getNewZip();
                        fax = annForm.getNewFax();
                        phone = annForm.getNewPhone();
                        email = annForm.getNewEmail();



                        String dbirth = birthmonth + "/" + birthday + "/" + birthyear;
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                        if (dbirth != null) {
                            dob = (Date) sdf.parse(dbirth);
                        } else {
                            dob = null;
                        }

                    }
                } catch (Exception e) {
                    Debug.print("AnnualRegActionForm1 New User:" + e.getMessage());

                }
                // Debug.print("AnnualRegActionForm2 ="+ annForm);    
                Debug.print("accomStatus:" + memName);
                boolean accomStatus = false;
                Debug.print("Non Member pony" + annForm.getPclub());
                Debug.print("Non Member ponyclubId" + annForm.getNewClub());
                Debug.print("Non Member ponyclubName" + annForm.getNewClubName());
                if (pony.equals("yes")) {
                    ponyclubId = annForm.getNewClub();
                    ponyclubName = annForm.getNewClubName();
                    Debug.print("Non Member ponyclubId" + ponyclubId);
                    Debug.print("Non Member ponyclubName" + ponyclubName);
                    ponyStatus = true;
                } else {
                    ponyclubId = "";
                    ponyclubName = "";
                    ponyStatus = false;
                }
                if (accom.equals("yes1")) {
                    accomStatus = true;
                    accomodation = annForm.getAccomodation();
                } else {
                    accomodation = "";
                    accomStatus = false;
                }
                //Debug.print("AnnualRegActionForm3 ="+ annForm);

                annualUser.setMembershipTypeId(memId);
                annualUser.setAreaIV(areaiv);
                annualUser.setFameDinner(fameDinner);
                annualUser.setRegisterId(regPriceId);
                annualUser.setYEndLauncheon(lanuch);
                annualUser.setUserId(hlcUserId);
                Debug.print("       UserId" + hlcUserId);
                Debug.print("       UserId" + annualUser.getUserId());
                annualUser.setFirstName(firstName);
                annualUser.setLastName(lastName);

                annualUser.setGender(gender);
                annualUser.setDob(dob);
                annualUser.setCity(city);
                annualUser.setCountry(country);
                annualUser.setAddress(address);
                annualUser.setZip(zip);
                Debug.print("" + annualUser.getAddress());
                annualUser.setState(state);
                annualUser.setEmailId(email);
                annualUser.setPhoneNo(phone);
                annualUser.setFaxNo(fax);
                annualUser.setBadgeInfo(badge);
                annualUser.setDaysApplied(meetingDates);
                annualUser.setRegAmount(regamount);
                annualUser.setOtherActAmount(otheramount);
                annualUser.setPoniClubStatus(ponyStatus);
                annualUser.setPoniClubMemberId(ponyclubId);
                annualUser.setClubName(ponyclubName);
                annualUser.setAccomFaciStaus(accomStatus);
                annualUser.setAccomodation(accomodation);
                annualUser.setAmount(amt);
                annualUser.setMemberTypeName(memName);
                Debug.print("accomStatus:" + memName);

                String priceIdLen = request.getParameter("priceIdLen");
                Debug.print("priceIdLen:" + priceIdLen);
                //String priceList[] = {};
                ArrayList priceList = new ArrayList();
                Debug.print("       UserId" + annualUser.getUserId());
                try {
                    if (priceIdLen != null) {
                        int arrayLength = Integer.parseInt(priceIdLen);
                        String tempArray[] = new String[arrayLength];
                        for (int pId = 0; pId < arrayLength; pId++) {
                            String cnt = String.valueOf(pId + 1);
                            // Debug.print("priceIdLen:" + priceIdLen);
                            String chkBx = "actCheckBox" + cnt;
                            String param = "newPId" + cnt;
                            String paramPrice = "AmtBx" + cnt;
                            String actVal = "actVal" + cnt;
                            Debug.print("param:" + actVal);
                            String tempPriceId = request.getParameter(param);
                            String regTempPrice = request.getParameter(paramPrice);
                            String activityName = request.getParameter(actVal);
                            String tempchkBx = request.getParameter(chkBx);
                            Debug.print("tempPriceId:" + tempPriceId);
                            Debug.print("regTempPrice:" + regTempPrice);
                            Debug.print("activityName:" + activityName);
                            if (tempchkBx != null && tempchkBx.trim().length() != 0) {
                                String priceDet[] = {tempPriceId, regTempPrice, activityName};
                                priceList.add(priceDet);
                            }
                        }
                        Debug.print("Sucessfully comes from loop:");
                    }
                } catch (Exception e) {
                    Debug.print("while getting price id:" + e.getMessage());
                }

                annualUser.setPriceList(priceList);
                Debug.print("Sucessfully setted Pricelist.");

                Debug.print("       UserId" + annualUser.getUserId());

                if (butName.equals("Register")) {
                    HLCAnnualMeetingStore regList = (HLCAnnualMeetingStore) session.getAttribute("regList");
                    if (regList != null) {
                        Debug.print("Session AnnualMeetingStore already exist AnnualMeetingStore");
                        Debug.print("       UserId" + annualUser.getUserId());
                        if (annualUser.getUserId() != null && annualUser.getUserId().trim().length() != 0) {
                            regList.addParticipant(annualUser.getUserId(), annualUser);
                            session.setAttribute("regList", regList);
                            Debug.print("Session AnnualMeetingStore UserId is exist" + annualUser.getUserId());
                        } else {
                            String userAlterId = (String) session.getAttribute("userAlterId");
                            if (userAlterId == null) {
                                Debug.print("Session AnnualMeetingStore Newly created userAlterId Append & Register");
                                userAlterId = "10000";
                            //session.setAttribute("userAlterId",userAlterId);
                            } else {
                                Debug.print("Session AnnualMeetingStore Newly created userAlterId Append & Register");
                                userAlterId = String.valueOf(Integer.parseInt(userAlterId) + 1);
                            }
                            Debug.print("Session AnnualMeetingStore UserId is not exist" + userAlterId);
                            annualUser.setUserId(userAlterId);
                            session.setAttribute("userAlterId", userAlterId);
                            regList.addParticipant(userAlterId, annualUser);
                            session.setAttribute("regList", regList);
                        }
                    } else {
                        Debug.print("Session AnnualMeetingStore newly creating Append & Register:" + annualUser.getUserId());
                        HLCAnnualMeetingStore tempRegList = new HLCAnnualMeetingStore();
                        if (annualUser.getUserId() != null && annualUser.getUserId().trim().length() != 0) {
                            tempRegList.addParticipant(annualUser.getUserId(), annualUser);
                            session.setAttribute("regList", tempRegList);
                        } else {
                            Debug.print("Session AnnualMeetingStore newly creating Append & Register");
                            String userAlterId = (String) session.getAttribute("userAlterId");
                            if (userAlterId == null) {
                                Debug.print("Session AnnualMeetingStore Newly created userAlterId Append & Register");
                                userAlterId = "10000";
                            } else {
                                Debug.print("Session AnnualMeetingStore Newly created userAlterId Append & Register");
                                userAlterId = String.valueOf(Integer.parseInt(userAlterId) + 1);
                            }
                            Debug.print("Session AnnualMeetingStore Newly created userAlterId Append & Register else userAlterId" + userAlterId);
                            annualUser.setUserId(userAlterId);
                            session.setAttribute("userAlterId", userAlterId);
                            tempRegList.addParticipant(userAlterId, annualUser);
                            session.setAttribute("regList", tempRegList);
                        }
                    }

                    return mapping.findForward("cnf");
                }
            } else if (annProcess.equals("payment")) {
                Vector memDet = memRemote.displayAnnualMemberDetails();
                String memberId = (String) session.getAttribute("memberId");
                String memberShipTypeID = "";
                if (memDet != null && memDet.size() != 0) {
                    Enumeration eDet = memDet.elements();
                    while (eDet.hasMoreElements()) {
                        String[] det = (String[]) eDet.nextElement();
                        String memId = det[0];
                        String memName = det[1];
                        if (memName.equals("HLC Member")) {
                            if (memberId != null) {
                                memberShipTypeID = memId;
                            }
                        }
                        if (memName.equals("Non Member")) {
                            if (memberId == null) {
                                memberShipTypeID = memId;
                                Debug.print("memName:" + memName);

                            }
                        }
                    }
                }
                Debug.print("memberShipTypeID:" + memberShipTypeID);
                request.setAttribute("memberShipTypeID", memberShipTypeID);
                Vector otherAct = vaiRemote.displayActivityTypeActivity();
                request.setAttribute("OTHER_ACTIVITY", otherAct);
                return mapping.findForward("finalize");
            } else if (annProcess.equals("remove")) {
                HLCAnnualMeetingStore regList = (HLCAnnualMeetingStore) session.getAttribute("regList");
                String deluserId = request.getParameter("deluserId");
                regList.removeParticipant(deluserId);
                Debug.print("regList.getSize()" + regList.getSize());
                if (regList == null || regList.getSize() == 0) {
                    session.removeAttribute("regList");
                    return mapping.findForward("redirToReg");
                }

                return mapping.findForward("cnf");
            }
        } catch (Exception e) {
            Debug.print("Exception occurs while inserting the elements" + e.getMessage());
        }
        return null;

    }

    private String getAsString(String[] arr) {
        if (arr == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int len = (arr.length - 1);
        for (int i = 0; i < len; i++) {
            buffer.append(arr[i] + ",");
        }
        if (arr.length > 0) {
            buffer.append(arr[arr.length - 1]);
        }
        //buffer.append();
        return buffer.toString();
    }

    public static Context getInitialContext()
            throws javax.naming.NamingException {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        p.setProperty("java.naming.provider.url", "");
        return new javax.naming.InitialContext(p);
    }

    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception {
        MessageResources mr = getResources(request);
        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.icp");

        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);

        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();

        return remote;
    }

    private HLCKaveryMembershipTypeSessionRemote initializeKaveryEJB(HttpServletRequest request) throws Exception {
        MessageResources mr = getResources(request);
        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.kaveryMembership");

        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);

        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCKaveryMembershipTypeSessionRemoteHome home = (HLCKaveryMembershipTypeSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref, HLCKaveryMembershipTypeSessionRemoteHome.class);
        HLCKaveryMembershipTypeSessionRemote remote = home.create();

        return remote;
    }
}
