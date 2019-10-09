/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.msg.action;

import com.hlccommon.util.Debug;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author Rupinder
 */
public class FetchUsersAndProjectsAction extends org.apache.struts.action.Action {
    
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
        
         MessageResources mr = getResources(request);

        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.messages");
        Debug.print("FetchUsersAndProjectsAction.jndiname:" + jndiname);
        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);

        HLCMessageSessionRemoteHome sessionRemoteHome = (HLCMessageSessionRemoteHome) PortableRemoteObject.narrow(objref, HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote sessionRemote = sessionRemoteHome.create();

        List usersList = null;
        List projectsList = null;
        usersList = sessionRemote.getActiveUsers();
        projectsList = sessionRemote.getAllProjects();
        
        request.setAttribute("usersList", usersList);
        request.setAttribute("projectsList", projectsList);
        
        return mapping.findForward(SUCCESS);
        
    }
}