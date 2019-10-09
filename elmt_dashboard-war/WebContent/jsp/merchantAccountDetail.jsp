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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script language="javascript">
function myvalidate()
{
	if((document.frmMerchantAccnt.merchId.value=="")||(document.frmMerchantAccnt.merchId.value.indexOf(' ')!=-1))
	{
		alert('Merchant Id cannot be empty');
		document.frmMerchantAccnt.merchId.value="";
		document.frmMerchantAccnt.merchId.focus();
		return false;
	}
	if((document.frmMerchantAccnt.userId.value=="")||(document.frmMerchantAccnt.userId.value.indexOf(' ')!=-1))
	{
		alert('User Id cannot be empty');
		document.frmMerchantAccnt.userId.value="";
		document.frmMerchantAccnt.userId.focus();
		return false;		
	}
	if((document.frmMerchantAccnt.mercPinNo.value=="")||(document.frmMerchantAccnt.mercPinNo.value.indexOf(' ')!=-1))
	{
		alert('Merchant pin number cannot be empty');
		document.frmMerchantAccnt.mercPinNo.value="";
		document.frmMerchantAccnt.mercPinNo.focus();
		return false;		
	}
	return true;	
}
</script>
<!--<link href="../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
<!--		< %@ include file = "../../include/login_header.jsp" %>-->
			<%@ include file = "../../include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Merchant Account: <span class="styleBoldTwo">Merchat Account Details </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">&nbsp;</td>
				  </tr>
						  <tr>
							<td>
<%         String[] value = (String[]) session.getAttribute("value");
			  String merchantId = value[0];
              String merchantLoginId = value[1];
              String merchantUserId = value[2];
              String merchantPin = value[3];
%>
							<form name="frmMerchantAccnt" id="frmMerchantAccnt" method="post" action="Merchantdetails.do" onsubmit="return myvalidate();">		  
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								  <tr>
									<td colspan="2" class="tblRowHead"> Merchant Account Details </td>
								  </tr>
								  <tr>
									<td class="tableLeft">Merchant ID:</td>
									<td class="tableRight"> <input type="text" name="merchId" id="merch_id" class="textboxOne"  size="20" value="<%=merchantLoginId%>"/>
									  <span class="asterisk">*</span></td>
								  </tr>
		
								  <input type="hidden" name="process" value="update" />
									 <input type="hidden" name="mId" value="<%=merchantId%>" />
								  <tr class="tableInner">
									<td class="tableLeft">User ID:</td>
									<td class="tableRight"><input type="text" name="userId" id="user_id" class="textboxOne" size="20" value="<%=merchantUserId%>" />
										<span class="asterisk">*</span> </td>
								  </tr>
								 
								 <tr class="tableInner">
									<td class="tableLeft">Merchant Pin Number:</td>
									<td class="tableRight"><input type="text" name="mercPinNo" id="mercPinNoId" class="textboxOne" size="20" value="<%=merchantPin%>"/>
										<span class="asterisk">*</span> </td>
								  </tr>
								<tr>
									<td colspan="2" class="alignCenter">
									<input name="submit" type="submit" value="Update" class="gradBtn" />
								    <input name="submit" type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);"/>
									</td>
							   </tr>				 
								<tr>
									<td colspan="2" height="25" class="alignCenter">&nbsp;</td>
							   </tr>
							</table>
						</form>
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
