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

<!--/*  Program Name    : advAdminManifestUpdatedDetails.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.4
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAdvMag.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /></head> 

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
    <td height="33" class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">Advertisement: <span class="styleBoldTwo">Published Advertisement Manifest Details</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">	You are viewing the updated and published details of all advertisements requested by you for all issues selected. </td>
  </tr>
  <tr>
  	<td><%
	      
					   			String advDetailId = "";
								String mediaId = "";
								String adsId = "";
								String mediaName = "";
								String issueName = "";
								String dispName = "";
								String dispSubName = "";
								String dimName = "";
								String placement = "";
								String amount = "";
								String manifestId ="";
					   			String pubAdvDetailId = "";
								String pubAdvId =  "";
								String pubDimId = "";
								String pubDimType = "";
								String pubDispSubType = "";
								String pubDispType = "";
								String pubIssueId = "";
								String pubIssueName = "";
								String pubMapId = "";
								String pubMediaId =  "";
								String pubMedianame = "";
								String pubPageNo = "";
								String pubIns = "";
								String pubPlacement = "";
	
	%>
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td class="tblRowHead">Updated and Published Manifest Details:</td>
				  </tr>
				 			  
				  <tr>
					  <td>
					  
	<%
	
	 ArrayList vAdsDetailList = (ArrayList)request.getAttribute("manifestDetailList");
					if(vAdsDetailList!=null && vAdsDetailList.size()!=0){
					Iterator eDispLst = vAdsDetailList.iterator();
							while(eDispLst.hasNext()){
									ArrayList al = (ArrayList)eDispLst.next();
									Iterator it = al.iterator();
										while(it.hasNext()){
												HLCAdsDetailedVO objAdsDetVO = (HLCAdsDetailedVO)it.next();
														advDetailId = objAdsDetVO.getAdvDetailId();
														if(advDetailId!=null){
														  mediaId = objAdsDetVO.getMediaId();
														  adsId = objAdsDetVO.getAdsId();
														  mediaName = objAdsDetVO.getMediaName();
														  issueName = objAdsDetVO.getIssueName();
														  dispName = objAdsDetVO.getDispName();
														  dispSubName = objAdsDetVO.getDispSubName();
														  dimName = objAdsDetVO.getDimName();
														  placement = objAdsDetVO.getPlacement();
														  if(placement==null || placement.equals("")){
														   placement ="NA";
														  }
														  amount = objAdsDetVO.getAmount();
														}
														
														HLCManifestVO objmanifest = (HLCManifestVO)it.next();
														 manifestId = objmanifest.getAdvManifestId();
														// out.print("manifestId"+manifestId);
														//  out.print("pubAdvDetailId"+objmanifest.getAdvDetailId());
														
														   pubAdvDetailId = objmanifest.getAdvDetailId();
														  
														   pubAdvId =  objmanifest.getAdvertisementId();
														   pubDimId = objmanifest.getDimId();
														   pubDimType = objmanifest.getDimensionTypeName();
														   pubDispSubType = objmanifest.getDisplaySubTypeName();
														   pubDispType = objmanifest.getDisplayTypeName();
														   pubIssueId = objmanifest.getIssueId();
														   pubIssueName = objmanifest.getIssueName();
														   pubMapId = objmanifest.getMapId();
														   pubMediaId =  objmanifest.getMediaId();
														   pubMedianame = objmanifest.getMediaName();
														   pubPageNo = objmanifest.getPageNo();
														   pubIns = objmanifest.getSplInstructions();
														   pubPlacement = objmanifest.getPlacement();
												     

		%>
						  <table border="0" cellpadding="0" align="center" cellspacing="0" class="tableInner">
	 							<form name="adminUpdate" method="post" action="advertiseAdmin.do">
								<input type="hidden" name="manifestId" value="<%=manifestId%>">
								<input type="hidden" name="pubMediaId" value="<%=pubMediaId%>">
								<input type="hidden" name="advProcess" value="editManifest">
							  <tr>
									<td colspan="2" class="tblMainHead">
									Published Issue: <span class="styleBoldOne"><%=issueName%>&nbsp;</span>	
									</td>
									<td class="tblMainHead">
									<span class="alignRight">
									<%
									 if(manifestId!=null){%>
									<input name="editIssue" type="submit" value=" Edit " class="twoBtn" /> 
									<%}else{%>
								    
							      	<input name="editIssue" type="submit" value=" Edit " disabled="disabled" class="twoBtn" />&nbsp;&nbsp;</span>
									<%}%>
									
								     </td>
								</tr>
								</form>
								<tr>
									<td class="tableLeft">Applied Issue:</td>
									<td colspan="2" class="tableRight"><%=issueName%>&nbsp;</td>
								</tr>
								<tr>
									<td class="tableLeft">Advertisement Types:</td>
									<td colspan="2" class="tableRight"><%=dispName%>&nbsp;</td>
								</tr>
								
								<tr>
									<td class="tableLeft">Advertisement Sub-Types:</td>
									<td colspan="2" class="tableRight"><%=dispSubName%>&nbsp;</td>
								</tr>
								
								<tr>
									<td class="tableLeft">Applied Ad Dimension:</td>
									<td colspan="2" class="tableRight"><%=dimName%>&nbsp;</td>
								</tr>
								<tr>
									<td class="tableLeft">Published Ad Dimension:</td>
									<td colspan="2" class="tableRight"><span class="styleBoldOne"><%=dimName%>&nbsp;</span></td>
								</tr>
								
								<tr>
									<td class="tableLeft">Applied Placement:</td>
									<td colspan="2" class="tableRight"><%=placement%>&nbsp;</td>
								</tr>
								<%
									if(objmanifest.getPlacement()!=null){
									pubPlacement = objmanifest.getPlacement();
									}
									else{
									pubPlacement="NA";
									}
									%>
								<tr>
									<td class="tableLeft">Published Placement:</td>
									<td colspan="2" class="tableRight"><span class="styleBoldOne"><%=pubPlacement%>&nbsp;</span></td>
								</tr>
								<%
									if(objmanifest.getPageNo()!=null){
									pubPageNo = objmanifest.getPageNo();
									}
									else{
									pubPageNo="NA";
									}
								%>
								<tr>
									<td class="tableLeft">Published Page Number:</td>
									<td colspan="2" class="tableRight"><%=pubPageNo%>&nbsp;</td>
								</tr>
								<%
									if(objmanifest.getSplInstructions()!=null){
										 pubIns = objmanifest.getSplInstructions();
									}
									else{
										 pubIns="NA";
									}
									%>
								<tr>
									<td class="tableLeftTxtArea">Special Instructions:</td>
									<td colspan="2" class="tableRight"><%=pubIns%>&nbsp;</td>
								</tr>
						   </table>		
						   <%}
						   }
	
%>			</td>
				  </tr>
				  <%
			  }
				else {
					%>
					<tr>
					  <th height="25" colspan="5" align="center">No records are available. </th>
					</tr>
					<%}%>
				  <tr>
					<td height="30" class="alignCenter">
					<input name="status" type="button" value="Back To Listing" style="width:100px;" class="gradBtn" onclick="javascript:history.back(-1);"  /></td>
				  </tr>
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
