/*
 * AnnualMeetingDAO.java
 *
 * Created on October 30, 2006, 7:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmee.ann;

import com.hlcmeeting.util.HLCAnnualDetVO;
import com.hlcmeeting.util.HLCAnnualPriceDetailVO;
import com.hlcmeeting.util.HLCAnnualRegisterVO;
import com.hlcmeeting.util.HLCAnnualRegistrationDetailVO;
import com.hlcmeeting.util.DBHelper;
import com.hlcmeeting.util.Debug;
import com.hlcmeeting.util.HLCEventRegPendingVO;
import com.hlcmeeting.util.HLCPaymentDetails;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.ArrayList;
import com.hlccommon.util.HLCPaymentDetailVO;
import java.util.Date;
import java.util.*;


/**
 *
 * @author suresh
 */
public class HLCAnnualMeetingDAO {
     private Connection con;
     private static final String DATASOURCE = "java:/ELMTMSSQLDS";
    /** Creates a new instance of AnnualMeetingDAO */
    public HLCAnnualMeetingDAO() {
    }
        
    //=============================================Insert Annual Details =========================================      
    public String insertAnnualDetails(String registrarId, String noOfAddTickets, String totalAmount,String requestStatus, String paymentId) {
            Debug.print("AnnualMeetingDAO.insertAnnualDetails():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            String meetingId = "";
            try{
                meetingId = getNextMeetingId();
            }
            catch(Exception e){
                Debug.print("Exception while getting meeting Id:" + e.getMessage());
            }
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_ANNUAL_DETAILS + 
                        " (annual_meeting_id, registrar_id, no_of_add_tickets, total_amount, request_status, comments, payment_id)" +
                        " values ( ?, ?, ?, ?, ?, ? , ?) ";

                prepStmt = con.prepareStatement(insertStatement);
                Debug.print("AnnualMeetingDAO insertAnnualDetails meetingId :" + meetingId);
                
                if(meetingId!=null || meetingId.trim().length()!=0){
                    prepStmt.setString(1, meetingId);
                    prepStmt.setString(2, registrarId);
                    prepStmt.setString(3, noOfAddTickets);
                    prepStmt.setString(4, totalAmount);
                    prepStmt.setString(5, requestStatus);
                    prepStmt.setString(6, "");
                    prepStmt.setString(7, paymentId);
                    
                    int cnt = prepStmt.executeUpdate();
                    Debug.print("Record Inserted succefully in insertAnnualDetails Details: " + cnt);
                    result = true;
                    Debug.print("AnnualMeetingDAO insertAnnualDetails Status :" + result);
                }
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.insertAnnualDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.insertAnnualDetails():" + e.getMessage());
        }
        return meetingId;
    }
    
    
    
