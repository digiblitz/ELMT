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

import com.hlccommon.util.Debug;
import com.hlcmrm.maps.HLCMapPrevilegeLocal;
import com.hlcmrm.maps.HLCMapPrevilegeLocalHome;
import com.hlcmrm.maps.HLCMapUserLocal;
import com.hlcmrm.maps.HLCMapUserLocalHome;
import com.hlcmrm.rolemanagement.masters.HLCPrivilegeLocal;
import com.hlcmrm.rolemanagement.masters.HLCPrivilegeLocalHome;
import com.hlcmrm.rolemanagement.masters.HLCRoleMasterLocal;
import com.hlcmrm.rolemanagement.masters.HLCRoleMasterLocalHome;
import javax.ejb.*;
import java.sql.*;
import javax.naming.*;
import java.rmi.*;
import java.util.*;

/**
 * This is the bean class for the BrahmaputraSessionBean enterprise bean.
 * Created Sep 1, 2006 10:23:55 AM
 * @author suresh
 */
public class HLCBrahmaputraSessionBean implements SessionBean, HLCBrahmaputraSessionRemoteBusiness {
    private SessionContext context;
    private InitialContext ic;
    private Connection con;
    
    private HLCPrivilegeLocalHome objPriHome;
    private HLCPrivilegeLocal objPriRemote;
    
    private HLCRoleMasterLocalHome objRoleHome;
    private HLCRoleMasterLocal objRoleRemote;

    private HLCMapPrevilegeLocalHome objMapPriRoleHome;
    private HLCMapPrevilegeLocal objMapPriRoleRemote;
    
    private HLCMapUserLocalHome objMapUserRoleHome;
    private HLCMapUserLocal objMapUserRoleRemote;
    
  
    private String roleId;
    private String roleName;
    
    private String privilegeId;
    private String privilegeName;
    
    private String mapPrivilegeId;
    
