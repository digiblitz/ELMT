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

<!--/*  Program Name    : frmAdvDisplaySubTypeList.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.6
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
<script language="javascript">
function postData(mid){
if(mid!=""){
		document.frmAdvDispSubTypeList.advProcess.value = "advDispLstShow";
		document.frmAdvDispSubTypeList.method="post";
		document.frmAdvDispSubTypeList.action="./advertisement.do?mid="+mid;
		document.frmAdvDispSubTypeList.submit();
	}
else{
	document.frmAdvDispSubTypeList.advProcess.value = "advDispSubTypeLst";
	document.frmAdvDispSubTypeList.method="post";
	document.frmAdvDispSubTypeList.action="advertisement.do"
	document.frmAdvDispSubTypeList.submit();
	
}
}
function showData(dispId){
if(dispId!=""){
	var mid = document.frmAdvDispSubTypeList.media.value;
	document.frmAdvDispSubTypeList.advProcess.value = "advDispSubTypeShow";
	document.frmAdvDispSubTypeList.method="post";
	document.frmAdvDispSubTypeList.action="./advertisement.do?dispId="+dispId + "&mid="+mid;
	document.frmAdvDispSubTypeList.submit();
	}
else{
	document.frmAdvDispSubTypeList.advProcess.value = "advDispSubTypeLst";
	document.frmAdvDispSubTypeList.method="post";
	document.frmAdvDispSubTypeList.action="advertisement.do"
	document.frmAdvDispSubTypeList.submit();
}
}

</script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<%
String  mId = (String)request.getAttribute("mid");
String displayId =(String)request.getAttribute("dispId");
if(mId==null || mId.equals("")){
mId="";
}
if(displayId==null || displayId.equals("")){
displayId="";
}
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" cellpadding="0" cellspacing="1"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="3" class="tblMainHead">
	Advertisement: <span class="styleBoldTwo">Advertisement Sub-Type Listing</span></td>
  </tr>
  <tr>
    <td colspan="3" class="tblDescrp">		The Advertisement Sub-Types are as follows: <br />
		<br />
		To edit a Sub-Type, click on the <strong>'Edit'</strong> button beside it. To detele a record click on the <strong>'Delete'</strong> button.</td>
  </tr>
 
 <tr>
 	<td colspan="3">
		<form name="frmAdvDispSubTypeList" >
			<input type="hidden" name="advProcess" value=""/>
		 <table border="0" cellpadding="3" align="left" cellspacing="1" class="formLayout">
		  
		 
		   <tr>
		   	<td colspan="3">
			
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
					  <th width="88" height="20" valign="top" class="alignLeft">&nbsp;Media Type: </th>
					  <td colspan="3" valign="middle" class="alignLeft">
					  <select name="media" class="selectboxOne" onchange="postData(this.value);">
						<option selected="selected" value="">Select One</option>
						<%
						
							Vector vMedia = (Vector)session.getAttribute("objSesAdvMediaType");
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
						</select>	     </td>
					  <th width="128" height="20" valign="top" class="alignLeft">Advertisement Type: </th>
					  <td width="177" colspan="3" valign="middle" class="alignLeft">
					  <select name="dispType" class="selectboxOne" onchange="showData(this.value);">
						<option selected="selected">Select One</option>
						<%
							Vector vType = (Vector)session.getAttribute("objSesAdvDispType");
							if(vType!=null && vType.size()!=0){
								Enumeration eDisp = vType.elements();
								String displayTypeId =  "";
								String displayTypeName =  "";
								String mediaIdVal = "";
								while(eDisp.hasMoreElements()){
									String[] s = (String [])eDisp.nextElement();
									displayTypeId = s[0];
									displayTypeName = s[1];
									mediaIdVal  = s[2];
									if(displayTypeId.equals(displayId)){
							%>
							<option value="<%=displayTypeId%>" selected="selected"><%=displayTypeName%></option>
						    <%}
							else{
							%>
              			     <option value="<%=displayTypeId%>"><%=displayTypeName%></option>
							<%		
							}
							}
							}%>
					  </select>					  </td>
				  </tr>
				</table>				</td>
				</tr>
				</table>
			</form>		</td>
	</tr>  
		   <%
			String deleteStatus = (String)request.getAttribute("errStat");
			if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
			%>
			<tr>
			<td class="styleError" height="25" colspan="4">Cannot delete this record. The file is in use.</td>
			</tr>
			<%
			}
			%> 	  	
		  <tr>
			<td width="339" height="27" class="tblRowHead">Advertisement Sub-Type</td>
			<td width="65" class="tblRowHead">Edit </td>
			<td width="87" class="tblRowHead">Delete</td>
		   </tr>

			<%
		
					Vector dispSubType =(Vector)session.getAttribute("objSesdispSubType");
					if(dispSubType!=null && dispSubType.size()!=0){
						String subTypeId ="";
						String subTypeName ="";
						String mediaSubId ="";
						String subTypeDesc ="";
						String subTypeStatus ="";
					Enumeration dispSubTypeEnum = dispSubType.elements();
					while(dispSubTypeEnum.hasMoreElements()){
						String sDispSubType[] = (String[])dispSubTypeEnum.nextElement();
						subTypeId =sDispSubType[0];
						subTypeName =sDispSubType[1];
						displayId =sDispSubType[2];
						subTypeDesc =sDispSubType[3];
						subTypeStatus =sDispSubType[4];
		  %>
		  
	<tr>
 		<form name="frmAdvDispSubType"  method="post" action="advertisement.do">	
				<input type="hidden" name="mediaId" value="<%=mId%>">	
				<input type="hidden" name="advProcess" value="advManupDispSubType">
				<input type="hidden" name="subTypeId" value="<%=subTypeId%>" />
			<td height="25" class="listCellBg"><%=subTypeName%></td>
			<td class="listCellBg"><input type="submit" name="advButtonDispSub" value="Edit" class="oneBtn" /></td>
			<td class="listCellBg"><input type="submit" name="advButtonDispSub" value="Delete" class="twoBtn" /></td>
	 	</form>
	</tr>
	<%}	
 	}
else {
%>
<tr>
<th height="24" colspan="4" class="alignCenter">No records are available. </th>
</tr>
<%}%>

<!--
<tr>
	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
		<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
</tr>
	-->	  
	<tr>
		<td height="20" colspan="3">&nbsp;</td>
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