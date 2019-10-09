/*
 * ActivityOrganizerDAO.java
 *
 * Created on September 18, 2006, 11:59 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcevent.activity.dao;

import com.hlccommon.util.HLCActivityUserVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import com.hlccommon.util.HLCUserSearchResultVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
import java.text.*;

/**
 *
 * @author suresh
 */

public class HLCActivityOrganizerDAO {
    private Connection con;
    
    /** Creates a new instance of ActivityOrganizerDAO */
    public HLCActivityOrganizerDAO() {
    }
    
    public ArrayList getActivityTypeMaster(){   
        Debug.print("ActivityOrganizerDAO getActivityTypeMaster() ");
         ArrayList activityTypeList = new ArrayList();
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
          String selectStatement = " SELECT activity_type_id, activity_type_name from " + DBHelper.USEA_EVT_EDU_ACT_TYPE_MASTER;
             conn = makeConnection();
             prepStmt = conn.prepareStatement(selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                al = new ArrayList();
                String activityTypeId = rs.getString(1);
                String activityTypeName = rs.getString(2);
                String actTypeList [] = {activityTypeId, activityTypeName};
                activityTypeList.add(actTypeList);
            }           
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getActivityTypeMaster:" + activityTypeList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getActivityTypeMaster:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getActivityTypeMaster:" + e.getMessage());
        }
        return activityTypeList;
    }
    
    public ArrayList getAreaMaster(){   
        Debug.print("ActivityOrganizerDAO getAreaMaster() ");
         ArrayList activityTypeList = new ArrayList();
         PreparedStatement prepStmt = null;
         Connection conn = null;
         ResultSet rs = null;
   	try {
          String selectStatement = " SELECT area_id, area_code, area_name from " + DBHelper.USEA_EVT_EDU_AREA_MASTER;
             conn = makeConnection();
             prepStmt = conn.prepareStatement(selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                al = new ArrayList();
                String area_id = rs.getString(1);
                String area_code = rs.getString(2);
                String area_name = rs.getString(3);
                String actTypeList [] = {area_id, area_code,area_name};
                activityTypeList.add(actTypeList);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getAreaMaster:" + activityTypeList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getAreaMaster:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getAreaMaster:" + e.getMessage());
        }
        return activityTypeList;
    }
    
    
     /*public ArrayList getUserContactDetails(String userId) {  
        Debug.print("ActivityOrganizerDAO getUserContactDetails() " + userId);
        ArrayList userContactList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no FROM " +
                  DBHelper.USEA_EVT_EDU_USER_DETAIL + " A, " +  DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.user_id = ?";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId);
             Debug.print("ActivityOrganizerDAO getUserContactDetails() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
               // boolean addressStatus = rs.getBoolean(17);
                
                //String addStst = String.valueOf(addressStatus);
                //String splNotes = rs.getString(18);
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
               // userContactList.add(addStst);
              //  userContactList.add(splNotes);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getUserContactDetails:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserContactDetails:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getUserContactDetails:" + e.getMessage());
        }
        return userContactList;
    }*/
      
   /* public ArrayList getUserContactDetailsForAdmin(String userId) {
        Debug.print("ActivityOrganizerDAO getUserContactDetailsForAdmin() " + userId);
        ArrayList userContactList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no,A.address_status,A.spl_notes FROM " +
                  DBHelper.USEA_EVT_EDU_USER_DETAIL + " A, " +  DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.user_id = ?";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId);
             Debug.print("ActivityOrganizerDAO getUserContactDetailsForAdmin() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                boolean addressStatus = rs.getBoolean(17);
                String addStst = String.valueOf(addressStatus);
                String splNotes = rs.getString(18);
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
                userContactList.add(addStst);
                userContactList.add(splNotes);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getUserContactDetailsForAdmin:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserContactDetailsForAdmin:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getUserContactDetailsForAdmin:" + e.getMessage());
        }
        return userContactList;
    }
      */
    public ArrayList getAnnualUserContactDetails(String userId){  
        Debug.print("ActivityOrganizerDAO getAnnualUserContactDetails() " + userId);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no , A.user_id, A.dob FROM " +
                  DBHelper.USEA_EVT_EDU_USER_DETAIL + " A, " +  DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.login_name = ?";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId);
              Debug.print("ActivityOrganizerDAO getUserContactDetails() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                String userIdVal = rs.getString(17);
                String dob = rs.getString(18);
                Date date = rs.getDate(18);
                
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
                userContactList.add(userIdVal);
                userContactList.add(dob);
                
                int ageCnt = 0;
                if(date!=null){
                    Date first = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String secondString = sdf.format(date);
                   // if(secondString==null) secondString = "";
                   // Date second = new Date(secondString);
                    long msPerDay = 1000 * 60 * 60 * 24;
                    long diff =(first.getTime() / msPerDay) - (date.getTime() / msPerDay);
                    Long convertLong = new Long(diff);
                    //return convertLong.intValue();
                    ageCnt = convertLong.intValue()/365;
                }
                
                userContactList.add(String.valueOf(ageCnt));

            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getUserContactDetails:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserContactDetails:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getUserContactDetails:" + e.getMessage());
        }
        return userContactList;
    }
    
      public ArrayList getAnnualUserContactDetailsByLoginName(String loginName){  
        Debug.print("ActivityOrganizerDAO getAnnualUserContactDetailsByLoginName() " + loginName);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no , A.user_id, A.dob FROM " +
                  DBHelper.USEA_EVT_EDU_USER_DETAIL + " A, " +  DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.email_id = ?";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,loginName);
              Debug.print("ActivityOrganizerDAO getAnnualUserContactDetailsByLoginName() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                String userIdVal = rs.getString(17);
                String dob = rs.getString(18);
                Date date = rs.getDate(18);
                
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
                userContactList.add(userIdVal);
                userContactList.add(dob);
                
                int ageCnt = 0;
                if(date!=null){
                    Date first = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String secondString = sdf.format(date);
                   // if(secondString==null) secondString = "";
                   // Date second = new Date(secondString);
                    long msPerDay = 1000 * 60 * 60 * 24;
                    long diff =(first.getTime() / msPerDay) - (date.getTime() / msPerDay);
                    Long convertLong = new Long(diff);
                    //return convertLong.intValue();
                    ageCnt = convertLong.intValue()/365;
                }
                userContactList.add(String.valueOf(ageCnt));

            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getAnnualUserContactDetailsByLoginName:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getAnnualUserContactDetailsByLoginName:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getAnnualUserContactDetailsByLoginName:" + e.getMessage());
        }
        return userContactList;
    }
      
    /*public ArrayList getUserContactDetailsByUserCode(String userCode) {  
        Debug.print("ActivityOrganizerDAO getUserContactDetailsByUserCode() " + userCode);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement = " SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, B.suite, B.address1, " +
                  " B.address2, B.city, B.state, B.country, B.zip, B.phone_no, B.mobile_no, B.fax_no FROM " +
                  DBHelper.USEA_EVT_EDU_USER_DETAIL + " A, " +  DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL + " B WHERE A.user_id = B.user_id AND " +
                  "A.contact_type_id = B.contact_type_id AND A.user_code = ?";
          
             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userCode);
              Debug.print("ActivityOrganizerDAO getUserContactDetailsByUserCode() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getUserContactDetailsByUserCode:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserContactDetailsByUserCode:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getUserContactDetailsByUserCode:" + e.getMessage());
        }
        return userContactList;
    }
     */ 
      
      
    /* public ArrayList getMemberContactDetails(String memberId){  
        Debug.print("ActivityOrganizerDAO getMemberContactDetails() " + memberId);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement ="SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, " +
                  " B.suite, B.address1, B.address2, B.city, B.state, B.country, B.zip, B.phone_no, " +
                  " B.mobile_no, B.fax_no , A.user_id FROM " + DBHelper.USEA_EVT_EDU_USER_DETAIL  + " A, " + DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL  + " B, " +
                  DBHelper.USEA_EVT_EDU_MEMBER_CONTACT_DETAIL  + " C  WHERE C.user_id = A.user_id AND A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id " +
                  " AND C.member_id = ?";
          
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,memberId);
            Debug.print("ActivityOrganizerDAO getMemberContactDetails() Query: " + selectStatement);
            rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);

                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getMemberContactDetails:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getMemberContactDetails:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getMemberContactDetails:" + e.getMessage());
        }
        return userContactList;
    }
     */
      
   public ArrayList getMemberContactDetails(String memberId){  
        Debug.print("ActivityOrganizerDAO getMemberContactDetails() " + memberId);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement ="SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, " +
                  " B.suite, B.address1, B.address2, B.city, B.state, B.country, B.zip, B.phone_no, " +
                  " B.mobile_no, B.fax_no , A.user_id FROM " + DBHelper.USEA_EVT_EDU_USER_DETAIL  + " A, " + DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL  + " B, " +
                  DBHelper.USEA_EVT_EDU_MEMBER_CONTACT_DETAIL  + " C  WHERE C.user_id = A.user_id AND A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id " +
                  " AND C.member_id = ?";
          
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,memberId);
            Debug.print("ActivityOrganizerDAO getMemberContactDetails() Query: " + selectStatement);
            rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);

                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getMemberContactDetails:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getMemberContactDetails:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getMemberContactDetails:" + e.getMessage());
        }
        return userContactList;
    }
         
      
      
      public ArrayList getMemberContactDetailsForAnnualMeeting(String memberId){  
        Debug.print("ActivityOrganizerDAO getMemberContactDetailsForAnnualMeeting() " + memberId);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement ="SELECT A.prefix, A.first_name, A.middle_name, A.last_name, A.sufix, A.email_id, " +
                  " B.suite, B.address1, B.address2, B.city, B.state, B.country, B.zip, B.phone_no, " +
                  " B.mobile_no, B.fax_no , A.user_id, A.dob, d.status_name FROM " + DBHelper.USEA_EVT_EDU_USER_DETAIL  + " A, " + DBHelper.USEA_EVT_EDU_USER_CONTACT_DETAIL  + " B, " +
                  DBHelper.USEA_EVT_EDU_MEMBER_CONTACT_DETAIL  + " C, "+DBHelper.USEA_MEMBERSHIP_STATUS_MASTER+" D WHERE C.user_id = A.user_id AND A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id " +
                  " AND C.status_id = D.status_id AND C.member_id = ?";
            
            Debug.print("selectStatement :"+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,memberId);
            Debug.print("ActivityOrganizerDAO getMemberContactDetailsForAnnualMeeting() Query: " + selectStatement);
            rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String prefix = rs.getString(1);
                String first_name = rs.getString(2);
                String middle_name = rs.getString(3);
                String last_name = rs.getString(4);
                String sufix = rs.getString(5);
                String email_id = rs.getString(6);
                String suite = rs.getString(7);
                String address1 = rs.getString(8);
                String address2 = rs.getString(9);
                String city = rs.getString(10);
                String state = rs.getString(11);
                String country = rs.getString(12);
                String zip = rs.getString(13);
                String phone_no = rs.getString(14);
                String mobile_no = rs.getString(15);
                String fax_no = rs.getString(16);
                String userId = rs.getString(17);
                String dob = rs.getString(18);
                Date date = rs.getDate(18);
                String status = rs.getString(19);
                
                userContactList.add(prefix);
                userContactList.add(first_name);
                userContactList.add(middle_name);
                userContactList.add(last_name);
                userContactList.add(sufix);
                userContactList.add(email_id);
                userContactList.add(suite);
                userContactList.add(address1);
                userContactList.add(address2);
                userContactList.add(city);
                userContactList.add(state);
                userContactList.add(country);
                userContactList.add(zip);
                userContactList.add(phone_no);
                userContactList.add(mobile_no);
                userContactList.add(fax_no);
                userContactList.add(userId);
                userContactList.add(dob);
                                
                int ageCnt = 0;
                if(date!=null){
                    Date first = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String secondString = sdf.format(date);
                   // if(secondString==null) secondString = "";
                   // Date second = new Date(secondString);
                    long msPerDay = 1000 * 60 * 60 * 24;
                    long diff =(first.getTime() / msPerDay) - (date.getTime() / msPerDay);
                    Long convertLong = new Long(diff);
                    //return convertLong.intValue();
                    ageCnt = convertLong.intValue()/365;
                }
                userContactList.add(String.valueOf(ageCnt));
                userContactList.add(status);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getMemberContactDetailsForAnnualMeeting:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getMemberContactDetailsForAnnualMeeting:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getMemberContactDetailsForAnnualMeeting:" + e.getMessage());
        }
        return userContactList;
    }
     
     
    public String getActivityName(String meetingId){  
        Debug.print("ActivityOrganizerDAO getActivityName() " + meetingId);
         ArrayList userContactList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         String activityName = "";
         makeConnection();
   	try {
          String selectStatement ="SELECT activity_name from " + DBHelper.USEA_EVT_EDU_ACTIVITY_ORGANIZER + " where activity_meeting_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,meetingId);
            rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                activityName = rs.getString(1);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getActivityName:" + userContactList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getActivityName:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getMemberContactDetails:" + e.getMessage());
        }
        return activityName;
    }
    
    public ArrayList getUserMyEducationalActivity(String userId){
         Debug.print("ActivityOrganizerDAO getUserMyEducationalActivity() " + userId);
         ArrayList userActivityList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         String activityName = "";
         makeConnection();
   	try {
          String selectStatement ="SELECT release_id, activity_meeting_id, user_id, no_of_horses, event_level_id, membership_status, " +
                  " member_id, add_date, active_status, request_status from  " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER + " where user_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                String releaseId = rs.getString(1);
                String activityMeetingId = rs.getString(2);
                String userIdVal = rs.getString(3);
                String noOfHorses = rs.getString(4);
                String eventLevelId = rs.getString(5);
                boolean membershipStatus = rs.getBoolean(6);
                String memberId = rs.getString(7);
                Date addDate = rs.getDate(8);
                boolean activeStatus = rs.getBoolean(9);
                String requestStatus = rs.getString(10);
                HLCActivityUserVO objActUserVO = new HLCActivityUserVO();
                objActUserVO.setReleaseId(releaseId);
                objActUserVO.setActivityMeetingId(activityMeetingId);
                objActUserVO.setUserId(userIdVal);
                objActUserVO.setNoOfHorses(noOfHorses);
                objActUserVO.setEventLevelId(eventLevelId);
                objActUserVO.setMembershipStatus(membershipStatus);
                objActUserVO.setAddDate(addDate);
                objActUserVO.setActiveStatus(activeStatus);
                objActUserVO.setRequestStatus(requestStatus);
                userActivityList.add(objActUserVO);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getUserMyEducationalActivity:" + userActivityList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserMyEducationalActivity:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getMemberContactDetails:" + e.getMessage());
        }
        return userActivityList;
    }
    
     public ArrayList selectUserDetailsByMemberId(String memberId){
         Debug.print("ActivityOrganizerDAO selectUserDetailsByMemberId() memberId:" + memberId);
         ArrayList userActivityList = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         String activityName = "";
        /* makeConnection();
   	try {
          String selectStatement ="SELECT release_id, activity_meeting_id, user_id, no_of_horses, event_level_id, membership_status, " +
                  " member_id, add_date, active_status, request_status from  " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER + " where user_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            rs = prepStmt.executeQuery();
            ArrayList al = null;
            while(rs.next()){
                String releaseId = rs.getString(1);
                String activityMeetingId = rs.getString(2);
                String userIdVal = rs.getString(3);
                String noOfHorses = rs.getString(4);
                String eventLevelId = rs.getString(5);
                boolean membershipStatus = rs.getBoolean(6);
                String memberId = rs.getString(7);
                Date addDate = rs.getDate(8);
                boolean activeStatus = rs.getBoolean(9);
                String requestStatus = rs.getString(10);
                UserSearchResultVO objActUserVO = new UserSearchResultVO();
                objActUserVO.setReleaseId(releaseId);
                objActUserVO.setActivityMeetingId(activityMeetingId);
                objActUserVO.setUserId(userIdVal);
                objActUserVO.setNoOfHorses(noOfHorses);
                objActUserVO.setEventLevelId(eventLevelId);
                objActUserVO.setMembershipStatus(membershipStatus);
                objActUserVO.setAddDate(addDate);
                objActUserVO.setActiveStatus(activeStatus);
                objActUserVO.setRequestStatus(requestStatus);
                userActivityList.add(objActUserVO);
            }
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in getUserMyEducationalActivity:" + userActivityList);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserMyEducationalActivity:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getMemberContactDetails:" + e.getMessage());
        }
         **/
        return userActivityList;
    }
    
    
    
   /*  public ArrayList selectMemberDetailsByMemberId(String memberId){  
        Debug.print("ActivityOrganizerDAO selectMemberDetailsByMemberId() " + memberId);
         ArrayList memberDetails = new ArrayList();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection(); 
         //Member ID, Name (Last Name, First Name- ex: Caldwell, Jason), State (code - ex: VA, MI), Member Type, Member Status
//membership_type_id, state,status_name
   	try {
          String selectStatement = "select A.first_name, A.last_name, A.login_name, " +
                  " A.password, B.member_id , A.email_id, A.user_id, E.membership_type_name, D.status_name, C.state from tblUserMaster A, " +
                  "tblMemberDetails B, tblContactDetails C, tblMembershipStatusMaster D, tblMembershipTypeMaster E" +
                  " where A.user_id = C.user_id AND A.contact_type_id = C.contact_type_id and A.user_id = B.user_id and" +
                  " B.status_id = D.status_id and B.membership_type_id = E.membership_type_id and B.member_id = ? ";

             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,memberId.trim());
              Debug.print("ActivityOrganizerDAO selectMemberDetailsByMemberId() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
            while(rs.next()){
                String firstName =  rs.getString(1);
                String lastName = rs.getString(2);
                String loginName = rs.getString(3);
                String password = rs.getString(4);
                String tempMemberId = rs.getString(5);
                String emailId = rs.getString(6);
                String userId = rs.getString(7);
                String membershipTypeName = rs.getString(8);
                String statusName = rs.getString(9);
                String state = rs.getString(10);
                
                HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
                
                objUserSearch.setFirstName(firstName);
                objUserSearch.setLastName(lastName);
                objUserSearch.setLoginName(loginName);
                objUserSearch.setPassword(password);
                objUserSearch.setMemberId(tempMemberId);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId);
                objUserSearch.setMembershipTypeName(membershipTypeName);
                objUserSearch.setStatusName(statusName);
                objUserSearch.setState(state);
                memberDetails.add(objUserSearch);
            }
            rs.close();
            releaseConnection(con);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in selectMemberDetailsByMemberId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in selectMemberDetailsByMemberId:" + e.getMessage());
        }
        return memberDetails;
    }
     */
    /* public ArrayList selectMemberDetailsByLoginName(String loginName){  
        Debug.print("ActivityOrganizerDAO selectMemberDetailsByLoginName() " + loginName);
        ArrayList memberDetails = new ArrayList();
        ArrayList tempList = new ArrayList();
        PreparedStatement prepStmt = null;
        PreparedStatement prepStmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        makeConnection();
   	try {
           
            
          String selectStatement = " select A.first_name, A.last_name, A.login_name, " +
                  " A.password, A.email_id, A.user_id, B.state  from tblUserMaster A, tblContactDetails B where" +
                  " A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id and A.login_name = '" + loginName.trim() + "'";
          
             prepStmt = con.prepareStatement(selectStatement);
             //prepStmt.setString(1,loginName.trim());
             Debug.print("ActivityOrganizerDAO selectMemberDetailsByLoginName() 1st Query: " + selectStatement);
             rs = prepStmt.executeQuery();
             
            while(rs.next()){
                String firstName =  rs.getString(1);
                String lastName = rs.getString(2);
                String loginName1 = rs.getString(3);
                String password = rs.getString(4);
                //String tempMemberId = rs.getString(5);
                String emailId = rs.getString(5);
                String userId = rs.getString(6);
                String state = rs.getString(7);
                String tempMemberId = null;
                String membershipTypeName = null;
                String statusName = null;
                
                String stmt = "select B.member_id , E.membership_type_name, D.status_name "+
                       "from tblMemberDetails B, tblMembershipStatusMaster D, tblMembershipTypeMaster E "+
                       "where B.status_id = D.status_id and B.membership_type_id = E.membership_type_id and "+
                       " B.user_id = ?";
                 Debug.print("ActivityOrganizerDAO selectMemberDetailsByLoginName() 2nd Query: " + selectStatement);
                    prepStmt1 = con.prepareStatement(stmt);
                    prepStmt1.setString(1,userId.trim());

                    rs1 = prepStmt1.executeQuery();
                    if (rs1.next()) {
                     tempMemberId = rs1.getString(1);
                     membershipTypeName = rs1.getString(2);
                     statusName = rs1.getString(3);
                     rs1.close();
                    }
                    
                
                HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
                
                objUserSearch.setFirstName(firstName);
                objUserSearch.setLastName(lastName);
                objUserSearch.setLoginName(loginName1);
                objUserSearch.setPassword(password);
                objUserSearch.setMemberId(tempMemberId);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId);
                objUserSearch.setMembershipTypeName(membershipTypeName);
                objUserSearch.setStatusName(statusName);
                objUserSearch.setState(state);
                
                tempList.add(objUserSearch);
            }
            rs.close();
           
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            throw new EJBException("SQL Exception in selectMemberDetailsByLoginName:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
             e.printStackTrace();
            throw new EJBException("General Exception  in selectMemberDetailsByLoginName:" + e.getMessage());
        }
         if(tempList!=null && tempList.size()!=0){
                Iterator itUserList = tempList.iterator();
                String memberId = "";
                while(itUserList.hasNext()){
                    HLCUserSearchResultVO objUserSearch = (HLCUserSearchResultVO)itUserList.next();
                    String userId = objUserSearch.getUserId();
                    memberId = selectMemeberId(userId);
                    objUserSearch.setMemberId(memberId);
                    memberDetails.add(objUserSearch);
                }
            }
        return memberDetails;
    }
     */
     
     /* public HLCUserSearchResultVO selectMemberDetailsByUserId(String userId){  
        Debug.print("ActivityOrganizerDAO selectMemberDetailsByLoginName() " + userId);
         HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
   	try {
          String selectStatement = " select A.first_name, A.last_name, A.login_name, " +
                  " A.password, A.email_id, A.user_id  from tblUserMaster A, tblContactDetails B where " +
                  " A.user_id = B.user_id AND A.contact_type_id = B.contact_type_id and  A.user_id = ?";
          
             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1,userId.trim());
             Debug.print("ActivityOrganizerDAO selectMemberDetailsByLoginName() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
             
            if(rs.next()){
                String firstName =  rs.getString(1);
                String lastName = rs.getString(2);
                String tempLoginName = rs.getString(3);
                String password = rs.getString(4);
                String emailId = rs.getString(5);
                String tempUserId = rs.getString(6);
                
                objUserSearch.setFirstName(firstName);
                objUserSearch.setLastName(lastName);
                objUserSearch.setLoginName(tempLoginName);
                objUserSearch.setPassword(password);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId);
            }
            
            rs.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in selectMemberDetailsByLoginName:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in selectMemberDetailsByLoginName:" + e.getMessage());
        }
            if(objUserSearch.getUserId()!=null && objUserSearch.getUserId().trim().length()!=0){
                String memberId = selectMemeberId(objUserSearch.getUserId().trim());
                objUserSearch.setMemberId(memberId);
            }
        return objUserSearch;
    }
     */
     
    /* public ArrayList selectMemberDetailsByGeneralSearch(String fName, String lName, String email, String zip){  
        Debug.print("ActivityOrganizerDAO selectMemberDetailsByLoginName() " + fName);
         ArrayList memberDetails = new ArrayList();
         PreparedStatement prepStmt = null;
         PreparedStatement prepStmt1 = null;
         ResultSet rs = null;
         ResultSet rs1 = null;
         makeConnection();
   	try {
            String selectStatement = "select A.first_name, A.last_name, A.login_name, A.password, B.member_id , " +
                  " A.email_id, A.user_id, E.membership_type_name, D.status_name, C.state " +
                  " from tblUserMaster A left join tblMemberDetails B ON " +
                  " A.user_id = B.user_id left join tblContactDetails C ON " +
                  " A.user_id = C.user_id left join tblMembershipStatusMaster D ON " +
                  " B.status_id = D.status_id left join tblMembershipTypeMaster E ON " +
                  " B.membership_type_id = E.membership_type_id where A.contact_type_id = C.contact_type_id ";
    
        
                if(fName!=null && fName.trim().length()!=0){
                    fName = fName.replaceAll("'","''");
                    selectStatement = selectStatement + " and A.first_name like '" + fName.trim() + "%' ";
                }
         
                if(lName!=null && lName.trim().length()!=0){
                    lName = lName.replaceAll("'","''");
                    selectStatement = selectStatement + " and A.last_name like '" + lName.trim() + "%' ";
                }
                
                if(email!=null && email.trim().length()!=0){
                    selectStatement = selectStatement + " and A.email_id = '" + email.trim() + "' " ;
                }

                if(zip!=null && zip.trim().length()!=0){
                     selectStatement = selectStatement + " and C.zip = '" + zip.trim() + "' " ;
                }
                
             selectStatement = selectStatement +" ORDER BY A.last_name,A.first_name ASC" ;
             Debug.print("Query : " + selectStatement);

             prepStmt = con.prepareStatement(selectStatement);
             Debug.print("ActivityOrganizerDAO selectMemberDetailsByGeneralSearch() Query: " + selectStatement);
             rs = prepStmt.executeQuery();
             
            while(rs.next()){
                String firstName =  rs.getString(1);
                String lastName = rs.getString(2);
                String loginName1 = rs.getString(3);
                String password = rs.getString(4);
                String tempMemberId = rs.getString(5);
                String emailId = rs.getString(6);
                String userId = rs.getString(7);
                String membershipTypeName =  rs.getString(8);
                String statusName =  rs.getString(9);
                String state = rs.getString(10);
             
                
                HLCUserSearchResultVO objUserSearch = new HLCUserSearchResultVO();
                
                objUserSearch.setFirstName(firstName);
                objUserSearch.setLastName(lastName);
                objUserSearch.setLoginName(loginName1);
                objUserSearch.setPassword(password);
                objUserSearch.setMemberId(tempMemberId);
                objUserSearch.setEmailId(emailId);
                objUserSearch.setUserId(userId);
                objUserSearch.setMembershipTypeName(membershipTypeName);
                objUserSearch.setStatusName(statusName);
                objUserSearch.setState(state);
                memberDetails.add(objUserSearch);
            }
            rs.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            sql.printStackTrace();
            throw new EJBException("SQL Exception in selectMemberDetailsByGeneralSearch:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
             e.printStackTrace();
            throw new EJBException("General Exception  in selectMemberDetailsByGeneralSearch:" + e.getMessage());
        }
          
        return memberDetails;
    }
    
      private String selectMemeberId(String userId){  
        Debug.print("ActivityOrganizerDAO selectMemeberId() " + userId);
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         String memberId = "";
         makeConnection();
   	try {
          String selectStatement ="select member_id from tblMemberDetails where user_id = ?";

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId.trim());
            rs = prepStmt.executeQuery();
            if(rs.next()){
                memberId = rs.getString(1);
            }
            rs.close();
            releaseConnection(con);
            Debug.print("ActivityOrganizerDAO in selectMemeberId memberId:" + memberId);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in selectMemeberId:" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in selectMemeberId:" + e.getMessage());
        }
        return memberId;
    }
    */
    public String getNextId() throws SQLException {
        Debug.print("ActivityOrganizerDAO getNextUserId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            Debug.print("ActivityOrganizerDAO getNextUserId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in ActivityOrganizerDAO getNextUserId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in ActivityOrganizerDAO getNextUserId:" + e.getMessage());
        }
        return nextId;
    }
    
    
