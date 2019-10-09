/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
/*
 * DBHelper.java
 *
 * Created on August 24, 2006, 12:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;

import com.hlcmeeting.exception.HLCPrimaryKeyException;
import java.text.SimpleDateFormat;
import java.sql.*;

/**
 *
 * @author harmohan
 */
public final class DBHelper {
    
    public static final String USEA_CONTACT_DETAILS = "tblContactDetails"; 
    public static final String USEA_PAYMENT = "tblUserPaymentDetails";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster";
    public static final String USEA_MMS_MEMBERTYPEMASTER = "tblMeeAnnMemTypeMaster";
    public static final String USEA_ANNUAL_MEMBERTYPEMASTER = "tblMeeAnnualMembershipTypeMaster";
    public static final String USEA_ANNUAL_TICKETDETAILS = "tblMeeAnnualMeetingTicketDetails";
    
    
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails"; 
    public static final String USEA_ANNUAL_SPECFICATION = "tblMeeAnnualSpecificationMaster";
    public static final String USEA__ANNUAL_PRICE = "tblMeeAnnualPriceDetails";
    public static final String USEA_ANNUAL_REGISTRATION = "tblMeeAnnualRegistrationDetails";
    public static final String USEA_ANNUAL_REGISTRATION_PRICE_DETAILS  = "tblMeeAnnualRegistrationPriceDetails";
    public static final String USEA_ANNUAL_DETAILS = "tblMeeAnnualDetails";
    public static final String USEA_AREA_MASTER = "tblMeeAreaMaster";
    public static final String USEA_INSTRUCTOR_PRICEMASTER = "tblMeeInstructorPriceMaster";
    public static final String USEA_INSTRUCTOR_DETAILS = "tblMeeInstructorDetails";
    public static final String USEA_EVENT_REGISTRATION_MASTER = "tblMeeEventRegistrationTypeMaster";
    public static final String USEA_ICP_USER_DETAIL = "tblMeeICPUserDetails";
    public static final String USEA_CONTACT_TYPE_MASTER = "tblContactTypeMaster";
    public static final String USEA_ACTIVITY_CATEGORY = "tblMeeAnnualActivityTypeMaster";
    public static final String USEA_SPECIFICATION_MASTER = "tblMeeAnnualSpecificationMaster";
    public static final String USEA_MEMBERSHIP_TYPE_MASTER = "tblMembershipTypeMaster";
    public static final String USEA_PAYMENT_DETAILS = "tblUserPaymentDetails";
    public static final String USEA_COMP_RESULT_FILE_DETAILS = "tblMeeCompResultFileDetails";
    public static final String USEA_COMP_REG_DETAILS = "tblMeeCompRegistrationDetails";
    public static final String USEA_COMP_RESULT_DETAILS = "Dashboard_Comp_Results";
    public static final String USEA_EVENT_TYPE_DETAILS = "tblMeeEventTypeMaster";
    public static final String USEA_TEMP_COMP_RESULT = "tblMeeTempCompResults";
    public static final String USEA_COMP_QUAL_DETAILS = "tblMeeCompQualificationDetails";
    public static final String USEA_COMP_RESULT_FIELDMASTER = "tblMeeCompResultFieldMaster";
    public static final String USEA_COMP_RESULT_FIELDS = "tblMeeMapCompResultFields";    
    public static final String USEA_COMP_RESULT_DETAIL = "tblMeeCompResultDetails";
    public static final String USEA_COMP_EXCPT_RESULT_DETAIL = "tblMeeExcpCompResultDetails";
    public static final String USEA_EVENT_DIVISION_MASTER = "tblMeeEventDivisionMaster";
    public static final String USEA_EVENT_DETAILS = "tblMeeEventDetails";
    
    public static final String USEA_ISSUE_MASTER = "tblAdvIssueMaster";
    public static final String USEA_EVENT_REG_PRICE_MASTER = "tblMeeEventRegistrationPriceMaster";
    public static final String USEA_EVENT_REG_PRICE_DETAILS = "tblMeeEventRegistrationPriceDetails";
    public static final String USEA_EVENT_REG_DATES = "tblMeeEventRegistrationDates";
    
    //tblMeeICPUserDetails
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
     public static final String dateToString(java.util.Date inDate) {
         SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

         String strDate = formatter.format(inDate);
         
        return strDate;
    }
    
