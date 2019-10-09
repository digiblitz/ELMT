/*
 * NewHorseMembPayAction.java
 *
 * Created on October 15, 2006, 2:03 AM
 */

package com.mrm.action;


import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlccommon.util.*;
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
import java.text.*;

/**
 *
 * @author punitha
 */
public class NewHorseMembPayAction extends Action {
    
 
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
            
///Transaction Entry
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);            
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create(); 
// History Entry
            String jname=mr.getMessage("jndi.usrreg");
            Object oef = jndiContext.lookup(jname);
            
            HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(oef,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote userremote = userhome.create();            
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");
            String totalAmount =(String)session.getAttribute("amount");
            String registrationLevel = (String)session.getAttribute("registrationLevel");
            String activationDate = (String)session.getAttribute("activationDate");
            System.out.print("activationDate" + activationDate);
            Date checkActDate =null;
                        if(activationDate!=null && activationDate.trim().length()!=0){
                            checkActDate =  sdf.parse(activationDate);
                            Debug.print("checkActDate:" + checkActDate);
                        }
                        else{
                            checkActDate = new Date();
                        }
             Debug.print("registrationLevel:"+registrationLevel);
             Debug.print("checkActDate outer:"+checkActDate);
             String horseName =(String)session.getAttribute("horseName");
             Debug.print("horseName:"+horseName);
             String paymentId = (String)session.getAttribute("paymentId");
             
            Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Nova UserCode:" + (String)session.getAttribute("userCode"));
               
                HLCHorseRegistrationVO horseRegVO = (HLCHorseRegistrationVO)session.getAttribute("horseRegVO");
             HLCHorseDescriptionVO objHorseDesc =(HLCHorseDescriptionVO)session.getAttribute("objHorseDesc");
             HLCHorseUserVO objOwnerVO = (HLCHorseUserVO)session.getAttribute("objOwnerVO");
             HLCHorseUserVO objAddOwnerVO =(HLCHorseUserVO)session.getAttribute("objAddOwnerVO");
             String serviceType = (String)session.getAttribute("serviceType");
             String status = (String)session.getAttribute("status");
             String horseStatus =(String)session.getAttribute("horseStatus");
             
             HLCHorseUserVO objTrainerVO =(HLCHorseUserVO)session.getAttribute("objTrainerVO");
             
             
             Debug.print("paymentId:" + paymentId);
             Debug.print("status:" + status);
             Debug.print("horseStatus:" + horseStatus);
             
             if(status==null){
                 status = "false";
             }
             if(horseStatus==null){
                 horseStatus = "false";
             }             
             
             boolean statusValue = Boolean.parseBoolean(status);//)Boolean.parseBoolean(status);
             boolean horseStatusValue = Boolean.parseBoolean(horseStatus);//Boolean.parseBoolean(horseStatus);
             
            Debug.print("horseRegVO:"+horseRegVO);
            Debug.print("objOwnerVO:"+objOwnerVO);
            Debug.print("objHorseDesc:"+objHorseDesc);
            Debug.print("objAddOwnerVO:"+objAddOwnerVO);
            Debug.print("serviceType:"+serviceType);
            Debug.print("status:"+status);
            
            Debug.print("Return from Nova:");
             String res =""; 
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
                email.setFrom("dashboard@useventing.com");
                email.setSubject("Non-Human Registration Decline");
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
                 String amt=(String)session.getAttribute("amount");                                
                 session.setAttribute("amt",amt);
                session.setAttribute("paymentId", (String)session.getAttribute("paymentId"));
                request.setAttribute("registrationLevel", registrationLevel);
                request.setAttribute("horseMemberId", res);
                request.setAttribute("horseName", horseName);
                request.setAttribute("totalAmount", totalAmount);
                request.setAttribute("paymentId", paymentId);

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
                    Debug.print("Sucessfully return from Nova sslEmail:" + sslEmail);
                    Debug.print(">>>>>>>>>>>>>>>>>>>>>>>>>Amount in Second Servlet:" + horseRegVO.getAmount());*/

                    HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
                    objPayDet = (HLCPaymentDetailVO)session.getAttribute("objPaymentVO");   
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
                Debug.print(">>>>>>>>>>>>>>>>>>>>>>>>>Amount in Second Servlet:" + horseRegVO.getAmount());



                    Debug.print("Sucessfully Return objPaymentList:" + objPayDet.toString());
                    
                    horseRegVO.setPaymentId(paymentId);
                    res = remote.createHorseRegistration(horseRegVO, objHorseDesc, objOwnerVO,objTrainerVO, objAddOwnerVO,serviceType,statusValue);
                    //res= (String) session.getAttribute("horseMemberId");
                    session.setAttribute("horseMemberId",res);
                    
                    String riderEmailId=""; 
                        String riderFName=""; 
                        String riderLName=""; 
                        String OwnerFName=""; 
                        String OwnerLName=""; 
                        String OwnerMailId=""; 
                        String membershipName1=""; 
                        
                         riderEmailId = (String) session.getAttribute("riderEmailId");
                         Debug.print("riderEmailId::: in NewHorseMembPayAction " + riderEmailId);
                         riderFName = (String) session.getAttribute("riderFName");
                         Debug.print("riderFName::: in NewHorseMembPayAction" + riderFName);
                         riderLName = (String) session.getAttribute("riderLName");
                         Debug.print("riderLName::: in NewHorseMembPayAction" + riderLName);
                         OwnerFName = (String) session.getAttribute("OwnerFName");
                         Debug.print("OwnerFName::: in NewHorseMembPayAction" + OwnerFName);
                         OwnerLName = (String) session.getAttribute("OwnerLName");
                         Debug.print("OwnerLName::: in NewHorseMembPayAction" + OwnerLName);
                         OwnerMailId = (String) session.getAttribute("OwnerMailId");
                         Debug.print("OwnerMailId::: in NewHorseMembPayAction" + OwnerMailId);
                         membershipName1 = (String) session.getAttribute("membershipName1");
                         Debug.print("membershipName1::: in NewHorseMembPayAction" + membershipName1);
                         
                    Debug.print("Horse Member Id :" + res);
                    String horseRelId[] = (String[])remote.getAllEmailIds(res);
                    Debug.print("horseRelId:::" + horseRelId);
                    
                        /*objPayDet.setSslResult(sslResult);
                        objPayDet.setSslResultMessage(sslResultMessage);
                        objPayDet.setSslTxnId(sslTxnId);
                        objPayDet.setSslApprovalCode(sslApprovalCode);
                        objPayDet.setSslCvv2Response(sslCvv2Response);
                        objPayDet.setSslAvsResponse(sslAvsResponse);
                        objPayDet.setSslTransactionType(sslTransactionType);
                        objPayDet.setSslInvoiceNo(sslInvoiceNo);
                        objPayDet.setSslEmail(sslEmail);
                        objPayDet.setPaymentId(paymentId);
                        objPayDet.setAmount(Double.parseDouble(totalAmount));*/
                        //boolean resultPay  = objPaySessRemote.createPayment(objPayDet);
                        //Debug.print("resultPay value is:" + resultPay);
                        
                        
                        //// Start History Table Entries
                        Debug.print("History Table Entries");
                        HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
                        
                        java.util.Calendar toDay = java.util.Calendar.getInstance();
                        int newyear = toDay.get(Calendar.YEAR);
                        int new_month = toDay.get(Calendar.MONTH);
                        String membtype = (String) session.getAttribute("membtype");
                        if(membtype!=null || membtype.trim().length()!=0){
                        String sarray[] = membtype.split("#");
                            String memberTypeId = sarray[0];
                            String membershipName = sarray[1];
                            String statid ="";
                            
                            if(sslResult.equals("0")) {
                                statid=userremote.getStatusIBasedOnStatus("Active");
                            } else{
                                statid=userremote.getStatusIBasedOnStatus("Pending");
                            }
                            
                            Debug.print("New Horse Member Id Generated is "+res);
                            Debug.print("New Payment Id Generated is "+paymentId);
                            Debug.print("New Member Type Id is "+memberTypeId);
                            Debug.print("New Member Type Name is "+membershipName);
                            
                            detailVO.setTo_year(newyear);
                            detailVO.setPayment_id(paymentId);
                            detailVO.setMemberId(res);
                            detailVO.setMembership_action("Register");
                            detailVO.setMembership_type_id(memberTypeId);
                            detailVO.setMembership_type_name(membershipName);
                            //detailVO.setZip_code();
                            detailVO.setStatus_id(statid);
                            detailVO.setUser_id(userId);
                            
                            Debug.print("History Updat Status");
                            
                            boolean historyUpdate = userremote.insertHorseMemberHistoryDetails(detailVO);
                            Debug.print("historyUpdate "+historyUpdate);
                        }
                     Debug.print("End History Table Entries");
                     //// End History Table Entries
                     
             if(sslResult.equals("0")) {
                
                 
                if(objPayDet!=null){
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

                //Phone Service
                       String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                        boolean idExist = userremote.isInvoiceIdExist(sessionInvoiceId);
                        Debug.print("idExist value is:" + idExist);

//======================INSERT ENTRIES INTO PAYMENTDETAILS TABLE==========================================
                        boolean resultPay = false;
                        if (!idExist) {
                            resultPay = objPaySessRemote.createPayment(objPayDet);
                            Debug.print("resultPay value is:" + resultPay);
                            session.setAttribute("sessionResultPay", "true");
                        }
 
                HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                if(PhoneVO!=null){
                    PhoneVO.setActive_status(true);
                    boolean phone_status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                    Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no "+phone_status);                    
                } 
                
                //Horse Member
                if(session.getAttribute("horseMem")!=null){
                    HLCAccTransactionVO horseMem =  (HLCAccTransactionVO) session.getAttribute("horseMem");
                    if(horseMem!=null){
                        horseMem.setActive_status(true);
                        boolean horse_status = mahaRemote.insertAccountTxnDetails(horseMem);
                        Debug.print("mahaRemote.insertAccountTxnDetails(horseMem)  "+horse_status);                    
                    }                
                }
                 
              /*
               //update Payment Status
                    if(paymentId!=null || paymentId.trim().length()!=0){
                        boolean update_payStat = mahaRemote.updatePaymentStatusAccTxnDetails(paymentId);
                        Debug.print("Update Status "+update_payStat);
                    }    
               */
                     if(horseStatusValue==true){
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@digiblitz.com");
                        email.setSubject("Non-Human Member Matching");
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
                            "<p>Your Non-Human may already be registered.<p>"+
                            "<p>In an effort to reduce the number of duplicate Non-Human registrations, the HLC "+
                            "would like to inform you that the Non-Human name <strong>"+ horseName +" </strong>is already in use in our database."+
                            "Please verify that your Non-Human <strong>"+ horseName +"</strong> has not be previously registered by a former owner or rider."+
                            "Please log in to the HLC Online Services, and access Non-Human Registration. Please search for the Non-Human by name to determine if "+
                            "<strong>"+ horseName +"</strong> has been registered before. If your Non-Human has been registered in the past, please " +
                            "contact the HLC Sports Services Department at 703-779-0440.<p>"+
                            "<p>If you have confirmed that your Non-Human has not been registered with the HLC previously, no further action is needed.<p><br />"+
                            "Thank You <br />"+                            
                            "<p>Sincerely," +
                            "<p>HLC Staff <p></td>"+
                            "<td valign=\"top\" background=\"images/Rght.jpg\">&nbsp;</td>"+
                            "</tr>"+
                            "<tr><td valign=\"top\" background=\"images/cornerBotLeft.jpg\">&nbsp;</td>"+
                            "<td valign=\"top\" background=\"images/cornerBot.jpg\">&nbsp;</td>"+
                            "<td valign=\"top\" background=\"images/cornerBotRght.jpg\">&nbsp;</td>"+
                            "</tr>"+
                            " </table>"+
                            "</td></tr>"+
                            "</table>";
                            email.setBody(content);
                            EmailEngine emailEngine = new EmailEngine();
                            boolean emailFlag = emailEngine.sendMimeEmail(email);
                            Debug.print("Email sent sucessfully :"+emailFlag);
                    }
                     
  
                    if(resultPay == true && res!=null){
                         boolean statusChange = remote.statusChangeForHorse(res,null);
                         boolean activeStatus = remote.updateRealtionshipStatus(res);
                         remote.updateRequestStatus(res,"","Horse Registration",checkActDate);
                         Debug.print("statusChange:0" + statusChange);
                         Debug.print("activeStatus:0" + activeStatus);
                         Debug.print("activeStatus:0" + activeStatus);
                        Debug.print(" Horse Registration status Changed for Credit Card:" + statusChange);
                        

                              //String toMailIds[] = {emailId};
                        if(OwnerMailId!=null && OwnerMailId.length()!=0){
                        Debug.print("OwnerMailId::: inside if condition in NewHorseMembPayAction" + OwnerMailId);     
                        String toMailIds1[] = {OwnerMailId};
                        EmailContent email1=new EmailContent();
                        email1.setTo(toMailIds1);
                        email1.setFrom("anandv@digiblitz.com");
                        email1.setSubject("Non-Human Member Registration");
                                
                         String content1 = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
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
                                "<p>Thank you for registering your Non-Human <strong>" + horseName +" </strong> with the HLC.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Non-Human Name: "+ horseName +"<br />"+
                                "Non-Human Registration ID:"+ res+"<br />"+
                                "Non-Human Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ OwnerFName+" "+ OwnerLName+"<br/>"+
                                "Rider(s):"+ riderFName+" "+riderLName+"<br/>"+                               
                                "<p>Additional information regarding "+ horseName +" can be viewed by logging in to the HLC Online Services.<p>"+ 
                                "<p>Thank you.<p><br />"+
                                "<p>Sincerely, <br />" +
                                "<p>HLC Staff <p>"+  
                                "</tr>"+                           
                                " </table>"+
                                "</td></tr>"+                      
                                "</table>";
                            email1.setBody(content1);
                            EmailEngine emailEngine1 = new EmailEngine();
                            boolean emailFlag1 = emailEngine1.sendMimeEmail(email1);
                            Debug.print("Email sent sucessfully to horse related members:"+emailFlag1);
                        }
                        
                       if(riderEmailId!=null && riderEmailId.length()!=0){
                       Debug.print("riderEmailId::: inside if condition in NewHorseMembPayAction" + riderEmailId);  
                        String toMailIds2[] = {riderEmailId};
                        EmailContent email2=new EmailContent();
                        email2.setTo(toMailIds2);
                        email2.setFrom("info@digiblitz.com");
                        email2.setSubject("Non-Human Member Registration");
                        String content2 = "<table width=\"526\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"border:1px solid #999;\"> " +
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
                                "<p>You have been listed as the rider for <strong>" + horseName +" </strong>, recently registered with the HLC.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ horseName +"<br />"+
                                "Horse Registration ID:"+ res+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ OwnerFName+" "+ OwnerLName+"<br/>"+
                                "Rider(s):"+ riderFName+" "+riderLName+"<br/>"+                               
                                "<p>Additional information regarding "+ horseName +" can be viewed by logging in to the HLC Online Services. Thank you.<p>"+                          
                                 "<p>Sincerely, <br />" +
                                "<p>HLC Staff <p>"+  
                                "</tr>"+                           
                                " </table>"+
                                "</td></tr>"+                      
                                "</table>";
                        email2.setBody(content2);
                        EmailEngine emailEngine2 = new EmailEngine();
                        boolean emailFlag2 = emailEngine2.sendMimeEmail(email2);
                        Debug.print("Email sent sucessfully :"+emailFlag2);
                        }
                        
                        request.setAttribute("totalAmount",totalAmount);
                        request.setAttribute("registrationLevel",registrationLevel);
                        request.setAttribute("horseMemberId",res);
                        request.setAttribute("horseName",horseName);

                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("horseName",null);
                        session.setAttribute("registrationLevel",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("totalAmount",null);


                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("horseName",null);
                        session.setAttribute("registrationLevel",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("totalAmount",null);
                        session.setAttribute("horseRegVO",null);
                        session.setAttribute("objHorseDesc",null);
                        session.setAttribute("objOwnerVO",null);
                        session.setAttribute("objAddOwnerVO",null);
                        session.setAttribute("serviceType",null);
                        session.setAttribute("status",null);
                       return mapping.findForward("confirm");
                    }
                      
                    }
                 }
                     else{
                        Debug.print("Not Inserted:");
                        Debug.print("Inside the New Horse Member Payment Declined State");
                        Debug.print("horsememId :"+horsememId);
                        Debug.print ("horsememId"+session.getAttribute("horseMemberId"));
                        Debug.print("Not inserted"+paymentId);
                        Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                        request.setAttribute("registrationLevel",registrationLevel);
                        request.setAttribute("horseMemberId",res);
                        request.setAttribute("horseName",horseName);
                        request.setAttribute("totalAmount",totalAmount);
                        request.setAttribute("paymentId",paymentId);   
                        
                        //Phone Service
                        HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                        if(PhoneVO!=null){
                            PhoneVO.setActive_status(false);
                            boolean phone_status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                            Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no "+phone_status);                    
                        } 

                        //Horse Member
                        if(session.getAttribute("horseMem")!=null){
                            HLCAccTransactionVO horseMem =  (HLCAccTransactionVO) session.getAttribute("horseMem");
                            if(horseMem!=null){
                                horseMem.setActive_status(false);
                                boolean horse_status = mahaRemote.insertAccountTxnDetails(horseMem);
                                Debug.print("mahaRemote.insertAccountTxnDetails(horseMem)  "+horse_status);                    
                            }                
                        }
                        
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@digiblitz.com");
                        email.setSubject("Non-Human Registration Decline");
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
                                "<p>We are sorry, but your payment for the Non-Human registration of  <strong>" + horseName +" </strong> has been declined.<p>"+
                                "<p>This registration is not active.<p>"+                                                        
                                "Please try another form of payment or contact the HLC at 703-779-0440."+ 
                                "<p>Thank you.<p><br />"+
                                "<p>Sincerely," +
                                "<p>HLC Staff <p>"+  
                                "</tr>"+                           
                                " </table>"+
                                "</td></tr>"+                      
                                "</table>";
                        email.setBody(content);
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);                                                                     
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

