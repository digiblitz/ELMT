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

<!--/*  Program Name    : frmAdvAdminRequestView.jsp
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
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<script language="javascript">

function postData(){
mid = document.frmAdvDispLst.media.value;
if(mid==""){
document.frmAdvDispLst.displayStatus.selectedIndex = 0;
alert("Please select media type");
document.frmAdvDispLst.media.focus();
return false;
}

else if(mid!=null){  
	document.frmAdvDispLst.advProcess.value = "viewAdsReqest";
    document.frmAdvDispLst.method="post";
    document.frmAdvDispLst.action="./advertiseAdmin.do?mid="+mid;
    document.frmAdvDispLst.submit();
}

return true;
}

</script>

<%
String  mId = (String)request.getAttribute("mid");
if(mId==null || mId.equals("")){
	mId="";
}
String  status = (String)request.getAttribute("status");
if(status==null || status.equals("")){
	status="";
}
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
	Advertisement: <span class="styleBoldTwo">Advertisement Request Listing</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The Advertisement requests are listed as follows. <br />
		<br />
		Select a media type and view the Advertisement Request Details.<br />
		<br />		<br />
	</td>
  </tr>
 
 <tr>
 	<td>
		<table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		  
		  <tr>
		   	<td colspan="4" class="tableSpan">
			<form name="frmAdvDispLst" >
			<input name="advProcess" type="hidden" value="viewAdsReqest">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
					  <th width="128" height="20" valign="top" class="alignRight">&nbsp;Media Type: </th>
					  <td colspan="3" valign="middle" class="alignLeft">
					  <select name="media" class="selectboxOne"  >
								<option selected="selected" value="">Select One</option>
								<%
									Vector vMedia = (Vector)session.getAttribute("displayMediaTypeSesssion");
									if(vMedia!=null && vMedia.size()!=0){
										Enumeration emedia = vMedia.elements();
										String mediaId = "";
										String mediaName = "";
										String mediaDesc  = "";
										String mediaStatus = "";
										while(emedia.hasMoreElements()){
											String[] s = (String [])emedia.nextElement();
											mediaId = s[0];
											mediaName = s[1];
											mediaDesc  = s[2];
											mediaStatus = s[3];
											if(mediaId.equals(mId)){
									%>
									<option value="<%=mediaId%>" selected="selected"><%=mediaName%></option>
									<%}
									else{
									%>
									 <option value="<%=mediaId%>"><%=mediaName%></option>
									<%		
									}
									}
									}%>
							 </select>
					  </td>
					  <th width="48" height="20" valign="top" class="alignLeft"> Status </th>
					  <td width="206" colspan="3" valign="middle" class="alignLeft">
					  <select name="status" id="displayStatus" class="selectboxOne" onchange="return postData();">
                        <option selected="selected" value="">Select One</option>
						<%
							if(status.equals("Pending")){
						%>
                        <option value="Pending" selected="selected" >Pending</option>
						<%
							}
							else{
						%>
						 	<option value="Pending" >Pending</option>
						<%
							}
							if(status.equals("Active")){
						%>
						<option value="Active" selected="selected" >Active</option>
						
						<%
						}
						else{
						%>
							<option value="Active" >Active</option>
						<%
						}
						if(status.equals("Rejected")){
						%>
						
						<option value="Rejected" selected="selected" >Rejected</option>
						<%
						}
						else{
						%>
						<option value="Rejected" >Rejected</option>
						<%
						}
						%>
                      </select></td>
				  </tr>
				</table>
			  </form>
			</td>
		   </tr>
<!--		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>
-->
		  <tr>
		    <td class="tblRowHeadTwo">Advertiser </td>
			<td class="tblRowHeadTwo">Advertising Agency </td>
			<td class="tblRowHeadTwo">Date Of Request </td>
			<td class="tblRowHeadTwo">View</td>
		   </tr>
		   <%
			ArrayList vPendingAds = (ArrayList)session.getAttribute("displayPendingAds");
			if(vPendingAds!=null && vPendingAds.size()!=0){
						Iterator eDispLst = vPendingAds.iterator();
						while(eDispLst.hasNext()){
						 	HLCAdvertiserVO objAdv = (HLCAdvertiserVO)eDispLst.next();
							String advertisementId = objAdv.getAdvertisementId();
							String userId = objAdv.getUserId();
							String advertiser = objAdv.getAdvertiser();
							String adAgency = objAdv.getAdAgency();
							String advReqDate = "";
							if(objAdv.getAddDate()!=null){
								advReqDate = sdf.format(objAdv.getAddDate());
							}
							
						//Debug.print("===============================");
					%>
						<form name="frmAdvReqView" action="advertiseAdmin.do" method="post" />
						<input name="advProcess" type="hidden" value="viewAds">
						<input name="advId" value="<%=advertisementId%>" type="hidden" />
						  <tr>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=advertiser%></td>
							<td bgcolor="#E3E1D2" class="alignCenter"><%=adAgency%></td>
							<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=advReqDate%></td>
							<td bgcolor="#E3E1D2" class="alignCenter">
							<input type="submit" name="Submit5" value="View" class="oneBtn"  /></td>
						   </tr>
							</form>
		   	<%}	
					 }
					else {
					%>
					<tr>
					  <th height="25" colspan="4" align="center">No  records are available.</th>
					</tr>
					<%}%>
<!-- <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;

			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
-->

		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
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
