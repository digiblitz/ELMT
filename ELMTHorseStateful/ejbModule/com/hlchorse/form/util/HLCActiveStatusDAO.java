/*
 * ActiveStatusDAO.java
 *
 * Created on September 12, 2006, 7:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlchorse.form.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
/**
 *
 * @author harmohan
 */
public class HLCActiveStatusDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    /** Creates a new instance of ActiveStatusDAO */
    public HLCActiveStatusDAO() {
    }
    
/**
  * Name         :updateActiveStatus
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    public void updateActiveStatus(String activeStatus, String userId) throws SQLException {
        Debug.print("Inside the updateActiveStatus");
        boolean bol = true;
        if (activeStatus.equalsIgnoreCase("true")){
            bol = true;
        }else if (activeStatus.equalsIgnoreCase("false")) {  
            bol = false;  
        }
      try {
            makeConnection();
            String updateStatement ="update " + DBHelper.USEA_MMS_USERMASTER  + " set  active_status = ? where user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setBoolean(1, bol);
            prepStmt.setString(2, userId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Active Status Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
      }catch (SQLException e){
          e.printStackTrace();
      }
      
    }   
    
    public  boolean getMemberShipStatus(String horseMemberId)throws SQLException, ParseException {
        String expDate = null;
        String yr = null;
        boolean bol = false;
        Calendar cal = new GregorianCalendar();
        
         try {
            makeConnection();
            String str = "SELECT expiry_date FROM tblHorseMemberDetails WHERE  horse_member_id = '"+horseMemberId+"'";
           /* prepStmt = con.prepareStatement(str);
            prepStmt.setString(1,horseMemberId);
            rs = prepStmt.executeQuery();*/
            Debug.print("Query "+str);
             Statement stmt = con.createStatement();       
             rs = stmt.executeQuery(str);
              Debug.print("Query "+str);
            if (rs.next()) {
                 expDate = rs.getString("expiry_date");
             } 
           stmt.close();
          }catch (SQLException e){
              releaseConnection();
              e.printStackTrace();
          }finally {
              releaseConnection();
          }
        Debug.print(" Expiry Date : "+expDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dt =(Date) formatter.parse(expDate); 

        
        Date curDate = new Date();
        Debug.print(" Current Date : "+curDate);
        
       /* int year = cal.get(Calendar.YEAR);             // 2002
        if (expDate != null && (expDate.length()) > 0 ){
            yr = expDate.substring(0,4);
        }
        Debug.print(" Expiry Year : "+yr+"   Current Year : "+year);
        int xx = Integer.parseInt(yr);
        if (year > xx ){
            bol = true;
        }else {
            bol = false;
        }*/
        if (curDate.before(dt)){
            bol = false;
        }else {
            bol = true;
        }
        
        
        return bol;
    }
    
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
