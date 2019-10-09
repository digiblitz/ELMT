/*
 * UserIcpListAction.java
 *
 * Created on September 20, 2006, 8:19 PM
 */

package com.mee.action;

import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.*;
import org.apache.struts.util.MessageResources;
/**
 *
 * @author karthikeyan
 * @version
 */

public class UserIcpListAction extends Action {
    
  
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
                    
                    System.out.println("\n after InitialContext inside icp user list action...\n");
                    
                    String process=request.getParameter("process");
                    HttpSession session=request.getSession();
                    String uId=(String)session.getAttribute("userId");
                   
                    /*
                     * Process based Page Redirection
                     */
                    
                    if(process!=null)
                    {
                        /*
                         * Redirection to IcpUsrRegList 
                         */
                        
                        if(process.equalsIgnoreCase("list"))
                        {   
                            Vector usrDetail=new Vector();
                            usrDetail= remote.displayUserList(uId);
                            request.setAttribute("usrDetail",usrDetail); 
                            fwd="IcpUsrRegList";
                        }
                        
                        /*
                         * Redirect to IcpUsrRegDetails
                         */
                        
                        if(process.equalsIgnoreCase("view"))
                        {
                            String releaseId=request.getParameter("releaseId");
                            Vector usrDesc=new Vector();
                            usrDesc= remote.displayUserBasedOnReleaseId(releaseId);
                            //Debug.print("usrDesc size :",usrDesc.size());
                            System.out.println("usrDesc size :"+usrDesc.size());
                            request.setAttribute("usrDesc",usrDesc);
                            fwd="IcpUsrRegDetails";
                            
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
