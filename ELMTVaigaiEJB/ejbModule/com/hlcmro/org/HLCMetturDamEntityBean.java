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
/*  Program Name    : MetturDamEntityBean.java
 *  Created Date    : Aug 18, 2006 5:45:38 PM
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
package com.hlcmro.org;

import com.hlcmro.exception.HLCMissingPrimaryKeyException;
import com.hlcmro.masters.HLCAccommodationDetailLocal;
import com.hlcmro.masters.HLCAccommodationDetailLocalHome;
import com.hlcmro.masters.HLCCogginsDetailLocal;
import com.hlcmro.masters.HLCCogginsDetailLocalHome;
import com.hlcmro.masters.HLCCrossCountryLocal;
import com.hlcmro.masters.HLCCrossCountryLocalHome;
import com.hlcmro.masters.HLCEventTypeLocal;
import com.hlcmro.masters.HLCEventTypeLocalHome;
import com.hlcmro.masters.HLCHorseDressageLocal;
import com.hlcmro.masters.HLCHorseDressageLocalHome;
import com.hlcmro.masters.HLCJudgeDetailLocal;
import com.hlcmro.masters.HLCJudgeDetailLocalHome;
import com.hlcmro.masters.HLCOtherDetailLocal;
import com.hlcmro.masters.HLCOtherDetailLocalHome;
import com.hlcmro.masters.HLCRefundRuleDetailLocal;
import com.hlcmro.masters.HLCRefundRuleDetailLocalHome;
import com.hlcmro.masters.HLCTentativeTimeScheduleLocal;
import com.hlcmro.masters.HLCTentativeTimeScheduleLocalHome;
import com.hlcmro.util.DBHelper;
import com.hlcmro.util.Debug;
import com.hlcmro.util.HLCEJBAllJNDIs;
import com.hlcmro.util.HLCEventDetailsVO;
import com.hlcmro.util.HLCOtherDetailsVO;
import javax.sql.*;
import java.sql.*;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;
import java.util.Date;
import com.hlcmro.dao.HLCEventsDAO;
import java.text.*;

/**
 * This is the bean class for the MetturDamEntityBean enterprise bean.
 * Created Aug 14, 2006 2:56:11 PM
 * @author suresh
 */
public class HLCMetturDamEntityBean implements EntityBean, HLCMetturDamEntityLocalBusiness {
    /*==============Event ID=================*/
    private String eventId;
    private String organizeId;
    private String eventTitle;
    private String eventSecretaryId;
    
    /*==============Entries Details=================*/
    private String entryFee;
    private String otherEntryFee;
    private String membershipApplicable;
    private String doubleEntryFee;
    private String officeFee;
    private String checkPayableTo;
    /*==============Pinnies Details=================*/
    private String pinniesCharge;
    private String pinniesPosition;
    /*==============Award Details=================*/
    private String awardTrophy;
    private String awardPrize;
    private String awardOthers;
    /*==============Starting Time Details=================*/
    private String dateAvailable;
    private String availableFrom;
    private String availableFromOther;
    private String availablePosition;
    /*==============Stabling Details=================*/
    private String stablingLimited;
    private String stallPernightPrice;
    private String perStallPrice;
    private String perStallFromTime;
    private String perStallFromDate;
    private String perStallToTime;
    private String perStallToDate;
    private String noOfStalls;
    private String noOfTempStalls;
    private String noOfTempPermanentStalls;
    private String milesFromEvent;
    /*==============VETERINARIAN Details=================*/
    private String veterinarianName;
    private String veterinarianPhone;
    private String veterinarianPlace;
    /*==============Accomodation Details=================*/
    private String accomodationCamping;
    /*==============Direction Details=================*/
    private String directions;
    private String footingDesc;
    private Date addDate;
    private String statusId;
    private String comments;
    /*==============Direction Details=================*/
    private Vector accommodation;
    private Vector crossCountry;
    private Vector cogginsDetails;
    private Vector divisionLevels;
    private Vector judgesDetails;
    private Vector hDressage;
    private HLCOtherDetailsVO othersDet;
    private Vector refundRuleDetails;
    private Vector tentativeTime;
    
    private EntityContext context;
    private InitialContext ic;
    private Connection con;
    
    private HLCCogginsDetailLocalHome objCoggHome;
    private HLCCogginsDetailLocal objCoggRemote;
    
    private HLCRefundRuleDetailLocalHome objRefundRuleHome;
    private HLCRefundRuleDetailLocal objRefundRuleRemote;
    
    private HLCEventTypeLocalHome objDivLevHome;
    private HLCEventTypeLocal objDivLevRemote;
    
    private HLCJudgeDetailLocalHome objJudgeHome;
    private HLCJudgeDetailLocal objJudgeRemote;
    
    private HLCTentativeTimeScheduleLocalHome objTTSHome;
    private HLCTentativeTimeScheduleLocal objTTSRemote;
    
    private HLCAccommodationDetailLocalHome objAccHome;
    private HLCAccommodationDetailLocal objAccRemote;
    
    private HLCHorseDressageLocalHome objDressHome;
    private HLCHorseDressageLocal objDressRemote;
    
    private HLCCrossCountryLocalHome objCrossHome;
    private HLCCrossCountryLocal objCrossRemote;
    
    private HLCOtherDetailLocalHome objOthersHome;
    private HLCOtherDetailLocal objOthersRemote;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    
    
     public HLCEventDetailsVO getEventDetails() {
        Debug.print("MetturDamEntityBean getEventDetails");

        return new HLCEventDetailsVO(eventId, organizeId, eventTitle, eventSecretaryId,
            entryFee, otherEntryFee, membershipApplicable,  doubleEntryFee, officeFee, checkPayableTo,
            pinniesCharge, pinniesPosition, awardTrophy,  awardPrize,
            awardOthers, dateAvailable, availableFrom, availableFromOther,
            availablePosition, stablingLimited, stallPernightPrice, perStallPrice,
            perStallFromTime, perStallFromDate, perStallToTime, perStallToDate,
            noOfStalls, noOfTempStalls, noOfTempPermanentStalls, milesFromEvent,
            veterinarianName, veterinarianPhone, veterinarianPlace, accomodationCamping,
            directions,footingDesc, addDate, statusId, comments);
    }

    /*================ Division Methods =====================*/
    public Vector getDivisionLevels(){
        return divisionLevels;
    }
    public void setDivisionLevels(Vector divisionLevels){
        this.divisionLevels = divisionLevels;
    }
    /*================ Judges Methods =====================*/
    public Vector getJudgesDetails(){
        return judgesDetails;
    }
    public void setJudgesDetails(Vector judgesDetails){
        this.judgesDetails = judgesDetails;
    }
    
    /*================ Refund Rule Methods =====================*/
    public Vector getRefundRuleDetails(){
        return refundRuleDetails;
    }
    public void setRefundRuleDetails(Vector refundRuleDetails){
        this.refundRuleDetails = refundRuleDetails;
    }
   /*================ Coggins Methods =====================*/
    public Vector getCogginsDetails(){
        return cogginsDetails;
    }
    public void setCogginsDetails(Vector cogginsDetails){
        this.cogginsDetails = cogginsDetails;
    }
    
