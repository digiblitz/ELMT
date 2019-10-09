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
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the PublicationBean enterprise bean.
 * Created Sep 5, 2006 11:04:29 AM
 * @author suresh
 */
public class HLCPublicationBean implements EntityBean, HLCPublicationLocalBusiness {
    private EntityContext context;
    private Connection con;
    private String publicationId;
    private String activityMeetingId;
    private String publicationEmail;
    private String mailingFormat;
    private String mailingBy;
    private String mailingSortBy;
    private String noOfCopies;
    private String mailListValues;
    
    
    public void setPublicationId(String publicationId){
         this.publicationId = publicationId;
    }
    
    public void setActivityMeetingId(String activityMeetingId){
         this.activityMeetingId = activityMeetingId;
    }
    
    public void setPublicationEmail(String publicationEmail){
         this.publicationEmail = publicationEmail;
    }
    
     public void setMailingFormat(String mailingFormat){
         this.mailingFormat = mailingFormat;
    }
    
    public void setMailingBy(String mailingBy){
         this.mailingBy = mailingBy;
    }
    
    public void setMailingSortBy(String mailingSortBy){
         this.mailingSortBy = mailingSortBy;
    }
     
    public void setNoOfCopies(String noOfCopies){
         this.noOfCopies = noOfCopies;
    }
    
    
    public String getPublicationId(){
        return publicationId;
    }
    
    public String getActivityMeetingId(){
         return activityMeetingId;
    }
    
    public String getPublicationEmail(){
        return publicationEmail;
    }
    
     public String getMailingFormat(){
         return mailingFormat;
    }
    
    public String getMailingBy(){
        return mailingBy;
    }
    
    public String getMailingSortBy(){
         return mailingSortBy;
    }
     
    public String getNoOfCopies(){
         return noOfCopies;
    }
    
       
  public String ejbCreate(String activityMeetingId , String publicationEmail,  String mailingFormat, 
          String mailingBy, String mailingSortBy, String noOfCopies, String mailListValues) throws CreateException{
        Debug.print("PublicationBean ejbCreate");
        this.activityMeetingId = activityMeetingId;
        this.publicationEmail =publicationEmail;
        this.mailingFormat = mailingFormat;
        this.mailingBy =mailingBy;
        this.mailingSortBy = mailingSortBy;
        this.noOfCopies =noOfCopies;
        this.mailListValues = mailListValues;
        try {
            insertRowPublication();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + publicationId);
        return  publicationId;
    }

