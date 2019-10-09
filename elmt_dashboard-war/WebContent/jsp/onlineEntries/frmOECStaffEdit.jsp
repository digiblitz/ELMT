#-------------------------------------------------------------------------------
# * * Copyright: 2019 digiBlitz Foundation
#  * * 
#  * * License: digiBlitz Public License 1.0 (DPL) 
#  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
#  * * 
#  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
#  * * 
#  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
#  * * 
#  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
#-------------------------------------------------------------------------------
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvDimensionEdit.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.4
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
--> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmOECStaffEdit.js" type="text/javascript"></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
 
  <%
  String eveSecreId=(String)request.getAttribute("eveSecreId");
  String logName=(String)request.getAttribute("LogName");
  System.out.println("logName"+logName);
  System.out.println("eveSecreId"+eveSecreId);	 
   
 %>
 
 <script type="text/javascript">
 function focus_login()
{
	document.frmOECStaffEdit.txtEventTitle.focus();
}	


 function focus_chk()
{
 var tmpLog=document.frmOECStaffEdit.logName.value;
 //alert(tmpLog);
 document.getElementById('accUser').style.display="block";
 document.getElementById('ridAddUser').style.display="none";
 document.frmOECStaffEdit.userNameId1.value=tmpLog;
 }	

var arHttpRequest;

function memberDetails()
{
	//alert(document.frmOECStaffEdit.ownerUseaNo2_id.value);
	if(document.frmOECStaffEdit.ownerUseaNo2_id.value!="")
	{
    var memberid=document.frmOECStaffEdit.ownerUseaNo2_id.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {membStatus(); } ; 
        arHttpRequest.send(null); 
   } 
	}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function membStatus() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
                if(arnameText=="false")
                {    
					alert("Member Id Doesn't Exists!"); 
					document.frmOECStaffEdit.ownerUseaNo2_id.value="";
					document.frmOECStaffEdit.ownerUseaNo2_id.focus();
					return false;
                }
				/*else    
				{
					famMemberStatus();
				}*/

            } 
            else 
            { 
                alert("Error loading page\n"+ arHttpRequest.status +":"+ arHttpRequest.statusText); 
            } 
        } 
    } 


//-------------------------------- User status validation Ajax Script ------------------------------------------------

var httpRequest;

