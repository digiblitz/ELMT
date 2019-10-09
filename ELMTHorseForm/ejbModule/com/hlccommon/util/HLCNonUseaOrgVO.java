/*
 * NonUseaOrgVO.java
 *
 * Created on November 17, 2006, 11:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

/**
 *
 * @author suresh
 */
public class HLCNonUseaOrgVO implements java.io.Serializable{
    
    /** Creates a new instance of NonUseaOrgVO */
    public HLCNonUseaOrgVO() {
    }
    
    private String nonuseaOrgMapId;
    private String memberId;
    private String nonuseaOrgId;
    private String nonuseaPrice;
    private String nonuseaOrgName;
    private String nonuseaMemberId;
       
    public void setNonuseaOrgMapId(String nonuseaOrgMapId) {
        this.nonuseaOrgMapId = nonuseaOrgMapId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setNonuseaOrgId(String nonuseaOrgId) {
        this.nonuseaOrgId = nonuseaOrgId;
    }

    public void setNonuseaPrice(String nonuseaPrice) {
        this.nonuseaPrice = nonuseaPrice;
    }

    public void setNonuseaOrgName(String nonuseaOrgName) {
        this.nonuseaOrgName = nonuseaOrgName;
    }

    public String getNonuseaOrgMapId() {
        return nonuseaOrgMapId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getNonuseaOrgId() {
        return nonuseaOrgId;
    }

    public String getNonuseaPrice() {
        return nonuseaPrice;
    }

    public String getNonuseaOrgName() {
        return nonuseaOrgName;
    }

    public String getNonuseaMemberId() {
        return nonuseaMemberId;
    }

    public void setNonuseaMemberId(String nonuseaMemberId) {
        this.nonuseaMemberId = nonuseaMemberId;
    }
}
