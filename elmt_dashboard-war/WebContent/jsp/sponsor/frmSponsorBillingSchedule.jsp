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
<%@ page language = "java"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcspnr.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

        <!--/*  Program Name    : frmSponsorBillingSchedule.JSP
         *  Created Date    : September 4, 2006, 4:24 PM
         *  Author          : Punitha.R
         *  Version         : 1.9
         *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
        */-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmBillingSchedule.js" type="text/javascript" ></script>
<script language="JavaScript" src="javascripts/calendar2.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>

<body>
<%
String sponDetails[] =(String [])session.getAttribute("spnrBillingDet");
if(sponDetails==null){
	response.sendRedirect("SponsorRequestInsert.do?pro=p1");
}

%>
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
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		HLC Billing Schedule Options
	</td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	    
		<strong>Please fill in the following information to state your billing schedule options
		provided by HLC</strong> <br />
	</td>
  </tr>
   <%
		String sid  = sponDetails[0];
		String cmpName  = sponDetails[1];
		String comments  = sponDetails[2];
		String planName  = sponDetails[4];
		String Amount  = sponDetails[5];
		String finalAmt = sponDetails[6];
		
		float Aamount = Float.parseFloat(Amount);
		java.math.BigDecimal bdAmount = new BigDecimal(Aamount);
		bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
		
		float Famount = Float.parseFloat(finalAmt);
		java.math.BigDecimal bdFAmount = new BigDecimal(Famount);
		bdFAmount = bdFAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
			//String res[] = {sid,cmpName,desc,plan_id,planName,Amount,finalAmt,salesId};
	%>

  <tr>
  	<td>
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			  <tr>
                <td colspan="2" class="tblRowHead">Required fields are marked with an asterisk*</td>
			    </tr>
				  
				  <tr>
				  <td colspan="2" class="tblRowHead">
						Sponsorship & Sales Person Information					</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Company Name:</td>
					<td class="tableRight"><strong><%=cmpName%></strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Sponsorship Plan:</td>
					<td class="tableRight"><strong><%=planName%></strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Actual Cost:</td>
					<td class="tableRight"><strong> $<%=Aamount%></strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Final Cost:</td>
					<td class="tableRight"><strong> $<%=Famount%></strong></td>
				  </tr>
				  <tr>
					<td class="tableLeftTxtArea" valign="top">Comments/Instructions:</td>
					<td class="tableRight"><%=comments%></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblRowHead">
						Billing Schedule					</td>
				  </tr>
					<%
					Vector v = (Vector)session.getAttribute("scheduleVect");
					String Mid = "";
					String Mname = "";
					String Rid = "";
					String Recurringname = "";
					String SpecificBillid = "";
					String SpecificBillName = "";
					String manualAmt ="";
					String recurringAmt="";
					String specificBillingAmt="";
					if(v!=null){
					Enumeration e = v.elements();
						while(e.hasMoreElements()){
						String schedule[] = (String [])e.nextElement();
						String scheduleId =  schedule[0];
						String scheduleName =  schedule[1];
					 		if(scheduleName.equals("Manual")){
								 Mid = scheduleId;
								 Mname = scheduleName;
								 manualAmt = String.valueOf(Famount);
							}
							if(scheduleName.equals("Recurring")){
								 Rid = scheduleId;
								 Recurringname = scheduleName;
								 recurringAmt = String.valueOf(Famount);
							}
							if(scheduleName.equals("Specific Billing")){
								 SpecificBillid = scheduleId;
								 SpecificBillName = scheduleName;
								 specificBillingAmt = String.valueOf(Famount);
							}
					    }
						}
					%>
	    <form name="frmSponsBillingManual" method="post" action="Sponsorship.do" onsubmit="return frmBillingCheck();">
		<input type="hidden" name="pro" value="billingInsertion">
		<input type="hidden" name="totalAmt" value="<%=String.valueOf(Famount)%>">
		<input name="sid" value="<%=sid%>" type="hidden" />
				  <tr>
					<td class="tableSpan" colspan="2">
						<input type="radio" name="bs1"  value="Manual" size="10" onclick="manualClear();"/> 
					<%=Mname%> - I will bill manually.
					<input type="hidden" name="mid" value="<%=Mid%>">					</td>
				  </tr>
				  <tr>
				  <td class="tableSubSpan" colspan="2">

						<table border="0" cellpadding="0" align="center" cellspacing="0" class="hiddenLayout">
									  <tr>
										<td colspan="2" class="tblMainHead">Manual</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Amount:</td>
										<td class="tableRight">
