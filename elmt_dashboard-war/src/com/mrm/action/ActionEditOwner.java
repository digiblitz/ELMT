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
/*  Program Name    : ActionEditOwner.java
 *  Created Date    : December 1, 2006, 5:57 PM
 *  Author          : Punitha.R
 *  Version         : 1.3
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
package com.mrm.action;

import com.hlccommon.util.*;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
 
 
import javax.servlet.http.*;
import java.util.regex.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.text.*;

 
public class ActionEditOwner extends Action {
  
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
try{
      String finalPrimaryPh="";
      String finalFax="";
        HttpSession session=request.getSession();
        MessageResources mr=getResources(request);

        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.kavery");

        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        
        Object objref = jndiContext.lookup(jndiname);
       
        HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
        HLCKaverySessionBeanStatfulRemote remote = home.create();
                                
        String jndiname2=mr.getMessage("jndi.usrreg");
        Object objref2 = jndiContext.lookup(jndiname2);

        HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote1 = home1.create(); 
                
                
                String changeProcess = request.getParameter("changeProcess");
                
                 HLCHorseUserVO objOwnerVO = new HLCHorseUserVO();
                
                if(changeProcess.equals("initPage")){
                        String ownerId = request.getParameter("ownerId");
                        String horseId = request.getParameter("horseId");
                         Debug.print("ownerId:" + ownerId);
                         Debug.print("horseId:" + horseId);

                      ArrayList ownDet =(ArrayList)remote1.getUserContactDetails(ownerId);
                        request.setAttribute("DispOwnDetails",ownDet);
                        request.setAttribute("horseId",horseId);
             
             return mapping.findForward("editHorseOwnerDetails");
        }
             else  if(changeProcess.equals("updateOwner")){
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                String horseId="";
                String firstName = "";
                String lastName = "";
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
                String regFor="";
                String ownerUserId = "";
                String loginName ="";
                String compAddFor = "";
                String companyName="";
                
                boolean status = false;
                Date dob=null;
                horseId = request.getParameter("horseId"); 
                Debug.print("horseId:" + horseId);
                regFor= request.getParameter("regFor");
                ownerUserId = request.getParameter("userDetId");
                if(regFor.equals("mem1")){
                    firstName = request.getParameter("firstNameOne");
                    lastName = request.getParameter("lastNameOne");
                }
                if(regFor.equals("rid1")){
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    Debug.print("Member ownerUserId:" + ownerUserId);
                }
                if(regFor.equals("acc1")){
                    firstName = request.getParameter("firstNameId1");
                    lastName = request.getParameter("lastNameId1");
                    Debug.print("Login ownerUserId:" + ownerUserId);
                }
                 
                if(regFor.equals("cmp1")){
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
                      }
                      else if(compAddFor.equals("no")){
                            loginName = request.getParameter("newFirstNameIdComp");
                            newAddressId = request.getParameter("newAddressIdComp");
                            newCountryId = request.getParameter("newCountryIdComp");
                            newStateId = request.getParameter("newStateIdComp");
                            newCityId = request.getParameter("newCityIdComp");
                            newZipId = request.getParameter("newZipIdComp");
                            newEmailId = request.getParameter("newEmailIdComp");
                            newPhoneId = request.getParameter("newPhoneIdComp");
                            newFaxId = request.getParameter("newFaxIdComp");
                            firstName = request.getParameter("cpFirstNameIdComp");
                            lastName = request.getParameter("cpLastNameIdComp");
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
                
                 
                  /*  System.out.println("======================================================");
                    System.out.println("=====================First Part Starts================");
                    System.out.println("======================================================");
                    System.out.println("ownerUserId :" +ownerUserId);
                    System.out.println(" regFor :" + regFor);
                    System.out.println(" firstName :" + firstName);
                    System.out.println(" lastName :" + lastName);
                    System.out.println(" ownerUserId :" + ownerUserId);
                    System.out.println(" newBirthmonthId :" + newBirthmonthId);
                    System.out.println(" newBirthdayId :" + newBirthdayId);
                    System.out.println(" newBirthyearId :" + newBirthyearId);
                    System.out.println(" sexId :" + sexId);
                    System.out.println(" newAddressId :" + newAddressId);
                    System.out.println(" newCountryId :" + newCountryId);
                    System.out.println(" newStateId :" + newStateId);
                    System.out.println("  newCityId:" + newCityId);
                    System.out.println(" newZipId :" + newZipId);
                    System.out.println(" newPhoneId :" + newPhoneId);
                    System.out.println("  newFaxId:" + newFaxId); */ 
                    boolean res = remote.editHorseOwner(horseId,ownerUserId,objOwnerVO,status,true);
                    Debug.print("Updated Result:" +res);
                    if(res==true){
                         request.setAttribute("horseId",horseId);
                         return mapping.findForward("ownerUpdate");
                    }
                    else{
                         return mapping.findForward("error");
                    }

        }
}
catch(Exception e){
    Debug.print("Exception occurs:" + e);
    
}
        return null;
        
    }
}
