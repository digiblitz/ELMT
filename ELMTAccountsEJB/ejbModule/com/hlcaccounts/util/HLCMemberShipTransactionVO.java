/*
 * MemberShipTransactionVO.java
 *
 * Created on May 29, 2007, 5:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;
import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author Suresh
 */
public class HLCMemberShipTransactionVO implements Serializable{
    
    /** Creates a new instance of MemberShipTransactionVO */
    public HLCMemberShipTransactionVO() {
    }
    
    private String itemNo;
    private String paymentMode;
    private String accountClass;
    private double amount;
    private Date transactionDate;
    private String accountNo;
    private String accountName;
    private String desc;
    private String payment_id;
    
    private String bank_name;
    private Date check_date;
    private String check_number;
    private String check_name;
    private float check_amount;
    
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getAccountClass() {
        return accountClass;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setCheck_date(Date check_date) {
        this.check_date = check_date;
    }

    public Date getCheck_date() {
        return check_date;
    }

    public void setCheck_number(String check_number) {
        this.check_number = check_number;
    }

    public String getCheck_number() {
        return check_number;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_amount(float check_amount) {
        this.check_amount = check_amount;
    }

    public float getCheck_amount() {
        return check_amount;
    }
    
    
}
