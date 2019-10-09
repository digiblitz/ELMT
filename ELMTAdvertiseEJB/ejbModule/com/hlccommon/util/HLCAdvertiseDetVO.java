/*
 * AdvertiseDetVO.java
 *
 * Created on September 9, 2006, 6:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCAdvertiseDetVO implements java.io.Serializable {
    
    private String advDetailId;
    private String advertisementId;
    private String issueId;
    private String advMapId;
    private String dimensionId;
    private String placement;
    private String discount;
    private String amount;    
    
     public HLCAdvertiseDetVO(){
         
     }
    
    /** Creates a new instance of AdvertiseDetVO */
    public HLCAdvertiseDetVO(String advDetailId, String advertisementId, String issueId,
           String advMapId, String dimensionId, String placement, String discount, String amount) {
        this.advDetailId = advDetailId;
        this.advertisementId = advertisementId;
        this.issueId = issueId;
        this.advMapId = advMapId;
        this.dimensionId = dimensionId;
        this.placement = placement;
        this.discount = discount;
        this.amount = amount;
    }
    
    public void setAdvDetailId(String advDetailId){
        this.advDetailId = advDetailId;
    }
     
    
    public void setAdvertisementId(String advertisementId){
        this.advertisementId = advertisementId;
    }
    
    public void setIssueId(String issueId){
        this.issueId = issueId;
    }
    
    public void setAdvMapId(String advMapId){
         this.advMapId = advMapId;
    }

    public void setDimensionId(String dimensionId){
         this.dimensionId = dimensionId;
    }
    
    public void setPlacement(String placement) {
        this.placement = placement;
    }
    
    public void setDiscount(String discount){
         this.discount = discount;
    }
    
    public void setAmount(String amount){
        this.amount = amount;
    }
     
    

    
    
    public String getAdvDetailId(){
         return advDetailId;
    }
    
    public String getAdvertisementId(){
         return advertisementId;
    }
    
    public String getIssueId(){
         return issueId;
    }
    
    public String getAdvMapId(){
         return advMapId;
    }
    
    public String getDimensionId(){
         return dimensionId;
    }
    public String getPlacement() {
        return placement;
    }
   
    public String getDiscount(){
         return discount;
    }
    
    public String getAmount(){
         return amount;
    }
    
}
