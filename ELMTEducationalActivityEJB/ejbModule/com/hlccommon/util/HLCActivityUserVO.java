/*
 * ActivityUserVO.java
 *
 * Created on September 6, 2006, 10:56 AM
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
public class HLCActivityUserVO implements java.io.Serializable {
    
    private String releaseId;
    private String activityMeetingId;
    private String userId;
    private String noOfHorses;
    private String eventLevelId;
    private boolean membershipStatus;
    private String memberId;
    private Date addDate;
    private boolean activeStatus;
    private String requestStatus;
    /** Creates a new instance of ActivityUserVO */
    public HLCActivityUserVO() {
    }
    
     public HLCActivityUserVO(String releaseId, String activityMeetingId, String userId,
	         String noOfHorses, String eventLevelId, boolean membershipStatus, String memberId, Date addDate,
	         boolean activeStatus,  String requestStatus){
        this.releaseId = releaseId;
        this.activityMeetingId =activityMeetingId;
        this.userId = userId;
        this.noOfHorses = noOfHorses;
        this.eventLevelId = eventLevelId;
        this.membershipStatus = membershipStatus;
        this.memberId = memberId;
        this.addDate = addDate;
        this.activeStatus = activeStatus;
        this.requestStatus = requestStatus;
   }
   public void setReleaseId(String releaseId){
         this.releaseId = releaseId;
    }
    
    public void setActivityMeetingId(String activityMeetingId){
         this.activityMeetingId = activityMeetingId;
    }
    
    public void setUserId(String userId){
         this.userId = userId;
    }
    
     public void setNoOfHorses(String noOfHorses){
         this.noOfHorses = noOfHorses;
    }
    
    public void setEventLevelId(String eventLevelId){
         this.eventLevelId = eventLevelId;
    }
    
    public void setMembershipStatus(boolean membershipStatus){
         this.membershipStatus = membershipStatus;
    }
     
    public void setMemberId(String memberId){
         this.memberId = memberId;
    }
    
    public void setAddDate(Date addDate){
         this.addDate = addDate;
    }
     
    public void setActiveStatus(boolean activeStatus){
         this.activeStatus = activeStatus;
    }
    
     public void setRequestStatus(String requestStatus){
         this.requestStatus = requestStatus;
    }
    
     
    public String getReleaseId(){
        return releaseId;
    }
    
    public String getActivityMeetingId(){
        return activityMeetingId;
    }
    
    public String getUserId(){
        return userId;
    }
    
     public String getNoOfHorses(){
        return noOfHorses;
    }
    
    public String getEventLevelId(){
         return eventLevelId;
    }
    
    public boolean isMembershipStatus(){
        return membershipStatus;
    }
     
    public String getMemberId(){
         return memberId;
    }
    
    public Date getAddDate(){
        return addDate;
    }
     
    public boolean isActiveStatus(){
        return activeStatus;
    }
    
     public String getRequestStatus(){
        return requestStatus;
    }
}
