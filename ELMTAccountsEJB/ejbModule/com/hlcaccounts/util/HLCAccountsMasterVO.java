/*
 * AccountsMasterVO.java
 *
 * Created on May 28, 2007, 4:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;

/**
 *
 * @author karthikeyan
 */

import java.util.*;
import java.io.Serializable;

public class HLCAccountsMasterVO implements Serializable{
    
    public HLCAccountsMasterVO() {
    }
    private String account_id;
    private String account_type;
    private String account_no;
    private String account_name;
    private String parent_account_no;
    private String account_desc;
    private String bank_account_no;
    private Date addDate;
    
    public String getAccount_desc() {
        return account_desc;
    }
    
    public void setAccount_desc(String account_desc) {
        this.account_desc = account_desc;
    }
    
    public String getAccount_id() {
        return account_id;
    }
    
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    
    public String getAccount_name() {
        return account_name;
    }
    
    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
    
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
    
    public String getBank_account_no() {
        return bank_account_no;
    }
    
    public void setBank_account_no(String bank_account_no) {
        this.bank_account_no = bank_account_no;
    }
    
    public String getParent_account_no() {
        return parent_account_no;
    }
    
    public void setParent_account_no(String parent_account_no) {
        this.parent_account_no = parent_account_no;
    }
    
    public String toString(){
        
        StringBuffer sb = new StringBuffer();
        
        sb.append("account_id :" + account_id + "\n");
        sb.append("account_type :" + account_type + "\n");
        sb.append("account_no :" + account_no + "\n");
        
        sb.append("account_name :" + account_name + "\n");
        sb.append("parent_account_no :" + parent_account_no + "\n");
        sb.append("account_desc :" + account_desc + "\n");
        sb.append("bank_account_no :" + bank_account_no + "\n");
        
        return sb.toString();
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
    
    public Date getAddDate() {
        return addDate;
    }
    
}

