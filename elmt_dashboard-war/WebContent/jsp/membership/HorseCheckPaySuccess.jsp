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
						String amt = (String)request.getAttribute("amount");					 
						DecimalFormat myFormatter = new DecimalFormat("###,###.00");
						double finalAmt = 0.0f;
						 if(amt!=null){
						finalAmt = Double.parseDouble(amt);
						}

		      			String output  = myFormatter.format(finalAmt);
						String horseMemberId =(String)request.getAttribute("horseMemberId");
						String horseName =(String)request.getAttribute("horseName");
						String CheckNo =(String)request.getAttribute("CheckNo");
						Date Chkdte= (Date) request.getAttribute("Chkdte");
						String banknme =(String)request.getAttribute("banknme");
						String PaidAmt =(String)request.getAttribute("PaidAmt");
						double finalpaid = 0.0f;
						double balAmt = finalAmt-finalpaid;
						String output_final  = myFormatter.format(finalpaid);
						String output_bal  = myFormatter.format(balAmt);	
					int getYr=Chkdte.getYear();
					int getDate = Chkdte.getDate();
					int getMonth = Chkdte.getMonth();
				%> 
				<input type="hidden" name="horseMemberId" value="<%=horseMemberId%>">
  <tr>
    <td colspan="2" class="tblDescrp" style="padding:10px;">
	
    <strong> You have successfully registered <Horse Name> 
    <span class="styleBoldOne">(<%=horseName%>)</span> </strong>Registration details are as follows:
	
	<br />	<br />	<br />	
		<br />
	<table align="center" cellpadding="2" cellspacing="3" width="100%">
	<th colspan="2"><span class="tblMainHead">Details of the Check Payment for Horse Re-payment:</span></th>
	<tr>
		<td><span class="alignLeft"><strong>No.:</strong></span></td>
		<td><span class="styleBoldOne"><%=horseMemberId%></span></td>
	</tr>
<!--	<tr>
		<td><span class="alignLeft"><strong>Check Date :</strong></span> </td>
		<td><span class="styleBoldOne">< %=getYr+1900%>-< %=getMonth+1%>-< %=getDate%></span></td>
	</tr>
	<tr>
		<td><span class="alignLeft"><strong>Check Number:</strong></span></td>
		<td><span class="styleBoldOne">< %=CheckNo%> </span></td>
	</tr>
	<tr>
		<td><span class="alignLeft"><strong>Bank Name:</strong></span> </td>
		<td><span class="styleBoldOne">< %=banknme%></span></td></tr>-->
	<tr>
		<td><span class="alignLeft"><strong>Total Amount:</strong></span></td>
		<td> <span class="styleBoldOne"><%=amt%></span></td>
	</tr>
<!--	<tr>
		<td><span class="alignLeft"><strong>Registration Level:</strong></span></td>
		<td> <span class="styleBoldOne">Full</span></td>
	</tr>-->
	<tr>
		<td><span class="alignLeft"><strong>Registration Status:</strong></span></td>
		<td> <span class="styleBoldOne"> Pending Until Check Is Processed</span></td>
	</tr>
	<tr>
	<td colspan="5"><br />
		If paying by check , please mail your payment <span class="styleBoldOne">(<%=PaidAmt%>)</span> to: <br />
	</td></tr>
	<tr>
	<td colspan="5">	
		<strong>
		<br />
		 <br />
		Horse  Registration <br />
		525 Old Waterford Road NW <br />
		Leesburg, VA 20176 <br />
		</strong><br />
	
		Please include Horse's name and No. on check!
	</td>	</tr>	
	<tr>
	<td colspan="5">
		<strong> Note:</strong> Your upgrade status will be pending until check is processed.		<br />
	</td>
	</tr>
	<tr>
		<td>
		<span class="alignCenter">
			<input name="button" type="button" class="gradBtn" value="Back to List" onclick="location.href='RegHorseListing.do?process=UserListing';" />
		</span>
		</td>
	</tr>
	</table>
<!--	<table>
						
									<tr>
									<td align="center" colspan="2" class="tblDescrp">
									<br />
									<input  type="button" class="gradBtn" value="Back To Listing" onclick="window.location.href('RegHorseListing.do?process=UserListing');"/>
									</td>
									</tr>
						
							</table>-->
	
		 
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
