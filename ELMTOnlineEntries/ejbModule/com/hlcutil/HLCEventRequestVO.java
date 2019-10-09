/*
 * EventRequestVO.java
 *
 * Created on October 30, 2007, 4:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hari
 */
public class HLCEventRequestVO implements Serializable {
    
   String event_id;
    String orgId;
    String eventTitle;
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
    
    private Date eveBegDate;
    private Date eveEndDate;
    private String issueName;
    private String issueId;
    private int compYear;
    private String appStat;
    private String payId;
    private String userId;
    
    private Date eveOeBegDate;
    private Date eveOeEndDate;
    private Date eveOeExtnDate;
    
    
    private float entry_fee;
    private String other_entry_fee;
    private String membership_applicable;
    private String double_entry_fee_status;
    private float office_fee;
    private String check_payable_to;
    private float pinnies_charge;
    private int pinnies_position;
    private String award_trophy;
    private String award_prize;
    private String award_others;
    private Date date_available;
    private String available_from;
    private String available_from_other;
    private int available_position;
    private boolean stabling_limited;
    private float stall_pernight_price;
    private float per_stall_price;
    private String per_stall_from_time;
    private Date per_stall_from_date;
    private String per_stall_to_time;
    private Date per_stall_to_date;
    private int no_of_stalls;
    private int no_of_temp_stalls;
    private int no_of_temp_permanent_stalls;
    private String miles_from_event;
    private String veterinarian_name;
    private String veterinarian_phone;
    private String veterinarian_place;
    private String accomodation_camping;
    private String directions;
    private String footing_desc;
    private String comments;
    private String paymentId;
    private String approvStatus;
   

   
    public String getPaymentId() {
        return paymentId;
    }

