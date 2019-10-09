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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head> 
<%
String amt = (String)request.getAttribute("totalAmount");
String activeLevel = (String)request.getAttribute("activeLevel");
%>
<body >
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
		<form name="horseSuccess" action="horserRidOwnAdd.do" method="post">		
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		Horse Membership Registration: <span class="styleBoldTwo">Confirmation.	</span></td>
  </tr>
  				<%
						String output ="";
						DecimalFormat myFormatter = new DecimalFormat("###,###.00");
						if(amt!=null){
						double finalAmt = Double.parseDouble(amt);
						
		      			  output  = myFormatter.format(finalAmt);
						}
						else{
						output = amt;
						}
						String registrationLevel = (String)request.getAttribute("registrationLevel");
						String horseMemberId =(String)request.getAttribute("horseMemberId");
						request.setAttribute("horseMemberId",horseMemberId);
						String horseName =(String)request.getAttribute("horseName");
						String forEE = (String)session.getAttribute("forEE");
						String successUrl = "";
						if(forEE!=null && forEE.equalsIgnoreCase("fromEventEntry")){
							successUrl = "OEEDemo.do?process=UserListing";
						}else{
							successUrl = "RegHorseListing.do?process=UserListing";
						}
				%> 
				 
				
  <tr>
    <td colspan="2" class="tblDescrp" style="padding:10px;">
	
    <strong> You have successfully registered <Horse Name> 
    <span class="styleBoldOne">(<%=horseName%>)</span> </strong>Registration details are as follows:
	
	<br />
	
		
		<%
		if(activeLevel!=null && activeLevel.equalsIgnoreCase("Active")){
		%>
		<span class="textCommon">
		<br />
		<strong>No.:</strong><span class="styleBoldOne"><%=horseMemberId%></span> 
		<br />
		
		<strong>Registration Level:</strong> <span class="styleBoldOne"><%=registrationLevel%></span>
		<br />
		<strong>Registration Status:</strong> <span class="styleBoldOne"><%=activeLevel%></span>
		<br />
	 
		<strong>Amount:</strong><span class="styleBoldOne">$<%=output%></span>(U.S. Dollars)
		<br />
		</span>
		<%}else{
		%>
		<span class="textCommon">
		<br />
		<strong>No.:</strong><span class="styleBoldOne"><%=horseMemberId%></span> 
		<br />
		
		<strong>Registration Level:</strong> <span class="styleBoldOne"><%=registrationLevel%></span>
		<br />
		<strong>Registration Status:</strong> <span class="styleBoldOne">Pending until payment processed</span>
		<br />
		
		<br />
		If paying by check (payable in U.S dollars  &quot;&quot;), please mail your payment of </br>   
		<span class="styleBoldOne">$<%=output%></span>(U.S. Dollars) to: <br />
		
		<strong>
		<br />
		 <br />
		Horse Life Registration <br />
		525 Old Waterford Road NW <br />
		Leesburg, VA 20176 <br />
		</strong><br />
		Please include Horse's name and No. on check!
		<br />
		<br />
		<br />
		</span>
		<%
		}
		%>
	
	<table>
						
									<tr>
									<td colspan="2" class="tblDescrp">
									
									You have successfully registered  the Horse.
									Would you like to add  another<strong> rider or owner</strong> for a horse? <br />
									<br />
									<input type="hidden" name="process" value="addRid" />
									<input type="hidden" name="cardStatus" value="check" />
									<input type="hidden" name="horseMemberId" value="<%=horseMemberId%>"/>
									<%
									System.out.print("horseMemberId 1" +horseMemberId);
									%>
									
									<input  type="submit" class="gradBtn" value="Yes, Add" />
									<input  type="button" class="gradBtn" value="No,View My List" onclick="location.href='<%=successUrl%>'"/>
									
									</td>
									</tr>
						
							</table>
	
		 
		<br />
	
	</td>
  </tr>
   
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
  </tr>
</table>
</form>
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
