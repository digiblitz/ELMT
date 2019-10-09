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
<%@ page import="com.hlcmrm.util.HLCPublicationVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmCntryMailAmount.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script language="javascript">

function myvalidate(){

//-------------------------------------Country Mail Type name------------------------------------------------------------

if((document.frmPubliMasterEdit.publicationName.value=="")||(document.frmPubliMasterEdit.publicationName.value.indexOf(' ')==0))
   {alert("Publication Type name cannot be empty ");
     document.frmPubliMasterEdit.publicationName.focus();
    return false; 
	}
	
	if(document.frmPubliMasterEdit.publicationName.value.length >80 ) {
	alert("Publication Type name exceeds the maximum of 80 characters");
  document.frmPubliMasterEdit.publicationName.focus();
 return false;
 }
 
    return true;
    }

</script>

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
	Membership:<span class="styleBoldTwo">Publication Type</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	    <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->
		<strong>Fill in the following information to assign an amount for a given Publication Type.</strong> <br />
	</td>
  </tr>
 
  <tr>
  	<td><table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			  <tr>
					<td colspan="2" class="tblRowHead">
						Edit Publication Type </td>
				  </tr>
			
			  <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2"  height="20" class="styleError"><strong>Publication Type Name Already Exists. Try Again.</strong></td>
					</tr>
					<%
					}
					%>
			<form name="frmPubliMasterEdit"  method="post" action="Publication.do" onSubmit="return myvalidate();">
			<input name="process" type="hidden" value="updatePub" />	 
			<%
		  			HLCPublicationVO donObj = (HLCPublicationVO)request.getAttribute("Vobj");
		   
					String publicationId = donObj.getPublicationId();
					String publicationName = donObj.getPublicationName();
					 
			%> 
			<input name="publicationId" type="hidden" value="<%=publicationId%>" />
				 
				  
				 <tr>
											<td colspan="2" class="tableSpan">&nbsp;Fields marked with an asterisk (<span class="asterisk">*</span>)  are required.</td>
									  </tr>
				  <tr>
					<td class="tableLeft">Publication Type  name:</td>
					<td class="tableRight"><input name="publicationName" type="text" value="<%=publicationName%>" class="textboxOne" /><span class="asterisk">*</span></td>
				  </tr>
<!--				  <tr>
					<td class="tableLeft"> Amount($):</td>
					<td class="tableRight"><input name="DonationAmount" type="text" value="< %=donationPrice%>" class="textboxOne" /></td>
				  </tr>	-->
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input  type="submit"  class="gradBtn" value="Update"/>&nbsp;&nbsp;&nbsp;
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