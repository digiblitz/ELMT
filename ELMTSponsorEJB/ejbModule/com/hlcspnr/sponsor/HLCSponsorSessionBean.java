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
/*  Program Name    : SponsorSessionBean.java
 *  Created Date    : Aug 22, 2006 1:52:29 PM
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

import com.hlcspnr.dao.HLCSponsorDAO;
import com.hlcspnr.plan.HLCSponsorPlanLocal;
import com.hlcspnr.plan.HLCSponsorPlanLocalHome;
import com.hlcspnr.schedule.HLCSponsorPaymentScheduleLocal;
import com.hlcspnr.schedule.HLCSponsorPaymentScheduleLocalHome;
import com.hlcspnr.util.DBHelper;
import com.hlcspnr.util.Debug;
import com.hlcspnr.util.HLCEJBAllJNDIs;
import com.hlcspnr.util.HLCSponsorDetails;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.rmi.*;
import java.util.*;
import java.util.Date;

/**
 * This is the bean class for the SponsorSessionBean enterprise bean.
 * Created Aug 22, 2006 1:52:29 PM
 * @author suresh
 */
public class HLCSponsorSessionBean implements SessionBean, HLCSponsorSessionRemoteBusiness {
    private SessionContext context;
    private InitialContext ic;
    private Connection con;
    private HLCKrishnaRajaSaragEntityLocalHome home;
    private HLCKrishnaRajaSaragEntityLocal remote;
    
    private HLCSponsorPlanLocalHome objPlanHome;
    private HLCSponsorPlanLocal objPlanRemote;
    
