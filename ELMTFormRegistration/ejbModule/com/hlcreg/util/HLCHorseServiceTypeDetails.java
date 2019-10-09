/*
 * HorseServiceTypeDetails.java
 *
 * Created on August 31, 2006, 6:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcreg.util;

/**
 *
 * @author harmohan
 */
public class HLCHorseServiceTypeDetails implements java.io.Serializable {
    private String horseMemberId;
    private String horseServiceTypeId;
    
    /** Creates a new instance of HorseServiceTypeDetails */
    public HLCHorseServiceTypeDetails() {
    }
    
    public HLCHorseServiceTypeDetails(String horseMemberId,String horseServiceTypeId ){
        this.horseMemberId = horseMemberId;
        this.horseServiceTypeId = horseServiceTypeId;
    }
    //Getter Method    
     public String getHorseMemberId() {
          return horseMemberId;
     }
     public String getHorseServiceTypeId() {
          return horseServiceTypeId;
     }
        
    //Setters methods
     public void setHorseMemberId(String horseMemberId) {
      this.horseMemberId = horseMemberId;
     }
     public void setHorseServiceTypeId(String horseServiceTypeId) {
      this.horseServiceTypeId = horseServiceTypeId;
     }
    
}
