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
<title>HLC Online Services Webstore</title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/store-ie.css" type="text/css" rel="stylesheet" />
</head>

<body>
<table width="760" border="0" cellpadding="0" cellspacing="0" id="mainTable">
  <tr>
    <td>
		<!-- HEADER STARTS HERE -->
			<%@ include file = "include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="cenTablePad">
		<!-- CENTER TAB STARTS HERE -->
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
		  <tr>
			<td width="170" class="lpClass">
				<%@ include file = "include/panel-left-others.jsp" %>
			</td>
			<td class="centerClass">
				<!-- CONTENT TAB STARTS HERE -->
				<form name="frmShopCheckout" id="form1" method="post" action="">
				  <table width="100%" border="0" align="left" cellspacing="5" cellpadding="0" id="centerContentTab">
                    <tr>
                      <td ><a href="#" class="linkFour">Home</a> &raquo; <a href="#" class="linkFour">My Order History</a> &raquo; <a href="#" class="linkFour">Order Information</a></td>
                    </tr>
                    <tr>
                      <td height="25" align="left" class="featHeadTwo"><span class="styleBoldTwo">&nbsp; Order Information </span> </td>
                    </tr>
                    
                    <tr>
                      <td height="20" valign="top" >
					  
						  <table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
							  <td class="tableLeft">Order No.:</td>
							  <th class="tableRight">1</th>
							</tr>
							<tr>
							  <td class="tableLeft">Order Date:</td>
							  <th class="tableRight">14-10-2006</th>
							</tr>
							<tr>
							  <td class="tableLeft">Order Status:</td>
							  <th class="tableRight"><span class="styleBoldOne">Pending</span></th>
							</tr>
							<tr>
							  <td class="tableLeft">&nbsp;</td>
							  <td class="tableRight">&nbsp;</td>
							</tr>
							<tr>
							  <td class="tblRowHead"><strong>Delivery Address </strong></td>
							  <td height="25" class="tblRowHead"><strong>Billing Address </strong></td>
							</tr>
							<tr>
							  <td height="65" class="listCellBg" style="padding:8px;">
							  12, Park Avenue,<br />
							    South Cross Street,<br />
							    West Virginia, VA 22044<br />
							    United States of America. </td>
							  <td class="listCellBg" style="padding:8px;">
							    12, Park Avenue,<br />
								South Cross Street,<br />
								West Virginia, VA 22044<br />
								United States of America. 
								</td>
							</tr>
							<tr>
							  <td class="tableLeft">&nbsp;</td>
							  <td class="tableRight">&nbsp;</td>
							</tr>
							<tr>
							  <td class="tblRowHead">Product</td>
							  <td height="25" class="tblRowHead">Price ($)</td>
							</tr>
							<tr>
							  <td class="listCellBg" style="padding-left:8px;">Navy Blue HLC Cap <span class="styleBoldOne">(2)</span></td>
							  <td class="listCellBg" style="padding-left:8px;">36</td>
							</tr>
							<tr>
							  <td class="listCellBg" style="padding-left:8px;">Crimson Red HLC Cap <span class="styleBoldOne">(1)</span></td>
							  <td class="listCellBg" style="padding-left:8px;">25</td>
							</tr>
							<tr>
							  <td class="tableLeft">&nbsp;</td>
							  <td class="tableRight">&nbsp;</td>
							</tr>
							<tr>
							  <td class="tableLeft">Sub-Total:</td>
							  <th class="tableRight">$ 61</th>
							</tr>
							<tr>
							  <td class="tableLeft">Discount:</td>
							  <th class="tableRight">$ 5</th>
							</tr>
							<tr>
							  <td class="tableLeft"><strong>Grand Total:</strong></td>
							  <td class="tableRight"><span class="styleBoldOne">$ 56</span></td>
							</tr>
							<tr>
							  <td class="tableLeft">Payment Method:</td>
							  <th class="tableRight">Shipment method one</th>
							</tr>
					    </table>
					  </td>
                    </tr>
                    
                    
                    <tr>
                      <td height="35" valign="middle" class="alignCenter">
 					   <input type="button" name="submit" class="gradTwoBtn" value="Back to Listing" onclick="javascript:history.back(-1);" />
					   </td>
				    </tr>
                  </table>
              </form>
				<!-- CONTENT TAB ENDS HERE -->
			
			</td>
			<td width="170" class="rpClass">
				<%@ include file = "include/panel-right.jsp" %>
			</td>
		  </tr>
		</table>
		<!-- CENTER TAB ENDS HERE -->
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
