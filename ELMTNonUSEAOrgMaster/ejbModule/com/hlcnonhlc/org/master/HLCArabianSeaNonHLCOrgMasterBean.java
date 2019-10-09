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
package com.hlcnonhlc.org.master;

import com.hlcnonhlc.org.util.DBHelper;
import com.hlcnonhlc.org.util.Debug;
import com.hlcnonhlc.org.util.HLCNonHLCOrgMaster;
import javax.ejb.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/**
 * This is the bean class for the ArabianSeaNonUSEAOrgMasterBean enterprise bean.
 * Created Aug 30, 2006 10:17:20 AM
 * @author harmohan
 */
public class HLCArabianSeaNonHLCOrgMasterBean implements EntityBean, HLCArabianSeaNonHLCOrgMasterLocalBusiness {
    private EntityContext context;
    private static final String dbName = "java:/ELMTMSSQLDS";
    private Connection con = null;
    private PreparedStatement prepStmt = null;
    private ResultSet rs = null;
    
    /*==============NonUSEAOrgMaster Variable ==========================*/
    private String  nonuseaOrgId;
    private String nonuseaOrgName;
    
    /**=======================Object Creation============================*/
    HLCNonHLCOrgMaster objNonUsaOrg = new HLCNonHLCOrgMaster();
    
    /*========================Setter Method==============================*/
     public void setNonuseaOrgId(String nonuseaOrgId) {
	this.nonuseaOrgId = nonuseaOrgId;
    }
    public void setNonuseaOrgName(String nonuseaOrgName) {
	this.nonuseaOrgName = nonuseaOrgName;
    }
    public  String  getNonuseaOrgId() {
            return nonuseaOrgId;
    }
    public  String  getNonuseaOrgName() {
            return nonuseaOrgName;
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
         Debug.print("ArabianSeaNonUSEAMasterBean ejbRemove");

        try {
            makeConnection();
            String deleteStatement = "delete from " + DBHelper.NON_USEA_ORG_MASTER   + "  where other_org_id= ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);
            prepStmt.setString(1, nonuseaOrgId);
            int cnt = prepStmt.executeUpdate();
            System.out.println("Successfully Delete the NON_USEA_ORG_MASTER record : "+cnt);
            prepStmt.close();
            releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ArabianSeaNonUSEAMasterBean ejbRemove: " + ex.getMessage());
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
        Debug.print("ArabianSeaNonUSEAMasterBean ejbLoad");
        this.nonuseaOrgId = (String) context.getPrimaryKey();
        try {
        makeConnection();
        String selectStatement = "SELECT other_org_id, other_org_name  FROM " +DBHelper.NON_USEA_ORG_MASTER+" WHERE other_org_id = ?";
        PreparedStatement prepStmt = con.prepareStatement(selectStatement);
        prepStmt.setString(1, nonuseaOrgId);
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next()) {
                this.nonuseaOrgId = rs.getString(1);
                this.nonuseaOrgName = rs.getString(2);
        } 
        prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
        } finally {
            releaseConnection();
        }
       System.out.println("ejbLoad for ArabianSeaNonUSEAMasterBean");
    }
    
    /**
     * @see javax.ejb.EntityBean#ejbStore()
     */
    public void ejbStore() {
       try {
            System.out.println("ejbStore for ArabianSeaAdminBean");
            Debug.print("nonuseaOrgId in ejbStore : "+nonuseaOrgId);
            makeConnection();
            String updateStatement =
                    "update " + DBHelper.NON_USEA_ORG_MASTER  + " set other_org_name = ?  where other_org_id = ?";
              
            PreparedStatement prepStmt = con.prepareStatement(updateStatement);
            prepStmt.setString(1, nonuseaOrgName);
            prepStmt.setString(2, nonuseaOrgId);
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
    
    public String ejbCreate(HLCNonHLCOrgMaster objNonUsaOrg) throws CreateException {
         
        Debug.print("NonUSEAOrgMaster ejbCreate"); 
        if(objNonUsaOrg==null){
                throw new EJBException("ejbCreate: objNonUsaOrg argument is null or empty");
        }
        this.nonuseaOrgId = objNonUsaOrg.getNonuseaOrgId();
        this.nonuseaOrgName = objNonUsaOrg.getNonuseaOrgName();
     
        try {
             makeConnection();
             String insertStatement = "insert into " + DBHelper.NON_USEA_ORG_MASTER + " ( " +
                "other_org_name) values ( ? )"; 
             prepStmt = con.prepareStatement(insertStatement);
             prepStmt.setString(1, nonuseaOrgName);
            // prepStmt.setString(2, contactTypeName);
             int cnt = prepStmt.executeUpdate();
             System.out.println("successfully inserted into NonUSEAOrgMaster  : "+cnt);
             prepStmt.close();
             releaseConnection();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: NonUSEAOrgMaster  --- " + ex.getMessage());
        }
        return null;
    }
    
    public void ejbPostCreate(HLCNonHLCOrgMaster objNonUsaOrg) {
        Debug.print("NonUSEAOrgMaster ejbPostCreate"); 
    }
    
    public String ejbFindByPrimaryKey(String nonuseaOrgId) throws FinderException {
         boolean result = false;
         try {
            makeConnection();
            String selectStatement = "SELECT other_org_id FROM " + DBHelper.NON_USEA_ORG_MASTER + " WHERE other_org_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, nonuseaOrgId);
            ResultSet rs = prepStmt.executeQuery();
            if( rs.next()) {
                this.nonuseaOrgId = rs.getString(1);
                //this.contactTypeName = rs.getString(2);
            }
            prepStmt.close();
           // releaseConnection();
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             releaseConnection();
         }
            return nonuseaOrgId;
    }
    
    public Collection ejbFindByNonUSEAOrgName(String nonuseaOrgName) throws ObjectNotFoundException {
        this.nonuseaOrgName =nonuseaOrgName;
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT other_org_id FROM  " +DBHelper.NON_USEA_ORG_MASTER +
                    "  WHERE other_org_name = ?";
            prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, nonuseaOrgName);
             
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
    
    public Collection ejbFindByNonUSEAOrgDetails() throws ObjectNotFoundException {
       Vector V = new Vector();
        ArrayList array = new ArrayList();
        try {
            makeConnection();
            String selectStatement = "SELECT other_org_id, other_org_name FROM  " +DBHelper.NON_USEA_ORG_MASTER;
            prepStmt = con.prepareStatement(selectStatement);
             
            rs = prepStmt.executeQuery();
            if (rs.next()) {
                V.addElement(rs.getString(1));
                V.addElement(rs.getString(2));
             } 
             prepStmt.close();
        } catch (SQLException sqe) {
            throw new EJBException(sqe);
            //sqe.printStackTrace();
        } finally {
            releaseConnection();
        }
        return V;
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
