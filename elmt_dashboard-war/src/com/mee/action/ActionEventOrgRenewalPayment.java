/*
 * ActionEventOrgRenewalPayment.java
 *
 * Created on October 13, 2006, 1:45 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.mee.action;

/**
 *
 * @author suresh
 */
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.mee.actionform.FormEventOrgRenewal;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import javax.naming.InitialContext;
import javax.naming.Context;
import com.hlcmro.util.*;
import com.hlcmro.org.*;
import java.util.Properties;
import java.text.*;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlccommon.util.HLCPaymentResultVO;
import com.hlcevent.edu.HLCEducationalSessionRemote;
import com.hlcevent.edu.HLCEducationalSessionRemoteHome;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcmro.renewal.*;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;

public class ActionEventOrgRenewalPayment extends Action {

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {
            Properties p = new Properties();
            p.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
            p.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
            p.setProperty("java.naming.provider.url", "");

            Context jndiContext = new InitialContext(p);
            Object obj = jndiContext.lookup("ejb/HLCVaigaiSessionBean");
            HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome) javax.rmi.PortableRemoteObject.narrow(obj, HLCVaigaiSessionRemoteHome.class);
            HLCVaigaiSessionRemote remote = home.create();
            
               Object objPayss= jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
            HttpSession session = request.getSession(false);
            
              Object objEdu=jndiContext.lookup("ejb/HLCEducationalSessionBean");
                     HLCEducationalSessionRemoteHome eduHome = (HLCEducationalSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objEdu,HLCEducationalSessionRemoteHome.class);
                    HLCEducationalSessionRemote eduRemote = eduHome.create();

                     Object objHuman=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
                    HLCkaverystatelessRemoteHome humanHome = (HLCkaverystatelessRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(objHuman,HLCkaverystatelessRemoteHome.class);
                    HLCkaverystatelessRemote humanRemote = humanHome.create();
                    
                     Object maha=jndiContext.lookup("ejb/HLCMahanadhiSessionBean");
                    HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
                    javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
                    HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();
                    
                    
                   HLCAccTxnTypeDetailVO transTxnDet = new HLCAccTxnTypeDetailVO();
            
            FormEventOrgRenewal endForm = (FormEventOrgRenewal) form;
            HLCRenewalOrganizerDetails objRenewalDet = new HLCRenewalOrganizerDetails();
            HLCPaymentDetails objPayDet = new HLCPaymentDetails();

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            Debug.print("Return from Paypal");
            HLCAccTransactionVO PhaccTxnVO = new HLCAccTransactionVO();
                objRenewalDet = (HLCRenewalOrganizerDetails) session.getAttribute("objRenewalDet");
                //objPayDet = (PaymentDetails) session.getAttribute("objPaymentList");
                //String eveTitle= String.valueOf(objRenewalDet.getCompetitionName());

                Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
                Debug.print("Sucessfully return from Nova objActDet:Size" + objRenewalDet.getCompetitionName());
                //Debug.print("Sucessfully return from Nova objPayDet:" + objPayDet);

  
            HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            System.out.println("payRes in card servlet: " + payRes);
            System.out.println("results in card servlet: " + results);
            System.out.println("statusId3 in card servlet: " + statusId3);
            //System.out.println("statusId in card servlet: " + statusId);
              if (statusId3 == null || statusId3.equalsIgnoreCase("Failure")) {
                String errorCd = payRes.getErrorCd();
                String lngMsg = payRes.getLngMsg();

                String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {"payments@useventing.com"};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("dashboard@useventing.com");
                email.setSubject("Event Registration Decline");
                //System.out.println("toMailIds: " + toMailIds);
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
                       // "CVV2MATCH:" + payRes.getSslCvv2Response() + "<br/>" +
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

                HLCPaymentDetailVO objPay = new HLCPaymentDetailVO();
                
                objPay.setAmount(objPayDet.getAmount());
                
                session.setAttribute("objPayDet",objPay);
                
                String amount1 = (String) session.getAttribute("amount");
                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);
                request.setAttribute("price",amount1);
                request.setAttribute("eventId",(String)session.getAttribute("EventID"));
                request.setAttribute("eveTitle",(String)session.getAttribute("EventTitle"));
                return mapping.findForward("frmMeeRenewalSuccess");
            }

            String sslResult = objPaypal.getSslResult();
                String sslResultMessage = objPaypal.getSslResultMessage();
                String sslTxnId = objPaypal.getSslTxnId();
                String sslApprovalCode = request.getParameter("ssl_approval_code");
                String sslCvv2Response = objPaypal.getSslCvv2Response();
                String sslAvsResponse = objPaypal.getSslAvsResponse();
                String sslTransactionType = objPaypal.getSslTransactionType();
                String sslInvoiceNo = objPaypal.getSslInvoiceNo();
                String sslEmail = objPaypal.getSslEmail();

