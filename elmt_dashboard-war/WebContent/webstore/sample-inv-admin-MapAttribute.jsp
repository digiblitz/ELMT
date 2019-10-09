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
				<form name="frmShopCheckout" id="form1" method="post" action="">
                  <table width="582" border="0" align="left" cellspacing="5" cellpadding="0" id="centerContentTab">
                    <tr>
                      <td ><a href="#" class="linkFour">Home</a> &raquo; <a href="#" class="linkFour">Inventory </a> &raquo; <a href="#" class="linkFour">Map Attributes </a></td>
                    </tr>
                    <tr>
                      <td height="25" align="left" class="featHeadTwo"><span class="styleBoldTwo">&nbsp;&nbsp;Map Attributes to Products </span></td>
                    </tr>
                    <tr>
                      <td height="20" valign="top" ><table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
                          <tr>
                            <td colspan="4">
							
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                  <th valign="top" class="tableLeft">Category:</th>
                                  <td colspan="6" valign="middle" class="tableRight">
                                    <select name="hlcAreaId" class="textboxOne" id="txtUseaArea" >
                                      <option selected="selected" value="">Select One</option>
                                    </select>                                  </td>
                              </tr>
							  <tr>
                                  <th valign="middle" class="tableLeft">Sub-Category:</th>
								  <td valign="middle" class="tableRight">
								    <select name="select" class="textboxOne" id="select" >
                                      <option selected="selected" value="">Select One</option>
                                    </select>								  </td>
                              </tr>
							  <tr>
                                  <th valign="middle" class="tableLeft">Product:</th>
								  <td valign="middle" class="tableRight">
								    <select name="select" class="textboxOne" id="select" >
                                      <option selected="selected" value="">Select One</option>
                                    </select>								  </td>
                              </tr>
                            </table>							</td>
                          </tr>
                          <tr>
                            <td width="48" height="27" class="tblRowHead">Select</td>
                            <td width="207" class="tblRowHead">Attribute Name </td>
                            <td width="295" class="tblRowHead">Value</td>
                          </tr>
                          <tr>
                            <td height="26" class="listCellBg" style="padding-left:10px;">
							<input type="checkbox" name="checkbox" value="checkbox" /></td>
                            <td class="listCellBg" style="padding-left:10px;">Color</td>
                            <td class="listCellBg" style="padding-left:10px;">
                              <input name="textbox73" type="text" class="textboxOne" size="25" />
                            </td>
                          </tr>
                          <tr>
                            <td height="26" class="listCellBg" style="padding-left:10px;">
                              <input type="checkbox" name="checkbox2" value="checkbox" />
                            </td>
                            <td class="listCellBg" style="padding-left:10px;">Size</td>
                            <td class="listCellBg" style="padding-left:10px;">
                              <input name="textbox732" type="text" class="textboxOne" size="25" />
                            </td>
                          </tr>
                          <!--
								<tr>
								<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
									<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
								<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
							   </tr>
							 -->
                      </table></td>
                    </tr>
                    <tr>
                      <td height="35" valign="middle" class="alignCenter">
                      <input type="button" name="submit2" class="gradTwoBtn" value="Submit" />
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