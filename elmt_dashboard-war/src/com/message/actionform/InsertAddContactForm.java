/*
 * InsertAddContactForm.java
 *
 * Created on September 27, 2006, 10:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.message.actionform;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author rathna
 */
public class InsertAddContactForm extends ActionForm{
    
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String emailid;
    private String alteremailid;
    private String phone;
    private String mobile;
    private String fax;
    private String street;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String contactId;
    private String[] userCheckBox;
    private List results;  
    private String start;
    private String range;
    private String resultSize;
    private String sortName;

//Getter Method
    public String getContactId() {
        return contactId;
    }

    public List getResults() {
        return results;
    }

    public String[] getUserCheckBox() {
        return userCheckBox;
    }

    public String getStart() {
        return start;
    }

    public String getRange() {
        return range;
    }

    public String getResultSize() {
        return resultSize;
    }

    public String getSortName() {
        return sortName;
    }

    public String getAlteremailid() {
        return alteremailid;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getFax() {
        return fax;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getMobile() {
        return mobile;
    }

    public String getNickname() {
        return nickname;
    }


    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }
//Setter Methods
    
    public void setStart(String start) {
        this.start = start;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setResultSize(String resultSize) {
        this.resultSize = resultSize;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
    
    public void setAlteremailid(String alteremailid) {
        this.alteremailid = alteremailid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setUserCheckBox(String[] userCheckBox) {
        this.userCheckBox = userCheckBox;
    }    
    
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }
    
    public void setResults(List results) {
        this.results = results;
    }    

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.firstname=null;
        this.middlename=null;
        this.lastname=null;
        this.nickname=null;
        this.emailid=null;
        this.alteremailid=null;
        this.phone=null;
        this.mobile=null;
        this.fax=null;
        this.street=null;
        this.country=null;
        this.state=null;
        this.city=null;
        this.zipcode=null;
        this.contactId=null;
        this.userCheckBox=null;
        this.results=null;  
        this.start=null;
        this.range=null;
        this.resultSize=null;
        this.sortName=null;
    }
    
    public String toString(){
        
        StringBuffer buffer = new StringBuffer()
        .append(" firstname :"+ firstname+"\t\t")
        .append(" middlename :"+ middlename+"\t\t")
        .append(" lastname :"+ lastname+"\t\t")
        .append(" nickname :"+nickname+"\t\t")
        .append(" emailid :"+emailid+"\t\t")
        .append(" alteremailid :"+alteremailid+"\t\t")
        .append(" phone :"+phone+"\t\t")
        .append(" mobile :"+mobile+"\t\t")
        .append(" fax :"+fax+"\t\t")
        .append(" street :"+ street+"\t\t")
        .append(" country :"+ country+"\t\t")
        .append(" state :"+ state+"\t\t")
        .append(" city :"+ city+"\t\t")
        .append(" zipcode :"+ zipcode+"\t\t");
        
        return buffer.toString();
    }    
}

