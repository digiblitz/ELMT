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
/*  Program Name    : RefundRuleDetailBean.java
 *  Created Date    : Aug 29, 2006 7:31:11 PM
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
 * This is the bean class for the RefundRuleDetailBean enterprise bean.
 * Created Aug 29, 2006 7:31:11 PM
 * @author suresh
 */
public class HLCRefundRuleDetailBean implements EntityBean, HLCRefundRuleDetailLocalBusiness {
    private EntityContext context;
    private String refundId;
    private String eventId;
    private String refundMapId;
    private String amount;
    private Connection con;
    
    //getter
    public String getRefundId () {
        return refundId ;
    }
    public String getEventId() {
        return eventId;
    }
    public String getRefundMapId() {
        return refundMapId;
    }
    public String getAmount() {
        return amount;
    }

    //setter
    public void setRefundId (String refundId) {
        this.refundId = refundId;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId ;
    }
    public void setRefundMapId(String refundMapId) {
        this.refundMapId = refundMapId;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
   
    public String ejbCreate(String eventId,String refundMapId,String amount) throws CreateException{
        Debug.print("RefundRuleDetailBean ejbCreate");
        this.eventId = eventId;
        this.refundMapId = refundMapId;
        this.amount = amount;
        try {
            insertRowRefundRuleDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + refundId);
        return  refundId;
    }
    
    public void ejbPostCreate(String eventId,String refundMapId,String amount) throws CreateException{
        Debug.print("RefundRuleDetailBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("RefundRuleDetailBean ejbActivate");
        refundId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("RefundRuleDetailBean ejbPassivate");
        refundId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("RefundRuleDetailBean ejbRemove");

        try {
            deleteRow(refundId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("RefundRuleDetailBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("RefundRuleDetailBean ejbLoad");
        try {
            loadRefundRuleDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("RefundRuleDetailBean ejbStore");

        try {
            storeRefundRuleDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String refundId) throws FinderException {
        Debug.print("RefundRuleDetailBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(refundId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return refundId;
        } else {
            throw new ObjectNotFoundException("Row for id " + refundId + " not found.");
        }
    }
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String refundId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String refundId) throws SQLException {
        Debug.print("RefundRuleDetailBean selectByPrimaryKey:" + refundId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT refund_id from " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS  + " WHERE refund_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, refundId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("RefundRuleDetailBean selectByPrimaryKey" + result);
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
        Debug.print("RefundRuleDetailBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select refund_id from " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS ;
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
         Debug.print("RefundRuleDetailBean ejbFindByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = " select C.refund_id, C.event_id, A.refund_rule_type_name, B.refund_rule_sub_type_name, C.refund_map_id, C.amount from " +
                    DBHelper.USEA_MRO_REFUND_RULE_MASTER + " A, " + DBHelper.USEA_MRO_REFUND_SUB_RULE_SUB_MASTER + " B, " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS + " C, " +
                    DBHelper.USEA_MRO_MAP_REFUND_RULE + " D where C.refund_map_id = D.refund_map_id and " +
                    " D.refund_rule_type_id = A.refund_rule_type_id and D.refund_rule_sub_type_id = B.refund_rule_sub_type_id " +
                    " and  C.event_id = ?";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setLong(1,Long.parseLong(eventId));
            Debug.print("RefundRuleDetailBean ejbFindByEventId Query:" + selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String refundId = rs.getString(1);
                String eventIdVal = rs.getString(2);
                String refundRuleTypeName = rs.getString(3);
                String refundRuleSubTypeName = rs.getString(4);
                String refundMapId = rs.getString(5);
                String amount = rs.getString(6);
                String refundList [] = {refundId,eventIdVal,refundRuleTypeName,refundRuleSubTypeName,refundMapId,amount};
                eventList.addElement(refundList);
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
  * @Method Name    :ejbFindByEventId1.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */  
  public Collection ejbFindByEventId1(String eventId) throws FinderException{
         Debug.print("RefundRuleDetailBean ejbFindByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = " select refund_id FROM "+ DBHelper.USEA_MRO_REFUND_RULE_DETAILS + 
                    " where event_id = ?";            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setLong(1,Long.parseLong(eventId));
            Debug.print("RefundRuleDetailBean ejbFindByEventId1 Query:" + selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String refundId = rs.getString(1);                
                eventList.addElement(refundId);
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByEventId1:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByEventId1:" + e.getMessage());
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
        Debug.print("RefundRuleDetailBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as refundId";
            Debug.print("RefundRuleDetailBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowRefundRuleDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */   
  private void insertRowRefundRuleDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("RefundRuleDetailBean insertRowRefundRuleDetails");
        
        this.refundId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS  + "(refund_id, event_id, refund_map_id, amount) " +
                 " values ( ? , ? , ? , ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, refundId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
            prepStmt.setString(3, refundMapId);
            if(amount==null || amount.trim().length()==0)
                amount = "0";
            prepStmt.setFloat(4, Float.parseFloat(amount));
          
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowRefundRuleDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowRefundRuleDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadRefundRuleDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void loadRefundRuleDetails() throws SQLException {
        Debug.print("RefundRuleDetailBean loadRefundRuleDetails");
        refundId = (String)context.getPrimaryKey();
        Debug.print("RefundRuleDetailBean loadRefundRuleDetails Primary Key:" + refundId );
        makeConnection();
        try{
            String selectStatement =
                "select  event_id, refund_map_id, amount from " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS  + " where refund_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, refundId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.eventId = rs.getString(1);
                this.refundMapId = rs.getString(2);
                this.amount = rs.getString(3);

                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + refundId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadRefundRuleDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadRefundRuleDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeRefundRuleDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void storeRefundRuleDetails() throws SQLException {
        Debug.print("RefundRuleDetailBean storeRefundRuleDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS  + " set event_id = ? , refund_map_id = ? ," + 
                    " amount = ? where refund_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setInt(1, Integer.parseInt(eventId));
            prepStmt.setString(2, refundMapId);
            prepStmt.setFloat(3, Float.parseFloat(amount));
            prepStmt.setString(4, refundId);

            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeRefundRuleDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeRefundRuleDetails:" + e.getMessage());
        }        
    }
   
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String refundId.
  * @return         :void value.
  * @throws         :SQLException.
  */    
  private void deleteRow(String refundId) throws SQLException {
         Debug.print("RefundRuleDetailBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_REFUND_RULE_DETAILS  + "  where refund_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, refundId);
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
            Debug.print("RefundRuleDetailBean : makeConnection");
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
            Debug.print("RefundRuleDetailBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
  }
}
