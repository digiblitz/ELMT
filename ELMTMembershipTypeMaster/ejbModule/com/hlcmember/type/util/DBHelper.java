/*
 * DBHelper.java
 *
 * Created on August 28, 2006, 6:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmember.type.util;

/**
 *
 * @author harmohan
 */
public class DBHelper {
    //tblMeeAreaMaster A, tblMeeMapStateZip B
    public static final String USEA_MEMBER_TYPE_MASTER = "tblMembershipTypeMaster";
    public static final String USEA_USERTYPE_MASTER ="tblUserTypeMaster";
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails"; 
    public static final String USEA_STATUS_MASTER = "tblMembershipStatusMaster";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_NONUSEA_MASTER = "tblNonUSEAOrgMaster"; 
    public static final String USEA_MMS_CNTMAIL_MASTER = "tblCountryMailPriceMaster"; 
    public static final String USEA_MMS_CONTACT_TYPE_MASTER = "tblContactTypeMaster";
    public static final String USEA_MMS_CONTACT_DETAIL = "tblContactDetails";
    public static final String USEA_MMS_HORSEMEMBERDETAILS = "tblHorseMemberDetails";
    public static final String USEA_MMS_DASHBOARD_HORSES="DASHBOARD_HORSES";
    
    public static final String USEA_MEE_AREAMASTER = "tblMeeAreaMaster";
    public static final String USEA_MEE_MAP_STATEZIP = "tblMeeMapStateZip";
    public static final String USEA_ANNUAL_MEMBERTYPEMASTER = "tblMeeAnnualMembershipTypeMaster";
    public static final String USEA_ACC_TXN_TYPE_DETAILS = "tblAccTransactionTypeDetails";
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    //tblMemberDetails A, tblNonUSEAOrgMaster B,tblCountryMailPriceMaster C, tblMembershipStatusMaster D
}
