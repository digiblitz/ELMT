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

import com.hlcmro.util.HLCAreaChairsVO;
import com.hlcmro.util.HLCEndorsedFormVO;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCEventRequestVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCPaymentDetailsVO;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import javax.ejb.EJBObject;
import javax.ejb.*;
import java.util.*;
import java.rmi.*;



/**
 * This is the remote interface for VaigaiSession enterprise bean.
 */
public interface HLCVaigaiSessionRemote extends EJBObject, HLCVaigaiSessionRemoteBusiness {
    public String createEvent(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
    Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
    HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException;
    public boolean updateMainEventRegDetails(HLCEventDetailsVO objEventDet) throws RemoteException;
    public void deleteEvent(String eventId) throws RemoteException,FinderException;
    public HLCEventDetailsVO getEventDetails(String eventId) throws RemoteException;
    public ArrayList getRenewalDetailByStatus(String status) throws RemoteException, FinderException;
    public ArrayList getRenewalDetailByOraganizer(String organizerId) throws RemoteException, FinderException;
    public ArrayList getDetailedEvents(String eventId) throws RemoteException;
    public ArrayList getAllEventDetails() throws RemoteException;
    public ArrayList getAllOrganizerEventDetails(String organizerId) throws RemoteException;
    public boolean changeEventStatus(String eventId, String statusId, String comments) throws RemoteException;
    public ArrayList getAllEventDetailsByRequestStatus(String requestStatus) throws RemoteException;
    
    //Renewal Endorse Form
    public void addRenewal(HLCRenewalOrganizerDetails objRenewalDet,HLCPaymentDetails objPayDet) throws RemoteException;  
    public boolean editRenewal(HLCRenewalOrganizerDetails objRenewalDet) throws RemoteException;
    public HLCRenewalOrganizerDetails getRenewalDetails(String eventId) throws RemoteException; 
    public ArrayList getRenewalDetailByEventId(String eventId) throws RemoteException,FinderException;
    public ArrayList searchByCompitionName(String cmpName) throws RemoteException,FinderException;
    public ArrayList getAllAreaMasters() throws RemoteException;
    public ArrayList getAllAreaChairs() throws RemoteException; 
    public ArrayList getAllStates() throws RemoteException;
    
    // Hari Added for retrieving Zip code range depending on the area ID
    public ArrayList getAreaStateMasterOnID(String areaID) throws RemoteException;
    public ArrayList getZipcodeFromToOnStateId(String stateID,String zipValue) throws RemoteException;
    public String insertEventRequest(HLCEventRequestVO requestVO, String userId) throws RemoteException;
    public boolean generateMappingAreaChairArea (String areaId, String  areaChairId) throws RemoteException;
    public boolean generateMappingAreaStateZip (String areaId, String  stateId, String zipCodeFrom, String zipCodeTo) throws RemoteException;
    public ArrayList getAllAreaAndAreaChairs() throws RemoteException;
    public HLCAreaChairsVO getAreaAndAreaChairsByAreaMapId(String mapAreaId) throws RemoteException;
    public boolean editMappingAreaChairArea (String mapAreaId, String areaId, String  areaChairId) throws RemoteException;
    public int stateCount (String areaId) throws RemoteException;
    public String[] getStateByAreaId(String areaId) throws RemoteException;
    
    //Ganapathy addedd Methods for Event Registration Listing 
    public ArrayList getAreaChairDetails(String status,int year ,String memberId) throws RemoteException;
    public String getAreaChairMemberId(String areaId) throws RemoteException;
    public HLCEventRequestVO getEventRequestDetails(String eventId) throws RemoteException;
    public String updateEventRequest(HLCEventRequestVO eventReqVO) throws RemoteException;
    public boolean changeEventRequestStatus(String eventId,String approveStatus) throws RemoteException;
    
    /*=======================Edit Event Organizer ========================================*/
    public boolean editEventOrganizer(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
    Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
    HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException, FinderException;
    public void editTentativeTime(String eventId, Vector tentativeTime) throws RemoteException, FinderException;
    public boolean editCrossCountry(String eventId, Vector crossCountry) throws RemoteException, FinderException;
    public boolean editHorseDressage(String eventId, Vector horseDressage) throws RemoteException, FinderException;
    public boolean editJudgeDetails(String eventId, Vector judgeDetail) throws RemoteException, FinderException;
    public boolean editDivisionLevel(String eventId, Vector divLevel) throws RemoteException, FinderException;
    public boolean editAccomodation(String eventId, Vector accomodation) throws RemoteException, FinderException;
    public boolean editRefundRuleDetails(String eventId, Vector refundDetail) throws RemoteException, FinderException;
    public boolean editCogginsDetail(Vector coggingsDet) throws RemoteException, FinderException;
    public boolean editOtherDetails(HLCOtherDetailsVO otherDet) throws RemoteException, FinderException;
    public void editEvent(HLCEventDetailsVO objEventDet) throws RemoteException,FinderException;

    //Harmohan Nath Sharma Added From Here
    /* =========================CRUD Operation for Event Organizer=========================================*/

    /*=================Mapping Dressage Test for horse Trials======================*/
    public boolean mapDressageTest(String eventLevelId, String eventSubLevelId) throws RemoteException;
    /*==========================Dressage Test============================*/
    public boolean insertHorseDressageTrial(String eventSubLevelName) throws RemoteException;
    public boolean updateHorseDressageTrial(String eventSubLevelName, String eventSubLevelId) throws RemoteException;
    public boolean deleteHorseDressageTrial(String eventSubLevelId) throws RemoteException;
    public boolean activateHorseDressageTrial(String eventSubLevelId) throws RemoteException;
    public Vector displayHorseDressageTrial(String eventSubLevelId) throws RemoteException;
    public Vector displayAllHorseDressageTrial(boolean status) throws RemoteException;
    
    /*==========================CRUD for Arena Size==================================*/
    public boolean insertArenaSize(String arenaSizeName) throws RemoteException;
    public boolean updateArenaSize(String arenaSizeName, String arenaSizeId) throws RemoteException;
    public boolean deleteArenaSize(String arenaSizeId) throws RemoteException;
    public boolean activateArenaSize(String arenaSizeId) throws RemoteException;
    public Vector displayArenaSize(String arenaSizeId) throws RemoteException;
    public Vector displayAllArenaSize(boolean status) throws RemoteException;
    
    /*==========================CRUD for Officials==================================*/
    public boolean insertOficial(String judgeTypeName) throws RemoteException;
    public boolean updateOfficial(String judgeTypeName, String judgetypeId) throws RemoteException;
    public boolean deleteOfficial(String judgeTypeId) throws RemoteException;
    public boolean activateJudgeType(String judgeTypeId) throws RemoteException;
    public Vector displayJudgeType(String judgetypeId) throws RemoteException;
    public Vector displayAllJudgetype(boolean status) throws RemoteException;
    
    /*==========================CRUD for REFUNDS==================================*/
    public boolean mapRefundRule(String refundRuleTypeId, String refundRuleSubTypeId) throws RemoteException;
    public boolean insertRefundRuleMaster(String refundRuleName) throws RemoteException;
    public boolean updateRefundRuleMaster(String refundRuleTypeName, String RefundRuleTypeId) throws RemoteException;
    public boolean deleteRefundRuleMaster(String refundRuleTypeId) throws RemoteException;
    public boolean activateRefundRuleType(String refundRuleTypeId) throws RemoteException;
    public Vector displayRefundRule(String refundruleTypeId) throws RemoteException;
    public Vector displayAllRefundRule(boolean status) throws RemoteException;

    /*==========================CRUD for REFUNDS SUB TYPE MASTER==================================*/
    public boolean insertRefundRuleSubTypeMaster(String refundRuleSubTypeName, boolean status) throws RemoteException;
    public boolean updateRefundRuleSubMaster(String refundRuleSubTypeName, String RefundRuleSubTypeId, boolean boxStatus) throws RemoteException;
    public boolean deleteRefundRuleSubMaster(String refundRuleSubTypeId) throws RemoteException;
    public boolean activateRefundRuleSubType(String refundRulesubTypeId) throws RemoteException;
    public Vector displayRefundRuleSubType(String refundruleSubTypeId) throws RemoteException;
    public Vector displayAllRefundRuleSubType(boolean status) throws RemoteException;

    /*==========================CRUD for EVENT TYPE MASTER==================================*/
    public boolean insertEventTypeMaster(String eventTypeName) throws RemoteException;
    public boolean updateEventTypeMaster(String eventTypeName, String eventTypeId) throws RemoteException;
    public boolean deleteEventTypeMaster(String eventTypeId) throws RemoteException;
    public boolean activateEventTypeMaster(String eventTypeId) throws RemoteException;
    public Vector displayEventTypeMaster(String eventTypeId) throws RemoteException;
    public Vector displayAllEventTypeMaster(boolean status) throws RemoteException;

    /*==========================CRUD for EVENT DIVSION MASTER==================================*/
    public boolean CreateEventDivision(String divisionName) throws RemoteException;
    public boolean updateEventDivision(String divisionName, String divisionId) throws RemoteException;
    public boolean deleteEventDivision(String divisionId) throws RemoteException;
    public boolean activateEventDivision(String divisionId) throws RemoteException;
    public Vector listEventDivision(String divisionId) throws RemoteException;
    public Vector listAllEventDivision(boolean status) throws RemoteException;
    public Vector getAllEventDivision() throws RemoteException;
    
    /*==========================CRUD for EVENT LEVEL MASTER==================================*/
    public boolean insertEventLevelMaster(String eventLevelName, String eventLevelCode, String jumpingEffort) throws RemoteException;
    public boolean updateEventLevelMaster(String eventLevelName, String eventLevelCode, String jumpingEfforts,String eventLevelId) throws RemoteException;
    public boolean deleteEventLevelMaster(String eventLevelId) throws RemoteException;
    public boolean activateEventLevelMaster(String eventLevelId) throws RemoteException;
    public Vector displayEventLevelMaster(String eventLevelId) throws RemoteException;
    public Vector displayAllEventLevelMaster(boolean status) throws RemoteException;

    /*======================================For Event Calendar==============================*/
    public boolean eventDateAvailability(String compDate)throws RemoteException, FinderException;
    public String createEventCalendar(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
    Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
    HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException;
    public boolean editEventCalendar(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
    Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
    HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws RemoteException, FinderException;
    public boolean generateCalendarBasedOnYear(String fromYear, String forYear) throws RemoteException;
    public List getCurrentYearCalendar(String areaName) throws RemoteException;     
    /**************************************For Organizer Event List==========================================*/        
    public ArrayList getAllOrgEventDetails(String organizerId) throws RemoteException;  
    public boolean isOrgEventDetExist(String eventId) throws RemoteException;
    public String isOrgMainEventRegExist(String eventId) throws RemoteException;
    public HLCEndorsedFormVO getSingleEndorsedFormDetails(String eventId) throws RemoteException;
    public String createPayment(HLCPaymentDetailsVO objPayDet) throws RemoteException;  
    public boolean insertEndorseRenDetails(HLCRenewalOrganizerDetails objRenewalDet, String paymentId) throws RemoteException;
    public boolean updateEndorseEventByAdmin(String eventId,String status) throws RemoteException;
    public boolean updatePaymentIdInHist(String eventId,int year,String paymentId) throws RemoteException;
    public boolean updatePayment(HLCPaymentDetailsVO objPayDet) throws RemoteException;
    /**********************************************New Event Registration************************************/
    
    public ArrayList getAllIssues() throws RemoteException;
    public boolean insertEventHistoryDets(HLCEventRequestVO requestVO, int compyear, String appStatus) throws RemoteException;
    public boolean updateEventHistoryDets(HLCEventRequestVO requestVO, int comYr) throws RemoteException;
    
    /**********************************************For Total Amount in Endorsement Form************************************/
    public ArrayList getIssueIdBasedOnEventId(String eventId) throws RemoteException; 
    public boolean isEventRegDateValid(String seasonId,int compYear,String currentDate) throws RemoteException;
    public float getDueDatePrice(String issueId) throws RemoteException;
    public float getAfterDueDatePrice(String issueId) throws RemoteException;
    public boolean isIssueIdExists(String seasonId,int compYear) throws RemoteException;
    public ArrayList getPriceDetsForEndorse(String issueId, int compYr) throws RemoteException;
    
     /*******************************************New Registration List Form for User******************************/
    
    public ArrayList getUserEveRegDetails(String userId, String status) throws RemoteException; 
    public ArrayList getEventsConductedYearWise(int startYear,int endYear) throws RemoteException;
    public ArrayList getEventsConductedAreaWise(java.util.Date startDate,java.util.Date endDate) throws RemoteException;
    public ArrayList getEventsConductedTypeWise(java.util.Date startDate,java.util.Date endDate) throws RemoteException;
    public ArrayList getEventsConductedAreaWiseSpecificEventType(java.util.Date startDate,java.util.Date endDate,String event_type_id) throws RemoteException;
    public Double getHumanMembersParticipatedInEvents(java.util.Date startDate,java.util.Date endDate) throws RemoteException;
    public ArrayList getHumanMembersParticipatedForAllHumanMemberships(java.util.Date startDate,java.util.Date endDate) throws RemoteException;
    public ArrayList getHumanMembersParticipatedEventTypeWise(java.util.Date startDate,java.util.Date endDate) throws RemoteException;
    public ArrayList getHumanMemeberParticipatedAreaWise(java.util.Date startDate,java.util.Date endDate) throws RemoteException;
    public ArrayList getHumanMemberParticipatedMembeAreaWise(java.util.Date startDate,java.util.Date endDate, String area)throws RemoteException;
    public ArrayList getMemberParticipatedEventAreaWise(java.util.Date startDate,java.util.Date endDate, String eventType)throws RemoteException;
    
    
     public ArrayList getAllEveSecreEventDetails(String userId) throws RemoteException;
     public ArrayList getChampionEveLevelDets(String eventId) throws RemoteException;
     public Vector getAllMapTyLe() throws RemoteException;
     public String getProvisionalId(String eventId) throws RemoteException;
     public ArrayList getAllEveTypeDets() throws RemoteException;
     public ArrayList getEvePriceDets(String issueId) throws RemoteException;
     public float getTotalPay(String eventId) throws RemoteException;
     public String getOldEventId(String eventId) throws RemoteException;
     public ArrayList getOmniComments(String eventId) throws RemoteException;
      public boolean editEveTitle(String eventId, String EveTitle) throws RemoteException;
     public boolean editEveTitleInMee(String eventId, String EveTitle) throws RemoteException;
      public boolean updateOmniCommts(HLCEventDetailsVO objEventDet)throws RemoteException;
       public String getPaymtId() throws RemoteException;
    public ArrayList getCompetitionMgtDetails(String eventId) throws RemoteException;
}