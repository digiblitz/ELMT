/*
 * DBHelper.java
 *
 * Created on August 13, 2006, 3:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccommon.util;

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
    
    public static final String USEA_EVT_EDU_ACTIVITY_ORGANIZER = "tblMeeActivityOrganizerDetails";
    public static final String USEA_EVT_EDU_PUBLICATION = "tblMeeEduPublicationDetails";
    public static final String USEA_EVT_EDU_ACTIVITY_USER = "tblMeeEduActivityUserDetails";
    public static final String USEA_EVT_EDU_RECAP = "tblMeeEduActivityRecapDetails";
 
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
}
