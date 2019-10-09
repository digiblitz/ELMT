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
<%-- 
    Document   : reportTypes
    Created on : Sep 2, 2009, 12:34:58 PM
    Author     : parteek
--%>

<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Business Service Center</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-sidebar.css" />


<link rel="stylesheet" type="text/css" href="css/EAframe.css" />
<link rel="stylesheet" type="text/css" href="css/Usermenu.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />




<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->


<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-sidebar.css" />

 
<script type="text/javascript" src="js/ddlevelsmenu.js">

/***********************************************
* All Levels Navigational Menu- (c) Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/


</script>

</head>


<body>

<!-- Code starts for setting width and border of Web Page -->

<!-- Code Ends -->

<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
<%@ include file = "/include/header.jsp" %> 
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
  <td align="right" bgcolor="#ffffff" class="text">&nbsp;
  </td>
 </tr>
  
  
  
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			
			<!-- LEFT MENU STARTS HERE -->
			<!-- <%@ include file = "ADV_MappingRoleLeftBar.html" %> -->

			<!-- LEFT MENU ENDS HERE -->		    
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="1" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                  
                  
                  <tr>
                    <td><table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
                        
						<tr>
                             <td class="tableLeft"><strong color="red"><%=(String)request.getAttribute("message") %></strong></td>
                            
                        </tr>
						<tr>
                             <td class="tableLeft"><strong>Please Select Another</strong></td>
                            
                        </tr>
						<tr>
                             <td class="tableLeft"> <input type="submit" class="gradTwoBtn" value="Back" name="Back" onclick="location.href='showSalesReportByValue.do?type=<%=(String)request.getAttribute("path")%>'" /></td>
                            
                        </tr>
						
                        </table></td>
                        </tr>
                                                            
                      <tr>
                          <td colspan="2" class="alignCenter">&nbsp;
                              <!-- DO NOT DELETE THIS ROW --></td>
                      </tr>
                    </table></td>
                  </tr>
                </table>
				<!-- CONTENTS END HERE --></td>
		  </tr>
                   <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "/include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
	  </table>
	
	</td>
  </tr>
  


<!-- Code starts for setting width and border of Web Page -->


<!-- Code Ends -->



</body>
</html>
