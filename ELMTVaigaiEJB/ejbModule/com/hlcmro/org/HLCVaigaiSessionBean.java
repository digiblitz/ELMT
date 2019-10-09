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
/*  Program Name    : VaigaiSessionBean.java
 *  Created Date    : Aug 18, 2006 5:45:38 PM
 *  Author          : Suresh.K
 *  Version         : 1.20
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.hlcmro.org;

import com.hlcmro.CRUD.HLCCRUDMasterDAO;
import com.hlcmro.dao.HLCEventsDAO;
import com.hlcmro.event.calendar.HLCEventCalDAO;
import com.hlcmro.exception.HLCMissingPrimaryKeyException;
import com.hlcmro.masters.HLCAccommodationDetailLocal;
import com.hlcmro.masters.HLCAccommodationDetailLocalHome;
import com.hlcmro.masters.HLCCogginsDetailLocal;
import com.hlcmro.masters.HLCCogginsDetailLocalHome;
import com.hlcmro.masters.HLCCrossCountryLocal;
import com.hlcmro.masters.HLCCrossCountryLocalHome;
import com.hlcmro.masters.HLCEventTypeLocal;
import com.hlcmro.masters.HLCEventTypeLocalHome;
import com.hlcmro.masters.HLCHorseDressageLocal;
import com.hlcmro.masters.HLCHorseDressageLocalHome;
import com.hlcmro.masters.HLCJudgeDetailLocal;
import com.hlcmro.masters.HLCJudgeDetailLocalHome;
import com.hlcmro.masters.HLCOtherDetailLocal;
import com.hlcmro.masters.HLCOtherDetailLocalHome;
import com.hlcmro.masters.HLCRefundRuleDetailLocal;
import com.hlcmro.masters.HLCRefundRuleDetailLocalHome;
import com.hlcmro.masters.HLCTentativeTimeScheduleLocal;
import com.hlcmro.masters.HLCTentativeTimeScheduleLocalHome;
import com.hlcmro.renewal.HLCMetturDamEntityLocalHomeRenewal;
import com.hlcmro.renewal.HLCMetturDamEntityLocalRenewal;
import com.hlcmro.util.HLCAreaChairsVO;
import com.hlcmro.util.HLCCalendarVO;
import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import com.hlcmro.util.HLCEndorsedFormVO;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCEventRequestVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCPaymentDetailsVO;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import javax.naming.*;
import java.rmi.*;
import java.sql.*;
import javax.sql.DataSource;
import java.util.*;

/**
 * This is the bean class for the VaigaiSessionBean enterprise bean.
 * Created c
 * @author suresh
 */
public class HLCVaigaiSessionBean implements SessionBean, HLCVaigaiSessionRemoteBusiness {
    private SessionContext context;
    private InitialContext ic;
    private Connection con;
    private HLCMetturDamEntityLocalHome home;
    private HLCMetturDamEntityLocal remote;
    
    private HLCMetturDamEntityLocalHomeRenewal objMetturHome;
    private HLCMetturDamEntityLocalRenewal objMetturremote;
    
    private HLCTentativeTimeScheduleLocalHome objTTSHome;
    private HLCTentativeTimeScheduleLocal objTTSRemote;
    
    private HLCCrossCountryLocalHome objCrossHome;
    private HLCCrossCountryLocal objCrossRemote;
    
    private HLCHorseDressageLocalHome objDressHome;
    private HLCHorseDressageLocal objHDRemote;
    
    private HLCJudgeDetailLocalHome objJudgeHome;
    private HLCJudgeDetailLocal objJudgeRemote;
    
    private HLCEventTypeLocalHome objDivLevHome;
    private HLCEventTypeLocal objDivLevRemote;
    
    private HLCAccommodationDetailLocalHome objAccHome;
    private HLCAccommodationDetailLocal objAccRemote;
    
    private HLCCogginsDetailLocalHome objCoggHome;
    private HLCCogginsDetailLocal objCoggRemote;
    
    private HLCOtherDetailLocalHome objOthersHome;
    private HLCOtherDetailLocal objOtherRemote;
    
