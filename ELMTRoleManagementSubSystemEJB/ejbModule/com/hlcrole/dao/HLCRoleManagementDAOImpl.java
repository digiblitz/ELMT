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
/*  Program Name    : RoleManagementDAOImpl.java
 *  Created Date    : September 17, 2006, 12:23 PM
 *  Author          : Suresh.K
 *  Version         : 1.20
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */


package com.hlcrole.dao;

import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCEJBAllJNDIs;
import com.hlccommon.util.HLCMenuListVO;
import javax.sql.DataSource;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.hlccommon.util.DBHelper;
import java.sql.Statement;

/**
 *
 * @author suresh
 */
public class HLCRoleManagementDAOImpl implements HLCRoleManagementDAO {
    private Connection conn;
    
    private static HLCRoleManagementDAOImpl daoImpl = null;
//=============================================Getting All Permission details=========================================      
    public ArrayList selectAllPermission() {
        Debug.print("RoleManagementDAOImpl.getAllPermission():");
        ArrayList permissionList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        
        makeConnection();
   	try {
            //for debug starts
            String selectStatement=" SELECT permission_id, permission_name from " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " order by permission_name" ;
          /*  String selectStatement="SELECT a.privilege_id, a.permission_id, a.access_name from " +DBHelper.USEA_ROLE_MAP_PERMISSION + " a , " +DBHelper.USEA_ROLE_PERMISSION_MASTER + " b "
                    +"where a.permission_id = b.permission_id and a.access_name !='' order by a.privilege_id";*/
            Debug.print(" selectStatement "+selectStatement);
            //for debug ends
            prepStmt = conn.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            permissionList = new ArrayList();
            while(rs.next()){
                
                String privilegeId = rs.getString(1);
                String permissionId = rs.getString(2);
                 // String accessName = rs.getString(3);
               // String[] perList = {privilegeId,permissionId, accessName};]
                String[] perList = {privilegeId,permissionId};
                permissionList.add(perList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.getAllPermission():" + permissionList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getAllPermission():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getAllPermission():" + e.getMessage());
        }
        return permissionList;
    }
    public ArrayList selectAllMapPermission(String roleId, String entityId){
        Debug.print("RoleManagementDAOImpl.getAllPermission():");
        ArrayList permissionList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
          
            /*(31-5-2011)String selectStatement="select distinct 'Map' chk, a.privilege_id, b.permission_id , a.access_name from "+ DBHelper.USEA_ROLE_MAP_PERMISSION +" a  FULL OUTER JOIN " + DBHelper.USEA_ROLE_MAP_ROLE +" b on a.privilege_id = b.privilege_id and a.permission_id = b.permission_id where b.role_id =? and b.entity_id =? and a.access_name !=''"
            + "union "
            +" select distinct 'All' chk, b.privilege_id, a.permission_id , b.access_name from " +DBHelper.USEA_ROLE_PERMISSION_MASTER + " a, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " b, " + DBHelper.USEA_ROLE_MAP_ROLE + " c where a.permission_id = b.permission_id and b.privilege_id = c.privilege_id and b.access_name !='' "
            +" and a.permission_id not in(select permission_id from " + DBHelper.USEA_ROLE_MAP_ROLE + " where privilege_id = b.privilege_id and role_id =? and  entity_id =?) order by a.access_name";*/

           /* String selectStatement="select distinct 'Map' chk, a.privilege_id, a.permission_id , a.access_name from " + DBHelper.USEA_ROLE_MAP_PERMISSION+" a FULL OUTER JOIN " + DBHelper.USEA_ROLE_MAP_ROLE +" b on a.privilege_id = b.privilege_id and a.permission_id = b.permission_id and b.role_id =? and b.entity_id =? where a.access_name !=''"
             + "union "
            +" select distinct 'All' chk, b.privilege_id, a.permission_id , b.access_name from " +DBHelper.USEA_ROLE_PERMISSION_MASTER + " a, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " b, " + DBHelper.USEA_ROLE_MAP_ROLE + " c where a.permission_id = b.permission_id and b.privilege_id = c.privilege_id and b.access_name !='' "
            +" and a.permission_id not in(select permission_id from " + DBHelper.USEA_ROLE_MAP_ROLE + " where privilege_id = b.privilege_id and role_id =? and  entity_id =?) order by a.access_name";*/

            String selectStatement="select distinct 'Map' chk, a.privilege_id, a.permission_id , a.access_name from " +DBHelper.USEA_ROLE_MAP_PERMISSION +" a, "+ DBHelper.USEA_ROLE_MAP_ROLE+" b where a.privilege_id = b.privilege_id and a.permission_id = b.permission_id and b.role_id =? and b.entity_id =? and a.access_name ! = '' "
                    +"union"
                    +" select distinct 'All' chk, b.privilege_id, a.permission_id , b.access_name from " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " a, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " b where a.permission_id = b.permission_id and b.access_name !='' "
                    +" and a.permission_id not in(select permission_id from "+ DBHelper.USEA_ROLE_MAP_ROLE +" where privilege_id = b.privilege_id and role_id =? and entity_id =?) order by a.access_name " ;


            Debug.print(" selectStatement "+selectStatement);
            //Start:[RoleMgt] For Role,Privilege,Permission Mapping
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, roleId);
            prepStmt.setString(2, entityId);
            prepStmt.setString(3, roleId);
            prepStmt.setString(4, entityId);
            /*  prepStmt.setString(5, roleId);
            prepStmt.setString(6, entityId);
           prepStmt.setString(7, roleId);
            prepStmt.setString(8, entityId);
           /* prepStmt.setString(9, roleId);
            prepStmt.setString(10, entityId);
            prepStmt.setString(11, roleId);
            prepStmt.setString(12, entityId);*/
            rs = prepStmt.executeQuery();
            permissionList = new ArrayList();
            while(rs.next()){

                String chk = rs.getString(1);
                String privilegeId = rs.getString(2);
                String permissionId = rs.getString(3);
                  String accessName = rs.getString(4);
                String[] perList = {chk, privilegeId,permissionId, accessName};
                permissionList.add(perList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.getAllPermission():" + permissionList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getAllPermission():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getAllPermission():" + e.getMessage());
        }
        return permissionList;
    }

    public ArrayList selectAllSubPermission() {
        Debug.print("RoleManagementDAOImpl.getAllPermission():");
        ArrayList permissionList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            //Start:[RoleMgt] For Role,Entity,Privilege,Permission,SubPermission Mapping
            //String selectStatement=" SELECT permission_id, permission_name from " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " order by permission_name" ;
            String selectStatement="SELECT permission_id,sub_permission_name,active_status from " +DBHelper.USEA_USER_MAP_SUB_PRIVILEGE;
            //String selectStatement = "SELECT permission_id, convert(varchar(100),map_sub_permission_id)+'#'+sub_permission_name sub_permission_name, active_status from " +DBHelper.USEA_USER_MAP_SUB_PRIVILEGE;
            Debug.print(" selectStatement "+selectStatement);
          //Ends:[RoleMgt] For Role,Entity,Privilege,Permission,SubPermission Mapping
            prepStmt = conn.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            permissionList = new ArrayList();
            while(rs.next()){

                String permissionId = rs.getString(1);
                String subPermissionId = rs.getString(2);
                String status = rs.getString(3);
                  
                String[] perList = {permissionId, subPermissionId,status};
                permissionList.add(perList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.getAllPermission():" + permissionList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getAllPermission():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getAllPermission():" + e.getMessage());
        }
        return permissionList;
    }

   //Start:[RoleMgt] For Role,Entity,Privilege,Permission,SubPermission Mapping
    public ArrayList selectEntityPrivPermission()
    {
         Debug.print("RoleManagementDAOImpl.selectEntityPrivPermission():");
        ArrayList EntpermissionList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement="select a.privilege_id, a.privilege_name, b.permission_id, c.access_name from "+ DBHelper.USEA_ROLE_PRIVILEGE_MASTER +" a," + DBHelper.USEA_ROLE_PERMISSION_MASTER +" b," + DBHelper.USEA_ROLE_MAP_PERMISSION +" c "+
                    " where a.privilege_id = c.privilege_id and b.permission_id = c.permission_id and access_name !='' order by a.privilege_name";
            prepStmt = conn.prepareStatement(selectStatement);
            System.out.println("4"+selectStatement);
            rs = prepStmt.executeQuery();
            EntpermissionList = new ArrayList();
            while(rs.next()){
                
                String privilegeId = rs.getString(1);
                String privilegeName = rs.getString(2);
                 String permissionID = rs.getString(3);
                  String accessName = rs.getString(4);

                String[] privperList = {privilegeId, privilegeName,permissionID,accessName};
                EntpermissionList.add(privperList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.getAllPermission():" + permissionList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getAllPermission():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getAllPermission():" + e.getMessage());
        }
        return EntpermissionList;
    }
   //Ends:[RoleMgt] For Role,Entity,Privilege,Permission,SubPermission Mapping
//=============================================Insert Privilege details=========================================      
    public boolean insertPrivilege(String privilegeName) {
            Debug.print("RoleManagementDAOImpl.insertPrivilege():");
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " (privilege_name) " +
                        " values( ?  )";
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, privilegeName);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);
            } 
            catch(SQLException sql){
                releaseConnection(conn);
                Debug.print("SQL Exception in RoleManagementDAOImpl.insertPrivilege():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn);
                Debug.print("General Exception  in RoleManagementDAOImpl.insertPrivilege():" + e.getMessage());
            }
            return true;
        }    
//=============================================Edit Mapping permission with privilege details=========================================      
    public boolean updatePrivilege(String privilegeId, String privilegeName){
        Debug.print("RoleManagementDAOImpl.updatePrivilege():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "update " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " set privilege_name = ? " +
                    " where privilege_id = ?";
            
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, privilegeName);
            prepStmt.setString(2, privilegeId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        }
        
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.updatePrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.updatePrivilege():" + e.getMessage());
        }
        return true;
    }    
//=============================================Privilege Name Checking details=========================================      
    public boolean isPrivilegeNameExist(String privilegeName) {
        Debug.print("RoleManagementDAOImpl.isPrivilegeNameExist():" + privilegeName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select privilege_id from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " where privilege_name = ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,privilegeName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from privilegeName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isPrivilegeNameExist:" + e.getMessage());
        }
        Debug.print("isPrivilegeNameExist():" + result);
        return result;
    }
//=============================================Privilege Name Checking details=========================================      
    public boolean isPrivilegeNameEditExist(String privilegeId, String privilegeName) {
        Debug.print("RoleManagementDAOImpl.isPrivilegeNameEditExist():" + privilegeName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select privilege_id from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " where privilege_name = ? and privilege_id != ?" ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,privilegeName);
            prepStmt.setString(2,privilegeId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from isPrivilegeNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isPrivilegeNameEditExist:" + e.getMessage());
        }
        Debug.print("isPrivilegeNameExist():" + result);
        return result;
    }        
//=============================================Getting a particular Privilege details=========================================      
    public String [] selectPrivilege(String privilegeId){
        Debug.print("RoleManagementDAOImpl.selectPrivilege():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        String[] priList = {};
        makeConnection();
   	try {
            String selectStatement=" SELECT privilege_id, privilege_name, privilege_path from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " where privilege_id = ? " ;
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,privilegeId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String privilegeIdVal = rs.getString(1);
                String privilegeName = rs.getString(2);
                String privilegePath = rs.getString(3);
                String tempPriList[] = {privilegeIdVal, privilegeName, privilegePath};
                priList = tempPriList;
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectPrivilege():" + priList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectPrivilege():" + e.getMessage());
        }
        return priList;
    }    
