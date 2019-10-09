/*
 * DBHelper.java
 *
 * Created on August 21, 2006, 10:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcreg.util;
import com.hlcreg.exception.HLCMissingPrimaryKeyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 *
 * @author harmohan
 */
public final class DBHelper {
    
    public static final String USEA_HORSE_MEMBERDETAILS = "tblHorseMemberDetails"; 
    public static final String USEA_PAYMENT = "tblUserPaymentDetails";
    public static final String USEA_HORSE_DESCRIPTION = "tblHorseDescription";
    public static final String USEA_HORSE_GRADE = "tblHorseGradeDetails";
    public static final String USEA_HORSE_RELATION = "tblHorseMemberRelationDetails";
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails"; 
    public static final String USEA_MEMBERSHIP_TYPE = "tblMembershipTypeMaster";
    public static final String USEA_HORSESERVICETYPE = "tblHorseServiceTypeDetails";
    public static final String USEA_MMS_CONTACT_DETAILS = "tblContactDetails";
    
    public static final String USEA_PUBLICATION_DETAILS ="tblMemberSubscription";
    public static final String USEA_DONATION_DETAILS= "tblDonationDetails";
   
    public static final String USEA_HORSE_COLOR= "tblColorMaster";
    public static final String USEA_HORSE_BREED= "tblBreedMaster";
      //*****************************************Action SATHEESH START MMMA_0010: Human/Non-Human relationships *************************************-->
    public static String USEA_USERTYPE_MASTER=  "tblUserTypeMaster";

    public static String USEA_HORSE_REALTION="tblHorseRelationshipTypeMaster";
      //--*****************************************Action SATHEESH End MMMA_0010: Human/Non-Human relationships *************************************-->
    
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
   /* public static final String getNextMemberId(Connection con) throws SQLException ,MissingPrimaryKeyException{
        Debug.print("DBHelper getNextMemberId");
        return getNextId(con,"tblMemberDetails");
    }*/
    
    
    public static final String getNextId(Connection con)
    throws SQLException ,HLCMissingPrimaryKeyException {
    Debug.print("DBHelper getNextId");
    String selectStatement = "SELECT member_id from " + USEA_MMS_MEMBERDETAIL + " order by member_id ASC ";

            PreparedStatement prepSelect = con.prepareStatement(selectStatement);

            ResultSet rs = prepSelect.executeQuery();
            String memberId = null;
            while (rs.next()) {
            //rs.next();
                 memberId = rs.getString(1);
            }
            if (memberId == null && memberId.trim().length() == 0)
                memberId = "0";
            
            System.out.println("member Id : "+memberId);
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
            System.out.println("member Id returned : "+memberId);
   
    return memberId;
    }
    
    public static final String getStatusId(Connection con)throws SQLException {
        String statusId = "";
        String str = "SELECT status_id FROM tblMembershipStatusMaster WHERE status_name = ?";
        PreparedStatement prepStmt1 = con.prepareStatement(str);
        prepStmt1.setString(1,"Pending");
        ResultSet rs = prepStmt1.executeQuery();
        if (rs.next()) {
             statusId = rs.getString(1);
         }
        Debug.print("StatusId:" + statusId);
        return statusId;
    }
    
    public static final boolean getMemberShipStatus(Connection con,String horseMemberId)throws SQLException {
        String expDate = null;
        String yr = null;
        boolean bol = false;
        Calendar cal = new GregorianCalendar();
        String str = "SELECT expiry_date FROM tblHorseMemberDetails WHERE horse_member_id = ?";
        PreparedStatement prepStmt1 = con.prepareStatement(str);
        prepStmt1.setString(1,horseMemberId);
        ResultSet rs = prepStmt1.executeQuery();
        if (rs.next()) {
             expDate = rs.getString(1);
         } 
        Debug.print(" Expiry Date : "+expDate);
        int year = cal.get(Calendar.YEAR);             // 2002
        if (expDate != null && (expDate.length()) > 0 ){
            yr = expDate.substring(0,4);
        }
        Debug.print(" Expiry Year : "+yr+"   Current Year : "+year);
        int xx = Integer.parseInt(yr);
        if (year > xx ){
            bol = false;
        }else {
            bol = true;
        }
        return bol;
    }
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
    public static final String getMemberId(Connection con, String h_name) throws SQLException {
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        String userTypeId = "";
       System.out.println("Name of the variable h_name = "+h_name);
            String str = "select user_type_id from tblUserTypeMaster where user_type_name = ?";
             prepStmt1 = con.prepareStatement(str);
             prepStmt1.setString(1, h_name.trim());
             rs = prepStmt1.executeQuery();
             if (rs.next()) {
                 userTypeId = rs.getString(1);
             } 
             Debug.print(" userTypeId : "+userTypeId);
         return userTypeId;
    }
    
    //getOwnerId(con,this.ownerEmail)
    public static final String getOwnerId(Connection con, String loginName) throws SQLException {
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        String userId = "";
       System.out.println("Name of the variable h_name = "+loginName);
            //String str = "select user_id from "+USEA_MMS_USERMASTER +" where email_id = ?";
            String str = "select user_id from "+USEA_MMS_USERMASTER +" where login_name = ?";
             prepStmt1 = con.prepareStatement(str);
             prepStmt1.setString(1, loginName.trim());
             rs = prepStmt1.executeQuery();
             if (rs.next()) {
                userId = rs.getString(1);
             } 
             Debug.print(" User Id inside DBHelper class "+userId);
         return userId;
    }
    
    public static final String getExpiryDate() throws ParseException {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat formatter;
   
        int year = cal.get(Calendar.YEAR);             // 2002
        Date date = new Date();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        
        Date dt1 =(Date) formatter.parse(s); 

        String curDate = String.valueOf(new Date());
        System.out.println(" Current Date dt1:  "+s);
        System.out.println(" Current Date :  "+curDate);
        String expDate = "2006-11-30";
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
    
}
