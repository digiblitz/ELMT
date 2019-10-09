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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvDimensionConfirmation.jsp
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
<script src="javascripts/frmAdvertise.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
   <%   String eventId=(String)request.getAttribute("eventId");
        System.out.println("eventId in jsp"+eventId);
  		String itemId = (String)request.getAttribute("itemId");
		
		String existStatus = (String)request.getAttribute("AlreadyExist");
		String insertStatus = (String)request.getAttribute("insertStatus");
		String chmpExistStatus = (String)request.getAttribute("AlreadyExistChmp");
		String chmpInsertStatus = (String)request.getAttribute("chmpInsertStatus");
		System.out.println("existStatus"+existStatus);
		System.out.println("insertStatus"+insertStatus);
		System.out.println("chmpExistStatus"+chmpExistStatus);
		System.out.println("chmpInsertStatus"+chmpInsertStatus);
		String iStatus = "";
		String chmpIStatus = "";
		String isStatusExist="";
		String chmpIsStatusExist="";
	if(insertStatus!=null && insertStatus.equalsIgnoreCase("success")) iStatus = "Price Details Inserted Successfully";
		else if(insertStatus!=null && insertStatus.equalsIgnoreCase("failed")) iStatus = "Price Details Not Inserted";
	if(existStatus!=null && existStatus.equalsIgnoreCase("AlreadyExist")) isStatusExist="Price Details for the Item already exists!";
	
if(chmpInsertStatus!=null && chmpInsertStatus.equalsIgnoreCase("chmpsuccess")) chmpIStatus = "Championship Price Details Inserted Successfully";
else if(chmpInsertStatus!=null && chmpInsertStatus.equalsIgnoreCase("chmpfailed")) chmpIStatus = "Championship Price Details Not Inserted";
	if(chmpExistStatus!=null && chmpExistStatus.equalsIgnoreCase("chmpExist")) chmpIsStatusExist="Championship Price Details for the Item already exists!";
			
  %>
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
		
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		Online Event Entries: <span class="styleBoldTwo">Confirmation</span></td>
  </tr>
	 <%
	if(existStatus!=null && existStatus.trim().length()!=0){
	%>
  <tr>
		  <td height="25" colspan="8" class="styleError"><%=isStatusExist%></td>
		</tr>
	<%}%>
	<%
	if(insertStatus!=null && insertStatus.trim().length()!=0){
	%>
  <tr>
		  <td height="25" colspan="8" class="styleError"><%=iStatus%></td>
		</tr>
	<%}%>

	<%
	if(chmpExistStatus!=null && chmpExistStatus.trim().length()!=0){
	%>
  <tr>
		  <td height="25" colspan="8" class="styleError"><%=chmpIsStatusExist%></td>
		</tr>
	<%}%>
	<%
	if(chmpInsertStatus!=null && chmpInsertStatus.trim().length()!=0){
	%>
  <tr>
		  <td height="25" colspan="8" class="styleError"><%=chmpIStatus%></td>
		</tr>
	<%}%>
	<tr>
	<td>
    <span>
    <input name="button" type="button" class="gradBtn" value="Create Another" onclick="location.href='OEEAddPrice.do?cmd=initPrice&eventId=<%=eventId%>'" />&nbsp;
    <input name="button" type="button" class="gradBtn" value="Back To List" onclick="location.href='OEEAddPrice.do?cmd=initOrgView&eventId=<%=eventId%>'" />
	
	</span><br />
</td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
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