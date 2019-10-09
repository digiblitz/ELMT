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
package com.hlcevent.activity;

import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCOraganizerRecapVO;
import com.hlccommon.util.HLCActivityUserVO;
import com.hlcevent.edu.HLCEducationalSessionRemote;
import com.hlcevent.edu.HLCEducationalSessionRemoteHome;
import javax.naming.Context;
import java.util.Properties;
import java.util.Date;
import java.util.*;



public class HLCTestClient {
    public static void main( String [] args )   {
        try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCEducationalSessionBean");
            HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCEducationalSessionRemoteHome.class);
            HLCEducationalSessionRemote eduRemote = eduHome.create();
            
            
//*================================ ActivityOrganizer  Start=======================================================================*/            
            ///============================Create ActivityOrganizer ================*/
 /*     ActivityOrganizerVO objActDet = new ActivityOrganizerVO();
        Vector publication = new Vector();
       
            publication.addElement("Punitha@digiblitz.com");
            publication.addElement("digiBLitz Mail");
            publication.addElement("Puni update");
            publication.addElement("Puni R");
            publication.addElement("10000");
   
            objActDet.setActivityMeetingId("10000");
            objActDet.setActivityName("puni");
            objActDet.setActivityDate(new Date());
            objActDet.setNoOfDays("3");
            objActDet.setUseaAreaId("01678a93-7aa2-47c4-9fa8-b33400ba4feb");
            objActDet.setLocation("HydraBed");
            objActDet.setState("Andhra");
            objActDet.setActivityOrganizerId("100000");
            objActDet.setActivityTypeId("24bb61a5-df1f-417b-b018-0843f342cd83");
            objActDet.setOtherActivityType("setOtherActivityTypepuni");
            objActDet.setActivityFees("12000");
            objActDet.setInstructorsCoaches("suresh,sury1,puni,punita");
            objActDet.setFacilities("factilities puni");
            objActDet.setOtherFacilities("other factilities puni");
            objActDet.setLandOwnerName("suresh update, punitha update");
            objActDet.setLandOwnerBusinessName("puni info com");
            objActDet.setLandOwnerAddress("suriya street ");    
            objActDet.setLandOwnerCity("Sirkali ");
            objActDet.setLandOwnerState("Tamil nadu ");   
            objActDet.setLandOwnerCountry("India ");
            objActDet.setLandOwnerZip("09876");
            objActDet.setLandOwnerPhone("9894307464");
            objActDet.setAdditionalSites(false);
            objActDet.setAdditionalSitesPath("c:/usea/puni/update/usea");
            objActDet.setAddDate(new Date());
            objActDet.setApprovedBy("Punitha");
            objActDet.setApprovedDate(new Date());
            objActDet.setPostingType("2d");
            objActDet.setActiveStatus(true);
            objActDet.setRequestStatus("Pending");
            
     */   
        //    boolean result = eduRemote.createActivityOrganizer(objActDet, publication);
        //    Debug.print("Result:" + result);
     
            ///============================Edit ActivityOrganizer ================*/
       //    boolean result = eduRemote.editActivityOrganizer(objActDet, publication);
       //    Debug.print("Result:" + result);
       