   /* public static final String getUserId(Connection con, String emailId) throws SQLException ,PrimaryKeyException{
        Debug.print("DBHelper getUserId");
        return getId(con,"tblUserMaster", emailId);
    }
    
    
    private static final String getId(Connection con,String table, String emailId)
    throws SQLException ,PrimaryKeyException {
        Debug.print("DBHelper getId");
        String selectStatement = "SELECT user_id from " + table + " WHERE email_id = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, emailId.trim());
        ResultSet rs = prepSelect.executeQuery();
        String userId = null;
        if (rs.next()) {
             userId = rs.getString(1);
        }
        Debug.print("USer ID is : "+userId);
        return userId;
    }
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }*/
    // getEventRegistrationTypeId(con, eventRegistrationTypeName)
     public static final String getEventRegistrationTypeId (Connection con, String eventRegistrationTypeName)throws SQLException, HLCPrimaryKeyException {
         String eventRegistrationTypeId = null;
         String selectStatement = "SELECT event_registration_type_id from " + USEA_EVENT_REGISTRATION_MASTER + " WHERE event_registration_type_name = ? ";
         
         PreparedStatement prepSelect = con.prepareStatement(selectStatement);
         prepSelect.setString(1,eventRegistrationTypeName);
         ResultSet rs = prepSelect.executeQuery();
         String meetingId = null;
         while (rs.next()) {
             eventRegistrationTypeId = rs.getString(1);
         }
         
         return eventRegistrationTypeId;
     }
     
     public static final String getAreaId (Connection con, String areaName)throws SQLException, HLCPrimaryKeyException {
         String araeId = null;
         String selectStatement = "SELECT area_id from " + USEA_AREA_MASTER + " WHERE area_name = ? ";
         
         PreparedStatement prepSelect = con.prepareStatement(selectStatement);
         prepSelect.setString(1,areaName);
         ResultSet rs = prepSelect.executeQuery();
         String meetingId = null;
         while (rs.next()) {
             araeId = rs.getString(1);
         }
         
         return araeId;
     }
    
     public static final String getNextMeetingId(Connection con) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getNextMeetingId");
        return getNextMeetingId(con,"tblMeeAnnualDetails");
     }
    
    
    private static final String getNextMeetingId(Connection con,String table)  throws SQLException ,HLCPrimaryKeyException {
        Debug.print("DBHelper getNextMeetingId");
        String selectStatement = "SELECT annual_meeting_id from " + table + " order by annual_meeting_id ASC ";
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        ResultSet rs = prepSelect.executeQuery();
        String meetingId = null;
        while (rs.next()) {
             meetingId = rs.getString(1);
        }
        Debug.print("Meeting ID : "+meetingId);
        long nextId = 0;
        if (meetingId != null) {
            nextId = Long.valueOf(meetingId).longValue();
        }
        Debug.print("Next Id : "+nextId);
        if(nextId==0 ){
            nextId = 10000;
        }
        else{
            nextId = nextId+1;
        }
        rs.close();
        prepSelect.close();
        meetingId = Long.toString(nextId);
        return meetingId;
    }
    
     public static final long getNextICPMeetingId(Connection con) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getNextICPMeetingId");
        return getNextICPId(con,"tblMeeInstructorDetails");
     }
    
    
    private static final long getNextICPId(Connection con,String table)  throws SQLException ,HLCPrimaryKeyException {
        Debug.print("DBHelper getNextICPId");
        String selectStatement = "SELECT icp_meeting_id from " + table + " order by icp_meeting_id ASC ";
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        ResultSet rs = prepSelect.executeQuery();
        long nextId = 0;
        while (rs.next()) {
             nextId = rs.getInt(1);
        }
        //long nextId = Long.valueOf(meetingId).longValue();
        if(nextId==0 ){
            nextId = 10000;
        }
        else{
            nextId = nextId+1;
        }
        rs.close();
        prepSelect.close();
        //meetingId = Long.toString(nextId);
        return nextId;
    }
    
    
   /* public static final String getMemberId(Connection con, String mailId)
    throws SQLException ,PrimaryKeyException {
        Debug.print("DBHelper getId");
        String selectStatement = "SELECT member_id from tblMemberDetails A, tblUserMaster B "+
        " WHERE A.user_id = B.user_id and B.email_id = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, mailId.trim());
        ResultSet rs = prepSelect.executeQuery();
        String memberId = null;
        if (rs.next()) {
             memberId = rs.getString(1);
        }
        Debug.print("USer ID is : "+memberId);
        return memberId;
    }*/
    
   

}
