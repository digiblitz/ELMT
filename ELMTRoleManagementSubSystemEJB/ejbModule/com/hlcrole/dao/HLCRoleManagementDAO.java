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
/*  Program Name    : RoleManagementDAO.java
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
import java.rmi.RemoteException;
import java.util.*;

import com.hlccommon.util.HLCMenuListVO;

public interface HLCRoleManagementDAO {
    //============================== Privilege Managemnt ==================================================
    public boolean insertPrivilege(String privilegeName);
    public boolean updatePrivilege(String privilegeId, String privilegeName);
    public boolean isPrivilegeNameExist(String privilegeName);
    public boolean isPrivilegeNameEditExist(String privilegeId, String privilegeName);
    public String [] selectPrivilege(String privilegeId);
    public ArrayList selectAllPrivilege();
    public ArrayList selectAllMapPrivilege(String roleId, String entityId);
    //============================== Permission Managemnt ==================================================
    public boolean insertPermission(String permissionName);
    public boolean updatePermission(String permissionId, String permissionName);
    public boolean isPermissionNameExist(String permissionName);
    public boolean isPermissionNameEditExist(String permissionId, String permissionName);
    public String [] selectPermission(String permissionId);
    public ArrayList selectAllPermission();
    public ArrayList selectAllMapPermission(String roleId , String entityId);
    //============================== Entity Managemnt ==================================================
    public boolean insertEntity(String entityName);
    public boolean updateEntity(String entityId, String entityName);
    public boolean isEntityNameExist(String entityName);
    public boolean isEntityNameEditExist(String entityId, String entityName);
    public String [] selectEntity(String entityId);
    public ArrayList selectAllEntity();
    //============================== Role Managemnt ==================================================
//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011

    //public boolean insertRole(String roleName);
   // public boolean updateRole(String roleId, String roleName);
    public boolean insertRole(String roleName, String roledesc, String status);
    public boolean updateRole(String roleId, String roleName,String roledesc,String status);
    //public boolean updateRole(String roleId[], String roleName[],String roledesc[],String status[]);
//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    public boolean isRoleNameExist(String roleName);
    //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    public boolean isRoleNameEditExist(String roleId, String roleName);
    //public boolean isRoleNameEditExist(String roleId[], String roleName[]);
    //public boolean deleteRole(String roleId);
    public boolean deleteRole(String chkRoleIdArr[]);
    //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
    public String [] selectRole(String roleId);
    public ArrayList selectAllRole();
    //==============================Permission with Privileges ==================================================
    public boolean insertPermissionPrivilegeMapping(String privilegeId, String permissionId, String accessName, String accessUrl);
    public boolean deleteRow(String fieldName, String fieldValue, String tableName,String deptId);
    public boolean deleteRow(String fieldName1, String fieldValue1, String fieldName2, String fieldValue2, String tableName);
    public boolean isAccessNameExist(String accessName);
    public boolean isAccessNameExistinEdit(String mapPermissionId, String accessName,String privilegeId,String permissionId);
    public ArrayList selectAllMappingDetailsForPrivilege(String privilegeId);
    public boolean updatePermissionPrivilegeMapping(String mapPermissionId, String accessName, String accessUrl,String privId);
    //==============================Enity with Privileges ==================================================
    public boolean insertEntityPrivilegeMapping(String entityId, String privilegeId);
    public ArrayList selectAllMappingDetailsForEntity(String entityId);
    //==============================Role with Entities ==================================================
    public boolean insertRoleEntityMapping(String roleId, String entityId);
    public ArrayList selectAllMappingDetailsForRole(String roleId);
    //==============================Role with Entities and Privileges ==================================================
     public boolean insertRoleEntityPrivilegeMapping(String roleId, String entityId, String privilegeId, String permissionId);
     public ArrayList selectPermissionBasedRoleEntity(String roleId, String entityId);
     public ArrayList selectDistinctPermissionBasedRoleEntity(String roleId, String entityId);
     public ArrayList selectPermissionBasedRoleEntityPrivilege(String roleId, String entityId, String privilegeId);
     public ArrayList selectMenuBasedOnRoleEntityPrivilege(String roleId, String entityId, String privilegeId);
      public ArrayList selectMenuBasedOnRoleEntitySubPrivilege(String roleId, String entityId, String privilegeId,String permissionId);
     
     //==============================User with Roles ==================================================
     public boolean insertUserWithRoleMapping(String userId, String roleId);
     public ArrayList selectAllRolesBasedOnUser(String userId);
//Start:[RoleMgt] For Role,Entity,Privilege,Permission,SubPermission Mapping
    public ArrayList selectEntityPrivPermission();

    public ArrayList selectAllSubPermission();
public boolean updatePermissionSubPermissionMapping(String permIdArr[],String subPermIdArr[]);
    

//Start:[RoleMgt] For Role,Entity,Privilege,Permission,SubPermission Mapping
public abstract String selectUserIdBasedOnUserName(String s);   
     
public abstract boolean insertCvsUser(String s, String s1);   
public abstract List SelectAllPermissionsBasedOnRole(String s);

//==============Dhivya Here: ADD & Edit Artifact==================
public ArrayList selectAllSubArtifacts(String mapPerId);
public String selectPermissionAccessName(String mapPerId);
public String selectSubArtifactName(String subArtifactId);
public boolean insertSubArtifact(String mainArtiname,String subArtiName,String mainArtiId);   
public boolean isSubArtifactNameEditExist(String artifactId, String subArtifact);
public boolean isSubArtifactNameExist(String subArtifact);
public boolean updateArtifact(String artifactId, String subArtifact);
public String selectViewName(String viewId);

//====================Dhivya Here: Manage Artifact Mapping=================
public ArrayList selectAllViews();
public Vector selectLOBs(String viewPntId); // Newly Added
public Vector selectViews(String viewPntId, String lobId); // Newly Added 
public Vector selectGroups(String viewPntId, String lobId); // Newly Added 
public Vector selectProcessDomain(String viewPntId, String lobId, String grpId); // Newly Added 
public boolean insertArtifactMapDetails(HLCMenuListVO objArti); //Updated
public boolean artifactMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId, String artifactId); // Newly Added 
public ArrayList selectAllGroup();
public ArrayList selectGrpDetails();
public ArrayList selectCount();

public boolean viewPointMapExists(String viewPntId, String lobId, String viewId, String grpId, String domProcId); // Newly Added 

//====================Dhivya Ends Here: Manage Artifact Mapping=================

public boolean insertFrameworkMapDetails(String viewPointId,HLCMenuListVO objMap, HLCMenuListVO objArti);

public ArrayList getViewPointDetailsList(String userId);
public ArrayList selectMapViewPointList(String viewPointId);
public ArrayList selectViewsBasedOnViewPoint(String viewPointId);
public ArrayList getViewPointArtifactList(String userId,String view_point_id,String lobid,String viewId,String groupId,String processId);
public boolean chgUserStatByAdmin(String uId);
public boolean chgUserStatBySup(String uId,String supName,String roleId);
public String getExternalAppURL(String perName);
public boolean insertUserRoleEntityMapping(String roleId, String entityId,String userId,String deptId,String tableName);
public ArrayList selectAllUserMappingDetailsForRole(String roleId,String userId);
public boolean chkUserMapRoleEntity(String roleId, String userId);
public String getSupervisorEmailByEmpId(String empId);
public String[] getAdminEmail();
public ArrayList getEntityPrivPermByRoleId(String roleId);
public ArrayList getDistinctEntityPrivByRoleId(String roleId);



}
