/*
 * EndorsedFormVO.java
 *
 * Created on December 20, 2007, 2:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;
import java.util.Date;

/**
 *
 * @author Dhivya
 */
public class HLCEndorsedFormVO {
    
    /** Creates a new instance of EndorsedFormVO */
    public HLCEndorsedFormVO() {
    }
    
    private String compResultId;
        private String renewId;
        private String eventId;
        private String orgId;
        private String compName;
        private Date compDate;
        private String com_Mgt_Name;
        private String com_Mgt_Addrs;
        private String com_Mgt_City;
        private String com_Mgt_State;
        private String com_Mgt_Country;
        private String com_Mgt_Zip;
        private String com_Mgt_Phone;
        private String com_Mgt_Fax;
        private String manager_usef_memberId;
        private String manager_usea_memberId;
        private String mgr_Name;
        private String secretary_usef_memberId;
        private String sectryName;
        private String reqStatus;
        private String compLocation;
        private String compCity;
        private String compState;
        private String compCountry;
        private String compZip;
        private String compArea;
        private String paymentId;
        private String userId;
        private String ccName;
        private String ccType;
        private String ccNumber;
        private int cc_exp_Month;
        private int cc_exp_Year;
        private int cc_CvvId;
        private String bankName;
        private Date chkDt;
        private int chkNumber;
        private String chkName;
        private float chkAmount;
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
        private float pendingAmount;
        private String parentPaymentId;
        private String ipAddress;
        private boolean nsfStatus;
        private boolean nsfChargeStatus;
        private String userComments;
        private String staffComments;
        private String statName;
        private String areaName;
        private Date nsfDate;
        private boolean reverseEntryStatus;        
        private Date addDate;
        private boolean activeStatus;
         
     /*===================================Setter Methods============================*/
        
 
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setAmt(float amt) {
        this.amt = amt;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
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

    public void setChkAmount(float chkAmount) {
        this.chkAmount = chkAmount;
    }

    public void setChkDt(Date chkDt) {
        this.chkDt = chkDt;
    }

    public void setChkName(String chkName) {
        this.chkName = chkName;
    }

    public void setChkNumber(int chkNumber) {
        this.chkNumber = chkNumber;
    }

    public void setCom_Mgt_Addrs(String com_Mgt_Addrs) {
        this.com_Mgt_Addrs = com_Mgt_Addrs;
    }

    public void setCom_Mgt_City(String com_Mgt_City) {
        this.com_Mgt_City = com_Mgt_City;
    }

    public void setCom_Mgt_Country(String com_Mgt_Country) {
        this.com_Mgt_Country = com_Mgt_Country;
    }

    public void setCom_Mgt_Fax(String com_Mgt_Fax) {
        this.com_Mgt_Fax = com_Mgt_Fax;
    }

    public void setCom_Mgt_Name(String com_Mgt_Name) {
        this.com_Mgt_Name = com_Mgt_Name;
    }

    public void setCom_Mgt_Phone(String com_Mgt_Phone) {
        this.com_Mgt_Phone = com_Mgt_Phone;
    }

    public void setCom_Mgt_State(String com_Mgt_State) {
        this.com_Mgt_State = com_Mgt_State;
    }

    public void setCom_Mgt_Zip(String com_Mgt_Zip) {
        this.com_Mgt_Zip = com_Mgt_Zip;
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

    public void setCompDate(Date compDate) {
        this.compDate = compDate;
    }

    public void setCompLocation(String compLocation) {
        this.compLocation = compLocation;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public void setCompResultId(String compResultId) {
        this.compResultId = compResultId;
    }

    public void setCompState(String compState) {
        this.compState = compState;
    }

    public void setCompZip(String compZip) {
        this.compZip = compZip;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setManager_usea_memberId(String manager_usea_memberId) {
        this.manager_usea_memberId = manager_usea_memberId;
    }

    public void setManager_usef_memberId(String manager_usef_memberId) {
        this.manager_usef_memberId = manager_usef_memberId;
    }

    public void setMgr_Name(String mgr_Name) {
        this.mgr_Name = mgr_Name;
    }

    public void setNsfChargeStatus(boolean nsfChargeStatus) {
        this.nsfChargeStatus = nsfChargeStatus;
    }

    public void setNsfDate(Date nsfDate) {
        this.nsfDate = nsfDate;
    }

    public void setNsfStatus(boolean nsfStatus) {
        this.nsfStatus = nsfStatus;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setParentPaymentId(String parentPaymentId) {
        this.parentPaymentId = parentPaymentId;
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

    public void setPendingAmount(float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public void setRenewId(String renewId) {
        this.renewId = renewId;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public void setReverseEntryStatus(boolean reverseEntryStatus) {
        this.reverseEntryStatus = reverseEntryStatus;
    }

    public void setSecretary_usef_memberId(String secretary_usef_memberId) {
        this.secretary_usef_memberId = secretary_usef_memberId;
    }

    public void setSectryName(String sectryName) {
        this.sectryName = sectryName;
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

    public void setStaffComments(String staffComments) {
        this.staffComments = staffComments;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
 /*===================================Getter Methods============================*/ 
    
    public Date getAddDate() {
        return addDate;
    }

    public float getAmt() {
        return amt;
    }

    public String getBankName() {
        return bankName;
    }

    public String getCcName() {
        return ccName;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public String getCcType() {
        return ccType;
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

    public float getChkAmount() {
        return chkAmount;
    }

    public Date getChkDt() {
        return chkDt;
    }

    public String getChkName() {
        return chkName;
    }

    public int getChkNumber() {
        return chkNumber;
    }

    public String getCom_Mgt_Addrs() {
        return com_Mgt_Addrs;
    }

    public String getCom_Mgt_City() {
        return com_Mgt_City;
    }

    public String getCom_Mgt_Country() {
        return com_Mgt_Country;
    }

    public String getCom_Mgt_Fax() {
        return com_Mgt_Fax;
    }

    public String getCom_Mgt_Name() {
        return com_Mgt_Name;
    }

    public String getCom_Mgt_Phone() {
        return com_Mgt_Phone;
    }

    public String getCom_Mgt_State() {
        return com_Mgt_State;
    }

    public String getCom_Mgt_Zip() {
        return com_Mgt_Zip;
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

    public Date getCompDate() {
        return compDate;
    }

    public String getCompLocation() {
        return compLocation;
    }

    public String getCompName() {
        return compName;
    }

    public String getCompResultId() {
        return compResultId;
    }

    public String getCompState() {
        return compState;
    }

    public String getCompZip() {
        return compZip;
    }

    public String getEventId() {
        return eventId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getManager_usea_memberId() {
        return manager_usea_memberId;
    }

    public String getManager_usef_memberId() {
        return manager_usef_memberId;
    }

    public String getMgr_Name() {
        return mgr_Name;
    }

    public Date getNsfDate() {
        return nsfDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getParentPaymentId() {
        return parentPaymentId;
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

    public float getPendingAmount() {
        return pendingAmount;
    }

    public String getRenewId() {
        return renewId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public String getSecretary_usef_memberId() {
        return secretary_usef_memberId;
    }

    public String getSectryName() {
        return sectryName;
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

    public String getStaffComments() {
        return staffComments;
    }

    public String getUserComments() {
        return userComments;
    }

    public String getUserId() {
        return userId;
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
