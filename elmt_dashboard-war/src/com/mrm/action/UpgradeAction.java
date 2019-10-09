/*
 * UpgradeAction.java
 *
 * Created on December 5, 2006, 10:26 PM
 */

package com.mrm.action;
 
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlcform.util.HLCMemberHistoryDetail;
import com.hlccommon.util.Debug;
import com.hlccommon.util.HLCPaymentResultVO;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import java.text.SimpleDateFormat;
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

 
/**
 *
 * @author hari
 * @version
 */

public class UpgradeAction extends Action {
    
  
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HLCKaverySessionBeanStatfulRemote remote=null;
         String horsememId=null;
          
       try{
            Debug.print("Inside Upgrade Action");
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

///Transaction Entry
            String mahanadhiJndi=mr.getMessage("jndi.acc");
            Debug.print("mahanadhiJndi  :" +mahanadhiJndi);            
            Object maha=jndiContext.lookup(mahanadhiJndi);
            HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
            HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();              
            
// History Table Entry
            String jname=mr.getMessage("jndi.usrreg");
            Object oef = jndiContext.lookup(jname);
            HLCkaverystatelessRemoteHome userhome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(oef,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote userremote = userhome.create();
            
            
            
            Object objhorse = jndiContext.lookup(jndihorse);
            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objhorse,HLCKaverySessionBeanStatfulRemoteHome.class);
            remote = home.create();

            Object objPayss= jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();

            String userId = (String)session.getAttribute("userId");
            String totalAmount =(String)session.getAttribute("totalAmount");
            String horseMemberId = (String)session.getAttribute("horseMemberId");
            String horseName =(String)session.getAttribute("horseName");
            String paymentId = (String)session.getAttribute("paymentId");
            String membershipTypeId =(String) session.getAttribute("membershipTypeId");
            String serviceType =(String) session.getAttribute("serviceType");
            Debug.print("Inside Upgrade Action: Payment Id "+paymentId);
            
             HLCPaymentResultVO payRes = (HLCPaymentResultVO) session.getAttribute("payRes");
            HLCPaymentDetailVO objPaypal = (HLCPaymentDetailVO) session.getAttribute("objPayDet");
            String[] results = (String[]) session.getAttribute("results");
            String statusId3 = (String) session.getAttribute("statusId3");
            String statusId = (String) session.getAttribute("statusId");
            System.out.println("payRes in card servlet: " + payRes);
            System.out.println("results in card servlet: " + results);
            System.out.println("statusId3 in card servlet: " + statusId3);
            System.out.println("statusId in card servlet: " + statusId);
            HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
            objPayDet = (HLCPaymentDetailVO)session.getAttribute("payVOList");       
            String activationDate = (String)session.getAttribute("activationDate");
            System.out.print("activationDate" + activationDate);
            
            
            
//Activation Date             
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
//End Activation Date   
            if (statusId3 == null || statusId3.equalsIgnoreCase("Failure")) {
                String errorCd = payRes.getErrorCd();
                String lngMsg = payRes.getLngMsg();
              //  PaymentDetailVO objPayDet = new PaymentDetailVO();
                String emailId = (String)session.getAttribute("emailId");
                String toMailIds[] = {emailId};
                EmailContent email = new EmailContent();
                email.setTo(toMailIds);
                email.setFrom("info@digiblitz.com");
                email.setSubject("Upgrade Horse Decline");
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
                     
                    //double payAmt=objPayment.getAmount();
                              
                   //String amt=String.valueOf(payAmt);
                  String amt=(String)session.getAttribute("amount");
                                                     
                   session.setAttribute("amt",amt);
                 
                 
                   request.setAttribute("horseMemberId",horseMemberId);
                   request.setAttribute("paymentId",(String)session.getAttribute("paymentId"));
                   request.setAttribute("registrationLevel",(String)session.getAttribute("registrationLevel"));
                   request.setAttribute("horseName",horseName);
                   request.setAttribute("totalAmount",amt);
                  // session.setAttribute("objPayDet",objPayDet);
                  System.out.println("Horse Name  and Registration Level in decline"+horseName+" "+(String)session.getAttribute("registrationLevel"));
                  
                  session.setAttribute("paymentId", (String)session.getAttribute("paymentId"));
                  System.out.println("Payment id in UpgradeAction+++++++++++"+(String)session.getAttribute("paymentId"));
                  System.out.println("Amount In Second sefverlet decline"+amt);
                request.setAttribute("msg", "Payment Declined");
                request.setAttribute("errorCd", errorCd);
                request.setAttribute("lngMsg", lngMsg);
                
                   Debug.print("Email sent sucessfully :" + emailFlag);

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
                
                 System.out.println("inside user id+++++++++++++++++++++++++");
               // PaymentDetailVO objPayDet = new PaymentDetailVO();
                objPayDet = (HLCPaymentDetailVO) session.getAttribute("objPaymentVO");
                //Debug.print("objpay det....."+objPayDet);
              
                String sslResult = objPaypal.getSslResult();
                String sslResultMessage = objPaypal.getSslResultMessage();
                String sslTxnId = objPaypal.getSslTxnId();
                String sslApprovalCode = request.getParameter("ssl_approval_code");
                String sslCvv2Response = objPaypal.getSslCvv2Response();
                String sslAvsResponse = objPaypal.getSslAvsResponse();
                String sslTransactionType = objPaypal.getSslTransactionType();
                String sslInvoiceNo = objPaypal.getSslInvoiceNo();
                String sslEmail = objPaypal.getSslEmail();
               // String sslinvoiceId=objPaypal.getInVoiceID();
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
                objPayDet.setSslResult(sslResult);
                objPayDet.setSslResultMessage(sslResultMessage);
                objPayDet.setSslTxnId(sslTxnId);
                objPayDet.setSslApprovalCode(sslApprovalCode);
                objPayDet.setSslCvv2Response(sslCvv2Response);
                objPayDet.setSslAvsResponse(sslAvsResponse);
                objPayDet.setSslTransactionType(sslTransactionType);
                objPayDet.setSslInvoiceNo(sslInvoiceNo);
                objPayDet.setSslEmail(sslEmail);
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
                 String sessionInvoiceId = (String) session.getAttribute("sessionInvoiceId");
                 Debug.print("seesion id is..............."+sessionInvoiceId);
              

                boolean resultPay = false;

                if(objPayDet!=null){
                    resultPay  = objPaySessRemote.createPayment(objPayDet);
                    Debug.print("resultPay:" +resultPay );
                    Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                }
                String HmembAmount = (String) session.getAttribute("membershipAmount");
                
            boolean res = remote.upgradeHorse(horseMemberId, membershipTypeId, paymentId, serviceType, HmembAmount, totalAmount);
            Debug.print("result of Upgrade "+res);
            
            Debug.print("SSl Result"+sslResult);
             
            String regstrBy="";
            String riderFName="";
            String riderLName="";
            String OwnerMailId="";
            String membershipName1="";
            String membertype="";
            String upgrdedType="";
            String ownRelName="";
            String ownFName="";
            String ownLName="";
            regstrBy= (String)session.getAttribute("regstrBy");
             Debug.print("regstrBy::: in UpgradeAction " + regstrBy);
            riderFName = (String) session.getAttribute("riderFName");
             Debug.print("riderFName::: in UpgradeAction " + riderFName);
            riderLName = (String) session.getAttribute("riderLName");
             Debug.print("riderLName::: in UpgradeAction " + riderLName);
            OwnerMailId = (String) session.getAttribute("OwnerMailId");
             Debug.print("OwnerMailId::: in UpgradeAction " + OwnerMailId);
             membershipName1 = (String) session.getAttribute("membershipName1");
             Debug.print("membershipName1::: in UpgradeAction " + membershipName1);
              membertype = (String) session.getAttribute("membertype");
             Debug.print("membertype::: in UpgradeAction " + membertype);
         upgrdedType = (String) session.getAttribute("upgradedType");
             Debug.print("upgrdedType::: in UpgradeAction " + upgrdedType);
             ownRelName =(String)session.getAttribute("ownRelName");
             ownFName =(String)session.getAttribute("ownFName");
             ownLName=(String)session.getAttribute("ownLName");
            
            //// Start History Table Entries
            Debug.print("History Table Entries");
            HLCMemberHistoryDetail detailVO = new HLCMemberHistoryDetail();
            
            java.util.Calendar toDay = java.util.Calendar.getInstance();
            int newyear = toDay.get(Calendar.YEAR);
            int new_month = toDay.get(Calendar.MONTH);
            String membershipTypeId1 = (String) session.getAttribute("membershipTypeId");
            String membershipName = (String) session.getAttribute("membershipName");
            String horseMemberId1 = (String) session.getAttribute("horseMemberId");
            
            if(membershipTypeId1!=null || membershipTypeId1.trim().length()!=0){
                String statid ="";
                
                if(sslResult.equals("0")) {
                    statid=userremote.getStatusIBasedOnStatus("Active");
                } else{
                    statid=userremote.getStatusIBasedOnStatus("Pending");
                }
                
                Debug.print("New Horse Member Id Generated is "+res);
                Debug.print("New Payment Id Generated is "+paymentId);
                Debug.print("New Member Type Id is "+membershipTypeId1);
                Debug.print("New Member Type Name is "+membershipName);
                
                detailVO.setTo_year(newyear);
                detailVO.setPayment_id(paymentId);
                detailVO.setMemberId(horseMemberId1);
                detailVO.setMembership_action("Upgrade");
                detailVO.setMembership_type_id(membershipTypeId1);
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
                
                
            if(sslResult.equals("0")){
                       boolean idExist = userremote.isInvoiceIdExist(sessionInvoiceId);
                 Debug.print("idExist value is:" + idExist);    
                
                  if(!idExist){
                    resultPay  = objPaySessRemote.createPayment(objPayDet);
                    Debug.print("resultPay:" +resultPay );
                  //  Debug.print("Sucessfully Payment Inserted:" + objPayDet.toString());
                  //  session.setAttribute("sessionResultPay", "true");
                  }

                        if(res==true){
                        boolean changeStatus = remote.statusChangeForHorse(horseMemberId,null);
                        boolean update_req_stat = remote.updateRequestStatus(horseMemberId,"","",checkActDate);
                        Debug.print("Update Request Status "+update_req_stat);
                        Debug.print("changed Status "+changeStatus);

                        //Phone Service
                        HLCAccTransactionVO PhoneVO =  (HLCAccTransactionVO) session.getAttribute("PhoneVO");
                        if(PhoneVO!=null){
                                boolean phone_status = mahaRemote.insertAccountTxnDetails(PhoneVO);
                                Debug.print("mahaRemote.insertAccountTxnDetails(PhoneVO) for Member addon no "+phone_status);                    
                        } 

                        //Horse Member
                        if(session.getAttribute("horseMem")!=null){
                            HLCAccTransactionVO horseMem =  (HLCAccTransactionVO) session.getAttribute("horseMem");
                            if(horseMem!=null){
                                boolean horse_status = mahaRemote.insertAccountTxnDetails(horseMem);
                                Debug.print("mahaRemote.insertAccountTxnDetails(horseMem)  "+horse_status);                    
                            }                
                        }                          

                        //Previous Horse Member
                        if(session.getAttribute("PrevMembTyp")!=null){
                            HLCAccTransactionVO prevHorseMem =  (HLCAccTransactionVO) session.getAttribute("PrevMembTyp");
                            if(prevHorseMem!=null){
                                boolean prev_horse_status = mahaRemote.insertAccountTxnDetails(prevHorseMem);
                                Debug.print("mahaRemote.insertAccountTxnDetails(horseMem)  "+prev_horse_status);                    
                            }                
                        }
                        
                      //update Payment Status
                        if(paymentId!=null || paymentId.trim().length()!=0){
                            boolean update_payStat = mahaRemote.updatePaymentStatusAccTxnDetails(paymentId);
                            Debug.print("Update Status "+update_payStat);
                        }                          
                        
                        
                        String emailId = (String)session.getAttribute("emailId");
                        String toMailIds[] = {OwnerMailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("info@digiblitz.com");
                        email.setSubject("Horse Member Upgrade");
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
                                "<p>Thank you for upgrading "+horseName+" to "+upgrdedType+" status.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ horseName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                 "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
                                "Owner(s):"+ regstrBy+"<br/>"+
                                "Rider(s):"+ riderFName+" "+riderLName+"<br/>"+                               
                                "<p>Additional information regarding "+ horseName +" can be viewed by logging in to the HLC Online Services.<p>"+ 
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
                        
                        request.setAttribute("totalAmount",totalAmount);
                        request.setAttribute("horseMemberId",horseMemberId); 
                        request.setAttribute("horseName",horseName);            
                        
                        
                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("horseName",null);
                        session.setAttribute("registrationLevel",null);
                        session.setAttribute("objPaymentList",null);
                        session.setAttribute("totalAmount",null);
                    
                        return mapping.findForward("confirm");      
                    }
                 }
             else{
                 Debug.print("Not Inserted:");
                 Debug.print("Inside the Upgrade Action Decline");
                 session.setAttribute("totalAmount",totalAmount);
                 request.setAttribute("horseMemberId",horseMemberId);
                 session.setAttribute("objPayDet",objPayDet);
                 session.setAttribute("paymentId",paymentId);
                 session.setAttribute("horseName",horseName);
                 
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
                
                 request.setAttribute("horseName",horseName);
                 request.setAttribute("paymentId",paymentId);
                 request.setAttribute("totalAmount",totalAmount);
                 request.setAttribute("registrationLevel","Limited Status");
               
                 return mapping.findForward("decline");
  
              }
              }
             
          
       }
     catch(Exception ecommon){
          Debug.print("" + ecommon);
          
 
      }
        return null;//mapping.findForward("confirm");
        
    }
}