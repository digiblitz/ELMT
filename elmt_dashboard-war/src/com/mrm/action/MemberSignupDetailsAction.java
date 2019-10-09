/*
 * MemberSignupDetailsAction.java
 *
 * Created on November 7, 2006, 10:43 AM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmsg.util.Debug;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hlcform.util.HLCUserMaster;
import com.vo.UsrSignUpVO;
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

public class MemberSignupDetailsAction extends Action {
    
    String fwd="";
  
    
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
        UsrSignUpVO usrVal=new UsrSignUpVO();
        
        String process=request.getParameter("process");
              
        HttpServlet ser = getServlet();
      
        ServletContext context=ser.getServletContext();
        System.out.println("after ser.getServletContext() .....");
        
         if(process == null)
        {
            fwd="NewMemSignDisp";
        }
        
         if(process!=null)
        {
                       
        if(process.equalsIgnoreCase("sign"))
        {        
        
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String birthmonth=request.getParameter("birthmonth");
        String birthday=request.getParameter("birthday");
        String birthyear=request.getParameter("birthyear");
        //String userName=request.getParameter("chsUserName");
        String membid=request.getParameter("membid");
        String email=request.getParameter("email");
        String zip=request.getParameter("zip");
        
        String dbirth=birthmonth+"/"+birthday+"/"+birthyear;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date dob=null;
        dob =(Date)sdf.parse(dbirth);
        
        Debug.print("fname :"+fname);
        Debug.print("lname :"+lname);
        Debug.print("birthmonth :"+birthmonth);
        Debug.print("birthday :"+birthday);
        Debug.print("birthyear :"+birthyear);
        Debug.print("membid :"+membid);
        Debug.print("email :"+email);
        Debug.print("zip :"+zip);
               
        //String[] usrVal={fname,lname,birthmonth,birthday,birthyear,membid,email,zip};
        
        usrVal.setFname(fname);
        usrVal.setLname(lname);
        usrVal.setBirthmonth(birthmonth);
        usrVal.setBirthday(birthday);
        usrVal.setBirthyear(birthyear);
        usrVal.setEmail(email);
        usrVal.setMembid(membid);
        usrVal.setZip(zip);
        
        System.out.println("dob :");
        System.out.println(dob);
          
        ArrayList usrDet=new ArrayList();
        usrDet=remote.checkUserExist(fname,lname,email,zip);
        
        context.setAttribute("usrVal",usrVal);
        
        if(usrDet!=null)
        {
            
            Debug.print("usrDet.size();"+usrDet.size());
                        
            for(int i=0;i<usrDet.size();i++)
            {
                objUserMaster=(com.hlcform.util.HLCUserMaster)usrDet.get(i);
                
                Debug.print("objUserMaster.getFirstName()"+objUserMaster.getFirstName());
                Debug.print("objUserMaster.getMiddleName()"+objUserMaster.getMiddleName());
                Debug.print("objUserMaster.getLastName()"+objUserMaster.getLastName());
                Debug.print("objUserMaster.getSecretQuestion()"+objUserMaster.getSecretQuestion());
                Debug.print("objUserMaster.getSecretAnswer()"+objUserMaster.getSecretAnswer());               
                
            }
            
            /*
             * Redirect to UserRegistration.jsp
             */
            
            if(usrDet.size() == 0)
            {                
                request.setAttribute("usrVal",usrVal);
                fwd="dsntExist";
            }
            
            /*
             * Redirect to UserSignupAlert.jsp
             */
            
            else
            {
                request.setAttribute("usrVal",usrVal);
                request.setAttribute("usrDet",usrDet);                
                fwd="usrDetailSecQA";
            }
            
        }
        }
     }
        /*
         * Secret Question validate 
         */           
        if(process!=null)
        {
                       
        if(process.equalsIgnoreCase("secQaVal"))
        {
            Debug.print("::::::::: Inside secQaValidate Block ::::::::");
            
            String usrId=request.getParameter("info_rad");
            Debug.print("request.getParameter(info_rad) :"+usrId);
            
            if(context.getAttribute("info_rad") != null)
            {
                usrId = (String)context.getAttribute("info_rad");
                Debug.print("context.getAttribute(info_rad) :"+usrId);
            }
            
            String secques=request.getParameter("secques");
            String answer=request.getParameter("answer");
            
              //String[] usrVal=(String[])context.getAttribute("usrVal");
            
              usrVal=(UsrSignUpVO)context.getAttribute("usrVal");
              
              if(context.getAttribute("userName") !=null)
              {
                String userName=(String)context.getAttribute("userName");
                context.setAttribute("userName",null);
                
                Debug.print("userName :" +userName);
                request.setAttribute("userName",userName);
              }
              
              Debug.print("context.getAttribute(usrVal) :"+usrVal.toString());
              
               if(context.getAttribute("mem_id") != null)
               {
                 String mem_id=(String)context.getAttribute("mem_id");
                 //String[] usrVal={fname,lname,birthmonth,birthday,birthyear,membid,email,zip};
                 //usrVal.[5]=mem_id;
                 usrVal.setMembid(mem_id);
                 Debug.print("context.getAttribute(mem_id) :"+mem_id);
               }
             
              Debug.print("usrVal.getMembid() :"+usrVal.getMembid());
              
              context.setAttribute("info_rad",null);
              context.setAttribute("mem_id",null);
              
             request.setAttribute("usrVal",usrVal);
             request.setAttribute("userid",usrId);
             context.setAttribute("userid",usrId);
             
             boolean stat=remote.isMemberExistBasedOnMemberAndUserId(usrVal.getMembid(),usrId);
             Debug.print("remote.isMemberExistBasedOnMemberAndUserId(usrVal.getMembid(),usrId) :"+stat);
             
             if(stat == true)
             {
                fwd="newMembReg";
                
             }
             else
             {
                fwd="dsntExist";
             }
             
             
           /*  Debug.print("request.getParameter(secques) :"+secques);
             Debug.print("request.getParameter(answer) :"+answer);
             
            boolean status=remote.checkSecurityQuestion(usrId,secques,answer); 
            Debug.print("remote.checkSecurityQuestion(usrId,secques,answer) :"+status);
            
            if(status == true)
            {
                Debug.print("Inside remote.checkSecurityQuestion(usrId,secques,answer) :"+status+"Redirect to login");           */
               
              /* String stat="Your answer is invalid !";
                request.setAttribute("stat",stat);*/
                
               /* String[] usrVal=(String[])context.getAttribute("usrVal");
               // String[] usrVal=(String[])request.getAttribute("usrVal");
             
                Debug.print("usrid :"+usrId);
                
                request.setAttribute("usrid",usrId);
                
                if(usrVal!=null)
                {Debug.print("Inside servlet ctx usrVal :"+usrVal.length);}
                else
                {Debug.print("Inside servlet ctx usrVal is empty");}
                
                //fwd="successLogin";
                
            }
            else
            {
                
                Debug.print("Inside remote.checkSecurityQuestion(usrId,secques,answer) :"+status+"Redirect to confirmation");
                
                /*String stat="Your answer is invalid !";
                request.setAttribute("stat",stat);
                
                String[] usrVal=(String[])context.getAttribute("usrVal");
               // String[] usrVal=(String[])request.getAttribute("usrVal");
             
                Debug.print("usrid :"+usrId);
                
                request.setAttribute("usrid",usrId);
                
                if(usrVal!=null)
                {Debug.print("Inside servlet ctx usrVal :"+usrVal.length);}
                else
                {Debug.print("Inside servlet ctx usrVal is empty");}*/
                
               /* fwd="secQaFail";
            }*/
            
            
        }
        
        /*
         * New Member Reg
         */
        
         if(process.equalsIgnoreCase("regMemb"))
            {
                Debug.print("Inside servlet Display New Member usrName & Pwd insert block....");
                String userid=request.getParameter("userid");
                
                Debug.print("Inside servlet New Member Display block userid :"+userid);
               
                //String[] usrVal=(String[])context.getAttribute("usrVal");
                
                usrVal=(UsrSignUpVO)context.getAttribute("usrVal");
                
                Debug.print("context.getAttribute(usrVal) :"+usrVal.toString());
                
                request.setAttribute("usrVal",usrVal);
                request.setAttribute("userid",userid);
                context.setAttribute("userid",userid);
                
                fwd="newMembReg";
              
            
            }
        
          /*
         * New Member usrName & Pwd insert
         */
        
         if(process.equalsIgnoreCase("regMembInsert"))
            {
            
                Debug.print("Inside servlet Insert New Member usrName & Pwd insert block....");
                String userid=request.getParameter("usrid");
                String loginName=request.getParameter("chsUserName");
                
                
                System.out.println("request.getParameter(chsUserName) :"+loginName);

                boolean usrStat=remote.checkUserNameExist(loginName);

                Debug.print("remote.checkUserNameExist(usrId)"+usrStat);
                
                 if(usrStat==true)
                {
                     request.setAttribute("stat","exists");
                     request.setAttribute("userid",context.getAttribute("userid"));
                     request.setAttribute("usrVal",context.getAttribute("usrVal"));
                     
                     fwd="usrExVal";           
                     
                
                }
                else
                {                    
                String password=request.getParameter("cpwd");
                Debug.print("Inside servlet New Member insert block userid :"+userid);
                Debug.print("loginName :"+loginName);
                Debug.print("password :"+password);
                
                boolean stat=remote.updateLoginDetails(userid,loginName,password);
                Debug.print("Insert New Member usrName & Pwd status :"+stat);
                
                request.setAttribute("loginName",loginName);
                request.setAttribute("pwd",password);
                
                /* =====================================
                 *
                 * Sending confirmation E-mail
                 *
                 * ====================================*/
                 //String[] usrVal={fname,lname,birthmonth,birthday,birthyear,membid,email,zip};
                //String[] usrVal=(String[])context.getAttribute("usrVal");
                
                usrVal=(UsrSignUpVO)context.getAttribute("usrVal");
                //context.setAttribute("usrVal",null);
                
                //String emailid=usrVal[6];
                String emailid=usrVal.getEmail();
                
                Debug.print("emailid :"+emailid);
                 
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
                        "<span class=\"boldTxt\">Dear Member,</span>,<br /><br />"+
                        "<p>Please save this email for your records. Your account information is as follows:.<p>"+"<p>----------------------------<p>"+"<p>UserName :"+loginName+"<p> password: "+password+"<p> ----------------------------<p>"+
                        /*"<p>Your account would be activated through your confirmation by visiting the following link: <p>"+
                        "<a href=http://192.168.3.98:8090/dashboad-war/uservalidate.do?email="+request.getParameter("email")+"> Click Here to Activate your Account </a>"+*/
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
                
                fwd="regSuccess";
                
             }
        
        }
     }
   }
         
      catch(Exception e)
         {
             System.out.println(e);
         }
        
         return mapping.findForward(fwd);
        
    }
}


