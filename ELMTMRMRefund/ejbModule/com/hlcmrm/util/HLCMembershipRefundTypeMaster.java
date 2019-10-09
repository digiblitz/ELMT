/*
 * MembershipRefundTypeMaster.java
 *
 * Created on August 26, 2006, 12:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author harmohan
 */
public class HLCMembershipRefundTypeMaster implements Serializable {
    private String refundTypeName;
    private String refundDescription;

    /** Creates a new instance of MembershipRefundTypeMaster */
    public HLCMembershipRefundTypeMaster() {
    }
    
    public HLCMembershipRefundTypeMaster(String refundTypeName, String refundDescription ) {
        
        this.refundDescription = refundDescription;
        this.refundTypeName = refundTypeName;
    }
    
    //getter Methods
    public String getRefundDescription() {
        return refundDescription;
    }
    public String getRefundTypeName() {
        return refundTypeName;
    }
    
    //Setter Methods
    public void setRefundDescription(String refundDescription){
        this.refundDescription = refundDescription;
    }
    public void setRefundTypeName(String refundTypeName){
        this.refundTypeName = refundTypeName;
    }
    
}