/*
 * AreaChairsVO.java
 *
 * Created on October 9, 2006, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

import java.io.Serializable;

/**
 *
 * @author manas
 */
public class HLCAreaChairsVO implements Serializable {
    
    /** Creates a new instance of AreaChairsVO */
    public HLCAreaChairsVO() {
    }
    
        private String  mapAreaId;
        private String areaId;
        private String areaChairId;
        private String areaCode;
        private String areaName;
        private String userId;
        private String userCode;
        private String firstName;
        private String middleName;
        private String lastName;
        private String emailId;
        
        
        
        
       


    public String getMapAreaId() {
        return mapAreaId;
    }
    
    public String getAreaId() {
        return areaId;
    }
 
    public String getAreaChairId() {
        return areaChairId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getAreaName() {
        return areaName;
    }
    
    public String getUserCode() {
        return userCode;
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
  
    public String getLastName() {
        return lastName;
    }

      
    public String getEmailId() {
        return emailId;
    }
    
    public void setMapAreaId(String mapAreaId) {
        this.mapAreaId = mapAreaId;
    }
    
    

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    
    
    public void setAreaChairId(String areaChairId) {
        this.areaChairId = areaChairId;
    }
    
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
   
    
    public String toString(){
   StringBuffer buffer = new StringBuffer()
   .append("mapAreaId="+this.mapAreaId+",")
   .append("areaId="+this.areaId+",")
   .append("areaChairId="+this.areaChairId+",")
   .append("areaCode="+this.areaCode+",")
   .append("areaName="+this.areaName+",")
   .append("userId="+this.userId+",")
   .append("userCode="+this.userCode+",")
   .append("firstName"+this.firstName+",")
   .append("middleName"+this.middleName+",")
   .append("lastName="+this.lastName+",")
   .append("emailId="+this.emailId);
  return buffer.toString();
  } 
    
}
