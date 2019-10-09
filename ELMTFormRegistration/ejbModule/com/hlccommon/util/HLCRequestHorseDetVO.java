/*
 * RequestHorseDetVO.java
 *
 * Created on February 7, 2007, 6:10 PM
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
public class HLCRequestHorseDetVO implements Serializable {
    
    /** Creates a new instance of RequestHorseDetVO */
    public HLCRequestHorseDetVO() {
    }
    
    private String requestId;
    private String horseMemberId;
    private String paymentId;
    private String reqOwnerId;
    private String reqRiderId;
    private String reqHorseName;
    private String reqHorseRegName;
    private String requestedBy;
    private float reqAmount;
    private boolean reqStatus;
    private Date reqDate;
    private boolean addPaymentStatus;
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setHorseMemberId(String horseMemberId) {
        this.horseMemberId = horseMemberId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setReqOwnerId(String reqOwnerId) {
        this.reqOwnerId = reqOwnerId;
    }

    public void setReqRiderId(String reqRiderId) {
        this.reqRiderId = reqRiderId;
    }

    public void setReqHorseName(String reqHorseName) {
        this.reqHorseName = reqHorseName;
    }

    public void setReqHorseRegName(String reqHorseRegName) {
        this.reqHorseRegName = reqHorseRegName;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public void setReqAmount(float reqAmount) {
        this.reqAmount = reqAmount;
    }

    public void setReqStatus(boolean reqStatus) {
        this.reqStatus = reqStatus;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getHorseMemberId() {
        return horseMemberId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getReqOwnerId() {
        return reqOwnerId;
    }

    public String getReqRiderId() {
        return reqRiderId;
    }

    public String getReqHorseName() {
        return reqHorseName;
    }

    public String getReqHorseRegName() {
        return reqHorseRegName;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public float getReqAmount() {
        return reqAmount;
    }

    public boolean isReqStatus() {
        return reqStatus;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setAddPaymentStatus(boolean addPaymentStatus) {
        this.addPaymentStatus = addPaymentStatus;
    }

    public boolean isAddPaymentStatus() {
        return addPaymentStatus;
    }
}
