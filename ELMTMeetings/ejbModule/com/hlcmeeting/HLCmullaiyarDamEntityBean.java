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
package com.hlcmeeting;

import com.hlcmeeting.util.DBHelper;
import com.hlcmeeting.util.Debug;
import com.hlcmeeting.util.HLCMeeAnnualDetails;
import com.hlcmeeting.util.HLCMeeAnnualRegistrationDetails;
import com.hlcmeeting.util.HLCPaymentDetails;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the mullaiyarDamEntityBean enterprise bean.
 * Created Aug 30, 2006 7:45:41 PM
 * @author harmohan
 */
public class HLCmullaiyarDamEntityBean implements EntityBean, HLCmullaiyarDamEntityLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    
    private String prefix=null;
    private String firstName=null;
    private String middle_name=null;
    private String lastName=null;
    private String areaName=null;
    
    
    /**=================AnnualDetails Variable =================================*/
    private String annualMeetingId;
    private String memberId=null;
    private String areaId=null;
    private String badgeInfo=null;
    private String totalAmount=null;
    private String requestStatus=null;
    private String email=null;
    
    private String specificationId;
    private String eventRegistrationTypeId=null;
    private String dueDatePrice=null;
    private String aftDueDatePrice=null;
    private Date currentDate=null;
    private Date registrationDate=null;
    
    /**=================AnnualRegistrationDetails Variable =====================*/
    private String ardId;
    private String meetingId;
    private String noOfTickets=null;
    private String priceId=null;
    private String accomodationDetails=null;
    private String daysApplied=null;
    
    /**=================HLCPaymentDetails Variable =====================*/
    private String paymentId=null;
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
    private String paymentStatus;
    
    
    /**=========================Object Creation=========================*/
    HLCMeeAnnualDetails objAnnaulDetails = new HLCMeeAnnualDetails();
    HLCMeeAnnualRegistrationDetails objAnnualReg = new HLCMeeAnnualRegistrationDetails();
    HLCPaymentDetails objPayment = new HLCPaymentDetails();
    /**==================Setter Method for Annual Details==================*/
    public void setAnnualMeetingId(String annualMeetingId) {this.annualMeetingId = annualMeetingId; }
    public void setUserId(String userId) {this.userId = userId; }
    public void setEmail(String email) {this.email = email; }
    public void setAreaId(String areaId) {this.areaId = areaId;}
    public void setBadgeInfo(String badgeInfo) {this.badgeInfo = badgeInfo;}
    public void setTotalAmount(String totalAmount) {this.totalAmount = totalAmount;}
    public void setreRuestStatus(String requestStatus) {this.requestStatus = requestStatus;}
    
    /**==================Setter Method for Annual Registration Details==================*/
    public void setArdId(String ardId) {this.ardId = ardId; }
    public void setMeetingId(String meetingId) {this.meetingId = meetingId; }
    public void setNoOfTickets(String noOfTickets) {this.noOfTickets = noOfTickets;}
    public void setPriceId(String priceId) {this.priceId = priceId;}
    public void setAccomodationDetails(String accomodationDetails) {this.accomodationDetails = accomodationDetails;}
    public void setDaysApplied(String daysApplied) {this.daysApplied = daysApplied;}
    //days_applied
    
     /**==================Setter Method for Payment Details==================*/
    public void setPaymentId(String paymentId) {this.paymentId = paymentId;}
   // public void setUserId(String userId) {this.userId = userId;}
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
    
    /**==========================Object Creation==================================================*/
    
    public HLCMeeAnnualDetails getMeeAnnualDetails(){
        Debug.print("MullaiyarDamEntityBean getMeeAnnualDetails");        
        return new HLCMeeAnnualDetails(paymentId,email,annualMeetingId, userId,areaId,badgeInfo,totalAmount,requestStatus);
    }
    public HLCMeeAnnualRegistrationDetails getMeeAnnualRegistrationDetails(){
        Debug.print("MullaiyarDamEntityBean getMeeAnnualDetails");        
        return new HLCMeeAnnualRegistrationDetails(ardId, meetingId,noOfTickets,priceId,accomodationDetails,daysApplied);
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
        Debug.print("MullaiyarDamEntityBean ejbRemove");
       // USEA_ANNUAL_REGISTRATION
        try {
            makeConnection();
            String deleteStatement = "delete from " + DBHelper.USEA_ANNUAL_REGISTRATION   + "  where meeting_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, meetingId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the annual Details record : "+cnt);
            
            deleteStatement = "delete from " + DBHelper.USEA_ANNUAL_DETAILS   + "  where annual_meeting_id = ? ";
            prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, meetingId);
            cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the Annual Registration Details record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("MullaiyarDamEntityBean ejbRemove: " + ex.getMessage());
        }
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
        Debug.print("MullaiyarDamEntityBean ejbLoad");

        try {
            loadAnnualDetails();
            System.out.println("Going to loadAnnualDetails");
            loadAnnualRegistrationDetails();
        } catch (Exception ex) {
           throw new EJBException("ejbLoad: " + ex.getMessage());
           // ex.printStackTrace();
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
         Debug.print("ArabianSeaEntityBean ejbStore");

        try {
            storeAnnualDetails();
            storeAnnualRegistrationDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    // </editor-fold>
    
   public String ejbCreate(HLCMeeAnnualRegistrationDetails objAnnualReg) throws CreateException {
        Debug.print("MullaiyarDamEntityBean ejbCreate"); 
        if(objAnnualReg==null){
                throw new EJBException("ejbCreate:  objAnnualReg argument is null or empty");
        }
        if (objAnnualReg.getArdId() != null) {
            this.ardId = objAnnualReg.getArdId();
        }
        if (objAnnualReg.getMeetingId() != null) {
            this.meetingId = objAnnualReg.getMeetingId();
        }
        if (objAnnualReg.getNoOfTickets() != null) {
            this.noOfTickets = objAnnualReg.getNoOfTickets();
        }
        if (objAnnualReg.getPriceId() != null) {
            this.priceId = objAnnualReg.getPriceId();
        }
        if (objAnnualReg.getAccomodationDetails() != null) {
            this.accomodationDetails = objAnnualReg.getAccomodationDetails();
        }
        if (objAnnualReg.getDaysApplied() != null) {
            this.daysApplied = objAnnualReg.getDaysApplied();

        }
        
        
       // this.objPayment = objPayment;
        try {
              //insertRowAnnualDetails();
              System.out.println("after insertRowAnnualDetails()");
              insertRowAnnualRegistrationDetails();
              //insertRowPayment();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
   }
    
    public void ejbPostCreate(HLCMeeAnnualRegistrationDetails objAnnualReg) {
        Debug.print("MullaiyarDamEntityBean ejbPostCreate"); 
    }
    
   public String ejbCreate(HLCMeeAnnualDetails objAnnaulDetails, HLCPaymentDetails objPayment) throws CreateException {
         
        Debug.print("MullaiyarDamEntityBean ejbCreate"); 
        if(objAnnaulDetails==null && objAnnualReg==null && objPayment==null){
                throw new EJBException("ejbCreate: objAnnaulDetails or objAnnualReg or objPayment argument is null or empty");
        }
        
       // this.horseMemberId = objHorseMem.getHorseMemberId();
        this.annualMeetingId = objAnnaulDetails.getAnnualMeetingId();
        this.email = objAnnaulDetails.getEmail();
        this.userId = objAnnaulDetails.getUserId();
        this.areaId = objAnnaulDetails.getAreaId();
        this.badgeInfo = objAnnaulDetails.getBadgeInfo();
        this.totalAmount = objAnnaulDetails.getTotalAmount();

        this.objPayment = objPayment;
        
        try {
              insertRowAnnualDetails();
              System.out.println("after insertRowAnnualDetails()");
              if (objPayment.getUserId() != null && objPayment.getUserId().trim().length()>0){
                insertRowPayment();
              }
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
     
    public void ejbPostCreate(HLCMeeAnnualDetails objAnnaulDetails, HLCPaymentDetails objPayment) {
        Debug.print("MullaiyarDamEntityBean ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String annualMeetingId) throws FinderException {
        Debug.print("MullaiyarDamEntityBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(annualMeetingId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("MullaiyarDamEntityBean ejbFindByPrimaryKey ID:" + annualMeetingId);
            return annualMeetingId;
        } else {
            throw new ObjectNotFoundException("Row for id " + annualMeetingId + " not found.");
        }
    }
    
     private boolean selectByPrimaryKey(String annualMeetingId) throws SQLException {
            Debug.print("MullaiyarDamEntityBean selectByPrimaryKey");

            makeConnection();
            String selectStatement = "SELECT annual_meeting_id from " + DBHelper.USEA_ANNUAL_DETAILS + " WHERE annual_meeting_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, annualMeetingId);
            ResultSet rs = prepStmt.executeQuery();
            System.out.println("selectByPrimaryKey : Working ");
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return true;
        }
     
     private void insertRowAnnualDetails() throws SQLException {
        Debug.print("MullaiyarDamEntityBean insertRowAnnualDetails");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);
        makeConnection();
        try {
            this.annualMeetingId = DBHelper.getNextMeetingId(con);
        }catch (Exception e){
            
        }
        String insertStatement = 
         "insert into " + DBHelper.USEA_ANNUAL_DETAILS + " (annual_meeting_id,user_id,area_id,badge_info,total_amount) " +
                " values ( ? , ? , ? , ?, ?) ";
        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the Annual Details ....\n\n ");
        prepStmt.setString(1, annualMeetingId);
        System.out.println("annualMeetingId :  "+annualMeetingId);
        prepStmt.setString(2, userId);
        System.out.println("userId :  "+userId);
        prepStmt.setString(3, areaId);
         System.out.println("areaId :  "+areaId);
        prepStmt.setString(4, badgeInfo);
         System.out.println("badgeInfo :  "+badgeInfo);
        prepStmt.setDouble(5, Double.parseDouble(totalAmount));
        System.out.println("totalAmount :  "+totalAmount);

        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        System.out.println("Succefully inserted :  "+cnt);
        }
     
      /**====================Insert for Contact Details==============================*/
        private void insertRowAnnualRegistrationDetails() throws SQLException {
            Debug.print("MullaiyarDamEntityBean insertRowAnnualRegistrationDetails");
            try {
            makeConnection();
            /*String str = "SELECT annual_meeting_id FROM "+DBHelper.USEA_ANNUAL_DETAILS+" WHERE member_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, memberId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.meetingId = rs.getString(1);
            } */
            
            System.out.print("Meeting Id is from insertRowAnnualRegistrationDetails: "+meetingId);
            String insertStatement = 
            "insert into " + DBHelper.USEA_ANNUAL_REGISTRATION + " (meeting_id,no_of_tickets,price_id,accomodation_details,days_applied )" +
                    " values ( ? , ? , ? , ?,?) ";
            prepStmt = con.prepareStatement(insertStatement);
            Debug.print("Inside the Annual Registration Details ....\n\n ");
            prepStmt.setString(1, annualMeetingId);
            Debug.print("meetingId: "+annualMeetingId);
            prepStmt.setInt(2, Integer.parseInt(noOfTickets));
            Debug.print("noOfTickets: "+noOfTickets);
            prepStmt.setString(3, priceId);
            Debug.print("priceId: "+priceId);
            prepStmt.setString(4, accomodationDetails);
            Debug.print("accomodationDetails: "+accomodationDetails);//daysApplied
            prepStmt.setString(5, daysApplied);
            Debug.print("daysApplied: "+daysApplied);
            if (priceId != null){
                int cnt = prepStmt.executeUpdate();
                Debug.print("Record Inserted succefully into Annual Registration Details "+cnt);
            }
            prepStmt.close();
            }catch (Exception e){
                releaseConnection();
                Debug.print("Error While Inserting : "+e.getMessage());
            }finally {
                 releaseConnection();
            }
        }
/**
  * Name         :insertRowPayment
  * Description  :This method will insert record into the Payment Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */   
    private void insertRowPayment() throws SQLException {
        Debug.print("MullaiyarDamEntityBean insertRowPayment");

        makeConnection();
        String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status)" +
                  " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
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
        prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));
        Debug.print(" getPaymentDate : "+new Date());
        prepStmt.setString(13, objPayment.getPaymentStatus());
        Debug.print(" getPaymentStatus : "+objPayment.getPaymentStatus());
        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        Debug.print("Successfully Inserted into HLCPaymentDetails......:" + cnt);
        }
    
    public Collection ejbFindByMemberId(String email) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByMemberId  memberId : "+memberId);
        this.email =email;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT user_id FROM  " +DBHelper.USEA_MMS_USERMASTER +"  WHERE email_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, email);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
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
    
    public Collection ejbFindByUserTypeId(String memberId) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByMemberId  memberId : "+memberId);
        this.memberId =memberId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = 
            "SELECT price_id FROM  " +DBHelper.USEA__ANNUAL_PRICE+" A, "+DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_MMS_MEMBERDETAIL+" C " +
             "  WHERE A.user_type_id = B.user_type_id AND C.user_id = B.user_id AND C.member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
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
  * Name         :ejbFindDueDateLateDate
  * Description  :This method will Display the Amount based on the due date or later date from the databse 
  * @ param      :current date
  * @return      :Collection
  * @throws      :ObjectNotFoundException
  */      
    public Collection ejbFindDueDateLateDate(Date currentDate, String specificationId, String userTypeId) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByMemberId  memberId : "+memberId);

        //java.sql.Date cd = java.sql.Date.valueOf(currentDate.trim());
        java.sql.Date cd =  DBHelper.toSQLDate(currentDate );
        
        this.currentDate =currentDate;
        this.specificationId = specificationId;
        this.userTypeId = userTypeId;
        //this.eventRegistrationTypeId = eventRegistrationTypeId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            
            
            String selectStatement = 
             "SELECT A.price_id, A.due_date_price, A.aft_due_date_price,C.registration_date FROM " +DBHelper.USEA__ANNUAL_PRICE+" A, "+
             DBHelper.USEA_ANNUAL_SPECFICATION+" B, "+DBHelper.USEA_EVENT_REGISTRATION_MASTER+" C, "+DBHelper.USEA_MMS_TYPEMASTER+" D "+
             " WHERE A.specification_id = B.specification_id  "+
             " AND A.user_type_id = D.user_type_id AND A.specification_id = ?  AND A.user_type_id = ?  ";
            
            // user_type_id from userMasterType for Human
            // specification_id from annual specificationMaster
            //event_registration_type_id from tblMeeEventRegistrationTypeMaster

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, specificationId);
            prepStmt.setString(2, userTypeId);
            //prepStmt.setString(3, eventRegistrationTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.priceId = rs.getString(1);
                this.dueDatePrice = rs.getString(2);
                this.aftDueDatePrice = rs.getString(3);
                this.registrationDate = rs.getDate(4);
                //array.add(rs.getString(1));
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
       // this.registrationDate = registrationDate.trim();
        //java.sql.Date dd =  DBHelper.toSQLDate(registrationDate );
        //java.sql.Date dd = java.sql.Date.valueOf(registrationDate);
        if (currentDate.after(registrationDate)) {
            array.add(aftDueDatePrice);
        }else {
            array.add(dueDatePrice);
        }
        
        
        return array;
    }
    
    
    /**
  * Name         :ejbFindDueDatePrice
  * Description  :This method will Display the Amount based on the due date or later date from the databse 
  * @ param      :current date
  * @return      :Collection
  * @throws      :ObjectNotFoundException
  */      
    public Collection ejbFindDueDatePrice(String specificationId, String userTypeId) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByMemberId  memberId : "+memberId);

        //java.sql.Date cd = java.sql.Date.valueOf(currentDate.trim());
        java.sql.Date cd =  DBHelper.toSQLDate(currentDate );
        
       // this.currentDate =currentDate;
        this.specificationId = specificationId;
        this.userTypeId = userTypeId;
        //this.eventRegistrationTypeId = eventRegistrationTypeId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            
            
            String selectStatement = 
             "SELECT A.price_id, A.due_date_price, A.aft_due_date_price,C.registration_date FROM " +DBHelper.USEA__ANNUAL_PRICE+" A, "+
             DBHelper.USEA_ANNUAL_SPECFICATION+" B, "+DBHelper.USEA_EVENT_REGISTRATION_MASTER+" C, "+DBHelper.USEA_MMS_TYPEMASTER+" D "+
             " WHERE A.specification_id = B.specification_id AND A.event_registration_type_id = C.event_registration_type_id "+
             " AND A.user_type_id = D.user_type_id AND A.specification_id = ?  AND A.user_type_id = ?  ";
            
            // user_type_id from userMasterType for Human
            // specification_id from annual specificationMaster
            //event_registration_type_id from tblMeeEventRegistrationTypeMaster

            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, specificationId);
            prepStmt.setString(2, userTypeId);
            //prepStmt.setString(3, eventRegistrationTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.priceId = rs.getString(1);
                this.dueDatePrice = rs.getString(2);
                this.aftDueDatePrice = rs.getString(3);
                this.registrationDate = rs.getDate(4);
                //array.add(rs.getString(1));
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        //this.registrationDate = registrationDate.trim();
        java.sql.Date dd =  DBHelper.toSQLDate(registrationDate );
        //java.sql.Date dd = java.sql.Date.valueOf(registrationDate);
       
            array.add(priceId);
            array.add(dueDatePrice);
       
        
        
        return array;
    }
    
    public Collection ejbFindByUserDetails(String userId) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByMemberetails  userId : "+userId);
        this.userId =userId;
        ArrayList array = new ArrayList();
        /*UserMaster
        user_id
        contact_type_id
        prefix
        first_name
        middle_name
        last_name
        email_id
        ContactDetails
        contact_type_id
                user_id
                suite
                address1
                address2
                city
                state
                country
                zip
                phone_no
                mobile_no
                fax_no
        Sreing str = "SELECT A.contact_type_id, A.prefix,A.first_name,A.middle_name,A.last_name, A.email_id, B.suite, B.address1 "+
        "B.address2, B.city, B.state, B.country, B.zip, B.phone_no,B.mobile_no, B.fax_no FROM "DBHelper.USEA_MMS_USERMASTER+" A "+
        DBHelper.USEA_CONTACT_DETAILS+" B "+" WHERE A.user_id = B.user_id AND A.user_id = ?" ;*/
           
        try {
            makeConnection();
            String selectStatement = 
            "SELECT A.contact_type_id, A.prefix,A.first_name,A.middle_name,A.last_name, A.email_id, B.suite, B.address1, "+
            "B.address2, B.city, B.state, B.country, B.zip, B.phone_no,B.mobile_no, B.fax_no FROM "+DBHelper.USEA_MMS_USERMASTER+" A, "+
            DBHelper.USEA_CONTACT_DETAILS+" B "+" WHERE A.user_id = B.user_id AND A.user_id = ?" ;
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));
                array.add(rs.getString(3));
                array.add(rs.getString(4));
                array.add(rs.getString(5));
                array.add(rs.getString(6));
                array.add(rs.getString(7));
                array.add(rs.getString(8));
                array.add(rs.getString(9));
                array.add(rs.getString(10));
                array.add(rs.getString(11));
                array.add(rs.getString(12));
                array.add(rs.getString(13));
                array.add(rs.getString(14));
                array.add(rs.getString(15));
                array.add(rs.getString(16));
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
  * Name         :loadAnnualDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private void loadAnnualDetails() throws SQLException {
        Debug.print("MullaiyarDamEntityBean loadAnnualDetails");
        
        this.meetingId = (String)context.getPrimaryKey();
        try {
            makeConnection();
            String selectStatement = 
            "SELECT annual_meeting_id, user_id, area_id, badge_info, total_amount FROM " + DBHelper.USEA_ANNUAL_DETAILS+" WHERE annual_meeting_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, meetingId);
            Debug.print("Meeting ID : "+meetingId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.meetingId = rs.getString(1);
                this.userId = rs.getString(2);
                Debug.print("userId ID : "+userId);
                this.areaId =  rs.getString(3);
                Debug.print("areaId ID : "+areaId);
                this.badgeInfo =  rs.getString(4);
                Debug.print("badgeInfo : "+badgeInfo);
                this.totalAmount = rs.getString(5);
                Debug.print("totalAmount : "+totalAmount);
            prepStmt.close();
            releaseConnection();
            } else {
            prepStmt.close();
            releaseConnection();
            throw new NoSuchEntityException("Row for id " + meetingId + " not found in database.");
            }
        }catch(Exception e){
            Debug.print("Error while listing Annual Detail : "+e.getMessage());
        }
    }
   
