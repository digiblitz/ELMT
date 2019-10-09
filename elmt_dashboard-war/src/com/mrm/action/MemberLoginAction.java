/*
 * MemberLoginAction.java
 *
 * Created on September 7, 2006, 11:59 AM
 */

package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.rmi.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts.util.MessageResources;

import com.util.XMLParser;

//import org.w3c.dom.Document;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



/**
 *
 * @author karthikeyan
 * @version
 */

public class MemberLoginAction extends Action {
   
    String userId = null;
    Vector vObj = new Vector();
    String status=null;
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
                    MessageResources mr=getResources(request);

                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.messages");
                    String str=mr.getMessage("applicationconfig.file.path");
                    Debug.print("ActionMessage.jndiname:" + jndiname);
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname); 
                    Debug.print("ActionMessage.jndiname:" + jndiname);

                    HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
                    HLCMessageSessionRemote msgRemote = msgHome.create();
                    
                    jndiContext = getInitialContext();
                    Object obj=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
                    HLCkaverystatelessRemoteHome home =
                    (HLCkaverystatelessRemoteHome) PortableRemoteObject.narrow(obj, HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote = home.create();
                    int totalXMLUsers=0;int totalUsersAssignedRole=0;
                    
                    System.out.println("after create.............");
           
             String loginProcess = (String)request.getAttribute("loginProcess");
             String login,pass = "";
                     String userName="";
                     String userPassword="";
             String adminUsrId = null;
             
            vObj=null;
            HttpSession session=request.getSession(true); 
            adminUsrId =(String)session.getAttribute("userId");
            Debug.print("adminUsrId :"+adminUsrId);
            
            String memberId = (String) session.getAttribute("searchMemId");
            String fName = (String) session.getAttribute("fname");
            String lName = (String) session.getAttribute("lname");
            String email = (String) session.getAttribute("email");
            String zip = (String) session.getAttribute("zip");
            String loginName = (String) session.getAttribute("login_Name");
            String frmDate = (String) session.getAttribute("fromDate");
            String tDate = (String) session.getAttribute("toDate");
            String rolId = (String) session.getAttribute("rolesId");
            String radMem = (String) session.getAttribute("radMem");
                                     
            session.setAttribute("sessionId",null);  
            session.setAttribute("userId",null);
            session.setAttribute("userCode",null);
            session.setAttribute("firstName",null);
            session.setAttribute("userTypeName",null);
            session.setAttribute("emailId",null);
            session.setAttribute("memberId",null);
            session.setAttribute("periodValue",null);
            session.setAttribute("userTypeId",null);
             if(loginProcess != null && loginProcess.equals("adminByAdmin")) {
                  String license_file_path=mr.getMessage("license.file.path");
                String license_file_name=mr.getMessage("license.file.name");
                String fileEncryptedContent=XMLParser.readXMLCreated(license_file_path+"\\"+license_file_name);
                String original=XMLParser.decryptFileContent(fileEncryptedContent);
                boolean output=XMLParser.readXMLDecryptedContent(original);
                ArrayList uu=XMLParser.totalXMLUsers(original);
               String hh1=uu.get(0).toString();
               String hh2=uu.get(1).toString();
               String hh3=uu.get(2).toString();
               String hh4=uu.get(3).toString();
               request.setAttribute("nuser",hh1);
               request.setAttribute("sdate",hh2);
               request.setAttribute("edate",hh3);
               request.setAttribute("luser",hh4);
                //System.out.println("File content"+original);
                totalXMLUsers=XMLParser.readFromXMLUsers(original); // Reading licensed users from license file .
                try{
                totalUsersAssignedRole=remote.getUserCountBasedOnRole();
                System.out.println("Inside totalUsersAssignedRole...."+totalUsersAssignedRole);
                if(totalUsersAssignedRole==-1)
                {
                	System.out.println("Inside if block....");
                	return mapping.findForward("callErrorPage");
                }
                
                }
                catch (Exception e)
                {
                	System.out.println("Inside catch block....");
                }
               //System.out.println("XML users"+totalXMLUsers+"  DB users"+totalUsersAssignedRole);
               
                userId = (String)request.getAttribute("userId");
                Debug.print("   Admin Login Process userId:" + userId);
                if(userId != null && userId.trim().length() != 0){
                    vObj = remote.getLoginStatusByUserId(userId);
                    session.setAttribute("loggedBy","Admin");
                    session.setAttribute("adminUserId",adminUsrId);
                    
                    ArrayList DBLeftPrivlegeList = new ArrayList();
                    DBLeftPrivlegeList = (ArrayList) session.getAttribute("DBLeftPrivlegeList");
                    
                    session.setAttribute("DBLeftPrivlegeList",null);
                    session.setAttribute("DBLeftPrivlegeListTemp",DBLeftPrivlegeList);
                    
                    session.setAttribute("adminUserId",adminUsrId);
                    Debug.print("adminUserId before logging as user :"+adminUsrId);
                }
            } else {
                String license_file_path=mr.getMessage("license.file.path");
                String license_file_name=mr.getMessage("license.file.name");
                String fileEncryptedContent=XMLParser.readXMLCreated(license_file_path+"\\"+license_file_name);
                String original=XMLParser.decryptFileContent(fileEncryptedContent);
                //System.out.println("File content"+original);
                totalXMLUsers=XMLParser.readFromXMLUsers(original); // Reading licensed users from license file .
                totalUsersAssignedRole=remote.getUserCountBasedOnRole();
                System.out.println("befor if totalUsersAssignedRole...."+totalUsersAssignedRole);
                
                if(totalUsersAssignedRole==-1)
                {
                	System.out.println("Inside if block....");
                	return mapping.findForward("callErrorPage");
                }
                
               
               
                //System.out.println("XML users"+totalXMLUsers+"  DB users"+totalUsersAssignedRole);
                boolean output=XMLParser.readXMLDecryptedContent(original);
                ArrayList uu=XMLParser.totalXMLUsers(original);
               String hh1=uu.get(0).toString();
               String hh2=uu.get(1).toString();
               String hh3=uu.get(2).toString();
               String hh4=uu.get(3).toString();
               request.setAttribute("nuser",hh1);
               request.setAttribute("sdate",hh2);
               request.setAttribute("edate",hh3);
               request.setAttribute("luser",hh4);
        //Temporarily Commented  
             /*  if(output)
               {
                login = request.getParameter("textfield");
                pass = request.getParameter("textfield2");
                //String viewVal=request.getParameter("viewVal");
                Hashtable env = new Hashtable(11);
              //C:\Documents and Settings\safe\OpenDJ\bat\control-panel.bat
              boolean chkUser = false;
              if(login!=null && pass!=null){
              env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
              env.put(Context.PROVIDER_URL, "ldap://localhost:389");
              env.put(Context.SECURITY_AUTHENTICATION, "simple");
              env.put(Context.SECURITY_PRINCIPAL, "uid="+ login +",dc=example,dc=com");
              env.put(Context.SECURITY_CREDENTIALS, pass);

                  }
              try {
              // Create initial context
              DirContext ctx = new InitialDirContext(env);
              // Close the context when we're done
              chkUser = true;
              ctx.close();

              } catch (NamingException e) {
                  System.out.println("error mssg======"+e.getMessage());                  
                  chkUser = false;
                  status="fail";
                  request.setAttribute("status",status);
                  session.removeAttribute("adminUserId");
                  return mapping.findForward("ReLogin");  
              }finally{
              if(chkUser){
              System.out.print("Success");
              Debug.print("   Normal Login Process    Login Name:" + login);
              if(login != null && login.trim().length() != 0 && pass != null && pass.trim().length() != 0)
              vObj = remote.getLoginStatus(login, pass);              
              }
            
                
               }
               }*/
               
               if(output)
               {
                login = request.getParameter("textfield");
                pass = request.getParameter("textfield2");
                userName = request.getParameter("textfield");
                userPassword = request.getParameter("textfield2");
                //String viewVal=request.getParameter("viewVal");
                
                Debug.print("   Normal Login Process    Login Name:" + login);
               // Debug.print("   Normal Login Process    viewVal:" + viewVal);
                if(login != null && login.trim().length() != 0 && pass != null && pass.trim().length() != 0)
                    vObj = remote.getLoginStatus(login, pass);
               // session.setAttribute("viewVal", viewVal);
                
               }
               
               else
               {
                 status="expired";
                 request.setAttribute("status",status);
                 session.removeAttribute("adminUserId");
                 return mapping.findForward("ReLogin");   
               }
            }
              if(vObj != null && vObj.size() != 0) {
                 String[] logdet=null;

                 logdet=(String[]) vObj.elementAt(0);
                 if(logdet[0].equalsIgnoreCase("true")) {            
                   // counting first Roles count for this userID
                     int count=remote.getRolesCountForUser(logdet[1]);
                    Debug.print("Count For Roles for this User"+count);
                     if(count==0)
                          request.setAttribute("requestStatus","false");
                     else if(count>0)
                     {
                         if(totalUsersAssignedRole>totalXMLUsers)
                         {
                             //System.out.println("If equal");
                             status="users";
                             request.setAttribute("status",status);
                             //System.out.println("If equal");
                             session.removeAttribute("adminUserId");
                             return mapping.findForward("ReLogin");
                         }
                             
                         else if(totalUsersAssignedRole<=totalXMLUsers)
                         {
                           request.setAttribute("requestStatus","true");
                           //System.out.println("If less");
                         }
                     }  
                   
                    
                    
                    session.setAttribute("userCode",logdet[2]);
                    session.setAttribute("userName",userName);
                    session.setAttribute("userPassword",userPassword);
                    session.setAttribute("userTypeName",logdet[4]);
                    session.setAttribute("emailId",logdet[5]);
                    session.setAttribute("memberId",logdet[6]);
                    session.setAttribute("loginName",logdet[7]);
                    session.setAttribute("lastName",logdet[8]);
                    session.setAttribute("phoneNo",logdet[9]);
                    session.setAttribute("periodValue",logdet[10]);
                    session.setAttribute("userTypeId",logdet[11]);
                    String request_Status=logdet[12];
                    request.setAttribute("requestValue",request_Status);
                    session.setAttribute("fname",fName);
                    session.setAttribute("lname",lName);
                    session.setAttribute("email",email);
                    session.setAttribute("zip",zip);
                    session.setAttribute("login_Name",loginName);
                    session.setAttribute("searchMemId",memberId);
                    session.setAttribute("fromDate",frmDate);
                    session.setAttribute("toDate",tDate);
                    session.setAttribute("rolesId",rolId);
                    session.setAttribute("radMem",radMem);
                    System.out.println(logdet[0]+" "+logdet[1]+" "+logdet[2]+" "+logdet[3]+" "+logdet[4]+" "+logdet[5]+" "+logdet[6]+" "+logdet[7]+" "+logdet[8]+" "+logdet[9] + " " + logdet[10] + " " + logdet[11]);
                       //return mapping.findForward("LoginSuccess");
                    String msgCount = String.valueOf(msgRemote.totalMessageCount(logdet[1].trim()));
                    session.setAttribute("msgCount",msgCount);
                    
                    boolean updateStat=remote.updateLoginDate(logdet[1]);
                    Debug.print("remote.updateLoginDate() in servlet :"+updateStat);
                   
                      session.setAttribute("sessionId",session.getId()); 
                    session.setAttribute("userId",logdet[1]);
                    session.setAttribute("firstName",logdet[3]);
                    session.setAttribute("userName",userName);
                    session.setAttribute("userPassword",pass);
                    status="success";
                    request.setAttribute("status",status);
                     try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            //String str="C:/jboss-4.2.2.GA/server/default/deploy/";
            Document doc = docBuilder.parse (new File(str+"association-config.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


            NodeList listOfPersons = doc.getElementsByTagName("first");
            int totalPersons = listOfPersons.getLength();
             for(int s=0; s<listOfPersons.getLength() ; s++){


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("title");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("Title Name : " + ((Node)textFNList.item(0)).getNodeValue().trim());
                     String titleName=((Node)textFNList.item(0)).getNodeValue().trim();
                     session.setAttribute("title",titleName);
                    //-------
                   // NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
                   // Element lastNameElement = (Element)lastNameList.item(0);

                   // NodeList textLNList = lastNameElement.getChildNodes();
                   // System.out.println("Last Name : " + 
                         //  ((Node)textLNList.item(0)).getNodeValue().trim());

                    //----
                   // NodeList ageList = firstPersonElement.getElementsByTagName("age");
                   // Element ageElement = (Element)ageList.item(0);

                 //   NodeList textAgeList = ageElement.getChildNodes();
                   // System.out.println("Age : " + 
                        //   ((Node)textAgeList.item(0)).getNodeValue().trim());

                    //------


                }//end of if clause


            }//end of for loop with s var


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        	
        t.printStackTrace ();
        System.out.println("Inside catch block");
        request.setAttribute("status","conFailed");
        return mapping.findForward("ReLogin");
        }
                   
                    return mapping.findForward("callMainBoard");

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
              public static Context getInitialContext() throws javax.naming.NamingException {
                Properties p =new Properties();
                p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
                p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
                p.setProperty( "java.naming.provider.url", "localhost:11199" );
                return new javax.naming.InitialContext(p);
              }           
              
     
    
}
