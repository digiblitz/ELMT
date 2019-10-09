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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
		<%@ include file = "../../include/login_header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				   <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups:</td>
				  </tr>	
				  	<tr>
						<td>
						<% String groupName = (String)request.getAttribute("groupName");%>
							<form name="frmInviteModerator" id="frmInviteModerator" action="/inviteAction.do" method="post">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">							
								 <tr>
									<td class="tblRowHead">Moderate A Group</td>
								 </tr>
								  <tr>
									<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">                                   	                                      
										  
										  <tr>
											<th height="25" class="tblRowHead"> Your have been Successfully activated as a Moderator  of the Group.</th><th class="tableLeft"> </th></tr>
											<tr><td class="listCellBg">The Group Name is : &nbsp;&nbsp;<%=groupName%>	</td>
											
										  </tr>	
									
								  
								 
								  <tr>
									<td colspan="2" height="35" class="alignCenter">
									<input type="button" value="Home" class="gradBtn" onclick="location.href='login.jsp'"/>
									</td>
									</form>
								  </tr>
							</table>
								
							
						
						
					   <tr>
						<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
					  </tr>
					  </table>
					</td>
				  </tr>
			<!-- CONTENTS END HERE -->		
			
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>

</body>
</html>