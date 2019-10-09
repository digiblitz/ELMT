/*
 * CompResultVO.java
 *
 * Created on November 28, 2007, 2:10 PM
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
public class HLCCompetitionResultVO {
    
    /** Creates a new instance of CompResultVO */
    public HLCCompetitionResultVO() {
     
    }
    
        private String compResultId;
        private String tempResultId;
        private String eventId;
        private String eventDivId;
        private String eventSubDivision;
        private String eventTypeId;
        private String horseName;
        private String horseMemberId;
        private String riderFirstName;
        private String riderLastName;
        private String riderMemberId;
        private String pinneyNumber;
        private String dressagePenal;
        private String dressagePlace;
        private String xc_phase_jmpPenal;
        private String xc_phase_elapsTime;
        private String xc_phase_timePenal;
        private String show_jmp_timePenal;
        private String show_jmp_jmpPenal;
        private String toDatePts;
        private String toDatePlace;
        private String dangerRidPenal;
        private String finalPoints;
        private String finalPlace;
        private String firstInspec;
        private String lastInspec;
        private String phase_DInspec;
        private String rdTrackA;
        private String rdTrackC;
        private String steepleCh_jmpPenal;
        private String steepleCh_timePenal;
        private String useaPoints;
        private Date addDate;
        private boolean activeStatus;
        private boolean expectationStatus;
        private String eventLevelId;
        private String riderUserId;
        private int compYear;
        
        
       
     //=====================================Setter Methods====================================================================
    
    
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setCompResultId(String compResultId) {
        this.compResultId = compResultId;
    }

    public void setDangerRidPenal(String dangerRidPenal) {
        this.dangerRidPenal = dangerRidPenal;
    }

    public void setDressagePenal(String dressagePenal) {
        this.dressagePenal = dressagePenal;
    }

    public void setDressagePlace(String dressagePlace) {
        this.dressagePlace = dressagePlace;
    }

    public void setEventDivId(String eventDivId) {
        this.eventDivId = eventDivId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventSubDivision(String eventSubDivision) {
        this.eventSubDivision = eventSubDivision;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public void setExpectationStatus(boolean expectationStatus) {
        this.expectationStatus = expectationStatus;
    }

    public void setFinalPlace(String finalPlace) {
        this.finalPlace = finalPlace;
    }

    public void setFinalPoints(String finalPoints) {
        this.finalPoints = finalPoints;
    }

    public void setFirstInspec(String firstInspec) {
        this.firstInspec = firstInspec;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }
    
    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setLastInspec(String lastInspec) {
        this.lastInspec = lastInspec;
    }

    public void setPhase_DInspec(String phase_DInspec) {
        this.phase_DInspec = phase_DInspec;
    }

    public void setPinneyNumber(String pinneyNumber) {
        this.pinneyNumber = pinneyNumber;
    }

    public void setRdTrackA(String rdTrackA) {
        this.rdTrackA = rdTrackA;
    }

    public void setRdTrackC(String rdTrackC) {
        this.rdTrackC = rdTrackC;
    }

    public void setRiderFirstName(String riderFirstName) {
        this.riderFirstName = riderFirstName;
    }

    public void setRiderLastName(String riderLastName) {
        this.riderLastName = riderLastName;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setShow_jmp_jmpPenal(String show_jmp_jmpPenal) {
        this.show_jmp_jmpPenal = show_jmp_jmpPenal;
    }

    public void setShow_jmp_timePenal(String show_jmp_timePenal) {
        this.show_jmp_timePenal = show_jmp_timePenal;
    }

    public void setSteepleCh_jmpPenal(String steepleCh_jmpPenal) {
        this.steepleCh_jmpPenal = steepleCh_jmpPenal;
    }

    public void setSteepleCh_timePenal(String steepleCh_timePenal) {
        this.steepleCh_timePenal = steepleCh_timePenal;
    }

    public void setTempResultId(String tempResultId) {
        this.tempResultId = tempResultId;
    }

    public void setToDatePlace(String toDatePlace) {
        this.toDatePlace = toDatePlace;
    }

    public void setToDatePts(String toDatePts) {
        this.toDatePts = toDatePts;
    }

    public void setUseaPoints(String useaPoints) {
        this.useaPoints = useaPoints;
    }

    public void setXc_phase_elapsTime(String xc_phase_elapsTime) {
        this.xc_phase_elapsTime = xc_phase_elapsTime;
    }

    public void setXc_phase_jmpPenal(String xc_phase_jmpPenal) {
        this.xc_phase_jmpPenal = xc_phase_jmpPenal;
    }

    public void setXc_phase_timePenal(String xc_phase_timePenal) {
        this.xc_phase_timePenal = xc_phase_timePenal;
    }
        
        
   //====================================Getter Methods===================================================================
    
   

    public Date getAddDate() {
        return addDate;
    }

    public String getCompResultId() {
        return compResultId;
    }

    public String getDangerRidPenal() {
        return dangerRidPenal;
    }

    public String getDressagePenal() {
        return dressagePenal;
    }

    public String getDressagePlace() {
        return dressagePlace;
    }

    public String getEventDivId() {
        return eventDivId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventSubDivision() {
        return eventSubDivision;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public String getFinalPlace() {
        return finalPlace;
    }

    public String getFinalPoints() {
        return finalPoints;
    }

    public String getFirstInspec() {
        return firstInspec;
    }

    public String getHorseName() {
        return horseName;
    }

    public String getLastInspec() {
        return lastInspec;
    }

    public String getPhase_DInspec() {
        return phase_DInspec;
    }

    public String getPinneyNumber() {
        return pinneyNumber;
    }

    public String getRdTrackA() {
        return rdTrackA;
    }

    public String getRdTrackC() {
        return rdTrackC;
    }

    public String getRiderFirstName() {
        return riderFirstName;
    }

    public String getRiderLastName() {
        return riderLastName;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public String getShow_jmp_jmpPenal() {
        return show_jmp_jmpPenal;
    }

    public String getShow_jmp_timePenal() {
        return show_jmp_timePenal;
    }

    public String getSteepleCh_jmpPenal() {
        return steepleCh_jmpPenal;
    }

    public String getSteepleCh_timePenal() {
        return steepleCh_timePenal;
    }

    public String getTempResultId() {
        return tempResultId;
    }

    public String getToDatePlace() {
        return toDatePlace;
    }

    public String getToDatePts() {
        return toDatePts;
    }

    public String getUseaPoints() {
        return useaPoints;
    }

    public String getXc_phase_elapsTime() {
        return xc_phase_elapsTime;
    }

    public String getXc_phase_jmpPenal() {
        return xc_phase_jmpPenal;
    }

    public String getXc_phase_timePenal() {
        return xc_phase_timePenal;
    }
        
     public boolean isActiveStatus() {
        return activeStatus;
    }
        
    public boolean isExpectationStatus() {
        return expectationStatus;
    }       
   
    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getEventLevelId() {
        return eventLevelId;
    }

    public void setEventLevelId(String eventLevelId) {
        this.eventLevelId = eventLevelId;
    }
    
      public void setRiderUserId(String riderUserId) {
        this.riderUserId = riderUserId;
    }
          
    public String getRiderUserId() {
        return riderUserId;
    }

    public int getCompYear() {
        return compYear;
    }

    public void setCompYear(int compYear) {
        this.compYear = compYear;
    }
}