<input type="text" name="manualAmount" class="textboxOne" value="<%=manualAmt%>" readonly="true" size="25" />
<span class="asterisk">*</span></td>
									  </tr>
									  <tr>
										<td class="tableLeft">Bill on Date:</td>
										<td class="tableRight"><input type="text" name="manualDate" readonly="true"  class="textboxOne" size="25" />
										<a href="javascript:cal1.popup();">
										<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
									  </tr>
							</table>					</td>
				  </tr>

				 <tr>
					<td class="tableSpan" colspan="2">
					<input type="radio" name="bs1" size="10" value="Recurring" onclick="reccuringClear();"/> 
					<%=Recurringname%> - I want to schedule billing based on the rules specified below.
					<input type="hidden" name="rid" value="<%=Rid%>">					</td>
				  </tr>
				   <tr>
					<td class="tableSubSpan" colspan="2">
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="hiddenLayout">
									  <tr>
										<td colspan="2" class="tblMainHead">
											Recurring Billing Schedule										</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Amount:</td>
										<td class="tableRight"><input type="text" name="recurringAmount"  value="<%=recurringAmt%>" readonly="true" class="textboxOne" size="25" />
									    <span class="asterisk">*</span></td>
									  </tr>
									  <tr>
										<td class="tableLeft">Frequency:</td>
										<td class="tableRight">
											<select name="recurringFrequency" id="recurringFrequency" class="selectboxOne">
											  <option selected="selected" value="">Select One</option>
											  <option value="12">Monthly</option>
											  <option value="4">Quarterly</option>
											  <option value="2">Half Yearly</option>
											  <option value="1">One Year</option>
										    </select>
											<span class="asterisk">*</span>											</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Bill on Date:</td>
										<td class="tableRight"><input type="text" name="recurringBillDate" readonly="true" class="textboxOne" size="25" />
										<a href="javascript:cal2.popup();">
										<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
									  </tr>
							</table>					</td>
				  </tr>
<!--				   specific billing schedule----------------------1
-->				  <tr>
					<td class="tableSpan" colspan="2">
					<input type="radio" name="bs1" size="10" value="Specific Billing"  onclick="specificClear();"/>
					 <%=SpecificBillName%> - I want to schedule billing based on the rules specified below.
					 <input type="hidden" name="spid" value="<%=SpecificBillid%>">					 </td>
				  </tr>
				  <tr>
					<td class="tableSubSpan" colspan="2">
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="hiddenLayout">
									  <tr>
										<td colspan="2" class="tblMainHead">
											Specific Billing Schedule
										1</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Amount:</td>
										<td class="tableRight"><input type="text" name="specificAmount1" value="" class="textboxOne" size="25" />
									    <span class="asterisk">*</span></td>
									  </tr>
									   <tr>
										<td class="tableLeft">Bill on Date:</td>
										<td class="tableRight"><input type="text" name="specificBillDate1" readonly="true" class="textboxOne" size="25" />
										<a href="javascript:cal3.popup();">
										<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
									  </tr>
							</table>					</td>
				  </tr>
<!--				  specific billing schedule----------------------2
-->				  <tr>
					<td class="tableSubSpan" colspan="2">
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="hiddenLayout">
									  <tr>
										<td colspan="2" class="tblMainHead">
											Specific Billing Schedule
										2</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Amount:</td>
										<td class="tableRight"><input type="text" name="specificAmount2" value="" class="textboxOne" size="25" />
									    <span class="asterisk">*</span></td>
									  </tr>
									   <tr>
										<td class="tableLeft">Bill on Date:</td>
										<td class="tableRight">
										<input type="text" name="specificBillDate2" readonly="true" class="textboxOne" size="25" />
										<a href="javascript:cal4.popup();">
										<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
									  </tr>
							</table>					</td>
				  </tr>
<!--				   specific billing schedule----------------------3
-->				   <tr>
					<td class="tableSubSpan" colspan="2">
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="hiddenLayout">
									  <tr>
										<td colspan="2" class="tblMainHead">
											Specific Billing Schedule
										3</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Amount:</td>
										<td class="tableRight"><input type="text" name="specificAmount3" value="" class="textboxOne" size="25" />
									    <span class="asterisk">*</span></td>
									  </tr>
									   <tr>
										<td class="tableLeft">Bill on Date:</td>
										<td class="tableRight"><input type="text" name="specificBillDate3" readonly="true" class="textboxOne" size="25" />
										<a href="javascript:cal5.popup();">
										<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
									  </tr>
							</table>					</td>
				  </tr>
				   <!--specific billing schedule----------------------4-->
				   <tr>
					<td class="tableSubSpan" colspan="2">
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="hiddenLayout">
									  <tr>
										<td colspan="2" class="tblMainHead">
											Specific Billing Schedule
										4</td>
									  </tr>
									  <tr>
										<td class="tableLeft">Amount:</td>
										<td class="tableRight"><input type="text" name="specificAmount4" value="" class="textboxOne" size="25" />
									    <span class="asterisk">*</span></td>
									  </tr>
									   <tr>
										<td class="tableLeft">Bill on Date:</td>
										<td class="tableRight">
										<input type="text" name="specificBillDate4" readonly="true" class="textboxOne" size="25" />
										<a href="javascript:cal6.popup();">
										<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
									  </tr>
							</table>					</td>
				  </tr>
				  <tr>
					<td class="alignCenter" height="30" colspan="2">
					<input type="submit" value="Send Billing Schedule" class="gradBtn" /></td>
				  </tr>
				  </form>
			</table>
	</td>
  </tr>
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmSponsBillingManual'].elements['manualDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;

	var cal2 = new calendar2(document.forms['frmSponsBillingManual'].elements['recurringBillDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;

	var cal3 = new calendar2(document.forms['frmSponsBillingManual'].elements['specificBillDate1']);
	cal3.year_scroll = true;
	cal3.time_comp = false;

	var cal4 = new calendar2(document.forms['frmSponsBillingManual'].elements['specificBillDate2']);
	cal4.year_scroll = true;
	cal4.time_comp = false;
	
	var cal5 = new calendar2(document.forms['frmSponsBillingManual'].elements['specificBillDate3']);
	cal5.year_scroll = true;
	cal5.time_comp = false;
	
	var cal6 = new calendar2(document.forms['frmSponsBillingManual'].elements['specificBillDate4']);
	cal6.year_scroll = true;
	cal6.time_comp = false;
</script>
