/*
 * HorsePayDeclineAction.java
 *
 * Created on February 13, 2007, 7:17 PM
 */

package com.mrm.action;
import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.hlccommon.util.Debug;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcreg.util.HLCPaymentVO; 
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
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

public class HorsePayDeclineAction extends Action {
    
    private final static String SUCCESS = "success";
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
                String fwd="";
        HttpSession session = request.getSession();
                    
        MessageResources mr=getResources(request);
        
        String namingfactory=mr.getMessage("ejbclient.namingfactory");
        String contextfactory=mr.getMessage("ejbclient.contextfactory");
        String urlprovider=mr.getMessage("ejbclient.urlprovider");
        String lookupip=mr.getMessage("ejbclient.ip");
        String jndiname=mr.getMessage("jndi.usrreg");
        String jndihorse=mr.getMessage("jndi.kavery");
        String jndiname2=mr.getMessage("jndi.usrreg");
        try
        {
         
            System.setProperty(namingfactory,contextfactory);
            System.setProperty(urlprovider,lookupip);
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            
            Object objref2 = jndiContext.lookup(jndiname2);
                       
        HLCkaverystatelessRemoteHome home = (HLCkaverystatelessRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref2,HLCkaverystatelessRemoteHome.class);
        HLCkaverystatelessRemote remote = home.create();
/// Transaction
        String mahanadhiJndi=mr.getMessage("jndi.acc");
        Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
        Object maha=jndiContext.lookup(mahanadhiJndi);
        HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
        HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();     


            Object objhorse = jndiContext.lookup(jndihorse);
            HLCKaverySessionBeanStatfulRemoteHome home1 = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objhorse,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote1 = home1.create();

            String memberId=(String)session.getAttribute("status_membId");
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");

            String amount=(String)session.getAttribute("amount");
            String paymentId = (String)session.getAttribute("paymentId");
            String horseName = (String)session.getAttribute("horseName");
            String horseMemberId=(String)session.getAttribute("horseMemberId");
            String registrationLevel = (String)session.getAttribute("registrationLevel");
        
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
            
            String riderEmailId = (String) session.getAttribute("ridEmailId");
            Debug.print("riderEmailId::: in HorsePayDeclineAction " + riderEmailId);
            String riderFName = (String) session.getAttribute("ridFName");
            Debug.print("riderFName::: in HorsePayDeclineAction" + riderFName);
            String riderLName = (String) session.getAttribute("ridLName");
            Debug.print("riderLName::: in HorsePayDeclineAction" + riderLName);
            String OwnerFName = (String) session.getAttribute("OwnFName");
            Debug.print("OwnerFName::: in HorsePayDeclineAction" + OwnerFName);
            String OwnerLName = (String) session.getAttribute("OwnLName");
            Debug.print("OwnerLName::: in HorsePayDeclineAction" + OwnerLName);
            String OwnerMailId = (String) session.getAttribute("OwnMailId");
            Debug.print("OwnerMailId::: in HorsePayDeclineAction" + OwnerMailId);
            String membershipName1 = (String) session.getAttribute("memName1");
            Debug.print("membershipName1::: in HorsePayDeclineAction" + membershipName1);
           
            HLCPaymentVO objPayDet = (HLCPaymentVO)session.getAttribute("objPaymentList");       
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
            //IP Address 
            objPayDet.setIpAddress(addr);
        //Activation Date           
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
        //End Activation Date   
            Debug.print("objPayDet::" + objPayDet);
            Debug.print("sslResult::" + sslResult);
            
           if(objPayDet!=null){
                  boolean stat=remote1.updatePaymentStatus(objPayDet);
                  Debug.print("remote.updatePaymentStatus(objPayment) :"+stat);    
            
              if(sslResult.equals("0")){
                      
              //update Payment Status
                    if(paymentId!=null || paymentId.trim().length()!=0){
                        boolean update_payStat = mahaRemote.updatePaymentStatusAccTxnDetails(paymentId);
                        Debug.print("Update Status "+update_payStat);
                    }  
                    
                    boolean statusChange = remote1.statusChangeForHorse(horseMemberId,null);
                    boolean activeStatus = remote1.updateRealtionshipStatus(horseMemberId);
                    String statusId = remote.getStatusIBasedOnStatus("Active");
                    boolean historyStatus = remote.updateMemberHistoryDetailStatus(horseMemberId,statusId,paymentId);
                    
                    Debug.print("!@#$%^&*())_History Status: "+historyStatus);
                    Debug.print("statusChange:0" + statusChange);
                        Debug.print("activeStatus:0" + activeStatus);
                        Debug.print(" Horse Registration status Changed for Credit Card:" + statusChange);
                        //Setting the request status for the horse
                        boolean update_req_stat = remote1.updateRequestStatus(horseMemberId,"","",checkActDate);
                        Debug.print("Update Request Status "+update_req_stat);
                        
                        if(riderEmailId!=null && riderEmailId.length()!=0){
                        Debug.print("ridEmailId::: inside if condition in NewHorseMembPayAction" + riderEmailId);     
                        String toMailIds1[] = {riderEmailId};
                        EmailContent email1=new EmailContent();
                        email1.setTo(toMailIds1);
                        email1.setFrom("anandv@digiblitz.com");
                        email1.setSubject("Horse Member Registration");
                                
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
                                "<p>Thank you for registering your horse <strong>" + horseName +" </strong> with the HLC.<p>"+
                                "<p>Please retain the following information for your records:<p>"+
                                "Horse Name: "+ horseName +"<br />"+
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
                                "Horse Registration level:"+membershipName1+".This is a lifetime registration.<br/>"+
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
                        email2.setFrom("anandv@digiblitz.com");
                        email2.setSubject("Horse Member Registration");
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
                                "Horse Registration ID:"+ horseMemberId+"<br />"+
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
                        
                        
                        request.setAttribute("totalAmount",amount);
                        request.setAttribute("registrationLevel",registrationLevel);
                        request.setAttribute("horseMemberId",horseMemberId);
                        request.setAttribute("paymentId",paymentId);
                        request.setAttribute("horseName",horseName);
                        request.setAttribute("ccType",objPayDet.getCcType());
                        request.setAttribute("ccNumber",objPayDet.getCcNumber());
                        
                        session.setAttribute("totalAmount",null);
                        session.setAttribute("registrationLevel",null);
                        session.setAttribute("horseMemberId",null);
                        session.setAttribute("paymentId",null);
                        session.setAttribute("horseName",null);
                        return mapping.findForward("backtoList");
                     }
                else{                
                        Debug.print("Not Inserted..:");
                        session.setAttribute("objPayment",objPayDet);
                        request.setAttribute("totalAmount",amount);
                        request.setAttribute("registrationLevel",registrationLevel);
                        request.setAttribute("horseMemberId",horseMemberId);
                        request.setAttribute("paymentId",paymentId);
                        request.setAttribute("horseName",horseName);
                        
                        Debug.print("totalAmount"+amount);
                        Debug.print("registrationLevel"+registrationLevel);
                        Debug.print("horseMemberId"+horseMemberId);
                        Debug.print("horseName"+horseName);
                        Debug.print("paymentId"+paymentId);
                        
                        String toMailIds[] = {emailId};
                        EmailContent email=new EmailContent();
                        email.setTo(toMailIds);
                        email.setFrom("anandv@digiblitz.com");
                        email.setSubject("Horse Registration Decline");
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
                                "<p>We are sorry, but your payment for the horse registration of  <strong>" + horseName +" </strong> has been declined.<p>"+
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
        
         catch (Exception ex) {
            System.err.println("Caught an exception.");
            ex.printStackTrace();
        }
        
        return null;
        
    }
}
