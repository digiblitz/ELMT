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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "/include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "/include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
	<!-- CENTER TABLE STARTS HERE -->	
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
		  <tr>
			<td colspan="2" class="cenTablePad">
			<!-- Welcome Tab Starts Here -->	
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
				  <tr>
					<td width="11" class="welTabLftTopCrnr"></td>
					<td colspan="2" class="loginTabLeft"></td>
					<td width="11" class="welTabRghtTopCrnr"></td>
				  </tr>
				  <tr>
					<td rowspan="2" class="welTabLft">&nbsp;</td>
					<td rowspan="2" class="loginTabLeft"> <span class="styleBoldTwo">Change Password Confirmation </span><br />
					<br />
					  <br />
						 
						 <!-- Descrip Tab Starts Here -->
						 
						 <table width="410" border="0" align="left" cellpadding="0" cellspacing="0" id="welcomeTab">
							  <tr>
								<td class="loginTabLftTopCrnr"></td>
								<td class="loginDescBg"></td>
								<td class="loginTabRghtTopCrnr"></td>
							  </tr>
							  <tr>
								<td class="loginTabLft">&nbsp;</td>
								<td class="loginDescBg"><span class="textBold">Dear User</span>,<br />
								<%
								String stat=(String)request.getAttribute("status");
				
								if(stat!=null)
								{
								if(stat.equalsIgnoreCase("fail"))
								{%>

							    <br />
							    Your request for changing the Password <strong>failed.</strong> Please give the right current Password ! <br />
							    <br />

								<%}}else{%>

								<br />
							    Your request for changing the Password <strong>successful</strong> for your Username! Please Re-Login to make your new Password effective ! <br />
							    <br />					

								<%}%>	

							    Thank You,<br />
							    <span class="styleBoldOne">TEAM</span> </td>
								<td class="loginTabRght">&nbsp;</td>
							  </tr>
							  <tr>
								<td class="loginTabLftBotCrnr"></td>
								<td class="loginTabBot"></td>
								<td class="loginTabRghtBotCrnr"></td>
							  </tr>
					  </table>
						 
						 <!-- Descrip Tab Ends Here -->
						 
					</td>
					<td height="80" bgcolor="#FBFBEF" align="left"><img src="images/img_welcome.jpg" class="imgWelcFloatRight" /></td>
					<td rowspan="2" class="welTabRght">&nbsp;</td>
				  </tr>
				  <tr>
				    <td valign="top" bgcolor="#FBFBEF">
					<!-- Login Tab Starts Here -->
					<!-- Login Tab Starts Here --></td>
				  </tr>
				  <tr>
					<td class="welTabLftBotCrnr"></td>
					<td colspan="2" class="welTabBot"></td>
					<td class="welTabRghtBotCrnr"></td>
				  </tr>
				</table>
			<!-- Welcome Tab Ends Here -->
			</td>
		  </tr>
		</table>
	<!-- CENTER TABLE ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "/include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
</body>
</html>
