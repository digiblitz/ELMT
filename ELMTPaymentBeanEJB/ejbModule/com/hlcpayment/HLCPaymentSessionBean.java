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
/*  Program Name    : PaymentSessionBean.java
 *  Created Date    : Sep 8, 2006 2:53:31 AM
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

import com.hlccommon.util.Debug;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.HLCPaymentDetailVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.DataSource;
import java.util.*;
import javax.naming.*;
import java.rmi.*;

/**
 * This is the bean class for the PaymentSessionBean enterprise bean.
 * Created Sep 8, 2006 2:53:31 AM
 * @author suresh
 */
public class HLCPaymentSessionBean implements SessionBean, HLCPaymentSessionRemoteBusiness {
    private SessionContext context;
    private InitialContext ic;
    private HLCPaymentDetailVO objPayDet;
    
    private HLCPaymentLocalHome objPayLoHome;
    private HLCPaymentLocal objPayLoRemote;
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    // </editor-fold>
    
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
/**
  * @Method Name    :ejbCreate.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :CreateException.
  */
    public void ejbCreate() throws CreateException {
        try{
            InitialContext jndiContext = getInitialContext();
            Object objPayd = jndiContext.lookup("HLCPaymentProcessBeanHomeJNDI");
            objPayLoHome = (HLCPaymentLocalHome)objPayd;
            Debug.print("JNDI Called PaymentSessionBean");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
/**
  * @Method Name    :createPayment.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :PaymentDetailVO objPayDet.
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
     public boolean createPayment(HLCPaymentDetailVO objPayDet) throws RemoteException{
        Debug.print("PaymentSessionBean createPayment");

        boolean result = false;
        try{
            Debug.print("PaymentSessionBean User Id:" + objPayDet.getUserId());
            Debug.print("objPayDet.toString() :" + objPayDet.toString());
            
            if(objPayDet.getUserId()!=null){
                objPayLoRemote = objPayLoHome.create(objPayDet);
                //Debug.print("PaymentSessionBean getUserId():" + objPayLoRemote);
                result  = true;
            }
            else{
               result  = false; 
            }
             Debug.print("PaymentSessionBean result:" + result);
        }
        catch(Exception exp){
            throw new EJBException("Create Payment: " + exp.getMessage());
        }
        return result;
      }
      public boolean updateDeclinePaymentStatus(HLCPaymentDetailVO objPaymentVO) {
        Debug.print("updateDeclinePaymentStatus VO Value():" + objPaymentVO.toString());

        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();

        try {
            String updateStatement = "update  tblUserPaymentDetails " +
                    " set  cc_name = ?, cc_type = ?, cc_number = ?, cc_exp_month = ? ," +
                    " cc_exp_year = ?, cc_cvvid = ?, bank_name = ?, check_date = ?, check_number = ?, " +
                    " check_name = ?, " +
                    " ssl_result = ? , ssl_result_message = ? ,ssl_txn_id= ?," +
                    " ssl_approval_code = ? , ssl_cvv2_response = ? , ssl_avs_response = ?, " +
                    " ssl_transaction_type = ?, ssl_invoice_no = ? , ssl_email = ?, " +
                    "  payment_status = ?, payment_date = ?, parent_payment_id=?," +
                    "  ip_address= ?, check_amount= ? where  payment_id = ?";

            prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, objPaymentVO.getCcName());
            prepStmt.setString(2, objPaymentVO.getCcType());
            // following code for changing the card no from real to dummy as per client saying in mail 13-March-2008.
            String ccNumber = objPaymentVO.getCcNumber();
            if (!(ccNumber.equals("0"))) {
                String temp = ccNumber.substring(0, 2);
                String temp1 = ccNumber.substring(2, 12);
                String temp2 = ccNumber.substring(12);
                temp1 = "***";
                ccNumber = temp + temp1 + temp2;
            }

            // code ends here for card no. change
            prepStmt.setString(3, ccNumber);
            prepStmt.setInt(4, objPaymentVO.getCcExpMonth());
            prepStmt.setInt(5, objPaymentVO.getCcExpYear());
            // DO NOT write/store ANY CVV information in the tblUserPaymentDetails.So commented. as in email 13-March-2008.

            //int ccCvvid = objPaymentVO.getCcCvvid();
            int ccCvvid = 0;
            prepStmt.setInt(6, ccCvvid);
            prepStmt.setString(7, objPaymentVO.getBankName());
            if (objPaymentVO.getCheckDate() != null) {
                prepStmt.setDate(8, DBHelper.toSQLDate(objPaymentVO.getCheckDate()));
            } else {
                prepStmt.setDate(8, null);
            }
            prepStmt.setString(9, objPaymentVO.getCheckNumber());
            prepStmt.setString(10, objPaymentVO.getCheckName());
            //prepStmt.setDouble(11, objPaymentVO.getAmount());

            prepStmt.setString(11, objPaymentVO.getSslResult());
            prepStmt.setString(12, objPaymentVO.getSslResultMessage());
            prepStmt.setString(13, objPaymentVO.getSslTxnId());
            prepStmt.setString(14, objPaymentVO.getSslApprovalCode());
            prepStmt.setString(15, objPaymentVO.getSslCvv2Response());
            prepStmt.setString(16, objPaymentVO.getSslAvsResponse());
            prepStmt.setString(17, objPaymentVO.getSslTransactionType());
            prepStmt.setString(18, objPaymentVO.getSslInvoiceNo());
            prepStmt.setString(19, objPaymentVO.getSslEmail());
            prepStmt.setString(20, objPaymentVO.getPaymentStatus());
            prepStmt.setDate(21, DBHelper.toSQLDate(new java.util.Date()));
            prepStmt.setString(22, objPaymentVO.getParentPaymentId());
            prepStmt.setString(23, objPaymentVO.getIpAddress());
            prepStmt.setFloat(24, objPaymentVO.getCheckAmount());
            prepStmt.setString(25, objPaymentVO.getPaymentId());



            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateDeclinePaymentStatus : " + cnt);
            if (cnt >= 1) {
                result = true;
            }

            Debug.print(" updateDeclinePaymentStatus Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch (SQLException sql) {
            releaseConnection();
            Debug.print("SQL Exception in .updateDeclinePaymentStatus():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection();
            Debug.print("General Exception  in updateDeclinePaymentStatus():" + e.getMessage());
        }
        return result;
    }
 /**
     * Name         :makeConnection
     * Description  :This method will Create a databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
            Debug.print(" Opening a connection...");
        } catch (Exception ex) {
            Debug.print("Unable to connect to database. " + ex.getMessage());
        }
    }

    /**
     * Name         :releaseConnection
     * Description  :This method will release the databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    private void releaseConnection() {
        try {
            //prepStmt.close();
            //rs.close();
            if (!con.isClosed()) {
                con.close();
            }
            Debug.print(" Closing a connection...");
        } catch (SQLException ex) {
            Debug.print("releaseConnection: " + ex.getMessage());
        }
    }

    
/**
  * @Method Name    :getInitialContext.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :InitialContext  value.
  * @throws         :javax.naming.NamingException.
  */
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("PaymentSessionBean Bean: This is from getInitialContext()");
        return ic;
    }
    
}
