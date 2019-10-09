/*
 * UserMaster.java
 *
 * Created on August 24, 2006, 1:02 PM
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
public class HLCUserMaster implements Serializable {
    public String  userId;
    public Boolean activeStatus;
    public String loginName;
    public String membershipTypeId;
    public String userTypeId;
    public String userCode;
    public String contactTypeId;
    public String contactType;
    public String prefix;
    public String firstName;
    public String middleName;
    public String lastName;
    public String sufix;
    public String dob;
    public String gender;
    public String emailId;
    public String password;
    public String secretQuestion;
    public String secretAnswer;
    public String adminString;
    public String userTypeName;
    public String employeeId;
    public boolean nonUseaMailingStatus;
    public boolean nonUseaEmailStatus;
   // public Date registerDate;
   // public Date loginDate;
    
    private String phoneNo;

    
    
    public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

    public void setNonUseaMailingStatus(boolean nonUseaMailingStatus) {
        this.nonUseaMailingStatus = nonUseaMailingStatus;
    }

    public void setNonUseaEmailStatus(boolean nonUseaEmailStatus) {
        this.nonUseaEmailStatus = nonUseaEmailStatus;
    }

    public boolean isNonUseaMailingStatus() {
        return nonUseaMailingStatus;
    }

    public boolean isNonUseaEmailStatus() {
        return nonUseaEmailStatus;
    }
    private String mobileNo;
    private String faxNo;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zip;
   
    
    /** Creates a new instance of UserMaster */
    public HLCUserMaster() {
    }
    
    public HLCUserMaster(Boolean activeStatus,String  userId,String userTypeName, String adminString,String membershipTypeId,String userTypeId,String userCode,String contactTypeId, String contactType,String prefix,String firstName,String middleName,
            String lastName,String sufix,String dob,String gender,String emailId, String password, String secretQuestion, 
            String secretAnswer,String loginName,boolean nonUseaMailingStatus, boolean nonUseaEmailStatus ){
         this.userTypeName = userTypeName;
        this.membershipTypeId = membershipTypeId;
        this.adminString = adminString;
        this.userTypeId=userTypeId;
        this.contactTypeId = contactTypeId;
        this.userCode = userCode;
        this.contactType = contactType;
        this.prefix=prefix;
        this.firstName=firstName;
        this.middleName=middleName;
        this.lastName=lastName;
        this.sufix=sufix;
        this.dob=dob;
        this.gender=gender;
        this.emailId=emailId;
        this.password=password;
        this.secretQuestion=secretQuestion;
        this.secretAnswer=secretAnswer;
        this.userId = userId;
        this.activeStatus = activeStatus;
        //this.registerDate=registerDate;
        this.loginName=loginName;
        this.nonUseaEmailStatus = nonUseaEmailStatus;
        this.nonUseaMailingStatus = nonUseaMailingStatus;
    }
    //getter method
    
    public Boolean getActiveStatus() {return activeStatus; }
    public String getUserId() {return userId; }
    public String getUserTypeName() {return userTypeName; }
    public String getAdminString(){return adminString ;}
    public String getMemeberTypeId(){ return membershipTypeId; }
    public String getUserTypeId(){return  userTypeId;}
    public String getUserCode(){return userCode; }
    public String getContactTypeId(){return contactTypeId ;}
    public String getCommunicationAddress(){return contactType; }
    public String getPrefix(){return prefix ;}
    public String getFirstName(){return  firstName;}
    public String getMiddleName(){return middleName ;}
    public String getLastName(){return lastName ;}
    public String getSufix(){return  sufix;}
    public String getDob(){return dob ;}
    public String getGender(){return gender ;}
    public String getEmailId(){return  emailId;}
    public String getPassword(){return password ;}
    public String getSecretQuestion(){return secretQuestion ;}
    public String getSecretAnswer(){return  secretAnswer;}
   // public Date getRegisterDate(){return registerDate ;}
   // public Date getloginDate(){return loginDate ;}
    
    //setter method
     public void setActiveStatus(Boolean activeStatus) {this.activeStatus = activeStatus; }
    public void setUserId(String userId) {this.userId = userId; }
    public void setUserTypeName(String userTypeName) {this.userTypeName = userTypeName; }
    public void setAdminString(String adminString){this.adminString=adminString;}
    public void setMemeberTypeId(String membershipTypeId){this.membershipTypeId = membershipTypeId; }
    public void setUserTypeId(String userTypeId){this.userTypeId=userTypeId;}
    public void setUserCode(String userCode){this.userCode = userCode; }
    public void setContactTypeId(String contactTypeId){this.contactTypeId=contactTypeId;}
    public void setCommunicationAddress(String contactType){this.contactType = contactType; }
    public void setPrefix(String prefix){this.prefix=prefix;}
    public void setFirstName(String firstName){this.firstName=firstName;}
    public void setMiddleName(String middleName){this.middleName=middleName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public void setSufix(String sufix){this.sufix=sufix;}
    public void setDob(String dob){this.dob=dob;}
    public void setGender(String gender){this.gender=gender;}
    public void setEmailId(String emailId){this.emailId=emailId;}
    public void setPassword(String password){this.password=password;}
    public void setSecretQuestion(String secretQuestion){this.secretQuestion=secretQuestion;}
    public void setSecretAnswer(String secretAnswer){this.secretAnswer=secretAnswer;}
   // public void setRegisterDate(Date registerDate){this.registerDate=registerDate;}
   // public void setLoginDate(Date loginDate){this.loginDate=loginDate;}
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" membershipTypeId :"+ membershipTypeId+"\n")
	.append(" userId :"+ userId+"\n")
	.append(" userTypeId :"+ userTypeId+"\n")
	.append(" userCode :"+userCode+"\n")
	.append(" contactTypeId :"+contactTypeId+"\n")
	.append(" contactType :"+contactType+"\t\t")
        .append(" prefix :"+prefix+"\n")
	.append(" firstName :"+firstName+"\n")
        .append(" middleName :"+middleName+"\n")
	.append(" lastName :"+lastName+"\n")
	.append(" sufix :"+ sufix+"\n")
	.append(" dob :"+ dob+"\n")
	.append(" gender :"+gender+"\n")
	.append(" emailId :"+emailId+"\n")
	.append(" password :"+ password+"\n")
	.append(" secretQuestion :"+ secretQuestion+"\n")
	.append(" secretAnswer :"+secretAnswer+"\n")
	.append(" adminString :"+adminString+"\n")
        .append(" loginName :"+loginName+"\n")
	.append(" userTypeName :"+ userTypeName+"\n");
	
    return buffer.toString();
  }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    
}
