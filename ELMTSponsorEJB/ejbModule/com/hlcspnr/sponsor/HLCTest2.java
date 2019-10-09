/*
 * Test2.java
 *
 * Created on August 24, 2006, 3:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcspnr.sponsor;
import java.sql.*;
import javax.sql.*;
import java.util.Date;
import javax.naming.*;
import java.util.*;
import javax.ejb.*;
import java.text.*;

/**
 *
 * @author suresh
 */
public class HLCTest2 {
    private static Connection con;
    
          
   /*  public void makeConnection() {
            Debug.print("KrishnaRajaSaragEntityBean Event: makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(EJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }
         // makeConnection
     public  void releaseConnection() {
            Debug.print("KrishnaRajaSaragEntityBean releaseConnection");

            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
         // releaseConnection

   */
    public static void main(String[] args) {
        HLCTest2 t = new HLCTest2();
        String a = "12/12/2007";
        Date today =new Date(a);
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        
        
        System.out.println(today);
        System.out.println(formatter.format(today));
        //System.out.println(today.getDate() +2);
        
        today.setDate(today.getDate() + 2);
       // System.out.println(formatter.format(today));
        Date date = new Date();
        
        
         Calendar cal = new GregorianCalendar();
            cal.setTime(date);

            System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        /*
         try{
            t.makeConnection();
            String insertStatement ="insert into tblDateTest values(?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, sponsorId);
            prepStmt.executeUpdate();
            prepStmt.close();
            t.releaseConnection();
        }
        catch(Exception e){
            System.out.println("Exception" + e.getMessage());
        }
        */
        
        
        
    }
    
}
