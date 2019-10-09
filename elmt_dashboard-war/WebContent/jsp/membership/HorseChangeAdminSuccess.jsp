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
String successStatus =(String)session.getAttribute("successStatus");
System.out.print("amt" + amt);
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
		<span class="styleBoldTwo">Edit  Payment Confirmation For Change Horse Rider/Owner.</span></td>
  </tr>
  				<%
						 
						 String output ="0.00";
						DecimalFormat myFormatter = new DecimalFormat("###,###.00");
						if(amt!=null){
						double finalAmt = Double.parseDouble(amt);
						System.out.print("finalAmt" + finalAmt);
						
		      			  output  = myFormatter.format(finalAmt);
						  }
						  else{
						  output = amt;
						  }
						System.out.print("output" + output);
						 
						String horseMemberId =(String)request.getAttribute("horseMemberId");
					 
						String horseName =(String)request.getAttribute("horseName");
				%> 
				<input type="hidden" name="horseMemberId" value="<%=horseMemberId%>">
  <tr>
    <td colspan="2" class="tblDescrp" style="padding:10px;">
	
    <strong> You have successfully Changed the Payment.</strong>
	</br>
	<span class="textCommon"><br />
	 <strong>Horse Name</strong>  
      <span class="styleBoldOne">(<%=horseName%>)</span>
		<br />
		<strong>Horse Member Id.:</strong><span class="styleBoldOne"><%=horseMemberId%></span> 
		<br />
		<strong>Amount.:</strong><span class="styleBoldOne"><strong>$</strong><%=output%></span>U.S.Dollars 
		<br />
		
		 
	</span>
	<%if(successStatus.equalsIgnoreCase("ChangeReq")){%>
	
	<br />
		<input name="button" type="button" class="gradBtn" value="Back to List" onclick="location.href='RegHorseListing.do?process=requestFrm'" />
		<br />
		<%
		}
		else{
		%>
		<br />
		<input name="button" type="button" class="gradBtn" value="Back to List" onclick="location.href='RegHorseListing.do'" />
		<br />
		<%
		}
		%>
	<table>
						 
						
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