            Debug.print("Sucessfully return from Nova sslResult:" + sslResult);
            Debug.print("Sucessfully return from Nova sslResultMessage:" + sslResultMessage);
            Debug.print("Sucessfully return from Nova sslTxnId:" + sslTxnId);
            Debug.print("Sucessfully return from Nova sslApprovalCode:" + sslApprovalCode);
            Debug.print("Sucessfully return from Nova sslCvv2Response:" + sslCvv2Response);
            Debug.print("Sucessfully return from Nova sslAvsResponse:" + sslAvsResponse);
            Debug.print("Sucessfully return from Nova sslTransactionType:" + sslTransactionType);
            Debug.print("Sucessfully return from Nova sslInvoiceNo:" + sslInvoiceNo);
            Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);    
            
            Debug.print("Sucessfully Return objPaymentList:" + objPayDet.toString());
            Debug.print("paymentId in Paypal servlet :"+objPayDet.getPaymentId());

            if(sslResult.equals("0")){
                    if(objRenewalDet!=null && objPayDet!=null){
                        objPayDet.setSslResult(sslResult);
                        objPayDet.setSslResultMessage(sslResultMessage);
                        objPayDet.setSslTxnId(sslTxnId);
                        objPayDet.setSslApprovalCode(sslApprovalCode);
                        objPayDet.setSslCvv2Response(sslCvv2Response);
                        objPayDet.setSslAvsResponse(sslAvsResponse);
                        objPayDet.setSslTransactionType(sslTransactionType);
                        objPayDet.setSslInvoiceNo(sslInvoiceNo);
                        objPayDet.setSslEmail(sslEmail);
                        Debug.print("Activity Organizer  Sucessfully Getting Started:");
                        if(sslResult.equals("0")){
                          objRenewalDet.setRequestStat("Approved");  
                        }else{
                          objRenewalDet.setRequestStat("Pending");  
                        }
                        
                        //remote.addRenewal(objRenewalDet,objPayDet);
                        //String paymentId=null;
                        
                     String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                        boolean idExist = humanRemote.isInvoiceIdExist(sessionInvoiceId);
                        Debug.print("idExist value is:" + idExist);
                          if (!idExist) {
                        boolean insertResult=false;
                        boolean payResult=objPaySessRemote.createPayment(objPaypal);
                        Debug.print("paymentId in servlet"+objPaypal.getPaymentId());
                       
                        if(payResult==true && objPaypal.getPaymentId()!=null && objPaypal.getPaymentId().trim().length()!=0){
                        insertResult=remote.insertEndorseRenDetails(objRenewalDet,objPaypal.getPaymentId());    
                        }
                        Debug.print("Sucessfully Inserted................"+insertResult);
                          }
                  
                   String transacTypId1 = (String)session.getAttribute("transacTypId1");
                   String transacTypId2 = (String)session.getAttribute("transacTypId2");
                   String transacTypId3 = (String)session.getAttribute("transacTypId3");
                   
                   String evePrice1 = (String)session.getAttribute("evePrice1");
                   String evePrice2 = (String)session.getAttribute("evePrice2");
                   String evePrice3 = (String)session.getAttribute("evePrice3");
                   
                  if(transacTypId1!=null){                                                             
                 transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId1);
                 //transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId2);                         
                //transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId3);
              
               String accNo = transTxnDet.getAccount_no();
                String accClassname = transTxnDet.getClass_name();
                String accItemNo = transTxnDet.getItem_no();
                String accAccNo = transTxnDet.getSub_account_no();
                String accTranName = transTxnDet.getTransaction_name();
                String accTyped = transTxnDet.getTransaction_type();
                String accTypeId = transTxnDet.getTransaction_type_id();
                
                Debug.print("Inside Phone Account No "+accNo);
                Debug.print("Inside Phone Family Class Name "+accClassname);
                Debug.print("Inside Phone Item No "+accItemNo);
                Debug.print("Inside Phone Sub-Account No "+accAccNo);
                Debug.print("Inside Phone Transaction Name "+accTranName);
                Debug.print("Inside Phone Transaction Type "+accTyped);
                Debug.print("Inside Phone Transaction Type Id "+accTypeId);
                               
               
                    String cardselect = (String)session.getAttribute("cardselect");                  
                    PhaccTxnVO.setPayment_mode(cardselect);
                    //PhaccTxnVO.setActive_status(false);
                
                PhaccTxnVO.setSub_account_no(accAccNo);
                PhaccTxnVO.setPayment_id(objPaypal.getPaymentId());
                PhaccTxnVO.setAccount_type("Events Income");
                PhaccTxnVO.setClass_Typ(accClassname);
                
                PhaccTxnVO.setAccount_no(accNo);
                PhaccTxnVO.setItem_no(accItemNo);               
                PhaccTxnVO.setDescription(accTranName);
                PhaccTxnVO.setAmount(Float.parseFloat(evePrice1));
                
                if(sslResult.equals("0")){
                  if(PhaccTxnVO!=null){
                        PhaccTxnVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO); 
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+status);
                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(objPaypal.getPaymentId());           
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+Update_Status);
                        }           
                }
                /*
                else if(sslResult.equals("1")){
                     if(PhaccTxnVO!=null){
                        PhaccTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);                                  
                        }    
                }  */             
                  } 
                  if(transacTypId2!=null){
                 
                      transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId2);                                        
                //transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId3);
              
               String accNo = transTxnDet.getAccount_no();
                String accClassname = transTxnDet.getClass_name();
                String accItemNo = transTxnDet.getItem_no();
                String accAccNo = transTxnDet.getSub_account_no();
                String accTranName = transTxnDet.getTransaction_name();
                String accTyped = transTxnDet.getTransaction_type();
                String accTypeId = transTxnDet.getTransaction_type_id();
                
                Debug.print("Inside Phone Account No "+accNo);
                Debug.print("Inside Phone Family Class Name "+accClassname);
                Debug.print("Inside Phone Item No "+accItemNo);
                Debug.print("Inside Phone Sub-Account No "+accAccNo);
                Debug.print("Inside Phone Transaction Name "+accTranName);
                Debug.print("Inside Phone Transaction Type "+accTyped);
                Debug.print("Inside Phone Transaction Type Id "+accTypeId);
                               
               
                    String cardselect = (String)session.getAttribute("cardselect");                 
                    PhaccTxnVO.setPayment_mode(cardselect);
                    //PhaccTxnVO.setActive_status(false);
                
                PhaccTxnVO.setSub_account_no(accAccNo);
                PhaccTxnVO.setPayment_id(objPaypal.getPaymentId());
                PhaccTxnVO.setAccount_type("Events Income");
                PhaccTxnVO.setClass_Typ(accClassname);
                
                PhaccTxnVO.setAccount_no(accNo);
                PhaccTxnVO.setItem_no(accItemNo);               
                PhaccTxnVO.setDescription(accTranName);
                PhaccTxnVO.setAmount(Float.parseFloat(evePrice2));
                
                if(sslResult.equals("0")){
                  if(PhaccTxnVO!=null){
                        PhaccTxnVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);            
                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(objPaypal.getPaymentId());           
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+status);
                        }           
                }
                /*else if(sslResult.equals("1")){
                     if(PhaccTxnVO!=null){
                        PhaccTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);                                  
                        }    
                }  */  
       
                  }
               if(transacTypId3!=null){
                 
                      transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId3);                                        
                //transTxnDet = mahaRemote.getAccTransacTypDetsForEveOrgEndorsed(transacTypId3);
              
               String accNo = transTxnDet.getAccount_no();
                String accClassname = transTxnDet.getClass_name();
                String accItemNo = transTxnDet.getItem_no();
                String accAccNo = transTxnDet.getSub_account_no();
                String accTranName = transTxnDet.getTransaction_name();
                String accTyped = transTxnDet.getTransaction_type();
                String accTypeId = transTxnDet.getTransaction_type_id();
                
                Debug.print("Inside Phone Account No "+accNo);
                Debug.print("Inside Phone Family Class Name "+accClassname);
                Debug.print("Inside Phone Item No "+accItemNo);
                Debug.print("Inside Phone Sub-Account No "+accAccNo);
                Debug.print("Inside Phone Transaction Name "+accTranName);
                Debug.print("Inside Phone Transaction Type "+accTyped);
                Debug.print("Inside Phone Transaction Type Id "+accTypeId);
                               
               
                    String cardselect = (String)session.getAttribute("cardselect");                 
                    PhaccTxnVO.setPayment_mode(cardselect);
                    //PhaccTxnVO.setActive_status(false);
                
                PhaccTxnVO.setSub_account_no(accAccNo);
                PhaccTxnVO.setPayment_id(objPaypal.getPaymentId());
                PhaccTxnVO.setAccount_type("Events Income");
                PhaccTxnVO.setClass_Typ(accClassname);
                
                PhaccTxnVO.setAccount_no(accNo);
                PhaccTxnVO.setItem_no(accItemNo);               
                PhaccTxnVO.setDescription(accTranName);
                PhaccTxnVO.setAmount(Float.parseFloat(evePrice3));
                
                if(sslResult.equals("0")){
                  if(PhaccTxnVO!=null){
                        PhaccTxnVO.setActive_status(true);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);            
                        boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(objPaypal.getPaymentId());           
                        Debug.print("mahaRemote.insertAccountTxnDetails(phoneVO) for Phone Service :"+status);
                        }           
                }
                /*else if(sslResult.equals("1")){
                     if(PhaccTxnVO!=null){
                        PhaccTxnVO.setActive_status(false);
                        boolean status = mahaRemote.insertAccountTxnDetails(PhaccTxnVO);                                  
                        }    
                }    */
       
                  }    
 
                        //for updating the paymentid in history table
                    String issueId="";
                    String compYear="";
                    int year=0;
                    
                    ArrayList seasonDetails = new ArrayList();
                    seasonDetails=remote.getIssueIdBasedOnEventId(String.valueOf(objRenewalDet.getEventId()));
                    if(seasonDetails!=null && seasonDetails.size()!=0){
                        Iterator it=seasonDetails.iterator();
                        while(it.hasNext()){
                        String[] tempDet= (String[])it.next();
                        issueId=tempDet[0];
                        compYear=tempDet[1];
                        }
                    }
                    if(compYear!=null && compYear.trim().length()!=0){
                    year=Integer.parseInt(compYear);    
                    }
                    boolean updatePaymentId=false;
                    if(objPaypal.getPaymentId()!=null && objPaypal.getPaymentId().trim().length()!=0){
                    updatePaymentId=remote.updatePaymentIdInHist(String.valueOf(objRenewalDet.getEventId()),year,objPaypal.getPaymentId());
                    }
                    Debug.print("Updating paymentId in History Table :"+updatePaymentId);
                    //For Email
                        String toMailId = (String)session.getAttribute("emailId");
                        String toMailIds[] = {toMailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@usea.com");
                        email.setSubject("Event Registration Application confirmation.");
            
                        String content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
                        " <tr>" +
                        " <td height=\"463\" valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                        " <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> " +
                        "<tr>" +
                        "<td height=\"70\" valign=\"top\"><img src=\"images/emailHeader.jpg\" alt=\"USEA Online Services Dashboard Email Template\" width=\"515\" height=\"55\" /></td> " +
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
                        "<p>Dear Organizer,"+

                        "You have successfully sent an Event Registration application to United States Eventing Association for all  "+
                        "the events and competitions that are to be held! Your application would be verified and processed within"+
                        "24 hours."+
                        "Thank you for using this service.<p>"+
                        "Thank you for using the service provided by <span class=\"boldTxt\">United States Eventing Association</span>.</p>"+
                        "Thank You <br />"+
                        "------------------ <br />"+
                        "<span class=\"boldRedTxt\">USEA Team</span></td>"+
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
                        "<p>The easiest way to access your day to day USEA activities online or offline where ever you are and when ever you want."+
                        "</p>If you are a NEW VISITOR, register now and ENJOY the following privileges:"+
                        "<ul>"+
                        "<li>Unlimited shopping online.</li>"+
                        "<li>Place advertisements online and/or on-site.</li>"+
                        "<li>Sponsor competitions held by USEA.</li>"+
                        "</ul>"+


                        "Also, REGISTER NOW! and become a member of USEA to access and 	enjoy the following privileges as per your Membership Type and as "+
                        "per your �Role� assigned:"+

                        "<ul>"+
                        "<li>Compete in Equestrian Events held by USEA.</li>"+
                        "<li>Take part in other events like; Annual Meetings, Educational events,"+
                        "Activity Meetings held by USEA etc.</li>"+
                        "<li>Send Messages to other members.</li>"+
                        "<li>Create your own Distribution Lists.</li>"+
                        "<li>Create/Join a group and share your thoughts and common ideas.</li>"+
                        " <li>Unlimited Shopping online.</li>"+
                        " <li>Place advertisements online and/or on-site.</li>"+
                        " <li>Sponsor competitions held by USEA.</li>"+
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
                        session.setAttribute("objRenewalDet",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("transacTypId1",null);
                        session.setAttribute("transacTypId2",null);
                        session.setAttribute("transacTypId3",null);
                        session.setAttribute("evePrice1",null);
                        session.setAttribute("evePrice2",null);
                        session.setAttribute("evePrice3",null);
                        request.setAttribute("eventId",String.valueOf(objRenewalDet.getEventId()));
                                           
                        request.setAttribute("eveTitle",String.valueOf(objRenewalDet.getCompetitionName()));
                        request.setAttribute("eventId",String.valueOf(objRenewalDet.getEventId()));
                        request.setAttribute("paymentMode","card"); 
                        request.setAttribute("amount",String.valueOf(objPaypal.getAmount()));
                        return mapping.findForward("forEndorseSuccess");
                      
                        /*else if(sslResult.equals("1")){ 
                        request.setAttribute("eveTitle",String.valueOf(objRenewalDet.getCompetitionName()));
                        request.setAttribute("paymentId",objPayDet.getPaymentId()); 
                        request.setAttribute("price",String.valueOf(objPayDet.getAmount()));
                        return mapping.findForward("frmMeeRenewalSuccess");    
                        }*/
                     }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
