/*
 * AnnualDetVO.java
 *
 * Created on October 29, 2007, 11:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import java.io.Serializable;

/**
 *
 * @author hari
 */
public class HLCAnnualDetVO  implements Serializable{
    
    /** Creates a new instance of AnnualDetVO */
    public HLCAnnualDetVO() {
    }
    
       String meetId;
       String registrat_id;
       String payment_id;
       float total_amount;
       String request_status;
       String badge_name;
       String days_applied;
       String remarks;
       String cc_name;
       String cc_type;
       String ssl_txn_id;
       
	public String getBadge_name() {
		return badge_name;
	}
	public void setBadge_name(String badge_name) {
		this.badge_name = badge_name;
	}
	public String getCc_name() {
		return cc_name;
	}
	public void setCc_name(String cc_name) {
		this.cc_name = cc_name;
	}
	public String getCc_type() {
		return cc_type;
	}
	public void setCc_type(String cc_type) {
		this.cc_type = cc_type;
	}
	public String getDays_applied() {
		return days_applied;
	}
	public void setDays_applied(String days_applied) {
		this.days_applied = days_applied;
	}
	public String getMeetId() {
		return meetId;
	}
	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getRegistrat_id() {
		return registrat_id;
	}
	public void setRegistrat_id(String registrat_id) {
		this.registrat_id = registrat_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRequest_status() {
		return request_status;
	}
	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}
	public String getSsl_txn_id() {
		return ssl_txn_id;
	}
	public void setSsl_txn_id(String ssl_txn_id) {
		this.ssl_txn_id = ssl_txn_id;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}    
    
    
}
