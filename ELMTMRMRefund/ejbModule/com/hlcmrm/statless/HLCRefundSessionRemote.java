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
package com.hlcmrm.statless;

import com.hlcmrm.util.HLCMembershipRefundDetails;
import com.hlcmrm.util.HLCMembershipRefundTypeDetails;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import javax.ejb.FinderException;
import java.sql.*;
import javax.sql.*;
import java.util.Vector;
import java.util.*;

/**
 * This is the remote interface for RefundSession enterprise bean.
 */
public interface HLCRefundSessionRemote extends EJBObject, HLCRefundSessionRemoteBusiness {
    
    public void addMembershipRefundDetail(HLCMembershipRefundDetails objRefDetails) throws RemoteException;
    
    public void addMembershipRefundTypeDetail(HLCMembershipRefundTypeDetails objRefTypeDetails) throws RemoteException;
    
    public Vector displayRefundTypes() throws RemoteException, SQLException;
    
    public Vector getRefundDetails(String memberId) throws RemoteException, FinderException;
    
    public Vector getRefundDetailList() throws RemoteException, FinderException;
    
    public HLCMembershipRefundDetails  getRefundDetail(String refId) throws RemoteException, FinderException;
    
    public void editRefundDetails(HLCMembershipRefundDetails objRefDetails) throws RemoteException, FinderException;
    
    public Collection memberDetails(String memberId) throws SQLException, RemoteException;
    
    public Collection memberDetailsOnUserId(String userId) throws SQLException, RemoteException;
    
    public String[] getBalanaceAmount(String emailId) throws RemoteException;
    
    public Vector getRefundDetailByUserId(String userId) throws RemoteException;
    
    public Vector displayRefundTypeDetail(String refundId) throws RemoteException;
    
    public Vector getRefundBasedOnStatus(String statusName) throws RemoteException;
    
    public boolean getApproveBalance(String refundId) throws RemoteException;
    
    public ArrayList listExcessPayment(String status) throws RemoteException,SQLException;
    
    public HLCMembershipRefundDetails  getExcessPaymentDetails(String refId) throws RemoteException, SQLException;
    
    public boolean updateRefundDetails(HLCMembershipRefundDetails memRefundDet) throws RemoteException,SQLException;
    
}
