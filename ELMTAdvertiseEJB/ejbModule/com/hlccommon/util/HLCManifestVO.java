/*
 * ManifestVO.java
 *
 * Created on October 12, 2006, 3:51 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCManifestVO implements java.io.Serializable {
    
    /** Creates a new instance of ManifestVO */
    public HLCManifestVO() {
    }
    private String advManifestId;
    private String advDetailId;
    private String advertisementId;
    private String mapId;
    private String issueId;
    private String dimId;
    private String mediaId;
    private String mediaName;
    private String issueName;
    private String displayTypeName;
    private String displaySubTypeName;
    private String dimensionTypeName;
    private String placement;
    private String splInstructions;
    private String pageNo;
    
           
    public String getAdvManifestId() {
        return advManifestId;
    }

    public String getAdvDetailId() {
        return advDetailId;
    }

    public String getAdvertisementId() {
        return advertisementId;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getIssueName() {
        return issueName;
    }

    public String getDisplayTypeName() {
        return displayTypeName;
    }

    public String getDisplaySubTypeName() {
        return displaySubTypeName;
    }

    public String getDimensionTypeName() {
        return dimensionTypeName;
    }

    public String getPlacement() {
        return placement;
    }

    public String getSplInstructions() {
        return splInstructions;
    }

    public String getPageNo() {
        return pageNo;
    }
    
   
    public void setAdvManifestId(String advManifestId) {
        this.advManifestId = advManifestId;
    }

    public void setAdvDetailId(String advDetailId) {
        this.advDetailId = advDetailId;
    }

    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public void setDisplayTypeName(String displayTypeName) {
        this.displayTypeName = displayTypeName;
    }

    public void setDisplaySubTypeName(String displaySubTypeName) {
        this.displaySubTypeName = displaySubTypeName;
    }

    public void setDimensionTypeName(String dimensionTypeName) {
        this.dimensionTypeName = dimensionTypeName;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public void setSplInstructions(String splInstructions) {
        this.splInstructions = splInstructions;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
        
    public String toString(){
       StringBuffer buffer = new StringBuffer()
       .append("advManifestId="+this.advManifestId+",")
       .append("advDetailId="+this.advDetailId+",")
       .append("advertisementId="+this.advertisementId+",")
       .append("mapId="+this.mapId+",")
       .append("issueId="+this.issueId+",")
       .append("dimId="+this.dimId+",")
       .append("mediaId="+this.mediaId+",")
       .append("mediaName="+this.mediaName+",")
       .append("issueName="+this.issueName+",")
       .append("displayTypeName="+this.displayTypeName+",")
       .append("displaySubTypeName="+this.displaySubTypeName+",")
       .append("dimensionTypeName="+this.dimensionTypeName+",")
       .append("placement="+this.placement)
       .append("splInstructions="+this.splInstructions+",")
       .append("pageNo"+this.pageNo);
      return buffer.toString();
  }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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
    
    
}
