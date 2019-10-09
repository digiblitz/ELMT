/*
 * SponsorDetails.java
 *
 * Created on August 22, 2006, 2:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.util.*;
import java.io.*;

/**
 *
 * @author suresh
 */
public class HLCSponsorDetails implements Serializable {
    
    /** Creates a new instance of SponsorDetails */
    public HLCSponsorDetails() {
    }
    
    private String sponsorId;
    private String userId;
    private String companyName;
    private String comments;
    private String planId;
    private String statusId;
    private String salesPersonId;
    private Date contractStartDate;
    private Date contractEndDate;
    private String sponsorAmount;
    private String requestStatus;
    
    
    public HLCSponsorDetails(String sponsorId,String userId,String companyName,String comments,
            String planId, String statusId, String salesPersonId ,Date contractStartDate , Date contractEndDate , String sponsorAmount , String requestStatus) {

        this.sponsorId = sponsorId;
        this.userId = userId;
        this.companyName = companyName;
        this.comments = comments;
        this.planId = planId;
        this.statusId = statusId;
        this.salesPersonId = salesPersonId;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.sponsorAmount = sponsorAmount;
        this.requestStatus = requestStatus;
    }
    
      // getters
        public String getSponsorId() {
            return sponsorId;
        }
        public String getUserId() {
            return userId;
        }
        public String getCompanyName() {
            return companyName;
        }
        public String getComments() {
            return comments;
        }
        public String getPlanId() {
            return planId;
        }
        public String getStatusId() {
            return statusId;
        }
        public String getSalesPersonId() {
            return salesPersonId;
        }
        public Date getContractStartDate() {
            return contractStartDate;
        }
        public Date getcontractEndDate() {
            return contractEndDate;
        }
        public String getSponsorAmount() {
            return sponsorAmount;
        }
        public String getRequestStatus() {
            return requestStatus;
        }
        
        
        
       // setters   
        public void setSponsorId(String sponsorId) {
            this.sponsorId = sponsorId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
        public void setComments(String comments) {
            this.comments = comments;
        }
        public void setPlanId(String planId) {
            this.planId = planId;
        }
        public void setStatusId(String statusId) {
            this.statusId = statusId;
        }
        public void setSalesPersonId(String salesPersonId) {
            this.salesPersonId = salesPersonId;
        }
        public void setContractStartDate(Date contractStartDate) {
            this.contractStartDate = contractStartDate;
        }
        public void setcontractEndDate(Date contractEndDate) {
            this.contractEndDate = contractEndDate;
        }
        public void setSponsorAmount(String sponsorAmount) {
            this.sponsorAmount = sponsorAmount;
        }
        public void setRequestStatus(String requestStatus) {
           this.requestStatus = requestStatus;
        }
}