 //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateStatus(String annualMeetingId, String requestStatus, String  comments) {
            Debug.print("AnnualMeetingDAO.updateStatus():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update  " + DBHelper.USEA_ANNUAL_DETAILS + 
                        " set request_status = ? , comments = ?  where annual_meeting_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, requestStatus);
                prepStmt.setString(2, comments);
                prepStmt.setString(3, annualMeetingId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateStatus : " + cnt);
                result = true;
                Debug.print("AnnualMeetingDAO updateStatus Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.updateStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.updateStatus():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateRequestStatus(String ardId, String requestStatus, String  comments) {
            Debug.print("AnnualMeetingDAO.updateRequestStatus():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update  " + DBHelper.USEA_ANNUAL_REGISTRATION + 
                        " set request_status = ? , remarks = ?  where ard_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, requestStatus);
                prepStmt.setString(2, comments);
                prepStmt.setString(3, ardId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateRequestStatus : " + cnt);
                result = true;
                Debug.print("AnnualMeetingDAO updateRequestStatus :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.updateRequestStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.updateRequestStatus():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateMassApprovalStatus(String annualMeetingId, String requestStatus, String  comments) {
            Debug.print("AnnualMeetingDAO.updateMassApprovalStatus():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update  " + DBHelper.USEA_ANNUAL_REGISTRATION + 
                        " set request_status = ? , remarks = ?  where annual_meeting_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, requestStatus);
                prepStmt.setString(2, comments);
                prepStmt.setString(3, annualMeetingId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateRequestStatus : " + cnt);
                result = true;
                Debug.print("AnnualMeetingDAO updateMassApprovalStatus :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.updateMassApprovalStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.updateMassApprovalStatus():" + e.getMessage());
        }
        return result;
    }
    
     //=============================================select a particular Ticket Details =========================================      
    public String[] selectMerchantDetails(){
            Debug.print("AnnualMeetingDAO.selectMerchantDetails():");
            String merchantDet[] = {};
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select  merchant_id, merchand_login_id, merchant_user_id , merchant_pin " +
                         " from tblMerchantDetails ";

                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    String merchantId = rs.getString(1);
                    String merchantLoginId = rs.getString(2);
                    String merchantUserId = rs.getString(3);
                    String merchantPin = rs.getString(4);
                    String tempMerchant[] = {merchantId, merchantLoginId, merchantUserId, merchantPin};
                    merchantDet = tempMerchant;
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAllMeetingTicket():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAllMeetingTicket():" + e.getMessage());
        }
        return merchantDet;
    }
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateMerchantDetails(String merchantId, String merchantLoginId, String  merchantUserId, String merchantPin) {
            Debug.print("AnnualMeetingDAO.updateMerchantDetails():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update  tblMerchantDetails set merchand_login_id = ?, merchant_user_id = ?, " +
                         " merchant_pin= ?, modify_date = ? where merchant_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, merchantLoginId);
                prepStmt.setString(2, merchantUserId);
                prepStmt.setString(3, merchantPin);
                prepStmt.setDate(4, DBHelper.toSQLDate(new Date()));
                prepStmt.setString(5, merchantId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateMerchantDetails : " + cnt);
                result = true;
                Debug.print("AnnualMeetingDAO updateMerchantDetails :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.updateMerchantDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.updateMerchantDetails():" + e.getMessage());
        }
        return result;
    }    

    //=============================================Insert Annual Registration Details =========================================      
    public String insertAnnualRegistrationDetails(String meetingId, String userId,  String  badgeName, 
            String priceId, String daysApplied, double regAmount, boolean ponyMemberStatus, String ponyMemId, 
            String ponyClubName, boolean accFacStatus, String accomodationDetails, String memberTypeName) {
            Debug.print("AnnualMeetingDAO.insertAnnualRegistrationDetails():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            String ardId = "";
            try{
                ardId = getNextId();
            }
            catch(Exception e){
                Debug.print("Exception while getting meeting Id:" + e.getMessage());
            }
            makeConnection();
            Debug.print("After Create ardId:" + ardId);
            Debug.print("       priceId:" + priceId);
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_ANNUAL_REGISTRATION + 
                        " (ard_id, annual_meeting_id, user_id, badge_name, price_id, days_applied, reg_amount, pony_member_status, " +
                         " pony_mem_ID, pony_club_name, accomodation_facilities_status, accomodation_details, mem_typ_Name)" +
                        " values ( ?, ?, ?, ?, ?, ?, ? , ?, ?, ? , ?, ?, ? ) ";

                prepStmt = con.prepareStatement(insertStatement);
            
                if(meetingId!=null || meetingId.trim().length()!=0 && ardId!=null && ardId.trim().length()!=0){
                    prepStmt.setString(1,ardId);
                    prepStmt.setString(2, meetingId);
                    prepStmt.setString(3, userId);
                    prepStmt.setString(4, badgeName);
                    if(priceId.trim().length()==0){
                        priceId = null;
                    }
                    prepStmt.setString(5, priceId);
                    prepStmt.setString(6, daysApplied);
                    prepStmt.setDouble(7, regAmount);
                    prepStmt.setBoolean(8, ponyMemberStatus);
                    prepStmt.setString(9, ponyMemId);
                    prepStmt.setString(10, ponyClubName);
                    prepStmt.setBoolean(11, accFacStatus);
                    prepStmt.setString(12, accomodationDetails);
                    prepStmt.setString(13, memberTypeName);
                    int cnt = prepStmt.executeUpdate();
                    Debug.print("Record Inserted succefully into Annual Registration Details "+cnt);
                    result = true;
                }
            
            Debug.print("AnnualMeetingDAO insertAnnualRegistrationDetails() Status :" + result);
            prepStmt.close();
             releaseConnection(con);
            }
            catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in AnnualMeetingDAO.insertAnnualRegistrationDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in AnnualMeetingDAO.insertAnnualRegistrationDetails():" + e.getMessage());
            }
            return ardId;
        }
    
    

    //=============================================Insert Annual Registration Details =========================================      
    public boolean insertAnnualRegistrationPriceDetails(String ardId, String priceId,  String  amount, 
                int noOfTickets, boolean addtionalTicketsStatus) {
            Debug.print("AnnualMeetingDAO.insertAnnualRegistrationPriceDetails():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_ANNUAL_REGISTRATION_PRICE_DETAILS + 
                        " (ard_id, price_id, amount, no_of_tickets , addtional_tickets_status)" +
                        " values ( ?, ?, ? , ? , ?) ";

            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, ardId);
            prepStmt.setString(2, priceId);
            prepStmt.setString(3, amount);
            prepStmt.setInt(4, noOfTickets);
            prepStmt.setBoolean(5, addtionalTicketsStatus);
            
            if (priceId != null){
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into Annual Registration insertAnnualRegistrationPriceDetails "+cnt);
                result = true;
            }
            
            Debug.print("AnnualMeetingDAO insertAnnualRegistrationPriceDetails() Status :" + result);
            prepStmt.close();
             releaseConnection(con);
            }
            
            catch(SQLException sql){
                sql.printStackTrace();
                releaseConnection(con);
                Debug.print("SQL Exception in AnnualMeetingDAO.insertAnnualRegistrationPriceDetails():" + sql.getMessage());
            }
            
            catch(Exception e){
                e.printStackTrace();
                releaseConnection(con);
                Debug.print("General Exception  in AnnualMeetingDAO.insertAnnualRegistrationPriceDetails():" + e.getMessage());
            }
            return result;
        }
    
     //=============================================Gettting next Id for Annual Meetings =========================================  
     private  String getNextMeetingId()  throws SQLException {
         Debug.print("AnnualMeetingDAO getNextMeetingId() ");
         String meetingId = null;
         makeConnection();
   	try {
            String selectStatement = "SELECT max(annual_meeting_id) from " + DBHelper.USEA_ANNUAL_DETAILS;
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
           
            while (rs.next()) {
                 meetingId = rs.getString(1);
            }
            
            Debug.print("Meeting ID : " + meetingId);
            long nextId = 0;
            if (meetingId != null) {
                nextId = Long.valueOf(meetingId).longValue();
                Debug.print("Next Id : "+nextId);
                if(nextId==0 ){
                    nextId = 10000;
                }
                else{
                    nextId = nextId+1;
                }
                meetingId = String.valueOf(nextId);
            }
            else{
                nextId = 10000;
                meetingId = String.valueOf(nextId);
            }
           
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        } 
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in getNextMeetingId():" + sql.getMessage());
        }
        catch(Exception e){
             releaseConnection(con);
            throw new EJBException("General Exception  in getNextMeetingId():" + e.getMessage());
        }
        Debug.print("Next Id Finally return meetingId: " + meetingId);
        return meetingId;
    }

//=============================================Insert User Registration Details =========================================      
    public String insertUserDetails(String firstName, String lastName, java.util.Date dob, String  gender, String emailId) {
        Debug.print("AnnualMeetingDAO.insertUserDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        String userId  = "";
        try{
            userId = getNextId();
        }
        catch(Exception e){
            Debug.print("Exception while calling getNextId():" + e.getMessage());
        }
        
        Debug.print("AnnualMeetingDAO.insertUserDetails() After Getting userId :" + userId);
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (user_id, first_name,"+
                "last_name,dob,gender,email_id)" +
                    " values ( ? , ? , ? , ? , ?, ?) ";

            prepStmt = con.prepareStatement(insertStatement);


            prepStmt.setString(1, userId);
            prepStmt.setString(2, firstName);
            prepStmt.setString(3, lastName);
            prepStmt.setDate(4, DBHelper.toSQLDate(dob));
            prepStmt.setString(5, gender);
            prepStmt.setString(6, emailId);

            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into insertContactDetails "+cnt);
            result = true;

            Debug.print("AnnualMeetingDAO insertUserDetails() Status :" + result);
            prepStmt.close();
             releaseConnection(con);
            }
            catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in AnnualMeetingDAO.insertUserDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in AnnualMeetingDAO.insertUserDetails():" + e.getMessage());
            }
        }
        return userId;
    }
    
    //=============================================Insert User Registration Details =========================================      
    public boolean insertTicketDetails(String availTkt, String meetingTypeId) {
        Debug.print("AnnualMeetingDAO.insertTicketDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(meetingTypeId!=null && meetingTypeId.trim().length()!=0){
            if(isTicketExist(meetingTypeId)== false){
                makeConnection();
                try {
                     String insertStatement = "insert into " + DBHelper.USEA_ANNUAL_TICKETDETAILS + " (avail_tkt, meeting_type_id) "+
                    " values ( ? , ? ) ";

                prepStmt = con.prepareStatement(insertStatement);

                prepStmt.setString(1, availTkt);
                prepStmt.setString(2, meetingTypeId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertContactDetails "+cnt);
                result = true;

                Debug.print("AnnualMeetingDAO insertTicketDetails() Status :" + result);
                prepStmt.close();
                 releaseConnection(con);
                }
                catch(SQLException sql){
                    releaseConnection(con);
                    Debug.print("SQL Exception in AnnualMeetingDAO.insertTicketDetails():" + sql.getMessage());
                }
                catch(Exception e){
                    releaseConnection(con);
                    Debug.print("General Exception  in AnnualMeetingDAO.insertUserDetails():" + e.getMessage());
                }
            }
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateTicketDetails(String annualTktId, String availTkt) {
            Debug.print("AnnualMeetingDAO.updateTicketDetails():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update  "  + DBHelper.USEA_ANNUAL_TICKETDETAILS +
                        " set avail_tkt = ? where annual_tktid = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, availTkt);
                prepStmt.setString(2, annualTktId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateTicketDetails : " + cnt);
                result = true;
                Debug.print("AnnualMeetingDAO updateTicketDetails Status :" + result);
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.updateTicketDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.updateTicketDetails():" + e.getMessage());
        }
        return result;
    }    
    
 //=============================================Update Request Status for Annual Details =========================================      
    public boolean isTicketExist(String meetingTypeId) {
            Debug.print("AnnualMeetingDAO.isTicketExist():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select  meeting_type_id from   " + DBHelper.USEA_ANNUAL_TICKETDETAILS  +
                        " where meeting_type_id = ? ";

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, meetingTypeId);
                ResultSet rs = prepStmt.executeQuery();
                result = rs.next();
                Debug.print("AnnualMeetingDAO isTicketExist Status :" + result);
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.isTicketExist():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.isTicketExist():" + e.getMessage());
        }
        return result;
    }
    //=============================================select a particular Ticket Details =========================================      
    public String[] selectMeetingTicket(String annualTktId) {
            Debug.print("AnnualMeetingDAO.selectMeetingTicket():");
            String[] result = {};
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select  A.annual_tktid, A.avail_tkt, A.meeting_type_id, B.specification_name from " +
                         DBHelper.USEA_ANNUAL_TICKETDETAILS  + "  A, " + DBHelper.USEA_SPECIFICATION_MASTER +  " B " +
                        " where A.meeting_type_id = B.specification_id and A.annual_tktid = ? ";

                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, annualTktId);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    String annualTId = rs.getString(1);
                    String availTicket = rs.getString(2);
                    String meeId = rs.getString(3);
                    String meeName = rs.getString(4);
                    String tempResult[] = {annualTId, availTicket, meeId , meeName};
                    result = tempResult;
                }
                rs.close();
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectMeetingTicket():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectMeetingTicket():" + e.getMessage());
        }
        return result;
    }
    
     //=============================================select a particular Ticket Details =========================================      
    public ArrayList selectAllMeetingTicket(){
            Debug.print("AnnualMeetingDAO.selectAllMeetingTicket():");
            ArrayList tktList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select  A.annual_tktid, A.avail_tkt, A.meeting_type_id , B.specification_name " +
                         " from " + DBHelper.USEA_ANNUAL_TICKETDETAILS  + " A ," + DBHelper.USEA_SPECIFICATION_MASTER + " B " +
                        " where A.meeting_type_id = B.specification_id ";

                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    String annualTId = rs.getString(1);
                    String availTicket = rs.getString(2);
                    String meeId = rs.getString(3);
                    String meeName = rs.getString(4);
                    String result[] = {annualTId, availTicket, meeId , meeName};
                    tktList.add(result);
                }
                rs.close();
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAllMeetingTicket():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAllMeetingTicket():" + e.getMessage());
        }
        return tktList;
    }
    