//=============================================Getting All Privilege details=========================================      
    public ArrayList selectAllPrivilege(){
        Debug.print("RoleManagementDAOImpl.getAllPrivilege():");
        ArrayList privilegeList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT privilege_id, privilege_name, privilege_path from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " order by privilege_name";
            prepStmt = conn.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            privilegeList = new ArrayList();
            while(rs.next()){
                String privilegeId = rs.getString(1);
                String privilegeName = rs.getString(2);
                String privilegePath = rs.getString(3);
                String[] priList = {privilegeId, privilegeName, privilegePath};
                privilegeList.add(priList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.getAllPrivilege():" + privilegeList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getAllPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getAllPrivilege():" + e.getMessage());
        }
        return privilegeList;
    }


    //=============================================Getting All Privilege details=========================================
    public ArrayList selectAllMapPrivilege(String roleId, String entityId){
        Debug.print("RoleManagementDAOImpl.getAllPrivilege():");
        ArrayList privilegeList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
           // String selectStatement=" SELECT privilege_id, privilege_name, privilege_path from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " order by privilege_name";
        String selectStatement="select 'Map' chk, privilege_id , privilege_name from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " where privilege_id in( select privilege_id from " + DBHelper.USEA_ROLE_MAP_ROLE + " where role_id=? and entity_id=? ) "
                +" union "
                +" select 'All' chk, privilege_id , privilege_name from " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " where privilege_id not in( select privilege_id from " + DBHelper.USEA_ROLE_MAP_ROLE + " where role_id=? and entity_id=? ) "
                +" and privilege_id in(select a.privilege_id from tblMapPermission a where a.access_name != '') order by privilege_name";


            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, roleId);
            prepStmt.setString(2, entityId);
            prepStmt.setString(3, roleId);
            prepStmt.setString(4, entityId);

            rs = prepStmt.executeQuery();

            privilegeList = new ArrayList();
            while(rs.next()){
                String chk = rs.getString(1);
                 String privilegeId = rs.getString(2);
                String privilegeName = rs.getString(3);

                String[] priList = {chk, privilegeId, privilegeName};
                privilegeList.add(priList);
            }

            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.getAllPrivilege():" + privilegeList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllMapPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllMapPrivilege():" + e.getMessage());
        }
        return privilegeList;
    }
//=============================================Getting All Enities details=========================================      
    public ArrayList selectAllEntity(){
        Debug.print("RoleManagementDAOImpl.selectAllEntity():");
        ArrayList entitiesList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement = " SELECT entity_id, entity_name from " + DBHelper.USEA_ROLE_ENTITY_MASTER + " order by entity_name";
            prepStmt = conn.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            entitiesList = new ArrayList();
            while(rs.next()){
                String entityId = rs.getString(1);
                String entityName = rs.getString(2);
                String[] entList = {entityId, entityName};
                entitiesList.add(entList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectAllEntity():" + entitiesList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllEntity():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllEntity():" + e.getMessage());
        }
        return entitiesList;
    } 
//=============================================Insert Entity details=========================================      
    public boolean insertEntity(String entityName) {
            Debug.print("RoleManagementDAOImpl.insertEntity():");
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_ROLE_ENTITY_MASTER + " (entity_name) " +
                        " values(?)";
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, entityName);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);
            } 
            catch(SQLException sql){
                releaseConnection(conn);
                Debug.print("SQL Exception in RoleManagementDAOImpl.insertEntity():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn);
                Debug.print("General Exception  in RoleManagementDAOImpl.insertEntity():" + e.getMessage());
            }
            return true;
        }
//=============================================Edit enity details=========================================      
    public boolean updateEntity(String entityId, String entityName) {
        Debug.print("RoleManagementDAOImpl.updateEntity():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "update " + DBHelper.USEA_ROLE_ENTITY_MASTER + " set entity_name = ? " +
                    " where entity_id = ? ";
            
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, entityName);
            prepStmt.setString(2, entityId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        }
        
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.updateEntity():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.updateEntity():" + e.getMessage());
        }
        return true;
    }     
//=============================================Entity Name Checking details=========================================      
    public boolean isEntityNameExist(String entityName) {
        Debug.print("RoleManagementDAOImpl.isEntityNameExist():" + entityName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select entity_id from " + DBHelper.USEA_ROLE_ENTITY_MASTER + " where entity_name = ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,entityName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from entityName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isEntityNameExist:" + e.getMessage());
        }
        Debug.print("isEntityNameExist():" + result);
        return result;
    }
//=============================================Privilege Name Checking details=========================================      
    public boolean isEntityNameEditExist(String entityId, String entityName) {
        Debug.print("RoleManagementDAOImpl.isEntityNameEditExist():" + entityName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select entity_id from " + DBHelper.USEA_ROLE_ENTITY_MASTER + " where entity_name = ? and entity_id != ?" ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,entityName);
            prepStmt.setString(2,entityId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from isEntityNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isEntityNameEditExist:" + e.getMessage());
        }
        Debug.print("isEntityNameEditExist():" + result);
        return result;
    }        
//=============================================Getting a particular Privilege details=========================================      
    public String [] selectEntity(String entityId){
        Debug.print("RoleManagementDAOImpl.selectEntity():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        String[] entList = {};
        makeConnection();
   	try {
            String selectStatement=" SELECT entity_id, entity_name from " + DBHelper.USEA_ROLE_ENTITY_MASTER + " where entity_id = ? " ;
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,entityId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String entityIdVal = rs.getString(1);
                String entityName = rs.getString(2);
                String[] tempEntList = {entityIdVal, entityName};
                entList = tempEntList;
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectEntity():" + entList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectEntity():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectEntity():" + e.getMessage());
        }
        return entList;
    }    
    
//=============================================Insert Role details=========================================      
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //public boolean insertRole(String roleName) {
    public boolean insertRole(String roleName,String roledesc,String status) {
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            Debug.print("RoleManagementDAOImpl.insertRole():");
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
	//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
              //  String insertStatement = "insert into " + DBHelper.USEA_ROLE_ROLE_MASTER + " (role_name) " +
                   //     " values( ?  )";
                String insertStatement = "insert into " + DBHelper.USEA_ROLE_ROLE_MASTER + " (role_name,role_desc,active_status) " +
                        " values( ?,?,?  )";
	//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, roleName);
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                 prepStmt.setString(2, roledesc);
                  prepStmt.setString(3, status);
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);
            } 
            catch(SQLException sql){
                releaseConnection(conn);
                Debug.print("SQL Exception in RoleManagementDAOImpl.insertRole():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn);
                Debug.print("General Exception  in RoleManagementDAOImpl.insertRole():" + e.getMessage());
            }
            return true;
        }      
 //=============================================Edit Role details=========================================      
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //public boolean updateRole(String roleId, String roleName) {
    public boolean updateRole(String roleId, String roleName,String roledesc,String status) {
    //public boolean updateRole(String roleId[], String roleName[],String roledesc[],String status[]) {
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        Debug.print("RoleManagementDAOImpl.updateRole():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "update " + DBHelper.USEA_ROLE_ROLE_MASTER + " set role_name = ?,role_desc =? " +
	//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                    //" where role_id = ?";
                    " ,active_status = ? where role_id = ?";
	//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011

            //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            /*for(int i=0;i<roleId.length;i++)
            {*/
            //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, roleName);
                //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                 prepStmt.setString(2, roledesc);
                prepStmt.setString(3, status);
                 prepStmt.setString(4, roleId);
                //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                prepStmt.executeUpdate();
                prepStmt.close();
            //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            //}
                //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011

            releaseConnection(conn);
        }
        
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.updateRole():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.updateRole():" + e.getMessage());
        }
        return true;
    }
    //=============================================delete Role details=========================================
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //public boolean updateRole(String roleId, String roleName) {
    //public boolean deleteRole(String roleId) {
    public boolean deleteRole(String chkRoleIdArr[]) {
        Debug.print("RoleManagementDAOImpl.deleteRole():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String deleteStatement = "delete from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_id = ?";
                    
            for(int i=0;i<chkRoleIdArr.length;i++)
            {
                prepStmt = conn.prepareStatement(deleteStatement);
                //prepStmt.setString(1, roleId);
                prepStmt.setString(1, chkRoleIdArr[i]);

                Debug.print("RoleManagementDAOImpl.deleteRole():"+deleteStatement+"=="+chkRoleIdArr[i]);
                prepStmt.executeUpdate();
                prepStmt.close();
            }

            releaseConnection(conn);
        }

        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.deleteRole:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.deleteRole:" + e.getMessage());
        }
        return true;
    }
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
//=============================================Role Name Checking details=========================================      
    public boolean isRoleNameExist(String roleName) {
        Debug.print("RoleManagementDAOImpl.isRoleNameExist():" + roleName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select role_id from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_name = ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from roleName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isRoleNameExist:" + e.getMessage());
        }
        Debug.print("isRoleNameExist():" + result);
        return result;
    }    
//=============================================Role Name Checking details=========================================      
    //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    public boolean isRoleNameEditExist(String roleId, String roleName) {
    //public boolean isRoleNameEditExist(String roleId[], String roleName[]) {
    //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        Debug.print("RoleManagementDAOImpl.isRoleNameEditExist():" + roleName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select role_id from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_name = ? and role_id != ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleName);
            prepStmt.setString(2,roleId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("isRoleNameEditExist result:" + result);  
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from isRoleNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isRoleNameEditExist:" + e.getMessage());
        }
        Debug.print("isRoleNameEditExist():" + result);
        return result;
    } 
