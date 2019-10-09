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
/*  Program Name    : AdvertiseBean.java
 *  Created Date    : Aug 29, 2006 2:32:40 PM
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

package com.hlcadv.advertise;

import com.hlccommon.exception.HLCMissingPrimaryKeyException;
import com.hlccommon.util.HLCAdvertiseDetVO;
import com.hlccommon.util.HLCAdvertiserVO;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.sql.*;
import java.sql.*;
import javax.ejb.*;
import javax.naming.*;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Vector;
import java.util.*;


/**
 * This is the bean class for the AdvertiseBean enterprise bean.
 * Created Aug 29, 2006 2:32:40 PM
 * @author suresh
 */
public class HLCAdvertiseBean implements EntityBean, HLCAdvertiseLocalBusiness {
        private String  advertisementId;
        private String  userId;
        private String  mediaId;
        private String  advertiser;
        private String  adAgency;
        private String  agencyFirstName;
        private String  agencyMiddleName;
        private String  agencyLastName;
        private String  agencyPhone;
        private String  agencyFax;
        private String  agencyEmail;
        private String  agencyAddress;
        private String  agencySuite;
        private String  agencyCity;
        private String  agencyState;
        private String  agencyCountry;
        private String  agencyZip;
        private String  invoiceTo;
        private String  comments;
        private boolean  advPosted;
        private Date    advPostedDate;
        private String   materialComingFrom;
        private Date     materialComingDate;
        private String   advSuppliedOn;
        private String   salesPersonId;
        private String   requestStatus;
        private boolean  activeStatus;
        private ArrayList adsDet;
        private Date addDate;
                
      
        
        
        private HLCAdsDetailLocalHome objAdsDetHome;
        private HLCAdsDetailLocal objAdsDetRemote;
        private InitialContext ic;
 
        
        private EntityContext context;
        private Connection con;
        
/**
  * @Method Name    :getAdvertiseDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :AdvertiserVO  value.
  * @throws         :Null.
  */
        public HLCAdvertiserVO getAdvertiseDetails() {
            Debug.print("AdvertiseBean getAdvertisetDetails");
            return new HLCAdvertiserVO(advertisementId, userId, mediaId, advertiser,
            adAgency, agencyFirstName, agencyMiddleName, agencyLastName, agencyPhone,
            agencyFax, agencyEmail, agencyAddress, agencySuite, agencyCity,
            agencyState,  agencyCountry, agencyZip, invoiceTo, comments,
            advPosted , advPostedDate, materialComingFrom, materialComingDate,
            advSuppliedOn, salesPersonId, requestStatus, activeStatus,addDate);
      }
        
  //setter
        public void setAdvertisementId(String advertisementId) {
            this.advertisementId = advertisementId;
        }
        
        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public void setMediaId(String mediaId) {
        this.mediaId =  mediaId;
        }
        public void setAdvertiser(String advertiser) {
            this.advertiser = advertiser;
        }
        
        public void setAdAgency(String adAgency) {
            this.adAgency  =  adAgency;
        }
        
        public void setAgencyFirstName(String agencyFirstName) {
            this.agencyFirstName  = agencyFirstName;
        }
        
        public void setAgencyMiddleName(String agencyMiddleName) {
            this.agencyMiddleName = agencyMiddleName;
        }
        
        public void setAgencyLastName(String agencyLastName) {
        this.agencyLastName = agencyLastName;
        }
        public void setAgencyPhone(String agencyPhone ) {
            this.agencyPhone = agencyPhone;
        }
        
        public void setAgencyFax(String agencyFax) {
            this.agencyFax = agencyFax;
        }
        public void setAgencyEmail(String agencyEmail ) {
        this.agencyEmail = agencyEmail;
        }
        
        public void setAgencyAddress(String agencyAddress) {
            this.agencyAddress = agencyAddress;
        }
       
        public void setAgencySuite(String agencySuite) {
            this.agencySuite = agencySuite;
        }
        
        public void setAgencyCity(String agencyCity)  {
            this.agencyCity = agencyCity;
        }
        
