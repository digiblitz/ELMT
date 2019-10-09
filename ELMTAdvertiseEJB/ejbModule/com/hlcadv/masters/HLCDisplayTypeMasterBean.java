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
/*  Program Name    : DisplayTypeMasterBean.java
 *  Created Date    : Aug 28, 2006 10:42:24 AM
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
 * This is the bean class for the DisplayTypeMasterBean enterprise bean.
 * Created Aug 28, 2006 10:42:24 AM
 * @author suresh
 */
public class HLCDisplayTypeMasterBean implements EntityBean, HLCDisplayTypeMasterLocalBusiness {
    private EntityContext context;
    private String displayTypeId;
    private String displayTypeName;
    private String mediaId;
    private String displayTypeDescription;
    private boolean activeStatus;
    private Connection con;
    
    public void setDisplayTypeId(String displayTypeId){
        this.displayTypeId = displayTypeId;
    }
    public void setDisplayTypeName(String displayTypeName){
        this.displayTypeName = displayTypeName;
    }
    public void setMediaId(String mediaId){
        this.mediaId = mediaId;
    }
    public void setDisplayTypeDescription(String displayTypeDescription){
          this.displayTypeDescription = displayTypeDescription;
    }
    public void setActiveStatus(boolean activeStatus){
          this.activeStatus = activeStatus;
    }
    
    public String getDisplayTypeId(){
         return displayTypeId;
    }
    public String getDisplayTypeName(){
         return displayTypeName;
    }
    public String getMediaId(){
         return mediaId;
    }
    public String getDisplayTypeDescription(){
         return displayTypeDescription;
    }
    public boolean isActiveStatus(){
         return activeStatus;
    }
    
     public String ejbCreate(String displayTypeName , String mediaId, String displayTypeDescription) throws CreateException{
        Debug.print("DisplayTypeMasterBean ejbCreate");   
        this.displayTypeName = displayTypeName;
        this.mediaId = mediaId;
        this.displayTypeDescription = displayTypeDescription;

        try {
            insertRowDisplay();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + displayTypeId);
        return  displayTypeId;
    }

