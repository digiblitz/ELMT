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
<%@ page import="com.hlccountry.mail.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
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
    <td colspan="2" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Country Mailing Price Master</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
		<strong>Delete Country Mailing Price.</strong> <br />
	</td>
  </tr>
  <tr>
  	<td>
		<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			
			<%
			HLCCountryMailPriceMaster obj = (HLCCountryMailPriceMaster) session.getAttribute("DisplayDeleteDetails");
				String countryMailTypeName =  obj.getCountryMailTypeName();
				String mailtypeId = obj.getCountryMailTypeId();
				String membertypeId = obj.getMembershipTypeId();
				String mailPrice = obj.getCountryMailPrice();
				String memberName =obj.getMemberShipName();	
				String year=obj.getYear();	
				// String  mDate = rs.getString(4);
               // String uTypeId = rs.getString(5);
/*			if (s!=null){
				countryTypeId= s[0];
				countryName= s[1];
				countryAmount= s[2];
				memtypeId=s[3];			*/
//			}
			%>
			
			<form name="frmCntryMailAmount" id="frmCntryMailAmount"  method="post" action="countrymail.do">
			<input name="countryProcess" type="hidden" value="deleteCountryPrice" />
			<input name="year" type="hidden" value="<%=year%>" />	  
			<input name="countryTypeId" type="hidden" value="<%=mailtypeId%>" />
				  <tr>
					<td colspan="2" class="tblRowHead">
						Delete Country Mailing Price</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Membership Year:</td>
					<td class="tableRight"><%=year%></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Membership Type  name:</td>
					<td class="tableRight"><%=memberName%></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Country Mail Price  name:</td>
					<td class="tableRight"><%=countryMailTypeName%></td>
				  </tr>
				  <tr>
					<td class="tableLeft"> Amount($):</td>
					<td class="tableRight"><%=mailPrice%></td>
				  </tr>
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight"><input  type="submit"  class="gradBtn" value="Confirm Delete" />&nbsp;&nbsp;&nbsp;
					<input  type="button"  class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>
				  </tr>
				 </form>
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