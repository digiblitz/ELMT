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
package com.hlcmro.org;

import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import javax.naming.Context;
import java.util.Properties;
import java.util.*;

///import com.ejb.productmanager.ProductManagerHomeRemote;
//import com.ejb.productmanager.ProductManagerRemote;

public class HLCTestClient {
    public static void main( String [] args ){
        try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
            boolean bol = false;
            
       // RenewalOrganizerDetails objRenewalDet = new RenewalOrganizerDetails();
       // PaymentDetails objPayDet = new PaymentDetails();

    // EventDetailsVO objEventDet = new EventDetailsVO();
  /*
       objEventDet.setOrganizeId("100001");
        objEventDet.setEventTitle("Sury Title");
        objEventDet.setEventSecretaryId("2343432");
        objEventDet.setEntryFee("43000");
        objEventDet.setOtherEntryFee("2432000");
        objEventDet.setDoubleEntryFee("3424000");
         objEventDet.setOfficeFee("238000");
        objEventDet.setCheckPayableTo("USEA Eventing Association");
        objEventDet.setPinniesCharge("14200");
        objEventDet.setPinniesPosition("24");
        objEventDet.setAwardTrophy("EventAward sfs");
        objEventDet.setAwardPrize("650324");
        objEventDet.setAwardOthers("AwardOtherfsdafdsa");
        objEventDet.setDateAvailable("2010-08-08");
        objEventDet.setAvailableFrom("Availerwr");
        objEventDet.setAvailableFromOther("setAvailableFromOtherrewr");
        objEventDet.setAvailablePosition("23");
        objEventDet.setStablingLimited("0");
        objEventDet.setStallPernightPrice("20030");
        objEventDet.setPerStallPrice("424000");
        objEventDet.setPerStallFromTime("132");
        objEventDet.setPerStallFromDate("2006-03-09");
        objEventDet.setPerStallToTime("11");
        objEventDet.setPerStallToDate("2006-09-08");
        objEventDet.setNoOfStalls("322");
        objEventDet.setNoOfTempStalls("33");
        objEventDet.setNoOfTempPermanentStalls("312");
        objEventDet.setMilesFromEvent("2334");
        objEventDet.setVeterinarianName("suresh Sury");
        objEventDet.setVeterinarianPhone("9884324919");
        objEventDet.setVeterinarianPlace("Bangalore");
        objEventDet.setAccomodationCamping("setAccomodationCamping");
        objEventDet.setDirections("setDirections");
        objEventDet.setFootingDesc("sadasdsadasdasdas");
        
        Vector accommodation = new Vector();
        Vector crossCountry = new Vector();
        Vector cogginsDetails = new Vector();
        Vector divisionLevels = new Vector();
        Vector judgesDetails = new Vector();
        Vector hDressage = new Vector();
        OtherDetailsVO othersDet = new OtherDetailsVO();
        Vector refundRuleDetails = new Vector();
        Vector tentativeTime= new Vector();

        
    
        String acc1[] = {"Taj Hotel","545354","200"};
        String acc2[] = {"Taj Hotel","545354","200"};
        accommodation.addElement(acc1);
        accommodation.addElement(acc2);
        
        String cross1[] = {"Cross Division1","100","50","CourseDesc1","addINfo1"};
        String cross2[] = {"Cross Division2","200","53","CourseDesc2","addINfo2"};
        crossCountry.addElement(cross1);
        crossCountry.addElement(cross2);
        
        
        String hDress1[] = {"71aa1c31-ade7-4047-a97d-552654cba4e0","d397b258-7608-48cf-a563-5a1ebd4edd56"};
        String hDress2[] = {"71aa1c31-ade7-4047-a97d-552654cba4e0","95f78a9d-f4b6-444f-9138-d5e63c5ca332"};
        hDressage.addElement(hDress1);
        hDressage.addElement(hDress2);
        
        othersDet.setCourseCloseDate(new Date());
        othersDet.setEntryLimit("entry limit 0 to 1");
        othersDet.setRidersHorseLevelLimit("horse limit");
        othersDet.setRidersHorseEntireLimit("horse entry limit");
        othersDet.setDivisionEntryBirthDate(new Date());
        othersDet.setLeaseDogs(true);
        othersDet.setTeamCompetition("teamCompetition details");
        othersDet.setPerTimePrice("123");
        othersDet.setOtherTeamInfo("otherTeamInfo Det");
        othersDet.setPartyName("Sury Park");
        othersDet.setAddInfo("This is for testing");
        
                
        divisionLevels.add("ed9ff5bd-ab6b-435f-a0d7-aef402bb9043");
        divisionLevels.add("84308fe5-5570-4f4f-9aa1-211219bc746f");
       
        String [] jud = {"5abfce5e-8871-4a2a-9f90-4d15c5cf9d9d","sury"};
        String [] jud1 = {"6699d781-d272-4ef3-9aa8-f55a97457db7","suresh"};
        judgesDetails.add(jud);
        judgesDetails.add(jud1);
        
        
        cogginsDetails.add("alladate");
        cogginsDetails.add("inState");
        cogginsDetails.add("outOfState");
        cogginsDetails.add("noState");
        cogginsDetails.add("others");

       ArrayList al = new ArrayList();
       al.add(new Date());
       al.add("phase1");
       al.add("time1");
       
       ArrayList al2 = new ArrayList();
       al2.add(new Date());
       al2.add("phase2");
       al2.add("time3");
      
        //String [] tt1 = {"day1","phase1","time1"};
       // String [] tt2 = {"day2","phase2","time2"};
        tentativeTime.add(al);
        tentativeTime.add(al2); 
        
        
        String [] rr1 = {"c1f7da6e-abe8-4728-b9b2-767f8475995f",""};
        String [] rr2 = {"6d7253a8-22d6-4884-8003-c533c3828610","1234"};
        refundRuleDetails.add(rr1);
        refundRuleDetails.add(rr2);
        
        
        
        String eventId = remote.createEvent(objEventDet, accommodation, crossCountry,
            cogginsDetails, divisionLevels, hDressage, judgesDetails,
            othersDet, refundRuleDetails, tentativeTime);
        System.out.print("Sucessfully Inserted.." + eventId);
     */ 
      /*
         
       
            objEventDet.setEventId("10000");
            objEventDet.setEventSecretaryId("12345");
            objEventDet.setEntryFee("23000");
            objEventDet.setOtherEntryFee("14000");
            objEventDet.setDoubleEntryFee("55000");
            objEventDet.setOfficeFee("60006");
            objEventDet.setCheckPayableTo("SHarma");
            objEventDet.setPinniesCharge("12300");
            objEventDet.setPinniesPosition("23");
            objEventDet.setAwardTrophy("EventAward3");
            objEventDet.setAwardPrize("6500330");
            objEventDet.setAwardOthers("AwardO33ther");
            objEventDet.setDateAvailable("2007-08-04");
            objEventDet.setAvailableFrom("Availd");
            objEventDet.setAvailableFromOther("setAvailableFromOtherds");
            objEventDet.setAvailablePosition("42");
            objEventDet.setStablingLimited("41");
            objEventDet.setStallPernightPrice("20004");
            objEventDet.setPerStallPrice("64000");
            objEventDet.setPerStallFromTime("12");
            objEventDet.setPerStallFromDate("2010-10-09");
            objEventDet.setPerStallToTime("1134");
            objEventDet.setPerStallToDate("2008-09-08");
            objEventDet.setNoOfStalls("24");
            objEventDet.setNoOfTempStalls("34");
            objEventDet.setNoOfTempPermanentStalls("1234");
            objEventDet.setMilesFromEvent("23434");
            objEventDet.setVeterinarianName("suresh kumar");
            objEventDet.setVeterinarianPhone("944367464");
            objEventDet.setVeterinarianPlace("Bangalore");
            objEventDet.setAccomodationCamping("setAccomodationCamping");
            objEventDet.setDirections("setDirections");
            objEventDet.setFootingDesc("Suresh setFootingDesc");
            remote.editEvent(objEventDet);
        
        */    
         
           
//=======================Get All Event Details =============================            
         /* ArrayList al = remote.getAllOrganizerEventDetails("100002");
            Debug.print("Process Started..");
            Iterator it = al.iterator();
            Debug.print("Process Iterator.. Length:" + al.size());
            
          while(it.hasNext()){
                Debug.print("Inside Loop Iterator..");
               EventDetailsVO objEventDet = (EventDetailsVO)it.next();
                //Debug.print("========Successfully Editted========================="); 
                //EventDetailsVO objEventDet = remote.getEventDetails(10000);
                Debug.print("================================="); 
                Debug.print("Event ID:"+objEventDet.getEventId());
                Debug.print("=================================");
                Debug.print(objEventDet.getOrganizeId());
                Debug.print( objEventDet.getEventSecretaryId());
                Debug.print(objEventDet.getEntryFee());
                Debug.print(objEventDet.getOtherEntryFee());
                Debug.print(objEventDet.getOfficeFee());
                Debug.print(objEventDet.getCheckPayableTo());
                Debug.print(objEventDet.getPinniesCharge());
                Debug.print(objEventDet.getPinniesPosition());
                Debug.print(objEventDet.getAwardTrophy());        
                Debug.print(objEventDet.getAwardPrize());
                Debug.print(objEventDet.getAwardOthers());
                Debug.print(objEventDet.getDateAvailable());
                Debug.print(objEventDet.getAvailableFrom());
                Debug.print(objEventDet.getAvailableFromOther());
                Debug.print(objEventDet.getAvailablePosition());
                Debug.print(objEventDet.getStablingLimited());
                Debug.print(objEventDet.getStallPernightPrice());
                Debug.print(objEventDet.getPerStallPrice());
                Debug.print(objEventDet.getPerStallFromTime());
                Debug.print(objEventDet.getPerStallFromDate());
                Debug.print(objEventDet.getPerStallToTime());
                Debug.print(objEventDet.getPerStallToDate());
                Debug.print(objEventDet.getNoOfStalls());
                Debug.print(objEventDet.getNoOfTempStalls());
                Debug.print(objEventDet.getNoOfTempPermanentStalls());
                Debug.print(objEventDet.getMilesFromEvent());
                Debug.print(objEventDet.getVeterinarianName());
                Debug.print(objEventDet.getVeterinarianPhone());
                Debug.print(objEventDet.getVeterinarianPlace());
                Debug.print(objEventDet.getAccomodationCamping());
                Debug.print(objEventDet.getDirections());
                Debug.print(objEventDet.getFootingDesc());
                Debug.print("=================================");
          }*/
         
        /*=======================edit Tantative Time =============================*/
            Vector tentativeTime= new Vector();
            
            ArrayList ttl = new ArrayList();
            ttl.add(new Date());
            ttl.add("phase1");
            ttl.add("time1");

            ArrayList tt2 = new ArrayList();
            tt2.add(new Date());
            tt2.add("phase2");
            tt2.add("time3");

            tentativeTime.add(ttl);
            tentativeTime.add(tt2);
            
        /*===========================Edit Event Details==============================*/
            
            HLCEventDetailsVO objEventDet = new HLCEventDetailsVO();
            
            objEventDet.setEventId("10000");
            objEventDet.setEventSecretaryId("100008");
            objEventDet.setEntryFee("23000");
            objEventDet.setOtherEntryFee("14000");
            objEventDet.setDoubleEntryFee("55000");
            objEventDet.setOfficeFee("60006");
            objEventDet.setMembershipApplicable("Hello Hello Hello");
            objEventDet.setCheckPayableTo("SHarma");
            objEventDet.setPinniesCharge("12300");
            objEventDet.setPinniesPosition("23");
            objEventDet.setAwardTrophy("EventAward3");
            objEventDet.setAwardPrize("6500330");
            objEventDet.setAwardOthers("AwardO33ther");
            objEventDet.setDateAvailable("2007-08-04");
            objEventDet.setAvailableFrom("Availd");
            objEventDet.setAvailableFromOther("setAvailableFromOtherds");
            objEventDet.setAvailablePosition("42");
            objEventDet.setStablingLimited("41");
            objEventDet.setStallPernightPrice("20004");
            objEventDet.setPerStallPrice("64000");
            objEventDet.setPerStallFromTime("12");
            objEventDet.setPerStallFromDate("2010-10-09");
            objEventDet.setPerStallToTime("1134");
            objEventDet.setPerStallToDate("2008-09-08");
            objEventDet.setNoOfStalls("24");
            objEventDet.setNoOfTempStalls("34");
            objEventDet.setNoOfTempPermanentStalls("1234");
            objEventDet.setMilesFromEvent("23434");
            objEventDet.setVeterinarianName("suresh kumar");
            objEventDet.setVeterinarianPhone("944367464");
            objEventDet.setVeterinarianPlace("Bangalore");
            objEventDet.setAccomodationCamping("setAccomodationCamping");
            objEventDet.setDirections("setDirections");
            objEventDet.setFootingDesc("Suresh setFootingDesc");
            objEventDet.setComments("XXXXXXXXXXXXX");
            //remote.editEvent(objEventDet);
        /*=======================edit Cross Country =============================*/
            //String eventId = "10004"; 
            Vector crossCountry = new Vector();
          //  division,length,speed,courseDescription,addInformation
            ArrayList cc1 = new ArrayList();
            cc1.add("First");
            cc1.add("22");
            cc1.add("55km per hr");
            cc1.add("Course Level One");
            cc1.add("Highly Intersted");

            ArrayList cc2 = new ArrayList();
            cc2.add("Second");
            cc2.add("33");
            cc2.add("44km per hr");
            cc2.add("Course Level Two");
            cc2.add("fairly Intersted");
            
            ArrayList cc3 = new ArrayList();
            cc3.add("Third");
            cc3.add("44");
            cc3.add("66km per hr");
            cc3.add("Course Level Three");
            cc3.add("Not Intersted");

            crossCountry.add(cc1);
            crossCountry.add(cc2);
            crossCountry.add(cc3);
            
           /* boolean bol = remote.editCrossCountry(eventId, crossCountry);
            if (bol == true){
                Debug.print("Successfully Updated Cross Country : "+bol);
            } */
           /*=====================edit Horse Dressage============================================*/
            //String eventId1 = "10005";
            Vector hDressage = new Vector();
            
            ArrayList hD1 = new ArrayList();
            hD1.add("3fd70d20-65d3-493c-945b-4e5b883aa3e8");
            hD1.add("02461d66-f919-4a42-8a7c-431b4d3ddc9e");
            
            ArrayList hD2 = new ArrayList();
            hD2.add("e636f3de-3e17-4370-92ae-3e769d5e5bd2");
            hD2.add("d397b258-7608-48cf-a563-5a1ebd4edd56");
            
            ArrayList hD3 = new ArrayList();
            hD3.add("ed3843b2-d591-4c4c-970b-b4fe4af4b8c6");
            hD3.add("95f78a9d-f4b6-444f-9138-d5e63c5ca332");
            
            hDressage.add(hD1);
            hDressage.add(hD2);
            hDressage.add(hD3);
            /*bol = remote.editHorseDressage(eventId, hDressage);
             if (bol == true){
                Debug.print("Successfully Updated Horse Dressage : "+bol);
            }*/
            
            
            /*=====================edit Judge Details============================================*/
           // String eventId = "10005";
            Vector jDetail = new Vector();
            
            ArrayList jD1 = new ArrayList();
            jD1.add("07b51f6b-dcfd-46df-ad8a-55e8be550a4c"); //judge type id
            jD1.add("President of the Ground Jury (Eventing Judge)"); //judge names
            
            ArrayList jD2 = new ArrayList();
            jD2.add("1bd72e4f-6c7c-49f1-8526-dc69dc7ef051"); //judge type id
            jD2.add("Judges: (Eventing Judges or Dressage Judges): ");//judge name
            
            ArrayList jD3 = new ArrayList();
            jD3.add("8d91d36b-189d-49fe-89f9-948d609e39bd"); //judge type id
            jD3.add("X-C Course Designer: (Must be licensed CD for Preliminary & Above): "); //judge names
            
            jDetail.add(jD1);
            jDetail.add(jD2);
            jDetail.add(jD3);
            /*bol = remote.editJudgeDetails(eventId, jDetail);
             if (bol == true){
                Debug.print("Successfully Updated Judge Details : "+bol);
            }*/
            
            /*=====================edit Division Level============================================*/
           // String eventId = "10005";
            Vector dLevel = new Vector();
            
            ArrayList dL1 = new ArrayList();
            dL1.add("6973be77-dbda-434b-910c-1672049f725e"); //map type id
            
            ArrayList dL2 = new ArrayList();
            dL2.add("84308fe5-5570-4f4f-9aa1-211219bc746f"); //map type id
            
            ArrayList dL3 = new ArrayList();
            dL3.add("ed9ff5bd-ab6b-435f-a0d7-aef402bb9043"); //map type id

            
            dLevel.add(dL1);
            dLevel.add(dL2);
            dLevel.add(dL3);
            /*bol = remote.editDivisionLevel(eventId, dLevel);
             if (bol == true){
                Debug.print("Successfully Updated Division Level : "+bol);
            }*/
            
            /*=====================edit Accomodation details============================================*/
           // String eventId = "10005";
            Vector accomodation = new Vector();
            
            ArrayList aD1 = new ArrayList();
            aD1.add("Hotel 1 Star"); //Hotel Name
            aD1.add("1234567"); //Hotel Phone
            aD1.add("45"); //Miles From event
            
            ArrayList aD2 = new ArrayList();
            aD2.add("Hotel 2 Star"); //Hotel Name
            aD2.add("98765432"); //Hotel Phone
            aD2.add("56"); //Miles From event
            
            ArrayList aD3 = new ArrayList();
            aD3.add("Hotel 3 Star"); //Hotel Name
            aD3.add("5643789"); //Hotel Phone
            aD3.add("88"); //Miles From event

            
            accomodation.add(aD1);
            accomodation.add(aD2);
            accomodation.add(aD3);
            /*bol = remote.editAccomodation(eventId, accomodation);
             if (bol == true){
                Debug.print("Successfully Updated Accomodation Details : "+bol);
            }*/
            
            /*=====================edit Refund Rule details============================================*/
            //String eventId = "10005";
            Vector refundRuleDet = new Vector();
            
            ArrayList rRD1 = new ArrayList();
            rRD1.add("3371cad4-b93c-4730-9c27-d501dcab6495"); //refind map id
            rRD1.add("50.00"); //amount
            
            ArrayList rRD2 = new ArrayList();
            rRD2.add("6d7253a8-22d6-4884-8003-c533c3828610"); //refund mao id
            rRD2.add("60.00"); //amount
            
            ArrayList rRD3 = new ArrayList();
            rRD3.add("c1f7da6e-abe8-4728-b9b2-767f8475995f"); //refund map id
            rRD3.add("70.00"); //amount
            
            refundRuleDet.add(rRD1);
            refundRuleDet.add(rRD2);
            refundRuleDet.add(rRD3);
           /* bol = remote.editRefundRuleDetails(eventId, refundRuleDet);
             if (bol == true){
                Debug.print("Successfully Updated Refund Rule Details : "+bol);
            }

            /*=====================edit Coggins details============================================*/
            
           Vector cogginsDet = new Vector();
            
            String eventId = "10000";
            String allState = " all";
            String inState = "all";
            String outOfState = "all";
            String noState = "all";
            String others = "all";
            cogginsDet.add(eventId);
            cogginsDet.add(allState);
            cogginsDet.add(inState);
            cogginsDet.add(outOfState);
            cogginsDet.add(noState);
            cogginsDet.add(others);
            
            
            
            
            /*bol = remote.editCogginsDetail(cogginsDet);
             if (bol == true){
                Debug.print("Successfully Updated Coggings Details : "+bol);
            }*/
            
           /*======================edit Other Details========================= */
            
            HLCOtherDetailsVO othersDet = new HLCOtherDetailsVO();
            othersDet.setEventId("10001");
            othersDet.setCourseCloseDate(new Date());
            othersDet.setEntryLimit("entry limit 0 to 1");
            othersDet.setRidersHorseLevelLimit("horse limit");
            othersDet.setRidersHorseEntireLimit("horse entry limit");
            othersDet.setDivisionEntryBirthDate(new Date());
            othersDet.setLeaseDogs(true);
            othersDet.setTeamCompetition("teamCompetition details");
            othersDet.setPerTimePrice("123");
            othersDet.setOtherTeamInfo("otherTeamInfo Det");
            othersDet.setPartyName("Sury Park");
            othersDet.setAddInfo("This is for testing");
            
           /* bol = remote.editOtherDetails(othersDet);
             if (bol == true){
                Debug.print("Successfully Updated Other Details : "+bol);
             }*/
            
            /*====================Edit eventOrganizer ========================*/
           /* bol = remote.editEventOrganizer(objEventDet, accomodation, crossCountry,
            cogginsDet, dLevel, hDressage, jDetail,
            othersDet, refundRuleDet, tentativeTime);
             if (bol == true){
                Debug.print("Successfully Updated Event Organizer Details : "+bol);
             }*/
            
            
            /*==============================Event Calendare===============================*/
            /*bol = remote.eventDateAvailability("12/26/2006");
            if (bol == true){
                Debug.print("Availability of event date status is  : "+bol);
            }else {
                Debug.print("Availability of event date status is  : "+bol);
            }*/
            
           /* String eventLevelId = "40fe0ad2-2bd3-4a01-998a-1be039765248";
            String eventSubLevelId = "0d443793-4bc9-4719-ad1b-e7f22f3cbfe9";
            bol = remote.mapDressageTest(eventLevelId,eventSubLevelId);
            if (bol == true){
                Debug.print("Successfully inserted in to Map Event Sub Level  : "+bol);
            }else {
                Debug.print("Successfully inserted in to Map Event Sub Level  : "+bol);
            }*/
            
          /*  String refundRuleTypeId = "cdedf25f-acfb-404a-b644-288c3a02047f";
            String refundRuleSubTypeId = "6ec1a5e7-2f99-4841-a18f-028ec1edad72";
            bol = remote.mapRefundRule(refundRuleTypeId,refundRuleSubTypeId);
            if (bol == true){
                Debug.print("Successfully inserted in to Map Refund Rule  : "+bol);
            }else {
                Debug.print("Successfully inserted in to Map Refund Rule  : "+bol);
            }*/
            
            
           // Refund displayAllRefundRuleSubType
            
           /* Vector refRule=new Vector();
            refRule=(Vector)remote.displayAllRefundRuleSubType(false);
            
            if(refRule!=null)
            {
                Debug.print("refRule.size() :" +refRule.size());
                
                Enumeration enumVal=(Enumeration)refRule.elements();
                
                while(enumVal.hasMoreElements())
                {
                   String[] refundSubName = (String[])enumVal.nextElement();
                   
                   Debug.print("refundSubName[0] :" +refundSubName[0]);
                   Debug.print("refundSubName[1] :" +refundSubName[1]);
                   Debug.print("refundSubName[2] :" +refundSubName[2]);
                   Debug.print("refundSubName[3] :" +refundSubName[3]);
                      
                   
                }
            }*/
            
             // Refund displayAllRefundRule
            
           /* Vector refRuleMaster=new Vector();
            refRuleMaster=(Vector)remote.displayAllRefundRule(true);
            
            if(refRuleMaster!=null)
            {
                Debug.print("refRuleMaster.size() :" +refRuleMaster.size());
                
                Enumeration enumVal=(Enumeration)refRuleMaster.elements();
                
                while(enumVal.hasMoreElements())
                {
                   String[] refundMasterName = (String[])enumVal.nextElement();
                   
                   Debug.print("refundMasterName[0] :" +refundMasterName[0]);
                   Debug.print("refundMasterName[1] :" +refundMasterName[1]);
                   Debug.print("refundMasterName[2] :" +refundMasterName[2]);
                   Debug.print("refundMasterName[3] :" +refundMasterName[3]);
                      
                   
                }
            }*/
            
             // judge displayAllJudgetype
            
         /*   Vector judgeMaster=new Vector();
            judgeMaster=(Vector)remote.displayAllJudgetype(true);
            
            if(judgeMaster!=null)
            {
                Debug.print("judgeMaster.size() :" +judgeMaster.size());
                
                Enumeration enumVal=(Enumeration)judgeMaster.elements();
                
                while(enumVal.hasMoreElements())
                {
                   String[] judgeMasterVal = (String[])enumVal.nextElement();
                   
                   Debug.print("judgeMasterVal[0] :" +judgeMasterVal[0]);
                   Debug.print("judgeMasterVal[1] :" +judgeMasterVal[1]);
                   Debug.print("judgeMasterVal[2] :" +judgeMasterVal[2]);
                   Debug.print("judgeMasterVal[3] :" +judgeMasterVal[3]);
                      
                   
                }
            }
            */
            
            //  displayAllEventTypeMaster
            
         /*   Vector eveTypMaster=new Vector();
            eveTypMaster=(Vector)remote.displayAllEventTypeMaster(true);
            
            if(eveTypMaster!=null)
            {
                Debug.print("eveTypMaster.size() :" +eveTypMaster.size());
                
                Enumeration enumVal=(Enumeration)eveTypMaster.elements();
                
                while(enumVal.hasMoreElements())
                {
                   String[] eveTypMasterVal = (String[])enumVal.nextElement();
                   
                   Debug.print("eveTypMasterVal[0] :" +eveTypMasterVal[0]);
                   Debug.print("eveTypMasterVal[1] :" +eveTypMasterVal[1]);
                   Debug.print("eveTypMasterVal[2] :" +eveTypMasterVal[2]);
                   Debug.print("eveTypMasterVal[3] :" +eveTypMasterVal[3]);
                      
                   
                }
            }*/
            
             //  displayAllEventTypeMaster
            
           /* Vector eveLevelMaster=new Vector();
            eveLevelMaster=(Vector)remote.displayAllEventLevelMaster(false);
            
            if(eveLevelMaster!=null)
            {
                Debug.print("eveLevelMaster.size() :" +eveLevelMaster.size());
                
                Enumeration enumVal=(Enumeration)eveLevelMaster.elements();
                
                while(enumVal.hasMoreElements())
                {
                   String[] eveLevelMasterVal = (String[])enumVal.nextElement();
                   
                   Debug.print("eveLevelMasterVal[0] :" +eveLevelMasterVal[0]);
                   Debug.print("eveLevelMasterVal[1] :" +eveLevelMasterVal[1]);
                   Debug.print("eveLevelMasterVal[2] :" +eveLevelMasterVal[2]);
                   Debug.print("eveLevelMasterVal[3] :" +eveLevelMasterVal[3]);
                   Debug.print("eveLevelMasterVal[4] :" +eveLevelMasterVal[4]);
                   Debug.print("eveLevelMasterVal[5] :" +eveLevelMasterVal[5]);   
                   
                }
            }*/
            
           /* ArrayList eventDet = remote.getDetailedEvents("10001");
            if(eventDet != null && eventDet.size() != 0)
            {
                    System.out.print(" eventDet.size() :" +eventDet.size());

                    objEventDet=(EventDetailsVO)eventDet.get(0);
                    Vector divisionLevels=(Vector)eventDet.get(1);
                    Vector judgesDetails=(Vector)eventDet.get(2);
                    Vector refundRuleDetails=(Vector)eventDet.get(3);
                    Vector cogginsDetails=(Vector)eventDet.get(4);
                    tentativeTime=(Vector)eventDet.get(5);
                    hDressage=(Vector)eventDet.get(6);
                    crossCountry=(Vector)eventDet.get(7);
                    othersDet=(OtherDetailsVO)eventDet.get(8);
                    Vector accommodation=(Vector)eventDet.get(9);
                    
                    Debug.print("objEventDet Date Available..."+objEventDet.getDateAvailable());
                    Debug.print("objEventDet Add Date..."+objEventDet.getAddDate());
                    Debug.print("\nobjEventDet ...\n"+objEventDet);
                    Debug.print("divisionLevels ...\n"+divisionLevels);
                    Debug.print("judgesDetails ...\n"+judgesDetails);
                    Debug.print("refundRuleDetails ...\n"+refundRuleDetails);
                    Debug.print("cogginsDetails ...\n"+cogginsDetails);
                    Debug.print("tentativeTime ...\n"+tentativeTime);
                    Debug.print("hDressage ...\n"+hDressage);
                    Debug.print("crossCountry ...\n"+crossCountry);
                    Debug.print("othersDet ...\n"+othersDet);
                    Debug.print("accommodation ...\n"+accommodation);
                   
            }	*/
            
        String str = "2006-02-24";//objEventDet.getDateAvailable();
       /* String finalDate = null;
        String pattern = "2006";
        String replace= "2007";
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e+pattern.length();
        }
        result.append(str.substring(s));
       System.out.println("The String is : "+result.toString());
       
       finalDate = result.toString();
       String sArr[] = result.toString().split("-");
       result = new StringBuffer();
       GregorianCalendar cal = new GregorianCalendar();
        boolean b = cal.isLeapYear(Integer.parseInt(replace)); 
        if (b == true){
            System.out.println("Yes the YEAR : "+replace+"  is a Leap Year.");            
        }else {
            if (Integer.parseInt(sArr[2]) >28 ){
                sArr[2] = "01";
                sArr[1] = "03";
                Debug.print(" The Date is 1 : "+sArr[0]+"-"+sArr[1]+"-"+sArr[2]);
                result.append(sArr[0]).append("-").append(sArr[1]).append("-").append(sArr[2]);
                Debug.print(" The Date is 2 : "+result.toString());
                finalDate = result.toString();
            }
        }
        Debug.print(" Date available : "+finalDate); */
   // }
        boolean bol1 = remote.generateCalendarBasedOnYear("2006","2007");
        Debug.print("Calendar Created : "+bol1);
        
        Debug.print("CALENDAR GET METHODS");
        Debug.print("=================================================================");
        Calendar c = Calendar.getInstance();

        Debug.print("  YEAR                 : " + c.get(Calendar.YEAR));
        Debug.print("  MONTH                : " + c.get(Calendar.MONTH));
        Debug.print("  DAY_OF_MONTH         : " + c.get(Calendar.DAY_OF_MONTH));
        Debug.print("  DAY_OF_WEEK          : " + c.get(Calendar.DAY_OF_WEEK));
        Debug.print("  DAY_OF_YEAR          : " + c.get(Calendar.DAY_OF_YEAR));
        Debug.print("  WEEK_OF_YEAR         : " + c.get(Calendar.WEEK_OF_YEAR));
        Debug.print("  WEEK_OF_MONTH        : " + c.get(Calendar.WEEK_OF_MONTH));
        Debug.print("  DAY_OF_WEEK_IN_MONTH : " + c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        Debug.print("  HOUR                 : " + c.get(Calendar.HOUR));
        Debug.print("  AM_PM                : " + c.get(Calendar.AM_PM));
        Debug.print("  HOUR_OF_DAY (24-hour): " + c.get(Calendar.HOUR_OF_DAY));
        Debug.print("  MINUTE               : " + c.get(Calendar.MINUTE));
        Debug.print("  SECOND               : " + c.get(Calendar.SECOND));
        
       /* List list = remote.getCurrentYearCalendar("A1");
        for (int i=0;i<list.size(); i++){
            CalendarVO calVO = (CalendarVO)list.get(i);
            Debug.print("=============================================================");
            Debug.print("  OrganizerName :  " +calVO.getOrganizerName());
            Debug.print("  OrganizerId   :  " +calVO.getOrganizerId());
            Debug.print("  EventName     :  " +calVO.getEventName());
            Debug.print("  EventArea     :  " +calVO.getEventArea());
            Debug.print("  EventType     :  " +calVO.getEventType());
            Debug.print("  EventLevel    :  " +calVO.getEventLevel());
            Debug.print("  CalEventId    :  " +calVO.getCalEventId());
            Debug.print("=============================================================");
        }*/

       
            
        }
        catch( Exception e )
        {
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
