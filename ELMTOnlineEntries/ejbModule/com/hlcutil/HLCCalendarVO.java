/*
 * CalendarVO.java
 *
 * Created on October 29, 2007, 1:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Dhivya
 */
public class HLCCalendarVO {
    
    /** Creates a new instance of CalendarVO */
    public HLCCalendarVO() {
    }
  private String calenderId;
    private String eventId;
    private String organizerId;
    private String eventTitle;
    private Date beginDate;
    private int noDays;
    private Date endDate;
    private String areaId;
    private String stateId;
    private String eventDesc;
    private String eventTypeId;
    private String eventLevel;
    private int compYear;
    private Date addDate;
    private Boolean renewStat;
    private String approvalStatus;
    private String orgApprovalStatus;
    private String comments;
    private String arearChairApproStatus;
    private String areaChairCommt;
    private Date modifyDate;
    private String modifyBy;
    private String ipAddress;
    private Boolean activeStatus;
    private String staffComments;
    private String eventLevelId;
    private String stateName;
    private ArrayList eventList;
    private String areaName;
    private String orgComments;
    private String areaCode;
    private String status;
    private String eventTypeNames;
    private Date entryStrtDate;
    private Date entryEndDate;
    private Date extDueDate;
    private String chmpLevel;
    private String userId;
    private String orgFName;
    private String orgLName;
    private String oldEventId;
    private String eveLocation;
    private String eveCity;
    private String eveZip;
    private String eveIssueId;
    private boolean resStatus;
    private String eveSecreId;
    private ArrayList eveLevels;
    
    
//=====================================GetterMethods()===================================
    
  

    public ArrayList getEveLevels() {
        return eveLevels;
    }

    public void setEveLevels(ArrayList eveLevels) {
        this.eveLevels = eveLevels;
    }
    public String getEveSecreId() {
        return eveSecreId;
    }

    public void setEveSecreId(String eveSecreId) {
        this.eveSecreId = eveSecreId;
    }
    public boolean isResStatus() {
        return resStatus;
    }

   
    public String getOldEventId() {
        return oldEventId;
    }
      
    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public Date getAddDate() {
        return addDate;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public String getAreaChairCommt() {
        return areaChairCommt;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getArearChairApproStatus() {
        return arearChairApproStatus;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public String getCalenderId() {
        return calenderId;
    }

    public String getComments() {
        return comments;
    }

    public int getCompYear() {
        return compYear;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public ArrayList getEventList() {
        return eventList;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public int getNoDays() {
        return noDays;
    }

    public String getOrgApprovalStatus() {
        return orgApprovalStatus;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public Boolean getRenewStat() {
        return renewStat;
    }

    public String getStaffComments() {
        return staffComments;
    }

    public String getStateId() {
        return stateId;
    }
  public String getEventLevelId() {
        return eventLevelId;
    }
   
 //=================================setterMethods()====================

 
    public void setResStatus(boolean resStatus) {
        this.resStatus = resStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setAreaChairCommt(String areaChairCommt) {
        this.areaChairCommt = areaChairCommt;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setArearChairApproStatus(String arearChairApproStatus) {
        this.arearChairApproStatus = arearChairApproStatus;
    }
    
   public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setCalenderId(String calenderId) {
        this.calenderId = calenderId;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCompYear(int compYear) {
        this.compYear = compYear;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public void setEventList(ArrayList eventList) {
        this.eventList = eventList;
    }

   public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setNoDays(int noDays) {
        this.noDays = noDays;
    }

    public void setOrgApprovalStatus(String orgApprovalStatus) {
        this.orgApprovalStatus = orgApprovalStatus;
    }

   public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

   public void setRenewStat(Boolean renewStat) {
        this.renewStat = renewStat;
    }

    public void setStaffComments(String staffComments) {
        this.staffComments = staffComments;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }
    
     public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setEventLevelId(String eventLevelId) {
        this.eventLevelId = eventLevelId;
    }    

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getOrgComments() {
        return orgComments;
    }

    public void setOrgComments(String orgComments) {
        this.orgComments = orgComments;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getEventTypeNames() {
        return eventTypeNames;
    }

    public void setEventTypeNames(String eventTypeNames) {
        this.eventTypeNames = eventTypeNames;
    }

    public Date getExtDueDate() {
        return extDueDate;
    }

    public Date getEntryEndDate() {
        return entryEndDate;
    }

    public Date getEntryStrtDate() {
        return entryStrtDate;
    }
   

    public void setEntryEndDate(Date entryEndDate) {
        this.entryEndDate = entryEndDate;
    }
    

    public void setEntryStrtDate(Date entryStrtDate) {
        this.entryStrtDate = entryStrtDate;
    }

    public void setExtDueDate(Date extDueDate) {
        this.extDueDate = extDueDate;
    }

    public String getChmpLevel() {
        return chmpLevel;
    }
    
     public void setChmpLevel(String chmpLevel) {
        this.chmpLevel = chmpLevel;
    }

    public void setOrgFName(String orgFName) {
        this.orgFName = orgFName;
    }

    public String getOrgFName() {
        return orgFName;
    }

    public void setOrgLName(String orgLName) {
        this.orgLName = orgLName;
    }

    public String getOrgLName() {
        return orgLName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
    public void setOldEventId(String oldEventId) {
        this.oldEventId = oldEventId;
    }

    public void setEveCity(String eveCity) {
        this.eveCity = eveCity;
    }

    public void setEveIssueId(String eveIssueId) {
        this.eveIssueId = eveIssueId;
    }

    public void setEveLocation(String eveLocation) {
        this.eveLocation = eveLocation;
    }

    public void setEveZip(String eveZip) {
        this.eveZip = eveZip;
    }

    public String getEveCity() {
        return eveCity;
    }

    public String getEveIssueId() {
        return eveIssueId;
    }

    public String getEveLocation() {
        return eveLocation;
    }

    public String getEveZip() {
        return eveZip;
    }
   
}
