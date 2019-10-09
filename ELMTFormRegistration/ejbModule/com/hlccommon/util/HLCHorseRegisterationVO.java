/*
 * HorseRegisterationVO.java
 *
 * Created on November 25, 2006, 1:18 PM
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
public class HLCHorseRegisterationVO implements java.io.Serializable {
    
    /** Creates a new instance of HorseRegisterationVO */
    public HLCHorseRegisterationVO() {
    }
    
    private String horseMemberId;
    private String competitionName;
    private String registeredName;
    private String baRegisteredName;
    private String baPastName;
    private String riderMemberId;
    private String prevRiderMemberId;
    private String addRiderMemberId;
    private String ownerId;
    private String trainerId;
    private String prevTrainerId;
    private String prevOwnerId;
    private String addOwnerId;
    private String prevOwnerName;
    private String addOwnerName;
    private String paymentId;
    private Date addDate;
    private String requestStatus;
    private String firstName;
    private String lastName;
    
    private String comments;
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
    
    private String checkNumber;
    private String paymentStatus;
    private String checkName;
    private String sslTxnId;
    private String bankName;
    private Date checkDate;
    private String amount;
    private String registeredBy;
    private String memberTypeName;
    private String membershipTypeId;
    private String horseTypeId;
    
    private String colorId;

    private String breedId;
    private String sireBreedId;
    private String damBreedId;
    private String breed2Id;
    private String sireBreed2Id;
    private String damBreed2Id;
    private String statusName;
    private String statusId;
    private Date activationDate;
    private Date upgradationDate ;
    private String registerByUserId;
    private String priorityValue;
    private String membershipAmount;
    
    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public void setBaRegisteredName(String baRegisteredName) {
        this.baRegisteredName = baRegisteredName;
    }

    public void setBaPastName(String baPastName) {
        this.baPastName = baPastName;
    }

 
    public void setPrevRiderMemberId(String prevRiderMemberId) {
        this.prevRiderMemberId = prevRiderMemberId;
    }

    public void setAddRiderMemberId(String addRiderMemberId) {
        this.addRiderMemberId = addRiderMemberId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setPrevOwnerName(String prevOwnerName) {
        this.prevOwnerName = prevOwnerName;
    }

    public void setAddOwnerName(String addOwnerName) {
        this.addOwnerName = addOwnerName;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getRegisteredName() {
        return registeredName;
    }

    public String getBaRegisteredName() {
        return baRegisteredName;
    }

    public String getBaPastName() {
        return baPastName;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public String getPrevRiderMemberId() {
        return prevRiderMemberId;
    }

    public String getAddRiderMemberId() {
        return addRiderMemberId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPrevOwnerName() {
        return prevOwnerName;
    }

    public String getAddOwnerName() {
        return addOwnerName;
    }

    public void setPrevOwnerId(String prevOwnerId) {
        this.prevOwnerId = prevOwnerId;
    }

    public void setAddOwnerId(String addOwnerId) {
        this.addOwnerId = addOwnerId;
    }

    public String getPrevOwnerId() {
        return prevOwnerId;
    }

    public String getAddOwnerId() {
        return addOwnerId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

                                  

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getAddDate() {
        return addDate;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
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

    public void setSireBreed2(String sireBreed2) {
        this.sireBreed2 = sireBreed2;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public void setSslTxnId(String sslTxnId) {
        this.sslTxnId = sslTxnId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getSslTxnId() {
        return sslTxnId;
    }

    public String getBankName() {
        return bankName;
    }

    
    public Date getCheckDate() {
        return checkDate;
    }

    public String getAmount() {
        return amount;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getRegisteredBy() {
        return registeredBy;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }


    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setHorseTypeId(String horseTypeId) {
        this.horseTypeId = horseTypeId;
    }

    public String getHorseTypeId() {
        return horseTypeId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setSireBreedId(String sireBreedId) {
        this.sireBreedId = sireBreedId;
    }
    
    public String getSireBreedId() {
        return sireBreedId;
    }

    public void setDamBreedId(String damBreedId) {
        this.damBreedId = damBreedId;
    }

    public String getDamBreedId() {
        return damBreedId;
    }

    public void setBreed2Id(String breed2Id) {
        this.breed2Id = breed2Id;
    }

    public String getBreed2Id() {
        return breed2Id;
    }

    public void setSireBreed2Id(String sireBreed2Id) {
        this.sireBreed2Id = sireBreed2Id;
    }

    public String getSireBreed2Id() {
        return sireBreed2Id;
    }

    public void setDamBreed2Id(String damBreed2Id) {
        this.damBreed2Id = damBreed2Id;
    }

    public String getDamBreed2Id() {
        return damBreed2Id;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public void setUpgradationDate(Date upgradationDate) {
        this.upgradationDate = upgradationDate;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public Date getUpgradationDate() {
        return upgradationDate;
    }

    public void setRegisterByUserId(String registerByUserId) {
        this.registerByUserId = registerByUserId;
    }

    public String getRegisterByUserId() {
        return registerByUserId;
    }
    
    public void setMembershipTypeId(String membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getMembershipTypeId() {
        return membershipTypeId;
    }

    public String getPrevTrainerId() {
        return prevTrainerId;
    }

    public void setPrevTrainerId(String prevTrainerId) {
        this.prevTrainerId = prevTrainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public String getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(String priorityValue) {
        this.priorityValue = priorityValue;
    }

    public String getMembershipAmount() {
        return membershipAmount;
    }

    public void setMembershipAmount(String membershipAmount) {
        this.membershipAmount = membershipAmount;
    }
}
