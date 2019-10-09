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
package com.hlcevent.activity;

import com.hlccommon.exception.HLCMissingPrimaryKeyException;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import com.hlccommon.util.HLCOraganizerRecapVO;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;

/**
 * This is the bean class for the OrganizerRecapBean enterprise bean.
 * Created Sep 20, 2006 6:34:42 PM
 * @author suresh
 */
public class HLCOrganizerRecapBean implements EntityBean, HLCOrganizerRecapLocalBusiness {
    private EntityContext context;
    private Connection con;
    private String recapId;
    private String activityMeetingId;
    private boolean activityDateChangeStatus;
    private String activityOrganizerId;
    private int noOfRiders;
    private int noOfInstructors;
    private int noOfCurrentMembers;
    private int noOfNewMembers;
    private int noOfRenewingMembers;
    private int noOfFullMembers;
    private int noOfJuniorMembers;
    private int noOfNonCompetingMembers;
    private Date activityReportDate;
    private float totalAmount;
    private Date closeDate;
    private String comments;
    private boolean publishComments;
    private String suggestions;
    private Date addDate;
    private boolean activeStatus;
    private String requestStatus;
    private HLCOraganizerRecapVO objOrgRecap;
    
    
     public HLCOraganizerRecapVO getOraganizerRecapDetails() {
        Debug.print("OrganizerRecapBean getOraganizerRecapDetails");        
        return new HLCOraganizerRecapVO(recapId, activityMeetingId, activityDateChangeStatus, activityOrganizerId, 
            noOfRiders, noOfInstructors, noOfCurrentMembers, noOfNewMembers, noOfRenewingMembers, noOfFullMembers, 
            noOfJuniorMembers, noOfNonCompetingMembers, activityReportDate, totalAmount, closeDate,
            comments, publishComments, suggestions, addDate, activeStatus, requestStatus );
    }
  //Setter
    public void setRecapId(String recapId){
        this.recapId = recapId;
    }
    public void setActivityMeetingId(String activityMeetingId){
         this.activityMeetingId = activityMeetingId;
    }
    public void setActivityDateChangeStatus(boolean activityDateChangeStatus){
         this.activityDateChangeStatus = activityDateChangeStatus;
    }
    public void setActivityOrganizerId(String activityOrganizerId){
         this.activityOrganizerId = activityOrganizerId;
    }
    public void setNoOfRiders(int noOfRiders){
         this.noOfRiders = noOfRiders;
    }
    public void setNoOfInstructors(int noOfInstructors){
         this.noOfInstructors = noOfInstructors;
    }
    public void setNoOfCurrentMembers(int noOfCurrentMembers){
         this.noOfCurrentMembers = noOfCurrentMembers;
    }
    public void setNoOfNewMembers(int noOfNewMembers){
         this.noOfNewMembers = noOfNewMembers;
    }
    public void setNoOfRenewingMembers(int noOfRenewingMembers){
         this.noOfRenewingMembers = noOfRenewingMembers;
    }
    public void setNoOfFullMembers(int noOfFullMembers){
         this.noOfFullMembers = noOfFullMembers;
    }
    public void setNoOfJuniorMembers(int noOfJuniorMembers){
         this.noOfJuniorMembers = noOfJuniorMembers;
    }
    public void setNoOfNonCompetingMembers(int noOfNonCompetingMembers){
         this.noOfNonCompetingMembers = noOfNonCompetingMembers;
    }
    public void setActivityReportDate(Date activityReportDate){
        this.activityReportDate = activityReportDate;
    }
    public void setTotalAmount(float totalAmount){
        this.totalAmount = totalAmount;
    }
    public void setCloseDate(Date closeDate){
        this.closeDate = closeDate;
    }
    public void setComments(String comments){
        this.comments = comments;
    }
    public void setPublishComments(boolean publishComments){
        this.publishComments = publishComments;
    }
    public void setSuggestions(String suggestions){
        this.suggestions = suggestions;
    } 
    public void setAddDate(Date addDate){
        this.addDate = addDate;
    }
    public void setActiveStatus(boolean activeStatus){
        this.activeStatus = activeStatus;
    }
    public void setRequestStatus(String requestStatus){
        this.requestStatus = requestStatus;
    }
    
    //Getter
     
    public String getRecapId(){
        return recapId;
    }
    
    public String getActivityMeetingId(){
       return activityMeetingId;
    }
    
    public boolean isActivityDateChangeStatus(){
        return activityDateChangeStatus;
    }
    
    public String getActivityOrganizerId(){
        return activityOrganizerId;
    }
    
