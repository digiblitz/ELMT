/*
 * AdvertisementVO.java
 *
 * Created on September 10, 2006, 2:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author Shiva Kumar Subbiaha
 */
import java.io.Serializable;


public class HLCAdvertisementVO implements Serializable{

    private String  dimensionId;
    private String  mediaType;
    private String  displayType;
    private String  displaySubType;
    private String  dimensionType;
    private String  dimensionalName;
    private String  height;
    private String  width;
    private String  dimensionalUnit;
    private String  imagePath;


    public HLCAdvertisementVO(){}

    public String getDimensionId() {
        return dimensionId;
    }

    public String getDimensionalName() {
        return dimensionalName;
    }

    public String getDisplaySubType() {
        return displaySubType;
    }

    public String getDimensionType() {
        return dimensionType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public String getHeight() {
        return height;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getWidth() {
        return width;
    }

    public String getDimensionalUnit() {
        return dimensionalUnit;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId;
    }

    public void setDimensionType(String dimensionType) {
        this.dimensionType = dimensionType;
    }

    public void setDimensionalName(String dimensionalName) {
        this.dimensionalName = dimensionalName;
    }

    public void setDimensionalUnit(String dimensionalUnit) {
        this.dimensionalUnit = dimensionalUnit;
    }

    public void setDisplaySubType(String displaySubType) {
        this.displaySubType = displaySubType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setWidth(String width) {
        this.width = width;
    }
  
  public String toString(){
  
   StringBuffer buffer = new StringBuffer()
   .append("DimensionId="+this.dimensionId+",")
   .append("MediaType="+this.mediaType+",")
   .append("DisplayType="+this.displayType+",")
   .append("DisplaySubType="+this.displaySubType+",")
   .append("DimensionType="+this.dimensionType+",")
   .append("DimensionalName="+this.dimensionalName+",")
   .append("Height="+this.height+",")
   .append("Width"+this.width+",")
   .append("DimensionalUnit="+this.dimensionalUnit+",")
   .append("ImagePath="+this.imagePath);
  
  return buffer.toString();
  }
}

