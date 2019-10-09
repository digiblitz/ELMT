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
<%@ page language = "java"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmOffcialDet.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/login_header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		
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
		
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		User Donations </td>
  </tr>
 
  <tr>
    <td colspan="2" class="tblDescrp" style="padding:10px;">
	<%
	float totDonAmt1=0;
HLCDonationDetailVO donVO1 = new HLCDonationDetailVO();
				ArrayList donationList1 = (ArrayList)request.getAttribute("donList");
				if(donationList1!=null && donationList1.size()!=0){
					Iterator itr1 = donationList1.iterator();
					while(itr1.hasNext()){
					donVO1 = (HLCDonationDetailVO)itr1.next();
					totDonAmt1 = donVO1.getTotDonAmt();
					}}	
					%>
	Thank you for your donation the amount of $ <strong><%=totDonAmt1%></strong> It would be unable to survive or to continue its efforts to educate our members and introduce our sport to new enthusiasts without these tangible expressions of goodwill.
It is most grateful for both your contribution and your commitment to our sport.  Thank you!

<br />
<br />
<strong>Details of Your Donation:</strong>
<br/>
<br/>
<%SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");				   
java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
String attactDate = String.valueOf(tempDate).substring(5,7)+"/"+String.valueOf(tempDate).substring(8,10)+"/"+String.valueOf(tempDate).substring(0,4); 
%>
<strong>Date:</strong> <%=attactDate%>
<br/>
<br/>
  <%
						
						
						String year=String.valueOf(tempDate).substring(0,4); 
  						String firstName= "";
						String lastName = "";
						String userName="";
						float totDonAmt=0;
						String sub1="";
						String sub2="";
						Date donDate=null;
				HLCDonationDetailVO donVO = new HLCDonationDetailVO();
				ArrayList donationList = (ArrayList)request.getAttribute("donList");
				if(donationList!=null && donationList.size()!=0){
					Iterator itr = donationList.iterator();
					while(itr.hasNext()){
						donVO = (HLCDonationDetailVO)itr.next();
						String donPayId = donVO.getDonationPaymentId();
						String userId = donVO.getUserId();
						String donId = donVO.getDonationId();
						String donPrice = donVO.getDonationPrice();
						String donName = donVO.getDonationName();
						String donBy = donVO.getDonatedBy();
						//donDate = (Date)sdf.parse(donVO.getMemberDonationDate());
						String ccNumber = donVO.getCcNumber();
						sub1=ccNumber.substring(0,2);
						sub2=ccNumber.substring(12,16);
						
						firstName = donVO.getFirstName();
						lastName = donVO.getLastName();
						userName=firstName+" "+lastName;
						totDonAmt = donVO.getTotDonAmt();
						
						%>
						$ &nbsp;<%=donPrice%> for <%=donName%> in the name of <%=donBy%>
						<br/>
						<%
						}
						}
						
												
  %>

<br/><br/>
<strong>Credit Card:</strong>
<br/>
<%=userName%>
<br/>
<%=sub1%>**************<%=sub2%>
<%if(totDonAmt>=250){%>
 <tr><td colspan="2" class="tblDescrp" style="padding:10px;">

<strong>GIFT ACKNOWLEDGEMENT</strong>
<br />
from the
<br /> 
United States Eventing Association
<br />
525 Old Waterford Road N.W.
<br />
Leesburg, VA  20176
<br /><br />

The Omnibus Budget Reconciliation Act of 1993, as amended, effective December 31, 1993, requires that all gifts
of <strong>$250.00</strong> or more made to a charitable or non-profit organization be substantiated by a written
acknowledgement from the donor organization.  The United States Eventing Association is a non-profit
association incorporated in the Commonwealth of Massachusetts, doing business in the Commonwealth of Virginia.
It is and remains in good standing pursuant to Section 501(c)(3) of the Internal Revenue Code. 
In accordance with the requirements of the laws of the United States, is pleased to acknowledge
its receipt of a gift or gifts during <strong><%=year%></strong> and to substantiate the following information:
  <br /><br />
A donation of $<strong><%=totDonAmt%></strong> was made to the United States Eventing Association on <strong><%=attactDate%></strong> by <strong><%=userName%></strong>.
 <br /><br />
It provided no goods and services in consideration, in whole or in part, for the contribution.
On behalf , I hereby confirm that during the calendar year <strong><%=year%></strong>received the contribution as described above.  The purpose of the donation was a gift to the United States Eventing Association.
<br /><br />
<strong>Dated</strong>:  <%=attactDate%>
 <br /> <br />
Jo Whitehouse<br />
Chief Executive Officer

</td></tr>
<%}%>
<tr><td>
    <span><center>
    <input name="button" type="button" class="gradBtn" value="Another Donation" onclick="location.href='./webSiteDonation.do?donationProcess=view'" />&nbsp;</center>	
	</span><br />
</td></tr>
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
