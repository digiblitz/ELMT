/*
 * AnnualPriceDetailVO.java
 *
 * Created on November 6, 2006, 12:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import java.util.Date;

/**
 *
 * @author suresh
 */
public class HLCAnnualPriceDetailVO implements java.io.Serializable{
    
    /** Creates a new instance of AnnualPriceDetailVO */
    public HLCAnnualPriceDetailVO() {
    }
    
    private String ardPricemapId;
    private String ardId;
    private String firstName ;
    private String specificationName ;
    private String memTypeName ;
    private String amount;
    private Date addDate;
    private int totalTicket;
    private boolean addTktStaus;
    
       
    public void setArdPricemapId(String ardPricemapId) {
        this.ardPricemapId = ardPricemapId;
    }

    public void setArdId(String ardId) {
        this.ardId = ardId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public void setMemTypeName(String memTypeName) {
        this.memTypeName = memTypeName;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getArdPricemapId() {
        return ardPricemapId;
    }

    public String getArdId() {
        return ardId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public String getMemTypeName() {
        return memTypeName;
    }

    public String getAmount() {
        return amount;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public void setAddTktStaus(boolean addTktStaus) {
        this.addTktStaus = addTktStaus;
    }

    public int getTotalTicket() {
        return totalTicket;
    }

    public boolean isAddTktStaus() {
        return addTktStaus;
    }
    
}
