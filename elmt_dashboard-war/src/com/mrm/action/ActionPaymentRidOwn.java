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
/*  Program Name    : ActionPaymentRidOwn.java
 *  Created Date    : February 7, 2007, 8:54 PM
 *  Author          : Punitha.R
 *  Version         : 1.3
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
package com.mrm.action;

import com.hlcaccounts.session.*;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlccommon.util.*;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.hlcpayment.*;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 *
 * @author punitha
 */
public class ActionPaymentRidOwn extends Action {
    
    /** Creates a new instance of MagazineOminibus */
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
            
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
            
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
            
            String jname = mr.getMessage("jndi.usrreg");
            Object oef = jndiContext.lookup(jname);

            HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome) javax.rmi.PortableRemoteObject.narrow(oef, HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote userremote = userhome.create();
            
            ArrayList txnDetails = new ArrayList();
            txnDetails = (ArrayList)session.getAttribute("txnDetails");
            Debug.print("txnDetails.size() :"+txnDetails.size());
            
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");
            String totalAmount =(String)session.getAttribute("amount");
            String registrationLevel = (String)session.getAttribute("registrationLevel");
            Debug.print("registrationLevel:"+registrationLevel);
            String horseName =(String)session.getAttribute("horseName");
            Debug.print("horseName:"+horseName);
            String paymentId = (String)session.getAttribute("paymentId");
            
            Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Nova UserCode:" + (String)session.getAttribute("userCode"));
            
            HLCRequestHorseDetVO reqHrDetVO = (HLCRequestHorseDetVO)session.getAttribute("reqHrDetVO");
            HLCHorseUserVO objOwnerVO =( HLCHorseUserVO)session.getAttribute("objOwnerVO");
            String horseMemberId = (String)session.getAttribute("horseMemberId");
            String riderId =(String)session.getAttribute("riderId");
            String ownerUserId =(String)session.getAttribute("ownerUserId");
            String status = (String)session.getAttribute("status");
            boolean StatusVal = Boolean.parseBoolean("status");
            ArrayList commonOwners =(ArrayList)session.getAttribute("commonOwners");
            Debug.print("Return from Nova:");
            HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            System.out.println("payRes in card servlet: " + payRes);
            System.out.println("results in card servlet: " + results);
            System.out.println("statusId3 in card servlet: " + statusId3);
            System.out.println("statusId in card servlet: " + statusId);

            if (statusId3 == null || statusId3.equalsIgnoreCase("Failure")) {
                String errorCd = payRes.getErrorCd();
                String lngMsg = payRes.getLngMsg();

                String toMailIds[] = {emailId};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("info@digiblitz.com");
                email.setSubject("Add Rider Owner Decline");
                System.out.println("toMailIds: " + toMailIds);
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"USEA Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
                        " </tr>" +
                        "  <tr>" +
                        "<td valign=\"top\">" +
                        "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" +
                        "<tr>" +
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopLeft.jpg\" width=\"13\" height=\"12\" /></td> " +
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\"></td>" +
                        "<td width=\"13\" height=\"12\" valign=\"top\"><img src=\"images/cornerTopRght.jpg\" width=\"13\" height=\"12\" /></td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td valign=\"top\" background=\"images/left.jpg\">&nbsp;</td>" +
                        "<td valign=\"top\" bgcolor=\"#FBF2F2\">" +
                        "<p>Payment has been declined and the details are as follows:<p>" +
                        "<span class=\"boldTxt\">Declined Details:</span>.</p>" +
                        "TIMESTAMP:" + payRes.getTmStmp() + " <br/>" +
                        "L_ERRORCODE0:" + payRes.getErrorCd() + "<br/>" +
                        "L_LONGMESSAGE0:" + payRes.getLngMsg() + "<br/>" +
                        "AMT:" + payRes.getAmount() + "<br/>" +
                       // "AVSCODE: " + payRes.getSslAvsResponse() + "<br/>" +
                      //  "CVV2MATCH:" + payRes.getSslCvv2Response() + "<br/>" +
                        "Corrective action:" + payRes.getFinalCorecAction() + "<br/></p>" +
                        "<span class=\"boldRedTxt\">HLC Team</span></td>" +
                        "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                        "</tr>" +
                        "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                        "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                        "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                        "</tr>" +
                        " </table>" +
                        "</td></tr>" +
                        "+<tr>" +
                        "<td valign=\"top\" style=\"padding:10px;\">" +
                        "</td>" +
                        "</tr>" +
                        " <tr>" +
                        "<td style=\"border-top:1px solid #CC3333; padding-top:8px;\" align=\"right\"><img src=\"images/logo-eMailFooter.jpg\" width=\"63\" height=\"65\" /></td>" +
                        "</tr>" +
                        "</table>";

                email.setBody(content);
                EmailEngine emailEngine = new EmailEngine();
                boolean emailFlag = emailEngine.sendMimeEmail(email);
                Debug.print("Email sent sucessfully :" + emailFlag);
                 String amt=(String)session.getAttribute("amount");                             
                 session.setAttribute("amt",amt);
                session.setAttribute("paymentId", (String)session.getAttribute("paymentId"));
                System.out.println("Payment id in Adrider Action+++++++++++"+(String)session.getAttribute("paymentId"));
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("registrationLevel", registrationLevel);
                request.setAttribute("horseMemberId", horseMemberId);
                request.setAttribute("horseName", horseName);
                request.setAttribute("paymentId",(String)session.getAttribute("paymentId"));

                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);

                return mapping.findForward("decline");
            }

            
            if(userId!=null){
                /*String sslResult = request.getParameter("ssl_result");
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
                Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);*/
                String sslResult = objPaypal.getSslResult();
                String sslResultMessage = objPaypal.getSslResultMessage();
                String sslTxnId = objPaypal.getSslTxnId();
                String sslApprovalCode = request.getParameter("ssl_approval_code");
                String sslCvv2Response = objPaypal.getSslCvv2Response();
                String sslAvsResponse = objPaypal.getSslAvsResponse();
                String sslTransactionType = objPaypal.getSslTransactionType();
                String sslInvoiceNo = objPaypal.getSslInvoiceNo();
                String sslEmail = objPaypal.getSslEmail();
                String ppAuthorizationID = objPaypal.getPpAuthorizationID();
                String ppPaymentType = objPaypal.getPpPaymentType();
                double ppFeeAmt = objPaypal.getPpFeeAmt();
                double ppSettleAmt = objPaypal.getPpSettleAmt();
                double ppTaxAmt = objPaypal.getPpTaxAmt();
                String ppExchangeRate = objPaypal.getPpExchangeRate();
                String ppPaymentStatus = objPaypal.getPpPaymentStatus();
                String ppPendingReason = objPaypal.getPpPendingReason();
                String ppCorrelationID = objPaypal.getPpCorrelationID();
                String ppParentTransactionID = objPaypal.getPpParentTransactionID();
                String ppReasonCode = objPaypal.getPpReasonCode();
                String inVoiceId3 = objPaypal.getInVoiceID();

                Debug.print("Sucessfully return from Nova sslResult:" + sslResult);
                Debug.print("Sucessfully return from Nova sslResultMessage:" + sslResultMessage);
                Debug.print("Sucessfully return from Nova sslTxnId:" + sslTxnId);
                Debug.print("Sucessfully return from Nova sslApprovalCode:" + sslApprovalCode);
                Debug.print("Sucessfully return from Nova sslCvv2Response:" + sslCvv2Response);
                Debug.print("Sucessfully return from Nova sslAvsResponse:" + sslAvsResponse);
                Debug.print("Sucessfully return from Nova sslTransactionType:" + sslTransactionType);
                Debug.print("Sucessfully return from Nova sslInvoiceNo:" + sslInvoiceNo);
                Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);
                
                HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
                objPayDet = (HLCPaymentDetailVO)session.getAttribute("objPaymentList");
                Debug.print("Sucessfully Return objPaymentList:" + objPayDet.toString());
                
                objPayDet.setSslResult(sslResult);
                objPayDet.setSslResultMessage(sslResultMessage);
                objPayDet.setSslTxnId(sslTxnId);
                objPayDet.setSslApprovalCode(sslApprovalCode);
                objPayDet.setSslCvv2Response(sslCvv2Response);
                objPayDet.setSslAvsResponse(sslAvsResponse);
                objPayDet.setSslTransactionType(sslTransactionType);
                objPayDet.setSslInvoiceNo(sslInvoiceNo);
                objPayDet.setSslEmail(sslEmail);
                objPayDet.setPpAuthorizationID(ppAuthorizationID);
                objPayDet.setPpPaymentType(ppPaymentType);
                objPayDet.setPpFeeAmt(ppFeeAmt);
                objPayDet.setPpSettleAmt(ppSettleAmt);
                objPayDet.setPpTaxAmt(ppTaxAmt);
                objPayDet.setPpExchangeRate(ppExchangeRate);
                objPayDet.setPpPaymentStatus(ppPaymentStatus);
                objPayDet.setPpPendingReason(ppPendingReason);
                objPayDet.setPpCorrelationID(ppCorrelationID);
                objPayDet.setPpParentTransactionID(ppParentTransactionID);
                objPayDet.setPpReasonCode(ppReasonCode);
                objPayDet.setInVoiceID(inVoiceId3);

                
                String riderEmailId="";
                String riderFName="";
                String riderLName="";
                String OwnerFName="";
                String OwnerLName="";
                String OwnerMailId="";
                String membershipName="";
                String OwnerStatusName="";
                String membershipName1="";
                String OwnEmail1="";
                String OwnStatus="";
                String competitionName="";
                String allOwners="";
                
                riderEmailId = (String) session.getAttribute("riderEmailId");
                Debug.print("riderEmailId::: in ActionPaymentRidOwn " + riderEmailId);
                riderFName = (String) session.getAttribute("riderFName");
                Debug.print("riderFName::: in ActionPaymentRidOwn" + riderFName);
                riderLName = (String) session.getAttribute("riderLName");
                Debug.print("riderLName::: in ActionPaymentRidOwn" + riderLName);
                OwnerFName = (String) session.getAttribute("OwnerFName");
                Debug.print("OwnerFName::: in ActionPaymentRidOwn" + OwnerFName);
                OwnerLName = (String) session.getAttribute("OwnerLName");
                Debug.print("OwnerLName::: in ActionPaymentRidOwn" + OwnerLName);
                OwnerMailId = (String) session.getAttribute("OwnerMailId");
                Debug.print("OwnerMailId::: in ActionPaymentRidOwn" + OwnerMailId);
                membershipName = (String) session.getAttribute("membershipName");
                Debug.print("membershipName::: in ActionPaymentRidOwn" + membershipName);
                OwnerStatusName = (String) session.getAttribute("OwnerStatusName");
                Debug.print("OwnerStatusName::: in ActionPaymentRidOwn" + OwnerStatusName);
                membershipName1 = (String) session.getAttribute("membershipName1");
                Debug.print("membershipName1::: in ActionPaymentRidOwn" + membershipName1);
                OwnEmail1 = (String) session.getAttribute("OwnEmail1");
                Debug.print("OwnEmail1::: in ActionPaymentRidOwn" + OwnEmail1);
                allOwners = (String) session.getAttribute("owners");
                Debug.print("All Owners::: in ActionPaymentRidOwn :" + allOwners);
                OwnStatus = (String) session.getAttribute("OwnStatus");
                Debug.print("OwnStatus::: in ActionPaymentRidOwn" + OwnStatus);
                competitionName = (String) session.getAttribute("competitionName");
                Debug.print("competitionName::: in ActionPaymentRidOwn" + competitionName);
                String primaryOwnerEmailId=remote.getPrimaryOwnEmailId(horseMemberId);
                Debug.print("primaryOwnerEmailId :"+primaryOwnerEmailId);
                String ownerNames=(String) session.getAttribute("ownerNames");
                String riderNames=(String) session.getAttribute("riderNames");
                boolean resultPay = false;
                boolean result = false;
                String payUpdate ="";
                if(objPayDet!=null){
                    boolean cardStatus = false;
                    resultPay  = objPaySessRemote.createPayment(objPayDet);
                    Debug.print("resultPay:" +resultPay );
                    Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                    payUpdate = remote.createHorseRequest(reqHrDetVO);
                    Debug.print("sslResult:" + sslResult);
                    if(sslResult.equals("0")){
                        cardStatus = true;
                    }
                    result = remote.createHorseRiderAndOwnerDetails(horseMemberId, riderId, ownerUserId, objOwnerVO,StatusVal,cardStatus,payUpdate);
                    Debug.print("result:" + result);
                    Debug.print("payUpdate:" +payUpdate );
                }
                
                
                
                if(sslResult.equals("0")){
                    
                    if (objPayDet != null) {
                        String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                        boolean idExist = userremote.isInvoiceIdExist(sessionInvoiceId);
                        Debug.print("idExist value is:" + idExist);

//======================INSERT ENTRIES INTO PAYMENTDETAILS TABLE==========================================

                        if (!idExist) {
                            System.out.println("payment Id in Add ridrer+++++"+paymentId);
                            resultPay = objPaySessRemote.createPayment(objPayDet);
                           // Debug.print("resultPay value is:" + resultPay);
                            //Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                            session.setAttribute("sessionResultPay", "true");
                        }
                    }
                    if(resultPay == true && result == true && payUpdate!=null){
                        if(OwnStatus!=null && OwnStatus.length()!=0 && OwnStatus.equalsIgnoreCase("Active") && OwnEmail1!=null && OwnEmail1.length()!=0){
                            // to all the active owners.
                            if(commonOwners!=null && commonOwners.size()!=0){
                          Debug.print("commonOwners::: &&&&" + commonOwners);
                          Iterator itr = commonOwners.iterator();
                          int i=0;
                          while(itr.hasNext()) {
                          HLCHorseRegListVO objOwnerInfo =(HLCHorseRegListVO)itr.next();                         
                          String relaTypeName=objOwnerInfo.getRelationTypeName();
                          if(relaTypeName!=null && (relaTypeName.equalsIgnoreCase("Owner"))){
                                    String OwnEmail=objOwnerInfo.getPreEmail();
                                    Debug.print("Active Owner :" + OwnEmail);
                            
                            String toMailIds[] = {OwnEmail};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Non-Human Additional Owner/Rider");
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
                                    "<span class=\"boldTxt\">Dear Member,</span><br />"+
                                    "<p>"+ OwnerFName+" "+ OwnerLName+" has been added as an owner of "+competitionName+".<p>"+
                                    "<p>Please retain the following information for your records:<p>"+
                                    "Non-Human Name: "+ competitionName +"<br />"+
                                    "Non-Human Registration ID:"+ horseMemberId+"<br />"+
                                    "non-Human Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                    "Owner(s):"+ownerNames+"<br/>"+
                                    "Rider(s):"+riderNames+"<br/>"+
                                    "<p>Additional information regarding "+ competitionName +" can be viewed by logging in to the HLC Online Services.<p>"+
                                    "<p>Thank you.<p><br />"+
                                    "<p>Sincerely, <br />" +
                                    "<p>HLC Staff <p>"+
                                    "</tr>"+
                                    " </table>"+
                                    "</td></tr>"+
                                    "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :"+emailFlag);
                            Debug.print("Email sent sucessfully :"+i);
                            i++;
                           }
                          }
                        }
                            //to the currently added owner.
                            Debug.print("added owner inside if condition :" + OwnerMailId);
                            String toCurrentlyAddedOwner[] = {OwnerMailId};
                            EmailContent email=new EmailContent();
                            email.setTo(toCurrentlyAddedOwner);
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Non-Human Additional Owner/Rider");
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
                                    "<span class=\"boldTxt\">Dear Member,</span><br />"+
                                    "<p>"+ OwnerFName+" "+ OwnerLName+" has been added as an owner of "+competitionName+".<p>"+
                                    "<p>Please retain the following information for your records:<p>"+
                                    "Non-Human Name: "+ competitionName +"<br />"+
                                    "Non-Human Registration ID:"+ horseMemberId+"<br />"+
                                    "Non-Human Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                    "Owner(s):"+ownerNames+"<br/>"+
                                    "Rider(s):"+riderNames+"<br/>"+
                                    "<p>Additional information regarding "+ competitionName +" can be viewed by logging in to the HLC Online Services.<p>"+
                                    "<p>Thank you.<p><br />"+
                                    "<p>Sincerely, <br />" +
                                    "<p>HLC Staff <p>"+
                                    "</tr>"+
                                    " </table>"+
                                    "</td></tr>"+
                                    "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :"+emailFlag);      
                              
                              
                              
                        }
                        // to the current added rider and owner.
                        if(riderEmailId!=null && riderEmailId.length()!=0 && primaryOwnerEmailId!=null && primaryOwnerEmailId.length()!=0){
                            Debug.print("Primary owner inside if condition" + primaryOwnerEmailId);
                            Debug.print("added rider inside if condition" + riderEmailId);
                            String toMailIds[] = {riderEmailId,primaryOwnerEmailId};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Non-Human Additional Owner/Rider");
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
                                    "<span class=\"boldTxt\">Dear Member,</span><br />"+
                                    "<p>"+ riderFName+" "+riderLName+" has been added as a rider of "+competitionName+".<p>"+
                                    "<p>Please retain the following information for your records:<p>"+
                                    "Non-Human Name: "+ competitionName +"<br />"+
                                    "Non-Human Registration ID:"+ horseMemberId+"<br />"+
                                    "Non-Human Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                    "Owner(s):"+ownerNames+"<br/>"+
                                    "Rider(s):"+riderNames+"<br/>"+
                                    "<p>Additional information regarding "+ competitionName +" can be viewed by logging in to the HLC Online Services. Thank you.<p>"+
                                    "<p>Sincerely, <br />" +
                                    "<p>HLC Staff <p>"+
                                    "</tr>"+
                                    " </table>"+
                                    "</td></tr>"+
                                    "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :"+emailFlag);
                        }
                        
                        request.setAttribute("totalAmount",totalAmount);
                        request.setAttribute("horseMemberId",horseMemberId);
                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("totalAmount",null);
                        
                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("riderId",null);
                        session.setAttribute("registrationLevel",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("totalAmount",null);
                        session.setAttribute("reqHrDetVO",null);
                        session.setAttribute("objOwnerVO",null);
                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("riderId",null);
                        session.setAttribute("status",null);
                        session.setAttribute("ownerUserId",null);
                        
                        if(txnDetails!=null) {
                            HLCAccTransactionVO accTxn = new HLCAccTransactionVO();
                            for(int i=0;i<txnDetails.size();i++) {
                                accTxn = new HLCAccTransactionVO();
                                accTxn = (HLCAccTransactionVO)txnDetails.get(i);
                                accTxn.setActive_status(true);
                                
                                boolean stat = mahaRemote.insertAccountTxnDetails(accTxn);
                                Debug.print("insertAccountTxnDetails(accTxn) for payment sucess status for rec no "+i+" :"+stat);
                            }
                        }
                        
                        return mapping.findForward("confirm");
                    }
                    
                } else{
                    Debug.print("Not Inserted:");
                    Debug.print("horsememId :"+horsememId);
                    request.setAttribute("totalAmount",totalAmount);
                    request.setAttribute("registrationLevel",registrationLevel);
                    request.setAttribute("horseMemberId",horseMemberId);
                    request.setAttribute("horseName",horseName);
                    
                    Debug.print("Pay Update is "+ payUpdate);
                    session.setAttribute("totalAmount",totalAmount);
                    session.setAttribute("registrationLevel",registrationLevel);
                    session.setAttribute("horseMemberId",horseMemberId);
                    session.setAttribute("horseName",horseName);
                    request.setAttribute("paymentId",paymentId);
                    
                    if(txnDetails!=null) {
                        HLCAccTransactionVO accTxn = new HLCAccTransactionVO();
                        for(int i=0;i<txnDetails.size();i++) {
                            accTxn = new HLCAccTransactionVO();
                            accTxn = (HLCAccTransactionVO)txnDetails.get(i);
                            accTxn.setActive_status(false);
                            
                            boolean stat = mahaRemote.insertAccountTxnDetails(accTxn);
                            Debug.print("insertAccountTxnDetails(accTxn) for payment declined status for rec no "+i+" :"+stat);
                        }
                    }
                    
                    return mapping.findForward("decline");
                }
            }
            
        } catch(Exception ecommon){
            Debug.print("" + ecommon);
            
        }
        return null;
    }
    
}

