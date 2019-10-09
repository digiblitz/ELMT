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
<%@ page language="java"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.math.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hlcspnr.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmSponsorAfterBilling.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.7
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
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<body>
<%
 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
String HLCSponsorDetails[] =(String [])session.getAttribute("spnrStatusFinalView");
if(HLCSponsorDetails==null){
	response.sendRedirect("sponsor.jsp");
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
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
		HLC Sponsorship: Assign Billing 	</td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    <br />
	    <br />		<br />
	</td>
  </tr>
  <tr>
  <td>
  <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
 <tr>
 <%
		String sid  = HLCSponsorDetails[0];
		String cmpName  = HLCSponsorDetails[1];
		String comments  = HLCSponsorDetails[2];
		String planName  = HLCSponsorDetails[4];
		String Amount  = HLCSponsorDetails[5];
		String finalAmt = HLCSponsorDetails[6];
		
			float Amt = Float.parseFloat(Amount);
			java.math.BigDecimal amount = new BigDecimal(Amt);
			amount = amount.setScale(2,BigDecimal.ROUND_HALF_UP);
			
			float fAmt = Float.parseFloat(finalAmt);
			java.math.BigDecimal flAmount = new BigDecimal(fAmt);
			flAmount = flAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
	%>
					<td  colspan="5" class="tblRowHead">
							Sponsorship & Sales Person Information
					</td>
				  </tr>
				  <tr>
					<td colspan="4" class="tableLeft">Company Name:</td>
					<td class="tableRight"><strong><%=cmpName%></strong></td>
				  </tr>
				  <tr>
					<td colspan="4" class="tableLeft">Sponsorship Plan:</td>
					<td class="tableRight"><strong><%=planName%></strong></td>
				  </tr>
				  <tr>
					<td colspan="4" class="tableLeft">Actual Cost:</td>
					<td class="tableRight"><strong>$<%=amount%></strong></td>
				  </tr>
				  <tr>
					<td colspan="4" class="tableLeft">Final Cost:</td>
					<td class="tableRight"><strong>$<%=flAmount%></strong></td>
				  </tr>
				  <tr>
					<td colspan="4" class="tableLeftTxtArea" valign="top">Comments/Instructions:</td>
					<td class="tableRight"><%=comments%></td>
 </tr>
 </table>
 </td>
 </tr>

 <tr>
 	<td>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		   <!--DWLayoutTable-->
	<% Vector v1= (Vector)session.getAttribute("finalview");
		if(v1!=null && v1.size()!=0){%>
		 
		  <tr>
			<td width="90"class="tblRowHeadTwo">Installation Period </td>
			<td width="80"  class="tblRowHeadTwo"> Due Amount </td>
			<td width="65" class="tblRowHeadTwo">Due Date  </td>
			<td width="65" class="tblRowHeadTwo">Payment Status</td>
		   </tr>
	 <%
		
		String scheduleId ="";
		String sponsorId ="";
		String scheduleTypeId ="";
		//Date dueDate = null;
		String dueDate = "";
		float dueAmount = 0;
		java.math.BigDecimal bd = new BigDecimal(dueAmount);
    	
		//String dueAmount = "";
		//boolean continueStatus = ((Boolean)e.nextElement()).booleanValue();
		String continueStatus = "";
		String frequencyRate = "";
		String paymentStatus ="";
		Enumeration e = v1.elements();
		int i=1;
		  while(e.hasMoreElements()){
			ArrayList a = (ArrayList) e.nextElement();
			Iterator it = a.iterator();
			 if(it.hasNext()){
				scheduleId = (String)it.next();
				sponsorId =  (String)it.next();
				scheduleTypeId = (String)it.next();
				if(dueDate!=null){
					dueDate =  sdf.format(it.next());}
					//dueAmount =  ((Float)it.next()).floatValue();
					dueAmount = ((Float)it.next()).floatValue();
					continueStatus = (String)it.next();
					frequencyRate = (String)it.next();
					paymentStatus =(String)it.next();
					bd = new BigDecimal(dueAmount);
					bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
				}
			%>
			  <tr>
				<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=i++%></th>
				<td bgcolor="#E3E1D2" class="alignCenter"><%=bd%></td>
				<td bgcolor="#E3E1D2" class="alignCenter"><%=dueDate%></td>
				<td bgcolor="#E3E1D2" class="alignCenter"><%=paymentStatus%></td>
			   </tr>
		   <%
		   
		}
  	}
	
	else{
	%>
	<tr>
		<th height="26" colspan="4" bgcolor="#E3E1D2" class="alignCenter">No Records were Found</th>
   </tr>
	
	<%
	}
 %>
  <tr>
	<td height="19" colspan="6">&nbsp;</td>
   </tr>
  </table>
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
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
</body>
</html>