/*
 * LogoutAction.java
 *
 * Created on September 15, 2006, 10:51 PM
 */

package com.mrm.action;

import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import com.hlcmrm.util.Debug;
import com.hlcrole.management.*;
import java.util.*;
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
import com.hlcform.stateless.*;
import java.text.SimpleDateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.File;

/**
 *
 * @author suresh
 * @version
 */

public class LogoutAction extends Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
        
        HttpSession session = request.getSession(true);
        String status=null;
        
        MessageResources mr=getResources(request);
        
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.messages");
        String str=mr.getMessage("applicationconfig.file.path");
       
        Context jndiContext = new InitialContext();
        jndiContext = getInitialContext();
        Object obj=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
        HLCkaverystatelessRemoteHome home =
        (HLCkaverystatelessRemoteHome) PortableRemoteObject.narrow(obj, HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Object objref = jndiContext.lookup(jndiname);
                
        HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote msgRemote = msgHome.create();

       
        String jndiname3=mr.getMessage("jndi.rolemanagement");
        Object objref2 = jndiContext.lookup(jndiname3); 
        HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref2,HLCBrahmaputraSessionRemoteHome.class);
        HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

         //Dhivya Here===================
         HttpSession session1 = request.getSession(true);
        String adminUserProcess = request.getParameter("adminUserProcess");
        
        

