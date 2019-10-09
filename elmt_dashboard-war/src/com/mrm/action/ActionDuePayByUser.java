 /*  Program Name    : ActionDuePayByUser.java
  *  Created Date    : February 24, 2007, 11.26 AM
  *  Author          : Punitha.R
  *  Version         : 1.19
  *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
  
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
  
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
  */
package com.mrm.action;

import com.hlccommon.util.*;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.mrm.actionform.FormHorseUpgrade;
import com.hlcpayment.HLCPaymentSessionRemote;
import com.hlcpayment.HLCPaymentSessionRemoteHome;
import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.MessageResources;

public class ActionDuePayByUser extends Action {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
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
        try{
            String process = (String)request.getParameter("process");
            HttpSession session = request.getSession();
            MessageResources mr=getResources(request);
            String jndiname=mr.getMessage("jndi.kavery");
            String jname=mr.getMessage("jndi.usrreg");
            Context jndiContext = new InitialContext();
            Object objref = jndiContext.lookup(jndiname);
            
            HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
            HLCKaverySessionBeanStatfulRemote remote = home.create();
            
            Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
            HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
            HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
            Object oef = jndiContext.lookup(jname);
            HLCPaymentDetailVO objPayment = new HLCPaymentDetailVO();
            String sesUserId =(String)session.getAttribute("userId");
            Debug.print("sesUserId in ActionDue Pay by User servlet:" + sesUserId);
            
            if(process.equalsIgnoreCase("duepay")){
                Debug.print("Inside Change of UserChangeDuePayAction");
                String horseMemberId = request.getParameter("horseMemberId");
                String competitionName =request.getParameter("competitionName");
                String paymentIdVal = request.getParameter("paymentIdVal");
                String dummyAmount = request.getParameter("amount");
                String chngHorseStat = (String)session.getAttribute("fromWhere");
                String requestId = request.getParameter("requestId");
                 String horseRidOwnStatus = request.getParameter("horseRidOwnStatus");
                    
                
                System.out.print("chngHorseStat in ActionDuePayByUser:" + chngHorseStat);
                System.out.print("requestId in ActionDuePayByUser:" + requestId);
                
                String totalAmount =  "";
                String amount ="";
                if(request.getParameter("totalAmount")!=null || request.getParameter("totalAmount").trim().length()!=0){
                    totalAmount = request.getParameter("totalAmount");
                } else{
                    totalAmount = "0";
                }
                if(dummyAmount !=null || dummyAmount.trim().length()!=0){
                    amount = dummyAmount;
                } else{
                    amount = "0";
                }
                request.setAttribute("requestId",requestId);
                request.setAttribute("horseMemberId",horseMemberId);
                request.setAttribute("competitionName",competitionName);
                request.setAttribute("paymentIdVal",paymentIdVal);
                request.setAttribute("totalAmount",totalAmount);
                request.setAttribute("amount",amount);
                request.setAttribute("chngHorseStat",chngHorseStat);
                request.setAttribute("horseRidOwnStatus",horseRidOwnStatus);
                return mapping.findForward("goToDuePay");
                
            }
            if(process.equalsIgnoreCase("duePayment")){
                Debug.print("Inside the duePayment option");
                
                
                String horseMemberId=request.getParameter("horseMemberId");
                String paymentIdVal=request.getParameter("paymentIdVal");
                String competitionName = request.getParameter("competitionName");
                String finalAmount = request.getParameter("finalAmount");
                String chngHorseStat = request.getParameter("chngHorseStat");
                String requestId = request.getParameter("requestId");
                System.out.print("requestId :" + requestId);
                 String horseRidOwnStatus = request.getParameter("horseRidOwnStatus");
                 System.out.print("horseRidOwnStatus :" + horseRidOwnStatus);
                String emailId = (String)session.getAttribute("emailId");
                FormHorseUpgrade fbean=(FormHorseUpgrade)form;
                System.out.println("FormHorseUpgrade ="+ fbean);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                // Get client's IP address
                String addr = request.getRemoteAddr(); // 123.123.123.123
                Debug.print("Port Value:" + addr);
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
                //float checkAmount = 0;
                String  r11 = fbean.getR11();
                System.out.print("Check Status :" + r11);
                
                if(r11.equals("card")){
                    if(fbean.getCcNumber()==null || fbean.getCcNumber().equals("")){
                        CcNumber = "0";
                    } else{
                        CcNumber = fbean.getCcNumber();
                    }
                    if(fbean.getCcName()==null || fbean.getCcName().equals("")){
                        ccName ="";
                    } else {
                        ccName=fbean.getCcName();
                    }
                    if(!(fbean.getCcType().equals("")) && fbean.getCcType()!=null){
                        ccType = fbean.getCcType();
                    }
                    if(fbean.getCcExpMonth()==null || fbean.getCcExpMonth().equals("")){
                        CcExpMonth =0;
                    } else {
                        CcExpMonth=Integer.parseInt(fbean.getCcExpMonth());
                    }
                    if(fbean.getCcExpYear()==null || fbean.getCcExpYear().equals("")){
                        CcExpYear =0;
                    } else{
                        CcExpYear = Integer.parseInt(fbean.getCcExpYear());
                    }
                    if(fbean.getCcCvvid()==null || fbean.getCcCvvid().equals("")){
                        CcCvvid =0;
                    } else{
                        CcCvvid = Integer.parseInt(fbean.getCcCvvid());
                    }
                    check_date= null;
                    objPayment.setPaymentStatus("Card");
                }
                
                if(r11.equals("check")){
                    if(fbean.getCheckNumber()==null || fbean.getCheckNumber().equals("")){
                        checkNumber="0";
                    } else{
                        checkNumber= fbean.getCheckNumber();
                    }
                    if(fbean.getCheckDate().equals("")){
                        check_date= null;
                    } else{
                        check_date =(Date)sdf.parse(fbean.getCheckDate());
                    }
                    objPayment.setPaymentStatus("Check");
                    
                }
                String tempAmount = request.getParameter("tempAmount");
                /* String tempCheckAmount = request.getParameter("chkBalAmt");*/
                Debug.print("tempAmount value:::::::" + tempAmount);
                if(tempAmount==null || tempAmount==""){
                    totalAmount=0;
                } else{
                    totalAmount = Double.parseDouble(tempAmount);
                }
                Debug.print("totalAmount value:::::::" + totalAmount);
                Debug.print("finalAmount value::::::::::::" + finalAmount);
                bankname =fbean.getBankName();
                nameoncheck =fbean.getNameCheck();
                
                objPayment.setUserId(sesUserId);
                objPayment.setCcName(ccName);
                objPayment.setCcType(ccType);
                objPayment.setCcNumber(CcNumber);
                objPayment.setCcExpMonth(CcExpMonth);
                objPayment.setCcExpYear(CcExpYear);
                objPayment.setCcCvvid(CcCvvid);
                //objPayment.setCheckAmount(checkAmount);
                objPayment.setBankName(bankname);
                objPayment.setCheckName(nameoncheck);
                objPayment.setCheckDate(check_date);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setAmount(totalAmount);
                objPayment.setPaymentDate(new Date());
                String paymentId = remote.getNextId();
                objPayment.setParentPaymentId(paymentIdVal);
                objPayment.setPaymentId(paymentId);
                objPayment.setIpAddress(addr);
                
                if(r11.equals("card")){
                    System.out.println("Sucessfully Redirect to NoVa:");
                    session.setAttribute("objPaymentList",objPayment);
                    System.out.println("Sucessfully Payment objPaymentList:" + objPayment.toString());
                    
                    String expMon = String.valueOf(CcExpMonth);
                    String expYear = String.valueOf(CcExpYear);
                    if(expMon.trim().length()==1) {
                        expMon = "0" + expMon;
                    }
                    System.out.println("expYear:" + expYear);
                    expYear = expYear.substring(2);
                    System.out.println("expMon:" + expMon);
                    System.out.println("expYear:" + expYear);
                    String expDate = expMon + expYear;
                    request.setAttribute("expDate",expDate);
                    
                    request.setAttribute("amount",String.valueOf(totalAmount));
                    
                    request.setAttribute("chkDigit",String.valueOf(CcCvvid));
                    request.setAttribute("email",emailId);
                    request.setAttribute("cardNo",String.valueOf(CcNumber));
                    
                    if(requestId!=null && requestId.trim().length()!=0){
                        session.setAttribute("requestId",requestId);
                    } else{
                        session.setAttribute("requestId",null);
                    }
                     if(horseRidOwnStatus!=null && horseRidOwnStatus.trim().length()!=0){
                        session.setAttribute("horseRidOwnStatus",horseRidOwnStatus);
                    } else{
                        session.setAttribute("horseRidOwnStatus",null);
                    }
                    
                    session.setAttribute("chngHorseStat",chngHorseStat);
                    session.setAttribute("amount",String.valueOf(totalAmount));
                    session.setAttribute("paymentIdVal",paymentIdVal);
                    session.setAttribute("paymentId",paymentId);
                    session.setAttribute("horseName",competitionName);
                    session.setAttribute("horseMemberId",horseMemberId);
                    
                StringBuffer reqURL = request.getRequestURL();
                int lastIndex = reqURL.lastIndexOf("/") ;
                String url ="";
                if(lastIndex!=-1){
                url = reqURL.substring(0,lastIndex+1);
                }
                String tmpNova  = mr.getMessage("userdue.nova");
                String nova = url + tmpNova;
                Debug.print("succsssullll from app file nova........::" + nova);
                Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                request.setAttribute("nova",nova);
                 return mapping.findForward("novaPage");
                  //  return mapping.findForward("UserDueNovaRedir");
                } else{
                    
                    
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                    Debug.print("amount:" + totalAmount);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId)");
                    boolean update_stat = objPaySessRemote.createPayment(objPayment);
                    Debug.print("Update Status "+update_stat);
                    
                    if(update_stat==true){
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
                        request.setAttribute("horseMemberId",horseMemberId);
                        request.setAttribute("horseName",competitionName);
                        request.setAttribute("totalAmount",String.valueOf(totalAmount));
                        return mapping.findForward("DuePayUserSuccess");
                    } else{
                        
                    }
                    
                }
            }
        } catch(Exception e){
            System.out.println("Exception occurs in change payment by admin:" + e.getMessage());
        }
        return null;
        
    }
}
