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
<%@ page import="com.hlcmee.ann.HLCAnnualMeetingStore"%>
<%@ page import="com.hlcmeeting.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlccommon.util.HLCPaymentDetailVO"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!--<link href="../css/core-ie.css" type="text/css" rel="stylesheet" />-->
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->
<%
    HLCPaymentDetailVO objPayDet = new HLCPaymentDetailVO();
    objPayDet=(HLCPaymentDetailVO)session.getAttribute("objPayDet");
%>
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Credit Card: <span class="styleBoldTwo">Transaction Declined </span></td>
				  </tr>
				  <tr>
					<td width="34" class="tblDescrp"><img src="images/warning.gif" class="floatLeft" /></td>
				    <td width="466" class="tblDescrp"><span class="labelTabHead">Your transaction has been DECLINED!</span></td>
				  </tr>
				   <tr>
				     <td colspan="2" class="tblDescrp">&nbsp;</td>
			      </tr>
				   <tr>
				     <td height="25" colspan="2" class="tblDescrp" style="border-bottom:1px solid #ccc;"><span class="styleBoldTwo">An Error Occurred</span></td>
			      </tr>
				   <tr>
				     <td colspan="2" class="tblDescrp" style="border-bottom:1px solid #ccc;"><strong>Number:</strong> 1</td>
			      </tr>
				   <tr>
				     <td colspan="2" class="tblDescrp" style="border-bottom:1px solid #ccc;">
					 <strong>Message:</strong> This transaction request has not been approved. You may  contact customer service for additional options.					 </td>
			       </tr>
				   <tr>
					  <td height="40" colspan="2" class="tblDescrp">
					  <%
                                            session.setAttribute("amt",String.valueOf(objPayDet.getAmount()));
                                          %>					 
					  <input name="submit2" type="button" value="Retry Payment" class="gradBtn" onclick="location.href='./AdminMembPayment.do?process=disp&usr=user&pid=<%=objPayDet.getPaymentId()%>';" />
					 		
							<input name="button" type="button" class="gradBtn" value="Home" onclick="location.href='./index.jsp';" />			
											
					  </td>
				  </tr>
				</table>
                        <%
                            session.setAttribute("objPayDet",null);
                        %>
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
