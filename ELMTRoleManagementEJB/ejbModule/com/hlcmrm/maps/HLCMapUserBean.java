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
 * This is the bean class for the MapUserBean enterprise bean.
 * Created Sep 1, 2006 9:19:32 PM
 * @author suresh
 */
public class HLCMapUserBean implements EntityBean, HLCMapUserLocalBusiness {
    private EntityContext context;
    private String userMapId;
    private String userId;
    private String mapPrivilegeId;
    private String userPrivilegePath;
    private String activeStatus;
    private Connection con;
    
    public void setUserMapId(String userMapId){
        this.userMapId = userMapId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public void setMapPrivilegeId(String mapPrivilegeId){
        this.mapPrivilegeId = mapPrivilegeId;
    }
    
     public void setUserPrivilegePath(String userPrivilegePath){
        this.userPrivilegePath = userPrivilegePath;
    }
     
    public void setActiveStatus(String activeStatus){
        this.activeStatus = activeStatus;
    }
   
    public String getUserMapId(){
        return userMapId;
    }
   
    public String getUserId(){
        return userId;
    }
    
    public String getMapPrivilegeId(){
        return mapPrivilegeId;
    }
    
     public String getUserPrivilegePath(){
        return mapPrivilegeId;
    }
     
    public String isActiveStatus(){
        return activeStatus;
    }
   
   
    public String ejbCreate(String userId, String mapPrivilegeId, String userPrivilegePath) throws CreateException{
        Debug.print("MapUserBean ejbCreate");   
        this.userId = userId;
        this.mapPrivilegeId = mapPrivilegeId;
        this.userPrivilegePath = userPrivilegePath;
        
        try {
            insertRowMapUser();
        } catch (Exception ex) {
            throw new EJBException("ejbCreate: " + ex.getMessage());
        }
        Debug.print("Primary Key: After Insertion" + userMapId);
        return  userMapId;
    }
    
    
     public void ejbPostCreate(String userId, String mapPrivilegeId, String userPrivilegePath) throws CreateException{
        Debug.print("MapUserBean ejbPostCreate");
    }
    
    public void setEntityContext(EntityContext aContext) {
        context = aContext;
    }
    
    public void ejbActivate() {
        Debug.print("MapUserBean ejbActivate");
        userMapId = (String)context.getPrimaryKey();
    }
    
    public void ejbPassivate() {
        Debug.print("MapUserBean ejbPassivate");
        userMapId = "";   
    }
    
    public void ejbRemove() {
        Debug.print("MapUserBean ejbRemove");

        try {
            deleteRow(userMapId);
        } catch (Exception ex) {
            throw new EJBException("ejbRemove: " + ex.getMessage());
        }
    }
       
    public void unsetEntityContext() {
         Debug.print("MapUserBean unsetEntityContext");
        context = null;
    }
    
  
    public void ejbLoad() { 
        Debug.print("MapUserBean ejbLoad");
        try {
            loadMapUser();
        } catch (Exception ex) {
            throw new EJBException("ejbLoad: " + ex.getMessage());
        }
    }
    
    public void ejbStore() {
        Debug.print("MapUserBean ejbStore");

        try {
            storeMapUser();
        } catch (Exception ex) {
            throw new EJBException("ejbStore: " + ex.getMessage());
        }
    }
     
   public String ejbFindByPrimaryKey(String userMapId) throws FinderException {
        Debug.print("MapUserBean ejbFindByPrimaryKey");
        boolean result;
        try {
            result = selectByPrimaryKey(userMapId);
        } catch (Exception ex) {
            throw new EJBException("ejbFindByPrimaryKey: " + ex.getMessage());
        }

        if (result) {
            return userMapId;
        } else {
            throw new ObjectNotFoundException("Row for id " + userMapId + " not found.");
        }
    }
        
    
       /*********************** Database Routines *************************/

    private boolean selectByPrimaryKey(String userMapId) throws SQLException {
            Debug.print("MapUserBean selectByPrimaryKey:" + userMapId);
            boolean result = false;          
            makeConnection();
            try {
                String selectStatement = "SELECT user_map_id from " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE  + " WHERE user_map_id = ?";
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1, userMapId);

                ResultSet rs = prepStmt.executeQuery();
                result = rs.next();
                prepStmt.close();
                releaseConnection();
                Debug.print("MapUserBean selectByPrimaryKey" + result);
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
            Debug.print("MapUserBean ejbFindAll");
            Vector userList = new Vector();
            makeConnection();
            try {
                String selectStatement = "select user_map_id from " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE ;
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    userList.addElement(rs.getString(1));
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
            return userList;
    }

    public Collection ejbFindByUserId(String userId) throws FinderException{
            Debug.print("MapUserBean ejbFindByUserId:" + userId);
            makeConnection();
            ArrayList userList = new ArrayList();
            try {
                String selectStatement = "select user_map_id from " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE + " where user_id = ?" ;
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,userId);

                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    userList.add(rs.getString(1));
                    Debug.print("MapUserBean in RoleId:" + rs.getString(1));
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("MapUserBean in ejbFindByUserId:" + userList);
            } 
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindByUserId:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindByUserId:" + e.getMessage());
            }
            return userList;
    }
    
    
    public Collection ejbFindByMapPrivilegeId(String mapPrivilegeId) throws FinderException{
            Debug.print("MapUserBean ejbFindByMapPrivilegeId:" + mapPrivilegeId);
            makeConnection();
            ArrayList priList = new ArrayList();
            try {
                String selectStatement = "select user_map_id from " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE + " where map_privilege_id = ?" ;
                PreparedStatement prepStmt = con.prepareStatement(selectStatement);
                prepStmt.setString(1,mapPrivilegeId);

                ResultSet rs = prepStmt.executeQuery();
                while (rs.next()){
                    priList.add(rs.getString(1));
                    Debug.print("MapUserBean in ejbFindByMapPrivilegeId:" + rs.getString(1));
                }
                rs.close();
                prepStmt.close();
                releaseConnection();
                Debug.print("MapUserBean in ejbFindByUserId:" + priList);
            } 
            catch(SQLException sql){
                releaseConnection();
                throw new EJBException("SQL Exception in ejbFindByMapPrivilegeId:" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection();
                throw new EJBException("General Exception  in ejbFindByMapPrivilegeId:" + e.getMessage());
            }
            return priList;
    }    
    

   
    private String getNextId() throws SQLException ,HLCMissingPrimaryKeyException {
        Debug.print("MapUserBean getNextId");
        makeConnection();
        String nextId = null;
        try{
            String selectStatement = "select newid() as userMapId";
            Debug.print("MapUserBean getNextId:" + selectStatement);
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
    
    private void insertRowMapUser() throws SQLException,HLCMissingPrimaryKeyException {
        Debug.print("MapUserBean insertRowMapUser");
        this.userMapId = getNextId();
        
        makeConnection();
        try{
            String insertStatement =
                "insert into " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE  + "(user_map_id, user_id, map_privilege_id, user_privilege_path) " +
                 " values ( ? , ?, ?, ?)";
            PreparedStatement prepStmt = con.prepareStatement(insertStatement);
            prepStmt.setString(1, userMapId);
            prepStmt.setString(2, userId);
            prepStmt.setString(3, mapPrivilegeId);
            prepStmt.setString(4, userPrivilegePath);
            
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in insertRowMapUser:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in insertRowMapUser:" + e.getMessage());
        }        
    }

    private void loadMapUser() throws SQLException {
        Debug.print("MapUserBean loadMapUser");
        userMapId = (String)context.getPrimaryKey();

        Debug.print("MapUserBean loadMapUser Primary Key:" + userMapId );
        makeConnection();
        try{
            String selectStatement =
                "select  user_id, map_privilege_id, user_privilege_path, active_status  from " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE  + " where user_map_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(selectStatement);
            prepStmt.setString(1, userMapId);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()) {
                prepStmt.setString(1, userId);
                prepStmt.setString(2, mapPrivilegeId);
                prepStmt.setString(3, userPrivilegePath);
                prepStmt.setString(4, activeStatus);
                
                this.userId = rs.getString(1);
                this.mapPrivilegeId = rs.getString(2);
                this.userPrivilegePath = rs.getString(3);
                this.activeStatus = rs.getString(4);
                prepStmt.close();
                releaseConnection();
            } else {
                prepStmt.close();
                releaseConnection();
                throw new NoSuchEntityException("Row for id " + userMapId + " not found in database.");
            }
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in loadMapUser:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in loadMapUser:" + e.getMessage());
        }        
    }
    
    private void storeMapUser() throws SQLException {
        Debug.print("MapUserBean storeMapUser");
        makeConnection();
        try{
            String updateStatement =
                    "update " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE  + " set user_id = ? , map_privilege_id = ? , " + 
                    "user_privilege_path = ?  where user_map_id = ? ";

            PreparedStatement prepStmt = con.prepareStatement(updateStatement);

            
            prepStmt.setString(1, userId);
            prepStmt.setString(2, mapPrivilegeId);
            prepStmt.setString(3, userPrivilegePath);
            //prepStmt.setString(4, activeStatus);
            prepStmt.setString(4, userMapId);
          
            
            int rowCount = prepStmt.executeUpdate();
            Debug.print("Sucessfully Updated." + rowCount);
            prepStmt.close();
            releaseConnection();
        }
        catch(SQLException sql){
            releaseConnection();
            throw new EJBException("SQL Exception in storeMapUser:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection();
            throw new EJBException("General Exception  in storeMapUser:" + e.getMessage());
        }        
    }
    
    
    private void deleteRow(String userMapId) throws SQLException {
         Debug.print("MapUserBean deleteRow");

        makeConnection();
        try{
            String deleteStatement = "delete from " + DBHelper.USEA_ROLE_MAP_USER_PRIVILEGE  + "  where user_map_id = ? ";
            PreparedStatement prepStmt = con.prepareStatement(deleteStatement);

            prepStmt.setString(1, userMapId);
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
            Debug.print("MapUserBean : makeConnection");
            try {
                InitialContext ic = new InitialContext();
                DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
                con = ds.getConnection();
            } catch (Exception ex) {
                throw new EJBException("Unable to connect to database. " + ex.getMessage());
            }
        }

     private void releaseConnection() {
        Debug.print("MapUserBean releaseConnection");
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException ex) {
            throw new EJBException("releaseConnection: " + ex.getMessage());
        }
    }
}
