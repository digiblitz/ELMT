/*
 * ValidationVO.java
 *
 * Created on November 6, 2007, 3:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;
import java.util.Date;

/**
 *
 * @author Dhivya
 */
public class HLCValidationVO {
    
    /** Creates a new instance of ValidationVO */
    public HLCValidationVO() {
    }
    
        private String qualificationId;
        private String userTypeId;
        private String eventTypeId;
        private String eventLevelId;
        private String eventDivisionId;
        private String membTypeName;
        private boolean champStatus;
        private boolean amateurStatus;
        private int  minAge;
        private int  maxAge;
        private String eveLevelRank;
        private String qualicPeriod;
        private String minRank;
        private int minCount;
        private String maxRank;
        private String maxXcJmpenal;
        private String maxXcTimepenal;
        private Date addDate;
        private boolean activeStatus;
        private String modifiedBy;
        private String eventLevelNames;
        private String divisionName;
        private String areaId;
        private String memTypId;
        
  
     public String getUserTypeId() {
        return userTypeId;
    }

    public String getQualificationId() {
        return qualificationId;
    }
    
  public String getQualicPeriod() {
        return qualicPeriod;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

     public String getMinRank() {
        return minRank;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getMembTypeName() {
        return membTypeName;
    }

    public String getMaxXcTimepenal() {
        return maxXcTimepenal;
    }

    public String getMaxXcJmpenal() {
        return maxXcJmpenal;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public String getEventLevelId() {
        return eventLevelId;
    }

    public String getEventDivisionId() {
        return eventDivisionId;
    }

    public String getEveLevelRank() {
        return eveLevelRank;
    }

    public boolean getChampStatus() {
        return champStatus;
    }

    public boolean getAmateurStatus() {
        return amateurStatus;
    }

    public Date getAddDate() {
        return addDate;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }
    
     public String getEventLevelNames() {
        return eventLevelNames;
    }
     
    public String getDivisionName() {
        return divisionName;
    }
        
   //===================Setter Methods=====================
    
   
    

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setMembTypeName(String membTypeName) {
        this.membTypeName = membTypeName;
    }

    public void setMaxXcTimepenal(String maxXcTimepenal) {
        this.maxXcTimepenal = maxXcTimepenal;
    }

    public void setMaxXcJmpenal(String maxXcJmpenal) {
        this.maxXcJmpenal = maxXcJmpenal;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public void setEventLevelId(String eventLevelId) {
        this.eventLevelId = eventLevelId;
    }

    public void setEventDivisionId(String eventDivisionId) {
        this.eventDivisionId = eventDivisionId;
    }

    public void setEveLevelRank(String eveLevelRank) {
        this.eveLevelRank = eveLevelRank;
    }

    public void setChampStatus(boolean champStatus) {
        this.champStatus = champStatus;
    }

    public void setAmateurStatus(boolean amateurStatus) {
        this.amateurStatus = amateurStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
    
 public void setQualicPeriod(String qualicPeriod) {
        this.qualicPeriod = qualicPeriod;
    }
 
   public void setMinRank(String minRank) {
        this.minRank = minRank;
    }
    
     public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setEventLevelNames(String eventLevelNames) {
        this.eventLevelNames = eventLevelNames;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getMemTypId() {
        return memTypId;
    }

    public void setMemTypId(String memTypId) {
        this.memTypId = memTypId;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
