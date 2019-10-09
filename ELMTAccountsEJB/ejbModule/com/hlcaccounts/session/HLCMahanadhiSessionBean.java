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
/*  Program Name    : MahanadhiSessionBean.java
 *  Created         : May 13, 2007 12:36:35 PM
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


package com.hlcaccounts.session;

import com.hlcaccounts.DAO.HLCAccountsDAO;
import com.hlcaccounts.util.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.ejb.*;
import java.util.*;

/**
 * This is the bean class for the MahanadhiSessionBean enterprise bean.
 * Created May 13, 2007 12:36:35 PM
 * @author karthikeyan
 */
public class HLCMahanadhiSessionBean implements SessionBean, HLCMahanadhiSessionRemoteBusiness {
    private SessionContext context;
    
    
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
    HLCAccountsDAO accDAO = new HLCAccountsDAO();
    
    /**
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
        
        Debug.print("ejbCreate called in MahanadhiSessionBean...");
        
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "EJB Methods > Add Business Method" or "Web Service > Add Operation")
    
    //=============================================list Acc Transaction details  =========================================
    public ArrayList listAccTxnDetails(java.util.Date transaction_date, boolean sync_status){
        
        Debug.print("General Exception  in MahanadhiSessionBean.listAccTxnDetails() :");
        
        ArrayList txnDetails = new ArrayList();
        
        try{
            
            txnDetails = accDAO.listAccTxnDetails(transaction_date,sync_status);
            
        }catch(Exception e){
            
            Debug.print("General Exception  in MahanadhiSessionBean.listAccTxnDetails() :" + e.getMessage());
        }
        return txnDetails;
    }
    
    /**
     * Name         :insertAccountTxnDetails
     * Description  :This method will insert record into the tblAccTxnDetails table
     * @ param      :AccTransactionVO
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean insertAccountTxnDetails(HLCAccTransactionVO accTxnVO){
        Debug.print("inside MahanadhiSessionBean.insertAccountTxnDetails :");
        
        boolean result = false;
        
        try {
            result = accDAO.insertAccountTxnDetails(accTxnVO);
            Debug.print("accDAO.insertAccountTxnDetails(accTxnVO) in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while inserting insertAccountTxnDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :selectClassMaster
     * Description  :This method will select records from tblAccClassMaster table
     * @ param      :nil
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    public ArrayList selectClassMaster() throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.selectClassMaster :");
        
        ArrayList classDetails = new ArrayList();
        
        try {
            classDetails = accDAO.selectClassMaster();
            Debug.print("classDetails.size() in session bean :"+classDetails.size());
        } catch(Exception e){
            
            Debug.print("Error while inserting selectClassMaster in MahanadhiSessionBean : "+e.getMessage());
        }
        return classDetails;
    }
    
    /**
     * Name         :selectAccountMaster
     * Description  :This method will select records from tblAccAccountsMaster table
     * @ param      :nil
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    public ArrayList selectAccountMaster() throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.selectAccountMaster :");
        
        ArrayList accDetails = new ArrayList();
        
        try {
            accDetails = accDAO.selectAccountMaster();
            Debug.print("accDetails.size() in session bean :"+accDetails.size());
        } catch(Exception e){
            
            Debug.print("Error while inserting selectAccountMaster in MahanadhiSessionBean : "+e.getMessage());
        }
        return accDetails;
    }
    
    /**
     * Name         :insertAccTxnTypeDetails
     * Description  :This method will insert record into the tblAccTransactionTypeDetails table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean insertAccTxnTypeDetails(HLCAccTxnTypeDetailVO accTxnTypVO) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.insertAccTxnTypeDetails :");
        
        boolean result = false;
        
        try {
            result = accDAO.insertAccTxnTypeDetails(accTxnTypVO);
            Debug.print("accDAO.insertAccTxnTypeDetails(accTxnVO) in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while inserting insertAccTxnTypeDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :updatePaymentStatusAccTxnDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updatePaymentStatusAccTxnDetails(String paymentId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.updatePaymentStatusAccTxnDetails :");
        
        boolean result = false;
        
        try {
            result = accDAO.updatePaymentStatusAccTxnDetails(paymentId);
            Debug.print("updatePaymentStatusAccTxnDetails(accTxnVO) in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while updatePaymentStatusAccTxnDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :getMemberAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :membershipTypeId
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
    public HLCAccTxnTypeDetailVO getMemberAccTxnTypeDetails(String membershipTypeId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getMemberAccTxnTypeDetails :");
        
        HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
        
        try {
            accTxnDet = accDAO.getMemberAccTxnTypeDetails(membershipTypeId);
        } catch(Exception e){
            
            Debug.print("Error while getMemberAccTxnTypeDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
    
    /**
     * Name         :getMemberAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :membershipTypeId
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
    
    public HLCAccTransactionVO getAccTransctionDetails(String transactionId) throws RemoteException {
        Debug.print("inside MahanadhiSessionBean.getMemberAccTxnTypeDetails :");
        HLCAccTransactionVO accTxnDet = null;
        try {
            if(transactionId!=null && transactionId.trim().length()!=0){
                accTxnDet = accDAO.selectAccTransactionDetails(transactionId);
            }
        } catch(Exception e){
            Debug.print("Error while getMemberAccTxnTypeDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
    
    /**
     * Name         :getDonationAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :donationTypeId
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
    public HLCAccTxnTypeDetailVO getDonationAccTxnTypeDetails(String donationTypeId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getDonationAccTxnTypeDetails :");
        
        HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
        
        try {
            accTxnDet = accDAO.getDonationAccTxnTypeDetails(donationTypeId);
        } catch(Exception e){
            
            Debug.print("Error while getDonationAccTxnTypeDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
    
    /**
     * Name         :getArmBandAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :Nil
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
    public HLCAccTxnTypeDetailVO getArmBandAccTxnTypeDetails() throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getArmBandAccTxnTypeDetails :");
        
        HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
        
        try {
            accTxnDet = accDAO.getArmBandAccTxnTypeDetails();
        } catch(Exception e){
            
            Debug.print("Error while getArmBandAccTxnTypeDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
    
    /**
     * Name         :getPhoneServChgAccTxnTypeDetails
     * Description  :This method will select records from AccTxnTypeDetails table
     * @ param      :servTypId
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
    public HLCAccTxnTypeDetailVO getPhoneServChgAccTxnTypeDetails(String servTypId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getPhoneServChgAccTxnTypeDetails :");
        
        HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
        
        try {
            accTxnDet = accDAO.getPhoneServChgAccTxnTypeDetails(servTypId);
        } catch(Exception e){
            
            Debug.print("Error while getPhoneServChgAccTxnTypeDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
    
    public ArrayList getMembershipTranctionDetails(Date transDate, String paymentMode, String userId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getMembershipTranctionDetails :");
        ArrayList result = null;
        try {
            if(transDate!=null){
                result = accDAO.selectMemberShipTransction(transDate, paymentMode,userId);
            }
            Debug.print("accDAO.getMembershipTranctionDetails in session bean :"+result.size());
        } catch(Exception e){
            
            Debug.print("Error while inserting insertAccTxnTypeDetails in getMembershipTranctionDetails : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :selectSyncronizedQBTransctionDetails
     * Author       :Karthikeyan.M
     * Description  :This method will select already Syncronized QB Transction Details from tblAccTransactionDetails table
     * @ param      :Date transDate, String paymentMode
     * @return      :ArrayList
     * @throws      :nil
     */
        
