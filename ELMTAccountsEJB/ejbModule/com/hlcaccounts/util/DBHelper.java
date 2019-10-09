/*
 * DBHelper.java
 *
 * Created on May 13, 2007, 2:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcaccounts.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author karthikeyan
 */
public class DBHelper {
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static final String USEA_ACC_TXN_DETAILS = "tblAccTransactionDetails";
    public static final String USEA_ACC_CLASS_DETAILS = "tblAccClassMaster";
    public static final String USEA_ACC_MASTER = "tblAccAccountsMaster";
    public static final String USEA_ACC_TXN_TYPE_DETAILS = "tblAccTransactionTypeDetails";
    public static final String USEA_MEMB_TYPE_MASTER = "tblMembershipTypeMaster";
    public static final String USEA_DONATION_DETAILS = "tblDonationDetails";
    public static final String USEA_SERVICE_TYPE_MASTER = "tblHorseServiceTypeMaster";
    public static final String USEA_ACC_ITEM_MASTER = "tblAccItemMaster";
    public static final String USEA_ClASS = "tblAccClassMaster";
    public static final String USEA_AREA_MEMBER_TYPE_MASTER = "tblAreaMemberTypeMaster";
    public static final String USEA_MEE_EVENT_REG_Price_MASTER = "tblMeeEventRegistrationPriceMaster";
    public static final String USEA_MEE_MAP_STATE_ZIP = "tblMeeMapStateZip";
    public static final String USEA_MEE_AREA_MASTER = "tblMeeAreaMaster";
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
     public static final String dateToString(java.util.Date inDate) {
         SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

         String strDate = formatter.format(inDate);
         
        return strDate;
    }
    
}
