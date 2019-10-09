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
import com.hlcutil.DBHelper;
import com.hlcutil.Debug;
import com.hlcutil.HLCEventEntryDAO;
import com.hlcutil.HLCMemberVO;
import com.hlcutil.HLCPaymentDetailVO;
import com.hlcutil.HLCPriceMatrixVO;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the MembershipQualificationMatrixBean enterprise bean.
 * Created Oct 29, 2007 1:32:50 PM
 * @author Dhivya
 */
public class HLCMembershipQualificationMatrixBean implements SessionBean, HLCMembershipQualificationMatrixRemoteBusiness {
    
    private SessionContext context;
    private InitialContext ic = null;
    private Connection con;
    ResultSet rs= null;
    PreparedStatement prepStmt = null;
    HLCEventEntryDAO dao = new HLCEventEntryDAO();
    
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
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
    }
    
    public HLCMemberVO getMemberDetails(String memberId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getSingleEventDetails() Called");
        HLCMemberVO memVO = new HLCMemberVO();
        
        try {
            if(memberId!=null && memberId.trim().length()!=0){
                memVO = dao.selectMemberDetails(memberId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return memVO;
    }
    public ArrayList getHorseDetails(String userId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getHorseDetails() :");
        ArrayList vObj = new ArrayList();
        try{
            if(userId!=null){
                vObj = dao.getHorseDetails(userId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getHorseDetails()" + e.getMessage());
        }
        return vObj;
    }
    public ArrayList getAllPriceItems(String result) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getHorseDetails() :");
        Debug.print("result in getAllPriceItems() :"+result);
        ArrayList vObj = new ArrayList();
        try{
            vObj = dao.selectAllPriceItems(result);
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getAllPriceItems()" + e.getMessage());
        }
        return vObj;
    }
    public ArrayList getSingleItemDetails(String itemId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getSingleItemDetails() :");
        ArrayList vObj = new ArrayList();
        try{
            if(itemId!=null){
                vObj = dao.selectSingleItemDetails(itemId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getSingleItemDetails()" + e.getMessage());
        }
        return vObj;
    }
    public boolean updateHorseDetails(String horseMemberId, String userId) throws RemoteException{
        Debug.print("KrishnaStatelesBean.updateHorseDetails() :");
        boolean result = false;
        try{
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0){
                result = dao.updateHorseDetails(horseMemberId,userId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.updateHorseDetails()" + e.getMessage());
        }
        return result;
    }
    public boolean updatePriceItemstatus(String itemId,String status) throws RemoteException{
        Debug.print("KrishnaStatelesBean.updatePriceItemstatus() :");
        boolean result = false;
        try{
            if(status!=null && status.trim().length()!=0 ){
                result = dao.updatePriceItemstatus(itemId,status);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.updatePriceItemstatus()" + e.getMessage());
        }
        return result;
    }
    public boolean updatePriceItem(String itemId,String itemName,String orgStatus) throws RemoteException{
        Debug.print("KrishnaStatelesBean.updatePriceItem() :");
        boolean result = false;
        try{
            if(itemId!=null && itemId.trim().length()!=0 ){
                result = dao.updatePriceItem(itemId,itemName,orgStatus);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.updatePriceItem()" + e.getMessage());
        }
        return result;
    }
    public boolean insertHorseRelationDetails(String horseMemberId,String userId,String trainerId) throws RemoteException{
        Debug.print("KrishnaStatelesBean.insertHorseRelationDetails() :");
        boolean result = false;
        try{
            if(horseMemberId!=null && horseMemberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0){
                result = dao.insertHorseRelationDetails(horseMemberId,userId,trainerId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.insertHorseRelationDetails()" + e.getMessage());
        }
        return result;
    }
    public String getUserIdBasedOnuserName(String userName) throws RemoteException {
        
        String userId = "";
        if(userName!=null && userName.trim().length()!=0){
            userId = dao.selectUserId(userName);
        }
        Debug.print(" getUserIdBasedOnuserName userId:" + userId);
        return userId;
    }
    
    public String insertUserDetails(String loginName, String firstName, String lastName, String emailId, String contactId) throws RemoteException{
        Debug.print("KrishnaStatelesBean.insertChampionshipDetails() :");
        String result="";
        try{
            if(loginName!=null && loginName.trim().length()!=0 ){
                result = dao.insertUserDetails(loginName,  firstName,  lastName,  emailId, contactId);
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.insertChampionshipDetails()" + e.getMessage());
        }
        return result;
    }
    public boolean insertContactDetails(String userId,String address,String country,String state,String city, String zipcode,String phone,String fax,String contactId) throws RemoteException{
        Debug.print("KrishnaStatelesBean.insertChampionshipDetails() :");
        boolean eveDet1 = false;
        
        try{
            if(userId!=null && userId.trim().length()!=0 ){
                eveDet1 = dao.insertContactDetails( userId, address, country, state, city,  zipcode, phone, fax, contactId);
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.insertChampionshipDetails()" + e.getMessage());
        }
        return eveDet1;
    }
    public boolean insertPriceItem(String itemName,String orgStatus) throws RemoteException{
        Debug.print("KrishnaStatelesBean.insertPriceItem() :");
        boolean eveDet1 = false;
        
        try{
            if(itemName!=null && itemName.trim().length()!=0 && orgStatus!=null && orgStatus.trim().length()!=0){
                eveDet1 = dao.insertPriceItem(itemName,orgStatus);
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.insertPriceItem()" + e.getMessage());
        }
        return eveDet1;
    }
    public String insertNewPriceItem(String itemName,String orgStatus) throws RemoteException{
        Debug.print("KrishnaStatelesBean.insertNewPriceItem() :");
        String eveDet1 = "";
        
        try{
            if(itemName!=null && itemName.trim().length()!=0 && orgStatus!=null && orgStatus.trim().length()!=0){
                eveDet1 = dao.insertNewPriceItem(itemName,orgStatus);
            }
        } catch(Exception e){
            Debug.print("Exception in KrishnastalessBean.insertNewPriceItem()" + e.getMessage());
        }
        return eveDet1;
    }
    public String getRiderId(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getMemberId: " + horseMemberId);
        String memberId  = "";
        try {
            memberId = dao.selectRiderId(horseMemberId);
        }catch(Exception e){
            Debug.print("Exception in getMemberId:" + e);
        }
        return memberId;
    }
    public ArrayList getEventDetailsBasedOnEventId(String eventId,String eventTypeID) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventDetailsBasedOnEventId()");
        ArrayList eventDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0 && eventTypeID!=null && eventTypeID.trim().length()!=0){
                eventDetails = dao.selectEventDetailsBasedOnEventId(eventId,eventTypeID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }
    public ArrayList getEventDetailsByEventId(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventDetailsByEventId()");
        ArrayList eventDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0 ){
                eventDetails = dao.selectEventDetailsByEventId(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }
    public ArrayList getEventDetailsBasedOnChamp(String eventId,String eventTypeID) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventDetailsBasedOnChamp()");
        ArrayList eventDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0 && eventTypeID!=null && eventTypeID.trim().length()!=0){
                eventDetails = dao.selectEventDetailsBasedOnChamp(eventId,eventTypeID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }
    public ArrayList getEventDetailsByChamp(String eventId) throws RemoteException {
        Debug.print("KrishnaStatelessBean.getEventDetailsByChamp()");
        ArrayList eventDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0 ){
                eventDetails = dao.selectEventDetailsByChamp(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventDetails;
    }
    public ArrayList getAllItemNamesForOrg() throws RemoteException {
        Debug.print("KrishnaStatelessBean.getAllItemNames()");
        ArrayList objUsrTyp = new ArrayList();
        try {
            objUsrTyp = dao.selectItemNames();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objUsrTyp;
    }
    public boolean isTrainerDetailsExist(String horseMemberId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.isTrainerDetailsExist() :");
        boolean result = false;
        try{
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                result = dao.isTrainerDetailsExist(horseMemberId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isTrainerDetailsExist()" + e.getMessage());
        }
        return result;
    }
    public ArrayList getAllHorseDetailsByOwnerId(String ownerId) throws RemoteException {
        Debug.print("KaverySessionBean getAllHorseDetailsByOwnerId ownerId: " + ownerId);
        ArrayList horseDetList  = new ArrayList();
        try {
            horseDetList = dao.selectAllHorseDetailsByOwnerId(ownerId);
        }catch(Exception e){
            Debug.print("Exception in getAllHorseDetailsByOwnerId:" + e);
        }
        return horseDetList;
    }
    public boolean isItemNameExist(String itemName) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean .isItemNameExist()");
        boolean result = false;
        try{
            if(itemName!=null && itemName.trim().length()!=0){
                result = dao.isItemNameExist(itemName);
                Debug.print("isItemNameExist in MembershipQualificationMatrixBean" + itemName);
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isItemNameExist()" + e.getMessage());
        }
        return result;
    }
    public boolean isEditItemNameExist(String itemId,String itemName) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean .isEditItemNameExist()");
        boolean result = false;
        try{
            if(itemId!=null && itemId.trim().length()!=0){
                result = dao.isEditItemNameExist(itemId,itemName);
                Debug.print("isEditItemNameExist in MembershipQualificationMatrixBean" + itemId);
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isEditItemNameExist()" + e.getMessage());
        }
        return result;
    }
    public ArrayList getEventLevelDetails(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventDetails()");
        ArrayList eventLevelDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventLevelDetails = dao.selectEventLevelDetails(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventLevelDetails;
    }
    
    public ArrayList getEventTypeDetails(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventDetails()");
        ArrayList eventTypeDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventTypeDetails = dao.selectEventTypeDetails(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventTypeDetails;
    }
    public ArrayList getEventTypeDetailsForChamp(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventDetails()");
        ArrayList eventTypeDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventTypeDetails = dao.selectEventTypeDetailsForChamp(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventTypeDetails;
    }
    public ArrayList getEventTypeDetWithoutChamp(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventTypeDetWithoutChamp()");
        ArrayList eventTypeDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventTypeDetails = dao.getEventTypeDetWithoutChamp(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventTypeDetails;
    }
    
    public boolean insertPriceMatrixDetails(HLCPriceMatrixVO objPrice) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.insertPriceMatrixDetails() :");
        Debug.print("MembershipQualificationMatrixBean insertPriceMatrixDetails():" + objPrice.getEventId());
        boolean objVal = false;
        String eventId=objPrice.getEventId();
        
        try{
            if(eventId!=null && eventId.trim().length()!=0 ){
                objVal = dao.insertPriceMatrixDetails(objPrice);
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.insertPriceMatrixDetails()" + e.getMessage());
        }
        return objVal;
    }
    public ArrayList getPriceMatrixDetails(String eventId, String itemId) throws RemoteException {
        ArrayList valVO = new ArrayList();
        
        try {
            if(eventId!=null && eventId.trim().length()!=0 && itemId!=null && itemId.trim().length()!=0){
                valVO = dao.selectPriceMatrixDetails(eventId,itemId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return valVO;
    }
    public ArrayList getPriceMatrixDetailsForChamp(String eventId, String itemId) throws RemoteException {
        ArrayList valVO = new ArrayList();
        
        try {
            if(eventId!=null && eventId.trim().length()!=0 && itemId!=null && itemId.trim().length()!=0){
                valVO = dao.selectPriceMatrixDetailsForChamp(eventId,itemId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return valVO;
    }
    public ArrayList getEventTypeDetailsByItemId(String eventId,String itemId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventTypeDetailsByItemId()");
        ArrayList eventTypeDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0 && itemId!=null && itemId.trim().length()!=0){
                
                eventTypeDetails = dao.selectEventTypeDetailsByItemId(eventId,itemId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventTypeDetails;
    }
    public ArrayList getEventTypeDetByItemIdForChamp(String eventId,String itemId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventTypeDetByItemIdForChamp()");
        ArrayList eventTypeDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0 && itemId!=null && itemId.trim().length()!=0){
                eventTypeDetails = dao.selectEventTypeDetailsByItemIdForChamp(eventId,itemId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventTypeDetails;
    }
    public boolean updatePriceMatrixDetails(HLCPriceMatrixVO priceVO) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.updatePriceMatrixDetails() Called");
        boolean result = false;
        String priceId=priceVO.getPriceId();
        Debug.print("priceId in MembershipQualificationMatrixBean.updatePriceMatrixDetails() : "+priceId);
        try{
            if(priceId!=null && priceId.trim().length()!=0 ){
                Debug.print(" MembershipQualificationMatrixBean.updatePriceMatrixDetails()"+priceId);
                result =  dao.updatePriceMatrixDetails(priceVO);
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.updatePriceMatrixDetails()" + e.getMessage());
        }
        return result;
    }
    public boolean isPriceMatrixExist(String eventId, String itemId, String eventTypeId, String eventLevelId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.ValidationDetailsExist() :");
        Debug.print("eventId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventId);
        Debug.print("itemId in MembershipQualificationMatrixBean ValidationDetailsExist():" + itemId);
        Debug.print("eventTypeId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventTypeId);
        Debug.print("eventLevelId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventLevelId);
        boolean result = false;
        try{
            if(eventId!=null && eventId.trim().length()!=0 ){
                result = dao.isPriceMatrixExist(eventId,  itemId,  eventTypeId,  eventLevelId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isPriceMatrixExist()" + e.getMessage());
        }
        return result;
    }
    public ArrayList getEventTiles(String year,String organizerId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getEventTiles() :");
        ArrayList vObj = new ArrayList();
        try{
            if(year!=null){
                vObj = dao.getEventTiles(year,organizerId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getEventTiles()" + e.getMessage());
        }
        return vObj;
    }
    
    public ArrayList getHorseMemberValidationDetails(String horseMemberId, String divisionId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventDetails()");
        ArrayList horseDetails = new ArrayList();
        try {
            if(horseMemberId!=null && horseMemberId.trim().length()!=0){
                horseDetails = dao.selectHorseValidationDetails(horseMemberId, divisionId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return horseDetails;
    }
    
    public ArrayList getQualificationDetails(String eventTypeId, String eventLevelId, String champStatus, String userTypeId, String divisionId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventDetails()");
        ArrayList qualificationDetails = new ArrayList();
        try {
            if(eventTypeId!=null && eventTypeId.trim().length()!=0 && eventLevelId!=null && eventLevelId.trim().length()!=0){
                qualificationDetails = dao.getQualificationDetails(eventTypeId, eventLevelId,champStatus,userTypeId,divisionId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return qualificationDetails;
    }
    
    public ArrayList getRiderDetails(String riderId, String divisionId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getRiderDetails()");
        ArrayList riderDetails = new ArrayList();
        try {
            if(riderId!=null && riderId.trim().length()!=0){
                riderDetails = dao.getRiderDetails(riderId, divisionId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return riderDetails;
    }
    
/*    public ArrayList getValidEventDetails(int jumpPenalties, int timePenalties, int finalPoints, String priority, String userTypeId, String divisionId, String chStatus) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getRiderDetails()");
        ArrayList validEventDetails = new ArrayList();
        try {
            validEventDetails = dao.getValidEventDetails(jumpPenalties, timePenalties, finalPoints, priority, userTypeId, divisionId, chStatus);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return validEventDetails;
    }*/
    
    public int getRecordCountForHorse(String qualificationPeriod, String dateToValidate, String horseMemberId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getRiderDetails()");
        int recordCount = 0;
        try {
            recordCount = dao.getRecordCountForHorse(qualificationPeriod, dateToValidate, horseMemberId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recordCount;
    }
    
    public int getRecordCountForRider(String qualificationPeriod, String dateToValidate, String riderId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getRiderDetails()");
        int recordCount = 0;
        try {
            recordCount = dao.getRecordCountForRider(qualificationPeriod, dateToValidate, riderId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recordCount;
    }
    public ArrayList getOrgCompRegList(int year, String eventId,String status, String organizerId) throws RemoteException {
        ArrayList organizerList = new ArrayList();
        try {
            if(year!=0 && organizerId!=null && organizerId.trim().length()!=0 && eventId!=null && eventId.trim().length()!=0){
                organizerList = dao.getOrgCompRegList(year, eventId, status, organizerId);
            }
            Debug.print("ArrayList Size in Session Bean: "+organizerList.size());
        }catch(Exception e){
            e.printStackTrace();
        }
        return organizerList;
    }
    public ArrayList getAllItemNamesForStaff() throws RemoteException {
        Debug.print("KrishnaStatelessBean.getAllItemNamesForStaff()");
        ArrayList objUsrTyp = new ArrayList();
        try {
            objUsrTyp = dao.selectAllItemNamesForStaff();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return objUsrTyp;
    }
    public boolean insertFixedPriceMatrix(HLCPriceMatrixVO objPrice) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.insertFixedPriceMatrix() :");
        boolean objVal = false;
        try{
            objVal = dao.insertFixedPriceMatrix(objPrice);
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.insertFixedPriceMatrix()" + e.getMessage());
        }
        return objVal;
    }
    public ArrayList getFixedPriceMatrixDetails(String itemId, String eventTypeId) throws RemoteException {
        ArrayList valVO = new ArrayList();
        
        try {
            if(eventTypeId!=null && eventTypeId.trim().length()!=0 && itemId!=null && itemId.trim().length()!=0){
                valVO = dao.selectFixedPriceMatrixDetails(itemId, eventTypeId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return valVO;
    }
    public ArrayList getFixedPriceMatrixForChamp(String areaId) throws RemoteException {
        ArrayList valVO = new ArrayList();
        
        try {
            if(areaId!=null && areaId.trim().length()!=0 ){
                valVO = dao.getFixedPriceMatrixForChamp(areaId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return valVO;
    }
    public boolean updateFixedPriceMatrixDetails(HLCPriceMatrixVO priceVO) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.updateFixedPriceMatrixDetails() Called");
        boolean result = false;
        String priceId=priceVO.getPriceId();
        Debug.print("priceId in MembershipQualificationMatrixBean.updateFixedPriceMatrixDetails() : "+priceId);
        try{
            Debug.print(" MembershipQualificationMatrixBean.updateFixedPriceMatrixDetails()"+priceId);
            result =  dao.updateFixedPriceMatrixDetails(priceVO);
            
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.updateFixedPriceMatrixDetails()" + e.getMessage());
        }
        return result;
    }
    
    public HLCCompRegistrationVO getSingleCompRegDetails(String registrationId) throws RemoteException {
        HLCCompRegistrationVO regVO = new HLCCompRegistrationVO();
        
        try {
            if(registrationId!=null && registrationId.trim().length()!=0){
                regVO = dao.getSingleCompRegDetails(registrationId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return regVO;
    }
    
    public boolean approveSingleCompRegDetails(String registrationId, String qualifyStatus, String comments) throws RemoteException {
        boolean result = false;
        Debug.print("approveSingleCompRegDetails :");
        try {
            if(registrationId!=null && registrationId.trim().length()!=0){
                Debug.print("registrationId :"+registrationId);
                result = dao.approveSingleCompRegDetails(registrationId, qualifyStatus, comments);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean insertCompResultUploadDetails(String event_id,String event_name,String result_file_path,String eventTypeId) throws RemoteException {
        boolean result = false;
        
        try {
            result = dao.insertCompResultUploadDetails(event_id,event_name,result_file_path,eventTypeId);
            Debug.print("compDao.insertCompResultUploadDetails() in session bean upload_id :"+result);
        } catch(Exception e){
            Debug.print("Error in session bean insertCompResultUploadDetails : "+e.getMessage());
        }
        return result;
    }
    
    public String getNextIdforPayment() throws RemoteException {
        Debug.print("KaverySessionBean Payment getNextId ");
        String nextPaymentId = "";
        try {
            nextPaymentId = dao.getNextIdforPayment();
        } catch(Exception e){
            Debug.print("Exception in getAllSpecification:" + e);
        }
        return nextPaymentId;
    }
    
    public String createPayment(HLCPaymentDetailVO objPayDet) throws RemoteException{
        Debug.print("PaymentSessionBean createPayment");
        
        String oePaymentId = "";
        try{
            if(objPayDet.getUserId()!=null){
                oePaymentId = dao.insertRowPayment(objPayDet);
            } else{
                oePaymentId  = "";
            }
            Debug.print("PaymentSessionBean Id:" + oePaymentId);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return oePaymentId;
    }
    
    public boolean insertEventRegistrationDetails(HLCCompRegistrationVO compVO, String oePaymentId) throws RemoteException{
        Debug.print("PaymentSessionBean insertEventRegistrationDetails()");
        
        boolean result = false;
        try{
            result = dao.insertEventRegistrationDetails(compVO, oePaymentId);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return result;
    }
    
    public ArrayList getHorseDetailsByHorseId(String horseMemberId) throws RemoteException{
        Debug.print("PaymentSessionBean createPayment");
        
        ArrayList horseDetails = new ArrayList();
        try{
            horseDetails = dao.getHorseDetailsByHorseId(horseMemberId);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return horseDetails;
    }
    
    public ArrayList getFixedAmount(String evTypeId, String evLevelId, String chStatus) throws RemoteException{
        Debug.print("PaymentSessionBean createPayment");
        
        ArrayList fixedDetails = new ArrayList();
        try{
            fixedDetails = dao.getEventFixedAmount(evTypeId, evLevelId, chStatus);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return fixedDetails;
    }
    
    public ArrayList getorganizerPriceDetails(String eventId, String chStatus, String eventLevelId, String eventTypeId) throws RemoteException{
        Debug.print("PaymentSessionBean createPayment");
        
        ArrayList orgPriceDetails = new ArrayList();
        try{
            orgPriceDetails = dao.getOrganizerPriceDetails(eventId, chStatus, eventLevelId, eventTypeId);
        } catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return orgPriceDetails;
    }
    
    public ArrayList getMyCompRegList(int year, String riderUserId) throws RemoteException {
        ArrayList ownList = new ArrayList();
        try {
            if(year!=0 && riderUserId!=null && riderUserId.trim().length()!=0){
                ownList = dao.getMyCompRegList(year,riderUserId);
            }
            Debug.print("ArrayList Size in Session Bean: "+ownList.size());
        }catch(Exception e){
            e.printStackTrace();
        }
        return ownList;
    }
    
    public HLCCompRegistrationVO getMySingleCompRegDetails(String registrationId) throws RemoteException {
        HLCCompRegistrationVO regVO = new HLCCompRegistrationVO();
        
        try {
            if(registrationId!=null && registrationId.trim().length()!=0){
                regVO = dao.getMySingleCompRegDetails(registrationId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return regVO;
    }
    
    public boolean isFixedPriceMatrixExist(String itemId, String eventTypeId, String eventLevelId, String areaId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.ValidationDetailsExist() :");
        Debug.print("itemId in MembershipQualificationMatrixBean ValidationDetailsExist():" + itemId);
        Debug.print("eventTypeId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventTypeId);
        Debug.print("eventLevelId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventLevelId);
        Debug.print("areaId in MembershipQualificationMatrixBean ValidationDetailsExist():" + areaId);
        boolean result = false;
        try{
            if(itemId!=null && itemId.trim().length()!=0 && eventLevelId!=null && eventLevelId.trim().length()!=0){
                result = dao.isFixedPriceMatrixExist(itemId,  eventTypeId,  eventLevelId,  areaId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isFixedPriceMatrixExist()" + e.getMessage());
        }
        return result;
    }
    
    public ArrayList getEventLevelDetailsWithChStatus(String eventId, String compYear) throws RemoteException {
        ArrayList regVO = new ArrayList();
        
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                regVO = dao.getEventLevelDetailsWithChStatus(eventId, compYear);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return regVO;
    }
    
    public HLCCalendarVO selectEventDetails(String eventId, String compYear) throws RemoteException {
        HLCCalendarVO regVO = new HLCCalendarVO();
        
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                regVO = dao.getEventFullDetails(eventId, compYear);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return regVO;
    }
    
    public String getEventLevelName(String eventLevelId) throws RemoteException {
        String evLevelName = "";
        
        try {
            if(eventLevelId!=null && eventLevelId.trim().length()!=0){
                evLevelName = dao.getEventLevelName(eventLevelId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return evLevelName;
    }
    
    public String getEventTypeName(String eventTypeId) throws RemoteException {
        String evTypeName = "";
        
        try {
            if(eventTypeId!=null && eventTypeId.trim().length()!=0){
                evTypeName = dao.getEventTypeName(eventTypeId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return evTypeName;
    }
    
    public ArrayList getEventTypes(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventTypes()");
        ArrayList eventTypeDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventTypeDetails = dao.selectEventTypes(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventTypeDetails;
    }
    public ArrayList getOrgCompResLabelList(String eventTypeId) throws RemoteException {
        ArrayList organizerList = new ArrayList();
        try {
            if( eventTypeId!=null && eventTypeId.trim().length()!=0){
                organizerList = dao.getOrgCompResLabelList(eventTypeId);
            }
            Debug.print("ArrayList Size in Session Bean: "+organizerList.size());
        }catch(Exception e){
            e.printStackTrace();
        }
        return organizerList;
    }
    public ArrayList getOrgCompResList(int year, String eventId, String eventTypeId, String organizerId) throws RemoteException {
        ArrayList organizerList = new ArrayList();
        try {
            if(year!=0 && organizerId!=null && organizerId.trim().length()!=0 && eventId!=null && eventId.trim().length()!=0){
                organizerList = dao.getOrgCompResList(year, eventId, eventTypeId, organizerId);
            }
            Debug.print("ArrayList Size in Session Bean: "+organizerList.size());
        }catch(Exception e){
            e.printStackTrace();
        }
        return organizerList;
    }
    
    public boolean insertCompResultDetails(HLCCompetitionResultVO compResVO) throws RemoteException{
        Debug.print("insertCompResultDetails");
        
        boolean result = false;
        try{
            result = dao.insertCompResultDetails(compResVO);
        } catch(Exception exp){
            throw new EJBException("insertCompResultDetails : " + exp.getMessage());
        }
        return result;
    }
    
    public String getEventLevelId(String eventLevelName) throws RemoteException {
        String evLevelName = "";
        
        try {
            if(eventLevelName!=null && eventLevelName.trim().length()!=0){
                evLevelName = dao.getEventLevelId(eventLevelName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return evLevelName;
    }
    public String getEventDivId(String divisionName) throws RemoteException {
        String evLevelName = "";
        
        try {
            if(divisionName!=null && divisionName.trim().length()!=0){
                evLevelName = dao.getEventDivId(divisionName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return evLevelName;
    }
    
    public boolean isCompResultExist(String eventId, String eventTypeId, String eventLevelId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.ValidationDetailsExist() :");
        Debug.print("eventId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventId);
        Debug.print("eventTypeId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventTypeId);
        Debug.print("eventLevelId in MembershipQualificationMatrixBean ValidationDetailsExist():" + eventLevelId);
        boolean result = false;
        try{
            if(eventId!=null && eventId.trim().length()!=0 ){
                result = dao.isCompResultExist(eventId, eventTypeId,  eventLevelId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isCompResultExist()" + e.getMessage());
        }
        return result;
    }
    
    public boolean checkHorseAvailability(String eventId, String horseMemberId, String compYear) throws RemoteException {
        boolean result = false;
        
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                result = dao.checkHorseAvailability(eventId, horseMemberId, compYear);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList getEventLevelDetWithoutChamp(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventLevelDetWithoutChamp()");
        ArrayList eventLevelDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventLevelDetails = dao.getEventLevelDetWithoutChamp(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventLevelDetails;
    }
    
    public ArrayList getEventLevelDetWithChamp(String eventId) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventLevelDetWithChamp()");
        ArrayList eventLevelDetails = new ArrayList();
        try {
            if(eventId!=null && eventId.trim().length()!=0){
                eventLevelDetails = dao.selectEventLevelDetailsWithChamp(eventId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventLevelDetails;
    }
    
    public ArrayList getMyEventTiles(int year,String riderUserId) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getEventTiles() :");
        ArrayList vObj = new ArrayList();
        try{
            if(year!=0){
                vObj = dao.getMyEventTiles(year,riderUserId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getEventTiles()" + e.getMessage());
        }
        return vObj;
    }
    
    public String getEventTypeId(String eventTypeName) throws RemoteException {
        Debug.print("MembershipQualificationMatrixBean.getEventTypeId() :"+eventTypeName);
        String evTypName = "";
        
        try {
            if(eventTypeName!=null && eventTypeName.trim().length()!=0){
                Debug.print("MembershipQualificationMatrixBean.getEventTypeId() :"+eventTypeName);
                evTypName = dao.getEventTypeId(eventTypeName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return evTypName;
    }
    
    public ArrayList getMyCompResultList(String riderUserId, String eventId, int year) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getMyCompResultList() :");
        ArrayList vObj = new ArrayList();
        try{
            if(eventId!=null && eventId.trim().length()!=0 && riderUserId!=null && riderUserId.trim().length()!=0 && year!=0){
                vObj = dao.getMyCompResultList(riderUserId,eventId,year);
                
            }
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getEventTiles()" + e.getMessage());
        }
        return vObj;
    }
    
    public HLCCompResultVO getMyCompResultView(String compResultId) throws RemoteException {
        HLCCompResultVO regVO = new HLCCompResultVO();
        
        try {
            if(compResultId!=null && compResultId.trim().length()!=0){
                regVO = dao.getMyCompResultView(compResultId);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return regVO;
    }
    
    public ArrayList getAllEventTiles(int year) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getAllEventTiles() :");
        ArrayList vObj = new ArrayList();
        try{
            if(year!=0){
                vObj = dao.getAllEventTiles(year);
            }
            
        } catch(Exception e){
            Debug.print("Exception in getEventDetails.getAllEventTiles()" + e.getMessage());
        }
        return vObj;
    }
    public ArrayList getCompResultList(String eventId, int year) throws RemoteException{
        Debug.print("MembershipQualificationMatrixBean.getCompResultList() :");
        ArrayList vObj = new ArrayList();
        try{
            if(eventId!=null && eventId.trim().length()!=0 && year!=0){
                vObj = dao.getCompResultList(eventId,year);
                
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.getCompResultList()" + e.getMessage());
        }
        return vObj;
    }
    
    public boolean isCompResExist(int year, String eventId, String horseMemberId, String riderUserId) throws RemoteException{
        boolean result = false;
        try{
            if(year!=0 && eventId!=null && eventId.trim().length()!=0 &&
                    horseMemberId!=null && horseMemberId.trim().length()!=0 && riderUserId!=null && riderUserId.trim().length()!=0){
                result = dao.isCompResExist(year, eventId, horseMemberId,  riderUserId);
                
            }
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isCompResExist()" + e.getMessage());
        }
        return result;
    }
    
    public String getTrainerId() throws RemoteException{
        String trainerId = "";
        try{
            trainerId = dao.getTrainerId();
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isCompResExist()" + e.getMessage());
        }
        return trainerId;
    }
    
    public String getContactTypeId() throws RemoteException {
        String result="";
        try{
            result = dao.selectContactTypeId();
        } catch(Exception e){
            Debug.print("Exception in MembershipQualificationMatrixBean.isCompResExist()" + e.getMessage());
        }
        
        return result;
    }
     public String getOwnerId(String horseMemberId) throws RemoteException {
        Debug.print("KaverySessionBean getMemberId: " + horseMemberId);
        String memberId  = "";
        try {
            memberId = dao.selectOwnerId(horseMemberId);
        }catch(Exception e){
            Debug.print("Exception in getMemberId:" + e);
        }
        return memberId;
    }
      public String getUserNameBasedOnuserId(String userId) throws RemoteException {
        
        String userName = "";
        if(userId!=null && userId.trim().length()!=0){
            userName = dao.selectUserName(userId);
        }
        Debug.print(" getUserNameBasedOnuserId userId:" + userName);
        return userName;
    }
    /**===================Initialization for Initial Context===============================*/
    
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("This is from getInitialContext()");
        return ic;
    }
    
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(DBHelper.dbName);
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
    }
    private void releaseConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
}
