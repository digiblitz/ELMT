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

/*  Program Name    : InsertHorseRegAction.java
 *  Created Date    : August 24, 2006, 10:15 PM
 *  Author          : Punitha.R
 *  Version         : 1.26
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlccommon.util.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.mrm.actionform.InsertHorseRegActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.*;
import javax.servlet.http.HttpSession;
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
import java.text.*;
import org.apache.struts.util.MessageResources;
import com.hlcform.stateless.*;

public class InsertHorseRegAction extends Action {
    
    String memberId = null;
    String userId = null;
    String fwd=" ";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            
            HttpSession session=request.getSession();
            MessageResources mr=getResources(request);
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
            
///Transaction Entry
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
            HLCHorseUserVO objTrainerVO = new HLCHorseUserVO();
            
            String reqIp=request.getRemoteAddr();
            Debug.print(" Request IP :"+reqIp);
            
            InsertHorseRegActionForm fbean=(InsertHorseRegActionForm)form;
            System.out.println("InsertHorseRegActionForm ="+ fbean);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
            String process = request.getParameter("process");
            
            String staff_user_id= (String) session.getAttribute("staff_user_id");
            
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
            String trainerUserId ="";
            String userNameId3 = "";
            String firstNameId3 = "";
            String lastNameId3 = "";
            String phoneId3 = "";
            String regAddFor="";
            String ado ="";
            
            String horseName ="";
            String userNameOne ="";
            String loginName ="";
            String compAddFor = "";
            String companyName="";
            
            
            String ownerSecMemId="";
            String userName="";
            String userNameId1="";
            String ownerSecLogName="";
            
            String res="";
            System.out.println("ownerUserId in InsertHorseRegAction Servlet :" + request.getParameter("ownerUserId"));
            System.out.println("addOwnerUserId :" + request.getParameter("addOwnerUserId"));
            Date dob=null;
            Date dob1=null;
            boolean status =false;
            String sesUserId = (String)session.getAttribute("userId");
            //-------------------------------HORSE OWNER INFORMATION-------------------------------------------
            try{
                Debug.print("This is for testing:");
                String finalPrimaryPh="";
                String finalFax="";
                regFor= request.getParameter("regFor");
                System.out.println(" regFor :" + regFor);
                ownerUserId = request.getParameter("ownerUserId");
                horseRegVO.setRegisteredBy(sesUserId);
                Debug.print("sesUserId:" + sesUserId);
                
                if(regFor.equals("mem1")){
                    userNameOne = request.getParameter("userNameOne");
                    ownerSecMemId=userNameOne;
                     Debug.print("ownerSecMemId in servlet 1 : "+ ownerSecMemId);
                    ownerUserId = (String)userremote.getUserIdBasedOnMemberId(userNameOne);
                    Debug.print("ownerUserId from another method: "+ ownerUserId);
                    
                } else if(regFor.equals("rid1")){
                     userName = request.getParameter("userName");
                     ownerSecMemId=userName;
                     Debug.print("ownerSecMemId in servlet 2: "+ ownerSecMemId);
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                } else if(regFor.equals("acc1")){
                    userNameId1 = request.getParameter("userNameId1");
                    ownerSecLogName=userNameId1;
                    Debug.print("ownerSecLogName in servlet 3: "+ ownerSecLogName);
                    firstName = request.getParameter("firstNameId1");
                    lastName = request.getParameter("lastNameId1");
                }
                
                else if(regFor.equals("cmp1")){
                    status = true;
                    Debug.print("Inside the company:" + status);
                    compAddFor = request.getParameter("ecmp");
                    Debug.print("compAddFor:" + compAddFor);
                    
                    if(compAddFor.equals("yes")){
                        loginName = request.getParameter("existCompNameId1");
                        ownerSecLogName=loginName;
                        Debug.print("ownerSecLogName in servlet 4: "+ ownerSecLogName);
                        firstName = request.getParameter("cpfirstNameId1");
                        lastName = request.getParameter("cplastNameId1");
                        Debug.print("loginName: " + loginName);
                        Debug.print("firstName: " + firstName);
                        Debug.print("firstName: " + firstName);
                    } else if(compAddFor.equals("no")){
                        loginName = request.getParameter("newFirstNameIdComp");
                        newAddressId = request.getParameter("newAddressIdComp");
                        newCountryId = request.getParameter("newCountryIdComp");
                        newStateId = request.getParameter("newStateIdComp");
                        newCityId = request.getParameter("newCityIdComp");
                        newZipId = request.getParameter("newZipIdComp");
                        newEmailId = request.getParameter("newEmailIdComp");
                        newPhoneId = request.getParameter("newPhoneIdComp");
                        
                        
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
                        newFaxId = request.getParameter("newFaxIdComp");
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
                        
                        
                        
                        firstName = request.getParameter("cpFirstNameIdComp");
                        lastName = request.getParameter("cpLastNameIdComp");
                        ownerUserId = null;
                        System.out.println(" companyName :" + loginName);
                        System.out.println(" newAddressIdComp :" + newAddressId);
                        System.out.println(" newCountryIdComp :" + newCountryId);
                        System.out.println(" newStateIdComp :" + newStateId);
                        System.out.println(" newCityIdComp :" + newCityId);
                        System.out.println("  newZipIdComp:" + newZipId);
                        System.out.println(" newEmailIdComp :" + newEmailId);
                        System.out.println(" newPhoneIdComp :" + newEmailId);
                        System.out.println("  newFaxIdComp:" + newFaxId);
                        System.out.println(" cpFirstNameIdComp :" + firstName);
                        System.out.println("  cpLastNameIdComp:" + lastName);
                        Debug.print("Successfully skipped Owner information:");
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
            } catch(Exception owner){
                System.out.println("Exception occurs in owner insertion part:" + owner.getMessage());
            }
            //// Horse Trainer Information
            try{
                
                newAddressId = "";
                newCountryId = "";
                newStateId = "";
                newCityId = "";
                newZipId = "";
                newEmailId = "";
                newPhoneId = "";
                newFaxId = "";
                regAddFor = "";
                addOwnerUserId = "";
                firstName = "";
                lastName = "";
                userNameId3 = "";
                regAddFor="";
                userNameOne ="";
                compAddFor = "";
                String horseMemberId = "";
                String riderId = "";
                companyName="";
                loginName ="";
                String finalPrimaryPh="";
                String finalFax="";
                
                trainerUserId = request.getParameter("addTrainerUserId");
                //================================================================================================================
                regAddFor= request.getParameter("regAddFor");
                String userId =(String)session.getAttribute("userId");
                Debug.print(" Session userId:" + userId);
                boolean trainResult=false;
                
                if(regAddFor!=null){
                    Debug.print("########################");
                    Debug.print("regAddFor: "+regAddFor);
                    Debug.print("########################");
                    
                    if(regAddFor.equals("own1")){
                        Debug.print("Inside own1");
                        loginName = objOwnerVO.getLoginName();
                        firstName = objOwnerVO.getFirstName();
                        lastName = objOwnerVO.getLastName();
                        sexId = objOwnerVO.getGender();
                        dob = objOwnerVO.getDob();
                        newAddressId = objOwnerVO.getAddress();
                        newCityId = objOwnerVO.getCity();
                        newCountryId = objOwnerVO.getCountry();
                        newStateId = objOwnerVO.getState();
                        newEmailId = objOwnerVO.getEmailId();
                        finalPrimaryPh = objOwnerVO.getPhoneNo();
                        finalFax = objOwnerVO.getFaxNo();
                        newZipId = objOwnerVO.getZip();
                        trainerUserId = objOwnerVO.getUserId();
                        
                        Debug.print(loginName);
                        Debug.print(firstName);
                        Debug.print(lastName);
                        Debug.print(sexId);
                        Debug.print(""+dob);
                        Debug.print(newAddressId);
                        Debug.print(newCityId);
                        Debug.print(newCountryId);
                        Debug.print(newStateId);
                        Debug.print(newEmailId);
                        Debug.print(finalPrimaryPh);
                        Debug.print(finalFax);
                        Debug.print(newZipId);
                        Debug.print(trainerUserId);
                        Debug.print("##########################");
                        
                    } else if(regAddFor.equals("mem1")){
                        Debug.print("Inside mem1");
                        userNameOne = request.getParameter("hlcNo");
                        Debug.print(userNameOne);
                        trainerUserId = (String)userremote.getUserIdBasedOnMemberId(userNameOne);
                        Debug.print("trainerUserId from another method: "+ trainerUserId);
                    } else if(regAddFor.equals("rid1")){
                        firstName = request.getParameter("firstName");
                        lastName = request.getParameter("lastName");
                    } else if(regAddFor.equals("acc1")){
                        firstName = request.getParameter("firstNameId1");
                        lastName = request.getParameter("lastNameId1");
                    } else if(regAddFor.equals("cmp1")){
                        status = true;
                        Debug.print("Inside the company:" + status);
                        compAddFor = request.getParameter("ecmp");
                        Debug.print("compAddFor:" + compAddFor);
                        
                        if(compAddFor.equals("yes")){
                            loginName = request.getParameter("existCompNameId1");
                            firstName = request.getParameter("cpfirstNameId1");
                            lastName = request.getParameter("cplastNameId1");
                            Debug.print("loginName: " + loginName);
                            Debug.print("firstName: " + firstName);
                            Debug.print("firstName: " + firstName);
                        } else if(compAddFor.equals("no")){
                            loginName = request.getParameter("newFirstNameIdComp");
                            newAddressId = request.getParameter("newAddressIdComp");
                            newCountryId = request.getParameter("newCountryIdComp");
                            newStateId = request.getParameter("newStateIdComp");
                            newCityId = request.getParameter("newCityIdComp");
                            newZipId = request.getParameter("newZipIdComp");
                            newEmailId = request.getParameter("newEmailIdComp");
                            newPhoneId = request.getParameter("newPhoneIdComp");
                            
                            
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
                            newFaxId = request.getParameter("newFaxIdComp");
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
                            
                            
                            
                            firstName = request.getParameter("cpFirstNameIdComp");
                            lastName = request.getParameter("cpLastNameIdComp");
                            trainerUserId = null;
                            System.out.println(" companyName :" + loginName);
                            System.out.println(" newAddressIdComp :" + newAddressId);
                            System.out.println(" newCountryIdComp :" + newCountryId);
                            System.out.println(" newStateIdComp :" + newStateId);
                            System.out.println(" newCityIdComp :" + newCityId);
                            System.out.println("  newZipIdComp:" + newZipId);
                            System.out.println(" newEmailIdComp :" + newEmailId);
                            System.out.println(" newPhoneIdComp :" + newEmailId);
                            System.out.println("  newFaxIdComp:" + newFaxId);
                            System.out.println(" cpFirstNameIdComp :" + firstName);
                            System.out.println("  cpLastNameIdComp:" + lastName);
                            Debug.print("Successfully skipped Owner information:");
                        }
                    }else if(regAddFor.equals("other")){
                        loginName = "";
                        firstName = "";
                        lastName = "";
                        sexId = "";
                        dob = null;
                        newAddressId = "";
                        newCityId = "";
                        newCountryId = "";
                        newStateId = "";
                        newEmailId = "";
                        finalPrimaryPh = "";
                        finalFax = "";
                        newZipId = "";
                        trainerUserId = "";
                    }
                    
                    objTrainerVO.setLoginName(loginName);
                    objTrainerVO.setFirstName(firstName);
                    objTrainerVO.setLastName(lastName);
                    objTrainerVO.setGender(sexId);
                    objTrainerVO.setDob(dob);
                    objTrainerVO.setAddress(newAddressId);
                    objTrainerVO.setCity(newCityId);
                    objTrainerVO.setCountry(newCountryId);
                    objTrainerVO.setState(newStateId);
                    objTrainerVO.setEmailId(newEmailId);
                    objTrainerVO.setPhoneNo(finalPrimaryPh);
                    objTrainerVO.setFaxNo(finalFax);
                    objTrainerVO.setZip(newZipId);
                    objTrainerVO.setUserId(trainerUserId);
                    
                    Debug.print("&&&&&&&&&&&&&&&& Important: "+objTrainerVO.getUserId());
                }//closing else
                //closing insert trainer
            } catch(Exception trainerExcep){
                trainerExcep.printStackTrace();
            }
            String serviceType = "";
            String feedisp=fbean.getFeeDisp();
            Pattern pat=Pattern.compile("#");
            String str[]=pat.split(feedisp);
            String id=str[0];
            String mname=str[1];
            String amt=str[2];
            System.out.println(id+"  "+mname+ "  "+amt);
            
            String[] nam=new String[200];
            String[] chkd=new String[200];
            int x=0;
            int j=3;
            String cid="";
            request.setAttribute("registrationLevel",mname);
            ArrayList  arr = (ArrayList)remote.getGenUserId();
            for (Iterator it=arr.iterator(); it.hasNext( ); ) {
                String result = String.valueOf(it.next( ));
                System.out.println(result);
                userId = result;
            }
            
            ArrayList  arr1 = (ArrayList)remote.getGenMemberId();
            for (Iterator it=arr1.iterator(); it.hasNext( ); ) {
                String result = String.valueOf(it.next( ));
                System.out.println(result);
                memberId = result;
            }
            
            String phoneCharge=request.getParameter("phoneCharge");
            String serviceStatus = request.getParameter("servicePrice");
            
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
            
            //-------------------------------------HORSE REGISTRATION DETAILS------------------------------------------------
            try{
                if(fbean.getUseaNo()!=null && fbean.getUseaNo().trim().length()!=0){
                    horseRegVO.setRiderMemberId(fbean.getUseaNo());
                } else{
                    horseRegVO.setRiderMemberId(null);
                }
                if(request.getParameter("addOwnerUserId")!=null && request.getParameter("addOwnerUserId").trim().length()!=0){
                    horseRegVO.setAddOwnerId(request.getParameter("addOwnerUserId"));
                } else{
                    horseRegVO.setAddOwnerId(null);
                }
                if(firstNameId3!=null && firstNameId3.trim().length()!=0){
                    horseRegVO.setAddOwnerName(firstNameId3);
                } else{
                    horseRegVO.setAddOwnerName(null);
                }
                if(fbean.getArhlcNo()!=null && fbean.getArhlcNo().trim().length()!=0){
                    horseRegVO.setAddRiderMemberId(fbean.getArhlcNo());
                } else{
                    horseRegVO.setAddRiderMemberId(null);
                }
                if(fbean.getPastName()!=null && fbean.getPastName().trim().length()!=0){
                    horseRegVO.setBaPastName(fbean.getPastName());
                } else{
                    horseRegVO.setBaPastName(null);
                }
                if(fbean.getRegisteredName()!=null && fbean.getRegisteredName().trim().length()!=0){
                    horseRegVO.setRegisteredName(fbean.getRegisteredName());
                } else{
                    horseRegVO.setRegisteredName(null);
                }
                if(fbean.getHorseName()!=null && fbean.getHorseName().trim().length()!=0){
                    horseRegVO.setCompetitionName(fbean.getHorseName());
                } else{
                    horseRegVO.setCompetitionName(null);
                }
                
                if(ownerUserId!=null && ownerUserId.trim().length()!=0){
                    horseRegVO.setOwnerId(ownerUserId);
                } else{
                    horseRegVO.setOwnerId(null);
                }
                //    Trainer Info
                
                Debug.print("&&&&&&&&&&& Important trainer id after the conditions: "+trainerUserId);
                if(trainerUserId!=null && trainerUserId.trim().length()!=0){
                    horseRegVO.setTrainerUserId(trainerUserId);
                } else{
                    horseRegVO.setTrainerUserId(null);
                }
                
                Debug.print("&&&&&&&&&&& Important trainer id in horseRegVO: "+horseRegVO.getTrainerUserId());
                
                if(fbean.getPreviousOwner()!=null && fbean.getPreviousOwner().trim().length()!=0){
                    horseRegVO.setPrevOwnerName(fbean.getPreviousOwner());
                } else{
                    horseRegVO.setPrevOwnerName(null);
                }
                if(fbean.getPrhlcNo()!=null && fbean.getPrhlcNo().trim().length()!=0){
                    horseRegVO.setPrevRiderMemberId(fbean.getPrhlcNo());
                } else{
                    horseRegVO.setPrevRiderMemberId(null);
                }
                if(fbean.getBreedAssoc()!=null && fbean.getBreedAssoc().trim().length()!=0){
                    horseRegVO.setBaRegisteredName(fbean.getBreedAssoc());
                } else{
                    horseRegVO.setBaRegisteredName(null);
                }
                // String paymentId = remote.getNextId();
                horseRegVO.setHorseMemberId(memberId);
                //  horseRegVO.setPaymentId(paymentId);
                horseRegVO.setPrevOwnerId(null);
                horseRegVO.setMembershipTypeId(id);
                horseRegVO.setCompanyName(loginName);
                horseRegVO.setCompanyStatus(status);
                horseRegVO.setContactFirstName(firstName);
                horseRegVO.setContactLastName(lastName);
            } catch(Exception horseDet){
                System.out.println("Exception occurs in horse description:" + horseDet.getMessage());
            }
            Debug.print("Successfully skipped HorseRegistration:");
//-------------------------------------HORSE REGISTRATION ENDS------------------------------------------------
            try{
                
                if(fbean.getColorSelect()!=null && fbean.getColorSelect().trim().length()!=0){
                    objHorseDesc.setColor(fbean.getColorSelect());
                } else{
                    objHorseDesc.setColor(null);
                }
                if(fbean.getGenderselect()!=null && fbean.getGenderselect().trim().length()!=0){
                    objHorseDesc.setGender(fbean.getGenderselect());
                } else{
                    objHorseDesc.setGender(null);
                }
                
                if(fbean.getHeight()!=null && fbean.getHeight().trim().length()!=0){
                    objHorseDesc.setHeight(fbean.getHeight());
                } else{
                    objHorseDesc.setHeight(null);
                }
                
                if(fbean.getBreed()!=null && fbean.getBreed().trim().length()!=0){
                    objHorseDesc.setBreed(fbean.getBreed());
                } else{
                    objHorseDesc.setBreed(null);
                }
                
                if(fbean.getBreedTwo()!=null && fbean.getBreedTwo().trim().length()!=0){
                    objHorseDesc.setBreed2(fbean.getBreedTwo());
                } else{
                    objHorseDesc.setBreed2(null);
                }
                
                if(fbean.getSireName()!=null && fbean.getSireName().trim().length()!=0){
                    objHorseDesc.setSire(fbean.getSireName());
                } else{
                    objHorseDesc.setSire(null);
                }
                
                if(fbean.getCountryOrigin()!=null && fbean.getCountryOrigin().trim().length()!=0){
                    objHorseDesc.setCountry(fbean.getCountryOrigin());
                } else{
                    objHorseDesc.setCountry(null);
                }
                if(fbean.getSireBreed()!=null && fbean.getSireBreed().trim().length()!=0){
                    objHorseDesc.setSireBreed(fbean.getSireBreed());
                } else{
                    objHorseDesc.setSireBreed(null);
                }
                
                if(fbean.getSireBreedTwo()!=null && fbean.getSireBreedTwo().trim().length()!=0){
                    objHorseDesc.setSireBreed2(fbean.getSireBreedTwo());
                } else{
                    objHorseDesc.setSireBreed2(null);
                }
                
                if(fbean.getDamName()!=null && fbean.getDamName().trim().length()!=0){
                    objHorseDesc.setDam(fbean.getDamName());
                } else{
                    objHorseDesc.setDam(null);
                }
                
                if(fbean.getDamBreed()!=null && fbean.getDamBreed().trim().length()!=0){
                    objHorseDesc.setDamBreed(fbean.getDamBreed());
                } else{
                    objHorseDesc.setDamBreed(null);
                }
                
                if(fbean.getDamBreedTwo()!=null && fbean.getDamBreedTwo().trim().length()!=0){
                    objHorseDesc.setDamBreed2(fbean.getDamBreedTwo());
                } else{
                    objHorseDesc.setDamBreed2(null);
                }
                
                if(fbean.getYearfoaled()!=null && fbean.getYearfoaled().trim().length()!=0){
                    objHorseDesc.setYearFoaled(fbean.getYearfoaled());
                } else{
                    objHorseDesc.setYearFoaled(null);
                }
                
                if(fbean.getImportedFrom()!=null && fbean.getImportedFrom().trim().length()!=0){
                    objHorseDesc.setImportedFrom(fbean.getImportedFrom());
                } else{
                    objHorseDesc.setImportedFrom(null);
                }
                
                if(fbean.getDateImported()!=null && fbean.getDateImported().trim().length()!=0){
                    objHorseDesc.setImportDate(sdf.parse(fbean.getDateImported()));
                } else{
                    objHorseDesc.setImportDate(null);
                }
                
                if(fbean.getForeignGrade()!=null && fbean.getForeignGrade().trim().length()!=0){
                    objHorseDesc.setForeignGrade(fbean.getForeignGrade());
                } else{
                    objHorseDesc.setForeignGrade(null);
                }
                
                if(fbean.getForeignPoints()!=null && fbean.getForeignPoints().trim().length()!=0){
                    objHorseDesc.setForeignPoints(Float.parseFloat(fbean.getForeignPoints()));
                } else{
                    objHorseDesc.setForeignPoints(0);
                }
                
                if(fbean.getAssignedGrade()!=null && fbean.getAssignedGrade().trim().length()!=0){
                    objHorseDesc.setAssignedGrade(fbean.getAssignedGrade());
                } else{
                    objHorseDesc.setAssignedGrade(null);
                }
                
                if(fbean.getAssignedPoints()!=null && fbean.getAssignedPoints().trim().length()!=0){
                    objHorseDesc.setAssignedPoints(Float.parseFloat(fbean.getAssignedPoints()));
                } else{
                    objHorseDesc.setAssignedPoints(0);
                }
                
                if(fbean.getNotes()!=null && fbean.getNotes().trim().length()!=0){
                    objHorseDesc.setNotes(fbean.getNotes());
                } else{
                    objHorseDesc.setNotes(null);
                }
                
                if(fbean.getSpecialNotes()!=null && fbean.getSpecialNotes().trim().length()!=0){
                    objHorseDesc.setSplNotes(fbean.getSpecialNotes());
                } else{
                    objHorseDesc.setSplNotes(null);
                }
                
            } catch(Exception horseDesc){
                System.out.println("Exception occurs in horse description:" + horseDesc);
            }
            Debug.print("Successfully skipped HorseDescription:");
            horseName = fbean.getHorseName();
//---------------------------------Payment Section Starts--------------------------------------------
            
            String emailId = (String)session.getAttribute("emailId");
            Debug.print("emailId in Horse Registration Section &&&&&&" +emailId);
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
            String tempAmount = request.getParameter("tempAmount");
            String tempCheckAmount = request.getParameter("chkBalAmt");
            if(tempCheckAmount!=null && tempCheckAmount.trim().length()!=0){
                checkAmount = Float.parseFloat(tempCheckAmount);
            } else{
                checkAmount=0;
            }
            System.out.println("checkAmount" + checkAmount);
            
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
            //objPayment.setCheckAmount(0);
            objPayment.setIpAddress(addr);
            objPayment.setPendingAmount(0);
            objPayment.setPaymentDate(new Date());
            
            String fee_txt = request.getParameter("fee_txt");
            Debug.print("fee_txt is "+fee_txt);
            horseRegVO.setMembership_amount(fee_txt);
            
            
            if(r11.equals("card")){
                String cvNo = request.getParameter("ccCvvid");
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
                horseRegVO.setAmount(tempAmount);
                Debug.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>Amount in First Servlet: "+horseRegVO.getAmount());
                System.out.println("expYear:" + expYear);
                //expYear = expYear.substring(2);
                System.out.println("expMon:" + expMon);
                //System.out.println("expYear:" + expYear);
                String expDate = expMon + expYear;
                request.setAttribute("expDate",expDate);
                request.setAttribute("amount",String.valueOf(totalAmount));
                request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                
                String paymentId = remote.getNextId();
                Debug.print("paymentId in check:" + paymentId);
                objPayment.setPaymentId(paymentId);
                
                boolean horseStatus = remote.isHorseExist(horseName);
                Debug.print("horseStatus:" + horseStatus);
                String activationDate = request.getParameter("activeDatVal");
                System.out.print("activationDate" + activationDate);
                
///Start Transaction Entries
///////  Phone Service Charge
                
                HLCAccTransactionVO PhaccTxnVO = null;
                HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
                int si=0;
                String logBy="user";
                
                if(session.getAttribute("loggedBy")!=null) {
                    String loggedBy="";
                    loggedBy=(String)session.getAttribute("loggedBy");
                    logBy=loggedBy;
                    
                    PhaccTxnVO = new HLCAccTransactionVO();
                    String phoneCharge1 = request.getParameter("phoneCharge");
                    String phoneId = request.getParameter("servicePrice");
                    if(phoneCharge1!=null){
                        if(phoneId!=null || phoneId.trim().length()!=0){
                            
                            HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getPhoneServChgAccTxnTypeDetails(phoneId);
                            
                            String accNo = accTxnDet.getAccount_no();
                            String accClassname = accTxnDet.getClass_name();
                            String accItemNo = accTxnDet.getItem_no();
                            String accAccNo = accTxnDet.getSub_account_no();
                            String accTranName = accTxnDet.getTransaction_name();
                            String accTyped = accTxnDet.getTransaction_type();
                            String accTypeId = accTxnDet.getTransaction_type_id();
                            
                            if(r11.equalsIgnoreCase("check")){
                                PhaccTxnVO.setPayment_mode("check");
                            }
                            if(r11.equalsIgnoreCase("card")){
                                String cardselect = request.getParameter("ccType");
                                PhaccTxnVO.setPayment_mode(cardselect);
                            }
                            
                            if(phoneCharge1!=null || phoneCharge1.trim().length()!=0){
                                PhaccTxnVO.setAmount(Float.parseFloat(phoneCharge1));
                            }
                                /*
                                if(r11.equalsIgnoreCase("card")){
                                     PhaccTxnVO.setAmount(0);
                                }
                                 */
                            
                            PhaccTxnVO.setActive_status(false);
                            PhaccTxnVO.setSub_account_no(accAccNo);
                            PhaccTxnVO.setPayment_id(paymentId);
                            PhaccTxnVO.setAccount_type("Income");
                            PhaccTxnVO.setClass_Typ(accClassname);
                            //PhaccTxnVO.setUser_id(userId);
                            //PhaccTxnVO.setIp_address(reqIp);
                            PhaccTxnVO.setAccount_no(accNo);
                            PhaccTxnVO.setItem_no(accItemNo);
                            PhaccTxnVO.setDescription(accTranName);
                        }
                    }
                }
                
                if(r11.equals("check")){
                    if(PhaccTxnVO!=null){
                        boolean phone_status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+phone_status);
                    }
                } else{
                    Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service");
                    session.setAttribute("PhoneVO",PhaccTxnVO);
                }
                
