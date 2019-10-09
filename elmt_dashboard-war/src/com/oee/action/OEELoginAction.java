/*
 * OEELoginAction.java
 *
 * Created on November 12, 2007, 12:36 PM
 */

package com.oee.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import com.hlcrole.management.HLCBrahmaputraSessionRemote;
import com.hlcrole.management.HLCBrahmaputraSessionRemoteHome;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;
import com.hlcutil.HLCCalendarVO;
import com.hlcutil.Debug;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author Ellamaran
 * @version
 */

public class OEELoginAction extends Action {
    Vector vObj = new Vector();
    String status=null;
    String userId = null;
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.pc");
            
            String jName=mr.getMessage("jndi.messages");
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jName);
            
            HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
            HLCMessageSessionRemote msgRemote = msgHome.create();
            
            Object obj=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome) PortableRemoteObject.narrow(obj, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote remote = home.create();
            
            Object krishnaObj = jndiContext.lookup(jndiname);
            HLCKrishnaStatelessRemoteHome krishnaHome = (HLCKrishnaStatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(krishnaObj,HLCKrishnaStatelessRemoteHome.class);
            HLCKrishnaStatelessRemote calRemote = krishnaHome.create();
            
            Object objRole = jndiContext.lookup(mr.getMessage("jndi.rolemanagement"));
            HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objRole,HLCBrahmaputraSessionRemoteHome.class);
            HLCBrahmaputraSessionRemote roleRemote = roleHome.create();
            
            Object kObj = jndiContext.lookup(mr.getMessage("jndi.mqm"));
            HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(kObj,HLCMembershipQualificationMatrixRemoteHome.class);
            HLCMembershipQualificationMatrixRemote qremote = qhome.create();
            
            String cmd = request.getParameter("cmd");
            HttpSession session = request.getSession(true);
            
            String memberId = (String) session.getAttribute("searchMemId");
            String fName = (String) session.getAttribute("fname");
            String lName = (String) session.getAttribute("lname");
            String email = (String) session.getAttribute("email");
            String zip = (String) session.getAttribute("zip");
            String loginName = (String) session.getAttribute("login_Name");
            
            session.setAttribute("sessionId",null);
            session.setAttribute("userId",null);
            session.setAttribute("userCode",null);
            session.setAttribute("firstName",null);
            session.setAttribute("userTypeName",null);
            session.setAttribute("emailId",null);
            session.setAttribute("memberId",null);
            session.setAttribute("periodValue",null);
            session.setAttribute("userTypeId",null);
            
            if(cmd!=null && cmd.equalsIgnoreCase("initLogin")){
                String eventId = request.getParameter("eventTypeId");
                String compYear = request.getParameter("compYear");
                request.setAttribute("compYear",compYear);
                request.setAttribute("eventTypeId",eventId);
                request.setAttribute("status","fromEE");
                return mapping.findForward("toLogin");
            }else if(cmd!=null && cmd.equalsIgnoreCase("checkLogin")){
                System.out.println("Inside Login Process:::");
                String eventTypeId = request.getParameter("eventTypeId");
                String compYear = request.getParameter("compYear");
                request.setAttribute("compYear",compYear);
                String login = request.getParameter("textfield");
                String pass = request.getParameter("textfield2");
                if(login != null && login.trim().length() != 0 && pass != null && pass.trim().length() != 0)
                    vObj = remote.getLoginStatus(login, pass);
                if(vObj != null && vObj.size() != 0) {
                    String[] logdet=null;
                    
                    logdet=(String[]) vObj.elementAt(0);
                    if(logdet[0].equalsIgnoreCase("true")) {
                        
                        status="success";
                        request.setAttribute("status",status);
                        
                        session.setAttribute("sessionId",session.getId());
                        session.setAttribute("userId",logdet[1]);
                        session.setAttribute("userCode",logdet[2]);
                        session.setAttribute("firstName",logdet[3]);
                        session.setAttribute("userTypeName",logdet[4]);
                        session.setAttribute("emailId",logdet[5]);
                        session.setAttribute("memberId",logdet[6]);
                        session.setAttribute("loginName",logdet[7]);
                        session.setAttribute("lastName",logdet[8]);
                        session.setAttribute("phoneNo",logdet[9]);
                        session.setAttribute("periodValue",logdet[10]);
                        session.setAttribute("userTypeId",logdet[11]);
                        
                        session.setAttribute("fname",fName);
                        session.setAttribute("lname",lName);
                        session.setAttribute("email",email);
                        session.setAttribute("zip",zip);
                        session.setAttribute("login_Name",loginName);
                        session.setAttribute("searchMemId",memberId);
                        
                        String msgCount = String.valueOf(msgRemote.totalMessageCount(logdet[1].trim()));
                        session.setAttribute("msgCount",msgCount);
                        boolean updateStat=remote.updateLoginDate(logdet[1]);
                        if(updateStat){
                            try {
                                Debug.print("ActionRoleMangement.initDashboard()");
                                String userIdVal = (String)session.getAttribute("userId");
                                ArrayList roleList = new ArrayList();
                                ArrayList entityList = new ArrayList();
                                ArrayList generalPrivList = new ArrayList();
                                ArrayList genealRoleList = new ArrayList();
                                ArrayList privilegeList = new ArrayList();
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
                                        if(roleId != null && roleId.trim().length() != 0 && entityId != null && entityId.trim().length() != 0)
                                            tempPrivList = roleRemote.getMyPrivilegesListFromEntity(roleId, entityId);
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
                                        String strRolelist[] = (String[])(String[])itEntList.next();
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
                                    privilegeList = roleRemote.getMyPrivilegesListForHeader(roleId, entityId);
                                session.setAttribute("DBPrivlegeList", null);
                                session.setAttribute("DBPrivlegeList", privilegeList);
                            } catch(Exception eDisp) {
                                Debug.print("while calling initalDashboard:" + eDisp);
                            }
                            Debug.print("ActionRoleMangement.initDashboard() sucessfully comes from servlet.");
                            request.setAttribute("eventTypeId",eventTypeId);
                            return mapping.findForward("loginSuccess");
                        }else{
                            request.setAttribute("eventTypeId",eventTypeId);
                            request.setAttribute("status","fail");
                            return mapping.findForward("toLogin");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
