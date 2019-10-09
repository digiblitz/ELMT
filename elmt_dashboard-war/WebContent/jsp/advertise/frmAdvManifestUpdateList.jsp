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


<!--/*  Program Name    : frmAdvManifestUpdateList.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.10
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
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmSponsReg.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>



<%

SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
%>

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

<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Advertisement: <span class="styleBoldTwo">Admin -  Manifest Detail Update Listings</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		You are viewing the list of Advertisements posted by this advertiser for all the issues listed below.  <br />
		<br />
		To update a Manifest click on the <strong>Update</strong> button which should open an interface for updating the details. <br />
		<br />		<br />
	</td>
  </tr>
 
 <tr>
 	<td>
		<table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="5" class="tableSpan">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th height="20" width="130" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
					
						<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
							<span class="legendOne">&nbsp;</span> &nbsp;Update Manifest
						</td>
						<!--
						<td valign="middle" class="alignLeft">
							<span class="legendTwo">&nbsp;</span> &nbsp;View	
						</td>
						-->
				  </tr>
				</table>
			</td>
		  </tr>

<!--		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>
-->
		  <tr>
		    <td width="79" class="tblRowHeadTwo">Issue Type </td>
			<td width="93" class="tblRowHeadTwo">Advertisement Type </td>
			<td width="108" class="tblRowHeadTwo">Advertisement Sub-Type </td>
			<td width="61" class="tblRowHeadTwo">Dimension </td>
			<td width="63" class="tblRowHeadTwo">Update Manifest </td>
		   </tr>
		   <%
			ArrayList vAdsDetailList = (ArrayList)request.getAttribute("adsDetailList");
			if(vAdsDetailList!=null && vAdsDetailList.size()!=0){
						Iterator eDispLst = vAdsDetailList.iterator();
						while(eDispLst.hasNext()){
						 	HLCAdsDetailedVO objAdsDetVO = (HLCAdsDetailedVO)eDispLst.next();
							String advDetailId = objAdsDetVO.getAdvDetailId();
							String mediaId = objAdsDetVO.getMediaId();
							String adsId = objAdsDetVO.getAdsId();
							String mediaName = objAdsDetVO.getMediaName();
							String issueName = objAdsDetVO.getIssueName();
							String dispName = objAdsDetVO.getDispName();
							String dispSubName = objAdsDetVO.getDispSubName();
							String dimName = objAdsDetVO.getDimName();
							String placement = objAdsDetVO.getPlacement();
							String amount = objAdsDetVO.getAmount();
							boolean status = objAdsDetVO.isShowStatus();
						//Debug.print("===============================");
					%>
						<form name="frmAdvReqView" action="advertiseAdmin.do" method="post" />
						<input name="advProcess" type="hidden" value="initUpdateAdsDetail">
						<input name="advDetailId" value="<%=advDetailId%>" type="hidden"/>
						<input name="mediaId" value="<%=mediaId%>" type="hidden"/>
						  <tr>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=issueName%></td>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=dispName%></td>
							<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=dispSubName%></td>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=dimName%></td>
							<%
							if(status==true){
							
							%>
							<td bgcolor="#E3E1D2" class="alignCenter">
								<input type="submit" name="updateManifest" value="Update" class="twoBtn" />
							</td>
							<%
							}
							else{
							%>
							<td bgcolor="#E3E1D2" class="alignCenter">
								<input type="submit" name="updateManifest" value="Update" class="twoBtn" disabled="disabled" />
							</td>
							<%
							}
							%>
				    </tr>
						</form>
		   	<%}	
					 }
					else {
					%>
					<tr>
					  <th height="25" colspan="5" align="center">No records are available. </th>
					</tr>
					<%}%>
<!--		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;

			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
-->

		  <tr>
			<td height="19" colspan="5">&nbsp;</td>
           </tr>
	  </table>
	</td>
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