    private HLCSponsorPaymentScheduleLocalHome objPayScheduleHome;
    private HLCSponsorPaymentScheduleLocal objPayScheduleRemote;
    
    
    private String sponsorId;
    private String planId;
    private String scheduleId;
    private String planName;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    // </editor-fold>
    
/**
  * @Method Name    :ejbCreate.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void  value.
  * @throws         :CreateException.
  */
    public void ejbCreate() throws CreateException {
        try{
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup("HLCKrishnaRajaSaragEntityLocalHome");
            home = (HLCKrishnaRajaSaragEntityLocalHome)obj;
            
            Object objPlan = jndiContext.lookup("HLCSponsorPlanLocalHome");
            objPlanHome = (HLCSponsorPlanLocalHome)objPlan;
            
            Object objPaySchedule = jndiContext.lookup("HLCSponsorPaymentScheduleLocalHome");
            objPayScheduleHome = (HLCSponsorPaymentScheduleLocalHome)objPaySchedule;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
/**
  * @Method Name    :createPaymentSchedule.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId,String scheduleTypeId, Date dueDate,float dueAmount , boolean continueStatus, String frequencyRate, String paymentStatus , Vector specialBil.
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
    public boolean createPaymentSchedule(String sponsorId,String scheduleTypeId,
        Date dueDate,float dueAmount , boolean continueStatus,
        String frequencyRate, String paymentStatus , Vector specialBil) throws RemoteException{
         Debug.print("SponsorSessionBean createPaymentSchedule");
        boolean result = false;
        try{
            objPayScheduleRemote = objPayScheduleHome.create(sponsorId,scheduleTypeId, dueDate,
                    dueAmount , continueStatus, frequencyRate,  paymentStatus , specialBil);
            result =  true;
        }
        catch(Exception exp){
            // throw new EJBException("CreateEvent Details: " + exp.getMessage());
             exp.printStackTrace();
        }
        finally{
            return result;
        }
    }
    
/**
  * @Method Name    :createSponsorRequest.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :SponsorDetails objSponsor.
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
    public boolean createSponsorRequest(HLCSponsorDetails objSponsor) throws RemoteException{
        Debug.print("SponsorSessionBean createSponsorRequest");
        boolean result = false;
        try{
            remote = home.create(objSponsor);
            result = true;
        }
        catch(Exception exp){
             //throw new EJBException("CreateEvent Details: " + exp.getMessage());
             exp.printStackTrace();
        }
         finally{
            return result;
        }
    }  
    
/**
  * @Method Name    :createPlan.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planName , String planDescription , String planAmount.
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
     public boolean createPlan(String planName , String planDescription , String planAmount) throws RemoteException{ 
        Debug.print("SponsorSessionBean createPlan");
        boolean result = false;
        try{
            if (planNameExists(planName) == false) {
                Debug.print("This name is already Exists");
                result = false;
               // throw new EJBException("PlanName Already Exist" + planName);
            }
            else{
                Debug.print("SponsorSessionBean Started..");
                objPlanRemote = objPlanHome.create(planName,planDescription,planAmount);
                result = true;
            }
        }
        catch(Exception exp){
             //throw new EJBException("CreateEvent Details: " + exp.getMessage());
              exp.printStackTrace();
        }
         finally{
            return result;
        }   
    }  

/**
  * @Method Name    :getPlanDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planName , String planDescription , String planAmount.
  * @return         :String[]  value.
  * @throws         :RemoteException, FinderException.
  */
    public String[] getPlanDetails(String planId) throws RemoteException,FinderException {
        Debug.print("SponsorSessionBean getPlanDetails");
        if (planExists(planId) == false) {
            throw new EJBException("Plan ID Not Exists" + planId);
        }

       objPlanRemote = objPlanHome.findByPrimaryKey(planId);
       String pId =  objPlanRemote.getPlanId();
       String planName =  objPlanRemote.getPlanName();
       String planDesc =  objPlanRemote.getPlanDescription();
       String planAmount =  objPlanRemote.getPlanAmount();
       String plan [] = {pId,planName,planDesc,planAmount};
       return plan;
    }
      
   
    
   /* public boolean paymentScheduleStatusChange(String scheduleId, String status) throws RemoteException{
        Debug.print(" SponsorSessionBean paymentScheduleStatusChange:" + scheduleId);
        Debug.print(" SponsorSessionBean paymentScheduleStatusChange status:" + status);
        boolean result = false;
        if (scheduleId == null ) {
            throw new EJBException("Schedule ID can't be empty");
        }
        if (scheduleExists(scheduleId) == false) {
            throw new EJBException("Schedule ID Not Exists" + scheduleId);
        }
        else{
            Debug.print(" SponsorSessionBean paymentScheduleStatusChange status After schedule ID checking:" + status);
            objPayScheduleRemote.setPaymentStatus(status);
            Debug.print("Payment Schedule sucessfully updated.. for Schedule ID:" + scheduleId);
            result = true;
        }
        return result;
    }
    */
    
/**
  * @Method Name    :paymentScheduleStatusChange.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleId, String status.
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
     public boolean paymentScheduleStatusChange(String scheduleId, String status) throws RemoteException{
        Debug.print(" SponsorSessionBean paymentScheduleStatusChange:" + scheduleId);
        Debug.print(" SponsorSessionBean paymentScheduleStatusChange status:" + status);
        boolean result = false;
       /* if (scheduleId == null ) {
            throw new EJBException("Schedule ID can't be empty");
        }
        if (scheduleExists(scheduleId) == false) {
            throw new EJBException("Schedule ID Not Exists" + scheduleId);
        }
        else{
            Debug.print(" SponsorSessionBean paymentScheduleStatusChange status After schedule ID checking:" + status);
            objPayScheduleRemote.setPaymentStatus(status);
            Debug.print("Payment Schedule sucessfully updated.. for Schedule ID:" + scheduleId);
            result = true;
        }
        **/
        result = new HLCSponsorDAO().statusChange(scheduleId, status);
        Debug.print(" SponsorSessionBean paymentScheduleStatusChange status:" + result);
        return result;
    }
    
    
/**
  * @Method Name    :getPaymentSchedule_old.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :Collection  value.
  * @throws         :RemoteException, FinderException.
  */ 
    private Collection getPaymentSchedule_old(String sponsorId) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean getPaymentSchedule");
        Vector paymentList = new Vector();
        Collection result = objPayScheduleHome.findBySponsorId(sponsorId);
        Iterator e = result.iterator();
        while(e.hasNext()){
           HLCSponsorPaymentScheduleLocal localPaymentRemote = (HLCSponsorPaymentScheduleLocal)e.next();
           Debug.print("This is form:" +  localPaymentRemote.getScheduleId());
           String scheduleId =  localPaymentRemote.getScheduleId();
           sponsorId =  localPaymentRemote.getSponsorId();
           String scheduleTypeId =  localPaymentRemote.getScheduleTypeId();
           Date dueDate =  localPaymentRemote.getDueDate();
           Debug.print("SponsorSessionBean getPaymentSchedule date:" + dueDate);
           Debug.print("SponsorSessionBean getPaymentSchedule Amount:" + localPaymentRemote.getDueAmount());
           float dueAmount =  localPaymentRemote.getDueAmount();
           Debug.print("SponsorSessionBean getPaymentSchedule dueAmount:" + dueAmount);
           boolean continueStatus = localPaymentRemote.isContinueStatus();
           String frequencyRate = localPaymentRemote.getFrequencyRate();
           String paymentStatus = localPaymentRemote.getPaymentStatus();
           Debug.print("SponsorSessionBean getPaymentSchedule dueAmount:" + dueAmount);
           ArrayList scheduleList = new ArrayList();
           scheduleList.add(scheduleId);
           scheduleList.add(sponsorId);
           scheduleList.add(scheduleTypeId);
           scheduleList.add(dueDate);
           scheduleList.add(Float.valueOf(dueAmount));
           scheduleList.add(Boolean.toString(continueStatus));
           scheduleList.add(frequencyRate);
           scheduleList.add(paymentStatus);
           paymentList.add(scheduleList);
           Debug.print("SponsorSessionBean getPaymentSchedule sucessfully comes from Sponsor Scedule:" + Float.valueOf(dueAmount));
        }
        return paymentList;
    }
    
