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
			<td class="centerClass">
				<!-- CONTENT TAB STARTS HERE -->
				<form action="" method="post" enctype="multipart/form-data" name="frmShopCheckout" id="form1">
                  <table width="582" border="0" align="left" cellspacing="5" cellpadding="0" id="centerContentTab">
                    <tr>
                      <td ><a href="#" class="linkFour">Home</a> &raquo; <a href="#" class="linkFour">Inventory</a> &raquo; <a href="#" class="linkFour">Edit an Attribute </a></td>
                    </tr>
                    <tr>
                      <td height="25" align="left" class="featHeadTwo"><span class="styleBoldTwo">&nbsp; Edit/Update an Attribute </span> </td>
                    </tr>
                    <tr>
                      <td height="20" valign="top" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="203" class="tableLeft">Attribute Name:</td>
                            <td width="369" class="tableRight"><input name="textbox722" type="text" class="textboxOne" size="25" /></td>
                          </tr>
                          
                      </table></td>
                    </tr>
                    <tr>
                      <td height="35" valign="middle" class="alignCenter"><input type="button" name="submit" class="gradTwoBtn" value="Update" onclick="javascript:history.back(-1);" />
                          <input type="button" name="submit2" class="gradTwoBtn" value="Cancel" onclick="javascript:history.back(-1);" />
                      </td>
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
