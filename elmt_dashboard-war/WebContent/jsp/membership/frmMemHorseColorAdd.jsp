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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlcmrm.util.HLCHorseColorVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<script language="javascript">
function myvalidate()
{

if((document.frmHorseAdd.horseDesc.value=="")||(document.frmHorseAdd.horseDesc.value.indexOf(' ')==0)){
		alert('Horse Description cannot be empty');
		document.frmHorseAdd.horseDesc.focus();
		return false;
	}
	if(document.frmHorseAdd.horseDesc.value.indexOf('  ')!==-1){
		alert("Enter valid Horse Description");
		document.frmHorseAdd.horseDesc.focus();
		return false;
	}
	/*
	if((document.frmHorseAdd.horseDesc.value.indexOf(' ')==0)||(document.frmHorseAdd.horseDesc.value=="")){
		alert('Horse Description cannot be empty');
		document.frmHorseAdd.horseDesc.focus();
		return false;
	}*/
	return true;
}
</script>
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
	Membership: <span class="styleBoldTwo">Horse Color Master</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><strong>Fill in the following information.</strong> <br />
	</td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmHorseAdd" id="frmHorseAdd" method="post" action="HorseColorDetails.do" onsubmit="return myvalidate();">
		<input type="hidden" name="horseprocess" value="insert" />
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					  <td colspan="2">Fields marked with an asterisk (*) are required.</td>
					  </tr>
			
				  <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					
					<tr>
					<td colspan="2" class="styleError"><strong>Horse Color Already Exists. Try  Again.</strong></td>
					</tr>
					<%
					}
					%>
				  <tr>
					<!--<td class="tableLeft">Horse  Color:</td>
					<td class="tableRight"><input name="horseCode" type="text" class="textboxOne" /><span class="asterisk">*</span></td>-->
				  </tr>

				  <tr>
					<td class="tableLeft">Horse  Color Description:</td>
					<td class="tableRight"><input name="horseDesc" type="text" class="textboxOne" maxlength="120" />
					<span class="asterisk">*</span></td>
				  </tr>
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight"><input type="submit" class="gradBtn" value="Add" />&nbsp;&nbsp;
					<input name="Reset"  type="reset" class="gradBtn" value="Reset"/></td>
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
