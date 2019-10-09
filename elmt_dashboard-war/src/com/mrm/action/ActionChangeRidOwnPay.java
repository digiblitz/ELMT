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
  *  Version         : 1.23
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
import java.util.regex.*;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.*;
import java.text.*;
import org.apache.struts.util.MessageResources;
import com.mrm.actionform.FormAddRidOwnPay;
import javax.servlet.http.*;
import java.net.*;
import com.hlcform.stateless.*;

public class ActionChangeRidOwnPay extends Action {
    
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
            
            HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
            HLCHorseRegisterationVO horseRegVO  = new HLCHorseRegisterationVO();
            HLCHorseDescriptionVO objHorseDesc = new HLCHorseDescriptionVO();
            HLCHorseUserVO objOwnerVO = new HLCHorseUserVO();
            HLCHorseUserVO objAddOwnerVO = new HLCHorseUserVO();
            HLCRequestHorseDetVO reqHrDetVO = new HLCRequestHorseDetVO();
            FormAddRidOwnPay fbean=(FormAddRidOwnPay)form;
            System.out.println("InsertHorseRegActionForm ="+ fbean);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
            
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
        
            String process = request.getParameter("process");
            Debug.print("process function:" + process);
            
            String reqIp=request.getRemoteAddr();
            Debug.print(" Request IP :"+reqIp);
            
            if(process==null || process.trim().length()==0){
                return mapping.findForward("login");
            }
            
