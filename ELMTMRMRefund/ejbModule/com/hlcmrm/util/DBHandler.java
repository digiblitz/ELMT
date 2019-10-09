/*
 * DBHandler.java
 *
 * Created on August 25, 2006, 11:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.util;
import com.hlcmrm.Exception.HLCPrimaryKeyException;
import javax.sql.*;
import java.sql.*;

/**
 *
 * @author harmohan
 */
public class DBHandler {
    
    public static final String USEA_MEMBER_REFUNDDETAILS = "tblMembershipRefundDetails"; 
    public static final String USEA_MEMBER_REFUNDTYPEDETAILS = "tblMembershipRefundTypeDetails";
    public static final String USEA_MEMBER_REFUNDTYPEMASTER = "tblMembershipRefundTypeMaster"; 
    public static final String USEA_MMS_USERMASTER = "tblUserMaster"; 
    public static final String USEA_MMS_MEMBERDETAIL = "tblMemberDetails"; 
    public static final String USEA_CONTACT_DETAILS = "tblContactDetails"; 
    
    
    /** Creates a new instance of DBHandler */
    public DBHandler() {
    }
    
    public static final String getMemberId(Connection con, String loginName)
    throws SQLException ,HLCPrimaryKeyException {
        Debug.print("DBHandler getMemberId");
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
    
    public static final String getRefundId(Connection con, String mailId) throws SQLException, HLCPrimaryKeyException {
        Debug.print("DBHandler getRefundId");
        String selectStatement = "SELECT refund_id FROM tblMembershipRefundDetails A, tblMemberDetails B, "+
        "tblUserMaster C WHERE A.member_id = B.member_id AND B.user_id = C.user_id AND  C.email_id = ?" ;
        
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        prepSelect.setString(1, mailId.trim());
        ResultSet rs = prepSelect.executeQuery();
        String refundId = null;
        if (rs.next()) {
             refundId = rs.getString(1);
        }
        Debug.print("Refund ID is : "+refundId);
        return refundId;
        
    }
    
     public static final java.sql.Date toSQLDate(java.util.Date inDate) {
        return new java.sql.Date(inDate.getTime());
    }
    
}