/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
package com.view.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import com.hlccountry.mail.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcrole.management.HLCBrahmaputraSessionRemote;
import com.hlcrole.management.HLCBrahmaputraSessionRemoteHome;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemote;
import com.hlcsessionbean.krishna.HLCKrishnaStatelessRemoteHome;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemote;
import com.hlcsessionbean.qualificationmatrix.HLCMembershipQualificationMatrixRemoteHome;

/**
 * Servlet implementation class ViewNavigationAction
 */
public class ViewMasterAction  extends Action {

	 public ActionForward execute(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	        try{
	        	MessageResources mr=getResources(request);
	                String namingfactory=mr.getMessage("ejbclient.namingfactory");
	                String contextfactory=mr.getMessage("ejbclient.contextfactory");
	                String urlprovider=mr.getMessage("ejbclient.urlprovider");
	                String lookupip=mr.getMessage("ejbclient.ip");
	                String jndiname=mr.getMessage("jndi.rolemanagement");

	                System.setProperty(namingfactory,contextfactory);
	                System.setProperty(urlprovider,lookupip);
	                Context jndiContext = new InitialContext();
	                Object objref = jndiContext.lookup(jndiname);
	                Debug.print("ViewAction.jndiname:" + jndiname);


	                HttpSession session = request.getSession(true);
	                String userId = (String) session.getAttribute("userId");
	                HLCBrahmaputraSessionRemoteHome roleHome = (HLCBrahmaputraSessionRemoteHome)PortableRemoteObject.narrow(objref,HLCBrahmaputraSessionRemoteHome.class);
	                HLCBrahmaputraSessionRemote roleRemote = roleHome.create();

	                String jndiName=mr.getMessage("jndi.mqm");
	                Object qualObj = jndiContext.lookup(jndiName);
	                HLCMembershipQualificationMatrixRemoteHome qhome = (HLCMembershipQualificationMatrixRemoteHome)
	                javax.rmi.PortableRemoteObject.narrow(qualObj,HLCMembershipQualificationMatrixRemoteHome.class);
	                HLCMembershipQualificationMatrixRemote qremote = qhome.create();

	                String jndiname1=mr.getMessage("jndi.usrreg");
	                Object objref1 = jndiContext.lookup(jndiname1);

	            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
	            javax.rmi.PortableRemoteObject.narrow(objref1,HLCkaverystatelessRemoteHome.class);
	            HLCkaverystatelessRemote remote = home.create();

	            String jndinameKrish=mr.getMessage("jndi.pc");
	            Object krishnaObj = jndiContext.lookup(jndinameKrish);
	                HLCKrishnaStatelessRemoteHome krishnaHome = (HLCKrishnaStatelessRemoteHome)
	                javax.rmi.PortableRemoteObject.narrow(krishnaObj,HLCKrishnaStatelessRemoteHome.class);
	                HLCKrishnaStatelessRemote eveCalRemote = krishnaHome.create();
	                String process = request.getParameter("process");
	                Debug.print("ViewAction.artiProcess:" + process);
	                
	              //==================================================initArtifact Artifact Management =====================================================================================
	                if(process.equals("initGroupView")){
	                	
	                	String masterId=request.getParameter("masterId");
	                   	ArrayList groupList =eveCalRemote.getGroupValues();	                	
	                	request.setAttribute("groupList", groupList);	                	
	                	request.setAttribute("groupId", masterId);
	                     return mapping.findForward("frmAddListgroups");
	                }else if(process.equals("AddGroups")){
	                	String masterId=request.getParameter("masterId");
	                	ArrayList groupList =eveCalRemote.getGroupValues();	                	
	                	request.setAttribute("groupList", groupList);
	                	request.setAttribute("groupId", masterId);
	                	return mapping.findForward("frmAddGroups");
	                }
	                
	                else if(process.equals("createGroups")){
	                	
	                	String masterId=request.getParameter("masterId");
	                	String groupDet=request.getParameter("groupDet");
	                	boolean existStatus=eveCalRemote.alreadyExistCheck(masterId,groupDet);
	                	if(existStatus==false){
	                	boolean status=eveCalRemote.insertGroupDetails(masterId,groupDet);
	                
	                	ArrayList groupList =eveCalRemote.getGroupValues();
	                	ArrayList viewGroupList =eveCalRemote.getViewGroupValues(masterId);
	                	request.setAttribute("viewGroupList", viewGroupList);
	                
	                	request.setAttribute("groupList", groupList);
	                   	request.setAttribute("groupId", masterId);
	                	return mapping.findForward("frmAddListgroups");
	                	}else{
	                		
		                ArrayList groupList =eveCalRemote.getGroupValues();		                		                	
		                request.setAttribute("groupList", groupList);
		                request.setAttribute("groupId", masterId);		                	
	                	request.setAttribute("status", "error");
	                	return mapping.findForward("frmAddGroups");
	                	 
	                	}
	                	
	                }
	                else if(process.equals("searchList")){
	                	
	                	String masterId=request.getParameter("masterId");
	                	String groupDet=request.getParameter("groupDet");	                  
	                	ArrayList groupList =eveCalRemote.getGroupValues();
	                	ArrayList viewGroupList =eveCalRemote.getViewGroupValues(masterId);
	                	request.setAttribute("viewGroupList", viewGroupList);	                	
	                	request.setAttribute("groupList", groupList);	                	
	                	request.setAttribute("groupId", masterId);
	                	return mapping.findForward("frmAddListgroups");
	                }
	                else if(process.equals("EditGroups")){
	                	
	                	String layer_value_id=request.getParameter("layer_value_id");
	                	String masterId=request.getParameter("masterId");
	                	ArrayList groupList =eveCalRemote.getGroupValues();	
	                	String layerValue =eveCalRemote.getGroupEditList(layer_value_id);	 
	                	request.setAttribute("layerValue", layerValue);
	                	request.setAttribute("groupList", groupList);
	                	request.setAttribute("groupId", masterId);
	                	return mapping.findForward("frmEditGroups");
	                }
	                else if(process.equals("update")){
	                	
	                	String masterId=request.getParameter("masterId");
	                	String groupDet=request.getParameter("groupDet");
	                	String layerId=request.getParameter("layerId");	                   	
	                	ArrayList groupList =eveCalRemote.getGroupValues();
	                	boolean existStatus=eveCalRemote.alreadyExistCheck(masterId,groupDet);
	                	if(existStatus==false){
	                	boolean status=eveCalRemote.updateGroupDetails(layerId,masterId,groupDet);
	                	ArrayList viewGroupList =eveCalRemote.getViewGroupValues(masterId);
	                	request.setAttribute("viewGroupList", viewGroupList);	                	
	                	request.setAttribute("groupList", groupList);	              
	                	request.setAttribute("groupId", masterId);
	                	return mapping.findForward("frmAddListgroups");
	                	}else{
	                		request.setAttribute("groupList", groupList);	      	              
		                	request.setAttribute("groupId", masterId);		                
		                	 request.setAttribute("status", "error");
		                		return mapping.findForward("frmEditGroups");
		                	 
		                	}
	                	
	                }
	                else if(process.equals("deleteGroups")){
	                	String viewId=null;
	                	String masterId=null;
	                	String chkIds[]=request.getParameterValues("chk");
	                	 
	                	System.out.println("viewId---------"+viewId);
	                	 masterId=request.getParameter("masterId");
	                	 if(viewId==null){
	                		 viewId=request.getParameter("srchViewId");
	                	 }
	                	 if( masterId==null){
	                		 masterId=request.getParameter("srchMasterId");
	                	 }
	                	String groupDet=request.getParameter("groupDet");
	                 	System.out.println("masterId---------"+masterId+"------"+groupDet);
	                	 if(chkIds!=null){
	                         boolean delStatus=eveCalRemote.deleteGroupValues(chkIds);
	                        }
	                	  
		                	ArrayList groupList =eveCalRemote.getGroupValues();
		                	ArrayList viewGroupList =eveCalRemote.getViewGroupValues(masterId);
		                	request.setAttribute("viewGroupList", viewGroupList);
		                	
		                	request.setAttribute("groupList", groupList);
		                	request.setAttribute("viewId", viewId);
		                	request.setAttribute("groupId", masterId);
		                	return mapping.findForward("frmAddListgroups");
	                }else if(process.equals("viewRoles"))
	                {
	                	String viewRoleId=request.getParameter("viewRoleId");
	                	ArrayList viewRoleList =eveCalRemote.getViewRelatedRoles();
	                	ArrayList viewRoleLOBList =eveCalRemote.getviewRoleLOB(viewRoleId);
	                 	ArrayList viewRoleUserList =eveCalRemote.getViewRelatedRoleUsers(viewRoleId);
	                 	ArrayList viewUserLobList = eveCalRemote.getUserMapLOBList();
	                	request.setAttribute("viewsRelatedRoleList", viewRoleList);
	                	request.setAttribute("viewRoleUserList", viewRoleUserList);
	                	request.setAttribute("viewRoleLOBList", viewRoleLOBList);
	                	request.setAttribute("viewRoleId", viewRoleId);
	                	request.setAttribute("viewUserLobList", viewUserLobList);
	                	return mapping.findForward("frmListViewsRoleUser");
	                }else if(process.equals("LobViewpointMapping"))
	                {
	                	String viewRoleId=request.getParameter("viewRoleId");
	                	String viewUserId=request.getParameter("userId");
	                	ArrayList viewRoleLOBList =eveCalRemote.getviewRoleLOB(viewRoleId);
	                	ArrayList viewRoleViewpointList =eveCalRemote.getviewRoleViewPoint();
	                	request.setAttribute("viewRoleLOBList", viewRoleLOBList);
	                	request.setAttribute("viewRoleViewpointList", viewRoleViewpointList);
	                	request.setAttribute("viewRoleId", viewRoleId);
	                	request.setAttribute("viewUserId", viewUserId);
	                	return mapping.findForward("frmListLobViewPoint");
	                }else if(process.equals("insertLobViewpointMapping"))
	                {
	                /*	String viewRoleId=request.getParameter("viewId");
	                	String viewUserId=request.getParameter("userId");
	                	String viewLobId=request.getParameter("lobId");*/
	                	String viewRoleId=request.getParameter("viewRoleId");
	                	String viewUserId=request.getParameter("userId");
	                	String viewLobId=request.getParameter("lobId");
	                	String[] viewPointId=request.getParameterValues("viewPointId");
	                	boolean insertStatus=eveCalRemote.insertLobViewpointMap(viewRoleId,viewUserId,viewLobId,viewPointId);
	                	ArrayList viewRoleList =eveCalRemote.getViewRelatedRoles();
	                	ArrayList viewRoleLOBList =eveCalRemote.getviewRoleLOB(viewRoleId);
	                 	ArrayList viewRoleUserList =eveCalRemote.getViewRelatedRoleUsers(viewRoleId);
	                	ArrayList viewRoleViewpointList =eveCalRemote.getviewRoleViewPoint();
	                	ArrayList viewUserLobList = eveCalRemote.getUserMapLOBList();
	                	request.setAttribute("viewRoleLOBList", viewRoleLOBList);
	                	request.setAttribute("viewRoleViewpointList", viewRoleViewpointList);
	                	request.setAttribute("viewUserLobList", viewUserLobList);
	                	request.setAttribute("viewRoleUserList", viewRoleUserList);
	                	request.setAttribute("viewsRelatedRoleList", viewRoleList);
	                	request.setAttribute("viewRoleId", viewRoleId);
	                	request.setAttribute("viewUserId", viewUserId);
	                	request.setAttribute("viewLobId", viewLobId);
	                	return mapping.findForward("frmListViewsRoleUser");
	                }
	                
	                
	        }
	 
	    	        catch(Exception exp){
	                    Debug.print("In ViewAction :" + exp.getMessage() );
	                }
			return null;
	 }
	 }


