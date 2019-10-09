/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.hlcpublication.DAO;
/*
 * PublicationDAO.java
 *
 * Created on November 21, 2006, 2:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import com.hlcmrm.util.DBHelper;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCPublicationVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
/**
 *
 * @author punitha
 */
public class HLCPublicationDAO {
    private Connection con;
    private static final String DATASOURCE = "java:/ELMTMSSQLDS";
    /** Creates a new instance of PublicationDAO */
    public HLCPublicationDAO() {
    }
    
    //=============================================Insert Donation Details =========================================      
    public boolean insertPublicationName(String publicationName) {
            Debug.print("PublicationDAO.insertPublicationName():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            String meetingId = "";
            makeConnection();
            try {
                 String insertStatement = "insert into " + DBHelper.USEA_PUBLICATION_DETAILS  + 
                        " (publication_name)" +
                        " values (?) ";

                prepStmt = con.prepareStatement(insertStatement);
                Debug.print("PublicationDAO insertPublicationName  :" + meetingId);
                
                if(publicationName!=null || publicationName.trim().length()!=0){
                    prepStmt.setString(1, publicationName);
                    
                    int cnt = prepStmt.executeUpdate();
                    
                    Debug.print("Record Inserted succefully in insertPublicationName Details: " + cnt);
                    if(cnt>=1){
                        result = true;
                    }
                    
                    Debug.print("PublicationDAO insertPublicationName Status :" + result);
                }
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in PublicationDAO.insertPublicationName():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in PublicationDAO.insertPublicationName():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Update Request Status for Annual Details =========================================      
    public boolean updatePublication(String publicationId, String publicationName) {
            Debug.print("PublicationDAO.PublicationName():");
            boolean result = false;
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String updateStatement = "update " + DBHelper.USEA_PUBLICATION_DETAILS  + " set publication_name = ? "+
                         " where publication_id = ?";

                prepStmt = con.prepareStatement(updateStatement);

                prepStmt.setString(1, publicationName);
                prepStmt.setString(2, publicationId);
                                
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Updated succefully in updatePublicationName : " + cnt);
                if(cnt>=1){
                    result = true;
                }
                Debug.print("PublicationDAO updatePublicationName :" + result);
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in PublicationDAO.updatePublicationName():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in PublicationDAO.updatePublicationName():" + e.getMessage());
        }
        return result;
    }    
    
    
          //=============================================select a All Country price Details =========================================      
    public ArrayList selectAllPublicationDetails(){
            Debug.print("PublicationDAO.selectAllPublicationDetails():");
            ArrayList publicationList = new ArrayList();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select publication_id, publication_name from " + DBHelper.USEA_PUBLICATION_DETAILS;
                           
                Debug.print("Result Query:"+ selectStatement);        
                prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while(rs.next()){
                    
                    HLCPublicationVO objDonVO = new HLCPublicationVO();
                    String publicationId = rs.getString(1);
                    String publicationName = rs.getString(2);
                   
                    Debug.print("publicationId:"+ publicationId);       
                    Debug.print("publicationName:"+ publicationName);       
                    objDonVO.setPublicationId(publicationId);
                    objDonVO.setPublicationName(publicationName);
                     
                    publicationList.add(objDonVO);
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in PublicationDAO.selectAllPublicationDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in PublicationDAO.selectAllPublicationDetails():" + e.getMessage());
        }
        return publicationList;
    }
    
    
     //=============================================select a All Country price Details =========================================      
    public HLCPublicationVO selectPublicationDetailsById(String publicationId){
            Debug.print("PublicationDAO.selectPublicationDetailsById() publicationId:" + publicationId);
            HLCPublicationVO objDonVO = new HLCPublicationVO();
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                 String selectStatement = "select publication_id, publication_name from " +
                         DBHelper.USEA_PUBLICATION_DETAILS +
                         " where publication_id= ?";
                        
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,publicationId);
                ResultSet rs = prepStmt.executeQuery();
                if(rs.next()){
                    String publicationIdVal = rs.getString(1);
                    String publicationName = rs.getString(2);
                
                    objDonVO.setPublicationId(publicationIdVal);
                    objDonVO.setPublicationName(publicationName);
                    
                }
                rs.close();
                prepStmt.close();
                releaseConnection(con);
            }
        catch(SQLException sql){
            releaseConnection(con);
            Debug.print("SQL Exception in PublicationDAO.selectPublicationDetailsById():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in PublicationDAO.selectPublicationDetailsById():" + e.getMessage());
        }
        return objDonVO;
    }
    
     //=============================================Country Price Name details=========================================      
    public boolean isPublicationNameExist(String publicationName) {
        Debug.print("PublicationDAO.isDonationTypeNameExist():" + publicationName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select publication_id from " + DBHelper.USEA_PUBLICATION_DETAILS + " where publication_name = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,publicationName);
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
            Debug.print("Could not find any from isPublicationNameExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isPublicationNameExist:" + e.getMessage());
        }
        Debug.print("isPublicationNameExist():" + result);
        return result;
    }
    
    //=============================================Privilege Name Checking details=========================================      
    public boolean isPublicationNameEditExist(String publicationId, String publicationName) {
        Debug.print("PubicationDAO.isPublicationNameEditExist():" + publicationId);
        boolean result = true;
        makeConnection();
   	try {

            String selectStatement = "select publication_id from   " + DBHelper.USEA_PUBLICATION_DETAILS +
                    " where publication_name = ?  and publication_id != ?" ;
            
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,publicationName);
            prepStmt.setString(2,publicationId);
            
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
            Debug.print("Could not find any from isPublicationTypeNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(con);
            Debug.print("General Exception  in isPublicationTypeNameEditExist:" + e.getMessage());
        }
        Debug.print("isPublicationTypeNameEditExist():" + result);
        return result;
    }
    
      //============================================= Database Connectivity=========================================  
       private Connection makeConnection() {
            Debug.print("PublicationDAO : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(DATASOURCE);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database in PublicationDAO. " + ex.getMessage());
            }
            return con; 
        }

    //=============================================Connection Release=========================================  
         private void releaseConnection(Connection con) {
            Debug.print("PublicationDAO releaseConnection");
            try {
               if(!con.isClosed())
                   con.close();
            } catch (Exception e) {

            }
         }
}
 
 
   
    
   
    
    
    
     
 
