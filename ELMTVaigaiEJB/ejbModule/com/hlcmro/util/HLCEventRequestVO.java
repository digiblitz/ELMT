/*
 * EventRequestVO.java
 *
 * Created on October 30, 2007, 4:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hari
 */
public class HLCEventRequestVO implements Serializable {
    
    String event_id;
    String organizer_id;
    String event_title;
    String event_begin_date;
    String event_end_date;
    String event_secretary_id;
    String competition_location;
    String competition_city;
    String competition_state;
    String competition_country;
    String competition_zip;
    String championship_area;
    String status_id;
    boolean active_status;
    ArrayList maping_type_id;
    ArrayList event_type_level_id;
    
    private Date eveBegDate;
    private Date eveEndDate;
    private String issueName;
    private String issueId;
    private int compYear;
    private String appStat;
    private String payId;
    private String userId;
    
    /** Creates a new instance of EventRequestVO */
    public HLCEventRequestVO() {
    }    

    public String getChampionship_area() {
        return championship_area;
    }
    public void setChampionship_area(String championship_area) {
        this.championship_area = championship_area;
    }
    public String getCompetition_city() {
        return competition_city;
    }
    public void setCompetition_city(String competition_city) {
        this.competition_city = competition_city;
    }
    public String getCompetition_country() {
        return competition_country;
    }
    public void setCompetition_country(String competition_country) {
        this.competition_country = competition_country;
    }
    public String getCompetition_location() {
        return competition_location;
    }
    public void setCompetition_location(String competition_location) {
        this.competition_location = competition_location;
    }
    public String getCompetition_state() {
        return competition_state;
    }
    public void setCompetition_state(String competition_state) {
        this.competition_state = competition_state;
    }
    public String getCompetition_zip() {
        return competition_zip;
    }
    public void setCompetition_zip(String competition_zip) {
        this.competition_zip = competition_zip;
    }
    public String getEvent_begin_date() {
        return event_begin_date;
    }
    public void setEvent_begin_date(String event_begin_date) {
        this.event_begin_date = event_begin_date;
    }
    public String getEvent_end_date() {
        return event_end_date;
    }
    public void setEvent_end_date(String event_end_date) {
        this.event_end_date = event_end_date;
    }
    public String getEvent_id() {
        return event_id;
    }
    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }
    public String getEvent_secretary_id() {
        return event_secretary_id;
    }
    public void setEvent_secretary_id(String event_secretary_id) {
        this.event_secretary_id = event_secretary_id;
    }
    public String getEvent_title() {
        return event_title;
    }
    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }
    public String getOrganizer_id() {
        return organizer_id;
    }
    public void setOrganizer_id(String organizer_id) {
        this.organizer_id = organizer_id;
    }
    public String getStatus_id() {
        return status_id;
    }
    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }    

    public boolean isActive_status() {
        return active_status;
    }

    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }

    public ArrayList getMaping_type_id() {
        return maping_type_id;
    }

    public void setMaping_type_id(ArrayList maping_type_id) {
        this.maping_type_id = maping_type_id;
    }

    public void setEveBegDate(Date eveBegDate) {
        this.eveBegDate = eveBegDate;
    }

    public Date getEveEndDate() {
        return eveEndDate;
    }

    public void setEveEndDate(Date eveEndDate) {
        this.eveEndDate = eveEndDate;
    }

    public Date getEveBegDate() {
        return eveBegDate;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setCompYear(int compYear) {
        this.compYear = compYear;
    }

    public int getCompYear() {
        return compYear;
    }

    public void setAppStat(String appStat) {
        this.appStat = appStat;
    }

    public String getAppStat() {
        return appStat;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayId() {
        return payId;
    }

   public void setUserId(String userId) {
        this.userId = userId;
    }
 
    public String getUserId() {
        return userId;
    }

     public ArrayList getEvent_type_level_id() {
        return event_type_level_id;
    }

    public void setEvent_type_level_id(ArrayList event_type_level_id) {
        this.event_type_level_id = event_type_level_id;
    }
    
    
}
