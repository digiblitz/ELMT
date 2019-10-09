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
package com.hlcreg.form;

import com.hlcreg.util.DBHelper;
import com.hlcreg.util.Debug;
import com.hlcreg.util.HLCHorseDescription;
import com.hlcreg.util.HLCHorseMemberDetails;
import com.hlcreg.util.HLCHorseServiceTypeDetails;
import com.hlcreg.util.HLCPaymentDetails;
import java.text.ParseException;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
import java.util.Date;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaEntityBean enterprise bean.
 * Created Aug 19, 2006 11:02:35 AM
 * @author harmohan
 */
public class HLCArabianSeaEntityBean implements EntityBean, HLCArabianSeaEntityLocalBusiness {
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    private EntityContext context;
   
   // String [] myStr;
    //Vector vObj;
    
    private String horseId;
    private String memberId;
    private String horseMemberId;
    private String horseMembershipTypeId;
    private String membershipTypeId;
    private String horseServiceTypeId;
    private String competitionName;
    private String registeredName;
    private String baRegisteredName;
    private String baPastName;
    private String riderMemberId;
    private String prevRiderMemberId;
    private String addRiderMemberId;
    private String ownerId;
    private String ownerEmail;
    private String loginName;
    private String prevOwnerName;
    private String addOwnerName;
    private boolean activeStatus;
    private String statusId;
    private Date addDate;
    private Date expiryDate;
    private String userId;
    private String UserTypeID;
   
    
    
    /*-----------------------Horse Description-----------------------------*/
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
    private Date importDate;
    private String foreignGrade;
    private double foreignPoints;
    private String assignedGrade;
    private double assignedPoints;
    private String breed2;
    private String sireBreed2;
    private String damBreed2;
    private String notes;
    private String splNotes;
    
    /*-----------------------Horse Description-----------------------------*/
    
    private HLCHorseMemberDetails objHorseMember;
    private HLCHorseDescription objHorseDesc;
    private HLCPaymentDetails objPayment;
    private HLCHorseServiceTypeDetails objHorseService;
    
