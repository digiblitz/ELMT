/*
 * Program Name     :   RegHorseListingAction.java
 * Created Date     :   November 25, 2006, 3:44 PM
 * Author           :   Hari & punitha
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.15
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.*;
import com.hlccommon.util.HLCHorseRegisterationVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;


public class RegHorseListingAction extends Action {
    
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try {
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
            HttpSession session=request.getSession();
            
            String kaveryname = mr.getMessage("jndi.kaverymrm");
            Object obj = jndiContext.lookup(kaveryname);
            HLCkaveryStatelessRemoteHome kaveryhome = (HLCkaveryStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj, HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote kaveryremote = kaveryhome.create();
            
            
            //For Horse MemberType
            String jname=mr.getMessage("jndi.kavery");
            Object objref = jndiContext.lookup(jname);
            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote = home.create();
            
            //For User Name
            String jndiname1=mr.getMessage("jndi.kaverysless");
            Object objref1 = jndiContext.lookup(jndiname1);
            HLCkaverySessionBeanStatlessRemoteHome horsehome =(HLCkaverySessionBeanStatlessRemoteHome) PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote horseremote = horsehome.create();
            
            //For User Contact and member contact Details
            
// History Table Entry
            String jname1=mr.getMessage("jndi.usrreg");
            Object oef = jndiContext.lookup(jname1);
            HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(oef,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote userremote = userhome.create();
                      
            String userId=(String)session.getAttribute("userId");
            Debug.print("Inside Servlet");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            ArrayList hrseObj = new ArrayList();
            String process = request.getParameter("process");
            Debug.print("Process in Servlet " + process);
            
            //Status List Added By Suresh
            ArrayList statusList = remote.getAllStatusDetails();
            request.setAttribute("statusList",statusList);
            //===========================================
            // Setting Status and Horse Member Type
            // Suresh Here
            if(process==null) {
                int rCnt =0;
                int pNo =1;
                String stat = request.getParameter("statSelect");
                String memTyp = request.getParameter("selectTyp");
                String pageNo = request.getParameter("pn");
                
                if(pageNo!=null){
                    pNo = Integer.parseInt(pageNo);
                }
                
                request.setAttribute("statusId", stat);
                request.setAttribute("memTypeId", memTyp);
                Debug.print("Status is "+stat);
                Debug.print("memTyp is "+memTyp);
                request.setAttribute("stat",stat);
                if(stat!=null && stat.trim().length()!=0 && memTyp!=null && memTyp.trim().length()!=0){
                    hrseObj = remote.getAllHorseDetails(stat,memTyp,pNo);
                    rCnt = remote.horseRowCount(stat,memTyp);
                }
                Vector horsememtypVect = new Vector();
                horsememtypVect = horseremote.displayMembershipType("Horse");
                request.setAttribute("arrVal",hrseObj);
                request.setAttribute("horsememberVect", horsememtypVect);
                request.setAttribute("rCnt", String.valueOf(rCnt));
                request.setAttribute("pNo", String.valueOf(pNo));
                Debug.print("Sucessfully comes from Admin List");
                return mapping.findForward("frmHorseMemberList");
            }
            
              //----------------------------------------------------satheesh Start MMMA_0010: Human/Non-Human relationships  -----------------------------------------------------------

            else if(process.equalsIgnoreCase("requestFrm")) {

                String uTypeId=request.getParameter("uTypeId");
                    System.out.println("nnn"+uTypeId);
                    System.out.print("enter the initBreedViewcontrol********************");
                    
                    Vector vDisUserType = remote.displayUserTypeDetails();
                    
                    System.out.print("After calling displayUserTypeDetails  ");
                    int i=0;
                    while(i<vDisUserType.size())
                    {
                        String[] val = (String[])vDisUserType.get(i);
                        String ID = val[0];
                        String name1 = val[1];
                        Debug.print("Value [0] is "+ID);
                        Debug.print("Value [1] is "+name1);
                        i++;
                    }
                                     session.setAttribute("displayUserTypeDetails" ,null);
                                     session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                                     return mapping.findForward("admin-horse-request-list");
            } 
              //----------------------------------------------------satheesh  MMMA_0010: Human/Non-Human relationships  -----------------------------------------------------------

            
            //Approve Horse Details by Admin
            else if(process!=null && process.trim().length()>0) {
                if(process.equals("Approve")){
                    Debug.print("Inside Approve Page");
                    String memId = request.getParameter("memid");
                    Debug.print("   MemberId" + memId);
                    HLCHorseRegisterationVO HorseDet = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        request.setAttribute("HorseDet",HorseDet);
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                        request.setAttribute("paydet",paydet);
                        request.setAttribute("ListVO",HorseDisp);
                        if(paymentId!=null && paymentId.trim().length()!=0){
                            ArrayList childPayment = remote.getHorseChildPaymentDetailsByAdmin(paymentId);
                            request.setAttribute("childPayment",childPayment);
                        }
                        return mapping.findForward("ApproveDetails");
                    }
                    
                    else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                
                else if(process.equalsIgnoreCase("userViewDet")){
                    Debug.print("Inside User Describing Page");
                    String memId = request.getParameter("memid");
                    Debug.print("   MemberId" + memId);
                    HLCHorseRegisterationVO HorseDet = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        
                        //End Setting Info
                        request.setAttribute("HorseDet",HorseDet);
                        
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                        request.setAttribute("paydet",paydet);
                        request.setAttribute("ListVO",HorseDisp);
                        Debug.print("Size is "+HorseDisp.size());
                        request.setAttribute("err","");
                        request.setAttribute("memId",memId);
                        return mapping.findForward("UserViewMemberList");
                    } else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                
                
                else if(process.equalsIgnoreCase("userdesc")){
                    Debug.print("Inside User Describing Page");
                    String memId = request.getParameter("memid");
                    Debug.print("   MemberId" + memId);
                    HLCHorseRegisterationVO HorseDet = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String addownerId = HorseDet.getAddOwnerId();
                        String prevownerId = HorseDet.getPrevOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String prevRiderId = HorseDet.getPrevRiderMemberId();
                        String addRiderId = HorseDet.getAddRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        //End Setting Info
                        request.setAttribute("HorseDet",HorseDet);
                        
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                        request.setAttribute("paydet",paydet);
                        request.setAttribute("ListVO",HorseDisp);
                        Debug.print("Size is "+HorseDisp.size());
                        
                        request.setAttribute("err","");
                        request.setAttribute("memId",memId);
                        return mapping.findForward("PermissionDenied");
                        
                    } else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                
                else if(process.equalsIgnoreCase("chngdesc")){
                    Debug.print("Inside User Change Description Page");
                    String memId = request.getParameter("memid");
                    Debug.print("   MemberId" + memId);
                    HLCHorseRegisterationVO HorseDet = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String addownerId = HorseDet.getAddOwnerId();
                        String prevownerId = HorseDet.getPrevOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String prevRiderId = HorseDet.getPrevRiderMemberId();
                        String addRiderId = HorseDet.getAddRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        //End Setting Info
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                        float balamt = remote.getBalanceAmount(paymentId);
                        Debug.print("Balance Amount for that value is:" + balamt);
                        request.setAttribute("BalAmount",String.valueOf(balamt));
                        request.setAttribute("paydet",paydet);
                        request.setAttribute("ListVO",HorseDisp);
                        request.setAttribute("HorseDet",HorseDet);
                        request.setAttribute("err","");
                        
                        boolean pri_owner = false;
                        pri_owner = remote.isHorsePriOwnerExist(memId,userId);
                        Debug.print("Primary Owner is Avilable "+pri_owner);
                        if(pri_owner == true){
                            request.setAttribute("primaryOwner","primaryOwner");
                        }
                        return mapping.findForward("UserDegradeMemberList");
                        
                    } else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                
                
                else if(process.equals("desc")){
                    Debug.print("Inside Describing Page");
                    String memId = request.getParameter("memid");
                    Debug.print("   MemberId" + memId);
                    HLCHorseRegisterationVO HorseDet = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDet = (HLCHorseRegisterationVO)remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        // Owner Details Info
                        
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        String[] paydet = (String[])remote.getHorsePaymentDetails(paymentId);
                        request.setAttribute("paydet",paydet);
                        request.setAttribute("ListVO",HorseDisp);
                        request.setAttribute("HorseDet",HorseDet);
                        
                        if(paymentId!=null && paymentId.trim().length()!=0){
                            ArrayList childPayment = remote.getHorseChildPaymentDetailsByAdmin(paymentId);
                            request.setAttribute("childPayment",childPayment);
                        }
                        return mapping.findForward("ViewMemberList");
                        
                    } else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                } else if(process.equals("processDesc")){
                    Debug.print("Inside process Describing Page::::::::::::");
                    String memId = request.getParameter("memid");
                    Debug.print("MemberId:::" + memId);
                    
                    
                    if(memId!=null && memId.trim().length()!=0){
                        
                        HLCHorseRegisterationVO HorseDetails = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        
                        Debug.print("Horse Details" + HorseDetails);
                        
                        String ownerId = HorseDetails.getOwnerId();
                        String riderId = HorseDetails.getRiderMemberId();
                        String paymentId = HorseDetails.getPaymentId();
                        
                        Debug.print("User Details riderId ID "+ riderId);
                        Debug.print("User Details owner ID "+ ownerId);
                        Debug.print("User Details paymentId ID "+ paymentId);
                        
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                        float balamt = remote.getBalanceAmount(paymentId);
                        Debug.print("Balance Amount for that value is:" + balamt);
                        request.setAttribute("paydet",paydet);
                        request.setAttribute("ListVO",HorseDisp);
                        request.setAttribute("HorseDet",HorseDetails);
                        
                        if(paymentId!=null && paymentId.trim().length()!=0){
                            ArrayList childPayment = remote.getHorseChildPaymentDetailsByAdmin(paymentId);
                            request.setAttribute("childPayment",childPayment);
                        }
                        return mapping.findForward("HorseRidOwnChngReq");
                        
                    } else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                //Owner Description Details
                else if(process.equals("ownerdesc")){
                    String ownerId = request.getParameter("ownerId");
                    Debug.print("User Details owner ID "+ownerId);
                    ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                    request.setAttribute("ownerDetails",ownerDetails);
                    return mapping.findForward("DispOwnerDetails");
                }
                
                //Rider Description Details
                else if(process.equals("riderDesc")){
                    Debug.print("Rider Description Detail");
                    String riderId1 = request.getParameter("riderid");
                    ArrayList riderId = (ArrayList)userremote.getMemberContactDetails(riderId1);
                    request.setAttribute("ownerDetails",riderId);
                    return mapping.findForward("DispRiderDetails");
                }
                
                //Updating Staus of the Horse
                else if(process.equals("updatestatus")){
                    Debug.print("Inside updateStatus");
                    String modifiedBy = (String) session.getAttribute("userId");
                    Debug.print("modifiedBy in updateStatus"+modifiedBy);
                    Date todayDt = new Date();
                    Debug.print("Current Date in updateStatus"+todayDt);
                    String status =  request.getParameter("statSelect");
                    String comments = request.getParameter("commentarea");
                    String horseMemberId = request.getParameter("horseMemId");
                    String membertype = request.getParameter("membertype");
                    String horseName = request.getParameter("horseName");
                    
                    String dateVal = request.getParameter("activeDatVal");
                    Debug.print("dateVal in updateStatus"+dateVal);
                    
                    Date actDateAsFrm =sdf.parse(dateVal);
                    Debug.print("actDateAsFrm in updateStatus"+actDateAsFrm);       
                    String statusId="";
                    String statusName="";
                    String statusVal[]={};
                    if(status!=null){
                        statusVal =  status.split("@");
                        statusId = statusVal[0];
                        statusName = statusVal[1];
                    }
                    System.out.print("statusId" + statusId);
                    System.out.print("statusName" + statusName);
                    
                    
                    /// update status on history table
                    String paymentId = request.getParameter("paymentIdVal");
                    
                    if(paymentId!=null && horseMemberId!=null && statusId!=null){
                        boolean histryupdate = userremote.updateHorseMemberHistoryDetailStatus(horseMemberId,statusId,paymentId,actDateAsFrm);
                        Debug.print("histryupdate is "+histryupdate);
                    }
                    /// End update status on history table
                    Date activeDatVal = null;
                    boolean bol = false;
                    boolean activeStatus = false;
                    if(dateVal!=null){
                        activeDatVal = sdf.parse(dateVal);
                    }
                    Debug.print("Status "+status);
                    Debug.print("horseMemberId "+horseMemberId);
                    if(horseMemberId!=null){
                        bol =  remote.approveHorseStatusByAdmin(horseMemberId,statusId,comments,activeDatVal,modifiedBy,todayDt);
                        if(statusName!=null && statusName.equalsIgnoreCase("Active")){
                            activeStatus = remote.updateRealtionshipStatus(horseMemberId);
                        }
                        Debug.print("REsult of update  is "+bol);
                    }
                    
                    Vector horsememtypVect = new Vector();
                    horsememtypVect = horseremote.displayMembershipType("Horse");
                    
                    Debug.print("horsememberVect Vector is \n"+horsememtypVect);
                    Debug.print("Size of arrayList is "+hrseObj.size());
                    Debug.print("ArrayList value is "+hrseObj.toString());
                    request.setAttribute("arrVal",hrseObj);
                    request.setAttribute("horsememberVect", horsememtypVect);
                    
                    Debug.print("activeStatus is "+activeStatus);
                    request.setAttribute("stat","Select");
                    request.setAttribute("horseMemberId",horseMemberId);
                    
                    
                    
                    if(bol==true){
                        
                        String OwnerFName="";
                        String OwnerLName="";
                        String OwnerMailId="";
                        
                        ArrayList objOwnerDet = new ArrayList();
                        objOwnerDet = remote.getOwnerDetails(horseMemberId);
                        if(objOwnerDet!=null && objOwnerDet.size()!=0){
                            Debug.print("objOwnerDet::: ***** " + objOwnerDet);
                            Iterator itr1 = objOwnerDet.iterator();
                            while(itr1.hasNext()) {
                                HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                                OwnerFName=objOwnerInfo1.getOwnerFirstName();
                                OwnerLName=objOwnerInfo1.getOwnerLastName();
                                OwnerMailId=objOwnerInfo1.getOwnerEmail();
                                Debug.print("OwnerMailId in upgrade horse section "+OwnerMailId);
                                
                            }
                        }
                        if(statusName!=null && (statusName.equalsIgnoreCase("Retired")) || (statusName.equalsIgnoreCase("Deceased"))){
                            String toMailIds[] = {OwnerMailId};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("anandv@digiblitz.com");
                            email.setSubject("Horse Status Updation");
                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                    " <tr>" +
                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                    "<tr>" +
                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                                    " </tr>"+
                                    "  <tr>"+
                                    "<td valign=\"top\">"+
                                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                                    "<tr>"+
                                    "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                    "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                    "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                    "</tr>"+
                                    "<tr>"+
                                    "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                                    "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                                    "<span class=\"boldTxt\">Dear Member,</span><br />"+
                                    "<p>Thank you for updating the status for your horse "+horseName+".<p>"+
                                    "<p>"+horseName+" "+horseMemberId+" is now marked as "+statusName+"<p>"+
                                    "<p>Additional information regarding "+ horseName +" can be viewed by logging in to the HLC Online Services. Thank you.<p>"+
                                    "<p>Sincerely, <br />" +
                                    "<p>HLC Staff <p>"+
                                    "</tr>"+
                                    " </table>"+
                                    "</td></tr>"+
                                    "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :"+emailFlag);
                        }
                    }
                    
                    String adminSearch =(String)session.getAttribute("adminSearch");
                    if(adminSearch!=null && adminSearch.trim().length()!=0){
                        return mapping.findForward("AdminHorseSearch");
                    } else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                //User List the information of horse page
                //Suresh Here
                else if( process.equals("UserListing")){
                    session.removeAttribute("forEE");
                    int rCnt =0;
                    int pNo =1;
                    
                    String pageNo = request.getParameter("pn");
                    
                    if(pageNo!=null){
                        pNo = Integer.parseInt(pageNo);
                    }
                    ArrayList userInfo = null;
                    if(userId!=null && userId.trim().length()!=0){
                        userInfo = remote.getAllHorseDetailsByOwnerId(userId,pNo);
                        rCnt = remote.horseUserRowCount(userId);
                    }
                    request.setAttribute("userInfo",userInfo);
                    request.setAttribute("rCnt", String.valueOf(rCnt));
                    request.setAttribute("pNo", String.valueOf(pNo));
                    return mapping.findForward("UserDetails");
                }
                //Failed Payment Listing
                else if( process.equals("UserFailedPaymntList")){
                    
                    ArrayList pendingInfo = null;
                    if(userId!=null && userId.trim().length()!=0){
                        pendingInfo = remote.getAllPendingPaymentsForHorse(userId);
                    }
                    request.setAttribute("pendingInfo",pendingInfo);
                    return mapping.findForward("UserPayFailDetails");
                } else if(process.equals("PayProceed")){
                    Debug.print("Inside pay proceed");
                    String memId = (String) request.getParameter("memid");
                    Debug.print("Horse Member ID "+memId);
                    HLCHorseRegisterationVO HorseDisp = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                    request.setAttribute("ListVO",HorseDisp);
                    String paymentId = (String) HorseDisp.getPaymentId();
                    String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                    request.setAttribute("paydet",paydet);
                    return mapping.findForward("ToPayFailedTransac");
                } else if(process.equals("DuePayProceed")){
                    Debug.print("Inside Due Pay proceed");
                    String memId = (String) request.getParameter("memid");
                    Debug.print("Horse Member ID "+memId);
                    HLCHorseRegisterationVO HorseDisp = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                    request.setAttribute("ListVO",HorseDisp);
                    String paymentId = (String) request.getParameter("paymentId");
                    String[] paydet = null;
                    if(paymentId!=null){
                        paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                    }
                    request.setAttribute("paydet",paydet);
                    return mapping.findForward("ToDuePayFailedTransac");
                } else if(process.equals("AddChngPayProceed")){
                    Debug.print("Inside Add Chng Pay proceed");
                    String memId = (String) request.getParameter("memid");
                    Debug.print("Horse Member ID "+memId);
                    HLCHorseRegisterationVO HorseDisp = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                    request.setAttribute("ListVO",HorseDisp);
                    String paymentId = (String) request.getParameter("paymentId");
                    String[] paydet = null;
                    if(paymentId!=null){
                        paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                    }
                    request.setAttribute("paydet",paydet);
                    return mapping.findForward("ToPayAddChngTransac");
                } else if(process.equals("AddChngDuePayProceed")){
                    Debug.print("Inside Add Change Due Pay proceed");
                    String memId = request.getParameter("memid");
                    Debug.print("Horse Member ID "+memId);
                    HLCHorseRegisterationVO HorseDisp = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                    request.setAttribute("ListVO",HorseDisp);
                    String paymentId = request.getParameter("paymentId");
                    String[] paydet = null;
                    if(paymentId!=null){
                        paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                    }
                    String requestId = request.getParameter("requestId");
                    System.out.print("requestId" + requestId);
                    String horseRidOwnStatus = request.getParameter("horseRidOwnStatus");
                    request.setAttribute("requestId",requestId);
                    request.setAttribute("paydet",paydet);
                    request.setAttribute("horseRidOwnStatus",horseRidOwnStatus);
                    return mapping.findForward("ToAddChngDuePayFailTransac");
                }
                //Upgrade the Details of the horse
                else if(process.equals("UpgradeDet")){
                    Debug.print("Entered Upgrade Page");
                    String memId = request.getParameter("memid");
                    String butName = request.getParameter("upgrade");
                    
                    Debug.print("   butName" + butName);
                    HLCHorseRegisterationVO HorseDisp = null;
                    HLCHorseRegisterationVO OwnerHorseDisp = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDisp = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDisp.getOwnerId();
                        String addownerId = HorseDisp.getAddOwnerId();
                        String prevownerId = HorseDisp.getPrevOwnerId();
                        String riderId = HorseDisp.getRiderMemberId();
                        String prevRiderId = HorseDisp.getPrevRiderMemberId();
                        String addRiderId = HorseDisp.getAddRiderMemberId();
                        
                        Debug.print("UpgradeDet User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        
                        request.setAttribute("ListVO",HorseDisp);
                        
                        Vector horsememtypVect = new Vector();
                        horsememtypVect = horseremote.displayMembershipType("Horse");
                        String horsesertyp[] = (String[])remote.getHorseServiceCharge();
                        String userTypeId = (String)session.getAttribute("userTypeId");
                        Debug.print("userTypeId in Upgrade is "+userTypeId);
                        String userName = remote.getUserTypeName(userTypeId);
                        Debug.print("userTypeId:" + userName);
                        Debug.print("horsesertypVect Vector is \n"+horsesertyp);
                        request.setAttribute("userName",userName);
                        request.setAttribute("horsememberVect", horsememtypVect);
                        request.setAttribute("horsesertypVect", horsesertyp);
                        return mapping.findForward("UpgradeDetails");
                    }
                    
                    else{
                        return mapping.findForward("frmHorseMemberList");
                    }
                }
                
                //Rider Description Details
                else if(process.equals("riderListing")) {
                    Debug.print("RegHorseListing.riderListing() calling...");
                    String memberId=(String)session.getAttribute("memberId");
                    Debug.print(" RegHorseListing.riderListing():  memberId:"+ memberId);
                    ArrayList riderHorseList = null;
                    if(memberId!=null && memberId.trim().length()!=0){
                        riderHorseList = remote.getAllHorseDetailsByRiderId(memberId);
                    }
                    Debug.print("Rider Horse Listing Size():" + riderHorseList.size());
                    request.setAttribute("riderHorseList",riderHorseList);
                    return mapping.findForward("frmMrmRiderLisitng");
                } else if(process.equals("relationshipListing")){
                    Debug.print("RegHorseLisitng.relationshipListing() calling......");
                    String relationId = (String)request.getParameter("relationId");
                    String horseMemberId =(String)request.getParameter("horseMemberId");
                    String [] str=null;
                    ArrayList relation ;
                    ArrayList relationshipDetails=null;
                    String[] strHorseDet=null;
                    String[] payDetails = null;
                    if(relationId != null && relationId.trim().length()!=0){
                        str = (String []) remote.getHorseRelationshipDetailsByRelationId(relationId);
                    }
                    String reqId=str[8];
                    if(str.length!=0 && reqId!=null){
                        
                        relationshipDetails = (ArrayList)remote.getHorseRelationshipDetailsByRequestId(reqId);
                        strHorseDet = (String [])remote.getHorseAddRequestByReqId(reqId);
                        if(strHorseDet!=null && strHorseDet.length!=0 && strHorseDet[1]!=null){
                            String paymentId= strHorseDet[1];
                            if(paymentId!=null && paymentId.trim().length()!=0){
                                payDetails = (String [])remote.getHorsePaymentDetails(paymentId);
                                ArrayList childPayment = remote.getHorseChildPaymentDetailsByAdmin(paymentId);
                                request.setAttribute("childPayment",childPayment);
                            }
                        }
                    }
                    relation= (ArrayList) remote.getAllHorseRelationMaster();
                    request.setAttribute("relationshipDetails",relationshipDetails);
                    request.setAttribute("strHorseDet",strHorseDet);
                    request.setAttribute("payDetails",payDetails);
                    request.setAttribute("horseRelationDetails",str);
                    request.setAttribute("horseRelation",relation);
                    request.setAttribute("relationId",relationId);
                    request.setAttribute("horseMemberId",horseMemberId);
                    
                    return mapping.findForward("ViewHorseRelationDetails");
                }
                
                else if(process.equals("viewRelationshipListing")){
                    Debug.print("RegHorseLisitng.viewRelationshipListing() calling......");
                    String relationId = (String)request.getParameter("relationId");
                    String horseMemberId =(String)request.getParameter("horseMemberId");
                    String [] str=null;
                    ArrayList relation ;
                    ArrayList relationshipDetails=null;
                    String[] strHorseDet=null;
                    String[] payDetails = null;
                    if(relationId != null && relationId.trim().length()!=0){
                        str = (String []) remote.getHorseRelationshipDetailsByRelationId(relationId);
                    }
                    String reqId = str[8];
                    if(str!=null && str.length!=0 && reqId!=null && reqId.length()!=0){
                        Debug.print("reqId:" + reqId);
                        relationshipDetails = (ArrayList)remote.getHorseRelationshipDetailsByRequestId(reqId);
                        strHorseDet = (String [])remote.getHorseAddRequestByReqId(reqId);
                        if(strHorseDet!=null && strHorseDet.length!=0 && strHorseDet[1]!=null){
                            String paymentId= strHorseDet[1];
                            if(paymentId!=null && paymentId.trim().length()!=0){
                                payDetails = (String [])remote.getHorsePaymentDetails(paymentId);
                                ArrayList childPayment = remote.getHorseChildPaymentDetailsByAdmin(paymentId);
                                request.setAttribute("payDetails",payDetails);
                                request.setAttribute("childPayment",childPayment);
                            }
                        }
                    }
                    relation= (ArrayList) remote.getAllHorseRelationMaster();
                    request.setAttribute("relationshipDetails",relationshipDetails);
                    request.setAttribute("strHorseDet",strHorseDet);
                    
                    request.setAttribute("horseRelationDetails",str);
                    request.setAttribute("horseRelation",relation);
                    request.setAttribute("relationId",relationId);
                    request.setAttribute("horseMemberId",horseMemberId);
                    
                    return mapping.findForward("changeRiderOwnStatus");
                }
                
                else if(process.equals("relationUpdate")){
                    Debug.print("RegHorseListing.relationUpdate() calling...");
                    String relationId = "";
                    String relationshipTypeId="";
                    String relationshipStatusId="";
                    String horseMemberId = (String) request.getParameter("horseMemberId");
                    relationId = (String)request.getParameter("relationId");
                    relationshipTypeId = (String)request.getParameter("statSelect");
                    relationshipStatusId = (String)request.getParameter("relstatSelect");
                    boolean flag= false;
                    if(relationId!= null && relationshipTypeId!=null && relationshipStatusId!=null){
                        flag = remote.updateOwnerRiderActivation(relationId,relationshipTypeId,relationshipStatusId);
                    }
                    if(flag){
                        Debug.print("Successfully updated the relation status in User Master");
                    } else{
                        Debug.print("Error in updation of the relation status in User Master");
                    }
                    request.setAttribute("horseMemberId",horseMemberId);
                    
                    return mapping.findForward("successApprovePage");
                    
                }
                
                else if(process.equals("relationChange")){
                    Debug.print("RegHorseListing.relationChange() calling...");
                    String relationId = "";
                    String relationshipTypeId="";
                    String relationshipStatusId="";
                    String horseMemberId = (String) request.getParameter("horseMemberId");
                    relationId = (String)request.getParameter("relationId");
                    relationshipTypeId = (String)request.getParameter("statSelect");
                    relationshipStatusId = (String)request.getParameter("relstatSelect");
                    boolean flag= false;
                    if(relationId!= null && relationshipTypeId!=null && relationshipStatusId!=null){
                        flag = remote.updateOwnerRiderActivation(relationId,relationshipTypeId,relationshipStatusId);
                    }
                    if(flag){
                        Debug.print("Successfully updated the relation status in User Master");
                    } else{
                        Debug.print("Error in updation of the relation status in User Master");
                    }
                    request.setAttribute("horseMemberId",horseMemberId);
                    
                    return mapping.findForward("RegHorseApproveOwnRidSuccess");
                    
                }
                
                //----------------------------------------------------Punitha Start-----------------------------------------------------------
                else if(process.equalsIgnoreCase("horseDet")){
                    Debug.print("Inside Horse Search Detail page...........................");
                    String memId = request.getParameter("memid");
                    Debug.print("MemberId" + memId);
                    if(memId!=null && memId.trim().length()!=0){
                        HLCHorseRegisterationVO HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        boolean pri_owner = false;
                        pri_owner = remote.isHorsePriOwnerExist(memId,userId);
                        Debug.print("Primary Owner is Avilable "+ pri_owner);
                        if(pri_owner == true){
                            request.setAttribute("primaryOwner","primaryOwner");
                        }
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        
                        //End Setting Info
                        request.setAttribute("HorseDet",HorseDet);
                        
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        request.setAttribute("ListVO",HorseDisp);
                        Debug.print("Size is "+HorseDisp.size());
                        request.setAttribute("memId",memId);
                        return mapping.findForward("userHorseSearchDetail");
                    } else{
                        return null;
                    }
                }
                
// Admin Transaction Display
                else if(process.equalsIgnoreCase("adminTransacDet")){
                    
                    /// Transaction
                    String mahanadhiJndi=mr.getMessage("jndi.acc");
                    Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
                    Object maha=jndiContext.lookup(mahanadhiJndi);
                    HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                    HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
///  Payment Details
                    String jndiname2=mr.getMessage("jndi.usrreg");
                    Object objref2 = jndiContext.lookup(jndiname2);
                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create();
                    
                    String payId = request.getParameter("pid");
                    ArrayList transacEntries = null;
                    ArrayList subTransac = null;
                    ArrayList nxtTransac = null;
                    
                    if(payId!=null || payId.trim().length()!=0){
                        transacEntries = mahaRemote.getAccTxnDetailsOnPaymentId(payId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(payId);
                        nxtTransac = remote1.getAdminSubPaymentDetails(payId);
                        request.setAttribute("payDet",paydet);
                    }
                    
                    request.setAttribute("transacEntries",transacEntries);
                    request.setAttribute("subTransac",nxtTransac);
                    
                    return mapping.findForward("adminTransNtry");
                }
                
// Admin Transaction Update
                else if(process.equalsIgnoreCase("adminHorseDetUpdate")){
                    
                    /// Transaction
                    String mahanadhiJndi=mr.getMessage("jndi.acc");
                    Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
                    Object maha=jndiContext.lookup(mahanadhiJndi);
                    HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                    HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
                    boolean transacConf =false;
                    String listSz =  request.getParameter("listSz");
                    Debug.print("listSz is "+listSz);
                    if(listSz!=null || listSz.trim().length()!=0){
                        int Sz = Integer.parseInt(listSz);
                        boolean status = true;
                        boolean voidStatus = false;
                        
///Setting Active status TRUE / FALSE
                        for(int i=0;i<Sz;i++){
                            String payType = request.getParameter("payType"+i);
                            Debug.print("Pay Type is "+payType);
                            
                            if(payType==null || payType.trim().length()==0){
                                System.out.println("Null Made False");
                                status = false;
                                break;
                            }
                            
                            else{
                                if(payType!=null || payType.trim().length()!=0){
                                    if(payType.equalsIgnoreCase("Partial Payment") || payType==null){
                                        status = false;
                                        Debug.print("Status set as false ");
                                        break;
                                    } else{
                                        Debug.print("Status set as true");
                                        status = true;
                                    }
                                }
                            }
                        }
                        
                        
///Update the entries to table
                        for(int i=0;i<Sz;i++){
                            
                            String amtName = request.getParameter("amtName"+i);
                            String payType = request.getParameter("payType"+i);
                            String ccType = request.getParameter("ccType"+i);
                            String transId = request.getParameter("transId"+i);
                            String actualAmount = request.getParameter("actualAmount"+i);
                            Debug.print("Actual Amount is  "+actualAmount);
                            HLCAccTransactionVO axnVO = new HLCAccTransactionVO();
                            
                            //Reconclie Amount
                            if(amtName!=null){
                                if(amtName.trim().length()!=0){
                                    axnVO.setReconcile_amount(Float.parseFloat(amtName));
                                }
                            } else{
                                axnVO.setReconcile_amount(0);
                            }
                            
                            //Set Transaction Mode
                            if(payType!=null || payType.trim().length()!=0){
                                axnVO.setTransaction_mode(payType);
                            }
                            
                            if(ccType!=null || ccType.trim().length()!=0){
                                axnVO.setPayment_mode(ccType);
                            }
                            
                            // Set Active Status False if PAYMENT VOID
                            if(status==true){
                                if(payType.equalsIgnoreCase("void")){
                                    axnVO.setReconcile_status(false);
                                } else{
                                    axnVO.setReconcile_status(true);
                                }
                            } else{
                                axnVO.setReconcile_status(false);
                            }
                            
                            // Set Reconile Status True if FULL PAYMENT
                            axnVO.setActive_status(status);
                            
                            
                            // Set Transaction ID
                            axnVO.setTransaction_id(transId);
                            
                            //Update to database
                            if(axnVO.getReconcile_amount()>0){
                                transacConf = mahaRemote.updateRecouncilAccTxnDetails(axnVO);
                                Debug.print("Transaction Confirmation  "+transacConf);
                            }
                            
                            if(actualAmount!=null || actualAmount.trim().length()!=0){
                                Debug.print("actVal is  "+actualAmount);
                                double actVal = Double.parseDouble(actualAmount);
                                if(actVal<0){
                                    axnVO.setReconcile_amount(Float.parseFloat(actualAmount));
                                    Debug.print("Inisde update"+actualAmount);
                                    transacConf = mahaRemote.updateRecouncilAccTxnDetails(axnVO);
                                    Debug.print("Transaction Confirmation  "+transacConf);
                                }
                            }
                        }
                        
///Setting Active status TRUE / FALSE
                        
                        boolean subPayStatus =false;
                        boolean subVoidstatus = false;
                        for(int i=0;i<Sz;i++){
                            
                            String payType = request.getParameter("divTransMode"+i);
                            Debug.print("Pay Type is "+payType);
                            
                            String divX = request.getParameter("div"+i);
                            
                            if(divX!=null){
                                if(payType==null || payType.trim().length()==0){
                                    System.out.println("Null Made False in sub payment");
                                    status = false;
                                    break;
                                } else{
                                    if(payType!=null || payType.trim().length()!=0){
                                        if(payType.equalsIgnoreCase("Partial Payment") || payType==null){
                                            subPayStatus = false;
                                            Debug.print("Status set as false ");
                                            break;
                                        } else{
                                            Debug.print("Status set as true");
                                            subPayStatus = true;
                                        }
                                    }
                                }
                            }
                        }
                        
                        if(status==false){
                            subPayStatus = false;
                        } else{
                            subPayStatus = true;
                        }
                        
                        //Sub Payment Entries
                        for(int j=1;j<=Sz;j++){
                            String divX = request.getParameter("div"+j);
                            Debug.print("Inside Sub divX "+divX + " : j =" + j);
                            if(divX!=null){
                                Debug.print("Inside Sub Payment divX is "+divX);
                                HLCAccTransactionVO axnVO = new HLCAccTransactionVO();
                                HLCAccTransactionVO dbVO = new HLCAccTransactionVO();
                                String accPayId = null;
                                
                                String itemNme = request.getParameter("divNewItem"+j);
                                String newAmt = request.getParameter("newAmt"+j);
                                String transMode = request.getParameter("divTransMode"+j);
                                String payMode = request.getParameter("payMode"+j);
                                String divtransId = request.getParameter("divTransId"+j);
                                
                                //Set Transaction Mode
                                if(transMode!=null || transMode.trim().length()!=0){
                                    axnVO.setTransaction_mode(transMode);
                                }
                                
                                if(payMode!=null || payMode.trim().length()!=0){
                                    axnVO.setPayment_mode(payMode);
                                }
                                
                                //Reconclie Amount
                                if(newAmt!=null){
                                    if(newAmt.trim().length()!=0){
                                        axnVO.setReconcile_amount(Float.parseFloat(newAmt));
                                    }
                                } else{
                                    axnVO.setReconcile_amount(0);
                                }
                                
                                if(divtransId!=null || divtransId.trim().length()!=0){
                                    dbVO = mahaRemote.getAccTransctionDetails(divtransId);
                                    
                                    String accNo = dbVO.getAccount_no();
                                    String accTyp = dbVO.getAccount_type();
                                    String accDesc = dbVO.getDescription();
                                    String accItemNo = dbVO.getItem_no();
                                    String subAccNo = dbVO.getSub_account_no();
                                    String classType = dbVO.getClass_Typ();
                                    accPayId = dbVO.getPayment_id();
                                    
                                    axnVO.setAccount_no(accNo);
                                    axnVO.setAccount_type(accTyp);
                                    axnVO.setDescription(accDesc);
                                    axnVO.setPayment_id(accPayId);
                                    axnVO.setSub_account_no(subAccNo);
                                    axnVO.setItem_no(accItemNo);
                                    axnVO.setClass_Typ(classType);
                                    
                                }
                                
                                // Set Sub Active Status False if PAYMENT VOID
                                if(subPayStatus==true){
                                    if(transMode.equalsIgnoreCase("void")){
                                        axnVO.setReconcile_status(false);
                                    } else{
                                        axnVO.setReconcile_status(true);
                                    }
                                } else{
                                    axnVO.setReconcile_status(false);
                                }
                                // Set Sub Reconile Status True if FULL PAYMENT
                                axnVO.setActive_status(subPayStatus);
                                
                                // Set Transaction ID
                                axnVO.setParentTransactionId(divtransId);
                                
                                if(axnVO.getReconcile_amount()>0){
                                    boolean insrtStatus = mahaRemote.insertAccountTxnDetails(axnVO);
                                    Debug.print("Sub Payment Isert Status is "+insrtStatus);
                                }
                            }
                        }
                    }
                    Debug.print("Before");
                    Vector horsememtypVect = new Vector();
                    horsememtypVect = horseremote.displayMembershipType("Horse");
                    request.setAttribute("horsememberVect", horsememtypVect);
                    Debug.print("After");
                    return mapping.findForward("frmHorseMemberList");
                }
                
//Change/Add Horse Rider Ownner Request
// Admin Transaction Display
                else if(process.equalsIgnoreCase("adminChangeTransacDet")){
                    
                    /// Transaction
                    String mahanadhiJndi=mr.getMessage("jndi.acc");
                    Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
                    Object maha=jndiContext.lookup(mahanadhiJndi);
                    HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                    HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
///  Payment Details
                    String jndiname2=mr.getMessage("jndi.usrreg");
                    Object objref2 = jndiContext.lookup(jndiname2);
                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create();
                    
                    String payId = request.getParameter("pid");
                    ArrayList transacEntries = null;
                    ArrayList subTransac = null;
                    ArrayList nxtTransac = null;
                    
                    if(payId!=null || payId.trim().length()!=0){
                        transacEntries = mahaRemote.getAccTxnDetailsOnPaymentId(payId);
                        String[] paydet = (String[]) remote.getHorsePaymentDetails(payId);
                        nxtTransac = remote1.getAdminSubPaymentDetails(payId);
                        request.setAttribute("payDet",paydet);
                    }
                    
                    request.setAttribute("transacEntries",transacEntries);
                    request.setAttribute("subTransac",nxtTransac);
                    
                    return mapping.findForward("adminchngeTransNtry");
                }
                
// Admin Transaction Update
                else if(process.equalsIgnoreCase("adminChngHorseDetUpdate")){
                    
                    /// Transaction
                    String mahanadhiJndi=mr.getMessage("jndi.acc");
                    Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
                    Object maha=jndiContext.lookup(mahanadhiJndi);
                    HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                    HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
                    boolean transacConf =false;
                    String listSz =  request.getParameter("listSz");
                    Debug.print("listSz is "+listSz);
                    if(listSz!=null || listSz.trim().length()!=0){
                        int Sz = Integer.parseInt(listSz);
                        boolean status = true;
                        boolean voidStatus = false;
                        
///Setting Active status TRUE / FALSE
                        for(int i=0;i<Sz;i++){
                            String payType = request.getParameter("payType"+i);
                            Debug.print("Pay Type is "+payType);
                            
                            if(payType==null || payType.trim().length()==0){
                                System.out.println("Null Made False");
                                status = false;
                                break;
                            }
                            
                            else{
                                if(payType!=null || payType.trim().length()!=0){
                                    if(payType.equalsIgnoreCase("Partial Payment") || payType==null){
                                        status = false;
                                        Debug.print("Status set as false ");
                                        break;
                                    } else{
                                        Debug.print("Status set as true");
                                        status = true;
                                    }
                                }
                            }
                        }
                        
                        
///Update the entries to table
                        for(int i=0;i<Sz;i++){
                            
                            String amtName = request.getParameter("amtName"+i);
                            String payType = request.getParameter("payType"+i);
                            String ccType = request.getParameter("ccType"+i);
                            String transId = request.getParameter("transId"+i);
                            String actualAmount = request.getParameter("actualAmount"+i);
                            
                            HLCAccTransactionVO axnVO = new HLCAccTransactionVO();
                            
                            //Reconclie Amount
                            if(amtName!=null){
                                if(amtName.trim().length()!=0){
                                    axnVO.setReconcile_amount(Float.parseFloat(amtName));
                                }
                            } else{
                                axnVO.setReconcile_amount(0);
                            }
                            
                            //Set Transaction Mode
                            if(payType!=null || payType.trim().length()!=0){
                                axnVO.setTransaction_mode(payType);
                            }
                            
                            if(ccType!=null || ccType.trim().length()!=0){
                                axnVO.setPayment_mode(ccType);
                            }
                            
                            // Set Active Status False if PAYMENT VOID
                            if(status==true){
                                if(payType.equalsIgnoreCase("void")){
                                    axnVO.setReconcile_status(false);
                                } else{
                                    axnVO.setReconcile_status(true);
                                }
                            } else{
                                axnVO.setReconcile_status(false);
                            }
                            
                            // Set Reconile Status True if FULL PAYMENT
                            axnVO.setActive_status(status);
                            
                            
                            // Set Transaction ID
                            axnVO.setTransaction_id(transId);
                            
                            // Setting Staff UserId, Staff IpAddress
                            String staff_userId = (String) session.getAttribute("userId");
                            String reqIp=request.getRemoteAddr();
                            Debug.print(" Request IP :"+reqIp);
                            
                            axnVO.setStaff_user_id(staff_userId);
                            axnVO.setStaff_ip_address(reqIp);
                            
                            //Update to database
                            if(axnVO.getReconcile_amount()>0){
                                transacConf = mahaRemote.updateRecouncilAccTxnDetails(axnVO);
                                Debug.print("Transaction Confirmation  "+transacConf);
                            }
                            
                            if(actualAmount!=null || actualAmount.trim().length()!=0){
                                double actVal = Double.parseDouble(actualAmount);
                                if(actVal<0){
                                    axnVO.setReconcile_amount(Float.parseFloat(actualAmount));
                                    transacConf = mahaRemote.updateRecouncilAccTxnDetails(axnVO);
                                    Debug.print("Transaction Confirmation  "+transacConf);
                                }
                            }
                        }
                        
///Setting Active status TRUE / FALSE
                        
                        boolean subPayStatus =false;
                        boolean subVoidstatus = false;
                        for(int i=0;i<Sz;i++){
                            
                            String payType = request.getParameter("divTransMode"+i);
                            Debug.print("Pay Type is "+payType);
                            
                            String divX = request.getParameter("div"+i);
                            
                            if(divX!=null){
                                if(payType==null || payType.trim().length()==0){
                                    System.out.println("Null Made False in sub payment");
                                    status = false;
                                    break;
                                } else{
                                    if(payType!=null || payType.trim().length()!=0){
                                        if(payType.equalsIgnoreCase("Partial Payment") || payType==null){
                                            subPayStatus = false;
                                            Debug.print("Status set as false ");
                                            break;
                                        } else{
                                            Debug.print("Status set as true");
                                            subPayStatus = true;
                                        }
                                    }
                                }
                            }
                        }
                        
                        if(status==false){
                            subPayStatus = false;
                        } else{
                            subPayStatus = true;
                        }
                        
                        //Sub Payment Entries
                        for(int j=1;j<=Sz;j++){
                            String divX = request.getParameter("div"+j);
                            Debug.print("Inside Sub divX "+divX + " : j =" + j);
                            if(divX!=null){
                                Debug.print("Inside Sub Payment divX is "+divX);
                                HLCAccTransactionVO axnVO = new HLCAccTransactionVO();
                                HLCAccTransactionVO dbVO = new HLCAccTransactionVO();
                                String accPayId = null;
                                
                                String itemNme = request.getParameter("divNewItem"+j);
                                String newAmt = request.getParameter("newAmt"+j);
                                String transMode = request.getParameter("divTransMode"+j);
                                String payMode = request.getParameter("payMode"+j);
                                String divtransId = request.getParameter("divTransId"+j);
                                
                                //Set Transaction Mode
                                if(transMode!=null || transMode.trim().length()!=0){
                                    axnVO.setTransaction_mode(transMode);
                                }
                                
                                if(payMode!=null || payMode.trim().length()!=0){
                                    axnVO.setPayment_mode(payMode);
                                }
                                
                                //Reconclie Amount
                                if(newAmt!=null){
                                    if(newAmt.trim().length()!=0){
                                        axnVO.setReconcile_amount(Float.parseFloat(newAmt));
                                    }
                                } else{
                                    axnVO.setReconcile_amount(0);
                                }
                                
                                if(divtransId!=null || divtransId.trim().length()!=0){
                                    dbVO = mahaRemote.getAccTransctionDetails(divtransId);
                                    
                                    String accNo = dbVO.getAccount_no();
                                    String accTyp = dbVO.getAccount_type();
                                    String accDesc = dbVO.getDescription();
                                    String accItemNo = dbVO.getItem_no();
                                    String subAccNo = dbVO.getSub_account_no();
                                    String classType = dbVO.getClass_Typ();
                                    accPayId = dbVO.getPayment_id();
                                    
                                    axnVO.setAccount_no(accNo);
                                    axnVO.setAccount_type(accTyp);
                                    axnVO.setDescription(accDesc);
                                    axnVO.setPayment_id(accPayId);
                                    axnVO.setSub_account_no(subAccNo);
                                    axnVO.setItem_no(accItemNo);
                                    axnVO.setClass_Typ(classType);
                                    
                                }
                                
                                // Set Sub Active Status False if PAYMENT VOID
                                if(subPayStatus==true){
                                    if(transMode.equalsIgnoreCase("void")){
                                        axnVO.setReconcile_status(false);
                                    } else{
                                        axnVO.setReconcile_status(true);
                                    }
                                } else{
                                    axnVO.setReconcile_status(false);
                                }
                                // Set Sub Reconile Status True if FULL PAYMENT
                                axnVO.setActive_status(subPayStatus);
                                
                                // Set Transaction ID
                                axnVO.setParentTransactionId(divtransId);
                                
                                // Setting Staff UserId, Staff IpAddress
                                String staff_userId = (String) session.getAttribute("userId");
                                String reqIp=request.getRemoteAddr();
                                Debug.print(" Request IP :"+reqIp);
                                
                                axnVO.setStaff_user_id(staff_userId);
                                axnVO.setStaff_ip_address(reqIp);
                                
                                if(axnVO.getReconcile_amount()>0){
                                    boolean insrtStatus = mahaRemote.insertAccountTxnDetails(axnVO);
                                    Debug.print("Sub Payment Isert Status is "+insrtStatus);
                                }
                            }
                        }
                    }
                    Debug.print("Before");
                    Vector horsememtypVect = new Vector();
                    horsememtypVect = horseremote.displayMembershipType("Horse");
                    request.setAttribute("horsememberVect", horsememtypVect);
                    Debug.print("After");
                    return mapping.findForward("admin-horse-request-listing");
                }
//End Add/Change Owner Rider Entries
                else if(process.equalsIgnoreCase("adminHorseDet")){
                    Debug.print("Inside Horse adminHorseDet Detail page...........................");
                    String memId = request.getParameter("memid");
                    Debug.print("MemberId" + memId);
                    if(memId!=null && memId.trim().length()!=0){
                        HLCHorseRegisterationVO HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        
                        
                        //End Setting Info
                        request.setAttribute("HorseDet",HorseDet);
                        
                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                        if(paymentId!=null && paymentId.trim().length()!=0){
                            String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                            request.setAttribute("paydet",paydet);
                        }
                        request.setAttribute("ListVO",HorseDisp);
                        Debug.print("Size is "+HorseDisp.size());
                        request.setAttribute("memId",memId);
                        return mapping.findForward("AdminHorseDetail");
                    } else{
                        return null;
                    }
                }
                 //----------------------------------------------------satheesh Start MMMA_0010: Human/Non-Human relationships  -----------------------------------------------------------


                 else if(process.equalsIgnoreCase("reqFrmList")) {
                 String uTypeId = request.getParameter("uTypeId");
                 ArrayList reqList = remote.getHorseRequestForm1(uTypeId);
                   Iterator itr = reqList.iterator();
                    while(itr.hasNext()){
                           HLCRequestHorseDetVO objReqList = (HLCRequestHorseDetVO)itr.next();
                            String relationshipId = objReqList.getRequestId();
                            String relationshipname =objReqList.getHorseMemberId();
                            String status =objReqList.getPaymentId();
                                             }
                request.setAttribute("reqList" ,reqList);
                request.setAttribute("uTypeId" ,uTypeId);
                return mapping.findForward("admin-horse-request-list");
            }







                   else if(process.equalsIgnoreCase("requFrmList")) {

                    String uTypeId=request.getParameter("uTypeId");

                     
                      System.out.print("enter the initBreedViewcontrol********************");
                      Vector vDisUserType = remote.displayUserTypeDetails();
                     
                     int i=0;
                     while(i<vDisUserType.size())
                    {
                        String[] val = (String[])vDisUserType.get(i);
                        String ID = val[0];
                        String name1 = val[1];
                        Debug.print("Value [0] is "+ID);
                        Debug.print("Value [1] is "+name1);
                        i++;
                    }
                       session.setAttribute("displayUserTypeDetails" ,null);
                       session.setAttribute("displayUserTypeDetails" ,vDisUserType);
                       request.setAttribute("uTypeId" , uTypeId);
                       return mapping.findForward("admin-horserequest-listing");
            }






                if(process.equalsIgnoreCase("insert")){
                    Debug.print("\n Inside Breed Insert...\n");
                    String relationDesc = request.getParameter("relationDesc");
                    String relationStatus = request.getParameter("status");
                    String SpecieId = request.getParameter("uType");
                    boolean bol = remote.createHorseOwner(relationDesc, relationStatus,SpecieId);
                    Debug.print("Result for add in Servlet : "+bol);
                    if (bol == true){
                    String uTypeId = request.getParameter("uType");
                    ArrayList reqList = remote.getHorseRequestForm1(uTypeId);
                             request.setAttribute("reqList" ,reqList);
                             request.setAttribute("uTypeId",uTypeId);
                             return mapping.findForward("admin-horse-request-list");

                      }
                      else{
                          request.setAttribute("err","st");
                          String uTypeId = request.getParameter("uType");
                          request.setAttribute("uTypeId",uTypeId);
                          return mapping.findForward("admin-horserequest-listing");
                       }
                }





                 if(process.equalsIgnoreCase("edit")){
                    Debug.print("\n Inside Breed Editing...\n");
                    String reqId= request.getParameter("requestId");
                    String breedName = request.getParameter("breedName");
                    ArrayList vDisUserType = (ArrayList)remote.getHorseRelationshipDetailsByRequestId1(reqId);
                                                 Iterator itPermList = vDisUserType.iterator();
						 while(itPermList.hasNext()){
			                         String [] perList = (String [])itPermList.next();
						 String Speciesname= perList[0];
						 String relationId= perList[1];
						 String relationDesc=perList[2];
						 String relationStatus=perList[3];
						 String uTypeId= perList[4];;
						 
                                               
                     }

                     request.setAttribute("Vobj",vDisUserType);

                    return mapping.findForward("admin-horserequest-listingEdit");
                }



                
if(process.equalsIgnoreCase("update")){
                    Debug.print("\n Inside Breed Update...\n");
                    String relation,relationStatus,relationId;

                     relation = request.getParameter("relationDesc");
                     relationStatus = request.getParameter("relationstatus");
                     relationId = request.getParameter("relationId");
                     String reqId=request.getParameter("relationId");
                     String uTypeId = request.getParameter("uTypeId");
                 
                     Debug.print("breedId is "+relationId);
                     boolean bol = remote.editHorserealtion(relation,relationStatus,relationId,uTypeId);
                     Debug.print("Update result is"+bol);
                     if(bol==true){
                             ArrayList reqList = remote.getHorseRequestForm1(uTypeId);
                               
                             session.setAttribute("reqList",null);
                             request.setAttribute("reqList" ,reqList);
                             request.setAttribute("uTypeId",uTypeId);
                             return mapping.findForward("admin-horse-request-list");
                    }
                    else{
                        request.setAttribute("err","st");
                         ArrayList vDisUserType = (ArrayList)remote.getHorseRelationshipDetailsByRequestId1(reqId);
                           request.setAttribute("Vobj",vDisUserType);
                           //For Debugs Starts
                           request.setAttribute("relation", relation);
                           //For Debugs Ends
                        return mapping.findForward("admin-horserequest-listingEdit");
                    }
                }



                if(process.equalsIgnoreCase("delete")){
                         Debug.print("ActionRoleMangement.deletebreed()");
                         String uTypeId=request.getParameter("uType");
                         String ownerId = request.getParameter("breedId");
                         String chkRoleIdArr[] = request.getParameterValues("chk");

                    for(int i=0;i<chkRoleIdArr.length;i++)
                    Debug.print("ActionRoleMangement.deletebreed() checked records: "+chkRoleIdArr[i]);

                    boolean result = false;

                    if(chkRoleIdArr!=null){

                        result = remote.deleteBreed(chkRoleIdArr);
                        Debug.print("ActionBreeedMangement.deletebreed() result:" + result);
                        if(result==true){
                             ArrayList reqList = remote.getHorseRequestForm1(uTypeId);
                              
                              session.setAttribute("reqList",null);
                              request.setAttribute("reqList" ,reqList);
                              Debug.print("ActionRoleMangement.deletebreed() result:" + result);
                              request.setAttribute("uTypeId",uTypeId);
                              System.out.println("utypeID"+uTypeId);
                             return mapping.findForward("admin-horse-request-list");
                        }
                        else{
                             
                              request.setAttribute("err","st");
                              return mapping.findForward("admin-horse-request-list");
                             }
                    }
                    Debug.print("ActionBreedMangement.deleteRole() sucessfully comes from servlet.");
                }


//-------------------------------------satheesh End MMMA_0010: Human/Non-Human relationships  -----------------------------------------------------------
                
                
                
                //----------------------------------------------------Punitha End-----------------------------------------------------------
                
                
                else if(process.equalsIgnoreCase("userDegrade")){
                    Debug.print("User Degrade");
                    String memId=(String)request.getParameter("memid");
                    
                    String relId = (String) request.getParameter("relId");
                    String relTyp = (String) request.getParameter("relTyp");
                    HLCHorseRegisterationVO HorseDet = null;
                    if(memId!=null && memId.trim().length()!=0){
                        HorseDet = (HLCHorseRegisterationVO) remote.getHorseDetailsByHorseMemberId(memId);
                        String ownerId = HorseDet.getOwnerId();
                        String addownerId = HorseDet.getAddOwnerId();
                        String prevownerId = HorseDet.getPrevOwnerId();
                        String riderId = HorseDet.getRiderMemberId();
                        String prevRiderId = HorseDet.getPrevRiderMemberId();
                        String addRiderId = HorseDet.getAddRiderMemberId();
                        String paymentId= HorseDet.getPaymentId();
                        // Owner Details Info
                        Debug.print("User Details owner ID "+ownerId);
                        if(ownerId!=null && ownerId.trim().length()!=0){
                            ArrayList ownerDetails = (ArrayList)userremote.getUserContactDetails(ownerId);
                            request.setAttribute("ownerDetails",ownerDetails);
                        } else{
                            request.setAttribute("ownerDetails",null);
                        }
                        //Start Setting Info
                        //Additional Owner Setting Info
                        if(addownerId!=null && addownerId.trim().length()!=0){
                            ArrayList addownerDet = (ArrayList)userremote.getUserContactDetails(addownerId);
                            request.setAttribute("addownerDet",addownerDet);
                        } else{
                            request.setAttribute("addownerDet",null);
                        }
                        //Rider Info Setting
                        if(riderId!=null && riderId.trim().length()!=0){
                            ArrayList prevownerDet = (ArrayList)userremote.getMemberContactDetails(riderId);
                            request.setAttribute("riderInfoDetails",prevownerDet);
                        } else{
                            request.setAttribute("riderInfoDetails",null);
                        }
                        //Previous Rider Info Setting
                        if(prevRiderId!=null && prevRiderId.trim().length()!=0){
                            ArrayList prevriderDet = (ArrayList)userremote.getMemberContactDetails(prevRiderId);
                            request.setAttribute("prevriderDetails",prevriderDet);
                        } else{
                            request.setAttribute("prevriderDetails",null);
                        }
                        //Additional Rider Info Setting
                        if(addRiderId!=null && addRiderId.trim().length()!=0){
                            ArrayList addriderDet = (ArrayList)userremote.getMemberContactDetails(addRiderId);
                            request.setAttribute("addriderDetails",addriderDet);
                        } else{
                            request.setAttribute("addriderDetails",null);
                        }
                        //End Setting Info
                        request.setAttribute("HorseDet",HorseDet);
                        boolean pri_owner = remote.isHorsePriOwnerExist(memId,userId);
                        Debug.print("Primary Owner is Avilable "+pri_owner);
                        if(pri_owner==true){
                            remote.deGradeOwnerRider(relId,relTyp);
                            ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                            String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                            request.setAttribute("paydet",paydet);
                            request.setAttribute("ListVO",HorseDisp);
                            Debug.print("Size is "+HorseDisp.size());
                            request.setAttribute("err","");
                            
                            boolean update_result = remote.isHorsePriTrainerExist(memId,userId,relId);
                            Debug.print("update_result is "+update_result);
                            return mapping.findForward("UserDegradeMemberList");
                            
                        } else{
                            boolean pri_rider = remote.isHorsePriOwnerRiderExistInRelation(relId,userId);
                            Debug.print("Primary Rider is Avilable "+pri_rider);
                            if(pri_rider==true){
                                remote.deGradeOwnerRider(relId,relTyp);
                                remote.deGradeOwnerRider(relId,relTyp);
                                ArrayList HorseDisp = (ArrayList) remote.getHorseRelationshipDetailsDetails(memId);
                                String[] paydet = (String[]) remote.getHorsePaymentDetails(paymentId);
                                request.setAttribute("paydet",paydet);
                                request.setAttribute("ListVO",HorseDisp);
                                Debug.print("Size is "+HorseDisp.size());
                                request.setAttribute("err","");
                                boolean update_result = remote.isHorsePriTrainerExist(memId,userId,relId);
                                Debug.print("update_result is "+update_result);
                                return mapping.findForward("UserDegradeMemberList");
                            } else{
                                Debug.print("No Primary Rider & Primary Owner is Avilable");
                                
                                if(memId!=null && memId.trim().length()!=0){
                                    request.setAttribute("err","st");
                                    request.setAttribute("horseMemberId",memId);
                                    return mapping.findForward("PermissionDenied");
                                }
                            }
                        }
                    }
                    Debug.print("Leaving Servlet");
                    return mapping.findForward("frmHorseMemberList");
                }
            }
            
        } catch(Exception einsert){
            System.out.println("RegHorseListing Action Error " + einsert.toString());
        }
        return null;
    }
}