        public void setAgencyState(String agencyState) {
            this.agencyState = agencyState;
        }
        
        public void setAgencyCountry(String agencyCountry) {
            this.agencyCountry = agencyCountry;
        }
        
        public void setAgencyZip(String agencyZip) {
            this.agencyZip = agencyZip;
        }
        
        public void setInvoiceTo(String invoiceTo) {
            this.invoiceTo = invoiceTo;
        }
        
        public void setComments(String comments) {
            this.comments = comments;
        }
        
        public void setAdvPosted(boolean advPosted) {
            this.advPosted = advPosted;
        }
        
        public void setAdvPostedDate(Date advPostedDate) {
            this.advPostedDate = advPostedDate;
        }
        
        public void setMaterialComingFrom(String materialComingFrom) {
            this.materialComingFrom = materialComingFrom;
        }
        
        public void setMaterialComingDate(Date materialComingDate) {
            this.materialComingDate = materialComingDate;
        }
        
        public void setAdvSuppliedOn(String advSuppliedOn) {
            this.advSuppliedOn = advSuppliedOn;
        }
        
        public void setSalesPersonId(String salesPersonId) {
            this.salesPersonId  = salesPersonId;
        }
        
        public void setRequestStatus(String requestStatus) {
            this.requestStatus = requestStatus;
        }
        
        public void setActiveStatus(boolean activeStatus) {
            this.activeStatus = activeStatus;
        }
        
        public void setAddDate(Date addDate){
            this.addDate = addDate;
        }
        
        
        public ArrayList getAdsDetails(){
            return adsDet;
        }
        public void setAdsDetails(ArrayList adsDet){
            this.adsDet = adsDet;
        }
        
       
        

