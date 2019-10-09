/*
 * HorseListVO.java
 *
 * Created on October 26, 2006, 8:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmember.type.util;

import java.util.*;

/**
 *
 * @author karthikeyan
 */
public class HLCHorseListVO implements java.io.Serializable {
    
    /** Creates a new instance of HorseListVO */
    public HLCHorseListVO() {
    }
     
    private String horseId ;
    private String horseMemberId ;
    private String ownerId ;
    private String competitionName;
    private String riderMemberId;
    private Date addDate;
    private String statusName;

    public String getHorseId() {
        return horseId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getRiderMemberId() {
        return riderMemberId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public String getStatusName() {
        return statusName;
    }
   
    public void setHorseId(String horseId) {
        this.horseId = horseId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setRiderMemberId(String riderMemberId) {
        this.riderMemberId = riderMemberId;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
      public String toString(){
        StringBuffer sb = new StringBuffer()
        .append("horseId: " + horseId + "\n")
        .append("horseMemberId: " + horseMemberId + "\n")
        .append("ownerId: " + ownerId + "\n")
        .append("competitionName: " + competitionName + "\n")
        .append("riderMemberId: " + riderMemberId + "\n")
        .append("addDate: " + addDate + "\n")
        .append("statusName: " + statusName + "\n");
        return sb.toString();
    }
}
