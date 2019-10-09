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
<jsp:directive.page import="java.util.*"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/Login.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function focus_login()
{
	document.form1.textfield.focus();
}
</script>
</head>

<body onLoad="focus_login();">
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "include/login_header.jsp"%> 
		<!-- HEADER ENDS HERE -->
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
					<td rowspan="2" class="welTabLft"></td>
					<td width="431" rowspan="2" class="loginTabLeft"> <span class="styleBoldWel">Welcome to the Integrated Enterprise Dashboard - Association / Club V0.5</span><br />
					  <br />
						 
						 <!-- Descrip Tab Starts Here -->
						 
						 <table width="410" border="0" align="left" cellpadding="0" cellspacing="0" id="welcomeTab">
							  <tr>
								<td height="3" class="loginTabLftTopCrnr"></td>
								<td class="loginDescBg"></td>
								<td width="12"  class="loginTabRghtTopCrnr"></td>
							  </tr>
							  <tr>
								<td class="loginTabLft">&nbsp;</td>
								<td class="loginDescBg">
								<div>
        <span class="labelTabHead">IE 5.5 - 7.0 & Firefox Compatible </span>
        </div><br />
        <br /><br /><div>
              <b>Welcome to the HLC Member Services Dashboard.</b><br /><br />
        </div><br />
        Recently Released User Services: 
        <ul>
         <li>2007 Annual Meeting &amp; Convention Registration<br />
         </li>
		 <li>2008 Membership Renewal &amp; Registration</li>
        </ul>
        
        <div>
If you encounter a technical problem, let 
us know at <a href="mailto:anandv@digiblitz.com"
class="linkFour">anandv@digiblitz.com</a></div>

<br />
<div
style="background-color:#FFFFFF;margin:15px;padding:10px;border:1px;border-s
tyle:solid;">

<div align="center"><a href="./MemberUsrSignUp.do?process=view"
class="linkFive">Click Here to Login for the First Time</a></div>
<br />
<br />
<span class="labelTabHead">IMPORTANT! Current or Expired Members</span>
<br />
<br />
When you create a new login, you must match your account details, or your HLC
membership & competition records will <b>NOT</b> be linked to your login.
</div>
         
							    </td>
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
					<td width="287" bgcolor="#FBFBEF"><br />
				      <br />
		            <img src="images/img_welcome.jpg" class="imgWelcFloatRight" /></td>
					<td rowspan="2" class="welTabRght" width="11">&nbsp;</td>
				  </tr>
				  <tr>
				    <td valign="top" bgcolor="#FBFBEF">
					<!-- Login Tab Starts Here -->
						<br />
					
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
						  <tr>
							<td width="10" height="3" class="loginTabLftTopCrnr"></td>
							<td width="251" height="3" class="loginCommonBg"></td>
							<td width="10" height="3" class="loginTabRghtTopCrnr"></td>
						  </tr>
						  <tr>
							<td class="loginTabLft" width="10" height="8">&nbsp;</td>
							<td class="loginCommonBg">
								
								
								<form id="form1" name="form1" method="post" action="OEELogin.do">
								<input type="hidden" name="cmd" value="checkLogin"  />
								  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="loginTab">
									<%
									String eventTypeId = (String)request.getAttribute("eventTypeId");
									String status=(String)request.getAttribute("status");
									String compYear = (String)request.getAttribute("compYear");
									if(status!=null)
									{										
										if(status.equals("fail"))
										{%>										
											<tr>
												  <td height="25" colspan="2" class="styleError">Invalid Login! Try logging in again.</td>
											</tr>
    									<%}}%>
									<tr>
									  <td height="25" colspan="2" class="styleBoldWel"> LOGIN HERE </td>
									</tr>
									<input type="hidden" name="eventTypeId" id="eventTypeId" value="<%=eventTypeId%>" />
									<input type="hidden" name="compYear" id="compYear" value="<%=compYear%>" />
									<tr>
									  <td width="33%" class="textBold">Username:</td>
									  <td width="67%"><input name="textfield" type="text" class="textboxOne" /></td>
									</tr>
									<tr>
									  <td class="textBold">Password:</td>
									  <td><input name="textfield2" type="password" class="textboxOne" /></td>
									</tr>
									<tr>
									  <td>&nbsp;</td>
									  <td><input name="Button" type="submit" class="gradTwoBtn" value="Login" /></td>
									</tr>
									<tr>
									  <td height="58" colspan="2" align="center">
									  <a href="./MemberUsrSignUp.do?process=view&source=fromEventEntry&compYear=<%=compYear%>&eventTypeId=<%=eventTypeId%>" class="linkFour">New User sign-up</a> <span class="divider">|</span>
									  <a href="./forgotPwd.do?process=view" class="linkFour">Forgot Password?</a> <br /><br />
									</tr>
								  </table>
								</form>
								 
							</td>
							<td class="loginTabRght" width="10" height="8">&nbsp;</td>
						  </tr>
						  <tr>
							<td width="10" height="8" class="loginTabLftBotCrnr"></td>
							<td height="8" class="loginTabBot"></td>
							<td width="10" height="8" class="loginTabRghtBotCrnr"></td>
							
						  </tr>
					</table>

					
				 	<!-- Login Tab Starts Here -->
					</td>
				  </tr>
				  <tr>
					<td width="11" height="8" class="welTabLftBotCrnr"></td>
					<td colspan="2" class="welTabBot" height="8"></td>
					<td class="welTabRghtBotCrnr" width="11"></td>
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
			<%@ include file = "include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
 

</body>
</html>