        public String ejbCreate(HLCAdvertiserVO objAdvDet, ArrayList adsDet) throws CreateException , HLCMissingPrimaryKeyException {
            Debug.print("AdvertiseBean ejbCreate");
          // this.advertisementId = objAdvDet.getAdvertisementId();
            this.userId      =  objAdvDet.getUserId();
            this.mediaId    =  objAdvDet.getMediaId();
            this.advertiser  = objAdvDet.getAdvertiser();
            this.adAgency  = objAdvDet.getAdAgency();
            this.agencyFirstName  =  objAdvDet.getAgencyFirstName();
            this.agencyMiddleName = objAdvDet.getAgencyMiddleName();
            this.agencyLastName = objAdvDet.getAgencyLastName();
            this.agencyPhone = objAdvDet.getAgencyPhone();
            this.agencyFax  = objAdvDet.getAgencyFax();
            this.agencyEmail  = objAdvDet.getAgencyEmail();
            this.agencyAddress = objAdvDet.getAgencyAddress();
            this.agencySuite = objAdvDet.getAgencySuite();
            this.agencyCity = objAdvDet.getAgencyCity();
            this.agencyState = objAdvDet.getAgencyState();
            this.agencyCountry = objAdvDet.getAgencyCountry();
            this.agencyZip  =  objAdvDet.getAgencyZip();
            this.invoiceTo = objAdvDet.getInvoiceTo();
            this.comments  = objAdvDet.getComments();
            this.advPosted = objAdvDet.isAdvPosted();
            this.advPostedDate = objAdvDet.getAdvPostedDate();
            this.materialComingFrom = objAdvDet.getMaterialComingFrom();
            this.materialComingDate = objAdvDet.getMaterialComingDate();
            this.advSuppliedOn = objAdvDet.getAdvSuppliedOn();
            this.salesPersonId = objAdvDet.getSalesPersonId();
            this.requestStatus = "Pending";
            this.activeStatus = objAdvDet.isActiveStatus();
            this.addDate = new Date();
            this.adsDet = adsDet;
            

        try {
            insertRowAdvtDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return advertisementId;
    }
       

        public void ejbPostCreate(HLCAdvertiserVO objAdvDet, ArrayList adsDet) throws CreateException , HLCMissingPrimaryKeyException {
            Debug.print("AdvertiseBean ejbPostCreate");
        }     
     

        public String ejbFindByPrimaryKey(String advertisementId) throws FinderException {
        Debug.print("AdvertiseBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(advertisementId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return advertisementId;
        } else {
            throw new ObjectNotFoundException("Row for id " + advertisementId +
                " not found.");
        }
    }
    

        public void setEntityContext(EntityContext aContext) {
        Debug.print("AdvertiseBean setEntityContext");
        context = aContext;
    }


        public void ejbActivate() {
        Debug.print("AdvertiseBean ejbActivate");
        advertisementId = (String)context.getPrimaryKey();
    }
      

        public void ejbPassivate() {
        Debug.print("AdvertiseBean ejbPassivate");
        advertisementId = "";   
    }
     
 
        public void ejbRemove() {
        Debug.print("AdvertiseBean ejbRemove");

        try {
            deleteRow(advertisementId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
   

        public void unsetEntityContext() {
        context = null;
    }
    

        public void ejbLoad() { 
        Debug.print("AdvertiseBean ejbLoad");
        try {
            loadAdvtDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }


        public void ejbStore() {
            Debug.print("AdvertiseBean ejbStore");
            try {
                storeAdvtDetails();
            } catch (Exception ex) {
                throw new EJBException("ejbStore: " + ex.getMessage());
            }
        }

 /*********************** Database Routines *************************/
     
/**
  * @Method Name    :makeConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */
     private void makeConnection() {
            Debug.print("AdvertiseBean Event: makeConnection");
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
            Debug.print("AdvertiseBean releaseConnection");
            try {
                con.close();
            } catch (SQLException ex) {
                throw new EJBException("releaseConnection: " + ex.getMessage());
            }
        }
         // releaseConnection
   
/**
  * @Method Name    :ejbFindByAll.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
     public Collection ejbFindByAll() throws FinderException {
        Debug.print("AdvertiseBean ejbFindAll");
        Vector adsList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " order by add_date desc " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                adsList.addElement(rs.getString(1));
                Debug.print("Ads In Find ALL:" + rs.getString(1));
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
        return adsList;
}
    
/**
  * @Method Name    :ejbFindByAdsRequestStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */
  public Collection  ejbFindByAdsRequestStatus(String requestStatus) throws FinderException{
        Debug.print("AdvertiseBean findByAdsRequestStatus" + requestStatus);
        makeConnection();
        ArrayList reqstatus = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where request_status = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,requestStatus);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                reqstatus.add(rs.getString(1));
                Debug.print("AdvertiseBean findByAdsRequestStatus ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads request status");
        }
         return reqstatus;
    }
  
/**
  * @Method Name    :ejbFindByUserAds.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :FinderException.
  */  
    public Collection  ejbFindByUserAds(String userId) throws FinderException{
        Debug.print("AdvertiseBean ejbFindByUserAds" + userId);
        makeConnection();
        ArrayList reqstatus = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where user_id = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                reqstatus.add(rs.getString(1));
                Debug.print("AdvertiseBean ejbFindByUserAds ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByUserAds");
        }
         return reqstatus;
    }
  
/**
  * @Method Name    :ejbFindByAdsMediaBasedRequestStatus.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId, String requestStatus.
  * @return         :Collection value.
  * @throws         :Null.
  */  
   public Collection  ejbFindByAdsMediaBasedRequestStatus(String mediaId, String requestStatus){
        Debug.print("AdvertiseBean ejbFindByAdsMediaBasedRequestStatus" + requestStatus);
        makeConnection();
        ArrayList reqstatus = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where media_id = ? and request_status = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
            prepStmt.setString(2,requestStatus);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                reqstatus.add(rs.getString(1));
                Debug.print("AdvertiseBean findByAdsRequestStatus ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByAdsMediaBasedRequestStatus");
        }
         return reqstatus;
    } 
  
  
/**
  * @Method Name    :ejbFindByAdsMediaId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String mediaId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */   
      public Collection ejbFindByAdsMediaId(String mediaId) throws FinderException {
        Debug.print("AdvertiseBean ejbFindByAdsMediaId" + mediaId);
        makeConnection();
        ArrayList mediaList = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where media_id  = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,mediaId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                mediaList.add(rs.getString(1));
                Debug.print("AdvertiseBean ejbFindByAdsMediaId ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByAdsMediaId");
        }
         return mediaList;
    }    
     
/**
  * @Method Name    :ejbFindByAdsAgency.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String adAgency.
  * @return         :Collection value.
  * @throws         :FinderException.
  */        
       public Collection ejbFindByAdsAgency(String adAgency) throws FinderException {
        Debug.print("AdvertiseBean ejbFindByAdsAgency" + adAgency);
        makeConnection();
        ArrayList agencyList = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where ad_agency  = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,adAgency);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                agencyList.add(rs.getString(1));
                Debug.print("AdvertiseBean ejbFindByAdsAgency ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByAdsAgency");
        }
         return agencyList;
    }    
   
