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
/*  Program Name    : BrahmaputraSessionBean.java
 *  Created Date    : September 17, 2006, 12:23 PM
 *  Author          : Suresh.K
 *  Version         : 1.10
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.hlcrole.management;

import com.hlccommon.util.DBHelper;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCMenuListVO;
import com.hlcrole.dao.HLCRoleManagementDAO;
import com.hlcrole.dao.HLCRoleManagementDAOImpl;
import javax.ejb.*;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;
import java.util.List;

public class HLCBrahmaputraSessionBean implements SessionBean {
    private SessionContext context;
    private static HLCRoleManagementDAO dao = null;
    
   
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        Debug.print("BrahmaputraSessionBean.ejbActivate()");
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        Debug.print("BrahmaputraSessionBean.ejbPassivate()");        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
         Debug.print("BrahmaputraSessionBean.ejbRemove()");        
    }

    public void ejbCreate() {
       dao = (HLCRoleManagementDAO) new HLCRoleManagementDAOImpl();
    }
    
//=================================== Privilege Management ==========================
    
 /**
  * @Method Name    :createPrivilege
  * @Description    :This method will create new privilege. If the Privilege Name already exist it will return 
  * @param          :privilegeName
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
    
    public boolean createPrivilege(String privilegeName) throws RemoteException{
        Debug.print("BrahmaputraSessionBean createPrivilege");
        boolean result = false;
        if(privilegeName!=null && privilegeName.trim().length()!=0){
            if(dao.isPrivilegeNameExist(privilegeName)){
                result = dao.insertPrivilege(privilegeName);
            }
        }
        return result;
    }
    
/**
  * @Method Name    :getPrivilege
  * @Description    :This method will return privilege details based on  privilege id.
  * @param          :privilegeId
  * @return         :String[]  value.
  * @throws         :RemoteException.
  */
    
    public String [] getPrivilege(String privilegeId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getPrivilege");
        String results[] = null;
        if(privilegeId!=null&& privilegeId.trim().length()!=0){
            String result[] = dao.selectPrivilege(privilegeId);
            results = result;
        }
        return results;
    }
    
/**
  * @Method Name    :editPrivilege
  * @Description    :This method will update privilege details based on  privilege id.
  * @param          :privilegeId, privilegeName.
  * @return         :boolean  value.
  * @throws         :RemoteException.
  */
    
   
  public boolean editPrivilege(String privilegeId, String privilegeName) throws RemoteException{
        Debug.print("BrahmaputraSessionBean editPrivilege");
        boolean result = false;
        if(privilegeId!=null && privilegeId.trim().length()!=0){
            boolean chkResult =dao.isPrivilegeNameEditExist(privilegeId, privilegeName);
            Debug.print("BrahmaputraSessionBean editPrivilege chkResult:" + chkResult);
            if(chkResult==true){
                result = dao.updatePrivilege(privilegeId, privilegeName);
            }
        }
        Debug.print("BrahmaputraSessionBean editPrivilege Result:" + result);
        return result;
    }
  
/**
  * @Method Name    :getAllPrivilege
  * @Description    :This method will return all privilege details.
  * @param          :None.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */  
    
     public ArrayList getAllPrivilege() throws RemoteException {
        Debug.print("BrahmaputraSessionBean getAllPrivilege");
        ArrayList results = dao.selectAllPrivilege();
        return results;
     }  

/**
  * @Method Name    :getAllMapPrivilege
  * @Description    :This method will return all and Mapped privilege details.
  * @param          :entityId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */

      public ArrayList getAllMapPrivilege(String roleId, String entityId) throws RemoteException {
        Debug.print("BrahmaputraSessionBean getAllMapPrivilege");
        ArrayList results = dao.selectAllMapPrivilege(roleId, entityId);
        return results;
     }

      /**
  * @Method Name    :getAllMapPrivilege
  * @Description    :This method will return all and Mapped privilege details.
  * @param          :entityId.
  * @return         :ArrayList.
  * @throws         :RemoteException.
  */

      public ArrayList getAllMapPermission(String roleId, String entityId) throws RemoteException {
        Debug.print("BrahmaputraSessionBean getAllMapPrivilege");
        ArrayList results = dao.selectAllMapPermission(roleId, entityId);
        return results;
     }
/**
  * @Method Name    :createRole
  * @Description    :This method will create new role.
  * @param          :roleName
  * @return         :boolean value.
  * @throws         :RemoteException.
  */  
     
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //public boolean createRole(String roleName) throws RemoteException{
    public boolean createRole(String roleName, String roledesc, String status) throws RemoteException{
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        Debug.print("BrahmaputraSessionBean createRole");
        boolean result = false;
        if(roleName!=null && roleName.trim().length()!=0 && dao.isRoleNameExist(roleName)){
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            //result = dao.insertRole(roleName);
            result = dao.insertRole(roleName,roledesc,status);
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        }
        Debug.print("BrahmaputraSessionBean createRole");
        return result;
    }
    
