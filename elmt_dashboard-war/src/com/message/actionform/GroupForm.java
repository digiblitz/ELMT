/*
 * InsertAddCreateGroupForm.java
 *
 * Created on September 30, 2006, 17:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.message.actionform;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Shiva Kumar Subbiaha
 *
 */
public class GroupForm extends ActionForm {

	private String groupId;
	private String groupName;
	private String groupEmail;
	private String groupType;
	private String groupDesc;
	private String keyword;
	private String categoryType;	
	private String categoryName;
	private List results;

	public String toString() {

		StringBuffer buffer = new StringBuffer().append(
				"GroupId :" + groupId + "\t\t").append(
				" groupname :" + groupName + "\t\t").append(
				" groupemail :" + groupEmail + "\t\t").append(
				" grouptype :" + groupType + "\t\t").append(
				" descgroup :" + groupDesc + "\t\t").append(
				" Keyword :" + keyword + "\t\t").append(
				" Category :" + categoryType + "\t\t");

		return buffer.toString();
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}



	public String getGroupEmail() {
		return groupEmail;
	}

	public void setGroupEmail(String groupEmail) {
		this.groupEmail = groupEmail;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
	this. groupId=null;
	this. groupName=null;
	this. groupEmail=null;
	this. groupType=null;
	this. groupDesc=null;
	this. keyword=null;
	this. categoryType=null;	
	this. categoryName=null;
	this. results=null;
    }


}
