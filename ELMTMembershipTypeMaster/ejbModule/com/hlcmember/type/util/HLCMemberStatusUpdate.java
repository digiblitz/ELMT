/*
 * MemberStatusUpdate.java
 *
 * Created on September 15, 2006, 11:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmember.type.util;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
/**
 *
 * @author harmohan
 */
public class HLCMemberStatusUpdate {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private String statusId = null;
    
    /** Creates a new instance of MemberStatusUpdate */
    public HLCMemberStatusUpdate() {
    }
    
    /**
  * Name         :updateActiveStatus
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    public boolean updateMemberStatus(String activeStatus,String memberId) throws SQLException {
        Debug.print("Inside the updateActiveStatus");
        boolean result = false;
      try {
            makeConnection();
            String slelctStr = "SELECT status_id FROM "+DBHelper.USEA_STATUS_MASTER+" WHERE status_name = ?";
            PreparedStatement prepStmt = con.prepareStatement(slelctStr);
            prepStmt.setString(1, activeStatus);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the updateActiveStatus activeStatus : "+activeStatus);
            if (rs.next()) {
                this.statusId = rs.getString(1);
            }
           
            String updateStatement ="update " + DBHelper.USEA_MMS_MEMBERDETAIL  + " set  status_id = ? where member_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, statusId);
            prepStmt.setString(2, memberId);
            int rowCount = prepStmt.executeUpdate();
            if(rowCount>=1){
                result = true;
            }
            Debug.print("Member Status Sucessfully Updated. " + result+"  Row Count: "+rowCount);
            prepStmt.close();
            releaseConnection();
      }catch (SQLException e){
          e.printStackTrace();
      }
      return result;
    }
    
     public void updateHorseStatus(String activeStatus,String horseMemberId) throws SQLException {
        Debug.print("Inside the updateHorseStatus");
        
      try {
            makeConnection();
            String slelctStr = "SELECT status_id FROM "+DBHelper.USEA_STATUS_MASTER+" WHERE status_name = ?";
            PreparedStatement prepStmt = con.prepareStatement(slelctStr);
            prepStmt.setString(1, activeStatus);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the updateActiveStatus activeStatus : "+activeStatus);
            if (rs.next()) {
                this.statusId = rs.getString(1);
            }
           
            String updateStatement ="update " + DBHelper.USEA_MMS_HORSEMEMBERDETAILS  + " set  status_id = ? where horse_member_id = ?";
            prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, statusId);
            prepStmt.setString(2, horseMemberId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("updateHorseStatus  Sucessfully Updated. " + rowCount);
            prepStmt.close();
            releaseConnection();
      }catch (SQLException e){
          e.printStackTrace();
      }
      
    }    

     public ArrayList getAccTxnTypDetails() throws SQLException {
        Debug.print("Inside the MemberStatusUpdate.getAccTxnTypDetails :");
        ArrayList txnDetails = new ArrayList();
        
      try {
            makeConnection();
            String slelctStr = "SELECT transaction_type_id, transaction_name FROM "+DBHelper.USEA_ACC_TXN_TYPE_DETAILS;
            Debug.print("Query Log :"+slelctStr);
            
            PreparedStatement prepStmt = con.prepareStatement(slelctStr);
            
            rs = prepStmt.executeQuery();
            
            while(rs.next()) {
                
                String txnId = rs.getString(1);
                String txnName = rs.getString(2);
                
                String txnDet[] = {txnId,txnName};
                txnDetails.add(txnDet);
            }
           
            prepStmt.close();
            
      }catch (SQLException e){
          Debug.print("SQLException in MemberStatusUpdate.getAccTxnTypDetails() :");
          e.printStackTrace();
      }
        catch (Exception e){
          Debug.print("general Exception in MemberStatusUpdate.getAccTxnTypDetails() :");          
          e.printStackTrace();
      }
        finally{
            releaseConnection();
        }
        
      return txnDetails;
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
