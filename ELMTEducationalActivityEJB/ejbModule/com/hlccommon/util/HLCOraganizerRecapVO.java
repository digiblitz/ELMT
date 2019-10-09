/*
 * OraganizerRecapVO.java
 *
 * Created on September 20, 2006, 5:33 PM
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
public class HLCOraganizerRecapVO implements java.io.Serializable {
    
    
     public HLCOraganizerRecapVO(){

     }

    private String recapId;
    private String activityMeetingId;
    private boolean activityDateChangeStatus;
    private String activityOrganizerId;
    private int noOfRiders;
    private int noOfInstructors;
    private int noOfCurrentMembers;
    private int noOfNewMembers;
    private int noOfRenewingMembers;
    private int noOfFullMembers;
    private int noOfJuniorMembers;
    private int noOfNonCompetingMembers;
    private Date activityReportDate;
    private float totalAmount;
    private Date closeDate;
    private String comments;
    private boolean publishComments;
    private String suggestions;
    private Date addDate;
    private boolean activeStatus;
    private String requestStatus;
    
    
    
     public HLCOraganizerRecapVO(String recapId, String activityMeetingId, boolean activityDateChangeStatus, String activityOrganizerId, 
            int noOfRiders, int noOfInstructors, int noOfCurrentMembers, int noOfNewMembers, int noOfRenewingMembers, int noOfFullMembers, 
            int noOfJuniorMembers, int noOfNonCompetingMembers, Date activityReportDate, float totalAmount, Date closeDate,
            String comments, boolean publishComments, String suggestions,  Date addDate, boolean activeStatus, String requestStatus ){
           
            this.recapId = recapId;
            this.activityMeetingId = activityMeetingId;
            this.activityDateChangeStatus = activityDateChangeStatus;
            this.activityOrganizerId = activityOrganizerId;
            this.noOfRiders = noOfRiders;
            this.noOfInstructors = noOfInstructors;
            this.noOfCurrentMembers = noOfCurrentMembers;
            this.noOfNewMembers = noOfNewMembers;
            this.noOfRenewingMembers = noOfRenewingMembers;
            this.noOfFullMembers = noOfFullMembers;
            this.noOfJuniorMembers = noOfJuniorMembers;
            this.noOfNonCompetingMembers = noOfNonCompetingMembers;
            this.activityReportDate = activityReportDate;
            this.totalAmount = totalAmount;
            this.closeDate = closeDate;
            this.comments = comments;
            this.publishComments = publishComments;
            this.suggestions = suggestions;
            this.addDate = addDate;
            this.activeStatus = activeStatus;
            this.requestStatus = requestStatus;
    }
    

    //Setter
    public void setRecapId(String recapId){
         this.recapId = recapId;
    }
    public void setActivityMeetingId(String activityMeetingId){
         this.activityMeetingId = activityMeetingId;
    }
    public void setActivityDateChangeStatus(boolean activityDateChangeStatus){
         this.activityDateChangeStatus = activityDateChangeStatus;
    }
    public void setActivityOrganizerId(String activityOrganizerId){
         this.activityOrganizerId = activityOrganizerId;
    }
    public void setNoOfRiders(int noOfRiders){
         this.noOfRiders = noOfRiders;
    }
    public void setNoOfInstructors(int noOfInstructors){
         this.noOfInstructors = noOfInstructors;
    }
    public void setNoOfCurrentMembers(int noOfCurrentMembers){
         this.noOfCurrentMembers = noOfCurrentMembers;
    }
    public void setNoOfNewMembers(int noOfNewMembers){
         this.noOfNewMembers = noOfNewMembers;
    }
    public void setNoOfRenewingMembers(int noOfRenewingMembers){
         this.noOfRenewingMembers = noOfRenewingMembers;
    }
    public void setNoOfFullMembers(int noOfFullMembers){
         this.noOfFullMembers = noOfFullMembers;
    }
    public void setNoOfJuniorMembers(int noOfJuniorMembers){
         this.noOfJuniorMembers = noOfJuniorMembers;
    }
    public void setNoOfNonCompetingMembers(int noOfNonCompetingMembers){
         this.noOfNonCompetingMembers = noOfNonCompetingMembers;
    }
    public void setActivityReportDate(Date activityReportDate){
    this.activityReportDate = activityReportDate;
    }
    public void setTotalAmount(float totalAmount){
    this.totalAmount = totalAmount;
    }
    public void setCloseDate(Date closeDate){
    this.closeDate = closeDate;
    }
    public void setComments(String comments){
    this.comments = comments;
    }
    public void setPublishComments(boolean publishComments){
    this.publishComments = publishComments;
    }
    public void setSuggestions(String suggestions){
    this.suggestions = suggestions;
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
    
    //Getter
     
    public String getRecapId(){
        return recapId;
    }
    
    public String getActivityMeetingId(){
       return activityMeetingId;
    }
    
    public boolean isActivityDateChangeStatus(){
        return activityDateChangeStatus;
    }
    
    public String getActivityOrganizerId(){
        return activityOrganizerId;
    }
    
    public int getNoOfRiders(){
        return noOfRiders;
    }
    
    public int getNoOfInstructors(){
        return noOfInstructors;
    }
    
    public int getNoOfCurrentMembers(){
      return noOfCurrentMembers;
    }
    
    public int getNoOfNewMembers(){
        return noOfNewMembers;
    }
    
    public int getNoOfRenewingMembers(){
        return noOfRenewingMembers;
    }
    
    public int getNoOfFullMembers(){
        return noOfFullMembers;
    }
    
    public int getNoOfJuniorMembers(){
        return noOfJuniorMembers;
    }
    
    public int getNoOfNonCompetingMembers(){
        return noOfNonCompetingMembers;
    }
    
    public Date getActivityReportDate(){
        return activityReportDate;
    }
    
    public float getTotalAmount(){
        return totalAmount;
    }
    
    public Date getCloseDate(){
        return closeDate;
    }
    
    public String getComments(){
        return comments;
    }
    
    public boolean isPublishComments(){
        return publishComments;
    }
    
    public String getSuggestions(){
        return suggestions;
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
