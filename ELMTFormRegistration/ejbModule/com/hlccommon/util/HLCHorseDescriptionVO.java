/*
 * HorseDescriptionVO.java
 *
 * Created on November 23, 2006, 7:35 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

import java.util.Date;

/**
 *
 * @author suresh
 */
public class HLCHorseDescriptionVO implements java.io.Serializable {
    
    /** Creates a new instance of HorseDescriptionVO */
    public HLCHorseDescriptionVO() {
    }
    
    private String horseDescriptionId;
    private String horseMemberId;
    private String color;
    private String gender;
    private String height;
    private String yearFoaled;
    
    private String breed;
    private String country;
    private String sire;
    private String sireBreed;
    private String dam;
    private String damBreed;
    
    private String breed2;
    private String sireBreed2;
    private String damBreed2;
    private String importedFrom;
    private Date importDate;
    private String foreignGrade;
    private float foreignPoints;
    
    private String assignedGrade;
    private float assignedPoints;
    private String notes;
    private String splNotes;
    
    
    public void setHorseDescriptionId(String horseDescriptionId) {
        this.horseDescriptionId = horseDescriptionId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setYearFoaled(String yearFoaled) {
        this.yearFoaled = yearFoaled;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSire(String sire) {
        this.sire = sire;
    }

    public void setSireBreed(String sireBreed) {
        this.sireBreed = sireBreed;
    }

    public void setDam(String dam) {
        this.dam = dam;
    }

    public void setDamBreed(String damBreed) {
        this.damBreed = damBreed;
    }

    public void setBreed2(String breed2) {
        this.breed2 = breed2;
    }


    public void setDamBreed2(String damBreed2) {
        this.damBreed2 = damBreed2;
    }

    public void setImportedFrom(String importedFrom) {
        this.importedFrom = importedFrom;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public void setForeignGrade(String foreignGrade) {
        this.foreignGrade = foreignGrade;
    }

    public void setForeignPoints(float foreignPoints) {
        this.foreignPoints = foreignPoints;
    }

    public void setAssignedGrade(String assignedGrade) {
        this.assignedGrade = assignedGrade;
    }

    public void setAssignedPoints(float assignedPoints) {
        this.assignedPoints = assignedPoints;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSplNotes(String splNotes) {
        this.splNotes = splNotes;
    }
      public void setSireBreed2(String sireBreed2) {
        this.sireBreed2 = sireBreed2;
    }

    public String getHorseDescriptionId() {
        return horseDescriptionId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getColor() {
        return color;
    }
    

    public String getGender() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getYearFoaled() {
        return yearFoaled;
    }

    public String getBreed() {
        return breed;
    }

  

    public String getSire() {
        return sire;
    }

    public String getSireBreed() {
        return sireBreed;
    }

    public String getDam() {
        return dam;
    }

    public String getDamBreed() {
        return damBreed;
    }

    public String getBreed2() {
        return breed2;
    }

    public String getSireBreed2() {
        return sireBreed2;
    }

    public String getDamBreed2() {
        return damBreed2;
    }

    public String getImportedFrom() {
        return importedFrom;
    }

    public Date getImportDate() {
        return importDate;
    }

    public String getForeignGrade() {
        return foreignGrade;
    }

    public float getForeignPoints() {
        return foreignPoints;
    }

    public String getAssignedGrade() {
        return assignedGrade;
    }

    public float getAssignedPoints() {
        return assignedPoints;
    }

    public String getNotes() {
        return notes;
    }

    public String getSplNotes() {
        return splNotes;
    }

    public String getCountry() {
        return country;
    }
}
