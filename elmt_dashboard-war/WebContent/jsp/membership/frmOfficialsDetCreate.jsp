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
<script src="javascripts/frmOfficialsValidate.js" type="text/javascript" ></script>
<script src="javascripts/frmCreateContactType.js" type="text/javascript" ></script>
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
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Meetings: <span class="styleBoldTwo">Official Details </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><br />
	  <strong>Fill in the following information to add a Official Detail Name.</strong></td>
  </tr>
  <tr>
  	<td>
	
		<form method="post" name="frmOffcialDet" id="frmOffcialDet" action="./OfficialsDet.do" onsubmit="return offValidate();">
		
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			<tr>
					  <td colspan="2">Fields marked with an asterisk (*) are required.</td>
		  	</tr>
				<%
					String status=(String)request.getAttribute("err");
					if(status!=null)
					{
					if(status.equalsIgnoreCase("st"))
					{%>
				  <tr>
                            <td class="styleError" colspan="4">Official Detail  already Exists !!</td>
                  </tr>
					<%}}%>
			
				<tr>
					<td colspan="2" class="tblRowHead">Create Official Detail:</td>
				</tr>
				  <tr>
					<td class="tableLeft">Judge Type Name:</td>
					<td class="tableRight"><input name="judgeName" id="judgeName" type="text" class="textboxOne" maxlength="50" /> 
					<span class="asterisk">*</span></td>
				 </tr>
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" value="Create" class="gradBtn"/>&nbsp;
					<input type="hidden" name="offProcess" value="create" />
					<input type="reset" value="Reset" class="gradBtn" onclick="javascript:history.back(-1);"/>
					</td>
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