/**
  * @Method Name    :getRole
  * @Description    :This method will return role details based on the role Id.
  * @param          :roleId;
  * @return         :String [] value.
  * @throws         :RemoteException.
  */  
    
    
    public String [] getRole(String roleId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getRole");
        String results[] = null;
        if(roleId!=null && roleId.trim().length()!=0){
            String tempResults[] = dao.selectRole(roleId);
            results = tempResults;
        }
          return results;
    }
    
 /**
  * @Method Name    :editRole
  * @Description    :This method will update role details based on the role Id.
  * @param          :roleId, roleName;
  * @return         :boolean value.
  * @throws         :RemoteException.
  */  
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
   // public boolean editRole(String roleId, String roleName) throws RemoteException{

    public boolean editRole(String roleId, String roleName,String roledesc,String status) throws RemoteException{
    //public boolean editRole(String roleId[], String roleName[],String roledesc[],String status[]) throws RemoteException{
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        Debug.print("BrahmaputraSessionBean editRole");
        boolean result = false;

        //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
        if(roleId!=null&& roleId.trim().length()!=0 && roleName!=null && roleName.trim().length()!=0){
            boolean chkResult =dao.isRoleNameEditExist(roleId, roleName);
        /*if(roleId!=null&& roleId.length!=0 && roleName!=null && roleName.length!=0){
            boolean chkResult =dao.isRoleNameEditExist(roleId, roleName);*/

       //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011

            Debug.print("BrahmaputraSessionBean editRole chkResult:" + chkResult);
            if(chkResult==true){
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
               // result = dao.updateRole(roleId, roleName);
                result = dao.updateRole(roleId, roleName,roledesc,status);
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
            }
        }
        Debug.print("BrahmaputraSessionBean editRole Result:" + result);
        return result;
    }

//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //public boolean deleteRole(String roleId) throws RemoteException{
    public boolean deleteRole(String chkRoleIdArr[]) throws RemoteException{
        Debug.print("BrahmaputraSessionBean deleteRole");
        boolean result = false;

        //if(roleId!=null&& roleId.trim().length()!=0 ){
        if(chkRoleIdArr!=null){

            //boolean chkResult =dao.isRoleNameExist(roleId);
            boolean chkResult =dao.isRoleNameExist(chkRoleIdArr[0]);

            Debug.print("BrahmaputraSessionBean deleteRole chkResult:" + chkResult);
            if(chkResult==true){
                //result = dao.deleteRole(roleId);
                result = dao.deleteRole(chkRoleIdArr);
            }
        }
        Debug.print("BrahmaputraSessionBean deleteRole Result:" + result);
        return result;
    }
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
 //Start:[RoleMgt] For Role,Privilege,Permission Mapping
    public boolean generateMappingPermSubPerm(String permIdArr[],String subPermIdArr[])throws RemoteException{
        Debug.print("BrahmaputraSessionBean deleteRole");
        boolean result = false;

        //if(roleId!=null&& roleId.trim().length()!=0 ){
        if(permIdArr!=null && subPermIdArr!=null){

                result = dao.updatePermissionSubPermissionMapping(permIdArr,subPermIdArr);

        }
        Debug.print("BrahmaputraSessionBean deleteRole Result:" + result);
        return result;
    }
   //Ends:[RoleMgt] For Role,Privilege,Permission Mapping
 /**
  * @Method Name    :createPermission
  * @Description    :This method will create new permission.
  * @param          :permissionName;
  * @return         :boolean value.
  * @throws         :RemoteException.
  */  

    public boolean createPermission(String permissionName) throws RemoteException{
        Debug.print("BrahmaputraSessionBean createPermission");
        boolean result = false;
        if(dao.isPermissionNameExist(permissionName)){
            result = dao.insertPermission(permissionName);
        }
        Debug.print("BrahmaputraSessionBean createPermission");
        return result;
    }
    
 /**
  * @Method Name    :getPermission
  * @Description    :This method will return permission details based on permissionId.
  * @param          :permissionId;
  * @return         :String [] value.
  * @throws         :RemoteException.
  */
    
    public String [] getPermission(String permissionId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getPermission");
        String results[] = null;
        if(permissionId!=null&& permissionId.trim().length()!=0){
            String result[] = dao.selectPermission(permissionId);
            results = result;
        }
        return results;
    }

/**
  * @Method Name    :editPermission
  * @Description    :This method will update permission details based on permissionId.
  * @param          :permissionId, permissionName;
  * @return         :booelan value.
  * @throws         :RemoteException.
  */          
    
    public boolean editPermission(String permissionId, String permissionName) throws RemoteException{
        Debug.print("BrahmaputraSessionBean editPermission");
        boolean result = false;
        boolean chkResult =dao.isPermissionNameEditExist(permissionId, permissionName);
        Debug.print("BrahmaputraSessionBean editPermission chkResult:" + chkResult);
        if(chkResult==true){
            result = dao.updatePermission(permissionId, permissionName);
        }
        Debug.print("BrahmaputraSessionBean editPermission Result:" + result);
        return result;
    }
    
 /**
  * @Method Name    :createEntity
  * @Description    :This method will create new Entity.
  * @param          :entityName;
  * @return         :booelan value.
  * @throws         :RemoteException.
  */
    
    public boolean createEntity(String entityName) throws RemoteException{
        Debug.print("BrahmaputraSessionBean createEntity");
        boolean result = false;
        if(entityName!=null && entityName.trim().length()!=0){
            if(dao.isEntityNameExist(entityName)){
                result = dao.insertEntity(entityName);
            }
        }
        Debug.print("BrahmaputraSessionBean createEntity");
        return result;
    }
    
 /**
  * @Method Name    :getEntity
  * @Description    :This method will return entity details based on the entityId.
  * @param          :entityId;
  * @return         :String [] value.
  * @throws         :RemoteException.
  */

    public String [] getEntity(String entityId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getEntity");
        String results[] = null;
        if(entityId!=null && entityId.trim().length()!=0){
            String result[] = dao.selectEntity(entityId);
            results = result;
        }
        return results;
    }
    
