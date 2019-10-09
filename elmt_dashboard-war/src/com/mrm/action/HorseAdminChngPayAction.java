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
package com.mrm.action;
/*
 * HorseAdminChngPayAction.java
 *
 * Created on February 21, 2007, 3:47 PM
 */

import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlccommon.util.Debug;
/**
 *
 * @author punitha
 * @version
 */

public class HorseAdminChngPayAction extends Action {
 
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
         HLCKaverySessionBeanStatfulRemote remote=null;
         String horsememId=null;
          
       try{
            HttpSession session = request.getSession();
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
          
            String jndihorse=mr.getMessage("jndi.kavery");
            
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
        
            Object objhorse = jndiContext.lookup(jndihorse);
            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objhorse,HLCKaverySessionBeanStatfulRemoteHome.class);
            remote = home.create();
            
            Object objPayss= jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
             
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");

            String horseMemberId = (String)session.getAttribute("horseMemberId");
            String competitionName =(String)session.getAttribute("competitionName");
            String totalAmount = (String)session.getAttribute("amount");
            String paymentId = (String)session.getAttribute("paymentIdVal");
            String regByuserId = (String)session.getAttribute("regByuserId");
            String pendingAmount = (String)session.getAttribute("pendingAmount");
           
            double tempPendAmount = 0;
                if(pendingAmount!=null && pendingAmount.trim().length()!=0){
                    tempPendAmount = Double.parseDouble(pendingAmount);
                }
            
            Debug.print("horseMemberId in Nova Action::" +horseMemberId);
            Debug.print("tempPendAmount in Nova Action::" +tempPendAmount);
          
            Debug.print("totalAmount in Nova Action::" +totalAmount);
            Debug.print("paymentId in Nova Action::" +paymentId);
             
            Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Nova UserCode:" + (String)session.getAttribute("userCode"));
                    
            Debug.print("Return from Nova:");
            if(userId!=null){
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

                    
                    com.hlcreg.util.HLCPaymentVO objPayDet = (com.hlcreg.util.HLCPaymentVO)session.getAttribute("objPaymentList");       
                    Debug.print("Sucessfully Return objPaymentList:" + objPayDet.toString());
                   
                    HLCPaymentDetailVO objPayDetail = new HLCPaymentDetailVO();  
                    String horseRelId[] = (String[])remote.getAllEmailIds("horseMemberId");
                    Debug.print("horseRelId:::" + horseRelId);
                    
                    objPayDet.setSslResult(sslResult);
                    objPayDet.setSslResultMessage(sslResultMessage);
                    objPayDet.setSslTxnId(sslTxnId);
                    objPayDet.setSslApprovalCode(sslApprovalCode);
                    objPayDet.setSslCvv2Response(sslCvv2Response);
                    objPayDet.setSslAvsResponse(sslAvsResponse);
                    objPayDet.setSslTransactionType(sslTransactionType);
                    objPayDet.setSslInvoiceNo(sslInvoiceNo);
                    objPayDet.setSslEmail(sslEmail);
                    Debug.print("objPayDet::" + objPayDet);
                    Debug.print("sslResult::" + sslResult);
                    objPayDet.setPaymentId(paymentId);
                    objPayDet.setAmount(Double.parseDouble(totalAmount));
                    
                objPayDetail.setUserId(objPayDet.getUserId());
                objPayDetail.setCcName(objPayDet.getCcName());
                objPayDetail.setCcType(objPayDet.getCcType());
                objPayDetail.setCcNumber(objPayDet.getCcNumber());
                objPayDetail.setCcExpMonth(objPayDet.getCcExpMonth());
                objPayDetail.setCcExpYear(objPayDet.getCcExpYear());
                objPayDetail.setCcCvvid(objPayDet.getCcCvvid());
                objPayDetail.setCheckAmount(objPayDet.getCheckAmount());
                objPayDetail.setBankName(objPayDet.getBankName());
                objPayDetail.setCheckName(objPayDet.getCheckName());
                objPayDetail.setCheckDate(objPayDet.getCheckDate());
                objPayDetail.setCheckNumber(objPayDet.getCheckNumber());
                objPayDetail.setAmount(tempPendAmount);
                objPayDetail.setPaymentDate(new Date());
                String newPaymentId = remote.getNextId();
                objPayDetail.setPaymentId(newPaymentId);
                objPayDetail.setParentPaymentId(paymentId);
                objPayDetail.setIpAddress(objPayDet.getIpAddress());
                objPayDetail.setPaymentStatus("Card");
                    
                    objPayDetail.setSslResult(sslResult);
                    objPayDetail.setSslResultMessage(sslResultMessage);
                    objPayDetail.setSslTxnId(sslTxnId);
                    objPayDetail.setSslApprovalCode(sslApprovalCode);
                    objPayDetail.setSslCvv2Response(sslCvv2Response);
                    objPayDetail.setSslAvsResponse(sslAvsResponse);
                    objPayDetail.setSslTransactionType(sslTransactionType);
                    objPayDetail.setSslInvoiceNo(sslInvoiceNo);
                    objPayDetail.setSslEmail(sslEmail);
                                   
 
            String declinePay = (String)session.getAttribute("declinePay");
            boolean update_stat = false;
            boolean create_stat = false;
            boolean bal_stat = false;
            if(objPayDetail!=null && tempPendAmount>0 && sslResult.equals("0")){
                      create_stat = objPaySessRemote.createPayment(objPayDetail);
                      bal_stat = remote.updateBalanaceAmount(paymentId,0);
                      Debug.print("Create Status for  "+ create_stat);
                      Debug.print("Balance Status "+ bal_stat);
            }
            else if(objPayDet!=null && sslResult.equals("0")){
                    update_stat = remote.updatePaymentStatus(objPayDet);
                    Debug.print("Update Status for updatePaymentStatus:"+update_stat);    
            }
              if(sslResult.equals("0")){
                    if(update_stat == true || create_stat == true){
                        boolean statusChange = remote.statusChangeForHorse(horseMemberId,null);
                        bal_stat = remote.updateBalanaceAmount(paymentId,0);
                        Debug.print(" Horse Registration status Changed for Credit Card:" + statusChange);
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
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);
                        request.setAttribute("horseMemberId",horseMemberId);
                         if(tempPendAmount>0){
                                request.setAttribute("totalAmount",String.valueOf(tempPendAmount));
                            }
                            else{
                               request.setAttribute("totalAmount",totalAmount);
                            }
                        
                        
                        
                        request.setAttribute("horseName",competitionName);
                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("competitionName",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("totalAmount",null);
                        return mapping.findForward("confirm");
                    }
                      
                    }
                    else{
                            Debug.print("Not Inserted:");
                            Debug.print("Inside the New Horse Member Payment Declined State");
                           
                            Debug.print("Not inserted"+ tempPendAmount);
                            String amt ="";
                            if(tempPendAmount>0){
                                session.setAttribute("totalAmount",String.valueOf(tempPendAmount));
                            }
                            else{
                                session.setAttribute("totalAmount",totalAmount);
                            }
                            session.setAttribute("paymentId",paymentId);  
                            session.setAttribute("horseName",competitionName);  
                            session.setAttribute("horseMemberId",horseMemberId);  
                            session.setAttribute("regByuserId",regByuserId); 
                            return mapping.findForward("decline");
                    }
                
                    
              }
              
       }
        catch(Exception ecommon){
          Debug.print("" + ecommon);
      }
        return null;  
     }
}