//=============================================Getting a particular Role details=========================================      
   public String [] selectRole(String roleId) {
        Debug.print("RoleManagementDAOImpl.selectRole():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        String[] strRoleList = {};
        makeConnection();
   	try {
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
          //  String selectStatement=" SELECT role_id, role_name from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_id = ? " ;
            //String selectStatement=" SELECT role_id, role_name,role_desc,active_status from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_id = ? " ;
            String selectStatement=" select case when role_id in(select role_id from tblMapEntity union all select role_id from tblMapUserPrivilege"+
                                   " union all select role_id from tblMapRole) then '0' else '1' end flag, "+
                                   " role_id, role_name,role_desc,active_status from " + DBHelper.USEA_ROLE_ROLE_MASTER + " where role_id = ? ";
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String roleIdVal = rs.getString(2);
                String roleName = rs.getString(3);
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                String roledesc = rs.getString("role_desc");
                String status = rs.getString("active_status");
                String flag = rs.getString("flag");
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                String tempStrRoleList[] = {roleIdVal, roleName,roledesc,status,flag};
                strRoleList = tempStrRoleList;
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("RoleManagementDAOImpl.selectRole():" + strRoleList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectRole():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectRole():" + e.getMessage());
        }
        return strRoleList;
    } 
    
//=============================================Getting All Roles details=========================================      
    public ArrayList selectAllRole(){
        Debug.print("RoleManagementDAOImpl.selectAllRole():");
        ArrayList rolesList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        //    String selectStatement = " SELECT role_id, role_name from " + DBHelper.USEA_ROLE_ROLE_MASTER + " order by role_name";
              //String selectStatement = " SELECT role_id, role_name,role_desc,active_status from " + DBHelper.USEA_ROLE_ROLE_MASTER + " order by role_name";
                String selectStatement=" select case when role_id in(select role_id from tblMapEntity union all select role_id from tblMapUserPrivilege "+
                                       " union all select role_id from tblMapRole) then '0' else '1' end flag, "+
                                       " role_id, role_name,role_desc,active_status,role_path from " + DBHelper.USEA_ROLE_ROLE_MASTER + " order by role_name";
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            prepStmt = conn.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            rolesList = new ArrayList();
            while(rs.next()){
                String roleId = rs.getString(2);
                String roleName = rs.getString(3);
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                String roledesc = rs.getString("role_desc");
                String status = rs.getString("active_status");
                String flag = rs.getString("flag");
                String rolePath = rs.getString(6);
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                String[] rlList = {roleId, roleName,roledesc,status,flag,rolePath};
                rolesList.add(rlList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectAllRole():" + rolesList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllRole():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllRole():" + e.getMessage());
        }
        return rolesList;
    }    
//=============================================Insert Permission details=========================================      
    public boolean insertPermission(String permissionName){
            Debug.print("RoleManagementDAOImpl.insertPermission():");
            PreparedStatement prepStmt = null;
            boolean result = false;
            makeConnection();
            try {
                String insertStatement = "insert into " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " (permission_name) " +
                        " values(?)";
                prepStmt = conn.prepareStatement(insertStatement);
                prepStmt.setString(1, permissionName);
                prepStmt.executeUpdate();
                prepStmt.close();
                releaseConnection(conn);
                result = true;
            } 
            catch(SQLException sql){
                releaseConnection(conn);
                Debug.print("SQL Exception in RoleManagementDAOImpl.insertPermission():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn);
                Debug.print("General Exception  in RoleManagementDAOImpl.insertPermission():" + e.getMessage());
            }
            return result;
        }    
    //=============================================Edit permission details=========================================      
    public boolean updatePermission(String permissionId, String permissionName) {
        Debug.print("RoleManagementDAOImpl.updatePermission():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String insertStatement = "update " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " set permission_name = ? " +
                    " where permission_id = ?";
            
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, permissionName);
            prepStmt.setString(2, permissionId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        }
        
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.updatePermission():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.updatePermission():" + e.getMessage());
        }
        return result;
    }    
    //=============================================Getting a particular permission details=========================================      
    public String [] selectPermission(String permissionId){
        Debug.print("RoleManagementDAOImpl.selectPrivilege():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        String[] perList = {};
        makeConnection();
   	try {
            String selectStatement=" SELECT permission_id, permission_name from " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " where permission_id = ? " ;
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,permissionId);
            rs = prepStmt.executeQuery();
            if(rs.next()){
                String permissionIdVal = rs.getString(1);
                String permissionName = rs.getString(2);
                String[] tempPerList = {permissionIdVal, permissionName};
                perList = tempPerList;
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectPrivilege():" + perList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectPrivilege():" + e.getMessage());
        }
        return perList;
    }    
    //=============================================Privilege Name Checking details=========================================      
     public boolean isPermissionNameExist(String permissionName) {
        Debug.print("RoleManagementDAOImpl.isPermissionNameExist():" + permissionName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select permission_id from " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " where permission_name = ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,permissionName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from permissionName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isPermissionNameExist:" + e.getMessage());
        }
        Debug.print("isPermissionNameExist():" + result);
        return result;
    }
//=============================================Privilege Name Checking details=========================================      
     public boolean isPermissionNameEditExist(String permissionId, String permissionName){
        Debug.print("RoleManagementDAOImpl.isPermissionNameEditExist():" + permissionName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select permission_id from " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " where permission_name = ? and permission_id != ?" ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,permissionName);
            prepStmt.setString(2,permissionId);
            
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from isPermissionNameEditExist" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isPermissionNameEditExist:" + e.getMessage());
        }
        Debug.print("isPrivilegeNameExist():" + result);
        return result;
    }        
//=============================================Create Mapping permission with privilege details=========================================      
    public boolean insertPermissionPrivilegeMapping(String privilegeId, String permissionId, String accessName, String accessUrl){
        Debug.print("RoleManagementDAOImpl.insertPermissionPrivilegeMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_ROLE_MAP_PERMISSION + " (privilege_id , permission_id, access_name, access_url) " +
                    " values( ? , ? , ? , ? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, privilegeId);
            prepStmt.setString(2, permissionId);
            prepStmt.setString(3, accessName);
            prepStmt.setString(4, accessUrl);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.insertPermissionPrivilegeMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.insertPermissionPrivilegeMapping():" + e.getMessage());
        }
        return true;
    }
    //=============================================Edit Mapping permission with privilege details=========================================      
    public boolean updatePermissionPrivilegeMapping(String mapPermissionId, String accessName, String accessUrl,String privId){
        Debug.print("RoleManagementDAOImpl.updatePermissionPrivilegeMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        String statementQuery="";
        try {
        	
        		statementQuery = "update " + DBHelper.USEA_ROLE_MAP_PERMISSION + " set access_name = ? , access_url = ? " +
        				" where map_permission_id = ?";   		
        
                    
            
            prepStmt = conn.prepareStatement(statementQuery);
            prepStmt.setString(1, accessName);
            prepStmt.setString(2, accessUrl);
            
           
            prepStmt.setString(3, mapPermissionId);
          
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        }
        
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.updatePermissionPrivilegeMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.updatePermissionPrivilegeMapping():" + e.getMessage());
        }
        return true;
    }
    
  //=============================================Getting Mapping permission with privilege details=========================================      
    public ArrayList selectAllMappingDetailsForPrivilege(String privilegeId){
        Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForPrivilege():");
        ArrayList permissionMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT a.map_permission_id , b.permission_name ,a.access_name, a.access_url from " + DBHelper.USEA_ROLE_MAP_PERMISSION 
                    + " a , " + DBHelper.USEA_ROLE_PERMISSION_MASTER + " b where a.permission_id = b.permission_id and a.privilege_id = ? ";
            
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,privilegeId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            permissionMapList = new ArrayList();
            while(rs.next()){
                String mapPermissionId = rs.getString(1);
                String permissionName = rs.getString(2);
                String accessName = rs.getString(3);
                String accessUrl = rs.getString(4);
                String[] priMapList = {mapPermissionId, permissionName, accessName, accessUrl};
                permissionMapList.add(priMapList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForPrivilege():" + permissionMapList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllMappingDetailsForPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllMappingDetailsForPrivilege():" + e.getMessage());
        }
        return permissionMapList;
    }

    //=============================================  Mapping Entites with privilege details=========================================      
     public boolean insertEntityPrivilegeMapping(String entityId, String privilegeId) {
        Debug.print("RoleManagementDAOImpl.insertEntityPrivilegeMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_ROLE_MAP_PRIVILEGE + " (entity_id , privilege_id) " +
                    " values( ? , ? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, entityId);
            prepStmt.setString(2, privilegeId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.insertEntityPrivilegeMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.insertEntityPrivilegeMapping():" + e.getMessage());
        }
        return true;
    }
//=============================================Getting Mapping permission with privilege details=========================================      
    public ArrayList selectAllMappingDetailsForEntity(String entityId){
        Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForEntity():");
        ArrayList entityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT a.map_privilege_id , a.entity_id ,a.privilege_id, b.entity_name, c.privilege_name from " + DBHelper.USEA_ROLE_MAP_PRIVILEGE 
                    + " a , " + DBHelper.USEA_ROLE_ENTITY_MASTER + " b , " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " c " +
                    " where a.privilege_id = c.privilege_id and a.entity_id = b.entity_id and a.entity_id =? order by b.entity_name";
            
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,entityId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            entityMapList = new ArrayList();
            while(rs.next()){
                String mapPrivilegeId = rs.getString(1);
                String entityIdVal = rs.getString(2);
                String privilegeId = rs.getString(3);
                String entityName = rs.getString(4);
                String privilegeName = rs.getString(5);
                
                String[] entMapList = {mapPrivilegeId, entityIdVal, privilegeId, entityName, privilegeName};
                entityMapList.add(entMapList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForEntity():" + entityMapList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllMappingDetailsForEntity():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllMappingDetailsForEntity():" + e.getMessage());
        }
        return entityMapList;
    }
  //=============================================  Mapping Entites with privilege details=========================================      
    public boolean insertRoleEntityMapping(String roleId, String entityId) {
        Debug.print("RoleManagementDAOImpl.insertRoleEntityMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_ROLE_MAP_ENTITY + " (role_id , entity_id) " +
                    " values( ? , ? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, roleId);
            prepStmt.setString(2, entityId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.insertRoleEntityMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.insertRoleEntityMapping():" + e.getMessage());
        }
        return true;
    }
    //=============================================Getting Mapping Role with Entities details=========================================      
    public ArrayList selectAllMappingDetailsForRole(String roleId){
         Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():");
         ArrayList roleEntityMapList = null;
         PreparedStatement prepStmt = null;
         ResultSet rs = null;
         makeConnection();
    	try {
             String selectStatement=" SELECT a.map_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name,c.entity_access_url from " + DBHelper.USEA_ROLE_MAP_ENTITY 
                     + " a , " + DBHelper.USEA_ROLE_ROLE_MASTER + " b , " + DBHelper.USEA_ROLE_ENTITY_MASTER + " c " +
                     " where a.entity_id = c.entity_id and a.role_id = b.role_id and a.role_id = ? order by c.entity_name";
          
             prepStmt = conn.prepareStatement(selectStatement);
             prepStmt.setString(1,roleId);
             Debug.print(selectStatement);
             rs = prepStmt.executeQuery();
             roleEntityMapList = new ArrayList();
             while(rs.next()){
                 String mapEntityId = rs.getString(1);
                 String roleIdVal = rs.getString(2);
                 String entityId = rs.getString(3);
                 String roleName = rs.getString(4);
                 String entityName = rs.getString(5);
                 String entityUrl = rs.getString(6);
                 String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl};
                 roleEntityMapList.add(entMapList);
             }
             rs.close();
             prepStmt.close();
             releaseConnection(conn);
             //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + roleEntityMapList);
         } 
         catch(SQLException sql){
             releaseConnection(conn);
             Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + sql.getMessage());
         }
         catch(Exception e){
             releaseConnection(conn);
             Debug.print("General Exception  in RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + e.getMessage());
         }
         return roleEntityMapList;
     }

   //=============================================  Mapping Entites with privilege details=========================================      
     public boolean insertRoleEntityPrivilegeMapping(String roleId, String entityId, String privilegeId, String permissionId) {
        Debug.print("RoleManagementDAOImpl.insertRoleEntityPrivilegeMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_ROLE_MAP_ROLE + " (role_id , entity_id, privilege_id, permission_id) " +
                    " values( ? , ? , ? , ?)";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, roleId);
            prepStmt.setString(2, entityId);
            prepStmt.setString(3, privilegeId);
            prepStmt.setString(4, permissionId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.insertRoleEntityPrivilegeMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.insertRoleEntityPrivilegeMapping():" + e.getMessage());
        }
        return true;
    }
     //Start:[RoleMgt] For Role,Privilege,Permission Mapping
public boolean updatePermissionSubPermissionMapping(String permIdArr[],String subPermIdArr[]) {
        Debug.print("RoleManagementDAOImpl.updatePermissionSubPermissionMapping():");
        PreparedStatement allUpdPrepStmt = null;
        PreparedStatement chkUpdPrepStmt = null;
        makeConnection();
        try {

            String allUpdateStatement = "update " + DBHelper.USEA_USER_MAP_SUB_PRIVILEGE + " set active_status = 0;";

            allUpdPrepStmt = conn.prepareStatement(allUpdateStatement);
            allUpdPrepStmt.executeUpdate();
            allUpdPrepStmt.close();
            
            String chkUpdateStatement = "update " + DBHelper.USEA_USER_MAP_SUB_PRIVILEGE + " set active_status = 1 where permission_id = ? and sub_permission_name = ? ";

            if(permIdArr!=null && subPermIdArr!=null)
            {
                for(int i=0;i<permIdArr.length;i++)
                {
                      System.out.println("insertStatement \n"+chkUpdateStatement+permIdArr[i]+subPermIdArr[i]);
                    chkUpdPrepStmt = conn.prepareStatement(chkUpdateStatement);
                    chkUpdPrepStmt.setString(1, permIdArr[i]);
                    chkUpdPrepStmt.setString(2, subPermIdArr[i]);
                    chkUpdPrepStmt.executeUpdate();
                    chkUpdPrepStmt.close();
                }
            }
            releaseConnection(conn);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.updatePermissionSubPermissionMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.updatePermissionSubPermissionMapping():" + e.getMessage());
        }
        return true;
    }

      //Ends:[RoleMgt] For Role,Privilege,Permission Mapping
    //============================================= Get Permissions based on Role, Entity  =========================================      
    public ArrayList selectPermissionBasedRoleEntity(String roleId, String entityId){
        Debug.print("RoleManagementDAOImpl.selectPermissionBasedRoleEntity():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement="SELECT A.role_name, B.entity_name, C.privilege_name, D.permission_name, E.privilege_id, E.permission_id  from " + DBHelper.USEA_ROLE_ROLE_MASTER 
                    + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                    DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E " +
                    " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and  " +
                    " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and " +
                    " E.role_id = ? and E.entity_id = ? order by C.privilege_name" ;

            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,entityId);
            
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String roleName = rs.getString(1);
                String entityName = rs.getString(2);
                String privilegeName = rs.getString(3);
                String permissionName = rs.getString(4);
                String privilegeIdVal = rs.getString(5);
                String permissionId = rs.getString(6);
                String[] entMapList = {roleName, entityName, privilegeName, permissionName, privilegeIdVal, permissionId};
                roleEntityMapList.add(entMapList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.selectPermissionBasedRoleEntity():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectPermissionBasedRoleEntity():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectPermissionBasedRoleEntity():" + e.getMessage());
        }
        return roleEntityMapList;
    }

   public ArrayList selectMenuBasedOnRoleEntitySubPrivilege(String roleId, String entityId, String privilegeId,String permissionId){
        Debug.print("RoleManagementDAOImpl.selectMenuBasedOnRoleEntitySubPrivilege():");
        ArrayList roleEntityMapSubList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT A.role_name, B.entity_name, C.privilege_name, D.permission_name, F.access_name, G.Sub_Url, F.permission_id ,G.sub_permission_name "
                    + " FROM " +
                    DBHelper.USEA_ROLE_ROLE_MASTER + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                    DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " F ," + DBHelper.USEA_USER_MAP_SUB_PRIVILEGE + " G " +
                    " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and   " +
                    " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and  " +
                    " F.privilege_id = E.privilege_id and F.permission_id = E.permission_id and " +
                    " E.role_id = ? and G.active_status = 1 "
                     //commented by murukesan for getting all entities
                   // + " and E.entity_id = ? "
                    //+ "and E.privilege_id = ?  "
                    //+ " and E.permission_id = ?  and "+
                    + " and E.permission_id = G.permission_id "
                    //commented by murukesan for getting all entities
                   // + " order by  D.permission_name " ;
                     + " order by  E.entity_id, D.permission_name " ;

            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            //prepStmt.setString(2,entityId);
            //prepStmt.setString(3,privilegeId);
           // prepStmt.setString(3,permissionId);
           Debug.print("Sub Permission ---->"+ selectStatement);
           Debug.print("role id ---->"+ roleId);
            //Debug.print("Entity Id ---->"+ entityId);
           // Debug.print(" privilegeId ---->"+ privilegeId);
          //  Debug.print("permissId ---->"+ permissionId);
            rs = prepStmt.executeQuery();
            // Debug.print("After executing query >");
            roleEntityMapSubList = new ArrayList();
            while(rs.next()){
                //Debug.print("Inside while loop ");
                String roleName = rs.getString(1);
                String entityName = rs.getString(2);
                String privilegeName = rs.getString(3);
                String permissionName = rs.getString(4);
                String accessName = rs.getString(5);
                String subUrl = rs.getString(6);
                String permissionnId = rs.getString(7);
                String subPermissionId = rs.getString(8);
                //String subPermissionName = rs.getString(9);
                //Debug.print("After subpermission name getting  ");
                HLCMenuListVO menuVO = new HLCMenuListVO();
                menuVO.setRoleId(roleId);
                menuVO.setEntityId(entityId);
                menuVO.setPrivilegeId(privilegeId);
                menuVO.setPermissionId(permissionnId);
                menuVO.setSubPermissionId(subPermissionId);

                menuVO.setRoleName(roleName);
                //Debug.print("Role Name ---->"+ roleName);

                menuVO.setEntityName(entityName);
                 //Debug.print("entityName ---->"+ entityName);

                menuVO.setPrivilegeName(privilegeName);
                //Debug.print("privilegeName ---->"+ privilegeName);

                menuVO.setPermissionName(permissionName);
                //Debug.print("permissionName ---->"+ permissionName);
               // menuVO.setSubPermissionId(subPermissionId);
                Debug.print("subPermissionId ---->"+ subPermissionId);
                Debug.print("accessName ---->"+ accessName);
                Debug.print("subUrl ---->"+ subUrl);

                menuVO.setAccessName(accessName);
                menuVO.setAccessUrl(subUrl);
                roleEntityMapSubList.add(menuVO);
                //Debug.print("After adding in  roleEntityMapSubList");
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.selectMenuBasedOnRoleEntitySubPrivilege():" + roleEntityMapSubList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectMenuBasedOnRoleEntityPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectMenuBasedOnRoleEntityPrivilege():" + e.getMessage());
        }
        return roleEntityMapSubList;
    }
    //============================================= Get distinct Permissions based on Role, Entity  =========================================      
    public ArrayList selectDistinctPermissionBasedRoleEntity(String roleId, String entityId){
        Debug.print("RoleManagementDAOImpl.selectDistinctPermissionBasedRoleEntity():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT distinct E.privilege_id, C.privilege_name, E.role_id, E.entity_id  from " + DBHelper.USEA_ROLE_ROLE_MASTER 
                    + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                    DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E " +
                    " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and  " +
                    " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and " +
                    " E.role_id = ? and E.entity_id = ? order by C.privilege_name" ;
         
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,entityId);
            
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String privilegeIdVal = rs.getString(1);
                String privilegeName = rs.getString(2);
                String roleIdVal = rs.getString(3);
                String entityIdVal = rs.getString(4);
                String[] entMapList = { privilegeIdVal, privilegeName,roleIdVal,entityIdVal};
                roleEntityMapList.add(entMapList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("RoleManagementDAOImpl.selectDistinctPermissionBasedRoleEntity() size:" + roleEntityMapList.size());
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectDistinctPermissionBasedRoleEntity():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectDistinctPermissionBasedRoleEntity():" + e.getMessage());
        }
        return roleEntityMapList;
    }
      //============================================= Get Permissions based on Role, Entity & Privilege =========================================          
     public ArrayList selectPermissionBasedRoleEntityPrivilege(String roleId, String entityId, String privilegeId){
        Debug.print("RoleManagementDAOImpl.selectPermissionBasedRoleEntityPrivilege():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT A.role_name, B.entity_name, C.privilege_name, D.permission_name, E.privilege_id, E.permission_id  from " + DBHelper.USEA_ROLE_ROLE_MASTER 
                    + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                    DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E " +
                    " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and  " +
                    " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and " +
                    " E.role_id = ? and E.entity_id = ? and E.privilege_id = ? order by C.privilege_name" ;
         
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,entityId);
            prepStmt.setString(3,privilegeId);
            
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String roleName = rs.getString(1);
                String entityName = rs.getString(2);
                String privilegeName = rs.getString(3);
                String permissionName = rs.getString(4);
                String privilegeIdVal = rs.getString(5);
                String permissionId = rs.getString(6);
                String[] entMapList = {roleName, entityName, privilegeName, permissionName, privilegeIdVal, permissionId};
                roleEntityMapList.add(entMapList);
            }
  
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectPermissionBasedRoleEntityPrivilege():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectPermissionBasedRoleEntityPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectPermissionBasedRoleEntityPrivilege():" + e.getMessage());
        }
        return roleEntityMapList;
    }
     //============================================= Get Menu based on Role, Entity & Privilege =========================================      
   public ArrayList selectMenuBasedOnRoleEntityPrivilege(String roleId, String entityId, String privilegeId){
        Debug.print("RoleManagementDAOImpl.selectMenuBasedOnRoleEntityPrivilege():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT A.role_name, B.entity_name, C.privilege_name, D.permission_name, F.access_name, F.access_url, F.permission_id, F.map_permission_id FROM " +
                    DBHelper.USEA_ROLE_ROLE_MASTER + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                    DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " F " + 
                    " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and   " +
                    " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and  " +
                    " F.privilege_id = E.privilege_id and F.permission_id = E.permission_id and " + 
                    //For Debug starts
                    //" E.role_id = ? and E.entity_id = ? and E.privilege_id = ? order by D.permission_name" ;
                    " E.role_id = ? and E.entity_id = ? order by "
                    //+ " D.permission_name" ;
                    + " C.privilege_name " ;
                    //For Debug Ends
                    System.out.println("entity id is "+entityId);
                    if(entityId== null ){
                        entityId="";
                    }
                    if(entityId.equals("")){
                         System.out.println("inside if loop ");
                        String selectStatementEntity=" select entity_id from " + DBHelper.USEA_ROLE_ENTITY_MASTER +" where entity_name ='Accounting' ";
                        Debug.print(selectStatementEntity);
                        stmt=conn.createStatement();
                        rs1=stmt.executeQuery(selectStatementEntity);
                         String entityIdNL ="";
                         while(rs1.next()){
                             entityIdNL=rs1.getString(1);
                         }
                         entityId=entityIdNL;
                          System.out.println("entity id after assigning is "+entityId);
                    }

            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,entityId);
            //prepStmt.setString(3,privilegeId);
            Debug.print(selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String roleName = rs.getString(1);
                String entityName = rs.getString(2);
                String privilegeName = rs.getString(3);
                String permissionName = rs.getString(4);
                String accessName = rs.getString(5);
                String accessUrl = rs.getString(6);
                String permissionId = rs.getString(7);
                String mapPermissionId = rs.getString(8);
                
                HLCMenuListVO menuVO = new HLCMenuListVO();
                menuVO.setRoleId(roleId);
                menuVO.setEntityId(entityId);
                menuVO.setPrivilegeId(privilegeId);
                menuVO.setPermissionId(permissionId);
                menuVO.setRoleName(roleName);
                menuVO.setEntityName(entityName);
                menuVO.setPrivilegeName(privilegeName);
                menuVO.setPermissionName(permissionName);
                menuVO.setAccessName(accessName);
                menuVO.setAccessUrl(accessUrl);
                menuVO.setMapPermissionId(mapPermissionId);
                roleEntityMapList.add(menuVO);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectMenuBasedOnRoleEntityPrivilege():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectMenuBasedOnRoleEntityPrivilege():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectMenuBasedOnRoleEntityPrivilege():" + e.getMessage());
        }
        return roleEntityMapList;
    }
 //=============================================  User Map with Roles details=========================================      
      public boolean insertUserWithRoleMapping(String userId, String roleId) {
        Debug.print("RoleManagementDAOImpl.insertUserWithRoleMapping():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try {
            String insertStatement = "insert into " + DBHelper.USEA_USER_MAP_PRIVILEGE + " (user_id , role_id) " +
                    " values( ? , ? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, userId);
            prepStmt.setString(2, roleId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.insertUserWithRoleMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.insertUserWithRoleMapping():" + e.getMessage());
        }
        return result;
    }
  
      public ArrayList selectAllRolesBasedOnUser(String userId){
        Debug.print("RoleManagementDAOImpl.selectAllRolesBasedOnUser():");
        ArrayList rolesList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement = " SELECT A.user_map_id, A.user_id, A.role_id, B.role_name from " + DBHelper.USEA_USER_MAP_PRIVILEGE + " A, " + DBHelper.USEA_ROLE_ROLE_MASTER + " B  where A.role_id = B.role_id and A.user_id = ? order by B.role_name" ;
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,userId);
            rs = prepStmt.executeQuery();
            rolesList = new ArrayList();
            while(rs.next()){
                String userMapId = rs.getString(1);
                String userIdVal = rs.getString(2);
                String roleId = rs.getString(3);
                String roleName = rs.getString(4);
                String[] rlList = {userMapId, userIdVal, roleId, roleName};
                rolesList.add(rlList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectAllRolesBasedOnUser():" + rolesList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllRolesBasedOnUser():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllRolesBasedOnUser():" + e.getMessage());
        }
        return rolesList;
      }
     
   
  //=============================================Delete Mapping Records with fieldValue details========================================= 
     public boolean deleteRow(String fieldName, String fieldValue, String tableName,String deptId) {
        Debug.print("RoleManagementDAOImpl.deleteRow()" +deptId);
        boolean result = false;
        makeConnection();
        try{
        	/*user entity mapping commented*/
        	/*if(deptId.equalsIgnoreCase(null) && deptId.equalsIgnoreCase("")){
            String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? ";
            Debug.print("RoleManagementDAOImpl.deleteRow():if" + "\n" + deleteStatement + ":" +  fieldValue);
            PreparedStatement prepStmt = conn.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        	}
        	else
        	{
        		  String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? and dept_id = ? ";
                  Debug.print("RoleManagementDAOImpl.deleteRow()else:" + "\n" + deleteStatement + ":" +  fieldValue);
                  PreparedStatement prepStmt = conn.prepareStatement(deleteStatement);
                  prepStmt.setString(1, fieldValue);
                  prepStmt.setString(2, deptId);
                  prepStmt.executeUpdate();
                  prepStmt.close();
                  releaseConnection(conn);
                  result = true;
        	}*/
        	
        	  String deleteStatement = "delete from " + tableName + "  where " + fieldName  + " = ? ";
              Debug.print("RoleManagementDAOImpl.deleteRow():" + "\n" + deleteStatement + ":" +  fieldValue);
              PreparedStatement prepStmt = conn.prepareStatement(deleteStatement);
              prepStmt.setString(1, fieldValue);
              prepStmt.executeUpdate();
              prepStmt.close();
              releaseConnection(conn);
              result = true;
        }
        catch(SQLException sql){
           releaseConnection(conn);
           Debug.print("SQL Exception in deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in deleteRow:" + e.getMessage());
        }        
        return result;
    }
