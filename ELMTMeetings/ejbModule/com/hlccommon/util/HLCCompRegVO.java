/*
 * CompRegVO.java
 *
 * Created on April 12, 2007, 7:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.io.Serializable;

/**
 *
 * @author Suresh
 */
public class HLCCompRegVO implements Serializable{
    
    /** Creates a new instance of CompRegVO */
    public HLCCompRegVO() {
    }
    
    private String registrationId;
    private String tmpRegistrationId;
    private String eventId;
    private String eventType;
    private String eventLevel;
    private String eventDivisionAmateur;
    private String eventDivisionAge;
    private String eventDivisionExperience;
    private String horseName;
    private String horseMemberId;
    private String horseUsefId;
    
    private String riderFirstName;
    private String riderLastName;
    private String riderMemberId;
    private String riderUsefId;
    private String horseUsaEq;
    private String riderUsaEq;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerUseaId;
    private String ownerUsefId;
    private String compResultId;
    private boolean exceptionStatus;
    private String regUploadId;
    
    
    public void setTmpRegistrationId(String tmpRegistrationId) {
        this.tmpRegistrationId = tmpRegistrationId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public void setEventDivisionAmateur(String eventDivisionAmateur) {
        this.eventDivisionAmateur = eventDivisionAmateur;
    }

    public void setEventDivisionAge(String eventDivisionAge) {
        this.eventDivisionAge = eventDivisionAge;
    }

    public void setEventDivisionExperience(String eventDivisionExperience) {
        this.eventDivisionExperience = eventDivisionExperience;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setHorseUsefId(String horseUsefId) {
        this.horseUsefId = horseUsefId;
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

    public void setRiderUsefId(String riderUsefId) {
        this.riderUsefId = riderUsefId;
    }

    public void setRiderUsaEq(String riderUsaEq) {
        this.riderUsaEq = riderUsaEq;
    }

    public void setHorseUsaEq(String horseUsaEq) {
        this.horseUsaEq = horseUsaEq;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public void setOwnerUseaId(String ownerUseaId) {
        this.ownerUseaId = ownerUseaId;
    }

    public void setOwnerUsefId(String ownerUsefId) {
        this.ownerUsefId = ownerUsefId;
    }

    public void setCompResultId(String compResultId) {
        this.compResultId = compResultId;
    }

    public String getTmpRegistrationId() {
        return tmpRegistrationId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public String getEventDivisionAmateur() {
        return eventDivisionAmateur;
    }

    public String getEventDivisionAge() {
        return eventDivisionAge;
    }

    public String getEventDivisionExperience() {
        return eventDivisionExperience;
    }

    public String getHorseName() {
        return horseName;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseUsefId() {
        return horseUsefId;
    }

    public String getHorseUsaEq() {
        return horseUsaEq;
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

    public String getRiderUsaEq() {
        return riderUsaEq;
    }

    public String getRiderUsefId() {
        return riderUsefId;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public String getOwnerUseaId() {
        return ownerUseaId;
    }

    public String getOwnerUsefId() {
        return ownerUsefId;
    }

    public String getCompResultId() {
        return compResultId;
    }

    public void setExceptionStatus(boolean exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public boolean isExceptionStatus() {
        return exceptionStatus;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }
    
    public String getRegUploadId() {
        return regUploadId;
    }

    public void setRegUploadId(String regUploadId) {
        this.regUploadId = regUploadId;
    }
    
}
