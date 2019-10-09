/*
 * MemberVO.java
 *
 * Created on November 12, 2007, 2:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Vidhya
 */
public class HLCMemberVO {
    
    /** Creates a new instance of MemberVO */
    public HLCMemberVO() {
    }
       private String memberName;
        private String memberId;
        private Date age;
        private String address;
        private String city;
        private String state;
        private String zipcode;
        private boolean decAmaStatus1;
        private boolean decAmaStatus2;
        private String firstName;
        private String lastName;
        private String membTypName;
        private String emailId;
        

     

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }        

    public String getMembTypName() {
        return membTypName;
    }
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

   

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
  
   
   

    public void setAge(Date age) {
        this.age = age;
    }

    public Date getAge() {
        return age;
    }
    
    public void setDecAmaStatus1(boolean decAmaStatus1) {
        this.decAmaStatus1 = decAmaStatus1;
    }

    public void setDecAmaStatus2(boolean decAmaStatus2) {
        this.decAmaStatus2 = decAmaStatus2;
    }

    public boolean isDecAmaStatus1() {
        return decAmaStatus1;
    }

    public boolean isDecAmaStatus2() {
        return decAmaStatus2;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMembTypName(String membTypName) {
        this.membTypName = membTypName;
    }
    
}
