/*
 * MemberDetails.java
 *
 * Created on August 24, 2006, 12:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcform.util;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author harmohan
 */
public class HLCMemberDetails implements Serializable {
    
    private String totalRecord;
    private List familyAddOn;
    private String memberId;
    private String userId;
    private String membershipTypeId;
    private String membershipTypeName;
    private String nonuseaOrgId;
    private String nonuseaOrgName;
    private String emailId;
    private String nonuseaOrgMemberId;
    private String countryMailTypeId;
    private String countryMailTypeName;
    private String familyAddOns;
    private String parentMemberId;
    private String endowmentTrustAmount;
    private Boolean activeStatus;
    private Date addDate;
    private String statusId;	
    private String statusName;
    private Date expiryDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private String loginName;
    private String areaMembTypId;
    private String areaProgName;
    private String areaProgYear;
    private String areaProgDesc;
    private Date activationDate;
    
    private String usefAmateur;
    private boolean renewalStatus;
    private String paymentId;
    private String amateurName;
    private boolean amateurDec1;
    private boolean amateurDec2;
    private String armbandQty;
    
    private String araeName;
    private String subExpDate;
    private Date subscribingExDate;
        
    /** Creates a new instance of MemberDetails */
    public HLCMemberDetails() {
    }

  
    public HLCMemberDetails(String armbandQty,String memberId,String emailId,String userId,String membershipTypeId,String nonuseaOrgId,String nonuseaOrgMemberId,
            String countryMailTypeId,String familyAddOns,String parentMemberId,String endowmentTrustAmount, Date expiryDate,String loginName, String usefAmateur){

        this.memberId=memberId;
        this.userId=userId;
        this.membershipTypeId=membershipTypeId;
        this.emailId = emailId;
        this.nonuseaOrgId=nonuseaOrgId;
        this.nonuseaOrgMemberId=nonuseaOrgMemberId;
        this.countryMailTypeId=countryMailTypeId;
        this.familyAddOns=familyAddOns;
        this.parentMemberId=parentMemberId;
        this.endowmentTrustAmount=endowmentTrustAmount;
       // this.addDate=addDate;
        this.statusId=statusId;
        this.expiryDate=expiryDate;
        this.expiryDate=expiryDate;
        this.loginName=loginName;
        this.usefAmateur = usefAmateur;
        this.armbandQty = armbandQty;
        this.subExpDate=subExpDate;
        this.subscribingExDate=subscribingExDate;
    }

    //statusName
    public String getStatusName(){return statusName;}
    public String getCountryMailTypeName(){return countryMailTypeName;}
    public String getNonuseaOrgName(){return nonuseaOrgName; }
    public String getMembershipTypeName(){ return membershipTypeName;}
    public String getMemberId(){return memberId ;}
    public String getUserId(){return  userId;}
    public String getEmailId(){return emailId; }
    public String getMembershipTypeId(){return membershipTypeId ;}
    public String getNonuseaOrgId(){return nonuseaOrgId ;}
    public String getNonUseaOrgMemberId(){return  nonuseaOrgMemberId;}
    public String getCountryMailTypeId(){return countryMailTypeId ;}
    public String getFamilyAddOns(){return familyAddOns ;}
    public String getParentMemberId(){return  parentMemberId;}
    public String getEndowmentTrustAmount(){return endowmentTrustAmount ;}
   // public Boolean getActiveStatus(){return activeStatus ;}
   // public Date getAddDate(){return  addDate;}
    public String getStatusId(){return statusId ;}
    public Date getExpiryDate(){return expiryDate ;}
    public List getFamilyAddOn() { return familyAddOn; }
    public String getTotalRecord(){ return totalRecord; }
    public String getFirstName() { return firstName;}
    public String getLastName() { return lastName; }
    public String getMiddleName() { return middleName;}
   

//statusName
    public void setTotalRecord(String totalRecord){this.totalRecord = totalRecord;}
    public void setFamilyAddOn(List familyAddOn) {this.familyAddOn = familyAddOn; }
    public void setStatusName(String statusName){this.statusName = statusName;}
    public void setCountryMailTypeName(String countryMailTypeName){this.countryMailTypeName = countryMailTypeName;}
    public void setNonuseaOrgName(String nonuseaOrgName){this.nonuseaOrgName = nonuseaOrgName;}
    public void setMembershipTypeName(String membershipTypeName){this.membershipTypeName = membershipTypeName;}
    public void setMemberId(String memberId){this.memberId=memberId;}
    public void setUserId(String userId){this.userId=userId;}
    public void setEmailId(String emailId){this.emailId = emailId; }
    public void setMembershipTypeId(String membershipTypeId){this.membershipTypeId=membershipTypeId;}
    public void setNonuseaOrgId(String nonuseaOrgId){this.nonuseaOrgId=nonuseaOrgId;}
    public void setNonUseaOrgMemberId(String nonuseaOrgMemberId){this.nonuseaOrgMemberId=nonuseaOrgMemberId;}
    public void setCountryMailTypeId(String countryMailTypeId){this.countryMailTypeId=countryMailTypeId;}
    public void setFamilyAddOns(String familyAddOns){this.familyAddOns=familyAddOns;}
    public void setParentMemberId(String parentMemberId){this.parentMemberId=parentMemberId;}
    public void setEndowmentTrustAmount(String endowmentTrustAmount){this.endowmentTrustAmount=endowmentTrustAmount;}
   // public void setAddDate(Date addDate){this.addDate=addDate;}
    public void setStatusId(String statusId){this.statusId=statusId;}
    public void setExpiryDate(Date expiryDate){this.expiryDate=expiryDate;}
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
     public String getArmbandQty() {
        return armbandQty;
    }

