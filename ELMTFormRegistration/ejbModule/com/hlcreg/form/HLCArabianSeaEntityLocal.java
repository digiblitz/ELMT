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
package com.hlcreg.form;

import javax.ejb.EJBLocalObject;
import java.util.Date;


/**
 * This is the local interface for ArabianSeaEntity enterprise bean.
 */
public interface HLCArabianSeaEntityLocal extends EJBLocalObject, HLCArabianSeaEntityLocalBusiness {
    
    /*=====================Horse Member Details =============================*/
    
        public void setHorseMemberId(String horseMemberId);
         public void setUserId(String userId);
        public void setHorseMembershipTypeId(String horseMembershipTypeId);
        public void setHorseServiceTypeId(String horseServiceTypeId);
        public void setCompetitionName(String competitionName);
        public void setRegisteredName(String registeredName); 
        public void setBaRegisteredName(String baRegisteredName);
        public void setBaPastName(String baPastName); 
        public void setRiderMemberId(String riderMemberId);
        public void setAddRiderMemberId(String addRiderMemberId);
        public void setPrevRiderMemberId(String prevRiderMemberId);
        public void setOwnerId(String ownerId);
        public void setOwnerEmail(String ownerEmail);
        public void setLoginName(String loginName);
        public void setPrevOwnerName(String prevOwnerName);
        public void setAddOwnerName(String addOwnerName);
        public void setExpiryDate(Date expiryDate);
        
   /*=====================Horse Description======================================*/
       
        public void setColor(String color);
        public void setGender(String gender);
        public void setHeight(String height);
        public void setYearFoaled(String yearFoaled);
        public void setBreed(String breed);
        public void setCountry(String country);
        public void setSire(String sire);
        public void setSireBreed(String sireBreed);
        public void setDam(String dam);
        public void setDamBreed(String damBreed);
        public void setImportedFrom(String importedFrom);
        public void setImportDate(Date importDate);
        public void setForeignGrade(String foreignGrade);
        public void setForeignPoints(double foreignPoints);
        public void setAssignedGrade(String assignedGrade);
        public void setAssignedPoints(double assignedPoints);
        public void setBreed2(String breed2);
        public void setSireBreed2(String sireBreed2);
        public void setDamBreed2(String damBreed2);
        public void setNotes(String notes);
        public void setSplNotes(String splNotes);
}

