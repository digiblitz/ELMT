/*
 * MembershipRefundDetails.java
 *
 * Created on August 25, 2006, 11:58 PM
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
public class HLCMembershipRefundDetails implements Serializable{
    private String memberId;
    private double claimAmount;
    private Date claimDate;
    private double refundAmount;
    private Date refundDate;
    private String refundedBy;
    private String bankName;
    private int checkNumber;
    private Date checkDate;
    private double balanceAmount;
    private String mailId;
    private String comments;
    private String refundStatus;
    private String refundId;
    private String loginName;
    private String userId;    
    private String firstName;
    private String lastName;
        
    /** Creates a new instance of MembershipRefundDetails */
    public HLCMembershipRefundDetails() {
    }
    
    public HLCMembershipRefundDetails(String refundId,double claimAmount, Date claimDate, double refundAmount,String refundStatus,
            Date refundDate, String refundedBy, String bankName, int checkNumber, Date checkDate, double balanceAmount, String mailId, String userId){
        //this.membrId = memberId;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.refundAmount = refundAmount;
        this.refundDate = refundDate;
        this.refundedBy = refundedBy;
        this.bankName = bankName;
        this.checkNumber = checkNumber;
        this.checkDate = checkDate;
        this.balanceAmount = balanceAmount;
        this.mailId = mailId;
        this.refundStatus = refundStatus;
        this.refundId = refundId;
        this.loginName =loginName;
        this.userId = userId;
    }
   
        // getters methods
    
        public String getRefundId(){return refundId; }
        public String getMemberId(){return memberId; }
        public double getClaimAmount() { return claimAmount;}
        public Date getClaimDate(){return claimDate;}
         public double getRefundAmount() {return refundAmount;}
        public Date getRefundDate(){return refundDate;}
         public String getRefundedBy() {return refundedBy;}
        public String getBankName(){return bankName;}
         public int getCheckNumber() {return checkNumber;}
        public Date getCheckDate(){return checkDate; }
         public double getBalanceAmount(){return balanceAmount;}
        public String getMailId(){return mailId; }
         public String getRefundStatus(){return refundStatus; }

        //Setters methods
        public void setRefundId(String refundId){this.refundId = refundId; }
        public void setRefundStatus(String refundStatus){this.refundStatus = refundStatus; }
        public void setMemberId(String memberId){
            this.memberId = memberId;
        }
        public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
        }
        public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
        }
        public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
        }
        public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
        }
        public void setRefundedBy(String refundedBy) {
        this.refundedBy = refundedBy;
        }
        public void setBankName(String bankName) {
        this.bankName = bankName;
        }
        public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
        }
        public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
        }
         public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
        }
        public void setMailId(String mailId){this.mailId = mailId; }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }
    
        public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
  
    public String toString()
    {
        StringBuffer sb = new StringBuffer()    
        .append(" memberId :"+ memberId+"\n")
	.append(" claimAmount :"+ claimAmount+"\n")
        .append(" claimDate :"+ claimDate+"\n")
        .append(" refundAmount :"+ refundAmount+"\n")
        .append(" refundDate :"+ refundDate+"\n")
	.append(" refundedBy :"+ refundedBy+"\n")
        .append(" bankName :"+ bankName+"\n")
        .append(" checkNumber :"+ checkNumber+"\n")
        .append(" balanceAmount :"+ balanceAmount+"\n")
        .append(" mailId :"+ mailId+"\n")
	.append(" comments :"+comments+"\n")
        .append(" refundStatus :"+refundStatus+"\n")
	.append(" refundId :"+refundId+"\n")
	.append(" loginName :"+loginName+"\n")
        .append(" userId :"+userId+"\n")
        .append(" firstName :"+firstName+"\n")
	.append(" lastName :"+lastName+"\n");	
        
    return sb.toString();
}
    
}