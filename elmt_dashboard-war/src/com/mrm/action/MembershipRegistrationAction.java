/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.mrm.action;
/*
 * MembershipRegistrationAction.java
 *
 * Created on August 24, 2006, 3:10 PM
 */

import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.Debug;
import com.hlcform.util.HLCMemberDetails;
import com.hlchorse.form.display.HLCKaverySessionStatefulRemote;
import com.hlchorse.form.display.HLCKaverySessionStatefulRemoteHome;
import com.hlchorse.form.util.HLCUserRegVO;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemote;
import com.hlcmember.type.stateless.HLCKaveryMembershipTypeSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import com.hlcform.util.HLCPaymentDetails;
import java.util.*;
import java.util.Date;
import java.text.*;
import org.apache.struts.util.*;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcform.stateless.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class MembershipRegistrationAction extends Action {
    
    String fwd="";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try {
                   /*
                    * Getting required parameters from session
                    */
            
            HttpSession session=request.getSession();
            String usrNam=(String) session.getAttribute("loginName");
            String usrId=(String) session.getAttribute("userId");
            
                    /*
                     * getting the request parameter
                     */
            
            String process=request.getParameter("process");
            
                    /*
                     * getting the JNDI names to initialize service locator from ApplicationResources.Properties file
                     */
            
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.kaverysless");
            String jndiname2=mr.getMessage("jndi.usrreg");
            String jndiname3=mr.getMessage("jndi.kaverymrm");
            String jndiname4=mr.getMessage("jndi.kaverysful");
            
                    /*
                     * Initialize Service Locators
                     */
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            
            HLCkaverySessionBeanStatlessRemoteHome home =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref, HLCkaverySessionBeanStatlessRemoteHome.class);
            Vector dispMembershipTypeVect = new Vector();
            Vector areUAMemberVect = new Vector();
            Vector membershipToVect = new Vector();
            Vector serviceTypeVect = new Vector();
            
            HLCkaverySessionBeanStatlessRemote remote = home.create();
            
            areUAMemberVect = remote.areUAMember();
            membershipToVect = remote.membershipTo();
            
            String country=remote.getCountryName(usrId);
            Debug.print("User Country :"+country);
            
            Object objref2 = jndiContext.lookup(jndiname2);
            HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote1 = home1.create();
            
            Object objref3 = jndiContext.lookup(jndiname3);
            HLCkaveryStatelessRemoteHome home2 = (HLCkaveryStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref3,HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote remote2 = home2.create();
            
            ArrayList donDet=new ArrayList();
            donDet=remote2.getAllDonationDetails();
            
            String memberId=(String) session.getAttribute("memberId");
            String userIdforDate = (String)session.getAttribute("userId");
            
            String name2 = "ejb/HLCKaveryMembershipTypeJNDI";
            
            Object obj2=jndiContext.lookup(name2);
            HLCKaveryMembershipTypeSessionRemoteHome memberHome2 = (HLCKaveryMembershipTypeSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj2,HLCKaveryMembershipTypeSessionRemoteHome.class);
            HLCKaveryMembershipTypeSessionRemote memberRemote2 = memberHome2.create();
            
            java.util.Calendar toDay1 = java.util.Calendar.getInstance();
            int newyear1 = toDay1.get(Calendar.YEAR);
            int new_month1 = toDay1.get(Calendar.MONTH);
            Debug.print("new Year1 "+newyear1);
            Debug.print("new month "+new_month1);
            String[] addonPrice= new String[2];
            if(new_month1==11){
                Debug.print("In month == 11");
                dispMembershipTypeVect = memberRemote2.displayMembershipTypeOnYearAndTypeName("human",newyear1+1);
                addonPrice = remote.getFamilyAddOnPrice("family member",newyear1+1);
            } else{
                Debug.print("In month != 11");
                dispMembershipTypeVect = remote.getMembershipTypesOnCurYr("Human");
                addonPrice = remote.getFamilyAddOnPrice("family member",newyear1);
            }
            
            ArrayList humanMembTypes = memberRemote2.getMembershipTypeForRegisteration();
            request.setAttribute("humanMembTypes",humanMembTypes);
            
            //dispMembershipTypeVect = remote.displayMembershipType("Human");
            
            String name="ejb/HLCKaveryJNDI";
            Object horseref = jndiContext.lookup(name);
            HLCKaverySessionBeanStatfulRemoteHome horsehome = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(horseref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote horseremote = horsehome.create();
            
            Object objref4 = jndiContext.lookup(jndiname4);
            HLCKaverySessionStatefulRemoteHome home4 =
                    (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref4, HLCKaverySessionStatefulRemoteHome.class);
            HLCKaverySessionStatefulRemote remote4 = null;
            remote4 = home4.create();
            
            serviceTypeVect=remote.displayHorsePhoneServiceType("Horse");
            
            request.setAttribute("dispMembershipTypeVect", dispMembershipTypeVect);
            request.setAttribute("areUAMemberVect", areUAMemberVect);
            request.setAttribute("membershipToVect", membershipToVect);
            request.setAttribute("addonPrice",addonPrice);
            request.setAttribute("donDet",donDet);
            request.setAttribute("country",country);
            request.setAttribute("serviceTypeVect",serviceTypeVect);
            
            boolean memextstat=remote1.isMemberExist(userIdforDate);
            Debug.print("remote1.isMemberExist(userIdforDate) :"+memextstat);
            
            if(process!=null) {
                        /*
                         * Redirect to member-registration.jsp Page
                         */
                
                if(process.equalsIgnoreCase("reg")) {
                    //Debug.print("isMemberExist status in servlet :" + memextstat);
                    
                    Debug.print("     User Id:" + userIdforDate);
                    Date dob = null;
                    dob=remote1.getUserBirthDate(userIdforDate);
                    Debug.print("     User DOB:" + dob);
                    
                    Debug.print("dob in Servlet:" + dob);
                    request.setAttribute("dobCnt",dob);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    
                    ArrayList objlist = (ArrayList)remote2.getAllPublicationDetails();
                    request.setAttribute("DispPubDetails",objlist);
                    
                    
                    //Added for Furture year price
                    
                    java.util.Calendar toDay = java.util.Calendar.getInstance();
                    int newyear = toDay.get(Calendar.YEAR);
                    
                    String[] dBnewFamilyPrice = memberRemote2.selectNextYrPrice("family member",newyear+1);
                    String newFamilyPrice = dBnewFamilyPrice[0]+"#"+dBnewFamilyPrice[1];
                    Debug.print("New Family Price "+newFamilyPrice);
                    request.setAttribute("newFamilyPrice",newFamilyPrice);
                    
                    // Ended for Furture year price
                    
                    if(memextstat==true) {
                        
                        String stat="If you would like to renew your membership, please go to our <a href=" + "MembershipReg.do?process=renew" +">Renewal Page</a>";
                        Debug.print("stat in servlet "+stat);
                        request.setAttribute("stat",stat);
                        fwd="alreadymemb";
                    } else {
                        fwd="MembershipReg";
                    }
                    
                }
                
                        /*
                         * Company Registration Block
                         *
                         *
                         
                        if(process.equalsIgnoreCase("preMember")){
                            Debug.print("Pre Member Confirmation Block");
                            fwd = "preMember";
                        }
                         
                        if(process.equalsIgnoreCase("compRegi")){
                            ArrayList objlist = (ArrayList)remote2.getAllPublicationDetails();
                            request.setAttribute("DispPubDetails",objlist);
                            fwd = "compRegi";
                        }
                         
                        /*
                         * Redirect to member-registration-renewal.jsp Page
                         */
                
                if(process.equalsIgnoreCase("renew")) {
                    
                    String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
                    //Context jndiContext1 = getInitialContext();
                    Object obj=jndiContext.lookup(name1);
                    HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
                    HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
                    
                    System.out.println("inside membership renewal diaplay block : ");
                    if(memberId==null){
                        if(memextstat==false){
                            String stat="Sorry You should first register as a member !!";
                            request.setAttribute("stat",stat);
                            fwd="notaMemb";
                        }
                    } else{
                        String status=remote1.getStatusByMemberId(memberId);
                        System.out.println("memberId : "+memberId);
                        
                        if(memberId != null){
                            ArrayList histValues = remote1.getMembershipStatusFromHistory(memberId);
                            request.setAttribute("histValues",histValues);
                        }
                        
                        java.util.Calendar toDay = java.util.Calendar.getInstance();
                        int newyear = toDay.get(Calendar.YEAR);
                        
                        Enumeration itrators= (Enumeration)dispMembershipTypeVect.elements();
                        String memNme = "";
                        
                        HLCMemberDetails objMember = new HLCMemberDetails();
                        objMember=remote1.displayMemberDetail(memberId);
                        
                        String membTypId = objMember.getMembershipTypeId();
                        int prior_val = memberRemote.selectPriorityValue(membTypId);
                        request.setAttribute("prior_value",String.valueOf(prior_val));
                        
                        //Added for Furture year price
                        String[] dBfuturePrice = memberRemote2.selectNextYrPrice(objMember.getMembershipTypeName(),newyear+1);
                        String futurePrice = dBfuturePrice[0]+"#"+dBfuturePrice[1];
                        Debug.print("futurePrice  "+futurePrice);
                        request.setAttribute("futurePrice",futurePrice);
                        
                        String[] dBnewFamilyPrice = memberRemote2.selectNextYrPrice("family member",newyear+1);
                        String newFamilyPrice = dBnewFamilyPrice[0]+"#"+dBnewFamilyPrice[1];
                        Debug.print("New Family Price "+newFamilyPrice);
                        request.setAttribute("newFamilyPrice",newFamilyPrice);
                        
                        // Ended for Furture year price
                        Debug.print("remote1.displayMemberDetail(memberId) :" + objMember);
                        System.out.println("objMember.getMembershipTypeName() from servlet : "+objMember.getMembershipTypeName());
                        
                        Vector userVect3=memberRemote.selectDetailsForMember(memberId);
                        request.setAttribute("userTypeVect3",userVect3);
                        
                        Date dob = null;
                        dob=remote1.getUserBirthDate(userIdforDate);
                        Debug.print("     User DOB:" + dob);
                        
                        Date expDt=new Date();
                        expDt=remote1.getExpiredDate(memberId);
                        Debug.print("remote1.getExpiredDate(memberId) :" + expDt);
                        request.setAttribute("expDt",expDt);
                        request.setAttribute("status",status);
                        
                        Debug.print("dob in Servlet:" + dob);
                        request.setAttribute("dobCnt",dob);
                        
                        request.setAttribute("objMember",objMember);
                        
                        ArrayList nonhlc=new ArrayList();
                        ArrayList donSelect=new ArrayList();
                        
                        nonhlc=remote.getMemberNonUseaDetails(memberId);
                        Debug.print("user Id from session : " +userIdforDate);
                        
                        donSelect=remote.getMemberDonationDetails(userIdforDate);
                        
                        request.setAttribute("nonhlc",nonhlc);
                        request.setAttribute("donSelect",donSelect);
                        
                        ArrayList objlist = (ArrayList)remote2.getAllPublicationDetails();
                        request.setAttribute("DispPubDetails",objlist);
                        
                        if(memberId!=null) {
                            if(memextstat==true && !status.equalsIgnoreCase("Pending")) {
                                fwd="MembershipRenewal";
                            } else {
                                if(status.equalsIgnoreCase("Pending")) {
                                    String stat="Sorry Your membership approval status is still pending !!";
                                    request.setAttribute("stat",stat);
                                    request.setAttribute("membStat",status);
                                }
                                
                                if(memextstat==false) {
                                    String stat="Sorry You should first register as a member !!";
                                    request.setAttribute("stat",stat);
                                }
                                
                                fwd="notaMemb";
                                
                            }
                            
                            
                        }
                        
                        else {
                            if(memextstat==false) {
                                String stat="Sorry You should first register as a member !!";
                                request.setAttribute("stat",stat);
                            } else {
                                
                                String stat="Sorry Your membership approval status is still pending !!";
                                request.setAttribute("stat",stat);
                                request.setAttribute("membStat",status);
                            }
                            
                            fwd="notaMemb";
                        }
                    }
                }
                
                            /*
                             * Redirect to view-member-regi.jsp Page
                             */
                        
                String name4 = "ejb/HLCKaveryMembershipTypeJNDI";
                //Context jndiContext1 = getInitialContext();
                Object objKav=jndiContext.lookup(name4);
                HLCKaveryMembershipTypeSessionRemoteHome memberHome4 = (HLCKaveryMembershipTypeSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objKav,HLCKaveryMembershipTypeSessionRemoteHome.class);
                HLCKaveryMembershipTypeSessionRemote memberRemote4 = memberHome4.create();
                
                if(process.equalsIgnoreCase("view")) {
                    
                    ArrayList donSelect=new ArrayList();
                    HLCMemberDetails objMember = new HLCMemberDetails();
                    ArrayList mailPreference = new ArrayList();
                    ArrayList familyAddOnId = new ArrayList();
                    ArrayList nonhlc=new ArrayList();
                    
                    String uId = (String) session.getAttribute("userId");
                    Debug.print("View Page");
                                 /*if(uId!=null && uId.trim().length()!=0){
                                    ArrayList histValues = remote1.getMembershipStatusFromHistory(uId);
                                    request.setAttribute("histValues",histValues);
                                 }*/
                    
                    if(request.getParameter("memberId") != null){
                        memberId=request.getParameter("memberId");
                        //String userhistUId = remote1.getUserIdBasedOnMemberId(memberId);
                        ArrayList histValues = remote1.getMembershipDetailsFromHistory(memberId);
                        request.setAttribute("histValues",histValues);
                    } else{
                        memberId = (String) session.getAttribute("memberId");
                        ArrayList histValues = remote1.getMembershipDetailsFromHistory(memberId);
                        request.setAttribute("histValues",histValues);
                    }
                    if(request.getParameter("memberId") != null && request.getParameter("memberId").trim().length() != 0) {
                        memberId=request.getParameter("memberId");
                        Debug.print("request.getParameter(memberId) :"+memberId);
                    }
                    
                    if(request.getParameter("userId") != null && request.getParameter("userId").trim().length() != 0) {
                        userIdforDate=request.getParameter("userId");
                        Debug.print("request.getParameter(userId) :"+userIdforDate);
                        request.setAttribute("userId",userIdforDate);
                        
                        if(request.getParameter("ownerInfo") != null && request.getParameter("ownerInfo").trim().length() != 0) {
                            if(request.getParameter("ownerInfo").equalsIgnoreCase("ownerValue")) {
                                Debug.print("request.getParameter(ownerInfo) :"+request.getParameter("ownerInfo"));
                                
                                memberId= remote1.getMemberIdBasedOnUserId(userIdforDate);
                                Debug.print("remote1.getMemberIdBasedOnUserId(userIdforDate) :"+memberId);
                                if(memberId==null || memberId.trim().length()==0){
                                    HLCUserRegVO ownerInfo=new HLCUserRegVO();
                                    ownerInfo = remote4.selectUserRegistrationForm1(userIdforDate);
                                    request.setAttribute("EditusrVect",ownerInfo);
                                    return mapping.findForward("DispOwnerDetails");
                                }
                            }
                        }
                    }
                    
                    Debug.print("userId from servlet :"+userIdforDate);
                    
                    
                    
                    Vector userVect3=memberRemote4.selectDetailsForMember(memberId);
                    request.setAttribute("userTypeVect3",userVect3);
                    
                    ArrayList objlist = (ArrayList)remote2.getAllPublicationDetails();
                    request.setAttribute("DispPubDetails",objlist);
                    
                    
                    objMember=remote1.displayMemberDetail(memberId);
                    request.setAttribute("objMember",objMember);
                    
                    
                    
                    
                    mailPreference = memberRemote4.getMailPreference(memberId);
                    familyAddOnId = memberRemote4.getFamilyAddOnId(memberId);
                    
                    nonhlc=remote.getMemberNonUseaDetails(memberId);
                    
                    //Debug.print("user Id from session : " +userIdforDate);
                    donSelect=remote.getMemberDonationDetails(userIdforDate);
                    
                    request.setAttribute("nonhlc",nonhlc);
                    request.setAttribute("donSelect",donSelect);
                    request.setAttribute("mailPreference",mailPreference);
                    request.setAttribute("familyAddOnId",familyAddOnId);
                    
                    HLCPaymentDetails objPayment = new HLCPaymentDetails();
                    objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                    request.setAttribute("objPayment",objPayment);
                    
                    Debug.print("objMember.getPaymentId() :"+objMember.getPaymentId());
                    System.out.println("objPayment :"+objPayment.toString());
                    
                    ArrayList resultNew = (ArrayList)remote1.getUserContactDetailsForAdmin(userIdforDate);
                    String mailStatus = (String)resultNew.get(16);
                    String splNotes = (String)resultNew.get(17);
                    System.out.println("Click mailStatus:"+mailStatus);
                    System.out.println("Click splNotes:"+splNotes);
                    request.setAttribute("mailStatus",mailStatus);
                    request.setAttribute("splNotes",splNotes);
                    Date approvalDate =(Date) remote1.getApprovedDate(memberId);
                    request.setAttribute("approvalDate",approvalDate);
                    
                    HLCUserRegVO userregvo=new HLCUserRegVO();
                    userregvo = remote4.selectUserRegistrationForm1(userIdforDate);
                    Debug.print("user details :"+userregvo.toString());
                    
                    request.setAttribute("userregvo",userregvo);
                    
                    fwd="membView";
                    //}
        
                }
                
                if(process.equalsIgnoreCase("familyView")) {
                    Debug.print("process in Family View"+process);
                    
                    ArrayList donSelect1=new ArrayList();
                    HLCMemberDetails objMember = new HLCMemberDetails();
                    ArrayList mailPreference = new ArrayList();
                    ArrayList familyAddOnId = new ArrayList();
                    ArrayList nonhlc=new ArrayList();
                    
                    String memberId1=request.getParameter("familyAddOnId1");
                    String familyUserId=request.getParameter("userId");
                    
                    if(memberId1 != null && memberId1.trim().length()!= 0) {                 
                        Debug.print("request.getParameter(familyAddOnId1) in FamilyView :"+memberId1); 
                        if(familyUserId != null && familyUserId.trim().length() != 0) {                         
                            Debug.print("request.getParameter(userId) in FamilyView :"+familyUserId);
                            request.setAttribute("userId",familyUserId);                           
                            donSelect1=remote.getMemberDonationDetails(familyUserId);
                            Debug.print("donSelect1 in familyView " +donSelect1);
                            if(donSelect1!=null){
                                request.setAttribute("donSelect1",donSelect1);
                            } else {
                                
                                request.setAttribute("donSelect1",null);
                            }
                            ArrayList resultNew = (ArrayList)remote1.getUserContactDetailsForAdmin(familyUserId);
                            String mailStatus = (String)resultNew.get(16);
                            String splNotes = (String)resultNew.get(17);
                            System.out.println("Click mailStatus in Family View:"+mailStatus);
                            System.out.println("Click splNotes in Family View:"+splNotes);
                            request.setAttribute("mailStatus",mailStatus);
                            request.setAttribute("splNotes",splNotes);
                            
                            Date approvalDate =(Date) remote1.getApprovedDate(memberId1);
                            request.setAttribute("approvalDate",approvalDate);
                            HLCUserRegVO userregvo=new HLCUserRegVO();
                            userregvo = remote4.selectUserRegistrationForm1(familyUserId);
                            Debug.print("user details :"+userregvo.toString());
                            request.setAttribute("userregvo",userregvo);
                            Vector userVect3=memberRemote4.selectDetailsForMember(memberId1);
                            request.setAttribute("userTypeVect3",userVect3);
                            objMember=remote1.displayMemberDetail(memberId1);
                            request.setAttribute("objMember",objMember);
                            mailPreference = memberRemote4.getMailPreference(memberId1);
                            familyAddOnId = memberRemote4.getFamilyAddOnId(memberId1);
                            nonhlc=remote.getMemberNonUseaDetails(memberId1);
                            
                            request.setAttribute("nonhlc",nonhlc);
                            request.setAttribute("mailPreference",mailPreference);
                            request.setAttribute("familyAddOnId",familyAddOnId);
                            
                            HLCPaymentDetails objPayment = new HLCPaymentDetails();
                            objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                            request.setAttribute("objPayment",objPayment);
                            
                            Debug.print("objMember.getPaymentId() in Family View :"+objMember.getPaymentId());
                            System.out.println("objPayment :"+objPayment.toString());
                            
                            if(request.getParameter("memberId") != null){
                                String memberId2=request.getParameter("memberId");
                                Debug.print("memberId2 in Family View" +memberId2);
                                ArrayList histValues = remote1.getMembershipDetailsFromHistory(memberId2);
                                request.setAttribute("histValues",histValues);
                            }
                        }
                   
                    fwd="famMembView";
                    }
                     /*
                    * Redirect to approve-memberSearch-regi.jsp Page
                    */
                    
                    else if(request.getParameter("status") != null) {
                    Debug.print("request.getParameter(status) in Family View approve :"+request.getParameter("status"));
                    String approMembId=request.getParameter("memberId");
                    String appUserId=request.getParameter("userId");                   
                    if(approMembId != null && approMembId.trim().length()!= 0) {                 
                        Debug.print("request.getParameter(familyAddOnId1) in FamilyView approve :"+approMembId); 
                        
                        if(appUserId != null && appUserId.trim().length() != 0) {                        
                            Debug.print("request.getParameter(userId) in FamilyView approve :"+appUserId);
                            request.setAttribute("userId",appUserId);                           
                            donSelect1=remote.getMemberDonationDetails(appUserId);
                            Debug.print("donSelect1 in familyView approve " +donSelect1);
                            if(donSelect1!=null){
                                request.setAttribute("donSelect1",donSelect1);
                            } else {
                                
                                request.setAttribute("donSelect1",null);
                            }
                            ArrayList resultNew = (ArrayList)remote1.getUserContactDetailsForAdmin(appUserId);
                            String mailStatus = (String)resultNew.get(16);
                            String splNotes = (String)resultNew.get(17);
                            System.out.println("Click mailStatus in Family View approve:"+mailStatus);
                            System.out.println("Click splNotes in Family View approve:"+splNotes);
                            request.setAttribute("mailStatus",mailStatus);
                            request.setAttribute("splNotes",splNotes);
                            
                            Date approvalDate =(Date) remote1.getApprovedDate(approMembId);
                            request.setAttribute("approvalDate",approvalDate);
                            HLCUserRegVO userregvo=new HLCUserRegVO();
                            userregvo = remote4.selectUserRegistrationForm1(appUserId);
                            Debug.print("user details :"+userregvo.toString());
                            request.setAttribute("userregvo",userregvo);
                            Vector userVect3=memberRemote4.selectDetailsForMember(approMembId);
                            request.setAttribute("userTypeVect3",userVect3);
                            objMember=remote1.displayMemberDetail(approMembId);
                            request.setAttribute("objMember",objMember);
                            mailPreference = memberRemote4.getMailPreference(approMembId);
                            familyAddOnId = memberRemote4.getFamilyAddOnId(approMembId);
                            nonhlc=remote.getMemberNonUseaDetails(approMembId);
                            
                            request.setAttribute("nonhlc",nonhlc);
                            request.setAttribute("mailPreference",mailPreference);
                            request.setAttribute("familyAddOnId",familyAddOnId);
                            
                            HLCPaymentDetails objPayment = new HLCPaymentDetails();
                            objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                            request.setAttribute("objPayment",objPayment);
                            
                            Debug.print("objMember.getPaymentId() in Family View approve :"+objMember.getPaymentId());
                            System.out.println("objPayment :"+objPayment.toString());
                            
                            if(request.getParameter("memberId") != null){
                                String memberId3=request.getParameter("memberId");
                                Debug.print("memberId3 in Family View approve" +memberId3);
                                ArrayList histValues = remote1.getMembershipDetailsFromHistory(memberId3);
                                request.setAttribute("histValues",histValues);
                            }
                        }            
                    }
                 fwd="approveMemb";
                        
                    }
                }
                                   /*
                                    * Redirect to view-member-payment.jsp Page
                                    */
                
                if(process.equalsIgnoreCase("membPay")) {
                    
                    Debug.print("memberId :"+memberId);
                    HLCMemberDetails objMember = new HLCMemberDetails();
                    HLCPaymentDetails objPayment = new HLCPaymentDetails();
                    
                    HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
                    
                    if(memberId!=null) {
                        
                        objMember=remote1.displayMemberDetail(memberId);
                        Debug.print("objMember :"+objMember.toString());
                        
                        objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                        
                        Debug.print("objMember.getPaymentId() :"+objMember.getPaymentId());
                        System.out.println("objPayment :"+objPayment.toString());
                        ArrayList NSFcharge = (ArrayList) remote1.getNsfPaymentDetails(objMember.getPaymentId());
                        Debug.print("NSFcharge Size"+NSFcharge.size());
                        request.setAttribute("NSFcharge",NSFcharge);
                    }
                    
                    
                    String userId=(String)session.getAttribute("userId");
                    Debug.print("userId in servlet MembershipRegistrationAction:"+userId);
                    ArrayList pendingInfo = null;
                    ArrayList horsependInfo = null;
                    ArrayList horseDuePendInfo = null;
                    ArrayList horseChngPendInfo = null;
                    ArrayList annaulPendDet = null;
                    ArrayList eventPendPay = null;
                    if(userId!=null && userId.trim().length()!=0){
                        pendingInfo = horseremote.getAllPendingPaymentsForHorse(userId);
                        horsependInfo = horseremote.getAllPendingPaymentsForHorseUser(userId);
                        horseDuePendInfo = horseremote.getAllDuePaymentsForHorse(userId);
                        horseChngPendInfo = horseremote.getAllDuePaymentsForHorseUser(userId);
                        annaulPendDet = vaiRemote.getAnnualPendingDetails(userId);
                        eventPendPay =vaiRemote.getEventRegPayPendingDets(userId);
                    }
                    request.setAttribute("pendingInfo",pendingInfo);
                    request.setAttribute("horsependInfo",horsependInfo);
                    request.setAttribute("horseDuePendInfo",horseDuePendInfo);
                    request.setAttribute("horseChngPendInfo",horseChngPendInfo);
                    request.setAttribute("objPayment",objPayment);
                    request.setAttribute("annualPend",annaulPendDet);
                    request.setAttribute("eventPendPay",eventPendPay);
                    
                    fwd="memberPayment";
                    
                }
                
                        /*
                         * Redirect to view-member-regi.jsp Page
                         */
                
                if(process.equalsIgnoreCase("viewAddOn")) {
                    
                    String name1 = "ejb/HLCKaveryMembershipTypeJNDI";
                    //Context jndiContext1 = getInitialContext();
                    Object obj=jndiContext.lookup(name1);
                    HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj,HLCKaveryMembershipTypeSessionRemoteHome.class);
                    HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
                    
                    String addOnMemId = (String)request.getParameter("familyAddOnId1");
                    Vector userVect3=memberRemote.selectDetailsForMember(addOnMemId);
                    request.setAttribute("userTypeVect3",userVect3);
                    
                    HLCMemberDetails objMember = new HLCMemberDetails();
                    objMember=remote1.displayMemberDetail(addOnMemId);
                    request.setAttribute("objMember",objMember);
                    
                    ArrayList nonhlc=new ArrayList();
                    ArrayList donSelect=new ArrayList();
                    ArrayList mailPreference = new ArrayList();
                    ArrayList familyAddOnId = new ArrayList();
                    mailPreference = memberRemote.getMailPreference(addOnMemId);
                    familyAddOnId = memberRemote.getFamilyAddOnId(addOnMemId);
                    
                    nonhlc=remote.getMemberNonUseaDetails(addOnMemId);
                    
                    Debug.print("user Id from session : " +userIdforDate);
                    donSelect=remote.getMemberDonationDetails(userIdforDate);
                    
                    request.setAttribute("nonhlc",nonhlc);
                    request.setAttribute("donSelect",donSelect);
                    request.setAttribute("mailPreference",mailPreference);
                    request.setAttribute("familyAddOnId",familyAddOnId);
                    
                    HLCPaymentDetails objPayment = new HLCPaymentDetails();
                    objPayment=remote1.getPaymentDetails(objMember.getPaymentId());
                    request.setAttribute("objPayment",objPayment);
                    
                    Debug.print("objMember.getPaymentId() :"+objMember.getPaymentId());
                    System.out.println("objPayment :"+objPayment.toString());
                    
                    fwd="membView";
                    //}
                    
                    
                }
                
                        /*
                         * Redirect to frmMemberSearchList.jsp Page
                         */
                
                if(process.equalsIgnoreCase("update")){
                    Debug.print("Inside update of member list");
                    String uStatusId=request.getParameter("uStatusId");
                    String memberId1=request.getParameter("memberId");
                    Debug.print("uStatusId is "+uStatusId);
                    Debug.print("memberId1 is "+memberId1);
                    memberRemote2.editMemberStatus(uStatusId,memberId1);
                    
                    /// Update History Details
                    String payId = request.getParameter("payId");
                    String user_id = request.getParameter("userId");
                    if(payId!=null && user_id!=null){
                        String status_id = remote1.getStatusIBasedOnStatus(uStatusId);
                        Debug.print("statusId is "+status_id);
                        Debug.print("payId is "+payId);
                        Debug.print("userId is "+user_id);
                        boolean hist_update=false;
                        if(status_id!=null && !(status_id.equalsIgnoreCase("null")) && payId!=null && !(payId.equalsIgnoreCase("null")) &&
                                user_id!=null && !(user_id.equalsIgnoreCase("null"))){
                            hist_update = remote1.updateHistoryOnMemberSearch(status_id,payId,user_id);
                        }
                        Debug.print(" History Update is "+hist_update);
                    }
                    /// End Update Histroy Details
                    
                    
                    ArrayList familyAddOnId=new ArrayList();
                    familyAddOnId=(ArrayList)session.getAttribute("familyAddOnId");
                    
                    if(uStatusId.equalsIgnoreCase("Inactive")) {
                        if(familyAddOnId!=null) {
                            Debug.print("Inside familyAddOn Inactiveate block :" +familyAddOnId.size());
                            
                            Debug.print("familyAddOnId.size() :" +familyAddOnId.size());
                            
                            for(int i=0;i<familyAddOnId.size();i++) {
                                String memId[]=(String[])familyAddOnId.get(i);
                                
                                Debug.print("uStatusId is "+uStatusId);
                                Debug.print("memberId of familyAddOn is "+memId[0]);
                                boolean stat=memberRemote2.editMemberStatus(uStatusId,memId[0]);
                                Debug.print("memberRemote2.editMemberStatus(uStatusId,memId[0]) :"+stat);
                                
                            }
                            
                            session.setAttribute("familyAddOnId",null);
                        }
                    }
                    
                    String name3 = "ejb/HLCKaveryMembershipTypeJNDI";
                    //Context jndiContext1 = getInitialContext();
                    Object obj3=jndiContext.lookup(name3);
                    HLCKaveryMembershipTypeSessionRemoteHome memberHome = (HLCKaveryMembershipTypeSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(obj3,HLCKaveryMembershipTypeSessionRemoteHome.class);
                    HLCKaveryMembershipTypeSessionRemote memberRemote = memberHome.create();
                    
                    String usrid=request.getParameter("userId");
                    Debug.print("request.getParameter(userId) :"+usrid);
                    
                    String mailAddressStatus = request.getParameter("mailAddressStatus");
                    String splNote="";
                    if(request.getParameter("splNote")!=null && request.getParameter("splNote").trim().length() != 0){
                        splNote = request.getParameter("splNote");
                        System.out.println("splNote is assigned");
                    }
                    boolean AddressStatus = false;
                    if(mailAddressStatus!=null ){
                        System.out.println("inside mailAddressStatus!=null");
                        if(mailAddressStatus.equals("true")){
                            AddressStatus = true;
                            System.out.println("AddressStatus = true");
                        }
                    }
                    System.out.println("mailAddressStatus:"+mailAddressStatus);
                    boolean updateStatus = memberRemote.updateUserMemberFlag(usrid,splNote,AddressStatus);
                    if(updateStatus){
                        System.out.println("Inside servlet view Action updateUserMemberFlag Successfull"+updateStatus);
                    } else {
                        System.out.println("Inside Servlet Action updateUserMemberFlag failed");
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
                    Date approvalDate=null;
                    if(request.getParameter("approvalDate")!=null && request.getParameter("approvalDate").trim().length()!=0) {
                        approvalDate = (Date) sdf.parse(request.getParameter("approvalDate"));
                        Debug.print("approvalDate :"+approvalDate.toString());
                    }
                    
                    boolean appDateStatus = remote1.updateApprovalDate(memberId1,approvalDate);
                    if(appDateStatus){
                        System.out.println("Inside servlet view Action updateApprovalDate Successfull:"+appDateStatus);
                    }else {
                        System.out.println("Inside servlet view Action updateApprovalDate failed");
                    }
                    
                    fwd="frmMemberSearchList";
                }
                
                        /*
                         * Redirect to index.jsp Page
                         */
                
                if(process.equalsIgnoreCase("editMemb")) {
                    Debug.print("Inside editMemberDetails of member profile block .....");
                    String membId=request.getParameter("memberId");
                    
                    boolean nonUseaStat=remote1.deleteNonUseaPriceDetails(membId);
                    Debug.print("remote1.deleteNonUseaPriceDetails(membId) for member id :"+membId+" :" +nonUseaStat);
                    
                    boolean pubDelStat=remote1.deletePublicationDetails(membId);
                    Debug.print("remote1.deletePublicationDetails(membId) for member id :"+membId+" :" +pubDelStat);
                    
                    String[] pubId=request.getParameterValues("mailOpt1");
                    Debug.print("pubId :" +pubId);
                    
                    if(pubId!=null) {
                        Debug.print("pubId.length :" +pubId.length);
                        
                        for(int i=0;i<pubId.length;i++) {
                            
                            boolean pubStat=remote1.insertPublicationDetails(membId,pubId[i]);
                            Debug.print("remote.insertPublicationDetails() for member id :"+membId+" publication id : "+pubId[i]+" Status :" +pubStat);
                            
                        }
                    }
                    
                    
                            /*
                             * Non-Usea Amt insertion block
                             */
                    
                    
                    String orgCbxCt=request.getParameter("orgCbxCt");
                    int orgCt=Integer.parseInt(orgCbxCt);
                    Debug.print("orgCt :"+orgCt);
                    
                    ArrayList nonUseaDet=new ArrayList();
                    
                    for(int c=0;c<orgCt;c++) {
                        String cbxName="orgNameCbx"+c;
                        String tbName="orgNameTxt"+c;
                        System.out.print("tbName :"+tbName);
                        System.out.print("cbxName :"+cbxName);
                        
                        String chkVal=request.getParameter(cbxName);
                        Debug.print("chkVal :"+chkVal);
                        
                        
                        if(chkVal!=null) {
                            String[] OrgTypId=chkVal.split("#");
                            
                            String orgMemId=request.getParameter(tbName);
                            Debug.print("orgMemId :"+orgMemId);
                            
                            boolean nonstat=remote1.insertNonUseaPriceDetails(membId,OrgTypId[0],orgMemId);
                            Debug.print("non hlc "+c+"insert status :"+nonstat);
                            
                        }
                    }
                    
                    // Amature status
                    
                    String nameAmatMemb="";
                    
                    nameAmatMemb=request.getParameter("amatName");
                    Debug.print("nameAmatMemb :"+nameAmatMemb);
                    
                    String amatCrdPossMemb=request.getParameter("posAmat");
                    Debug.print("amatCrdPossMemb :"+amatCrdPossMemb);
                    
                    boolean amatCrdPossMembStatus;
                    
                    if(amatCrdPossMemb!=null) {
                        amatCrdPossMembStatus=true;
                        
                    } else {
                        amatCrdPossMembStatus=false;
                    }
                    Debug.print("amatCrdPossMembStatus :"+amatCrdPossMembStatus);
                    
                    String amatCrdEligMemb=request.getParameter("decAmat");
                    Debug.print("amatCrdEligMemb :"+amatCrdEligMemb);
                    
                    boolean amatCrdEligMembStatus;
                    
                    if(amatCrdEligMemb!=null) {
                        amatCrdEligMembStatus=true;
                        Debug.print("amatCrdEligMembStatus :"+amatCrdEligMembStatus);
                    } else {
                        amatCrdEligMembStatus=false;
                    }
                    Debug.print("amatCrdEligMembStatus :"+amatCrdEligMembStatus);
                    
                    boolean res=remote1.updateAmatureStatus(membId,nameAmatMemb,amatCrdPossMembStatus,amatCrdEligMembStatus);
                    Debug.print("remote1.updateAmatureStatus(membId,nameAmatMemb,amatCrdPossMembStatus,amatCrdEligMembStatus) :"+res);
                    
                    boolean output=remote1.updateAmateurStatusInHistoryDets(membId,nameAmatMemb,amatCrdPossMembStatus,amatCrdEligMembStatus);
                    Debug.print("remote1.updateAmateurStatusInHistoryDets(membId,nameAmatMemb,amatCrdPossMembStatus,amatCrdEligMembStatus) :"+output);
                    fwd="home";
                    
                }
                if(process.equalsIgnoreCase("listDonation")) {
                    ArrayList alldonations=new ArrayList();
                    if(userIdforDate!=null || userIdforDate.trim().length()!=0){
                        Debug.print("Inside DonationDetails user ID "+ userIdforDate);
                        alldonations=remote.getMemberDonationDetails(userIdforDate);
                        Debug.print("Size of the Donation Details "+alldonations.size());
                        request.setAttribute("allDonations",alldonations);
                    }
                    fwd="listalldonations";
                    
                }
            }
        }
        
        catch(Exception e) {
            System.out.println("client exception :" +e);
        }
        
        return(mapping.findForward(fwd));
        
    }
    
    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception{
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.icp");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();
        
        return remote;
    }
}
