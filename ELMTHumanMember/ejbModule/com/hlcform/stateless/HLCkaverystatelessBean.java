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
package com.hlcform.stateless;

import com.hlcform.user.HLCArabianSeaEntityUserLocal;
import com.hlcform.user.HLCArabianSeaEntityUserLocalHome;
import com.hlcform.util.HLCContactDetails;
import com.hlcform.util.DBHelper;
import com.hlcform.util.Debug;
import com.hlcform.util.HLCEmployeeDetails;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlcform.util.HLCMemberUpdateDAO;
import com.hlcform.util.HLCPaymentDetailVO;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcform.util.HLCUserMaster;
import com.hlcform.util.HLCUserSearchResultVO;
import java.text.SimpleDateFormat;
import javax.ejb.*;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import javax.transaction.*;
import com.hlcform.util.HLCUsrSignUpVO;
import org.jasypt.util.text.BasicTextEncryptor;

import com.hlcform.util.HLCUsrSignUpVO;



/**
 * This is the bean class for the kaverystatelessBean enterprise bean.
 * Created Aug 24, 2006 6:34:06 PM
 * @author harmohan
 */

public class HLCkaverystatelessBean implements SessionBean, HLCkaverystatelessRemoteBusiness {
    private SessionContext context;
    private InitialContext ic = null;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con;
    ResultSet rs= null;
    PreparedStatement prepStmt = null;
    HLCArabianSeaEntityUserLocalHome home;
    HLCArabianSeaEntityUserLocal remote;
    String name = "ejb/HLCHumanJNDI";
    String mailId;
    Collection col;
    private static String userId;
    private String memberId;
    private String email;
    
    private String loginName;
    private String lastName;
    private String phoneNo;
    private String userCode;
    private String firstName;
    private String userTypeId;
    private String emailId;
    private String userTypeName;
    private String memberTypeName;
    private String pass;
    private String contactTypeId = null;
    
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
    UserTransaction utx = null;
    private String periodValue;
    
    
   
    HLCContactDetails objContact;
    HLCUserMaster objUserMaster;
    HLCPaymentDetails objPayment;
    
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
     * See section 7.10.3 of the EJB 2.0 specification
     * See section 7.11.3 of the EJB 2.1 specification
     */
    public void ejbCreate() {
        try{
            InitialContext jndiContext = getInitialContext();
            Object obj = jndiContext.lookup(name);
            home = (HLCArabianSeaEntityUserLocalHome)obj;
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
  
    
    public String addMemberDetails(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws RemoteException {
        Debug.print("kaverystatelessBean.addMemberDetails()");
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        String memberId = "";
        
        try{
            // For Debugs Starts
           /* if (objMember.getMembershipTypeName() != null){
                objMember.setMembershipTypeId(objDAO.getMembershipTypeId(objMember.getMembershipTypeName()));
            }*/
            //For Debugs Ends
            if (objMember.getNonuseaOrgName() != null){
                objMember.setNonuseaOrgId(objDAO.getNonuseaOrgId(objMember.getNonuseaOrgName()));
            }
            if (objMember.getCountryMailTypeName() != null){
                objMember.setCountryMailTypeId(objDAO.getCountryMailTypeId(objMember.getCountryMailTypeName()));
            }
            
            Debug.print("Inside the Add Member Details ");
            try{
                memberId = selectMemberId();
                Debug.print("             After Generationg MemberID:" + memberId);
            } catch(Exception e){
                Debug.print("Exception:" + e);
            }
            Debug.print("             Before Init MemberID:" + memberId);
            objMember.setMemberId(memberId);
            remote = home.create(objMember, objPayment);
        } catch(Exception exp){
            throw new EJBException("addMemberDetails: " + exp.getMessage());
        }
        return memberId;
    }
    
    public boolean addPaymentDetails(HLCPaymentDetails objPayment) throws RemoteException {
        try {
            remote = home.create(objPayment);
        }catch(Exception e){
            Debug.print(" Exception while inserting payment details : "+e.getMessage());
        }
        return true;
    }
    
    
    public String addUserRegistration(HLCUserMaster objUserMaster) throws RemoteException{
        //this.objUserMaster = objUserMaster;
        String contactTypeId = null;
        String usrId = null;
        
        // Collection c = home.findContactTypeId(objContact.getContactTypeId());
        //return c;
        try{
            Collection c = home.findByContactTypeId(objUserMaster.getCommunicationAddress());
            ArrayList array = new ArrayList(c);
            for (Iterator it=array.iterator(); it.hasNext( ); ) {
                // Object anObject = it.next( );
                String str = String.valueOf(it.next( ));
                String[] result = str.split(":");
                for (int x=1; x<result.length; x++)
                    System.out.println("Session Bean Contact Type ID : "+result[x]);
                contactTypeId = result[1];
            }
            objUserMaster.setContactTypeId(contactTypeId);
            Debug.print("Contant Of objUserMaster : "+objUserMaster);
            
            Debug.print("user Registration getNextUserId");
            makeConnection();
            
            try{
                String selectStatement = "select newid() as userId";
                Debug.print("AnnualMeetingDAO getNextUserId:" + selectStatement);
                PreparedStatement prepSelect = con.prepareStatement(selectStatement);
                ResultSet rs = prepSelect.executeQuery();
                rs.next();
                usrId = rs.getString(1);
                rs.close();
                prepSelect.close();
                releaseConnection();
            } catch(SQLException sql){
                releaseConnection();
                Debug.print("SQL Exception in getNextUserId:" + sql.getMessage());
            }
            Debug.print("getNextUserId :" + usrId);
            
            objUserMaster.setUserId(usrId);
            remote = home.create(objUserMaster);
        } catch(Exception exp){
            throw new EJBException("addUserRegistration: " + exp.getMessage());
        }
        
        return usrId;
    }
    
    public void addContactDetails(HLCContactDetails objContact) throws RemoteException {
        this.objContact = objContact;
        String contactTypeId = null;;
        // Collection c = home.findContactTypeId(objContact.getContactTypeId());
        //return c;
        try{
            Collection c = home.findByContactTypeId(objContact.getContactType());
            ArrayList array = new ArrayList(c);
            for (Iterator it=array.iterator(); it.hasNext( ); ) {
                // Object anObject = it.next( );
                String str = String.valueOf(it.next( ));
                String[] result = str.split(":");
                for (int x=1; x<result.length; x++)
                    System.out.println(result[x]);
                contactTypeId = result[1];
            }
            objContact.setContactTypeId(contactTypeId);
            objContact.setUserId(objContact.getUserId());
            remote = home.create(objContact);
        } catch(Exception exp){
            throw new EJBException("addContactDetails: " + exp.getMessage());
        }
    }
    
    public String addFamilyAddOn(HLCUserMaster objUserMaster,HLCContactDetails objContact) throws RemoteException {
        String contactTypeId = null;
        String passwd = null;
        String userId = null;
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try{
            
            /* Collection c = home.findByContactTypeId(objUserMaster.getCommunicationAddress());
             ArrayList array = new ArrayList(c);
                for (Iterator it=array.iterator(); it.hasNext( ); ) {
                   // Object anObject = it.next( );
                    String str = String.valueOf(it.next( ));
                    String[] result = str.split(":");
                     for (int x=1; x<result.length; x++)
                         System.out.println("Session Bean Contact Type ID : "+result[x]);
                     contactTypeId = result[1];
                }
             
             passwd = Password.getPassword();
             objUserMaster.setContactTypeId(contactTypeId);
             objUserMaster.setPassword(passwd);
             Debug.print("Contant Of objUserMaster : "+objUserMaster);
             remote = home.create(objUserMaster);
             **/
            
            String firstName = objUserMaster.getFirstName();
            String lastName = objUserMaster.getLastName();
            java.sql.Date dob = java.sql.Date.valueOf(objUserMaster.getDob());
            String gender = objUserMaster.getGender();
            String emailId = objUserMaster.getEmailId();
            String prefix = objUserMaster.getPrefix();
            String sufix = objUserMaster.getSufix();
            
            String user_type_id = objDAO.getUserType(objUserMaster.getUserTypeName());
            Debug.print("user_type_id :"+user_type_id);
            
            String address1 = objContact.getAddress1();
            String address2 = objContact.getAddress2();
            String city = objContact.getCity();
            String state = objContact.getState();
            String country = objContact.getCountry();
            String zip = objContact.getZip();
            String phoneNo = objContact.getPhoneNo();
            String mobileNo = objContact.getMobileNo();
            String faxNo = objContact.getFaxNo();
            
            
            userId = objDAO.insertUserDetails(firstName, lastName, dob, gender,
                    emailId,  prefix, sufix, user_type_id);
            
            Debug.print(" AddOns UserId" + userId);
            if(userId!=null && userId.trim().length()!=0){
                boolean result = objDAO.insertContactDetails(userId, address1, address2,  city,  state, country,
                        zip, phoneNo, mobileNo,  faxNo);
                Debug.print("   AddOns UserContact Result:" + result);
            }
            
        } catch(Exception exp){
            exp.printStackTrace();
            throw new EJBException("addFamilyAddOn: " + exp.getMessage());
            
        }
        
       /* try{
            // userId = objDAO.getUserId(objUserMaster.getEmailId());
        
             objContact.setContactTypeId(contactTypeId);
             //objContact.setUserId(userId);
             remote = home.create(objContact);
        }
        catch(Exception exp){
             throw new EJBException("addContactDetails: " + exp.getMessage());
        }
        **/
        return userId;
    }
    
   /*  public String getParentMemberId(String email) throws RemoteException {
         this.email = email;
    
        try {
               makeConnection();
               this.memberId = DBHelper.getMemberId(con, email);
        }catch (Exception e){
              releaseConnection();
            e.printStackTrace();
        }finally {
            releaseConnection();
        }
    
        return memberId;
     }
    */
    
    public String getParentMemberId(String loginName) throws RemoteException {
        //this.loginName = loginName;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        String memberId = "";
        
        try {
            //makeConnection();
            if(loginName!=null && loginName.trim().length()!=0){
                memberId = objDAO.getExistingMemberId(loginName);
            }
        }catch (Exception e){
            //releaseConnection();
            e.printStackTrace();
        }finally {
            //releaseConnection();
        }
        
        return memberId;
    }
    
    public HLCContactDetails getContactDetailBasedOnUserId(String userId) throws RemoteException {
        HLCContactDetails objCont = new HLCContactDetails();
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            objCont = objDAO.displayContactDetails(userId);
            Debug.print(" Contact Details  : \n"+objCont);
        }catch (Exception e){
            e.printStackTrace();
        }
        return objCont;
    }
    //deleteMemberDetail(String memberId)
    public boolean deleteMember(String memberId) throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            makeConnection();
            
            bol = objDAO.deleteMemberDetail(memberId);
            Debug.print(" Member Details Deletion Status : "+bol);
        }catch (Exception e){
            releaseConnection();
            e.printStackTrace();
        }finally {
            releaseConnection();
        }
        return bol;
    }
    
    public boolean updateLoginDate(String userId) throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            makeConnection();
            
            Debug.print(" Inside session bean updateLoginDate() userId :"+userId);
            
