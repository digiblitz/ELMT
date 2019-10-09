/*
 * ForgotPasswordAction.java
 *
 * Created on October 16, 2006, 3:16 PM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmrm.util.Debug;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.io.File;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import org.jasypt.util.text.BasicTextEncryptor;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 *
 * @author karthikeyan
 * @version
 */

public class ForgotPasswordAction extends Action {
    
       
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
        
        String process=request.getParameter("process");
        String fwd="";
        
        MessageResources mr=getResources(request);
         HttpSession session=request.getSession(true); 
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.usrreg");
        String jboss=mr.getMessage("jboss.path");
        
        System.out.println("Application Resourece PAth get :::::"+jboss);
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        
        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
        
        HttpServlet ser = getServlet();
        ServletContext context=ser.getServletContext();
        System.out.println("after ser.getServletContext() .....");
        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          
            Document doc = docBuilder.parse (new File(jboss+"association-config.xml"));

            // normalize text representation
            doc.getDocumentElement ().normalize ();
            System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


            NodeList listOfPersons = doc.getElementsByTagName("first");
            int totalPersons = listOfPersons.getLength();
            System.out.println("Total no of element : " + totalPersons);

            for(int s=0; s<listOfPersons.getLength() ; s++){


                Node firstPersonNode = listOfPersons.item(s);
                if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){


                    Element firstPersonElement = (Element)firstPersonNode;

                    //-------
                    NodeList firstNameList = firstPersonElement.getElementsByTagName("title");
                    Element firstNameElement = (Element)firstNameList.item(0);

                    NodeList textFNList = firstNameElement.getChildNodes();
                    System.out.println("Title Name : " + ((Node)textFNList.item(0)).getNodeValue().trim());
                     String dd=((Node)textFNList.item(0)).getNodeValue().trim();
                     System.out.println("node get "+dd);
                     String titleName=((Node)textFNList.item(0)).getNodeValue().trim();
                     session.setAttribute("title1",titleName);
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


                }


            }


        }catch (SAXParseException err) {
        System.out.println ("** Parsing error" + ", line " 
             + err.getLineNumber () + ", uri " + err.getSystemId ());
        System.out.println(" " + err.getMessage ());

        }catch (SAXException e) {
        Exception x = e.getException ();
        ((x == null) ? e : x).printStackTrace ();

        }catch (Throwable t) {
        t.printStackTrace ();
        }
        if(process!=null)
        {
            if(process.equalsIgnoreCase("view"))
            {               
                fwd="forgetPwdDisp";
            }
            
            if(process.equalsIgnoreCase("Password"))
            {
                Debug.print("inside forget user name block ...........");
                String usrname=request.getParameter("usrname");
                Debug.print("usrname :" +usrname);
               
                //String[] forgetusr=remote.getEmailOnEmail(usrname);
                 ArrayList det=new ArrayList();
                 det=remote.getLoginDetailsByEmailId(usrname);
                /* Debug.print("usrname from db :" +forgetusr[0]);
                 Debug.print("pwd from db :" +forgetusr[1]);
                 Debug.print("status from db :" +forgetusr[2]);*/
                 
                if(det.size() == 1){
                     for(int i=0;i<det.size();i++){
                        String lname=(String)det.get(i);
                        if(lname==null){
                            Debug.print("Login Name is "+lname+"     Redirected to user sign up page ");
                            request.setAttribute("loginName","notFound");
                            return mapping.findForward("nologinName");
                        }
                     }
                }
                 
                if(det!=null)
                {                     
                
                if(det.size()!=0)
                {                   
                    String username="";              
                    Debug.print("det.size() :"+det.size());
                          
                    for(int i=0;i<det.size();i++)
                    {
                            String lname=(String)det.get(i);
                            if(lname!=null)
                            {
                                username=username+lname+",";
                            }
                            
                            Debug.print("usrname's fetched :"+lname);
                            Debug.print("usrname's consolidated :"+username);
                    }
                    
                /* =====================================
                 *
                 * Sending Password E-mail
                 *
                 * ====================================*/
                    
                String toMailIds[] = {usrname};
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
                                                                           
                        "<p> User Name : "+username+"<p>"+                    
                                              
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
                   
                fwd="forgetPwdConf";
                
                }
                else
                {
                    String stat="fail";
                    request.setAttribute("status",stat);
                    
                    fwd="forgetPwdDisp";
                    
                }
                }
               
            }
            
             if(process.equalsIgnoreCase("User"))
            {    
                
                Debug.print("Inside forget pwd block ..........");
                String secretqus=request.getParameter("sAnswer2");
                String sAnswer=request.getParameter("sAnswer");
                String usrname=request.getParameter("usrname");
                
                /*String birthmonth=request.getParameter("birthmonth");
                String birthday=request.getParameter("birthday");
                String birthyear=request.getParameter("birthyear");
                String dob= birthmonth+"/"+birthday+"/"+birthyear;*/
                
                 
                Debug.print("usrname :" +usrname);
                Debug.print("sAnswer :" +secretqus);
                Debug.print("secretqus :" +sAnswer);
                                 
                 context.setAttribute("usrname",usrname);
                
                boolean status=remote.checkSecurityQuestionByLoginName(usrname,secretqus,sAnswer);
                
                Debug.print("remote.checkSecurityQuestionByLoginName(usrname,secretqus,sAnswer2) :" +status);
                
               /* String[] forgetpwd=remote.getEmailAndPassword(dob,secretqus,sAnswer);
                Debug.print("db status :" +forgetpwd[2]);
                 
                Debug.print("forgetpwd :" +forgetpwd.length +forgetpwd);*/
                                              
                if(status == false)
                {
                    
                    Debug.print(" ::::::::::::::: inside secret q/a fail block send pwd mail ::::::::" );
                    String emailid=remote.getEmailByLoginName(usrname);
                    Debug.print("remote.getEmailByLoginName(usrname) :" +emailid);
                    Debug.print("remote.getPasswordByLoginName(usrname) :" +usrname);
                    String password=remote.getPasswordByLoginName(usrname);
                   
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
                
                     fwd="forgetPwdConf";
                     
                }
                
                else
                {
                    /*String stat="fail";
                    
                    request.setAttribute("idstatus",stat);
                    
                    fwd="forgetPwdDisp";*/
                    
                    fwd="resetPwd";
                    
                }
                
             
            }
            
             if(process.equalsIgnoreCase("resetpw"))
            { 
                
                Debug.print("::::::::::::::::::: Inside reset pwd update block ::::::::::::");
                String usrname=(String)context.getAttribute("usrname");
                Debug.print("context.getAttribute(usrname) :"+usrname);
                
                String password=request.getParameter("password");
                Debug.print("request.getParameter(password) :"+password);
                
                boolean stat=remote.updateLoginDetailsByLoginName(usrname,password);
                
                Debug.print("remote.updateLoginDetailsByLoginName(usrname,password) :"+stat);
                
                 String emailid=remote.getEmailByLoginName(usrname);
                 Debug.print("remote.getEmailByLoginName(usrname) :" +emailid);
                 
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
                       
                        "<p> Your Password has been changed !!<p>"+
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
                
                fwd="resetPwdConf";
                
            }
            
        }
        
        return mapping.findForward(fwd);
        
    }
}



