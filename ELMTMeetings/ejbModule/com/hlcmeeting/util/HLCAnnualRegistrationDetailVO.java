/*
 * AnnualRegistrationDetailVO.java
 *
 * Created on November 3, 2006, 4:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import java.util.*;
/**
 *
 * @author suresh
 */
public class HLCAnnualRegistrationDetailVO implements java.io.Serializable {
    
    /** Creates a new instance of AnnualRegistrationDetailVO */
    public HLCAnnualRegistrationDetailVO() {
    }
    
    private String ardId;
    private String annualMeetingId;
    private String userId;
    private String badgeName;
    private String firstName;
    private String specificationName;
    

    private String memTypeName;
    private String daysApplied;
    private String regAmount;
    private boolean ponyMemberStatus;
    private String ponyMemId;
    private String ponyClubName;
    
    private boolean accomFaciStatus;
    private String accomDetails;
    private String requestStatus;
    private String remarks;
    private Date addDate;
    private String lastName;
    private String registrarId;
    private String registrarName;
    
    
    
    public void setArdId(String ardId) {
        this.ardId = ardId;
    }

    public void setAnnualMeetingId(String annualMeetingId) {
        this.annualMeetingId = annualMeetingId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public void setMemTypeName(String memTypeName) {
        this.memTypeName = memTypeName;
    }

    public void setDaysApplied(String daysApplied) {
        this.daysApplied = daysApplied;
    }

    public void setRegAmount(String regAmount) {
        this.regAmount = regAmount;
    }

    public void setPonyMemberStatus(boolean ponyMemberStatus) {
        this.ponyMemberStatus = ponyMemberStatus;
    }

    public void setPonyMemId(String ponyMemId) {
        this.ponyMemId = ponyMemId;
    }

    public void setPonyClubName(String ponyClubName) {
        this.ponyClubName = ponyClubName;
    }

    public void setAccomFaciStatus(boolean accomFaciStatus) {
        this.accomFaciStatus = accomFaciStatus;
    }

    public void setAccomDetails(String accomDetails) {
        this.accomDetails = accomDetails;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getArdId() {
        return ardId;
    }

    public String getAnnualMeetingId() {
        return annualMeetingId;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getBadgeName(){
        return badgeName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public String getMemTypeName() {
        return memTypeName;
    }

    public String getDaysApplied() {
        return daysApplied;
    }

    public String getRegAmount() {
        return regAmount;
    }

    public boolean isPonyMemberStatus() {
        return ponyMemberStatus;
    }

    public String getPonyMemId() {
        return ponyMemId;
    }

    public String getPonyClubName() {
        return ponyClubName;
    }

    public boolean getAccomFaciStatus() {
        return accomFaciStatus;
    }

    public String getAccomDetails() {
        return accomDetails;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRegistrarId(String registrarId) {
        this.registrarId = registrarId;
    }

    public String getRegistrarId() {
        return registrarId;
    }

    public void setRegistrarName(String registrarName) {
        this.registrarName = registrarName;
    }

    public String getRegistrarName() {
        return registrarName;
    }
}
