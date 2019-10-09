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
	<!-- CENTER TABLE STARTS HERE -->	
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
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
			<td width="400" class="deptTablePad">
			  <table width="396" border="0" align="center" cellpadding="0" cellspacing="0" id="deptTab">
				  <tr>
					<td class="deptTabHead">&nbsp;</td>
				  </tr>
				  <tr>
				  	<td class="deptTabList">
					<!-- Department List Starts Here -->
						<table width="396" border="0" cellspacing="0" cellpadding="0">
						   <tr>
							<td class="deptTabListOth"><a href="#" class="linkFour">&raquo; Membership  Department - <span class="styleClick">Click Here</span></a></td>
						  </tr>
						  <tr>
							<td class="deptTabListOth"><a href="#" class="linkFour">&raquo; Events & Meetings Department - <span class="styleClick">Click Here</span></a></td>
						  </tr>
						  <tr>
							<td class="deptTabListOth"><a href="#" class="linkFour">&raquo; Sponsorship & Advertisement Department - <span class="styleClick">Click Here</span></a></td>
						  </tr>
						  <tr>
							<td class="deptTabListOth"><a href="#" class="linkFour">&raquo; Inventory Department - <span class="styleClick">Click Here</span></a></td>
						  </tr>
						  <tr>
							<td class="deptTabListOth"><a href="#" class="linkFour">&raquo; Accounts Department - <span class="styleClick">Click Here</span></a></td>
						  </tr>
						</table>
					<!-- Department List Ends Here -->
					</td>
				  </tr>
				  <tr>
					<td class="deptTabFoot">&nbsp;</td>
				  </tr>
			  </table>
			</td>
			<td width="340" class="subDeptTablePad">

				<table width="337" border="0" align="center" cellpadding="0" cellspacing="0" id="subDeptTab">
				  <tr>
					<td class="subDeptTabHead">Sub-Departments - <span class="styleBoldWel">Membership</span></td>
				  </tr>
				  <tr>
					<td class="subDeptListBg">
							
					<!-- Sub-Department List Starts Here -->
					<div class="subDeptDiv">
						<table width="320" border="0" align="left" cellpadding="2" cellspacing="1">
						  <tr>
							<td class="subDeptTabList">&bull; Member Registration</td>
						  </tr>
						  <tr>
							<td class="subDeptTabList">&bull; Horse Registration</td>
						  </tr>
						  <tr>
							<td class="subDeptTabList">&bull; Event Organizer Registration</td>
						  </tr>
						  <tr>
							<td class="subDeptTabList">&bull; Non-Member Registration;</td>
						  </tr>
						  <tr>
							<td class="subDeptTabList">&bull; Member Registration</td>
						  </tr>
						  <tr>
						    <td class="subDeptTabList">&bull; Member Registration</td>
					      </tr>
						  <tr>
						    <td class="subDeptTabList">&bull; Member Registration</td>
					      </tr>
						  <tr>
						    <td class="subDeptTabList">&nbsp;</td>
					      </tr>
						  <tr>
						    <td class="subDeptTabList">&nbsp;</td>
					      </tr>
					  </table>
					 </div> 
					<!-- Sub-Department List Ends Here -->
					
					</td>
				  </tr>
				  <tr>
					<td class="subDeptTabFoot">&nbsp;</td>
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
			<%@ include file = "include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
 

</body>
</html>
