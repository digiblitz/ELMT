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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Business Service Center</title>

<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/Login.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function focus_login()
{
	document.form1.textfield.focus();
}
</script>

<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-sidebar.css" />

<script type="text/javascript" src="js/ddlevelsmenu.js">

/***********************************************
* All Levels Navigational Menu- (c) Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/

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
		
		<!-- HEADER ENDS HERE -->
		
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">

			  <tr>
				
				
				<td class="infoTopPad" height="25" align="right" colspan="2"><span class="tabHead"> LogIn Page</span></td>
				<td class="infoTopPad" width="2%">&nbsp;				</td>
				
			  </tr>
			  
			  <tr>
				
				<td class="infoTopPad" width="2%">&nbsp;				</td>				
				<td class="infoTopPad" height="25" colspan="2"><span class="styleBoldTwo">&nbsp;</span></td>
				
				
			  </tr>
			  
	  </table>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>

  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="430" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			


  

				<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="fullHeight" class="menuTableBg">
				  <tr>
					<td bgcolor="#ffffff" width="430"><img src="images/view.JPG" width="411" height="296"/></td>
				  </tr>
			  </table>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="315" class="menuTablePad" align="center">
			
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="fullHeight" class="menuTableBg">
				  <tr>
				  <td width="287" height="50">&nbsp;
				      
		            </td>
					</tr>
					<tr>
					<td>
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" id="welcomeTab">
						  
						  <tr>
							
							<td class="loginCommonBg">
								
								
								 <form id="form1" name="form1" method="post" action="./MemberLogin.do">
								  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="loginTab">
									<%
									if(request.getAttribute("status") != null){
									String status=(String)request.getAttribute("status");
									if(status!=null)
									{										
										if(status.equals("fail"))
										{%>										
											<tr>
												  <td height="25" colspan="2" class="styleError">Invalid Login! Try logging in again.</td>
											</tr>
    									<%}}}%>
									<tr>
									  <td height="25" colspan="2" class="tabHead" align="center"> LOGIN HERE </td>
									</tr>
									
									<tr>
									  <td width="33%" class="textBold" align="center">Username:</td>
									  <td width="67%" align="center"><input name="textfield" type="text" class="textboxOne" /></td>
									</tr>
									<tr>
									  <td class="textBold" align="center">Password:</td>
									  <td align="center"><input name="textfield2" type="password" class="textboxOne" /></td>
									</tr>
									<tr>
									  <td>&nbsp;</td>
									  <td align="center"><input name="Button" type="submit" class="gradBtn" value="Login" /></td>
									</tr>
									<tr>
									  <td height="58" colspan="2" align="center">
									   
									  <a href="#" class="linkFour">Forgot Password?</a> <span class="divider">|</span>
									  <a href="#" class="linkFour">Click Here!</a> <br /><br />
									 
									</tr>
								  </table>
								</form>
								 
							</td>
							
						  </tr>
						  
					</table>
					
					</td>
				  </tr>
				</table>

			<!--  MENU ENDS HERE -->

		    </td>
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
			<%@ include file = "include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>


 </body>
</html>
