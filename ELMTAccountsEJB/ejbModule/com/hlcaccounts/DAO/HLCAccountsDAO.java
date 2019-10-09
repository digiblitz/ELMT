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
/*  Program Name    : AccountsDAO.java
 *  Created         : May 13, 2007, 12:50 PM
 *  Author          : Karthieyan.M
 *  Version         : 1.00
 *  CopyrightInformation:
(c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
916 W. Broad Street Suite 205, FallsChurch, VA 22046.
This document is protected by copyright. No part of this document may be reproduced in any form by any means without
prior written authorization of Sun and its licensors. if any.
The information described in this document may be protected by one or more U.S.patents.foreign patents,or
pending applications.
 */
package com.hlcaccounts.DAO;

import com.hlcaccounts.util.*;

import java.sql.*;
import javax.ejb.EJBException;
import javax.naming.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 *
 * @author karthikeyan
 */
public class HLCAccountsDAO {

    /** Creates a new instance of AccountsDAO */
    public HLCAccountsDAO() {
    }
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;

    //=============================================list Acc Transaction details  =========================================
    public ArrayList listAccTxnDetails(java.util.Date transaction_date, boolean sync_status) {
		Debug.print("in HLCAccountsDAO");
        Debug.print("transaction_date in AccountsDAO.listAccTxnDetails() :" + transaction_date.toString());
        Debug.print("sync_status in AccountsDAO.listAccTxnDetails() :" + sync_status);

        PreparedStatement prepStmt = null;
        ArrayList txnDetails = new ArrayList();
        HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();

        makeConnection();

        try {
            String selectStatement = "select account_type, account_no, sub_account_no, description, class, quantity, amount," +
                    " transaction_date ,sync_date, sync_status, user_id, ip_address, active_status from " + DBHelper.USEA_ACC_TXN_DETAILS +
                    " where CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? and sync_status = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setDate(1, DBHelper.toSQLDate(transaction_date));
            prepStmt.setBoolean(2, sync_status);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                accTxnVO = new HLCAccTransactionVO();

                accTxnVO.setAccount_type(rs.getString(1));
                accTxnVO.setAccount_no(rs.getString(2));
                accTxnVO.setSub_account_no(rs.getString(3));
                accTxnVO.setDescription(rs.getString(4));
                accTxnVO.setClass_Typ(rs.getString(5));
                accTxnVO.setQuantity(rs.getInt(6));
                accTxnVO.setAmount(rs.getFloat(7));
                accTxnVO.setTransaction_date(rs.getDate(8));
                accTxnVO.setSync_date(rs.getDate(9));
                accTxnVO.setSync_status(rs.getBoolean(10));
                accTxnVO.setUser_id(rs.getString(11));
                accTxnVO.setIp_address(rs.getString(12));
                accTxnVO.setActive_status(rs.getBoolean(13));

                txnDetails.add(accTxnVO);
            }

            Debug.print("listAccTxnDetails() size :" + txnDetails.size());

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.listAccTxnDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.listAccTxnDetails() :" + e.getMessage());
        }
        return txnDetails;
    }

    /**
     * Name         :getAccTxnDetailsOnPaymentId
     * Description  :This method will get record from the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList getAccTxnDetailsOnPaymentId(String paymentId) throws SQLException {
        Debug.print("paymentId in AccountsDAO.getAccTxnDetailsOnPaymentId() :" + paymentId);
        PreparedStatement prepStmt = null;
        ArrayList txnDetails = new ArrayList();
        ArrayList parentPaymentList = new ArrayList();
        HLCAccTransactionVO accTxnVO = new HLCAccTransactionVO();
        //Suresh Here =============================================
        try {
            if (paymentId != null && paymentId.trim().length() != 0) {
                Debug.print("inside getting ParentIds in AccountsDAO.getAccTxnDetailsOnPaymentId() :" + paymentId);
                parentPaymentList = selectParentTranactionIds(paymentId);
            }
        } catch (Exception e) {
            Debug.print("Exception while invoking selectParentTransaction in AccountsDAO.getAccTxnDetailsOnPaymentId() :" + e.getMessage());
        }
        //========================================================

        makeConnection();

        try {
            String selectStatement = "select account_type, account_no, sub_account_no, description, class, quantity, amount," +
                    " transaction_date ,sync_date, sync_status, user_id, ip_address, active_status, item_no, payment_mode, " +
                    " reconcile_amount, transaction_mode, reconcile_status, transaction_id, parent_transaction_id from " + DBHelper.USEA_ACC_TXN_DETAILS + " where payment_id = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, paymentId);

            ResultSet rs = prepStmt.executeQuery();
            int ss = 1;
            while (rs.next()) {
                Debug.print("-----------While Start Here --------------------------");
                accTxnVO = new HLCAccTransactionVO();
                accTxnVO.setAccount_type(rs.getString(1));
                accTxnVO.setAccount_no(rs.getString(2));
                accTxnVO.setSub_account_no(rs.getString(3));
                accTxnVO.setDescription(rs.getString(4));
                accTxnVO.setClass_Typ(rs.getString(5));
                accTxnVO.setQuantity(rs.getInt(6));

                float amt = rs.getFloat(7);
                Debug.print("Total Amount:" + amt);

                accTxnVO.setAmount(amt);
                accTxnVO.setTransaction_date(rs.getDate(8));
                accTxnVO.setSync_date(rs.getDate(9));
                accTxnVO.setSync_status(rs.getBoolean(10));
                accTxnVO.setUser_id(rs.getString(11));
                accTxnVO.setIp_address(rs.getString(12));
                accTxnVO.setActive_status(rs.getBoolean(13));
                accTxnVO.setItem_no(rs.getString(14));
                accTxnVO.setPayment_mode(rs.getString(15));

                float rConAmt = rs.getFloat(16);

                accTxnVO.setReconcile_amount(rs.getFloat(16));
                accTxnVO.setTransaction_mode(rs.getString(17));
                accTxnVO.setReconcile_status(rs.getBoolean(18));
                String transId = rs.getString(19);

                accTxnVO.setTransaction_id(transId);
                accTxnVO.setPayment_id(paymentId);

                String parentTransId = rs.getString(20);

                accTxnVO.setParentTransactionId(parentTransId);

                //Debug.print("TransAction Id:" + transId);
                //Debug.print("Parent TransAction Id:" + parentTransId);
                //Debug.print("parentPaymentList size():" + parentPaymentList.size());

                if (amt > 0 && parentTransId == null) {
                    if (parentPaymentList != null && parentPaymentList.size() != 0) {
                        Iterator itParent = parentPaymentList.iterator();
                        // ss++;
                        while (itParent.hasNext()) {
                            String parentTId = (String) itParent.next();
                            //Debug.print(ss + "Parent Transaction ID "+parentTId);
                            //Debug.print(ss + "Transaction transId "+transId);
                            if (parentTId != null && parentTId.trim().length() != 0 && transId.equalsIgnoreCase(parentTId)) {
                                double recAmt = selectReconcileAmount(parentTId);
                                //Debug.print("selectReconcileAmount.recAmt Amount inside While :" + recAmt);
                                //Debug.print("selectReconcileAmount.amt Amount inside While :" + amt);
                                if (amt > recAmt) {
                                    accTxnVO.setCheckStatus(true);
                                    Debug.print("========Loop Is break ==========");
                                } else {
                                    accTxnVO.setCheckStatus(false);
                                    Debug.print(" @@@@@@@@@@@@@@@@@@@@ Loop False @@@@@@@@@@@@@@@@@@@@ ");
                                }
                                break;
                            } else {
                                if (rConAmt < amt) {
                                    accTxnVO.setCheckStatus(true);
                                    Debug.print(" ========== parentPaymentList!=null && parentPaymentList.size()!=0 ========== ");
                                } else {
                                    accTxnVO.setCheckStatus(false);
                                    Debug.print(" @@@@@@@@@@@@@@@@@@@@ parentPaymentList!=null && parentPaymentList.size()!=0 @@@@@@@@@@@@@@@@@@@@ ");
                                }
                            }
                        }
                    } else {
                        if (rConAmt < amt) {
                            accTxnVO.setCheckStatus(true);
                            Debug.print(" ========== parentPaymentList!=null && parentPaymentList.size()!=0 ========== ");
                        } else {
                            accTxnVO.setCheckStatus(false);
                            Debug.print(" @@@@@@@@@@@@@@@@@@@@ parentPaymentList!=null && parentPaymentList.size()!=0 @@@@@@@@@@@@@@@@@@@@ ");
                        }
                    }
                } else {
                    Debug.print(" !!!!!!!!!!!!!!!! amt!=0 && parentTransId==null Block False !!!!!!!!!!!!!!!!!!!! ");
                    accTxnVO.setCheckStatus(false);
                }
                txnDetails.add(accTxnVO);
                Debug.print("-----------While End Here --------------------------");
            }

            Debug.print("getAccTxnDetailsOnPaymentId() size :" + txnDetails.size());

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getAccTxnDetailsOnPaymentId() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAccTxnDetailsOnPaymentId() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return txnDetails;
    }

    /**
     * Name         :selectParentTranactionIds
     * Description  :This method will get record from the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectParentTranactionIds(String paymentId) throws SQLException {
        Debug.print("paymentId in AccountsDAO.selectParentTranactionIds() :" + paymentId);
        PreparedStatement prepStmt = null;
        ArrayList txnDetails = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select distinct parent_transaction_id from tblAccTransactionDetails" +
                    " where payment_id=? and parent_transaction_id IS NOT NULL";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, paymentId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String parentPaymentId = rs.getString(1);
                txnDetails.add(parentPaymentId);
            }

            Debug.print("selectParentTranactionIds() size :" + txnDetails.size());

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectParentTranactionIds() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAccTxnDetailsOnPaymentId() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return txnDetails;
    }

    /**
     * Name         :selectReconcileAmount
     * Description  :This method will get record from the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :double
     * @throws      :SQLException
     */
    public double selectReconcileAmount(String transactionId) throws SQLException {

        Debug.print("paymentId in AccountsDAO.selectReconcileAmount() :" + transactionId);

        PreparedStatement prepStmtRAmt = null;
        double recAmount = 0;
        // makeConnection();

        try {
            String selectStatement = " select sum(reconcile_amount) as amt from tblAccTransactionDetails " +
                    " where parent_transaction_id = ? " +
                    " or transaction_id = ?";

            Debug.print("Query Log :" + selectStatement);

            prepStmtRAmt = con.prepareStatement(selectStatement);
            prepStmtRAmt.setString(1, transactionId);
            prepStmtRAmt.setString(2, transactionId);

            ResultSet rsRAmt = prepStmtRAmt.executeQuery();

            if (rsRAmt.next()) {
                recAmount = rsRAmt.getDouble(1);
            }

            Debug.print("selectReconcileAmount() recAmount :" + recAmount);
            rsRAmt.close();
            prepStmtRAmt.close();

        } catch (SQLException sql) {
            // releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectReconcileAmount() :" + sql.getMessage());
        } catch (Exception e) {
            // releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectReconcileAmount() :" + e.getMessage());
        } finally {
        // releaseConnection(con);
        }

        return recAmount;
    }

    /**
     * Name         :insertAccountTxnDetails
     * Description  :This method will insert record into the tblAccTxnDetails table
     * @ param      :AccTransactionVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean insertAccountTxnDetails(HLCAccTransactionVO accTxnVO) {
        Debug.print("inside AccountsDAO.insertAccountTxnDetails :");
        Debug.print("accTxnVO.toString() :" + accTxnVO.toString());
        boolean status = false;

        try {

            makeConnection();

            String selectStatement = "select newid() as transaction_id ";

            Debug.print("select Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);

            ResultSet rs = prepStmt.executeQuery();
            String txnId = "";

            if (rs.next()) {

                txnId = rs.getString(1);
            }
            rs.close();

            Debug.print("selected txnId before insert :" + txnId);


            String insertStatement = "insert into " + DBHelper.USEA_ACC_TXN_DETAILS + " (account_type, account_no, sub_account_no, " +
                    " description, class, quantity, amount, user_id, ip_address, payment_mode, item_no, " +
                    " payment_id, active_status, reconcile_status, parent_transaction_id, reconcile_amount, transaction_mode, transaction_id, staff_user_id, staff_ip_address) " +
                    "values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ? , ?, ?, ? )";
            prepStmt = con.prepareStatement(insertStatement);
            Debug.print("insertStatement :" + insertStatement);

            prepStmt.setString(1, accTxnVO.getAccount_type());
            prepStmt.setString(2, accTxnVO.getAccount_no());
            prepStmt.setString(3, accTxnVO.getSub_account_no());
            prepStmt.setString(4, accTxnVO.getDescription());
            prepStmt.setString(5, accTxnVO.getClass_Typ());
            prepStmt.setInt(6, accTxnVO.getQuantity());
            prepStmt.setFloat(7, accTxnVO.getAmount());
            prepStmt.setString(8, accTxnVO.getUser_id());
            prepStmt.setString(9, accTxnVO.getIp_address());

            prepStmt.setString(10, accTxnVO.getPayment_mode());
            prepStmt.setString(11, accTxnVO.getItem_no());
            prepStmt.setString(12, accTxnVO.getPayment_id());
            prepStmt.setBoolean(13, accTxnVO.isActive_status());
            prepStmt.setBoolean(14, accTxnVO.isReconcile_status());
            prepStmt.setString(15, accTxnVO.getParentTransactionId());
            prepStmt.setFloat(16, accTxnVO.getReconcile_amount());
            prepStmt.setString(17, accTxnVO.getTransaction_mode());
            prepStmt.setString(18, txnId);
            prepStmt.setString(19, accTxnVO.getStaff_user_id());
            prepStmt.setString(20, accTxnVO.getStaff_ip_address());

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                status = true;
            }

            prepStmt.close();
            releaseConnection(con);
            Debug.print("Successfully Inserted insertAccountTxnDetails ......:" + cnt);

            if (accTxnVO.getParentTransactionId() != null) {
                Debug.print("inside accTxnVO.getParentTransactionId()!=null block :");
                boolean res = updateRecouncilTxnDateOnParentTxnId(accTxnVO.getParentTransactionId(), txnId);
                Debug.print("result for updateRecouncilTxnDateOnParentTxnId : " + res);
            }

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while inserting insertAccountTxnDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while inserting insertAccountTxnDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return status;
    }

    /**
     * Name         :updatePaymentStatusAccTxnDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updatePaymentStatusAccTxnDetails(String paymentId) {
        Debug.print("inside AccountsDAO.updatePaymentStatusAccTxnDetails :");
        Debug.print("paymentId :" + paymentId);
        boolean val = false;

        try {

            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set  active_status = ? where payment_id = ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, paymentId);

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updatePaymentStatusAccTxnDetails ......:" + cnt);

            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updatePaymentStatusAccTxnDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updatePaymentStatusAccTxnDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :updateRecouncilAccTxnDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :AccTransactionVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateRecouncilAccTxnDetails(HLCAccTransactionVO accTxnVO) throws SQLException {
        Debug.print("inside AccountsDAO.updateRecouncilAccTxnDetails :");
        Debug.print("accTxnVO.toString() :" + accTxnVO.toString());
        boolean val = false;

        try {

            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set reconcile_amount = ?, transaction_mode = ?, " +
                    "payment_mode = ?, reconcile_status = ?, active_status = ? , staff_user_id = ?, staff_ip_address = ? where transaction_id = ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            prepStmt.setFloat(1, accTxnVO.getReconcile_amount());
            prepStmt.setString(2, accTxnVO.getTransaction_mode());
            prepStmt.setString(3, accTxnVO.getPayment_mode());
            prepStmt.setBoolean(4, accTxnVO.isReconcile_status());
            prepStmt.setBoolean(5, accTxnVO.isActive_status());
            prepStmt.setString(6, accTxnVO.getStaff_user_id());
            prepStmt.setString(7, accTxnVO.getStaff_ip_address());

            prepStmt.setString(8, accTxnVO.getTransaction_id());

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateRecouncilAccTxnDetails ......:" + cnt);

            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateRecouncilAccTxnDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateRecouncilAccTxnDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :updatePaymentStatusAccTxnDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateRecouncilActiveStatusAccTxnDetails(String paymentId) {
        Debug.print("inside AccountsDAO.updateRecouncilActiveStatusAccTxnDetails :");
        Debug.print("paymentId :" + paymentId);
        boolean val = false;

        try {

            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set active_status = ?, reconcile_status = ? where payment_id = ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            prepStmt.setBoolean(1, true);
            prepStmt.setBoolean(2, true);
            prepStmt.setString(3, paymentId);

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateRecouncilActiveStatusAccTxnDetails ......:" + cnt);

            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateRecouncilActiveStatusAccTxnDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateRecouncilActiveStatusAccTxnDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :selectClassMaster
     * Description  :This method will select records from tblAccClassMaster table
     * @ param      :nil
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectClassMaster() throws SQLException {
        Debug.print("AccountsDAO.selectClassMaster() :");

        PreparedStatement prepStmt = null;
        ArrayList classDetails = new ArrayList();
        HLCClassDetailsVO classDet = new HLCClassDetailsVO();

        makeConnection();

        try {
            String selectStatement = "select class_id, class_name, parent_class_name from " + DBHelper.USEA_ACC_CLASS_DETAILS;

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                classDet = new HLCClassDetailsVO();

                classDet.setClass_id(rs.getString(1));
                classDet.setClass_name(rs.getString(2));
                classDet.setParent_class_name(rs.getString(3));

                classDetails.add(classDet);
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectClassMaster() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectClassMaster() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return classDetails;
    }

    /**
     * Name         :selectAccountMaster
     * Description  :This method will select records from tblAccAccountsMaster table
     * @ param      :nil
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectAccountMaster() throws SQLException {
        Debug.print("AccountsDAO.selectAccountMaster() :");

        PreparedStatement prepStmt = null;
        ArrayList accountDetails = new ArrayList();
        HLCAccountsMasterVO accDet = new HLCAccountsMasterVO();

        makeConnection();

        try {
            String selectStatement = "select account_id, account_type, account_no, account_name, parent_account_no, " + "account_desc, bank_account_no from " + DBHelper.USEA_ACC_MASTER;

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccountsMasterVO();

                accDet.setAccount_id(rs.getString(1));
                accDet.setAccount_type(rs.getString(2));
                accDet.setAccount_no(rs.getString(3));
                accDet.setAccount_name(rs.getString(4));
                accDet.setParent_account_no(rs.getString(5));
                accDet.setAccount_desc(rs.getString(6));
                accDet.setBank_account_no(rs.getString(7));
                accountDetails.add(accDet);
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectAccountMaster() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectAccountMaster() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accountDetails;
    }

    /**
     * Name         :getMemberAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :membershipTypeId
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
    public HLCAccTxnTypeDetailVO getMemberAccTxnTypeDetails(String membershipTypeId) throws SQLException {
        Debug.print("AccountsDAO.getMemberAccTxnTypeDetails() :");
        Debug.print("membershipTypeId :" + membershipTypeId);

        PreparedStatement prepStmt = null;

        HLCAccTxnTypeDetailVO accDet = new HLCAccTxnTypeDetailVO();

        makeConnection();

        try {

            String selectStatement = "select A.transaction_type_id, A.transaction_name, A.transaction_type, A.account_no, A.sub_account_no, A.item_no, A.class_name, B.transaction_type_id " +
                    "from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " A, " + DBHelper.USEA_MEMB_TYPE_MASTER + " B where A.transaction_type_id = B.transaction_type_id " +
                    "and B.membership_type_id = ?";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, membershipTypeId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccTxnTypeDetailVO();

                accDet.setTransaction_type_id(rs.getString(1));
                accDet.setTransaction_name(rs.getString(2));
                accDet.setTransaction_type(rs.getString(3));
                accDet.setAccount_no(rs.getString(4));
                accDet.setSub_account_no(rs.getString(5));
                accDet.setItem_no(rs.getString(6));
                accDet.setClass_name(rs.getString(7));
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getMemberAccTxnTypeDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getMemberAccTxnTypeDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accDet;
    }

    /**
     * Name         :getDonationAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :donationTypeId
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
    public HLCAccTxnTypeDetailVO getDonationAccTxnTypeDetails(String donationTypeId) throws SQLException {
        Debug.print("AccountsDAO.getDonationAccTxnTypeDetails() :");
        Debug.print("donationTypeId :" + donationTypeId);

        PreparedStatement prepStmt = null;

        HLCAccTxnTypeDetailVO accDet = new HLCAccTxnTypeDetailVO();

        makeConnection();

        try {

            String selectStatement = "select A.transaction_type_id, A.transaction_name, A.transaction_type, A.account_no, A.sub_account_no, A.item_no, A.class_name, B.transaction_type_id " +
                    "from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " A, " + DBHelper.USEA_DONATION_DETAILS + " B where A.transaction_type_id = B.transaction_type_id " +
                    "and B.donation_id = ?";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, donationTypeId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccTxnTypeDetailVO();

                accDet.setTransaction_type_id(rs.getString(1));
                accDet.setTransaction_name(rs.getString(2));
                accDet.setTransaction_type(rs.getString(3));
                accDet.setAccount_no(rs.getString(4));
                accDet.setSub_account_no(rs.getString(5));
                accDet.setItem_no(rs.getString(6));
                accDet.setClass_name(rs.getString(7));
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getDonationAccTxnTypeDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getDonationAccTxnTypeDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accDet;
    }

    /**
     * Name         :getArmBandAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :Nil
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
    public HLCAccTxnTypeDetailVO getArmBandAccTxnTypeDetails() throws SQLException {
        Debug.print("AccountsDAO.getArmBandAccTxnTypeDetails() :");

        PreparedStatement prepStmt = null;

        HLCAccTxnTypeDetailVO accDet = new HLCAccTxnTypeDetailVO();

        makeConnection();

        try {

            String selectStatement = "select transaction_type_id, transaction_name, transaction_type, account_no, sub_account_no, item_no, class_name " +
                    "from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " where transaction_name = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, "Arm Band Card");

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccTxnTypeDetailVO();

                accDet.setTransaction_type_id(rs.getString(1));
                accDet.setTransaction_name(rs.getString(2));
                accDet.setTransaction_type(rs.getString(3));
                accDet.setAccount_no(rs.getString(4));
                accDet.setSub_account_no(rs.getString(5));
                accDet.setItem_no(rs.getString(6));
                accDet.setClass_name(rs.getString(7));
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getArmBandAccTxnTypeDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getArmBandAccTxnTypeDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accDet;
    }

    /**
     * Name         :getPhoneServChgAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :servTypId
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
    public HLCAccTxnTypeDetailVO getPhoneServChgAccTxnTypeDetails(String servTypId) throws SQLException {
        Debug.print("AccountsDAO.getPhoneServChgAccTxnTypeDetails() :");
        Debug.print("servTypId :" + servTypId);

        PreparedStatement prepStmt = null;

        HLCAccTxnTypeDetailVO accDet = new HLCAccTxnTypeDetailVO();

        makeConnection();

        try {

            String selectStatement = "select A.transaction_type_id, A.transaction_name, A.transaction_type, A.account_no, A.sub_account_no, A.item_no, A.class_name, " +
                    "B.transaction_type_id from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " A ," + DBHelper.USEA_SERVICE_TYPE_MASTER + " B where A.transaction_type_id = B.transaction_type_id and B.horse_service_type_id = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, servTypId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccTxnTypeDetailVO();

                accDet.setTransaction_type_id(rs.getString(1));
                accDet.setTransaction_name(rs.getString(2));
                accDet.setTransaction_type(rs.getString(3));
                accDet.setAccount_no(rs.getString(4));
                accDet.setSub_account_no(rs.getString(5));
                accDet.setItem_no(rs.getString(6));
                accDet.setClass_name(rs.getString(7));
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getPhoneServChgAccTxnTypeDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getPhoneServChgAccTxnTypeDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accDet;
    }

    /**
     * Name         :insertAccTxnTypeDetails
     * Description  :This method will insert record into the tblAccTransactionTypeDetails table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean insertAccTxnTypeDetails(HLCAccTxnTypeDetailVO accTxnTypVO) throws SQLException {
        Debug.print("inside AccountsDAO.insertAccTxnTypeDetails :");
        Debug.print("accTxnTypVO.toString() :" + accTxnTypVO.toString());
        boolean stat = false;
        try {
            makeConnection();
            String insertStatement = "insert into " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " (transaction_name, transaction_type, account_no, " +
                    " sub_account_no, item_no, class_name) values ( ? , ? , ? , ? , ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);
            Debug.print("insertStatement :" + insertStatement);

            prepStmt.setString(1, accTxnTypVO.getTransaction_name());
            prepStmt.setString(2, accTxnTypVO.getTransaction_type());
            prepStmt.setString(3, accTxnTypVO.getAccount_no());
            prepStmt.setString(4, accTxnTypVO.getSub_account_no());
            prepStmt.setString(5, accTxnTypVO.getItem_no());
            prepStmt.setString(6, accTxnTypVO.getClass_name());

            int cnt = prepStmt.executeUpdate();
            Debug.print("Successfully Inserted insertAccTxnTypeDetails ......:" + cnt);

            if (cnt > 0) {
                stat = true;
            }

            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while inserting insertAccTxnTypeDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while inserting insertAccTxnTypeDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return stat;
    }

    /**
     * Name         :selectMemberShipTransction
     * Author       :Suresh.K
     * Description  :This method will insert record into the tblAccTransactionDetails table
     * @ param      :Date transDate, String paymentMode
     * @return      :ArrayList
     * @throws      :nil
     */
    //=============================================Getting All Privilege details=========================================
    public ArrayList selectMemberShipTransction(Date transDate, String paymentMode, String userId) {
        Debug.print("AccountsDAO.selectMemberShipTransction():");
        ArrayList transactionList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {

            /*==========Old One================================================
             *  String selectStatement="select item_no, payment_mode, class, sum(amount) as tot_amount," +
            " description from tblAccTransactionDetails " +
            " where sync_status = 0 and active_status = 1 and " ;
            if(paymentMode!=null && paymentMode.trim().length()!=0){
            if(paymentMode.equalsIgnoreCase("Visa, MasterCard")){
            String paymentval[] = paymentMode.split(",");
            paymentMode = paymentval[0].trim() + "', '" + paymentval[1].trim();
            Debug.print("Payment Mode:" + paymentMode);
            }
            selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and " ;
            }
            selectStatement = selectStatement + " CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
            " group by item_no, payment_mode, class, description ";
             */

            /*
            select item_no, payment_mode, class, sum(amount) as tot_amount,
            description from tblAccTransactionDetails
            where sync_status = 0 and active_status = 1 and
            payment_mode in('Visa','MasterCard') and reconcile_amount = 0 and
            CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = '2007-06-22'
            group by item_no, payment_mode, class, description
            UNION ALL
            select item_no, payment_mode, class, sum(reconcile_amount) as tot_amount,
            description from tblAccTransactionDetails
            where sync_status = 0 and active_status = 1 and
            payment_mode in('Visa','MasterCard') and reconcile_amount > 0 and
            CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = '2007-06-22'
            group by item_no, payment_mode, class, description
             */
            String selectStatement = "";

            if (paymentMode != null) {
                if (paymentMode.equalsIgnoreCase("Check")) {
                    selectStatement = "select item_no, payment_mode, class, sum(amount) as tot_amount," +
                            " description from tblAccTransactionDetails " +
                            " where sync_status = 0 and active_status = 1 and ";


                    selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and staff_user_id = ? and ";

                    selectStatement = selectStatement + " reconcile_amount = 0 and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                            " group by item_no, payment_mode, class, description ";

                    selectStatement = selectStatement + " UNION ALL select item_no, payment_mode, class, " +
                            " sum(reconcile_amount) as tot_amount, description from tblAccTransactionDetails" +
                            " where sync_status = 0 and active_status = 1 and ";

                    selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and staff_user_id = ? and ";

                    selectStatement = selectStatement + " reconcile_amount > 0 and " +
                            " CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                            " group by item_no, payment_mode, class, description ";

                    selectStatement = selectStatement + " UNION ALL select item_no, payment_mode, class,  sum(reconcile_amount) as tot_amount, description " +
                            "from tblAccTransactionDetails where sync_status = 0 and active_status = 1 and  " +
                            "payment_mode in ('" + paymentMode + "') and staff_user_id = ? and reconcile_amount < 0 and  CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                            "group by item_no, payment_mode, class, description ";

                    prepStmt = con.prepareStatement(selectStatement);
                    prepStmt.setString(1, userId);
                    prepStmt.setDate(2, DBHelper.toSQLDate(transDate));
                    prepStmt.setString(3, userId);
                    prepStmt.setDate(4, DBHelper.toSQLDate(transDate));
                    prepStmt.setString(5, userId);
                    prepStmt.setDate(6, DBHelper.toSQLDate(transDate));
                } else {
                    selectStatement = "select item_no, payment_mode, class, sum(amount) as tot_amount," +
                            " description from tblAccTransactionDetails " +
                            " where sync_status = 0 and active_status = 1 and ";

                    if (paymentMode != null && paymentMode.trim().length() != 0) {
                        if (paymentMode.equalsIgnoreCase("Visa, MasterCard")) {
                            String paymentval[] = paymentMode.split(",");
                            paymentMode = paymentval[0].trim() + "', '" + paymentval[1].trim();
                            Debug.print("Payment Mode:" + paymentMode);
                        }
                        selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and ";
                    }

                    selectStatement = selectStatement + " reconcile_amount = 0 and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                            " group by item_no, payment_mode, class, description ";

                    selectStatement = selectStatement + " UNION ALL select item_no, payment_mode, class, " +
                            " sum(reconcile_amount) as tot_amount, description from tblAccTransactionDetails" +
                            " where sync_status = 0 and active_status = 1 and ";

                    selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and ";

                    selectStatement = selectStatement + " reconcile_amount > 0 and " +
                            " CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                            " group by item_no, payment_mode, class, description ";

                    selectStatement = selectStatement + " UNION ALL select item_no, payment_mode, class,  sum(reconcile_amount) as tot_amount, description " +
                            "from tblAccTransactionDetails where sync_status = 0 and active_status = 1 and  " +
                            "payment_mode in ('" + paymentMode + "') and reconcile_amount < 0 and  CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                            "group by item_no, payment_mode, class, description ";

                    prepStmt = con.prepareStatement(selectStatement);
                    prepStmt.setDate(1, DBHelper.toSQLDate(transDate));
                    prepStmt.setDate(2, DBHelper.toSQLDate(transDate));
                    prepStmt.setDate(3, DBHelper.toSQLDate(transDate));
                }
            }
            Debug.print("SQL Query:" + selectStatement);

            rs = prepStmt.executeQuery();
            while (rs.next()) {
                HLCMemberShipTransactionVO transVO = new HLCMemberShipTransactionVO();

                String itemNo = rs.getString(1);
                // transVO.setPayment_id(rs.getString(2));
                paymentMode = rs.getString(2);
                String accountClass = rs.getString(3);
                double amount = rs.getDouble(4);
                // Date transactionDate = rs.getDate(5);
                String description = rs.getString(5);
                //transVO.setPayment_id(rs.getString(6));
                //String accountName = rs.getString(7);


                transVO.setItemNo(itemNo);
                transVO.setPaymentMode(paymentMode);
                transVO.setAccountClass(accountClass);
                transVO.setAmount(amount);
                //transVO.setTransactionDate(transactionDate);
                //transVO.setAccountNo(accountNo);
                //transVO.setAccountName(accountName);
                transVO.setDesc(description);
                transactionList.add(transVO);
            }
            Debug.print("Transction List Size():" + transactionList.size());

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectMemberShipTransction():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectMemberShipTransction():" + e.getMessage());
        }
        return transactionList;
    }

    /**
     * Name         :selectSyncronizedQBTransctionDetails
     * Author       :Karthikeyan.M
     * Description  :This method will select already Syncronized QB Transction Details from tblAccTransactionDetails table
     * @ param      :Date transDate, String paymentMode
     * @return      :ArrayList
     * @throws      :nil
     */
    public ArrayList selectSyncronizedQBTransctionDetails(Date transDate, String paymentMode) {
        Debug.print("AccountsDAO.selectSyncronizedQBTransctionDetails():");

        Debug.print("transDate :" + transDate.toString());
        Debug.print("paymentMode :" + paymentMode);

        ArrayList transactionList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {

            String selectStatement = "select item_no, payment_mode, class, sum(amount) as tot_amount," +
                    " description from tblAccTransactionDetails " +
                    " where sync_status = 1 and active_status = 1 and ";

            if (paymentMode != null && paymentMode.trim().length() != 0) {
                if (paymentMode.equalsIgnoreCase("Visa, MasterCard")) {
                    String paymentval[] = paymentMode.split(",");
                    paymentMode = paymentval[0].trim() + "', '" + paymentval[1].trim();
                    Debug.print("Payment Mode:" + paymentMode);
                }
                selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and ";
            }

            selectStatement = selectStatement + " reconcile_amount = 0 and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                    " group by item_no, payment_mode, class, description ";

            selectStatement = selectStatement + " UNION ALL select item_no, payment_mode, class, " +
                    " sum(reconcile_amount) as tot_amount, description from tblAccTransactionDetails" +
                    " where sync_status = 1 and active_status = 1 and ";

            selectStatement = selectStatement + " payment_mode in ('" + paymentMode + "') and ";

            selectStatement = selectStatement + " reconcile_amount > 0 and " +
                    " CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " +
                    " group by item_no, payment_mode, class, description ";


            Debug.print("SQL Query:" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setDate(1, DBHelper.toSQLDate(transDate));
            prepStmt.setDate(2, DBHelper.toSQLDate(transDate));
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String itemNo = rs.getString(1);
                paymentMode = rs.getString(2);
                String accountClass = rs.getString(3);
                double amount = rs.getDouble(4);
                // Date transactionDate = rs.getDate(5);
                String description = rs.getString(5);
                //String accountName = rs.getString(7);

                HLCMemberShipTransactionVO transVO = new HLCMemberShipTransactionVO();
                transVO.setItemNo(itemNo);
                transVO.setPaymentMode(paymentMode);
                transVO.setAccountClass(accountClass);
                transVO.setAmount(amount);
                //transVO.setTransactionDate(transactionDate);
                //transVO.setAccountNo(accountNo);
                //transVO.setAccountName(accountName);
                transVO.setDesc(description);
                transactionList.add(transVO);
            }
            Debug.print("Transction List Size():" + transactionList.size());

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectSyncronizedQBTransctionDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectSyncronizedQBTransctionDetails():" + e.getMessage());
        }
        return transactionList;
    }

    /**
     * Name         :updateQBStatus
     * Description  :This method will update qb_status record into the tblAccTransactionDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateQBStatus(Date transDate, String paymentMode, boolean qbStatus, boolean contQBStatus,
            String userId, String ipAddress) {
        Debug.print("inside AccountsDAO.updateQBStatus :");
        Debug.print("~~~~paymentMode in updateQBStatus~~~~~ :" + paymentMode);
        Debug.print("~~~~transDate updateQBStatus ~~~~~~:" + transDate);
        boolean val = false;
        try {
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set  qb_status = ?, " +
                    " ip_address= ?, user_id= ?  where sync_status = 0 and ";

            if (paymentMode != null && paymentMode.trim().length() != 0) {
                if (paymentMode.equalsIgnoreCase("Visa, MasterCard")) {
                    String paymentval[] = paymentMode.split(",");
                    paymentMode = paymentval[0].trim() + "', '" + paymentval[1].trim();
                    Debug.print("Payment Mode:" + paymentMode);
                }
                updateStatement = updateStatement + " payment_mode in ('" + paymentMode + "') and ";
            }
            updateStatement = updateStatement + " CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? and " +
                    " qb_status = ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);
            prepStmt.setBoolean(1, qbStatus);
            prepStmt.setString(2, ipAddress);
            prepStmt.setString(3, userId);
            prepStmt.setDate(4, DBHelper.toSQLDate(transDate));
            prepStmt.setBoolean(5, contQBStatus);

            int cnt = prepStmt.executeUpdate();

            if (cnt > 1) {
                val = true;
            }
            Debug.print("Successfully updated AccountsDAO.updateQBStatus ......:" + cnt);
            prepStmt.close();

        } catch (SQLException sql) {
            //releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateQBStatus :" + sql.getMessage());
        } catch (Exception e) {
            // releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateQBStatus : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :updateQBSynStatus
     * Description  :This method will update qb_status record into the tblAccTransactionDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateQBSynStatus(Date transDate, String paymentMode, String userId, String ipAddress) {
        Debug.print("inside AccountsDAO.updateQBSynStatus :");
        Debug.print("paymentMode :" + paymentMode);
        Debug.print("transDate :" + transDate);
        boolean val = false;
        try {
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set  sync_status = 1 , sync_date= ?,  " +
                    " ip_address= ?, user_id= ?  where sync_status = 0 and qb_status = 1 and ";

            if (paymentMode != null && paymentMode.trim().length() != 0) {
                if (paymentMode.equalsIgnoreCase("Visa, MasterCard")) {
                    String paymentval[] = paymentMode.split(",");
                    paymentMode = paymentval[0].trim() + "', '" + paymentval[1].trim();
                    Debug.print("Payment Mode:" + paymentMode);
                }
                updateStatement = updateStatement + " payment_mode in ('" + paymentMode + "') and ";
            }
            updateStatement = updateStatement + " CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? ";


            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);
            Debug.print("Current Time:" + new Timestamp(new Date().getTime()));
            prepStmt.setTimestamp(1, new Timestamp(new Date().getTime()));
            prepStmt.setString(2, ipAddress);
            prepStmt.setString(3, userId);
            prepStmt.setDate(4, DBHelper.toSQLDate(transDate));

            int cnt = prepStmt.executeUpdate();

            if (cnt > 1) {
                val = true;
            }
            Debug.print("Successfully updated AccountsDAO.updateQBSynStatus ......:" + cnt);
            prepStmt.close();

        } catch (SQLException sql) {
            // releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateQBSynStatus :" + sql.getMessage());
        } catch (Exception e) {
            //releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateQBSynStatus : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :selectAccTransactionDetails
     * Description  :This method will get record from the tblAccTxnDetails table based on transaction id.
     * @ param      :transactionId
     * @return      :AccTransactionVO
     * @Author      :Suresh K
     * @throws      :SQLException
     */
    public HLCAccTransactionVO selectAccTransactionDetails(String transactionId) throws SQLException {
        Debug.print("paymentId in AccountsDAO.selectAccTransactionDetails() :" + transactionId);
        PreparedStatement prepStmt = null;
        HLCAccTransactionVO accTxnVO = null;
        ArrayList parentPaymentList = new ArrayList();

        makeConnection();
        try {
            String selectStatement = "select account_type, account_no, sub_account_no, description, class, quantity, amount," +
                    " transaction_date ,sync_date, sync_status, user_id, ip_address, active_status, item_no, payment_mode, " +
                    " reconcile_amount, transaction_mode, reconcile_status, transaction_id, payment_id, " +
                    " parent_transaction_id  from " + DBHelper.USEA_ACC_TXN_DETAILS + " where transaction_id = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, transactionId);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                accTxnVO = new HLCAccTransactionVO();
                accTxnVO.setAccount_type(rs.getString(1));
                accTxnVO.setAccount_no(rs.getString(2));
                accTxnVO.setSub_account_no(rs.getString(3));
                accTxnVO.setDescription(rs.getString(4));
                accTxnVO.setClass_Typ(rs.getString(5));
                accTxnVO.setQuantity(rs.getInt(6));
                accTxnVO.setAmount(rs.getFloat(7));
                accTxnVO.setTransaction_date(rs.getDate(8));
                accTxnVO.setSync_date(rs.getDate(9));
                accTxnVO.setSync_status(rs.getBoolean(10));
                accTxnVO.setUser_id(rs.getString(11));
                accTxnVO.setIp_address(rs.getString(12));
                accTxnVO.setActive_status(rs.getBoolean(13));
                accTxnVO.setItem_no(rs.getString(14));
                accTxnVO.setPayment_mode(rs.getString(15));
                accTxnVO.setReconcile_amount(rs.getFloat(16));
                accTxnVO.setTransaction_mode(rs.getString(17));
                accTxnVO.setReconcile_status(rs.getBoolean(18));
                accTxnVO.setTransaction_id(rs.getString(19));
                accTxnVO.setPayment_id(rs.getString(20));
                accTxnVO.setParentTransactionId(rs.getString(21));


            }
            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectAccTransactionDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectAccTransactionDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return accTxnVO;
    }

    /**
     * Name         :selectServiceTypeMaster
     * Description  :This method will get record from the tblAccServiceTypeMaster table
     * @ param      :Nil
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectServiceTypeMaster() throws SQLException {
        Debug.print("AccountsDAO.selectServiceTypeMaster() calling");
        PreparedStatement prepStmt = null;
        ArrayList serviceTypeList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select service_type_id, service_type_name from tblAccServiceTypeMaster";

            prepStmt = con.prepareStatement(selectStatement);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String serviceTypeId = rs.getString(1);
                String serviceTypeName = rs.getString(2);
                String serviceTypeArray[] = {serviceTypeId, serviceTypeName};
                serviceTypeList.add(serviceTypeArray);
            }

            Debug.print("selectServiceTypeMaster() size :" + serviceTypeList.size());
            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectServiceTypeMaster() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectServiceTypeMaster() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return serviceTypeList;
    }

    /**
     * Name         :selectItemMaster
     * Description  :This method will get record from the tblAccItemMaster table
     * @ param      :Nil
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectItemMaster() throws SQLException {
        Debug.print("AccountsDAO.selectItemMaster() Calling");
        PreparedStatement prepStmt = null;
        ArrayList itemList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select item_id, service_type_name, item_no, parent_item_no, item_desc, " +
                    " rate, account_no, add_date from tblAccItemMaster order by item_no, parent_item_no";

            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                HLCItemMaster itemVO = new HLCItemMaster();
                String itemId = rs.getString(1);
                String serviceTypeName = rs.getString(2);
                String itemNo = rs.getString(3);
                String parentItemNo = rs.getString(4);
                String itemDesc = rs.getString(5);
                float rate = rs.getFloat(6);
                String accountNo = rs.getString(7);
                Date addDate = rs.getDate(8);

                itemVO.setItemId(itemId);
                itemVO.setServiceTypeName(serviceTypeName);
                itemVO.setItemNo(itemNo);
                itemVO.setParentItemNo(parentItemNo);
                itemVO.setItemDesc(itemDesc);
                itemVO.setRate(rate);
                itemVO.setAccountNo(accountNo);
                itemVO.setAddDate(addDate);

                itemList.add(itemVO);
            }

            Debug.print("selectItemMaster() size :" + itemList.size());

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectItemMaster() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectItemMaster() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return itemList;
    }

    /**
     * Name         :insertAccItemMaster
     * Description  :This method will insert record into the tblAccItemMaster table
     * @ param      :ItemMaster
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean insertAccItemMaster(HLCItemMaster itemVO) throws SQLException {
        Debug.print("inside AccountsDAO.insertAccItemMaster :");
        boolean status = false;
        try {
            makeConnection();
            String insertStatement = "insert into tblAccItemMaster (service_type_name, item_no, " +
                    " parent_item_no, item_desc, rate, account_no)" +
                    "values ( ? , ? , ? , ? , ?, ?)";

            prepStmt = con.prepareStatement(insertStatement);
            Debug.print("insertStatement :" + insertStatement);

            prepStmt.setString(1, itemVO.getServiceTypeName());
            prepStmt.setString(2, itemVO.getItemNo());
            prepStmt.setString(3, itemVO.getParentItemNo());
            prepStmt.setString(4, itemVO.getItemDesc());
            prepStmt.setFloat(5, itemVO.getRate());
            prepStmt.setString(6, itemVO.getAccountNo());

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                status = true;
            }

            prepStmt.close();
            releaseConnection(con);
            Debug.print("Successfully Inserted insertAccItemMaster ......:" + cnt);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while inserting insertAccItemMaster :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while inserting insertAccItemMaster : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return status;
    }

    /**
     * Name         :selectItemDetails
     * Description  :This method will get record based on itemId from the tblAccItemMaster table
     * @ param      :itemId
     * @return      :ItemMaster
     * @throws      :SQLException
     */
    public HLCItemMaster selectItemDetails(String itemId) throws SQLException {
        Debug.print("AccountsDAO.selectItemDetails() Calling");
        HLCItemMaster itemVO = new HLCItemMaster();
        PreparedStatement prepStmt = null;
        makeConnection();

        try {
            String selectStatement = "select item_id, service_type_name, item_no, parent_item_no, item_desc, " +
                    " rate, account_no, add_date from tblAccItemMaster where item_id= ?";

            prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1, itemId);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                itemId = rs.getString(1);
                String serviceTypeName = rs.getString(2);
                String itemNo = rs.getString(3);
                String parentItemNo = rs.getString(4);
                String itemDesc = rs.getString(5);
                float rate = rs.getFloat(6);
                String accountNo = rs.getString(7);
                Date addDate = rs.getDate(8);

                itemVO.setItemId(itemId);
                itemVO.setServiceTypeName(serviceTypeName);
                itemVO.setItemNo(itemNo);
                itemVO.setParentItemNo(parentItemNo);
                itemVO.setItemDesc(itemDesc);
                itemVO.setRate(rate);
                itemVO.setAccountNo(accountNo);
                itemVO.setAddDate(addDate);
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectItemDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectItemDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return itemVO;
    }

    /**
     * Name         :updateAccItemDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateAccItemDetails(String itemId, String serviceTypeName, String itemNo,
            String parentItemNo, String itemDesc, float rate, String accountNo) {
        Debug.print("inside AccountsDAO.updateAccItemDetails :");
        Debug.print("itemId :" + itemId);
        boolean val = false;

        try {
            makeConnection();
            String updateStatement = "update tblAccItemMaster set service_type_name = ?, " +
                    " item_no = ?, parent_item_no=?, item_desc = ?, " +
                    " rate = ?, account_no = ? where item_id= ?";

            prepStmt = con.prepareStatement(updateStatement);

            Debug.print("Query Log :" + updateStatement);
            prepStmt.setString(1, serviceTypeName);
            prepStmt.setString(2, itemNo);
            prepStmt.setString(3, parentItemNo);
            prepStmt.setString(4, itemDesc);
            prepStmt.setFloat(5, rate);
            prepStmt.setString(6, accountNo);
            prepStmt.setString(7, itemId);

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateAccItemDetails ......:" + cnt);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateAccItemDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateAccItemDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :isItemNoExist
     * Description  :This method will check whether itemNo already exist or not from tblAccItemMaster table
     * @ param      :itemNo
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isItemNoExist(String itemNo) {
        Debug.print("inside AccountsDAO.isItemNoExist :");
        boolean val = false;
        try {
            makeConnection();
            String selectStatement = "select item_no from tblAccItemMaster where item_no = ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, itemNo);
            ResultSet rs = prepStmt.executeQuery();
            val = rs.next();
            Debug.print("AccountsDAO.isItemNoExist ......:" + val);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.isItemNoExist :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.isItemNoExist : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :isItemNoEditExist
     * Description  :This method will check whether itemNo already exist or not from tblAccItemMaster table
     * @ param      :itemNo
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isItemNoEditExist(String itemId, String itemNo) {
        Debug.print("inside AccountsDAO.isItemNoEditExist :");
        boolean val = false;
        try {
            makeConnection();
            String selectStatement = "select item_no from tblAccItemMaster where item_no = ? and item_id !=?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, itemNo);
            prepStmt.setString(2, itemId);

            ResultSet rs = prepStmt.executeQuery();
            val = rs.next();
            Debug.print("AccountsDAO.isItemNoEditExist ......:" + val);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.isItemNoEditExist :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.isItemNoEditExist : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :insertAccountsMasterDetails
     * Description  :This method will insert record into the tblAccAccountsMaster table
     * @ param      :AccountsMasterVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean insertAccountsMasterDetails(HLCAccountsMasterVO accMasterVO) throws SQLException {
        Debug.print("inside AccountsDAO.insertAccountsMasterDetails :");
        boolean status = false;
        try {
            makeConnection();
            String insertStatement = "insert into tblAccAccountsMaster (account_type, account_no, " +
                    " account_name, parent_account_no, account_desc, bank_account_no)" +
                    "values ( ? , ? , ? , ? , ?, ?)";

            prepStmt = con.prepareStatement(insertStatement);


            Debug.print("insertStatement :" + insertStatement);
            prepStmt.setString(1, accMasterVO.getAccount_type());
            prepStmt.setString(2, accMasterVO.getAccount_no());
            prepStmt.setString(3, accMasterVO.getAccount_name());
            prepStmt.setString(4, accMasterVO.getParent_account_no());
            prepStmt.setString(5, accMasterVO.getAccount_desc());
            prepStmt.setString(6, accMasterVO.getBank_account_no());

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                status = true;
            }

            prepStmt.close();
            releaseConnection(con);
            Debug.print("Successfully Inserted insertAccountsMasterDetails ......:" + cnt);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while inserting insertAccountsMasterDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while inserting insertAccountsMasterDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return status;
    }

    /**
     * Name         :isAccountNameExist
     * Description  :This method will check whether accountName is already exist or not from tblAccAccountsMaster table
     * @ param      :accountName
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isAccountNameExist(String accountName) {
        Debug.print("inside AccountsDAO.isAccountNameExist :");
        boolean val = false;
        try {
            makeConnection();
            String selectStatement = "select account_id from tblAccAccountsMaster where account_name = ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accountName);
            ResultSet rs = prepStmt.executeQuery();
            val = rs.next();
            Debug.print("AccountsDAO.isAccountNameExist ......:" + val);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.isAccountNameExist :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.isAccountNameExist : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :isAccountNoExist
     * Description  :This method will check whether accountNo is already exist or not from tblAccAccountsMaster table
     * @ param      :accountNo
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isAccountNoExist(String accountNo) {
        Debug.print("inside AccountsDAO.isAccountNoExist :");
        boolean val = false;
        try {
            makeConnection();
            String selectStatement = "select account_id from tblAccAccountsMaster where account_no = ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accountNo);
            ResultSet rs = prepStmt.executeQuery();
            val = rs.next();
            Debug.print("AccountsDAO.isAccountNoExist ......:" + val);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.isAccountNoExist :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.isAccountNoExist : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :isItemNoEditExist
     * Description  :This method will check whether accountName already exist or not from tblAccAccountsMaster table
     * @ param      :String accountId, String accountName
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isAccountNameEditExist(String accountId, String accountName) throws SQLException {
        Debug.print("inside AccountsDAO.isAccountNameEditExist :");
        boolean val = false;
        try {
            makeConnection();
            String selectStatement = "select account_id from tblAccAccountsMaster where account_name = ? and account_id !=?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accountName);
            prepStmt.setString(2, accountId);

            ResultSet rs = prepStmt.executeQuery();
            val = rs.next();
            Debug.print("AccountsDAO.isAccountNameEditExist ......:" + val);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.isAccountNameEditExist :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.isAccountNameEditExist : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :isAccountNoEditExist
     * Description  :This method will check whether accountNo already exist or not from tblAccAccountsMaster table
     * @ param      :String accountId, String accountNo
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isAccountNoEditExist(String accountId, String accountNo) throws SQLException {
        Debug.print("inside AccountsDAO.isAccountNoEditExist :");
        boolean val = false;
        try {
            makeConnection();
            String selectStatement = "select account_id from tblAccAccountsMaster where account_no = ? and account_id !=?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accountNo);
            prepStmt.setString(2, accountId);

            ResultSet rs = prepStmt.executeQuery();
            val = rs.next();
            Debug.print("AccountsDAO.isAccountNoEditExist ......:" + val);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.isAccountNoEditExist :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.isAccountNoEditExist : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :updateAccountsDetails
     * Description  :This method will update record into the tblAccAccountsMaster table
     * @ param      :AccountsMasterVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateAccountsDetails(HLCAccountsMasterVO accMasterVO) throws SQLException {
        Debug.print("inside AccountsDAO.updateAccountsDetails :");
        boolean val = false;

        try {
            makeConnection();
            String updateStatement = "update tblAccAccountsMaster set account_type = ?, " +
                    " account_no = ?, account_name=?, parent_account_no = ?, " +
                    " account_desc = ?, bank_account_no = ? where account_id= ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            prepStmt.setString(1, accMasterVO.getAccount_type());
            prepStmt.setString(2, accMasterVO.getAccount_no());
            prepStmt.setString(3, accMasterVO.getAccount_name());
            prepStmt.setString(4, accMasterVO.getParent_account_no());
            prepStmt.setString(5, accMasterVO.getAccount_desc());
            prepStmt.setString(6, accMasterVO.getBank_account_no());
            prepStmt.setString(7, accMasterVO.getAccount_id());

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateAccountsDetails ......:" + cnt);
            prepStmt.close();
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateAccountsDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateAccountsDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :selectAccountDetails
     * Description  :This method will get record based on itemId from the tblAccItemMaster table
     * @ param      :itemId
     * @return      :ItemMaster
     * @throws      :SQLException
     */
    public HLCAccountsMasterVO selectAccountDetails(String accountId) throws SQLException {
        Debug.print("AccountsDAO.selectAccountDetails() Calling");
        HLCAccountsMasterVO accountVO = new HLCAccountsMasterVO();
        PreparedStatement prepStmt = null;
        makeConnection();

        try {
            String selectStatement = "select account_id, account_type, account_no, account_name, parent_account_no, " +
                    " account_desc, bank_account_no, add_date from tblAccAccountsMaster where account_id= ?";

            prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1, accountId);
            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                accountId = rs.getString(1);
                String accountType = rs.getString(2);
                String accountNo = rs.getString(3);
                String accountName = rs.getString(4);
                String parentAccountNo = rs.getString(5);
                String accountDesc = rs.getString(6);
                String bankAccountNo = rs.getString(7);
                Date addDate = rs.getDate(8);

                accountVO.setAccount_id(accountId);
                accountVO.setAccount_type(accountType);
                accountVO.setAccount_no(accountNo);
                accountVO.setAccount_name(accountName);
                accountVO.setParent_account_no(parentAccountNo);
                accountVO.setAccount_desc(accountDesc);
                accountVO.setBank_account_no(bankAccountNo);
                accountVO.setAddDate(addDate);
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectAccountDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectAccountDetails() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return accountVO;
    }

    /**
     * Name         :selectAccountTypeMaster
     * Description  :This method will get record from the tblAccServiceTypeMaster table
     * @ param      :Nil
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectAccountTypeMaster() throws SQLException {
        Debug.print("AccountsDAO.tblAccAccountTypeMaster() calling");
        PreparedStatement prepStmt = null;
        ArrayList accTypeList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select account_type_id, account_type_name from tblAccAccountTypeMaster";

            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String accountTypeId = rs.getString(1);
                String accountTypeName = rs.getString(2);
                String accTypeArray[] = {accountTypeId, accountTypeName};
                accTypeList.add(accTypeArray);
            }

            Debug.print("selectAccountTypeMaster() size :" + accTypeList.size());
            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectAccountTypeMaster() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectAccountTypeMaster() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accTypeList;
    }

    /**
     * Name         :selectSubItemList
     * Description  :This method will get record based on serviceTypeName from the tblAccItemMaster table
     * @ param      :serviceTypeName
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectSubItemList(String serviceTypeName) throws SQLException {
        Debug.print("AccountsDAO.selectItemMaster() Calling");
        PreparedStatement prepStmt = null;
        ArrayList itemSubList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select item_id, service_type_name, item_no, parent_item_no, item_desc, " +
                    " rate, account_no, add_date from tblAccItemMaster where service_type_name= ? order by item_no";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, serviceTypeName);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String itemId = rs.getString(1);
                serviceTypeName = rs.getString(2);
                String itemNo = rs.getString(3);
                String parentItemNo = rs.getString(4);
                String itemDesc = rs.getString(5);
                float rate = rs.getFloat(6);
                String accountNo = rs.getString(7);
                Date addDate = rs.getDate(8);

                String subItemArray[] = {itemId, serviceTypeName, itemNo, parentItemNo, itemDesc, accountNo, String.valueOf(rate)};
                itemSubList.add(subItemArray);
            }

            Debug.print("selectSubItemList() size :" + itemSubList.size());

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectSubItemList() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectSubItemList() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return itemSubList;
    }

    /**
     * Name         :selectSubAccountList
     * Description  :This method will get record based on accountType from the tblAccAccountsMaster table
     * @ param      :serviceTypeName
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList selectSubAccountList(String accountType) throws SQLException {
        Debug.print("AccountsDAO.selectSubAccountList() Calling");
        PreparedStatement prepStmt = null;
        ArrayList accountSubList = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select account_id, account_type, account_no, account_name, parent_account_no, " +
                    " account_desc, bank_account_no, add_date from tblAccAccountsMaster where account_type= ? order by account_no";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, accountType);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                String accountId = rs.getString(1);
                accountType = rs.getString(2);
                String accountNo = rs.getString(3);
                String accountName = rs.getString(4);
                String parent_accountNo = rs.getString(5);
                String accountDesc = rs.getString(6);
                String bankAccountNo = rs.getString(7);
                Date addDate = rs.getDate(8);

                String subAccountArray[] = {accountId, accountType, accountNo, accountName, parent_accountNo, accountDesc, bankAccountNo};
                accountSubList.add(subAccountArray);
            }

            Debug.print("selectSubAccountList() size :" + accountSubList.size());

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectSubAccountList() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectSubAccountList() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return accountSubList;
    }

    /**
     * Name         :isTransactionExists
     * Description  :This method will check weather any one of the passed parameters exists in tblAccTransactionTypeDetails table
     * @ param      :item_no, transaction_name
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean isTransactionExists(String transaction_name, String item_no, String class_name) throws SQLException {

        Debug.print("AccountsDAO.isTransactionExists() Calling");
        Debug.print("transaction_name :" + transaction_name);
        Debug.print("item_no :" + item_no);
        Debug.print("class_name :" + class_name);

        PreparedStatement prepStmt = null;
        boolean status = false;

        try {
            makeConnection();

            String selectStatement = "select transaction_name from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS +
                    " where transaction_name = ?";

            Debug.print("selectStatement 1 :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, transaction_name);

            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                status = true;
                Debug.print("record status for transaction_name existance :" + rs.next());
            }

            rs.close();
            prepStmt.close();

            prepStmt = null;

            String selectStatement2 = "select item_no from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS +
                    " where item_no = ? and class_name = ? and transaction_name = ?";

            Debug.print("selectStatement 2 :" + selectStatement2);

            prepStmt = con.prepareStatement(selectStatement2);
            prepStmt.setString(1, item_no);
            prepStmt.setString(2, class_name);
            prepStmt.setString(3, transaction_name);

            rs = prepStmt.executeQuery();

            if (rs.next()) {
                status = true;
                Debug.print("record status for item_no existance :" + rs.next());
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.isTransactionExists() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.isTransactionExists() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return status;
    }

    /**
     * Name         :createItemNoFromAccMaster
     * Description  :This method will get hierarchy of parent item no's details records concatinated as final item no's. 
     * @ param      :itemNo
     * @return      :String
     * @throws      :SQLException
     */
    public String createItemNoFromAccMaster(String itemNo) throws SQLException {
        Debug.print("AccountsDAO.createItemNoFromAccMaster() Calling");
        Debug.print("itemNo :" + itemNo);

        String finalItemNo = "";
        String tempItemNo = itemNo;

        PreparedStatement prepStmt = null;
        ArrayList itemList = new ArrayList();


        try {

            boolean nullStat = true;

            do {
                makeConnection();

                Debug.print("inside loop nullStat :" + nullStat);

                String selectStatement = "select parent_item_no from " + DBHelper.USEA_ACC_ITEM_MASTER +
                        " where item_no= ?";

                Debug.print("selectStatement :" + selectStatement);
                Debug.print("prepStmt.setString(1, tempItemNo) :" + tempItemNo);

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, tempItemNo);

                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()) {
                    String parentItemNo = rs.getString(1);
                    tempItemNo = parentItemNo;
                    Debug.print("parent_item_no retrieved :" + parentItemNo);

                    if (parentItemNo == null) {
                        nullStat = false;
                    } else {
                        itemList.add(parentItemNo);
                    }
                } else {
                    nullStat = false;
                }

                rs.close();
                prepStmt.close();
                releaseConnection(con);

            } while (nullStat);

            Debug.print("end of loop nullStat :" + nullStat);

            Debug.print("createItemNoFromAccMaster() itemList.size :" + itemList.size());

            if (itemList.size() != 0) {
                for (int i = itemList.size() - 1; i >= 0; i--) {
                    String tmpItemNo = (String) itemList.get(i);

                    if (i != 0) {
                        finalItemNo = finalItemNo + tmpItemNo + ":";
                    } else {
                        finalItemNo = finalItemNo + tmpItemNo + ":" + itemNo;
                    }
                }
            } else {
                finalItemNo = itemNo;
            }

            Debug.print("finalItemNo :" + finalItemNo);

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.createItemNoFromAccMaster() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.createItemNoFromAccMaster() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return finalItemNo;
    }

    /**
     * Name         :listAccTxnTypeDetailsOnClass
     * Description  :This method will get the records from tblAccTransactionTypeDetails table based on class_name. 
     * @ param      :class_name
     * @return      :ArrayList
     * @throws      :SQLException
     */
    public ArrayList listAccTxnTypeDetailsOnClass(String class_name) {

        Debug.print("AccountsDAO.listAllAccTxnTypeDetails() class_name :" + class_name);

        PreparedStatement prepStmt = null;
        ArrayList txnDetails = new ArrayList();
        HLCAccTxnTypeDetailVO accTxnTypVO = new HLCAccTxnTypeDetailVO();

        makeConnection();

        try {
            String selectStatement = "select transaction_type_id, account_no, transaction_name, item_no, add_date from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " where class_name = ? ORDER BY transaction_name";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, class_name);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                accTxnTypVO = new HLCAccTxnTypeDetailVO();

                accTxnTypVO.setTransaction_type_id(rs.getString(1));
                accTxnTypVO.setTransaction_name(rs.getString(3));
                accTxnTypVO.setAccount_no(rs.getString(2));
                accTxnTypVO.setItem_no(rs.getString(4));
                accTxnTypVO.setAdd_date(rs.getDate(5));

                txnDetails.add(accTxnTypVO);
            }

            Debug.print("listAllAccTxnTypeDetails() size :" + txnDetails.size());

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.listAllAccTxnTypeDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.listAllAccTxnTypeDetails() :" + e.getMessage());
        }
        return txnDetails;
    }

    /**
     * Name         :updateAccTxnTypeDetails
     * Description  :This method will update record into the tblAccTxnTypeDetails table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateAccTxnTypeDetails(HLCAccTxnTypeDetailVO accTxnTypVO) {
        Debug.print("inside AccountsDAO.updateAccTxnTypeDetails :");
        Debug.print("transaction_type_id :" + accTxnTypVO.toString());
        boolean val = false;

        try {

            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " set transaction_name = ?, transaction_type = ?, account_no = ?, sub_account_no = ?, " +
                    " item_no = ?, class_name = ? where transaction_type_id = ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            prepStmt.setString(1, accTxnTypVO.getTransaction_name());
            prepStmt.setString(2, accTxnTypVO.getTransaction_type());
            prepStmt.setString(3, accTxnTypVO.getAccount_no());
            prepStmt.setString(4, accTxnTypVO.getSub_account_no());
            prepStmt.setString(5, accTxnTypVO.getItem_no());
            prepStmt.setString(6, accTxnTypVO.getClass_name());
            prepStmt.setString(7, accTxnTypVO.getTransaction_type_id());

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateAccTxnTypeDetails ......:" + cnt);

            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateAccTxnTypeDetails :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateAccTxnTypeDetails : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :selectAccTransactionTypeDetail
     * Description  :This method will get record based on transaction_type_id from the tblAccTransactionTypeDetails table
     * @ param      :transaction_type_id
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
    public HLCAccTxnTypeDetailVO selectAccTransactionTypeDetail(String transaction_type_id) throws SQLException {
        Debug.print("AccountsDAO.selectAccTransactionTypeDetail() Calling");
        PreparedStatement prepStmt = null;
        HLCAccTxnTypeDetailVO accTxnTypeDetail = new HLCAccTxnTypeDetailVO();
        makeConnection();

        try {
            String selectStatement = "select transaction_type_id, transaction_name, transaction_type, account_no, sub_account_no, item_no, " +
                    " class_name from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " where transaction_type_id = ?";

            Debug.print("selectStatement :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, transaction_type_id);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {

                accTxnTypeDetail = new HLCAccTxnTypeDetailVO();

                accTxnTypeDetail.setTransaction_type_id(rs.getString(1));
                accTxnTypeDetail.setTransaction_name(rs.getString(2));
                accTxnTypeDetail.setTransaction_type(rs.getString(3));
                accTxnTypeDetail.setAccount_no(rs.getString(4));
                accTxnTypeDetail.setSub_account_no(rs.getString(5));
                accTxnTypeDetail.setItem_no(rs.getString(6));
                accTxnTypeDetail.setClass_name(rs.getString(7));

            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectAccTransactionTypeDetail() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectAccTransactionTypeDetail() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return accTxnTypeDetail;
    }

    /**
     * Name         :getOverPayAccTransactionTypeDetail
     * Description  :This method will get record based on transaction_type_id from the tblAccTransactionTypeDetails table
     * @ param      :nil
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
    public HLCAccTxnTypeDetailVO getOverPayAccTransactionTypeDetail() throws SQLException {
        Debug.print("AccountsDAO.getOverPayAccTransactionTypeDetail() Calling");
        PreparedStatement prepStmt = null;
        HLCAccTxnTypeDetailVO accTxnTypeDetail = new HLCAccTxnTypeDetailVO();
        makeConnection();

        try {
            String selectStatement = "select transaction_type_id, transaction_name, transaction_type, account_no, sub_account_no, item_no, " +
                    " class_name from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " where transaction_name = ?";

            Debug.print("selectStatement :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, "Over Payment");

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {

                accTxnTypeDetail = new HLCAccTxnTypeDetailVO();

                accTxnTypeDetail.setTransaction_type_id(rs.getString(1));
                accTxnTypeDetail.setTransaction_name(rs.getString(2));
                accTxnTypeDetail.setTransaction_type(rs.getString(3));
                accTxnTypeDetail.setAccount_no(rs.getString(4));
                accTxnTypeDetail.setSub_account_no(rs.getString(5));
                accTxnTypeDetail.setItem_no(rs.getString(6));
                accTxnTypeDetail.setClass_name(rs.getString(7));

            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getOverPayAccTransactionTypeDetail() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getOverPayAccTransactionTypeDetail() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return accTxnTypeDetail;
    }

    /**
     * Name         :getAccTransactionTypeDetailBasedOnTran_Name
     * Description  :This method will get record based on transaction_name from the tblAccTransactionTypeDetails table
     * @ param      :transaction_name
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
    public HLCAccTxnTypeDetailVO getAccTransactionTypeDetailBasedOnTran_Name(String transaction_name) throws SQLException {
        Debug.print("AccountsDAO.getAccTransactionTypeDetailBasedOnTran_Name() Calling");
        Debug.print("transaction_name :" + transaction_name);

        PreparedStatement prepStmt = null;
        HLCAccTxnTypeDetailVO accTxnTypeDetail = new HLCAccTxnTypeDetailVO();
        makeConnection();

        try {
            String selectStatement = "select transaction_type_id, transaction_name, transaction_type, account_no, sub_account_no, item_no, " +
                    " class_name from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " where transaction_name = ?";

            Debug.print("selectStatement :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, transaction_name);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {

                accTxnTypeDetail = new HLCAccTxnTypeDetailVO();

                accTxnTypeDetail.setTransaction_type_id(rs.getString(1));
                accTxnTypeDetail.setTransaction_name(rs.getString(2));
                accTxnTypeDetail.setTransaction_type(rs.getString(3));
                accTxnTypeDetail.setAccount_no(rs.getString(4));
                accTxnTypeDetail.setSub_account_no(rs.getString(5));
                accTxnTypeDetail.setItem_no(rs.getString(6));
                accTxnTypeDetail.setClass_name(rs.getString(7));

            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getAccTransactionTypeDetailBasedOnTran_Name() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAccTransactionTypeDetailBasedOnTran_Name() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return accTxnTypeDetail;
    }

    /**
     * Name         :searchQBTransctionDetails
     * Author       :Karthikeyan.M
     * Description  :This method will select already Syncronized QB Transction Details from tblAccTransactionDetails table
     * @ param      :Date transDate, String checkNo
     * @return      :ArrayList
     * @throws      :nil
     */
    public ArrayList searchQBTransctionDetails(Date transDate, String checkNo) {
        Debug.print("AccountsDAO.searchQBTransctionDetails():");

        if (transDate != null) {
            Debug.print("transDate :" + transDate.toString());
        }

        Debug.print("checkNo :" + checkNo);

        ArrayList transactionList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        try {

            makeConnection();

            String selectStatement = "";

            if (transDate != null && checkNo != null && checkNo.trim().length() != 0) {

                Debug.print("inside transaction Date and check no selected block :");

                /*selectStatement = "select A.payment_id, A.item_no, A.payment_mode, A.class, A.amount as tot_amount, A.description, "
                +"B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B "
                +"where A.sync_status = 0 and A.active_status = 1 and A.payment_mode in ('Check') and A.reconcile_amount = 0 and (A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) "
                +"and CAST(FLOOR(CAST(B.check_date AS float)) AS datetime) = ? and B.check_number = ? "
                +"UNION ALL select A.payment_id, A.item_no, A.payment_mode, A.class, A.reconcile_amount as tot_amount, A.description, "
                +"B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B "
                +"where A.sync_status = 0 and A.active_status = 1 and A.payment_mode in ('Check') and A.reconcile_amount > 0 and "
                +"(A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) and CAST(FLOOR(CAST(B.check_date AS float)) AS datetime) = ? "
                +"and B.check_number = ? order by payment_id";*/

                selectStatement = "select A.payment_id, A.item_no, A.payment_mode, A.class, A.amount as tot_amount, A.description, " + "B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B " + "where A.sync_status = 0 and A.payment_mode in ('Check') and A.reconcile_amount = 0 and (A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) " + "and CAST(FLOOR(CAST(B.check_date AS float)) AS datetime) = ? and B.check_number = ? " + "UNION ALL select A.payment_id, A.item_no, A.payment_mode, A.class, A.reconcile_amount as tot_amount, A.description, " + "B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B " + "where A.sync_status = 0 and A.payment_mode in ('Check') and A.reconcile_amount > 0 and " + "(A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) and CAST(FLOOR(CAST(B.check_date AS float)) AS datetime) = ? " + "and B.check_number = ? order by payment_id";

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setDate(1, DBHelper.toSQLDate(transDate));
                prepStmt.setString(2, checkNo);
                prepStmt.setDate(3, DBHelper.toSQLDate(transDate));
                prepStmt.setString(4, checkNo);

            } else if (transDate != null) {

                Debug.print("inside transaction Date selected block :");

                /*selectStatement = "select A.payment_id, A.item_no, A.payment_mode, A.class, A.amount as tot_amount, A.description, "
                +"B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B "
                +"where A.sync_status = 0 and A.active_status = 1 and A.payment_mode in ('Check') and A.reconcile_amount = 0 and (A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) "
                +"and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? "
                +"UNION ALL select A.payment_id, A.item_no, A.payment_mode, A.class, A.reconcile_amount as tot_amount, A.description, "
                +"B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B "
                +"where A.sync_status = 0 and A.active_status = 1 and A.payment_mode in ('Check') and A.reconcile_amount > 0 and "
                +"(A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? "
                +"order by payment_id";*/

                selectStatement = "select A.payment_id, A.item_no, A.payment_mode, A.class, A.amount as tot_amount, A.description, " + "B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B " + "where A.sync_status = 0 and A.payment_mode in ('Check') and A.reconcile_amount = 0 and (A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) " + "and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " + "UNION ALL select A.payment_id, A.item_no, A.payment_mode, A.class, A.reconcile_amount as tot_amount, A.description, " + "B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B " + "where A.sync_status = 0 and A.payment_mode in ('Check') and A.reconcile_amount > 0 and " + "(A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) and CAST(FLOOR(CAST(transaction_date AS float)) AS datetime) = ? " + "order by payment_id";

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setDate(1, DBHelper.toSQLDate(transDate));
                prepStmt.setDate(2, DBHelper.toSQLDate(transDate));

            } else if (checkNo != null && checkNo.trim().length() != 0) {

                Debug.print("inside check no selected block :");

                /*selectStatement = "select A.payment_id, A.item_no, A.payment_mode, A.class, A.amount as tot_amount, A.description, "
                +"B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B "
                +"where A.sync_status = 0 and A.active_status = 1 and A.payment_mode in ('Check') and A.reconcile_amount = 0 and (A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) "
                +"and B.check_number = ? "
                +"UNION ALL select A.payment_id, A.item_no, A.payment_mode, A.class, A.reconcile_amount as tot_amount, A.description, "
                +"B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B "
                +"where A.sync_status = 0 and A.active_status = 1 and A.payment_mode in ('Check') and A.reconcile_amount > 0 and "
                +"(A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) "
                +"and B.check_number = ? order by payment_id";*/

                selectStatement = "select A.payment_id, A.item_no, A.payment_mode, A.class, A.amount as tot_amount, A.description, " + "B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B " + "where A.sync_status = 0 and A.payment_mode in ('Check') and A.reconcile_amount = 0 and (A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) " + "and B.check_number = ? " + "UNION ALL select A.payment_id, A.item_no, A.payment_mode, A.class, A.reconcile_amount as tot_amount, A.description, " + "B.bank_name, B.check_date, B.check_number, B.check_name, B.check_amount from tblAccTransactionDetails A, tblUserPaymentDetails B " + "where A.sync_status = 0 and A.payment_mode in ('Check') and A.reconcile_amount > 0 and " + "(A.payment_id = B.payment_id or A.payment_id = B.parent_payment_id) " + "and B.check_number = ? order by payment_id";

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, checkNo);
                prepStmt.setString(2, checkNo);

            }

            Debug.print("SQL Query:" + selectStatement);

            rs = prepStmt.executeQuery();
            while (rs.next()) {
                String payment_id = rs.getString(1);
                String itemNo = rs.getString(2);
                String paymentMode = rs.getString(3);
                String accountClass = rs.getString(4);
                double amount = rs.getDouble(5);
                String description = rs.getString(6);
                String bankName = rs.getString(7);
                Date checkDate = rs.getDate(8);
                String checkNum = rs.getString(9);
                String checkName = rs.getString(10);
                float checkAmt = rs.getFloat(11);

                HLCMemberShipTransactionVO transVO = new HLCMemberShipTransactionVO();
                transVO.setPayment_id(payment_id);
                transVO.setItemNo(itemNo);
                transVO.setPaymentMode(paymentMode);
                transVO.setAccountClass(accountClass);
                transVO.setAmount(amount);
                transVO.setDesc(description);

                transVO.setBank_name(bankName);
                transVO.setCheck_date(checkDate);
                transVO.setCheck_number(checkNum);
                transVO.setCheck_name(checkName);
                transVO.setCheck_amount(checkAmt);

                transactionList.add(transVO);
            }
            Debug.print("Transction List Size():" + transactionList.size());

            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.searchQBTransctionDetails():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.searchQBTransctionDetails():" + e.getMessage());
        }
        return transactionList;
    }

    /**
     * Name         :updateAccTxnDetailsMissingCheck
     * Description  :This method will update active_status record into the tblAccTxnDetails table
     * @ param      :paymentId, active_status
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateAccTxnDetailsMissingCheck(String paymentId, boolean active_status) throws SQLException {
        Debug.print("inside AccountsDAO.updateAccTxnDetailsMissingCheck :");
        Debug.print("paymentId :" + paymentId);
        Debug.print("active_status :" + active_status);

        boolean val = false;

        try {

            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set  active_status = ? where payment_id = ?";

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            prepStmt.setBoolean(1, active_status);
            prepStmt.setString(2, paymentId);

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateAccTxnDetailsMissingCheck ......:" + cnt);

            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateAccTxnDetailsMissingCheck :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateAccTxnDetailsMissingCheck : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :updateAccTxnTypeDetails
     * Description  :This method will update record into the tblAccTxnTypeDetails table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateRecouncilTxnDateOnParentTxnId(String parentTxnId, String txnId) {
        Debug.print("inside AccountsDAO.updateRecouncilTxnDateOnParentTxnId :");
        Debug.print("parentTxnId  :" + parentTxnId);
        Debug.print("txnId  :" + txnId);

        boolean val = false;

        try {

            makeConnection();

            String selectStatement = "";

            Debug.print("inside transaction Date and check no selected block :");

            selectStatement = "select transaction_date from " + DBHelper.USEA_ACC_TXN_DETAILS + " where transaction_id = ? ";
            Debug.print("selectStatement for getting transaction_date :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, parentTxnId);
            rs = prepStmt.executeQuery();

            Timestamp ts = null;

            while (rs.next()) {

                ts = rs.getTimestamp(1);
                Debug.print("Timestamp value for parentTxnId is :" + ts.toString());
            }

            if (ts != null) {
                String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set transaction_date = ? where transaction_id = ?";
                Debug.print("updateStatement :" + updateStatement);

                prepStmt = con.prepareStatement(updateStatement);
                Debug.print("Query Log :" + updateStatement);

                prepStmt.setTimestamp(1, ts);
                prepStmt.setString(2, txnId);

                int cnt = prepStmt.executeUpdate();

                if (cnt > 0) {
                    val = true;
                }

                Debug.print("Successfully updated AccountsDAO.updateRecouncilTxnDateOnParentTxnId ......:" + cnt);
            }

            prepStmt.close();


        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateRecouncilTxnDateOnParentTxnId :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateRecouncilTxnDateOnParentTxnId : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    /**
     * Name         :updateRecouncilTxnDateOnTxnId
     * Description  :This method will update record into the tblAccTxnTypeDetails table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateRecouncilTxnDateOnTxnId(String txnId) {
        Debug.print("inside AccountsDAO.updateRecouncilTxnDateOnTxnId :");
        Debug.print("txnId  :" + txnId);

        boolean val = false;

        try {

            makeConnection();

            String updateStatement = "update " + DBHelper.USEA_ACC_TXN_DETAILS + " set transaction_date = ? where transaction_id = ?";
            Debug.print("updateStatement :" + updateStatement);

            prepStmt = con.prepareStatement(updateStatement);
            Debug.print("Query Log :" + updateStatement);

            java.sql.Timestamp ts = new Timestamp(new Date().getTime());
            Debug.print("ts.toString() :" + ts.toString());

            prepStmt.setTimestamp(1, ts);
            prepStmt.setString(2, txnId);

            int cnt = prepStmt.executeUpdate();

            if (cnt > 0) {
                val = true;
            }

            Debug.print("Successfully updated AccountsDAO.updateRecouncilTxnDateOnTxnId ......:" + cnt);

            prepStmt.close();


        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception while AccountsDAO.updateRecouncilTxnDateOnTxnId :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("Error while AccountsDAO.updateRecouncilTxnDateOnTxnId : " + e.getMessage());
        } finally {
            releaseConnection(con);
        }
        return val;
    }

    public boolean insertMembershipRefundDetails(String userId, String comments, float balanceAmount) {
        Debug.print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%HorseRegDAO.insertMembershipRefundDetails():%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        boolean result = false;
        PreparedStatement prepStmt = null;

        try {
            makeConnection();
            String insertStatement = "insert into tblMembershipRefundDetails " +
                    " (user_id, comments, balance_amount, refund_status)" +
                    " values ( ? , ? , ?, ?) ";

            prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, userId);
            prepStmt.setString(2, comments);
            prepStmt.setFloat(3, balanceAmount);
            prepStmt.setString(4, "Pending");

            int cnt = prepStmt.executeUpdate();
            //prepStmt.close();
            Debug.print("Record Inserted succefully in insertMembershipRefundDetails Details: " + cnt);
            if (cnt >= 1) {
                result = true;
            }
        // releaseConnection(con);
        } catch (SQLException sql) {
            Debug.print("SQL Exception in HorseRegDAO.insertMembershipRefundDetails():" + sql.getMessage());
        } catch (Exception e) {
            Debug.print("General Exception  in HorseRegDAO.insertMembershipRefundDetails():" + e.getMessage());
        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                }
                if (!con.isClosed()) {
                    releaseConnection(con);
                }
            } catch (Exception exp) {
                Debug.print("General Exception  in HorseRegDAO.insertMembershipRefundDetails():" + exp.getMessage());
            }
        }
        return result;
    }

    public boolean isClassExist(String classname) {
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select class_id from " + DBHelper.USEA_ClASS + " where class_name = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, classname);

            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from HorseBreedDAO.isHorseBreedExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.isHorseBreedExist:" + e.getMessage());
        }
        Debug.print("HorseBreedDAO.isHorseBreedExist():" + result);
        return result;
    }

    public boolean insertClass(String classname) {
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_ClASS +
                    " (class_name)" +
                    " values (?) ";

            prepStmt = con.prepareStatement(insertStatement);

            if (classname != null && classname.trim().length() != 0) {

                prepStmt.setString(1, classname);
                int cnt = prepStmt.executeUpdate();

                Debug.print("Record Inserted succefully in " + cnt);
                if (cnt >= 1) {
                    result = true;
                }


            }
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in HorseBreedDAO.insertHorseColor():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in HorseBreedDAO.insertHorseBreed():" + e.getMessage());
        }
        return result;
    }

    public ArrayList getAllClassDetails() {
        ArrayList list = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select class_id, " +
                    "class_name from " + DBHelper.USEA_ClASS + " order by class_name";
            prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                String classId = rs.getString(1);
                String className = rs.getString(2);
                String cd[] = {classId, className};
                list.add(cd);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            Debug.print("SQL Exception in AccountsDAO.getAllClassDetails()():" + sql.getMessage());
            releaseConnection(con);
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAllClassDetails():" + e.getMessage());
        }
        return list;
    }

    public ArrayList selectClassNameById(String classId) {
        Debug.print("inside selectClassNameById");
        ArrayList list = new ArrayList();
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "select class_id,  " +
                    " class_name from " + DBHelper.USEA_ClASS +
                    " where class_id= ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, classId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                String tempClassId = rs.getString(1);
                //String breedCode = rs.getString(2);
                String className = rs.getString(2);
                Debug.print("tempClassId " + tempClassId);
                Debug.print("className " + className);

                String cd[] = {tempClassId, className};
                list.add(cd);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.selectClassNameById():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.selectClassNameById():" + e.getMessage());
        }
        return list;
    }

    public boolean isClassNameEditExist(String classId, String className) {
        Debug.print("AccountsDAO.isClassNameEditExist():" + classId);
        boolean result = true;
        makeConnection();
        try {
            String selectStatement = "select class_id from " + DBHelper.USEA_ClASS + " where class_name = ? and " +
                    " class_id!=?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, className);
            // prepStmt.setString(2,breedCode);
            prepStmt.setString(2, classId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from AccountsDAO.isClassNameEditExist" + e.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.isClassNameEditExist:" + e.getMessage());
        }
        Debug.print("AccountsDAO.isClassNameEditExist():" + result);
        return result;
    }

    public boolean updateClassName(String classId, String className) {
        Debug.print("AccountsDAO.updateClassName():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_ClASS + " set class_name = ? " +
                    " where class_id = ?";

            prepStmt = con.prepareStatement(updateStatement);


            prepStmt.setString(1, className);
            prepStmt.setString(2, classId);

            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in updateClassName : " + cnt);
            if (cnt >= 1) {
                result = true;
            }
            Debug.print("AccountsDAO updateClassName :" + result);
            prepStmt.close();
            releaseConnection(con);
        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.updateClassName():" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.updateClassName():" + e.getMessage());
        }
        return result;
    }

    public HLCAccTxnTypeDetailVO getAccTransacTypDetsForAreaRiderProg(String transacTypId) throws SQLException {
        Debug.print("AccountsDAO.getAccTransacTypDetsForAreaRiderProg() :");
        Debug.print("transacTypId :" + transacTypId);

        PreparedStatement prepStmt = null;

        HLCAccTxnTypeDetailVO accDet = new HLCAccTxnTypeDetailVO();
        makeConnection();
        try {

            String selectStatement = "select A.transaction_type_id, A.transaction_name, A.transaction_type, A.account_no, A.sub_account_no, A.item_no, A.class_name, " +
                    "B.transaction_type_id from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " A ," + DBHelper.USEA_AREA_MEMBER_TYPE_MASTER + " B where A.transaction_type_id = B.transaction_type_id and B.transaction_type_id = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, transacTypId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccTxnTypeDetailVO();

                accDet.setTransaction_type_id(rs.getString(1));
                accDet.setTransaction_name(rs.getString(2));
                accDet.setTransaction_type(rs.getString(3));
                accDet.setAccount_no(rs.getString(4));
                accDet.setSub_account_no(rs.getString(5));
                accDet.setItem_no(rs.getString(6));
                accDet.setClass_name(rs.getString(7));
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getAccTransacTypDetsForAreaRiderProg() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAccTransacTypDetsForAreaRiderProg() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accDet;
    }

    public HLCAccTxnTypeDetailVO getAccTransacTypDetsForEveOrgEndorsed(String transacTypId) throws SQLException {
        Debug.print("AccountsDAO.getAccTransacTypDetsForEveOrgEndorsed() :");
        Debug.print("transacTypId :" + transacTypId);

        PreparedStatement prepStmt = null;

        HLCAccTxnTypeDetailVO accDet = new HLCAccTxnTypeDetailVO();
        makeConnection();
        try {

            String selectStatement = "select A.transaction_type_id, A.transaction_name, A.transaction_type, A.account_no, A.sub_account_no, A.item_no, A.class_name, " +
                    "B.transaction_type_id from " + DBHelper.USEA_ACC_TXN_TYPE_DETAILS + " A ," + DBHelper.USEA_MEE_EVENT_REG_Price_MASTER + " B where A.transaction_type_id = B.transaction_type_id and B.transaction_type_id = ? ";

            Debug.print("Query Log :" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, transacTypId);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                accDet = new HLCAccTxnTypeDetailVO();

                accDet.setTransaction_type_id(rs.getString(1));
                accDet.setTransaction_name(rs.getString(2));
                accDet.setTransaction_type(rs.getString(3));
                accDet.setAccount_no(rs.getString(4));
                accDet.setSub_account_no(rs.getString(5));
                accDet.setItem_no(rs.getString(6));
                accDet.setClass_name(rs.getString(7));
            }

            rs.close();
            prepStmt.close();

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getAccTransacTypDetsForEveOrgEndorsed() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAccTransacTypDetsForEveOrgEndorsed() :" + e.getMessage());
        } finally {
            releaseConnection(con);
        }

        return accDet;
    }

    public ArrayList getDailyPaymentDetails(int year, int month, String paymentMode) {

        Debug.print("AccountsDAO.getPaymentDetails() year :" + year);
        Debug.print("AccountsDAO.getPaymentDetails() month :" + month);
        Debug.print("AccountsDAO.getPaymentDetails() paymentMode :" + paymentMode);

        PreparedStatement prepStmt = null;
        ArrayList paymentDetails = new ArrayList();
        int noOfDays = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        makeConnection();

        try {
            for (int i = 1; i <= noOfDays; i++) {
                Date tempTransDate = calendar.getTime();
                Debug.print("AccountsDAO.getPaymentDetails() tempTransDate :" + tempTransDate);
                String selectStatement = "select sum(amount) as amount from " + DBHelper.USEA_ACC_TXN_DETAILS + " where payment_mode= ?" +
                        " and DATEADD(d, 0, DATEDIFF(d, 0, transaction_date)) = ?";

                Debug.print("Query Log getPaymentDetails:" + selectStatement);

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, paymentMode);
                prepStmt.setDate(2, DBHelper.toSQLDate(tempTransDate));

                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                    double amount = rs.getFloat(1);
                    //double amountArray[] = {amount};
                    System.out.println("amount value in AccountsDAO.getPaymentDetails():::::::::::::::::"+amount);
                    paymentDetails.add(new Double(amount));
                }

                Debug.print("paymentDetails size :" + paymentDetails.size());
                calendar.add(Calendar.DATE, 1);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getPaymentDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getPaymentDetails() :" + e.getMessage());
        }
        return paymentDetails;
    }

    public ArrayList getMonthlyPaymentDetails(int year, String paymentMode) {

        Debug.print("AccountsDAO.getMonthlyPaymentDetails() year :" + year);
        Debug.print("AccountsDAO.getMonthlyPaymentDetails() paymentMode :" + paymentMode);
        PreparedStatement prepStmt = null;
        ArrayList paymentDetails = new ArrayList();
        makeConnection();

        try {
            for (int i = 1; i <= 12; i++) {
                String selectStatement = "select sum(amount) as amount from " + DBHelper.USEA_ACC_TXN_DETAILS + " where payment_mode= ?" +
                        " and month(transaction_date)= ? and year(transaction_date)=?";

                Debug.print("Query Log getMonthlyPaymentDetails:" + selectStatement);

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, paymentMode);
                prepStmt.setInt(2, i);
                prepStmt.setInt(3, year);

                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                    double amount = rs.getFloat(1);
                    //double amountArray[] = {amount};
                    paymentDetails.add(new Double(amount));
                }

                Debug.print("getMonthlyPaymentDetails size :" + paymentDetails.size());
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getMonthlyPaymentDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getMonthlyPaymentDetails() :" + e.getMessage());
        }
        return paymentDetails;
    }

    public ArrayList getAreaPaymentDetails(int year) {

        //Debug.print("AccountsDAO.getMonthlyPaymentDetails() year :" + year);
        PreparedStatement prepStmt = null;
     PreparedStatement pStmt = null;

        Statement stmt = null;

        ArrayList areaPaymentDetails = new ArrayList();
        ArrayList areaAmount = null;
        makeConnection();

        try {
            String selectStatement = "select user_id, sum(amount) as amount from " + DBHelper.USEA_ACC_TXN_DETAILS + " where year(transaction_date)= ?  group by user_id";
            String psStmt = "select B.area_name, B.area_code from " + DBHelper.USEA_MEE_MAP_STATE_ZIP + " A, " + DBHelper.USEA_MEE_AREA_MASTER + " B where A.area_id = B.area_id " +

                    "and (select top 1 zip from tblContactDetails where user_id=?) " +
                    "between A.zip_code_from and A.zip_code_to";
            Debug.print("Query Log getAreaPaymentDetails:" + selectStatement);
           
               pStmt = con.prepareStatement(selectStatement);
                pStmt.setInt(1,year);
               ResultSet stmtRS = pStmt.executeQuery();
              
            while (stmtRS.next()) {

                prepStmt = con.prepareStatement(psStmt);
                prepStmt.setString(1, stmtRS.getString(1));

                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {


                    String areaName = rs.getString(1);
                    double amount = stmtRS.getFloat(2);
                    String areaCode = rs.getString(2);
                    areaAmount = new ArrayList();
                    areaAmount.add(areaCode);
                    areaAmount.add(areaName);
                    areaAmount.add(new Double(amount));

                    areaPaymentDetails.add(areaAmount);
                }

                Debug.print("getAreaPaymentDetails size :" + areaPaymentDetails.size());
                
                rs.close();
                prepStmt.close();
            }
              pStmt.close();
            releaseConnection(con);

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getAreaPaymentDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getAreaPaymentDetails() :" + e.getMessage());
        }
        return areaPaymentDetails;
    }

    public ArrayList getMemberRegistrationDetails(String year) {

        Debug.print("AccountsDAO.getMemberRegistrationDetails() year :" + year);
        PreparedStatement prepStmt = null;
        ArrayList regDetails = new ArrayList();
        makeConnection();

        try {
            String selectStatement = "select count(A.membership_type_id) from tblMemberDetails A, tblMembershipTypeMaster B, " +
                    "tblUserTypeMaster C  where year(activation_date) = ? and C.user_type_id='caafe0ba-6a20-49bf-aa86-8f95f7d9346d' " +
                    "and A.membership_type_id = B.membership_type_id and year(A.activation_date) = B.membership_year " +
                    "group by B.membership_type_name";


            Debug.print("Query Log getMemberRegistrationDetails:" + selectStatement);

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, year);

            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                regDetails.add(new Double(count));
            }

            Debug.print("getMemberRegistrationDetails size :" + regDetails.size());

            rs.close();
            prepStmt.close();
            releaseConnection(con);

        } catch (SQLException sql) {
            releaseConnection(con);
            Debug.print("SQL Exception in AccountsDAO.getMemberRegistrationDetails() :" + sql.getMessage());
        } catch (Exception e) {
            releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getMemberRegistrationDetails() :" + e.getMessage());
        }
        return regDetails;
    }
    
   public HashMap getYearBasedSalesAmount()
   {
        Debug.print("AccountsDAO.getYearBasedSalesAmount() year :");
        HashMap yearViseAmount=new HashMap();
        
        double mem1=0;double mem2=0;double mem3=0;double mem4=0;double mem5=0;double mem6=0;
        makeConnection();
        try
        {
          //String query="SELECT Year(transaction_date) as Year,SUM(amount) as Sales_Amt FROM "+DBHelper.USEA_ACC_TXN_DETAILS+" GROUP BY Year(transaction_date)"; 
            String query=null;
            String year[]={"2006","2007","2008","2009"};
             for(int i=0;i<year.length;i++)
             {
                 ArrayList member_amount=new ArrayList();
                 System.out.println("Before Query");
                 query="select sum(C.amount) as amount, B.membership_type_name from tblMembershipTypeMaster B ,tblMemberDetails A,tblAccTransactionDetails C where A.membership_type_id = B.membership_type_id and B.membership_year=year(C.transaction_date) and B.membership_year='"+year[i]+"' and year(C.transaction_date)='"+year[i]+"' group by C.user_id,B.membership_type_id,B.membership_type_name,B.membership_year ";
                 System.out.println("After Query");
          PreparedStatement pt=con.prepareStatement(query);
          ResultSet rs=pt.executeQuery();
          while(rs.next())
          {
              double amount=rs.getDouble(1);
                System.out.println("Amount of Diufferenct members"+amount);   
              String memberShipType=rs.getString(2);
               if(memberShipType.equalsIgnoreCase("Subscribing Member"))
                     mem1=mem1+amount;
              if(memberShipType.equalsIgnoreCase("Junior Member"))
                     mem2=mem2+amount;
              if(memberShipType.equalsIgnoreCase("Non-Competing Member"))
                     mem3=mem3+amount;
              if(memberShipType.equalsIgnoreCase("Full Member"))
                     mem4=mem4+amount;
              if(memberShipType.equalsIgnoreCase("Family Member"))
                     mem5=mem5+amount;
              if(memberShipType.equalsIgnoreCase("Life Member"))
                     mem6=mem6+amount;
                   
                   
              //yearViseAmount.put(new 1, new Double(amount));
              
               }
                member_amount.add(0,new Double(mem1));
                member_amount.add(1,new Double(mem2));
                member_amount.add(2,new Double(mem3));
                member_amount.add(3,new Double(mem4));
                member_amount.add(4,new Double(mem5));
                member_amount.add(5,new Double(mem6));
                yearViseAmount.put(new Integer(Integer.parseInt(year[i])), member_amount);
             } // Loop ends here
          
            rs.close();
           
        releaseConnection(con);
        
        }
           catch(Exception e)  
           {
              releaseConnection(con);
            Debug.print("General Exception  in AccountsDAO.getYearBasedSalesAmount() :" + e.getMessage()); 
           }
        return yearViseAmount;
   }
   public double getTotalDonationAmount(Date fromDate,Date toDate)
   {
       Debug.print("AccountsDAO.getTotalDonationAmount()  :");
       ResultSet rs=null;PreparedStatement ps=null;
       double totalAmount=0;
       java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate); 
       String query="select sum(donation_price) from tblMemberDonationDetails where (add_date>=? and add_date<=?)";
       makeConnection();
       try
       {
           ps=con.prepareStatement(query);
           ps.setDate(1,startDate);
           ps.setDate(2,endDate);
           rs=ps.executeQuery();
           while(rs.next())
               totalAmount=Double.parseDouble(rs.getString(1));
           rs.close();ps.close();releaseConnection(con);
       }
       catch(SQLException e)
       {
           Debug.print(e.getMessage());
           e.printStackTrace();
           releaseConnection(con);
       }
       return totalAmount;
   }
   public ArrayList getTotalDonationAmountForAllDonationItems(Date startDate,Date endDate)
   {
        Debug.print("AccountsDAO.getTotalDonationAmount()  :");
        ArrayList totalDonationAmountForAllDonationItems=new ArrayList();
        HashMap donationAmountForEachDonation=null;
        ResultSet rs=null;PreparedStatement ps=null;
        ResultSet rs1=null;PreparedStatement ps1=null;
        String donation_id=null,donation_name=null;double totalDonationAmount=0;
        java.sql.Date fromDate=DBHelper.toSQLDate(startDate);
        java.sql.Date toDate=DBHelper.toSQLDate(endDate); 
        String query_1="select donation_id,donation_account_name from "+DBHelper.USEA_DONATION_DETAILS+" where active_status='true' and donation_only_status='true'";
        String query_2="select sum(donation_price) from tblMemberDonationDetails where donation_id=? and (add_date>=? and add_date<=?)";
        makeConnection();
        try
        {
           ps=con.prepareStatement(query_1);
           rs=ps.executeQuery();
           while(rs.next())
           {
               donation_id=rs.getString(1);
               donation_name=rs.getString(2);
               ps1=con.prepareStatement(query_2);
               ps1.setString(1,donation_id);
               ps1.setDate(2,fromDate);
               ps1.setDate(3,toDate);
               rs1=ps1.executeQuery();
               while(rs1.next())
                   totalDonationAmount =Double.parseDouble(rs1.getString(1));
               rs1.close();ps1.close();
               donationAmountForEachDonation=new HashMap();
              donationAmountForEachDonation.put(donation_name,new Double(totalDonationAmount)); 
             Debug.print("Donation Name is:"+donation_name+" with Donation Amount "+totalDonationAmount);   
             totalDonationAmountForAllDonationItems.add(donationAmountForEachDonation);
           }
           rs.close();ps.close();
        }
        catch(Exception r)
        {
            releaseConnection(con);
            Debug.print("Exception Occured "+r.getMessage());
            r.printStackTrace();
        }
        releaseConnection(con);
       return totalDonationAmountForAllDonationItems;
       
       
        
   }
   
   public double getDonationAmountSpecificDonationItem(Date startDate,Date endDate,String donation_id)
   {
       Debug.print("AccountsDAO.getTotalDonationAmount()  :");
       String query_1="select sum(donation_price) from tblMemberDonationDetails where donation_id=? and (add_date>=? and add_date<=?)";
        ResultSet rs=null;PreparedStatement ps=null;double donationAmount=0;
        java.sql.Date fromDate=DBHelper.toSQLDate(startDate);
        java.sql.Date toDate=DBHelper.toSQLDate(endDate);
        makeConnection();
        try
        {
            ps=con.prepareStatement(query_1);
            ps.setString(1,donation_id);
            ps.setDate(2,fromDate);
            ps.setDate(3,toDate);
            rs=ps.executeQuery();
            while(rs.next())
                donationAmount =Double.parseDouble(rs.getString(1));
            rs.close();ps.close();
        }
        catch(Exception r)
        {
            Debug.print(r.getMessage());
            r.printStackTrace();
            releaseConnection(con);
        }
        releaseConnection(con);
        return donationAmount;
   }   

    //============================================= Database Connectivity=========================================
    private Connection makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            Debug.print("AccountsDAO makeConnection() ....... ");
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database in ValidateDAO. " + ex.getMessage());
        }


        return con;
    }
    //=============================================Connection Release=========================================
    private void releaseConnection(Connection con) {
        Debug.print("AccountsDAO releasing Connection");
        try {
            if (!con.isClosed()) {
                Debug.print("AccountsDAO released Connection() .......");
            }
            con.close();
        } catch (Exception e) {
        }
    }
}
