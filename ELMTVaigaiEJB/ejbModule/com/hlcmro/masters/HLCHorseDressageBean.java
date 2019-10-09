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
/*  Program Name    : HorseDressageBean.java
 *  Created Date    : Aug 29, 2006 7:28:57 PM
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
 * This is the bean class for the HorseDressageBean enterprise bean.
 * Created Aug 29, 2006 7:28:57 PM
 * @author suresh
 */
public class HLCHorseDressageBean implements EntityBean, HLCHorseDressageLocalBusiness {
   private EntityContext context;
    private String  dressageId;
    private String eventId;
    private String dressageMapId;
    private String arenaSizeId;
    private Connection con;
    
    // getters
    public String getDressageId() {
        return dressageId;
    }
    public String getEventId() {
        return eventId;
    }
    public String getDressageMapId() {
        return dressageMapId;
    }
    public String getArenaSizeId() 	{
        return arenaSizeId;
    }

    //setter
    public void setDressageId(String dressageId) {
        this.dressageId = dressageId;
    }
    public void setEventId(String eventId) 	{
        this.eventId = eventId;
    }
    public void setDressageMapId(String dressageMapId) 	{
        this.dressageMapId = dressageMapId;
    }
    public void setArenaSizeId(String arenaSizeId) 	{
        this.arenaSizeId = arenaSizeId;
    }
    
   
    public String ejbCreate(String eventId,String dressageMapId,String arenaSizeId) throws CreateException{
        Debug.print("HorseDressageBean ejbCreate");
        this.eventId = eventId;
        this.dressageMapId = dressageMapId;
        this.arenaSizeId = arenaSizeId;
        try {
            insertRowHorseDressageDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + dressageId);
        return  dressageId;
    }
    
    public void ejbPostCreate(String eventId,String dressageMapId,String arenaSizeId) throws CreateException{
        Debug.print("HorseDressageBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("HorseDressageBean ejbActivate");
        dressageId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("HorseDressageBean ejbPassivate");
        dressageId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("HorseDressageBean ejbRemove");

        try {
            deleteRow(dressageId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("HorseDressageBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("HorseDressageBean ejbLoad");
        try {
            loadDressageDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("HorseDressageBean ejbStore");

        try {
            storeDressageDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String dressageId) throws FinderException {
        Debug.print("HorseDressageBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(dressageId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return dressageId;
        } else {
            throw new ObjectNotFoundException("Row for id " + dressageId + " not found.");
        }
    }
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dressageId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String dressageId) throws SQLException {
        Debug.print("HorseDressageBean selectByPrimaryKey:" + dressageId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT dressage_id from " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS  + " WHERE dressage_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, dressageId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("HorseDressageBean selectByPrimaryKey" + result);
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
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */  
  public Collection ejbFindByAll() throws FinderException {
        Debug.print("HorseDressageBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select dressage_id from " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                dimList.addElement(rs.getString(1));
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
        return dimList;
}
  
  public Collection ejbFindByEventId1(String eventId) throws FinderException {
      Debug.print("HorseDressageBean findByEventId1");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = " select dressage_id  from " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS +
                    " where event_id = ? ";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            
            Debug.print("HorseDressageBean ejbFindByEventId query:" + selectStatement);
            prepStmt.setLong(1,Long.parseLong(eventId));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                
                eventList.addElement(rs.getString(1));
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
        Debug.print("HorseDressageBean findByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = " select D.dressage_id, A.event_level_name, B.event_sub_level_name, C.arena_size_name, D.dressage_map_id " +
                    " from " + DBHelper.USEA_MRO_LEVEL_MASTER + " A, " + DBHelper.USEA_MRO_SUB_LEVEL_MASTER + " B , " + DBHelper.USEA_MRO_ARENA_SIZE_MASTER + " C, " +
                    DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS + " D, " + DBHelper.USEA_MRO_MAP_EVENT_SUB_LEVEL  + " E " +
                    " where D.dressage_map_id = E.dressage_map_id and D.arena_size_id = C.arena_size_id and " +
                    " E.event_level_id = A.event_level_id and E.event_sub_level_id = B.event_sub_level_id and " +
                    " D.event_id = ? ";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            
            Debug.print("HorseDressageBean ejbFindByEventId query:" + selectStatement);
            prepStmt.setLong(1,Long.parseLong(eventId));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String dressageId = rs.getString(1);
                String eventLevelName = rs.getString(2);
                String eventSubLevelName = rs.getString(3);
                String arenaSizeName = rs.getString(4);
                String dressageMapId = rs.getString(5);
                String horseList [] = {dressageId, eventLevelName, eventSubLevelName, arenaSizeName, dressageMapId};
                eventList.addElement(horseList);
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
        Debug.print("HorseDressageBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as dressageId";
            Debug.print("HorseDressageBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowHorseDressageDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private void insertRowHorseDressageDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("HorseDressageBean insertRowHorseDressageDetails");
        
        this.dressageId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS  + "(dressage_id, event_id, dressage_map_id, arena_size_id) " +
                 " values ( ? , ? , ?, ? )";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, dressageId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
            prepStmt.setString(3, dressageMapId);
            prepStmt.setString(4, arenaSizeId);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowHorseDressageDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowHorseDressageDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadDressageDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void loadDressageDetails() throws SQLException {
        Debug.print("HorseDressageBean loadDressageDetails");
        dressageId = (String)context.getPrimaryKey();

        Debug.print("HorseDressageBean loadDressageDetails Primary Key:" + dressageId );
        makeConnection();
        try{
            String selectStatement =
                "select  event_id, dressage_map_id, arena_size_id from " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS  + " where dressage_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, dressageId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.eventId = rs.getString(1);
                this.dressageMapId = rs.getString(2);
                this.arenaSizeId = rs.getString(3);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + dressageId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadDressageDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadDressageDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeDressageDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void storeDressageDetails() throws SQLException {
        Debug.print("HorseDressageBean storeDressageDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS  + " set event_id = ? , dressage_map_id = ? , " + 
                    " arena_size_id = ? where dressage_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
          
            prepStmt.setInt(1, Integer.parseInt(eventId));
            prepStmt.setString(2, dressageMapId);
            prepStmt.setString(3, arenaSizeId);
            prepStmt.setString(4, dressageId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeDressageDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeDressageDetails:" + e.getMessage());
        }        
    }
   
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dressageId.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void deleteRow(String dressageId) throws SQLException {
         Debug.print("HorseDressageBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_HORSE_DRESSAGE_DETAILS  + "  where dressage_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, dressageId);
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
            Debug.print("HorseDressageBean : makeConnection");
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
            Debug.print("HorseDressageBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
