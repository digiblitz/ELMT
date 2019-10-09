/*
 * InsertPostMessageAction.java
 *
 * Created on October 3, 2006, 20:35
 */

package com.message.action;


import com.hlcform.util.Debug;
import com.message.actionform.InsertPostMessageForm;
import com.hlcmsg.util.HLCGroup;
import java.util.ArrayList;

import java.util.List;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.hlcmsg.util.HLCGroupMessage;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailAttachment;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;
import org.apache.struts.upload.FormFile;

import com.hlcmsg.session.HLCMessageSessionRemote;
import com.hlcmsg.session.HLCMessageSessionRemoteHome;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author rathna
 * @version
 */
public class InsertPostMessageAction extends DispatchAction {
    
    private static final String  UPLOAD_DIR = "upload";
    private static final String  COMMA = ",";
    
     public ActionForward initAdd(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     
     InsertPostMessageForm addPostbean =(InsertPostMessageForm)form;
     String userId = (String)request.getSession().getAttribute("userId");
     String emailId = (String)request.getSession().getAttribute("emailId");
     String ROOT_DIR = getResources(request).getMessage("email.Attachment.path");
     HLCMessageSessionRemote msgRemote =  initializeEJB(request);     
     ArrayList groups = msgRemote.getAvailableGroup(userId);
     request.setAttribute("AvailGroups",groups);
     Debug.print("Contact edit Details :"+addPostbean+",userId="+userId+",emailId="+emailId+" ,parentId="+
             addPostbean.getParentMsgId());
      addPostbean.setFrom(emailId) ;
      request.setAttribute(mapping.getName(),addPostbean);
      request.setAttribute("validgroup","yes");
      return mapping.findForward("formView");
     }
        
    
    public ActionForward postMail(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        Debug.print("InsertInviteGroupAction.edit() method calling ..........");
        InsertPostMessageForm addPostbean =(InsertPostMessageForm)form;
        
        String userId = (String)request.getSession().getAttribute("userId");
        String emailId = (String)request.getSession().getAttribute("emailId");
        Debug.print("Contact edit Details :"+addPostbean+",userId="+userId+",emailId="+emailId);
        
        //getting the value
        
        String[] to=addPostbean.getTo();
        String from=addPostbean.getFrom();
        String subject=addPostbean.getSubject();
        String msg=addPostbean.getMsg();
        FormFile file = addPostbean.getfile();
        
        //logging the inputs coming from the GUI
        Debug.print("InsertPostMessageForm ="+addPostbean);
        String ROOT_DIR = getResources(request).getMessage("email.Attachment.path");
        HLCMessageSessionRemote msgRemote =  initializeEJB(request);

//for check the  user is owner or not

        String [] groupIds = new String[to.length];
        List emailLists = new ArrayList();
        List userLists = new ArrayList();
        for (int i = 0; i < to.length; i++) {
         List groupNameDetails =  msgRemote.getEmailBasedOnGroupName(to[i]);
             for (int j = 0; j < groupNameDetails.size(); j++) {
                 String[] strArr = (String[]) groupNameDetails.get(i);
                 userLists.add(strArr[0]);
                 emailLists.add(strArr[1]);
                 if(j==0)
                   groupIds[i] = strArr[2];                   
             }            
        }
        
        String[] emailIds = (String[]) emailLists.toArray(new String[emailLists.size()]);
        String[] userIds = (String[]) userLists.toArray(new String[userLists.size()]);
       
        //upload the  file
        Debug.print("RootDir="+ROOT_DIR);
        String fileName = file.getFileName();
        String dbFilePath = UPLOAD_DIR+COMMA+fileName;
        String uploadDir =ROOT_DIR+File.separator +UPLOAD_DIR;
        String filePath = uploadDir+File.separator+fileName;
        
        
        Debug.print("FullPath="+filePath);
        Debug.print("DBPath="+dbFilePath);
        BufferedOutputStream outFile = null;
        try {
            //creating the temp dir if not exists
            File fDir = new File(uploadDir);
            if (!fDir.exists()) {
                if (!fDir.mkdir()) {
                    throw new IOException("Cannot create uploaded for  file");
                }
            }
            File newFile = new File(filePath);
            Debug.print("NewFilePath ="+newFile.getAbsolutePath());
            outFile = new BufferedOutputStream(
                    new FileOutputStream(newFile));
            outFile.write(file.getFileData());
        } catch (IOException ie) {
            Debug.print("Error while writing into the file");
        } finally {
            if(outFile!=null){
                outFile.flush();
                outFile.close();
            }
        }
        
        for (int i = 0; i < groupIds.length; i++) {
            //setting the form values to the group object
            HLCGroupMessage group = new HLCGroupMessage();
            group.setFromUserId(userId);
            group.setSubject(addPostbean.getSubject());
            group.setMessageDesc(addPostbean.getMsg());
            group.setAttachFilePath(dbFilePath);
            group.setGroupId(groupIds[i]);
            group.setToUserId(userIds[i]); 
            if(isNotNull(addPostbean.getParentMsgId()))
            group.setParentMessageId(addPostbean.getParentMsgId());
            // call ejb to insert the GroupMessage details
            boolean flag =  msgRemote.createGroupMessage(group);
            Debug.print("Inserted sucessfully :"+flag);
            
        }
        List attachmentList = new ArrayList();
        EmailContent email=new EmailContent();        
        email.setTo(emailIds);
        email.setFrom(emailId);
        email.setSubject(addPostbean.getSubject());
        email.setBody(addPostbean.getMsg());
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setName(addPostbean.getSubject());
        emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
        emailAttachment.setPath(filePath);
        attachmentList.add(emailAttachment);
        email.setAttachments(attachmentList);        
       
        EmailEngine emailEngine = new EmailEngine();
        boolean emailFlag = emailEngine.sendMimeEmail(email);
        Debug.print("Email sent sucessfully :"+emailFlag);

         /*
          * Redirected to Confirmation page
          */
        return mapping.findForward("confirmView");
    }
    
     
     private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
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
    
}