    public ArrayList selectSyncronizedQBTransctionDetails(Date transDate, String paymentMode) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.selectSyncronizedQBTransctionDetails :");
        ArrayList result = null;
        try {
            if(transDate!=null){
                result = accDAO.selectSyncronizedQBTransctionDetails(transDate, paymentMode);
            }
            Debug.print("accDAO.selectSyncronizedQBTransctionDetails in session bean :"+result.size());
        } catch(Exception e){
            
            Debug.print("Error while selectSyncronizedQBTransctionDetails : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :getAccTxnDetailsOnPaymentId
     * Description  :This method will get record from the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    
    public ArrayList getAccTxnDetailsOnPaymentId(String paymentId) throws RemoteException{
        
        Debug.print("paymentId in session bean getAccTxnDetailsOnPaymentId() :"+paymentId);
        ArrayList txnDetails = null;
        
        try {
            txnDetails = accDAO.getAccTxnDetailsOnPaymentId(paymentId);
            
        } catch(Exception e){
            
            Debug.print("Error while getAccTxnDetailsOnPaymentId in mahanadi session bean : "+e.getMessage());
        }
        
        return txnDetails;
    }
    
    /**
     * Name         :getAccTxnDetailsOnPaymentId
     * Description  :This method will get record from the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    
    public ArrayList getParentTranactionIds(String paymentId) throws RemoteException {
        Debug.print("paymentId in session bean getParentTranactionIds() :"+paymentId);
        ArrayList txnDetails =null;
        try {
            if(paymentId!=null && paymentId.trim().length()!=0) {
                txnDetails = accDAO.selectParentTranactionIds(paymentId);
            }
        } catch(Exception e){
            Debug.print("Error while getParentTranactionIds in mahanadi session bean : "+e.getMessage());
        }
        return txnDetails;
    }
    
    
    /**
     * Name         :getReconcileAmount
     * Description  :This method will get record from the tblAccTxnDetails table
     * @ param      :paymentId
     * @return      :double
     * @throws      :RemoteException
     */
    
    public double getReconcileAmount(String paymentId) throws RemoteException {
        Debug.print("paymentId in session bean getReconcileAmount() :"+paymentId);
        double amount = 0;
        try {
            if(paymentId!=null && paymentId.trim().length()!=0) {
                amount = accDAO.selectReconcileAmount(paymentId);
            }
        } catch(Exception e){
            Debug.print("Error while getReconcileAmount in mahanadi session bean : "+e.getMessage());
        }
        return amount;
    }
    
    /**
     * Name         :updateRecouncilAccTxnDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :AccTransactionVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    
    public boolean updateRecouncilAccTxnDetails(HLCAccTransactionVO accTxnVO) throws RemoteException{
        
        boolean status = false;
        
        try {
            status = accDAO.updateRecouncilAccTxnDetails(accTxnVO);
            
        } catch(Exception e){
            
            Debug.print("Error while updateRecouncilAccTxnDetails in mahanadi session bean : "+e.getMessage());
        }
        
        return status;
    }
    
    /**
     * Name         :updateRecouncilActiveStatusAccTxnDetails
     * Description  :This method will update record into the tblAccTxnDetails table
     * @ param      :AccTransactionVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    
    public boolean updateRecouncilActiveStatusAccTxnDetails(String paymentId) throws RemoteException{
        
        boolean status = false;
        
        try {
            status = accDAO.updateRecouncilActiveStatusAccTxnDetails(paymentId);
            
        } catch(Exception e){
            
            Debug.print("Error while updateRecouncilActiveStatusAccTxnDetails in mahanadi session bean : "+e.getMessage());
        }
        
        return status;
    }
    
    /**
     * Name         :updateQBStatus
     * Description  :This method will update qb_status record into the tblAccTransactionDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    public boolean updateQBStatus(Date transDate,  String paymentMode, boolean qbStatus, boolean contQBStatus,
            String userId, String ipAddress) throws RemoteException {
        Debug.print("inside MahanadhiSessionBean.updateQBStatus :");
        boolean result = false;
        try {
            if(transDate!=null && paymentMode!=null){
                result = accDAO.updateQBStatus(transDate, paymentMode, qbStatus, contQBStatus, userId, ipAddress);
            }
            Debug.print("updateQBStatus in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while updateQBStatus in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :updateQBStatus
     * Description  :This method will update qb_status record into the tblAccTransactionDetails table
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateQBSynStatus(Date transDate,  String paymentMode,
            String userId, String ipAddress) throws RemoteException {
        Debug.print("inside MahanadhiSessionBean.updateQBSynStatus :");
        boolean result = false;
        try {
            if(transDate!=null && paymentMode!=null){
                result = accDAO.updateQBSynStatus(transDate, paymentMode, userId, ipAddress);
            }
            Debug.print("updateQBSynStatus in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while updateQBSynStatus in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :getAccTxnDetailsOnPaymentId
     * Description  :This method will get record from the tblAccServiceTypeMaster table
     * @ param      :NIL
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    
    public ArrayList getServiceTypeMaster() throws RemoteException {
        Debug.print("MahanadiSessionBean.getServiceTypeMaster() Calling.");
        ArrayList serviceTypeList =null;
        try {
            serviceTypeList = accDAO.selectServiceTypeMaster();
        } catch(Exception e){
            Debug.print("Error while getServiceTypeMaster in MahanadiSessionBean : "+e.getMessage());
        }
        return serviceTypeList;
    }
    
    /**
     * Name         :getItemMaster
     * Description  :This method will get record from the tblAccItemMaster table
     * @ param      :NIL
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    
    public ArrayList getItemMaster() throws RemoteException {
        Debug.print("MahanadiSessionBean.getItemMaster() Calling.");
        ArrayList itemList =null;
        try {
            itemList = accDAO.selectItemMaster();
        } catch(Exception e){
            Debug.print("Error while getItemMaster in MahanadiSessionBean : "+e.getMessage());
        }
        return itemList;
    }
    
    
     /**
     * Name         :getSubItemName
     * Description  :This method will get record based on serviceTypeName from the tblAccItemMaster table
     * @ param      :serviceTypeName
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    
    public ArrayList getSubItemName(String serviceTypeName) throws RemoteException {
        Debug.print("MahanadiSessionBean.getSubItemName() Calling.");
        ArrayList itemSubList =null;
        try {
            if(serviceTypeName!=null && serviceTypeName.trim().length()!=0){
                itemSubList = accDAO.selectSubItemList(serviceTypeName);
            }
        } catch(Exception e){
            Debug.print("Error while getSubItemName in MahanadiSessionBean : "+e.getMessage());
        }
        return itemSubList;
    }
    
     /**
     * Name         :getSubItemName
     * Description  :This method will get record based on serviceTypeName from the tblAccItemMaster table
     * @ param      :serviceTypeName
     * @return      :ArrayList
     * @throws      :RemoteException
     */
    
    public ArrayList getSubAccountList(String accountType) throws RemoteException {
        Debug.print("MahanadiSessionBean.getSubAccountList() Calling.");
        ArrayList accountSubList =null;
        try {
            if(accountType!=null && accountType.trim().length()!=0){
                accountSubList = accDAO.selectSubAccountList(accountType);
            }
        } catch(Exception e){
            Debug.print("Error while getSubAccountList in MahanadiSessionBean : "+e.getMessage());
        }
        return accountSubList;
    }
    
    /**
     * Name         :createAccItemDetails
     * Description  :This method will insert record into the tblAccItemMaster table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    public  boolean createAccItemDetails(HLCItemMaster itemVO) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.createAccItemDetails :");
        boolean result = false;
        try {
            if(itemVO!=null && itemVO.getItemNo()!=null && itemVO.getItemNo().trim().length()!=0){
                boolean chkResult = accDAO.isItemNoExist(itemVO.getItemNo().trim());
                Debug.print("createAccItemDetails.chkResult() Value:" + chkResult);
                if(chkResult==false){
                    result = accDAO.insertAccItemMaster(itemVO);
                }
            }
            Debug.print("accDAO.createAccItemDetails(itemVO) in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while inserting createAccItemDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :updateAccItemDetails
     * Description  :This method will insert record into the tblAccItemMaster table
     * @ param      :String itemId, String serviceTypeName, String itemNo,
     *                  String parentItemNo, String itemDesc, float rate, String accountNo
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updateAccItemDetails(String itemId, String serviceTypeName, String itemNo,
            String parentItemNo, String itemDesc, float rate, String accountNo) throws RemoteException {
        Debug.print("inside MahanadhiSessionBean.updateAccItemDetails :");
        boolean result = false;
        try {
            if(itemId!=null && itemNo!=null && itemId.trim().length()!=0 && itemNo.trim().length()!=0){
                boolean chkResult = accDAO.isItemNoEditExist(itemId, itemNo);
                Debug.print("createAccItemMaster.isItemNoEditExist.chkResult() Value:" + chkResult);
                if(chkResult==false){
                    result = accDAO.updateAccItemDetails(itemId, serviceTypeName, itemNo,
                            parentItemNo, itemDesc, rate, accountNo);
                }
            }
            Debug.print("accDAO.updateAccItemDetails() in session bean :"+result);
        } catch(Exception e){
            Debug.print("Error while updating updateAccItemDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    /**
     * Name         :getItemDetails
     * Description  :This method will get record based on itemId from the tblAccItemMaster table
     * @ param      :itemId
     * @return      :ItemMaster
     * @throws      :RemoteException
     */
    
    public HLCItemMaster getItemDetails(String itemId) throws RemoteException {
        Debug.print("MahanadiSessionBean.getItemDetails() Calling.");
        HLCItemMaster itemVO = new HLCItemMaster();
        try {
            if(itemId!=null && itemId.trim().length()!=0){
                itemVO = accDAO.selectItemDetails(itemId);
            }
        } catch(Exception e){
            Debug.print("Error while getItemDetails in MahanadiSessionBean : "+e.getMessage());
        }
        return itemVO;
    }
    
    /**
     * Name         :createAccItemMaster
     * Description  :This method will insert record into the tblAccItemMaster table
     * @ param      :AccTxnTypeDetailVO
     * @return      :String
     * @throws      :RemoteException
     */
    
    public String createAccountDetails(HLCAccountsMasterVO accMasterVO) throws RemoteException {
        Debug.print("inside MahanadhiSessionBean.createAccountDetails :");
        String accStatus = null;
        try {
            if(accMasterVO!=null && accMasterVO.getAccount_no()!=null && accMasterVO.getAccount_no().trim().length()!=0
                    && accMasterVO.getAccount_name()!=null && accMasterVO.getAccount_name().trim().length()!=0){
                
                boolean chkAccNOResult = accDAO.isAccountNoExist(accMasterVO.getAccount_no().trim());
                Debug.print("createAccountDetails.chkAccNOResult() Value:" + chkAccNOResult);
                if(chkAccNOResult==true){
                    accStatus =  "This account number is already in use. Please enter another number.";
                } else{
                    boolean chkAccNameResult = accDAO.isAccountNameExist(accMasterVO.getAccount_name().trim());
                    Debug.print("createAccountDetails.chkAccNameResult() Value:" + chkAccNameResult);
                    if(chkAccNameResult==true){
                        accStatus = "This account name is already in use. Please enter another name.";
                    } else{
                        boolean result = accDAO.insertAccountsMasterDetails(accMasterVO);
                        Debug.print("Successfully called insertAccountsMasterDetails. result:" + result);
                        if(result==true){
                            accStatus = "ok";
                        }
                    }
                }
            }
            Debug.print("accDAO.createAccountDetails() in session bean accStatus:"+ accStatus);
        } catch(Exception e){
            Debug.print("Error while inserting createAccountDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accStatus;
    }
    
    /**
     * Name         :updateAccountsDetails
     * Description  :This method will insert record into the tblAccItemMaster table
     * @ param      :AccountsMasterVO
     * @return      :String
     * @throws      :RemoteException
     */
    
    public String updateAccountsDetails(HLCAccountsMasterVO accMasterVO) throws RemoteException {
        Debug.print("inside MahanadhiSessionBean.updateAccountsDetails :");
        String accountStatus = null;
        try {
            if(accMasterVO!=null && accMasterVO.getAccount_id()!=null &&
                    accMasterVO.getAccount_id().trim().length()!=0 && accMasterVO.getAccount_no()!=null &&
                    accMasterVO.getAccount_no().trim().length()!=0 && accMasterVO.getAccount_name()!=null &&
                    accMasterVO.getAccount_name().trim().length()!=0){
                
                boolean chkAccNOResult = accDAO.isAccountNoEditExist(accMasterVO.getAccount_id().trim(),
                        accMasterVO.getAccount_no().trim());
                Debug.print("updateAccountsDetails.chkAccNOResult() Value:" + chkAccNOResult);
                if(chkAccNOResult==true){
                    accountStatus =  "This account number is already in use. Please enter another number.";
                } else{
                    boolean chkAccNameResult = accDAO.isAccountNameEditExist(accMasterVO.getAccount_id().trim(),
                            accMasterVO.getAccount_name().trim());
                    Debug.print("createAccountDetails.chkAccNameResult() Value:" + chkAccNameResult);
                    if(chkAccNameResult==true){
                        accountStatus = "This account name is already in use. Please enter another name.";
                    } else{
                        boolean result = accDAO.updateAccountsDetails(accMasterVO);
                        Debug.print("Successfully called updateAccountsDetails. result:" + result);
                        if(result==true){
                            accountStatus = "ok";
                        }
                    }
                }
            }
            Debug.print("accDAO.updateAccountsDetails() in session bean accountStatus:"+ accountStatus);
        } catch(Exception e){
            Debug.print("Error while updating updateAccountsDetails in MahanadhiSessionBean : "+e.getMessage());
        }
        return accountStatus;
    }
    
    /**
     * Name         :getAccountsDetails
     * Description  :This method will get record based on itemId from the tblAccItemMaster table
     * @ param      :itemId
     * @return      :ItemMaster
     * @throws      :RemoteException
     */
    
    public HLCAccountsMasterVO getAccountsDetails(String accountId) throws RemoteException {
        Debug.print("MahanadiSessionBean.getAccountsDetails() Calling.");
        HLCAccountsMasterVO accountVO = new HLCAccountsMasterVO();
        try {
            if(accountId!=null && accountId.trim().length()!=0){
                accountVO = accDAO.selectAccountDetails(accountId);
            }
        } catch(Exception e){
            Debug.print("Error while getAccountsDetails in MahanadiSessionBean : "+e.getMessage());
        }
        return accountVO;
    }
    
    public ArrayList getAllAccountTypeMaster() throws RemoteException{
        Debug.print("MahanadiSessionBean.getAllAccountTypeMaster() Calling.");
        ArrayList accTypeList= new ArrayList();
        try {
                accTypeList = accDAO.selectAccountTypeMaster();
        } catch(Exception e){
            Debug.print("Error while getAllAccountTypeMaster in MahanadiSessionBean : "+e.getMessage());
        }
        return accTypeList;
    }
    
    /**
     * Name         :createItemNoFromAccMaster
     * Description  :This method will get hierarchy of parent item no's details records concatinated as final item no's. 
     * @ param      :itemNo
     * @return      :String
     * @throws      :RemoteException
     */
    
    public String createItemNoFromAccMaster(String itemNo) throws RemoteException {
        Debug.print("MahanadiSessionBean.createItemNoFromAccMaster() Called .");
               
        String finalItemNo = "";
        
        try {
            
                finalItemNo = accDAO.createItemNoFromAccMaster(itemNo);
            
        } catch(Exception e){
            Debug.print("Error while createItemNoFromAccMaster in MahanadiSessionBean : "+e.getMessage());
        }
        return finalItemNo;
    }
    
    /**
     * Name         :isTransactionExists
     * Description  :This method will check weather any one of the passed parameters exists in tblAccTransactionTypeDetails table
     * @ param      :item_no, transaction_name
     * @return      :boolean
     * @throws      :SQLException
     */
    
     public boolean isTransactionExists(String transaction_name,String item_no,String class_name) throws RemoteException {
        Debug.print("MahanadiSessionBean.isTransactionExists() Called .");
               
        boolean status = false;
        
        try {
            
                status = accDAO.isTransactionExists(transaction_name,item_no,class_name);
            
        } catch(Exception e){
            Debug.print("Error while isTransactionExists in MahanadiSessionBean : "+e.getMessage());
        }
        return status;
    }
     public boolean createClass(String classname) throws RemoteException
     {
         boolean result = false;
        if(classname!=null && classname.trim().length()!=0 ){
            if(accDAO.isClassExist(classname)){
                result = accDAO.insertClass(classname);
               
            }
        }
        return result;
     }
     public ArrayList getClassDetails() throws RemoteException
     {
         ArrayList results=accDAO.getAllClassDetails();
         return results;
     }
     
      public ArrayList getClassNameDetailsById(String classId) throws RemoteException{
        Debug.print("inside getClassNameDetailsById");
        ArrayList results = null;
        if(classId!=null && classId.trim().length()!=0){
            results = accDAO.selectClassNameById(classId);
            Debug.print("got class id and name");
        }
        return results; 
    }
     
       public boolean editClassName(String classId,String className) throws RemoteException
       {
           boolean result = false;
        if(classId!=null && classId.trim().length()!=0 && className!=null && className.trim().length()!=0 ){
            if(accDAO.isClassNameEditExist(classId, className)){
                result = accDAO.updateClassName(classId,className);
                Debug.print("MahanadhiSessionBean editClassName Result:" +  result);
            }
        }
        return result;
       }
     /**
     * Name         :listAccTxnTypeDetailsOnClass
     * Description  :This method will get the records from tblAccTransactionTypeDetails table based on class_name. 
     * @ param      :class_name
     * @return      :ArrayList
     * @throws      :SQLException
     */
     
      public ArrayList listAccTxnTypeDetailsOnClass(String class_name) throws RemoteException {
        Debug.print("MahanadiSessionBean.listAllAccTxnTypeDetails() Called.");
        ArrayList txnDetail = new ArrayList();
        
        try {
            
                txnDetail = accDAO.listAccTxnTypeDetailsOnClass(class_name);
            
        } catch(Exception e){
            Debug.print("Error while listAccTxnTypeDetailsOnClass in MahanadiSessionBean : "+e.getMessage());
        }
        return txnDetail;
    }
      
       /**
     * Name         :updateAccTxnTypeDetails
     * Description  :This method will update record into the tblAccTxnTypeDetails table
     * @ param      :AccTxnTypeDetailVO
     * @return      :boolean
     * @throws      :SQLException
     */
      
      public boolean updateAccTxnTypeDetails(HLCAccTxnTypeDetailVO accTxnTypVO) throws RemoteException {
        Debug.print("MahanadiSessionBean.updateAccTxnTypeDetails() Called .");
               
        boolean status = false;
        
        try {
            
                status = accDAO.updateAccTxnTypeDetails(accTxnTypVO);
            
        } catch(Exception e){
            Debug.print("Error while updateAccTxnTypeDetails in MahanadiSessionBean : "+e.getMessage());
        }
        return status;
    }
      
      /**
     * Name         :selectAccTransactionTypeDetail
     * Description  :This method will get record based on transaction_type_id from the tblAccTransactionTypeDetails table
     * @ param      :transaction_type_id
     * @return      :AccTxnTypeDetailVO
     * @throws      :SQLException
     */
      
      public HLCAccTxnTypeDetailVO selectAccTransactionTypeDetail(String transaction_type_id) throws RemoteException{
          
          Debug.print("selectAccTransactionTypeDetail in MahanadiSessionBean called ");
          HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
          
          try {
            
                accTxnDet = accDAO.selectAccTransactionTypeDetail(transaction_type_id);
            
        } catch(Exception e){
            Debug.print("Error while selectAccTransactionTypeDetail in MahanadiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
        
      }
      
       /**
     * Name         :getOverPayAccTransactionTypeDetail
     * Description  :This method will get record based on transaction_name from the tblAccTransactionTypeDetails table
     * @ param      :nil
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
      
      public HLCAccTxnTypeDetailVO getOverPayAccTransactionTypeDetail() throws RemoteException{
          
          Debug.print("getOverPayAccTransactionTypeDetail in MahanadiSessionBean called ");
          HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
          
          try {
            
                accTxnDet = accDAO.getOverPayAccTransactionTypeDetail();
            
        } catch(Exception e){
            Debug.print("Error while getOverPayAccTransactionTypeDetail in MahanadiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
        
      }
      
      /**
     * Name         :getAccTransactionTypeDetailBasedOnTran_Name
     * Description  :This method will get record based on transaction_name from the tblAccTransactionTypeDetails table
     * @ param      :transaction_name
     * @return      :AccTxnTypeDetailVO
     * @throws      :RemoteException
     */
      
      public HLCAccTxnTypeDetailVO getAccTransactionTypeDetailBasedOnTran_Name(String transaction_name) throws RemoteException{
          
          Debug.print("getAccTransactionTypeDetailBasedOnTran_Name in MahanadiSessionBean called ");
          HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
          
          try {
            
                accTxnDet = accDAO.getAccTransactionTypeDetailBasedOnTran_Name(transaction_name);
            
        } catch(Exception e){
            Debug.print("Error while getAccTransactionTypeDetailBasedOnTran_Name in MahanadiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
        
      }
      
      /**
     * Name         :searchQBTransctionDetails
     * Author       :Karthikeyan.M
     * Description  :This method will select already Syncronized QB Transction Details from tblAccTransactionDetails table
     * @ param      :Date transDate, String checkNo
     * @return      :ArrayList
     * @throws      :RemoteException
     */
        
    public ArrayList searchQBTransctionDetails(Date transDate, String checkNo) throws RemoteException{
        Debug.print("MahanadiSessionBean.searchQBTransctionDetails() : ");
        ArrayList transactionList = new ArrayList();
        
        try {
            
                transactionList = accDAO.searchQBTransctionDetails(transDate,checkNo);
            
        } catch(Exception e){
            Debug.print("Error while searchQBTransctionDetails in MahanadiSessionBean : "+e.getMessage());
        }
        return transactionList;
        
    }
    
    /**
     * Name         :updateAccTxnDetailsMissingCheck
     * Description  :This method will update active_status record into the tblAccTxnDetails table
     * @ param      :paymentId, active_status
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updateAccTxnDetailsMissingCheck(String paymentId,boolean active_status) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.updateAccTxnDetailsMissingCheck :");
        
        boolean result = false;
        
        try {
            result = accDAO.updateAccTxnDetailsMissingCheck(paymentId,active_status);
            Debug.print("updateAccTxnDetailsMissingCheck() in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while updateAccTxnDetailsMissingCheck in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
      
    /**
     * Name         :updateRecouncilTxnDateOnTxnId
     * Description  :This method will update transaction_date record into the tblAccTxnDetails table
     * @ param      :parentTxnId
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updateRecouncilTxnDateOnTxnId(String txnId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.updateRecouncilTxnDateOnTxnId :");
        
        boolean result = false;
        
        try {
            result = accDAO.updateRecouncilTxnDateOnTxnId(txnId);
            Debug.print("updateRecouncilTxnDateOnTxnId() in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while updateRecouncilTxnDateOnTxnId in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
    public boolean insertMembershipRefundDetails(String userId, String comments, float balanceAmount) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.insertMembershipRefundDetails :");
        
        boolean result = false;
        
        try {
            result = accDAO.insertMembershipRefundDetails(userId, comments, balanceAmount);
            Debug.print("updateRecouncilTxnDateOnTxnId() in session bean :"+result);
        } catch(Exception e){
            
            Debug.print("Error while updateRecouncilTxnDateOnTxnId in MahanadhiSessionBean : "+e.getMessage());
        }
        return result;
    }
    
   public HLCAccTxnTypeDetailVO getAccTransacTypDetsForAreaRiderProg(String transacTypId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getAccTransacTypDetsForAreaRiderProg :");
        
        HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
        
        try {
            accTxnDet = accDAO.getAccTransacTypDetsForAreaRiderProg(transacTypId);
        } catch(Exception e){
            
            Debug.print("Error while getAccTransacTypDetsForAreaRiderProg in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
      
    public HLCAccTxnTypeDetailVO getAccTransacTypDetsForEveOrgEndorsed(String transacTypId) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getAccTransacTypDetsForEveOrgEndorsed :");
        
        HLCAccTxnTypeDetailVO accTxnDet = new HLCAccTxnTypeDetailVO();
        
        try {
            accTxnDet = accDAO.getAccTransacTypDetsForEveOrgEndorsed(transacTypId);
        } catch(Exception e){
            
            Debug.print("Error while getAccTransacTypDetsForEveOrgEndorsed in MahanadhiSessionBean : "+e.getMessage());
        }
        return accTxnDet;
    }
    
    public ArrayList getDailyPaymentDetails(int year, int month, String paymentMode) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getPaymentDetails :");
        ArrayList result = null;
        try {
            if(paymentMode!=null){
                result = accDAO.getDailyPaymentDetails(year, month, paymentMode);
            }
            Debug.print("accDAO.getPaymentDetails in session bean :"+result.size());
        } catch(Exception e){
            
            Debug.print("Error while getting paymentdetails in getPaymentDetails : "+e.getMessage());
        }
        return result;
    }
    
     public ArrayList getMonthlyPaymentDetails(int year, String paymentMode) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getMonthlyPaymentDetails :");
        ArrayList result = null;
        try {
            if(paymentMode!=null){
                result = accDAO.getMonthlyPaymentDetails(year, paymentMode);
            }
            Debug.print("accDAO.getMonthlyPaymentDetails in session bean :"+result.size());
        } catch(Exception e){
            
            Debug.print("Error while getting getMonthlyPaymentDetails in getPaymentDetails : "+e.getMessage());
        }
        return result;
    }
     
       public ArrayList getAreaPaymentDetails(int year) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getAreaPaymentDetails :");
        ArrayList result = null;
        try {
            
                result = accDAO.getAreaPaymentDetails(year);
           
            Debug.print("accDAO.getAreaPaymentDetails in session bean :"+result.size());
        } catch(Exception e){
            
            Debug.print("Error while getting getAreaPaymentDetails: "+e.getMessage());
        }
        return result;
    }
       
        public ArrayList getMemberRegistrationDetails(String year) throws RemoteException{
        Debug.print("inside MahanadhiSessionBean.getMemberRegistrationDetails :");
        ArrayList result = null;
        try {
            
                result = accDAO.getMemberRegistrationDetails(year);
           
            Debug.print("accDAO.getMemberRegistrationDetails in session bean :"+result.size());
        } catch(Exception e){
            
            Debug.print("Error while getting getMemberRegistrationDetails: "+e.getMessage());
        }
        return result;
    }
        
        public HashMap getYearBasedSalesAmount() throws RemoteException
        {
             Debug.print("inside MahanadhiSessionBean.getYearBasedSalesAmount :");
             HashMap yearAmount=null;
             try
             {
              yearAmount=accDAO.getYearBasedSalesAmount();
               Debug.print("accDAO.getYearBasedSalesAmount in session bean :"+yearAmount.size());
             }
             catch(Exception e)
             {
                  Debug.print("Error while getting getYearBasedSalesAmount: "+e.getMessage());
             }
             return yearAmount;
        }
        public double getTotalDonationAmount(Date startDate,Date endDate) throws RemoteException
        {
            Debug.print("Inside HLCMahandhiSession Bean ");
            double totalDonationAmount=0;
            try {
            totalDonationAmount=accDAO.getTotalDonationAmount(startDate,endDate);
            Debug.print("accDAO.getTotalDonationAmount in session bean :"+totalDonationAmount);
            
            }
            catch(Exception e)
            {
            Debug.print(e.getMessage());
            }    
            return totalDonationAmount;
        }
        public ArrayList getTotalDonationAmountForAllDonationItems(Date startDate,Date endDate) throws RemoteException
        {
           Debug.print("Inside HLCMahandhiSession Bean ");  
           ArrayList donationAmountForAllDonationItems=null;
           try
           {
               donationAmountForAllDonationItems=accDAO.getTotalDonationAmountForAllDonationItems(startDate, endDate);
               Debug.print("Inside HLCMahandhiSession Bean Size:"+donationAmountForAllDonationItems.size()); 
           }
           catch(Exception r)
           {
              Debug.print(r.getMessage());  
           }
           return donationAmountForAllDonationItems;
        }
        public double getDonationAmountSpecificDonationItem(Date startDate,Date endDate,String donation_id) throws RemoteException
        {
          Debug.print("Inside HLCMahandhiSession Bean ");  
          double totalDonationAmount=0;
            try {
            totalDonationAmount=accDAO.getDonationAmountSpecificDonationItem(startDate, endDate, donation_id); 
            Debug.print("accDAO.getTotalDonationAmount in session bean :"+totalDonationAmount);
            }
            catch(Exception e)
            {
            Debug.print(e.getMessage());
            }    
            return totalDonationAmount;
        }
                
  
}
