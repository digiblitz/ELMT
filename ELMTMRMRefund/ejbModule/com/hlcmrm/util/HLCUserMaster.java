/*
 * UserMaster.java
 *
 * Created on August 24, 2006, 1:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author harmohan
 */
public class HLCUserMaster implements Serializable {
   // user_id
   // public String userCode;
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
   // public Date registerDate;
   // public Date loginDate;
   
    
    /** Creates a new instance of UserMaster */
    public HLCUserMaster() {
    }
    
    public HLCUserMaster(String userTypeName, String adminString,String membershipTypeId,String userTypeId,String userCode,String contactTypeId, String contactType,String prefix,String firstName,String middleName,
            String lastName,String sufix,String dob,String gender,String emailId, String password, String secretQuestion, 
            String secretAnswer){
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
        //this.registerDate=registerDate;
        //this.loginDate=loginDate;
    }
    //getter method
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
    
}
