/*
 * InstructorDetailDAO.java
 *
 * Created on September 21, 2006, 10:16 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmeeting.util;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import javax.naming.*;
/**
 *
 * @author harmohan
 */
public class HLCInstructorDetailDAO {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private PreparedStatement prepStmt1 = null;
    private ResultSet rs1 = null;
    
    private String  icpMeetingId;
    private String  assesmentLevel;
    private String  assesmentDate;
    private String  noOfDays;
    private String  useaAreaId;
    private String  areaName;
    private String  location;
    private String  instructorId;
    private String  hostMemberId;
    private String  shippingTypeId;
    private String  assessor;
    private String  facilities;
    private String  landOwnerName;
    private String landOwnerBusinessName;
    private String  landOwnerAddress;
    private String  landOwnerCity;
    private String  landOwnerState;
    private String  landOwnerCountry;
    private String  landOwnerZip;
    private String  landOwnerPhone;
    private String landOwnerFax;
    private String landOwnerEmail;
    private String  addDate;
    private String  approvedBy;
    private String  approvedDate;
    private String  postingType;
    private String  requestStatus;
    private String comments;
    private String email;
    
    /** Creates a new instance of InstructorDetailDAO */
    public HLCInstructorDetailDAO() {
    }
    
/**
  * Name         :storeICPRequestStatus
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    
    public boolean updateInstructorDetail(HLCInstructorDetails objInst) throws SQLException {
        Debug.print("InstructorDetailDAO updateInstructorDetail");
        //java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
      //area_id,badge_info,total_amount
    try {
        makeConnection();
        String updateStatement = 
        "UPDATE " + DBHelper.USEA_INSTRUCTOR_DETAILS  + " SET  assesment_level = ?, assesment_date = ?,usea_area_id = ?,"+
          "location = ?, instructor_id = ?, host_member_id = ?, assessor = ?, facilities = ?, land_owner_name = ?,"+
          " land_owner_business_name =?,land_owner_address = ?, land_owner_city = ?,land_owner_state = ?, land_owner_zip = ?,"+
          " land_owner_phone = ?, land_owner_fax= ?, land_owner_country = ?,no_of_days = ?,"+
          " request_status = ? WHERE icp_meeting_id = ?";
        
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        
        prepStmt.setString(1, objInst.getAssesmentLevel());
        Debug.print("assesmentLevel :  "+objInst.getAssesmentLevel());
        prepStmt.setDate(2, java.sql.Date.valueOf(objInst.getAssesmentDate())); 
//        prepStmt.setDate(2,objInst.getAssesmentDate());
        Debug.print("assesment_date :  "+objInst.getAssesmentDate());
        Debug.print("Date thru "+java.sql.Date.valueOf(objInst.getAssesmentDate()));        
        prepStmt.setString(3, objInst.getUseaAreaId());
        Debug.print("useaAreaId :  "+objInst.getUseaAreaId());
        prepStmt.setString(4, objInst.getLocation());
        Debug.print("location :  "+objInst.getLocation());
        prepStmt.setString(5, objInst.getInstructorId());
        Debug.print("Instructor ID :  "+objInst.getInstructorId());        
        prepStmt.setString(6, objInst.getHostMemberId());
        Debug.print("Host Member ID :  "+objInst.getHostMemberId());        
        prepStmt.setString(7, objInst.getAssessor());
        Debug.print("Assessor Details :  "+objInst.getAssessor());        
        prepStmt.setString(8, objInst.getFacilities());
        Debug.print("Facilities :  "+objInst.getFacilities());        
        prepStmt.setString(9, objInst.getLandOwnerName());
        Debug.print("Land Owner Name :  "+objInst.getLandOwnerName());        
        prepStmt.setString(10, objInst.getLandOwnerBusinessName());
        Debug.print("Land Business Name :  "+objInst.getLandOwnerBusinessName());        
        prepStmt.setString(11, objInst.getLandOwnerAddress());
        Debug.print("Land Address :  "+objInst.getLandOwnerAddress());        
        prepStmt.setString(12, objInst.getLandOwnerCity());
        Debug.print("Land City :  "+objInst.getLandOwnerCity());        
        prepStmt.setString(13, objInst.getLandOwnerState());
        Debug.print("Land State :  "+objInst.getLandOwnerState());        
        prepStmt.setString(14, objInst.getLandOwnerZip());
        Debug.print("Land Zip :  "+objInst.getLandOwnerZip());        
        prepStmt.setString(15, objInst.getLandOwnerPhone());
        Debug.print("Land Phone :  "+objInst.getLandOwnerPhone());        
        prepStmt.setString(16, objInst.getLandOwnerFax());
        Debug.print("Land Fax :  "+objInst.getLandOwnerFax());        
//prepStmt.setString(17, objInst.getLandOwnerEmail());
//Debug.print("Land Email :  "+objInst.getLandOwnerEmail());        
        prepStmt.setString(17, objInst.getLandOwnerCountry()); 
        Debug.print("Land Owner Country :  "+objInst.getLandOwnerCountry());        
        // Debug.print("approvedDate :  "+approvedDate);
        prepStmt.setInt(18, Integer.parseInt(objInst.getNoOfDays()));
        Debug.print("No of days :  "+objInst.getNoOfDays());        
        prepStmt.setString(19, objInst.getRequestStatus());
        Debug.print("Requested Status :  "+objInst.getRequestStatus());                
        prepStmt.setString(20, objInst.getIcpMeetingId());
        Debug.print("Meeting Id :  "+objInst.getIcpMeetingId());        
//java.sql.Date dt = java.sql.Date.valueOf(approvedDate);
// Debug.print("approvedDate : "+approvedDate);
//java.sql.Date.valueOf(approvedDate));        
/*shipping_type_id = ?,
prepStmt.setString(7, objInst.getShippingTypeId());
Debug.print("Shipping Type ID :  "+objInst.getShippingTypeId());        
// prepStmt.setString(17, objInst.getPostingType());
// Debug.print("Posting Type :  "+objInst.getPostingType());     */           
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
        }catch (Exception e){
            Debug.print("Error While Updating Instructor Details : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return true;
    } 
    
    
    
    
    
/**
  * Name         :makeConnection
  * Description  :This method will create the databacse connection
  * @ param      :
  * @return      :void
  * @throws      :EJBException
  */      
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

