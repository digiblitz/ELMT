/*
 * MessageDBActionForm.java
 *
 * Created on October 12, 2006, 4:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.message.actionform;

import com.hlcform.util.HLCPaymentDetails;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author harmohan
 */
public class MessageDBActionForm extends ActionForm{
    
    
    private List reqLists;
    
    private List adminLists;
    
    private List dbLists;
    
    private HLCPaymentDetails payment;
    
    private String hlcArea;
    
    private String zipCode;
    
    private String memberType;
    
    private String status;
    
    private String recordCount;
    
    private String amount;
    
    private String comment;
    
    private String reqId;
    
    private String query;
    
    
    
    
    private String paymentId;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    private String userId;
    private String userTypeId;
    private String ccName;
    private String ccType;
    private String ccNumber;
    private String ccExpMonth;
    private String ccExpYear;
    private String ccCvvid;
    private String bankName;
    private String checkDate;
    private String checkNumber;
    private String paymentDate;
    private String paymentStatus;
    private String cardNo;
    private String cardCvvNo;
    private String favourOf;
    private String checkNo;
    
    
    /** Creates a new instance of MessageDBActionForm */
    public MessageDBActionForm() {
    }
    
    public void setReqLists(List reqLists) {
        this.reqLists = reqLists;
    }
    
    public List getReqLists() {
        return reqLists;
    }
    
    public void setAdminLists(List adminLists) {
        this.adminLists = adminLists;
    }
    
    public List getAdminLists() {
        return adminLists;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setMemberType(String memberTypeId) {
        this.memberType = memberTypeId;
    }
    
    public String getMemberType() {
        return memberType;
    }
    
    public void setStatus(String statusId) {
        this.status = statusId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setDbLists(List dbLists) {
        this.dbLists = dbLists;
    }
    
    public List getDbLists() {
        return dbLists;
    }
    
    public void setUseaArea(String hlcArea) {
        this.hlcArea = hlcArea;
    }
    
    public String getUseaArea() {
        return hlcArea;
    }
    
    
    public void  setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }
    
    
    public String getRecordCount() {
        return recordCount;
    }
    
    public void setPayment(HLCPaymentDetails payment) {
        this.payment = payment;
    }
    
    public HLCPaymentDetails getPayment() {
        return payment;
    }
    
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
    public String getAmount() {
        return amount;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public String getComment() {
        return comment;
    }
    
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }
    
    public String getReqId() {
        return reqId;
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
        public String getCcNumber() {
            return ccNumber;
        }
        public String getCcExpMonth() {
            return ccExpMonth;
        }
        public String getCcExpYear() {
        return ccExpYear;
        }
        public String getCcCvvid() {
            return ccCvvid;
        }
        public String getBankName() {
            return bankName;
        }
        public String getCheckDate() {
            return checkDate;
        }
        public String getCheckNumber() {
            return checkNumber;
        }
  
        public String getPaymentDate() {
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
        public void setCcNumber(String ccNumber) {
            this.ccNumber = ccNumber;
        }
        public void setCcExpMonth(String ccExpMonth) {
            this.ccExpMonth = ccExpMonth;
        }
        public void setCcExpYear(String ccExpYear) {
            this.ccExpYear = ccExpYear;
        }
        public void setCcCvvid(String ccCvvid) {
            this.ccCvvid = ccCvvid;
        }
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }
        public void setCheckNumber(String checkNumber) {
            this.checkNumber = checkNumber;
        }

        public void setPaymentDate(String paymentDate) {
            this.paymentDate = paymentDate;
        }
        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }
        
  

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getCardCvvNo() {
        return cardCvvNo;
    }

    public void setCardCvvNo(String cardCvvNo) {
        this.cardCvvNo = cardCvvNo;
    }

    public void setFavourOf(String favourOf) {
        this.favourOf = favourOf;
    }

    public String getFavourOf() {
        return favourOf;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getCheckNo() {
        return checkNo;
    }
    public String toString() {
        
        StringBuffer buffer = new StringBuffer()
        .append("  UseaArea= "+   hlcArea+ "\t\t")
        .append(" zipCode =   "+   zipCode+ "\t\t")
        .append(" memberType=   "+   memberType+ "\t\t")
        .append(" status=  "+   status + "\t\t")
        .append(" recordCount=  "+ recordCount + "\t\t")
        .append(" amount =   "+   amount + "\t\t")
        .append(" comment=  "+   comment + "\t\t")
        .append(" reqId=   "+   reqId + "\t\t")
        .append(" Payment Details :\n   ")
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
        .append(" favourOf :"+favourOf+"\n")
        .append(" checkNo :"+checkNo+"\n")
        .append(" cardNo :"+cardNo+"\n")
         .append(" checkNo :"+cardCvvNo+"\n")
	.append(" paymentStatus :"+ paymentStatus+"\n")
        .append("Query="+query);
        
        return buffer.toString();
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
            this. reqLists=null;    
    this. adminLists=null;    
    this. dbLists=null;    
    this.payment=null;    
    this. hlcArea=null;    
    this. zipCode=null;    
    this. memberType=null;    
    this. status=null;    
    this. recordCount=null;    
    this. amount=null;    
    this. comment=null;    
    this. reqId=null; 
    this. paymentId=null;
    this. userId=null;
    this. userTypeId=null;
    this. ccName=null;
    this. ccType=null;
    this.ccNumber=null;
    this. ccExpMonth=null;
    this. ccExpYear=null;
    this. ccCvvid=null;
    this. bankName=null;
    this.checkDate=null;
    this.checkNumber=null;
    this.paymentDate=null;
    this. paymentStatus=null;
    this. cardNo=null;
    this. cardCvvNo=null;
    this. favourOf=null;
    this. checkNo=null;
    this.query = null;
    }
    

}
