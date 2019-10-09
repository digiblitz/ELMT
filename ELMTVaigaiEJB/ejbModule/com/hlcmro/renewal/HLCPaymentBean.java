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
/*  Program Name    : PaymentBean.java
 *  Created Date    : Aug 23, 2006 11:21:10 AM
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
package com.hlcmro.renewal;

import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import com.hlcmro.util.HLCPaymentDetails;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;

/**
 * This is the bean class for the PaymentBean enterprise bean.
 * Created Aug 23, 2006 11:21:10 AM
 * @author suresh
 */
public class HLCPaymentBean implements EntityBean, HLCPaymentLocalBusiness {
    private EntityContext context;
    private String paymentId;
    private String userId;
    private String ccName;
    private String ccType;
    private long ccNumber;
    private int ccExpMonth;
    private int ccExpYear;
    private int ccCvvid;
    private String bankName;
    private Date checkDate;
    private long checkNumber;
    private double amount;
    private Date paymentDate;
    private String paymentStatus;
    private String sslResult;
    private String sslResultMessage;
    private String sslTxnId;
    private String sslApprovalCode;
    private String sslCvv2Response;
    private String sslAvsResponse;
    private String sslTransactionType;
    private String sslInvoiceNo;
    private String sslEmail;
    private Connection con;
    
    
    public HLCPaymentDetails getPaymentDetails(){
        Debug.print("PaymentBean getPaymentDetails");        
        return  new HLCPaymentDetails(paymentId, userId, ccName,
            ccType, ccNumber, ccExpMonth, ccExpYear,
            ccCvvid,  bankName, checkDate, checkNumber, amount,
            paymentDate, paymentStatus, sslResult, 
                sslResultMessage,  sslTxnId, sslApprovalCode,
                sslCvv2Response, sslAvsResponse, sslTransactionType, 
                 sslInvoiceNo, sslEmail);
    }
    
      public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public void setCcName(String ccName) {
            this.ccName = ccName;
        }
        public void setCcType(String ccType) {
            this.ccType = ccType;
        }
        public void setCcNumber(long ccNumber) {
            this.ccNumber = ccNumber;
        }
        public void setCcExpMonth(int ccExpMonth) {
            this.ccExpMonth = ccExpMonth;
        }
        public void setCcExpYear(int ccExpYear) {
            this.ccExpYear = ccExpYear;
        }
        public void setCcCvvid(int ccCvvid) {
            this.ccCvvid = ccCvvid;
        }
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
        public void setCheckDate(Date checkDate) {
            this.checkDate = checkDate;
        }
        public void setCheckNumber(long checkNumber) {
            this.checkNumber = checkNumber;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
        public void setPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
        }
        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
        public void setSslResult(String sslResult) {
            this.sslResult  = sslResult;
        }

        public void setSslResultMessage(String sslResultMessage) {
            this.sslResultMessage = sslResultMessage;
        }

        public void setSslTxnId(String sslTxnId) {
            this.sslTxnId = sslTxnId;
        }

        public void setSslApprovalCode(String sslApprovalCode) {
            this.sslApprovalCode = sslApprovalCode;
        }

        public void setSslCvv2Response(String sslCvv2Response) {
            this.sslCvv2Response = sslCvv2Response;
        }

        public void setSslAvsResponse(String sslAvsResponse) {
            this.sslAvsResponse = sslAvsResponse;
        }

        public void setSslTransactionType(String sslTransactionType) {
            this.sslTransactionType = sslTransactionType;
        }

        public void setSslInvoiceNo(String sslInvoiceNo) {
            this.sslInvoiceNo = sslInvoiceNo;
        }

