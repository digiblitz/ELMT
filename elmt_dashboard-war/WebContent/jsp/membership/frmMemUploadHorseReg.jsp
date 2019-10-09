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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript" ></script>
<script src="javascripts/UploadHorseReg.js" type="text/javascript" ></script>

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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				<tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Membership: <span class="styleBoldTwo">Horse
					    Regisration Details.</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><br />
						
					You can upload your competition registration excel files here
					  . </td>
				  </tr>
				<tr>
				<td>
			
		 <html:form method="post" action="/horseRegExcelData" enctype="multipart/form-data" onsubmit="return myValidate();">
         
				<input type="hidden" name="process" value="upload" />
				
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
								 <tr>
									 <td class="tableLeft">Event Id:</td>
									 <td class="tableRight">
									    <input type="text" name="eventId" id="eventId" class="textboxOne" size="18" />									 </td>
								  </tr>
								  
								  <tr>
									 <td class="tableLeft">Horse Registration  File:</td>
									 <td class="tableRight">
										<html:file  property="fileUpload" styleClass="textboxOne" size="18" />	
										<span class="asterisk">*</span>									 </td>
								  </tr>
								  <tr>
									<td class="tableLeft">&nbsp;</td>
									<td class="tableRight">
									  <input type="submit" value="Upload" class="gradBtn"/>									</td>
								 </tr>						  
					</table>
					
			  </html:form> 
			  
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
