/*
 * OtherDetailsVO.java
 *
 * Created on August 30, 2006, 6:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;
import java.util.Date;

/**
 *
 * @author suresh
 */
public class HLCOtherDetailsVO implements java.io.Serializable {
        private String otherId;
        private String eventId;
        private Date courseCloseDate;
        private String entryLimit;
        private String ridersHorseLevelLimit;
        private String ridersHorseEntireLimit;
        private Date divisionEntryBirthDate;
        private boolean leaseDogs;
        private String teamCompetition;
        private String perTimePrice;
        private String otherTeamInfo;
        private String partyName;
        private String addInfo;

        public HLCOtherDetailsVO() { }
        
        public HLCOtherDetailsVO(String otherId,String eventId,Date courseCloseDate,String entryLimit,
                            String ridersHorseLevelLimit,String ridersHorseEntireLimit,Date divisionEntryBirthDate,
                            boolean leaseDogs,String teamCompetition,String perTimePrice,String otherTeamInfo,
                            String partyName,String addInfo) {
            this.otherId = otherId;
            this.eventId = eventId;
            this.courseCloseDate = courseCloseDate;
            this.entryLimit = entryLimit;
            this.ridersHorseLevelLimit = ridersHorseLevelLimit;
            this.ridersHorseEntireLimit = ridersHorseEntireLimit;
            this.divisionEntryBirthDate = divisionEntryBirthDate;
            this.leaseDogs = leaseDogs;
            this.teamCompetition = teamCompetition ;
            this.perTimePrice = perTimePrice;
            this.otherTeamInfo = otherTeamInfo;
            this.partyName = partyName;
            this.addInfo = addInfo;
        }


        //getter
        public String getOtherId() {
            return otherId;
        }
        public String getEventId() {
            return eventId;
        }
        public Date getCourseCloseDate() {
            return courseCloseDate;
        }
        public String getEntryLimit() {
            return entryLimit;
        }
        public String getRidersHorseLevelLimit() {
            return ridersHorseLevelLimit;
        }
        public String getRidersHorseEntireLimit() {
            return ridersHorseEntireLimit;
        }
        public Date getDivisionEntryBirthDate() {
            return divisionEntryBirthDate;
        }
        public boolean isLeaseDogs() {
            return leaseDogs;
        }
        public String getTeamCompetition() {
            return teamCompetition;
        }
        public String getPerTimePrice() {
            return perTimePrice;
        }
        public String getOtherTeamInfo() {
            return otherTeamInfo;
        }
        public String getPartyName() {
            return partyName;
        }
        public String getAddInfo() {
            return addInfo;
        }

        //Setters methods

        public void setOtherId(String otherId) {
            this.otherId = otherId;
        }
        public void setEventId(String eventId) 	{
            this.eventId = eventId;
        }
        public void setCourseCloseDate(Date courseCloseDate) {
            this.courseCloseDate = courseCloseDate;
        }
        public void setEntryLimit(String entryLimit){
            this.entryLimit = entryLimit;
        }
        public void setRidersHorseLevelLimit(String ridersHorseLevelLimit) {
            this.ridersHorseLevelLimit = ridersHorseLevelLimit;
        }
        public void setRidersHorseEntireLimit(String ridersHorseEntireLimit) {
            this.ridersHorseEntireLimit = ridersHorseEntireLimit;
        }
        public void setDivisionEntryBirthDate(Date divisionEntryBirthDate) {
            this.divisionEntryBirthDate = divisionEntryBirthDate ;
        }
        public void setLeaseDogs(boolean leaseDogs) {
            this.leaseDogs = leaseDogs;
        }
        public void setTeamCompetition(String teamCompetition)	{
            this.teamCompetition = teamCompetition;
        }
        public void setPerTimePrice(String perTimePrice) {
            this.perTimePrice = perTimePrice;
        }
        public void setOtherTeamInfo(String otherTeamInfo) {
            this.otherTeamInfo = otherTeamInfo;
        }
        public void setPartyName(String  partyName) {
            this.partyName = partyName;
        }
        public void setAddInfo(String addInfo) 	{
            this.addInfo = addInfo;
        }
}