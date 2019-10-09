/*
 * AnnualRegisterVO.java
 *
 * Created on November 1, 2006, 1:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import java.util.*;


/**
 *
 * @author suresh
 */
public class HLCAnnualRegisterVO implements java.io.Serializable {
    
    /** Creates a new instance of AnnualRegisterVO */
    public HLCAnnualRegisterVO() {
    }
    
    private String annualMeetingId;
    private String registrarId;
    private String noOfAddTickets;
    private String totalAmount;
    private boolean activeStatus;
    private Date addDate;
    private String firstName;
    private String lastName;
    private String email;
    private String requestStatus;
    private String comments;
    private String checkNumber;
    private String paymentMode;
    private String paymentId;
    private String paymentBy;
    private String transactionId;
    private String bankName;
    private Date checkDate;
    

    public String getAnnualMeetingId() {
        return annualMeetingId;
    }

    public String getRegistrarId() {
        return registrarId;
    }

    public String getNoOfAddTickets() {
        return noOfAddTickets;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAnnualMeetingId(String annualMeetingId) {
        this.annualMeetingId = annualMeetingId;
    }

    public void setRegistrarId(String registrarId) {
        this.registrarId = registrarId;
    }

    public void setNoOfAddTickets(String noOfAddTickets) {
        this.noOfAddTickets = noOfAddTickets;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentBy(String paymentBy) {
        this.paymentBy = paymentBy;
    }

    public String getPaymentBy() {
        return paymentBy;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }
    
}
