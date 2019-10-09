/*
 * AnnualUserVO.java
 *
 * Created on October 31, 2006, 5:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author manas
 */
public class HLCAnnualUserVO implements java.io.Serializable{
    /** Creates a new instance of AnnualUserVO */
    public HLCAnnualUserVO() {
        
    }
    
    private String  userId;
    private String memberId;
    private String membershipTypeId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String emailId;
    private String phoneNo;
    private String mobileNo;
    private String faxNo;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zip;
    
    private String badgeInfo;
    private String registerId;
    private String daysApplied;
    private String areaIV;
    private String yEndLauncheon;
    private String fameDinner;
    private boolean poniClubStatus;
    private String poniClubMemberId;
    private String clubName;
    private boolean accomFaciStaus;
    private String accomodation;
    private String memberTypeName;
    private double regAmount;
    private double otherActAmount;
    private double amount;
    
    private ArrayList priceList;
    
    
    public String getUserId() {
        return userId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMembershipTypeId() {
        return membershipTypeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getFaxNo() {
        return faxNo;
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

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }
    
    public String getBadgeInfo(){
        return badgeInfo;
    }

    public String getRegisterId() {
        return registerId;
    }

    public String getAreaIV() {
        return areaIV;
    }

    public String getYEndLauncheon() {
        return yEndLauncheon;
    }

    public String getFameDinner() {
        return fameDinner;
    }

    public String getPoniClubMemberId() {
        return poniClubMemberId;
    }
    
     public String getClubName() {
        return clubName;
    }

    public String getAccomodation() {
        return accomodation;
    }


    public double getAmount() {
        return amount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMembershipTypeId(String membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setBadgeInfo(String badgeInfo) {
        this.badgeInfo = badgeInfo;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public void setAreaIV(String areaIV) {
        this.areaIV = areaIV;
    }

    public void setYEndLauncheon(String yEndLauncheon) {
        this.yEndLauncheon = yEndLauncheon;
    }

    public void setFameDinner(String fameDinner) {
        this.fameDinner = fameDinner;
    }

    public void setPoniClubMemberId(String poniClubMemberId) {
        this.poniClubMemberId = poniClubMemberId;
    }
    
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }


    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPoniClubStatus(boolean poniClubStatus) {
        this.poniClubStatus = poniClubStatus;
    }

    public void setAccomFaciStaus(boolean accomFaciStaus) {
        this.accomFaciStaus = accomFaciStaus;
    }

    public boolean isPoniClubStatus() {
        return poniClubStatus;
    }

    public boolean isAccomFaciStaus() {
        return accomFaciStaus;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setDaysApplied(String daysApplied) {
        this.daysApplied = daysApplied;
    }

    public String getDaysApplied() {
        return daysApplied;
    }

    public void setRegAmount(double regAmount) {
        this.regAmount = regAmount;
    }

    public void setOtherActAmount(double otherActAmount) {
        this.otherActAmount = otherActAmount;
    }

    public double getRegAmount() {
        return regAmount;
    }

    public double getOtherActAmount() {
        return otherActAmount;
    }

    public void setPriceList(ArrayList priceList) {
        this.priceList = priceList;
    }

    public ArrayList getPriceList() {
        return priceList;
    }
}
