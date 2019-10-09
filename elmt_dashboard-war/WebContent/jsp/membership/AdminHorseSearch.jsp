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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlccommon.util.HLCHorseSearchVO" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/horseSearch.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>


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
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	<strong>Membership:</strong> <span class="styleBoldTwo">Non-Human Member Search/Registration Process</span>
	</td>
  </tr>
  
  <tr>
  <td><span class="floatLeft">
  Please search our Non-Human Member's database before registering your Non-Human Member to prevent duplicates and incomplete 
competition results history.</span>
<br/>
<span class="floatLeft">
The Name fields will match on whatever text is entered, so if you are unsure of spelling, try partial variations.</span>
<br/>
<span class="floatLeft">
The Non-Human Member ID field will only return an exact match.</span> 
  </td>  
  </tr>
 
<tr>
  	<td>
	
		<form name="frmCreateServType" id="frmCreateServType" method="post" action="SearchHorse.do" onSubmit="return 
validate(this);">
	  <input type="hidden" name="searchProcess" value="searchAdminResult"> 
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				 			  
				   <tr>
				  	<td colspan="2" id="search">
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							 <tr>
								<td colspan="2" class="tblRowHead">
									Search  Non-Human Member:				
				</td>
							 </tr>
							 
							 <tr>
									<td class="tableLeft">Non-Human Member Name:</td>
							   <td class="tableRight">
									<input type="text" name="horseSearchName" 
id="horseSearchName" class="textboxOne" size="20" /> 
								 (Competition Name)</td>
							  </tr>
							  
							  <tr>
									<td class="tableLeft">Non-Human Member Id :</td>
								<td class="tableRight">
									<input type="text" name="memberId" id="memberId" 
class="textboxOne" size="20" /></td>
							  </tr>
							   <tr>
									<td class="tableLeft">Owner First Name:</td>
								<td class="tableRight">
									<input type="text" name="ownerName" id="ownerName" 
class="textboxOne" size="20" /></td>
							  </tr>
							  
							   <tr>
									<td class="tableLeft">Owner Last Name:</td>
								<td class="tableRight">
									<input type="text" name="ownerLastName" 
id="ownerLastName" class="textboxOne" size="20" /></td>
							  </tr>

							   <tr>
									<td class="tableLeft">Rider First Name:</td>
								<td class="tableRight">
									<input type="text" name="riderName" id="riderName" 
class="textboxOne" size="20" /></td>
							  </tr>							   <tr>
									<td class="tableLeft">Rider Last Name:</td>
								<td class="tableRight">
									<input type="text" name="riderLastName" 
id="riderLastName" class="textboxOne" size="20" /></td>
							  </tr>
					  
						</table>
					</td>
				  </tr>
				  
				  
				  
				  
				  
				   <tr>
						<td class="tableLeft">&nbsp;</td>
						<td class="tableRight">
						<input type="submit" value="Submit" class="gradBtn" />
						</td>
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
