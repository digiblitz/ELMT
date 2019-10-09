/*
 * ActionRoleMangement.java
 *
 * Created on September 29, 2006, 6:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 * [RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
 */

package com.role.action;

/**
 *
 * @author suresh
 */
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCMenuListVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.*;

import com.hlcrole.management.*;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;
import com.hlcutil.HLCMemberVO;
import com.util.XMLParser;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;

public class ActionRoleMangement extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          try {
                MessageResources mr=getResources(request);
                String namingfactory=mr.getMessage("ejbclient.namingfactory");
                String contextfactory=mr.getMessage("ejbclient.contextfactory");
                String urlprovider=mr.getMessage("ejbclient.urlprovider");
                String lookupip=mr.getMessage("ejbclient.ip");
                String jndiname=mr.getMessage("jndi.rolemanagement");

                System.setProperty(namingfactory,contextfactory);
                System.setProperty(urlprovider,lookupip);
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);
                Debug.print("ActionRoleMangement.jndiname:" + jndiname);


                HttpSession session = request.getSession(true);
                String userId = (String) session.getAttribute("userId");
                HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCBrahmaputraSessionRemoteHome.class);
                HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

                String jndiName=mr.getMessage("jndi.mqm");
                Object qualObj = jndiContext.lookup(jndiName);
                HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(qualObj,HLCMembershipQualificationMatrixRemoteHome.class);
                HLCMembershipQualificationMatrixRemote qremote = qhome.create();

                /*String eduJndiname=mr.getMessage("jndi.education");

                Object objrefEdu = jndiContext.lookup(eduJndiname);
                HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objrefEdu, HLCEducationalSessionRemoteHome.class);
                HLCEducationalSessionRemote eduRemote = eduHome.create();*/

                String jndiname1=mr.getMessage("jndi.usrreg");
                Object objref1 = jndiContext.lookup(jndiname1);

            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();

            String jndinameKrish=mr.getMessage("jndi.pc");
            Object krishnaObj = jndiContext.lookup(jndinameKrish);
            HLCKrishnaStatelessRemoteHome krishnaHome = (HLCKrishnaStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(krishnaObj,HLCKrishnaStatelessRemoteHome.class);
            HLCKrishnaStatelessRemote eveCalRemote = krishnaHome.create();
                String roleProcess = request.getParameter("roleProcess");
                Debug.print("ActionRoleMangement.roleProcess:" + roleProcess);

//==================================================initRole Role Management =====================================================================================
               if(roleProcess.equals("initCreateRole")){
                    Debug.print("ActionRoleMangement.initCreateRole() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMCreateRole");
                }
//==================================================create Role Management =====================================================================================
                else if(roleProcess.equals("createRole")){
                    Debug.print("ActionRoleMangement.createRole()");
                    String rolename = request.getParameter("rolename");
		//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                     String roledesc = request.getParameter("roledesc");
                      String status = request.getParameter("status");
		//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                    Debug.print("ActionRoleMangement.createRole() rolename:" + rolename);
                    if(rolename!=null){
		//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                        //boolean result = roleRemote.createRole(rolename);
                        boolean result = roleRemote.createRole(rolename,roledesc,status);
		//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                        Debug.print("ActionRoleMangement.createRole()");
                        if(result){
		//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                            //return mapping.findForward("frmRolMCreateRole");
                            return mapping.findForward("frmRolMgtListRoles");
		//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                        }
                        else{
                            return mapping.findForward("errRedirRole");
                        }
                    }
                    Debug.print("ActionRoleMangement.createRole() sucessfully comes from servlet.");
                }
//==================================================create Role Management =====================================================================================
                else if(roleProcess.equals("editRole")){
                    Debug.print("ActionRoleMangement.editRole()");
                    String roleId = request.getParameter("roleId");
                    String rolename = request.getParameter("rolename");
		//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                    String roledesc =request.getParameter("roledesc");
                    String status = request.getParameter("status");
		//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                    boolean result = false;
                    Debug.print("ActionRoleMangement.editRole() values:" + roleId+"==="+rolename+"==="+roledesc+status);
                    if(roleId!=null && roleId.trim().length()!=0 && rolename!=null){
		//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                        //result = roleRemote.editRole(roleId, rolename);
                        result = roleRemote.editRole(roleId, rolename,roledesc,status);
		//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                        Debug.print("ActionRoleMangement.editRole() result:" + result);
                        if(result==true){
                            Debug.print("ActionRoleMangement.editRole() result:" + result);
                            if(roleId!=null){
                                String[]  resultString = roleRemote.getRole(roleId);
                                request.setAttribute("roleDetails",resultString);
                            }
                            //Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                            //return mapping.findForward("frmRolMEditRole");
                            return mapping.findForward("frmRolMgtListRoles");
                            //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                        }
                        else{
                            Debug.print("ActionRoleMangement.editRole() result:" + result);
                            if(roleId!=null){
                                String[]  resultString = roleRemote.getRole(roleId);
                                request.setAttribute("roleDetails",resultString);
                            }
                            //For Debugs Starts
                            request.setAttribute("rolename", rolename);
                            //For Debugs Ends

                            return mapping.findForward("errRedirEditRole");
                        }
                    }
                    Debug.print("ActionRoleMangement.editRole() sucessfully comes from servlet.");
                }
//==================================================create Role Management =====================================================================================
                else if(roleProcess.equals("initeditRole")){
                    Debug.print("ActionRoleMangement.editRole()");
                    String roleId = request.getParameter("roleId");
                    if(roleId!=null){
                        String[]  result = roleRemote.getRole(roleId);
                        request.setAttribute("roleDetails",result);
                    }
                    
                    Debug.print("ActionRoleMangement.editRole() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEditRole");
                }
 //==================================================delete Role Management =====================================================================================
		//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                else if(roleProcess.equals("deleteRole")){
                    Debug.print("ActionRoleMangement.deleteRole()");
                    String roleId = request.getParameter("roleId");
                    String rolename = request.getParameter("rolename");
                    String roledesc =request.getParameter("roledesc");
                    //String status = request.getParameter("status");
                    String status = request.getParameter(roleId);
                    String chkRoleIdArr[] = request.getParameterValues("chk");

                    for(int i=0;i<chkRoleIdArr.length;i++)
                    Debug.print("ActionRoleMangement.deleteRole() checked records: "+chkRoleIdArr[i]);

                    boolean result = false;
                    Debug.print("ActionRoleMangement.deleteRole() values:" + roleId+"==="+rolename+"==="+roledesc+status);

                    if(chkRoleIdArr!=null){
                        //result = roleRemote.deleteRole(roleId);
                        result = roleRemote.deleteRole(chkRoleIdArr);
                        Debug.print("ActionRoleMangement.deleteRole() result:" + result);
                        if(result==true){
                            Debug.print("ActionRoleMangement.deleteRole() result:" + result);
                            //if(roleId!=null){
                                //String[]  resultString = roleRemote.getRole(roleId);
                            if(chkRoleIdArr!=null){
                                String[]  resultString = roleRemote.getRole(chkRoleIdArr[0]);
                                request.setAttribute("roleDetails",resultString);
                            }
                            return mapping.findForward("frmRolMgtListRoles");
                        }
                        else{
                            Debug.print("ActionRoleMangement.deleteRole() result:" + result);
                            //if(roleId!=null){
                                //String[]  resultString = roleRemote.getRole(roleId);
                            if(chkRoleIdArr!=null){
                                String[]  resultString = roleRemote.getRole(chkRoleIdArr[0]);
                                request.setAttribute("roleDetails",resultString);
                            }

                            return mapping.findForward("errRedirEditRole");
                        }
                    }
                    Debug.print("ActionRoleMangement.deleteRole() sucessfully comes from servlet.");
                }

            //End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011
                //==================================================create Role Management =====================================================================================
                else if(roleProcess.equals("roleList")){
                    Debug.print("ActionRoleMangement.roleList()");
                    ArrayList roleList = new ArrayList();
                    roleList = (ArrayList)roleRemote.getAllRoles();

                    request.setAttribute("allRoleList",null);
                    request.setAttribute("allRoleList",roleList);

                    Debug.print("ActionRoleMangement.roleList() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMListRoles");
                }
//==================================================initPrivilege  Management =====================================================================================
                else if(roleProcess.equals("initCreatePriv")){
                    Debug.print("ActionRoleMangement.initCreatePriv() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMCreatePrivileges");
                }
//==================================================create Privilege Management =====================================================================================
                else if(roleProcess.equals("insertPriv")){
                    Debug.print("ActionRoleMangement.insertPriv()");
                    String privName = request.getParameter("privName");
                    Debug.print("ActionRoleMangement.insertPriv() privName:" + privName);
                    if(privName!=null){
                        boolean result = roleRemote.createPrivilege(privName);
                        Debug.print("ActionRoleMangement.insertPriv()");
                        if(result){
                            return mapping.findForward("frmRolMCreatePrivileges");
                        }
                        else{
                            return mapping.findForward("errRedirPriv");
                        }
                    }
                    Debug.print("ActionRoleMangement.insertPriv() sucessfully comes from servlet.");
                }
//==================================================create Role Management =====================================================================================
                else if(roleProcess.equals("privList")){
                    Debug.print("ActionRoleMangement.privList()");
                    ArrayList roleList = new ArrayList();
                    roleList = (ArrayList)roleRemote.getAllPrivilege();

                    request.setAttribute("allPrivList",null);
                    request.setAttribute("allPrivList",roleList);

                    Debug.print("ActionRoleMangement.privList() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMListPrivileges");
                }
//==================================================edit Privilege  =====================================================================================
                else if(roleProcess.equals("editPriv")){
                    Debug.print("ActionRoleMangement.editPriv()");
                    String privId = request.getParameter("privId");
                    String privName = request.getParameter("privName");
                    boolean result = false;
                    if(privId!=null && privId.trim().length()!=0 && privName!=null){
                        result = roleRemote.editPrivilege(privId, privName);
                        Debug.print("ActionRoleMangement.editPriv() result:" + result);
                        if(result==true){
                            Debug.print("ActionRoleMangement.editPriv() result:" + result);
                            if(privId!=null){
                                String[]  resultString = roleRemote.getPrivilege(privId);
                                request.setAttribute("privDetails",resultString);
                            }
                            return mapping.findForward("frmRolMEditPrivileges");
                        }
                        else{
                            if(privId!=null){
                                String[]  resultString = roleRemote.getPrivilege(privId);
                                request.setAttribute("privDetails",resultString);
                            }
                            Debug.print("ActionRoleMangement.editPriv() result:" + result);
                            return mapping.findForward("errRedirEditPriv");
                        }
                    }
                    Debug.print("ActionRoleMangement.editPriv() sucessfully comes from servlet.");
                }
//==================================================initEdit Privilege Management =====================================================================================
                else if(roleProcess.equals("initEditPriv")){
                    Debug.print("ActionRoleMangement.initEditPriv()");
                    String privId = request.getParameter("privId");
                    if(privId!=null){
                        String[]  result = roleRemote.getPrivilege(privId);
                        request.setAttribute("privDetails",result);
                    }

                    Debug.print("ActionRoleMangement.initEditPriv() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEditPrivileges");
                }

//==================================================initPermission  Management =====================================================================================
                else if(roleProcess.equals("initCreatePerm")){
                    Debug.print("ActionRoleMangement.initCreatePerm() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMCreatePermission");
                }
//==================================================create Permission  =====================================================================================
                else if(roleProcess.equals("insertPerm")){
                    Debug.print("ActionRoleMangement.insertPerm()");
                    String permissionName = request.getParameter("permissionName");
                    Debug.print("ActionRoleMangement.insertPriv() insertPerm:" + permissionName);
                    if(permissionName!=null){
                        boolean result = roleRemote.createPermission(permissionName);
                        Debug.print("ActionRoleMangement.insertPerm()");
                        if(result){
                            return mapping.findForward("frmRolMCreatePermission");
                        }
                        else{
                            return mapping.findForward("errRedirPerm");
                        }
                    }
                    Debug.print("ActionRoleMangement.insertPerm() sucessfully comes from servlet.");
                }

//==================================================List Permission Management =====================================================================================
                else if(roleProcess.equals("permissionList")){
                    Debug.print("ActionRoleMangement.permissionList()");
                    ArrayList permList = new ArrayList();
                    permList = (ArrayList)roleRemote.getAllPermission();

                    request.setAttribute("allPermList",null);
                    request.setAttribute("allPermList",permList);

                    Debug.print("ActionRoleMangement.permissionList() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMListPermissions");
                }

//==================================================edit Privilege  =====================================================================================
                else if(roleProcess.equals("editPerm")){
                    Debug.print("ActionRoleMangement.editPerm()");
                    String permissionId = request.getParameter("permissionId");
                    String permissionName = request.getParameter("permissionName");
                    boolean result = false;
                    if(permissionId!=null && permissionId.trim().length()!=0 && permissionName!=null){
                        result = roleRemote.editPermission(permissionId, permissionName);
                        Debug.print("ActionRoleMangement.editPerm() result:" + result);
                        if(result==true){
                            Debug.print("ActionRoleMangement.editPerm() result:" + result);
                            if(permissionId!=null && permissionId.trim().length()!=0){
                                String[]  resultString = roleRemote.getPermission(permissionId);
                                request.setAttribute("permDetails",resultString);
                            }
                            return mapping.findForward("frmRolMEditPermission");
                        }
                        else{
                            if(permissionId!=null){
                                String[]  resultString = roleRemote.getPermission(permissionId);
                                request.setAttribute("permDetails",resultString);
                            }
                            Debug.print("ActionRoleMangement.editPerm() result:" + result);
                            return mapping.findForward("errRedirEditPerm");
                        }
                    }
                    Debug.print("ActionRoleMangement.editPerm() sucessfully comes from servlet.");
                }
//==================================================initEdit Privilege Management =====================================================================================
                else if(roleProcess.equals("initEditPerm")){
                    Debug.print("ActionRoleMangement.initEditPerm()");
                    String permissionId = request.getParameter("permissionId");
                    if(permissionId!=null){
                        String[]  result = roleRemote.getPermission(permissionId);
                        request.setAttribute("permDetails",result);
                    }

                    Debug.print("ActionRoleMangement.initEditPerm() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEditPermission");
                }

//==================================================initEntity  Management =====================================================================================
                else if(roleProcess.equals("initCreateEntity")){
                    Debug.print("ActionRoleMangement.initCreateEntity() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMCreateEntity");
                }
//==================================================create Permission  =====================================================================================
                else if(roleProcess.equals("createEntity")){
                    Debug.print("ActionRoleMangement.createEntity()");
                    String entityName = request.getParameter("entityName");
                    Debug.print("ActionRoleMangement.insertPriv() insertPerm:" + entityName);
                    if(entityName!=null){
                        boolean result = roleRemote.createEntity(entityName);
                        Debug.print("ActionRoleMangement.createEntity()");
                        if(result){
                            return mapping.findForward("frmRolMCreateEntity");
                        }
                        else{
                            return mapping.findForward("errRedirEntity");
                        }
                    }
                    Debug.print("ActionRoleMangement.createEntity() sucessfully comes from servlet.");
                }

//==================================================List Permission Management =====================================================================================
                else if(roleProcess.equals("entityList")){
                    Debug.print("ActionRoleMangement.entityList()");
                    ArrayList entityList = new ArrayList();
                    entityList = (ArrayList)roleRemote.getAllEntity();

                    request.setAttribute("allEntityList",null);
                    request.setAttribute("allEntityList",entityList);

                    Debug.print("ActionRoleMangement.entityList() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMListEntities");
                }
//==================================================edit Privilege  =====================================================================================
                else if(roleProcess.equals("editEntity")){
                    Debug.print("ActionRoleMangement.editEntity()");
                    String entityId = request.getParameter("entityId");
                    String entityName = request.getParameter("entityName");
                    boolean result = false;
                    if(entityId!=null && entityId.trim().length()!=0 && entityName!=null){
                        result = roleRemote.editEntity(entityId, entityName);
                        Debug.print("ActionRoleMangement.editEntity() result:" + result);
                        if(result==true){
                            Debug.print("ActionRoleMangement.editEntity() result:" + result);
                            if(entityId!=null && entityId.trim().length()!=0){
                                String[]  resultString = roleRemote.getEntity(entityId);
                                request.setAttribute("entityDetails",resultString);
                            }
                            return mapping.findForward("frmRolMEditEntity");
                        }
                        else{
                            if(entityId!=null && entityId.trim().length()!=0){
                                String[]  resultString = roleRemote.getEntity(entityId);
                                request.setAttribute("entityDetails",resultString);
                            }
                            Debug.print("ActionRoleMangement.editEntity() result:" + result);
                            return mapping.findForward("errRedirEditEntity");
                        }
                    }
                    Debug.print("ActionRoleMangement.editEntity() sucessfully comes from servlet.");
                }
//==================================================initEdit Entity Management =====================================================================================
                else if(roleProcess.equals("initEditEntity")){
                    Debug.print("ActionRoleMangement.initEditEntity()");
                    String entityId = request.getParameter("entityId");
                    if(entityId!=null){
                        String[]  result = roleRemote.getEntity(entityId);
                        request.setAttribute("entityDetails",result);
                    }
                    Debug.print("ActionRoleMangement.initEditEntity() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEditEntity");
                }

//==================================================init Privilege mapping with Permission =====================================================================================
                else if(roleProcess.equals("privPermissionSelect")){
                    try{
                        String privId = request.getParameter("privId");
                        if(privId!=null){
                            Debug.print("ActionRoleMangement.privPermissionSelect()");
                            ArrayList privList = new ArrayList();
                            ArrayList permList = new ArrayList();
                            ArrayList mapPrivPerm = new ArrayList();
                            privList = (ArrayList)roleRemote.getAllPrivilege();
                            permList = (ArrayList)roleRemote.getAllPermission();

                            mapPrivPerm = roleRemote.getMappingDetailsForPermissionAndPrivilege(privId);

                            request.setAttribute("privillegeDetails" ,null);
                            request.setAttribute("privillegeDetails" ,privList);

                            request.setAttribute("permissionDetails" ,null);
                            request.setAttribute("permissionDetails" ,permList);

                            request.setAttribute("mapDetails" ,null);
                            request.setAttribute("mapDetails" ,mapPrivPerm);
                            request.setAttribute("privId",privId);
                            //{mapPermissionId, permissionName, accessName, accessUrl};
                        }
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting privPermissionSelect:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.privPermissionSelect() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMPriviPermissionMapping");
                }
//==================================================init Privilege mapping with Permission =====================================================================================
                else if(roleProcess.equals("initPrivPermission")){
                    try{
                        Debug.print("ActionRoleMangement.initPrivPermission()");
                        ArrayList privList = new ArrayList();
                        ArrayList permList = new ArrayList();
                        privList = (ArrayList)roleRemote.getAllPrivilege();
                        permList = (ArrayList)roleRemote.getAllPermission();

                        request.setAttribute("privillegeDetails" ,null);
                        request.setAttribute("privillegeDetails" ,privList);

                        request.setAttribute("permissionDetails" ,null);
                        request.setAttribute("permissionDetails" ,permList);
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initPrivPermission:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initPrivPermission() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMPriviPermissionMapping");
                }
//==================================================Mapping Privilege with Permissions =====================================================================================
            /*   else if(roleProcess.equals("mappingPrivPermission")){
                    try{
                        Debug.print("ActionRoleMangement.mappingPrivPermission()");
                        String privId = request.getParameter("privId");
                        String editStatus = request.getParameter("editStatus");
                        Debug.print("editStatus:" + editStatus);

                        String viewId = request.getParameter("viewId");
                        String deleteId = request.getParameter("deleteId");
                        String createId = request.getParameter("createId");
                        String editId = request.getParameter("editId");
                        Debug.print("viewId:" + viewId);
                        Debug.print("deleteId:" + deleteId);
                        Debug.print("createId:" + createId);
                        Debug.print("editId:" + editId);

                        if(privId!=null && viewId!= null && deleteId!= null && createId!= null && editId!= null ){
                            Debug.print("ActionRoleMangement.mappingPrivPermission() All Ids Are valid");
                            String viewName = request.getParameter("viewName");
                            String viewURL = request.getParameter("viewURL");
                            String editName = request.getParameter("editName");
                            String editURL = request.getParameter("editURL");
                            String createName = request.getParameter("createName");
                            String createUrl = request.getParameter("createUrl");
                            String deleteName = request.getParameter("deleteName");
                            String deleteURL = request.getParameter("deleteURL");

                            if(viewName==null) viewName="";
                            if(viewURL==null) viewURL="";
                            if(editName==null) editName="";
                            if(editURL==null) editURL="";
                            if(createName==null) createName="";
                            if(createUrl==null) createUrl="";
                            if(deleteName==null) deleteName="";
                            if(deleteURL==null) deleteURL="";
                            if(editStatus==null) editStatus="";

                            String[] permListView = {viewId,viewName,viewURL};
                            String[] permListEdit = {editId,editName,editURL};
                            String[] permListCreate = {createId,createName,createUrl};
                            String[] permListDelete = {deleteId,deleteName,deleteURL};

                            ArrayList permissionList = new ArrayList();
                            permissionList.add(permListView);
                            permissionList.add(permListDelete);
                            permissionList.add(permListCreate);
                            permissionList.add(permListEdit);

                            if(editStatus.equalsIgnoreCase("editMapPrivPerm")) {
                                 Debug.print("ActionRoleMangement.mappingPrivPermission() Editting..");
                                 roleRemote.editMappingPermissionToPrivilege(permissionList);
                            }
                            else {
                                Debug.print("ActionRoleMangement.mappingPrivPermission() Inserting..");
                                roleRemote.createMappingPermissionToPrivilege(privId,permissionList);
                            }
                        }
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting mappingPrivPermission:" + eDisp);
                    }
                    Debug.print("ActionRoleMangement.mappingPrivPermission() sucessfully inserted and comes from servlet.");
                    return mapping.findForward("frmRoleHome");
                }*/

               
               else if(roleProcess.equals("mappingPrivPermission")){
                   try{
                       Debug.print("ActionRoleMangement.mappingPrivPermission()");
                       String privId = request.getParameter("privId");
                       String editStatus = request.getParameter("editStatus");
                       Debug.print("editStatus:" + editStatus);
                       
                       String viewId = request.getParameter("viewId");
                        String deleteId = request.getParameter("deleteId");
                        String createId = request.getParameter("createId");
                        String editId = request.getParameter("editId");
                       String permissionId1 = request.getParameter("permissionId1");
                       String permissionId2 = request.getParameter("permissionId2");
                       String permissionId3 = request.getParameter("permissionId3");
                       String permissionId4 = request.getParameter("permissionId4");
                       String permissionId5 = request.getParameter("permissionId5");
                       String permissionId6 = request.getParameter("permissionId6");
                       String permissionId7 = request.getParameter("permissionId7");
                       String permissionId8 = request.getParameter("permissionId8");
                     Debug.print("permissionId1=="+permissionId1+"permissionId2=="+permissionId2+"permissionId3=="+permissionId3+"permissionId4=="+permissionId4+"permissionId5=="+permissionId5+"permissionId6=="+permissionId6);
                     

                       if(privId!=null && permissionId1!= null && permissionId2!= null && permissionId3!= null && permissionId4!= null 
                    		   && permissionId5!= null  && permissionId6!= null  && permissionId7!= null  && permissionId8!= null)
                       {
                           Debug.print("ActionRoleMangement.mappingPrivPermission() All Ids Are valid");
						   String viewName = request.getParameter("viewName");
                            String viewURL = request.getParameter("viewURL");
                            String editName = request.getParameter("editName");
                            String editURL = request.getParameter("editURL");
                            String createName = request.getParameter("createName");
                            String createUrl = request.getParameter("createUrl");
                            String deleteName = request.getParameter("deleteName");
                            String deleteURL = request.getParameter("deleteURL");
                            
                           String perName1 = request.getParameter("perName1");
                           String perURL1 = request.getParameter("perURL1");
                           String perName2 = request.getParameter("perName2");
                           String perURL2 = request.getParameter("perURL2");
                           String perName3 = request.getParameter("perName3");
                           String perURL3 = request.getParameter("perURL3");
                           String perName4 = request.getParameter("perName4");
                           String perURL4 = request.getParameter("perURL4");
                           String perName5 = request.getParameter("perName5");
                           String perURL5 = request.getParameter("perURL5");
                           String perName6 = request.getParameter("perName6");
                           String perURL6 = request.getParameter("perURL6");
                           String perName7 = request.getParameter("perName7");
                           String perURL7 = request.getParameter("perURL7");
                           String perName8 = request.getParameter("perName8");
                           String perURL8 = request.getParameter("perURL8");

                           if(perName1==null) perName1="";
                           if(perURL1==null) perURL1="";
                           if(perName2==null) perName2="";
                           if(perURL2==null) perURL2="";
                           if(perName3==null) perName3="";
                           if(perURL3==null) perURL3="";
                           if(perName4==null) perName4="";
                           if(perURL4==null) perURL4="";
                           if(perName5==null) perName5="";
                           if(perURL5==null) perURL5="";
                           if(perName6==null) perName6="";
                           if(perURL6==null) perURL6="";
                           if(perName7==null) perName7="";
                           if(perURL7==null) perURL7="";
                           if(perName8==null) perName8="";
                           if(perURL8==null) perURL8="";
                           if(editStatus==null) editStatus="";
						   
                            if(viewName==null) viewName="";
                            if(viewURL==null) viewURL="";
                            if(editName==null) editName="";
                            if(editURL==null) editURL="";
                            if(createName==null) createName="";
                            if(createUrl==null) createUrl="";
                            if(deleteName==null) deleteName="";
                            if(deleteURL==null) deleteURL="";
                            if(editStatus==null) editStatus="";

                            String[] permListView = {viewId,viewName,viewURL};
                            String[] permListEdit = {editId,editName,editURL};
                            String[] permListCreate = {createId,createName,createUrl};
                            String[] permListDelete = {deleteId,deleteName,deleteURL};

                           String[] permListPerm1 = {permissionId1,perName1,perURL1};
                           String[] permListPerm2 = {permissionId2,perName2,perURL2};
                           String[] permListPerm3 = {permissionId3,perName3,perURL3};
                           String[] permListPerm4 = {permissionId4,perName4,perURL4};
                           String[] permListPerm5 = {permissionId5,perName5,perURL5};
                           String[] permListPerm6 = {permissionId6,perName6,perURL6};
                           String[] permListPerm7 = {permissionId7,perName7,perURL7};
                           String[] permListPerm8 = {permissionId8,perName8,perURL8};
                         
                           
                           ArrayList permissionList = new ArrayList();
						     permissionList.add(permListView);
                            permissionList.add(permListDelete);
                            permissionList.add(permListCreate);
                            permissionList.add(permListEdit);
                           permissionList.add(permListPerm1);
                           permissionList.add(permListPerm2);
                           permissionList.add(permListPerm3);
                           permissionList.add(permListPerm4);
                           permissionList.add(permListPerm5);
                           permissionList.add(permListPerm6);
                           permissionList.add(permListPerm7);
                           permissionList.add(permListPerm8);

                           if(editStatus.equalsIgnoreCase("editMapPrivPerm")) {
                                Debug.print("ActionRoleMangement.mappingPrivPermission() Editting..");
                                roleRemote.editMappingPermissionToPrivilege(permissionList,privId);
                           }
                           else {
                               Debug.print("ActionRoleMangement.mappingPrivPermission() Inserting..");
                               roleRemote.createMappingPermissionToPrivilege(privId,permissionList);
                           }
                       }
                   }
                   catch(Exception eDisp){
                       Debug.print("while getting mappingPrivPermission:" + eDisp);
                   }
                   Debug.print("ActionRoleMangement.mappingPrivPermission() sucessfully inserted and comes from servlet.");
                   return mapping.findForward("frmRoleHome");
               }
//==================================================init Entity mapping with Privileges =====================================================================================
                else if(roleProcess.equals("initEntPriv")){
                    try{
                        Debug.print("ActionRoleMangement.initEntPriv()");
                        ArrayList privList = new ArrayList();
                        ArrayList entityList = new ArrayList();
                        privList = (ArrayList)roleRemote.getAllPrivilege();
                        entityList = (ArrayList)roleRemote.getAllEntity();

                        request.setAttribute("privillegeDetails" ,null);
                        request.setAttribute("privillegeDetails" ,privList);

                        request.setAttribute("enityDetails" ,null);
                        request.setAttribute("enityDetails" ,entityList);
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initEntPriv:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initEntPriv() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMPrivilegeEntityMapping");
                }
//==================================================init Privilege mapping with Permission =====================================================================================
                else if(roleProcess.equals("entPrivSelect")){
                    try{
                        String entityId = request.getParameter("entityId");
                        String permissionId = request.getParameter("permissionId");
                        String roleId = request.getParameter("roleID");
                        String privilegeId = request.getParameter("privilegeId");
                        if(entityId!=null){
                            Debug.print("ActionRoleMangement.entPrivSelect()");
                            ArrayList privList = new ArrayList();
                            ArrayList entityList = new ArrayList();
                            ArrayList mapEntPriv = new ArrayList();
                            ArrayList permission= new ArrayList();
                            ArrayList mapPrivPer = new ArrayList();
                            privList = (ArrayList)roleRemote.getAllPrivilege();
                            entityList = (ArrayList)roleRemote.getAllEntity();
                            permission =(ArrayList)roleRemote.getAllPermission();

                            mapEntPriv = roleRemote.getMappingDetailsForEnitityAndPrivileges(entityId);
                            mapPrivPer=roleRemote.getMappingDetailsForPermissionAndPrivilege(privilegeId);

                            request.setAttribute("PermissionDetails" ,null);
                            request.setAttribute("PermissionDetails" ,permission);
                            request.setAttribute("privillegeDetails" ,null);
                            request.setAttribute("privillegeDetails" ,privList);

                            request.setAttribute("enityDetails" ,null);
                            request.setAttribute("enityDetails" ,entityList);

                             request.setAttribute("PermapDetails" ,null);
                            request.setAttribute("PermapDetails" ,mapPrivPer);
                            request.setAttribute("permissionId",permissionId);
                            request.setAttribute("mapDetails" ,null);
                            request.setAttribute("mapDetails" ,mapEntPriv);
                            request.setAttribute("entityId",entityId);
                            //{mapPermissionId, permissionName, accessName, accessUrl};
                        }
                    }
                catch(Exception eDisp){
                        Debug.print("while getting entPrivSelect:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.entPrivSelect() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMPrivilegeEntityMapping");
                }
//==================================================Mapping Entity with Privileges =====================================================================================
               else if(roleProcess.equals("mappingEntPriv")){
                    try{
                        Debug.print("ActionRoleMangement.mappingEntPriv()");
                        String entityId = request.getParameter("entityId");
                        Debug.print("entityId:" + entityId);
                        request.setAttribute("entityId",entityId);

                        String privilegeIds = request.getParameter("privilegeIds");
                        Debug.print("privilegeIds:" + privilegeIds);
                        StringTokenizer strTkns = new StringTokenizer(privilegeIds,"#");
                        ArrayList privList = new ArrayList();
                        while(strTkns.hasMoreTokens()){
                            try{
                                String privilegeId = (String)strTkns.nextToken();
                                Debug.print("ActionRoleMangement.mappingEntPriv() :" + privilegeId);
                                if(privilegeId!=null && privilegeId.trim().length()!=0){
                                    Debug.print("ActionRoleMangement.mappingEntPriv() Added from Stokenizer:" + privilegeId);
                                    privList.add(privilegeId);
                                }
                            }
                            catch(Exception e){
                                Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mappingEntPriv() :" + e);
                            }
                        }

                        if(entityId!=null){
                            Debug.print("ActionRoleMangement.mappingEntPriv() All Ids Are valid");
                                 roleRemote.generateMappingEnitityWithPrivileges(entityId, privList);
                        }
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting mappingEntityPriv:" + eDisp);
                    }
                    Debug.print("ActionRoleMangement.mappingEntityPriv() sucessfully comes from servlet.");
                    return mapping.findForward("redirectFromEntPrivMap");
                }
//==================================================init Role mapping with Entities =====================================================================================
                else if(roleProcess.equals("initRoleEnt")){
                    try{
                        Debug.print("ActionRoleMangement.initRoleEnt()");
                        ArrayList roleList = new ArrayList();
                        ArrayList entityList = new ArrayList();
                        roleList = (ArrayList)roleRemote.getAllRoles();
                        entityList = (ArrayList)roleRemote.getAllEntity();

                        request.setAttribute("roleDetails" ,null);
                        request.setAttribute("roleDetails" ,roleList);

                        request.setAttribute("enityDetails" ,null);
                        request.setAttribute("enityDetails" ,entityList);
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initEntPriv:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initRoleEnt() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEntityRoleMapping");
                }
               
//=================================================init MapUserRolEntity======================================================================================================
                else if(roleProcess.equals("initUserRoleEnt")){
                    try{
                    	
                        Debug.print("ActionRoleMangement.initUserRoleEnt()");
                        String uId=request.getParameter("uId");
                        String uName=request.getParameter("uName");
                        String depId=request.getParameter("depId");
                        String deptName=request.getParameter("deptName");
                        String roleId=request.getParameter("roleId");
                        String roleName=request.getParameter("roleName");
                      //  ArrayList roleList = new ArrayList();
                        ArrayList entityList = new ArrayList();
                        ArrayList mapRoleEnt = new ArrayList();
                       // roleList = (ArrayList)roleRemote.getAllRoles();
                        entityList = (ArrayList)roleRemote.getAllEntity();
                       // request.setAttribute("roleDetails" ,null);
                       // request.setAttribute("roleDetails" ,roleList);
                        mapRoleEnt = roleRemote.getMappingDetailsForUserRoleAndPrivileges(roleId,uId);
                        request.setAttribute("enityDetails" ,null);
                        request.setAttribute("enityDetails" ,entityList);
                        request.setAttribute("uId" ,uId);
                        request.setAttribute("uName" ,uName);
                        request.setAttribute("depId" ,depId);
                        request.setAttribute("deptName" ,deptName);
                        request.setAttribute("roleId" ,roleId);
                        request.setAttribute("roleName" ,roleName);
                        request.setAttribute("mapDetails" ,mapRoleEnt);
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initEntPriv:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initRoleEnt() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEntityRoleMapping");
                }
//==================================================initSelect Role mapping with Enities =====================================================================================
                else if(roleProcess.equals("roleEntSelect")){
                    try{
                        String roleId = request.getParameter("roleId");
                        if(roleId!=null){
                            Debug.print("ActionRoleMangement.roleEntSelect()");
                            ArrayList roleList = new ArrayList();
                            ArrayList entityList = new ArrayList();
                            ArrayList mapRoleEnt = new ArrayList();
                            roleList = (ArrayList)roleRemote.getAllRoles();
                            entityList = (ArrayList)roleRemote.getAllEntity();

                            mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);

                            request.setAttribute("roleDetails" ,null);
                            request.setAttribute("roleDetails" ,roleList);

                            request.setAttribute("enityDetails" ,null);
                            request.setAttribute("enityDetails" ,entityList);

                            request.setAttribute("mapDetails" ,null);
                            request.setAttribute("mapDetails" ,mapRoleEnt);
                            request.setAttribute("roleId", roleId);
                            //{mapEntityId, roleIdVal, entityId, roleName, entityName};
                        }
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting roleEntSelect:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.roleEntSelect() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMEntityRoleMapping");
                }
//==================================================Mapping Role with Enities =====================================================================================
               /*else if(roleProcess.equals("mappingRoleEnt")){
                    try{
                        Debug.print("ActionRoleMangement.mappingRoleEnt()");
                        String roleId = request.getParameter("roleId");	
                        String depId = request.getParameter("depId");
                        String uId = request.getParameter("uId");  
                        String roleName = (String)session.getAttribute("roleName");
                        if(uId.equalsIgnoreCase(null) && uId.equalsIgnoreCase("")){
                        Debug.print("roleId inside if:" + roleId);
                        request.setAttribute("roleId",roleId);

                        String entityIds = request.getParameter("entityIds");
                        Debug.print("entityIds:" + entityIds);
                        StringTokenizer strTkns = new StringTokenizer(entityIds,"#");
                        ArrayList entityList = new ArrayList();
                        while(strTkns.hasMoreTokens()){
                            try{
                                String entityId = (String)strTkns.nextToken();
                                if(entityId!=null && entityId.trim().length()!=0){
                                    Debug.print("ActionRoleMangement.mappingRoleEnt() Added from Stokenizer:" + entityId);
                                    entityList.add(entityId);
                                }
                            }
                            catch(Exception e){
                                Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mappingRoleEnt() :" + e);
                            }
                        }

                        if(roleId!=null){
                            Debug.print("ActionRoleMangement.mappingRoleEnt() All Ids Are valid");
                                 roleRemote.generateMappingRoleWithEntities(roleId, entityList);
                        }
                        return mapping.findForward("redirectFromRolEntMap");
                        }
                        else
                        {
                        	 Debug.print("roleId else:" + roleId);
                             request.setAttribute("roleId",roleId);

                             String entityIds = request.getParameter("entityIds");
                             Debug.print("entityIds:" + entityIds);
                             StringTokenizer strTkns = new StringTokenizer(entityIds,"#");
                             ArrayList entityList = new ArrayList();
                             while(strTkns.hasMoreTokens()){
                                 try{
                                     String entityId = (String)strTkns.nextToken();
                                     if(entityId!=null && entityId.trim().length()!=0){
                                         Debug.print("ActionRoleMangement.mappingRoleEnt() Added from Stokenizer:" + entityId);
                                         entityList.add(entityId);
                                     }
                                 }
                                 catch(Exception e){
                                     Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mappingRoleEnt() :" + e);
                                 }
                             }

                             if(roleId!=null){
                                 Debug.print("ActionRoleMangement.mappingRoleEnt() All Ids Are valid");
                                  
                                    roleRemote.generateMappingUserRoleWithEntities(roleId, depId, uId, entityList,roleName);
                                    boolean result=roleRemote.chgUserStatBySup(uId,session.getAttribute("loginName").toString(),roleId);
                                    if(roleName.equalsIgnoreCase("Admin")){
                                    	 boolean chgStatus = roleRemote.chgUserStatByAdmin(uId);
                                    }
                             }
                             return mapping.findForward("redirectUserSearchList");
                        }
                        
                        
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting mappingRoleEnt:" + eDisp);
                    }
                    Debug.print("ActionRoleMangement.mappingRoleEnt() sucessfully comes from servlet.");
                  
                }*/
                else if(roleProcess.equals("mappingRoleEnt")){
                	String empMap=null;
                	String uId=null;
                	String uName=null;
                	String roleName=null;
                	String deptName=null;
                	 String roleId1=null;
                	 String empId=null;
                    try{
                        Debug.print("ActionRoleMangement.mappingRoleEnt==()");
                        String roleId = request.getParameter("roleId");
                        empMap= request.getParameter("empMap");
                         uId = request.getParameter("userId");
                         deptName = request.getParameter("deptName");
                         roleId1 = request.getParameter("roleId1");
                         uName = request.getParameter("uName");  
                         roleName = request.getParameter("roleName"); 
                         empId = request.getParameter("empId");
                       
                       
                        
                        Debug.print("roleId:" + roleId);
                        request.setAttribute("roleId",roleId);

                        String entityIds = request.getParameter("entityIds");
                        Debug.print("entityIds:" + entityIds);
                        StringTokenizer strTkns = new StringTokenizer(entityIds,"#");
                        ArrayList entityList = new ArrayList();
                        while(strTkns.hasMoreTokens()){
                            try{
                                String entityId = (String)strTkns.nextToken();
                                if(entityId!=null && entityId.trim().length()!=0){
                                    Debug.print("ActionRoleMangement.mappingRoleEnt() Added from Stokenizer:" + entityId);
                                    entityList.add(entityId);
                                }
                            }
                            catch(Exception e){
                                Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mappingRoleEnt() :" + e);
                            }
                        }

                        if(roleId!=null){
                            Debug.print("ActionRoleMangement.mappingRoleEnt() All Ids Are valid");
                                 roleRemote.generateMappingRoleWithEntities(roleId, entityList);
                        }
                    }
                    
                    catch(Exception eDisp){
                        Debug.print("while getting mappingRoleEnt:" + eDisp);
                    }
                    Debug.print("ActionRoleMangement.mappingRoleEnt() sucessfully comes from servlet.");
                    if(empMap==null)
                    {
                    	 return mapping.findForward("redirectFromRolEntMap");
                    }
                    else
                    {
                    	   request.setAttribute("chgStat","mapped");
                    	   request.setAttribute("deptName",deptName);
                    	   request.setAttribute("empId",empId);
                    	   
                    	   //boolean result=roleRemote.chgUserStatBySup(uId,session.getAttribute("loginName").toString(),roleId1);
                    	   
                    	  // String emailId[] = roleRemote.getAdminEmail();
                    	 /*  String emailId[] = {"deepa.v@digiblitz.com"};
                    	   
                    	   String supName = (String)session.getAttribute("loginName");
                    	   
                           if(result==true)
                           {
                         	  EmailContent email = new EmailContent();
                               email.setTo(emailId);                     
                               //email.setFrom("anandv@digiblitz.com");
                               email.setFrom("info_elmt@digiblitz.com");                     
                               email.setSubject("User Approval Status Info !");
                               

                               String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                       " <tr>" +
                                       " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                       " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                       "<tr>" +
                                       "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"ELT Online Services Email Template\" width=\"515\" height=\"55\" /></td> " +
                                       " </tr>" +
                                       "  <tr>" +
                                       "<td valign=\"top\">" +
                                       "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                       "<tr>" +
                                       "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                       "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                       "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                       "</tr>" +
                                       "<tr>" +
                                       "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                                       "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                                       "<span class=\"boldTxt\">Dear Admin </span>,<br /><br />" +
                                       "<p>The following user details has been approved by  <p>" + supName +" ."+ "Please refer user and mapped role details " +
                                       "<p>UserName :" + uName + "<p> RoleName: " + roleName + "<p> Department Name : " + deptName + "<p> ----------------------------<p>" + 
                                      
                                       "Thank you for using the service provided by <span class=\"boldTxt\">Enterprise Landscape Management and Transformation</span>.</p>" +
                                       "Thank You <br />" +
                                       "------------------ <br />" +
                                       "<span class=\"boldRedTxt\">ELMT Team</span></td>" +
                                       "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                                       "</tr>" +
                                       "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                       "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                                       "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                                       "</tr>" +
                                       " </table>" +
                                       "</td></tr>" +
                                        " <tr>" +
                                       "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                                       "</tr>" +
                                       "</table>";


                               email.setBody(content);
                               //email.setAttachments();

                               EmailEngine emailEngine = new EmailEngine();
                               boolean emailFlag = emailEngine.sendMimeEmail(email);
                               Debug.print("Email sent sucessfully :" + emailFlag);
                           }*/
                    	   ArrayList roleList = new ArrayList();
                           ArrayList entityList = new ArrayList();
                           ArrayList mapRoleEnt = new ArrayList();
                           roleList = (ArrayList)roleRemote.getAllRoles();
                           entityList = (ArrayList)roleRemote.getAllEntity();

                           mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId1);

                           request.setAttribute("roleDetails" ,null);
                           request.setAttribute("roleDetails" ,roleList);

                           request.setAttribute("enityDetails" ,null);
                           request.setAttribute("enityDetails" ,entityList);

                           request.setAttribute("mapDetails" ,null);
                           request.setAttribute("mapDetails" ,mapRoleEnt);
                           request.setAttribute("roleId", roleId1);
                           request.setAttribute("roleName", roleName);
                           request.setAttribute("uName", uName);
                    	  return mapping.findForward("dispRoletoUser");
                    } 
                }

//==================================================init Role mapping with Entities and Privileges=====================================================================================
                else if(roleProcess.equals("initRoleEntPriv")){
                    try{
                        Debug.print("ActionRoleMangement.initRoleEntPriv()");
                        ArrayList roleList = new ArrayList();
                        roleList = (ArrayList)roleRemote.getAllRoles();

                        request.setAttribute("roleDetails" ,null);
                        request.setAttribute("roleDetails" ,roleList);
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initEntPriv:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initRoleEntPriv() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMRoleMapping");
                }
//==================================================initSelect Role for Entities=====================================================================================
                else if(roleProcess.equals("initSelectRoleEnt")){
                    try{
                        Debug.print("ActionRoleMangement.initSelectRoleEnt()");
                        String roleId = request.getParameter("roleId");
                        Debug.print("roleId:" + roleId);
                        request.setAttribute("roleId",roleId);

                        Debug.print("ActionRoleMangement.initSelectRoleEnt()");
                        ArrayList roleList = new ArrayList();
                        ArrayList mapRoleEnt = new ArrayList();

                        roleList = (ArrayList)roleRemote.getAllRoles();
                        mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);

                        request.setAttribute("roleDetails" ,null);
                        request.setAttribute("roleDetails" ,roleList);

                        request.setAttribute("mapDetails" ,null);
                        request.setAttribute("mapDetails" ,mapRoleEnt);

                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initSelectRoleEnt:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initSelectRoleEnt() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMRoleMapping");
                }
//==================================================initSelect Role for Entities and Privileges=====================================================================================
                else if(roleProcess.equals("initSelectRoleEntPriv")){
                    try{
                        Debug.print("ActionRoleMangement.initSelectRoleEntPriv()");
                        String roleId = request.getParameter("roleId");
                        String entityId = request.getParameter("entityId");
                        Debug.print("roleId:" + roleId);
                        request.setAttribute("roleId",roleId);
                        Debug.print("entityId:" + entityId);
                        request.setAttribute("entityId",entityId);

                        ArrayList roleList = new ArrayList();
                        ArrayList mapRoleEnt = new ArrayList();
                        ArrayList mapEntPriv = new ArrayList();
                        ArrayList mapRoleEntPriv = new ArrayList();
                          ArrayList mapRoleEntPrivPer = new ArrayList();
                        ArrayList mapRoleSubPer = new ArrayList();


                        roleList = (ArrayList)roleRemote.getAllRoles();
                        mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);

                        if(entityId!=null){
                            mapEntPriv = roleRemote.getMappingDetailsForEnitityAndPrivileges(entityId);
                        }

                        if(roleId!=null && entityId!=null){
                            mapRoleEntPriv = roleRemote.getPermissionBasedOnEntityRole(roleId,entityId);
                        }


                      //Start:[RoleMgt] For Role,Privilege,Permission Mapping
                     ArrayList subPermList = new ArrayList();
                        subPermList = (ArrayList)roleRemote.getMappingDetailsForRoleSubPerm();

                        request.setAttribute("allSubPerm" ,null);
                        request.setAttribute("allSubPerm" ,subPermList);
                        ArrayList permList = new ArrayList();
                        permList = (ArrayList)roleRemote.getAllPermission();
                        //For Debug
                        ArrayList allMapPrivList = new ArrayList();
                        allMapPrivList = (ArrayList)roleRemote.getAllMapPrivilege(roleId, entityId);

                        request.setAttribute("allMapPrivDetails" ,null);
                        request.setAttribute("allMapPrivDetails" ,allMapPrivList);

                        ArrayList allMapPermList = new ArrayList();
                        allMapPermList = (ArrayList)roleRemote.getAllMapPermission(roleId, entityId);

                        request.setAttribute("allMapPermDetails" ,null);
                        request.setAttribute("allMapPermDetails" ,allMapPermList);

                        //End:[RoleMgt] For Role,Privilege,Permission Mapping

                        request.setAttribute("permissionDetails" ,null);
                        request.setAttribute("permissionDetails" ,permList);

                        request.setAttribute("roleDetails" ,null);
                        request.setAttribute("roleDetails" ,roleList);

                        request.setAttribute("mapDetails" ,null);
                        request.setAttribute("mapDetails" ,mapRoleEnt);

                        request.setAttribute("mapEntPrivDetails" ,null);
                        request.setAttribute("mapEntPrivDetails" ,mapEntPriv);

                        request.setAttribute("mapRoleEntPrivDetails" ,null);
                        request.setAttribute("mapRoleEntPrivDetails" ,mapRoleEntPriv);

                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initSelectRoleEntPriv:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initSelectRoleEntPriv() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMRoleMapping");
                }
//==================================================init Roles assign to User =====================================================================================
                else if(roleProcess.equals("mapRoleEntityPrivPerms")){
                    try{
                        Debug.print("ActionRoleMangement.mapRoleEntityPrivPerms()");
                        String roleId = request.getParameter("roleId");
                        String entityId = request.getParameter("entityId");
                        Debug.print("roleId:" + roleId);
                        request.setAttribute("roleId",roleId);
                        Debug.print("entityId:" + entityId);
                        request.setAttribute("entityId",entityId);

                        if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0){
                            Debug.print("ActionRoleMangement.mapRoleEntityPrivPerms() Role ID and EntityIds are valid");
                            String PrivPermIds = request.getParameter("PrivPermIds");
                            Debug.print("PrivPermIds:" + PrivPermIds);
                            ArrayList privPermList = new ArrayList();
                            if(PrivPermIds!=null && PrivPermIds.trim().length()!=0){
                                StringTokenizer strTkns = new StringTokenizer(PrivPermIds,"#");
                                while(strTkns.hasMoreTokens()){
                                    try{
                                        String permIds = (String)strTkns.nextToken();
                                        StringTokenizer strTknPerm = new StringTokenizer(permIds,"!");
                                        while(strTknPerm.hasMoreTokens()){
                                            String privId = (String)strTknPerm.nextToken();
                                            String permissionId = (String)strTknPerm.nextToken();
                                            Debug.print("Splitted privId:" + privId);
                                            Debug.print("Splitted permissionId:" + permissionId);
                                            if(privId!=null && permissionId!=null && privId.trim().length()!=0 && permissionId.trim().length()!=0){
                                                String [] arrayPrivPerm = {privId,permissionId};
                                                privPermList.add(arrayPrivPerm);
                                            }
                                        }
                                    }
                                    catch(Exception e){
                                        Debug.print("Exception while spliting privilegeIds and PermissionIds ActionRoleMangement.mapRoleEntityPrivPerms() :" + e);
                                    }
                                }
                            }
                                            //For Debug Starts
                                            String permSubPermIds[] = request.getParameterValues("subPermChk");
                                            String permId = null;
                                            String subPermId = null;
                                            int permSubPermLen = 0;

                                            if(permSubPermIds!=null && !permSubPermIds.equals(""))
                                            {
                                                permSubPermLen = permSubPermIds.length;
                                            }

                                           
                                            String permIdArr[] = new String[permSubPermLen];
                                        
                                            String subPermIdArr[] = new String[permSubPermLen];
                                         
                                            if(permSubPermIds!=null){
                                            for(int i=0;i <permSubPermIds.length; i++)
                                            {
                                                    StringTokenizer permSubPermTkns = new StringTokenizer(permSubPermIds[i],"#");
                                                        System.out.println("String token 1 "+permSubPermIds[i]);
                                                    while(permSubPermTkns.hasMoreTokens()){

                                                         permId =  (String)permSubPermTkns.nextToken();
                                                         System.out.println("String token 2 "+permId);

                                                         subPermId =  (String)permSubPermTkns.nextToken();
                                                         System.out.println("String token 3 "+subPermId);
                                                    }
                                                     permIdArr[i] =  permId;
                                                     subPermIdArr[i] =  subPermId;
                                                     System.out.println("String token 4 "+permIdArr[i] +"===="+subPermIdArr[i]);

                                            }
                            }
                                                System.out.println("String token 5 ");
                                            //roleRemote.generateMappingPermSubPerm(permIdArr,subPermIdArr);
                                              System.out.println("String token 6 ");
                                            //For Debug Ends
                            Debug.print("ActionRoleMangement.mapRoleEntityPrivPerms() All Ids Are valid");
                            roleRemote.generateMappingRoleWithEntitiesAndPrivileges(roleId, entityId, privPermList);
                        }
                    }
                    catch(Exception eDisp){
                        Debug.print("while generation mapUserRoles:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.mapRoleEntityPrivPerms() sucessfully comes from servlet.");
                    return mapping.findForward("redirectFromRolesEntityPrivMap");
                }
//==================================================init Roles assign to User =====================================================================================
                else if(roleProcess.equals("initUserRole")){
                    int usersFromDB=remote.getUserCountBasedOnRole();
                     String license_file_path = mr.getMessage("license.file.path");
                    String license_file_name = mr.getMessage("license.file.name");
                    String fileEncryptedContent = XMLParser.readXMLCreated(license_file_path + "\\" + license_file_name);
                    String original = XMLParser.decryptFileContent(fileEncryptedContent); //validating  Registered Users Count
                    int usersFromXML = XMLParser.readFromXMLUsers(original);
                    if(usersFromDB<usersFromXML) // if users from Database are less than In license file
                    {

                    try{
                        Debug.print("ActionRoleMangement.initUserRole()");
                        String userIdVal = request.getParameter("userId");
                        String empMapScr = request.getParameter("empScr");
                        
                        if(empMapScr!=null || empMapScr!="")
                        {
                        	 request.setAttribute("empMapScr","empMapScr");
                        }
                        //String userIdVal = (String)session.getAttribute("userId");
                        ArrayList roleList = new ArrayList();
                        ArrayList userRoles = new ArrayList();
                        ArrayList usrViewPnt = new ArrayList();
                        roleList = (ArrayList)roleRemote.getAllRoles();
                        userRoles = (ArrayList)roleRemote.getAllRolesBasedOnUser(userIdVal);
                        String usrCrit="";
                        ArrayList userContactDetail = new ArrayList();
                        if(userIdVal!=null){
                            userContactDetail = (ArrayList)remote.getUserContactDetails(userIdVal);
                            usrCrit=remote.getusrCriteria(userIdVal);
                            usrViewPnt=remote.getUserViewPoints(userIdVal);
                            request.setAttribute("usrCrit" ,usrCrit);
                            request.setAttribute("usrViewPnt" ,usrViewPnt);
                        }
                        
                        ArrayList viewPoint=roleRemote.getAllViews();
                       request.setAttribute("userContactDetails" ,null);
                       request.setAttribute("userContactDetails" ,userContactDetail);

                       request.setAttribute("userId",userIdVal);
                       request.setAttribute("roleDetails" ,null);
                       request.setAttribute("roleDetails" ,roleList);

                       request.setAttribute("userRoleDetails" ,null);
                       request.setAttribute("userRoleDetails" ,userRoles);
                       request.setAttribute("viewPoint" ,viewPoint);
                      
                       
                    }
                    catch(Exception eDisp){
                        Debug.print("while getting initUserRole:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.initUserRole() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolMUserRoleMapping");
                    }
                    else if(usersFromDB==usersFromXML)
                        return mapping.findForward("frmUserRoleAssignAccessDenied");
                }
//==================================================init Roles assign to User =====================================================================================
                else if(roleProcess.equals("mapUserRoles")){
                     ArrayList userContactDetail = new ArrayList();
                    try{
                        Debug.print("ActionRoleMangement.mapUserRoles()");
                        //String rolename=request.getParameter("roleIdValue");
                         String roleIds = request.getParameter("roleIds");
                         String empScreen=request.getParameter("empScreen");
                          ArrayList roleList = new ArrayList();
                         roleList = (ArrayList)roleRemote.getAllRoles();
                         String rolId=null;String rolName=null;
                        Debug.print("roleIds:" + roleIds);
                        String userIdVal = request.getParameter("userId");
                    //==================Dhivya Here:User View==============================    
                        
                        String viewPntCnt = request.getParameter("viewPntCnt");
                        String usrCrit = request.getParameter("usrCrit");
                        
                        
                        
                        int viewpntSize = 0;
	                      if(viewPntCnt!=null && viewPntCnt.trim().length()!=0){
	                    	  viewpntSize = Integer.parseInt(viewPntCnt);
	                      }   
	                     
	                      for(int i=0; i<viewpntSize; i++){
	                    	  if(request.getParameter("viewPnt"+i)!=null && request.getParameter("viewPnt"+i)!=""){	                    		 	                    		  
	           	               String viewPnt = request.getParameter("viewPnt"+i); 
	           	               
	           	               
	           	           boolean result=remote.updateUserCriteria(userIdVal,viewPnt,usrCrit);    
	           	                                
	                    	  }  
	                      }
	                      
	                     /* if(empScreen!=null || empScreen!="")
	                      {
	                    	  boolean result=roleRemote.chgUserStatBySup(userIdVal,session.getAttribute("loginName").toString(),roleIds);
	                      }
	                     */ 
                         StringTokenizer strTkns = new StringTokenizer(roleIds,"#");
                       String userList="";
                        while(strTkns.hasMoreTokens()){
                            try{
                                String roleId = (String)strTkns.nextToken();
                                 Iterator listName=roleList.iterator();
                               while(listName.hasNext())
                               {
                                  String strRoleName[] =(String[])listName.next();
                                   rolId=strRoleName[0];
                                  rolName=strRoleName[1];
                                  if(roleId.equalsIgnoreCase(rolId))
                                  {
                                      //userList=rolName;
                                      userList=userList+rolName+",";

                                  }


                            }

                            }
                            catch(Exception e){
                                Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mapUserRoles() :" + e);
                            }
                        }

                           request.setAttribute("userNameList",userList);
                           System.out.println("user name list....."+userList);
                        

                         //For Debug Starts
                         remote.deactivateRequestStatus(userIdVal,true);
                         //For Debug Ends
                        if(userIdVal!=null){
                            userContactDetail = (ArrayList)remote.getUserContactDetails(userIdVal);
                        }


                        request.setAttribute("userId",userIdVal);



                        strTkns = new StringTokenizer(roleIds,"#");
                        ArrayList rolesList = new ArrayList();
                        while(strTkns.hasMoreTokens()){
                            try{
                                String roleId = (String)strTkns.nextToken();
                                if(roleId!=null && roleId.trim().length()!=0){
                                    Debug.print("ActionRoleMangement.mapUserRoles() Added from Stokenizer:" + roleId);
                                    rolesList.add(roleId);
                                }
                            }
                            catch(Exception e){
                                Debug.print("Exception while spliting privilegeIds ActionRoleMangement.mapUserRoles() :" + e);
                            }
                        }

                        if(userIdVal!=null){
                            Debug.print("ActionRoleMangement.mapUserRoles() All Ids Are valid");
                            roleRemote.createMappingUserWithRoles(userIdVal, rolesList);
                        }

                    }
                    catch(Exception eDisp){
                        Debug.print("while getting mapUserRoles:" + eDisp);
                    }
                    request.setAttribute("userContactDetails" ,userContactDetail);
                    Debug.print("ActionRoleMangement.mapUserRoles() sucessfully comes from servlet.");
                    return mapping.findForward("successRoleAssigned");
                }

//================================ Initial Dashboard MainBoard ========================
              else if(roleProcess.equals("initDashboard")){
                String requestStat=(String)request.getAttribute("requestStatus");
                String req_Value=(String)request.getAttribute("requestValue");
               System.out.println("Request Value is "+req_Value);
               String displayButton=(String)request.getAttribute("viewButton");
               System.out.println("display button....."+displayButton);
                Debug.print("RequestStatus In main board"+requestStat);
                try {
                Debug.print("ActionRoleMangement.initDashboard()");
                String userIdVal = (String)session.getAttribute("userId");
               // System.out.println("user id value............."+userIdVal);
                ArrayList roleList = new ArrayList();
                ArrayList entityList = new ArrayList();
                ArrayList generalPrivList = new ArrayList();
                ArrayList genealRoleList = new ArrayList();
                ArrayList privilegeList = new ArrayList();
                ArrayList permissionList = new ArrayList();
                ArrayList privilegeSubList = new ArrayList();
                ArrayList roleSubList = new ArrayList();
                ArrayList privilegeMList = new ArrayList();
                
   String viewVal=(String) session.getAttribute("viewVal");
   Debug.print("View Value in Action Role Mgt"+viewVal);
   
   
                if(userIdVal != null)
                    roleList = roleRemote.getAllRolesBasedOnUser(userIdVal);
                    String generalRoleId = "bc559b5e-3aaf-47c4-8560-d10197c86119";
                    if(generalRoleId != null && generalRoleId.trim().length() != 0)
                        genealRoleList = roleRemote.getMappingDetailsForRoleAndPrivileges(generalRoleId);
                    if(genealRoleList != null && genealRoleList.size() != 0) {
                        Iterator itEntList = genealRoleList.iterator();
                        while(itEntList.hasNext()){
                            String strRolelist[] = (String[])(String[])itEntList.next();
                            String roleId = strRolelist[1];
                            String roleName = strRolelist[3];
                            String entityId = strRolelist[2];
                            String entityName = strRolelist[4];
                            ArrayList tempPrivList = new ArrayList();
                          //  System.out.println("Role id.............."+roleId);
                          //  System.out.println("privlist..."+entityId);
                            if(roleId != null && roleId.trim().length() != 0 && entityId != null && entityId.trim().length() != 0)
                                tempPrivList = roleRemote.getMyPrivilegesListFromEntity(roleId, entityId);
                           // System.out.println("temp..."+tempPrivList);
                            if(tempPrivList != null && tempPrivList.size() != 0)
                                generalPrivList.add(tempPrivList);
                        }
                    }
                session.setAttribute("DBGeneralPrivList", null);
                session.setAttribute("DBGeneralPrivList", generalPrivList);
                String roleId = null;
                session.setAttribute("DBInitialBoard", null);
                session.setAttribute("DBInitialBoard", roleList);
                if(roleList != null && roleList.size() != 0) {
                    Iterator itEntList = roleList.iterator();
                    if(itEntList.hasNext()) {
                        String[] strRolelist = (String[])(String[])itEntList.next();
                        roleId = strRolelist[2];
                    }
                }

                if(roleId != null && roleId.trim().length() != 0) {
                    entityList = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);
                    String roleDetails[] = roleRemote.getRole(roleId);
                    String roleName = roleDetails[1];
                    if(roleName == null)
                        roleName = "";
                    session.setAttribute("roleId", roleId);
                    session.setAttribute("roleName", roleName);
                }
                session.setAttribute("DBEntityList", null);
                session.setAttribute("DBEntityList", entityList);
                session.setAttribute("viewVal", viewVal);
                String entityId = null;
                if(entityList != null && entityList.size() != 0) {
                    Iterator itEntList = entityList.iterator();
                    if(itEntList.hasNext())  {
                        String strRolelist[] = (String[])(String[])itEntList.next();
                        roleId = strRolelist[1];
                        String roleName = strRolelist[3];
                        entityId = strRolelist[2];
                        String entityName = strRolelist[4];
                        session.setAttribute("entityId", entityId);
                    }
                }
                if(roleId != null && roleId.trim().length() != 0 && entityId != null && entityId.trim().length() != 0)
                    System.out.println("before calling getMyPrivilegesListForHeader");
                    System.out.println("roleId==>"+roleId+"   entityId  ===>"+entityId);
                    String privId = request.getParameter("headPrivId");
                    privilegeList = roleRemote.getMyPrivilegesListForHeader(roleId, entityId);

                      System.out.println("before calling getMenuBasedOnEntityRolePrivileges==> length"+privilegeList.size());
                    permissionList = roleRemote.getMenuBasedOnEntityRolePrivileges(roleId, entityId, "");
                     System.out.println("after calling getMenuBasedOnEntityRolePrivileges");
                    session.setAttribute("DBPrivlegeList", null);
                    session.setAttribute("DBPrivlegeList", privilegeList);
                    session.setAttribute("DBLeftPrivlegeList" ,null);
                    session.setAttribute("DBLeftPrivlegeList" ,permissionList);
                    //System.out.println("leftPrivilegeId==>"+leftPrivilegeId+" leftPrivilegeName==>"+leftPrivilegeName+" leftAccessName ==>"+leftAccessName+" getPermissionId==>"+leftPermissionId +" leftPermissionName==>"+leftPermissionName);
                    roleSubList = roleRemote.getMenuBasedOnEntityRoleSubPrivileges(roleId, "", "","");
                    System.out.println("After calling getMenuBasedOnEntityRoleSubPrivileges "+roleId);

                    System.out.println("roleId ====>"+roleId+" roleSubList***********************size"+roleSubList.size());
                    java.util.HashMap hmSubList =new java.util.HashMap();
                    hmSubList.put(roleId,roleSubList);
                
                    /*
                     * */

                    /*ArrayList leftMenuList = new ArrayList();
                        leftMenuList=permissionList;
                           Debug.print("---------------before if***1------------");
                        if(leftMenuList!=null && leftMenuList.size()!=0){//if***1 block
                             Debug.print("---------------Inside if***1------------");
                              Iterator itMenu = leftMenuList.iterator();
                                    int showPriv = 0;
                                     Debug.print("---------------before while0 ------------");
                                     java.util.HashMap hmSubList =new java.util.HashMap();
                                    while(itMenu.hasNext()){//while0 block
                                            HLCMenuListVO leftMenuVO = (HLCMenuListVO) itMenu.next();
                                            Debug.print("---------------Inside while0 ------------");
                                            if(leftMenuVO.getRoleId()!=null && leftMenuVO.getEntityId()!=null){//if0 block
                                                    Debug.print("---------------Inside while0  && if0 block------------");
                                                    String leftRoleId = leftMenuVO.getRoleId();
                                                    String leftEntityId = leftMenuVO.getEntityId();
                                                    String leftPrivilegeId = leftMenuVO.getPrivilegeId();
                                                    String leftPrivilegeName = leftMenuVO.getPrivilegeName();
                                                    String leftAccessName = leftMenuVO.getAccessName();
                                                    String leftAccessURL = leftMenuVO.getAccessUrl();
                                                    String leftPermissionId = leftMenuVO.getPermissionId();
                                                    String leftPermissionName = leftMenuVO.getPermissionName();
                                                    showPriv++;
                                                    Debug.print("---------------before getMenuBasedOnEntityRoleSubPrivileges------------");

                                                    System.out.println("leftPrivilegeId==>"+leftPrivilegeId+" leftPrivilegeName==>"+leftPrivilegeName+" leftAccessName ==>"+leftAccessName+" getPermissionId==>"+leftPermissionId +" leftPermissionName==>"+leftPermissionName);
                                                    privilegeSubList = roleRemote.getMenuBasedOnEntityRoleSubPrivileges(leftRoleId, leftEntityId, leftPrivilegeId,leftPermissionId);
                                                    System.out.println("After calling getMenuBasedOnEntityRoleSubPrivileges "+leftPermissionId);

                                                    System.out.println("leftPrivilegeId ====>"+leftPrivilegeId+" sub permisslion***********************size"+privilegeSubList.size());
                                                    hmSubList.put(leftPrivilegeId,privilegeSubList);
                                            }//if0 block end
                                    }//while0 block end
                                     session.setAttribute("DBLeftSubPrivlegeList" ,null);
                                     session.setAttribute("DBLeftSubPrivlegeList" ,hmSubList);
                        }//if***1 block end

                        Debug.print("---------------before if2------------");
                    /*
                     *
                     *
                     */
                    session.setAttribute("DBLeftSubPrivlegeList" ,null);
                    session.setAttribute("DBLeftSubPrivlegeList" ,hmSubList);
                    //session.setAttribute("DBLeftSubPrivlegeList" ,null);
                   // session.setAttribute("DBLeftSubPrivlegeList" ,permissionList);
                }
                catch(Exception eDisp) {
                    Debug.print("while calling initalDashboard:" + eDisp);
                }
                Debug.print("ActionRoleMangement.initDashboard() sucessfully comes from servlet.");
                if(requestStat.equalsIgnoreCase("true"))
                {
                     System.out.println("first if ......");
                    session.setAttribute("requestStat","true");
                    session.setAttribute("req_Value","true");

                    return mapping.findForward("LoginSuccess");
                }
                else if(displayButton!=null && requestStat.equalsIgnoreCase("false") && displayButton.equalsIgnoreCase("true"))
                {
                    System.out.println("second if else......");

                    request.setAttribute("viewButton","true");
                    session.setAttribute("viewButton","true");

                    return mapping.findForward("LoginSuccessNoRole");
                }
                else if(requestStat.equalsIgnoreCase("false") && req_Value.equalsIgnoreCase("true"))
                {
                    System.out.println("Third if else......");
                    request.setAttribute("viewButton","true");
                    session.setAttribute("viewButton","true");
                    session.setAttribute("requestStat","false");
                    session.setAttribute("req_Value","true");
                    return mapping.findForward("LoginSuccessNoRole");
                }
                else if(requestStat.equalsIgnoreCase("false") && req_Value.equalsIgnoreCase("false"))
                    {
                       System.out.println("Fourth if else......");
                    session.setAttribute("requestStat","false");
                    session.setAttribute("req_Value","false");
                    return mapping.findForward("LoginSuccessNoRole");
                }

            } else if(roleProcess.equals("selectViewpoints")){
            	   String sessUserId=(String)session.getAttribute("userId");
                   ArrayList viewPointDetailsList=roleRemote.getViewPointDetailsList(sessUserId);
                   ArrayList viewPointArtifactList=roleRemote.getViewPointArtifactList(sessUserId,null,null,null,null,null);
                   request.setAttribute("viewPointArtifactList", viewPointArtifactList);
                   session.setAttribute("viewPointDetailsList", viewPointDetailsList);
                   return mapping.findForward("viewpoint");
            }     
//================================ Role and Entity Dashboard MainBoard ========================
            else if(roleProcess.equals("selectEntityDashboard")){
                    try {
                        Debug.print("ActionRoleMangement.selectEntityDashboard()");
                        String headRoleId = request.getParameter("headRoleId");
                        ArrayList entityList = new ArrayList();
                        ArrayList generalPrivList = new ArrayList();
                        ArrayList genealRoleList = new ArrayList();
                        ArrayList privilegeList = new ArrayList();
                        Debug.print("roleId:" + headRoleId);
                        if(headRoleId != null && headRoleId.trim().length() != 0) {
                            entityList = roleRemote.getMappingDetailsForRoleAndPrivileges(headRoleId);
                            String roleDetails[] = roleRemote.getRole(headRoleId);
                            String roleName = roleDetails[1];
                            if(roleName == null)
                                roleName = "";
                            session.setAttribute("roleId", headRoleId);
                            session.setAttribute("roleName", roleName);
                        }

                        session.setAttribute("DBEntityList", null);
                        session.setAttribute("DBEntityList", entityList);

                        String entityId = null;
                        if(entityList != null && entityList.size() != 0){
                            Iterator itEntList = entityList.iterator();
                            if(itEntList.hasNext()) {
                                String strRolelist[] = (String[])(String[])itEntList.next();
                                headRoleId = strRolelist[1];
                                String roleName = strRolelist[3];
                                entityId = strRolelist[2];
                                String entityName = strRolelist[4];
                                session.setAttribute("entityId", entityId);
                            }
                        }
                        if(headRoleId != null && headRoleId.trim().length() != 0 && entityId != null && entityId.trim().length() != 0)
                            privilegeList = roleRemote.getMyPrivilegesListForHeader(headRoleId, entityId);
                        session.setAttribute("DBPrivlegeList", null);
                        session.setAttribute("DBPrivlegeList", privilegeList);
                    }
                    catch(Exception eDisp) {
                        Debug.print("while calling selectEntityDashboard:" + eDisp);
                    }
                    Debug.print("ActionRoleMangement.selectEntityDashboard() sucessfully comes from servlet.");
                    return mapping.findForward("frmRolEntityList");
                }
//================================ Role and Entity Dashboard MainBoard ========================
            else if(roleProcess.equals("selectEntPrivePermDashboard")){
                String entityName=null;
                String roleName=null;
                    try{
                        Debug.print("ActionRoleMangement.selectEntPrivePermDashboard()");
                        String roleId = request.getParameter("headRoleId");
                        String entityId = request.getParameter("headEntityId");
                        entityName = request.getParameter("headEntityName");
                        Debug.print("entityName" +entityName);
                        roleName=request.getParameter("headRoleName");

                        ArrayList privilegeList = new ArrayList();
                        ArrayList permissionList = new ArrayList();

                        Debug.print("roleId:" + roleId);
                        Debug.print("entityId:" + entityId);

                        if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0){
                            privilegeList = roleRemote.getMyPrivilegesListForHeader(roleId, entityId);
                        }
                       session.setAttribute("entityId", entityId);
                       session.setAttribute("DBPrivlegeList" ,null);
                       session.setAttribute("DBPrivlegeList" ,privilegeList);
                       //Debug.print("privilegeList:" + privilegeList);
                      if(privilegeList!=null && privilegeList.size()!=0){
                        Iterator itPrivRoleList = privilegeList.iterator();
                        if(itPrivRoleList.hasNext()){
                                String strPrivilegeList[]= (String[])itPrivRoleList.next();
                                String privPrivlegeId = strPrivilegeList[0];
                                String privilegeName = strPrivilegeList[1];
                                String privRoleId = strPrivilegeList[2];
                                String privEntityId = strPrivilegeList[3];
                                 if(privRoleId!=null && privRoleId.trim().length()!=0 && privEntityId!=null && privEntityId.trim().length()!=0
                                        && privPrivlegeId!=null && privPrivlegeId.trim().length()!=0 ){
                                    permissionList = roleRemote.getMenuBasedOnEntityRolePrivileges(privRoleId, privEntityId, privPrivlegeId);
                            }
                        }
                      }
                       session.setAttribute("DBLeftPrivlegeList" ,null);
                       session.setAttribute("DBLeftPrivlegeList" ,permissionList);

                       if(entityName!=null && entityName.equalsIgnoreCase("Online Entries") && roleName!=null && roleName.equalsIgnoreCase("User")){
                     ArrayList allAreaList = null;
                String area="";
                HLCMemberVO memDetails = new HLCMemberVO();
                String memberId=(String)session.getAttribute("memberId");
                allAreaList = (ArrayList)eveCalRemote.getAllAreaMasters();
                if(allAreaList!=null&& allAreaList.size()!=0){
		Iterator areaLst = allAreaList.iterator();
		while(areaLst.hasNext()){
		String AreaLstArr[] = (String [])areaLst.next();
		String area_id = AreaLstArr[0];

                Debug.print("memberId:" +memberId);
                Debug.print("area_id:" +area_id);
                String zipCode=eveCalRemote.getZipCodeDetail(memberId);
                Debug.print("zipCode:" +zipCode);
                area=eveCalRemote.getAreaDetail(zipCode);
                }
                }
                if(memberId!=null && memberId.trim().length()!=0){
                    memDetails = qremote.getMemberDetails(memberId);
                    request.setAttribute("isMember","yes");
                }else{
                    request.setAttribute("isMember","no");
                }
                request.setAttribute("memDetails",memDetails);

                ArrayList objAppEvent = null;
                objAppEvent = eveCalRemote.getFinalizedEventCalDetails(null);
                Debug.print("getFinalizedEventCalDetails called");


                request.setAttribute("allAreaList",allAreaList);
                request.setAttribute("objAppEvent",objAppEvent);
          }
                    }
                    catch(Exception eDisp){
                        Debug.print("while calling selectEntPrivePermDashboard:" + eDisp);
                    }
                if(entityName!=null && entityName.equalsIgnoreCase("Online Entries") && roleName!=null && roleName.equalsIgnoreCase("User")){
                  Debug.print("Inside Online Entries:");
                    return mapping.findForward("frmFinalizedEventCalendar");
                  }

                   Debug.print("ActionRoleMangement.selectEntPrivePermDashboard() sucessfully comes from servlet.");
                    return mapping.findForward("menu-roles-leftmenu");
                }
//================================ Role and Entity Dashboard MainBoard ========================
   else if(roleProcess.equals("selectRoleEntPrivePermDashboard")){
                    try{
                        Debug.print("ActionRoleMangement.selectRoleEntPrivePermDashboard()");
                        String roleId = request.getParameter("headRoleId");
                        String entityId = request.getParameter("headEntityId");
                        String privId = request.getParameter("headPrivId");
                        String permisId=request.getParameter("headPermissionId");
                        ArrayList privilegeList = new ArrayList();
                        ArrayList privilegeSubList = new ArrayList();


                        Debug.print("roleId:" + roleId);
                        Debug.print("entityId:" + entityId);
                        Debug.print("privId:" + privId);
                        Debug.print("permisId:" + permisId);

                        Debug.print("---------------before if1------------");
                        if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0
                                && privId!=null && privId.trim().length()!=0 ){
                            privilegeList = roleRemote.getMenuBasedOnEntityRolePrivileges(roleId, entityId, privId);
                        }
                       session.setAttribute("DBLeftPrivlegeList" ,null);
                       session.setAttribute("DBLeftPrivlegeList" ,privilegeList);
                       Debug.print("---------------After setting DBLeftPrivlegeList in session------------");
                        ArrayList leftMenuList = new ArrayList();
                        leftMenuList=privilegeList;
                           Debug.print("---------------before if***1------------");
                        if(leftMenuList!=null && leftMenuList.size()!=0){//if***1 block
                             Debug.print("---------------Inside if***1------------");
                              Iterator itMenu = leftMenuList.iterator();
                                    int showPriv = 0;
                                     Debug.print("---------------before while0 ------------");
                                     java.util.HashMap hmSubList =new java.util.HashMap();
                                    while(itMenu.hasNext()){//while0 block
                                            HLCMenuListVO leftMenuVO = (HLCMenuListVO) itMenu.next();
                                            Debug.print("---------------Inside while0 ------------");
                                            if(leftMenuVO.getRoleId()!=null && leftMenuVO.getEntityId()!=null){//if0 block
                                                    Debug.print("---------------Inside while0  && if0 block------------");
                                                    String leftRoleId = leftMenuVO.getRoleId();
                                                    String leftEntityId = leftMenuVO.getEntityId();
                                                    String leftPrivilegeId = leftMenuVO.getPrivilegeId();
                                                    String leftPrivilegeName = leftMenuVO.getPrivilegeName();
                                                    String leftAccessName = leftMenuVO.getAccessName();
                                                    String leftAccessURL = leftMenuVO.getAccessUrl();
                                                    String leftPermissionId = leftMenuVO.getPermissionId();
                                                    String leftPermissionName = leftMenuVO.getPermissionName();
                                                    showPriv++;
                                                    Debug.print("---------------before getMenuBasedOnEntityRoleSubPrivileges------------");

                                                    System.out.println("leftPrivilegeId==>"+leftPrivilegeId+" leftPrivilegeName==>"+leftPrivilegeName+" leftAccessName ==>"+leftAccessName+" getPermissionId==>"+leftPermissionId +" leftPermissionName==>"+leftPermissionName);
                                                    privilegeSubList = roleRemote.getMenuBasedOnEntityRoleSubPrivileges(leftRoleId, leftEntityId, leftPrivilegeId,leftPermissionId);
                                                    System.out.println("After calling getMenuBasedOnEntityRoleSubPrivileges ");
                                                    hmSubList.put(leftPermissionId,privilegeSubList);




                                            }//if0 block end
                                    }//while0 block end
                                     session.setAttribute("DBLeftSubPrivlegeList" ,null);
                                     session.setAttribute("DBLeftSubPrivlegeList" ,hmSubList);
                        }//if***1 block end

                        Debug.print("---------------before if2------------");
                       /*if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0
                                 && privId!=null && privId.trim().length()!=0 && permisId!=null && permisId.trim().length()!=0 ){
                            Debug.print("---------------before getMenuBasedOnEntityRoleSubPrivileges------------");
                            privilegeSubList = roleRemote.getMenuBasedOnEntityRoleSubPrivileges(roleId, entityId, privId,permisId);

                            Debug.print("---------------after getMenuBasedOnEntityRoleSubPrivileges------------");
                        }
                        session.setAttribute("DBLeftSubPrivlegeList" ,null);
                        session.setAttribute("DBLeftSubPrivlegeList" ,privilegeSubList);

                        */
                      // Debug.print("privilegeList:" + privilegeList);
                    }
                    catch(Exception eDisp){
                        Debug.print("while calling selectRoleEntPrivePermDashboard:" + eDisp);
                    }

                    Debug.print("ActionRoleMangement.selectRoleEntPrivePermDashboard() sucessfully comes from servlet.");
                    return mapping.findForward("menu-roles-leftmenu");
                }
            else if(roleProcess.equalsIgnoreCase("requestRole"))
            {
                Object obj=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
                HLCkaverystatelessRemoteHome homeNew =
                    (HLCkaverystatelessRemoteHome) PortableRemoteObject.narrow(obj, HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remoteNew = homeNew.create();
                    int result=remoteNew.updateUserRequestStatus(userId);
                    System.out.println("Result Output"+result);
                    request.setAttribute("requestStatus","false");
                    if(result==1)
                     request.setAttribute("requestValue","true");
                    else
                       request.setAttribute("requestValue","false");
                    request.setAttribute("viewButton","true");
                return mapping.findForward("frmUserRequestRole");
            }

            else if(roleProcess.equalsIgnoreCase("listSubViews"))
            {
               String lobId=request.getParameter("lobId");
               String view_point_id=request.getParameter("view_point_id");
               String viewId=request.getParameter("viewId");
               String groupId=request.getParameter("groupId");
               String processId=request.getParameter("processId");
               String user_Id=(String)session.getAttribute("userId");
               
               System.out.println("viewId-----------------------"+viewId);
               
               ArrayList subViewList=eveCalRemote.getSubViewList(lobId,view_point_id);  
               ArrayList treeDetailsList=eveCalRemote.getTreeArtifactDetails(user_Id,lobId);
               ArrayList viewPointArtifactList=roleRemote.getViewPointArtifactList(user_Id,view_point_id,lobId,viewId,groupId,processId);
               
               session.setAttribute("subViewList", subViewList);
               request.setAttribute("LOBId", lobId); 
               request.setAttribute("objViewPointId", view_point_id); 
               request.setAttribute("treeDetailsList", treeDetailsList);
               request.setAttribute("viewPointArtifactList", null);
               request.setAttribute("viewPointArtifactList", viewPointArtifactList);
               return mapping.findForward("viewpoint");
            }
            else if(roleProcess.equalsIgnoreCase("dispRoleToUser"))
            {
            	 
            	  String fname = request.getParameter("fname");
                  String lname = request.getParameter("lname");
                  String empId = request.getParameter("empId");  
                 
                  request.setAttribute("fName", fname);
                  request.setAttribute("lName", lname);
                  request.setAttribute("empId", empId);                
                  request.setAttribute("chgStat","");
                  String roleId = request.getParameter("roleId");
                  if(roleId!=null){
                      Debug.print("ActionRoleMangement.roleEntSelect()");
                      ArrayList roleList = new ArrayList();
                      ArrayList entityList = new ArrayList();
                      ArrayList mapRoleEnt = new ArrayList();
                      roleList = (ArrayList)roleRemote.getAllRoles();
                      entityList = (ArrayList)roleRemote.getAllEntity();

                      mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);

                      request.setAttribute("roleDetails" ,null);
                      request.setAttribute("roleDetails" ,roleList);

                      request.setAttribute("enityDetails" ,null);
                      request.setAttribute("enityDetails" ,entityList);

                      request.setAttribute("mapDetails" ,null);
                      request.setAttribute("mapDetails" ,mapRoleEnt);
                      request.setAttribute("roleId", roleId);
                      //{mapEntityId, roleIdVal, entityId, roleName, entityName};
                  }
                  
                  return mapping.findForward("dispRoletoUser");
            	
            }
            else if(roleProcess.equalsIgnoreCase("confirmProcess"))
            {
            	 
            	 String uEmail = request.getParameter("uEmail");
           	     String uLogin = request.getParameter("uLogin");
           	    String sEmail = request.getParameter("sEmail");
           	    String uRole = request.getParameter("uRole");
           	    String fname = request.getParameter("fname");
                 String lname = request.getParameter("lname");
                 String empId = request.getParameter("empId");  
                 String supName = request.getParameter("supName");
                 String roleId = request.getParameter("roleId");	
                 String uId = request.getParameter("userId");
                 String depId = request.getParameter("depId");
                 
                  request.setAttribute("fName", fname);
                  request.setAttribute("lName", lname);
                  request.setAttribute("empId", empId);                
                  request.setAttribute("chgStat","");
                 
                  if(roleId!=null){
                      Debug.print("ActionRoleMangement.roleEntSelect()");
                      ArrayList entPriPermList = new ArrayList();
                      ArrayList disEntPriPermList = new ArrayList();
                   
                      entPriPermList = (ArrayList)roleRemote.getEntityPrivPermByRoleId(roleId);
                      disEntPriPermList = (ArrayList)roleRemote.getDistinctEntityPrivByRoleId(roleId);

                     

                      request.setAttribute("entPriPermList" ,null);
                      request.setAttribute("entPriPermList" ,entPriPermList);

                      request.setAttribute("disEntPriPermList" ,null);
                      request.setAttribute("disEntPriPermList" ,disEntPriPermList);

                      request.setAttribute("roleId", roleId);
                      request.setAttribute("uLogin", uLogin);
                      request.setAttribute("uRole", uRole);
                      request.setAttribute("uRole", uRole);
                      
                      //{mapEntityId, roleIdVal, entityId, roleName, entityName};
                  }
                  
                  return mapping.findForward("dispRoleEntPriMap");
            	
            }
               
            else if(roleProcess.equalsIgnoreCase("confirmRoleToUser"))
            {
            	 
            	  //uEmail,sEmail,uRole
            	  String uEmail = request.getParameter("uEmail");
            	  String uLogin = request.getParameter("uLogin");
            	  String sEmail = request.getParameter("sEmail");
            	  String uRole = request.getParameter("uRole");
            	  String fname = request.getParameter("fname");
                  String lname = request.getParameter("lname");
                  String empId = request.getParameter("empId");  
                  String supName = request.getParameter("supName");
                  String roleId = request.getParameter("roleId");	
                  String uId = request.getParameter("userId");
                  String depId = request.getParameter("depId");
                  //String uId = request.getParameter("uId");  
                 
                  boolean result=roleRemote.chgUserStatBySup(uId,session.getAttribute("loginName").toString(),roleId);
                  ArrayList empSearchList = remote.searchEmpBySearchCriteria(fname, lname, empId,supName);
                  request.setAttribute("fName", fname);
                  request.setAttribute("lName", lname);
                  request.setAttribute("empId", empId);  
                  request.setAttribute("supName", supName);   
                  request.setAttribute("empSearchList",empSearchList);
                   request.setAttribute("chgStat","success");
                                  
                  
                 // return mapping.findForward("empSearchList");
                   return mapping.findForward("dispRoletoUser");
                   
            	
            }
            else if(roleProcess.equalsIgnoreCase("chgUserStat"))
            {
            	  String uId = request.getParameter("uId");
            	  StringBuffer supEmailList1=new StringBuffer();
            	  //uEmail,sEmail,uRole
            	  String uEmail = request.getParameter("uEmail");
            	  String uLogin = request.getParameter("uLogin");
            	  String sEmail = request.getParameter("sEmail");
            	  String uRole = request.getParameter("uRole");
            	  String fname = request.getParameter("fname");
                  String lname = request.getParameter("lname");
                  String empId = request.getParameter("empId");  
                  String supName = request.getParameter("supName");
                  String roleId = request.getParameter("roleId");
                  
                  
                  boolean chgStatus = roleRemote.chgUserStatByAdmin(uId);
                  boolean result=roleRemote.chgUserStatBySup(uId,supName,roleId);
                  String supEmailList=roleRemote.getSupervisorEmailByEmpId(uId);
                  Debug.print("result==="+result+"==supEmailList=="+supEmailList);
                  ArrayList empSearchList = remote.searchEmpBySearchCriteria(fname, lname, empId,"");
                  request.setAttribute("fName", fname);
                  request.setAttribute("lName", lname);
                  request.setAttribute("empId", empId);  
                  request.setAttribute("supName", supName);   
                  request.setAttribute("empSearchList",empSearchList);
                  request.setAttribute("supEmailList",supEmailList);
                  
                  supEmailList1.append(uEmail).append(",").append(supEmailList);
                  String supEmail1=supEmailList1.toString();
                  System.out.println("supEmail1===="+supEmail1);
                  //StringBuffer mailids=new StringBuffer();
                  //mailids.append("'"+emailid+"'"+","+"'"+supEmail1+"'");
                  		
                 // String toMailIds[] = {emailid,supEmail1};
                  
                 
                  
                  if(chgStatus==true)
                  {
                	  
                	  int l = 0;
                      StringTokenizer st = new StringTokenizer(supEmail1, ",");
                      String toMailIds[] = new String[st.countTokens()];
                     // toMailIds[0]=emailid;
                      while (st.hasMoreTokens()) {
                          toMailIds[l] = st.nextToken();
                          
                          l++;

                      }
                     EmailContent email = new EmailContent();
                     email.setTo(toMailIds);                     
                     //email.setFrom("anandv@digiblitz.com");
                     email.setFrom("info_elmt@digiblitz.com");                     
                     email.setSubject("Your Account Info !");
                     

                     String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                             " <tr>" +
                             " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                             " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                             "<tr>" +
                             "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"ELMT Online Services Email Template\" width=\"515\" height=\"55\" /></td> " +
                             " </tr>" +
                             "  <tr>" +
                             "<td valign=\"top\">" +
                             "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                             "<tr>" +
                             "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                             "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                             "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                             "</tr>" +
                             "<tr>" +
                             "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                             "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                             "<span class=\"boldTxt\">Dear " + uLogin + "</span>,<br /><br />" +
                             "<p>Your account has been activated . Please use the following creditianls for access the our dashboard <p>" + "<p>----------------------------<p>" +
                             "<p>UserName :" + uLogin +  "<p>Password :" + uLogin + "<p> RoleName: " + uRole + "<p> ----------------------------<p>" + 
                             /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                             "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
                             "Thank you for using the service provided by <span class=\"boldTxt\">Enterprise Landscape Management & Transformation</span>.</p>" +
                             "Thank You <br />" +
                             "------------------ <br />" +
                             "<span class=\"boldRedTxt\">ELMT Team</span></td>" +
                             "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                             "</tr>" +
                             "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                             "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                             "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                             "</tr>" +
                             " </table>" +
                             "</td></tr>" +
                              " <tr>" +
                             "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                             "</tr>" +
                             "</table>";


                     email.setBody(content);
                     //email.setAttachments();

                     EmailEngine emailEngine = new EmailEngine();
                     boolean emailFlag = emailEngine.sendMimeEmail(email);
                     Debug.print("Email sent sucessfully :" + emailFlag);
                	  request.setAttribute("moduleStat","success");
                	  
                  }
                  
                  
                  return mapping.findForward("empSearchList");
            	
            }
            else if(roleProcess.equals("initSupRoleEntPriv")){
                try{
                	String roleId = request.getParameter("roleId");
                	String uName = request.getParameter("uName");
                	String roleName = request.getParameter("roleName");
                	String uId = request.getParameter("userId");
                	String deptName=request.getParameter("deptName");
                	String empId=request.getParameter("empId");
                    Debug.print("roleId:" + roleId);
                    request.setAttribute("roleId",roleId);
                    request.setAttribute("uName",uName);
                    request.setAttribute("roleName",roleName);

                    Debug.print("ActionRoleMangement.initSelectRoleEnt()");
                    ArrayList roleList = new ArrayList();
                    ArrayList mapRoleEnt = new ArrayList();

                    roleList = (ArrayList)roleRemote.getAllRoles();
                    mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);
                    boolean result=roleRemote.chgUserStatBySup(uId,session.getAttribute("loginName").toString(),roleId);
                    String emailId[] = {"ashwini.ramesh@digiblitz.com","deepa.v@digiblitz.com","ramachandran@digiblitz.com"};
                    Debug.print("result === :" + result);
             	   
             	   String supName = (String)session.getAttribute("loginName");
             	   
                    if(result==true)
                    {
                  	  EmailContent email = new EmailContent();
                        email.setTo(emailId);                     
                        //email.setFrom("anandv@digiblitz.com");
                        email.setFrom("info_elmt@digiblitz.com");                     
                        email.setSubject("User Approval Status Info !");
                        

                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"ELMT Online Services Email Template\" width=\"515\" height=\"55\" /></td> " +
                                " </tr>" +
                                "  <tr>" +
                                "<td valign=\"top\">" +
                                "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                                "<tr>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                                "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                                "</tr>" +
                                "<tr>" +
                                "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                                "<span class=\"boldTxt\">Dear Admin </span>,<br /><br />" +
                                "<p> The following user details has been approved by " + supName +" .<br /><br />"+ "Please refer user and mapped role details " +
                                "<p>Employee Id :" + empId + "<p>UserName :" + uName + "<p> RoleName: " + roleName  + "<p> ----------------------------<p>" + 
                               
                                "Thank you for using the service provided by <span class=\"boldTxt\">Enterprise Landscape Management and Transformation</span>.</p>" +
                                "Thank You <br />" +
                                "------------------ <br />" +
                                "<span class=\"boldRedTxt\">ELMT Team</span></td>" +
                                "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                " </table>" +
                                "</td></tr>" +
                                 " <tr>" +
                                "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                                "</tr>" +
                                "</table>";


                        email.setBody(content);
                        //email.setAttachments();

                        EmailEngine emailEngine = new EmailEngine();
                       boolean emailFlag = emailEngine.sendMimeEmail(email);
                       Debug.print("Email sent sucessfully :" + emailFlag);
                    }
                    request.setAttribute("roleDetails" ,null);
                    request.setAttribute("roleDetails" ,roleList);

                    request.setAttribute("mapDetails" ,null);
                    request.setAttribute("mapDetails" ,mapRoleEnt);
                }
                catch(Exception eDisp){
                    Debug.print("while getting initEntPriv:" + eDisp);
                }

                Debug.print("ActionRoleMangement.initRoleEntPriv() sucessfully comes from servlet.");
                return mapping.findForward("frmSupRolMRoleMapping");
            }
               
            else if(roleProcess.equals("initSelectSupRoleEntPriv")){
           	 String roleId = null;
                String roleName =null;
           	 String uName =null;
           	 String empRolMap=null;
               try{
                   Debug.print("ActionRoleMangement.initSelectSupRoleEntPriv()");
                    roleId = request.getParameter("roleId");
                   String entityId = request.getParameter("entityId");
                    roleName = request.getParameter("roleName");
                    uName = request.getParameter("uName");
                   Debug.print("roleId:" + roleId);
                   request.setAttribute("roleId",roleId);
                   Debug.print("entityId:" + entityId);
                   request.setAttribute("entityId",entityId);

                   ArrayList roleList = new ArrayList();
                   ArrayList mapRoleEnt = new ArrayList();
                   ArrayList mapEntPriv = new ArrayList();
                   ArrayList mapRoleEntPriv = new ArrayList();
                     ArrayList mapRoleEntPrivPer = new ArrayList();
                   ArrayList mapRoleSubPer = new ArrayList();


                   roleList = (ArrayList)roleRemote.getAllRoles();
                   mapRoleEnt = roleRemote.getMappingDetailsForRoleAndPrivileges(roleId);

                   if(entityId!=null){
                       mapEntPriv = roleRemote.getMappingDetailsForEnitityAndPrivileges(entityId);
                   }

                   if(roleId!=null && entityId!=null){
                       mapRoleEntPriv = roleRemote.getPermissionBasedOnEntityRole(roleId,entityId);
                   }


                 //Start:[RoleMgt] For Role,Privilege,Permission Mapping
                ArrayList subPermList = new ArrayList();
                   subPermList = (ArrayList)roleRemote.getMappingDetailsForRoleSubPerm();

                   request.setAttribute("allSubPerm" ,null);
                   request.setAttribute("allSubPerm" ,subPermList);
                   ArrayList permList = new ArrayList();
                   permList = (ArrayList)roleRemote.getAllPermission();
                   //For Debug
                   ArrayList allMapPrivList = new ArrayList();
                   allMapPrivList = (ArrayList)roleRemote.getAllMapPrivilege(roleId, entityId);

                   request.setAttribute("allMapPrivDetails" ,null);
                   request.setAttribute("allMapPrivDetails" ,allMapPrivList);

                   ArrayList allMapPermList = new ArrayList();
                   allMapPermList = (ArrayList)roleRemote.getAllMapPermission(roleId, entityId);

                   request.setAttribute("allMapPermDetails" ,null);
                   request.setAttribute("allMapPermDetails" ,allMapPermList);

                   //End:[RoleMgt] For Role,Privilege,Permission Mapping

                   request.setAttribute("permissionDetails" ,null);
                   request.setAttribute("permissionDetails" ,permList);

                   request.setAttribute("roleDetails" ,null);
                   request.setAttribute("roleDetails" ,roleList);

                   request.setAttribute("mapDetails" ,null);
                   request.setAttribute("mapDetails" ,mapRoleEnt);

                   request.setAttribute("mapEntPrivDetails" ,null);
                   request.setAttribute("mapEntPrivDetails" ,mapEntPriv);

                   request.setAttribute("mapRoleEntPrivDetails" ,null);
                   request.setAttribute("mapRoleEntPrivDetails" ,mapRoleEntPriv);

               }
               catch(Exception eDisp){
                   Debug.print("while getting initSelectSupRoleEntPriv:" + eDisp);
               }

               Debug.print("ActionRoleMangement.initSelectSupRoleEntPriv() sucessfully comes from servlet.");
             
               	 request.setAttribute("roleId",roleId);
               	 request.setAttribute("roleName",roleName);
               	 request.setAttribute("uName",uName);
               	return mapping.findForward("frmSupRolMRoleMapping");
               
           }
            else if(roleProcess.equals("mapSupRoleEntityPrivPerms")){
            	String empRolMap=null;
            	String uName=null;
            	String roleName=null;
                try{
                    Debug.print("ActionRoleMangement.mapSupRoleEntityPrivPerms()");
                    String roleId = request.getParameter("roleId");
                    String entityId = request.getParameter("entityId");
                    empRolMap = request.getParameter("empRolMap");
                    roleName = request.getParameter("roleName");
                    uName = request.getParameter("uName");
                    Debug.print("roleId:" + roleId);
                    request.setAttribute("roleId",roleId);
                    Debug.print("entityId:" + entityId);
                    request.setAttribute("entityId",entityId);
                    

                    if(roleId!=null && roleId.trim().length()!=0 && entityId!=null && entityId.trim().length()!=0){
                        Debug.print("ActionRoleMangement.mapSupRoleEntityPrivPerms() Role ID and EntityIds are valid");
                        String PrivPermIds = request.getParameter("PrivPermIds");
                        Debug.print("PrivPermIds:" + PrivPermIds);
                        ArrayList privPermList = new ArrayList();
                        if(PrivPermIds!=null && PrivPermIds.trim().length()!=0){
                            StringTokenizer strTkns = new StringTokenizer(PrivPermIds,"#");
                            while(strTkns.hasMoreTokens()){
                                try{
                                    String permIds = (String)strTkns.nextToken();
                                    StringTokenizer strTknPerm = new StringTokenizer(permIds,"!");
                                    while(strTknPerm.hasMoreTokens()){
                                        String privId = (String)strTknPerm.nextToken();
                                        String permissionId = (String)strTknPerm.nextToken();
                                        Debug.print("Splitted privId:" + privId);
                                        Debug.print("Splitted permissionId:" + permissionId);
                                        if(privId!=null && permissionId!=null && privId.trim().length()!=0 && permissionId.trim().length()!=0){
                                            String [] arrayPrivPerm = {privId,permissionId};
                                            privPermList.add(arrayPrivPerm);
                                        }
                                    }
                                }
                                catch(Exception e){
                                    Debug.print("Exception while spliting privilegeIds and PermissionIds ActionRoleMangement.mapSupRoleEntityPrivPerms() :" + e);
                                }
                            }
                        }
                                        //For Debug Starts
                                        String permSubPermIds[] = request.getParameterValues("subPermChk");
                                        String permId = null;
                                        String subPermId = null;
                                        int permSubPermLen = 0;

                                        if(permSubPermIds!=null && !permSubPermIds.equals(""))
                                        {
                                            permSubPermLen = permSubPermIds.length;
                                        }

                                       
                                        String permIdArr[] = new String[permSubPermLen];
                                    
                                        String subPermIdArr[] = new String[permSubPermLen];
                                     
                                        if(permSubPermIds!=null){
                                        for(int i=0;i <permSubPermIds.length; i++)
                                        {
                                                StringTokenizer permSubPermTkns = new StringTokenizer(permSubPermIds[i],"#");
                                                    System.out.println("String token 1 "+permSubPermIds[i]);
                                                while(permSubPermTkns.hasMoreTokens()){

                                                     permId =  (String)permSubPermTkns.nextToken();
                                                     System.out.println("String token 2 "+permId);

                                                     subPermId =  (String)permSubPermTkns.nextToken();
                                                     System.out.println("String token 3 "+subPermId);
                                                }
                                                 permIdArr[i] =  permId;
                                                 subPermIdArr[i] =  subPermId;
                                                 System.out.println("String token 4 "+permIdArr[i] +"===="+subPermIdArr[i]);

                                        }
                        }
                                            System.out.println("String token 5 ");
                                        //roleRemote.generateMappingPermSubPerm(permIdArr,subPermIdArr);
                                          System.out.println("String token 6 ");
                                        //For Debug Ends
                        Debug.print("ActionRoleMangement.mapSupRoleEntityPrivPerms() All Ids Are valid");
                        roleRemote.generateMappingRoleWithEntitiesAndPrivileges(roleId, entityId, privPermList);
                    }
                }
                catch(Exception eDisp){
                    Debug.print("while generation mapSupRoleEntityPrivPerms:" + eDisp);
                }
            
                Debug.print("ActionRoleMangement.mapSupRoleEntityPrivPerms() sucessfully comes from servlet.");
                
                	request.setAttribute("uName",uName);
                	request.setAttribute("roleName",roleName);
                 return mapping.findForward("redirectToMapSuccess");
            }
               
   //============================================GLOBAL TRY BLOCK END===================================================================
            }
//===============================================GLOBAL CATCH BLOCK================================================================
            catch(Exception exp){
                Debug.print("In ActionRoleMangement :" + exp.getMessage() );
            }
//============================================GLOBAL FORWARD METHOD===================================================================
              return mapping.findForward("LoginSuccess");
        }
}
