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
package com.hlcpayment;

import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import com.hlccommon.util.HLCPaymentDetailVO;
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
    private String ccNumber;
    private int ccExpMonth;
    private int ccExpYear;
    private int ccCvvid;
    private String bankName;
    private Date checkDate;
    private String checkNumber;
    private String checkName;
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
    private float checkAmount;
    private String parentPaymentId;
    private String ipAddress;
    private float pendingAmount;

    public String getInVoiceID() {
        return inVoiceID;
    }

    public void setInVoiceID(String inVoiceID) {
        this.inVoiceID = inVoiceID;
    }

    public String getPpAuthorizationID() {
        return ppAuthorizationID;
    }

    public void setPpAuthorizationID(String ppAuthorizationID) {
        this.ppAuthorizationID = ppAuthorizationID;
    }

    public String getPpCorrelationID() {
        return ppCorrelationID;
    }

    public void setPpCorrelationID(String ppCorrelationID) {
        this.ppCorrelationID = ppCorrelationID;
    }

    public String getPpExchangeRate() {
        return ppExchangeRate;
    }

    public void setPpExchangeRate(String ppExchangeRate) {
        this.ppExchangeRate = ppExchangeRate;
    }

    public double getPpFeeAmt() {
        return ppFeeAmt;
    }

    public void setPpFeeAmt(double ppFeeAmt) {
        this.ppFeeAmt = ppFeeAmt;
    }

    public String getPpParentTransactionID() {
        return ppParentTransactionID;
    }

    public void setPpParentTransactionID(String ppParentTransactionID) {
        this.ppParentTransactionID = ppParentTransactionID;
    }

    public String getPpPaymentStatus() {
        return ppPaymentStatus;
    }

    public void setPpPaymentStatus(String ppPaymentStatus) {
        this.ppPaymentStatus = ppPaymentStatus;
    }

    public String getPpPaymentType() {
        return ppPaymentType;
    }

    public void setPpPaymentType(String ppPaymentType) {
        this.ppPaymentType = ppPaymentType;
    }

    public String getPpPendingReason() {
        return ppPendingReason;
    }

    public void setPpPendingReason(String ppPendingReason) {
        this.ppPendingReason = ppPendingReason;
    }

    public String getPpReasonCode() {
        return ppReasonCode;
    }

    public void setPpReasonCode(String ppReasonCode) {
        this.ppReasonCode = ppReasonCode;
    }

    public double getPpSettleAmt() {
        return ppSettleAmt;
    }

    public void setPpSettleAmt(double ppSettleAmt) {
        this.ppSettleAmt = ppSettleAmt;
    }

    public double getPpTaxAmt() {
        return ppTaxAmt;
    }

    public void setPpTaxAmt(double ppTaxAmt) {
        this.ppTaxAmt = ppTaxAmt;
    }
    private String ppAuthorizationID;
    private String ppPaymentType;
    private double ppFeeAmt;
    private double ppSettleAmt;
    private double ppTaxAmt;
    private String ppExchangeRate;
    private String ppPaymentStatus;
    private String ppCorrelationID;
    private String ppParentTransactionID;
    private String ppPendingReason;
    private String ppReasonCode;
    private String inVoiceID; 
    
