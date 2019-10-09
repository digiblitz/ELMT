/*
 * MembershipTypeMaster.java
 *
 * Created on August 28, 2006, 6:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmember.type.util;
import java.util.*;

/**
 *
 * @author harmohan
 */
public class HLCMembershipTypeMaster implements java.io.Serializable {

	private String membershipTypeId;
	private String membershipTypeName;
	private String userTypeId;
	private String membershipAmount;
    private String userTypeName;
    private Date modifyDate;
    private String periodValue;
    private String transaction_type_id;
	//for bug starts
	private int active_status;
    private String duration;
	//for bug ends
    private int membership_year;

    public HLCMembershipTypeMaster() { }

    public HLCMembershipTypeMaster(String membershipTypeId,String userTypeName,String membershipTypeName,String userTypeId,String membershipAmount,Date modifyDate, int membership_year ) {
            this.membershipTypeId = membershipTypeId;
            this.membershipTypeName = membershipTypeName;
            this.userTypeId = userTypeId;
            this.membershipAmount = membershipAmount;
            this.modifyDate = modifyDate;
            this.userTypeName = userTypeName;
            this.periodValue = periodValue;
            this.transaction_type_id = transaction_type_id;
            this.membership_year = membership_year;
			//for bug starts
			this.active_status = active_status;
            this.duration = duration;
			//for bug ends

    }


  //getter

    public  String getMembershipTypeId() {
	return membershipTypeId;
    }
    public  String getMembershipTypeName() {
	return membershipTypeName;
    }
    public  String getUserTypeId() {
	return userTypeId;
    }
     public String getUserTypeName(){
        return userTypeName;
    }
    public  String getMembershipAmount() {
	return  membershipAmount;
    }
    public  Date getModifyDate() {
	return modifyDate;
    }
    public String getPeriodValue() {
        Debug.print("Period VAlue is SEt "+periodValue);
        return periodValue;
    }
 //setter

	public  void setMembershipTypeId(String membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
    }

	public  void setMembershipTypeName(String membershipTypeName) {
		 this.membershipTypeName = membershipTypeName;

    }
    public  void setUserTypeId(String userTypeId) {
		 this.userTypeId = userTypeId;
    }
    public void setUserTypeName(String userTypeName){
        this.userTypeName = userTypeName;
    }
    public  void setMembershipAmount(String membershipAmount) {
		 this.membershipAmount = membershipAmount;
    }
    public  void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
    }

    public void setPeriodValue(String periodValue) {
        Debug.print("Period VAlue is SEt "+periodValue);
        this.periodValue = periodValue;
    }

    public String getTransaction_type_id() {
        return transaction_type_id;
    }

    public void setTransaction_type_id(String transaction_type_id) {
        this.transaction_type_id = transaction_type_id;
    }

    public int getMembership_year() {
        return membership_year;
    }

    public void setMembership_year(int membership_year) {
        this.membership_year = membership_year;
    }
	//for bug starts
    public int getActive_Status() {
        return active_status;
    }

    public void setActive_Status(int active_status) {
        this.active_status = active_status;
    }

    public String getDuration()
    {
        return duration;
    }
    public void setDuration(String duration)
    {
        this.duration=duration;
    }
    //for bug ends


}