   /*   ActivityOrganizerVO objActDet = new ActivityOrganizerVO();
          Vector publication = new Vector();
           ArrayList v = (ArrayList)eduRemote.getActivityOraganizerMeeting("10001");
           Iterator e = v.iterator();
            while(e.hasNext()){
                objActDet = (ActivityOrganizerVO)e.next();
                publication = (Vector)e.next();
            }
            
            Enumeration enumPub = publication.elements();
            while(enumPub.hasMoreElements()){
                Debug.print("=============Publiccation Details================");
                Debug.print("publicationEmail" + (String) enumPub.nextElement());
                Debug.print("mailingFormat" + (String) enumPub.nextElement());
                Debug.print("mailingBy" + (String) enumPub.nextElement());
                Debug.print("mailingSortBy" + (String) enumPub.nextElement());
                Debug.print("noOfCopies" + (String) enumPub.nextElement());
                Debug.print("=============================");
            }
            Debug.print("========Activity Details=====================");
            Debug.print("" + objActDet.getActivityMeetingId());
            Debug.print("" + objActDet.getActivityName());
            Debug.print("" + objActDet.getActivityDate());
            Debug.print("" + objActDet.getNoOfDays());
            Debug.print("" + objActDet.getUseaAreaId());
            Debug.print("" + objActDet.getLocation());
            Debug.print("" + objActDet.getState());
            Debug.print("" + objActDet.getActivityOrganizerId());
            Debug.print("" + objActDet.getActivityTypeId());
            Debug.print("" + objActDet.getOtherActivityType());
            Debug.print("" + objActDet.getActivityFees());
            Debug.print("" + objActDet.getInstructorsCoaches());
            Debug.print("" + objActDet.getFacilities());
            Debug.print("" + objActDet.getOtherFacilities());
            Debug.print("" + objActDet.getLandOwnerName());
            Debug.print("" + objActDet.getLandOwnerBusinessName());
            Debug.print("" + objActDet.getLandOwnerAddress());    
            Debug.print("" + objActDet.getLandOwnerCity());
            Debug.print("" + objActDet.getLandOwnerState());   
            Debug.print("" + objActDet.getLandOwnerCountry());
            Debug.print("" + objActDet.getLandOwnerZip());
            Debug.print("" + objActDet.getLandOwnerPhone());
            Debug.print("" + objActDet.isAdditionalSites());
            Debug.print("" + objActDet.getAdditionalSitesPath());
            Debug.print("" + objActDet.getAddDate());
            Debug.print("" + objActDet.getApprovedBy());
            Debug.print("" + objActDet.getApprovedDate());
            Debug.print("" + objActDet.getPostingType());
            Debug.print("" + objActDet.isActiveStatus());
            Debug.print("" + objActDet.getRequestStatus());
            Debug.print("=============================");
        
      */
             ///============================View All Activity details================*/
  /*
       ArrayList v = (ArrayList)eduRemote.getAllActivityOraganizerMeeting();
            Iterator e = v.iterator();
            while(e.hasNext()){
                 ArrayList exaVal = (ArrayList)e.next();
                 Iterator enumPub1 = exaVal.iterator();
                 while(enumPub1.hasNext()){
                        ActivityOrganizerVO objActDet = (ActivityOrganizerVO)enumPub1.next();
                        Vector publication = (Vector)enumPub1.next();
                        Enumeration enumPub = publication.elements();
                        while(enumPub.hasMoreElements()){
                            Debug.print("=============Publication Details================");
                            Debug.print("publicationEmail" + (String) enumPub.nextElement());
                            Debug.print("mailingFormat" + (String) enumPub.nextElement());
                            Debug.print("mailingBy" + (String) enumPub.nextElement());
                            Debug.print("mailingSortBy" + (String) enumPub.nextElement());
                            Debug.print("noOfCopies" + (String) enumPub.nextElement());
                            Debug.print("=============================");
                        }
                        Debug.print("========Activity Details=====================");
                        Debug.print("" + objActDet.getActivityMeetingId());
                        Debug.print("" + objActDet.getActivityName());
                        Debug.print("" + objActDet.getActivityDate());
                        Debug.print("" + objActDet.getNoOfDays());
                        Debug.print("" + objActDet.getUseaAreaId());
                        Debug.print("" + objActDet.getLocation());
                        Debug.print("" + objActDet.getState());
                        Debug.print("" + objActDet.getActivityOrganizerId());
                        Debug.print("" + objActDet.getActivityTypeId());
                        Debug.print("" + objActDet.getOtherActivityType());
                        Debug.print("" + objActDet.getActivityFees());
                        Debug.print("" + objActDet.getInstructorsCoaches());
                        Debug.print("" + objActDet.getFacilities());
                        Debug.print("" + objActDet.getOtherFacilities());
                        Debug.print("" + objActDet.getLandOwnerName());
                        Debug.print("" + objActDet.getLandOwnerBusinessName());
                        Debug.print("" + objActDet.getLandOwnerAddress());    
                        Debug.print("" + objActDet.getLandOwnerCity());
                        Debug.print("" + objActDet.getLandOwnerState());   
                        Debug.print("" + objActDet.getLandOwnerCountry());
                        Debug.print("" + objActDet.getLandOwnerZip());
                        Debug.print("" + objActDet.getLandOwnerPhone());
                        Debug.print("" + objActDet.isAdditionalSites());
                        Debug.print("" + objActDet.getAdditionalSitesPath());
                        Debug.print("" + objActDet.getAddDate());
                        Debug.print("" + objActDet.getApprovedBy());
                        Debug.print("" + objActDet.getApprovedDate());
                        Debug.print("" + objActDet.getPostingType());
                        Debug.print("" + objActDet.isActiveStatus());
                        Debug.print("" + objActDet.getRequestStatus());
                        Debug.print("=============================");
                       
                    }
                }
       
     */
            //======================Change status Activity=====================//
        //      boolean result = eduRemote.changeStatusOrganizer("10000","Active","Your mentoiningdate ");
             // Debug.print("Result:" + result);

