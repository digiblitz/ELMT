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
 * EventRegistrationTypeDAO.java
 *
 * Created on September 20, 2006, 3:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.naming.*;
/**
 *
 * @author harmohan
 */
public class HLCEventRegistrationTypeDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    private String eventRegistrationTypeId;
    private String eventRegistrationTypeName;
    private String registrationDate;
    private String meetingDate;
    private String noOfDays;
    
    private String annualMeetingId;
    private String userId;
    private String areaId;
    private String badgeInfo;
    private String totalAmount;
    private String requestStatus;
    private String addDate;
    private String firstName;
    private String lastName;
    private String areaName;
    private String activityTypeId;
    private String activityTypeName;
    private String activeStatus;
    
    
    /** Creates a new instance of EventRegistrationTypeDAO */
    public HLCEventRegistrationTypeDAO() {
    }
    //updateStatusAnnConReg(meetingId,status,comments)
/**
  * Name         :updateStatusAnnConReg
  * Description  :This method will Update the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean updateStatusAnnConReg(String meetingId,String status,String comments) throws SQLException {
        Debug.print("Inside updateStatusAnnConReg ");
        
        try {
            makeConnection();
            String insertStatement = "UPDATE " + DBHelper.USEA_ANNUAL_DETAILS + " SET  request_status = ?, "+
            " comments = ? WHERE  annual_meeting_id = ? ";
            
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, status);
             Debug.print(" status : "+status);
            prepStmt.setString(2, comments);
            Debug.print(" Comments : "+comments);
            prepStmt.setString(3, meetingId);
            Debug.print(" meetingId : "+meetingId);
            
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Updated into Annual Details......:" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating Annual Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }   
    
/**
  * Name         :updateRowPriceDetail
  * Description  :This method will Update the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public boolean updateEventRegType(String eventRegistrationTypeId,String eventRegistrationTypeName,String registrationDate,String meetingDate,int noOfDays) throws SQLException {
        Debug.print("eventRegistrationType updateEventRegType");
        Date d1 = null;
        Date d2 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
            String textDate = registrationDate; 
            d1 = sdf.parse(textDate); 
            textDate = meetingDate; 
            d2 = sdf.parse(textDate);
        }catch (Exception e){
            Debug.print("Error while converting date format : "+e.getMessage());
        }
         Debug.print(" inside updateEventRegType Registration Date : "+d1);
        Debug.print(" inside updateEventRegType Meeting Date : "+d2);
        
        //java.sql.Date regDate = java.sql.Date.valueOf(registrationDate);
        //java.sql.Date meeDate = java.sql.Date.valueOf(meetingDate);
       // Debug.print(" Registration Date : "+regDate);
        //Debug.print(" Meeting Date : "+meeDate);
        try {
            makeConnection();
            String insertStatement = "UPDATE " + DBHelper.USEA_EVENT_REGISTRATION_MASTER + " SET  registration_date = ?, "+
            " meeting_date = ?, no_of_days = ? WHERE  event_registration_type_id = ? ";
            prepStmt = con.prepareStatement(insertStatement);
           // prepStmt.setString(1, eventRegistrationTypeName);
            prepStmt.setDate(1, DBHelper.toSQLDate(d1));
             Debug.print(" Registration Date : "+d1);
            prepStmt.setDate(2, DBHelper.toSQLDate(d2));
            Debug.print(" Meeting Date : "+d2);
            prepStmt.setInt(3, noOfDays);
            Debug.print(" No of Days Date : "+noOfDays);
            prepStmt.setString(4, eventRegistrationTypeId);
            Debug.print(" eventRegistrationTypeId  : "+eventRegistrationTypeId);
           // prepStmt.setString(5, eventRegistrationTypeId);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Updated into updateEventRegType......:" + cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating updateEventRegType : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
     }     
    //
/**
  * Name         :selectAnnualMeeForNoOfDays
  * Description  :This method will display the annual meeting's detail fields
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectAnnualMeeForNoOfDays(String annualMeeName) throws SQLException {
        Debug.print("eventRegistrationType selectAnnualMeeForNoOfDays");
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT event_registration_type_id, event_registration_type_name, registration_date, meeting_date, no_of_days FROM "+
                    DBHelper.USEA_EVENT_REGISTRATION_MASTER+" WHERE event_registration_type_name = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,"Annual Meeting");
            rs = prepStmt.executeQuery();
            //System.out.println("Inside the getUserAndMemberId mailId : "+mailId);
            while (rs.next()) {
                this.eventRegistrationTypeId = rs.getString(1);
                this.eventRegistrationTypeName = rs.getString(2);
                this.registrationDate = rs.getString(3);
                this.meetingDate = rs.getString(4);
                this.noOfDays = rs.getString(5);
                
               // this.registrationDate = DBHelper.dateToString(registrationDate1);
               // this.meetingDate = DBHelper.dateToString(meetingDate1);
                
                String eventReg[] = {eventRegistrationTypeId,registrationDate,meetingDate,noOfDays};
                vObj.add(eventReg);
            }
            prepStmt.close();
            releaseConnection();
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while display Annual meeting details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return vObj;
     }     
    
    
/**
  * Name         :selectEventRegTypeDetails
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectEventRegTypeDetails() throws SQLException {
        Debug.print("eventRegistrationType updateEventRegType");
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT event_registration_type_id, event_registration_type_name, registration_date, meeting_date, no_of_days FROM "+
                    DBHelper.USEA_EVENT_REGISTRATION_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            //System.out.println("Inside the getUserAndMemberId mailId : "+mailId);
            while (rs.next()) {
                this.eventRegistrationTypeId = rs.getString(1);
                this.eventRegistrationTypeName = rs.getString(2);
                this.registrationDate = rs.getString(3);
                this.meetingDate = rs.getString(4);
                this.noOfDays = rs.getString(5);
                
                //this.registrationDate = DBHelper.dateToString(registrationDate1);
                //this.meetingDate = DBHelper.dateToString(meetingDate1);
                
                String eventReg[] = {eventRegistrationTypeId,eventRegistrationTypeName,registrationDate,meetingDate,noOfDays};
                vObj.add(eventReg);
            }
            prepStmt.close();
            releaseConnection();
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating updateEventRegType : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return vObj;
     }      
     public Vector selectAnnualActivityTypeDetails() throws SQLException {
        Debug.print("eventRegistrationType updateEventRegType");
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT activity_type_id, activity_type_name, active_status FROM "+
                    DBHelper.USEA_ACTIVITY_CATEGORY;
            prepStmt = con.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            //System.out.println("Inside the getUserAndMemberId mailId : "+mailId);
            while (rs.next()) {
                this.activityTypeId = rs.getString(1);
                this.activityTypeName = rs.getString(2);
                this.activeStatus = rs.getString(3);
                //this.registrationDate = DBHelper.dateToString(registrationDate1);
                //this.meetingDate = DBHelper.dateToString(meetingDate1);
                
                String eventReg[] = {activityTypeId,activityTypeName,activeStatus};
                vObj.add(eventReg);
            }
            prepStmt.close();
            releaseConnection();
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating updateEventRegType : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return vObj;
     }       
/**
  * Name         :selectAnnualDetails
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectAnnualDetails(String status) throws SQLException {
        Debug.print(" Inside the selectAnnualDetails ");
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT A.annual_meeting_id, A.badge_info, B.first_name, B.last_name, C.area_name, A.request_status, A.add_date,B.email_id FROM "+
                    DBHelper.USEA_ANNUAL_DETAILS+" A, "+DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_AREA_MASTER+" C "+
                    "  WHERE A.user_id = B.user_id AND A.area_id = C.area_id AND A.request_status = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,status);
            rs = prepStmt.executeQuery();
            //System.out.println("Inside the getUserAndMemberId mailId : "+mailId);
            while (rs.next()) {
                this.annualMeetingId = rs.getString(1);
                this.badgeInfo = rs.getString(2);
                this.firstName = rs.getString(3);
                this.lastName = rs.getString(4);
                this.areaName = rs.getString(5);
                this.requestStatus = rs.getString(6);
                Date addDate1 = rs.getDate(7);
                String email = rs.getString(8);
                
                this.addDate = DBHelper.dateToString(addDate1);
                
                String annualMee[] = {annualMeetingId,badgeInfo,firstName,lastName,areaName,requestStatus,addDate,email};
                vObj.add(annualMee);
            }
            prepStmt.close();
            releaseConnection();
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating updateEventRegType : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return vObj;
     }    
    
/**
  * Name         :selectAnnualDetails
  * Description  :This method will display the updateEventRegType
  * @ param      :specificationId,userTypeId,eventRegistrationTypeId,dueDatePrice,aftDueDatePrice
  * @return      :boolean
  * @throws      :SQLException
  */   
    public Vector selectAnnualDetailsBasedOnUserId(String userId) throws SQLException {
        Debug.print(" Inside the selectAnnualDetailsBasedOnUserId ");
        Vector vObj = new Vector();
        try {
            makeConnection();
            String selectStatement = "SELECT A.annual_meeting_id, A.badge_info,B.first_name, B.last_name, C.area_name, A.request_status, A.add_date FROM "+
                    DBHelper.USEA_ANNUAL_DETAILS+" A, "+DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_AREA_MASTER+" C "+
                    "  WHERE A.user_id = B.user_id AND A.area_id = C.area_id AND A.user_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            rs = prepStmt.executeQuery();
            //System.out.println("Inside the getUserAndMemberId mailId : "+mailId);
            while (rs.next()) {
                this.annualMeetingId = rs.getString(1);
                this.badgeInfo = rs.getString(2);
                this.firstName = rs.getString(3);
                this.lastName = rs.getString(4);
                this.areaName = rs.getString(5);
                this.requestStatus = rs.getString(6);
                Date addDate1 = rs.getDate(7);
                
                this.addDate = DBHelper.dateToString(addDate1);
                
                String annualMee[] = {annualMeetingId,badgeInfo,firstName,lastName,areaName,requestStatus,addDate};
                vObj.add(annualMee);
            }
            prepStmt.close();
            releaseConnection();
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error while Updating updateEventRegType : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return vObj;
     }     
    
/**
  * Name         :makeConnection
  * Description  :This method will create the databacse connection
  * @ param      :
  * @return      :void
  * @throws      :EJBException
  */      
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);

            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
    }

/**
  * Name         :releaseConnection
  * Description  :This method will release the databacse connection
  * @ param      :
  * @return      :void
  * @throws      :EJBException
  */      
    private void releaseConnection() {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }     
    
}
