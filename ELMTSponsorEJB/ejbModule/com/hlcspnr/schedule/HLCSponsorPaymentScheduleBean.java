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
/*  Program Name    : SponsorPaymentScheduleBean.java
 *  Created Date    : Aug 24, 2006 1:26:32 PM
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
package com.hlcspnr.schedule;

import com.hlcspnr.exception.HLCMissingPrimaryKeyException;
import com.hlcspnr.util.DBHelper;
import com.hlcspnr.util.Debug;
import com.hlcspnr.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.Date;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the SponsorPaymentScheduleBean enterprise bean.
 * Created Aug 24, 2006 1:26:32 PM
 * @author suresh
 */
public class HLCSponsorPaymentScheduleBean implements EntityBean, HLCSponsorPaymentScheduleLocalBusiness {
    private EntityContext context;
    private String scheduleId;
    private String sponsorId;
    private String scheduleTypeId;
    private Date dueDate;
    private float dueAmount;
    private boolean continueStatus;
    private String frequencyRate;
    private String paymentStatus;
    private String insertStatus;
    private Connection con;
    private Vector specialBil;
    
     public void setSpecialBill(Vector specialBil) {
         this.specialBil = specialBil;
     }
     
   
    
    public void setScheduleId(String scheduleId){
        this.scheduleId = scheduleId;
    }
    public void setSponsorId(String sponsorId){
        this.sponsorId = sponsorId;
    }
       
