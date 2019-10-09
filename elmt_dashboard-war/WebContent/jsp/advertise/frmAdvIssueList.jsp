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

<!--/*  Program Name    : frmAdvIssueList.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.5
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
<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>
<script language="javascript">
function postIssueData(mid){
if(mid!=""){
	   
		var form = document.getElementById('frmAdvIssueLst'); 
		form.advProcess.value = "advIssueTypeShow";
		form.method="post";
		form.action="./advertisement.do?mid="+mid;
		form.submit();
		}
else {
	    form.advProcess.value = "advIssueLst";
		form.method="post";
		form.action="advertisement.do";
		form.submit();

}		
	}

</script>
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
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
<%
String  mId = (String)request.getAttribute("mid");
if(mId==null || mId.equals("")){
mId="";
}
%>
  <tr>
    <td colspan="5" class="tblMainHead">
	HLC Advertisement: <span class="styleBoldTwo">Advertisement Issue Listing</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The Advertisement Sub-Types are as follows. <br />
		<br />
		To edit a Sub-Type, click on the <strong>'Edit'</strong> button beside it. To detele a record click on the <strong>'Delete'</strong> button.  </td>
  </tr>
<tr>
  	<td>
		<table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		<tr>
		  <td colspan="4">
		  				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th height="20" width="130" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
					<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendOne">&nbsp;</span> &nbsp;Delete					</td>
				    <td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendTwo">&nbsp;</span> &nbsp;Edit.					</td>
				  </tr>
				</table>		  </td>
		  </tr>
		<!-- 
		<tr bgcolor="#FFFFFF">
		  <td height="25" colspan="4" class="alignRight">
		  <strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp; <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>
		  </td>
		  </tr>
			-->
		<form name="frmAdvIssueLst" id="frmAdvIssueLst">
		<input name="advProcess" type="hidden" value="">
			
			  <tr>
			    <td colspan="4" class="alignLeft">
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <th width="18%" height="20" class="alignRight">Media Type: </th>
                    <td width="82%" height="20">&nbsp;
					<select name="selMediaType" class="selectboxOne" onchange="postIssueData(this.value);">
					<option selected="selected" value="">Select One</option>
					<%
			Vector mediaType = (Vector)session.getAttribute("objSesAdvMediaIssue");
			if(mediaType!=null && mediaType.size()!=0){
		
			
            Enumeration e = mediaType.elements();
			    String mediaId = "";
				String mediaName = "";
				String mediaDesc  = "";
				String mediaStatus = "";
                while(e.hasMoreElements()){
                String[] s = (String [])e.nextElement();
					mediaId = s[0];
				//	out.print("mediaId" + mediaId);
					mediaName = s[1];
					mediaDesc  = s[2];
					mediaStatus = s[3];
					if(mediaId.equals(mId)){
							%>
							<option value="<%=mediaId%>" selected="selected"><%=mediaName%></option>
						    <%}
							else{
							%>
              			     <option value="<%=mediaId%>"><%=mediaName%></option>
							<%		
							}
							}
							}%>
				    </select>                 </td>
                  </tr>
                </table></td>
	      </tr>
		  </form>
			  <tr class="tblRowHeadTwo">
				<td  height="25" class="alignCenter">Issue Name</td>
				<td height="25" class="alignCenter">Edit</td>
				<td  height="25" class="alignCenter">Delete</td>
		     </tr>
			 <%
			String deleteStatus = (String)request.getAttribute("errStat");
			if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
			%>
			<tr>
			<td class="styleError" height="25" colspan="4">Cannot delete this record. its already in use</td>
			</tr>
			<%
			}
			%> 	  
			
			
			
			 <%
			Vector vIssueLst = (Vector)session.getAttribute("objSesAdvIssueLst");
			if(vIssueLst!=null && vIssueLst.size()!=0){
			%>
			 
					<%
					Enumeration eIssueLst =vIssueLst.elements();
						while(eIssueLst.hasMoreElements()){
						String[] sIss = (String [])eIssueLst.nextElement();
						//Debug.print("===============================");
						String issue_id=sIss[0];
						String issue_name=sIss[1];
						String media_id=sIss[2];
						String issue_status=sIss[3];
						//Debug.print("===============================");
					%>
			 <form name="frmAdvIssue" method="post" action="./advertisement.do">		
			<input name="advProcess" type="hidden" value="advManupIssue">		
			<tr bgcolor="#E3E1D2" class="alignCenter">
				
				<input type="hidden" value="<%=issue_id%>" name="issue_id">
				
				<td height="24" class="placeLeft"><strong><%=issue_name%></strong></td>
				
				<td height="24" class="placeLeft"><input name="advIssueButton" type="submit" value="Edit" class="oneBtn" /></td>
				<td height="24" class="placeLeft"><input name="advIssueButton" type="submit" value="Delete" class="twoBtn" /></td>
				
			</tr>
						</form>
					<%}	
					 }
					else {
					%>
					<tr>
					  <th height="24" colspan="4" align="center">No records are available. </th>
					</tr>
					<%}%>
				<!--
		<tr bgcolor="#FFFFFF">
		  <td height="25" colspan="4" class="alignRight">
		  <strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp; <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>
		  </td>
		</tr>
		  -->
	  </table></td>
  </tr>
 
  
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
</body>
</html>

