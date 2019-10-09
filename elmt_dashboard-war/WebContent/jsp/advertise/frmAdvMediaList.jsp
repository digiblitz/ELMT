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


<!--/*  Program Name    : frmAdvMediaList.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.7
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
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		<strong></strong> Advertisement: <span class="styleBoldTwo">Media Type Listing </span>
	</td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><strong> Following are the  Media Types for all the advertisements that are to be placed for all the event. </strong><br />
<br />
To edit a Media Type Name click on <strong>'Edit'</strong> button. To delete a record click on the <strong>'Delete'</strong> button. </td>
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
				</table>
			</td>
		</tr>
			
<!--
			<tr>
		  		<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>&nbsp;			 </td>
		   </tr>
		 -->
		  <%
			String deleteStatus = (String)request.getAttribute("errStat");
			if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
			%>
			<tr>
			<td height="25" class="styleError" colspan="4">Cannot delete this record. The file is in use</td>
			</tr>
			<%
			}
			%> 
		  <tr>
			<td  class="tblRowHead">Media  Name</td>
			<td  class="tblRowHead">Edit</td>
			<td  class="tblRowHead">Delete</td>
		 </tr>
<%
			Vector v = (Vector)session.getAttribute("objSesAdvLstMedia");
			if(v!=null && v.size()!=0){
			
			
            Enumeration e = v.elements();
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
	
		%>
					<tr>
					<form name="frmAdvInsertMedia" method="post" action="./advertisement.do">
					<input name="advProcess" type="hidden" value="advManupMedia">
					<input type="hidden" value="<%=mediaId%>" name="mediaId">
					<td class="listCellBg"><span class="alignCenter"><%=mediaName%></span></td>
					
					<td height="24" class="listCellBg"><input name="advButValue" type="submit" value="Edit" class="oneBtn" /></td>
					<td height="24" class="listCellBg"><input name="advButValue" type="submit" value="Delete" class="twoBtn" /></td>
					
					</form>
					</tr>
					<%}	
					 }
					else {
					%>
					<tr>
					  <th height="25" colspan="4" align="center">No records are available. </th>
					</tr>
					<%}%>
			<!--
				<tr>
					<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
					<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
				  <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>&nbsp;			 </td>
			   </tr>
			 -->	
			</table>
			
		
	</td>
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
