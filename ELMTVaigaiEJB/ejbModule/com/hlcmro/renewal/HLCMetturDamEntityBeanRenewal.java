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
/*  Program Name    : MetturDamEntityBeanRenewal.java
 *  Created Date    : Aug 18, 2006 7:08:37 PM
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
package com.hlcmro.renewal;

import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import com.hlcmro.util.HLCPaymentDetails;
import com.hlcmro.util.HLCRenewalOrganizerDetails;
import javax.ejb.*;
import java.sql.*;
import javax.sql.DataSource;
import java.util.*;
import javax.naming.*;

/**
 * This is the bean class for the MetturDamEntityBeanRenewalRenewal enterprise bean.
 * Created Aug 18, 2006 7:08:37 PM
 * 
 * @author suresh
 */
public class HLCMetturDamEntityBeanRenewal implements EntityBean, HLCMetturDamEntityLocalBusinessRenewal {
    private EntityContext context;
    private Connection con;
    private String renewalId;
    private int eventId;
    private String organizerId;
    private String competitionName;
    private String competitionLocation;
    private String competitionCity;
    private String competitionState;
    private String competitionCountry;
    private String competitionZip;
    private java.util.Date competitionDate;
    private String championshipArea;    
    private String comManagementName;
    private String comManagementAddress;
    private String comManagementCity;
    private String comManagementState;
    private String comManagementCountry;
    private String comManagementZip;
    private String comManagementPhone;
    private String comManagementFax;
    private String managerUsefMemberId;
    private String managerUseaMemberId;
    private String managerName;
    private String secretaryUsefMemberId;
    private String secretaryName;
    private String userId;

    public String getComManagementEmail() {
        return comManagementEmail;
    }

    public void setComManagementEmail(String comManagementEmail) {
        this.comManagementEmail = comManagementEmail;
    }
    private String comManagementEmail;

    public String getComManagementAddress2() {
        return comManagementAddress2;
    }

    public void setComManagementAddress2(String comManagementAddress2) {
        this.comManagementAddress2 = comManagementAddress2;
    }
    private String comManagementAddress2;
        
    private HLCRenewalOrganizerDetails objRenewal;
    private HLCPaymentDetails objPayDet;
    
    private HLCPaymentLocalHome objPayHome;
    private HLCPaymentLocal objPayRemote;
    private InitialContext ic;
    
    
    public HLCRenewalOrganizerDetails getRenewalDetails(){
        Debug.print("MetturDamEntityBeanRenewal getRenewalDetails");        
        return new HLCRenewalOrganizerDetails(renewalId, eventId,organizerId,competitionName,
              competitionDate, comManagementName,  comManagementAddress, comManagementAddress2,  comManagementCity, comManagementState,
             comManagementCountry, comManagementZip, comManagementPhone, comManagementFax,comManagementEmail, managerUsefMemberId, managerUseaMemberId,
             managerName,  secretaryUsefMemberId,  secretaryName);
    }
    
    public HLCPaymentDetails getPaymentDetails(){
        Debug.print("MetturDamEntityBeanRenewal getPaymentDetails");        
        return  objPayDet;
    }
    
     public void setPaymentDetails(HLCPaymentDetails objPayDet){
        Debug.print("MetturDamEntityBeanRenewal setPaymentDetails");        
        this.objPayDet = objPayDet;
    }
   
    public void setRenewalId(String renewalId) {
        Debug.print("MetturDamEntityBeanRenewal setRenewalId");
        this.renewalId = renewalId;
    }
    public void setEventId(int eventId) {
        Debug.print("MetturDamEntityBeanRenewal setEventId");
        this.eventId = eventId;
    }
    public void setOrganizerId(String organizerId) {
        Debug.print("MetturDamEntityBeanRenewal setOrganizerId");
        this.organizerId = organizerId;
    }
    public void setCompetitionName(String competitionName) {
        Debug.print("MetturDamEntityBeanRenewal setCompetitionName");        
        this.competitionName = competitionName;
    }
    
