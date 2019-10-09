/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.mrm.actionform;
/*
 * RenewHorseRegActionForm.java
 *
 * Created on August 26, 2006, 3:01 PM
 */

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author karthikeyan
 * @version
 */

public class RenewHorseRegActionForm extends org.apache.struts.action.ActionForm {
    
    private String horseName;
    private String registeredName;
    private String breedAssoc;
    private String pastName;
    private String colorHorse;
    private String sex;
    private String height;
    private String yearfoaled;
    private String breed;
    private String countryOrigin;
    private String sireName;
    private String sireBreed;
    private String damName;
    private String damBreed ;
    private String importedFrom;
    private String dateImported;
    private String foreignGrade;
    private String foreignPoints;
    private String assignedGrade;
    private String assignedPoints;
       
    
    public String getHorseName()
   {
       return horseName;
   }
   public void setHorseName(String hname)
   {
        this.horseName=hname;
   }
   public String getRegisteredName() {
        return registeredName;
    }
    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
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

    public String getSireBreed() {
        return sireBreed;
    }

    public void setSireBreed(String sireBreed) {
        this.sireBreed = sireBreed;
    }

    public String getSireName() {
        return sireName;
    }

    public void setSireName(String sireName) {
        this.sireName = sireName;
    }

    public String getDamName() {
        return damName;
    }

    public void setDamName(String damName) {
        this.damName = damName;
    }

    public String getImportedFrom() {
        return importedFrom;
    }

    public void setImportedFrom(String importedFrom) {
        this.importedFrom = importedFrom;
    }

    public void setDateImported(String dateImported) {
        this.dateImported = dateImported;
    }

    public String getDateImported() {
        return dateImported;
    }

    public void setForeignGrade(String foreignGrade) {
        this.foreignGrade = foreignGrade;
    }

    public String getForeignGrade() {
        return foreignGrade;
    }

    public String getForeignPoints() {
        return foreignPoints;
    }

    public void setForeignPoints(String foreignPoints) {
        this.foreignPoints = foreignPoints;
    }

    public String getAssignedGrade() {
        return assignedGrade;
    }

    public void setAssignedGrade(String assignedGrade) {
        this.assignedGrade = assignedGrade;
    }

    public String getAssignedPoints() {
        return assignedPoints;
    }

    public void setAssignedPoints(String assignedPoints) {
        this.assignedPoints = assignedPoints;
    }
}