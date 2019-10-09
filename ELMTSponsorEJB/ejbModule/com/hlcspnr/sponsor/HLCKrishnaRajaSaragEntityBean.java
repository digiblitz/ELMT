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
/*  Program Name    : KrishnaRajaSaragEntityBean.java
 *  Created Date    : Aug 22, 2006 1:54:14 PM
 *  Author          : Suresh.K
 *  Version         : 1.20
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.hlcspnr.sponsor;

import com.hlcspnr.exception.HLCMissingPrimaryKeyException;
import com.hlcspnr.util.DBHelper;
import com.hlcspnr.util.Debug;
import com.hlcspnr.util.HLCEJBAllJNDIs;
import com.hlcspnr.util.HLCSponsorDetails;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.Date;
import javax.naming.*;
import java.util.*;

        

/**
 * This is the bean class for the KrishnaRajaSaragEntityBean enterprise bean.
 * Created Aug 22, 2006 1:54:14 PM
 * @author suresh
 */
public class HLCKrishnaRajaSaragEntityBean implements EntityBean, HLCKrishnaRajaSaragEntityLocalBusiness {
    private EntityContext context;
    private String sponsorId;
    private String userId;
    private String companyName;
    private String comments;
    private String planId;
    //private String statusId;
    private String salesPersonId;
    private Date contractStartDate;
    private Date contractEndDate;
    private String sponsorAmount;
    private String requestStatus;
    private String filePath;
    private Date addDate;
    private Connection con;
    
    public HLCSponsorDetails getSponsorDetails() {
      //  Debug.print("KrishnaRajaSaragEntityBean getSponsorDetails");
        Debug.print("KrishnaRajaSaragEntityBean getSponsorDetails");

        return new HLCSponsorDetails(sponsorId,userId,companyName,comments,
            planId,  salesPersonId , contractStartDate , contractEndDate, sponsorAmount , requestStatus, filePath, addDate);
    }
    
   public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public void setPlanId(String planId) {
        this.planId = planId;
    }

     public void setAddDate(Date addDate) {
           this.addDate = addDate;
     }

      public Date getAddDate() {
           return addDate;
     }

