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
package com.hlcmrm.admin;

import com.hlcmrm.util.HLCContactTypeMaster;
import com.hlcmrm.util.HLCCountryMailPriceMaster;
import com.hlcmrm.util.DBHelper;
import com.hlcmrm.util.Debug;
import com.hlcmrm.util.HLCHorseServiceTypeMaster;
import com.hlcmrm.util.HLCMembershipStatusMaster;
import com.hlcmrm.util.HLCMembershipTypeMaster;
import com.hlcmrm.util.HLCNonUSEAOrgMaster;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaAdminBean enterprise bean.
 * Created Aug 28, 2006 12:05:19 PM
 * @author harmohan
 */
public class HLCArabianSeaAdminBean implements EntityBean, HLCArabianSeaAdminLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
    /*==============MembershipStatusMaster Variable ==========================*/
    public String statusId;
    public String statusName;
    public String description;
    /*==============MembershipTypeMaster Variable ==========================*/
    private String membershipTypeId;
    private String membershipIypeName;
    private String userTypeId;
    private String membershipAmount;
    private Date modifyDate;
    /*==============HorseServiceTypeMaster Variable ==========================*/
    private String horseServiceTypeId;
    private String horseServiceTypeName;
    private String horseServiceTypeAmount;
     /*==============CountryMailPriceMaster Variable ==========================*/
    private String countryMailTypeId;
    private String countryMailTypeName;
    private String countryMailPrice;
     /*==============ContactTypeMaster Variable ==========================*/
    private String contactTypeId;
    private String contactTypeName;
    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    private String contactTypeDescription;
    private String contactType;
    private String status;
    private String flag;
    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
     /*==============NonUSEAOrgMaster Variable ==========================*/
    private String  nonuseaOrgId;
    private String nonuseaOrgName;
    
    /**=========================Object Creation=========================*/
    HLCContactTypeMaster objContactTypeMaster = new HLCContactTypeMaster();
    HLCCountryMailPriceMaster objCountryMail = new HLCCountryMailPriceMaster();
    HLCHorseServiceTypeMaster objHorseService = new HLCHorseServiceTypeMaster();
    HLCMembershipStatusMaster objStatusMaster = new HLCMembershipStatusMaster();
    HLCMembershipTypeMaster objMemTypeMaster = new HLCMembershipTypeMaster();
    HLCNonUSEAOrgMaster objNonUsaOrg = new HLCNonUSEAOrgMaster();
    
        
    /**========================Setter Method============================*/
    public void setContactTypeId(String contactTypeId ) {
        this.contactTypeId = contactTypeId;
	}
    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }
    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    public void setContactTypeDescription(String contactTypeDescription) {
        this.contactTypeDescription = contactTypeDescription;
    }
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    public String getContactTypeId() {
            return contactTypeId;
    }
    public String getContactTypeName() {
            return contactTypeName;
    }
    //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    public String getContactTypeDescription() {
            return contactTypeDescription;
    }
    public String getContactType() {
            return contactType;
    }
    public String getStatus() {
            return status;
    }
    //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
    
    
    public void setCountryMailTypeId(String countryMailTypeId) {
	this.countryMailTypeId = countryMailTypeId;
	}
    public void setCountryMailTypeName(String countryMailTypeName) {
            this.countryMailTypeName = countryMailTypeName;
    }
    public void setCountryMailPrice(String countryMailPrice) {
            this.countryMailPrice = countryMailPrice ;
    }
    public void setHorseServiceTypeId(String horseServiceTypeId) {
            this.horseServiceTypeId  = horseServiceTypeId ;
    }
    public void setHorseServiceTypeName(String horseServiceTypeName) {
            this.horseServiceTypeName = horseServiceTypeName;
    }
    public void setHorseServiceTypeAmount(String horseServiceTypeAmount ) {
             this.horseServiceTypeAmount = horseServiceTypeAmount;
    }
     public void setStatusId(String statusId) {
	this.statusId = statusId ;
    }
    public void setStatusName(String statusName) {
	this.statusName = statusName ;
    }
    public void setDescription(String Description) {
	this.description = description ;
    }
    public  void setMembershipTypeId(String membershipTypeId) {
	this.membershipTypeId = membershipTypeId;
    }
    public  void setMembershipIypeName(String membershipIypeName) {
        this.membershipIypeName = membershipIypeName;
    }
    public  void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }
    public  void setMembershipAmount(String membershipAmount) {
        this.membershipAmount = membershipAmount;
    }
    public  void setModifyDate(Date modifyDate) {
	this.modifyDate = modifyDate;
    }
    public void setNonuseaOrgId(String nonuseaOrgId) {
	this.nonuseaOrgId = nonuseaOrgId;
    }
    public void setNonuseaOrgName(String nonuseaOrgName) {
	this.nonuseaOrgName = nonuseaOrgName;
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
        Debug.print("ArabianSeaAdminBean ejbRemove");

        try {
            makeConnection();
            String deleteStatement = "delete from " + DBHelper.USEA_CONTACT_TYPEMASTER   + "  where contact_type_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, contactTypeId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the CONTACT_TYPEMASTER record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ArabianSeaAdminBean ejbRemove: " + ex.getMessage());
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
        Debug.print("ArabianSeaAdminBean ejbLoad");
        this.contactTypeId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        String selectStatement = " SELECT contact_type_id, contact_type_name, contact_type_desc, contact_type, active_status FROM  "+DBHelper.USEA_CONTACT_TYPEMASTER+" WHERE contact_type_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, contactTypeId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
                this.contactTypeId = rs.getString(1);
                this.contactTypeName = rs.getString(2);
                //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
                this.contactTypeDescription = rs.getString(3);
                this.contactType = rs.getString(4);
                this.status = rs.getString(5);
                //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        } 
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for ArabianSeaAdminBean");
       
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
        try {
            System.out.println("ejbStore for ArabianSeaAdminBean");
            makeConnection();
            String updateStatement =
                    "update " + DBHelper.USEA_CONTACT_TYPEMASTER  + " set contact_type_name = ?, contact_type_desc= ? "+
                    ",contact_type=?, active_status=? where contact_type_id = ?";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, contactTypeName);
            //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
            //prepStmt.setString(2, contactTypeId);
            prepStmt.setString(2, contactTypeDescription);
            prepStmt.setString(3, contactType);
            prepStmt.setString(4, status);
            prepStmt.setString(5, contactTypeId);
            //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
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
    
    
    public String ejbCreate(HLCContactTypeMaster objContactTypeMaster) throws CreateException {
         
        Debug.print("ContactTypeMaster ejbCreate"); 
        if(objContactTypeMaster==null){
                throw new EJBException("ejbCreate: objRefDetails argument is null or empty");
        }
        this.contactTypeId = objContactTypeMaster.getContactTypeId();
        this.contactTypeName = objContactTypeMaster.getContactTypeName();
        //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
         this.contactTypeDescription = objContactTypeMaster.getContactTypeDescription();
         this.contactType = objContactTypeMaster.getContactType();
         this.status = objContactTypeMaster.getStatus();
         //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
        try {
             makeConnection();
             //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
             //String insertStatement = "insert into " + DBHelper.USEA_CONTACT_TYPEMASTER + " (contact_type_name) values ( ? )";
             String insertStatement = "insert into " + DBHelper.USEA_CONTACT_TYPEMASTER + " (contact_type_name,contact_type_desc,contact_type,active_status) values ( ?,?,?,? )";
             //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, contactTypeName);
             //Start:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
             prepStmt.setString(2, contactTypeDescription);
             prepStmt.setString(3, contactType);
             prepStmt.setString(4, status);
             //End:[ContactMgt] For Contact Type Addition, Editing and Deletion changes dated 30-Mar-2011
             int cnt = prepStmt.executeUpdate();
             System.out.println("successfully inserted into ContactTypeMaster  : "+cnt);
             prepStmt.close();
             releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: ContactTypeMaster  --- " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCContactTypeMaster objContactTypeMaster) {
        Debug.print("ContactTypeMaster ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String contactTypeId) throws FinderException {
         boolean result = false;
         try {
            makeConnection();
            String selectStatement = "SELECT contact_type_id FROM " + DBHelper.USEA_CONTACT_TYPEMASTER + " WHERE contact_type_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, contactTypeId);
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.contactTypeId = rs.getString(1);
               // this.contactTypeName = rs.getString(2);
            }
            prepStmt.close();
           // releaseConnection();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
            return contactTypeId;
    }
    
    public Collection ejbFindByContactTypeName() throws ObjectNotFoundException {
        this.contactTypeName =contactTypeName;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT contact_type_id, contact_type_name FROM  " +DBHelper.USEA_CONTACT_TYPEMASTER;
            prepStmt = con.prepareStatement(selectStatement);
            
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));
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
    
        //Debugs Starts to check eixsting name for edit page by Lakshmi 26-05-11
    //public Collection ejbFindByContactTypeID(String contactTypeName)throws FinderException {
    public Collection ejbFindByContactTypeID(String contactTypeId,String contactTypeName)throws FinderException {
        this.contactTypeId =contactTypeId;
        //Debugs Ends to check eixsting name for edit page by Lakshmi 26-05-11
        this.contactTypeName =contactTypeName;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            //Debugs Starts to check eixsting name for edit page by Lakshmi 26-05-11
            Debug.print("contact type id" + contactTypeId);
            if(contactTypeId==null){
                Debug.print("if statement");
             //Debugs Ends to check eixsting name for edit page by Lakshmi 26-05-11
            String selectStatement = "SELECT contact_type_id FROM  " +DBHelper.USEA_CONTACT_TYPEMASTER+"  WHERE contact_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, contactTypeName);
            //Debugs Starts to check eixsting name for edit page by Lakshmi 26-05-11
            }
         else{
                Debug.print("else statement");
            String selectStatement = "SELECT contact_type_id FROM  " +DBHelper.USEA_CONTACT_TYPEMASTER+"  WHERE contact_type_id!= ? and contact_type_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, contactTypeId);
            prepStmt.setString(2, contactTypeName);
           }
            rs = prepStmt.executeQuery();
            while (rs.next()) {
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

