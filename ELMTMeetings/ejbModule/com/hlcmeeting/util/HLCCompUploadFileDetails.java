/*
 * CompUploadFileDetails.java
 *
 * Created on April 2, 2007, 12:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import java.sql.*;

/**
 *
 * @author karthikeyan
 */

public class HLCCompUploadFileDetails implements java.io.Serializable{
    
    private String upload_id;
    private String event_id;
    private String event_name;
    private String result_file_path;
    private boolean active_status = false;
    private boolean validation_status = false;
    private java.sql.Date upload_date;

    public void setValidation_status(boolean validation_status) {
        this.validation_status = validation_status;
    }

    public boolean isValidation_status() {
        return validation_status;
    }
       
    /** Creates a new instance of CompUploadFileDetails */
    public HLCCompUploadFileDetails() {
    }
    
    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getResult_file_path() {
        return result_file_path;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setResult_file_path(String result_file_path) {
        this.result_file_path = result_file_path;
    }

    public void setUpload_date(java.sql.Date upload_date) {
        this.upload_date = upload_date;
    }
    
    public String toString(){
        StringBuffer buffer = new StringBuffer()
        .append("event_id : " + this.event_id+"\n")
        .append("event_name : " + this.event_name+"\n")
        .append("result_file_path : " + this.result_file_path+"\n")
        .append("upload_date : " + this.upload_date.toString()+"\n")
        .append("upload_id : " + this.upload_id+"\n")
        .append("active_status : " + this.active_status+"\n")
        .append("validation_status : " + this.validation_status+"\n");
        
        return buffer.toString();
    }

    public void setUpload_id(String upload_id) {
        this.upload_id = upload_id;
    }

    public String getUpload_id() {
        return upload_id;
    }

    public boolean isActive_status() {
        return active_status;
    }

    public void setActive_status(boolean active_status) {
        this.active_status = active_status;
    }
    
}
