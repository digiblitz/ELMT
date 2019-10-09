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
/*
 * InstructorDetails.java
 *
 * Created on September 1, 2006, 6:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

/**
 *
 * @author harmohan
 */


public class HLCInstructorDetails implements  java.io.Serializable {

	private String  icpMeetingId;
	private String  assesmentLevel;
	private String  assesmentDate;
        private String  noOfDays;
	private String  useaAreaId;
        private String  areaName;
	private String  location;
	private String  instructorId;
	private String  hostMemberId;
	private String  shippingTypeId;
	private String  assessor;
	private String  facilities;
	private String  landOwnerName;
            private String landOwnerBusinessName;
	private String  landOwnerAddress;
	private String  landOwnerCity;
	private String  landOwnerState;
        private String  landOwnerCountry;
	private String  landOwnerZip;
	private String  landOwnerPhone;
            private String landOwnerFax;
            private String landOwnerEmail;
	private String  addDate;
	private String  approvedBy;
	private String  approvedDate;
	private String  postingType;
	private String  requestStatus;
        private String comments;
        private String email;
        private String paymentId;

	public  HLCInstructorDetails() { }

	public  HLCInstructorDetails (String paymentId,String email,String comments,String noOfDays,String landOwnerCountry,String  icpMeetingId, String assesmentLevel,String assesmentDate,String areaName,
	        String useaAreaId, String location, String instructorId, String hostMemberId, String  shippingTypeId,
	         String  assessor, String  facilities, String landOwnerName,String landOwnerBusinessName, String landOwnerAddress, String landOwnerCity,
	         String landOwnerState, String landOwnerZip, String landOwnerPhone,String landOwnerFax,String landOwnerEmail, String addDate, String approvedBy, String approvedDate, String postingType, String requestStatus) 	{
		 this.icpMeetingId = icpMeetingId ;
                 this.email = email;
		 this.assesmentLevel = assesmentLevel;
		 this.assesmentDate = assesmentDate;
                 this.landOwnerBusinessName = landOwnerBusinessName;
                 this.landOwnerFax = landOwnerFax;
                 this.landOwnerEmail = landOwnerEmail;
                 this.noOfDays = noOfDays;
                 this.landOwnerCountry = landOwnerCountry;
                 
		 this.useaAreaId = useaAreaId;
                 this.areaName = areaName;
		 this.location = location;
		 this.instructorId = instructorId;
		 this.hostMemberId = hostMemberId;
		 this.shippingTypeId = shippingTypeId;
		 this.assessor = assessor;
		 this.facilities = facilities;
		 this.landOwnerName = landOwnerName;
		 this.landOwnerAddress = landOwnerAddress;
		 this.landOwnerCity = landOwnerCity;
		 this.landOwnerState = landOwnerState;
                 this.landOwnerCountry =landOwnerCountry;
		 this.landOwnerZip = landOwnerZip;
		 this.landOwnerPhone = landOwnerPhone;
		 this.addDate = addDate;
		 this.approvedBy = approvedBy;
		 this.approvedDate = approvedDate;
		 this.postingType = postingType;
		 this.requestStatus = requestStatus;
                 this.comments = comments;
                 this.paymentId = paymentId;
    }

//getteremail
    public String getEmail(){return email; }
    public String getComments(){return comments; }
    public String getLandOwnerCountry(){return landOwnerCountry; }
    public String getNoOfDays() { return noOfDays; }
    public String getIcpMeetingId() { return icpMeetingId; }
    public String getAssesmentLevel() { return assesmentLevel; }
    public String getAssesmentDate() {return assesmentDate; }
    public String getUseaAreaId() {return useaAreaId; }
    public String getAreaName() {return areaName; }
    public String getLocation() {return location;}
    public String getInstructorId() { return instructorId; }
    public String getHostMemberId() {return hostMemberId;}
    public String getShippingTypeId() {return shippingTypeId; }
    public String getAssessor() {return assessor;}
    public String getFacilities() {return facilities; }
    public String getLandOwnerName() {return landOwnerName; }
    public String getLandOwnerBusinessName() {return landOwnerBusinessName; }
    public String getLandOwnerFax(){return landOwnerFax; }
    public String getLandOwnerEmail() {return landOwnerEmail; }
    public String getLandOwnerAddress() {return landOwnerAddress; }
    public String getLandOwnerCity() {return landOwnerCity; }
    public String getLandOwnerState() {return landOwnerState; }
    public String getLandOwnerZip() {return landOwnerZip; }
    public String getLandOwnerPhone() {return landOwnerPhone; }
    public String getAddDate() {return addDate;}
    public String getApprovedBy() {return approvedBy; }
    public String getApprovedDate() { return approvedDate; }
    public String getPostingType() { return postingType; }
    public String getRequestStatus() {return requestStatus;}
    public String getPaymentId() { return paymentId; }
    
    
    //Setters methods email
    public void setEmail(String email){this.email = email; }
    public void setComments(String comments){this.comments = comments; }
    public void setLandOwnerCountry(String landOwnerCountry){this.landOwnerCountry = landOwnerCountry; }
    public void setNoOfDays(String noOfDays){this.noOfDays = noOfDays; }
    public void setIcpMeetingId(String icpMeetingId) {this.icpMeetingId = icpMeetingId; }
    public void setAssesmentLevel(String assesmentLevel)  { this.assesmentLevel = assesmentLevel; }
    public void setAssesmentDate(String assesmentDate) {this.assesmentDate =  assesmentDate; }
    public void setUseaAreaId(String useaAreaId) {this.useaAreaId =  useaAreaId; }
    public void setAreaName(String areaName) {this.areaName =  areaName; }
    public void setLocation(String location) {this.location =  location; }
    public void setInstructorId(String instructorId) {this.instructorId = instructorId;}
    public void setHostMemberId(String hostMemberId) {this.hostMemberId = hostMemberId; }
    public void setShippingTypeId(String shippingTypeId) {this.shippingTypeId = shippingTypeId; }
    public void setAssessor(String assessor) {this.assessor = assessor;}
    public void setFacilities(String facilities) {this.facilities = facilities;}
    public void setLandOwnerName(String landOwnerName) {this.landOwnerName = landOwnerName; }
    public void setLandOwnerBusinessName(String landOwnerBusinessName) {this.landOwnerBusinessName = landOwnerBusinessName; }
    public void setLandOwnerFax(String landOwnerFax){this.landOwnerFax = landOwnerFax; }
    public void setLandOwnerEmail(String landOwnerEmail) {this.landOwnerEmail = landOwnerEmail; }
    public void setLandOwnerAddress(String landOwnerAddress) { this.landOwnerAddress = landOwnerAddress; }
    public void setLandOwnerCity(String landOwnerCity) { this.landOwnerCity = landOwnerCity; }
    public void setLandOwnerState(String landOwnerState) {this.landOwnerState = landOwnerState;}
    public void setLandOwnerZip(String landOwnerZip) {this.landOwnerZip = landOwnerZip; }
    public void setLandOwnerPhone(String landOwnerPhone) { this.landOwnerPhone = landOwnerPhone; }
    public void setAddDate(String addDate) { this.addDate = addDate; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy;}
    public void setApprovedDate(String approvedDate) {this.approvedDate = approvedDate; }
    public void setPostingType(String postingType) {this.postingType = postingType; }
    public void setRequestStatus(String requestStatus) {this.requestStatus = requestStatus; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

}
