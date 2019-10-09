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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmAddNonUseaOrg.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
			
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer" width="100%">
  <tr>
    <td colspan="2" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Add New Non Organization</span>
	</td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
		<strong>Fill in the following information to add a new non organization Roster.</strong> <br />
	</td>
  </tr>
  <tr>
  	<td>
	
		<form method="post" name="frmAddNonUseaOrg" id="frmAddNonUseaOrg" action="./nonhlc.do" onsubmit="return myvalidate();">
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" width="100%">
				  <tr>
					<td colspan="2" class="tblRowHead">
						Add Non Organization </td>
						<%
						String statusadd=(String)request.getAttribute("statusadd");
						
						if(statusadd!=null)
						{
						if(statusadd.equalsIgnoreCase("false"))
						{%>
						</tr>
						<tr>
						<td class="styleError" colspan="3">Cannot Add this record. It already Exists !!</td>
						</tr>
						
						<%}}%>
				
					<tr>
							<td colspan="3" height="25">Fields marked with an asterisk (*) are required.</td>
					</tr>			
				  
				  <tr>
					<td width="61%" class="tableLeft" >Non Organization Name:</td>
					<td width="39%" class="tableRight"><input name="txtorgname" type="text" class="textboxOne" />	<span class="asterisk">*</span>					  <input type="hidden" name="process" value="Insert" /></td>
					</tr>
				   <tr>
					<td colspan="2" class="tableLeft"><input name="button" type="submit" class="gradBtn" value="Add" onClick=""/>
					  <input type="button" value="Cancel" class="gradBtn"  onclick="javascript:history.back(-1);" /></td>
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

</body>
</html>