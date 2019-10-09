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
/*  Program Name    : DisplaySubTypeMasterBean.java
 *  Created Date    : Aug 28, 2006 11:47:00 AM
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
 * This is the bean class for the DisplaySubTypeMasterBean enterprise bean.
 * Created Aug 28, 2006 11:47:00 AM
 * @author suresh
 */
public class HLCDisplaySubTypeMasterBean implements EntityBean, HLCDisplaySubTypeMasterLocalBusiness {
    private EntityContext context;
    private String displaySubTypeId;
    private String displaySubTypeName;
    private String displayTypeId;
    private String displaySubTypeDescription;
    private boolean activeStatus;
    private Connection con;

    public void setDisplaySubTypeId(String displaySubTypeId){
        this.displaySubTypeId = displaySubTypeId;
    }
    public void setDisplaySubTypeName(String displaySubTypeName){
        this.displaySubTypeName = displaySubTypeName;
    }
    public void setDisplayTypeId(String displayTypeId){
        this.displayTypeId = displayTypeId;
    }
    public void setDisplaySubTypeDescription(String displaySubTypeDescription){
          this.displaySubTypeDescription = displaySubTypeDescription;
    }
    public void setActiveStatus(boolean activeStatus){
          this.activeStatus = activeStatus;
    }
    
    public String getDisplaySubTypeId(){
         return displaySubTypeId;
    }
    public String getDisplaySubTypeName(){
         return displaySubTypeName;
    }
    public String getDisplayTypeId(){
         return displayTypeId;
    }
    public String getDisplaySubTypeDescription(){
         return displaySubTypeDescription;
    }
    public boolean isActiveStatus(){
         return activeStatus;
    }
    

     public String ejbCreate(String displaySubTypeName , String displayTypeId, String displaySubTypeDescription) throws CreateException{
        Debug.print("DisplaySubTypeMasterBean ejbCreate");   
        this.displaySubTypeName = displaySubTypeName;
        this.displayTypeId = displayTypeId;
        this.displaySubTypeDescription = displaySubTypeDescription;
        try {
            insertRowDisplaySubType();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + displaySubTypeId);
        return  displaySubTypeId;
    }


    public void ejbPostCreate(String displaySubTypeName , String displayTypeId, String displaySubTypeDescription) throws CreateException{
        Debug.print("DisplaySubTypeMasterBean ejbPostCreate");
    }
    

    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    

    public void ejbActivate() {
        Debug.print("DisplaySubTypeMasterBean ejbActivate");
        displaySubTypeId = (String)context.getPrimaryKey();
    }
    

    public void ejbPassivate() {
        Debug.print("DisplaySubTypeMasterBean ejbPassivate");
        displaySubTypeId = "";   
    }
    