/**
  * @Method Name    :ejbFindByUser.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String userId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */   
    public Collection ejbFindByUser(String userId) throws FinderException {
        Debug.print("AdvertiseBean ejbFindByUser" + userId);
        makeConnection();
        ArrayList userList = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where ad_agency  = ? order by add_date desc" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                userList.add(rs.getString(1));
                Debug.print("AdvertiseBean ejbFindByUser ID:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from Ads ejbFindByUser");
        }
         return userList;
    } 
  
  


 /*   public Collection ejbFindByAdsId(String advertisementId) throws FinderException{
        Debug.print("AdsDetailBean findByAdsId:" + advertisementId);
        makeConnection();
        ArrayList adsList = new ArrayList();
   	try {
            String selectStatement = "select advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER + " where advertisement_id = ? " ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            Debug.print("AdsDetailBean findByAdsId:" + selectStatement);
            prepStmt.setString(1,advertisementId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                AdsDetList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("AdsDetailBean in findByAdsId:" + AdsDetList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in findByAdsId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in findByAdsId:" + e.getMessage());
        }
        return AdsDetList;
}
 
     */
     
/**
  * @Method Name    :selectByPrimaryKey.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advertisementId.
  * @return         :boolean value.
  * @throws         :SQLException.
  */  
    private boolean selectByPrimaryKey(String advertisementId) throws SQLException {
        Debug.print("AdvertiseBean selectByPrimaryKey");

        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT advertisement_id from " + DBHelper.USEA_ADV_ADVERTISER  + " WHERE advertisement_id = ? order by add_date desc";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, advertisementId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
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
        return result;
    }
    
