/*
 * CompResultVO.java
 *
 * Created on April 3, 2007, 7:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author karthikeyan
 */

package com.hlcmeeting.util;

import java.math.*;
import java.util.ArrayList;

public class HLCCompResultVO implements java.io.Serializable{
		
    public HLCCompResultVO(){            
            }
            
	    private String tmp_result_id;
	    private String event_type_id;
	    private String event_id;
	    private String event_type;
	    private String event_level;
	    private String event_division;
	    private String starters;
	    private String horse_name;
	    private String horse_member_id;
	   // private String rider_name;
	    private String rider_member_id;
	    private String pinney_number;
	    private String dressage_penalties;
	    private String dressage_place;
	    private String xc_phaseD_jump_penalties;
	    private String xc_phaseD_elapsed_time;
	    private String xc_phaseD_time_penalties;
            private String show_jump_time_penalties;
	    private String show_jump_jump_penalties;
	    private String to_date_points;
	    private String to_date_place;
	    private String dangerous_riding_penalties;
	    private String final_points;
	    private String final_place;
	    private String first_inspection;
	    private String last_inspection;
	    private String phase_D_inspection;
	    private String road_and_track_A;
	    private String road_and_track_C;
	    private String steeplechase_jump_penalties;
	    private String steeplechase_time_penalties;
	    private String usea_points;
            private String upload_id;
            private String rider_first_name;
            private String rider_last_name;            
            private String event_sub_division;            
            private boolean exceptionStatus;
            private ArrayList riderList;
            private ArrayList relationList;
            private ArrayList horseList;            
            private String eventLevelId;
            private String eventDivId;


            
        public String getDangerous_riding_penalties() {
		return dangerous_riding_penalties;
	}
	public void setDangerous_riding_penalties(String dangerous_riding_penalties) {
		this.dangerous_riding_penalties = dangerous_riding_penalties;
	}
	public String getDressage_penalties() {
		return dressage_penalties;
	}
	public void setDressage_penalties(String dressage_penalties) {
		this.dressage_penalties = dressage_penalties;
	}
	public String getDressage_place() {
		return dressage_place;
	}
	public void setDressage_place(String dressage_place) {
		this.dressage_place = dressage_place;
	}
	public String getEvent_division() {
		return event_division;
	}
	public void setEvent_division(String event_division) {
		this.event_division = event_division;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEvent_level() {
		return event_level;
	}
	public void setEvent_level(String event_level) {
		this.event_level = event_level;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getEvent_type_id() {
		return event_type_id;
	}
	public void setEvent_type_id(String event_type_id) {
		this.event_type_id = event_type_id;
	}
	public String getFinal_place() {
		return final_place;
	}
	public void setFinal_place(String final_place) {
		this.final_place = final_place;
	}
	public String getFinal_points() {
		return final_points;
	}
	public void setFinal_points(String final_points) {
		this.final_points = final_points;
	}
	public String getFirst_inspection() {
		return first_inspection;
	}
	public void setFirst_inspection(String first_inspection) {
		this.first_inspection = first_inspection;
	}
	public String getHorse_member_id() {
		return horse_member_id;
	}
	public void setHorse_member_id(String horse_member_id) {
		this.horse_member_id = horse_member_id;
	}
	public String getHorse_name() {
		return horse_name;
	}
	public void setHorse_name(String horse_name) {
		this.horse_name = horse_name;
	}
	public String getLast_inspection() {
		return last_inspection;
	}
	public void setLast_inspection(String last_inspection) {
		this.last_inspection = last_inspection;
	}
	public String getPhase_D_inspection() {
		return phase_D_inspection;
	}
	public void setPhase_D_inspection(String phase_D_inspection) {
		this.phase_D_inspection = phase_D_inspection;
	}
	public String getPinney_number() {
		return pinney_number;
	}
	public void setPinney_number(String pinney_number) {
		this.pinney_number = pinney_number;
	}
	public String getRider_member_id() {
		return rider_member_id;
	}
	public void setRider_member_id(String rider_member_id) {
		this.rider_member_id = rider_member_id;
	}
	/*public String getRider_name() {
		return rider_name;
	}
	public void setRider_name(String rider_name) {
		this.rider_name = rider_name;
	}*/
	public String getRoad_and_track_A() {
		return road_and_track_A;
	}
	public void setRoad_and_track_A(String road_and_track_A) {
		this.road_and_track_A = road_and_track_A;
	}
	public String getRoad_and_track_C() {
		return road_and_track_C;
	}
	public void setRoad_and_track_C(String road_and_track_C) {
		this.road_and_track_C = road_and_track_C;
	}
	public String getShow_jump_jump_penalties() {
		return show_jump_jump_penalties;
	}
	public void setShow_jump_jump_penalties(String show_jump_jump_penalties) {
		this.show_jump_jump_penalties = show_jump_jump_penalties;
	}
	public String getShow_jump_time_penalties() {
		return show_jump_time_penalties;
	}
	public void setShow_jump_time_penalties(String show_jump_time_penalties) {
		this.show_jump_time_penalties = show_jump_time_penalties;
	}
	public String getStarters() {
		return starters;
	}
	public void setStarters(String starters) {
		this.starters = starters;
	}
	public String getSteeplechase_jump_penalties() {
		return steeplechase_jump_penalties;
	}
	public void setSteeplechase_jump_penalties(String steeplechase_jump_penalties) {
		this.steeplechase_jump_penalties = steeplechase_jump_penalties;
	}
	public String getSteeplechase_time_penalties() {
		return steeplechase_time_penalties;
	}
	public void setSteeplechase_time_penalties(String steeplechase_time_penalties) {
		this.steeplechase_time_penalties = steeplechase_time_penalties;
	}
	public String getTmp_result_id() {
		return tmp_result_id;
	}
	public void setTmp_result_id(String tmp_result_id) {
		this.tmp_result_id = tmp_result_id;
	}
	public String getTo_date_place() {
		return to_date_place;
	}
	public void setTo_date_place(String to_date_place) {
		this.to_date_place = to_date_place;
	}
	public String getTo_date_points() {
		return to_date_points;
	}
	public void setTo_date_points(String to_date_points) {
		this.to_date_points = to_date_points;
	}
	public String getUsea_points() {
		return usea_points;
	}
	public void setUsea_points(String usea_points) {
		this.usea_points = usea_points;
	}
	public String getXc_phaseD_elapsed_time() {
		return xc_phaseD_elapsed_time;
	}
	public void setXc_phaseD_elapsed_time(String xc_phaseD_elapsed_time) {
		this.xc_phaseD_elapsed_time = xc_phaseD_elapsed_time;
	}
	public String getXc_phaseD_jump_penalties() {
		return xc_phaseD_jump_penalties;
	}
	public void setXc_phaseD_jump_penalties(String xc_phaseD_jump_penalties) {
		this.xc_phaseD_jump_penalties = xc_phaseD_jump_penalties;
	}
	public String getXc_phaseD_time_penalties() {
		return xc_phaseD_time_penalties;
	}
	public void setXc_phaseD_time_penalties(String xc_phaseD_time_penalties) {
		this.xc_phaseD_time_penalties = xc_phaseD_time_penalties;
	}
          	  
        public String toString() {
			
                    StringBuffer sb = new StringBuffer();
                                        
                    sb.append("tmp_result_id :" + tmp_result_id + "\n");
                    sb.append("event_type_id :" + event_type_id + "\n");                    
                    sb.append("event_id :" + event_id + "\n");
                    sb.append("event_type :" + event_type + "\n");
                    sb.append("event_level :" + event_level + "\n");
                    sb.append("event_division :" + event_division + "\n");
                    sb.append("starters :" + starters + "\n");
                    sb.append("horse_name :" + horse_name + "\n");
                    sb.append("horse_member_id :" + horse_member_id + "\n");                             
                    sb.append("rider_first_name :" + rider_first_name + "\n");
                    sb.append("rider_last_name :" + rider_last_name + "\n");
                    sb.append("exceptionStatus :" + exceptionStatus + "\n");                      
                    sb.append("rider_member_id :" + rider_member_id + "\n");
                    sb.append("pinney_number :" + pinney_number + "\n");
                    sb.append("dressage_penalties :" + dressage_penalties + "\n");
                    sb.append("dressage_place :" + dressage_place + "\n");
                    sb.append("xc_phaseD_jump_penalties :" + xc_phaseD_jump_penalties + "\n");
                    sb.append("xc_phaseD_elapsed_time :" + xc_phaseD_elapsed_time + "\n");
                    sb.append("xc_phaseD_time_penalties :" + xc_phaseD_time_penalties + "\n");
                    sb.append("show_jump_time_penalties :" + show_jump_time_penalties + "\n");
                    sb.append("show_jump_jump_penalties :" + show_jump_jump_penalties + "\n");
                    sb.append("to_date_points :" + to_date_points + "\n");
                    sb.append("to_date_place :" + to_date_place + "\n");
                    sb.append("dangerous_riding_penalties :" + dangerous_riding_penalties + "\n");
                    sb.append("final_points :" + final_points + "\n");
                    sb.append("final_place :" + final_place + "\n");
                    sb.append("first_inspection :" + first_inspection + "\n");
                    sb.append("last_inspection :" + last_inspection + "\n");
                    sb.append("phase_D_inspection :" + phase_D_inspection + "\n");
                    sb.append("road_and_track_A :" + road_and_track_A + "\n");                    
                    sb.append("road_and_track_C :" + road_and_track_C + "\n");
                    sb.append("steeplechase_jump_penalties :" + steeplechase_jump_penalties + "\n");
                    sb.append("steeplechase_time_penalties :" + steeplechase_time_penalties + "\n");
                    sb.append("usea_points :" + usea_points + "\n");
                    sb.append("event_sub_division :" + event_sub_division + "\n");                    
                    sb.append("upload_id :" + upload_id + "\n"); 
                   
                    return sb.toString();
                    
	}

    public String getUpload_id() {
        return upload_id;
    }

    public void setUpload_id(String upload_id) {
        this.upload_id = upload_id;
    }

    public void setRider_first_name(String rider_first_name) {
        this.rider_first_name = rider_first_name;
    }

    public void setRider_last_name(String rider_last_name) {
        this.rider_last_name = rider_last_name;
    }

    public String getRider_first_name() {
        return rider_first_name;
    }

    public String getRider_last_name() {
        return rider_last_name;
    }
    
     public void setExceptionStatus(boolean exceptionStatus) {
        this.exceptionStatus = exceptionStatus;
    }

    public boolean isExceptionStatus() {
        return exceptionStatus;
    }
    
    public void setHorseList(ArrayList horseList) {
        this.horseList = horseList;
    }

    public ArrayList getHorseList() {
        return horseList;
    }
    
     public void setRiderList(ArrayList riderList) {
        this.riderList = riderList;
    }
     
     public ArrayList getRiderList() {
        return riderList;
    }
     
     public void setRelationList(ArrayList relationList) {
        this.relationList = relationList;
    }

    public ArrayList getRelationList() {
        return relationList;
    }    

    public String getEvent_sub_division() {
        return event_sub_division;
    }

    public void setEvent_sub_division(String event_sub_division) {
        this.event_sub_division = event_sub_division;
    }
    
    public String getEventDivId() {
        return eventDivId;
 }

 public String getEventLevelId() {
        return eventLevelId;
 }

 public void setEventDivId(String eventDivId) {
        this.eventDivId = eventDivId;
 }

 public void setEventLevelId(String eventLevelId) {
        this.eventLevelId = eventLevelId;
 } 

}
