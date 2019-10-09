/*
 * BulkMessageAction.java
 *
 * Created on September 19, 2007, 5:18 PM
 */

package com.msg.action;

import com.hlccommon.util.Debug;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.*;


public class BulkMessageAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        MessageResources mr=getResources(request);
        
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        Context jndiContext = new InitialContext();

        String jndiname2 = mr.getMessage("jndi.message");
        Object objref2 = jndiContext.lookup(jndiname2);
        com.hlcmsg.session.HLCMessageSessionRemoteHome  msgSessionHome =(com.hlcmsg.session.HLCMessageSessionRemoteHome)
        PortableRemoteObject.narrow(objref2,com.hlcmsg.session.HLCMessageSessionRemoteHome.class);
        com.hlcmsg.session.HLCMessageSessionRemote msgSessionRemote = msgSessionHome.create();

        String cmd = request.getParameter("cmd");
        Debug.print("CMD: "+cmd);
        HttpSession session = request.getSession(true);
        String userId = (String)session.getAttribute("userId");
        
        /*
         * To Compose Bulk Mail
         * Bean- com.msg.groups.MessageSession
         * Method - newMessagesCount(String toUserId)
         * Bean - com.msg.session.MessageSession
         * Method - displayContactListBasedOnUserId(String userId),displayDistribitionListNameAndmailId(String userId)
         * Redirects to - frmMsgSendBulkMail.jsp
         */
        if(cmd.equals("initBulkMail")){
            Debug.print("BulkMailAction.initBulkMail() calling");
           
            Vector HLCContactDetails = null;
            ArrayList distDetails = null;
            try{
                HLCContactDetails = (Vector)msgSessionRemote.displayContactListBasedOnUserId(userId);
                distDetails = msgSessionRemote.displayDistribitionListNameAndmailId(userId);
            } catch(Exception eDisp){
                Debug.print("while getting contact list and distributiton list:" + eDisp);
            }
            //The returned Vector object is set in request Attribute.
            request.setAttribute("ContactListDetails",HLCContactDetails);
            //The reurned ArrayList object is set in request Attribute.
            request.setAttribute("DistributionListDetails",distDetails);
           
            return mapping.findForward("goToBulkMail");
            
        }
        /*
         * To send Composed Bulk mail
         * Bean - None
         * Redirects to - frmBulkMsgConfirmSend.jsp
         */
        else if(cmd.equals("composeMsg")){
            try{
                String toEmail = request.getParameter("toEmail");
                String subject = request.getParameter("subject");
                String mesgDesc = request.getParameter("mesgDesc");
                
                StringTokenizer strTkns = new StringTokenizer(toEmail,";");
                String composeUserId  = null;
                int i = strTkns.countTokens();
                String toMailIds[]= new String[i];
                int j=0;
                while(strTkns.hasMoreTokens()){
                    try{
                        
                        String emailId = (String)strTkns.nextToken();
                        if(emailId!=null && emailId.trim().length()!=0){
                            toMailIds[j] =  emailId;
                            j++;
                        }
                    } catch(Exception e){
                        Debug.print("Exception while spliting emailId BulkMailAction.composeMsg() :" + e);
                    }
                    
                }
                String fromId = msgSessionRemote.getEmailId(userId);
                EmailContent email=new EmailContent();
                email.setTo(toMailIds);
                email.setFrom(fromId);
                email.setSubject(subject);
                String content="<table width='765' 'border='0' align='center' cellpadding='0' cellspacing='0'>"+
                        "<tr>"+
                        "<td class='blue_division'>"+
                         "<p class='fonts2'>" + mesgDesc + "</p>"+
                        "</td>"+
                        "</tr>"+
                        "</table>";
                email.setBody(content);
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                //Returns a boolean status regarding the Email sending process to the User Email Id with User Details.
                
                Debug.print("Email sent to the employee :"+ emailFlag);
                if(emailFlag){
                    return mapping.findForward("frmMsgConfirmSend");
                }else{
                    return mapping.findForward("frmMsgConfirmFailed");
                }
            }
            
            catch(Exception exp){
                Debug.print("In ActionMessage :" + exp.getMessage() );
            }
        }
//============================================GLOBAL FORWARD METHOD===================================================================
        return null;
    }
}