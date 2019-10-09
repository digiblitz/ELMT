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

<!--/*  Program Name    : frmAdvManifestUpdate.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.7
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
<script src="javascripts/frmAdvManifest.js" type="text/javascript" ></script>
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" />
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
    <td colspan="2" class="tblMainHead">
		Advertisment: <span class="styleBoldTwo">Update Advertisement Manifest </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">To update the selected <strong>'Applied Issue</strong>' advertisement, fill-in the following details and click on the <strong>Update</strong> button  <br />
   </td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmAdvManifestUpdate" method="post" action="advertiseAdmin.do" onsubmit="return manifestUpdate();" >
		<input name="advProcess" type="hidden" value="updateConfirm" />

					
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
					<%
						HLCAdsDetailedVO manifestUpdateDetails = (HLCAdsDetailedVO)session.getAttribute("DispUpdateDetails");
						
						String adId = manifestUpdateDetails.getAdsId();
                        String advDetailId = manifestUpdateDetails.getAdvDetailId();
                        String amount = manifestUpdateDetails.getAmount();
                        String placementName = manifestUpdateDetails.getPlacement();
                        String mediaId = manifestUpdateDetails.getMediaId();
						String mediaName = manifestUpdateDetails.getMediaName();
                        String dispSubName = manifestUpdateDetails.getDispSubName();
                        String dimensionId = manifestUpdateDetails.getDimId();
						String advMapId = manifestUpdateDetails.getMapId();
                        String dispName = manifestUpdateDetails.getDispName();
                        String dimensionName = manifestUpdateDetails.getDimName();
						String issueId =  manifestUpdateDetails.getIssueId();
                        String issueName = manifestUpdateDetails.getIssueName();
		                                 
				  %>
				
				  <tr>
				  	 <td colspan="2" class="tblRowHead">Update Manifest</td>
				  </tr>
				   <tr>
				  	 <td colspan="2" height="25" class="tableSpan">Required fields are marked with an asterisk*.</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Advertisement Type:</td>
					<td class="tableRight"><%=dispName%>&nbsp;</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Advertisement Sub-Type:</td>
					<td class="tableRight"><%=dispSubName%>&nbsp;</td>
				  </tr>
				  <tr>
					 <td class="tableLeft">Issue Type:</td>
					 <td class="tableRight">
						 <select name="selIssue" id="chkIssueId" class="selectboxOne">
						  <option value="">Select One</option>
						 <%
						Vector issueDet = (Vector)session.getAttribute("DisplayIssueDetails");
						if(issueDet!=null && issueDet.size()!=0){
								Enumeration eIssueLst1 =issueDet.elements();
								while(eIssueLst1.hasMoreElements()){
									String[] sIss = (String [])eIssueLst1.nextElement();
									String issue_id=sIss[0];
									String issue_name1=sIss[1];
									String media_id1=sIss[2];
									
						   if(issue_id.equals(issueId)){
							%>
							<option value="<%=issue_id%>" selected="selected"><%=issue_name1%></option>
						    <%
							}
							else{
							%>
              			<option value="<%=issue_id%>"><%=issue_name1%></option>
							<%		
							}
							}
							}%>
					 </select>
 					 <span class="asterisk">*</span></td>
				  </tr>
				  
				  <tr>
					 <td class="tableLeft">Advertisement Dimension:</td>
					 <td class="tableRight">
						 <select name="selDimension" id="selWinDimchkIssue" class="selectboxOne" >
						 <option value="">Select One</option>
							<%
								Vector dimDetails = (Vector)session.getAttribute("DisplayDimensionDetails");
								if(dimDetails!=null && dimDetails.size()!=0){
									Enumeration eDimLst = dimDetails.elements();
									String dimListId = "";
									String dimListName = "";
										while(eDimLst.hasMoreElements()){
										String[] s = (String [])eDimLst.nextElement();
										dimListId = s[0];
										dimListName = s[1];
										if(dimListId.equals(dimensionId)){
											%>
											<option value="<%=dimListId%>" selected="selected"><%=dimListName%></option>
											<%}
											else{
											%>
										<option value="<%=dimListId%>"><%=dimListName%></option>
							<%		
							}
							}
							}%>
					 </select>
						<span class="asterisk">*</span>					</td>
				  </tr>
				  <tr>
				    <td class="tableLeftTxtArea" valign="top">Published Page No.: </td>
				    <td class="tableRight"><input type="text"  name="pageno" id="txtPage" class="textboxOne" size="15" />
                      <span class="asterisk">*</span> </td>
				    </tr>
				  <tr>
				    <td class="tableLeftTxtArea" valign="top">Special Placements:</td>
				   <td class="tableRight"><input type="text"  name="placement" id="txtSpecial"  class="textboxOne"  size="25" />
                      <span class="asterisk">*</span> </td>
				    </tr>
				  <tr>
					<td class="tableLeftTxtArea" valign="top">Special Instructions :</td>
					<td class="tableRight"><textarea name="SpecialInstructions" id="txtSpInstructions" rows="5" cols="27"></textarea>
					  <span class="asterisk">*</span>					</td>
				  </tr>
				    <input type="hidden" name="adId" value="<%=adId%>" />
				   <input type="hidden" name="advMapId" value="<%=advMapId%>" />
				   <input type="hidden" name="advDetailId" value="<%=advDetailId%>" />
				   
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input name="Submit" type="submit" class="gradBtn" value="Create"/>&nbsp;&nbsp;&nbsp;
					<input name="button" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);"/>
					</td>
				  </tr>
			</table>
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW -->
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
