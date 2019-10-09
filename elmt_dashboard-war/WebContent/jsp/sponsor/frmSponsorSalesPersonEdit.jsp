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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcspnr.util.*"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

        <!--/*  Program Name    : frmSponsorSalesPersonEdit.jsp
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
        */
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Integrated Enterprise Dashboard</title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmSponsReg.js" type="text/javascript" ></script>
<script language="JavaScript" src="javascripts/calendar2.js" type="text/javascript"></script>

<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<body>
<%

String HLCSponsorDetails[] =(String [])session.getAttribute("editsalesPerson");
if(HLCSponsorDetails==null){
	response.sendRedirect("./SponsorRequestInsert.do?pro=p0");
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
		HLC Sponsorship: View A Sponsorship Request And Finalize It. 
	</td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	     
		
		<br/>
	</td>
  </tr>
  <tr>
  	<td>
	
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyy");
		String sid  = HLCSponsorDetails[0];
		String cmpName  = HLCSponsorDetails[1];
		String comments  = HLCSponsorDetails[2];
		String planName  = HLCSponsorDetails[4];
		String salePerson  = HLCSponsorDetails[5];
		String Amount  = HLCSponsorDetails[6];
		String finalCost  = HLCSponsorDetails[7];
		if(finalCost==null || finalCost.equals("")){
			finalCost = "0.00";
		}
		String startDate  = HLCSponsorDetails[9];
		
		String endDate  = HLCSponsorDetails[10];
		String  contractStartDate;
		String contractEndDate;
		
		  if(startDate.equals("")){
                    contractStartDate= null;
                }
                else{
					String startDateArr[] = startDate.split("-");
					String sDate = startDateArr[1] + "/" + startDateArr[2] + "/" + startDateArr[0];
                    //contractStartDate =(Date)sdf.parse(sDate);
					contractStartDate =sDate;
                }
                if(endDate.equals("")){
                    contractEndDate= "";
                }
                else{
					 String endDateArr[] = endDate.split("-");
					String eDate = endDateArr[1] + "/" + endDateArr[2] + "/" + endDateArr[0];
                    contractEndDate =eDate;
                }
	%>

		<form name="frmSponsReg"  method="post" action="./Sponsorship.do" onsubmit="return frmOnCheck();" >
		<input type="hidden" name="pro" value="salesentryAdd" />
		<input type="hidden" name="spoId" value=<%=sid%> />
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
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
					<td class="tableRight"><strong>$<%=Amount%></strong></td>
				  </tr>
				  <tr>
					<td class="tableLeftTxtArea" valign="top">Comments/Instructions:</td>
					<td class="tableRight">
					<%=comments%>					</td>
				  </tr>
				    <tr>
						<td colspan="2" class="tblMainHead">
							Sales Person Details						</td>
				   </tr>
				  <tr>
					<td class="tableLeft">Sales Person ID:</td>
					<td class="tableRight"><%=salePerson%></td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Final Cost Of Sponsorship:</td>
					<td class="tableRight"><input type="text" name="finalCost" class="textboxOne" size="25" value="<%=finalCost%>" /> <strong>$</strong>  <span class="asterisk">*</span></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Contract Start Date:</td>
					<td class="tableRight">
<input type="text" name="contractStartDate" class="textboxOne" readonly="true" id="contractStartDate" value="<%=contractStartDate%>" size="25" />
					<a href="javascript:cal1.popup();">
					<img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Contract End Date:</td>
					<td class="tableRight"><input type="text" readonly="true" name="contractEndDate" id="contractEndDate" class="textboxOne" value="<%=contractEndDate%>" size="25" />
					  <a href="javascript:cal2.popup();">
					<img src="images/calendar.jpg"   alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">				    </a> <span class="asterisk">*</span></td>
				  </tr>
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight"><input type="submit" value="Finalize" class="gradBtn" /></td>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmSponsReg'].elements['contractStartDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['frmSponsReg'].elements['contractEndDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>