//// Horse Member type Entry
                String membtype = request.getParameter("feeDisp");
                String membershipName="";
                if(membtype!=null || membtype.trim().length()!=0){
                    String  sarray[] = membtype.split("#");
                    String memberTypeId = sarray[0];
                    membershipName = sarray[1];
                    String membershipAmount = sarray[2];
                    String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
                    Debug.print("membtype is "+membtype);
                    HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(memberTypeId);
                    HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
                    
                    String accNo =  accTxnDet.getAccount_no();
                    String className = accTxnDet.getClass_name();
                    String itemNo = accTxnDet.getItem_no();
                    String subAccNo = accTxnDet.getSub_account_no();
                    String transName = accTxnDet.getTransaction_name();
                    String transType = accTxnDet.getTransaction_type();
                    
                    if(r11.equalsIgnoreCase("check")){
                        horseMem.setPayment_mode("check");
                        horseMem.setActive_status(false);
                    }
                    if(r11.equalsIgnoreCase("card")){
                        String cardselect = request.getParameter("ccType");
                        horseMem.setPayment_mode(cardselect);
                        horseMem.setActive_status(false);
                    }
                    
                    if(membershipAmount==null || membershipAmount.trim().length()!=0){
                        horseMem.setAmount(Float.parseFloat(membershipAmount));
                    } else{
                        horseMem.setAmount(0);
                    }
                    horseMem.setClass_Typ(className);
                    horseMem.setAccount_no(accNo);
                    horseMem.setAccount_type("Income");
                    //horseMem.setUser_id(userId);
                    //horseMem.setIp_address(reqIp);
                    horseMem.setItem_no(itemNo);
                    horseMem.setSub_account_no(subAccNo);
                    horseMem.setPayment_id(paymentId);
                    horseMem.setDescription(transName);
                    if(r11.equals("check")){
                        if(PhaccTxnVO!=null){
                            boolean insert_status = mahaRemote.insertAccountTxnDetails(horseMem);
                            Debug.print("Insert Status "+insert_status);
                        }
                    } else{
                        Debug.print("mahaRemote.insertAccountTxnDetails(horseMem) for Phone Service");
                        session.setAttribute("horseMem",horseMem);
                    }
                }
                