/**
  * @Method Name    :getNextId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :String value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */ 
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("AdvertiseBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as planId";
            Debug.print("AdvertiseBean getNextId:" + selectStatement);
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

/**
  * @Method Name    :insertRowAdvtDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException, MissingPrimaryKeyException.
  */
    private void insertRowAdvtDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("AdvertiseBean insertRowAdvtDetails");
        this.advertisementId = getNextId();
        Debug.print("Primary Key ID:" + advertisementId);
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ADV_ADVERTISER  + " (advertisement_id, user_id, media_id, advertiser, ad_agency, " +
                "agency_first_name, agency_middle_name, agency_last_name, agency_phone, agency_fax, agency_email, agency_address, agency_suite , " +
                    "agency_city, agency_state, agency_country, agency_zip, invoice_to, comments, adv_posted, adv_posted_date, " +
                    "material_coming_from, material_coming_date, adv_supplied_on, sales_person_id, request_status, active_status, add_date) " +
                 " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? ,? , ? , " +
                 " ?, ?, ? , ? , ? , ? , ? , ?, ? )";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            Debug.print("After Conncetion Primary Key ID:" + advertisementId);

            prepStmt.setString(1, advertisementId);
            prepStmt.setString(2, userId);
            prepStmt.setString(3, mediaId);
            prepStmt.setString(4, advertiser);
            prepStmt.setString(5, adAgency);
            prepStmt.setString(6, agencyFirstName);
            prepStmt.setString(7, agencyMiddleName);
            prepStmt.setString(8, agencyLastName);
            prepStmt.setString(9, agencyPhone);
            prepStmt.setString(10, agencyFax);
            prepStmt.setString(11, agencyEmail);
            prepStmt.setString(12, agencyAddress);
            prepStmt.setString(13, agencySuite);
            prepStmt.setString(14, agencyCity);
            prepStmt.setString(15, agencyState);
            prepStmt.setString(16, agencyCountry);
            prepStmt.setString(17, agencyZip);
            prepStmt.setString(18, invoiceTo);
            prepStmt.setString(19, comments);
            prepStmt.setBoolean(20, advPosted);
            
            if(advPostedDate == null){
                prepStmt.setDate(21, null);
            }
            else{
                prepStmt.setDate(21, DBHelper.toSQLDate(advPostedDate));
            }
            
            prepStmt.setString(22, materialComingFrom);
            
            if(materialComingDate == null){
                prepStmt.setDate(23, null);
            }
            else{
                 prepStmt.setDate(23, DBHelper.toSQLDate(materialComingDate));
            }
           
            prepStmt.setString(24, advSuppliedOn);
            prepStmt.setString(25, salesPersonId);
            prepStmt.setString(26, requestStatus);
            prepStmt.setBoolean(27, activeStatus);
            prepStmt.setDate(28, DBHelper.toSQLDate(addDate));
            
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            getInitialContext();
            insertRowAdsDet();
        }
       catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowAdvtDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowAdvtDetails:" + e.getMessage());
        }        
    }

/**
  * @Method Name    :insertRowAdsDet.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void insertRowAdsDet() throws SQLException {
        Debug.print("AdvertiseBean insertRowAdsDet");
        try{
            Iterator adsDetAll = adsDet.iterator();
            while(adsDetAll.hasNext()){
                ArrayList adsDetEnum = (ArrayList)adsDetAll.next();
                Iterator adsEnum = adsDetEnum.iterator();
                while(adsEnum.hasNext()){
                    Debug.print("AdvertiseBean insertRowAdsDet Enumeration startd:");
                    String issueId = (String)adsEnum.next();
                    String advMapId = (String)adsEnum.next();
                    String dimensionId = (String)adsEnum.next();
                    String placement = (String)adsEnum.next();
                    String discount = (String)adsEnum.next();
                    String amount = (String)adsEnum.next();
                    
                    objAdsDetRemote = objAdsDetHome.create( advertisementId ,  issueId,  advMapId,  dimensionId, placement, discount,  amount);
                }
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowAdsDet:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :loadAdvtDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */      
    private void loadAdvtDetails() throws SQLException {
        Debug.print("AdvertiseBean loadAdvtDetails");
        advertisementId = (String)context.getPrimaryKey();
        makeConnection();
        try {
            String selectStatement =
                    "select  user_id, media_id, advertiser, ad_agency, " +
                    "agency_first_name, agency_middle_name, agency_last_name, agency_phone, agency_fax, agency_email, agency_address, agency_suite , " +
                    "agency_city, agency_state, agency_country, agency_zip, invoice_to, comments, adv_posted, adv_posted_date, " +
                    "material_coming_from, material_coming_date, adv_supplied_on, sales_person_id, request_status, active_status, add_date from " + DBHelper.USEA_ADV_ADVERTISER  + " where advertisement_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setString(1, advertisementId);

            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                this.userId      =  rs.getString(1);
                this.mediaId    =  rs.getString(2);
                this.advertiser  = rs.getString(3);
                this.adAgency  = rs.getString(4);
                this.agencyFirstName  = rs.getString(5);
                this.agencyMiddleName = rs.getString(6);
                this.agencyLastName = rs.getString(7);
                this.agencyPhone = rs.getString(8);
                this.agencyFax  = rs.getString(9);
                this.agencyEmail  = rs.getString(10);
                this.agencyAddress = rs.getString(11);
                this.agencySuite = rs.getString(12);
                this.agencyCity = rs.getString(13);
                this.agencyState = rs.getString(14);
                this.agencyCountry = rs.getString(15);
                this.agencyZip  =  rs.getString(16);
                this.invoiceTo = rs.getString(17);
                this.comments  = rs.getString(18);
                this.advPosted = rs.getBoolean(19);
                this.advPostedDate = rs.getDate(20);
                this.materialComingFrom = rs.getString(21);
                this.materialComingDate = rs.getDate(22);
                this.advSuppliedOn = rs.getString(23);
                this.salesPersonId = rs.getString(24);
                this.requestStatus = rs.getString(25);
                this.activeStatus = rs.getBoolean(26);
                this.addDate =  rs.getDate(27);

                prepStmt.close();
                releaseConnection();
                getInitialContext();
                loadAdsDet();
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadAdvtDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadAdvtDetails:" + e.getMessage());
        }        
    }
    
