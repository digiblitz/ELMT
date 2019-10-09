/*
 * MembershipRefundTypeDetails.java
 *
 * Created on August 26, 2006, 12:55 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author harmohan
 */
public class HLCMembershipRefundTypeDetails implements Serializable {
    private String refundId;
    private String refundTypeId;
    private double refundAmount;
    private String mailId;
    
    /** Creates a new instance of MembershipRefundTypeDetails */
    public HLCMembershipRefundTypeDetails() {
    }
    
    public HLCMembershipRefundTypeDetails(String refundId, String refundTypeId, double refundAmount, String loginName) {
        this.refundId = refundId;
        this.refundTypeId = refundTypeId;
        this.refundAmount = refundAmount;
        
    }
    
    
    //Getter Methods
    public String getRefundId() {
        return refundId;
    }
    public String getRefundTypeId() {
        return refundTypeId;
    }
    public double getRefundAmount() {
        return refundAmount;
    }
    public String getMailId(){return mailId; }
    
    //Setter Methods
     public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    public void setRefundTypeId(String refundTypeId) {
        this.refundTypeId = refundTypeId;
    }
    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }
    public void setMailId(String mailId){this.mailId = mailId; }
  
    
}
