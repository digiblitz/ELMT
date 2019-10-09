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
<%@ page import="com.hlccommon.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
					  <!--<tr>
						<td colspan="2" class="tblMainHead">
							<strong>HLC</strong> Meetings:  <span class="styleBoldTwo">Membership Registration Confirmation</span></td>
					  </tr>-->
					<%
					String eventId = (String)request.getAttribute("eventId");
					String paymentId = (String)request.getAttribute("paymentId");
					String amt=(String)request.getAttribute("price");
					/*String payResult = (String)request.getAttribute("payResult");
					String dispMessage = "";
					if(payResult!=null && payResult.trim().length()!=0){
					dispMessage = "You have successfully placed your Membership Registration Application with HLC! You will be intimated within 24 Hrs after formal approval.";
					}else{
					dispMessage = "Payment Process Failed. Please Try Again Later";
					}*/
					%>
					<tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Credit Card: <span class="styleBoldTwo">Transaction Declined </span></td>
				  </tr>
				  <tr>
					<td width="34" class="tblDescrp"><img src="images/warning.gif" class="floatLeft" /></td>
				    <td width="466" class="tblDescrp"><span class="labelTabHead">Your transaction has been DECLINED!</span></td>
				  </tr>
				   <tr>
				     <td height="25" colspan="2" class="tblDescrp" style="border-bottom:1px solid #ccc;"><span class="styleBoldTwo">An Error Occurred</span></td>
			      </tr>
				   <tr>
				     <td colspan="2" class="tblDescrp" style="border-bottom:1px solid #ccc;"><strong>Number:</strong> 1</td>
			      </tr>
				   <tr>
				     <td colspan="2" class="tblDescrp" style="border-bottom:1px solid #ccc;">
					 <strong>Message:</strong> This transaction request has not been approved. You may elect to use another 
					 form of payment to complete this transaction or contact customer service for additional options. 
					 </td>
			       </tr>
				   <form name="myform" method="post" action="endorseRetryPay.do">
				   <input type="hidden" name="eventProcess" value="initRetryPayment" />
				   <input type="hidden" name="eventId" value="<%=eventId%>" />
				   <input type="hidden" name="paymentId" value="<%=paymentId%>" />
				   <input type="hidden" name="amt" value="<%=amt%>" />
				   <tr>
					<td colspan="2" >
					  <input name="butSbmt" type="submit" class="oneBtn" value="Retry Payment" />
					  <input name="butSbmt" type="submit" class="oneBtn" value="Register OmniBus"  />
					  <input name="butSbmt" type="submit" class="oneBtn" value="Back To List"  />
					  </td>
				 
				  </tr>
					</form>  
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
