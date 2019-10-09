/*
 * UsrSignUpVO.java
 *
 * Created on December 5, 2006, 10:34 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.vo;
import java.util.*;
import java.io.*;

/**
 *
 * @author karthikeyan
 */
public class UsrSignUpVO implements Serializable{
    
    
    private String fname;
    private String lname;
    private String birthmonth;
    private String birthday;
    private String birthyear;
    
    private String userName;
    private String email;
    private String zip;
    private String membid;
    private String phone;
    private String userId;
    
    /** Creates a new instance of UsrSignUpVO */
    public UsrSignUpVO() {
    }
    
     public UsrSignUpVO(String fname,String lname,String birthmonth, String birthday,String birthyear,String userName
             ,String email,String zip,String membid,String phone,String userId) {
         
         this.fname=fname;
         this.lname=lname;
         this.birthmonth=birthmonth;
         this.birthday=birthday;
         this.birthyear=birthyear;
         this.userName=userName;
         this.email=email;
         this.zip=zip;
         this.membid=membid;
         this.phone=phone;
         this.userId=userId;
    }
    
     

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setBirthmonth(String birthmonth) {
        this.birthmonth = birthmonth;
    }

    public String getBirthmonth() {
        return birthmonth;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMembid() {
        return membid;
    }

    public void setMembid(String membid) {
        this.membid = membid;
    }
 
     public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" fname :"+ fname+"\n")
        .append(" lname :"+ lname+"\n")
        .append(" birthmonth :"+ birthmonth+"\n")
        .append(" birthyear :"+ birthyear+"\n") 
        .append(" birthday :"+ birthday+"\n") 
        .append(" email :"+ email+"\n")
        .append(" userName :"+ userName+"\n")
        .append(" zip :"+ zip+"\n")
        .append(" membid :"+ membid+"\n")
        .append(" Phone :"+ phone+"\n")
        .append(" UserId :"+ userId+"\n");
    
    return buffer.toString();
     }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
