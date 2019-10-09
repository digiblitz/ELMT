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
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : advViewOmnibus.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.8
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAdvMag.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /></head> -->

<body>

<%
 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
			ArrayList alAdsView = (ArrayList)session.getAttribute("displayParticularAds");
			if(alAdsView==null || alAdsView.size()==0){
					
			}
		
%>
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

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">Advertisement: <span class="styleBoldTwo">View Request</span> <span class="styleBoldOne">Omnibus </span></td>
  </tr>
  
  <tr>
  	<td>
	<%
	Iterator itAll = alAdsView.iterator();
		if(itAll.hasNext()){
				HLCAdvertiserVO objAdvt = (HLCAdvertiserVO)itAll.next();
				ArrayList listAdsDet = (ArrayList)itAll.next();
				ArrayList priceList = (ArrayList)itAll.next();
	%>
		<form name="frmAdvReqView" action="advertiseAdmin.do" method="post" />
		<input name="advProcess" type="hidden" value="statusChange">
		<input name="advId" value="<%=objAdvt.getAdvertisementId()%>" type="hidden" />
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">Ad Agencies and Contact person information:</td>
				  </tr>
				   <tr>
					<td class="tableLeft">Advertiser's Name:</td>
					<th class="tableRight"><%=objAdvt.getAdvertiser()%>&nbsp;</th>
				  </tr>
				  <tr>
					<td class="tableLeft">Ad Agency  Name:</td>
					<th class="tableRight"><%=objAdvt.getAdAgency()%>&nbsp;</th>
				  </tr>
				   <tr>
					<td colspan="2" class="tblRowHead"><strong>Contact Person details:</strong></td>
				  </tr>
				  <tr>
					<td class="tableLeft">First Name:</td>
					<td class="tableRight"><%=objAdvt.getAgencyFirstName()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Middle Name:</td>
					<td class="tableRight"><%=objAdvt.getAgencyMiddleName()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Last Name:</td>
					<td class="tableRight"><%=objAdvt.getAgencyLastName()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Street:</td>
					<td class="tableRight"><%=objAdvt.getAgencyAddress()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Suite:</td>
					<td class="tableRight"><%=objAdvt.getAgencySuite()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Country:</td>
					<td class="tableRight"><%=objAdvt.getAgencyCountry()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">State:</td>
					<td class="tableRight"><%=objAdvt.getAgencyState()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">City:</td>
					<td class="tableRight"><%=objAdvt.getAgencyCity()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Zip:</td>
					<td class="tableRight"><%=objAdvt.getAgencyZip()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Phone:</td>
					<td class="tableRight"><%=objAdvt.getAgencyPhone()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Fax:</td>
					<td class="tableRight"><%=objAdvt.getAgencyFax()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">EMail:</td>
					<td class="tableRight"><%=objAdvt.getAgencyEmail()%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Invoice to:</td>
					<td class="tableRight"><%=objAdvt.getInvoiceTo()%>&nbsp;</td>
				  </tr>
				  
				  
				  <tr>
					<td class="tableLeft">Media Name:</td>
					<td class="tableRight"><span class="styleBoldOne"><%=objAdvt.getMediaId()%>&nbsp;</span></td>
				  </tr>
				  <%
				   double totalAmount = 0.00;
				  Iterator its = priceList.iterator();
                    while(its.hasNext()){
                        //String priceDet[] = {mediaName, issueName, dispName, dispSubName, dimName, placement, amount };
                        String [] priceDet = (String [] )its.next();
						
						String mediaName = priceDet[0];
						String issueName = priceDet[1];
						String dispName = priceDet[2];
						String dispSubName = priceDet[3];
						String dimName = priceDet[4];
						String placement = priceDet[5];
						String amount = priceDet[6];
						totalAmount = totalAmount + Double.parseDouble(amount);
						
				  %>
				  
				  <tr>
					  <td colspan="2">
						  <table border="0" cellpadding="0" align="center" cellspacing="0" class="tableInner">
								<tr>
									<td colspan="2" class="tblMainHead"><%=issueName%>&nbsp;</td>
								</tr>
								
								<tr>
									<td class="tableLeft">Advertisement Types:</td>
									<td class="tableRight"><%=dispName%>&nbsp;</td>
								</tr>
								
								<tr>
									<td class="tableLeft">Advertisement Sub-Types:</td>
									<td class="tableRight"><%=dispSubName%>&nbsp;</td>
								</tr>
								
								<tr>
									<td class="tableLeft">Ad Dimension:</td>
									<td class="tableRight"><%=dimName%>&nbsp;</td>
								</tr>
								
								<tr>
									<td class="tableLeft">Placement:</td>
									<td class="tableRight"><%=placement%>&nbsp;<span class="asterisk"></span></td>
								</tr>
								
								<tr>
									<td class="tableLeft">Amount:</td>
									<td class="tableRight"><%=amount%>&nbsp;<span class="asterisk"></span></td>
								</tr>
						   </table>					</td>
				  </tr>
				  <%
				  }
				  %>
				  <tr>
					<td colspan="2" class="tblRowHead">&nbsp;Other Information</td>
				  </tr>
					<tr>
						<td class="tableLeft">Advertising Material Coming From:</td>
						<td class="tableRight"><%=objAdvt.getMaterialComingFrom()%>&nbsp;</td>
				  </tr>
				  <%
				  String comingDate="";
				  if(objAdvt.getMaterialComingDate()!=null){
				  		comingDate =sdf.format(objAdvt.getMaterialComingDate());
				  }
				  %>
				  <tr>
					<td class="tableLeft">Date:(MM/DD/YY)</td>
					<td class="tableRight"><%=comingDate%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Ad Supplied on </td>
					<td class="tableRight"><%=objAdvt.getAdvSuppliedOn()%>&nbsp;</td>
				  </tr>
				  <tr>
					  <td class="tableLeftTxtArea">Special Instruction: </td>
				    <td class="tableRight"><%=objAdvt.getComments()%>&nbsp;</td>
				  </tr>
				   <tr>
					<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
				  </tr>
				  <tr>
					  <td class="tableLeft"><strong>Total Amount:</strong></td>
				    <td class="tableRight"> <span class="styleBoldOne">$ <%=totalAmount%>&nbsp;</span> <span class="asterisk"></span></td>
				  </tr>
				  <tr>
					<td colspan="2" height="30" class="alignCenter">
					<%
						String statusValue = objAdvt.getRequestStatus().trim();
						//String statusValue = "Active";
						String statusOne = "";
						String statusTwo = "";
						if(statusValue.equals("Pending")){
							statusOne = "Activate";
							statusTwo = "Reject";	
							%>
						<input name="status" type="submit" value="<%=statusOne%>" class="gradBtn"  />
				        <input name="status" type="submit" value="<%=statusTwo%>" class="gradBtn"  />
						<%						
						}
						%>
						
					   <%
						 if(statusValue.equals("Active")){
							statusOne = "Pending";
							statusTwo = "Reject";	
							%>
							<input type="button" class="gradBtn" value="Back" onclick="javascript:history.back(-1)";/>	
						<%}
								
						 if(statusValue.equals("Rejected")){
							statusOne = "Pending";
							statusTwo = "Activate";
							%>
							<input type="button" class="gradBtn" value="Back" onclick="javascript:history.back(-1)";/></td>			
						<%}
					 
					%>
					
					
					
				  </tr>
			</table>
			
		</form>
		<%
		}
		%>
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
