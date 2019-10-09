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

/<!--*  Program Name    : frmSponsorActiveRequest.jsp
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
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
		HLC Sponsorship: Assign Billing Schedules To Finalized Requests	</td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The finalized sponsorship requests are as follows. <br />
		<br />
	    To assign a billing schedule to a finalized sponsorship request, click on the <strong>'Assign'</strong> button. <br />
	</td>
  </tr>
  
 <tr>
 	<td valign="top">
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		   <!--DWLayoutTable-->
		  
		  
		  <tr>
			<td height="27" colspan="2" class="tblRowHeadTwo">Company name </td>
			<td width="93" class="tblRowHeadTwo">Plan Type </td>
			<td width="88" class="tblRowHeadTwo">Actual Cost($) </td>
			<td width="86" class="tblRowHeadTwo">Final Cost ($) </td>
			<!-- <td width="61" class="tblRowHeadTwo">Edit</td> -->
			<td width="97" class="tblRowHeadTwo">View/Assign Billing Schedule </td>
		   </tr>
<%
 Vector actDetails = (Vector)session.getAttribute("activeSponsorDetails");
 if(actDetails!=null && actDetails.size()!=0){
  %>  
		  		<%
				 	Enumeration e = actDetails.elements();
						while(e.hasMoreElements()){
							String resActive[] = (String []) e.nextElement();
							String sponsorId = resActive[0];
							String cmpName = resActive[1];
							String plan_name = resActive[3];
							String plan_amount = resActive[4];
							String final_amount = resActive[5];
							String salesPersonId = resActive[6];
							float pAmount = 0;
							float fAmount = 0;
							if(plan_amount!=null){
								pAmount = Float.parseFloat(plan_amount);
							}
							if(final_amount!=null){
								fAmount = Float.parseFloat(final_amount);
							}
			 %>
				 <tr>
				<form name="frmSpoBillingAssign" id="frmSpoBillingAssign" method="post" action="./Sponsorship.do">
				<input type="hidden" name="pro" value="billSchedule"/>
				<input type="hidden" name="sponsorId" value="<%=sponsorId%>" />	   
					<th height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><%=cmpName%></th>
					<td bgcolor="#E3E1D2" class="alignCenter"><%=plan_name%></td>
					<td bgcolor="#E3E1D2" class="alignCenter"><%=pAmount%></td>
					<td bgcolor="#E3E1D2" class="alignCenter"><%=fAmount%></td>
					<!--<td bgcolor="#E3E1D2" class="alignCenter">
					<input type="button" name="btnEdit" value="Edit" class="twoBtn" onclick="location.href='./Sponsorship.do?pro=sPe&sid=<%=sponsorId%>'" /></td>
					-->
					<input type="hidden" name="finalAmt" value="<%=final_amount%>">
					<td bgcolor="#E3E1D2" class="alignCenter">
					 <input type="submit" name="Submit" value="Schedule" class="twoBtn" />
					</td>
					</form>
					</tr>
			  <%}		   
		  }
		  
		  else{
		  %>
		  <tr>
		  	<td bgcolor="#E3E1D2" colspan="6" class="alignCenter"><strong>No Records were Found</strong></td>
		  </tr>
		  
		  <%
		  }
		  %>
		  
		  <tr>
			<td height="19" colspan="7">&nbsp;</td>
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
