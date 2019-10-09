/*
 * MembershipTypeInsertAction.java
 *
 * Created on September 7, 2006, 3:17 PM
 */
package com.mrm.action.adminmaster;

import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import com.hlcmember.type.util.Debug;
import com.hlcmember.type.util.HLCMembershipTypeMaster;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

/**
 *
 * @author karthikeyan
 * @version
 */
public class NonhumanMembershipTypeInsertAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            String name = "ejb/HLCKaveryMembershipTypeJNDI";
            Context jndiContext = getInitialContext();
            Object obj = jndiContext.lookup(name);
            HLCKaveryMembershipTypeSessionRemoteHome home = (HLCKaveryMembershipTypeSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(obj, HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote remote = home.create();
            HLCMembershipTypeMaster objMemberTypeMaster = new HLCMembershipTypeMaster();
            System.out.println("\n after InitialContext Beginning emp Client...\n");
            //For Debug Starts
            //HttpSession session = request.getSession();
            HttpSession session = request.getSession(true);
            Debug.print("request Called " + request + "=== " + request.getQueryString());
            //For Debug Ends
            String memProcess = request.getParameter("memProcess").trim();
            Debug.print("memProcess Called " + memProcess);
//==================================================MemeberShip Master Started here ===========================================
            if (memProcess != null && memProcess.trim().length() != 0) {
//==================================================MemeberShip Init Method ===========================================
                if (memProcess.equals("initCreate")) {
                    Debug.print("initCreate Called");
                    String uType1 = request.getParameter("uType");
                    String uTypeId = request.getParameter("uTypeId");
                    Debug.print("utype---->" + uType1);

                    Vector vDisUserType = remote.displayUserTypeDetails();
                    Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);
                    ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                    session.setAttribute("displayUserTypeDetails", null);
                    session.setAttribute("displayUserTypeDetails", vMemDetails);
                    session.setAttribute("displayUserTypeDetails", vDisUserType);
                    Debug.print("The After the list");
                    session.setAttribute("displayMemDetails", null);
                    request.setAttribute("accDetails", accDetails);
                    request.setAttribute("status", "true");
                    request.setAttribute("uTypeId", uTypeId);
                    request.setAttribute("uTypeId", uType1);
                    Debug.print("utype---->" + accDetails);
                    return mapping.findForward("membershipCrea");
                } else if (memProcess.equals("createMemTyMaster")) {
                    Debug.print("createMemTyMaster Called");
                    String uTypeId = request.getParameter("uType");
                    String memName = request.getParameter("memName");
                    String memAmount = request.getParameter("memAmount");
                    //for bug starts
                    String duration = request.getParameter("DurationNoDdl") + "-";
                    duration = duration + request.getParameter("DurationTypeDdl");
                    //for bug ends
                    String periodValue = request.getParameter("periodValue");
                    String QBTranascType = request.getParameter("transacType");
                    String memYear = request.getParameter("memYear");
                    int active_Status = Integer.parseInt(request.getParameter("rd1"));

                    if (memYear != null && memYear.trim().length() != 0) {
                        objMemberTypeMaster.setMembership_year(Integer.parseInt(memYear));
                    } else {
                        objMemberTypeMaster.setMembership_year(0);
                    }

                    objMemberTypeMaster.setUserTypeId(uTypeId);
                    objMemberTypeMaster.setMembershipTypeName(memName);
                    objMemberTypeMaster.setMembershipAmount(memAmount);
                    objMemberTypeMaster.setDuration(duration);
                    objMemberTypeMaster.setActive_Status(active_Status);
                    objMemberTypeMaster.setPeriodValue(periodValue);
                    objMemberTypeMaster.setTransaction_type_id(QBTranascType);
                    boolean stat = remote.addMembershipTypeMaster(objMemberTypeMaster);
                    Debug.print("Inserted Sucessfully " + stat);
                    // String status=String.valueOf(stat);

                    if (stat == true) {
                        //For Debug Starts
                        //return mapping.findForward("admin");
                        Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);
                        Vector vDisUserType = remote.displayUserTypeDetails();
                        session.setAttribute("displayMemDetails", null);
                        session.setAttribute("displayMemDetails", vMemDetails);
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType);
                        request.setAttribute("uTypeId", uTypeId);
                        return mapping.findForward("membershipList");
                        //For Debug Ends

                    } else {
                        //Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);
                        Vector vDisUserType = remote.displayUserTypeDetails();
                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType);
                        //session.setAttribute("displayMemDetails" ,null);
                        //session.setAttribute("displayMemDetails" ,vMemDetails);
                        request.setAttribute("uTypeId", uTypeId);
                        request.setAttribute("status", String.valueOf(stat));
                        request.setAttribute("accDetails", accDetails);
                        return mapping.findForward("membershipCrea");

                    }

                } else if (memProcess.equals("initList")) {
                    Debug.print("initList Called");
                    Vector vDisUserType = remote.displayUserTypeDetails();
                    int i = 0;
                    while (i < vDisUserType.size()) {
                        String[] val = (String[]) vDisUserType.get(i);
                        String ID = val[0];
                        String name1 = val[1];
                        /* Debug.print("Value [0] is "+ID);
                        Debug.print("Value [1] is "+name1);*/
                        i++;
                    }
                    ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                    request.setAttribute("accDetails", accDetails);
                    session.setAttribute("displayUserTypeDetails", null);
                    session.setAttribute("displayUserTypeDetails", vDisUserType);
                    session.setAttribute("displayMemDetails", null);
                    return mapping.findForward("membershipList");
                } else if (memProcess.equals("memGetMemDet")) {
                    Debug.print("memGetMemDet Called");
                    String uTypeId = request.getParameter("uTypeId");
                    //  String yr = request.getParameter("year");
                    String membTxt = request.getParameter("membTxt");

                    //int year = 0;
                    Debug.print("uTypeId :" + uTypeId);
                    // Debug.print("year :" + year);
                    Debug.print("membTxt is  " + membTxt);

                    if (uTypeId == null) {
                        Vector vDisUserType1 = remote.displayUserTypeDetails();
                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                        request.setAttribute("accDetails", accDetails);
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType1);
                        session.setAttribute("displayMemDetails", null);
                        request.setAttribute("membTxt", membTxt);
                        session.setAttribute("membTxt", membTxt);
                        return mapping.findForward("membershipList");
                    }
                    if (uTypeId != null && uTypeId.trim().length() != 0) {
                        //Vector vMemDetails = remote.displayMembershipType(uTypeId);
                        Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);

                        int i = 0;
                        while (i < vMemDetails.size()) {
                            String[] val = (String[]) vMemDetails.get(i);
                            i++;
                        }
                        i = 0;
                        Vector vDisUserType = remote.displayUserTypeDetails();
                        while (i < vDisUserType.size()) {
                            String[] val1 = (String[]) vDisUserType.get(i);
                            String ID = val1[0];
                            String name1 = val1[1];
                            Debug.print("Id is " + ID);
                            Debug.print("name is " + name1);
                            i++;
                        }
                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                        request.setAttribute("accDetails", accDetails);
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType);
                        session.setAttribute("displayMemDetails", null);
                        session.setAttribute("displayMemDetails", vMemDetails);
                        request.setAttribute("uTypeId", uTypeId);
                        request.setAttribute("membTxt", membTxt);
                        session.setAttribute("membTxt", membTxt);
                        //   request.setAttribute("year", String.valueOf(year));
                        //  session.setAttribute("year", String.valueOf(year));
                        session.setAttribute("membTxt", membTxt);

                        return mapping.findForward("membershipList");
                    }
                    //return mapping.findForward("admin");
                } else if (memProcess.equals("initEdit")) {
                    Debug.print("initEdit Called");
                    String memId = request.getParameter("memId");
                    String uTypeId = request.getParameter("uType");
                    Debug.print("uTypeId::::>" + uTypeId);
                    String membTxt = request.getParameter("membTxt");
                    if (memId != null && memId.trim().length() != 0) {
                        String[] vMemDetail = remote.getMembershipTypeDetail(memId);
                        //    String [] vMemDetail = remote.getMembershipTypeDetail("membTxt");
                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                        Vector vDisUserType = remote.displayUserTypeDetails();
                        // int year = remote.getMembershipTypeYearOnId(memId);
                        //  request.setAttribute("year", String.valueOf(year));
                        session.setAttribute("displayEditMemDetails", null);
                        session.setAttribute("displayEditMemDetails", vMemDetail);
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType);
                        request.setAttribute("uTypeId", uTypeId);
                        Debug.print("membertype ID" + memId);
                        request.setAttribute("membTxt", membTxt);
                        request.setAttribute("accDetails", accDetails);
                        request.setAttribute("status", "true");
                        return mapping.findForward("membershipEdit");
                    }
                    //return mapping.findForward("admin");
                } else if (memProcess.equals("memTyEdit")) {
                    Debug.print("memTyEdit Called");
                    String memId = request.getParameter("memId");
                    Debug.print("membership ID" + memId);
                    String membTxt = request.getParameter("membTxt");
                    Debug.print("membTxt" + membTxt);

                    String uTypeId = request.getParameter("uType");
                    Debug.print("usertype ID" + uTypeId);

                    String memName = request.getParameter("memName");
                    Debug.print("memName" + memName);
                    String memAmount = request.getParameter("memAmount");
                    String duration = request.getParameter("DurationNoDdl") + "-";
                    duration = duration + request.getParameter("DurationTypeDdl");
                    int active_Status = Integer.parseInt(request.getParameter("rd1"));
                    Debug.print("duration=" + duration);
                    //Debugs Starts by Lakshmi
                    String memYear = request.getParameter("memyear");
                    //Debugs Ends by lakshmi
                    String periodValue = request.getParameter("periodValue");
                    String transacType = request.getParameter("transacType");
                    //Debugs Starts by Lakshmi
                    objMemberTypeMaster.setUserTypeId(uTypeId);
                    Debug.print("UtypeId" +uTypeId);
                    //Debugs Endss by Lakshmi
                    objMemberTypeMaster.setMembershipTypeId(memId);
                    Debug.print("set memid" + memId);
                    objMemberTypeMaster.setMembershipTypeName(membTxt);
                    Debug.print("set membTxt" + membTxt);
                    // objMemberTypeMaster.setUserTypeId(uTypeId);
                    // Debug.print("set uTypeId" + uTypeId);
                    objMemberTypeMaster.setMembershipTypeName(memName);
                    Debug.print("memName" + memName);
                    objMemberTypeMaster.setMembershipAmount(memAmount);
                    objMemberTypeMaster.setDuration(duration);
                    Debug.print("duration" + duration);
                    //objMemberTypeMaster.setMembership_year(memYear);
                    objMemberTypeMaster.setActive_Status(active_Status);
                    Debug.print("active_staus=" + active_Status);
                    objMemberTypeMaster.setPeriodValue(periodValue);
                    objMemberTypeMaster.setTransaction_type_id(transacType);
                    if (memYear != null && !memYear.equalsIgnoreCase("0")) {
                        objMemberTypeMaster.setMembership_year(Integer.parseInt(memYear));
                    } else {
                        objMemberTypeMaster.setMembership_year(0);
                    }
                    boolean stat = remote.editMembershipTypeMaster(objMemberTypeMaster);

                    if (stat == true) {
                        Debug.print("memTyEdit Sucessfully Editted:" + memId);
                        //String sesYear = (String) session.getAttribute("year");
                        //   int year = 0;
                        //    if (sesYear != null && sesYear.trim().length() != 0) {
                        //       year = Integer.parseInt(sesYear);
                        //    }
                        //String membTxt = (String)session.getAttribute("membTxt");
                        //Vector vMemDetails = remote.displayMembershipType(uTypeId);
                        Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);
                        request.setAttribute("uTypeId", uTypeId);
                        Vector vDisUserType = remote.displayUserTypeDetails();
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType);
                        session.setAttribute("displayMemDetails", null);
                        session.setAttribute("displayMemDetails", vMemDetails);
                        request.setAttribute("status", String.valueOf(stat));
                        request.setAttribute("membTxt", membTxt);
                        return mapping.findForward("membershipList");
                    } else {
                        String[] vMemDetail = remote.getMembershipTypeDetail(memId);
                        // int year = remote.getMembershipTypeYearOnId(memId);
                        //  request.setAttribute("year", String.valueOf(year));
                        ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                        Vector vDisUserType = remote.displayUserTypeDetails();
                        session.setAttribute("displayEditMemDetails", null);
                        session.setAttribute("displayEditMemDetails", vMemDetail);
                        session.setAttribute("displayUserTypeDetails", null);
                        session.setAttribute("displayUserTypeDetails", vDisUserType);
                        request.setAttribute("uTypeId", uTypeId);
                        //For Debugs Starts
                        request.setAttribute("memName", memName);
                        Debug.print("memName"+memName);
                        //For Debugs Ends
                        request.setAttribute("accDetails", accDetails);
                        request.setAttribute("status", "false");
                        return mapping.findForward("membershipEdit");
                    }
                } else if (memProcess.equals("initDelete")) {
                    Debug.print("initDelete Called");
                    String memId = request.getParameter("memId");
                    if (memId != null && memId.trim().length() != 0) {
                        String[] vMemDetail = remote.getMembershipTypeDetail(memId);
                        // int year = remote.getMembershipTypeYearOnId(memId);
                        // request.setAttribute("year", String.valueOf(year));
                        session.setAttribute("displayEditMemDetails", null);
                        session.setAttribute("displayEditMemDetails", vMemDetail);
                        request.setAttribute("uTypeId", memId);
                        return mapping.findForward("membershipDelete");
                    }
                    // return mapping.findForward("admin");
                } else if (memProcess.equals("memTyDelete")) {
                    Debug.print("memTyDelete Called");
                    Debug.print("memTyDelete Called");
                    String memID[] = request.getParameterValues("chk");
                    String typeid;
                    String uTypeId = request.getParameter("uType");
                    if (memID != null) {
                        System.out.println("You have selected:" + memID.length);
                        for (int i = 0; i < memID.length; i++) {
                            typeid = memID[i];
                            if (typeid != null && typeid.trim().length() != 0) {
                                boolean result = remote.deleteMembershipTypeMaster(typeid);
                                System.out.println("MembershipTypeId: " + typeid);
                                System.out.println("Delete Operation Result " + result);
                            }
                        }
                    }
                    Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);
                    request.setAttribute("uTypeId", uTypeId);
                    Vector vDisUserType = remote.displayUserTypeDetails();
                    ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                    request.setAttribute("accDetails", accDetails);
                    session.setAttribute("displayUserTypeDetails", null);
                    session.setAttribute("displayUserTypeDetails", vDisUserType);
                    session.setAttribute("displayMemDetails", null);
                    session.setAttribute("displayMemDetails", vMemDetails);
                    return mapping.findForward("membershipList");
                } else if (memProcess.equals("initView")) {
                    Debug.print("initView Called");
                    String memId = request.getParameter("memId");
                    String membTxt = request.getParameter("membTxt");
                    if (memId != null && memId.trim().length() != 0) {
                        String[] vMemDetail = remote.getMembershipTypeDetail(memId);
                        //  int year = remote.getMembershipTypeYearOnId(memId);
                        // request.setAttribute("year", String.valueOf(year));
                        session.setAttribute("displayEditMemDetails", null);
                        session.setAttribute("displayEditMemDetails", vMemDetail);
                        request.setAttribute("uTypeId", memId);
                        request.setAttribute("membTxt", membTxt);
                        return mapping.findForward("membershipViewSingle");
                    }
                    // return mapping.findForward("admin");
                } //==================================================MemeberShip Master Ended here ===========================================
                else if (memProcess.equals("Listing")) {
                    Debug.print("Inside Listing");
                    String memId = request.getParameter("memId");
                    Debug.print("memId is " + memId);
                    String membTxt = request.getParameter("membTxt");
                    String process = request.getParameter("btnsubmit");
                    Debug.print("button Value " + process);
                    if (memId != null && memId.trim().length() != 0) {
                        if (process.equals("Edit")) {
                            String utypeid = request.getParameter("uType");
                            String[] vMemDetail = remote.getMembershipTypeDetail(memId);
                            session.setAttribute("displayEditMemDetails", null);
                            session.setAttribute("displayEditMemDetails", vMemDetail);
                            request.setAttribute("uTypeId", utypeid);
                            ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                            request.setAttribute("accDetails", accDetails);
                            request.setAttribute("status", "true");
                            return mapping.findForward("membershipEdit");
                        } else if (process.equals("Delete")) {
                            String memID = request.getParameter("memId");
                            if (memID != null && memID.trim().length() != 0) {
                                String[] vMemDetail = remote.getMembershipTypeDetail(memID);
                                //int year = remote.getMembershipTypeYearOnId(memId);
                                //  request.setAttribute("year", String.valueOf(year));
                                Vector vDisUserType = remote.displayUserTypeDetails();
                                session.setAttribute("displayUserTypeDetails", null);
                                session.setAttribute("displayUserTypeDetails", vDisUserType);
                                session.setAttribute("displayDeleteMemDetails", null);
                                session.setAttribute("displayDeleteMemDetails", vMemDetail);
                                request.setAttribute("uTypeId", memID);
                                return mapping.findForward("membershipDelete");
                            }
                        } else if (process.equals("Create")) {
                            Debug.print("createMemTyMaster Called");
                            String uTypeId = request.getParameter("uType");
                            String memName = request.getParameter("memName");
                            String memAmount = request.getParameter("memAmount");
                            String duration = request.getParameter("DurationNoDdl") + "-";
                            duration = duration + request.getParameter("DurationTypeDdl");
                            String periodValue = request.getParameter("periodValue");
                            String QBTranascType = request.getParameter("transacType");
                            String memYear = request.getParameter("memYear");
                            Debug.print("memYear " + memYear);

                            if (memYear != null && memYear.trim().length() != 0) {
                                objMemberTypeMaster.setMembership_year(Integer.parseInt(memYear));
                            } else {
                                objMemberTypeMaster.setMembership_year(0);
                            }

                            objMemberTypeMaster.setUserTypeId(uTypeId);
                            objMemberTypeMaster.setMembershipTypeName(memName);
                            objMemberTypeMaster.setMembershipAmount(memAmount);
                            objMemberTypeMaster.setPeriodValue(periodValue);
                            objMemberTypeMaster.setTransaction_type_id(QBTranascType);
                            objMemberTypeMaster.setDuration(duration);
                            boolean stat = remote.addMembershipTypeMaster(objMemberTypeMaster);
                            Debug.print("Inserted Sucessfully " + stat);
                            if (stat == true) {
                                Debug.print("Inserted Sucessfully " + stat);
                            }

                            Vector vMemDetails = remote.displayNonHumanMembershipTypeDetail(uTypeId);
                            ArrayList accDetails = (ArrayList) remote.getAccTxnTypDetails();
                            Vector vDisUserType = remote.displayUserTypeDetails();
                            session.setAttribute("displayUserTypeDetails", null);
                            session.setAttribute("displayUserTypeDetails", vDisUserType);
                            request.setAttribute("accDetails", accDetails);
                            session.setAttribute("displayMemDetails", null);
                            session.setAttribute("displayMemDetails", vMemDetails);
                            return mapping.findForward("membershipList");
                        } else {
                            return null;
                        }

                    }
                }
            }

//==================================================Try Block Closing here===========================================
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        return null;
    }

    public static Context getInitialContext() throws javax.naming.NamingException {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
        p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        p.setProperty("java.naming.provider.url", "");
        return new javax.naming.InitialContext(p);
    }
}
