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
/*  Program Name    : ActionChennaiServlet.java
 *  Created Date    : Augest 17, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.18
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */

package com.sponsor.action;

import com.util.email.EmailContent;
import com.util.email.EmailEngine;
import com.hlccommon.util.HLCPaymentDetailVO;
import com.hlcpayment.*;
import com.sponsor.actionform.*;
import java.io.File;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.upload.FormFile;
import javax.naming.InitialContext;
import javax.naming.Context;
import org.apache.struts.util.MessageResources;
import com.hlcspnr.sponsor.*;
import com.hlcspnr.util.*;
import java.util.Properties;
import java.util.*;
import java.text.*;
import com.hlccommon.util.Debug;
import com.hlcform.stateless.*;
public class ActionChennaiServlet extends Action {
  
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try{
            Properties p =new Properties();
            p.setProperty( "java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory" );
            p.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
            p.setProperty( "java.naming.provider.url", "localhost:11199" );
            Context jndiContext = new InitialContext(p);
            Object obj=jndiContext.lookup("ejb/HLCSponsorSessionBean");
            HLCSponsorSessionRemoteHome home = (HLCSponsorSessionRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(obj,HLCSponsorSessionRemoteHome.class);
            HLCSponsorSessionRemote remote = home.create();
            
            Object objHuman=jndiContext.lookup("ejb/HLCMemberRegistrationJNDI");
            HLCkaverystatelessRemoteHome humanHome = (HLCkaverystatelessRemoteHome)
            javax.rmi.PortableRemoteObject.narrow(objHuman,HLCkaverystatelessRemoteHome.class);
            HLCkaverystatelessRemote humanRemote = humanHome.create();
            HttpSession session = request.getSession(true);
            FormChennaiServlet spoform =(FormChennaiServlet)form;
            MessageResources mr=getResources(request);
            String filePath=mr.getMessage("file.uploadPath");
//=============================================Declaration==============================================================================            

 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 String pro = request.getParameter("pro");
//=============================================Sponsor Request Details Insertion============================================================================== 
     if(pro.equals("assignSpoReq")){
      try{
           
             String companyName="";
             String comments="";
             String planId="";
             String sponsorAmount="";
             String requestStatus="";
             boolean result=false;
 
            HLCSponsorDetails  objSponsor = new HLCSponsorDetails();
            String userId = (String)session.getAttribute("userId");
            objSponsor.setUserId(userId);
            objSponsor.setCompanyName(spoform.getCompanyName());
            objSponsor.setComments(spoform.getComments());
           
            StringTokenizer tkn = new StringTokenizer(spoform.getPlanId(),"#");
            String pId = tkn.nextToken();
            objSponsor.setPlanId(pId);
            objSponsor.setSponsorAmount(spoform.getSponsorAmount());
            Debug.print("Company Name"  +spoform.getCompanyName());
            Debug.print("getComments()"  +spoform.getComments());
            Debug.print("getPlanId()"  +spoform.getPlanId());
            Debug.print("After Spliting plan ID" + pId );
            Debug.print("getSponsorAmount()"  +spoform.getSponsorAmount());
            result = remote.createSponsorRequest(objSponsor);
            Debug.print("create result"+result);
            if(result==true){
            String tomailId =(String)session.getAttribute("emailId");
            String toMailIds[] = {tomailId};
            //String toMailIds[] = {"rpunithaa@yahoo.co.in"};
            EmailContent email=new EmailContent();
            email.setTo(toMailIds);
            email.setFrom("info@hlc.com");
            email.setSubject("Sponsor Request Successfully posted");
         
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
                "<p>You have successfully sent your Sponsorship Request. It will be reviewed and Approved within 24 hrs.<p>"+
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
			"per your ‘Role’ assigned:"+
			
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
            //boolean emailFlag = emailEngine.sendEmailWithAttachment(email);
            Debug.print("Email sent sucessfully :"+emailFlag);
            return mapping.findForward("success");
            }
           }
           catch(Exception enewReq){
               Debug.print("While adding new sponsor request" + enewReq);
           }
     }
//======================================show pending details=============================================================== 
      else if(pro.equals("p0")){
        try{
            Vector resultColl = new Vector();
            Vector statusVect = (Vector)remote.getAllPendingSponsorsForAdmin();
            Enumeration e = statusVect.elements();
            while(e.hasMoreElements()){
                HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e.nextElement();
                String sId = objSponsor.getSponsorId();
                String cmpName = objSponsor.getCompanyName();
                String plan_id = objSponsor.getPlanId();
                String date = "";
                if(objSponsor.getAddDate()!=null){
                    //date = String.valueOf(objSponsor.getAddDate());
                    date = sdf.format(objSponsor.getAddDate());
                }
                String [] planDet = (String [])remote.getPlanDetails(plan_id);
                String planName = planDet[1];
                String Amount = planDet[3];
                String res[] = {sId,cmpName,plan_id,planName,Amount,date};
                resultColl.add(res);
             }
            
                session.setAttribute("PendingSponsorDetails" ,null);
                session.setAttribute("PendingSponsorDetails" ,resultColl);
                return mapping.findForward("frmSponsorPendingRequest");
               }
               catch(Exception ePending){
                   Debug.print("While display pending request:::::::::::" + ePending);
               }
          } 
 //======================================show pending for sales person ============================================================== 
       else if(pro.equals("p01")){
        try{
            Vector resultColl = new Vector();
            String salesPersonId =  (String)session.getAttribute("userCode");
            Debug.print("salesPersonId" + salesPersonId);
            if(salesPersonId!=null){
            Vector statusVect = (Vector)remote.getAllRequestStatusSponsorsSalesPerson(salesPersonId,"Pending");
            Enumeration e = statusVect.elements();
            while(e.hasMoreElements()){
                HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e.nextElement();
                String sId = objSponsor.getSponsorId();
                String cmpName = objSponsor.getCompanyName();
                String plan_id = objSponsor.getPlanId();
                String date = "";
                if(objSponsor.getAddDate()!=null){
                   // date = String.valueOf(objSponsor.getAddDate());
                    date = sdf.format(objSponsor.getAddDate());
                }
                String [] planDet = (String [])remote.getPlanDetails(plan_id);
                String planName = planDet[1];
                String Amount = planDet[3];
                String res[] = {sId,cmpName,plan_id,planName,Amount,date};
                resultColl.add(res);
             }
           }
            session.setAttribute("PendingSponsorDetails",null);
            session.setAttribute("PendingSponsorDetails" ,resultColl);
            return mapping.findForward("frmSponsorPendingRequestForSalesPerson");
          }
            catch(Exception ePending){
               Debug.print("While display pending request" + ePending);
          }
     }
 //======================================show Payment for sales person ============================================================== 
    else if(pro.equals("p011")){
        try{
            Vector resultColl = new Vector();
            String salesPersonId = (String)session.getAttribute("userCode");
            if(salesPersonId!=null){
            Vector statusVect = (Vector)remote.getAllRequestStatusSponsorsSalesPerson(salesPersonId,"Payment");
            Enumeration e = statusVect.elements();
                while(e.hasMoreElements()){
                    HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e.nextElement();
                    String sId = objSponsor.getSponsorId();
                    String cmpName = objSponsor.getCompanyName();
                    String plan_id = objSponsor.getPlanId();
                    String finalAmount = objSponsor.getSponsorAmount();

                    String salPersonId = objSponsor.getSalesPersonId();
                    String filePath1 = objSponsor.getFilePath();
                    String [] planDet = (String [])remote.getPlanDetails(plan_id);
                    String planName = planDet[1];
                    String Amount = planDet[3];
                    String res[] = {sId,cmpName,plan_id,planName,finalAmount,Amount,filePath1};
                    resultColl.add(res);
                 }
            }
            session.setAttribute("PaymentSponsorDetails" ,null);
            session.setAttribute("PaymentSponsorDetails" ,resultColl);
            return mapping.findForward("frmSponsorSalesPersonPaymentView");
          }
               catch(Exception ePending){
                   Debug.print("While display pending request:::::::::::" + ePending);
               }
          } 
//======================================show Active details===============================================================  
      else if(pro.equals("p1")){
        try{
            Vector activeColl = new Vector();
            Vector activeVect = (Vector)remote.getAllRequestStatusSponsors("Active");
            Enumeration e1 = activeVect.elements();
            while(e1.hasMoreElements()){
                HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e1.nextElement();
                String sId = objSponsor.getSponsorId();
                String cmpName = objSponsor.getCompanyName();
                String plan_id = objSponsor.getPlanId();
                String final_amt = objSponsor.getSponsorAmount();
                String salesPersonId = objSponsor.getSalesPersonId();
                                
                String [] planDet = (String [])remote.getPlanDetails(plan_id);
                String planName = planDet[1];
                String Amount = planDet[3];
                String res[] = {sId,cmpName,plan_id,planName,Amount,final_amt,salesPersonId};
                activeColl.add(res);
             }
                session.setAttribute("activeSponsorDetails",null);
                session.setAttribute("activeSponsorDetails",activeColl);
                return mapping.findForward("frmSponsorActiveRequest");
              }
               catch(Exception eActive){
                   Debug.print("While display Active request:::::::::::" + eActive);
               }
      }
 //======================================show Active details===============================================================  
  else if(pro.equals("p11")){
        try{
            Vector activeColl = new Vector();
             String salesPersonId = (String)session.getAttribute("userCode");
            if(salesPersonId!=null){
            Vector activeVect = (Vector)remote.getAllRequestStatusSponsorsSalesPerson(salesPersonId,"Active");
            Enumeration e1 = activeVect.elements();
                while(e1.hasMoreElements()){
                    HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e1.nextElement();
                    String sId = objSponsor.getSponsorId();
                    String cmpName = objSponsor.getCompanyName();
                    String plan_id = objSponsor.getPlanId();
                    String final_amt = objSponsor.getSponsorAmount();
                    String salPersonId = objSponsor.getSalesPersonId();
                        String [] planDet = (String [])remote.getPlanDetails(plan_id);
                        String planName = planDet[1];
                        String Amount = planDet[3];
                        String res[] = {sId,cmpName,plan_id,planName,Amount,final_amt,salPersonId};
                        activeColl.add(res);
                 }
            }
                session.setAttribute("activeSponsorDetails",null);
                session.setAttribute("activeSponsorDetails",activeColl);
                return mapping.findForward("frmSponsorActiveRequest");
               }
               catch(Exception eActive){
                   Debug.print("While display Active request:::::::::::" + eActive);
               }
      }
//==========================================Sales person entry page retrival information=======================================  
 else if(pro.equals("uv")){
     Debug.print("This is from User ID:");
        try{
            //String uid = request.getParameter("yid");
             String uid = (String)session.getAttribute("userId");
             Debug.print("This is from User ID:" + uid);
                    if(uid!=null){
                        Vector activeColl = new Vector();
                        Vector activeVect = (Vector)remote.getMySposorDetails(uid);
                        Debug.print("This is from getMySposorDetails User ID:");
                        Enumeration e1 = activeVect.elements();
                        Debug.print("This is from getMySposorDetails User ID:" + activeVect);
                        while(e1.hasMoreElements()){
                            HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e1.nextElement();
                            String sId = objSponsor.getSponsorId();
                            String cmpName = objSponsor.getCompanyName();
                            String plan_id = objSponsor.getPlanId();
                            String final_amt = objSponsor.getSponsorAmount();
                            String salesPersonId = objSponsor.getSalesPersonId();
                            String filePath1 = objSponsor.getFilePath();
                            
                            Debug.print("This is from uid");
                            String date = "";
                            if(objSponsor.getAddDate()!=null){
                               // date = String.valueOf(objSponsor.getAddDate());
                                date = sdf.format(objSponsor.getAddDate());
                            }
                            String reqStatus = objSponsor.getRequestStatus();
                            Debug.print("This is from uid Date:" + date);
                            String [] planDet = (String [])remote.getPlanDetails(plan_id);
                            String planName = planDet[1];
                            String Amount = planDet[3];
                          
                            String res[] = {sId,cmpName,plan_id,planName,Amount,final_amt,salesPersonId,date,reqStatus,filePath1};
                            activeColl.add(res);
                            Debug.print("After completion of uid");
                         }
                        session.setAttribute("mysponsordetails",null);
                        session.setAttribute("mysponsordetails",activeColl);
                    }
                return mapping.findForward("frmSponsorMyRequest");
               }
               catch(Exception eActive){
                   Debug.print("While display my plan Id request:::::::::::" + eActive);
               }
      }
//======================================Show All Sales Person details=============================================================== 
     else if(pro.equals("padm01")){
        try{
            Vector resultColl = new Vector();
            Vector statusVect = (Vector)remote.getAllPendingSponsorsForAdmin();
            Enumeration e = statusVect.elements();
            while(e.hasMoreElements()){
                HLCSponsorDetails  objSponsor = (HLCSponsorDetails) e.nextElement();
                String sId = objSponsor.getSponsorId();
                String cmpName = objSponsor.getCompanyName();
                String plan_id = objSponsor.getPlanId();
                String date = "";
                if(objSponsor.getAddDate()!=null){
                   // date = String.valueOf(objSponsor.getAddDate());
                    date = sdf.format(objSponsor.getAddDate());
                }
                String [] planDet = (String [])remote.getPlanDetails(plan_id);
                String planName = planDet[1];
                String Amount = planDet[3];
                String res[] = {sId,cmpName,plan_id,planName,Amount,date};
                resultColl.add(res);
             }
             
            session.setAttribute("PendingSponsorDetails" ,null);
            session.setAttribute("PendingSponsorDetails" ,resultColl);
                
            return mapping.findForward("frmSponsorPendingRequest");
               }
               catch(Exception ePending){
                   Debug.print("While display pending request:::::::::::" + ePending);
               }
          } 
//==========================================Sales person entry page retrival information======================================= 
      else if(pro.equals("salesPerson")){
            try{
                String sid = request.getParameter("sid");
                    if(sid!=null){
                    HLCSponsorDetails  objSponsor = remote.getSponsorDetails(sid);
                    String sId = objSponsor.getSponsorId();
                    String cmpName = objSponsor.getCompanyName();
                    String plan_id = objSponsor.getPlanId();
                    String desc =  objSponsor.getComments();
                    String salesPerson = objSponsor.getSalesPersonId();
                    String [] planDet = (String [])remote.getPlanDetails(plan_id);
                    String planName = planDet[1];
                    String Amount = planDet[3];
                    String res[] = {sId,cmpName,desc,plan_id,planName,salesPerson,Amount};
                     session.setAttribute("salesPersonView" ,null);
                    session.setAttribute("salesPersonView",res);

                    return mapping.findForward("frmSponsorSalesPerson");
                    }
            }
                catch(Exception eSalesPerson){
                     Debug.print("While redirecting salesperson entry page:::::::::::" + eSalesPerson);
                }
       }
 //==========================================Admin Assigns Sales person ======================================= 
      else if(pro.equals("assignSalesPerson")){
            String sid = request.getParameter("sid");
            String processVal = request.getParameter("processVal");
            Debug.print("Sid:"  +sid);
            Debug.print("processVal" + processVal);
        try{
            if(sid!=null){
             
                HLCSponsorDetails  objSponsor = remote.getSponsorDetails(sid);
                Debug.print("Sid:"  + sid);
               // String sId = objSponsor.getSponsorId();
                String cmpName = objSponsor.getCompanyName();
                String plan_id = objSponsor.getPlanId();
                String desc =  objSponsor.getComments();
                Debug.print("Date:" + objSponsor.getAddDate());
                String date = "";
                if(objSponsor.getAddDate()!=null){
                   // date = String.valueOf(objSponsor.getAddDate());
                    date = sdf.format(objSponsor.getAddDate());
                }
                
                Debug.print("plan_id:"  + plan_id);
                String [] planDet = (String [])remote.getPlanDetails(plan_id);
                String planName = planDet[1];
                String Amount = planDet[3];
                String res[] = {sid,cmpName,desc,plan_id,planName,Amount,date};
                session.setAttribute("assignsalesPerson",res);
                if(processVal.equals("Assign")){
                return mapping.findForward("frmSponsorAssingSalesPerson");
                }
                  else if(processVal.equals("Reject")){
                    session.setAttribute("assignsalesPerson",res);
                    return mapping.findForward("frmSponsorRejectRequest");
                    
                }
            }
        }
           catch(Exception eAssignSales){
               Debug.print("While assigning sales person:::::::::::" + eAssignSales);
           }
      
      }
      else if(pro.equals("confirmRej")){
        String sid = request.getParameter("spoassinedId");        
        boolean statusChange = remote.statusUpdationForReject(sid,"Rejected");
        return mapping.findForward("pendingReq");  
      }
 //==========================================Admin Edit Contract person ======================================= 
    else if(pro.equals("sPe")){
        try{
            String sid = request.getParameter("sid");
                    if(sid!=null){
                        HLCSponsorDetails  objSponsor = remote.getSponsorDetails(sid);
                        String sId = objSponsor.getSponsorId();
                        String cmpName = objSponsor.getCompanyName();
                        String plan_id = objSponsor.getPlanId();
                        String desc =  objSponsor.getComments();
                        String salesPerson = objSponsor.getSalesPersonId();
                      
                        String date = "";
                        String contStartDate = "";
                        String contEndDate =  "";
                        if(objSponsor.getAddDate()!=null){
                           // date = String.valueOf(objSponsor.getAddDate());
                              date = sdf.format(objSponsor.getAddDate());
                        }
                         if(objSponsor.getContractStartDate()!=null){
                            //contStartDate = String.valueOf(objSponsor.getContractStartDate());
                            contStartDate = sdf.format(objSponsor.getContractStartDate());
                        }
                         if(objSponsor.getcontractEndDate()!=null){
                            //contEndDate = String.valueOf(objSponsor.getcontractEndDate());
                            contStartDate = sdf.format(objSponsor.getcontractEndDate());
                        }
                        
                        String finalcost = objSponsor.getSponsorAmount();
                        String [] planDet = (String [])remote.getPlanDetails(plan_id);
                        String planName = planDet[1];
                        String Amount = planDet[3];
                        String res[] = {sId,cmpName,desc,plan_id,planName,salesPerson,Amount,finalcost,date,contStartDate,contEndDate};
                        request.setAttribute("editsalesPerson",res);
                        return mapping.findForward("frmSponsorSalesPersonEdit");
                    }
        }
           catch(Exception eAssignSales){
               Debug.print("While assigning sales person:::::::::::" + eAssignSales);
           }
      
      }
  //==========================================Admin Edit Contract person ============================================================== 
    else if(pro.equals("sponsorViewStatusDet")){
        try{
            String sid = request.getParameter("sid");
                    if(sid!=null){
                        HLCSponsorDetails  objSponsor = remote.getSponsorDetails(sid);
                        String sId = objSponsor.getSponsorId();
                        String cmpName = objSponsor.getCompanyName();
                        String plan_id = objSponsor.getPlanId();
                        String desc =  objSponsor.getComments();
                        String salesPerson = objSponsor.getSalesPersonId();
                        
                         String date = "";
                        if(objSponsor.getAddDate()!=null){
                           // date = String.valueOf(objSponsor.getAddDate());
                             date = sdf.format(objSponsor.getAddDate());
                        }
                        //String date = objSponsor.getAddDate().toString();
                        
                        String contStartDate =  objSponsor.getContractStartDate().toString();
                        String contEndDate =  objSponsor.getcontractEndDate().toString();
                        String finalcost = objSponsor.getSponsorAmount();
                        String requestSta = objSponsor.getRequestStatus();
                        String [] planDet = (String [])remote.getPlanDetails(plan_id);
                        String planName = planDet[1];
                        String Amount = planDet[3];
                        
                        String res[] = {sId,cmpName,desc,plan_id,planName,salesPerson,Amount,finalcost,date,contStartDate,contEndDate,requestSta};
                   
                        session.setAttribute("viewSponsorStatus",null);
                        session.setAttribute("viewSponsorStatus",res);
                        return mapping.findForward("frmSponsorStatus");
                    }
        }
           catch(Exception eAssignSales){
               Debug.print("While assigning sales person:::::::::::" + eAssignSales);
           }
      
      }
//========================================== Redirects to sales person confirmation template=========================================================== 
        else if(pro.equals("assignSuccess")){
        String sid = request.getParameter("spoassinedId");
        String salesPersonId = request.getParameter("salesPersonId");
        Debug.print("ActionChennaiServlet sid:" + sid);
        Debug.print("ActionChennaiServlet salesPersonId:" + salesPersonId);
        if(sid!=null && salesPersonId!=null){
            try{
                boolean resultVal = humanRemote.getUserCode(salesPersonId);
                 Debug.print("ActionChennaiServlet salesPersonId Checking:" + resultVal);
                if(resultVal==true){
                    remote.salesPersonAssign(sid,salesPersonId);
                }
                else{
                    request.setAttribute("err",sid);
                    return mapping.findForward("frmSponsorAssingSalesPerson");
                }
            }
            catch(Exception exp){
                Debug.print("ActionChennaiServlet salesPersonId:" + salesPersonId);
            }
        }
            return mapping.findForward("frmAssignSalesPersonConfirmation");
     }
//========================================== Payment redirection insertion===========================================================
      else if(pro.equals("redirPayment")){
          
            String sponsorId = request.getParameter("sponsorId");
            String scheId = request.getParameter("scheId");
            String dueAmount = request.getParameter("dueAmount");
            request.setAttribute("sponsorId",sponsorId);
            request.setAttribute("scheId",scheId);
            request.setAttribute("dueAmount",dueAmount); 
            
            Debug.print("sponsorId" + sponsorId);
            Debug.print("scheId" + scheId);
            Debug.print("dueAmount" + dueAmount);
            
            return mapping.findForward("frmSponsorPaymentDet");
      }   
//========================================== Sales person entry page insertion===========================================================
   /*   else if(pro.equals("salesentryAdd")){
            Date contractStartDate = null;
            Date contractEndDate = null;
            String sponsorId = request.getParameter("spoId");
            Debug.print("sponsorId"  + sponsorId);
            String salesPersonId = request.getParameter("salesPersonId");
            String finalCost = request.getParameter("finalCost");
            String startDate = request.getParameter("contractStartDate");
            String endDate = request.getParameter("contractEndDate");
                if(startDate.equals("")){
                    contractStartDate= null;
                }
                else{
                    contractStartDate =(Date)sdf.parse(startDate);
                }
                if(endDate.equals("")){
                    contractEndDate= null;
                }
                else{
                    contractEndDate =(Date)sdf.parse(endDate);
                }
           // remote.salesPersonUpdate(sponsorId,contractStartDate,contractEndDate,finalCost);
            
            return mapping.findForward("successcontract");
      }   */
//==========================================Billing section from active page=========================================================== 
      else if(pro.equals("billSchedule")){
     try{
         String sponsorId = request.getParameter("sponsorId");
         Debug.print("sponsorId" + sponsorId );
                if(sponsorId!=null){
                    HLCSponsorDetails  objSponsor = remote.getSponsorDetails(sponsorId);
                    String sid = objSponsor.getSponsorId();
                    String cmpName = objSponsor.getCompanyName();
                    String plan_id = objSponsor.getPlanId();
                    String desc =  objSponsor.getComments();
                    String finalAmt = objSponsor.getSponsorAmount();
                    String salesId = objSponsor.getSalesPersonId();
                    
                    String [] planDet = (String [])remote.getPlanDetails(plan_id);
                    String planName = planDet[1];
                    String Amount = planDet[3];
                    String res[] = {sid,cmpName,desc,plan_id,planName,Amount,finalAmt,salesId};
                     
                    session.setAttribute("spnrBillingDet",null);
                    session.setAttribute("spnrBillingDet",res);
                            Vector scheduleVect =  (Vector)remote.getScheduleTypeMaster();
                            Debug.print("scheduleVect"  + scheduleVect); 
                            session.setAttribute("scheduleVect",null);
                            session.setAttribute("scheduleVect",scheduleVect);
                           // response.sendRedirect("spnrBillingSchedule.jsp");
                            return mapping.findForward("frmSponsorBillingSchedule");
              }
     }
     catch(Exception billschdule){
         
          Debug.print("While redirecting bill schedule page:::::::::::" + billschdule);
     }
      }
 //==========================================Sponsor Status View Page===========================================================        
    else if(pro.equals("sponsorViewStatus")){
     try{
         String sponsorId = request.getParameter("sponsorId");
         Debug.print("sponsorId" + sponsorId );
                if(sponsorId!=null){
                            HLCSponsorDetails  objSponsor = remote.getSponsorDetails(sponsorId);
                            String sid = objSponsor.getSponsorId();
                            String cmpName = objSponsor.getCompanyName();
                            String plan_id = objSponsor.getPlanId();
                            String desc =  objSponsor.getComments();
                            String finalAmt = objSponsor.getSponsorAmount();
                            String salesId = objSponsor.getSalesPersonId();
                            String stDate = objSponsor.getContractStartDate().toString();
                            String endDate = objSponsor.getcontractEndDate().toString();
                            String [] planDet = (String [])remote.getPlanDetails(plan_id);
                            String planName = planDet[1];
                            String Amount = planDet[3];
                            String res[] = {sid,cmpName,desc,plan_id,planName,Amount,finalAmt,salesId,stDate,endDate};
                            // form.setLstPendingSponsorDetails(Collection );
                      
                            session.setAttribute("spnrStatusView",null);
                            session.setAttribute("spnrStatusView",res);
                              return mapping.findForward("frmSponsorStatus");
                  }
         }
         catch(Exception eSponsorStatus){
              Debug.print("While showing sponsor status:::::::::::" + eSponsorStatus); 
         }
      }
 //==========================================Billing section from active page=========================================================== 
     else if(pro.equals("billingInsertion")){
      try{
        String sid = request.getParameter("sid");
        Debug.print("sid" + sid );
        String scheduleTypeId = request.getParameter("bs1");
        Debug.print("scheduleTypeId" + scheduleTypeId );
 //=================================For Manual=========================================        
        if(scheduleTypeId.equals("Manual")){
            boolean result1= false;
            Date manualDueDate = null;
            String mid = request.getParameter("mid");
            float manualAmount = Float.parseFloat(request.getParameter("manualAmount"));
            String manualBillDate = request.getParameter("manualDate");
            Debug.print("Manual Date in Servlet :" + manualBillDate);
            
            if(manualBillDate.equals("")){
              manualDueDate= null;
            }
            else{
               manualDueDate =(Date)sdf.parse(manualBillDate);
            }
          
            Debug.print("Manual Date in Servlet After Parsing:" + manualBillDate);
            result1 = remote.createPaymentSchedule(sid,mid,manualDueDate, manualAmount, false, "1", "Pending", null);
                if(result1==true){
                   // return mapping.findForward("afterBillingStatusView");
                  remote.statusUpdation(sid,"Payment");
                }
                else
                {
               // return mapping.findForward("spnrBillingSchedule");
                }
          }
//=================================For Recurring=====================================================             
        else if(scheduleTypeId.equals("Recurring")){
            boolean recRes= false;
            Date recurringDueDate = null;
            String rid = request.getParameter("rid");
            
            float recurringAmount = Float.parseFloat(request.getParameter("recurringAmount"));
            String recurringFrequency = request.getParameter("recurringFrequency");
            String recurringBillDate = request.getParameter("recurringBillDate");
                        if(recurringBillDate.equals("")){
                            recurringDueDate= null;
                        }
                        else{
                            recurringDueDate =(Date)sdf.parse(recurringBillDate);
                        }
 
            recRes = remote.createPaymentSchedule(sid,rid,recurringDueDate, recurringAmount, true, recurringFrequency, "Pending", null);
           
            if(recRes==true){
                //return mapping.findForward("afterBillingStatusView");
                remote.statusUpdation(sid,"Payment");
            }
            else{
              // return mapping.findForward("spnrBillingSchedule");
            }
          }
//=================================For Specific Billing=====================================================             
        else if(scheduleTypeId.equals("Specific Billing")){
            boolean result2= false;
            Date specificDueDate1 = null;
            Date specificDueDate2 = null;
            Date specificDueDate3 = null;
            Date specificDueDate4 = null;
            float specificAmount1 =0;
            float specificAmount2 =0;
            float specificAmount3 =0;
            float specificAmount4 =0;
            
            String spid = request.getParameter("spid");
            Debug.print("Specific Bill ID:" +spid);
            Vector v = new Vector();
            ArrayList a1 = new ArrayList();
            ArrayList a2 = new ArrayList();
            ArrayList a3 = new ArrayList();
            ArrayList a4 = new ArrayList();
           
            String specAmount1 = request.getParameter("specificAmount1");
            Debug.print("specAmount1" + specAmount1);
            String specificBillDate1 = request.getParameter("specificBillDate1");
            if((specAmount1!=null && specAmount1.trim().length()!=0 )  && specificBillDate1!=null ){
                specificAmount1 = Float.parseFloat(specAmount1);
                Debug.print("float specAmount1" + specificAmount1);
                specificDueDate1 =(Date)sdf.parse(specificBillDate1);
                a1.add(new Float(specificAmount1));
                a1.add(specificDueDate1);
                v.add(a1);
           }
            
            String specAmount2 = request.getParameter("specificAmount2");
            Debug.print("specAmount2" + specAmount2);
            String specificBillDate2 = request.getParameter("specificBillDate2");
             if((specAmount2!=null && specAmount2.trim().length()!=0 )  && specificBillDate2!=null ){
                specificAmount2 = Float.parseFloat(specAmount2);
                Debug.print("float specAmount2" + specificAmount2);
                specificDueDate2 =(Date)sdf.parse(specificBillDate2);
                a2.add(new Float(specificAmount2));
                a2.add(specificDueDate2);
                v.add(a2);
           }
           
            String specAmount3 = request.getParameter("specificAmount3");
            Debug.print("specAmount3" + specAmount3);
            String specificBillDate3 = request.getParameter("specificBillDate3");
            if((specAmount3!=null  && specAmount3.trim().length()!=0 ) && specificBillDate3!=null ){
                 specificAmount3 = Float.parseFloat(specAmount3);
                 Debug.print("float specAmount3" + specificAmount3);
                 specificDueDate3 =(Date)sdf.parse(specificBillDate3);
                 a3.add(new Float(specificAmount3));
                 a3.add(specificDueDate3);
                 v.add(a3);
            }
            
            String specAmount4 = request.getParameter("specificAmount4");
            Debug.print("specAmount4" + specAmount4);
            String specificBillDate4 = request.getParameter("specificBillDate4");
            if((specAmount4!=null  && specAmount4.trim().length()!=0 ) && specificBillDate4!=null ){
                specificAmount4 = Float.parseFloat(specAmount4);
                Debug.print("float specAmount4" + specificAmount4);
                specificDueDate4 =(Date)sdf.parse(specificBillDate4);
                a4.add(new Float(specificAmount4));
                a4.add(specificDueDate4);
                v.add(a4);
            }   
            result2 = remote.createPaymentSchedule( sid, spid, null, 0, true, "0", "Pending", v);
                if(result2==true){
                    remote.statusUpdation(sid,"Payment");
                    return mapping.findForward("frmSuccessBilling");
                }
                else{

                }
           }
      }
          catch(Exception eBilling){
              Debug.print("Exception occurs while billing section" +eBilling);

          }
      return mapping.findForward("frmSuccessBilling");
   }
//=============================================After Billing Status View==============================================================================  
     else if(pro.equals("sh")){
      String spnsrId = request.getParameter("sponsorId");
      Debug.print("Servlet Sponsor ID:" + spnsrId );
        if(spnsrId!=null){
     
                    HLCSponsorDetails  objSponsor = remote.getSponsorDetails(spnsrId);
                    String sid = objSponsor.getSponsorId();
                    String cmpName = objSponsor.getCompanyName();
                    String plan_id = objSponsor.getPlanId();
                    String desc =  objSponsor.getComments();
                    String finalAmt = objSponsor.getSponsorAmount();
                    String salesId = objSponsor.getSalesPersonId();
                    
                    String stDate = "";
                    String endDate = "";
                    if(objSponsor.getContractStartDate()!=null){
                       // stDate = String.valueOf(objSponsor.getContractStartDate());
                        stDate = sdf.format(objSponsor.getContractStartDate());
                    }
                    if(objSponsor.getcontractEndDate()!=null){
                       // endDate = String.valueOf(objSponsor.getcontractEndDate());
                         endDate = sdf.format(objSponsor.getcontractEndDate());
                    }

                    String [] planDet = (String [])remote.getPlanDetails(plan_id);
                    String planName = planDet[1];
                    String Amount = planDet[3];
                    String res[] = {sid,cmpName,desc,plan_id,planName,Amount,finalAmt,salesId,stDate,endDate};
                    session.setAttribute("spnrStatusFinalView",res);
                    Vector installAmount = (Vector)remote.getPaymentSchedule(spnsrId);
                    session.setAttribute("finalview",installAmount);
        }
                return mapping.findForward("frmSponsorAfterBilling");
       }
 
 //=============================================After Billing Status View==============================================================================  
  else if(pro.equals("usersh")){
      String spnsrId = request.getParameter("sponsorId");
      Debug.print("Servlet Sponsor ID:" + spnsrId);
        if(spnsrId!=null){
                    try{
                            HLCSponsorDetails  objSponsor = remote.getSponsorDetails(spnsrId);
                            String sid = objSponsor.getSponsorId();
                            String cmpName = objSponsor.getCompanyName();
                            String plan_id = objSponsor.getPlanId();
                            String desc =  objSponsor.getComments();
                            String finalAmt = objSponsor.getSponsorAmount();
                            String salesId = objSponsor.getSalesPersonId();
                            Debug.print("salesId:" + salesId);
                            String stDate = "";
                            String endDate = "";
                            if(objSponsor.getContractStartDate()!=null){
                                stDate = String.valueOf(objSponsor.getContractStartDate());
                            }
                            if(objSponsor.getcontractEndDate()!=null){
                                endDate = String.valueOf(objSponsor.getcontractEndDate());
                            }
                            
                            Debug.print("plan_id:" + plan_id);
                            String [] planDet = (String [])remote.getPlanDetails(plan_id);
                            String planName = planDet[1];
                            String Amount = planDet[3];
                            Debug.print("plan_Aount" + Amount);
                            String res[] = {sid,cmpName,desc,plan_id,planName,Amount,finalAmt,salesId,stDate,endDate};
                         
                            session.setAttribute("spnrStatusFinalView",null);
                            session.setAttribute("spnrStatusFinalView",res);
                            Debug.print("Calling Payment Details:" + plan_id);
                            
                            Vector v = (Vector)remote.getPaymentSchedule(spnsrId);
                            Debug.print("Calling Payment Details:" + spnsrId);
                            HttpSession hs = request.getSession(true);
                            hs.setAttribute("finalview",null);
                            hs.setAttribute("finalview",v);
                            Debug.print("Sucessfully return:");
                    }
                    catch(Exception exp){
                        Debug.print("Exception While calling usersh" + exp);
                    }
                 return mapping.findForward("frmSponsorAfterBillingSposor");
        }
       }
//=====================================================================================
                      else if(pro.equals("uplView")){
                            String filePath1 = request.getParameter("fPath");
                            Debug.print("filePath1" + filePath1);
                            
                            // String file1 = getServletContext().getRealPath(filePath1);
                            String type = filePath1.substring(filePath1.lastIndexOf(".")+1,filePath1.length());
                            Debug.print("type" + type);
                          // String name = filePath1.getName().substring(filePath1.getName().lastIndexOf("/") + 1,filePath1.getName().length());
                            String dirPath = filePath;//get it from the configuration  
                            String tempPath = dirPath + filePath1;
                            Debug.print("tempPath" + tempPath);
                            File file = new File(tempPath);
                            Debug.print("file" + file);
                           // outputImage(filePath1,type,response);
                            outputImage(file,filePath1,response);
                            return null;
                 }
 
//=============================================try block end============================================================================== 
  }
//=============================================catch block==============================================================================      
        catch(Exception e){
          Debug.print("While caliing business logic ::::::::::::::::::" + e);
        }
 
//=============================================Final Forward==============================================================================
        return null;
        
    }
 	 public void outputImage(File thumbfile, String filePath1, HttpServletResponse response) throws FileNotFoundException, IOException {
                 if (thumbfile == null) {
                      Debug.print("Extra path info was null; should be a resource to view");
                  }

		//response.reset();
		//response.setContentType("text/plain");
                response.setContentType("application/octet-stream");
               //response.setContentType("application/" + type);
                response.setHeader("Content-Disposition", "attachment;filename=\"" + filePath1 + "\"");
                //response.setHeader("Content-Disposition","attachment; filename=\"" + thumbfile + "\"");

		OutputStream os = response.getOutputStream();
		FileInputStream fin = new FileInputStream(thumbfile);
		byte[] buf = new byte[4096];
		int count = 0;
		while(true) {
                   //  byte[] buf = new byte[4 * 1024]; // 4K buffer
   // int bytesRead;
   // while ((bytesRead = in.read(buf)) != -1) {
      //out.write(buf, 0, bytesRead);

			int n = fin.read(buf);
			if(n == -1) {
				break;
			}
			count = count + n;
			os.write(buf,0,n);
		}
		os.flush();
		os.close();
		fin.close();
	}

 }

