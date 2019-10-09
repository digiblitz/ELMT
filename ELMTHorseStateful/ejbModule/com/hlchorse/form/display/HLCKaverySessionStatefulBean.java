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
package com.hlchorse.form.display;

import com.hlchorse.form.util.HLCActiveStatusDAO;
import com.hlchorse.form.util.DBHelper;
import com.hlchorse.form.util.Debug;
import com.hlchorse.form.util.HLCHorseDisplayVO;
import com.hlchorse.form.util.HLCUserRegVO;
import javax.ejb.*;
import javax.sql.*;
import javax.naming.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.Date;
import java.sql.*;

/**
 * This is the bean class for the KaverySessionStatefulBean enterprise bean.
 * Created Aug 26, 2006 2:59:54 PM
 * @author harmohan
 */
public class HLCKaverySessionStatefulBean implements SessionBean, HLCKaverySessionStatefulRemoteBusiness {
   
    private SessionContext context;
    private Connection con = null;
    Vector vObj;
    private ResultSet rs,rs1;
    private PreparedStatement prepStmt;
    private String selectStatement;
    private String loginName;
    
    private String horseId;
    private String memberId;
    private String horseMemberId;
    private String horseMembershipTypeId;
    private String membershipTypeName;
    private String horseServiceTypeId;
    private String horseServiceTypeName;
    private String competitionName;
    private String registeredName;
    private String baRegisteredName;
    private String baPastName;
    private String riderMemberId;
    private String prevRiderMemberId;
    private String addRiderMemberId;
    private String ownerId;
    private String prevOwnerName;
    private String addOwnerName;
    private boolean activeStatus;
    private String statusId;
    private Date addDate;
    private Date expiryDate;
    private static String userId;
    private static String UserTypeID;
    private String statusName;
    
    
    /*-----------------------Horse Description Variable -----------------------------*/
    private String color;
    private String gender;
    private String height;
    private String yearFoaled;
    private String breed;
    private String country;
    private String sire;
    private String sireBreed;
    private String dam;
    private String damBreed;
    private String importedFrom;
    private String importDate;
    private String foreignGrade;
    private String foreignPoints;
    private String assignedGrade;
    private String assignedPoints;
    
    /*==================================User Master Variable ========================*/
    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailId;
    private String sufix;
    private String dob;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private String registerDate;
    private String login_Name;
    private boolean nonUseaMailingStatus;
    private boolean nonUseaEmailStatus;
    
    /*=============================Contact Details Variable =========================*/
    private String contactTypeId;
    private String suite;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phoneNo;
    private String mobileNo;
    private String faxNo;
    private String contactTypeName;
    
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
        // TODO implement ejbCreate if necessary, acquire resources
        // This method has access to the JNDI context so resource aquisition
        // spanning all methods can be performed here such as home interfaces
        // and data sources.
    }
    
