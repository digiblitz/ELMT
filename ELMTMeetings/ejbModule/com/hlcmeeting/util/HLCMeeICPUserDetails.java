/*
 * MeeICPUserDetails.java
 *
 * Created on September 19, 2006, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

/**
 *
 * @author harmohan
 */
public class HLCMeeICPUserDetails implements java.io.Serializable {
    private String icpMeetingId;
    private String icpMeetingName;
    private String userId;
    private String membershipStatus;
    private String memberId;
    private String requestStatus;
    private String emailId;
    private String releaseId;
    private String comments;
    
    /** Creates a new instance of MeeICPUserDetails */
    public HLCMeeICPUserDetails() {
    }
    
    public HLCMeeICPUserDetails(String comments,String releaseId,String emailId,String icpMeetingId,String icpMeetingName,String userId,String membershipStatus,String memberId,String requestStatus) {
        
        this.icpMeetingId = icpMeetingId;
        this.icpMeetingName = icpMeetingName;
        this.userId = userId;
        this.membershipStatus = membershipStatus;
        this.memberId = memberId;
        this.requestStatus = requestStatus;
        this.emailId = emailId;
        this.releaseId = releaseId;
        this.comments = comments;
    }
    
    //Getter Method
     public String getReleaseId() { return releaseId;}
     public String getIcpMeetingId() { return icpMeetingId;}
     public String getIcpMeetingName() { return icpMeetingName;}
     public String getUserId() { return userId;}
     public String getMembershipStatus() { return membershipStatus;}
     public String getMemberId() { return memberId;}
     public String getRequestStatus() { return requestStatus;}
     public String getEmailId() { return emailId;}
     public String getComments() { return comments;  }
     
        
     //Setters methods
    public void setReleaseId(String releaseId) {this.releaseId = releaseId; }
    public void setIcpMeetingId(String icpMeetingId) {this.icpMeetingId = icpMeetingId; }
    public void setIcpMeetingName(String icpMeetingName) {this.icpMeetingName = icpMeetingName; }
    public void setUserId(String userId) {this.userId = userId; }
    public void setMembershipStatus(String membershipStatus) {this.membershipStatus = membershipStatus; }
    public void setMemberId(String memberId) {this.memberId = memberId; }
    public void setRequestStatus(String requestStatus) {this.requestStatus = requestStatus; }
    public void setEmailId(String emailId) {this.emailId = emailId; }
    public void setComments(String comments) { this.comments = comments;  }
    
}       