/**
  * @Method Name    :editEntity
  * @Description    :This method will update entity Name based on the entityId.
  * @param          :entityId, entityName;
  * @return         :boolean value.
  * @throws         :RemoteException.
  */    
    
    public boolean editEntity(String entityId, String entityName) throws RemoteException{
        Debug.print("BrahmaputraSessionBean editEntity");
        boolean result = false;
        if(entityId!=null && entityId.trim().length()!=0 && entityName!=null && entityName.trim().length()!=0){
            boolean chkResult =dao.isEntityNameEditExist(entityId, entityName);
            Debug.print("BrahmaputraSessionBean editEntity chkResult:" + chkResult);
            if(chkResult==true){
                result = dao.updateEntity(entityId, entityName);
            }
        }
        Debug.print("BrahmaputraSessionBean editEntity Result:" + result);
        return result;
    }

/**
  * @Method Name    :createMappingPermissionToPrivilege
  * @Description    :This method will create permission details for a particular privilege.
  * @param          :String privilegeId, ArrayList mapPriPerList;
  * @return         :void.
  * @throws         :RemoteException.
  */    

    public void createMappingPermissionToPrivilege(String privilegeId, ArrayList mapPriPerList) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getAllPermission");
        Iterator itMapPriPer = mapPriPerList.iterator();
        while(itMapPriPer.hasNext()){
            String priList[] = (String [])itMapPriPer.next();
            String permissionId = priList[0];
            String accessName = priList[1];
            String accessUrl = priList[2];
            if(accessName.trim().length()!=0 && accessName !=null){
                if(dao.isAccessNameExist(accessName)){
                    dao.insertPermissionPrivilegeMapping(privilegeId, permissionId, accessName, accessUrl);
                }
                else{
                     dao.insertPermissionPrivilegeMapping(privilegeId, permissionId, "", accessUrl);
                }
            }
            else{
                dao.insertPermissionPrivilegeMapping(privilegeId, permissionId, accessName, accessUrl);
            }
        }
    }
    
/**
  * @Method Name    :editMappingPermissionToPrivilege
  * @Description    :This method will map permission details to particualar privilege.
  * @param          :ArrayList mapPriPerList;
  * @return         :void.
  * @throws         :RemoteException.
  */    
    
    
    public void editMappingPermissionToPrivilege(ArrayList mapPriPerList,String privId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean.editMappingPermissionToPrivilege()");
        Iterator itMapPriPer = mapPriPerList.iterator();
        while(itMapPriPer.hasNext()){
            String priList[] = (String [])itMapPriPer.next();
            String mapPermissionId = priList[0];
            String accessName = priList[1];
            String accessUrl = priList[2];
            
            if(accessName!=null && accessName.trim().length()!=0 ){
                Debug.print("BrahmaputraSessionBean.editMappingPermissionToPrivilege() accessName not empty");
                if(dao.isAccessNameExistinEdit(mapPermissionId,accessName,"","")){
                    dao.updatePermissionPrivilegeMapping( mapPermissionId, accessName, accessUrl,privId);
                }
                else{
                	dao.insertPermissionPrivilegeMapping(privId, mapPermissionId, accessName, accessUrl);
                }
            }
            else{
            	if(dao.isAccessNameExistinEdit("","",privId,mapPermissionId)){
            		Debug.print("BrahmaputraSessionBean.editMappingPermissionToPrivilege() empty accessName");
            		dao.updatePermissionPrivilegeMapping( mapPermissionId, accessName, accessUrl,privId);
            	}
            	else {
            		dao.insertPermissionPrivilegeMapping(privId, mapPermissionId, accessName, accessUrl);
            	}
            }
        }
    }
    

/**
  * @Method Name    :editMappingPermissionToPrivilege
  * @Description    :This method will map permission details to particualar privilege.
  * @param          :ArrayList mapPriPerList;
  * @return         :void.
  * @throws         :RemoteException.
  */    
    
    public ArrayList getMappingDetailsForPermissionAndPrivilege(String  privilegeId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMappingDetailsForPermissionAndPrivilege");
        ArrayList results = dao.selectAllMappingDetailsForPrivilege(privilegeId); 
        return results;
    }


     public ArrayList getAllPermission() throws RemoteException {
        Debug.print("BrahmaputraSessionBean getAllPermission");
        ArrayList results = dao.selectAllPermission();
        return results;
     }
     //Start:[RoleMgt] For Role,Privilege,Permission Mapping
     public ArrayList getEntityPrivPermission()throws RemoteException
    {
         Debug.print("BrahmaputraSessionBean getEntityPermission()");
        ArrayList results = dao.selectEntityPrivPermission();
        return results;
     }

    //Ends:[RoleMgt] For Role,Privilege,Permission Mapping
