/*
 * GroupMessage.java
 *
 * Created on October 2, 2006, 8:31 PM
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
public class HLCGroupMessage implements Serializable {
    private String messageId;
    private String groupId;
    private String fromUserId;
    private String toUserId;
    private String subject;
    private String messageDesc;
    private String parentMessageId;
    private String posterIp;
    private String attachFilePath;
    private String addDate;
    private String activeStatus;
    private String author;
    private String postedBy;
    
    private String groupName;
    private String groupType;    
    private String groupDesc;
    private String postedName;
    

    
    
    
    
    /** Creates a new instance of GroupMessage */
    public HLCGroupMessage() {
    }
    
   
    public String getPostedBy() { return postedBy; }
    public String getActiveStatus() { return activeStatus; }
    public String getAddDate() { return addDate; }
    public String getAttachFilePath() { return attachFilePath;}
    public String getFromUserId() { return fromUserId; }
    public String getGroupId() {  return groupId; }
    public String getMessageDesc() { return messageDesc;}
    public String getMessageId() { return messageId; }
    public String getParentMessageId() { return parentMessageId; }
    public String getPosterIp() { return posterIp; }
    public String getSubject() { return subject;}
    public String getToUserId() {return toUserId; }
    public String getAuthor() { return author; }
    
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }
    public void setActiveStatus(String activeStatus) { this.activeStatus = activeStatus; }
    public void setAddDate(String addDate) { this.addDate = addDate; }
    public void setAttachFilePath(String attachFilePath) { this.attachFilePath = attachFilePath; }
    public void setFromUserId(String fromUserId) { this.fromUserId = fromUserId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }
    public void setMessageDesc(String messageDesc) { this.messageDesc = messageDesc; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public void setParentMessageId(String parentMessageId) { this.parentMessageId = parentMessageId;  }
    public void setPosterIp(String posterIp) { this.posterIp = posterIp;   }
    public void setSubject(String subject) { this.subject = subject; }
    public void setToUserId(String toUserId) { this.toUserId = toUserId; }
    public void setAuthor(String author) { this.author = author; }
    
    public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" groupId :"+ groupId+"\n")
	.append(" messageId :"+ messageId+"\n")
	.append(" addDate :"+ addDate+"\n")
	.append(" attachFilePath :"+attachFilePath+"\n")
	.append(" fromUserId :"+fromUserId+"\n")
        .append(" messageDesc :"+ messageDesc+"\n")
	.append(" parentMessageId :"+parentMessageId+"\n")
	.append(" posterIp :"+posterIp+"\n")
        .append(" toUserId :"+toUserId+"\n")
	.append(" subject :"+subject+"\n")
        .append(" author :"+author+"\n")
	.append(" activeStatus :"+activeStatus+"\n");
	
	
    return buffer.toString();
  }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public String getGroupType() {
        return groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setPostedName(String postedName) {
        this.postedName = postedName;
    }

    public String getPostedName() {
        return postedName;
    }

   

   

   

   
}
