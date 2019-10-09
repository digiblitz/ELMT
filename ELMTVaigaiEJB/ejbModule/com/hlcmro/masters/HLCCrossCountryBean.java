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
/*  Program Name    : CrossCountryBean.java
 *  Created Date    : Aug 29, 2006 7:29:39 PM
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
 * This is the bean class for the CrossCountryBean enterprise bean.
 * Created Aug 29, 2006 7:29:39 PM
 * @author suresh
 */
public class HLCCrossCountryBean implements EntityBean, HLCCrossCountryLocalBusiness {
    private EntityContext context;
    private  String crossCountryId;
    private  String eventId;
    private  String division;
    private  String length;
    private  String speed;
    private  String courseDescription;
    private  String addInformation;
    private Connection con;
    
 //getters
    public String getCrossCountryId() {
        return crossCountryId;
    }
    public String getEventId() {
        return eventId;
    }
    public String getDivision() {
        return division;
    }
    public String getLength() {
        return length;
    }
    public String getSpeed() {
        return speed;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public String getAddInformation() {
        return addInformation;
    }

    //Setters methods
    public void setCrossCountryId(String crossCountryId) {
        this.crossCountryId = crossCountryId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public void setDivision(String division) {
        this.division = division;
    }

    public void setLength(String length) {
        this.length = length;
    }
    public void setSpeed(String speed)	{
        this.speed = speed;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public void setAddInformation(String addInformation) {
        this.addInformation = addInformation;
    }
    
   
    public String ejbCreate(String eventId , String division,
	               String length,String speed,String courseDescription,String addInformation) throws CreateException{
        Debug.print("CrossCountryBean ejbCreate");
        this.crossCountryId = crossCountryId;
        this.eventId = eventId;
        this.division = division;
        this.length =length;
        this.speed = speed;
        this.courseDescription = courseDescription;
        this.addInformation = addInformation;
        try {
            insertRowCrossCountryDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + crossCountryId);
        return  crossCountryId;
    }
    
    public void ejbPostCreate(String eventId , String division,
	               String length,String speed,String courseDescription,String addInformation) throws CreateException{
        Debug.print("CrossCountryBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("CrossCountryBean ejbActivate");
        crossCountryId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("CrossCountryBean ejbPassivate");
        crossCountryId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("CrossCountryBean ejbRemove");

        try {
            deleteRow(crossCountryId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("CrossCountryBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("CrossCountryBean ejbLoad");
        try {
            loadCrossCountryDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("CrossCountryBean ejbStore");

        try {
            storeCrossCountryDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String crossCountryId) throws FinderException {
        Debug.print("CrossCountryBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(crossCountryId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return crossCountryId;
        } else {
            throw new ObjectNotFoundException("Row for id " + crossCountryId + " not found.");
        }
    }
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String crossCountryId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String crossCountryId) throws SQLException {
        Debug.print("CrossCountryBean selectByPrimaryKey:" + crossCountryId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT cross_country_id from " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS  + " WHERE cross_country_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, crossCountryId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("CrossCountryBean selectByPrimaryKey" + result);
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
        Debug.print("CrossCountryBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select cross_country_id from " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS ;
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
        Debug.print("CrossCountryBean findByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select cross_country_id from " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS + " where event_id = ?";
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
        Debug.print("CrossCountryBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as crossCountryId";
            Debug.print("CrossCountryBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowCrossCountryDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private void insertRowCrossCountryDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("CrossCountryBean insertRowCrossCountryDetails");
        
        this.crossCountryId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS  + "(cross_country_id, event_id, division, length, speed, course_description, add_information) " +
                 " values ( ? , ? , ? , ?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, crossCountryId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
            prepStmt.setString(3, division);
            prepStmt.setString(4, length);
            prepStmt.setString(5, speed);
            prepStmt.setString(6, courseDescription);
            prepStmt.setString(7, addInformation);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowCogginsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowCogginsDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadCrossCountryDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void loadCrossCountryDetails() throws SQLException {
        Debug.print("CrossCountryBean loadCogginsDetails");
        crossCountryId = (String)context.getPrimaryKey();

        Debug.print("CrossCountryBean loadCogginsDetails Primary Key:" + crossCountryId );
        makeConnection();
        try{
            String selectStatement =
                "select  event_id, division, length, speed, course_description, add_information from " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS  + " where cross_country_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, crossCountryId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.eventId = rs.getString(1);
                this.division = rs.getString(2);
                this.length = rs.getString(3);
                this.speed = rs.getString(4);
                this.courseDescription = rs.getString(5);
                this.addInformation = rs.getString(6);

                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + crossCountryId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadCogginsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadCogginsDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeCrossCountryDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void storeCrossCountryDetails() throws SQLException {
        Debug.print("CrossCountryBean storeCrossCountryDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS  + " set event_id = ? , division = ? ," + 
                    " length = ?, speed = ?, course_description = ? , add_information = ?  where cross_country_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setInt(1, Integer.parseInt(eventId));
            prepStmt.setString(2, division);
            prepStmt.setString(3, length);
            prepStmt.setString(4, speed);
            prepStmt.setString(5, courseDescription);
            prepStmt.setString(6, addInformation);
            prepStmt.setString(7, crossCountryId);
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeCrossCountryDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeCrossCountryDetails:" + e.getMessage());
        }        
    }
   
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String crossCountryId.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void deleteRow(String crossCountryId) throws SQLException {
         Debug.print("CrossCountryBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_CROSS_COUNTRY_DETAILS  + "  where cross_country_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, crossCountryId);
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
            Debug.print("CrossCountryBean : makeConnection");
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
            Debug.print("CrossCountryBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
