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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvDimensionDetailsDelete.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.5
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
-->
<bean:define id="advDisplayDetail" name="AdvertisementDisplayDetail" scope="request"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="./javascripts/frmCreateDimenDetail.js" type="text/javascript" ></script>

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
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">Delete Dimension Details </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	  </strong><br />
	<br /></td>
  </tr>
  <tr>
  	<td>
		        <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout"><tr>
				     <td class="tableLeft">Media Type:</td>
				     <td class="tableRight"><bean:write name="advDisplayDetail" property="mediaType"/></td>
		     	  </tr>
				   
				   <tr>
					<td class="tableLeft">Advertisement Type:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="displayType"/></td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Advertisement Sub-Type:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="displaySubType"/></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Dimension Type:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="dimensionType"/></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Dimensional Name:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="dimensionalName"/></td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Advertisement Height:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="height"/></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Advertisement Width:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="width"/></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Dimensional Unit:</td>
					<td class="tableRight"><bean:write name="advDisplayDetail" property="dimensionalUnit"/></td>
				  </tr>	
				  <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="button" name="method" value="Confirm Delete" class="gradBtn" onClick="window.location='advertise.do?method=delete&dimId=<bean:write name="advDisplayDetail" property="dimensionId"/>'" />&nbsp;
					<input type="button"  value="Cancel" class="gradBtn" onClick="javascript:history.back(-1);"/>					</td>
				  </tr>
			</table>	
    </td>  <tr>
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
				  
		
