/*
 * UsrSignupAjaxAction.java
 *
 * Created on November 3, 2006, 4:21 PM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmeeting.util.Debug;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hlcform.util.HLCUserMaster;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author karthikeyan
 * @version
 */

public class UsrSignupAjaxAction extends Action {
    
   
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
        
        try
        {
            
          //set the content type 
        response.setContentType("text/xml"); 
         
        response.setHeader("Cache-Control", "no-cache"); 
        
        //get the PrintWriter object to write the html page 
        PrintWriter writer = response.getWriter(); 
        
        MessageResources mr=getResources(request);
        
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.usrreg");
     
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        
        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        
       
        
        HLCUserMaster objUserMaster = new HLCUserMaster();
        
        String process=request.getParameter("process");
        
        /*
         * Checking weather the user name already exist or not
         */
        
        if(process.equalsIgnoreCase("checkusrnam"))
        {
                
            String usrId=request.getParameter("chsUserName");
            System.out.println("request.getParameter(chsUserName) :"+usrId);

            boolean usrStat=remote.checkUserNameExist(usrId);

            Debug.print("remote.checkUserNameExist(usrId)"+usrStat);

            //get the member id existing or not status and respond 

            writer.println("<userstatus><![CDATA[" + usrStat + "]]></userstatus>"); 
        
        }
        /*
         * Checking whether the EmployeeId already exist or not
         */
        if(process.equalsIgnoreCase("checkEmpId"))
        {
                
            String empId=request.getParameter("chkEmpId");
            String userName="";
            String pwd="";
            String email="";
            String employeeId="";
            System.out.println("request.getParameter(empId) :"+empId);

            boolean empStat=remote.checkEmpIdExist(empId);

            Debug.print("remote.checkEmpId(empId)"+empStat);

           // writer.println("<empStatus><![CDATA[" + empStat + "]]></empStatus>"); 
           // if(empStat==false)
           // {
            	  ArrayList empDetails=remote.getEmpInfBasedOnId(empId);
                  if(empDetails!=null)
                  {
                            Iterator it=empDetails.iterator();
                          	while(it.hasNext()){
      					String[] scnt = (String[])it.next();
      					employeeId = scnt[0];
      					userName = scnt[1];
      					pwd = scnt[2];	
      					email = scnt[3];	
                  }
                  }
                  

                  Debug.print("remote.getEmp)"+userName+pwd+email+employeeId);

                  //get the member id existing or not status and respond 

                  //writer.println("<userName><![CDATA[" + userName + "]]><![CDATA[" + pwd + "]]><![CDATA[" + email + "]]></userName>"); 
          //  }
            
            writer.println("<empDet><![CDATA[" + empStat + "]]><![CDATA[" + employeeId + "]]><![CDATA[" + userName + "]]><![CDATA[" + pwd + "]]><![CDATA[" + email + "]]></empDet>");
        }
        /*
         * Getting Employee Info based on EmpId by Chennai Team
         */
        
        if(process.equalsIgnoreCase("getEmp"))
        {
                
            String empId=request.getParameter("empId");
            String userName="";
            String pwd="";
            String email="";
            System.out.println("request.getParameter(empId) :"+empId);

            ArrayList empDetails=remote.getEmpInfBasedOnId(empId);
            if(empDetails!=null)
            {
                      Iterator it=empDetails.iterator();
                    	while(it.hasNext()){
					String[] scnt = (String[])it.next();
					userName = scnt[0];
					pwd = scnt[1];	
					email = scnt[2];	
            }
            }
            

            Debug.print("remote.getEmp)"+userName+pwd+email);

            //get the member id existing or not status and respond 

            writer.println("<userName><![CDATA[" + userName + "]]><![CDATA[" + pwd + "]]><![CDATA[" + email + "]]></userName>"); 
            //writer.println("<password><![CDATA[" + pwd + "]]></password>");
            //writer.println("<email><![CDATA[" + email + "]]></email>");
        
        }
        
        /*
         * Checking weather the user code already exist or not
         */
        
        if(process.equalsIgnoreCase("chkUsrCode"))
        {
            Debug.print("chkUsrCode block in Ajax Servlet :");     
            String UserCode = request.getParameter("UserCode");
            Debug.print("request.getParameter(UserCode) :"+UserCode);

            boolean usrStat = remote.checkUserCodeExist(UserCode);

            Debug.print("remote.checkUserCodeExist(UserCode)"+usrStat);
            
            String codeStat = "NA";
            //get the member id existing or not status and respond 
            
            if(usrStat)
            {
                codeStat = "exists";
            }
            Debug.print(" codeStat :"+codeStat);
            
            writer.println("<userCode><![CDATA["+codeStat+"]]></userCode>"); 
        
        }
        
        /*
         * getting the secret question for the particular user
         */
        
        if(process.equalsIgnoreCase("secques"))
        {
        
            Debug.print(":::::::: inside secret question block in ajax action ::::::::");
            
            String usrId=request.getParameter("usrId");
            System.out.println("request.getParameter(usrId) :"+usrId);

            String secqus=remote.getSecretQuestion(usrId);

            Debug.print("remote.getSecretQuestion(usrId) :"+secqus);

            //get the member id existing or not status and respond 

            writer.println("<secqus><![CDATA[" + secqus + "]]></secqus>"); 
            
                        
        }
        
        if(process.equalsIgnoreCase("sQaonLogName"))
        {
        
            Debug.print(":::::::: inside secret question block in ajax action ::::::::");
            
            String usrName=request.getParameter("usrName");
            System.out.println("request.getParameter(usrName) :"+usrName);

            String secqus=remote.getSecretQuestionByLoginName(usrName);

            Debug.print("remote.getSecretQuestionByLoginName(usrName) :"+secqus);

            //get the member id existing or not status and respond 

            writer.println("<secqus><![CDATA[" + secqus + "]]></secqus>"); 
            
            if(secqus == null)
            {
            
           /* if(secqus.trim().length() == 0 && )
            {*/
                
                Debug.print(" ::::::::::::::: inside secret q/a unavailable block of ajax servlet send pwd mail ::::::::" );
                String emailid=remote.getEmailByLoginName(usrName);
                 Debug.print("remote.getEmailByLoginName(usrName) :" +emailid);
                 
                 String password=remote.getPasswordByLoginName(usrName);
                    
                    Debug.print("remote.getPasswordByLoginName(usrname) :" +password);
                 
                /* =====================================
                 *
                 * Sending User Name E-mail
                 *
                 * ====================================*/
                    
                String toMailIds[] = {emailid};
                EmailContent email=new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("anandv@digiblitz.com");
                email.setSubject("Your Account Details");
                
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                        " </tr>"+
                        "  <tr>"+
                        "<td valign=\"top\">"+
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                        "<tr>"+
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                        "</tr>"+
                        "<tr>"+
                        "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                        "<span class=\"boldTxt\">Dear user,</span>,<br /><br />"+
                        "<p>Please save this email for your records. Your account information is as follows:.<p>"+
                        "<p> --------------------- <p>"+
                       
                        "<p> Password :"+password+"<p>"+
                        "<p> --------------------- <p>"+
                        "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                        "Thank You <br />"+
                        "------------------ <br />"+
                        "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                        "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        " </table>"+
                        "</td></tr>"+
                        "+<tr>"+
                        "<td valign=\"top\" style=\"padding:10px;\">"+
                        "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                        "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                        "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                        "<ul>"+
                        "<li>Unlimited shopping online.</li>"+
                        "<li>Place advertisements online and/or on-site.</li>"+
                        "<li>Sponsor competitions held by HLC.</li>"+
                        "</ul>"+
                        
                        
                        "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                        "per your �Role� assigned:"+
                        
                        "<ul>"+
                        "<li>Compete in Equestrian Events held by HLC.</li>"+
                        "<li>Take part in other events like; Annual Meetings, Educational events,"+
                        "Activity Meetings held by HLC etc.</li>"+
                        "<li>Send Messages to other members.</li>"+
                        "<li>Create your own Distribution Lists.</li>"+
                        "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                        " <li>Unlimited Shopping online.</li>"+
                        " <li>Place advertisements online and/or on-site.</li>"+
                        " <li>Sponsor competitions held by HLC.</li>"+
                        "</ul>"+
                        
                        "and much more..."+
                        "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                        "</tr>"+
                        " <tr>"+
                        "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                        "</tr>"+
                        "</table>";
                
                
                email.setBody(content);
                //email.setAttachments();
                
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :"+emailFlag);
            }
            else if(secqus.trim().length() == 0)
            {
                Debug.print(" ::::::::::::::: inside secret q/a unavailable block of ajax servlet send pwd mail ::::::::" );
                String emailid=remote.getEmailByLoginName(usrName);
                 Debug.print("remote.getEmailByLoginName(usrName) :" +emailid);
                 
                 String password=remote.getPasswordByLoginName(usrName);
                    
                    Debug.print("remote.getPasswordByLoginName(usrname) :" +password);
                 
                /* =====================================
                 *
                 * Sending User Name E-mail
                 *
                 * ====================================*/
                    
                String toMailIds[] = {emailid};
                EmailContent email=new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("anandv@digiblitz.com");
                email.setSubject("Your Account Details");
                
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                        " </tr>"+
                        "  <tr>"+
                        "<td valign=\"top\">"+
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                        "<tr>"+
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                        "</tr>"+
                        "<tr>"+
                        "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                        "<span class=\"boldTxt\">Dear user,</span>,<br /><br />"+
                        "<p>Please save this email for your records. Your account information is as follows:.<p>"+
                        "<p> --------------------- <p>"+
                       
                        "<p> Password :"+password+"<p>"+
                        "<p> --------------------- <p>"+
                        "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                        "Thank You <br />"+
                        "------------------ <br />"+
                        "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                        "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        " </table>"+
                        "</td></tr>"+
                        "+<tr>"+
                        "<td valign=\"top\" style=\"padding:10px;\">"+
                        "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                        "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                        "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                        "<ul>"+
                        "<li>Unlimited shopping online.</li>"+
                        "<li>Place advertisements online and/or on-site.</li>"+
                        "<li>Sponsor competitions held by HLC.</li>"+
                        "</ul>"+
                        
                        
                        "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                        "per your �Role� assigned:"+
                        
                        "<ul>"+
                        "<li>Compete in Equestrian Events held by HLC.</li>"+
                        "<li>Take part in other events like; Annual Meetings, Educational events,"+
                        "Activity Meetings held by HLC etc.</li>"+
                        "<li>Send Messages to other members.</li>"+
                        "<li>Create your own Distribution Lists.</li>"+
                        "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                        " <li>Unlimited Shopping online.</li>"+
                        " <li>Place advertisements online and/or on-site.</li>"+
                        " <li>Sponsor competitions held by HLC.</li>"+
                        "</ul>"+
                        
                        "and much more..."+
                        "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                        "</tr>"+
                        " <tr>"+
                        "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                        "</tr>"+
                        "</table>";
                
                
                email.setBody(content);
                //email.setAttachments();
                
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :"+emailFlag);
                                                   
        }
            
        }
        //close the write 
        writer.close();   
    }
        
          catch(Exception e)
         {
             System.out.println(e);
         }
        
        return null;
    }
}
