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
<%-- 
    Document   : frmMeeEndorseCardSuccess
    Created on : Dec 15, 2009, 5:30:13 PM
    Author     : admin
--%>

<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="membership.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
                    
                    <%
                 String amt = (String)request.getAttribute("amount");
				String eveId = (String)request.getAttribute("eventId");
				String eveTitle = (String)request.getAttribute("eveTitle");
				
                    %>
                    
                    
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		USEA Event Registration: <span class="styleBoldTwo">Confirmation.	</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp" valign="top" style="padding:8px;">
	<form name="confirmation" method="post" action="MemberLogin.do" />
	<p>
	  <%
	
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DATE);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		month=month+1;
		String date = month+"-"+day+"-"+year;
		
	%>
	  
	  You have successfully made a payment of $ (<%=amt%>)  (U.S. Dollars) and registered for the Event. Event details are as follows:<br />
	  <br />
	  <strong>USEA Event ID #.:</strong> <span class="styleBoldOne"><%=eveId%></span> <br />
	  <strong>Event Title :</strong> <span class="styleBoldOne"> <%=eveTitle%> </span><br />
	  
	  
	  
	  <strong>Registration Date:</strong> <span class="styleBoldOne"> <%=date%> </span> <br />
	      <strong>Registration Status: </strong><span class="styleBoldOne">Pending until signed USEA Event Registration Application received.
	        </span><br />
	  
	      <br />
	  Click <strong>Print Event Reg App</strong> button below to save and print a copy of the USEA Event Registration - USEF Endorsed Competition/Management Application. This document MUST be printed, signed and returned to USEA to finalize event registration.</p>
	<p><br />
	  
	  <input name="button" type="button" class="gradBtn" value="Print Event Reg App" style="width:125px;" onclick="window.open('http://reports.useventing.com/ReportServer?/Public/USEF_endorsed_app&rs:Command=Render&rs:format=PDF&EVENTID=<%=eveId%>')" />
	  
	  <input name="button" type="button" class="gradBtn" value="Update Omnibus Listing" style="width:145px;" onclick="location.href='OrganizerUSEAEventReg.do?process=initUpdate&eventId=<%=eveId%>'"/>
	  
	  <input name="button" type="button" class="gradBtn" value="View My Events" style="width:100px;" onclick="location.href='EventOrgRenewal.do?eventProcess=eventViewOrg'" />	
	  <br />
	  </form>
	  </p></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>

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



