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
package com.hlcmrm.maps;

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
 * This is the bean class for the MapPrevilegeBean enterprise bean.
 * Created Sep 1, 2006 4:18:18 PM
 * @author suresh
 */
public class HLCMapPrevilegeBean implements EntityBean, HLCMapPrevilegeLocalBusiness {
    private EntityContext context;
    private String mapPrivilegeId;
    private String privilegeId;
    private String roleId;
    private Connection con;
    
    public void setMapPrivilegeId(String mapPrivilegeId){
        this.mapPrivilegeId = mapPrivilegeId;
    }
    
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    
    public void setPrivilegeId(String privilegeId){
        this.privilegeId = privilegeId;
    }
   
    public String getMapPrivilegeId(){
        return mapPrivilegeId;
    }
   
    public String getRoleId(){
        return roleId;
    }
    
    public String getPrivilegeId(){
        return privilegeId;
    }
   
   
    public String ejbCreate(String roleId, String privilegeId) throws CreateException{
        Debug.print("MapPrevilegeBean ejbCreate");   
        this.roleId = roleId;
        this.privilegeId = privilegeId;
        try {
            insertRowMapPrivilege();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + mapPrivilegeId);
        return  mapPrivilegeId;
    }
    
    
     public void ejbPostCreate(String roleId, String privilegeId) throws CreateException{
        Debug.print("MapPrevilegeBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("MapPrevilegeBean ejbActivate");
        mapPrivilegeId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("MapPrevilegeBean ejbPassivate");
        mapPrivilegeId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("MapPrevilegeBean ejbRemove");

        try {
            deleteRow(mapPrivilegeId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("MapPrevilegeBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("MapPrevilegeBean ejbLoad");
        try {
            loadMapPrivilege();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("MapPrevilegeBean ejbStore");

        try {
            storeMapPrivilege();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String mapPrivilegeId) throws FinderException {
        Debug.print("MapPrevilegeBean ejbFindByPrimaryKey");

        boolean result;

        try {
            result = selectByPrimaryKey(mapPrivilegeId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return mapPrivilegeId;
        } else {
            throw new ObjectNotFoundException("Row for id " + mapPrivilegeId + " not found.");
        }
    }
        
    
       /*********************** Database Routines *************************/

  private boolean selectByPrimaryKey(String mapPrivilegeId) throws SQLException {
        Debug.print("MapPrevilegeBean selectByPrimaryKey:" + mapPrivilegeId);
        boolean result = false;          
        makeConnection();
        try {
            String selectStatement = "SELECT map_privilege_id from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE  + " WHERE map_privilege_id = ?";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, mapPrivilegeId);

            ResultSet rs = prepStmt.executeQuery();
            result = rs.next();
            prepStmt.close();
            releaseConnection();
            Debug.print("MapPrevilegeBean selectByPrimaryKey" + result);
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
        Debug.print("MapPrevilegeBean ejbFindAll");
        Vector dimList = new Vector();
        makeConnection();
   	try {
            String selectStatement = "select map_privilege_id from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE ;
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

    public Collection ejbFindByRoleId(String roleId) throws FinderException{
        Debug.print("MapPrevilegeBean ejbFindByRoleId:" + roleId);
        makeConnection();
        ArrayList roList = new ArrayList();
   	try {
            String selectStatement = "select map_privilege_id from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE + " where role_id = ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                roList.add(rs.getString(1));
                Debug.print("MapPrevilegeBean in RoleId:" + rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("MapPrevilegeBean in ejbFindByRoleId:" + roList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByRoleId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByRoleId:" + e.getMessage());
        }
        return roList;
}
    
        public Collection ejbFindByPrivilegeId(String privilegeId) throws FinderException{
        Debug.print("MapPrevilegeBean ejbFindByPrivilegeId:" + privilegeId);
        makeConnection();
        ArrayList priList = new ArrayList();
   	try {
            String selectStatement = "select map_privilege_id from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE + " where privilege_id = ?" ;
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1,privilegeId);
            
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next()){
                priList.add(rs.getString(1));
            }
            rs.close();
            prepStmt.close();
            releaseConnection();
            Debug.print("MapPrevilegeBean in ejbFindByPrivilegeId:" + priList);
        } 
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in ejbFindByPrivilegeId:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in ejbFindByPrivilegeId:" + e.getMessage());
        }
        return priList;
    }
   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("MapPrevilegeBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as mapPrivilegeId";
            Debug.print("MapPrevilegeBean getNextId:" + selectStatement);
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
    private void insertRowMapPrivilege() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("MapPrevilegeBean insertRowMapPrivilege");
        
        this.mapPrivilegeId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ROLE_MAP_PRIVILEGE  + "(map_privilege_id, role_id, privilege_id) " +
                 " values ( ? , ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, mapPrivilegeId);
            prepStmt.setString(2, roleId);
            prepStmt.setString(3, privilegeId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowMapPrivilege:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowMapPrivilege:" + e.getMessage());
        }        
    }

    private void loadMapPrivilege() throws SQLException {
        Debug.print("MapPrevilegeBean loadMapPrivilege");
        mapPrivilegeId = (String)context.getPrimaryKey();

        Debug.print("MapPrevilegeBean loadMapPrivilege Primary Key:" + mapPrivilegeId );
        makeConnection();
        try{
            String selectStatement =
                "select  role_id, privilege_id from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE  + " where map_privilege_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, mapPrivilegeId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                this.roleId = rs.getString(1);
                this.privilegeId = rs.getString(2);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + mapPrivilegeId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadMapPrivilege:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadMapPrivilege:" + e.getMessage());
        }        
    }
    
    private void storeMapPrivilege() throws SQLException {
        Debug.print("MapPrevilegeBean storeMapPrivilege");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ROLE_MAP_PRIVILEGE  + " set role_id = ? , privilege_id = ? " + 
                    " where map_privilege_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            prepStmt.setString(1, roleId);
            prepStmt.setString(2, privilegeId);
            prepStmt.setString(3, mapPrivilegeId);
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeMapPrivilege:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeMapPrivilege:" + e.getMessage());
        }        
    }
    
    
    private void deleteRow(String mapPrivilegeId) throws SQLException {
         Debug.print("MapPrevilegeBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE  + "  where map_privilege_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, mapPrivilegeId);
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
            Debug.print("MapPrevilegeBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
        Debug.print("MapPrevilegeBean releaseConnection");
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
}
