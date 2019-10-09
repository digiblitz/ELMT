/*
 * EventCompQualDet.java
 *
 * Created on April 23, 2007, 11:25 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import java.util.*;
import java.io.*;

/**
 *
 * @author karthikeyan
 */
public class HLCEventCompQualDetVO  implements Serializable{
    
    /** Creates a new instance of EventCompQualDet */
    public HLCEventCompQualDetVO() {
    }
    
    private String event_type;
	 private String event_level;
	 private String rider_age;
	 private String horse_age;
	 private String event_division;
	 private String age_from;
	 private String age_to;
	 private String horse_rank;
	 private boolean amateur_status;
	 
	public String getAge_from() {
		return age_from;
	}
	public void setAge_from(String age_from) {
		this.age_from = age_from;
	}
	public String getAge_to() {
		return age_to;
	}
	public void setAge_to(String age_to) {
		this.age_to = age_to;
	}
	public boolean isAmateur_status() {
		return amateur_status;
	}
	public void setAmateur_status(boolean amateur_status) {
		this.amateur_status = amateur_status;
	}
	public String getEvent_division() {
		return event_division;
	}
	public void setEvent_division(String event_division) {
		this.event_division = event_division;
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
	public String getHorse_age() {
		return horse_age;
	}
	public void setHorse_age(String horse_age) {
		this.horse_age = horse_age;
	}
	public String getHorse_rank() {
		return horse_rank;
	}
	public void setHorse_rank(String horse_rank) {
		this.horse_rank = horse_rank;
	}
	public String getRider_age() {
		return rider_age;
	}
	public void setRider_age(String rider_age) {
		this.rider_age = rider_age;
	}
        
        public String toString() {
			
                    StringBuffer sb = new StringBuffer();
                               
                    sb.append("event_type :" + event_type + "\n");
                    sb.append("event_level :" + event_level + "\n");                    
                    sb.append("rider_age :" + rider_age + "\n");
                    sb.append("horse_age :" + horse_age + "\n");
                    sb.append("event_division :" + event_division + "\n");
                    sb.append("age_from :" + age_from + "\n");
                    sb.append("age_to :" + age_to + "\n");
                    sb.append("horse_rank :" + horse_rank + "\n");
                    sb.append("amateur_status :" + amateur_status + "\n");
                   
                    return sb.toString();
                    
	}
    
}
