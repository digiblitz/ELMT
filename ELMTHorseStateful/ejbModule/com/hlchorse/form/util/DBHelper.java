/*
 * DBHelper.java
 *
 * Created on August 26, 2006, 3:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlchorse.form.util;
import javax.sql.*;
import java.sql.*;
/**
 *
 * @author harmohan
 */
public final class DBHelper {
    
    public static final String USEA_HORSE_MEMBERDETAILS = "tblHorseMemberDetails"; 
    public static final String USEA_PAYMENT = "tblUserPaymentDetails";
    public static final String USEA_HORSE_DESCRIPTION = "tblHorseDescription"; 
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_TYPEMASTER = "tblUserTypeMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails"; 
    public static final String USEA_MEMBERSHIP_TYPE = "tblMembershipTypeMaster";
    public static final String USEA_HORSESERVICETYPE = "tblHorseServiceTypeDetails";
    public static final String USEA_CONTACT_DETAILS = "tblContactDetails"; 
    public static final String USEA_HORSE_SERVICETYPEMASTER = "tblHorseServiceTypeMaster";
    public static final String USEA_CONTACT_TYPEMASTER = "tblContactTypeMaster";
    public static final String USEA_STATUS_MASTER = "tblMembershipStatusMaster";
    
    /** Creates a new instance of DBHelper */
    public DBHelper() {
    }
    
    public static final String getNextMemberId(Connection con) throws SQLException {
        Debug.print("DBHelper getNextAccountId");
        return getNextId(con,"tblMemberDetails");
    }
    
    
    private static final String getNextId(Connection con,String table) throws SQLException {
    Debug.print("DBHelper getNextId");
    String selectStatement = "SELECT member_id from " + table + " order by member_id ASC ";

    PreparedStatement prepSelect = con.prepareStatement(selectStatement);

    ResultSet rs = prepSelect.executeQuery();
    String memberId = null;
    while (rs.next()) {
    //rs.next();
         memberId = rs.getString(1);
    }

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
    
    public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
    public static final String getMemberId(Connection con, String h_name) throws SQLException {
        PreparedStatement prepStmt1=null;
        ResultSet rs= null;
        //String h_name = h_name;
        String name_id = "";
       System.out.println("Name of the variable h_name = "+h_name);
            String str = "select user_type_id from tblUserTypeMaster where user_type_name = ?";
             prepStmt1 = con.prepareStatement(str);
             prepStmt1.setString(1, h_name.trim());
             rs = prepStmt1.executeQuery();
             if (rs.next()) {
                 name_id = rs.getString(1);
             } 
        
        return name_id;
    }
    
}
