/*
 * ActionAnnualPayment.java
 *
 * Created on November 6, 2006, 5:28 PM
 */

package com.mee.action;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlccommon.util.HLCPaymentResultVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcmee.ann.HLCAnnualMeetingStore;
import com.hlcmeeting.session.HLCVaigaiSessionRemote;
import com.hlcmeeting.session.HLCVaigaiSessionRemoteHome;
import com.hlcmeeting.util.Debug;
import com.hlcmeeting.util.HLCAnnualUserVO;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.*;
import java.text.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
/**
 *
 * @author punitha
 * @version
 */

public class ActionAnnualPayment extends Action {
     public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            HLCVaigaiSessionRemote vaiRemote  = initializeVaigaiEJB(request);
///Transaction Entry
            MessageResources mr=getResources(request);
            Context jndiContext = new InitialContext();
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);            
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create(); 
            
             Object objHuman=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
            HLCkaverystatelessRemoteHome humanHome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objHuman,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote humanRemote = humanHome.create();
            
            HLCPaymentSessionRemote objPaySessRemote = initializePaymentEJB(request);
            HttpSession session = request.getSession(true);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
           
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
            
            
            String sessUserId = (String)session.getAttribute("userId");
            
            Debug.print("Sucessfully return from NOVA UserId:" + sessUserId);
             HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");
            HLCPaymentDetails objPayment = (HLCPaymentDetails) session.getAttribute("objPayment");
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            String payId=(String)session.getAttribute("payId");
            System.out.println("payRes in card servlet: " + payRes);
            System.out.println("results in card servlet: " + results);
            System.out.println("statusId3 in card servlet: " + statusId3);
            System.out.println("statusId in card servlet: " + statusId);
            System.out.println("payId in card servlet: " + payId);
            
       if (statusId3 == null || statusId3.equalsIgnoreCase("Failure") ) {
                String errorCd = payRes.getErrorCd();
                String lngMsg = payRes.getLngMsg();

                String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("info@digiblitz.com");
                email.setSubject("Horse Registration Decline");
                //System.out.println("toMailIds: " + toMailIds);
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
                       // "CVV2MATCH:" + payRes.getSslCvv2Response() + "<br/>" +
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

                HLCPaymentDetailVO objPay = new HLCPaymentDetailVO();
                
                objPay.setAmount(objPayment.getAmount());
                System.out.println("Amount  in Failure  "+objPayment.getAmount());
                
                session.setAttribute("objPayDet",objPay);
               
                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);

                return mapping.findForward("decline");
            }      
                
            // HLCPaymentSessionRemote objPaySessRemote = initializePaymentEJB(request);
            //HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO(); 
           // objPayDet = (HLCPaymentDetailVO) session.getAttribute("objPaymentList");
           // String res[]=(String[])session.getAttribute("registerObj");
           // Debug.print("Sucessfully return from Nova objActDet:" + res);
           // Debug.print("Sucessfully return from Nova publication:" + publication);
           // Debug.print("Sucessfully return from Nova objPayDet:" + objPayDet);
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
            
            String paymentId=(String)session.getAttribute("paymentId");
            String amount =(String)session.getAttribute("amount");
            
           /* boolean paymentStatus = false;
            if(sslResult.equals("0")){
                paymentStatus = true;
            }
            
            Debug.print("paymentId:" + paymentId);
            Debug.print("User Id:" + sessUserId);
            Debug.print(" sslResult:" + sslResult);
            Debug.print(" amount:" + amount);*/
            /*
             *To register for Annual Meeting and Convention based on Payment Details.
             *Bean - VaigaiSession.updatePaymentStatus()
             *Parameter - String paymentId, String userId, String sslResult, String sslResultMessage,
                          String  sslTxnId, String sslApprovalCode, String sslCvv2Response, String sslAvsResponse,
                          String sslTransactionType, String sslInvoiceNo, String sslEmail, boolean activeStatus
             *Bean - VaigaiSession.updateAnnualStatus()
             *Parameter - String annualMeetingId, String requestStatus, String comments
             *Response - frmMeeCardSuccess.jsp,frmMeeAnnualConvRegPaymentNovaDecline.jsp
             */
            
            /*if(paymentId!=null&&paymentId.trim().length()!=0&&sessUserId!=null && sessUserId.trim().length()!=0){
                boolean result =vaiRemote.updatePaymentStatus(paymentId, sessUserId, sslResult, sslResultMessage, 
                    sslTxnId, sslApprovalCode, sslCvv2Response, sslAvsResponse,
                    sslTransactionType, sslInvoiceNo, sslEmail, paymentStatus);
            }*/
            
               if(sslResult.equals("0")){
                  HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
              if(objPaypal!=null){
                  Debug.print("Sucessfully Return objPaymentList:" + objPayment.getPaymentId());
                    objPayDet.setUserId(sessUserId);
                     objPayDet.setSslResult(sslResult);
                     objPayDet.setPaymentId(objPayment.getPaymentId());
                     Debug.print("Payment ID in servlet "+objPayDet.getPaymentId());
                        objPayDet.setSslResultMessage(sslResultMessage);
                        objPayDet.setSslTxnId(sslTxnId);
                        objPayDet.setSslApprovalCode(sslApprovalCode);
                        objPayDet.setSslCvv2Response(sslCvv2Response);
                        objPayDet.setSslAvsResponse(sslAvsResponse);
                        objPayDet.setSslTransactionType(sslTransactionType);
                        objPayDet.setSslInvoiceNo(sslInvoiceNo);
                        objPayDet.setSslEmail(sslEmail);
                       // objPayDet.setPaymentId(paymentId);
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
                        objPayDet.setCcName(objPayment.getCcName());
                        objPayDet.setCcType(objPayment.getCcType());
                        objPayDet.setCcExpMonth(objPayment.getCcExpMonth());
                        objPayDet.setCcExpYear(objPayment.getCcExpYear());
                        objPayDet.setCcCvvid(objPayment.getCcCvvid());
                        objPayDet.setBankName(objPayment.getBankName());
                        objPayDet.setAmount(objPayment.getAmount());
                        objPayDet.setPaymentStatus(objPayment.getPaymentStatus());
                        objPayDet.setCcNumber(objPayment.getCcNumber());
                        objPayDet.setIpAddress(objPayment.getIpAddress());
                        objPayDet.setPaymentDate(objPayment.getPaymentDate());
                        objPayDet.setCheckDate(null);
                        objPayDet.setCheckNumber("0");
                        objPayDet.setCheckName(null);
                        objPayDet.setCheckAmount(0);
                        
                  HLCAnnualMeetingStore regList = (HLCAnnualMeetingStore)session.getAttribute("regList"); 
                  String ticket = (String)session.getAttribute("ticket"); 
                  String totAmount = (String)session.getAttribute("totAmount");
                  String addTktRegistrarName = (String)session.getAttribute("addTktRegistrarName");
                  ArrayList addTktList = (ArrayList)session.getAttribute("addTktList");
                  String checkAmount1 = (String)session.getAttribute("checkAmount1");
                 float checkAmount = 0;
                   ArrayList priceList =new ArrayList();
         if(checkAmount1==null || checkAmount1==""){
                        checkAmount=0; 
               }
                        else{
                        checkAmount = Float.parseFloat(checkAmount1);
                        }   
                   boolean result =false;
                   String meetingId=null;
                   String res[]={sessUserId,ticket,String.valueOf(totAmount),"Pending"};
                    //String paymentId1 = vaiRemote.getNextId();
                      String paymentId1=objPayDet.getPaymentId();
                 if(regList!=null){
                Enumeration e = regList.getEnumeration();
                //String[] priceDet ={};
                
                 Debug.print("paymentId1 in check:" + paymentId1);
                String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                        boolean idExist = humanRemote.isInvoiceIdExist(sessionInvoiceId);
                        Debug.print("idExist value is:" + idExist);
                      
                          if (!idExist) {  
                 meetingId = vaiRemote.registerAnnualMeeting(res, e, paymentId1, addTktRegistrarName, addTktList);
                 
                  String registraterId=vaiRemote.getAnnualRegistraterId(meetingId);
                  priceList= vaiRemote.getAnnualPriceDetailsByAnnualId(registraterId);
                 
                 objPaypal.setPaymentId(paymentId1);
                 Debug.print("Activity Organizer INserted Sucessfully for credit card:"+result);

                 boolean result1  = objPaySessRemote.createPayment(objPayDet);
                 Debug.print("Result  Payment Bean" + result1);
                 Debug.print("succsssullll Created for credit card........::");
                          }
                 session.setAttribute("paymentId",paymentId1);
                 session.setAttribute("amount",String.valueOf(totAmount));
                    //// Horse Member type Entry             /*   
                              double otherAmount = 0.0;
                              String otherStrAmt = "";
                              double tempregAmount = 0.0;
                              double tempOtherAmount = 0.0;
                              
                     float tempTotAmt = Float.parseFloat(totAmount);
                     float pendingAmount=0;
                     if(checkAmount!=0){
                     pendingAmount = tempTotAmt - checkAmount;
                     }else{
                         pendingAmount=0;
                     }
                    Debug.print(" pendingAmount after calculation in bean :"+pendingAmount);
                    
                   session.setAttribute("pendAmt",String.valueOf(pendingAmount));
                              
                              String specTransId = (String) session.getAttribute("specTransId");
                              Debug.print("Specification Trans Id is ::::::::::::::::"+specTransId);
                                if(specTransId!=null || specTransId.trim().length()!=0){


                                    HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.selectAccTransactionTypeDetail(specTransId);
                                    HLCAccTransactionVO regiVO = new HLCAccTransactionVO();

                                    String accNo =  accTxnDet.getAccount_no();
                                    String className = accTxnDet.getClass_name();
                                    String itemNo = accTxnDet.getItem_no();
                                    String subAccNo = accTxnDet.getSub_account_no();
                                    String transName = accTxnDet.getTransaction_name();
                                    String transType = accTxnDet.getTransaction_type();

                                    String cardselect = (String)session.getAttribute("cardselect");
                                    regiVO.setPayment_mode(cardselect);
                                    regiVO.setActive_status(false);

                                    double regAmount = 0.0;
                                    String regStrAmt = "";

                                    Debug.print("Before Renumeration");
                                    Enumeration new_Enum = regList.getEnumeration();
                                    Debug.print("After Renumeration");
                                    while(new_Enum.hasMoreElements()){
                                            Debug.print("Inisde While Loop");
                                            HLCAnnualUserVO tempObjAnnualUser = (HLCAnnualUserVO) new_Enum.nextElement();
                                            
                                            regAmount = tempObjAnnualUser.getRegAmount();
                                            otherAmount = tempObjAnnualUser.getOtherActAmount();
                                            
                                            tempregAmount = tempregAmount + regAmount;
                                            tempOtherAmount = tempOtherAmount + otherAmount;
                                                    
                                            Debug.print(" tempregAmount "+tempregAmount);
                                            Debug.print(" tempOtherAmount "+tempOtherAmount);
                                    }
                                    
                                    regStrAmt = Double.toString(tempregAmount);
                                    otherStrAmt = Double.toString(tempOtherAmount);
                                    
                                    regiVO.setAmount(Float.parseFloat(regStrAmt));
                                    regiVO.setDescription(transName);
                                    regiVO.setClass_Typ(className);
                                    regiVO.setAccount_no(accNo);
                                    regiVO.setAccount_type("Income");

                                    regiVO.setItem_no(itemNo);
                                    regiVO.setSub_account_no(subAccNo);
                                    regiVO.setPayment_id(paymentId1);
                                    
                                    Debug.print("Setting the attribute regiVO");
                                    session.setAttribute("regiVO",regiVO);
                                }

                    //// Ended the registration Trans Entry          
                        Debug.print("tempOtherAmount is "+tempOtherAmount);
                        Debug.print("tempregAmount is "+tempregAmount);
                    //// For Activity Trans Entry
                              tempOtherAmount = 0.0;
                              tempregAmount = 0.0;
                              String activityId = (String) session.getAttribute("activityId");
                              Debug.print("Activity Trans Id is ::::::::::::::::"+activityId);
                                if(activityId!=null || activityId.trim().length()!=0){


                                    HLCAccTxnTypeDetailVO accTxnDet = mahaRemote.selectAccTransactionTypeDetail(activityId);
                                    HLCAccTransactionVO regiVO = new HLCAccTransactionVO();

                                    String accNo =  accTxnDet.getAccount_no();
                                    String className = accTxnDet.getClass_name();
                                    String itemNo = accTxnDet.getItem_no();
                                    String subAccNo = accTxnDet.getSub_account_no();
                                    String transName = accTxnDet.getTransaction_name();
                                    String transType = accTxnDet.getTransaction_type();

                                   
                                String cardselect = (String)session.getAttribute("cardselect");
                                         regiVO.setPayment_mode(cardselect);
                                         regiVO.setActive_status(false);
                                   

                                    double regAmount = 0.0;
                                    String regStrAmt = "";
                                    Debug.print("Before Renumeration");
                                    Enumeration new_Enum = regList.getEnumeration();
                                    Debug.print("After Renumeration");
                                    while(new_Enum.hasMoreElements()){
                                            Debug.print("Inisde While Loop");
                                            HLCAnnualUserVO tempObjAnnualUser = (HLCAnnualUserVO) new_Enum.nextElement();
                                            
                                            regAmount = tempObjAnnualUser.getRegAmount();
                                            otherAmount = tempObjAnnualUser.getOtherActAmount();
                                            
                                            tempregAmount = tempregAmount + regAmount;
                                            tempOtherAmount = tempOtherAmount + otherAmount;
                                                    
                                            Debug.print(" tempregAmount "+tempregAmount);
                                            Debug.print(" tempOtherAmount "+tempOtherAmount);
                                    }
                                    Iterator itr = addTktList.iterator();
                                    double otherTktAmt = 0.0;
                                    while(itr.hasNext()){
                                        String tempAddTkt[] = (String[])itr.next();
                                        otherTktAmt = otherTktAmt + Double.parseDouble(tempAddTkt[2]);
                                    }
                                    
                                    Debug.print("Additional Tkt amt is "+otherTktAmt);
                                    Debug.print("Main Activ Tkt amt is "+tempOtherAmount);
                                    tempOtherAmount = tempOtherAmount + otherTktAmt; 
                                    
                                    Debug.print("Sum of amt is "+tempOtherAmount);
                                    otherStrAmt = Double.toString(tempOtherAmount);

                                    regiVO.setAmount(Float.parseFloat(otherStrAmt));
                                    regiVO.setDescription(transName);
                                    regiVO.setClass_Typ(className);
                                    regiVO.setAccount_no(accNo);
                                    regiVO.setAccount_type("Income");
                                    //horseMem.setUser_id(userId);
                                    //horseMem.setIp_address(reqIp);
                                    regiVO.setItem_no(itemNo);
                                    regiVO.setSub_account_no(subAccNo);
                                    regiVO.setPayment_id(paymentId1);

                                    Debug.print("Setting the attribute activity VO");
                                    session.setAttribute("activityVO",regiVO);
                                }          

                              /// End Transac Entry                
                } 
                  
                       
                       /* String meetingId = vaiRemote.getMeetingId(paymentId);
                        if(meetingId!=null&&meetingId.trim().length()!=0){
                            boolean annualResult = vaiRemote.updateAnnualStatus(meetingId, "Registered", "");
                            Debug.print("        AnnualResult:" + annualResult);
                        }*/
      String emailId = (String)session.getAttribute("emailId");
      
    String fffff=""; 
    String content="";
 String loopContent="";
 String finalContent="";
 String content1="";
 String subContent="";
  String toMailIds[] = {emailId};

                EmailContent email=new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("info@usea.com");
                email.setSubject("Annual Meeting & Convention Registration");

                         
              content = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +             
                      "+<tr>"+
                            "<td valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\"> "+                                                                       
                            "<strong>"+
                            "2009 Annual Meeting and Convention Registration Confirmation"+
                            "</strong>"+   							
				"<p>"+
"Thank you for your registration. The United States Eventing "+
"Association is looking forward to seeing you in Reston, Virginia on "+
"December 2-6, 2009 for the Annual Meeting and Convention as "+
"we celebrate the USEA's 50th Anniversary and honor the "+  
"inductees into the 2009 Eventing Hall of Fame."+       

 "<hr size=\"4\" noshade=\"noshade\"/>"+
                     
      "<p><a href='http://reports.useventing.com/ReportServer?/Public/AMC_Receipt&rs:Command=Render&rs:format=PDF&PAYID="+meetingId+"'>Click to view your Annual Meeting payment details</a> "+
                            "</p>"+	                                           			                        						
			"</td>"+
                      "</tr>";                   
                       
             
           loopContent= content;  

 content1=" <tr>" +
              " <td valign=\"top\" bgcolor=\"#FCFBF0\" style=\"padding-top:10px; padding-bottom:10px; padding-left:10px; padding-right:10px;\">" +
                            
       "<hr size=\"3\" noshade=\"noshade\"/>"+                        
                            "<p>Please visit <a href='http://useventing.com/aboutus.php?section=convention'>http://useventing.com/aboutus.php?section=convention</a> "+
                            "</p>"+			  
					 "<p>Not a USEA Member? Join today at <a href='http://dashboard.useventing.com/'>http://dashboard.useventing.com/</a> "+
                            "</p>"+ 
					 "<p>USEA Meeting Services"+
                            "</p>"+
                         "</td>"+
                      "</tr>"+                   
                       "</table>";    
                            
  
    
    finalContent=loopContent+" "+content1;
    
    //Debug.print("finalContent"+finalContent);
    if(finalContent!=null && !(finalContent.equalsIgnoreCase(""))){
             email.setBody(finalContent);
                            //email.setAttachments();
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            //boolean emailFlag = emailEngine.sendEmailWithAttachment(email);
                             Debug.print("Email sent sucessfully :"+emailFlag);
    }
                           
                        
                                                                                    
                    //(String)request.getAttribute("amount");
                        Debug.print("amount in card:" + String.valueOf(totAmount));
                        request.setAttribute("amount",String.valueOf(totAmount));
                       // session.setAttribute("regList",null);
                   // session.setAttribute("objPaymentList",null);
                        session.removeAttribute("regList");
                        session.removeAttribute("payRes");
                        session.removeAttribute("objPaypal");
                        session.removeAttribute("ticket");
                        session.removeAttribute("totAmount");
                        session.removeAttribute("addTktRegistrarName");
                        session.removeAttribute("addTktList");
                        session.removeAttribute("checkAmount1");

                        Debug.print("Activity VO");
                        if(session.getAttribute("activityVO")!=null){
                            HLCAccTransactionVO activityVO = (HLCAccTransactionVO) session.getAttribute("activityVO");
                            if(activityVO.getPayment_id()!=null){
                                boolean insert_status = mahaRemote.insertAccountTxnDetails(activityVO);
                                Debug.print("Activity VO Insert Status "+insert_status);                            
                            }
                            else{
                                Debug.print("Activity VO Not inserted in the card page");
                            }
                        }
                        
                        Debug.print("Registration VO");
                        if(session.getAttribute("regiVO")!=null){
                            HLCAccTransactionVO regiVO = (HLCAccTransactionVO) session.getAttribute("regiVO");
                            if(regiVO.getPayment_id()!=null){
                                boolean insert_status = mahaRemote.insertAccountTxnDetails(regiVO);
                                Debug.print("Registration VO Insert Status "+insert_status);                                                        
                            }
                            else{
                                 Debug.print("Registration VO Not inserted in the card page");
                            }
                        }

                      //update Payment Status
                        if(paymentId1!=null || paymentId1.trim().length()!=0){
                            boolean update_payStat = mahaRemote.updatePaymentStatusAccTxnDetails(paymentId1);
                            Debug.print("Update Status "+update_payStat);
                        }                        
                      /*
                       *Redirects to -frmMeeCardSuccess.jsp
                       */
                      return mapping.findForward("cardcnf");
              }    
                
            }
            
        }
      catch(Exception e){
          Debug.print("Exception occurs in payment" +e.getMessage());
          
      }
        return null;
    }

    public static Context getInitialContext()
    throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
    private HLCVaigaiSessionRemote initializeVaigaiEJB(HttpServletRequest request) throws Exception{
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.icp");
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
        HLCVaigaiSessionRemoteHome home = (HLCVaigaiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCVaigaiSessionRemoteHome.class);
        HLCVaigaiSessionRemote remote = home.create();
        
        return remote;
    }
    private HLCPaymentSessionRemote initializePaymentEJB(HttpServletRequest request) throws Exception{
        MessageResources mr=getResources(request);
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        
        
        System.setProperty(namingfactory,contextfactory);
        System.setProperty(urlprovider,lookupip);
        
        Context jndiContext = new InitialContext();
        Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
         
        HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
        HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
        
        return objPaySessRemote;
          
               
    }

       
}