             //======================Get Based On status Activity=====================//
/*          ArrayList v = (ArrayList)eduRemote.getAllActivityByRequestStatus("Pending");
            Iterator e = v.iterator();
            while(e.hasNext()){
                 ArrayList exaVal = (ArrayList)e.next();
                 Iterator enumPub1 = exaVal.iterator();
                 while(enumPub1.hasNext()){
                        ActivityOrganizerVO objActDet = (ActivityOrganizerVO)enumPub1.next();
                        Vector publication = (Vector)enumPub1.next();
                        Enumeration enumPub = publication.elements();
                        while(enumPub.hasMoreElements()){
                            Debug.print("=============Publication Details================");
                            Debug.print("publicationEmail" + (String) enumPub.nextElement());
                            Debug.print("mailingFormat" + (String) enumPub.nextElement());
                            Debug.print("mailingBy" + (String) enumPub.nextElement());
                            Debug.print("mailingSortBy" + (String) enumPub.nextElement());
                            Debug.print("noOfCopies" + (String) enumPub.nextElement());
                            Debug.print("=============================");
                        }
                        Debug.print("========Activity Details=====================");
                        Debug.print("" + objActDet.getActivityMeetingId());
                        Debug.print("" + objActDet.getActivityName());
                        Debug.print("" + objActDet.getActivityDate());
                        Debug.print("" + objActDet.getNoOfDays());
                        Debug.print("" + objActDet.getUseaAreaId());
                        Debug.print("" + objActDet.getLocation());
                        Debug.print("" + objActDet.getState());
                        Debug.print("" + objActDet.getActivityOrganizerId());
                        Debug.print("" + objActDet.getActivityTypeId());
                        Debug.print("" + objActDet.getOtherActivityType());
                        Debug.print("" + objActDet.getActivityFees());
                        Debug.print("" + objActDet.getInstructorsCoaches());
                        Debug.print("" + objActDet.getFacilities());
                        Debug.print("" + objActDet.getOtherFacilities());
                        Debug.print("" + objActDet.getLandOwnerName());
                        Debug.print("" + objActDet.getLandOwnerBusinessName());
                        Debug.print("" + objActDet.getLandOwnerAddress());    
                        Debug.print("" + objActDet.getLandOwnerCity());
                        Debug.print("" + objActDet.getLandOwnerState());   
                        Debug.print("" + objActDet.getLandOwnerCountry());
                        Debug.print("" + objActDet.getLandOwnerZip());
                        Debug.print("" + objActDet.getLandOwnerPhone());
                        Debug.print("" + objActDet.isAdditionalSites());
                        Debug.print("" + objActDet.getAdditionalSitesPath());
                        Debug.print("" + objActDet.getAddDate());
                        Debug.print("" + objActDet.getApprovedBy());
                        Debug.print("" + objActDet.getApprovedDate());
                        Debug.print("" + objActDet.getPostingType());
                        Debug.print("" + objActDet.isActiveStatus());
                        Debug.print("" + objActDet.getRequestStatus());
                        Debug.print("" + objActDet.getComments());
                        Debug.print("=============================");
                       
                    }
                }
     */       
            //======================Remove Activity=====================//
         //  boolean result = eduRemote.deleteActivityOrganizer(10001);
          // Debug.print("Result:" + result);
               //======================Get My status Activity=====================//
 /*        ArrayList v = (ArrayList)eduRemote.getMyEducationalActivity("100000");
            Iterator e = v.iterator();
            while(e.hasNext()){
                 ArrayList exaVal = (ArrayList)e.next();
                 Iterator enumPub1 = exaVal.iterator();
                 while(enumPub1.hasNext()){
                        ActivityOrganizerVO objActDet = (ActivityOrganizerVO)enumPub1.next();
                        Vector publication = (Vector)enumPub1.next();
                        Enumeration enumPub = publication.elements();
                        while(enumPub.hasMoreElements()){
                            Debug.print("=============Publication Details================");
                            Debug.print("publicationEmail" + (String) enumPub.nextElement());
                            Debug.print("mailingFormat" + (String) enumPub.nextElement());
                            Debug.print("mailingBy" + (String) enumPub.nextElement());
                            Debug.print("mailingSortBy" + (String) enumPub.nextElement());
                            Debug.print("noOfCopies" + (String) enumPub.nextElement());
                            Debug.print("=============================");
                        }
                        Debug.print("========Activity Details=====================");
                        Debug.print("" + objActDet.getActivityMeetingId());
                        Debug.print("" + objActDet.getActivityName());
                        Debug.print("" + objActDet.getActivityDate());
                        Debug.print("" + objActDet.getNoOfDays());
                        Debug.print("" + objActDet.getUseaAreaId());
                        Debug.print("" + objActDet.getLocation());
                        Debug.print("" + objActDet.getState());
                        Debug.print("" + objActDet.getActivityOrganizerId());
                        Debug.print("" + objActDet.getActivityTypeId());
                        Debug.print("" + objActDet.getOtherActivityType());
                        Debug.print("" + objActDet.getActivityFees());
                        Debug.print("" + objActDet.getInstructorsCoaches());
                        Debug.print("" + objActDet.getFacilities());
                        Debug.print("" + objActDet.getOtherFacilities());
                        Debug.print("" + objActDet.getLandOwnerName());
                        Debug.print("" + objActDet.getLandOwnerBusinessName());
                        Debug.print("" + objActDet.getLandOwnerAddress());    
                        Debug.print("" + objActDet.getLandOwnerCity());
                        Debug.print("" + objActDet.getLandOwnerState());   
                        Debug.print("" + objActDet.getLandOwnerCountry());
                        Debug.print("" + objActDet.getLandOwnerZip());
                        Debug.print("" + objActDet.getLandOwnerPhone());
                        Debug.print("" + objActDet.isAdditionalSites());
                        Debug.print("" + objActDet.getAdditionalSitesPath());
                        Debug.print("" + objActDet.getAddDate());
                        Debug.print("" + objActDet.getApprovedBy());
                        Debug.print("" + objActDet.getApprovedDate());
                        Debug.print("" + objActDet.getPostingType());
                        Debug.print("" + objActDet.isActiveStatus());
                        Debug.print("" + objActDet.getRequestStatus());
                        Debug.print("=============================");
                    }
                }
        */    
            
//=============================User Activity Details =========================
            
/*            ActivityUserVO objActUser = new ActivityUserVO();
            
            //objActUser.setReleaseId(String releaseId);
            objActUser.setActivityMeetingId("10000");
            objActUser.setUserId("ff093ecd-e823-4e36-acb9-cef71534dd74");
            objActUser.setNoOfHorses("500");
            objActUser.setEventLevelId("ab9f2838-93bd-4664-a863-1be65c4efdae");
            objActUser.setMembershipStatus(true);
            objActUser.setMemberId("100001");
            objActUser.setAddDate(new Date());
            objActUser.setActiveStatus(true);
            objActUser.setRequestStatus("Pending");
 */
             //======================Insert User Activity=====================//
           // boolean result = eduRemote.createActivityUser(objActUser);
            //Debug.print("Result:" + result);
            
