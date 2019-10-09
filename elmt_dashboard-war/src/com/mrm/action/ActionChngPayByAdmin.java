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
 /* Program Name    : ActionChngPayByAdmin.java
 *  Created Date    : February 21, 2007, 12:13 PM
 *  Author          : Punitha.R 
 *  Version         : 1.14
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
 
package com.mrm.action;

import com.hlcaccounts.session.HLCMahanadhiSessionRemote;
import com.hlcaccounts.session.HLCMahanadhiSessionRemoteHome;
import com.hlcaccounts.util.HLCAccTransactionVO;
import com.hlcaccounts.util.HLCAccTxnTypeDetailVO;
import com.hlccommon.util.Debug;
import com.hlcreg.util.HLCPaymentVO; 
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemote;
import com.hlckavery.statful.HLCKaverySessionBeanStatfulRemoteHome;
import com.hlcutil.HLCPaymentDetailVO;
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
 

public class ActionChngPayByAdmin extends Action {
    
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
       
        HttpSession session = request.getSession();
        MessageResources mr=getResources(request);
        String jndiname=mr.getMessage("jndi.kavery");
        String jname=mr.getMessage("jndi.usrreg");
        Context jndiContext = new InitialContext();
        Object objref = jndiContext.lookup(jndiname);
       
        HLCKaverySessionBeanStatfulRemoteHome home = (HLCKaverySessionBeanStatfulRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objref,HLCKaverySessionBeanStatfulRemoteHome.class);
        HLCKaverySessionBeanStatfulRemote remote = home.create();

//Transaction         
        String mahanadhiJndi=mr.getMessage("jndi.acc");
        Debug.print("mahanadhiJndi  :" +mahanadhiJndi);
        Object maha=jndiContext.lookup(mahanadhiJndi);
        HLCMahanadhiSessionRemoteHome mahaHome = (HLCMahanadhiSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(maha,HLCMahanadhiSessionRemoteHome.class);
        HLCMahanadhiSessionRemote mahaRemote = mahaHome.create();         
        
        Object objPayss=jndiContext.lookup("ejb/HLCPaymentSessionBean");
        HLCPaymentSessionRemoteHome objPaySessHome = (HLCPaymentSessionRemoteHome)
        javax.rmi.PortableRemoteObject.narrow(objPayss,HLCPaymentSessionRemoteHome.class);
        HLCPaymentSessionRemote objPaySessRemote = objPaySessHome.create();
        Object oef = jndiContext.lookup(jname);
        HLCPaymentVO objPayment = new HLCPaymentVO();
        HLCPaymentDetailVO objPayDetail = new HLCPaymentDetailVO();
         String process = (String)request.getParameter("process");
        System.out.println("process" + process);
        
         if(process==null || process.trim().length()==0){
                return mapping.findForward("login");
         }
        
        
        if(process!=null && process.equalsIgnoreCase("chngpay")){
            Debug.print("Inside Change of AdminChangePayAction");            
      
            String horseMemberId = (String)session.getAttribute("horseMemberId");
            String competitionName =(String)session.getAttribute("competitionName");
            String paymentIdVal = (String)session.getAttribute("paymentIdVal");
            String regByuserId = (String)session.getAttribute("regByuserId");
            String totalAmount =  "";
            String pendingAmount =  "";
           
            String[] paydet = (String[])remote.getHorsePaymentDetails(paymentIdVal);
            if(paydet!=null && paydet.length !=0){
              totalAmount =  paydet[6]; 
              pendingAmount = paydet[10];
            }
            request.setAttribute("horseMemberId",horseMemberId);
            request.setAttribute("competitionName",competitionName);
            request.setAttribute("paymentIdVal",paymentIdVal);
            request.setAttribute("totalAmount",totalAmount);
            request.setAttribute("regByuserId",regByuserId);
            request.setAttribute("pendingAmount",pendingAmount);
           
          

            return mapping.findForward("goToChngPay");
        }
        if(process!=null && process.equalsIgnoreCase("updatePayment")){
            Debug.print("Inside the updatePayment option");
            
                String regByuserId =null;
                String horseMemberId=request.getParameter("horseMemberId");
                String paymentIdVal=request.getParameter("paymentIdVal");
                String competitionName = request.getParameter("competitionName");
                String tempPendingAmount = request.getParameter("tempPendingAmount");
                float pendingAmount =0;
                float tempPendAmount =0;
                if(tempPendingAmount!=null && tempPendingAmount.trim().length()!=0){
                    pendingAmount = Float.parseFloat(tempPendingAmount);
                }
                if(request.getParameter("regByuserId")!=null){
                     regByuserId = request.getParameter("regByuserId");
                }
                /*Debug.print("horseMemberId"+horseMemberId);
                Debug.print("competitionName"+competitionName);
                Debug.print("paymentIdVal"+paymentIdVal);
                Debug.print("regByuserId in update::"+regByuserId);*/
            
                String sesUserId = (String)session.getAttribute("userId");
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
                    objPayment.setPaymentStatus("Check");
                     
                }
                String tempAmount = request.getParameter("tempAmount");
                String tempCheckAmount = request.getParameter("chkBalAmt");
                if(tempCheckAmount!=null &&  tempCheckAmount.trim().length()!=0){
                    checkAmount = Float.parseFloat(tempCheckAmount); 
                 }
                else{
                    checkAmount=0;
                } 
                
                System.out.println("tempAmount1" + tempAmount);
                if(tempAmount==null || tempAmount==""){
                    totalAmount=0; 
                }
                else{
                    totalAmount = Double.parseDouble(tempAmount);
                  
                } 
  
                Debug.print("pendingAmount::::::::::" +pendingAmount);
                Debug.print("tempPendingAmount::::::::::" +tempPendingAmount);
                Debug.print("checkAmount::::::::::" +checkAmount);
                
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
                objPayment.setCheckName(nameoncheck);;
                objPayment.setCheckDate(check_date);
                objPayment.setCheckNumber(checkNumber);
                objPayment.setAmount(totalAmount);
                objPayment.setPaymentDate(new Date());
                objPayment.setPaymentId(paymentIdVal);
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
                            
                            session.setAttribute("amount",String.valueOf(totalAmount));
                            session.setAttribute("paymentIdVal",paymentIdVal);
                            session.setAttribute("horseName",competitionName);
                            session.setAttribute("horseMemberId",horseMemberId);
                            session.setAttribute("regByuserId",regByuserId);
                            session.setAttribute("pendingAmount",String.valueOf(pendingAmount));
                            StringBuffer reqURL = request.getRequestURL();
                            int lastIndex = reqURL.lastIndexOf("/") ;
                            String url ="";
                            if(lastIndex!=-1){
                                url = reqURL.substring(0,lastIndex+1);
                            }
                            String tmpNova  = mr.getMessage("adminhorse_adminpay.nova");
                            String nova = url + tmpNova;
                            Debug.print("succsssullll from app file nova........::" + nova);
                            Debug.print("succsssullll from app file tmpNova ........::" + tmpNova);
                            request.setAttribute("nova",nova);
                            return mapping.findForward("novaPage");

                }
                else{
  
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId);" + sesUserId);
                    Debug.print("amount:" + totalAmount);
                    Debug.print("horseRegVO.setRegisteredBy(sesUserId)");
                     boolean pend_stat = false;
                   if(checkAmount!=0){
                      pend_stat = remote.updatePendingAmount(regByuserId,paymentIdVal,checkAmount);
                    }
                    Debug.print("pend_stat Status "+pend_stat);
                    boolean update_stat = remote.updatePaymentStatus(objPayment);
            
                    // Making the active Status and reconcile status as true on full payment
                        String logBy="user";

                        if(session.getAttribute("loggedBy")!=null)
                        {
                            String loggedBy="";
                            loggedBy=(String)session.getAttribute("loggedBy");
                            logBy=loggedBy;
                            Debug.print("session.getAttribute(\"loggedBy\") is "+session.getAttribute("loggedBy"));
                            if(loggedBy.equalsIgnoreCase("Admin")){                     
                            String chckAmount = request.getParameter("chkBalAmt");
                            String totAmount = request.getParameter("totalAmount");
                            String tempAmt = request.getParameter("tempPendingAmount");
                            Debug.print("Check Amount is in Admin Pay "+chckAmount);
                            Debug.print("Total Amount is in Admin Pay "+totAmount);
                            if(tempAmt==null && tempAmt.trim().length()==0){
                                tempAmt = "0";
                            }                            
                            if(chckAmount!=null && totAmount!=null){
                                    double chkAmt = Double.parseDouble(chckAmount);
                                    double totAmt = Double.parseDouble(totAmount);
                                    double dpendAmt = Double.parseDouble(tempAmt);
                                    if(dpendAmt>0){
                                        double extraAmt = chkAmt - dpendAmt;
                                        Debug.print("Check Amount is in Admin Pay "+chkAmt);
                                        Debug.print("Total Amount is in Admin Pay "+totAmt);
                                        Debug.print("Extra Amount Through Admin Pay "+extraAmt);

                                        if(extraAmt > 0){
                                            String StrextraAmt = String.valueOf(extraAmt);
                                            HLCAccTxnTypeDetailVO overpayDet = mahaRemote.getOverPayAccTransactionTypeDetail();
                                            HLCAccTransactionVO overpayfinalDet = new HLCAccTransactionVO();

                                            String accNo = overpayDet.getAccount_no();
                                            String accClassname = overpayDet.getClass_name();
                                            String accItemNo = overpayDet.getItem_no();
                                            String accAccNo = overpayDet.getSub_account_no();
                                            String accTranName = overpayDet.getTransaction_name();
                                            String accTyped = overpayDet.getTransaction_type();
                                            String accTypeId = overpayDet.getTransaction_type_id();
                                            String description = overpayDet.getTransaction_name();
                                            Debug.print("While getting"+ overpayDet.toString());

                                            overpayfinalDet.setAccount_no(accNo);
                                            overpayfinalDet.setAccount_type("Income");
                                            overpayfinalDet.setAccount_no(accNo);
                                            overpayfinalDet.setClass_Typ(accClassname);
                                            overpayfinalDet.setPayment_id(paymentIdVal);
                                            overpayfinalDet.setSub_account_no(accAccNo);
                                            overpayfinalDet.setItem_no(accItemNo);
                                            overpayfinalDet.setAmount(Float.parseFloat(StrextraAmt));
                                            overpayfinalDet.setTransaction_id(accTypeId);
                                            overpayfinalDet.setDescription(description);

                                            String pay_mode=request.getParameter("r11");

                                            if(pay_mode!=null && pay_mode.trim().length()>0){
                                                if(pay_mode.equalsIgnoreCase("check")){    
                                                    overpayfinalDet.setPayment_mode("check");
                                                    overpayfinalDet.setActive_status(false);
                                                }
                                            }
                                            Debug.print("B4 insert "+ overpayfinalDet.toString());
                                            boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                        }
                                        }
                                    if(chkAmt>0){
                                        double extraAmt = chkAmt - totAmt;
                                            if(extraAmt > 0){
                                            String StrextraAmt = String.valueOf(extraAmt);
                                            HLCAccTxnTypeDetailVO overpayDet = mahaRemote.getOverPayAccTransactionTypeDetail();
                                            HLCAccTransactionVO overpayfinalDet = new HLCAccTransactionVO();
                                            
                                            String accNo = overpayDet.getAccount_no();
                                            String accClassname = overpayDet.getClass_name();
                                            String accItemNo = overpayDet.getItem_no();
                                            String accAccNo = overpayDet.getSub_account_no();
                                            String accTranName = overpayDet.getTransaction_name();
                                            String accTyped = overpayDet.getTransaction_type();
                                            String accTypeId = overpayDet.getTransaction_type_id();
                                            String description = overpayDet.getTransaction_name();
                                            Debug.print("While getting"+ overpayDet.toString());
                                            
                                            overpayfinalDet.setAccount_no(accNo);
                                            overpayfinalDet.setAccount_type("Income");
                                            overpayfinalDet.setAccount_no(accNo);
                                            overpayfinalDet.setClass_Typ(accClassname);
                                            overpayfinalDet.setPayment_id(paymentIdVal);
                                            overpayfinalDet.setSub_account_no(accAccNo);
                                            overpayfinalDet.setItem_no(accItemNo);
                                            overpayfinalDet.setAmount(Float.parseFloat(StrextraAmt));
                                            overpayfinalDet.setTransaction_id(accTypeId);
                                            overpayfinalDet.setDescription(description);
                                            
                                            String pay_mode=request.getParameter("r11");
                                            
                                            if(pay_mode!=null && pay_mode.trim().length()>0){
                                                if(pay_mode.equalsIgnoreCase("check")){
                                                    overpayfinalDet.setPayment_mode("check");
                                                    overpayfinalDet.setActive_status(false);
                                                }
                                            }
                                            Debug.print("B4 insert "+ overpayfinalDet.toString());
                                            boolean extraPay = mahaRemote.insertAccountTxnDetails(overpayfinalDet);
                                            }                                        
                                    }

                                    if(chkAmt>=totAmt){
                                        if(paymentIdVal!=null || paymentIdVal.trim().length()!=0){
                                            boolean Update_Status = mahaRemote.updateRecouncilActiveStatusAccTxnDetails(paymentIdVal);
                                            Debug.print("Update Status "+Update_Status);
                                        }
                                    }
                                }
                            }
                        }
                    
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
                                     
                                        if(checkAmount!=0){
                                        request.setAttribute("totalAmount",String.valueOf(checkAmount));
                                        }
                                        
                                        else if(pendingAmount!=0){
                                         request.setAttribute("totalAmount",String.valueOf(pendingAmount));
                                        }
                                     
                            if(pendingAmount==0 && checkAmount ==0){
                                request.setAttribute("totalAmount",String.valueOf(totalAmount));
                            }
                                    
                            return mapping.findForward("ChngPayAdminSuccess");
                            }
                            else{

                            }
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception occurs in change payment by admin:" + e.getMessage());
        }
        return null;
        
    }
}