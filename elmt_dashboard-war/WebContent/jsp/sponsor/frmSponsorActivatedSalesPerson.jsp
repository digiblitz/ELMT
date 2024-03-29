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

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmSponsorActivatedSalesPerson.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.3
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
		HLC Sponsorship: <span class="styleBoldTwo"> Activated Sales Person</span></td>
  </tr>
  
 <tr>
 	<td valign="top">
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		   <!--DWLayoutTable-->
		  <tr>
		  	<th width="99" height="20" valign="top" class="alignLeft">Sales Person</th>
			<td colspan="6" valign="middle" class="alignLeft">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td width="50%">
					<select name="selMediaType" onchange="postIssueData(this.value);">
					<option selected="selected" value="">Select One</option>
					<%
					Vector mediaType = (Vector)session.getAttribute("objSesAdvMediaIssue");
					if(mediaType!=null && mediaType.size()!=0){
						Enumeration e = mediaType.elements();
						String mediaId = "";
						String mediaName = "";
						String mediaDesc  = "";
						String mediaStatus = "";
						while(e.hasMoreElements()){
							String[] s = (String [])e.nextElement();
							mediaId = s[0];
							//	out.print("mediaId" + mediaId);
							mediaName = s[1];
							mediaDesc  = s[2];
							mediaStatus = s[3];
							if(mediaId.equals(mId)){
								%>
								<option value="<%=mediaId%>" selected="selected"><%=mediaName%></option>
								<%
								}
								else{
								%>
								<option value="<%=mediaId%>"><%=mediaName%></option>
								<%		
							}
						}
					}%>
					</select>
						</td>
				      </tr>
					</table></td>
		   </tr>
		  <tr>
		  	<td height="25" colspan="7"  bgcolor="#ffffff" class="alignRight">
				<!--<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
                <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>--></td>
		   </tr>
		  <tr>
			<td height="27" colspan="2" class="tblRowHeadTwo">SalesPerson Id  </td>
			<td width="138" class="tblRowHeadTwo">Pending Request </td>
			<td width="107" class="tblRowHeadTwo">Active Request </td>
			<!-- <td width="61" class="tblRowHeadTwo">Edit</td> -->
			<td width="87" class="tblRowHeadTwo">Payment Details  </td>
		   </tr>
<%
 Vector actDetails = (Vector)session.getAttribute("activeSponsorDetails");
 if(actDetails!=null ){
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
			 %>
				 <tr>
				<form name="frmSpoBillingAssign" id="frmSpoBillingAssign" method="post" action="./Sponsorship.do">
				<input type="hidden" name="pro" value="billSchedule"/>
				<input type="hidden" name="sponsorId" value="<%=sponsorId%>" />	   
					<th height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><%=cmpName%></th>
					<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit3" value="Pending" class="twoBtn" /></td>
					<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit2" value="Active" class="oneBtn" /></td>
					<!--<td bgcolor="#E3E1D2" class="alignCenter">
					<input type="button" name="btnEdit" value="Edit" class="twoBtn" onclick="location.href='./Sponsorship.do?pro=sPe&sid=<%=sponsorId%>'" /></td>
					-->
					<input type="hidden" name="finalAmt" value="<%=final_amount%>">
					<td bgcolor="#E3E1D2" class="alignCenter">
					 <input type="submit" name="Submit" value="View Payments" class="welcome" />
					</td>
					</form>
					</tr>
			  <%}		   
		  }%>
		  <tr>
		  	<td height="25" colspan="7"  bgcolor="#ffffff" class="alignRight">
				<!--<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>-->			</td>
		   </tr>
		  <tr>
			<td height="19" colspan="7">&nbsp;</td>
           </tr>
		   <!--
		   <bean:define id="listPlans" property="lstPlans"/>
		   <table>
		   <logic:iterate id="planRow" property="listPlans" indexed="true">
		   	<tr>
				<td>
				<html:text property="companyName" disabled="true" readonly="true" index="true"/>		   
				</td>
				<td>
				<html:text property="planName" disabled="true" readonly="true"/>		   
				</td>
			</tr>	
		   </logic:iterate>
		   </table>
		   -->
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