            bol = objDAO.updateLoginDate(userId);
            Debug.print(" objDAO.updateLoginDate(userId) : "+bol);
            
        }catch (Exception e){
            releaseConnection();
            e.printStackTrace();
        }finally {
            releaseConnection();
        }
        return bol;
    }
    
     /*
      *
      */
    
    
    
    public boolean updateAddOnExistingMember(String memberId,String statusName,String parentId,String paymentId,String memb_typ_id) throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            makeConnection();
            
            Debug.print(" Inside session bean updateAddOnExistingMember()");
            
            bol = objDAO.updateAddOnExistingMember(memberId,statusName,parentId,paymentId,memb_typ_id);
            Debug.print(" objDAO.updateAddOnExistingMember(memberId,statusName,parentId,paymentId) : "+bol);
            
        }catch (Exception e){
            releaseConnection();
            e.printStackTrace();
        }finally {
            releaseConnection();
        }
        return bol;
    }
    
    public String getEmailBasedOnUserId(String userId) throws RemoteException {
        String emailIdonUser="";
        boolean bol=false;
        
        try {
            makeConnection();
            
            Debug.print(" Inside session bean getEmailBasedOnUserId()");
            
            String str = "SELECT email_id FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE user_id = ? ";
            prepStmt = con.prepareStatement(str);
            
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            
            if (bol = rs.next()) {
                emailIdonUser = rs.getString(1);
                Debug.print("mail id exist : "+bol);
                Debug.print("mail id  : "+emailIdonUser);
            }
            
            rs.close();
            prepStmt.close();
            
        }catch (Exception e){
            Debug.print("Exception in session bean getEmailBasedOnUserId() :");
            releaseConnection();
            e.printStackTrace();
        }finally {
            
            releaseConnection();
        }
        return emailIdonUser;
    }
    
    public boolean deleteContactDetail(String userId, String contactTypeName) throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            makeConnection();
            contactTypeId = DBHelper.getContacttypeId(con,contactTypeName);
            Debug.print(" contactTypeName: "+contactTypeName+"  Contact Type Id : "+contactTypeId);
            bol = objDAO.deleteContactDetail(userId,contactTypeId);
            Debug.print(" Contact Details Deletion Status : "+bol);
        }catch (Exception e){
            releaseConnection();
            e.printStackTrace();
        }finally {
            releaseConnection();
        }
        return bol;
    }
    
    
    
    public void editUserDetails(HLCUserMaster objUserMaster, HLCContactDetails objContact) throws RemoteException{
        System.out.print("Email ID In Kavery Session Bean : "+objUserMaster.getEmailId());
        boolean bol = false;
        String commAddrId="";
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        if (objUserMaster == null && (objUserMaster.getEmailId()).equals("") ) {
            throw new EJBException("Email ID can't be empty");
        }
        
        makeConnection();
        Debug.print(" USer Id in Kavery Session Bean : "+objUserMaster.getUserId());
        if (objUserMaster.getUserId() == null) {
            try {
                userId = DBHelper.getUserId(con, objUserMaster.getEmailId());
                Debug.print(" User Id Based on email Id in Kavery Session Bean : "+userId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            this.userId = objUserMaster.getUserId();
        }
        
        if (objContact.getContactType() != null) {
            try {
                contactTypeId = DBHelper.getContacttypeId(con,objContact.getContactType());
                commAddrId = DBHelper.getContacttypeId(con,objUserMaster.getCommunicationAddress());
                Debug.print("commAddrId for comm name - "+objUserMaster.getCommunicationAddress()+" :" +commAddrId);
                objUserMaster.setCommunicationAddress(commAddrId);
                
                bol = objDAO.isContactTypeExist(contactTypeId,userId);
                Debug.print(" contact type Exist in Kavery Session Bean: "+bol);
                Debug.print(" Contact Details Received From JSP: \n"+objContact);
                
                if (bol == false){
                    objContact.setContactTypeId(contactTypeId);
                    remote = home.create(objContact);
                    Debug.print(" New Record added to the contact Details");
                    Debug.print(" Contact Details Received From JSP After insertion: \n"+objContact);
                    releaseConnection();
                    return;
                    
                }
                
            }
           
            catch (Exception e){
                releaseConnection();
                e.printStackTrace();
            }
        }
        
        releaseConnection();
        
        
        
        if (memberExists(userId) == false) {
            throw new EJBException("Email ID Not Exists : " + userId);
        }
        
        Debug.print("objUserMaster.getLoginName() :"+objUserMaster.getLoginName());
        Debug.print("objUserMaster.getEmailId() :"+objUserMaster.getEmailId());
        
        if (objUserMaster.getLoginName() != null){
            remote.setLoginName(objUserMaster.getLoginName());
        }
        
        
        if (objUserMaster.getActiveStatus() != null){
            remote.setActiveStatus(objUserMaster.getActiveStatus());
        }
        if (objUserMaster.getUserId() != null){
            remote.setUserId(objUserMaster.getUserId());
        }
        if (objUserMaster.getPrefix() != null){
            remote.setPrefix(objUserMaster.getPrefix());
        }
        if (objUserMaster.getFirstName() != null){
            remote.setFirstName(objUserMaster.getFirstName());
        }
        if (objUserMaster.getMiddleName() != null){
            remote.setMiddleName(objUserMaster.getMiddleName());
        }
        if (objUserMaster.getLastName() != null){
            remote.setLastName(objUserMaster.getLastName());
        }
        if (objUserMaster.getSufix() != null){
            remote.setSufix(objUserMaster.getSufix());
        }
        if (objUserMaster.getDob() != null){
            remote.setDob(objUserMaster.getDob());
        }
        if (objUserMaster.getGender() != null){
            remote.setGender(objUserMaster.getGender());
        }
        if (objUserMaster.getEmailId() != null){
            remote.setEmailId(objUserMaster.getEmailId());
        }
        if (objUserMaster.getUserCode() != null){
            remote.setUserCode(objUserMaster.getUserCode());
        }
        
        if (objUserMaster.getCommunicationAddress() != null){
            remote.setPrefAddress(commAddrId);
        }
        
        remote.setNonUseaEmailStatus(objUserMaster.isNonUseaEmailStatus());
        remote.setNonUseaMailingStatus(objUserMaster.isNonUseaMailingStatus());
        
        
          /*  if (objUserMaster.getPassword() != null){
                remote.setPassword(objUserMaster.getPassword());
                System.out.println("Email : ID "+objUserMaster.getEmailId());
            }
           **/
        if (objUserMaster.getSecretQuestion() != null){
            remote.setSecretQuestion(objUserMaster.getSecretQuestion());
        }
        if (objUserMaster.getSecretAnswer() != null){
            remote.setSecretAnswer(objUserMaster.getSecretAnswer());
        }
        
        if (objContact.getContactType() != null){
            remote.setContactType(objContact.getContactType());
            remote.setContactTypeId(contactTypeId);
        }
        
        if (objContact.getSuite() != null){
            remote.setSuite(objContact.getSuite());
        }
        if (objContact.getAddress1() != null){
            remote.setAddress1(objContact.getAddress1());
        }
        if (objContact.getAddress2() != null){
            remote.setAddress2(objContact.getAddress2());
        }
        if (objContact.getAddress2() != null){
            remote.setCity(objContact.getCity());
        }
        if (objContact.getState() != null){
            remote.setState(objContact.getState());
        }
        if (objContact.getCountry() != null){
            remote.setCountry(objContact.getCountry());
        }
        if (objContact.getCountry() != null){
            remote.setZip(objContact.getZip());
        }
        if (objContact.getPhoneNo() != null){
            remote.setPhoneNo(objContact.getPhoneNo());
        }
        if (objContact.getMobileNo() != null){
            remote.setMobileNo(objContact.getMobileNo());
        }
        if (objContact.getMobileNo() != null){
            remote.setFaxNo(objContact.getFaxNo());
        }
        
    }
    
    private boolean memberExists(String userId) {
        Debug.print("Kavery Session Bean memberExists");
        
        // if ( !(userId.equals(this.userId)) ) {
        try {
            remote = home.findByPrimaryKey(userId);
            this.mailId = mailId;
        } catch (Exception ex) {
            return false;
        }
        // }
        return true;
    }
    
    /**
     * Name         :getEmailId
     * Description  :This method will retrieve the member details for refund details table
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public Vector getUserIdByEmailId(String emailId) throws RemoteException {
        System.out.println("Email Id : "+emailId);
        Vector A = new Vector();
        try{
        Collection col = home.findByUserEmailId(emailId);
        }
        catch(FinderException e)
        {
            
        }
        ArrayList array = new ArrayList(col);
        for (Iterator it=array.iterator(); it.hasNext( ); ) {
            // Object anObject = it.next( );
            String str = String.valueOf(it.next( ));
            String[] result = str.split(":");
            for (int x=1; x<result.length; x++){
                System.out.println("USER Id : "+result[x]);
                A.add(result[x]);
            }
            
            //System.out.println( nonuseaOrgId); //anObject.toString() );
        }
        //Collection c = home.findByUserEmailId(emailId);
        return A;
    }
    
    /**
     * Name         :getEmailId
     * Description  :This method will retrieve the member details for refund details table
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    
    public Vector getUserIdByLoginName(String loginName) throws RemoteException {
        System.out.println("loginName : "+loginName);
        Vector A = new Vector();
        Collection col=null;
        try{
        col = home.findByUserLoginName(loginName);
        }
        catch(FinderException ne)
        {
            ne.printStackTrace();
        }
        ArrayList array = new ArrayList(col);
        for (Iterator it=array.iterator(); it.hasNext( ); ) {
            // Object anObject = it.next( );
            String str = String.valueOf(it.next( ));
            String[] result = str.split(":");
            for (int x=1; x<result.length; x++){
                System.out.println("USER Id : "+result[x]);
                A.add(result[x]);
            }
            
            //System.out.println( nonuseaOrgId); //anObject.toString() );
        }
        //Collection c = home.findByUserEmailId(emailId);
        return A;
    }
    
    //findByLoginmailId(String emailId, String pass)
    
   /* public Vector getLoginStatus(String emailId, String pass) throws RemoteException, SQLException {
        Vector V = new Vector();
        this.email = emailId;
        this.pass = pass;
        this.userId = null;
        this.userCode = null;
        this.firstName = null;
        this.userTypeId = null;
        this.userTypeName = null;
        this.memberId  =null;
        boolean bol = false;
        String status = null;
    
        try {
        makeConnection();
    
         String selectStatement = "SELECT A.user_id, A.user_code, A.first_name, A.user_type_id, A.email_id , A.login_name," +
                 " A.last_name , B.phone_no FROM  " + DBHelper.USEA_MMS_USERMASTER +
                " A, " + DBHelper.USEA_CONTACT_DETAILS + " WHERE  A.login_name = ? AND A.password = ? AND A.active_status = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, emailId);
        prepStmt.setString(2, pass);
        prepStmt.setBoolean(3, true);
        this.emailId=null;
        bol = false;
        rs = prepStmt.executeQuery();
        if (bol = rs.next()) {
            this.userId = rs.getString(1);
            this.userCode = rs.getString(2);
            this.firstName = rs.getString(3);
            this.userTypeId = rs.getString(4);
            this.emailId = rs.getString(5);
            this.loginName = rs.getString(6);
            this.lastName = rs.getString(7);
            this.phoneNo = rs.getString(8);
    
         }
        //Debug.print("Member Id : "+memberId+" Result Set Status : "+bol);
        //USEA_MMS_MEMBERDETAIL
        rs = null;
        
        String statement = "SELECT member_id FROM " +DBHelper.USEA_MMS_MEMBERDETAIL + "  WHERE user_id = ? ";
        prepStmt = con.prepareStatement(statement);
        System.out.println("USER ID : "+userId);
        prepStmt.setString(1, userId);
        rs = prepStmt.executeQuery();
         if (rs.next()) {
            this.memberId = rs.getString(1);
         }
        System.out.print(" Member ID : "+memberId+"  Boolean : "+bol);
    
        selectStatement = "SELECT user_type_name FROM  " +DBHelper.USEA_MMS_TYPEMASTER + "  WHERE user_type_id = ? ";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userTypeId);
        rs = prepStmt.executeQuery();
         if (rs.next()) {
            this.userTypeName = rs.getString(1);
         }
    
        if (bol){
            status = "true";
        }
        else
        {
             status = "false";
        }
    
        String [] userDet = {status,userId,userCode, firstName,userTypeName,emailId,memberId};
        V.add(userDet);
        releaseConnection();
        }catch(Exception e){
             releaseConnection();
            System.out.println("Errpr: "+e.getMessage());
        }finally {
             releaseConnection();
        }
    
    
        //Collection c = home.findByLoginmailId(emailId,pass);
        return V;
    }
    *
    **/
    
    public Vector getLoginStatus(String loginName, String pass) throws RemoteException, SQLException {
        Vector V = new Vector();
        this.loginName = loginName;
        this.pass = pass;
        this.userId = null;
        this.userCode = null;
        this.firstName = null;
        this.userTypeId = null;
        this.userTypeName = null;
        this.memberId  =null;
        String requestStatus="false";
        boolean bol = false;
        String status = null;
        String dbPassword=null;String decryptPassword=null;
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("sa");
        try {
            makeConnection();
            String query="select password from "+DBHelper.USEA_MMS_USERMASTER +" where login_name=?";
            prepStmt=con.prepareStatement(query);
            prepStmt.setString(1,loginName);
            rs=prepStmt.executeQuery();
            while(rs.next())
                  dbPassword=rs.getString(1);
            rs.close();prepStmt.close();releaseConnection();
        }
        catch(Exception er)
        {
            Debug.print("Exception occured : " + er.getMessage());
            releaseConnection();
        }  
        if(dbPassword!=null)
           {
               decryptPassword=textEncryptor.decrypt(dbPassword); 
               if(decryptPassword.equalsIgnoreCase(pass))
               {
        try {
            makeConnection();
            String selectStatement = "SELECT A.user_id, A.user_code, A.first_name, A.user_type_id, A.email_id , A.login_name, " +
                    " A.last_name , B.phone_no,A.request_status FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    " A, " + DBHelper.USEA_CONTACT_DETAILS + " B WHERE  A.contact_type_id = B.contact_type_id and A.user_id = B.user_id and A.login_name = ?" +
                    " AND A.password = ? AND A.active_status = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName);
            prepStmt.setString(2, dbPassword);
            prepStmt.setBoolean(3, true);
            this.emailId=null;
            bol = false;
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                this.userId = rs.getString(1);
                this.userCode = rs.getString(2);
                this.firstName = rs.getString(3);
                this.userTypeId = rs.getString(4);
                this.emailId = rs.getString(5);
                this.loginName = rs.getString(6);
                this.lastName = rs.getString(7);
                this.phoneNo = rs.getString(8);
                requestStatus=String.valueOf(rs.getBoolean(9));
            }
        } catch(Exception e1){
            Debug.print("Error in First Login Checking Query:" + e1.getMessage());
        } finally{
            if(rs!=null){
                rs.close();
            }
            
            if(prepStmt!=null){
                prepStmt.close();
            }
            releaseConnection();
        }
        
        Debug.print("1.UserId : "+userId+" Result Set Status :  " + bol);
        
        try {
            makeConnection();
            //USEA_MMS_MEMBERDETAIL
            //String statement = "SELECT member_id FROM " +DBHelper.USEA_MMS_MEMBERDETAIL + "  WHERE user_id = ? ";
            String statement = "SELECT A.member_id , B.priority_value FROM tblMemberDetails A, tblMembershipTypeMaster B WHERE " +
                    " A.membership_type_id = B.membership_type_id and A.user_id = ?";
            prepStmt = con.prepareStatement(statement);
            //Debug.print("USER ID : "+userId);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.memberId = rs.getString(1);
                this.periodValue = rs.getString(2);
            }
        } catch(Exception e2){
            Debug.print("Error in second Login Checking Query:" + e2.getMessage());
        } finally{
            if(rs!=null){
                rs.close();
            }
            
            if(prepStmt!=null){
                prepStmt.close();
            }
            releaseConnection();
        }
        
        Debug.print("2. Member ID : "+memberId+"  Boolean : "+bol);
               }
        }
        try{
            makeConnection();
            String selectStatement = "SELECT user_type_name FROM  " +DBHelper.USEA_MMS_TYPEMASTER + "  WHERE user_type_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.userTypeName = rs.getString(1);
            }
            
            if (bol){
                status = "true";
            } else {
                status = "false";
            }
        } catch(Exception e3){
            Debug.print("Error in Third Login Checking Query:" + e3.getMessage());
        } finally{
            if(rs!=null){
                rs.close();
            }
            
            if(prepStmt!=null){
                prepStmt.close();
            }
            releaseConnection();
        }
        
        Debug.print("3.UserTypeName : "+userTypeName);
        
        String [] userDet = {status,userId,userCode, firstName,userTypeName,emailId,memberId,loginName,lastName,phoneNo,periodValue,userTypeId,requestStatus};
        V.add(userDet);
        return V;
    }
    
    public Vector getLoginStatusByUserId(String userId) throws RemoteException, SQLException {
        Vector V = new Vector();
        this.loginName = loginName;
        this.pass = pass;
        this.userId = null;
        this.userCode = null;
        this.firstName = null;
        this.userTypeId = null;
        this.userTypeName = null;
        this.memberId  =null;
        boolean bol = false;
        String status = null;
        String request_status=null;
        
        try {
            makeConnection();
            String selectStatement = "SELECT A.user_id, A.user_code, A.first_name, A.user_type_id, A.email_id , A.login_name," +
                    " A.last_name , B.phone_no,A.request_status FROM  " + DBHelper.USEA_MMS_USERMASTER +
                    " A, " + DBHelper.USEA_CONTACT_DETAILS + " B WHERE  A.contact_type_id = B.contact_type_id and A.user_id = B.user_id and A.user_id = ?" +
                    " AND A.active_status = ?";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            //prepStmt.setString(2, pass);
            prepStmt.setBoolean(2, true);
            this.emailId=null;
            bol = false;
            rs = prepStmt.executeQuery();
            if (bol = rs.next()) {
                this.userId = rs.getString(1);
                this.userCode = rs.getString(2);
                this.firstName = rs.getString(3);
                this.userTypeId = rs.getString(4);
                this.emailId = rs.getString(5);
                this.loginName = rs.getString(6);
                this.lastName = rs.getString(7);
                this.phoneNo = rs.getString(8);
                request_status=String.valueOf(rs.getBoolean(9));
            }
        } catch(Exception e1){
            Debug.print("Error in First Login Checking Query:" + e1.getMessage());
        } finally{
            if(rs!=null){
                rs.close();
            }
            
            if(prepStmt!=null){
                prepStmt.close();
            }
            releaseConnection();
        }
        
        Debug.print("1.UserId : "+userId+" Result Set Status :  " + bol);
        
        try {
            makeConnection();
            //USEA_MMS_MEMBERDETAIL
            //String statement = "SELECT member_id FROM " +DBHelper.USEA_MMS_MEMBERDETAIL + "  WHERE user_id = ? ";
            String statement = "SELECT A.member_id , B.priority_value FROM tblMemberDetails A, tblMembershipTypeMaster B WHERE " +
                    " A.membership_type_id = B.membership_type_id and A.user_id = ?";
            prepStmt = con.prepareStatement(statement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.memberId = rs.getString(1);
                this.periodValue = rs.getString(2);
            }
        } catch(Exception e2){
            Debug.print("Error in Second Login Checking Query:" + e2.getMessage());
        } finally{
            if(rs!=null){
                rs.close();
            }
            
            if(prepStmt!=null){
                prepStmt.close();
            }
            releaseConnection();
        }
        
        Debug.print("2. Member ID : "+memberId+"  Boolean : "+bol);
        try{
            makeConnection();
            String selectStatement = "SELECT user_type_name FROM  " +DBHelper.USEA_MMS_TYPEMASTER + "  WHERE user_type_id = ? ";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userTypeId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.userTypeName = rs.getString(1);
            }
            
            if (bol){
                status = "true";
            } else {
                status = "false";
            }
        } catch(Exception e3){
            Debug.print("Error in First Login Checking Query:" + e3.getMessage());
        } finally{
            if(rs!=null){
                rs.close();
            }
            
            if(prepStmt!=null){
                prepStmt.close();
            }
            releaseConnection();
        }
        
        Debug.print("3. UserTypeName : "+userTypeName);
        String [] userDet = {status,userId,userCode, firstName,userTypeName,emailId,memberId,loginName,lastName,phoneNo,periodValue,userTypeId,request_status};
        V.add(userDet);
        //releaseConnection();
        //Collection c = home.findByLoginmailId(emailId,pass);
        return V;
    }
    
    /**
     * Name         :displayMemberDetail
     * Description  :This method will retrieve the member details based on member Id
     * @ param      :member id
     * @return      :MemberDetails VO
     * @throws      :RemoteException
     */
    public HLCMemberDetails displayMemberDetail(String memberId)throws RemoteException {
        
        HLCMemberDetails membVO = new HLCMemberDetails();
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            membVO = objDAO.getHumanMemberDetail(memberId);
        }catch (Exception e){
            e.printStackTrace();
            Debug.print("Error In displayMemberDetail "+e.getMessage());
        }
        return membVO;
    }
    
    /**
     * Name         :getEmailAndPassword
     * Description  :This method will retrieve email id and password based on dob, secret question and secret answer
     * @ param      :dob, secret question and secret answer
     * @return      :String array
     * @throws      :RemoteException
     */
    public String[] getEmailAndPassword(String dob, String secQes, String secAns)throws RemoteException {
        
        String[] email = new String[10];
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            email = objDAO.getEmailbasedOnDob(dob,secQes,secAns);
        }catch (Exception e){
            e.printStackTrace();
            Debug.print("Error In getEmailAndPassword "+e.getMessage());
        }
        return email;
    }
    /**
     * Name         :getEmailOnEmail
     * Description  :This method will retrieve email id and password based on dob, secret question and secret answer
     * @ param      :dob, secret question and secret answer
     * @return      :String array
     * @throws      :RemoteException
     */
    public String[] getEmailOnEmail(String emailId)throws RemoteException {
        
        String[] email = new String[10];
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            email = objDAO.getEmailBasedOnEmail(emailId);
        }catch (Exception e){
            e.printStackTrace();
            Debug.print("Error In getEmailOnEmail "+e.getMessage());
        }
        return email;
    }
    /**
     * Name         :deleteMemberDetail
     * Description  :This method will change the password for an user
     * @ param      :user id and changed pasword
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean changePassword(String userId, String pass)throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            bol = objDAO.changePasword(userId, pass);
            
        }catch (Exception e){
            e.printStackTrace();
            Debug.print("Error In changePassword "+e.getMessage());
        }
        return bol;
    }
    
    /**
     * Name         :deleteMemberDetail
     * Description  :This method will delete member detail based on member id
     * @ param      :member id
     * @return      :boolean
     * @throws      :RemoteException, FinderException
     */
    public boolean deleteMemberDetail(String memberId)throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            
            bol = objDAO.deleteMemberDetail(memberId);
            
            
        }catch (Exception e){
            e.printStackTrace();
            Debug.print("Error In deleteMemberDetail "+e.getMessage());
        }
        return bol;
    }
    /**
     * Name         :statusDeactivate
     * Description  :This method will deactivate the status of the member details based on member id
     * @ param      :member id
     * @return      :String
     * @throws      :RemoteException
     */
    public boolean statusDeactivate(String memberId)throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            
            bol = objDAO.deactivateStatus(memberId);
            
            
        }catch (Exception e){
            Debug.print("Error In statusDeactivate "+e.getMessage());
        }
        return bol;
    }
    /**
     * Name         :statusActivate
     * Description  :This method will deactivate the status of the User based on email id
     * @ param      :email id
     * @return      :String
     * @throws      :RemoteException
     */
    public boolean statusActivate(String emailId)throws RemoteException {
        boolean bol = false;
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            
            bol = objDAO.activateStatus(emailId);
            
            
        }catch (Exception e){
            Debug.print("Error In statusActivate "+e.getMessage());
        }
        return bol;
    }
    /**
     * Name         :getFamilyAddOnPrice
     * Description  :This method will retrieve the member price and member Type Id based on member type name
     * @ param      :memberTypeName
     * @return      :String
     * @throws      :RemoteException
     */
    public String[] getFamilyAddOnPrice(String memberTypeName)throws RemoteException {
        
        String priceAndId[] = new String[3];
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            
            priceAndId = objDAO.listFamiltAddOnPrice(memberTypeName);
            
            
        }catch (Exception e){
            Debug.print("Error In getFamilyAddOnPrice "+e.getMessage());
        }
        return priceAndId;
    }
    
    
    public boolean editMemberDetail(HLCMemberDetails objMemDet)throws RemoteException {
        Vector vObj = new Vector();
        boolean bol = false;
        HLCMemberDetails objMem = new HLCMemberDetails();
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            //makeConnection();
            Debug.print("Member Id : "+objMemDet.getMemberId());
            //  vObj = objDAO.loadHumanMemberDetails(objMemDet.getMemberId());
            //  Debug.print("Member Id : "+objMemDet.getMemberId());
            //  String [] vStr = (String[])vObj.elementAt(0);
            //  Debug.print("Vector element is : "+vStr);
            //{memberId,userId,membershipTypeId,nonuseaOrgId,nonuseaOrgMemberId,countryMailTypeId,familyAddOns,parentMemberId,endowmentTrustAmount,statusId };
            
            
          /*  this.memberId = vStr[0];
            objMem.setUserId(this.userId = vStr[1]);
            objMem.setMembershipTypeId(this.membershipTypeId = vStr[2]);
            objMem.setNonuseaOrgId(this.nonuseaOrgId = vStr[3]);
            objMem.setNonUseaOrgMemberId(this.nonuseaOrgMemberId = vStr[4]);
            objMem.setCountryMailTypeId(this.countryMailTypeId = vStr[5]);
            objMem.setFamilyAddOns(this.familyAddOns = vStr[6]);
            objMem.setParentMemberId(this.parentMemberId = vStr[7]);
            objMem.setEndowmentTrustAmount(this.endowmentTrustAmount = vStr[8]);
            objMem.setStatusId(this.statusId = vStr[9]);*/
            
            objMem.setMemberId(objMemDet.getMemberId());
            if (objMemDet.getUserId() != null){
                objMem.setUserId(objMemDet.getUserId());
            }
            if (objMemDet.getMembershipTypeId() != null){
                objMem.setMembershipTypeId(objMemDet.getMembershipTypeId());
            }
            if (objMemDet.getMembershipTypeName() != null){
                objMem.setMembershipTypeName(objMemDet.getMembershipTypeName());
            }
            if (objMemDet.getNonuseaOrgId() != null){
                objMem.setNonuseaOrgId(objMemDet.getNonuseaOrgId());
            }
            if (objMemDet.getNonUseaOrgMemberId() != null){
                objMem.setNonUseaOrgMemberId(objMemDet.getNonUseaOrgMemberId());
            }
            if (objMemDet.getCountryMailTypeId() != null){
                objMem.setCountryMailTypeId(objMemDet.getCountryMailTypeId());
            }
            if (objMemDet.getFamilyAddOns() != null){
                objMem.setFamilyAddOns(objMemDet.getFamilyAddOns());
            }
            if (objMemDet.getParentMemberId() != null){
                objMem.setParentMemberId(objMemDet.getParentMemberId());
            }
            if (objMemDet.getEndowmentTrustAmount() != null){
                objMem.setEndowmentTrustAmount(objMemDet.getEndowmentTrustAmount());
            }
            if (objMemDet.getStatusId() != null){
                objMem.setStatusId(objMemDet.getStatusId());
            }
            if (objMemDet.getPaymentId() != null){
                objMem.setPaymentId(objMemDet.getPaymentId());
            }
            Debug.print(" SubExpDate : \n"+objMemDet.getSubExpDate());
            if (objMemDet.getSubExpDate() != null){
                objMem.setSubExpDate(objMemDet.getSubExpDate());
            }
            objMem.setAmateurName(objMemDet.getAmateurName());
            objMem.setAmateurDec1(objMemDet.isAmateurDec1());
            objMem.setAmateurDec2(objMemDet.isAmateurDec2());
            objMem.setArmbandQty(objMemDet.getArmbandQty());
            objMem.setRenewalStatus(objMemDet.isRenewalStatus());
            Debug.print("Status ID : "+objMem.getStatusId());
            Debug.print(" Member Details : \n"+objMem);
            bol = objDAO.updateHumanMemberDetails(objMem);
            Debug.print(" Result for Update Member Details: "+bol);
            
        }catch (Exception e){
            // releaseConnection();
            Debug.print("Error In updateMemberDetail "+e.getMessage());
        }
        return bol;
    }
    
    
    public boolean updateFamilyMemeber(String memberId,String paymentId)throws RemoteException {
        Vector vObj = new Vector();
        boolean bol = false;
        Debug.print(" In side the Session Bean memberId :"+memberId);
        Debug.print(" In side the Session Bean paymentId :"+paymentId);
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            //makeConnection();
            
            bol = objDAO.updatFamilyAddOn(memberId,paymentId);
            Debug.print(" Result for Update Family Member Details: "+bol);
            
            //releaseConnection();
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error In updateFamilyMemeber "+e.getMessage());
        }
        
        //releaseConnection();
        return bol;
    }
    
    public boolean updatMemberFamilyAddOns(String memberId, int familyAddOns) throws RemoteException {
        boolean bol = false;
        Debug.print(" updatMemberFamilyAddOns memberId :" + memberId);
        Debug.print(" updatMemberFamilyAddOns familyAddOns :" + familyAddOns);
        
        try {
            bol = new HLCMemberUpdateDAO().updatMemberFamilyAddOns(memberId, familyAddOns);
            Debug.print(" Result for updatMemberFamilyAddOns Details Result: " + bol);
            
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error In updatMemberFamilyAddOns "+e.getMessage());
        }
        
        return bol;
    }
    /**
     * Name         :getStatusIBasedOnStatus
     * Description  :This method will retrieve the status id based on the status name
     * @ param      :status
     * @return      :String
     * @throws      :RemoteException, FinderException
     */
    public String getStatusIBasedOnStatus(String status)throws RemoteException {
        String statusId = null;
        Debug.print(" In side the Session Bean status name"+status);
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            makeConnection();
            
            statusId = objDAO.getstatusId(status);
            Debug.print(" Status id : "+statusId);
            
            releaseConnection();
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error In getStatusIBasedOnStatus "+e.getMessage());
        }finally {
            releaseConnection();
        }
        return statusId;
    }
    
    public String getUserIdBasedOnMemberId(String memberId) throws RemoteException {
        
        String userId = "";
        if(memberId!=null && memberId.trim().length()!=0){
            userId = selectUserId(memberId);
        }
        Debug.print(" getUserIdBasedOnMemberId userId:" + userId);
        return userId;
    }
    
    public String getMembershipTypeIdBasedOnUserId(String userId) throws RemoteException {
        
        String membTypeId = null;
        Debug.print(" userId in getMembershipTypeIdBasedOnUserId() :"+userId);
        
        try {
            makeConnection();
            String selectStatement = "SELECT membership_type_id FROM  " +DBHelper.USEA_MMS_MEMBERDETAIL + "  WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            
            while(rs.next())
                membTypeId = rs.getString(1);
            
            prepStmt.close();
            
            releaseConnection();
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error in getUserCode");
        } finally {
            releaseConnection();
        }
        
        Debug.print(" membTypeId in getMembershipTypeIdBasedOnUserId() :"+membTypeId);
        return membTypeId;
    }
    
    /**
     * Name         :getSearchDetails
     * Description  :This method will retrieve the member details based on the search criteria
     * @ param      :firstName, lastName, frmDate, toDate, userTypeId
     * @return      :Collection
     * @throws      :RemoteException, FinderException
     */
    public Collection getSearchDetails(String firstName, String lastName, Date frmDate, Date toDate, String userTypeId) throws RemoteException {
        Collection c=null;
        try{
         c = home.findBySearchcondition(firstName, lastName, frmDate, toDate, userTypeId);
        }
        catch(FinderException er)
        {
            
        }
        return c;
    }
    //findBySearchcondition(String firstName, String lastName, Date frmDate, Date toDate, String userTypeId)
    /**
     * Name         :getJuniorMemberAge
     * Description  :This method will retrieve the member details for refund details table
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public int getJuniorMemberAge(String emailId, String memberTypeId) throws RemoteException {
        int age = 0;
        Vector A = new Vector();
        try {
            Collection col = home.findByJuniorMemberEmailId(emailId,memberTypeId);
            ArrayList array = new ArrayList(col);
            for (Iterator it=array.iterator(); it.hasNext( ); ) {
                // Object anObject = it.next( );
                String str = String.valueOf(it.next( ));
                String[] result = str.split(":");
                for (int x=1; x<result.length; x++){
                    System.out.println("Age :  "+result[x]);
                    A.add(result[x]);
                    age = Integer.parseInt(result[x]);
                }
                this.emailId =emailId;
            }
        }catch (Exception e) {
            Debug.print("Error while getting age");
            //}
            //String dateOfBirth = null;
            //ArrayList array = new ArrayList();
            //String dateOfBirth = null;
            //int age = 0;
       /* try {
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
                dateOfBirth = rs.getString(2);
             }
             prepStmt.close();
             releaseConnection();
            if (memberTypeName.startsWith("Junior Member")){
                 age = DBHelper.getAge(dateOfBirth);
               // A.add(age);
            }*/
            
            releaseConnection();
            //} catch (Exception sqe) {
            // throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        //System.out.println( nonuseaOrgId); //anObject.toString() );
        
        // Collection c = home.findByJuniorMemberEmailId(emailId,memberTypeId);
        return age;
    }
    
    public String getFamilyaddOn(String memberId) throws RemoteException {
        
        String memberTypeName=null;
        //Vector A = new Vector();
        try {
            Collection col = home.findByFamilyAddOnMemberID(emailId);
            ArrayList array = new ArrayList(col);
            for (Iterator it=array.iterator(); it.hasNext( ); ) {
                // Object anObject = it.next( );
                String str = String.valueOf(it.next( ));
                String[] result = str.split(":");
                for (int x=1; x<result.length; x++){
                    System.out.println("Name : "+result[x]);
                    memberTypeName = result[x];
                }
                
                System.out.println( "memberTypeName  :  "+memberTypeName); //anObject.toString() );
            }
            // Collection c = home.findByFamilyAddOnMemberID( memberId);
        }catch (Exception e){
            Debug.print("Error Message");
        }
        return memberTypeName;
    }
    
    public boolean getUserCode(String userCode)throws RemoteException {
        boolean flag = true;
        this.userCode = userCode;
        try {
            makeConnection();
            String selectStatement = "SELECT * FROM  " +DBHelper.USEA_MMS_USERMASTER + "  WHERE user_code = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userCode);
            
            rs = prepStmt.executeQuery();
            flag = rs.next();
            prepStmt.close();
            
            releaseConnection();
        }catch (Exception e){
            Debug.print("Error in getUserCode");
        }
        return flag;
    }
    //public UserMaster getUserDetails(String userId) throws RemoteException
    
    public List getUserDetails(String userId)throws RemoteException {
        boolean flag = true;
        HLCUserMaster objUser = new HLCUserMaster();
        List lst = new ArrayList();
        Map map = null;
        try {
            makeConnection();
            String selectStatement =
                    "SELECT A.first_name,A.middle_name,A.last_name,A.email_id,B.address1,B.address2,B.city,B.state,B.country,B.zip,B.phone_no,B.mobile_no,B.fax_no FROM  "+
                    DBHelper.USEA_MMS_USERMASTER+" A, "+DBHelper.USEA_CONTACT_DETAILS+" B WHERE A.user_id = B.user_id AND WHERE A.user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()){
                map = new HashMap();
               /*objUser.setUserCode(rs.getString("first_name"));
                objUser.setUserCode(rs.getString("middle_name"));
                objUser.setUserCode(rs.getString("last_name"));
                objUser.setUserCode(rs.getString("email_id"));
                objUser.setAddress1(rs.getString("address1"));
                objUser.setAddress2(rs.getString("address2"));
                objUser.setCity(rs.getString("city"));
                objUser.setState(rs.getString("state"));
                objUser.setCountry(rs.getString("country"));
                objUser.setZip(rs.getString("zip"));
                objUser.setPhoneNo(rs.getString("phone_no"));
                objUser.setMobileNo(rs.getString("mobile_no"));
                objUser.setFaxNo(rs.getString("fax_no"));*/
                
                map.put("First Name", rs.getString("first_name"));
                map.put("Middle Name", rs.getString("middle_name"));
                map.put("Last Name", rs.getString("last_name"));
                map.put("Email Id", rs.getString("email_id"));
                map.put("Address1", rs.getString("address1"));
                map.put("Address2", rs.getString("address2"));
                map.put("City", rs.getString("city"));
                map.put("State", rs.getString("state"));
                map.put("Country", rs.getString("country"));
                map.put("Zip", rs.getString("zip"));
                map.put("Phone Number", rs.getString("phone_no"));
                map.put("Mobile Number", rs.getString("mobile_no"));
                map.put("Fax Number", rs.getString("fax_no"));
                lst.add(map);
                
                
            }
            prepStmt.close();
            
            releaseConnection();
        }catch (Exception e){
            Debug.print("Error in getUserCode");
        }
        return lst;
    }
    
    public Date getUserBirthDate(String userId)throws RemoteException {
        Debug.print("getUserBirthDate:" + userId);
        boolean flag = true;
        Date dob = null;
        try {
            makeConnection();
            String selectStatement = "SELECT dob FROM  " +  DBHelper.USEA_MMS_USERMASTER + " where user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()){
                dob =rs.getDate(1);
            }
            prepStmt.close();
            
            releaseConnection();
        }catch (Exception e){
            Debug.print("Error in getUser DOB");
        }
        Debug.print("getUserBirthDate Date:" + dob);
        return dob;
    }
    
    public boolean isMemberIdExist(String memberId)throws RemoteException {
        boolean flag = true;
        this.memberId = memberId.trim();
        String memId=memberId.trim();
        Debug.print("memberId in isMemberIdExist :"+memId);
        try {
            makeConnection();
            String selectStatement = "SELECT member_id FROM  " +DBHelper.USEA_MMS_MEMBERDETAIL + "  WHERE member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memId);
            
            rs = prepStmt.executeQuery();
            flag = rs.next();
            prepStmt.close();
            
            releaseConnection();
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error in getUserCode");
        }
        return flag;
    }
    
    public boolean isMemberExist(String userId) throws RemoteException{
        boolean result =false;
        if(userId!=null && userId.trim().length()!=0){
            try{
                result = new HLCMemberUpdateDAO().isMemberExist(userId);
            } catch(Exception e){
                Debug.print("Exception while calling isMemberExist():" + e.getMessage());
            }
        }
        return result;
    }
    
    public boolean isMemberExistBasedOnMemberAndUserId(String memberId, String userId) throws RemoteException {
        boolean result =false;
        if(memberId!=null && memberId.trim().length()!=0 && userId!=null && userId.trim().length()!=0){
            try{
                result = new HLCMemberUpdateDAO().isMemberExistBasedOnMemberAndUserId(memberId, userId);
            } catch(Exception e){
                Debug.print("Exception while calling isMemberExist():" + e.getMessage());
            }
        }
        return result;
    }
    
    // public Vector display
    public String  getStatusByMemberId(String memberId) throws RemoteException{
        String  statusName = "";
        if(memberId!=null && memberId.trim().length()!=0){
            try{
                statusName = new HLCMemberUpdateDAO().selectStatusByMemberId(memberId);
            } catch(Exception e){
                Debug.print("Exception while calling isMemberExist():" + e.getMessage());
            }
        }
        return statusName;
    }
    
    public ArrayList checkUserExist(String firstName, String lastName, String emailId, String zip) throws RemoteException {
        Debug.print("kaverystatelessBean checkUserExist()");
        ArrayList results = new ArrayList();
        if(firstName!=null && firstName.trim().length()!=0 && lastName!=null && lastName.trim().length()!=0 && emailId!=null&& zip!=null ){
            try{
                results = new HLCMemberUpdateDAO().checkUserExist(firstName,lastName,emailId,zip);
            } catch(Exception e){
                Debug.print("Exception while calling kaverystatelessBean checkUserExist():" + e.getMessage());
            }
        }
        return results;
    }
    
    public String getSecretQuestion(String userId) throws RemoteException {
        Debug.print("kaverystatelessBean getSecretQuestion()");
        String secretQuestion = "";
        ArrayList results = new ArrayList();
        if(userId!=null && userId.trim().length()!=0){
            try{
                secretQuestion = new HLCMemberUpdateDAO().getSecretQuestion(userId);
            } catch(Exception e){
                Debug.print("Exception while calling kaverystatelessBean getSecretQuestion():" + e.getMessage());
            }
        }
        return secretQuestion;
    }
    
    public String getSecretQuestionByLoginName(String loginName) throws RemoteException {
        Debug.print("kaverystatelessBean getSecretQuestionByLoginName()");
        String secretQuestion= "";
        ArrayList results = new ArrayList();
        if(loginName!=null && loginName.trim().length()!=0){
            try{
                secretQuestion = new HLCMemberUpdateDAO().getSecretQuestionByLoginName(loginName);
            } catch(Exception e){
                Debug.print("Exception while calling kaverystatelessBean getSecretQuestionByLoginName():" + e.getMessage());
            }
        }
        return secretQuestion;
    }
    
    
    public boolean checkSecurityQuestion(String userId, String secretQuestion, String secretAnswer) throws RemoteException {
        Debug.print("kaverystatelessBean checkSecurityQuestion() secretQuestion:" + secretQuestion);
        Debug.print("kaverystatelessBean checkSecurityQuestion() secretAnswer:" + secretAnswer);
        Debug.print("kaverystatelessBean checkSecurityQuestion() userId:" + userId);
        boolean result =false;
        if(userId!=null && userId.trim().length()!=0 && secretQuestion!=null && secretQuestion.trim().length()!=0){
            try{
                Debug.print("kaverystatelessBean checkSecurityQuestion() calling:");
                result = new HLCMemberUpdateDAO().checkSecurityQuestion(userId,secretQuestion, secretAnswer);
            } catch(Exception e){
                Debug.print("Exception while calling isMemberExist():" + e.getMessage());
            }
        }
        return result;
    }
    
    public boolean checkSecurityQuestionByLoginName(String loginName, String secretQuestion, String secretAnswer) throws RemoteException {
        Debug.print("kaverystatelessBean checkSecurityQuestionByLoginName() secretQuestion:" + secretQuestion);
        Debug.print("kaverystatelessBean checkSecurityQuestionByLoginName() secretAnswer:" + secretAnswer);
        Debug.print("kaverystatelessBean checkSecurityQuestionByLoginName() userId:" + loginName);
        boolean result =false;
        if(loginName!=null && loginName.trim().length()!=0 && secretQuestion!=null && secretQuestion.trim().length()!=0){
            try{
                Debug.print("kaverystatelessBean checkSecurityQuestion() calling:");
                result = new HLCMemberUpdateDAO().checkSecurityQuestionByLoginName(loginName,secretQuestion, secretAnswer);
            } catch(Exception e){
                Debug.print("Exception while calling isMemberExist():" + e.getMessage());
            }
        }
        return result;
    }
    
    public boolean checkUserNameExist(String loginName) throws RemoteException {
        Debug.print("kaverystatelessBean checkUserNameExist() loginName:" + loginName);
        boolean result =false;
        if(loginName!=null && loginName.trim().length()!=0 ){
            try{
                Debug.print("kaverystatelessBean checkSecurityQuestion() calling:");
                result = new HLCMemberUpdateDAO().checkUserNameExist(loginName);
            } catch(Exception e){
                Debug.print("Exception while calling checkUserNameExist():" + e.getMessage());
            }
        }
        Debug.print("kaverystatelessBean checkUserNameExist() loginName:" + result);
        return result;
    }
    
    public boolean checkUserCodeExist(String userCode) throws RemoteException {
        Debug.print("kaverystatelessBean checkUserCodeExist(String userCode) userCode:" + userCode);
        boolean result =false;
        if(userCode!=null && userCode.trim().length()!=0 ){
            try{
                Debug.print("kaverystatelessBean checkUserCodeExist() calling:");
                result = new HLCMemberUpdateDAO().checkUserCodeExist(userCode);
            } catch(Exception e){
                Debug.print("Exception while calling checkUserCodeExist(String userCode):" + e.getMessage());
            }
        }
        Debug.print("kaverystatelessBean checkUserCodeExist(String userCode) :" + result);
        return result;
    }
    
    public String[] getLoginDetails(String userId) throws RemoteException{
        Debug.print("kaverystatelessBean getLoginDetails");
        String[] results = new HLCMemberUpdateDAO().selectLoginDetails(userId);
        return results;
    }
    
    public ArrayList getLoginDetailsByEmailId(String emailId) throws RemoteException{
        Debug.print("kaverystatelessBean getLoginDetails");
        ArrayList results = new HLCMemberUpdateDAO().selectLoginDetailsByEmailId(emailId);
        return results;
    }
    
    public String getPasswordByLoginName(String loginName) throws RemoteException {
        Debug.print("kaverystatelessBean getPasswordByLoginName");
        String password = new HLCMemberUpdateDAO().selectPasswordByLoginName(loginName);
        return password;
    }
    
    public String getEmailByLoginName(String loginName) throws RemoteException {
        Debug.print("kaverystatelessBean getPasswordByLoginName");
        String emailId = new HLCMemberUpdateDAO().selectEmailByLoginName(loginName);
        return emailId;
    }
    public String getUserIDByLoginName(String loginName) throws RemoteException {
        Debug.print("kaverystatelessBean getPasswordByLoginName");
        String emailId = new HLCMemberUpdateDAO().selectUserIDByLoginName(loginName);
        return emailId;
    }
    public Date getExpiredDate(String memberId) throws RemoteException {
        Debug.print("kaverystatelessBean getExpiredDate MemberId:" + memberId);
        Date expDate = null;
        try{
            expDate = new HLCMemberUpdateDAO().selectExpiredDate(memberId);
        } catch(Exception exp){
            Debug.print("Exception in kaverystatelessBean getExpiredDate():" + exp.getMessage());
        }
        
        return expDate;
    }
    
    public ArrayList getMebershipTypeBasedOnPeriodValue(String userTypeId, int periodValue, String statusGrade, int memb_year) throws RemoteException{
        Debug.print("kaverystatelessBean getMebershipTypeBasedOnPeriodValue");
        ArrayList results = new HLCMemberUpdateDAO().selectMebershipTypeBasedOnPeriodValue(userTypeId, periodValue, statusGrade, memb_year);
        Debug.print("kaverystatelessBean getMebershipTypeBasedOnPeriodValue results:" + results.size());
        return results;
    }
    
    
    
    
    
    public boolean updateLoginDetails(String userId, String loginName, String  password) throws RemoteException{
        Debug.print("kaverystatelessBean updateLoginDetails");
        boolean result = new HLCMemberUpdateDAO().updateLoginDetails(userId, loginName, password );
        return result;
    }

    
      public boolean deactivateRequestStatus(String userId, boolean active_status) throws RemoteException
    {
        Debug.print("kaverystatelessBean:: deactivateRequestStatus() ");
        boolean result = (new HLCMemberUpdateDAO()).deactivateRequestStatus(userId, active_status);
        Debug.print((new StringBuilder()).append("result---------->").append(result).toString());
        return result;
    }
    public boolean updateLoginDetailsByLoginName(String loginName, String  password) throws RemoteException{
        Debug.print("kaverystatelessBean updateLoginDetailsByLoginName");
        boolean result = new HLCMemberUpdateDAO().updateLoginDetailsByLoginName(loginName, password);
        return result;
    }
    
    
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        System.out.println("This is from getInitialContext()");
        return ic;
    }
    
    //=============================================Insert Nonusea Details =========================================
    public boolean insertNonUseaPriceDetails(String memberId, String nonOrgId,  String  nonUseaMember) throws RemoteException {
        Debug.print("kaverystatelessBean.insertAnnualRegistrationPriceDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblMemberNonUseaOrgDetails " +
                    " (member_id, nonusea_org_id, nonusea_member_id)" +
                    " values ( ?, ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, memberId);
            prepStmt.setString(2, nonOrgId);
            prepStmt.setString(3, nonUseaMember);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into kaverystatelessBean insertAnnualRegistrationPriceDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("kaverystatelessBean insertAnnualRegistrationPriceDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.insertAnnualRegistrationPriceDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.insertAnnualRegistrationPriceDetails():" + e.getMessage());
        }
        return result;
    }
    
    //=============================================Insert Donation Details =========================================
    public boolean insertDonationPriceDetails(String userId, String donationId,  String  donationPrice, String donatedBy, String paymentId)  throws RemoteException {
        Debug.print("kaverystatelessBean.insertDonationPriceDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblMemberDonationDetails " +
                    " (user_id, donation_id, donation_price, donated_by, payment_id)" +
                    " values ( ?, ?, ?, ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, userId);
            prepStmt.setString(2, donationId);
            prepStmt.setString(3, donationPrice);
            prepStmt.setString(4, donatedBy);
            prepStmt.setString(5, paymentId);
            Debug.print("paymentId :"+paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into kaverystatelessBean insertDonationPriceDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("kaverystatelessBean insertDonationPriceDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.insertDonationPriceDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.insertDonationPriceDetails():" + e.getMessage());
        }
        return result;
    }
    
    public boolean insertWebSiteDonationDetails(String userId, String donationId,  String  donationPrice, String donatedBy, String paymentId)  throws RemoteException {
        Debug.print("kaverystatelessBean.insertWebSiteDonationDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblMemberDonationDetails " +
                    " (user_id, donation_id, donation_price, donated_by, donation_payment_id)" +
                    " values ( ?, ?, ?, ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, userId);
            prepStmt.setString(2, donationId);
            prepStmt.setString(3, donationPrice);
            prepStmt.setString(4, donatedBy);
            prepStmt.setString(5, paymentId);
            Debug.print("paymentId :"+paymentId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into kaverystatelessBean insertWebSiteDonationDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("kaverystatelessBean insertWebSiteDonationDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.insertWebSiteDonationDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.insertWebSiteDonationDetails():" + e.getMessage());
        }
        return result;
    }
    public boolean deleteNonUseaPriceDetails(String memberId) throws RemoteException{
        System.out.println("Inside deleteNonUseaPriceDetails memberId:  "+memberId);
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "DELETE FROM tblMemberNonUseaOrgDetails WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, memberId);
            int cnt = prepStmt.executeUpdate();
            if(cnt>=1){
                result = true;
            }
            Debug.print("Successfully Delete the deleteNonUseaPriceDetails. No Of Record : "+cnt);
            prepStmt.close();
        } catch (Exception ex) {
            //releaseConnection();
            Debug.print("deleteNonUseaPriceDetails : " + ex.getMessage());
        }finally{
            releaseConnection();
        }
        return result;
    }
    
    
    public boolean deleteDonationPriceDetails(String userId) throws RemoteException {
        System.out.println("Inside deleteDonationPriceDetails userId:  "+userId);
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "DELETE FROM tblMemberDonationDetails WHERE user_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, userId);
            int cnt = prepStmt.executeUpdate();
            if(cnt>=1){
                result = true;
            }
            Debug.print("Successfully Delete the deleteDonationPriceDetails. No Of Record : "+cnt);
            prepStmt.close();
        } catch (Exception ex) {
            //releaseConnection();
            Debug.print("deleteDonationPriceDetails : " + ex.getMessage());
        }finally{
            releaseConnection();
        }
        return result;
    }
    
    
    public boolean deletePublicationDetails(String memberId) throws RemoteException {
        System.out.println("Inside deletePublicationDetails memberId:  "+memberId);
        boolean result = false;
        makeConnection();
        try {
            String deleteStatement = "DELETE FROM tblPublicationDetails WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, memberId);
            int cnt = prepStmt.executeUpdate();
            if(cnt>=1){
                result = true;
            }
            Debug.print("Successfully Delete the deletePublicationDetails. No Of Record : "+cnt);
            prepStmt.close();
        } catch (Exception ex) {
            //releaseConnection();
            Debug.print("deletePublicationDetails : " + ex.getMessage());
        }finally{
            releaseConnection();
        }
        return result;
    }
    
    //=============================================Insert Donation Details =========================================
    public boolean insertPublicationDetails(String memberId, String publicationId)  throws RemoteException {
        Debug.print("kaverystatelessBean.insertPublicationDetails():");
        boolean result = false;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into tblPublicationDetails " +
                    " (member_id, publication_id)" +
                    " values ( ?, ?) ";
            
            prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, memberId);
            prepStmt.setString(2, publicationId);
            
            int cnt = prepStmt.executeUpdate();
            Debug.print("Record Inserted succefully into kaverystatelessBean insertPublicationDetails "+cnt);
            if(cnt>=1){
                result = true;
            }
            
            Debug.print("kaverystatelessBean insertPublicationDetails() Status :" + result);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.insertPublicationDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.insertPublicationDetails():" + e.getMessage());
        }
        return result;
    }
    //------------------------ update amature status -----------------------------
    
    public boolean updateAmatureStatus(String memberId,String amatName,boolean posAmat,boolean decAmat) throws RemoteException{
        Debug.print("kaverystatelessBean updateAmatureStatus() memberId:" + memberId);
        boolean result =false;
        if(memberId!=null){
            try{
                Debug.print("MemberUpdateDAO updateAmatureStatus() calling:");
                result = new HLCMemberUpdateDAO().updateAmatureStatus(memberId,amatName,posAmat,decAmat);
            } catch(Exception e){
                Debug.print("Exception while calling updateAmatureStatus() in kaverystatelessBean :" + e.getMessage());
            }
        }
        return result;
    }
    
     //------------------------ update amateur status in History Details Table -----------------------------
    
    public boolean updateAmateurStatusInHistoryDets(String memberId, String amatName, boolean posAmat, boolean decAmat) throws RemoteException{
        Debug.print("kaverystatelessBean updateAmateurStatusInHistoryDets() memberId:" + memberId);
        boolean result =false;
        if(memberId!=null){
            try{
                Debug.print("MemberUpdateDAO updateAmateurStatusInHistoryDets() calling:");
                result = new HLCMemberUpdateDAO().updateAmateurStatusInHistoryDets(memberId,amatName,posAmat,decAmat);
            } catch(Exception e){
                Debug.print("Exception while calling updateAmateurStatusInHistoryDets() in kaverystatelessBean :" + e.getMessage());
            }
        }
        return result;
    }
    
    // ---------------- get activation date -------------------------
    
    public Date getApprovedDate(String memberId)throws RemoteException {
        Debug.print("getApprovedDate:" + memberId);
        
        Date actDate = null;
        try {
            makeConnection();
            String selectStatement = "SELECT activation_date FROM  " +  DBHelper.USEA_MMS_MEMBERDETAIL + " where member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            
            rs = prepStmt.executeQuery();
            if (rs.next()){
                actDate =rs.getDate(1);
            }
            rs.close();
            prepStmt.close();
            
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error in getApprovedDate :");
            e.printStackTrace();
        }
        
        finally {
            releaseConnection();
        }
        
        Debug.print("getApprovedDate :" + actDate);
        return actDate;
    }
    
    // - ---------------------------- update approval date ------------------------
    
    public boolean updateApprovalDate(String memberId,Date approveDate) throws RemoteException{
        Debug.print("kaverystatelessBean updateApprovalDate() memberId:" + memberId);
        Debug.print("kaverystatelessBean updateApprovalDate() approveDate:" + approveDate);
        
        boolean result =false;
        if(memberId!=null){
            try{
                Debug.print("MemberUpdateDAO updateApprovalDate() calling:");
                result = new HLCMemberUpdateDAO().updateApprovalDate(memberId,approveDate);
            } catch(Exception e){
                Debug.print("Exception while calling updateApprovalDate() in kaverystatelessBean :" + e.getMessage());
            }
        }
        return result;
    }
    
    //------------------clac exp dat -----------------------------------
    
    public Date calculateExpireDate(int interval,Date date) {
        Debug.print("kaverystatelessBean calculateExpireDate");
        makeConnection();
        try{
            String selectStatement = "SELECT DATEADD(month, " + interval + ", '" + DBHelper.toSQLDate(date) + "' ) AS TimeFrame";
            Debug.print("SponsorPaymentScheduleBean calculateExpireDate:" + selectStatement);
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            rs.next();
            Date nextRecurring = rs.getDate(1);
            rs.close();
            prepSelect.close();
            releaseConnection();
            Debug.print("SponsorPaymentScheduleBean calculateExpireDate:" + nextRecurring);
            return nextRecurring;
        } catch (SQLException e) {
            releaseConnection();
            throw new EJBException("Could not find any from calculateExpireDate");
        } catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in calculateExpireDate:" + e.getMessage());
        }
    }
    
    
    
    //=============================================Insert Donation Details =========================================
    public String selectMemberId()  throws RemoteException {
        Debug.print("kaverystatelessBean.selectMemberId():");
        //String memberId = "";;
        String memberId = null;
        PreparedStatement prepSelect = null;
        makeConnection();
        try {
            String selectStatement = "SELECT max(cast(member_id as int)) from tblMemberDetails ";
            
            prepSelect = con.prepareStatement(selectStatement);
            
            ResultSet rs = prepSelect.executeQuery();
            while (rs.next()) {
                memberId = rs.getString(1);
            }
            if (memberId == null)
                memberId = "0";
            
            long nextId = Long.valueOf(memberId).longValue();
            
            if(nextId==0){
                nextId = 10000;
            } else{
                nextId = nextId+1;
            }
            rs.close();
            prepSelect.close();
            memberId = Long.toString(nextId);
            Debug.print("kaverystatelessBean selectMemberId() Status memberId:" + memberId);
            //prepStmt.close();
            releaseConnection();
            Debug.print("kaverystatelessBean selectMemberId() Status memberId:" + memberId);
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.selectMemberId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.selectMemberId():" + e.getMessage());
        }
        Debug.print("kaverystatelessBean selectMemberId() Status Final MemberId memberId:" + memberId);
        return memberId;
    }
    
    public ArrayList getMemberDetailsForUsrSign(String memberId)throws RemoteException {
        
        Debug.print("getMemberDetailsForUsrSign(memberId) :"+memberId);
        String memId=memberId.trim();
        Debug.print("memberId in getMemberDetailsForUsrSign :"+memId);
        String statusID="";
        HLCUsrSignUpVO usrVo = new HLCUsrSignUpVO();
        ArrayList mbrDetails=new ArrayList();
        /*try{
                makeConnection();
                String selectStatement="select status_id from "+DBHelper.USEA_STATUS_MASTER+" where status_name='Duplicate'"; 
                Debug.print("selectStatement Query:"+selectStatement);
                prepStmt = con.prepareStatement(selectStatement);
                rs = prepStmt.executeQuery();
                while (rs.next()){
                 statusID=rs.getString(1);   
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
        }catch(Exception e){
            releaseConnection();
            Debug.print("Error in getMemberDetailsForUsrSign");
        }
        finally {
            Debug.print("inside finally ............");
            releaseConnection();
        }*/
        try {
            
            makeConnection();
            
            if(memId!=null) {
                /*String userId=selectUserId(memberId);
                Debug.print("selectUserId(memberId) :"+userId);*/
                
                
                String selectStatement = "select A.member_id, B.user_id, B.first_name, B.last_name, B.email_id, C.phone_no, C.zip, D.status_name from "+DBHelper.USEA_MMS_MEMBERDETAIL+" A, "
                        +DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_CONTACT_DETAILS+" C , "+DBHelper.USEA_STATUS_MASTER+" D where A.user_id = B.user_id and B.user_id = C.user_id and "+
                        "B.contact_type_id = C.contact_type_id and D.status_id=A.status_id and A.member_id = ?";
                //and A.status_id!=?
                Debug.print("selectStatement Query : "+selectStatement);
                
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, memId);
                //prepStmt.setString(2, statusID);
                
                rs = prepStmt.executeQuery();
                while (rs.next()){
                    
                    usrVo = new HLCUsrSignUpVO();
                    
                    usrVo.setMembid(rs.getString(1));
                    usrVo.setUserId(rs.getString(2));
                    usrVo.setFname(rs.getString(3));
                    usrVo.setLname(rs.getString(4));
                    usrVo.setEmail(rs.getString(5));
                    usrVo.setPhone(rs.getString(6));
                    usrVo.setZip(rs.getString(7));
                    usrVo.setStatusName(rs.getString(8));
                    
                    mbrDetails.add(usrVo);
                    Debug.print("UsrSignUpVO in Bean for rec no "+mbrDetails.size()+":"+usrVo.toString());
                    
                }
                prepStmt.close();
                
                releaseConnection();
                
            } else {
                Debug.print("getMemberDetailsForUsrSign(memberId) is null :"+memId);
            }
            
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error in getUserCode");
        } finally {
            
            Debug.print("inside finally ............");
            releaseConnection();
            
        }
        return mbrDetails;
    }
    
    public ArrayList getUserDetailsForUsrSign(String firstName,String lastName)throws RemoteException {
        
        Debug.print("getUserDetailsForUsrSign(String firstName,String lastName) :");
        
        Debug.print("firstName :"+firstName);
        Debug.print("lastName :"+lastName);
        
        String fName="";
        String lName="";
        
        HLCUsrSignUpVO usrVo = new HLCUsrSignUpVO();
        ArrayList usrDetails=new ArrayList();
        
        try {
            
            makeConnection();
            
            String selectStatement ="";
            
            if(firstName!=null && firstName.trim().length()!=0) {
                
                fName=firstName.trim()+"%";
                lName=lastName.trim()+"%";
                
                Debug.print("fName :"+fName);
                Debug.print("lName :"+lName);
                
                /*selectStatement= "select A.member_id, B.user_id, B.first_name, B.last_name, B.email_id, C.phone_no, C.zip from "+DBHelper.USEA_MMS_MEMBERDETAIL+" A, "
                                  +DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_CONTACT_DETAILS+" C where A.user_id = B.user_id and B.user_id = C.user_id "
                                  +"and B.contact_type_id = C.contact_type_id and B.first_name like ? and last_name like ?";*/
                
                selectStatement= "select A.user_id, A.first_name, A.last_name, A.email_id, B.phone_no, B.zip, C.member_id, D.status_name from "+DBHelper.USEA_MMS_USERMASTER+" A left join "+DBHelper.USEA_MMS_MEMBERDETAIL+" C ON "
                        +"C.user_id = A.user_id left join "+DBHelper.USEA_STATUS_MASTER+" D ON D.status_id = C.status_id left join "+DBHelper.USEA_CONTACT_DETAILS+" B ON B.user_id = A.user_id where A.contact_type_id = B.contact_type_id and A.first_name like ? and A.last_name like ?";
                
            } else {
                lName=lastName.trim()+"%";
                
                Debug.print("lName :"+lName);
                
               /* selectStatement= "select A.member_id, B.user_id, B.first_name, B.last_name, B.email_id, C.phone_no, C.zip from "+DBHelper.USEA_MMS_MEMBERDETAIL+" A, "
                                  +DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_CONTACT_DETAILS+" C where A.user_id = B.user_id and B.user_id = C.user_id "
                                  +"and B.contact_type_id = C.contact_type_id and last_name like ?";   */
                
                selectStatement= "select A.user_id, A.first_name, A.last_name, A.email_id, B.phone_no, B.zip, C.member_id , D.status_name from "+DBHelper.USEA_MMS_USERMASTER+" A left join "+DBHelper.USEA_MMS_MEMBERDETAIL+" C ON "
                        +"C.user_id = A.user_id left join "+DBHelper.USEA_STATUS_MASTER+" D ON D.status_id = C.status_id left join "+DBHelper.USEA_CONTACT_DETAILS+" B ON B.user_id = A.user_id where A.contact_type_id = B.contact_type_id and A.last_name like ?";
                
            }
            
            Debug.print("selectStatement Query : "+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            
            if(firstName!=null && firstName.trim().length()!=0) {
                prepStmt.setString(1, fName);
                Debug.print("prepStmt.setString(1, fName) :"+fName);
                
                prepStmt.setString(2, lName);
                Debug.print("prepStmt.setString(2, lName); :"+lName);
                
            } else {
                prepStmt.setString(1, lName);
                Debug.print("prepStmt.setString(1, lName); :"+lName);
                
            }
            
            rs = prepStmt.executeQuery();
            while (rs.next()){
                
                usrVo = new HLCUsrSignUpVO();
                
                usrVo.setUserId(rs.getString(1));
                usrVo.setFname(rs.getString(2));
                usrVo.setLname(rs.getString(3));
                usrVo.setEmail(rs.getString(4));
                usrVo.setPhone(rs.getString(5));
                usrVo.setZip(rs.getString(6));
                usrVo.setMembid(rs.getString(7));
                usrVo.setStatusName(rs.getString(8));
                usrDetails.add(usrVo);
                Debug.print("UsrSignUpVO in Bean for rec "+usrDetails.size()+" : "+usrVo.toString());
                
            }
            prepStmt.close();
            
            releaseConnection();
            
        }catch (Exception e){
            releaseConnection();
            Debug.print("Error in getUserCode");
        } finally {
            
            Debug.print("inside finally ............");
            releaseConnection();
            
        }
        
        return usrDetails;
    }
    
    public HLCUsrSignUpVO getUsrDetForUsrSignOnUsrId(String userId)throws RemoteException {
        
        Debug.print("getUsrDetForUsrSignOnUsrId(userId) :"+userId);
        
        HLCUsrSignUpVO usrVo = new HLCUsrSignUpVO();
        //ArrayList mbrDetails=new ArrayList();
        
        try {
            
            makeConnection();
            
               /*String userId=selectUserId(memberId);
                Debug.print("selectUserId(memberId) :"+userId);*/
            
            
            String selectStatement = "select A.user_id, A.first_name, A.last_name, A.email_id, B.phone_no, B.zip, C.member_id from "+DBHelper.USEA_MMS_USERMASTER+" A left join "+DBHelper.USEA_MMS_MEMBERDETAIL+" C ON "
                    +"C.user_id = A.user_id left join "+DBHelper.USEA_CONTACT_DETAILS+" B ON B.user_id = A.user_id where A.contact_type_id = B.contact_type_id and A.user_id= ?";
            
           /* String selectStatement = "select A.member_id, B.user_id, B.first_name, B.last_name, B.email_id, C.phone_no, C.zip from "+DBHelper.USEA_MMS_MEMBERDETAIL+" A, "
             +DBHelper.USEA_MMS_USERMASTER+" B, "+DBHelper.USEA_CONTACT_DETAILS+" C where A.user_id = B.user_id and B.user_id = C.user_id and "+
            "B.contact_type_id = C.contact_type_id and B.user_id = ?";*/
            
            Debug.print("selectStatement Query : "+selectStatement);
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            
            rs = prepStmt.executeQuery();
            while (rs.next()){
                
                usrVo = new HLCUsrSignUpVO();
                
                usrVo.setUserId(rs.getString(1));
                usrVo.setFname(rs.getString(2));
                usrVo.setLname(rs.getString(3));
                usrVo.setEmail(rs.getString(4));
                usrVo.setPhone(rs.getString(5));
                usrVo.setZip(rs.getString(6));
                usrVo.setMembid(rs.getString(7));
                
                //mbrDetails.add(usrVo);
                Debug.print("UsrSignUpVO in Bean :"+usrVo.toString());
                
            }
            prepStmt.close();
            
            releaseConnection();
            
        }catch (Exception e){
            releaseConnection();
            
            Debug.print("Error in getUserCode");
            e.printStackTrace();
        } finally {
            
            Debug.print("inside finally ............");
            releaseConnection();
            
        }
        return usrVo;
    }
    
    //=============================================Insert Donation Details =========================================
    public String selectUserId(String memberId)  throws RemoteException {
        Debug.print("kaverystatelessBean.selectUserId():");
        String userId = "";;
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String selectStatement = "SELECT user_id from " + DBHelper.USEA_MMS_MEMBERDETAIL + " WHERE member_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, memberId);
            ResultSet  rs = prepStmt.executeQuery();
            if (rs.next()) {
                userId = rs.getString(1);
            }
            rs.close();
            //  prepStmt.close();
            Debug.print("kaverystatelessBean selectUserId userId:" + userId);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.selectUserId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.selectUserId():" + e.getMessage());
        }
        return userId;
    }
    
    //============================================= user id based on member id =========================================
    
    public String getMemberIdBasedOnUserId(String userId)  throws RemoteException {
        Debug.print("kaverystatelessBean.selectUserId(): "+userId);
        String memberId = "";
        PreparedStatement prepStmt = null;
        makeConnection();
        
        try {
            String selectStatement = "SELECT member_id from " + DBHelper.USEA_MMS_MEMBERDETAIL + " WHERE user_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            ResultSet  rs = prepStmt.executeQuery();
            if (rs.next()) {
                memberId = rs.getString(1);
            }
            rs.close();
            //  prepStmt.close();
            Debug.print("kaverystatelessBean getMemberIdBasedOnUserId memberId:" + memberId);
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.getMemberIdBasedOnUserId():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.getMemberIdBasedOnUserId():" + e.getMessage());
        }
        return memberId;
    }
    
    public boolean updatePaymentStatus(HLCPaymentDetailVO paymentVO) throws RemoteException{
        Debug.print("kaverystatelessBean updatePaymentStatus() userId:" + paymentVO.getPaymentId());
        boolean result =false;
        if(paymentVO!=null){
            try{
                Debug.print("kaverystatelessBean updatePaymentStatus() calling:");
                result = new HLCMemberUpdateDAO().updatePaymentStatus(paymentVO);
            } catch(Exception e){
                Debug.print("Exception while calling updatePaymentStatus():" + e.getMessage());
            }
        }
        return result;
    }
    
    public boolean insertPayment(HLCPaymentDetailVO objPayment) throws RemoteException {
        Debug.print("ArabianSeaEntityBean insertRowPayment");
        
        
        try {
            
            makeConnection();
            
            String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, check_name, amount, payment_status, " +
                    " ssl_result, ssl_result_message, ssl_txn_id, ssl_approval_code, ssl_cvv2_response, ssl_avs_response, " +
                    " ssl_transaction_type, ssl_invoice_no, ssl_email,check_amount,payment_id, pending_amount, parent_payment_id, ip_address, nsf_status, user_comments, staff_comments, nsf_charge_status)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ? , ?, ? , ? , ? , ? )";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, objPayment.getUserId());
            prepStmt.setString(2, objPayment.getCcName());
            prepStmt.setString(3, objPayment.getCcType());
            //prepStmt.setString(4, objPayment.getCcNumber());
            // following code for changing the card no from real to dummy as per client saying in mail 13-March-2008.
            String ccNumber=objPayment.getCcNumber();
             if (!(ccNumber.equals("0"))) {
            String temp = ccNumber.substring(0, 2);
            String temp1 = ccNumber.substring(2, 12);
            String temp2 = ccNumber.substring(12);
            temp1 = "***";
            ccNumber = temp+temp1+temp2;
             }
            Debug.print(ccNumber+"=testing ccnumber for renewal");
            // code ends here for card no. change
            prepStmt.setString(4,ccNumber );
            prepStmt.setInt(5, objPayment.getCcExpMonth());
            prepStmt.setInt(6, objPayment.getCcExpYear());
            // DO NOT write/store ANY CVV information in the tblUserPaymentDetails.So commented. as in email 13-March-2008.
            
            int ccCvvid = 0;
            prepStmt.setInt(7, ccCvvid);
            
            //prepStmt.setInt(7, objPayment.getCcCvvid());
            prepStmt.setString(8, objPayment.getBankName());
            if(objPayment.getCheckDate()!=null){
                prepStmt.setDate(9, DBHelper.toSQLDate(objPayment.getCheckDate()));
            } else{
                prepStmt.setDate(9, null);
            }
            
            prepStmt.setString(10, objPayment.getCheckNumber());
            prepStmt.setString(11, objPayment.getCheckName());
            prepStmt.setDouble(12, objPayment.getAmount());
            //prepStmt.setDate(13, DBHelper.toSQLDate(new Date()));
            prepStmt.setString(13, objPayment.getPaymentStatus());
            
            prepStmt.setString(14, objPayment.getSslResult());
            prepStmt.setString(15, objPayment.getSslResultMessage());
            prepStmt.setString(16, objPayment.getSslTxnId());
            prepStmt.setString(17, objPayment.getSslApprovalCode());
            prepStmt.setString(18, objPayment.getSslCvv2Response());
            prepStmt.setString(19, objPayment.getSslAvsResponse());
            prepStmt.setString(20, objPayment.getSslTransactionType());
            prepStmt.setString(21, objPayment.getSslInvoiceNo());
            prepStmt.setString(22, objPayment.getSslEmail());
            prepStmt.setFloat(23, objPayment.getCheckAmount());
            prepStmt.setString(24, objPayment.getPaymentId());
            prepStmt.setFloat(25, objPayment.getPendingAmount());
            prepStmt.setString(26, objPayment.getParentPaymentId());
            prepStmt.setString(27, objPayment.getIpAddress());
            prepStmt.setBoolean(28, objPayment.isNsfStatus());
            prepStmt.setString(29, objPayment.getUser_comments());
            prepStmt.setString(30, objPayment.getStaff_comments());
            prepStmt.setBoolean(31, objPayment.isNsf_charge_status());
            
            int ct=0;
            
            ct=prepStmt.executeUpdate();
            
            Debug.print("rows affected :"+ct);
            
            prepStmt.close();
            releaseConnection();
            
        } catch (Exception e){
            Debug.print("Error in insertRowPayment :  "+e.getMessage());
        }
        
        finally {
            releaseConnection();
        }
        return true;
    }
    
    //=============================================Select Payment Details =========================================
    public HLCPaymentDetails getPaymentDetails(String paymentId) throws RemoteException{
        Debug.print("kaverystatelessBean.getPaymentDetails():");
        Debug.print("kaverystatelessBean.getPaymentDetails() PaymentId:" + paymentId);
        //Debug.print("kaverystatelessBean.getPaymentDetails() memberId:" + memberId);
        HLCPaymentDetails objPayment = new HLCPaymentDetails();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " select A.payment_id, A.user_id, cc_name, " +
                    " A.cc_type, A.cc_number , A.cc_exp_month, A.cc_exp_year, A.cc_cvvid, A.bank_name, " +
                    " A.check_date, A.check_number, A.check_name, A.amount, A.payment_date, A.payment_status, " +
                    " A.ssl_result, A.ssl_result_message, A.ssl_txn_id, A.ssl_approval_code, " +
                    " A.ssl_cvv2_response, A.ssl_avs_response, A.ssl_transaction_type, " +
                    " A.ssl_invoice_no, A.ssl_email,A.check_amount,A.pending_amount,A.user_comments,A.staff_comments,A.nsf_charge_status from tblUserPaymentDetails A, tblMemberDetails B " +
                    " where A.payment_id = B.payment_id and " +
                    " B.payment_id = ? ";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,paymentId);
            // prepStmt.setString(2,memberId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String paymentIdVal = rs.getString(1);
                String userIdVal = rs.getString(2);
                String ccName = rs.getString(3);
                String ccType = rs.getString(4);
                String ccNumber = rs.getString(5);
                int ccExpMonth = rs.getInt(6);
                int ccExpYear = rs.getInt(7);
                int ccCvvid = rs.getInt(8);
                String bankName = rs.getString(9);
                Date checkDate = rs.getDate(10);
                String checkNumber = rs.getString(11);
                String checkName = rs.getString(12);
                double amount = rs.getDouble(13);
                Date paymentDate = rs.getDate(14);
                String paymentStatus = rs.getString(15);
                String sslResult = rs.getString(16);
                String sslResultMessage = rs.getString(17);
                String sslTxnId = rs.getString(18);
                String sslApprovalCode = rs.getString(19);
                String sslCvv2Response = rs.getString(20);
                String sslAvsResponse = rs.getString(21);
                String sslTransactionType = rs.getString(22);
                String sslInvoiceNo = rs.getString(23);
                String sslEmail = rs.getString(24);
                float chkAmt = rs.getFloat(25);
                float pendingAmt= rs.getFloat(26);
                String user_comments = rs.getString(27);
                String staff_comments = rs.getString(28);
                boolean nsf_charge_status = rs.getBoolean(29);
                
                objPayment.setPaymentId(paymentId);
                objPayment.setUserId(userId);
                objPayment.setCcName(ccName);
                objPayment.setCcType(ccType);
                objPayment.setCcNumber(ccNumber);
                objPayment.setCcExpMonth(ccExpMonth);
                objPayment.setCcExpYear(ccExpYear);
                objPayment.setCcCvvid(ccCvvid);
                objPayment.setBankName(bankName);
                objPayment.setCheckDate(checkDate);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setCheckName(checkName);
                objPayment.setAmount(amount);
                objPayment.setPaymentDate(paymentDate);
                objPayment.setPaymentStatus( paymentStatus);
                objPayment.setSslResult(sslResult);
                objPayment.setSslResultMessage(sslResultMessage);
                objPayment.setSslTxnId(sslTxnId);
                objPayment.setSslApprovalCode(sslApprovalCode);
                objPayment.setSslCvv2Response(sslCvv2Response);
                objPayment.setSslAvsResponse(sslAvsResponse);
                objPayment.setSslTransactionType(sslTransactionType);
                objPayment.setSslInvoiceNo(sslInvoiceNo);
                objPayment.setSslEmail(sslEmail);
                objPayment.setCheckAmount(chkAmt);
                objPayment.setPendingAmount(pendingAmt);
                objPayment.setUser_comments(user_comments);
                objPayment.setStaff_comments(staff_comments);
                objPayment.setNsf_charge_status(nsf_charge_status);
            }
            
            Debug.print("kaverystatelessBean getPaymentDetails:" + objPayment.getPaymentId());
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.getPaymentDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.getPaymentDetails():" + e.getMessage());
        }
        return objPayment;
    }
    
    /**
     * Name         : getNsfPaymentDetails
     * Description  : This method will pull out all transaction details based on payment id and nfs status .
     * @ param      : parentPaymentId
     * @return      : ArrayList
     * @throws      : RemoteException
     */
    public ArrayList getNsfPaymentDetails(String parentPaymentId) throws RemoteException{
        Debug.print("kaverystatelessBean.getNsfPaymentDetails():");
        Debug.print("kaverystatelessBean.getNsfPaymentDetails() parentPaymentId:" + parentPaymentId);
        
        ArrayList payDet = new ArrayList();
        HLCPaymentDetails objPayment = new HLCPaymentDetails();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try {
            String selectStatement = " select A.payment_id, A.user_id, cc_name, " +
                    " A.cc_type, A.cc_number , A.cc_exp_month, A.cc_exp_year, A.cc_cvvid, A.bank_name, " +
                    " A.check_date, A.check_number, A.check_name, A.amount, A.payment_date, A.payment_status, " +
                    " A.ssl_result, A.ssl_result_message, A.ssl_txn_id, A.ssl_approval_code, " +
                    " A.ssl_cvv2_response, A.ssl_avs_response, A.ssl_transaction_type, " +
                    " A.ssl_invoice_no, A.ssl_email,A.check_amount,A.pending_amount, A.parent_payment_id, A.nsf_status, A.user_comments, A.staff_comments, A.nsf_charge_status from tblUserPaymentDetails A " +
                    " where A.parent_payment_id = ? and nsf_status = 'true'";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, parentPaymentId);
            // prepStmt.setString(2,memberId);
            rs = prepStmt.executeQuery();
            Debug.print("Query Log :"+selectStatement);
            
            while(rs.next()){
                objPayment = new HLCPaymentDetails();
                
                String paymentIdVal = rs.getString(1);
                String userIdVal = rs.getString(2);
                String ccName = rs.getString(3);
                String ccType = rs.getString(4);
                String ccNumber = rs.getString(5);
                int ccExpMonth = rs.getInt(6);
                int ccExpYear = rs.getInt(7);
                int ccCvvid = rs.getInt(8);
                String bankName = rs.getString(9);
                Date checkDate = rs.getDate(10);
                String checkNumber = rs.getString(11);
                String checkName = rs.getString(12);
                double amount = rs.getDouble(13);
                Date paymentDate = rs.getDate(14);
                String paymentStatus = rs.getString(15);
                String sslResult = rs.getString(16);
                String sslResultMessage = rs.getString(17);
                String sslTxnId = rs.getString(18);
                String sslApprovalCode = rs.getString(19);
                String sslCvv2Response = rs.getString(20);
                String sslAvsResponse = rs.getString(21);
                String sslTransactionType = rs.getString(22);
                String sslInvoiceNo = rs.getString(23);
                String sslEmail = rs.getString(24);
                float chkAmt = rs.getFloat(25);
                float pendingAmt= rs.getFloat(26);
                String parent_payment_id = rs.getString(27);
                boolean nfsStat = rs.getBoolean(28);
                String user_comments = rs.getString(29);
                String staff_comments = rs.getString(30);
                boolean nsf_charge_status = rs.getBoolean(31);
                
                objPayment.setPaymentId(paymentIdVal);
                objPayment.setUserId(userId);
                objPayment.setCcName(ccName);
                objPayment.setCcType(ccType);
                objPayment.setCcNumber(ccNumber);
                objPayment.setCcExpMonth(ccExpMonth);
                objPayment.setCcExpYear(ccExpYear);
                objPayment.setCcCvvid(ccCvvid);
                objPayment.setBankName(bankName);
                objPayment.setCheckDate(checkDate);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setCheckName(checkName);
                objPayment.setAmount(amount);
                objPayment.setPaymentDate(paymentDate);
                objPayment.setPaymentStatus( paymentStatus);
                
                objPayment.setSslResult(sslResult);
                objPayment.setSslResultMessage(sslResultMessage);
                objPayment.setSslTxnId(sslTxnId);
                objPayment.setSslApprovalCode(sslApprovalCode);
                objPayment.setSslCvv2Response(sslCvv2Response);
                objPayment.setSslAvsResponse(sslAvsResponse);
                objPayment.setSslTransactionType(sslTransactionType);
                objPayment.setSslInvoiceNo(sslInvoiceNo);
                objPayment.setSslEmail(sslEmail);
                objPayment.setCheckAmount(chkAmt);
                objPayment.setPendingAmount(pendingAmt);
                objPayment.setParentPaymentId(parent_payment_id);
                objPayment.setNfsStat(nfsStat);
                objPayment.setUser_comments(user_comments);
                objPayment.setStaff_comments(staff_comments);
                objPayment.setNsf_charge_status(nsf_charge_status);
                
                payDet.add(objPayment);
            }
            
            //Debug.print("kaverystatelessBean getNsfPaymentDetails:" + objPayment.getPaymentId());
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.getNsfPaymentDetails() :" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.getNsfPaymentDetails() :" + e.getMessage());
        }
        return payDet;
    }
    
    /**
     * Name         : getAdminPaymentDetails
     * Description  : This method will pull out all transaction details based on parent payment id.
     * @ param      : parentPaymentId
     * @return      : ArrayList
     * @throws      : RemoteException
     */
    
    public ArrayList getAdminPaymentDetails(String parentPaymentId) throws RemoteException,Exception{
        Debug.print("kaverystatelessBean.getAdminPaymentDetails():");
        Debug.print("kaverystatelessBean.getAdminPaymentDetails() parentPaymentId:" + parentPaymentId);
        ArrayList PaymentDetail=new ArrayList();
        
        HLCPaymentDetails objPayment = new HLCPaymentDetails();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        
        try {
            String selectStatement = " select A.payment_id, A.user_id, cc_name, " +
                    " A.cc_type, A.cc_number , A.cc_exp_month, A.cc_exp_year, A.cc_cvvid, A.bank_name, " +
                    " A.check_date, A.check_number, A.check_name, A.amount, A.payment_date, A.payment_status, " +
                    " A.ssl_result, A.ssl_result_message, A.ssl_txn_id, A.ssl_approval_code, " +
                    " A.ssl_cvv2_response, A.ssl_avs_response, A.ssl_transaction_type, " +
                    " A.ssl_invoice_no, A.ssl_email,A.check_amount,A.pending_amount,A.user_comments,A.staff_comments,A.nsf_charge_status from tblUserPaymentDetails A " +
                    " where A.parent_payment_id = ? and nsf_status = ? and nsf_charge_status = ? ORDER BY A.payment_date";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,parentPaymentId);
            prepStmt.setBoolean(2,false);
            prepStmt.setBoolean(3,false);
            
            rs = prepStmt.executeQuery();
            Debug.print("Query Log :"+selectStatement);
            
            while(rs.next()){
                String paymentIdVal = rs.getString(1);
                String userIdVal = rs.getString(2);
                String ccName = rs.getString(3);
                String ccType = rs.getString(4);
                String ccNumber = rs.getString(5);
                int ccExpMonth = rs.getInt(6);
                int ccExpYear = rs.getInt(7);
                int ccCvvid = rs.getInt(8);
                String bankName = rs.getString(9);
                Date checkDate = rs.getDate(10);
                String checkNumber = rs.getString(11);
                String checkName = rs.getString(12);
                double amount = rs.getDouble(13);
                Date paymentDate = rs.getDate(14);
                String paymentStatus = rs.getString(15);
                String sslResult = rs.getString(16);
                String sslResultMessage = rs.getString(17);
                String sslTxnId = rs.getString(18);
                String sslApprovalCode = rs.getString(19);
                String sslCvv2Response = rs.getString(20);
                String sslAvsResponse = rs.getString(21);
                String sslTransactionType = rs.getString(22);
                String sslInvoiceNo = rs.getString(23);
                String sslEmail = rs.getString(24);
                float chkAmt = rs.getFloat(25);
                float pendingAmt= rs.getFloat(26);
                String user_comments = rs.getString(27);
                String staff_comments = rs.getString(28);
                boolean nsf_charge_status = rs.getBoolean(29);
                
                objPayment = new HLCPaymentDetails();
                
                objPayment.setPaymentId(paymentIdVal);
                objPayment.setUserId(userId);
                objPayment.setCcName(ccName);
                objPayment.setCcType(ccType);
                objPayment.setCcNumber(ccNumber);
                objPayment.setCcExpMonth(ccExpMonth);
                objPayment.setCcExpYear(ccExpYear);
                objPayment.setCcCvvid(ccCvvid);
                objPayment.setBankName(bankName);
                objPayment.setCheckDate(checkDate);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setCheckName(checkName);
                objPayment.setAmount(amount);
                objPayment.setPaymentDate(paymentDate);
                objPayment.setPaymentStatus( paymentStatus);
                objPayment.setSslResult(sslResult);
                objPayment.setSslResultMessage(sslResultMessage);
                objPayment.setSslTxnId(sslTxnId);
                objPayment.setSslApprovalCode(sslApprovalCode);
                objPayment.setSslCvv2Response(sslCvv2Response);
                objPayment.setSslAvsResponse(sslAvsResponse);
                objPayment.setSslTransactionType(sslTransactionType);
                objPayment.setSslInvoiceNo(sslInvoiceNo);
                objPayment.setSslEmail(sslEmail);
                objPayment.setCheckAmount(chkAmt);
                objPayment.setPendingAmount(pendingAmt);
                objPayment.setParentPaymentId(parentPaymentId);
                objPayment.setUser_comments(user_comments);
                objPayment.setStaff_comments(staff_comments);
                objPayment.setNsf_charge_status(nsf_charge_status);
                
                PaymentDetail.add(objPayment);
            }
            
            Debug.print("kaverystatelessBean getAdminPaymentDetails:" + objPayment.getPaymentId());
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.getAdminPaymentDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.getAdminPaymentDetails():" + e.getMessage());
        }
        return PaymentDetail;
    }
    
    /**
     * Name         : getAdminSubPaymentDetails
     * Description  : This method will pull out all transaction details based on parent payment id.
     * @ param      : parentPaymentId
     * @return      : ArrayList
     * @throws      : RemoteException
     */
    
    public ArrayList getAdminSubPaymentDetails(String parentPaymentId) throws RemoteException{
        Debug.print("kaverystatelessBean.getAdminSubPaymentDetails():");
        Debug.print("kaverystatelessBean.getAdminSubPaymentDetails() parentPaymentId:" + parentPaymentId);
        ArrayList PaymentDetail=new ArrayList();
        
        HLCPaymentDetails objPayment = new HLCPaymentDetails();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        
        try {
            String selectStatement = " select A.payment_id, A.cc_type, A.amount, A.payment_status, A.ssl_result, A.check_amount, A.pending_amount from "
                    +DBHelper.USEA_PAYMENT_DETAILS+" A where A.parent_payment_id = ? ORDER BY A.payment_date";
            
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,parentPaymentId);
            
            
            rs = prepStmt.executeQuery();
            Debug.print("Query Log :"+selectStatement);
            
            while(rs.next()){
                
                String paymentIdVal = rs.getString(1);
                String ccType = rs.getString(2);
                double amount = rs.getDouble(3);
                String paymentStatus = rs.getString(4);
                String sslResult = rs.getString(5);
                float chkAmt = rs.getFloat(6);
                float pendingAmt= rs.getFloat(7);
                
                objPayment = new HLCPaymentDetails();
                
                objPayment.setPaymentId(paymentIdVal);
                objPayment.setCcType(ccType);

                objPayment.setAmount(amount);
                objPayment.setPaymentStatus( paymentStatus);
                objPayment.setSslResult(sslResult);
                objPayment.setCheckAmount(chkAmt);
                objPayment.setPendingAmount(pendingAmt);
                
                PaymentDetail.add(objPayment);
            }
            
            Debug.print("kaverystatelessBean getAdminSubPaymentDetails:" + objPayment.getPaymentId());
            rs.close();
            prepStmt.close();
            releaseConnection();
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.getAdminSubPaymentDetails():" + sql.getMessage());
        } catch(Exception e){
            releaseConnection();
            Debug.print("General Exception  in kaverystatelessBean.getAdminSubPaymentDetails():" + e.getMessage());
        }
        return PaymentDetail;
    }
    
    /**
     * Name         :updateNSFPayment
     * Description  :This method will update payment based on the payment Id
     * @ param      :PaymentDetailVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updateNSFPayment(HLCPaymentDetailVO paymentVO) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean updateNSFStatus() :");
        Debug.print("paymentVO.toString() : "+paymentVO.toString());
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            stat = objDAO.updateNSFPayment(paymentVO);
            Debug.print("stat :"+stat);
        } catch (Exception e){
            Debug.print("General exception while updateNSFStatus in session bean : "+e.getMessage());
        }
        return stat;
    }
    
    /**
     * Name         :updateNSFPayment
     * Description  :This method will update payment based on the payment Id
     * @ param      :PaymentDetailVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updateNSFChargeStatus(String paymentId) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean updateNSFChargeStatus() :");
        Debug.print("paymentId : "+paymentId);
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            stat = objDAO.updateNSFChargeStatus(paymentId);
            Debug.print("stat :"+stat);
        } catch (Exception e){
            Debug.print("General exception while updateNSFChargeStatus in session bean : "+e.getMessage());
        }
        return stat;
    }
    
    /**
     * Name         :updateNSFPendingAmt
     * Description  :This method will update payment based on the payment Id
     * @ param      :String paymentId,String amount
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean updateNSFPendingAmt(String paymentId,float amount) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean updateNSFPendingAmt() :");
        Debug.print("paymentId : "+paymentId);
        Debug.print("amount : "+amount);
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            stat = objDAO.updateNSFPendingAmt(paymentId,amount);
            Debug.print("stat :"+stat);
        } catch (Exception e){
            Debug.print("General exception while updateNSFStatus in session bean : "+e.getMessage());
        }
        return stat;
    }
    
    /**
     * Name         :updateNSFPayment
     * Description  :This method will update payment based on the payment Id
     * @ param      :PaymentDetailVO
     * @return      :boolean
     * @throws      :RemoteException
     */
    public boolean checkSubpaymentExistance(String paymentId) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean checkSubpaymentExistance() :");
        Debug.print("paymentId : "+paymentId);
        
        try {
            
            PreparedStatement prepStmt1=null;
            ResultSet rs= null;
            makeConnection();
            
            String selectStatement1 = "select payment_id from "+DBHelper.USEA_PAYMENT_DETAILS+" where parent_payment_id = ?";
            prepStmt1 = con.prepareStatement(selectStatement1);
            prepStmt1.setString(1,paymentId);
            
            rs = prepStmt1.executeQuery();
            Debug.print("Query Log :"+selectStatement1);
            
            if(rs.next())
                stat = true;
            
            rs.close();
            prepStmt1.close();
            releaseConnection();
            
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.checkSubpaymentExistance() :" + sql.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while checkSubpaymentExistance in  kaverystatelessBean : "+e.getMessage());
        } finally {
            
            releaseConnection();
        }
        return stat;
    }
    
    /**
     * Name         :loadServiceType
     * Description  :This method will load service type details for nsf
     * @ param      :nil
     * @return      :String[]
     * @throws      :RemoteException
     */
    
    public String[] loadServiceType() throws RemoteException,Exception {
        
        String serviceTypeId = "";
        String memberType = "";
        String memberAmount = "";
        
        try {
            Debug.print("loadServiceType() in DAO :");
            PreparedStatement prepStmt1=null;
            ResultSet rs= null;
            makeConnection();
            
            String selectStatement1 = "select horse_service_type_id, horse_service_type_name, horse_service_type_amount from "+DBHelper.USEA_HORSE_SERVICE_TYPE+" where horse_service_type_name = 'NSF Charge'";
            prepStmt1 = con.prepareStatement(selectStatement1);
            rs = prepStmt1.executeQuery();
            Debug.print("Query Log :"+selectStatement1);
            
            if(rs.next()){
                serviceTypeId = rs.getString(1);
                memberType = rs.getString(2);
                memberAmount = rs.getString(3);
                
            }
            
            rs.close();
            prepStmt1.close();
            releaseConnection();
            
        } catch(SQLException sql){
            releaseConnection();
            Debug.print("SQL Exception in kaverystatelessBean.getAdminPaymentDetails():" + sql.getMessage());
        } catch (Exception e){
            
            Debug.print("general exception while loadServiceType in DAO : "+e.getMessage());
        } finally {
            
            releaseConnection();
        }
        
        String[] member = {serviceTypeId, memberType, memberAmount};
        return member;
    }
    
    /**
     * Name         :getLastSubPaymentIdOnParentId
     * Description  :This method will get the most recent sub payment's payment id and returns.
     * @ param      :parentPaymentId
     * @return      :String
     * @throws      :SQLException
     */
    public String getLastSubPaymentIdOnParentId(String parentPaymentId) throws RemoteException{
        
        String paymentId = "";
        Debug.print("session bean getLastSubPaymentIdOnParentId() :");
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            paymentId = objDAO.getLastSubPaymentIdOnParentId(parentPaymentId);
            Debug.print("paymentId in session bean :"+paymentId);
        } catch (Exception e){
            Debug.print("General exception while getLastSubPaymentIdOnParentId in session bean : "+e.getMessage());
        }
        return paymentId;
    }
    
    /**
     * Name         :updateCheckAmountOnPaymentId
     * Description  :This method will update check amt on payment id.
     * @ param      :paymentId, check_amt
     * @return      :boolean
     * @throws      :RemoteException
     */
    
    public boolean updateCheckAmountOnPaymentId(String paymentId, float check_amt) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean updateCheckAmountOnPaymentId() :");
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            stat = objDAO.updateCheckAmountOnPaymentId(paymentId,check_amt);
            Debug.print("status in session bean updateCheckAmountOnPaymentId :"+stat);
        } catch (Exception e){
            Debug.print("General exception while getLastSubPaymentIdOnParentId in session bean : "+e.getMessage());
        }
        return stat;
    }
    
    /**
     * Name         :updateNSFChargeStatusForSubSequentPayments
     * Description  :This method will update nsf_charge_status on parent_payment_id and amt.
     * @ param      :parentPaymentId, amount
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateNSFChargeStatusForSubSequentPayments(String parentPaymentId, float amount) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean updateNSFChargeStatusForSubSequentPayments() :");
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            stat = objDAO.updateNSFChargeStatusForSubSequentPayments(parentPaymentId,amount);
            Debug.print("status in session bean updateNSFChargeStatusForSubSequentPayments :"+stat);
        } catch (Exception e){
            Debug.print("General exception while updateNSFChargeStatusForSubSequentPayments in session bean : "+e.getMessage());
        }
        return stat;
    }
    
    /**
     * Name         :getNSFCheckDetails
     * Description  :This method will get NSF Check Details based on the payment Id
     * @ param      :PaymentDetailVO
     * @return      :paymentId
     * @throws      :RemoteException
     */
    public HLCPaymentDetailVO getNSFCheckDetails(String paymentId) throws RemoteException{
        
        Debug.print("session bean getNSFCheckDetails() :");
        Debug.print("paymentId : "+paymentId);
        
        HLCPaymentDetailVO paymentdet = new HLCPaymentDetailVO();
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            paymentdet = objDAO.getNSFCheckDetails(paymentId);
            Debug.print("paymentdet.toString() :"+paymentdet.toString());
        } catch (Exception e){
            Debug.print("General exception while getNSFCheckDetails in session bean : "+e.getMessage());
        }
        return paymentdet;
    }
    
    /**
     * Name         :updateReverseEntryStatus
     * Description  :This method will update Reverse Entry Status on payment id.
     * @ param      :paymentId
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateReverseEntryStatus(String paymentId) throws RemoteException{
        
        boolean stat = false;
        Debug.print("session bean updateReverseEntryStatus() :");
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            stat = objDAO.updateReverseEntryStatus(paymentId);
            Debug.print("status in session bean updateReverseEntryStatus :"+stat);
        } catch (Exception e){
            Debug.print("General exception while updateReverseEntryStatus in session bean : "+e.getMessage());
        }
        return stat;
    }
    
    /**
     * Name         :updateFutureExpDateByYear
     * Description  :This method will update Future Exp Date By Year.
     * @ param      :year, memberId
     * @return      :boolean
     * @throws      :SQLException
     */
    
    public boolean updateFutureExpDateByYear(String memberId) throws RemoteException{
        Debug.print("updateFutureExpDateByYear :");
        Debug.print("memberId  ID : "+memberId);
        boolean stat = false;
        
        try {
            
            String expDate = "2007-11-30";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(expDate);
            System.out.println("sample Date format created :" + formatter.format(date));
            java.util.Date today = new java.util.Date();
            date.setYear(today.getYear()+1);
            System.out.println("Date:" + formatter.format(date));
            System.out.println("final expiry Date created before updation is :" + date);
            
            makeConnection();
            
            String str = "update  " + DBHelper.USEA_MMS_MEMBERDETAIL + " set expiry_date = ?  WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(str);
            prepStmt.setDate(1,DBHelper.toSQLDate(date));
            prepStmt.setString(2, memberId);
            
            int cnt = prepStmt.executeUpdate();
            if(cnt>=1){
                stat = true;
            }
            Debug.print("Succefully updateFutureExpDateByYear details......" + cnt);
            prepStmt.close();
            
        }catch (SQLException e){
            
            Debug.print("SQL Exception while updateFutureExpDateByYear details : "+e.getMessage());
        } catch (Exception e){
            releaseConnection();
            Debug.print("General Exception while updateFutureExpDateByYear details : "+e.getMessage());
        } finally {
            //prepStmt.close();
            releaseConnection();
        }
        
        return stat;
    }
    
    /**
     * Name         :insertMemberHistoryDetails
     * Description  :This method insert Member History Details
     * @ param      :MemberHistoryDetail
     * @return      :boolean
     * @throws      :EJBException
     */
    
    public boolean insertMemberHistoryDetails(HLCMemberHistoryDetail membHistory) throws RemoteException{
        
        Debug.print("session bean insertMemberHistoryDetails() :");
        boolean result = false;
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            result = objDAO.insertMemberHistoryDetails(membHistory);
        } catch (Exception e){
            Debug.print("General exception while insertMemberHistoryDetails in session bean : "+e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Name         :insertHorseMemberHistoryDetails
     * Description  :This method will insert Horse Member History Details
     * @ param      :MemberHistoryDetail
     * @return      :boolean
     * @throws      :EJBException
     */
    
    public boolean insertHorseMemberHistoryDetails(HLCMemberHistoryDetail membHistory) throws RemoteException{
        
        Debug.print("session bean insertHorseMemberHistoryDetails() :");
        boolean result = false;
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            result = objDAO.insertHorseMemberHistoryDetails(membHistory);
        } catch (Exception e){
            Debug.print("General exception while insertHorseMemberHistoryDetails in session bean : "+e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Name         :getZipCodeOnUserId
     * Description  :This method will get Zip Code On UserId.
     * @ param      :userId
     * @return      :String
     * @throws      :EJBException
     */
    
    public String getZipCodeOnUserId(String userId) throws RemoteException{
        
        Debug.print("session bean getZipCodeOnUserId() :");
        Debug.print(" userId :"+userId);
        
        String result = null;
        
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        
        try{
            result = objDAO.getZipCodeOnUserId(userId);
        } catch (Exception e){
            Debug.print("General exception while getZipCodeOnUserId in session bean : "+e.getMessage());
        }
        
        return result;
    }
    
    /**
     * Name         :updateMemberHistoryDetailStatus
     * Description  :This method will update the login date of a user in user master table
     * @ param      :userId
     * @return      :boolean
     * @throws      :Exception
     */
    public boolean updateMemberHistoryDetailStatus(String memberId, String status_Id, String paymentId) throws RemoteException {
        Debug.print("session bean updateMemberHistoryDetailStatus() :");
        Debug.print("memberId : "+memberId);
        Debug.print("status_Id :"+status_Id);
        Debug.print("paymentId :"+paymentId);
        
        String activity_id = null;
        boolean stat = false;
        
        try {
            makeConnection();
            if(paymentId!=null && paymentId.trim().length()!=0)   {
                String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set status_id = ? where payment_id = ?";
                Debug.print("update qry log :"+str2);
                
                PreparedStatement prepStmt = con.prepareStatement(str2);
                prepStmt.setString(1, status_Id);
                prepStmt.setString(2, paymentId);
                //prepStmt.setString(3, memberId);
                
                Debug.print("Before calling executeUpdate Mathod in updateMemberHistoryDetailStatus");
                int cnt = prepStmt.executeUpdate();
                
                Debug.print("Succefully updateMemberHistoryDetailStatus....."+cnt);
                
                if(cnt>0)
                    stat = true;
                prepStmt.close();
            }
        } catch (SQLException ex){
            
            releaseConnection();
            Debug.print("SQLException while updateMemberHistoryDetailStatus : ");
            ex.printStackTrace();
        } catch (Exception e){
            
            releaseConnection();
            Debug.print("General Exception updateMemberHistoryDetailStatus : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return stat;
    }
    public boolean updateHorseMemberHistoryDetailStatus(String memberId, String status_Id, String paymentId, Date actDtAsFrmDt) throws RemoteException {
        Debug.print("session bean updateHorseMemberHistoryDetailStatus() :");
        Debug.print("memberId : "+memberId);
        Debug.print("status_Id :"+status_Id);
        Debug.print("paymentId :"+paymentId);
        Debug.print("actDtAsFrmDt :"+actDtAsFrmDt);
        
        String activity_id = null;
        boolean stat = false;
        
        try {
            makeConnection();
            
            String str2 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set from_date = ?, status_id = ? where member_id=? and active_status=?  ";
            Debug.print("update qry log :"+str2);
            
            PreparedStatement prepStmt = con.prepareStatement(str2);
            prepStmt.setDate(1, DBHelper.toSQLDate(actDtAsFrmDt));
            prepStmt.setString(2, status_Id);          
            prepStmt.setString(3, memberId);
            prepStmt.setBoolean(4, true);
            
            Debug.print("Before calling executeUpdate Method in updateHorseMemberHistoryDetailStatus");
            int cnt = prepStmt.executeUpdate();
            
            Debug.print("Successfully updateHorseMemberHistoryDetailStatus....."+cnt);
            
            if(cnt>0)
                stat = true; 
            
           if(stat==true){    
               String str3 = "select activity_id, add_date from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" WHERE active_status = ? and member_id = ? order by add_date asc";
                Debug.print("sel activity id Query Log :"+str3); 
          
                prepStmt = con.prepareStatement(str3);
                prepStmt.setBoolean(1, false);
                prepStmt.setString(2, memberId);
                
                rs = prepStmt.executeQuery();
                String activityId = null;
                int i=0;
                ArrayList tempActList= new ArrayList();
                String activityId1=null;
                Date addDt=null;
                Date tempDt=null;
                while(rs.next()){
                activityId = rs.getString(1);
                addDt = rs.getDate(2);
                tempActList.add(activityId);
                tempActList.add(addDt);
                Debug.print("activityId in Loop updateHorseMemberHistoryDetailStatus:" + activityId);
                Debug.print("activityId in Loop updateHorseMemberHistoryDetailStatus:" + addDt);
                Debug.print("tempActList in Loop updateHorseMemberHistoryDetailStatus:" + tempActList);
                i++;                                      
                }
                rs.close();
               Iterator tempActId = tempActList.iterator();
            while(tempActId.hasNext()){ 
              activityId1 = (String)tempActId.next();
              tempDt = (Date)tempActId.next();
              Debug.print("activityId1 in Loop updateHorseMemberHistoryDetailStatus:" + activityId1);
              Debug.print("tempDt in Loop updateHorseMemberHistoryDetailStatus:" + tempDt);
            }
          if(activityId1!=null){
             Debug.print("activityId1 in if condition updateHorseMemberHistoryDetailStatus:" + activityId1);
                SimpleDateFormat df=new SimpleDateFormat("MM/dd/yyyy");   		
                int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
                Date prevDat = new Date(df.format(new Date(actDtAsFrmDt.getTime() - MILLIS_IN_DAY ) ));
                Debug.print("Previous date : "+prevDat.toString());
                
                String str4 = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set to_date = ? where activity_id = ?";
                Debug.print("update qry log :"+str4); 
                
                PreparedStatement prepStmt1 = con.prepareStatement(str4);                
                prepStmt1.setDate(1, DBHelper.toSQLDate(prevDat));
                prepStmt1.setString(2, activityId1);       
                int cnt1 = prepStmt1.executeUpdate();

                Debug.print("Successfully updated the to_date in history table..........."+cnt); 
            }     
            
            }
 
            prepStmt.close();
            
        } catch (SQLException ex){
            
            releaseConnection();
            Debug.print("SQLException while updateHorseMemberHistoryDetailStatus : ");
            ex.printStackTrace();
        }     
        catch (Exception e){
            
            releaseConnection();
            Debug.print("General Exception updateMemberHistoryDetailStatus : "+e.getMessage());
        }finally {
            
            releaseConnection();
        }
        return stat;
    }
    /**
     * Name         : getMembershipStatusFromHistory
     * Description  : This method will get the membership history details
     * @ param      : user_id
     * @return      : ArrayList
     * @throws      : RemoteException
     */
    
    public ArrayList getMembershipStatusFromHistory(String membId) throws RemoteException{
        ArrayList Collection_values = new ArrayList();
        try {
            makeConnection();
            
            String query = "select Top 2 A.membership_type_id, A.status_id, A.to_date, B.membership_type_name, B.membership_year, C.status_name "+
                    "from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" A, "+DBHelper.USEA_MEMBERSHIP_TYPE+" B, "+DBHelper.USEA_STATUS_MASTER+" C "+
                    "where A.membership_type_id = B.membership_type_id and A.status_id = C.status_id and "+
                    "A.member_id = ? order by A.add_date desc";
            
            Debug.print("select Query  :\n"+query);
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, membId);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                
                
                String membership_type_id = rs.getString(1);
                String status_id = rs.getString(2);
                Date to_date = rs.getDate(3);
                String memberTypeName = rs.getString(4);
                String membership_year = rs.getString(5);
                String statusName = rs.getString(6);
                
                String values[] = {memberTypeName,membership_year,statusName,String.valueOf(to_date)};
                Collection_values.add(values);
            }
            Debug.print("Size of the collection values is "+Collection_values.size());
            rs.close();
        } catch(Exception e){
            e.printStackTrace();
            Debug.print("Exception caused is "+e);
        } finally{
            releaseConnection();
        }
        return Collection_values;
    }
    
    /**
     * Name         : getMembershipDetailsFromHistory
     * Description  : This method will get the membership history details
     * @ param      : user_id
     * @return      : ArrayList
     * @throws      : RemoteException
     */
    
    public ArrayList getMembershipDetailsFromHistory(String membId) throws RemoteException{
        ArrayList Collection_values = new ArrayList();
        Debug.print("getMembershipDetailsFromHistory memberId "+membId);
        try {
            makeConnection();
            String query1 = "select A.membership_type_id, A.status_id, A.to_date, B.membership_type_name, B.membership_year, C.status_name"+
                    " from tblMembershipHistoryDetails A, tblMembershipTypeMaster B, tblMembershipStatusMaster C "+
                    " where A.membership_type_id = B.membership_type_id and A.status_id = C.status_id " +
                    "and A.member_id = ? and A.active_status = ?";
            
            String query2 = "select A.membership_type_id, A.status_id, A.to_date, B.membership_type_name, B.membership_year, C.status_name"+
                    " from tblMembershipHistoryDetails A, tblMembershipTypeMaster B, tblMembershipStatusMaster C "+
                    " where A.membership_type_id = B.membership_type_id and A.status_id = C.status_id and "+
                    " A.member_id = ? and YEAR(to_date) = (select YEAR(to_date) + 1 from tblmembershipHistoryDetails where "+
                    " active_status = ? and member_id = ?) and A.member_id = ?";
            
            Debug.print("select Query1  :\n"+query1);
            Debug.print("select Query2  :\n"+query2);
            PreparedStatement prepStmt1 = con.prepareStatement(query1);
            prepStmt1.setString(1, membId);
            prepStmt1.setBoolean(2, true);
            
            PreparedStatement prepStmt2 = con.prepareStatement(query2);
            prepStmt2.setString(1, membId);
            prepStmt2.setBoolean(2, true);
            prepStmt2.setString(3, membId);
            prepStmt2.setString(4, membId);
            
            
            ResultSet rs1 = prepStmt1.executeQuery();
            ResultSet rs2 = prepStmt2.executeQuery();
            Date to_date = null;
            
            while(rs1.next()){
                String membership_type_id = rs1.getString(1);
                String status_id = rs1.getString(2);
                to_date = rs1.getDate(3);
                String memberTypeName = rs1.getString(4);
                String membership_year = rs1.getString(5);
                String statusName = rs1.getString(6);
                
                String values[] = {memberTypeName,membership_year,statusName,String.valueOf(to_date)};
                Collection_values.add(values);
            }
            
            if(to_date!=null){
                while(rs2.next()){
                    String membership_type_id = rs2.getString(1);
                    String status_id = rs2.getString(2);
                    Date to_date1 = rs2.getDate(3);
                    String memberTypeName = rs2.getString(4);
                    String membership_year = rs2.getString(5);
                    String statusName = rs2.getString(6);
                    
                    String values[] = {memberTypeName,membership_year,statusName,String.valueOf(to_date1)};
                    Collection_values.add(values);
                }
            }
            
            Debug.print("Size of the collection values is "+Collection_values.size());
            rs1.close();
            rs2.close();
            Debug.print("Exiting getMembershipDetailsFromHistory memberId "+membId);
        } catch(Exception e){
            e.printStackTrace();
            Debug.print("Exception caused is "+e);
        } finally{
            releaseConnection();
        }
        return Collection_values;
    }
    
    /**
     * Name         : updateHistoryOnMemberSearch
     * Description  : This method will update membership history details when admin search user and change the status
     * @ param      : status_id, payment_id, user_id
     * @return      : boolean
     * @throws      : RemoteException
     */
    
    public boolean updateHistoryOnMemberSearch(String status_id, String payment_id, String user_id) throws RemoteException{
        boolean result = false;
        try {
            Debug.print("updateHistoryOnMemberSearch Entering");
            makeConnection();
            if(status_id!=null && !(status_id.equalsIgnoreCase("null")) && payment_id!=null && !(payment_id.equalsIgnoreCase("null")) &&
                    user_id!=null && !(user_id.equalsIgnoreCase("null"))){
                String query = "update "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" set status_id = ? where  user_id = ? and payment_id = ?";
                
                Debug.print("select Query  :\n"+query);
                
                PreparedStatement prepStmt = con.prepareStatement(query);
                
                prepStmt.setString(1, status_id);
                prepStmt.setString(2, user_id);
                prepStmt.setString(3, payment_id);
                
                int count = prepStmt.executeUpdate();
                Debug.print("Query update Count is "+count);
                if(count > 0){
                    result = true;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            Debug.print("Exception caused is "+e);
        } finally{
            Debug.print("updateHistoryOnMemberSearch Exiting");
            releaseConnection();
        }
        return result;
    }
    
   public boolean insertWebSiteDonPayDetails(HLCPaymentDetails objPayment) throws RemoteException {
        Debug.print("ArabianSeaEntityBean insertWebSiteDonPayDetails");
        boolean result = false;
        try {
            
            makeConnection();
            
            String insertStatement = "insert into tblDonationPaymentDetails (donation_payment_id,user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, amount, prefix, first_name, last_name, email_id, city, state  ) values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,? ,?)";
            
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            
            prepStmt.setString(1, objPayment.getPaymentId());
            prepStmt.setString(2, objPayment.getUserId());
            prepStmt.setString(3, objPayment.getCcName());
            prepStmt.setString(4, objPayment.getCcType());
            prepStmt.setString(5, objPayment.getCcNumber());
            prepStmt.setInt(6, objPayment.getCcExpMonth());
            prepStmt.setInt(7, objPayment.getCcExpYear());
            prepStmt.setInt(8, objPayment.getCcCvvid());
            prepStmt.setDouble(9, objPayment.getAmount());
            prepStmt.setString(10, objPayment.getPrefix());
            prepStmt.setString(11, objPayment.getFirstName());
            prepStmt.setString(12, objPayment.getLastName());
            prepStmt.setString(13, objPayment.getEmailId());
            prepStmt.setString(14, objPayment.getCity());
            prepStmt.setString(15, objPayment.getState());
                       
            int count = prepStmt.executeUpdate();
                Debug.print("Query insert Count is "+count);
                if(count > 0){
                    result = true;
                }
            
            prepStmt.close();
            releaseConnection();
            
        } catch (Exception e){
            Debug.print("Error in insertWebSiteDonPayDetails :  "+e.getMessage());
        }
        
        finally {
            releaseConnection();
        }
        return true;
    }
     
   public boolean insertWebSiteContactDetails(String userId, String address1, String address2,  String city, String  state, String country,
            String zip, String phoneNo, String mobileNo, String faxNo) throws RemoteException{
        boolean result =false;
        try{
                Debug.print("MemberUpdateDAO insertWebSiteContactDetails() calling:");
                result = new HLCMemberUpdateDAO().insertContactDetails(userId, address1, address2, city, state, country, zip, phoneNo, mobileNo, faxNo);
            } catch(Exception e){
                Debug.print("Exception while calling insertWebSiteContactDetails() in kaverystatelessBean :" + e.getMessage());
            }
       return result;
    } 
    /**
     * Name         :makeConnection
     * Description  :This method will Create a databacse connection
     * @ param      :
     * @return      :void
     * @throws      :EJBException
     */
    
   public String[] getUserDetailsForEmail(String userId) throws RemoteException{
        Debug.print("kaverystatelessBean getUserDetailsForEmail");
        String[] results = new HLCMemberUpdateDAO().selectUserDetailsForEmail(userId);
        return results;
    }
   
   /**
  * @Method Name    :getUserContactDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String userId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */    
      
    public ArrayList getUserContactDetails(String userId) throws RemoteException {
        Debug.print("EducationalSessionBean getUserContactDetails");
        ArrayList results = new HLCMemberUpdateDAO().getUserContactDetails(userId);
         Debug.print("EducationalSessionBean getUserContactDetails Result :" + results);
        return results;
    }
    
    /**
  * @Method Name    :getMemberContactDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String memberId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */         
    public ArrayList getMemberContactDetails(String memberId) throws RemoteException {
        Debug.print("EducationalSessionBean getMemberContactDetails");
        ArrayList results = new HLCMemberUpdateDAO().getMemberContactDetails(memberId);
         Debug.print("EducationalSessionBean getMemberContactDetails Result :" + results);
        return results;
    }
    
      /**
  * @Method Name    :getUserContactDetails.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String userId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */    
      
     public ArrayList getUserContactDetailsForAdmin(String userId) throws RemoteException {
        Debug.print("HLCKaverystatelessBean getUserContactDetailsForAdmin");
        ArrayList results = new HLCMemberUpdateDAO().getUserContactDetailsForAdmin(userId);
         Debug.print("HLCKaverystatelessBean getUserContactDetailsForAdmin Result :" + results);
        return results;
    }
   
      public String getNextId() throws RemoteException {
        Debug.print("HLCKaverystatelessBean Payment getNextId ");
        String nextPaymentId = "";
        try {
            nextPaymentId = new HLCMemberUpdateDAO().getNextId();
        } catch(Exception e){
            Debug.print("Exception in getNextId:" + e);
        }
        return nextPaymentId;
    }
    
  /**
  * @Method Name    :searchUserByMemberId.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String memberId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */       
    public ArrayList searchUserByMemberId(String memberId) throws RemoteException {
        Debug.print("HLCKaverystatelessBean searchUserByMemberId() memberId:" + memberId);
        ArrayList memberList = new HLCMemberUpdateDAO().selectMemberDetailsByMemberId(memberId);
        return memberList;
    }
    
    /**
  * @Method Name    :searchUserByGeneral.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String firstName, String lastName, String email, String zip.
  * @return         :ArrayList  value.
  * @throws         :RemoteException.
  */           
      public ArrayList searchUserByGeneral(String firstName, String lastName, String email, String zip, Date fromDate, Date toDate, String roleId, String radMem) throws RemoteException {
        Debug.print("HLCKaverystatelessBean searchUserByGeneral() firstName:" + firstName);
        ArrayList memberList = new HLCMemberUpdateDAO().selectMemberDetailsByGeneralSearch( firstName, lastName, email, zip, fromDate, toDate, roleId, radMem);
        return memberList;
    }
  /**
  * @Method Name    :searchUserByLoginName.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String loginName.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */    
    
     public ArrayList searchUserByLoginName(String loginName, String radMem) throws RemoteException {
        Debug.print("HLCKaverystatelessBean searchUserByMemberId() loginName:" + loginName);
        ArrayList memberList = new HLCMemberUpdateDAO().selectMemberDetailsByLoginName(loginName,radMem);
        return memberList;
    }
     
   public HLCUserSearchResultVO getUserDetailsByUserId(String userId) throws RemoteException {
        Debug.print("EducationalSessionBean getUserDetailsByUserId() userId:" + userId);
         HLCUserSearchResultVO memberList = new HLCMemberUpdateDAO().selectMemberDetailsByUserId(userId);
        return memberList;     
   } 
   
  /**
  * @Method Name    :getUserContactDetailsByUserCode.
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return.
  * @param          :String userCode.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */       
    public ArrayList getUserContactDetailsByUserCode(String userCode) throws RemoteException {
        Debug.print("EducationalSessionBean getUserContactDetailsByUserCode");
        ArrayList results = new HLCMemberUpdateDAO().getUserContactDetailsByUserCode(userCode);
        Debug.print("EducationalSessionBean getUserContactDetailsByUserCode Result :" + results);
        return results;
    }
  
    /**
     * Name         :displayMemberType
     * Description  :This method will list the User Type Name and  Details
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public Vector displayMemberType() throws RemoteException {
        Vector vObj = new Vector();
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            vObj = objDAO.listMemberType();
        }catch(Exception e){
            Debug.print("Error While getting displayMemberType list : "+e.getMessage());
        }
        return vObj;
    }
    
      /**
     * Name         :displayUserType
     * Description  :This method will list the User Type Name and  Details
     * @ param      :None
     * @return      :member Identification number
     * @throws      :RemoteException, FinderException
     */
    public Vector displayMembershipType() throws RemoteException {
        Vector vObj = new Vector();
        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
        try {
            vObj = objDAO.listMembershipType();
        }catch(Exception e){
            Debug.print("Error While getting membership Type list : "+e.getMessage());
        }
        return vObj;
    }
    
    public HashMap getHumanMembersStatusWise(Date fromDate,Date toDate) throws RemoteException
    {
        HashMap humanMemberCountStatusWise=new HashMap();
        String statusName=null;
        ResultSet rs=null;ResultSet rs1=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;
          makeConnection();
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate); 
          String query="select status_id,status_name from "+ DBHelper.USEA_STATUS_MASTER;
          String query_1="select count(*) as total   from tblMemberDetails   where status_id=? AND (add_date>=? AND add_date<=?) ";
          try
          {
          prepStmt = con.prepareStatement(query);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                  String total=null;  
                  String statusId=rs.getString(1);
                  statusName=rs.getString(2);
                  prepStmt1=con.prepareStatement(query_1);
                  prepStmt1.setString(1,statusId);
                  prepStmt1.setDate(2, startDate);
                  prepStmt1.setDate(3, endDate);
                  
           
           
                   rs1=prepStmt1.executeQuery();
                              while(rs1.next()){
                                     total=rs1.getString(1);
                              }
                   humanMemberCountStatusWise.put(statusName,total);
                   
                 
                   //Debug.print("StatusName+++++++++"+statusName+"   Total "+total);
           }
            Debug.print("Map Size inside HLCkaverystatelessBean"+humanMemberCountStatusWise.size());
            rs1.close();
                  prepStmt1.close(); 
                  rs.close();
                  prepStmt.close();
                  releaseConnection();
          }
          catch(SQLException e)
          {
            
                Debug.print("Eception occured inside HLCkaverystatelessBean" + e.getMessage());
                 releaseConnection();
                e.printStackTrace();
                
             
            }
          return humanMemberCountStatusWise;
    }
    
    public HashMap getHumanMembersMemberShipsByStatus(Date fromDate,Date toDate,String status) throws RemoteException
    {
       HashMap humanMemberCountMembershipWiseByStatus=new HashMap();
        String user_type_id=null,membership_type_name=null,membership_type_id=null;
         ResultSet rs=null;ResultSet rs1=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;
          makeConnection();
           java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
           java.sql.Date endDate=DBHelper.toSQLDate(toDate);
           String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
           String query_1="select iMIS_Mem_Type_Name,membership_type_id from tblMembershipTypeMaster where user_type_id=? AND active_status='true' AND membership_year='2009'"; // iMIS_Mem_Type_Name is new column name added
           String query_2="select count(*) from tblMemberDetails m  where m.status_id=?  AND m.membership_type_id=? AND (m.add_date>=? AND m.add_date<=?)";
           
           try
           { 
            prepStmt = con.prepareStatement(query);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
               user_type_id=rs.getString(1);
           }
            rs.close();prepStmt.close(); 
            prepStmt = con.prepareStatement(query_1);
            prepStmt.setString(1,user_type_id);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                 String total=null;
               membership_type_name=rs.getString(1);
               membership_type_id=rs.getString(2);
               Debug.print("Type_Name"+membership_type_name+"  Type_id="+membership_type_id);
               prepStmt1=con.prepareStatement(query_2);
               prepStmt1.setString(1,status);
               prepStmt1.setString(2,membership_type_id);
               prepStmt1.setDate(3,startDate);
               prepStmt1.setDate(4,endDate);
               rs1=prepStmt1.executeQuery();
               while(rs1.next())
               {
                   total=rs1.getString(1);
               }
               humanMemberCountMembershipWiseByStatus.put(membership_type_name,total);
               Debug.print("Mebership Name"+membership_type_name+"  Total"+total);
           }
            Debug.print("Map Size inside HLCkaverystatelessBean"+humanMemberCountMembershipWiseByStatus.size());
            rs.close();
           prepStmt.close();
            rs1.close();
            prepStmt1.close(); 
            releaseConnection();
           }
            
           catch(SQLException e)
           {
               
                //releaseConnection();
               Debug.print("Error occured"+e.getMessage());
           }
           return humanMemberCountMembershipWiseByStatus;
    }
    public String getHumanMembersSpecificMembershipByStatus(Date fromDate,Date toDate,String status,String membershipType) throws RemoteException
    {
        Debug.print("Inside HLCHaveryStatelessBean getHumanMembersSpecificMembershipByStatus");
        Debug.print("Status "+status+" membership Type "+membershipType);
        String total=null;
        String query="select count(*) as total from "+DBHelper.USEA_MMS_MEMBERDETAIL+" where status_id=?  AND membership_type_id=? AND (add_date>=? AND add_date<=?)";
        ResultSet rs=null;
        PreparedStatement prepStmt=null;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate);
        makeConnection();
        try
        {
             prepStmt=con.prepareStatement(query);
             prepStmt.setString(1,status);
             prepStmt.setString(2,membershipType);
             prepStmt.setDate(3,startDate);
             prepStmt.setDate(4,endDate);
             rs=prepStmt.executeQuery();
               while(rs.next())
               {
                   total=rs.getString(1);
               }
             Debug.print("Total for membership Id"+membershipType +" is "+total);
             rs.close();
             prepStmt.close();
             releaseConnection();
             
        }
        catch(SQLException r)
        {
             r.printStackTrace();
             Debug.print(r.getMessage());
        }
        return total;
    }
    
    public ArrayList getHumanMembersAreaWise(Date fromDate,Date toDate)
    {
        Debug.print("Inside HLCHaveryStatelessBean getHumanMembersAreaWise");
        ArrayList  totalHumanAreaWise=new ArrayList();
        HashMap HumanMembersAreaWise=null;
        String user_type_id=null;String user_id=null;String area_id=null;String area_name=null;
        ResultSet rs=null;ResultSet rs1=null;ResultSet rs2=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;PreparedStatement prepStmt2=null;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate);
        String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
        String query_1="select user_id from "+DBHelper.USEA_MMS_USERMASTER+" where user_type_id=?  and  (register_date>=? and register_date<=?)";
        String query_2="select A.area_id  from tblMeeMapStateZip  A where A.area_id =? and (select top 1 zip from "+DBHelper.USEA_CONTACT_DETAILS+" where user_id=?) between A.zip_code_from and A.zip_code_to";
        String query_3="select  area_id,area_name from tblMeeAreaMaster where active_status='true'";
        makeConnection();
        try
        {
            prepStmt=con.prepareStatement(query); // For 1st Query   
            rs=prepStmt.executeQuery();
            while(rs.next())
                user_type_id=rs.getString(1);
            rs.close();
            prepStmt.close();
            // For query_3 Query  
            prepStmt1=con.prepareStatement(query_3);
            rs1=prepStmt1.executeQuery();
            while(rs1.next())
            {
                
                int count=0;
              area_id=rs1.getString(1);
             area_name=rs1.getString(2);
            Debug.print("Area Id  is  "+area_id+" Area name "+area_name);
            prepStmt=con.prepareStatement(query_1);  // For query_1 Query   
            prepStmt.setString(1,user_type_id);
            prepStmt.setDate(2,startDate);
            prepStmt.setDate(3,endDate);
            rs=prepStmt.executeQuery();
             while(rs.next())
             {
                 user_id=rs.getString(1);
                 prepStmt2= con.prepareStatement(query_2);
                 prepStmt2.setString(1,area_id);
                 prepStmt2.setString(2,user_id);
                 rs2=prepStmt2.executeQuery();
                 while(rs2.next())
                 {
                     if(rs2.getString(1)!=null & rs2.getString(1).length()>0)
                     {
                         count=count+1;
                        
                     }
                 }
                 rs2.close();prepStmt2.close();
             }
            HumanMembersAreaWise=new HashMap();
             //Debug.print("Count for "+area_name +" Count is"+ count);
            HumanMembersAreaWise.put(area_name,new Double(count));
            Debug.print("Area Name "+area_name+" Count is :"+count);
            totalHumanAreaWise.add(HumanMembersAreaWise);
            //iterate=iterate+1;
            
        }
          rs1.close();
          prepStmt1.close();
          releaseConnection();
    }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return totalHumanAreaWise;
    }
    public ArrayList getHumanMembersAllAreasStatusWise(Date fromDate,Date toDate,String status)
    {
         Debug.print("Inside HLCHaveryStatelessBean getHumanMembersAllAreasStatusWise");
         String user_type_id=null;String user_id=null;String status_id=null;String area_id=null;String area_name=null;
        ResultSet rs=null;ResultSet rs1=null;ResultSet rs2=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;PreparedStatement prepStmt2=null;
        ArrayList  totalHumanAllAreasStatusWise=new ArrayList();
        HashMap HumanMembersAllAreasStatusWise=null;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate);
        String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
        String query_1="select user_id from "+DBHelper.USEA_MMS_USERMASTER+" where user_type_id=?  and  (register_date>=? and register_date<=?)"; // remove and membership_type_id IS NOT NULL from query
        String query_2="select A.area_id  from tblMeeMapStateZip  A where A.area_id =? and (select top 1 zip from "+DBHelper.USEA_CONTACT_DETAILS+" where user_id=?) between A.zip_code_from and A.zip_code_to";
        String query_3="select  area_id,area_name from tblMeeAreaMaster where active_status='true'";
        String query_4="select status_id from "+DBHelper.USEA_MMS_MEMBERDETAIL+" where user_id=?";
        makeConnection();
        try
        {
            prepStmt=con.prepareStatement(query); // For 1st Query   
            rs=prepStmt.executeQuery();
            while(rs.next())
                user_type_id=rs.getString(1);
            rs.close();
            prepStmt.close();
            // For query_3 Query  
            prepStmt1=con.prepareStatement(query_3);
            rs1=prepStmt1.executeQuery();
            while(rs1.next())
            {
                
                int count=0;
              area_id=rs1.getString(1);
             area_name=rs1.getString(2);
            Debug.print("Area Id  is  "+area_id+" Area name "+area_name);
            prepStmt=con.prepareStatement(query_1);  // For query_1 Query   
            prepStmt.setString(1,user_type_id);
            prepStmt.setDate(2,startDate);
            prepStmt.setDate(3,endDate);
            rs=prepStmt.executeQuery();
             while(rs.next())
             {
                 user_id=rs.getString(1);
                 prepStmt2=con.prepareStatement(query_4);
                 prepStmt2.setString(1,user_id);
                 rs2=prepStmt2.executeQuery();
                   while(rs2.next())
                        status_id=rs2.getString(1);
                 rs2.close();
                 prepStmt2.close();
                 if(status_id!=null && status_id.equalsIgnoreCase(status))
                 {
                 prepStmt2= con.prepareStatement(query_2);
                 prepStmt2.setString(1,area_id);
                 prepStmt2.setString(2,user_id);
                 rs2=prepStmt2.executeQuery();
                 while(rs2.next())
                 {
                     if(rs2.getString(1)!=null & rs2.getString(1).length()>0)
                     {
                         count=count+1;
                        
                     }
                 } 
                  rs2.close();
                 prepStmt2.close();
                 }
                
             }
            HumanMembersAllAreasStatusWise=new HashMap();
            HumanMembersAllAreasStatusWise.put(area_name,new Double(count));
            Debug.print("Area Name "+area_name+" Count is :"+count);
            totalHumanAllAreasStatusWise.add(HumanMembersAllAreasStatusWise);
            }
           
            rs1.close();
            prepStmt1.close();
           releaseConnection();
        }
        catch(Exception f)
        {
            f.printStackTrace();
            Debug.print(f.getMessage());
        }
            return  totalHumanAllAreasStatusWise;    
    } 
    
    public ArrayList getHumanMembersAllMemberhipTypesByArea(Date fromDate,Date toDate,String area_id)
    {
      Debug.print("Inside HLCHaveryStatelessBean getHumanMembersAllMemberhipTypesByArea");
         String user_type_id=null;String user_id=null;String status_id=null;
         String membership_type_name=null,membership_type_id=null;
         int count;
        ResultSet rs=null;ResultSet rs1=null;ResultSet rs2=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;PreparedStatement prepStmt2=null;
        ArrayList  totalHumanAllMembershipsByArea=new ArrayList();
        HashMap HumanMembersAllMembershipsByArea=null;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate);  
         String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
         String query_1="select membership_type_name,membership_type_id from tblMembershipTypeMaster where user_type_id=? AND active_status='true' AND membership_year='2009'";
         String query_2="select user_id from "+DBHelper.USEA_MMS_USERMASTER+" where user_type_id=? and membership_type_id=? and  (register_date>=? and register_date<=?)";
         String query_3="select A.area_id  from tblMeeMapStateZip  A where A.area_id =? and (select top 1 zip from "+DBHelper.USEA_CONTACT_DETAILS+" where user_id=?) between A.zip_code_from and A.zip_code_to";
         makeConnection();
         try
         {
             prepStmt=con.prepareStatement(query); // For 1st Query   
            rs=prepStmt.executeQuery();
            while(rs.next())
                user_type_id=rs.getString(1);
            rs.close();
            prepStmt.close();
             prepStmt = con.prepareStatement(query_1); // For query_1
            prepStmt.setString(1,user_type_id);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                 count=0;
                   membership_type_name=rs.getString(1);
                   membership_type_id=rs.getString(2);
                   Debug.print("Type_Name"+membership_type_name+"  Type_id="+membership_type_id);
                prepStmt2=con.prepareStatement(query_2);
                prepStmt2.setString(1, user_type_id);
                prepStmt2.setString(2,membership_type_id);
                prepStmt2.setDate(3,startDate);
                prepStmt2.setDate(4,endDate);
                rs2=prepStmt2.executeQuery();
                while(rs2.next())
                {
                   user_id=rs2.getString(1);
                   prepStmt1= con.prepareStatement(query_3);
                   prepStmt1.setString(1,area_id);
                   prepStmt1.setString(2,user_id);
                   rs1=prepStmt1.executeQuery();
                   while(rs1.next())
                   {
                      if(rs1.getString(1)!=null && rs1.getString(1).length()>0)
                              count=count+1;
                   }
                   rs1.close();prepStmt1.close();
                }
            rs2.close();prepStmt2.close();
            HumanMembersAllMembershipsByArea=new HashMap();
            HumanMembersAllMembershipsByArea.put(membership_type_name,new Double(count));
            Debug.print("MemberShip Name "+membership_type_name+" Count is :"+count);
            totalHumanAllMembershipsByArea.add(HumanMembersAllMembershipsByArea);
                }
              rs.close();prepStmt.close();
              releaseConnection();
            
         }
         catch(SQLException f)
         {
             Debug.print(f.getMessage());
             f.printStackTrace();
             releaseConnection();
         }
         return totalHumanAllMembershipsByArea;
    }
    public ArrayList getHumanMembersAllMemberhipTypesByAreaAndStatus(Date fromDate,Date toDate,String area_id,String status_id)
    {
       Debug.print("Inside HLCHaveryStatelessBean getHumanMembersAllMemberhipTypesByArea");
         String user_type_id=null;String user_id=null;String status_id_DB=null;
         String membership_type_name=null,membership_type_id=null;
         int count;
        ResultSet rs=null;ResultSet rs1=null;ResultSet rs2=null;ResultSet rs3=null;
        PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;PreparedStatement prepStmt2=null;PreparedStatement prepStmt3=null;
        ArrayList  totalHumanAllMembershipsByAreaAndStatus=new ArrayList();
        HashMap HumanMembersAllMembershipsByAreaAndStatus=null;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate); 
        String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
         String query_1="select membership_type_name,membership_type_id from tblMembershipTypeMaster where user_type_id=? AND active_status='true' AND membership_year='2009'";
         String query_2="select user_id from "+DBHelper.USEA_MMS_USERMASTER+" where user_type_id=? and membership_type_id=? and  (register_date>=? and register_date<=?)";
         String query_3="select A.area_id  from tblMeeMapStateZip  A where A.area_id =? and (select top 1 zip from "+DBHelper.USEA_CONTACT_DETAILS+" where user_id=?) between A.zip_code_from and A.zip_code_to";
         String query_4="select status_id from "+DBHelper.USEA_MMS_MEMBERDETAIL+" where user_id=?";
         makeConnection();
         try
         {
             prepStmt=con.prepareStatement(query); // For 1st Query   
            rs=prepStmt.executeQuery();
            while(rs.next())
                user_type_id=rs.getString(1);
            rs.close();
            prepStmt.close();
             prepStmt = con.prepareStatement(query_1); // For query_1
            prepStmt.setString(1,user_type_id);
            rs=prepStmt.executeQuery();
           while(rs.next())
           {
                 count=0;
                   membership_type_name=rs.getString(1);
                   membership_type_id=rs.getString(2);
                   Debug.print("Type_Name"+membership_type_name+"  Type_id="+membership_type_id);
                prepStmt2=con.prepareStatement(query_2);
                prepStmt2.setString(1, user_type_id);
                prepStmt2.setString(2,membership_type_id);
                prepStmt2.setDate(3,startDate);
                prepStmt2.setDate(4,endDate);
                rs2=prepStmt2.executeQuery();
                while(rs2.next())
                {
                   user_id=rs2.getString(1);
                   prepStmt1=con.prepareStatement(query_4);
                   prepStmt1.setString(1,user_id);
                   rs1=prepStmt1.executeQuery();
                      while(rs1.next())
                            status_id_DB=rs1.getString(1);
                   rs1.close();prepStmt1.close();
                   if(status_id.equalsIgnoreCase(status_id_DB))
                   {
                       prepStmt1=con.prepareStatement(query_3);
                       prepStmt1.setString(1, area_id);
                       prepStmt1.setString(2,user_id);
                       rs1=prepStmt1.executeQuery();
                        while(rs1.next())
                        {
                             if(rs1.getString(1)!=null && rs1.getString(1).length()>0)
                                  count=count+1;
                        }
                         rs1.close();prepStmt1.close();         
                   } 
                  
                }
                HumanMembersAllMembershipsByAreaAndStatus=new HashMap();
            HumanMembersAllMembershipsByAreaAndStatus.put(membership_type_name,new Double(count));
            Debug.print("MemberShip Name "+membership_type_name+" Count is :"+count);
            totalHumanAllMembershipsByAreaAndStatus.add(HumanMembersAllMembershipsByAreaAndStatus);
                rs2.close();prepStmt2.close();
           }  
            rs.close();prepStmt.close();releaseConnection();
         }
        catch(SQLException f)
        {
            f.printStackTrace();
            Debug.print(f.getMessage());
            releaseConnection();
        }
         return totalHumanAllMembershipsByAreaAndStatus;
    }

    public int getHumanMembersSpecificAreaAndMembershipType(Date fromDate,Date toDate,String area_id,String membership_type_id)
    {
         Debug.print("Inside HLCHaveryStatelessBean getHumanMembersAllMemberhipTypesByArea");
         String user_type_id=null;String user_id=null;
          ResultSet rs=null;ResultSet rs1=null;ResultSet rs2=null;ResultSet rs3=null;
          PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;PreparedStatement prepStmt2=null;PreparedStatement prepStmt3=null;
         int count=0;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate);
         String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
         String query_1="select user_id from tblMemberDetails where  membership_type_id=? and  (add_date>=? and add_date<=?)";
         String query_2="select A.area_id  from tblMeeMapStateZip  A where A.area_id =? and (select top 1 zip from "+DBHelper.USEA_CONTACT_DETAILS+" where user_id=?) between A.zip_code_from and A.zip_code_to";
         
          makeConnection();
         try
         {
             prepStmt=con.prepareStatement(query); // For 1st Query   
            rs=prepStmt.executeQuery();
            while(rs.next())
                user_type_id=rs.getString(1);
            rs.close();
            prepStmt.close();
             prepStmt = con.prepareStatement(query_1); // For query_1
            //prepStmt.setString(1,user_type_id);
            prepStmt.setString(1,membership_type_id);
            prepStmt.setDate(2,startDate);
            prepStmt.setDate(3,endDate);
            rs=prepStmt.executeQuery();
            while(rs.next())
            {
                 user_id=rs.getString(1);
                prepStmt1=con.prepareStatement(query_2);
                prepStmt1.setString(1,area_id);
                prepStmt1.setString(2,user_id);
                rs1=prepStmt1.executeQuery();
                
                while(rs1.next())
                {
                    if(rs1.getString(1)!=null && rs1.getString(1).length()>0)
                         count=count+1;
                }
                rs1.close();prepStmt1.close();
                
            }
            rs.close();prepStmt.close();releaseConnection();
         }
         catch(SQLException r)
         {
             Debug.print(r.getMessage());
             r.printStackTrace();
             releaseConnection();
             
         }
          return count;
     }
    public int getHumanMembersSpecificAreaAndMembershipTypeAndStatus(Date fromDate,Date toDate,String area_id,String membership_type_id,String status_id) 
    {
       Debug.print("Inside HLCHaveryStatelessBean getHumanMembersAllMemberhipTypesByArea");
         String user_type_id=null;String user_id=null;
          ResultSet rs=null;ResultSet rs1=null;ResultSet rs2=null;ResultSet rs3=null;
          PreparedStatement prepStmt=null;PreparedStatement prepStmt1=null;PreparedStatement prepStmt2=null;PreparedStatement prepStmt3=null;
         int count=0;
        java.sql.Date startDate=DBHelper.toSQLDate(fromDate);
        java.sql.Date endDate=DBHelper.toSQLDate(toDate); 
         String query="select user_type_id from tblUserTypeMaster where user_type_name='Human'";
         String query_1="select user_id from tblMemberDetails where  membership_type_id=? and  (add_date>=? and add_date<=?)";
         String query_2="select status_id from "+DBHelper.USEA_MMS_MEMBERDETAIL+" where user_id=?";
         String query_3="select A.area_id  from tblMeeMapStateZip  A where A.area_id =? and (select top 1 zip from "+DBHelper.USEA_CONTACT_DETAILS+" where user_id=?) between A.zip_code_from and A.zip_code_to";
         makeConnection();
         try
         {
             prepStmt=con.prepareStatement(query);
             rs=prepStmt.executeQuery();
             while(rs.next())
                  user_type_id=rs.getString(1);
             rs.close();prepStmt.close();
             prepStmt=con.prepareStatement(query_1);
             //prepStmt.setString(1,user_type_id);
             prepStmt.setString(1,membership_type_id);
             prepStmt.setDate(2,startDate);
             prepStmt.setDate(3,endDate);
             rs=prepStmt.executeQuery();
             while(rs.next())
             {
                 user_id=rs.getString(1);
                 prepStmt1=con.prepareStatement(query_2);
                 prepStmt1.setString(1,user_id);
                 rs1=prepStmt1.executeQuery();
                 while(rs1.next())
                 {
                      if(rs1.getString(1).equalsIgnoreCase(status_id))
                      {
                          prepStmt2=con.prepareStatement(query_3);
                          prepStmt2.setString(1,area_id);
                          prepStmt2.setString(2,user_id);
                          rs2=prepStmt2.executeQuery();
                          while(rs2.next())
                          {
                          if(rs2.getString(1)!=null)
                                  count=count+1;
                          }
                          rs2.close();prepStmt2.close();
                      }
                        
                 }
                  rs1.close();prepStmt1.close();
             }
                  rs.close();prepStmt.close();releaseConnection();
         }
         catch(SQLException f)
         {
           Debug.print(f.getMessage());  
           f.printStackTrace();
           releaseConnection();
         }
         return count;
    }

    public boolean isUserIdExist(String userId) throws RemoteException{
        boolean result =false;
        if(userId!=null && userId.trim().length()!=0){
            try{
                result = new HLCMemberUpdateDAO().isUserIdExist(userId);
            } catch(Exception e){
                Debug.print("Exception while calling isUserIdExist:" + e.getMessage());
            }
        }
        return result;
    }
    
    public ArrayList getAreaProgramDetailsFrmHistory(String memberId) throws RemoteException
   {
      ArrayList areaProgDets= new ArrayList();
       Debug.print("kaverystateless Bean getAreaProgramDetailsFrmHistory");
       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
       try{
           areaProgDets = objDAO.selectAreaProgramDetailsFrmHistory(memberId);  
       }catch(Exception e){
          Debug.print("Exception while calling getAreaProgramDetailsFrmHistory():" + e.getMessage());  
       }
       
        return areaProgDets; 
   }
    public ArrayList getAreaZipDetails(String memberId) throws RemoteException
   {
      ArrayList areaZipList= new ArrayList();
       Debug.print("kaverystateless Bean getAreaZipDetails");
       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
       try{
           areaZipList = objDAO.selectAreaZipDetails(memberId);  
       }catch(Exception e){
          Debug.print("Exception while calling getAreaZipDetails():" + e.getMessage());  
       }     
        return areaZipList; 
   }
    
     public ArrayList getAreaProgBasedOnAgeAndProgYear(int age, int progYear, String areaId) throws RemoteException
   {
      ArrayList progList= new ArrayList();
       Debug.print("kaverystateless Bean getAreaProgBasedOnAgeAndProgYear");
       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
       try{
           progList = objDAO.selectAreaProgBasedOnAgeAndProgYear(age,progYear,areaId);  
       }catch(Exception e){
          Debug.print("Exception while calling getAreaProgBasedOnAgeAndProgYear():" + e.getMessage());  
       }     
        return progList; 
   }
    
      public ArrayList getAreaProgramDetailsFrmTypeMaster(int tempProgYear, String areaId) throws RemoteException
   {
      ArrayList areaProgDets= new ArrayList();
       Debug.print("kaverystateless Bean getAreaProgramDetailsFrmTypeMaster");
       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
       try{
           areaProgDets = objDAO.selectAreaProgramDetailsFrmTypeMaster(tempProgYear,areaId);  
       }catch(Exception e){
          Debug.print("Exception while calling getAreaProgramDetailsFrmTypeMaster():" + e.getMessage());  
       }
       
        return areaProgDets; 
   }
       public boolean insertAreaMembProgram(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws RemoteException{
        boolean result =false;       
            try{
                result = new HLCMemberUpdateDAO().insertAreaMembProgram(objMember,objPayment);
            } catch(Exception e){
                Debug.print("Exception while calling isMemberExist():" + e.getMessage());
            }
       
        return result;
    }
       
        public boolean getAreaMembHistDetails(HLCMemberDetails objMember, HLCPaymentDetails objPayment) throws RemoteException{
        boolean result =false;       
            
       try {
            makeConnection();
         String areaMembHisId="";
            String selectStatement = "select area_member_history_id from " + DBHelper.USEA_MMS_AREA_MEMBER_HISTORY_DETAILS + " where active_status= ? and member_id= ?";
            prepStmt = con.prepareStatement(selectStatement);         
            prepStmt.setBoolean(1, true);
            prepStmt.setString(2, objMember.getMemberId());           
            ResultSet rs = prepStmt.executeQuery();
             while(rs.next()){
                  areaMembHisId=rs.getString(1);
                  
            if(areaMembHisId!=null){               
            String updateStatement = "update " + DBHelper.USEA_MMS_AREA_MEMBER_HISTORY_DETAILS + " set active_status= ? where area_member_history_id= ?";
            prepStmt = con.prepareStatement(updateStatement);         
            prepStmt.setBoolean(1, false);
            prepStmt.setString(2, areaMembHisId);  
         
            Debug.print("Before calling executeUpdate Mathod in getAreaMembHistDetails");
             int cnt = prepStmt.executeUpdate();
             
            }      
             }
           rs.close();
           prepStmt.close(); 
           releaseConnection();
            } catch(Exception e){
                Debug.print("Exception while calling getAreaMembHistDetails():" + e.getMessage());
            }
       
        return result;
    }
        
         public ArrayList getMembershipStatusFromHistoryForAreaProgram(String membId) throws RemoteException{
        ArrayList Collection_values = new ArrayList();
        try {
            makeConnection();
            
            String query = "select Top 1 A.membership_type_id, A.status_id, A.to_date, B.membership_type_name, B.membership_year, C.status_name "+
                    "from "+DBHelper.USEA_MEMBERSHIP_HISTORY_DETAILS +" A, "+DBHelper.USEA_MEMBERSHIP_TYPE+" B, "+DBHelper.USEA_STATUS_MASTER+" C "+
                    "where A.membership_type_id = B.membership_type_id and A.status_id = C.status_id and "+
                    "A.member_id = ? order by A.add_date desc";
            
            Debug.print("select Query  :\n"+query);
            PreparedStatement prepStmt = con.prepareStatement(query);
            prepStmt.setString(1, membId);
            ResultSet rs = prepStmt.executeQuery();
            
            while(rs.next()){
                
                
                String membership_type_id = rs.getString(1);
                String status_id = rs.getString(2);
                Date to_date = rs.getDate(3);
                String memberTypeName = rs.getString(4);
                String membership_year = rs.getString(5);
                String statusName = rs.getString(6);
                
                String values[] = {memberTypeName,membership_year,statusName,String.valueOf(to_date)};
                Collection_values.add(values);
            }
            Debug.print("Size of the collection values is "+Collection_values.size());
            rs.close();
        } catch(Exception e){
            e.printStackTrace();
            Debug.print("Exception caused is "+e);
        } finally{
            releaseConnection();
        }
        return Collection_values;
    }
         
          public ArrayList getAreaZipDetailsBasedOnUserId(String userId) throws RemoteException
   {
      ArrayList areaZipList= new ArrayList();
       Debug.print("kaverystateless Bean getAreaZipDetailsBasedOnUserId");
       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
       try{
           areaZipList = objDAO.selectAreaZipDetailsBasedOnUserId(userId);  
       }catch(Exception e){
          Debug.print("Exception while calling getAreaZipDetailsBasedOnUserId():" + e.getMessage());  
       }     
        return areaZipList; 
   } 
         
         
    

    public int getTotalUsersRegister() throws RemoteException
    {
        Debug.print("Inside HLCkaverystatelessBean");
        String query="select count(*) from "+DBHelper.USEA_MMS_USERMASTER+" where user_type_id=? and active_status=?";
        ResultSet rs=null;PreparedStatement prep=null;
        int user_Count=0;
        makeConnection();
        try
        {
           prep=con.prepareStatement(query);          
           prep.setString(1,"caafe0ba-6a20-49bf-aa86-8f95f7d9346d");
           prep.setString(2,"true");
           rs=prep.executeQuery();
            while(rs.next())
                user_Count=Integer.parseInt(rs.getString(1));
           rs.close();prep.close();releaseConnection();
        }
      
        catch(Exception er)
        {
            Debug.print("Exception Occured"+er.getMessage());
            releaseConnection();
        }
        return  user_Count; 
    }
    public int updateUserRequestStatus(String userId)
    {
       Debug.print("Inside HLCkaverystatelessBean updateUserRequestStatus method");
       String query="update "+DBHelper.USEA_MMS_USERMASTER+" SET request_status='true' where user_id=?";
       PreparedStatement prep=null;int result=0;
       makeConnection();
       try
       {
           prep=con.prepareStatement(query);          
           prep.setString(1,userId);
            result=prep.executeUpdate();
            prep.close();releaseConnection();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
           releaseConnection();
       }
       return result;
    }
    public int getRolesCountForUser(String userId)
    {
        Debug.print("Inside HLCkaverystatelessBean getRolesCountForUser method");
       String query="select count(*) from tblMapUserPrivilege where user_id=? and active_status='true'";
       PreparedStatement prep=null;int result=0;ResultSet rs=null;
       makeConnection();
       try
       {
            prep=con.prepareStatement(query);          
           prep.setString(1,userId);
            rs=prep.executeQuery();
            while(rs.next())
                result=Integer.parseInt(rs.getString(1));
            rs.close();prep.close();releaseConnection();
       }
       catch(SQLException e)
       {
           Debug.print(e.getMessage());
           releaseConnection();
       }
       return result;
    }
    
    public int getUserCountBasedOnRole()
    {
       Debug.print("Inside HLCkaverystatelessBean getRolesCountForUser method");
       String query="select Count (DISTINCT [user_id]) from tblMapUserPrivilege where active_status='true'";
       PreparedStatement prep=null;int result=0;ResultSet rs=null;
       
       try
       {
    	   makeConnection(); 
           prep=con.prepareStatement(query);
           rs=prep.executeQuery();
           while(rs.next())
                result=Integer.parseInt(rs.getString(1));
           rs.close();prep.close();releaseConnection();
       }
       catch(Exception er)
       {
    	   
           Debug.print("Exception getUserCountBasedOnRole(); "+er.getMessage());
           //er.printStackTrace();
           if(con!=null)
           {
           releaseConnection();
           }
           
           result=-1;
           return result;
          
           //throw new EJBException("Exception Unable to connect to database getUserCountBasedOnRole. " + er.getMessage());
       }
       return result;
    }
    
    public LinkedHashMap getMemberJoined(String year) throws RemoteException
    {
       Debug.print("Inside HLCkaverystatelessBean getMemberJoined method");
       String query="select count(*) from tblMembershipHistoryDetails where  year(add_date) like ? and month(add_date)=?  ";
       LinkedHashMap membersCount=new LinkedHashMap();
       
       String months[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
       PreparedStatement prep=null;int result=0;ResultSet rs=null;
       makeConnection(); 
       try
       {
           for(int i=0;i<months.length;i++)
           {
           prep=con.prepareStatement(query);
           prep.setString(1,year);
           prep.setString(2,(i+1)+"");
            rs=prep.executeQuery();
            while(rs.next())
                
                result=Integer.parseInt(rs.getString(1));
            membersCount.put(months[i],new Integer(result));
            rs.close();
           }
           prep.close();releaseConnection();
       }
       catch(Exception e)
       {
          Debug.print("Exception Occured"+e.getMessage()); 
       }
       return membersCount;
    }
    public String getErrorDets(String errorCode) throws RemoteException{
        
        String contactTypeId = null;
        String correctiveAction = "";
        try{
         makeConnection();    
    String query = "select corrective_action from tblDirectPaymentAPIErrorsMaster where error_code=?";    
       
    prepStmt = con.prepareStatement(query);         
    prepStmt.setString(1, errorCode);
   ResultSet rs = prepStmt.executeQuery();         
            while(rs.next()){                            
               correctiveAction = rs.getString(1);              
            }    
           rs.close();
           prepStmt.close(); 
           releaseConnection();
        } catch(Exception exp){
            throw new EJBException("getErrorDets: " + exp.getMessage());
        }
        
        return correctiveAction;
    }

   public boolean isInvoiceIdExist(String invoiceId) throws RemoteException {
        boolean bol = false;
        String invoiceId1 = "";
        Debug.print("MemberUpdateDAO isInvoiceIdExist ");
        try {
            makeConnection();

            String str = "SELECT invoice_id FROM tblUserPaymentDetails WHERE invoice_id = ?";
            prepStmt = con.prepareStatement(str);
            prepStmt.setString(1, invoiceId);
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                bol = true;
                invoiceId1 = rs.getString(1);
                Debug.print("invoice Id exist : " + bol);
            }
            prepStmt.close();
            releaseConnection();
        } catch(Exception exp){
            throw new EJBException("isInvoiceIdExist: " + exp.getMessage());
        }
        return bol;
    }
   
   public ArrayList getQuarterlySalesByItemForYear(String year, String item) throws RemoteException {
	    ArrayList vObj = new ArrayList();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      vObj = objDAO.getQuarterlySalesByItemForYear(year, item);
	    } catch (Exception e) {
	      Debug.print("Error While getting  list : " + e.getMessage());
	    }
	    return vObj;
	  }

	  public ArrayList getQuarterWiseSalesAllItemsForYear(String quarter, String year) throws RemoteException {
	    ArrayList vObj = new ArrayList();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      vObj = objDAO.getQuarterWiseSalesAllItemsForYear(quarter, year);
	    } catch (Exception e) {
	      Debug.print("Error While getting list : " + e.getMessage());
	    }
	    return vObj;
	  }

	  public ArrayList getQuarterlyVolumeSalesByItemForYear(String year, String item) throws RemoteException {
	    ArrayList vObj = new ArrayList();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      vObj = objDAO.getQuarterlyVolumeSalesByItemForYear(year, item);
	    } catch (Exception e) {
	      Debug.print("Error While getting list : " + e.getMessage());
	    }
	    return vObj;
	  }

	  public ArrayList getStatusWisePurchaseRequisition(java.util.Date startDate, java.util.Date endDate) throws RemoteException {
	    ArrayList vObj = new ArrayList();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      vObj = objDAO.getStatusWisePurchaseRequisition(startDate, endDate);
	    } catch (Exception e) {
	      Debug.print("Error While getting getStatusWisePurchaseRequisition : " + e.getMessage());
	    }
	    return vObj;
	  }

	  public Double getPurchaseRequisitionPerStatus(String status) {
	    Double count = null;
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      count = objDAO.getPurchaseRequisitionPerStatus(status);
	    } catch (Exception e) {
	      Debug.print("Error While getting getPurchaseRequisitionPerStatus : " + e.getMessage());
	    }
	    return count;
	  }

	  public HashMap getStockReqAndAvailable(java.util.Date startDate) throws RemoteException {
	    HashMap list = null;
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      list = objDAO.getStockReqAndAvailable(startDate);
	    } catch (Exception e) {
	      Debug.print("Error While getting getStockReqAndAvailable : " + e.getMessage());
	    }
	    return list;
	  }

	  public ArrayList getDefectiveStocksSuppliedByEachVendorForYear(String year) throws RemoteException {
	    ArrayList vObj = new ArrayList();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      vObj = objDAO.getDefectiveStocksSuppliedByEachVendorForYear(year);
	    } catch (Exception e) {
	      Debug.print("Error While getting getDefectiveStocksSuppliedByEachVendorForYear : " + e.getMessage());
	    }
	    return vObj;
	  }

	  public ArrayList getStocksSuppliedByEachVendorForYear(String year) throws RemoteException {
	    ArrayList vObj = new ArrayList();
	    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	    try {
	      vObj = objDAO.getStocksSuppliedByEachVendorForYear(year);
	    } catch (Exception e) {
	      Debug.print("Error While getting getStocksSuppliedByEachVendorForYear : " + e.getMessage());
	    }
	    return vObj;
	  }
