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
package com.hlcrole.management;

import javax.naming.Context;
import java.util.Properties;
import java.util.*;

public class HLCTestClient {
    public static void main( String [] args )   {
        try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCBrahmaputraSessionRemoteHome");
            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();
            
            /*=======================
             //String roleName = "Admin";
            //String roleName = "  ";
            //String roleName = "Admin";
            
            boolean result = roleRemote.createRole(roleName);
            Debug.print(" Role Creation Status:" + result); 
             */
            
            /*String roleId = "";
            String roleId = "1974f5c7-f6d4-4266-a9c6-0448ad0ff32f";
            
            String role[] = roleRemote.getRole(roleId);
            Debug.print(" Role Name:" + role[1]); 
             */
            /*   
            String roleId = "";
            //String roleId = "1974f5c7-f6d4-4266-a9c6-0448ad0ff32f";
            String roleName = "Admin1";
            
            boolean result = roleRemote.editRole(roleId,roleName);
            Debug.print(" Role Creation Status:" + result); 
           
           
            String roleId = "1974f5c7-f6d4-4266-a9c6-0448ad0ff32f";
            String role[] = roleRemote.getRole(roleId);
            Debug.print(" Role Name:" + role[1]); 
            */
            
              /*       
           ArrayList li = (ArrayList)roleRemote.getAllRoles();
            Iterator its = li.iterator();
            while(its.hasNext()){
                String freqlist [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("Id:" + freqlist[0]);
                Debug.print("Name:" + freqlist[1]);
                Debug.print("==============================="); 
            }
            */
            
            /*
             String privilegeName = "Manage Horse";
            
            boolean result = roleRemote.createPrivilege(privilegeName);
            Debug.print(" Privilege Creation Status:" + result); 
             */
            
            
            //String privilegeId = "930c4c45-f079-45bb-a35b-a163ae63fc34";

           //String privDet[] = roleRemote.getPrivilege(privilegeId);
           // Debug.print(" Priv Name:" + privDet[1]); 
            
            
            
/* 
            ArrayList li = (ArrayList)roleRemote.getAllPrivilege();
            Iterator its = li.iterator();
            while(its.hasNext()){
                String freqlist [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("Id:" + freqlist[0]);
                Debug.print("Name:" + freqlist[1]);
                Debug.print("==============================="); 
            }
           
            ArrayList li = (ArrayList)roleRemote.getAllPermission();
            Iterator its = li.iterator();
            while(its.hasNext()){
                String freqlist [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("Id:" + freqlist[1]);
                Debug.print("Name:" + freqlist[1]);
                Debug.print("==============================="); 
            }
 */

          /*  String entityName = "";
            
            boolean result = roleRemote.createEntity(entityName);
            Debug.print(" Entity Creation Status:" + result); 
            */
            
           /* String entityId = "d315b82f-fee7-44df-a89f-1dec846b9602";
            String entityDet[] = roleRemote.getEntity(entityId);
            Debug.print(" EntityName:" + entityDet[1]); 
            */
            
      /*      
         ArrayList li = (ArrayList)roleRemote.getAllEntity();
            Iterator its = li.iterator();
            while(its.hasNext()){
                String freqlist [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("Id:" + freqlist[0]);
                Debug.print("Name:" + freqlist[1]);
                Debug.print("==============================="); 
            }
    */
   /*   String priId = "14a545b0-83a2-4547-881d-9f273262a279";
        ArrayList li = new ArrayList();
        
        String perId = "508f0482-7936-4e4d-8208-651c2268d6b8";
        String accessName = "CreateMedia";
        String accessUrl = "advCreateMedia.do";
        String priList[] = {perId,accessName,accessUrl};
        li.add(priList);
        
        perId = "d8eeb4d2-9779-4605-9e4f-6c93ecb89c21";
        accessName = "EditMedia";
        accessUrl = "advEditMedia.do";
        String priList1[] = {perId, accessName, accessUrl};
        li.add(priList1);
        
        
        perId = "280b2632-df96-4be6-b565-5e130c12c0de";
        accessName = "DeleteMedia";
        accessUrl = "advDeleteMedia.do";
        String priList2[] = {perId, accessName, accessUrl};
        li.add(priList2);
        
        perId = "905a0554-23c7-4442-8613-0983a6ea051d";
        accessName = "ViewMedia";
        accessUrl = "advViewMedia.do";
        String priList3 [] = {perId, accessName, accessUrl};
        li.add(priList3);
        roleRemote.createMappingPermissionToPrivilege(priId,li);
    *
    */
       
   /*     ArrayList li = (ArrayList)roleRemote.getMappingDetailsForPermissionAndPrivilege("14a545b0-83a2-4547-881d-9f273262a279");
            Iterator its = li.iterator();
            while(its.hasNext()){
                String mapPermissionId [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("mapPermissionId:" + mapPermissionId[0]);
                Debug.print("permissionName:" + mapPermissionId[1]);
                Debug.print("accessName:" + mapPermissionId[2]);
                Debug.print("accessUrl:" + mapPermissionId[3]);
                Debug.print("==============================="); 
            }
      */     
  /*          
        String priId = "14a545b0-83a2-4547-881d-9f273262a279";
        ArrayList li = new ArrayList();
        
        String map_perId = "fe98964b-aae4-48ca-8de2-4e5630c8d1fe";
        String accessName = "CreateMediaUpdate";
        String accessUrl = "advCreateMedia.do/update";
        String priList[] = {map_perId,accessName,accessUrl};
        li.add(priList);
        
        map_perId = "5abbc9ad-8a00-460f-9b89-590854db1e39";
        accessName = "EditMediaUpdate";
        accessUrl = "advEditMedia.do/update";
        String priList1[] = {map_perId, accessName, accessUrl};
        li.add(priList1);
        
        
        map_perId = "bd61e861-1db3-40fa-9e38-663f88792b5c";
        accessName = "DeleteMediaUpdate";
        accessUrl = "advDeleteMedia.do/update";
        String priList2[] = {map_perId, accessName, accessUrl};
        li.add(priList2);
        
        map_perId = "a4a26a19-2edc-4410-9212-ab874451f791";
        accessName = "ViewMediaUpdate";
        accessUrl = "advViewMedia.do/update";
        String priList3 [] = {map_perId, accessName, accessUrl};
        li.add(priList3);
        roleRemote.editMappingPermissionToPrivilege(li);
*/        
        
        //=====================Create Mapping Entites with Privillege =======
/*            
           String entityId = "82302a82-8c67-4437-b10a-54c78bbb2629";
           ArrayList al = new ArrayList();
           al.add("14a545b0-83a2-4547-881d-9f273262a279");
           al.add("9e801347-8829-42a0-91c0-73bdf46f1299");
           al.add("5e781863-ff7f-48ad-970f-01ad4abc85db");
           boolean result = roleRemote.generateMappingEnitityWithPrivileges(entityId,al);
           Debug.print("Result: " + result);
 */
        //=====================Getting  Mapped Entites with Privillege ======= 

  /*          ArrayList li = (ArrayList)roleRemote.getMappingDetailsForEnitityAndPrivileges("82302a82-8c67-4437-b10a-54c78bbb2629");
            Iterator its = li.iterator();
            while(its.hasNext()){
                String entMapList [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("mapPrivilegeId:" + entMapList[0]);
                Debug.print("entityIdVal:" + entMapList[1]);
                Debug.print("privilegeId:" + entMapList[2]);
                Debug.print("entityName:" + entMapList[3]);
                Debug.print("privilegeName:" + entMapList[4]);
                Debug.print("==============================="); 
            }
   **/
 //String[] priMapList = {mapPrivilegeId, entityIdVal, privilegeId, entityName, privilegeName};
            
    /*          ArrayList li = (ArrayList)roleRemote.getAllRoles();
            Iterator its = li.iterator();
            while(its.hasNext()){
                String freqlist [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("Id:" + freqlist[0]);
                Debug.print("Name:" + freqlist[1]);
                Debug.print("==============================="); 
            }
    */
            
              //=====================Create Mapping Entites with Privillege =======
    /*      
           String roleId = "e133a1b0-b9c1-4872-bfb6-f28f7b96fd79";
           ArrayList al = new ArrayList();
           al.add("82302a82-8c67-4437-b10a-54c78bbb2629");
           al.add("726867a7-7a30-4ed7-84cb-6655b96c4592");
           al.add("5293826e-541d-4dd0-b96d-2504bd7848c0");
           boolean result = roleRemote.generateMappingRoleWithEntities(roleId,al);
           Debug.print("Result: " + result);
 */
            
      /*     ArrayList li = (ArrayList)roleRemote.getMappingDetailsForRoleAndAndPrivileges("e133a1b0-b9c1-4872-bfb6-f28f7b96fd79");
            Iterator its = li.iterator();
            while(its.hasNext()){
                String roleEntMapList [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("mapEntityId:" + roleEntMapList[0]);
                Debug.print("roleIdVal:" + roleEntMapList[1]);
                Debug.print("entityId:" + roleEntMapList[2]);
                Debug.print("roleName:" + roleEntMapList[3]);
                Debug.print("entityName:" + roleEntMapList[4]);
                Debug.print("==============================="); 
                //String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName};
            }
        */    
           
        /*   String roleId = "e133a1b0-b9c1-4872-bfb6-f28f7b96fd79";
           String entityId = "95cc2d5b-6a8d-4a09-8774-c5f5fb213d42";
           
           ArrayList al = new ArrayList();
           String priPerList1 [] = {"b2d5fbf9-932c-45af-8833-c75ecca6ba7a","905a0554-23c7-4442-8613-0983a6ea051d"};
           String priPerList2 [] = {"14a545b0-83a2-4547-881d-9f273262a279","280b2632-df96-4be6-b565-5e130c12c0de"};
           String priPerList3 [] = {"5e781863-ff7f-48ad-970f-01ad4abc85db","508f0482-7936-4e4d-8208-651c2268d6b8"};
           
           al.add(priPerList1);
           al.add(priPerList2);
           al.add(priPerList3);
           boolean result = roleRemote.generateMappingRoleWithEntitiesAndPrivileges(roleId, entityId, al);
           Debug.print("Result: " + result);
         **/
            //public boolean generateMappingRoleWithEntitiesAndPrivileges(String roleId, String entityId, ArrayList priPerList);
            
           //  String[] entMapList = {roleName, entityName, privilegeName, permissionName};
       /*     ArrayList li = (ArrayList)roleRemote.getMenuBasedOnEntityRolePrivileges("e133a1b0-b9c1-4872-bfb6-f28f7b96fd79", "82302a82-8c67-4437-b10a-54c78bbb2629", "b2d5fbf9-932c-45af-8833-c75ecca6ba7a");
            Iterator its = li.iterator();
            while(its.hasNext()){
                String roleEntMapList [] = (String []) its.next(); //{frequencyId,frequencyName,mediaId};
                Debug.print("===============================");
                Debug.print("mapEntityId:" + roleEntMapList[0]);
                Debug.print("roleIdVal:" + roleEntMapList[1]);
                Debug.print("entityId:" + roleEntMapList[2]);
                Debug.print("roleName:" + roleEntMapList[3]);
                Debug.print("entityId:" + roleEntMapList[4]);
                Debug.print("roleName:" + roleEntMapList[5]);
                Debug.print("==============================="); 
                //String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName};
            }
      */
            
            //Mapping User with Roles
            
        /*   String userId = "ff093ecd-e823-4e36-acb9-cef71534dd74";
           ArrayList al = new ArrayList();
           al.add("87f35734-b7f2-45c8-b82a-ab20244976b6");
           al.add("e133a1b0-b9c1-4872-bfb6-f28f7b96fd79");
           boolean result = roleRemote.createMappingUserWithRoles(userId,al);
           Debug.print("Result: " + result);
          */
  /*        ArrayList li = (ArrayList)roleRemote.getAllRolesBasedOnUser("ff093ecd-e823-4e36-acb9-cef71534dd74");
            Iterator its = li.iterator();
            while(its.hasNext()){
                String roleMapList [] = (String []) its.next(); // String[] rlList = {userMapId, userIdVal, roleId};
                Debug.print("===============================");
                Debug.print("userMapId:" + roleMapList[0]);
                Debug.print("userIdVal:" + roleMapList[1]);
                Debug.print("roleId:" + roleMapList[2]);
                Debug.print("==============================="); 
                //String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName};
            }
   **/
/*                ArrayList roleList = (ArrayList)roleRemote.getMyPrivilegesList("ff093ecd-e823-4e36-acb9-cef71534dd74");
                Iterator itRoleList = roleList.iterator();
                while(itRoleList.hasNext()){
                    String entityName = (String)itRoleList.next();
                    ArrayList privlist = (ArrayList)itRoleList.next();
                    Iterator list = privlist.iterator();
                    Debug.print("################"+ roleName + "#########################");
                    Debug.print("################"+ entityName + "#########################");
                    while(list.hasNext()){
                        String privilegeName = (String)list.next();
                        ArrayList li = (ArrayList)list.next();
                        Iterator its = li.iterator();
                        Debug.print("==============="+ privilegeName +"================");
                        while(its.hasNext()){
                            String roleMapList [] = (String []) its.next(); 
                            //{roleName, entityName, privilegeName, permissionName, accessName, accessUrl };
                            Debug.print("---------------------------------");
                            Debug.print("privilegeName:" + roleMapList[2]);
                            Debug.print("permissionName:" + roleMapList[3]);
                            Debug.print("accessName:" + roleMapList[4]);
                            Debug.print("accessUrl:" + roleMapList[5]);
                            Debug.print("---------------------------------");
                            //String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName};
                        }
                        Debug.print("===============================");
                    }
                      Debug.print("#########################################");
                }
            */
            
     /*       ArrayList roleList = (ArrayList)roleRemote.getMyEntityList("ff093ecd-e823-4e36-acb9-cef71534dd74");
            Iterator itRoleList = roleList.iterator();
            while(itRoleList.hasNext()){
                String strRolelist[]= (String[])itRoleList.next();
                String roleName = strRolelist[1];
                String roleId = strRolelist[0];
                
                Debug.print("RoleId:" + roleId);
                
                ArrayList privlist = (ArrayList)itRoleList.next();
                Debug.print("" + privlist);
                if(privlist!=null && privlist.size()!=0){
                    Iterator list = privlist.iterator();
                    Debug.print("################"+ roleName + "#########################");
                    while(list.hasNext()){
                        String entList[] = (String[])list.next();
                        if(entList.length!=0){
                             String entName = entList[1];
                             Debug.print("====================================");
                             Debug.print("Ent Name:" + entName );
                             Debug.print("====================================");
                        }
                    }
                }
            }
            */
            
         /*   ArrayList privilegeList = (ArrayList)roleRemote.getPermissionBasedOnEntityRole("83B7ED61-E606-4A84-8B00-C09BC49325DB","95CC2D5B-6A8D-4A09-8774-C5F5FB213D42");
            Debug.print("privilegeList:" + privilegeList); 
            if(privilegeList!=null && privilegeList.size()!=0){
                    Iterator itPrivRoleList = privilegeList.iterator();
                    while(itPrivRoleList.hasNext()){
                            String strRolelist[]= (String[])itPrivRoleList.next();
                            //String DBEntityId = strRolelist[];
                            String DBEntityName = strRolelist[2];
                            System.out.println("DBEntityName:" + DBEntityName );
                            //{roleName, entityName, privilegeName, permissionName, privilegeIdVal, permissionId};
                    }
            }
          **/
            
            
      /*    
            ArrayList privilegeList = (ArrayList)roleRemote.getMyPrivilegesListFromEntity("E133A1B0-B9C1-4872-BFB6-F28F7B96FD79","82302A82-8C67-4437-B10A-54C78BBB2629");
            Debug.print("privilegeList:" + privilegeList); 
            if(privilegeList!=null && privilegeList.size()!=0){
                    Iterator itPrivRoleList = privilegeList.iterator();
                    while(itPrivRoleList.hasNext()){
                            String strRolelist[]= (String[])itPrivRoleList.next();
                            //{roleName, entityName, privilegeName, permissionName, privilegeIdVal, permissionId};
                            Debug.print("#################################");
                            Debug.print("PrivilegeName:" + strRolelist[1]);
                            Debug.print("#################################");
                           
                            ArrayList menuList = (ArrayList) itPrivRoleList.next();
                            Iterator itMenu = menuList.iterator();
                            while(itMenu.hasNext()){
                                MenuListVO menuVO = (MenuListVO) itMenu.next();
                                if(menuVO.getRoleId()!=null && menuVO.getEntityId()!=null){
                                    Debug.print("================================");
                                    Debug.print("PermissionName:" + menuVO.getPermissionName());
                                    Debug.print("AccessName:" + menuVO.getAccessName());
                                    Debug.print("AccessUrl:" + menuVO.getAccessUrl());
                                    Debug.print("================================");
                                }
                            }
                    }
            }
            */
                    
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static Context getInitialContext()
        throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
}
