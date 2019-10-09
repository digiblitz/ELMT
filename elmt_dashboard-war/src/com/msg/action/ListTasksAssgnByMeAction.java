/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msg.action;

/**
 *
 * @author Prateek
 */
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
import com.hlccommon.util.*;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListTasksAssgnByMeAction extends Action {

    public ActionForward execute(ActionMapping map, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        Debug.print("List Tasks Assigned By Me Action : execute");

        try {
            MessageResources mr = getResources(request);
            String namingfactory = mr.getMessage("ejbclient.namingfactory");
            String contextfactory = mr.getMessage("ejbclient.contextfactory");
            String urlprovider = mr.getMessage("ejbclient.urlprovider");
            String lookupip = mr.getMessage("ejbclient.ip");
            String jndiname = mr.getMessage("jndi.messages");
            Debug.print("ListTasksAssignedByMeAction.jndiname:" + jndiname);
            System.setProperty(namingfactory, contextfactory);
            System.setProperty(urlprovider, lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome) PortableRemoteObject.narrow(objref, HLCMessageSessionRemoteHome.class);
            HLCMessageSessionRemote msgRemote = msgHome.create();
            HttpSession session = request.getSession(true);
            String userId = (String) session.getAttribute("userId");
            String taskStatusChoice = request.getParameter("tasksChoice");
            Map tasksList = msgRemote.listUsersWithAssgnTasks(userId);
            Set tasks = tasksList.keySet();
            Debug.print("Size of Map returned " + tasks.size());
            Iterator it = tasks.iterator();

            while (it.hasNext()) {
                String id = (String) it.next();

            }


            Debug.print("Map Size is:" + tasksList.size());
            request.setAttribute("tasks", tasksList);
            request.setAttribute("display", "nameAndStatusFilterOnly");


            if (taskStatusChoice != null && taskStatusChoice.equalsIgnoreCase("taskStatus")) {
                request.removeAttribute("display");
                String assgnToId = request.getParameter("AssgnToName");
                String taskStatus = request.getParameter("taskStatus");
                Debug.print("Inside ListTasksAssgnByMeAction : checking taskStatus ");
                Debug.print("AssignToUserId :" + assgnToId);
                Debug.print("TaskStatus :" + taskStatus);
                Debug.print("UserId :" + userId);
                List listOfTasks = msgRemote.listTasksAssgnByMe(assgnToId, taskStatus, userId);
                Debug.print("Number of Tasks Assigned By Me :" + listOfTasks.size());
                Iterator it1 = listOfTasks.iterator();
                HLCTaskVO vo = null;
                while (it1.hasNext()) {
                    vo = (HLCTaskVO) it1.next();

                }


                request.setAttribute("tasksList", listOfTasks);
                request.setAttribute("AssgnToName",assgnToId);
                request.setAttribute("display", "ListTasks");

            }

        } catch (Exception e) {
            Debug.print("Exception occured in ListTasksAssgnByMeAction" + e.getMessage());
        }

        return map.findForward("success");

    }
}
