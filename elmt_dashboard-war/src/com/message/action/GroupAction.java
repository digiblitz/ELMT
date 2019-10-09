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
package com.message.action;
/*
 * GroupAction.java
 *
 * Created on sep 30, 2006, 17:45
*/



//import com.msg.util.MsgContactListMaster;
import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

import com.hlcform.util.Debug;
import com.message.actionform.GroupForm;
import com.hlcmsg.util.HLCGroup;

//import com.msg.session.MessageSessionRemote;
//import com.msg.session.HLCMessageSessionRemoteHome;

/**
 *
 * @author rathna
 * @version
 */

public class GroupAction extends DispatchAction {
    
    public ActionForward initAdd(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("GroupAction.initAdd() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);        
        List  categoryLists = msgRemote.displayCategory();        
        request.setAttribute("CATEGORY", getDropDown(categoryLists));
         Debug.print("Drop Down Category :"+categoryLists.size());  
        return mapping.findForward("formView");
        
    }
    
    
     public ActionForward delete(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("GroupAction.delete() method calling ..........");
        String groupId = request.getParameter("groupId");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);        
        boolean  deactivateGroup = msgRemote.deactivateGroupStatus(groupId);
        Debug.print("Group Deactivated :"+deactivateGroup);
        List  categoryLists = msgRemote.displayCategory();   
        Debug.print("Drop Down Category :"+categoryLists.size());         
        request.setAttribute("CATEGORY", getDropDown(categoryLists));        
        return mapping.findForward("searchView");
        
    }
    
