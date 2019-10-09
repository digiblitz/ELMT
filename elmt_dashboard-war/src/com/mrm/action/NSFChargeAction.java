/*
 * Program Name     :   NSFChargeAction.java
 * Created Date     :   May 24, 2007, 10:57 AM
 * Author           :   Hari
 * Copy Right       :   digiBlitz Technologies Inc /  digiBlitz Technologies (P) Ltd
 * Version          :   1.3
 * CopyRightInformation:
 *  (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
 *  916 W. Broad Street Suite 205, FallsChurch, VA 22046.
 *  This document is protected by copyright. No part of this document may be reproduced in any form by any means without
 *  prior written authorization of Sun and its licensors. if any.
 *  The information described in this document may be protected by one or more U.S.patents.foreign patents,or
 *  pending applications.
 */


package com.mrm.action;

import com.hlccommon.util.Debug;
import com.hlcform.stateless.HLCkaverystatelessRemote;
import com.hlcform.stateless.HLCkaverystatelessRemoteHome;
import com.mrm.actionform.FormHorseUpgrade;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.hlcform.util.HLCPaymentDetailVO;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class NSFChargeAction extends Action {
    
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     
            MessageResources mr = getResources(request);
            Context jndiContext = new InitialContext();
            
            String jndiName =mr.getMessage("jndi.usrreg");
            Object objref = jndiContext.lookup(jndiName);
            HLCkaverystatelessRemoteHome remoteHome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote statelessRemote = remoteHome.create();        
            
            HttpSession session=request.getSession();
            String usrNam=(String) session.getAttribute("loginName");
            String usrId=(String) session.getAttribute("userId");
            
            String process = request.getParameter("process");
            Debug.print("Process in Servlet is "+process);
            
            if(process != null){
                
                if(process.equalsIgnoreCase("NSFPayProceed")){
                 
                    String parentPayId = (String) request.getParameter("parentPayId");
                    String paymentId = (String) request.getParameter("paymentId");
                    Debug.print("parentPayId " + parentPayId);
                    Debug.print("paymentId " + paymentId);
                    String[] serviceType = (String[]) statelessRemote.loadServiceType();
                    request.setAttribute("serviceType",serviceType);                    
                    if(parentPayId!=null && paymentId!=null){
                        request.setAttribute("parentPayId",parentPayId);
                        request.setAttribute("paymentId",paymentId);
                    }
                    return mapping.findForward("payProceed");
                }
                
                if(process.equalsIgnoreCase("pay")){
                    HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
                    Debug.print("Inside the payment option");
                    String sesUserId = (String)session.getAttribute("userId");
                    String emailId = (String)session.getAttribute("emailId");
                    
                    FormHorseUpgrade fbean=(FormHorseUpgrade)form;
                    System.out.println("FormHorseUpgrade ="+ fbean);
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                
                    String paymentId= request.getParameter("paymentId");
                    String parentpayId= request.getParameter("parentPayId");
                    String totalAmount1 = request.getParameter("totalAmount");
                    String tempAmount;
                    String addr = request.getRemoteAddr(); // 123.123.123.123
                    
                    Debug.print("paymentId     "+paymentId);
                    Debug.print("parentpayId   "+parentpayId);
                    Debug.print("totalAmount1  "+totalAmount1);
                    Debug.print("Port Value    "+addr);
 
                    int  CcExpMonth = 0;
                    int CcExpYear = 0;
                    int CcCvvid =0;
                    double totalAmount =0;
                    String CcNumber ="0";
                    String checkNumber="0";
                    Date check_date = null;
                    Date comp_date = null;
                    String ccType=null;
                    String ccName=null;
                    String nameoncheck = null;
                    String bankname = null; 
                    float checkAmount = 0;
                    String  r11 = fbean.getR11();
                    System.out.print("Check Status :" + r11);
               
                    if(r11.equals("card")){
                        if(fbean.getCcNumber()==null || fbean.getCcNumber().equals("")){
                            CcNumber = "0";
                        }
                        else{
                            CcNumber = fbean.getCcNumber();
                        }
                        if(fbean.getCcName()==null || fbean.getCcName().equals("")){
                            ccName ="";
                        }
                        else {
                            ccName=fbean.getCcName();
                        }
                        if(!(fbean.getCcType().equals("")) && fbean.getCcType()!=null){
                            ccType = fbean.getCcType();  
                        }
                        if(fbean.getCcExpMonth()==null || fbean.getCcExpMonth().equals("")){
                            CcExpMonth =0;
                        }
                        else {
                            CcExpMonth=Integer.parseInt(fbean.getCcExpMonth());
                        }
                        if(fbean.getCcExpYear()==null || fbean.getCcExpYear().equals("")){
                            CcExpYear =0;
                        }
                        else{
                            CcExpYear = Integer.parseInt(fbean.getCcExpYear());
                        }
                        if(fbean.getCcCvvid()==null || fbean.getCcCvvid().equals("")){
                            CcCvvid =0;
                        }
                        else{
                             CcCvvid = Integer.parseInt(fbean.getCcCvvid());
                        }
                        check_date= null;
                        objPayment.setPaymentStatus("Card");
                    }
      
                    if(r11.equals("check")){
                        if(fbean.getCheckNumber()==null || fbean.getCheckNumber().equals("")){
                        checkNumber="0";
                        }
                        else{
                        checkNumber= fbean.getCheckNumber();
                        }
                        if(fbean.getCheckDate().equals("")){
                        check_date= null;
                        }
                        else{
                        check_date =(Date)sdf.parse(fbean.getCheckDate());
                        }
                        tempAmount = fbean.getTotalAmount();
                        Debug.print("Inside check "+tempAmount);
                        objPayment.setPaymentStatus("Check");

                    }
                    
                    tempAmount = request.getParameter("tempAmount");
                    System.out.println("tempAmount" + tempAmount);
                    if(tempAmount==null || tempAmount==""){
                        totalAmount=0; 
                    }
                    else{
                        totalAmount = Double.parseDouble(tempAmount);
                    } 
                 
                    bankname =fbean.getBankName();
                    nameoncheck =fbean.getNameCheck();
                    objPayment.setUserId(sesUserId);
                    objPayment.setCcName(ccName);
                    objPayment.setCcType(ccType);
                    objPayment.setCcNumber(CcNumber);
                    objPayment.setCcExpMonth(CcExpMonth);
                    objPayment.setCcExpYear(CcExpYear);
                    objPayment.setCcCvvid(CcCvvid);
                    objPayment.setBankName(bankname);
                    objPayment.setCheckName(nameoncheck);
                    objPayment.setCheckDate(check_date);
                    objPayment.setCheckNumber(checkNumber);
                    //objPayment.getCheckAmount();
                    objPayment.setAmount(totalAmount);
                    objPayment.setPaymentDate(new Date());
                    objPayment.setPaymentId(paymentId);
                    objPayment.setParentPaymentId(parentpayId); 
                    //IP Address
                    objPayment.setIpAddress(addr);
                    boolean updBal = false;

                    if(r11.equals("card")){
                        System.out.println("Sucessfully Redirect to NoVa:");
                        System.out.println("Sucessfully Payment objPaymentList:" + objPayment.toString());
                        request.setAttribute("email",emailId);
                        request.setAttribute("cardNo",String.valueOf(CcNumber));
                        String expMon = String.valueOf(CcExpMonth);
                        String expYear = String.valueOf(CcExpYear);
                        
                        if(expMon.trim().length()==1) {
                                expMon = "0" + expMon;
                        }
                        
                        expYear = expYear.substring(2);
                        System.out.println("expYear:" + expYear);
                        System.out.println("expMon:" + expMon);
                        
                        String expDate = expMon + expYear;
                        String res ="";
                        
                        request.setAttribute("expDate",expDate);
                        request.setAttribute("amount",String.valueOf(totalAmount));
                        request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                
/*                      boolean payment_stat = statelessRemote.insertPayment(objPayment);
                        Debug.print("Update Status "+payment_stat);     */

                        session.setAttribute("amount",String.valueOf(totalAmount));
                        session.setAttribute("paymentId",paymentId);
                        session.setAttribute("totalAmount",totalAmount1);
                        session.setAttribute("PayDetVO",objPayment);

                        StringBuffer reqURL = request.getRequestURL();
                        int lastIndex = reqURL.lastIndexOf("/") ;
                        String url ="";
                        
                        if(lastIndex!=-1){
                         url = reqURL.substring(0,lastIndex+1);
                        }
                        
                        String tmpNova  = mr.getMessage("NSFCharge.nova");
                        String nova = url + tmpNova;
                        Debug.print("succsssullll from app file nova........::" + nova);
                        Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                        request.setAttribute("nova",nova);
                        return mapping.findForward("novaPage");

                    }
                else{
                    
                       Debug.print("paymentId in check:" + paymentId);
                       Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                       Debug.print("amount:" + totalAmount);

                       request.setAttribute("totalAmount",totalAmount1);
                       request.setAttribute("totalAmount",String.valueOf(objPayment.getAmount()));

                       request.setAttribute("CheckNo",objPayment.getCheckNumber());
                       request.setAttribute("Chkdte",objPayment.getCheckDate());
                       request.setAttribute("banknme",objPayment.getBankName());
                       objPayment.setPendingAmount(0);
                       //objPayment.setNsf_charge_status(true);
                       String sessionUser = (String)session.getAttribute("loggedBy");
                       Debug.print("sessionUser" + sessionUser);

                       boolean payment_stat = statelessRemote.updateNSFPayment(objPayment);
                       Debug.print("Update Status "+payment_stat);
                   
                       if(payment_stat==true){
                            String toMailIds[] = {emailId};
                            EmailContent email=new EmailContent();
                            email.setTo(toMailIds);
                            email.setFrom("anandv@digiblitz.com");
                            email.setSubject("Horse Member Matching");
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
                                    "<p>Dear User,"+

                                   "<p>You have successfully upgraded your payment Request. It will be reviewed and Approved within 24 hrs.<p>"+
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
                                email.setBody(content);
                                EmailEngine emailEngine = new EmailEngine();
                                boolean emailFlag = emailEngine.sendMimeEmail(email);
                                Debug.print("Email sent sucessfully :"+emailFlag);
                                return mapping.findForward("payConf");
                    }   
                }
                }/// end of process = pay
            }
        return mapping.findForward("login");
    }
}