    public int getNoOfRiders(){
        return noOfRiders;
    }
    
    public int getNoOfInstructors(){
        return noOfInstructors;
    }
    
    public int getNoOfCurrentMembers(){
      return noOfCurrentMembers;
    }
    
    public int getNoOfNewMembers(){
        return noOfNewMembers;
    }
    
    public int getNoOfRenewingMembers(){
        return noOfRenewingMembers;
    }
    
    public int getNoOfFullMembers(){
        return noOfFullMembers;
    }
    
    public int getNoOfJuniorMembers(){
        return noOfJuniorMembers;
    }
    
    public int getNoOfNonCompetingMembers(){
        return noOfNonCompetingMembers;
    }
    
    public Date getActivityReportDate(){
        return activityReportDate;
    }
    
    public float getTotalAmount(){
        return totalAmount;
    }
    
    public Date getCloseDate(){
        return closeDate;
    }
    
    public String getComments(){
        return comments;
    }
    
    public boolean isPublishComments(){
        return publishComments;
    }
    
    public String getSuggestions(){
        return suggestions;
    } 
    public Date getAddDate(){
        return addDate;
    }
    public boolean isActiveStatus(){
        return activeStatus;
    }
    public String getRequestStatus(){
        return requestStatus;
    }
       
  public String ejbCreate(HLCOraganizerRecapVO objOrgRecap) throws CreateException{
        Debug.print("OrganizerRecapBean ejbCreate"); 
        this.recapId = objOrgRecap.getRecapId();
        this.activityMeetingId = objOrgRecap.getActivityMeetingId();
        this.activityDateChangeStatus = objOrgRecap.isActivityDateChangeStatus();
        this.activityOrganizerId = objOrgRecap.getActivityOrganizerId();
        this.noOfRiders = objOrgRecap.getNoOfRiders();
        this.noOfInstructors = objOrgRecap.getNoOfInstructors();
        this.noOfCurrentMembers = objOrgRecap.getNoOfCurrentMembers();
        this.noOfNewMembers = objOrgRecap.getNoOfNewMembers();
        this.noOfRenewingMembers = objOrgRecap.getNoOfRenewingMembers();
        this.noOfFullMembers = objOrgRecap.getNoOfFullMembers();
        this.noOfJuniorMembers = objOrgRecap.getNoOfJuniorMembers();
        this.noOfNonCompetingMembers = objOrgRecap.getNoOfNonCompetingMembers();
        this.activityReportDate = objOrgRecap.getActivityReportDate();
        this.totalAmount = objOrgRecap.getTotalAmount();
        this.closeDate = objOrgRecap.getCloseDate();
        this.comments = objOrgRecap.getComments();
        this.publishComments = objOrgRecap.isPublishComments();
        this.suggestions = objOrgRecap.getSuggestions();
        this.addDate = objOrgRecap.getAddDate();
        this.activeStatus = objOrgRecap.isActiveStatus();
        this.requestStatus = objOrgRecap.getRequestStatus();
        
        try {
            insertRowOrganizerRecapDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + recapId);
        return  recapId;
    }

