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
package com.hlcsessionbean.qualificationmatrix;

import com.hlcutil.HLCCalendarVO;
import com.hlcutil.HLCCompRegistrationVO;
import com.hlcutil.HLCCompResultVO;
import com.hlcutil.HLCCompetitionResultVO;
import com.hlcutil.HLCMemberVO;
import com.hlcutil.HLCPaymentDetailVO;
import com.hlcutil.HLCPriceMatrixVO;
import javax.ejb.EJBObject;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.*;
import java.util.ArrayList;



/**
 * This is the remote interface for MembershipQualificationMatrix enterprise bean.
 */
public interface HLCMembershipQualificationMatrixRemote extends EJBObject, HLCMembershipQualificationMatrixRemoteBusiness {
    
    public HLCMemberVO getMemberDetails(String memberId) throws RemoteException;
    public ArrayList getHorseDetails(String userId) throws RemoteException;
    public boolean updateHorseDetails(String horseMemberId,String userId) throws RemoteException;
    public String getUserIdBasedOnuserName(String userName) throws RemoteException;
    public String insertUserDetails(String loginName, String firstName, String lastName, String emailId, String contactId)  throws RemoteException;
    public boolean insertContactDetails(String userId,String address,String country,String state,String city, String zipcode,String phone,String fax,String contactId)  throws RemoteException;
    public boolean insertHorseRelationDetails(String horseMemberId,String userId,String trainerId) throws RemoteException;
    public String getRiderId(String horseMemberId) throws RemoteException;
    public ArrayList getEventDetailsBasedOnEventId(String eventId,String eventTypeID) throws RemoteException;
    public ArrayList getEventDetailsByEventId(String eventId) throws RemoteException;
    public ArrayList getEventDetailsBasedOnChamp(String eventId,String eventTypeID) throws RemoteException;
    public ArrayList getEventDetailsByChamp(String eventId) throws RemoteException;
    public ArrayList getAllItemNamesForOrg() throws RemoteException;
    public boolean isTrainerDetailsExist(String horseMemberId) throws RemoteException;
    public ArrayList getAllHorseDetailsByOwnerId(String ownerId) throws RemoteException;
    public boolean insertPriceItem(String itemName,String orgStatus) throws RemoteException;
    public String insertNewPriceItem(String itemName,String orgStatus) throws RemoteException;
    public ArrayList getAllPriceItems(String result) throws RemoteException;
    public boolean updatePriceItemstatus(String itemId,String status) throws RemoteException;
    public ArrayList getSingleItemDetails(String itemId) throws RemoteException;
    public boolean updatePriceItem(String itemId,String itemName,String orgStatus) throws RemoteException;
    public boolean isItemNameExist(String itemName) throws RemoteException;
    public boolean isEditItemNameExist(String itemId,String itemName) throws RemoteException;
    public ArrayList getEventLevelDetails(String eventId) throws RemoteException;
    public ArrayList getEventTypeDetails(String eventId) throws RemoteException;
    public ArrayList getEventTypeDetailsForChamp(String eventId) throws RemoteException;
    public ArrayList getEventTypeDetWithoutChamp(String eventId) throws RemoteException;
    public boolean insertPriceMatrixDetails(HLCPriceMatrixVO objPrice) throws RemoteException;
    public ArrayList getPriceMatrixDetails(String eventId, String itemId) throws RemoteException;
    public ArrayList getPriceMatrixDetailsForChamp(String eventId, String itemId) throws RemoteException;
    public ArrayList getEventTypeDetailsByItemId(String eventId,String itemId) throws RemoteException;
    public ArrayList getEventTypeDetByItemIdForChamp(String eventId,String itemId) throws RemoteException;
    public boolean updatePriceMatrixDetails(HLCPriceMatrixVO objPrice) throws RemoteException;
    public boolean isPriceMatrixExist(String eventId, String itemId, String eventTypeId, String eventLevelId) throws RemoteException;
    public ArrayList getEventTiles(String year,String organizerId) throws RemoteException;
    public ArrayList getHorseMemberValidationDetails(String horseId, String divisionId) throws RemoteException;
    public ArrayList getQualificationDetails(String eventTypeId, String eventLevelId, String champStatus, String userTypeId, String divisionId) throws RemoteException;
    public ArrayList getRiderDetails(String riderId, String divisionId) throws RemoteException;
    //public ArrayList getValidEventDetails(int jumpPenalties, int timePenalties, int finalPoints, String priority, String userTypeId, String divisionId, String chStatus) throws RemoteException;
    public int getRecordCountForHorse(String qualificationPeriod, String dateToValidate, String horseMemberId) throws RemoteException;
    public int getRecordCountForRider(String qualificationPeriod, String dateToValidate, String riderId) throws RemoteException;
    public ArrayList getOrgCompRegList(int year, String eventId,String status, String organizerId) throws RemoteException;
    public ArrayList getAllItemNamesForStaff() throws RemoteException;
    public boolean insertFixedPriceMatrix(HLCPriceMatrixVO objPrice) throws RemoteException;
    public ArrayList getFixedPriceMatrixDetails(String itemId, String eventTypeId) throws RemoteException;
    public ArrayList getFixedPriceMatrixForChamp(String areaId) throws RemoteException;
    public boolean updateFixedPriceMatrixDetails(HLCPriceMatrixVO objPrice) throws RemoteException;
    public HLCCompRegistrationVO getSingleCompRegDetails(String registrationId) throws RemoteException;
    public boolean approveSingleCompRegDetails(String registrationId, String qualifyStatus, String comments) throws RemoteException;
    public boolean insertCompResultUploadDetails(String event_id,String event_name,String result_file_path,String eventTypeId) throws RemoteException;
    public String getNextIdforPayment() throws RemoteException;
    public String createPayment(HLCPaymentDetailVO objPayDet) throws RemoteException;
    public boolean insertEventRegistrationDetails(HLCCompRegistrationVO compVO, String oePaymentId) throws RemoteException;
    public ArrayList getHorseDetailsByHorseId(String horseMemberId) throws RemoteException;
    public ArrayList getFixedAmount(String evTypeId, String evLevelId, String chStatus) throws RemoteException;
    public ArrayList getorganizerPriceDetails(String eventId, String chStatus, String eventLevelId, String eventTypeId) throws RemoteException;
    public ArrayList getMyCompRegList(int year,String riderUserId) throws RemoteException;
    public HLCCompRegistrationVO getMySingleCompRegDetails(String registrationId) throws RemoteException;
    public boolean isFixedPriceMatrixExist(String itemId, String eventTypeId, String eventLevelId, String areaId) throws RemoteException;
    public ArrayList getEventLevelDetailsWithChStatus(String eventId, String compYear) throws RemoteException;
    public HLCCalendarVO selectEventDetails(String eventId, String compYear) throws RemoteException;
    public String getEventLevelName(String eventLevelId) throws RemoteException;
    public String getEventTypeName(String eventTypeId) throws RemoteException;
    public ArrayList getEventTypes(String eventId) throws RemoteException;
    public ArrayList getOrgCompResLabelList(String eventTypeId) throws RemoteException;
    public ArrayList getOrgCompResList(int year, String eventId, String eventTypeId, String organizerId) throws RemoteException;
    public boolean insertCompResultDetails(HLCCompetitionResultVO compResVO) throws RemoteException;
    public String getEventLevelId(String eventLevelName) throws RemoteException;
    public String getEventDivId(String divisionName) throws RemoteException;
    public boolean isCompResultExist(String eventId, String eventTypeId, String eventLevelId) throws RemoteException;
    public boolean checkHorseAvailability(String eventId, String horseMemberId, String compYear) throws RemoteException;
    public ArrayList getEventLevelDetWithoutChamp(String eventId) throws RemoteException;
    public ArrayList getEventLevelDetWithChamp(String eventId) throws RemoteException;
    public ArrayList getMyEventTiles(int year,String riderUserId) throws RemoteException;
    public String getEventTypeId(String eventTypeName) throws RemoteException;
    public ArrayList getMyCompResultList(String riderUserId, String eventId, int year) throws RemoteException;
    public HLCCompResultVO getMyCompResultView(String compResultId) throws RemoteException;
    public ArrayList getAllEventTiles(int year) throws RemoteException;
    public ArrayList getCompResultList(String eventId, int year) throws RemoteException;
    public boolean isCompResExist(int year, String eventId, String horseMemberId, String riderUserId) throws RemoteException;
    public String getTrainerId() throws RemoteException;
    public String getContactTypeId() throws RemoteException;
    public String getOwnerId(String horseMemberId) throws RemoteException;
     public String getUserNameBasedOnuserId(String userId) throws RemoteException;
}