//==============================Dhivya Here: User View Mapping======================================================
	  
	  public boolean addUserCriteria(String usrId, String usrCrit) throws RemoteException {
	        boolean bol = false;
	        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	        try {
	            makeConnection();
	            
	            bol = objDAO.addUserCriteria(usrId,usrCrit);
	            Debug.print(" addUserCriteria() : "+bol);
	        }catch (Exception e){
	            releaseConnection();
	            e.printStackTrace();
	        }finally {
	            releaseConnection();
	        }
	        return bol;
	    }
	  
	  
	  public String getusrCriteria(String userId) throws RemoteException {
		  String userCri = "";
		    HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
		    try {
		    	userCri = objDAO.getusrCriteria(userId);
		    } catch (Exception e) {
		      Debug.print("Error While getting getusrCriteria : " + e.getMessage());
		    }
		    return userCri;
		  }
	  
	  public boolean updateUserCriteria(String usrId, String viewPnt, String usrCrit) throws RemoteException {
	        boolean bol = false;
	        HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	        try {
	            makeConnection();
	            
	            bol = objDAO.updateUserCriteria(usrId,viewPnt,usrCrit);
	            Debug.print(" updateUserCriteria() : "+bol);
	        }catch (Exception e){
	            releaseConnection();
	            e.printStackTrace();
	        }finally {
	            releaseConnection();
	        }
	        return bol;
	    }
	  
	  public ArrayList getUserViewPoints(String userId) throws RemoteException
	   {
	      ArrayList usrViewPnt= new ArrayList();
	       Debug.print("kaverystateless Bean getUserViewPoints");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   usrViewPnt = objDAO.selectUserViewPoints(userId);  
	       }catch(Exception e){
	          Debug.print("Exception while calling getUserViewPoints():" + e.getMessage());  
	       }     
	        return usrViewPnt; 
	   }  
	  
	  
	
	 public boolean checkEmpIdExist(String empId) throws RemoteException {
	        Debug.print("kaverystatelessBean checkEmpIdExist() empId:" + empId);
	        boolean result =false;
	        if(empId!=null && empId.trim().length()!=0 ){
	            try{
	               
	                result = new HLCMemberUpdateDAO().checkEmpIdExist(empId);
	            } catch(Exception e){
	                Debug.print("Exception while calling checkUserNameExist():" + e.getMessage());
	            }
	        }
	        Debug.print("kaverystatelessBean checkEmpIdExist() :" + result);
	        return result;
	    }
	 public ArrayList getEmpInfBasedOnId(String empId) throws RemoteException
	   {
	      ArrayList empDetList= new ArrayList();
	       Debug.print("kaverystateless Bean getEmpInfBasedOnId");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   empDetList = objDAO.getEmpInfBasedOnId(empId);  
	       }catch(Exception e){
	          Debug.print("Exception while calling getEmpInfBasedOnId():" + e.getMessage());  
	       }     
	        return empDetList; 
	   }  
	 public ArrayList selectCountryList() throws RemoteException
	   {
	      ArrayList empDetList= new ArrayList();
	       Debug.print("kaverystateless Bean selectCountryList");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   empDetList = objDAO.selectCountryList();  
	       }catch(Exception e){
	          Debug.print("Exception while calling selectCountryList():" + e.getMessage());  
	       }     
	        return empDetList; 
	   }  
	 public Vector selectLocBasedOnCId(String cId) throws RemoteException
	   {
		 Vector empDetList= new Vector();
	       Debug.print("kaverystateless Bean selectLocBasedOnCId");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   empDetList = objDAO.selectLocBasedOnCId(cId);  
	       }catch(Exception e){
	          Debug.print("Exception while calling selectLocBasedOnCId():" + e.getMessage());  
	       }     
	        return empDetList; 
	   }  
	 public ArrayList selectLOBList() throws RemoteException
	   {
	      ArrayList empDetList= new ArrayList();
	       Debug.print("kaverystateless Bean selectLOBList");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   empDetList = objDAO.selectLOBList();  
	       }catch(Exception e){
	          Debug.print("Exception while calling selectLOBList():" + e.getMessage());  
	       }     
	        return empDetList; 
	   }  
	 public Vector selectDeptSuperVisorBasedOnCId(String lobId,String depId,String roleGrade) throws RemoteException
	   {
		 Vector empDetList= new Vector();
	       Debug.print("kaverystateless Bean selectDeptSuperVisorBasedOnCId");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   empDetList = objDAO.selectDeptSuperVisorBasedOnCId(lobId,depId,roleGrade);  
	       }catch(Exception e){
	          Debug.print("Exception while calling selectDeptSuperVisorBasedOnCId():" + e.getMessage());  
	       }     
	        return empDetList; 
	   }  
	 
	 public Vector selectLOBBasedOnCId(String baseId,String curId) throws RemoteException
	   {
		 Vector empDetList= new Vector();
	       Debug.print("kaverystateless Bean selectLOBBasedOnCId");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   empDetList = objDAO.selectLOBBasedOnCId(baseId,curId);  
	       }catch(Exception e){
	          Debug.print("Exception while calling selectLOBBasedOnCId():" + e.getMessage());  
	       }     
	        return empDetList; 
	   }  
