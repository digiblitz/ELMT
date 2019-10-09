  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlccommon.util.HLCPaymentResultVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberDetails;
import com.hlcform.util.HLCPaymentDetails;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import com.hlcutil.Debug;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
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
import java.util.*;



/**
 *
 * @author Dhivya
 */
public class ActionAreaMembershipPayment extends Action {
    
  
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
                
        
         String paymentIds="";
         Date act_date_card=new Date();
         String amount1="";
         HLCMemberDetails objMember = new HLCMemberDetails();     
         HLCPaymentDetails objPayment = new HLCPaymentDetails();
         
        try{
            
           HttpSession session = request.getSession();
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            String jndiname=mr.getMessage("jndi.usrreg");
           
            String jndiname2=mr.getMessage("jndi.rolemanagement");
            String defrole=mr.getMessage("default.role");
            Debug.print("defrole :" +defrole);
        
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            

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
        
            Object objref = jndiContext.lookup(jndiname);
            HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create(); 
         HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");// From Paypal Action
           
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            objMember=(HLCMemberDetails)session.getAttribute("objMember");
           // System.out.println("payRes in card servlet: " + payRes);
           //System.out.println("results in card servlet: " + results);
          //  System.out.println("statusId3 in card servlet: " + statusId3);
          //  System.out.println("statusId in card servlet: " + statusId); 
        
            Debug.print("Return from Nova UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Nova memeberId:" + (String)session.getAttribute("memberId"));
            
            Debug.print("Return from Nova:");
            
           if (statusId3 == null || statusId3.equalsIgnoreCase("Failure")) {
                String errorCd = payRes.getErrorCd();
                String lngMsg = payRes.getLngMsg();
                HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
                String emailId = (String)session.getAttribute("emailId");
                String toMailIds[] = {emailId};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("info@digiblitz.com");
                email.setSubject("Area Program Decline");
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
                      //  "AVSCODE: " + payRes.getSslAvsResponse() + "<br/>" +
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
                     
                    //double payAmt=objPayment.getAmount();
                              
                   //String amt=String.valueOf(payAmt);
                      String amt=(String)session.getAttribute("amount");
                   request.setAttribute("areaName",objMember.getAraeName());                                     
                   session.setAttribute("amt",amt);
                                     
                   request.setAttribute("areaRidProg",objMember.getAreaProgName());
                   request.setAttribute("progYear",objMember.getAreaProgYear());
                   request.setAttribute("areaRidProgDesc",objMember.getAreaProgDesc());
                   session.setAttribute("objPayDet",objPayDet);
                  session.setAttribute("paymentId", (String)session.getAttribute("paymentId"));
                
                  System.out.println("Amount and PaymentID  In Second sefverlet decline"+amt+"****  "+(String)session.getAttribute("paymentId"));
                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);
                
                   Debug.print("Email sent sucessfully :" + emailFlag);

                return mapping.findForward("decline");
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
            
                String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                 boolean idExist = remote.isInvoiceIdExist(sessionInvoiceId);
                 Debug.print("idExist value is:" + idExist);
                 
            //Debug.print("Sucessfully Return objPaymentList:" + objPayment.toString());
            
            Debug.print("paymentId in nova servlet :"+(String)session.getAttribute("paymentId"));
            
            act_date_card=(java.util.Date)session.getAttribute("act_date");
            Debug.print("act_date_card from session :"+act_date_card.toString()); 
          
             //objPayment=(PaymentDetails)session.getAttribute("objPayment");
             objMember=(HLCMemberDetails)session.getAttribute("objMember");
            String userId = (String)session.getAttribute("userId");
            paymentIds=objPaypal.getPaymentId();
            
            if(objPaypal!=null){
                     
                     amount1=String.valueOf(objPaypal.getAmount());
                     
             }
    
              if(sslResult.equals("0")){
                
                 if(objPaypal!=null){
                     
                    //amount1=String.valueOf(objPayment.getAmount());
                     
                    HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
                    
                    objPayDet.setPaymentId(objPaypal.getPaymentId());
                    objPayDet.setUserId(userId);
                    objPayDet.setIpAddress(objPaypal.getIpAddress());
                    objPayDet.setCcName(objPaypal.getCcName());
                    objPayDet.setCcType(objPaypal.getCcType());
                    //objPayDet.setCcNumber(objPayment.getCcNumber());
                    objPayDet.setCcExpMonth(objPaypal.getCcExpMonth());
                    objPayDet.setCcExpYear(objPaypal.getCcExpYear());
                    objPayDet.setCcCvvid(objPaypal.getCcCvvid());
                    objPayDet.setBankName(objPaypal.getBankName());
                    objPayDet.setAmount(objPaypal.getAmount());
                    objPayDet.setPaymentStatus(objPaypal.getPaymentStatus());  
                    objPayDet.setCcNumber(objPaypal.getCcNumber());
                                                      
                    objPayDet.setSslResult(sslResult);
                    objPayDet.setSslResultMessage(sslResultMessage);
                    objPayDet.setSslTxnId(sslTxnId);
                    objPayDet.setSslApprovalCode(sslApprovalCode);
                    objPayDet.setSslCvv2Response(sslCvv2Response);
                    objPayDet.setSslAvsResponse(sslAvsResponse);
                    objPayDet.setSslTransactionType(sslTransactionType);
                    objPayDet.setSslInvoiceNo(sslInvoiceNo);
                    objPayDet.setSslEmail(sslEmail);
                    objPayDet.setCheckAmount(0);
                    objPayDet.setInVoiceID(inVoiceId3);
                    objPayDet.setPpCorrelationID(ppCorrelationID);
                    objPayDet.setPpExchangeRate(ppExchangeRate);
                    objPayDet.setPpPaymentStatus(ppPaymentStatus);
                    objPayDet.setPpTaxAmt(ppTaxAmt);
                    objPayDet.setPpParentTransactionID(ppParentTransactionID);
                    objPayDet.setPpAuthorizationID(ppAuthorizationID);
                    objPayDet.setPpFeeAmt(ppFeeAmt);
                    objPayDet.setPpPaymentType(ppPaymentType);
                    objPayDet.setPpSettleAmt(ppSettleAmt);
                    objPayDet.setPpPendingReason(ppPendingReason);
                    objPayDet.setPpReasonCode(ppReasonCode);
                
                    if(!idExist){
                    boolean resultPay1  = objPaySessRemote.createPayment(objPayDet);
                  //  Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                   
                    boolean output1=remote.getAreaMembHistDetails(objMember,objPayment);
                   
                    boolean result = remote.insertAreaMembProgram(objMember,objPayment);
                 
               HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO");
                if(PhoneVO!=null){
                    PhoneVO.setActive_status(true);
                    boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);                    
                    Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for area Member"+status);                    
                }
                                  
//Setting the reconcile & active Status TRUE
                if(paymentIds!=null || paymentIds.trim().length()!=0){
                    boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentIds);
                    Debug.print("Update Status "+Update_Status);
                }
             
               HLCAccTransactionVO PhoneVO1 =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO1");   
               if(PhoneVO1!=null){
                    PhoneVO1.setActive_status(true);
                    boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO1);                    
                    Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO1) for area Member"+status);                    
                
                                                             
               if(paymentIds!=null || paymentIds.trim().length()!=0){
                    boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentIds);
                    Debug.print("Update Status "+Update_Status);
                }
               }                         
                   // double payAmt=objPayment.getAmount();
                   // String amt=String.valueOf(payAmt);
                     String amt=(String)session.getAttribute("amount");
                    if(resultPay1==true || result==true){
                    request.setAttribute("areaName",objMember.getAraeName());                                     
                    request.setAttribute("amt",amt);
                    request.setAttribute("areaRidProg",objMember.getAreaProgName());
                    request.setAttribute("progYear",objMember.getAreaProgYear());
                    request.setAttribute("areaRidProgDesc",objMember.getAreaProgDesc());
                    //request.setAttribute("progYear",objMember.getA);
                    // the following session variable are used to replace the above two request variables.
                    session.setAttribute("areaName",objMember.getAraeName());                                     
                    session.setAttribute("amt",amt);
                    session.setAttribute("areaRidProg",objMember.getAreaProgName());
                    session.setAttribute("progYear",objMember.getAreaProgYear());
                    session.setAttribute("areaRidProgDesc",objMember.getAreaProgDesc());
                    // code ends here.
                                                 
                     return mapping.findForward("confirm");
                      
                    }                                                 
                 }
              }
              }
              
             else{                
                
                 Debug.print("Transaction Declined..:");
                 Debug.print("amount1:" + amount1);
                 
                 HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
                 
                    objPayDet.setPaymentId(objPayment.getPaymentId());
                    objPayDet.setUserId(userId);
                    objPayDet.setCcName(objPayment.getCcName());
                    objPayDet.setCcType(objPayment.getCcType());
                    //objPayDet.setCcNumber(objPayment.getCcNumber());
                    objPayDet.setCcExpMonth(objPayment.getCcExpMonth());
                    objPayDet.setCcExpYear(objPayment.getCcExpYear());
                    objPayDet.setCcCvvid(objPayment.getCcCvvid());
                    objPayDet.setBankName(objPayment.getBankName());
                    objPayDet.setAmount(objPayment.getAmount());
                    objPayDet.setPaymentStatus(objPayment.getPaymentStatus());  
                    objPayDet.setCcNumber(objPayment.getCcNumber());
                    objPayDet.setIpAddress(objPayment.getIpAddress());
                    
                    
                    
                    objPayDet.setSslResult(sslResult);
                    objPayDet.setSslResultMessage(sslResultMessage);
                    objPayDet.setSslTxnId(sslTxnId);
                    objPayDet.setSslApprovalCode(sslApprovalCode);
                    objPayDet.setSslCvv2Response(sslCvv2Response);
                    objPayDet.setSslAvsResponse(sslAvsResponse);
                    objPayDet.setSslTransactionType(sslTransactionType);
                    objPayDet.setSslInvoiceNo(sslInvoiceNo);
                    objPayDet.setSslEmail(sslEmail);
                    objPayDet.setCheckAmount(0);
              
               //  Debug.print("objPayDet.toString() :" + objPayDet.toString());
                 boolean resultPay  = objPaySessRemote.createPayment(objPayDet);
                 
                 Debug.print("Declined Payment Sucessfully Inserted Status :" + resultPay);
                 
                 boolean output1=remote.getAreaMembHistDetails(objMember,objPayment);
                   
                    //boolean result = remote.insertAreaMembProgram(objMember,objPayment);
               
               HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO");
                if(PhoneVO!=null){
                    PhoneVO.setActive_status(false);
                    //boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);                    
                    //Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for area Member"+status);                    
                }
                
               HLCAccTransactionVO PhoneVO1 =  (HLCAccTransactionVO) session.getAttribute("PhaccTxnVO1");
               
               if(PhoneVO1!=null){
                    PhoneVO1.setActive_status(false);
                    //boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO1);                    
                    //Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO1) for area Member"+status);                    
                }
                                     

                /* AccTransactionVO PhoneVO =  (AccTransactionVO) session.getAttribute("PhaccTxnVO");
                if(PhoneVO!=null){
                    PhoneVO.setActive_status(true);
                    boolean status = mahaRemote.insertAccountTxnDetails(PhoneVO);                    
                    Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no"+status);                    
                }*/
                    request.setAttribute("amt",amount1);
                    session.setAttribute("objPayDet",objPayDet);
                   
                    session.setAttribute("areaName",objMember.getAraeName());                                                        
                    session.setAttribute("areaRidProg",objMember.getAreaProgName());
                    session.setAttribute("progYear",objMember.getAreaProgYear());
                    session.setAttribute("areaRidProgDesc",objMember.getAreaProgDesc());
                  
                 //for decline payment
                 if(session.getAttribute("loggedBy")==null && Integer.parseInt(sslResult)==1){
                    String emailId = (String)session.getAttribute("emailId");
                    String toMailIds[] = {emailId};
                    EmailContent email=new EmailContent();
                    email.setTo(toMailIds);
                    email.setFrom("info@digiblitz.com");
                    email.setSubject("Area Rider Program"); 
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
                                "<span class=\"boldTxt\">Dear Member</span>,<br />"+
                                "<p>We are sorry, but your payment for Area Rider Program with the HLC has been declined. Please try another form of payment or contact us at (703) 779-0440 and press 1.</p><br/>"+
                                "<p>Sincerely,</p><br/>"+
                                "<p>HLC Staff</p>"+
                                " </table>"+
                                "</td></tr>"+
                                "</table>";
                                email.setBody(content);
                                EmailEngine emailEngine = new EmailEngine();
                                boolean emailFlag = emailEngine.sendMimeEmail(email);
                                Debug.print("Email sent sucessfully :"+emailFlag);
                        
                }
                 return mapping.findForward("decline");
                                           
              }
 
        }catch(Exception e){
            e.printStackTrace();            
        }
     
        return null;  
        
    }
   public static Context getInitialContext() throws javax.naming.NamingException {
        Properties p =new Properties();
        p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
        p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
        p.setProperty( "java.naming.provider.url", "localhost:11199" );
        return new javax.naming.InitialContext(p);
    }
 
}