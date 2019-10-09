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
                      <td ><a href="#" class="linkFour">Home</a> &raquo; <a href="#" class="linkFour">My Order History</a></td>
                    </tr>
                    <tr>
                      <td height="25" align="left" class="featHeadTwo"><span class="styleBoldTwo">&nbsp;&nbsp;My Order History</span> </td>
                    </tr>
                    
                    <tr>
                      <td height="20" valign="top" >
					  
						  <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
							<tr>
							  <td colspan="6">
							  
								  <table width="100%" border="0" cellspacing="0" cellpadding="0">
									  <tr>
										<th width="125" height="20" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
										<td colspan="3" valign="middle" class="alignLeft"><span class="legendTwo">&nbsp;</span> &nbsp;View. </td>
										<!--
									<td colspan="3" valign="middle" class="alignLeft">
										<span class="legendOne">&nbsp;</span> &nbsp;Deactivate.
									</td>
										-->
									  </tr>
								  </table>
							  
							  </td>
							</tr>
							<tr>
							  <td width="61" height="27" class="tblRowHead">Order No.</td>
							  <td width="178" class="tblRowHead">Date of Order</td>
							  <td width="119" class="tblRowHead">Cost ($)</td>
							  <td width="118" class="tblRowHead">Status</td>
							  <td width="63" class="tblRowHead">View</td>
							</tr>
							<tr>
							  <td height="26" class="listCellBg" style="padding-left:10px;">1</td>
							  <td class="listCellBg" style="padding-left:10px;">14-10-2006</td>
							  <td class="listCellBg" style="padding-left:10px;">38</td>
							  <td class="listCellBg" style="padding-left:10px;"><span class="styleBoldOne">Pending</span></td>
							  <td class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="View" onclick="javascript:window.location.href('frmMsgDatabaseReqsDetails.jsp');" /></td>
							</tr>
							<tr>
							  <td height="26" class="listCellBg" style="padding-left:10px;">2</td>
							  <td class="listCellBg" style="padding-left:10px;">12-09-2006</td>
							  <td class="listCellBg" style="padding-left:10px;">78</td>
							  <td class="listCellBg" style="padding-left:10px;"><span class="styleBoldTwo">Delivered</span></td>
							  <td class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="View" /></td>
							</tr>
							<!--
								<tr>
								<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
									<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
								<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
							   </tr>
							 -->
						  </table>
					  </td>
                    </tr>
                    
                    
                    <tr>
                      <td height="35" valign="middle" class="alignLeft">
 					    <p> <strong>&nbsp;</strong></p> 					    </td>
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
