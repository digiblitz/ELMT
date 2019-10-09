/*
 * HorseRegListVO.java
 *
 * Created on November 24, 2006, 7:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;

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

    public String getYearFoaled() {
        return yearFoaled;
    }

    
    public void setYearFoaled(String yearFoaled) {
        this.yearFoaled = yearFoaled;
    }
    private String yearFoaled;
    
    
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
                    
}