function usrStat()
{

if(document.frmOECStaffEdit.userNameId1.value!="" && document.frmOECStaffEdit.userNameId1.value.indexOf(' ')!=0)
	{

   var chsUserName=document.frmOECStaffEdit.userNameId1.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.do?process=checkusrnam&chsUserName="+chsUserName; 

        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processUser; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUser() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML) 
    { 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 

		if(salutationText == "false")
		{
			alert("User Name doesn't Exists !");
			document.frmOECStaffEdit.userNameId1.value="";
			document.frmOECStaffEdit.userNameId1.focus();
		}
		      
    } 


 </script>
 
</head>

<%if(eveSecreId!=null && eveSecreId.trim().length()!=0){%>
<body onload="focus_login();focus_chk();">
<%}else{
%>
<body onload="focus_login();">
<%}%>

<%! 
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%! String dateCheck(Date fieldName){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String detValue = "";
        if(fieldName!=null){
            detValue = sdf.format(fieldName);
        }
        return detValue;
    }
    %>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong>Online Entries </strong>:Event Details </td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">		 </td>
  </tr>
 
 <!-- ==================================================Edit Dimension Details==============================================-->
  <tr>
    <td height="20">
	<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
      <tr>
        <td colspan="2" class="tabHead">Required fields are marked with an asterisk <span class="asterisk">*</span></td>
      </tr>
      <% 
	  String staffList = (String)request.getAttribute("staffList");
	  String statuscheck = (String)request.getAttribute("err");
				
				if(statuscheck!=null && statuscheck.equals("st")){
				%>
      <tr>
        <td colspan="2" class="styleError" height="20">&nbsp;<strong> Try Again!</strong></td>
      </tr>
      <%
				}
				%>
      <tr>
        <td colspan="5" class="tblRowHead">Edit/Update Event Details:</td>
      </tr>
      <%
		HLCCalendarVO calVO = new HLCCalendarVO();
		
		calVO = (HLCCalendarVO)request.getAttribute("singleEventDetails");
		String forReadOnly = "";
		String provisionId = calVO.getCalenderId();
		Date startDate = calVO.getBeginDate();
		String tempDt =(startDate.toString()); 
		Date endDate = calVO.getEndDate();
		Date entryBeginDte = calVO.getEntryStrtDate();
		Date entryEndDte = calVO.getEntryEndDate();
		Date extDueDte = calVO.getExtDueDate();
		String eventTitle = calVO.getEventTitle();
		String selAreaId1 = calVO.getAreaId();
		String selStateId1 = calVO.getStateId();
		System.out.println("selAreaId1"+selAreaId1);
		System.out.println("selStateId1"+selStateId1);
		String orgStatus = calVO.getOrgApprovalStatus();
		String renewalStatus = calVO.getRenewStat().toString();
		String eventId = calVO.getEventId();
		String eventDesc = calVO.getEventDesc();
		String eventLevels = calVO.getEventLevel();
		String orgComments = calVO.getOrgComments();
		String acStatus = calVO.getArearChairApproStatus();
        String acComments = calVO.getAreaChairCommt();
        String usStatus = calVO.getApprovalStatus();
        String usComments = calVO.getStaffComments();
		String issueId1 = calVO.getEveIssueId();
		String eveOrgId = calVO.getOrganizerId();
		
		
		String rStat = "";
		if(renewalStatus.equalsIgnoreCase("true"))	rStat = "No";
		else rStat = "Yes";
		
		long stTime = calVO.getBeginDate().getTime();
		long eTime = calVO.getEndDate().getTime();
		long diffTime = eTime - stTime;
		int noDays = (int)(diffTime/(1000*60*60*24));
		int tempNo=noDays+1;
		String noOfDays=new Integer(tempNo).toString();
		System.out.println("noOfDays"+noOfDays);
		String modifyBy="";  
  %>
			
      <form name="frmOECStaffEdit" id="frmOECStaffEdit" method="post" action="./calender.do"  onsubmit="return myvalidate();">
        <input type="hidden" name="method" value="updateStaff"/>
        <input type="hidden" name="provisionId" value="<%=provisionId%>" />
		<input type="hidden" name="noOfDays" value="<%=noOfDays%>" />
		<input type="hidden" name="modifyBy" value="<%=modifyBy%>" />
		<input type="hidden" name="compYear" value="<%=tempDt.substring(0,4)%>"/>
		<input type="hidden" name="tempstaffList" value="<%=staffList%>"/>
		<input type="hidden" name="eveOrgId" value="<%=eveOrgId%>"/>
		<input type="hidden" name="logName" value="<%=logName%>"/>
		
		  <tr>
          <td colspan="2" class="tableLeft">Event Title :</td>
          <td colspan="2" class="tableRight"><input  type="text" name="txtEventTitle" id="txtEventTitle"  class="textboxOne" value="<%=nullCheck(eventTitle)%>" size="25" maxlength="20" />
              <span class="asterisk">*</span></td>
        </tr>
        <tr>
          <td colspan="2" class="tableLeft">Event Begin Date :</td>
          <td colspan="2" class="tableRight"><input  type="text" name="startDate1" id="startDate1" readonly="readonly" class="textboxOne" value="<%=dateCheck(startDate)%>" size="25" maxlength="20" />
              <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> <span class="asterisk">*</span></td>
        </tr>
        <tr>
          <td colspan="2" class="tableLeft">Event End Date :</td>
          <td colspan="2" class="tableRight"><input  type="text" name="endDate" id="endDate" readonly="readonly" class="textboxOne" value="<%=dateCheck(endDate)%>" size="25" maxlength="20" />
              <a href="javascript:cal2.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> <span class="asterisk">*</span></td>
        </tr>
          <tr>
          <td colspan="2" class="tableLeft">Area Name :</td>
          <td  colspan="2" class="tableRight">
		  				  <select name="selAreaId" id="selAreaId" class="selectboxOne" >
						  <option selected="selected" value=" ">Select One</option>
							<%
							ArrayList areaList = (ArrayList)request.getAttribute("selectAreaDetails");
							if(areaList!=null && areaList.size()!=0){
							Iterator it1 = areaList.iterator();				
							while(it1.hasNext()){
							String sr1[] = (String[])it1.next();
							String selAreaId = sr1[0];
							String selArea  = sr1[2];
							if(selAreaId.equals(selAreaId1)){				
							%>
						   <option value="<%=selAreaId%>" selected="selected"> <%=selArea%> </option>
							<%
							}else{
							%>
							<option value="<%=selAreaId%>"> <%=selArea%> </option>	 
							<%}}}%>
							</select>
						<span class="asterisk">*</span>&nbsp;
				</td>
				</tr>
		
       <tr>
          <td colspan="2" class="tableLeft">State Name :</td>
          <td colspan="2" class="tableRight">
		  				  <select name="selStateId" id="selStateId" class="selectboxOne" >
						  <option selected="selected" value=" ">Select One</option>
							<%
							ArrayList stateList = (ArrayList)request.getAttribute("selectStateDetails");
							if(stateList!=null && stateList.size()!=0){
							Iterator it1 = stateList.iterator();				
							while(it1.hasNext()){
							String sr1[] = (String[])it1.next();
							String selStateId = sr1[0];
							String selState  = sr1[1];
							if(selStateId.equals(selStateId1)){				
							%>
						   <option value="<%=selStateId%>" selected="selected"> <%=selState%> </option>
							<%
							}else{
							%>
							<option value="<%=selStateId%>"> <%=selState%> </option>	 
							<%}}}%>
							</select>
						<span class="asterisk">*</span>&nbsp;
				</td>
				</tr>
<tr>
				     <td colspan="2" class="tableLeft">Season:</td>
				     <td colspan="2" class="tableRight">
						<select name="selIssue" id="selIssue" class="selectboxOne" >
										<option selected="selected" value="">Select One</option>										
										<%
										ArrayList issDetails = (ArrayList)request.getAttribute("issueDetails");
										if(issDetails!=null && issDetails.size()!=0){
										Iterator itr = issDetails.iterator();               
                						while(itr.hasNext()){
                						String[] issueDet = (String[])itr.next();
										String issueId = issueDet[0];
										String issueName = issueDet[1];
								
									if(issueId.equals(issueId1)){				
							%>
						   <option value="<%=issueId%>" selected="selected"> <%=issueName%> </option>
							<%
							}else{
							%>
							<option value="<%=issueId%>"> <%=issueName%> </option>	 
							<%}}}%>
							</select>

				 	 <span class="asterisk">*</span>
					 </td>
		     	</tr>				
				
				<tr>
          <td colspan="2" class="tableLeft">Entry Reg. Begin Date :</td>
          <td colspan="2" class="tableRight"><input  type="text" name="entryBeginDate" id="entryBeginDate" readonly="readonly" class="textboxOne" value="<%=dateCheck(entryBeginDte)%>" size="25" maxlength="20" />
              <a href="javascript:cal3.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> <span class="asterisk">*</span></td>
        </tr>			
				
	<tr>
          <td colspan="2" class="tableLeft">Entry Reg. End Date :</td>
          <td colspan="2" class="tableRight"><input  type="text" name="entryEndDate" id="entryEndDate" readonly="readonly" class="textboxOne" value="<%=dateCheck(entryEndDte)%>" size="25" maxlength="20" />
              <a href="javascript:cal4.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a> <span class="asterisk">*</span></td>
        </tr>			
				
	<tr>
          <td colspan="2" class="tableLeft">Extended Due Date</td>
          <td colspan="2" class="tableRight"><input  type="text" name="extDueDate" id="extDueDate" readonly="readonly" class="textboxOne" value="<%=dateCheck(extDueDte)%>" size="25" maxlength="20" />
              <a href="javascript:cal5.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a></td>
        </tr>	
		
	<tr>
				     <td class="tableLeft" colspan="2">Organizer ID:</td>
				     <td class="tableRight" colspan="2">
					 <input name="orgnaizerId" type="text" id="orgnaizerId" class="textboxOne" size="20" maxlength="20" value="<%=eveOrgId%>" onBlur="orgMemberDetails();"/></td>
				</tr>	

<%if(eveSecreId!=null && eveSecreId.trim().length()!=0){%>	
	
			
<tr>
			<td class="tableLeftTxtArea" colspan="2">Choose one option that apply: </td>
			<td class="tableRight" colspan="2">
				<input type="radio" size="10" name="regFor" id="regFor" value="mem1" onClick="switchDiv('ridAddUser'),showHideRadio('regFor','accUser','mem1'),clearMemberdetails(); clearUserdetails();" />
Secretary&rsquo;s Member ID<br />
<input type="radio" size="10" name="regFor" id="regFor" value="acc1" checked="checked" onClick="switchDiv('accUser'),  showHideRadio('regFor','ridAddUser','acc1'),clearMemberdetails(); clearUserdetails();" />
Login Name <br />
	</td>
		</tr>		
		
	<tr>
			<td colspan="5">
				<div id="ridAddUser">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="tableLeft">Member ID:</td>
	<th class="tableRight"><input name="ownerUseaNo2_id" id="ownerUseaNo2_id" class="textboxOne" size="20" onBlur="memberDetails();"/></th>
						</tr>					
					</table>
				</div>
			</td>
		</tr> 	
		
 <tr>
					<td colspan="5"><div id="accUser">
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							
							
							<tr>
							  <td class="tableLeft">Login Name:</td>
<th class="tableRight">
<input name="userNameId1" id="userNameId1" class="textboxOne" size="20" onblur="usrStat();" /> </th>
							</tr>				
						 </table>
						 </div>								</td>
				  </tr>		
		
<%}else{%>	


<tr>
			<td class="tableLeftTxtArea" colspan="2">Choose one option that apply: </td>
			<td class="tableRight" colspan="2">
				<input type="radio" size="10" name="regFor" id="regFor" value="mem1" onClick="switchDiv('ridAddUser'),showHideRadio('regFor','accUser','mem1'),clearMemberdetails(); clearUserdetails();" />
Secretary&rsquo;s Member ID<br />
<input type="radio" size="10" name="regFor" id="regFor" value="acc1" onClick="switchDiv('accUser'),  showHideRadio('regFor','ridAddUser','acc1'),clearMemberdetails(); clearUserdetails();" />
Login Name <br />
	</td>
		</tr>		
		
	<tr>
			<td colspan="5">
				<div id="ridAddUser">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						
						<tr>
							<td class="tableLeft">Member ID:</td>
	<th class="tableRight"><input name="ownerUseaNo2_id" id="ownerUseaNo2_id" class="textboxOne" size="20" onBlur="memberDetails();"/></th>
						</tr>					
					</table>
				</div>
			</td>
		</tr> 	
		
 <tr>
					<td colspan="5"><div id="accUser">
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							
							
							<tr>
							  <td class="tableLeft">Login Name:</td>
							  <th class="tableRight"><input name="userNameId1" id="userNameId1" class="textboxOne" size="20" onblur="usrStat();" /></th>
							</tr>				
						 </table>
						 </div>								</td>
				  </tr>		
		

<%}%>


<tr>
				     <td colspan="2" class="tableLeft">Area Chair Approval Status: </td>
				     <td colspan="2" class="tableRight"><%=nullCheck(acStatus)%></td>
		      </tr>
			  <tr>
				     <td colspan="2" class="tableLeft">Area Chair Approval Comments: </td>
				     <td colspan="2" class="tableRight"><%=nullCheck(acComments)%></td>
		      </tr>
			  <tr>
				     <td colspan="2" class="tableLeft">Event Organizer  Approval Status: </td>
				     <td colspan="2" class="tableRight"><%=nullCheck(orgStatus)%></td>
		      </tr>
			  <tr>
				     <td colspan="2" class="tableLeft">Event Organizer Approval Comments: </td>
				     <td colspan="2" class="tableRight"><%=nullCheck(orgComments)%></td>
		      </tr>
							
          <tr>
          <td class="tableLeft" colspan="2">Event Levels :
		  <span class="asterisk">*</span></td>
		  <td class="tableLeft">Divisions :</td>
		  <td class="tableLeft" >Championship Details :</td>
          </tr>
          <%! String x1; %>
			
			<% 
            int countVal=0;           
			int chksfx=0;
			String cbx="divlevel";
			boolean entIdStatus=false;
			boolean chStatus=false;
			boolean checkStatus=false;
			String eventcnt="";
			ArrayList divisionList1 = (ArrayList)request.getAttribute("divisionDetails");
			java.util.Vector allTypesVect=new Vector();
			allTypesVect=(java.util.Vector)request.getAttribute("allTypesVect");
			Enumeration itrators= (Enumeration)allTypesVect.elements();
			int i=0;
			countVal=allTypesVect.size();
			while(itrators.hasMoreElements()){
			String[] s = (String[]) itrators.nextElement();                                    
			String code=s[4];
			String selEventId=s[2];
			String selEventTypeId=s[1];
			String mid=s[0];
			String cnct=selEventId+"#"+code+"#"+selEventTypeId;
			i++;
			ArrayList eventList1 = (ArrayList)request.getAttribute("selectedEventDetails");
						if(eventList1!=null && eventList1.size()!=0){
							Iterator it = eventList1.iterator();
							while(it.hasNext()){
								entIdStatus = false;
								String sr[] = (String[])it.next();
								String selectEventId = sr[0];
								String selectEvTypeId = sr[2];
								String champStatus =sr[5];
								boolean champSts=Boolean.parseBoolean(champStatus);


								eventcnt=selectEventId+"#"+selectEvTypeId;
								if(selEventTypeId.equals(selectEvTypeId)){
									if(selEventId.equals(selectEventId)){
									entIdStatus = true;
									if(champSts==true){
									chStatus=true;
									}else if(champSts==false){
									chStatus=false;
									}
									break;
									
									
									}
								}
				   			 }
					   }
			%>   
			    <%
				String cbxname=cbx+Integer.toString(chksfx);
				if(!(s[3].equals(x1))){
				x1=s[3];
				%>
					<tr>
		          
		  <td colspan="5" class="tblRowHead" ><%=x1%>
		  </td>
		
		  </tr>
		  <%}%>
		
		  <%
		  if(entIdStatus==true){
		  
		  %>
		   <tr>
		  <td class="tableLeft" >&nbsp;</td>
          <td class="tableLeft" width="10%"><input type="checkbox" size="10" name="txtEvent<%=i%>" id="txtEvent<%=i%>" value="<%=cnct%>" checked="checked" onclick="<%=forReadOnly%>"/><%=code%> </td>
		  <td class="tableLeft" width="40%" >
			<table><tr>
		  <% ArrayList divisionList = (ArrayList)request.getAttribute("divisionDetails");
		 
		  String divisioncnt = "";
		  int j = 0;
						if(divisionList!=null && divisionList.size()!=0){
						
							Iterator it = divisionList.iterator();
							while(it.hasNext()){
								
								String sr[] = (String[])it.next();
								String divisionId = sr[0];
								String divisionName = sr[1];
								divisioncnt=divisionId+"#"+divisionName;
								ArrayList selectDivisionDetails = (ArrayList)request.getAttribute("selectDivisionDetails");
								checkStatus = false;
								if(selectDivisionDetails!=null && selectDivisionDetails.size()!=0){
							Iterator itr = selectDivisionDetails.iterator();
							while(itr.hasNext()){
							String str[] = (String[])itr.next();
							String seldivisionId = str[0];
							String selTypeId = str[1];
							String selLevelId = str[2];
							if(selEventTypeId.equals(selTypeId))
							{
							if(selEventId.equals(selLevelId))
							{
							if(seldivisionId == null && divisionName.equals("Open"))
							checkStatus = true;
							if(divisionId.equals(seldivisionId))
							{
									checkStatus = true;
									}
									}}
									}
								}
								
				   		if(checkStatus)	{  %>
			  <td><font size="2px"><input type="checkbox" size="10" name="txtDiv<%=i%>_<%=j%>" id="txtDiv<%=i%>_<%=j%>" value="<%=divisioncnt%>" checked="checked" onclick="<%=forReadOnly%>"/><%=divisionName%></font></td>
			   <%
			    }else{
			   %>
			   <td><font size="2px"><input type="checkbox" size="10" name="txtDiv<%=i%>_<%=j%>" id="txtDiv<%=i%>_<%=j%>" value="<%=divisioncnt%>" onclick="<%=forReadOnly%>"/><%=divisionName%></font></td>
				<%}
				j++;}
				}
				%>
				</tr>
			</table>
		  </td>
		  <%if(chStatus==true){ %>
          <td class="tableLeft" width="40%" colspan="3"><input type="radio" size="10" name="champ<%=i%>" value="<%=eventcnt%>" onclick="<%=forReadOnly%>" />
            Yes
            <input type="radio" size="10" name="champ<%=i%>" value="no" onclick="<%=forReadOnly%>" checked="true"/>
            No </td></tr>
			<%}else{%>
			<td class="tableLeft" width="40%" colspan="3"><input type="radio" size="10" name="champ<%=i%>" value="<%=eventcnt%>" onclick="<%=forReadOnly%>" />
            Yes
            <input type="radio" onclick="<%=forReadOnly%>" size="10" name="champ<%=i%>" value="no" checked="true"/>
            No </td></tr>
			<%}%>
          <%
			}else{
			 
				%><tr>
	<td class="tableLeft" >&nbsp;</td>
        <td class="tableLeft" width="10%"><input type="checkbox" size="10" name="txtEvent<%=i%>" id="txtEvent<%=i%>" value="<%=cnct%>" onclick="<%=forReadOnly%>"/>
              <%=code%></td>
			  <td class="tableLeft" width="40%">
				<table><tr>

			  <% ArrayList divisionList = (ArrayList)request.getAttribute("divisionDetails");
			  String divisioncnt = "";
			  int j = 0;
						if(divisionList!=null && divisionList.size()!=0){
							Iterator it = divisionList.iterator();
							while(it.hasNext()){
							
								String sr[] = (String[])it.next();
								String divisionId = sr[0];
								String divisionName = sr[1];
								divisioncnt=divisionId+"#"+divisionName;
								
				   			  %>
			  <td><font size="2px"><input type="checkbox" size="10" name="txtDiv<%=i%>_<%=j%>" id="txtDiv<%=i%>_<%=j%>" value="<%=divisioncnt%>" onclick="<%=forReadOnly%>"/><%=divisionName%></font></td>
			   
				<%
				j++;}
				}
				%>
				</tr>
			</table>
		  </td>
          <td class="tableLeft" width="40%" colspan="3"><input type="radio" size="10" name="champ<%=i%>" value="<%=eventcnt%>" onclick="<%=forReadOnly%>" />
            Yes
            <input type="radio" size="10" name="champ<%=i%>" value="no" onclick="<%=forReadOnly%>" checked="true"/>
            No </td></tr>
          <%}
			chksfx++; 
			
        }%>
			<input type="hidden" name="countVal" value="<%=countVal%>"/>
			<input type="hidden" name="allTypesVect" id="allTypesVect" value="<%=allTypesVect.size()%>" /> 
			<input type="hidden" name="allDivisions" id="allDivisions" value="<%=divisionList1.size()%>" /> 
	
	 
	
	 <tr>
				     <td class="tableLeft">Approval Status: </td>
				     <td colspan="2" class="tableRight">
					 	<select name="uStaffStatus" id="uStaffStatus" class="selectboxOne" >
							<option selected="selected" value="" >Select One</option>
								<%	String[] status = new String[]{"Approved","Pending","Rejected"};
									for(int k =0; k<status.length; k++){
										if(usStatus!=null && usStatus.equalsIgnoreCase(status[k])){
								%>
							<option value="<%=status[k]%>" selected="selected" ><%=status[k]%></option>
								<%}else{%>
							<option value="<%=status[k]%>"><%=status[k]%></option>
								<%}}%>
						</select><span class="asterisk">&nbsp;*</span>	
					</td>
		      </tr>
	
			      
        <tr>
          <td class="tableLeft">Staff Comments :</td>
          <td  colspan="2" class="tableRight"><textarea id="txtStaffComm" name="txtStaffComm" rows="5" class="textboxOne" cols="25"><%=nullCheck(usComments)%></textarea>
              </td>
        </tr>
		
        <tr>
          <td class="tableLeft">&nbsp;</td>
          <td  colspan="2" class="tableRight"><input name="editButton" type="submit" class="gradBtn" value="Update"  />
            &nbsp;
            <input name="advDimButton" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" />          </td>
        </tr>
      </form>
    </table></td>
  </tr>
  
  <!-- ==================================================List Dimension Details==============================================-->
 
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
  </tr>
</table>
			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
<script language="javascript">
    var cal1 = new calendar2(document.forms['frmOECStaffEdit'].elements['startDate1']);
	 cal1.year_scroll = true; 
	 cal1.time_comp = false;
	 
    var cal2= new calendar2(document.forms['frmOECStaffEdit'].elements['endDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;	
	
	
	 var cal3 = new calendar2(document.forms['frmOECStaffEdit'].elements['entryBeginDate']);
	 cal3.year_scroll = true; 
	 cal3.time_comp = false;
	 
    var cal4= new calendar2(document.forms['frmOECStaffEdit'].elements['entryEndDate']);
	cal4.year_scroll = true;
	cal4.time_comp = false;	
	
		 
    var cal5= new calendar2(document.forms['frmOECStaffEdit'].elements['extDueDate']);
	cal5.year_scroll = true;
	cal5.time_comp = false;	
	
</script>

 

</body>
</html>