     //=============================================select a particular Ticket Details =========================================      
    public ArrayList selectAllSpecification(){
            Debug.print("AnnualMeetingDAO.selectAllSpecification():");
            ArrayList tktList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select  specification_id, specification_name from  " + DBHelper.USEA_SPECIFICATION_MASTER; 
                        
                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    String specification_id = rs.getString(1);
                    String specification_name = rs.getString(2);
                    String result[] = {specification_id, specification_name};
                    tktList.add(result);
                }
                rs.close();
                prepStmt.close();
                 releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAllSpecification():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAllSpecification():" + e.getMessage());
        }
        return tktList;
    }
    
    
    //=============================================Insert User Contact Details =========================================   
       public boolean insertContactDetails(String userId, String address1, String city, String  state, String country, 
               String zip, String phoneNo, String mobileNo, String faxNo) {
        Debug.print("AnnualMeetingDAO.insertContactDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        if(userId!=null && userId.trim().length()!=0){
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_CONTACT_DETAILS + " (user_id, address1,"+
                " city, state, country, zip, phone_no, mobile_no, fax_no, contact_type_id)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?) ";
                prepStmt = con.prepareStatement(insertStatement);
                prepStmt.setString(1, userId);
                prepStmt.setString(2, address1);
                prepStmt.setString(3, city);
                prepStmt.setString(4, state);
                prepStmt.setString(5, country);
                prepStmt.setString(6, zip);
                prepStmt.setString(7, phoneNo);
                prepStmt.setString(8, mobileNo);
                prepStmt.setString(9, faxNo);
                prepStmt.setString(10, "ee2ae764-4750-420b-bc8e-9bd50b00f4fc");

                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into insertContactDetails "+cnt);
                result = true;

                Debug.print("AnnualMeetingDAO insertContactDetails() Status :" + result);
                prepStmt.close();
                 releaseConnection(con);
            }
            catch(SQLException sql){
                releaseConnection(con);
                Debug.print("SQL Exception in AnnualMeetingDAO.insertContactDetails():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(con);
                Debug.print("General Exception  in AnnualMeetingDAO.insertContactDetails():" + e.getMessage());
            }
        }
        return result;
    }
    
     public String getNextId() throws SQLException {
        Debug.print("AnnualMeetingDAO getNextUserId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            Debug.print("AnnualMeetingDAO getNextUserId:" + selectStatement);
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
            throw new EJBException("SQL Exception in getNextUserId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in getNextUserId:" + e.getMessage());
        }
        return nextId;
    }
     
      public String selectMeetingId(String paymentId) throws SQLException {
        Debug.print("AnnualMeetingDAO selectMeetingId");
        makeConnection();
        String meetingId = null;
        try{
            String selectStatement = "select annual_meeting_id from " + DBHelper.USEA_ANNUAL_DETAILS + 
                    " where payment_id = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,paymentId);
            ResultSet rs = prepSelect.executeQuery();
            if(rs.next()){
                meetingId =rs.getString(1);
            }
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in selectMeetingId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in selectMeetingId:" + e.getMessage());
        }
        return meetingId;
    }

//=============================================Select All Annual Register USer=========================================      
    public ArrayList selectAllAnnualDetails(String requestStatus) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAllAnnualDetails():" + requestStatus);
            ArrayList userList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                 String selectStatement = " select A.annual_meeting_id, A.registrar_id, A.no_of_add_tickets, " +
                         " A.total_amount, A.active_status , A.add_date, B.first_name, B.last_name, B.email_id, " +
                         " A.request_status, A.comments, C.check_number, C.payment_status from  " + DBHelper.USEA_ANNUAL_DETAILS + 
                         " A ,  " + DBHelper.USEA_MMS_USERMASTER + " B, " + DBHelper.USEA_PAYMENT_DETAILS + " C where A.registrar_id = B.user_id  " +
                         " and A.payment_id = C.payment_id and A.request_status = ? order by add_date desc";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,requestStatus);
                rs = prepStmt.executeQuery();
                ArrayList al = null;
                while(rs.next()){
                    HLCAnnualRegisterVO objAnnualUser = new HLCAnnualRegisterVO();
                    String annualMeetingId = rs.getString(1);
                    String registrarId = rs.getString(2);
                    String noOfAddTickets = rs.getString(3);
                    String totalAmount = rs.getString(4);
                    boolean activeStatus = rs.getBoolean(5);
                    Date addDate = rs.getDate(6);
                    String firstName = rs.getString(7);
                    String lastName = rs.getString(8);
                    String email = rs.getString(9);
                    String requestStatusVal = rs.getString(10);
                    String comments = rs.getString(11);
                    String checkNo = rs.getString(12);
                    String paymentMode = rs.getString(13);
                    
                    
                    objAnnualUser.setAnnualMeetingId(annualMeetingId);
                    objAnnualUser.setRegistrarId(registrarId);
                    objAnnualUser.setNoOfAddTickets(noOfAddTickets);
                    objAnnualUser.setTotalAmount(totalAmount);
                    objAnnualUser.setActiveStatus(activeStatus);
                    objAnnualUser.setAddDate(addDate);
                    objAnnualUser.setFirstName(firstName);
                    objAnnualUser.setLastName(lastName);
                    objAnnualUser.setEmail(email);
                    objAnnualUser.setRequestStatus(requestStatusVal);
                    objAnnualUser.setComments(comments);
                    objAnnualUser.setCheckNumber(String.valueOf(checkNo));
                    objAnnualUser.setPaymentMode(paymentMode);
                    
                    userList.add(objAnnualUser);
                }

                Debug.print("AnnualMeetingDAO selectAllAnnualDetails size:" + userList.size());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAllAnnualDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAllAnnualDetails():" + e.getMessage());
        }
        return userList;
    }
    
    //=============================================Select All Annual Register USer=========================================      
    public ArrayList selectAllAnnualDetailsForAdminList(String requestStatus) throws SQLException{
        Debug.print("AnnualMeetingDAO.selectAllAnnualDetailsForAdminList():" + requestStatus);
        ArrayList regList = new ArrayList();
        try {
            ArrayList regUserList = selectAllAnnualDetails(requestStatus);
            
            if(regUserList!=null && regUserList.size()!=0){
              
                Iterator itRegUser = regUserList.iterator();
                  while(itRegUser.hasNext()){
                    HLCAnnualRegisterVO objAnnualUser = (HLCAnnualRegisterVO) itRegUser.next();
                    String annualMeetingId = objAnnualUser.getAnnualMeetingId();
                    ArrayList userList = selectAnnualDetailsByMeetingId(annualMeetingId);
                    regList.add(objAnnualUser);
                    regList.add(userList);
                }
            }
        }
        catch(Exception e){
            Debug.print("General Exception  in AnnualMeetingDAO.selectAllAnnualDetailsForAdminList():" + e.getMessage());
        }
        return regList;
    }
    
    
    
    
 
    //=============================================Select All Annual Register USer=========================================      
    public HLCAnnualRegisterVO selectAnnualRequestStatus(String annualMeetingId) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAnnualRequestStatus()");
            HLCAnnualRegisterVO objAnnualUser = new HLCAnnualRegisterVO();
            ArrayList userList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                /* String selectStatement = " select A.annual_meeting_id, A.registrar_id, A.no_of_add_tickets," +
                         " A.total_amount, A.active_status , A.add_date, B.first_name, B.last_name, B.email_id," +
                         " A.request_status, A.comments from " + DBHelper.USEA_ANNUAL_DETAILS 
                         + " A , " +  DBHelper.USEA_MMS_USERMASTER  + " 
                 B where A.registrar_id = B.user_id and A.request_status = ? order by A.add_date desc";
                */
                 
                  String selectStatement = " select A.annual_meeting_id, A.registrar_id, A.no_of_add_tickets, " +
                         " A.total_amount, A.active_status , A.add_date, B.first_name, B.last_name, B.email_id, " +
                         " A.request_status, A.comments, C.check_number, C.payment_status, C.payment_id, C.check_name," +
                          " C.ssl_txn_id, C.bank_name, C.check_date from  " + DBHelper.USEA_ANNUAL_DETAILS + 
                         " A ,  " + DBHelper.USEA_MMS_USERMASTER + " B, " + DBHelper.USEA_PAYMENT_DETAILS + " C where A.registrar_id = B.user_id  " +
                         " and A.payment_id = C.payment_id and A.annual_meeting_id = ? ";
                  
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,annualMeetingId);
                rs = prepStmt.executeQuery();
                while(rs.next()){
                   String annualMeetingIdVal = rs.getString(1);
                    String registrarId = rs.getString(2);
                    String noOfAddTickets = rs.getString(3);
                    String totalAmount = rs.getString(4);
                    boolean activeStatus = rs.getBoolean(5);
                    Date addDate = rs.getDate(6);
                    String firstName = rs.getString(7);
                    String lastName = rs.getString(8);
                    String email = rs.getString(9);
                    String requestStatusVal = rs.getString(10);
                    String comments = rs.getString(11);
                    String checkNo = rs.getString(12);
                    String paymentMode = rs.getString(13);
                    String paymentId = rs.getString(14);
                    String paymentBy = rs.getString(15);
                    String transactionId = rs.getString(16);
                    String bankName = rs.getString(17);
                    Date checkDate = rs.getDate(18);
                    
                    
                    objAnnualUser.setAnnualMeetingId(annualMeetingIdVal);
                    objAnnualUser.setRegistrarId(registrarId);
                    objAnnualUser.setNoOfAddTickets(noOfAddTickets);
                    objAnnualUser.setTotalAmount(totalAmount);
                    objAnnualUser.setActiveStatus(activeStatus);
                    objAnnualUser.setAddDate(addDate);
                    objAnnualUser.setFirstName(firstName);
                    objAnnualUser.setLastName(lastName);
                    objAnnualUser.setEmail(email);
                    objAnnualUser.setRequestStatus(requestStatusVal);
                    objAnnualUser.setComments(comments);
                    objAnnualUser.setCheckNumber(String.valueOf(checkNo));
                    objAnnualUser.setPaymentMode(paymentMode);
                    objAnnualUser.setPaymentId(paymentId);
                    objAnnualUser.setPaymentBy(paymentBy);
                    objAnnualUser.setTransactionId(transactionId);
                    objAnnualUser.setBankName(bankName);
                    objAnnualUser.setCheckDate(checkDate);
                }

                Debug.print("AnnualMeetingDAO selectAnnualRequestStatus size:");
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAnnualRequestStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAnnualRequestStatus():" + e.getMessage());
        }
        return objAnnualUser;
    }
    
    
    //=============================================Select Payment Details =========================================      
    public HLCPaymentDetailVO selectPaymentDetails(String paymentId, String userId) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectPaymentDetails():");
            HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                 String selectStatement = " select payment_id, user_id, cc_name, " +
                         " cc_type, cc_number , cc_exp_month, cc_exp_year, cc_cvvid, bank_name, " +
                         " check_date, check_number, check_name, amount, payment_date, payment_status, " +
                         " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                         " ssl_transaction_type, ssl_invoice_no, ssl_email from  " + DBHelper.USEA_PAYMENT_DETAILS +
                         " where payment_id = ? and user_id = ? ";
                 
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,paymentId);
                prepStmt.setString(2,userId);
                rs = prepStmt.executeQuery();
                if(rs.next()){
                    String paymentIdVal = rs.getString(1);
                    String userIdVal = rs.getString(2);
                    String ccName = rs.getString(3);
                    String ccType = rs.getString(4);
                    String ccNumber = rs.getString(5);
                    int ccExpMonth = rs.getInt(6);
                    int ccExpYear = rs.getInt(7);
                    int ccCvvid = rs.getInt(8);
                    String bankName = rs.getString(9);
                    Date checkDate = rs.getDate(10);
                    String checkNumber = rs.getString(11);
                    String checkName = rs.getString(12);
                    double amount = rs.getDouble(13);
                    
                    
                    Date paymentDate = rs.getDate(14);
                    String paymentStatus = rs.getString(15);
                    
                    String sslResult = rs.getString(16);
                    String sslResultMessage = rs.getString(17);
                    String sslTxnId = rs.getString(18);
                    String sslApprovalCode = rs.getString(19);
                    String sslCvv2Response = rs.getString(20);
                    String sslAvsResponse = rs.getString(21);
                    
                    String sslTransactionType = rs.getString(22);
                    String sslInvoiceNo = rs.getString(23);
                    String sslEmail = rs.getString(24);
           
                    objPayment.setPaymentId(paymentId);
                    objPayment.setUserId(userId);
                    objPayment.setCcName(ccName);
                    objPayment.setCcType(ccType);
                    objPayment.setCcNumber(ccNumber);
                    objPayment.setCcExpMonth(ccExpMonth);
                    objPayment.setCcExpYear(ccExpYear);
                    objPayment.setCcCvvid(ccCvvid);
                    objPayment.setBankName(bankName);
                    objPayment.setCheckDate(checkDate);
                    objPayment.setCheckNumber(checkNumber);
                    objPayment.setCheckName(checkName);
                    objPayment.setAmount(amount);
                    objPayment.setPaymentDate(paymentDate);
                    objPayment.setPaymentStatus( paymentStatus);

                    objPayment.setSslResult(sslResult);
                    objPayment.setSslResultMessage(sslResultMessage);
                    objPayment.setSslTxnId(sslTxnId);
                    objPayment.setSslApprovalCode(sslApprovalCode);
                    objPayment.setSslCvv2Response(sslCvv2Response);
                    objPayment.setSslAvsResponse(sslAvsResponse);
                    objPayment.setSslTransactionType(sslTransactionType);
                    objPayment.setSslInvoiceNo(sslInvoiceNo);
                    objPayment.setSslEmail(sslEmail);
                }

                Debug.print("AnnualMeetingDAO selectPaymentDetails:" + objPayment.getPaymentId());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectPaymentDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectPaymentDetails():" + e.getMessage());
        }
        return objPayment;
    }
    
       public String selectUserName(String userId) throws SQLException {
        Debug.print("AnnualMeetingDAO selectUserName userId" + userId);
        makeConnection();
        String userName = "";
        try{
            String selectStatement = "select first_name, last_name from " + DBHelper.USEA_MMS_USERMASTER + 
                    " where user_id = ?";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            prepSelect.setString(1,userId);
            ResultSet rs = prepSelect.executeQuery();
            if(rs.next()){
                userName = rs.getString(1) + " " + rs.getString(2);
            }
            rs.close();
            prepSelect.close();
            releaseConnection(con);
        }
        catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in selectMeetingId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in selectMeetingId:" + e.getMessage());
        }
         Debug.print("AnnualMeetingDAO selectUserName userName" + userName);
        return userName;
    }
    
    