    private HLCRefundRuleDetailLocalHome objRefundRuleHome;
    private HLCRefundRuleDetailLocal objRefundRuleRemote;
    
    
    private String eventId;
    private String renewalId;
    private String cogginsId;
    private String otherDetId;
    
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    // </editor-fold>
    
/**
  * @Method Name    :ejbCreate.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :CreateException.
  */
    public void ejbCreate() throws CreateException {
        
        try{
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup("HLCMetturDamEntityLocalHome");
            home = (HLCMetturDamEntityLocalHome)obj;

            
            Object objRenewal = jndiContext.lookup("HLCMetturDamEntityLocalHomeRenewal");
            objMetturHome = (HLCMetturDamEntityLocalHomeRenewal)objRenewal;
            
            Object objTTS = jndiContext.lookup("HLCTentativeTimeScheduleLocalHome");
            objTTSHome = (HLCTentativeTimeScheduleLocalHome)objTTS;
            
            Object objCross = jndiContext.lookup("HLCCrossCountryLocalHome");
            objCrossHome = (HLCCrossCountryLocalHome)objCross;
            
            Object objDress = jndiContext.lookup("HLCHorseDressageLocalHome");
            objDressHome = (HLCHorseDressageLocalHome)objDress;
            
            Object objJud = jndiContext.lookup("HLCJudgeDetailLocalHome");
            objJudgeHome = (HLCJudgeDetailLocalHome)objJud;
            
            Object objDivLev = jndiContext.lookup("HLCEventTypeLocalHome");
            objDivLevHome = (HLCEventTypeLocalHome)objDivLev;
            
            Object objAcc = jndiContext.lookup("HLCAccommodationDetailLocalHome");
            objAccHome = (HLCAccommodationDetailLocalHome)objAcc;
            
            Object objCogg = jndiContext.lookup("HLCCogginsDetailLocalHome");
            objCoggHome = (HLCCogginsDetailLocalHome)objCogg;
            
            Object objOthers = jndiContext.lookup("HLCOtherDetailLocalHome");
            objOthersHome = (HLCOtherDetailLocalHome)objOthers;
            
            Object objRF = jndiContext.lookup("HLCRefundRuleDetailLocalHome");
            objRefundRuleHome  = (HLCRefundRuleDetailLocalHome)objRF;
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
/**
  * @Method Name    :createEvent.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :EventDetailsVO objEventDet, Vector accommodation, Vector crossCountry, Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails, OtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime.
  * @return         :String value.
  * @throws         :RemoteException.
  */
    public String createEvent(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException{
        try{
            //this.eventId =  String.valueOf(getNextId());
            //objEventDet.setEventId(eventId);
            this.eventId=objEventDet.getEventId();
            remote = home.create(objEventDet, accommodation, crossCountry,
            cogginsDetails, divisionLevels, hDressage, judgesDetails,
            othersDet, refundRuleDetails, tentativeTime);
        }
        catch(Exception exp){
             throw new EJBException("CreateEvent Details: " + exp.getMessage());
        }
        return eventId;
    }
    
 /*****************************************for updating the event details****************************************************/
    
    public boolean updateMainEventRegDetails(HLCEventDetailsVO objEventDet) throws RemoteException{
         boolean result = false;         
         Debug.print("VaigaiSessionBean.updateMainEventRegDetails():");
         try{
             result = new HLCEventsDAO().updateMainEventRegDetails(objEventDet);
         }catch(Exception e){
             e.printStackTrace();
         }
         return result;
     }
/**
  * @Method Name    :editEventOrganizer.
  * @Description    :This method will edit event Otganizer. If the the Event Organizer gets updated the a boolean value will return.
  * @param          :EventDetailsVO objEventDet, Vector accommodation, Vector crossCountry, Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails, OtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */    
    public boolean editEventOrganizer(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException, FinderException {
        String eventId = objEventDet.getEventId();
        HLCEventDetailsVO objEventDetail = objEventDet;
        
        boolean bol = false;       
        try {
            boolean bol1 = false;
            
            bol1 = editAccomodation(eventId,accommodation);
            Debug.print("In editEventOrganizer Succefully update editAccomodation : "+bol1);
            bol1 = editCrossCountry(eventId, crossCountry);
            Debug.print("In editEventOrganizer Succefully update editCrossCountry : "+bol1);
            bol1 = editCogginsDetail(cogginsDetails);
            Debug.print("In editEventOrganizer Succefully update editCogginsDetail : "+bol1);
            bol1 = editDivisionLevel(eventId, divisionLevels);
            Debug.print("In editEventOrganizer Succefully update editDivisionLevel : "+bol1);
            bol1 = editHorseDressage(eventId, hDressage);
            Debug.print("In editEventOrganizer Succefully update editHorseDressage : "+bol1);
            bol1 = editJudgeDetails(eventId, judgesDetails);
            Debug.print("In editEventOrganizer Succefully update editJudgeDetails : "+bol1);
            bol1 = editOtherDetails(othersDet);
            Debug.print("In editEventOrganizer Succefully update editOtherDetails : "+bol1);
            bol1 = editRefundRuleDetails(eventId, refundRuleDetails);
            Debug.print("In editEventOrganizer Succefully update editRefundRuleDetails : "+bol1);
            editTentativeTime(eventId, tentativeTime);
            Debug.print("In editEventOrganizer Succefully update editTentativeTime : ");
            editEvent(objEventDetail);
            Debug.print("In editEventOrganizer Succefully update editEvent");
            
            bol = true;            
        }catch(Exception e){
            e.printStackTrace();
            throw new EJBException("Error in  EditEventOrganizsr Details : "+e.getMessage());
            
        }
        return bol;
    }
    
/**
  * @Method Name    :changeEventStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId, String statusId, String comments.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */
      public boolean changeEventStatus(String eventId, String statusId, String comments) throws RemoteException{
        Debug.print("VaigaiSessionBean changeEventStatus:" + statusId);
            boolean result = false;
            if (eventId == null) {
               Debug.print("Event ID can't be empty");
            }

            if (eventExists(eventId) == false) {
               Debug.print("Event ID Not Exists" + eventId);
            }
            else{
                remote.setStatusId(statusId);
                remote.setComments(comments);
                result = true;
            }
            return result;
      }
   
/**
  * @Method Name    :getEventDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :EventDetailsVO value.
  * @throws         :RemoteException.
  */
      public HLCEventDetailsVO getEventDetails(String eventId) throws RemoteException {
            Debug.print("VaigaiSessionBean getEventDetails");
            HLCEventDetailsVO result;
            if (eventId == null) {
                throw new EJBException("Event ID can't be empty");
            }

            if (eventExists(eventId) == false) {
                throw new EJBException("Event ID Not Exists" + eventId);
            }
            result = remote.getEventDetails();
            return result;
      }
      
/**
  * @Method Name    :getAllEventDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */
      public ArrayList getAllEventDetails() throws RemoteException { 
         Debug.print("VaigaiSessionBean getAllEventDetails");
            HLCEventDetailsVO result;
            ArrayList resultList = new ArrayList();
            try{
                ArrayList alOrganizer = (ArrayList) home.findByAll();
                Iterator e = alOrganizer.iterator();
                Debug.print("VaigaiSessionBean getAllEventDetails alOrganizer:" + alOrganizer.size());
                while(e.hasNext()){
                    HLCMetturDamEntityLocal localRemote = (HLCMetturDamEntityLocal)e.next();
                    result  = (HLCEventDetailsVO)localRemote.getEventDetails();
                    resultList.add(result);
                    Debug.print("VaigaiSessionBean getAllEventDetails result:" + result);
                }
            }
            catch(Exception exp){
                Debug.print("Exception in getAllEventDetails" + exp);
            }
        return resultList;
      }
      
/**
  * @Method Name    :getAllEventDetailsByRequestStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String requestStatus.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */
    public ArrayList getAllEventDetailsByRequestStatus(String requestStatus) throws RemoteException { 
         Debug.print("VaigaiSessionBean getAllEventDetailsByRequestStatus");
         HLCEventDetailsVO result;
         ArrayList resultList = new ArrayList();
         if(requestStatus!=null && requestStatus.trim().length()!=0){
            try{
                ArrayList alOrganizer = (ArrayList) home.findByAllByStautsId(requestStatus);
                Iterator e = alOrganizer.iterator();
                Debug.print("VaigaiSessionBean getAllEventDetailsByRequestStatus alOrganizer:" + alOrganizer.size());
                while(e.hasNext()){
                    HLCMetturDamEntityLocal localRemote = (HLCMetturDamEntityLocal)e.next();
                    result  = (HLCEventDetailsVO)localRemote.getEventDetails();
                    resultList.add(result);
                    Debug.print("VaigaiSessionBean getAllEventDetailsByRequestStatus result:" + result);
                }
            }
            catch(Exception exp){
                Debug.print("Exception in getAllEventDetailsByRequestStatus" + exp);
            }
         }
        return resultList;
      }
      
/**
  * @Method Name    :getAllOrganizerEventDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String organizerId.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */
      public ArrayList getAllOrganizerEventDetails(String organizerId) throws RemoteException{
           Debug.print("VaigaiSessionBean getAllOrganizerEventDetails" + organizerId);
            HLCEventDetailsVO result;
            ArrayList resultList = new ArrayList();
            try{
                ArrayList alOrganizer = (ArrayList) home.findByOrganizerId(organizerId);
                Iterator e = alOrganizer.iterator();
                Debug.print("VaigaiSessionBean getAllOrganizerEventDetails alOrganizer:" + alOrganizer.size());
                while(e.hasNext()){
                    HLCMetturDamEntityLocal localRemote = (HLCMetturDamEntityLocal)e.next();
                    result  = (HLCEventDetailsVO)localRemote.getEventDetails();
                    resultList.add(result);
                    Debug.print("VaigaiSessionBean getAllOrganizerEventDetails result:" + result);
                }
            }
            catch(Exception exp){
                Debug.print("Exception in getAllOrganizerEventDetails" + exp);
            }
        return resultList;
      }
      
/**
  * @Method Name    :getDetailedEvents.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */
     public ArrayList getDetailedEvents(String eventId) throws RemoteException {
            Debug.print("VaigaiSessionBean getDetailedEvents");
            ArrayList eventDet = new ArrayList();
            HLCEventDetailsVO objEventDet;
            Vector accommodation = new Vector();
            Vector crossCountry = new Vector();
            Vector cogginsDetails = new Vector();
            Vector divisionLevels = new Vector();
            Vector judgesDetails = new Vector();
            Vector hDressage = new Vector();
            HLCOtherDetailsVO othersDet = new HLCOtherDetailsVO();
            Vector refundRuleDetails = new Vector();
            Vector tentativeTime= new Vector();
            if (eventId == null) {
                throw new EJBException("Event ID can't be empty");
            }

            if (eventExists(eventId) == false) {
                throw new EJBException("Event ID Not Exists" + eventId);
            }
            else{
                try{
                    Debug.print("VaigaiSessionBean getDetailedEvents Started..");
                    objEventDet = remote.getEventDetails();
                    eventDet.add(objEventDet);
                    Debug.print("VaigaiSessionBean getDetailedEvents objEventDet");
                    /*================ Division Methods =====================*/
                    divisionLevels = remote.getDivisionLevels();
                    Debug.print("VaigaiSessionBean getDetailedEvents divisionLevels:" + divisionLevels);
                    eventDet.add(divisionLevels);
                   /*================ Judges Methods =====================*/
                    judgesDetails = remote.getJudgesDetails();
                    eventDet.add(judgesDetails);            
                    /*================ Refund Rule Methods =====================*/
                    refundRuleDetails = remote.getRefundRuleDetails();
                    eventDet.add(refundRuleDetails);            
                    /*================ Coggins Methods =====================*/
                    cogginsDetails = remote.getCogginsDetails();
                    eventDet.add(cogginsDetails);            
                    /*================ Tentative Time Methods =====================*/
                    tentativeTime = remote.getTentativeTime();
                    eventDet.add(tentativeTime);            
                    /*================ Horse Dressing Methods =====================*/
                    hDressage = remote.getHorseDressingDetails();
                    eventDet.add(hDressage);            
                    /*================ Cross Country Details Methods =====================*/
                    crossCountry = remote.getCrossCountryDetails();
                    eventDet.add(crossCountry);            
                    /*================ Cross Country Details Methods =====================*/
                    othersDet = remote.getOtherDetails();
                    eventDet.add(othersDet);            
                    /*================ Accommdation Details Methods =====================*/
                    accommodation = remote.getAccommodationDetails();
                    eventDet.add(accommodation);
                }
                catch(Exception e){
                    Debug.print("Exception in getDetailedEvents" + e);
                }
            }
            return eventDet;
      }   
/**
  * @Method Name    :editCogginsDetail.
  * @Description    :This method will update Other Details. If succeffuly update then return a boolean value.
  * @param          :Vector coggingsDet.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */
   public boolean editOtherDetails(HLCOtherDetailsVO otherDet) throws RemoteException, FinderException{
       String otherDetId = null;
       if (otherDet != null && otherDet.getEventId() == null) {
            throw new EJBException("Event ID can't be empty");
        }
       try {
        Collection result = objOthersHome.findByEventId(otherDet.getEventId());
        if (result != null && result.size() != 0){
            Iterator e = result.iterator();
            while(e.hasNext()){
                objOtherRemote = (HLCOtherDetailLocal)e.next();
                otherDetId = objOtherRemote.getOtherId();
                Debug.print("objOtherRemote : "+objOtherRemote);
                Debug.print(" otherDetId  : "+otherDetId);
            }
        }
    }catch(Exception e){
        Debug.print(" Exception in Removing Accomodation Details...");
    }
        if (eventOtherDetExists(otherDetId) == false) {
            throw new EJBException("Event ID Not Exists" + eventId);
        }            
       objOtherRemote.setEventId(otherDet.getEventId());
       objOtherRemote.setCourseCloseDate(otherDet.getCourseCloseDate());
       objOtherRemote.setEntryLimit(otherDet.getEntryLimit());
       objOtherRemote.setRidersHorseLevelLimit(otherDet.getRidersHorseLevelLimit());
       objOtherRemote.setRidersHorseEntireLimit(otherDet.getRidersHorseEntireLimit());
       objOtherRemote.setDivisionEntryBirthDate(otherDet.getDivisionEntryBirthDate());
       objOtherRemote.setLeaseDogs(otherDet.isLeaseDogs());
       objOtherRemote.setTeamCompetition(otherDet.getTeamCompetition());
       objOtherRemote.setPerTimePrice(otherDet.getPerTimePrice());
       objOtherRemote.setPartyName(otherDet.getPartyName());
       objOtherRemote.setAddInfo(otherDet.getAddInfo());
      

       return true;
   }
       
/**
  * @Method Name    :editCogginsDetail.
  * @Description    :This method will update Coggins Details. If succeffuly update then return a boolean value.
  * @param          :Vector coggingsDet.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */
       public boolean editCogginsDetail(Vector coggingsDet) throws RemoteException, FinderException{
           String eventId = (String)coggingsDet.elementAt(0);
           String allState = (String)coggingsDet.elementAt(1);
           String inState = (String)coggingsDet.elementAt(2);
           String outOfState = (String)coggingsDet.elementAt(3);
           String noState = (String)coggingsDet.elementAt(4);
           String others = (String)coggingsDet.elementAt(5); 
           String cogginsId = null;
           
           Debug.print(" Vector Contents : eventId ->"+eventId+" allState ->"+allState);
           Debug.print(" inState->"+inState+"  outOfState->"+outOfState+" noState->"+noState+" others->"+others);
           
            if (eventId == null) {
                throw new EJBException("Event ID can't be empty");
            }
           try {
            Collection result = objCoggHome.findByEventId(eventId);
            if (result != null && result.size() != 0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                    objCoggRemote = (HLCCogginsDetailLocal)e.next();
                    cogginsId = objCoggRemote.getCogginsId();
                    Debug.print("objCoggRemote : "+objCoggRemote);
                    Debug.print(" cogginsId  : "+cogginsId);
                }
            }
        }catch(Exception e){
            Debug.print(" Exception in Removing Accomodation Details...");
        }
            if (eventCogginsExists(cogginsId) == false) {
                throw new EJBException("Event ID Not Exists" + eventId);
            }            
           objCoggRemote.setEventId(eventId);
           objCoggRemote.setAllState(allState);
           objCoggRemote.setInState(inState);
           objCoggRemote.setOutOfState(outOfState);
           objCoggRemote.setNoState(noState);
           objCoggRemote.setOther(others);  
           
           return true;
       }
      
/**
  * @Method Name    :editEvent.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :EventDetailsVO objEventDet.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */
       public void editEvent(HLCEventDetailsVO objEventDet) throws RemoteException, FinderException{
            if (objEventDet != null && objEventDet.getEventId() == null) {
                throw new EJBException("Event ID can't be empty");
            }
            if (eventExists(objEventDet.getEventId()) == false) {
                throw new EJBException("Event ID Not Exists" + eventId);
            }
            Debug.print("INSIDE FIRST OR REMOTE IN EDITEVENT............... "+objEventDet);
            remote.setEventSecretaryId(objEventDet.getEventSecretaryId());
            remote.setEntryFee(objEventDet.getEntryFee());
            remote.setOtherEntryFee(objEventDet.getOtherEntryFee());
            remote.setDoubleEntryFee(objEventDet.getDoubleEntryFee());
            remote.setOfficeFee(objEventDet.getOfficeFee());
            remote.setMembershipApplicable(objEventDet.getMembershipApplicable());
            remote.setCheckPayableTo(objEventDet.getCheckPayableTo());
            remote.setPinniesCharge(objEventDet.getPinniesCharge());
            remote.setPinniesPosition(objEventDet.getPinniesPosition());
            remote.setAwardTrophy(objEventDet.getAwardTrophy());
            remote.setAwardPrize(objEventDet.getAwardPrize());
            remote.setAwardOthers(objEventDet.getAwardOthers());
            remote.setDateAvailable(objEventDet.getDateAvailable());
            remote.setAvailableFrom(objEventDet.getAvailableFrom());
            remote.setAvailableFromOther(objEventDet.getAvailableFromOther());
            remote.setAvailablePosition(objEventDet.getAvailablePosition());
            remote.setStablingLimited(objEventDet.getStablingLimited());
            remote.setStallPernightPrice(objEventDet.getStallPernightPrice());
            remote.setPerStallPrice(objEventDet.getPerStallPrice());
            remote.setPerStallFromTime(objEventDet.getPerStallFromTime());
            remote.setPerStallFromDate(objEventDet.getPerStallFromDate());
            remote.setPerStallToTime(objEventDet.getPerStallToTime());
            remote.setPerStallToDate(objEventDet.getPerStallToDate());
            remote.setNoOfStalls(objEventDet.getNoOfStalls());
            remote.setNoOfTempStalls(objEventDet.getNoOfTempStalls());
            remote.setNoOfTempPermanentStalls(objEventDet.getNoOfTempPermanentStalls());
            remote.setMilesFromEvent(objEventDet.getMilesFromEvent());
            remote.setVeterinarianName(objEventDet.getVeterinarianName());
            remote.setVeterinarianPhone(objEventDet.getVeterinarianPhone());
            remote.setVeterinarianPlace(objEventDet.getVeterinarianPlace());
            remote.setAccomodationCamping(objEventDet.getAccomodationCamping());
            remote.setDirections(objEventDet.getDirections());
            remote.setComments(objEventDet.getComments());
            remote.setFootingDesc(objEventDet.getFootingDesc());
            Debug.print("INSIDE LAST OR REMOTE IN EDITEVENT............... ");
       }
      
/**
  * @Method Name    :editHorseDressage.
  * @Description    :This method will edit horse Dressage. If the data gets update the it will return boolean value.
  * @param          :String eventId.
  * @return         :Vector horseDressage.
  * @throws         :FinderException, RemoteException.
  */          
       
       public boolean editHorseDressage(String eventId, Vector horseDressage) throws RemoteException, FinderException {
           boolean bol = false;
           if (horseDressage != null && eventId == null){
               throw new EJBException("Event Id can't be empty");
           }
           try {
               Collection result = objDressHome.findByEventId1(eventId);
               if (result != null && result.size() != 0 ) {
                   Iterator e = result.iterator();
                   while(e.hasNext()){
                       objHDRemote = (HLCHorseDressageLocal)e.next();
                       objHDRemote.remove();
                       Debug.print("Horse Dressage deleted successfully");
                   }
               }
           }catch(Exception e){
               Debug.print("Exception in removing Horse Dresage : "+e);
           }
           try{
               Enumeration hdEnum = horseDressage.elements();
               Debug.print("Horse Dressage Insertion started");
               while(hdEnum.hasMoreElements()){
                   ArrayList hd = (ArrayList)hdEnum.nextElement();
                   Iterator it = hd.iterator();
                   while(it.hasNext()){
                       String dressageMapId = (String)it.next();
                       String arenaSizeId = (String)it.next();
                       objHDRemote = objDressHome.create(eventId,dressageMapId,arenaSizeId);
                       Debug.print("Horse Dressage Inserted Succeffully");
                       bol = true;
                   }
               }
           }catch(Exception e){
               Debug.print("Error in editHorseDressage : "+e);
           }
        return bol;
       }
/**
  * @Method Name    :editAccomodation.
  * @Description    :This method will edit Accomodation. If the data gets update the it will return boolean value.
  * @param          :String eventId.
  * @return         :Vector accomodation.
  * @throws         :FinderException, RemoteException.
  */    
    public boolean editAccomodation(String eventId, Vector accomodation) throws RemoteException, FinderException {
        boolean bol = false;
        if (accomodation != null && eventId == null){
            throw new EJBException("Event Id can't be empty");
        }
        try {
            Collection result = objAccHome.findByEventId(eventId);
            if (result != null && result.size() != 0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                    objAccRemote = (HLCAccommodationDetailLocal)e.next();
                    Debug.print("objAccRemote : "+objAccRemote);
                    objDivLevRemote.remove();
                    Debug.print(" Accomodation Details Succefully Deleted...");
                }
            }
        }catch(Exception e){
            Debug.print(" Exception in Removing Accomodation Details...");
        }
        try{
            Enumeration dlEnum = accomodation.elements();
           Debug.print("Accomodation details insert started");
           while(dlEnum.hasMoreElements()){
               ArrayList ad = (ArrayList)dlEnum.nextElement();
               Iterator it = ad.iterator();
               while(it.hasNext()){
                   String hotelName = (String)it.next();
                   String hotelPhone = (String)it.next();
                   String milesFromEvent = (String)it.next();
                   objAccRemote = objAccHome.create(eventId,hotelName,hotelPhone, milesFromEvent);
                   Debug.print("Accomodation Details Insert Succeffully");
                   bol = true;
               }
           }
        }catch(Exception e){
            Debug.print(" Error in editAccomodation...");
        }
        return bol;
    }
    
/**
  * @Method Name    :editDivisionLevel.
  * @Description    :This method will edit Division Level. If the data gets update the it will return boolean value.
  * @param          :String eventId.
  * @return         :Vector divLevel.
  * @throws         :FinderException, RemoteException.
  */    
    public boolean editDivisionLevel(String eventId, Vector divLevel) throws RemoteException, FinderException {
        boolean bol = false;
        if (divLevel != null && eventId == null){
            throw new EJBException("Event Id can't be empty");
        }
        try {
            Collection result = objDivLevHome.findByEventId1(eventId);
            if (result != null && result.size() != 0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                    objDivLevRemote = (HLCEventTypeLocal)e.next();
                    Debug.print("objDivLevRemote : "+objDivLevRemote);
                    objDivLevRemote.remove();
                    Debug.print(" Division Level Succefully Deleted...");
                }
            }
        }catch(Exception e){
            Debug.print(" Exception in Removing Division Level...");
        }
        try{
            Enumeration dlEnum = divLevel.elements();
           Debug.print("Division Level insert started");
           while(dlEnum.hasMoreElements()){
               ArrayList dl = (ArrayList)dlEnum.nextElement();
               Iterator it = dl.iterator();
               while(it.hasNext()){
                   String mapTypeId = (String)it.next();
                   objDivLevRemote = objDivLevHome.create(eventId,mapTypeId);
                   Debug.print("Division Level Insert Succeffully");
                   bol = true;
               }
           }
        }catch(Exception e){
            Debug.print(" Error in editDivisionLevel...");
        }
        return bol;
    }
/**
  * @Method Name    :editRefundRuleDetails.
  * @Description    :This method will edit Refund Rule Details. If the data gets update the it will return boolean value.
  * @param          :String eventId.
  * @return         :Vector refundDetail.
  * @throws         :FinderException, RemoteException.
  */    
    public boolean editRefundRuleDetails(String eventId, Vector refundDetail) throws RemoteException, FinderException {
        boolean bol = false;
        if (refundDetail != null && eventId == null){
            throw new EJBException("Event Id can't be empty");
        }
        try {
            Collection result = objRefundRuleHome.findByEventId1(eventId);
            if (result != null && result.size() != 0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                    objRefundRuleRemote = (HLCRefundRuleDetailLocal)e.next();
                    Debug.print("objRefundRuleRemote : "+objRefundRuleRemote);
                    objRefundRuleRemote.remove();
                    Debug.print(" Refund Rule Details Succefully Deleted...");
                }//gdggdfg    String eventId,String refundMapId,String amount
            }
            
        }catch(Exception e){
            Debug.print(" Exception in Removing Refund Rule Details...");
        }
        try{
            Enumeration rrdEnum = refundDetail.elements();
           Debug.print("Refund Rule Details insert started");
           while(rrdEnum.hasMoreElements()){
               ArrayList rRD = (ArrayList)rrdEnum.nextElement();
               Iterator it = rRD.iterator();
               while(it.hasNext()){
                   String refundMapId   = (String)it.next();
                   String amount   = (String)it.next();
                   objRefundRuleRemote = objRefundRuleHome.create(eventId,refundMapId,amount);
                   Debug.print("Refund Rule Details Insert Succeffully");
                   bol = true;
               }
           }
        }catch(Exception e){
            Debug.print(" Error in editJudgeDetails...");
        }
        return bol;
    }    
    
/**
  * @Method Name    :editJudgeDetails.
  * @Description    :This method will edit Judge Details. If the data gets update the it will return boolean value.
  * @param          :String eventId.
  * @return         :Vector judgeDetail.
  * @throws         :FinderException, RemoteException.
  */    
    public boolean editJudgeDetails(String eventId, Vector judgeDetail) throws RemoteException, FinderException {
        boolean bol = false;
        if (judgeDetail != null && eventId == null){
            throw new EJBException("Event Id can't be empty");
        }
        try {
            Collection result = objJudgeHome.findByEventId(eventId);
            if (result != null && result.size() != 0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                    objJudgeRemote = (HLCJudgeDetailLocal)e.next();
                    Debug.print("objJudgeRemote : "+objJudgeRemote);
                    objJudgeRemote.remove();
                    Debug.print(" Judge Details Succefully Deleted...");
                }
            }
            
        }catch(Exception e){
            Debug.print(" Exception in Removing Judge Details...");
        }
        try{
            Enumeration jdEnum = judgeDetail.elements();
           Debug.print("Judge Details insert started");
           while(jdEnum.hasMoreElements()){
               ArrayList jd = (ArrayList)jdEnum.nextElement();
               Iterator it = jd.iterator();
               while(it.hasNext()){
                   String judgeTypeId = (String)it.next();
                   String judgeName   = (String)it.next();
                   objJudgeRemote = objJudgeHome.create(eventId,judgeTypeId,judgeName);
                   Debug.print("Judge Details Insert Succeffully");
                   bol = true;
               }
           }
        }catch(Exception e){
            Debug.print(" Error in editJudgeDetails...");
        }
        return bol;
    }
       
