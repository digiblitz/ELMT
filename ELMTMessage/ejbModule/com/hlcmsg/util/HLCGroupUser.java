/*
 * GroupUser.java
 *
 * Created on October 2, 2006, 8:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;

import java.io.Serializable;

/**
 *
 * @author harmohan
 */
public class HLCGroupUser implements Serializable {
    private String invitationId;
    private String ownerId;
    private String groupId;
    private String userId;
    private String joinDate;
    private String activeStatus;
    /** Creates a new instance of GroupUser */
    public HLCGroupUser() {
    }
    
    

    public String getActiveStatus() { return activeStatus; }
    public String getGroupId() {return groupId;}
    public String getInvitationId() { return invitationId; }
    public String getJoinDate() {return joinDate;}
    public String getOwnerId() { return ownerId; }
    public String getUserId() { return userId; }

    public void setActiveStatus(String activeStatus) { this.activeStatus = activeStatus; }
    public void setGroupId(String groupId) { this.groupId = groupId; }
    public void setInvitationId(String invitationId) { this.invitationId = invitationId; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate;}
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public void setUserId(String userId) { this.userId = userId;   }
    
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" groupId :"+ groupId+"\n")
	.append(" invitationId :"+ invitationId+"\n")
	.append(" joinDate :"+ joinDate+"\n")
	.append(" ownerId :"+ownerId+"\n")
	.append(" userId :"+userId+"\n")
	.append(" activeStatus :"+activeStatus+"\n");
	
	
    return buffer.toString();
  }
}
