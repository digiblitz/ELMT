/*
 * DBHelper.java
 *
 * Created on August 13, 2006, 3:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmro.util;

import com.hlcmro.exception.HLCMissingPrimaryKeyException;
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
    public static final String USEA_MMS_EVENTDETAILS = "tblMeeEventDetails";
    
    public static final String USEA_MRO_ACCOMMODATION_DETAILS = "tblMeeAccommodationDetails";
    public static final String USEA_MRO_COGGINS_DETAILS = "tblMeeCogginsDetails";
    public static final String USEA_MRO_CROSS_COUNTRY_DETAILS = "tblMeeCrossCountryDetails";
    public static final String USEA_MRO_EVENT_TYPE_DETAILS = "tblMeeEventTypeDetails";
    public static final String USEA_MRO_HORSE_DRESSAGE_DETAILS = "tblMeeHorseDressageDetails";
    
    public static final String USEA_MRO_JUDGE_DETAILS = "tblMeeJudgeDetails";
    public static final String USEA_MRO_JUDGE_TYPE_MSATER = "tblMeeJudgeTypeMaster ";
    public static final String USEA_MRO_EVENTDETAILS = "tblMeeEventDetails";
    public static final String USEA_MRO_OTHER_DETAILS = "tblMeeOtherDetails";
    public static final String USEA_MRO_REFUND_RULE_DETAILS = "tblMeeRefundRuleDetails";
    public static final String USEA_MRO_TENTATIVE_TIME_SCHEDULE = "tblMeeTentativeTimeSchedule";

    public static final String USEA_MRO_TYPE_MASTER = "tblMeeEventTypeMaster";
     public static final String USEA_MRO_DIVISION_MASTER = "tblMeeEventDivisionMaster";
    public static final String USEA_MRO_LEVEL_MASTER = "tblMeeEventLevelMaster";
    public static final String USEA_MRO_MAP_EVENT_LEVEL = "tblMeeMapEventLevel";
    
    public static final String USEA_MRO_SUB_LEVEL_MASTER = "tblMeeEventSubLevelMaster";
    public static final String USEA_MRO_ARENA_SIZE_MASTER = "tblMeeArenaSizeMaster";
    public static final String USEA_MRO_MAP_EVENT_SUB_LEVEL = "tblMeeMapEventSubLevel";

    public static final String USEA_MRO_REFUND_RULE_MASTER = "tblMeeRefundRuleMaster";
    public static final String USEA_MRO_REFUND_SUB_RULE_SUB_MASTER = "tblMeeRefundRuleSubTypeMaster";
    public static final String USEA_MRO_MAP_REFUND_RULE = "tblMeeMapRefundRule";    
    
    public static final String USEA_MRO_MAP_STATE_ZIP = "tblMeeMapStateZip";
    public static final String USEA_MRO_MAP_AREA_CHAIR = "tblMeeMapAreaChair";
    public static final String USEA_MRO_STATE_MASTER = "tblMeeStateMaster";
    public static final String USEA_MRO_AREA_MASTER = "tblMeeAreaMaster";
    public static final String USEA_ADV_ISSUE_MASTER = "tblAdvIssueMaster";
    public static final String USEA_MEE_Event_HISTORY_DETAILS = "tblMeeEventHistoryDetails";
    
    public static final String USEA_ISSUE_MASTER = "tblAdvIssueMaster";
    public static final String USEA_EVENT_REG_PRICE_MASTER = "tblMeeEventRegistrationPriceMaster";
    public static final String USEA_EVENT_REG_PRICE_DETAILS = "tblMeeEventRegistrationPriceDetails";
    public static final String USEA_EVENT_REG_DATES = "tblMeeEventRegistrationDates";
    public static final String USEA_EVENT_HIST_DETAILS = "tblMeeEventHistoryDetails";
    /*  added */
    
    
     /** Creates a new instance of DBHelper */
       public static final long getNextEventId(Connection con) throws SQLException ,HLCMissingPrimaryKeyException{
        Debug.print("DBHelper getNextAccountId");
        return getNextId(con,"tblMeeEventDetails");
    }
    
     private static final long getNextId(Connection con,String table)
        throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("DBHelper getNextId");
        String selectStatement = "SELECT max(event_id) from " + USEA_MMS_EVENTDETAILS + " WHERE event_id = ? ";
        
        
       // String updateStatement = "update " + table + " " + "set id = id + 1 ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);

        ResultSet rs = prepSelect.executeQuery();
        rs.next();
        long nextId = rs.getLong(1) + 1;
        
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
     // getNextId

    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        // This method returns a sql.Date version of the util.Date arg.
        return new java.sql.Date(inDate.getTime());
    }
    
    
    
}
