/*
 * DBHelper.java
 *
 * Created on August 13, 2006, 3:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

import com.hlccommon.exception.HLCMissingPrimaryKeyException;
import javax.sql.*;
import java.sql.*;

/**
 *
 * @author suresh
 */
public final class DBHelper {
    
    public static final String USEA_ORG_RENEWAL = "tblMeeEventOrganizerRenewalDetails"; 
    public static final String USEA_PAYMENT = "tblUserPaymentDetails";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails";
    public static final String USEA_SPNR_BILL = "tblBillingScheduleDetails"; 
    public static final String USEA_SPNR_SPONSOR = "tblSponsors";
    public static final String USEA_SPNR_SPONSORPLAN = "tblSponsorshipPlanMaster";
    public static final String USEA_SPNR_SCHE_MASTER = "tblBillingScheduleMaster";
    public static final String USEA_SPNR_SCHE_DETAILS = "tblBillingScheduleDetails"; 
    
    public static final String USEA_ADV_MEDIA = "tblAdvMediaMaster";
    public static final String USEA_ADV_DIMENSION_MASTER = "tblAdvDimensionTypeMaster"; 
    public static final String USEA_ADV_ISSUE_MASTER = "tblAdvIssueMaster";
    
    public static final String USEA_ADV_DISPLAY_TYPE = "tblAdvDisplayTypeMaster";
    public static final String USEA_ADV_DISPLAY_SUB_TYPE = "tblAdvDisplaySubTypes"; 
    public static final String USEA_ADV_DIMENSION_DETAILS = "tblAdvDimensionDetails";
    public static final String USEA_ADV_MAP_PRICE = "tblAdvMapPrice";
    
    public static final String USEA_ADV_ADS_DETAILS = "tblAdvertisementDetails";
    public static final String USEA_ADV_ADVERTISER = "tblAdvertisers";
    
    public static final String USEA_ROLE_ROLE_MASTER = "tblRoleMaster";
    public static final String USEA_ROLE_PRIVILEGE_MASTER = "tblPrivilegeMaster";
    public static final String USEA_ROLE_MAP_PRIVILEGE  = "tblMapPrivilege";
    public static final String USEA_ROLE_MAP_USER_PRIVILEGE = "tblMapUserPrivilege";
    
    

    public static final long getNextEventId(Connection con) throws SQLException ,HLCMissingPrimaryKeyException{
        Debug.print("DBHelper getNextAccountId");
        return getNextId(con,"tblMeeEventDetails");
    }

    private static final long getNextId(Connection con,String table)
    throws SQLException ,HLCMissingPrimaryKeyException {
    Debug.print("DBHelper getNextId");
    String selectStatement = "SELECT max(event_id) from " + table + " WHERE event_id = ? ";

    // String updateStatement = "update " + table + " " + "set id = id + 1 ";

    PreparedStatement prepSelect = con.prepareStatement(selectStatement);

    ResultSet rs = prepSelect.executeQuery();
    rs.next();

    long nextId = rs.getLong(1);
    if(nextId==0){
    nextId = 10000;
    }
    else{
    nextId = nextId+1;
    }

    rs.close();
    prepSelect.close();

    // if (nextId <= "") {
    //throw new MissingPrimaryKeyException("Table " + table +
       // " is empty.");
    //  }

    return nextId;
    }

    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
}
