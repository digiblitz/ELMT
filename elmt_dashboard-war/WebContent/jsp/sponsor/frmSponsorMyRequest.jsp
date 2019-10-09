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
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

       <!-- /*  Program Name    : frmSponsorMyRequest.jsp
         *  Created Date    : September 4, 2006, 4:24 PM
         *  Author          : Punitha.R
         *  Version         : 1.5
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
		HLC Sponsorship: </td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	   <strong>List of Sponsorship Request:	</strong>	<br />
	</td>
  </tr>
  
 <tr>
 	<td valign="top">
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">

<!--
<tr>
<td height="25" colspan="6"  bgcolor="#ffffff" class="alignRight">
	<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
	<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a></td>
</tr>
-->
		  <tr>
		    <td width="99" class="tblRowHeadTwo">Company Name </td>
			<td width="99" height="27" class="tblRowHeadTwo">Plan Name</td>
			<td width="158" class="tblRowHeadTwo">Request Status </td>
			<td width="87" class="tblRowHeadTwo">Requested Date </td>
			<td width="87" class="tblRowHeadTwo">View Contract Form </td>
			<td width="87" class="tblRowHeadTwo">View Payment Schedule </td>
		   </tr>
			<%
			Vector mySponsDet = (Vector)session.getAttribute("mysponsordetails");
			
			//out.print("mySponsDet:" + mySponsDet);
			if(mySponsDet!=null && mySponsDet.size()!=0){
				Enumeration e = mySponsDet.elements();
				while(e.hasMoreElements()){
				String reqMySpons[] = (String []) e.nextElement();
				String sponsorId = reqMySpons[0];
				String cmpName = reqMySpons[1];
				String plan_name = reqMySpons[3];
				String plan_amount = reqMySpons[4];
				String final_amount = reqMySpons[5];
				String salesPersonId = reqMySpons[6];
				String ReqDate = reqMySpons[7];
				String requestStatus = reqMySpons[8];
				String filePath = reqMySpons[9];
			
				
			 %>
			 	<form name="viewPayment" method="post" action="Sponsorship.do">
					<input type="hidden" name="pro" value="usersh" />
					<input type="hidden" name="sponsorId" value="<%=sponsorId%>" />
					 <tr>
					   <th bgcolor="#E3E1D2" class="alignCenter"><%=cmpName%></th>
						<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=plan_name%></th>
						<td bgcolor="#E3E1D2" class="alignCenter"><%=requestStatus%></td>
						<td bgcolor="#E3E1D2" class="alignCenter"><%=ReqDate%></td>
						<%if(requestStatus.equals("Pending") ||  requestStatus.equals("Rejected")){%>
						<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" class="oneBtn"  value="NA" /></td>
						<td bgcolor="#E3E1D2" class="alignCenter"><input type="button"  value="NA" class="twoBtn"/></td>
						<%
						}else{%>
						<td bgcolor="#E3E1D2" class="alignCenter"><input name="button" type="button" class="oneBtn"  value="View" onClick="javascript:location.href('Sponsorship.do?pro=uplView&fPath=<%=filePath%>')" /></td>
						<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit"  value="View Schedule" class="twoBtn"/></td>
						<%}
						%>
					</tr>
				</form>
			  <%
			 }
		  }
		  else
		  {
		  %>
			<tr>
				<th height="26" colspan="8" class="alignCenter">No Records were Found</th>
			</tr>
		  <%
		  }
		  %>
		  <tr>
			<td height="19" colspan="8">&nbsp;</td>
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
