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
/*  Program Name    : DimensionDetailBean.java
 *  Created Date    : Aug 28, 2006 3:19:19 PM
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
import com.hlccommon.util.HLCAdvertisementVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the DimensionDetailBean enterprise bean.
 * Created Aug 28, 2006 3:19:19 PM
 * @author suresh
 */
public class HLCDimensionDetailBean implements EntityBean, HLCDimensionDetailLocalBusiness {
    private EntityContext context;
    private String dimensionId;
    private String displaySubTypeId;
    private String displayTypeId;
    private String dimensionTypeId;
    private String mediaId;
    private String dimensionName;
    private String height;
    private String width;
    private String units;
    private String imagePath;
    private ArrayList advLists;
            
    private Connection con;
    
    
     public void setDisplayTypeId(String displayTypeId){
        this.displayTypeId = displayTypeId;
    }
     
    public String getDisplayTypeId(){
       return this.displayTypeId;
    }
    public void setAdvLists(List displayTypeId){
        this.advLists = advLists;
    }
    public List getAdvLists(){
       return this.advLists;
    }
    
    public void setDimensionId(String dimensionId){
        this.dimensionId = dimensionId;
    }
    
     public void setDisplaySubTypeId(String displaySubTypeId){
        this.displaySubTypeId = displaySubTypeId;
    }
    
    public void setDimensionTypeId(String dimensionTypeId){
        this.dimensionTypeId = dimensionTypeId;
    }

    public void setMediaId(String mediaId){
         this.mediaId = mediaId;
    }
     
    public void setDimensionName(String dimensionName){
         this.dimensionName = dimensionName;
    }
  
    
    public void setHeight(String height){
         this.height = height;
    }
    
    public void setWidth(String width){
         this.width = width;
    }
        
    public void setUnits(String units){
        this.units = units;
    }
    
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    
    public String getDimensionId(){
        return dimensionId;
    }
    
     public String getDisplaySubTypeId(){
        return displaySubTypeId;
    }
    
    public String getDimensionTypeId(){
       return dimensionTypeId;
    }

    public String getMediaId(){
        return mediaId;
    }
     
    public String getDimensionName(){
         return dimensionName;
    }
  
    
    public String getHeight(){
        return height;
    }
    
    public String getWidth(){
        return width;
    }
        
    public String getUnits(){
        return units;
    }
    
    public String getImagePath(){
        return imagePath;
    }
    
   
     public String ejbCreate(String displaySubTypeId , String dimensionTypeId, String mediaId, String dimensionName, String height,
             String width,  String units,  String imagePath) throws CreateException{
        Debug.print("DimensionDetailBean ejbCreate");   
        
        this.displaySubTypeId = displaySubTypeId;
        this.dimensionTypeId = dimensionTypeId;
        this.mediaId = mediaId;
        this.dimensionName = dimensionName;
        this.height = height;
        this.width = width;
        this.units = units;
        this.imagePath = imagePath;
        
        try {
            insertRowDimDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + dimensionId);
        return  dimensionId;
    }

    public void ejbPostCreate(String displaySubTypeId , String dimensionTypeId, String mediaId, String dimensionName, String height,
             String width,  String units,  String imagePath) throws CreateException{
        Debug.print("DimensionDetailBean ejbPostCreate");
    }
    

    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
  

    public void ejbActivate() {
        Debug.print("DimensionDetailBean ejbActivate");
        dimensionId = (String)context.getPrimaryKey();
    }
    

    public void ejbPassivate() {
        Debug.print("DimensionDetailBean ejbPassivate");
        dimensionId = "";   
    }
    

