/*
 * AreaStateZipVO.java
 *
 * Created on March 30, 2007, 11:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

/**
 *
 * @author Hari
 */
public class HLCAreaStateZipVO {
    
    /** Creates a new instance of AreaStateZipVO */
    public HLCAreaStateZipVO() {
    }
        String area_id;
        String area_code;
        String area_name;
        String state_id;
        String state_name;
        String state_code;
        String map_zip_id;
        String zip_code_from;   
        String zip_code_to;
    

    public String getArea_code() {
        return area_code;
    }

    public String getArea_id() {
        return area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public String getMap_zip_id() {
        return map_zip_id;
    }

    public String getState_code() {
        return state_code;
    }

    public String getState_id() {
        return state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public String getZip_code_from() {
        return zip_code_from;
    }

    public String getZip_code_to() {
        return zip_code_to;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public void setMap_zip_id(String map_zip_id) {
        this.map_zip_id = map_zip_id;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public void setZip_code_from(String zip_code_from) {
        this.zip_code_from = zip_code_from;
    }

    public void setZip_code_to(String zip_code_to) {
        this.zip_code_to = zip_code_to;
    }
}
