/*
 * SalesReceiptResponseVO.java
 *
 * Created on May 14, 2007, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.acc.action.util;

/**
 *
 * @author punitha
 */
public class SalesReceiptResponseVO implements java.io.Serializable{

    
    public SalesReceiptResponseVO() {
    }

    private String txnID;
    private String timeCreated;
    private String timeModified;
    private String editSequence;
    private String txnNumber;
    private String customerRefListID;
    private String customerRefFullName;
    private String txnDate;
    private String salRefNumber;

    private String billAddressAddr1;
    private String billAddressCity;
    private String billAddressState;
    private String billAddressPostalCode;

    private String shipAddressAddr1;
    private String shipAddressCity;
    private String shipAddressState;
    private String shipAddressPostalCode;

    private String paymentMethodRefListID;
    private String paymentMethodFullName;

    private String subtotal;
    private String salesTaxPercentage;
    private String totalAmount;


    private String depositToAccountRefListID;
    private String depositToAccountRefFullName;

    private String salesReceiptLineRetTxnLineID;
    private String salesReceiptLineRetItemRefListID;
    private String salesReceiptLineRetItemRefFullName;
    private String salesReceiptLineRetDesc;
    private String salesReceiptLineRetAmount;

    public void setTxnID(String txnID) {
        this.txnID = txnID;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeModified(String timeModified) {
        this.timeModified = timeModified;
    }

    public void setEditSequence(String editSequence) {
        this.editSequence = editSequence;
    }

    public void setTxnNumber(String txnNumber) {
        this.txnNumber = txnNumber;
    }


    public void setCustomerRefListID(String customerRefListID) {
        this.customerRefListID = customerRefListID;
    }

    public void setCustomerRefFullName(String customerRefFullName) {
        this.customerRefFullName = customerRefFullName;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public void setSalRefNumber(String salRefNumber) {
        this.salRefNumber = salRefNumber;
    }

    public void setBillAddressPostalCode(String billAddressPostalCode) {
        this.billAddressPostalCode = billAddressPostalCode;
    }

    public void setBillAddressState(String billAddressState) {
        this.billAddressState = billAddressState;
    }

    public void setBillAddressCity(String billAddressCity) {
        this.billAddressCity = billAddressCity;
    }

    public void setBillAddressAddr1(String billAddressAddr1) {
        this.billAddressAddr1 = billAddressAddr1;
    }
    public void setShipAddressPostalCode(String shipAddressPostalCode) {
        this.shipAddressPostalCode = shipAddressPostalCode;
    }

    public void setShipAddressState(String shipAddressState) {
        this.shipAddressState = shipAddressState;
    }

    public void setShipAddressCity(String shipAddressCity) {
        this.shipAddressCity = shipAddressCity;
    }

    public void setShipAddressAddr1(String shipAddressAddr1) {
        this.shipAddressAddr1 = shipAddressAddr1;
    }


    public void setPaymentMethodRefListID(String paymentMethodRefListID) {
        this.paymentMethodRefListID = paymentMethodRefListID;
    }

     public void setPaymentMethodFullName(String paymentMethodFullName) {
        this.paymentMethodFullName = paymentMethodFullName;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public void setDepositToAccountRefFullName(String depositToAccountRefFullName) {
        this.depositToAccountRefFullName = depositToAccountRefFullName;
    }

 

    public void setDepositToAccountRefListID(String depositToAccountRefListID) {
        this.depositToAccountRefListID = depositToAccountRefListID;
    }

    public void setSalesTaxPercentage(String salesTaxPercentage) {
        this.salesTaxPercentage = salesTaxPercentage;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setSalesReceiptLineRetAmount(String salesReceiptLineRetAmount) {
        this.salesReceiptLineRetAmount = salesReceiptLineRetAmount;
    }

    public void setSalesReceiptLineRetDesc(String salesReceiptLineRetDesc) {
        this.salesReceiptLineRetDesc = salesReceiptLineRetDesc;
    }

    public void setSalesReceiptLineRetItemRefListID(String salesReceiptLineRetItemRefListID) {
        this.salesReceiptLineRetItemRefListID = salesReceiptLineRetItemRefListID;
    }

    public void setSalesReceiptLineRetItemRefFullName(String salesReceiptLineRetItemRefFullName) {
        this.salesReceiptLineRetItemRefFullName = salesReceiptLineRetItemRefFullName;
    }

    public void setSalesReceiptLineRetTxnLineID(String salesReceiptLineRetTxnLineID) {
        this.salesReceiptLineRetTxnLineID = salesReceiptLineRetTxnLineID;
    }
}