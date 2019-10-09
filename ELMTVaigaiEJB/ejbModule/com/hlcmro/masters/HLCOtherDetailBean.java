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
/*  Program Name    : OtherDetailBean.java
 *  Created Date    : Aug 29, 2006 7:30:13 PM
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
import com.hlcmro.util.HLCOtherDetailsVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;


/**
 * This is the bean class for the OtherDetailBean enterprise bean.
 * Created Aug 29, 2006 7:30:13 PM
 * @author suresh
 */
public class HLCOtherDetailBean implements EntityBean, HLCOtherDetailLocalBusiness {
    private EntityContext context;
    private String otherId;
    private String eventId;
    private Date courseCloseDate;
    private String entryLimit;
    private String ridersHorseLevelLimit;
    private String ridersHorseEntireLimit;
    private Date divisionEntryBirthDate;
    private boolean leaseDogs;
    private String teamCompetition;
    private String perTimePrice;
    private String otherTeamInfo;
    private String partyName;
    private String addInfo;
    private Connection con;
    private HLCOtherDetailsVO objOtherDetVO;
    
    //getter
    public HLCOtherDetailsVO getOtherDetailsVO() {
       Debug.print("OtherDetailBean getOtherDetailsVO");
       return new HLCOtherDetailsVO(otherId, eventId, courseCloseDate, entryLimit,
		 ridersHorseLevelLimit, ridersHorseEntireLimit, divisionEntryBirthDate,
		 leaseDogs, teamCompetition, perTimePrice, otherTeamInfo,
		 partyName, addInfo);
    }
    
