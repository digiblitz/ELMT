/*
 * IcpOrgRegListAction.java
 *
 * Created on September 22, 2006, 5:38 PM
 */

package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.Debug;
import com.hlcmeeting.util.HLCMeeICPUserDetails;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author karthikeyan
 * @version
 */

public class IcpOrgRegListAction extends Action {
    
    
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
                    
                    String process=request.getParameter("process");
                    HttpSession session=request.getSession();
                    
                    System.out.println("\n after InitialContext inside icp org reg list action...\n");
                                      
                     if(process!=null)
                    {
                        /*
                         * Redirection to IcpOrgRegInitList 
                         */
                        
                        if(process.equalsIgnoreCase("initList"))
                        { 
                             System.out.println("\n inside icp org reg Init list action...\n");
                    
                             fwd="IcpOrgRegInitList";
                        }
                        
                        /*
                         * Redirection to IcpOrgRegList 
                         */
                        
                        if(process.equalsIgnoreCase("list")){   
                            
                             String status=request.getParameter("status");
                             Vector orgList=new Vector();
                             orgList=remote.displayUserListBasedOnStatus(status);
                             System.out.println("Size :" + orgList.size());
                             request.setAttribute("orgList",orgList);
                             request.setAttribute("status",status);
                             fwd="IcpOrgRegList";
                       }
                        
                        /*
                         * Redirection to IcpOrgRegView
                         */
                        
                         if(process.equalsIgnoreCase("view"))
                        {   
                            
                            String relId=request.getParameter("relId");
                            Debug.print("Rel ID in servlet"+relId);
                            Vector orgDet=new Vector();
                            orgDet=remote.displayUserBasedOnReleaseId(relId);
                            System.out.println("Size :" +orgDet.size());
                            request.setAttribute("orgDet",orgDet);
                            fwd="IcpOrgRegView";
                        }
                        
                        /*
                         * Redirection to IcpOrgRegList After Approval 
                         */
                        
                         if(process.equalsIgnoreCase("Approve"))
                        {   
                            
                            String relId=request.getParameter("relId");
                            String membStat=request.getParameter("usrStat");
                            String comment = request.getParameter("commentarea");
                            
                            Debug.print("relId:" + relId);
                             Debug.print("relId:" + membStat);
                              Debug.print("relId:" + comment);
                              
                            HLCMeeICPUserDetails objIcpUser = new HLCMeeICPUserDetails();
                            objIcpUser.setReleaseId(relId);
                            objIcpUser.setRequestStatus(membStat);
                            objIcpUser.setComments(comment);

                            boolean bol = remote.editICPUserStatus(objIcpUser);
                            System.out.println("Result Status : "+bol);
                           
                            fwd="IcpOrgRegList";
                        }
                        
                     }
         }
            catch (Exception ex) {
            System.err.println("Caught an exception."+ex.getMessage());
            ex.printStackTrace();
        }
             return mapping.findForward(fwd);
}
}