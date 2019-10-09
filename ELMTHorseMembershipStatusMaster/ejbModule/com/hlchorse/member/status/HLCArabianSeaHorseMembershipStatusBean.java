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
package com.hlchorse.member.status;

import com.hlchorse.member.util.DBHelper;
import com.hlchorse.member.util.Debug;
import com.hlchorse.member.util.HLCMembershipStatusMaster;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaHorseMembershipStatusBean enterprise bean.
 * Created Aug 29, 2006 6:44:52 PM
 * @author harmohan
 */
public class HLCArabianSeaHorseMembershipStatusBean implements EntityBean, HLCArabianSeaHorseMembershipStatusLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
    /*==============MembershipStatusMaster Variable ==========================*/
    public String statusId;
    public String statusName;
    public String description;
    /**=========================Object Creation=========================*/
    HLCMembershipStatusMaster objStatusMaster = new HLCMembershipStatusMaster();
    
    /**==================setter method=====================================*/
     public void setStatusId(String statusId) {
	this.statusId = statusId ;
    }
    public void setStatusName(String statusName) {
	this.statusName = statusName ;
    }
    public void setDescription(String Description) {
	this.description = description ;
    }
    
     public String getStatusId() {
    	return statusId;
    }

    public String getStatusName() {
    	return statusName;
    }
    public String getDescription() {
	    return description;
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
         Debug.print("ArabianSeaMemberShipStatusBean ejbRemove");

        try {
            makeConnection();
            String deleteStatement = "delete from " + DBHelper.USEA_MEMBER_STATUS_MASTER   + "  where status_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, statusId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the USEA_MEMBER_STATUS_MASTER record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ArabianSeaMemberShipStatusBean ejbRemove: " + ex.getMessage());
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
         Debug.print("ArabianSeaMemberShipStatusBean ejbLoad");
        this.statusId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        String selectStatement = "SELECT * FROM " +DBHelper.USEA_MEMBER_STATUS_MASTER+" WHERE status_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, statusId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
                this.statusId = rs.getString(1);
                this.statusName = rs.getString(2);
                this.description = rs.getString(3);
        } 
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for ArabianSeaMemberShipStatusBean");
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
       try {
            System.out.println("ejbStore for ArabianSeaMemberShipStatusBean");
            makeConnection();
            String updateStatement =
                    "update " + DBHelper.USEA_MEMBER_STATUS_MASTER  + " set status_name = ?,description = ?   where status_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, statusName);
            prepStmt.setString(2, description);
             prepStmt.setString(3, statusId);
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
    
    public String ejbCreate(HLCMembershipStatusMaster objStatusMaster) throws CreateException {
         
        Debug.print("MembershipStatusMaster ejbCreate"); 
        if(objStatusMaster==null){
                throw new EJBException("ejbCreate: objStatusMaster argument is null or empty");
        }
        this.statusName = objStatusMaster.getStatusName();
        this.description = objStatusMaster.getDescription();
        try {
             makeConnection();
             String insertStatement = "insert into " + DBHelper.USEA_MEMBER_STATUS_MASTER + " ( " +
                "status_name, description ) values ( ?, ? )"; 
             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, statusName);
             prepStmt.setString(2, description);
             int cnt = prepStmt.executeUpdate();
             System.out.println(" description : "+description);
             System.out.println("successfully Inserted into MembershipStatusMaster  : "+cnt);
             prepStmt.close();
             releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: MembershipStatusMaster  --- " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCMembershipStatusMaster objStatusMaster) {
        Debug.print("MembershipStatusMaster ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String statusId) throws FinderException {
         boolean result = false;
         try {
            makeConnection();
            String selectStatement = "SELECT status_id FROM " + DBHelper.USEA_MEMBER_STATUS_MASTER + " WHERE status_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, statusId);
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.statusId = rs.getString(1);
            }
            prepStmt.close();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
            return statusId;
    }
    
    public Collection ejbFindByMembershipStatusName(String statusName) throws ObjectNotFoundException {
        this.statusId =statusId;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT status_id FROM  " +DBHelper.USEA_MEMBER_STATUS_MASTER +
                    "  WHERE status_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, statusName);
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
    
    public Collection ejbFindByMembershipStatusDetail() throws FinderException {
        this.statusName =statusName; boolean bol = false;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT status_id,status_name FROM  " +DBHelper.USEA_MEMBER_STATUS_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
            Debug.print(" Inside the ejbFindByMembershipStatusDetail");
            rs = prepStmt.executeQuery();
            while (bol = rs.next()) {
                array.add(rs.getString(1));
                array.add(rs.getString(2));
             } 
             prepStmt.close();
             Debug.print(" Inside the ejbFindByMembershipStatusDetail"+bol);
        } catch (SQLException sqe) {
            //throw new EJBException(sqe);
            Debug.print("  "+sqe.getMessage());
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        Debug.print(" Inside the ejbFindByMembershipStatusDetail" + array);
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
