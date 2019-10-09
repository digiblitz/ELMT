/*
 * HorseNotRegisteredAction.java
 *
 * Created on March 7, 2007, 4:18 PM
 */

package com.mrm.action;


import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
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

public class HorseNotRegisteredAction extends Action {
    
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        HLCKaverySessionBeanStatfulRemote remote=null;
        
        
        try{
            HttpSession session = request.getSession();
            MessageResources mr=getResources(request);
            String namingfactory=mr.getMessage("ejbclient.namingfactory");
            String contextfactory=mr.getMessage("ejbclient.contextfactory");
            String urlprovider=mr.getMessage("ejbclient.urlprovider");
            String lookupip=mr.getMessage("ejbclient.ip");
            
            String jndihorse=mr.getMessage("jndi.kavery");
            
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
            
            
            String emailId = (String)session.getAttribute("emailId");
            String userId = (String)session.getAttribute("userId");
            String totalAmount =(String)session.getAttribute("amount");
            String registrationLevel = (String)session.getAttribute("registrationLevel");
            String res =(String)session.getAttribute("horseMemberId");
            String horseName =(String)session.getAttribute("horseName");
            String paymentId = (String)session.getAttribute("paymentId");
           
            Debug.print("registrationLevel:"+registrationLevel);
            Debug.print("horseName:"+horseName);
            Debug.print("Sucessfully return from NOVA UserId:" + (String)session.getAttribute("userId"));
            Debug.print("Return from Nova UserCode:" + (String)session.getAttribute("userCode"));
           
            String horseStatus =(String)session.getAttribute("horseStatus");
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            String activationDate = (String)session.getAttribute("activationDate");
            System.out.print("activationDate"+activationDate);
           
            Date checkActDate =null;
            if(activationDate!=null && activationDate.trim().length()!=0){
                checkActDate =  sdf.parse(activationDate);
                Debug.print("checkActDate:" + checkActDate);
            } else{
                checkActDate = new Date();
                
            }
                     
            Debug.print("paymentId:" + paymentId);
            
            Debug.print("horseStatus:" + horseStatus);
            
            if(horseStatus==null){
                horseStatus = "false";
            }
            
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
                
                
                HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
                objPayDet = (HLCPaymentDetailVO)session.getAttribute("objPaymentList");
                String horseRelId[] = (String[])remote.getAllEmailIds(res);
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
                
                boolean resultPay =false;
                if(objPayDet!=null){
                    resultPay  = objPaySessRemote.createPayment(objPayDet);
                    Debug.print("resultPay value is:" + resultPay);
                }
                Debug.print("sslResult in HorseNotRegisteredAction: " + sslResult);
                if(sslResult.equals("0")) {
                    
                    if(resultPay == true && res!=null){
                        boolean statusChange = remote.statusChangeForHorse(res,null);
                        boolean activeStatus = remote.updateRealtionshipStatus(res);
                        remote.updateRequestStatus(res,"","Horse Registration",checkActDate);
                        Debug.print("statusChange:0" + statusChange);
                        Debug.print("activeStatus:0" + activeStatus);
                        Debug.print("activeStatus:0" + activeStatus);
                        Debug.print(" Horse Registration status Changed for Credit Card:" + statusChange);
                        
                        //Phone Service
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
                        //email.setAttachments();
                        EmailEngine emailEngine = new EmailEngine();
                        boolean emailFlag = emailEngine.sendMimeEmail(email);
                        Debug.print("Email sent sucessfully :"+emailFlag);
                        
                        request.setAttribute("totalAmount",totalAmount);
                        request.setAttribute("registrationLevel",registrationLevel);
                        request.setAttribute("horseMemberId",res);
                        request.setAttribute("horseName",horseName);
                        
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
                        
                        //String toMailIds[] = {emailId};
                        EmailContent email1=new EmailContent();
                        email1.setTo(horseRelId);
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
                                "<span class=\"boldTxt\">Dear User</span>,<br /><br />"+
                                "<p>Dear User,"+
                                
                                "<p>You have successfully registered the horse. your horse member id is" + res + ". It will be reviewed and Approved within 24 hrs.<p>"+
                                "Thank you for using this service.<p>"+
                                
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
                        email1.setBody(content1);
                        EmailEngine emailEngine1 = new EmailEngine();
                        boolean emailFlag1 = emailEngine1.sendMimeEmail(email1);
                        Debug.print("Email sent sucessfully to horse related members:"+emailFlag1);
                        return mapping.findForward("confirm");
                    }
                } 
                else{
                    Debug.print("Not Inserted:");
                    Debug.print("Inside the New Horse Member Payment Declined State");
                    Debug.print("horsememId :"+res);
                    Debug.print("Not inserted"+paymentId);
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
                        
                        
                    return mapping.findForward("decline");
                }
            }
            
        } catch(Exception ecommon){
            Debug.print("" + ecommon);
        }
        return null;
        
    }
}
