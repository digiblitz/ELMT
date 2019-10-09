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
/*  Program Name    : MediaMasterBean.java
 *  Created Date    : Aug 26, 2006 1:17:55 PM
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
 * This is the bean class for the MediaMasterBean enterprise bean.
 * Created Aug 26, 2006 1:17:55 PM
 * @author suresh
 */
public class HLCMediaMasterBean implements EntityBean, HLCMediaMasterLocalBusiness {
    private EntityContext context;
    private String mediaId;
    private String mediaName;
    private String mediaDescription;
    private boolean activeStatus;
    private Connection con;
    
    
     public void setMediaId(String mediaId){
        this.mediaId = mediaId;
    }
     
    public void setMediaName(String mediaName){
        this.mediaName = mediaName;
    }
         
    public void setMediaDescription(String mediaDescription){
        this.mediaDescription = mediaDescription;
    }

    public void setActiveStatus(boolean activeStatus){
        this.activeStatus = activeStatus;
    }
    
  
    public String getMediaId(){
        return mediaId;
    }
    
    public String getMediaName(){
        return mediaName;
    }
        
    public String getMediaDescription(){
        return mediaDescription;
    }
        
    public boolean isActiveStatus(){
        return activeStatus;
    }
    
  
  public String ejbCreate(String mediaName , String mediaDescription) throws CreateException{
    Debug.print("MediaMasterBean ejbCreate");   
      this.mediaName =mediaName;
      this.mediaDescription = mediaDescription;
      
        try {
            insertRowMedia();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + mediaId);
        return  mediaId;
    }

    public void ejbPostCreate(String mediaName , String mediaDescription) throws CreateException{
        Debug.print("MediaMasterBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("MediaMasterBean ejbActivate");
        mediaId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("MediaMasterBean ejbPassivate");
        mediaId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("MediaMasterBean ejbRemove");

        try {
            deleteRow(mediaId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("MediaMasterBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("MediaMasterBean ejbLoad");
        try {
            loadMediaDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("MediaMasterBean ejbStore");

        try {
            storeMediaDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String mediaId) throws FinderException {
        Debug.print("MediaMasterBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(mediaId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return mediaId;
        } else {
            throw new ObjectNotFoundException("Row for id " + mediaId + " not found.");
        }
    }
        
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String mediaId) throws SQLException {
        Debug.print("MediaMasterBean selectByPrimaryKey:" + mediaId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT media_id from " + DBHelper.USEA_ADV_MEDIA  + " WHERE media_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, mediaId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("MediaMasterBean selectByPrimaryKey" + result);
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
  * @Method Name    :getMedia.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :Null.
  */  
   public Collection getMedia(){
        Debug.print("MediaMasterBean getMedia");
        Vector mediaList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select media_id, media_name, media_description, active_status from " + DBHelper.USEA_ADV_MEDIA +
                    " where media_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
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
            throw new EJBException("SQL Exception in getMedia:" + sql.getMessage());
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
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
   public Collection ejbFindByAll() throws FinderException {
        Debug.print("MediaMasterBean ejbFindAll");
        Vector mediaList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select media_id from " + DBHelper.USEA_ADV_MEDIA + " order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mediaList.addElement(rs.getString(1));
                Debug.print("Media ID In Find ALL:" + rs.getString(1));
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
        return mediaList;
}

/**
  * @Method Name    :ejbFindByMediaName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */     
    public Collection ejbFindByMediaName(String mediaName) throws FinderException{
        Debug.print("MediaMasterBean ejbFindByMediaName:" + mediaName);
        makeConnection();
        ArrayList mediaList = new ArrayList();
   	try {
            String selectStatement = "select media_id from " + DBHelper.USEA_ADV_MEDIA + " where media_name = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("MediaMasterBean ejbFindByMediaName:" + selectStatement);
            prepStmt.setString(1,mediaName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mediaList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("MediaMasterBean in ejbFindByMediaName:" + mediaList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByMediaName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByMediaName:" + e.getMessage());
        }
        return mediaList;
}
    
/**
  * @Method Name    :ejbFindByEditMediaName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId, String mediaName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */     
     public Collection ejbFindByEditMediaName(String mediaId, String mediaName) throws FinderException{
        Debug.print("MediaMasterBean ejbFindByEditMediaName:" + mediaName);
        makeConnection();
        ArrayList mediaList = new ArrayList();
   	try {
            String selectStatement = "select media_id from " + DBHelper.USEA_ADV_MEDIA + " where media_name = ? and media_id !=?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("MediaMasterBean ejbFindByEditMediaName:" + selectStatement);
            prepStmt.setString(1,mediaName);
            prepStmt.setString(2,mediaId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mediaList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("MediaMasterBean in ejbFindByEditMediaName:" + mediaList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByEditMediaName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByEditMediaName:" + e.getMessage());
        }
        return mediaList;
}
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */     
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("MediaMasterBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("MediaMasterBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowMedia.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */    
    private void insertRowMedia() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("MediaMasterBean insertRowMedia");
        
        this.mediaId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_MEDIA  + "(media_id, media_name, media_description) " +
                 " values ( ? , ? ,  ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, mediaId);
            prepStmt.setString(2, mediaName);
            prepStmt.setString(3, mediaDescription);
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
  * @Method Name    :loadMediaDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */      
    private void loadMediaDetails() throws SQLException {
        Debug.print("MediaMasterBean loadMediaDetails");
        mediaId = (String)context.getPrimaryKey();

        Debug.print("MediaMasterBean loadMediaDetails Primary Key:" + mediaId );
        makeConnection();
        try{
            String selectStatement =
                "select  media_name, media_description, active_status from " + DBHelper.USEA_ADV_MEDIA  + " where media_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, mediaId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.mediaName = rs.getString(1);
                this.mediaDescription = rs.getString(2);
                this.activeStatus = rs.getBoolean(3);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + mediaId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadMediaDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadMediaDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeMediaDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */        
    private void storeMediaDetails() throws SQLException {
        Debug.print("MediaMasterBean storeMediaDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_MEDIA  + " set media_name = ? , media_description = ? , active_status = ? " + 
                    " where media_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, mediaName);
            prepStmt.setString(2, mediaDescription);
            prepStmt.setBoolean(3, activeStatus);
            prepStmt.setString(4, mediaId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeMediaDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeMediaDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void deleteRow(String mediaId) throws SQLException {
         Debug.print("MediaMasterBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_MEDIA  + "  where media_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, mediaId);
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
            Debug.print("MediaMasterBean : makeConnection");
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
            Debug.print("MediaMasterBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