    public void ejbRemove() {
        Debug.print("DisplaySubTypeMasterBean ejbRemove");

        try {
            deleteRow(displaySubTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }

    public void unsetEntityContext() {
         Debug.print("DisplaySubTypeMasterBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("DisplaySubTypeMasterBean ejbLoad");
        try {
            loadDisplaySubTypeDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    

    public void ejbStore() {
        Debug.print("DisplaySubTypeMasterBean ejbStore");

        try {
            storeDisplaySubTypeDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     

   public String ejbFindByPrimaryKey(String displaySubTypeId) throws FinderException {
        Debug.print("DisplaySubTypeMasterBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(displaySubTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return displaySubTypeId;
        } else {
            throw new ObjectNotFoundException("Row for id " + displaySubTypeId + " not found.");
        }
    }
        
           
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displaySubTypeId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
    private boolean selectByPrimaryKey(String displaySubTypeId) throws SQLException {
        Debug.print("DisplaySubTypeMasterBean selectByPrimaryKey:" + displaySubTypeId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT display_sub_type_id from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE  + " WHERE display_sub_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, displaySubTypeId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("DisplaySubTypeMasterBean selectByPrimaryKey" + result);
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
  * @Method Name    :getDisplaySubType.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :Null.
  */
    public Collection getDisplaySubType(){
        Debug.print("DisplaySubTypeMasterBean getDisplaySubType");
        Vector subTypeList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE +
                    " where display_sub_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,displaySubTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                subTypeList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getDisplaySubType:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getDisplaySubType:" + e.getMessage());
        }
        return subTypeList;
}
  
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByAll() throws FinderException {
        Debug.print("DisplaySubTypeMasterBean ejbFindAll");
        Vector subTypeList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE + " order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                subTypeList.addElement(rs.getString(1));
                Debug.print("Display Sub Type ID In Find ALL:" + rs.getString(1));
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
        return subTypeList;
}

/**
  * @Method Name    :ejbFindByDisplaySubTypeName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displaySubTypeName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByDisplaySubTypeName(String displaySubTypeName) throws FinderException{
        Debug.print("DisplaySubTypeMasterBean findByDisplaySubTypeName:" + displaySubTypeName);
        makeConnection();
        ArrayList subTypeList = new ArrayList();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE + " where display_sub_type_name = ? order by display_sub_type_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DisplaySubTypeMasterBean findByDisplaySubTypeName:" + selectStatement);
            prepStmt.setString(1,displaySubTypeName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                subTypeList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DisplaySubTypeMasterBean in findByDisplaySubTypeName:" + subTypeList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByDisplaySubTypeName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByDisplaySubTypeName:" + e.getMessage());
        }
        return subTypeList;
    }
    
/**
  * @Method Name    :ejbFindByDisplaySubEditTypeName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displaySubTypeId, String displaySubTypeName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
        public Collection ejbFindByDisplaySubEditTypeName(String displaySubTypeId, String displaySubTypeName) throws FinderException{
            Debug.print("DisplaySubTypeMasterBean ejbFindByDisplaySubEditTypeName:" + displaySubTypeName);
            makeConnection();
            ArrayList subTypeList = new ArrayList();
            try {
                String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE + " where display_sub_type_name = ? and display_sub_type_id != ? order by display_sub_type_name" ;
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                Debug.print("DisplaySubTypeMasterBean findByDisplaySubTypeName:" + selectStatement);
                prepStmt.setString(1,displaySubTypeName);
                prepStmt.setString(2,displaySubTypeId);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    subTypeList.add(rs.getString(1));
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("DisplaySubTypeMasterBean in findByDisplaySubTypeName:" + subTypeList);
            } 
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in findByDisplaySubTypeName:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in findByDisplaySubTypeName:" + e.getMessage());
            }
            return subTypeList;
        }
    
/**
  * @Method Name    :ejbFindByDisplayTypeId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displayTypeId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
        public Collection ejbFindByDisplayTypeId(String displayTypeId) throws FinderException{
        Debug.print("DisplaySubTypeMasterBean ejbFindByDisplayTypeId:" + displayTypeId);
        makeConnection();
        ArrayList subTypeList = new ArrayList();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE + " where display_type_id = ? order by display_sub_type_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DisplaySubTypeMasterBean ejbFindByDisplayTypeId:" + selectStatement);
            prepStmt.setString(1,displayTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                subTypeList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DisplaySubTypeMasterBean in ejbFindByDisplayTypeId:" + subTypeList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByDisplayTypeId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByDisplayTypeId:" + e.getMessage());
        }
        return subTypeList;
}
    
    
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String displayTypeId.
  * @return         :Collection value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("DisplaySubTypeMasterBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("DisplaySubTypeMasterBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowDisplaySubType.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :NUll.
  * @return         :void value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */ 
    private void insertRowDisplaySubType() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("DisplaySubTypeMasterBean insertRowMedia");
        
        this.displaySubTypeId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE  + "(display_sub_type_id, display_sub_type_name, display_type_id, display_sub_type_description) " +
                 " values ( ? , ? , ? , ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, displaySubTypeId);
            prepStmt.setString(2, displaySubTypeName);
            prepStmt.setString(3, displayTypeId);
            prepStmt.setString(4, displaySubTypeDescription);
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
  * @Method Name    :loadDisplaySubTypeDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */ 
    private void loadDisplaySubTypeDetails() throws SQLException {
        Debug.print("DisplaySubTypeMasterBean loadDisplayDetails");
        displaySubTypeId = (String)context.getPrimaryKey();

        Debug.print("DisplaySubTypeMasterBean loadDisplayDetails Primary Key:" + displaySubTypeId );
        makeConnection();
        try{
            String selectStatement =
                "select  display_sub_type_name, display_type_id, display_sub_type_description, active_status from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE  + " where display_sub_type_id = ?  order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, displaySubTypeId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.displaySubTypeName = rs.getString(1);
                this.displayTypeId = rs.getString(2);
                this.displaySubTypeDescription = rs.getString(3);
                this.activeStatus = rs.getBoolean(4);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + displaySubTypeId + " not found in database.");
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
  * @Method Name    :storeDisplaySubTypeDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */ 
    private void storeDisplaySubTypeDetails() throws SQLException {
        Debug.print("DisplaySubTypeMasterBean storeDisplayDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE  + " set display_sub_type_name = ? , display_type_id = ? , display_sub_type_description = ? , active_status = ? " + 
                    " where display_sub_type_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, displaySubTypeName);
            prepStmt.setString(2, displayTypeId);
            prepStmt.setString(3, displaySubTypeDescription);
            prepStmt.setBoolean(4, activeStatus);
            prepStmt.setString(5, displaySubTypeId);
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
  * @param          :String displaySubTypeId.
  * @return         :void value.
  * @throws         :SQLException.
  */ 
    private void deleteRow(String displaySubTypeId) throws SQLException {
         Debug.print("DisplaySubTypeMasterBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_DISPLAY_SUB_TYPE  + "  where display_sub_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, displaySubTypeId);
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
            Debug.print("DisplaySubTypeMasterBean : makeConnection");
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
            Debug.print("DisplaySubTypeMasterBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
