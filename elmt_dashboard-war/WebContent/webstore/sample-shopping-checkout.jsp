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
                      <td colspan="2"><a href="#" class="linkFour">Home</a> &raquo; <a href="#" class="linkFour">Shopping Cart </a>&raquo; <a href="#" class="linkFour">Checkout &amp; Payment Details </a></td>
                    </tr>
                    <tr>
                      <td colspan="2" height="25" align="left"  class="featHeadTwo" ><span class="styleBoldTwo">&nbsp;&nbsp;Checkout &amp; Payment Details </span></td>
                    </tr>
                    <tr>
                      <td colspan="2" height="20" class="tblRowHead">Shipping Address</td>
                    </tr>
                    <tr>
                      <td colspan="2" height="20" valign="top" style="padding:8px;" class="listCellBg">
					  
						  <table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="61%" valign="top">Please choose an address from below to which you would like your product to be shipped to. </td>
								<td width="39%" rowspan="2" valign="top">
									<span class="tabHead">Your Shipping Address:</span><br />
									  -----------------------------------------------------<br />
									  12, Park Avenue, <br />
									  First Cross Street, <br />
									  VA, 22044<br />
									  United States of America.								</td>
							  </tr>
							  <tr>
								<td><input name="radiobutton" type="radio" value="radiobutton" checked="checked" />Primary Address 
									<input name="radiobutton" type="radio" value="radiobutton" />Secondary Address								</td>
							  </tr>
						  </table>					  </td>
                    </tr>
                    <tr>
                      <td colspan="2" class="tblRowHead">Preffered Shipment Method </td>
                    </tr>
                    <tr>
                      <td colspan="2" style="padding-left:8px;" class="listCellBg">
					  
						  <table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
							  <td width="37%"><select name="select" class="selectboxOne">
								<option selected="selected">Select One</option>
								<option>Shipment Method One</option>
								<option>Shipment Method Two</option>
								<option>Shipment Method Three</option>
								<option>Shipment Method Four</option>
							  </select>							  </td>
							  <th width="11%" class="alignRight">Price ($):&nbsp;</th>
							  <td width="12%" ><input name="textfield" type="text" class="textboxOne" size="8" readonly="true" /></td>
							  <th width="24%"  class="alignRight">No. of Shipping Days:&nbsp; </th>
							  <td width="16%"><input name="textfield2" type="text" class="textboxOne" size="8" readonly="true" /></td>
							</tr>
						  </table>					  </td>
                    </tr>
					<tr>
                      <td colspan="2" class="tblRowHead">Payment Details </td>
                    </tr>
                    <tr>
                      <td colspan="2" class="listCellBg">Fields marked with asterisk (<span class="asterisk">*</span>) are required. </td>
                    </tr>
                    <tr>
                      <td colspan="2" class="listCellBg">
					  
					  <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                        <tr>
                          <td class="tableLeft"><strong>Total Amount:</strong></td>
                          <td class="tableRight"><strong>$</strong>
                              <input name="text" type="text" class="textboxOne" size="10" />
                              <span class="asterisk">*</span>						  </td>
                        </tr>
                        <tr>
                          <td class="tableLeft"><input type="radio" size="10" name="amt" value="check" checked="checked" onclick="switchDiv('chkEnc'), showHideRadio('amt','chrgCrd','check');" />
                            Check enclosed. </td>
                          <td class="tableRight"><input type="radio" size="10" name="amt" value="card" onclick="switchDiv('chrgCrd'), showHideRadio('amt','chkEnc','card');" />
                            Please charge my card.						  </td>
                        </tr>
                        <tr id="chkEnc">
                          <td colspan="2">
						  
							  <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
								  <tr>
									<td class="tblRowHead" colspan="3">Check Details:</td>
								  </tr>
								  <tr>
									<td class="tableLeft">Check No:</td>
									<td class="tableRight"><input name="text" type="text" class="textboxOne" size="16" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft">Check Date:</td>
									<td class="tableRight"><input name="text" type="text" class="textboxOne" size="16" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft">In Favor Of:</td>
									<td class="tableRight"><input name="text" type="text" class="textboxOne" size="16" />
										<span class="asterisk">*</span> </td>
								  </tr>
							  </table>						  </td>
                        </tr>
                        <tr id="chrgCrd">
                          <td colspan="2">
						  
							  <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
								  <tr>
									<td class="tblRowHead"  colspan="2">Card Details:</td>
								  </tr>
								  <tr>
									<td class="tableLeft">Card No.:</td>
									<td class="tableRight"><input name="text" type="text" class="textboxOne" size="16" />
										<span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft">Card CVV No.:</td>
									<td class="tableRight"><input name="text" type="text" class="textboxOne" size="5" />
										<span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft">Card Type:</td>
									<td class="tableRight"><select name="select2" id="select" class="selectboxOne">
										<option selected="selected">Select One</option>
										<option>Visa</option>
										<option>Master Card</option>
										<option>AmEx</option>
									  </select>
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft">Print Name On Card:</td>
									<td class="tableRight"><input name="text" type="text" class="textboxOne" size="25" />
										<span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft">Expiry Date:</td>
									<td class="tableRight"><select name="expirymonth" id="select" class="selectboxOne">
										<option value="" selected="selected">Month</option>
										<option value="01">January</option>
										<option value="02">February</option>
										<option value="03">March</option>
										<option value="04">April</option>
										<option value="05">May</option>
										<option value="06">June</option>
										<option value="07">July</option>
										<option value="08">August</option>
										<option value="09">September</option>
										<option value="10">October</option>
										<option value="11">November</option>
										<option value="12">December</option>
									  </select>
										<select name="expiryyear" id="select" class="selectboxOne">
										  <option value="" selected="selected" >Year</option>
										  <option  value="2006">2006</option>
										  <option  value="2007">2007</option>
										  <option  value="2008">2008</option>
										  <option  value="2009">2009</option>
										  <option  value="2010">2010</option>
										  <option  value="2011">2011</option>
										  <option  value="2012">2012</option>
										  <option  value="2013">2013</option>
										  <option  value="2014">2014</option>
										  <option  value="2015">2015</option>
										</select>
										<span class="asterisk">*</span> </td>
								  </tr>
							  </table>						  </td>
                        </tr>
                      </table></td>
                    </tr>
                    
                    <tr>
                      <td width="300" height="35" valign="middle" class="alignLeft">
 					    <p>
						<strong>&nbsp;Continue the checkout process 
						to finalize the order.</strong>						</p>                      </td>
					  <td width="270" valign="middle"><input name="Submit32" type="submit" class="gradTwoBtn" value="FINALIZE" /></td>
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
