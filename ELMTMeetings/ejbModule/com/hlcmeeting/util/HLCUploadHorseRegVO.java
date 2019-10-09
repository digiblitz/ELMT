/*
 * UploadHorseRegVO.java
 *
 * Created on April 11, 2007, 3:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import java.sql.*;
/**
 *
 * @author Punitha
 */
public class HLCUploadHorseRegVO {
    
    /** Creates a new instance of UploadHorseRegVO */
    public HLCUploadHorseRegVO() {
    }
    private String reg_upload_id;
    private String event_id;
    private String event_type_id;
    private String event_name;
    private String registration_file_path;
    private boolean active_status = false;
    private boolean validation_status = false;
    private java.sql.Date upload_date;
  
    
   

    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }

    public void setEvent_type_id(String event_type_id) {
        this.event_type_id = event_type_id;
    }

    public void setValidation_status(boolean validation_status) {
        this.validation_status = validation_status;
    }

    public void setReg_upload_id(String reg_upload_id) {
        this.reg_upload_id = reg_upload_id;
    }

    public void setRegistration_file_path(String registration_file_path) {
        this.registration_file_path = registration_file_path;
    }
    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setUpload_date(java.sql.Date upload_date) {
        this.upload_date = upload_date;
    }
     public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public Date getUpload_date() {
        return upload_date;
    }
    public boolean isActive_status() {
        return active_status;
    }
    
    public String getEvent_type_id() {
        return event_type_id;
    }

    public String getReg_upload_id() {
        return reg_upload_id;
    }

    public String getRegistration_file_path() {
        return registration_file_path;
    }

    public boolean isValidation_status() {
        return validation_status;
    }
    
    public String toString(){
        StringBuffer buffer = new StringBuffer()
        .append("event_id : " + this.event_id+"\n")
        .append("event_name : " + this.event_name+"\n")
        .append("registration_file_path : " + this.registration_file_path+"\n")
        .append("upload_date : " + this.upload_date.toString()+"\n")
        .append("reg_upload_id : " + this.reg_upload_id+"\n")
        .append("active_status : " + this.active_status+"\n");
        
        return buffer.toString();
    }

    

 

   
    
}
