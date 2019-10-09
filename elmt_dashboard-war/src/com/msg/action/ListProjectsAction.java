/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msg.action;

import com.hlccommon.util.*;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import java.util.List;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.HttpSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vidhya
 */
public class ListProjectsAction extends Action {

    public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Debug.print("ListProjectAction : execute");
        try {
            MessageResources mr = getResources(request);

            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndiname = mr.getMessage("jndi.messages");
            Debug.print("ListProjectsAction.jndiname:" + jndiname);
            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
             
            if (request.getParameter("projectStatus") == null) {
                request.setAttribute("display", "statusFilterOnly");
            } else {
            HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome) PortableRemoteObject.narrow(objref, HLCMessageSessionRemoteHome.class);
            HLCMessageSessionRemote msgRemote = msgHome.create();

            String projectStatus = request.getParameter("projectStatus");
            List projects = null;
            HttpSession session = request.getSession(true);
            String userId = (String) session.getAttribute("userId");
            Debug.print("ActionMessage.userId:" + userId);
            projects = msgRemote.listProjects(userId, projectStatus);
            request.setAttribute("listOfProjects", projects);
            request.setAttribute("selectedProjectStatus", projectStatus);
                 
            }
            
        } catch (Exception e) {
            Debug.print("Exception in ListProjectAction.execute:" + e.getMessage());
        }
        
        return map.findForward("success");
    }
}
