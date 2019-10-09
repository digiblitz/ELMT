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

<!--/*  Program Name    : frmAdvDisplayTypeEdit .jsp
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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAdvDisplayType.js" type="text/javascript" ></script>
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
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">Update Advertisement Type </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	  Please create the required Advertisement Type for all the advertisements that are to be placed for all the event. </strong><br />
		<br />
		Select a Media Type given below and fill-in the relevant Advertisement Type details. <br />
	<br /></td>
  </tr>
  <% String statuscheck = (String)request.getAttribute("err");
				
				if(statuscheck!=null && statuscheck.equals("st")){
				%>
				<tr>
				<td colspan="2" class="styleError">&nbsp;<strong>Advertisement Type Name Already Exists. Try it Again</strong></td>
				</tr>
				<%
				}
				%>
  <tr>
  	<td>
			
		<form name="frmCreateAdvType" method="post" action="./advertisement.do" onsubmit="return advDisplayEdit();">
		 <input type="hidden" name="advProcess" value="advDispEdit">
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				<tr>
				  <td colspan="2" class="tblRowHead">Required fields are marked with an asterisk*</td>
				</tr>
				  <tr>
					<td colspan="2" class="tblRowHead">Create An Advertisement Type:</td>
				  </tr>
				   <tr>
				     <td class="tableLeft">Select A Media Type:</td>
				     <td class="tableRight">
					 	<select name="mediaDispId" id="mediaDispId" class="selectboxOne">
						<option selected="selected">Select One</option>
						<%
						String displayId = "";
						String displayName = "";
						String mediaDispId = "";
						String displayDesc = "";
						String displayStatus= "";
						
						String dispType [] =(String[])session.getAttribute("objSesAdvEditDisp");
						if(dispType!=null){	  
						displayId = dispType[0] ;
						displayName =dispType[1] ;
						mediaDispId =dispType[2] ;
						displayDesc = dispType[3] ;
						displayStatus =dispType[4] ;
						}
						%>
		<%
			Vector vDisp = (Vector)session.getAttribute("objSesAdvEditDispMedia");
			if(vDisp==null && vDisp.size()==0){
			response.sendRedirect("/advertise/frmAdvIssueList.jsp");
			}
			%>
						
							<%	Enumeration eDisp = vDisp.elements();
								String mediaId = "";
								String mediaName = "";
								String mediaDesc  = "";
								String mediaStatus = "";
									while(eDisp.hasMoreElements()){
										String[] sMedia = (String [])eDisp.nextElement();
										mediaId = sMedia[0];
										mediaName =sMedia[1];
										mediaDesc  =sMedia[2];
										mediaStatus = sMedia[3];
						                if(mediaId.equals(mediaDispId)){%>
												
													<option  value="<%=mediaId%>" selected><%=mediaName%></option>
													<%
												}
												else{
													%>
													 <option value="<%=mediaId%>"><%=mediaName%></option>
													<%
													}
												}
													%>
					    </select>
					 	<span class="asterisk">*</span></td>
		     	  </tr>
				    <input type="hidden" name="displayId" value="<%=displayId%>">
	
				  <tr>
					<td class="tableLeft">Advertisement Type Name:</td>
					<td class="tableRight">
					<input type="text" class="textboxOne" name="txtDisplayType"  value="<%=displayName%>"size="25" />
					<span class="asterisk">*</span></td>
				  </tr>
				  
				  <tr>
					<td class="tableTopAlign">Description:</td>
					<td class="tableRight">
					<textarea name="txtareaDisplayTypeDesc"  cols="25" rows="5" class="textAreaOne"><%=displayDesc%></textarea>
					<span class="asterisk">*</span>
					</td>
				  </tr>
				  
			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" name="advButtonDisp" value="Update" class="gradBtn"/>
					&nbsp;
					<input type="button" name="advButtonDisp" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
				  </tr>
			</table>
			
		</form>
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