/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
/*  Program Name    : AccTransactionVO.java
 *  Created         : May 13, 2007 12:36:35 PM
 *  Author          : Karthieyan.M
 *  Version         : 1.00
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.hlcaccounts.util;

/**
 *
 * @author karthikeyan
 */

import java.io.Serializable;
import java.util.*;

public class HLCAccTransactionVO implements Serializable{
    
    public HLCAccTransactionVO() {
    }
    private String transaction_id;
    private String account_type;
    private String account_no;
    private String sub_account_no;
    private String description;
    private String class_Typ;
    
    private String payment_mode;
    private String payment_id;
    private String item_no;
    
    private int quantity;
    private float amount;
    private Date transaction_date;
    private Date sync_date;
    private boolean sync_status;
    private String user_id;
    private String ip_address;
    private boolean active_status;
    
    private float reconcile_amount;
    private String transaction_mode;
    private boolean reconcile_status;
    private String parentTransactionId;
    private boolean checkStatus;
    private String staff_ip_address;
    private String staff_user_id;
    
    public String getAccount_no() {
        return account_no;
    }
    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }
    public String getAccount_type() {
        return account_type;
    }
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
    public boolean isActive_status() {
        return active_status;
    }
    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public String getClass_Typ() {
        return class_Typ;
    }
    public void setClass_Typ(String class_Typ) {
        this.class_Typ = class_Typ;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIp_address() {
        return ip_address;
    }
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getSub_account_no() {
        return sub_account_no;
    }
    public void setSub_account_no(String sub_account_no) {
        this.sub_account_no = sub_account_no;
    }
    public Date getSync_date() {
        return sync_date;
    }
    public void setSync_date(Date sync_date) {
        this.sync_date = sync_date;
    }
    public boolean isSync_status() {
        return sync_status;
    }
    public void setSync_status(boolean sync_status) {
        this.sync_status = sync_status;
    }
    public Date getTransaction_date() {
        return transaction_date;
    }
    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }
    
    public String getPayment_mode() {
        return payment_mode;
    }
    
    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }
    
    public String getPayment_id() {
        return payment_id;
    }
    
    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }
    
    public String getItem_no() {
        return item_no;
    }
    
    public String toString(){
        StringBuffer sb = new StringBuffer();
        
        sb.append("transaction_id :" + transaction_id + "\n");
        sb.append("account_type :" + account_type + "\n");
        sb.append("account_no :" + account_no + "\n");
        sb.append("sub_account_no :" + sub_account_no + "\n");
        sb.append("description :" + description + "\n");
        sb.append("class_Typ :" + class_Typ + "\n");
        sb.append("quantity :" + quantity + "\n");
        sb.append("amount :" + amount + "\n");
        sb.append("transaction_date :" + transaction_date + "\n");
        sb.append("sync_date :" + sync_date + "\n");
        sb.append("sync_status :" + sync_status + "\n");
        sb.append("user_id :" + user_id + "\n");
        sb.append("ip_address :" + ip_address + "\n");
        sb.append("active_status :" + active_status + "\n");
        sb.append("payment_mode :" + payment_mode + "\n");
        sb.append("payment_id :" + payment_id + "\n");
        sb.append("item_no :" + item_no + "\n");
        
        sb.append("reconcile_amount :" + reconcile_amount + "\n");
        sb.append("reconcile_status :" + reconcile_status + "\n");
        sb.append("transaction_mode :" + transaction_mode + "\n");
        sb.append("staff_ip_addres "+staff_ip_address+"\n");
        sb.append("staff_user_id "+staff_user_id+"\n");
        
        return sb.toString();
    }
    
    public float getReconcile_amount() {
        return reconcile_amount;
    }
    
    public boolean isReconcile_status() {
        return reconcile_status;
    }
    
    public String getTransaction_mode() {
        return transaction_mode;
    }
    
    public void setReconcile_amount(float reconcile_amount) {
        this.reconcile_amount = reconcile_amount;
    }
    
    public void setReconcile_status(boolean reconcile_status) {
        this.reconcile_status = reconcile_status;
    }
    
    public void setTransaction_mode(String transaction_mode) {
        this.transaction_mode = transaction_mode;
    }
    
    public String getTransaction_id() {
        return transaction_id;
    }
    
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    
    public void setParentTransactionId(String parentTransactionId) {
        this.parentTransactionId = parentTransactionId;
    }
    
    public String getParentTransactionId() {
        return parentTransactionId;
    }
    
    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
    
    public boolean isCheckStatus() {
        return checkStatus;
    }

    public String getStaff_ip_address() {
        return staff_ip_address;
    }

    public String getStaff_user_id() {
        return staff_user_id;
    }

    public void setStaff_user_id(String staff_user_id) {
        this.staff_user_id = staff_user_id;
    }

    public void setStaff_ip_address(String staff_ip_address) {
        this.staff_ip_address = staff_ip_address;
    }
    
}