//addEmpRegistration
	 public String addEmpRegistration(HLCEmployeeDetails objEmpMaster) throws RemoteException
	   {
		 String userId="";
	       Debug.print("kaverystateless Bean addEmpRegistration");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   userId = objDAO.addEmpRegistration(objEmpMaster);  
	       }catch(Exception e){
	          Debug.print("Exception while calling addEmpRegistration():" + e.getMessage());  
	       }     
	        return userId; 
	   } 
	 
//searchEmployee
	
	 public ArrayList searchEmpBySearchCriteria(String fName, String lName,String empId,String supName) throws RemoteException {
	        Debug.print("HLCKaverystatelessBean searchEmpBySearchCriteria()");
	        ArrayList memberList = new HLCMemberUpdateDAO().searchEmpBySearchCriteria(fName,lName,empId,supName);
	        return memberList;
	    }
	 
	 
	 public boolean addUserSupMapping(String userId,String supName,String supEmail,String roleId,String depId) throws RemoteException
	   {
		 boolean insertStatus=false;
	       Debug.print("kaverystateless Bean addUsrSupMapping");
	       HLCMemberUpdateDAO objDAO = new HLCMemberUpdateDAO();
	       try{
	    	   insertStatus = objDAO.addUsrSupMapping(userId,supName,supEmail,roleId,depId);  
	       }catch(Exception e){
	          Debug.print("Exception while calling addEmpRegistration():" + e.getMessage());  
	       }     
	        return insertStatus; 
	   } 
	  
	 public ArrayList getActiveUserEmpIds()  throws RemoteException {
	        Debug.print("BrahmaputraSessionBean getActiveUserEmpIds");
	        ArrayList results = new HLCMemberUpdateDAO().getActiveUserEmpIds();
	        return results;
	    }
	  
    private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(dbName);
            con = ds.getConnection();
            Debug.print("getConnection() .........");
            
        }
        catch (SQLException ex) {
        	Debug.print("SQLException() .........");
        	//makeConnection();
          throw new EJBException("SQLException Unable to connect to database. " + ex.getMessage());
        }
       
        catch (Exception ex1) {
        	Debug.print("Exception() .........");
        	//makeConnection();
            throw new EJBException("Exception Unable to connect to database. " + ex1.getMessage());
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
            if(!con.isClosed()){
                Debug.print("connection released !!");
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
       
}