//=============================================Delete Mapping Records with fieldValue details========================================= 
     public boolean deleteRow(String fieldName1, String fieldValue1, String fieldName2, String fieldValue2, String tableName) {
        Debug.print("RoleManagementDAOImpl.deleteRow()");
        boolean result = false;
        makeConnection();
        try{
            String deleteStatement = "delete from " + tableName + "  where " + fieldName1  + " = ? and " + fieldName2 + " = ?" ;
            PreparedStatement prepStmt = conn.prepareStatement(deleteStatement);
            prepStmt.setString(1, fieldValue1);
            prepStmt.setString(2, fieldValue2);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        }
        catch(SQLException sql){
           releaseConnection(conn);
           Debug.print("SQL Exception in deleteRow:" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in deleteRow:" + e.getMessage());
        }        
        return result;
    }
     
        
//=============================================Access Name Checking details=========================================      
    public boolean isAccessNameExist(String accessName) {
        Debug.print("RoleManagementDAOImpl.isAccessNameExist():" + accessName);
        boolean result = true;
        makeConnection();
   	try {
            String selectStatement = "select map_permission_id from " + DBHelper.USEA_ROLE_MAP_PERMISSION + " where access_name = ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,accessName);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from accessName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isAccessNameExist:" + e.getMessage());
        }
        Debug.print("isAccessNameExist():" + result);
        return result;
    }

    //=============================================Access Name Checking while editing Mapping Details =========================================      
    public boolean isAccessNameExistinEdit(String mapPermissionId, String accessName,String privId, String permissionId) {
        Debug.print("RoleManagementDAOImpl.isAccessNameExistinEdit():" + accessName);
        boolean result = true;
        String selectStatement=null;
        PreparedStatement prepStmt=null;
        ResultSet rs=null;
        makeConnection();
   	try {
   		 	
   		if(!mapPermissionId.equals("") && !accessName.equals("")) {
   			
   			selectStatement = "select map_permission_id from " + DBHelper.USEA_ROLE_MAP_PERMISSION + " where access_name = ? and map_permission_id != ?" ;
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,accessName);
            prepStmt.setString(2,mapPermissionId);
            rs = prepStmt.executeQuery();
            if (rs.next()){
                result = false;
            }
   		}
            
            if(!privId.equals("") && !permissionId.equals("")) {            	

	            selectStatement = "select count(1) from " + DBHelper.USEA_ROLE_MAP_PERMISSION + " where privilege_id = ?" ;
	       		prepStmt = conn.prepareStatement(selectStatement);
	       		prepStmt.setString(1,privId);
	       		
	       		rs = prepStmt.executeQuery();
	       		
	            if (rs.next()){
	                int recCnt = rs.getInt(1);
	                    if(recCnt==12){
	                    Debug.print("If Inside recCnt==="+recCnt);
	                    result=true;
	                }
	                else {
	                	 Debug.print("else Inside recCnt==="+recCnt);
	                	result = false;
	                }
	            }
            }
            
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from accessName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isAccessNameExistinEdit:" + e.getMessage());
        }
        Debug.print("isAccessNameExistinEdit():" + result);
        return result;
    }
   

    
    public String selectUserIdBasedOnUserName(String userName)
    {
        Debug.print("RoleManagementDAOImpl.selectUserIdBasedOnUserName():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String userId = null;
        try
        {
            String sqlQuery = "select user_id from tblusermaster where user_name=?";
            prepStmt = conn.prepareStatement(sqlQuery);
            prepStmt.setString(1, userName);
            rs = prepStmt.executeQuery();
            rs.next();
            userId = rs.getString(1);
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.selectUserIdBasedOnUserName():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.selectUserIdBasedOnUserName():").append(e.getMessage()).toString());
        }
        return userId;
    }
    
    public boolean insertCvsUser(String user_name, String password)
    {
        Debug.print("RoleManagementDAOImpl.insertCvsUser():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try
        {
            String insertStatement = "insert into tblMfgCVSUsers (user_name,password) values( ?,? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, user_name);
            prepStmt.setString(2, password);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertCvsUser():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertCvsUser():").append(e.getMessage()).toString());
        }
        return result;
    }

    public List SelectAllPermissionsBasedOnRole(String roleId)
    {
        Debug.print("RoleManagementDAOImpl.SelectAllPermissionsBasedOnRole():");
        List permissionsList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        try
        {
            String selectStatement = "SELECT distinct A.role_name, B.entity_name, C.privilege_name, E.domain_name, F.process_name, G.application_name  from  tblMfgMapRole D left join tblMfgRoleMaster A on D.role_id = A.role_id left join tblMfgEntityMaster B on D.entity_id = B.entity_id  left join tblMfgPrivilegeMaster C on D.privilege_id = C.privilege_id left join tblMfgDomainMaster E on D.domain_id = E.domain_id  left join tblMfgProcessMaster F on D.process_id = F.process_id left join tblMfgApplicationMaster G on D.application_id = G.application_id WHERE D.role_id=? order by A.role_name, B.entity_name, C.privilege_name, E.domain_name, F.process_name, G.application_name";
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1, roleId);
            rs = prepStmt.executeQuery();
            permissionsList = new ArrayList();
            String roleMapList[];
            for(; rs.next(); permissionsList.add(roleMapList))
            {
                String roleName = rs.getString(1);
                String entityName = rs.getString(2);
                String privilegeName = rs.getString(3);
                String domainName = rs.getString(4);
                String processName = rs.getString(5);
                String applicationName = rs.getString(6);
                roleMapList = (new String[] {
                    roleName, entityName, privilegeName, domainName, processName, applicationName
                });
            }

            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("RoleManagementDAOImpl.SelectAllPermissionsBasedOnRole() size:").append(permissionsList.size()).toString());
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.SelectAllPermissionsBasedOnRole():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.SelectAllPermissionsBasedOnRole():").append(e.getMessage()).toString());
        }
        return permissionsList;
    }
    
    //==================Artifact CRUD================================================
    
    public ArrayList selectAllSubArtifacts(String mapPerId){
        Debug.print("RoleManagementDAOImpl.selectAllSubArtifacts():");
        ArrayList rolesList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {

 String selectStatement = " SELECT artifact_id, artifact_name from " + DBHelper.USEA_ARTIFACT_MASTER + " where map_permission_id=? order by artifact_name";
             
 //String accessName=selectPermissionAccessName(mapPerId);
 prepStmt = conn.prepareStatement(selectStatement);
 prepStmt.setString(1, mapPerId);
 rs = prepStmt.executeQuery();
            rolesList = new ArrayList();
            while(rs.next()){
                String artifactId = rs.getString(1);
                String artifactName = rs.getString(2);
                String[] rlList = {artifactId, artifactName};
                rolesList.add(rlList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           // Debug.print("RoleManagementDAOImpl.selectAllRole():" + rolesList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllSubArtifacts():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllSubArtifacts():" + e.getMessage());
        }
        return rolesList;
    }
    
    
    public String selectPermissionAccessName(String mapPerId)
    {
        Debug.print("RoleManagementDAOImpl.selectPermissionAccessName():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String access_name = "";
        try
        {
            String sqlQuery = "select access_name from tblMapPermission where map_permission_id=?";
            prepStmt = conn.prepareStatement(sqlQuery);
            prepStmt.setString(1, mapPerId);
            rs = prepStmt.executeQuery();
            rs.next();
            access_name = rs.getString(1);
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.selectUserIdBasedOnUserName():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.selectUserIdBasedOnUserName():").append(e.getMessage()).toString());
        }
        return access_name;
    }
    
    public String selectSubArtifactName(String subArtifactId)
    {
        Debug.print("RoleManagementDAOImpl.selectSubArtifactName():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String subArtifactName = "";
        try
        {
            String sqlQuery = "select artifact_name from tblArtifactMaster where artifact_id=?";
            prepStmt = conn.prepareStatement(sqlQuery);
            prepStmt.setString(1, subArtifactId);
            rs = prepStmt.executeQuery();
            rs.next();
            subArtifactName = rs.getString(1);
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.selectSubArtifactName():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.selectSubArtifactName():").append(e.getMessage()).toString());
        }
        return subArtifactName;
    }
    
   
    public boolean insertSubArtifact(String mainArtiname,String subArtiName,String mainArtiId)
    {
        Debug.print("RoleManagementDAOImpl.insertSubArtifact():");
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try
        {
            String insertStatement = "insert into tblArtifactMaster (map_permission_id,artifact_name) values( ?,? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, mainArtiId);
            prepStmt.setString(2, subArtiName);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertSubArtifact():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertSubArtifact():").append(e.getMessage()).toString());
        }
        return result;
    }
    
    
    public boolean isSubArtifactNameExist(String subArtifact) {
        Debug.print("RoleManagementDAOImpl.isSubArtifactNameExist():" + subArtifact);
        boolean result = false;
        makeConnection();
   	try {
            String selectStatement = "select artifact_id from tblArtifactMaster where artifact_name = ? " ;
            PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,subArtifact);
            ResultSet rs = prepStmt.executeQuery();
            if (rs.next()){
                result = true;
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch (SQLException e) {
            releaseConnection(conn);
            Debug.print("Could not find any from subArtifactName" + e.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in isSubArtifactNameExist:" + e.getMessage());
        }
        Debug.print("isSubArtifactNameExist():" + result);
        return result;
    }    
    
    
    public boolean isSubArtifactNameEditExist(String artifactId, String subArtifact) {
     
            Debug.print("RoleManagementDAOImpl.isSubArtifactNameEditExist():" + subArtifact);
            Debug.print("RoleManagementDAOImpl.isSubArtifactNameEditExist()&&&&:" + artifactId);
            boolean result = false;
            makeConnection();
       	try {
                String selectStatement = "select artifact_id from tblArtifactMaster where artifact_name = ? and artifact_id != ? " ;
                PreparedStatement prepStmt = conn.prepareStatement(selectStatement);
                prepStmt.setString(1,subArtifact);
                prepStmt.setString(2,artifactId);
                
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()){
                    result = true;
                }
                
                rs.close();
                prepStmt.close();
                releaseConnection(conn);
                Debug.print("isSubArtifactNameEditExist result:" + result);  
            } 
            catch (SQLException e) {
                releaseConnection(conn);
                Debug.print("Could not find any from isSubArtifactNameEditExist" + e.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn);
                Debug.print("General Exception  in isSubArtifactNameEditExist:" + e.getMessage());
            }
            Debug.print("isSubArtifactNameEditExist():" + result);
            return result;
        } 
    
    
    
    public boolean updateArtifact(String artifactId, String subArtifact) {
       
            Debug.print("RoleManagementDAOImpl.updateArtifact():");
            PreparedStatement prepStmt = null;
            makeConnection();
            try {
                String insertStatement = "update tblArtifactMaster set artifact_name = ? where artifact_Id = ?";
    	
                    prepStmt = conn.prepareStatement(insertStatement);
                    prepStmt.setString(1, subArtifact);        
                     prepStmt.setString(2, artifactId);
                  
                  
                    prepStmt.executeUpdate();
                    prepStmt.close();
               
                releaseConnection(conn);
            }
            
            catch(SQLException sql){
                releaseConnection(conn);
                Debug.print("SQL Exception in RoleManagementDAOImpl.updateArtifact():" + sql.getMessage());
            }
            catch(Exception e){
                releaseConnection(conn);
                Debug.print("General Exception  in RoleManagementDAOImpl.updateArtifact():" + e.getMessage());
            }
            return true;
        }
    
    
    
    public String selectViewName(String viewId)
    {
        Debug.print("RoleManagementDAOImpl.selectViewName():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String viewName = "";
        try
        {
            String sqlQuery = "select privilege_name from tblPrivilegeMaster where privilege_id=?";
            prepStmt = conn.prepareStatement(sqlQuery);
            prepStmt.setString(1, viewId);
            rs = prepStmt.executeQuery();
            rs.next();
            viewName = rs.getString(1);
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.selectViewName():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.selectViewName():").append(e.getMessage()).toString());
        }
        return viewName;
    }
    
    
    //=============Dhivya Here: Manage Artifact Mappings==================================================
    
    public ArrayList selectAllGroup(){
  	  System.out.println("RoleManagementDAOImpl: selectAllGroupDetails()");
        ArrayList groupList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {

  String selectStatement = "select master_id, master_name, master_sequence from tblframeworkmaster " +
  		"where active_status='1' and show_master='1' order by master_sequence";
                
  prepStmt = conn.prepareStatement(selectStatement);
  rs = prepStmt.executeQuery();
           
            while(rs.next()){
                String masterId = rs.getString(1);
                String masterName = rs.getString(2);
                String masterSeq = rs.getString(3);
                
                String[] grpList = {masterId, masterName,masterSeq};
                groupList.add(grpList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            System.out.println("SQL Exception in RoleManagementDAOImpl.selectAllGroup():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            e.printStackTrace();
        }
        return groupList;
    }
  
  
  public ArrayList selectMapViewPointList(String viewPointId){
  	  System.out.println("RoleManagementDAOImpl: selectMapViewPointList()");
        ArrayList viewPointList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {

  String selectStatement = "select (select view_point_name from tblviewpointmaster where view_point_id = a.view_point_value_id ) as View_Point, " +
  		" (select layer_value from tblframeworklayermap where layer_value_id = a.lob_value_id) as LOB,(select layer_value from tblframeworklayermap " +
  		" where layer_value_id =a.view_id) as Views,(select layer_value from tblframeworklayermap where layer_value_id = a.group_value_id) " +
  		" as GroupId,(select layer_value from tblframeworklayermap where layer_value_id = a.process_domain_value_id)  as Process_Domain_Id " +
  		" from tblframeworkmapping a,tblviewpointmaster b where a.view_point_value_id = b.view_point_id and a.view_point_value_id = ?";                
  prepStmt = conn.prepareStatement(selectStatement);
  prepStmt.setString(1, viewPointId);
 
  rs = prepStmt.executeQuery();
           
            while(rs.next()){
                String View_Point = rs.getString(1);
                String LOB = rs.getString(2);
                String Views = rs.getString(3);
                String Groups = rs.getString(4);
                String Process_Domain = rs.getString(5);
          
                String[] viewPoint = {View_Point,LOB,Views, Groups,Process_Domain};
                viewPointList.add(viewPoint);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            System.out.println("SQL Exception in RoleManagementDAOImpl.selectMapViewPointList():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            e.printStackTrace();
        }
        return viewPointList;
    }
    
  public ArrayList selectGrpDetails(){
	  System.out.println("RoleManagementDAOImpl: selectGrpDetails()");
      ArrayList groupList = new ArrayList();
      PreparedStatement prepStmt = null;
      ResultSet rs = null;
      makeConnection();
 	try {

String selectStatement = "select b.master_id, b.master_name,a.layer_value,a.layer_value_id, b.master_sequence from tblframeworklayermap a, " +
		"tblframeworkmaster b where a.master_id = b.master_id " +
		"and b.active_status='1' and b.show_master='1' order by b.master_sequence";
              
prepStmt = conn.prepareStatement(selectStatement);

rs = prepStmt.executeQuery();
         
          while(rs.next()){
              String masterId = rs.getString(1);
              String masterName = rs.getString(2);
              String layerVal = rs.getString(3);
              String layerValId = rs.getString(4);
              Integer seq = rs.getInt(5);
        
              String[] grpList = {masterId,masterName,layerVal, layerValId,String.valueOf(seq)};
              groupList.add(grpList);
          }
          rs.close();
          prepStmt.close();
          releaseConnection(conn);
         
      } 
      catch(SQLException sql){
          releaseConnection(conn);
          System.out.println("SQL Exception in RoleManagementDAOImpl.selectGrpDetails():" + sql.getMessage());
      }
      catch(Exception e){
          releaseConnection(conn);
          e.printStackTrace();
      }
      return groupList;
  }
  
  
  public ArrayList selectCount(){
      int cnt = 0;
      ArrayList cntRecord = new ArrayList();
      PreparedStatement prepStmt = null;
      String selectStatement="";
      makeConnection();
      System.out.println("RoleManagementDAOImpl: getCount()");
      try {
    
               selectStatement = "select count(*) from tblFrameworkMaster";
              
              prepStmt = conn.prepareStatement(selectStatement);
             
              ResultSet rs = prepStmt.executeQuery();
              if(rs.next()){
                  cnt = rs.getInt(1);
                  
                   selectStatement = "select A.layer_value_id, A.layer_value, B.master_name, B.master_id from " +
                  		"tblframeworklayermap A, tblframeworkmaster B where " +
                  		"A.master_id=B.master_id and master_sequence=?";
                   
                   prepStmt = conn.prepareStatement(selectStatement);
                   prepStmt.setInt(1, cnt);
                   rs = prepStmt.executeQuery();
                   
                   while(rs.next()){
                  	 
                  	 String layerValId = rs.getString(1);
                       String layerValName = rs.getString(2);
                       String masterId = rs.getString(3);
                       String masterName = rs.getString(4);
                       String[] cntList = {layerValId, layerValName,masterId,masterName,String.valueOf(cnt)};
                       cntRecord.add(cntList);	 
                  	 
                   }
              }
              rs.close();
              prepStmt.close();
         
          releaseConnection(conn);
          
      } catch(SQLException sql){
          releaseConnection(conn);
          Debug.print("SQL Exception in EventEntryrDAO.getCount():" + sql.getMessage());
      } catch(Exception e){
          releaseConnection(conn);
          Debug.print("General Exception  in EventEntryrDAO.getCount():" + e.getMessage());
      }
      return cntRecord;
  }
    
    public ArrayList selectViewsBasedOnViewPoint(String viewPointId){
    	  System.out.println("RoleManagementDAOImpl: selectViewsBasedOnViewPoint()");
          ArrayList viewList = new ArrayList();
          PreparedStatement prepStmt = null;
          ResultSet rs = null;
          makeConnection();
     	try {

    String selectStatement = "select a.layer_value_id,a.layer_value from tblframeworklayermap a,tblviewpointmaster b where a.view_point_id=b.view_point_id and a.view_point_id = ?";
               
    
    prepStmt = conn.prepareStatement(selectStatement);
    prepStmt.setString(1, viewPointId);

    rs = prepStmt.executeQuery();
             
              while(rs.next()){
                  String layerId = rs.getString(1);
                  String layerName = rs.getString(2);
                  String[] viList = {layerId, layerName};
                  viewList.add(viList);
              }
              rs.close();
              prepStmt.close();
              releaseConnection(conn);
             
          } 
          catch(SQLException sql){
              releaseConnection(conn);
              System.out.println("SQL Exception in RoleManagementDAOImpl.selectViewsBasedOnViewPoint():" + sql.getMessage());
          }
          catch(Exception e){
              releaseConnection(conn);
              e.printStackTrace();
          }
          return viewList;
      }

    public ArrayList selectAllViews(){
  	  System.out.println("RoleManagementDAOImpl: selectAllViews()");
        ArrayList viewList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {

  String selectStatement = " SELECT view_point_id, view_point_name from tblviewpointmaster order by view_point_name";
             
  
  prepStmt = conn.prepareStatement(selectStatement);

  rs = prepStmt.executeQuery();
           
            while(rs.next()){
                String viewId = rs.getString(1);
                String viewName = rs.getString(2);
                String[] viList = {viewId, viewName};
                viewList.add(viList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            System.out.println("SQL Exception in RoleManagementDAOImpl.selectAllViews():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            e.printStackTrace();
        }
        return viewList;
    }

    public Vector selectLOBs(String viewPntId){
    	  System.out.println("RoleManagementDAOImpl: selectLOBs()");
    	Vector lobsLst = new Vector();
          PreparedStatement prepStmt = null;
          ResultSet rs = null;
          makeConnection();
     	try {

    String selectStatement = " select A.lob_value_id, B.layer_value from tblframeworkmapping A, tblFrameworkLayerMap B where " +
    		"A.lob_value_id=B.layer_value_id and A.view_point_value_id=?";
               
   
    prepStmt = conn.prepareStatement(selectStatement);
    prepStmt.setString(1, viewPntId);
    rs = prepStmt.executeQuery();
             
              while(rs.next()){
                  String lobValueId=rs.getString(1);
                  String lobValueName= rs.getString(2);
                                
                  String[] lobList = {lobValueId, lobValueName};
                  lobsLst.add(lobList);
              }
              rs.close();
              prepStmt.close();
              releaseConnection(conn);
             
          } 
          catch(SQLException sql){
              releaseConnection(conn);
              System.out.println("SQL Exception in RoleManagementDAOImpl.selectLOBs():" + sql.getMessage());
          }
          catch(Exception e){
              releaseConnection(conn);
              System.out.println("General Exception  in RoleManagementDAOImpl.selectLOBs():" + e.getMessage());
          }
          return lobsLst;
      }

    public Vector selectViews(String viewPntId, String lobId){
    	  System.out.println("RoleManagementDAOImpl: selectViews()");
    	  Vector viewObj = new Vector();
          PreparedStatement prepStmt = null;
          ResultSet rs = null;
          String selectStatement="";
          makeConnection();
     	try {

     		
     		selectStatement = "select A.view_id, B.layer_value from tblframeworkmapping A," +
     				" tblFrameworkLayerMap B where A.view_id=B.layer_value_id and " +
     				"A.view_point_value_id=? and " +
     				"A.lob_value_id=?";
     		
     		 prepStmt = conn.prepareStatement(selectStatement);
     		 prepStmt.setString(1, viewPntId);
     		 prepStmt.setString(2, lobId);

    rs = prepStmt.executeQuery();
             
              while(rs.next()){
                  String viewId = rs.getString(1);
                  String viewName = rs.getString(2);
                  String[] viewList = {viewId, viewName};
                  viewObj.add(viewList);
              }
              rs.close();
              prepStmt.close();
              releaseConnection(conn);
             
          } 
          catch(SQLException sql){
              releaseConnection(conn);
              System.out.println("SQL Exception in RoleManagementDAOImpl.selectViews():" + sql.getMessage());
          }
          catch(Exception e){
              releaseConnection(conn);
              System.out.println("General Exception  in RoleManagementDAOImpl.selectViews():" + e.getMessage());
          }
          return viewObj;
      }
    
    public Vector selectGroups(String viewPntId, String lobId){
  	  System.out.println("RoleManagementDAOImpl: selectGroups()");
  	  Vector grpObj = new Vector();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        String selectStatement="";
        makeConnection();
   	try {

   		
   		selectStatement = "select A.group_value_id, B.layer_value from tblframeworkmapping A, tblFrameworkLayerMap B where " +
   				"A.group_value_id=B.layer_value_id and A.view_point_value_id=? and " +
   				"A.lob_value_id=?";
   		
   		 prepStmt = conn.prepareStatement(selectStatement);
   		prepStmt.setString(1, viewPntId);
		 prepStmt.setString(2, lobId);
   
  rs = prepStmt.executeQuery();
           
            while(rs.next()){
                String grpId = rs.getString(1);
                String grpName = rs.getString(2);
                String[] grpList = {grpId, grpName};
                grpObj.add(grpList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            System.out.println("SQL Exception in RoleManagementDAOImpl.selectGroups():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            System.out.println("General Exception  in RoleManagementDAOImpl.selectGroups():" + e.getMessage());
        }
        return grpObj;
    }

    public Vector selectProcessDomain(String viewPntId, String lobId, String grpId){
    	  System.out.println("RoleManagementDAOImpl: selectProcessDomain()");
    	  Vector proDomObj = new Vector();
          PreparedStatement prepStmt = null;
          ResultSet rs = null;
          String selectStatement="";
          makeConnection();
     	try {

     		
     		selectStatement = "select A.process_domain_value_id, B.layer_value from tblframeworkmapping A, tblFrameworkLayerMap B where " +
     				"A.process_domain_value_id=B.layer_value_id and A.view_point_value_id=? and " +
     				"A.lob_value_id=? and A.group_value_id=?";
     		
     		 prepStmt = conn.prepareStatement(selectStatement);
     		prepStmt.setString(1, viewPntId);
  		 prepStmt.setString(2, lobId);
  		prepStmt.setString(3, grpId);
     
    rs = prepStmt.executeQuery();
             
              while(rs.next()){
                  String proDomId = rs.getString(1);
                  String proDomName= rs.getString(2);
                  String[] proDomList = {proDomId, proDomName};
                  proDomObj.add(proDomList);
              }
              rs.close();
              prepStmt.close();
              releaseConnection(conn);
             
          } 
          catch(SQLException sql){
              releaseConnection(conn);
              System.out.println("SQL Exception in RoleManagementDAOImpl.selectProcessDomain():" + sql.getMessage());
          }
          catch(Exception e){
              releaseConnection(conn);
              System.out.println("General Exception  in RoleManagementDAOImpl.selectProcessDomain():" + e.getMessage());
          }
          return proDomObj;
      }
    
    public boolean insertArtifactMapDetails(HLCMenuListVO objArti)
    {
        Debug.print("RoleManagementDAOImpl.insertArtifactMapDetails():");
        Debug.print("RoleManagementDAOImpl.insertArtifactMapDetails():"+objArti);
        
        Debug.print("RoleManagementDAOImpl.objArti.getGroupsId()():"+objArti.getGroupsId());
        Debug.print("RoleManagementDAOImpl.objArti.getProcessDomainId()():"+objArti.getProcessDomainId());
        
        PreparedStatement prepStmt = null;
        boolean result = false;
        
     
        makeConnection();
        try
        {
        	
   
        	
      if(objArti.getViewPtId()!=null && objArti.getLobLayerId()==null && objArti.getViewId()==null &&  objArti.getGroupsId()==null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
        	
            String insertStatement = "insert into tblMapArtifact (view_point_value_id, artifact_id, artifact_description) values( ?,?,?)";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, objArti.getViewPtId());         
            prepStmt.setString(2, objArti.getArtifactId());                     
            prepStmt.setString(3, objArti.getArtifactDesc());
      }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()==null && objArti.getGroupsId()==null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
    	  
    	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, artifact_id, artifact_description) values( ?,?,?,?)";
          prepStmt = conn.prepareStatement(insertStatement);
          prepStmt.setString(1, objArti.getViewPtId());
          prepStmt.setString(2, objArti.getLobLayerId());       
          prepStmt.setString(3, objArti.getArtifactId());                     
          prepStmt.setString(4, objArti.getArtifactDesc());  
    	  
      }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()!=null && objArti.getGroupsId()==null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
    	  
    	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, view_value_id, artifact_id, artifact_description) values( ?,?,?,?,?)";
          prepStmt = conn.prepareStatement(insertStatement);
          prepStmt.setString(1, objArti.getViewPtId());
          prepStmt.setString(2, objArti.getLobLayerId());     
          prepStmt.setString(3, objArti.getViewId());
          prepStmt.setString(4, objArti.getArtifactId());                     
          prepStmt.setString(5, objArti.getArtifactDesc());  
    	  
      }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()!=null && objArti.getGroupsId()!=null && objArti.getProcessDomainId()==null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
    	  
    	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, view_value_id, group_value_id, artifact_id, artifact_description) values( ?,?,?,?,?,?)";
          prepStmt = conn.prepareStatement(insertStatement);
          prepStmt.setString(1, objArti.getViewPtId());
          prepStmt.setString(2, objArti.getLobLayerId());     
          prepStmt.setString(3, objArti.getViewId());
          prepStmt.setString(4, objArti.getGroupsId());
          prepStmt.setString(5, objArti.getArtifactId());                     
          prepStmt.setString(6, objArti.getArtifactDesc());  
    	  
      }else if(objArti.getViewPtId()!=null && objArti.getLobLayerId()!=null && objArti.getViewId()!=null && objArti.getGroupsId()!=null && objArti.getProcessDomainId()!=null && objArti.getArtifactId()!=null && objArti.getArtifactDesc()!=null){
    	  
    	  String insertStatement = "insert into tblMapArtifact (view_point_value_id, lob_value_id, view_value_id, group_value_id, process_domain_value_id, artifact_id, artifact_description) values( ?,?,?,?,?,?,?)";
          prepStmt = conn.prepareStatement(insertStatement);
          prepStmt.setString(1, objArti.getViewPtId());
          prepStmt.setString(2, objArti.getLobLayerId());
          prepStmt.setString(3, objArti.getViewId());  
          prepStmt.setString(4, objArti.getGroupsId());  
          prepStmt.setString(5, objArti.getProcessDomainId());  
          prepStmt.setString(6, objArti.getArtifactId());                     
          prepStmt.setString(7, objArti.getArtifactDesc());  
    	  
      }
      
           
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertArtifactMapDetails():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertArtifactMapDetails():").append(e.getMessage()).toString());
        }
        
        Debug.print("RoleManagementDAOImpl.insertArtifactMapDetails():"+result);
        return result;
    }
    
    public boolean artifactMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId, String artifactId)
    {
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+viewPntId);
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+lobId);
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+viewId);
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+grpId);
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+domProcId);
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+artifactId); 
        
        
        PreparedStatement prepStmt = null;
        boolean result = false;
        int cnt=0;
        
        
        if(lobId.equals("")){
        	lobId=null;
        	
        }
        if(viewId.equals("")){
        	viewId=null;
        	
        }
        if(grpId.equals("")){
        	grpId=null;
        	
        }
        
        if(domProcId.equals("")){
        	
        	domProcId=null;
        }
        makeConnection();
        try
        {
        	if(viewPntId!=null && lobId==null && viewId==null && grpId==null && domProcId==null && artifactId!=null){
        	
            String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and artifact_id=?";
            
            prepStmt = conn.prepareStatement(selectStatement);
     		prepStmt.setString(1, viewPntId);  			
  		prepStmt.setString(2, artifactId);
        	}else if(viewPntId!=null && lobId!=null && viewId==null && grpId==null && domProcId==null && artifactId!=null){
        		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
        				"and artifact_id=?";	
        	
        		 prepStmt = conn.prepareStatement(selectStatement);
          		prepStmt.setString(1, viewPntId);
       		 prepStmt.setString(2, lobId);
       		prepStmt.setString(3, artifactId);
       		
        	}
        	else if(viewPntId!=null && lobId!=null && viewId!=null && grpId==null && domProcId==null && artifactId!=null){
        		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
        				"and view_value_id=? and artifact_id=?";	
        	
        		 prepStmt = conn.prepareStatement(selectStatement);
          		prepStmt.setString(1, viewPntId);
       		 prepStmt.setString(2, lobId);
       		 prepStmt.setString(3, viewId);
       		prepStmt.setString(4, artifactId);
       		
        	}
        	else if(viewPntId!=null && lobId!=null && viewId!=null && grpId!=null && domProcId==null && artifactId!=null){
        		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
        				"and view_value_id=? and group_value_id=? and artifact_id=?";	
        	
        		 prepStmt = conn.prepareStatement(selectStatement);
          		prepStmt.setString(1, viewPntId);
       		 prepStmt.setString(2, lobId);
       		 prepStmt.setString(3, viewId);
       		prepStmt.setString(4, grpId);
       		prepStmt.setString(5, artifactId);
       		
        	}else if(viewPntId!=null && lobId!=null && viewId!=null && grpId!=null && domProcId!=null && artifactId!=null){
        		String selectStatement = "select count(*) from tblMapArtifact where view_point_value_id=? and lob_value_id=? " +
        				"and view_value_id=? and group_value_id=? and process_domain_value_id=? and artifact_id=?";	
        	
        		 prepStmt = conn.prepareStatement(selectStatement);
          		prepStmt.setString(1, viewPntId);
       		 prepStmt.setString(2, lobId);
       		prepStmt.setString(3, viewId);
       		prepStmt.setString(4, grpId);
       		prepStmt.setString(5, domProcId);
       		prepStmt.setString(6, artifactId);
        	}
           
           
           
  	   ResultSet rs = prepStmt.executeQuery();
       if(rs.next()){
            cnt = rs.getInt(1);
           if(cnt>0){
        	   result=true;   
           }
       }
              rs.close();
            prepStmt.close();
            releaseConnection(conn);
            
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.artifactMapExists():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.artifactMapExists():").append(e.getMessage()).toString());
        }
        
        Debug.print("RoleManagementDAOImpl.artifactMapExists():"+result);
        return result;
    }
    
 
    
    
    /*
     * 
     * Dhivya Here: View Point Mapping Existence
     * 
     */
    
    public boolean viewPointMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId)
    {
        Debug.print("RoleManagementDAOImpl.viewPointMapExists() View Pnt:"+viewPntId);
        Debug.print("RoleManagementDAOImpl.viewPointMapExists() LOB:"+lobId);
        Debug.print("RoleManagementDAOImpl.viewPointMapExists() View:"+viewId);
        Debug.print("RoleManagementDAOImpl.viewPointMapExists() Grp:"+grpId);
        Debug.print("RoleManagementDAOImpl.viewPointMapExists() ProDom:"+domProcId);
       
        
        
        
        PreparedStatement prepStmt = null;
        boolean result = false;
        int cnt=0;
        
      
        makeConnection();
        try
        {
        	
            String selectStatement = "select count(*) from tblframeworkmapping where view_point_value_id=? and " +
            		"lob_value_id=? and view_id=? and group_value_id=? and process_domain_value_id=?";
            
            prepStmt = conn.prepareStatement(selectStatement);
     		prepStmt.setString(1, viewPntId);  			
  		prepStmt.setString(2, lobId);
  		prepStmt.setString(3,viewId);  			
  		prepStmt.setString(4, grpId);
  		prepStmt.setString(5, domProcId);
  		
                        
  	   ResultSet rs = prepStmt.executeQuery();
       if(rs.next()){
            cnt = rs.getInt(1);
           if(cnt>0){
        	   result=true;   
           }
       }
              rs.close();
            prepStmt.close();
            releaseConnection(conn);
            
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.viewPointMapExists():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.viewPointMapExists():").append(e.getMessage()).toString());
        }
        
        Debug.print("RoleManagementDAOImpl.viewPointMapExists():"+result);
        return result;
    }
    
  //==========================Dhivya Ends Here:=================================================  
  
  
   
  
   
    
    public boolean insertFrameworkMapDetails(String viewPointId,HLCMenuListVO objMap, HLCMenuListVO objArti)
    {
        Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():");
        Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():"+viewPointId);
        
        PreparedStatement prepStmt = null;
        boolean result = false;
        makeConnection();
        try
        {
            String insertStatement = "insert into tblframeworkmapping (view_point_value_id, lob_value_id, view_id, group_value_id, process_domain_value_id) values( ?,?,?,?,?)";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, viewPointId);
            prepStmt.setString(2, objMap.getLobLayerId());
            prepStmt.setString(3, objMap.getViewPtId());
            prepStmt.setString(4, objMap.getGroupsId());
            prepStmt.setString(5, objMap.getProcessDomainId());
          
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
            result = true;
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.insertFrameworkMapDetails():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.insertFrameworkMapDetails():").append(e.getMessage()).toString());
        }
        
        Debug.print("RoleManagementDAOImpl.insertFrameworkMapDetails():"+result);
        return result;
    }
    
  
    //kalai
    public ArrayList getViewPointDetailsList(String userId)
    {
    	ArrayList ViewPointDetailsList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {

  String selectStatement = "select distinct a.view_point_id,a.view_point_name,d.layer_value,d.layer_value_id from tblviewpointmaster a,tblMapUserView b,tblMapArtifact c,tblframeworklayermap d where a.view_point_id=b.view_point_id and a.view_point_id=c.view_point_value_id and c.lob_value_id=d.layer_value_id and b.user_id=?";
                
  prepStmt = conn.prepareStatement(selectStatement);
  prepStmt.setString(1, userId);
  rs = prepStmt.executeQuery();
           
            while(rs.next()){
                String viewId = rs.getString(1);
                String viewName = rs.getString(2);
                String layerVal = rs.getString(3);
                String layerid = rs.getString(4);
                          
                String[] viewpointList = {viewId,viewName,layerVal,layerid};
                ViewPointDetailsList.add(viewpointList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            System.out.println("SQL Exception in RoleManagementDAOImpl.selectGrpDetails():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            e.printStackTrace();
        }
        return ViewPointDetailsList;
    	
    }
	public String gettingViewPoint(String userId)
      {
    	  PreparedStatement prepStmt = null;
          ResultSet rs = null;
          makeConnection();
          String viewPointId="";
      	try {

      	  String selectStatement = "select view_point_id from tblMapUserView where user_id=?";
      	                
      	  prepStmt = conn.prepareStatement(selectStatement);
      	prepStmt.setString(1, userId);
      	  rs = prepStmt.executeQuery();
      	           
      	            while(rs.next()){
      	            	viewPointId = rs.getString(1);      	                
      	            }
      	           
      	           
      	        } 
      	        catch(SQLException sql){
      	          
      	            System.out.println("SQL Exception in RoleManagementDAOImpl.gettingViewPoint():" + sql.getMessage());
      	        }
      	        catch(Exception e){
      	           
      	            e.printStackTrace();
      	        }
      	        return viewPointId ;
      }
    
    public ArrayList getViewPointArtifactList(String userId,String view_point_id,String lobid,String viewId,String groupId,String processId)
    {
    	
    	System.out.println("RoleManagementDAOImpl: getViewPointArtifactList()");
        ArrayList groupList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String selectStatement="";
        String viewPointId=gettingViewPoint(userId);
        System.out.println("viewPointId--------------"+viewPointId);
   	try {

   selectStatement = "select a.view_point_value_id,b.layer_value,a.artifact_description from tblmapartifact a, tblframeworklayermap b where a.artifact_id=b.layer_value_id and a.view_point_value_id='"+viewPointId+"'";
           
  // prepStmt.setString(1, viewPointId);
   if(lobid!=null){
	   selectStatement=selectStatement+" and lob_value_id='"+lobid+"'";
	  	  
   }if(viewId!=null)
   {	   selectStatement=selectStatement+" and view_value_id='"+viewId+"'";
	 
   }if(groupId!=null)
   {
	   selectStatement=selectStatement+" and group_value_id='"+groupId+"'";	   
   }if(processId!=null)
   {
	   selectStatement=selectStatement+" and process_domain_value_id='"+processId+"'";	   
   }
 
  System.out.println("selectStatement-------------------"+selectStatement);
  prepStmt = conn.prepareStatement(selectStatement);  
  rs = prepStmt.executeQuery();
           
            while(rs.next()){
                String view_point_value_id = rs.getString(1);
                String layer_value = rs.getString(2);
                String artifact_description = rs.getString(3);
             
          
                String[] grpList = {view_point_value_id,layer_value,artifact_description};
                groupList.add(grpList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
           
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            System.out.println("SQL Exception in RoleManagementDAOImpl.getViewPointArtifactList():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            e.printStackTrace();
        }
        return groupList;
    }
    
    //kalai
    
    public boolean chgUserStatByAdmin(String uid)
    {
    	boolean chgStat = false;
    	  PreparedStatement prepStmt = null;
          ResultSet rs = null;
          makeConnection();
          try
          {
        	  String updateStatement = "update tblusermaster set active_status = ? , request_status = 'false' where user_id = ? ";
        	  prepStmt=conn.prepareStatement(updateStatement);
        	  prepStmt.setBoolean(1, true);
        	  prepStmt.setString(2, uid);
        	  int count=prepStmt.executeUpdate();
        	  if(count>=1)
        	  {
        		  chgStat=true;
        	  }
        	  prepStmt.close();
        	  releaseConnection(conn);
        	  
        	  
          }
          catch(SQLException e)
          {
        	  Debug.println("SQLException in chgUserStatByAdmin"+e);
        	  
          }
    	return chgStat;
    		
    }
    public boolean chgUserStatBySup(String uid,String supName,String roleId)
    {
    	boolean chgStat = false;
    	  PreparedStatement prepStmt = null;
          ResultSet rs = null;
          makeConnection();
          try
          {
        	  String updateStatement = "update tblmapsupuserdetails set active_status = ? ,role_id = ? where user_id = ? and sup_name = ? ";
        	  prepStmt=conn.prepareStatement(updateStatement);
        	  prepStmt.setBoolean(1, true);
        	  prepStmt.setString(2, roleId);
        	  prepStmt.setString(3, uid);
        	  prepStmt.setString(4, supName);
        	  int count=prepStmt.executeUpdate();
        	  if(count>=1)
        	  {
        		  chgStat=true;
        	  }
        	  prepStmt.close();
        	  releaseConnection(conn);
        	  
        	  
          }
          catch(SQLException e)
          {
        	  Debug.println("SQLException in chgUserStatByAdmin"+e);
        	  
          }
    	return chgStat;
    		
    }
    
    public String getExternalAppURL(String perName)
    {
        Debug.print("RoleManagementDAOImpl.getExternalAppURL():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String perUrl = "";
        try
        {
            String sqlQuery = "select access_url from tblmappermission where access_name=?";
            prepStmt = conn.prepareStatement(sqlQuery);
            prepStmt.setString(1, perName);
            rs = prepStmt.executeQuery();
            rs.next();
            perUrl = rs.getString(1);
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
        }
        catch(SQLException sql)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("SQL Exception in RoleManagementDAOImpl.getExternalAppURL():").append(sql.getMessage()).toString());
        }
        catch(Exception e)
        {
            releaseConnection(conn);
            Debug.print((new StringBuilder()).append("General Exception  in RoleManagementDAOImpl.getExternalAppURL():").append(e.getMessage()).toString());
        }
        return perUrl;
    }
    
    public boolean insertUserRoleEntityMapping(String roleId, String entityId,String deptId,String userId,String tableName) {
        Debug.print("RoleManagementDAOImpl.insertUserRoleEntityMapping():");
        PreparedStatement prepStmt = null;
        makeConnection();
        try {
            String insertStatement = "insert into "+ tableName +" (role_id , entity_id, dept_id, user_id) " +
                    " values( ? , ? , ?, ? )";
            prepStmt = conn.prepareStatement(insertStatement);
            prepStmt.setString(1, roleId);
            prepStmt.setString(2, entityId);
            prepStmt.setString(3, deptId);
            prepStmt.setString(4, userId);
            prepStmt.executeUpdate();
            prepStmt.close();
            releaseConnection(conn);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.insertUserRoleEntityMapping():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.insertUserRoleEntityMapping():" + e.getMessage());
        }
        return true;
    }
    
    
    public ArrayList selectAllUserMappingDetailsForRole(String roleId,String userId){
        Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():");
        ArrayList roleEntityMapList = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT a.map_user_entity_id , a.role_id ,a.entity_id, b.role_name, c.entity_name,c.entity_access_url from tblmapuserentitytemp a , " + DBHelper.USEA_ROLE_ROLE_MASTER + " b , " + DBHelper.USEA_ROLE_ENTITY_MASTER + " c ," + 
   	       " tblusermaster d where a.entity_id = c.entity_id and a.role_id = b.role_id  and a.user_id = d.user_id and a.role_id = ? and a.user_id = ? order by c.entity_name";
         
            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            prepStmt.setString(2,userId);
            Debug.print("selectStatement selectAllUserMappingDetailsForRole====="+selectStatement);
            rs = prepStmt.executeQuery();
            roleEntityMapList = new ArrayList();
            while(rs.next()){
                String mapEntityId = rs.getString(1);
                String roleIdVal = rs.getString(2);
                String entityId = rs.getString(3);
                String roleName = rs.getString(4);
                String entityName = rs.getString(5);
                String entityUrl = rs.getString(6);
                String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName,entityUrl};
                roleEntityMapList.add(entMapList);
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + roleEntityMapList);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.selectAllMappingDetailsForRole():" + e.getMessage());
        }
        return roleEntityMapList;
    }
    
    public boolean chkUserMapRoleEntity(String roleId,String userId)
    {
    	boolean chkStat = false;
    	  PreparedStatement prepStmt = null;
          ResultSet rs = null;
          makeConnection();
          try
          {
        	  String selectStatement = "select count(*) from tblMapUserEntity where role_id = ? and user_id = ? ";
        	  prepStmt=conn.prepareStatement(selectStatement);
        	
        	  prepStmt.setString(1, roleId);
        	  prepStmt.setString(2, userId);
        	  Debug.print("chkUserMapRoleEntity selectStatement===="+selectStatement);
        	  rs=prepStmt.executeQuery();
        	  
        	  if(rs.next())
        	  { 
        		  int count=rs.getInt(1);
        		  Debug.print("inside chkUserMapRoleEntity rs "+count);
        	     if(count>=1)
        	     {
        		 
        		  chkStat=true;
        	     }
        	  }
        	  rs.close();
        	  prepStmt.close();
        	  releaseConnection(conn);
        	  
        	  
          }
          catch(SQLException e)
          {
        	  Debug.print("SQLException in chkUserMapRoleEntity"+e);
        	  
          }
    	return chkStat;
    		
    }
    
    public String getSupervisorEmailByEmpId(String uId)
    {
  	  PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
        String email="";
    	try {

    	  String selectStatement = "select rept_supervisor_email_id from tblOrganizationalPositionMapping where user_id=?";
    	                
    	  prepStmt = conn.prepareStatement(selectStatement);
    	prepStmt.setString(1, uId);
    	  rs = prepStmt.executeQuery();
    	  while(rs.next())
    	  {
    		  email=rs.getString(1);
    	  }
    	  
    	           
    	  rs.close();
    	  prepStmt.close();
    	  releaseConnection(conn);
    	           
    	           
    	        } 
    	        catch(SQLException sql){
    	          
    	            System.out.println("SQL Exception in RoleManagementDAOImpl.gettingViewPoint():" + sql.getMessage());
    	        }
    	        catch(Exception e){
    	           
    	            e.printStackTrace();
    	        }
    	        return email ;
    }
  
    public String[] getAdminEmail() {
        Debug.print("RoleManagementDAOImpl.getAdminEmail():");
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        int emailIndex=0;
       String[] selectAdminEmail = null;
        makeConnection();
   	try {

            String selectStatement=" select a.email_id from tblusermaster a,tblMapUserPrivilege b,tblrolemaster c where a.user_id=b.user_id and " +
            		" b.role_id=c.role_id and c.role_name='Admin' and a.email_id !=''  ";
            prepStmt = conn.prepareStatement(selectStatement);
            rs = prepStmt.executeQuery();
            selectAdminEmail = new String[rs.getFetchSize()];
          
            while(rs.next()){
            	   
            	 selectAdminEmail[emailIndex] = rs.getString(1);
              // String tempSelectAdminEmail[] = {email};
               //selectAdminEmail = tempSelectAdminEmail;
            	 emailIndex++;
            }
              System.out.println("selectAdminEmail=="+selectAdminEmail.length);
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            Debug.print("RoleManagementDAOImpl.getAdminEmail():" + selectAdminEmail);
        } 
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getAdminEmail():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getAdminEmail():" + e.getMessage());
        }
        return selectAdminEmail;
    } 
    
    public ArrayList getEntityPrivPermByRoleId(String roleId){
        Debug.print("RoleManagementDAOImpl.getEntityPrivPermByRoleId():");
        ArrayList roleEntityPrivMapList = new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
            String selectStatement=" SELECT distinct c.privilege_name "
                    + " FROM " +
                    DBHelper.USEA_ROLE_ROLE_MASTER + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                    DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " F "+
                    " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and   " +
                    " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and  " +
                    " F.privilege_id = E.privilege_id and F.permission_id = E.permission_id and " +
                    " E.role_id = ?  order by  c.privilege_name " ;

            prepStmt = conn.prepareStatement(selectStatement);
            prepStmt.setString(1,roleId);
            
           Debug.print("Select ENTPRIMAP Query ---->"+ selectStatement);
           Debug.print("role id ---->"+ roleId);
           
            rs = prepStmt.executeQuery();
           
           
            while(rs.next()){
            
               // String roleName = rs.getString(1);
                //String entityName = rs.getString(2);
                String privilegeName = rs.getString(1);             
                //String accessName = rs.getString(4);
                String tempArray[]={privilegeName};
                roleEntityPrivMapList.add(tempArray);
               
            }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.getEntityPrivPermByRoleId():" + roleEntityMapSubList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getEntityPrivPermByRoleId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getEntityPrivPermByRoleId():" + e.getMessage());
        }
        return roleEntityPrivMapList;
    }
    
    public ArrayList getDistinctEntityPrivByRoleId(String roleId){
        Debug.print("RoleManagementDAOImpl.getDistinctEntityPrivByRoleId():");
        ArrayList roleDistinctEntityPrivList =  new ArrayList();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        makeConnection();
   	try {
   		String selectStatement=" SELECT distinct B.entity_name "
                + " FROM " +
                DBHelper.USEA_ROLE_ROLE_MASTER + " A, " + DBHelper.USEA_ROLE_ENTITY_MASTER + " B, " + DBHelper.USEA_ROLE_PRIVILEGE_MASTER + " C, " +
                DBHelper.USEA_ROLE_PERMISSION_MASTER + " D, " + DBHelper.USEA_ROLE_MAP_ROLE + " E, " + DBHelper.USEA_ROLE_MAP_PERMISSION + " F "+
                " WHERE E.role_id = A.role_id and E.entity_id = B.entity_id and   " +
                " E.privilege_id = C.privilege_id and E.permission_id = D.permission_id and  " +
                " F.privilege_id = E.privilege_id and F.permission_id = E.permission_id and " +
                " E.role_id = ?  order by  B.entity_name " ;

        prepStmt = conn.prepareStatement(selectStatement);
        prepStmt.setString(1,roleId);
        
       Debug.print("Select DISTINCTENTPRIMAP Query ---->"+ selectStatement);
       Debug.print("role id ---->"+ roleId);
       
        rs = prepStmt.executeQuery();
       
       
        while(rs.next()){
        
            //String roleName = rs.getString(1);
            String entityName = rs.getString(1);
            //String privilegeName = rs.getString(3);             
            //String tempArray[]={roleName,entityName,privilegeName};
            String tempArray[]={entityName};
            roleDistinctEntityPrivList.add(tempArray);
           
        }
            rs.close();
            prepStmt.close();
            releaseConnection(conn);
            //Debug.print("RoleManagementDAOImpl.selectMenuBasedOnRoleEntitySubPrivilege():" + roleEntityMapSubList);
        }
        catch(SQLException sql){
            releaseConnection(conn);
            Debug.print("SQL Exception in RoleManagementDAOImpl.getDistinctEntityPrivByRoleId():" + sql.getMessage());
        }
        catch(Exception e){
            releaseConnection(conn);
            Debug.print("General Exception  in RoleManagementDAOImpl.getDistinctEntityPrivByRoleId():" + e.getMessage());
        }
        return roleDistinctEntityPrivList;
    }
    
  
    
    
    
  //=============================================Database Connection details=========================================    
    private void makeConnection() {
        Debug.print("RoleManagementDAOImpl : makeConnection");
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup(HLCEJBAllJNDIs.USEA_DB);
            conn = ds.getConnection();
           
        }
        catch (SQLException sqlExp) {
            Debug.print("Unable to connect to database. " + sqlExp.getMessage());
        }
        catch (Exception exp) {
            Debug.print("Exception while calling makeConnection. " + exp.getMessage());
        }
    }
     
    private void releaseConnection(Connection conn) {
        Debug.print("RoleManagementDAOImpl: releaseConnection");
        try {
            if(!conn.isClosed()){
                conn.close();
            }
        } 
        catch (SQLException sqlExp) {
            Debug.print("Unable to release Connection. " + sqlExp.getMessage());
        }
        catch (Exception ex) {
            Debug.print("Exception while releasing Connection: " + ex.getMessage());
        }
    }

    
}
