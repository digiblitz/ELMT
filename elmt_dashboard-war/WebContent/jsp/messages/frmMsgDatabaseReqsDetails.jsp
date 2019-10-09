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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<bean:define id="requestDetails" name="REQUEST_DETAILS" scope="request"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmfrmMsgRequestDatabases.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<form name="frmfrmMsgRequestDatabases" id="myform" action="">	
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
					  <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Database Dowloading Request Details  </span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp"> You	are	viewing	the	details	of the <strong>Database Downloading Request</strong> sent by you.	</td>
					  </tr>
					  <tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									<td colspan="2" class="tblRowHead">Download Information</td>
								 </tr> 
								 <tr>
									<td colspan="2">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											  <tr>
												<td width="42%" class="tableLeft">Number of addresses selected:</td>
												<td width="30%" class="tableRight"><span class="labelTabHead"><bean:write name="requestDetails" property="noOfRecords"/></span> Nos. </td>
											  </tr>
											   <tr>
													<td class="tableLeft">Date of Request: </td>
													<th class="tableRight"><bean:write name="requestDetails" property="addDate"/></th>
											  </tr>
										</table>
									</td>
								 </tr>				 

									  <tr>
									    <td colspan="2" height="35">
										
												  
												<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
													  <tr>
														<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
													  </tr>
											
													  
													  <tr>
														<td class="tableLeft"><strong>Total Amount:</strong></td>
														<td class="tableRight"> <strong>$</strong><span class="labelTabHead"><bean:write name="requestDetails" property="totalAmount"/></span></td>
													  </tr>
												</table>
										</td>
						      		  </tr>
									   <tr>
										<td colspan="2" class="tblRowHead">&nbsp;Approval Status</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Status:</td>
										<td class="tableRight">
											<span class="styleBoldOne"><bean:write name="requestDetails" property="requestStatus"/></span>
									  </tr>
									  <tr>
										<td class="tableLeftTxtArea">Comments: </td>
										<td class="tableRight"><bean:write name="requestDetails" property="comments"/></td>
									  </tr>
									  <tr>
										<th colspan="2" height="35" class="alignCenter">
											<input type="button" value="Back To Listings" class="gradBtn" style="width:110px;" onclick="javascript:history.back(-1);" />										</th>
									  </tr>
								</table>
						</td>
					  </tr>
					   <tr>
						<td colspan="2" height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
					  </tr>
				</table>
					
				</form>	
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