            //======================Status Change User Activity=====================//
            //boolean result = eduRemote.changeStatusForUser("b0d335e7-f72b-42e6-bde5-2a827b8afaca","Active");
            //Debug.print("Result:" + result);
            
         
             //======================get User Activity based on status=====================//
/*
        ArrayList al = eduRemote.getAllActivityUserByRequestStatus("10000" , "Pending");
            Iterator it = al.iterator();
            while(it.hasNext()){
                Debug.print("=============Activity User Details================");
                ActivityUserVO objActUser = (ActivityUserVO) it.next();
                Debug.print("========Activity User Details=====================");
                Debug.print("" + objActUser.getReleaseId());
                Debug.print("" + objActUser.getActivityMeetingId());
                Debug.print("" + objActUser.getUserId());
                Debug.print("" + objActUser.getEventLevelId());
                Debug.print("" + objActUser.getMemberId());
                Debug.print("" + objActUser.getNoOfHorses());
                Debug.print("" + objActUser.getRequestStatus());
                Debug.print("" + objActUser.getAddDate());
            }
   
   */         
             
        
         
             //======================get Particular User Activity =====================//
              
                HLCActivityUserVO objActUser = eduRemote.getActivityUserDetail("e4ef364b-c7b1-4fa4-bc42-1d76f0137be2");
                if(objActUser!=null){
                    Debug.print("========Activity User Details=====================");
                    Debug.print("" + objActUser.getReleaseId());
                    Debug.print("" + objActUser.getActivityMeetingId());
                    Debug.print("" + objActUser.getUserId());
                    Debug.print("" + objActUser.getEventLevelId());
                    Debug.print("" + objActUser.getMemberId());
                    Debug.print("" + objActUser.getNoOfHorses());
                    Debug.print("" + objActUser.getRequestStatus());
                    Debug.print("" + objActUser.getAddDate());
                }
       

  
            
