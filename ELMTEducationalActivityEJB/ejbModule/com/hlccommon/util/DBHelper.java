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
    
    public static final String USEA_EVT_EDU_ACTIVITY_ORGANIZER = "tblMeeActivityOrganizerDetails";
    public static final String USEA_EVT_EDU_PUBLICATION = "tblMeeEduPublicationDetails";
    public static final String USEA_EVT_EDU_ACTIVITY_USER = "tblMeeEduActivityUserDetails";
    public static final String USEA_EVT_EDU_RECAP = "tblMeeEduActivityRecapDetails";
    public static final String USEA_EVT_EDU_ACT_TYPE_MASTER = "tblMeeEduActivityTypeMaster";
    public static final String USEA_EVT_EDU_ACT_RECAP_DETAIL = "tblMeeEduActivityRecapDetails";    
   // 
    public static final String USEA_EVT_EDU_USER_DETAIL = "tblUserMaster";   
    //
   public static final String USEA_EVT_EDU_USER_CONTACT_DETAIL = "tblContactDetails"; 
    public static final String USEA_EVT_EDU_MEMBER_CONTACT_DETAIL = "tblMemberDetails";
     public static final String USEA_EVT_EDU_AREA_MASTER = "tblMeeAreaMaster"; 
    public static final String USEA_MEMBERSHIP_STATUS_MASTER = "tblMembershipStatusMaster";
    
    
    
    
    
    
    

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
