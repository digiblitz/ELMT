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
<%@ page import="java.sql.*"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvSubTypeDimensionDisplay.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.3
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
<script src="javascripts/frmCreateDimenDetail.js" type="text/javascript" ></script>

<!--=====================================================================
-->
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">Dimension Details </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	  </strong><br />
	<br /></td>
  </tr>
  <tr>
  	<td>

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
					<%
					ArrayList  dimList = (ArrayList)session.getAttribute("dimensionDetails");
					
					if(dimList.size()!=0 && dimList!=null){
					
					String mediaName = "";
					String dispName = "";
					String	dispSubName = "";
					String	dimName = "";
					String	height = "";
					String	width = "";
					String	units = "";
					String	imagePath = "";           
					for (Iterator itP = dimList.iterator(); itP.hasNext();) {
						String dimensionList[] = (String []) itP.next();
						
						mediaName = dimensionList[0];
						dispName = dimensionList[1];
						dispSubName = dimensionList[2];
						
					}
					
					%>
					
					<tr>
					<td colspan="2" class="tblRowHead">Dimension Detail:</td>
				  </tr>
				  <tr>
				  <tr>
				     <td width="51%" class="tableLeft">Media Type:</td>
				     <td width="49%" class="tableRight"><%=mediaName%></td>
		     	  </tr>
				   
				   <tr>
					<td class="tableLeft">Advertisement Type:</td>
					<td class="tableRight"><%=dispName%></td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Advertisement Sub-Type:</td>
					<td class="tableRight"><%=dispSubName%></td>
				  </tr>
				 
				<td  colspan="2">
				<table width="100%">
					<tr>
						<td  class="tblRowHead">Dimensional Name:</td>
						<td  class="tblRowHead">Height</td>
						<td  class="tblRowHead">Width</td>
						<td  class="tblRowHead">Unit</td>
					</tr>
				  <%
				  for (Iterator itP = dimList.iterator(); itP.hasNext();) {
						String dimensionList[] = (String []) itP.next();
						dimName = dimensionList[3];
						height = dimensionList[4];
						width = dimensionList[5];
						units = dimensionList[6];
						imagePath = dimensionList[7];
					  %>
					  
					    <tr>
							<td  class="tableLeft"><%=dimName%></td>
							<td  class="tableLeft"><%=height%></td>
							<td class="tableLeft"><%=width%></td>
							<td class="tableLeft"><%=units%></td>
					  </tr>
					<%
				  	}
				  %>
							
							</table>
							</td>
					  </tr>
					<%
					}
					else{%>
					<tr>
					<td height="20" colspan="2" align="center">No Records were found</td>
					</tr>
					<%}
					%>
			</table>	
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
