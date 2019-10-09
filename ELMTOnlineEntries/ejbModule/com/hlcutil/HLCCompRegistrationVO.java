/*
 * CompRegistrationVO.java
 *
 * Created on November 23, 2007, 11:35 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;

import java.util.Date;

/**
 *
 * @author Vidhya
 */
public class HLCCompRegistrationVO {
    
    /** Creates a new instance of CompRegistrationVO */
    public HLCCompRegistrationVO() {
    }
    private String priceId;
        private String registrationId;
        private String tmpRegistrationId;
        private String eventId;
        private String organizerId;
        private int competitionYear;
        private String eventType;
        private String eventTitle;
        private String eventLevel;
        private String eventDivision;
        private String eventSubDivision;
        private String eventDivAmateur;
        private String eventDivAge;
        private String eventDivExp;
        private String horseName;
        private String horseMemberId;
        private String horseUSEFId;
        private String riderFirstName;
        private String riderLastName;
        private String riderMemberId;
        private String riderUSEFId;
        private String ownerFirstName;
        private String ownerLastName;
        private String ownerUSEAId;
        private String ownerUSEFId;
        private String compResultId;
        private Date addDate;
        private boolean activeStatus;
        private boolean expectationStatus;
        private String qualFilePath;
        private String qualifystatus;
        private String paymemtId;
        private String quaComments;
        private String riderUserId;
        private String orgFirstName;
        private String orgLastName;


    public int getCompetitionYear() {
        return competitionYear;
    }

    public String getEventId() {
        return eventId;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public String getPriceId() {
        return priceId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public String getTmpRegistrationId() {
        return tmpRegistrationId;
    }

    public void setCompetitionYear(int competitionYear) {
        this.competitionYear = competitionYear;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public void setTmpRegistrationId(String tmpRegistrationId) {
        this.tmpRegistrationId = tmpRegistrationId;
    }
        

    public String getEventDivAge() {
        return eventDivAge;
    }

    public String getEventDivAmateur() {
        return eventDivAmateur;
    }

    public String getEventDivExp() {
        return eventDivExp;
    }

    public String getEventDivision() {
        return eventDivision;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public String getEventSubDivision() {
        return eventSubDivision;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventDivAge(String eventDivAge) {
        this.eventDivAge = eventDivAge;
    }

    public void setEventDivAmateur(String eventDivAmateur) {
        this.eventDivAmateur = eventDivAmateur;
    }

    public void setEventDivExp(String eventDivExp) {
        this.eventDivExp = eventDivExp;
    }

    public void setEventDivision(String eventDivision) {
        this.eventDivision = eventDivision;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public void setEventSubDivision(String eventSubDivision) {
        this.eventSubDivision = eventSubDivision;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseName() {
        return horseName;
    }

    public String getHorseUSEFId() {
        return horseUSEFId;
    }

    public String getRiderFirstName() {
        return riderFirstName;
    }

    public String getRiderLastName() {
        return riderLastName;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public String getRiderUSEFId() {
        return riderUSEFId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setHorseUSEFId(String horseUSEFId) {
        this.horseUSEFId = horseUSEFId;
    }

    public void setRiderFirstName(String riderFirstName) {
        this.riderFirstName = riderFirstName;
    }

    public void setRiderLastName(String riderLastName) {
        this.riderLastName = riderLastName;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setRiderUSEFId(String riderUSEFId) {
        this.riderUSEFId = riderUSEFId;
    }
    

    public Date getAddDate() {
        return addDate;
    }

    public String getCompResultId() {
        return compResultId;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public String getOwnerUSEAId() {
        return ownerUSEAId;
    }

    public String getOwnerUSEFId() {
        return ownerUSEFId;
    }

    public String getPaymemtId() {
        return paymemtId;
    }

    public String getQualFilePath() {
        return qualFilePath;
    }

    public String getQualifystatus() {
        return qualifystatus;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public boolean isExpectationStatus() {
        return expectationStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setCompResultId(String compResultId) {
        this.compResultId = compResultId;
    }

    public void setExpectationStatus(boolean expectationStatus) {
        this.expectationStatus = expectationStatus;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public void setOwnerUSEAId(String ownerUSEAId) {
        this.ownerUSEAId = ownerUSEAId;
    }

    public void setOwnerUSEFId(String ownerUSEFId) {
        this.ownerUSEFId = ownerUSEFId;
    }

    public void setPaymemtId(String paymemtId) {
        this.paymemtId = paymemtId;
    }

    public void setQualFilePath(String qualFilePath) {
        this.qualFilePath = qualFilePath;
    }

    public void setQualifystatus(String qualifystatus) {
        this.qualifystatus = qualifystatus;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setQuaComments(String quaComments) {
        this.quaComments = quaComments;
    }

    public String getQuaComments() {
        return quaComments;
    }
  
    public String getRiderUserId() {
        return riderUserId;
    }

    public void setRiderUserId(String riderUserId) {
        this.riderUserId = riderUserId;
    }
    
    public void setOrgFirstName(String orgFirstName) {
        this.orgFirstName = orgFirstName;
    }
 
    public String getOrgFirstName() {
        return orgFirstName;
    }
 
    public void setOrgLastName(String orgLastName) {
        this.orgLastName = orgLastName;
    }
 
    public String getOrgLastName() {
        return orgLastName;
    }
}
