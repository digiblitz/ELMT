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
/*  Program Name    : AccommodationDetailBean.java
 *  Created Date    : Aug 29, 2006 7:15:36 PM
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
 * This is the bean class for the AccommodationDetailBean enterprise bean.
 * Created Aug 29, 2006 7:15:36 PM
 * @author suresh
 */
public class HLCAccommodationDetailBean implements EntityBean, HLCAccommodationDetailLocalBusiness {
    private EntityContext context;
    private String accommodationId;
    private String eventId;
    private String hotelName;
    private String hotelPhone;
    private String milesFromEvent;
    private Connection con;
    
    public void setAccommodationId(String accommodationId){
        this.accommodationId = accommodationId;
    }
    
    public void setEventId(String eventId){
        this.eventId = eventId;
    }

    public void setHotelName(String hotelName){
        this.hotelName = hotelName;
    }
 
    public void setHotelPhone(String hotelPhone){
        this.hotelPhone = hotelPhone;
    }
       
    public void setMilesFromEvent(String milesFromEvent){
        this.milesFromEvent = milesFromEvent;
    }
    
    public String getAccommodationId(){
        return accommodationId;
    }
   
     public String getEventId(){
        return eventId;
    }

    public String getHotelName(){
        return hotelName;
    }
 
    public String getHotelPhone(){
       return hotelPhone;
    }
       
    public String getMilesFromEvent(){
        return milesFromEvent;
    }
   
    public String ejbCreate(String eventId, String hotelName, String hotelPhone, String milesFromEvent) throws CreateException{
        Debug.print("AccommodationDetailBean ejbCreate");   
        this.eventId = eventId;
        this.hotelName = hotelName;
        this.hotelPhone = hotelPhone;
        this.milesFromEvent = milesFromEvent;

        try {
            insertRowAccommodationDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + accommodationId);
        return  accommodationId;
    }
    
    public void ejbPostCreate(String eventId, String hotelName, String hotelPhone, String milesFromEvent) throws CreateException{
        Debug.print("AccommodationDetailBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("AccommodationDetailBean ejbActivate");
        accommodationId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("AccommodationDetailBean ejbPassivate");
        accommodationId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("AccommodationDetailBean ejbRemove");

        try {
            deleteRow(accommodationId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("AccommodationDetailBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("AccommodationDetailBean ejbLoad");
        try {
            loadAccommodationDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("AccommodationDetailBean ejbStore");

        try {
            storeAccommodationDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String accommodationId) throws FinderException {
        Debug.print("AccommodationDetailBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(accommodationId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return accommodationId;
        } else {
            throw new ObjectNotFoundException("Row for id " + accommodationId + " not found.");
        }
    }
        
    
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String accommodationId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String accommodationId) throws SQLException {
        Debug.print("AccommodationDetailBean selectByPrimaryKey:" + accommodationId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT accommodation_id from " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS  + " WHERE accommodation_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accommodationId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("AccommodationDetailBean selectByPrimaryKey" + result);
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
        Debug.print("AccommodationDetailBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select accommodation_id from " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS ;
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

/**
  * @Method Name    :ejbFindByEventId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */  
  public Collection ejbFindByEventId(String eventId) throws FinderException{
        Debug.print("AccommodationDetailBean findByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select accommodation_id from " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS + " where event_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setFloat(1,Float.parseFloat(eventId));
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
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("AccommodationDetailBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("AccommodationDetailBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowAccommodationDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private void insertRowAccommodationDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("AccommodationDetailBean insertRowAccommodationDetails");
        
        this.accommodationId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS  + "(accommodation_id, event_id, hotel_name, hotel_phone, miles_from_event) " +
                 " values ( ? , ? , ? , ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, accommodationId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
            prepStmt.setString(3, hotelName);
            prepStmt.setString(4, hotelPhone);
            prepStmt.setString(5, milesFromEvent);

            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowAccommodationDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowAccommodationDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadAccommodationDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */  
    private void loadAccommodationDetails() throws SQLException {
        Debug.print("AccommodationDetailBean loadAccommodationDetails");
        accommodationId = (String)context.getPrimaryKey();

        Debug.print("AccommodationDetailBean loadAccommodationDetails Primary Key:" + accommodationId );
        makeConnection();
        try{
            String selectStatement =
                "select  event_id, hotel_name, hotel_phone, miles_from_event from " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS  + " where accommodation_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accommodationId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.eventId = rs.getString(1);
                this.hotelName = rs.getString(2);
                this.hotelPhone = rs.getString(3);
                this.milesFromEvent = rs.getString(4);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + accommodationId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadAccommodationDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadAccommodationDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeAccommodationDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void storeAccommodationDetails() throws SQLException {
        Debug.print("AccommodationDetailBean storeAccommodationDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS  + " set event_id = ? , hotel_name = ? , " + 
                    " hotel_phone = ?, miles_from_event = ? where accommodation_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setInt(1, Integer.parseInt(eventId));
            prepStmt.setString(2, hotelName);
            prepStmt.setString(3, hotelPhone);
            prepStmt.setString(4, milesFromEvent);
            prepStmt.setString(5, accommodationId);
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeAccommodationDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeAccommodationDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String accommodationId.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void deleteRow(String accommodationId) throws SQLException {
         Debug.print("AccommodationDetailBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_ACCOMMODATION_DETAILS  + "  where accommodation_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, accommodationId);
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
            Debug.print("AccommodationDetailBean : makeConnection");
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
            Debug.print("AccommodationDetailBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
    
}
