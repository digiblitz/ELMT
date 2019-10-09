/*
 * OwnerHorseListAction.java
 *
 * Created on September 19, 2006, 12:10 PM
 */

package com.mrm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import org.apache.struts.util.MessageResources;
import com.hlchorse.form.display.*;
/**
 *
 * @author karthikeyan
 * @version
 */

public class OwnerHorseListAction extends Action {
    
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
        
                HttpSession session=request.getSession();
                String fwdpath="";
        
             try
                {
                    MessageResources mr=getResources(request);
                    String namingfactory=mr.getMessage("ejbclient.namingfactory");
                    String contextfactory=mr.getMessage("ejbclient.contextfactory");
                    String urlprovider=mr.getMessage("ejbclient.urlprovider");
                    String lookupip=mr.getMessage("ejbclient.ip");
                    String jndiname=mr.getMessage("jndi.kaverysful");
                                      
                    System.setProperty(namingfactory,contextfactory);
                    System.setProperty(urlprovider,lookupip);
                    Context jndiContext = new InitialContext();
                    Object objref = jndiContext.lookup(jndiname);
                    
                    
                    HLCKaverySessionStatefulRemoteHome home =
                    (HLCKaverySessionStatefulRemoteHome) PortableRemoteObject.narrow(objref, HLCKaverySessionStatefulRemoteHome.class);
                    
                   
                    HLCKaverySessionStatefulRemote remote = null;
                   
                    Vector HorseList = new Vector();
                  
                    System.out.println("creating Remote...");
                   
                            remote = home.create();
                            System.out.println("After Create...");
                            String process=request.getParameter("process");
                            
                            if(process!=null)
                            {
                                if(process.equalsIgnoreCase("view"))
                                {
                                    String loginName=(String)session.getAttribute("loginName");
                                    HorseList = remote.getHorseDetailsBasedOnOwner(loginName);
                                    request.setAttribute("HorseList",HorseList);
                                    fwdpath="HorseMemberListing";
                                }
                            
                                if(process.equalsIgnoreCase("desc"))
                                {               

                                    fwdpath="ViewHorseRegistration";
                                }
                                
                            }
   
                             }
                             catch (Exception ex) 
                             {
                                   System.err.println("Caught an exception.");
                                   ex.printStackTrace();
                             }
    
        
        return mapping.findForward(fwdpath);
        
    }
}
