/*
 * PriceMatrixVO.java
 *
 * Created on November 19, 2007, 12:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcutil;

import java.util.Date;

/**
 *
 * @author Vidhya
 */
public class HLCPriceMatrixVO {
    
    /** Creates a new instance of PriceMatrixVO */
    public HLCPriceMatrixVO() {
    }
        private String priceId;
        private String eventId;
        private String entryItemId;
        private String entryItemName;
        private String eventTypeId;
        private String eventTypeName;
        private String eventLevelId;
        private String eventLevelName;
        private String itemType;
        private String chargeType;
        private float dueDatePrice;
        private float afterDueDatePrice;
        private float depositPrice;
        private int quantity;
        private String approveStatus;
        private String approvedBY;
        private Date addDate;
        private boolean activeStatus;
        private boolean championshipStatus;
        private String areaId;
        private String areaName;
        
    public String getChargeType() {
        return chargeType;
    }

    public String getEntryItemId() {
        return entryItemId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventLevelId() {
        return eventLevelId;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public String getItemType() {
        return itemType;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public void setEntryItemId(String entryItemId) {
        this.entryItemId = entryItemId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setEventLevelId(String eventLevelId) {
        this.eventLevelId = eventLevelId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public float getAfterDueDatePrice() {
        return afterDueDatePrice;
    }

    public String getApprovedBY() {
        return approvedBY;
    }

    public float getDepositPrice() {
        return depositPrice;
    }

    public float getDueDatePrice() {
        return dueDatePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setAfterDueDatePrice(float afterDueDatePrice) {
        this.afterDueDatePrice = afterDueDatePrice;
    }

    public void setApprovedBY(String approvedBY) {
        this.approvedBY = approvedBY;
    }

    public void setDepositPrice(float depositPrice) {
        this.depositPrice = depositPrice;
    }

    public void setDueDatePrice(float dueDatePrice) {
        this.dueDatePrice = dueDatePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getChampionshipStatus() {
        return championshipStatus;
    }

    public void setChampionshipStatus(boolean championshipStatus) {
        this.championshipStatus = championshipStatus;
    }
   
        

    public String getEntryItemName() {
        return entryItemName;
    }

    public String getEventLevelName() {
        return eventLevelName;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEntryItemName(String entryItemName) {
        this.entryItemName = entryItemName;
    }

    public void setEventLevelName(String eventLevelName) {
        this.eventLevelName = eventLevelName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
  
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
    
    public String getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
