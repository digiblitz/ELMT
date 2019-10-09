/*
 * UsrMeetListAction.java
 *
 * Created on September 21, 2006, 1:08 PM
 */

package com.mee.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.HLCMeeICPUserDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.*;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.*;

/**
 *
 * @author karthikeyan
 * @version
 */

public class UsrMeetListAction extends Action {
    
   
    
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
            /*
             * Service Locator for ejb/HLCVaigaiSessionJNDI
 JNDI
             */            
                    MessageResources mr=getResources(request);
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.icp");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
        
                    HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
                    HLCVaigaiSessionRemote remote = home.create();
                 
                    String jndiname1=mr.getMessage("jndi.usrreg");
                    Object objref1 = jndiContext.lookup(jndiname1);

                    HLCkaverystatelessRemoteHome home1 = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote remote1 = home1.create();
                               
                    System.out.println("\n after InitialContext inside icp user Meeting list action...\n");
                    
                    String process=request.getParameter("process");
                    HttpSession session=request.getSession();
                    String uId=(String)session.getAttribute("userId");
                    String emailId=(String)session.getAttribute("emailId");
                     /*
                     * Process based Page Redirection
                     */
                   
                    if(process!=null)
                    {
                        /*
                         * Redirection to IcpUsrMeetList 
                         */
                    
                        if(process.equalsIgnoreCase("list")) {   
                            System.out.println("\n Inside Meeting list ...\n");
                    
                            Vector meeDet=new Vector();                     
                            meeDet=remote.displayICPRegistrationForm("Approved");
                            
                            System.out.println("meeDet Vect Size :"+meeDet.size());
                            request.setAttribute("meeDet",meeDet);
                            fwd="IcpUsrMeetList";
                        }
                        
                         /*
                         * Redirection to IcpUsrMeetListAppDisp
                         */
               
                        if(process.equalsIgnoreCase("applyDisp")){  
                            System.out.println("\n Inside Meeting after apply ...\n");
                            String meetingId=request.getParameter("mid");
                            Vector meeDet=new Vector();
                            meeDet = remote.displayICPRegBasedOnMeetingId(meetingId);
                            System.out.println("Size :" +meeDet.size());
                            String userId = (String) session.getAttribute("userId");
                            ArrayList userInfo = (ArrayList)remote1.getUserContactDetails(userId);
                            request.setAttribute("userInfo",userInfo);
                            request.setAttribute("meeDetapp",meeDet);
                            fwd = "IcpUsrMeetListAppDisp";
//                            fwd="IcpUsrMeetListApply";
                        }
                        
                        /*
                         * Redirection to IcpUsrMeetListApply
                         */
                        
                        if(process.equalsIgnoreCase("view"))
                        { 
                            System.out.println("\n Inside Meeting view ...\n");
                    
                            String meetingId=request.getParameter("mid");
                            Vector meeDet=new Vector();
                            meeDet = remote.displayICPRegBasedOnMeetingId(meetingId);
                            System.out.println("Size :" +meeDet.size());
                            request.setAttribute("meeDet",meeDet);
                            String userId = (String) session.getAttribute("userId");
                            ArrayList userInfo = (ArrayList)remote1.getUserContactDetails(userId); 
                            request.setAttribute("userInfo",userInfo);
                            fwd="IcpUsrMeetListApply";
                        }
                        
                       
                          if(process.equalsIgnoreCase("apply"))  { 
                            System.out.println("\n Inside Meeting insert ...\n");
                            String midapp=request.getParameter("mid");
                            String membapp=request.getParameter("hlcmemb");
                            String membno=request.getParameter("membno");
                            if(membapp.equals("no")){
                                membno=null;
                            }
                            System.out.println("meeid :" +midapp + "memb status :"+membapp + "memb no :" +membno);
                            HLCMeeICPUserDetails objICPUser = new HLCMeeICPUserDetails();
                            objICPUser.setIcpMeetingId(midapp);
                            objICPUser.setMembershipStatus(membapp);
                            objICPUser.setUserId(uId);
                            objICPUser.setMemberId(membno);
                            boolean bol = remote.addICPUserDetails(objICPUser);
                            System.out.println(" Result is : "+bol);
                            fwd="IcpUsrMeetListAppConf";
                        }
                   }
        }
                      catch(Exception ex)
                        {
                            System.err.println("Caught an exception."+ex.getMessage());
                            ex.printStackTrace();
                        }
         return mapping.findForward(fwd);
    }
}