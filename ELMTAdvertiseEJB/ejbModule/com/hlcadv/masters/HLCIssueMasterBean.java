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
/*  Program Name    : AdvertiseSessionBean.java
 *  Created Date    : Aug 27, 2006 3:27:12 PM
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
package com.hlcadv.masters;

import com.hlccommon.exception.HLCMissingPrimaryKeyException;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the IssueMasterBean enterprise bean.
 * Created Aug 27, 2006 3:27:12 PM
 * @author suresh
 */
public class HLCIssueMasterBean implements EntityBean, HLCIssueMasterLocalBusiness {
    private EntityContext context;
    private Connection con;
    private String issueId;
    private String issueName;
    private String mediaId;
    private boolean activeStatus;
    
    
    public void setIssueId(String issueId){
         this.issueId = issueId;
    }
    public void setIssueName(String issueName){
         this.issueName = issueName;
    }
    public void setMediaId(String mediaId){
         this.mediaId = mediaId;
    }
    public void setActiveStatus(boolean activeStatus){
         this.activeStatus = activeStatus;
    }
    public String getIssueId(){
        return issueId;
    }
    public String getIssueName(){
         return issueName;
    }
    public String getMediaId(){
         return mediaId;
    }
    public boolean isActiveStatus(){
         return activeStatus;
    }
    
     
  public String ejbCreate(String issueName , String mediaId) throws CreateException{
    Debug.print("IssueMasterBean ejbCreate"); 
        this.issueName = issueName;
        this.mediaId =mediaId;

        try {
            insertRowIssue();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + issueId);
        return  issueId;
    }

    public void ejbPostCreate(String issueName , String mediaId) throws CreateException{
        Debug.print("IssueMasterBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("IssueMasterBean ejbActivate");
        issueId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("IssueMasterBean ejbPassivate");
        issueId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("IssueMasterBean ejbRemove");

        try {
            deleteRow(issueId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("IssueMasterBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("IssueMasterBean ejbLoad");
        try {
            loadIssueDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("IssueMasterBean ejbStore");

        try {
            storeIssueDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String issueId) throws FinderException {
        Debug.print("IssueMasterBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(issueId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return issueId;
        } else {
            throw new ObjectNotFoundException("Row for id " + issueId + " not found.");
        }
    }     
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String issueId.
  * @return         :boolean  value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String issueId) throws SQLException {
        Debug.print("IssueMasterBean selectByPrimaryKey:" + issueId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT issue_id from " + DBHelper.USEA_ADV_ISSUE_MASTER  + " WHERE issue_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, issueId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("IssueMasterBean selectByPrimaryKey" + result);
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
  * @Method Name    :getIssue.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection  value.
  * @throws         :Null.
  */
   public Collection getIssue(){
        Debug.print("IssueMasterBean getIssue");
        Vector mediaList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select issue_id, issue_name, media_id, active_status from " + DBHelper.USEA_ADV_ISSUE_MASTER +
                    " where issue_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,issueId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mediaList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getIssue:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getMedia:" + e.getMessage());
        }
        return mediaList;
}
  
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
   public Collection ejbFindByAll() throws FinderException {
        Debug.print("IssueMasterBean ejbFindAll");
        Vector issueList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select issue_id from " + DBHelper.USEA_ADV_ISSUE_MASTER + " order by add_date" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                issueList.addElement(rs.getString(1));
                Debug.print("Issue ID In find aLL:" + rs.getString(1));
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
        return issueList;
}

/**
  * @Method Name    :ejbFindByIssueName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String issueName.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByIssueName(String issueName) throws FinderException{
        Debug.print("IssueMasterBean ejbFindByIssueName:" + issueName);
        makeConnection();
        ArrayList issueList = new ArrayList();
   	try {
            String selectStatement = "select issue_id from " + DBHelper.USEA_ADV_ISSUE_MASTER + " where issue_name = ? order by issue_name";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("IssueMasterBean ejbFindByIssueName:" + selectStatement);
            prepStmt.setString(1,issueName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                issueList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("IssueMasterBean in ejbFindByIssueName:" + issueList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByIssueName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByIssueName:" + e.getMessage());
        }
        return issueList;
}

/**
  * @Method Name    :ejbFindByIssueEditName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String issueId, String issueName.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByIssueEditName(String issueId,String issueName) throws FinderException{
        Debug.print("IssueMasterBean ejbFindByIssueEditName:" + issueName);
        makeConnection();
        ArrayList issueList = new ArrayList();
   	try {
            String selectStatement = "select issue_id from " + DBHelper.USEA_ADV_ISSUE_MASTER + " where issue_name = ? and issue_id != ? order by issue_name";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,issueName);
            prepStmt.setString(2,issueId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                issueList.add(rs.getString(1));
                Debug.print("IssueMasterBean in ejbFindByIssueEditName:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByIssueEditName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByIssueEditName:" + e.getMessage());
        }
        return issueList;
    }
    
/**
  * @Method Name    :ejbFindByMediaId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
       public Collection ejbFindByMediaId(String mediaId) throws FinderException{
        Debug.print("IssueMasterBean ejbFindByMediaId:" + mediaId);
        makeConnection();
        ArrayList issueList = new ArrayList();
   	try {
            String selectStatement = "select issue_id from " + DBHelper.USEA_ADV_ISSUE_MASTER + " where media_id = ? order by issue_name";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("IssueMasterBean ejbFindByMediaId:" + selectStatement);
            prepStmt.setString(1,mediaId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                issueList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("IssueMasterBean in ejbFindByMediaId:" + issueList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByMediaId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByMediaId:" + e.getMessage());
        }
        return issueList;
}
    
    
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :String value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("IssueMasterBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("IssueMasterBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowIssue.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private void insertRowIssue() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("IssueMasterBean insertRowIssue");
        
        this.issueId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_ISSUE_MASTER  + "(issue_id, issue_name, media_id) " +
                 " values ( ? , ? ,  ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, issueId);
            prepStmt.setString(2, issueName);
            prepStmt.setString(3, mediaId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowIssue:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowIssue:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadIssueDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void loadIssueDetails() throws SQLException {
        Debug.print("IssueMasterBean loadIssueDetails");
        issueId = (String)context.getPrimaryKey();

        Debug.print("IssueMasterBean loadIssueDetails Primary Key:" + issueId );
        makeConnection();
        try{
            String selectStatement =
                "select  issue_name, media_id, active_status from " + DBHelper.USEA_ADV_ISSUE_MASTER  + " where issue_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, issueId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.issueName = rs.getString(1);
                this.mediaId = rs.getString(2);
                this.activeStatus = rs.getBoolean(3);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + issueId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadIssueDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadIssueDetails:" + e.getMessage());
        }
    }
    
/**
  * @Method Name    :storeIssueDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void storeIssueDetails() throws SQLException {
        Debug.print("IssueMasterBean storeIssueDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_ISSUE_MASTER  + " set issue_name = ? , media_id = ? , active_status = ? " + 
                    " where issue_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, issueName);
            prepStmt.setString(2, mediaId);
            prepStmt.setBoolean(3, activeStatus);
            prepStmt.setString(4, issueId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeIssueDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeIssueDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String issueId.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void deleteRow(String issueId) throws SQLException {
        Debug.print("IssueMasterBean deleteRow");
        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_ISSUE_MASTER  + "  where issue_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, issueId);
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
            Debug.print("IssueMasterBean : makeConnection");
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
            Debug.print("IssueMasterBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
