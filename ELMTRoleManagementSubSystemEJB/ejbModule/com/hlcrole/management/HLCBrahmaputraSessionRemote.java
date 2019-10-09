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
/*  Program Name    : BrahmaputraSessionRemote.java
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

import javax.ejb.EJBObject;

import com.hlccommon.util.HLCMenuListVO;

import java.util.ArrayList;
import java.util.List;
import java.rmi.*;
import java.util.Vector;


/**
 * This is the remote interface for BrahmaputraSession enterprise bean.
 */

public interface HLCBrahmaputraSessionRemote extends EJBObject, HLCBrahmaputraSessionRemoteBusiness {
    
//=================================== Privilege Management ==========================
    public boolean createPrivilege(String privilegeName) throws RemoteException;
    public String [] getPrivilege(String privilegeId) throws RemoteException;
    public boolean editPrivilege(String privilegeId, String privilegeName) throws RemoteException;    
    public ArrayList getAllPrivilege() throws RemoteException;
    public ArrayList getAllMapPrivilege(String roleId, String entityId) throws RemoteException;
//=================================== Permission Management ==========================
    public boolean createPermission(String permissionName) throws RemoteException;
    public String [] getPermission(String permissionId) throws RemoteException;
    public boolean editPermission(String permissionId, String permissionName) throws RemoteException;    
    public ArrayList getAllPermission() throws RemoteException;
    public ArrayList getAllMapPermission(String roleId, String entityId) throws RemoteException;
//=================================== Entity Management ==========================
    public boolean createEntity(String entityName) throws RemoteException;
    public String [] getEntity(String entityId) throws RemoteException;
    public boolean editEntity(String entityId, String entityName) throws RemoteException;    
    public ArrayList getAllEntity() throws RemoteException;   
//=================================== Role Management ==========================
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //public boolean createRole(String roleName) throws RemoteException;
    public boolean createRole(String roleName, String roledesc, String status) throws RemoteException;
    public String [] getRole(String roleId) throws RemoteException;
    //public boolean editRole(String roleId, String roleName) throws RemoteException;
    public boolean editRole(String roleId, String roleName,String roledesc,String status) throws RemoteException;
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    //for Debug Starts
    //public boolean deleteRole(String roleId)throws RemoteException;
    public boolean deleteRole(String chkRoleIdArr[])throws RemoteException;
    //for Debug Ends

    public ArrayList getAllRoles() throws RemoteException;
    
//===================================Mapping Entities with Privilege ==========================  
    
    public void createMappingPermissionToPrivilege(String privilegeId, ArrayList mapPriPerList) throws RemoteException;
    public void editMappingPermissionToPrivilege(ArrayList mapPriPerList,String privId) throws RemoteException;
    public ArrayList getMappingDetailsForPermissionAndPrivilege(String  privilegeId) throws RemoteException ;
      
    //===================================Mapping Entities with Privilege ==========================
    public boolean generateMappingEnitityWithPrivileges(String entityId, ArrayList privilegeList) throws RemoteException;
    public ArrayList getMappingDetailsForEnitityAndPrivileges(String entityId) throws RemoteException;
    
    //===================================Mapping Role with Entities  ==========================      
   
    public boolean generateMappingRoleWithEntities(String roleId, ArrayList entitesList) throws RemoteException;
    public ArrayList getMappingDetailsForRoleAndPrivileges(String roleId) throws RemoteException;
    public boolean insertRoleEntityDetails(String roleId, String entityId) throws RemoteException;
    //===================================Assign Mapping Role and Entities with Privilege ==========================      
    public boolean generateMappingRoleWithEntitiesAndPrivileges(String roleId, String entityId, ArrayList priPerList) throws RemoteException;
    public ArrayList getPermissionBasedOnEntityRolePrivileges(String  roleId, String entityId, String privilegeId)  throws RemoteException;
    public ArrayList getPermissionBasedOnEntityRole(String  roleId, String entityId)  throws RemoteException; 
    public ArrayList getDistinctPermissionBasedOnEntityRole(String  roleId, String entityId)  throws RemoteException;
    public ArrayList getMenuBasedOnEntityRolePrivileges(String  roleId, String entityId, String privilegeId)  throws RemoteException ;
     public ArrayList getMenuBasedOnEntityRoleSubPrivileges(String  roleId, String entityId, String privilegeId,String permissionId)  throws RemoteException ;
   // public ArrayList getMappingDetailsForRoleAndAndPrivileges(String roleId) throws RemoteException;
//===================================Assign Mapping Role and Entities with Privilege ==========================          
    public boolean createMappingUserWithRoles(String userId, ArrayList roleList) throws RemoteException;
    public ArrayList getAllRolesBasedOnUser(String userId) throws RemoteException;
    public boolean createDefaultRole(String userId, String roleId) throws RemoteException;
//===================================Getting Whole Role Management Details  ==========================
    public ArrayList getMyPrivilegesListFromEntity(String roleId, String entityId) throws RemoteException;
    public ArrayList getMyPrivilegesList(String userId) throws RemoteException;
    public ArrayList getMyPrivilegesListForHeader(String roleId, String entityId) throws RemoteException;
//===================================Getting Whole Entity Based on UserId  ==========================    
    public ArrayList getMyEntityList(String userId) throws RemoteException;
 //Start:[RoleMgt] For Role,Privilege,Permission Mapping
     public ArrayList getMappingDetailsForRoleSubPerm() throws RemoteException;
    public ArrayList getEntityPrivPermission()throws RemoteException;
    public boolean generateMappingPermSubPerm(String permIdArr[],String subPermIdArr[])throws RemoteException;
 //Start:[RoleMgt] For Role,Privilege,Permission Mapping


