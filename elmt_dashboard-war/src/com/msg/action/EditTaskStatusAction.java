/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msg.action;

import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
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
public class EditTaskStatusAction extends Action {

    private String forward = null;

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        if (request.getParameter("postEditng") == null) {
            forward = "input";
        } else {

            MessageResources mr = getResources(request);

            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextFactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");

            String jndiname = mr.getMessage("jndi.messages");

            System.setProperty(namingfactory, contextFactory);
            System.setProperty(urlprovider, lookupip);

            Context context = new InitialContext();
            Object ejbObject = context.lookup(jndiname);

            HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome) PortableRemoteObject.narrow(ejbObject, HLCMessageSessionRemoteHome.class);
            HLCMessageSessionRemote sessionRemote = home.create();

            String taskId = request.getParameter("taskId");
            String taskStatus = request.getParameter("taskStatus");
            boolean result = sessionRemote.updateTaskStatus(taskStatus, taskId);

            if (result) {
                forward = "updateSuccess";
            }
        }

        return mapping.findForward(forward);
    }
}