/**
  * @Method Name    :editCrossCountry.
  * @Description    :This method will edit Cross Country Details. If the data gets update the it will return boolean value.
  * @param          :String eventId.
  * @return         :Vector crossCountry.
  * @throws         :FinderException, RemoteException.
  */              
       public boolean editCrossCountry(String eventId, Vector crossCountry) throws RemoteException, FinderException {
           boolean bol = false;
           if (crossCountry != null && eventId == null){
               throw new EJBException("Event Id can't be empty");
           }
           try {
               Collection result = objCrossHome.findByEventId(eventId);
               if (result != null && result.size() != 0){
                   Iterator e = result.iterator();
                   while(e.hasNext()){
                       objCrossRemote = (HLCCrossCountryLocal)e.next();
                       Debug.print("objCrossRemote : "+objCrossRemote);
                       objCrossRemote.remove();
                       Debug.print("Cross Country deleted successfully");
                   }
               }
           }catch(Exception e){
               Debug.print("Exception in Removing Cross Country : "+e);
           }
           try{
               Enumeration ccEnum = crossCountry.elements();
               Debug.print("Cross Country insert started");
               while(ccEnum.hasMoreElements()){
                   ArrayList cc = (ArrayList)ccEnum.nextElement();
                   Iterator it = cc.iterator();
                   while(it.hasNext()){
                       String division = (String)it.next();
                       String length   = (String)it.next();
                       String speed    = (String)it.next();
                       String courseDescription = (String)it.next();
                       String addInformation = (String)it.next();
                       objCrossRemote = objCrossHome.create(eventId,division,length,speed,courseDescription,addInformation);
                       Debug.print("Course Country Insert Succeffully");
                       bol = true;
                   }
               }
           }catch(Exception e){
               Debug.print("Error in editCrossCountry : "+e);
           }
           return bol;
       }
       
/**
  * @Method Name    :editTentativeTime.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId, Vector tentativeTime.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */
       public void editTentativeTime(String eventId, Vector tentativeTime) throws RemoteException, FinderException{
            if (tentativeTime != null && eventId == null) {
                throw new EJBException("Event ID can't be empty");
            }
            try{
                Collection result = objTTSHome.findByEventId(eventId);
                if(result!=null && result.size()!=0){
                    Iterator e = result.iterator();
                    while(e.hasNext()){
                        objTTSRemote = (HLCTentativeTimeScheduleLocal)e.next();
                        objTTSRemote.remove();
                         Debug.print("Tentaive time deleted sucessfully");
                    }
                }
            }
            catch(Exception exp){
                 Debug.print("Exception in removing Tentative Time:" + exp);
            }
            try{
                Enumeration ttsEnum = tentativeTime.elements();
                Debug.print("Tentaive time insert started:");
                while(ttsEnum.hasMoreElements()){
                    ArrayList tts = (ArrayList)ttsEnum.nextElement();
                    Iterator it = tts.iterator();
                    while(it.hasNext()){
                        java.util.Date day = (java.util.Date)it.next();
                        String phase =  (String)it.next();
                        String time =  (String)it.next();
                        objTTSRemote = objTTSHome.create(eventId, day, phase, time);
                         Debug.print("Tentaive time inserted sucessfully");
                    }
                   
                }
            }
            catch(Exception e){
                Debug.print("Exception in editTentaiveTime:" + e);
            }
       }
     
/**
  * @Method Name    :deleteEvent.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */   
    public void deleteEvent(String eventId) throws RemoteException,FinderException{
        Debug.print("VaigaiSessionBean removeEvent");

        if (eventId == null) {
            throw new EJBException("Event ID can't be empty");
        }
        if (eventExists(eventId) == false) {
            throw new EJBException("Event ID Not Exists" + eventId);
        }
        try {
            remote.remove();
        } catch (Exception ex) {
              throw new EJBException("removeEvent: " + ex.getMessage());
        }
    }

