/*
 * Group.java
 *
 * Created on October 2, 2006, 8:18 PM
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
public class HLCGroup implements Serializable {
    private String groupId = null;
    private String groupName = null;
    private String userId = null;
    private String groupType = null;
    private String categoryId = null;
    private String groupDesc = null;
    private String moderatorId = null;
    private String activeStatus = null;
    private String addDate = null;
    private String categoryName;

    /** Creates a new instance of Group */
    public HLCGroup() {
    }
    
    public String getCategoryName() {return categoryName; }
    public String getGroupId() { return groupId; }
    public String getUserId() { return userId; }
    public String getGroupName() { return groupName; }
    public String getGroupType() { return groupType; }
    public String getGroupDesc() {return groupDesc; }
    public String getCategoryId() {return categoryId; }
    public String getModeratorId() {return moderatorId; }
    public String getActiveStatus() { return activeStatus; }
    public String getAddDate() { return addDate; }
    
    public void setGroupDesc(String groupDesc) { this.groupDesc = groupDesc; }
    public void setGroupId(String groupId) { this.groupId = groupId; }
    public void setGroupName(String groupName) {this.groupName = groupName;}
    public void setGroupType(String groupType) {this.groupType = groupType;}
    public void setUserId(String userId) {this.userId = userId;}
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public void setActiveStatus(String activeStatus) {this.activeStatus = activeStatus; }
    public void setAddDate(String addDate) {this.addDate = addDate;}
    public void setModeratorId(String moderatorId) {this.moderatorId = moderatorId;}
    public void setCategoryName(String categoryName) {this.categoryName = categoryName;}
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" groupId :"+ groupId+"\n")
	.append(" categoryId :"+ categoryId+"\n")
	.append(" groupName :"+ groupName+"\n")
	.append(" groupDesc :"+groupDesc+"\n")
	.append(" groupType :"+groupType+"\n")
	.append(" userId :"+userId+"\t\t")
        .append(" activeStatus :"+activeStatus+"\n")
	.append(" addDate :"+addDate+"\n")
        .append(" categoryName :"+categoryName+"\n")
        .append(" moderatorId :"+moderatorId+"\n");
	
    return buffer.toString();
  }

   

    
    
}