//End Transaction Entries
                
                
                
                if(activationDate!=null && activationDate.trim().length()!=0){
                    session.setAttribute("activationDate",activationDate);
                } else{
                    session.setAttribute("activationDate",null);
                }
                session.setAttribute("horseStatus",String.valueOf(horseStatus));
                session.setAttribute("registrationLevel",mname);
                session.setAttribute("horseName",horseName);
                
                session.setAttribute("horseRegVO",horseRegVO);
                session.setAttribute("objHorseDesc",objHorseDesc);
                session.setAttribute("objOwnerVO",objOwnerVO);
                session.setAttribute("objTrainerVO",objTrainerVO);
                session.setAttribute("objAddOwnerVO",objAddOwnerVO);
                session.setAttribute("serviceType",serviceType);
                session.setAttribute("status",String.valueOf(status));
                
                session.setAttribute("amount",String.valueOf(totalAmount));
                session.setAttribute("paymentId",paymentId);
                session.setAttribute("horseMemberId",res);
                session.setAttribute("membtype",membtype);
                
                
                 String tempRiderId = request.getParameter("hlcNo");                                             
                        String riderFName="";
                        String riderLName="";
                        String OwnerFName="";
                        String OwnerLName="";
                        String OwnerMailId="";
                        
                        String riderEmailId=request.getParameter("rEmail");
                        riderFName=request.getParameter("riderFname_id");
                        riderLName=request.getParameter("riderLname_id");
                        
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
          
                          }  
                        }
                        }else{
       
                        ArrayList objOwnerDet = new ArrayList();
                        objOwnerDet = remote.getUserDets(ownerSecMemId);
                        if(objOwnerDet!=null && objOwnerDet.size()!=0){
                        Debug.print("objOwnerDet::: &&&& in card section" + objOwnerDet);
                        Iterator itr = objOwnerDet.iterator();
                        while(itr.hasNext()) {
                        HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();
                        OwnerFName=objOwnerInfo.getOwnerFirstName();
                        Debug.print("OwnerFName::: card section" + OwnerFName);
                        OwnerLName=objOwnerInfo.getOwnerLastName();
                        Debug.print("OwnerLName::: card section" + OwnerLName);
                        OwnerMailId=objOwnerInfo.getOwnerEmail(); 
                        Debug.print("OwnerMailId::: card section" + OwnerMailId);
                          }  
                        }
                        }
                        
                     String textLimit="This Non-Human can enter events up to and including Training level";
                     String textFull="This Non-Human can enter any level at which it is properly qualified";
                     
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

                if(membtype!=null || membtype.trim().length()!=0){
                    String  sarray[] = membtype.split("#");
                    String memberTypeId = sarray[0];
                    membershipName = sarray[1];
                    String membershipAmount = sarray[2];
                }       
                        
      
                StringBuffer reqURL = request.getRequestURL();
                int lastIndex = reqURL.lastIndexOf("/") ;
                String url ="";
                if(lastIndex!=-1){
                    url = reqURL.substring(0,lastIndex+1);
                }
                String tmpNova  = mr.getMessage("horsereg.nova");
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
                 session.setAttribute("membershipName1",membershipName1);
                 String inVId = request.getParameter("inVoiceId");
                System.out.println("inVId in servlet from request: " + inVId);
                int intVId = 1;