     //Setters methods

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }
    public void setEventId(String eventId) 	{
        this.eventId = eventId;
    }
    public void setCourseCloseDate(Date courseCloseDate) {
        this.courseCloseDate = courseCloseDate;
    }
    public void setEntryLimit(String entryLimit){
        this.entryLimit = entryLimit;
    }
    public void setRidersHorseLevelLimit(String ridersHorseLevelLimit) {
        this.ridersHorseLevelLimit = ridersHorseLevelLimit;
    }
    public void setRidersHorseEntireLimit(String ridersHorseEntireLimit) {
        this.ridersHorseEntireLimit = ridersHorseEntireLimit;
    }
    public void setDivisionEntryBirthDate(Date divisionEntryBirthDate) {
        this.divisionEntryBirthDate = divisionEntryBirthDate ;
    }
    public void setLeaseDogs(boolean leaseDogs) {
        this.leaseDogs = leaseDogs;
    }
    public void setTeamCompetition(String teamCompetition)	{
        this.teamCompetition = teamCompetition;
    }
    public void setPerTimePrice(String perTimePrice) {
        this.perTimePrice = perTimePrice;
    }
    public void setOtherTeamInfo(String otherTeamInfo) {
        this.otherTeamInfo = otherTeamInfo;
    }
    public void setPartyName(String  partyName) {
        this.partyName = partyName;
    }
    public void setAddInfo(String addInfo) 	{
        this.addInfo = addInfo;
    }
    
     //getter
        public String getOtherId() {
            return otherId;
        }
        public String getEventId() {
            return eventId;
        }
        public Date getCourseCloseDate() {
            return courseCloseDate;
        }
        public String getEntryLimit() {
            return entryLimit;
        }
        public String getRidersHorseLevelLimit() {
            return ridersHorseLevelLimit;
        }
        public String getRidersHorseEntireLimit() {
            return ridersHorseEntireLimit;
        }
        public Date getDivisionEntryBirthDate() {
            return divisionEntryBirthDate;
        }
        public boolean isLeaseDogs() {
            return leaseDogs;
        }
        public String getTeamCompetition() {
            return teamCompetition;
        }
        public String getPerTimePrice() {
            return perTimePrice;
        }
        public String getOtherTeamInfo() {
            return otherTeamInfo;
        }
        public String getPartyName() {
            return partyName;
        }
        public String getAddInfo() {
            return addInfo;
        }
   
    public String ejbCreate(HLCOtherDetailsVO objOtherDetVO) throws CreateException{
        Debug.print("OtherDetailBean ejbCreate");
        this.eventId = objOtherDetVO.getEventId();
        this.courseCloseDate = objOtherDetVO.getCourseCloseDate();
        this.entryLimit = objOtherDetVO.getEntryLimit();
        this.ridersHorseLevelLimit = objOtherDetVO.getRidersHorseLevelLimit();
        this.ridersHorseEntireLimit = objOtherDetVO.getRidersHorseEntireLimit();
        this.divisionEntryBirthDate = objOtherDetVO.getDivisionEntryBirthDate();
        this.leaseDogs = objOtherDetVO.isLeaseDogs();
        this.teamCompetition = objOtherDetVO.getTeamCompetition();
        this.perTimePrice = objOtherDetVO.getPerTimePrice();
        this.otherTeamInfo = objOtherDetVO.getOtherTeamInfo();
        this.partyName = objOtherDetVO.getPartyName();
        this.addInfo = objOtherDetVO.getAddInfo();
        try {
            insertRowOtherDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + otherId);
        return  otherId;
    }
    
    public void ejbPostCreate(HLCOtherDetailsVO objOtherDetVO) throws CreateException{
        Debug.print("OtherDetailBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("OtherDetailBean ejbActivate");
        otherId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("OtherDetailBean ejbPassivate");
        otherId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("OtherDetailBean ejbRemove");

        try {
            deleteRow(otherId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("OtherDetailBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("OtherDetailBean ejbLoad");
        try {
            loadOtherDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("OtherDetailBean ejbStore");

        try {
            storeOtherDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String otherId) throws FinderException {
        Debug.print("OtherDetailBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(otherId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return otherId;
        } else {
            throw new ObjectNotFoundException("Row for id " + otherId + " not found.");
        }
    }
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String otherId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String otherId) throws SQLException {
        Debug.print("OtherDetailBean selectByPrimaryKey:" + otherId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT other_id from " + DBHelper.USEA_MRO_OTHER_DETAILS  + " WHERE other_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, otherId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("OtherDetailBean selectByPrimaryKey" + result);
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
        Debug.print("OtherDetailBean ejbFindAll");
        Vector otherList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select other_id from " + DBHelper.USEA_MRO_OTHER_DETAILS ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                otherList.addElement(rs.getString(1));
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
        return otherList;
}

/**
  * @Method Name    :ejbFindByEventId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */  
  public Collection ejbFindByEventId(String eventId) throws FinderException{
        Debug.print("OtherDetailBean ejbFindByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select other_id from " + DBHelper.USEA_MRO_OTHER_DETAILS + " where event_id = ?";
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
            throw new EJBException("SQL Exception in ejbFindByEventId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByEventId:" + e.getMessage());
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
        Debug.print("OtherDetailBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as otherId";
            Debug.print("OtherDetailBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowOtherDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */   
  private void insertRowOtherDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("OtherDetailBean insertRowOtherDetails");
        
        this.otherId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_OTHER_DETAILS  + "(other_id, event_id, course_close_date, entry_limit, riders_horse_level_limit, " +
                    " riders_horse_entire_limit, division_entry_birth_date, lease_dogs, team_competition, per_time_price, other_team_info, party_name, add_info) " +
                 " values ( ? , ? , ? , ?, ?, ?, ? , ? , ? ,?, ? , ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, otherId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
             if(courseCloseDate == null){
                prepStmt.setDate(3, null);
            }
            else{
                 prepStmt.setDate(3, DBHelper.toSQLDate(courseCloseDate));
            }
            prepStmt.setString(4, entryLimit);
            prepStmt.setString(5, ridersHorseLevelLimit);
            prepStmt.setString(6, ridersHorseEntireLimit);

            if(divisionEntryBirthDate == null){
                prepStmt.setDate(7, null);
            }
            else{
                 prepStmt.setDate(7, DBHelper.toSQLDate(divisionEntryBirthDate));
            }
            prepStmt.setBoolean(8, leaseDogs);
            prepStmt.setString(9, teamCompetition);
            
            if(perTimePrice==null || perTimePrice.equals("") || perTimePrice.equals("0")){
                 prepStmt.setFloat(10, 0);
            }
            else{
                 prepStmt.setFloat(10, Float.parseFloat(perTimePrice));
            }
            
            prepStmt.setString(11, otherTeamInfo);
            prepStmt.setString(12, partyName);
            prepStmt.setString(13, addInfo);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowOtherDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowOtherDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadOtherDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void loadOtherDetails() throws SQLException {
        Debug.print("OtherDetailBean loadOtherDetails");
        otherId = (String)context.getPrimaryKey();

        Debug.print("OtherDetailBean loadOtherDetails Primary Key:" + otherId );
        makeConnection();
        try{
            String selectStatement =
                "select  event_id, course_close_date, entry_limit, riders_horse_level_limit, " +
                " riders_horse_entire_limit, division_entry_birth_date, lease_dogs, team_competition, " +
                " per_time_price, other_team_info, party_name, add_info from " + DBHelper.USEA_MRO_OTHER_DETAILS  + " where other_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, otherId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.eventId = rs.getString(1);
                this.courseCloseDate = rs.getDate(2);
                this.entryLimit = rs.getString(3);
                this.ridersHorseLevelLimit = rs.getString(4);
                this.ridersHorseEntireLimit = rs.getString(5);
                this.divisionEntryBirthDate = rs.getDate(6);
                this.leaseDogs = rs.getBoolean(7);
                this.teamCompetition = rs.getString(8);
                this.perTimePrice = rs.getString(9);
                this.otherTeamInfo = rs.getString(10);
                this.partyName = rs.getString(11);
                this.addInfo = rs.getString(12);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + otherId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadOtherDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadOtherDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeOtherDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void storeOtherDetails() throws SQLException {
        Debug.print("OtherDetailBean storeOtherDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_OTHER_DETAILS  + " set event_id = ?, course_close_date = ?," +
                    " entry_limit = ?, riders_horse_level_limit = ?, riders_horse_entire_limit = ?," +
                    " division_entry_birth_date = ?, lease_dogs = ?, team_competition = ?, " +
                    " per_time_price = ?, other_team_info = ?, party_name = ?, " +
                    "add_info = ?   where other_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setInt(1, Integer.parseInt(eventId));
             if(courseCloseDate == null){
                prepStmt.setDate(2, null);
            }
            else{
                 prepStmt.setDate(2, DBHelper.toSQLDate(courseCloseDate));
            }
            prepStmt.setString(3, entryLimit);
            prepStmt.setString(4, ridersHorseLevelLimit);
            prepStmt.setString(5, ridersHorseEntireLimit);

            if(divisionEntryBirthDate == null){
                prepStmt.setDate(6, null);
            }
            else{
                 prepStmt.setDate(6, DBHelper.toSQLDate(divisionEntryBirthDate));
            }
            prepStmt.setBoolean(7, leaseDogs);
            prepStmt.setString(8, teamCompetition);
            prepStmt.setString(9, perTimePrice);
            prepStmt.setString(10, otherTeamInfo);
            prepStmt.setString(11, partyName);
            prepStmt.setString(12, addInfo);
            prepStmt.setString(13, otherId);
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeOtherDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeOtherDetails:" + e.getMessage());
        }        
    }
   
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String otherId.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void deleteRow(String otherId) throws SQLException {
         Debug.print("OtherDetailBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_OTHER_DETAILS  + "  where other_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, otherId);
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
            Debug.print("OtherDetailBean : makeConnection");
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
            Debug.print("OtherDetailBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
  }
}