    //=============================================Getting Payment Scedule Details =========================================
/**
  * @Method Name    :getPaymentSchedule.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :Collection  value.
  * @throws         :RemoteException.
  */ 
    public Collection getPaymentSchedule(String sponsorId) throws RemoteException{
        Debug.print("SponsorSessionBean.getPaymentSchedule():");
        Vector sponsorScheduleList = new Vector();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement =
                "select  schedule_id, sponsor_id, schedule_type_id, due_date," +
                " due_amount, continue_status, frequency_rate , payment_status  from " + 
                DBHelper.USEA_SPNR_SCHE_DETAILS  + " where sponsor_id = ? order by due_date ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,sponsorId);
            rs = prepStmt.executeQuery();
            while(rs.next()){
               ArrayList scheduleList = new ArrayList();
               String scheduleId =   rs.getString(1);
               String sponsorIdVal =   rs.getString(2);
               String scheduleTypeId =   rs.getString(3);
               Date dueDate =   rs.getDate(4);
               float dueAmount =   rs.getFloat(5);
               boolean continueStatus =  rs.getBoolean(6);
               String frequencyRate =  rs.getString(7);
               String paymentStatus =  rs.getString(8);
           
               scheduleList.add(scheduleId);
               scheduleList.add(sponsorId);
               scheduleList.add(scheduleTypeId);
               scheduleList.add(dueDate);
               scheduleList.add(Float.valueOf(dueAmount));
               scheduleList.add(Boolean.toString(continueStatus));
               scheduleList.add(frequencyRate);
               scheduleList.add(paymentStatus);
               scheduleList.add(scheduleList);
               sponsorScheduleList.add(scheduleList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("SponsorSessionBean.getPaymentSchedule():" + sponsorId);
        } 
        catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in SponsorSessionBean.getPaymentSchedule():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in SponsorSessionBean.getPaymentSchedule():" + e.getMessage());
        }
        return sponsorScheduleList;
    }
    
/**
  * @Method Name    :getPaymentScheduleDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleId.
  * @return         :Vector  value.
  * @throws         :RemoteException, FinderException.
  */ 
    public Vector getPaymentScheduleDetails(String scheduleId) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean getPaymentScheduleDetails" + scheduleId);
        if (scheduleId == null ) {
            throw new EJBException("Schedule ID can't be empty");
        }
        if (scheduleExists(scheduleId) == false) {
            throw new EJBException("Schedule ID Not Exists:" + scheduleId);
        }
        Debug.print("SponsorSessionBean getPaymentScheduleDetails" + scheduleId);
        //objPayScheduleRemote = objPayScheduleHome.findByPrimaryKey(scheduleId);
        scheduleId =  objPayScheduleRemote.getScheduleId();
        sponsorId =  objPayScheduleRemote.getSponsorId();
        String scheduleTypeId =  objPayScheduleRemote.getScheduleTypeId();
        Date dueDate =  objPayScheduleRemote.getDueDate();
        float dueAmount =  objPayScheduleRemote.getDueAmount();
        boolean continueStatus = objPayScheduleRemote.isContinueStatus();
        String frequencyRate = objPayScheduleRemote.getFrequencyRate();
        String paymentStatus = objPayScheduleRemote.getPaymentStatus();
          
