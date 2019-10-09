/*
 * AdsDetailedVO.java
 *
 * Created on October 12, 2006, 11:55 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCAdsDetailedVO implements java.io.Serializable {
    
    /** Creates a new instance of AdsDetailedVO */
    public HLCAdsDetailedVO() {
    }
    
    private String advDetailId;
    private String adsId;
    private String mediaId;
    private String mapId;
    private String issueId;
    private String dimId;
    private String mediaName;
    private String issueName;
    private String dispName;
    private String dispSubName;
    private String dimName;
    private String placement;
    private String amount;
    private boolean showStatus;

    
    

    
    public String getAdvDetailId() {
        return advDetailId;
    }
     
    public String getAdsId() {
        return adsId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getIssueName() {
        return issueName;
    }

    public String getDispName() {
        return dispName;
    }

    public String getDispSubName() {
        return dispSubName;
    }

    public String getDimName() {
        return dimName;
    }

    public String getPlacement() {
        return placement;
    }

    public String getAmount() {
        return amount;
    }
    
    public boolean isShowStatus() {
        return showStatus;
    }

    
    
    public void setAdvDetailId(String advDetailId) {
        this.advDetailId = advDetailId;
    }
    
    public void setAdsId(String adsId) {
        this.adsId = adsId;
    }
    
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public void setDispName(String dispName) {
        this.dispName = dispName;
    }

    public void setDispSubName(String dispSubName) {
        this.dispSubName = dispSubName;
    }

    public void setDimName(String dimName) {
        this.dimName = dimName;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
   
    
     public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    
    public String toString(){
       StringBuffer buffer = new StringBuffer()
       .append("advDetailId="+this.advDetailId+",")
       .append("adsId="+this.adsId+",")
       .append("mapId="+this.mapId+",")
       .append("issueId="+this.issueId+",")
       .append("dimId="+this.dimId+",")
       .append("mediaId="+this.mediaId+",")
       .append("mediaName="+this.mediaName+",")
       .append("issueName="+this.issueName+",")
       .append("dispName="+this.dispName+",")
       .append("dispSubName="+this.dispSubName+",")
       .append("dimName="+this.dimName+",")
       .append("placement="+this.placement+",")
       .append("amount="+this.amount+",")
       .append("showStatus="+this.showStatus);
      return buffer.toString();
  }

    public String getMediaId() {
        return mediaId;
    }

   
        
    public String getMapId() {
        return mapId;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getDimId() {
        return dimId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setDimId(String dimId) {
        this.dimId = dimId;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }
    
}
