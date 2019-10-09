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
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">View Existing Dimension Names </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	  Following are the  Dimension Names for all the advertisements that are to be placed for all the event. </strong><br />
	  <br />
	  To edit a dimension name click on <strong>'Edit'</strong> button. To delete a record click on the <strong>'Delete'</strong> button.  <br />
	<br /></td>
  </tr>
<tr>
  	<td>
		<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
		
		   
		    <%
			String deleteStatus = (String)request.getAttribute("errStat");
			if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
			%>
			<tr>
			<td class="styleError" colspan="4">Cannot delete this record. its already in use</td>
			</tr>
			<%
			}
			
			%> 
		   
		   
		   <tr>
		
			<%
			Vector vDimLst = (Vector)session.getAttribute("objSesAdvDimLst");
			if(vDimLst!=null && vDimLst.size()!=0){
			%>
			  <tr>
				<td width="183" class="tblRowHead">Dimension Name</td>
				<td width="194" class="tblRowHead">Edit</td>
				<td width="123" class="tblRowHead">Delete</td>
		     </tr>
			<%
	            Enumeration eDimLst = vDimLst.elements();
				String dimListId = "";
				String dimListName = "";
				String dimListStatus = "";
				while(eDimLst.hasMoreElements()){
					String[] s = (String [])eDimLst.nextElement();
					  dimListId = s[0];

					  dimListName = s[1];
					  dimListStatus = s[2];
		%>
			<tr>
				<form name="frmAdvDimensionLst" method="post" action="./advertisement.do">
				<input name="advProcess" type="hidden" value="advManupDimension">
				<input type="hidden" value="<%=dimListId%>" name="dimensionId">
				<td class="alignLeft"><strong><%=dimListName%></strong></td>
				
				<td class="alignLeft"><input name="advDimButton" type="submit" value="Edit" class="oneBtn" /></td>
				<td class="alignLeft"><input name="advDimButton" type="submit" value="Delete" class="twoBtn" /></td>
				</form>
			</tr>
					
					<%}	
					 }
					else {
					%>
				<tr>
				  <th height="25" colspan="4">There are no records available. </th>
				</tr>
					<%}%>
					
			   
		   <tr>
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