        Vector scheduleList = new Vector();
        scheduleList.add(scheduleId);
        scheduleList.add(sponsorId);
        scheduleList.add(scheduleTypeId);
        scheduleList.add(dueDate);
        scheduleList.add(Float.valueOf(dueAmount));
        scheduleList.add(Boolean.toString(continueStatus));
        scheduleList.add(frequencyRate);
        scheduleList.add(paymentStatus);
        return scheduleList;
    }

/**
  * @Method Name    :getPlanName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId.
  * @return         :String  value.
  * @throws         :RemoteException, FinderException.
  */ 
     public String getPlanName(String planId) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean getPlanName");
        if (planExists(planId) == false) {
            throw new EJBException("Plan ID Not Exists" + planId);
        }
       objPlanRemote = objPlanHome.findByPrimaryKey(planId);
       String planName =  objPlanRemote.getPlanName();
       return planName;
     }
    
 /**
  * @Method Name    :getAllPlanDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Collection value.
  * @throws         :RemoteException, FinderException.
  */ 
    public Collection getAllPlanDetails( ) throws RemoteException,FinderException {
        Debug.print("SponsorSessionBean getPlanDetails");
        Vector v = new Vector();
        try{
        Collection result = objPlanHome.findByAll();
        Iterator e = result.iterator();
            while(e.hasNext()){
               HLCSponsorPlanLocal localRemote = (HLCSponsorPlanLocal)e.next();
               Debug.print("SponsorSessionBean getPlanDetails:" +  localRemote.getPlanName());
               String pId =  localRemote.getPlanId();
               String planName =  localRemote.getPlanName();
               String planDesc =  localRemote.getPlanDescription();
               String planAmount =  localRemote.getPlanAmount();
               String plan [] = {pId,planName,planDesc,planAmount};
               v.add(plan);
            }
        }
        catch(Exception e){
            Debug.print("Exception while invoking getAllPlanDetails()" + e.getMessage());
        }
        return v;
    }
    
 /**
  * @Method Name    :getScheduleTypeMaster.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Vector value.
  * @throws         :SQLException.
  */ 
       public Vector getScheduleTypeMaster() throws SQLException  {
        Debug.print("SponsorPaymentScheduleBean getScheduleType");
        makeConnection();
        Vector v = new Vector();
        String selectStatement = "select schedule_type_id, schedule_type_name from " + DBHelper.USEA_SPNR_SCHE_MASTER ;
        PreparedStatement prepSelect = con.prepareStatement(selectStatement);
        ResultSet rs = prepSelect.executeQuery();
        while(rs.next()){
            String scheduleTypeId = rs.getString(1);
            String scheduleTypeName = rs.getString(2);
            String scheduleType[] = {scheduleTypeId,scheduleTypeName};
            v.add(scheduleType);
        }
        rs.close();
        prepSelect.close();
        releaseConnection();
        return v;
    }

 /**
  * @Method Name    :getAllRequestStatusSponsors.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Vector value.
  * @throws         :RemoteException,FinderException.
  */ 
    public Vector getAllRequestStatusSponsors(String requestStatus) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean getAllRequestStatusSponsors");
        Vector v = new Vector();
        try{
            ArrayList result =  (ArrayList)home.findByRequestStatus(requestStatus);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
            else{
                v = null;
            }
        }
        catch(Exception e){
            Debug.print("Exception in getAllRequestStatusSponsors:" + e);
        }
        return v;
    }
    
