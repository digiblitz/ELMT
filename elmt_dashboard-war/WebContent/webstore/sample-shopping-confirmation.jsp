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
<script src="javascripts/frmShopCheckout.js" type="text/javascript" ></script>
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
			<td width="22%" class="lpClass">
				<%@ include file = "include/panel-left-others.jsp" %>
		    </td>
			<td width="88%" class="centerClass">
				<!-- CONTENT TAB STARTS HERE -->
				<form name="frmShopCheckout" id="form1" method="post" action="">
				  <table width="585" border="0" align="left" cellspacing="5" cellpadding="0" id="centerContentTab">
                    <tr>
                      <td ><a href="#" class="linkFour">Home</a> &raquo; <a href="#" class="linkFour">Shopping Cart </a>&raquo; <a href="#" class="linkFour"> Checkout &amp; Payment Details</a><a href="#" class="linkFour"> </a>&raquo; <a href="#" class="linkFour">Confirmation</a></td>
                    </tr>
                    <tr>
                      <td height="25" align="left" class="featHeadTwo"><span class="styleBoldTwo">&nbsp;&nbsp;Order Confirmation</span> </td>
                    </tr>
                    
                    <tr>
                      <td height="20" valign="top" style="padding:8px;" class="listCellBg"><span class="msgHead">Your order has been successfully processed! </span><br />
                        <br />
                      Your product will reach your destination within the shipment period mentioned. Thank You for shopping with <strong>United States Eventing Association</strong>.  </td>
                    </tr>
                    
                    
                    <tr>
                      <td height="35" valign="middle" class="alignLeft">
 					    <p>
						<strong>&nbsp;</strong><input name="Submit32" type="submit" class="gradTwoBtn" value="Continue" />
 					    </p> 					    </td>
				    </tr>
                  </table>
              </form>
				<!-- CONTENT TAB ENDS HERE --></td>
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
