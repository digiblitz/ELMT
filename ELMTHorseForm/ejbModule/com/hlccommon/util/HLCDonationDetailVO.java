/*
 * DonationDetailVO.java
 *
 * Created on November 17, 2006, 11:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCDonationDetailVO implements java.io.Serializable{
    
    /** Creates a new instance of DonationDetailVO */
    public HLCDonationDetailVO() {
    }
    
    private String donationMapId;
    private String memberId;
    private String donationId;
    private String donationPrice;
    private String donationName;
    private String donatedBy;
    private String userId;
    private String memberDonationDate;
    private boolean precheckStatus;
    
    private String donationPaymentId; 
    private String ccNumber;
    private float totDonAmt;
    private String firstName;
    private String lastName;
    

    public boolean isPrecheckStatus() {
        return precheckStatus;
    }


    

    public String getDonatedBy() {
        return donatedBy;
    }
    
    public void setDonationMapId(String donationMapId) {
        this.donationMapId = donationMapId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public void setDonationPrice(String donationPrice) {
        this.donationPrice = donationPrice;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

     public void setDonatedBy(String donatedBy) {
        this.donatedBy = donatedBy;
    }
     
    public String getDonationMapId() {
        return donationMapId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getDonationId() {
        return donationId;
    }

    public String getDonationName() {
        return donationName;
    }

    public String getDonationPrice() {
        return donationPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberDonationDate() {
        return memberDonationDate;
    }

    public void setMemberDonationDate(String memberDonationDate) {
        this.memberDonationDate = memberDonationDate;
    }

    public void setPrecheckStatus(boolean precheckStatus) {
        this.precheckStatus = precheckStatus;
    }
    

    public String getCcNumber() {
        return ccNumber;
    }

    public String getDonationPaymentId() {
        return donationPaymentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

   

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public void setDonationPaymentId(String donationPaymentId) {
        this.donationPaymentId = donationPaymentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    public void setTotDonAmt(float totDonAmt) {
        this.totDonAmt = totDonAmt;
    }

    public float getTotDonAmt() {
        return totDonAmt;
    }
   
}
