/*
 * EventRegPendingVO.java
 *
 * Created on January 18, 2008, 2:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import java.util.Date;

/**
 *
 * @author Vidhya
 */
public class HLCEventRegPendingVO {
    
    /** Creates a new instance of EventRegPendingVO */
    public HLCEventRegPendingVO() {
    }
    
    /* for pending payment*/
    private String eventId;
    private String orgId;
    private String eveTitle;
    private String userId;
    private String payId;
    private String ccName;
    private String ccType;
    private String ssl_Txn_Id;
    private float totAmt;
    private String compName;     
    private String compLocation;
    private String compCity;
    private String compState;
    private String compCountry;
    private String compZip;
    private String compArea;
    private String paymentId;        
    private String ccNumber;
    private int cc_exp_Month;
    private int cc_exp_Year;
    private int cc_CvvId;       
    private float amt;
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
    private String statName;
    private String areaName;
    private String sslResMesg;  

    

     public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
  
    public void setCcName(String ccName) {
        this.ccName = ccName;
    }
  
    public void setCcType(String ccType) {
        this.ccType = ccType;
    }
    public void setPayId(String payId) {
        this.payId = payId;
    }
    public String getCcName() {
        return ccName;
    }
    public String getCcType() {
        return ccType;
    }
    public String getPayId() {
        return payId;
    }
  
    public void setSsl_Txn_Id(String ssl_Txn_Id) {
        this.ssl_Txn_Id = ssl_Txn_Id;
    }
  
    public String getSsl_Txn_Id() {
        return ssl_Txn_Id;
    }
    public void setTotAmt(float totAmt) {
        this.totAmt = totAmt;
    }
    public float getTotAmt() {
        return totAmt;
    }
  
    public void setEveTitle(String eveTitle) {
        this.eveTitle = eveTitle;
    }
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getEveTitle() {
        return eveTitle;
    }
    public String getEventId() {
        return eventId;
    }
     public String getOrgId() {
        return orgId;
    }
       
     /*===================================Setter Methods============================*/
      
    public void setAmt(float amt) {
        this.amt = amt;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void setCc_CvvId(int cc_CvvId) {
        this.cc_CvvId = cc_CvvId;
    }

    public void setCc_exp_Month(int cc_exp_Month) {
        this.cc_exp_Month = cc_exp_Month;
    }

    public void setCc_exp_Year(int cc_exp_Year) {
        this.cc_exp_Year = cc_exp_Year;
    }
    
    public void setCompArea(String compArea) {
        this.compArea = compArea;
    }

    public void setCompCity(String compCity) {
        this.compCity = compCity;
    }

    public void setCompCountry(String compCountry) {
        this.compCountry = compCountry;
    }

    public void setCompLocation(String compLocation) {
        this.compLocation = compLocation;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setCompState(String compState) {
        this.compState = compState;
    }

    public void setCompZip(String compZip) {
        this.compZip = compZip;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setSslApprovalCode(String sslApprovalCode) {
        this.sslApprovalCode = sslApprovalCode;
    }

    public void setSslAvsResponse(String sslAvsResponse) {
        this.sslAvsResponse = sslAvsResponse;
    }

    public void setSslCvv2Response(String sslCvv2Response) {
        this.sslCvv2Response = sslCvv2Response;
    }

    public void setSslEmail(String sslEmail) {
        this.sslEmail = sslEmail;
    }

    public void setSslInvoiceNo(String sslInvoiceNo) {
        this.sslInvoiceNo = sslInvoiceNo;
    }

    public void setSslResult(String sslResult) {
        this.sslResult = sslResult;
    }

    public void setSslResultMessage(String sslResultMessage) {
        this.sslResultMessage = sslResultMessage;
    }

    public void setSslTransactionType(String sslTransactionType) {
        this.sslTransactionType = sslTransactionType;
    }

    public void setSslTxnId(String sslTxnId) {
        this.sslTxnId = sslTxnId;
    }

  
    
 /*===================================Getter Methods============================*/ 
    
   
    public float getAmt() {
        return amt;
    }

   
    public String getCcNumber() {
        return ccNumber;
    }

    public int getCc_CvvId() {
        return cc_CvvId;
    }

    public int getCc_exp_Month() {
        return cc_exp_Month;
    }

    public int getCc_exp_Year() {
        return cc_exp_Year;
    }
    public String getCompArea() {
        return compArea;
    }

    public String getCompCity() {
        return compCity;
    }

    public String getCompCountry() {
        return compCountry;
    }

    public String getCompLocation() {
        return compLocation;
    }

    public String getCompName() {
        return compName;
    }

    public String getCompState() {
        return compState;
    }

    public String getCompZip() {
        return compZip;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getSslApprovalCode() {
        return sslApprovalCode;
    }

    public String getSslAvsResponse() {
        return sslAvsResponse;
    }

    public String getSslCvv2Response() {
        return sslCvv2Response;
    }

    public String getSslEmail() {
        return sslEmail;
    }

    public String getSslInvoiceNo() {
        return sslInvoiceNo;
    }

    public String getSslResult() {
        return sslResult;
    }

    public String getSslResultMessage() {
        return sslResultMessage;
    }

    public String getSslTransactionType() {
        return sslTransactionType;
    }

    public String getSslTxnId() {
        return sslTxnId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public String getStatName() {
        return statName;
    }
  
    public String getAreaName() {
        return areaName;
    }
    
   
}
