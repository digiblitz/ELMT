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

import com.hlcaccounts.session.*;
import com.hlccommon.util.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
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
import java.util.Date;
import java.text.*;
import org.apache.struts.util.MessageResources;
import com.hlcform.stateless.*;

public class HorseRegActionAdd extends Action {

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
         
         //InsertHorseRegActionForm fbean=(InsertHorseRegActionForm)form;
         //System.out.println("InsertHorseRegActionForm ="+ fbean);
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         
         
         String process = request.getParameter("process");
         Debug.print("process function:" + process);
      
         
        if(process.equals("addRid")){
            String CardStatus = request.getParameter("cardStatus");
            String horseMemberId = request.getParameter("horseMemberId");
            String requestId = request.getParameter("requestId");
            
            Debug.print("horseMemberid in requestId:" + requestId);
            Debug.print("horseMemberid in addRid:" + horseMemberId);
            Debug.print("CardStatus in addRid:" + CardStatus);
            if(requestId!=null && requestId.trim().length()!=0){
                 request.setAttribute("requestId",requestId);
            }
            else{
                request.setAttribute("requestId",null);
            }
            
            request.setAttribute("horseMemberId",horseMemberId);
            request.setAttribute("CardStatus",CardStatus);
            return mapping.findForward("goToAddRid");
        }
        