/**
  * @Method Name    :loadAdsDet.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    public void loadAdsDet() throws SQLException{
        Debug.print("AdvertiseBean loadAdsDet");
        ArrayList aAdsDet = new ArrayList();
        try{
            getInitialContext();
            Collection result = objAdsDetHome.findByAdsId(advertisementId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                    HLCAdsDetailLocal localAdsDetRemote = (HLCAdsDetailLocal)e.next();
                        aAdsDet.add((HLCAdvertiseDetVO)localAdsDetRemote.getAdvertiseDetVO());
                   }
            }
            this.adsDet = aAdsDet;
            Debug.print("AdvertiseBean Final Value :" + aAdsDet);
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadAdsDet()" + e.getMessage());
        }
    }     
    
/**
  * @Method Name    :storeAdvtDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void storeAdvtDetails() throws SQLException {
        Debug.print("AdvertiseBean storeAdvtDetails");
      
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ADV_ADVERTISER  + " set  user_id = ?, media_id= ?, advertiser= ?, ad_agency= ?, " +
                    "agency_first_name= ?, agency_middle_name = ?, agency_last_name = ?, agency_phone = ?, agency_fax = ?, agency_email = ?, " +
                    " agency_address = ?, agency_suite = ?, agency_city = ?, agency_state = ?, agency_country = ?, agency_zip = ?, " +
                    " invoice_to = ?, comments = ?, adv_posted = ?, adv_posted_date = ?, " +
                    " material_coming_from = ?, material_coming_date = ?, adv_supplied_on = ?, sales_person_id = ?, " +
                    " request_status = ?, active_status = ? , add_date = ? where advertisement_id=? ";


            PreparedStatement prepStmt = con.prepareStatement(updateStatement);


            prepStmt.setString(1, userId);
            prepStmt.setString(2, mediaId);
            prepStmt.setString(3, advertiser);
            prepStmt.setString(4, adAgency);
            prepStmt.setString(5, agencyFirstName);
            prepStmt.setString(6, agencyMiddleName);
            prepStmt.setString(7, agencyLastName);
            prepStmt.setString(8, agencyPhone);
            prepStmt.setString(9, agencyFax);
            prepStmt.setString(10, agencyEmail);
            prepStmt.setString(11, agencyAddress);
            prepStmt.setString(12, agencySuite);
            prepStmt.setString(13, agencyCity);
            prepStmt.setString(14, agencyState);
            prepStmt.setString(15, agencyCountry);
            prepStmt.setString(16, agencyZip);
            prepStmt.setString(17, invoiceTo);
            prepStmt.setString(18, comments);
            prepStmt.setBoolean(19, advPosted);

            if(advPostedDate == null){
                prepStmt.setDate(20, null);
            }
            else{
                prepStmt.setDate(20, DBHelper.toSQLDate(advPostedDate));
            }

            prepStmt.setString(21, materialComingFrom);

            if(materialComingDate == null){
                prepStmt.setDate(22, null);
            }
            else{
                 prepStmt.setDate(22, DBHelper.toSQLDate(materialComingDate));
            }

            prepStmt.setString(23, advSuppliedOn);
            prepStmt.setString(24, salesPersonId);
            prepStmt.setString(25, requestStatus);
            prepStmt.setBoolean(26, activeStatus);
            
            if(addDate!=null){
               prepStmt.setDate(27, DBHelper.toSQLDate(addDate)); 
            }
            else{
               prepStmt.setDate(27, null); 
            }
            
            prepStmt.setString(28, advertisementId);
            
            

            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
           // storeAdsDet();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeAdvtDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeAdvtDetails:" + e.getMessage());
        }        
    }
    
    
/*     public void storeAdsDet() throws SQLException{
        Debug.print("AdvertiseBean storeAdsDet");
        try{
            getInitialContext();
            if(adsDet!=null && adsDet.size()!=0){
                String issueId = "";
                String advMapId = "";
                String dimensionId = "";
                String placement = "";
                String discount = "";
                String amount = "";
               Iterator itAdsDet = adsDet.iterator();
               if(itAdsDet.hasNext()){
                    issueId = (String) itAdsDet.next();
                    advMapId = (String) itAdsDet.next();
                    dimensionId = (String) itAdsDet.next();
                    placement = (String) itAdsDet.next();
                    discount = (String) itAdsDet.next();
                    amount = (String) itAdsDet.next();
                    Debug.print("AdvertiseBean storeAdsDet");
               }
               ArrayList  iName = (ArrayList) objAdsDetHome.findByAdsId(advertisementId);
               Iterator e = iName.iterator();
               if(e.hasNext()){
                    AdsDetailLocal localAdsDetRemote = (AdsDetailLocal)e.next();
                    localAdsDetRemote.setIssueId(issueId);
                    localAdsDetRemote.setAdvMapId(advMapId);
                    localAdsDetRemote.setDimensionId(dimensionId);
                    localAdsDetRemote.setPlacement(placement);
                    localAdsDetRemote.setAmount(amount);
                    localAdsDetRemote.setDiscount(discount);
               }
             }
        }
        catch(Exception exp){
            Debug.print("Exception in AdvertiseBean storeAdsDet" + exp.getMessage());
            exp.printStackTrace();
        }
    }
    
    */
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advertisementId.
  * @return         :void value.
  * @throws         :SQLException.
  */
    private void deleteRow(String advertisementId) throws SQLException {
        Debug.print("AdvertiseBean deleteRow");
        boolean result =  deleteRowAdsDet(advertisementId);
        Debug.print("Publication  deleteRow:" + result);
        if(result==true){
            makeConnection();
            try{
                String deleteStatement = "delete from " + DBHelper.USEA_ADV_ADVERTISER  + "  where advertisement_id = ? ";
                PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
                prepStmt.setString(1, advertisementId);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection();
            }
            catch (SQLException e) {
                releaseConnection();
                throw new EJBException("Could not find any from advertisementId");
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in deleteRow:" + e.getMessage());
            }
        }
        
    }
    
/**
  * @Method Name    :deleteRowAdsDet.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String advertisementId.
  * @return         :boolean value.
  * @throws         :Null.
  */
    public boolean deleteRowAdsDet(String advertisementId){
        Debug.print("AdvertiseBean deleteRowAdsDet");
        boolean result = false;
        try{
            getInitialContext();
           ArrayList  iName = (ArrayList) objAdsDetHome.findByAdsId(advertisementId);
           Iterator e = iName.iterator();
           if(e.hasNext()){
               HLCAdsDetailLocal localAdsDetRemote = (HLCAdsDetailLocal)e.next();
                localAdsDetRemote.remove();
                result = true;
           }
        }
        catch(Exception exp){
             result = true;
            exp.printStackTrace();
        }
        return result;
    }
    
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
            Object objAdsDet = ic.lookup("HLCAdsDetailLocalHome");
            objAdsDetHome = (HLCAdsDetailLocalHome)objAdsDet;  
        }
        System.out.println("AdvertiseBean Bean getInitialContext()");
        return ic;
    }
}