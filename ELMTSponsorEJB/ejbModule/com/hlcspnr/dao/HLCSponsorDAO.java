/*
 * SponsorDAO.java
 *
 * Created on October 26, 2006, 9:49 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcspnr.dao;
import com.hlcspnr.util.Debug;
import com.hlcspnr.util.HLCEJBAllJNDIs;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hlcspnr.util.DBHelper;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author suresh
 */
public class HLCSponsorDAO {
    private Connection conn;
    /** Creates a new instance of SponsorDAO */
    public HLCSponsorDAO() {
    }
    
    //============================================ status Change for particular Schedule  ===================================       
    public  boolean statusChange(String scheduleId, String status){
        Debug.print("SponsorDAO.statusChange():" + scheduleId);
        Debug.print("SponsorDAO.statusChange()  status: " + status);
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
   	try {
            String selectStatement = "  update " + DBHelper.USEA_SPNR_SCHE_DETAILS  + " set payment_status = ? where schedule_id = ? " ;
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,status);
            prepStmt.setString(2,scheduleId);
            int intResult  = prepStmt.executeUpdate();
            if(intResult != 0){
                result = true;
            }
            
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("SponsorDAO.statusChange():" + result);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in SponsorDAO.statusChange():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in SponsorDAO.statusChange():" + e.getMessage());
        }
        return result;
    }
    
    
     //=============================================Database Connection details=========================================    
    private void makeConnection() {
        Debug.print("MessageDAOImpl : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
            conn = ds.getConnection();
        }
        catch (SQLException sqlExp) {
            Debug.print("Unable to connect to database. " + sqlExp.getMessage());
        }
        catch (Exception exp) {
            Debug.print("Exception while calling makeConnection. " + exp.getMessage());
        }
    }
     
    private void releaseConnection(Connection con) {
        Debug.print("MessageDAOImpl: releaseConnection");
        try {
            if(!con.isClosed()){
                con.close();
            }
        } 
        catch (SQLException sqlExp) {
            Debug.print("Unable to release Connection. " + sqlExp.getMessage());
        }
        catch (Exception ex) {
            Debug.print("Exception while releasing Connection: " + ex.getMessage());
        }
    }
}