/**
  * @Method Name    :getAllRequestStatusSponsorsSalesPerson.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String salesPersonId, String requestStatus.
  * @return         :Vector value.
  * @throws         :RemoteException,FinderException.
  */   
    public Vector getAllRequestStatusSponsorsSalesPerson(String salesPersonId, String requestStatus) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean getAllRequestStatusSponsorsSalesPerson");
        Vector v = new Vector();
        try{
            ArrayList result =  (ArrayList)home.findByAllRequestStatusSponsors(salesPersonId, requestStatus);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
            else{
                v = null;
            }
        }
        catch(Exception e){
            Debug.print("Exception in getAllRequestStatusSponsorsSalesPerson:" + e);
        }
        return v;
    }

/**
  * @Method Name    :getAllPendingSponsorsForAdmin.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :Vector value.
  * @throws         :RemoteException,FinderException.
  */
    public Vector getAllPendingSponsorsForAdmin() throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean getAllPendingSponsorsForAdmin");
        Vector v = new Vector();
        try{
            ArrayList result =  (ArrayList)home.findByPendingRequestForAdmin();
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
            else{
                v = null;
            }
        }
        catch(Exception e){
            Debug.print("Exception in getAllPendingSponsorsForAdmin:" + e);
        }
        return v;
    }
    
/**
  * @Method Name    :getByPlanId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId.
  * @return         :Vector value.
  * @throws         :RemoteException.
  */
    public Vector getByPlanId(String planId) throws RemoteException{
        Debug.print("SponsorSessionBean getByPlanId");
        Vector v = new Vector();
        //v = null;
        try{
            ArrayList result =  (ArrayList)home.findByPlanId(planId);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception :" + e);
        }
        return v;
    }
    
/**
  * @Method Name    :getMySposorDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String userId.
  * @return         :Vector value.
  * @throws         :RemoteException.
  */
     public Vector getMySposorDetails(String userId) throws RemoteException{
        Debug.print("SponsorSessionBean getMySposorDetails" + userId);
        Vector v = new Vector();
        //v = null;
        try{
            ArrayList result =  (ArrayList)home.findBySponsor(userId);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                   Debug.print("SponsorSessionBean getMySposorDetails in While loop:" + userId);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception :" + e);
        }
        return v;
     }
    
/**
  * @Method Name    :getByCompanyName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String companyName.
  * @return         :Vector value.
  * @throws         :RemoteException.
  */
    public Vector getByCompanyName(String companyName) throws RemoteException{
        Debug.print("SponsorSessionBean getByPlanId");
        Vector v = new Vector();
        v = null;
        try{
            ArrayList result =  (ArrayList)home.findByCompanyName(companyName);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception :" + e);
        }
        return v;   
    }

/**
  * @Method Name    :getBySalesPersonId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String salesPersonId.
  * @return         :Vector value.
  * @throws         :RemoteException.
  */
    public Vector getBySalesPersonId(String salesPersonId) throws RemoteException{
        Debug.print("SponsorSessionBean getByPlanId");
        Vector v = new Vector();
        v = null;
        try{
            ArrayList result =  (ArrayList)home.findBySalesPerson(salesPersonId);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception :" + e);
        }
        return v;   
    }
    
/**
  * @Method Name    :getByDates.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Date fromDate, Date toDate.
  * @return         :Vector value.
  * @throws         :RemoteException.
  */
    public Vector getByDates(Date fromDate, Date toDate) throws RemoteException{
        Debug.print("SponsorSessionBean getByDates");
        Vector v = new Vector();
        v = null;
        if(fromDate==null || toDate==null){
            return null;
        }
        try{
            ArrayList result =  (ArrayList)home.findByDates(fromDate,toDate);
            if(result!=null){
                Iterator e = result.iterator();
                while(e.hasNext()){
                   HLCKrishnaRajaSaragEntityLocal localSpRemote = (HLCKrishnaRajaSaragEntityLocal)e.next();
                   HLCSponsorDetails objSponsor = localSpRemote.getSponsorDetails();
                   v.add(objSponsor);
                }
            }
        }
        catch(Exception e){
            Debug.print("Exception :" + e);
        }
        return v;   
    }
    
