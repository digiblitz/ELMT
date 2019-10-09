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
/*  Program Name    : MapPriceBean.java
 *  Created Date    : Aug 28, 2006 3:18:20 PM
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
 * This is the bean class for the MapPriceBean enterprise bean.
 * Created Aug 28, 2006 3:18:20 PM
 * @author suresh
 */
public class HLCMapPriceBean implements EntityBean, HLCMapPriceLocalBusiness {
    private EntityContext context;
    private String advMapId;
    private String displayTypeId;
    private String displaySubTypeId;
    private String frequencyId;
    private String mediaId;
    private String price;
    
    private Connection con;
    
    public void setAdvMapId(String advMapId){
        this.advMapId = advMapId;
    }
    
    public void setDisplayTypeId(String displayTypeId){
        this.displayTypeId = displayTypeId;
    }

    public void setDisplaySubTypeId(String displaySubTypeId){
        this.displaySubTypeId = displaySubTypeId;
    }
    
    public void setFrequencyId(String frequencyId){
         this.frequencyId = frequencyId;
    }

    public void setMediaId(String mediaId){
         this.mediaId = mediaId;
    }
    
    public void setPrice(String price){
         this.price = price;
    }
    
    public String getAdvMapId(){
         return advMapId;
    }
    
    public String getDisplayTypeId(){
         return displayTypeId;
    }
    
    public String getDisplaySubTypeId(){
         return displaySubTypeId;
    }
   
    public String getFrequencyId(){
         return frequencyId;
    }

    public String getMediaId(){
         return mediaId;
    }
    
    public String getPrice(){
         return price;
    }
    
     public String ejbCreate(String displayTypeId , String displaySubTypeId, String frequencyId, String mediaId, String price) throws CreateException{
        Debug.print("MapPriceBean ejbCreate");   
        this.displayTypeId = displayTypeId;
        this.displaySubTypeId = displaySubTypeId;
        this.frequencyId = frequencyId;
        this.mediaId = mediaId;
        this.price = price;
        if(price==null){
            this.price = "0.00";
        }
        
        try {
            insertRowMapPrice();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + advMapId);
        return  advMapId;
    }

