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
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmro.util.*"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />

<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmEventRegPendingPay.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<body >


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
				<div class="cmmnForm">
	<div class="colspan">
		<strong>Meetings: <span class="styleBoldTwo">Event Registration Pending Payment </span></strong>	</div>
    <div id="commonBG" class="textCommon"></div>
	<%
		String eventId = (String) request.getAttribute("eventId");
		String paymentId = (String) request.getAttribute("paymentId");
		//eventId
		if(eventId==null){
			response.sendRedirect("index.jsp");
		}
		if(paymentId==null){
			response.sendRedirect("index.jsp");
		}
	
	%>
	
<form name="frmEventPendPay" id="frmEventPendPay" method="post" class="formcss" action="eventPendPayment.do" onSubmit="return onValidate();" >
<input type="hidden" name="cmd" value="updatePendPay">
<input type="hidden" name="initeventId" value="<%=eventId%>" />
<input type="hidden" name="initpaymentId" value="<%=paymentId%>" />

    <div class="row">
		Fields marked with an asterisk(<span class="asterisk">*</span>) are mandatory.
	</div>

	<div class="rowHead">
		Payments/General Information
	</div>
	<%
	String amt=(String)request.getAttribute("totalAmt"); 
	%>
	<div class="row"> <span class="label">Total Amount:</span> <span class="formX">
	  <input  type="text" name="amount" id="amount" class="textboxOne" size="10" readonly="true" value="<%=amt%>" />
    <strong>$</strong></span>  </div>
			<div class="row">
			  <span class="floatLeft">
			  <input type="radio" size="10" name="r11"  value="check" onclick="checkClear(),switchDiv('checkEncl'), showHideRadio('r11','chargeCard','check');" >
			  </span>
			  <span class="floatLeft">
				  Check enclosed.
			  </span>
			  <span class="floatLeft">
			  <input type="radio" size="10" name="r11" value="card" onclick="cardClear(),switchDiv('chargeCard'), showHideRadio('r11','checkEncl','card');" ></span>
			  <span class="floatLeft">
				  Please charge my card.
			  </span>
			</div>

					<div id="checkEncl">
							<div class="colspan">
								<strong>Check Details:</strong>
							</div>
							
							<div class="tblDescrp">
																				<p>
																					If paying by check please mail your payment to:  <br /><br />
																					
																					<strong>
																					<br />
																					Event Organizer Registration
																					<br />
																					525 Old Waterford Road, NW
																					<br />
																					Leesburg, VA 20176
																					</strong><br /><br />
																					
																					<strong>Note:</strong> Your registration status will be pending until check is processed. <br /><br />
																				</p>
					  </div>
							
							<div class="row">
								<span class="label">Check No:</span>
								<span class="formX"><input type="text" name="checkNumber"  id="checkNumber" class="textboxOne" size="16" />
								<span class="asterisk">*</span></span>
							</div>
							
							<%
																		    String chkDat2="";
																		
																			java.util.Calendar c6 = java.util.Calendar.getInstance();
																			int dyChk = c6.get(Calendar.DATE);
																			int mnth = c6.get(Calendar.MONTH);
																			
																			int mnthChk = mnth+1;
																			int yrChk = c6.get(Calendar.YEAR);
																			
																			if(mnthChk<10)
																			{
																				chkDat2 = "0"+mnthChk+"/"+dyChk+"/"+yrChk;
																			}
																			else
																			{
																				
																				chkDat2 = mnthChk+"/"+dyChk+"/"+yrChk;
																			}
							%>
							
					  <div class="row"> <span class="label">Check Date:</span> <span class="formX">
					    <input type="text" name="checkDate"  id="checkDate" value="<%=chkDat2%>" readonly="true" class="textboxOne" size="10" /><a href="javascript:cal2.popup();">
					 <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
					</a>
				      <span class="asterisk">*</span>
					  </span>						</div>
							<div class="row">
								<span class="label">Bank Name:</span>
								<span class="formX"><input type="text" name="bankName" id="bankName" class="textboxOne" size="30" />
								<span class="asterisk">*</span></span>
							</div>
							
							<div class="row">
									<span class="label">Name on Check:</span>
									<span class="formX">
									<input name="nameOnchk" id="nameOnchk" type="text" class="textboxOne"  size="30" maxlength="25" /> <span class="asterisk">*</span>	</span>
							</div>
							
					</div>

					<div id="chargeCard">
							<div class="colspan">
								<strong>Card Details:</strong>
							</div>
							<div class="row">
								<span class="label">Card Type:</span>
								<span class="formX">
								<select name="ccType" id="ccType" class="selectboxOne">
								  <option value="" selected="selected" >Select One</option>
								  <option value="Visa">Visa</option>
								  <option value="Master Card">Master Card</option>
								  <option value="AmEx">AmEx</option>
								</select>
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Card No:</span>
								<span class="formX"><input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="20" />
								<span class="asterisk">*</span></span>
							</div>

							<div class="row">
								<span class="label">CVV No.:</span>
								<span class="formX"><input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
								</span>
							</div>
							<div class="row">
								<span class="label">Print Name On Card:</span>
								<span class="formX"><input type="text" name="ccName" id="ccName" class="textboxOne" size="15"/>
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Expiry Date:</span>
								<span class="formX">
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
								<span class="asterisk">*</span></span>
							</div>
					</div>
	
		<div class="row">
		<span class="label">&nbsp;</span>
		<span class="formX"><input type="submit" name="submit" value="Submit" class="gradBtn"/></span>
		</div>

	<div id="spacer">&nbsp;</div>

</form>
</div>
				
				
				
				
				
				
			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	
	</td>  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>


</body>
<script language="javascript">
	var cal2 = new calendar2(document.forms['frmEventPendPay'].elements['checkDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
	
</script>
</html>