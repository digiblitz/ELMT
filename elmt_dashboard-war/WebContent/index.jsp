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
<%@ page import="com.hlccommon.util.*"%>
<%@taglib uri="/WEB-INF/tlds/taglib186.tld" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<link href="ttip2.css" rel="stylesheet" type="text/css"/>
</head>

<%


String sessLogId = (String) session.getAttribute("sessionId");
String viewValue = (String) session.getAttribute("viewVal");
if(sessLogId==null){
	session.invalidate();	
		response.sendRedirect("login.jsp");	
		}
%>

<body>
<!-- Test commit by Manas -->
<table width="1024" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->
	</td>
  </tr>

  <tr>
   <tr><td><table width="337" border="0" align="center" cellpadding="0" cellspacing="0" id="subDeptTab" bgcolor="">
				  <tr>
					<td class="subDeptTabHead">Services Available</td>
				  </tr>
				

                                                        <tr><td class="subDeptTabList" align="left">
                                                       
                                                        <!-- ul>
                                                            <li>Membership Management</li>
                                                            <li>Meeting/Event Management</li>
                                                            <li>Mailbox</li>
                                                            <li>Reports</li>
                                                            <li>Accounting</li>
                                                        </ul-->
                                                        <ul>
                                                            <li>Business Process Management</li>
                                                            <li>SOA</li>
                                                            <li>Artifact Management</li>
                                                            <li>Project Management</li>
                                                            <li>Learning Management</li>
                                                            <li>CRM</li>
                                                        </ul>
                                                        
                                                        <br /></td> </tr>
				  <tr>
					<td class="subDeptTabFoot">&nbsp;</td>
				  </tr>
			  </table></td></tr>
				
				
                
                                          
    <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
 

</body>
</html>
