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
 /* Program Name    : ActionAddRidOwnPay.java
 *  Created Date    : February 6, 2007, 1:13 PM
 *  Author          : Punitha.R 
 *  Version         : 1.24
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
 
package com.mrm.action;
import com.hlcaccounts.session.*;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlccommon.util.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.mrm.actionform.FormAddRidOwnPay;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
// import com.sun.org.apache.bcel.internal.verifier.statics.DOUBLE_Upper;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.regex.*;
import javax.servlet.http.*;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.text.*;
import java.util.*;
import com.hlcform.stateless.*;


public class ActionAddRidOwnPay extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     try{ 
          
        HttpSession session=request.getSession();
        MessageResources mr=getResources(request);
        
        String staff_user_id= (String) session.getAttribute("staff_user_id");
        
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.kavery");
         String jname=mr.getMessage("jndi.usrreg");
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        
        Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
         
        HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
        HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
        Object oef = jndiContext.lookup(jname);
        
        HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(oef,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote userremote = userhome.create();
        
        Object objref = jndiContext.lookup(jndiname);
       
        HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
        HLCKaverySessionBeanStatfulRemote remote = home.create();
         
        String mahanadhiJndi=mr.getMessage("jndi.acc");
        Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
        
        Object maha=jndiContext.lookup(mahanadhiJndi);
        HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
        HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
        
         HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
         HLCHorseRegistrationVO horseRegVO  = new HLCHorseRegistrationVO(); 
         HLCHorseDescriptionVO objHorseDesc = new HLCHorseDescriptionVO();
         HLCHorseUserVO objOwnerVO = new HLCHorseUserVO();
         HLCHorseUserVO objAddOwnerVO = new HLCHorseUserVO();
         HLCRequestHorseDetVO reqHrDetVO = new HLCRequestHorseDetVO();
         FormAddRidOwnPay fbean=(FormAddRidOwnPay)form;
         System.out.println("FormAddRidOwnPay ="+ fbean);
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         
         String userId =(String)session.getAttribute("userId");
         String reqIp=request.getRemoteAddr();
         Debug.print(" Request IP :"+reqIp);
         
         String process = request.getParameter("process");
         Debug.print("process function:" + process);
         
         if(process==null || process.trim().length()==0){
         return mapping.findForward("login");
         }
      
        if(process!=null && process.equals("horseReg")){
            Debug.print("Inside the add function");
            String newBirthmonthId = "";
            String newBirthdayId = "";
            String newBirthyearId = "";
            String sexId = "";
            String newAddressId = "";
            String newCountryId = "";
            String newStateId = "";
            String newCityId = "";
            String newZipId = "";
            String newEmailId = "";
            String newPhoneId = "";
            String newFaxId = "";      
            String regFor = "";
            String addOwnerUserId = "";
            String firstName = "";
            String lastName = "";
            String phone = "";
            String firstNameId1 = "";
            String lastNameId1 = "";
            String phoneId1 = "";
            String ownerUserId = "";
            String userNameId3 = "";
            String firstNameId3 = "";
            String lastNameId3 = "";
            String phoneId3 = "";
            String regAddFor="";
            String ado ="";
            String userNameOne ="";
            String comFor ="";
            String compAddFor = "";
            String horseMemberId = "";
            String riderId = "";
            String companyName="";
            
            
            String horseRegType="";
            String horseName="";
            String ownerSecLogName="";
            String ownerSecMemId="";
            String userName="";
            
            System.out.println("ownerUserId :" + request.getParameter("ownerUserId"));
            System.out.println("addOwnerUserId :" + request.getParameter("addOwnerUserId"));
            Date dob=null;
            Date dob1=null;
            boolean status = false;
            String loginName ="";
           String sesUserId =(String)session.getAttribute("userId");
           String membershipName=request.getParameter("horseRegType");
           String competitionName=request.getParameter("competitionName");       
  //-------------------------------HORSE OWNER INFORMATION-------------------------------------------
           
           ArrayList txnDetails = new ArrayList();
           HLCAccTransactionVO accTxnAddPay = new HLCAccTransactionVO();
           HLCAccTransactionVO accTxnPhoneChg = new HLCAccTransactionVO();
           String phoneCharge=request.getParameter("phoneCharge");
           
            try{
                 
                String serviceType = "";
                 String finalPrimaryPh="";
                 String finalFax="";
                horseMemberId = request.getParameter("horseMemberId");
                Debug.print("horseMemberId:" + horseMemberId);
                riderId = request.getParameter("arhlcNo");
                regFor= request.getParameter("regAddFor");
                Debug.print("regFor:" + regFor);
                ownerUserId = request.getParameter("addOwnerUserId");
                
                horseRegVO.setRegisteredBy(sesUserId);
                Debug.print(" Session userId:" + sesUserId);
                
                String serviceStatus=request.getParameter("servicePrice");
                
                String servicePrice ="";
                    if(phoneCharge!=null && phoneCharge.trim().length()!=0){
                        if(serviceStatus!=null){
                            serviceType = serviceStatus;
                        }
                        
                        else{
                            serviceType = null;
                        }
                        
                    }
                System.out.print("serviceType" + serviceType);
                System.out.print("serviceStatus" + serviceStatus);
                System.out.print("phoneCharge" + phoneCharge);

                String serviceTypId = request.getParameter("serTypId");
                Debug.print("serviceTypId in servlet:"+serviceTypId);
                
                HLCAccTxnTypeDetailVO accTxnTyp = new HLCAccTxnTypeDetailVO();
                accTxnTyp = mahaRemote.getPhoneServChgAccTxnTypeDetails(serviceTypId);
                Debug.print("getPhoneServChgAccTxnTypeDetails() for add owner / rider txn :"+accTxnTyp.toString());
                
                
                
                // Get client's IP address
                String addr = request.getRemoteAddr(); // 123.123.123.123
                Debug.print("Port Value:" + addr);
                
                //accTxnAddPay.setUser_id(sesUserId);
                accTxnAddPay.setAccount_no(accTxnTyp.getAccount_no());
                accTxnAddPay.setAccount_type("Income");
                accTxnAddPay.setActive_status(false);
                accTxnAddPay.setClass_Typ(accTxnTyp.getClass_name());
                //accTxnAddPay.setIp_address(addr);
                accTxnAddPay.setItem_no(accTxnTyp.getItem_no());
                String changeAmt = request.getParameter("addAmt");
                
                accTxnAddPay.setAmount(Float.parseFloat(changeAmt));
                
                accTxnAddPay.setSub_account_no(accTxnTyp.getSub_account_no());
                accTxnAddPay.setDescription(accTxnTyp.getTransaction_name());    
                
                String phoneCrgId = request.getParameter("servicePrice");
                //String phoneCharge = request.getParameter("phoneCharge");
                
                Debug.print("phoneCrgId for admin service type id :"+phoneCrgId);
                Debug.print("phoneCharge for admin service type checkbox :"+phoneCharge);
                                
                if(phoneCharge!=null)
                {
                    HLCAccTxnTypeDetailVO accTxnTyp1 = new HLCAccTxnTypeDetailVO();
                    accTxnTyp1 = mahaRemote.getPhoneServChgAccTxnTypeDetails(phoneCrgId);
                    Debug.print("getPhoneServChgAccTxnTypeDetails() for admin phone charge txn :"+accTxnTyp1.toString());
                    
                    //accTxnPhoneChg.setUser_id(sesUserId);
                    accTxnPhoneChg.setAccount_no(accTxnTyp1.getAccount_no());
                    accTxnPhoneChg.setAccount_type("Income");
                    accTxnPhoneChg.setActive_status(false);
                    accTxnPhoneChg.setClass_Typ(accTxnTyp1.getClass_name());
                    //accTxnPhoneChg.setIp_address(addr);
                    accTxnPhoneChg.setItem_no(accTxnTyp1.getItem_no());
                    String phAmt = request.getParameter("phoneCharge");
                    
                    accTxnPhoneChg.setAmount(Float.parseFloat(phAmt));
                    
                    accTxnPhoneChg.setSub_account_no(accTxnTyp1.getSub_account_no());
                    accTxnPhoneChg.setDescription(accTxnTyp1.getTransaction_name());    
                
                }
                
               /* Debug.print("outside the phoneCharge:" +phoneCharge);
               if(phoneCharge!=null){
             Debug.print("phoneCharge:" +phoneCharge);
              serviceType = phoneCharge;
                }
               else{
               serviceType = null;
               }*/
                
                
              /*
               * if rider id and owner id comes null it redirects to horseregadd.jsp
               */  
                if((riderId==null || riderId.trim().length()==0)  && (regFor==null || regFor.trim().length()==0)){ 
                    Debug.print("inside the loop riderId:" + riderId);
                    Debug.print("inside the loop regFor:" + regFor);
                    request.setAttribute("horseMemberId",horseMemberId);
                    return mapping.findForward("goToAddRidOwn");
                }
                else{ 
                         if(regFor.equals("mem1")){
                             
                          userNameOne = request.getParameter("ownerUseaNoTwo");
                          ownerSecMemId=userNameOne;
                          Debug.print("ownerSecMemId in servlet 1 : "+ ownerSecMemId);
                          ownerUserId = (String)userremote.getUserIdBasedOnMemberId(userNameOne);
                          Debug.print("ownerUserId from another method: "+ ownerUserId);
                        }
                         else if(regFor.equals("rid")){
                          Debug.print("rid method: "); 
                          userName = request.getParameter("userNameId2");
                          ownerSecMemId=userName;
                          Debug.print("ownerSecMemId in servlet 2: "+ ownerSecMemId);
                          firstName = request.getParameter("firstNameId2");
                          lastName = request.getParameter("lastNameId2");
                        }
                         else if(regFor.equals("acc")){
                          Debug.print("acc method: "); 
                          userNameId3 = request.getParameter("userNameId3");
                          ownerSecLogName=userNameId3;
                          Debug.print("ownerSecLogName in servlet 3: "+ ownerSecLogName);
                          firstName = request.getParameter("firstNameId3");
                          lastName = request.getParameter("lastNameId3");
                        }
                          
                        else if(regFor.equals("cmp")){
                              status = true;
                              compAddFor = request.getParameter("ecmp1");
                              if(compAddFor.equals("yes")){
                                  loginName = request.getParameter("existAddCompNameId1");
                                  firstName = request.getParameter("cpAddfirstNameId1");
                                  lastName = request.getParameter("cpAddlastNameId1");                                
                                  ownerSecLogName=loginName;
                                  Debug.print("ownerSecLogName in servlet 4: "+ ownerSecLogName);
                                    Debug.print("loginName: " + loginName);
                                    Debug.print("firstName: " + firstName);
                                    Debug.print("firstName: " + firstName);
                              }
                              else if(compAddFor.equals("no")){
                                    loginName = request.getParameter("newAddFirstNameIdComp");
                                    newAddressId = request.getParameter("newAddAddressIdComp");
                                    newCountryId = request.getParameter("newAddCountryIdComp");
                                    newStateId = request.getParameter("newAddStateIdComp");
                                    newCityId = request.getParameter("newAddCityIdComp");
                                    newZipId = request.getParameter("newAddZipIdComp");
                                    newEmailId = request.getParameter("newAddEmailIdComp");
                                    newPhoneId = request.getParameter("newAddPhoneIdComp");
                                    newFaxId = request.getParameter("newAddFaxIdComp");
                                    firstName = request.getParameter("cpAddFirstNameIdComp");
                                    lastName = request.getParameter("cpAddLastNameIdComp");
                                    ownerUserId = null;
                                     if(newPhoneId!=null){
                Debug.print("request.getParameter(pphone_txt) value:"+newPhoneId);
                StringTokenizer strTkns = new StringTokenizer(newPhoneId,"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                                try{
                                    String phNo = (String)strTkns.nextToken();
                                    if(phNo!=null && phNo.trim().length()!=0){
                                        Debug.print("ph no Added from Stokenizer:" + phNo);
                                        finalPrimaryPh=finalPrimaryPh+phNo;
                                    }
                                }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended primary phNo :"+finalPrimaryPh);
                }
                      
                  if(newFaxId!=null){
                Debug.print("request.getParameter(pphone_txt) value:"+newFaxId);
                StringTokenizer strTkns = new StringTokenizer(newFaxId,"[](),.-{}");
                                               
                        while(strTkns.hasMoreTokens()){
                                try{
                                    String faxNo = (String)strTkns.nextToken();
                                    if(faxNo!=null && faxNo.trim().length()!=0){
                                        Debug.print("ph no Added from Stokenizer:" + faxNo);
                                        finalFax=finalFax+faxNo;
                                    }
                                }
                                 catch(Exception e){
                                    Debug.print("ph no tokanizing exception:" + e);
                                }
                            }
                            
                Debug.print("finally appended primary phNo :"+finalFax);
                }  
                              }
                        } 
            objOwnerVO.setLoginName(loginName);      
            objOwnerVO.setFirstName(firstName);
            objOwnerVO.setLastName(lastName);
            objOwnerVO.setGender(sexId);
            objOwnerVO.setDob(dob);
            objOwnerVO.setAddress(newAddressId);
            objOwnerVO.setCity(newCityId);
            objOwnerVO.setCountry(newCountryId);
            objOwnerVO.setState(newStateId);
            objOwnerVO.setEmailId(newEmailId);
            objOwnerVO.setPhoneNo(finalPrimaryPh);
            objOwnerVO.setFaxNo(finalFax);
            objOwnerVO.setZip(newZipId);
            
            if(ownerUserId!=null && ownerUserId.trim().length()!=0){
            objOwnerVO.setUserId(ownerUserId);
            }
            else{
               objOwnerVO.setUserId(null);     
            }
                }
   } 
  catch(Exception owner){
      System.out.println("Exception occurs in owner insertion part:" + owner.getMessage());
  }
         
           /*System.out.println("======================================================");
            System.out.println("=====================First Part Starts================");
            System.out.println("======================================================");
            System.out.println("ownerUserId :" +ownerUserId);
            System.out.println(" regFor :" + regFor);
            System.out.println(" comFor :" + comFor);
            System.out.println(" compAddFor :" + compAddFor);
            
            System.out.println(" firstName :" + firstName);
            System.out.println(" lastName :" + lastName);
            System.out.println(" phone :" + phone);
            System.out.println(" ownerUserId :" + ownerUserId);
            System.out.println(" phoneId1 :" + phoneId1);
            System.out.println(" newBirthmonthId :" + newBirthmonthId);
            System.out.println(" newBirthdayId :" + newBirthdayId);
            System.out.println(" newBirthyearId :" + newBirthyearId);
            System.out.println(" sexId :" + sexId);
            System.out.println(" newAddressId :" + newAddressId);
            System.out.println(" newCountryId :" + newCountryId);
            System.out.println(" newStateId :" + newStateId);
            System.out.println("  newCityId:" + newCityId);
            System.out.println(" newZipId :" + newEmailId);
            System.out.println(" newPhoneId :" + newPhoneId);
            System.out.println("  newFaxId:" + newFaxId); 
            
            
            Debug.print("Successfully skipped Owner information:");     
            Debug.print("Successfully skipped Owner information: Add");  */   
            boolean result = false;
                
                String emailId = (String)session.getAttribute("emailId");
                 // Get client's IP address
            String addr = request.getRemoteAddr(); // 123.123.123.123
            Debug.print("Port Value:" + addr);
                int  CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double totalAmount =0;
                String CcNumber ="0";
                String checkNumber="0";
                Date check_date = null;
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
                String nameoncheck = null;
                String bankname = null;
                float checkAmount = 0;
                String  r11 = fbean.getR11();
                System.out.print("Check Status :" + r11);
               
                if(r11.equals("card")){
                    if(fbean.getCcNumber()==null || fbean.getCcNumber().equals("")){
                        CcNumber = "0";
                    }
                    else{
                        CcNumber = fbean.getCcNumber();
                    }
                    if(fbean.getCcName()==null || fbean.getCcName().equals("")){
                        ccName ="";
                    }
                    else {
                        ccName=fbean.getCcName();
                    }
                    if(!(fbean.getCcType().equals("")) && fbean.getCcType()!=null){
                        ccType = fbean.getCcType();  
                    }
                    if(fbean.getCcExpMonth()==null || fbean.getCcExpMonth().equals("")){
                        CcExpMonth =0;
                    }
                    else {
                        CcExpMonth=Integer.parseInt(fbean.getCcExpMonth());
                    }
                    if(fbean.getCcExpYear()==null || fbean.getCcExpYear().equals("")){
                        CcExpYear =0;
                    }
                    else{
                        CcExpYear = Integer.parseInt(fbean.getCcExpYear());
                    }
                    if(fbean.getCcCvvid()==null || fbean.getCcCvvid().equals("")){
                        CcCvvid =0;
                    }
                    else{
                        CcCvvid = Integer.parseInt(fbean.getCcCvvid());
                    }
                    check_date= null;
                    objPayment.setPaymentStatus("Card");
                }
      
                if(r11.equals("check")){
                    if(fbean.getCheckNumber()==null || fbean.getCheckNumber().equals("")){
                    checkNumber="0";
                    }
                    else{
                    checkNumber= fbean.getCheckNumber();
                    }
                    if(fbean.getCheckDate().equals("")){
                    check_date= null;
                    }
                    else{
                    check_date =(Date)sdf.parse(fbean.getCheckDate());
                    }
                    objPayment.setPaymentStatus("Check");
                     
                }
                String tempAmount = request.getParameter("totalAmount");
                String tempCheckAmount = request.getParameter("chkBalAmt");
                 if(tempCheckAmount==null || tempCheckAmount==""){
                    checkAmount=0; 
                }
                else{
                    checkAmount = Float.parseFloat(tempCheckAmount);
                } 
                System.out.println("tempAmount1" + tempAmount);
                if(tempAmount==null || tempAmount==""){
                    totalAmount=0; 
                }
                else{
                    totalAmount = Double.parseDouble(tempAmount);
                } 
                System.out.println("tempAmount1" + tempAmount);
                bankname =fbean.getBankName();
                nameoncheck =fbean.getNameCheck();
                objPayment.setUserId(sesUserId);
                objPayment.setCcName(ccName);
                objPayment.setCcType(ccType);
                objPayment.setCcNumber(CcNumber);
                objPayment.setCcExpMonth(CcExpMonth);
                objPayment.setCcExpYear(CcExpYear);
                objPayment.setCcCvvid(CcCvvid);
                objPayment.setBankName(bankname);
                objPayment.setCheckName(nameoncheck);
                objPayment.setCheckDate(check_date);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setAmount(totalAmount);
                //objPayment.setCheckAmount(checkAmount);
                objPayment.setPaymentDate(new Date());
                objPayment.setIpAddress(addr);
              
           
                

           if(r11.equals("card")){
                System.out.println("Sucessfully Redirect to NoVa:");
                session.setAttribute("objPaymentList",objPayment);
                System.out.println("Sucessfully Payment objPaymentList:" + objPayment.toString());
                request.setAttribute("email",emailId);
                request.setAttribute("cardNo",String.valueOf(CcNumber));
                String expMon = String.valueOf(CcExpMonth);
                String expYear = String.valueOf(CcExpYear);
                if(expMon.trim().length()==1) {
                     expMon = "0" + expMon;
                }
                System.out.println("expYear:" + expYear);
                //expYear = expYear.substring(2);
                System.out.println("expMon:" + expMon);
               // System.out.println("expYear:" + expYear);
                String expDate = expMon + expYear;
                request.setAttribute("expDate",expDate);
                request.setAttribute("amount",String.valueOf(totalAmount));
              
                request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                String paymentId = remote.getNextId();
                Debug.print("paymentId in check:" + paymentId);
                objPayment.setPaymentId(paymentId);
                objPayment.setPaymentStatus("Card");

                session.setAttribute("amount",String.valueOf(totalAmount));
                session.setAttribute("paymentId",paymentId);
                
               
                
                    reqHrDetVO.setHorseMemberId(horseMemberId);
                    reqHrDetVO.setPaymentId(paymentId);
                    reqHrDetVO.setRequestedBy(sesUserId);
                    reqHrDetVO.setReqAmount(Float.parseFloat(tempAmount));  

 
                    session.setAttribute("reqHrDetVO",reqHrDetVO);
                    session.setAttribute("objOwnerVO",objOwnerVO);
                    session.setAttribute("horseMemberId",horseMemberId);
                    session.setAttribute("riderId",riderId);
                    session.setAttribute("status",String.valueOf(status));
                    session.setAttribute("ownerUserId",ownerUserId); 
                    StringBuffer reqURL = request.getRequestURL();
                    
                    String riderFName="";
                        String riderLName="";
                        String OwnerFName="";
                        String OwnerLName="";
                        String OwnerMailId="";
                        String OwnerStatusId="";
                        String OwnerStatusName="";
                        
                        String relaId="";
                        String relaTypeName="";
                        String preFName="";
                        String preLName="";
                        String OwnerFName1="";
                        String OwnerLName1="";
                        String OwnEmail1="";
                        String preOwnerEmail="";
                        String preOwnerStatus="";
                        String OwnStatus="";
                     
                                        
                        String riderEmailId=request.getParameter("addREmail");
                         riderFName=request.getParameter("additionalRider");
                        riderLName=request.getParameter("lastRider");
                    
                    if(ownerSecLogName!=null && ownerSecLogName.length()!=0){
                        Debug.print("ownerSecLogName in log name section" + ownerSecLogName);
                        ArrayList objOwnerDet1 = new ArrayList();
                        objOwnerDet1 = remote.getOwnerDetailsForMail(ownerSecLogName);
                        session.setAttribute("commonOwners",objOwnerDet1);
                        if(objOwnerDet1!=null && objOwnerDet1.size()!=0){
                            Debug.print("objOwnerDet1::: ***** " + objOwnerDet1);
                          Iterator itr1 = objOwnerDet1.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                           OwnerFName=objOwnerInfo1.getOwnerLogName();
                           OwnerFName=objOwnerInfo1.getOwnerFirstName();
                           OwnerLName=objOwnerInfo1.getOwnerLastName();
                           OwnerMailId=objOwnerInfo1.getOwnerEmail();
                           OwnerStatusId=objOwnerInfo1.getStatusId();
                           OwnerStatusName=objOwnerInfo1.getStatusName1();
                          }  
                        }
                        }else{ 
            
                        ArrayList objOwnerDet = new ArrayList();
                        objOwnerDet = remote.getUserDets(ownerSecMemId);
                        session.setAttribute("commonOwners",objOwnerDet);
                        if(objOwnerDet!=null && objOwnerDet.size()!=0){
                          Debug.print("objOwnerDet::: &&&&" + objOwnerDet);
                          Iterator itr = objOwnerDet.iterator();
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();
                           OwnerFName=objOwnerInfo.getOwnerFirstName();
                           Debug.print("OwnerFName:::" + OwnerFName);
                           OwnerLName=objOwnerInfo.getOwnerLastName();
                           Debug.print("OwnerLName:::" + OwnerLName);
                           OwnerMailId=objOwnerInfo.getOwnerEmail(); 
                           Debug.print("OwnerMailId:::" + OwnerMailId);
                           OwnerStatusId=objOwnerInfo.getStatusId();
                           OwnerStatusName=objOwnerInfo.getStatusName1();
                          }  
                        }
                        
                        }
                       String owners=""; 
                       String ownerNames="";
                       String riderNames="";
                       ArrayList objPreOwnRidDet = new ArrayList();
                        objPreOwnRidDet = remote.getPreOwnerRiderDetails(horseMemberId);
                        session.setAttribute("commonOwners",objPreOwnRidDet);
                         if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0){
                          Debug.print("objPreOwnRidDet::: &&&&" + objPreOwnRidDet);
                          Iterator itr = objPreOwnRidDet.iterator();
                          int i=0;
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                           relaId=objOwnerInfo.getRelationTypeId();
                           Debug.print("relaId:::" + relaId);
                           relaTypeName=objOwnerInfo.getRelationTypeName();
                           Debug.print("relaTypeName:::" + relaTypeName);
                           
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){
                                preFName=objOwnerInfo.getPreOwnerFName();
                                OwnerFName1=preFName; 
                                preLName=objOwnerInfo.getPreOwnerLName();
                                OwnerLName1=preLName;
                                preOwnerEmail=objOwnerInfo.getPreEmail();
                                OwnEmail1=preOwnerEmail;
                                preOwnerStatus=objOwnerInfo.getRelationStatus();
                                OwnStatus=preOwnerStatus;
                                if(i==0){
                                owners="\""+OwnEmail1+"\"";    
                                }else{
                                owners=owners+","+"\""+OwnEmail1+"\"";
                                }
                                Debug.print("before increment :"+i);
                                Debug.print("Owners :"+owners); 
                                i++;
                                Debug.print("after increment :"+i);
                               
                           } 
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){  
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preLName=objOwnerInfo.getPreOwnerLName();
                                String tempOwnerNames=preFName+" "+preLName;
                                if(i==0){
                                ownerNames=tempOwnerNames;
                                }else{
                                ownerNames=ownerNames+","+tempOwnerNames;    
                                }
                          }
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Rider"))){  
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preLName=objOwnerInfo.getPreOwnerLName();
                                String tempRiderNames=preFName+" "+preLName;
                                if(i==0){
                                riderNames=tempRiderNames;
                                }else{
                                riderNames=riderNames+","+tempRiderNames;    
                                }
                           }
                           
                          }  
                        }  
                        
                   Debug.print("Owners :"+owners);     
                   String textLimit="This horse can enter events up to and including Training level";
                   String textFull="This horse can enter any level at which it is properly qualified";
                       
                      String membershipName1="";
                      Debug.print("membershipName a:" + membershipName);
                     if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                        membershipName1 = membershipName+" "+textLimit;  
                        Debug.print("membershipName1 b:" + membershipName1);
                        }
                    else if(membershipName!=null && membershipName.equalsIgnoreCase("Full")){
                        membershipName1 = membershipName+" "+textFull; 
                        Debug.print("membershipName1 d :" + membershipName1);
                        }
                     else{
                         membershipName1 =membershipName;  
                        Debug.print("membershipName1 e:" + membershipName1);
                         } 
                      
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("addrid.nova");
                    String nova = url + tmpNova;
                    Debug.print("succsssullll from app file nova........::" + nova);
                    Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    request.setAttribute("nova",nova);
                    
                 session.setAttribute("riderEmailId",riderEmailId);
                 session.setAttribute("riderFName",riderFName);
                 session.setAttribute("riderLName",riderLName);
                 session.setAttribute("OwnerFName",OwnerFName);
                 session.setAttribute("OwnerLName",OwnerLName);                
                 session.setAttribute("OwnerMailId",OwnerMailId);
                 session.setAttribute("membershipName",membershipName);
                 session.setAttribute("OwnerStatusName",OwnerStatusName);
                 session.setAttribute("membershipName1",membershipName1);
                 session.setAttribute("OwnStatus",OwnStatus);
                 session.setAttribute("OwnEmail1",OwnEmail1);
                 session.setAttribute("owners",owners);
                 session.setAttribute("competitionName",competitionName);
                 session.setAttribute("ownerNames",ownerNames);
                 session.setAttribute("riderNames",riderNames);
                    
                    accTxnAddPay.setPayment_id(paymentId);
                    accTxnPhoneChg.setPayment_id(paymentId);
                    
                    accTxnPhoneChg.setPayment_mode(ccType);
                    accTxnAddPay.setPayment_mode(ccType);

                    accTxnAddPay.setActive_status(false);
                    accTxnPhoneChg.setActive_status(false);
                    
                    txnDetails.add(accTxnAddPay);
                    
                    if(phoneCharge!=null)
                    {
                        txnDetails.add(accTxnPhoneChg);
                    }
                    
                    session.setAttribute("txnDetails",txnDetails);
                     String inVId = request.getParameter("inVoiceId");
                System.out.println("inVId in servlet from request: " + inVId);
                int intVId = 1;
                request.setAttribute("AMT", request.getParameter("totalAmount"));
                request.setAttribute("PAYMENTACTION", "Authorization");
                request.setAttribute("CREDITCARDTYPE", request.getParameter("ccType"));
                request.setAttribute("ACCT", request.getParameter("ccNumber"));
                request.setAttribute("EXPDATE", expDate);
                request.setAttribute("IPADDRESS", reqIp);
                request.setAttribute("FIRSTNAME", request.getParameter("ccName"));
                request.setAttribute("CVVNo", request.getParameter("ccCvvid"));
                request.setAttribute("STREET", "1 Main St");
                request.setAttribute("CITY", "San Jose");
                request.setAttribute("STATE", "CA");
                request.setAttribute("ZIP", "95131");
                request.setAttribute("COUNTRYCODE", "US");
                request.setAttribute("EMAIL", emailId);
                session.setAttribute("objPaymentVO", objPayment);

                request.setAttribute("purpose", "addriderowner");
                //intVId++; 
                request.setAttribute("intVId", "1");
                System.out.println("intVId in servlet InsertHorseRegAction &&&&&&&" + intVId);

                return mapping.findForward("testing");

                    //return mapping.findForward("novaPage");

           }
                else{
                    String paymentId = remote.getNextId();
                    reqHrDetVO.setHorseMemberId(horseMemberId);
                    reqHrDetVO.setPaymentId(paymentId);
                    reqHrDetVO.setRequestedBy(sesUserId);
                    reqHrDetVO.setReqAmount(Float.parseFloat(tempAmount));
                  
                    Debug.print("paymentId in check:" + paymentId);
                    objPayment.setPaymentId(paymentId);
                    objPayment.setPaymentStatus("check");
   
                     Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                     
                     boolean output=false;
                     boolean updBal =false;
                     boolean result1  = objPaySessRemote.createPayment(objPayment); 
                     String reqId1 = remote.createHorseRequest(reqHrDetVO);
                     result = remote.createHorseRiderAndOwnerDetails(horseMemberId, riderId, ownerUserId, objOwnerVO,status,false,reqId1);
                     boolean cardStatus = false;
                     String sessionUser = (String)session.getAttribute("loggedBy");
                     Debug.print("sessionUser" + sessionUser);
                     if(sessionUser !=null && (sessionUser.equalsIgnoreCase("Admin") || sessionUser.equalsIgnoreCase("Usea Staff"))){
                     if(checkAmount!=0){
                            updBal = remote.updatePendingAmount(sesUserId,paymentId,checkAmount);
                            request.setAttribute("totalAmount",String.valueOf(checkAmount));
                                    if(tempAmount!=null && tempCheckAmount!=null){
                                    double tempAmt = Double.parseDouble(tempAmount);
                                    double chkAmt = Double.parseDouble(tempCheckAmount);
                                    double extraAmt =  chkAmt - tempAmt;
                                        Debug.print("Extra Amount Through Admin Pay "+extraAmt);
                                        if(extraAmt > 0){
                                            String StrextraAmt = String.valueOf(extraAmt);
                                            HLCAccTxnTypeDetailVO overpayDet = mahaRemote.getOverPayAccTransactionTypeDetail();
                                            HLCAccTransactionVO overpayfinalDet = new HLCAccTransactionVO();

                                            String accNo = overpayDet.getAccount_no();
                                            String accClassname = overpayDet.getClass_name();
                                            String accItemNo = overpayDet.getItem_no();
                                            String accAccNo = overpayDet.getSub_account_no();
                                            String accTranName = overpayDet.getTransaction_name();
                                            String accTyped = overpayDet.getTransaction_type();
                                            String accTypeId = overpayDet.getTransaction_type_id();
                                            String description = overpayDet.getTransaction_name();
                                            Debug.print("While getting \n"+ overpayDet.toString());

                                            overpayfinalDet.setAccount_no(accNo);
                                            overpayfinalDet.setAccount_type("Income");
                                            overpayfinalDet.setAccount_no(accNo);
                                            overpayfinalDet.setClass_Typ(accClassname);
                                            overpayfinalDet.setPayment_id(paymentId);
                                            overpayfinalDet.setSub_account_no(accAccNo);
                                            overpayfinalDet.setItem_no(accItemNo);
                                            overpayfinalDet.setAmount(Float.parseFloat(StrextraAmt));
                                            overpayfinalDet.setTransaction_id(accTypeId);
                                            overpayfinalDet.setDescription(description);

                                            String pay_mode=request.getParameter("r11");

                                            if(pay_mode!=null && pay_mode.trim().length()>0){
                                                if(pay_mode.equalsIgnoreCase("check")){    
                                                    overpayfinalDet.setPayment_mode("check");
                                                    overpayfinalDet.setActive_status(false);
                                                }
                                            }
                                            // Setting staff user_id and ip_address 
                                            overpayfinalDet.setStaff_user_id(staff_user_id);
                                            overpayfinalDet.setStaff_ip_address(reqIp);                                        
                                            //End Setting staff_user_id and ip_address                                             
                                            Debug.print("B4 insert \n"+ overpayfinalDet.toString());
                                            boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                        }                            
                     }
                       if(checkAmount>=totalAmount){
                                Debug.print("inside the condition totAmt > checkAmt:");
                                cardStatus = true;
                                output = remote.updateRequestStatusForAdmin(reqId1);
                                System.out.print("output:" + output);
                                request.setAttribute("activation","Active");
                            }else{
                                request.setAttribute("activation","null");
                            }
                       }
                      else{
                            request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        }
                     }
                     
                    accTxnAddPay.setPayment_id(paymentId);
                    accTxnPhoneChg.setPayment_id(paymentId);
                    
                    accTxnAddPay.setActive_status(false);
                    accTxnPhoneChg.setActive_status(false);
                    
                    accTxnPhoneChg.setPayment_mode("check");
                    accTxnAddPay.setPayment_mode("check");
                    
                    String logBy = "user";
                    if(session.getAttribute("loggedBy")!=null)
                    {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;

                            accTxnAddPay.setStaff_user_id(staff_user_id);
                            accTxnAddPay.setStaff_ip_address(reqIp);
                            accTxnPhoneChg.setStaff_user_id(staff_user_id);
                            accTxnPhoneChg.setStaff_ip_address(reqIp);                            
                    }
                    
                    boolean chgStat = mahaRemote.insertAccountTxnDetails(accTxnAddPay);
                    Debug.print("insertAccountTxnDetails(accTxnAddPay) chgStat for add owner/rider txn :"+chgStat);
                    
                    if(phoneCharge!=null)
                    {
                        boolean stat = mahaRemote.insertAccountTxnDetails(accTxnPhoneChg);
                        Debug.print("insertAccountTxnDetails(accTxnPhoneChg) stat for admin Phone Charge txn :"+stat);
                    }
                    
                    
                     if(checkAmount!=0){
                         if(checkAmount>=totalAmount){
                             Debug.print("Update Reconile Status in servlet ");
                                if(paymentId!=null || paymentId.trim().length()!=0){
                                   boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                   Debug.print("Update Status "+Update_Status);
                                }
                            }
                    }
                     
                      //result = remote.createHorseRiderAndOwnerDetails(horseMemberId, riderId, ownerUserId, objOwnerVO,status,cardStatus,reqId1);
                      Debug.print("updBla:" + updBal);
                      Debug.print("output:" + output);
                      Debug.print("inside the result function");
                      String horseRelId[] = (String[])remote.getAllEmailIds(horseMemberId);
                      Debug.print("horseRelId:::" + horseRelId);   
                     Debug.print("Result  Payment Bean" + result1);
                     if(result1 == true && result == true){
                         
                         
                        String riderFName="";
                        String riderLName="";
                        String OwnerFName="";
                        String OwnerLName="";
                        String OwnerMailId="";
                        String OwnerStatusId="";
                        String OwnerStatusName="";
                        
                        String relaId="";
                        String relaTypeName="";
                        String preFName="";
                        String preLName="";
                        String OwnerFName1="";
                        String OwnerLName1="";
                        String OwnEmail1="";
                        String preOwnerEmail="";
                        String preOwnerStatus="";
                        String OwnStatus="";
                                   
                        String riderEmailId=request.getParameter("addREmail");
                        riderFName=request.getParameter("additionalRider");
                        riderLName=request.getParameter("lastRider");
                    String primaryOwnerEmailId=remote.getPrimaryOwnEmailId(horseMemberId);
                    Debug.print("primaryOwnerEmailId :"+primaryOwnerEmailId);
                    if(ownerSecLogName!=null && ownerSecLogName.length()!=0){
                        Debug.print("ownerSecLogName in log name section" + ownerSecLogName);
                        ArrayList objOwnerDet1 = new ArrayList();
                        objOwnerDet1 = remote.getOwnerDetailsForMail(ownerSecLogName);
                        if(objOwnerDet1!=null && objOwnerDet1.size()!=0){
                            Debug.print("objOwnerDet1::: ***** " + objOwnerDet1);
                          Iterator itr1 = objOwnerDet1.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                           OwnerFName=objOwnerInfo1.getOwnerLogName();
                           OwnerFName=objOwnerInfo1.getOwnerFirstName();
                           OwnerLName=objOwnerInfo1.getOwnerLastName();
                           OwnerMailId=objOwnerInfo1.getOwnerEmail();
                           OwnerStatusId=objOwnerInfo1.getStatusId();
                           OwnerStatusName=objOwnerInfo1.getStatusName1();
                          }  
                        }
                        }else{ 
            
                        ArrayList objOwnerDet = new ArrayList();
                        objOwnerDet = remote.getUserDets(ownerSecMemId);
                        if(objOwnerDet!=null && objOwnerDet.size()!=0){
                          Debug.print("objOwnerDet::: &&&&" + objOwnerDet);
                          Iterator itr = objOwnerDet.iterator();
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();
                           OwnerFName=objOwnerInfo.getOwnerFirstName();
                           Debug.print("OwnerFName:::" + OwnerFName);
                           OwnerLName=objOwnerInfo.getOwnerLastName();
                           Debug.print("OwnerLName:::" + OwnerLName);
                           OwnerMailId=objOwnerInfo.getOwnerEmail(); 
                           Debug.print("OwnerMailId:::" + OwnerMailId);
                           OwnerStatusId=objOwnerInfo.getStatusId();
                           OwnerStatusName=objOwnerInfo.getStatusName1();
                           Debug.print("OwnerStatusName:::" + OwnerStatusName);
                          }  
                        }                    
                        }
                        
                        ArrayList objPreOwnRidDet = new ArrayList();
                        objPreOwnRidDet = remote.getPreOwnerRiderDetails(horseMemberId);
                        String ownerNames="";
                        String riderNames="";
                        if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0){
                        Debug.print("objPreOwnRidDet::: &&&&" + objPreOwnRidDet);
                        Iterator itr = objPreOwnRidDet.iterator();
                        int i=0;
                        while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                           relaId=objOwnerInfo.getRelationTypeId();
                           Debug.print("relaId:::" + relaId);
                           relaTypeName=objOwnerInfo.getRelationTypeName();
                           Debug.print("relaTypeName:::" + relaTypeName);
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){
                                preFName=objOwnerInfo.getPreOwnerFName();
                                OwnerFName1=preFName; 
                                preLName=objOwnerInfo.getPreOwnerLName();
                                OwnerLName1=preLName;
                                preOwnerEmail=objOwnerInfo.getPreEmail();
                                OwnEmail1=preOwnerEmail;
                                preOwnerStatus=objOwnerInfo.getRelationStatus();
                                OwnStatus=preOwnerStatus;
                                Debug.print("OwnEmail1 :"+OwnEmail1+" :"+i);
                                i++;
                           } 
                           
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){  
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preLName=objOwnerInfo.getPreOwnerLName();
                                String tempOwnerNames=preFName+" "+preLName;
                                if(i==0){
                                ownerNames=tempOwnerNames;
                                }else{
                                ownerNames=ownerNames+","+tempOwnerNames;    
                                }
                          }
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Rider"))){  
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preLName=objOwnerInfo.getPreOwnerLName();
                                String tempRiderNames=preFName+" "+preLName;
                                if(i==0){
                                riderNames=tempRiderNames;
                                }else{
                                riderNames=riderNames+","+tempRiderNames;    
                                }
                           }
  
                          }  
                        }
                        
                       String textLimit="This horse can enter events up to and including Training level";
                       String textFull="This horse can enter any level at which it is properly qualified";
                       
                      String membershipName1=""; 
                     if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                        
                        membershipName1 = membershipName+" "+textLimit;      
                        }
                     else if(membershipName!=null && membershipName.equalsIgnoreCase("Full")){
                        
                        membershipName1 = membershipName+" "+textFull;      
                        }
                     else{
                         membershipName1 =membershipName;  
                     }      
                        
                    String OwnEmail="";    
                    if(OwnStatus!=null && OwnStatus.length()!=0 && OwnStatus.equalsIgnoreCase("Active") && OwnEmail1!=null && OwnEmail1.length()!=0){
                         // to all the active owners.
                         if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0 ){
                         Debug.print("commonOwners::: &&&&" + objPreOwnRidDet);
                         Iterator itr = objPreOwnRidDet.iterator();
                         int i=0;
                         
                         while(itr.hasNext()) {
                         Debug.print("Inside While");
                         HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                         String relaTypeName1=objOwnerInfo.getRelationTypeName();
                         Debug.print("relaTypeName1 :"+relaTypeName1);
                         if(relaTypeName1!=null && (relaTypeName1.equalsIgnoreCase("Owner"))){
                                
                                OwnEmail=objOwnerInfo.getPreEmail();
                                Debug.print("Active owner :" + OwnEmail);
                                String toMailIds[] = {OwnEmail};
                                EmailContent email=new EmailContent();
                                email.setTo(toMailIds);
                                email.setFrom("anandv@digiblitz.com");
                                email.setSubject("Horse Addition Owner/Rider");
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
                                "<p>"+ OwnerFName+" "+ OwnerLName+" has been added as an owner of "+competitionName+".<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ competitionName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ownerNames+"<br/>"+
                                "Rider(s):"+riderNames+"<br/>"+                               
                                "<p>Additional information regarding "+ competitionName +" can be viewed by logging in to the HLC Online Services.<p>"+ 
                                "<p>Thank you.<p><br />"+
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
                        Debug.print("Email sent sucessfully :"+i);
                        i++;
                       }
                       }
                       }
                      
                       }
                    // to the current added rider and owner.  
                     if(riderEmailId!=null && riderEmailId.length()!=0 && primaryOwnerEmailId!=null && primaryOwnerEmailId.length()!=0){
                        Debug.print("primary Owner EmailId inside if condition" + primaryOwnerEmailId);
                        Debug.print("added rider inside if condition" + riderEmailId);
                        String toMailIds[] = {riderEmailId,primaryOwnerEmailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Addition Owner/Rider");
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
                                "<p>"+ riderFName+" "+riderLName+" has been added as a rider of "+competitionName+".<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ competitionName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ownerNames+"<br/>"+
                                "Rider(s):"+riderNames+"<br/>"+                               
                                "<p>Additional information regarding "+ competitionName +" can be viewed by logging in to the HLC Online Services. Thank you.<p>"+                          
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
                        
                        request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        request.setAttribute("horseMemberId",horseMemberId);
 
                            request.setAttribute("reqId1",reqId1);   
                                return mapping.findForward("HorseRidOwnSuccess");
                             } 
                     
                             else{
                                 Debug.print("Sorry unable to insert due to some bugs");
                             }
                    
                         
                     } 
        }
      }
       catch (Exception ex) {
           System.out.println("Exception occurs in Adding owner and rider details:" + ex.getMessage());
        }
         
       return null;
    }
}