/**
  * Name         :loadAnnualRegistrationDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private void loadAnnualRegistrationDetails() throws SQLException {
        Debug.print("MullaiyarDamEntityBean loadAnnualRegistrationDetails");
        //USEA_ANNUAL_REGISTRATION + " (meeting_id,no_of_tickets,price_id,accomodation_details )"
        this.meetingId = (String) context.getPrimaryKey();
        makeConnection();
        String selectStatement = 
        "SELECT no_of_tickets,price_id,accomodation_details FROM " + DBHelper.USEA_ANNUAL_REGISTRATION+" WHERE meeting_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, meetingId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            this.noOfTickets = rs.getString(1);
            this.priceId =  rs.getString(2);
            this.accomodationDetails =  rs.getString(3);
        prepStmt.close();
        releaseConnection();
    } else {
        prepStmt.close();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + meetingId + " not found in database.");
    }
    }    
   
/**
  * Name         :storeAnnualDetails
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    
    private void storeAnnualDetails() throws SQLException {
        Debug.print("MullaiyarDamEntityBean storeAnnualDetails");
        java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
      //area_id,badge_info,total_amount
        makeConnection();
        String updateStatement =
        "update " + DBHelper.USEA_ANNUAL_DETAILS  + " set area_id = ?, badge_info = ?, total_amount= ?, request_status = ?" +
                " where annual_meeting_id = ?";
            
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, areaId);
        prepStmt.setString(2, badgeInfo);
        prepStmt.setString(3, totalAmount);
        prepStmt.setString(4, requestStatus);
        prepStmt.setString(5, meetingId);
        
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
    }    
    
    private void storeAnnualRegistrationDetails() throws SQLException {
        Debug.print("MullaiyarDamEntityBean storeAnnualRegistrationDetails");
        java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
        //orseId = (String) context.getPrimhorseIdaryKey();
      //USEA_ANNUAL_REGISTRATION + " (meeting_id,no_of_tickets,price_id,accomodation_details )"
        makeConnection();
        String updateStatement =
        "update " + DBHelper.USEA_ANNUAL_REGISTRATION  + " set no_of_tickets = ?, price_id = ?, accomodation_details= ?" +
                " where meeting_id = ?";
            
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, noOfTickets);
        prepStmt.setString(2, priceId);
        prepStmt.setString(3, accomodationDetails);
        prepStmt.setString(4, meetingId);
        
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
    }    
    
    /**************************Search Critaria********************************/
    public Collection ejbFindByMeetingID(String meetingId) throws ObjectNotFoundException {
        Debug.print("Inside the gethMeetingDetails meeting ID :  "+meetingId);
        this.meetingId = meetingId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = 
            "SELECT A.annual_meeting_id,A.user_id, A.area_id,A.badge_info,A.total_amount,A.add_date, A.request_status FROM "+DBHelper.USEA_ANNUAL_DETAILS+" A "+
            " WHERE A.annual_meeting_id = ?" ;
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, meetingId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                 array.add(rs.getString(1));
                 this.userId = rs.getString(2);
                 this.areaId = rs.getString(3);
                 selectStatement = "SELECT prefix,first_name,middle_name,last_name FROM "+DBHelper.USEA_MMS_USERMASTER+
                 " WHERE user_id =  ?";
                 prepStmt = con.prepareStatement(selectStatement);
                 prepStmt.setString(1, userId);
                 rs1 = prepStmt.executeQuery();
                 if (rs1.next()){
                     this.prefix = rs1.getString(1);
                     this.firstName = rs1.getString(2);
                     this.middle_name =rs1.getString(3);
                     this.lastName = rs1.getString(4);
                 }
                 array.add(prefix+" "+firstName+" "+middle_name+" "+lastName) ;
                 selectStatement = "SELECT area_name FROM "+DBHelper.USEA_AREA_MASTER+" A "+" WHERE A.area_id = ?";
                 prepStmt = con.prepareStatement(selectStatement);
                 prepStmt.setString(1, areaId);
                 rs1 = prepStmt.executeQuery();
                 if (rs1.next()){
                     this.areaName = rs1.getString(1);
                 }
                array.add(areaName) ;
                //array.add(rs.getString(1));
                //array.add(rs.getString(2));
                array.add(rs.getString(4));
                array.add(rs.getString(5));
                array.add(rs.getString(6));
                array.add(rs.getString(7));
                
                selectStatement = "SELECT A.no_of_tickets,A.price_id,A.accomodation_details  FROM "+DBHelper.USEA_ANNUAL_REGISTRATION+" A "+" WHERE A.meeting_id = ?";
                 prepStmt = con.prepareStatement(selectStatement);
                 prepStmt.setString(1, meetingId);
                 rs1 = prepStmt.executeQuery();
                 if (rs1.next()){
                     array.add(rs1.getString(1));
                     array.add(rs1.getString(2));
                     array.add(rs1.getString(3));
                 }
                 
                 
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
