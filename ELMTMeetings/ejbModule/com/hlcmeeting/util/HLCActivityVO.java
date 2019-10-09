/*
 * ActivityVO.java
 *
 * Created on October 30, 2006, 7:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

/**
 *
 * @author sridhar
 */
public class HLCActivityVO implements java.io.Serializable  {
    
    private String meetingId;
    private String userId;
    private String areaId;
    private String badgeInfo;
    private String priceId;
    private String daysApplied;
    private String noOfTickets;
    private String accomodationDetails;
    
    
    /** Creates a new instance of ActivityVO */
    public HLCActivityVO() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setBadgeInfo(String badgeInfo) {
        this.badgeInfo = badgeInfo;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public void setDaysApplied(String daysApplied) {
        this.daysApplied = daysApplied;
    }

    public void setNoOfTickets(String noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public void setAccomodationDetails(String accomodationDetails) {
        this.accomodationDetails = accomodationDetails;
    }

    public String getUserId() {
        return userId;
    }
    

    public String getAreaId() {
        return areaId;
    }

    public String getBadgeInfo() {
        return badgeInfo;
    }

    public String getPriceId() {
        return priceId;
    }

    public String getDaysApplied() {
        return daysApplied;
    }

    public String getNoOfTickets() {
        return noOfTickets;
    }

    public String getAccomodationDetails() {
        return accomodationDetails;
    }
    
   public String toString(){
        StringBuffer buffer = new StringBuffer()
        .append("userId=" + this.userId+",")
        .append("areaId=" + this.areaId+",")
        .append("badgeInfo=" + this.badgeInfo+",")
        .append("priceId=" + this.priceId+",")
        .append("daysApplied=" + this.daysApplied+",")
        .append("noOfTickets=" + this.noOfTickets+",")
        .append("accomodationDetails=" + this.accomodationDetails);
        return buffer.toString();
    }

}
