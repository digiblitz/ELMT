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
import com.hlccommon.util.HLCActivityUserVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;

/**
 * This is the bean class for the ActivityUserBean enterprise bean.
 * Created Sep 6, 2006 10:30:06 AM
 * @author suresh
 */
public class HLCActivityUserBean implements EntityBean, HLCActivityUserLocalBusiness {
   private EntityContext context;
    private Connection con;
    private String releaseId;
    private String activityMeetingId;
    private String userId;
    private String noOfHorses;
    private String eventLevelId;
    private boolean membershipStatus;
    private String memberId;
    private Date addDate;
    private boolean activeStatus;
    private String requestStatus;
    private HLCActivityUserVO objActivityUser;
    
    
     public HLCActivityUserVO getActivityUserDetails(){
        Debug.print("ActivityUserBean getActivityUserrDetails");        
        return new HLCActivityUserVO(releaseId, activityMeetingId, userId,
	         noOfHorses, eventLevelId, membershipStatus, memberId, addDate,
	         activeStatus,  requestStatus);
    }
 
    public void setReleaseId(String releaseId){
         this.releaseId = releaseId;
    }
    
    public void setActivityMeetingId(String activityMeetingId){
         this.activityMeetingId = activityMeetingId;
    }
    
    public void setUserId(String userId){
         this.userId = userId;
    }
    
     public void setNoOfHorses(String noOfHorses){
         this.noOfHorses = noOfHorses;
    }
    
    public void setEventLevelId(String eventLevelId){
         this.eventLevelId = eventLevelId;
    }
    
    public void setMembershipStatus(boolean membershipStatus){
         this.membershipStatus = membershipStatus;
    }
     