/**
  * @Method Name    :eventExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :boolean value.
  * @throws         :Null.
  */   
    private boolean eventExists(String eventId) {
        Debug.print("VaigaiSessionBean eventExists");
        if (eventId != this.eventId) {
            try {
                remote = home.findByPrimaryKey(eventId);
                this.eventId = eventId;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }   
/**
  * @Method Name    :eventExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :boolean value.
  * @throws         :Null.
  */   
    private boolean eventCogginsExists(String cogginsId) {
        Debug.print("VaigaiSessionBean eventCogginsExists");
        if (eventId != this.eventId) {
            try {
                objCoggRemote = objCoggHome.findByPrimaryKey(cogginsId);
                this.cogginsId = cogginsId;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }    
    
/**
  * @Method Name    :eventExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :boolean value.
  * @throws         :Null.
  */   
    private boolean eventOtherDetExists(String otherDetId) {
        Debug.print("VaigaiSessionBean eventOtherDetExists");
        if (otherDetId != this.otherDetId) {
            try {
                objOtherRemote = objOthersHome.findByPrimaryKey(otherDetId);
                this.otherDetId = otherDetId;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }    
   
  //================================== Renewal Endorse Form ==================
        
/**
  * @Method Name    :addRenewal.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :RenewalOrganizerDetails objRenewalDet,PaymentDetails objPayDet.
  * @return         :void value.
  * @throws         :RemoteException.
  */ 
    public void addRenewal(HLCRenewalOrganizerDetails objRenewalDet,HLCPaymentDetails objPayDet) throws RemoteException{
        try{
             objMetturremote = objMetturHome.create(objRenewalDet,objPayDet);
        }
        catch(Exception exp){
             throw new EJBException("createRenewal: " + exp.getMessage());
        }
    }  
    
/**
  * @Method Name    :editRenewal.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :RenewalOrganizerDetails objRenewalDet.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */     
    public boolean editRenewal(HLCRenewalOrganizerDetails objRenewalDet) throws RemoteException{
         Debug.print("VaigaiSessionBean getRenewalDetails" + renewalId);
            HLCRenewalOrganizerDetails result;
            if (objRenewalDet.getRenewalId() == null) {
                throw new EJBException("null renewalId");
            }

            if (eventRenewalExists(objRenewalDet.getRenewalId()) == false) {
                throw new EJBException(objRenewalDet.getRenewalId());
            }
           // result = objMetturremote.getRenewalDetails();
            //objMetturremote.setobjRenewalDet.getEventId());
          /*  Debug.print(objRenewalDet.getOrganizerId());
            Debug.print( objRenewalDet.getCompetitionName());
            Debug.print(objRenewalDet.getCompetitionLocation());
            Debug.print(objRenewalDet.getCompetitionCity());
            Debug.print(objRenewalDet.getCompetitionState());
            Debug.print(objRenewalDet.getCompetitionCountry());
            Debug.print(objRenewalDet.getCompetitionZip());
            Debug.print("" + objRenewalDet.getCompetitionDate());
            Debug.print(objRenewalDet.getChampionshipArea());        
            Debug.print(objRenewalDet.getComManagementName());
            Debug.print(objRenewalDet.getComManagementAddress());
            Debug.print(objRenewalDet.getComManagementCity());
            Debug.print(objRenewalDet.getComManagementState());
            Debug.print(objRenewalDet.getComManagementCountry());
            Debug.print(objRenewalDet.getComManagementZip());
            Debug.print(objRenewalDet.getComManagementPhone());
            Debug.print(objRenewalDet.getComManagementFax());
            Debug.print(objRenewalDet.getManagerUsefMemberId());
            Debug.print(objRenewalDet.getManagerUseaMemberId());
            Debug.print(objRenewalDet.getManagerName());
            Debug.print(objRenewalDet.getSecretaryUsefMemberId());
            Debug.print(objRenewalDet.getSecretaryName());
            */
            return true;
      }
   
/**
  * @Method Name    :getRenewalDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String renewalId.
  * @return         :RenewalOrganizerDetails value.
  * @throws         :RemoteException.
  */ 
    public HLCRenewalOrganizerDetails getRenewalDetails(String renewalId) throws RemoteException {
            Debug.print("VaigaiSessionBean getRenewalDetails" + renewalId);
            HLCRenewalOrganizerDetails result;
            if (renewalId == null) {
                throw new EJBException("null renewalId");
            }

            if (eventRenewalExists(renewalId) == false) {
                throw new EJBException(renewalId);
            }
            result = objMetturremote.getRenewalDetails();
            return result;
      }
      
/**
  * @Method Name    :getRenewalDetailByEventId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :ArrayList value.
  * @throws         :RemoteException, FinderException.
  */ 
    public ArrayList getRenewalDetailByEventId(String eventId) throws RemoteException,FinderException {
            Debug.print("VaigaiSessionBean getRenewalDetailByEventId");
            HLCRenewalOrganizerDetails result;
            ArrayList resultList = new ArrayList();
            if (eventId == null) {
                throw new EJBException("null eventId");
            }
            else{
                try{
                    ArrayList alOrganizer = (ArrayList) objMetturHome.findByEventId(eventId);
                    Iterator e = alOrganizer.iterator();
                    while(e.hasNext()){
                        HLCMetturDamEntityLocalRenewal objMetlocalRemote = (HLCMetturDamEntityLocalRenewal)e.next();
                        result  = (HLCRenewalOrganizerDetails)objMetlocalRemote.getRenewalDetails();
                        resultList.add(result);
                    }
                }
                catch(Exception exp){
                    Debug.print("Exception in getRenewalDetailByEventId" + exp);
                }
            }
            return resultList;
      }
      
/**
  * @Method Name    :getRenewalDetailByOraganizer.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String organizerId.
  * @return         :ArrayList value.
  * @throws         :RemoteException, FinderException.
  */   
    public ArrayList getRenewalDetailByOraganizer(String organizerId) throws RemoteException,FinderException {
            Debug.print("VaigaiSessionBean getRenewalDetailByOraganizer");
            HLCRenewalOrganizerDetails result;
            ArrayList resultList = new ArrayList();
            if (organizerId == null) {
                throw new EJBException("null organizerId");
            }
            else{
                try{
                    ArrayList alOrganizer = (ArrayList) objMetturHome.findByPaymentOrganizerId(organizerId);
                    Iterator e = alOrganizer.iterator();
                    while(e.hasNext()){
                        HLCMetturDamEntityLocalRenewal objMetlocalRemote = (HLCMetturDamEntityLocalRenewal)e.next();
                        result  = (HLCRenewalOrganizerDetails)objMetlocalRemote.getRenewalDetails();
                        resultList.add(result);
                    }
                }
                catch(Exception exp){
                    Debug.print("Exception in getRenewalDetailByOraganizer" + exp);
                }
            }
            return resultList;
      }
  
/**
  * @Method Name    :getRenewalDetailByStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String status.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */ 
    public ArrayList getRenewalDetailByStatus(String status) throws RemoteException {
            Debug.print("VaigaiSessionBean getRenewalDetailByStatus");
            HLCRenewalOrganizerDetails result;
            ArrayList resultList = new ArrayList();
            if (status == null) {
                throw new EJBException("null status");
            }
            else{
                try{
                    ArrayList alOrganizer = (ArrayList) objMetturHome.findRenewalByStatus(status);
                    Iterator e = alOrganizer.iterator();
                    while(e.hasNext()){
                        HLCMetturDamEntityLocalRenewal objMetlocalRemote = (HLCMetturDamEntityLocalRenewal)e.next();
                        result  = (HLCRenewalOrganizerDetails)objMetlocalRemote.getRenewalDetails();
                        resultList.add(result);
                    }
                }
                catch(Exception exp){
                    Debug.print("Exception in getRenewalDetailByStatus" + exp);
                }
            }
            return resultList;
      }
        
/**
  * @Method Name    :searchByCompitionName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String cmpName.
  * @return         :ArrayList value.
  * @throws         :RemoteException, FinderException.
  */ 
    public ArrayList searchByCompitionName(String cmpName) throws RemoteException,FinderException {
            Debug.print("VaigaiSessionBean searchByCompitionName");
            HLCRenewalOrganizerDetails result;
            ArrayList resultList = new ArrayList();
            if (cmpName == null) {
                throw new EJBException("searchByCompitionName is Null");
            }
            else{
                try{
                    ArrayList alOrganizer = (ArrayList) objMetturHome.findByCompititionName(cmpName);
                    Iterator e = alOrganizer.iterator();
                    while(e.hasNext()){
                        HLCMetturDamEntityLocalRenewal objMetlocalRemote = (HLCMetturDamEntityLocalRenewal)e.next();
                        result  = (HLCRenewalOrganizerDetails)objMetlocalRemote.getRenewalDetails();
                        resultList.add(result);
                    }
                }
                catch(Exception exp){
                    Debug.print("Exception in searchByCompitionName" + exp);
                }
            }
            return resultList;
      }
 
/**
  * @Method Name    :eventRenewalExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String renewalId.
  * @return         :boolean value.
  * @throws         :Null.
  */ 
    private boolean eventRenewalExists(String renewalId) {
        Debug.print("VaigaiSessionBean eventExists");

        if (renewalId != this.renewalId) {
            try {
                objMetturremote = objMetturHome.findByPrimaryKey(renewalId);
                this.renewalId = renewalId;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
    
    //==============================Mapping  Area Chair and State ==========================================================
 /**
  * @Method Name    :getAllAreaMasters.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */ 
     public ArrayList getAllAreaMasters() throws RemoteException { 
        Debug.print("VaigaiSessionBean getAllAreaMasters");
        HLCEventsDAO dao = new HLCEventsDAO();
        ArrayList results = dao.selectAllAreaMaster();
        return results;    
     }
     
//==============================Mapping  Area Chair and State ==========================================================
 /**
  * @Method Name    :getAllAreaMastersOnID.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */           
     
 public ArrayList getAreaStateMasterOnID(String areaID) throws RemoteException{
       Debug.print("VaigaiSessionBean getAreaStateMasterOnID");
       Debug.print("Area ID "+areaID);
        HLCEventsDAO dao = new HLCEventsDAO();
        ArrayList results = dao.selectAreaStateMasteronID(areaID);
        return results;
 }     

/**
  * @Method Name    :getAllAreaChairs.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */ 
      public ArrayList getAllAreaChairs() throws RemoteException { 
        Debug.print("VaigaiSessionBean getAllAreaChairs");
        HLCEventsDAO dao = new HLCEventsDAO();
        ArrayList results = dao.selectAllAreaChairs();
        return results;    
     }
     
     
/**
  * @Method Name    :getAllStates.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */
      public ArrayList getAllStates() throws RemoteException { 
        Debug.print("VaigaiSessionBean getAllStates");
        HLCEventsDAO dao = new HLCEventsDAO();
        ArrayList results = dao.selectAllStateMaster();
        return results;    
     }
     
/**
  * @Method Name    :generateMappingAreaChairArea.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String areaId, String  areaChairId.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */
     public boolean generateMappingAreaChairArea (String areaId, String  areaChairId) throws RemoteException {
        Debug.print("VaigaiSessionBean generateMappingAreaChairArea()" + areaChairId);
        HLCEventsDAO dao = new HLCEventsDAO();
        boolean result =false;
        if(areaChairId!=null && areaChairId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0){
            dao.insertMapAreaAndAreaChair(areaId, areaChairId);
            result = true;
        }
        return result;
     }
    
/**
  * @Method Name    :getStateByAreaId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String areaId.
  * @return         :String[] value.
  * @throws         :RemoteException.
  */
     public String[] getStateByAreaId(String areaId) throws RemoteException { 
        Debug.print("VaigaiSessionBean getStateByAreaId");
        HLCEventsDAO dao = new HLCEventsDAO();
        String [] results = dao.selectStateByAreaId(areaId);
        return results;    
     }
     
/**
  * @Method Name    :editMappingAreaChairArea.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mapAreaId, String areaId, String  areaChairId.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */
      public boolean editMappingAreaChairArea (String mapAreaId, String areaId, String  areaChairId) throws RemoteException {
        Debug.print("VaigaiSessionBean editMappingAreaChairArea()" + mapAreaId);
        HLCEventsDAO dao = new HLCEventsDAO();
        boolean result =false;
        if(mapAreaId!=null && mapAreaId.trim().length()!=0 && areaChairId!=null && areaChairId.trim().length()!=0 && areaId!=null && areaId.trim().length()!=0){
            dao.updateMapAreaAndAreaChair(mapAreaId, areaId, areaChairId);
            result = true;
        }
        return result;
     }
     
/**
  * @Method Name    :stateCount.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String areaId.
  * @return         :int value.
  * @throws         :RemoteException.
  */
      public int stateCount (String areaId) throws RemoteException{
        Debug.print("VaigaiSessionBean stateCount");
        int stateCount = 0;
        HLCEventsDAO dao = new HLCEventsDAO();
        stateCount  = dao.stateCount(areaId);
        return stateCount;  
      }
      
/**
  * @Method Name    :generateMappingAreaStateZip.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String areaId, String  stateId, String zipCodeFrom, String zipCodeTo.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */
     public boolean generateMappingAreaStateZip (String areaId, String  stateId, String zipCodeFrom, String zipCodeTo) throws RemoteException {
        Debug.print("VaigaiSessionBean generateMappingAreaStateZip()" + areaId);
        HLCEventsDAO dao = new HLCEventsDAO();
        boolean result =false;
        boolean flagZipCheck = false;
        if(areaId!=null && areaId.trim().length()!=0 &&
             stateId!=null && stateId.trim().length()!=0){
            //flagZipCheck = dao.isZipCodeRangeExists(zipCodeFrom, zipCodeTo);
            flagZipCheck = dao.isStateAreaExists(areaId, stateId);
            Debug.print("VaigaiSessionBean generateMappingAreaStateZip() - Zip Range Checking: " + flagZipCheck);
            if(flagZipCheck){
                boolean flag = dao.deleteRow("area_id", areaId, "state_id" , stateId, DBHelper.USEA_MRO_MAP_STATE_ZIP);
                Debug.print("VaigaiSessionBean generateMappingAreaStateZip(): Deleted Result:" + flag);
            }
        }
        
        if(areaId!=null && areaId.trim().length()!=0 && stateId!=null && stateId.trim().length()!=0 &&
                zipCodeFrom!=null && zipCodeFrom.trim().length()!=0  && zipCodeTo!=null && zipCodeTo.trim().length()!=0){
                dao.insertMapStateZip(areaId, stateId, zipCodeFrom, zipCodeTo);
                Debug.print("VaigaiSessionBean generateMappingAreaStateZip() successfully Inserted.");
                result = true;
        }
        if(flagZipCheck){
            result = false;
        }
        
        Debug.print("VaigaiSessionBean generateMappingAreaStateZip() Result Status:" + result);
        return result;
     }
     
/**
  * @Method Name    :getAllAreaAndAreaChairs.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :ArrayList value.
  * @throws         :RemoteException.
  */
      public ArrayList getAllAreaAndAreaChairs() throws RemoteException { 
        Debug.print("VaigaiSessionBean getAllAreaAndAreaChairs");
        HLCEventsDAO dao = new HLCEventsDAO();
        ArrayList results = dao.selectAllMappingDetailsForAreaAndAreaChair();
        return results;    
     }
     
/**
  * @Method Name    :getAreaAndAreaChairsByAreaMapId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mapAreaId.
  * @return         :AreaChairsVO value.
  * @throws         :RemoteException.
  */
      public HLCAreaChairsVO getAreaAndAreaChairsByAreaMapId(String mapAreaId) throws RemoteException{
        Debug.print("VaigaiSessionBean getAreaAndAreaChairsByAreaMapId");
        HLCEventsDAO dao = new HLCEventsDAO();
        HLCAreaChairsVO objACVO = new  HLCAreaChairsVO();
        if(mapAreaId!=null){
            objACVO = dao.selectMappingDetailsForAreaAndAreaChair(mapAreaId);
        }
        return objACVO;  
      }
      
      
/**
 * Added By          : Harmohan Natm Sharma
 * Update On         : 20-12-2006
 * Name Of Operation : CRUD Operation For Master Table for Event Organizer Form
 */
      
/* =========================CRUD Operation for Event Organizer Master Entries=========================================*/
      /*===============================Dressage Test==================================*/
/**
  * @Method Name    :insertHorseDressageTrial.
  * @Description    :This method will insert svsntSubLevelName.
  * @param          :eventSubLevelName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertHorseDressageTrial(String eventSubLevelName) throws RemoteException {
      boolean bol = false;
      try{
          if (eventSubLevelName != null && eventSubLevelName.trim().length()>0){
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isRecordExist(eventSubLevelName) != true){
                  bol = objDAO.insertRowDHT(eventSubLevelName);
                  Debug.print(" In VaigaiSessionBean while inserting subLevelName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting HorseDressageTrial : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Horse Dressage Trial Name Is exist in the data base OR NULL. Plz. Check & Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateHorseDressageTrial.
  * @Description    :This method will update horseDressageTrial based on eventSubLevelId.
  * @param          :eventSubLevelName, eventSubLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateHorseDressageTrial(String eventSubLevelName, String eventSubLevelId) throws RemoteException {
      boolean bol = false;
      try{
          if ( (eventSubLevelName != null && eventSubLevelName.trim().length() > 0) && 
                  (eventSubLevelId != null && eventSubLevelId.trim().length() > 0)) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isRecordExist(eventSubLevelName) != true){
                  bol = objDAO.updateRowDHT(eventSubLevelName,eventSubLevelId);
                  Debug.print(" In VaigaiSessionBean while Updating Horse dressage Trial in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating HorseDressageTrial : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Horse Dresage Trial Name Is exist in the data base OR NULL. Plz. check & Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteHorseDressageTrial.
  * @Description    :This method will delete horseDressageTrial based on eventSubLevelId.
  * @param          :eventSubLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteHorseDressageTrial(String eventSubLevelId) throws RemoteException {
      boolean bol = false;
      try{
          if (eventSubLevelId != null && eventSubLevelId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              bol = objDAO.deletetRowDHT(eventSubLevelId);
              Debug.print(" In VaigaiSessionBean while deleting Horse dressage Trial in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting HorseDressageTrial : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateHorseDressageTrial.
  * @Description    :This method will activate a record of horseDressageTrial based on eventSubLevelId.
  * @param          :eventSubLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateHorseDressageTrial(String eventSubLevelId) throws RemoteException {
      boolean bol = false;
      try{
          if (eventSubLevelId != null && eventSubLevelId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              bol = objDAO.activateRowDHT(eventSubLevelId);
              Debug.print(" In VaigaiSessionBean while activate Horse dressage Trial in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate HorseDressageTrial : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayHorseDressageTrial.
  * @Description    :This method will display horseDressageTrial based on eventSubLevelId.
  * @param          :eventSubLevelId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayHorseDressageTrial(String eventSubLevelId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          if (eventSubLevelId != null && eventSubLevelId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              vObj = objDAO.loadDHT(eventSubLevelId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Horse dressage Trial in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing HorseDressageTrial : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllHorseDressageTrial.
  * @Description    :This method will display all horseDressageTrial.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllHorseDressageTrial(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadDHTAll(status);
          bol = true;
          Debug.print(" In VaigaiSessionBean while Listing Horse dressage Trial in DAO : "+bol);
      }catch(Exception e){
          Debug.print(" Error While Listing HorseDressageTrial : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }  
  
 /*===============================Dressage Test for Arena Size==================================*/
/**
  * @Method Name    :insertArenaSize.
  * @Description    :This method will insert record to arenaSize.
  * @param          :arenaSizeName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertArenaSize(String arenaSizeName) throws RemoteException {
      boolean bol = false;
      try{
          if (arenaSizeName != null && arenaSizeName.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isArenaRecordExist(arenaSizeName) != true){
                  bol = objDAO.insertArenaSize(arenaSizeName);
                  Debug.print(" In VaigaiSessionBean while inserting arenaSizeName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting Arena Size : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Arena Name Is exist in the data base Plz. Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateArenaSize.
  * @Description    :This method will update arena size based on arenasizeId.
  * @param          :arenaSizeName, arenaSizeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateArenaSize(String arenaSizeName, String arenaSizeId) throws RemoteException {
      boolean bol = false;
      try{
          if ( (arenaSizeName != null && arenaSizeName.trim().length() > 0) && 
                  (arenaSizeId != null && arenaSizeId.trim().length() > 0)) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isArenaRecordExist(arenaSizeName) != true){
                  bol = objDAO.updateArenaSize(arenaSizeName,arenaSizeId);
                  Debug.print(" In VaigaiSessionBean while Updating Arena Size in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating Arena Size : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Arena Name Is exist in the data base Or NULL. Plz. Check & Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteArenaSize.
  * @Description    :This method will delete arena Size based on arenaSizeId.
  * @param          :arenaSizeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteArenaSize(String arenaSizeId) throws RemoteException {
      boolean bol = false;
      try{
          if (arenaSizeId != null && arenaSizeId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              bol = objDAO.deletetArenaSize(arenaSizeId);
              Debug.print(" In VaigaiSessionBean while deleting Arena Size in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting Arena Size : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateArenaSize.
  * @Description    :This method will activate a record of arena Size based on ArenaSizeId.
  * @param          :ArenaSizeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateArenaSize(String arenaSizeId) throws RemoteException {
      boolean bol = false;
      try{
          if (arenaSizeId != null && arenaSizeId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              bol = objDAO.activateArenaSize(arenaSizeId);
              Debug.print(" In VaigaiSessionBean while activate Arena Size in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate Arena Size : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayArenaSize.
  * @Description    :This method will display Arena Size based on arenaSizeId.
  * @param          :arenaSizeId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayArenaSize(String arenaSizeId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          if (arenaSizeId != null && arenaSizeId.trim().length()>0 ){
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              vObj = objDAO.loadArenaSize(arenaSizeId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Arena Size in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing Arena Size : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllArenaSize.
  * @Description    :This method will display all arena size.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllArenaSize(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllArenaSize(status);
          bol = true;
          Debug.print(" In VaigaiSessionBean while Listing Arena Size in DAO : "+bol);
      }catch(Exception e){
          Debug.print(" Error While Listing Arena Size : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }    
  
/*===============================For Officials==================================*/
/**
  * @Method Name    :insertOficial.
  * @Description    :This method will insert record to Judge Type.
  * @param          :judgeTypeName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertOficial(String judgeTypeName) throws RemoteException {
      boolean bol = false;
      try{
          if (judgeTypeName != null && judgeTypeName.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isOfficialRecordExist(judgeTypeName) != true){
                  bol = objDAO.insertOfficial(judgeTypeName);
                  Debug.print(" In VaigaiSessionBean while inserting judgeTypeName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting Judge Type in insertOficial of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Judge Type Name Is exist in the data base Plz. Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateOfficial.
  * @Description    :This method will update Judge Type based on judgetypeId.
  * @param          :judgeTypeName, judgetypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateOfficial(String judgeTypeName, String judgetypeId) throws RemoteException {
      boolean bol = false;
      try{
          if ( (judgeTypeName != null && judgeTypeName.trim().length() > 0) &&
                  (judgetypeId != null && judgetypeId.trim().length() > 0) ){
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isOfficialRecordExist(judgeTypeName) != true){
                  bol = objDAO.updateJudgeType(judgeTypeName,judgetypeId);
                  Debug.print(" In VaigaiSessionBean while Updating Judge type in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating Judge type In  VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Judge type is exist in the data base Or Null . Plz. Check & Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteOfficial.
  * @Description    :This method will delete Judge Type based on judgeTypeId.
  * @param          :judgeTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteOfficial(String judgeTypeId) throws RemoteException {
      boolean bol = false;
      try{
          if (judgeTypeId != null && judgeTypeId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              bol = objDAO.deletetOfficial(judgeTypeId);
              Debug.print(" In VaigaiSessionBean while deleting Judge Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting Judge type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateJudgeType.
  * @Description    :This method will activate a record of Judge Type Master based on judgeTypeId.
  * @param          :judgeTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateJudgeType(String judgeTypeId) throws RemoteException {
      boolean bol = false;
      try{
          if (judgeTypeId != null && judgeTypeId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              bol = objDAO.activateJudgeType(judgeTypeId);
              Debug.print(" In VaigaiSessionBean while activate Judge type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate Judge type in VaigaiSessionBran : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayJudgeType.
  * @Description    :This method will display Judge Type based on judgeTypeId.
  * @param          :judgetypeId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayJudgeType(String judgetypeId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          if (judgetypeId != null && judgetypeId.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              vObj = objDAO.loadJudgetype(judgetypeId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Judge type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing Judge type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllJudgetype.
  * @Description    :This method will display all arena size.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllJudgetype(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllJudgeType(status);
          bol = true;
          
          Debug.print(" status value on loadAllJudgeType(boolean status) : "+status);
          Debug.print(" In VaigaiSessionBean while Listing Judge Type in DAO : "+bol);
          
      }catch(Exception e){
          Debug.print(" Error While Listing Judge type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }    
  
/*===============================For REFUNDS==================================*/
/**
  * @Method Name    :insertRefundRuleMaster.
  * @Description    :This method will insert record to Refund Rule Master.
  * @param          :refundRuleName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertRefundRuleMaster(String refundRuleName) throws RemoteException {
      boolean bol = false;
      Debug.print(" In VaigaiSessionBean insertRefundRuleMaster refundRuleName: "+refundRuleName);
      try{
          if (refundRuleName != null && refundRuleName.trim().length() > 0) {
              HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
              if (objDAO.isRefundMasterRecordExist(refundRuleName) != true){
                  bol = objDAO.insertRefundRuleMaster(refundRuleName);
                  Debug.print(" In VaigaiSessionBean while inserting RefundRuleName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting Judge Type in insertRefundRuleMaster of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false){
          Debug.print(" Refund Rule Type Name Is exist in the data base Plz. Insert with another name....");
      }
      return bol;
  }
  
/**
  * @Method Name    :updateRefundRuleMaster.
  * @Description    :This method will update Judge Type based on judgetypeId.
  * @param          :refundRuleTypeName, RefundRuleTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateRefundRuleMaster(String refundRuleTypeName, String RefundRuleTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( (refundRuleTypeName != null && refundRuleTypeName.trim().length() > 0) &&
                  (RefundRuleTypeId != null && RefundRuleTypeId.trim().length() > 0)) {
              if (objDAO.isRefundMasterRecordEditExist(RefundRuleTypeId, refundRuleTypeName) != true){
                  bol = objDAO.updateRefundRuleMaster(refundRuleTypeName,RefundRuleTypeId);
                  Debug.print(" In VaigaiSessionBean while Updating Refund Rule type in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating refund Rule type In  VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" refund Rule Type is exist in the data base Plz. Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteRefundRuleMaster.
  * @Description    :This method will delete Refund Rule Type based on refundRuleTypeId.
  * @param          :refundRuleTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteRefundRuleMaster(String refundRuleTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundRuleTypeId != null && refundRuleTypeId.trim().length() > 0) {
              bol = objDAO.deletetRefundRuleMaster(refundRuleTypeId);
              Debug.print(" In VaigaiSessionBean while deleting Refund Rule Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting Refund rule Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateRefundRuleType.
  * @Description    :This method will activate a record of Refund Rule Type Master based on refundRuleTypeId.
  * @param          :refundRuleTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateRefundRuleType(String refundRuleTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundRuleTypeId != null && refundRuleTypeId.trim().length() > 0) {
              bol = objDAO.activateRefundRuleMaster(refundRuleTypeId);
              Debug.print(" In VaigaiSessionBean while activate Refund Rule type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate Refund rule type in VaigaiSessionBran : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :refundruleTypeId.
  * @Description    :This method will display Refund Rule Type based on refundruleTypeId.
  * @param          :refundruleTypeId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayRefundRule(String refundruleTypeId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundruleTypeId != null && refundruleTypeId.trim().length() > 0) {
              vObj = objDAO.loadRefundRuleMaster(refundruleTypeId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Refund Rule type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing Refund rule type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllRefundRule.
  * @Description    :This method will display all Refund Rule type Master.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllRefundRule(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllRefundRuleMaster(status);
          bol = true;
          Debug.print(" status in loadAllRefundRuleMaster(status): "+status);
          Debug.print(" In VaigaiSessionBean while Listing Refund rule Type in DAO : "+bol);
      }catch(Exception e){
          Debug.print(" Error While Listing Refund Rule Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }  
  
/*===============================For REFUNDS Sub Type Master==================================*/
/**
  * @Method Name    :mapRefundRule.
  * @Description    :This method map the Refund rule for Refund sule Master and Refund Rule subType Master.
  * @param          :refundRuleTypeId, refundRuleSubTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */  
  public boolean mapRefundRule(String refundRuleTypeId, String refundRuleSubTypeId) throws RemoteException{
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( ( refundRuleTypeId != null && refundRuleTypeId.trim().length()>0 ) &&
                  (refundRuleSubTypeId != null && refundRuleSubTypeId.trim().length() > 0) ) {
              if (objDAO.isMapRefundRuleExist(refundRuleTypeId, refundRuleSubTypeId ) != true){
                  bol = objDAO.insertMapRefundRule(refundRuleTypeId, refundRuleSubTypeId);
                  Debug.print(" In VaigaiSessionBean while inserting mapRefundRule in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting event Level Type in mapDressageTest of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      
      return bol;
  }  
/**
  * @Method Name    :insertRefundRuleMaster.
  * @Description    :This method will insert record to Refund Rule Sub Type Master.
  * @param          :refundRuleSubTypeName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertRefundRuleSubTypeMaster(String refundRuleSubTypeName, boolean status) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundRuleSubTypeName != null && refundRuleSubTypeName.trim().length() > 0) {
              if (objDAO.isRefundSubMasterRecordExist(refundRuleSubTypeName) != true){
                  bol = objDAO.insertRefundRuleSubMaster(refundRuleSubTypeName,status);
                  Debug.print(" In VaigaiSessionBean while inserting RefundRuleSubTypeName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting refund Sub Type in insertRefundRuleSubTypeMaster of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Refund Rule Sub Type Name Is exist in the data base Plz. Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateRefundRuleMaster.
  * @Description    :This method will update Judge Type based on judgetypeId.
  * @param          :refundRuleSubTypeName, RefundRuleSubTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateRefundRuleSubMaster(String refundRuleSubTypeName, String RefundRuleSubTypeId, boolean boxStatus) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundRuleSubTypeName != null && refundRuleSubTypeName.trim().length() > 0){
              if (objDAO.isRefundSubMasterRecordEditExist(RefundRuleSubTypeId, refundRuleSubTypeName) != true){
                  bol = objDAO.updateRefundRuleSubMaster(refundRuleSubTypeName,RefundRuleSubTypeId,boxStatus);
                  Debug.print(" In VaigaiSessionBean while Updating Refund Rule Sub Type in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating refund Rule Sub Type In  VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" refund Rule Sub Type is exist in the data base Or Null. Plz. check and Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteRefundRuleSubMaster.
  * @Description    :This method will delete Refund Rule Type based on refundRuleSubTypeId.
  * @param          :refundRuleSubTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteRefundRuleSubMaster(String refundRuleSubTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundRuleSubTypeId != null && refundRuleSubTypeId.trim().length() > 0) {
              bol = objDAO.deletetRefundRuleSubMaster(refundRuleSubTypeId);
              Debug.print(" In VaigaiSessionBean while deleting Refund Rule Sub Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting Refund rule Sub Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateRefundRuleSubType.
  * @Description    :This method will activate a record of Refund Rule Sub Type Master based on refundRulesubTypeId.
  * @param          :refundRulesubTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateRefundRuleSubType(String refundRulesubTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundRulesubTypeId != null && refundRulesubTypeId.trim().length() > 0) {
              bol = objDAO.activateRefundRuleSubMaster(refundRulesubTypeId);
              Debug.print(" In VaigaiSessionBean while activate Refund Rule Sub type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate Refund rule Sub Type in VaigaiSessionBran : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayRefundRuleSubType.
  * @Description    :This method will display Refund Rule Type based on refundruleSubTypeId.
  * @param          :refundruleSubTypeId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayRefundRuleSubType(String refundruleSubTypeId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (refundruleSubTypeId != null && refundruleSubTypeId.trim().length() > 0) {
              vObj = objDAO.loadRefundRuleSubMaster(refundruleSubTypeId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Refund Rule Sub Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing Refund Rule sub Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllRefundRuleSubType.
  * @Description    :This method will display all Refund Rule Sub Type Master.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllRefundRuleSubType(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllRefundRuleSubMaster(status);
          bol = true;
          Debug.print(" Status value from method displayAllRefundRuleSubType(String status) : "+status);
           
          Debug.print(" In VaigaiSessionBean while Listing Refund rule Sub Type in DAO : "+bol);
      }catch(Exception e){
          Debug.print(" Error While Listing Refund Rule Sub Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }    
 /*===============================For Event Division Master==================================*/
/**
  * @Method Name    :createEventDivision.
  * @Description    :This method will insert record to Event Type Master.
  * @param          :divisionName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean CreateEventDivision(String divisionName) throws RemoteException {
      Debug.print("Create Event Division in VaigaiSession bean mro.org" + divisionName);
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (divisionName != null && divisionName.trim().length() > 0) {
              if (objDAO.isEventDivisionExist(divisionName) != true){
                  bol = objDAO.insertEventDivision(divisionName);
                  Debug.print(" In VaigaiSessionBean while inserting EventTypeName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting refund Sub Type in CreateEventDivision of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Event Type Name Is exist in the data base Plz. Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateEventDivision.
  * @Description    :This method will update Event Type based on eventTypeId.
  * @param          :divisionName, divisionId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateEventDivision(String divisionName, String divisionId) throws RemoteException {
      Debug.print("updateEventDivision in VaigaiSession bean :" + divisionId);
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( (divisionName != null && divisionName.trim().length() > 0) && 
                  (divisionId != null && divisionId.trim().length() > 0)) {
              if (objDAO.isEventDivisionEditExist(divisionName, divisionId) == false) {
                  bol = objDAO.updateEventDivsion(divisionName,divisionId);
                  Debug.print(" Result of Update :"+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating updateEventDivision In  VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }  
/**
  * @Method Name    :deleteEventDivision.
  * @Description    :This method will delete Event Type based on divisionId.
  * @param          :divisionId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteEventDivision(String divisionId) throws RemoteException {
      Debug.print("deleteEventDivision in VaigaiSession bean mro.org" + divisionId);
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (divisionId != null && divisionId.trim().length() > 0) {
              bol = objDAO.deletetEventDivision(divisionId);
              Debug.print(" In VaigaiSessionBean while deleting event Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateEventDivision.
  * @Description    :This method will activate a record of Event Type Master based on eventTypeId.
  * @param          :divisionId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateEventDivision(String divisionId) throws RemoteException {
      Debug.print("activateEventDivision in VaigaiSession bean mro.org" + divisionId);
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (divisionId != null && divisionId.trim().length() > 0) {
              bol = objDAO.activateEventDivison(divisionId);
              Debug.print(" In VaigaiSessionBean while activate Event type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate event Type in VaigaiSessionBran : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayEventDivision.
  * @Description    :This method will display Event Type Master based on eventTypeId.
  * @param          :divisionId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector listEventDivision(String divisionId) throws RemoteException {
       Debug.print("listEventDivision in VaigaiSession bean mro.org" + divisionId);
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (divisionId != null && divisionId.trim().length() > 0){
          vObj = objDAO.loadEventDivision(divisionId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Event Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :listAllEventDivision.
  * @Description    :This method will display all event Type Master.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector listAllEventDivision(boolean status) throws RemoteException {
      Debug.print("listAllEventDivision in VaigaiSession bean mro.org" );
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllEventDivision(status);
          bol = true;
          Debug.print(" status in objDAO.loadAllEventTypeMaster(status) : "+status);
          Debug.print(" In VaigaiSessionBean while Listing Event Type in DAO : "+bol);
          
      }catch(Exception e){
          Debug.print(" Error While Listing Event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }  
  
  /**
  * @Method Name    :getAllEventDivision.
  * @Description    :This method will display all event Type Master.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector getAllEventDivision() throws RemoteException {
      Debug.print("getAllEventDivision in VaigaiSession bean mro.org" );
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllEventDivision();
           
          Debug.print(" status in objDAO.getAllEventDivisio");
          Debug.print(" In VaigaiSessionBean while Listing Event Type in DAO : ");
          
      }catch(Exception e){
          Debug.print(" Error While Listing Event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  } 
 /*===============================For Event Type Master==================================*/
/**
  * @Method Name    :insertEventTypeMaster.
  * @Description    :This method will insert record to Event Type Master.
  * @param          :eventTypeName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertEventTypeMaster(String eventTypeName) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (eventTypeName != null && eventTypeName.trim().length() > 0) {
              if (objDAO.isEventTypeMasterRecordExist(eventTypeName) != true){
                  bol = objDAO.insertEventTypeMaster(eventTypeName);
                  Debug.print(" In VaigaiSessionBean while inserting EventTypeName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting refund Sub Type in insertEventTypeMaster of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Event Type Name Is exist in the data base Plz. Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateEventTypeMaster.
  * @Description    :This method will update Event Type based on eventTypeId.
  * @param          :eventTypeName, eventTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateEventTypeMaster(String eventTypeName, String eventTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( (eventTypeName != null && eventTypeName.trim().length() > 0) && 
                  (eventTypeId != null && eventTypeId.trim().length() > 0)) {
              if (objDAO.isEventTypeMasterRecordEditExist(eventTypeName, eventTypeId) == false) {
                  bol = objDAO.updateEventTypeMaster(eventTypeName,eventTypeId);
                  Debug.print(" In VaigaiSessionBean while Updating Event Type in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating updateEventTypeMaster In  VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Event Type is exist in the data base Plz. Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteEventTypeMaster.
  * @Description    :This method will delete Event Type based on eventTypeId.
  * @param          :eventTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteEventTypeMaster(String eventTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (eventTypeId != null && eventTypeId.trim().length() > 0) {
              bol = objDAO.deletetEventTypeMaster(eventTypeId);
              Debug.print(" In VaigaiSessionBean while deleting event Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateEventTypeMaster.
  * @Description    :This method will activate a record of Event Type Master based on eventTypeId.
  * @param          :eventTypeId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateEventTypeMaster(String eventTypeId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (eventTypeId != null && eventTypeId.trim().length() > 0) {
              bol = objDAO.activateEventTypeMaster(eventTypeId);
              Debug.print(" In VaigaiSessionBean while activate Event type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate event Type in VaigaiSessionBran : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayEventTypeMaster.
  * @Description    :This method will display Event Type Master based on eventTypeId.
  * @param          :eventTypeId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayEventTypeMaster(String eventTypeId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (eventTypeId != null && eventTypeId.trim().length() > 0){
              vObj = objDAO.loadEventTypeMaster(eventTypeId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Event Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllEventTypeMaster.
  * @Description    :This method will display all event Type Master.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllEventTypeMaster(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllEventTypeMaster(status);
          bol = true;
          Debug.print(" status in objDAO.loadAllEventTypeMaster(status) : "+status);
          Debug.print(" In VaigaiSessionBean while Listing Event Type in DAO : "+bol);
          
      }catch(Exception e){
          Debug.print(" Error While Listing Event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }  
/**
  * @Method Name    :mapDressageTest.
  * @Description    :This method map the Dressage Test for Horse Trial for Advance Level and Arena Size.
  * @param          :eventLevelId, eventSubLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */  
  public boolean mapDressageTest(String eventLevelId, String eventSubLevelId) throws RemoteException{
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( ( eventLevelId != null && eventLevelId.trim().length()>0 ) &&
                  (eventSubLevelId != null && eventSubLevelId.trim().length() > 0) ) {
              if (objDAO.isMapDressageTestExist(eventLevelId, eventSubLevelId ) != true){
                  bol = objDAO.insertMapDressageTest(eventLevelId, eventSubLevelId);
                  Debug.print(" In VaigaiSessionBean while inserting mapDressageTest in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting event Level Type in mapDressageTest of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      
      return bol;
  }
 /*------------------------DIVISION EVENT LEVEL MASTER CRUD Operation------------------------*/
  
 /**
  * @Method Name    :insertEventLevelMaster.
  * @Description    :This method will insert record to Event Level Master.
  * @param          :eventTypeName
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean insertEventLevelMaster(String eventLevelName, String eventLevelCode, String jumpingEffort) throws RemoteException {
      boolean bol = false;
      if (jumpingEffort == null)
          jumpingEffort = null;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( ( eventLevelName != null && eventLevelName.trim().length()>0 ) &&
                  (eventLevelCode != null && eventLevelCode.trim().length() > 0) ) {
              if (objDAO.isEventLevelMasterRecordExist(eventLevelName, eventLevelCode ) != true){
                  bol = objDAO.insertEventLevelMaster(eventLevelName, eventLevelCode, jumpingEffort);
                  Debug.print(" In VaigaiSessionBean while inserting EventTypeName in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While Inserting event Level Type in insertEventLevelMaster of VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Event Type Name Is exist in the data base Or Null, Plz. Check and Insert with another name....");
      return bol;
  }
  
/**
  * @Method Name    :updateEventLevelMaster.
  * @Description    :This method will update Event Type based on eventLevelId.
  * @param          :eventLevelName, eventLevelCode, jumpingEfforts, eventLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean updateEventLevelMaster(String eventLevelName, String eventLevelCode, String jumpingEfforts,String eventLevelId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( (eventLevelName != null && eventLevelName.trim().length() > 0) && 
                  (eventLevelCode != null && eventLevelCode.trim().length() > 0) && 
                  (eventLevelId != null && eventLevelId.trim().length() > 0)) {
              if (objDAO.isEventLevelMasterRecordEditExist(eventLevelId, eventLevelName, eventLevelCode) != true){
                  bol = objDAO.updateEventLevelMaster(eventLevelName,eventLevelCode, jumpingEfforts,eventLevelId );
                  Debug.print(" In VaigaiSessionBean while Updating Event Type in DAO : "+bol);
              }
          }
      }catch(Exception e){
          Debug.print(" Error While updating updateEventTypeMaster In  VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      if (bol == false)
          Debug.print(" Event Level is exist OR Null in the data base Plz. check and Update with another name....");
      return bol;
  }  
/**
  * @Method Name    :deleteEventLevelMaster.
  * @Description    :This method will delete Event Level based on eventLevelId.
  * @param          :eventLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean deleteEventLevelMaster(String eventLevelId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (eventLevelId != null && eventLevelId.trim().length() > 0){
              bol = objDAO.deletetEventLevelMaster(eventLevelId);
              Debug.print(" In VaigaiSessionBean while deleting event Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While deleting event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }   
/**
  * @Method Name    :activateEventLevelMaster.
  * @Description    :This method will activate a record of Event Level Master based on eventLevelId.
  * @param          :eventLevelId
  * @return         :boolean value
  * @throws         :RemoteException.
  */
      
  public boolean activateEventLevelMaster(String eventLevelId) throws RemoteException {
      boolean bol = false;
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if (eventLevelId != null && eventLevelId.trim().length() > 0){
              bol = objDAO.activateEventLevelMaster(eventLevelId);
              Debug.print(" In VaigaiSessionBean while activate Event type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While activate event Type in VaigaiSessionBran : "+e.getMessage());
          e.printStackTrace();
      }
      return bol;
  }    
/**
  * @Method Name    :displayEventLevelMaster.
  * @Description    :This method will display Event Type Master based on eventLevelId.
  * @param          :eventLevelId
  * @return         :vector
  * @throws         :RemoteException.
  */
      
  public Vector displayEventLevelMaster(String eventLevelId) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          if ( eventLevelId != null && eventLevelId.trim().length() > 0){
              vObj = objDAO.loadEventLevelMaster(eventLevelId);
              bol = true;
              Debug.print(" In VaigaiSessionBean while Listing Event Type in DAO : "+bol);
          }
      }catch(Exception e){
          Debug.print(" Error While Listing event Type in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  }
  
/**
  * @Method Name    :displayAllEventLevelMaster.
  * @Description    :This method will display all event Level Master.
  * @param          :None
  * @return         :Vector
  * @throws         :RemoteException.
  */
      
  public Vector displayAllEventLevelMaster(boolean status) throws RemoteException {
      boolean bol = false;
      Vector vObj = new Vector();
      try{
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
          vObj = objDAO.loadAllEventLevelMaster(status);
          bol = true;
          Debug.print(" status value in objDAO.loadAllEventLevelMaster(status) : "+status);
          Debug.print(" In VaigaiSessionBean while Listing Event Level in DAO : "+bol);
          
      }catch(Exception e){
          Debug.print(" Error While Listing Event Level in VaigaiSessionBean : "+e.getMessage());
          e.printStackTrace();
      }
      return vObj;
  } 
  
  
/*=========================END Of CRUD Operation for Event Organizer===================================*/
     

   //================================== Renewal End Of Endorse Form ==================      
  
  
  /*========================Method for Event Calendar===================================*/
/**
  * @Method Name    :eventDateAvailability.
  * @Description    :This method will check for date availability for events. If available then returns true otherwise false.
  * @param          :compDate.
  * @return         :boolean value.
  * @throws         :RemoteException, FinderException.
  */
  
  public boolean eventDateAvailability(String compDate)throws RemoteException, FinderException {
      Debug.print("VaigaiSessionBean eventDateAvailability");
      boolean bol = true;
      String result = null;
      if (compDate == null)
          throw new EJBException("competation Date is Null");
      
      try {
          SimpleDateFormat sdfOutput =  new SimpleDateFormat  ("MM/dd/yyyy") ; 
          java.util.Date cDate = sdfOutput.parse(compDate);
          Debug.print(" eventDateAvailability result is cDate: "+cDate);
          Collection result1 = objMetturHome.findByCompititionDate(cDate);
          if (result1 != null && result1.size() != 0 ){
              Iterator e = result1.iterator();
              while(e.hasNext()){
                  objMetturremote = (HLCMetturDamEntityLocalRenewal )e.next();
                  Debug.print("objMetturremote : "+objMetturremote);
                  bol = false;
              }
          }          
          Debug.print(" eventDateAvailability result is : "+result1);
      }catch(Exception e){
          Debug.print("Exception in eventDateAvailability : "+e);
      }
     
      return bol;
  }
  
  
 /**
  * @Method Name    :makeConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */
       private void makeConnection() {
            Debug.print("VaigaiSessionBean Event: makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }
         // makeConnection
/**
  * @Method Name    :releaseConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */
     private void releaseConnection() {
            Debug.print("VaigaiSessionBean releaseConnection");

            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
 /**
  * @Method Name    :getInitialContext.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :InitialContext value.
  * @throws         :javax.naming.NamingException.
  */
       public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("Vaigai Bean: This is from getInitialContext()");
        return ic;
    }
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :long value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */
      private long getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("VaigaiSessionBean getNextId");
        makeConnection();
       //For Debugs Starts Here
       // String selectStatement = "SELECT max(event_id) from " + DBHelper.USEA_MMS_EVENTDETAILS;
        String selectStatement = "select max(event_id) as max_event_id from ((select event_id from tbloeprovisionalcalendar)union (select event_id from " +DBHelper.USEA_MMS_EVENTDETAILS+ ")) as max_query";
        //For Debugs Ends Here

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);

        ResultSet rs = prepSelect.executeQuery();
        rs.next();
        long nextId = rs.getLong(1);
        
        if(nextId==0){
            nextId = 10000;
        }
        else{
            nextId = nextId+1;
        }
        rs.close();
        prepSelect.close();
        releaseConnection();
        return nextId;
    }
         // releaseConnection
/* ==============================Event Calendar Methods ============================================*/
           
/**
  * @Method Name    :createEventCalendar.
  * @Description    :This method will create a Event Calendar for the following year. If the Privilege Name already exist it will return.
  * @param          :EventDetailsVO objEventDet, Vector accommodation, Vector crossCountry, Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails, OtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime.
  * @return         :String value.
  * @throws         :RemoteException.
  */
    public String createEventCalendar(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException {
        /*try{
            this.eventId =  String.valueOf(getNextId());
            objEventDet.setEventId(eventId);
            remote = home.create(objEventDet, accommodation, crossCountry,
            cogginsDetails, divisionLevels, hDressage, judgesDetails,
            othersDet, refundRuleDetails, tentativeTime);
        }
        catch(Exception exp){
             throw new EJBException("CreateEventCalendar Details: " + exp.getMessage());
        }*/
        try {
            this.eventId = createEvent(objEventDet, accommodation, crossCountry, cogginsDetails, divisionLevels, hDressage, judgesDetails,
            othersDet, refundRuleDetails, tentativeTime);
        }catch(Exception e){
            throw new EJBException("CreateEventCalendar Details: " + e.getMessage());
        }
        return eventId;
    }      
    
/**
  * @Method Name    :editEventCalendar.
  * @Description    :This method will edit event Calender. If the the Event Organizer gets updated the a boolean value will return.
  * @param          :EventDetailsVO objEventDet, Vector accommodation, Vector crossCountry, Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails, OtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime.
  * @return         :boolean value.
  * @throws         :RemoteException.
  */    
    public boolean editEventCalendar(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException, FinderException {
        /*String eventId = objEventDet.getEventId();
        EventDetailsVO objEventDetail = objEventDet;
        
        boolean bol = false;       
        try {
            boolean bol1 = false;
            
            bol1 = editAccomodation(eventId,accommodation);
            Debug.print("In editEventOrganizer Succefully update editAccomodation : "+bol1);
            bol1 = editCrossCountry(eventId, crossCountry);
            Debug.print("In editEventOrganizer Succefully update editCrossCountry : "+bol1);
            bol1 = editCogginsDetail(cogginsDetails);
            Debug.print("In editEventOrganizer Succefully update editCogginsDetail : "+bol1);
            bol1 = editDivisionLevel(eventId, divisionLevels);
            Debug.print("In editEventOrganizer Succefully update editDivisionLevel : "+bol1);
            bol1 = editHorseDressage(eventId, hDressage);
            Debug.print("In editEventOrganizer Succefully update editHorseDressage : "+bol1);
            bol1 = editJudgeDetails(eventId, judgesDetails);
            Debug.print("In editEventOrganizer Succefully update editJudgeDetails : "+bol1);
            bol1 = editOtherDetails(othersDet);
            Debug.print("In editEventOrganizer Succefully update editOtherDetails : "+bol1);
            bol1 = editRefundRuleDetails(eventId, refundRuleDetails);
            Debug.print("In editEventOrganizer Succefully update editRefundRuleDetails : "+bol1);
            editTentativeTime(eventId, tentativeTime);
            Debug.print("In editEventOrganizer Succefully update editTentativeTime : ");
            editEvent(objEventDetail);
            Debug.print("In editEventOrganizer Succefully update editEvent");
            
            bol = true;            
        }catch(Exception e){
            e.printStackTrace();
            throw new EJBException("Error in  EditEventOrganizsr Details : "+e.getMessage());
            
        }*/
        boolean bol = false;
        try {
            bol = editEventOrganizer(objEventDet, accommodation, crossCountry, cogginsDetails, divisionLevels, hDressage, judgesDetails,
            othersDet, refundRuleDetails, tentativeTime);
        }catch(Exception e){
            throw new EJBException("Error in  EditEventCalendar Details : "+e.getMessage());
        }
        return bol;
    }    
    
    public boolean generateCalendarBasedOnYear(String fromYear, String forYear) throws RemoteException {
        boolean bol = false;
        HLCEventDetailsVO eventCalVO = new HLCEventDetailsVO();
        HLCEventCalDAO eventCalDAO = new HLCEventCalDAO();
        try {
           List list = eventCalDAO.getEventId(fromYear);
            for (int i=0;i<list.size(); i++){
                String eventId = (String)list.get(i);
                eventCalVO = eventCalDAO.getEventCalendar(eventId);
                String str = eventCalVO.getDateAvailable();
                String s1[] = str.split(" ");
                str = s1[0];
                String finalDate = null;
                String pattern = fromYear;
                String replace= forYear;
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
                
                /* Checking of the Leap Year for the year and converting it to the next month date
                   If the year is not a leap year and the date is 29th then the date will be change to 
                   year-03-01.
                 */   
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
                
                Debug.print("Date Available is : "+finalDate);
                eventCalVO.setDateAvailable(finalDate);

                Vector accommodation = eventCalDAO.getAccomodationForCalendar(eventId);
                Vector crossCountry = eventCalDAO.getCrossCountryForCalendar(eventId);
                Vector cogginsDetails = eventCalDAO.getCogginsDetailsForCalendar(eventId);
                Vector divisionLevels = eventCalDAO.getDivisionLevelsForCalendar(eventId);
                Vector hDressage = eventCalDAO.getHorseDressageForCalendar(eventId);
                Vector judgesDetails = eventCalDAO.getJudgesDetailsForCalendar(eventId);
                HLCOtherDetailsVO othersDet = eventCalDAO.getOtherDetailsForCalendar(eventId);
                Vector refundRuleDetails = eventCalDAO.getRefundRuleDetailsForCalendar(eventId);
                Vector tentativeTime = eventCalDAO.getTentativeTimeForCalendar(eventId);
                Debug.print(" Event Name : "+eventCalVO.getEventTitle()+"     Event Year : "+forYear);
                
                if (eventCalDAO.isEventNameExist(eventCalVO.getEventTitle(),forYear) == false){
                    String ID = createEventCalendar( eventCalVO,  accommodation,  crossCountry, cogginsDetails,  divisionLevels,
                           hDressage,  judgesDetails, othersDet,  refundRuleDetails,  tentativeTime);
                    if (ID != null && ID.trim().length() >0 ){
                       bol = true;
                    }
                }
                                             
            }
        }catch(Exception e){
            e.printStackTrace();            
        }
       return bol;
    }
    
/**
  * @Method Name    :getCurrentYearCalendar.
  * @Description    :This method will get minimal information for the calendar for the current year. 
  * @param          :None.
  * @return         :List.
  * @throws         :RemoteException.
  */     
    public List getCurrentYearCalendar(String areaName) throws RemoteException {
        List calList = new ArrayList();
        HLCCalendarVO calVO = null;
        HLCEventCalDAO eventCalDAO = new HLCEventCalDAO();
        Calendar c = Calendar.getInstance();
        Debug.print("  Current YEAR      : " + c.get(Calendar.YEAR));
        String currentYear = String.valueOf(c.get(Calendar.YEAR));
        try {
           List list = eventCalDAO.getEventId(currentYear);
                for (int i=0;i<list.size(); i++){
                    String eventId = (String)list.get(i);
                    calVO = new HLCCalendarVO();
                    Debug.print(" Event Id in VaigaiSessionBean.getCurrentYearCalendar() ..."+eventId);
                    if (eventId != null && eventId.trim().length() > 0) {
                        calVO = eventCalDAO.getCalendarInfo(eventId,areaName);
                        if (calVO != null){
                            calList.add(calVO);
                        }
                    }
                }
        }catch(Exception e){
            e.printStackTrace();  
        }
        return calList;
    }
    
/**
  * @Method Name    :getZipcodeFromToOnStateId.
  * @Description    :This method will get information of the zip codes matching the state id
  * @param          :None.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */     
    public ArrayList getZipcodeFromToOnStateId(String stateID,String zipValue) throws RemoteException {
        ArrayList zipCodeLst = new ArrayList();
        
        Debug.print("getZipcodeFromToOnStateId state Id is "+stateID);
        Debug.print("getZipcodeFromToOnStateId zip Value is "+zipValue);
        try {
            zipCodeLst = new HLCEventsDAO().getZipCodesOnStateId(stateID,zipValue);
        }
        catch(Exception e){
            e.printStackTrace();  
        }
        return zipCodeLst;
    }

 /*=================================Event Registration Request==========================================*/  
    public String insertEventRequest(HLCEventRequestVO requestVO, String userId) throws RemoteException {
        boolean result = false;
         Debug.print("userId in VaigaiSessionBean:"+userId);
        String eventId = "";
        try {
            eventId =  String.valueOf(getNextId());
            requestVO.setEvent_id(eventId);
            Debug.print("Event Id:"+eventId);
            result = new HLCEventsDAO().insertEventRegiRequest(requestVO,userId);
            if(result==false){
                eventId = "";
            }
        }
        catch(Exception e){
            e.printStackTrace();  
        }
        return eventId;
    }    
    /**
  * @Method Name    :getAreaChairDetails.
  * @Description    :This method will get all Event Registered for the Given calendar year ,status 
  *                 and Area based on the Logged in Area Chair
  * @param          :String status,int year ,String memberId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */ 
    public ArrayList getAreaChairDetails(String status,int year ,String memberId) throws RemoteException{
        ArrayList areaEventRegList = new ArrayList();
        try{
            areaEventRegList = new HLCEventsDAO().getAreaChairDetails(status,year,memberId);
           
        }catch(Exception e){
            e.printStackTrace();
        }
        return areaEventRegList;
    }
    /**
  * @Method Name    :getAreaChairMemberId.
  * @Description    :This method will get Area Chair based on the Area Id passed
  * @param          :String areaId
  * @return         :String.
  * @throws         :RemoteException.
  */ 
    public String getAreaChairMemberId(String areaId) throws RemoteException{
        String areaChairMemberId = new String();
        try{
            areaChairMemberId = new HLCEventsDAO().getAreaChairMemberId(areaId);
           
        }catch(Exception e){
            e.printStackTrace();
        }
        return areaChairMemberId;
    }
    /*=================================Area Chair Event Registration Request List==========================================*/
     public HLCEventRequestVO getEventRequestDetails(String eventId) throws RemoteException{
         HLCEventRequestVO eventReqVO = new HLCEventRequestVO();
         try{
             eventReqVO = new HLCEventsDAO().getEventRequestDetails(eventId);
         }catch(Exception e){
             e.printStackTrace();
         }
         return eventReqVO;        
     }
    /*=================================Area Chair Event Registration Request Edit==========================================*/
     public String updateEventRequest(HLCEventRequestVO eventReqVO) throws RemoteException{
         //boolean result = false;
         String eventId="";
         Debug.print("VaigaiSessionBean.updateEventRequest():");
         try{
             eventId = new HLCEventsDAO().updateEventRequest(eventReqVO);
         }catch(Exception e){
             e.printStackTrace();
         }
         return eventId;
     }
   /*=================================Area Chair Event Registration Request Approve==========================================*/
     public boolean changeEventRequestStatus(String eventId,String approveStatus) throws RemoteException{
         boolean result = false;
         try{
             result = new HLCEventsDAO().changeEventRequestStatus(eventId,approveStatus);
         }catch(Exception e){
             e.printStackTrace();
         }
         return result;
     }
     /**************************************For Organizer Event List==========================================*/         
public ArrayList getAllOrgEventDetails(String organizerId) throws RemoteException {
        Debug.print("KaverySessionBean getAllOrgEventDetails ownerId: " + organizerId);
        ArrayList detList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            detList = objDAO.getAllOrgEventDetails(organizerId);
        }catch(Exception e){
            Debug.print("Exception in getAllOrgEventDetails:" + e);
        }
        return detList;
    }     
 public boolean isOrgEventDetExist(String eventId) throws RemoteException{
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(eventId!=null && eventId.trim().length()!=0 ){
                result = objDAO.isOrgEventDetExist(eventId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.isOrgEventDetExist()" + e.getMessage());
        }
        return result;
    }
 public String isOrgMainEventRegExist(String eventId) throws RemoteException{
        String result = "";
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(eventId!=null && eventId.trim().length()!=0 ){
                result = objDAO.isOrgMainEventRegExist(eventId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.isOrgMainEventRegExist()" + e.getMessage());
        }
        return result;
    }
  public HLCEndorsedFormVO getSingleEndorsedFormDetails(String eventId) throws RemoteException {
        HLCEndorsedFormVO endorseVO = new HLCEndorsedFormVO();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                endorseVO = objDAO.getSingleEndorsedFormDetails(eventId);
            }
        }catch(Exception e){
            e.printStackTrace();  
        }
        return endorseVO;
    }
  
  public String createPayment(HLCPaymentDetailsVO objPayDet) throws RemoteException{
        Debug.print("PaymentSessionBean createPayment");
        
        String oePaymentId = "";
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            //if(objPayDet.getUserId()!=null){
                oePaymentId = objDAO.insertRowPayment(objPayDet);
            //} else{
               // oePaymentId  = "";
            //}
            Debug.print("PaymentSessionBean Id:" + oePaymentId);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return oePaymentId;
    }
  
   public boolean insertEndorseRenDetails(HLCRenewalOrganizerDetails objRenewalDet, String paymentId) throws RemoteException{
        Debug.print("PaymentSessionBean insertEndorseRenDetails()");
        
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            result = objDAO.insertEndorseRenDetails(objRenewalDet, paymentId);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return result;
    }
    public boolean updateEndorseEventByAdmin(String eventId,String status) throws RemoteException{
        Debug.print("VaigaiSessionBean.updateEndorseEventByAdmin() :");
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(status!=null && status.trim().length()!=0 ){
                result = objDAO.updateEndorseEventByAdmin(eventId,status);
                
            }
        } catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.updateEndorseEventByAdmin()" + e.getMessage());
        }
        return result;
    }
    public boolean updatePayment(HLCPaymentDetailsVO objPayDet) throws RemoteException{
        Debug.print("VaigaiSessionBean.updatePayment() :");
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(objPayDet.getPaymentId()!=null && objPayDet.getPaymentId().trim().length()!=0 ){
                result = objDAO.updateRowPayment(objPayDet);
                
            }
        } catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.updatePayment()" + e.getMessage());
        }
        return result;
    }
    public boolean updatePaymentIdInHist(String eventId,int year,String paymentId) throws RemoteException{
        Debug.print("VaigaiSessionBean.updatePaymentIdInHist() :");
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(eventId!=null && eventId.trim().length()!=0 && year!=0){
                result = objDAO.updatePaymentIdInHist(eventId,year, paymentId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.updatePaymentIdInHist()" + e.getMessage());
        }
        return result;
    }
    
     public ArrayList getAllIssues() throws RemoteException { 
        Debug.print("VaigaiSessionBean getAllIssues");
        ArrayList objIssue= new ArrayList();
        try{
        HLCEventsDAO dao = new HLCEventsDAO();
        objIssue = dao.selectAllIssues();
        }
        catch(Exception e){
             Debug.print("Exception in VaigaiSessionBean.getAllIssues()" + e.getMessage());
        }
        return objIssue;    
     }
     
     public boolean insertEventHistoryDets(HLCEventRequestVO requestVO, int compyear, String appStatus) throws RemoteException{
         Debug.print("VaigaiSessionBean.insertEventHistoryDets() :");
         boolean result = false;        
         try{
                HLCEventsDAO dao = new HLCEventsDAO();
                result = dao.insertEventHistoryDets(requestVO, compyear, appStatus);

         }
         catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.insertEventHistoryDets()" + e.getMessage());
         }
         return result;
    }
     
      public boolean updateEventHistoryDets(HLCEventRequestVO requestVO, int comYr) throws RemoteException{
         Debug.print("VaigaiSessionBean.updateEventHistoryDets() :");
         boolean result = false;        
         try{
                HLCEventsDAO dao = new HLCEventsDAO();
                result = dao.updateEventHistoryDets(requestVO, comYr);

         }
         catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.updateEventHistoryDets()" + e.getMessage());
         }
         return result;
    }
/**********************************************For Total Amount in Endorsement Form************************************/
 public ArrayList getIssueIdBasedOnEventId(String eventId) throws RemoteException {
        Debug.print("KaverySessionBean getIssueIdBasedOnEventId ownerId: " +eventId);
        ArrayList detList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
            detList = objDAO.getIssueIdBasedOnEventId(eventId);
            }
        }catch(Exception e){
            Debug.print("Exception in getIssueIdBasedOnEventId:" + e);
        }
        return detList;
    }
 public boolean isEventRegDateValid(String seasonId,int compYear,String currentDate) throws RemoteException{
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(seasonId!=null && seasonId.trim().length()!=0 && compYear!=0){
                result = objDAO.isEventRegDateValid(seasonId,compYear,currentDate);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.isEventRegDateValid()" + e.getMessage());
        }
        return result;
    }
  public float getDueDatePrice(String issueId) throws RemoteException{
        float result = 0;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(issueId!=null && issueId.trim().length()!=0 ){
                result = objDAO.getDueDatePrice(issueId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.getDueDatePrice()" + e.getMessage());
        }
        return result;
    }
   public float getAfterDueDatePrice(String issueId) throws RemoteException{
        float result = 0;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(issueId!=null && issueId.trim().length()!=0 ){
                result = objDAO.getAfterDueDatePrice(issueId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.getAfterDueDatePrice()" + e.getMessage());
        }
        return result;
    }
   public boolean isIssueIdExists(String seasonId,int compYear) throws RemoteException{
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
            if(seasonId!=null && seasonId.trim().length()!=0 && compYear!=0){
                result = objDAO.isIssueIdExists(seasonId,compYear);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.isIssueIdExists()" + e.getMessage());
        }
        return result;
    }
   public ArrayList getPriceDetsForEndorse(String issueId, int compYr) throws RemoteException {
        Debug.print("VaigaiSessionBean getPriceDetsForEndorse issueId: " +issueId);
        ArrayList priceList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            if(issueId!=null && issueId.trim().length()!=0 && compYr!=0){
            priceList = objDAO.getPriceDetsForEndorse(issueId,compYr);
            }
        }catch(Exception e){
            Debug.print("Exception in getPriceDetsForEndorse:" + e);
        }
        return priceList;
    }
   
   //=================================For User Event Registration Details==========================
   
   public ArrayList getUserEveRegDetails(String userId,String status) throws RemoteException {
        Debug.print("VaigaiSessionBean getUserEveRegDetails ownerId: " + userId + " " +status);
        ArrayList userList  = new ArrayList();
        HLCEventsDAO objDAO = new HLCEventsDAO();
        try {
            userList = objDAO.getUserEveRegDetails(userId, status);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getUserEveRegDetails:" + e);
        }
        return userList;
    }     
  
 /*******************************************************************************************************************/
   
   public ArrayList getEventsConductedYearWise(int startYear,int endYear) throws RemoteException
   {
     Debug.print("VaigaiSessionBean getEventsConductedYearWise : ");
     ArrayList count=null;
     try {
            count = new HLCEventsDAO().getEventsConductedYearWise(startYear, endYear);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getUserEveRegDetails:" + e);
        }
        return count;
   }
   
   /*************************************************************************************************************************
    *
    *  
    */
   public ArrayList getEventsConductedAreaWise(java.util.Date startDate,java.util.Date endDate)
   {
      Debug.print("VaigaiSessionBean getEventsConductedAreaWise: ");
      ArrayList eventCount=null; 
      try {
            eventCount = new HLCEventsDAO().getEventsConductedAreaWise(startDate, endDate);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getEventsConductedAreaWise:" + e);
        }
        return eventCount;
   }
   public ArrayList getEventsConductedTypeWise(java.util.Date startDate,java.util.Date endDate)
   {
       Debug.print("VaigaiSessionBean getEventsConductedTypeWise: ");
      ArrayList eventCount=null; 
      try {
            eventCount = new HLCEventsDAO().getEventsConductedTypeWise(startDate, endDate);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getEventsConductedTypeWise:" + e);
        }
        return eventCount;
   }
   public ArrayList getEventsConductedAreaWiseSpecificEventType(java.util.Date startDate,java.util.Date endDate,String event_type_id) 
   {
       Debug.print("VaigaiSessionBean getEventsConductedAreaWiseSpecificEventTyp: ");
      ArrayList eventCount=null; 
      try {
            eventCount = new HLCEventsDAO().getEventsConductedAreaWiseSpecificEventType(startDate, endDate,event_type_id);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getEventsConductedAreaWiseSpecificEventTyp:" + e);
        }
        return eventCount; 
   }
   public Double getHumanMembersParticipatedInEvents(java.util.Date startDate,java.util.Date endDate)
   {
       Debug.print("VaigaiSessionBean getHumanMembersParticipatedInEvents: ");
      Double humanCount=null; 
      try {
            humanCount = new HLCEventsDAO().getHumanMembersParticipatedInEvents(startDate,endDate);
    }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getHumanMembersParticipatedInEvents" + e);
        }
        return humanCount;
   }
   public ArrayList getHumanMembersParticipatedForAllHumanMemberships(java.util.Date startDate,java.util.Date endDate)
   {
       Debug.print("VaigaiSessionBean getHumanMembersParticipatedForAllHumanMemberships: ");
      ArrayList humanCount=null; 
      try {
            humanCount = new HLCEventsDAO().getHumanMembersParticipatedForAllHumanMemberships(startDate,endDate);
    }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getHumanMembersParticipatedForAllHumanMemberships" + e);
        }
        return humanCount;
   }
   
   public ArrayList getHumanMembersParticipatedEventTypeWise(java.util.Date startDate,java.util.Date endDate)
   {
      Debug.print("VaigaiSessionBean getHumanMembersParticipatedEventTypeWise: ");
      ArrayList humanCount=null; 
      try {
            humanCount = new HLCEventsDAO().getHumanMembersParticipatedEventTypeWise(startDate,endDate);
    }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getHumanMembersParticipatedEventTypeWise" + e);
        }
        return humanCount;  
   }
   
   public ArrayList getHumanMemeberParticipatedAreaWise(java.util.Date startDate,java.util.Date endDate)
   {
        Debug.print("VaigaiSessionBean getHumanMemeberParticipatedAreaWise: ");
         ArrayList humanCount=null; 
      try {
            humanCount = new HLCEventsDAO().getHumanMemeberParticipatedAreaWise(startDate,endDate);
    }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getHumanMemeberParticipatedAreaWise" + e);
        }
        return humanCount;
   }
    public ArrayList getHumanMemberParticipatedMembeAreaWise(java.util.Date startDate,java.util.Date endDate,String area) 
   {
       Debug.print("VaigaiSessionBean getHumanMemberParticipatedMembeAreaWise: ");
      ArrayList eventCount=null; 
      try {
            eventCount = new HLCEventsDAO().getHumanMemberParticipatedMembeAreaWise(startDate, endDate,area);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getHumanMemberParticipatedMembeAreaWise:" + e);
        }
        return eventCount; 
   }
      public ArrayList getMemberParticipatedEventAreaWise(java.util.Date startDate,java.util.Date endDate,String eventType) 
   {
       Debug.print("VaigaiSessionBean getMemberParticipatedEventAreaWise: ");
      ArrayList eventCount=null; 
      try {
            eventCount = new HLCEventsDAO().getMemberParticipatedEventAreaWise(startDate, endDate,eventType);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getHumanMemberParticipatedEventAreaWise:" + e);
        }
        return eventCount; 
   }
      
  public ArrayList getAllEveSecreEventDetails(String userId) throws RemoteException {
        Debug.print("KaverySessionBean getAllEveSecreEventDetails userId: " + userId);
        ArrayList detList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            detList = objDAO.getAllEveSecreEventDetails(userId);
        }catch(Exception e){
            Debug.print("Exception in getAllEveSecreEventDetails:" + e);
        }
        return detList;
    }
  
  
  public ArrayList getChampionEveLevelDets(String eventId) throws RemoteException{
        ArrayList chmpEveLevelList = new ArrayList();
        try{
            chmpEveLevelList = new HLCEventsDAO().selectChampionEveLevelDets(eventId);
           
        }catch(Exception e){
            e.printStackTrace();
        }
        return chmpEveLevelList;
    }
   public Vector getAllMapTyLe() throws RemoteException{
        
        Vector vecObj = new Vector();
        try {
                makeConnection();
                System.out.println("Inside the Loop getAllMapTyLe");
                String typeId = "select event_type_id from tblMeeEventTypeMaster";
                PreparedStatement prepStmt1 = con.prepareStatement(typeId);
                ResultSet rs1 = prepStmt1.executeQuery();
                //System.out.println("Main TypeIds:" + typeId);
                //System.out.println("Event Id Inside the getAllMapTyLe  ");
                while (rs1.next()){
                    String eventID = rs1.getString(1);
                    
                     System.out.println("Event Id Inside the getAllMapTyLe  "+ typeId);
                    String selectStr = "select A.map_type_id,A.event_type_id,A.event_level_id, B.event_type_name, C.event_level_code, C.event_level_name "+
                    "from tblMeeMapEventLevel A, tblMeeEventTypeMaster B, tblMeeEventLevelMaster C  "+
                    "where A.event_type_id = B.event_type_id and A.event_level_id = C.event_level_id and A.event_type_id = ?" ;
                    
                    PreparedStatement prepStmt = con.prepareStatement(selectStr);
                     System.out.println("eventID:" + eventID);
                    prepStmt.setString(1,eventID);
                    ResultSet rs = prepStmt.executeQuery();
                    while (rs.next()){
                        String mapid = rs.getString(1);
                        //System.out.println("mapid:" + mapid);
                        String tyid = rs.getString(2);
                        //System.out.println("tyid:" + tyid);
                        String leid = rs.getString(3);
                        //System.out.println("leid:" + leid);
                        String tyname = rs.getString(4);
                        //System.out.println("tyname:" + tyname);
                        String levelcode = rs.getString(5);
                        //System.out.println("levelcode:" + levelcode);
                        String levelName = rs.getString(6);
                        //System.out.println("levelName:" + levelName);
                        String [] eventName = {mapid,tyid,leid,tyname,levelcode,levelName};
                        vecObj.add(eventName);
                    }
                    prepStmt.close();
                }
        
                prepStmt1.close();
                releaseConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return vecObj;
    } 
         
  public String getProvisionalId(String eventId) throws RemoteException{
         String provisionalId="";
         try {
                 Debug.print("eventId in getProvisionalId():" + eventId);
                makeConnection();
                System.out.println("Inside the Loop getProvisionalId");
                String selectStmt = "select pro_calendar_id from tblOEChampionshipDetails where event_id=?";
                PreparedStatement prepStmt1 = con.prepareStatement(selectStmt);
                prepStmt1.setString(1, eventId);
                ResultSet rs1 = prepStmt1.executeQuery();
                while (rs1.next()){
                    provisionalId = rs1.getString(1);
                }
             rs1.close();
            prepStmt1.close();
            releaseConnection();
            Debug.print("getProvisionalId():" + provisionalId);
        } catch(SQLException sql){
            
            Debug.print("SQL Exception in getProvisionalId():" + sql.getMessage());
        } catch(Exception e){
            
            Debug.print("General Exception  in getProvisionalId():" + e.getMessage());
        }
        return provisionalId;
           
     }
  
   public ArrayList getAllEveTypeDets() throws RemoteException { 
        Debug.print("VaigaiSessionBean getAllEveTypeDets");
        ArrayList objTypes= new ArrayList();
        try{
        HLCEventsDAO dao = new HLCEventsDAO();
        objTypes = dao.selectAllEveTypes();
        }
        catch(Exception e){
             Debug.print("Exception in VaigaiSessionBean.getAllEveTypeDets()" + e.getMessage());
        }
        return objTypes;    
     }
   
   public ArrayList getEvePriceDets(String issueId) throws RemoteException {
        Debug.print("VaigaiSessionBean getEvePriceDets");
        ArrayList evePriceList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            evePriceList = objDAO.getEvePriceDets(issueId);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getEvePriceDets:" + e);
        }
        return evePriceList;
    }     
   
  public float getTotalPay(String eveId) throws RemoteException{
         //boolean result = false;
         float pay=0;
          HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
         Debug.print("VaigaiSessionBean.getTotalPay():");
         try{
             pay = objDAO.getTotalPay(eveId);
         }catch(Exception e){
             e.printStackTrace();
         }
         return pay;
     }
  
  public String getOldEventId(String eventId) throws RemoteException{
         String oldEveId=null;
         try {
                 Debug.print("eventId in getOldEventId():" + eventId);
                makeConnection();
                System.out.println("Inside the Loop getProvisionalId");
                String selectStmt = "SELECT old_event_id FROM tblOEProvisionalCalendar WHERE event_id= ? ";
                PreparedStatement prepStmt1 = con.prepareStatement(selectStmt);
                prepStmt1.setString(1, eventId);
                ResultSet rs1 = prepStmt1.executeQuery();
                while (rs1.next()){
                    oldEveId = rs1.getString(1);
                }
             rs1.close();
            prepStmt1.close();
            releaseConnection();
            Debug.print("getOldEventId():" + oldEveId);
        } catch(SQLException sql){
            
            Debug.print("SQL Exception in getProvisionalId():" + sql.getMessage());
        } catch(Exception e){
            
            Debug.print("General Exception  in getProvisionalId():" + e.getMessage());
        }
        return oldEveId;
           
     }
  public ArrayList getOmniComments(String eveId) throws RemoteException {
        Debug.print("VaigaiSessionBean getOmniComments");
        ArrayList commtsList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            commtsList = objDAO.selectOmniComments(eveId);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getOmniComments:" + e);
        }
        return commtsList;
    }   
  
  
   public boolean editEveTitle(String eventId, String eveTitle) throws RemoteException {
        Debug.print("VaigaiSessionBean editEveTitle");
        boolean result  = false;
        HLCEventsDAO objDAO = new HLCEventsDAO();
        try {
            result = objDAO.editEveTitle(eventId, eveTitle);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.editEveTitle:" + e);
        }
        return result;
    } 
   
    public boolean editEveTitleInMee(String eventId, String eveTitle) throws RemoteException {
        Debug.print("VaigaiSessionBean editEveTitleInMee:"+eveTitle);
        boolean result  = false;
        HLCEventsDAO objDAO = new HLCEventsDAO();
        try {
            result = objDAO.editEveTitleInMee(eventId, eveTitle);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.editEveTitleInMee:" + e);
        }
        return result;
    } 
  
  public boolean updateOmniCommts(HLCEventDetailsVO objEventDet) throws RemoteException{
        boolean result = false;
        try{
            HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();          
                result = objDAO.updateOmniCommts(objEventDet);
                       
        } catch(Exception e){
            Debug.print("Exception in KaverySessionBean.updateOmniCommts()" + e.getMessage());
        }
        return result;
    }
  public String getPaymtId() throws RemoteException {
        String pymtId = null;
        try {
            pymtId = new HLCCRUDMasterDAO().getNextId();
            Debug.print("Payment Id in the Vaigai session bean is : "+pymtId);
        }catch (Exception e){
            Debug.print("Exception in setting paymentID in getPaymtId:" + e);
        }
        return pymtId;
    } 
  public ArrayList getCompetitionMgtDetails(String eventId) throws RemoteException {
        Debug.print("VaigaiSessionBean getCompetitionMgtDetails");
        ArrayList compMgtList  = new ArrayList();
        HLCCRUDMasterDAO objDAO = new HLCCRUDMasterDAO();
        try {
            compMgtList = objDAO.selectCompetitionMgtDetails(eventId);
        }catch(Exception e){
            Debug.print("Exception in VaigaiSessionBean.getCompetitionMgtDetails:" + e);
        }
        return compMgtList;
    }    
  
}