    public abstract String getUserIdBasedOnUserName(String s)
            throws RemoteException;

    public abstract boolean insertCvsUser(String s, String s1)
            throws RemoteException;
   
    public abstract List SelectAllPermissionsBasedOnRole(String s)
            throws RemoteException;
    //========================Dhivya Here:Add & Edit Artifacts================================
    public ArrayList getAllSubArtifacts(String mapPerId) throws RemoteException;
    public String getArtifactName(String mapPerId) throws RemoteException;
    public boolean createSubArtifact(String mainArtiname,String subArtiName,String mainArtiId) throws RemoteException;
    public String getSubArtifactName(String subArtifactId) throws RemoteException;
    public boolean editSubArtifact(String artifactId, String subArtifact) throws RemoteException;
    public String getViewName(String viewId) throws RemoteException;
    
  //========================Dhivya Here:Manage Artifact Mappings================================
    public ArrayList getAllViews() throws RemoteException;
    public Vector getLOBs(String viewPntId) throws RemoteException;
    public Vector getViews(String viewPntId, String lobId) throws RemoteException;
    public Vector getGroups(String viewPntId, String lobId) throws RemoteException;
    public Vector getProcessDomain(String viewPntId, String lobId, String grpId) throws RemoteException;
    public boolean insertArtifactMapDetails(HLCMenuListVO objArti) throws RemoteException;
    public boolean artifactMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId, String artifactId) throws RemoteException;
    
    public ArrayList getAllGroup() throws RemoteException;
    public ArrayList getGrpDetails() throws RemoteException;
    public ArrayList getCount() throws RemoteException;
    
    public boolean viewPointMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId) throws RemoteException;
    
    //========================Dhivya Ends Here:Manage Artifact Mappings================================
	
    public boolean insertFrameworkMapDetails(String viewId,HLCMenuListVO objMap, HLCMenuListVO objArti) throws RemoteException;  
     public ArrayList getAllViewMapList(String viewPointId) throws RemoteException;
    //kalai
    public ArrayList getViewPointDetailsList(String userId) throws RemoteException;
    public ArrayList getViewPointArtifactList(String userId,String view_point_id,String lobId,String viewId,String groupId,String processId) throws RemoteException;
    //kalai
	public ArrayList getAllViewsBasedOnViewPoint(String viewPointId) throws RemoteException;
	
	public boolean chgUserStatByAdmin(String uId) throws RemoteException;
	public boolean chgUserStatBySup(String uId,String supName,String roleId) throws RemoteException;
	
    public String getExternalAppURL(String permissName) throws RemoteException;
    
    public boolean generateMappingUserRoleWithEntities(String roleId,String deptId,String userId,ArrayList entitesList,String roleName) throws RemoteException;
    public ArrayList getMappingDetailsForUserRoleAndPrivileges(String roleId,String userId) throws RemoteException;
    public boolean chkUserMapRoleEntity(String roleId,String userId) throws RemoteException;
    public String getSupervisorEmailByEmpId(String empId) throws RemoteException;
    public String [] getAdminEmail() throws RemoteException;
    public ArrayList getEntityPrivPermByRoleId(String roleId) throws RemoteException;
    public ArrayList getDistinctEntityPrivByRoleId(String roleId) throws RemoteException;
    
    
}