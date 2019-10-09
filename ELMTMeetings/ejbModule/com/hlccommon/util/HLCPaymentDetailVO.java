/*
 * EventDetails.java
 *
 * Created on August 13, 2006, 4:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.util.*;
import java.io.*;
/**
 *
 * @author suresh
 */
 
    public class HLCPaymentDetailVO implements Serializable {
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
        private float checkAmount;
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
        private boolean activeStaus;
        private String parentPaymentId;
        private String ipAddress;
        private float pendingAmount;
        
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
        
   
        public HLCPaymentDetailVO(){}
   
        public HLCPaymentDetailVO(String paymentId, String userId,String ccName,
             String ccType, String ccNumber, int ccExpMonth,int ccExpYear,
            int ccCvvid, String bankName, Date checkDate, String checkNumber, String checkName, double amount,
            float checkAmount,Date paymentDate,String paymentStatus, String sslResult, 
                String sslResultMessage,  String sslTxnId, String sslApprovalCode,
                String sslCvv2Response, String sslAvsResponse, String sslTransactionType, 
                String sslInvoiceNo, String sslEmail,String parentPaymentId,String ipAddress,float pendingAmount,
                String ppAuthorizationID, String ppPaymentType, double ppFeeAmt, double ppSettleAmt, double ppTaxAmt,
                String ppExchangeRate, String ppPaymentStatus, String ppCorrelationID, String ppParentTransactionID, 
                String ppPendingReason, String ppReasonCode, String inVoiceID){

            this.paymentId = paymentId;
            this.userId = userId;
            this.ccName = ccName;
            this.ccType = ccType;
            this.ccNumber = ccNumber;
            this.ccExpMonth = ccExpMonth;
            this.ccExpYear = ccExpYear;
            this.ccCvvid = ccCvvid;
            this.bankName = bankName;
            this.checkDate = checkDate;
            this.checkNumber = checkNumber;
            this.checkName = checkName;
            this.amount = amount;
             this.checkAmount = checkAmount;
            this.paymentDate = paymentDate;
            this.paymentStatus = paymentStatus;
            
           this.sslResult = sslResult;
           this.sslResultMessage = sslResultMessage;
           this.sslTxnId = sslTxnId;
           this.sslApprovalCode = sslApprovalCode;
           this.sslCvv2Response = sslCvv2Response;
           this.sslAvsResponse = sslAvsResponse;
           this.sslTransactionType = sslTransactionType;
           this.sslInvoiceNo =  sslInvoiceNo;
           this.sslEmail = sslEmail;
           this.parentPaymentId = parentPaymentId;
           this.ipAddress = ipAddress;
           this.pendingAmount = pendingAmount;
           
           this.ppAuthorizationID = ppAuthorizationID;
           this.ppPaymentType = ppPaymentType;
           this.ppFeeAmt =  ppFeeAmt;
           this.ppSettleAmt = ppSettleAmt;
           this.ppTaxAmt = ppTaxAmt;
           this.ppExchangeRate = ppExchangeRate;
           this.ppPaymentStatus = ppPaymentStatus;
           this.ppCorrelationID = ppCorrelationID;
           this.ppParentTransactionID = ppParentTransactionID;
           this.ppPendingReason = ppPendingReason;
           this.ppReasonCode = ppReasonCode;
           this.inVoiceID = inVoiceID;
           
       }

        // getters
        public String getPaymentId() {
            return paymentId;
        }
        public String getUserId() {
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
       
        
    public String getIpAddress() {
        return ipAddress;
    }

    public String getParentPaymentId() {
        return parentPaymentId;
    }

    public float getPendingAmount() {
        return pendingAmount;
    }
 
    public String getPpAuthorizationID() {
        return ppAuthorizationID;
    }

    public String getPpCorrelationID() {
        return ppCorrelationID;
    }
    
    public String getPpExchangeRate() {
        return ppExchangeRate;
    }
    
    public double getPpFeeAmt() {
        return ppFeeAmt;
    }
    
    public String getPpParentTransactionID() {
        return ppParentTransactionID;
    }
 
    public String getPpPaymentStatus() {
        return ppPaymentStatus;
    }
    
    public String getPpPaymentType() {
        return ppPaymentType;
    }
    public double getPpSettleAmt() {
        return ppSettleAmt;
    }
    public double getPpTaxAmt() {
        return ppTaxAmt;
    }
    
    public String getInVoiceID() {
        return inVoiceID;
    }
   
    public String getPpPendingReason() {
        return ppPendingReason;
    }
   
    public String getPpReasonCode() {
        return ppReasonCode;
    }

    //Setters methods
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

       public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setParentPaymentId(String parentPaymentId) {
        this.parentPaymentId = parentPaymentId;
    }
   

    public void setPendingAmount(float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }
   
    public void setPpAuthorizationID(String ppAuthorizationID) {
        this.ppAuthorizationID = ppAuthorizationID;
    }
  
    public void setPpCorrelationID(String ppCorrelationID) {
        this.ppCorrelationID = ppCorrelationID;
    }
   
    public void setPpExchangeRate(String ppExchangeRate) {
        this.ppExchangeRate = ppExchangeRate;
    }
   
    public void setPpFeeAmt(double ppFeeAmt) {
        this.ppFeeAmt = ppFeeAmt;
    }
   
    public void setPpParentTransactionID(String ppParentTransactionID) {
        this.ppParentTransactionID = ppParentTransactionID;
    }
   
    public void setPpPaymentStatus(String ppPaymentStatus) {
        this.ppPaymentStatus = ppPaymentStatus;
    }
  
    public void setPpPaymentType(String ppPaymentType) {
        this.ppPaymentType = ppPaymentType;
    }
   
    public void setPpSettleAmt(double ppSettleAmt) {
        this.ppSettleAmt = ppSettleAmt;
    }
    public void setPpTaxAmt(double ppTaxAmt) {
        this.ppTaxAmt = ppTaxAmt;
    }
    public void setInVoiceID(String inVoiceID) {
        this.inVoiceID = inVoiceID;
    }
    public void setPpPendingReason(String ppPendingReason) {
        this.ppPendingReason = ppPendingReason;
    }
    public void setPpReasonCode(String ppReasonCode) {
        this.ppReasonCode = ppReasonCode;
    }
        public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("PatmentId:" + paymentId + "\n");
        sb.append("userId:" + userId + "\n");
        sb.append("ccName:" + ccName + "\n");
        sb.append("ccType:" + ccType + "\n");
        sb.append("ccNumber:" + ccNumber + "\n");
        sb.append("ccExpMonth:" + ccExpMonth + "\n");
        sb.append("ccExpYear:" + ccExpYear + "\n");
        sb.append("ccCvvid:" + ccCvvid + "\n");
        sb.append("bankName:" + bankName + "\n");
        sb.append("checkDate:" + checkDate + "\n");
        sb.append("checkNumber:" + checkNumber + "\n");
         sb.append("checkAmount:" + checkAmount + "\n");
        sb.append("checkName:" + checkName + "\n");
        sb.append("amount:" + amount + "\n");
        sb.append("paymentDate:" + paymentDate + "\n");
        sb.append("paymentStatus:" + paymentStatus + "\n");
        sb.append("sslResult:" + sslResult + "\n");
        sb.append("sslResultMessage:" + sslResultMessage + "\n");
        sb.append("sslTxnId:" + sslTxnId + "\n");
        sb.append("sslApprovalCode:" + sslApprovalCode + "\n");
        sb.append("sslCvv2Response:" + sslCvv2Response + "\n");
        sb.append("sslAvsResponse:" + sslAvsResponse + "\n");
        sb.append("sslTransactionType:" + sslTransactionType + "\n");
        sb.append("sslInvoiceNo:" + sslInvoiceNo + "\n");
        sb.append("sslEmail:" + sslEmail + "\n");
        sb.append("activeStaus:" + activeStaus + "\n");
        
        sb.append("ipAddress :" + ipAddress + "\n");
        sb.append("parentPaymentId :" + parentPaymentId + "\n");
        sb.append("pendingAmount :" + pendingAmount + "\n");
        
        sb.append("ppAuthorizationID :" + ppAuthorizationID + "\n");
        sb.append("ppPaymentType :" + ppPaymentType + "\n");
        sb.append("ppFeeAmt :" + ppFeeAmt + "\n");
        sb.append("ppSettleAmt :" + ppSettleAmt + "\n");
        sb.append("ppTaxAmt :" + ppTaxAmt + "\n");
        sb.append("ppExchangeRate :" + ppExchangeRate + "\n");
        sb.append("ppPaymentStatus :" + ppPaymentStatus + "\n");
        sb.append("ppCorrelationID :" + ppCorrelationID + "\n");
        sb.append("ppParentTransactionID :" + ppParentTransactionID + "\n");
        sb.append("ppPendingReason :" + ppPendingReason + "\n");
        sb.append("ppReasonCode :" + ppReasonCode + "\n");
        sb.append("inVoiceID :" + inVoiceID + "\n");
        
        
        return sb.toString();
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

    public void setActiveStaus(boolean activeStaus) {
        this.activeStaus = activeStaus;
    }

    public boolean isActiveStaus() {
        return activeStaus;
    }

    public void setCheckAmount(float checkAmount) {
        this.checkAmount = checkAmount;
    }

    public float getCheckAmount() {
        return checkAmount;
    }
}
