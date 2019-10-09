/*
 * HorseSearchVO.java
 *
 * Created on November 17, 2006, 1:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCHorseSearchVO implements java.io.Serializable{
    
    /** Creates a new instance of HorseSearchVO */
    public HLCHorseSearchVO() {
    }
    
    private String horseMemberId;
    private String competitionName; 
    private String registeredName; 
    private String riderMemberId;
    private String firstName;
    private String lastName; 
    private String gender; 
    private String breed; 
    private String yearFoaled; 
    private String sire; 
    private String dam;
    
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setYearFoaled(String yearFoaled) {
        this.yearFoaled = yearFoaled;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public void setDam(String dam) {
        this.dam = dam;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getRegisteredName() {
        return registeredName;
    }

  

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public String getYearFoaled() {
        return yearFoaled;
    }

    public String getSire() {
        return sire;
    }

    public String getDam() {
        return dam;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }
    
}
