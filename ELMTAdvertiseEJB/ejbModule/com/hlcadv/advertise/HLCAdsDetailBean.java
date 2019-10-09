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
/*  Program Name    : AdsDetailBean.java
 *  Created Date    : Aug 29, 2006 12:16:25 PM
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

package com.hlcadv.advertise;

import com.hlccommon.exception.HLCMissingPrimaryKeyException;
import com.hlccommon.util.HLCAdvertiseDetVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the AdsDetailBean enterprise bean.
 * Created Aug 29, 2006 12:16:25 PM
 * @author suresh
 */

public class HLCAdsDetailBean implements EntityBean, HLCAdsDetailLocalBusiness {
    private EntityContext context;
    private String advDetailId;
    private String advertisementId;
    private String issueId;
    private String advMapId;
    private String dimensionId;
    private String placement;
    private String discount;
    private String amount;        
    
    private Connection con;
 
    
    public HLCAdvertiseDetVO getAdvertiseDetVO(){
       return new HLCAdvertiseDetVO(advDetailId, advertisementId, issueId, advMapId, dimensionId, placement, discount, amount);
    }
    
     public void setAdvDetailId(String advDetailId){
        this.advDetailId = advDetailId;
    }
    
    public void setAdvertisementId(String advertisementId){
        this.advertisementId = advertisementId;
    }
    
    public void setIssueId(String issueId){
        this.issueId = issueId;
    }
    
    public void setAdvMapId(String advMapId){
         this.advMapId = advMapId;
    }

    public void setDimensionId(String dimensionId){
         this.dimensionId = dimensionId;
    }
    
    public void setPlacement(String placement) {
        this.placement = placement;
    }
    
    public void setDiscount(String discount){
         this.discount = discount;
    }
    
    public void setAmount(String amount){
        this.amount = amount;
    }
     
    

    
    
    public String getAdvDetailId(){
         return advDetailId;
    }
    
    public String getAdvertisementId(){
         return advertisementId;
    }
    
    public String getIssueId(){
         return issueId;
    }
    
    public String getAdvMapId(){
         return advMapId;
    }
    
    public String getDimensionId(){
         return dimensionId;
    }
    public String getPlacement() {
        return placement;
    }
   
    public String getDiscount(){
         return discount;
    }
    
    public String getAmount(){
         return amount;
    }
 
     public String ejbCreate(String advertisementId , String issueId, String advMapId, String dimensionId, String placement , String discount,String  amount) throws CreateException{
        Debug.print("AdsDetailBean ejbCreate");   
        this.advertisementId = advertisementId;
        this.issueId = issueId;
        this.advMapId = advMapId;
        this.dimensionId = dimensionId;
        this.placement = placement;
        this.discount = discount;
        this.amount = amount;
        
        if(amount==null){
            this.amount = "0.00";
        }
        if(discount==null){
            this.discount = "0.00";
        }
        
        try {
            insertRowAdsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + advDetailId);
        return  advDetailId;
    }


    public void ejbPostCreate(String advertisementId , String issueId, String advMapId, String dimensionId, String placement, String discount,String  amount) throws CreateException{        Debug.print("AdsDetailBean ejbPostCreate");
    }
   

    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    

    public void ejbActivate() {
        Debug.print("AdsDetailBean ejbActivate");
        advDetailId = (String)context.getPrimaryKey();
    }
    

    public void ejbPassivate() {
        Debug.print("AdsDetailBean ejbPassivate");
        advDetailId = "";   
    }
    

