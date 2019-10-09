/*
 * PaymentDetails.java
 *
 * Created on August 24, 2006, 1:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcform.util;
import java.util.*;
import java.io.*;
/**
 *
 * @author harmohan
 */
public class HLCPaymentDetails implements Serializable{
    private String paymentId;
    private String userId;
    private String userTypeId;
    private String ccName;
    private String ccType;
    private String ccNumber;
    private int ccExpMonth;
    private int ccExpYear;
    private int ccCvvid;
    private String bankName;
    private Date checkDate;
    private String checkNumber;
    private double amount;
    private Date paymentDate;
    private String paymentStatus;
    private String cardNo;
    private String cardCvvNo;
    private String favourOf;
    private String checkNo;
    private String checkName;
    private float checkAmount;
    private String parentPaymentId;
    private String ipAddress;
    private float pendingAmount;
        
    private String sslResult;
    private String sslResultMessage;
    private String sslTxnId;
    private String sslApprovalCode;
    private String sslCvv2Response;
    private String sslAvsResponse;
    private String sslTransactionType;
    private String sslInvoiceNo;
    private String sslEmail;
    private String user_comments;
    private String staff_comments;
    private boolean nfsStat;
    private boolean nsf_charge_status;
    
    private String prefix;
    private String firstName;
    private String lastName;
    private String emailId;
    private String city;
    private String state;
     private boolean activeStatus;
    
    /** Creates a new instance of PaymentDetails */
    public HLCPaymentDetails() {  }
    
    public HLCPaymentDetails(String paymentId, String userId,String userTypeId,String ccName,
             String ccType, String ccNumber, int ccExpMonth,int ccExpYear,
            int ccCvvid, String bankName, Date checkDate, String checkNumber,double amount,float checkAmount,
            Date paymentDate,String paymentStatus,String parentPaymentId,String ipAddress,float pendingAmount,boolean nfsStat,
            String staff_comments, String user_comments, boolean nsf_charge_status, boolean activeStatus){

            this.paymentId = paymentId;
            this.userId = userId;
            this.userTypeId = userTypeId;
            this.ccName = ccName;
            this.ccType = ccType;
            this.ccNumber = ccNumber;
            this.ccExpMonth = ccExpMonth;
            this.ccExpYear = ccExpYear;
            this.ccCvvid = ccCvvid;
            this.bankName = bankName;
            this.checkDate = checkDate;
            this.checkNumber = checkNumber;
            this.amount = amount;
             this.checkAmount = checkAmount;
            this.paymentDate = paymentDate;
            this.paymentStatus = paymentStatus;
            this.parentPaymentId = parentPaymentId;
            this.ipAddress = ipAddress;
            this.pendingAmount = pendingAmount;
            this.nfsStat = nfsStat;
            this.staff_comments = staff_comments;
            this.user_comments = user_comments;
            this.nsf_charge_status = nsf_charge_status;
             this.activeStatus = activeStatus;
        }

        // getters
    
        public String getPaymentId() {
            return paymentId;
        }
        public String getUserId() {
            return userId;
        }
        public String getUserTypeId() {
            return userId;
        }
        public String getCcName() {
            return ccName;
        }
        public String getCcType() {
            return ccType;
        }
        public String getCcNumber() {
            return ccNumber;
        }
        public int getCcExpMonth() {
            return ccExpMonth;
        }
        public int getCcExpYear() {
        return ccExpYear;
        }
        public int getCcCvvid() {
            return ccCvvid;
        }
        public String getBankName() {
            return bankName;
        }
        public Date getCheckDate() {
            return checkDate;
        }
        public String getCheckNumber() {
            return checkNumber;
        }
        public double getAmount() {
            return amount;
        }
        public Date getPaymentDate() {
            return paymentDate;
        }
        public String getPaymentStatus() {
            return paymentStatus;
        }

    //Setters methods
        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public void setUserTypeId(String userTypeId) {
            this.userTypeId = userTypeId;
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
        
   public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" paymentId :"+ paymentId+"\n")
	.append(" userId :"+ userId+"\n")
	.append(" ccName :"+ ccName+"\n")
	.append(" ccType :"+ccType+"\n")
	.append(" ccNumber :"+ccNumber+"\n")
	.append(" ccExpMonth :"+ccExpMonth+"\t\t")
        .append(" ccExpYear :"+ccExpYear+"\n")
	.append(" ccCvvid :"+ccCvvid+"\n")
	.append(" bankName :"+ bankName+"\n")
	.append(" checkDate :"+ checkDate+"\n")
	.append(" checkNumber :"+checkNumber+"\n")
	.append(" amount :"+amount+"\n")
	.append(" paymentDate :"+paymentDate+"\n")
	.append(" paymentStatus :"+ paymentStatus+"\n")
        .append(" ipAddress :"+ ipAddress+"\n")
        .append(" parentPaymentId :"+ parentPaymentId+"\n")
        .append(" parentPaymentId :"+ parentPaymentId+"\n")
        .append("staff_comments :" + staff_comments + "\n")
        .append("user_comments :" + user_comments + "\n")
        .append("nsf_charge_status :" + nsf_charge_status + "\n")
        .append(" nfsStat :"+ nfsStat+"\n");
	
    return buffer.toString();
  }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCardCvvNo() {
        return cardCvvNo;
    }

    public void setCardCvvNo(String cardCvvNo) {
        this.cardCvvNo = cardCvvNo;
    }

    public void setFavourOf(String favourOf) {
        this.favourOf = favourOf;
    }

    public String getFavourOf() {
        return favourOf;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getCheckNo() {
        return checkNo;
    }
    
     

        public String getSslResult() {
            return sslResult;
        }

        public String getSslResultMessage() {
            return sslResultMessage;
        }

        public String getSslTxnId() {
            return sslTxnId;
        }

        public String getSslApprovalCode() {
            return sslApprovalCode;
        }

        public String getSslCvv2Response() {
            return sslCvv2Response;
        }

        public String getSslAvsResponse() {
            return sslAvsResponse;
        }

        public String getSslTransactionType() {
            return sslTransactionType;
        }

        public String getSslInvoiceNo() {
            return sslInvoiceNo;
        }

        public String getSslEmail() {
            return sslEmail;
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

    public String getCheckName() {
        return checkName;
    }
    

    public void setCheckAmount(float checkAmount) {
        this.checkAmount = checkAmount;
    }

    public float getCheckAmount() {
        return checkAmount;
    }

    public String getParentPaymentId() {
        return parentPaymentId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public float getPendingAmount() {
        return pendingAmount;
    }
   

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setParentPaymentId(String parentPaymentId) {
        this.parentPaymentId = parentPaymentId;
    }

    public void setPendingAmount(float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public boolean isNfsStat() {
        return nfsStat;
    }

    public void setNfsStat(boolean nfsStat) {
        this.nfsStat = nfsStat;
    }
    
    public String getUser_comments() {
        return user_comments;
    }

    public void setUser_comments(String user_comments) {
        this.user_comments = user_comments;
    }

    public String getStaff_comments() {
        return staff_comments;
    }

    public void setStaff_comments(String staff_comments) {
        this.staff_comments = staff_comments;
    }   
    
    public void setNsf_charge_status(boolean nsf_charge_status) {
        this.nsf_charge_status = nsf_charge_status;
    }

    public boolean isNsf_charge_status() {
        return nsf_charge_status;
    }
    

    public String getEmailId() {
        return emailId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }
   
    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
      public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}