//=============================================Update Request Status for Annual Details =========================================      
    public boolean updatePaymentStatus(String paymentId, String userId, String sslResult, String sslResultMessage, 
            String  sslTxnId, String sslApprovalCode, String sslCvv2Response, String sslAvsResponse,
            String sslTransactionType, String sslInvoiceNo, String sslEmail, boolean activeStatus) {
             Debug.print("AnnualMeetingDAO.updatePaymentStatus():");
        
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            
            try {
                 String updateStatement = "update  " + DBHelper.USEA_PAYMENT_DETAILS + 
                        " set ssl_result = ? , ssl_result_message = ? ,ssl_txn_id= ?," +
                         " ssl_approval_code = ? , ssl_cvv2_response = ? , ssl_avs_response = ?, " +
                         " ssl_transaction_type = ?, ssl_invoice_no = ? , ssl_email = ?, active_status = ?" +
                         " where  user_id = ? and payment_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, sslResult);
                prepStmt.setString(2, sslResultMessage);
                prepStmt.setString(3, sslTxnId);
                prepStmt.setString(4, sslApprovalCode);
                prepStmt.setString(5, sslCvv2Response);
                prepStmt.setString(6, sslAvsResponse);
                prepStmt.setString(7, sslTransactionType);
                prepStmt.setString(8, sslInvoiceNo);
                prepStmt.setString(9, sslEmail);
                prepStmt.setBoolean(10, activeStatus);
                prepStmt.setString(11, userId);
                prepStmt.setString(12, paymentId);
                
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updatePaymentStatus : " + cnt);
                result = true;
                Debug.print("AnnualMeetingDAO updatePaymentStatus Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.updatePaymentStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.updatePaymentStatus():" + e.getMessage());
        }
        return result;
    }       
  //=============================================Select Annual Register USer Details based on userID=========================================      
    public ArrayList selectMyAnnualDetailsByUserId(String userId) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectMyAnnualDetailsByUserId():");
            ArrayList userList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                 String selectStatement = " select A.annual_meeting_id, A.registrar_id, A.no_of_add_tickets, " +
                         " A.total_amount, A.active_status , A.add_date, B.first_name, B.last_name, B.email_id, " +
                         " A.request_status, A.comments, C.check_number, C.payment_status, C.ssl_txn_id ," +
                         " C.bank_name, C.check_date from  " + DBHelper.USEA_ANNUAL_DETAILS + 
                         " A ,  " + DBHelper.USEA_MMS_USERMASTER + " B, " + DBHelper.USEA_PAYMENT_DETAILS + " C where A.registrar_id = B.user_id  " +
                         " and A.payment_id = C.payment_id and A.registrar_id = ? order by add_date desc";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,userId);
                rs = prepStmt.executeQuery();
                ArrayList al = null;
                while(rs.next()){
                    HLCAnnualRegisterVO objAnnualUser = new HLCAnnualRegisterVO();
                    String annualMeetingId = rs.getString(1);
                    String registrarId = rs.getString(2);
                    String noOfAddTickets = rs.getString(3);
                    String totalAmount = rs.getString(4);
                    boolean activeStatus = rs.getBoolean(5);
                    Date addDate = rs.getDate(6);
                    String firstName = rs.getString(7);
                    String lastName = rs.getString(8);
                    String email = rs.getString(9);
                    String requestStatusVal = rs.getString(10);
                    String comments = rs.getString(11);
                    String checkNo = rs.getString(12);
                    String paymentMode = rs.getString(13);
                    String transactionId = rs.getString(14);
                    String bankName = rs.getString(15);
                    Date checkDate = rs.getDate(16);
                    
                    
                    
                    objAnnualUser.setAnnualMeetingId(annualMeetingId);
                    objAnnualUser.setRegistrarId(registrarId);
                    objAnnualUser.setNoOfAddTickets(noOfAddTickets);
                    objAnnualUser.setTotalAmount(totalAmount);
                    objAnnualUser.setActiveStatus(activeStatus);
                    objAnnualUser.setAddDate(addDate);
                    objAnnualUser.setFirstName(firstName);
                    objAnnualUser.setLastName(lastName);
                    objAnnualUser.setEmail(email);
                    objAnnualUser.setRequestStatus(requestStatusVal);
                    objAnnualUser.setComments(comments);
                    objAnnualUser.setCheckNumber(String.valueOf(checkNo));
                    objAnnualUser.setPaymentMode(paymentMode);
                    objAnnualUser.setTransactionId(transactionId);
                    objAnnualUser.setBankName(bankName);
                    objAnnualUser.setCheckDate(checkDate);
                    
                    userList.add(objAnnualUser);
                }

                Debug.print("AnnualMeetingDAO selectMyAnnualDetailsByUserId size:" + userList.size());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectMyAnnualDetailsByUserId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectMyAnnualDetailsByUserId():" + e.getMessage());
        }
        return userList;
    }
    
    //=============================================Select Annual Register User Details based on annualMeetingId=========================================      
    public ArrayList selectAnnualDetailsByMeetingId(String annualMeetingId) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAnnualDetailsByMeetingId():");
            ArrayList userRegList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                 String selectStatement = " select A.ard_id, A.annual_meeting_id, A.user_id, A.badge_name, B.first_name,  " +
                         " A.days_applied, A.reg_amount, A.pony_member_status, A.pony_mem_ID, A.pony_club_name, " +
                         " A.accomodation_facilities_status, A.accomodation_details, A.request_status,  " +
                         " A.remarks, A.add_date, A.mem_typ_Name, B.last_name from " + DBHelper.USEA_ANNUAL_REGISTRATION + " A, " + 
                         DBHelper.USEA_MMS_USERMASTER + " B  where A.user_id = B.user_id and A.annual_meeting_id = ? ";
                 
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,annualMeetingId);
                rs = prepStmt.executeQuery();
                ArrayList al = null;
                while(rs.next()){
                    HLCAnnualRegistrationDetailVO objAnnualRegDet = new HLCAnnualRegistrationDetailVO();
                    String ardId = rs.getString(1);
                    String meetingId = rs.getString(2);
                    String userId = rs.getString(3);
                    String badgeName = rs.getString(4);
                    String firstName = rs.getString(5);
                  //  String specificationName = rs.getString(6);
                  //  String memTypeName = rs.getString(7);
                    String daysApplied = rs.getString(6);
                    String regAmount  = rs.getString(7);
                    boolean ponyMemberStatus  = rs.getBoolean(8);
                    String ponyMemId  = rs.getString(9);
                    String ponyClubName  = rs.getString(10);
                    boolean accomFaciStatus  = rs.getBoolean(11);
                    String accomDetails  = rs.getString(12);
                    String requestStatus  = rs.getString(13);
                    String remarks  = rs.getString(14);
                    Date addDate  = rs.getDate(15);
                    String memTypName = rs.getString(16);
                    String lastName = rs.getString(17);
                    
                    objAnnualRegDet.setArdId(ardId);
                    objAnnualRegDet.setAnnualMeetingId(meetingId);
                    objAnnualRegDet.setUserId(userId);
                    objAnnualRegDet.setBadgeName(badgeName);
                    objAnnualRegDet.setFirstName(firstName);
                    //objAnnualRegDet.setSpecificationName("");
                    objAnnualRegDet.setMemTypeName(memTypName);
                    objAnnualRegDet.setDaysApplied(daysApplied);
                    objAnnualRegDet.setRegAmount(regAmount);
                    objAnnualRegDet.setPonyMemberStatus(ponyMemberStatus);
                    objAnnualRegDet.setPonyMemId(ponyMemId);
                    objAnnualRegDet.setPonyClubName(ponyClubName);
                    objAnnualRegDet.setAccomFaciStatus(accomFaciStatus);
                    objAnnualRegDet.setAccomDetails(accomDetails);
                    objAnnualRegDet.setRequestStatus(requestStatus);
                    objAnnualRegDet.setRemarks(remarks);
                    objAnnualRegDet.setAddDate(addDate);
                    objAnnualRegDet.setLastName(lastName);
                   
                    userRegList.add(objAnnualRegDet);
                }            

                Debug.print("AnnualMeetingDAO selectAnnualDetailsByMeetingId size:" + userRegList.size());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAnnualDetailsByMeetingId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAnnualDetailsByMeetingId():" + e.getMessage());
        }
        return userRegList;
    }
    
     //=============================================Select Annual Register User Details based on annualMeetingId=========================================      
    public ArrayList selectAnnualRegistrationDetailsByRequestStatus(String requestStatus, int pNo) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAnnualRegistrationDetailsByRequestStatus() requestStatus:" + requestStatus);
            Debug.print("pNo :" + pNo);
            
            ArrayList userRegList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                
                String callableSt = "{call sp_get_acm_registrations(?,?)}"; 
                Debug.print("callableStatement :"+callableSt);
                
                CallableStatement cstmt = con.prepareCall(callableSt);
                cstmt.setInt(1,pNo);
                cstmt.setString(2,requestStatus);
               
                rs = cstmt.executeQuery();
                
                /* String selectStatement = " select A.ard_id,A.annual_meeting_id, A.user_id, " +
                         " A.badge_name, B.first_name,  A.days_applied, A.reg_amount, A.pony_member_status, " +
                         " A.pony_mem_ID, A.pony_club_name, A.accomodation_facilities_status, A.accomodation_details, " +
                         " A.request_status, A.remarks, A.add_date, A.mem_typ_Name, B.last_name, C.registrar_id from " +
                         DBHelper.USEA_ANNUAL_REGISTRATION + " A, " + DBHelper.USEA_MMS_USERMASTER + " B  , " +
                         " tblMeeAnnualDetails C  where A.user_id = B.user_id and A.annual_meeting_id = C.annual_meeting_id and " +
                         " A.request_status = ? order by A.add_date desc";*/
                
                /*prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,requestStatus);
                rs = prepStmt.executeQuery();*/
                
                ArrayList tempUserList = new ArrayList();
                
                while(rs.next()){
                    HLCAnnualRegistrationDetailVO objAnnualRegDet = new HLCAnnualRegistrationDetailVO();
                    String ardId = rs.getString(1);
                    String meetingId = rs.getString(2);
                    String userId = rs.getString(3);
                    String badgeName = rs.getString(4);
                    String firstName = rs.getString(5);
                  //  String specificationName = rs.getString(6);
                  //  String memTypeName = rs.getString(7);
                    String daysApplied = rs.getString(6);
                    String regAmount  = rs.getString(7);
                    boolean ponyMemberStatus  = rs.getBoolean(8);
                    String ponyMemId  = rs.getString(9);
                    String ponyClubName  = rs.getString(10);
                    boolean accomFaciStatus  = rs.getBoolean(11);
                    String accomDetails  = rs.getString(12);
                    String tempRequestStatus  = rs.getString(13);
                    String remarks  = rs.getString(14);
                    Date addDate  = rs.getDate(15);
                    String memTypName = rs.getString(16);
                    String lastName = rs.getString(17);
                    String registrarId = rs.getString(18);
                    
                    objAnnualRegDet.setArdId(ardId);
                    objAnnualRegDet.setAnnualMeetingId(meetingId);
                    objAnnualRegDet.setUserId(userId);
                    objAnnualRegDet.setBadgeName(badgeName);
                    objAnnualRegDet.setFirstName(firstName);
                    //objAnnualRegDet.setSpecificationName("");
                    objAnnualRegDet.setMemTypeName(memTypName);
                    objAnnualRegDet.setDaysApplied(daysApplied);
                    objAnnualRegDet.setRegAmount(regAmount);
                    objAnnualRegDet.setPonyMemberStatus(ponyMemberStatus);
                    objAnnualRegDet.setPonyMemId(ponyMemId);
                    objAnnualRegDet.setPonyClubName(ponyClubName);
                    objAnnualRegDet.setAccomFaciStatus(accomFaciStatus);
                    objAnnualRegDet.setAccomDetails(accomDetails);
                    objAnnualRegDet.setRequestStatus(tempRequestStatus);
                    objAnnualRegDet.setRemarks(remarks);
                    objAnnualRegDet.setAddDate(addDate);
                    objAnnualRegDet.setLastName(lastName);
                    objAnnualRegDet.setRegistrarId(registrarId);
                   
                    tempUserList.add(objAnnualRegDet);
                }

                Debug.print("AnnualMeetingDAO selectAnnualRegistrationDetailsByRequestStatus size:" + tempUserList.size());
                rs.close();
                cstmt.close();
                releaseConnection(con);
                
                if(tempUserList!=null && tempUserList.size()!=0){
                    Iterator it = tempUserList.iterator();
                     Debug.print("AnnualMeetingDAO selectAnnualRegistrationDetailsByRequestStatus size:" + tempUserList.size());
                    while(it.hasNext()){
                        HLCAnnualRegistrationDetailVO objAnnualRegDet = (HLCAnnualRegistrationDetailVO) it.next();
                        String userId = objAnnualRegDet.getRegistrarId();
                         String registrarName = "";
                         Debug.print(" userId:" + userId);
                        if(userId!=null&& userId.trim().length()!=0){
                             registrarName = selectUserName(userId);
                             Debug.print(" inside userId:" + userId);
                        }
                         
                        Debug.print(" registrarName:" + registrarName);
                        objAnnualRegDet.setRegistrarName(registrarName);
                        userRegList.add(objAnnualRegDet);
                    }
                }
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAnnualRegistrationDetailsByRequestStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAnnualRegistrationDetailsByRequestStatus():" + e.getMessage());
        }
        return userRegList;
    }
    
      //=============================================Select Annual Register User Details based on annualMeetingId=========================================      
    public HLCAnnualRegistrationDetailVO selectAnnualDetailsByAnnualId(String ard_id) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAnnualDetailsByAnnualId():");
            HLCAnnualRegistrationDetailVO objAnnualRegDet = new HLCAnnualRegistrationDetailVO();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                  String selectStatement = " select A.ard_id, A.annual_meeting_id, A.user_id,  A.badge_name, B.first_name,  " +
                         " A.days_applied, A.reg_amount, A.pony_member_status, A.pony_mem_ID, A.pony_club_name, " +
                         " A.accomodation_facilities_status, A.accomodation_details, A.request_status,  " +
                         " A.remarks, A.add_date, A.mem_typ_Name, B.last_name from " + DBHelper.USEA_ANNUAL_REGISTRATION + " A, " + 
                         DBHelper.USEA_MMS_USERMASTER + " B  where A.user_id = B.user_id and A.ard_id= ?";
                 
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,ard_id);
                rs = prepStmt.executeQuery();
                if(rs.next()){
                    
                    String ardId = rs.getString(1);
                    String meetingId = rs.getString(2);
                    String userId = rs.getString(3);
                    String badgeName = rs.getString(4);
                    String firstName = rs.getString(5);
                  //  String specificationName = rs.getString(6);
                  //  String memTypeName = rs.getString(7);
                    String daysApplied = rs.getString(6);
                    String regAmount  = rs.getString(7);
                    boolean ponyMemberStatus  = rs.getBoolean(8);
                    String ponyMemId  = rs.getString(9);
                    String ponyClubName  = rs.getString(10);
                    boolean accomFaciStatus  = rs.getBoolean(11);
                    String accomDetails  = rs.getString(12);
                    String requestStatus  = rs.getString(13);
                    String remarks  = rs.getString(14);
                    Date addDate  = rs.getDate(15);
                    String memTypName = rs.getString(16);
                     String lastName = rs.getString(17);
                    
                    objAnnualRegDet.setArdId(ardId);
                    objAnnualRegDet.setAnnualMeetingId(meetingId);
                    objAnnualRegDet.setUserId(userId);
                    objAnnualRegDet.setBadgeName(badgeName);
                    objAnnualRegDet.setFirstName(firstName);
                    //objAnnualRegDet.setSpecificationName("");
                    objAnnualRegDet.setMemTypeName(memTypName);
                    objAnnualRegDet.setDaysApplied(daysApplied);
                    objAnnualRegDet.setRegAmount(regAmount);
                    objAnnualRegDet.setPonyMemberStatus(ponyMemberStatus);
                    objAnnualRegDet.setPonyMemId(ponyMemId);
                    objAnnualRegDet.setPonyClubName(ponyClubName);
                    objAnnualRegDet.setAccomFaciStatus(accomFaciStatus);
                    objAnnualRegDet.setAccomDetails(accomDetails);
                    objAnnualRegDet.setRequestStatus(requestStatus);
                    objAnnualRegDet.setRemarks(remarks);
                    objAnnualRegDet.setAddDate(addDate);
                    objAnnualRegDet.setLastName(lastName);
                }            

                Debug.print("AnnualMeetingDAO selectAnnualDetailsByAnnualId size:");
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAnnualDetailsByAnnualId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAnnualDetailsByAnnualId():" + e.getMessage());
        }
        return objAnnualRegDet;
    }
    
    
      //=============================================Select Annual Register User Details based on annualMeetingId=========================================      
    public ArrayList selectAnnualRegistrationDetailsByUserId(String userId) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAnnualRegistrationDetailsByUserId():");
            ArrayList userRegList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                 String selectStatement = " select A.ard_id,A.annual_meeting_id,A.user_id,  A.badge_name, C.first_name, " +
                         " A.days_applied, A.reg_amount, " +
                         " A.pony_member_status,A.pony_mem_ID, A.pony_club_name,  " +
                         " A.accomodation_facilities_status, A.accomodation_details, A.request_status, " +
                         " A.remarks, A.add_date, A.mem_typ_Name from " + DBHelper.USEA_ANNUAL_REGISTRATION + " A, " + DBHelper.USEA_SPECIFICATION_MASTER + 
                         "  B, " + DBHelper.USEA_MMS_USERMASTER + " C, " + DBHelper.USEA_ANNUAL_DETAILS + " D, " + DBHelper.USEA_ANNUAL_MEMBERTYPEMASTER + 
                         "  E , " + DBHelper.USEA__ANNUAL_PRICE + " F " +
                         " where A.user_id = ?" ;
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,userId);
                rs = prepStmt.executeQuery();
                ArrayList al = null;
                while(rs.next()){
                    HLCAnnualRegistrationDetailVO objAnnualRegDet = new HLCAnnualRegistrationDetailVO();
                    String ardId = rs.getString(1);
                    String meetingId = rs.getString(2);
                    String userIdVal = rs.getString(3);
                    String badgeName = rs.getString(4);
                    String firstName = rs.getString(5);
                   // String specificationName = rs.getString(6);
                  //  String memTypeName = rs.getString(7);
                    String daysApplied = rs.getString(6);
                    String regAmount  = rs.getString(7);
                    boolean ponyMemberStatus  = rs.getBoolean(8);
                    String ponyMemId  = rs.getString(9);
                    String ponyClubName  = rs.getString(10);
                    boolean accomFaciStatus  = rs.getBoolean(11);
                    String accomDetails  = rs.getString(12);
                    String requestStatus  = rs.getString(13);
                    String remarks  = rs.getString(14);
                    Date addDate  = rs.getDate(15);
                    String memTypName = rs.getString(16);
                    
                    objAnnualRegDet.setArdId(ardId);
                    objAnnualRegDet.setAnnualMeetingId(meetingId);
                    objAnnualRegDet.setUserId(userIdVal);
                    objAnnualRegDet.setBadgeName(badgeName);
                    objAnnualRegDet.setFirstName(firstName);
                   // objAnnualRegDet.setSpecificationName(specificationName);
                    objAnnualRegDet.setMemTypeName(memTypName);
                    objAnnualRegDet.setDaysApplied(daysApplied);
                    objAnnualRegDet.setRegAmount(regAmount);
                    objAnnualRegDet.setPonyMemberStatus(ponyMemberStatus);
                    objAnnualRegDet.setPonyMemId(ponyMemId);
                    objAnnualRegDet.setPonyClubName(ponyClubName);
                    objAnnualRegDet.setAccomFaciStatus(accomFaciStatus);
                    objAnnualRegDet.setAccomDetails(accomDetails);
                    objAnnualRegDet.setRequestStatus(requestStatus);
                    objAnnualRegDet.setRemarks(remarks);
                    objAnnualRegDet.setAddDate(addDate);
                    userRegList.add(objAnnualRegDet);
                }            

                Debug.print("AnnualMeetingDAO selectAnnualRegistrationDetailsByUserId size:" + userRegList.size());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAnnualRegistrationDetailsByUserId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAnnualRegistrationDetailsByUserId():" + e.getMessage());
        }
        return userRegList;
    }
    
    public ArrayList getAnnualPendingDetails(String registrar_id) throws SQLException {
        ArrayList pendInfo = new ArrayList();
        HLCAnnualDetVO detailVO = new HLCAnnualDetVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try{
            String sql ="select A.annual_meeting_id, A.registrar_id, A.payment_id, A.total_amount, A.request_status, B.badge_name,"+
                    "B.days_applied, B.remarks, C.cc_name, C.cc_type, C.ssl_txn_id from "+DBHelper.USEA_ANNUAL_DETAILS +" A, "+DBHelper.USEA_ANNUAL_REGISTRATION +" B, "+DBHelper.USEA_PAYMENT+" C "+
                    "where A.registrar_id = C.user_id and A.annual_meeting_id = B.annual_meeting_id and A.payment_id = C.payment_id and "+
                    "A.registrar_id = ? and C.ssl_result='1'";
            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1,registrar_id);
            rs = prepStmt.executeQuery();
            while(rs.next()){
                detailVO = new HLCAnnualDetVO();
                
                String meetId = rs.getString(1);
                String registrat_id = rs.getString(2);
                String payment_id = rs.getString(3);
                float total_amount = rs.getFloat(4);
                String request_status = rs.getString(5);
                String badge_name = rs.getString(6);
                String days_applied = rs.getString(7);
                String remarks = rs.getString(8);
                String cc_name = rs.getString(9);
                String cc_type = rs.getString(10);
                String ssl_txn_id = rs.getString(11);
                
                detailVO.setBadge_name(badge_name);
                detailVO.setCc_name(cc_name);
                detailVO.setCc_type(cc_type);
                detailVO.setDays_applied(days_applied);
                detailVO.setMeetId(meetId);
                detailVO.setPayment_id(payment_id);
                detailVO.setRegistrat_id(registrat_id);
                detailVO.setRemarks(remarks);
                detailVO.setRequest_status(request_status);
                detailVO.setSsl_txn_id(ssl_txn_id);
                detailVO.setTotal_amount(total_amount);
                
                pendInfo.add(detailVO);
            }
        } 
        catch(SQLException sqle){
            Debug.print("SQLException caught "+sqle);
            sqle.printStackTrace();
        }
        catch(Exception e){
            Debug.print("Genereal Exception "+e);
            e.printStackTrace();
        }
        finally{
            releaseConnection(con);
        }
        return pendInfo;
    }
    
    
    public ArrayList getAnnualPendingListPendInfo(String registrar_id, String payment_id,String meetingId) throws SQLException {
        Debug.print("getAnnualPendingListPendInfo" + registrar_id +" payment_id "+ payment_id+" meetingId "+meetingId);
        ArrayList pendInfo = new ArrayList();
        HLCAnnualDetVO detailVO = new HLCAnnualDetVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try{
            String sql ="select A.annual_meeting_id, A.registrar_id, A.payment_id, A.total_amount, A.request_status, B.badge_name,"+
                    "B.days_applied, B.remarks, C.cc_name, C.cc_type, C.ssl_txn_id from "+DBHelper.USEA_ANNUAL_DETAILS +" A, "+DBHelper.USEA_ANNUAL_REGISTRATION +" B, "+DBHelper.USEA_PAYMENT+" C "+
                    "where A.registrar_id = C.user_id and A.annual_meeting_id = B.annual_meeting_id and A.payment_id = C.payment_id and "+
                    "A.registrar_id = ? and C.ssl_result='1' and A.payment_id = ? and A.annual_meeting_id = ?";
            
            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1,registrar_id);
            prepStmt.setString(2,payment_id);
            prepStmt.setString(3,meetingId);
            
            rs = prepStmt.executeQuery();
            while(rs.next()){
                detailVO = new HLCAnnualDetVO();
                String meetId = rs.getString(1);
                String registrat_id = rs.getString(2);
                String pay_id = rs.getString(3);
                float total_amount = rs.getFloat(4);
                String request_status = rs.getString(5);
                String badge_name = rs.getString(6);
                String days_applied = rs.getString(7);
                String remarks = rs.getString(8);
                String cc_name = rs.getString(9);
                String cc_type = rs.getString(10);
                String ssl_txn_id = rs.getString(11);
                
                detailVO.setBadge_name(badge_name);
                detailVO.setCc_name(cc_name);
                detailVO.setCc_type(cc_type);
                detailVO.setDays_applied(days_applied);
                detailVO.setMeetId(meetId);
                detailVO.setPayment_id(pay_id);
                detailVO.setRegistrat_id(registrat_id);
                detailVO.setRemarks(remarks);
                detailVO.setRequest_status(request_status);
                detailVO.setSsl_txn_id(ssl_txn_id);
                detailVO.setTotal_amount(total_amount);
                
                pendInfo.add(detailVO);
            }
        } 
        catch(SQLException sqle){
            Debug.print("SQLException caught "+sqle);
            sqle.printStackTrace();
        }
        catch(Exception e){
            Debug.print("Genereal Exception "+e);
            e.printStackTrace();
        }
        finally{
            releaseConnection(con);
        }
        return pendInfo;
    }    
    
    public ArrayList getAnnualMeetingDetails(String registrar_id, String payment_id) throws SQLException {
        Debug.print("getAnnualMeetingDetails" + registrar_id +" payment_id "+ payment_id);
        ArrayList pendInfo = new ArrayList();
        HLCAnnualDetVO detailVO = new HLCAnnualDetVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try{
            String sql ="select A.annual_meeting_id, A.registrar_id, A.payment_id, A.total_amount, A.request_status, B.badge_name,"+
                    "B.days_applied, B.remarks from "+DBHelper.USEA_ANNUAL_DETAILS +" A, "+DBHelper.USEA_ANNUAL_REGISTRATION +" B, "+
                    DBHelper.USEA_PAYMENT+" C where A.registrar_id = C.user_id and A.annual_meeting_id = B.annual_meeting_id and "+
                    "A.payment_id = C.payment_id and A.registrar_id = ? and A.payment_id = ?";
            
            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1,registrar_id);
            prepStmt.setString(2,payment_id);
            
            rs = prepStmt.executeQuery();
            while(rs.next()){
                detailVO = new HLCAnnualDetVO();
                String meetId = rs.getString(1);
                String registrat_id = rs.getString(2);
                String pay_id = rs.getString(3);
                float total_amount = rs.getFloat(4);
                String request_status = rs.getString(5);
                String badge_name = rs.getString(6);
                String days_applied = rs.getString(7);
                String remarks = rs.getString(8);

                
                detailVO.setBadge_name(badge_name);
                detailVO.setDays_applied(days_applied);
                detailVO.setMeetId(meetId);
                detailVO.setPayment_id(pay_id);
                detailVO.setRegistrat_id(registrat_id);
                detailVO.setRemarks(remarks);
                detailVO.setRequest_status(request_status);
                detailVO.setTotal_amount(total_amount);
                
                pendInfo.add(detailVO);
            }
        } 
        catch(SQLException sqle){
            Debug.print("SQLException caught "+sqle);
            sqle.printStackTrace();
        }
        catch(Exception e){
            Debug.print("Genereal Exception "+e);
            e.printStackTrace();
        }
        finally{
            releaseConnection(con);
        }
        return pendInfo;
    } 
    
    
     //=============================================Select Annual Register User Details based on annualMeetingId=========================================      
    public ArrayList selectAnnualRegistrationPriceDetailsByAnnRegId(String ardId) throws SQLException{
            Debug.print("AnnualMeetingDAO.selectAnnualRegistrationDetailsByUserId():");
            ArrayList userRegList = new ArrayList();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                 String selectStatement = " select A.ard_pricemap_id, A.ard_id, C.first_name, B.specification_name, " +
                        " E.mem_type_name , A.amount, A.add_date, A.no_of_tickets, addtional_tickets_status  from " + DBHelper.USEA_ANNUAL_REGISTRATION_PRICE_DETAILS + " A," +
                         DBHelper.USEA_SPECIFICATION_MASTER + "  B, " + DBHelper.USEA_MMS_USERMASTER + " C, " + DBHelper.USEA_ANNUAL_REGISTRATION + " D, " +
                         DBHelper.USEA_ANNUAL_MEMBERTYPEMASTER + "  E , " + DBHelper.USEA__ANNUAL_PRICE + " F " +
                        " where A.price_id = F.price_id and F.specification_id = B.specification_id  " +
                        " and F.mem_type_id = E.mem_type_id and D.user_id = C.user_id and A.ard_id = D.ard_id and " +
                        " A.ard_id = ? order by A.addtional_tickets_status";
                 
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,ardId);
                rs = prepStmt.executeQuery();
                ArrayList al = null;
                while(rs.next()){
                    String ardPricemapId = rs.getString(1);
                    String ardIdVal = rs.getString(2);
                    String firstName = rs.getString(3);
                    String specificationName = rs.getString(4);
                    String memTypeName = rs.getString(5);
                    String amount = rs.getString(6);
                    Date addDate = rs.getDate(7);
                    int totalTkt = rs.getInt(8);
                    boolean addTktStaus = rs.getBoolean(9);
                    
                    
                    HLCAnnualPriceDetailVO objPriceDet = new HLCAnnualPriceDetailVO();
                    objPriceDet.setArdPricemapId(ardPricemapId);
                    objPriceDet.setArdId(ardIdVal);
                    objPriceDet.setFirstName(firstName);
                    objPriceDet.setSpecificationName(specificationName);
                    objPriceDet.setMemTypeName(memTypeName);
                    objPriceDet.setAmount(amount);
                    objPriceDet.setAddDate(addDate);
                    objPriceDet.setTotalTicket(totalTkt);
                    objPriceDet.setAddTktStaus(addTktStaus);
                    
                    
                    userRegList.add(objPriceDet);
                }            

                Debug.print("AnnualMeetingDAO selectAnnualRegistrationDetailsByUserId size:" + userRegList.size());
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in AnnualMeetingDAO.selectAnnualRegistrationDetailsByUserId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.selectAnnualRegistrationDetailsByUserId():" + e.getMessage());
        }
        return userRegList;
    }
    
    public ArrayList getEventRegPayPendingDets(String userId) throws SQLException {
        ArrayList payPendInfo = new ArrayList();
        //HLCEventRegPendingVO detailVO = new HLCEventRegPendingVO();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try{
            String sql ="select A.event_id, C.user_id, B.payment_id, " +
                    "A.event_title, A.organizer_id, C.cc_name, " +
                    "C.cc_type, C.ssl_txn_id, C.amount from tblMeeEventDetails A, " +
                    "tblMeeEventOrganizerRenewalDetails B, tblUserPaymentDetails C " +
                    "where A.event_id = B.event_id and C.payment_id = B.payment_id " +
                    "and C.user_id = ? and C.ssl_result='1'";
            prepStmt = con.prepareStatement(sql);
            prepStmt.setString(1,userId);
            rs = prepStmt.executeQuery();
            while(rs.next()){              
                String eventId = rs.getString(1);
                userId = rs.getString(2);
                String paymentId = rs.getString(3);
                String eveTitle = rs.getString(4);
                String organizerId = rs.getString(5);
                String cc_name = rs.getString(6);
                String cc_type = rs.getString(7);
                String ssl_txn_id = rs.getString(8);
                float totalAmount = rs.getFloat(9);                           
                String [] tempList={eventId,userId,paymentId,eveTitle,organizerId,cc_name,cc_type,ssl_txn_id,String.valueOf(totalAmount)};                
                payPendInfo.add(tempList);
            }
        } 
        catch(SQLException sqle){
            Debug.print("SQLException caught in getEventRegPayPendingDets "+sqle);
            sqle.printStackTrace();
        }
        catch(Exception e){
            Debug.print("Genereal Exception in getEventRegPayPendingDets"+e);
            e.printStackTrace();
        }
        finally{
            releaseConnection(con);
        }
        return payPendInfo;
    }
     public HLCEventRegPendingVO getSingleEventRegPendingDetails(String eventId) throws SQLException {
        HLCEventRegPendingVO eveRegPendVO = new HLCEventRegPendingVO();
        Debug.print("Inside DAO");
        try {
            makeConnection();
            
            String selStatement = "select A.event_id, A.organizer_id, A.competition_name, B.competition_location, " +
                    "B.competition_city, B.competition_state, B.competition_country, B.competition_zip, B.championship_area, " +
                    "C.payment_id, C.cc_name, C.cc_type, C.cc_number, C.cc_exp_month, C.cc_exp_year, C.cc_cvvid, " +
                    "C.amount, C.payment_date, C.payment_status, C.ssl_txn_id, C.ssl_email, " +
                    "D.state_name, E.area_name, C.ssl_result_message from tblMeeEventOrganizerRenewalDetails A, tblMeeEventDetails B, " +
                    "tblUserPaymentDetails C, tblMeeStateMaster D, tblMeeAreaMaster E where A.event_id=B.event_id " +
                    "and A.payment_id=C.payment_id and B.competition_state=D.state_id and B.championship_area=E.area_id " +
                    "and A.event_id=?";
            
            Debug.print("Query :"+selStatement);
            PreparedStatement prepStmt = con.prepareStatement(selStatement);
            prepStmt.setString(1,eventId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){            
                eveRegPendVO.setEventId(rs.getString(1));
                eveRegPendVO.setOrgId(rs.getString(2));
                eveRegPendVO.setCompName(rs.getString(3));                                           
                eveRegPendVO.setCompLocation(rs.getString(4));
                eveRegPendVO.setCompCity(rs.getString(5));
                eveRegPendVO.setCompState(rs.getString(6));
                eveRegPendVO.setCompCountry(rs.getString(7));
                eveRegPendVO.setCompZip(rs.getString(8));
                eveRegPendVO.setCompArea(rs.getString(9));
                eveRegPendVO.setPaymentId(rs.getString(10));
                eveRegPendVO.setCcName(rs.getString(11));
                eveRegPendVO.setCcType(rs.getString(12));
                eveRegPendVO.setCcNumber(rs.getString(13));
                eveRegPendVO.setCc_exp_Month(rs.getInt(14));
                eveRegPendVO.setCc_exp_Year(rs.getInt(15));
                eveRegPendVO.setCc_CvvId(rs.getInt(16));             
                eveRegPendVO.setAmt(rs.getFloat(17));
                eveRegPendVO.setPaymentDate(rs.getDate(18));
                eveRegPendVO.setPaymentStatus(rs.getString(19));              
                eveRegPendVO.setSslTxnId(rs.getString(20));
                eveRegPendVO.setSslEmail(rs.getString(21));                
                eveRegPendVO.setStatName(rs.getString(22));
                eveRegPendVO.setAreaName(rs.getString(23));
                eveRegPendVO.setSslResultMessage(rs.getString(24));
            }
            prepStmt.close();
            releaseConnection(con);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return eveRegPendVO;
    }
   public boolean updateRowPendingPayment(HLCPaymentDetails paymentVO) throws SQLException {
        Debug.print("PaymentBean updateRowPendingPayment");
        
        boolean paymentResult = false;
        boolean result = false;
        String paymentId=paymentVO.getPaymentId(); 
        if(paymentId!=null && paymentId.trim().length()!=0){
        makeConnection();
        try{
            String updateStatement = "update " + DBHelper.USEA_PAYMENT + " set user_id=?, cc_name=?,  cc_type=?,  cc_number=?,  cc_exp_month=?, " +
                    "  cc_exp_year=?,  cc_cvvid=?,  bank_name=?,  check_date=?,  check_number=?,  amount=?,  payment_date=?,  payment_status=?, " +
                    "  ssl_result=?,  ssl_result_message=?,  ssl_txn_id=?,  ssl_approval_code=?,  ssl_cvv2_response=?,  ssl_avs_response=?, " +
                    "  ssl_transaction_type=?,  ssl_invoice_no=?,  ssl_email=? where payment_id=? ";
            Debug.print("Query :"+updateStatement);
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            
            prepStmt.setString(1, paymentVO.getUserId());
            prepStmt.setString(2, paymentVO.getCcName());
            prepStmt.setString(3, paymentVO.getCcType());
            prepStmt.setLong(4, paymentVO.getPendPayccNumber());
            prepStmt.setInt(5, paymentVO.getCcExpMonth() );
            prepStmt.setInt(6, paymentVO.getCcExpYear());
            prepStmt.setInt(7, paymentVO.getCcCvvid());
            prepStmt.setString(8, paymentVO.getBankName());
            if(paymentVO.getCheckDate()!=null) prepStmt.setDate(9, DBHelper.toSQLDate(paymentVO.getCheckDate()));
            else prepStmt.setDate(9, null);
            prepStmt.setLong(10, paymentVO.getPendPayCheckNumber());
            prepStmt.setDouble(11, paymentVO.getAmount());
            prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(13, paymentVO.getPaymentStatus());
            prepStmt.setString(14, paymentVO.getSslResult());
            prepStmt.setString(15, paymentVO.getSslResultMessage());
            prepStmt.setString(16, paymentVO.getSslTxnId());
            prepStmt.setString(17, paymentVO.getSslApprovalCode());
            prepStmt.setString(18, paymentVO.getSslCvv2Response());
            prepStmt.setString(19, paymentVO.getSslAvsResponse());
            prepStmt.setString(20, paymentVO.getSslTransactionType());
            prepStmt.setString(21, paymentVO.getSslInvoiceNo());
            prepStmt.setString(22, paymentVO.getSslEmail());
            prepStmt.setString(23, paymentId);
          
            int cnt = prepStmt.executeUpdate();
                Debug.print("Record updated succefully into updateRowPendingPayment "+cnt);
                if(cnt >=1) result = true;
                Debug.print("AnnualMeetingDAO updateRowPendingPayment() Status :" + result);
                prepStmt.close();
                releaseConnection(con);
            
        } catch(SQLException sql){
            releaseConnection(con);
            throw new EJBException("SQL Exception in updateRowPendingPayment:" + sql.getMessage());
        } catch(Exception e){
            releaseConnection(con);
            throw new EJBException("General Exception  in updateRowPendingPayment:" + e.getMessage());
        }
        }
        return result;
    }
   
  public String[] selectEventIds(String eventTypeName,String eventLevelName, String eventDivName){
            Debug.print("AnnualMeetingDAO.getEventIds():");
            String eventDet[] = {};
            PreparedStatement prepStmt = null;
            PreparedStatement prepStmt1 = null;
            PreparedStatement prepStmt2 = null;
            String eventTypeId="";
            String eventLevelId="";
            String eventDivId="";
            try {
            makeConnection();    
            String selectStatement = "select event_type_id from tblMeeEventTypeMaster where event_type_name=?"; 
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,eventTypeName);
            ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                eventTypeId = rs.getString(1);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.getEventIds():" + e.getMessage());
            }
            try {
            makeConnection();    
            String selectStatement1 = "select event_level_id from tblMeeEventLevelMaster where event_level_code=?";   
            prepStmt1 = con.prepareStatement(selectStatement1);
            prepStmt1.setString(1,eventLevelName);
            ResultSet rs1 = prepStmt1.executeQuery();
                while(rs1.next()){    
                eventLevelId = rs1.getString(1);
                }
                rs1.close();
                prepStmt1.close();
                releaseConnection(con);
            }catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.getEventIds():" + e.getMessage());
            }
            try {
            makeConnection();
            String selectStatement2 = "select event_division_id from tblMeeEventDivisionMaster where event_division_name=?";
            prepStmt2 = con.prepareStatement(selectStatement2);
            prepStmt2.setString(1,eventDivName);
            ResultSet rs2 = prepStmt2.executeQuery();
                while(rs2.next()){
                eventDivId = rs2.getString(1);
                }
                rs2.close();
                prepStmt2.close();
                releaseConnection(con);
            }catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in AnnualMeetingDAO.getEventIds():" + e.getMessage());
            }
            String tempeventDet[] = {eventTypeId, eventLevelId, eventDivId};
            eventDet = tempeventDet;
            return eventDet;
    } 
   
    //============================================= Database Connectivity=========================================  
       private Connection makeConnection() {
            Debug.print("AnnualMeetingDAO : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(DATASOURCE);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database in AnnualMeetingDAO. " + ex.getMessage());
            }
            return con; 
        }

    //=============================================Connection Release=========================================  
         private void releaseConnection(Connection con) {
            Debug.print("AnnualMeetingDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {

            }
         }
}
