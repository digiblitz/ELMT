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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<link href="../css/core-ie.css" type="text/css" rel="stylesheet" />
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Credit Card: <span class="styleBoldTwo">Transaction Details </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">&nbsp;</td>
				  </tr>
						  <tr>
							<td>
							
							<form name="frmUserSignup" id="myform" method="post" action="MemberSignupDetails.do" onsubmit="return myvalidate();">		  
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								  <tr>
									<td colspan="2" class="tblRowHead"> SSL Transaction Details </td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft">Transaction Result:</td>
									<td class="tableRight"><input type="text" name="merchId" id="merch_id" readonly="true" class="txtBoxLabel"  size="20" /></td>
								  </tr>
								  <input type="hidden" name="process" value="sign" />
					
								  <tr class="tableInner">
									<td class="tableLeft">SSL Transaction  ID:</td>
									<td class="tableRight"><input type="text" name="userId" id="user_id" readonly="true" class="txtBoxLabel" size="35" /></td>
								  </tr>
								 
								 <tr class="tableInner">
									<td class="tableLeft">SSL Approval Code:</td>
									<td class="tableRight"><input type="text" name="mercPinNo" id="mercPinNoId" readonly="true"  class="txtBoxLabel" size="20" /></td>
								  </tr>
								  
								   <tr class="tableInner">
									<td class="tableLeft">Transaction Type:</td>
									<td class="tableRight"><input type="text" name="mercPinNo" id="mercPinNoId" readonly="true"  class="txtBoxLabel" size="20" /></td>
								  </tr>
								  
								   <tr class="tableInner">
									<td class="tableLeft">Credit Card Number:</td>
									<td class="tableRight"><input type="text" name="mercPinNo" id="mercPinNoId" readonly="true"  class="txtBoxLabel" size="30" /></td>
								  </tr>
								  
								   <tr class="tableInner">
									<td class="tableLeft">Amount:</td>
									<td class="tableRight"><strong>$</strong>
								     <input name="mercPinNo" type="text"  class="txtBoxLabel" id="mercPinNoId" value="00.00" size="18" readonly="true" /></td>
								  </tr>
								  
								   <tr class="tableInner">
									<td class="tableLeft">e-Mail:</td>
									<td class="tableRight"><input type="text" name="mercPinNo" id="mercPinNoId" readonly="true"  class="txtBoxLabel" size="25" /></td>
								  </tr>
								  
								<tr>
									<td colspan="2" class="alignCenter">
									<input name="submit" type="submit" value="Done" class="gradBtn" />									</td>
							   </tr>				 
								<tr>
									<td colspan="2" height="25" class="alignCenter">&nbsp;</td>
							   </tr>
							</table>
						</form>
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
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>

s
</body>
</html>