    public void setSalesPersonId(String salesPersonId) {
        this.salesPersonId = salesPersonId;
    }
    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }
    public void setcontractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public void setSponsorAmount(String sponsorAmount) {
        this.sponsorAmount = sponsorAmount;
    }
    public void setRequestStatus(String requestStatus) {
       this.requestStatus = requestStatus;
    }
    
      public void setFilePath(String filePath) {
            this.filePath = filePath;
    }


    public String ejbCreate(HLCSponsorDetails objSponsor) throws CreateException , HLCMissingPrimaryKeyException {
        Debug.print("KrishnaRajaSaragEntityBean ejbCreate");
        this.userId = objSponsor.getUserId();
        this.companyName = objSponsor.getCompanyName();
        this.comments = objSponsor.getComments();
        this.planId = objSponsor.getPlanId();
       // this.statusId = objSponsor.getStatusId();
        this.salesPersonId = objSponsor.getSalesPersonId();
        this.contractStartDate = objSponsor.getContractStartDate();
        this.contractEndDate = objSponsor.getcontractEndDate();
        this.sponsorAmount = objSponsor.getSponsorAmount();
        if(sponsorAmount==null){
             this.sponsorAmount = "0.00";
        }
        this.requestStatus = "Pending";
        this.filePath = filePath;
        this.addDate = new Date();
        try {
            insertRowSponsorDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + sponsorId);
        return  sponsorId;
    }
        
    public void ejbPostCreate(HLCSponsorDetails objSponsor){
        Debug.print("KrishnaRajaSaragEntityBean ejbPostCreate");
    }
   
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
     public void ejbActivate() {
        Debug.print("KrishnaRajaSaragEntityBean ejbActivate");
        sponsorId = (String)context.getPrimaryKey();
    }
    
     public void ejbPassivate() {
        Debug.print("KrishnaRajaSaragEntityBean ejbPassivate");
        sponsorId = "";   
   }
     
        public void ejbRemove() {
        Debug.print("KrishnaRajaSaragEntityBean ejbRemove");

        try {
            deleteRow(sponsorId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
    
   
    public void unsetEntityContext() {
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("KrishnaRajaSaragEntityBean ejbLoad");
        try {
            loadSponsorDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("KrishnaRajaSaragEntityBean ejbStore");

        try {
            storeSponsorDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    
    public String ejbFindByPrimaryKey(String sponsorId) throws FinderException {
        Debug.print("KrishnaRajaSaragEntityBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(sponsorId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return sponsorId;
        } else {
            throw new ObjectNotFoundException("Row for id " + sponsorId + " not found.");
        }
    }
     

 /*********************** Database Routines *************************/
/**
  * @Method Name    :makeConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :Null.
  */        
     private void makeConnection() {
            Debug.print("KrishnaRajaSaragEntityBean Event: makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }
         // makeConnection
/**
  * @Method Name    :releaseConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :Null.
  */          
     private void releaseConnection() {
            Debug.print("KrishnaRajaSaragEntityBean releaseConnection");

            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
         // releaseConnection

/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */      
    private boolean selectByPrimaryKey(String sponsorId) throws SQLException {
        Debug.print("KrishnaRajaSaragEntityBean selectByPrimaryKey");

        makeConnection();

        String selectStatement = "SELECT sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR  + " WHERE sponsor_id = ? order by add_date desc";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, sponsorId);

        ResultSet rs = prepStmt.executeQuery();
        boolean result = rs.next();
        prepStmt.close();
        releaseConnection();
        Debug.print("KrishnaRajaSaragEntityBean selectByPrimaryKey" + result);
        return result;
    }
    
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
       public Collection ejbFindByAll() throws FinderException {
        Debug.print("KrishnaRajaSaragEntityBean ejbFindAll");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " order by add_date desc";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();

            Vector sponsorList = new Vector();
            while (rs.next()){
                sponsorList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            return sponsorList;
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Request");
        }
    }
 
/**
  * @Method Name    :ejbFindBySponsorId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
     public Collection ejbFindBySponsorId(String sponsorId) throws FinderException {
        Debug.print("KrishnaRajaSaragEntityBean ejbFindBySponsorId");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where sponsor_id = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,sponsorId);
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
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindBySponsorId:" + e.getMessage());
        }        
    }
     
/**
  * @Method Name    :ejbFindBySponsor.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String userId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */     
      public Collection ejbFindBySponsor(String userId) throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean findBySponsor");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where user_id = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print(selectStatement);
            prepStmt.setString(1,userId);
            ResultSet rs = prepStmt.executeQuery();

            ArrayList sponsorList = new ArrayList();
            //sponsorList = null;
            while (rs.next()){
                sponsorList.add(rs.getString(1));
                Debug.print("KrishnaRajaSaragEntityBean User Id Find:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            return sponsorList;
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findBySponsor:" + e.getMessage());
        }      
      }
     
/**
  * @Method Name    :ejbFindByPlanId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */      
    public Collection ejbFindByPlanId(String planId) throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean ejbFindByPlanId");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where plan_id = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,planId);
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
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByPlanId:" + e.getMessage());
        }     
    }
    
/**
  * @Method Name    :ejbFindByCompanyName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String companyName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
    public Collection ejbFindByCompanyName(String companyName) throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean ejbFindByCompanyName");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where company_name = ? order by company_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,companyName);
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
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByCompanyName:" + e.getMessage());
        }     
    }
    
/**
  * @Method Name    :ejbFindBySalesPerson.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String salesPersonId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */    
     public Collection ejbFindBySalesPerson(String salesPersonId) throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean ejbFindBySponsorName");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where sales_person_id = ? order by company_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,salesPersonId);
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
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindBySponsorName:" + e.getMessage());
        }     
    }
     
/**
  * @Method Name    :ejbFindByDates.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Date fromDate, Date toDate.
  * @return         :Collection value.
  * @throws         :FinderException.
  */     
     public Collection ejbFindByDates(Date fromDate, Date toDate) throws FinderException{
     Debug.print("KrishnaRajaSaragEntityBean findByDates");
        makeConnection();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where  add_date between ? and ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            if(fromDate == null || toDate == null){
                return null;
            }
            else{
                prepStmt.setDate(1,DBHelper.toSQLDate(fromDate));
                prepStmt.setDate(2,DBHelper.toSQLDate(toDate));
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
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByDates:" + e.getMessage());
        }     
     }
     
/**
  * @Method Name    :ejbFindByRequestStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String requestStatus.
  * @return         :Collection value.
  * @throws         :FinderException.
  */        
    public Collection  ejbFindByRequestStatus(String requestStatus) throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean findByRequestStatus" + requestStatus);
        makeConnection();
        ArrayList reqstatus = new ArrayList();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where request_status = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,requestStatus);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                reqstatus.add(rs.getString(1));
                Debug.print("KrishnaRajaSaragEntityBean findByRequestStatus ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor request status");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByRequestStatus:" + e.getMessage());
        }        
         return reqstatus;
    }
    
/**
  * @Method Name    :ejbFindByAllRequestStatusSponsors.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String salesPersonId, String requestStatus.
  * @return         :Collection value.
  * @throws         :FinderException.
  */     
      public Collection  ejbFindByAllRequestStatusSponsors(String salesPersonId, String requestStatus) throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean ejbFindByAllRequestStatusSponsors" + requestStatus);
        makeConnection();
        ArrayList reqstatus = new ArrayList();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where sales_person_id = ? and request_status= ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,salesPersonId);
            prepStmt.setString(2,requestStatus);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                reqstatus.add(rs.getString(1));
                Debug.print("KrishnaRajaSaragEntityBean ejbFindByAllRequestStatusSponsors ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor request status");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByAllRequestStatusSponsors:" + e.getMessage());
        }        
         return reqstatus;
    }
    
