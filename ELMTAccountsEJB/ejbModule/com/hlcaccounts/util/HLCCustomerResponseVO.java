/*
 * CustomerResponseVO.java
 *
 * Created on May 30, 2007, 1:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;

/**
 *
 * @author suresh
 */

public class HLCCustomerResponseVO implements java.io.Serializable {

    /** Creates a new instance of CustomerResponseVO */
    public HLCCustomerResponseVO() {
    }

    private String listId;
    private String timeCreated;
    private String timeModified;
    private String editSequence;
    private String name;
    private String fullName;
    private String subLevel;
    private String companyName;
    private String salutation;

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String postal;
	private String country;
    private String phone;
    private String altPhone;
    private String email;
    private String termsRefListId;
    private String termsRefFullName;
    private String balance;
    private String totalBalance;


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

    public void setSubLevel(String subLevel) {
        this.subLevel = subLevel;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

	public void setCountry(String country){
		this.country = country;
	}

    public void setTermsRefListId(String termsRefListId) {
        this.termsRefListId = termsRefListId;
    }

    public void setTermsRefFullName(String termsRefFullName) {
        this.termsRefFullName = termsRefFullName;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSubLevel() {
        return subLevel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostal() {
        return postal;
    }

	public String getCountry(){
		return country;
	}

    public String getPhone() {
        return phone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getTermsRefListId() {
        return termsRefListId;
    }

    public String getTermsRefFullName() {
        return termsRefFullName;
    }

    public String getBalance() {
        return balance;
    }

    public String getTotalBalance() {
        return totalBalance;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer()
        .append("listId=" + this.listId+"\n")
        .append("timeCreated=" + this.timeCreated+"\n")
        .append("timeModified=" + this.timeModified+"\n")

        .append("editSequence=" + this.editSequence+"\n")
        .append("name=" + this.name+"\n")
        .append("fullName=" + this.fullName+"\n")
        .append("subLevel=" + this.subLevel+"\n")

        .append("editSequence=" + this.editSequence+"\n")
        .append("name=" + this.name+"\n")
        .append("fullName=" + this.fullName+"\n")
        .append("subLevel=" + this.subLevel+"\n")

        .append("companyName=" + this.companyName+"\n")
        .append("salutation=" + this.salutation+"\n")
        .append("firstName=" + this.firstName+"\n")
        .append("lastName=" + this.lastName+"\n")
        .append("address=" + this.address+"\n")
        .append("lastName=" + this.lastName+"\n")
        .append("city=" + this.city+"\n")
        .append("state=" + this.state+"\n")
        .append("country=" + this.country+"\n")
        .append("postal=" + this.postal+"\n")
        .append("phone=" + this.phone+"\n")
        .append("altPhone=" + this.altPhone+"\n")
        .append("email=" + this.email+"\n")
        .append("termsRefListId=" + this.termsRefListId+"\n")
        .append("balance=" + this.balance+"\n")
        .append("totalBalance=" + this.totalBalance+"\n");
        return sb.toString();
    }
}
