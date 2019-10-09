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
package com.hlcsessionbean.krishna;

import javax.ejb.EJBObject;
import java.sql.SQLException;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.*;

import com.hlcutil.*;
import java.util.ArrayList;


/**
 * This is the remote interface for KrishnaStateless enterprise bean.
 */
public interface HLCKrishnaStatelessRemote extends EJBObject, HLCKrishnaStatelessRemoteBusiness {
/************************************************Provisional Calendar*******************************************************************************/    
    public boolean calenderDetailsExist(String calenderYear) throws RemoteException;
    public ArrayList getEventDetails(int presentYear) throws RemoteException;
    public boolean insertCalenderDetails(String newEventId,String organizerId,String eventTitle,Date beginDat,Date endDat,
    String areaId, String stateId,int compYear,String eventTypeId,String eventLevelName,String oldEventId) throws RemoteException;
    public ArrayList getEventDetailsBasedOnEventId(String eventId) throws RemoteException;
    public int getMaxEventId() throws RemoteException;
    public boolean insertChampionshipDetails(String provCalId,String eventId,
    String eventTypeId,String eventLevelId) throws RemoteException;
    public String getProvCalId(String eventId, int year) throws RemoteException; 
    public ArrayList getOrganizerList(int year, String acStatus, String organizerId) throws RemoteException;
    public HLCCalendarVO getSingleEventDetails(String provisionalId) throws RemoteException;
    public HLCCalendarVO getSingleEventDetailsByEventId(String eventId) throws RemoteException;
    public boolean approveSingleEventDetails(String provisionalId, String orgStatus, String orgComments) throws RemoteException;
    public ArrayList getAreaChairList(int year, String acStatus, String acId) throws RemoteException;
    public boolean approveAreaChairDetails(String provisionalId, String acStatus, String acComments) throws RemoteException;
    public ArrayList getAllEventLevels() throws RemoteException;
    public ArrayList getSelectedEventLevels(String calendarId) throws RemoteException;
    public boolean updateChampStatus(String eventLevelId,String eventTypeId,String provisionalId) throws RemoteException;
    public boolean updateEventDetByOrg(HLCCalendarVO calVO) throws RemoteException;
    public boolean updateEventDetByAreaChair(HLCCalendarVO calVO) throws RemoteException;
    public boolean updateEventDetByStaff(HLCCalendarVO calVO) throws RemoteException;
    public ArrayList getAllAreaMasters() throws RemoteException;
    public ArrayList getAllStateMasters() throws RemoteException;
    public Vector getAllMapTyLe() throws RemoteException;
    public boolean updateChampionshipDetails(ArrayList champList) throws RemoteException;
    public ArrayList getUseaStaffList(int year, String areaId) throws RemoteException;
    public ArrayList searchEventDetailsList(String eventId, String eventTitle, String areaId) throws RemoteException;
    public ArrayList searchEventDetailsListAdmin(String eventId, String eventTitle, String areaId, int compYear) throws RemoteException;
    public boolean approveEventDetailsByUStaff(String provisionalId, String uStaffStatus, String uStaffComments) throws RemoteException;
    public ArrayList getFinalizedEventCalDetails(String areaId) throws RemoteException;
    public ArrayList getAllIssues() throws RemoteException;
/***************************************************************************************************************************************************/ 
/**********************************************For Event Entries************************************************************************************/
    public Vector getAllEventTypes() throws RemoteException;
    public ArrayList getUserTypes() throws RemoteException;
    public ArrayList getDivisions() throws RemoteException;
    public ArrayList getMembershipTypes(String userTypeId) throws RemoteException;
    public ArrayList getEventLevelsForArea(String areaId) throws RemoteException;
    public ArrayList getEventLevelsBasedOnEventId(String eventId) throws RemoteException;
    public boolean ValidationDetailsExist(String eventTypeId, String userTypeId, String divisionId, String areaId, String chmpStatus) throws RemoteException;
    public boolean insertValidationDetails(HLCValidationVO objValidation) throws RemoteException;
    public ArrayList getValidationDetails(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws RemoteException;
    public boolean updateValidationDetails(HLCValidationVO validVO) throws RemoteException;
    public ArrayList getValidationDetsForEdit(String eventTypId, String usrTypId, String divisionId, String areaId, String chmpStatus) throws RemoteException;
    public boolean getMapDetailsExists(String areaId, String eventLevelId) throws RemoteException;  
    public boolean mappingAreaWithEventLevel(String areaId, ArrayList eventLevelList) throws RemoteException;      
    public ArrayList getEventLevelsBasedOnAreaId(String areaId) throws RemoteException;
    //public boolean updateAreaEveLevelMapping(String areaId, ArrayList eventLevelList) throws RemoteException;
    
    /*public String insertCalenderDetails(String eventId,String organizerId,String eventTitle,Date beginDat,Date endDat,
    String areaId, String stateId,int compYear,String eventTypeId,String eventLevelName) throws RemoteException;
    public String insertChampionshipDetails(String provCalId,String eventId,
    String eventTypeId,String eventLevelId) throws RemoteException;*/
    public ArrayList getProvCalIdBasedOnYear(String year) throws RemoteException;
    public boolean deleteCalendarDetails(String calendarId) throws RemoteException;
     public boolean updateChampDetails(String provisionalId,String eventId,String eventType,String eventLevel,String champStatus,String divisionId) throws RemoteException;
    public boolean deleteChampDetails(String eventId) throws RemoteException;
    public ArrayList getMappedEventLavelBasedOnEventType(String eventId) throws RemoteException;
/**********************************Update Event Details with Approved Provisional Calendar Events*******************************************************************************/     
public ArrayList getEventDetsApprovedByAll(String provisionalId, String eventId) throws RemoteException; 
public boolean insertProCalEveIntoEveDetails(HLCEventRequestVO objEventDetails) throws RemoteException; 
public ArrayList getOldEventDetails(String oldEventId) throws RemoteException;
public ArrayList getProvEventDetails(String EventId) throws RemoteException;
public boolean updateNewEveDets(String orgId,String eveTitle, Date eveBegDt, Date eveEndDt,Date oeBegDt,Date oeEndDt,Date oeExDueDt,String state,String area,String EventId) throws RemoteException;
public boolean updateOmnibusDetsInAllTables(String eventId, String oldEventId) throws RemoteException;
public boolean eveDetailsExistsInEveTable(String newEventId) throws RemoteException;
public ArrayList getOldEventHistoryDetails(String oldEventId) throws RemoteException;
public boolean insertProCalEveIntoEveHistoryDetails(HLCEventRequestVO objEventDetails) throws RemoteException; 
public String getZipCodeDetail(String memberId) throws RemoteException;
public String getAreaDetail(String zipCode) throws RemoteException;
public ArrayList getEventDivision() throws RemoteException;
public ArrayList getselectDivisionDetails(String provisionalId) throws RemoteException;
public String getEventTypeName(String eventTypeId) throws RemoteException;

public ArrayList getApprovedEventDet(String provisionalId, String eventId) throws RemoteException; 
public ArrayList getEveTyLevIdDet(String provisionalId, String eventId) throws RemoteException; 
public boolean insertEveDetails(HLCEventRequestVO objEventDetails) throws RemoteException; 
public String getEveTypLevelMapId(String eventTypeId, String eventLevelId) throws RemoteException; 
public boolean insertMapDets(String eventId,String mapTypId) throws RemoteException; 
public boolean getMapDets(String eventId,String mapTypId) throws RemoteException;
public ArrayList getChampionEveLevelDets(String eventId) throws RemoteException;
public boolean deleteEveTypeDets(String eventId) throws RemoteException;
public boolean updateEveDetsInMeeEventTable(HLCCalendarVO calVO) throws RemoteException;

public String getEveSecretaryId(String eventId) throws RemoteException;
public String getUserName(String eveSecreId) throws RemoteException;
public String getEveSecretaryIdBasedOnMemId(String membId) throws RemoteException;
public String getEveSecretaryIdBasedOnUsr(String usrName) throws RemoteException;
public boolean insertEveSecreRelationDets(String eveOrgId, String eveSecreId,String eventId) throws RemoteException;
public boolean updateEveDetsInMeeEventTableByAdmin(HLCCalendarVO calVO) throws RemoteException;

public ArrayList getEveLevTypDets(String provisionalId) throws RemoteException;
//kalai
public ArrayList getViews() throws RemoteException;
public ArrayList getGroupValues() throws RemoteException;
public boolean insertGroupDetails(String groupId,String groupDet) throws RemoteException;
public ArrayList getViewGroupValues(String group_id) throws RemoteException;
public boolean updateGroupDetails(String layerId,String groupId,String groupDet) throws RemoteException;
public boolean alreadyExistCheck(String groupId,String groupDet) throws RemoteException;
public boolean deleteGroupValues(String[] chkIds) throws RemoteException;
public String getGroupEditList(String layerId) throws RemoteException;
public ArrayList getViewRelatedRoles() throws RemoteException;
public ArrayList getUserMapLOBList() throws RemoteException;
public ArrayList getViewRelatedRoleUsers(String viewRoleId) throws RemoteException;
public ArrayList getviewRoleLOB(String viewRoleId) throws RemoteException;
public ArrayList getviewRoleViewPoint()throws RemoteException;
public boolean insertLobViewpointMap(String viewRoleId,String viewUserId,String viewLobId,String[] viewPointId) throws RemoteException;
public ArrayList getSubViewList(String lobId,String viewId)throws RemoteException;
public Map getSubViewMap(String lobId,String viewId)throws RemoteException;
public ArrayList getTreeArtifactDetails(String user_Id,String lobId)throws RemoteException;
//kalai
}