    // <editor-fold defaultstate="collapsed" desc="EJB infrastructure methods. Click the + sign on the left to edit the code.">
    // TODO Add code to acquire and use other enterprise resources (DataSource, JMS, enterprise bean, Web services)
    // TODO Add business methods or web service operations
    /**
     * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
     */
    public void setSessionContext(SessionContext aContext) {
        context = aContext;
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbActivate()
     */
    public void ejbActivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbPassivate()
     */
    public void ejbPassivate() {
        
    }
    
    /**
     * @see javax.ejb.SessionBean#ejbRemove()
     */
    public void ejbRemove() {
        
    }
    // </editor-fold>
    
  
    public void ejbCreate() throws CreateException {
        try{
            InitialContext jndiContext = getInitialContext();
            Object objPri = jndiContext.lookup("HLCPrivilegeLocalHome");
            objPriHome = (HLCPrivilegeLocalHome)objPri;
            
            Object objPlan = jndiContext.lookup("HLCRoleMasterLocalHome");
            objRoleHome = (HLCRoleMasterLocalHome)objPlan;
            
            Object objMapPriRole = jndiContext.lookup("HLCMapPrevilegeLocalHome");
            objMapPriRoleHome = (HLCMapPrevilegeLocalHome)objMapPriRole;
            
            Object objMapUserRole = jndiContext.lookup("HLCMapUserLocalHome");
            objMapUserRoleHome = (HLCMapUserLocalHome)objMapUserRole;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /*===================================Privilege  Master =============================================*/    
    public boolean createPrivilege(String privilegeName, String privilegePath) throws RemoteException {
        Debug.print("BrahmaputraSessionBean createPrivilege");
        boolean result = false;
        try{
            if (privilegeNameExists(privilegeName) == false) {
                Debug.print("This name is already Exists");
                result = false;
                // throw new EJBException("PlanName Already Exist" + planName);
            }
            else{
                objPriRemote = objPriHome.create(privilegeName,privilegePath);
                result =  true;
            }
        }
        catch(Exception exp){
            //throw new EJBException("createMedia Details: " + exp.getMessage());
            exp.printStackTrace();
        }
        finally{
            return result;
        }   
    }
    public boolean editPrivilege(String privilegeId, String privilegeName, String privilegePath) throws RemoteException, FinderException{
        Debug.print("BrahmaputraSessionBean editPrivilege");
        boolean result = false;
        try{
            if (privilegeId == null ) {
                throw new EJBException("Privilege Id can't be empty");
            }
            if (privilegeExists(privilegeId) == false) {
               // throw new EJBException("Display Sub Type ID does not Exists" + displaySubTypeId);
                result = false;
            }
            else{
                objPriRemote.setPrivilegeName(privilegeName);
                objPriRemote.setPrivilegePath(privilegePath);
                result = true;
            }
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
        finally{
            return result;
        }
    }
    public String[] getPrivilegeDetails(String privilegeId) throws RemoteException{
        Debug.print("BrahmaputraSessionBean getPrivilegeDetails");
        if (privilegeExists(privilegeId) == false) {
            //throw new EJBException("Privilege Id doesnot Exists" + privilegeId);
            return null;
        } else{
            privilegeId =  objPriRemote.getPrivilegeId();
            String privilegeName =  objPriRemote.getPrivilegeName();
            String privilegePath = objPriRemote.getPrivilegePath();
            String status = String.valueOf(objPriRemote.isActiveStatus());
            String priDet [] = {privilegeId,privilegeName,privilegePath,status};
            return priDet;
        }
    }
    public Vector getAllPrivilegeDetails() throws RemoteException,FinderException {
    Debug.print("BrahmaputraSessionBean getAllPrivilegeDetails");
    Vector priDetList = new Vector();
    Collection result = objPriHome.findByAll();
    Iterator e = result.iterator();
    while(e.hasNext()){
        HLCPrivilegeLocal objPrilocalRemote = (HLCPrivilegeLocal)e.next();
        Debug.print("AdvertiseSessionBean getAllDisplaySubTypeDetails");
        String privilegeId =  objPrilocalRemote.getPrivilegeId();
        String privilegeName =  objPrilocalRemote.getPrivilegeName();
        String privilegePath = objPrilocalRemote.getPrivilegePath();
        String status = String.valueOf(objPriRemote.isActiveStatus());
        String priDet [] = {privilegeId,privilegeName,privilegePath,status};
        priDetList.add(priDet);
    }
    return priDetList;
    }
    public boolean deletePrivilege(String privilegeId) throws RemoteException,FinderException{
        Debug.print("BrahmaputraSessionBean deletePrivilege");
        Debug.print("deletePrivilege ID:" + privilegeId);
        boolean result = false;
        if (privilegeId == null) {
            throw new EJBException("Privilege Detail ID can't be empty");
        }
        if (privilegeExists(privilegeId) == false) {
        //throw new EJBException("Privilege ID does not  Exists" + displayTypeId);
            result = false;
        }
        else{
            try {
                objPriRemote.remove();
                result = true;
            } catch (Exception ex) {
                result = false;
                throw new EJBException("Privilege Detail: " + ex.getMessage());
            }
            finally{
                return result;
            }
        }
        return result;
    }
    private boolean privilegeExists(String privilegeId) {
    Debug.print("BrahmaputraSessionBean PrivilegeExists");
    if (privilegeId != this.privilegeId) {
        try {
            objPriRemote = objPriHome.findByPrimaryKey(privilegeId);
            this.privilegeId = privilegeId;
        } catch (Exception ex) {
            return false;
        }
    }
    Debug.print("Privilege Key Exist" + privilegeId);
    return true;
    }
    private boolean privilegeNameExists(String privilegeName) {
    Debug.print("BrahmaputraSessionBean privilegeNameExists");
    boolean result = true;
    if (!(privilegeName.equals(this.privilegeName))) {
        try {
           ArrayList  priName = (ArrayList) objPriHome.findByPrivilegeName(privilegeName);
           Iterator e = priName.iterator();
           while(e.hasNext()){
               HLCPrivilegeLocal objPriLocalRemote = (HLCPrivilegeLocal)e.next();
               String PriNameRs = objPriLocalRemote.getPrivilegeName();
               if(PriNameRs.equals(privilegeName)){
                   this.privilegeName = privilegeName;
                   result = false;
                   Debug.print("privilegeNameExists alreay exists:" + privilegeName);
                   break;
               }
           }
        } catch (Exception ex) {
             result = false;
             Debug.print("Exception:" + ex.getMessage());
        }
    }
    else{
         result = false;
    }
    Debug.print("privilegeNameExists Outer Most Loop:" + result);
    return result;
    }

     /*===================================Role  Master =============================================*/    
     
    public boolean createRole(String roleName, String rolePath) throws RemoteException{
    Debug.print("BrahmaputraSessionBean createRole");
        boolean result = false;
        try{
            if (roleNameExists(roleName) == false) {
                Debug.print("This name is already Exists");
                result = false;
                // throw new EJBException("PlanName Already Exist" + planName);
            }
            else{
                objRoleRemote = objRoleHome.create(roleName, rolePath);
                result =  true;
            }
        }
        catch(Exception exp){
            //throw new EJBException("createMedia Details: " + exp.getMessage());
            exp.printStackTrace();
        }
        finally{
            return result;
        }           
    }  
    public boolean editRole(String roleId, String roleName, String rolePath) throws RemoteException, FinderException{
     Debug.print("BrahmaputraSessionBean editPrivilege");
        boolean result = false;
        try{
            if (roleId == null ) {
                throw new EJBException("Privilege Id can't be empty");
            }
            if (roleNameExists(roleId) == false) {
               // throw new EJBException("Display Sub Type ID does not Exists" + displaySubTypeId);
                result = false;
            }
            else{
                objRoleRemote.setRoleName(roleName);
                objRoleRemote.setRolePath(rolePath);
                result = true;
            }
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
        finally{
            return result;
        }        
    }
    public String[] getRoleDetails(String roleId) throws RemoteException {
        Debug.print("BrahmaputraSessionBean getRoleDetails");
        if (roleExists(roleId) == false) {
            //throw new EJBException("Privilege Id doesnot Exists" + privilegeId);
            return null;
        } else{
            String roleIdVal =  objRoleRemote.getRoleId();
            String roleName =  objRoleRemote.getRoleName();
            String rolePath = objRoleRemote.getRolePath();
            String status = String.valueOf(objRoleRemote.isActiveStatus());
            String priDet [] = {roleIdVal, roleName, rolePath,status};
            return priDet;
        }        
    }
    public Vector getAllRoleDetails() throws RemoteException,FinderException {
        Debug.print("BrahmaputraSessionBean getAllRoleDetails");
        Vector roleDetList = new Vector();
        Collection result = objRoleHome.findByAll();
        Iterator e = result.iterator();
        while(e.hasNext()){
            HLCRoleMasterLocal objRolelocalRemote = (HLCRoleMasterLocal)e.next();
            Debug.print("AdvertiseSessionBean getAllRoleDetails");
            String roleIdVal =  objRolelocalRemote.getRoleId();
            String roleName =  objRolelocalRemote.getRoleName();
            String rolePath = objRolelocalRemote.getRolePath();
            String status = String.valueOf(objRolelocalRemote.isActiveStatus());
            String roleDet [] = {roleIdVal, roleName, rolePath,status};
            roleDetList.add(roleDet);
        }
        return roleDetList;
    }    
    public boolean deleteRoleDetails(String roleId) throws RemoteException,FinderException{
        Debug.print("BrahmaputraSessionBean deleteRoleDetails");
        Debug.print("deleteRoleDetails ID:" + roleId);
        boolean result = false;
        if (roleId == null) {
            throw new EJBException("Privilege Detail ID can't be empty");
        }
        if (roleExists(roleId) == false) {
        //throw new EJBException("Privilege ID does not  Exists" + displayTypeId);
            result = false;
        }
        else{
            try {
                objRoleRemote.remove();
                result = true;
            } catch (Exception ex) {
                result = false;
                throw new EJBException("Privilege Detail: " + ex.getMessage());
            }
            finally{
                return result;
            }
        }
        return result;
    } 
    private boolean roleExists(String roleId) {
    Debug.print("BrahmaputraSessionBean roleExists");
    if (roleId != this.roleId) {
        try {
            objRoleRemote = objRoleHome.findByPrimaryKey(roleId);
            this.roleId = roleId;
        } catch (Exception ex) {
            return false;
        }
    }
    Debug.print("Privilege Key Exist" + roleId);
    return true;
    }
    private boolean roleNameExists(String roleName) {
    Debug.print("BrahmaputraSessionBean roleNameExists");
    boolean result = true;
    if (!(roleName.equals(this.roleName))) {
        try {
           ArrayList  roName = (ArrayList) objRoleHome.findByRoleName(roleName);
           Iterator e = roName.iterator();
           while(e.hasNext()){
               HLCRoleMasterLocal objRoleLocalRemote = (HLCRoleMasterLocal)e.next();
               String roleNameRs = objRoleLocalRemote.getRoleName();
               if(roleNameRs.equals(roleName)){
                   this.roleName = roleName;
                   result = false;
                   Debug.print("roleNameExists alreay exists:" + roleName);
                   break;
               }
           }
        } catch (Exception ex) {
             result = false;
             Debug.print("Exception:" + ex.getMessage());
        }
    }
    else{
         result = false;
    }
    Debug.print("roleNameExists Outer Most Loop:" + result);
    return result;
    }
    
    //============================Map Privilege Details =======================================/
    public boolean mappingRolesAndPrivileges(String roleId,Vector clPrivilege) throws RemoteException{
       Debug.print("BrahmaputraSessionBean mappingRolesAndPrivileges Role ID:" + roleId);
       boolean result = false;
       try{
            if(roleId==null){
                Debug.print("BrahmaputraSessionBean  Role Id can't be empty.");
                result = false;
            } 
            else{
                ArrayList roleList =  (ArrayList)objMapPriRoleHome.findByRoleId(roleId);
                if(roleList!=null){
                    Iterator e = roleList.iterator();
                    Debug.print("BrahmaputraSessionBean mappingRolesAndPrivileges Deletion Started....");
                    while(e.hasNext()){
                       HLCMapPrevilegeLocal localRoleRemote = (HLCMapPrevilegeLocal)e.next();
                       localRoleRemote.remove();
                       Debug.print("BrahmaputraSessionBean mappingRolesAndPrivileges Deleting  Mapped Role ID:" + roleId);
                    }
                    Debug.print("BrahmaputraSessionBean mappingRolesAndPrivileges Deletion Completed..");
                    //result = true;
                }
               
                if(clPrivilege!=null){
                    Debug.print("BrahmaputraSessionBean mappingRolesAndPrivileges Starting Mapping");
                    Enumeration mapPrivilege = clPrivilege.elements();
                    while(mapPrivilege.hasMoreElements()){
                        String splitedPrivilege = (String)mapPrivilege.nextElement();
                        if(splitedPrivilege!=null){
                            objMapPriRoleRemote = objMapPriRoleHome.create(roleId,splitedPrivilege);
                            Debug.print("BrahmaputraSessionBean Role Mapping for :" + splitedPrivilege);
                        }
                    }
                    //result = true;
                    Debug.print("BrahmaputraSessionBean  Role and Privilege Mapping Completed.");
                }
              result = true;  
           }
           Debug.print("BrahmaputraSessionBean exiting from mappingRolesAndPrivileges.");
        }
        catch(Exception exp){
            result = false;
            Debug.print("Exception in mappingRolesAndPrivileges:" + exp);
            exp.printStackTrace();
        }
        finally{
            return result;
        }
    }
    
    public Vector getAllMappedRolePrivilegeDetails() throws RemoteException,FinderException{
        Debug.print("BrahmaputraSessionBean getAllMappedRolePrivilegeDetails");
        Vector mapDetList = new Vector();
        Collection result = objMapPriRoleHome.findByAll();
        Iterator e = result.iterator();
        while(e.hasNext()){
            HLCMapPrevilegeLocal objMaplocalRemote = (HLCMapPrevilegeLocal)e.next();
            Debug.print("AdvertiseSessionBean getAllMappedRolePrivilegeDetails");
            String mapPrivilegeIdVal =  objMaplocalRemote.getMapPrivilegeId();
            String roleId =  objMaplocalRemote.getRoleId();
            String privilegeIdVal = objMaplocalRemote.getPrivilegeId();
            String roleDet [] = {mapPrivilegeIdVal, roleId, privilegeIdVal};
            mapDetList.add(roleDet);
        }
        return mapDetList;
    }
    
    public Vector getMappedRoleDetails(String roleId) throws RemoteException,FinderException{
        Debug.print("BrahmaputraSessionBean getMappedRoleDetails");
        Vector mapDetList = new Vector();
        Collection result = objMapPriRoleHome.findByRoleId(roleId);
        Iterator e = result.iterator();
        while(e.hasNext()){
            HLCMapPrevilegeLocal objMaplocalRemote = (HLCMapPrevilegeLocal)e.next();
            Debug.print("AdvertiseSessionBean getMappedRoleDetails");
            String mapPrivilegeIdVal =  objMaplocalRemote.getMapPrivilegeId();
            String roleIdVal =  objMaplocalRemote.getRoleId();
            String privilegeIdVal = objMaplocalRemote.getPrivilegeId();
            String roleDet [] = {mapPrivilegeIdVal, roleIdVal, privilegeIdVal};
            mapDetList.add(roleDet);
        }
        return mapDetList;
    }
    //============================Map User Role Details =======================================/    
    public boolean mappingUserAndRole(String userId, String mapPrivilegeId, String userPrivilegePath) throws RemoteException{
    Debug.print("BrahmaputraSessionBean mappingUserAndRole User ID:" + userId);
       boolean result = false;
       try{
            if(userId==null){
                Debug.print("BrahmaputraSessionBean  userId Id can't be empty.");
                result = false;
            } 
            else{
                ArrayList userList =  (ArrayList)objMapUserRoleHome.findByUserId(userId);
                if(userList!=null){
                    Iterator e = userList.iterator();
                    Debug.print("BrahmaputraSessionBean mappingUserAndRole Deletion Started....");
                    while(e.hasNext()){
                       HLCMapUserLocal localUserRoleRemote = (HLCMapUserLocal)e.next();
                       localUserRoleRemote.remove();
                       Debug.print("BrahmaputraSessionBean mappingUserAndRole Deleting  Mapped User ID:" + userId);
                    }
                    Debug.print("BrahmaputraSessionBean mappingUserAndRole Deletion Completed..");
                    //result = true;
                }
               
                if(mapPrivilegeId!=null){
                    Debug.print("BrahmaputraSessionBean mappingUserAndRole Starting Mapping");
                    objMapUserRoleRemote = objMapUserRoleHome.create(userId, mapPrivilegeId, userPrivilegePath);
                    Debug.print("BrahmaputraSessionBean User Mapping for :" + mapPrivilegeId);
                    Debug.print("BrahmaputraSessionBean  User and Role Mapping Completed.");
                }
                result = true;  
           }
           Debug.print("BrahmaputraSessionBean exiting from mappingUserAndRole.");
        }
        catch(Exception exp){
            result = false;
            Debug.print("Exception in mappingRolesAndPrivileges:" + exp);
            exp.printStackTrace();
        }
        finally{
            return result;
        }        
      }
    
   // public Vector getMappedUserAndRoleDetails(String userId) throws RemoteException,FinderException{
        
   // }
    
    private boolean mappingExists(String mapPrivilegeId) {
        Debug.print("BrahmaputraSessionBean mappingExists");
        if (mapPrivilegeId != this.mapPrivilegeId) {
            try {
                objMapPriRoleRemote = objMapPriRoleHome.findByPrimaryKey(mapPrivilegeId);
                this.mapPrivilegeId = mapPrivilegeId;
            } catch (Exception ex) {
                return false;
            }
        }
        Debug.print("Mapping Privilege Key Exist" + mapPrivilegeId);
        return true;
    }
     
     
     
    public InitialContext getInitialContext() throws javax.naming.NamingException {
        if( this.ic == null ) {
            ic = new InitialContext();
        }
        Debug.print("BrahmaputraSessionBean: This is from getInitialContext()");
        return ic;
    }
}