    public void ejbPostCreate(String displayTypeId , String displaySubTypeId, String frequencyId, String mediaId, String price) throws CreateException{
        Debug.print("MapPriceBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("MapPriceBean ejbActivate");
        displaySubTypeId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("MapPriceBean ejbPassivate");
        displaySubTypeId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("MapPriceBean ejbRemove");

        try {
            deleteRow(displaySubTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("MapPriceBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("MapPriceBean ejbLoad");
        try {
            loadMapPriceDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("MapPriceBean ejbStore");

        try {
            storeMapPriceDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String advMapId) throws FinderException {
        Debug.print("MapPriceBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(advMapId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return advMapId;
        } else {
            throw new ObjectNotFoundException("Row for id " + advMapId + " not found.");
        }
    }
           
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advMapId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */   
    private boolean selectByPrimaryKey(String advMapId) throws SQLException {
        Debug.print("MapPriceBean selectByPrimaryKey:" + advMapId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT adv_map_id from " + DBHelper.USEA_ADV_MAP_PRICE  + " WHERE adv_map_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, advMapId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("MapPriceBean selectByPrimaryKey:" + result);
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
  * @Method Name    :getMapPrice.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :Null.
  */       
    public Collection getMapPrice(){
        Debug.print("MapPriceBean getMapPrice");
        Vector mapPriceList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_MAP_PRICE +
                    " where display_sub_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,advMapId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mapPriceList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getMapPrice:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getMapPrice:" + e.getMessage());
        }
        return mapPriceList;
}
  
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */     
    public Collection ejbFindByAll() throws FinderException {
        Debug.print("MapPriceBean ejbFindAll");
        Vector mapPriceList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select adv_map_id from " + DBHelper.USEA_ADV_MAP_PRICE ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mapPriceList.addElement(rs.getString(1));
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
        return mapPriceList;
}
    
/**
  * @Method Name    :ejbFindByMapDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId,String frequencyId,String displayTypeId, String displaySubTypeId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */       
    public Collection ejbFindByMapDetails(String mediaId,String frequencyId,String displayTypeId, String displaySubTypeId) throws FinderException{
        Debug.print("AdvertiseBean ejbFindByMapDetails" + mediaId);
        makeConnection();
        ArrayList mapList = new ArrayList();
   	try {
            String selectStatement = "select adv_map_id from " + DBHelper.USEA_ADV_MAP_PRICE + " where media_id  = ? and  frequency_id  = ?  and  display_sub_type_id  = ? and  display_type_id  = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
            prepStmt.setString(2,frequencyId);
            prepStmt.setString(3,displaySubTypeId);
            prepStmt.setString(4,displayTypeId);
            
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mapList.add(rs.getString(1));
                Debug.print("AdvertiseBean ejbFindByMapDetails ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByMapDetails");
        }
         return mapList;
    }    
    
/**
  * @Method Name    :ejbFindByMapMediaDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId,String displayTypeId, String displaySubTypeId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
    public Collection ejbFindByMapMediaDetails(String mediaId, String displayTypeId, String displaySubTypeId) throws FinderException {
        Debug.print("AdvertiseBean ejbFindByMapMediaDetails" + mediaId);
        makeConnection();
        ArrayList mapList = new ArrayList();
   	try {
            String selectStatement = "select adv_map_id from " + DBHelper.USEA_ADV_MAP_PRICE + " where media_id  = ? and  display_sub_type_id  = ?  and  display_type_id  = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
            prepStmt.setString(2,displaySubTypeId);
            prepStmt.setString(3,displayTypeId);
            
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mapList.add(rs.getString(1));
                Debug.print("AdvertiseBean ejbFindByMapMediaDetails ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByMapDetails");
        }
         return mapList;
    }   
    
/*
    public Collection ejbFindByDisplaySubTypeName(String displaySubTypeName) throws FinderException{
        Debug.print("MapPriceBean findByDisplaySubTypeName:" + displaySubTypeName);
        makeConnection();
        ArrayList subTypeList = new ArrayList();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_MAP_PRICE + " where display_sub_type_name = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("MapPriceBean findByDisplaySubTypeName:" + selectStatement);
            prepStmt.setString(1,displaySubTypeName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                subTypeList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("MapPriceBean in findByDisplaySubTypeName:" + subTypeList);
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
  */
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */     
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("MapPriceBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("MapPriceBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowMapPrice.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */       
    private void insertRowMapPrice() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("MapPriceBean insertRowMapPrice");
        
        this.advMapId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_MAP_PRICE  + "(adv_map_id, display_type_id, display_sub_type_id, frequency_id, media_id, price) " +
                 " values ( ? , ? , ? , ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, advMapId);
            prepStmt.setString(2, displayTypeId);
            prepStmt.setString(3, displaySubTypeId);
            prepStmt.setString(4, frequencyId);
            prepStmt.setString(5, mediaId);
            prepStmt.setFloat(6, Float.parseFloat(price));
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowMapPrice:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowMapPrice:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadMapPriceDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void loadMapPriceDetails() throws SQLException {
        Debug.print("MapPriceBean loadMapPriceDetails");
        advMapId = (String)context.getPrimaryKey();

        Debug.print("MapPriceBean loadMapPriceDetails Primary Key:" + advMapId );
        makeConnection();
        try{
            String selectStatement =
                "select display_type_id, display_sub_type_id, frequency_id, media_id, price from " + DBHelper.USEA_ADV_MAP_PRICE  + " where adv_map_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, advMapId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.displayTypeId = rs.getString(1);
                this.displaySubTypeId = rs.getString(2);
                this.frequencyId = rs.getString(3);
                this.mediaId = rs.getString(4);
                this.price = rs.getString(5);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + advMapId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadMapPriceDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadMapPriceDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeMapPriceDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void storeMapPriceDetails() throws SQLException {
        Debug.print("MapPriceBean storeMapPriceDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_MAP_PRICE  + " set display_type_id = ? , display_sub_type_id = ? , frequency_id = ? , media_id = ? , " + 
                    "  price = ? where adv_map_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, displayTypeId);
            prepStmt.setString(2, displaySubTypeId);
            prepStmt.setString(3, frequencyId);
            prepStmt.setString(4, mediaId);
            prepStmt.setFloat(5, Float.parseFloat(price));
            prepStmt.setString(6, advMapId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated:" + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeMapPriceDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeMapPriceDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advMapId.
  * @return         :void value.
  * @throws         :SQLException.
  */      
    private void deleteRow(String advMapId) throws SQLException {
         Debug.print("MapPriceBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_MAP_PRICE  + "  where adv_map_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, advMapId);
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
            Debug.print("MapPriceBean : makeConnection");
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
            Debug.print("MapPriceBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
    
    
}