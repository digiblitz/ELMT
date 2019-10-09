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

import com.hlcform.util.Debug;
import com.message.actionform.MyMessageForm;
import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import com.hlcmsg.util.HLCGroup;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;

public class MyMessageAction extends DispatchAction
{

    public MyMessageAction()
    {
    }

    public ActionForward message(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Debug.print("myMessageAction.message() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String groupId = request.getParameter("groupId");
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact edit Details :" + mymsgbean + ",userId=" + userId + ",emailId=" + emailId + "mapping.getName()=" +mapping.getName());
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        List grpname = msgRemote.getGroupName(userId);
        Debug.print(" Group Results:" + grpname.size());
        request.setAttribute("GrpName", getDropDown(grpname));
        List results = msgRemote.getGroupMyMessages(userId,mymsgbean.getSubject(),groupId);
        Debug.print("My message Results:" + results.size());
        mymsgbean.setResult(results); 
        mymsgbean.setSubject(mymsgbean.getSubject());
        mymsgbean.setGroupId(mymsgbean.getGroupId());
        mymsgbean.setGroupName(mymsgbean.getGroupName());
        request.setAttribute(mapping.getName(), mymsgbean);
        return mapping.findForward("myMsgView");
    }

    public ActionForward group(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Debug.print("myMessageAction.group() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String userId = (String)request.getSession().getAttribute("userId");
        Debug.print("Group View Details :" + mymsgbean + ",userId=" + userId);
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        
        List grpResults = msgRemote.getGroupMyGroupsList(userId);
        Debug.print("my group Results:" + grpResults.size());
        mymsgbean.setGrpResults(grpResults);
        request.setAttribute(mapping.getName(), mymsgbean);
        return mapping.findForward("myGrpView");
     }

     public ActionForward myGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
         throws Exception 
    {
        Debug.print("myMessageAction.myGroup() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String groupId = request.getParameter("groupId");
        String userId = (String)request.getSession().getAttribute("userId");
        Debug.print("MessageAction myGroup Details :" + mymsgbean + ",userId=" + userId);        
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        List grpname = msgRemote.getGroupName(userId);
        Debug.print(" Group Results:" + grpname.size());
        request.setAttribute("GrpName", getDropDown(grpname));        
        List grpMemResults = msgRemote.getGroupMyMembers(userId, mymsgbean.getSubject(),groupId);
        Debug.print("My group member Results:" + grpMemResults.size());
        mymsgbean.setGrpResults(grpMemResults);
        request.setAttribute(mapping.getName(), mymsgbean);
        return mapping.findForward("myGrpMemView");
    }

    public ActionForward myGrpDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Debug.print("myMessageAction.myGrpDetail() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String messageId = request.getParameter("messageId");
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        com.hlcmsg.util.HLCGroupMessage groupMessage = msgRemote.getPostedMessages(messageId);
        Debug.print("my group D:" + groupMessage);
        mymsgbean.setGroupMessage(groupMessage);
        List grpDetails = msgRemote.getMessagesAndReplies(groupMessage.getGroupId(), 
                groupMessage.getMessageId());
        Debug.print("Search Group Results:" +((grpDetails!=null)?grpDetails.size():0));
        mymsgbean.setPostedMsgs(grpDetails);
         Debug.print("My Bean :"+mymsgbean);
        request.setAttribute(mapping.getName(), mymsgbean);
        return mapping.findForward("myGrpDetailsView");
    }
    
    

    
     public ActionForward showDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Debug.print("myMessageAction.showDelete() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String groupId = request.getParameter("groupId");
        HLCMessageSessionRemote msgRemote = initializeEJB(request);   
        Debug.print("GroupId="+groupId);
        HLCGroup groupDetails = msgRemote.getGroupDetailsBasedOnGroupId(groupId);
        mymsgbean.setGroupId(groupId);
        mymsgbean.setGroupName(groupDetails.getGroupName());
        request.setAttribute(mapping.getName(),mymsgbean);
        return mapping.findForward("deleteView");
    }
     
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Debug.print("myMessageAction.delete() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String groupId = mymsgbean.getGroupId();
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        String emailId = mymsgbean.getEmailId();
        Debug.print("Message delete Details :emailId=" + emailId+",groupId="+groupId);
        boolean removeMember = msgRemote.deleteMsgGroupUser(groupId, emailId);     
        Debug.print("Remove Member :" + removeMember);
        return mapping.findForward("deleteConfirmView");
    }

    
     public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        Debug.print("myMessageAction.deactivate() method calling ..........");
        MyMessageForm mymsgbean = (MyMessageForm)form;
        String groupId = mymsgbean.getGroupId();
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        Debug.print("Message delete Details :groupId="+groupId);
        boolean removeMember = msgRemote.deactivateGroupStatus(groupId);     
        Debug.print("Deactivate Group Member:" + removeMember);
        return mapping.findForward("deleteConfirmView");
    }
     
    private HLCMessageSessionRemote initializeEJB(HttpServletRequest request)
        throws Exception
    {
        MessageResources mr = getResources(request);
        String namingfactory = mr.getMessage("ejbclient.namingfactory");
        String contextfactory = mr.getMessage("ejbclient.contextfactory");
        String urlprovider = mr.getMessage("ejbclient.urlprovider");
        String lookupip = mr.getMessage("ejbclient.ip");
        String jndiname = mr.getMessage("jndi.contactDetail");
        System.setProperty(namingfactory, contextfactory);
        System.setProperty(urlprovider, lookupip);
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCMessageSessionRemoteHome home = (HLCMessageSessionRemoteHome)PortableRemoteObject.narrow(objref, com.hlcmsg.session.HLCMessageSessionRemoteHome.class);
        HLCMessageSessionRemote remote = home.create();
        return remote;
    }

    private ArrayList getDropDown(List mediaLists)
    {
        ArrayList dropDwonLists = new ArrayList();
        String keyValue[];
        for(Iterator it = mediaLists.iterator(); it.hasNext(); dropDwonLists.add(new LabelValueBean(keyValue[1], keyValue[0])))
            keyValue = (String[])(String[])it.next();

        return dropDwonLists;
    }
}