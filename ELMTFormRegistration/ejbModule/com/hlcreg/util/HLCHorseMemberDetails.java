/*
 * HorseMemberDetails.java
 *
 * Created on August 21, 2006, 11:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcreg.util;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author harmohan
 */
public class HLCHorseMemberDetails implements Serializable {
    
    private String memberId;
    private String userId;
    private String horseMemberId;
    private String horseMembershipTypeId;
    private String horseServiceTypeId;
    private String competitionName;
    private String registeredName;
    private String baRegisteredName;
    private String baPastName;
    private String riderMemberId;
    private String prevRiderMemberId;
    private String addRiderMemberId;
    private String ownerId;
    private String prevOwnerName;
    private String addOwnerName;
    private long statusId;
    private Date expiryDate;
    private String ownerEmail;
    private String loginName;
    
    /** Creates a new instance of HorseMemberDetails */
    public HLCHorseMemberDetails() {
    }
    
    public HLCHorseMemberDetails(String ownerEmail,String userId,String horseMemberId, String horseMembershipTypeId, 
            String horseServiceTypeId, String competitionName, String registeredName, String baRegisteredName, 
            String baPastName, String riderMemberId, String prevRiderMemberId, String addRiderMemberId, 
            String ownerId, String prevOwnerName, String addOwnerName, Date expiryDat, String loginName) {
        
        this.horseMemberId = horseMemberId;
        this.userId = userId;
        this.horseMembershipTypeId = horseMembershipTypeId;
        this.horseServiceTypeId = horseServiceTypeId;
        this.competitionName = competitionName;
        this.registeredName = registeredName;
        this.baRegisteredName = baRegisteredName;
        this.baPastName = baPastName;
        this.riderMemberId = riderMemberId;
        this.addRiderMemberId = addRiderMemberId;
        this.prevRiderMemberId = prevRiderMemberId;
        this.ownerId = ownerId;
        this.prevOwnerName = prevOwnerName;
        this.addOwnerName = addOwnerName;
        this.expiryDate = expiryDate;
        this.ownerEmail = ownerEmail;
        this.loginName = loginName;
        
    }
    
        // getters methods
        public String getOwnerEmail() {
            return ownerEmail;
        }
        public String getHorseMemberId() {
          return horseMemberId;
        }
         public String getHorseMembershipTypeId() {
           return horseMembershipTypeId;
        }
        public String getHorseServiceTypeId() {
          return horseServiceTypeId;
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
        public String getAddRiderMemberId() {
          return addRiderMemberId;
        }
         public String getPrevRiderMemberId() {
           return prevRiderMemberId;
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
        public Date getExpiryDate() {
          return expiryDate;
        }
        public String getUserId() {
            return userId;
        }
        
        //Setters methods
        public void setOwnerEmail(String ownerEmail){
            this.ownerEmail = ownerEmail;
        }
        public void setHorseMemberId(String horseMemberId) {
          this.horseMemberId = horseMemberId;
        }
        public void setUserId(String userId) { this.userId = userId; }
        public void setHorseMembershipTypeId(String horseMembershipTypeId) {
           this.horseMembershipTypeId = horseMembershipTypeId;
        }
        public void setHorseServiceTypeId(String horseServiceTypeId) {
          this.horseServiceTypeId = horseServiceTypeId;
        }
         public void setCompetitionName(String competitionName) {
          this.competitionName =  competitionName;
        }
        public void setRegisteredName(String registeredName) {
          this.registeredName =  registeredName;
        }
         public void setBaRegisteredName(String baRegisteredName) {
           this.baRegisteredName = baRegisteredName;
        }
        public void setBaPastName(String baPastName) {
          this.baPastName =  baPastName;
        }
          public void setRiderMemberId(String riderMemberId) {
           this.riderMemberId =  riderMemberId;
        }
        public void setAddRiderMemberId(String addRiderMemberId) {
          this.addRiderMemberId = addRiderMemberId;
        }
         public void setPrevRiderMemberId(String prevRiderMemberId) {
           this.prevRiderMemberId = prevRiderMemberId;
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
        public void setExpiryDate(Date expiryDate) {
          this.expiryDate = expiryDate;
        }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return loginName;
    }
        
        
}
