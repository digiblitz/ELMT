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
/*  Program Name    : EventTypeBean.java
 *  Created Date    : Aug 29, 2006 7:26:38 PM
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
package com.hlcmro.masters;

import com.hlcmro.exception.HLCMissingPrimaryKeyException;
import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the EventTypeBean enterprise bean.
 * Created Aug 29, 2006 7:26:38 PM
 * @author suresh
 */
public class HLCEventTypeBean implements EntityBean, HLCEventTypeLocalBusiness {
    private EntityContext context;
    private String eventDetailId;
    private String eventId;
    private String mapTypeId;
    private Connection con;
    private String[] evTypeList;
    
      public void setEvTypeList(String [] evTypeList){
            this.evTypeList = evTypeList;
      }
  
    public String [] getEvTypeList(){
        return evTypeList;
    }
    
    // getters
    public String getEventDetailId() {
        return eventDetailId;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public String getMapTypeId() {
        return mapTypeId;
    }

    //Setters methods
    public void setEventDetailId(String eventDetailId) {
        this.eventDetailId = eventDetailId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    
    public void setMapTypeId(String mapTypeId) 	{
        this.mapTypeId = mapTypeId;
    }
   
    public String ejbCreate(String eventId,String mapTypeId) throws CreateException{
        Debug.print("EventTypeBean ejbCreate");
        this.eventId = eventId;
        this.mapTypeId = mapTypeId;
        try {
            insertRowEventTypeDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + eventDetailId);
        return  eventDetailId;
    }
    
    public void ejbPostCreate(String eventId,String mapTypeId) throws CreateException{
        Debug.print("EventTypeBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("EventTypeBean ejbActivate");
        eventDetailId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("EventTypeBean ejbPassivate");
        eventDetailId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("EventTypeBean ejbRemove");

        try {
            deleteRow(eventDetailId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("EventTypeBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("EventTypeBean ejbLoad");
        try {
            loadEventTypeDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("EventTypeBean ejbStore");

        try {
            storeEventTypeDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String eventDetailId) throws FinderException {
        Debug.print("EventTypeBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(eventDetailId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return eventDetailId;
        } else {
            throw new ObjectNotFoundException("Row for id " + eventDetailId + " not found.");
        }
    }
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventDetailId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String eventDetailId) throws SQLException {
        Debug.print("EventTypeBean selectByPrimaryKey:" + eventDetailId);
        boolean result = false;
        makeConnection();
        try {
            String selectStatement = "SELECT event_detail_id from " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS  + " WHERE event_detail_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventDetailId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("EventTypeBean selectByPrimaryKey" + result);
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectByPrimaryKey:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectByPrimaryKey:" + e.getMessage());
        }
         return result;
    }
    
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventDetailId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */  
  public Collection ejbFindByAll() throws FinderException {
        Debug.print("EventTypeBean ejbFindAll");
        Vector evtList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select event_detail_id from " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                evtList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByAll:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByAll:" + e.getMessage());
        }
        return evtList;
}
/**
  * @Method Name    :ejbFindByEventId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
  public Collection ejbFindByEventId1(String eventId) throws FinderException{
        Debug.print("EventTypeBean findByEventId1");
        ArrayList eventList = new ArrayList();
        makeConnection();
   	try {
            String selectStatement = 
                "select event_detail_id from " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " where event_id =  ?";
             
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("EventTypeBean ejbFindByEventId:" + selectStatement);
            prepStmt.setLong(1,Long.parseLong(eventId));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String eventDetailId = rs.getString(1);
                eventList.add(eventDetailId);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByEventId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByEventId:" + e.getMessage());
        }
        return eventList;
  }  
/**
  * @Method Name    :ejbFindByEventId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
  public Collection ejbFindByEventId(String eventId) throws FinderException{
        Debug.print("EventTypeBean findByEventId");
        ArrayList eventList = new ArrayList();
        makeConnection();
   	try {
            String selectStatement = "select A.event_type_id, A.event_type_name, B.event_level_code, C.event_id,  B.event_level_id, C.map_type_id from " + 
            DBHelper.USEA_MRO_TYPE_MASTER + " A, " + DBHelper.USEA_MRO_LEVEL_MASTER + " B, " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " C, " +
            DBHelper.USEA_MRO_MAP_EVENT_LEVEL + " D  where C.map_type_id = D.map_type_id and D.event_type_id = A.event_type_id and  " +
            " D.event_level_id = B.event_level_id and C.event_id = ?";
             
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("EventTypeBean ejbFindByEventId:" + selectStatement);
            prepStmt.setLong(1,Long.parseLong(eventId));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String eventIdVal = rs.getString(4);
                String eventLevelId = rs.getString(5);
                String mapTypeId = rs.getString(6);
                String evtTypeList [] = {eventTypeId, eventTypeName, eventLevelCode, eventIdVal, eventLevelId, mapTypeId};
                //Debug.print("EventTypeBean ejbFindByEventId:" + evtTypeList);
                this.evTypeList = evtTypeList;
                eventList.add(evtTypeList);
            }
            
          
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByEventId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByEventId:" + e.getMessage());
        }
        return eventList;
  }
   
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */   
  private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("EventTypeBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as eventDetailId";
            Debug.print("EventTypeBean getNextId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getNextId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getNextId:" + e.getMessage());
        }        
        return nextId;
    }
  
/**
  * @Method Name    :insertRowEventTypeDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private void insertRowEventTypeDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("EventTypeBean insertRowEventTypeDetails");
        
        this.eventDetailId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS  + "(event_detail_id, event_id, map_type_id) " +
                 " values ( ? , ? , ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, eventDetailId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
            prepStmt.setString(3, mapTypeId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowEventTypeDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowEventTypeDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadEventTypeDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void loadEventTypeDetails() throws SQLException {
        Debug.print("EventTypeBean loadEventTypeDetails");
        eventDetailId = (String)context.getPrimaryKey();

        Debug.print("EventTypeBean loadEventTypeDetails Primary Key:" + eventDetailId );
        makeConnection();
        try{
            String selectStatement =
                "select  event_id, map_type_id from " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS  + " where event_detail_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventDetailId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.eventId = rs.getString(1);
                this.mapTypeId = rs.getString(2);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + eventDetailId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadEventTypeDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadEventTypeDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeEventTypeDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void storeEventTypeDetails() throws SQLException {
        Debug.print("EventTypeBean storeEventTypeDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS  + " set event_id = ? , map_type_id = ? " + 
                    " where event_detail_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setInt(1, Integer.parseInt(eventId));
            prepStmt.setString(2, mapTypeId);
            prepStmt.setString(3, eventDetailId);
       
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeEventTypeDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeEventTypeDetails:" + e.getMessage());
        }        
    }
   
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventDetailId.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void deleteRow(String eventDetailId) throws SQLException {
         Debug.print("EventTypeBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS  + "  where event_detail_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, eventDetailId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in deleteRow:" + e.getMessage());
        }        

    }
        
/**
  * @Method Name    :makeConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */  
  private void makeConnection() {
            Debug.print("EventTypeBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

/**
  * @Method Name    :releaseConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
  private void releaseConnection() {
            Debug.print("EventTypeBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
