/*
 * DonationDAO.java
 *
 * Created on November 15, 2006, 4:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcdonation.dao;

import com.hlcmrm.util.DBHelper;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCDonationVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
/**
 *
 * @author suresh
 */
public class HLCDonationDAO {
    private Connection con;
    private static final String DATASOURCE = "java:/ELMTMSSQLDS";
    
    
    /** Creates a new instance of DonationDAO */
    public HLCDonationDAO() {
    }
    
    
    
     //=============================================Insert Donation Details =========================================      
    public boolean insertDonationPrice(String donationName, String donationPrice, String transaction_type_id, boolean precheckStatus, int priority) {
            Debug.print("DonationDAO.insertDonationPrice():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            String meetingId = "";
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_DONATION_DETAILS  + 
                        " (donation_name, donation_price,active_status,precheck_status, transaction_type_id, priority_value)" +
                        " values ( ? , ?, ?, ? , ?, ? ) ";
                 
                Debug.print("Query Log :"+insertStatement);
                
                prepStmt = con.prepareStatement(insertStatement);
                Debug.print("AnnualMeetingDAO insertDonationPrice  :" + meetingId);
                
                if(donationName!=null || donationName.trim().length()!=0 && donationPrice!=null && donationPrice.trim().length()!=0){
                    prepStmt.setString(1, donationName);
                    prepStmt.setString(2, donationPrice);
                    prepStmt.setBoolean(3,true);
                    prepStmt.setBoolean(4,precheckStatus);
                    prepStmt.setString(5,transaction_type_id);
                    prepStmt.setInt(6,priority);
                    
                    int cnt = prepStmt.executeUpdate();
                    
                    Debug.print("Record Inserted succefully in insertDonationPrice Details: " + cnt);
                    if(cnt>=1){
                        result = true;
                    }
                    
                    Debug.print("DonationDAO insertDonationPrice Status :" + result);
                }
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.insertCountryPrice():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.insertCountryPrice():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateDonationPrice(String donationId, String donationName, String donationPrice, String transaction_type_id, boolean precheckStatus,int priority) {
            Debug.print("DonationDAO.updateDonationPrice():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update " + DBHelper.USEA_DONATION_DETAILS  + " set donation_name = ?, donation_price = ?, " +
                         " precheck_status = ?, transaction_type_id = ?, priority_value=?  where donation_id = ?";

                 Debug.print("Query Log :"+updateStatement);
                 
                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, donationName);
                prepStmt.setString(2, donationPrice);
                prepStmt.setBoolean(3, precheckStatus);
                prepStmt.setString(4, transaction_type_id);
                prepStmt.setInt(5,priority);
                prepStmt.setString(6, donationId);
                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateCountryPrice : " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("DonationDAO updateCountryPrice :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.updateCountryPrice():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.updateCountryPrice():" + e.getMessage());
        }
        return result;
    }    
    

    public boolean activeDonation(String donationId) {
        Debug.print("DonationDAO.activeDonation():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_DONATION_DETAILS + " set active_status = ? where donation_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, donationId);
           Debug.print("updateStmt "+updateStatement);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in activeDonation : " + cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("DonationDAO activeDonation :" + result);
            prepStmt.close();
            releaseConnection(con);
        }catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.activeDonation():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.activeDonation():" + e.getMessage());
        }
        return result;
    }
    
    public boolean deactiveDonation(String donationId) {
        Debug.print("DonationDAO.deactiveDonation():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String updateStatement = "update " + DBHelper.USEA_DONATION_DETAILS + " set active_status = ? where donation_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setBoolean(1, false);
            prepStmt.setString(2, donationId);
           Debug.print("updateStmt "+updateStatement);
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Updated succefully in deactiveDonation : " + cnt);
            if(cnt>=1){
                result = true;
            }
            Debug.print("DonationDAO deactiveDonation :" + result);
            prepStmt.close();
            releaseConnection(con);
        }catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.deactiveDonation():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.deactiveDonation():" + e.getMessage());
        }
        return result;
    }
    
          //=============================================select a All Country price Details =========================================      
    public ArrayList selectAllDonationPriceDetails(){
            Debug.print("DonationDAO.selectAllDonationPriceDetails():");
            ArrayList donationList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select donation_id, donation_name, active_status, donation_price,precheck_status from " + 
                         DBHelper.USEA_DONATION_DETAILS+ " WHERE active_status = ? order by priority_value ";
                        
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setBoolean(1, true);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    HLCDonationVO objDonVO = new HLCDonationVO();
                    String donationId = rs.getString(1);
                    String donationName = rs.getString(2);
                    boolean status = rs.getBoolean(3);
                    String donationPrice = rs.getString(4);
                    boolean precheckStatus = rs.getBoolean(5);
                    
                    objDonVO.setDonationId(donationId);
                    objDonVO.setDonationName(donationName);
                    objDonVO.setDonationPrice(donationPrice);
                    objDonVO.setActiveStatus(status);
                    objDonVO.setPrecheckStatus(precheckStatus);
                    donationList.add(objDonVO);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.selectAllDonationPriceDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.selectAllDonationPriceDetails():" + e.getMessage());
        }
        return donationList;
    }
    
    public ArrayList selectAllDonationPriceDetailsBasedOnStatus(boolean activeStatus){
            Debug.print("DonationDAO.selectAllDonationPriceDetails():");
            ArrayList donationList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select donation_id, donation_name, active_status, donation_price, priority_value from " + 
                         DBHelper.USEA_DONATION_DETAILS+ " WHERE active_status = ? order by priority_value ";
                        
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setBoolean(1, activeStatus);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    HLCDonationVO objDonVO = new HLCDonationVO();
                    String donationId = rs.getString(1);
                    String donationName = rs.getString(2);
                    boolean status = rs.getBoolean(3);
                    String donationPrice = rs.getString(4);
                    int priorValue=rs.getInt(5);
                    
                    objDonVO.setDonationId(donationId);
                    objDonVO.setDonationName(donationName);
                    objDonVO.setDonationPrice(donationPrice);
                    objDonVO.setActiveStatus(status);
                    objDonVO.setPriorityValue(priorValue);
                    
                    donationList.add(objDonVO);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.selectAllDonationPriceDetailsBasedOnStatus():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.selectAllDonationPriceDetailsBasedOnStatus():" + e.getMessage());
        }
        return donationList;
    }
    
    
     //=============================================select a All Country price Details =========================================      
    public HLCDonationVO selectDonationPriceDetailsById(String donationId){
            Debug.print("DonationDAO.selectDonationPriceDetailsById() DonationId:" + donationId);
             HLCDonationVO objDonVO = new HLCDonationVO();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select donation_id, donation_name, " +
                         " donation_price,precheck_status,transaction_type_id, priority_value from " + DBHelper.USEA_DONATION_DETAILS +
                         " where donation_id= ?";
                        
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,donationId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    String donationIdVal = rs.getString(1);
                    String donationName = rs.getString(2);
                    String donationPrice = rs.getString(3);
                    boolean precheck_status = rs.getBoolean(4);
                    String transaction_type_id = rs.getString(5);
                    int priorValue = rs.getInt(6);
                    
                    objDonVO.setDonationId(donationIdVal);
                    objDonVO.setDonationName(donationName);
                    objDonVO.setDonationPrice(donationPrice);
                    objDonVO.setPrecheckStatus(precheck_status);
                    objDonVO.setTransaction_type_id(transaction_type_id);
                    objDonVO.setPriorityValue(priorValue);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in DonationDAO.selectDonationPriceDetailsById():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in DonationDAO.selectDonationPriceDetailsById():" + e.getMessage());
        }
        return objDonVO;
    }
    
     //=============================================Country Price Name details=========================================      
    public boolean isDonationTypeNameExist(String donationName) {
        Debug.print("DonationDAO.isDonationTypeNameExist():" + donationName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select donation_id from " + DBHelper.USEA_DONATION_DETAILS + " where donation_name = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,donationName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } 
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from isDonationTypeNameExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isDonationTypeNameExist:" + e.getMessage());
        }
        Debug.print("isDonationTypeNameExist():" + result);
        return result;
    }
    
    //=============================================Privilege Name Checking details=========================================      
    public boolean isDonationTypeNameEditExist(String donationId, String donationName) {
        Debug.print("CountryMailPriceDAO.isDonationTypeNameEditExist():" + donationId);
        boolean result = true;
        makeConnection();
   	try {

            String selectStatement = "select donation_id from " + DBHelper.USEA_DONATION_DETAILS +
                    " where donation_name = ?  and donation_id != ?" ;
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,donationName);
            prepStmt.setString(2,donationId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(con);
        } 
        catch (SQLException e) {
            releaseConnection(con);
            Debug.print("Could not find any from isDonationTypeNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isDonationTypeNameEditExist:" + e.getMessage());
        }
        Debug.print("isDonationTypeNameEditExist():" + result);
        return result;
    }
    
      //============================================= Database Connectivity=========================================  
       private Connection makeConnection() {
            Debug.print("DonationDAO : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(DATASOURCE);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database in DonationDAO. " + ex.getMessage());
            }
            return con; 
        }

    //=============================================Connection Release=========================================  
         private void releaseConnection(Connection con) {
            Debug.print("DonationDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {

            }
         }
}