        public void setSslEmail(String sslEmail) {
            this.sslEmail = sslEmail;
        }
        
        
        
        
     public String ejbCreate(HLCPaymentDetails objPayDet) throws CreateException{
        Debug.print("PaymentBean ejbCreate"); 
        if(objPayDet==null){
                throw new EJBException("ejbCreate: objPayDet arg is null or empty");
        }
        
       // this.paymentId = objRenewalDet.getPaymentId();
        this.userId = objPayDet.getUserId();
        this.ccName = objPayDet.getCcName();
        this.ccType = objPayDet.getCcType();
        this.ccNumber = objPayDet.getCcNumber();
        this.ccExpMonth = objPayDet.getCcExpMonth();
        this.ccExpYear = objPayDet.getCcExpYear();
        this.ccCvvid = objPayDet.getCcCvvid();
        this.bankName = objPayDet.getBankName();
        this.checkDate =objPayDet.getCheckDate();
        this.checkNumber = objPayDet.getCheckNumber();        
        this.amount = objPayDet.getAmount();
        this.paymentDate = objPayDet.getPaymentDate();
        this.paymentStatus = objPayDet.getPaymentStatus();
        
        this.sslResult = objPayDet.getSslResult();
        this.sslResultMessage = objPayDet.getSslResultMessage();
        this.sslTxnId = objPayDet.getSslTxnId();
        this.sslApprovalCode = objPayDet.getSslApprovalCode();
        this.sslCvv2Response = objPayDet.getSslCvv2Response();
        this.sslAvsResponse = objPayDet.getSslAvsResponse();
        this.sslTransactionType = objPayDet.getSslTransactionType();
        this.sslInvoiceNo =  objPayDet.getSslInvoiceNo();
        this.sslEmail = objPayDet.getSslEmail();
        
        try {
            insertRowPayment();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
     public void ejbPostCreate(HLCPaymentDetails objPayDet){
        Debug.print("PaymentBean ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String paymentId) throws FinderException {
        Debug.print("PaymentBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(paymentId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("PaymentBean ejbFindByPrimaryKey ID:" + paymentId);
            return paymentId;
        } else {
            throw new ObjectNotFoundException("Row for id " + paymentId + " not found.");
        }
    }
     
    
    public void setEntityContext(EntityContext aContext) {
        Debug.print("PaymentBean setEntityContext");
        context = aContext;
    }
    
    public void ejbActivate() {
         Debug.print("PaymentBean ejbActivate");
        paymentId = (String) context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("PaymentBean ejbPassivate");
        paymentId = null;
    }
        
    public void ejbRemove() {
        
    }
    
    public void unsetEntityContext() {
        context = null;
    }
    
     public void ejbLoad() {
        Debug.print("PaymentBean ejbLoad");

        try {
            loadRenewal();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("PaymentBean ejbStore");

        try {
            storeRenewal();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }

    
    
    /*********************** Database Routines *************************/
/**
  * @Method Name    :insertRowPayment.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */        
        private void insertRowPayment() throws SQLException {
        Debug.print("PaymentBean insertRowPayment");

        makeConnection();
        String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status, " +
                    " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                    " ssl_transaction_type, ssl_invoice_no, ssl_email)" +
                      " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, userId);
            prepStmt.setString(2, ccName);
            prepStmt.setString(3, ccType);
            prepStmt.setLong(4, ccNumber);
            prepStmt.setInt(5, ccExpMonth);
            prepStmt.setInt(6, ccExpYear);
            prepStmt.setInt(7, ccCvvid);
            prepStmt.setString(8, bankName);
            if(checkDate!=null){
                prepStmt.setDate(9, DBHelper.toSQLDate(checkDate));
            }
            else{
                prepStmt.setDate(9, null);
            }
            
            prepStmt.setLong(10, checkNumber);
            prepStmt.setDouble(11, amount);
            prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(13, paymentStatus);
            
            prepStmt.setString(14, sslResult);
            prepStmt.setString(15, sslResultMessage);
            prepStmt.setString(16, sslTxnId);
            prepStmt.setString(17, sslApprovalCode);
            prepStmt.setString(18, sslCvv2Response);
            prepStmt.setString(19, sslAvsResponse);
            prepStmt.setString(20, sslTransactionType);
            prepStmt.setString(21, sslInvoiceNo);
            prepStmt.setString(22, sslEmail);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }

/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String paymentId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */         
        private boolean selectByPrimaryKey(String paymentId) throws SQLException {
            Debug.print("PaymentBean selectByPrimaryKey");

            makeConnection();

            String selectStatement = "SELECT payment_id from " + DBHelper.USEA_PAYMENT + " WHERE payment_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, paymentId);

            ResultSet rs = prepStmt.executeQuery();
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return true;
        }

/**
  * @Method Name    :loadRenewal.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */       
        private void loadRenewal() throws SQLException {
            Debug.print("PaymentBean loadRenewal");
            makeConnection();
            try{
                String selectStatement =
                        "select user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                        " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status " +
                        " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                        " ssl_transaction_type, ssl_invoice_no, ssl_email" +
                        " from " + DBHelper.USEA_PAYMENT + " where payment_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);

                prepStmt.setString(1, paymentId);

                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()) {
                    this.userId = rs.getString(1);
                    this.ccName = rs.getString(2);
                    this.ccType = rs.getString(3);
                    this.ccNumber = rs.getLong(4);
                    this.ccExpMonth = rs.getInt(5);
                    this.ccExpYear = rs.getInt(6);
                    this.ccCvvid = rs.getInt(7);
                    this.bankName = rs.getString(8);
                    this.checkDate =rs.getDate(9);
                    this.checkNumber = rs.getLong(10);        
                    this.amount = rs.getDouble(11);
                    this.paymentDate = rs.getDate(12);
                    this.paymentStatus = rs.getString(13);
                    
                    this.sslResult = rs.getString(14);
                    this.sslResultMessage = rs.getString(15);
                    this.sslTxnId = rs.getString(16);
                    this.sslApprovalCode = rs.getString(17);
                    this.sslCvv2Response = rs.getString(18);
                    this.sslAvsResponse = rs.getString(19);
                    this.sslTransactionType = rs.getString(20);
                    this.sslInvoiceNo =  rs.getString(21);
                    this.sslEmail = rs.getString(22);
                    prepStmt.close();
                    releaseConnection();
            }
                else {
            prepStmt.close();
            releaseConnection();
            throw new NoSuchEntityException("Row for id " + paymentId + " not found in database.");
            }
            }
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in loadRenewal:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in loadRenewal:" + e.getMessage());
            }
        }
        
/**
  * @Method Name    :storeRenewal.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */         
        private void storeRenewal() throws SQLException {
        Debug.print("PaymentBean storeCustomer");

        makeConnection();
         String updateStatement =
                    "update " + DBHelper.USEA_PAYMENT + " set user_id = ? ,cc_name = ? ,cc_type = ?, cc_number = ?, cc_exp_month = ? ," +
                        " cc_exp_year = ?, cc_cvvid= ?, bank_name= ?, check_date = ?, check_number = ?, amount = ?, payment_date = ?," +
                        " payment_status = ? ssl_result = ?, ssl_result_message = ?, ssl_txn_id = ? , ssl_approval_code = ?, " +
                        " ssl_cvv2_response = ?, ssl_avs_response = ?, " +
                        " ssl_transaction_type = ?, ssl_invoice_no = ?, ssl_email = ? where payment_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, userId);
            prepStmt.setString(2, ccName);
            prepStmt.setString(3, ccType);
            prepStmt.setLong(4, ccNumber);
            prepStmt.setInt(5, ccExpMonth);
            prepStmt.setInt(6, ccExpYear);
            prepStmt.setInt(7, ccCvvid);
            prepStmt.setString(8, bankName);
            prepStmt.setDate(9, DBHelper.toSQLDate(checkDate));
            prepStmt.setLong(10, checkNumber);
            prepStmt.setDouble(11, amount);
            prepStmt.setDate(12, DBHelper.toSQLDate(paymentDate));
            prepStmt.setString(13, paymentStatus);

            prepStmt.setString(14, sslResult);
            prepStmt.setString(15, sslResultMessage);
            prepStmt.setString(16, sslTxnId);
            prepStmt.setString(17, sslApprovalCode);
            prepStmt.setString(18, sslCvv2Response);
            prepStmt.setString(19, sslAvsResponse);
            prepStmt.setString(20, sslTransactionType);
            prepStmt.setString(21, sslInvoiceNo);
            prepStmt.setString(22, sslEmail);

             prepStmt.setString(23, paymentId);
        
        int rowCount = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        
        if (rowCount == 0) {
            throw new EJBException("Storing row for id " + paymentId +  " failed.");
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
            Debug.print("PaymentBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }
         // makeConnection

/**
  * @Method Name    :releaseConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
     private void releaseConnection() {
            Debug.print("PaymentBean releaseConnection");
            try {
                if(con!=null){
                    con.close();
                }
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
         // releaseConnection

}
