/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  Author Prateek
 */

package com.msg.action;
import com.hlccommon.util.*;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import com.message.actionform.*;

import java.text.SimpleDateFormat;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import  javax.servlet.http.HttpSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
/**
 *
 * @author vidhya
 */
public class ActionProject extends Action{
    
    public ActionForward execute(ActionMapping map,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        MessageResources mr=getResources(request);
            
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.messages");
            Debug.print("ActionMessage.jndiname:" + jndiname);
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            Debug.print("ActionMessage.jndiname:" + jndiname);
            
            HLCMessageSessionRemoteHome msgHome = (HLCMessageSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
            HLCMessageSessionRemote msgRemote = msgHome.create();
            
            HttpSession session = request.getSession(true);
            String project = request.getParameter("Project");
            String userId = (String) session.getAttribute("userId");
            Debug.print("ActionMessage.userId:" + userId);
           if(project.equals("CreateProject"))
           {
                   Debug.print("Creating Project for UserId"+userId);
                   SimpleDateFormat sdate=new SimpleDateFormat("MM/dd/yyyy");
                   ProjectActionForm  fbean=(ProjectActionForm)form;
                  String projectTitle=fbean.getProjectTitle();
                  String projectDescription=fbean.getProjectDescription();
                  String projectStatus=fbean.getProjectStatus();
                 
                   Date startDate=(Date)sdate.parse(fbean.getStartDate());
                
                   Date endDate=(Date)sdate.parse(fbean.getEndDate());
                  Debug.print("Project Title"+projectTitle);
                  Debug.print("Project Description"+projectDescription);
                  Debug.print("Project Status"+projectStatus);
                  Debug.print("Start Date"+startDate);
                  Debug.print("End Date"+endDate);
                  msgRemote.createProject(projectTitle, projectDescription, startDate, endDate, projectStatus, userId);
                  return map.findForward("projectAdded");
           }
            if(project.equals("EditProject"))
            {
                 Debug.print("Editing Project for UserId"+userId);
                  SimpleDateFormat sdate=new SimpleDateFormat("MM/dd/yyyy");
                   ProjectActionForm  fbean=(ProjectActionForm)form;
                  String projectTitle=fbean.getProjectTitle();
                  String projectDescription=fbean.getProjectDescription();
                  String projectStatus=fbean.getProjectStatus();
                  Date startDate=(Date)sdate.parse(fbean.getStartDate());
                  Date endDate=(Date)sdate.parse(fbean.getEndDate());
                  String projectId=request.getParameter("projectId");
                  HLCProjectVO pvo=new HLCProjectVO();
                             pvo.setProjectTitle(projectTitle);
                             pvo.setProjectDesc(projectDescription);
                             pvo.setProjectStartDate(startDate);
                             pvo.setProjectEndDate(endDate);
                             pvo.setProjectStatus(projectStatus);
                             pvo.setProjectId(projectId);
                  msgRemote.updateEditedProject(pvo);           
                  Debug.print("Project Title"+projectTitle);
                  Debug.print("Project Description"+projectDescription);
                  Debug.print("Project Status"+projectStatus);
                  Debug.print("Start Date"+startDate);
                  Debug.print("End Date"+endDate);
                return map.findForward("editProject"); 
            }
            return map.findForward("createProject");
    }
    
    
}
