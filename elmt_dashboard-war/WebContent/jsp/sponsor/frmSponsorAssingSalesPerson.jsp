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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

        <!--/*  Program Name    : frmSponsorAssingSalesPerson.jsp
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
        */-->


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmSponssalesPerson.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<body>
<%

String HLCSponsorDetails[] =(String [])session.getAttribute("assignsalesPerson");
if(HLCSponsorDetails==null){
	response.sendRedirect("Sponsorship.do?pro=p0");
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
		HLC Sponsorship: <span class="styleBoldTwo">View A Sponsorship Request And Assign A Sales Person.</span> 
	</td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	     
 <strong>To assign a sales person for this request, enter the sales person ID below.</strong></td>
  </tr>
  <tr>
  	<td>
	<%
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String sid  = HLCSponsorDetails[0];
		String cmpName  = HLCSponsorDetails[1];
		String comments  = HLCSponsorDetails[2];
		String planName  = HLCSponsorDetails[4];
		String Amount  = HLCSponsorDetails[5];
		String requestDate = HLCSponsorDetails[6];
		 
		float pAmt = Float.parseFloat(Amount);
		java.math.BigDecimal bdAmount = new BigDecimal(pAmt);
		bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
	%>
	
	
		
		<form name="frmAssignSalesPerson" method="post" action="Sponsorship.do" onsubmit="return frmSalesCheck();" >
		<input type="hidden" name="pro" value="assignSuccess" />
		<input type="hidden" name="spoassinedId" value=<%=sid%> />
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <%
				  String salespersonCodeErr = (String)request.getAttribute("err");
				  if(salespersonCodeErr!=null){
				  %>
				   <tr>
                <td colspan="2" class="tblRowHead">Required fields are marked with an asterisk*</td>
			    </tr>
				   <tr>
					<td colspan="2" class="asterisk">
						Sales Person ID Does not exist					</td>
				  </tr>
				  <%
				  }
				  %>
				  <tr>
					<td colspan="2" class="tblRowHead">
						Sponsorship Information & Assign a Sales Person					</td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Company Name:</td>
					<td class="tableRight"><strong><%=cmpName%>&nbsp;</strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Sponsorship Plan:</td>
					<td class="tableRight"><strong><%=planName%>&nbsp;</strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Date Of Request:</td>
					<td class="tableRight"><strong><%=requestDate%>&nbsp;</strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Actual Cost:</td>
					<td class="tableRight"><strong>$ <%=pAmt%>&nbsp;</strong></td>
				  </tr>
				  <tr>
					<td class="tableLeftTxtArea" valign="top">Comments/Instructions:</td>
					<td class="tableRight"><%=comments%>&nbsp;</td>
				  </tr>
				    <tr>
						<td colspan="2" class="tblMainHead">
							Assign a Sales Person						</td>
				   </tr>
				  <tr>
					<td class="tableLeft">Sales Person ID:</td>
					<td class="tableRight"><input type="text" name="salesPersonId" class="textboxOne" size="25" /> <span class="asterisk">*</span></td>
				  </tr>
				  
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight"><input type="submit" name="button" value="Assign Now" class="gradBtn" /></td>
				  </tr>
			</table>
			
		</form>
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