 //======================get My User Activity =====================//

  /* 
   ArrayList al = eduRemote.getUserMyEducationalActivity("ff093ecd-e823-4e36-acb9-cef71534dd74");
            Iterator it = al.iterator();
            while(it.hasNext()){
                Debug.print("=============Activity User Details================");
                ActivityUserVO objActUser = (ActivityUserVO) it.next();
                Debug.print("========Activity User Details=====================");
                Debug.print("" + objActUser.getReleaseId());
                Debug.print("" + objActUser.getActivityMeetingId());
                Debug.print("" + objActUser.getUserId());
                Debug.print("" + objActUser.getEventLevelId());
                Debug.print("" + objActUser.getMemberId());
                Debug.print("" + objActUser.getNoOfHorses());
                Debug.print("" + objActUser.getRequestStatus());
                Debug.print("" + objActUser.getAddDate());
            }
*/            
        /*
            OraganizerRecapVO objOrgRecap = new OraganizerRecapVO();
            
            //objOrgRecap.setRecapId(String recapId);
            objOrgRecap.setActivityMeetingId("10002");
            objOrgRecap.setActivityDateChangeStatus(true);
            objOrgRecap.setActivityOrganizerId("100000");
            objOrgRecap.setNoOfRiders(100);
            objOrgRecap.setNoOfInstructors(200);
            objOrgRecap.setNoOfCurrentMembers(300);
            objOrgRecap.setNoOfNewMembers(400);
            objOrgRecap.setNoOfRenewingMembers(500);
            objOrgRecap.setNoOfFullMembers(600);
            objOrgRecap.setNoOfJuniorMembers(700);
            objOrgRecap.setNoOfNonCompetingMembers(800);
            objOrgRecap.setActivityReportDate(new Date());
            objOrgRecap.setTotalAmount(90000);
            objOrgRecap.setCloseDate(new Date());
            objOrgRecap.setComments("This is sample testing for comments 2");
            objOrgRecap.setPublishComments(false);
            objOrgRecap.setSuggestions("This is from suggestions 2");
            objOrgRecap.setAddDate(new Date());
            objOrgRecap.setActiveStatus(true);
            objOrgRecap.setRequestStatus("Active");
            
            boolean result =  eduRemote.createRecap( objOrgRecap);
            Debug.print("Result:" + result);
            
            */
            