    public ActionForward initSearch(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         Debug.print("GroupAction.initSearch() method calling ..........");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);        
        List  categoryLists = msgRemote.displayCategory();   
         Debug.print("Drop Down Category :"+categoryLists.size());         
        request.setAttribute("CATEGORY", getDropDown(categoryLists));          
        return mapping.findForward("searchView");
    }
    
    
    public ActionForward save(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Debug.print("GroupAction.save() method calling ..........");        
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);        
               
        if(isCancelled(request)) {           
            return mapping.findForward("cancelAddView");
        }        
        GroupForm groupForm =(GroupForm)form;        
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Group Details Add :"+groupForm+",userId="+userId+",emailId="+emailId);        
        //setting the form values to the group object
        HLCGroup group = new HLCGroup();
        group.setGroupName(groupForm.getGroupName());
        group.setCategoryId(groupForm.getCategoryType());
        group.setGroupType(groupForm.getGroupType());
        group.setGroupDesc(groupForm.getGroupDesc());
        group.setUserId(userId);
        
       if(isNotNull(groupForm.getGroupName())) 
       {            
        if(msgRemote.isValidGroupName(group.getUserId(),groupForm.getGroupName())) {
            /*request.setAttribute("GROUP_ALREADY_EXISTS",
                    getResources(request).getMessage("error.groupName.exists"));*/
            String err="Group Name Already Exists";
            request.setAttribute("GROUP_ALREADY_EXISTS",err);
            return mapping.findForward("errorView");
        } else {        
          HLCGroup groupObject =  msgRemote.createGroup(group);        
          Debug.print("Group Success fully added : "+groupObject.getGroupId());
          groupForm.setGroupId(groupObject.getGroupId());
        }
       }
        
       request.setAttribute(mapping.getName(),groupForm);
         /*
          * Redirected to Confirmation page
          */
        return mapping.findForward("confirmView");
    }
    
    public ActionForward initEdit(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Debug.print("GroupAction.initEdit() method calling ..........");
        String groupId = request.getParameter("groupId");
        GroupForm groupForm =(GroupForm)form;        
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact Details Edit:"+groupForm+",userId="+userId+",emailId="+emailId);
        
        // TODO call ejb to get the group details
        
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        HLCGroup group  = msgRemote.getGroupDetailsBasedOnGroupId(groupId);
        groupForm.setGroupId(group.getGroupId());
        groupForm.setCategoryType(group.getCategoryId());
        groupForm.setCategoryName(group.getCategoryName());
        groupForm.setGroupType(group.getGroupType());
        groupForm.setGroupName(group.getGroupName());
        groupForm.setGroupDesc(group.getGroupDesc());
        groupForm.setCategoryType(group.getCategoryId());
        
        request.setAttribute(mapping.getName(),groupForm);
                 /*
                  * Redirected to Confirmation page
                  */
        return mapping.findForward("editView");
    }
    public ActionForward edit(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Debug.print("GroupAction.edit() method calling ..........");
        GroupForm groupForm =(GroupForm)form;
        
        if(isCancelled(request)) {           
            return mapping.findForward("cancelEditView");
        }
        
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact Details Edit:"+groupForm+",userId="+userId+",emailId="+emailId);
        
        HLCGroup group = new HLCGroup();
        group.setGroupId(groupForm.getGroupId());
         group.setGroupName(groupForm.getGroupName());
        group.setCategoryId(groupForm.getCategoryType());
        group.setGroupType(groupForm.getGroupType());
        group.setGroupDesc(groupForm.getGroupDesc());
        
        // call ejb to update the group details        
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);        
        boolean flag =   msgRemote.updateGroup(group);
        
        Debug.print("Group Success fully updated :"+flag);
        
         List  categoryLists = msgRemote.displayCategory();   
         Debug.print("Drop Down Category :"+categoryLists.size());         
        request.setAttribute("CATEGORY", getDropDown(categoryLists)); 
        
        request.setAttribute(mapping.getName(),groupForm);
                 /*
                  * Redirected to Confirmation page
                  */
        return mapping.findForward("searchView");
    }
    
    
    public ActionForward initView(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Debug.print("GroupAction.initView() method calling ..........");
        String groupId = request.getParameter("groupId");
        GroupForm groupForm =(GroupForm)form;
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        HLCGroup group  = msgRemote.getGroupDetailsBasedOnGroupId(groupId);
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact Details Edit:"+groupForm+",userId="+userId+",emailId="+emailId);
        
        
        // MessageSessionRemote msgRemote =  initializeEJB(request);
        groupForm.setGroupId(group.getGroupId());
        groupForm.setCategoryType(group.getCategoryId());
        groupForm.setCategoryName(group.getCategoryName());
        groupForm.setGroupType(group.getGroupType());
        groupForm.setGroupName(group.getGroupName());
        groupForm.setGroupDesc(group.getGroupDesc());
        groupForm.setCategoryType(group.getCategoryId());
        
        request.setAttribute(mapping.getName(),groupForm);
                 /*
                  * Redirected to Confirmation page
                  */
        return mapping.findForward("editView");
    }
    
    public ActionForward search(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Debug.print("GroupAction.search() method calling ..........");
        GroupForm groupForm=(GroupForm)form;        
     
          if(request.getAttribute(mapping.getName())!=null) {          
            groupForm = (GroupForm) request.getAttribute(mapping.getName());
          }
        Debug.print("Contact Details :"+groupForm+",Category="+groupForm.getCategoryType()+",Keyword="+groupForm.getKeyword());
        
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);
        
        // calling ejb to get the details
        
        List results =  msgRemote.searchByGroup(groupForm.getCategoryType(),groupForm.getKeyword());        
        Debug.print("Search Group Results:"+((results!=null)?results.size():0));                
        groupForm.setResults(results);        
         List  categoryLists = msgRemote.displayCategory();            
         Debug.print("Drop Down Category :"+categoryLists.size());  
         request.setAttribute("CATEGORY", getDropDown(categoryLists)); 
        request.setAttribute(mapping.getName(),groupForm);
        
        
        // Redirected to success page
        
        return mapping.findForward("searchView");
    }
    
    private HLCMessageSessionRemote initializeEJB(HttpServletRequest request) throws Exception{
        
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.contactDetail");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote remote = home.create();
        
        return remote;
        
    }
    
    
    
    
     private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
    }
    
    private ArrayList getDropDown(List mediaLists){
        
        ArrayList dropDwonLists = new ArrayList();
        for (Iterator it = mediaLists.iterator(); it.hasNext();) {
            String[] keyValue = (String[]) it.next();
            dropDwonLists.add(new LabelValueBean(keyValue[1],keyValue[0]));
        }
        return dropDwonLists;
    }
}
