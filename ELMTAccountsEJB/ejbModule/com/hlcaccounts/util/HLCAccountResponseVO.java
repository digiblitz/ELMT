/*
 * AccountResponseVO.java
 *
 * Created on May 30, 2007, 1:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;

/**
 *
 * @author suresh
 */
public class HLCAccountResponseVO implements java.io.Serializable {

    /** Creates a new instance of AccountResponseVO */
    public HLCAccountResponseVO() {
    }

    private String listId;
    private String timeCreated;
    private String timeModified;
    private String editSequence;
    private String name;
    private String fullName;
    private String sublevel;
    private String parentRefListId;
    private String parentFullName;
    private String accountType;
    private String detailAccountType;
    private String balance;
    private String totalBalance;
    private String desc;

    public void setListId(String listId) {
        this.listId = listId;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSublevel(String sublevel) {
        this.sublevel = sublevel;
    }

    public void setParentRefListId(String parentRefListId) {
        this.parentRefListId = parentRefListId;
    }

    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setDetailAccountType(String detailAccountType) {
        this.detailAccountType = detailAccountType;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getListId() {
        return listId;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public String getTimeModified() {
        return timeModified;
    }

    public String getEditSequence() {
        return editSequence;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSublevel() {
        return sublevel;
    }

    public String getParentRefListId() {
        return parentRefListId;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getDetailAccountType() {
        return detailAccountType;
    }

    public String getBalance() {
        return balance;
    }

    public String getTotalBalance() {
        return totalBalance;
    }

    public String getDesc() {
        return desc;
    }


  	public String toString(){
        StringBuffer buffer = new StringBuffer()
        .append("listId=" + this.listId+",")
        .append("\ntimeCreated=" + this.timeCreated+",")
        .append("\ntimeModified=" + this.timeModified+",")
        .append("\neditSequence=" + this.editSequence+",")
        .append("\nname=" + this.name+",")
        .append("\nfullName=" + this.fullName+",")
        .append("\nsublevel=" + this.sublevel)
        .append("\nparentRefListId=" + this.parentRefListId+",")
        .append("\nparentFullName=" + this.parentFullName)
        .append("\naccountType=" + this.accountType+",")
        .append("\ndetailAccountType=" + this.detailAccountType)
        .append("\nbalance=" + this.balance)
        .append("\ntotalBalance=" + this.totalBalance)
        .append("\ndesc=" + this.desc)
        .append("\n=====================");
        return buffer.toString();
    }

}
