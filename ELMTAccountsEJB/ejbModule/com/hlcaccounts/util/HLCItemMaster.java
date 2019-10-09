/*
 * ItemMaster.java
 *
 * Created on July 4, 2007, 12:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;
import java.util.*;
import java.io.*;

/**
 *
 * @author Suresh
 */
public class HLCItemMaster implements Serializable{
    
    /** Creates a new instance of ItemMaster */
    public HLCItemMaster() {
    }
    
    private String itemId;
    private String serviceTypeName;
    private String itemNo;
    private String parentItemNo;
    private String itemDesc;
    private float rate;
    private String accountNo;
    private Date addDate;
    
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public void setParentItemNo(String parentItemNo) {
        this.parentItemNo = parentItemNo;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getItemId() {
        return itemId;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public String getParentItemNo() {
        return parentItemNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public float getRate() {
        return rate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Date getAddDate() {
        return addDate;
    }
    
}
