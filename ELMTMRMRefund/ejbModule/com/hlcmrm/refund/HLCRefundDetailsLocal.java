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
package com.hlcmrm.refund;

import javax.ejb.EJBLocalObject;
import java.util.*;


/**
 * This is the local interface for RefundDetails enterprise bean.
 */
public interface HLCRefundDetailsLocal extends EJBLocalObject, HLCRefundDetailsLocalBusiness {
    
    /**==================Membership Refund Details================================*/
    //refund_id, member_id
   // public void setRefundId(String refundId);
    public void setMemberId(String memberId);
    public void setClaimAmount(double claimAmount);
    public void setClaimDate(Date claimDate);
    public void setRefundAmount(double refundAmount);
    public void setRefundDate(Date refundDate);
    public void setRefundedBy(String refundedBy);
    public void setBankName(String bankName);
    public void setCheckNumber(int checkNumber);
    public void setCheckDate(Date checkDate);
    public void setBalanceAmount(double balanceAmount);
    public void setMailId(String mailId);
    public void setRefundStatus(String refundStatus);
    public void setUserId(String userId);
     
   /* public String getRefundId();
    public String getMemberId();
    public double getClaimAmount();
    public Date getClaimDate();
    public double getRefundAmount();
    public Date getRefundDate();
    public String getRefundedBy();
    public String getBankName();
    public int getCheckNumber();
    public Date getCheckDate();
    public double getBalanceAmount();
    public String getMailId();*/
    
    /*============================Membership type Refund Details====================*/
    
    public void setRefundId(String refundId);
    public void setRefundTypeId(String refundTypeId);
   // public void setRefundAmount(double refundAmount);
}

