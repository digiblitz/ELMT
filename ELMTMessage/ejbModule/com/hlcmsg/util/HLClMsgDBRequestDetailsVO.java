/*
 * lMsgDBRequestDetailsVO.java
 *
 * Created on October 10, 2006, 9:38 PM
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
public class HLClMsgDBRequestDetailsVO implements Serializable{
    private String dbRequestId;
    private String userId;
    private String noOfRecords;
    private String totalAmount;
    private String filePath;
    private String requestStatus;
    private String comments;
    private String addDate;
    private String firstName;
    private String lastName;
    private String middleName;
    private String searchPattern;
   
    
    /** Creates a new instance of lMsgDBRequestDetailsVO */
    public HLClMsgDBRequestDetailsVO() {
    }

   
    public String getComments() { return comments; }
    public String getDbRequestId() { return dbRequestId;}
    public String getFilePath() {return filePath; }
    public String getNoOfRecords() { return noOfRecords; }
    public String getRequestStatus() { return requestStatus; }
    public String getTotalAmount() { return totalAmount; }
    public String getUserId() { return userId; }
    public String getAddDate() { return addDate;}

    public void setComments(String comments) { this.comments = comments; }
    public void setDbRequestId(String dbRequestId) { this.dbRequestId = dbRequestId; }
    public void setFilePath(String filePath) { this.filePath = filePath;}
    public void setNoOfRecords(String noOfRecords) { this.noOfRecords = noOfRecords; }
    public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus; }
    public void setTotalAmount(String totalAmount) { this.totalAmount = totalAmount;}
    public void setUserId(String userId) { this.userId = userId; }
    public void setAddDate(String addDate) { this.addDate = addDate; }
    

     public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append("\n dbRequestId :"+ dbRequestId+"\n")
        .append(" noOfRecords :"+ noOfRecords+"\n")
	.append(" totalAmount :"+ totalAmount+"\n")
	.append(" filePath :"+ filePath+"\n")
	.append(" comments :"+comments+"\n")
	.append(" requestStatus :"+requestStatus+"\n")
        .append(" searchPattern :"+searchPattern+"\n")
        .append(" addDate :"+addDate+"\n")
	.append(" userId :"+userId+"\t\t");
        
    return buffer.toString();
  }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSearchPattern() {
        return searchPattern;
    }

    public void setSearchPattern(String searchPattern) {
        this.searchPattern = searchPattern;
    }
    
}
