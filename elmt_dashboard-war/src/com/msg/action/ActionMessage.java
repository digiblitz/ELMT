/*
 * ActionMessage.java
 *
 * Created on September 27, 2006, 7:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.msg.action;


import com.hlccommon.util.*;
import com.hlcmsg.groups.HLCMessageSessionRemote;
import com.hlcmsg.groups.HLCMessageSessionRemoteHome;
import com.message.actionform.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.*;

public class ActionMessage extends Action {
    
    public void outputImage(File thumbfile, String filePath1, HttpServletResponse response) throws FileNotFoundException, IOException {
        if (thumbfile == null) {
            Debug.print("Extra path info was null; should be a resource to view");
        }
        
        //response.reset();
        //response.setContentType("text/plain");
        response.setContentType("application/octet-stream");
        //response.setContentType("application/" + type);
        response.setHeader("Content-Disposition", "attachment;filename=\"" + filePath1 + "\"");
        //response.setHeader("Content-Disposition","attachment; filename=\"" + thumbfile + "\"");
        
        OutputStream os = response.getOutputStream();
        FileInputStream fin = new FileInputStream(thumbfile);
        byte[] buf = new byte[4096];
        int count = 0;
        while(true) {
            //  byte[] buf = new byte[4 * 1024]; // 4K buffer
            // int bytesRead;
            // while ((bytesRead = in.read(buf)) != -1) {
            //out.write(buf, 0, bytesRead);
            
            int n = fin.read(buf);
            if(n == -1) {
                break;
            }
            count = count + n;
            os.write(buf,0,n);
        }
        os.flush();
        os.close();
        fin.close();
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            
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
            String msgProcess = request.getParameter("msgProcess");
            String userId = (String) session.getAttribute("userId");
            Debug.print("ActionMessage.userId:" + userId);
            int count = 0;
            String filePath=mr.getMessage("email.Attachment.path");
            
//==================================================Show Inbox =====================================================================================
            
            if(msgProcess.equals("inbox")){
                try{
                    Debug.print("ActionMessage.inbox()");
                    ArrayList messageList = new ArrayList();
                    if(userId !=null){
                        messageList = (ArrayList)msgRemote.showInbox(userId,1,2);
                    }
                    request.setAttribute("showInbox" ,null);
                    request.setAttribute("showInbox" ,messageList);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                try{
                    count = (int)msgRemote.newMessagesCount(userId);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                session.setAttribute("count",String.valueOf(count));
                Debug.print("ActionMessage.inbox() sucessfully comes from servlet.");
                return mapping.findForward("frmMsgInbox");
            }
//==================================================Show message =====================================================================================
            else if(msgProcess.equals("viewMsg")){
                try{
                    Debug.print("ActionMessage.viewMsg() calling");
                    String messageId = request.getParameter("messageId");
                    Debug.print("ActionMessage.viewMsg() MesssageId" + messageId);
                    HLCMessageVO msgVo = new HLCMessageVO();
                    if(messageId!=null){
                        msgVo = (HLCMessageVO)msgRemote.showMessage(messageId);
                    }
                    if(msgVo.getMessageId()==null){
                        return mapping.findForward("redirfrmMsgInbox");
                    }
                    request.setAttribute("viewMessageDetail" ,null);
                    request.setAttribute("viewMessageDetail" ,msgVo);
                } catch(Exception eDisp){
                    Debug.print("while getting particular messages:" + eDisp);
                }
                try{
                    count = (int)msgRemote.newMessagesCount(userId);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                session.setAttribute("count",String.valueOf(count));
                
                return mapping.findForward("frmMsgMessageView");
            }
//==================================================delete messages =====================================================================================
            else if(msgProcess.equals("deleteMsgs")){
                try{
                    Debug.print("ActionMessage.deleteMsg() calling");
                    String messageIds = request.getParameter("messageIds");
                    boolean result = false;
                    //delete all selected messages
                    if(messageIds!=null){
                        result = msgRemote.deleteMessages(messageIds);
                    }
                    Debug.print("ActionMessage.deleteMsg() After deleting");
                    //show inbox Again
                    ArrayList messageList = new ArrayList();
                    if(userId !=null){
                        messageList = (ArrayList)msgRemote.showInbox(userId,1,2);
                    }
                    request.setAttribute("showInbox" ,null);
                    request.setAttribute("showInbox" ,messageList);
                } catch(Exception eDisp){
                    Debug.print("while deleting messages:" + eDisp);
                }
                try{
                    count = (int)msgRemote.newMessagesCount(userId);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                session.setAttribute("count",String.valueOf(count));
                return mapping.findForward("frmMsgInbox");
            }
//==================================================delete a particular Messages=====================================================================================
            else if(msgProcess.equals("deleteMsg")){
                try{
                    Debug.print("ActionMessage.deleteMsg() calling");
                    String messageId = request.getParameter("messageId");
                    boolean result = false;
                    //delete all selected messages
                    if(messageId!=null){
                        result = msgRemote.deleteMessage(messageId);
                    }
                    Debug.print("ActionMessage.deleteMsg() After deleting");
                    //show inbox Again
                    ArrayList messageList = new ArrayList();
                    if(userId !=null){
                        messageList = (ArrayList)msgRemote.showInbox(userId,1,2);
                    }
                    request.setAttribute("showInbox" ,null);
                    request.setAttribute("showInbox" ,messageList);
                } catch(Exception eDisp){
                    Debug.print("while deleting particular message:" + eDisp);
                }
                try{
                    count = (int)msgRemote.newMessagesCount(userId);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                session.setAttribute("count",String.valueOf(count));
                return mapping.findForward("frmMsgInbox");
            }
//==================================================Init Compose Message=====================================================================================
            else if(msgProcess.equals("initCompose")){
                Debug.print("ActionMessage.initCompose() calling");
                try{
                    count = (int)msgRemote.newMessagesCount(userId);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                request.setAttribute("count",String.valueOf(count));
                return mapping.findForward("frmMsgComposeMessage");
            }
//==================================================Compose Message=====================================================================================
            
            else if(msgProcess.equals("composeMsg")){
                try{
                    Debug.print("ActionMessage.composeMsg() calling");
                    MessageActionForm fbean= (MessageActionForm)form;
                    String fileName=null;
                    String toEmail = request.getParameter("toEmail");
                    String subject = fbean.getSubject();
                    String mesgDesc =fbean.getMesgDesc();
                    FormFile attachFile=fbean.getAttachFile();
                    
                    Debug.print("toEmail: "+toEmail);
                    Debug.print("subject: "+subject);
                    Debug.print("mesgDesc: "+mesgDesc);
                    Debug.print("attachFile: "+attachFile);
                    
                    if(attachFile!=null){
                        String directoryTemp = filePath;
                        fileName = attachFile.getFileName();
                        String imageFilePath = directoryTemp + fileName;
                        Debug.print("ImagePath=" + filePath);
                        BufferedOutputStream outFile = null;
                        
                        try {
                            //creating the temp dir if not exists
                            File fDir = new File(directoryTemp);
                            if (!fDir.exists()) {
                                if (!fDir.mkdir()) {
                                    throw new IOException("Cannot create tmpdir for dimension file");
                                }
                            }
                            File newFile = new File(imageFilePath);
                            Debug.print("NewFilePath ="+ newFile.getAbsolutePath());
                            outFile = new BufferedOutputStream(
                                    new FileOutputStream(newFile));
                            outFile.write(attachFile.getFileData());
                            
                        } catch (IOException ie) {
                            Debug.print("Error while writing into the file");
                        } finally {
                            if(outFile!=null){
                                outFile.flush();
                                outFile.close();
                            }
                        }
                        
                        if(subject==null)
                            subject = "";
                        if(mesgDesc==null)
                            mesgDesc = "";
                        boolean result = false;
                        if(toEmail!=null){
                            result = msgRemote.composeMessage(userId,toEmail,subject,mesgDesc,fileName);
                        }
                        
                        Debug.print("ActionMessage.composeMsg() After composing:" + result);
                        ArrayList messageList = new ArrayList();
                        if(userId !=null){
                            messageList = (ArrayList)msgRemote.showInbox(userId,1,2);
                        }
                    }
                } catch(Exception eDisp){
                    Debug.print("while composing message:" + eDisp);
                }
                try{
                    count = (int)msgRemote.newMessagesCount(userId);
                } catch(Exception eDisp){
                    Debug.print("while getting inbox messages:" + eDisp);
                }
                session.setAttribute("count",String.valueOf(count));
                return mapping.findForward("frmMsgConfirmSend");
                //response.sendRedirect("/jsp/meessages/frmMsgConfirmSend.jsp");
            }
            
//                else if(msgProcess.equals("composeMsg")){
//                    try{
//                        Debug.print("ActionMessage.composeMsg() calling");
//                        String toEmail = request.getParameter("toEmail");
//                        String subject = request.getParameter("subject");
//                        String mesgDesc = request.getParameter("mesgDesc");
//                        String fileName = "";
//                        FormFile attachFile = (FormFile)request.getParameter("attachFile");
//                    if(attachFile!=null){
//                         String directoryTemp = filePath;
//                         fileName = attachFile.getFileName();
//                         String imageFilePath = directoryTemp + fileName;
//                         Debug.print("ImagePath=" + filePath);
//                         BufferedOutputStream outFile = null;
//
//                          try {
//                    //creating the temp dir if not exists
//                      File fDir = new File(directoryTemp);
//                        if (!fDir.exists()) {
//                            if (!fDir.mkdir()) {
//                              throw new IOException("Cannot create tmpdir for dimension file");
//                            }
//                        }
//                   File newFile = new File(imageFilePath);
//                   Debug.print("NewFilePath ="+ newFile.getAbsolutePath());
//                    outFile = new BufferedOutputStream(
//                                new FileOutputStream(newFile));
//                    outFile.write(attachFile.getFileData());
//
//                } catch (IOException ie) {
//                  Debug.print("Error while writing into the file");
//                } finally {
//                 if(outFile!=null){
//                    outFile.flush();
//                    outFile.close();
//                 }
//                }
//
//                       if(subject==null)
//                            subject = "";
//                        if(mesgDesc==null)
//                            mesgDesc = "";
//                        boolean result = false;
//                        if(toEmail!=null){
//                            result = msgRemote.composeMessage(userId,toEmail,subject,mesgDesc,fileName);
//                        }
//
//                        Debug.print("ActionMessage.composeMsg() After composing:" + result);
//                        ArrayList messageList = new ArrayList();
//                        if(userId !=null){
//                            messageList = (ArrayList)msgRemote.showInbox(userId,1,2);
//                        }
//                        }
//                    }
//                    catch(Exception eDisp){
//                        Debug.print("while composing message:" + eDisp);
//                    }
//                    try{
//                        count = (int)msgRemote.newMessagesCount(userId);
//                    }
//                    catch(Exception eDisp){
//                        Debug.print("while getting inbox messages:" + eDisp);
//                    }
//                    session.setAttribute("count",String.valueOf(count));
//                    return mapping.findForward("frmMsgConfirmSend");
//                    //response.sendRedirect("/jsp/meessages/frmMsgConfirmSend.jsp");
//                }
            
            else if(msgProcess.equals("uplView")){
                String filePath1 = request.getParameter("fPath");
                Debug.print("filePath1" + filePath1);
                
                // String file1 = getServletContext().getRealPath(filePath1);
                String type = filePath1.substring(filePath1.lastIndexOf(".")+1,filePath1.length());
                Debug.print("type" + type);
                // String name = filePath1.getName().substring(filePath1.getName().lastIndexOf("/") + 1,filePath1.getName().length());
                String dirPath = filePath;//get it from the configuration
                String tempPath = dirPath + filePath1;
                Debug.print("tempPath" + tempPath);
                File file = new File(tempPath);
                Debug.print("file" + file);
                // outputImage(filePath1,type,response);
                outputImage(file,filePath1,response);
                return null;
            }
            //============================================GLOBAL TRY BLOCK END===================================================================
        }
//===============================================GLOBAL CATCH BLOCK================================================================
        catch(Exception exp){
            Debug.print("In ActionMessage :" + exp.getMessage() );
        }
//============================================GLOBAL FORWARD METHOD===================================================================
        return mapping.findForward("frmMsgHome");
    }
}
