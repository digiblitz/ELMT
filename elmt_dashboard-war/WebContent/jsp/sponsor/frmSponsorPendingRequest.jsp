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
<%@ page import="com.hlcspnr.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.math.*"%>
 

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

        <!--/*  Program Name    : frmSponsorPendingRequest.jsp
         *  Created Date    : September 4, 2006, 4:24 PM
         *  Author          : Punitha.R
         *  Version         : 1.4
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
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

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
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
		HLC Sponsorship Request Listings 
	</td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The sponsorship requests are as follows. <br />
	</td>
  </tr>


					<tr>
					<td>
					<table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
					<!--DWLayoutTable-->
					
					   <!--
				  <tr>
						<td height="25" colspan="7"  bgcolor="#ffffff" class="alignRight">
							<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
						  <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>							 </td>
				   </tr>-->
				  <tr>
			<td height="27" colspan="2" class="tblRowHeadTwo">Company name </td>
			<td width="120" class="tblRowHeadTwo">Plan Type </td>
			<td width="75" class="tblRowHeadTwo">Plan Amount </td>
			<td width="76" class="tblRowHeadTwo">Reject Sponsor </td>
			<td width="97" class="tblRowHeadTwo">Assign Sales Person </td>
		   </tr>
 <%
 
Vector recDetails = (Vector)session.getAttribute("PendingSponsorDetails");
if(recDetails!=null && recDetails.size()!=0){
 
						Enumeration e = recDetails.elements();
						while(e.hasMoreElements()){
							String res[] = (String []) e.nextElement();
							String sponsorId = res[0];
							String cmpName = res[1];
							String plan_name = res[3];
							String plan_amount = res[4];
							String date = res[5];
							float pAmt = Float.parseFloat(plan_amount);
							java.math.BigDecimal bdAmount = new BigDecimal(pAmt);
							bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
				  %>
				   <form name="frmSpoStatusPen" id="frmSpoStatusPen" method="post" action="Sponsorship.do">
				   <input type="hidden" name="pro" value="assignSalesPerson"/>
				   <input type="hidden" name="sid" value="<%=sponsorId%>" />
					<tr>
						<th height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><%=cmpName%></th>
						<td bgcolor="#E3E1D2" class="alignCenter"><%=plan_name%></td>
						<td bgcolor="#E3E1D2" class="alignCenter"><%=bdAmount%></td>
						<td bgcolor="#E3E1D2" class="alignCenter">
						<input type="submit" name="processVal" value="Reject" class="twoBtn" ></td>
						<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="processVal" value="Assign" class="oneBtn"  />		    </td>
					</tr>
				   </form>
		<% }
		
}
else{
%>
<tr>
<th height="26" class="alignCenter" colspan="6">No Records were Found</th>
</tr>
<%
}
%>
	  </table>
	</td>
</tr>  
</form>
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
