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
package com.hlcmrm.rolemanagement;

import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import java.rmi.*;
import java.util.*;


/**
 * This is the remote interface for BrahmaputraSession enterprise bean.
 */
public interface HLCBrahmaputraSessionRemote extends EJBObject, HLCBrahmaputraSessionRemoteBusiness {
   //============================Privillage Master =======================================/
    public boolean createPrivilege(String privilegeName, String privilegePath) throws RemoteException;  
    public boolean editPrivilege(String privilegeId, String privilegeName, String privilegePath) throws RemoteException, FinderException;
    public String[] getPrivilegeDetails(String privilegeId) throws RemoteException; 
    public boolean deletePrivilege(String privilegeId) throws RemoteException,FinderException;
    public Vector getAllPrivilegeDetails() throws RemoteException,FinderException; 

   //============================Role Master =======================================/
    public boolean createRole(String roleName, String rolePath) throws RemoteException;  
    public boolean editRole(String roleId, String roleName, String rolePath) throws RemoteException, FinderException;
    public String[] getRoleDetails(String roleId) throws RemoteException; 
    public boolean deleteRoleDetails(String roleId) throws RemoteException,FinderException;
    public Vector getAllRoleDetails() throws RemoteException,FinderException; 
//============================Map Privilege Details =======================================/
    public boolean mappingRolesAndPrivileges(String roleId,Vector clPrivilege) throws RemoteException;  
    public Vector getAllMappedRolePrivilegeDetails() throws RemoteException, FinderException;
    public Vector getMappedRoleDetails(String roleId) throws RemoteException,FinderException;
//============================Map User Details =======================================/
    public boolean mappingUserAndRole(String userId, String mapPrivilegeId, String userPrivilegePath) throws RemoteException;
    //public Vector getMappedUserAndRoleDetails(String userId) throws RemoteException,FinderException;
    //public Vector getMappedUserAndRoleDetails(String userId) throws RemoteException,FinderException;
    
}
