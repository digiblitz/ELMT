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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmGrossAmtMast.js" type="text/javascript" ></script>
<%
	String priceDetails[] = (String[])request.getAttribute("priceDetails");
	String priceDetId = priceDetails[0];
	String specId = priceDetails[1];
	String userTyId = priceDetails[2];
	String eventTypeId = priceDetails[3];
	
	String dueDatePrice = priceDetails[4];
	String afterDueDatePrice = priceDetails[5];
	String userTypeName = priceDetails[6];
	
	String specName = priceDetails[7];
	String eventName = priceDetails[8];
// {priceDetId,specId,userTyId,eventTypeId, dueDatePrice, afterDueDatePrice, userTypeName, specName, eventName};
%>




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
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">


  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">View Price Detail Master </span></td>
  </tr>
  <tr>
  	<td>
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">View Price Detail:</td>
				  </tr>
				   <tr>
				     <td class="tableLeft">Event Type :</td>
				     <td class="tableRight"><%=eventName%></td>
		     	  </tr>
				  <tr>
				     <td class="tableLeft">Specification:</td>
				     <td class="tableRight"><%=specName%></td>
		     	  </tr>

				  <tr>
				     <td class="tableLeft">User Type :</td>
				     <td class="tableRight"><%=userTypeName%></td>
		     	  </tr>
				  <tr>
					<td class="tableLeft">Normal Price :</td>
					<td class="tableRight"><strong>$<%=dueDatePrice%></strong></td>
				  </tr>
			      <tr>
					<td class="tableLeft">After Due Date Price :</td>
					<td class="tableRight">
					<strong>$<%=afterDueDatePrice%></strong></td>
				  </tr>

			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">&nbsp;
					<input type="button" value="Back" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
				  </tr>
			</table>
		
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
