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
package com.lmsstock.util;

import java.io.Serializable;

public class HLCEmployeeDetails implements Serializable {
    public String prefix;
    public String firstName;
    public String middleName;
    public String lastName;
    public String sufix;
    public String emailId;
    public String password;
    public String loginName;
    public String employeeId;
    public String counBaseLoc;
    public String baseLoc;
    public String curLoc;
    public String desig;
    public String lob;
    public String dept;
    public String supName;
    public String supEmail;
    public String curCoun;
    
    
   
    
	public String getCurCoun() {
		return curCoun;
	}
	public void setCurCoun(String curCoun) {
		this.curCoun = curCoun;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCounBaseLoc() {
		return counBaseLoc;
	}
	public void setCounBaseLoc(String counBaseLoc) {
		this.counBaseLoc = counBaseLoc;
	}
	public String getBaseLoc() {
		return baseLoc;
	}
	public void setBaseLoc(String baseLoc) {
		this.baseLoc = baseLoc;
	}
	public String getCurLoc() {
		return curLoc;
	}
	public void setCurLoc(String curLoc) {
		this.curLoc = curLoc;
	}
	public String getDesig() {
		return desig;
	}
	public void setDesig(String desig) {
		this.desig = desig;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getSupEmail() {
		return supEmail;
	}
	public void setSupEmail(String supEmail) {
		this.supEmail = supEmail;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSufix() {
		return sufix;
	}
	public void setSufix(String sufix) {
		this.sufix = sufix;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
    
    

}