    public String getApprovStatus() {
        return approvStatus;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setApprovStatus(String approvStatus) {
        this.approvStatus = approvStatus;
    }
   
    public String getOrgId() {
        return orgId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    
    
    
    
    
    
    public boolean isStabling_limited() {
        return stabling_limited;
    }

    public void setStabling_limited(boolean stabling_limited) {
        this.stabling_limited = stabling_limited;
    }

    public String getVeterinarian_place() {
        return veterinarian_place;
    }

    public String getVeterinarian_phone() {
        return veterinarian_phone;
    }

    public String getVeterinarian_name() {
        return veterinarian_name;
    }

    public float getStall_pernight_price() {
        return stall_pernight_price;
    }

    public int getPinnies_position() {
        return pinnies_position;
    }

    public float getPinnies_charge() {
        return pinnies_charge;
    }

    public String getPer_stall_to_time() {
        return per_stall_to_time;
    }

    public Date getPer_stall_to_date() {
        return per_stall_to_date;
    }

    public float getPer_stall_price() {
        return per_stall_price;
    }

    public String getPer_stall_from_time() {
        return per_stall_from_time;
    }

    public Date getPer_stall_from_date() {
        return per_stall_from_date;
    }

    public String getOther_entry_fee() {
        return other_entry_fee;
    }

    public float getOffice_fee() {
        return office_fee;
    }

    public int getNo_of_temp_stalls() {
        return no_of_temp_stalls;
    }

    public int getNo_of_temp_permanent_stalls() {
        return no_of_temp_permanent_stalls;
    }

    public int getNo_of_stalls() {
        return no_of_stalls;
    }

    public String getMiles_from_event() {
        return miles_from_event;
    }

    public String getMembership_applicable() {
        return membership_applicable;
    }

    public String getFooting_desc() {
        return footing_desc;
    }

    public float getEntry_fee() {
        return entry_fee;
    }

    public String getDouble_entry_fee_status() {
        return double_entry_fee_status;
    }

    public String getDirections() {
        return directions;
    }

    public Date getDate_available() {
        return date_available;
    }

    public String getComments() {
        return comments;
    }

    public String getCheck_payable_to() {
        return check_payable_to;
    }

    public String getAward_trophy() {
        return award_trophy;
    }

    public String getAward_prize() {
        return award_prize;
    }

    public String getAward_others() {
        return award_others;
    }

    public int getAvailable_position() {
        return available_position;
    }

    public String getAvailable_from_other() {
        return available_from_other;
    }

    public String getAvailable_from() {
        return available_from;
    }

    public String getAccomodation_camping() {
        return accomodation_camping;
    }
 

    public void setVeterinarian_place(String veterinarian_place) {
        this.veterinarian_place = veterinarian_place;
    }

    public void setVeterinarian_phone(String veterinarian_phone) {
        this.veterinarian_phone = veterinarian_phone;
    }

    public void setVeterinarian_name(String veterinarian_name) {
        this.veterinarian_name = veterinarian_name;
    }

    public void setStall_pernight_price(float stall_pernight_price) {
        this.stall_pernight_price = stall_pernight_price;
    }

    
    public void setPinnies_position(int pinnies_position) {
        this.pinnies_position = pinnies_position;
    }

    public void setPinnies_charge(float pinnies_charge) {
        this.pinnies_charge = pinnies_charge;
    }

    public void setPer_stall_to_time(String per_stall_to_time) {
        this.per_stall_to_time = per_stall_to_time;
    }

    public void setPer_stall_to_date(Date per_stall_to_date) {
        this.per_stall_to_date = per_stall_to_date;
    }

    public void setPer_stall_price(float per_stall_price) {
        this.per_stall_price = per_stall_price;
    }

    public void setPer_stall_from_time(String per_stall_from_time) {
        this.per_stall_from_time = per_stall_from_time;
    }

    public void setPer_stall_from_date(Date per_stall_from_date) {
        this.per_stall_from_date = per_stall_from_date;
    }

    public void setOther_entry_fee(String other_entry_fee) {
        this.other_entry_fee = other_entry_fee;
    }

    public void setOffice_fee(float office_fee) {
        this.office_fee = office_fee;
    }

    public void setNo_of_temp_stalls(int no_of_temp_stalls) {
        this.no_of_temp_stalls = no_of_temp_stalls;
    }

    public void setNo_of_temp_permanent_stalls(int no_of_temp_permanent_stalls) {
        this.no_of_temp_permanent_stalls = no_of_temp_permanent_stalls;
    }

    public void setNo_of_stalls(int no_of_stalls) {
        this.no_of_stalls = no_of_stalls;
    }

    public void setMiles_from_event(String miles_from_event) {
        this.miles_from_event = miles_from_event;
    }

    public void setMembership_applicable(String membership_applicable) {
        this.membership_applicable = membership_applicable;
    }

    public void setFooting_desc(String footing_desc) {
        this.footing_desc = footing_desc;
    }

    public void setEntry_fee(float entry_fee) {
        this.entry_fee = entry_fee;
    }

    public void setDouble_entry_fee_status(String double_entry_fee_status) {
        this.double_entry_fee_status = double_entry_fee_status;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public void setDate_available(Date date_available) {
        this.date_available = date_available;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setCheck_payable_to(String check_payable_to) {
        this.check_payable_to = check_payable_to;
    }

    public void setAward_trophy(String award_trophy) {
        this.award_trophy = award_trophy;
    }

    public void setAward_prize(String award_prize) {
        this.award_prize = award_prize;
    }

    public void setAward_others(String award_others) {
        this.award_others = award_others;
    }

    public void setAvailable_position(int available_position) {
        this.available_position = available_position;
    }

    public void setAvailable_from_other(String available_from_other) {
        this.available_from_other = available_from_other;
    }

    public void setAvailable_from(String available_from) {
        this.available_from = available_from;
    }

    public void setAccomodation_camping(String accomodation_camping) {
        this.accomodation_camping = accomodation_camping;
    }
    
    
    
    
    
    
    
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

    public void setEveOeBegDate(Date eveOeBegDate) {
        this.eveOeBegDate = eveOeBegDate;
    }

    public void setEveOeEndDate(Date eveOeEndDate) {
        this.eveOeEndDate = eveOeEndDate;
    }

    public void setEveOeExtnDate(Date eveOeExtnDate) {
        this.eveOeExtnDate = eveOeExtnDate;
    }

    public Date getEveOeBegDate() {
        return eveOeBegDate;
    }

    public Date getEveOeEndDate() {
        return eveOeEndDate;
    }

    public Date getEveOeExtnDate() {
        return eveOeExtnDate;
    }

  
}
