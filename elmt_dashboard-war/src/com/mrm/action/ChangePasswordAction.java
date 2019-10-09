/*
 * ChangePasswordAction.java
 *
 * Created on October 16, 2006, 7:23 PM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmrm.util.Debug;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import javax.naming.Context;
import javax.naming.InitialContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import org.jasypt.util.text.BasicTextEncryptor;
/**
 *
 * @author karthikeyan
 * @version
 */

public class ChangePasswordAction extends Action {
    
     
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
        
        String fwd="";
        
        try
        {
            
        
        String process=request.getParameter("process");
        
        if(process!=null)
        {
               
        if(process.equalsIgnoreCase("disp"))
        {
            fwd="changePwdDisp";
        }
        
         if(process.equalsIgnoreCase("Change"))
        {
                    
        MessageResources mr=getResources(request);
        HttpSession session=request.getSession();
        
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
        
        String userId=(String)session.getAttribute("userId");
        //String currPwd=request.getParameter("currPwd");
        //String chPass=request.getParameter("reNewPwd");
        String chPass=(String)session.getAttribute("confPwd");
        
        Debug.print("user id from session :" +userId);
        //Debug.print("current pwd :" +currPwd);
        Debug.print("changed password :" +chPass);
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("sa");
        boolean status = remote.changePassword(userId,textEncryptor.encrypt(chPass));
        
        Debug.print("changePassword status :" +status);
        
        if(status==true)
            
        {
            
            /* =====================================
                 *
                 * Sending confirmation E-mail
                 *
                 * ====================================*/
                String emailid=request.getParameter("email");
                String toMailIds[] = {emailid};
                EmailContent email=new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("anandv@digiblitz.com");
                email.setSubject("Your Account Info !");
                
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"https://dashboard.useventing.com/dashboard/images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                        " </tr>"+
                        "  <tr>"+
                        "<td valign=\"top\">"+
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                        "<tr>"+
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"https://dashboard.useventing.com/dashboard/images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"https://dashboard.useventing.com/dashboard/images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                        "</tr>"+
                        "<tr>"+
                        "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/left.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                        "<span class=\"boldTxt\">Dear User"+"</span>,<br /><br />"+
                        "<p>Please save this email for your records. Your Password Change Request is Succesful !! Your account information is as follows:.<p>"+"<p>----------------------------<p>"+"<p> New Password : "+chPass+"<p> ----------------------------<p>"+
                        /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                        "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
                        "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                        "Thank You <br />"+
                        "------------------ <br />"+
                        "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                        "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/Rght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        "<tr><td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBotLeft.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBot.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"https://dashboard.useventing.com/dashboard/images/cornerBotRght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        " </table>"+
                        "</td></tr>"+
                        "+<tr>"+
                        "<td valign=\"top\" style=\"padding:10px;\">"+
                        "<img src=\"http://dashboard.useventing.com/dashboard/images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
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
                        "So go ahead and <a href=\"http://dashboard.useventing.com\">LOGIN NOW!</a></td>"+
                        "</tr>"+
                        " <tr>"+
                        "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"http://dashboard.useventing.com/dashboard/images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                        "</tr>"+
                        "</table>";
                
                
                email.setBody(content);
                //email.setAttachments();
                
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :"+emailFlag);
            
            fwd="changeSuccess";
            
        }
        
        else
        {
            String stat="fail";
            request.setAttribute("status",stat);
            fwd="changeSuccess";
         }
        
         }
        }
        }
        
         catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        return mapping.findForward(fwd);
        
    }
}
