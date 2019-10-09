/*
 * MsgContactListMaster.java
 *
 * Created on September 2, 2006, 2:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;

/**
 *
 * @author harmohan
 */

import java.io.*;
import java.util.*;

public class HLCMsgContactListMaster implements Serializable {
    
    private String contactlistId;
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    private String emailId;
    private String alternateEmailId;
    private String phoneNo;
    private String mobileNo;
    private String faxNo;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String activeStatus;
    private String registrationEmailId;

public HLCMsgContactListMaster() { }
public HLCMsgContactListMaster(String contactlistId,String userId,String firstName,String middleName,String lastName,String nickName,
     String emailId,String alternateEmailId,String phoneNo,String mobileNo,String faxNo,String street,String city,String state,
        String country,String zip,String activeStatus,String registrationEmailId)
{
    this.contactlistId = contactlistId;
    this.userId =userId ;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName= lastName;
    this.nickName = nickName;
    this.emailId = emailId;
    this.alternateEmailId = alternateEmailId ;
    this.phoneNo = phoneNo;
    this.mobileNo = mobileNo;
    this.faxNo = faxNo;
    this.street =street ;
    this.city = city;
    this.state = state;
    this.country =country ;
    this.zip = zip;
    this.activeStatus = activeStatus;
    this.registrationEmailId = registrationEmailId;
}
    //Getter Method
    public String getContactlistId(){ return  contactlistId; }
    public String getUserId(){ return userId; }
    public String getFirstName(){ return firstName; }
    public String getMiddleName(){ return middleName; }
    public String getLastName(){ return lastName;}
    public String getNickName(){return nickName; }
    public String getEmailId(){return emailId; }
    public String getAlternateEmailId(){return alternateEmailId; }
    public String getPhoneNo(){ return phoneNo; }
    public String getMobileNo(){ return mobileNo; }
    public String getFaxNo(){ return faxNo; }
    public String getStreet(){ return street; }
    public String getCity(){return city; }
    public String getState(){ return state; }
    public String getCountry(){  return country; }
    public String getZip(){ return zip; }
    public String getActiveStatus(){ return activeStatus; }
    public String getRegistrationEmailId(){ return registrationEmailId; }
    
    //Setter Method
    public void setContactlistId(String contactlistId){ this.contactlistId = contactlistId; }
    public void setUserId(String userId){ this.userId =userId ; }
    public void setFirstName(String firstName){this.firstName = firstName; }
    public void setMiddleName(String middleName){ this.middleName = middleName; }
    public void setLastName(String lastName){ this.lastName= lastName; }
    public void setNickName(String nickName){this.nickName = nickName; }
    public void setEmailId(String emailId){ this.emailId = emailId; }
    public void setAlternateEmailId(String alternateEmailId){ this.alternateEmailId = alternateEmailId ; }
    public void setPhoneNo(String phoneNo){this.phoneNo = phoneNo; }
    public void setMobileNo(String mobileNo){ this.mobileNo = mobileNo; }
    public void setFaxNo(String faxNo){ this.faxNo = faxNo; }
    public void setStreet(String street){ this.street =street ; }
    public void setCity(String city){ this.city = city; }
    public void setState(String state){  this.state = state; }
    public void setCountry(String country){ this.country =country ; }
    public void setZip(String zip){ this.zip = zip; }
    public void setActiveStatus(String activeStatus){ this.activeStatus = activeStatus; }
    public void setRegistrationEmailId(String registrationEmailId){ this.registrationEmailId = registrationEmailId; }
    
    public String toString(){

    StringBuffer buffer = new StringBuffer()
   	.append(" contactlistId :"+ contactlistId+"\n")
	.append(" userId :"+ userId+"\n")
	.append(" firstName :"+ firstName+"\n")
	.append(" middleName :"+middleName+"\n")
	.append(" lastName :"+lastName+"\n")
	.append(" nickName :"+nickName+"\t\t")
        .append(" emailId :"+emailId+"\n")
	.append(" alternateEmailId :"+alternateEmailId+"\n")
        .append(" phoneNo :"+phoneNo+"\n")
	.append(" mobileNo :"+mobileNo+"\n")
	.append(" faxNo :"+faxNo+"\n")
        .append(" street :"+street+"\n")
	.append(" city :"+city+"\n")
        .append(" state :"+state+"\n")
	.append(" country :"+country+"\n")
	.append(" zip :"+zip+"\n")
        .append(" activeStatus :"+activeStatus+"\n")
        .append(" registrationEmailId :"+registrationEmailId+"\n");
	
    return buffer.toString();
  }

    }