if(adminUserProcess!=null){
         if(adminUserProcess.equals("adminUserLogOut")){
             Debug.print("Inside adminUserLogOut");
        //session=session1;
 ArrayList memberDetails = null;
   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   
         String sessionId=(String)session1.getAttribute("sessionId");
         String adminUserSessId=(String)session1.getAttribute("adminUserId");
          Debug.print("Inside adminUserLogOut adminUserSessId"+adminUserSessId);
         String MemberSessId = (String) session1.getAttribute("searchMemId");
            String fSessName = (String) session1.getAttribute("fname");
            String lSessName = (String) session1.getAttribute("lname");
            String emailSess = (String) session1.getAttribute("email");
            String zipSess = (String) session1.getAttribute("zip");
            String loginSessName = (String) session1.getAttribute("login_Name");
            String frmSessDate = (String) session1.getAttribute("fromDate");
            String tSessDate = (String) session1.getAttribute("toDate");
            String rolSessId = (String) session1.getAttribute("roleIds");
            String radSessMem = (String) session1.getAttribute("radMem");
            String titleSessName = (String) session1.getAttribute("title");
             ArrayList DBLeftSessPrivlegeList = (ArrayList) session1.getAttribute("DBLeftPrivlegeList");

                Date fromDate = null;
        if (frmSessDate != null && frmSessDate.trim().length() != 0) {
            fromDate = (Date) sdf.parse(frmSessDate);
            Debug.print("fromDate in servlet" + fromDate);
        }
              Date toDate = null;
        if (tSessDate != null && tSessDate.trim().length() != 0) {
            toDate = (Date) sdf.parse(tSessDate);
            Debug.print("toDate in servlet" + toDate);
        }

            Vector vObj = new Vector();
            vObj = remote.getLoginStatusByUserId(adminUserSessId);
            Debug.print("remote.getLoginStatusByUserId(adminUserId) - vObj.size() :"+vObj.size());

            //session.setAttribute("loggedBy","Admin");
            //session.setAttribute("adminUserId",userId);
            //Debug.print("adminUserId before logging as user :"+userId);

            if(vObj != null && vObj.size() != 0) {
                 String[] logdet=null;

                 logdet=(String[]) vObj.elementAt(0);
                 if(logdet[0].equalsIgnoreCase("true")) {

                    status="success";
                    request.setAttribute("status",status);

               // String sessionId =(String) session.getAttribute("sessionId");

//if(sessionId==null){
 //HttpSession session1 = request.getSession(true);

 //session=session1;



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

                    session.setAttribute("fname",fSessName);
                    session.setAttribute("lname",lSessName);
                    session.setAttribute("email",emailSess);
                    session.setAttribute("zip",zipSess);
                    session.setAttribute("login_Name",loginSessName);
                    session.setAttribute("fromDate",frmSessDate);
                    session.setAttribute("toDate",tSessDate);
                    session.setAttribute("rolesId",rolSessId);
                    session.setAttribute("searchMemId",MemberSessId);
                    session.setAttribute("DBLeftPrivlegeList",DBLeftSessPrivlegeList);
                     session.setAttribute("radMem",radSessMem);
 session.setAttribute("title",titleSessName);



                    System.out.println(logdet[0]+" "+logdet[1]+" "+logdet[2]+" "+logdet[3]+" "+logdet[4]+" "+logdet[5]+" "+logdet[6]+" "+logdet[7]+" "+logdet[8]+" "+logdet[9] + " " + logdet[10] + " " + logdet[11]);

                    //return mapping.findForward("LoginSuccess");
                    String msgCount = String.valueOf(msgRemote.totalMessageCount(logdet[1].trim()));
                    session.setAttribute("msgCount",msgCount);

                    boolean updateStat=remote.updateLoginDate(logdet[1]);
                    Debug.print("remote.updateLoginDate() in servlet :"+updateStat);

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



      session.setAttribute("title",titleSessName);

                    /*
                     * Reload search result page
                     */

                    Debug.print("Restoring Human member search status ....");

                    if(MemberSessId!=null && MemberSessId.trim().length()!=0){
                        Debug.print("SearchList Action Found MemberSearch");
                        memberDetails = remote.searchUserByMemberId(MemberSessId);
                    } else{

                        if((fSessName!=null && fSessName.trim().length()!=0) || (lSessName!=null && lSessName.trim().length()!=0)
                        || (emailSess!=null && emailSess.trim().length()!=0) || (zipSess!=null && zipSess.trim().length()!=0) || (fromDate!=null) || (toDate!=null) || (rolSessId!=null && rolSessId.trim().length()!=0)){
                            memberDetails = (ArrayList)remote.searchUserByGeneral(fSessName, lSessName, emailSess, zipSess,fromDate,toDate,rolSessId,radSessMem);
                        } else if(loginSessName!=null && loginSessName.trim().length()!=0) {
                            Debug.print("SearchList Action Found LoginSearch");
                            memberDetails = (ArrayList)remote.searchUserByLoginName(loginSessName,radSessMem);
                        }
                    }
                    request.setAttribute("memberDetails",memberDetails);
                     Debug.print("memberDetails in logoutAction"+memberDetails.size());
                     // }
                      session.removeAttribute("adminUserId");
                    return mapping.findForward("frmMemberSearchResultList");

                   // return mapping.findForward("callMainBoard");

                 }
                 else  {
                     status="fail";
                     request.setAttribute("status",status);
                     session.removeAttribute("adminUserId");
                     return mapping.findForward("ReLogin");
                 }
             }
             else{
                 status="fail";
                 request.setAttribute("status",status);
                  session.removeAttribute("adminUserId");
                 return mapping.findForward("ReLogin");
             }


    }
        
        }else{
             String adminUserId=(String)session.getAttribute("adminUserId");
        Debug.print("adminUserId while logout :"+adminUserId);
                       
        if(adminUserId!=null)
        {

            Debug.print("Admin Logout Process !!");

            
            String memberId = (String) session.getAttribute("searchMemId");
            String fName = (String) session.getAttribute("fname");
            String lName = (String) session.getAttribute("lname");
            String email = (String) session.getAttribute("email");
            String zip = (String) session.getAttribute("zip");
            String loginName = (String) session.getAttribute("login_Name");
            String frmDate = (String) session.getAttribute("fromDate");
            String tDate = (String) session.getAttribute("toDate");
            String rolId = (String) session.getAttribute("roleIds");
            String radMem = (String) session.getAttribute("radMem");
            String titleName = (String) session.getAttribute("title");

          


            ArrayList DBLeftPrivlegeList = new ArrayList();
            DBLeftPrivlegeList=(ArrayList)session.getAttribute("DBLeftPrivlegeListTemp");
            
          /* Debug.print("memberId in logoutAction"+memberId);
           Debug.print("fName in logoutAction"+fName);
           Debug.print("lName in logoutAction"+lName);
           Debug.print("email in logoutAction"+email);
           Debug.print("zip in logoutAction"+zip);
           Debug.print("loginName in logoutAction"+loginName);
           Debug.print("frmDate in logoutAction"+frmDate);
           Debug.print("tDate in logoutAction"+tDate);
           Debug.print("rolId in logoutAction"+rolId);
           Debug.print("radMem in logoutAction"+radMem);*/

            session1.setAttribute("sessionId",session1.getId());
            session1.setAttribute("adminUserId",adminUserId);
             Debug.print("Admin Logout Process adminUserId !!"+(String)session1.getAttribute("adminUserId"));

            session1.setAttribute("fname",fName);
            session1.setAttribute("lname",lName);
            session1.setAttribute("email",email);
            session1.setAttribute("zip",zip);
            session1.setAttribute("login_Name",loginName);
            session1.setAttribute("fromDate",frmDate);
            session1.setAttribute("toDate",tDate);
            session1.setAttribute("rolesId",rolId);
            session1.setAttribute("searchMemId",memberId);
            session1.setAttribute("radMem",radMem);
            session1.setAttribute("DBLeftPrivlegeList",DBLeftPrivlegeList);
            session1.setAttribute("title",titleName);


            session.removeAttribute("sessionId");
            session.removeAttribute("userId");
            session.removeAttribute("userCode");
            session.removeAttribute("firstName");
            session.removeAttribute("userTypeName");
            session.removeAttribute("emailId");
            session.removeAttribute("memberId");
            //session.invalidate();

Debug.print("Admin Logout Process adminUserId !!"+(String)session1.getAttribute("adminUserId"));
           return mapping.findForward("bckAdminProcess");

        } else
        {

            Debug.print("Normal User Logout Process !!");

            session.removeAttribute("sessionId");
            session.removeAttribute("userId");
            session.removeAttribute("userCode");
            session.removeAttribute("firstName");
            session.removeAttribute("userTypeName");
            session.removeAttribute("emailId");
            session.removeAttribute("memberId");
            session.invalidate();
            
            System.out.println("Sucessfully Logout");
            return mapping.findForward(SUCCESS);
        }
        }
        
        
       return null;
    }
    //===========Ends Here==================================
    public static Context getInitialContext() throws javax.naming.NamingException {
                Properties p =new Properties();
                p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
                p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
                p.setProperty( "java.naming.provider.url", "localhost:11199" );
                return new javax.naming.InitialContext(p);
              }
}