    public void setScheduleTypeId(String scheduleTypeId){
        this.scheduleTypeId = scheduleTypeId;
    }
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }
    
     public void setDueAmount(float dueAmount){
        this.dueAmount = dueAmount;
    }
    
    public void setContinueStatus(boolean continueStatus){
        this.continueStatus = continueStatus;
    }
    
    public void setFrequencyRate(String frequencyRate){
        this.frequencyRate = frequencyRate;
    }
    
    public void setPaymentStatus(String paymentStatus){
        this.paymentStatus = paymentStatus;
    }
    
    //getter
    
     
    public String getScheduleId(){
        return scheduleId;
    }
    public String getSponsorId(){
        return  sponsorId;
    }
    
  
    
    public String getScheduleTypeId(){
       return scheduleTypeId;
    }
    
    public Date getDueDate(){
       return dueDate;
    }
    
     public float getDueAmount(){
       return dueAmount;
    }
    
    public boolean isContinueStatus(){
        return continueStatus;
    }
   
    
    public String getFrequencyRate(){
        return frequencyRate;
    }
    
      
    public String getPaymentStatus(){
        return paymentStatus;
    }
    
     public Vector getSpecialBill(){
        return specialBil;
    }
    
    
     public String ejbCreate(String sponsorId,String scheduleTypeId,
            Date dueDate, float dueAmount , boolean continueStatus,
            String frequencyRate, String paymentStatus, Vector specialBil) throws CreateException{
            Debug.print("dueDate from ejbCreate" + dueDate); 
            this.sponsorId =sponsorId;
            this.scheduleTypeId = scheduleTypeId;
            this.dueDate = dueDate;
            Debug.print("dueDate from ejbCreate" + dueDate);        
            this.dueAmount = dueAmount;
            this.continueStatus = continueStatus;
            this.frequencyRate = frequencyRate;
            this.paymentStatus = paymentStatus;
            this.insertStatus = "insert";
            this.specialBil = specialBil;
            try {
                insertRowPaymentSchedule();
            } catch (Exception ex) {
                throw new EJBException("ejbCreate: " + ex.getMessage());
            }
            Debug.print("Primary Key: After Insertion" + scheduleId);
            return  scheduleId;
        }

    public void ejbPostCreate(String sponsorId,String scheduleTypeId,
            Date dueDate,float dueAmount , boolean continueStatus,
            String frequencyRate, String paymentStatus, Vector specialBil) throws CreateException{
        Debug.print("SponsorPaymentScheduleBean ejbPostCreate");
    }
        
          
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
     public void ejbActivate() {
        Debug.print("SponsorPaymentScheduleBean ejbActivate");
        scheduleId = (String)context.getPrimaryKey();
    }
    
     public void ejbPassivate() {
        Debug.print("SponsorPaymentScheduleBean ejbPassivate");
        scheduleId = "";   
   }
     
        public void ejbRemove() {
        Debug.print("SponsorPaymentScheduleBean ejbRemove");

        try {
            deleteRow(scheduleId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
    
   
    public void unsetEntityContext() {
        context = null;
    }
  
    public void ejbLoad() { 
        Debug.print("SponsorPaymentScheduleBean ejbLoad");
        try {
            loadScheduleDetails();
            insertStatus = null;
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("SponsorPaymentScheduleBean ejbStore: insertStatus " + insertStatus);
        try {
            if(insertStatus==null){
                storeScheduleDetails();
            }
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    public String ejbFindByPrimaryKey(String scheduleId) throws FinderException {
        Debug.print("SponsorPaymentScheduleBean ejbFindByPrimaryKey");
        boolean result;

        try {
            result = selectByPrimaryKey(scheduleId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return scheduleId;
        } else {
            throw new ObjectNotFoundException("Row for id " + scheduleId + " not found.");
        }
    }
    
     
    /*********************** Database Routines *************************/
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */
  private boolean selectByPrimaryKey(String scheduleId) throws SQLException {
        Debug.print("SponsorPaymentScheduleBean selectByPrimaryKey" + scheduleId);

        makeConnection();

        String selectStatement = "SELECT schedule_id from " + DBHelper.USEA_SPNR_SCHE_DETAILS  + " WHERE schedule_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, scheduleId);

        ResultSet rs = prepStmt.executeQuery();
        boolean result = rs.next();
        prepStmt.close();
        releaseConnection();
        Debug.print("SponsorPaymentScheduleBean selectByPrimaryKey" + result);
        return result;
    }
  
/**
  * @Method Name    :getPaymentSchedule.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleId.
  * @return         :Collection value.
  * @throws         :Null.
  */  
   public Collection getPaymentSchedule(String scheduleId){
        Debug.print("SponsorPaymentScheduleBean ejbFindAll");
   	try {
            makeConnection();
            String selectStatement = "select schedule_id, sponsor_id, sponsorship_id , schedule_type_id, due_date" +
                    " due_amount, continue_status, frequency_rate , payment_status " +
                    " from " + DBHelper.USEA_SPNR_SCHE_DETAILS +
                    " where schedule_id = ? order by due_date";
              
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,scheduleId);
            ResultSet rs = prepStmt.executeQuery();

            Vector scheduleList = new Vector();
            while (rs.next()){
                scheduleList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            return scheduleList;
        }
        
        catch (SQLException e) {
            throw new EJBException("Could not find any from schedule ist");
        }
        finally{
            releaseConnection();
        }
}
  
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */   
   public Collection ejbFindByAll() throws FinderException {
        Debug.print("SponsorPaymentScheduleBean ejbFindAll");
        makeConnection();
   	try {
            String selectStatement = "select schedule_id from " + DBHelper.USEA_SPNR_SCHE_DETAILS ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            Vector scheduleList = new Vector();
            while (rs.next()){
                scheduleList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            return scheduleList;
        } 
        catch (SQLException e) {
            throw new EJBException("Could not find any from schedule List");
        }
        finally{
            releaseConnection();
        }
}
 /*    public Collection ejbfindBySponsorTypeId(String sponsorId) throws FinderException{
        Debug.print("SponsorPaymentScheduleBean findBySponsorTypeId");
        makeConnection();
        try {
            String selectStatement = "select schedule_id from " + DBHelper.USEA_SPNR_SCHE_DETAILS + " where sponsor_id" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,sponsorId);
            ResultSet rs = prepStmt.executeQuery();

            Vector scheduleList = new Vector();
            while (rs.next()){
                scheduleList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            return scheduleList;
        } 
        catch (SQLException e) {
            throw new EJBException("Could not find any from sponsor Schedule");
        }
        finally{
            releaseConnection();
        }
     }
   
  *
  */
   /*  public Collection ejbFindByScheduleId(String scheduleId) throws FinderException {
        Debug.print("SponsorPaymentScheduleBean ejbFindByPlanId");
        makeConnection();
   	try {
            String selectStatement = "select schedule_id from " + DBHelper.USEA_SPNR_SCHE_DETAILS + " where schedule_id = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,scheduleId);
            ResultSet rs = prepStmt.executeQuery();

            Vector scheduleList = new Vector();
            while (rs.next()){
                scheduleList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            return scheduleList;
        } 
        catch (SQLException e) {
            throw new EJBException("Could not find any from sponsor Schedule");
        }
        finally{
            releaseConnection();
        }
}*/
     
/**
  * @Method Name    :ejbFindBySponsorId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
    public Collection ejbFindBySponsorId(String sponsorId) throws FinderException {
        Debug.print("SponsorPaymentScheduleBean ejbFindBySponsorId");
        makeConnection();
   	try {
            String selectStatement = "select schedule_id from " + DBHelper.USEA_SPNR_SCHE_DETAILS + " where sponsor_id = ? order by due_date " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,sponsorId);
            ResultSet rs = prepStmt.executeQuery();
            ArrayList scheduleList = new ArrayList();
            while (rs.next()){
                scheduleList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            return scheduleList;
        } 
        catch (SQLException e) {
            throw new EJBException("Could not find any from sponsor Id");
        }
        finally{
            releaseConnection();
        }
}
   
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */    
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("SponsorPaymentScheduleBean getNextId");
        makeConnection();
       
        String selectStatement = "select newid() as scheduleId";
        Debug.print("SponsorPaymentScheduleBean getNextId:" + selectStatement);
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        ResultSet rs = prepSelect.executeQuery();
        rs.next();
        String nextId = rs.getString(1);
        rs.close();
        prepSelect.close();
        releaseConnection();
        Debug.print("SponsorPaymentScheduleBean NEWID:" + nextId);
        return nextId;
    }
    
/**
  * @Method Name    :getNextRecurring.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :int interval,Date date.
  * @return         :Date value.
  * @throws         :SQLException ,MissingPrimaryKeyException.
  */    
        private Date getNextRecurring(int interval,Date date) throws SQLException ,HLCMissingPrimaryKeyException {
            Debug.print("SponsorPaymentScheduleBean getNextRecurring");
            makeConnection();
            try{
                String selectStatement = "SELECT DATEADD(month, " + interval + ", '" + DBHelper.toSQLDate(date) + "' ) AS TimeFrame";
                Debug.print("SponsorPaymentScheduleBean getNextRecurring:" + selectStatement);
                PreparedStatement prepSelect = con.prepareStatement(selectStatement);
                ResultSet rs = prepSelect.executeQuery();
                rs.next();
                Date nextRecurring = rs.getDate(1);
                rs.close();
                prepSelect.close();
                releaseConnection();
                Debug.print("SponsorPaymentScheduleBean getNextRecurring:" + nextRecurring);
                return nextRecurring;
            }
            catch (SQLException e) {
                releaseConnection();
                throw new EJBException("Could not find any from getNextRecurring");
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in getNextRecurring:" + e.getMessage());
            }       
        }
    
/**
  * @Method Name    :getScheduleType.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleTypeId.
  * @return         :String value.
  * @throws         :SQLException.
  */    
   private String getScheduleType(String scheduleTypeId) throws SQLException  {
        Debug.print("SponsorPaymentScheduleBean getScheduleType" + scheduleTypeId);
        makeConnection();
       
        String selectStatement = "select schedule_type_name from " + DBHelper.USEA_SPNR_SCHE_MASTER + " where schedule_type_id = ? " ;
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, scheduleTypeId);
         Debug.print("SponsorPaymentScheduleBean getScheduleType" + scheduleTypeId);
        ResultSet rs = prepSelect.executeQuery();
        rs.next();
        String scheduleTypeName = rs.getString(1);
        rs.close();
        prepSelect.close();
        releaseConnection();
        return scheduleTypeName;
    }
        
  
/**
  * @Method Name    :insertRowPaymentSchedule.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */   
    private void insertRowPaymentSchedule() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("SponsorPaymentScheduleBean insertRowPaymentSchedule");
        
        String insertStatement =
                        "insert into " + DBHelper.USEA_SPNR_SCHE_DETAILS  + "( schedule_id , sponsor_id,  schedule_type_id, due_date , " +
                            " due_amount, continue_status, frequency_rate , payment_status) " +
                         " values (  ? , ? , ? , ? , ? , ? , ? , ?)";
        Debug.print("SponsorPaymentScheduleBean scheduleTypeId:" + scheduleTypeId);
        
        String ScheduleTypeName = getScheduleType(scheduleTypeId);
        
        Debug.print("SponsorPaymentScheduleBean ScheduleTypeName:" + ScheduleTypeName);
        Debug.print("dueDate from insert method" + dueDate); 
        try{
            if(ScheduleTypeName.trim().equals("Recurring")){
                Debug.print("This Recurring:" + ScheduleTypeName);
                int   fRate = Integer.parseInt(frequencyRate);
                Date chkDate = dueDate;
                Debug.print("SponsorPaymentScheduleBean chkDate:" + chkDate);
                float dAmount = 0;
                dAmount = dueAmount/fRate;
                Debug.print("Install Amount:" + dAmount);
                Debug.print("frequencyRate:" + fRate);
                int dateCal = 12/fRate;
                 Debug.print("Month Increment:" + dateCal);
                for(int i = 1; i<=fRate; i++){
                    Debug.print("Install Amount:" + dAmount);
                    this.scheduleId = getNextId();
                    chkDate =  getNextRecurring(dateCal,chkDate);
                    Debug.print("This date From Recurring After getNextRecurring Calling:" + dueDate + "" + dateCal);
                    makeConnection();
                    PreparedStatement prepStmt = con.prepareStatement(insertStatement);
                    Debug.print("Next Due:" + chkDate);
                    prepStmt.setString(1, scheduleId);
                    prepStmt.setString(2, sponsorId);
                    prepStmt.setString(3, scheduleTypeId);
                    Debug.print("This date From Recurring Billing:" + dueDate);
                    Debug.print("This date From Recurring dAmount:" + dAmount);
                    prepStmt.setDate(4, DBHelper.toSQLDate(chkDate));
                    prepStmt.setFloat(5, dAmount);
                    prepStmt.setBoolean(6, false);
                    prepStmt.setString(7, frequencyRate);
                    prepStmt.setString(8, "Pending");
                    prepStmt.executeUpdate();
                    prepStmt.close();
                    releaseConnection();
                }
            }
            else if(ScheduleTypeName.trim().equals("Specific Billing")){
                if(specialBil!=null){
                    Enumeration e = specialBil.elements();
                    while(e.hasMoreElements()){
                        ArrayList  specialBillList = (ArrayList) e.nextElement();
                        Iterator it = specialBillList.iterator();
                        if(it.hasNext()){
                            dueAmount = ((Float)it.next()).floatValue();
                            dueDate = (Date)it.next();
                        }
                        Debug.print("Install Amount:" + dueAmount);
                        this.scheduleId = getNextId();
                        makeConnection();
                        PreparedStatement prepStmt = con.prepareStatement(insertStatement);    
                        prepStmt.setString(1, scheduleId);
                        prepStmt.setString(2, sponsorId);
                        prepStmt.setString(3, scheduleTypeId);
                        Debug.print("This date From Specific Billing:" + DBHelper.toSQLDate(dueDate));
                        prepStmt.setDate(4, DBHelper.toSQLDate(dueDate));
                        prepStmt.setFloat(5, dueAmount);
                        Debug.print("This date From Specific Billing dueAmount:" + dueAmount);
                        prepStmt.setBoolean(6, false);
                        prepStmt.setString(7, frequencyRate);
                        prepStmt.setString(8, "Pending");
                        prepStmt.executeUpdate();
                        prepStmt.close();
                        releaseConnection();
                    }
                }
            }
            else if(ScheduleTypeName.trim().equals("Manual")){
                this.scheduleId = getNextId();
                makeConnection();
                PreparedStatement prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, scheduleId);
                prepStmt.setString(2, sponsorId);
                prepStmt.setString(3, scheduleTypeId);
                Debug.print("This date From Manual:" + dueDate);
                Debug.print("This date From Manual:" + DBHelper.toSQLDate(dueDate));
                prepStmt.setDate(4, DBHelper.toSQLDate(dueDate));
                prepStmt.setFloat(5, dueAmount);
                Debug.print("This date From Specific Billing dueAmount:" + dueAmount);
                prepStmt.setBoolean(6, false);
                prepStmt.setString(7, null);
                prepStmt.setString(8, "Pending");
                prepStmt.executeUpdate();
                releaseConnection();
            }
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from insertRowPaymentSchedule");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPaymentSchedule:" + e.getMessage());
        }       
    }

/**
  * @Method Name    :loadScheduleDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */      
    private void loadScheduleDetails() throws SQLException {
        Debug.print("SponsorPaymentScheduleBean loadScheduleDetails" + scheduleId);
        scheduleId = (String)context.getPrimaryKey();
        makeConnection();
        try{
            String selectStatement =
                "select  sponsor_id, schedule_type_id, due_date," +
                " due_amount, continue_status, frequency_rate , payment_status  from " + 
                DBHelper.USEA_SPNR_SCHE_DETAILS  + " where schedule_id = ? order by due_date ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, scheduleId);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                this.sponsorId =rs.getString(1);
                this.scheduleTypeId = rs.getString(2);
                this.dueDate = (java.util.Date)rs.getDate(3);
                Debug.print("========== scheduleTypeId:" + scheduleTypeId);
                this.dueAmount = rs.getFloat(4);
                Debug.print("========== rs.getFloat(4):" + rs.getFloat(4));
                Debug.print("========== dueAmount:" + dueAmount);
                this.continueStatus = rs.getBoolean(5);
                this.frequencyRate = rs.getString(6);
                this.paymentStatus = rs.getString(7);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + scheduleId + " not found in database.");
            }
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from insertRowPaymentSchedule");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPaymentSchedule:" + e.getMessage());
        }      
    }