    public HLCHorseMemberDetails getHorseMemberDetails(){
        Debug.print("ArabianSeaEntityBean getHorseMemberDetails");        
        return new HLCHorseMemberDetails(ownerEmail,userId,horseMemberId, horseMembershipTypeId, horseServiceTypeId,  
            competitionName, registeredName, baRegisteredName, baPastName, riderMemberId, 
            prevRiderMemberId, addRiderMemberId, ownerId, prevOwnerName, addOwnerName, expiryDate, loginName);
    }
    public HLCHorseDescription getHorseDescription(){
        Debug.print("ArabianSeaEntityBean getHorseDescription");        
        return  objHorseDesc;
    }
    public void setHorseDescription() {
        this.objHorseDesc = objHorseDesc;
    }
    public HLCPaymentDetails getPaymentDetails(){
        Debug.print("ArabianSeaEntityBean getPaymentDetails");        
        return  objPayment;
    }
    public void setPaymentDetails(HLCPaymentDetails objPayDet){
        Debug.print("ArabianSeaEntityBean setPaymentDetails");        
        this.objPayment = objPayment;
    }
    
    
    public void setHorseMemberId(String horseMemberId) {this.horseMemberId = horseMemberId; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setHorseMembershipTypeId(String horseMembershipTypeId) { this.horseMembershipTypeId = horseMembershipTypeId; }
    public void setHorseServiceTypeId(String horseServiceTypeId) { this.horseServiceTypeId = horseServiceTypeId; }
    public void setCompetitionName(String competitionName) { this.competitionName = competitionName; }
    public void setRegisteredName(String registeredName) { this.registeredName = registeredName; }
    public void setBaRegisteredName(String baRegisteredName) { this.baRegisteredName = baRegisteredName; }
    public void setBaPastName(String baPastName) { this.baPastName = baPastName; }
    public void setRiderMemberId(String riderMemberId) { this.riderMemberId = riderMemberId; }
    public void setPrevRiderMemberId(String prevRiderMemberId) { this.prevRiderMemberId = prevRiderMemberId; }
     public void setAddRiderMemberId(String addRiderMemberId) { this.addRiderMemberId = addRiderMemberId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public void setOwnerEmail(String ownerEmail){this.ownerEmail = ownerEmail; }
    public void setLoginName(String loginName){ this.loginName = loginName;}
    public void setPrevOwnerName(String prevOwnerName) { this.prevOwnerName = prevOwnerName; }
    public void setAddOwnerName(String addOwnerName) { this.addOwnerName = addOwnerName; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
   
    /*------------------------------Setter Method for Horse Description--------------------------------------*/
    
        public void setColor(String color) { this.color = color; }
        public void setGender(String gender) { this.gender = gender; }
        public void setHeight(String height) {this.height = height; }
        public void setYearFoaled(String yearFoaled) { 
           this.yearFoaled = yearFoaled;
        }
        public void setBreed(String breed) {
           this.breed = breed;
        }
        public void setCountry(String country) {
           this.country = country;
        }
        public void setSire(String sire) {
           this.sire = sire;
        }
        public void setSireBreed(String sireBreed) {
           this.sireBreed = sireBreed;
        }
        public void setDam(String dam) {
           this.dam = dam;
        }
        public void setDamBreed(String damBreed) {
           this.damBreed = damBreed;
        }
        public void setImportedFrom(String importedFrom) {
           this.importedFrom = importedFrom;
        }
        public void setImportDate(Date importDate) {
           this.importDate = importDate;
        }
        public void setForeignGrade(String foreignGrade) {
           this.foreignGrade = foreignGrade;
        }
        public void setForeignPoints(double foreignPoints) {
          this.foreignPoints = foreignPoints;
        }
        public void setAssignedGrade(String assignedGrade) {
           this.assignedGrade = assignedGrade;
        }
        public void setAssignedPoints(double assignedPoints) {
           this.assignedPoints = assignedPoints;
        }
        
         public void setBreed2(String breed2) {
            this.breed2 = breed2;
        }
         public void setSireBreed2(String sireBreed2) {
            this.sireBreed2 = sireBreed2;
        }

    public void setDamBreed2(String damBreed2) {
        this.damBreed2 = damBreed2;
    }
    
     public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSplNotes(String splNotes) {
        this.splNotes = splNotes;
    }

        
      
    /*-------------------------------------------------------------------------------------------------------*/
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
        Debug.print("ArabianSeaEntityBean ejbActivate");
        horseId = (String) context.getPrimaryKey();
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbPassivate()
     */
    public void ejbPassivate() {
        Debug.print("ArabianSeaEntityBean ejbPassivate");
        horseId = null;
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbRemove()
     */
    public void ejbRemove() {
        Debug.print("ArabianSeaEntityBean ejbRemove");

        try {
            deleteRow(horseMemberId);
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
            loadMemberDetails();
            System.out.println("Going to loadHorseDescription");
            loadHorseDescription();
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
            //expiryDate = null;
           // storeMembershipType();
            //storeHorseMemberDetails();
            storeHorseDescription();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
    
    // </editor-fold>
    
    public String ejbCreate(HLCPaymentDetails objPayment) throws CreateException {
         
        Debug.print("ArabianSeaEntityBean PaymentDetails ejbCreate"); 
        if(objPayment==null ){
                throw new EJBException("ejbCreate: objPayment  argument is null or empty");
        }
        this.objPayment = objPayment;
        try {
            if (objPayment.getUserId() != null && objPayment.getUserId().trim().length() > 0)
                 insertRowPayment();
        } catch (Exception ex) {
            throw new EJBException("PaymentDetails ejbCreate: " + ex.getMessage());
        }
        return null;
    }
     
    public void ejbPostCreate(HLCPaymentDetails objPayment) {
        Debug.print("ArabianSeaEntityBean PaymentDetails ejbPostCreate"); 
    }
    
    public String ejbCreate(HLCHorseServiceTypeDetails objHorseService) throws CreateException {
         
        Debug.print("ArabianSeaEntityBean HorseServiceTypeDetails ejbCreate"); 
        if(objHorseService==null ){
                throw new EJBException("ejbCreate: objHorseService  argument is null or empty");
        }
        this.horseMemberId = objHorseService.getHorseMemberId();
        this.horseServiceTypeId = objHorseService.getHorseServiceTypeId();
        Debug.print("horseMemberId : "+horseMemberId);
        Debug.print("horseServiceTypeId : "+horseServiceTypeId);
        try {
              makeConnection();
              String str = "insert into "+DBHelper.USEA_HORSESERVICETYPE +" ( horse_member_id, horse_service_type_id)" + "values ( ?, ?)";
              prepStmt = con.prepareStatement(str);
              prepStmt.setString(1, horseMemberId);
              prepStmt.setString(2, horseServiceTypeId);
              prepStmt.executeUpdate();
              prepStmt.close();
              releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("HorseServiceTypeDetails ejbCreate: " + ex.getMessage());
        }finally {
             releaseConnection();
        }
        return null;
    }
     
    public void ejbPostCreate(HLCHorseServiceTypeDetails objHorseService) {
        Debug.print("ArabianSeaEntityBean HorseServiceTypeDetails ejbPostCreate"); 
    }
    
    
     public String ejbCreate(HLCHorseMemberDetails objHorseMem, HLCHorseDescription objHorseDesc,  HLCPaymentDetails objPayment) throws CreateException {
         
        Debug.print("ArabianSeaEntityBean ejbCreate"); 
        if(objHorseMem==null && objHorseDesc==null && objPayment==null){
                throw new EJBException("ejbCreate: objHorseMem or objHorseDesc or objPayment argument is null or empty");
        }
        
        this.horseMemberId = objHorseMem.getHorseMemberId();
        this.userId = objHorseMem.getUserId();
        this.horseMembershipTypeId = objHorseMem.getHorseMembershipTypeId();
        this.horseServiceTypeId = objHorseMem.getHorseServiceTypeId();
        this.competitionName = objHorseMem.getCompetitionName();
        this.registeredName = objHorseMem.getRegisteredName();
        this.baRegisteredName = objHorseMem.getBaRegisteredName();
        this.baPastName = objHorseMem.getBaPastName();
        this.riderMemberId = objHorseMem.getRiderMemberId();
        this.prevRiderMemberId = objHorseMem.getPrevRiderMemberId();
        this.addRiderMemberId = objHorseMem.getAddRiderMemberId();
        this.ownerId = objHorseMem.getOwnerId();
        this.ownerEmail = objHorseMem.getOwnerEmail();
        this.loginName = objHorseMem.getLoginName();
        this.prevOwnerName = objHorseMem.getPrevOwnerName();
        this.addOwnerName = objHorseMem.getAddOwnerName();
       // this.expiryDate = objHorseMem.getExpiryDate();
           
        this.objHorseDesc = objHorseDesc;
        this.objPayment = objPayment;
        
        try {
              insertRowHorseMemberDetails();
              System.out.println("After insertRowHorseMemberDetails ");
              insertRowHorseDescription();
              if (objPayment.getUserId() != null && objPayment.getUserId().trim().length() > 0) {
                System.out.println("After insertRowHorseDescription ");
              insertRowPayment();
              }
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        return null;

    }
     
    public void ejbPostCreate(HLCHorseMemberDetails objHorseMem, HLCHorseDescription objHorseDesc,  HLCPaymentDetails objPayment) {
        Debug.print("ArabianSeaEntityBean ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String horseMemberId) throws FinderException {
        Debug.print("ArabianSeaEntityBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(horseMemberId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }
        if (result) {
             Debug.print("ArabianSeaEntityBean ejbFindByPrimaryKey ID:" + horseMemberId);
            return horseMemberId;
        } else {
            throw new ObjectNotFoundException("Row for id " + horseMemberId + " not found.");
        }
    }
    
      /*********************** Database Routines *************************/
/**
  * Name         :insertRowHorseMemberDetails
  * Description  :This method will insert record into the Horse Member Details table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */  
    private void insertRowHorseMemberDetails() throws SQLException {
        Debug.print("ArabianSeaEntityBean insertRowHorseMemberDetails");
        //String expDate = DBHelper.getExpiryDate();
        //java.sql.Date dt = java.sql.Date.valueOf(expDate);
        
       try {
                try {
                    this.UserTypeID = DBHelper.getMemberId(con, "Horse");
                    this.statusId = DBHelper.getStatusId(con);
                }catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("User Type ID from UserTypeMaster for Horse : "+UserTypeID);
                makeConnection();
                try{
                    String str = "insert into "+DBHelper.USEA_MMS_USERMASTER +" ( user_id, user_type_id ) " + "values (?, ?)";
                    prepStmt = con.prepareStatement(str);
                    prepStmt.setString(1, objPayment.getUserId());
                    prepStmt.setString(2, UserTypeID);
                    prepStmt.executeUpdate();
                    prepStmt = null;
                }
                catch(Exception exp){
                    Debug.print("Exception in HorseMemberInsertion while inserting userMasterDetails:" + exp.getMessage());
                }
                finally{
                    releaseConnection();
                }
                
                    try {
                        this.ownerId = DBHelper.getOwnerId(con,this.loginName);
                    }catch (Exception e){
                        Debug.print("Error Getting member Id : "+e.getMessage());
                    }
            System.out.println("Horse Member ID :  "+horseMemberId);
            String expDate = DBHelper.getExpiryDate();
            java.sql.Date dt = java.sql.Date.valueOf(expDate);
            makeConnection();
            try{
                String str1 = "insert into "+DBHelper.USEA_MMS_MEMBERDETAIL +" ( member_id, user_id, membership_type_id, expiry_date)" + "values (?, ?, ?, ?)";

                prepStmt = con.prepareStatement(str1);
                prepStmt.setString(1, horseMemberId);
                prepStmt.setString(2, userId);
                prepStmt.setString(3, horseMembershipTypeId);
                prepStmt.setDate(4, dt);

                int xx = prepStmt.executeUpdate();
                System.out.println("Insertion status : "+xx);
                 prepStmt = null;
            }
            catch(Exception ex){
                Debug.print("Exception :" + ex);
            }
            finally{
                releaseConnection();
            }

            try{
            String insertStatement = "insert into " + DBHelper.USEA_HORSE_MEMBERDETAILS + " (horse_member_id, " +
                    "competition_name,registered_name,ba_registered_name,ba_past_name,rider_member_id,prev_rider_member_id," +
                    "add_rider_member_id,owner_id,prev_owner_name," +
                    "add_owner_name,expiry_date,status_id)" +
                    " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?) ";

            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, horseMemberId);
            prepStmt.setString(2, competitionName);
            prepStmt.setString(3, registeredName);
            prepStmt.setString(4, baRegisteredName);
            prepStmt.setString(5, baPastName);
            prepStmt.setString(6, riderMemberId);
            prepStmt.setString(7, prevRiderMemberId);
            prepStmt.setString(8, addRiderMemberId);
            prepStmt.setString(9, ownerId);
            prepStmt.setString(10, prevOwnerName);
            prepStmt.setString(11, addOwnerName);
            prepStmt.setDate(12, dt);
            prepStmt.setString(13, statusId);

            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            }
            catch(Exception exInse){
                Debug.print("Exception while inserting HorseMemberDetails:" + exInse.getMessage());
            }
            finally{
                releaseConnection();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
/**
  * Name         :insertRowHorseDescription
  * Description  :This method will insert record into the Horse Description table
  * @ param      :
  * @return      :void
  * @throws      :SQLException
  */  
    private void insertRowHorseDescription() throws SQLException {
        Debug.print("ArabianSeaEntityBean insertRowHorseDescription");
      try {
        makeConnection();

        String insertStatement = "insert into " + DBHelper.USEA_HORSE_DESCRIPTION + " (horse_member_id,color,gender, " +
                "height,year_foaled,breed,country,sire,sire_breed,dam,dam_breed,imported_from," +
                "import_date,foreign_grade, foreign_points, assigned_grade, assigned_points, " +
                " breed2, sire_breed2, dam_breed2, notes, spl_notes)" +
                " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ?, ?, ?, ? , ?, ?, ? , ?) ";
        
        //breed2, sire_breed2, dam_breed2, notes, spl_notes

        PreparedStatement prepStmt = con.prepareStatement(insertStatement);
        prepStmt.setString(1, objHorseDesc.getHorseMemberId());
        prepStmt.setString(2, objHorseDesc.getColor());
        prepStmt.setString(3, objHorseDesc.getGender());
        prepStmt.setString(4, objHorseDesc.getHeight());
        prepStmt.setString(5, objHorseDesc.getYearFoaled());
        prepStmt.setString(6, objHorseDesc.getBreed());
        prepStmt.setString(7, objHorseDesc.getCountry());
        prepStmt.setString(8, objHorseDesc.getSire());
        prepStmt.setString(9, objHorseDesc.getSireBreed());
        prepStmt.setString(10, objHorseDesc.getDam());
        prepStmt.setString(11, objHorseDesc.getDamBreed());
        prepStmt.setString(12, objHorseDesc.getImportedFrom());
        if (objHorseDesc.getImportDate() != null ){
            prepStmt.setDate(13, DBHelper.toSQLDate(objHorseDesc.getImportDate()));
        }else {
            prepStmt.setDate(13, null);
        }
        prepStmt.setString(14, objHorseDesc.getForeignGrade());
        prepStmt.setDouble(15, objHorseDesc.getForeignPoints());
        prepStmt.setString(16, objHorseDesc.getAssignedGrade());
        prepStmt.setDouble(17, objHorseDesc.getAssignedPoints());
        
        //suresh here
        prepStmt.setString(18, objHorseDesc.getBreed2());
        prepStmt.setString(19, objHorseDesc.getSireBreed2());
        prepStmt.setString(20, objHorseDesc.getDamBreed2());
        prepStmt.setString(21, objHorseDesc.getNotes());
        prepStmt.setString(22, objHorseDesc.getSplNotes());
        //===============
        Debug.print("Inside the horse Description Insert method \n "+objHorseDesc);

        prepStmt.executeUpdate();
        
        //releaseConnection();
      } catch (Exception e){
          Debug.print("Error while inserting insertRowHorseDescription : "+e.getMessage());
      }finally {
            prepStmt.close();
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
        Debug.print("ArabianSeaEntityBean insertRowPayment");
         try {
            makeConnection();
  
            String insertStatement = "insert into " + DBHelper.USEA_PAYMENT + " (user_id,cc_name,cc_type, cc_number, cc_exp_month ," +
                    " cc_exp_year, cc_cvvid, bank_name, check_date, check_number, amount, payment_date, payment_status)" +
                      " values ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";
            prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, objPayment.getUserId());
            prepStmt.setString(2, objPayment.getCcName());
            prepStmt.setString(3, objPayment.getCcType());
            prepStmt.setString(4, objPayment.getCcNumber());
            prepStmt.setInt(5, objPayment.getCcExpMonth());
            prepStmt.setInt(6, objPayment.getCcExpYear());
            prepStmt.setInt(7, objPayment.getCcCvvid());
            prepStmt.setString(8, objPayment.getBankName());
            if (objPayment.getCheckDate() != null ){
                prepStmt.setDate(9, DBHelper.toSQLDate(objPayment.getCheckDate()));
            } else {
                prepStmt.setDate(9, null);
            }
            prepStmt.setString(10, objPayment.getCheckNumber());
            prepStmt.setDouble(11, objPayment.getAmount());
            prepStmt.setDate(12, DBHelper.toSQLDate(new Date()));

            prepStmt.setString(13, objPayment.getPaymentStatus());
            Debug.print(" Payment Details are ... : \n"+objPayment);
            int cnt = prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
            Debug.print("Successfully Inserted..:" + cnt);
            }catch (Exception e){
                Debug.print("Error in insertRowPayment :  "+e.getMessage());
            }finally {
                 releaseConnection();
            }
        }
    
    private boolean selectByPrimaryKey(String horseMemberId) throws SQLException {
            Debug.print("ArabianSeaEntityBean selectByPrimaryKey");

            makeConnection();
            String selectStatement = "SELECT member_id from " + DBHelper.USEA_MMS_MEMBERDETAIL + " WHERE member_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            boolean result = rs.next();
            prepStmt.close();
            releaseConnection();
            return true;
        }
/**
  * Name         :loadHorseMemberDetails
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */  
    private void loadMemberDetails() throws SQLException {
        Debug.print("ArabianSeaEntityBean loadHorseMemberDetails");
        
        this.horseMemberId = (String) context.getPrimaryKey();
        makeConnection();
        String selectStatement = "SELECT member_id, membership_type_id, expiry_date FROM " +
                DBHelper.USEA_MMS_MEMBERDETAIL+" WHERE member_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, horseMemberId);
        ResultSet rs = prepStmt.executeQuery();
        
        if (rs.next()) {
        this.memberId = rs.getString(1);
        this.membershipTypeId =  rs.getString(2);
        this.expiryDate =  rs.getDate(3);
        
        
        prepStmt.close();
        releaseConnection();
    } else {
        prepStmt.close();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + memberId + " not found in database.");
    }
        releaseConnection();
    }
/**
  * Name         :loadHorseDescription
  * Description  :This method will Display the content from the databse 
  * @ param      :none
  * @return      :void
  * @throws      :SQLException
  */      
    private void loadHorseDescription() throws SQLException {
        Debug.print("ArabianSeaEntityBean loadHorseDescription");
        
        this.horseMemberId = (String) context.getPrimaryKey();
        makeConnection();
        String selectStatement = "SELECT horse_member_id, color, gender, height, year_foaled, " +
                "breed, country, sire, sire_breed, dam, dam_breed, imported_from, import_date, " +
                "foreign_grade, foreign_points, assigned_grade, assigned_points, " +
                " breed2, sire_breed2, dam_breed2, notes, spl_notes " +
                " FROM " +DBHelper.USEA_HORSE_DESCRIPTION+" WHERE horse_member_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, horseMemberId);
        ResultSet rs = prepStmt.executeQuery();
        
        if (rs.next()) {
        this.horseMemberId = this.memberId = rs.getString(1);
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
        this.importDate = rs.getDate(13);
        this.foreignGrade = rs.getString(14);
        this.foreignPoints = rs.getDouble(15);
        this.assignedGrade = rs.getString(16);
        this.assignedPoints = rs.getDouble(17);
        
        this.breed2 = rs.getString(18);
        this.sireBreed2 = rs.getString(19);
        this.damBreed2 = rs.getString(20);
        this.notes = rs.getString(21);
        this.splNotes = rs.getString(22);

        
        prepStmt.close();
        releaseConnection();
    } else {
        prepStmt.close();
        releaseConnection();
        throw new NoSuchEntityException("Row for id " + memberId + " not found in database.");
    }
        releaseConnection();
    }
    
  /*  private void storeMembershipType() throws SQLException {
        
        String expDate = DBHelper.getExpiryDate();
        
        java.sql.Date dt = java.sql.Date.valueOf(expDate);
        makeConnection(); //member_id, membership_type_id, expiry_date
        String updateStatement =
                "UPDATE " + DBHelper.USEA_MMS_MEMBERDETAIL  + " SET membership_type_id = ?, expiry_date = ? " +
                " WHERE member_id = ?";
        Debug.print("horseMembershipTypeId  : "+horseMembershipTypeId);
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, horseMembershipTypeId);
        prepStmt.setDate(2, dt);
        prepStmt.setString(3, horseMemberId);
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated MEMBERDETAIL . " + rowCount);
        prepStmt.close();
        releaseConnection();
    }*/

/**
  * Name         :storeHorseDescription
  * Description  :This method will UPDATE a ROW into the Database 
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */        
     private void storeHorseDescription() throws SQLException, ParseException {
        Debug.print("ArabianSeaEntityBean storeHorseDescription");
        String expDate = DBHelper.getExpiryDate();
        java.sql.Date dt = java.sql.Date.valueOf(expDate);
        java.sql.Date jsqlD = DBHelper.toSQLDate(importDate);
     
        makeConnection();
        /*=========================Updating member Details=============================*/
        String updateStatement =
                "UPDATE " + DBHelper.USEA_MMS_MEMBERDETAIL  + " SET membership_type_id = ?, expiry_date = ? " +
                " WHERE member_id = ?";
        Debug.print("horseMembershipTypeId  : "+horseMembershipTypeId);
        PreparedStatement prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, horseMembershipTypeId);
        prepStmt.setDate(2, dt);
        prepStmt.setString(3, horseMemberId);
        int rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated MEMBERDETAIL . " + rowCount);
        
        /*=========================Updating Horse Description=============================*/
        updateStatement =
                "update " + DBHelper.USEA_HORSE_DESCRIPTION  + " set  imported_from = ?, import_date = ?, " +
                "foreign_grade = ?, foreign_points = ?, assigned_grade = ?, assigned_points = ? ," +
                " breed2 = ? , sire_breed2 = ?, dam_breed2 = ?, notes = ?, spl_notes = ?" +
                "where horse_member_id = ?";
        
        Debug.print("ArabianSeaEntityBean SECOND TIME storeHorseDescription");
            
        prepStmt = con.prepareStatement(updateStatement);
        prepStmt.setString(1, importedFrom);
        prepStmt.setDate(2, DBHelper.toSQLDate(importDate));
        prepStmt.setString(3, foreignGrade);
        prepStmt.setDouble(4, foreignPoints);
        prepStmt.setString(5, assignedGrade);
        prepStmt.setDouble(6, assignedPoints);
        prepStmt.setString(7, horseMemberId);
        
        prepStmt.setString(8, breed2);
        prepStmt.setString(9, sireBreed2);
        prepStmt.setString(10, damBreed2);
        prepStmt.setString(11, notes);
        prepStmt.setString(12, splNotes);
        
        rowCount = prepStmt.executeUpdate();
        Debug.print("Sucessfully Updated." + rowCount);
        prepStmt.close();
        releaseConnection();
    }
/**
  * Name         :deleteRow
  * Description  :This method will DELETE a ROW from the Databacse connection
  * @ param      :horseMemberId
  * @return      :void
  * @throws      :SQLException
  */     
    private void deleteRow(String horseMemberId) throws SQLException {
         Debug.print("ArabianSeaEntityBean deleteRow");

        makeConnection();

        String deleteStatement = "delete from " + DBHelper.USEA_HORSESERVICETYPE  + "  where horse_member_id = ? ";
        prepStmt = con.prepareStatement(deleteStatement);

        prepStmt.setString(1, horseMemberId);
        int cnt = prepStmt.executeUpdate();
        Debug.print(" Member Detail Delete Status : "+cnt);
        
        deleteStatement = "delete from " + DBHelper.USEA_HORSE_DESCRIPTION  + "  where horse_member_id = ? ";
        prepStmt = con.prepareStatement(deleteStatement);

        prepStmt.setString(1, horseMemberId);
         cnt = prepStmt.executeUpdate();
        Debug.print(" Horse Description Detail Delete Status : "+cnt);
        
        deleteStatement = "delete from " + DBHelper.USEA_HORSE_MEMBERDETAILS  + "  where horse_member_id = ? ";
        prepStmt = con.prepareStatement(deleteStatement);

        prepStmt.setString(1, horseMemberId);
        cnt = prepStmt.executeUpdate();
        Debug.print(" Horse Member Detail Delete Status : "+cnt);
        
        deleteStatement = "delete from " + DBHelper.USEA_MMS_MEMBERDETAIL  + "  where member_id = ? ";
        prepStmt = con.prepareStatement(deleteStatement);

        prepStmt.setString(1, horseMemberId);
        cnt = prepStmt.executeUpdate();
        Debug.print(" Member Detail Delete Status : "+cnt);//USEA_HORSESERVICETYPE
        
        
        
        prepStmt.close();
        releaseConnection();
    }
    
    public Collection ejbFindByGenMemberID() throws ObjectNotFoundException {
        Debug.print("inside the Arabian Sea GenMemberID");
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT max(cast(member_id as int)) from " + DBHelper.USEA_MMS_MEMBERDETAIL; // + " order by member_id ASC ";
            PreparedStatement prepSelect = con.prepareStatement(selectStatement);
            ResultSet rs = prepSelect.executeQuery();
            Debug.print("After the Arabian SeaGenMemberID");

            if (rs.next()) {
                 this.memberId = rs.getString(1);
            }
            System.out.println("member Id : "+memberId);
            long nextId = Long.valueOf(memberId).longValue();
            if(nextId==0){
                nextId = 10000;
            }
            else{
                nextId = nextId+1;
            }
            rs.close();
            prepSelect.close();

            memberId = Long.toString(nextId);
            System.out.println("member Id returned : "+memberId);
            array.add(memberId);

        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }
    
    public Collection ejbFindByGenUserID() throws ObjectNotFoundException {
        Debug.print("inside the Arabian Sea GenMemberID");
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            
       //     System.out.println("User Type ID from UserTypeMaster for Horse : "+UserTypeID);
        String str = "SELECT newid() as pk_key";
        PreparedStatement prepStmt = con.prepareStatement(str);
        rs = prepStmt.executeQuery();
        rs.next();
        String New_ID = rs.getString(1);
        System.out.println("Generated ID = "+New_ID);
            
            array.add(New_ID);

        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return array;
    }
    
    public Collection ejbFindByMemberIdUserID(String horseMemberId) throws ObjectNotFoundException {
        Debug.print("inside the Arabian Sea ejbFindByMemberIdUserID");
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT user_id from " + DBHelper.USEA_MMS_MEMBERDETAIL+" WHERE member_id = ? "; // + " order by member_id ASC ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, horseMemberId);
            ResultSet rs = prepStmt.executeQuery();
            Debug.print("After the Arabian ejbFindByMemberIdUserID");

            if (rs.next()) {
                 this.userId = rs.getString(1);
            }
            array.add(userId);

        } catch (Exception sqe) {
            releaseConnection();
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
