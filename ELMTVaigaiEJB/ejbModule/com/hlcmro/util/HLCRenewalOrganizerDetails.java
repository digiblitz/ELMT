/*
 * RenewalOrganizerDetails.java
 *
 * Created on August 18, 2006, 6:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author suresh
 */
public class HLCRenewalOrganizerDetails implements Serializable{
        private String renewalId;
        private int eventId;
        private String organizerId;
        private String competitionName;
        
        private Date competitionDate;
      
        private String comManagementName;
        private String comManagementAddress;
         private String comManagementAddress2;
        private String comManagementCity;
        private String comManagementState;
        private String comManagementCountry;
        private String comManagementZip;
        private String comManagementPhone;
        private String comManagementFax;
        private String managerUsefMemberId;
        private String managerUseaMemberId;
        private String managerName;
        private String secretaryUsefMemberId;
        private String secretaryName;
         private String requestStat;
         private String comManagementEmail;
        
         public HLCRenewalOrganizerDetails(){
         }
   
        public HLCRenewalOrganizerDetails(String renewalId, int eventId,String organizerId,String competitionName,
             Date competitionDate, String comManagementName, String comManagementAddress,String comManagementAddress2, String comManagementCity,String comManagementState,
            String comManagementCountry,String comManagementZip,String comManagementPhone, String comManagementFax,String comManagementEmail,String managerUsefMemberId,String managerUseaMemberId,
            String managerName, String secretaryUsefMemberId, String secretaryName){
           
            this.renewalId = renewalId;
            this.eventId = eventId;
            this.organizerId = organizerId;
            this.competitionName = competitionName;
            this.competitionDate = competitionDate;
            this.comManagementName = comManagementName;
            this.comManagementAddress = comManagementAddress;
            this.comManagementAddress2 = comManagementAddress2;
            this.comManagementCity = comManagementCity;
            this.comManagementState = comManagementState;
            this.comManagementCountry = comManagementCountry;
            this.comManagementZip = comManagementZip;
            this.comManagementPhone = comManagementPhone;
            this.comManagementFax = comManagementFax;
            this.managerUsefMemberId = managerUsefMemberId;
            this.managerUseaMemberId = managerUseaMemberId;
            this.managerName = managerName;
            this.secretaryUsefMemberId = secretaryUsefMemberId;
            this.secretaryName = secretaryName;
        }

        // getters
        public String getRenewalId() {
           return renewalId;
        }
        public int getEventId() {
          return eventId;
        }
        public String getOrganizerId() {
          return organizerId;
        }
        public String getCompetitionName() {
           return competitionName;
        }
      
        
        public Date getCompetitionDate() {
            return competitionDate;
        }
               
        public String getComManagementName() {
            return comManagementName;
        }
        public String getComManagementAddress() {
            return comManagementAddress;
        }
        public String getComManagementCity() {
            return comManagementCity;
        }
        public String getComManagementState() {
            return comManagementState;
        }
        public String getComManagementCountry() {
            return comManagementCountry;
        }
        public String getComManagementZip() {
            return comManagementZip;
        }
        
         public String getComManagementPhone() {
            return comManagementPhone;
        }
         
        public String getComManagementFax() {
            return comManagementFax;
        }
      
            
        
        public String getManagerUsefMemberId() {
            return managerUsefMemberId;
        }
        public String getManagerUseaMemberId() {
            return managerUseaMemberId;
        }

        public String getManagerName() {
            return managerName;
        }
        
        public String getSecretaryUsefMemberId() {
            return secretaryUsefMemberId;
        }
        
        public String getSecretaryName() {
            return secretaryName;
        }
   
    //Setters methods
        public void setRenewalId(String renewalId) {
            this.renewalId = renewalId;
        }
        public void setEventId(int eventId) {
            this.eventId = eventId;
        }
        public void setOrganizerId(String organizerId) {
            this.organizerId = organizerId;
        }
        public void setCompetitionName(String competitionName) {
            this.competitionName = competitionName;
        }
       
        
        public void setCompetitionDate(Date competitionDate) {
            this.competitionDate = competitionDate;
        }
        
       
        public void setComManagementName(String comManagementName) {
            this.comManagementName = comManagementName;
        }
        public void setComManagementAddress(String comManagementAddress) {
            this.comManagementAddress = comManagementAddress;
        }
        public void setComManagementCity(String comManagementCity) {
            this.comManagementCity = comManagementCity;
        }
        public void setComManagementState(String comManagementState) {
            this.comManagementState = comManagementState;
        }
        public void setComManagementCountry(String comManagementCountry) {
            this.comManagementCountry = comManagementCountry;
        }
        public void setComManagementZip(String comManagementZip) {
            this.comManagementZip = comManagementZip;
        }
        
        public void setComManagementPhone(String comManagementPhone) {
            this.comManagementPhone = comManagementPhone ;
        }
         
        public void setComManagementFax(String comManagementFax) {
            this.comManagementFax = comManagementFax ;
        }
     
        public void setManagerUsefMemberId(String managerUsefMemberId) {
            this.managerUsefMemberId = managerUsefMemberId;
        }
        public void setManagerUseaMemberId(String managerUseaMemberId) {
            this.managerUseaMemberId = managerUseaMemberId;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }
        
        public void setSecretaryUsefMemberId(String secretaryUsefMemberId) {
            this.secretaryUsefMemberId = secretaryUsefMemberId;
        }
        
        public void setSecretaryName(String secretaryName) {
            this.secretaryName = secretaryName;
        }
         public String getComManagementAddress2() {
        return comManagementAddress2;
    }

    public void setComManagementAddress2(String comManagementAddress2) {
        this.comManagementAddress2 = comManagementAddress2;
    }

    public void setRequestStat(String requestStat) {
        this.requestStat = requestStat;
    }

    public String getRequestStat() {
        return requestStat;
    }
        public String getComManagementEmail() {
        return comManagementEmail;
    }

        public void setComManagementEmail(String comManagementEmail) {
        this.comManagementEmail = comManagementEmail;
    }
}
