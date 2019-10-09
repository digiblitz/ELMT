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
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
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
		  <form name="horseSuccess" action="horserRidOwnAdd.do" method="post">	
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
					<%
							String amt = (String)request.getAttribute("totalAmount");
							DecimalFormat myFormatter = new DecimalFormat("###,###.00");
						 
							double finalAmt = Double.parseDouble(amt);
							String output  = myFormatter.format(finalAmt);
							String horseMemberId =(String)request.getAttribute("horseMemberId");
							request.setAttribute("horseMemberId",horseMemberId);
						String forEE = (String)session.getAttribute("forEE");
						String successUrl = "";
						if(forEE!=null && forEE.equalsIgnoreCase("fromEventEntry")){
							successUrl = "OEEDemo.do?process=UserListing";
						}else{
							successUrl = "RegHorseListing.do?process=UserListing";
						}
						String membershipName = "";
						if(session.getAttribute("UpgrdMembName")!=null){
							membershipName = (String) session.getAttribute("UpgrdMembName");
						}
/*							String CardType=(String)request.getAttribute("CcType");
							String CCNumber=(String)request.getAttribute("ccNumber");*/							
					%>
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
					  <tr>
						<td colspan="2" class="tblMainHead">
							<strong></strong><span class="styleBoldTwo"> Upgrade  Non-Human Confirmation</span></td>
					  </tr>
					 
					  <tr>
						<td colspan="4" class="tblDescrp"> 
							<p>You have successfully made a payment of<strong> $<%=output%></strong> and registered for the <strong>Non-Human Registration</strong>. <br />
							    <br />
							    You will receive an email from <strong>info@digiblitz.com</strong> regarding this transaction.<br /> 
						 <table width="75%" cellpadding="3" cellspacing="3" align="center">								
							<tr>
								<td><strong>No.:</strong></td>
								<td><span class="styleBoldOne"><%=horseMemberId%></span></td>
							</tr>	
							<tr>
								<td><strong>Registration Level:</strong></td>
								<td><span class="styleBoldOne"><%=membershipName%></span></td>
							</tr>
							<tr>
								<td><strong>Registration Status:</strong></td>
								<td><span class="styleBoldOne">Active</span></td>
							</tr>
						</table>
		                  </p>						</td>
					  </tr>
					  <tr>
						<td height="20" align="center">&nbsp; <!-- DO NOT DELETE THIS ROW -->
						  <span class="tblDescrp" >
						  <input name="button" type="button" class="gradBtn" value="Back to Home" style="width:90px;" onclick="location.href='index.jsp';" />
						  <input name="button" type="button" class="gradBtn" value="Back to List" style="width:90px;" onclick="location.href='<%=successUrl%>';" />
					    </span></td>
					  </tr>
				</table>
			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
		
			</form>
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
