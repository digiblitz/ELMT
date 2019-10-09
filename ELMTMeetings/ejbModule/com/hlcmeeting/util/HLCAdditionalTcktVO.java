/*
 * AdditionalTcktVO.java
 *
 * Created on October 31, 2007, 3:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author hari
 */
public class HLCAdditionalTcktVO implements Serializable{

    String user_id="";
    String member_type_id="";
    String ard_id="";
    String meeting_id="";
    String regi_Amount="";
    String tickets="";
    String paymentId="";
    String badgeInfo="";
    boolean poniClubStatus;
    String poniClubMemberId="";
    String clubName="";
    boolean accomFaciStaus;
    String daysApplied="";
    String accomodation="";
    String tempMember ="";
    String no_of_tickets = "";
    ArrayList priceIds;
    
        /** Creates a new instance of AdditionalTcktVO */
        public HLCAdditionalTcktVO() {
        }
	public boolean isAccomFaciStaus() {
		return accomFaciStaus;
	}
	public void setAccomFaciStaus(boolean accomFaciStaus) {
		this.accomFaciStaus = accomFaciStaus;
	}
	public String getAccomodation() {
		return accomodation;
	}
	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}
	public String getBadgeInfo() {
		return badgeInfo;
	}
	public void setBadgeInfo(String badgeInfo) {
		this.badgeInfo = badgeInfo;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getDaysApplied() {
		return daysApplied;
	}
	public void setDaysApplied(String daysApplied) {
		this.daysApplied = daysApplied;
	}
	public String getPoniClubMemberId() {
		return poniClubMemberId;
	}
	public void setPoniClubMemberId(String poniClubMemberId) {
		this.poniClubMemberId = poniClubMemberId;
	}
	public boolean isPoniClubStatus() {
		return poniClubStatus;
	}
	public void setPoniClubStatus(boolean poniClubStatus) {
		this.poniClubStatus = poniClubStatus;
	}
	public String getTempMember() {
		return tempMember;
	}
	public void setTempMember(String tempMember) {
		this.tempMember = tempMember;
	}
	public String getArd_id() {
		return ard_id;
	}
	public void setArd_id(String ard_id) {
		this.ard_id = ard_id;
	}
	public String getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(String meeting_id) {
		this.meeting_id = meeting_id;
	}
	public String getMember_type_id() {
		return member_type_id;
	}
	public void setMember_type_id(String member_type_id) {
		this.member_type_id = member_type_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}    

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getTickets() {
        return tickets;
    }

    public String getRegi_Amount() {
        return regi_Amount;
    }

    public void setRegi_Amount(String regi_Amount) {
        this.regi_Amount = regi_Amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getNo_of_tickets() {
        return no_of_tickets;
    }

    public void setNo_of_tickets(String no_of_tickets) {
        this.no_of_tickets = no_of_tickets;
    }

    public ArrayList getPriceIds() {
        return priceIds;
    }

    public void setPriceIds(ArrayList priceIds) {
        this.priceIds = priceIds;
    }
}
