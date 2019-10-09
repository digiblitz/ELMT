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
<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeAnnualConvRegister.js" type="text/javascript" ></script>
<!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->

</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "include/menu-messages-member.jsp" %>
			<!-- LEFT MENU ENDS HERE -->
			&nbsp;
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
					
						<form name="frmMeeAnnualConvRegister" id="myform" action="">
					
							
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="formLayout">
							<tr>
								<td colspan="2" class="tblMainHead"><strong>HLC</strong> Role Management <span class="styleBoldTwo"> Home</span></td>
							</tr>
							<tr>
								<td colspan="2" class="tblDescrp"><img src="images/usea_logo.jpg" width="100" height="99" /></td>
							</tr>
							<tr> 
								<td colspan="2" class="tblRowHead">Available Links</td>
							</tr>
							<tr> 
								<td colspan="2" class="alignCenter">
								  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
									  <tr>
										<td rowspan="2" valign="top">
										
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">
												  <tr>
													<td height="20" class="alignLeft"><span class="textBold">&nbsp;&nbsp;&nbsp;Message Section </span> </td>
												  </tr>
												  <tr>
													<td height="20" class="menuLinkbg" align="left">&raquo;
												    <a href="messages.do?msgProcess=inbox" class="linkFour"> Inbox</a></td>
												  </tr>
												   <tr>
													<td height="20" class="menuLinkbg" align="left">&nbsp;</td>
												  </tr>
											</table>
										
										</td>
										<td valign="top">&nbsp;</td>
									  </tr>
									  <tr>
										<td valign="top">&nbsp;</td>
									  </tr>
									  <tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									  </tr>
								  </table>
							  </td>
							</tr>
							
					  </table>
					  </form>
				  <tr>
						<td >&nbsp; 
					   		<!-- DO NOT DELETE THIS ROW -->
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