    public void ejbPostCreate(HLCOraganizerRecapVO objOrgRecap) throws CreateException{
        Debug.print("OrganizerRecapBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("OrganizerRecapBean ejbActivate");
        recapId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("OrganizerRecapBean ejbPassivate");
        recapId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("OrganizerRecapBean ejbRemove");

        try {
            deleteRow(recapId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("OrganizerRecapBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("OrganizerRecapBean ejbLoad");
        try {
            loadOrganizerRecapDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("OrganizerRecapBean ejbStore");

        try {
            storeOrganizerRecapDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String recapId) throws FinderException {
        Debug.print("OrganizerRecapBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(recapId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return recapId;
        } else {
            throw new ObjectNotFoundException("Row for id " + recapId + " not found.");
        }
    }     
    /*********************** Database Routines *************************/

  private boolean selectByPrimaryKey(String recapId) throws SQLException {
        Debug.print("OrganizerRecapBean selectByPrimaryKey:" + recapId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT recap_id from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL  + " WHERE recap_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, recapId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("OrganizerRecapBean selectByPrimaryKey" + result);
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectByPrimaryKey:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectByPrimaryKey:" + e.getMessage());
        }
         return result;
    }
  
  
   public Collection ejbFindByAll() throws FinderException {
        Debug.print("OrganizerRecapBean ejbFindAll");
        Vector issueList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select recap_id from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL + " order by add_date desc";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                issueList.addElement(rs.getString(1));
                Debug.print("Recap  ID In find all:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByAll:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByAll:" + e.getMessage());
        }
        return issueList;
}
   
   
    


    public Collection ejbFindByActivityUserRequestStatus(String requestStatus) throws FinderException {
        Debug.print("OrganizerRecapBean ejbFindByActivityUserRequestStatus:" + requestStatus);
        ArrayList actUserList = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select recap_id from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL + " where request_status = ? order by add_date desc "  ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("Query:" + selectStatement);
            prepStmt.setString(1,requestStatus);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                actUserList.add(rs.getString(1));
                Debug.print("OrganizerRecapBean In ejbFindByActivityUserRequestStatus:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        }
        
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByActivityUserRequestStatus:" + sql.getMessage());
        }

        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByActivityUserRequestStatus:" + e.getMessage());
        }
        return actUserList;
    }

      
    public Collection ejbFindByActivityMeetingId(String activityMeetingId) throws FinderException{
        Debug.print("OrganizerRecapBean ejbFindByActivityMeetingId:" + activityMeetingId);
        makeConnection();
        ArrayList pubList = new ArrayList();
   	try {
            String selectStatement = "select recap_id from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL + " where activity_meeting_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,activityMeetingId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                pubList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("OrganizerRecapBean in ejbFindByActivityMeetingId:" + pubList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByActivityMeetingId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByActivityMeetingId:" + e.getMessage());
        }
        return pubList;
    }
    
    
    public ArrayList ejbFindByOrganizerId(String activityOrganizerId) throws FinderException {
        Debug.print("OrganizerRecapBean ejbFindByOrganizerId:" + activityOrganizerId);
        makeConnection();
        ArrayList pubList = new ArrayList();
   	try {
            String selectStatement = "select recap_id from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL + " where activity_organizer_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,activityOrganizerId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                pubList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("OrganizerRecapBean in ejbFindByOrganizerId:" + pubList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByOrganizerId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByOrganizerId:" + e.getMessage());
        }
        return pubList;
    }
   
   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("OrganizerRecapBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as recapId";
            Debug.print("OrganizerRecapBean getNextId:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            nextId = rs.getString(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in getNextId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getNextId:" + e.getMessage());
        }
        return nextId;
    }
   
    private void insertRowOrganizerRecapDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("OrganizerRecapBean insertRowOrganizerRecapDetails");
        
        this.recapId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL  + "(recap_id, activity_meeting_id, activity_date_change_status," +
                    " activity_organizer_id, no_of_riders, no_of_instructors, no_of_current_members, no_of_new_members, no_of_renewing_members," +
                    " no_of_full_members, no_of_junior_members, no_of_non_competing_members, activity_report_date, total_amount, close_date," +
                    " comments, publish_comments, suggestions, add_date, active_status, request_status) " +
                 " values ( ? , ? , ?, ? , ? , ? , ?, ? ,? , ?, ? , ? , ? , ? , ?, ? , ? , ? , ? , ?, ? )";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, recapId);
            prepStmt.setLong(2, Long.parseLong(activityMeetingId));
            
            prepStmt.setBoolean(3, activityDateChangeStatus);
            prepStmt.setString(4, activityOrganizerId);
            prepStmt.setInt(5,noOfRiders);
            prepStmt.setInt(6,noOfInstructors);
            prepStmt.setInt(7,noOfCurrentMembers);
            prepStmt.setInt(8,noOfNewMembers);
            prepStmt.setInt(9,noOfRenewingMembers);
            prepStmt.setInt(10,noOfFullMembers);
            prepStmt.setInt(11,noOfJuniorMembers);
            prepStmt.setInt(12,noOfNonCompetingMembers);
            
            
            if(activityReportDate==null){
                 prepStmt.setDate(13, null);
            } else{
                prepStmt.setDate(13, DBHelper.toSQLDate(activityReportDate));
            }
        
            prepStmt.setFloat(14,totalAmount);
            
            if(closeDate==null){
                 prepStmt.setDate(15, null);
            } else{
                prepStmt.setDate(15, DBHelper.toSQLDate(closeDate));
            }
            
            prepStmt.setString(16,comments);
            prepStmt.setBoolean(17,publishComments);
            prepStmt.setString(18,suggestions);
        
            prepStmt.setDate(19, DBHelper.toSQLDate(new Date()));
            prepStmt.setBoolean(20, false);
            prepStmt.setString(21, requestStatus);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowOrganizerRecapDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowOrganizerRecapDetails:" + e.getMessage());
        }        
    }

    private void loadOrganizerRecapDetails() throws SQLException {
        Debug.print("OrganizerRecapBean loadOrganizerRecapDetails");
        recapId = (String)context.getPrimaryKey();

        Debug.print("OrganizerRecapBean loadOrganizerRecapDetails Primary Key:" + recapId );
        makeConnection();
        try{
            String selectStatement =
                "select  activity_meeting_id, activity_date_change_status," +
                    " activity_organizer_id, no_of_riders, no_of_instructors, no_of_current_members, no_of_new_members, no_of_renewing_members," +
                    " no_of_full_members, no_of_junior_members, no_of_non_competing_members, activity_report_date, total_amount, close_date," +
                    " comments, publish_comments, suggestions, add_date, active_status, request_status from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL  + " where recap_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, recapId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.activityMeetingId = rs.getString(1);
                this.activityDateChangeStatus = rs.getBoolean(2);
                this.activityOrganizerId = rs.getString(3);
                this.noOfRiders = rs.getInt(4);
                this.noOfInstructors = rs.getInt(5);
                this.noOfCurrentMembers = rs.getInt(6);
                this.noOfNewMembers = rs.getInt(7);
                this.noOfRenewingMembers = rs.getInt(8);
                this.noOfFullMembers = rs.getInt(9);
                this.noOfJuniorMembers = rs.getInt(10);
                this.noOfNonCompetingMembers = rs.getInt(11);
                this.activityReportDate = rs.getDate(12);
                this.totalAmount = rs.getFloat(13);
                this.closeDate = rs.getDate(14);
                this.comments = rs.getString(15);
                this.publishComments = rs.getBoolean(16);
                this.suggestions = rs.getString(17);
                this.addDate = rs.getDate(18);
                this.activeStatus = rs.getBoolean(19);
                this.requestStatus = rs.getString(20);
                
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + recapId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadOrganizerRecapDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadOrganizerRecapDetails:" + e.getMessage());
        }
    }
    
    private void storeOrganizerRecapDetails() throws SQLException {
        Debug.print("OrganizerRecapBean storeOrganizerRecapDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL  + " set activity_meeting_id = ?, activity_date_change_status = ?," +
                    " activity_organizer_id = ?, no_of_riders = ?, no_of_instructors = ?, no_of_current_members = ?, no_of_new_members = ?, no_of_renewing_members = ?," +
                    " no_of_full_members = ?, no_of_junior_members = ?, no_of_non_competing_members = ?, activity_report_date = ?, total_amount = ?, close_date = ?," +
                    " comments = ?, publish_comments = ?, suggestions = ?, active_status = ?, request_status = ?  where recap_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setLong(1, Long.parseLong(activityMeetingId));
            
            prepStmt.setBoolean(2, activityDateChangeStatus);
            prepStmt.setString(3, activityOrganizerId);
            prepStmt.setInt(4,noOfRiders);
            prepStmt.setInt(5,noOfInstructors);
            prepStmt.setInt(6,noOfCurrentMembers);
            prepStmt.setInt(7,noOfNewMembers);
            prepStmt.setInt(8,noOfRenewingMembers);
            prepStmt.setInt(9,noOfFullMembers);
            prepStmt.setInt(10,noOfJuniorMembers);
            prepStmt.setInt(11,noOfNonCompetingMembers);
            if(activityReportDate==null){
                 prepStmt.setDate(12, null);
            } else{
                prepStmt.setDate(12, DBHelper.toSQLDate(activityReportDate));
            }
        
            prepStmt.setFloat(13,totalAmount);
            
            if(closeDate==null){
                 prepStmt.setDate(14, null);
            } else{
                prepStmt.setDate(14, DBHelper.toSQLDate(closeDate));
            }
            
            prepStmt.setString(15,comments);
            prepStmt.setBoolean(16,publishComments);
            prepStmt.setString(17,suggestions);

            prepStmt.setBoolean(18, false);
            prepStmt.setString(19, requestStatus);
            
            prepStmt.setString(20, recapId);
            
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeOrganizerRecapDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeOrganizerRecapDetails:" + e.getMessage());
        }        
    }
    
    private void deleteRow(String recapId) throws SQLException {
        Debug.print("OrganizerRecapBean deleteRow");
        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_EVT_EDU_ACT_RECAP_DETAIL  + "  where recap_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, recapId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in deleteRow:" + e.getMessage());
        }        
    }
        
        
     private void makeConnection() {
            Debug.print("OrganizerRecapBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
            Debug.print("OrganizerRecapBean releaseConnection");
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
    }
}