    public void ejbPostCreate(String activityMeetingId , String publicationEmail,  String mailingFormat, 
          String mailingBy, String mailingSortBy, String noOfCopies, String mailListValues) throws CreateException{
        Debug.print("PublicationBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("PublicationBean ejbActivate");
        publicationId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("PublicationBean ejbPassivate");
        publicationId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("PublicationBean ejbRemove");

        try {
            deleteRow(publicationId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("PublicationBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("PublicationBean ejbLoad");
        try {
            loadPublicationDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("PublicationBean ejbStore");

        try {
            storePublicationDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String publicationId) throws FinderException {
        Debug.print("PublicationBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(publicationId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return publicationId;
        } else {
            throw new ObjectNotFoundException("Row for id " + publicationId + " not found.");
        }
    }     
    /*********************** Database Routines *************************/

  private boolean selectByPrimaryKey(String publicationId) throws SQLException {
        Debug.print("PublicationBean selectByPrimaryKey:" + publicationId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT publication_id from " + DBHelper.USEA_EVT_EDU_PUBLICATION  + " WHERE publication_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, publicationId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("PublicationBean selectByPrimaryKey" + result);
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
        Debug.print("PublicationBean ejbFindAll");
        Vector issueList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select publication_id from " + DBHelper.USEA_EVT_EDU_PUBLICATION ;
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

    public Collection ejbFindByActivityMeetingId(String activityMeetingId) throws FinderException{
        Debug.print("PublicationBean ejbFindByActivityMeetingId:" + activityMeetingId);
        makeConnection();
        ArrayList pubList = new ArrayList();
   	try {
            String selectStatement = "select publication_id from " + DBHelper.USEA_EVT_EDU_PUBLICATION + " where activity_meeting_id = ?  ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,activityMeetingId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                pubList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("PublicationBean in ejbFindByActivityMeetingId:" + pubList);
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
    
    
   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("PublicationBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as publicationId";
            Debug.print("PublicationBean getNextId:" + selectStatement);
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
   
    private void insertRowPublication() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("PublicationBean insertRowPublication");
        
        this.publicationId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_EVT_EDU_PUBLICATION  + "(publication_id, activity_meeting_id, publication_email," +
                    " mailing_format, mailing_by, mailing_sort_by, no_of_copies, mail_list_values) " +
                 " values ( ? , ? , ?, ? , ? , ? , ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, publicationId);
            prepStmt.setLong(2, Long.parseLong(activityMeetingId));
            prepStmt.setString(3, publicationEmail);
            prepStmt.setString(4, mailingFormat);
            prepStmt.setString(5, mailingBy);
            prepStmt.setString(6, mailingSortBy);
            if(noOfCopies==null || (noOfCopies.equals(""))){
                prepStmt.setInt(7, 0);
            } else{
                prepStmt.setInt(7, Integer.parseInt(noOfCopies));
            }
            
            prepStmt.setString(8, mailListValues);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowPublication:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPublication:" + e.getMessage());
        }        
    }

    private void loadPublicationDetails() throws SQLException {
        Debug.print("PublicationBean loadPublicationDetails");
        publicationId = (String)context.getPrimaryKey();

        Debug.print("PublicationBean loadPublicationDetails Primary Key:" + publicationId );
        makeConnection();
        try{
            String selectStatement =
                "select  activity_meeting_id, publication_email," +
                    " mailing_format, mailing_by, mailing_sort_by, no_of_copies, mail_list_values from " + DBHelper.USEA_EVT_EDU_PUBLICATION  + " where publication_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, publicationId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.activityMeetingId = rs.getString(1);
                this.publicationEmail = rs.getString(2);
                this.mailingFormat = rs.getString(3);
                this.mailingBy = rs.getString(4);
                this.mailingSortBy = rs.getString(5);
                this.noOfCopies = rs.getString(6);
                this.mailListValues = rs.getString(7);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + publicationId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadPublicationDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadPublicationDetails:" + e.getMessage());
        }
    }
    
    private void storePublicationDetails() throws SQLException {
        Debug.print("PublicationBean storePublicationDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_EVT_EDU_PUBLICATION  + " set activity_meeting_id = ? , publication_email = ? , mailing_format = ?, " + 
                    " mailing_by = ? , mailing_sort_by = ? , no_of_copies = ?, mail_list_values = ?  where publication_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);


            prepStmt.setLong(1, Long.parseLong(activityMeetingId));
            prepStmt.setString(2, publicationEmail);
            prepStmt.setString(3, mailingFormat);
            prepStmt.setString(4, mailingBy);
            prepStmt.setString(5, mailingSortBy);
            if(noOfCopies==null || (noOfCopies.equals(""))){
                prepStmt.setInt(6, 0);
            } else{
                prepStmt.setInt(6, Integer.parseInt(noOfCopies));
            }
            prepStmt.setString(7, mailListValues);
            prepStmt.setString(8, publicationId);
            
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storePublicationDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storePublicationDetails:" + e.getMessage());
        }        
    }
    
    
    private void deleteRow(String publicationId) throws SQLException {
        Debug.print("PublicationBean deleteRow");
        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_EVT_EDU_PUBLICATION  + "  where publication_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, publicationId);
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
            Debug.print("PublicationBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
            Debug.print("PublicationBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }

    public void setMailListValues(String mailListValues) {
        this.mailListValues = mailListValues;
    }

    public String getMailListValues() {
        return mailListValues;
    }
}