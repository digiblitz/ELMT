/*
 * DonationVO.java
 *
 * Created on November 15, 2006, 5:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

/**
 *
 * @author suresh
 */
public class HLCDonationVO implements java.io.Serializable{
    
    /** Creates a new instance of DonationVO */
    public HLCDonationVO() {
    }
    
    private String donationId;
    private String donationName;
    private String donationPrice;
    private boolean activeStatus;
    private boolean precheckStatus;
    private String transaction_type_id;
    private int priorityValue;

    public void setTransaction_type_id(String transaction_type_id) {
        this.transaction_type_id = transaction_type_id;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public void setDonationPrice(String donationPrice) {
        this.donationPrice = donationPrice;
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

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public boolean isPrecheckStatus() {
        return precheckStatus;
    }

    public void setPrecheckStatus(boolean precheckStatus) {
        this.precheckStatus = precheckStatus;
    }

    public String getTransaction_type_id() {
        return transaction_type_id;
    }
   
    public int getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(int priorityValue) {
        this.priorityValue = priorityValue;
    }
    
}
