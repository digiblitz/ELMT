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
/*  Program Name    : ActionBillingSchedule.java
 *  Created Date    : October 13, 2006, 12:15 AM
 *  Author          : Punitha.R
 *  Version         : 1.2
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.sponsor.action;


import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import com.sponsor.actionform.FormChennaiServlet;
import com.sponsor.actionform.FormSponsorPayment;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;

import javax.naming.InitialContext;
import javax.naming.Context;

import com.hlcspnr.sponsor.*;
import com.hlcspnr.util.*;
import java.util.Properties;
import java.util.*;
import java.text.*;
import com.hlccommon.util.Debug;

/**
 *
 * @author suresh
 */
public class ActionBillingSchedule extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
  
      //==========================================redirects to payment page
        try{
                Properties p =new Properties();
                p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
                p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
                p.setProperty( "java.naming.provider.url", "localhost:11199" );
                Context jndiContext = new InitialContext(p);
                
                Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
                HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
                HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
                
                Object obj=jndiContext.lookup("ejb/HLCSponsorSessionBean");
                HLCSponsorSessionRemoteHome home = (HLCSponsorSessionRemoteHome)
                javax.rmi.PortableRemoteObject.narrow(obj,HLCSponsorSessionRemoteHome.class);
                HLCSponsorSessionRemote remote = home.create();
               
                HttpSession session = request.getSession(true);
                Debug.print("Return from Nova");
                
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

                
                HLCPaymentDetailVO objPayDet = (HLCPaymentDetailVO) session.getAttribute("objPaymentList");

                Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
                Debug.print("Sucessfully return from Nova objPayDet:" + objPayDet);
                
                String scheduleId = (String)session.getAttribute("scheduleId");
                Debug.print("Sucessfully return from NOVA scheduleId:" + scheduleId);
                 if(sslResult.equals("0")){
                    if(objPayDet!=null && scheduleId!=null){
                        objPayDet.setSslResult(sslResult);
                        objPayDet.setSslResultMessage(sslResultMessage);
                        objPayDet.setSslTxnId(sslTxnId);
                        objPayDet.setSslApprovalCode(sslApprovalCode);
                        objPayDet.setSslCvv2Response(sslCvv2Response);
                        objPayDet.setSslAvsResponse(sslAvsResponse);
                        objPayDet.setSslTransactionType(sslTransactionType);
                        objPayDet.setSslInvoiceNo(sslInvoiceNo);
                        objPayDet.setSslEmail(sslEmail);
                        
                        Debug.print("Sucessfully getting Updated");
                         boolean result  = objPaySessRemote.createPayment(objPayDet);
                         Debug.print("Result  Payment Bean" + result);
                         if(result=true){
                               remote.paymentScheduleStatusChange(scheduleId,"Payment Completed");
                         }
                    String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                    EmailContent email=new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("info@hlc.com");
                    email.setSubject("Test Mail");

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
                            "<p>You have successfully sent your Sponsorship Request. It will be reviewed and Approved within 24 hrs.<p>"+
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
                                    "per your ‘Role’ assigned:"+

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
                        //boolean emailFlag = emailEngine.sendEmailWithAttachment(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);

                         
                         
                        session.setAttribute("scheduleId",null);
                        session.setAttribute("objPaymentList",null);
                       return mapping.findForward("successPayment");
                     }
                 }
                 else{
                    Debug.print("Not Inserted..:");
                 }
                
        }
        catch(Exception exp){
            Debug.print("Exception while getting JNDI for Payment Bean" + exp);
        }
          return mapping.findForward("successPayment");
    }
}
