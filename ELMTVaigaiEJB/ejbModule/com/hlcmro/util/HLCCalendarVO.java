/*
 * CalendarVO.java
 *
 * Created on January 4, 2007, 2:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

/**
 *
 * @author harmohan
 */
public class HLCCalendarVO implements java.io.Serializable{
    
    /** Creates a new instance of CalendarVO */
    public HLCCalendarVO() {
    }
    
    private String calEventId;
    private String eventName;
    private String organizerName;
    private String organizerId;
    private String eventArea;
    private String eventAreaId;
    private String eventType;
    private String eventLevel;
    

    public String getEventArea() { return eventArea; }
    public String getEventAreaId() { return eventAreaId; }
    public String getEventLevel() { return eventLevel; }
    public String getEventName() { return eventName; }
    public String getEventType() { return eventType; }
    public String getOrganizerId() { return organizerId; }
    public String getOrganizerName() {return organizerName; }
    public String getCalEventId() { return calEventId; }        

    public void setEventArea(String eventArea) { this.eventArea = eventArea; }
    public void setEventAreaId(String eventAreaId) {this.eventAreaId = eventAreaId; }
    public void setEventLevel(String eventLevel) { this.eventLevel = eventLevel; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setOrganizerId(String organizerId) { this.organizerId = organizerId; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
    public void setCalEventId(String calEventId) {this.calEventId = calEventId; }
    
    public String toString(){
        StringBuffer strBuf = new StringBuffer()
        .append("\n  Cal Event Id   : "+this.calEventId)
        .append("\n  Event Name     : "+this.eventName)
        .append("\n  Organizer Name : "+this.organizerName)
        .append("\n  Organizer Id   : "+this.organizerId)
        .append("\n  Event Area     : "+this.eventArea)
        .append("\n  Event area Id  : "+this.eventAreaId)
        .append("\n  Event Type     : "+this.eventType)
        .append("\n  Event Level    : "+this.eventLevel);
        return strBuf.toString();
    }

   

   
}
