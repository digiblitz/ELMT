/*
 * DBHelper.java
 *
 * Created on September 2, 2006, 12:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmsg.util;


import com.hlcmsg.exception.HLCMessageException;
import javax.sql.*;
import java.sql.*;
import javax.sql.*;

/**
 *
 * @author harmohan
 */
public final class DBHelper {
    
    public static final String USEA_MSG_CONTACT_LIST_MASTER = "tblMsgContactListMaster"; 
    public static final String USEA_MSS_DISTRIBUTION_LIST = "tblMsgDistributionListMaster";
    public static final String USEA_MSG_GROUPMASTER = "tblMsgGroupMaster";
    public static final String USEA_MSG_GROUPUSERS = "tblMsgGroupUsers";
    public static final String USEA_MSG_CATEGORY = "tblMsgCategoryMaster";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster";
    public static final String USEA_MSG_GROUPMESSAGE = "tblMsgGrpMessages";
    public static final String USEA_MEMBER_TYPE_MASTER = "tblMembershipTypeMaster";
    public static final String USEA_MEMBERSHIP_STATUS = "tblMembershipStatusMaster";
    public static final String USEA_MSG_DBREQUEST_DETAIL = "tblMsgDBRequestDetails";
    public static final String USEA_MRO_STATE_MASTER = "tblMeeStateMaster";
    public static final String USEA_MRO_MAP_STATE_ZIP = "tblMeeMapStateZip";
    public static final String USEA_MRO_AREA_MASTER = "tblMeeAreaMaster";
    public static final String USEA_CONTACT_DETAILS = "tblContactDetails";
    public static final String USEA_USER_MASTER = "tblUserMaster";
    public static final String USEA_MEMBER_DETAIL = "tblMemberDetails";
    
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e+pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }
    
    //getUserIdBasedOnMemberId(con, memberId);
     public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
     
     public static final String getUserIdBasedOnMemberId(Connection con, String email) throws SQLException ,HLCMessageException{
        Debug.print("DBHelper getUserId");
        return getUsrId(con, email); //"tblUserMaster", emailId);
    }
        
    private static final String getUsrId(Connection con, String email)
    throws SQLException ,HLCMessageException {
        Debug.print("DBHelper getUsrId");
        String selectStatement = "SELECT user_id from tblUserMaster WHERE email_id = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, email);
        ResultSet rs = prepSelect.executeQuery();
        String userId = null;
        if (rs.next()) {
             userId = rs.getString(1);
        }
        Debug.print("USer ID is : "+userId);
        return userId;
    }
    
    public static final String getUserId(Connection con, String emailId) throws SQLException ,HLCMessageException{
        Debug.print("DBHelper getUserId");
        return getId(con, emailId); //"tblUserMaster", emailId);
    }
        
    private static final String getId(Connection con, String emailId)
    throws SQLException ,HLCMessageException {
        Debug.print("DBHelper getId");
        String selectStatement = "SELECT user_id from " + USEA_MMS_USERMASTER + " WHERE email_id = ? ";

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
    
    public static final String getContactId(Connection con, String emailId) throws SQLException ,HLCMessageException{
        Debug.print("DBHelper getContactId");
        
        String selectStatement = "SELECT contactlist_id FROM " + USEA_MSG_CONTACT_LIST_MASTER + " WHERE email_id = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, emailId.trim());
        ResultSet rs = prepSelect.executeQuery();
        String contactlistId = null;
        if (rs.next()) {
             contactlistId = rs.getString(1);
        }
        Debug.print("Contact List ID is : "+contactlistId);
        return contactlistId;
    }
    
   /* public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }*/
    // getEventRegistrationTypeId(con, eventRegistrationTypeName)
     /*public static final String getEventRegistrationTypeId (Connection con, String eventRegistrationTypeName)throws SQLException, MessageException {
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
     
     public static final String getAreaId (Connection con, String areaName)throws SQLException, MessageException {
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
     }*/
    
     public static final String getNextMeetingId(Connection con) throws SQLException ,HLCMessageException{
        Debug.print("DBHelper getNextMeetingId");
        return getNextMeetingId(con,"tblMeeAnnualDetails");
     }
    
    
    private static final String getNextMeetingId(Connection con,String table)  throws SQLException ,HLCMessageException {
        Debug.print("DBHelper getNextMeetingId");
        String selectStatement = "SELECT annual_meeting_id from " + table + " order by annual_meeting_id ASC ";
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        ResultSet rs = prepSelect.executeQuery();
        String meetingId = null;
        while (rs.next()) {
             meetingId = rs.getString(1);
        }
        long nextId = Long.valueOf(meetingId).longValue();
        if(nextId==0 || meetingId == null){
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
    
     public static final long getNextICPMeetingId(Connection con) throws SQLException ,HLCMessageException{
        Debug.print("DBHelper getNextICPMeetingId");
        return getNextICPId(con,"tblMeeInstructorDetails");
     }
    
    
    private static final long getNextICPId(Connection con,String table)  throws SQLException ,HLCMessageException {
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
    
    
   public static final String getNextUserCode(Connection con) throws SQLException {
        Debug.print("DBHelper getNextAccountId");
        return getNextCode(con);
    }
    
    private static final String getNextCode(Connection con)  throws SQLException{
    Debug.print("DBHelper getNextId");
    String selectStatement = "SELECT max(user_code) from " + USEA_MMS_USERMASTER;// + " order by member_id ASC ";

    PreparedStatement prepSelect = con.prepareStatement(selectStatement);

    ResultSet rs = prepSelect.executeQuery();
    String userCode = null;
    while (rs.next()) {
    //rs.next();
         userCode = rs.getString(1);
    }
    if (userCode == null)
        userCode = "0";
    long nextId = Long.valueOf(userCode).longValue();
   
    if(nextId==0){
        nextId = 10000;
    }
    else{
        nextId = nextId+1;
    }
    rs.close();
    prepSelect.close();

    userCode = Long.toString(nextId);
   
    return userCode;
    }
    
     /*=====================Get contact Type Id Based On Contact Type Name ========================================*/
    public static final String getContacttypeId(Connection con, String contactTypeName) throws SQLException {
        Debug.print("DBHelper getContacttypeId");
        return getcontactId(con,"tblContactTypeMaster", contactTypeName);
    }
    
    
    private static final String getcontactId(Connection con,String table, String contactTypeName)
    throws SQLException {
        Debug.print("DBHelper getcontactId ContacttypeName :"+contactTypeName);
        String selectStatement = "SELECT contact_type_id from " + table + " WHERE contact_type_name = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, contactTypeName);
        ResultSet rs = prepSelect.executeQuery();
        String contactTypeId = null;
        if (rs.next()) {
             contactTypeId = rs.getString(1);
        }
        Debug.print("contactTypeId in DBHandling: "+contactTypeId+"    Contact Type Name : "+contactTypeName);
        return contactTypeId;
    }
    
    public static final String getUserTypeId(Connection con, String userType) throws SQLException {
        Debug.print("DBHelper getContacttypeId");
        return getUTypeId(con,"tblUserTypeMaster", userType);
    }
    
    
    private static final String getUTypeId(Connection con,String table, String userType)
    throws SQLException {
        Debug.print("DBHelper getUTypeId userType : "+userType);
        String selectStatement = "SELECT user_type_id from " + table + " WHERE user_type_name = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, userType);
        ResultSet rs = prepSelect.executeQuery();
        String userTypeId = null;
        if (rs.next()) {
             userTypeId = rs.getString(1);
        }
        Debug.print("UserTypeId in DBHandling: "+userTypeId+"    User Type Name : "+userType);
        return userTypeId;
    }
    
   

}