    public void setArmbandQty(String armbandQty) {
        this.armbandQty = armbandQty;
    }

   
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setUsefAmateur(String usefAmateur) {
        this.usefAmateur = usefAmateur;
    }

    public String getUsefAmateur() {
        return usefAmateur;
    }

    public void setRenewalStatus(boolean renewalStatus) {
        this.renewalStatus = renewalStatus;
    }

    public boolean isRenewalStatus() {
        return renewalStatus;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setAmateurName(String amateurName) {
        this.amateurName = amateurName;
    }

    public void setAmateurDec1(boolean amateurDec1) {
        this.amateurDec1 = amateurDec1;
    }

    public void setAmateurDec2(boolean amateurDec2) {
        this.amateurDec2 = amateurDec2;
    }

    public String getAmateurName() {
        return amateurName;
    }

    public boolean isAmateurDec1() {
        return amateurDec1;
    }

    public boolean isAmateurDec2() {
        return amateurDec2;
    }

   
    public String getAraeName() {
        return araeName;
    }

    public void setAraeName(String araeName) {
        this.araeName = araeName;
    }
    public String getAreaMembTypId() {
        return areaMembTypId;
    }
    public void setAreaMembTypId(String areaMembTypId) {
        this.areaMembTypId = areaMembTypId;
    }
    public void setAreaProgName(String areaProgName) {
        this.areaProgName = areaProgName;
    }

    public String getAreaProgName() {
        return areaProgName;
    }
     public String getAreaProgYear() {
        return areaProgYear;
    }

    public void setAreaProgYear(String areaProgYear) {
        this.areaProgYear = areaProgYear;
    }

    public void setAreaProgDesc(String areaProgDesc) {
        this.areaProgDesc = areaProgDesc;
    }

    public String getAreaProgDesc() {
        return areaProgDesc;
    }
    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" memberId :"+ memberId+"\n")
	.append(" userId :"+ userId+"\n")
        .append(" firstName :"+ firstName+"\n")
        .append(" middleName :"+ middleName+"\n")
        .append(" lastName :"+ lastName+"\n")
	.append(" membershipTypeId :"+ membershipTypeId+"\n")
        .append(" membershipTypeName :"+ membershipTypeName+"\n")
        .append(" familyAddOn :"+ familyAddOn+"\n")
        .append(" Total Number Of record :"+ totalRecord+"\n")
        .append(" statusName :"+ statusName+"\n")
	.append(" nonuseaOrgId :"+nonuseaOrgId+"\n")
        .append(" nonuseaOrgName :"+nonuseaOrgName+"\n")
	.append(" emailId :"+emailId+"\n")
	.append(" countryMailTypeId :"+countryMailTypeId+"\n")
        .append(" countryMailTypeName :"+countryMailTypeName+"\n")
        .append(" familyAddOns :"+familyAddOns+"\n")
	.append(" parentMemberId :"+parentMemberId+"\n")
	.append(" endowmentTrustAmount :"+ endowmentTrustAmount+"\n")
	.append(" activeStatus :"+ activeStatus+"\n")
	.append(" addDate :"+addDate+"\n")
	.append(" statusId :"+statusId+"\n")
	.append(" expiryDate :"+ expiryDate+"\n")
        .append(" usefAmateur :"+ usefAmateur+"\n")
        .append(" paymentId :"+ paymentId+"\n")
        .append(" loginName :"+ loginName+"\n")
	.append(" subExpDate :"+ subExpDate+"\n")
        .append(" subscribingExDate :"+ subscribingExDate+"\n")
        .append(" armbandQty :"+ armbandQty+"\n");
    return buffer.toString();
  }

   
    public String getSubExpDate() {
        return subExpDate;
    }

    public void setSubExpDate(String subExpDate) {
        this.subExpDate = subExpDate;
    }
   
    public Date getSubscribingExDate() {
        return subscribingExDate;
    }

    public void setSubscribingExDate(Date subscribingExDate) {
        this.subscribingExDate = subscribingExDate;
    }
    }

   
    


