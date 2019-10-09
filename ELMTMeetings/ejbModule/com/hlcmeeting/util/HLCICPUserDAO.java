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
 * ICPUserDAO.java
 *
 * Created on September 19, 2006, 1:23 AM
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
import javax.naming.*;

/**
 *
 * @author harmohan
 */
public class HLCICPUserDAO  {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    private String statusId = null;
    
    private String userId;
    private String memberId;
    private String icpMeetingId;    
    private String membershipStatus;
    private String addDate;
    private String activeStatus;
    private String requestStatus;
    private String releaseId;
    
    /*==================================User Master Variable ========================*/
    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String sufix;
    private String dob;
    private String gender;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private String registerDate;
    /*=============================Contact Details Variable =========================*/
    private String contactTypeId;
    private String suite;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNo;
    private String mobileNo;
    private String faxNo;
    private String contactTypeName;
    
    /*==============================ACtivity Category Variables=========================*/
    private String activityTypeId;
    private String activityTypeName;
    private String specificationId;
    private String specificationName;
    
    private String userTypeId;
    private String userTypeName;
    
    private String assesmentLevel;
    private String assesmentDate;
    private String noOfDays;
    private String areaName;
    private String location;

    private String comments;
    

    /** Creates a new instance of ICPUserDAO */
    public HLCICPUserDAO() {
    }
    
/**
  * Name         :getUserAndMemberId() 
  * Description  :This method will retrieve the user ID and Member Id from the Database 
  * @ param      :mailId
  * @return      :Vector
  * @throws      :SQLException
  */          
    public Vector getUserAndMemberId(String mailId) throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT A.user_id, B.member_id FROM "+DBHelper.USEA_MMS_USERMASTER+" A, "+
                DBHelper.USEA_MMS_MEMBERDETAIL+" B "+ 
                " WHERE A.user_id = B.user_id and A.email_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, mailId.trim());
                rs = prepStmt.executeQuery();
                System.out.println("Inside the getUserAndMemberId mailId : "+mailId);
                if (rs.next()) {
                    this.userId = rs.getString(1);
                    this.memberId = rs.getString(2);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          String [] useMemberId  = {userId,memberId};
          vObj.add(useMemberId);
          return vObj;
    }    
    
    
/**
  * Name         :listICPUserReg() 
  * Description  :This method will retrieve the user registration details for Organizer
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listICPUserReg(String userId) throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT icp_meeting_id,user_id,membership_status,member_id,add_date,active_status,request_status,release_id FROM "+
                DBHelper.USEA_ICP_USER_DETAIL+" WHERE user_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, userId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listICPUserReg status : "+userId);
                Debug.print(" Query : \n"+slelctStr);
                while (rs.next()) {
                    this.icpMeetingId = rs.getString(1);
                    this.userId = rs.getString(2);
                    this.membershipStatus = rs.getString(3);
                    this.memberId = rs.getString(4);
                    Date addDate1 = rs.getDate(5);
                    this.activeStatus = rs.getString(6);
                    this.requestStatus = rs.getString(7);
                    this.releaseId = rs.getString(8);
                    
                    this.addDate = DBHelper.dateToString(addDate1);
                    
                    String [] userList  = {icpMeetingId,userId,membershipStatus,memberId,addDate,activeStatus,requestStatus,releaseId};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }  
//listICPUserRegBasedOnReleaseId  
/**
  * Name         :listICPUserRegBasedOnReleaseId() 
  * Description  :This method will retrieve the user registration details for Organizer
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listICPUserRegBasedOnReleaseId(String releaseId) throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "select A.assesment_level, A.assesment_date, A.no_of_days, B.area_name, "+
                " A.location, C.first_name, C.last_name, D.address1, D.country, D.state, "+
                "D.zip, D.phone_no, D.fax_no, E.membership_status, E.member_id, E.add_date, E.request_status,C.email_id, D.city,E.release_id,E.comments  FROM "+
                DBHelper.USEA_INSTRUCTOR_DETAILS+" A, "+DBHelper.USEA_AREA_MASTER+" B, "+DBHelper.USEA_MMS_USERMASTER+" C, "+
                DBHelper.USEA_CONTACT_DETAILS+" D, "+DBHelper.USEA_ICP_USER_DETAIL+" E "+
                " where E.icp_meeting_id = A.icp_meeting_id and E.user_id = C.user_id and "+
                " A.usea_area_id = B.area_id and C.user_id = D.user_id and C.contact_type_id = D.contact_type_id and "+
                " E.release_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, releaseId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listICPUserReg status : "+userId);
                if(rs.next()) {
                    this.assesmentLevel = rs.getString(1);
                    Date assesmentDate1 = rs.getDate(2);
                    this.noOfDays = rs.getString(3);
                    this.areaName = rs.getString(4);
                    this.location = rs.getString(5);
                    this.firstName = rs.getString(6);
                    this.lastName = rs.getString(7);
                    this.address1 = rs.getString(8);
                    this.country = rs.getString(9);
                    this.state = rs.getString(10);
                    this.zip = rs.getString(11);
                    this.phoneNo = rs.getString(12);
                    this.faxNo = rs.getString(13);
                    this.membershipStatus = rs.getString(14);
                    this.memberId = rs.getString(15);
                    Date addDate1 = rs.getDate(16);
                    this.requestStatus = rs.getString(17);
                    this.emailId = rs.getString(18);
                    this.city = rs.getString(19);
                    this.releaseId = rs.getString(20);
                    this.comments = rs.getString(21);
                    
                    this.addDate = DBHelper.dateToString(addDate1);
                    this.assesmentDate = DBHelper.dateToString(assesmentDate1);
                    
                    String [] userList  = {assesmentLevel,assesmentDate,noOfDays,areaName,location,firstName,lastName,address1,
                    country,state,zip,phoneNo,faxNo,membershipStatus,memberId,addDate,requestStatus,emailId,city,releaseId,comments};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }  
/**
  * Name         :listICPUserRegBasedOnStatus() 
  * Description  :This method will retrieve the user registration details for Organizer
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listICPUserRegBasedOnStatus(String status) throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT A.icp_meeting_id,A.user_id,B.first_name, B.last_name, A.membership_status,A.member_id,A.add_date,A.active_status,A.request_status,A.release_id FROM "+
                DBHelper.USEA_ICP_USER_DETAIL+" A, "+DBHelper.USEA_MMS_USERMASTER+" B  WHERE A.user_id = B.user_id AND A.request_status = ? ";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, status);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listICPUserReg status : "+userId);
                while (rs.next()) {
                    this.icpMeetingId = rs.getString(1);
                    this.userId = rs.getString(2);
                    this.firstName = rs.getString(3);
                    this.lastName = rs.getString(4);
                    this.membershipStatus = rs.getString(5);
                    this.memberId = rs.getString(6);
                    Date addDate1 = rs.getDate(7);
                    this.activeStatus = rs.getString(8);
                    this.requestStatus = rs.getString(9);
                    this.releaseId = rs.getString(10);
                    
                    this.addDate = DBHelper.dateToString(addDate1);
                    
                    String [] userList  = {icpMeetingId,userId,firstName,lastName,membershipStatus,memberId,addDate,activeStatus,requestStatus,releaseId};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }    
  //listActivityTypeActivity
/**
  * Name         :listActivityTypeActivity() 
  * Description  :This method will retrieve the Activity details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listActivityTypeActivity() throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT A.activity_type_id,B.specification_id,B.specification_name, A.transaction_type_id FROM "+ 
                DBHelper.USEA_ACTIVITY_CATEGORY+" A, "+DBHelper.USEA_SPECIFICATION_MASTER+" B "+
                " WHERE A.activity_type_id = B.activity_type_id AND A.activity_type_name = ? and B.active_status = ? order by B.specification_name";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, "Activity");
                prepStmt.setBoolean(2, true);
                
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listActivityCategory   ");
                while (rs.next()) {
                    
                    this.specificationId = rs.getString(2);
                    this.specificationName = rs.getString(3);
                    String txnTypeId = rs.getString(4);
                    
                    String [] activityList  = {specificationId,specificationName,txnTypeId};
                    vObj.add(activityList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }     
  
/**
  * Name         :listActivityTypeRegister() 
  * Description  :This method will retrieve the Activity details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listActivityTypeRegister() throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT B.specification_id,B.specification_name, A.transaction_type_id FROM "+ 
                DBHelper.USEA_ACTIVITY_CATEGORY+" A, "+DBHelper.USEA_SPECIFICATION_MASTER+" B "+
                " WHERE A.activity_type_id = B.activity_type_id AND A.activity_type_name = ? and B.active_status = ? ";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, "Registration");
                prepStmt.setBoolean(2 , true);
                
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listActivityCategory   ");
                while (rs.next()) {                   
                    this.specificationId = rs.getString(1);
                    this.specificationName = rs.getString(2);
                    String txnTypeId = rs.getString(3);
                    
                    String [] activityList  = {specificationId,specificationName,txnTypeId};
                    Debug.print(" Specification Id : "+specificationId);
                    Debug.print(" specificationName : "+specificationName);
                    Debug.print(" txnTypeId : "+txnTypeId);
                    
                    vObj.add(activityList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }   
  
/**
  * Name         :listActivityCategory() 
  * Description  :This method will retrieve the Activity details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listActivityCategory() throws SQLException {
        Debug.print("Inside the getUserAndMemberId");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT activity_type_id,activity_type_name FROM "+ DBHelper.USEA_ACTIVITY_CATEGORY;
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listActivityCategory   ");
                while (rs.next()) {
                    this.activityTypeId = rs.getString(1);
                    this.activityTypeName = rs.getString(2);
                    String [] activityList  = {activityTypeId,activityTypeName};
                    vObj.add(activityList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }  
/**
  * Name         :listSpecification() 
  * Description  :This method will retrieve the listSpecification details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */
    
