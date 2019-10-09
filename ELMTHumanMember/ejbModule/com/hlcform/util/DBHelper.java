/*
 * DBHelper.java
 *
 * Created on August 24, 2006, 12:32 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcform.util;

import com.hlcform.exception.HLCPrimaryKeyException;
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
    
    public static final String USEA_CONTACT_DETAILS = "tblContactDetails"; 
    public static final String USEA_PAYMENT = "tblUserPaymentDetails";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails"; 
    public static final String USEA_MEMBERSHIP_TYPE = "tblMembershipTypeMaster";
    public static final String USEA_CONTACT_TYPEMASTER = "tblContactTypeMaster";
    public static final String USEA_STATUS_MASTER = "tblMembershipStatusMaster";
    public static final String USEA_NONUSEA_ORGMASTER = "tblNonUSEAOrgMaster";
    public static final String USEA_COUNTRY_MAIL_PRICE_MASTER = "tblCountryMailPriceMaster";
    public static final String USEA_PAYMENT_DETAILS = "tblUserPaymentDetails";
    public static final String USEA_HORSE_SERVICE_TYPE = "tblHorseServiceTypeMaster";
    public static final String USEA_MEMBERSHIP_HISTORY_DETAILS = "tblMembershipHistoryDetails";
    public static final String USEA_MMS_AREA_MEMBER_HISTORY_DETAILS = "tblAreaMemberHistoryDetails";
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
    
    
    public static final String getUserIdBasedOnMemberId(Connection con, String memberId) throws SQLException {
        Debug.print("DBHelper getUserId");
        return getUsrId(con, memberId); //"tblUserMaster", emailId);
    }
        
    private static final String getUsrId(Connection con, String memberId)
    throws SQLException {
        Debug.print("DBHelper getUsrId");
        String selectStatement = "SELECT user_id from " + USEA_MMS_MEMBERDETAIL + " WHERE member_id = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, memberId);
        ResultSet rs = prepSelect.executeQuery();
        String userId = null;
        if (rs.next()) {
             userId = rs.getString(1);
        }
        Debug.print("USer ID is : "+userId);
        return userId;
    }
    
    
    
    public static final String getStatusId(Connection con) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getStatusId");
        String result = getstatId(con,"tblMembershipStatusMaster");
        Debug.print("DBHelper getStatusId StatusId:" + result);
        return result;
    }

    private static final String getstatId(Connection con,String table)
    throws SQLException ,HLCPrimaryKeyException {
        Debug.print("DBHelper getstatId : Table Name:" + table);
        String selectStatement = "SELECT status_id from " + table + " WHERE status_name = ? ";
        Debug.print("DBHelper getstatId : Query:" + selectStatement);
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1,"Pending");
        ResultSet rs = prepSelect.executeQuery();
        String statusId = null;
        if (rs.next()) {
             statusId = rs.getString(1);
             Debug.print("Status Id in side DB Loop : "+statusId);
        }
        rs.close();
        prepSelect.close();
        Debug.print("Status Id in DBHandling : "+statusId);
        return statusId;
    }
    
    public static final String getUserId(Connection con, String loginName) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getUserId loginName:" + loginName);
        String result = getId(con,"tblUserMaster", loginName);
        Debug.print("DBHelper getUserId userId:" + result);
        return result;
    }
    
    
    private static final String getId(Connection con,String table, String loginName)
    throws SQLException ,HLCPrimaryKeyException {
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
    
    
    /*=====================Get contact Type Id Based On Contact Type Name ========================================*/
    public static final String getContacttypeId(Connection con, String contactTypeName) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getContacttypeId");
        return getcontactId(con,"tblContactTypeMaster", contactTypeName);
    }
    
    
    private static final String getcontactId(Connection con,String table, String contactTypeName)
    throws SQLException ,HLCPrimaryKeyException {
        Debug.print("DBHelper getcontactId");
        String selectStatement = "SELECT contact_type_id from " + table + " WHERE contact_type_name = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, contactTypeName);
        ResultSet rs = prepSelect.executeQuery();
        String contactTypeId = null;
        if (rs.next()) {
             contactTypeId = rs.getString(1);
        }
        rs.close();
        prepSelect.close();
        Debug.print("contactTypeId in DBHandling: "+contactTypeId+"    Contact Type Name : "+contactTypeName);
        return contactTypeId;
    }
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
     public static final String getNextMemberId(Connection con) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getNextAccountId");
        return getNextId(con,"tblMemberDetails");
    }
    
    
    private static final String getNextId(Connection con,String table)
    throws SQLException ,HLCPrimaryKeyException {
    Debug.print("DBHelper getNextId");
    String selectStatement = "SELECT max(cast(member_id as int)) from " + table;// + " order by member_id ASC ";

    PreparedStatement prepSelect = con.prepareStatement(selectStatement);

    ResultSet rs = prepSelect.executeQuery();
    String memberId = null;
    while (rs.next()) {
    //rs.next();
         memberId = rs.getString(1);
    }
    if (memberId == null)
        memberId = "0";
    
    long nextId = Long.valueOf(memberId).longValue();
   
    if(nextId==0){
        nextId = 10000;
    }
    else{
        nextId = nextId+1;
    }
    rs.close();
    prepSelect.close();

    memberId = Long.toString(nextId);
   
    return memberId;
    }
    
    //getNextUserCode
    
    
    public static final String getNextUserCode(Connection con) throws SQLException ,HLCPrimaryKeyException{
        Debug.print("DBHelper getNextAccountId");
        return getNextCode(con);
    }
    
    private static final String getNextCode(Connection con)
    throws SQLException ,HLCPrimaryKeyException {
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
    
    
    public static final String getMemberId(Connection con, String loginName)
    throws SQLException ,HLCPrimaryKeyException {
        Debug.print("DBHelper getId");
        String selectStatement = "SELECT member_id from tblMemberDetails A, tblUserMaster B "+
        " WHERE A.user_id = B.user_id and B.login_name = ? ";

        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, loginName.trim());
        ResultSet rs = prepSelect.executeQuery();
        String memberId = null;
        if (rs.next()) {
             memberId = rs.getString(1);
        }
        Debug.print("USer ID is : "+memberId);
        return memberId;
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
        
         public java.sql.Date getExpiredDateForMember(String memberId) {
            Debug.print("kaverystatelessBean DBHelper getExpiredDateForMember");
            makeConnection();
            Date nextRecurring = null;
            java.sql.Date subsExpireDate = null;
            try{
                 String selectStatement = "SELECT expiry_date from tblMemberDetails WHERE member_id = ? ";
                Debug.print("DBHelper getExpiredDateForMember:" + selectStatement);
                PreparedStatement prepSelect = con.prepareStatement(selectStatement);
                prepSelect.setString(1,memberId);
                ResultSet rs = prepSelect.executeQuery();
                if(rs.next()){
                    nextRecurring = rs.getDate(1);
                }
                rs.close();
                prepSelect.close();
                releaseConnection();
                Debug.print("DBHelper getExpiredDateForMember:" + nextRecurring);
                
            }
            catch (SQLException e) {
                releaseConnection();
                Debug.print("Could not find any from getExpiredDateForMember");
            }
            catch(Exception e){
                releaseConnection();
                Debug.print("General Exception  in getExpiredDateForMember:" + e.getMessage());
            }
            
            if(nextRecurring!=null){
                subsExpireDate = toSQLDate(calculateExpireDate(12,nextRecurring));
            }
            Debug.print("getExpiredDateForMember ExpiredDate:" + subsExpireDate);
            return subsExpireDate;
        }
        
     public static final String getExpiryDate() throws ParseException {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat formatter;

         // Get the components of the date               
        int year = cal.get(Calendar.YEAR);             // 2002
        Date date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        
        Date dt1 =(Date) formatter.parse(s); 

        String curDate = String.valueOf(new Date());
        System.out.println(" Current Date dt1:  "+s);
        System.out.println(" Current Date :  "+curDate);
        String expDate = "2007-11-30";
        //String expDate = formatter.format(date);
        String yr = expDate.substring(0,4);
        int xx = Integer.parseInt(yr);
        System.out.println("Previous year is : "+yr);
        System.out.println("Current year is : "+year);
                
        if (year > xx){
            expDate = year+expDate.substring(4,10);
        }
        System.out.println("Expiry Date is : "+expDate);
        Date dt2 =(Date) formatter.parse(expDate); 
        System.out.println("Expiry Date is dt2: "+dt2);
        
        if (dt1.after(dt2)){
            System.out.println(" dt1 : "+dt1+" AND dt2: "+dt2+"   dt1.after(dt2) : "+dt1.after(dt2));
            year +=1 ;
            expDate = year+expDate.substring(4,10);
            System.out.println("Latest Expiry Date is : "+expDate);
        }
     return expDate;
    }
     
     
     public Date getExpDateForCurrentYear() throws Exception{
        String expDate = "2007-11-30";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(expDate);
        System.out.println("Date:" + formatter.format(date));
        java.util.Date today = new java.util.Date();
        date.setYear(today.getYear());
        System.out.println("Date:" + formatter.format(date));
        System.out.println("Date:" + date);
        return date;
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
            DataSource ds = (DataSource) ic.lookup("java:/ELMTMSSQLDS");

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
