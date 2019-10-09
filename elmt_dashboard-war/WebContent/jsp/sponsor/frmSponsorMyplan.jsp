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
<%@ page import="java.sql.*"%>
	<%@ page import="java.math.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

       <!-- /*  Program Name    : frmSponsorMyplan.jsp
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
<!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
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
							HLC Sponsorship: <span class="styleBoldTwo">My Sponsorship Plans</span>	</td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp">
							
							<strong>Listed out are the sponsorship plans requested by you and it's status.</strong> <br /><br />
							You can also view your plans that were applied 
							and approved previously or rejected previously. <br />
						</td>
					  </tr>
					 
					 <tr>
						<td>
						  <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
									<%
									Vector myplan = (Vector)session.getAttribute("planDet");
									if(myplan!=null  && myplan!=0){
									%>
							<!--DWLayoutTable-->
							<tr>
							  <th width="125" height="20" valign="top" class="alignLeft" style="border-bottom:1px solid #999;">&nbsp;Color Legends: </th>
							  <td width="360" colspan="3" valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									  <tr>
										<!--
												<td width="50%"><span class="legendOne">&nbsp;</span> &nbsp;Pending.</td>
												-->
										<td width="50%"><span class="legendTwo">&nbsp;</span> &nbsp;View Details.</td>
									  </tr>
								  </table>
							  
							  </td>
							</tr>
							<tr>
							  <td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
							  <!--<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp; <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>--> </td>
							</tr>
							<!--
							<tr>
							  <th width="125" height="20" valign="top" class="alignLeft">&nbsp;My Plan Categories: </th>
							  <td width="360" colspan="3" valign="middle" class="alignLeft">
							  <select name="select" class="selectboxOne">
										<option selected="selected" >Current Plans</option>
										<option>Previous Plans</option>
										<option>Rejected Plans</option>
							  </select>
							  </td>
							</tr>
							-->
							
							<tr>
							  <td colspan="8" height="25" valign="top">
							  
							  <table border="0" cellpadding="3" align="center" cellspacing="1" class="tableInner">
								  <tr>
									<td height="27" colspan="2" class="tblRowHeadTwo">Plan Type </td>
									<td  width="130" class="tblRowHeadTwo">Plan Cost </td>
									<td width="101" class="tblRowHeadTwo">View Details </td>
								  </tr>
								  <%
											Enumeration it = myplan.elements();
											while (it.hasMoreElements()) {
											String[] s = (String[]) it.nextElement();
											String plan_id= s[0];
											String plan_name= s[1];
											String plan_desc= s[2];
											String plan_amt=s[3];
											
											float Aamount = Float.parseFloat(plan_amt);
											java.math.BigDecimal bdAmount = new BigDecimal(Aamount);
											bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
								  %>
								  <form id="frmSponsorMyPlan" name="frmSponsorMyPlan" method="post" action="./Sponsor.do">
								  <input type="hidden" value="planDet" name="usrprocess">
								  <input type="hidden" value="<%=plan_id%>" name="planId">
								  <tr>
								  
									<th height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><%=plan_name%></th>
									<td bgcolor="#E3E1D2" class="alignCenter">$<%=bdAmount%></td>
									<td bgcolor="#E3E1D2" class="alignCenter">
									<input type="submit" name="buttonView" value="View Details" class="oneBtn" />	
									</td>
								  </tr>
								  </form>
								   <%}	%>
										<%}	
										else {
										%>
										<tr>
										<th height="26" class="alignCenter" colspan="4">No Plans are available.</th>
										</tr>
									<%}%>
							  </table></td>
							</tr>
							<tr>
							  <td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
							  <!--<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp; <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>--> </td>
							</tr>
							<tr>
							  <td height="19" colspan="4">&nbsp;</td>
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
