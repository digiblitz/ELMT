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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						HLC Roles &amp; Privileges: <span class="styleBoldTwo"> Permissions Mapping For Privileges </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					<img src="images/usea_logo.jpg" class="imgFloatLeft" />
					Check the required <strong>'Permissions'</strong> to be given for a selected <strong>'Privilege</strong>' and corresponding <strong>'Entity'</strong>. Fill the required Name for the link to be show and it's relevant Link URL too. <br />
					<br />
					<br />					</td>
				  </tr>
				  <tr>
					<td>
					
						<form name="frmMeeEduActRecap" id="myform" action="">
					
							
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Map  Permissions: </td>
						  </tr>
						  
						  <tr>
						    <td class="tableLeft">Select an Entity:</td>
						    <td class="tableRight"><span class="alignLeft">
						      <select name="select3" id="select2" class="selectboxOne">
                                <option selected="selected">Select One</option>
                                <option>Accounting</option>
                                <option>Advertising</option>
                                <option>Inventory</option>
                                <option>Meetings</option>
                                <option>Membership</option>
                                <option>Sponsorship</option>
                                <option>Store</option>
                                                                                          </select>
						    </span></td>
					      </tr>
						  <tr> 
							<td class="tableLeftTxtArea"> Select A Privilege:</td>
							<td class="tableRight"><span class="alignLeft">
							  <select name="select" id="select" class="selectboxOne">
                                <option selected="selected">Select One</option>
                                <option>Customer Account Balance</option>
                                <option>Customer Account Status </option>
                                <option>Manual Sychronize with QB</option>
                                <option>Payment History</option>
                                <option>Available Credits</option>
                                <option>Ad Manifest</option>
                                <option>Electronic ad copy</option>
                                                            </select>
							</span></td>
						  </tr>
						  <tr>
						    <td colspan="2" class="alignCenter">
							
								<table width="100%" border="0" cellspacing="1" cellpadding="0" class="formLayout">
								  <tr>
									<td headers="20" width="6%" class="tblRowHead">&nbsp;</td>
									<td width="23%" class="tblRowHead">Permission </td>
									<td width="34%" class="tblRowHead">Link Name </td>
									<td width="37%" class="tblRowHead">Link URL </td>
								  </tr>
								  <tr>
									<td class="listCellBg"><input type="checkbox" name="checkbox" value="checkbox" /></td>
									<td class="listCellBg">View</td>
									<td class="listCellBg"><input name="textbox8" type="text" class="textboxOne" size="25" /></td>
									<td class="listCellBg"><input name="textbox82" type="text" class="textboxOne" size="35" /></td>
								  </tr>
								  <tr>
									<td class="listCellBg"><input type="checkbox" name="checkbox2" value="checkbox" /></td>
									<td class="listCellBg">Edit</td>
									<td class="listCellBg"><input name="textbox83" type="text" class="textboxOne" size="25" /></td>
									<td class="listCellBg"><input name="textbox822" type="text" class="textboxOne" size="35" /></td>
								  </tr>
								  <tr>
									<td class="listCellBg"><input type="checkbox" name="checkbox3" value="checkbox" /></td>
									<td class="listCellBg">Create</td>
									<td class="listCellBg"><input name="textbox84" type="text" class="textboxOne" size="25" /></td>
									<td class="listCellBg"><input name="textbox823" type="text" class="textboxOne" size="35" /></td>
								  </tr>
								  <tr>
									<td class="listCellBg"><input type="checkbox" name="checkbox4" value="checkbox" /></td>
									<td class="listCellBg">Delete</td>
									<td class="listCellBg"><input name="textbox85" type="text" class="textboxOne" size="25" /></td>
									<td class="listCellBg"><input name="textbox824" type="text" class="textboxOne" size="35" /></td>
								  </tr>
								</table>
							
							</td>
					      </tr>
						  <tr> 
							<td colspan="2" class="alignCenter"><input type="button" value="Submit" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" /></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
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

</body>
</html>