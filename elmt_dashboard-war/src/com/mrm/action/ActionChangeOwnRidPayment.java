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
 /* Program Name    : ActionChangeOwnRidPayment.java
  *  Created Date    : February 8, 2007, 7:11 PM
  *  Author          : Punitha.R
  *  Version         : 1.11
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



public class ActionChangeOwnRidPayment extends Action {
    
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
            
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");
            String totalAmount =(String)session.getAttribute("amount");
            String paymentId = (String)session.getAttribute("paymentId");
            String horseStatus =(String)session.getAttribute("horseStatus");
             boolean horseStatusValue = Boolean.parseBoolean(horseStatus);
             
            ArrayList commonOwnersRiders =(ArrayList)session.getAttribute("commonOwnersRiders");
            Debug.print("commonOwners :"+commonOwnersRiders.size());
            
            ArrayList txnDetails = new ArrayList();
            txnDetails = (ArrayList)session.getAttribute("txnDetails");
            Debug.print("txnDetails.size() :"+txnDetails.size());
            
            Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Nova UserCode:" + (String)session.getAttribute("userCode"));
            
            // RequestHorseDetVO reqHrDetVO = (RequestHorseDetVO)session.getAttribute("reqHrDetVO");
            HLCHorseUserVO objOwnerVO =( HLCHorseUserVO)session.getAttribute("objOwnerVO");
            String horseMemberId = (String)session.getAttribute("horseMemberId");
            String riderId =(String)session.getAttribute("riderId");
            String ownerUserId =(String)session.getAttribute("ownerUserId");
            String horseRegisterName =(String)session.getAttribute("horseRegisterName");
            String horseName =(String)session.getAttribute("horseName");
            String status = (String)session.getAttribute("status");
            String regFor = (String)session.getAttribute("regFor");
            String serviceType =(String)session.getAttribute("serviceType");
            String ownNames =(String)session.getAttribute("ownerNames");
            String ridNames =(String)session.getAttribute("riderNames");
            boolean StatusVal = Boolean.parseBoolean("status");
            Debug.print("regFor" + regFor);
            boolean reqValue = true;
            
            boolean ridStatus = false;
            if(regFor!=null){
                ridStatus = true;
            } else{
                ridStatus = false;
            }
            Debug.print("ridStatus:" + ridStatus);
            Debug.print("horseName:"+horseName);
            Debug.print("Return from Nova:");
            
             String riderFName="";
                String riderLName="";
                String OwnerFName="";
                String OwnerLName="";
                String OwnerMailId="";
                String OwnerStatusId="";
                String OwnerStatusName="";
                String relaId="";
                String relaTypeName="";
                String preFName="";
                String preLName="";
                String preOwnerEmail="";
                String preRiderEmail="";
                String preOwnerFName="";
                String preOwnerLName="";
                String preOwnEmail="";
                String preRiderFName="";
                String preRiderLName="";
                String preRidEmail="";
                String OwnerLogName="";
                String horseName1="";
                String membershipName1="";
                
                String membershipName= (String)session.getAttribute("membershipName");
                String riderEmailId= (String)session.getAttribute("riderEmailId");
                riderFName= (String)session.getAttribute("riderFName");
                riderLName= (String)session.getAttribute("riderLName");
                String competitionName= (String)session.getAttribute("competitionName");
                OwnerFName= (String)session.getAttribute("OwnerFName");
                OwnerLName= (String)session.getAttribute("OwnerLName");
                OwnerMailId= (String)session.getAttribute("OwnerMailId");
                OwnerStatusId= (String)session.getAttribute("OwnerStatusId");
                OwnerStatusName= (String)session.getAttribute("OwnerStatusName");
                preOwnerFName= (String)session.getAttribute("preOwnerFName");
                preOwnerLName= (String)session.getAttribute("preOwnerLName");
                preOwnEmail= (String)session.getAttribute("preOwnEmail");
                preRiderFName= (String)session.getAttribute("preRiderFName");
                preRiderLName= (String)session.getAttribute("preRiderLName");
                preRidEmail= (String)session.getAttribute("preRidEmail");
                horseName1= (String)session.getAttribute("horseName1");
                membershipName1= (String)session.getAttribute("membershipName1");
                relaTypeName= (String)session.getAttribute("relaTypeName");
                
                String tempOwnerName = "";
            String tempRiderName = "";

            if (OwnerFName != null && !(OwnerFName.equalsIgnoreCase("")) && OwnerLName != null && !(OwnerLName.equalsIgnoreCase("")) && OwnerFName.trim().length() != 0 && OwnerLName.trim().length() != 0) {
                tempOwnerName = OwnerFName + " " + OwnerLName;
            } else if (ownNames != null) {
                tempOwnerName = ownNames;
            }

            Debug.print("tempOwnerName in card servlet" + tempOwnerName);

            if (riderFName != null && !(riderFName.equalsIgnoreCase("")) && riderLName != null && !(riderLName.equalsIgnoreCase(""))) {
                tempRiderName = riderFName + " " + riderLName;
            } else if (ridNames != null) {
                tempRiderName = ridNames;
            }

            Debug.print("tempRiderName in card servlet" + tempRiderName);

            Debug.print("Return from Nova:");
            //boolean result = false;
            HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            //System.out.println("payRes in card servlet: " + payRes);
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
                email.setSubject("Change Rider Owner Decline");
                System.out.println("toMailIds: " + toMailIds);
                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                        "<span class=\"boldRedTxt\">USEA Team</span></td>" +
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
                System.out.println("Payment id in +++++++++++"+(String)session.getAttribute("paymentId"));
                request.setAttribute("horseMemberId", horseMemberId);
                request.setAttribute("horseName", horseName);
                request.setAttribute("paymentId", paymentId);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("horseName1", horseName1);
                request.setAttribute("riderId", riderId);
                request.setAttribute("OwnerMailId", OwnerMailId);
                request.setAttribute("riderEmailId", riderEmailId);
                request.setAttribute("competitionName", competitionName);
                request.setAttribute("membershipName1", membershipName1);
                request.setAttribute("tempOwnerName", tempOwnerName);
                request.setAttribute("tempRiderName", tempRiderName);

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
                //Debug.print("Sucessfully Return objPaymentList:" + objPayDet.toString());
                objPayDet.setSslResult(sslResult);
                objPayDet.setSslResultMessage(sslResultMessage);
                objPayDet.setSslTxnId(sslTxnId);
                objPayDet.setSslApprovalCode(sslApprovalCode);
                objPayDet.setSslCvv2Response(sslCvv2Response);
                objPayDet.setSslAvsResponse(sslAvsResponse);
                objPayDet.setSslTransactionType(sslTransactionType);
                objPayDet.setSslInvoiceNo(sslInvoiceNo);
                objPayDet.setSslEmail(sslEmail);
                objPayDet.setPaymentId(paymentId);
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

                
               
                
                /*boolean resultPay = false;
                result = remote.editHorseRiderAndOwnerDetails(horseMemberId,horseName,horseRegisterName,userId,riderId,ownerUserId,false,objOwnerVO,StatusVal,paymentId,Float.parseFloat(totalAmount),serviceType);
                Debug.print("result:" + result);
                if(objPayDet!=null){
                    resultPay  = objPaySessRemote.createPayment(objPayDet);
                    Debug.print("resultPay:" +resultPay );
                    Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                }*/
                
               String tempPreRiderNames = "";
                String tempPreOwnerNames = "";

                String preOwnEmailId = "";
                String preOwnRelStat = "";
                String preRidEmailId = "";
                String preRidRelStat = "";
                String preOwnEmailId1 = "";
                String preRidEmailId1 = "";

                if (sslResult.equals("0")) {
                    boolean resultPay = false;
                    String requestId = remote.editHorseRiderAndOwnerDetails(horseMemberId, horseName, horseRegisterName, userId, riderId, ownerUserId, false, objOwnerVO, StatusVal, paymentId, Float.parseFloat(totalAmount), serviceType, reqValue);
                    Debug.print("requestId:" + requestId);

                    if (requestId != null && requestId.trim().length() != 0) {

                        ArrayList chngHorseDetails = remote.getHorseOwnRidChangeDetails(requestId);

                        String horseMemId = "";
                        String reqOwnerId = "";
                        String reqHorseName = "";
                        String reqHorseRegName = "";
                        if (chngHorseDetails != null && chngHorseDetails.size() != 0) {
                            Iterator itr = chngHorseDetails.iterator();
                            while (itr.hasNext()) {
                                String[] s = (String[]) itr.next();
                                horseMemId = s[0];
                                String payId = s[1];
                                reqOwnerId = s[2];
                                String reqRiderId = s[3];
                                reqHorseName = s[4];
                                reqHorseRegName = s[5];
                                String requestBy = s[6];
                                String reqAmt = s[7];
                                String reqStat = s[8];
                                String reqDt = s[9];
                                String addPayStat = s[10];

                            }

                        }

                        boolean output = remote.updateRequestHorseDetailsByAdmin(horseMemId, requestId, reqHorseName, reqHorseRegName, riderId, reqOwnerId, objOwnerVO, StatusVal, ridStatus, competitionName);
                        Debug.print("Final output:" + output);
                    }

                    String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                    boolean idExist = userremote.isInvoiceIdExist(sessionInvoiceId);
                    Debug.print("idExist value is:" + idExist);

                    if (!idExist) {
                         if(objPayDet != null) {
                            resultPay = objPaySessRemote.createPayment(objPayDet);
                           // Debug.print("resultPay value is:" + resultPay);
                            //Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                            session.setAttribute("sessionResultPay", "true");
                        }
                   }

                    if (horseStatusValue == true) {
                        String toMailIds[] = {emailId};
                        EmailContent email = new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@digiblitz.com");
                        email.setSubject("Horse Member Matching");
                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                " <tr>" +
                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                "<tr>" +
                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                "<span class=\"boldTxt\">Dear User</span>,<br /><br />" +
                                "<p>Your horse may already be registered.<p>" +
                                "<p>In an effort to reduce the number of duplicate horse registrations, the HLC " +
                                "would like to inform you that the horse name <strong>" + horseName + " </strong>is already in use in our database." +
                                "Please verify that your horse <strong>" + horseName + "</strong> has not be previously registered by a former owner or rider." +
                                "Please log in to the USEA Online Services, and access Horse Registration. Please search for the horse by name to determine if " +
                                "<strong>" + horseName + "</strong> has been registered before. If your horse has been registered in the past, please " +
                                "contact the HLC Sports Services Department at 703-779-0440.<p>" +
                                "<p>If you have confirmed that your horse has not been registered with the USEA previously, no further action is needed.<p><br />" +
                                "Thank You <br />" +
                                "<p>Sincerely," +
                                "<p>USEA Staff <p></td>" +
                                "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>" +
                                "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>" +
                                "</tr>" +
                                " </table>" +
                                "</td></tr>" +
                                "</table>";
                        email.setBody(content);
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :" + emailFlag);
                    }



                    String sessionResultPay = (String) session.getAttribute("sessionResultPay");
                    if (sessionResultPay.equalsIgnoreCase("true") && requestId != null && requestId.trim().length() != 0) {


                        String preOwnFName = "";
                        String preOwnLName = "";
                        String preRidFName = "";
                        String preRidLName = "";


                        ArrayList horseDetails = (ArrayList) remote.getHorseRelationDetails(horseMemberId);
                        String firstName1, last_name1, relationship_type_id1, relationship_type_name1 = "";

                        if (horseDetails != null && horseDetails.size() != 0) {
                            Iterator itr = horseDetails.iterator();
                            int k = 0;
                            int s = 0;
                            while (itr.hasNext()) {
                                String[] val = (String[]) itr.next();
                                firstName1 = val[0];
                                last_name1 = val[1];
                                relationship_type_id1 = val[3];
                                relationship_type_name1 = val[4];
                                if (relationship_type_id1 != null && relationship_type_id1.trim().length() != 0 && relationship_type_name1.equalsIgnoreCase("Previous Owner")) {

                                    preOwnFName = firstName1;
                                    preOwnLName = last_name1;
                                    if (k == 0) {
                                        tempPreOwnerNames = preOwnFName + " " + preOwnLName;
                                    //Debug.print("tempPreOwnerNames inside if condition in first ArrayList :" + tempPreOwnerNames);
                                    } else {
                                        tempPreOwnerNames = tempPreOwnerNames + "," + preOwnFName + " " + preOwnLName;
                                    //Debug.print("tempPreOwnerNames inside else condition in first ArrayList :" + tempPreOwnerNames);
                                    }
                                    k++;
                                } else if (relationship_type_id1 != null && relationship_type_id1.trim().length() != 0 && relationship_type_name1.equalsIgnoreCase("Previous Rider")) {

                                    preRidFName = firstName1;
                                    //Debug.print("preRidFName inside if condition :" + preRidFName);
                                    preRidLName = last_name1;
                                    //Debug.print("preRidLName::: inside if condition :" + preRidLName);

                                    if (s == 0) {
                                        tempPreRiderNames = preRidFName + " " + preRidLName;
                                    //Debug.print("tempPreRiderNames inside if condition in first ArrayList :" + tempPreRiderNames);
                                    } else {
                                        tempPreRiderNames = tempPreRiderNames + "," + preRidFName + " " + preRidLName;
                                    //Debug.print("tempPreRiderNames inside else condition in first ArrayList :" + tempPreRiderNames);
                                    }
                                    s++;
                                }
                            }

                        }

                        ArrayList HorseDisp = (ArrayList) remote.getHorseRelationDetails(horseMemberId);
                        String emailId1, relationship_status1, relationship_type_id2, relationship_type_name2, relation_id = "";

                        if (HorseDisp != null && HorseDisp.size() != 0) {
                            Iterator itr = HorseDisp.iterator();
                            int i = 0;
                            int j = 0;
                            while (itr.hasNext()) {
                                String[] val = (String[]) itr.next();
                                emailId1 = val[2];
                                relationship_type_id2 = val[3];
                                relationship_type_name2 = val[4];
                                relationship_status1 = val[5];
                                relation_id = val[6];
                                if (relationship_type_id2 != null && relationship_type_id2.trim().length() != 0 && relationship_type_name2.equalsIgnoreCase("Previous Owner")) {

                                    preOwnRelStat = relationship_status1;
                                    if (i == 0) {
                                        preOwnEmailId = emailId1;
                                    } else {
                                        preOwnEmailId1 = emailId1;
                                        preOwnRelStat = preOwnRelStat + "," + relationship_status1;
                                        Debug.print("preOwnEmailId1::: inside else condition :" + preOwnEmailId1);
                                        //Debug.print("tempPreRiderNames inside else condition :" + tempPreRiderNames);

                                        // for sending mail to previous Owner when Horse and Rider and Owner names have changed     
                                        if ((horseName1 != null && horseName1.trim().length() != 0) && (riderId != null && riderId.trim().length() != 0) && (OwnerMailId != null && OwnerMailId.trim().length() != 0)) {
                                            Debug.print("Inside the all three chage");
                                            EmailContent email = new EmailContent();
                                            String toMailIds[] = {preOwnEmailId1};
                                            email.setTo(toMailIds);
                                            email.setFrom("info@digiblitz.com");
                                            email.setSubject("Horse/Owner/Rider Name change");
                                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                                    " <tr>" +
                                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                                    "<tr>" +
                                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                                    "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                                    "<p>The Rider,Owner and Horse information have been updated for <strong>" + competitionName + "</strong>.<p>" +
                                                    "<p>Please retain the following information for your records:<p>" +
                                                    "Current Non-Human Name:  " + horseName1 + "<br />" +
                                                    "Past Name: " + competitionName + "<br />" +
                                                    "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                                    "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                                    "Current Owner(s):" + tempOwnerName + "<br/>" +
                                                    "Current Rider(s):" + tempRiderName + "<br/>" +
                                                    "Previous Owner(s):" + tempPreOwnerNames + "<br/>" +
                                                    "Previous Rider(s):" + tempPreRiderNames + "<br/>" +
                                                    "<p>Additional information regarding <strong>" + horseName1 + "</strong> can be viewed by logging in to the USEA Online Services. Thank you.<p>" +
                                                    "<p>Sincerely, <br />" +
                                                    "<p>HLC Staff <p>" +
                                                    "</tr>" +
                                                    " </table>" +
                                                    "</td></tr>" +
                                                    "</table>";
                                            email.setBody(content);
                                            EmailEngine emailEngine = new EmailEngine();
                                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                                            Debug.print("Email sent sucessfully :" + emailFlag);

                                        }

                                    }
                                    i++;
                                } else if (relationship_type_id2 != null && relationship_type_id2.trim().length() != 0 && relationship_type_name2.equalsIgnoreCase("Previous Rider")) {
                                    preRidRelStat = relationship_status1;

                                    if (j == 0) {
                                        preRidEmailId = emailId1;
                                        Debug.print("preRidEmailId::: inside if condition :" + preRidEmailId);
                                    } else {

                                        preRidRelStat = preRidRelStat + "," + relationship_status1;
                                        preRidEmailId1 = emailId1;
                                        //Debug.print("preRidEmailId1::: inside else condition :" + preRidEmailId1);

                                        // for sending mail to previous Rider when Horse and Rider and Owner names have changed       
                                        if ((horseName1 != null && horseName1.trim().length() != 0) && (riderId != null && riderId.trim().length() != 0) && (OwnerMailId != null && OwnerMailId.trim().length() != 0)) {
                                            Debug.print("Inside the all three chage in else");
                                            EmailContent email = new EmailContent();
                                            String toMailIds[] = {preRidEmailId1};
                                            email.setTo(toMailIds);
                                            email.setFrom("info@digiblitz.com");
                                            email.setSubject("Horse/Owner/Rider Name change");
                                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                                    " <tr>" +
                                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                                    "<tr>" +
                                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                                    "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                                    "<p>The Rider,Owner and Non-Human information have been updated for <strong>" + competitionName + "</strong>.<p>" +
                                                    "<p>Please retain the following information for your records:<p>" +
                                                    "Current Non-Human Name:  " + horseName1 + "<br />" +
                                                    "Past Name: " + competitionName + "<br />" +
                                                    "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                                    "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                                    "Current Owner(s):" + tempOwnerName + "<br/>" +
                                                    "Current Rider(s):" + tempRiderName + "<br/>" +
                                                    "Previous Owner(s):" + tempPreOwnerNames + "<br/>" +
                                                    "Previous Rider(s):" + tempPreRiderNames + "<br/>" +
                                                    "<p>Additional information regarding <strong>" + horseName1 + "</strong> can be viewed by logging in to the USEA Online Services. Thank you.<p>" +
                                                    "<p>Sincerely, <br />" +
                                                    "<p>HLC Staff <p>" +
                                                    "</tr>" +
                                                    " </table>" +
                                                    "</td></tr>" +
                                                    "</table>";
                                            email.setBody(content);
                                            EmailEngine emailEngine = new EmailEngine();
                                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                                            Debug.print("Email sent sucessfully :" + emailFlag);
                                        }


                                    }
                                    j++;
                                }

                            }
                        }
                        // for sending mail to new owner and Rider, previous Owner and Rider when Horse and Rider and Owner names have changed

                        if ((horseName1 != null && horseName1.trim().length() != 0) && (riderId != null && riderId.trim().length() != 0) && (OwnerMailId != null && OwnerMailId.trim().length() != 0)) {
                            Debug.print("Inside the all three chage");
                            if (OwnerMailId != null && OwnerMailId.trim().length() != 0 && preOwnEmailId != null && preOwnEmailId.trim().length() != 0 && riderEmailId != null && riderEmailId.trim().length() != 0 && preRidEmailId != null && preRidEmailId.trim().length() != 0) {
                                EmailContent email = new EmailContent();
                                String toMailIds[] = {OwnerMailId, preOwnEmailId, riderEmailId, preRidEmailId};
                                email.setTo(toMailIds);
                                email.setFrom("info@digiblitz.com");
                                email.setSubject("Horse/Owner/Rider Name change");
                                String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                        " <tr>" +
                                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                        "<tr>" +
                                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                        "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                        "<p>The Rider,Owner and Non-Human information have been updated for <strong>" + competitionName + "</strong>.<p>" +
                                        "<p>Please retain the following information for your records:<p>" +
                                        "Current Non-Human Name:  " + horseName1 + "<br />" +
                                        "Past Name: " + competitionName + "<br />" +
                                        "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                        "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                        "Current Owner(s):" + tempOwnerName + "<br/>" +
                                        "Current Rider(s):" + tempRiderName + "<br/>" +
                                        "Previous Owner(s):" + tempPreOwnerNames + "<br/>" +
                                        "Previous Rider(s):" + tempPreRiderNames + "<br/>" +
                                        "<p>Additional information regarding <strong>" + horseName1 + "</strong> can be viewed by logging in to the HLC Online Services. Thank you.<p>" +
                                        "<p>Sincerely, <br />" +
                                        "<p>HLC Staff <p>" +
                                        "</tr>" +
                                        " </table>" +
                                        "</td></tr>" +
                                        "</table>";
                                email.setBody(content);
                                EmailEngine emailEngine = new EmailEngine();
                                boolean emailFlag = emailEngine.sendMimeEmail(email);
                                Debug.print("Email sent sucessfully :" + emailFlag);


                            }
                        } // for sending mail to new Owner and Rider when only Horse name has changed 
                        else if (horseName1 != null && horseName1.length() != 0) {
                            if (commonOwnersRiders != null && commonOwnersRiders.size() != 0) {
                                Debug.print("commonOwnersRiders::: &&&&" + commonOwnersRiders);
                                Iterator itr = commonOwnersRiders.iterator();
                                int i = 0;
                                while (itr.hasNext()) {
                                    Debug.print("Inside While");
                                    HLCHorseRegListVO objOwnerInfo = (HLCHorseRegListVO) itr.next();
                                    String relaTypeName1 = objOwnerInfo.getRelationTypeName();
                                    Debug.print("relaTypeName1 :" + relaTypeName1);
                                    if ((relaTypeName1 != null && relaTypeName1.equalsIgnoreCase("Owner")) || (relaTypeName1 != null && relaTypeName1.equalsIgnoreCase("Rider"))) {
                                        String OREmailId = objOwnerInfo.getPreEmail();
                                        Debug.print("OREmailId :" + OREmailId);
                                        String toMailIds[] = {OREmailId};
                                        EmailContent email = new EmailContent();
                                        email.setTo(toMailIds);
                                        email.setFrom("info@digiblitz.com");
                                        email.setSubject("Non-Human Name Change");
                                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                                " <tr>" +
                                                " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                                " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                                "<tr>" +
                                                "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                                "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                                "<p>Thank you for updating the Non-Human name for <strong>" + horseName1 + "</strong>.<p>" +
                                                "<p>Please retain the following information for your records:<p>" +
                                                "Current Non-Human Name:  " + horseName1 + "<br />" +
                                                "Past Name: " + competitionName + "<br />" +
                                                "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                                "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                                "Owner(s):" + tempOwnerName + "<br/>" +
                                                "Rider(s):" + tempRiderName + "<br/>" +
                                                "<p>Additional information regarding <strong>" + horseName1 + " </strong>can be viewed by logging in to the USEA Online Services.<p>" +
                                                "<p>Thank you.<p><br />" +
                                                "<p>Sincerely, <br />" +
                                                "<p>HLC Staff <p>" +
                                                "</tr>" +
                                                " </table>" +
                                                "</td></tr>" +
                                                "</table>";
                                        email.setBody(content);
                                        EmailEngine emailEngine = new EmailEngine();
                                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                                        Debug.print("Email sent sucessfully :" + emailFlag);
                                        Debug.print("Email sent sucessfully :" + i);
                                        i++;
                                    }
                                }
                            }
                        } //String tempOwnerMailId="";
                        //if(newOwnerMailId!=null && newOwnerMailId.trim().length()!=0){
                        //tempOwnerMailId=newOwnerMailId;
                        // }
                        //if(OwnerMailId!=null && OwnerMailId.trim().length()!=0){
                        //tempOwnerMailId=OwnerMailId;
                        //}
                        // for sending mail to new owner and rider when Owner/rider or both Owner and rider name(s) changed 
                        else if (riderId != null && riderId.trim().length() != 0 && horseName1.equals("") && OwnerMailId.equals("") || riderId != null && riderId.trim().length() != 0 && !(horseName1.equals("")) && OwnerMailId.equals("") || riderId != null && riderId.trim().length() != 0 && horseName1.equals("") && !(OwnerMailId.equals("")) || OwnerMailId != null && OwnerMailId.trim().length() != 0 && horseName1.equals("") && riderId.equals("") || OwnerMailId != null && OwnerMailId.trim().length() != 0 && horseName1.equals("") && !(riderId.equals("")) || OwnerMailId != null && OwnerMailId.trim().length() != 0 && !(horseName1.equals("")) && riderId.equals("")) {
                            Debug.print("OwnerMailId::: inside if condition" + OwnerMailId);
                            EmailContent email = new EmailContent();
                            if ((OwnerMailId != null && OwnerMailId.length() != 0) && (riderEmailId != null && riderEmailId.length() != 0)) {
                                String toMailIds[] = {OwnerMailId, riderEmailId};
                                email.setTo(toMailIds);
                            } else if (OwnerMailId != null && OwnerMailId.length() != 0) {
                                String toMailIds[] = {OwnerMailId};
                                email.setTo(toMailIds);
                            } else if (riderEmailId != null && riderEmailId.length() != 0) {
                                String toMailIds[] = {riderEmailId};
                                email.setTo(toMailIds);
                            }
                            String tempHorseName1 = "";
                            if (horseName1 != null && horseName1.trim().length() != 0 && !(horseName1.equals(""))) {
                                tempHorseName1 = horseName1;
                            } else if (competitionName != null && competitionName.trim().length() != 0) {
                                tempHorseName1 = competitionName;
                            }
                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Non-Human Change Rider/Owner");
                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                    " <tr>" +
                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                    "<tr>" +
                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                    "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                    "<p>Thank you for updating the rider and/or owner information for <strong>" + tempHorseName1 + "</strong>.<p>" +
                                    "<p>Please retain the following information for your records:<p>" +
                                    "Non-Human Name: " + tempHorseName1 + "<br />" +
                                    "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                    "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                    "Owner(s):" + tempOwnerName + "<br/>" +
                                    "Rider(s):" + tempRiderName + "<br/>" +
                                    "<p>Additional information regarding <strong>" + tempHorseName1 + " </strong>can be viewed by logging in to the USEA Online Services.<p>" +
                                    "<p>Thank you.<p><br />" +
                                    "<p>Sincerely, <br />" +
                                    "<p>HLC Staff <p>" +
                                    "</tr>" +
                                    " </table>" +
                                    "</td></tr>" +
                                    "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :" + emailFlag);

                        }
// for sending mail to previous owner when Owner/rider or both Owner and rider name(s) changed 
                        if (HorseDisp != null && HorseDisp.size() != 0) {
                            Iterator itr = HorseDisp.iterator();
                            int g = 0;
                            int h = 0;
                            while (itr.hasNext()) {
                                String[] val = (String[]) itr.next();
                                emailId1 = val[2];
                                relationship_type_id2 = val[3];
                                relationship_type_name2 = val[4];
                                relationship_status1 = val[5];
                                relation_id = val[6];
                                if (relationship_type_id2 != null && relationship_type_id2.trim().length() != 0 && relationship_type_name2.equalsIgnoreCase("Previous Owner")) {
                                    preOwnRelStat = relationship_status1;
                                    if (g == 0) {
                                        preOwnEmailId = emailId1;
                                    } else {
                                        preOwnEmailId1 = emailId1;
                                        preOwnRelStat = preOwnRelStat + "," + relationship_status1;
                                        //Debug.print("preOwnEmailId1::: inside else condition :" + preOwnEmailId1);
                                        //Debug.print("tempPreRiderNames inside else condition :" + tempPreRiderNames);

                                        if (riderId != null && riderId.trim().length() != 0 && horseName1.equals("") || OwnerMailId != null && OwnerMailId.trim().length() != 0 && horseName1.equals("")) {
                                            //Debug.print("riderEmailId::: inside if condition :" + preRidEmailId);
                                            //Debug.print("OwnerMailId::: inside if condition :" + preOwnEmailId);
                                            EmailContent email = new EmailContent();

                                            if (preOwnEmailId1 != null && preOwnEmailId1.length() != 0) {
                                                String toMailIds[] = {preOwnEmailId1};
                                                email.setTo(toMailIds);
                                            }
                                            String tempHorseName = "";
                                            if (horseName1 != null && horseName1.trim().length() != 0 && !(horseName1.equals(""))) {
                                                tempHorseName = horseName1;
                                            } else if (competitionName != null && competitionName.trim().length() != 0) {
                                                tempHorseName = competitionName;
                                            }

                                            email.setFrom("info@digiblitz.com");
                                            email.setSubject("Non-Human Change Rider/Owner");
                                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                                    " <tr>" +
                                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                                    "<tr>" +
                                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                                    "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                                    "<p>The rider and/or owner information has been updated for <strong>" + tempHorseName + "</strong>.<p>" +
                                                    "<p>Please retain the following information for your records:<p>" +
                                                    "Non-Human Name: " + tempHorseName + "<br />" +
                                                    "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                                    "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                                    "Current Owner(s):" + tempOwnerName + "<br/>" +
                                                    "Current Rider(s):" + tempRiderName + "<br/>" +
                                                    "Previous Owner(s):" + tempPreOwnerNames + "<br/>" +
                                                    "Previous Rider(s):" + tempPreRiderNames + "<br/>" +
                                                    "<p>Additional information regarding <strong>" + tempHorseName + "</strong> can be viewed by logging in to the USEA Online Services. Thank you.<p>" +
                                                    "<p>Sincerely, <br />" +
                                                    "<p>HLC Staff <p>" +
                                                    "</tr>" +
                                                    " </table>" +
                                                    "</td></tr>" +
                                                    "</table>";
                                            email.setBody(content);
                                            EmailEngine emailEngine = new EmailEngine();
                                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                                            Debug.print("Email sent sucessfully :" + emailFlag);
                                        }

                                    }
                                    g++;
                                } // for sending mail to previous rider when Owner/rider or both Owner and rider name(s) changed                                                              
                                else if (relationship_type_id2 != null && relationship_type_id2.trim().length() != 0 && relationship_type_name2.equalsIgnoreCase("Previous Rider")) {
                                    preRidRelStat = relationship_status1;

                                    if (h == 0) {
                                        preRidEmailId = emailId1;
                                    //Debug.print("preRidEmailId::: inside if condition :" + preRidEmailId);
                                    } else {
                                        preRidRelStat = preRidRelStat + "," + relationship_status1;
                                        preRidEmailId1 = emailId1;
                                        //Debug.print("preRidEmailId1::: inside else condition :" + preRidEmailId1);
                                        if (riderId != null && riderId.trim().length() != 0 && horseName1.equals("") || OwnerMailId != null && OwnerMailId.trim().length() != 0 && horseName1.equals("")) {
                                            Debug.print("riderEmailId::: inside if condition :" + preRidEmailId1);
                                            EmailContent email = new EmailContent();
                                            if (preRidEmailId1 != null && preRidEmailId1.length() != 0) {
                                                String toMailIds[] = {preRidEmailId1};
                                                email.setTo(toMailIds);
                                            }
                                            String tempHorseName = "";
                                            if (horseName1 != null && horseName1.trim().length() != 0 && !(horseName1.equals(""))) {
                                                tempHorseName = horseName1;
                                            } else if (competitionName != null && competitionName.trim().length() != 0) {
                                                tempHorseName = competitionName;
                                            }

                                            email.setFrom("info@digiblitz.com");
                                            email.setSubject("Non-Human Change Rider/Owner");
                                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                                    " <tr>" +
                                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                                    "<tr>" +
                                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                                    "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                                    "<p>The rider and/or owner information has been updated for <strong>" + tempHorseName + "</strong>.<p>" +
                                                    "<p>Please retain the following information for your records:<p>" +
                                                    "Non-Human Name: " + tempHorseName + "<br />" +
                                                    "Non-Human Registration ID:" + horseMemberId + "<br />" +
                                                    "Non-Human Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                                    "Current Owner(s):" + tempOwnerName + "<br/>" +
                                                    "Current Rider(s):" + tempRiderName + "<br/>" +
                                                    "Previous Owner(s):" + tempPreOwnerNames + "<br/>" +
                                                    "Previous Rider(s):" + tempPreRiderNames + "<br/>" +
                                                    "<p>Additional information regarding <strong>" + tempHorseName + "</strong> can be viewed by logging in to the USEA Online Services. Thank you.<p>" +
                                                    "<p>Sincerely, <br />" +
                                                    "<p>HLC Staff <p>" +
                                                    "</tr>" +
                                                    " </table>" +
                                                    "</td></tr>" +
                                                    "</table>";
                                            email.setBody(content);
                                            EmailEngine emailEngine = new EmailEngine();
                                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                                            Debug.print("Email sent sucessfully :" + emailFlag);

                                        }
                                    }
                                    h++;
                                }

                            }
                        }
                        // for sending mail to previous owner and rider when Owner/rider or both Owner and rider name(s) changed         
                        if (riderId != null && riderId.trim().length() != 0 && horseName1.equals("") || OwnerMailId != null && OwnerMailId.trim().length() != 0 && horseName1.equals("") || riderId != null && riderId.trim().length() != 0 && !(horseName1.equals("")) || OwnerMailId != null && OwnerMailId.trim().length() != 0 && !(horseName1.equals(""))) {
                            Debug.print("riderEmailId::: inside if condition :" + preRidEmailId);
                            Debug.print("OwnerMailId::: inside if condition :" + preOwnEmailId);
                            EmailContent email = new EmailContent();

                            if ((preOwnEmailId != null && preOwnEmailId.length() != 0) && (preRidEmailId != null && preRidEmailId.length() != 0)) {
                                String toMailIds[] = {preOwnEmailId, preRidEmailId};
                                email.setTo(toMailIds);
                            } else if (preOwnEmailId != null && preOwnEmailId.length() != 0) {
                                String toMailIds[] = {preOwnEmailId};
                                email.setTo(toMailIds);
                            } else if (preRidEmailId != null && preRidEmailId.length() != 0) {
                                String toMailIds[] = {preRidEmailId};
                                email.setTo(toMailIds);
                            }
                            String tempHorseName = "";
                            if (horseName1 != null && horseName1.trim().length() != 0 && !(horseName1.equals(""))) {
                                tempHorseName = horseName1;
                            } else if (competitionName != null && competitionName.trim().length() != 0) {
                                tempHorseName = competitionName;
                            }

                            email.setFrom("info@digiblitz.com");
                            email.setSubject("Non-Human Change Rider/Owner");
                            String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                                    " <tr>" +
                                    " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                                    " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                                    "<tr>" +
                                    "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"HLC Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                                    "<span class=\"boldTxt\">Dear Member,</span><br />" +
                                    "<p>The rider and/or owner information has been updated for <strong>" + tempHorseName + "</strong>.<p>" +
                                    "<p>Please retain the following information for your records:<p>" +
                                    "Horse Name: " + tempHorseName + "<br />" +
                                    "Horse Registration ID:" + horseMemberId + "<br />" +
                                    "Horse Registration level:" + membershipName1 + ".This is a lifetime registration.<br/>" +
                                    "Current Owner(s):" + tempOwnerName + "<br/>" +
                                    "Current Rider(s):" + tempRiderName + "<br/>" +
                                    "Previous Owner(s):" + tempPreOwnerNames + "<br/>" +
                                    "Previous Rider(s):" + tempPreRiderNames + "<br/>" +
                                    "<p>Additional information regarding <strong>" + tempHorseName + "</strong> can be viewed by logging in to the USEA Online Services. Thank you.<p>" +
                                    "<p>Sincerely, <br />" +
                                    "<p>HLC Staff <p>" +
                                    "</tr>" +
                                    " </table>" +
                                    "</td></tr>" +
                                    "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :" + emailFlag);

                        }

                        session.setAttribute("totalAmount", totalAmount);
                        request.setAttribute("horseMemberId", horseMemberId);
                       /* session.setAttribute("horseMemberId", null);

                        session.setAttribute("objPaymentList", null);
                        session.setAttribute("totalAmount", null);
                        session.setAttribute("objOwnerVO", null);
                        session.setAttribute("riderId", null);
                        session.setAttribute("status", null);
                        session.setAttribute("ownerUserId", null);
                        session.setAttribute("horseName", null);
                        session.setAttribute("horseRegisterName", null);*/
                        
                     if (!idExist) {
                        if (txnDetails != null) {
                            HLCAccTransactionVO accTxn = null;
                            Iterator it=txnDetails.iterator();
                           // for (int i = 0; i < txnDetails.size(); i++) {
                              //  accTxn = new AccTransactionVO();
                               // accTxn = (AccTransactionVO) txnDetails.get(i);
                            while(it.hasNext()){
                                accTxn = (HLCAccTransactionVO) it.next();
                                accTxn.setActive_status(true);
                            }
                                boolean stat = mahaRemote.insertAccountTxnDetails(accTxn);
                                Debug.print("insertAccountTxnDetails(accTxn) for payment sucess status for rec no :" + stat);
                           // }
                        }
                     }
                        return mapping.findForward("confirm");
                    }

                } else {
                    Debug.print("Not Inserted:");
                    Debug.print("horsememId :" + horsememId);

                    request.setAttribute("totalAmount", totalAmount);
                    request.setAttribute("horseMemberId", horseMemberId);
                    request.setAttribute("horseName", horseName);
                    request.setAttribute("paymentId", paymentId);

                    request.setAttribute("horseName1", horseName1);
                    request.setAttribute("riderId", riderId);
                    request.setAttribute("OwnerMailId", OwnerMailId);
                    request.setAttribute("riderEmailId", riderEmailId);
                    request.setAttribute("competitionName", competitionName);
                    request.setAttribute("membershipName1", membershipName1);
                    request.setAttribute("tempOwnerName", tempOwnerName);
                    request.setAttribute("tempRiderName", tempRiderName);

                    session.setAttribute("totalAmount", null);
                    session.setAttribute("horseMemberId", null);
                    session.setAttribute("horseName", null);
                    session.setAttribute("horseRegisterName", null);
                    session.setAttribute("ownerUserId", null);
                    session.setAttribute("riderId", null);
                   
                    if (txnDetails != null) {
                        HLCAccTransactionVO accTxn = new HLCAccTransactionVO();
                        for (int i = 0; i < txnDetails.size(); i++) {
                            accTxn = new HLCAccTransactionVO();
                            accTxn = (HLCAccTransactionVO) txnDetails.get(i);
                            accTxn.setActive_status(false);

                            boolean stat = mahaRemote.insertAccountTxnDetails(accTxn);
                            Debug.print("insertAccountTxnDetails(accTxn) for payment declined status for rec no " + i + " :" + stat);
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