/**
  * @Method Name    :editSponsorPlan.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId, String planName ,String planDescription ,String planAmount.
  * @return         :boolean value.
  * @throws         :RemoteException, FinderException.
  */   
     public boolean editSponsorPlan(String planId, String planName ,String planDescription ,String planAmount) throws RemoteException, FinderException{
           Debug.print(" SponsorSessionBean editSponsorPlan Plan ID:" + planId);
           boolean result = false; 
           if (planId == null ) {
                throw new EJBException("Sponsor ID can't be empty");
            }
            if (editPlanNameExists(planId,planName) == false) {
                Debug.print("SponsorSessionBean editSponsorPlan This name is already Exists");
                result = false;
               // throw new EJBException("PlanName Already Exist" + planName);
            }
            else{
                if (planExists(planId) == false) {
                    Debug.print("PlanId is not Exists");
                    result = false;
                   // throw new EJBException("Plan ID Not Exists" + planId);
                }
                else{
                    objPlanRemote.setPlanName(planName);
                    objPlanRemote.setPlanDescription(planDescription);
                    objPlanRemote.setPlanAmount(planAmount);
                    result = true;
                }
            }
            return result;
       }
   
/**
  * @Method Name    :deleteSponsorPlan.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId.
  * @return         :boolean value.
  * @throws         :RemoteException, FinderException.
  */  
    public boolean deleteSponsorPlan(String planId) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean deleteSponsorPlan");
        Debug.print("Plan ID:" + planId);
        boolean result = false;
        if (planId == null) {
            throw new EJBException("Plan ID can't be empty");
        }
        if (planExists(planId) == false) {
            throw new EJBException("Plan ID Not Exists" + planId);
        }
        try {
            objPlanRemote.remove();
            result = true;
        } catch (Exception ex) {
              throw new EJBException("removeSponsor: " + ex.getMessage());
        }
        finally{
            return result;
        }   
    }
    
/**
  * @Method Name    :salesPersonUpdate.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId, Date contractStartDate,Date contractEndDate, String negoAmount, String filePath.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */ 
    public void salesPersonUpdate(String sponsorId, Date contractStartDate,Date contractEndDate, String negoAmount, String filePath) throws RemoteException,FinderException{
        Debug.print("Sponsor ID:" + sponsorId);
        if (sponsorId == null ) {
            throw new EJBException("Sponsor ID can't be empty");
        }
        if (eventExists(sponsorId) == false) {
            throw new EJBException("Sponsor ID does not Exists" + sponsorId);
        }

        //remote.setSalesPersonId(salesPersonId);
        remote.setContractStartDate(contractStartDate);
        remote.setcontractEndDate(contractEndDate);
        remote.setSponsorAmount(negoAmount);
        remote.setFilePath(filePath);
        remote.setRequestStatus("Active");
    }
    
/**
  * @Method Name    :salesPersonAssign.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId,String salesPersonId.
  * @return         :void value.
  * @throws         :RemoteException, FinderException.
  */
    public void salesPersonAssign(String sponsorId,String salesPersonId) throws RemoteException,FinderException{
        Debug.print("Sponsor ID:" + sponsorId);
        if (sponsorId == null ) {
            throw new EJBException("Sponsor ID can't be empty");
        }
        if (eventExists(sponsorId) == false) {
            throw new EJBException("Sponsor ID does not Exists" + sponsorId);
        }
        remote.setSalesPersonId(salesPersonId);
    }
    
/**
  * @Method Name    :statusUpdation.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId, String requestStatus.
  * @return         :boolean value.
  * @throws         :RemoteException, FinderException.
  */
    public boolean statusUpdation(String sponsorId, String requestStatus) throws RemoteException,FinderException{
        Debug.print("statusUpdation Sponsor ID:" + sponsorId);
        boolean result = false;
        if (sponsorId == null ) {
            result = false;
            throw new EJBException("Sponsor ID can't be empty");
        }
        if (eventExists(sponsorId) == false) {
            result = false;
            throw new EJBException("Sponsor ID does not Exists" + sponsorId);
        }
        else{
            remote.setRequestStatus(requestStatus);
            result = true;
        }
        return result;
    }
    