            if(process!=null && process.equalsIgnoreCase("dispdet")){
                HLCHorseRegisterationVO HorseDet = null;
                String horseMemberId = (String)request.getParameter("horseMemberId");
                HorseDet = (HLCHorseRegisterationVO)remote.getHorseDetailsByHorseMemberId(horseMemberId);
                String amount = remote.getHorseEditCharge();
                String horsesertypVect[] = (String[])remote.getHorseServiceCharge();
                String typId = remote.selectHorseServiceTypeId("Horse / Rider / Owner Change");
                
                String firstName1 = (String)request.getParameter("firstName1");
                String relationship_type_name = (String)request.getParameter("relationship_type_name1");
                String relationship_status = (String)request.getParameter("relationship_status1");
                
                request.setAttribute("serviceTypId",typId);
                request.setAttribute("horsesertypVect", horsesertypVect);
                request.setAttribute("HorseDet",HorseDet);
                request.setAttribute("amount",amount);
                request.setAttribute("firstName1",firstName1);
                request.setAttribute("relationship_type_name",relationship_type_name);
                request.setAttribute("relationship_status",relationship_status);
                
                Debug.print("amount:"+amount);
                Debug.print("HorseDet:"+HorseDet);
                Debug.print("horsesertypVect:"+horsesertypVect);
                return mapping.findForward("goToAddRidOwn");
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
                String ownerId = "";
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
                String horseName = "";
                String horseRegisterName ="";
                String riderId = "";
                String companyName="";
                
                
                String horseRegType="";            
                String ownerSecLogName="";
                String ownerSecMemId="";
                String userName="";
                
                System.out.println("ownerUserId :" + request.getParameter("ownerUserId"));
                System.out.println("addOwnerUserId :" + request.getParameter("addOwnerUserId"));
                Date dob=null;
                Date dob1=null;
                boolean status = false;
                boolean ridStatus = false;
                String loginName ="";
                String userId =(String)session.getAttribute("userId");
                
                String tempFName=request.getParameter("firstName1");
                String tempRelationship_type_name=request.getParameter("relationship_type_name");
                String tempRelationship_status=request.getParameter("relationship_status");
                
                //-------------------------------HORSE OWNER INFORMATION-------------------------------------------
                String serviceType = "";
                try{
                    String finalPrimaryPh="";
                    String finalFax="";
                    
                    
                    String phoneCharge=request.getParameter("phoneCharge");
                    String serviceStatus = request.getParameter("servicePrice");
                    
                    String servicePrice ="";
                    if(phoneCharge!=null && phoneCharge.trim().length()!=0){
                        if(serviceStatus!=null){
                            serviceType = serviceStatus;
                        } else{
                            serviceType = null;
                        }
                    }
                    
                    System.out.print("serviceType" + serviceType);
                    System.out.print("serviceStatus" + serviceStatus);
                    System.out.print("phoneCharge" + phoneCharge);
                    
                    horseMemberId = request.getParameter("horseMemberId");
                    riderId = request.getParameter("arhlcNo");
                    regFor= request.getParameter("regAddFor");
                    ownerUserId = request.getParameter("addOwnerUserId");
                    if(request.getParameter("horseName")!=null || request.getParameter("horseName").trim().length()!=0){
                        horseName=request.getParameter("horseName");
                    } else{
                        horseName=null;
                    }
                    if(request.getParameter("horseRegisterName")!=null || request.getParameter("horseRegisterName").trim().length()!=0){
                        horseRegisterName=request.getParameter("horseRegisterName");
                    } else{
                        horseRegisterName=null;
                    }
                    Debug.print("regFor:" + regFor);
                    Debug.print("horseName:" + horseName);
                    Debug.print("horseRegisterName:" + horseRegisterName);
                    Debug.print("horseMemberId:" + horseMemberId);
                    Debug.print(" Session userId:" + userId);
                    
                    if((riderId==null || riderId.trim().length()==0)  && (regFor==null || regFor.trim().length()==0) && (horseRegisterName==null || horseRegisterName.trim().length()==0) && (horseName==null || horseName.trim().length()==0)){
                        
                        Debug.print("inside the loop riderId:" + riderId);
                        Debug.print("inside the loop regFor:" + regFor);
                        HLCHorseRegisterationVO HorseDet = null;
                        String horMemberId = (String)request.getParameter("horseMemberId");
                        HorseDet = (HLCHorseRegisterationVO)remote.getHorseDetailsByHorseMemberId(horMemberId);
                        request.setAttribute("HorseDet",HorseDet);
                        String amount = remote.getHorseEditCharge();
                        request.setAttribute("amount",amount);
                        return mapping.findForward("goToAddRidOwn");
                    } else{
                        
                        if(regFor!=null){
                            
                            ridStatus = true;
                            
                            if(regFor.equals("mem1")){
                                
                                userNameOne = request.getParameter("ownerUseaNoTwo");
                                ownerSecMemId=userNameOne;
                                Debug.print("ownerSecMemId in servlet 1 : "+ ownerSecMemId);
                                ownerUserId = (String)userremote.getUserIdBasedOnMemberId(userNameOne);
                                Debug.print("ownerUserId from another method: "+ ownerUserId);
                            } else if(regFor.equals("rid")){
                                Debug.print("rid method: ");
                                userName = request.getParameter("userNameId2");
                                ownerSecMemId=userName;
                                Debug.print("ownerSecMemId in servlet 2: "+ ownerSecMemId);
                                firstName = request.getParameter("firstNameId2");
                                lastName = request.getParameter("lastNameId2");
                            } else if(regFor.equals("acc")){
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
                                } else if(compAddFor.equals("no")){
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
                                            } catch(Exception e){
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
                                            } catch(Exception e){
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
                            } else{
                                objOwnerVO.setUserId(null);
                            }
                        } else{
                            ridStatus = false;
                        }
                    }
                    
                } catch(Exception owner){
                    System.out.println("Exception occurs in owner insertion part:" + owner.getMessage());
                }
                
                System.out.println("======================================================");
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
                Debug.print("Successfully skipped Owner information: Add");
                boolean result = false;
                String sesUserId = (String)session.getAttribute("userId");
                Debug.print("Ins ActionChangeRidOwnPay Ses Usr ID"+session.getAttribute("userId"));
                String emailId = (String)session.getAttribute("emailId");
                // Get client's IP address
                String addr = request.getRemoteAddr(); // 123.123.123.123
                Debug.print("Port Value:" + addr);
                int  CcExpMonth = 0;
                int CcExpYear = 0;
                int CcCvvid =0;
                double totalAmount =0;
                float checkAmount = 0;
                String CcNumber ="0";
                String checkNumber="0";
                Date check_date = null;
                Date comp_date = null;
                String ccType=null;
                String ccName=null;
                String nameoncheck = null;
                String bankname = null;
                String  r11 = fbean.getR11();
                System.out.print("Check Status :" + r11);
                
                String serviceTypId = request.getParameter("serviceTypId");
                Debug.print("serviceTypId in servlet:"+serviceTypId);
                
                HLCAccTxnTypeDetailVO accTxnTyp = new HLCAccTxnTypeDetailVO();
                accTxnTyp = mahaRemote.getPhoneServChgAccTxnTypeDetails(serviceTypId);
                Debug.print("getPhoneServChgAccTxnTypeDetails() for change horse rider txn :"+accTxnTyp.toString());
                
                ArrayList txnDetails = new ArrayList();
                HLCAccTransactionVO accTxnChangePay = new HLCAccTransactionVO();
                
                //accTxnChangePay.setUser_id(sesUserId);
                accTxnChangePay.setAccount_no(accTxnTyp.getAccount_no());
                accTxnChangePay.setAccount_type("Income");
                accTxnChangePay.setActive_status(false);
                accTxnChangePay.setClass_Typ(accTxnTyp.getClass_name());
                //accTxnChangePay.setIp_address(addr);
                accTxnChangePay.setItem_no(accTxnTyp.getItem_no());
                String changeAmt = request.getParameter("cngAmt");
                
                accTxnChangePay.setAmount(Float.parseFloat(changeAmt));
                
                accTxnChangePay.setSub_account_no(accTxnTyp.getSub_account_no());
                accTxnChangePay.setDescription(accTxnTyp.getTransaction_name());    
                
                String phoneCrgId = request.getParameter("servicePrice");
                String phoneCharge = request.getParameter("phoneCharge");
                
                Debug.print("phoneCrgId for admin service type id :"+phoneCrgId);
                Debug.print("phoneCharge for admin service type checkbox :"+phoneCharge);
                
                HLCAccTransactionVO accTxnPhoneChg = new HLCAccTransactionVO();
                
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
                
                if(r11.equals("card")){
                    if(fbean.getCcNumber()==null || fbean.getCcNumber().equals("")){
                        CcNumber = "0";
                    } else{
                        CcNumber = fbean.getCcNumber();
                    }
                    if(fbean.getCcName()==null || fbean.getCcName().equals("")){
                        ccName ="";
                    } else {
                        ccName=fbean.getCcName();
                    }
                    if(!(fbean.getCcType().equals("")) && fbean.getCcType()!=null){
                        ccType = fbean.getCcType();
                    }
                    if(fbean.getCcExpMonth()==null || fbean.getCcExpMonth().equals("")){
                        CcExpMonth =0;
                    } else {
                        CcExpMonth=Integer.parseInt(fbean.getCcExpMonth());
                    }
                    if(fbean.getCcExpYear()==null || fbean.getCcExpYear().equals("")){
                        CcExpYear =0;
                    } else{
                        CcExpYear = Integer.parseInt(fbean.getCcExpYear());
                    }
                    if(fbean.getCcCvvid()==null || fbean.getCcCvvid().equals("")){
                        CcCvvid =0;
                    } else{
                        CcCvvid = Integer.parseInt(fbean.getCcCvvid());
                    }
                    check_date= null;
                    objPayment.setPaymentStatus("Card");
                }
                
                if(r11.equals("check")){
                    if(fbean.getCheckNumber()==null || fbean.getCheckNumber().equals("")){
                        checkNumber="0";
                    } else{
                        checkNumber= fbean.getCheckNumber();
                    }
                    if(fbean.getCheckDate().equals("")){
                        check_date= null;
                    } else{
                        check_date =(Date)sdf.parse(fbean.getCheckDate());
                    }
                    objPayment.setPaymentStatus("Check");
                    
                }
                String tempAmount = request.getParameter("totalAmount");
                String tempCheckAmount = request.getParameter("chkBalAmt");
                if(tempCheckAmount==null || tempCheckAmount==""){
                    checkAmount=0;
                } else{
                    checkAmount = Float.parseFloat(tempCheckAmount);
                }
                System.out.println("tempAmount1" + tempAmount);
                if(tempAmount==null || tempAmount==""){
                    totalAmount=0;
                } else{
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
                objPayment.setPaymentDate(new Date());
                //objPayment.setCheckAmount(checkAmount);
                objPayment.setIpAddress(addr);
                
                if(r11.equals("card")){
                   /* //Suresh Here Nova Refresh code======================================
                    int sCnt = 0;
                    String sessionCount = (String)session.getAttribute("sessCnt");
                    Debug.print("SessionNovaCount Value:" + sessionCount);
                    
                    if(sessionCount!=null){
                        sCnt = Integer.parseInt(sessionCount);
                        sCnt++;
                    }
                    else{
                        sCnt++;
                        session.setAttribute("sessCnt",String.valueOf(sCnt));
                    }
                    
                    Debug.print("Final SessionNovaCount sCnt Value:" + sCnt);
                    
                    if(sCnt!=1){
                        Debug.print("This is not a valid NOVA Call");
                        return mapping.findForward("repeatNovaPage");
                    }
                    else{
                        Debug.print("This is valid NOVA Call");
                    }
                    //Nova Refresh ======================================*/
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
                    System.out.println("expYear:" + expYear);
                    String expDate = expMon + expYear;
                    request.setAttribute("expDate",expDate);
                    request.setAttribute("amount",String.valueOf(totalAmount));
                    
                    boolean horseStatus = remote.isHorseExist(horseName);
                    Debug.print("horseStatus:" + horseStatus);
                    
                    request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                    String paymentId = remote.getNextId();
                    Debug.print("paymentId in card:" + paymentId);
                    objPayment.setPaymentId(paymentId);
                    session.setAttribute("regFor",regFor);
                    session.setAttribute("amount",String.valueOf(totalAmount));
                    session.setAttribute("paymentId",paymentId);
                    
                    
                    session.setAttribute("horseName",horseName);
                    session.setAttribute("horseRegisterName",horseRegisterName);
                    
                    session.setAttribute("reqHrDetVO",reqHrDetVO);
                    session.setAttribute("objOwnerVO",objOwnerVO);
                    session.setAttribute("horseMemberId",horseMemberId);
                    session.setAttribute("riderId",riderId);
                    session.setAttribute("status",String.valueOf(status));
                    session.setAttribute("ownerUserId",ownerUserId);
                    session.setAttribute("serviceType",serviceType);
                    
                    
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
                        String preOwnerEmail="";
                        String preRiderEmail="";
                        String preOwnerFName="";
                        String preOwnerLName="";
                        String preOwnEmail="";
                        String preRiderFName="";
                        String preRiderLName="";
                        String preRidEmail="";
                        String OwnerLogName="";
                     
                        String membershipName=request.getParameter("membertype");                                          
                        String riderEmailId=request.getParameter("addREmail");
                        riderFName=request.getParameter("additionalRider");
                        riderLName=request.getParameter("lastRider");
                        String competitionName=request.getParameter("competitionName");
                    
                    if(ownerSecLogName!=null && ownerSecLogName.length()!=0){
                        Debug.print("ownerSecLogName in log name section" + ownerSecLogName);
                        ArrayList objOwnerDet1 = new ArrayList();
                        objOwnerDet1 = remote.getOwnerDetailsForMail(ownerSecLogName);
                        if(objOwnerDet1!=null && objOwnerDet1.size()!=0){
                            Debug.print("objOwnerDet1::: ***** " + objOwnerDet1);
                          Iterator itr1 = objOwnerDet1.iterator();
                          while(itr1.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo1 =(HLCHorseRegListVO)itr1.next();
                           OwnerLogName=objOwnerInfo1.getOwnerLogName();
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
                          }  
                        }                       
                        }
                                     
                        ArrayList objPreOwnRidDet = new ArrayList();
                        objPreOwnRidDet = remote.getPreOwnerRiderDetails(horseMemberId);
                        session.setAttribute("commonOwnersRiders",objPreOwnRidDet);
                        String ownerNames="";
                        String riderNames="";
                        if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0){
                          Iterator itr = objPreOwnRidDet.iterator();
                          int i=0;
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                           relaId=objOwnerInfo.getRelationTypeId();
                           Debug.print("relaId:::" + relaId);
                           relaTypeName=objOwnerInfo.getRelationTypeName();
                           Debug.print("relaTypeName:::" + relaTypeName);
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Owner"))){
                               preFName=objOwnerInfo.getPreOwnerFName();
                                preOwnerFName=preFName; 
                                preLName=objOwnerInfo.getPreOwnerLName();
                                preOwnerLName=preLName;
                                preOwnerEmail=objOwnerInfo.getPreEmail();
                                preOwnEmail=preOwnerEmail;
                           } 
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Rider"))){    
                               String preRidFName=objOwnerInfo.getPreOwnerFName();
                                preRiderFName=preRidFName; 
                               String preRidLName=objOwnerInfo.getPreOwnerLName();
                                preRiderLName=preRidLName; 
                               preRiderEmail=objOwnerInfo.getPreEmail();
                                preRidEmail=preRiderEmail;
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
                           i++;
                          }  
                        }    
                       String textFull=" This horse can enter any level at which it is properly qualified";
                       String textLimit=" This horse can enter events up to and including Training level"; 
                       String membershipName1="";
                      