//==============================Mapping  Entity  with Privilege ==========================================================
     public ArrayList getAllEntity() throws RemoteException { 
        Debug.print("BrahmaputraSessionBean getAllEntity");
        ArrayList results = dao.selectAllEntity();
        return results;    
     }
     
     public boolean generateMappingEnitityWithPrivileges(String entityId, ArrayList privilegeList) throws RemoteException {
        Debug.print("BrahmaputraSessionBean generateMappingEnitityWithPrivileges()" + entityId);
        boolean result =false;
        boolean flag = dao.deleteRow("entity_id", entityId, DBHelper.USEA_ROLE_MAP_PRIVILEGE,"");
        Debug.print("BrahmaputraSessionBean generateMappingEnitityWithPrivileges(): Deleted Enities Result:" + flag);
        if(flag){
            if(privilegeList!=null && privilegeList.size()!=0){
                Iterator itPrivilege = privilegeList.iterator();
                while(itPrivilege.hasNext()){
                    String privilegeId = (String)itPrivilege.next();
                    if(privilegeId!=null && privilegeId.trim().length()!=0){
                        dao.insertEntityPrivilegeMapping(entityId, privilegeId);
                    }
                }
            }
            result = true;
        }
         return result;
     }
     
    public ArrayList getMappingDetailsForEnitityAndPrivileges(String  entityId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMappingDetailsForEnitityAndPrivileges");
        ArrayList results = dao.selectAllMappingDetailsForEntity(entityId); 
        return results;
    }