              //======================get Particular recap Activity =====================//
            /*
                OraganizerRecapVO objOrgRecap = eduRemote.getOrganizerRecapDetails("b1396f8b-c9a2-4edf-a3c5-977dd40f7c3");
                if(objOrgRecap!=null){
                    Debug.print("========Activity User Details=====================");
                    Debug.print("" + objOrgRecap.getRecapId());
                    Debug.print("" + objOrgRecap.getActivityMeetingId());
                    Debug.print("" + objOrgRecap.getActivityOrganizerId());
                    Debug.print("" + objOrgRecap.getNoOfRiders());
                    Debug.print("" + objOrgRecap.getNoOfInstructors());
                    Debug.print("" + objOrgRecap.getNoOfCurrentMembers());
                    Debug.print("" + objOrgRecap.getNoOfNewMembers());
                    Debug.print("" + objOrgRecap.getNoOfRenewingMembers());
                    
                    Debug.print("" + objOrgRecap.getNoOfFullMembers());
                    Debug.print("" + objOrgRecap.getNoOfJuniorMembers());
                    Debug.print("" + objOrgRecap.getNoOfNonCompetingMembers());
                    Debug.print("" + objOrgRecap.getActivityReportDate());
                    Debug.print("" + objOrgRecap.getTotalAmount());
                    Debug.print("" + objOrgRecap.getCloseDate());
                    Debug.print("" + objOrgRecap.getComments());
                    Debug.print("" + objOrgRecap.isPublishComments());
                    Debug.print("" + objOrgRecap.getRequestStatus());
                }
                */
                //======================get Particular Recap by Metting ID Activity =====================//
        /*    
                ArrayList al = eduRemote.getOrganizerRecapDetailsByMeetingId("10002");
                Iterator it = al.iterator();
                while(it.hasNext()){
                    OraganizerRecapVO objOrgRecap = (OraganizerRecapVO) it.next();
                    if(objOrgRecap!=null){
                        Debug.print("========Activity User Details=====================");
                        Debug.print("" + objOrgRecap.getRecapId());
                        Debug.print("" + objOrgRecap.getActivityMeetingId());
                        Debug.print("" + objOrgRecap.getActivityOrganizerId());
                        Debug.print("" + objOrgRecap.getNoOfRiders());
                        Debug.print("" + objOrgRecap.getNoOfInstructors());
                        Debug.print("" + objOrgRecap.getNoOfCurrentMembers());
                        Debug.print("" + objOrgRecap.getNoOfNewMembers());
                        Debug.print("" + objOrgRecap.getNoOfRenewingMembers());

                        Debug.print("" + objOrgRecap.getNoOfFullMembers());
                        Debug.print("" + objOrgRecap.getNoOfJuniorMembers());
                        Debug.print("" + objOrgRecap.getNoOfNonCompetingMembers());
                        Debug.print("" + objOrgRecap.getActivityReportDate());
                        Debug.print("" + objOrgRecap.getTotalAmount());
                        Debug.print("" + objOrgRecap.getCloseDate());
                        Debug.print("" + objOrgRecap.getComments());
                        Debug.print("" + objOrgRecap.isPublishComments());
                        Debug.print("" + objOrgRecap.getRequestStatus());
                    }
                }
                
                */
                 //======================get Particular Recap by Organizer ID Activity =====================//
            
             ArrayList al = eduRemote.getOrganizerRecapDetailsByOrganizerId("100001");
                Iterator it = al.iterator();
                while(it.hasNext()){
                    HLCOraganizerRecapVO objOrgRecap = (HLCOraganizerRecapVO) it.next();
                    if(objOrgRecap!=null){
                        Debug.print("========Activity User Details=====================");
                        Debug.print("" + objOrgRecap.getRecapId());
                        Debug.print("" + objOrgRecap.getActivityMeetingId());
                        Debug.print("" + objOrgRecap.getActivityOrganizerId());
                        Debug.print("" + objOrgRecap.getNoOfRiders());
                        Debug.print("" + objOrgRecap.getNoOfInstructors());
                        Debug.print("" + objOrgRecap.getNoOfCurrentMembers());
                        Debug.print("" + objOrgRecap.getNoOfNewMembers());
                        Debug.print("" + objOrgRecap.getNoOfRenewingMembers());

                        Debug.print("" + objOrgRecap.getNoOfFullMembers());
                        Debug.print("" + objOrgRecap.getNoOfJuniorMembers());
                        Debug.print("" + objOrgRecap.getNoOfNonCompetingMembers());
                        Debug.print("" + objOrgRecap.getActivityReportDate());
                        Debug.print("" + objOrgRecap.getTotalAmount());
                        Debug.print("" + objOrgRecap.getCloseDate());
                        Debug.print("" + objOrgRecap.getComments());
                        Debug.print("" + objOrgRecap.isPublishComments());
                        Debug.print("" + objOrgRecap.getRequestStatus());
                    }
                }
     
         /*   
             ArrayList al = eduRemote.getMemberContactDetails("100001");
                Iterator it = al.iterator();
                while(it.hasNext()){
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                     Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                     Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                     Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    Debug.print("" + (String)it.next());
                    
                }
      */
            
            
            
            //*================================END of Activity Master=======================================================================*/
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static Context getInitialContext()
        throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
}
