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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Business Service Center</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeEduActRecap.js" type="text/javascript" ></script>
<script src="javascripts/frmrRolePrevilageValidate.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
 <style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
 </style>
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
		<table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">

			  <tr>
				
				<td class="infoTopPad" width="2%">				</td>
				<td class="infoTopPad" width="90%" height="25">&nbsp;</td>
				<td class="infoTopPad" width="8%" align="center">
	</td>
				
			  </tr>
	  </table>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="fullHeight" class="menuTableBg">
				  <tr>
					<td class="alignTop"><br />
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="leftMenuTab">
						  <tr>
							<td class="menuTabLft"></td>
							<td class="menuTabTwoBg">
								
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1">

                                  <tr>
                                    <td height="20" class="alignLeft"><span class="textBold">&nbsp;</span> </td>
                                  </tr>
								  
									<tr>
										<td height="25">
										
										<!-- code for side bar starts here -->
	                                       <%@ include file = "include/#" %>
	                                    <!-- code for side bar starts here -->
		 	
									  </td>
								  </tr>
									 
									  															  								  
                                </table>

							</td>
							<td class="menuTabRght"></td>
						  </tr>
						  <tr>
							<td class="menuTabLftBotCrnr"></td>
							<td class="menuTabBot"></td>
							<td class="menuTabRghtBotCrnr"></td>
						  </tr>
						</table>

						<br />
					

					</td>
				  </tr>
				</table>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						Business Intelligence : 
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					
					 <span class="tabHead">Business Intelligence Reporting</span> 
					
					<br />					</td>
				  </tr>
				  <tr>
					<td>
					
						<form name="frmRoleMgtRolePrev" id="frmRoleMgtRolePrev" action="" onsubmit="return myvalidate();">
							<input type="hidden" name="roleProcess" value="createRole"/>
							
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="3" class="tblRowHead"> Please Select Report From the Dropdown list Below : </td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Order to Cash:</td>
							<td class="tableRight">
							
							<select name="roleId" id="roleId" class="selectboxTwo" onchange="">
							<option selected="selected" value="">Select One</option>
							<option>Backorder Report</option>
							<option>Sales Revenue Volume Report Monthly/Yearly / report</option>
							<option>Duplicate Sales Orders in a given time period</option>
							<option>Item Ranking Report based on Sales Revenue</option>
							<option>Item Ranking Report based on Sales Volume</option>
							<option>Item Availability Report</option>
							<option>Backorder Report</option>
							<option>Orders by Item Report</option>
							<option>Daily Exceptions Report </option>
							<option>Daily Shipment Report</option>
							<option>Shipping Error Analysis Report </option>
							<option>Customer/Material Returns Analysis</option>
							<option>Open Invoices Report</option>
							
							
							<option></option>
							<option></option>
							<option></option>
							<option></option>
							<option></option>
							</select></td>
			<td><input type="submit" value="Browse" class="gradBtn" />
						    </td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Procure to Pay:</td>
							<td class="tableRight">
							
							<select name="roleId" id="roleId" class="selectboxTwo" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
							<option>Purchase Requisition Report</option>
							<option>Cancelled Requisition Report</option>
							<option>Requisitions on Cancelled Sales  Order Report</option>
							<option>PO Purchases Report</option>
							<option>Cancelled PO Report</option>
							<option>Open PO Report</option>
							<option>PO Distribution Detail Report</option>
							<option>Advanced Shipment Notice </option>
							<option>Discrepant Receipts Report</option>
							<option>Receiving Transactions Register Report</option>
							<option>Receipt Adjustments Report</option>
							<option>Un invoiced receipts report</option>
							<option>Un ordered receipts report</option>
							<option>Suppliers Report (with Payment history)</option>
							</select></td>
			<td><input type="submit" value="Browse" class="gradBtn" />
						    </td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Plan to Produce</td>
							<td class="tableRight">
							
							<select name="roleId" id="roleId" class="selectboxTwo" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
							<option>Sales Order History Report </option>
							<option>Stock Requirement and Availability Report</option>
							<option>Material Requirement Planning Report</option>
							<option>Capacity Planning Report</option>
							<option>Master Schedule Status Report</option>
							<option>Late Order Report</option>
							<option>Material Usage Analysis Repor</option>
							<option>Bill of Material report</option>
							<option>Operation analysis report</option>
							<option>DC Capacity utilization report</option>
							<option>Distribution FTE report </option>
							<option>Quality against inspection lot report</option>
							<option>Vendor Analysis report </option>
							
							</select></td>
			<td><input type="submit" value="Browse" class="gradBtn" />
						    </td>
						  </tr>
						  <tr> 
							<td colspan="3" class="alignCenter"><input type="submit" value="Select Report" class="gradBtn" />
						    </td>
						  </tr>
						  <tr> 
							<td colspan="3" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
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
			<%@ include file = "/../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>


 

</body>
</html>