//==============================Mapping  Role  with Entities ==========================================================
     public ArrayList getAllRoles() throws RemoteException { 
        Debug.print("BrahmaputraSessionBean getAllRole");
        ArrayList results = dao.selectAllRole();
        return results;
     }
    
    public boolean generateMappingRoleWithEntities(String roleId, ArrayList entitesList) throws RemoteException {
        Debug.print("BrahmaputraSessionBean generateMappingRoleWithEntities()" + roleId);
        boolean result =false;
        Iterator itEntityList = entitesList.iterator();
        boolean flag = dao.deleteRow("role_id", roleId, DBHelper.USEA_ROLE_MAP_ENTITY,"");
        Debug.print("BrahmaputraSessionBean generateMappingRoleWithEntities(): Deleted Enities Result:" + flag);
        if(flag){
            if(entitesList!=null && entitesList.size()!=0){
                while(itEntityList.hasNext()){
                    String entityId = (String)itEntityList.next();
                    if(entityId!=null && entityId.trim().length()!=0){
                        dao.insertRoleEntityMapping(roleId, entityId);
                    }
                }
            }
            result = true;
        }
         return result;
     }
     
    public ArrayList getMappingDetailsForRoleAndPrivileges(String  roleId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMappingDetailsForRoleAndAndPrivileges");
        ArrayList results = dao.selectAllMappingDetailsForRole(roleId); 
        return results;
    }
    
    public boolean insertRoleEntityDetails(String  roleId, String entityId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMappingDetailsForRoleAndAndPrivileges");
        boolean results = dao.insertRoleEntityMapping(roleId,entityId); 
        return results;
    }   
    
    
    //==============================Mapping  Role  with Entities and Privilege==========================================================
    
    public boolean generateMappingRoleWithEntitiesAndPrivileges(String roleId, String entityId, ArrayList priPerList) throws RemoteException {
        Debug.print("BrahmaputraSessionBean generateMappingRoleWithEntitiesAndPrivileges() : " + roleId + ": Entity :" + entityId);
        boolean result =false;
        boolean flag = false;
        if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0){
            flag = dao.deleteRow("role_id", roleId, "entity_id" , entityId , DBHelper.USEA_ROLE_MAP_ROLE);
        }
        
        Debug.print("BrahmaputraSessionBean generateMappingRoleWithEntities(): Deleted Enities Result:" + flag);
        if(flag){
            Iterator itPriPerList = priPerList.iterator();
            while(itPriPerList.hasNext()){
                String []  strPriPerList = (String [])itPriPerList.next();
                String privilegeId = strPriPerList[0];
                String permissionId = strPriPerList[1];
                if(privilegeId!=null && permissionId!=null && privilegeId.trim().length()!=0 && permissionId.trim().length()!=0){
                    dao.insertRoleEntityPrivilegeMapping(roleId, entityId, privilegeId, permissionId);
                }
            }
            result = true;
        }
         return result;
     }
   //Start:[RoleMgt] For Role,Privilege,Permission Mapping

     public ArrayList getMappingDetailsForRoleSubPerm()  throws RemoteException{
        Debug.print("BrahmaputraSessionBean getPermissionBasedOnEntityRole");
        ArrayList results = dao.selectAllSubPermission();
        return results;
    }
   //End:[RoleMgt] For Role,Privilege,Permission Mapping
     
    public ArrayList getPermissionBasedOnEntityRole(String  roleId, String entityId)  throws RemoteException{
        Debug.print("BrahmaputraSessionBean getPermissionBasedOnEntityRole");
        ArrayList results = dao.selectPermissionBasedRoleEntity(roleId, entityId);
        return results;
    }
    public ArrayList getDistinctPermissionBasedOnEntityRole(String  roleId, String entityId)  throws RemoteException{
        Debug.print("BrahmaputraSessionBean getPermissionBasedOnEntityRole");
        ArrayList results = dao.selectDistinctPermissionBasedRoleEntity(roleId, entityId);
        return results;
    }
    
    
    
    public ArrayList getPermissionBasedOnEntityRolePrivileges(String  roleId, String entityId, String privilegeId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getPermissionBasedOnEntityRolePrivileges");
        ArrayList results = dao.selectPermissionBasedRoleEntityPrivilege(roleId, entityId, privilegeId);
        return results;
    }
     public ArrayList getMenuBasedOnEntityRoleSubPrivileges(String  roleId, String entityId, String privilegeId,String permissionId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMenuBasedOnEntityRoleSubPrivileges");
        ArrayList results = dao.selectMenuBasedOnRoleEntitySubPrivilege(roleId, entityId, privilegeId,permissionId);
        return results;
    }
    
     public ArrayList getMenuBasedOnEntityRolePrivileges(String  roleId, String entityId, String privilegeId)  throws RemoteException {
        Debug.print("BrahmaputraSessionBean getMenuBasedOnEntityRolePrivileges");
        ArrayList results = dao.selectMenuBasedOnRoleEntityPrivilege(roleId, entityId, privilegeId);
        return results;
    }
     
    public ArrayList getLeftMenuPrivilegePermission(String roleId, String EnityId){
        return null;
    }

   //==============================Mapping  User  with Role ==========================================================
    public boolean createMappingUserWithRoles(String userId, ArrayList roleList) throws RemoteException{
        Debug.print("BrahmaputraSessionBean createMappingUserWithRoles UserId:" + userId);
        boolean result = false;
        boolean flag = false;
        if(userId!=null && userId.trim().length()!=0){
            flag = dao.deleteRow("user_id", userId,  DBHelper.USEA_USER_MAP_PRIVILEGE,"");
        }
        Debug.print("BrahmaputraSessionBean generateMappingRoleWithEntities(): Deleted Enities Result:" + flag);
        if(flag){
            Iterator itRoleIds = roleList.iterator();
            while(itRoleIds.hasNext()){
                String roleId = (String)itRoleIds.next();
                if(roleId!=null){
                   result =  dao.insertUserWithRoleMapping(userId, roleId);
                }
            }
        }
        return result;
    }
    
    
     //==============================Create Default Role==========================================================
     public boolean createDefaultRole(String userId, String roleId) throws RemoteException {
        Debug.print("BrahmaputraSessionBean createDefaultRole UserId:" + userId);
        boolean result = false;
        if(userId!=null && userId.trim().length()!=0 &&  roleId!=null && roleId.trim().length()!=0){
           result =  dao.insertUserWithRoleMapping(userId, roleId);
           Debug.print("BrahmaputraSessionBean Default Role Assigned UserId " + result   +  " Result:" +  result);
        }
        return result;
    }
   
    public ArrayList getAllRolesBasedOnUser(String userId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getAllRolesBasedOnUser");
        ArrayList results = dao.selectAllRolesBasedOnUser(userId);
        return results; 
    }
 
    
    //===================================Getting Whole Role Management Details based on Role and Entity  ==========================
    public ArrayList getMyPrivilegesListFromEntity(String roleId, String entityId) throws RemoteException{
         Debug.print("BrahmaputraSessionBean getMyPrivilegesListFromEntity");
         ArrayList myPrivilegesList = new ArrayList();
         if(entityId!=null){
             try{
                ArrayList privList = (ArrayList)getDistinctPermissionBasedOnEntityRole(roleId, entityId);
                if(privList!=null && privList.size()!=0){
                    Iterator itPrivi = privList.iterator();
                    while(itPrivi.hasNext()){
                         String priMapList [] = (String []) itPrivi.next();
                         //{roleName, entityName, privilegeName, permissionName, privilegeIdVal, permissionId};
                         String privillegeId = priMapList[0];
                         // Debug.print("Priv Name:" + priMapList[1]);
                         ArrayList privMenuList = new ArrayList();
                         if(privillegeId!=null){
                            privMenuList = (ArrayList)getMenuBasedOnEntityRolePrivileges(roleId,entityId,privillegeId);
                        }
                        if(priMapList!=null && priMapList.length!=0){
                            myPrivilegesList.add(priMapList);
                            myPrivilegesList.add(privMenuList);
                        }
                    }
                }
             }
             catch(Exception exception){
                 Debug.print("Exception while getting BrahmaputraSessionBean getMyPrivilegesList() :" + exception.getMessage());
             }
             
         }
         return myPrivilegesList;
    }
     //===================================Getting Whole Role Management Details based on Role and Entity  ==========================
    public ArrayList getMyPrivilegesListForHeader(String roleId, String entityId) throws RemoteException{
         Debug.print("BrahmaputraSessionBean getMyPrivilegesListForHeader");
         ArrayList myPrivilegesList = new ArrayList();
         if(entityId!=null){
             try{
                myPrivilegesList = (ArrayList)getDistinctPermissionBasedOnEntityRole(roleId, entityId);
             }
             catch(Exception exception){
                 Debug.print("Exception while getting BrahmaputraSessionBean getMyPrivilegesListForHeader() :" + exception.getMessage());
             }
             
         }
         return myPrivilegesList;
    }
        
     //===================================Getting Whole Role Management Details based on UserId==========================
    public ArrayList getMyPrivilegesList(String userId) throws RemoteException{
         Debug.print("BrahmaputraSessionBean getMyPrivilegesList");
         ArrayList myPrivilegesList = new ArrayList();
       
        if(userId!=null){
             try{
                ArrayList roleList = (ArrayList)getAllRolesBasedOnUser(userId);
                Iterator itRole = roleList.iterator();
                while(itRole.hasNext()){
                    String roleMapList [] = (String []) itRole.next();
                    String roleId = roleMapList[2];
                    String roleName = roleMapList[3];
                    //Debug.print("          roleName:" + roleName);
                    //{userMapId, userIdVal, roleId, roleName};
                    if(roleId!=null){
                        //Debug.print("          roleName:" + roleName);
                        ArrayList entityList = (ArrayList)getMappingDetailsForRoleAndPrivileges(roleId);
                        if(entityList!=null && entityList.size() !=0){
                            Iterator itEntity = entityList.iterator();
                            while(itEntity.hasNext()){
                                    String entityMapList [] = (String []) itEntity.next();
                                    String entityId = entityMapList[2];
                                    //mapEntityId, roleIdVal, entityId, roleName, entityName};
                                    String entityName = entityMapList[4];
                                    if(entityId!=null){
                                        myPrivilegesList.add(entityName);
                                        ArrayList privList = (ArrayList)getMyPrivilegesListFromEntity(roleId, entityId);
                                         if(privList!=null && privList.size()!=0){
                                               // myPrivilegesList.add(entityName);
                                                myPrivilegesList.add(privList);
                                         }
                                         else{
                                            ArrayList tempPriv = new ArrayList();
                                            myPrivilegesList.add(tempPriv);
                                         }
                                    }
                            }
                        }
                    }
                }
             }
             catch(Exception exception){
                 Debug.print("Exception while getting BrahmaputraSessionBean getMyPrivilegesList() :" + exception.getMessage());
             }
         }
         return myPrivilegesList;
    }
    
    // getting Entity List
     public ArrayList getMyEntityList(String userId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getMyEntityList");
        ArrayList myEntityList = new ArrayList();
        if(userId!=null){
             try{
                ArrayList roleList = (ArrayList)getAllRolesBasedOnUser(userId);
                Iterator itRole = roleList.iterator();
                while(itRole.hasNext()){
                    String roleMapList [] = (String []) itRole.next();
                    String roleId = roleMapList[2];
                    String roleName = roleMapList[3];
                    String strRole[] = {roleId,roleName};
                    //{userMapId, userIdVal, roleId, roleName};
                    if(roleId!=null){
                        myEntityList.add(strRole);
                        ArrayList entityList = new ArrayList();
                        entityList = (ArrayList)getMappingDetailsForRoleAndPrivileges(roleId);
                        if(entityList!=null && entityList.size() !=0){
                            Iterator itEntity = entityList.iterator();
                            ArrayList tempEntityList = new ArrayList();
                            while(itEntity.hasNext()){
                                String entityMapList [] = (String []) itEntity.next();
                                String entityId = entityMapList[2];
                                //mapEntityId, roleIdVal, entityId, roleName, entityName};
                                String entityName = entityMapList[4];
                                String strEntity[] = {entityId,entityName};
                                tempEntityList.add(strEntity);
                            }
                            myEntityList.add(tempEntityList);
                        }
                        else{
                             myEntityList.add(new ArrayList());
                        }
                    }
                }
             }
             catch(Exception exception){
                 Debug.print("Exception while getting BrahmaputraSessionBean getMyEntityList() :" + exception.getMessage());
             }
         }
         return myEntityList;
                
     }
     
     public String getUserIdBasedOnUserName(String userName)
     {
         return dao.selectUserIdBasedOnUserName(userName);
     }
  
     
     public boolean insertCvsUser(String username, String password)
    	        throws RemoteException
    	    {
    	        Debug.print("BrahmaputraSessionBean createUser");
    	        boolean result = false;
    	        if(username != null && username.trim().length() != 0 && password != null && password.trim().length() != 0)
    	            result = dao.insertCvsUser(username, password);
    	        return result;
    	    }
     
     public List SelectAllPermissionsBasedOnRole(String roleId)
     {
         Debug.print("BrahmaputraSessionBean createUser");
         List result = dao.SelectAllPermissionsBasedOnRole(roleId);
         return result;
     }
     
     public ArrayList getAllSubArtifacts(String mapPerId) throws RemoteException { 
         Debug.print("HLCBrahmaputraSessionBean getAllArtifacts");
         ArrayList results = dao.selectAllSubArtifacts(mapPerId);
         return results;
      }
     
     public String getArtifactName(String mapPerId) throws RemoteException { 
         Debug.print("HLCBrahmaputraSessionBean getArtifactName");
         String artifactName = dao.selectPermissionAccessName(mapPerId);
         return artifactName;
      }
     
     public boolean createSubArtifact(String mainArtiname,String subArtiName,String mainArtiId)
 	        throws RemoteException
 	    {
 	        Debug.print("BrahmaputraSessionBean createSubArtifact()");
 	        boolean result = false;
 	       boolean result1 = false;
 	        if(mainArtiname != null && mainArtiname.trim().length() != 0 && subArtiName != null && subArtiName.trim().length() != 0 && mainArtiId != null && mainArtiId.trim().length() != 0)
 	        	 result1=dao.isSubArtifactNameExist(subArtiName);
 	        if(result1==false){
 	            result = dao.insertSubArtifact(mainArtiname,subArtiName,mainArtiId);
 	        }else{
 	        	result=false;
 	        }
 	        return result;
 	    }
     
     public String getSubArtifactName(String subArtifactId) throws RemoteException { 
         Debug.print("HLCBrahmaputraSessionBean getSubArtifactName");
         String subArtifactName = dao.selectSubArtifactName(subArtifactId);
         return subArtifactName;
      }
     
     public boolean editSubArtifact(String artifactId, String subArtifact) throws RemoteException{
    	 
    	        Debug.print("BrahmaputraSessionBean editSubArtifact");
    	        boolean result = false;
    	     
    	        if(artifactId!=null&& artifactId.trim().length()!=0 && subArtifact!=null && subArtifact.trim().length()!=0){
    	            boolean chkResult =dao.isSubArtifactNameEditExist(artifactId, subArtifact);
    	      
    	            Debug.print("BrahmaputraSessionBean editSubArtifact chkResult:" + chkResult);
    	            if(chkResult==false){
    	
    	                result = dao.updateArtifact(artifactId, subArtifact);
    
    	            }else{
    	            	result = false;
    	            }
    	        }
    	        Debug.print("BrahmaputraSessionBean editSubArtifact Result:" + result);
    	        return result;
    	    }
     
     public String getViewName(String viewId) throws RemoteException { 
         Debug.print("HLCBrahmaputraSessionBean getViewName");
         String viewName = dao.selectViewName(viewId);
         return viewName;
      }
     
   
     
  //========================Dhivya Here:Manage Artifact Mappings================================
    

     
    
     public ArrayList getAllViewsBasedOnViewPoint(String viewPointId) throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getAllViewsBasedOnViewPoint()");
         ArrayList results = dao.selectViewsBasedOnViewPoint(viewPointId);
         return results;
      }
     
     public ArrayList getAllViews() throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getAllViews()");
         ArrayList results = dao.selectAllViews();
         return results;
      }
     
     public Vector getLOBs(String viewPntId) throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getLOBs()");
         Vector results = dao.selectLOBs(viewPntId);
         return results;
      }
     public Vector getViews(String viewPntId, String lobId) throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getViewsGrops()");
         Vector results = dao.selectViews(viewPntId,lobId);
         return results;
      }
     
     public Vector getGroups(String viewPntId, String lobId) throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getViewsGrops()");
         Vector results = dao.selectGroups(viewPntId,lobId);
         return results;
      }
     
     public Vector getProcessDomain(String viewPntId, String lobId, String grpId) throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getProcessDomain()");
         Vector results = dao.selectProcessDomain(viewPntId,lobId,grpId);
         return results;
      }
     
     public boolean insertArtifactMapDetails(HLCMenuListVO objArti)
  	        throws RemoteException
  	    {
  	        Debug.print("BrahmaputraSessionBean insertArtifactMapDetails()");
  	        boolean result = false;
  	       boolean result1 = false;
  	        if(objArti!=null){
  	        	
  	            result = dao.insertArtifactMapDetails(objArti);
  	        }
  	        
  	        return result;
  	    }
     
     public boolean artifactMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId,String artifactId)
   	        throws RemoteException
   	    {
   	        Debug.print("BrahmaputraSessionBean artifactMapExists()");
   	        boolean result = false;
   	       boolean result1 = false;
   	        
   	        	
   	            result = dao.artifactMapExists(viewPntId, lobId, viewId, grpId, domProcId, artifactId);
   	      
   	        
   	        return result;
   	    }
      
     public ArrayList getAllGroup() throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getAllGroup()");
         ArrayList results = dao.selectAllGroup();
         return results;
      }
     
     public ArrayList getGrpDetails() throws RemoteException { 
         System.out.println("HLCBrahmaputraSessionBean getGrpDetails()");
         ArrayList results = dao.selectGrpDetails();
         return results;
      }
     
     public ArrayList getCount() throws RemoteException { 
         Debug.print("HLCBrahmaputraSessionBean getCount");
         ArrayList cnt = dao.selectCount();
         return cnt;
      }
     
     public boolean viewPointMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId)
    	        throws RemoteException
    	    {
    	        Debug.print("BrahmaputraSessionBean viewPointMapExists()");
    	        boolean result = false;
    	       boolean result1 = false;
    	        
    	        	
    	            result = dao.viewPointMapExists(viewPntId, lobId, viewId, grpId, domProcId);
    	      
    	        
    	        return result;
    	    }
     //==============Dhivya Ends Here=============================================
     public boolean insertFrameworkMapDetails(String viewPointId,HLCMenuListVO objMap, HLCMenuListVO objArti)
   	        throws RemoteException
   	    {
   	        Debug.print("BrahmaputraSessionBean insertFrameworkMapDetails()");
   	        boolean result = false;
   	       boolean result1 = false;
   	        if(viewPointId != null && viewPointId.trim().length() != 0)
   	        	
   	            result = dao.insertFrameworkMapDetails(viewPointId,objMap,objArti);
   	        
   	        return result;
   	    }
      
  
     public ArrayList getViewPointDetailsList(String userId)throws RemoteException
     {
    	 
    	 Debug.print("HLCBrahmaputraSessionBean getViewPointDetailsList");
         ArrayList ViewPointDetailsList = dao.getViewPointDetailsList(userId);
         return ViewPointDetailsList;
     }
     public ArrayList getViewPointArtifactList(String userId,String view_point_id,String lobId,String viewId,String groupId,String processId) throws RemoteException
     {
    	 Debug.print("HLCBrahmaputraSessionBean getViewPointArtifactList");
         ArrayList ViewPointDetailsList = dao.getViewPointArtifactList(userId,view_point_id,lobId,viewId,groupId,processId);
         return ViewPointDetailsList;
     }
	 public ArrayList getAllViewMapList(String viewPointId) throws RemoteException { 
         Debug.print("HLCBrahmaputraSessionBean getAllViewMapList");
         ArrayList cnt = dao.selectMapViewPointList(viewPointId);
         return cnt;
      }
	 
	 public boolean chgUserStatByAdmin(String uId)throws RemoteException
	   	        
	   	    {
	   	        Debug.print("BrahmaputraSessionBean chgUserStatByAdmin()");
	   	        boolean result = false;
	   	    
	   	        if(uId != null && uId.trim().length() != 0)
	   	        	
	   	            result = dao.chgUserStatByAdmin(uId);
	   	        
	   	        return result;
	   	    }
	 public boolean chgUserStatBySup(String uId,String supName,String roleId)throws RemoteException
        
	    {
	        Debug.print("BrahmaputraSessionBean chgUserStatBySup()");
	        boolean result = false;
	    
	        if(uId != null && uId.trim().length() != 0)
	        	
	            result = dao.chgUserStatBySup(uId,supName,roleId);
	        
	        return result;
	    }
	 
	  public String getExternalAppURL(String perName) throws RemoteException { 
	         Debug.print("HLCBrahmaputraSessionBean getExtAppUrl");
	         String viewName = dao.getExternalAppURL(perName);
	         return viewName;
	      }
	  
	  public boolean generateMappingUserRoleWithEntities(String roleId,String deptId,String userId,ArrayList entitesList,String roleName) throws RemoteException {
	        Debug.print("BrahmaputraSessionBean generateMappingUserRoleWithEntities()" + roleId);
	        boolean result =false;
	        boolean flag = false;
	        Iterator itEntityList = entitesList.iterator();
	    	if(roleName.equalsIgnoreCase("Admin")){
	        flag = dao.deleteRow("user_id", userId, "tblmapuserentity",deptId);
	    	}
	    	else
	    	{
	         flag = dao.deleteRow("user_id", userId, "tblmapuserentitytemp",deptId);
	    	}
	        Debug.print("BrahmaputraSessionBean generateMappingUserRoleWithEntities(): Deleted Enities Result:" + flag);
	        if(flag){
	            if(entitesList!=null && entitesList.size()!=0){
	                while(itEntityList.hasNext()){
	                    String entityId = (String)itEntityList.next();
	                    if(entityId!=null && entityId.trim().length()!=0){
	                    	if(roleName.equalsIgnoreCase("Admin")){
	                    		 dao.insertUserRoleEntityMapping(roleId, entityId,deptId,userId,"tblmapuserentity");
	                    	}
	                    	else
	                    	{
	                    		dao.insertUserRoleEntityMapping(roleId, entityId,deptId,userId,"tblmapuserentitytemp");
	                    	}
	                    }
	                }
	            }
	            result = true;
	        }
	         return result;
	     }
	  
	  public ArrayList getMappingDetailsForUserRoleAndPrivileges(String  roleId,String userId)  throws RemoteException {
	        Debug.print("BrahmaputraSessionBean getMappingDetailsForUserRoleAndPrivileges");
	        ArrayList results = dao.selectAllUserMappingDetailsForRole(roleId,userId); 
	        return results;
	    }
	  
	  public boolean chkUserMapRoleEntity(String roleId,String userId) throws RemoteException {
	        Debug.print("BrahmaputraSessionBean chkUserMapRoleEntity()" + roleId+"=="+userId);
	        boolean result =false;
	        result=dao.chkUserMapRoleEntity(roleId, userId);
	        return result;
	  }
	  
	  public String getSupervisorEmailByEmpId(String uId) throws RemoteException { 
	         Debug.print("HLCBrahmaputraSessionBean getSupervisorEmailByEmpId");
	         String viewName = dao.getSupervisorEmailByEmpId(uId);
	         return viewName;
	      }
	  public String [] getAdminEmail() throws RemoteException{
	        Debug.print("BrahmaputraSessionBean getAdminEmail");
	        String results[] = null;
	       
	            String tempResults[] = dao.getAdminEmail();
	            results = tempResults;
	       
	          return results;
	    }
	  
	  public ArrayList getEntityPrivPermByRoleId(String  roleId)  throws RemoteException {
	        Debug.print("BrahmaputraSessionBean getEntityPrivPermByRoleId");
	        ArrayList results = dao.getEntityPrivPermByRoleId(roleId);
	        return results;
	    }
	  
	  public ArrayList getDistinctEntityPrivByRoleId(String  roleId)  throws RemoteException {
	        Debug.print("BrahmaputraSessionBean getDistinctEntityPrivByRoleId");
	        ArrayList results = dao.getDistinctEntityPrivByRoleId(roleId);
	        return results;
	    }
	  
	
}
