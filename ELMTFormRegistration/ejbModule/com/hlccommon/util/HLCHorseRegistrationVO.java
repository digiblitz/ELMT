/*
 * HorseRegistrationVO.java
 *
 * Created on November 23, 2006, 6:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCHorseRegistrationVO implements java.io.Serializable{
    
    /** Creates a new instance of HorseRegistrationVO */
    public HLCHorseRegistrationVO() {
    }
    
    private String horseId;
    private String horseMemberId;
    private String competitionName;
    private String registeredName;
    private String baRegisteredName;
    private String baPastName;
    private String riderMemberId;
    private String prevRiderMemberId;
    private String addRiderMemberId;
    private String ownerId;
    private String prevOwnerId;
    private String addOwnerId;
    private String prevOwnerName;
    private String addOwnerName;
    private String paymentId;
    private boolean addOwnerStatus;
    private String membershipTypeId;
    private String horseServiceTypeId;
    private String amount;
    private String registeredBy;
    private boolean companyStatus;
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private String trainerUserId;
    private String membership_amount;
    
    public void setHorseId(String horseId) {
        this.horseId = horseId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public void setBaRegisteredName(String baRegisteredName) {
        this.baRegisteredName = baRegisteredName;
    }

    public void setBaPastName(String baPastName) {
        this.baPastName = baPastName;
    }

 
    public void setPrevRiderMemberId(String prevRiderMemberId) {
        this.prevRiderMemberId = prevRiderMemberId;
    }

    public void setAddRiderMemberId(String addRiderMemberId) {
        this.addRiderMemberId = addRiderMemberId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setPrevOwnerName(String prevOwnerName) {
        this.prevOwnerName = prevOwnerName;
    }

    public void setAddOwnerName(String addOwnerName) {
        this.addOwnerName = addOwnerName;
    }

    public String getHorseId() {
        return horseId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getRegisteredName() {
        return registeredName;
    }

    public String getBaRegisteredName() {
        return baRegisteredName;
    }

    public String getBaPastName() {
        return baPastName;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public String getPrevRiderMemberId() {
        return prevRiderMemberId;
    }

    public String getAddRiderMemberId() {
        return addRiderMemberId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPrevOwnerName() {
        return prevOwnerName;
    }

    public String getAddOwnerName() {
        return addOwnerName;
    }

    public void setPrevOwnerId(String prevOwnerId) {
        this.prevOwnerId = prevOwnerId;
    }

    public void setAddOwnerId(String addOwnerId) {
        this.addOwnerId = addOwnerId;
    }

    public String getPrevOwnerId() {
        return prevOwnerId;
    }

    public String getAddOwnerId() {
        return addOwnerId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setAddOwnerStatus(boolean addOwnerStatus) {
        this.addOwnerStatus = addOwnerStatus;
    }

    public boolean isAddOwnerStatus() {
        return addOwnerStatus;
    }

    public void setMembershipTypeId(String membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public void setHorseServiceTypeId(String horseServiceTypeId) {
        this.horseServiceTypeId = horseServiceTypeId;
    }

    public String getMembershipTypeId() {
        return membershipTypeId;
    }

    public String getHorseServiceTypeId() {
        return horseServiceTypeId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

 public void setCompanyStatus(boolean companyStatus) {
        this.companyStatus = companyStatus;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public boolean isCompanyStatus() {
        return companyStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }   

    public String getTrainerUserId() {
        return trainerUserId;
    }

    public void setTrainerUserId(String trainerUserId) {
        this.trainerUserId = trainerUserId;
    }

    public String getMembership_amount() {
        return membership_amount;
    }

    public void setMembership_amount(String membership_amount) {
        this.membership_amount = membership_amount;
    }
    
}