/**
  * @Method Name    :ejbFindByPendingRequestForAdmin.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */      
    public Collection  ejbFindByPendingRequestForAdmin() throws FinderException{
        Debug.print("KrishnaRajaSaragEntityBean ejbFindByPendingRequestForAdmin");
        makeConnection();
        ArrayList reqstatus = new ArrayList();
   	try {
            String selectStatement = "select sponsor_id from " + DBHelper.USEA_SPNR_SPONSOR + " where sales_person_id  is null and request_status !='Rejected' order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                reqstatus.add(rs.getString(1));
                Debug.print("KrishnaRajaSaragEntityBean ejbFindByPendingRequestForAdmin ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor request status");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByPendingRequestForAdmin:" + e.getMessage());
        }        
         return reqstatus;
    }
    
    
/**
  * @Method Name    :insertRowSponsorDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException,MissingPrimaryKeyException.
  */
    private void insertRowSponsorDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("KrishnaRajaSaragEntityBean insertRowSponsorDetails");
     
        this.sponsorId = getNextId();
        Debug.print("KrishnaRajaSaragEntityBean After Getting Sponsor ID:" + sponsorId);
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_SPNR_SPONSOR  + " (sponsor_id, user_id, company_name, comments, plan_id,  " +
                " sales_person_id, contract_start_date, contract_end_date , sponsor_amount , request_status, file_path,  add_date ) " +
                 " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ? )";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, sponsorId);
            prepStmt.setString(2, userId);
            prepStmt.setString(3, companyName);
            prepStmt.setString(4, comments);
            prepStmt.setString(5, planId);
          //  prepStmt.setString(6, statusId);
            prepStmt.setString(6, salesPersonId);

            if(contractStartDate == null){
                prepStmt.setDate(7, null);
            }
            else{
                 prepStmt.setDate(7, DBHelper.toSQLDate(contractStartDate));
            }
            if(contractEndDate == null){
                prepStmt.setDate(8, null);
            }
            else{
                 prepStmt.setDate(8, DBHelper.toSQLDate(contractEndDate));
            }

            prepStmt.setFloat(9, Float.parseFloat(sponsorAmount));
            prepStmt.setString(10, requestStatus);
            prepStmt.setString(11, filePath);
            
            prepStmt.setDate(12, DBHelper.toSQLDate(addDate));
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowSponsorDetails:" + e.getMessage());
        }                
    }