/**
  * Name         :listHorseMemberDetails
  * Description  :This method will Display a list contenting Horse Name, Horse Member Id and Date of Registration from the database
  * @ param      :none
  * @return      :Vector
  * @throws      :RemoteException, SQLException
  */      
    
    //public Vector displayMemberUserList() throws RemoteException;
    public Vector displayMemberUserList(String userTypeName, int rCnt) throws RemoteException {
        try {
            vObj = selectMemberList(userTypeName,rCnt);
        }catch (Exception e){
            //Debug.print("Error in ListHorseMember  :  "+e.getMessage());
            e.printStackTrace();
        }
        return vObj;
    }
    
    public Vector displayHorseMember() throws RemoteException {
        try {
            vObj = selectHorseMember(); 
        }catch (Exception e){
            //Debug.print("Error in ListHorseMember  :  "+e.getMessage());
            e.printStackTrace();
        }
        return vObj;
    }
    
    public HLCHorseDisplayVO displayHorseDetails (String memberId) throws RemoteException {
       this.horseMemberId = memberId;
       HLCHorseDisplayVO objHD = null;
        try {
              objHD = selectHorseDetails(horseMemberId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objHD;
    }
    
  
    
    public boolean editActiveStatus(String activeStat, String userId) throws RemoteException {
        boolean bol = true;
        try {
            HLCActiveStatusDAO objDAO = new HLCActiveStatusDAO();
            objDAO.updateActiveStatus(activeStat,userId);
        }catch(Exception e){
            Debug.print("Error while updating status : "+e.getMessage());
        }
        return true;
    }
    
     public boolean getMemberShipStatus(String horseMemberId) throws RemoteException {
        boolean bol = true;
        HLCActiveStatusDAO objDAO = new HLCActiveStatusDAO();
        try {
       
            bol = objDAO.getMemberShipStatus(horseMemberId);
        }catch(Exception e){
            Debug.print("Error while updating status : "+e.getMessage());
        }
        return bol;
    }
    
   /* public UserRegVO displayUserRegistrationForm (String email) throws RemoteException {
       this.emailId = email;
       UserRegVO objUGVO = new UserRegVO();
        try {
              objUGVO = selectUserRegistrationForm(emailId);
              Debug.print(" User Registration Details : \n"+objUGVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objUGVO;
    }
    **/
     
    public HLCUserRegVO displayUserRegistrationForm (String loginName) throws RemoteException {
       this.loginName = loginName;
       HLCUserRegVO objUGVO = new HLCUserRegVO();
        try {
              objUGVO = selectUserRegistrationForm(loginName);
              Debug.print(" User Registration Details : \n"+objUGVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objUGVO;
    }
     
    public HLCUserRegVO displayUserRegistrationFormOnUserId(String userId) throws RemoteException {
       //this.loginName = loginName;
       HLCUserRegVO objUGVO = new HLCUserRegVO();
        try {
              objUGVO = selectUserRegistrationFormOnUserId(userId);
              Debug.print(" User Registration Details OnUserId : \n"+objUGVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objUGVO;
    }
    
 
     public Vector getRiderDetails(String riderId) throws RemoteException {
         this.memberId = riderId;
         try {
             vObj = selectRiderDetails(memberId);
         }catch(Exception e){
             e.printStackTrace();
         }
         return vObj;
     }
     
      public Vector getOwnerDetails(String loginName) throws RemoteException {
         this.loginName = loginName;
         try {
             vObj = selectOwnerDetails(loginName);
         }catch(Exception e){
             e.printStackTrace();
         }
         return vObj;
     }
      //selectHMemberBasedOnOwner(String ownerId)
    public Vector getHorseDetailsBasedOnOwner(String loginName) throws RemoteException {
         //this.emailId = email;
         try {
             vObj = selectHMemberBasedOnOwner(loginName);
         }catch(Exception e){
             e.printStackTrace();
         }
         return vObj;
     }
     
/**
  * Name         :selectHorseMember
  * Description  :This method will Display a list contenting Horse Name, Horse Member Id and Date of Registration from the database
  * @ param      :none
  * @return      :Vector
  * @throws      :RemoteException, SQLException
  */           
     private Vector selectHorseMember() throws SQLException {
         vObj = new Vector();
        // try {
             makeConnection();
             String statement = "SELECT horse_member_id, registered_name, add_date FROM "+DBHelper.USEA_HORSE_MEMBERDETAILS;
             prepStmt = con.prepareStatement(statement);
            
             rs = prepStmt.executeQuery();
             while(rs.next()){
                this.horseMemberId = rs.getString(1);
                this.registeredName = rs.getString(2);
                String addDate = rs.getString(3);
                String [] horseDet = {horseMemberId,registeredName,addDate };
                vObj.add(horseDet);
             }
             releaseConnection();
         //}catch (Exception e){
        //     Debug.print("Error while listing in selectHorseMember : "+e.getMessage());
        // }
         return vObj;
     }
     
/**
  * Name         :selectHMemberBasedOnOwner
  * Description  :This method will Display a list contenting Horse Name, Horse Member Id and Date of Registration from the database
  * @ param      :none
  * @return      :Vector
  * @throws      :RemoteException, SQLException
  */           
     private Vector selectHMemberBasedOnOwner(String loginName) throws SQLException {
         vObj = new Vector();
        // try {
             makeConnection();
            /* String statement = "SELECT horse_id,horse_member_id, competition_name,registered_name, add_date FROM "+
                DBHelper.USEA_HORSE_MEMBERDETAILS+" A, "+DBHelper.USEA_MMS_USERMASTER+
               " B  WHERE A.owner_id = B.user_id AND B.email_id = ? ";
             **/
             String statement = "SELECT horse_id,horse_member_id, competition_name,registered_name, add_date FROM "+
                DBHelper.USEA_HORSE_MEMBERDETAILS+" A, "+DBHelper.USEA_MMS_USERMASTER+
               " B  WHERE A.owner_id = B.user_id AND B.login_name = ? ";
            
             
             prepStmt = con.prepareStatement(statement);
             prepStmt.setString(1, loginName);
             rs = prepStmt.executeQuery();
             while(rs.next()){
                this.horseId = rs.getString(1);
                this.horseMemberId = rs.getString(2);
                this.competitionName = rs.getString(3);
                this.registeredName = rs.getString(4);
                this.registerDate = rs.getString(5);
               
                String [] horseDet = {horseId,horseMemberId,competitionName,registeredName,registerDate,loginName};
                vObj.add(horseDet);
             }
             releaseConnection();
         //}catch (Exception e){
        //     Debug.print("Error while listing in selectHorseMember : "+e.getMessage());
        // }
         return vObj;
     }     
     
     
     private Vector selectRiderDetails(String riderId) throws SQLException {
         Debug.print("Kavery Satefull Session Bean :  selectHorseDetails");
         this.memberId = riderId;
         vObj = new Vector();
        // try {
              makeConnection();
             selectStatement = "SELECT user_id FROM "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
             prepStmt = con.prepareStatement(selectStatement);
             prepStmt.setString(1, memberId);
             rs = prepStmt.executeQuery();
             if (rs.next()){
                this.userId = rs.getString(1);
             }
             String riderDetail [] = horseOwnerRideDetails(userId);
             vObj.add(riderDetail);
             releaseConnection();
         //}catch(Exception e){
          //   e.printStackTrace();
         //}
         return vObj;
     }
     
     private Vector selectOwnerDetails(String loginName) throws SQLException {
         Debug.print("Kavery Satefull Session Bean :  selectHorseDetails loginName:" + loginName);
         //this.memberId = riderId;
         vObj = new Vector();
         //try {
            makeConnection();
            //selectStatement = "SELECT user_id FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE email_id = ?";
            selectStatement = "SELECT user_id FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE login_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
            rs = prepStmt.executeQuery();
            System.out.println("Inside the selectUserRegistrationForm email Id : "+loginName);
            if (rs.next()) {
            this.userId = rs.getString(1);
            }
             String ownerDetail [] = horseOwnerRideDetails(userId);
             vObj.add(ownerDetail);
             
            /* selectStatement = "SELECT prev_owner_name,add_owner_name FROM " +DBHelper.USEA_HORSE_MEMBERDETAILS+" WHERE horse_member_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, horseMemberId);
            rs = prepStmt.executeQuery();

            if (rs.next()) {
            this.memberId = rs.getString(1);
            System.out.println("memberId  :  "+memberId);
            this.competitionName =  rs.getString(2);
            System.out.println("competitionName  :  "+competitionName);
            this.registeredName =  rs.getString(3);
            System.out.println("registeredName  :  "+registeredName);
            this.baRegisteredName = rs.getString(4);
            this.baPastName = rs.getString(5);
            this.riderMemberId = rs.getString(6);
            this.prevRiderMemberId = rs.getString(7);
            this.addRiderMemberId = rs.getString(8);
            this.ownerId = rs.getString(9);
            this.prevOwnerName = rs.getString(10);
            this.addOwnerName = rs.getString(11);
            }*/
             
             releaseConnection();
        // }catch(Exception e){
          //   e.printStackTrace();
        // }
         return vObj;
     }
     
  /**
  * Name         :loadHorseMemberDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private HLCHorseDisplayVO selectHorseDetails(String horseMemberId) throws SQLException {
        Debug.print("Kavery Satefull Session Bean :  selectHorseDetails");
        vObj = new Vector();
        List list = new ArrayList();
        HLCHorseDisplayVO objHorse = new HLCHorseDisplayVO();
        this.horseMemberId = horseMemberId;
        makeConnection();
        try {
        selectStatement = 
        " SELECT membership_type_name FROM "+DBHelper.USEA_MEMBERSHIP_TYPE +" A, "+ DBHelper.USEA_MMS_MEMBERDETAIL+" B "+
        " WHERE A.membership_type_id = B.membership_type_id AND B.member_id = ? ";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, horseMemberId);
        rs = prepStmt.executeQuery();
        if (rs.next()){
            this.membershipTypeName = rs.getString(1);
            System.out.println("membershipTypeName  :  "+membershipTypeName);
        }
        System.out.println("membershipTypeName  :  "+membershipTypeName);
        vObj.add(membershipTypeName);
        objHorse.setMembershipTypeName(membershipTypeName);
        selectStatement = 
        "SELECT horse_service_type_name FROM "+DBHelper.USEA_HORSE_SERVICETYPEMASTER +" A, "+ DBHelper.USEA_HORSESERVICETYPE+" B "+
        " WHERE A.horse_service_type_id = B.horse_service_type_id AND B.horse_member_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, horseMemberId);
        rs = prepStmt.executeQuery();
        String[] horseServiceTypeName= new String[4];
        int i=0;
        while (rs.next()){
            //this.horseServiceTypeName = rs.getString(1);
             System.out.println("horseServiceTypeName  :  "+horseServiceTypeName);
             //horseServiceTypeName[i++] = rs.getString(1);
             list.add(rs.getString(1));
        }
        //String horseType [] = {membershipTypeName, horseServiceTypeName };
        /*=========First Vector=======================*/
        objHorse.sethorseServiceTypeName(list);
        vObj.add(horseServiceTypeName);
        }catch (Exception e){
            System.out.println("Error while getting  horseMembershipTypeId &  horseServiceTypeId : "+e);
        }
        
        
        try {
        selectStatement = "SELECT A.horse_member_id, A.competition_name,A.registered_name,A.ba_registered_name,A.ba_past_name,"+
                "A.rider_member_id, A.prev_rider_member_id, A.add_rider_member_id, A.owner_id, A.prev_owner_name," +
                " A.add_owner_name, A.status_id, B.status_name FROM " +DBHelper.USEA_HORSE_MEMBERDETAILS+" A, " +
                DBHelper.USEA_STATUS_MASTER + " B WHERE A.status_id = B.status_id and A.horse_member_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, horseMemberId);
        rs = prepStmt.executeQuery();
        
        if (rs.next()) {
        this.memberId = rs.getString(1);
        System.out.println("memberId  :  "+memberId);
        this.competitionName =  rs.getString(2);
        System.out.println("competitionName  :  "+competitionName);
        this.registeredName =  rs.getString(3);
        System.out.println("registeredName  :  "+registeredName);
        this.baRegisteredName = rs.getString(4);
        this.baPastName = rs.getString(5);
        this.riderMemberId = rs.getString(6);
        this.prevRiderMemberId = rs.getString(7);
        this.addRiderMemberId = rs.getString(8);
        this.ownerId = rs.getString(9);
        this.prevOwnerName = rs.getString(10);
        this.addOwnerName = rs.getString(11);
        this.statusId = rs.getString(12);
        this.statusName = rs.getString(13);
        } else {
       // prepStmt.close();
       // releaseConnection();
        throw new NoSuchEntityException("Row for id " + memberId + " not found in database.");
        }
       /*=========Second Vector=======================*/
        objHorse.setMemberId(memberId);
        objHorse.setCompetitionName(competitionName);
        objHorse.setRegisteredName(registeredName);
        objHorse.setBaRegisteredName(baRegisteredName);
        objHorse.setBaPastName(baPastName);
        objHorse.setStatusId(statusId);
        objHorse.setStatusName(statusName);
        
        String NameOfHorse [] = {memberId, competitionName, registeredName, baRegisteredName, baPastName};
        vObj.add(NameOfHorse);
        }catch (Exception e){
            System.out.println("Error while getting  member details : "+e);
        }
        
        /**============================Rider Details====================================*/
        try {
        selectStatement = "SELECT user_id FROM "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, riderMemberId);
        rs = prepStmt.executeQuery();
        if (rs.next()){
            this.userId = rs.getString(1);
        }
        /*=========Third Vector=======================*/
//{prefix, firstName, middleName, lastName, suite, address1, address2, city, state, zip, phoneNo, mobileNo, emailId,userId,faxNo,country, memberId };        
        
        String riderDetails [] = horseOwnerRideDetails(userId);
        //String rider [] ={riderDetails, horseMemberId};
        vObj.add(riderDetails);
        objHorse.setRiderPrefix(riderDetails[0]);
        objHorse.setRiderFirstName(riderDetails[1]);
        objHorse.setRiderMiddleName(riderDetails[2]);
        objHorse.setRiderLastName(riderDetails[3]);
        objHorse.setRiderSuite(riderDetails[4]);
        objHorse.setRiderAddress1(riderDetails[5]);
        objHorse.setRiderAddress2(riderDetails[6]);
        objHorse.setRiderCity(riderDetails[7]);
        objHorse.setRiderState(riderDetails[8]);
        objHorse.setRiderZip(riderDetails[9]);
        objHorse.setRiderPhoneNo(riderDetails[10]);
        objHorse.setRiderMobileNo(riderDetails[11]);
        objHorse.setRiderEmailId(riderDetails[12]);
        objHorse.setRiderUserId(riderDetails[13]);
        objHorse.setRiderFaxNo(riderDetails[14]);
        objHorse.setRiderCountry(riderDetails[15]);
        objHorse.setRiderMemberId(riderMemberId);
        /*=========Fourth Vector=======================*/
        vObj.add(horseMemberId);
        
        selectStatement = "SELECT user_id FROM "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, prevRiderMemberId);
        rs = prepStmt.executeQuery();
        if (rs.next()){
            this.userId = rs.getString(1);
        }
        
        String prevRider [] = contactDetails (userId);
        /*this.prefix = contactName[0];
        this.firstName = contactName[1];
        this.middleName = contactName[2];
        this.lastName = contactName[3];*/
        /*=========Fifth Vector=======================*/
       // String prevRider [] = {prefix, firstName,middleName, lastName };
        vObj.add(prevRider);
        objHorse.setPrevRiderUserId(prevRider[0]);
        objHorse.setPrevRiderPrefix(prevRider[1]);
        objHorse.setPrevRiderFirstName(prevRider[2]);
        objHorse.setPrevRiderMiddleName(prevRider[3]);
        objHorse.setPrevRiderLastName(prevRider[4]);
        /*=========Sixth Vector=======================*/
        vObj.add(prevRiderMemberId);
        objHorse.setPrevRiderMemberId(prevRiderMemberId);
        
        selectStatement = "SELECT user_id FROM "+DBHelper.USEA_MMS_MEMBERDETAIL +" WHERE member_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, addRiderMemberId);
        rs = prepStmt.executeQuery();
        if (rs.next()){
            this.userId = rs.getString(1);
        }
        /*=========Seventh Vector=======================*/
        String addRider [] = contactDetails (userId);//{userId,prefix,firstName,middleName,lastName };
        vObj.add(addRider);
        objHorse.setAddRiderUserId(addRider[0]);
        objHorse.setAddRiderPrefix(addRider[1]);
        objHorse.setAddRiderFirstName(addRider[2]);
        objHorse.setAddRiderMiddleName(addRider[3]);
        objHorse.setAddRiderLastName(addRider[4]);
        /*=========Eighth Vector=======================*/
        vObj.add(addRiderMemberId);
        objHorse.setAddRiderMemberId(addRiderMemberId);
        }catch (Exception e){
            System.out.println("Error while getting  Rider Details : "+e);
        }
        
        
        /**============================Owner details====================================*/
        try {
        String ownerDet [] = horseOwnerRideDetails(ownerId);
        /*=========Nineth Vector=======================*/
//{prefix, firstName, middleName, lastName, suite, address1, address2, city, state, zip, phoneNo, mobileNo, emailId,userId,faxNo,country };        
        
        vObj.add(ownerDet);
        objHorse.setOwnerPrefix(ownerDet[0]);
        objHorse.setOwnerFirstName(ownerDet[1]);
        objHorse.setOwnerMiddleName(ownerDet[2]);
        objHorse.setOwnerLastName(ownerDet[3]);
        objHorse.setOwnerSuite(ownerDet[4]);
        objHorse.setOwnerAddress1(ownerDet[5]);
        objHorse.setOwnerAddress2(ownerDet[6]);
        objHorse.setOwnerCity(ownerDet[7]);
        objHorse.setOwnerState(ownerDet[8]);
        objHorse.setOwnerZip(ownerDet[9]);
        objHorse.setOwnerPhoneNo(ownerDet[10]);
        objHorse.setOwnerMobileNo(ownerDet[11]);
        objHorse.setOwnerEmailId(ownerDet[12]);
        objHorse.setOwnerUserId(ownerDet[13]);
        objHorse.setOwnerFaxNo(ownerDet[14]);
        objHorse.setOwnerCountry(ownerDet[15]);
        String prevAddOwner [] = {addOwnerName, prevOwnerName};
        /*=========Tenth Vector=======================*/
        vObj.add(prevAddOwner);
        objHorse.setAddOwnerName(addOwnerName);
        objHorse.setPrevOwnerName(prevOwnerName);
        }catch (Exception e){
            System.out.println("Error while getting  Owner Details : "+e);
        }
        
        /*============================Horse Description=================================*/
        try {
        selectStatement = "SELECT horse_member_id, color, gender, height, year_foaled, " +
                "breed, country, sire, sire_breed, dam, dam_breed, imported_from, import_date, " +
                "foreign_grade, foreign_points, assigned_grade, assigned_points FROM " +DBHelper.USEA_HORSE_DESCRIPTION+" WHERE horse_member_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, horseMemberId);
        rs = prepStmt.executeQuery();
        
        if (rs.next()) {
        this.memberId = rs.getString(1);
        System.out.print("Member ID : "+memberId);
        this.color =  rs.getString(2);
        System.out.print("Color : "+color);
        this.gender =  rs.getString(3);
        System.out.print("Gender : "+gender);
        this.height = rs.getString(4);
        this.yearFoaled = rs.getString(5);
        this.breed = rs.getString(6);
        this.country = rs.getString(7);
        this.sire = rs.getString(8);
        this.sireBreed = rs.getString(9);
        this.dam = rs.getString(10);
        this.damBreed = rs.getString(11);
        this.importedFrom = rs.getString(12);
        this.importDate = rs.getString(13);
        this.foreignGrade = rs.getString(14);
        this.foreignPoints = rs.getString(15);
        this.assignedGrade = rs.getString(16);
        this.assignedPoints = rs.getString(17);
         System.out.print("assignedPoints : "+assignedPoints);
        /*=========Eleventh Vector=======================*/
        String horseDescription [] = {color, gender, height, yearFoaled, breed, country, sire, sireBreed, dam, damBreed, importedFrom, 
        importDate, foreignGrade, foreignPoints, assignedGrade, assignedPoints };
        vObj.add(horseDescription);
        objHorse.setHorseColor(horseDescription[0]);
        objHorse.setHorseGender(horseDescription[1]);
        objHorse.setHorseHeight(horseDescription[2]);
        objHorse.setHorseYearFoaled(horseDescription[3]);
        objHorse.setHorseBreed(horseDescription[4]);
        objHorse.setHorseCountry(horseDescription[5]);
        objHorse.setHorseSire(horseDescription[6]);
        objHorse.setHorseSireBreed(horseDescription[7]);
        objHorse.setHorseDam(horseDescription[8]);
        objHorse.setHorseDamBreed(horseDescription[9]);
        objHorse.setHorseImportedFrom(horseDescription[10]);
        objHorse.setHorseImportDate(horseDescription[11]);
        objHorse.setHorseForeignGrade(horseDescription[12]);
        objHorse.setHorseForeignPoints(horseDescription[13]);
        objHorse.setHorseAssignedGrade(horseDescription[14]);
        objHorse.setHorseAssignedPoints(horseDescription[15]);
        
    } else {
        prepStmt.close();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + memberId + " not found in database.");
    }
        }catch (Exception e){
            System.out.println("Error while getting  Horse Description : "+e);
        }
        prepStmt.close();
        releaseConnection();
      return objHorse;  
    }  
    
   // public Vector getRideretails(String riderId)
/**
  * Name         :horseOwnerRideDetails
  * Description  :This method will extract the owner or Rider etails line Name, Address etc
  * @ param      :userId
  * @return      :Array of String
  * @throws      :SQLException
  */
    
    public String [] horseOwnerRideDetails(String userId) throws SQLException {
        
        try {
        String contactName [] = contactDetails (userId);
        this.userId = contactName[0];
        this.prefix = contactName[1];
        this.firstName = contactName[2];
        this.middleName = contactName[3];
        this.lastName = contactName[4];
        this.emailId = contactName[5];
        
        selectStatement = "SELECT A.suite,A.address1,A.address2,A.city,A.state,A.zip,A.phone_no, A.mobile_no,A.fax_no,A.country, B.member_id FROM " +
                DBHelper.USEA_CONTACT_DETAILS+" A, "+DBHelper.USEA_MMS_MEMBERDETAIL+" B "+
                " WHERE A.user_id = B.user_id AND A.user_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId);
        rs = prepStmt.executeQuery();
        System.out.println("Inside the loadContactDetails User Id : "+userId);
        if (rs.next()) {
        this.suite = rs.getString(1);
        this.address1 = rs.getString(2);
        this.address2 = rs.getString(3);
        this.city = rs.getString(4);
        this.state = rs.getString(5);
        this.zip = rs.getString(6);
        this.phoneNo = rs.getString(7);
        this.mobileNo = rs.getString(8);
        this.faxNo = rs.getString(9);
        this.country = rs.getString(10);
        this.memberId = rs.getString(11);
        }

        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Error While getting owner and rider details");
        }
         String ownerDetail [] =  {prefix, firstName, middleName, lastName, suite, address1, address2, city, state, zip, phoneNo, mobileNo, emailId,userId,faxNo,country,memberId };
         return ownerDetail;
    }
    
    public String [] contactDetails (String userId)throws SQLException {
        try {
        selectStatement = "SELECT prefix,first_name,middle_name,last_name,email_id FROM " +
                DBHelper.USEA_MMS_USERMASTER+" WHERE user_id = ?";
        prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, userId);
        rs = prepStmt.executeQuery();
        System.out.println("Inside the loadUserDetails User Id : "+userId);
        if (rs.next()) {
        this.prefix = rs.getString(1);
        this.firstName = rs.getString(2);
        this.middleName = rs.getString(3);
        this.lastName = rs.getString(4);
        this.emailId = rs.getString(5);
        }
        String contactName [] = {userId,prefix,firstName,middleName,lastName,emailId };
        return contactName;
        }catch (Exception e){
            System.out.println("Error while getting  Contact Details : "+e);
        }
        String contactName [] = {userId,prefix,firstName,middleName,lastName,emailId };
        return contactName;
    }
    
    public HLCUserRegVO selectUserRegistrationForm(String loginName)throws SQLException {
        vObj = new Vector();
        HLCUserRegVO objURVO = new HLCUserRegVO();
        makeConnection();
        try {
            selectStatement = "SELECT user_id, contact_type_id, prefix,first_name,middle_name,last_name,sufix,dob,gender,email_id, password, secret_question, "+
                    "secret_answer,login_name,non_usea_mailing_status,non_usea_email_status FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE login_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, loginName.trim());
            rs = prepStmt.executeQuery();
            System.out.println("Inside the selectUserRegistrationForm Login Name : "+loginName);
            if (rs.next()) {
            this.userId = rs.getString(1);
            this.contactTypeId = rs.getString(2);
            this.prefix = rs.getString(3);
            this.firstName = rs.getString(4);
            this.middleName = rs.getString(5);
            this.lastName = rs.getString(6);
            this.sufix = rs.getString(7);
            this.dob = rs.getString(8);
            this.gender = rs.getString(9);
            this.emailId = rs.getString(10);
            this.password = rs.getString(11);
            this.secretQuestion = rs.getString(12);
            this.secretAnswer = rs.getString(13);
            this.loginName = rs.getString(14);
            this.nonUseaMailingStatus = rs.getBoolean(15);
            this.nonUseaEmailStatus = rs.getBoolean(16);
            }
                try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPEMASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            //prepStmt.close();
            //rs.close();
            //releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            /*====================Adding User Details fro User Master===== Vector One=============================================*/
            objURVO.setPreferedCommunication(contactTypeName);
            objURVO.setPrefix(prefix);
            objURVO.setFirstName(firstName);
            objURVO.setMiddleName(middleName);
            objURVO.setLastName(lastName);
            objURVO.setSufix(sufix);
            objURVO.setDob(dob);
            objURVO.setGender(gender);
            objURVO.setEmailId(emailId);
            objURVO.setPassword(password);
            objURVO.setSecretQuestion(secretQuestion);
            objURVO.setSecretAnswer(secretAnswer);
            objURVO.setUserId(userId);
            objURVO.setLoginName(loginName);
            objURVO.setNonUseaEmailStatus(nonUseaEmailStatus);
            objURVO.setNonUseaMailingStatus(nonUseaMailingStatus);
            String userDetail [] = {contactTypeName,prefix,firstName,middleName,lastName,sufix,dob,gender,emailId,password,secretQuestion,secretAnswer,userId ,loginName};
            vObj.add(userDetail);
            //DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
            //        "address2,city,state,country,zip,phone_no, mobile_no, fax_no
            try {
                 
                selectStatement = "SELECT suite,address1,address2,city,state,country,zip,phone_no, mobile_no,fax_no,contact_type_id FROM " +
                        DBHelper.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the loadContactDetails User Id : "+userId);
                while (rs.next()) {
                this.suite = rs.getString(1);
                this.address1 = rs.getString(2);
                this.address2 = rs.getString(3);
                this.city = rs.getString(4);
                this.state = rs.getString(5);
                this.country = rs.getString(6);
                this.zip = rs.getString(7);
                this.phoneNo = rs.getString(8);
                this.mobileNo = rs.getString(9);
                this.faxNo = rs.getString(10);
                this.contactTypeId = rs.getString(11);
                    try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPEMASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                /**==========================Contact Details including Primary and Secondary name ==================================*/
                if (contactTypeName.equalsIgnoreCase("Primary")) {
                    objURVO.setPrimaryContactTypeName(contactTypeName);
                    objURVO.setPrimarySuite(suite);
                    objURVO.setPrimaryAddress1(address1);
                    objURVO.setPrimaryAddress2(address2);
                    objURVO.setPrimaryCity(city);
                    objURVO.setPrimaryState(state);
                    objURVO.setPrimaryCountry(country);
                    objURVO.setPrmaryZip(zip);
                    objURVO.setPrimaryPhoneNo(phoneNo);
                    objURVO.setPromaryMobileNo(mobileNo);
                    objURVO.setPrimaryFaxNo(faxNo);
                }else if (contactTypeName.equalsIgnoreCase("Secondary")){
                    objURVO.setSecondaryContactTypeName(contactTypeName);
                    objURVO.setSecondarySuite(suite);
                    objURVO.setSecondaryAddress1(address1);
                    objURVO.setSecondaryAddress2(address2);
                    objURVO.setSecondaryCity(city);
                    objURVO.setSecondaryState(state);
                    objURVO.setSecondaryCountry(country);
                    objURVO.setSecondaryZip(zip);
                    objURVO.setSecondaryPhoneNo(phoneNo);
                    objURVO.setSecondaryMobileNo(mobileNo);
                    objURVO.setSecondaryFaxNo(faxNo);
                }
                String contactDetail [] =  {contactTypeName, suite, address1, address2, city, state, country, zip, phoneNo, mobileNo, faxNo }; 
                vObj.add(contactDetail);
                }
            
            releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //prepStmt.close();
                //rs.close();
                //rs1.close();
                releaseConnection();
            }

        return objURVO;
    }
    
    public HLCUserRegVO selectUserRegistrationFormOnUserId(String userId)throws SQLException {
        vObj = new Vector();
        HLCUserRegVO objURVO = new HLCUserRegVO();
        makeConnection();
        try {
            selectStatement = "SELECT user_id, contact_type_id, prefix,first_name,middle_name,last_name,sufix,dob,gender,email_id, password, secret_question, "+
                    "secret_answer,login_name,non_usea_mailing_status,non_usea_email_status FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId);
            rs = prepStmt.executeQuery();
            System.out.println("Inside the selectUserRegistrationForm userId : "+userId);
            if (rs.next()) {
            this.userId = rs.getString(1);
            this.contactTypeId = rs.getString(2);
            this.prefix = rs.getString(3);
            this.firstName = rs.getString(4);
            this.middleName = rs.getString(5);
            this.lastName = rs.getString(6);
            this.sufix = rs.getString(7);
            this.dob = rs.getString(8);
            this.gender = rs.getString(9);
            this.emailId = rs.getString(10);
            this.password = rs.getString(11);
            this.secretQuestion = rs.getString(12);
            this.secretAnswer = rs.getString(13);
            this.loginName = rs.getString(14);
            this.nonUseaMailingStatus = rs.getBoolean(15);
            this.nonUseaEmailStatus = rs.getBoolean(16);
            }
                try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPEMASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            //prepStmt.close();
            //rs.close();
            //releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            /*====================Adding User Details fro User Master===== Vector One=============================================*/
            objURVO.setPreferedCommunication(contactTypeName);
            objURVO.setPrefix(prefix);
            objURVO.setFirstName(firstName);
            objURVO.setMiddleName(middleName);
            objURVO.setLastName(lastName);
            objURVO.setSufix(sufix);
            objURVO.setDob(dob);
            objURVO.setGender(gender);
            objURVO.setEmailId(emailId);
            objURVO.setPassword(password);
            objURVO.setSecretQuestion(secretQuestion);
            objURVO.setSecretAnswer(secretAnswer);
            objURVO.setUserId(userId);
            objURVO.setLoginName(loginName);
            objURVO.setNonUseaEmailStatus(nonUseaEmailStatus);
            objURVO.setNonUseaMailingStatus(nonUseaMailingStatus);
            String userDetail [] = {contactTypeName,prefix,firstName,middleName,lastName,sufix,dob,gender,emailId,password,secretQuestion,secretAnswer,userId ,loginName};
            vObj.add(userDetail);
            //DBHelper.USEA_CONTACT_DETAILS + " (contact_type_id,user_id,suite,address1,"+
            //        "address2,city,state,country,zip,phone_no, mobile_no, fax_no
            try {
                 
                selectStatement = "SELECT suite,address1,address2,city,state,country,zip,phone_no, mobile_no,fax_no,contact_type_id FROM " +
                        DBHelper.USEA_CONTACT_DETAILS+" WHERE user_id = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userId);
                rs = prepStmt.executeQuery();
                System.out.println("Inside the loadContactDetails User Id : "+userId);
                while (rs.next()) {
                this.suite = rs.getString(1);
                this.address1 = rs.getString(2);
                this.address2 = rs.getString(3);
                this.city = rs.getString(4);
                this.state = rs.getString(5);
                this.country = rs.getString(6);
                this.zip = rs.getString(7);
                this.phoneNo = rs.getString(8);
                this.mobileNo = rs.getString(9);
                this.faxNo = rs.getString(10);
                this.contactTypeId = rs.getString(11);
                    try {
                        String str = "SELECT contact_type_name FROM "+DBHelper.USEA_CONTACT_TYPEMASTER+ " WHERE contact_type_id = ? ";
                        prepStmt = con.prepareStatement(str);
                        prepStmt.setString(1, contactTypeId);
                        rs1 = prepStmt.executeQuery();
                        if (rs1.next()){
                            this.contactTypeName = rs1.getString(1);
                            System.out.println(" contactTypeName   :  "+contactTypeName);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                /**==========================Contact Details including Primary and Secondary name ==================================*/
                if (contactTypeName.equalsIgnoreCase("Primary")) {
                    objURVO.setPrimaryContactTypeName(contactTypeName);
                    objURVO.setPrimarySuite(suite);
                    objURVO.setPrimaryAddress1(address1);
                    objURVO.setPrimaryAddress2(address2);
                    objURVO.setPrimaryCity(city);
                    objURVO.setPrimaryState(state);
                    objURVO.setPrimaryCountry(country);
                    objURVO.setPrmaryZip(zip);
                    objURVO.setPrimaryPhoneNo(phoneNo);
                    objURVO.setPromaryMobileNo(mobileNo);
                    objURVO.setPrimaryFaxNo(faxNo);
                }else if (contactTypeName.equalsIgnoreCase("Secondary")){
                    objURVO.setSecondaryContactTypeName(contactTypeName);
                    objURVO.setSecondarySuite(suite);
                    objURVO.setSecondaryAddress1(address1);
                    objURVO.setSecondaryAddress2(address2);
                    objURVO.setSecondaryCity(city);
                    objURVO.setSecondaryState(state);
                    objURVO.setSecondaryCountry(country);
                    objURVO.setSecondaryZip(zip);
                    objURVO.setSecondaryPhoneNo(phoneNo);
                    objURVO.setSecondaryMobileNo(mobileNo);
                    objURVO.setSecondaryFaxNo(faxNo);
                }
                String contactDetail [] =  {contactTypeName, suite, address1, address2, city, state, country, zip, phoneNo, mobileNo, faxNo }; 
                vObj.add(contactDetail);
                }
            
            releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //prepStmt.close();
                //rs.close();
                //rs1.close();
                releaseConnection();
            }

        return objURVO;
    }
    
   /*============================Display member & contact details based on user Type Name, and Status Name ===================*/
    public Vector selectMemberList(String userTypeId, int rCnt)throws SQLException {
        Debug.print("Inside the select member list method");
        vObj = new Vector();
        CallableStatement cstmt = null;
        try {
                makeConnection();
               /* String str = "SELECT user_type_id FROM "+DBHelper.USEA_MMS_TYPEMASTER+ " WHERE user_type_name = ? ";
                prepStmt = con.prepareStatement(str);
                prepStmt.setString(1, userTypeName);
                rs1 = prepStmt.executeQuery();
                if (rs1.next()){
                    this.UserTypeID = rs1.getString(1);
                    System.out.println(" UserTypeID   :  "+UserTypeID);
                }
               
                selectStatement = "SELECT user_id, user_type_id, prefix,first_name,middle_name,last_name,sufix,register_date,dob,gender,email_id,login_name "+
                        " FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE user_type_id = ? AND active_status = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userTypeId);
                prepStmt.setBoolean(2, true);
                * */
                String command = "{call sp_get_userlist(?,?,?)}";
                cstmt = con.prepareCall(command);
                cstmt.setInt(1,rCnt);
                cstmt.setString(2, userTypeId);
                cstmt.setBoolean(3, true);
                
                rs = cstmt.executeQuery();
                //System.out.println("Inside the selectUserRegistrationForm email Id : "+email);
                while (rs.next()) {
                    this.userId = rs.getString(1);
                    this.UserTypeID = rs.getString(2);
                    this.prefix = rs.getString(3);
                    this.firstName = rs.getString(4);
                    this.middleName = rs.getString(5);
                    this.lastName = rs.getString(6);
                    this.sufix = rs.getString(7);
                    this.registerDate = rs.getString(8);
                    this.dob = rs.getString(9);
                    this.gender = rs.getString(10);
                    this.emailId = rs.getString(11);
                    this.login_Name = rs.getString(12);
                    String request_Status=String.valueOf(rs.getBoolean(13));
                    //For Debug Starts
                    String active_status = rs.getString(14);
                    //For Debug Ends
                    /*====================Adding User Details from User Master===== Vector One=============================================*/
                    //For Debug Starts
                    //String userDetail [] = {prefix,firstName,middleName,lastName,registerDate,emailId,userId,UserTypeID,sufix,dob,gender,login_Name,request_Status};
                    String userDetail [] = {prefix,firstName,middleName,lastName,registerDate,emailId,userId,UserTypeID,sufix,dob,gender,login_Name,request_Status,active_status};
                    //For Debug Ends
                    vObj.add(userDetail);
                    //Debug.print("Inside the select member list method  contain in userDetail :  "+userDetail);
                }
            //prepStmt.close();
           // releaseConnection();
        }catch (Exception e){
            Debug.print("Exception in selectMemberList:" + e.getMessage());
           // e.printStackTrace();
        }finally {
            if(rs!=null) rs.close();
            if(cstmt!=null) cstmt.close();
            releaseConnection();
        }
        return vObj;
    }
    
    /*============================Display member & contact details based on user Type Name, and Status Name ===================*/
    public int userRowCount(String userTypeId)throws RemoteException {
        Debug.print("Inside the userRowCount method");
        int rCnt = 0;
        try {
                makeConnection();
                selectStatement = "SELECT count(*) "+
                        " FROM "+DBHelper.USEA_MMS_USERMASTER+" WHERE user_type_id = ? AND active_status = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userTypeId);
                prepStmt.setBoolean(2, true);
                rs = prepStmt.executeQuery();
                //System.out.println("Inside the selectUserRegistrationForm email Id : "+email);
                if (rs.next()) {
                    rCnt = rs.getInt(1);
                }
                if(rs!=null) rs.close();
                if(prepStmt!=null) prepStmt.close();
        }catch (SQLException sql){
            Debug.print("SQLException in userRowCount:" + sql.getMessage());
        }catch (Exception e){
            Debug.print("Exception in userRowCount:" + e.getMessage());
        }
       finally {
            releaseConnection();
        }
        Debug.print("User RowCount:" + rCnt);
        return rCnt;
    }
    
    public String getHumanUserTypeId() throws RemoteException,SQLException {
         String typeId="";
         try {
             makeConnection();
             String statement = "SELECT user_type_id FROM "+DBHelper.USEA_MMS_TYPEMASTER+" where user_type_name = ?";
             prepStmt = con.prepareStatement(statement);
             prepStmt.setString(1, "Human");
             rs = prepStmt.executeQuery();
             
             while(rs.next()){
                typeId = rs.getString(1);                
                
             }
             
             prepStmt.close();
             releaseConnection();
             
         }catch (Exception e){
             releaseConnection();
             Debug.print("Error while listing in selectHorseMember : "+e.getMessage());
         }
         finally
         {
            releaseConnection();
         }
         
         Debug.print(" getHumanUserTypeId :"+typeId);
         return typeId;
     }
    
    public HLCUserRegVO selectUserRegistrationForm1(String userId)throws RemoteException {
        vObj = new Vector();
        HLCUserRegVO objURVO = new HLCUserRegVO();
        makeConnection();
        try {
            selectStatement = "SELECT A.user_id, A.user_code, A.contact_type_id, A.prefix,A.first_name,A.middle_name,A.last_name,A.sufix,A.dob,A.gender,A.email_id, A.password, A.secret_question, "+
                    "A.secret_answer,A.login_name,A.non_usea_mailing_status,A.non_usea_email_status,B.contact_type_name FROM "
                    +DBHelper.USEA_MMS_USERMASTER+" A, "+DBHelper.USEA_CONTACT_TYPEMASTER+
                    " B WHERE A.contact_type_id = B.contact_type_id AND A.user_id = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userId.trim());
            rs = prepStmt.executeQuery();
            Debug.print("Query Statement : "+selectStatement);
            System.out.println("Inside the selectUserRegistrationForm Login Name : "+userId);
            if (rs.next()) {
                objURVO.setPreferedCommunication(rs.getString("contact_type_name"));
                objURVO.setPrefix(rs.getString("prefix"));
                objURVO.setFirstName(rs.getString("first_name"));
                objURVO.setMiddleName(rs.getString("middle_name"));
                objURVO.setLastName(rs.getString("last_name"));
                objURVO.setSufix(rs.getString("sufix"));
                objURVO.setDob(rs.getString("dob"));
                objURVO.setGender(rs.getString("gender"));
                objURVO.setEmailId(rs.getString("email_id"));
                objURVO.setPassword(rs.getString("password"));
                objURVO.setSecretQuestion(rs.getString("secret_question"));
                objURVO.setSecretAnswer(rs.getString("secret_answer"));
                objURVO.setUserId(rs.getString("user_id"));
                objURVO.setLoginName(rs.getString("login_name"));
                this.nonUseaEmailStatus = rs.getBoolean("non_usea_mailing_status");
                objURVO.setNonUseaEmailStatus(nonUseaEmailStatus);
                this.nonUseaMailingStatus = rs.getBoolean("non_usea_email_status");
                objURVO.setNonUseaMailingStatus(nonUseaMailingStatus);
                objURVO.setUser_code(rs.getString("user_code"));
            }
            Debug.print(" Member Details in VO...\n"+objURVO);
            
            //prepStmt.close();
            //rs.close();
            //releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            
            try {
                 
                selectStatement = "SELECT A.suite,A.address1,A.address2,A.city,A.state,A.country,A.zip,A.phone_no, A.mobile_no," +
                        "A.fax_no,A.contact_type_id, B.contact_type_name FROM " +DBHelper.USEA_CONTACT_DETAILS+" A, "+
                        DBHelper.USEA_CONTACT_TYPEMASTER+" B WHERE A.contact_type_id = B.contact_type_id AND A.user_id = ?";
                prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, objURVO.getUserId());
                rs = prepStmt.executeQuery();
                Debug.print("Query Statement : "+selectStatement);
                System.out.println("Inside the loadContactDetails User Id : "+objURVO.getUserId());
                while (rs.next()) {
                    this.contactTypeName = rs.getString("contact_type_name");
                    Debug.print("contactTypeName Inside the Loop : "+contactTypeName);
                    if ((contactTypeName.trim()).equalsIgnoreCase("Primary")) {
                        objURVO.setPrimaryContactTypeName(rs.getString("contact_type_name"));
                        objURVO.setPrimarySuite(rs.getString("suite"));
                        objURVO.setPrimaryAddress1(rs.getString("address1"));
                        objURVO.setPrimaryAddress2(rs.getString("address2"));
                        objURVO.setPrimaryCity(rs.getString("city"));
                        objURVO.setPrimaryState(rs.getString("state"));
                        objURVO.setPrimaryCountry(rs.getString("country"));
                        objURVO.setPrmaryZip(rs.getString("zip")); 
                        objURVO.setPrimaryPhoneNo(rs.getString("phone_no"));
                        objURVO.setPromaryMobileNo(rs.getString("mobile_no"));
                        objURVO.setPrimaryFaxNo(rs.getString("fax_no"));
                    }
                    if ((contactTypeName.trim()).equalsIgnoreCase("Secondary")){
                        objURVO.setSecondaryContactTypeName(rs.getString("contact_type_name"));
                        objURVO.setSecondarySuite(rs.getString("suite"));
                        objURVO.setSecondaryAddress1(rs.getString("address1"));
                        objURVO.setSecondaryAddress2(rs.getString("address2"));
                        objURVO.setSecondaryCity(rs.getString("city"));
                        objURVO.setSecondaryState(rs.getString("state"));
                        objURVO.setSecondaryCountry(rs.getString("country"));
                        objURVO.setSecondaryZip(rs.getString("zip"));
                        objURVO.setSecondaryPhoneNo(rs.getString("phone_no"));
                        objURVO.setSecondaryMobileNo(rs.getString("mobile_no"));
                        objURVO.setSecondaryFaxNo(rs.getString("fax_no"));
                    }
                }
                Debug.print(" Member Details & Contact Details in VO...\n"+objURVO);
            
            releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //prepStmt.close();
                //rs.close();
                //rs1.close();
                releaseConnection();
            }

        return objURVO;
    }
    
/**-----------------------DataBase Connection---------------------------------------*/
      private void makeConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/ELMTMSSQLDS");
            con = ds.getConnection();
        } catch (Exception ex) {
            throw new EJBException("Unable to connect to database. " + ex.getMessage());
        }
      }// makeConnection
      private void releaseConnection() {
         try {
            con.close();
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
      }// releaseConnection    
}