  public Vector listSpecification(String activityId) throws SQLException {
        Debug.print("Inside the listSpecification  activityId : "+activityId);
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT specification_id,activity_type_id,specification_name FROM "+ DBHelper.USEA_SPECIFICATION_MASTER+
                        " WHERE activity_type_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
                prepStmt.setString(1, activityId);
                rs = prepStmt.executeQuery();
                System.out.println("after execute Query activityId  "+activityId);
                while (rs.next()) {
                    this.specificationId = rs.getString(1);
                    this.activityTypeId = rs.getString(2);
                    this.specificationName = rs.getString(3);
                    String [] speciList  = {specificationId,specificationName};
                    vObj.add(speciList);
                }
               
          }catch (SQLException e){
               releaseConnection();
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }   
  
/**
  * Name         :listSpecification() 
  * Description  :This method will retrieve the listSpecification details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listSpecification1() throws SQLException {
        Debug.print("Inside the listSpecification1  : ");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT specification_id,activity_type_id,specification_name FROM "+ DBHelper.USEA_SPECIFICATION_MASTER;
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
               // prepStmt.setString(1, activityId);
                rs = prepStmt.executeQuery();
                System.out.println("after execute Query listSpecification1  ");
                while (rs.next()) {
                    this.specificationId = rs.getString(1);
                    this.activityTypeId = rs.getString(2);
                    this.specificationName = rs.getString(3);
                    String [] speciList  = {specificationId,specificationName};
                    vObj.add(speciList);
                }
               
          }catch (SQLException e){
               releaseConnection();
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }    

/**
  * Name         :listUserType() 
  * Description  :This method will retrieve the listSpecification details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */     
  public Vector listUserType() throws SQLException {
        Debug.print("Inside the listUserType");
        Vector vObj = new Vector();
          try {
                makeConnection();
                String slelctStr = "SELECT user_type_id,user_type_name FROM "+ DBHelper.USEA_MMS_TYPEMASTER;
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
               // prepStmt.setString(1, activityId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the listUserType   ");
                while (rs.next()) {
                    this.userTypeId = rs.getString(1);
                    this.userTypeName = rs.getString(2);
                    String [] userList  = {userTypeId,userTypeName};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
    }
  
  /**
  * Name         :listMemberType() 
  * Description  :This method will retrieve the listSpecification details
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */   
  
  /*public Vector listMemberType() throws SQLException {
        Debug.print("Inside the listMemberType");
        Vector vObj = new Vector();
        ResultSet rs = null;
          try {
                makeConnection();
                String slelctStr = "SELECT mem_type_id, mem_type_name FROM " + DBHelper.USEA_ANNUAL_MEMBERTYPEMASTER;
                
                Debug.print("Query:" + slelctStr);
                PreparedStatement prepStmt = con.prepareStatement(slelctStr);
               // prepStmt.setString(1, activityId);
                 rs = prepStmt.executeQuery();
                System.out.println("Inside the listMemberType   ");
                while (rs.next()) {
                    this.userTypeId = rs.getString(1);
                    this.userTypeName = rs.getString(2);
                    String [] userList  = {userTypeId,userTypeName};
                    vObj.add(userList);
                }
                releaseConnection();
          }catch (SQLException e){
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
          return vObj;
}   */
  
  
  //displayUserDetailsBasedOnMemberId(memberId)
/**
  * Name         :displayUserDetailsBasedOnMemberId() 
  * Description  :This method will retrieve the user registration details for Organizer
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */   
  public Vector displayUserDetailsBasedOnMemberId(String memberId)throws SQLException {
       Vector vObj = new Vector();
       
        makeConnection();
        try {
           
            String selectStatement = "SELECT A.user_id, A.contact_type_id, A.prefix,A.first_name,A.middle_name,A.last_name,A.sufix,A.dob,A.gender,A.email_id, A.password, A.secret_question, "+
                    "A.secret_answer FROM "+DBHelper.USEA_MMS_USERMASTER+" A, "+DBHelper.USEA_MMS_MEMBERDETAIL+" B "+
                    "WHERE A.user_id = B.user_id AND B.member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the selectUserRegistrationForm memberId : "+memberId);
            if (rs.next()) {
            this.userId = rs.getString(1);
            this.contactTypeId = rs.getString(2);
            this.prefix = rs.getString(3);
            this.firstName = rs.getString(4);
            this.middleName = rs.getString(5);
            this.lastName = rs.getString(6);
            this.sufix = rs.getString(7);
            Date dob1 = rs.getDate(8);
            this.gender = rs.getString(9);
            this.emailId = rs.getString(10);
            this.password = rs.getString(11);
            this.secretQuestion = rs.getString(12);
            this.secretAnswer = rs.getString(13);
            this.dob = DBHelper.dateToString(dob1);
            }
                try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPE_MASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            //prepStmt.close();
            //rs.close();
            //releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            /*====================Adding User Details fro User Master===== Vector One=============================================*/
            String userDetail [] = {contactTypeName,prefix,firstName,middleName,lastName,sufix,dob,gender,emailId,password,secretQuestion,secretAnswer,userId };
            vObj.add(userDetail);
            //DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
            //        "address2,city,state,country,zip,phone_no, mobile_no, fax_no
            try {
                 
                String selectStatement = "SELECT suite,address1,address2,city,state,country,zip,phone_no, mobile_no,fax_no,contact_type_id FROM " +
                        DBHelper.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the loadContactDetails User Id : "+userId);
                while (rs.next()) {
                this.suite = rs.getString(1);
                this.address1 = rs.getString(2);
                this.address2 = rs.getString(3);
                this.city = rs.getString(4);
                this.state = rs.getString(5);
                this.country = rs.getString(6);
                this.zip = rs.getString(7);
                this.phoneNo = rs.getString(8);
                this.mobileNo = rs.getString(9);
                this.faxNo = rs.getString(10);
                this.contactTypeId = rs.getString(11);
                    try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPE_MASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                /**==========================Contact Details including Primary and Secondary name ==================================*/
                String contactDetail [] =  {contactTypeName, suite, address1, address2, city, state, country, zip, phoneNo, mobileNo, faxNo }; 
                vObj.add(contactDetail);
                }
            
            releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //prepStmt.close();
                //rs.close();
                //rs1.close();
                releaseConnection();
            }
        return vObj;
    }  
/**
  * Name         :listICPUserReg() 
  * Description  :This method will retrieve the user registration details for Organizer
  * @ param      :none
  * @return      :Vector
  * @throws      :SQLException
  */   
  public Vector displayUserDetails(String email)throws SQLException {
       Vector vObj = new Vector();
       
        makeConnection();
        try {
           
            String selectStatement = "SELECT user_id, contact_type_id, prefix,first_name,middle_name,last_name,sufix,dob,gender,email_id, password, secret_question, "+
                    "secret_answer FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE email_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, email.trim());
            rs = prepStmt.executeQuery();
            System.out.println("Inside the selectUserRegistrationForm email Id : "+email);
            if (rs.next()) {
            this.userId = rs.getString(1);
            this.contactTypeId = rs.getString(2);
            this.prefix = rs.getString(3);
            this.firstName = rs.getString(4);
            this.middleName = rs.getString(5);
            this.lastName = rs.getString(6);
            this.sufix = rs.getString(7);
            Date dob1 = rs.getDate(8);
            this.gender = rs.getString(9);
            this.emailId = rs.getString(10);
            this.password = rs.getString(11);
            this.secretQuestion = rs.getString(12);
            this.secretAnswer = rs.getString(13);
            this.dob = DBHelper.dateToString(dob1);
            }
                try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPE_MASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            //prepStmt.close();
            //rs.close();
            //releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            /*====================Adding User Details fro User Master===== Vector One=============================================*/
            String userDetail [] = {contactTypeName,prefix,firstName,middleName,lastName,sufix,dob,gender,emailId,password,secretQuestion,secretAnswer,userId };
            vObj.add(userDetail);
            //DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
            //        "address2,city,state,country,zip,phone_no, mobile_no, fax_no
            try {
                 
                String selectStatement = "SELECT suite,address1,address2,city,state,country,zip,phone_no, mobile_no,fax_no,contact_type_id FROM " +
                        DBHelper.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the loadContactDetails User Id : "+userId);
                while (rs.next()) {
                this.suite = rs.getString(1);
                this.address1 = rs.getString(2);
                this.address2 = rs.getString(3);
                this.city = rs.getString(4);
                this.state = rs.getString(5);
                this.country = rs.getString(6);
                this.zip = rs.getString(7);
                this.phoneNo = rs.getString(8);
                this.mobileNo = rs.getString(9);
                this.faxNo = rs.getString(10);
                this.contactTypeId = rs.getString(11);
                    try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPE_MASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                /**==========================Contact Details including Primary and Secondary name ==================================*/
                String contactDetail [] =  {contactTypeName, suite, address1, address2, city, state, country, zip, phoneNo, mobileNo, faxNo }; 
                vObj.add(contactDetail);
                }
            
            releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //prepStmt.close();
                //rs.close();
                //rs1.close();
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
