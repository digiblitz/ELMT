/*
 * ValidateCompRegVO.java
 *
 * Created on April 12, 2007, 6:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Suresh
 */
public class HLCValidateCompRegVO implements Serializable{
    
    /** Creates a new instance of ValidateCompRegVO */
    public HLCValidateCompRegVO() {
    }
    
    private String horseName;
    private String horseMemberId;
    private ArrayList horseList;
    private String riderMemberId;
    private String riderFName;
    private String riderLName;
    private ArrayList riderList;
    private ArrayList relationList;
    
    private String tmpRegistrationId;
    private String eventId;
    private String eventType;
    private String eventLevel;
    private String eventDivisionAmateur;
    private String eventDivisionAge;
    private String eventDivisionExperience;
    private String horseUsefId;
    private String riderUsefId;
    private String ownerFirstName;
    private String ownerLastName;
    private String ownerUseaId;
    private String ownerUsefId;
   
    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setHorseList(ArrayList horseList) {
        this.horseList = horseList;
    }

    public ArrayList getHorseList() {
        return horseList;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public void setRiderFName(String riderFName) {
        this.riderFName = riderFName;
    }

    public void setRiderLName(String riderLName) {
        this.riderLName = riderLName;
    }

    public void setRiderList(ArrayList riderList) {
        this.riderList = riderList;
    }

    public String getRiderFName() {
        return riderFName;
    }

    public String getRiderLName() {
        return riderLName;
    }

    public ArrayList getRiderList() {
        return riderList;
    }

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

    public void setHorseUsefId(String horseUsefId) {
        this.horseUsefId = horseUsefId;
    }

    public void setRiderUsefId(String riderUsefId) {
        this.riderUsefId = riderUsefId;
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

    public String getHorseUsefId() {
        return horseUsefId;
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

    public void setRelationList(ArrayList relationList) {
        this.relationList = relationList;
    }

    public ArrayList getRelationList() {
        return relationList;
    }
    
}
