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

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvDimensionList.jsp
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
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script language="javascript">

function postData(){
	frmRefundSubList.eventProcess.value = "show";
	//alert(frmRewalList.eventProcess.value);
    frmRefundSubList.method="post";
    frmRefundSubList.action="refundsubtype.do";
    frmRefundSubList.submit();
}
</script>
<%
String  status = (String)request.getAttribute("status");
System.out.println("status in list:"+status);
if(status==null || status.equals("")){
	status="";
}
%>
<body>
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
    <td colspan="3" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">View Existing Refund SubType Level Names </span></td>
  </tr>
  <tr>
    <td colspan="3" class="tblDescrp">
    <strong><br />
    To edit a Refund Sub Type Name click on <strong>'Edit'</strong> button. To change the status click on the <strong>'Activate/Deactivate'</strong> button.</td>
  </tr>
  <form name="frmRefundSubList" id="frmRefundSubList">
  <input type="hidden" name="process" value="newSubTypeList"  />
 
  
						   <input type="hidden" name="eventProcess" value="" />
						  
						  <tr>
					  <td width="36" height="20" valign="center" class="alignLeft"><strong>  Status:</strong></td>
					 
				      <td width="464" valign="center" class="alignLeft"><span class="tblDescrp">
				        <select name="status" class="selectboxOne" onchange="postData();">
                          <%if(status==""){%>
                          <option selected="selected" value="">Select One</option>
                          <%} else{%>
                          <option value="">Select One</option>
                          <%}%>
                          <%
							 if(status.equals("true")){
						%>
                          <option value="true" selected="selected" >Active</option>
                          <%
							} else {%>
                          <option value="true" >Active</option>
                          <%}%>
                          <%
							 if(status.equals("false")){
						%>
                          <option value="false" selected="selected" >Deactive</option>
                          <%} else {%>
                          <option value="false"  >Deactive</option>
                          <%}%>
                        </select>
				      </span></td>
				  </tr>
				  </form>
<tr>
  	<td colspan="2">
		<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
		 
		  <!-- <tr>
		  	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>		&nbsp;	 </td>
		   </tr>-->
		    <%
			String deleteStatus = (String)request.getAttribute("errStat");
			if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
			%>
			<!--<tr>
			<td class="styleError" colspan="4">Cannot Change the Status of this  record. its already in use</td>
			</tr>-->
			<%
			}
			
			%> 
		   
		   
		   <tr>
		
			<%
			Vector vDimLst = (Vector)request.getAttribute("DisplayEventLevel");
			
			if(vDimLst!=null && vDimLst.size()!=0){
			%>
			  <tr>
				<td width="183" class="tblRowHead">Refund SubType Name:</td>
				<td width="194" class="tblRowHead">Status</td>
				<td width="123" class="tblRowHead">Edit</td>
		     </tr>
			<%
	            Enumeration eDimLst = vDimLst.elements();
				String refundListId = "";
				String refundListName = "";
				String refundListStatus = "";
				while(eDimLst.hasMoreElements()){
					String[] s = (String [])eDimLst.nextElement();
					  refundListId = s[0];
					  refundListName = s[1];
					  refundListStatus = s[2];
					  
					  
		%>
			<tr>
				<form name="frmAdvDimensionLst" id="frmAdvDimensionLst" method="post" action="./refundsubtype.do">
				<input name="process" type="hidden" value="editRefundLevel">
				 
				<input type="hidden" value="<%=refundListId%>" name="refundListId">
				<input type="hidden" value="<%=refundListName%>" name="refundListName">
				<input type="hidden" value="<%=refundListStatus%>" name="rdstatus">
				<td class="alignLeft"><strong><%=refundListName%></strong></td>
				<% if(refundListStatus.equalsIgnoreCase("0")){ %>
		 <td class="listCellBg"><input name="btnSubmit" type="button" class="oneBtn" value=" Activate " onclick="location.href='./refundsubtype.do?process=Activate&refundListId=<%=refundListId%>&status=<%=status%>'" /></td>
		<%} else if(refundListStatus.equalsIgnoreCase("1")){	%>
		 <td class="listCellBg"><input name="btnSubmit2" type="button" class="oneBtn" value="Deactivate" onclick="location.href='./refundsubtype.do?process=Deactivate&refundListId=<%=refundListId%>&status=<%=status%>'" /></td>
		 <%}%>
				<td class="alignLeft"><input name="advDimButton" type="submit" value="Edit" class="oneBtn" /></td>
				</form>
			</tr>
					
					<%}	
					 }
					else {
					%>
				<tr>
				  <th height="25" colspan="4">No records are available. </th>
				</tr>
					<%}%>
					
			   
		  
			</table></td>
  </tr>
 
  <tr>
    <td height="20" colspan="2">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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


 

</body>
</html>