//                if (inVoiceId1.equalsIgnoreCase("0")) {
//                    intVId = 1;
//                } else {
//                    intVId = 1;
 //               }
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

                request.setAttribute("purpose", "newhorseregistration");
                //intVId++; 
                request.setAttribute("intVId", String.valueOf(intVId));
                System.out.println("intVId in servlet InsertHorseRegAction &&&&&&&" + intVId);

                return mapping.findForward("testing");
                //return mapping.findForward("novaPage");
            } else{
                String paymentId = remote.getNextId();
                Debug.print("paymentId in check:" + paymentId);
                objPayment.setPaymentId(paymentId);
                
                horseRegVO.setPaymentId(paymentId);
                
                Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                horseRegVO.setAmount(String.valueOf(totalAmount));
                
                Debug.print("amount:" + totalAmount);
                Debug.print("horseRegVO.setRegisteredBy(sesUserId)");
                boolean horseStatus = remote.isHorseExist(horseName);
                Debug.print("Outside the company:" + status);
                res = remote.createHorseRegistration(horseRegVO, objHorseDesc, objTrainerVO, objOwnerVO, objAddOwnerVO,serviceType,status);
                
                Debug.print("Horse Member id"+ res);
                
                Debug.print("Horse horseStatus:"+ horseStatus);
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
                } else{
                    
                }
                boolean result1 = false;
                boolean updBal = false;
                boolean statusChange = false;
                boolean activeStatus = false;
                boolean activateDateStatus = false;
                
                if(res!=null && res.trim().length()!=0){
                    result1  = objPaySessRemote.createPayment(objPayment);
                    
///Start Transaction Entries
///////  Phone Service Charge
                    
                    HLCAccTransactionVO PhaccTxnVO = null;
                    HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
                    int si=0;
                    String logBy="user";
                    
                    if(session.getAttribute("loggedBy")!=null) {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;
                        
                        PhaccTxnVO = new HLCAccTransactionVO();
                        String phoneCharge1 = request.getParameter("phoneCharge");
                        Debug.print(" phoneCharge1  "+phoneCharge1);
                        String phoneId = request.getParameter("servicePrice");
                        if(phoneCharge1!=null){
                            if(phoneId!=null || phoneId.trim().length()!=0){
                                
                                HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getPhoneServChgAccTxnTypeDetails(phoneId);
                                
                                String accNo = accTxnDet.getAccount_no();
                                String accClassname = accTxnDet.getClass_name();
                                String accItemNo = accTxnDet.getItem_no();
                                String accAccNo = accTxnDet.getSub_account_no();
                                String accTranName = accTxnDet.getTransaction_name();
                                String accTyped = accTxnDet.getTransaction_type();
                                String accTypeId = accTxnDet.getTransaction_type_id();
                                
                                if(r11.equalsIgnoreCase("check")){
                                    PhaccTxnVO.setPayment_mode("check");
                                    PhaccTxnVO.setActive_status(false);
                                }
                                if(r11.equalsIgnoreCase("card")){
                                    String cardselect = request.getParameter("ccType");
                                    PhaccTxnVO.setPayment_mode(cardselect);
                                    PhaccTxnVO.setActive_status(false);
                                }
                                if(phoneCharge1!=null){
                                    if(phoneCharge1.trim().length()!=0){
                                        PhaccTxnVO.setAmount(Float.parseFloat(phoneCharge1));
                                    }
                                } else{
                                    Debug.print("Amount Set as NULL ");
                                    PhaccTxnVO.setAmount(0);
                                }
                                
                                PhaccTxnVO.setPayment_id(paymentId);
                                PhaccTxnVO.setAccount_type("Income");
                                PhaccTxnVO.setSub_account_no(accAccNo);
                                PhaccTxnVO.setClass_Typ(accClassname);
                                //PhaccTxnVO.setUser_id(userId);
                                //PhaccTxnVO.setIp_address(reqIp);
                                PhaccTxnVO.setAccount_no(accNo);
                                PhaccTxnVO.setItem_no(accItemNo);
                                PhaccTxnVO.setDescription(accTranName);
                            }
                        }
                    }
                    
                    if(r11.equals("check")){
                        if(PhaccTxnVO!=null){
                            // Setting staff user_id and ip_address
                            if(session.getAttribute("loggedBy")!=null) {
                                String loggedBy="";
                                loggedBy=(String)session.getAttribute("loggedBy");
                                logBy=loggedBy;
                                
                                PhaccTxnVO.setStaff_user_id(staff_user_id);
                                PhaccTxnVO.setStaff_ip_address(reqIp);
                            }
                            //End Setting staff_user_id and ip_address
                            boolean phone_status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+phone_status);
                        }
                    } else{
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service");
                        session.setAttribute("PhoneVO",PhaccTxnVO);
                    }
                    
