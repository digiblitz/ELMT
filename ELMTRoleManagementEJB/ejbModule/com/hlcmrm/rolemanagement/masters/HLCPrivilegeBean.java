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
package com.hlcmrm.rolemanagement.masters;

import com.hlccommon.exception.HLCMissingPrimaryKeyException;
import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

/**
 * This is the bean class for the PrivilegeBean enterprise bean.
 * Created Sep 1, 2006 10:52:32 AM
 * @author suresh
 */
public class HLCPrivilegeBean implements EntityBean, HLCPrivilegeLocalBusiness {
    private EntityContext context;
    private String privilegeId;
    private String privilegeName;
    private String privilegePath;
    private boolean activeStatus;
    private Connection con;
    
    public void setPrivilegeId(String privilegeId){
        this.privilegeId = privilegeId;
    }
    
    public void setPrivilegeName(String privilegeName){
        this.privilegeName = privilegeName;
    }
    
    public void setPrivilegePath(String privilegePath){
        this.privilegePath = privilegePath;
    }

    public void setActiveStatus(boolean activeStatus){
        this.activeStatus = activeStatus;
    }
    
    public String getPrivilegeId(){
        return privilegeId;
    }
   
    public String getPrivilegeName(){
        return privilegeName;
    }
    
    public String getPrivilegePath(){
        return privilegePath;
    }
    
    public boolean isActiveStatus(){
        return activeStatus;
    }
   
    public String ejbCreate(String privilegeName, String privilegePath) throws CreateException{
        Debug.print("PrivilegeBean ejbCreate");   
        this.privilegeName = privilegeName;
        this.privilegePath = privilegePath;
        try {
            insertRowPrivilege();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + privilegeId);
        return  privilegeId;
    }
    
    
     public void ejbPostCreate(String privilegeName, String privilegePath) throws CreateException{
        Debug.print("PrivilegeBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("PrivilegeBean ejbActivate");
        privilegeId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("PrivilegeBean ejbPassivate");
        privilegeId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("PrivilegeBean ejbRemove");

        try {
            deleteRow(privilegeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("PrivilegeBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("PrivilegeBean ejbLoad");
        try {
            loadPrivilege();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("PrivilegeBean ejbStore");

        try {
            storePrivilege();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String privilegeId) throws FinderException {
        Debug.print("PrivilegeBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(privilegeId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return privilegeId;
        } else {
            throw new ObjectNotFoundException("Row for id " + privilegeId + " not found.");
        }
    }
        
    
       /*********************** Database Routines *************************/

  private boolean selectByPrimaryKey(String privilegeId) throws SQLException {
        Debug.print("PrivilegeBean selectByPrimaryKey:" + privilegeId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT privilege_id from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER  + " WHERE privilege_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, privilegeId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("PrivilegeBean selectByPrimaryKey" + result);
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in selectByPrimaryKey:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in selectByPrimaryKey:" + e.getMessage());
        }
         return result;
    }
  
   public Collection ejbFindByAll() throws FinderException {
        Debug.print("PrivilegeBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select privilege_id from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " order by add_date";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                dimList.addElement(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByAll:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByAll:" + e.getMessage());
        }
        return dimList;
}

    public Collection ejbFindByPrivilegeName(String privilegeName) throws FinderException{
        Debug.print("PrivilegeBean ejbFindByPrivilegeName:" + privilegeName);
        makeConnection();
        ArrayList priList = new ArrayList();
   	try {
            String selectStatement = "select privilege_id from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " where privilege_name = ? order by privilege_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,privilegeName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                priList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("PrivilegeBean in ejbFindByPrivilegeName:" + priList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByPrivilegeName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByprivilegeName:" + e.getMessage());
        }
        return priList;
}
   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("PrivilegeBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as privilegeId";
            Debug.print("PrivilegeBean getNextId:" + selectStatement);
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
            throw new EJBException("SQL Exception in getNextId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in getNextId:" + e.getMessage());
        }        
        return nextId;
    }
    private void insertRowPrivilege() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("PrivilegeBean insertRowPrivilege");
        
        this.privilegeId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER  + "(privilege_id, privilege_name, privilege_path) " +
                 " values ( ? , ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, privilegeId);
            prepStmt.setString(2, privilegeName);
            prepStmt.setString(3, privilegePath);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowPrivilege:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowPrivilege:" + e.getMessage());
        }        
    }

    private void loadPrivilege() throws SQLException {
        Debug.print("PrivilegeBean loadPrivilege");
        privilegeId = (String)context.getPrimaryKey();

        Debug.print("PrivilegeBean loadPrivilege Primary Key:" + privilegeId );
        makeConnection();
        try{
            String selectStatement =
                "select  privilege_name, privilege_path, active_status from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER  + " where privilege_id = ? order by add_date desc";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, privilegeId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.privilegeName = rs.getString(1);
                this.privilegePath = rs.getString(2);
                this.activeStatus = rs.getBoolean(3);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + privilegeId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadPrivilege:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadPrivilege:" + e.getMessage());
        }        
    }
    
    private void storePrivilege() throws SQLException {
        Debug.print("PrivilegeBean storePrivilege");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER  + " set privilege_name = ? , privilege_path = ? , active_status = ? " + 
                    " where privilege_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, privilegeName);
            prepStmt.setString(2, privilegePath);
            prepStmt.setBoolean(3, activeStatus);
            prepStmt.setString(4, privilegeId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storePrivilege:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storePrivilege:" + e.getMessage());
        }        
    }
    
    
    private void deleteRow(String privilegeId) throws SQLException {
         Debug.print("PrivilegeBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER  + "  where privilege_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, privilegeId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in deleteRow:" + e.getMessage());
        }        

    }
        
        
     private void makeConnection() {
            Debug.print("PrivilegeBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
        Debug.print("PrivilegeBean releaseConnection");
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
}
