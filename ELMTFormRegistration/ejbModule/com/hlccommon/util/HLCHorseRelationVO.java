/*
 * HorseRelationVO.java
 *
 * Created on January 31, 2007, 2:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author suresh
 */
public class HLCHorseRelationVO implements Serializable{
    
    private String relationId;
    private String horseMemberId;
    private String relationshipTypeId;
    private String userId;
    private String relationshipStatus;
    private String activeStatus;
    private Date addDate;
    private String modifiedBy;
    private Date modifyDate;
    private String requestId;
    
       
    /** Creates a new instance of HorseRelationVO */
    public HLCHorseRelationVO() {
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setRelationshipTypeId(String relationshipTypeId) {
        this.relationshipTypeId = relationshipTypeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getRelationId() {
        return relationId;
    }
    
    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getRelationshipTypeId() {
        return relationshipTypeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public Date getAddDate() {
        return addDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
    
}
