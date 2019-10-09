/*
 * PaymentDetails.java
 *
 * Created on August 24, 2006, 1:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;
import java.util.*;
import java.io.*;
/**
 *
 * @author harmohan
 */
public class HLCPaymentDetails implements Serializable{
    private String paymentId;
    private String userId;
    private String userTypeId;
    private String ccName;
    private String ccType;
    private long ccNumber;
    private int ccExpMonth;
    private int ccExpYear;
    private int ccCvvid;
    private String bankName;
    private Date checkDate;
    private long checkNumber;
    private double amount;
    private Date paymentDate;
    private String paymentStatus;
    
    /** Creates a new instance of PaymentDetails */
    public HLCPaymentDetails() {  }
    
    public HLCPaymentDetails(String paymentId, String userId,String userTypeId,String ccName,
             String ccType, long ccNumber, int ccExpMonth,int ccExpYear,
            int ccCvvid, String bankName, Date checkDate, long checkNumber,double amount,
            Date paymentDate,String paymentStatus){

            this.paymentId = paymentId;
            this.userId = userId;
            this.userTypeId = userTypeId;
            this.ccName = ccName;
            this.ccType = ccType;
            this.ccNumber = ccNumber;
            this.ccExpMonth = ccExpMonth;
            this.ccExpYear = ccExpYear;
            this.ccCvvid = ccCvvid;
            this.bankName = bankName;
            this.checkDate = checkDate;
            this.checkNumber = checkNumber;
            this.amount = amount;
            this.paymentDate = paymentDate;
            this.paymentStatus = paymentStatus;
        }

        // getters
        public String getPaymentId() {
            return paymentId;
        }
        public String getUserId() {
            return userId;
        }
        public String getUserTypeId() {
            return userId;
        }
        public String getCcName() {
            return ccName;
        }
        public String getCcType() {
            return ccType;
        }
        public long getCcNumber() {
            return ccNumber;
        }
        public int getCcExpMonth() {
            return ccExpMonth;
        }
        public int getCcExpYear() {
        return ccExpYear;
        }
        public int getCcCvvid() {
            return ccCvvid;
        }
        public String getBankName() {
            return bankName;
        }
        public Date getCheckDate() {
            return checkDate;
        }
        public long getCheckNumber() {
            return checkNumber;
        }
        public double getAmount() {
            return amount;
        }
        public Date getPaymentDate() {
            return paymentDate;
        }
        public String getPaymentStatus() {
            return paymentStatus;
        }

    //Setters methods
        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }
        public void setUserId(String userId) {
            this.userId = userId;
        }
        public void setUserTypeId(String userTypeId) {
            this.userTypeId = userTypeId;
        }
        public void setCcName(String ccName) {
            this.ccName = ccName;
        }
        public void setCcType(String ccType) {
            this.ccType = ccType;
        }
        public void setCcNumber(long ccNumber) {
            this.ccNumber = ccNumber;
        }
        public void setCcExpMonth(int ccExpMonth) {
            this.ccExpMonth = ccExpMonth;
        }
        public void setCcExpYear(int ccExpYear) {
            this.ccExpYear = ccExpYear;
        }
        public void setCcCvvid(int ccCvvid) {
            this.ccCvvid = ccCvvid;
        }
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
        public void setCheckDate(Date checkDate) {
            this.checkDate = checkDate;
        }
        public void setCheckNumber(long checkNumber) {
            this.checkNumber = checkNumber;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
        public void setPaymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
        }
        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
        
   public String toString(){
     
    StringBuffer buffer = new StringBuffer()
   	.append(" paymentId :"+ paymentId+"\n")
	.append(" userId :"+ userId+"\n")
	.append(" ccName :"+ ccName+"\n")
	.append(" ccType :"+ccType+"\n")
	.append(" ccNumber :"+ccNumber+"\n")
	.append(" ccExpMonth :"+ccExpMonth+"\t\t")
        .append(" ccExpYear :"+ccExpYear+"\n")
	.append(" ccCvvid :"+ccCvvid+"\n")
	.append(" bankName :"+ bankName+"\n")
	.append(" checkDate :"+ checkDate+"\n")
	.append(" checkNumber :"+checkNumber+"\n")
	.append(" amount :"+amount+"\n")
	.append(" paymentDate :"+paymentDate+"\n")
	.append(" paymentStatus :"+ paymentStatus+"\n");
	
    return buffer.toString();
  }
    
}
