/*
 * DBHelper.java
 *
 * Created on August 24, 2006, 12:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.lmsstock.util;

import com.lmsstock.exception.LMSPrimaryKeyException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;

/**
 *
 * @author harmohan
 */
public final class DBHelper {
    
  
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
   
    Connection con = null;
    
    

    
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
    
   
    public static final String getUserId(Connection con, String loginName) throws SQLException ,LMSPrimaryKeyException{
        Debug.print("DBHelper getUserId loginName:" + loginName);
        String result = getId(con,"tblUserMaster", loginName);
        Debug.print("DBHelper getUserId userId:" + result);
        return result;
    }
    
    
    private static final String getId(Connection con,String table, String loginName)
    throws SQLException ,LMSPrimaryKeyException {
        Debug.print("DBHelper getId");
        String selectStatement = "SELECT user_id from " + table + " WHERE login_name = ? ";
        Debug.print("selectStatement :"+selectStatement);
        
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, loginName.trim());
        ResultSet rs = prepSelect.executeQuery();
        String userId = null;
        if (rs.next()) {
             userId = rs.getString(1);
        }
        rs.close();
        prepSelect.close();
        Debug.print("USer ID in DBHandling : "+userId);
        return userId;
    }
    
   
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
   
    public static final int getAge(String dob) {
         // Create a calendar object with the date of birth
            //Calendar dateOfBirth = new GregorianCalendar(1967,1,10);
           // String dob1 = "1/10/1969 12:00:00 AM";
            Debug.print("Enter DOB is : "+dob);
            String DOB [] = dob.split("-");
            String month = DOB[1];
            String day = DOB[2];
            String Year = DOB[0];
            day = day.substring(0,2);

            int mm = Integer.parseInt(month);
            int dy = Integer.parseInt(day);
            int Yr = Integer.parseInt(Year);
            Debug.print("Year in Integer format is : "+Yr+","+mm+","+dy);
            Calendar dateOfBirth = new GregorianCalendar(Yr,mm,dy);
            for (int i=0; i<DOB.length;i++)
                Debug.print(Year+" DOB : "+i+"  "+DOB[i]);

            //int mm = Integer.parseInt(dob.substring(0,1));
            //int dy = Integer.parseInt(dob.substring(1,4));
            //int Yr = Integer.parseInt(dob.substring(1,4));
            // Create a calendar object with today's date
            Calendar today = Calendar.getInstance();

            // Get age based on year
            int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

            // Add the tentative age to the date of birth to get this year's birthday
            dateOfBirth.add(Calendar.YEAR, age);

            // If this year's birthday has not happened yet, subtract one from age
            if (today.before(dateOfBirth)) {
                age--;
            }
            Debug.print("Age of the person till today : "+age);
            
            return age;
    }
    
        public Date calculateExpireDate(int interval,Date date) {
            Debug.print("kaverystatelessBean calculateExpireDate");
            makeConnection();
            Date nextRecurring = null;
                    
            try{
                String selectStatement = "SELECT DATEADD(month, " + interval + ", '" + DBHelper.toSQLDate(date) + "' ) AS TimeFrame";
                Debug.print("DBHelper calculateExpireDate:" + selectStatement);
                PreparedStatement prepSelect = con.prepareStatement(selectStatement);
                ResultSet rs = prepSelect.executeQuery();
                rs.next();
                nextRecurring = rs.getDate(1);
                rs.close();
                prepSelect.close();
                releaseConnection();
                Debug.print("DBHelper calculateExpireDate:" + nextRecurring);
                
            }
            catch (SQLException e) {
                releaseConnection();
                Debug.print("Could not find any from calculateExpireDate");
            }
            catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in calculateExpireDate:" + e.getMessage());
            }       
            
            return nextRecurring;
        }
     
/**
  * Name         :makeConnection
  * Description  :This method will Create a databacse connection
  * @ param      :
  * @return      :void
  * @throws      :EJBException
  */   
    
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/LMSMSSQLDS");

            con = ds.getConnection();
        } catch (Exception ex) {
            Debug.print("Unable to connect to database. " + ex.getMessage());
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
            //prepStmt.close();
            //rs.close();
            con.close();
        } catch (SQLException ex) {
           Debug.print("releaseConnection: " + ex.getMessage());
        }
    }     
    
}
