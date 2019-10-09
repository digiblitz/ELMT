/*
 * RenewUpdateHorseRegActionForm.java
 *
 * Created on August 27, 2006, 4:19 PM
 */

package com.mrm.actionform;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author karthikeyan
 * @version
 */

public class RenewUpdateHorseRegActionForm extends org.apache.struts.action.ActionForm {
    
    private String feeDisp;
    private String horseName;
    private String registeredName;
    private String breedAssoc;
    private String pastName;
    private String colorHorse;
    private String sex;
    private String height;
    private String yearfoaled;
    private String breed ;
    private String countryOrigin;
    private String sireName;
    private String sireBreed;
    private String damName;
    private String damBreed;
    private String importedFrom;
    private String dateImported;
    private String foreignGrade;
    private String foreignPoints;
    private String assignedGrade;
    private String assignedPoints;
    private String r11; 
    private String totalAmount;
    
    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public String getRegisteredName() {
        return registeredName;
    }
   
    public String getBreedAssoc() {
        return breedAssoc;
    }

    public void setBreedAssoc(String breedAssoc) {
        this.breedAssoc = breedAssoc;
    }

    public String getPastName() {
        return pastName;
    }

    public void setPastName(String pastName) {
        this.pastName = pastName;
    }

    public String getColorHorse() {
        return colorHorse;
    }

    public void setColorHorse(String colorHorse) {
        this.colorHorse = colorHorse;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getYearfoaled() {
        return yearfoaled;
    }

    public void setYearfoaled(String yearfoaled) {
        this.yearfoaled = yearfoaled;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public String getSireName() {
        return sireName;
    }

    public void setSireName(String sireName) {
        this.sireName = sireName;
    }

    public String getSireBreed() {
        return sireBreed;
    }

    public void setSireBreed(String sireBreed) {
        this.sireBreed = sireBreed;
    }

    public String getDamName() {
        return damName;
    }

    public void setDamName(String damName) {
        this.damName = damName;
    }

    public String getDamBreed() {
        return damBreed;
    }

    public void setDamBreed(String damBreed) {
        this.damBreed = damBreed;
    }

    public String getImportedFrom() {
        return importedFrom;
    }

    public void setImportedFrom(String importedFrom) {
        this.importedFrom = importedFrom;
    }

    public String getDateImported() {
        return dateImported;
    }

    public void setDateImported(String dateImported) {
        this.dateImported = dateImported;
    }

    public String getForeignGrade() {
        return foreignGrade;
    }

    public void setForeignGrade(String foreignGrade) {
        this.foreignGrade = foreignGrade;
    }

    public String getForeignPoints() {
        return foreignPoints;
    }

    public void setForeignPoints(String foreignPoints) {
        this.foreignPoints = foreignPoints;
    }

    public String getAssignedPoints() {
        return assignedPoints;
    }

    public void setAssignedPoints(String assignedPoints) {
        this.assignedPoints = assignedPoints;
    }

    public String getAssignedGrade() {
        return assignedGrade;
    }

    public void setAssignedGrade(String assignedGrade) {
        this.assignedGrade = assignedGrade;
    }

    public String getFeeDisp() {
        return feeDisp;
    }

    public void setFeeDisp(String feeDisp) {
        this.feeDisp = feeDisp;
    }

    public String getR11() {
        return null;
    }

    public void setR11(String r11) {
        this.r11 = r11;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
      
  }

