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
/*  Program Name    : DimensionBean.java
 *  Created Date    : Aug 27, 2006 12:53:57 PM
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
 * This is the bean class for the DimensionBean enterprise bean.
 * Created Aug 27, 2006 12:53:57 PM
 * @author suresh
 */
public class HLCDimensionBean implements EntityBean, HLCDimensionLocalBusiness {
    private EntityContext context;
    private String dimensionTypeId;
    private String dimensionTypeName;
    private boolean activeStatus;
    private Connection con;
    
    public void setDimensionTypeId(String dimensionTypeId){
        this.dimensionTypeId = dimensionTypeId;
    }
    
    public void setDimensionTypeName(String dimensionTypeName){
        this.dimensionTypeName = dimensionTypeName;
    }

    public void setActiveStatus(boolean activeStatus){
        this.activeStatus = activeStatus;
    }
    
   public String getDimensionTypeId(){
        return dimensionTypeId;
    }
   
    public String getDimensionTypeName(){
        return dimensionTypeName;
    }
        
    public boolean isActiveStatus(){
        return activeStatus;
    }
   
    public String ejbCreate(String dimensionTypeName) throws CreateException{
    Debug.print("DimensionBean ejbCreate");   
      this.dimensionTypeName = dimensionTypeName;
      
        try {
            insertRowDimension();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + dimensionTypeId);
        return  dimensionTypeId;
    }
    
      public void ejbPostCreate(String dimensionTypeName) throws CreateException{
        Debug.print("DimensionBean ejbPostCreate");
    }
   
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("DimensionBean ejbActivate");
        dimensionTypeId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("DimensionBean ejbPassivate");
        dimensionTypeId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("DimensionBean ejbRemove");

        try {
            deleteRow(dimensionTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
    
    public void unsetEntityContext() {
         Debug.print("DimensionBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("DimensionBean ejbLoad");
        try {
            loadDimensionDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    

    public void ejbStore() {
        Debug.print("DimensionBean ejbStore");

        try {
            storeDimensionDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    

   public String ejbFindByPrimaryKey(String dimensionTypeId) throws FinderException {
        Debug.print("DimensionBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(dimensionTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return dimensionTypeId;
        } else {
            throw new ObjectNotFoundException("Row for id " + dimensionTypeId + " not found.");
        }
    }
        
    
       /*********************** Database Routines *************************/

/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionTypeId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String dimensionTypeId) throws SQLException {
        Debug.print("DimensionBean selectByPrimaryKey:" + dimensionTypeId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT dimension_type_id from " + DBHelper.USEA_ADV_DIMENSION_MASTER  + " WHERE dimension_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, dimensionTypeId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("DimensionBean selectByPrimaryKey" + result);
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
  * @Method Name    :getDimension.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :Null.
  */
   public Collection getDimension(){
        Debug.print("DimensionBean getMedia");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select dimension_type_id from " + DBHelper.USEA_ADV_DIMENSION_MASTER +
                    " where dimension_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,dimensionTypeId);
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
            throw new EJBException("SQL Exception in getMedia:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getMedia:" + e.getMessage());
        }
        return dimList;
}
  
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
   public Collection ejbFindByAll() throws FinderException {
        Debug.print("DimensionBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select dimension_type_id from " + DBHelper.USEA_ADV_DIMENSION_MASTER + " order by add_date";
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
  * @Method Name    :ejbFindByDimensionTypeName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionTypeName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByDimensionTypeName(String dimensionTypeName) throws FinderException{
        Debug.print("DimensionBean ejbFindByDimensionTypeName:" + dimensionTypeName);
        makeConnection();
        ArrayList dimList = new ArrayList();
   	try {
            String selectStatement = "select dimension_type_id from " + DBHelper.USEA_ADV_DIMENSION_MASTER + " where dimension_type_name = ? order by dimension_type_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DimensionBean ejbFindByDimensionTypeName:" + selectStatement);
            prepStmt.setString(1,dimensionTypeName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                dimList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DimensionBean in ejbFindByDimensionTypeName:" + dimList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByDimensionTypeName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByDimensionTypeName:" + e.getMessage());
        }
        return dimList;
}
    
/**
  * @Method Name    :ejbFindByDimensionEditTypeName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionTypeId,String dimensionTypeName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByDimensionEditTypeName(String dimensionTypeId,String dimensionTypeName) throws FinderException{
        Debug.print("DimensionBean ejbFindByDimensionEditTypeName:" + dimensionTypeName);
        makeConnection();
        ArrayList dimList = new ArrayList();
   	try {
            String selectStatement = "select dimension_type_id from " + DBHelper.USEA_ADV_DIMENSION_MASTER + " where dimension_type_name = ? and dimension_type_id!= ? order by dimension_type_name " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,dimensionTypeName);
            prepStmt.setString(2,dimensionTypeId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                dimList.add(rs.getString(1));
                Debug.print("DimensionBean ejbFindByDimensionEditTypeName already name exist:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DimensionBean in ejbFindByDimensionEditTypeName:" + dimList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByDimensionEditTypeName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByDimensionEditTypeName:" + e.getMessage());
        }
        return dimList;
}
   
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionTypeId,String dimensionTypeName.
  * @return         :String value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("DimensionBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("DimensionBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowDimension.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private void insertRowDimension() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("DimensionBean insertRowDimension");
        
        this.dimensionTypeId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_DIMENSION_MASTER  + "(dimension_type_id, dimension_type_name) " +
                 " values ( ? , ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, dimensionTypeId);
            prepStmt.setString(2, dimensionTypeName);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowDimension:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowDimension:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadDimensionDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void loadDimensionDetails() throws SQLException {
        Debug.print("DimensionBean loadDimensionDetails");
        dimensionTypeId = (String)context.getPrimaryKey();

        Debug.print("DimensionBean loadDimensionDetails Primary Key:" + dimensionTypeId );
        makeConnection();
        try{
            String selectStatement =
                "select  dimension_type_name, active_status from " + DBHelper.USEA_ADV_DIMENSION_MASTER  + " where dimension_type_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, dimensionTypeId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.dimensionTypeName = rs.getString(1);
                this.activeStatus = rs.getBoolean(2);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + dimensionTypeId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadDimensionDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadDimensionDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeDimensionDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void storeDimensionDetails() throws SQLException {
        Debug.print("DimensionBean storeDimensionDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_DIMENSION_MASTER  + " set dimension_type_name = ? , active_status = ? " + 
                    " where dimension_type_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, dimensionTypeName);
            prepStmt.setBoolean(2, activeStatus);
            prepStmt.setString(3, dimensionTypeId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeDimensionDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeDimensionDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionTypeId.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void deleteRow(String dimensionTypeId) throws SQLException {
         Debug.print("DimensionBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_DIMENSION_MASTER  + "  where dimension_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, dimensionTypeId);
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
            Debug.print("DimensionBean : makeConnection");
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
            Debug.print("DimensionBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