    public void setCompetitionDate(java.util.Date competitionDate) {
        Debug.print("MetturDamEntityBeanRenewal setCompetitionDate");                 
        this.competitionDate = competitionDate;
    }
   
    public void setComManagementName(String comManagementName) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementName");        
        this.comManagementName = comManagementName;
    }
    public void setComManagementAddress(String comManagementAddress) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementAddress");        
        this.comManagementAddress = comManagementAddress;
    }
    public void setComManagementCity(String comManagementCity) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementCity");        
        this.comManagementCity = comManagementCity;
    }
    public void setComManagementState(String comManagementState) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementState");        
        this.comManagementState = comManagementState;
    }
    public void setComManagementCountry(String comManagementCountry) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementCountry");        
        this.comManagementCountry = comManagementCountry;
    }
    public void setComManagementZip(String comManagementZip) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementZip");        
        this.comManagementZip = comManagementZip;
    }
   public void setComManagementPhone(String comManagementPhone) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementPhone");        
        this.comManagementPhone = comManagementPhone ;
    }

    public void setComManagementFax(String comManagementFax) {
        Debug.print("MetturDamEntityBeanRenewal setComManagementFax");        
        this.comManagementFax = comManagementFax ;
    }
   
    public void setManagerUsefMemberId(String managerUsefMemberId) {
        Debug.print("MetturDamEntityBeanRenewal setManagerUsefMemberId");                
        this.managerUsefMemberId = managerUsefMemberId;
    }
    
    public void setManagerUseaMemberId(String managerUseaMemberId) {
        Debug.print("MetturDamEntityBeanRenewal setManagerUseaMemberId");                
        this.managerUseaMemberId = managerUseaMemberId;
    }

    public void setManagerName(String managerName) {
        Debug.print("MetturDamEntityBeanRenewal setManagerName");                
        this.managerName = managerName;
    }

    public void setSecretaryUsefMemberId(String secretaryUsefMemberId) {
        Debug.print("MetturDamEntityBeanRenewal setSecretaryUsefMemberId");        
        this.secretaryUsefMemberId = secretaryUsefMemberId;
    }

    public void setSecretaryName(String secretaryName) {
        Debug.print("MetturDamEntityBeanRenewal setSecretaryName");        
        this.secretaryName = secretaryName;
    }
    

    public String ejbCreate(HLCRenewalOrganizerDetails objRenewalDet,HLCPaymentDetails objPayDet) throws CreateException{
        Debug.print("MetturDamEntityBeanRenewal ejbCreate"); 
        if(objRenewalDet==null && objPayDet==null){
                throw new EJBException("ejbCreate: objRenewalDet or objPayDet arg is null or empty");
        }
        
       // this.renewalId = objRenewalDet.getRenewalId();
        this.eventId = objRenewalDet.getEventId();
        this.organizerId = objRenewalDet.getOrganizerId();
        this.competitionName = objRenewalDet.getCompetitionName();
        this.competitionDate =objRenewalDet.getCompetitionDate();
        this.comManagementName = objRenewalDet.getComManagementName();
        this.comManagementAddress = objRenewalDet.getComManagementAddress();
        this.comManagementCity = objRenewalDet.getComManagementCity();
        this.comManagementState = objRenewalDet.getComManagementState();
        this.comManagementCountry = objRenewalDet.getComManagementCountry();
        this.comManagementZip = objRenewalDet.getComManagementZip();
        this.comManagementPhone = objRenewalDet.getComManagementPhone();
        this.comManagementFax = objRenewalDet.getComManagementFax();
        this.managerUsefMemberId = objRenewalDet.getManagerUsefMemberId();
        this.managerUseaMemberId =objRenewalDet.getManagerUseaMemberId();
        this.managerName = objRenewalDet.getManagerName();
        this.secretaryUsefMemberId = objRenewalDet.getSecretaryUsefMemberId();
        this.secretaryName = objRenewalDet.getSecretaryName();
       
        this.objPayDet = objPayDet;
        
        try {
            insertRowRenewDetails();
            if(objPayDet!=null){
                insertRowPayment();
            }
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
     public void ejbPostCreate(HLCRenewalOrganizerDetails objRenewalDet,HLCPaymentDetails objPayDet){
        Debug.print("MetturDamEntityBeanRenewal ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String renewalId) throws FinderException {
        Debug.print("MetturDamEntityBeanRenewal ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(renewalId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("MetturDamEntityBeanRenewal ejbFindByPrimaryKey ID:" + renewalId);
            return renewalId;
        } else {
            throw new ObjectNotFoundException("Row for id " + renewalId + " not found.");
        }
    }
     
    
    public void setEntityContext(EntityContext aContext) {
        Debug.print("MetturDamEntityBeanRenewal setEntityContext");
        context = aContext;
    }
    
    public void ejbActivate() {
         Debug.print("MetturDamEntityBeanRenewal ejbActivate");
        renewalId = (String) context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("MetturDamEntityBeanRenewal ejbPassivate");
        renewalId = null;
    }
        
    public void ejbRemove() {
        Debug.print("MetturDamEntityBeanRenewal ejbRemove");
    }
    
    public void unsetEntityContext() {
        context = null;
    }
    
     public void ejbLoad() {
        Debug.print("MetturDamEntityBeanRenewal ejbLoad");
        try {
            loadRenewal();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("MetturDamEntityBeanRenewal ejbStore");
        try {
            storeRenewal();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }

    
    
    public Collection ejbFindByEventId(String eventId) throws FinderException {
        Debug.print("MetturDamEntityBeanRenewal ejbFindByEventId");
        Collection result;

        try {
            result = selectByEventId(eventId);
            Debug.print("MetturDamEntityBeanRenewal ejbFindByEventId" + result);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByEventId " + ex.getMessage());
        }
        return result;
    }
    
  
    /*********************** Database Routines *************************/
/**
  * @Method Name    :insertRowRenewDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
        private void insertRowRenewDetails() throws SQLException {
        Debug.print("MetturDamEntityBeanRenewal insertRowEventDetails");

        makeConnection();
        try{
            String insertStatement = "insert into " + DBHelper.USEA_ORG_RENEWAL + " (event_id, organizer_id, competition_name, " +
                    " competition_date, com_management_name, com_management_address, com_management_city, " +
                    "com_management_state, com_management_country, com_management_zip, com_management_phone, com_management_fax, manager_usef_member_id, manager_usea_member_id, manager_name, " +
                    "secretary_usef_member_id, secretary_name)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? , ? , ? ) ";

            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setInt(1, eventId);
            prepStmt.setString(2, organizerId);
            prepStmt.setString(3, competitionName);
            prepStmt.setDate(4, DBHelper.toSQLDate(competitionDate));
            prepStmt.setString(5, comManagementName);
            prepStmt.setString(6, comManagementAddress);
            prepStmt.setString(7, comManagementCity);
            prepStmt.setString(8, comManagementState);
            prepStmt.setString(9, comManagementCountry);
            prepStmt.setString(10, comManagementZip);
            prepStmt.setString(11, comManagementPhone);
            prepStmt.setString(12, comManagementFax);
            prepStmt.setString(13, managerUsefMemberId);
            prepStmt.setString(14, managerUseaMemberId);
            prepStmt.setString(15, managerName);
            prepStmt.setString(16, secretaryUsefMemberId);
            prepStmt.setString(17, secretaryName);

            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowRenewDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowRenewDetails:" + e.getMessage());
        }        
        }
        
/**
  * @Method Name    :insertRowPayment.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */        
      private void insertRowPayment() throws SQLException {
        Debug.print("MetturDamEntityBeanRenewal insertRowPayment");
        makeConnection();
        try{
            String userId = null;
            String userType = null;

            String selectUserId = "select A.user_id, A.user_type_id " +
                    "FROM " + DBHelper.USEA_MMS_USERMASTER + " A, " + DBHelper.USEA_MMS_TYPEMASTER + " B, " + DBHelper.USEA_MMS_MEMBERDETAIL + " C " +
                    "WHERE A.user_type_id = B.user_type_id AND A.user_id = C.user_id AND C.member_id = ?"; 

            PreparedStatement prepStmtSelect = con.prepareStatement(selectUserId);

            prepStmtSelect.setString(1, organizerId);
            ResultSet rs = prepStmtSelect.executeQuery();



            while(rs.next()){
                userId = rs.getString(1);
                userType = rs.getString(2);
            }
            rs.close();
            prepStmtSelect.close();
            releaseConnection();
            objPayDet.setUserId(userId);
        
             Debug.print("MetturDamEntityBeanRenewal User ID:" + userId);
             Debug.print("MetturDamEntityBeanRenewal Calling Payment Bean");
            try{
                InitialContext jndiContext = getInitialContext();
                Object objPay = jndiContext.lookup("HLCPaymentLocalHome");
                objPayHome = (HLCPaymentLocalHome)objPay;
                objPayRemote = objPayHome.create(objPayDet);
                Debug.print("Payment Sucessfully Inserted..");
            }
            catch(Exception e){
                  e.printStackTrace();
                 Debug.print("Exception in " + e);
           }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowPayment:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPayment:" + e.getMessage());
        }        
    }
        
 
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String renewalId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */        
        private boolean selectByPrimaryKey(String renewalId) throws SQLException {
            Debug.print("MetturDamEntityBeanRenewal selectByPrimaryKey");

            makeConnection();
            try{    
                String selectStatement = "SELECT renewal_id from " + DBHelper.USEA_ORG_RENEWAL + " WHERE renewal_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, renewalId);

                ResultSet rs = prepStmt.executeQuery();
                boolean result = rs.next();
                prepStmt.close();
                releaseConnection();
            }
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in selectByPrimaryKey:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in selectByPrimaryKey:" + e.getMessage());
            }
            return true;
        }

/**
  * @Method Name    :selectByEventId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :Collection value.
  * @throws         :SQLException.
  */        
        private Collection selectByEventId(String eventId) throws SQLException {
            Debug.print("MetturDamEntityBeanRenewal selectByEventId:" + eventId);
            makeConnection();
            ArrayList AdsDetList = new ArrayList();
            try {
                String selectStatement = "select renewal_id from " + DBHelper.USEA_ORG_RENEWAL + " where event_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setLong(1,Long.parseLong(eventId));
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    AdsDetList.add(rs.getString(1));
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("AdsDetailBean in selectByEventId:" + AdsDetList);
            } 
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in selectByEventId:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in selectByEventId:" + e.getMessage());
            }
            return AdsDetList;
        }
        
/**
  * @Method Name    :ejbFindByCompititionName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String cmpName.
  * @return         :Collection value.
  * @throws         :FinderException.
  */        
        public Collection ejbFindByCompititionName(String cmpName) throws FinderException {
            Debug.print("MetturDamEntityBeanRenewal ejbFindByCompititionName:" + cmpName);
            makeConnection();
            ArrayList cmpDetList = new ArrayList();
            try {
                String selectStatement = "select renewal_id from " + DBHelper.USEA_ORG_RENEWAL + " where competition_name = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,cmpName);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    cmpDetList.add(rs.getString(1));
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("MetturDamEntityBeanRenewal in ejbFindByCompititionName:" + cmpDetList);
            } 
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindByCompititionName:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindByCompititionName:" + e.getMessage());
            }
            return cmpDetList;
        }
        
/**
  * @Method Name    :ejbFindByCompititionDate.
  * @Description    :This method will return a collection of renewal_id if the competation date exist other wise return empty.
  * @param          :java.util.Date cmpDate.
  * @return         :Collection value.
  * @throws         :FinderException.
  */        
    public Collection ejbFindByCompititionDate(java.util.Date cmpDate) throws FinderException {
        Debug.print("MetturDamEntityBeanRenewal ejbFindByCompititionDate :" + cmpDate);
        makeConnection();
        ArrayList cmpDetList = new ArrayList();
        try {
            String selectStatement = "select renewal_id from " + DBHelper.USEA_ORG_RENEWAL + " where competition_date = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setDate(1,DBHelper.toSQLDate(cmpDate));
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                cmpDetList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("MetturDamEntityBeanRenewal in ejbFindByCompititionDate:" + cmpDetList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in MetturDamEntityBeanRenewal ejbFindByCompititionDate:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in MetturDamEntityBeanRenewal ejbFindByCompititionDate:" + e.getMessage());
        }
        return cmpDetList;
    }        
        
        
/**
  * @Method Name    :ejbFindRenewalByStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String status.
  * @return         :Collection value.
  * @throws         :FinderException.
  */         
        public Collection ejbFindRenewalByStatus(String status) throws FinderException {
            Debug.print("MetturDamEntityBeanRenewal ejbFindRenewalByStatus");
            Debug.print("MetturDamEntityBeanRenewal Renewal ID :" + status);
            ArrayList a = new ArrayList();
            makeConnection();
            try{
                String selectStatement =
                    "select renewal_id from " + DBHelper.USEA_ORG_RENEWAL + " where request_status = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, status);

                ResultSet rs = prepStmt.executeQuery();

                while (rs.next()) {
                    a.add(rs.getString(1));
                }

                prepStmt.close();
                releaseConnection();
            }
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindRenewalByStatus:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindRenewalByStatus:" + e.getMessage());
            }
            return a;
        }
       
/**
  * @Method Name    :ejbFindByPaymentOrganizerId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String organizerId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */        
       public Collection ejbFindByPaymentOrganizerId(String organizerId) throws FinderException {
            Debug.print("MetturDamEntityBeanRenewal ejbFindByPaymentOrganizerId");
            Debug.print("MetturDamEntityBeanRenewal organizerId ID :" + organizerId);
            ArrayList a = new ArrayList();
            makeConnection();
            try{
                String selectStatement =
                    "select renewal_id from " + DBHelper.USEA_ORG_RENEWAL + " where  organizer_id=? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, organizerId);
                ResultSet rs = prepStmt.executeQuery();
              
                while (rs.next()) {
                    a.add(rs.getString(1));
                }

                prepStmt.close();
                releaseConnection();
             } 
             catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindByPaymentOrganizerId:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindByPaymentOrganizerId:" + e.getMessage());
            }
            return a;
        }
        
        /*
         private Collection selectByPaymentOraganizerId(String organizerId) throws SQLException {
        Debug.print("MetturDamEntityBeanRenewal selectByPaymentOraganizerId");

      makeConnection();
        String selectStatement =
            "select from " + DBHelper.USEA_ORG_RENEWAL + "where organizer_id= ?) ";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, organizerId);

        ResultSet rs = prepStmt.executeQuery();
        Vector v = new Vector();

        while (rs.next()) {
            v.add(rs.getString(1));
        }
        USEA_PAYMENT
        prepStmt.close();
        releaseConnection();

        return a;
      
       // return null;
      //  }
        
       */ 
        
    
/**
  * @Method Name    :loadRenewal.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */       
        private void loadRenewal() throws SQLException {
            Debug.print("MetturDamEntityBeanRenewal loadRenewal");
            Debug.print("MetturDamEntityBeanRenewal Before Loading Renewal ID:" + renewalId);
            makeConnection();
            try{
                String selectStatement =
                        "select event_id,organizer_id,competition_name,competition_date, " +
                        "com_management_name,com_management_address,com_management_city, com_management_state,com_management_country," +
                        "com_management_zip,com_management_phone,com_management_fax,manager_usef_member_id,manager_usea_member_id,manager_name,secretary_usef_member_id,secretary_name" +
                        " from " + DBHelper.USEA_ORG_RENEWAL + " where renewal_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);

                prepStmt.setString(1, renewalId);

                ResultSet rs = prepStmt.executeQuery();

                if (rs.next()) {
                    this.eventId = rs.getInt(1);
                    this.organizerId = rs.getString(2);
                    this.competitionName = rs.getString(3);
                    this.competitionDate =rs.getDate(4);
                    this.comManagementName = rs.getString(5);
                    this.comManagementAddress = rs.getString(6);
                    this.comManagementCity = rs.getString(7);
                    this.comManagementState = rs.getString(8);
                    this.comManagementCountry = rs.getString(9);
                    this.comManagementZip = rs.getString(10);
                    this.comManagementPhone = rs.getString(11);
                    this.comManagementFax =rs.getString(12);
                    this.managerUsefMemberId = rs.getString(13);
                    this.managerUseaMemberId =rs.getString(14);
                    this.managerName = rs.getString(15);
                    this.secretaryUsefMemberId = rs.getString(16);
                    this.secretaryName = rs.getString(17);
                    prepStmt.close();
                    releaseConnection();
                }
            }
             catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in loadRenewal:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in loadRenewal:" + e.getMessage());
            }
        }
        
