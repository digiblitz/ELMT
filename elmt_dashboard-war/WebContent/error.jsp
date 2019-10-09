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
<%@ page language="java" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
	<!-- CENTER TABLE STARTS HERE -->	
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
		  <tr>
			<td colspan="2" class="cenTablePad">
			<!-- Welcome Tab Starts Here -->	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
				  <tr>
					<td class="welTabLftTopCrnr"></td>
					<td class="welcomeTab"></td>
					<td class="welTabRghtTopCrnr"></td>
				  </tr>
				  <tr>
					<td class="welTabLft">&nbsp;</td>
					<td class="welcomeTab">
						 <img src="images/img_welcome.jpg" class="imgWelcFloatRight" />
						 <span class="styleBoldWel">Welcome To Dashboard</span><br /><br />
						 <div>The modules and sub-modules displayed below has been assigned to you depending on the 
						 privileges that you hold as a/an <span class="styleBoldPrivilege">'Administrator'</span>.</div><br />
						 <div><strong>Click on any of the modules displayed below to list out it's related sub-modules beside 
						 and access it further.</strong></div>
					</td>
					<td class="welTabRght"></td>
				  </tr>
				  <tr>
					<td class="welTabLftBotCrnr"></td>
					<td class="welTabBot"></td>
					<td class="welTabRghtBotCrnr"></td>
				  </tr>
				</table>
			<!-- Welcome Tab Ends Here -->
			</td>
		  </tr>
		  <tr>
			<td colspan="2" class="deptTablePad">
			
			<table width="100%"  border="0" align="center" cellpadding="3" cellspacing="2">
              <tr>
                <td height="25"><div align="left">Sorry for the Inconvinence ! </div></td>
                </tr>
				<tr>
					<td ><div>Exception Class:</div><div><%=exception.getClass()%></div></td>
				</tr>
				
				<tr>
					<td ><div>Message:</div>
					<div><%=exception.getMessage()%></div></td>
				</tr>
				<tr>
					<td ><div>
					<input name="redir" type="button" style="cursor:hand;" onclick="window.location.href='index.jsp';" value="Back To Home page"/>
					</div></td>
				</tr>
            </table>
			</td>
		  </tr>
		</table>
	<!-- CENTER TABLE ENDS HERE -->
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