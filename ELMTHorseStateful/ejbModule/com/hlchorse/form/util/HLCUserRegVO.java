/*
 * UserRegVO.java
 *
 * Created on October 7, 2006, 1:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlchorse.form.util;
import java.io.Serializable;
/**
 *
 * @author harmohan
 */
public class HLCUserRegVO implements Serializable {
    private String preferedCommunication;
    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String sufix;
    private String dob;
    private String gender;
    private String emailId;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private String userId;
    private String loginName;
    private String user_code;
    
    private boolean nonUseaMailingStatus;
    private boolean nonUseaEmailStatus;

    private String primaryContactTypeName;

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_code() {
        return user_code;
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
    private String primarySuite; 
    private String primaryAddress1; 
    private String primaryAddress2; 
    private String primaryCity; 
    private String primaryState; 
    private String primaryCountry; 
    private String prmaryZip; 
    private String primaryPhoneNo; 
    private String promaryMobileNo; 
    private String primaryFaxNo; 

    private String secondaryContactTypeName;
    private String secondarySuite; 
    private String secondaryAddress1; 
    private String secondaryAddress2; 
    private String secondaryCity; 
    private String secondaryState; 
    private String secondaryCountry; 
    private String secondaryZip; 
    private String secondaryPhoneNo; 
    private String secondaryMobileNo;
    private String secondaryFaxNo; 

    /** Creates a new instance of UserRegVO */
    public HLCUserRegVO() {
    }
    
        
    public String getDob() { return dob;}
    public void setDob(String dob) {this.dob = dob;}
    public String getEmailId() {return emailId;	}
    public void setEmailId(String emailId) {this.emailId = emailId;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getGender() {	return gender;	}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPreferedCommunication() {
			return preferedCommunication;
		}

		public void setPreferedCommunication(String preferedCommunication) {
			this.preferedCommunication = preferedCommunication;
		}

		public String getPrefix() {
			return prefix;
		}

		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}

		public String getPrimaryAddress1() {
			return primaryAddress1;
		}

		public void setPrimaryAddress1(String primaryAddress1) {
			this.primaryAddress1 = primaryAddress1;
		}

		public String getPrimaryAddress2() {
			return primaryAddress2;
		}

		public void setPrimaryAddress2(String primaryAddress2) {
			this.primaryAddress2 = primaryAddress2;
		}

		public String getPrimaryCity() {
			return primaryCity;
		}

		public void setPrimaryCity(String primaryCity) {
			this.primaryCity = primaryCity;
		}

		public String getPrimaryContactTypeName() {
			return primaryContactTypeName;
		}

		public void setPrimaryContactTypeName(String primaryContactTypeName) {
			this.primaryContactTypeName = primaryContactTypeName;
		}

		public String getPrimaryCountry() {
			return primaryCountry;
		}

		public void setPrimaryCountry(String primaryCountry) {
			this.primaryCountry = primaryCountry;
		}

		public String getPrimaryFaxNo() {
			return primaryFaxNo;
		}

		public void setPrimaryFaxNo(String primaryFaxNo) {
			this.primaryFaxNo = primaryFaxNo;
		}

		public String getPrimaryPhoneNo() {
			return primaryPhoneNo;
		}

		public void setPrimaryPhoneNo(String primaryPhoneNo) {
			this.primaryPhoneNo = primaryPhoneNo;
		}

		public String getPrimaryState() {
			return primaryState;
		}

		public void setPrimaryState(String primaryState) {
			this.primaryState = primaryState;
		}

		public String getPrimarySuite() {
			return primarySuite;
		}

		public void setPrimarySuite(String primarySuite) {
			this.primarySuite = primarySuite;
		}

		public String getPrmaryZip() {
			return prmaryZip;
		}

		public void setPrmaryZip(String prmaryZip) {
			this.prmaryZip = prmaryZip;
		}

		public String getPromaryMobileNo() {
			return promaryMobileNo;
		}

		public void setPromaryMobileNo(String promaryMobileNo) {
			this.promaryMobileNo = promaryMobileNo;
		}

		public String getSecondaryAddress1() {
			return secondaryAddress1;
		}

		public void setSecondaryAddress1(String secondaryAddress1) {
			this.secondaryAddress1 = secondaryAddress1;
		}

		public String getSecondaryAddress2() {
			return secondaryAddress2;
		}

		public void setSecondaryAddress2(String secondaryAddress2) {
			this.secondaryAddress2 = secondaryAddress2;
		}

		public String getSecondaryCity() {
			return secondaryCity;
		}

		public void setSecondaryCity(String secondaryCity) {
			this.secondaryCity = secondaryCity;
		}

		public String getSecondaryContactTypeName() {
			return secondaryContactTypeName;
		}

		public void setSecondaryContactTypeName(String secondaryContactTypeName) {
			this.secondaryContactTypeName = secondaryContactTypeName;
		}

		public String getSecondaryCountry() {
			return secondaryCountry;
		}

		public void setSecondaryCountry(String secondaryCountry) {
			this.secondaryCountry = secondaryCountry;
		}

		public String getSecondaryFaxNo() {
			return secondaryFaxNo;
		}

		public void setSecondaryFaxNo(String secondaryFaxNo) {
			this.secondaryFaxNo = secondaryFaxNo;
		}

		public String getSecondaryMobileNo() {
			return secondaryMobileNo;
		}

		public void setSecondaryMobileNo(String secondaryMobileNo) {
			this.secondaryMobileNo = secondaryMobileNo;
		}

		public String getSecondaryPhoneNo() {
			return secondaryPhoneNo;
		}

		public void setSecondaryPhoneNo(String secondaryPhoneNo) {
			this.secondaryPhoneNo = secondaryPhoneNo;
		}

		public String getSecondaryState() {
			return secondaryState;
		}

		public void setSecondaryState(String secondaryState) {
			this.secondaryState = secondaryState;
		}

		public String getSecondarySuite() {
			return secondarySuite;
		}

		public void setSecondarySuite(String secondarySuite) {
			this.secondarySuite = secondarySuite;
		}

		public String getSecondaryZip() {
			return secondaryZip;
		}

		public void setSecondaryZip(String secondaryZip) {
			this.secondaryZip = secondaryZip;
		}

		public String getSecretAnswer() {
			return secretAnswer;
		}

		public void setSecretAnswer(String secretAnswer) {
			this.secretAnswer = secretAnswer;
		}

		public String getSecretQuestion() {
			return secretQuestion;
		}

		public void setSecretQuestion(String secretQuestion) {
			this.secretQuestion = secretQuestion;
		}

		public String getSufix() {
			return sufix;
		}

		public void setSufix(String sufix) {
			this.sufix = sufix;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}
                public void setLoginName(String loginName) {
                    this.loginName = loginName;
                }

                public String getLoginName() {
                    return loginName;
                }
    
    
   public String toString(){
     
    StringBuffer buffer = new StringBuffer()
        .append("\n loginName :"+ loginName)
   	.append("\n preferedCommunication :"+ preferedCommunication)
	.append("\n prefix :"+ prefix)
	.append("\n firstName :"+ firstName)
	.append("\n middleName :"+ middleName)
	.append("\n lastName :"+ lastName)
	.append("\n sufix :"+ sufix)
	.append("\n dob :"+ dob) 
        .append("\n user_code :"+ user_code)
        
	.append("\n gender :"+ gender) 
	.append("\n emailId :"+ emailId) 
	.append("\n password :"+ password) 
	.append("\n secretQuestion :"+ secretQuestion) 
	.append("\n secretAnswer :"+ secretAnswer)
	.append("\n userId :"+ userId)
	.append("\n primaryContactTypeName :"+ primaryContactTypeName) 
	.append("\n primarySuite :"+ primarySuite)
	.append("\n primaryAddress1 :"+ primaryAddress1) 
	.append("\n primaryAddress1 :"+ primaryAddress2)
	.append("\n primaryCity :"+ primaryCity)
	.append("\n primaryState :"+ primaryState)
	.append("\n primaryCountry :"+ primaryCountry)
	.append("\n prmaryZip :"+ prmaryZip)
	.append("\n primaryPhoneNo :"+ primaryPhoneNo)
	.append("\n primaryFaxNo :"+ primaryFaxNo)

	.append("\n secondaryContactTypeName :"+ secondaryContactTypeName)
	.append("\n secondarySuite :"+ secondarySuite)
	.append("\n secondaryAddress1 :"+ secondaryAddress1)
	.append("\n secondaryAddress2 :"+ secondaryAddress2)
	.append("\n secondaryCity :"+ secondaryCity)
	.append("\n secondaryState :"+ secondaryState)
	
	.append("\n secondaryCountry :"+ secondaryCountry)
	.append("\n secondaryZip :"+ secondaryZip)
	.append("\n secondaryPhoneNo :"+ secondaryPhoneNo)
	.append("\n secondaryMobileNo :"+ secondaryMobileNo)
	.append("\n secondaryFaxNo :"+ secondaryFaxNo);

    return buffer.toString();
  }

   
    
}
