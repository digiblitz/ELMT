/*
 * HorseDescription.java
 *
 * Created on August 23, 2006, 11:33 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.vo;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author karthikeyan
 */
public class voHorseDescription implements Serializable {
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
    private String importedFrom;
    private Date importDate;
    private String foreignGrade;
    private int foreignPoints;
    private String assignedGrade;
    private int assignedPoints;
    
    /** Creates a new instance of HorseDescription */
    public voHorseDescription() {
    }
    
    public voHorseDescription(String horseMemberId, String color, String gender, String height, String yearFoaled, String breed, 
            String country, String sire, String sireBreed, String dam, String damBreed, String importedFrom, Date importDate, 
            String foreignGrade, int foreignPoints, String assignedGrade, int assigned_points){
        
        this.horseMemberId = horseMemberId;
        this.color = color;
        this.gender = gender;
        this.height = height;
        this.yearFoaled = yearFoaled;
        this.breed = breed;
        this.country = country;
        this.sire = sire;
        this.sireBreed = sireBreed;
        this.dam = dam;
        this.damBreed = damBreed;
        this.importedFrom = importedFrom;
        this.importDate = importDate;
        this.foreignGrade = foreignGrade;
        this.foreignPoints = foreignPoints;
        this.assignedGrade = assignedGrade;
        this.assignedPoints = assignedPoints;
    }
    
    // getters methods
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
         public String getCountry() {
           return country;
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
         public String getImportedFrom() {
           return importedFrom;
        }
          public Date getImportDate() {
           return importDate;
        }
           public String getForeignGrade() {
           return foreignGrade;
        }
            public int getForeignPoints() {
           return foreignPoints;
        }
         public String getAssignedGrade() {
           return assignedGrade;
        }
         public int getAssignedPoints() {
           return assignedPoints;
        }
         
         
          // setters methods
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
        public void setImportedFrom(String importedFrom) {
           this.importedFrom = importedFrom;
        }
        public void setImportDate(Date importDate) {
           this.importDate = importDate;
        }
        public void setForeignGrade(String foreignGrade) {
           this.foreignGrade = foreignGrade;
        }
        public void setForeignPoints(int foreignPoints) {
          this.foreignPoints = foreignPoints;
        }
        public void setAssignedGrade(String assignedGrade) {
           this.assignedGrade = assignedGrade;
        }
        public void setAssignedPoints(int assignedPoints) {
           this.assignedPoints = assignedPoints;
        }
}