/**
  * @Method Name    :storeRenewal.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */        
        private void storeRenewal() throws SQLException {
            Debug.print("MetturDamEntityBeanRenewal storeCustomer");
            makeConnection();
            try{
                Debug.print("MetturDamEntityBeanRenewal inside storeRenewal Renewal ID:" + renewalId);
                Debug.print("MetturDamEntityBeanRenewal inside storeRenewal Event ID :" + eventId);
                Debug.print("MetturDamEntityBeanRenewal inside storeRenewal Oraganizer ID :" + organizerId);
                String updateStatement =
                        "update " + DBHelper.USEA_ORG_RENEWAL + " set event_id = ?, organizer_id= ?,competition_name= ?, competition_date = ?, " +
                        " com_management_name = ?, com_management_address = ?, com_management_city = ? , " +
                        " com_management_state = ?,com_management_country = ?, com_management_zip = ?," +
                        "com_management_phone = ?, com_management_fax = ?, manager_usef_member_id = ?, " +
                        "manager_usea_member_id = ?, manager_name = ?, secretary_usef_member_id = ?, secretary_name = ? " +
                        "  where renewal_id = ? ";

                PreparedStatement prepStmt = con.prepareStatement(updateStatement);
                prepStmt.setInt(1, eventId);
                prepStmt.setString(2, organizerId);
                prepStmt.setString(3, competitionName);
                prepStmt.setDate(4, DBHelper.toSQLDate(competitionDate));
                prepStmt.setString(5, comManagementName);
                prepStmt.setString(6, comManagementAddress);
                prepStmt.setString(7, comManagementCity);
                prepStmt.setString(8, comManagementState);
                prepStmt.setString(9, comManagementCountry);
                prepStmt.setString(10, comManagementZip);
                prepStmt.setString(11, comManagementPhone);
                prepStmt.setString(12, comManagementFax);
                prepStmt.setString(13, managerUsefMemberId);
                prepStmt.setString(14, managerUseaMemberId);
                prepStmt.setString(15, managerName);
                prepStmt.setString(16, secretaryUsefMemberId);
                prepStmt.setString(17, secretaryName);
                prepStmt.setString(18, renewalId);

                int rowCount = prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();

                Debug.print("MetturDamEntityBeanRenewal Successfully Updated Event ID:" + eventId);
                Debug.print("MetturDamEntityBeanRenewal Successfully Updated Oraganizer ID :" + organizerId);
                if (rowCount == 0) {
                    throw new EJBException("Storing row for id " + renewalId +  " failed.");
                }
            }
             catch(SQLException sql){
                    releaseConnection();
                    throw new EJBException("SQL Exception in storeRenewal:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in storeRenewal:" + e.getMessage());
            }
        
    }

/**
  * @Method Name    :makeConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */         
     private void makeConnection() {
            Debug.print("MetturDamEntityBeanRenewal : makeConnection");
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
  * @return         :void value.
  * @throws         :Null.
  */     
     private void releaseConnection() {
            Debug.print("MetturDamEntityBeanRenewal releaseConnection");
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
         // releaseConnection
     
/**
  * @Method Name    :getInitialContext.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :InitialContext value.
  * @throws         :javax.naming.NamingException.
  */      
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("Vaigai Bean: This is from getInitialContext()");
        return ic;
    }

    
}
