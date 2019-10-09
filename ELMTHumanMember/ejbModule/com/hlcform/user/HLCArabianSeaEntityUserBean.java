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
package com.hlcform.user;

import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.DBHelper;
import com.hlcform.util.Debug;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPassword;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import java.text.ParseException;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaEntityUserBean enterprise bean.
 * Created Aug 24, 2006 2:57:52 PM
 * @author harmohan
 */
public class HLCArabianSeaEntityUserBean implements EntityBean, HLCArabianSeaEntityUserLocalBusiness {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private EntityContext context;
    
    //====================UserMaster Variables=====================
    public String userId = null;
    public String loginName;
    public String userTypeId;
    public String userCode;
    public String contactTypeId;
    public String contactType;
    public String prefix;
    public String firstName;
    public String middleName;
    public String lastName;
    public String sufix;
    public String dob;
    public String gender;
    public String emailId;
    public String password;
    public String secretQuestion;
    public String secretAnswer;
    public String memberTypeName;
    private String statusName;
    private String userTypeName;
    private String adminString;
    private String usefAmateur;
    private String paymentId;
    private String membershipTypeName;
    private String amateurName;
    private boolean amateurDec1;
    private boolean amateurDec2;
    private String armbandQty;
    private boolean nonUseaMailingStatus;
    private boolean nonUseaEmailStatus;
    private String CommAddr;
    //  private String employeeId;
    //====================UserMaster Variables=====================
    
    //====================Contact Details Variable================
    private String contactId;
   // private String contactTypeId;
   // private String userId;
    private String suite;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String phoneNo;
    private String mobileNo;
    private String faxNo;
   
  
    //=================Member Details =============================
    
    private String memberId;
   // private String userId;
    private String membershipTypeId;
    private String nonuseaOrgId;
    private String nonuseaOrgMemberId;
    private String countryMailTypeId;
    private String familyAddOns;
    private String parentMemberId;
    private String endowmentTrustAmount;
    private Boolean activeStatus;
    private Date addDate;
    private String statusId;	
    private Date expiryDate;
    private String registerDate;
    private String loginDate;
    String dateOfBirth = null;
      //=================Object Decleration =========================
    private HLCContactDetails objContact;
    private HLCUserMaster objUserMaster;
    private HLCPaymentDetails objPayment;
    private HLCMemberDetails objMember;
    
    
    public HLCUserMaster getUserMasterDetails(){
        Debug.print("ArabianSeaEntityBean getUserMasterDetails");        
        return new HLCUserMaster(activeStatus,userId,userTypeName,adminString,membershipTypeId, userTypeId,userCode, contactTypeId,prefix,firstName,middleName,lastName,sufix,dob, 
            gender,emailId,password,secretQuestion,secretAnswer,userTypeId,loginName,nonUseaMailingStatus,nonUseaEmailStatus);
    }
    
    public HLCPaymentDetails getPaymentDetails(){
        Debug.print("ArabianSeaEntityBean getPaymentDetails");        
        return  objPayment;
    }
    public void setPaymentDetails(HLCPaymentDetails objPayDet){
        Debug.print("ArabianSeaEntityBean setPaymentDetails");        
        this.objPayment = objPayment;
    }
    
