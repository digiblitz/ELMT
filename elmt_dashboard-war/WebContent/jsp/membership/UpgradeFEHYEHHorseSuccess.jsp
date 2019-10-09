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
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
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
			Horse Membership Upgrade: <span class="styleBoldTwo">Confirmation.	</span></td>
	  </tr>
		<% 
			String membTypeName = (String) request.getAttribute("membTypeName");
			String horseMemberId = (String) request.getAttribute("horseMemberId");
			String horseName = (String) request.getAttribute("horseName");
			String successUrl = "RegHorseListing.do?process=UserListing";
		%>
  <tr>
    <td colspan="2" class="tblDescrp" style="padding:10px;">
    <strong> You have successfully Upgraded <span class="styleBoldOne">(<%=horseName%>)</span>  </strong><br />
	<br />Upgrade details are as follows:<br /><br /><br />
    <strong>Horse Member Id :</strong><span class="styleBoldOne"><%=horseName%></span><br /><br />
	<strong>Upgrade Type Name :</strong><span class="styleBoldOne"><%=membTypeName%></span><br /><br />
	<br />	 
		<br />
	<table align="center" cellpadding="2" cellspacing="3" width="100%">
	<tr>
		<td colspan="6"><span class="alignCenter"><input name="button" type="button" class="gradBtn" value="Back to List" onclick="location.href='<%=successUrl%>'" /></span>
		</td>
	</tr>
	</table>
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
