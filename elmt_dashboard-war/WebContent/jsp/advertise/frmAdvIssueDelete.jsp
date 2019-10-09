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

<!--/*  Program Name    : frmAdvIssueDelete.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.6
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
<script src="javascripts/frmAdvIssue.js" type="text/javascript" ></script>
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">Delete Issue Master </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	  Please create the required Advertisement Issue Master for all the advertisements that are to be placed both in Magazine &amp; Omnibus for all the event. </strong><br />
		<br />
		Select a Media Type given below and fill-in the relevant Issue Master details. <br />
	<br /></td>
  </tr>
  <tr>
  	<td>

			<%
			Vector vvIssue = (Vector)session.getAttribute("objSesAdvDelIssueMedia");
			if(vvIssue==null && vvIssue.size()==0){
			response.sendRedirect("frmAdvIssueList.jsp");
			}
			%>

	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">Create Issue Master:</td>
				  </tr>
				   <form name="frmEditIssueMast" method="post" action="./advertisement.do" onsubmit="return advIssueEditCheck();">	
				   <input type="hidden" name="advProcess" value="advIssueDeleteIss">
				   <tr>
				
					
				     <td class="tableLeft">Media Type:</td>
				     <td class="tableRight">
					
						     <%	String issueId ="";
								String media_id ="";
								String issueName ="";
								String issueStatus ="";
								String[] s =(String[])session.getAttribute("objSesAdvDelIssue");
									if(s!=null){
										issueId=  s[0];
										media_id = s[2];
										issueName = s[1];
										issueStatus = s[3];					
									}
							 	Enumeration eIssue = vvIssue.elements();
								String mediaId = "";
								String mediaName = "";
								String mediaDesc  = "";
								String mediaStatus = "";
									while(eIssue.hasMoreElements()){
										String[] sMedia = (String [])eIssue.nextElement();
										mediaId = sMedia[0];
										mediaName =sMedia[1];
											    
												if(mediaId.equals(media_id)){
															%>
														<div><%=mediaName%></div>
												<%	
												}
												
												}
													%>
					   </select>
					 	</td>
				   </tr>
				 
				  <tr>
					<td class="tableLeft">Issue Name:</td>
					<input type="hidden" name="issueId" value="<%=issueId%>">
					<td class="tableRight"><%=issueName%></td>
					 
				  </tr>
			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" name="advIssueButton" value="Confirm Delete" class="gradBtn" />&nbsp;
					<input type="button" name="advIssueButton" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
				 
				  </tr>
				  </form>
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


�

</body>
</html>