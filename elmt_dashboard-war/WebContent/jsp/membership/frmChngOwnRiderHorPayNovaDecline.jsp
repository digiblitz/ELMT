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
 <%@ page import="com.hlcmeeting.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /></head>
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
				     <td colspan="2" class="tblDescrp">
					 Click '<strong>Return to Order</strong>' button to return to your payment details page and verify your 
				     payment information. </td>
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
				   <tr>
					  <td height="40" colspan="2" class="tblDescrp">
					   <% 
 					  	 
						String horseName = (String) request.getAttribute("horseName");
						String horseMemberId = (String) request.getAttribute("horseMemberId");
						String totalAmount = (String) request.getAttribute("totalAmount");
						String paymentId= (String) request.getAttribute("paymentId");
                                                System.out.println("Payment id in jsp..........."+paymentId);
                                                String horseName1=(String) request.getAttribute("horseName1");
                                                String riderId =(String) session.getAttribute("riderId");
                                                String OwnerMailId=(String)request.getAttribute("OwnerMailId");
                                                String riderEmailId =(String)request.getAttribute("riderEmailId");
                                                String competitionName=(String)request.getAttribute("competitionName");
                                                String membershipName1=(String)request.getAttribute("membershipName1");
                                                String tempOwnerName=(String)request.getAttribute("tempOwnerName");
                                                String tempRiderName=(String)request.getAttribute("tempRiderName");
					String forEE = (String)session.getAttribute("forEE");
					String successUrl = "";
					if(forEE!=null && forEE.equalsIgnoreCase("fromEventEntry")){
						successUrl = "OEEDemo.do?process=UserListing";
					}else{
						successUrl = "RegHorseListing.do?process=UserListing";
					}
 					  %>
					  <form name="frmdecline" action="ChngeRePay.do" method="post">
					  <input name="submit2" type="submit" value="Try Again" class="gradBtn" />															
 <input type="button" name="backlist" value="Back To Listing" class="gradBtn" onclick="location.href='<%=successUrl%>'"/>
					  <input type="hidden" name="process" value="chngrepay" />
					  
					  <input type="hidden" name="horseName" value="<%=horseName%>" />
					  <input type="hidden" name="horseMemberId" value="<%=horseMemberId%>" />
					   <input type="hidden" name="totalAmount" value="<%=totalAmount%>" />
					  <input type="hidden" name="paymentId" value="<%=paymentId%>" />
                                           <input type="hidden" name="horseName1" value="<%=horseName1%>" />
					  <input type="hidden" name="riderId" value="<%=riderId%>" />
					  <input type="hidden" name="OwnerMailId" value="<%=OwnerMailId%>" />
					  <input type="hidden" name="riderEmailId" value="<%=riderEmailId%>" />					  
					  <input type="hidden" name="competitionName" value="<%=competitionName%>" />
					  <input type="hidden" name="membershipName1" value="<%=membershipName1%>" />
					  <input type="hidden" name="tempOwnerName" value="<%=tempOwnerName%>" />
					  <input type="hidden" name="tempRiderName" value="<%=tempRiderName%>" />
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
