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
package com.hlcmsg.entity;

import com.hlcmsg.util.DBHelper;
import com.hlcmsg.util.Debug;
import com.hlcmsg.util.HLCMsgContactListMaster;
import com.hlcmsg.util.HLCMsgDistributionListMaster;
import javax.ejb.*;

import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the MessagingEntityBean enterprise bean.
 * Created Sep 2, 2006 1:21:07 PM
 * @author harmohan
 */
public class HLCMessagingEntityBean implements EntityBean, HLCMessagingEntityLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    
    
    /*===================Msg Distribution List Master=======================*/
    private String listId= null;
    private String listName= null;
    private String contactlistId= null;
   // private String activeStatus;
    private String addDate= null;
    
    /*===================Msg Distribution List Master=======================*/
    private String registrationEmailId = null;
    private String userId= null;
    private String firstName= null;
    private String middleName= null;
    private String lastName= null;
    private String nickName= null;
    private String emailId= null;
    private String alternateEmailId= null;
    private String phoneNo= null;
    private String mobileNo= null;
    private String faxNo= null;
    private String street= null;
    private String city= null;
    private String state= null;
    private String country= null;
    private String zip= null;
    private String activeStatus= null;
    
    /*============================Object Creation========================*/
    HLCMsgContactListMaster ObjMsgList = new HLCMsgContactListMaster();
    HLCMsgDistributionListMaster objMsgDist = new HLCMsgDistributionListMaster();
    
    /*=======================Setter Method==================================*/
    public void setContactlistId(String contactlistId){ this.contactlistId = contactlistId; }
    public void setUserId(String userId){ this.userId =userId ; }
    public void setFirstName(String firstName){this.firstName = firstName; }
    public void setMiddleName(String middleName){ this.middleName = middleName; }
    public void setLastName(String lastName){ this.lastName= lastName; }
    public void setNickName(String nickName){this.nickName = nickName; }
    public void setEmailId(String emailId){ this.emailId = emailId; }
    public void setAlternateEmailId(String alternateEmailId){ this.alternateEmailId = alternateEmailId ; }
    public void setPhoneNo(String phoneNo){this.phoneNo = phoneNo; }
    public void setMobileNo(String mobileNo){ this.mobileNo = mobileNo; }
    public void setFaxNo(String faxNo){ this.faxNo = faxNo; }
    public void setStreet(String street){ this.street =street ; }
    public void setCity(String city){ this.city = city; }
    public void setState(String state){  this.state = state; }
    public void setCountry(String country){ this.country =country ; }
    public void setZip(String zip){ this.zip = zip; }
    public void setActiveStatus(String activeStatus){ this.activeStatus = activeStatus; }
    
    
    public void setListId(String listId){this.listId = listId ; }
    public void setListName(String listName){ this.listName = listName; }
    public void setRegistrationEmailId(String registrationEmailId){ this.registrationEmailId = registrationEmailId; }
   // public void setActiveStatus(String activeStatus){ this.activeStatus =activeStatus ; }
    public void setAddDate(String addDate){ this.addDate = addDate; }
    
    /**============================Object Initialization=============================*/
    public HLCMsgContactListMaster getMsgContactListMaster(){
        Debug.print("MsgContactListMaster getMsgContactListMaster");        
         return new HLCMsgContactListMaster(contactlistId,userId,firstName,middleName,lastName,nickName,emailId,alternateEmailId,
         phoneNo,mobileNo,faxNo,street,city,state,country,zip,activeStatus,registrationEmailId);
    }
    
    public HLCMsgDistributionListMaster getMsgDistributionListMaster() {
        Debug.print("MsgDistributionListMaster getMsgDistributionListMaster"); 
        return new HLCMsgDistributionListMaster(listId,listName, contactlistId,activeStatus,addDate,emailId);
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
        Debug.print("MessageEntityBean ejbRemove");

        try {
            makeConnection();
            //USEA_MSS_DISTRIBUTION_LIST
            System.out.println("Inside ejbRemove Contact List ID :  "+contactlistId);
            String deleteStatement = "DELETE FROM "+DBHelper.USEA_MSS_DISTRIBUTION_LIST +" WHERE contactlist_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, contactlistId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the USEA_MSS_DISTRIBUTION_LIST record : "+cnt);
                   
            deleteStatement = "DELETE FROM " + DBHelper.USEA_MSG_CONTACT_LIST_MASTER   + "  WHERE contactlist_id = ? ";
            prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, contactlistId);
            cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the USEA_MSG_CONTACT_LIST_MASTER record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            releaseConnection();
            throw new EJBException("MessageEntityBean ejbRemove: " + ex.getMessage());
        }finally {
            releaseConnection();
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
        Debug.print("MessagingEntityBean ejbLoad");
        this.contactlistId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        String selectStatement = "SELECT contactlist_id,user_id,first_name,middle_name,last_name,nick_name,email_id,alternate_email_id,phone_no,"+
        "mobile_no,fax_no,street,city,state,country,zip FROM " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+" WHERE contactlist_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, contactlistId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
            this.contactlistId = rs.getString(1);
            this.userId = rs.getString(2);
            this.firstName = rs.getString(3);
            this.middleName = rs.getString(4);
            this.lastName = rs.getString(5);
            this.nickName = rs.getString(6);
            this.emailId = rs.getString(7);
            this.alternateEmailId = rs.getString(8);
            this.phoneNo = rs.getString(9);
            this.mobileNo = rs.getString(10);
            this.faxNo = rs.getString(11);
            this.street = rs.getString(12);
            this.city = rs.getString(13);
            this.state = rs.getString(14);
            this.country = rs.getString(15);
            this.zip = rs.getString(16);
        } 
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for MessagingEntityBean");
    }
    
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            System.out.println("ejbStore for MessagingEntityBean");
            makeConnection();
            String updateStatement = "update " + DBHelper.USEA_MSG_CONTACT_LIST_MASTER  + 
           " set first_name = ?,middle_name = ?,last_name = ?,nick_name = ?,email_id = ?,alternate_email_id = ?,phone_no = ?,"+
           "mobile_no = ?,fax_no = ?,street = ?,city = ?,state = ?,country = ?,zip = ?   where contactlist_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, firstName);
            Debug.print(" firstName : "+firstName);
            prepStmt.setString(2, middleName);
            Debug.print(" middleName : "+middleName);
            prepStmt.setString(3, lastName);
            Debug.print(" lastName : "+lastName);
             prepStmt.setString(4, nickName);
             Debug.print(" nickName : "+nickName);
            prepStmt.setString(5, emailId);
            Debug.print(" emailId : "+emailId);
            prepStmt.setString(6, alternateEmailId);
            Debug.print(" alternateEmailId : "+alternateEmailId);
             prepStmt.setString(7, phoneNo);
            Debug.print(" phoneNo : "+phoneNo);
            prepStmt.setString(8, mobileNo);
            Debug.print(" mobileNo : "+mobileNo);
            prepStmt.setString(9, faxNo);
            Debug.print(" faxNo : "+faxNo);
             prepStmt.setString(10, street);
             Debug.print(" street : "+street);
            prepStmt.setString(11, city);
            Debug.print(" city : "+city);
            prepStmt.setString(12, state);
            Debug.print(" state : "+state);
             prepStmt.setString(13, country);
            Debug.print(" country : "+country);
            prepStmt.setString(14, zip);
            Debug.print(" zip : "+zip);
            prepStmt.setString(15, contactlistId);
            Debug.print(" contactlistId : "+contactlistId);
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("ejbStore Sucessfully Updated." + rowCount);
            prepStmt.close();
        } catch (Exception ex) {
           throw new EJBException("ejbStore: " + ex.getMessage());
           // ex.printStackTrace();
        }finally {
            releaseConnection();
        }
    }
    
    // </editor-fold>
    
    public String ejbCreate(HLCMsgContactListMaster ObjMsgList) throws CreateException {
         
        Debug.print("MessagingEntityBean ejbCreate"); 
        if(ObjMsgList==null ){
                throw new EJBException("ejbCreate: ObjMsgList argument is null or empty");
        }
        if (isNotNull(ObjMsgList.getUserId())){
            this.userId = ObjMsgList.getUserId();
        }
        if (isNotNull(ObjMsgList.getFirstName())){
            this.firstName = ObjMsgList.getFirstName();
        }
        if (isNotNull(ObjMsgList.getMiddleName())){
            this.middleName = ObjMsgList.getMiddleName();
        }
        if (isNotNull(ObjMsgList.getLastName())){
            this.lastName = ObjMsgList.getLastName();
        }
        if (isNotNull(ObjMsgList.getNickName())){
            this.nickName = ObjMsgList.getNickName();
        }
        if (isNotNull(ObjMsgList.getEmailId())){
            this.emailId = ObjMsgList.getEmailId();
        }
        if (isNotNull(ObjMsgList.getAlternateEmailId())){
            this.alternateEmailId = ObjMsgList.getAlternateEmailId();
        }
        if (isNotNull(ObjMsgList.getPhoneNo())){
            this.phoneNo = ObjMsgList.getPhoneNo();
        }
        if (isNotNull(ObjMsgList.getMobileNo())){
            this.mobileNo = ObjMsgList.getMobileNo();
        }
        if (isNotNull(ObjMsgList.getFaxNo())){
            this.faxNo = ObjMsgList.getFaxNo();
        }
        if (isNotNull(ObjMsgList.getStreet())){
            this.street = ObjMsgList.getStreet();
        }
        if (isNotNull(ObjMsgList.getCity())){
            this.city = ObjMsgList.getCity();
        }
        if (isNotNull(ObjMsgList.getState())){
            this.state = ObjMsgList.getState();
        }
        if (isNotNull(ObjMsgList.getCountry())){
            this.country = ObjMsgList.getCountry();
        }
        if (isNotNull(ObjMsgList.getZip())){
            this.zip = ObjMsgList.getZip();
        }
        if (isNotNull(ObjMsgList.getActiveStatus())){
            this.activeStatus = ObjMsgList.getActiveStatus();
        }
        if (isNotNull(ObjMsgList.getRegistrationEmailId())){
            this.registrationEmailId = ObjMsgList.getRegistrationEmailId();
        }
        
        
        try {
              insertRowMsgContactList();
              System.out.println("after insertRowMsgContactList()");
          } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCMsgContactListMaster ObjMsgList) {
        Debug.print("MessagingEntityBean MsgDistributionListMaster ejbPostCreate"); 
    }
    
    
    private void insertRowMsgContactList() throws SQLException {
        Debug.print("MessagingEntityBean insertRowInstructorDetails");
        //java.sql.Date dt = java.sql.Date.valueOf(dob);

        try{
             makeConnection();
            String insertStatement = 
              "insert into " + DBHelper.USEA_MSG_CONTACT_LIST_MASTER + " (user_id,first_name,middle_name,last_name,nick_name,email_id," +
              "alternate_email_id,phone_no,mobile_no,fax_no,street,city,state,country,zip)"+
                    " values ( ? , ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the Instructor Details ....\n\n ");
            prepStmt.setString(1, userId);
            System.out.println("userId :  "+userId);
            prepStmt.setString(2, firstName);
            System.out.println("first_name :  "+firstName);
            prepStmt.setString(3, middleName); 
             System.out.println("middle_name :  "+middleName);
            prepStmt.setString(4, lastName);
             System.out.println("last_name :  "+lastName);
            prepStmt.setString(5, nickName);
            System.out.println("nick_name :  "+nickName);
            prepStmt.setString(6, emailId);
            System.out.println("emailId :  "+emailId);
            prepStmt.setString(7, alternateEmailId);
            System.out.println("alternateEmailId :  "+alternateEmailId);
            prepStmt.setString(8, phoneNo);
            System.out.println("phoneNo :  "+phoneNo);
            prepStmt.setString(9, mobileNo);
            System.out.println("mobileNo :  "+mobileNo);
            prepStmt.setString(10, faxNo);
            System.out.println("faxNo :  "+faxNo);
            prepStmt.setString(11, street);
            System.out.println("street :  "+street);
            prepStmt.setString(12, city);
            System.out.println("city :  "+city);
             prepStmt.setString(13, state);
             System.out.println("state :  "+state);
            prepStmt.setString(14, country);
            System.out.println("country :  "+country);
            prepStmt.setString(15, zip);
            System.out.println("zip :  "+zip);

            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            System.out.println("Succefully inserted :  "+cnt);
        }catch(Exception e){
            releaseConnection();
            Debug.print(" Error while Inserting : "+e.getMessage());
        }finally {
            releaseConnection();
        }
        
        }
    
    
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
   public String ejbFindByPrimaryKey(String contactlistId) throws FinderException {
        Debug.print("MessageEntityBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(contactlistId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("MessageEntityBean ejbFindByPrimaryKey ID:" + contactlistId);
            return contactlistId;
        } else {
            throw new ObjectNotFoundException("Row for id " + contactlistId + " not found.");
        }
    }
    
     private boolean selectByPrimaryKey(String contactlistId) throws SQLException {
            Debug.print("MessageEntityBean selectByPrimaryKey");
            
            makeConnection();
            String selectStatement = "SELECT contactlist_id from " + DBHelper.USEA_MSG_CONTACT_LIST_MASTER + " WHERE contactlist_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, contactlistId);
            ResultSet rs = prepStmt.executeQuery();
            System.out.println("selectByPrimaryKey : Working ");
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return result;
        }
     
      
     //first name, last name and email-id
      public Collection ejbFindByContactListId(String userId) throws ObjectNotFoundException {
        Debug.print("Inside the ejbFindByContactListId  contactlistId : "+userId);
        this.userId =userId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT contactlist_id,first_name, last_name, email_id, user_id FROM  " +DBHelper.USEA_MSG_CONTACT_LIST_MASTER+
                    " where user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
               // this.firstName = rs.getString(1);
               /// this.lastName = rs.getString(2);
               // this.emailId = rs.getString(3);
               // String [] names = {firstName, lastName, emailId };
                for (int i=1; i<=5;i++)
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
      
   private boolean isNotNull(String data) {      
          return (data!=null && data.trim().length()>0) ? true :false;      
   }  
     //Display Distribution list
    
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