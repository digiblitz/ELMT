/*
 * TestClient.java
 *
 * Created on September 1, 2006, 1:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hlcmrm.rolemanagement.masters;

import com.hlccommon.util.Debug;
import com.hlcmrm.rolemanagement.HLCBrahmaputraSessionRemote;
import com.hlcmrm.rolemanagement.HLCBrahmaputraSessionRemoteHome;
import javax.naming.Context;
import java.util.Properties;
import java.util.Vector;


public class HLCTestClient {
    public static void main( String [] args )   {
        try {
            Context jndiContext = getInitialContext();
            Object obj=jndiContext.lookup("ejb/HLCBrahmaputraSession");
            HLCBrahmaputraSessionRemoteHome advHome = (HLCBrahmaputraSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote advRemote = advHome.create();
            
            
//*================================ Privilege Master Start=======================================================================*/            
            ///============================Create Privilege ================*/
        //   boolean result = advRemote.createPrivilege("Sample Privilege 2" ,"www.digiBlitz.com");
        //    Debug.print("Result:" + result);
       //     result = advRemote.createPrivilege("Sample Privilege 3" ,"www.yahoo.com");            
       //     Debug.print("Result:" + result);
        //    result = advRemote.createPrivilege("Sample Privilege 4" ,"www.cricinfo.com");   
        //    Debug.print("Result:" + result);
         
            
            ///============================Edit Privilege ================*/
           // boolean result =  advRemote.editPrivilege("7a3d6c44-d8d6-45c2-823b-ae7d3385a88e", "Suresh" ,"/digiblitz.com.do");
           // Debug.print("Result:" + result);
/*             public boolean createPrivilege(String privilegeName, String privilegePath) throws RemoteException;  
                public boolean editPrivilege(String privilegeId, String privilegeName, String privilegePath) throws RemoteException, FinderException;
                public String[] getPrivilegeDetails(String privilegeId) throws RemoteException; 
                public boolean deletePrivilege(String privilegeId) throws RemoteException,FinderException;
                public Vector getAllPrivilegeDetails() throws RemoteException,FinderException; 
 *    public boolean createRole(String roleName, String rolePath) throws RemoteException;  
 *
    public boolean editRole(String roleId, String roleName, String rolePath) throws RemoteException, FinderException;
    public String[] getRoleDetails(String roleId) throws RemoteException; 
    public boolean deleteRoleDetails(String roleId) throws RemoteException,FinderException;
    public Vector getAllRoleDetails() throws RemoteException,FinderException; 
    */        
            ///============================View particular Privilege ================*/
       /*    String[] s = advRemote.getPrivilegeDetails("d41c3767-90db-41a6-962c-a780ea664792");
            
           //String priDet [] = {privilegeId,privilegeName,privilegePath};
            Debug.print("Pri Id:" + s[0]);
            Debug.print("Pri Name:" + s[1]);
            Debug.print("Pri Path:" + s[2]);
         */   
            // String[] s1 = advRemote.getPrivilegeDetails("7a3d6c44-d8d6-45c2-823b-ae7d3385a88e");
            
           //String priDet [] = {privilegeId,privilegeName,privilegePath};
           // Debug.print("Pri Id:" + s1[0]);
          //  Debug.print("Pri Name:" + s1[1]);
          //  Debug.print("Pri Path:" + s1[2]);
             
             ///============================View All Privilege details================*/
         /* 
          
            Vector v = (Vector)advRemote.getAllPrivilegeDetails();
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                String[] s = (String [])e.nextElement();
                Debug.print("===============================");
                Debug.print("Media Id:" + s[0]);
                Debug.print("Media Name:" + s[1]);
                Debug.print("Media Description:" + s[2]);
               // Debug.print("Media Status:" + s[3]);
                Debug.print("===============================");
            }
       */
            //======================Remove Privilege=====================//
          // boolean result = advRemote.deletePrivilege("7a3d6c44-d8d6-45c2-823b-ae7d3385a88e");
          // Debug.print("Result:" + result);
            //*================================END of Privilege Master=======================================================================*/
            
