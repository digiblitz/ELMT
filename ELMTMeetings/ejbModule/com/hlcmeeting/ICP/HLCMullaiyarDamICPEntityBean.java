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
package com.hlcmeeting.ICP;

import com.hlcmeeting.util.DBHelper;
import com.hlcmeeting.util.Debug;
import com.hlcmeeting.util.HLCInstructorDetails;
import com.hlcmeeting.util.HLCInstructorPriceMaster;
import com.hlcmeeting.util.HLCPaymentDetails;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the MullaiyarDamICPEntityBean enterprise bean.
 * Created Sep 1, 2006 5:54:24 PM
 * @author harmohan
 */
public class HLCMullaiyarDamICPEntityBean implements EntityBean, HLCMullaiyarDamICPEntityLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    
    /*======================Instructor Price Master=============================*/
    private String priceId;
    private String eventRegistrationTypeId;
    private String noOfDays = "0";
    private String price;
    private String eventRegistrationTypeName;
    
    /*======================Instructor Details=============================*/
    private String icpMeetingId;
    private String assesmentLevel;
    private String assesmentDate;
    private String useaAreaId;
    private String areaName;
    private String location;
    private String instructorId;
    private String hostMemberId;
    private String shippingTypeId;
    private String assessor;
    private String facilities;
    private String landOwnerName;
    private String landOwnerBusinessName;
    private String landOwnerFax;
    private String landOwnerEmail;
    private String landOwnerAddress;
    private String landOwnerCity;
    private String landOwnerState;
    private String landOwnerZip;
    private String landOwnerPhone;
    private String addDate;
    private String approvedBy;
    private String approvedDate;
    private String postingType;
    private String requestStatus;
    private String landOwnerCountry;
    private String comments;
    private String email;
    
    /**=================HLCPaymentDetails Variable =====================*/
    private String paymentId;
    private String userId;
    private String userTypeId;
    private String ccName;
    private String ccType;
    private long ccNumber;
    private int ccExpMonth;
    private int ccExpYear;
    private int ccCvvid;
    private String bankName;
    private Date checkDate;
    private long checkNumber;
    private double amount;
    private Date paymentDate;
    private String checkName;
    private String paymentStatus;
   
    
    /*============================Object Creation========================*/
    HLCInstructorDetails objInstDetail = new HLCInstructorDetails();
    HLCInstructorPriceMaster objInstPriceMas = new HLCInstructorPriceMaster();
    HLCPaymentDetails objPayment = new HLCPaymentDetails();
    
    /**==================Setter Method for Instructor Details=======================*/
    public void setComments(String comments){this.comments = comments; }
    public void setLandOwnerCountry(String landOwnerCountry){this.landOwnerCountry = landOwnerCountry; }
    public void setNoOfDays(String noOfDays){this.noOfDays = noOfDays; }
    public void setIcpMeetingId(String icpMeetingId) {this.icpMeetingId = icpMeetingId;}
    public void setAssesmentLevel(String assesmentLevel) {this.assesmentLevel = assesmentLevel;}
    public void setAssesmentDate(String assesmentDate) {this.assesmentDate =  assesmentDate;}
    public void setUseaAreaId(String useaAreaId) {this.useaAreaId =  useaAreaId; }
    public void setAreaName(String areaName) {this.areaName =  areaName; }
    public void setLocation(String location) {this.location =  location;}
    public void setInstructorId(String instructorId) {this.instructorId = instructorId;}
    public void setHostMemberId(String hostMemberId) {this.hostMemberId = hostMemberId;}
    public void setShippingTypeId(String shippingTypeId) {this.shippingTypeId = shippingTypeId;}
    public void setAssessor(String assessor) {this.assessor = assessor;}
    public void setFacilities(String facilities) { this.facilities = facilities;}
    public void setLandOwnerName(String landOwnerName) {this.landOwnerName = landOwnerName;}
    public void setLandOwnerAddress(String landOwnerAddress) {this.landOwnerAddress = landOwnerAddress;}
    public void setLandOwnerBusinessName(String landOwnerBusinessName) {this.landOwnerBusinessName = landOwnerBusinessName; }
    public void setLandOwnerFax(String landOwnerFax){this.landOwnerFax = landOwnerFax; }
    public void setLandOwnerEmail(String landOwnerEmail) {this.landOwnerEmail = landOwnerEmail; }
    public void setLandOwnerCity(String landOwnerCity) { this.landOwnerCity = landOwnerCity; }
    public void setLandOwnerState(String landOwnerState) {this.landOwnerState = landOwnerState;}
    public void setLandOwnerZip(String landOwnerZip) {this.landOwnerZip = landOwnerZip;}
    public void setLandOwnerPhone(String landOwnerPhone) {this.landOwnerPhone = landOwnerPhone;}
    public void setAddDate(String addDate) {this.addDate = addDate;}
    public void setApprovedBy(String approvedBy) {this.approvedBy = approvedBy;}
    public void setApprovedDate(String approvedDate) {this.approvedDate = approvedDate;}
    public void setPostingType(String postingType) { this.postingType = postingType;}
    public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus;}
    
    /**================Setter Method For Instructor PriceMaster====================================*/
    public void setPriceId(String PriceId) {this.priceId = priceId; }
    public void setEventRegistrationTypeId(String EventRegistrationTypeId)  {this.eventRegistrationTypeId = eventRegistrationTypeId;}
   // public void setNoOfDays(String noOfDays) {this.noOfDays =  noOfDays;}
    public void setPrice(String price)   { this.price =  price; }
    public void setEventRegistrationTypeName(String eventRegistrationTypeName) 
    {this.eventRegistrationTypeName= eventRegistrationTypeName; }

    /**==================Setter Method for Payment Details==================*/
    public void setPaymentId(String paymentId) {this.paymentId = paymentId;}
    public void setUserId(String userId) {this.userId = userId;}
    public void setUserTypeId(String userTypeId) {this.userTypeId = userTypeId;}
    public void setCcName(String ccName) {this.ccName = ccName;}
    public void setCcType(String ccType) {this.ccType = ccType;}
    public void setCcNumber(long ccNumber) {this.ccNumber = ccNumber;}
    public void setCcExpMonth(int ccExpMonth) {this.ccExpMonth = ccExpMonth;}
    public void setCcExpYear(int ccExpYear) {this.ccExpYear = ccExpYear;}
    public void setCcCvvid(int ccCvvid) {this.ccCvvid = ccCvvid;}
    public void setBankName(String bankName) {this.bankName = bankName;}
    public void setCheckDate(Date checkDate) {this.checkDate = checkDate;}
    public void setCheckNumber(long checkNumber) {this.checkNumber = checkNumber;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setPaymentDate(Date paymentDate) {this.paymentDate = paymentDate;}
    public void setPaymentStatus(String paymentStatus) {this.paymentStatus = paymentStatus;}
    public void setCheckName(String checkName) {this.checkName = checkName;}

    
    
    /*=====================Object Setter & getter Method===========================*/
    
    public HLCInstructorDetails getInstructorDetails(){
        Debug.print("HLCInstructorDetails getInstructorDetails");        
         return new HLCInstructorDetails(paymentId,email,comments,landOwnerCountry,noOfDays,icpMeetingId, assesmentLevel, assesmentDate,areaName,useaAreaId, location, instructorId,
          hostMemberId, shippingTypeId,assessor, facilities, landOwnerName, landOwnerBusinessName, landOwnerAddress, landOwnerCity,
          landOwnerState, landOwnerZip, landOwnerPhone, landOwnerFax,landOwnerEmail, addDate, approvedBy, approvedDate, postingType, requestStatus);
    }
   
    
    public HLCPaymentDetails getPaymentDetails(){
        Debug.print("getMeeAnnualDetails getPaymentDetails");        
        return  objPayment;
    }
    public void setPaymentDetails(HLCPaymentDetails objPayDet){
        Debug.print("getMeeAnnualDetails setPaymentDetails");        
        this.objPayment = objPayment;
    }
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise beans, Web services)
    // TODO Add business methods
    // TODO Add create methods
    /**
     * @see javax.ejb.EntityBean#setEntityContext(javax.ejb.EntityContext)
     */
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    
    /**
     * @see javax.ejb.EntityBean#unsetEntityContext()
     */
    public void unsetEntityContext() {
        context = null;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbLoad()
     */
    public void ejbLoad() {
         Debug.print("MullaiyarDamICPEntityBean ejbLoad");
        try {
            loadICPMeetingDetails();
            Debug.print("Going to loadICPMeetingDetails");
        } catch (Exception ex) {
           throw new EJBException("loadICPMeetingDetails ejbLoad: " + ex.getMessage());
           // ex.printStackTrace();
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        Debug.print("MullaiyarDamICPEntityBean ejbStore");

        try {
            storeICPRequestStatus();
            Debug.print("Going to storeICPRequestStatus");
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    // </editor-fold>
    
    
    public String ejbCreate(HLCInstructorDetails objInstDetail,  HLCPaymentDetails objPayment) throws CreateException {
         
        Debug.print("MullaiyarDamICPEntityBean ejbCreate"); 
        if(objInstDetail==null && objPayment==null){
                throw new EJBException("ejbCreate: objAnnaulDetails or objAnnualReg or objPayment argument is null or empty");
        }
        if (objInstDetail.getNoOfDays() != null){
            this.noOfDays = objInstDetail.getNoOfDays();
        }
        this.landOwnerCountry = objInstDetail.getLandOwnerCountry();
        this.icpMeetingId = objInstDetail.getIcpMeetingId();
        this.assesmentLevel = objInstDetail.getAssesmentLevel();
        this.assesmentDate = objInstDetail.getAssesmentDate();
        this.useaAreaId = objInstDetail.getUseaAreaId();
        this.areaName = objInstDetail.getAreaName();
        this.location = objInstDetail.getLocation();
        this.instructorId = objInstDetail.getInstructorId();
        this.hostMemberId = objInstDetail.getHostMemberId();
        this.shippingTypeId = objInstDetail.getShippingTypeId();
        this.assessor = objInstDetail.getAssessor();
        this.facilities = objInstDetail.getFacilities();
        this.landOwnerName = objInstDetail.getLandOwnerName();
        this.landOwnerBusinessName = objInstDetail.getLandOwnerBusinessName();
        this.landOwnerFax = objInstDetail.getLandOwnerFax();
        this.landOwnerEmail = objInstDetail.getLandOwnerEmail();
        this.landOwnerAddress = objInstDetail.getLandOwnerAddress();
        this.landOwnerCity = objInstDetail.getLandOwnerCity();
        this.landOwnerState = objInstDetail.getLandOwnerState();
        this.landOwnerZip = objInstDetail.getLandOwnerZip();
        this.landOwnerPhone = objInstDetail.getLandOwnerPhone();
        this.addDate = objInstDetail.getAddDate();
        this.approvedBy = objInstDetail.getApprovedBy();
        this.approvedDate = objInstDetail.getApprovedDate();
        this.postingType = objInstDetail.getPostingType();
        this.requestStatus = objInstDetail.getRequestStatus();
        this.comments = objInstDetail.getComments();
        this.paymentId = objInstDetail.getPaymentId();
        this.objPayment = objPayment;
        
        try {
              insertRowInstructorDetails();
              System.out.println("after insertRowAnnualDetails() user Id : "+objPayment.getUserId());
              if (objPayment.getUserId() != null && (objPayment.getUserId().trim()).length() > 0){
                    insertRowPayment();
              }
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCInstructorDetails objInstDetail,  HLCPaymentDetails objPayment) {
        Debug.print("MullaiyarDamICPEntityBean ejbPostCreate"); 
    }
    
    public String ejbCreate( HLCInstructorPriceMaster objInstPriceMas) throws CreateException {
         
        Debug.print("MullaiyarDamICPEntityBean ejbCreate"); 
        if(objInstPriceMas==null ){
                throw new EJBException("ejbCreate: objInstPriceMas argument is null or empty");
        }

        this.priceId = objInstPriceMas.getPriceId();
        this.eventRegistrationTypeId = objInstPriceMas.getEventRegistrationTypeId();
        this.noOfDays = objInstPriceMas.getNoOfDays();
        this.price = objInstPriceMas.getPrice();
        this.eventRegistrationTypeName = objInstPriceMas.getEventRegistrationTypeName();
       
        try {
              insertRowInstructorPriceMaster();
              System.out.println("Inside insertRowInstructorPriceMaster()");
            
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;

    }
    
    public void ejbPostCreate( HLCInstructorPriceMaster objInstPriceMas) {
        Debug.print("MullaiyarDamICPEntityBean ejbPostCreate"); 
    }
    
   public String ejbFindByPrimaryKey(String icpMeetingId) throws FinderException {
        Debug.print("MullaiyarDamICPEntityBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(icpMeetingId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("MullaiyarDamICPEntityBean ejbFindByPrimaryKey ID:" + icpMeetingId);
            return icpMeetingId;
        } else {
            throw new ObjectNotFoundException("Row for id " + icpMeetingId + " not found.");
        }
    }
    
     private boolean selectByPrimaryKey(String icpMeetingId) throws SQLException {
            Debug.print("MullaiyarDamICPEntityBean selectByPrimaryKey");

            makeConnection();
            String selectStatement = "SELECT icp_meeting_id from " + DBHelper.USEA_INSTRUCTOR_DETAILS + " WHERE icp_meeting_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, icpMeetingId);
            ResultSet rs = prepStmt.executeQuery();
            System.out.println("selectByPrimaryKey : Working ");
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return result;
        }
     private void insertRowInstructorDetails() throws SQLException {
        Debug.print("MullaiyarDamICPEntityBean insertRowInstructorDetails");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
        long icp_ID = 0;
        makeConnection();
        try {
            icp_ID = DBHelper.getNextICPMeetingId(con);
           // this.useaAreaId = DBHelper.getAreaId(con,this.areaName);
        }catch (Exception e){
            e.printStackTrace();
        }
        String insertStatement = 
          "insert into " + DBHelper.USEA_INSTRUCTOR_DETAILS + " (icp_meeting_id, assesment_level, assesment_date,usea_area_id,"+
          "location, instructor_id, host_member_id, shipping_type_id,assessor, facilities, land_owner_name, land_owner_business_name,"+
          "land_owner_address, land_owner_city,land_owner_state, land_owner_zip, land_owner_phone, land_owner_fax,land_owner_email,"+
          "approved_by, approved_date, posting_type, land_owner_country,no_of_days,payment_id)"+
                " values ( ? , ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?) ";
        prepStmt = con.prepareStatement(insertStatement);
        Debug.print("Inside the Instructor Details ....\n\n ");
        prepStmt.setLong(1, icp_ID);
        Debug.print("icp_ID :  "+icp_ID);
        prepStmt.setString(2, assesmentLevel);
        Debug.print("assesmentLevel :  "+assesmentLevel);
        prepStmt.setDate(3, java.sql.Date.valueOf(assesmentDate)); 
         Debug.print("assesment_date :  "+assesmentDate);
        prepStmt.setString(4, useaAreaId);
         Debug.print("useaAreaId :  "+useaAreaId);
        prepStmt.setString(5, location);
        Debug.print("location :  "+location);
        prepStmt.setString(6, instructorId);
        Debug.print("instructorId :  "+instructorId);
        prepStmt.setString(7, hostMemberId);
        Debug.print("hostMemberId :  "+hostMemberId);
        prepStmt.setString(8, shippingTypeId);
        Debug.print("shippingTypeId :  "+shippingTypeId);
        prepStmt.setString(9, assessor);
        Debug.print("assessor :  "+assessor);
        prepStmt.setString(10, facilities);
        Debug.print("facilities :  "+facilities);
        prepStmt.setString(11, landOwnerName);
         Debug.print("landOwnerName :  "+landOwnerName);
        prepStmt.setString(12, landOwnerBusinessName);
         Debug.print("landOwnerBusinessName :  "+landOwnerBusinessName);
         prepStmt.setString(13, landOwnerAddress);
          Debug.print("landOwnerAddress :  "+landOwnerAddress);
        prepStmt.setString(14, landOwnerCity);
        Debug.print("landOwnerCity :  "+landOwnerCity);
        prepStmt.setString(15, landOwnerState);
        Debug.print("landOwnerState :  "+landOwnerState);
        prepStmt.setString(16, landOwnerZip);
        Debug.print("landOwnerZip :  "+landOwnerZip);
        prepStmt.setString(17, landOwnerPhone);
        Debug.print("landOwnerPhone :  "+landOwnerPhone);
         prepStmt.setString(18, landOwnerFax);
         prepStmt.setString(19, landOwnerEmail);
        prepStmt.setString(20, approvedBy);
        if ( approvedDate != null){
            prepStmt.setDate(21, java.sql.Date.valueOf(approvedDate));
        }else {
            prepStmt.setDate(21, null);
        }
        Debug.print("approvedDate :  "+approvedDate);
        prepStmt.setString(22, postingType);
         prepStmt.setString(23, landOwnerCountry);
         Debug.print("landOwnerCountry :  "+landOwnerCountry);
        prepStmt.setInt(24, Integer.parseInt(noOfDays));
        Debug.print("noOfDays :  "+noOfDays);
        prepStmt.setString(25,paymentId);
        Debug.print("Payment Id : "+this.paymentId);

        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        System.out.println("Succefully inserted :  "+cnt);
       
        }
     
      private void insertRowInstructorPriceMaster() throws SQLException {
        Debug.print("MullaiyarDamICPEntityBean insertRowInstructorPriceMaster");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
        System.out.println(" Event Registration Type Name  :  "+eventRegistrationTypeName);
        makeConnection();
        try {
            this.eventRegistrationTypeId = DBHelper.getEventRegistrationTypeId(con, eventRegistrationTypeName);
            //this.useaAreaId = DBHelper.getAreaId(con,this.areaName);
        }catch (Exception e){
            e.printStackTrace();
        }
        String insertStatement = 
          "insert into " + DBHelper.USEA_INSTRUCTOR_PRICEMASTER + " (event_registration_type_id, no_of_days,price)"+
          " values ( ? , ? , ? ) ";
        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the Instructor Price Master ....\n\n ");
        
        prepStmt.setString(1, eventRegistrationTypeId);
        System.out.println("eventRegistrationTypeId :  "+eventRegistrationTypeId);
        prepStmt.setInt(2, Integer.parseInt(noOfDays)); 
         System.out.println("noOfDays :  "+noOfDays);
        prepStmt.setDouble(3, Double.parseDouble(price));
         System.out.println("price :  "+price);
        

        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        System.out.println("Succefully inserted :  "+cnt);
       
        }
      
      public Collection ejbFindByMeetingId(String icpMeetingId) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByMeetingId  icpMeetingId : "+icpMeetingId);
        this.icpMeetingId =icpMeetingId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = 
            "SELECT icp_meeting_id, assesment_level, assesment_date,no_of_days,usea_area_id,"+
          "location, instructor_id, host_member_id, shipping_type_id,assessor, facilities, land_owner_name, land_owner_business_name,"+
          "land_owner_address, land_owner_city,land_owner_state, land_owner_country,land_owner_zip, land_owner_phone, land_owner_fax,land_owner_email,"+
          "approved_by, approved_date, posting_type,add_date,request_status  FROM  " +DBHelper.USEA_INSTRUCTOR_DETAILS+ "  WHERE icp_meeting_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, icpMeetingId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                for (int i =1; i<=26; i++)
                     array.add(rs.getString(i));
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }
     
/**
  * Name         :insertRowPayment
  * Description  :This method will insert record into the Payment Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */   
    private void insertRowPayment() throws SQLException {
        Debug.print("MullaiyarDamICPEntityBean insertRowPayment");

        makeConnection();
        String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status,payment_id, check_name)" +
                  " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?,?,? )";
        prepStmt = con.prepareStatement(insertStatement);

        prepStmt.setString(1, objPayment.getUserId());
        Debug.print(" User ID : "+objPayment.getUserId());
        prepStmt.setString(2, objPayment.getCcName());
        Debug.print(" getCcName : "+objPayment.getCcName());
        prepStmt.setString(3, objPayment.getCcType());
        Debug.print(" getCcType : "+objPayment.getCcName());
        prepStmt.setString(4, objPayment.getCcNumber());
         Debug.print(" getCcNumber : "+objPayment.getCcNumber());
        prepStmt.setInt(5, objPayment.getCcExpMonth());
        Debug.print(" getCcExpMonth : "+objPayment.getCcExpMonth());
        prepStmt.setInt(6, objPayment.getCcExpYear());
        Debug.print(" getCcExpYear : "+objPayment.getCcExpYear());
        prepStmt.setInt(7, objPayment.getCcCvvid());
        Debug.print(" getCcCvvid : "+objPayment.getCcCvvid());
        prepStmt.setString(8, objPayment.getBankName());
        Debug.print(" getBankName : "+objPayment.getBankName());
        Debug.print(" getCheckDate : "+objPayment.getCheckDate());
        String chdate = null;
        if (objPayment.getCheckDate() != null ){
            prepStmt.setDate(9, DBHelper.toSQLDate(objPayment.getCheckDate()));
        } else {
            prepStmt.setDate(9, null);
        }
        Debug.print(" getCheckDate : "+objPayment.getCheckDate());
        prepStmt.setString(10, objPayment.getCheckNumber());
         Debug.print(" getCheckNumber : "+objPayment.getCheckNumber());
        prepStmt.setDouble(11, objPayment.getAmount());
        Debug.print(" getAmount : "+objPayment.getAmount());
        prepStmt.setDate(12, DBHelper.toSQLDate(objPayment.getPaymentDate()));
        Debug.print(" getPaymentDate : "+objPayment.getPaymentDate());
        prepStmt.setString(13, objPayment.getPaymentStatus());
         Debug.print(" getPaymentStatus : "+objPayment.getPaymentStatus());
        prepStmt.setString(14,objPayment.getPaymentId());
        Debug.print("Payment Id : "+objPayment.getPaymentId());
        
        prepStmt.setString(15,objPayment.getCheckName());
        Debug.print("Payment getCheckName : "+objPayment.getCheckName());        
        
        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        Debug.print("Successfully Inserted into HLCPaymentDetails......:"+cnt+"  USer ID: "+objPayment.getUserId());
        }
    
/**
  * Name         :loadAnnualDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private void loadICPMeetingDetails() throws SQLException {
        Debug.print("MullaiyarDamICPEntityBean loadAnnualDetails");
        
        this.icpMeetingId = (String) context.getPrimaryKey();
        makeConnection();
        String selectStatement = 
        "SELECT icp_meeting_id, request_status,approved_by, approved_date FROM " + DBHelper.USEA_INSTRUCTOR_DETAILS+" WHERE icp_meeting_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, icpMeetingId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            this.icpMeetingId = rs.getString(1);
            this.requestStatus = rs.getString(2);
            this.approvedBy = rs.getString(3);
            this.approvedDate = rs.getString(4);
            
        prepStmt.close();
        releaseConnection();
    } else {
        prepStmt.close();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + icpMeetingId + " not found in database.");
    }
    }    
    
/**
  * Name         :storeICPRequestStatus
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    
    private void storeICPRequestStatus() throws SQLException {
        Debug.print("MullaiyarDamEntityBean storeICPRequestStatus");
        //java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
      //area_id,badge_info,total_amount
        try {
        makeConnection();
        String updateStatement = "update " + DBHelper.USEA_INSTRUCTOR_DETAILS  + " set  request_status = ?,"+
                " approved_by = ?, approved_date = ?,comments = ? where icp_meeting_id = ?";
            
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, requestStatus);
        prepStmt.setString(2, approvedBy);
        Debug.print("approvedBy : "+approvedBy);
        //java.sql.Date dt = java.sql.Date.valueOf(approvedDate);
        // Debug.print("approvedDate : "+approvedDate);
        prepStmt.setDate(3, DBHelper.toSQLDate(new Date()));
        prepStmt.setString(4,comments);
        prepStmt.setString(5, icpMeetingId);
       
        
        
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
        }catch (Exception e){
            Debug.print("Error While Request Update : "+e.getMessage());
        }finally {
            releaseConnection();
        }
    }     
    
/**
  * Name         :makeConnection
  * Description  :This method will Create a databacse connection
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
