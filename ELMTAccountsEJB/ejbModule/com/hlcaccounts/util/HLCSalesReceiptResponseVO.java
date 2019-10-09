/*
 * SalesReceiptResponseVO.java
 *
 * Created on May 30, 2007, 1:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;
import java.util.ArrayList;
/**
 *
 * @author suresh
 */
public class HLCSalesReceiptResponseVO implements java.io.Serializable{

    /** Creates a new instance of SalesReceiptResponseVO */
    public HLCSalesReceiptResponseVO() {
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
    private ArrayList salesReceiptLineRet;
    

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

    public void setSalesTaxPercentage(String salesTaxPercentage) {
        this.salesTaxPercentage = salesTaxPercentage;
    }

     public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    

    public void setDepositToAccountRefFullName(String depositToAccountRefFullName) {
        this.depositToAccountRefFullName = depositToAccountRefFullName;
    }

    public void setDepositToAccountRefListID(String depositToAccountRefListID) {
        this.depositToAccountRefListID = depositToAccountRefListID;
    }

    public void setSalesReceiptLineRetTxnLineID(String salesReceiptLineRetTxnLineID) {
        this.salesReceiptLineRetTxnLineID = salesReceiptLineRetTxnLineID;
    }

    public void setSalesReceiptLineRetItemRefListID(String salesReceiptLineRetItemRefListID) {
        this.salesReceiptLineRetItemRefListID = salesReceiptLineRetItemRefListID;
    }

    public void setSalesReceiptLineRetItemRefFullName(String salesReceiptLineRetItemRefFullName) {
        this.salesReceiptLineRetItemRefFullName = salesReceiptLineRetItemRefFullName;
    }

    public void setSalesReceiptLineRetDesc(String salesReceiptLineRetDesc) {
        this.salesReceiptLineRetDesc = salesReceiptLineRetDesc;
    }

    public void setSalesReceiptLineRetAmount(String salesReceiptLineRetAmount) {
        this.salesReceiptLineRetAmount = salesReceiptLineRetAmount;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public String getCustomerRefFullName() {
        return customerRefFullName;
    }

    public String getCustomerRefListID() {
        return customerRefListID;
    }

    public String getTxnNumber() {
        return txnNumber;
    }

    public String getEditSequence() {
        return editSequence;
    }

    public String getTimeModified() {
        return timeModified;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public String getTxnID() {
        return txnID;
    }

    public String getSalRefNumber() {
        return salRefNumber;
    }

    public String getBillAddressPostalCode() {
        return billAddressPostalCode;
    }

    public String getBillAddressState() {
        return billAddressState;
    }

    public String getBillAddressCity() {
        return billAddressCity;
    }

    public String getBillAddressAddr1() {
        return billAddressAddr1;
    }
    public String getShipAddressPostalCode() {
        return shipAddressPostalCode;
    }

    public String getShipAddressState() {
        return shipAddressState;
    }

    public String getShipAddressCity() {
        return shipAddressCity;
    }

    public String getShipAddressAddr1() {
        return shipAddressAddr1;
    }

    public String getPaymentMethodFullName() {
        return paymentMethodFullName;
    }

    public String getPaymentMethodRefListID() {
        return paymentMethodRefListID;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getSalesTaxPercentage() {
        return salesTaxPercentage;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public String getDepositToAccountRefFullName() {
        return depositToAccountRefFullName;
    }

    public String getDepositToAccountRefListID() {
        return depositToAccountRefListID;
    }

    public String getSalesReceiptLineRetTxnLineID() {
        return salesReceiptLineRetTxnLineID;
    }

    public String getSalesReceiptLineRetItemRefListID() {
        return salesReceiptLineRetItemRefListID;
    }

    public String getSalesReceiptLineRetItemRefFullName() {
        return salesReceiptLineRetItemRefFullName;
    }

    public String getSalesReceiptLineRetDesc() {
        return salesReceiptLineRetDesc;
    }

    public String getSalesReceiptLineRetAmount() {
        return salesReceiptLineRetAmount;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer()
        .append("txnID=" + this.txnID+"\n")
        .append("timeCreated=" + this.timeCreated+"\n")
        .append("timeModified=" + this.timeModified+"\n")
        .append("editSequence=" + this.editSequence+"\n")
        .append("txnNumber=" + this.txnNumber+"\n")
        .append("customerRefListID=" + this.customerRefListID+"\n")
        .append("customerRefFullName=" + this.customerRefFullName + "\n")
        .append("txnDate=" + this.txnDate+"\n")
        .append("salRefNumber=" + this.salRefNumber + "\n")
        .append("billAddressAddr1=" + this.billAddressAddr1+"\n")
        .append("billAddressCity=" + this.billAddressCity + "\n")
        .append("billAddressState=" + this.billAddressState + "\n")
        .append("billAddressPostalCode=" + this.billAddressPostalCode + "\n")

        .append("shipAddressAddr1=" + this.shipAddressAddr1+"\n")
        .append("shipAddressCity=" + this.shipAddressCity + "\n")
        .append("shipAddressState=" + this.shipAddressState + "\n")
        .append("shipAddressPostalCode=" + this.shipAddressPostalCode+ "\n")

        .append("paymentMethodRefListID=" + this.paymentMethodRefListID+"\n")
        .append("paymentMethodFullName=" + this.paymentMethodFullName + "\n")
        .append("subtotal=" + this.subtotal + "\n")
        .append("salesTaxPercentage=" + this.salesTaxPercentage + "\n")

        .append("totalAmount=" + this.totalAmount+"\n")
        .append("depositToAccountRefListID=" + this.depositToAccountRefListID+"\n")
        .append("depositToAccountRefFullName=" + this.depositToAccountRefFullName + "\n")
        .append("salesReceiptLineRetTxnLineID=" + this.salesReceiptLineRetTxnLineID + "\n")
        .append("salesReceiptLineRetItemRefListID=" + this.salesReceiptLineRetItemRefListID + "\n")
        .append("salesReceiptLineRetItemRefFullName=" + this.salesReceiptLineRetItemRefFullName + "\n")
        .append("salesReceiptLineRetDesc=" + this.salesReceiptLineRetDesc + "\n")
        .append("salesReceiptLineRetAmount=" + this.salesReceiptLineRetAmount);
        return sb.toString();
    }

    public void setSalesReceiptLineRet(ArrayList salesReceiptLineRet) {
        this.salesReceiptLineRet = salesReceiptLineRet;
    }

    public ArrayList getSalesReceiptLineRet() {
        return salesReceiptLineRet;
    }
}
