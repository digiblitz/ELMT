/*
 * MessageVO.java
 *
 * Created on September 27, 2006, 3:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;
import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author sylvia
 */
public class HLCMessageVO implements Serializable {
    private String  messageId;
    private String  fromUserId;
    private String  toUserId;
    private String  subject;
    private String  message;
    private Date  postDate;
    private boolean  viewStatus;
    private String fromEmailId;
    private String firstName;
    private String lastName;
    private String filePath;
    /** Creates a new instance of IEDMessageVO */
    public HLCMessageVO() {
    }
  public  HLCMessageVO(String  messageId, String fromUserId, String toUserId,
            String subject, String message , Date postDate , boolean  viewStatus,
            String fromEmailId, String firstName, String lastName, String filePath) {
        this.messageId = messageId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.subject = subject;
        this.message = message;
        this.postDate = postDate;
        this.viewStatus = viewStatus;
        this.fromEmailId = fromEmailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.filePath = filePath;
    }

//getter

    public String getMessageId() {
        return messageId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public Date getPostDate() {
        return postDate;
    }

    public boolean isViewStatus() {
        return viewStatus;
    }
    
   public String getFromEmailId() {
        return fromEmailId;
   }
   
   public String getFirstName() {
        return firstName;
   }
   
   public String getLastName() {
        return lastName;
   }
   

 //Setters methods

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setFromUserId(String fromUserId)  {
        this.fromUserId = fromUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId =  toUserId;
    }
    
    public void setSubject(String subject)   {
        this.subject =  subject;
    }
		
    public void setMessage(String message) {
        this.message =  message;
    }
    
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    
    public void setViewStatus(boolean viewStatus) {
        this.viewStatus = viewStatus;
    }
    
   public void setFromEmailId(String fromEmailId) {
        this.fromEmailId = fromEmailId;
   }
   
   public void  setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
   
  public String toString(){
    StringBuffer buffer = new StringBuffer()
        .append("messageId=" + this.messageId+",")
        .append("fromUserId=" + this.fromUserId+",")
        .append("toUserId=" + this.toUserId+",")
        .append("subject=" + this.subject+",")
        .append("message=" + this.message+",")
        .append("postDate=" + this.postDate+",")
        .append("viewStatus=" + this.viewStatus+",")
        .append("fromEmailId=" + this.fromEmailId + ",")
        .append("firstName=" + this.firstName+",")
        .append("lastName=" + this.lastName);
    return buffer.toString();
  }

   
}