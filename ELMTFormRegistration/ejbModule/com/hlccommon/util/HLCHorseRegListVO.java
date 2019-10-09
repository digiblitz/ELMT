/*
 * HorseRegListVO.java
 *
 * Created on November 24, 2006, 7:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

import java.util.Date;

/**
 *
 * @author suresh
 */
public class HLCHorseRegListVO implements java.io.Serializable{
    
    /** Creates a new instance of HorseRegListVO */
    public HLCHorseRegListVO() {
    }
    
    private String horseId;
    private String horseMemberId;
    private String horseName;
    private String riderID;
    private String firstName;
    private String lastName;
    private String reqStatus;
    private Date addDate;
    private String ownerId;
    private String membershipTypeId;
    private String membershipTypeName;
    private String registeredBy;
    private String statusName;
    private float amount;
    private float checkAmount;
    private float pendingAmount;
    private String paymentId;
    
    
    
    private String ownerLogName;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerEmail;
    private String riderFirstName;
    private String riderLastName;
    private String statusId;
    private String statusName1;
    private String relationTypeId;
    private String relationTypeName;
    private String preOwnerFName;
    private String preOwnerLName;
    private String preRiderFName;
    private String preRiderLName;
    private String preEmail;
    private String relationStatus;
    
    
    public void setHorseId(String horseId) {
        this.horseId = horseId;
    }
    
    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }
    
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }
    
    public void setRiderID(String riderID) {
        this.riderID = riderID;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
    
    public String getHorseId() {
        return horseId;
    }
    
    public String getHorseMemberId() {
        return horseMemberId;
    }
    
    public String getHorseName() {
        return horseName;
    }
    
    public String getRiderID() {
        return riderID;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getReqStatus() {
        return reqStatus;
    }
    
    public Date getAddDate() {
        return addDate;
    }
    
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    public String getOwnerId() {
        return ownerId;
    }
    
    public void setMembershipTypeId(String membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }
    
    public void setMembershipTypeName(String membershipTypeName) {
        this.membershipTypeName = membershipTypeName;
    }
    
    public String getMembershipTypeId() {
        return membershipTypeId;
    }
    
    public String getMembershipTypeName() {
        return membershipTypeName;
    }
    
    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }
    
    public String getRegisteredBy() {
        return registeredBy;
    }
    
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    public String getStatusName() {
        return statusName;
    }
    
    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public void setCheckAmount(float checkAmount) {
        this.checkAmount = checkAmount;
    }
    
    public void setPendingAmount(float pendingAmount) {
        this.pendingAmount = pendingAmount;
    }
    
    public float getAmount() {
        return amount;
    }
    
    public float getPendingAmount() {
        return pendingAmount;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public float getCheckAmount() {
        return checkAmount;
    }
    
    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }
    
    
    
    public void setOwnerLogName(String ownerLogName) {
        this.ownerLogName = ownerLogName;
    }
    
    public String getOwnerFirstName() {
        return ownerFirstName;
    }
    
    
    public String getOwnerLogName() {
        return ownerLogName;
    }
    
    
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
    
    public String getOwnerEmail() {
        return ownerEmail;
    }
    
    
    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }
    
    public String getOwnerLastName() {
        return ownerLastName;
    }
    
    public void setRiderFirstName(String riderFirstName) {
        this.riderFirstName = riderFirstName;
    }
    
    public String getRiderFirstName() {
        return riderFirstName;
    }
    
    public void setRiderLastName(String riderLastName) {
        this.riderLastName = riderLastName;
    }
    
    public String getRiderLastName() {
        return riderLastName;
    }
    
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    
    public String getStatusId() {
        return statusId;
    }
    
    public void setStatusName1(String statusName1) {
        this.statusName1 = statusName1;
    }
    
    public String getStatusName1() {
        return statusName1;
    }
    
    
    
    public void setRelationTypeName(String relationTypeName) {
        this.relationTypeName = relationTypeName;
    }
    
    
    public void setRelationTypeId(String relationTypeId) {
        this.relationTypeId = relationTypeId;
    }
    
    
    public void setPreRiderLName(String preRiderLName) {
        this.preRiderLName = preRiderLName;
    }
    
    
    public void setPreRiderFName(String preRiderFName) {
        this.preRiderFName = preRiderFName;
    }
    
    
    public void setPreOwnerFName(String preOwnerFName) {
        this.preOwnerFName = preOwnerFName;
    }
    
    public void setPreOwnerLName(String preOwnerLName) {
        this.preOwnerLName = preOwnerLName;
    }
    
    
    public String getRelationTypeName() {
        return relationTypeName;
    }
    
    
    public String getRelationTypeId() {
        return relationTypeId;
    }
    
    
    public String getPreRiderFName() {
        return preRiderFName;
    }
    
    
    public String getPreRiderLName() {
        return preRiderLName;
    }
    
    public String getPreOwnerFName() {
        return preOwnerFName;
    }
    
    public String getPreOwnerLName() {
        return preOwnerLName;
    }
    
    public void setPreEmail(String preEmail) {
        this.preEmail = preEmail;
    }
    
    public String getPreEmail() {
        return preEmail;
    }
    
    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus;
    }
    
    public String getRelationStatus() {
        return relationStatus;
    }
}
