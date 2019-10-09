/*
 * MemberHistoryDetail.java
 *
 * Created on September 21, 2007, 12:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcform.util;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author karthikeyan
 */
public class HLCMemberHistoryDetail implements Serializable{
    
    /** Creates a new instance of MemberHistoryDetail */
    public HLCMemberHistoryDetail() {
    }
    
    private String memberId;
    private String membership_type_id; 
    private String membership_type_name;
    private String membership_action;
    private String status_id;
    private int to_year;
   // private int to_date;
    private String zip_code;
    private String payment_id;
    private String user_id;
    private String subExpDate;
    private Date subscribingExDate;
    private String amatName;
    private boolean amatDec1;
    private boolean amatDec2;
   /* public int getFrom_date() {
        return from_date;
    }

    public void setFrom_date(int from_date) {
        this.from_date = from_date;
    }*/

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMembership_type_id() {
        return membership_type_id;
    }

    public void setMembership_type_id(String membership_type_id) {
        this.membership_type_id = membership_type_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

   /* public int getTo_date() {
        return to_date;
    }

    public void setTo_date(int to_date) {
        this.to_date = to_date;
    }*/

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getZip_code() {
        return zip_code;
    }
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" memberId :"+ memberId+"\n")
	.append("  membership_type_id :"+ membership_type_id+"\n")
        .append(" status_id :"+ status_id+"\n")
        /*.append(" from_date :"+ from_date+"\n")
        .append(" to_date :"+ to_date+"\n")*/
        .append(" to_year :"+ to_year+"\n")
	.append(" zip_code :"+ zip_code+"\n")
        .append(" membership_type_name :"+ membership_type_name+"\n")
	.append(" membership_action :"+ membership_action+"\n")
        .append(" payment_id :"+ payment_id+"\n")
        .append(" user_id :"+ user_id+"\n")
        .append(" subscribingExDate :"+ subscribingExDate+"\n")
        .append(" subExpDate :"+ subExpDate+"\n");
        return buffer.toString();
  }

    public String getMembership_action() {
        return membership_action;
    }

    public void setMembership_action(String membership_action) {
        this.membership_action = membership_action;
    }

    public String getMembership_type_name() {
        return membership_type_name;
    }

    public void setMembership_type_name(String membership_type_name) {
        this.membership_type_name = membership_type_name;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public int getTo_year() {
        return to_year;
    }

    public void setTo_year(int to_year) {
        this.to_year = to_year;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
   
    public String getSubExpDate() {
        return subExpDate;
    }

    public void setSubExpDate(String subExpDate) {
        this.subExpDate = subExpDate;
    }
    
    public Date getSubscribingExDate() {
        return subscribingExDate;
    }

    public void setSubscribingExDate(Date subscribingExDate) {
        this.subscribingExDate = subscribingExDate;
    }
   
   public void setAmatName(String amatName) {
        this.amatName = amatName;
    }

    public String getAmatName() {
        return amatName;
    }

    
    public void setAmatDec1(boolean amatDec1) {
        this.amatDec1 = amatDec1;
    }
    
    public boolean isAmatDec1() {
        return amatDec1;
    }
    
    public void setAmatDec2(boolean amatDec2) {
        this.amatDec2 = amatDec2;
    }

    public boolean isAmatDec2() {
        return amatDec2;
    }
   
}
