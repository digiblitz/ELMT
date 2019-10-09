/*
 * UsrRegActivateAction.java
 *
 * Created on October 15, 2006, 4:36 PM
 */

package com.mrm.action;

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlchorse.form.util.Debug;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author karthikeyan
 * @version
 */

public class UsrRegActivateAction extends Action {
       
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
        
         try {   
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
                                        
                    String email=request.getParameter("email");
                    
                    boolean status=remote.statusActivate(email);
                    
                    Debug.print("Account Succesfully Activated !! ");
                    
         }
         
          catch(Exception e)
         {
             System.out.println(e);
         }
        
        return mapping.findForward("redirLogin");
        
    }
}
