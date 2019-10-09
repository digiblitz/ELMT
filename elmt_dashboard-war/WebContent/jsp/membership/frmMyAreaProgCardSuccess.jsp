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
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="meetings.title"/></title>
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
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
			<%
			try{
				String arName = (String)session.getAttribute("areaName");					
				String progName=(String)session.getAttribute("areaRidProg");
				String areaRidProgDesc=(String)session.getAttribute("areaRidProgDesc");
				String progYr=(String)session.getAttribute("progYear"); 
				String amt=(String)session.getAttribute("amt");             
			%>
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
					  <tr>
						<td colspan="2" class="tblMainHead">
							<strong>HLC:</strong>  <span class="styleBoldTwo">Area Member Program Confirmation</span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp"> 
							<p>You have successfully made a payment of<strong> $<%=amt%></strong>. <br />
							    <br />
							    You will receive an email from <strong>info@digiblitz.com</strong> regarding this transaction.<br />
							    <br />
							    
			<strong>Area Name</strong> <span class="styleBoldOne"><%=arName%></span> <br />
            <strong>Area Rider Program Name</strong> <span class="styleBoldOne"><%=progName%> (<%=areaRidProgDesc%>)</span> <br />
                                <strong>Program Year</strong> <span class="styleBoldOne"> <%=progYr%> </span> <br />
                                <strong>Registration Status</strong> <span class="styleBoldOne">Active</span> <br />
                                <br />
								<%
								}
								catch(Exception e)
								{
									System.out.print(" jsp catch :"+e);
								}
								%>
											  
						      <input name="button" type="button" class="gradBtn" value=" Back " onclick="location.href='<%=request.getContextPath()%>/index.jsp';" />
            
		                  </p>
						</td>
					  </tr>
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