/**
  * @Method Name    :statusUpdationForReject.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId, String requestStatus.
  * @return         :boolean value.
  * @throws         :RemoteException, FinderException.
  */
    public boolean statusUpdationForReject(String sponsorId, String requestStatus) throws RemoteException,FinderException{
        Debug.print("statusUpdationForReject Sponsor ID:" + sponsorId);
        boolean result = false;
        if (sponsorId == null ) {
            result = false;
            throw new EJBException("Sponsor ID can't be empty");
        }
        if (eventExists(sponsorId) == false) {
            result = false;
            throw new EJBException("Sponsor ID does not Exists" + sponsorId);
        }
        else{
            remote.setSalesPersonId("");
            remote.setRequestStatus(requestStatus);
            result = true;
        }
        return result;
    }
    
/**
  * @Method Name    :getSponsorDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :SponsorDetails value.
  * @throws         :RemoteException.
  */
    public HLCSponsorDetails getSponsorDetails(String sponsorId) throws RemoteException ,FinderException{
            Debug.print("SponsorSessionBean getSponsorDetails");
            HLCSponsorDetails result;
            if (sponsorId == null) {
                throw new EJBException("Sponsor ID can't be empty");
            }

            if (eventExists(sponsorId) == false) {
                throw new EJBException("Sponsor ID Not Exists" + sponsorId);
            }
            result = remote.getSponsorDetails();
            return result;
        }

/**
  * @Method Name    :editSponsorRequest.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :SponsorDetails objSponsor.
  * @return         :SponsorDetails value.
  * @throws         :RemoteException, FinderException.
  */
    public void editSponsorRequest(HLCSponsorDetails objSponsor) throws RemoteException, FinderException{
           Debug.print("Sponsor ID:" + objSponsor.getSponsorId());
            if (objSponsor == null ) {
                throw new EJBException("Sponsor ID can't be empty");
            }
            if (eventExists(objSponsor.getSponsorId()) == false) {
                throw new EJBException("Sponsor ID does not Exists" + sponsorId);
            }
            
            remote.setUserId(objSponsor.getUserId());
            remote.setCompanyName(objSponsor.getCompanyName());
            remote.setComments(objSponsor.getComments());
            remote.setPlanId(objSponsor.getPlanId());
            //remote.setStatusId(objSponsor.getStatusId());
            //remote.setSalesPersonId(objSponsor.getSalesPersonId());
            //remote.setContractDtartDate(objSponsor.getContractDtartDate());
            //remote.setcontractEndDate(objSponsor.getcontractEndDate());
       }

/**
  * @Method Name    :deleteSponsor.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :boolean value.
  * @throws         :RemoteException, FinderException.
  */
    public boolean deleteSponsor(String sponsorId) throws RemoteException,FinderException{
        Debug.print("SponsorSessionBean removeEvent");
        Debug.print("Sponsor ID:" + sponsorId);
        boolean result = false;
        if (sponsorId == null) {
            throw new EJBException("Sponsor ID can't be empty");
        }
        if (eventExists(sponsorId) == false) {
            throw new EJBException("Sponsor ID Not Exists" + sponsorId);
        }
        try {
            remote.remove();
            result =true;
        } catch (Exception ex) {
              throw new EJBException("removeSponsor: " + ex.getMessage());
        }
        finally{
            return result;
        }   
    }

/**
  * @Method Name    :eventExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String sponsorId.
  * @return         :boolean value.
  * @throws         :Null.
  */
    private boolean eventExists(String sponsorId) throws FinderException {
        Debug.print("SponsorSessionBean sponsorExists");
        if (sponsorId != this.sponsorId) {
            try {
                remote = home.findByPrimaryKey(sponsorId);
                this.sponsorId = sponsorId;
            } catch (Exception ex) {
                return false;
            }
        }
        Debug.print("Sponsor Key Exist" + sponsorId);
        return true;
    }
    
    
    StringBuffer sbss = new StringBuffer();
        
 /**
  * @Method Name    :scheduleExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String scheduleId.
  * @return         :boolean value.
  * @throws         :Null.
  */
    private boolean scheduleExists(String scheduleId) throws FinderException {
        Debug.print("SponsorSessionBean scheduleExists" + scheduleId);
        boolean   result  = false;
        if (scheduleId != this.scheduleId) {
            Debug.print("SponsorSessionBean scheduleExists inside condition:" + scheduleId);
            try {
                objPayScheduleRemote = objPayScheduleHome.findByPrimaryKey(scheduleId);
                Debug.print("SponsorSessionBean scheduleExists inside objPayScheduleRemote:" + objPayScheduleRemote);
                this.scheduleId = scheduleId;
                result = true;
            } catch (Exception ex) {
                Debug.print("Schedule does not  Exist" + scheduleId);
            }
        }
        Debug.print("Schedule is Exist" + result);
        return result;
    }