//// Horse Member type Entry
                    String membtype = request.getParameter("feeDisp");
                    if(membtype!=null || membtype.trim().length()!=0){
                        String  sarray[] = membtype.split("#");
                        String memberTypeId = sarray[0];
                        String membershipName = sarray[1];
                        String membershipAmount = sarray[2];
                        String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
                        
                        HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.getMemberAccTxnTypeDetails(memberTypeId);
                        HLCAccTransactionVO horseMem = new HLCAccTransactionVO();
                        
                        String accNo =  accTxnDet.getAccount_no();
                        String className = accTxnDet.getClass_name();
                        String itemNo = accTxnDet.getItem_no();
                        String subAccNo = accTxnDet.getSub_account_no();
                        String transName = accTxnDet.getTransaction_name();
                        String transType = accTxnDet.getTransaction_type();
                        
                        if(membershipAmount==null || membershipAmount.trim().length()!=0){
                            horseMem.setAmount(Float.parseFloat(membershipAmount));
                        } else{
                            horseMem.setAmount(0);
                        }
                        
                        if(r11.equalsIgnoreCase("check")){
                            horseMem.setPayment_mode("check");
                            horseMem.setActive_status(false);
                        }
                        if(r11.equalsIgnoreCase("card")){
                            String cardselect = request.getParameter("ccType");
                            horseMem.setPayment_mode(cardselect);
                            horseMem.setActive_status(false);
                        }
                        
                        horseMem.setAccount_no(accNo);
                        horseMem.setAccount_type("Income");
                        horseMem.setClass_Typ(className);
                        //horseMem.setUser_id(userId);
                        //horseMem.setIp_address(reqIp);
                        horseMem.setItem_no(itemNo);
                        horseMem.setSub_account_no(subAccNo);
                        horseMem.setPayment_id(paymentId);
                        horseMem.setDescription(transName);
                        
                        if(r11.equalsIgnoreCase("check")){
                            // Setting staff user_id and ip_address
                            if(session.getAttribute("loggedBy")!=null) {
                                String loggedBy="";
                                loggedBy=(String)session.getAttribute("loggedBy");
                                logBy=loggedBy;
                                
                                horseMem.setStaff_user_id(staff_user_id);
                                horseMem.setStaff_ip_address(reqIp);
                                
                            }
                            //End Setting staff_user_id and ip_address
                            boolean insert_status = mahaRemote.insertAccountTxnDetails(horseMem);
                            Debug.print("Insert Status "+insert_status);
                        }
                        
                    }
