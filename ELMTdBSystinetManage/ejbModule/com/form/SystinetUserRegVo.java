/*
 * UserMaster.java
 *
 * Created on August 24, 2006, 1:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.form;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author harmohan
 */
public class SystinetUserRegVo implements Serializable {
  
    public String loginName;

  
    public String emailId;
    public String password;


    private String phoneNo;

    private String fullName;
    private String defLanguage;
    private String description;
    private String businessName;
    private String userProfile;
    
   
    private String mobileNo;
 
    private String address;

    private String city;
    private String state;
    private String country;
    private String zip;
   
    
    /** Creates a new instance of UserMaster */
    public SystinetUserRegVo() {
    }
    
  


    public String getEmailId() {
		return emailId;
	}




	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getFullName() {
		return fullName;
	}




	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	public String getDefLanguage() {
		return defLanguage;
	}




	public void setDefLanguage(String defLanguage) {
		this.defLanguage = defLanguage;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getBusinessName() {
		return businessName;
	}




	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}




	public String getUserProfile() {
		return userProfile;
	}




	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
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

 
    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
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