    public void ejbRemove() {
        Debug.print("AdsDetailBean ejbRemove");

        try {
            deleteRow(advDetailId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       

    public void unsetEntityContext() {
         Debug.print("AdsDetailBean unsetEntityContext");
        context = null;
    }
    

    public void ejbLoad() { 
        Debug.print("AdsDetailBean ejbLoad");
        try {
            loadAdsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    

    public void ejbStore() {
        Debug.print("AdsDetailBean ejbStore");

        try {
            storeAdsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     

   public String ejbFindByPrimaryKey(String advDetailId) throws FinderException {
        Debug.print("AdsDetailBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(advDetailId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return advDetailId;
        } else {
            throw new ObjectNotFoundException("Row for id " + advDetailId + " not found.");
        }
    }
           
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advDetailId.
  * @return         :boolean  value.
  * @throws         :SQLException.
  */
    private boolean selectByPrimaryKey(String advDetailId) throws SQLException {
        Debug.print("AdsDetailBean selectByPrimaryKey:" + advDetailId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT adv_detail_id from " + DBHelper.USEA_ADV_ADS_DETAILS  + " WHERE adv_detail_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, advDetailId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("AdsDetailBean selectByPrimaryKey:" + result);
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
  
  /*  public Collection getMapPrice(){
        Debug.print("AdsDetailBean getMapPrice");
        Vector mapPriceList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select display_sub_type_id from " + DBHelper.USEA_ADV_ADS_DETAILS +
                    " where display_sub_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,advDetailId);
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
  */

/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByAll() throws FinderException {
        Debug.print("AdsDetailBean ejbFindAll");
        Vector mapPriceList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select adv_detail_id from " + DBHelper.USEA_ADV_ADS_DETAILS ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mapPriceList.addElement(rs.getString(1));
                Debug.print("AdsDetailBean In Find ALL:" + rs.getString(1));
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
  * @Method Name    :ejbFindByAdsId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advertisementId.
  * @return         :Collection  value.
  * @throws         :FinderException.
  */
    public Collection ejbFindByAdsId(String advertisementId) throws FinderException{
        Debug.print("AdsDetailBean findByAdsId:" + advertisementId);
        makeConnection();
        ArrayList AdsDetList = new ArrayList();
   	try {
            String selectStatement = "select adv_detail_id from " + DBHelper.USEA_ADV_ADS_DETAILS + " where advertisement_id = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("AdsDetailBean findByAdsId:" + selectStatement);
            prepStmt.setString(1,advertisementId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                AdsDetList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("AdsDetailBean in findByAdsId:" + AdsDetList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByAdsId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByAdsId:" + e.getMessage());
        }
        return AdsDetList;
}
 
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advertisementId.
  * @return         :String  value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("AdsDetailBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("AdsDetailBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowAdsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private void insertRowAdsDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("AdsDetailBean insertRowAdsDetails" + advertisementId);
        
        this.advDetailId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_ADS_DETAILS  + "(adv_detail_id, advertisement_id, issue_id, adv_map_id, dimension_id, placement, discount, amount) " +
                 " values ( ? , ? , ? , ?, ?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, advDetailId);
            prepStmt.setString(2, advertisementId);
            prepStmt.setString(3, issueId);
            prepStmt.setString(4, advMapId);
            prepStmt.setString(5, dimensionId);
            prepStmt.setString(6, placement);
            prepStmt.setFloat(7, Float.parseFloat(discount));
            prepStmt.setFloat(8, Float.parseFloat(amount));
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowAdsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowAdsDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadAdsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :SQLException.
  */
    private void loadAdsDetails() throws SQLException {
        Debug.print("AdsDetailBean loadAdsDetails");
        advDetailId = (String)context.getPrimaryKey();

        Debug.print("AdsDetailBean loadAdsDetails Primary Key:" + advDetailId );
        makeConnection();
        try{
            String selectStatement =
                "select  advertisement_id, issue_id, adv_map_id, dimension_id, placement, discount, amount from " + DBHelper.USEA_ADV_ADS_DETAILS  + " where adv_detail_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, advDetailId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.advertisementId = rs.getString(1);
                this.issueId = rs.getString(2);
                this.advMapId = rs.getString(3);
                this.dimensionId = rs.getString(4);
                this.placement = rs.getString(5);
                this.discount = rs.getString(6);
                this.amount = rs.getString(7);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + advDetailId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadAdsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadAdsDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeAdsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :SQLException.
  */
    private void storeAdsDetails() throws SQLException {
        Debug.print("AdsDetailBean storeAdsDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_ADS_DETAILS  + " set advertisement_id = ? , issue_id = ? , adv_map_id = ? , dimension_id = ? , " + 
                    "  placement = ? ,discount = ?, amount = ?  where adv_detail_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        
            
            
            prepStmt.setString(1, advertisementId);
            prepStmt.setString(2, issueId);
            prepStmt.setString(3, advMapId);
            prepStmt.setString(4, dimensionId);
            prepStmt.setString(5, placement);
            prepStmt.setFloat(6, Float.parseFloat(discount));
            prepStmt.setFloat(7, Float.parseFloat(amount));
            prepStmt.setString(8, advDetailId);

            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated:" + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeAdsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeAdsDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advDetailId.
  * @return         :void  value.
  * @throws         :SQLException.
  */
    private void deleteRow(String advDetailId) throws SQLException {
         Debug.print("AdsDetailBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ADV_ADS_DETAILS  + "  where adv_detail_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, advDetailId);
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
            Debug.print("AdsDetailBean : makeConnection");
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
            Debug.print("AdsDetailBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }

   
  
}