/**
  * @Method Name    :loadSponsorDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void loadSponsorDetails() throws SQLException {
        Debug.print("KrishnaRajaSaragEntityBean loadSponsorDetails");
        sponsorId = (String)context.getPrimaryKey();

        Debug.print("KrishnaRajaSaragEntityBean loadEventDetails Primary Key:" + sponsorId );
        makeConnection();
        try{
            String selectStatement =
                "select user_id, company_name, comments, plan_id,  " +
                "sales_person_id, contract_start_date, contract_end_date ,sponsor_amount, request_status, file_path, add_date from " + DBHelper.USEA_SPNR_SPONSOR  + " where sponsor_id = ?  order by add_date desc";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1, sponsorId);

            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                this.userId = rs.getString(1);
                this.companyName = rs.getString(2);
                this.comments = rs.getString(3);
                this.planId = rs.getString(4);
                //this.statusId = rs.getString(5);
                this.salesPersonId = rs.getString(5);
                this.contractStartDate = rs.getDate(6);
                this.contractEndDate = rs.getDate(7);
                this.sponsorAmount = rs.getString(8);
                this.requestStatus = rs.getString(9);
                this.filePath = rs.getString(10);
                this.addDate = rs.getDate(11);
                prepStmt.close();
                releaseConnection();
            } 
            else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + sponsorId + " not found in database.");
            }
        }
       catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor Details");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadSponsorDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :storeSponsorDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void storeSponsorDetails() throws SQLException {
        Debug.print("KrishnaRajaSaragEntityBean storeSponsorDetails" + sponsorId);
      
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_SPNR_SPONSOR  + " set user_id = ? , company_name = ? , comments = ? , plan_id = ? , " +
                    " sales_person_id = ? ,contract_start_date = ? , contract_end_date = ? ," +
                    " sponsor_amount = ? , request_status = ?, file_path = ?, add_date = ?  where sponsor_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, userId);
            prepStmt.setString(2, companyName);
            prepStmt.setString(3, comments);
            prepStmt.setString(4, planId);
           // prepStmt.setString(5, statusId);
            prepStmt.setString(5, salesPersonId);
            if(contractStartDate == null){
                prepStmt.setDate(6, null);
            }
            else{
                 prepStmt.setDate(6, DBHelper.toSQLDate(contractStartDate));
            }
            if(contractEndDate == null){
                prepStmt.setDate(7, null);
            }
            else{
                 prepStmt.setDate(7, DBHelper.toSQLDate(contractEndDate));
            }
            prepStmt.setFloat(8, Float.parseFloat(sponsorAmount));
            prepStmt.setString(9, requestStatus);  
            
            prepStmt.setString(10, filePath);
            
            if(addDate == null){
                prepStmt.setDate(11, null);
            }
            else{
                 prepStmt.setDate(11, DBHelper.toSQLDate(addDate));
            }
           
            prepStmt.setString(12,sponsorId);
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor plan");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeSponsorDetails:" + e.getMessage());
        }
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void deleteRow(String sponsorId) throws SQLException {
         Debug.print("KrishnaRajaSaragEntityBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_SPNR_SPONSOR  + "  where sponsor_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, sponsorId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from sponsor plan");
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in deleteRow:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :void value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */    
     private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("KrishnaRajaSaragEntityBean getNextId");
        makeConnection();
       
        String selectStatement = "select newid() as sponsorId";
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        ResultSet rs = prepSelect.executeQuery();
        rs.next();
        String nextId = rs.getString(1);
        rs.close();
        prepSelect.close();
        releaseConnection();
        Debug.print("KrishnaRajaSaragEntityBean NEWID:" + nextId);
        return nextId;
    }
}