                      if(membershipName!=null && membershipName.equalsIgnoreCase("Full")){
                        
                        membershipName1 = membershipName+" "+textFull;      
                        }
                     
                      else if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                        
                        membershipName1 = membershipName+" "+textLimit;      
                        }
                      else{
                         membershipName1 =membershipName;  
                       }                                     
  
                      String horseName1=request.getParameter("horseName");    
                    session.setAttribute("ownerNames",ownerNames);
                    session.setAttribute("riderNames",riderNames);  
                    session.setAttribute("membershipName",membershipName);
                    session.setAttribute("riderEmailId",riderEmailId);
                    session.setAttribute("riderFName",riderFName);
                    session.setAttribute("riderLName",riderLName);
                    session.setAttribute("competitionName",competitionName);
                    session.setAttribute("OwnerFName",OwnerFName);
                    session.setAttribute("OwnerLName",OwnerLName);
                    session.setAttribute("OwnerMailId",OwnerMailId);
                    session.setAttribute("OwnerStatusId",OwnerStatusId);
                    session.setAttribute("OwnerStatusName",OwnerStatusName);
                    session.setAttribute("preOwnerFName",preOwnerFName);
                    session.setAttribute("preOwnerLName",preOwnerLName);
                    session.setAttribute("preOwnEmail",preOwnEmail);
                    session.setAttribute("preRiderFName",preRiderFName);
                    session.setAttribute("preRiderLName",preRiderLName);
                    session.setAttribute("preRidEmail",preRidEmail);
                    session.setAttribute("horseName1",horseName1);
                    session.setAttribute("membershipName1",membershipName1);
                    session.setAttribute("relaTypeName",relaTypeName);
                    session.setAttribute("horseStatus",String.valueOf(horseStatus));
             
                    
                    StringBuffer reqURL = request.getRequestURL();
                    int lastIndex = reqURL.lastIndexOf("/") ;
                    String url ="";
                    if(lastIndex!=-1){
                        url = reqURL.substring(0,lastIndex+1);
                    }
                    String tmpNova  = mr.getMessage("changerid.nova");
                    String nova = url + tmpNova;
                    Debug.print("succsssullll from app file nova........::" + nova);
                    Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                    //Suresh Here ==================================================
                  /*  
                    String str = "ssl_merchant_id=" + URLEncoder.encode("408871");
                    str = str + "&ssl_user_id=" + URLEncoder.encode("website");
                    str = str + "&ssl_pin=" + URLEncoder.encode("1JBJ01");
                    str = str + "&ssl_show_form=" + URLEncoder.encode("false");
                    str = str + "&ssl_test_mode=" + URLEncoder.encode("true");
                    str = str + "&ssl_email=" + URLEncoder.encode(emailId);
                    str = str + "&ssl_card_number=" + URLEncoder.encode(String.valueOf(CcNumber));
                    str = str + "&ssl_exp_date=" + URLEncoder.encode(expDate);
                    
                    str = str + "&ssl_result_format=" + URLEncoder.encode("HTML");
                    str = str + "&ssl_receipt_decl_method=" + URLEncoder.encode("REDG");
                    str = str + "&ssl_receipt_decl_get_url=" + URLEncoder.encode(nova);
                    
                    
                    
                    str = str + "&ssl_receipt_apprvl_method=" + URLEncoder.encode("REDG");
                    str = str + "&ssl_receipt_apprvl_get_url=" + URLEncoder.encode(nova);
                    str = str + "&ssl_receipt_link_text=" + URLEncoder.encode("Continue");
                    str = str + "&ssl_amount=" + URLEncoder.encode(String.valueOf(totalAmount));
                    str = str + "&ssl_cvv2=" + URLEncoder.encode("present");
                    str = str + "&ssl_cvv2cvc2=" + URLEncoder.encode(request.getParameter("ccCvvid"));
                    str = str + "&ssl_avs_address=" + URLEncoder.encode("123 Main St.");
                    str = str + "&ssl_avs_zip=" + URLEncoder.encode("01234");
                    
                    Debug.print("Total Parameter String:" + str);
                    URL u = new URL("https://www.viaKLIX.com/process.asp");
                    Debug.print("This is come from After URL");
                    URLConnection uc = u.openConnection();
                    uc.setDoOutput(true);
                    Debug.print("This is come from After setDOOut");
                    uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    PrintWriter pw = new PrintWriter(uc.getOutputStream());
                    Debug.print("This is come from After Print writer ");
                    pw.println(str);
                    pw.close();
                    Debug.print("This is come from After Close ");
                    */
                    //Suresh Here ==================================================
                    
                   /* BufferedReader in = new BufferedReader(
                            new InputStreamReader(uc.getInputStream()));
                    String res = in.readLine();
                    in.close();
                    Debug.print("This is come from After Buffer Close " + res);
                    */
                    
                    accTxnChangePay.setPayment_id(paymentId);
                    accTxnPhoneChg.setPayment_id(paymentId);
                    
                    accTxnPhoneChg.setPayment_mode(ccType);
                    accTxnChangePay.setPayment_mode(ccType);

                    accTxnChangePay.setActive_status(false);
                    accTxnPhoneChg.setActive_status(false);
                    
                    txnDetails.add(accTxnChangePay);
                    
                    if(phoneCharge!=null)
                    {
                        txnDetails.add(accTxnPhoneChg);
                    }
                    String inVoiceId1 = "1";
                    session.setAttribute("txnDetails",txnDetails);
                    String inVId = request.getParameter("inVoiceId");
                System.out.println("inVId in servlet from request: " + inVId);
                int intVId = 0;
                if (inVoiceId1.equalsIgnoreCase("0")) {
                    intVId = 1;
                } else {
                    intVId = 1;
                }
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

                request.setAttribute("purpose", "horseriderownerchange");
                //intVId++; 
                request.setAttribute("intVId", String.valueOf(intVId));
                System.out.println("intVId in servlet InsertHorseRegAction &&&&&&&" + intVId);

                return mapping.findForward("testing");
                    //request.setAttribute("nova",nova);
                    //return mapping.findForward("novaPage");
                } else{
                    String paymentId = remote.getNextId();
                    Debug.print("paymentId in check:" + paymentId);
                    objPayment.setPaymentId(paymentId);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                    
                    boolean horseStatus = remote.isHorseExist(horseName);
                    Debug.print("horseStatus:" + horseStatus);
                      boolean reqStatus1=false;             
                    boolean result1  = objPaySessRemote.createPayment(objPayment);
                    String requestId = remote.editHorseRiderAndOwnerDetails(horseMemberId,horseName,horseRegisterName,userId,riderId,ownerUserId,ridStatus,objOwnerVO,status,paymentId,Float.parseFloat(tempAmount),serviceType,reqStatus1);
                    if (requestId != null && requestId.trim().length() != 0) {

                        ArrayList chngHorseDetails = remote.getHorseOwnRidChangeDetails(requestId);

                        String horseMemId = "";
                        String reqOwnerId = "";
                        String reqHorseName = "";
                        String reqHorseRegName = "";
                        if (chngHorseDetails != null && chngHorseDetails.size() != 0) {
                            Iterator itr = chngHorseDetails.iterator();
                            while (itr.hasNext()) {
                                String[] s = (String[]) itr.next();
                                horseMemId = s[0];
                                String payId = s[1];
                                reqOwnerId = s[2];
                                String reqRiderId = s[3];
                                reqHorseName = s[4];
                                reqHorseRegName = s[5];
                                String requestBy = s[6];
                                String reqAmt = s[7];
                                String reqStat = s[8];
                                String reqDt = s[9];
                                String addPayStat = s[10];

                            }

                        }

                        boolean output = remote.updateRequestHorseDetailsByAdmin(horseMemId, requestId, reqHorseName, reqHorseRegName, riderId, reqOwnerId, objOwnerVO, status, ridStatus, request.getParameter("competitionName"));
                        Debug.print("Final output:" + output);
                    }        
                    
                    if(horseStatus==true){
                    String toMailIds[] = {emailId};
                    EmailContent email=new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("anandv@digiblitz.com");
                    email.setSubject("Horse Member Matching");
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
                            "<span class=\"boldTxt\">Dear User</span>,<br /><br />"+                           
                            "<p>Your horse may already be registered.<p>"+
                            "<p>In an effort to reduce the number of duplicate horse registrations, the HLC "+
                            "would like to inform you that the horse name <strong>"+ horseName +" </strong>is already in use in our database."+
                            "Please verify that your horse <strong>"+ horseName +"</strong> has not be previously registered by a former owner or rider."+
                            "Please log in to the HLC Online Services, and access Horse Registration. Please search for the horse by name to determine if "+
                            "<strong>"+ horseName +"</strong> has been registered before. If your horse has been registered in the past, please " +
                            "contact the HLC Sports Services Department at 703-779-0440.<p>"+
                            "<p>If you have confirmed that your horse has not been registered with the HLC previously, no further action is needed.<p><br />"+
                            "Thank You <br />"+                            
                            "<p>Sincerely," +
                            "<p>HLC Staff <p></td>"+
                            "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                            "</tr>"+
                            "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                            "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                            "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                            "</tr>"+
                            " </table>"+
                            "</td></tr>"+
                            "</table>";
                    email.setBody(content);
                    EmailEngine emailEngine = new EmailEngine();
                    boolean emailFlag = emailEngine.sendMimeEmail(email);
                    Debug.print("Email sent sucessfully :"+emailFlag);
                }
 
                    boolean updBal =false;
                    if(checkAmount!=0){
                        updBal = remote.updatePendingAmount(sesUserId,paymentId,checkAmount);
                    }
                    Debug.print("updBla:" + updBal);
                    
                    accTxnChangePay.setPayment_id(paymentId);
                    accTxnPhoneChg.setPayment_id(paymentId);
                    
                    accTxnChangePay.setActive_status(false);
                    accTxnPhoneChg.setActive_status(false);
                    
                    accTxnPhoneChg.setPayment_mode("check");
                    accTxnChangePay.setPayment_mode("check");
                    // Setting staff user_id and ip_address 
                    String logBy = "user";
                    if(session.getAttribute("loggedBy")!=null)
                    {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;

                            accTxnChangePay.setStaff_user_id(staff_user_id);
                            accTxnChangePay.setStaff_ip_address(reqIp);
                            accTxnPhoneChg.setStaff_user_id(staff_user_id);
                            accTxnPhoneChg.setStaff_ip_address(reqIp);                            
                    }
                    //End Setting staff_user_id and ip_address
                    boolean chgStat = mahaRemote.insertAccountTxnDetails(accTxnChangePay);
                    Debug.print("insertAccountTxnDetails(accTxnPhoneChg) chgStat for change owner rider txn :"+chgStat);
                    
                    if(phoneCharge!=null)
                    {
                        boolean stat = mahaRemote.insertAccountTxnDetails(accTxnPhoneChg);
                        Debug.print("insertAccountTxnDetails(accTxnPhoneChg) stat for admin Phone Charge txn :"+stat);
                    }
                    
                // Making the active Status and reconcile status as true on full payment
                    String chckAmount = request.getParameter("chkBalAmt");
                    String totAmount = request.getParameter("totalAmount");
                    Debug.print("Check Amount is in Admin Pay "+chckAmount);
                    Debug.print("Total Amount is in Admin Pay "+totAmount);
                    if(chckAmount!=null && totAmount!=null){
                        if(chckAmount.trim().length()!=0 && totAmount.trim().length()!=0){
                            double chkAmt = Double.parseDouble(chckAmount);
                            double totAmt = Double.parseDouble(totAmount);
                            Debug.print("Check Amount is in Admin Pay "+chkAmt);
                            Debug.print("Total Amount is in Admin Pay "+totAmt);
                            double extraAmt = chkAmt - totAmt;
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
                                Debug.print("While getting"+ overpayDet.toString());
                                
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
                                Debug.print("B4 insert "+ overpayfinalDet.toString());
                                boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                            }
                            if(chkAmt>=totAmt){
                                if(paymentId!=null || paymentId.trim().length()!=0){
                                    boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentId);
                                    Debug.print("Update Status "+Update_Status);
                                }
                            }
                        }
                    }
                    