/**
  * @Method Name    :ejbCregetPaymentDetailVOate.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :PaymentDetailVO value.
  * @throws         :Null.
  */    
    public HLCPaymentDetailVO getPaymentDetailVO(){
        Debug.print("PaymentBean getPaymentDetails");        
        return  new HLCPaymentDetailVO(paymentId, userId, ccName,
            ccType, ccNumber, ccExpMonth, ccExpYear,
            ccCvvid,  bankName, checkDate, checkNumber, checkName, amount,
            paymentDate, paymentStatus, sslResult, 
                sslResultMessage,  sslTxnId, sslApprovalCode,
                sslCvv2Response, sslAvsResponse, sslTransactionType, 
                sslInvoiceNo, sslEmail, ppPaymentType,ppFeeAmt,ppSettleAmt,ppTaxAmt,
                ppExchangeRate,ppPaymentStatus,ppPendingReason,
                ppReasonCode,ppAuthorizationID, ppCorrelationID,inVoiceID,ppParentTransactionID);
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
        public void setCcNumber(String ccNumber) {
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
        public void setCheckNumber(String checkNumber) {
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
        
        
    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    
     public void setCheckAmount(float checkAmount) {
        this.checkAmount = checkAmount;
    }
     
      public void setParentPaymentId(String parentPaymentId) {
        this.parentPaymentId = parentPaymentId;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

  
   

    public void setPendingAmount(float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

  
        
     public String ejbCreate(HLCPaymentDetailVO objPayDet) throws CreateException{
        Debug.print("PaymentBean ejbCreate"); 
        if(objPayDet==null){
                throw new EJBException("ejbCreate: objPayDet arg is null or empty");
        }
        
      //  if(objPayDet.getPaymentId()==null){
            this.paymentId = objPayDet.getPaymentId();
      // }
        
            this.userId = objPayDet.getUserId();
            Debug.print("PaymentBean ejbCreate User ID:" + this.userId);
            this.ccName = objPayDet.getCcName();
            this.ccType = objPayDet.getCcType();
            this.ccNumber = objPayDet.getCcNumber();
            // following code for changing the card no from real to dummy as per client saying in mail 13-March-2008
            if (!(this.ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
            }
            Debug.print(ccNumber+"=testing ccnumber");
            // code ends here for card no. change
            
            this.ccExpMonth = objPayDet.getCcExpMonth();
            this.ccExpYear = objPayDet.getCcExpYear();
            // DO NOT write/store ANY CVV information in the tblUserPaymentDetails.So commented. 13-March-2008.
            
            //this.ccCvvid = objPayDet.getCcCvvid();
            
            this.bankName = objPayDet.getBankName();
            this.checkDate =objPayDet.getCheckDate();
            this.checkNumber = objPayDet.getCheckNumber();
            this.checkName = objPayDet.getCheckName();
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
            this.checkAmount = objPayDet.getCheckAmount();
            this.parentPaymentId = objPayDet.getParentPaymentId();
            this.ipAddress = objPayDet.getIpAddress();
            this.pendingAmount = objPayDet.getPendingAmount();
            this.inVoiceID=objPayDet.getInVoiceID();
            this.paymentStatus=objPayDet.getPaymentStatus();
            this.ppAuthorizationID=objPayDet.getPpAuthorizationID();
            this.ppCorrelationID=objPayDet.getPpCorrelationID();
            this.ppFeeAmt=objPayDet.getPpFeeAmt();
            this.ppReasonCode=objPayDet.getPpReasonCode();
            this.ppParentTransactionID=objPayDet.getPpParentTransactionID();
            this.ppFeeAmt=objPayDet.getPpFeeAmt();
            this.ppPaymentType=objPayDet.getPpPaymentType();
        
        try {
            insertRowPayment();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
     public void ejbPostCreate(HLCPaymentDetailVO objPayDet){
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
            loadPayment();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("PaymentBean ejbStore");

        try {
            storePayment();
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
        try{
            String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, check_name, amount, payment_status, " +
                    " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                    " ssl_transaction_type, ssl_invoice_no, ssl_email,check_amount,payment_id, pending_amount, parent_payment_id, ip_address,invoice_id,authorization_id,correlation_id,paypal_payment_status,fee_amount,payment_type,reason_code,parent_transaction_id)" +
                      " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ? , ? ,?,?,?,?,?,?,?,?)";

            PreparedStatement prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, userId);
            prepStmt.setString(2, ccName);
            prepStmt.setString(3, ccType);
            prepStmt.setString(4, ccNumber);
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
            
            prepStmt.setString(10, checkNumber);
            prepStmt.setString(11, checkName);
            prepStmt.setDouble(12, amount);
            //prepStmt.setDate(13, DBHelper.toSQLDate(new Date()));
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
            prepStmt.setFloat(23, checkAmount);
            prepStmt.setString(24, paymentId);
            prepStmt.setFloat(25, pendingAmount);
            prepStmt.setString(26, parentPaymentId);
            prepStmt.setString(27, ipAddress);
            prepStmt.setString(28,inVoiceID);
            prepStmt.setString(29,ppAuthorizationID);
            prepStmt.setString(30,ppCorrelationID);
            prepStmt.setString(31,ppPaymentStatus);
            prepStmt.setDouble(32,ppFeeAmt);
            prepStmt.setString(33,ppPaymentType);
            prepStmt.setString(34,ppReasonCode);
            prepStmt.setString(35,ppParentTransactionID);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("PaymentBean Sucessfully insertRowPayment");

        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowPayment:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPayment:" + e.getMessage());
        }
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
  * @Method Name    :loadPayment.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */        
        private void loadPayment() throws SQLException {
            Debug.print("PaymentBean loadPayment");
            makeConnection();
            try{
                String selectStatement =
                        "select user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                        " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, check_name, amount, payment_date, payment_status " +
                        " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                        " ssl_transaction_type, ssl_invoice_no, ssl_email, check_amount, pending_amount, parent_payment_id, ip_address " +
                        " from " + DBHelper.USEA_PAYMENT + " where payment_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);

                prepStmt.setString(1, paymentId);

                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()) {
                    this.userId = rs.getString(1);
                    this.ccName = rs.getString(2);
                    this.ccType = rs.getString(3);
                    this.ccNumber = rs.getString(4);
                    this.ccExpMonth = rs.getInt(5);
                    this.ccExpYear = rs.getInt(6);
                    this.ccCvvid = rs.getInt(7);
                    this.bankName = rs.getString(8);
                    this.checkDate =rs.getDate(9);
                    this.checkNumber = rs.getString(10);      
                    this.checkName = rs.getString(11);
                    this.amount = rs.getDouble(12);
                    this.paymentDate = rs.getDate(13);
                    this.paymentStatus = rs.getString(14);
                    
                    this.sslResult = rs.getString(15);
                    this.sslResultMessage = rs.getString(16);
                    this.sslTxnId = rs.getString(17);
                    this.sslApprovalCode = rs.getString(18);
                    this.sslCvv2Response = rs.getString(19);
                    this.sslAvsResponse = rs.getString(20);
                    this.sslTransactionType = rs.getString(20);
                    this.sslInvoiceNo =  rs.getString(21);
                    this.sslEmail = rs.getString(22);
                    this.checkAmount = rs.getFloat(23);
                    this.pendingAmount = rs.getFloat(24);
                    this.parentPaymentId = rs.getString (25);
                    this.ipAddress = rs.getString(26);
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
                throw new EJBException("SQL Exception in loadPayment:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in loadPayment:" + e.getMessage());
            }
        }
        
/**
  * @Method Name    :storePayment.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */        
        private void storePayment() throws SQLException {
        Debug.print("PaymentBean storePayment");

        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_PAYMENT + " set user_id = ? ,cc_name = ? ,cc_type = ?, cc_number = ?, cc_exp_month = ? ," +
                        " cc_exp_year = ?, cc_cvvid= ?, bank_name= ?, check_date = ?, check_number = ?, amount = ?, payment_date = ?," +
                        " payment_status = ? ssl_result = ?, ssl_result_message = ?, ssl_txn_id = ? , ssl_approval_code = ?, " +
                        " ssl_cvv2_response = ?, ssl_avs_response = ?, " +
                        " ssl_transaction_type = ?, ssl_invoice_no = ?, ssl_email = ? , check_name = ?, check_amount = ?, " +
                        " pending_amount = ?, parent_payment_id = ?, ip_address = ? " +
                        " where payment_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, userId);
            prepStmt.setString(2, ccName);
            prepStmt.setString(3, ccType);
            prepStmt.setString(4, ccNumber);
            prepStmt.setInt(5, ccExpMonth);
            prepStmt.setInt(6, ccExpYear);
            prepStmt.setInt(7, ccCvvid);
            prepStmt.setString(8, bankName);
            prepStmt.setDate(9, DBHelper.toSQLDate(checkDate));
            prepStmt.setString(10, checkNumber);
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
            prepStmt.setString(23, checkName);
            prepStmt.setFloat(24, checkAmount);
            prepStmt.setFloat(25, pendingAmount);
            prepStmt.setString(26, parentPaymentId);
            prepStmt.setString(27, ipAddress);
            prepStmt.setString(28, paymentId);
            
            int rowCount = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
               if (rowCount == 0) {
                    throw new EJBException("Storing row for id " + paymentId +  " failed.");
                }
         }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storePayment:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storePayment:" + e.getMessage());
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