/**
  * @Method Name    :storeScheduleDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void storeScheduleDetails() throws SQLException {
        Debug.print("SponsorPaymentScheduleBean storeScheduleDetails");

        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_SPNR_SCHE_DETAILS  + " set sponsor_id = ? ,  " +
                    " schedule_type_id = ? , due_date = ? , due_amount = ? , continue_status = ? , " + 
                     " frequency_rate = ? , payment_status = ? " + 
                    " where schedule_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);


            prepStmt.setString(1, sponsorId);
            prepStmt.setString(2, scheduleTypeId);
            if(dueDate == null){
                prepStmt.setDate(3, null);
            }
            else{
                prepStmt.setDate(3, DBHelper.toSQLDate(dueDate));
            }

            prepStmt.setFloat(4, dueAmount);
            prepStmt.setBoolean(5, continueStatus);
            prepStmt.setString(6, frequencyRate);
            prepStmt.setString(7, paymentStatus);
            prepStmt.setString(8, scheduleId);

            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from storeScheduleDetails");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeScheduleDetails:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleId.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void deleteRow(String scheduleId) throws SQLException {
        Debug.print("SponsorPaymentScheduleBean deleteRow");
        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_SPNR_SCHE_DETAILS  + "  where schedule_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, scheduleId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from deleteRow");
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
            Debug.print("SponsorPaymentScheduleBean : makeConnection");
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
            Debug.print("SponsorPaymentScheduleBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