// boolean payUpdate = remote.createHorseRequest(reqHrDetVO);
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
                        String preOwnerEmail="";
                        String preRiderEmail="";
                        String preOwnerFName="";
                        String preOwnerLName="";
                        String preOwnEmail="";
                        String preRiderFName="";
                        String preRiderLName="";
                        String preRidEmail="";
                     
                        String membershipName=request.getParameter("membertype");
                                           
                        String riderEmailId=request.getParameter("addREmail");
                         riderFName=request.getParameter("additionalRider");
                        riderLName=request.getParameter("lastRider");
                        String competitionName=request.getParameter("competitionName");
                    
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
                          }  
                        }
                        
                        }
                        
                        String ownerNames="";
                        String riderNames="";
                        ArrayList objPreOwnRidDet = new ArrayList();
                        objPreOwnRidDet = remote.getPreOwnerRiderDetails(horseMemberId);
                        if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0){
                          Iterator itr = objPreOwnRidDet.iterator();
                          int i=0;
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                           relaId=objOwnerInfo.getRelationTypeId();
                           relaTypeName=objOwnerInfo.getRelationTypeName();
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Owner"))){
                                preFName=objOwnerInfo.getPreOwnerFName();
                                preOwnerFName=preFName; 
                                preLName=objOwnerInfo.getPreOwnerLName();
                                preOwnerLName=preLName;
                                preOwnerEmail=objOwnerInfo.getPreEmail();
                                preOwnEmail=preOwnerEmail;
                           } 
                           if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Previous Rider"))){    
                               String preRidFName=objOwnerInfo.getPreOwnerFName();
                                preRiderFName=preRidFName; 
                               String preRidLName=objOwnerInfo.getPreOwnerLName();
                                preRiderLName=preRidLName; 
                               preRiderEmail=objOwnerInfo.getPreEmail();
                                preRidEmail=preRiderEmail;
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
                           i++; 
                          }  
                        }  
                        
                      String textFull=" This horse can enter any level at which it is properly qualified";
                      String textLimit=" This horse can enter events up to and including Training level";      
                     String membershipName1="";
                      
                      if(membershipName!=null && membershipName.equalsIgnoreCase("Full")){
                        
                        membershipName1 = membershipName+" "+textFull;      
                        }
                     else if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                        
                        membershipName1 = membershipName+" "+textLimit;      
                        }
                     else{
                         membershipName1 =membershipName;  
                      } 
     
                        if((OwnerMailId!=null && OwnerMailId.length()!=0) || (riderEmailId!=null && riderEmailId.length()!=0)){
                       Debug.print("OwnerMailId::: inside if condition" + OwnerMailId); 
                            EmailContent email=new EmailContent();
                            if((OwnerMailId!=null && OwnerMailId.length()!=0) && (riderEmailId!=null && riderEmailId.length()!=0)){
                            String toMailIds[] = {OwnerMailId,riderEmailId};
                            email.setTo(toMailIds);
                            }else if(OwnerMailId!=null && OwnerMailId.length()!=0){
                            String toMailIds[] = {OwnerMailId};
                            email.setTo(toMailIds);    
                            }else if(riderEmailId!=null && riderEmailId.length()!=0){
                            String toMailIds[] = {riderEmailId};
                            email.setTo(toMailIds);     
                            }
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Change Rider/Owner");
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
                                "<p>Thank you for updating the rider and/or owner information for <strong>"+competitionName+"</strong>.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ competitionName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ ownerNames+"<br/>"+
                                "Rider(s):"+ riderNames+"<br/>"+                               
                                "<p>Additional information regarding <strong>"+ competitionName +" </strong>can be viewed by logging in to the HLC Online Services.<p>"+ 
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
                        }
                          
        
                        
                     String horseName1=request.getParameter("horseName");  
                    /* textLimit="This horse can enter events up to and including Training level";                     
                     membershipName1=""; 
                     if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                        
                        membershipName1 = membershipName+" "+textLimit;      
                        }
                     else{
                         membershipName1 =membershipName;  
                     } */    
                    if(horseName1!=null && horseName1.length()!=0){
                        if(objPreOwnRidDet!=null && objPreOwnRidDet.size()!=0 ){
                         Debug.print("commonOwners::: &&&&" + objPreOwnRidDet);
                         Iterator itr = objPreOwnRidDet.iterator();
                         int i=0;
                         while(itr.hasNext()) {
                         Debug.print("Inside While");
                         HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                         String relaTypeName1=objOwnerInfo.getRelationTypeName();
                         Debug.print("relaTypeName1 :"+relaTypeName1);
                         if((relaTypeName1!=null && relaTypeName1.equalsIgnoreCase("Owner")) || (relaTypeName1!=null && relaTypeName1.equalsIgnoreCase("Rider"))){
                                String  OREmailId=objOwnerInfo.getPreEmail();
                                Debug.print("OREmailId :" + OREmailId);     
                                String toMailIds[] = {OREmailId};
                                EmailContent email=new EmailContent();
                                email.setTo(toMailIds);
                                email.setFrom("anandv@digiblitz.com");
                                email.setSubject("Horse Name Change");
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
                                "<p>Thank you for updating the horse name for <strong>"+horseName1+"</strong>.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Current Horse Name:  "+ horseName1 +"<br />"+
                                "Past Name: "+ competitionName+"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ ownerNames+"<br/>"+
                                "Rider(s):"+ riderNames+"<br/>"+                               
                                "<p>Additional information regarding <strong>"+ competitionName +" </strong>can be viewed by logging in to the HLC Online Services.<p>"+ 
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
  
                        request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        request.setAttribute("horseMemberId",horseMemberId);
                        
                        
                        return mapping.findForward("changeRidOwnSuccess");
                    } else{
                        Debug.print("Sorry unable to insert due to some bugs");
                    }                    
                }
            } else{
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception occurs in Adding owner and rider details:" + ex.getMessage());
        }
        
        return null;
    }
}
