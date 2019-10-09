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

        <!--/*  Program Name    : frmSponsorOwnPlanList.jsp
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
<script src="javascripts/frmSponsReg.js" type="text/javascript" ></script>
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
		HLC Sponsorship: <span class="styleBoldTwo">Available  Plans</span>	</td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The sponsorship plans available with HLC are as follows: <br />
	</td>
  </tr>
 
 <tr>
 	<td>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		   <!--DWLayoutTable-->
		  
		 
		    <%
			String deleteStatus = (String)request.getAttribute("errStat");
				if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
				%>
				<tr>
				<td class="styleError" colspan="4">Cannot delete this record. its already in use</td>
				</tr>
				<%
			}
			%> 
				 
		   <tr>
		   	 <td colspan="8">
			    <table border="0" cellpadding="3" align="center" cellspacing="1" class="tableInner">
				  <tr>
					<td height="27" colspan="2" class="tblRowHeadTwo">Plan Type </td>
					<td  width="119" class="tblRowHeadTwo">Plan Cost <span class="alignCenter">$</span></td>
					<td width="122" class="tblRowHeadTwo">View Details </td>
			      </tr>
					<% 
						Vector v = (Vector)session.getAttribute("showAllPlanDet");
						if(v!=null  && v.size()!=0){
				 
						Enumeration it = v.elements();
						while (it.hasMoreElements()) {
						String[] s = (String[]) it.nextElement();
						String plan_id= s[0];
						String plan_name= s[1];
						String plan_desc= s[2];
						String plan_amt=s[3];
					%>
				  
				  <tr>
				  <form name="frmSponsPlan" id="frmSponsPlan" action="Sponsor.do" >
				  <input type="hidden" value="manupPlan" name="usrprocess">
					<input type="hidden" value="<%=plan_id%>" name="planId">
					<th height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><%=plan_name%></th>
					<td bgcolor="#E3E1D2" class="alignCenter"><%=plan_amt%></td>
					<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="buttonView" value="View Details" class="oneBtn" /></td>
				 </form>
				 </tr>
				<%}	
					 }
					else {
					%>
					<tr>
					  <th height="25" colspan="4">No records are available. </th>
					</tr>
					<%}%>
			 </table>
		 	</td>
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


�

</body>
</html>