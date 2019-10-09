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
package com.hlchorse.form.util;

import java.io.Serializable;
import java.util.List;


public class HLCHorseDisplayVO implements Serializable {

        private List horseServiceTypeName;
	private String memberId;
	private String horseMemberId;
	private String competitionName;
	private String registeredName;
	private String baRegisteredName;
	private String baPastName;
        private String membershipTypeName;
	
	private String riderPrefix; 
	private String riderFirstName; 
	private String riderMiddleName; 
	private String riderLastName; 
	private String riderSuite;
	private String riderAddress1;
	private String riderAddress2; 
	private String riderCity;
	private String riderState; 
	private String riderZip;
	private String riderPhoneNo;
	private String riderMobileNo;
	private String riderEmailId;
	private String riderUserId;
	private String riderFaxNo;
	private String riderCountry;
        private String riderMemberId;

	private String prevRiderUserId;
	private String prevRiderPrefix;
	private String prevRiderFirstName;
	private String prevRiderMiddleName;
	private String prevRiderLastName;
	private String prevRiderMemberId;
	
	private String addRiderUserId;
	private String addRiderPrefix;
	private String addRiderFirstName;
	private String addRiderMiddleName;
	private String addRiderLastName;
	private String addRiderMemberId;
	
	private String ownerPrefix; 
	private String ownerFirstName; 
	private String ownerMiddleName; 
	private String ownerLastName; 
	private String ownerSuite;
	private String ownerAddress1;
	private String ownerAddress2; 
	private String ownerCity;
	private String ownerState; 
	private String ownerZip;
	private String ownerPhoneNo;
	private String ownerMobileNo;
	private String ownerEmailId;
	private String ownerUserId;
	private String ownerFaxNo;
	private String ownerCountry;
	private String addOwnerName;
	private String prevOwnerName;
	
	private String horseColor; 
	private String horseGender; 
	private String horseHeight; 
	private String horseYearFoaled;
	private String horseBreed; 
	private String horseCountry; 
	private String horseSire;
	private String horseSireBreed; 
	private String horseDam; 
	private String horseDamBreed; 
	private String horseImportedFrom;      	
	private String horseImportDate; 
	private String horseForeignGrade; 
	private String horseForeignPoints; 
	private String horseAssignedGrade; 
	private String horseAssignedPoints;
        private String statusId;
        private String statusName;
                
        
        
        public List gethorseServiceTypeName() { return horseServiceTypeName; }
        public void sethorseServiceTypeName(List horseServiceTypeName) {this.horseServiceTypeName = horseServiceTypeName; }
        
        public String getMembershipTypeName() {return membershipTypeName; }
        public void setMembershipTypeName( String membershipTypeName){this.membershipTypeName = membershipTypeName; }
        
        public String getRiderMemberId() { return riderMemberId; }
        public void setRiderMemberId(String riderMemberId){this.riderMemberId = riderMemberId; }
        