/**
  * @Method Name    :planNameExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planName.
  * @return         :boolean value.
  * @throws         :Null.
  */    
 private boolean planNameExists(String planName) throws FinderException {
        Debug.print("SponsorSessionBean planNameExists");
        boolean result = true;
        if (!(planName.equalsIgnoreCase(this.planName))) {
            try {
               ArrayList  pName = (ArrayList) objPlanHome.findByPlanName(planName);
               Iterator e = pName.iterator();
               while(e.hasNext()){
                   HLCSponsorPlanLocal objPlanLocalRemote = (HLCSponsorPlanLocal)e.next();
                   String plName = objPlanLocalRemote.getPlanName();
                   if(plName.equalsIgnoreCase(planName)){
                       this.planName = planName;
                       result = false;
                       Debug.print("Inside  planNameExists:" + plName);
                       break;
                   }
               }
            } catch (Exception ex) {
                 result = false;
                 Debug.print("Exception:" + ex.getMessage());
            }
        }
        else{
             result = false;
        }
        Debug.print("Plan Name Outer Most" + result);
        return result;
    }
 
 /**
  * @Method Name    :editPlanNameExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId, String planName.
  * @return         :boolean value.
  * @throws         :Null.
  */  
  private boolean editPlanNameExists(String planId, String planName) throws FinderException{
        Debug.print("SponsorSessionBean editPlanNameExists");
        boolean result = true;
        Debug.print("SponsorSessionBean editPlanNameExists planName:" + planName);
        Debug.print("SponsorSessionBean editPlanNameExists planName:" + this.planName);
        if (!(planName.equalsIgnoreCase(this.planName))) {
            try {
               ArrayList  pName = (ArrayList) objPlanHome.findByEditPlanName(planId,planName);
               Iterator e = pName.iterator();
               while(e.hasNext()){
                   HLCSponsorPlanLocal objPlanLocalRemote = (HLCSponsorPlanLocal)e.next();
                   String plName = objPlanLocalRemote.getPlanName();
                   if(plName.equalsIgnoreCase(planName)){
                       this.planName = planName;
                       result = false;
                       Debug.print(" editPlanNameExists Inside  editPlanNameExists:" + plName);
                       break;
                   }
               }
            } catch (Exception ex) {
                 result = false;
                 Debug.print("Exception:" + ex.getMessage());
            }
        }
        else{
             result = false;
        }
        Debug.print("editPlanNameExists Plan Name Outer Most" + result);
        return result;
    }
     
/**
  * @Method Name    :planExists.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String planId.
  * @return         :boolean value.
  * @throws         :Null.
  */  
    private boolean planExists(String planId)  throws FinderException{
        Debug.print("SponsorSessionBean sponsorExists");
        if (planId != this.planId) {
            try {
                objPlanRemote = objPlanHome.findByPrimaryKey(planId);
                this.planId = planId;
            } catch (Exception ex) {
                return false;
            }
        }
        Debug.print("Sponsor Key Exist" + planId);
        return true;
    }

/**
  * @Method Name    :makeConnection.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :Null.
  * @return         :void value.
  * @throws         :Null.
  */ 
     private void makeConnection() {
            Debug.print("SponsorSessionBean Event: makeConnection");
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
            Debug.print("SponsorSessionBean releaseConnection");
            try {
                con.close();
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
        System.out.println("SponsorSessionBean: This is from getInitialContext()");
        return ic;
    }
}