//End Transaction Entries
                    
                    
                    double glblChkAmt =0.0;
                    double glbltotAmt =0.0;
                    String totAmount1 = request.getParameter("totalAmount");
                    if(totAmount1.trim().length()!=0){
                        double totAmt = Double.parseDouble(totAmount1);
                        glbltotAmt = totAmt;
                    }
                    // Activating Status
                    if(session.getAttribute("loggedBy")!=null) {
                        String loggedBy="";
                        loggedBy=(String)session.getAttribute("loggedBy");
                        logBy=loggedBy;
                        
                        // Making the active Status and reconcile status as true on full payment
                        
                        //Setting the reconcile & active Status TRUE
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
                                glblChkAmt = chkAmt;
                                glbltotAmt = totAmt;
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
                                    
                                    
                                    // Setting staff user_id and ip_address
                                    overpayfinalDet.setStaff_user_id(staff_user_id);
                                    overpayfinalDet.setStaff_ip_address(reqIp);
                                    //End Setting staff_user_id and ip_address
                                    overpayfinalDet.setPayment_mode("check");
                                    overpayfinalDet.setActive_status(false);
                                    
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
                            // End Of Status change
                        }
                    }
                    /// Start Histroy Details
                    //membtype
                    Debug.print("History Table Entries");
                    HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                    
                    java.util.Calendar toDay = java.util.Calendar.getInstance();
                    int newyear = toDay.get(Calendar.YEAR);
                    int new_month = toDay.get(Calendar.MONTH);
                    
                    if(membtype!=null || membtype.trim().length()!=0){
                        String sarray[] = membtype.split("#");
                        String memberTypeId = sarray[0];
                        String membershipName = sarray[1];
                        String statid ="";
                        
                        if(glbltotAmt<=glblChkAmt) {
                            statid=userremote.getStatusIBasedOnStatus("Active");
                        } else{
                            statid=userremote.getStatusIBasedOnStatus("Pending");
                        }
                        
                        Debug.print("New Horse Member Id Generated is "+res);
                        Debug.print("New Payment Id Generated is "+paymentId);
                        Debug.print("New Member Type Id is "+memberTypeId);
                        Debug.print("New Member Type Name is "+membershipName);
                        
                        detailVO.setTo_year(newyear);
                        detailVO.setPayment_id(paymentId);
                        detailVO.setMemberId(res);
                        detailVO.setMembership_action("Register");
                        detailVO.setMembership_type_id(memberTypeId);
                        detailVO.setMembership_type_name(membershipName);
                        //detailVO.setZip_code();
                        detailVO.setStatus_id(statid);
                        detailVO.setUser_id(sesUserId);
                        
                        Debug.print("History Updat Status");
                        
                        boolean historyUpdate = userremote.insertHorseMemberHistoryDetails(detailVO);
                        Debug.print("historyUpdate "+historyUpdate);
                    }
                    //// End History Details
                    String sessionUser = (String)session.getAttribute("loggedBy");
                    Debug.print("sessionUser" + sessionUser);
                    if(sessionUser !=null && (sessionUser.equalsIgnoreCase("Admin") || sessionUser.equalsIgnoreCase("Usea Staff"))){
                        String activationDate = request.getParameter("activeDatVal");
                        Date checkActDate =null;
                        if(activationDate!=null && activationDate.trim().length()!=0){
                            checkActDate =  sdf.parse(activationDate);
                            Debug.print("checkActDate:" + checkActDate);
                        }
                        if(checkAmount!=0){
                            updBal = remote.updatePendingAmount(sesUserId,paymentId,checkAmount);
                            request.setAttribute("totalAmount",String.valueOf(checkAmount));
                            if(checkAmount>=totalAmount){
                                Debug.print("inside the condition totAmt > checkAmt:");
                                statusChange = remote.statusChangeForHorse(res,null);
                                activeStatus = remote.updateRealtionshipStatus(res);
                                activateDateStatus = remote.updateRequestStatus(res,"","Horse Registration",checkActDate);
                                request.setAttribute("activeLevel","Active");
                            } else{
                                request.setAttribute("activeLevel","null");
                            }
                        } else{
                            request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        }
                        
                    }
                    Debug.print("Result  Payment Bean" + result1);
                    String horseRelId[] = (String[])remote.getAllEmailIds(res);
                    Debug.print("updBal:::" + updBal);
                    Debug.print("horseRelId:::" + horseRelId);
                    Debug.print("activateDateStatus:::" + activateDateStatus);
                    Debug.print("statusChange:::" + statusChange);
                    Debug.print("activeStatus:::" + activeStatus);
                    
                
                    if(result1 == true && res!=null){
                        String tempRiderId = request.getParameter("hlcNo");
                                             
                        String riderFName="";
                        String riderLName="";
                        String OwnerFName="";
                        String OwnerLName="";
                        String OwnerMailId="";
                        
                        String riderEmailId=request.getParameter("rEmail");
                         riderFName=request.getParameter("riderFname_id");
                        riderLName=request.getParameter("riderLname_id");
                        String membershipName1="";
                        
                        String textLimit="This horse can enter events up to and including Training level";
                        String textFull="This horse can enter any level at which it is properly qualified";
                     
                        
                    if(membtype!=null || membtype.trim().length()!=0){
                    String  sarray[] = membtype.split("#");
                    String memberTypeId = sarray[0];
                    String membershipName = sarray[1];
                    String membershipAmount = sarray[2];
                    
                    
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
                          }  
                        }
                        
                        }
                        
                    
                     if(membershipName!=null && membershipName.equalsIgnoreCase("Limited")){
                     membershipName1 = membershipName+" "+textLimit;      
                     }
                     else if(membershipName!=null && membershipName.equalsIgnoreCase("Full")){
                     membershipName1 = membershipName+" "+textFull;      
                     }
                     else{
                     membershipName1 =membershipName;  
                     }
                        /*ArrayList objRidDet = new ArrayList();
                        objRidDet = remote.getUserDets(tempRiderId);
                        if(objRidDet!=null && objRidDet.size()!=0){
                           Debug.print("objRidDet::: ***** " + objRidDet);
                          Iterator itr2 = objRidDet.iterator();
                          while(itr2.hasNext()) {
                          HLCHorseRegListVO objRiderInfo =(HLCHorseRegListVO)itr2.next();
                           riderFName=objRiderInfo.getFirstName();
                           Debug.print("riderLName:::" + riderFName);
                           riderLName=objRiderInfo.getLastName();
                           Debug.print("riderLName:::" + riderLName);
                           
                          }  
                        }*/
                        
                        if(OwnerMailId!=null && OwnerMailId.length()!=0){
                       Debug.print("OwnerMailId::: inside if condition" + OwnerMailId);     
                        String toMailIds[] = {OwnerMailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Member Registration");
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
                                "<p>Thank you for registering your horse <strong>" + horseName +" </strong> with the HLC.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ horseName +"<br />"+
                                "Horse Registration ID:"+ res+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ OwnerFName+" "+ OwnerLName+"<br/>"+
                                "Rider(s):"+ riderFName+" "+riderLName+"<br/>"+                               
                                "<p>Additional information regarding "+ horseName +" can be viewed by logging in to the HLC Online Services.<p>"+ 
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
                        
                     if(riderEmailId!=null && riderEmailId.length()!=0){
                       Debug.print("riderEmailId::: inside if condition" + riderEmailId);  
                        String toMailIds[] = {riderEmailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Member Registration");
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
                                "<p>You have been listed as the rider for <strong>" + horseName +" </strong>, recently registered with the HLC.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ horseName +"<br />"+
                                "Horse Registration ID:"+ res+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ OwnerFName+" "+ OwnerLName+"<br/>"+
                                "Rider(s):"+ riderFName+" "+riderLName+"<br/>"+                               
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
                        if(horseRelId!=null && horseRelId.length!=0){       //String toMailIds[] = {emailId};
                            EmailContent email1=new EmailContent();
                            email1.setTo(horseRelId);
                            email1.setFrom("anandv@digiblitz.com");
                            email1.setSubject("Horse Member Registration");
                            String content1 = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
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
                                    "<p>Dear User,"+
                                    
                                    "<p>You have successfully registered the horse. your horse member id is" + res + ". It will be reviewed and Approved within 24 hrs.<p>"+
                                    "Thank you for using this service.<p>"+
                                    
                                    "Thank You <br />"+
                                    "------------------ <br />"+
                                    "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                                    "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                                    "</tr>"+
                                    "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                                    "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                                    "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                                    "</tr>"+
                                    " </table>"+
                                    "</td></tr>"+
                                    "+<tr>"+
                                    "<td valign=\"top\" style=\"padding:10px;\">"+
                                    "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                                    "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                                    "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                                    "<ul>"+
                                    "<li>Unlimited shopping online.</li>"+
                                    "<li>Place advertisements online and/or on-site.</li>"+
                                    "<li>Sponsor competitions held by HLC.</li>"+
                                    "</ul>"+
                                    "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                                    "per your �Role� assigned:"+
                                    "<ul>"+
                                    "<li>Compete in Equestrian Events held by HLC.</li>"+
                                    "<li>Take part in other events like; Annual Meetings, Educational events,"+
                                    "Activity Meetings held by HLC etc.</li>"+
                                    "<li>Send Messages to other members.</li>"+
                                    "<li>Create your own Distribution Lists.</li>"+
                                    "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                                    " <li>Unlimited Shopping online.</li>"+
                                    " <li>Place advertisements online and/or on-site.</li>"+
                                    " <li>Sponsor competitions held by HLC.</li>"+
                                    "</ul>"+
                                    "and much more..."+
                                    "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                                    "</tr>"+
                                    " <tr>"+
                                    "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                                    "</tr>"+
                                    "</table>";
                            email1.setBody(content1);
                            EmailEngine emailEngine1 = new EmailEngine();
                            boolean emailFlag1 = emailEngine1.sendMimeEmail(email1);
                            Debug.print("Email sent sucessfully to horse related members:"+emailFlag1);
                            
                        }
                        
                        request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        request.setAttribute("registrationLevel",mname);
                        request.setAttribute("horseMemberId",res);
                        request.setAttribute("horseName",horseName);
                        return mapping.findForward("HorseSuccess");
                    } else{
                        Debug.print("Sorry unable to insert due to some bugs");
                        return mapping.findForward("error");
                    }
                    
                } else{
                    return mapping.findForward("error");
                }
            }
  
//---------------------------------Payment Section Ends--------------------------------------------
            
            
        } catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        return null;
    }
}
