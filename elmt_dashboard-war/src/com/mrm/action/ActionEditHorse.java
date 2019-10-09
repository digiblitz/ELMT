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
/* Program Name     : ActionEditHorse.java
 *  Created Date    : December 6, 2006, 1:48 PM
 *  Author          : Punitha.R
 *  Version         : 1.8
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
package com.mrm.action;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemote;
import com.hlcHorse.Form.Display.HLCkaverySessionBeanStatlessRemoteHome;
import com.hlccommon.util.*;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.stateless.HLCkaveryStatelessRemote;
import com.hlckavery.stateless.HLCkaveryStatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.*;
import javax.naming.Context;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.text.*;
import java.util.*;


public class ActionEditHorse extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try  {
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            
//For Horse MemberType
            String jndiname1=mr.getMessage("jndi.kaverysless");
            Object objref1 = jndiContext.lookup(jndiname1);
            HLCkaverySessionBeanStatlessRemoteHome horsehome =(HLCkaverySessionBeanStatlessRemoteHome)
            PortableRemoteObject.narrow(objref1, HLCkaverySessionBeanStatlessRemoteHome.class);
            HLCkaverySessionBeanStatlessRemote horseremote = horsehome.create();
            //For User Name
            String jname=mr.getMessage("jndi.kavery");
            Object oref = jndiContext.lookup(jname);
            HLCKaverySessionBeanStatfulRemoteHome statefulhome = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(oref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote statefulremote = statefulhome.create();
                     
            String name = "ejb/HLCKaveryMrmJNDI";
            System.out.println("\n after InitialContext Beginning emp Client...\n" + name);
            Object kaveryobj = jndiContext.lookup(name);
            HLCkaveryStatelessRemoteHome kaveryhome = (HLCkaveryStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(kaveryobj, HLCkaveryStatelessRemoteHome.class);
            HLCkaveryStatelessRemote kaveryremote = kaveryhome.create();
            System.out.println("After Create...");
            
            String jndiname2=mr.getMessage("jndi.usrreg");
            Object objref2 = jndiContext.lookup(jndiname2);
            
            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();
            
            
            HttpSession session=request.getSession();
            ArrayList hrseObj = new ArrayList();
            String process = request.getParameter("process");
            if(process.equals("desc")){
                Debug.print("Inside Describing Page");
                String memId = request.getParameter("memid");
                Debug.print("   MemberId" + memId);
                
                HLCHorseRegisterationVO HorseDisp = null;
                HLCHorseRegisterationVO OwnerHorseDisp = null;
                if(memId!=null && memId.trim().length()!=0){
                    String userId = (String) session.getAttribute("userId");
                    HorseDisp = (HLCHorseRegisterationVO)statefulremote.getHorseDetailsByHorseMemberId(memId);
                    String ownerId = HorseDisp.getOwnerId();
                    String riderId = HorseDisp.getRiderMemberId();
                    String paymentId= HorseDisp.getPaymentId();
                    String TrainerId = HorseDisp.getTrainerId();
                    
                    Debug.print("User Details owner ID "+ownerId);
                    if(ownerId!=null){
                        ArrayList ownerDetails = (ArrayList)remote.getUserContactDetails(ownerId);
                        request.setAttribute("ownerDetails",ownerDetails);
                    } else{
                        request.setAttribute("ownerDetails",null);
                    }
                    
                    Debug.print("User Details Trainer ID "+ownerId);
                    if(TrainerId!=null){
                        ArrayList TrainerDetails = (ArrayList)remote.getUserContactDetails(TrainerId);
                        request.setAttribute("TrainerDetails",TrainerDetails);
                    } else{
                        request.setAttribute("TrainerDetails",null);
                    }
                    //Rider Info Setting
                    if(riderId!=null){
                        ArrayList prevriderDet = (ArrayList)remote.getMemberContactDetails(riderId);
                        request.setAttribute("riderInfoDetails",prevriderDet);
                    } else {
                        request.setAttribute("riderInfoDetails",null);
                    }
                    ArrayList HorseDispDetails = (ArrayList)statefulremote.getHorseRelationshipDetailsDetails(memId);
                    request.setAttribute("ListVO",HorseDispDetails);
                    String[] paydet = (String[]) statefulremote.getHorsePaymentDetails(paymentId);
                    request.setAttribute("paydet",paydet);
                    
                    Debug.print("User Details owner ID "+ownerId);
                    ArrayList colorDetails = kaveryremote.getAllHorseColorDetails();
                    request.setAttribute("DisplayColorDetails", colorDetails);
                    
                    ArrayList breedDetails = kaveryremote.getAllHorseBreedDetails();
                    request.setAttribute("DisplayBreedDetails", breedDetails);
                    
                    request.setAttribute("HorseDet",HorseDisp);
                    //End Setting Info
                    boolean pri_owner = statefulremote.isHorsePriOwnerExist(memId,userId);
                    Debug.print("Primary Owner is Avilable "+pri_owner);
                    if(pri_owner==true){
                        return mapping.findForward("EditHorseRegistration");
                    } else{
                        boolean pri_rider = statefulremote.isHorsePriRiderExist(memId,userId);
                        Debug.print("Primary Rider is Avilable "+pri_rider);
                        if(pri_rider==true){
                            return mapping.findForward("EditHorseRegistration");
                        } else{
                            Debug.print("No Primary Rider & Primary Owner is Avilable");
                            if(memId!=null && memId.trim().length()!=0){
                                return mapping.findForward("PermissionDenied");
                            }
                        }
                    }
                } else{
                    return mapping.findForward(" ");
                }
            }
            if(process.equals("horseUpdate")){
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                HLCHorseDescriptionVO objHorseDesc = new HLCHorseDescriptionVO();
                String colorselect = "";
                String genderselect = "";
                String height = "";
                String foaled = "";
                String breed = "";
                String breedTwo = "";
                String countryOrigin = "";
                String sireName = "";
                String sireBreed = "";
                String sireBreedTwo = "";
                String damName = "";
                String damBreed = "";
                String damBreedTwo = "";
                String importedFrm = "";
                String importedDate = "";
                String foreignGrade = "";
                String foreignPoints = "";
                String hlcNo = "";
                String arhlcNo = "";
                String prhlcNo = "";
                String horseMemberId = "";
                if(request.getParameter("hlcNo")!=null && request.getParameter("hlcNo").trim().length()!=0){
                    hlcNo = request.getParameter("hlcNo");
                } else{
                    hlcNo = null;
                }
                if(request.getParameter("arhlcNo")!=null && request.getParameter("arhlcNo").trim().length()!=0){
                    arhlcNo = request.getParameter("arhlcNo");
                } else{
                    arhlcNo = null;
                }
                if(request.getParameter("prhlcNo")!=null && request.getParameter("prhlcNo").trim().length()!=0){
                    prhlcNo = request.getParameter("prhlcNo");
                } else{
                    prhlcNo = null;
                }
                if(request.getParameter("horseMemberId")!=null && request.getParameter("horseMemberId").trim().length()!=0){
                    horseMemberId = request.getParameter("horseMemberId");
                } else{
                    horseMemberId = null;
                }
                
                colorselect = request.getParameter("colorselect");
                genderselect = request.getParameter("genderselect");
                height = request.getParameter("height");
                foaled = request.getParameter("foaled");
                breed = request.getParameter("breed");
                breedTwo = request.getParameter("breedTwo");
                countryOrigin = request.getParameter("countryOrigin");
                sireName = request.getParameter("sireName");
                sireBreed = request.getParameter("sireBreed");
                sireBreedTwo = request.getParameter("sireBreedTwo");
                damName = request.getParameter("damName");
                damBreed = request.getParameter("damBreed");
                damBreedTwo = request.getParameter("damBreedTwo");
                importedFrm = request.getParameter("importedFrm");
                importedDate = request.getParameter("importedDate");
                foreignGrade = request.getParameter("foreignGrade");
                foreignPoints = request.getParameter("foreignPoints");
                Debug.print("colorselect:" + colorselect);
                Debug.print("genderselect:" + genderselect);
                Debug.print("height:" + height);
                Debug.print("foaled:" + foaled);
                Debug.print("breed:" + breed);
                Debug.print("breedTwo:" + breedTwo);
                Debug.print("countryOrigin:" + countryOrigin);
                Debug.print("sireName:" + sireName);
                Debug.print("sireBreed:" + sireBreed);
                Debug.print("sireBreedTwo:" + sireBreedTwo);
                Debug.print("damName:" + damName);
                Debug.print("damBreed:" + damBreed);
                Debug.print("damBreedTwo:" + damBreedTwo);
                Debug.print("importedFrm:" + importedFrm);
                Debug.print("importedDate:" + importedDate);
                Debug.print("foreignGrade:" + foreignGrade);
                Debug.print("foreignPoints:" + foreignPoints);
                Debug.print("hlcNo:" + hlcNo);
                Debug.print("arhlcNo:" + arhlcNo);
                Debug.print("prhlcNo:" + prhlcNo);
                Debug.print("horseMemberId:" +horseMemberId );
                
                if(colorselect!=null && colorselect.trim().length()!=0){
                    objHorseDesc.setColor(colorselect);
                } else{
                    objHorseDesc.setColor(null);
                }
                if(genderselect!=null && genderselect.trim().length()!=0){
                    objHorseDesc.setGender(genderselect);
                } else{
                    objHorseDesc.setGender(null);
                }
                if(height!=null && height.trim().length()!=0){
                    objHorseDesc.setHeight(height);
                } else{
                    objHorseDesc.setHeight(null);
                }
                
                if(foaled!=null && foaled.trim().length()!=0){
                    objHorseDesc.setYearFoaled(foaled);
                } else{
                    objHorseDesc.setYearFoaled(null);
                }
                if(breed!=null && breed.trim().length()!=0){
                    objHorseDesc.setBreed(breed);
                } else{
                    objHorseDesc.setBreed(null);
                }
                if(breedTwo!=null && breedTwo.trim().length()!=0){
                    objHorseDesc.setBreed2(breedTwo);
                } else{
                    objHorseDesc.setBreed2(null);
                }
                if(countryOrigin!=null && countryOrigin.trim().length()!=0){
                    objHorseDesc.setCountry(countryOrigin);
                } else{
                    objHorseDesc.setCountry(null);
                }
                
                if(sireName!=null && sireName.trim().length()!=0){
                    objHorseDesc.setSire(sireName);
                } else{
                    objHorseDesc.setSire(null);
                }
                if(sireBreed!=null && sireBreed.trim().length()!=0){
                    objHorseDesc.setSireBreed(sireBreed);
                } else{
                    objHorseDesc.setSireBreed(null);
                }
                if(sireBreedTwo!=null && sireBreedTwo.trim().length()!=0){
                    objHorseDesc.setSireBreed2(sireBreedTwo);
                } else{
                    objHorseDesc.setSireBreed2(null);
                }
                if(damName!=null && damName.trim().length()!=0){
                    objHorseDesc.setDam(damName);
                } else{
                    objHorseDesc.setDam(null);
                }
                if(damName!=null && damName.trim().length()!=0){
                    objHorseDesc.setDamBreed(damBreed);
                } else{
                    objHorseDesc.setDamBreed(null);
                }
                if(damName!=null && damName.trim().length()!=0){
                    objHorseDesc.setDamBreed2(damBreedTwo);
                } else{
                    objHorseDesc.setDamBreed2(null);
                }
                if(importedFrm!=null && importedFrm.trim().length()!=0){
                    objHorseDesc.setImportedFrom(importedFrm);
                } else{
                    objHorseDesc.setImportedFrom(null);
                }
                if(importedFrm!=null && importedFrm.trim().length()!=0){
                    objHorseDesc.setImportDate(sdf.parse(importedDate));
                } else{
                    objHorseDesc.setImportDate(null);
                }
                
                if(importedFrm!=null && importedFrm.trim().length()!=0){
                    objHorseDesc.setForeignGrade(foreignGrade);
                } else{
                    objHorseDesc.setForeignGrade(null);
                }
                if(foreignPoints!=null && foreignPoints.trim().length()!=0){
                    objHorseDesc.setForeignPoints(Float.parseFloat(foreignPoints));
                } else{
                    objHorseDesc.setForeignPoints(0);
                }
                
                Debug.print("   Rider Id:" + hlcNo);
                Debug.print("   Privious Rider" + prhlcNo);
                Debug.print("   Additional OwnerId:" + arhlcNo);
                String competitionName = request.getParameter("competitionName");
                String membertype = request.getParameter("membertype");
                Debug.print("membertype:" + membertype);
                Debug.print("competitionName:" + competitionName);
                boolean updateRes =statefulremote.updateHorseDetailsByOwner(horseMemberId,hlcNo, prhlcNo, arhlcNo, objHorseDesc);
                Debug.print("updateRes:" + updateRes);
                if(updateRes==true){
                    request.setAttribute("horseMemberId",horseMemberId);
                    request.setAttribute("horseName",competitionName);
                    request.setAttribute("registrationLevel",membertype);
                    return mapping.findForward("horseUpdateSuccess");
                } else{
                    
                }
                
                
            }
            if(process.equals("initPage")){
                Debug.print("Inside initPage");

                String trainerId = request.getParameter("trainerId");
                String horseId = request.getParameter("horseId");
                Debug.print("trainerId:" + trainerId);
                Debug.print("horseId:" + horseId);
                
                if(trainerId!=null){
                    if(!trainerId.trim().equalsIgnoreCase("null")){
                        ArrayList ownDet =(ArrayList)remote.getUserContactDetails(trainerId);
                        request.setAttribute("DispTrnrDetails",ownDet);
                    }
                }
                
                String avail = request.getParameter("avail");
                request.setAttribute("trainerId",trainerId);
                request.setAttribute("avail",avail);
                request.setAttribute("horseId",horseId);
                
                return mapping.findForward("editHorseTrainerDetails");
            }
            else if(process.equals("updateTrainer")){
                Debug.print("Inside updateTrainer ");
                HLCHorseUserVO objTrainerVO = new HLCHorseUserVO();
                
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
                String trainerUserId = "";
                String loginName ="";
                String compAddFor = "";
                String companyName="";
                String finalPrimaryPh="";
                String finalFax="";
                String prev_trainer="";
                
                boolean status = false;
                Date dob=null;
                horseId = request.getParameter("horseId");
                Debug.print("horseId:" + horseId);
                regFor= request.getParameter("regFor");
                trainerUserId = request.getParameter("userDetId");
                prev_trainer = request.getParameter("prev_trainer");
                Debug.print("prev_trainer is "+prev_trainer);
                
                String userId = (String) session.getAttribute("userId");
                if(regFor.equals("mem1")){
                    firstName = request.getParameter("firstNameOne");
                    lastName = request.getParameter("lastNameOne");
                }
                if(regFor.equals("rid1")){
                    firstName = request.getParameter("firstName");
                    lastName = request.getParameter("lastName");
                    Debug.print("Member trainerUserId:" + trainerUserId);
                }
                if(regFor.equals("acc1")){
                    firstName = request.getParameter("firstNameId1");
                    lastName = request.getParameter("lastNameId1");
                    Debug.print("Login trainerUserId:" + trainerUserId);
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
                    } else if(compAddFor.equals("no")){
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
                        trainerUserId = null;
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
                
                
                boolean res = statefulremote.editHorseTrainer(horseId,trainerUserId,objTrainerVO,status,true,prev_trainer, userId);
                Debug.print("Updated Result:" +res);
                if(res==true){
                    request.setAttribute("horseId",horseId);
                    return mapping.findForward("TrainerUpdate");
                } else{
                    return mapping.findForward("error");
                }
            }
        } 
        catch(Exception e){
            Debug.print("General Exception in Edit Horse:" + e);
        }
        return null;
    }
}
