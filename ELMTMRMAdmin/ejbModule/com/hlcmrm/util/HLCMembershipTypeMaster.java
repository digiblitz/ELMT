/*
 * MembershipTypeMaster.java
 *
 * Created on August 28, 2006, 6:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;

import java.util.*;


public class HLCMembershipTypeMaster implements java.io.Serializable {

	private String membershipTypeId;
	private String membershipIypeName;
	private String userTypeId;
	private String membershipAmount;
	private Date modifyDate;

    public HLCMembershipTypeMaster() { }

    public HLCMembershipTypeMaster(String membershipTypeId,String membershipIypeName,String userTypeId,String membershipAmount,Date modifyDate ) {

		this.membershipTypeId = membershipTypeId;
		this.membershipIypeName = membershipIypeName;
		this.userTypeId = userTypeId;
		this.membershipAmount = membershipAmount;
		this.modifyDate = modifyDate;

    }

  //getter

    public  String getMembershipTypeId() {
		return membershipTypeId;
    }

	public  String getMembershipIypeName() {
		 return membershipIypeName;

    }
    public  String getUserTypeId() {
		 return userTypeId;
    }

    public  String getMembershipAmount() {
		 return  membershipAmount;
    }
    public  Date getModifyDate() {
		return modifyDate;
    }

 //setter

	public  void setMembershipTypeId(String membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
    }

	public  void setMembershipIypeName(String membershipIypeName) {
		 this.membershipIypeName = membershipIypeName;

    }
    public  void setUserTypeId(String userTypeId) {
		 this.userTypeId = userTypeId;
    }

    public  void setMembershipAmount(String membershipAmount) {
		 this.membershipAmount = membershipAmount;
    }
    public  void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
    }
}