/*
 * CountryMailPriceDAO.java
 *
 * Created on November 15, 2006, 2:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlccountry.dao;

import com.hlccountry.mail.util.HLCCountryMailPriceMaster;
import com.hlccountry.mail.util.DBHelper;
import com.hlccountry.mail.util.Debug;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 *
 * @author suresh
 */
public class HLCCountryMailPriceDAO {
    
     private Connection con;
     private static final String DATASOURCE = "java:/ELMTMSSQLDS";
    /** Creates a new instance of CountryMailPriceDAO */
    public HLCCountryMailPriceDAO() {
    }
    
    //=============================================Insert Annual Details =========================================      
    public boolean insertCountryPrice(String countryMailTypeName, String membershipTypeId, String countryMailPrice) {
            Debug.print("CountryMailPriceDAO.insertCountryPrice():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            String meetingId = "";
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER  + 
                        " (country_mail_type_name, membership_type_id, country_mail_price)" +
                        " values ( ?, ?, ?) ";

                prepStmt = con.prepareStatement(insertStatement);
                
                if(countryMailTypeName!=null || countryMailTypeName.trim().length()!=0 && membershipTypeId!=null && membershipTypeId.trim().length()!=0){
                    prepStmt.setString(1, countryMailTypeName);
                    prepStmt.setString(2, membershipTypeId);
                    prepStmt.setString(3, countryMailPrice);
                    int cnt = prepStmt.executeUpdate();
                    
                    Debug.print("Record Inserted succefully in insertCountryPrice Details: " + cnt);
                    if(cnt>=1){
                        result = true;
                    }
                    
                    Debug.print("CountryMailPriceDAO insertCountryPrice Status :" + result);
                }
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in CountryMailPriceDAO.insertCountryPrice():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in CountryMailPriceDAO.insertCountryPrice():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updateCountryPrice(String countryMailTypeId, String countryMailTypeName, String membershipTypeId, String countryMailPrice) {
            Debug.print("CountryMailPriceDAO.updateCountryPrice():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER  + " set country_mail_type_name = ?, membership_type_id = ?, " +
                         " country_mail_price= ? where country_mail_type_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, countryMailTypeName);
                prepStmt.setString(2, membershipTypeId);
                prepStmt.setString(3, countryMailPrice);
                prepStmt.setString(4, countryMailTypeId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updateCountryPrice : " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("CountryMailPriceDAO updateCountryPrice :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in CountryMailPriceDAO.updateCountryPrice():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in CountryMailPriceDAO.updateCountryPrice():" + e.getMessage());
        }
        return result;
    }    
    
      //=============================================select a All Country price Details =========================================      
    public ArrayList selectAllCountryPriceDetails(int membershipYear){
            Debug.print("CountryMailPriceDAO.selectAllCountryPriceDetails():");
            Debug.print("membershipYear in CountryMailPriceDAO.selectAllCountryPriceDetails():"+membershipYear);
            ArrayList mailPriceList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select A.country_mail_type_id, A.country_mail_type_name, " +
                         " A.membership_type_id, B.membership_type_name , A.country_mail_price, B.membership_year from " +
                         DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " A, " + DBHelper.USEA_MEMBER_TYPE_MASTER + " B " +
                         " where A.membership_type_id = B.membership_type_id and B.membership_year = ?";
                        
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setInt(1, membershipYear);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    HLCCountryMailPriceMaster objCntMailList = new HLCCountryMailPriceMaster();
                    String countryMailTypeId = rs.getString(1);
                    String countryMailTypeName = rs.getString(2);
                    String membershipTypeId = rs.getString(3);
                    String membershipTypeName = rs.getString(4);
                    String countryMailPrice = rs.getString(5);
                    String year =  rs.getString(6);
                    
                    objCntMailList.setCountryMailTypeId(countryMailTypeId);
                    objCntMailList.setCountryMailTypeName(countryMailTypeName);
                    objCntMailList.setMembershipTypeId(membershipTypeId);
                    objCntMailList.setMemberShipName(membershipTypeName);
                    objCntMailList.setCountryMailPrice(countryMailPrice);
                    objCntMailList.setYear(year);
                    mailPriceList.add(objCntMailList);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in CountryMailPriceDAO.selectAllCountryPriceDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in CountryMailPriceDAO.selectAllCountryPriceDetails():" + e.getMessage());
        }
        return mailPriceList;
    }
    
      //=============================================select a All Country price Details =========================================      
    public ArrayList selectAllMembershipType(){
            Debug.print("CountryMailPriceDAO.selectAllMembershipType():");
            ArrayList mailPriceList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select membership_type_id, membership_type_name from " +  DBHelper.USEA_MEMBER_TYPE_MASTER ;

                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    HLCCountryMailPriceMaster objCntMailList = new HLCCountryMailPriceMaster();
                    String membershipTypeId = rs.getString(1);
                    String membershipTypeName = rs.getString(2);
                    
                    String memDet[] = {membershipTypeId, membershipTypeName};
                    
                    mailPriceList.add(memDet);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in CountryMailPriceDAO.selectAllMembershipType():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in CountryMailPriceDAO.selectAllMembershipType():" + e.getMessage());
        }
        return mailPriceList;
    }
    
      //=============================================select a All Country price Details =========================================      
    public HLCCountryMailPriceMaster selectCountryPriceDetailsByPriceId(String countryMailTypeId){
            Debug.print("CountryMailPriceDAO.selectCountryPriceDetailsByPriceId()");
            HLCCountryMailPriceMaster objCntMailList = new HLCCountryMailPriceMaster();
            PreparedStatement prepStmt = null;
            ResultSet rs = null;
            makeConnection();
            try {
                    String selectStatement = "select A.country_mail_type_id, A.country_mail_type_name, " +
                         " A.membership_type_id, B.membership_type_name , A.country_mail_price , B.membership_year from " +
                         DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " A, " + DBHelper.USEA_MEMBER_TYPE_MASTER + " B " +
                         " where A.membership_type_id = B.membership_type_id and A.country_mail_type_id = ?";
                  
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,countryMailTypeId);
                rs = prepStmt.executeQuery();
                if(rs.next()){
                    String countryMailTypeIdVal = rs.getString(1);
                    String countryMailTypeName = rs.getString(2);
                    String membershipTypeId = rs.getString(3);
                    String membershipTypeName = rs.getString(4);
                    String countryMailPrice = rs.getString(5);
                    String year = rs.getString(6);
                    
                    
                    objCntMailList.setCountryMailTypeId(countryMailTypeIdVal);
                    objCntMailList.setCountryMailTypeName(countryMailTypeName);
                    objCntMailList.setMembershipTypeId(membershipTypeId);
                    objCntMailList.setMemberShipName(membershipTypeName);
                    objCntMailList.setCountryMailPrice(countryMailPrice);
                    objCntMailList.setYear(year);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in CountryMailPriceDAO.selectCountryPriceDetailsByPriceId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in CountryMailPriceDAO.selectCountryPriceDetailsByPriceId():" + e.getMessage());
        }
        return objCntMailList;
    }
    
    //=============================================Country Price Name details=========================================      
    public boolean isCountryMailTypeNameExist(String countryMailTypeName) {
        Debug.print("CountryMailPriceDAO.isCountryMailTypeNameExist():" + countryMailTypeName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select country_mail_type_id from " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER + " where country_mail_type_name = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,countryMailTypeName);
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
            Debug.print("Could not find any from countryMailTypeName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isCountryMailTypeNameExist:" + e.getMessage());
        }
        Debug.print("isCountryMailTypeNameExist():" + result);
        return result;
    }
    
//=============================================Privilege Name Checking details=========================================      
    public boolean isCountryMailTypeNameEditExist(String countryMailTypeId, String countryMailTypeName, String membershipTypeId) {
        Debug.print("CountryMailPriceDAO.isCountryMailTypeNameEditExist():" + countryMailTypeName);
        boolean result = true;
        makeConnection();
   	try {

            String selectStatement = "select country_mail_type_id from " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER +
                    " where country_mail_type_name = ? and membership_type_id= ? and country_mail_type_id != ?" ;
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,countryMailTypeName);
            prepStmt.setString(2,membershipTypeId);
            prepStmt.setString(3,countryMailTypeId);
            
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
            Debug.print("Could not find any from isCountryMailTypeNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isCountryMailTypeNameEditExist:" + e.getMessage());
        }
        Debug.print("isCountryMailTypeNameEditExist():" + result);
        return result;
    }
    
    
//=============================================Privilege Name Checking details=========================================      
    public boolean isCountryMailTypeNameExist(String countryMailTypeName, String membershipTypeId) {
        Debug.print("CountryMailPriceDAO.isCountryMailTypeNameExist():" + countryMailTypeName);
        boolean result = true;
        makeConnection();
   	try {

            String selectStatement = "select country_mail_type_id from " + DBHelper.USEA_COUNTRY_MAIL_PRICEMASTER +
                    " where country_mail_type_name = ? and membership_type_id= ? " ;
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, countryMailTypeName);
            prepStmt.setString(2, membershipTypeId);
            
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
            Debug.print("Could not find any from isCountryMailTypeNameExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isCountryMailTypeNameExist:" + e.getMessage());
        }
        Debug.print("isCountryMailTypeNameExist():" + result);
        return result;
    }
    
      //============================================= Database Connectivity=========================================  
       private Connection makeConnection() {
            Debug.print("CountryMailPriceDAO : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(DATASOURCE);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database in CountryMailPriceDAO. " + ex.getMessage());
            }
            return con; 
        }

    //=============================================Connection Release=========================================  
         private void releaseConnection(Connection con) {
            Debug.print("CountryMailPriceDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {

            }
         }
}
