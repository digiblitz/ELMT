/*
 * MemberSignupDetailsAction.java
 *
 * Created on November 7, 2006, 10:43 AM
 */
package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.HLCUsrSignUpVO;
import com.hlchorse.form.util.HLCUserRegVO;
import com.mrm.actionform.UpdateUserRegActionForm;
import com.hlcmsg.util.Debug;
import com.hlcrole.management.HLCBrahmaputraSessionRemote;
import com.hlcrole.management.HLCBrahmaputraSessionRemoteHome;
import com.mysql.dao.MySQLDAO;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hlcform.util.HLCUserMaster;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.hlcform.stateless.*;
import com.hlchorse.form.display.*;
import com.util.XMLParser;
import java.io.File;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author karthikeyan
 * @version
 */
public class MemberUserSignupAction extends Action {

    String fwd = "";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            HttpServlet ser = getServlet();
            ServletContext context = ser.getServletContext();
            System.out.println("after ser.getServletContext() .....");

            MessageResources mr = getResources(request);

            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndiname = mr.getMessage("jndi.usrreg");

            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            HttpSession session=request.getSession(true);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);

            HLCkaverystatelessRemoteHome home =
                    (HLCkaverystatelessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();
            String jndinameRole = mr.getMessage("jndi.rolemanagement");
            
            Object objrefRole = jndiContext.lookup(jndinameRole);
            Debug.print("ActionRoleMangement.jndiname:" + jndinameRole);

            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome) PortableRemoteObject.narrow(objrefRole, HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();
            String process = request.getParameter("process");
            String source = request.getParameter("source");
            String eventTypeId = request.getParameter("eventTypeId");
            String compYear = request.getParameter("compYear");
            request.setAttribute("compYear", compYear);
            Debug.print("process :" + process);
            Debug.print("source :" + source);

            if (process.equals("view")) {

                Debug.print("inside view block : ");
                String license_file_path = mr.getMessage("license.file.path");
                String license_file_name = mr.getMessage("license.file.name");
                String fileEncryptedContent = XMLParser.readXMLCreated(license_file_path + "\\" + license_file_name);
                String original = XMLParser.decryptFileContent(fileEncryptedContent); //validating  Registered Users Count
                boolean output = XMLParser.readXMLDecryptedContent(original);// validating license
                //int usersFromXML = XMLParser.readFromXMLUsers(original);
                //int usersFromDB = remote.getTotalUsersRegister();
                if (output) {
                    //if (usersFromDB <= usersFromXML) {
                        request.setAttribute("loginName", "newUserFound");
                        request.setAttribute("source", source);
                        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            //String str="C:/jboss-4.2.2.GA/server/default/deploy/";
            String str=mr.getMessage("applicationconfig.file.path");
            Document doc = docBuilder.parse (new File(str+"association-config.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


            NodeList listOfPersons = doc.getElementsByTagName("first");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of element : " + totalPersons);

            for(int s=0; s<listOfPersons.getLength() ; s++){


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("title");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("Title Name : " + ((Node)textFNList.item(0)).getNodeValue().trim());
                     String titleName=((Node)textFNList.item(0)).getNodeValue().trim();
                     session.setAttribute("title",titleName);
                    //-------
                   // NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
                   // Element lastNameElement = (Element)lastNameList.item(0);

                   // NodeList textLNList = lastNameElement.getChildNodes();
                   // System.out.println("Last Name : " +
                         //  ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                   // NodeList ageList = firstPersonElement.getElementsByTagName("age");
                   // Element ageElement = (Element)ageList.item(0);

                 //   NodeList textAgeList = ageElement.getChildNodes();
                   // System.out.println("Age : " +
                        //   ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------


                }//end of if clause


            }//end of for loop with s var

                        fwd = "UserSignup";
                   /* } else {
                        String status = "users";
                        request.setAttribute("status", status);
                        fwd = "ReLogin";*/
                   // }
                } else {
                    String status = "expired";
                    request.setAttribute("status", status);
                    fwd ="ReLogin";
                }

            }

            if (process.equals("usrReg")) {

                Debug.print("inside usrReg block : ");
                fwd = "UserReg";
            }

            if (process.equals("usrEmpReg")) {
            	ArrayList counBaseLocList = new ArrayList();
            	//ArrayList baseLocList = new ArrayList();
            	//ArrayList lobList = new ArrayList();
            	ArrayList roleList = new ArrayList();
            	
            	counBaseLocList = remote.selectCountryList();
            	roleList = roleRemote.getAllRoles();
            	//lobList = remote.selectLOBList();
            	request.setAttribute("counBaseLocList",counBaseLocList);
            	//request.setAttribute("lobList",lobList);
            	request.setAttribute("roleList",roleList);
                Debug.print("inside EmpMapReg block : ");
                fwd = "EmpMapReg";
            }
            

            if (process.equals("sign")) {

                HLCUsrSignUpVO usrVo = new HLCUsrSignUpVO();
                String userStatus;

                userStatus = request.getParameter("UserStatus");
                Debug.print("Radio selected value :" + userStatus);

                //if(userStatus.equalsIgnoreCase("Member")){

                String memID = request.getParameter("membid");
                String firstName = request.getParameter("fname");
                String lastName = request.getParameter("lname");

                String lName = "";
                String fName = "";
                String parFName = "";
                String parLName = "";
                String iniFName = "";
                String iniLName = "";

                String hlcMembID = memID.trim();
                fName = firstName.trim();
                lName = lastName.trim();

                Debug.print("First Name:" + fName);
                Debug.print("Last Name:" + lName);
                Debug.print("parFName:" + parFName);
                Debug.print("parLName:" + parLName);
                Debug.print("iniFNamee:" + iniFName);
                Debug.print("iniLName:" + iniLName);
                Debug.print("hlcMembID:" + hlcMembID);

                if ((hlcMembID == "" && hlcMembID.trim().length() == 0 && fName != null && fName.trim().length() != 0 && lName != null && lName.trim().length() != 0) || (hlcMembID == "" && hlcMembID.trim().length() == 0 && fName == "" && fName.trim().length() == 0 && lName != null && lName.trim().length() != 0)) {
                    Debug.print("0");
                    ArrayList userDetails = new ArrayList();
                    Debug.print("Inside WebUser Block :");
                    userDetails = remote.getUserDetailsForUsrSign(fName, lName);

                    if (userDetails.size() == 0) {
                        request.setAttribute("source", source);
                        fwd = "searchFail";
                    } else {
                        request.setAttribute("source", source);
                        fwd = "UserDetial";
                    }
                    Debug.print("fwd :" + fwd);

                    Debug.print("userDetails.size() :" + userDetails.size());

                    request.setAttribute("membDetails", userDetails);
                } else {
                    request.setAttribute("source", source);
                    fwd = "searchFail";
                }

                /*if((hlcMembID!=null && hlcMembID.trim().length()!=0) || (hlcMembID!=null && hlcMembID.trim().length()!=0 && 
                fName!=null && fName.trim().length()!=0 && lName!=null && lName.trim().length()!=0)){ */
                if ((hlcMembID != null && hlcMembID.trim().length() != 0) &&
                        (fName == "" || fName.trim().length() == 0 || lName == "" || lName.trim().length() == 0)) {
                    Debug.print("Inside Existing Member block :");
                    Debug.print("1");
                    boolean membstatus = false;
                    Debug.print("HLC Member ID:" + hlcMembID);
                    if (hlcMembID != null) {

                        membstatus = remote.isMemberIdExist(hlcMembID);
                        Debug.print("membstatus" + membstatus);
                    }
                    if (membstatus == false) {
                        Debug.print("Inside if");
                        request.setAttribute("loginName", null);
                        request.setAttribute("source", source);
                        fwd = "UserSignup";
                    } else {
                        Debug.print("Inside else");
                        ArrayList membDetails = new ArrayList();
                        membDetails = remote.getMemberDetailsForUsrSign(hlcMembID);

                        Debug.print("membDetails.size() :" + membDetails.size());

                        if (membDetails.size() == 0) {
                            request.setAttribute("source", source);
                            fwd = "searchFail";
                        } else {
                            request.setAttribute("source", source);
                            fwd = "UserDetial";
                        }
                        request.setAttribute("membDetails", membDetails);
                    }
                }
                if ((hlcMembID != null && hlcMembID.trim().length() != 0 &&
                        fName != null && fName.trim().length() != 0 && lName != null && lName.trim().length() != 0) ||
                        (hlcMembID != null && hlcMembID.trim().length() != 0 && lName != null && lName.trim().length() != 0)) {
                    Debug.print("2");
                    String dbFirstName = "";
                    String dbLastName = "";
                    String parDBFName = "";
                    String parDBLName = "";
                    String iniDBFName = "";
                    String iniDBLName = "";
                    boolean firstFlag = false;
                    boolean lastFlag = false;
                    ArrayList membDetails = new ArrayList();
                    HLCUsrSignUpVO usVO = new HLCUsrSignUpVO();
                    membDetails = remote.getMemberDetailsForUsrSign(hlcMembID);
                    if (membDetails != null && membDetails.size() != 0) {
                        Iterator itr = membDetails.iterator();
                        while (itr.hasNext()) {
                            usVO = (HLCUsrSignUpVO) itr.next();
                            dbFirstName = usVO.getFname();
                            dbLastName = usVO.getLname();
                            //parDBFName=dbFirstName.substring(0,3);
                            //parDBLName=dbLastName.substring(0,3);
                            iniDBFName = dbFirstName.substring(0, 1);
                            iniDBLName = dbLastName.substring(0, 1);
                        }
                    }
                    Debug.print("DB First Name:" + dbFirstName);
                    Debug.print("DB Last Name:" + dbLastName);
                    Debug.print("DB parFName:" + parDBFName);
                    Debug.print("DB parLName:" + parDBLName);
                    Debug.print("DB iniFNamee:" + iniDBFName);
                    Debug.print("DB iniLName:" + iniDBLName);
                    if ((fName != null && fName.trim().length() != 0 && fName != "" && fName.trim().length() != 0) &&
                            (lName != null && lName.trim().length() != 0 && lName != "" && lName.trim().length() != 0)) {
                        Debug.print("3");
                        if (dbFirstName.equalsIgnoreCase(fName) && dbLastName.equalsIgnoreCase(lName)) {
                            Debug.print("4");
                            Debug.print("membDetails.size() :" + membDetails.size());
                            if (membDetails.size() == 0) {
                                Debug.print("41");
                                request.setAttribute("source", source);
                                //fwd="searchFail";
                                return mapping.findForward("searchFail");
                            } else {
                                Debug.print("42");
                                request.setAttribute("source", source);
                                request.setAttribute("membDetails", membDetails);
                                //fwd="UserDetial";
                                return mapping.findForward("UserDetial");
                            }

                        }/*else if(fName.length()>=3){
                        Debug.print("5");
                        parFName=fName.substring(0,3);
                        if(parDBFName.equalsIgnoreCase(parFName)){
                        firstFlag=true;    
                        }
                        }*/ else if (fName.length() >= 1) {
                            iniFName = fName.substring(0, 1);
                            if (iniDBFName.equalsIgnoreCase(iniFName)) {
                                Debug.print("6");
                                firstFlag = true;
                            }
                        }
                        if (lName.length() >= 1) {
                            iniLName = lName.substring(0, 1);
                            if (iniDBLName.equalsIgnoreCase(iniLName)) {
                                Debug.print("7");
                                lastFlag = true;
                            }
                        }
                        Debug.print("firstFlag :" + firstFlag);
                        Debug.print("lastFlag :" + lastFlag);
                        if (firstFlag == true && lastFlag == true) {
                            Debug.print("8");
                            request.setAttribute("source", source);
                            //fwd="UserDetial";
                            request.setAttribute("membDetails", membDetails);
                            return mapping.findForward("UserDetial");
                        } else {
                            Debug.print("9");
                            ArrayList userDetails = new ArrayList();
                            Debug.print("Inside WebUser Block :");
                            userDetails = remote.getUserDetailsForUsrSign(fName, lName);
                            if (userDetails.size() == 0) {
                                request.setAttribute("source", source);
                                fwd = "searchFail";
                            } else {
                                request.setAttribute("source", source);
                                fwd = "UserDetial";
                            }
                            Debug.print("fwd :" + fwd);
                            Debug.print("userDetails.size() :" + userDetails.size());
                            request.setAttribute("membDetails", userDetails);
                        }
                    } else if ((fName == null && fName.trim().length() == 0 || fName == "" && fName.trim().length() == 0) &&
                            (lName != null && lName.trim().length() != 0 && lName != "" && lName.trim().length() != 0)) {
                        Debug.print("10");
                        if (dbLastName.equalsIgnoreCase(lName)) {
                            Debug.print("11");
                            Debug.print("membDetails.size() :" + membDetails.size());
                            if (membDetails.size() == 0) {
                                request.setAttribute("source", source);
                                //fwd="searchFail";
                                return mapping.findForward("searchFail");
                            } else {
                                request.setAttribute("source", source);
                                //fwd="UserDetial";
                                request.setAttribute("membDetails", membDetails);
                                return mapping.findForward("UserDetial");
                            }

                        } else if (lName.length() >= 1) {
                            iniLName = lName.substring(0, 1);
                            if (iniDBLName.equalsIgnoreCase(iniLName)) {
                                Debug.print("12");
                                lastFlag = true;
                            }
                        }
                        Debug.print("lastFlag only lname given:" + lastFlag);
                        if (lastFlag == true) {
                            Debug.print("13");
                            request.setAttribute("source", source);
                            request.setAttribute("membDetails", membDetails);
                            //fwd="UserDetial";
                            return mapping.findForward("UserDetial");
                        } else {
                            Debug.print("14");
                            ArrayList userDetails = new ArrayList();
                            Debug.print("Inside WebUser Block :");
                            userDetails = remote.getUserDetailsForUsrSign(fName, lName);
                            if (userDetails.size() == 0) {
                                request.setAttribute("source", source);
                                fwd = "searchFail";
                            } else {
                                request.setAttribute("source", source);
                                fwd = "UserDetial";
                            }
                            Debug.print("fwd :" + fwd);
                            Debug.print("userDetails.size() :" + userDetails.size());
                            request.setAttribute("membDetails", userDetails);
                        }
                    }
                    Debug.print("after else if");
                }

            }
            if (process.equals("auth")) {

                Debug.print("request.getParameter(prof_rad) :" + request.getParameter("prof_rad"));

                if (request.getParameter("prof_rad").equalsIgnoreCase("yes")) {
                    String userId = "";
                    HLCUsrSignUpVO userDet = new HLCUsrSignUpVO();
                    if (request.getParameter("info_rad") != null) {
                        userId = request.getParameter("info_rad");
                        Debug.print("User ID:" + userId);
                        userDet = remote.getUsrDetForUsrSignOnUsrId(userId);
                        Debug.print("remote.getUsrDetForUsrSignOnUsrId(userId) :" + userDet.toString());
                    }
                    request.setAttribute("userDet", userDet);
                    request.setAttribute("source", source);
                    fwd = "UserAuth";
                } else {
                    request.setAttribute("source", source);
                    fwd = "usrNoMatch";
                }
            }

            if (process.equals("validate")) {

                String userId = request.getParameter("userId");
                Debug.print("request.getParameter(userId) :" + userId);

                HLCUsrSignUpVO userDet = new HLCUsrSignUpVO();

                userDet = remote.getUsrDetForUsrSignOnUsrId(userId);
                Debug.print("remote.getUsrDetForUsrSignOnUsrId(userId) :" + userDet.toString());

                String ph = request.getParameter("phone");
                String phone = ph.trim();
                Debug.print("request.getParameter(phone) :" + phone);
                Debug.print("request.getParameter(phone) :" + ph);

                String em = request.getParameter("email");
                String email = em.trim();
                Debug.print("request.getParameter(email) :" + email);
                Debug.print("request.getParameter(email) :" + em);

                String zp = request.getParameter("zip");
                String zip = zp.trim();
                Debug.print("request.getParameter(zip) :" + zip);
                Debug.print("request.getParameter(zip) :" + zp);

                /*String membid=request.getParameter("membid");
                Debug.print("request.getParameter(phone) :"+membid);*/

                int flag = 0;

                if (phone != null && phone.trim().length() != 0) {

                    StringTokenizer strTkns = new StringTokenizer(phone, "[](),.-{} ");
                    String finalUserPh = "";

                    while (strTkns.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalUserPh = finalUserPh + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("ph no 1 tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended User phNo :" + finalUserPh);

                    StringTokenizer strTkns1 = new StringTokenizer(userDet.getPhone(), "[](),.-{} ");
                    String finalDbPh = "";

                    while (strTkns1.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns1.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalDbPh = finalDbPh + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("ph no 1 tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended DB phNo :" + finalDbPh);

                    if (finalDbPh.equalsIgnoreCase(finalUserPh)) {
                        Debug.print("inside if(userDet.getPhone().equalsIgnoreCase(phone) block.....");
                        flag = flag + 1;
                    }
                }

                if (email != null) {
                    if (userDet.getEmail().equalsIgnoreCase(email)) {
                        Debug.print("inside if(userDet.getEmail().equalsIgnoreCase(email) block.....");
                        flag = flag + 1;
                    }
                }

                if (zip != null) {
                    if (userDet.getZip().equalsIgnoreCase(zip)) {
                        Debug.print("inside if(userDet.getZip().equalsIgnoreCase(zip) block.....");
                        flag = flag + 1;
                    }
                }

                /* if(membid!=null)
                {
                if(userDet.getMembid().equalsIgnoreCase(membid))
                {
                Debug.print("inside if(userDet.getMembid().equalsIgnoreCase(membid) block.....");
                flag=flag+1;
                }
                }*/

                Debug.print("flag count :" + flag);

                if (flag < 2) {
                    request.setAttribute("source", source);
                    fwd = "searchFail";
                } else {

                    String jndiname1 = mr.getMessage("jndi.kaverysful");
                    Object objref1 = jndiContext.lookup(jndiname1);
                    HLCKaverySessionStatefulRemoteHome home1 =
                            (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref1, HLCKaverySessionStatefulRemoteHome.class);

                    HLCKaverySessionStatefulRemote remote1 = null;
                    remote1 = home1.create();

                    HLCUserRegVO userregvo = new HLCUserRegVO();

                    Vector EditusrVect = new Vector();

                    Debug.print("userId :" + userId);

                    userregvo = remote1.selectUserRegistrationForm1(userId);

                    Debug.print("set on context userregvo.getPassword() :" + userregvo.getPassword());
                    context.setAttribute("pwd", userregvo.getPassword());

                    if (userregvo.getPassword() != null && userregvo.getPassword().trim().length() != 0) {
                        request.setAttribute("pwdStat", "available");
                    } else {
                        request.setAttribute("pwdStat", "empty");
                    }

                    request.setAttribute("EditusrVect", userregvo);
                    request.setAttribute("source", source);
                    fwd = "editUserDet";
                }
            }

            if (process.equals("edit")) {

                String jndiname2 = mr.getMessage("jndi.usrreg");
                Object objref2 = jndiContext.lookup(jndiname2);

                HLCkaverystatelessRemoteHome home2 = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(objref2, HLCkaverystatelessRemoteHome.class);
                HLCkaverystatelessRemote remote2 = home2.create();

                HLCUserMaster objUserMaster = new HLCUserMaster();
                HLCContactDetails objContact = new HLCContactDetails();

                /*
                 * Updating User Details by ejb/HLCMemberRegistrationJNDI JNDI
                 */

                UpdateUserRegActionForm usrbean = (UpdateUserRegActionForm) form;

                String bday = usrbean.getBirthyear() + "-" + usrbean.getBirthmonth() + "-" + usrbean.getBirthday();
                System.out.println(bday);

                String userId = request.getParameter("userId");


                objUserMaster.setPrefix(usrbean.getSelect());
                objUserMaster.setFirstName(usrbean.getFname());
                objUserMaster.setMiddleName(usrbean.getMname());
                objUserMaster.setLastName(usrbean.getLname());
                objUserMaster.setSufix(usrbean.getSname());
                objUserMaster.setDob(bday);
                objUserMaster.setGender(usrbean.getGender());
                objUserMaster.setCommunicationAddress(usrbean.getComAdd_sel());
                objUserMaster.setEmailId(request.getParameter("email"));
                objUserMaster.setUserId(userId);
                objUserMaster.setLoginName(request.getParameter("usrname"));
                objUserMaster.setSecretQuestion(request.getParameter("QSelect"));
                objUserMaster.setSecretAnswer(request.getParameter("ans"));

                request.setAttribute("usrname", request.getParameter("usrname"));

                if (request.getParameter("confPwd") != null && request.getParameter("confPwd").trim().length() != 0) {
                    request.setAttribute("cpwd", request.getParameter("confPwd"));
                } else {
                    String pwd = (String) context.getAttribute("pwd");

                    Debug.print("context.getAttribute(pwd) :" + pwd);
                    request.setAttribute("cpwd", pwd);
                }

                String finalPrimaryPh = "";

                if (request.getParameter("pphone_txt") != null) {
                    Debug.print("request.getParameter(pphone_txt) value:" + request.getParameter("pphone_txt"));

                    StringTokenizer strTkns = new StringTokenizer(request.getParameter("pphone_txt"), "[](),.-{}");

                    while (strTkns.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalPrimaryPh = finalPrimaryPh + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("ph no tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended primary phNo :" + finalPrimaryPh);
                }

                String finalPrimaryMob = "";

                if (request.getParameter("pmob_txt") != null) {
                    Debug.print("request.getParameter(pmob_txt) value:" + request.getParameter("pmob_txt"));

                    StringTokenizer strTkns = new StringTokenizer(request.getParameter("pmob_txt"), "[](),.-{}");

                    while (strTkns.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalPrimaryMob = finalPrimaryMob + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("ph no tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended finalPrimaryMob :" + finalPrimaryMob);
                }

                String finalPrimaryFax = "";

                if (request.getParameter("pfax_txt") != null) {
                    Debug.print("request.getParameter(pfax_txt) value:" + request.getParameter("pfax_txt"));

                    StringTokenizer strTkns = new StringTokenizer(request.getParameter("pfax_txt"), "[](),.-{}");

                    while (strTkns.hasMoreTokens()) {
                        try {
                            String phNo = (String) strTkns.nextToken();

                            if (phNo != null && phNo.trim().length() != 0) {
                                Debug.print("ph no Added from Stokenizer:" + phNo);
                                finalPrimaryFax = finalPrimaryFax + phNo;
                            }
                        } catch (Exception e) {
                            Debug.print("ph no tokanizing exception:" + e);
                        }
                    }

                    Debug.print("finally appended finalPrimaryFax :" + finalPrimaryFax);
                }

                objContact.setContactType(usrbean.getPriAdd_cbx());
                objContact.setAddress1(usrbean.getPadd_txt());
                objContact.setAddress2(usrbean.getPadd_txt2());
                objContact.setCity(usrbean.getPcity_txt());
                objContact.setState(usrbean.getPstate_sel());
                objContact.setCountry(usrbean.getPcountry_sel());
                objContact.setZip(usrbean.getPzip_txt());
                objContact.setPhoneNo(finalPrimaryPh);
                objContact.setMobileNo(finalPrimaryMob);
                objContact.setFaxNo(finalPrimaryFax);
                System.out.println("nonUseaEmail.status:=" + request.getParameter("nonUseaEmail"));
                System.out.println("nonUseaMail.status:=" + request.getParameter("nonUseaMail"));

                if (request.getParameter("nonUseaEmail") != null) {
                    objUserMaster.setNonUseaEmailStatus(true);
                    System.out.println("commit nonUseaEMail status--true");
                } else {
                    objUserMaster.setNonUseaEmailStatus(false);
                    System.out.println("commit nonUseaEMail status--false");
                }
                if (request.getParameter("nonUseaMail") != null) {
                    objUserMaster.setNonUseaMailingStatus(true);
                    System.out.println("commit nonUseaMail status--true");
                } else {
                    objUserMaster.setNonUseaMailingStatus(false);
                    System.out.println("commit nonUseaMail status--false");
                }
                remote.editUserDetails(objUserMaster, objContact);

                Debug.print("objUserMaster.getLoginName() :" + objUserMaster.getLoginName());
                Debug.print("objUserMaster.getEmailId() :" + objUserMaster.getEmailId());

                boolean result = new MySQLDAO().updateUserDetailToMqSQL(objUserMaster, objContact);
                Debug.print("                MySql Result :" + result);

                /*
                 * If Secondary address is updated or added
                 */

                String secstat = request.getParameter("cttyp");
                String userid = request.getParameter("userid");

                System.out.println("sec status :" + secstat);
                System.out.println("user id :" + userid);


                if (usrbean.getSecAdd_cbx() != null) {
                    objContact.setUserId(userid);
                    objContact.setContactType(usrbean.getSecAdd_cbx());
                    objContact.setAddress1(usrbean.getSadd_txt());
                    objContact.setAddress2(usrbean.getSadd_txt1());
                    objContact.setCity(usrbean.getScity_txt());
                    objContact.setState(usrbean.getSstate_txt());
                    objContact.setCountry(usrbean.getScountry_txt());
                    objContact.setZip(usrbean.getSzip_txt());

                    Debug.print("request.getParameter(sphone_txt) value:" + request.getParameter("sphone_txt"));

                    String finalSecPh = "";
                    if (request.getParameter("sphone_txt") != null) {

                        StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sphone_txt"), "[](),.-{}");


                        while (strTkns1.hasMoreTokens()) {
                            try {
                                String phNo = (String) strTkns1.nextToken();

                                if (phNo != null && phNo.trim().length() != 0) {
                                    Debug.print("ph no Added from Stokenizer:" + phNo);
                                    finalSecPh = finalSecPh + phNo;
                                }
                            } catch (Exception e) {
                                Debug.print("Secondary ph no tokanizing exception:" + e);
                            }
                        }

                        Debug.print("finally appended Secondary phNo :" + finalSecPh);
                    }

                    String finalSecMob = "";
                    if (request.getParameter("smob_txt") != null) {

                        StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("smob_txt"), "[](),.-{}");


                        while (strTkns1.hasMoreTokens()) {
                            try {
                                String phNo = (String) strTkns1.nextToken();

                                if (phNo != null && phNo.trim().length() != 0) {
                                    Debug.print("ph no Added from Stokenizer:" + phNo);
                                    finalSecMob = finalSecMob + phNo;
                                }
                            } catch (Exception e) {
                                Debug.print("Secondary ph no tokanizing exception:" + e);
                            }
                        }

                        Debug.print("finally appended Secondary finalSecMob :" + finalSecMob);
                    }

                    String finalSecFax = "";
                    if (request.getParameter("sfax_txt") != null) {

                        StringTokenizer strTkns1 = new StringTokenizer(request.getParameter("sfax_txt"), "[](),.-{}");


                        while (strTkns1.hasMoreTokens()) {
                            try {
                                String phNo = (String) strTkns1.nextToken();

                                if (phNo != null && phNo.trim().length() != 0) {
                                    Debug.print("ph no Added from Stokenizer:" + phNo);
                                    finalSecFax = finalSecFax + phNo;
                                }
                            } catch (Exception e) {
                                Debug.print("Secondary ph no tokanizing exception:" + e);
                            }
                        }

                        Debug.print("finally appended Secondary finalSecFax :" + finalSecFax);
                    }

                    objContact.setPhoneNo(finalSecPh);
                    objContact.setFaxNo(finalSecFax);
                    objContact.setMobileNo(finalSecMob);
                    // remote.addContactDetails(objContact);

                    remote.editUserDetails(objUserMaster, objContact);

                }

                if (secstat != null && usrbean.getSecAdd_cbx() == null) {
                    boolean delstat = remote.deleteContactDetail(userId, "Secondary");
                    System.out.println("Secondary Contact Delete Status :" + delstat);
                }
                request.setAttribute("source", source);
                fwd = "Confirmation";

                if (request.getParameter("confPwd") != null && request.getParameter("confPwd").trim().length() != 0) {
                    String confPwd = request.getParameter("confPwd");
                    Debug.print("confPwd :" + confPwd);

                    //request.setAttribute("cpwd",request.getParameter("confPwd"));

                    //String userId=(String)session.getAttribute("userId");
                    //String currPwd=request.getParameter("currPwd");
                    //String chPass=request.getParameter("reNewPwd");
                    //String chPass=(String)session.getAttribute("confPwd");

                    Debug.print("user id from session :" + userId);
                    //Debug.print("current pwd :" +currPwd);
                    Debug.print("changed password :" + confPwd);

                    boolean status = remote.changePassword(userId, confPwd);

                    Debug.print("changePassword status :" + status);

                    if (status == true) {

                        /* =====================================
                         *
                         * Sending confirmation E-mail
                         *
                         * ====================================*/
                        String emailid = request.getParameter("email");
                        String toMailIds[] = {emailid};
                        EmailContent email = new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Your Account Info !");

                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"https://dashboard.useventing.com/dashboard/images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                                " </tr>" +
                                "  <tr>" +
                                "<td valign=\"top\">" +
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                "<tr>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"https://dashboard.useventing.com/dashboard/images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"https://dashboard.useventing.com/dashboard/images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/left.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                                "<span class=\"boldTxt\">Dear User" + "</span>,<br /><br />" +
                                "<p>Please save this email for your records. Your Password Change Request is Succesful !! Your account information is as follows:.<p>" + "<p>----------------------------<p>" + "<p> New Password : " + confPwd + "<p> ----------------------------<p>" +
                                /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                                "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
                                "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>" +
                                "Thank You <br />" +
                                "------------------ <br />" +
                                "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                                "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/Rght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                "<tr><td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBot.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBotRght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                " </table>" +
                                "</td></tr>" +
                                "+<tr>" +
                                "<td valign=\"top\" style=\"padding:10px;\">" +
                                "<img src=\"http://dashboard.useventing.com/dashboard/images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />" +
                                "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want." +
                                "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:" +
                                "<ul>" +
                                "<li>Unlimited shopping online.</li>" +
                                "<li>Place advertisements online and/or on-site.</li>" +
                                "<li>Sponsor competitions held by HLC.</li>" +
                                "</ul>" +
                                "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as " +
                                "per your �Role� assigned:" +
                                "<ul>" +
                                "<li>Compete in Equestrian Events held by HLC.</li>" +
                                "<li>Take part in other events like; Annual Meetings, Educational events," +
                                "Activity Meetings held by HLC etc.</li>" +
                                "<li>Send Messages to other members.</li>" +
                                "<li>Create your own Distribution Lists.</li>" +
                                "<li>Create/Join a group and share your thoughts and common ideas.</li>" +
                                " <li>Unlimited Shopping online.</li>" +
                                " <li>Place advertisements online and/or on-site.</li>" +
                                " <li>Sponsor competitions held by HLC.</li>" +
                                "</ul>" +
                                "and much more..." +
                                "So go ahead and <a href=\"http://dashboard.useventing.com\">LOGIN NOW!</a></td>" +
                                "</tr>" +
                                " <tr>" +
                                "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"http://dashboard.useventing.com/dashboard/images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                                "</tr>" +
                                "</table>";


                        email.setBody(content);
                        //email.setAttachments();

                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :" + emailFlag);

                    //fwd="changeSuccess";

                    } else {
                        String stat = "fail";
                        request.setAttribute("status", stat);
                    //fwd="changeSuccess";
                    }

                }
            }

            request.setAttribute("eventTypeId", eventTypeId);
        } catch (Exception e) {
            System.out.println(e);
        }

        return mapping.findForward(fwd);

    }
}