    public void setMemberId(String memberId){
         this.memberId = memberId;
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
    
     
    public String getReleaseId(){
        return releaseId;
    }
    
    public String getActivityMeetingId(){
        return activityMeetingId;
    }
    
    public String getUserId(){
        return userId;
    }
    
     public String getNoOfHorses(){
        return noOfHorses;
    }
    
    public String getEventLevelId(){
         return eventLevelId;
    }
    
    public boolean isMembershipStatus(){
        return membershipStatus;
    }
     
    public String getMemberId(){
         return memberId;
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
        
       
  public String ejbCreate(HLCActivityUserVO objActivityUser) throws CreateException{
        Debug.print("ActivityUserBean ejbCreate"); 
        this.activityMeetingId =objActivityUser.getActivityMeetingId();
        this.userId = objActivityUser.getUserId();
        this.noOfHorses = objActivityUser.getNoOfHorses();
        this.eventLevelId = objActivityUser.getEventLevelId();
        this.membershipStatus = objActivityUser.isMembershipStatus();
        this.memberId = objActivityUser.getMemberId();
        this.addDate = new Date();
        this.activeStatus = objActivityUser.isActiveStatus();
        this.requestStatus = objActivityUser.getRequestStatus();
        
        try {
            insertRowActivityUserDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + releaseId);
        return  releaseId;
    }

    public void ejbPostCreate(HLCActivityUserVO objActivityUser) throws CreateException{
        Debug.print("ActivityUserBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("ActivityUserBean ejbActivate");
        releaseId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("ActivityUserBean ejbPassivate");
        releaseId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("ActivityUserBean ejbRemove");

        try {
            deleteRow(releaseId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("ActivityUserBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("ActivityUserBean ejbLoad");
        try {
            loadActivityUserDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("ActivityUserBean ejbStore");

        try {
            storeActivityUserDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String releaseId) throws FinderException {
        Debug.print("ActivityUserBean ejbFindByPrimaryKey");
        boolean result;
        try {
            result = selectByPrimaryKey(releaseId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
            return releaseId;
        } else {
            throw new ObjectNotFoundException("Row for id " + releaseId + " not found.");
        }
    }     
    /*********************** Database Routines *************************/

  private boolean selectByPrimaryKey(String releaseId) throws SQLException {
        Debug.print("ActivityUserBean selectByPrimaryKey:" + releaseId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT release_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER  + " WHERE release_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, releaseId);
            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("ActivityUserBean selectByPrimaryKey" + result);
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
        Debug.print("ActivityUserBean ejbFindAll");
        Vector issueList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select release_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                issueList.addElement(rs.getString(1));
                Debug.print("Publication ID In find all:" + rs.getString(1));
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

    public Collection ejbFindByActivityUserRequestStatus(String activityMeetingId, String requestStatus) throws FinderException {
        Debug.print("ActivityUserBean ejbFindByActivityUserRequestStatus:" + requestStatus);
        ArrayList actUserList = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select release_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER + " where activity_meeting_id = ? and request_status = ? "  ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("Query:" + selectStatement);
            prepStmt.setString(1,activityMeetingId);
            prepStmt.setString(2,requestStatus);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                actUserList.add(rs.getString(1));
                Debug.print("ActivityUserBean In ejbFindByActivityUserRequestStatus:" + rs.getString(1));
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

    
       public Collection ejbFindByUserId(String userId) throws FinderException {
        Debug.print("ActivityUserBean ejbFindByUserId:" + requestStatus);
        ArrayList actUserList = new ArrayList();
        makeConnection();
        try {
            String selectStatement = "select release_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER + " where user_id = ? "  ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("Query:" + selectStatement);
            prepStmt.setString(1,userId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                actUserList.add(rs.getString(1));
                Debug.print("ActivityUserBean In ejbFindByUserId:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        }
        
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByUserId:" + sql.getMessage());
        }

        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByUserId:" + e.getMessage());
        }
        return actUserList;
    }

   
    public Collection ejbFindByActivityMeetingId(String activityMeetingId) throws FinderException{
        Debug.print("ActivityUserBean ejbFindByActivityMeetingId:" + activityMeetingId);
        makeConnection();
        ArrayList pubList = new ArrayList();
   	try {
            String selectStatement = "select release_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER + " where activity_meeting_id = ? order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,activityMeetingId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                pubList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("ActivityUserBean in ejbFindByActivityMeetingId:" + pubList);
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
    
    
     public Collection findByRecordsFromToActivityDate(String activityMeetingId, Date fromDate, Date toDate) throws FinderException{
     Debug.print("KrishnaRajaSaragEntityBean findByRecordsFromToActivityDate");
        makeConnection();
   	try {
            String selectStatement = "select release_id from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER + " where activity_meeting_id= ? and add_date between ? and ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            if(fromDate == null || toDate == null){
                return null;
            }
            else{
                prepStmt.setLong(1,Long.parseLong(activityMeetingId));
                prepStmt.setDate(2,DBHelper.toSQLDate(fromDate));
                prepStmt.setDate(3,DBHelper.toSQLDate(toDate));
                ResultSet rs = prepStmt.executeQuery();

                Vector sponsorList = new Vector();
                sponsorList = null;
                while (rs.next()){
                    sponsorList.addElement(rs.getString(1));
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                return sponsorList;
            }
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from findByRecordsFromToActivityDate Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByRecordsFromToActivityDate:" + e.getMessage());
        }     
     }
    
    
   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("ActivityUserBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as releaseId";
            Debug.print("ActivityUserBean getNextId:" + selectStatement);
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
   
    private void insertRowActivityUserDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("ActivityUserBean insertRowActivityUserDetails");
        
        this.releaseId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER  + "(release_id, activity_meeting_id, user_id," +
                    " no_of_horses, event_level_id, membership_status, member_id, add_date, active_status, request_status) " +
                 " values ( ? , ? , ?, ? , ? , ? , ?, ? ,? , ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, releaseId);
            prepStmt.setLong(2, Long.parseLong(activityMeetingId));
            prepStmt.setString(3, userId);
            
            if(noOfHorses!=null && !(noOfHorses.equals(""))){
                prepStmt.setInt(4, 0);
            } else{
                prepStmt.setInt(4, Integer.parseInt(noOfHorses));
            }

            prepStmt.setString(5, eventLevelId);
            prepStmt.setBoolean(6, membershipStatus);
            prepStmt.setString(7, memberId);
            prepStmt.setDate(8, DBHelper.toSQLDate(addDate));
            prepStmt.setBoolean(9, false);
            prepStmt.setString(10, requestStatus);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowActivityUserDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowActivityUserDetails:" + e.getMessage());
        }        
    }

    private void loadActivityUserDetails() throws SQLException {
        Debug.print("ActivityUserBean loadActivityUserDetails");
        releaseId = (String)context.getPrimaryKey();

        Debug.print("ActivityUserBean loadActivityUserDetails Primary Key:" + releaseId );
        makeConnection();
        try{
            String selectStatement =
                "select  activity_meeting_id, user_id," +
                    " no_of_horses, event_level_id, membership_status, member_id, add_date, " +
                    " active_status, request_status from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER  + " where release_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, releaseId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.activityMeetingId = rs.getString(1);
                this.userId = rs.getString(2);
                this.noOfHorses = rs.getString(3);
                this.eventLevelId = rs.getString(4);
                this.membershipStatus = rs.getBoolean(5);
                this.memberId = rs.getString(6);
                this.addDate = rs.getDate(7);
                this.activeStatus = rs.getBoolean(8);
                this.requestStatus = rs.getString(9);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + releaseId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadActivityUserDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadActivityUserDetails:" + e.getMessage());
        }
    }
    
    private void storeActivityUserDetails() throws SQLException {
        Debug.print("ActivityUserBean storeActivityUserDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER  + " set activity_meeting_id = ? , user_id = ? , no_of_horses = ?, " + 
                    " event_level_id = ? , membership_status = ? , member_id = ?, add_date = ?, active_status = ?, request_status=?   where release_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setLong(1, Long.parseLong(activityMeetingId));
            prepStmt.setString(2, userId);
            
            if(noOfHorses==null || (noOfHorses.equals(""))){
                prepStmt.setInt(3, 0);
            } else{
                prepStmt.setInt(3, Integer.parseInt(noOfHorses));
            }

            prepStmt.setString(4, eventLevelId);
            prepStmt.setBoolean(5, membershipStatus);
            prepStmt.setString(6, memberId);
            prepStmt.setDate(7, DBHelper.toSQLDate(addDate));
            prepStmt.setBoolean(8, false);
            prepStmt.setString(9, requestStatus);
            prepStmt.setString(10, releaseId);
            
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeActivityUserDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeActivityUserDetails:" + e.getMessage());
        }        
    }
    
    private void deleteRow(String releaseId) throws SQLException {
        Debug.print("ActivityUserBean deleteRow");
        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_EVT_EDU_ACTIVITY_USER  + "  where release_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, releaseId);
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
            Debug.print("ActivityUserBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
            Debug.print("ActivityUserBean releaseConnection");
            try {
               if(!con.isClosed()){
                    con.close();
                }
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
}
