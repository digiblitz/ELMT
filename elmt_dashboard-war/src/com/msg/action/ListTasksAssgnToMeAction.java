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
public class ListTasksAssgnToMeAction extends Action {

    public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Debug.print("ListTasksAssgnToMeAction : execute");
        try {


            if (request.getParameter("taskStatus") == null) {
                request.setAttribute("display", "statusFilterOnly");
            } else {
                MessageResources mr = getResources(request);

                String namingfactory = mr.getMessage("ejbclient.namingfactory");
                String contextfactory = mr.getMessage("ejbclient.contextfactory");
                String urlprovider = mr.getMessage("ejbclient.urlprovider");
                String lookupip = mr.getMessage("ejbclient.ip");
                String jndiname = mr.getMessage("jndi.messages");
                Debug.print("ListTasksAssgnToMeAction.jndiname:" + jndiname);
                System.setProperty(namingfactory, contextfactory);
                System.setProperty(urlprovider, lookupip);
                Context jndiContext = new InitialContext();
                Object objref = jndiContext.lookup(jndiname);
                HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome) PortableRemoteObject.narrow(objref, HLCMessageSessionRemoteHome.class);
                HLCMessageSessionRemote msgRemote = msgHome.create();

                String tasksStatus = request.getParameter("taskStatus");
                List tasks = null;
                HttpSession session = request.getSession(true);
                String userId = (String) session.getAttribute("userId");
                Debug.print("ActionMessage.userId:" + userId);
                tasks = msgRemote.listTasksAssgnToMe(tasksStatus, userId);
                request.setAttribute("listOfTasks", tasks);
                request.setAttribute("selectedTaskStatus", tasksStatus);

            }

        } catch (Exception e) {
            Debug.print("Exception in ListTasksAssgnToMeAction.execute:" + e.getMessage());
        }

        return map.findForward("success");
    }
}
