/*
 * MapHorseRegVO.java
 *
 * Created on April 12, 2007, 10:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import java.math.*;
import java.util.*;
/**
 *
 * @author Punitha
 */
public class HLCMapHorseRegVO {
    
    /** Creates a new instance of MapHorseRegVO */
    public HLCMapHorseRegVO() {
    }
    
 
        private String eventId;
        private String eventType;
        private String eventLevel;
        private String eventDivisionAmateur;
        private String eventDivisionAge;
        private String eventDdivisionExperience;
        private String horseName;
        private String horseMemberId;
        private String horseUsefId;
        private String riderFirstName;
        private String riderLastName;
        
        private String riderMemberId;
        private String riderUsefId;
        private String horseUsaEq;
        private String riderUsaEq;
        private String ownerFirstName;
        private String ownerLastName;
        private String ownerUseaId;
        private String ownerUsefId;
        private long compResultId;
        private Date addDate;
        private boolean activeStatus;
        private String regUploadId;
	    
	    
            
            
	    public HLCMapHorseRegVO(String eventId, String eventType, String eventLevel, String eventDivisionAmateur, String eventDivisionAge,
                    String eventDivisionExperience,String horseName, String horseMemberId,String horseUsefId, String riderFirstName, 
                    String riderLastName,String riderMemberId,String riderUsefId, String horseUsaEq, String riderUsaEq,
                    String ownerFirstName,String ownerLastName,String ownerUseaId,String ownerUsefId,long compResultId,
                    Date addDate, boolean activeStatus, String regUploadId){
			super();
			 
			 
			this.eventId = eventId;
			this.eventType = eventType;
			this.eventLevel = eventLevel;
			this.eventDivisionAmateur = eventDivisionAmateur;
			this.eventDivisionAge = eventDivisionAge;
                        this.horseName = horseName;
			this.horseMemberId = horseMemberId;
                        this.horseUsefId = horseUsefId;
			this.riderFirstName = riderFirstName;
                        this.riderLastName = riderLastName;
			this.riderMemberId = riderMemberId;
                        this.riderUsefId = riderUsefId;
			this.horseUsaEq = horseUsaEq;
                        this.riderUsaEq = riderUsaEq;
                        this.ownerFirstName = ownerFirstName;
                        this.ownerLastName = ownerLastName;
                        this.ownerUseaId = ownerUseaId;
                        this.ownerUsefId = ownerUsefId;
                        
                        this.compResultId = compResultId;
			this.addDate = addDate;
                        this.activeStatus = activeStatus;
			this.regUploadId = regUploadId; 
		}
/*
 * setter methods 
 */
            
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setCompResultId(long compResultId) {
        this.compResultId = compResultId;
    }

    public void setEventDivisionAge(String eventDivisionAge) {
        this.eventDivisionAge = eventDivisionAge;
    }

    public void setEventDivisionAmateur(String eventDivisionAmateur) {
        this.eventDivisionAmateur = eventDivisionAmateur;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public void setHorseUsaEq(String horseUsaEq) {
        this.horseUsaEq = horseUsaEq;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setRiderFirstName(String riderFirstName) {
        this.riderFirstName = riderFirstName;
    }

    public void setRiderUsaEq(String riderUsaEq) {
        this.riderUsaEq = riderUsaEq;
    }
     public void setHorseUsefId(String horseUsefId) {
        this.horseUsefId = horseUsefId;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public void setOwnerUseaId(String ownerUseaId) {
        this.ownerUseaId = ownerUseaId;
    }

    public void setOwnerUsefId(String ownerUsefId) {
        this.ownerUsefId = ownerUsefId;
    }
     public void setRiderLastName(String riderLastName) {
        this.riderLastName = riderLastName;
    }
      public void setRiderUsefId(String riderUsefId) {
        this.riderUsefId = riderUsefId;
    }

/*
 * getter methods
 */
    public String getRiderMemberId() {
        return riderMemberId;
    }

    public String getRiderFirstName() {
        return riderFirstName;
    }

    public String getRiderUsaEq() {
        return riderUsaEq;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getHorseName() {
        return horseName;
    }

    public String getHorseUsaEq() {
        return horseUsaEq;
    }

    public Date getAddDate() {
        return addDate;
    }

    public long getCompResultId() {
        return compResultId;
    }

    public String getEventDivisionAge() {
        return eventDivisionAge;
    }

    public String getEventDivisionAmateur() {
        return eventDivisionAmateur;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public String getEventType() {
        return eventType;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }
	 public void setEventDdivisionExperience(String eventDdivisionExperience) {
        this.eventDdivisionExperience = eventDdivisionExperience;
    }
 
    public String getEventDdivisionExperience() {
        return eventDdivisionExperience;
    }
     public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public String getOwnerUseaId() {
        return ownerUseaId;
    }

    public String getOwnerUsefId() {
        return ownerUsefId;
    }

    public String getHorseUsefId() {
        return horseUsefId;
    }
    
    public String getRiderLastName() {
        return riderLastName;
    }

    public String getRiderUsefId() {
        return riderUsefId;
    }
             public String toString() {
                   StringBuffer sb = new StringBuffer();
                    sb.append("eventId :" + eventId  + "\n");
                    sb.append("eventLevel :" +  eventLevel + "\n");                    
                    sb.append("eventDivisionAmateur :" +eventDivisionAmateur + "\n");
                    sb.append("eventDivisionAge :" + eventDivisionAge  + "\n");
                    sb.append("horseName :" + horseName  + "\n");
                    sb.append("horseMemberId :" + horseMemberId  + "\n");
                    sb.append("riderFirstName :" +  riderFirstName + "\n");
                    sb.append("riderMemberId :" + riderMemberId  + "\n");
                    sb.append("horseUsaEq :" +  horseUsaEq + "\n");
                    sb.append("riderUsaEq :" +  riderUsaEq + "\n");
                    sb.append("compResultId :" + compResultId  + "\n");
                    sb.append("addDate :" +  addDate + "\n");
                    sb.append("activeStatus :" + activeStatus  + "\n");
                    sb.append("ownerFirstName :" +  ownerFirstName + "\n");
                    sb.append("ownerLastName :" +  ownerLastName + "\n");
                    sb.append("ownerUsefId :" +  ownerUsefId + "\n");
                    sb.append("ownerUseaId :" +  ownerUseaId + "\n");
                    sb.append("horseUsefId :" + horseUsefId  + "\n");
                    sb.append("riderLastName :" +  riderLastName + "\n");
                    sb.append("riderUsefId :" + riderUsefId  + "\n");
                    return sb.toString();
 	}

    public String getRegUploadId() {
        return regUploadId;
    }

    public void setRegUploadId(String regUploadId) {
        this.regUploadId = regUploadId;
    }

   
   

   
  

 
 

   

   

    

   
		

    
}