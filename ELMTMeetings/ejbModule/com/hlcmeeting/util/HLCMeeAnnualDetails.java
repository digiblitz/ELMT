/*
 * MeeAnnualDetails.java
 *
 * Created on August 30, 2006, 9:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

/**
 *
 * @author harmohan
 */
public class HLCMeeAnnualDetails implements java.io.Serializable{
    private String annualMeetingId;
    private String userId;
    private String areaId;
    private String badgeInfo;
    private String totalAmount;
    private String requestStatus;
    private String email;
    private String paymetId;
    
    /** Creates a new instance of MeeAnnualDetails */
    public HLCMeeAnnualDetails() {
    }
    
    public HLCMeeAnnualDetails(String paymentId, String email,String annualMeetingId, String userId, String areaId, String badgeInfo,String totalAmount, String requestStatus ){
        
        this.annualMeetingId = annualMeetingId;
        this.userId = userId;
        this.areaId = areaId;
        this.badgeInfo = badgeInfo;
        this.totalAmount = totalAmount;
        this.requestStatus = requestStatus;
        this.email = email;
        this.paymetId = paymentId;
    }
   
     // getters
        public String getEmail() {return email; }
        public String getAnnualMeetingId() {return annualMeetingId; }
        public String getUserId() { return userId; }
        public String getAreaId() {return areaId; }
        public String getBadgeInfo() {return badgeInfo; }
        public String getTotalAmount() { return totalAmount;}
        public String getreRuestStatus() { return requestStatus;}
        public String getPaymetId() {return paymetId; }
         //Setters methods
        public void setEmail(String email) {this.email = email; }
        public void setAnnualMeetingId(String annualMeetingId) {this.annualMeetingId = annualMeetingId; }
        public void setUserId(String userId) {this.userId = userId; }
        public void setAreaId(String areaId) {this.areaId = areaId;}
        public void setBadgeInfo(String badgeInfo) {this.badgeInfo = badgeInfo;}
        public void setTotalAmount(String totalAmount) {this.totalAmount = totalAmount;}
        public void setreRuestStatus(String requestStatus) {this.requestStatus = requestStatus;}
        public void setPaymetId(String paymetId) { this.paymetId = paymetId; }
        
    
}