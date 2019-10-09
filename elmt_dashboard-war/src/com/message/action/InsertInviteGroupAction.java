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

import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.util.Debug;
import com.message.actionform.InsertInviteGroupForm;
import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import com.hlcmsg.util.HLCGroup;
import com.hlcmsg.util.HLCGroupUser;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.util.MessageResources;
import javax.servlet.http.HttpSession;

public class InsertInviteGroupAction extends DispatchAction {
    
    public InsertInviteGroupAction() {
    }
    
    public ActionForward initAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        Debug.print("InsertInviteGroupAction.initAdd() method calling ..........");
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        InsertInviteGroupForm addInvbean = (InsertInviteGroupForm)form;
        String groupId = request.getParameter("groupId");
        String groupName = request.getParameter("groupName");
        if(isNotNull(groupId)) {
            addInvbean.setGroupId(groupId);
        }
        if(isNotNull(groupName)) {
            addInvbean.setGroupName(groupName);
        }
        Debug.print("Invite Group Form ="+ addInvbean);
        request.setAttribute(mapping.getName(), addInvbean);
        return mapping.findForward("formView");
    }
    
    public ActionForward initEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        Debug.print("InsertInviteGroupAction.initEdit() method calling ..........");
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        InsertInviteGroupForm addInvbean = (InsertInviteGroupForm)form;
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact edit Details :"+addInvbean+",userId="+userId+",emailId="+emailId);
        List grpname = msgRemote.getGroupName(userId);
        Debug.print("Group Name Size: "+grpname.size());
        addInvbean.setemailId(emailId);
        //addInvbean.setGroupName();
        //addInvbean.setResult(grpname);
        request.setAttribute("GrpName", getDropDown(grpname));
        request.setAttribute(mapping.getName(), addInvbean);
        
        return mapping.findForward("editView");
    }
    
    public ActionForward invitePeople(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Debug.print("InsertInviteGroupAction.invitePeople() method calling ..........");
        InsertInviteGroupForm addInvbean = (InsertInviteGroupForm)form;
        String userId = (String)session.getAttribute("userId");
        String emailId = (String)session.getAttribute("emailId");
        String groupId = request.getParameter("groupId");
        Debug.print("Invite people :" + addInvbean + ",userId=" + userId + ",groupId=" + groupId);
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        
        System.out.println("URL="+request.getRequestURL());
        System.out.println("Remote Address="+request.getRemoteAddr());
        System.out.println("URI="+request.getRequestURI());
        
        String str = request.getRequestURL().toString();
        str = str.substring(0,str.lastIndexOf("/"));
        
        //String hostAddress = str +"/joingroup.do?process=initJoin";
         String hostAddress = str +"/joingroup.do?process=initJoin&gId="+groupId+"&oId="+userId+"&eId="+emailId;
        
        Debug.print("Invite people URL:" + hostAddress);
        
        for (int i = 0; i < addInvbean.getEmailIds().length; i++) {
            String toEmailId =addInvbean.getEmailIds()[i];
            Debug.print("Session User First Name "+session.getAttribute("firstName"));
            Debug.print("Sesssion User Last Name "+session.getAttribute("lastName"));
            Debug.print("Session User Email ID "+session.getAttribute("emailId"));
            String firstName = (String) session.getAttribute("firstName");
            String lastName = (String) session.getAttribute("lastName");
            String emailID = (String) session.getAttribute("emailId");
            Debug.print("Email ID "+i+" is "+toEmailId);
            Debug.print("addInvbean.getinvSubject() "+i+" is "+addInvbean.getinvSubject());
            Debug.print("addInvbean.getmsg() "+addInvbean.getmsg());
            String url = hostAddress+"&toId="+toEmailId;
            EmailContent email = new EmailContent();
            email.setFrom(emailId);
            email.setTo(new String[]{toEmailId});
            email.setSubject(addInvbean.getinvSubject());
            String content ="<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> "+
                    "<tr>" +
                    "<td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">"+
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                    "<tr>"+
                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> "+
                    "</tr>"+
                    "<tr>"+
                    "<td valign=\"top\">"+
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                    "<tr>"+
                    "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"../images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td>"+
                    "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>"+
                    "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>"+
                    "</tr>"+
                    "<tr>"+
                    "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                    "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                    "<span class=\"boldTxt\">Dear User</span>,<br /><br />"+
                    "<p>Dear You Are Requested to join to the new message group created by ,<strong>"+firstName+"</strong><strong> "+ lastName +"</strong>"+
                    "<p>Group Owner mailing Address is <strong>"+emailID+"</strong>"+
                    "<p> U can join into this mailing group by clicking on to the link specified below.<p>"+
                    "<p> User personalised Message to u on creating a group <br>"+addInvbean.getmsg()+"<br/><br/>"+"<a href=\"+url+\"/>"+url+"</a></p>"+
                    "<p>Thank you for using this service.<p>"+
                    "Thank You <br />------------------ <br />"+
                    "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                    "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                    "</tr>"+
                    "<tr>"+
                    "<td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                    "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                    "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                    "</tr>"+
                    "</table>"+
                    "</td>"+
                    "</tr>"+
                    "<tr>"+
                    "<td valign=\"top\" style=\"padding:10px;\">" +
                    "</td>"+
                    "</tr>"+
                    "<tr>"+
                    "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                    "</tr>"+
                    "</table>";//addInvbean.getmsg()+"\n\n"+url
            email.setBody(content);
            Debug.print("Body content is "+addInvbean.getmsg()+"\n\n"+url);
            
            EmailEngine emailEngine = new EmailEngine();
            boolean emailFlag = emailEngine.sendMimeEmail(email);
            Debug.print("Email sent sucessfully :" + emailFlag);
        }
        //  boolean userinvite = msgRemote.createUserInvite(firstName,lastName,emailID)
           /*
               GroupUser group = new GroupUser();
                    boolean boo=true  ;
                  if(msgRemote.isValidEmail(addInvbean.getemailId())==boo) {
                   msgRemote.getGroupDetails(groupName);
                   group.setOwnerId(emailId);
                   group.setUserId(request.getParameter(userId));
                   } else {
                   boolean flag = msgRemote.createGroupUser(group);
                   Debug.print("Inserted sucessfully :" + flag);
            }*/
        
        
        return mapping.findForward("confirmView");
    }
    
    public ActionForward inviteModerate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        Debug.print("InsertInviteGroupAction.inviteModerate() method calling ..........");
        InsertInviteGroupForm addInvbean = (InsertInviteGroupForm)form;
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact edit Details :" + addInvbean + ",userId=" + userId + ",emailId=" + emailId);
        HLCMessageSessionRemote msgRemote = initializeEJB(request);
        String loginName = request.getParameter("loginName");
        if(loginName!=null && loginName.trim().length()!=0){
            HLCkaverystatelessRemote kaveryRemote = initializeKaveryEJB(request);
            String toEmailId = kaveryRemote.getEmailByLoginName(loginName);
            
            Vector toUserId = kaveryRemote.getUserIdByLoginName(loginName);
            String toUserIdStr = (String)toUserId.get(0);
            Debug.print("TO toEmailId::>"+toEmailId);
            Debug.print("From EmailId::>"+emailId);
            Debug.print("TO USer ID::>"+toUserIdStr);
            //String groupId = addInvbean.getGroupId();
            String groupId = request.getParameter("groupName");
            
                String str = request.getRequestURL().toString();
                str = str.substring(0,str.lastIndexOf("/"));        
                String hostAddress = str +"/inviteAction.do?method=initModerator&userId="+userId+"&toUserId="+toUserIdStr+"&groupId="+groupId;
                Debug.print("URL Formed::>"+hostAddress);
            String emailBody = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> "+
                    "<tr>" +
                    "<td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">"+
                    "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                    "<tr><td>"+addInvbean.getbody() +"</td></tr><tr><td>"+"<a href=\"+hostAddress+\"/>"+hostAddress+"</a></td></tr>"+
                    "</table>";
            String[] toEmailIds = new String[]{toEmailId};
            Debug.print("TO emailBody::>"+emailBody);
            EmailContent email = new EmailContent();
            email.setTo(toEmailIds);
            email.setFrom(emailId);
            email.setSubject(addInvbean.getsubject());
            email.setBody(emailBody);
            EmailEngine emailEngine = new EmailEngine();
            boolean emailFlag = emailEngine.sendMimeEmail(email);
            Debug.print("Email sent sucessfully :" + emailFlag);
            return mapping.findForward("confirmView");
        }else{             
            Debug.print("Contact edit Details :"+addInvbean+",userId="+userId+",emailId="+emailId);
            List grpname = msgRemote.getGroupName(userId);
            Debug.print("Group Name Size: "+grpname.size());
            addInvbean.setemailId(emailId);
            //addInvbean.setGroupName();
            //addInvbean.setResult(grpname);
            request.setAttribute("GrpName", getDropDown(grpname));
            request.setAttribute(mapping.getName(), addInvbean);
            return mapping.findForward("editView");
        }
    }
     public ActionForward initModerator(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
         Debug.print("InsertInviteGroupAction.initModerator() method calling ..........");
         HLCMessageSessionRemote msgRemote = initializeEJB(request);
         String groupId = request.getParameter("groupId");
         String fromUserId = request.getParameter("userId");
         String toUserId = request.getParameter("toUserId");
         String groupName = "";
         List grpname = msgRemote.getGroupName(fromUserId);
         ArrayList dropDwonLists = new ArrayList();
         String keyValue[];
         for(Iterator it = grpname.iterator(); it.hasNext(); ){
             keyValue = (String[])(String[])it.next();
             if(keyValue[0].equalsIgnoreCase(groupId)){
                 groupName = keyValue[1];
             }
         }
         boolean status = false;
         if(groupId!=null && groupId.trim().length()!=0 && toUserId != null && toUserId.trim().length()!=0){
           status = msgRemote.setModeratorByGroupId(toUserId,groupId);
         }
         Debug.print("Moderator Activated ::"+status);
         request.setAttribute("groupName",groupName);
         return mapping.findForward("confirmModeratorView");
     }
    private HLCMessageSessionRemote initializeEJB(HttpServletRequest request)
    throws Exception {
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
     private HLCkaverystatelessRemote initializeKaveryEJB(HttpServletRequest request)
    throws Exception {
        MessageResources mr = getResources(request);
        String namingfactory2 = mr.getMessage("ejbclient.namingfactory");
        String contextfactory2= mr.getMessage("ejbclient.contextfactory");
        String urlprovider2 = mr.getMessage("ejbclient.urlprovider");
        String lookupip2 = mr.getMessage("ejbclient.ip");
        String jndiname2 = mr.getMessage("jndi.usrreg");
        System.setProperty(namingfactory2, contextfactory2);
        System.setProperty(urlprovider2, lookupip2);
        Context jndiContext2 = new InitialContext();
        Object objref2 = jndiContext2.lookup(jndiname2);
        com.hlcform.stateless.HLCkaverystatelessRemoteHome home = (com.hlcform.stateless.HLCkaverystatelessRemoteHome)PortableRemoteObject.narrow(objref2, com.hlcform.stateless.HLCkaverystatelessRemoteHome.class);
        com.hlcform.stateless.HLCkaverystatelessRemote remote = home.create();
        return remote;
    }
    
    private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
    }
    private ArrayList getDropDown(List mediaLists) {
        ArrayList dropDwonLists = new ArrayList();
        String keyValue[];
        for(Iterator it = mediaLists.iterator(); it.hasNext(); dropDwonLists.add(new LabelValueBean(keyValue[1], keyValue[0])))
            keyValue = (String[])(String[])it.next();
        
        return dropDwonLists;
    }
}