/*    public Vector getUserDetailedRelease(String releaseId ){
        Debug.print("EventsDAO getUserDetailedRelease:" + releaseId);
        Vector eventList = new Vector();
        makeConnection();
        try {
            String selectStatement = "select A.event_type_id, A.event_type_name, B.event_level_code, C.event_id,  B.event_level_id, C.map_type_id from " + 
            DBHelper.USEA_MRO_TYPE_MASTER + " A, " + DBHelper.USEA_MRO_LEVEL_MASTER + " B, " + DBHelper.USEA_MRO_EVENT_TYPE_DETAILS + " C, " +
            DBHelper.USEA_MRO_MAP_EVENT_LEVEL + " D  where C.map_type_id = D.map_type_id and D.event_type_id = A.event_type_id and  " +
            " D.event_level_id = B.event_level_id and C.event_id = ?";
            
            " select A.activity_name, A.activity_date, A.no_of_days, B.area_name, " +
            " A.location, A.state, C.first_name, C.last_name, D.address1, D.country, D.state, D.state, " +
                    " D.zip, D.phone_no, D.fax_no, E.no_of_horses, E.membership_status, E.member_id, E.add_date, E.request_status " +
                    " from tblMeeActivityOrganizerDetails A, tblMeeAreaMaster B, tblUserMaster C, " +
                    " tblContactDetails D, tblMeeEduActivityUserDetails E where E.activity_meeting_id = A.activity_meeting_id " +
                    " and E.user_id = C.user_id and A.usea_area_id = B.area_id and C.user_id = D.user_id and " +
                    "C.contact_type_id = D.contact_type_id and E.release_id = ''";
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("EventTypeBean getDivLevelByEventId:" + selectStatement);
            prepStmt.setString(1,releaseId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                String eventTypeId = rs.getString(1);
                String eventTypeName = rs.getString(2);
                String eventLevelCode = rs.getString(3);
                String eventIdVal = rs.getString(4);
                String eventLevelId = rs.getString(5);
                String mapTypeId = rs.getString(6);
                String evtTypeList [] = {eventTypeId, eventTypeName, eventLevelCode, eventIdVal, eventLevelId, mapTypeId};
                //Debug.print("EventTypeBean getDivLevelByEventId:" + evtTypeList);
                eventList.add(evtTypeList);
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getUserDetailedRelease:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in getUserDetailedRelease:" + e.getMessage());
        }
        return eventList;
  }
    
   */ 
    
  public String getOrganiserByEventId(String eventId)
          {
             Debug.print("ActivityOrganizerDAO getOrganiserDetails() " + eventId);
             String event_id=null;
             PreparedStatement prepStmt = null;
            ResultSet rs = null;
             makeConnection();
             try
             {
                 String query="select organizer_id from tblMeeEventDetails where event_id=?";
                 prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, eventId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                event_id = rs.getString(1);
             }
             }
             catch(SQLException g)
             {
                 g.printStackTrace();
                 System.out.println(g.getMessage());
                 releaseConnection(con);
             }
            
             catch(Exception e)
             {
                 e.printStackTrace();
                 System.out.println(e.getMessage());
                 
                 releaseConnection(con);
             }
             finally{
             try
             {
                 rs.close();
                 prepStmt.close();
             }
             catch(SQLException f)
             {
                      f.printStackTrace();
             } 
          }
             return event_id;
          }
         
   public boolean updatePendingAmount(String userId, String paymentId, float pendingAmount) {
        Debug.print("ActivityOrganizerDAO.updatePendingAmount() paymentId:" + paymentId);
        boolean result = false;
        PreparedStatement prepStmt = null;
        Debug.print("pendingAmount:" + pendingAmount);
        makeConnection();
        try {
            String updateStatement = "update tblUserPaymentDetails set pending_amount=? where payment_id=?";
             Debug.print("SQL String : " + updateStatement);
            prepStmt = con.prepareStatement(updateStatement);
           
            prepStmt.setFloat(1, pendingAmount);
            prepStmt.setString(2, paymentId);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated successfully updatePendingAmount : " + cnt);
            if(cnt>=1){
                result = true;
            }
            
            prepStmt.close();
            releaseConnection(con);
        } catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in ActivityOrganizerDAO.updatePendingAmount():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in ActivityOrganizerDAO.updatePendingAmount():" + e.getMessage());
        }
        Debug.print("ActivityOrganizerDAO.updatePendingAmount Status :" + result);
        return result;
    }
       
   
    private Connection makeConnection() {
            Debug.print("ActivityOrganizerDAO : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
           return con; 
        }
    
  
     private void releaseConnection(Connection con) {
            Debug.print("ActivityOrganizerDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {
                
            }
       }
}