        else if(process.equals("addRidOwn")){
            String horsesertypVect[] = {};
            horsesertypVect = (String[])remote.getHorseServiceCharge();
           // String CardStatus = request.getParameter("cardStatus");
            String horseMemberId = request.getParameter("horseMemberId");
            String horseName = (String) request.getParameter("horseName");
            Debug.print("horseMemberid in addRid:" + horseMemberId);
            //Debug.print("CardStatus in addRid:" + CardStatus);
            String amount = remote.getHorseEditCharge();
            String serTypId = remote.selectHorseServiceTypeId("Horse / Rider / Owner Change");
            
            String horseTypeName=request.getParameter("membertype");
            Debug.print("horseTypeName in addRidOwn process:" + horseTypeName);
            String competitionName=request.getParameter("competitionName");
            Debug.print("competitionName in addRidOwn process:" + competitionName);
            
            request.setAttribute("serTypId",serTypId);
            request.setAttribute("amount",amount);
            request.setAttribute("horseMemberId",horseMemberId);            
            session.setAttribute("horseName",horseName);
            request.setAttribute("horsesertypVect", horsesertypVect);
            
            request.setAttribute("horseTypeName",horseTypeName);  
            request.setAttribute("competitionName",competitionName);  
            return mapping.findForward("goToAddRidOwn");
        }
          
         
        else if(process.equals("horseReg")){
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
            String newAddFirstNameIdComp ="";
            String newAddAddressIdComp ="";
            String newAddCountryIdComp="";
            String newAddStateIdComp="";
            String newAddCityIdComp ="";
            String newAddZipIdComp ="";
            String newAddEmailIdComp ="";
            String newAddPhoneIdComp ="";
            String newAddFaxIdComp ="";
            String cpAddFirstNameIdComp ="";
            String cpAddLastNameIdComp ="";
            System.out.println("ownerUserId :" + request.getParameter("ownerUserId"));
            System.out.println("addOwnerUserId :" + request.getParameter("addOwnerUserId"));
            Date dob=null;
            Date dob1=null;
            boolean status = false;
            String loginName ="";
            String CardStatus ="";
            String tempRequestId = null;
             String requestId = null;

  //-------------------------------HORSE OWNER INFORMATION-------------------------------------------
            try{
                requestId = request.getParameter("requestId");
                if(requestId!=null && requestId.trim().length()!=0){
                    tempRequestId = requestId;
                    request.setAttribute("requestId",requestId);
                }
                else{
                    request.setAttribute("requestId",null);
                    tempRequestId = null;
                }
                CardStatus = request.getParameter("cardStatus");
                horseMemberId = request.getParameter("horseMemberId");
                Debug.print("horseMemberId:" + horseMemberId);
                riderId = request.getParameter("arhlcNo");
                regFor= request.getParameter("regAddFor");
                
                ownerUserId = request.getParameter("addOwnerUserId");
                String userId =(String)session.getAttribute("userId");
                horseRegVO.setRegisteredBy(userId);
                Debug.print(" Session userId:" + userId);
              /*
               * if rider id and owner id comes null it redirects to horseregadd.jsp
               */  
                Debug.print(" riderId :" + riderId);
                Debug.print(" regFor :" + regFor);
               if((riderId==null || riderId.trim().length()==0)  && (regFor==null || regFor.trim().length()==0)){ 
                    Debug.print("inside the loop riderId:" + riderId);
                    Debug.print("inside the loop regFor:" + regFor);
                    request.setAttribute("horseMemberId",horseMemberId);
                    return mapping.findForward("goToAddRid");
                }
                else{
                    String finalPrimaryPh="";
                    String finalFax="";
                    Debug.print("inside the else loop riderId:" + riderId);
                    Debug.print("inside the else loop regFor:" + regFor);
                       if(regFor.equals("mem1")){
                              userNameOne = request.getParameter("ownerUseaNoTwo");
                              ownerUserId = (String)userremote.getUserIdBasedOnMemberId(userNameOne);
                              Debug.print("ownerUserId from another method: "+ ownerUserId);
                        }
                        else if(regFor.equals("rid")){
                              Debug.print("rid method: "); 
                              firstName = request.getParameter("firstNameId2");
                              lastName = request.getParameter("lastNameId2");
                       }
                        else if(regFor.equals("acc")){
                              Debug.print("acc method: "); 
                              firstName = request.getParameter("firstNameId3");
                              lastName = request.getParameter("lastNameId3");
                        }
                        
                        else if(regFor.equals("cmp")){
                           Debug.print("inside the company add function:");
                            status = true;
                            compAddFor = request.getParameter("ecmp1");
                            Debug.print("compAddFor:" + compAddFor);
                              if(compAddFor.equals("yes")){
                                  loginName = request.getParameter("existAddCompNameId1");
                                  firstName = request.getParameter("cpAddfirstNameId1");
                                  lastName = request.getParameter("cpAddlastNameId1");
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
            objOwnerVO.setPhoneNo(newPhoneId);
            objOwnerVO.setFaxNo(newFaxId);
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
         
            System.out.print("cardStatus value:" + CardStatus);
            boolean result = false;
  
            if(horseMemberId!=null){
            result = remote.createHorseRiderAndOwnerDetails(horseMemberId, riderId, ownerUserId, objOwnerVO,status,false,tempRequestId);
                if(CardStatus!=null && CardStatus.equalsIgnoreCase("card")){
                boolean activeStatus = remote.updateRealtionshipStatus(horseMemberId);
                }
               
                 String sessionUser = (String)session.getAttribute("loggedBy");
                    Debug.print("sessionUser" + sessionUser);
                    if(sessionUser !=null && (sessionUser.equalsIgnoreCase("Admin") || sessionUser.equalsIgnoreCase("Usea Staff"))){
                       if(CardStatus!=null && CardStatus.equalsIgnoreCase("check")){
                            boolean checkactiveStatus = remote.updateRealtionshipStatus(horseMemberId);
                            System.out.print("checkactiveStatus" + checkactiveStatus);
                       }
                  }
           }
            
            else{
                System.out.print("Horsemember is empty:" + horseMemberId);
                return mapping.findForward("error");
            }
            
            Debug.print("result:" + result);
                if(result==true){
                    Debug.print("inside the result function");
                    request.setAttribute("horseMemberId",horseMemberId);
                    request.setAttribute("CardStatus",CardStatus);
                    return mapping.findForward("goToAddRidSuccess");
                }
                else{
                    return mapping.findForward("error");
                }
         }
    }
catch (Exception ex) {
   System.out.println("Exception occurs in Adding owner and rider details:" + ex.getMessage());
}
         
       return null;
    }
}