//*================================ Role Master Start=======================================================================*/            
            ///============================Create Role ================*/
         //  result = advRemote.createRole("Software Engineer" ,"/www.sury.com");
         //  Debug.print("Result:" + result);
         // result = advRemote.createRole(" Designe Engineer" ,"/www.design.com");
        //  Debug.print("Result:" + result);
            
            
            
            ///============================Edit Privilege ================*/
            //result =  advRemote.editRole("d6d25db9-4713-4a4b-ac5b-ccc46751b250", "Suresh" ,"/digiblitz.com.do");
           // Debug.print("Result:" + result);
            /* public boolean createRole(String roleName, String rolePath) throws RemoteException;  
            *
            public boolean editRole(String roleId, String roleName, String rolePath) throws RemoteException, FinderException;
            public String[] getRoleDetails(String roleId) throws RemoteException; 
            public boolean deleteRoleDetails(String roleId) throws RemoteException,FinderException;
            public Vector getAllRoleDetails() throws RemoteException,FinderException; 
            */        
            ///============================View particular Privilege ================*/
          // String[] s = advRemote.getRoleDetails("d6d25db9-4713-4a4b-ac5b-ccc46751b250");
            // String priDet [] = {roleIdVal, roleName, rolePath,status};
           //String priDet [] = {privilegeId,privilegeName,privilegePath};
           // Debug.print("Role Id:" + s[0]);
          //  Debug.print("Role Name:" + s[1]);
         //   Debug.print("Role Path:" + s[2]);
           
            // String[] s1 = advRemote.getPrivilegeDetails("7a3d6c44-d8d6-45c2-823b-ae7d3385a88e");
            
           //String priDet [] = {privilegeId,privilegeName,privilegePath};
           // Debug.print("Pri Id:" + s1[0]);
          //  Debug.print("Pri Name:" + s1[1]);
          //  Debug.print("Pri Path:" + s1[2]);
             
             ///============================View All Role details================*/
        
          /*
            Vector v = (Vector)advRemote.getAllRoleDetails();
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                String[] s = (String [])e.nextElement();
                Debug.print("===============================");
                Debug.print("Role Id:" + s[0]);
                Debug.print("Role Name:" + s[1]);
                Debug.print("Role Description:" + s[2]);
                Debug.print("Role Status:" + s[3]);
                Debug.print("===============================");
            }
       
           */
            //======================Remove Role=====================//
          // boolean result = advRemote.deleteRoleDetails("80675aa7-74bc-4e37-a830-fe05b281c9b3");
          // Debug.print("Result:" + result);
            //*================================END of Role Master=======================================================================*/
            
            
            //clPrivilege.add("");
            //clPrivilege.add();
            try{
                Vector clPrivilege = new Vector();
            //    clPrivilege.addElement("da527c76-d970-4a7f-b186-9dc650108efe");
              //  String val1 = "cc74d8bc-1dd3-4b20-a9d5-eb1c18d289cc";
              //  clPrivilege.addElement(val1);
               // clPrivilege.addElement("35e2898f-e707-43c8-b0cf-05c4febd770a");
             //   boolean result =  advRemote.mappingRolesAndPrivileges("7ae2437b-ad28-4946-b02b-c6b6d15e2245",clPrivilege);
             //   Debug.print("Mapping Result:" + result);
               // boolean result =  advRemote.getMappedRoleDetails("40c67b8a-b1de-4753-9cd5-b8c20cad7f8a");
             
/*            Vector v = (Vector)advRemote.getAllMappedRolePrivilegeDetails();
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                String[] s = (String [])e.nextElement();
                Debug.print("===============================");
                Debug.print("Role Id:" + s[0]);
                Debug.print("Role Name:" + s[1]);
                Debug.print("Role Description:" + s[2]);

                Debug.print("===============================");
 *
 *
 */
            //}
                
    /*    Vector v = (Vector)advRemote.getMappedRoleDetails("40c67b8b-b1de-4753-9cd5-b8c20cad7f8a");
        if(v==null || v.size()==0){
            Debug.print("Null Value");
        }
            Enumeration e = v.elements();
            while(e.hasMoreElements()){
                String[] s = (String [])e.nextElement();
                Debug.print("===============================");
                Debug.print("Map Id:" + s[0]);
                Debug.print("Role ID:" + s[1]);
                Debug.print("Pre Id:" + s[2]);

                Debug.print("===============================");
                
            }
     */
           boolean  result =  advRemote.mappingUserAndRole("45acee3b-493e-4fe7-b523-3db914bb00cd", "04fd0d81-79fe-45b2-bce7-5824d6f4d069", "http://www.suresh.net");
            Debug.print("Mapping User and Role Result:" + result);   
                
            }
            catch(Exception e){
                 Debug.print("Exception:" + e.getMessage());
            }
     
                
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
