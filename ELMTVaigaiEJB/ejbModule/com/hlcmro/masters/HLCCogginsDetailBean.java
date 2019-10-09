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
/*  Program Name    : CogginsDetailBean.java
 *  Created Date    : Aug 29, 2006 7:30:39 PM
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
 * This is the bean class for the CogginsDetailBean enterprise bean.
 * Created Aug 29, 2006 7:30:39 PM
 * @author suresh
 */
public class HLCCogginsDetailBean implements EntityBean, HLCCogginsDetailLocalBusiness {
    private EntityContext context;
    private String cogginsId;
    private String eventId;
    private String allState;
    private String inState;
    private String outOfState;
    private String noState;
    private String others;
    private Connection con;
    
  //getter

    public String getCogginsId() {
        return cogginsId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getAllState() {
        return allState;
    }

    public String getInState() {
            return inState;
    }

    public String getOutOfState() {
            return outOfState;
    }

    public String getNoState() {
            return noState;
    }

    public String getOthers() {
            return others;
    }

 //Setters methods

    public void setCogginsId(String cogginsId) {
        this.cogginsId = cogginsId;
    }

    public void setEventId(String eventId)  {
        this.eventId = eventId;
    }

    public void setAllState(String allState) {
        this.allState = allState ;
    }
    public void setInState(String inState)   {
        this.inState = inState ;
    }
    public void setOutOfState(String outOfState) {
       this.outOfState = outOfState ;
    }
    public void setNoState(String noState) {
       this.noState = noState;
    }
    public void setOther(String others) {
       this.others = others;
    }
    
   
    public String ejbCreate(String eventId,String allState,
	        String inState,String outOfState,String noState,String others) throws CreateException{
        Debug.print("CogginsDetailBean ejbCreate");
        this.eventId = eventId;
        this.allState = allState;
        this.inState =  inState;
        this.outOfState = outOfState;
        this.noState =  noState;
        this.others = others;
        try {
            insertRowCogginsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + cogginsId);
        return  cogginsId;
    }
    
    public void ejbPostCreate(String eventId,String allState,
	        String inState,String outOfState,String noState,String others) throws CreateException{
        Debug.print("CogginsDetailBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("CogginsDetailBean ejbActivate");
        cogginsId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("CogginsDetailBean ejbPassivate");
        cogginsId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("CogginsDetailBean ejbRemove");

        try {
            deleteRow(cogginsId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("CogginsDetailBean unsetEntityContext");
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("CogginsDetailBean ejbLoad");
        try {
            loadCogginsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("CogginsDetailBean ejbStore");

        try {
            storeCogginsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String cogginsId) throws FinderException {
        Debug.print("CogginsDetailBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(cogginsId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return cogginsId;
        } else {
            throw new ObjectNotFoundException("Row for id " + cogginsId + " not found.");
        }
    }
       /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String cogginsId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String eventId) throws SQLException {
        Debug.print("CogginsDetailBean selectByPrimaryKey:" + cogginsId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT coggins_id from " + DBHelper.USEA_MRO_COGGINS_DETAILS  + " WHERE event_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, eventId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("CogginsDetailBean selectByPrimaryKey" + result);
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
        Debug.print("CogginsDetailBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select coggins_id from " + DBHelper.USEA_MRO_COGGINS_DETAILS ;
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
         Debug.print("CogginsDetailBean ejbFindByEventId");
        Vector eventList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select coggins_id from " + DBHelper.USEA_MRO_COGGINS_DETAILS + " where event_id = ?";
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
  * @return         :Collection value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("CogginsDetailBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as CogginsID";
            Debug.print("CogginsDetailBean getNextId:" + selectStatement);
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
  * @Method Name    :insertRowCogginsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */  
  private void insertRowCogginsDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("CogginsDetailBean insertRowCogginsDetails");
        
        this.cogginsId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MRO_COGGINS_DETAILS  + "(coggins_id, event_id, all_state, in_state, out_of_state, no_state, others) " +
                 " values ( ? , ? , ? , ?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, cogginsId);
            prepStmt.setInt(2, Integer.parseInt(eventId));
            prepStmt.setString(3, allState);
            prepStmt.setString(4, inState);
            prepStmt.setString(5, outOfState);
            prepStmt.setString(6, noState);
            prepStmt.setString(7, others);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowCogginsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowCogginsDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :loadCogginsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void loadCogginsDetails() throws SQLException {
        Debug.print("CogginsDetailBean loadCogginsDetails");
        cogginsId = (String)context.getPrimaryKey();

        Debug.print("CogginsDetailBean loadCogginsDetails Primary Key:" + cogginsId );
        makeConnection();
        try{
            String selectStatement =
                "select  coggins_id,event_id, all_state, in_state, out_of_state, no_state, others from " + DBHelper.USEA_MRO_COGGINS_DETAILS  + " where coggins_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, cogginsId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.cogginsId = rs.getString("coggins_id");
                this.eventId = rs.getString("event_id");
                this.allState = rs.getString("all_state");
                this.inState = rs.getString("in_state");
                this.outOfState = rs.getString("out_of_state");
                this.noState =  rs.getString("no_state");
                this.others = rs.getString("others");

                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + cogginsId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadCogginsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadCogginsDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeCogginsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */   
  private void storeCogginsDetails() throws SQLException {
        Debug.print("CogginsDetailBean storeCogginsDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MRO_COGGINS_DETAILS  + " set event_id = ? , all_state = ?, " + 
                    " in_state = ?, out_of_state = ?, no_state = ? , others = ?  where coggins_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
           
            prepStmt.setInt(1, Integer.parseInt(eventId));
            prepStmt.setString(2, allState);
            prepStmt.setString(3, inState);
            prepStmt.setString(4, outOfState);
            prepStmt.setString(5, noState);
            prepStmt.setString(6, others);
            prepStmt.setString(7, cogginsId);
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeCogginsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeCogginsDetails:" + e.getMessage());
        }        
    }
   
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String cogginsId.
  * @return         :void value.
  * @throws         :SQLException.
  */  
  private void deleteRow(String cogginsId) throws SQLException {
         Debug.print("CogginsDetailBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_MRO_COGGINS_DETAILS  + "  where coggins_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, cogginsId);
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
            Debug.print("CogginsDetailBean : makeConnection");
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
            Debug.print("CogginsDetailBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
  }
}