   /*================ Tentative Time Methods =====================*/
    public Vector getTentativeTime(){
        return tentativeTime;
    }
    
    
    public void setTentativeTime(Vector tentativeTime){
        this.tentativeTime = tentativeTime;
    }
    
    
       /*================ Horse Dressing Methods =====================*/
    public Vector getHorseDressingDetails(){
        return hDressage;
    }
    public void setHorseDressingDetails(Vector hDressage){
        this.hDressage = hDressage;
    }
    
    /*================ Cross Country Details Methods =====================*/
    public Vector getCrossCountryDetails(){
        return crossCountry;
    }
    public void setCrossCountryDetails(Vector crossCountry){
        this.crossCountry = crossCountry;
    }
    
    /*================ Cross Country Details Methods =====================*/
    public HLCOtherDetailsVO getOtherDetails(){
         return othersDet;
    }
    public void setOtherDetails(HLCOtherDetailsVO othersDet){
       this.othersDet = othersDet;
    }
    
    /*================ Accommdation Details Methods =====================*/
    public Vector getAccommodationDetails(){
        return accommodation;
    }
    public void setAccommodationDetails(Vector accommodation){
        this.accommodation = accommodation;
    }
    
    //====================================================================
     
    //Setters methods
    public void setEventId(String eventId) {
        //Debug.print("MetturDamEntityBean setEventId");
        this.eventId = eventId;
    }
     
    public void setOrganizeId(String organizeId) {
        //Debug.print("MetturDamEntityBean setOrganizeId");
        this.organizeId = organizeId;
    }
    
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }


    public void setEventSecretaryId(String eventSecretaryId) {
        //Debug.print("MetturDamEntityBean setEventSecretaryId");
        this.eventSecretaryId = eventSecretaryId;
    }

    public void setEntryFee(String entryFee) {
        //Debug.print("MetturDamEntityBean setEntryFee");
        this.entryFee = entryFee;
    }

    public void setOtherEntryFee(String otherEntryFee) {
       // Debug.print("MetturDamEntityBean setOtherEntryFee");
        this.otherEntryFee = otherEntryFee;
    }
    
    public void setMembershipApplicable(String membershipApplicable) {
        this.membershipApplicable = membershipApplicable;
    }

    public void setDoubleEntryFee(String doubleEntryFee) {
      //  Debug.print("MetturDamEntityBean setDoubleEntryFee");
        this.doubleEntryFee = doubleEntryFee;
    }

    public void  setOfficeFee(String officeFee) {
       // Debug.print("MetturDamEntityBean setOfficeFee");        
        this.officeFee = officeFee;
    }

    public void setCheckPayableTo(String checkPayableTo) {
       // Debug.print("MetturDamEntityBean setCheckPayableTo");
        this.checkPayableTo = checkPayableTo;
    }

    public void setPinniesCharge(String pinniesCharge) {
       // Debug.print("MetturDamEntityBean setPinniesCharge");
        this.pinniesCharge = pinniesCharge;
    }

    public void setPinniesPosition(String pinniesPosition) {
       // Debug.print("MetturDamEntityBean setPinniesPosition");        
        this.pinniesPosition = pinniesPosition;
    }

    public void setAwardTrophy(String awardTrophy) {
       // Debug.print("MetturDamEntityBean setAwardTrophy");
        this.awardTrophy = awardTrophy;
    }

    public void setAwardPrize(String awardPrize) {
       // Debug.print("MetturDamEntityBean setAwardPrize");
        this.awardPrize = awardPrize;
    }

    public void setAwardOthers(String awardOthers) {
        //Debug.print("MetturDamEntityBean setAwardOthers");
        this.awardOthers = awardOthers;
    }

    public void setDateAvailable(String dateAvailable) {
       // Debug.print("MetturDamEntityBean setDateAvailable");
        this.dateAvailable = dateAvailable;
    }

    public void setAvailableFrom(String availableFrom) {
       // Debug.print("MetturDamEntityBean setAvailableFrom");
        this.availableFrom = availableFrom;
    }

    public void setAvailableFromOther(String availableFromOther) {
        //Debug.print("MetturDamEntityBean setAvailableFromOther");
        this.availableFromOther = availableFromOther;
    }

    public void setAvailablePosition(String availablePosition) {
       // Debug.print("MetturDamEntityBean setAvailablePosition");
        this.availablePosition = availablePosition;
    }

    
