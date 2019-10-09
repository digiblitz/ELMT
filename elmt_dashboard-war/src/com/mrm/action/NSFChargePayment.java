/*
 * Program Name     :   NSFChargePayment.java
 * Created Date     :   May 24, 2007, 12:06 PM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.3
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */

package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hlcform.util.HLCPaymentDetailVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class NSFChargePayment extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
            MessageResources mr = getResources(request);
            Context jndiContext = new InitialContext();
            
            String jndiName =mr.getMessage("jndi.usrreg");
            Object objref = jndiContext.lookup(jndiName);
            HLCkaverystatelessRemoteHome remoteHome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote statelessRemote = remoteHome.create();        
            
            HttpSession session=request.getSession();
            String usrNam=(String) session.getAttribute("loginName");
            String usrId=(String) session.getAttribute("userId");
            
            String process = request.getParameter("process");
            Debug.print("Process in Servlet is "+process);   
//            try{

//            }

            String memberId=(String)session.getAttribute("status_membId");
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");

            String amount=(String)session.getAttribute("totalAmount1");
            String paymentId = (String)session.getAttribute("paymentId");
        
            // Get client's IP address
            String addr = request.getRemoteAddr(); // 123.123.123.123
            Debug.print("Port Value:" + addr);
             
            Debug.print("Return from Nova:");
            String sslResult = request.getParameter("ssl_result");
            String sslResultMessage =  request.getParameter("ssl_result_message");
            String sslTxnId =  request.getParameter("ssl_txn_id");
            String sslApprovalCode = request.getParameter("ssl_approval_code");
            String sslCvv2Response = request.getParameter("ssl_cvv2_response");
            String sslAvsResponse = request.getParameter("ssl_avs_response");
            String sslTransactionType =  request.getParameter("ssl_transaction_type");
            String sslInvoiceNo = request.getParameter("ssl_invoice_number");
            String sslEmail = request.getParameter("ssl_email");

            Debug.print("Sucessfully return from Nova sslResult:" + sslResult);
            Debug.print("Sucessfully return from Nova sslResultMessage:" + sslResultMessage);
            Debug.print("Sucessfully return from Nova sslTxnId:" + sslTxnId);
            Debug.print("Sucessfully return from Nova sslApprovalCode:" + sslApprovalCode);
            Debug.print("Sucessfully return from Nova sslCvv2Response:" + sslCvv2Response);
            Debug.print("Sucessfully return from Nova sslAvsResponse:" + sslAvsResponse);
            Debug.print("Sucessfully return from Nova sslTransactionType:" + sslTransactionType);
            Debug.print("Sucessfully return from Nova sslInvoiceNo:" + sslInvoiceNo);
            Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);    
            
            HLCPaymentDetailVO objPayDet = (HLCPaymentDetailVO)session.getAttribute("PayDetVO"); 
            String parentPayId = objPayDet.getParentPaymentId();

            objPayDet.setSslResult(sslResult);
            objPayDet.setSslResultMessage(sslResultMessage);
            objPayDet.setSslTxnId(sslTxnId);
            objPayDet.setSslApprovalCode(sslApprovalCode);
            objPayDet.setSslCvv2Response(sslCvv2Response);
            objPayDet.setSslAvsResponse(sslAvsResponse);
            objPayDet.setSslTransactionType(sslTransactionType);
            objPayDet.setSslInvoiceNo(sslInvoiceNo);
            objPayDet.setSslEmail(sslEmail);
            //IP Address 
            objPayDet.setIpAddress(addr);
/*        //Activation Date           
                    String activationDate = (String)session.getAttribute("activationDate");
                    System.out.print("activationDate" + activationDate);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    Date checkActDate =null;
                    if(activationDate!=null && activationDate.trim().length()!=0){
                        checkActDate =  sdf.parse(activationDate);
                        Debug.print("checkActDate:" + checkActDate);
                    }
                    else{
                        checkActDate = new Date();
                    }
                    Debug.print("checkActDate outer:"+checkActDate);
        //End Activation Date */  
            Debug.print("sslResult::" + sslResult);
            
           if(objPayDet!=null){

              if(sslResult.equals("0")){
                        objPayDet.setPendingAmount(0);
                        //objPayDet.setNsf_charge_status(true);
                        boolean payment_stat = statelessRemote.updateNSFPayment(objPayDet);
                        Debug.print("Update Status "+payment_stat); 
                        Debug.print("Amount is "+ objPayDet.getAmount());
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Your Registration Status");
                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                        " </tr>"+
                        "  <tr>"+
                        "<td valign=\"top\">"+
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"+
                        "<tr>"+
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                        "</tr>"+
                        "<tr>"+
                        "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\">"+
                        "<span class=\"boldTxt\">Dear User</span>,<br /><br />"+
                        "<p>You have successfully sent your Membership Request. It will be reviewed and Approved within 24 hrs.<p>"+
                        "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                        "Thank You <br />"+
                        "------------------ <br />"+
                        "<span class=\"boldRedTxt\">HLC Team</span></td>"+
                        "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                        "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                        "</tr>"+
                        " </table>"+
                        "</td></tr>"+
                        "+<tr>"+
                        "<td valign=\"top\" style=\"padding:10px;\">"+
                        "<img src=\"images/pic.jpg\" width=\"272\" height=\"76\" style=\"float:right; padding-left:8px; padding-bottom:8px;\" />"+
                        "<p>The easiest way to access your day to day HLC activities online or offline where ever you are and when ever you want."+
                        "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                        "<ul>"+
                        "<li>Unlimited shopping online.</li>"+
                        "<li>Place advertisements online and/or on-site.</li>"+
                        "<li>Sponsor competitions held by HLC.</li>"+
                        "</ul>"+


                        "Also, REGISTER NOW! and become a member of HLC to access and 	enjoy the following privileges as per your Membership Type and as "+
                        "per your �Role� assigned:"+

                        "<ul>"+
                        "<li>Compete in Equestrian Events held by HLC.</li>"+
                        "<li>Take part in other events like; Annual Meetings, Educational events,"+
                        "Activity Meetings held by HLC etc.</li>"+
                        "<li>Send Messages to other members.</li>"+
                        "<li>Create your own Distribution Lists.</li>"+
                        "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                        " <li>Unlimited Shopping online.</li>"+
                        " <li>Place advertisements online and/or on-site.</li>"+
                        " <li>Sponsor competitions held by HLC.</li>"+
                        "</ul>"+

                        "and much more..."+
                        "So go ahead and <a href=\"#\">REGISTER NOW!</a></td>"+
                        "</tr>"+
                        " <tr>"+
                        "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>"+
                        "</tr>"+
                        "</table>";

                        email.setBody(content);
                        //email.setAttachments();
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);
                        
                        request.setAttribute("totalAmount",String.valueOf(objPayDet.getAmount()));
                        request.setAttribute("paymentId",paymentId);
                        request.setAttribute("ccType",objPayDet.getCcType());
                        request.setAttribute("ccNumber",objPayDet.getCcNumber());
                        
                        session.setAttribute("totalAmount",null);
                        session.setAttribute("paymentId",null);
                        return mapping.findForward("payConf");
                    }
                else{
                    
                        Debug.print("Not Inserted..:");
                        session.setAttribute("objPayment",objPayDet);
                        request.setAttribute("paymentId",paymentId);
                        request.setAttribute("parentPayId",parentPayId);
                        return mapping.findForward("payFailure");
                    }   
                }
        return mapping.findForward("login");
   }/// end of process = pay
}        