    public void ejbPostCreate(String displayTypeName , String mediaId, String displayTypeDescription) throws CreateException{
        Debug.print("DisplayTypeMasterBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("DisplayTypeMasterBean ejbActivate");
        displayTypeId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("DisplayTypeMasterBean ejbPassivate");
        displayTypeId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("DisplayTypeMasterBean ejbRemove");

        try {
            deleteRow(displayTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("DisplayTypeMasterBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("DisplayTypeMasterBean ejbLoad");
        try {
            loadDisplayDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("DisplayTypeMasterBean ejbStore");

        try {
            storeDisplayDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String displayTypeId) throws FinderException {
        Debug.print("DisplayTypeMasterBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(displayTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return displayTypeId;
        } else {
            throw new ObjectNotFoundException("Row for id " + displayTypeId + " not found.");
        }
    }
        
           
    /*********************** Database Routines *************************/

/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displayTypeId.
  * @return         :boolean  value.
  * @throws         :SQLException.
  */
    private boolean selectByPrimaryKey(String displayTypeId) throws SQLException {
        Debug.print("DisplayTypeMasterBean selectByPrimaryKey:" + displayTypeId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT display_type_id from " + DBHelper.USEA_ADV_DISPLAY_TYPE  + " WHERE display_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, displayTypeId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("DisplayTypeMasterBean selectByPrimaryKey" + result);
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
  * @Method Name    :getDisplayType.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection  value.
  * @throws         :Null.
  */
    public Collection getDisplayType(){
        Debug.print("DisplayTypeMasterBean getMedia");
        Vector mediaList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select display_type_id, display_type_name, media_id, display_type_description, active_status from " + DBHelper.USEA_ADV_DISPLAY_TYPE +
                    " where display_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,displayTypeId);
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
            throw new EJBException("SQL Exception in getDisplayType:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getDisplayType:" + e.getMessage());
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
        Debug.print("DisplayTypeMasterBean ejbFindAll");
        Vector displayList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select display_type_id from " + DBHelper.USEA_ADV_DISPLAY_TYPE + " order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                displayList.addElement(rs.getString(1));
                Debug.print("Display Type ID In Find ALL:" + rs.getString(1));
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
        return displayList;
}

/**
  * @Method Name    :ejbFindByDisplayTypeName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displayTypeName.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByDisplayTypeName(String displayTypeName) throws FinderException{
        Debug.print("DisplayTypeMasterBean findByDisplayTypeName:" + displayTypeName);
        makeConnection();
        ArrayList displayList = new ArrayList();
   	try {
            String selectStatement = "select display_type_id from " + DBHelper.USEA_ADV_DISPLAY_TYPE + " where display_type_name = ?  order by display_type_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DisplayTypeMasterBean findByDisplayTypeName:" + selectStatement);
            prepStmt.setString(1,displayTypeName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                displayList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DisplayTypeMasterBean in findByDisplayTypeName:" + displayList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByDisplayTypeName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByDisplayTypeName:" + e.getMessage());
        }
        return displayList;
}
    
/**
  * @Method Name    :ejbFindByDisplayTypeEditName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displayTypeId, String displayTypeName.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
     public Collection ejbFindByDisplayTypeEditName(String displayTypeId, String displayTypeName) throws FinderException{
        Debug.print("DisplayTypeMasterBean ejbFindByDisplayTypeEditName:" + displayTypeName);
        makeConnection();
        ArrayList displayList = new ArrayList();
   	try {
            String selectStatement = "select display_type_id from " + DBHelper.USEA_ADV_DISPLAY_TYPE + " where display_type_name = ?  and display_type_id !=? order by display_type_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DisplayTypeMasterBean findByDisplayTypeName:" + selectStatement);
            prepStmt.setString(1,displayTypeName);
            prepStmt.setString(2,displayTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                displayList.add(rs.getString(1));
                Debug.print("DisplayTypeMasterBean in findByDisplayTypeName:" + displayList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByDisplayTypeName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByDisplayTypeName:" + e.getMessage());
        }
        return displayList;
}
   
/**
  * @Method Name    :ejbFindByMedia.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByMedia(String mediaId) throws FinderException{
        Debug.print("DisplayTypeMasterBean ejbFindByMedia:" + mediaId);
        makeConnection();
        ArrayList displayList = new ArrayList();
   	try {
            String selectStatement = "select display_type_id from " + DBHelper.USEA_ADV_DISPLAY_TYPE + " where media_id = ?  order by display_type_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DisplayTypeMasterBean findByDisplayTypeName:" + selectStatement);
            prepStmt.setString(1,mediaId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                displayList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DisplayTypeMasterBean in ejbFindByMedia:" + displayList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByMedia:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByMedia:" + e.getMessage());
        }
        return displayList;
}
    
    
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :String  value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("DisplayTypeMasterBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("DisplayTypeMasterBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowDisplay.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */
    private void insertRowDisplay() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("DisplayTypeMasterBean insertRowMedia");
        
        this.displayTypeId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_DISPLAY_TYPE  + "(display_type_id, display_type_name, media_id, display_type_description) " +
                 " values ( ? , ? , ? , ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, displayTypeId);
            prepStmt.setString(2, displayTypeName);
            prepStmt.setString(3, mediaId);
            prepStmt.setString(4, displayTypeName);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowMedia:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowMedia:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadDisplayDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :SQLException.
  */
    private void loadDisplayDetails() throws SQLException {
        Debug.print("DisplayTypeMasterBean loadDisplayDetails");
        displayTypeId = (String)context.getPrimaryKey();

        Debug.print("DisplayTypeMasterBean loadDisplayDetails Primary Key:" + displayTypeId );
        makeConnection();
        try{
            String selectStatement =
                "select  display_type_name, media_id, display_type_description, active_status from " + DBHelper.USEA_ADV_DISPLAY_TYPE  + " where display_type_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, displayTypeId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.displayTypeName = rs.getString(1);
                this.mediaId = rs.getString(2);
                this.displayTypeDescription = rs.getString(3);
                this.activeStatus = rs.getBoolean(4);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + displayTypeId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadDisplayDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadMediaDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeDisplayDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :SQLException.
  */
    private void storeDisplayDetails() throws SQLException {
        Debug.print("DisplayTypeMasterBean storeDisplayDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_DISPLAY_TYPE  + " set display_type_name = ? , media_id = ? , display_type_description = ? , active_status = ? " + 
                    " where display_type_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, displayTypeName);
            prepStmt.setString(2, mediaId);
            prepStmt.setString(3, displayTypeDescription);
            prepStmt.setBoolean(4, activeStatus);
            prepStmt.setString(5, displayTypeId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeDisplayDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeDisplayDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displayTypeId.
  * @return         :void  value.
  * @throws         :SQLException.
  */
    private void deleteRow(String displayTypeId) throws SQLException {
         Debug.print("DisplayTypeMasterBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_DISPLAY_TYPE  + "  where display_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, displayTypeId);
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
  * @return         :void  value.
  * @throws         :Null.
  */
    private void makeConnection() {
            Debug.print("DisplayTypeMasterBean : makeConnection");
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
  * @return         :void  value.
  * @throws         :Null.
  */
    private void releaseConnection() {
            Debug.print("DisplayTypeMasterBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