/*==============Stabling Details=================*/
    public void setStablingLimited(String stablingLimited) {
       // Debug.print("MetturDamEntityBean setStablingLimited");
        this.stablingLimited = stablingLimited;
    }
    
    public void setStallPernightPrice(String stallPernightPrice) {
       // Debug.print("MetturDamEntityBean setStallPernightPrice");
        this.stallPernightPrice = stallPernightPrice;
    }
    
    public void setPerStallPrice(String perStallPrice) {
       // Debug.print("MetturDamEntityBean setPerStallPrice");
        this.perStallPrice = perStallPrice;
    }
    
    public void setPerStallFromTime(String perStallFromTime) {
        //Debug.print("MetturDamEntityBean setPerStallFromTime");
        this.perStallFromTime = perStallFromTime;
    }
    
    public void setPerStallFromDate(String perStallFromDate) {
       // Debug.print("MetturDamEntityBean setPerStallFromDate");
        this.perStallFromDate = perStallFromDate;
    }
    
    public void setPerStallToTime(String perStallToTime) {
        //Debug.print("MetturDamEntityBean setPerStallToTime");
        this.perStallToTime = perStallToTime;
    }
    
    public void setPerStallToDate(String perStallToDate) {
        //Debug.print("MetturDamEntityBean setPerStallToDate");
        this.perStallToDate = perStallToDate;
    }
    
    public void setNoOfStalls(String noOfStalls) {
       // Debug.print("MetturDamEntityBean setNoOfStalls");
        this.noOfStalls = noOfStalls;
    }
    
    public void setNoOfTempStalls(String noOfTempStalls) {
        //Debug.print("MetturDamEntityBean setNoOfTempStalls");
        this.noOfTempStalls = noOfTempStalls;
    }
    
    public void setNoOfTempPermanentStalls(String noOfTempPermanentStalls) {
       // Debug.print("MetturDamEntityBean setNoOfTempPermanentStalls");
        this.noOfTempPermanentStalls = noOfTempPermanentStalls;
    }
    
    public void setMilesFromEvent(String milesFromEvent) {
        //Debug.print("MetturDamEntityBean setMilesFromEvent");
        this.milesFromEvent = milesFromEvent;
    }
 /*==============VETERINARIAN Details=================*/
    public void setVeterinarianName(String veterinarianName) {
        //Debug.print("MetturDamEntityBean setVeterinarianName");
        this.veterinarianName = veterinarianName;
    }
     
    public void setVeterinarianPhone(String veterinarianPhone) {
    //Debug.print("MetturDamEntityBean setveterinarianPhone");
    this.veterinarianPhone = veterinarianPhone;
    }
    public void setVeterinarianPlace(String veterinarianPlace) {
    //Debug.print("MetturDamEntityBean setVeterinarianPlace");
    this.veterinarianPlace = veterinarianPlace;
    }

    /*==============Accomodation Details=================*/
    public void setAccomodationCamping(String accomodationCamping) {
    //Debug.print("MetturDamEntityBean setAccomodationCamping");
    this.accomodationCamping = accomodationCamping;
    }
    /*==============Direction Details=================*/
    public void setDirections(String directions) {
    //Debug.print("MetturDamEntityBean setDirections");
    this.directions = directions;
    }
    
    public void setFootingDesc(String footingDesc) {
       // Debug.print("MetturDamEntityBean setFootingDesc");
        this.footingDesc = footingDesc;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
    
   
 
    public String ejbCreate(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws CreateException , HLCMissingPrimaryKeyException {
        
        Debug.print("MetturDamEntityBean ejbCreate");
        
        if (objEventDet.getEventId() == null) {
            throw new javax.ejb.CreateException("The field \"eventId\" must not be null");
        }
        
        if (accommodation == null) {
            throw new javax.ejb.CreateException("The field \" Accommodation Details \" must not be null");
        }
        
        if (crossCountry == null) {
            throw new javax.ejb.CreateException("The field \" Cross Country Details \" must not be null");
        }
        
        if (cogginsDetails == null) {
            throw new javax.ejb.CreateException("The field \" Coggins Details \" must not be null");
        }
        
        if (divisionLevels == null) {
            throw new javax.ejb.CreateException("The field \" Division Levels \" must not be null");
        }
        
        if (hDressage == null) {
            throw new javax.ejb.CreateException("The field \" Horse Dressage Rule Details \" must not be null");
        }
        
        
        if (judgesDetails == null) {
            throw new javax.ejb.CreateException("The field \" Judges Details \" must not be null");
        }
        
           
        if (othersDet == null) {
            throw new javax.ejb.CreateException("The field \" Other Details \" must not be null");
        }
        
        if (refundRuleDetails == null) {
            throw new javax.ejb.CreateException("The field \" Refund Rule Details \" must not be null");
        }
        
        if (tentativeTime == null) {
            throw new javax.ejb.CreateException("The field \" Tentative Time \" must not be null");
        }
        
        this.eventId = objEventDet.getEventId();
        this.organizeId = objEventDet.getOrganizeId();
        this.eventTitle = objEventDet.getEventTitle();
        this.eventSecretaryId = objEventDet.getEventSecretaryId();
        this.entryFee = objEventDet.getEntryFee();
        this.otherEntryFee = objEventDet.getOtherEntryFee();
        this.membershipApplicable = objEventDet.getMembershipApplicable();
        this.doubleEntryFee = objEventDet.getDoubleEntryFee();
        this.officeFee = objEventDet.getOfficeFee();
        this.checkPayableTo = objEventDet.getCheckPayableTo();
        this.pinniesCharge = objEventDet.getPinniesCharge();
        this.pinniesPosition = objEventDet.getPinniesPosition();
        this.awardTrophy = objEventDet.getAwardTrophy();
        this.awardPrize = objEventDet.getAwardPrize();
        this.awardOthers = objEventDet.getAwardOthers();
        this.dateAvailable = objEventDet.getDateAvailable();
        this.availableFrom = objEventDet.getAvailableFrom();
        this.availableFromOther = objEventDet.getAvailableFromOther();
        this.availablePosition = objEventDet.getAvailablePosition();
        this.stablingLimited = objEventDet.getStablingLimited();
        this.stallPernightPrice = objEventDet.getStallPernightPrice();
        this.perStallPrice = objEventDet.getPerStallPrice();
        this.perStallFromTime = objEventDet.getPerStallFromTime();
        this.perStallFromDate = objEventDet.getPerStallFromDate();
        this.perStallToTime = objEventDet.getPerStallToTime();
        this.perStallToDate = objEventDet.getPerStallToDate();
        this.noOfStalls = objEventDet.getNoOfStalls();
        this.noOfTempStalls = objEventDet.getNoOfTempStalls();
        this.noOfTempPermanentStalls = objEventDet.getNoOfTempPermanentStalls();
        this.milesFromEvent = objEventDet.getMilesFromEvent();
        this.veterinarianName = objEventDet.getVeterinarianName();
        this.veterinarianPhone = objEventDet.getVeterinarianPhone();
        this.veterinarianPlace = objEventDet.getVeterinarianPlace();
        this.accomodationCamping = objEventDet.getAccomodationCamping();
        this.directions = objEventDet.getDirections();
        this.footingDesc = objEventDet.getFootingDesc();
        this.addDate = objEventDet.getAddDate();
        this.statusId = "Approved";
        this.comments = "";
        
      
        this.accommodation = accommodation;
        this.cogginsDetails = cogginsDetails;
        this.crossCountry = crossCountry;
        this.divisionLevels = divisionLevels;
        this.hDressage = hDressage;
        this.judgesDetails = judgesDetails;
        this.othersDet = othersDet;
        this.refundRuleDetails = refundRuleDetails;
        this.tentativeTime = tentativeTime;
       
        try {
            getInitialContext();
            //insertRowEventDetails();
            insertRowAccommodation();
            insertRowCrossCountry();
            insertRowCoggins();
            insertRowDivisionLevel();
            insertRowHorseDressage();
            insertRowJudges();
            insertRowOtherDetails();
            insertRowRefundRule();
            insertRowTTS();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return  eventId;
    }
 
 
     public void ejbPostCreate(HLCEventDetailsVO objEventDet, Vector accommodation, Vector crossCountry,
            Vector cogginsDetails, Vector divisionLevels, Vector hDressage, Vector judgesDetails,
            HLCOtherDetailsVO othersDet, Vector refundRuleDetails, Vector tentativeTime) throws CreateException , HLCMissingPrimaryKeyException{
        Debug.print("MetturDamEntityBean ejbCreate");
     }
 
     
     public String ejbFindByPrimaryKey(String primaryKey) throws FinderException {
        Debug.print("MetturDamEntityBean ejbFindByPrimaryKey");
        boolean result;
        try {
            result = selectByPrimaryKey(primaryKey);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return primaryKey;
        } else {
            throw new ObjectNotFoundException("Row for id " + primaryKey + " not found.");
        }
    }
   
    public void setEntityContext(EntityContext aContext) {
        Debug.print("MetturDamEntityBean setEntityContext");
        context = aContext;
    }
  
    public void ejbActivate() {
        Debug.print("MetturDamEntityBean ejbActivate");
        //Long tempEventId = (Long)context.getPrimaryKey();
        //eventId = tempEventId.longValue();
        eventId = (String)context.getPrimaryKey();
        Debug.print("MetturDamEntityBean ejbActivate Event ID:" + eventId);
    }
       
    public void ejbPassivate() {
        Debug.print("MetturDamEntityBean ejbPassivate");
        eventId = "";
    }
   
    public void ejbRemove() {
        Debug.print("MetturDamEntityBean ejbRemove");

        try {
            deleteRow(eventId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
    
   
    public void unsetEntityContext() {
        context = null;
    }
    
    public void ejbLoad() { 
        Debug.print("MetturDamEntityBean ejbLoad");
        try {
            loadEventDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("MetturDamEntityBean ejbStore");

        try {
            storeEventsDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
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
            Debug.print("MetturDamEntityBean Event: makeConnection");
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
            Debug.print("MetturDamEntityBean releaseConnection");

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
  * @param          :String primaryKey.
  * @return         :boolean value.
  * @throws         :SQLException.
  */    
    private boolean selectByPrimaryKey(String primaryKey) throws SQLException {
        Debug.print("MetturDamEntityBean selectByPrimaryKey");
        makeConnection();

        String selectStatement = "SELECT event_id from " + DBHelper.USEA_MMS_EVENTDETAILS  + " WHERE event_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setLong(1, Long.parseLong(primaryKey));

        ResultSet rs = prepStmt.executeQuery();
        boolean result = rs.next();
        prepStmt.close();
        releaseConnection();
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
          Debug.print("MetturDamEntityBean ejbFindByAll");
            ArrayList allEvent = new ArrayList();
            makeConnection();
            try{
                String selectStatement =" SELECT event_id from " + DBHelper.USEA_MMS_EVENTDETAILS + " order by add_date desc";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                   allEvent.add(rs.getString(1));
                }
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
            return allEvent;
        }
     
/**
  * @Method Name    :ejbFindByAllByStautsId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String requestStatus.
  * @return         :Collection value.
  * @throws         :FinderException.
  */      
      public Collection ejbFindByAllByStautsId(String requestStatus) throws FinderException {
          Debug.print("MetturDamEntityBean ejbFindByAllByStautsId");
            ArrayList allEvent = new ArrayList();
            makeConnection();
            try{
                String selectStatement =" SELECT event_id from " + DBHelper.USEA_MMS_EVENTDETAILS + " where status_id = ? order by add_date desc";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, requestStatus);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                   allEvent.add(rs.getString(1));
                }
                prepStmt.close();
                releaseConnection();
             } 
             catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindByAllByStautsId:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindByAllByStautsId:" + e.getMessage());
            }
            return allEvent;
        }
     
     
/**
  * @Method Name    :ejbFindByOrganizerId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String organizerId.
  * @return         :Collection value.
  * @throws         :FinderException.
  */      
    public Collection ejbFindByOrganizerId(String organizerId) throws FinderException {
          Debug.print("MetturDamEntityBean ejbFindByOrganizerId:" + organizerId);
            ArrayList allEvent = new ArrayList();
            makeConnection();
            try{
                String selectStatement =" SELECT event_id from " + DBHelper.USEA_MMS_EVENTDETAILS + " where organizer_id  = ? order by add_date desc";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,organizerId);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()) {
                   allEvent.add(rs.getString(1));
                }
                prepStmt.close();
                releaseConnection();
             } 
             catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindByOrganizerId:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindByOrganizerId:" + e.getMessage());
            }
            return allEvent;
        }    
    
    
/**
  * @Method Name    :insertRowEventDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException,MissingPrimaryKeyException.
  */
    private void insertRowEventDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("MetturDamEntityBean insertRowEventDetails");

       // Debug.print("Primary Key ID:" + eventId);

        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_MMS_EVENTDETAILS  + " (event_id,  organizer_id, event_title, event_secretary_id, entry_fee, other_entry_fee, " +
                " membership_applicable, double_entry_fee_status, office_fee, check_payable_to, pinnies_charge, pinnies_position,award_trophy, award_prize, award_others ,date_available,available_from, " +
                "available_from_other,available_position, stabling_limited, stall_pernight_price, per_stall_price, per_stall_from_time, per_stall_from_date, per_stall_to_time, " +
                 "per_stall_to_date,no_of_stalls,no_of_temp_stalls,no_of_temp_permanent_stalls,miles_from_event,veterinarian_name,veterinarian_phone, "+
                 "veterinarian_place,accomodation_camping,directions,footing_desc,status_id) " +
                 " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? , ? ,? , ? , " +
                 " ?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
             //Debug.print("Before Primary Key ID:" + insertStatement);
            Debug.print("After Conncetion Primary Key ID:" + eventId);

            prepStmt.setLong(1, Long.parseLong(eventId));
            prepStmt.setString(2, organizeId);
            prepStmt.setString(3, eventTitle);
            prepStmt.setString(4, eventSecretaryId);
            prepStmt.setString(5, entryFee);
            prepStmt.setString(6, otherEntryFee);
            prepStmt.setString(7, membershipApplicable);
            prepStmt.setString(8, doubleEntryFee);
                        
          /*  if(officeFee == null && officeFee.trim().length() != 0){
                prepStmt.setString(9, "0");
                Debug.print("       officeFee : 0");
            } else{
                prepStmt.setString(9, officeFee);
                Debug.print("       officeFee:" + officeFee);
            }*/
            
            prepStmt.setString(9, officeFee);
            prepStmt.setString(10, checkPayableTo);
            prepStmt.setString(11, pinniesCharge);
            prepStmt.setString(12, pinniesPosition);
            prepStmt.setString(13, awardTrophy);
            prepStmt.setString(14, awardPrize);
            prepStmt.setString(15, awardOthers);
            prepStmt.setString(16, dateAvailable);
            prepStmt.setString(17, availableFrom);
            prepStmt.setString(18, availableFromOther);
            prepStmt.setString(19, availablePosition);
           
            if(stablingLimited!=null && stablingLimited.trim().length()!=0 && stablingLimited.equals("1") ){
                prepStmt.setBoolean(20, true);
            }
            else{
                prepStmt.setBoolean(20, false);
            }
            
            if(stallPernightPrice==null || stallPernightPrice.trim().length()!=0){
                prepStmt.setFloat(21, 0);
                Debug.print("       stallPernightPrice: 0");
            }
            else{
                prepStmt.setFloat(21, Float.parseFloat(stallPernightPrice));
                Debug.print("       stallPernightPrice:" + Float.parseFloat(stallPernightPrice));
            }
            
            if(perStallPrice==null || perStallPrice.trim().length()!=0){
                prepStmt.setFloat(22, 0);
                Debug.print("       perStallPrice: 0");
            }
            else{
                prepStmt.setFloat(22, Float.parseFloat(perStallPrice));
                 Debug.print("       perStallPrice:" + Float.parseFloat(perStallPrice));
            }
            
            prepStmt.setString(23, perStallFromTime);
            
            Debug.print("       perStallFromTime:" + perStallFromTime);
            
            if(perStallFromDate!=null && perStallFromDate.trim().length()!=0){
                 Debug.print("       before perStallFromDate:" + perStallFromDate);
                Date perDate = sdf.parse(perStallFromDate);
                prepStmt.setDate(24, DBHelper.toSQLDate(perDate));
                Debug.print("       perStallFromDate:" + perDate);
            }
            else{
                java.sql.Date dt;// = new Date(); 
                        dt=null;
                prepStmt.setDate(24, dt);
                 Debug.print("       perStallFromDate: Null");
            }
            
           prepStmt.setString(25, perStallToTime);
           
            if(perStallToDate!=null && perStallToDate.trim().length()!=0){
                Debug.print("       before perStallToDate:" + perStallToDate);
                 Date pTODate = sdf.parse(perStallToDate);
                prepStmt.setDate(26, DBHelper.toSQLDate(pTODate));
                 Debug.print("       perStallToDate: " + pTODate);
            }
            else{
               
                java.sql.Date dt;// = new Date(); 
                        dt=null;
               
                prepStmt.setDate(26, dt);
                Debug.print("       perStallToDate: Null");
            }
           
            Debug.print("==========================");
            
            prepStmt.setString(27, noOfStalls);
            prepStmt.setString(28, noOfTempStalls);
            prepStmt.setString(29, noOfTempPermanentStalls);
            prepStmt.setString(30, milesFromEvent);
            prepStmt.setString(31, veterinarianName);
            prepStmt.setString(32, veterinarianPhone);
            prepStmt.setString(33, veterinarianPlace);
            prepStmt.setString(34, accomodationCamping);
            prepStmt.setString(35, directions);
            prepStmt.setString(36, footingDesc);
            prepStmt.setString(37, "Pending");
             
             Debug.print("======================= debug on bean : ====================== ");
            
             Debug.print("       eventId :"+eventId);
             Debug.print("       eventTitle :" +eventTitle);
             Debug.print("       eventSecretaryId : "+eventSecretaryId);
             Debug.print("       entryFee :"+entryFee);
             Debug.print("       otherEntryFee :"+otherEntryFee);
             Debug.print("       membershipApplicable :"+membershipApplicable);
             Debug.print("       doubleEntryFee :"+doubleEntryFee);
             Debug.print("       officeFee :"+officeFee);
             Debug.print("       checkPayableTo :"+checkPayableTo);
             Debug.print("       pinniesCharge :"+pinniesCharge);
             Debug.print("       pinniesPosition :"+pinniesPosition);
             Debug.print("       awardTrophy :"+awardTrophy);
             Debug.print("       awardPrize :"+awardPrize);
             Debug.print("       awardOthers :"+awardOthers);
             Debug.print("       dateAvailable :"+dateAvailable);
             Debug.print("       availableFrom :"+availableFrom);
             Debug.print("       availableFromOther :"+availableFromOther);
             Debug.print("       availablePosition :"+availablePosition);
             Debug.print("       organizeId :"+organizeId);
             Debug.print("       stablingLimited:" + stablingLimited);
             Debug.print("       stallPernightPrice:" + stallPernightPrice);
             Debug.print("       perStallPrice:" + perStallPrice);
             Debug.print("       perStallFromTime:" + perStallFromTime);
             Debug.print("       before perStallFromDate:" + perStallFromDate);
             Debug.print("       perStallFromTime:" + perStallToTime);
             Debug.print("       before perStallToDate:" + perStallToDate);
             Debug.print("       noOfStalls :"+noOfStalls);
             Debug.print("       noOfTempStalls :"+noOfTempStalls);
             Debug.print("       noOfTempPermanentStalls :"+noOfTempPermanentStalls);
             Debug.print("       milesFromEvent :"+milesFromEvent);
             Debug.print("       veterinarianName :"+veterinarianName);
             Debug.print("       veterinarianPhone :"+veterinarianPhone);
             Debug.print("       veterinarianPlace :"+veterinarianPlace);
             Debug.print("       accomodationCamping :"+accomodationCamping);
             Debug.print("       directions :"+directions);
             Debug.print("       footingDesc :"+footingDesc);
                    
            Debug.print("============================================= ");
            
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            /*insertRowAccommodation();
            insertRowCrossCountry();
            insertRowCoggins();
            insertRowDivisionLevel();
            insertRowHorseDressage();
            insertRowJudges();
            insertRowOtherDetails();
            insertRowRefundRule();
            insertRowTTS();*/
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowEventDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowEventDetails:" + e.getMessage());
        }       
    } 

/**
  * @Method Name    :insertRowAccommodation.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void insertRowAccommodation() throws SQLException {
        Debug.print("MetturDamEntityBean insertRowAccommodation");
        try{
            Enumeration accEnum = accommodation.elements();
            while(accEnum.hasMoreElements()){
                String acc[] = (String[])accEnum.nextElement();
                String hotelName = acc[0];
                String hotelPhone =  acc[1];
                String milesFromEvent =  acc[2];
                Debug.print("hotelName:" + hotelName);
                Debug.print("hotelPhone:" + hotelPhone);
                Debug.print("milesFromEvent:" + milesFromEvent);
                objAccRemote = objAccHome.create(eventId,hotelName,hotelPhone,milesFromEvent);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowAccommodation:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :insertRowCrossCountry.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void insertRowCrossCountry() throws SQLException {
        Debug.print("MetturDamEntityBean insertRowCrossCountry");
        try{
            Enumeration crossEnum = crossCountry.elements();
            while(crossEnum.hasMoreElements()){
                String crossCountry[] = (String[])crossEnum.nextElement();
                    String division = crossCountry[0];
                    String length =  crossCountry[1];
                    String speed =  crossCountry[2];
                    String courseDescription = crossCountry[3];
                    String addInformation =  crossCountry[4];
                    objCrossRemote = objCrossHome.create(eventId, division, length, speed, courseDescription, addInformation);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowCrossCountry:" + e.getMessage());
        }       
    }
        
/**
  * @Method Name    :insertRowCoggins.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
    private void insertRowCoggins() {
        Debug.print("MetturDamEntityBean insertRowCoggins");
        try{
            Enumeration coggEnum = cogginsDetails.elements();
            while(coggEnum.hasMoreElements()){
                String allState = (String)coggEnum.nextElement();
                String inState = (String)coggEnum.nextElement();
                String outOfState = (String)coggEnum.nextElement();
                String noState = (String)coggEnum.nextElement();
                String others = (String)coggEnum.nextElement();
                objCoggRemote = objCoggHome.create(eventId,allState,inState,outOfState,noState,others);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowCoggins:" + e.getMessage());
        }       
    }

/**
  * @Method Name    :insertRowDivisionLevel.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void insertRowDivisionLevel() throws SQLException {
        Debug.print("MetturDamEntityBean insertRowDivisionLevel");
        try{
            Enumeration divEnum = divisionLevels.elements();
            while(divEnum.hasMoreElements()){
               // ArrayList al = (ArrayList)divEnum.nextElement();
              //  Iterator it = al.iterator();
               // while(it.hasNext()){
                    String mapTypeId = (String)divEnum.nextElement();
                    Debug.print("Map Type Id:" + mapTypeId);
                    objDivLevRemote = objDivLevHome.create(eventId,mapTypeId);
               // }
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowDivisionLevel:" + e.getMessage());
        }       
    }

/**
  * @Method Name    :insertRowJudges.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */      
    private void insertRowJudges() throws SQLException {
        Debug.print("MetturDamEntityBean insertRowJudges");
        try{
         
            Enumeration judEnum = judgesDetails.elements();
            while(judEnum.hasMoreElements()){
                String judges[] = (String[])judEnum.nextElement();
                    String judgeTypeId = judges[0];
                    String judgeNames =  judges[1];
                    Debug.print("judgeTypeId:" + judgeTypeId);
                    Debug.print("judgeNames:" + judgeNames);
                    objJudgeRemote = objJudgeHome.create(eventId,judgeTypeId,judgeNames);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowJudges:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :insertRowHorseDressage.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */       
    private void insertRowHorseDressage() throws SQLException {
        Debug.print("MetturDamEntityBean insertRowHorseDressage");
        try{
            Enumeration hDressEnum = hDressage.elements();
            while(hDressEnum.hasMoreElements()){
                String hDress[] = (String[])hDressEnum.nextElement();
                String dressageMapId = hDress[0];
                String arenaSizeId =  hDress[1];
                Debug.print("MetturDamEntityBean insertRowHorseDressage dressageMapId:" + dressageMapId);
                Debug.print("MetturDamEntityBean insertRowHorseDressage arenaSizeId" + arenaSizeId);
                objDressRemote = objDressHome.create(eventId, dressageMapId, arenaSizeId);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowHorseDressage:" + e.getMessage());
        }       
    }
  
/**
  * @Method Name    :insertRowOtherDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void insertRowOtherDetails() throws SQLException{
        Debug.print("MetturDamEntityBean insertRowOtherDetails");
        try{
            othersDet.setEventId(eventId);
            objOthersRemote = objOthersHome.create(othersDet);
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowOtherDetails:" + e.getMessage());
        }      
     }
     
/**
  * @Method Name    :insertRowRefundRule.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
    private void insertRowRefundRule() {
        Debug.print("MetturDamEntityBean insertRowRefundRule");
        try{
            Enumeration rfEnum = refundRuleDetails.elements();
            while(rfEnum.hasMoreElements()){
                String refundRule[] = (String[])rfEnum.nextElement();
                    String refundMapId = refundRule[0];
                    String amount =  refundRule[1];
                    objRefundRuleRemote = objRefundRuleHome.create(eventId,refundMapId,amount);
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowRefundRule:" + e.getMessage());
        }       
    }
  
/**
  * @Method Name    :insertRowTTS.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void insertRowTTS() throws SQLException {
        Debug.print("MetturDamEntityBean insertRowTTS");
        try{
            Enumeration ttsEnum = tentativeTime.elements();
            Debug.print("tentativeTime.elements() :"+tentativeTime.size());
            
            while(ttsEnum.hasMoreElements()){
                ArrayList tts = (ArrayList)ttsEnum.nextElement();
                Debug.print("tts array list ----> tts.size():"+tts.size());
                
                Iterator it = tts.iterator();
                while(it.hasNext()){
                    Date day = (Date)it.next();
                    String phase =  (String)it.next();
                    String time =  (String)it.next();
                    
                    Debug.print("=================== tts bean =============");
                    
                    Debug.print("day :"+day);
                    Debug.print("phase :"+phase);
                    Debug.print("time :"+time);
                    
                    
                    objTTSRemote = objTTSHome.create(eventId, day, phase, time);
                    
                    Debug.print("after objTTSHome.create(eventId, day, phase, time) ");
                    Debug.print("===============================");
                    
                }
            }
        }
        catch(Exception e){
            throw new EJBException("General Exception  in insertRowTTS:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :loadEventDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void loadEventDetails() throws SQLException {
        Debug.print("MetturDamEntityBean loadEventDetails");
       // Long tempEventId = (Long)context.getPrimaryKey();
        eventId = (String)context.getPrimaryKey();
       // eventId = tempEventId.longValue();

        makeConnection();
        try{

            String selectStatement =
                "select organizer_id, event_title, event_secretary_id, entry_fee, other_entry_fee, " +
                "membership_applicable, double_entry_fee_status, office_fee, check_payable_to, pinnies_charge, pinnies_position,award_trophy, award_prize, award_others ,date_available,available_from, " +
                "available_from_other,available_position, stabling_limited, stall_pernight_price, per_stall_price, per_stall_from_time, per_stall_from_date, per_stall_to_time, " +
                 "per_stall_to_date,no_of_stalls,no_of_temp_stalls,no_of_temp_permanent_stalls,miles_from_event,veterinarian_name,veterinarian_phone, "+
                 "veterinarian_place,accomodation_camping,directions,footing_desc, add_date, status_id, comments from " + DBHelper.USEA_MMS_EVENTDETAILS  + " where event_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);

            prepStmt.setLong(1, Long.parseLong(eventId));

            ResultSet rs = prepStmt.executeQuery();

            if (rs.next()) {
                this.organizeId = rs.getString(1);
                this.eventTitle = rs.getString(2);
                this.eventSecretaryId =  rs.getString(3);
                this.entryFee =  rs.getString(4);
                this.otherEntryFee = rs.getString(5);
                this.membershipApplicable = rs.getString(6);
                this.doubleEntryFee = rs.getString(7);
                this.officeFee = rs.getString(8);
                this.checkPayableTo = rs.getString(9);
                this.pinniesCharge = rs.getString(10);
                this.pinniesPosition = rs.getString(11);
                this.awardTrophy = rs.getString(12);
                this.awardPrize = rs.getString(13);
                this.awardOthers = rs.getString(14);
                this.dateAvailable = rs.getString(15);
                this.availableFrom = rs.getString(16);
                this.availableFromOther = rs.getString(17);
                this.availablePosition = rs.getString(18);
                this.stablingLimited = rs.getString(19);
                this.stallPernightPrice = rs.getString(20);
                this.perStallPrice = rs.getString(21);
                this.perStallFromTime = rs.getString(22);
                this.perStallFromDate = rs.getString(23);
                this.perStallToTime = rs.getString(24);
                this.perStallToDate = rs.getString(25);
                this.noOfStalls = rs.getString(26);
                this.noOfTempStalls = rs.getString(27);
                this.noOfTempPermanentStalls = rs.getString(28);
                this.milesFromEvent = rs.getString(29);
                this.veterinarianName = rs.getString(30);
                this.veterinarianPhone = rs.getString(31);
                this.veterinarianPlace = rs.getString(32);
                this.accomodationCamping = rs.getString(33);
                this.directions = rs.getString(34);
                this.footingDesc = rs.getString(35);
                this.addDate = rs.getDate(36);
                this.statusId = rs.getString(37);
                this.comments = rs.getString(38);
                rs.close();
                prepStmt.close();
                releaseConnection();
                //load all other tales]
                getInitialContext();
                loadAccommodation();
                loadCrossCountry();
                loadCoggins();
                loadDivisionLevel();
                loadHorseDressage();
                loadJudges();
                loadOthersDetails();
                loadRefundRule();
                loadTTS();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + eventId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadEventDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadEventDetails:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :loadAccommodation.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */         
    public void loadAccommodation(){
        Debug.print("MetturDamEntityBean loadAccommodation:" + eventId);
        Vector v = new Vector();
        try{
        Collection result = objAccHome.findByEventId(eventId);
            Debug.print("MetturDamEntityBean loadAccommodation Event ID:" + eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                Debug.print("MetturDamEntityBean loadAccommodation After Iterator:" + result);
                while(e.hasNext()){
                   HLCAccommodationDetailLocal localAccRemote = (HLCAccommodationDetailLocal)e.next();
                    String accId = localAccRemote.getAccommodationId();
                    String eId = localAccRemote.getEventId();
                    String hotelName = localAccRemote.getHotelName();
                    String hotelPhone =  localAccRemote.getHotelPhone();
                    String milesFromEvent =  localAccRemote.getMilesFromEvent();
                    Debug.print("Event Id:" + eId);
                    Debug.print("hotelName:" + hotelName);
                    Debug.print("hotelPhone:" + hotelPhone);
                    Debug.print("milesFromEvent:" + milesFromEvent);
                    String acc[] = {accId,eId,hotelName,hotelPhone,milesFromEvent};
                    v.add(acc);
                }
                this.accommodation = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadAccommodation()" + e.getMessage());
        }
}
    
/**
  * @Method Name    :loadCrossCountry.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
    public void loadCrossCountry(){
        Debug.print("MetturDamEntityBean loadCrossCountry");
        Vector v = new Vector();
        try{
        Collection result = objCrossHome.findByEventId(eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCCrossCountryLocal localCrossRemote = (HLCCrossCountryLocal)e.next();
                    String CrossId = localCrossRemote.getCrossCountryId();
                    String eId = localCrossRemote.getEventId();
                    String division = localCrossRemote.getDivision();
                    String length =  localCrossRemote.getLength();
                    String speed =  localCrossRemote.getSpeed();
                    String courseDescription = localCrossRemote.getCourseDescription();
                    String addInformation =  localCrossRemote.getAddInformation();
                    String cross[] = {CrossId, eId, division, length, speed, courseDescription, addInformation};
                    v.addElement(cross);
                }
                this.crossCountry = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadCrossCountry()" + e.getMessage());
        }
}
    
/**
  * @Method Name    :loadCoggins.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */     
    public void loadCoggins(){
        Debug.print("MetturDamEntityBean loadCoggins");
        Vector v = new Vector();
        try{
        Collection result = objCoggHome.findByEventId(eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCCogginsDetailLocal localcoggRemote = (HLCCogginsDetailLocal)e.next();
                    String coggId = localcoggRemote.getCogginsId();
                    String eId = localcoggRemote.getEventId();
                    String allState = localcoggRemote.getAllState();
                    String inState = localcoggRemote.getInState();
                    String outOfState = localcoggRemote.getOutOfState();
                    String noState = localcoggRemote.getNoState();
                    String others = localcoggRemote.getOthers();
                    String CoggVal[]= {coggId,eId,allState,inState,outOfState,noState,others};
                    v.addElement(CoggVal);
                }
                this.cogginsDetails = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadCoggins()" + e.getMessage());
        }
}
    
/**
  * @Method Name    :loadDivisionLevel.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
     public void loadDivisionLevel(){
        Debug.print("MetturDamEntityBean loadDivisionLevel");
        Vector  divisionLevels = new HLCEventsDAO().getDivLevelByEventId(eventId);
        this.divisionLevels = divisionLevels;
    }
    
    
  /*   public void loadDivisionLevel(){
        Debug.print("MetturDamEntityBean loadDivisionLevel");
        Vector v = new Vector();
        try{
        Collection result = objDivLevHome.findByEventId(eventId);
        Debug.print("MetturDamEntityBean loadDivisionLevel Result:" + result);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                Debug.print("MetturDamEntityBean loadDivisionLevel Result:" + result);
                while(e.hasNext()){
                   EventTypeLocal localETypeRemote = (EventTypeLocal)e.next();
                    Debug.print("MetturDamEntityBean loadDivisionLevel Result loop:" + localETypeRemote.getEvTypeList());
                   //String divList[] = (String[])e.next();
                  // Debug.print("MetturDamEntityBean loadDivisionLevel Result:" + divList);
                  // String eDetId = localETypeRemote.getEventDetailId();
                  // String eId = localETypeRemote.getEventId();
                 //  String mapTypeId = localETypeRemote.getMapTypeId();
                //   String Div[]= {eDetId,eId,mapTypeId};
                  // v.addElement(divList);
                }
                this.divisionLevels = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadDivisionLevel()" + e.getMessage());
        }
}
   **/
     
/*    public void loadHorseDressage(){
        Debug.print("MetturDamEntityBean loadHorseDressage");
        Vector v = new Vector();
        try{
        Collection result = objDressHome.findByEventId(eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HorseDressageLocal localDressRemote = (HorseDressageLocal)e.next();
                   String eDresId = localDressRemote.getDressageId();
                   String eId = localDressRemote.getEventId();
                   String dressageMapId = localDressRemote.getDressageMapId();
                   String arenaSizeId = localDressRemote.getArenaSizeId();
                   String dress[]= {eDresId,eId,dressageMapId,arenaSizeId};
                   v.addElement(dress);
                }
                this.hDressage = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadHorseDressage()" + e.getMessage());
        }
}
    */
     
/**
  * @Method Name    :loadHorseDressage.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */       
    public void loadHorseDressage(){
        Debug.print("MetturDamEntityBean loadHorseDressage");
        Vector  hDressage = new HLCEventsDAO().getHorseDressageByEventId(eventId);
        this.hDressage = hDressage;
    }
    
/**
  * @Method Name    :loadJudges.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */     
    public void loadJudges(){
        Debug.print("MetturDamEntityBean loadJudges");
        Vector v = new Vector();
        try{
        Collection result = objJudgeHome.findByEventId(eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCJudgeDetailLocal localJudRemote = (HLCJudgeDetailLocal)e.next();
                   String eJudId = localJudRemote.getJudgeId();
                   String eId = localJudRemote.getEventId();
                   String judgeTypeId = localJudRemote.getJudgeTypeId();
                   String judgeNames = localJudRemote.getJudgeNames();
                   String jud[]= {eJudId,eId,judgeTypeId,judgeNames};
                   v.addElement(jud);
                   
                   
                }
                this.judgesDetails = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadJudges()" + e.getMessage());
        }
}
      
/*    public void loadRefundRule(){
        Debug.print("MetturDamEntityBean loadRefundRule");
        Vector v = new Vector();
        try{
        Collection result = objRefundRuleHome.findByEventId(eventId);
           if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   RefundRuleDetailLocal localRefundRemote = (RefundRuleDetailLocal)e.next();
                   String refundId = localRefundRemote.getRefundId();
                   String eId = localRefundRemote.getEventId();
                   String eId = localRefundRemote.getEventId();
                    String refundMapId = localRefundRemote.getRefundMapId();
                    String amount =  localRefundRemote.getAmount();
                   String rf[]= {refundId,eId,refundMapId,amount};
                   v.addElement(rf);
                }
                this.refundRuleDetails = v;
            }
           
         // C.refund_id, C.event_id, A.refund_rule_type_name, B.refund_rule_sub_type_name, C.refund_map_id, C.amount 
        }
       // C.refund_id, C.event_id, A.refund_rule_type_name, B.refund_rule_sub_type_name, C.refund_map_id, C.amount 
        catch(Exception e){
            Debug.print("Exception while invoking loadRefundRule()" + e.getMessage());
        }
}

*/
    
/**
  * @Method Name    :loadRefundRule.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */    
    public void loadRefundRule(){
        Debug.print("MetturDamEntityBean loadRefundRule");
        Vector  refundRuleDetails = new HLCEventsDAO().getRefundRuleByEventId(eventId);
        this.refundRuleDetails = refundRuleDetails;
    }
    
/**
  * @Method Name    :loadTTS.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */        
    public void loadTTS(){
        Debug.print("MetturDamEntityBean loadTTS");
        Vector v = new Vector();
        try{
        Collection result = objTTSHome.findByEventId(eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCTentativeTimeScheduleLocal localTTSRemote = (HLCTentativeTimeScheduleLocal)e.next();
                    String tId = localTTSRemote.getTimeScheduleId();
                    String eId = localTTSRemote.getEventId();
                    Date day = localTTSRemote.getDay();
                    String phase = localTTSRemote.getPhase();
                    String time =  localTTSRemote.getTime();
                    ArrayList tts = new ArrayList();
                    tts.add(tId);
                    tts.add(eId);
                    tts.add(day);
                    tts.add(phase);
                    tts.add(time);
                    v.addElement(tts);
                }
                this.tentativeTime = v;
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadTTS()" + e.getMessage());
        }
}     
 
/**
  * @Method Name    :loadOthersDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */     
    public void loadOthersDetails(){
        Debug.print("MetturDamEntityBean loadOthersDetails");
        //Vector v = new Vector();
        try{
        Collection result = objOthersHome.findByEventId(eventId);
            if(result!=null && result.size()!=0){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCOtherDetailLocal localOtherRemote = (HLCOtherDetailLocal)e.next();
                   HLCOtherDetailsVO objOthers = localOtherRemote.getOtherDetailsVO();
                   //v.addElement(objOthers);
                   this.othersDet = objOthers;
               }
                
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking loadOthersDetails()" + e.getMessage());
        }
}     
 
/**
  * @Method Name    :storeEventsDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :SQLException.
  */    
    private void storeEventsDetails() throws SQLException {
        Debug.print("MetturDamEntityBean storeEventsDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_MMS_EVENTDETAILS  + " set organizer_id = ? , event_title = ?,  event_secretary_id = ? , entry_fee = ? , other_entry_fee = ? , " +
                    " membership_applicable = ?,double_entry_fee_status = ? , office_fee = ? ,check_payable_to = ? , pinnies_charge = ? , pinnies_position = ? , award_trophy = ? , " +
                    "award_prize = ? , award_others = ? , date_available = ? , available_from = ? , available_from_other = ? , " +
                    "available_position = ? , stabling_limited = ? , stall_pernight_price = ? , per_stall_price = ? , per_stall_from_time = ? , " +
                    "per_stall_from_date = ? , per_stall_to_time = ? , per_stall_to_date = ? ,  no_of_stalls = ? , no_of_temp_stalls=?, no_of_temp_permanent_stalls=?, " +
                    "miles_from_event = ? , veterinarian_name = ? , veterinarian_phone = ? , veterinarian_place = ? ,  accomodation_camping = ? ,  " +
                    "directions = ? , footing_desc = ? , status_id = ?, comments = ?  where event_id=? ";


            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, organizeId);
            prepStmt.setString(2, eventTitle);
            prepStmt.setString(3, eventSecretaryId);
            prepStmt.setString(4, entryFee);
            prepStmt.setString(5, otherEntryFee);
            prepStmt.setString(6, membershipApplicable);
            prepStmt.setString(7, doubleEntryFee);
            prepStmt.setString(8, officeFee);
            prepStmt.setString(9, checkPayableTo);
            prepStmt.setString(10, pinniesCharge);
            prepStmt.setString(11, pinniesPosition);
            prepStmt.setString(12, awardTrophy);
            prepStmt.setString(13, awardPrize);
            prepStmt.setString(14, awardOthers);
            prepStmt.setString(15, dateAvailable);
            prepStmt.setString(16, availableFrom);
            prepStmt.setString(17, availableFromOther);
            prepStmt.setString(18, availablePosition);
            prepStmt.setString(19, stablingLimited);
            prepStmt.setString(20, stallPernightPrice);
            prepStmt.setString(21, perStallPrice);
            prepStmt.setString(22, perStallFromTime);
            prepStmt.setString(23, perStallFromDate);
            prepStmt.setString(24, perStallToTime);
            prepStmt.setString(25, perStallToDate);
            prepStmt.setString(26, noOfStalls);
            prepStmt.setString(27, noOfTempStalls);
            prepStmt.setString(28, noOfTempPermanentStalls);
            prepStmt.setString(29, milesFromEvent);
            prepStmt.setString(30, veterinarianName);
            prepStmt.setString(31, veterinarianPhone);
            prepStmt.setString(32, veterinarianPlace);
            prepStmt.setString(33, accomodationCamping);
            prepStmt.setString(34, directions);
            prepStmt.setString(35, footingDesc);
            prepStmt.setString(36, statusId);
            prepStmt.setString(37, comments);
            prepStmt.setLong(38, Long.parseLong(eventId));


            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeEventsDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeEventsDetails:" + e.getMessage());
        }       
    }
    
/**
  * @Method Name    :deleteRow.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String eventId.
  * @return         :void value.
  * @throws         :SQLException.
  */     
    private void deleteRow(String eventId) throws SQLException {
         Debug.print("MetturDamEntityBean deleteRow");

        makeConnection();

        String deleteStatement = "delete from " + DBHelper.USEA_MMS_EVENTDETAILS  + "  where event_id = ? ";
        PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

        prepStmt.setLong(1, Long.parseLong(eventId));
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
    }
      
/**
  * @Method Name    :getInitialContext.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :javax.naming.NamingException.
  */    
    public void getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
            InitialContext jndiContext = ic;
            Object objAcc = jndiContext.lookup("HLCAccommodationDetailLocalHome");
            objAccHome = (HLCAccommodationDetailLocalHome)objAcc;
            
            Object objCross = jndiContext.lookup("HLCCrossCountryLocalHome");
            objCrossHome = (HLCCrossCountryLocalHome)objCross;
            
            Object objCogg = jndiContext.lookup("HLCCogginsDetailLocalHome");
            objCoggHome = (HLCCogginsDetailLocalHome)objCogg;
            
            Object objDivLev = jndiContext.lookup("HLCEventTypeLocalHome");
            objDivLevHome = (HLCEventTypeLocalHome)objDivLev;
   
            Object objJud = jndiContext.lookup("HLCJudgeDetailLocalHome");
            objJudgeHome = (HLCJudgeDetailLocalHome)objJud;
    
            Object objDress = jndiContext.lookup("HLCHorseDressageLocalHome");
            objDressHome = (HLCHorseDressageLocalHome)objDress;
            
            Object objOthers = jndiContext.lookup("HLCOtherDetailLocalHome");
            objOthersHome = (HLCOtherDetailLocalHome)objOthers;
            
            Object objRF = jndiContext.lookup("HLCRefundRuleDetailLocalHome");
            objRefundRuleHome  = (HLCRefundRuleDetailLocalHome)objRF;            
            
            Object objTTS = jndiContext.lookup("HLCTentativeTimeScheduleLocalHome");
            objTTSHome = (HLCTentativeTimeScheduleLocalHome)objTTS;            
        }
        System.out.println("MetturDamEntityBean: Instantiated all local bean in  getInitialContext()");
    }

/**
  * @Method Name    :setComments.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String comments.
  * @return         :void value.
  * @throws         :Null.
  */     
    public void setComments(String comments) {
        this.comments = comments;
    }
}
