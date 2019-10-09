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
 * This is the bean class for the RoleMasterBean enterprise bean.
 * Created Sep 1, 2006 12:01:03 PM
 * @author suresh
 */
public class HLCRoleMasterBean implements EntityBean, HLCRoleMasterLocalBusiness {
    private EntityContext context;
    private String roleId;
    private String roleName;
    private String rolePath;
    private boolean activeStatus;
    private Connection con;
    
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    
    public void setRolePath(String rolePath){
        this.rolePath = rolePath;
    }

    public void setActiveStatus(boolean activeStatus){
        this.activeStatus = activeStatus;
    }
    
    public String getRoleId(){
        return roleId;
    }
   
    public String getRoleName(){
        return roleName;
    }
    
    public String getRolePath(){
        return rolePath;
    }
    
    public boolean isActiveStatus(){
        return activeStatus;
    }
   
    public String ejbCreate(String roleName, String rolePath) throws CreateException{
        Debug.print("RoleMasterBean ejbCreate");   
        this.roleName = roleName;
        this.rolePath = rolePath;
        try {
            insertRowRoleDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + roleId);
        return  roleId;
    }
    
    
     public void ejbPostCreate(String roleName, String rolePath) throws CreateException{
        Debug.print("RoleMasterBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("RoleMasterBean ejbActivate");
        roleId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("RoleMasterBean ejbPassivate");
        roleId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("RoleMasterBean ejbRemove");

        try {
            deleteRow(roleId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("RoleMasterBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("RoleMasterBean ejbLoad");
        try {
            loadRoleDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("RoleMasterBean ejbStore");

        try {
            storeRoleDetails();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String roleId) throws FinderException {
        Debug.print("RoleMasterBean ejbFindByPrimaryKey");

        boolean result;
        try {
            result = selectByPrimaryKey(roleId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return roleId;
        } else {
            throw new ObjectNotFoundException("Row for id " + roleId + " not found.");
        }
    }
        
    
       /*********************** Database Routines *************************/

  private boolean selectByPrimaryKey(String roleId) throws SQLException {
        Debug.print("RoleMasterBean selectByPrimaryKey:" + roleId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT role_id from " + DBHelper.USEA_ROLE_ROLE_MASTER  + " WHERE role_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, roleId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("RoleMasterBean selectByPrimaryKey" + result);
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
        Debug.print("RoleMasterBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select role_id from " + DBHelper.USEA_ROLE_ROLE_MASTER + " order by add_date";
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

    public Collection ejbFindByRoleName(String roleName) throws FinderException{
        Debug.print("RoleMasterBean ejbFindByRoleName:" + roleName);
        makeConnection();
        ArrayList priList = new ArrayList();
   	try {
            String selectStatement = "select role_id from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_name = ? order by role_name" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleName);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                priList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("RoleMasterBean in ejbFindByRoleName:" + priList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByRoleName:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByRoleName:" + e.getMessage());
        }
        return priList;
}
   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("RoleMasterBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as roleId";
            Debug.print("RoleMasterBean getNextId:" + selectStatement);
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
    private void insertRowRoleDetails() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("RoleMasterBean insertRowPrivilege");
        
        this.roleId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ROLE_ROLE_MASTER  + "(role_id, role_name, role_path) " +
                 " values ( ? , ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, roleId);
            prepStmt.setString(2, roleName);
            prepStmt.setString(3, rolePath);
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

    private void loadRoleDetails() throws SQLException {
        Debug.print("RoleMasterBean loadRoleDetails");
        roleId = (String)context.getPrimaryKey();

        Debug.print("RoleMasterBean loadRoleDetails Primary Key:" + roleId );
        makeConnection();
        try{
            String selectStatement =
                "select  role_name, role_path, active_status from " + DBHelper.USEA_ROLE_ROLE_MASTER  + " where role_id = ? order by add_date desc";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, roleId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.roleName = rs.getString(1);
                this.rolePath = rs.getString(2);
                this.activeStatus = rs.getBoolean(3);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + roleId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadRoleDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadRoleDetails:" + e.getMessage());
        }        
    }
    
    private void storeRoleDetails() throws SQLException {
        Debug.print("RoleMasterBean storeRoleDetails");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ROLE_ROLE_MASTER  + " set role_name = ? , role_path = ? , active_status = ? " + 
                    " where role_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, roleName);
            prepStmt.setString(2, rolePath);
            prepStmt.setBoolean(3, activeStatus);
            prepStmt.setString(4, roleId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeRoleDetails:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storePrivilege:" + e.getMessage());
        }        
    }
    
    
    private void deleteRow(String roleId) throws SQLException {
         Debug.print("RoleMasterBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ROLE_ROLE_MASTER  + "  where role_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, roleId);
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
            Debug.print("RoleMasterBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
        Debug.print("RoleMasterBean releaseConnection");
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
}