    public void ejbRemove() {
        Debug.print("DimensionDetailBean ejbRemove");

        try {
            deleteRow(dimensionId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       

    public void unsetEntityContext() {
         Debug.print("DimensionDetailBean unsetEntityContext");
        context = null;
    }
    

    public void ejbLoad() { 
        Debug.print("DimensionDetailBean ejbLoad");
        try {
            loadDimDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    

    public void ejbStore() {
        Debug.print("DimensionDetailBean ejbStore");

        try {
            storeDimDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     

   public String ejbFindByPrimaryKey(String dimensionId) throws FinderException {
        Debug.print("DimensionDetailBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(dimensionId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return dimensionId;
        } else {
            throw new ObjectNotFoundException("Row for id " + dimensionId + " not found.");
        }
    }
           
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
    private boolean selectByPrimaryKey(String dimensionId) throws SQLException {
        Debug.print("DimensionDetailBean selectByPrimaryKey:" + dimensionId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS  + " WHERE dimension_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, dimensionId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("DimensionDetailBean selectByPrimaryKey:" + result);
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
  * @Method Name    :getDimDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :Null.
  */
    public Collection getDimDetails(){
        Debug.print("DimensionDetailBean getDimDetails");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS +
                    " where dimension_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,dimensionId);
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
            throw new EJBException("SQL Exception in getDimDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getDimDetails:" + e.getMessage());
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
        Debug.print("DimensionDetailBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                dimList.addElement(rs.getString(1));
                Debug.print("Map Price In Find ALL:" + rs.getString(1));
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
  * @Method Name    :ejbFindByMediaTypeAndDisplayType.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId, String displaySubTypeId.
  * @return         :List value.
  * @throws         :FinderException.
  */
    public List ejbFindByMediaTypeAndDisplayType(String mediaId, String displaySubTypeId) throws FinderException{
        Debug.print("DimensionDetailBean findByMediaTypeAndDisplayType:" + displaySubTypeId);
        makeConnection();
        ArrayList subTypeList = new ArrayList();
   	try {
           // String selectStatement = "select dimension_id from " + DBHelper.USEA_ADV_DIMENSION_DETAILS + " where display_sub_type_id = ? and media_id=? " ;
          String selectStatement="SELECT dimension_id,dd.display_sub_type_id,dst.display_type_id,dimension_type_id,"+
              " media_id,dimension_name,height,width,units,image_path  "+
              " FROM tblAdvDimensionDetails dd,tblAdvDisplaySubTypes  dst "+
              " WHERE dd.display_sub_type_id = dst.display_sub_type_id AND dst.display_type_id=? and media_id=?";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("DimensionDetailBean findByDisplaySubTypeName:" + selectStatement);
            prepStmt.setString(1,displaySubTypeId);
            prepStmt.setString(2,mediaId);
            ResultSet rs = prepStmt.executeQuery();
            HLCAdvertisementVO advertisementVO = null;
            while (rs.next()){
                advertisementVO = new HLCAdvertisementVO();
                advertisementVO.setDimensionId(rs.getString(1));
		advertisementVO.setDisplaySubType(rs.getString(2));
                advertisementVO.setDisplayType(rs.getString(3));
                advertisementVO.setDimensionType(rs.getString(4));
		advertisementVO.setMediaType(rs.getString(5));
		advertisementVO.setDimensionalName(rs.getString(6));
		advertisementVO.setHeight(rs.getString(7));
		advertisementVO.setWidth(rs.getString(8));
		advertisementVO.setDimensionalUnit(rs.getString(9));
		advertisementVO.setImagePath(rs.getString(10));
                subTypeList.add(advertisementVO);
            }
            
            this.advLists = subTypeList;
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("DimensionDetailBean in findByMediaTypeAndDisplayType:" + subTypeList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByMediaTypeAndDisplayType:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByMediaTypeAndDisplayType:" + e.getMessage());
        }
      return subTypeList;
}
  
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("DimensionDetailBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("DimensionDetailBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowDimDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */
    private void insertRowDimDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("DimensionDetailBean insertRowDimDetails");
        
        this.dimensionId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_DIMENSION_DETAILS  + "(dimension_id, display_sub_type_id, dimension_type_id, media_id, dimension_name," +
                    " height, width, units, image_path) " +
                 " values ( ? , ? , ? , ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, dimensionId);
            prepStmt.setString(2, displaySubTypeId);
            prepStmt.setString(3, dimensionTypeId);
            prepStmt.setString(4, mediaId);
            prepStmt.setString(5, dimensionName);
            prepStmt.setString(6, height);
            prepStmt.setString(7, width);
            prepStmt.setString(8, units);
            prepStmt.setString(9, imagePath);
            
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowDimDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowDimDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadDimDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void loadDimDetails() throws SQLException {
        Debug.print("DimensionDetailBean loadDimDetails");
        dimensionId = (String)context.getPrimaryKey();

        Debug.print("DimensionDetailBean loadDimDetails Primary Key:" + dimensionId );
        makeConnection();
        try{
            String selectStatement =
                "select  dimension_id,display_sub_type_id, dimension_type_id, media_id, dimension_name," +
                    " height, width, units, image_path " +
                    " from " + DBHelper.USEA_ADV_DIMENSION_DETAILS  + " where dimension_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, dimensionId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.dimensionId = rs.getString(1);
                this.displaySubTypeId = rs.getString(2);
                this.dimensionTypeId = rs.getString(3);
                this.mediaId = rs.getString(4);
                this.dimensionName = rs.getString(5);
                this.height = rs.getString(6);
                this.width = rs.getString(7);
                this.units = rs.getString(8);
                this.imagePath = rs.getString(9);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + dimensionId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadDimDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadDimDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeDimDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void storeDimDetails() throws SQLException {
        Debug.print("DimensionDetailBean storeDimDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_DIMENSION_DETAILS  + " set display_sub_type_id = ? , dimension_type_id = ? , media_id = ? , dimension_name = ? , " + 
                    "  height = ?, width = ?, units = ?, image_path = ? where dimension_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, displaySubTypeId);
            prepStmt.setString(2, dimensionTypeId);
            prepStmt.setString(3, mediaId);
            prepStmt.setString(4, dimensionName);
            prepStmt.setString(5, height);
            prepStmt.setString(6, width);
            prepStmt.setString(7, units);
            prepStmt.setString(8, imagePath);
            
            prepStmt.setString(9, dimensionId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated:" + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeDimDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeDimDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String dimensionId.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void deleteRow(String dimensionId) throws SQLException {
         Debug.print("DimensionDetailBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_DIMENSION_DETAILS  + "  where dimension_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, dimensionId);
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
            Debug.print("DimensionDetailBean : makeConnection");
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
            Debug.print("DimensionDetailBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }

   
}
