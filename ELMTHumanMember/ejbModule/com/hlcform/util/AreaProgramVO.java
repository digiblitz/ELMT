/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hlcform.util;

import java.util.Date;

/**
 *
 * @author Radhadatta
 */
public class AreaProgramVO {
    
  private String areaMembHistoryId;
  private String areaMembTypId;
  private String areaId;
  private String progName;
  private int progYear;
  private String areaMembPrice;
  private String url;
  private String description;
  private int minAge;
  private int maxAge;
  private boolean upgradable;
  private String userId;
  private String membId;
  private String payId;
  private Date activeDate;
  private String areaName;
  private String transacTypId;
  
 
    public String getTransacTypId() {
        return transacTypId;
    }

    public void setTransacTypId(String transacTypId) {
        this.transacTypId = transacTypId;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaMembHistoryId(String areaMembHistoryId) {
        this.areaMembHistoryId = areaMembHistoryId;
    }

    public void setAreaMembPrice(String areaMembPrice) {
        this.areaMembPrice = areaMembPrice;
    }

    public void setAreaMembTypId(String areaMembTypId) {
        this.areaMembTypId = areaMembTypId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void setMembId(String membId) {
        this.membId = membId;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public void setProgYear(int progYear) {
        this.progYear = progYear;
    }

    public void setUpgradable(boolean upgradable) {
        this.upgradable = upgradable;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAreaMembHistoryId() {
        return areaMembHistoryId;
    }

    public String getAreaMembPrice() {
        return areaMembPrice;
    }

    public String getAreaMembTypId() {
        return areaMembTypId;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public String getMembId() {
        return membId;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getPayId() {
        return payId;
    }

    public String getProgName() {
        return progName;
    }

    public int getProgYear() {
        return progYear;
    }

    public String getUrl() {
        return url;
    }

    public String getUserId() {
        return userId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public boolean isUpgradable() {
        return upgradable;
    }
   

}
