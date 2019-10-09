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

       <!-- /*  Program Name    : frmSponsorPaymentDet.jsp
         *  Created Date    : September 4, 2006, 4:24 PM
         *  Author          : Punitha.R
         *  Version         : 1.6
         *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
        */
-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmPayment.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript" ></script>

<%
String dueAmount = (String)request.getAttribute("dueAmount");
String sponsorId = (String)request.getAttribute("sponsorId");
String scheId = (String)request.getAttribute("scheId");

if(dueAmount==null || dueAmount.equals("")){

}
%>


<link href="css/core-ie.css" type="text/css" rel="stylesheet" /></head>

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
			<td class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
		<form name="frmAdvMag" id="frmAdvMag" method="post" action="Payment.do" onsubmit="return onPayCheck();">
		<input type="hidden" name="pro" value="p0a1y01" />
		<input type="hidden" name="sponsorId" value="<%=sponsorId%>" />
		<input type="hidden" name="scheduleId" value="<%=scheId%>" />
		<input type="hidden" name="userId" value="ff093ecd-e823-4e36-acb9-cef71534dd74"/>
		<input type="hidden" name="amount" value="<%=dueAmount%>" />
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
				  <tr>
				    <td colspan="2" class="tblRowHead"><p>Required fields are marked with an asterisk*</p></td>
			    </tr>
				  <tr>
					<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
				  </tr>
  
				  <tr>
					<td class="tableLeft"><strong>Total Amount:</strong></td>
				    <td class="tableRight"> <strong>$</strong>
					 <input type="text" class="textboxOne" size="10"  value="<%=dueAmount%>" readonly="true"/>
					 
				      <span class="asterisk">*</span></td>
				  </tr>
									  <tr>
									  	<td class="tableLeft">
									 		<input type="radio" size="10" name="r11" value="check" checked="checked" onclick="switchDiv('chkEnc'), showHideRadio('r11','chrgCrd','check'),cardClear();"/> Check enclosed.				 	</td>
									 	<td class="tableRight">
									 		<input type="radio" size="10" name="r11" value="card" 
onclick="switchDiv('chrgCrd'), showHideRadio('r11','chkEnc','card'),checkClear();"  /> Please charge my card.					</td>
									  </tr>
						  			  <tr id="chkEnc">
										<td colspan="2">

											<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
												<tr>
													<td class="tblMainHead" colspan="3">Check Details:</td>
												</tr>
												<tr>
												  <td class="tableLeft">Check No:</td>
												  <td class="tableRight"><input type="text" name="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
												</tr>
												<tr>
												  <td class="tableLeft">Check Date:</td>
												  <td class="tableRight"><input type="text" name="checkDate" class="textboxOne" readonly="yes"size="16" />

												  <a href="javascript:cal1.popup();">
					                   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">												  </a>
										  <span class="asterisk">*</span>												  </td>
												</tr>
												<tr>
												  <td class="tableLeft">Bank Name :</td>
												  <td class="tableRight">
												  <input type="text" name="bankName" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
												</tr>
											</table>					</td>
									  </tr>

									  <tr id="chrgCrd">
										<td colspan="2">

											<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
												<tr>
													<td class="tblMainHead" colspan="2">Card Details:</td>
												</tr>
												<tr>
													<td class="tableLeft">Card No.:</td>
												  <td class="tableRight">
												  <input type="text" name="ccNumber" class="textboxOne" size="16" />
					                                  <span class="asterisk">*</span></td>
												</tr>
												<tr>
													<td class="tableLeft">Card CVV No.:</td>
												  <td class="tableRight">
												  <input type="text" name="ccCvvid" class="textboxOne" size="5" />
					                                  <span class="asterisk">*</span></td>
												</tr>
												<tr>
													<td class="tableLeft">Card Type:</td>
												  <td class="tableRight">
														<select name="ccType" id="ccType" class="selectboxOne">
														  <option selected="selected">Select One</option>
														  <option value="Visa">Visa</option>
														  <option value="Master Card">Master Card</option>
														  <option value="AmEx">AmEx</option>
														</select>
					                                    <span class="asterisk">*</span>								</td>
												</tr>
												<tr>
													<td class="tableLeft">Print Name On Card:</td>
												  <td class="tableRight">
												  <input type="text" name="ccName" class="textboxOne" size="25" />
					                                  <span class="asterisk">*</span></td>
												</tr>
												<tr>
													<td class="tableLeft">Expiry Date:</td>
												  <td class="tableRight">
														<select name="ccExpMonth" id="ccExpMonth" class="selectboxOne">
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
														<select name="ccExpYear" id="ccExpYear" class="selectboxOne">
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
					                                    <span class="asterisk">*</span>			         </td>
								      </tr>
								 </table>								</td>
							</tr>
							<tr>
								<td colspan="2" class="alignCenter">
								<input type="submit" value="Sponsorship Payment" class="gradBtn" /></td>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmAdvMag'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>