	public String getAddOwnerName() {
		return addOwnerName;
	}
	public void setAddOwnerName(String addOwnerName) {
		this.addOwnerName = addOwnerName;
	}
	public String getAddRiderFirstName() {
		return addRiderFirstName;
	}
	public void setAddRiderFirstName(String addRiderFirstName) {
		this.addRiderFirstName = addRiderFirstName;
	}
	public String getAddRiderLastName() {
		return addRiderLastName;
	}
	public void setAddRiderLastName(String addRiderLastName) {
		this.addRiderLastName = addRiderLastName;
	}
	public String getAddRiderMemberId() {
		return addRiderMemberId;
	}
	public void setAddRiderMemberId(String addRiderMemberId) {
		this.addRiderMemberId = addRiderMemberId;
	}
	public String getAddRiderMiddleName() {
		return addRiderMiddleName;
	}
	public void setAddRiderMiddleName(String addRiderMiddleName) {
		this.addRiderMiddleName = addRiderMiddleName;
	}
	public String getAddRiderPrefix() {
		return addRiderPrefix;
	}
	public void setAddRiderPrefix(String addRiderPrefix) {
		this.addRiderPrefix = addRiderPrefix;
	}
	public String getAddRiderUserId() {
		return addRiderUserId;
	}
	public void setAddRiderUserId(String addRiderUserId) {
		this.addRiderUserId = addRiderUserId;
	}
	public String getBaPastName() {
		return baPastName;
	}
	public void setBaPastName(String baPastName) {
		this.baPastName = baPastName;
	}
	public String getBaRegisteredName() {
		return baRegisteredName;
	}
	public void setBaRegisteredName(String baRegisteredName) {
		this.baRegisteredName = baRegisteredName;
	}
	public String getCompetitionName() {
		return competitionName;
	}
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}
	public String getHorseAssignedGrade() {
		return horseAssignedGrade;
	}
	public void setHorseAssignedGrade(String horseAssignedGrade) {
		this.horseAssignedGrade = horseAssignedGrade;
	}
	public String getHorseAssignedPoints() {
		return horseAssignedPoints;
	}
	public void setHorseAssignedPoints(String horseAssignedPoints) {
		this.horseAssignedPoints = horseAssignedPoints;
	}
	public String getHorseBreed() {
		return horseBreed;
	}
	public void setHorseBreed(String horseBreed) {
		this.horseBreed = horseBreed;
	}
	public String getHorseColor() {
		return horseColor;
	}
	public void setHorseColor(String horseColor) {
		this.horseColor = horseColor;
	}
	public String getHorseCountry() {
		return horseCountry;
	}
	public void setHorseCountry(String horseCountry) {
		this.horseCountry = horseCountry;
	}
	public String getHorseDam() {
		return horseDam;
	}
	public void setHorseDam(String horseDam) {
		this.horseDam = horseDam;
	}
	public String getHorseDamBreed() {
		return horseDamBreed;
	}
	public void setHorseDamBreed(String horseDamBreed) {
		this.horseDamBreed = horseDamBreed;
	}
	public String getHorseForeignGrade() {
		return horseForeignGrade;
	}
	public void setHorseForeignGrade(String horseForeignGrade) {
		this.horseForeignGrade = horseForeignGrade;
	}
	public String getHorseForeignPoints() {
		return horseForeignPoints;
	}
	public void setHorseForeignPoints(String horseForeignPoints) {
		this.horseForeignPoints = horseForeignPoints;
	}
	public String getHorseGender() {
		return horseGender;
	}
	public void setHorseGender(String horseGender) {
		this.horseGender = horseGender;
	}
	public String getHorseHeight() {
		return horseHeight;
	}
	public void setHorseHeight(String horseHeight) {
		this.horseHeight = horseHeight;
	}
	public String getHorseImportDate() {
		return horseImportDate;
	}
	public void setHorseImportDate(String horseImportDate) {
		this.horseImportDate = horseImportDate;
	}
	public String getHorseImportedFrom() {
		return horseImportedFrom;
	}
	public void setHorseImportedFrom(String horseImportedFrom) {
		this.horseImportedFrom = horseImportedFrom;
	}
	public String getHorseMemberId() {
		return horseMemberId;
	}
	public void setHorseMemberId(String horseMemberId) {
		this.horseMemberId = horseMemberId;
	}
	public String getHorseSire() {
		return horseSire;
	}
	public void setHorseSire(String horseSire) {
		this.horseSire = horseSire;
	}
	public String getHorseSireBreed() {
		return horseSireBreed;
	}
	public void setHorseSireBreed(String horseSireBreed) {
		this.horseSireBreed = horseSireBreed;
	}
	public String getHorseYearFoaled() {
		return horseYearFoaled;
	}
	public void setHorseYearFoaled(String horseYearFoaled) {
		this.horseYearFoaled = horseYearFoaled;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOwnerAddress1() {
		return ownerAddress1;
	}
	public void setOwnerAddress1(String ownerAddress1) {
		this.ownerAddress1 = ownerAddress1;
	}
	public String getOwnerAddress2() {
		return ownerAddress2;
	}
	public void setOwnerAddress2(String ownerAddress2) {
		this.ownerAddress2 = ownerAddress2;
	}
	public String getOwnerCity() {
		return ownerCity;
	}
	public void setOwnerCity(String ownerCity) {
		this.ownerCity = ownerCity;
	}
	public String getOwnerCountry() {
		return ownerCountry;
	}
	public void setOwnerCountry(String ownerCountry) {
		this.ownerCountry = ownerCountry;
	}
	public String getOwnerEmailId() {
		return ownerEmailId;
	}
	public void setOwnerEmailId(String ownerEmailId) {
		this.ownerEmailId = ownerEmailId;
	}
	public String getOwnerFaxNo() {
		return ownerFaxNo;
	}
	public void setOwnerFaxNo(String ownerFaxNo) {
		this.ownerFaxNo = ownerFaxNo;
	}
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public String getOwnerMiddleName() {
		return ownerMiddleName;
	}
	public void setOwnerMiddleName(String ownerMiddleName) {
		this.ownerMiddleName = ownerMiddleName;
	}
	public String getOwnerMobileNo() {
		return ownerMobileNo;
	}
	public void setOwnerMobileNo(String ownerMobileNo) {
		this.ownerMobileNo = ownerMobileNo;
	}
	public String getOwnerPhoneNo() {
		return ownerPhoneNo;
	}
	public void setOwnerPhoneNo(String ownerPhoneNo) {
		this.ownerPhoneNo = ownerPhoneNo;
	}
	public String getOwnerPrefix() {
		return ownerPrefix;
	}
	public void setOwnerPrefix(String ownerPrefix) {
		this.ownerPrefix = ownerPrefix;
	}
	public String getOwnerState() {
		return ownerState;
	}
	public void setOwnerState(String ownerState) {
		this.ownerState = ownerState;
	}
	public String getOwnerSuite() {
		return ownerSuite;
	}
	public void setOwnerSuite(String ownerSuite) {
		this.ownerSuite = ownerSuite;
	}
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public String getOwnerZip() {
		return ownerZip;
	}
	public void setOwnerZip(String ownerZip) {
		this.ownerZip = ownerZip;
	}
	public String getPrevOwnerName() {
		return prevOwnerName;
	}
	public void setPrevOwnerName(String prevOwnerName) {
		this.prevOwnerName = prevOwnerName;
	}
	public String getPrevRiderFirstName() {
		return prevRiderFirstName;
	}
	public void setPrevRiderFirstName(String prevRiderFirstName) {
		this.prevRiderFirstName = prevRiderFirstName;
	}
	public String getPrevRiderLastName() {
		return prevRiderLastName;
	}
	public void setPrevRiderLastName(String prevRiderLastName) {
		this.prevRiderLastName = prevRiderLastName;
	}
	public String getPrevRiderMemberId() {
		return prevRiderMemberId;
	}
	public void setPrevRiderMemberId(String prevRiderMemberId) {
		this.prevRiderMemberId = prevRiderMemberId;
	}
	public String getPrevRiderMiddleName() {
		return prevRiderMiddleName;
	}
	public void setPrevRiderMiddleName(String prevRiderMiddleName) {
		this.prevRiderMiddleName = prevRiderMiddleName;
	}
	public String getPrevRiderPrefix() {
		return prevRiderPrefix;
	}
	public void setPrevRiderPrefix(String prevRiderPrefix) {
		this.prevRiderPrefix = prevRiderPrefix;
	}
	public String getPrevRiderUserId() {
		return prevRiderUserId;
	}
	public void setPrevRiderUserId(String prevRiderUserId) {
		this.prevRiderUserId = prevRiderUserId;
	}
	public String getRegisteredName() {
		return registeredName;
	}
	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}
	public String getRiderAddress1() {
		return riderAddress1;
	}
	public void setRiderAddress1(String riderAddress1) {
		this.riderAddress1 = riderAddress1;
	}
	public String getRiderAddress2() {
		return riderAddress2;
	}
	public void setRiderAddress2(String riderAddress2) {
		this.riderAddress2 = riderAddress2;
	}
	public String getRiderCity() {
		return riderCity;
	}
	public void setRiderCity(String riderCity) {
		this.riderCity = riderCity;
	}
	public String getRiderCountry() {
		return riderCountry;
	}
	public void setRiderCountry(String riderCountry) {
		this.riderCountry = riderCountry;
	}
	public String getRiderEmailId() {
		return riderEmailId;
	}
	public void setRiderEmailId(String riderEmailId) {
		this.riderEmailId = riderEmailId;
	}
	public String getRiderFaxNo() {
		return riderFaxNo;
	}
	public void setRiderFaxNo(String riderFaxNo) {
		this.riderFaxNo = riderFaxNo;
	}
	public String getRiderFirstName() {
		return riderFirstName;
	}
	public void setRiderFirstName(String riderFirstName) {
		this.riderFirstName = riderFirstName;
	}
	public String getRiderLastName() {
		return riderLastName;
	}
	public void setRiderLastName(String riderLastName) {
		this.riderLastName = riderLastName;
	}
	public String getRiderMiddleName() {
		return riderMiddleName;
	}
	public void setRiderMiddleName(String riderMiddleName) {
		this.riderMiddleName = riderMiddleName;
	}
	public String getRiderMobileNo() {
		return riderMobileNo;
	}
	public void setRiderMobileNo(String riderMobileNo) {
		this.riderMobileNo = riderMobileNo;
	}
	public String getRiderPhoneNo() {
		return riderPhoneNo;
	}
	public void setRiderPhoneNo(String riderPhoneNo) {
		this.riderPhoneNo = riderPhoneNo;
	}
	public String getRiderPrefix() {
		return riderPrefix;
	}
	public void setRiderPrefix(String riderPrefix) {
		this.riderPrefix = riderPrefix;
	}
	public String getRiderState() {
		return riderState;
	}
	public void setRiderState(String riderState) {
		this.riderState = riderState;
	}
	public String getRiderSuite() {
		return riderSuite;
	}
	public void setRiderSuite(String riderSuite) {
		this.riderSuite = riderSuite;
	}
	public String getRiderUserId() {
		return riderUserId;
	}
	public void setRiderUserId(String riderUserId) {
		this.riderUserId = riderUserId;
	}
	public String getRiderZip() {
		return riderZip;
	}
	public void setRiderZip(String riderZip) {
		this.riderZip = riderZip;
	}

        
        
         public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append("\n memberId :"+ memberId)
	.append("\n horseMemberId :"+ horseMemberId)
	.append("\n competitionName :"+ competitionName)
	.append("\n registeredName :"+ registeredName)
	.append("\n baRegisteredName :"+ baRegisteredName)
	.append("\n baPastName :"+ baPastName)
	.append("\n membershipTypeName :"+ membershipTypeName) 
        
	.append("\n riderPrefix :"+ riderPrefix) 
	.append("\n riderFirstName :"+ riderFirstName) 
	.append("\n riderMiddleName :"+ riderMiddleName) 
	.append("\n riderLastName :"+ riderLastName) 
	.append("\n riderSuite :"+ riderSuite)
	.append("\n riderAddress1 :"+ riderAddress1)
	.append("\n riderAddress2 :"+ riderAddress2) 
	.append("\n riderCity :"+ riderCity)
	.append("\n riderState :"+ riderState) 
	.append("\n riderZip :"+ riderZip)
	.append("\n riderPhoneNo :"+ riderPhoneNo)
	.append("\n riderMobileNo :"+ riderMobileNo)
	.append("\n riderEmailId :"+ riderEmailId)
	.append("\n riderUserId :"+ riderUserId)
	.append("\n riderFaxNo :"+ riderFaxNo)
	.append("\n riderCountry :"+ riderCountry)

	.append("\n prevRiderUserId :"+ prevRiderUserId)
	.append("\n prevRiderPrefix :"+ prevRiderPrefix)
	.append("\n prevRiderFirstName :"+ prevRiderFirstName)
	.append("\n prevRiderMiddleName :"+ prevRiderMiddleName)
	.append("\n prevRiderLastName :"+ prevRiderLastName)
	.append("\n prevRiderMemberId :"+ prevRiderMemberId)
	
	.append("\n addRiderUserId :"+ addRiderUserId)
	.append("\n addRiderPrefix :"+ addRiderPrefix)
	.append("\n addRiderFirstName :"+ addRiderFirstName)
	.append("\n addRiderMiddleName :"+ addRiderMiddleName)
	.append("\n addRiderLastName :"+ addRiderLastName)
	.append("\n addRiderMemberId :"+ addRiderMemberId)
	
	.append("\n ownerPrefix :"+ ownerPrefix) 
	.append("\n ownerFirstName :"+ ownerFirstName) 
	.append("\n ownerMiddleName :"+ ownerMiddleName) 
	.append("\n ownerLastName :"+ ownerLastName) 
	.append("\n ownerSuite :"+ ownerSuite)
	.append("\n ownerAddress1 :"+ ownerAddress1)
	.append("\n ownerAddress2 :"+ ownerAddress2) 
	.append("\n ownerCity :"+ ownerCity)
	.append("\n ownerState :"+ ownerState) 
	.append("\n ownerZip :"+ ownerZip)
	.append("\n ownerPhoneNo :"+ ownerPhoneNo)
	.append("\n ownerMobileNo :"+ ownerMobileNo)
	.append("\n ownerEmailId :"+ ownerEmailId)
	.append("\n ownerUserId :"+ ownerUserId)
	.append("\n ownerFaxNo :"+ ownerFaxNo)
	.append("\n ownerCountry :"+ ownerCountry)
	.append("\n addOwnerName :"+ addOwnerName)
	.append("\n prevOwnerName :"+ prevOwnerName)
	
	.append("\n horseColor :"+ horseColor) 
	.append("\n horseGender :"+ horseGender) 
	.append("\n horseHeight :"+ horseHeight) 
	.append("\n horseYearFoaled :"+ horseYearFoaled)
	.append("\n horseBreed :"+ horseBreed) 
	.append("\n horseCountry :"+ horseCountry) 
	.append("\n horseSire :"+ horseSire)
	.append("\n horseSireBreed :"+ horseSireBreed) 
	.append("\n horseDam :"+ horseDam) 
	.append("\n horseDamBreed :"+ horseDamBreed) 
	.append("\n horseImportedFrom :"+ horseImportedFrom)      	
	.append("\n horseImportDate :"+ horseImportDate) 
	.append("\n horseForeignGrade :"+ horseForeignGrade) 
	.append("\n horseForeignPoints :"+ horseForeignPoints) 
	.append("\n horseAssignedGrade :"+ horseAssignedGrade) 
	.append("\n horseAssignedPoints :"+ horseAssignedPoints);
	
	
    return buffer.toString();
  }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getStatusName() {
        return statusName;
    }
	
	
}