    //==================User Master Setter Method====================
     public void setActiveStatus(Boolean activeStatus) {this.activeStatus = activeStatus; }
    public void setAdminString(String adminString){this.adminString=adminString;}
    public void setMemeberTypeId(String membershipTypeId){this.membershipTypeId =membershipTypeId; }
    public void setUserTypeId(String userTypeId){this.userTypeId=userTypeId;}
    public void setUserCode(String userCode){this.userCode = userCode; }
    public void setContactTypeId(String contactTypeId){this.contactTypeId=contactTypeId;}
    public void setCommunicationAddress(String contactType){this.contactType = contactType;}
    public void setPrefix(String prefix){this.prefix=prefix;}
    public void setFirstName(String firstName){this.firstName=firstName;}
    public void setMiddleName(String middleName){this.middleName=middleName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public void setSufix(String sufix){this.sufix=sufix;}
    public void setDob(String dob){this.dob=dob;}
    public void setGender(String gender){this.gender=gender;}
    public void setEmailId(String emailId){this.emailId=emailId;}
    public void setPassword(String password){this.password=password;}
    public void setSecretQuestion(String secretQuestion){this.secretQuestion=secretQuestion;}
    public void setSecretAnswer(String secretAnswer){this.secretAnswer=secretAnswer;}
    public void setLoginName(String loginName) {this.loginName=loginName;}
    public void setUsefAmateur(String usefAmateur) {this.usefAmateur=usefAmateur;}
    public void setNonUseaMailingStatus(boolean nonUseaMailingStatus) {this.nonUseaMailingStatus = nonUseaMailingStatus;}
    public void setNonUseaEmailStatus(boolean nonUseaEmailStatus) {this.nonUseaEmailStatus = nonUseaEmailStatus; }
    public void setPrefAddress(String CommAddr){this.CommAddr = CommAddr;}
         
    //=======================Contact Details Setter Method==================================
   // public void setContactId(String contactId){this.contactId=contactId;}
   // public void setContactTypeId(String contactTypeId){this.contactTypeId=contactTypeId;}
    public void setUserId(String userId){this.userId=userId;}
    public void setContactType(String contactType){this.contactType = contactType;}
    public void setSuite(String suite){this.suite=suite;}
    public void setAddress1(String address1){this.address1=address1;}
    public void setAddress2(String address2){this.address2=address2;}
    public void setCity(String city){this.city=city;}
    public void setState(String state){this.state=state;}
     public void setCountry(String country){this.country=country;}
    public void setZip(String zip){this.zip=zip;}
    public void setPhoneNo(String phoneNo){this.phoneNo=phoneNo;}
    public void setMobileNo(String mobileNo){this.mobileNo=mobileNo;}
    public void setFaxNo(String faxNo){this.faxNo=faxNo;}
    //=====================Member Details Setter method====================================
    public void setMemberId(String memberId){this.memberId=memberId;}
    //public void setUserId(String userId){this.userId=userId;}
    public void setMembershipTypeId(String membershipTypeId){this.membershipTypeId=membershipTypeId;}
    public void setNonuseaOrgId(String nonuseaOrgId){this.nonuseaOrgId=nonuseaOrgId;}
    public void setNonUseaOrgMemberId(String nonuseaOrgMemberId){this.nonuseaOrgMemberId=nonuseaOrgMemberId;}
    public void setCountryMailTypeId(String countryMailTypeId){this.countryMailTypeId=countryMailTypeId;}
    public void setFamilyAddOns(String familyAddOns){this.familyAddOns=familyAddOns;}
    public void setParentMemberId(String parentMemberId){this.parentMemberId=parentMemberId;}
    public void setEndowmentTrustAmount(String endowmentTrustAmount){this.endowmentTrustAmount=endowmentTrustAmount;}
   // public void setAddDate(Date addDate){this.addDate=addDate;}
    public void setStatusId(String statusId){this.statusId=statusId;}
    public void setExpiryDate(Date expiryDate){this.expiryDate=expiryDate;}
    //public void setEmployeeId(String employeeId){this.employeeId=employeeId;}
   
    
    
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
        Debug.print("ArabianSeaEntityBean ejbActivate");
        userId = (String) context.getPrimaryKey();
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
        Debug.print("ArabianSeaEntityBean ejbPassivate");
        userId = null;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
        Debug.print("ArabianSeaEntityBean ejbRemove");

        try {
            deleteRow(userTypeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
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
        Debug.print("ArabianSeaEntityBean ejbLoad");

        try {
            loadUserDetails();
            System.out.println("Going to loadUserDetails");
            loadContactDetails();
        } catch (Exception ex) {
           throw new EJBException("ejbLoad: " + ex.getMessage());
           // ex.printStackTrace();
        }
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            System.out.println("inside the ejbStore  Primary :   "+contactType);
            //this.contactType = objContact.getContactType();
            System.out.println("inside the ejbStore  :  "+contactType);
            if (("Primary").equalsIgnoreCase(contactType)){
                storeUserDetails();
                storeContactDetails();
            }else {
                storeContactDetails();
            }
        }catch (Exception ex) {
             throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    // </editor-fold>
   
    /**
     * See EJB 2.0 and EJB 2.1 section 12.2.5
     */
   public String ejbFindByPrimaryKey(String userTypeId) throws FinderException {
        Debug.print("ArabianSeaEntityBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(userId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("ArabianSeaEntityBean ejbFindByPrimaryKey ID:" + userId);
            return userTypeId;
        } else {
            throw new ObjectNotFoundException("Row for id " + userId + " not found.");
        }
    }
    /**========================Create method for User Master=============================================*/
    public String ejbCreate(HLCUserMaster objUserMaster) throws CreateException {
         
        Debug.print("ArabianSeaEntityBean ejbCreate objUserMaster : "+objUserMaster); 
        if(objUserMaster==null ){
                throw new EJBException("ejbCreate: objUserMaster argument is null or empty");
        }
        this.userId = objUserMaster.getUserId();
        this.loginName = objUserMaster.getLoginName();
        this.userCode = objUserMaster.getUserCode();
        Debug.print("objUserMaster.getUserCode() "+objUserMaster.getUserCode());
        this.adminString = objUserMaster.getAdminString();
        this.userTypeName = objUserMaster.getUserTypeName();
        Debug.print("objUserMaster.getUserTypeName() "+objUserMaster.getUserTypeName());
        this.userTypeId = objUserMaster.getUserTypeId();
        this.contactTypeId = objUserMaster.getContactTypeId();
        this.prefix = objUserMaster.getPrefix();
        this.firstName = objUserMaster.getFirstName();
        this.middleName = objUserMaster.getMiddleName();
        this.lastName = objUserMaster.getLastName();
        this.sufix = objUserMaster.getSufix();
        this.dob = objUserMaster.getDob();
        this.gender = objUserMaster.getGender();
        this.emailId = objUserMaster.getEmailId();
        this.password = objUserMaster.getPassword();
        this.secretQuestion = objUserMaster.getSecretQuestion();
        this.nonUseaEmailStatus = objUserMaster.isNonUseaEmailStatus();
        this.nonUseaMailingStatus = objUserMaster.isNonUseaMailingStatus();
       // this.employeeId=objUserMaster.getEmployeeId();
        Debug.print("objUserMaster.getSecretQuestion() "+objUserMaster.getSecretQuestion());
        this.secretAnswer = objUserMaster.getSecretAnswer();
        Debug.print("objUserMaster.getSecretAnswer() "+objUserMaster.getSecretAnswer());
        
        Debug.print("Data available in User Master : \n"+objUserMaster);
        this.objPayment = objPayment;
        try {
            Debug.print("Before the method insertRowUserMaster()");
            
              insertRowUserMaster();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
     public void ejbPostCreate(HLCUserMaster objUserMaster) {
        Debug.print("ArabianSeaEntityBean ejbPostCreate"); 
    }
     
     /**==============================Create method for Contact Details =============================*/
     
      /**========================Create method for User Master=============================================*/
    public String ejbCreate(HLCContactDetails objContact) throws CreateException {
         
        Debug.print("ArabianSeaEntityBean ejbCreate"); 
        if(objContact==null ){
                throw new EJBException("ejbCreate: objContact argument is null or empty");
        }
        this.contactTypeId = objContact.getContactTypeId();
        this.userId = objContact.getUserId();
        this.suite = objContact.getSuite();
        this.address1 = objContact.getAddress1();
        this.address2 = objContact.getAddress2();
        this.city = objContact.getCity();
        this.state = objContact.getState();
        this.country = objContact.getCountry();
        this.zip = objContact.getZip();
        this.phoneNo = objContact.getPhoneNo();
        this.mobileNo = objContact.getMobileNo();
        this.faxNo = objContact.getFaxNo();
        Debug.print("Data available in Contact Details : \n"+objContact);
        try {
              insertRowContactDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
     public void ejbPostCreate(HLCContactDetails objContact) {
        Debug.print("ArabianSeaEntityBean ejbPostCreate"); 
    }
     
   public String ejbCreate(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws CreateException {
         String parentId;
        Debug.print("ArabianSeaEntityBean ejbCreate"); 
        if(objMember==null ){
                throw new EJBException("ejbCreate: objContact argument is null or empty");
        }
        this.memberId = objMember.getMemberId();
        
        Debug.print("                MemberId" + this.memberId);
        Debug.print("                objMember.getMemberId():" + objMember.getMemberId());
        
        this.emailId = objMember.getEmailId();
        Debug.print(emailId+"=emailId");
        this.userId = objMember.getUserId();
        Debug.print(userId+"=userId");
        this.membershipTypeId = objMember.getMembershipTypeId();
        Debug.print(membershipTypeId+"=membershipTypeId");
        this.nonuseaOrgId = objMember.getNonuseaOrgId();
        this.nonuseaOrgMemberId = objMember.getNonUseaOrgMemberId();
        this.countryMailTypeId = objMember.getCountryMailTypeId();
        this.familyAddOns = objMember.getFamilyAddOns();
        this.parentMemberId = objMember.getParentMemberId();
        this.endowmentTrustAmount = objMember.getEndowmentTrustAmount();
        this.statusId = objMember.getStatusId();
        this.expiryDate = objMember.getExpiryDate();
        this.loginName = objMember.getLoginName();
        Debug.print(loginName+"=loginName");
        this.usefAmateur = objMember.getUsefAmateur();
        this.paymentId = objMember.getPaymentId();
        this.membershipTypeName = objMember.getMembershipTypeName();
        
        this.amateurName = objMember.getAmateurName();
        this.amateurDec1 = objMember.isAmateurDec1();
        this.amateurDec2 = objMember.isAmateurDec2();
        
        if (objMember.getArmbandQty() != null){
             this.armbandQty = objMember.getArmbandQty();
         } else {
             this.armbandQty = "0";
         }
        
        Debug.print(" Member Detail Before Insert : \n"+objMember);
        Debug.print(" Payment Detail Before Insert : \n"+objPayment);
        this.objPayment = objPayment;
        try {
            Debug.print("Insert in to the member registrtion");
               insertRowHumanMemberDetails();
                Debug.print("after in to the member registrtion");
                if (objPayment.getUserId() != null){
                    insertRowPayment();
                }
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    
     public void ejbPostCreate(HLCMemberDetails objMember, HLCPaymentDetails objPayment) {
        Debug.print("ArabianSeaEntityBean ejbPostCreate"); 
    }
     
    public String ejbCreate(HLCPaymentDetails objPayment) throws CreateException {
        String parentId;
        Debug.print("ArabianSeaEntityBean ejbCreate PaymentDetails"); 
        if(objPayment==null ){
                throw new EJBException("ejbCreate: objPayment argument is null or empty");
        }
        Debug.print(" Payment Detail Before Insert : \n"+objPayment);
        this.objPayment = objPayment;
        try {
              insertRowPayment();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;
    }
    public void ejbPostCreate(HLCPaymentDetails objPayment) {
        Debug.print("ArabianSeaEntityBean ejbPostCreate PaymentDetails"); 
    }  
     
    
    
     /**=======================DATABASE CONNECTION=============================================*/
  
      private void insertRowUserMaster() throws SQLException {
        Debug.print("ArabianSeaEntityBean insertRowUserMaster");
        java.sql.Date dt = null;
       
     if(dob!=null)
        {
            dt = java.sql.Date.valueOf(dob);
        }
        Debug.print("After converting DOB");
       
        /*if (userTypeName.startsWith("USEA Staff")) {
                try {
                    this.userCode = DBHelper.getNextUserCode(con);
                }catch (Exception e){
                    //e.printStackTrace();
                    Debug.print("Error In member ID : "+e.getMessage());
                }
        }else {
            this.userCode = null;
             Debug.print("USer code : "+userCode);
        }*/
        //Debug.print("userTypeName.startsWith(\"USEA Staff\") "+userTypeName.startsWith("USEA Staff"));
         makeConnection();
        Debug.print("USer code : "+userCode);
        String str = "SELECT user_type_id FROM "+DBHelper.USEA_MMS_TYPEMASTER+" where user_type_name = ?";
        prepStmt = con.prepareStatement(str);
        prepStmt.setString(1, "Human".trim());
        rs = prepStmt.executeQuery();
        if (rs.next()) {
            this.userTypeId = rs.getString(1);
        } 
        
        rs.close();
        
        if (userTypeName != null){
            str = "SELECT user_type_id FROM "+DBHelper.USEA_MMS_TYPEMASTER+" where user_type_name = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, userTypeName);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.userTypeId = rs.getString(1);
            } 
            rs.close();
        }
        
        
        if (userTypeName.startsWith("USEA Staff")){
            this.password = HLCPassword.getPassword();
        }
        
        //=============================Checking for apostopies in the string and removing==========================
        String s1 = secretQuestion;
        String s2 = "'";
        String s3 = "";
        String str1 = null;
        Debug.print(" Replace String : "+s1);
        if (s1 != null && s1.trim().length() > 0) {
            str1 = DBHelper.replace(s1,s2,s3);
            Debug.print(" Replace String : "+str1);
            int index = s1.indexOf("'");    
            if (index >= 0){
                secretQuestion = str1;
            }
        }
        Debug.print(" Final String : "+secretQuestion);  
        
         //=============================Cnd of Checking for apostopies in the string and removing===================
         //Suresh Here =============================
           
        ////=========================
        ///
       
      /*  String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (contact_type_id,user_type_id,prefix,first_name,middle_name,"+
            "last_name,sufix,dob,gender,email_id, password, secret_question, secret_answer, user_code,login_name,non_usea_mailing_status,non_usea_email_status,user_id,spl_notes)" +
                " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?,?,?,?,?,?,?) ";
                */
      /*  String insertStatement = "insert into " + DBHelper.USEA_MMS_USERMASTER + " (contact_type_id,user_type_id,prefix,first_name,middle_name,"+
                "last_name,sufix,dob,gender,email_id, password, secret_question, secret_answer, user_code,login_name,non_usea_mailing_status,non_usea_email_status,user_id)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?,?,?,?,?,?) ";*/
        
        String insertStatement = "insert into tblusermaster (contact_type_id,user_type_id,prefix,first_name,middle_name,"+
                "last_name,sufix,dob,gender,email_id, password, secret_question, secret_answer,user_code,login_name,non_usea_mailing_status,non_usea_email_status,user_id)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?,?,?,?,?,?) ";

        prepStmt = con.prepareStatement(insertStatement);
        System.out.println("Inside the User Master ....\n\n ");
       // prepStmt.setString(1, membershipTypeId);
       //System.out.println("membershipTypeId : "+membershipTypeId);
        
        prepStmt.setString(1, contactTypeId);
        System.out.println("contactTypeId :  "+contactTypeId);
        prepStmt.setString(2, userTypeId);
        System.out.println("userTypeId :  "+userTypeId);
        prepStmt.setString(3, prefix);
         System.out.println("prefix :  "+prefix);
        prepStmt.setString(4, firstName);
         System.out.println("firstName :  "+firstName);
        prepStmt.setString(5, middleName);
        prepStmt.setString(6, lastName);
        prepStmt.setString(7, sufix);
       prepStmt.setDate(8, dt);
	   
         System.out.println("Date :  "+dt);
        prepStmt.setString(9, gender);
        prepStmt.setString(10, emailId);
        System.out.println("emailId :  "+emailId);
        prepStmt.setString(11, password);
        prepStmt.setString(12, secretQuestion);
        System.out.println("secretQuestion :  "+secretQuestion);
        prepStmt.setString(13, secretAnswer);
        System.out.println("secretAnswer :  "+secretAnswer);
        prepStmt.setString(14, userCode);
        System.out.println("userCode :  "+userCode);
        prepStmt.setString(15, loginName);
        System.out.println("loginName :  "+loginName);
        prepStmt.setBoolean(16, nonUseaMailingStatus);
        System.out.println("nonUseaMailingStatus :  "+nonUseaMailingStatus);
        prepStmt.setBoolean(17, nonUseaEmailStatus);
        System.out.println("nonUseaEmailStatus :  "+nonUseaEmailStatus);
        prepStmt.setString(18, userId);
        System.out.println("userId :  "+userId);
        
        int cnt = prepStmt.executeUpdate();
      
        prepStmt.close();
        releaseConnection();
        System.out.println("Succefully inserted :  "+cnt);
       
        }
      
      
     public String getNextId() throws SQLException {
        Debug.print("AnnualMeetingDAO getNextUserId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userId";
            Debug.print("AnnualMeetingDAO getNextUserId:" + selectStatement);
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
            Debug.print("SQL Exception in getNextUserId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in getNextUserId:" + e.getMessage());
        }
        return nextId;
    }
        
      /**====================Insert for Contact Details==============================*/
        private void insertRowContactDetails() throws SQLException {
            Debug.print("ArabianSeaEntityBean insertRowContactDetails");
 
            makeConnection();
            if (loginName != null){
                try {
                    userId = DBHelper.getUserId(con, loginName);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.print("User Id is from insertRowContactDetails: "+userId);
            String insertStatement = "insert into " + DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
                "address2,city,state,country,zip,phone_no, mobile_no, fax_no)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?) ";
            prepStmt = con.prepareStatement(insertStatement);
            System.out.println("Inside the Contact Details ....\n\n ");
            prepStmt.setString(1, contactTypeId);
            System.out.print("contactTypeId: "+contactTypeId);
            prepStmt.setString(2, userId);
            System.out.print("userId: "+userId);
            prepStmt.setString(3, suite);
            System.out.print("suite: "+suite);
            prepStmt.setString(4, address1);
            prepStmt.setString(5, address2);
            prepStmt.setString(6, city);
            prepStmt.setString(7, state);
            prepStmt.setString(8, country);
            prepStmt.setString(9, zip);
            prepStmt.setString(10, phoneNo);
            System.out.print("phoneNo: "+phoneNo);
            prepStmt.setString(11, mobileNo);
            System.out.print("mobileNo: "+mobileNo);
            prepStmt.setString(12, faxNo);
            System.out.print("faxNo: "+faxNo);
            int cnt = prepStmt.executeUpdate();
            System.out.print("Record Inserted succefully  "+cnt);
            Debug.print(" Insert Contact Detail Data : \n"+objContact);
            prepStmt.close();
            releaseConnection();
        }
     
        //===============Insert Member Details==============================
        /**
  * Name         :insertRowHorseMemberDetails
  * Description  :This method will insert record into the Horse Member Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */  
    private void insertRowHumanMemberDetails() throws SQLException, ParseException {
       Debug.print("ArabianSeaEntityBean insertRowHumanMemberDetails");
       java.util.Date dt = null;
       Debug.print("membershipTypeName :"+membershipTypeName);
       
       if(membershipTypeName.equalsIgnoreCase("Subscribing Member")){
           Debug.print( " Expired Date Form CalaulateExpiredDate() Before:" + dt);
           dt = new DBHelper().calculateExpireDate(12,new Date());
           Debug.print( " Expired Date Form CalaulateExpiredDate() After:" + dt);
       }
       else if(membershipTypeName.equalsIgnoreCase("Life Member")){
           Debug.print( " Expired Date Form CalaulateExpiredDate() Before:" + dt);
           dt = new DBHelper().calculateExpireDate(240,new Date());
           Debug.print( " Expired Date Form CalaulateExpiredDate() After:" + dt);
       }
       else{
           String expDt = DBHelper.getExpiryDate();
           dt = java.sql.Date.valueOf(expDt);
       }
       Debug.print( " Expired Date Final:" + dt);
       makeConnection();
       try {
             try{
                Debug.print("        this.memberId:" + this.memberId);
                Debug.print("        this.Email Id:" + emailId);
                Debug.print("        this.loginName :" + this.loginName);
                 
               // userId = DBHelper.getUserId(con, loginName);
                //userId = DBHelper.getUserId(con, loginName);
                Debug.print("        userId:" + userId);
                this.statusId = DBHelper.getStatusId(con);
                Debug.print("        statusId:" + statusId);
             }
             catch(Exception e){
                  Debug.print("Error In member ID : "+e.getMessage());
             }

            
            Debug.print("memberId  ID : "+memberId);
            Debug.print("statusId : "+statusId);

            System.out.println("User ID : "+userId);

            System.out.println("Human Member ID :  "+memberId);
            String str = "insert into "+DBHelper.USEA_MMS_MEMBERDETAIL +" ( member_id, user_id, membership_type_id, expiry_date, "+
                    "nonusea_org_id, nonusea_org_member_id, country_mail_type_id, family_add_ons, parent_member_id, " +
                    "endowment_trust_amount,status_id, usef_amateur, payment_id, amateur_name, amateur_dec1, amateur_dec2,armband_qty )"+
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,? , ? , ? , ?, ?)";

            PreparedStatement prepStmt = con.prepareStatement(str);
            System.out.println("Inside the Human Member Details ....\n\n ");
            prepStmt.setString(1, memberId);
            System.out.println("Member ID : "+memberId);
            prepStmt.setString(2, userId);
            System.out.println("User ID : "+userId);
            prepStmt.setString(3, membershipTypeId);
            System.out.println("membershipTypeId : "+membershipTypeId);
            prepStmt.setDate(4, DBHelper.toSQLDate(dt));
            System.out.println("Date : "+dt);
            prepStmt.setString(5, nonuseaOrgId);
            System.out.println("nonuseaOrgId : "+nonuseaOrgId);
            prepStmt.setString(6, nonuseaOrgMemberId);
            System.out.println("nonuseaOrgMemberId : "+nonuseaOrgMemberId);
            prepStmt.setString(7, countryMailTypeId);
            System.out.println("countryMailTypeId : "+countryMailTypeId);
            prepStmt.setString(8, familyAddOns);
            System.out.println("familyAddOns : "+familyAddOns);
            prepStmt.setString(9, parentMemberId);
            System.out.println("parentMemberId : "+parentMemberId);
            prepStmt.setDouble(10, Double.valueOf("0").doubleValue());
            System.out.println("endowmentTrustAmount : "+endowmentTrustAmount);
            prepStmt.setString(11, statusId);
            System.out.println("statusId : "+statusId);
            prepStmt.setString(12, usefAmateur);
            System.out.println("usefAmateur : "+usefAmateur);
            prepStmt.setString(13, paymentId);
            System.out.println("paymentId : "+paymentId);
            prepStmt.setString(14, amateurName);
            System.out.println("amateurName : "+amateurName);
            prepStmt.setBoolean(15, amateurDec1);
            System.out.println("amateurDec1 : "+amateurDec1);
            prepStmt.setBoolean(16, amateurDec2);
            System.out.println("amateurDec2 : "+amateurDec2);
            prepStmt.setInt(17, Integer.parseInt(armbandQty));
            System.out.println("armbandQty : "+armbandQty);
            

            int cnt = prepStmt.executeUpdate();
            System.out.println("Succefully inserted into human member details......"+cnt);
            prepStmt.close();
            releaseConnection();
        }
        catch (Exception e){
            e.printStackTrace();
            Debug.print("Exception in ArabianSeaEntityBean insertRowHumanMemberDetails: "+e.getMessage());
        }
        //return memberId;
    }
    
    /**
  * Name         :insertRowPayment
  * Description  :This method will insert record into the Payment Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */   
    private void insertRowPayment() throws SQLException {
        Debug.print("ArabianSeaEntityBean insertRowPayment");
/*
        makeConnection();
        try{
        String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status)" +
                  " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";

        prepStmt = con.prepareStatement(insertStatement);
       /* if (objPayment.getUserId() != null){
            prepStmt.setString(1, objPayment.getUserId());
        }else {
            prepStmt.setString(1, userId);
        }*/
     /*   prepStmt.setString(1, objPayment.getUserId());
        Debug.print(" getUserId:  "+objPayment.getUserId());
        prepStmt.setString(2, objPayment.getCcName());
        prepStmt.setString(3, objPayment.getCcType());
        prepStmt.setLong(4, objPayment.getCcNumber());
        prepStmt.setInt(5, objPayment.getCcExpMonth());
        prepStmt.setInt(6, objPayment.getCcExpYear());
        prepStmt.setInt(7, objPayment.getCcCvvid());
        prepStmt.setString(8, objPayment.getBankName());
        Debug.print(" getBankName:  "+objPayment.getBankName());
        if ( objPayment.getCheckDate() != null) {
            prepStmt.setDate(9, DBHelper.toSQLDate(objPayment.getCheckDate()));
        } else {
            prepStmt.setDate(9, null);
        }
        prepStmt.setLong(10, objPayment.getCheckNumber());
        prepStmt.setDouble(11, objPayment.getAmount());
        prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));

        prepStmt.setString(13, objPayment.getPaymentStatus());
        Debug.print(" getPaymentStatus:  "+objPayment.getPaymentStatus());
        Debug.print(" Payment Detail Inside the Payment Details : \n"+objPayment);
        int cnt = prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
        Debug.print("Successfully Inserted..Into Payment Details:" + cnt);
        */
         makeConnection();
        try{
            String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, check_name, amount, payment_date, payment_status, " +
                    " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                    " ssl_transaction_type, ssl_invoice_no, ssl_email,payment_id,check_amount,pending_amount, parent_payment_id, ip_address)" +
                      " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ?)";

            String paymentUserId = objPayment.getUserId();
            Debug.print("PaymentBean ejbCreate User ID:" + paymentUserId);
            String ccName = objPayment.getCcName();
            String ccType = objPayment.getCcType();
            String ccNumber = objPayment.getCcNumber();
            
             // following code for changing the card no from real to dummy as per client saying in mail 13-March-2008.
             if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
             }
            Debug.print(ccNumber+"=testing ccnumber for renewal");
            // code ends here for card no. change
            
            int ccExpMonth = objPayment.getCcExpMonth();
            int ccExpYear = objPayment.getCcExpYear();
            
            // DO NOT write/store ANY CVV information in the tblUserPaymentDetails.So commented. as in email 13-March-2008.
            
            //int ccCvvid = objPayment.getCcCvvid();
            int ccCvvid = 0;
            String bankName = objPayment.getBankName();
            Date checkDate =objPayment.getCheckDate();
            String checkNumber = objPayment.getCheckNumber();
            String checkName = objPayment.getCheckName();
            double amount = objPayment.getAmount();
            Date paymentDate = objPayment.getPaymentDate();
            String paymentStatus = objPayment.getPaymentStatus();
            String sslResult = objPayment.getSslResult();
            String sslResultMessage = objPayment.getSslResultMessage();
            String sslTxnId = objPayment.getSslTxnId();
            String sslApprovalCode = objPayment.getSslApprovalCode();
            String sslCvv2Response = objPayment.getSslCvv2Response();
            String sslAvsResponse = objPayment.getSslAvsResponse();
            String sslTransactionType = objPayment.getSslTransactionType();
            String sslInvoiceNo =  objPayment.getSslInvoiceNo();
            String sslEmail = objPayment.getSslEmail();
            String parentPaymentId = objPayment.getParentPaymentId();
            String ipAddress = objPayment.getIpAddress();
            float pendingAmount = objPayment.getPendingAmount();
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);

            prepStmt.setString(1, paymentUserId);
            prepStmt.setString(2, ccName);
            prepStmt.setString(3, ccType);
            prepStmt.setString(4, ccNumber);
            prepStmt.setInt(5, ccExpMonth);
            prepStmt.setInt(6, ccExpYear);
            prepStmt.setInt(7, ccCvvid);
            prepStmt.setString(8, bankName);
            if(checkDate!=null){
                prepStmt.setDate(9, DBHelper.toSQLDate(checkDate));
            }
            else{
                prepStmt.setDate(9, null);
            }
            
            prepStmt.setString(10, checkNumber);
            prepStmt.setString(11, checkName);
            prepStmt.setDouble(12, amount);
            prepStmt.setDate(13, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(14, paymentStatus);
            
            prepStmt.setString(15, sslResult);
            prepStmt.setString(16, sslResultMessage);
            prepStmt.setString(17, sslTxnId);
            prepStmt.setString(18, sslApprovalCode);
            prepStmt.setString(19, sslCvv2Response);
            prepStmt.setString(20, sslAvsResponse);
            prepStmt.setString(21, sslTransactionType);
            prepStmt.setString(22, sslInvoiceNo);
            prepStmt.setString(23, sslEmail);
            prepStmt.setString(24, objPayment.getPaymentId());
            prepStmt.setFloat(25, objPayment.getCheckAmount());
            Debug.print(" objPayment.getCheckAmount() :"+objPayment.getCheckAmount());
            prepStmt.setFloat(26, pendingAmount);
            prepStmt.setString(27, parentPaymentId);
            prepStmt.setString(28, ipAddress);
            
            System.out.println("       ArabianSeaEntityBean insertRowPayment paymentId:" + objPayment.getPaymentId());
           
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
             Debug.print("PaymentBean Sucessfully insertRowPayment");

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
     
    private boolean selectByPrimaryKey(String userId) throws SQLException {
            Debug.print("ArabianSeaEntityBean selectByPrimaryKey");

            makeConnection();
            String selectStatement = "SELECT user_id from " + DBHelper.USEA_MMS_USERMASTER + " WHERE user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            ResultSet rs = prepStmt.executeQuery();
            System.out.println("selectByPrimaryKey : Working ");
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return true;
        }
        
    public Collection ejbFindByContactTypeId(String contactType) throws ObjectNotFoundException {
        this.contactType = contactType;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT contact_type_id FROM  " +DBHelper.USEA_CONTACT_TYPEMASTER +"  WHERE contact_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, contactType.trim());
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
            } 
             prepStmt.close();
             releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {releaseConnection(); }
        
        return array;
    }
/**
  * Name         :ejbFindBySearchcondition
  * Description  :Select the content from the database based on the search condition
  * @ param      :firstName, lastName,frmDate, toDate, userTypeId
  * @return      :Collection
  * @throws      :ObjectNotFoundException
  */         
     public Collection ejbFindBySearchcondition(String firstName, String lastName, Date frmDate, Date toDate, String userTypeId) throws ObjectNotFoundException {
        this.contactType = contactType;
        ArrayList array = new ArrayList();
        
        String searchStr = "SELECT contact_type_id,user_type_id, prefix, first_name, middle_name,"+
        " last_name, sufix, dob, gender, email_id, register_date,login_date FROM  "+
         DBHelper.USEA_MMS_USERMASTER+ " WHERE active_status = true ORDER BY register_date desc  ";
        
        if (!("".equals(firstName))){
            searchStr += " AND first_name = "+firstName;
        }
        if(!("".equals(firstName))) {
            searchStr += " AND last_name = "+lastName;
        }
        //register_date between '2006-08-01' and '2006-08-31'
        
        
        if (!("".equals(frmDate)) && ("".equals(toDate)) ){
            searchStr += " AND register_date > \'"+frmDate+"\' ";
        }
        if (("".equals(frmDate)) && !("".equals(toDate)) ){
            searchStr += " AND register_date < \'"+toDate+"\' ";
        }
        if(!("".equals(userTypeId))) {
            searchStr += " AND user_type_id = "+userTypeId;
        }
        
        if (!("".equals(frmDate)) && !("".equals(toDate)) ){
             searchStr += "  AND register_date BETWEEN \'"+frmDate+"\' AND  \'"+toDate+"\' ";
        }
        
        try {
            makeConnection();
            Debug.print(searchStr);
            prepStmt = con.prepareStatement(searchStr);
            
            rs = prepStmt.executeQuery();
           // contact_type_id,user_type_id, prefix, first_name, middle_name,"+
        //" last_name, sufix, dob, gender, email_id, register_date,login_date
            while (rs.next()) {
                this.contactTypeId = rs.getString(1);
                this.userTypeId  = rs.getString(2);
                this.prefix = rs.getString(3);
                this.firstName = rs.getString(4);
                this.middleName = rs.getString(5);
                this.lastName = rs.getString(6);
                this.sufix = rs.getString(7);
                this.dob = rs.getString(7);
                this.gender = rs.getString(7);
                this.emailId = rs.getString(7);
                this.registerDate = rs.getString(7);
                this.loginDate = rs.getString(7);
                //String [] searchDetail = {contactTypeId, userTypeId,prefix,firstName,middleName,lastName,sufix,dob,gender,emailId,registerDate,loginDate};
                //array.add(searchDetail);
                array.add(contactTypeId);
                array.add(userTypeId);
                array.add(prefix);
                array.add(firstName);
                array.add(middleName);
                array.add(lastName);
                array.add(sufix);
                array.add(dob);
                array.add(gender);
                array.add(emailId);
                array.add(registerDate);
                array.add(loginDate);
               
            } 
             prepStmt.close();
             releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {releaseConnection(); }
        
        return array;
    
    }
    
    
/**
  * Name         :loadUserDetails
  * Description  :Select the content from the database
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */     
      private void loadUserDetails() throws SQLException {
        Debug.print("ArabianSeaEntityBean loadUserDetails");
        
       // this.horseMemberId = (String) context.getPrimaryKey();
        makeConnection();
        String selectStatement = "SELECT user_id, user_type_id,contact_type_id,prefix,first_name,middle_name,"+
            " last_name,sufix,dob,gender,email_id, password, secret_question, secret_answer,login_name," +
                "non_usea_mailing_status,non_usea_email_status FROM " 
             +DBHelper.USEA_MMS_USERMASTER+" WHERE user_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId);
        ResultSet rs = prepStmt.executeQuery();
        System.out.println("Inside the loadUserDetails User Id : "+userId);
        if (rs.next()) {
        this.userId = rs.getString(1);
        this.userTypeId =  rs.getString(2);
        this.contactTypeId =  rs.getString(3);
        this.prefix = rs.getString(4);
        this.firstName = rs.getString(5);
        this.middleName = rs.getString(6);
        this.lastName = rs.getString(7);
        this.sufix = rs.getString(8);
        this.dob = rs.getString(9);
        this.gender = rs.getString(10);
        this.emailId = rs.getString(11);
        this.password = rs.getString(12);
        this.secretQuestion = rs.getString(13);
        this.secretAnswer = rs.getString(14);
        this.loginName = rs.getString(15);
        this.nonUseaMailingStatus = rs.getBoolean(16);
        this.nonUseaEmailStatus = rs.getBoolean(17);
      //  this.usefAmateur = rs.getString(16);
        rs.close();
        prepStmt.close();
        releaseConnection();
    } else {
        prepStmt.close();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + emailId + " not found in database.");
    }
    }
      
/**
  * Name         :loadContactDetails
  * Description  :Select the content from the database
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */     
      private void loadContactDetails() throws SQLException {
        Debug.print("ArabianSeaEntityBean loadContactDetails");
        
       // this.horseMemberId = (String) context.getPrimaryKey();
        makeConnection();
        try {
        String selectStatement = "SELECT contact_id,contact_type_id,user_id,suite,address1,"+
                "address2,city,state,country,zip,phone_no, mobile_no, fax_no FROM " +DBHelper.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId);
        ResultSet rs = prepStmt.executeQuery();
        System.out.println("Inside the loadContactDetails User Id : "+userId);
        while (rs.next()) {
        this.contactId = rs.getString(1);
        this.contactTypeId =  rs.getString(2);
        this.userId =  rs.getString(3);
        this.suite = rs.getString(4);
        this.address1 = rs.getString(5);
        this.address2 = rs.getString(6);
        this.city = rs.getString(7);
        this.state = rs.getString(8);
        this.country = rs.getString(9);
        this.zip = rs.getString(10);
        this.phoneNo = rs.getString(11);
        this.mobileNo = rs.getString(12);
        this.faxNo = rs.getString(13);
        }
        prepStmt.close();
        releaseConnection();
       } catch (Exception e) {
        prepStmt.close();
        e.printStackTrace();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + userId + " not found in database.");
      }
    }      
      
/**
  * Name         :storeHorseMemberDetails
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    private void storeUserDetails() throws SQLException {
        Debug.print("ArabianSeaEntityBean storeUserDetails");
        java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
      try {
        makeConnection();
        String updateStatement =
                "update " + DBHelper.USEA_MMS_USERMASTER  + " set prefix = ? , first_name = ? , middle_name = ? , last_name = ? , " +
                "sufix = ? , dob = ? ,gender = ? , password = ? , secret_question = ? , " +
                "secret_answer = ?, email_id = ? , login_name = ?, non_usea_mailing_status = ?, non_usea_email_status=?, contact_type_id=?, user_code=?  where user_id = ?";
            
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, prefix);
        prepStmt.setString(2, firstName);
        prepStmt.setString(3, middleName);
        prepStmt.setString(4, lastName);
        prepStmt.setString(5, sufix);
        prepStmt.setString(6, dob);
        prepStmt.setString(7, gender);
        prepStmt.setString(8, password);
        prepStmt.setString(9, secretQuestion);
        prepStmt.setString(10, secretAnswer);
        prepStmt.setString(11, emailId);
        prepStmt.setString(12, loginName);
        prepStmt.setBoolean(13, nonUseaMailingStatus);
        prepStmt.setBoolean(14, nonUseaEmailStatus);
        prepStmt.setString(15, CommAddr);
        prepStmt.setString(16, userCode);
        prepStmt.setString(17, userId);
        
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated UserDetails Master." + rowCount);
        Debug.print(" User Details ....\n"+objUserMaster);
        prepStmt.close();
        //releaseConnection();
      }catch (SQLException e){
          e.printStackTrace();
      }finally {
          releaseConnection();
      }
      
    }
     
     
/**
  * Name         :storeHorseMemberDetails
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */          
    
    private void storeContactDetails()  throws SQLException {
        Debug.print("ArabianSeaEntityBean storeContactDetails");
        java.sql.Date jsqlD =  new java.sql.Date( new Date().getTime() );
       // horseId = (String) context.getPrimhorseIdaryKey();
       try {
        makeConnection();
        String updateStatement =
                "update " + DBHelper.USEA_CONTACT_DETAILS  + " set suite = ? , address1 = ? , address2 = ? , city = ? , " +
                "state = ? , country = ? ,zip = ? , phone_no = ? , mobile_no = ? , fax_no = ?  where user_id = ? AND contact_type_id = ?";
            
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, suite);
        prepStmt.setString(2, address1);
        prepStmt.setString(3, address2);
        prepStmt.setString(4, city);
        prepStmt.setString(5, state);
        prepStmt.setString(6, country);
        prepStmt.setString(7, zip);
        prepStmt.setString(8, phoneNo);
        prepStmt.setString(9, mobileNo);
        prepStmt.setString(10, faxNo);
        prepStmt.setString(11, userId);
        prepStmt.setString(12, contactTypeId);
        Debug.print("Data In Contact Details : \n"+objContact);
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Contact Details Sucessfully Updated." + rowCount);
        prepStmt.close();
       }catch (Exception e){
            releaseConnection();
            Debug.print(" Error While Updating contact Details : "+e.getMessage());
       }finally {
            releaseConnection();
       }
    }     
     
/**
  * Name         :deleteRow
  * Description  :This method will DELETE a ROW from the Databacse connection
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */     
    private void deleteRow(String userTypeId) throws SQLException {
         Debug.print("ArabianSeaEntityBean deleteRow");

        makeConnection();

        String deleteStatement = "delete from " + DBHelper.USEA_MMS_USERMASTER  + "  where user_type_id = ? ";
        PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

        prepStmt.setString(1, userTypeId);
        prepStmt.executeUpdate();
        prepStmt.close();
        releaseConnection();
    }     
     
    
    public Collection ejbFindByUserEmailId(String emailId) throws ObjectNotFoundException {
        this.emailId =emailId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT user_id,dob FROM  " +DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE email_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId.trim());
             
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getDate(2));
                Debug.print("Date format from Database : "+rs.getString(2));
             } 
             prepStmt.close();
             releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }
    
    
     public Collection ejbFindByUserLoginName(String loginName) throws ObjectNotFoundException {
        this.emailId =emailId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT user_id,dob FROM  " +DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE login_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
             
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getDate(2));
                Debug.print("Date format from Database : "+rs.getString(2));
             } 
             prepStmt.close();
             releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }
    
    public Collection ejbFindByLoginMailId(String emailId, String pass) throws ObjectNotFoundException {
        this.emailId =emailId;
        String flag = null;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT user_id,user_code,first_name,user_type_id,email_id,password FROM  " +DBHelper.USEA_MMS_USERMASTER +
                    "  WHERE email_id = ? AND password = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId);
            prepStmt.setString(2, pass);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));
                array.add(rs.getString(3));
                array.add(rs.getString(4));
                array.add(rs.getString(5));
             } 
             prepStmt.close();
              releaseConnection();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }
    
    public Collection ejbFindByJuniorMemberEmailId(String emailId, String memberTypeId) throws ObjectNotFoundException {
        this.emailId =emailId;
        String dateOfBirth = null;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id, membership_type_name FROM  " +DBHelper.USEA_MEMBERSHIP_TYPE +
                    "  WHERE membership_type_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
               this.memberTypeName = rs.getString(2);
             } 
            selectStatement = "SELECT user_id,dob FROM  " +DBHelper.USEA_MMS_USERMASTER + "  WHERE email_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, emailId.trim());
             
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.userId = rs.getString(1);
                this.dateOfBirth = rs.getString(2);
             } 
            System.out.println("Date Of Birth : "+dateOfBirth);
             prepStmt.close();
             releaseConnection();
            if (memberTypeName.startsWith("Junior Member")){
                String age = ""+DBHelper.getAge(dateOfBirth);
                array.add(age);
            }
             
             
        } catch (Exception sqe) {
            //throw new EJBException(sqe);
            //sqe.printStackTrace();
            Debug.print("Error while getting age of a junior member");
        } finally {
            releaseConnection();
        }
       // int age = DBHelper.getAge(dateOfBirth);
        
        return array;
    }
    
    
    public Collection ejbFindByFamilyAddOnMemberID( String memberId) throws ObjectNotFoundException {
        this.memberId =memberId;
        String dateOfBirth = null;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            
            String selectStatement = "SELECT member_id, membership_type_id, status_id FROM  " +DBHelper.USEA_MMS_MEMBERDETAIL + "  WHERE member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
             
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.memberId = rs.getString(1);
                this.membershipTypeId = rs.getString(2);
                this.statusId = rs.getString(3);
             } 
            
            System.out.println("memberId : "+memberId);
            System.out.println("membershipTypeId : "+membershipTypeId);
            System.out.println("statusId : "+statusId);
            
            
            selectStatement = "SELECT membership_type_id, membership_type_name FROM  " +DBHelper.USEA_MEMBERSHIP_TYPE +
                    "  WHERE membership_type_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, membershipTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
               this.memberTypeName = rs.getString(2);
               
             } 
             System.out.println("memberTypeName : "+memberTypeName);
            
            selectStatement = "SELECT status_name FROM  " +DBHelper.USEA_STATUS_MASTER +
                    "  WHERE status_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, statusId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
               this.statusName = rs.getString(1);
             } 
            System.out.println("statusName : "+statusName);
            prepStmt.close();
            releaseConnection();
        } catch (Exception sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
       // int age = DBHelper.getAge(dateOfBirth);
        if ( (memberTypeName.startsWith("Life Member") || memberTypeName.startsWith("Full Member")) && statusName.startsWith("Active")){
            //String age = ""+DBHelper.getAge(dateOfBirth);
            array.add(memberTypeName);
        }
        return array;
    }
    
/**
  * Name         :humanMemberList
  * Description  :This method will list all the Human member for display
  * @ param      :None
  * @return      :Collection
  * @throws      :ObjectNotFoundException
  */   
    
    public Collection humanMemberList()throws ObjectNotFoundException {
        String memberType = "Human";
        ArrayList array = new ArrayList();
        try {
            String statement = "SELECT user_type_id FROM "+DBHelper.USEA_MMS_TYPEMASTER+"  WHERE user_type_name = ? ";
            prepStmt = con.prepareStatement(statement);
            prepStmt.setString(1, memberType);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
               this.statusName = rs.getString(1);
             } 
            
        }catch(Exception e){
            Debug.print("Error in HumanMemberList method : "+e.getMessage());
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
            //prepStmt.close();
            //rs.close();
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }     
    
}




