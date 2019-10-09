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
<%@ page import ="com.hlccommon.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvUsrPricematrixList.jsp
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
		document.frmCreateContactTypeList.advPrice.value = "mediaPriceLst";
		document.frmCreateContactTypeList.method="post";
		document.frmCreateContactTypeList.action="AdvertisePrice.do?mid="+mid;
		document.frmCreateContactTypeList.submit();
	}
else{
	document.frmCreateContactTypeList.advPrice.value = "userView";
	document.frmCreateContactTypeList.method="post";
	document.frmCreateContactTypeList.action="AdvertisePrice.do"
	document.frmCreateContactTypeList.submit();
	
}
	}
function showData(dispId){
//alert(dispId);
if(dispId!=""){
	var mid = document.frmCreateContactTypeList.selMediaType.value;
	
	document.frmCreateContactTypeList.advPrice.value = "searchFreq";
	document.frmCreateContactTypeList.method="post";
	document.frmCreateContactTypeList.action="AdvertisePrice.do?dispId="+dispId + "&mid="+mid;
	document.frmCreateContactTypeList.submit();
	}
}	

 
</script>
</head>

<body>
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
	Advertisement: <span class="styleBoldTwo">Advertisement Price Matrix </span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The Advertisement <strong>Price Matrices</strong> are listed as follows. <br />
		<br />
		Select a <strong>Media Type</strong> and it's corresponding <strong>Advertisement Type</strong> to list out all 
		price matrices for various frequencies related to each <strong>Avertisement Sub-Type</strong>.
	</td>
  </tr>
 
 <tr>
 	<td>
		<form name="frmCreateContactTypeList" id="frmCreateContactTypeList">
		<input type="hidden" name="advPrice" id="advPrice" value="advPrice">
		 <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
		  <tr>
		   	<td colspan="4" class="tableSpan">
			
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
					  <th width="75" height="20" class="alignLeft">Media Type: </th>
					  <th width="133" class="alignLeft">
                        <select name="selMediaType" id="selMediaType"  class="selectboxOne" onchange="postData(this.value);">
						<option selected="selected" value="">Select One</option>
						<%
						
						
							Vector vMedia = (Vector)session.getAttribute("DisplayMediaDetails");
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
							</select>	</th>
					  <th width="59" class="alignLeft">Adv Type:</span></th>
					  <th width="215" class="alignLeft">
					 <select name="selDisplayType" id="selDisplayType" class="selectboxOne" onchange="showData(this.value);">
						<option selected="selected" value="">Select One</option>						
					<%
							Vector vType = (Vector)session.getAttribute("DisplayTypeDetails");
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
					  </select>		</th>
			      </tr>
				</table>			</td>
		   </tr>
		   <!--
		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>
		   -->
		   
		     <tr>
			   <td>
			   		  <table width="100%" border="0" cellspacing="1" cellpadding="0">
		   <%
		  ArrayList result =(ArrayList)session.getAttribute("FrequencyPriceDetails");
            if(result!=null && result.size()!=0){
				String freq = "";
				String price = "";	
				String dispSubTypeId = "";
				String dispSubType = "";
                for (Iterator it = result.iterator(); it.hasNext();) {
                     String[] subType = (String []) it.next();
					 dispSubTypeId = subType[0];
                     dispSubType = subType[1];
                    //out.print("===============================");
                  
		   %>
		   
		 
						  <tr>
								<td colspan="2" class="tblMainHead">
								
					Subtype Name:<a href="AdvertisePrice.do?advPrice=freqDet&mediaId=<%=mId%>&subTypeId=<%=dispSubTypeId%>" class="linkFour"><%=dispSubType%></a> </td>
						   </tr>
						   
						    <tr>
							<td height="26" bgcolor="#E3E1D2" >Frequency Rate</td>
							<td height="26" bgcolor="#E3E1D2" >Frequency Price</td>
						  </tr>
						   
						   <%
						     ArrayList  priceList = (ArrayList) it.next();
								for (Iterator itP = priceList.iterator(); itP.hasNext();) {
									 String fqArray[] = (String []) itP.next();
									
										 freq = fqArray[0];
										 price = fqArray[1];
							 
						   %>
						  <tr>
							<td height="26" bgcolor="#E3E1D2" ><%=freq%></td>
							<td height="26" bgcolor="#E3E1D2" ><%=price%></td>
						  </tr>
						
					
					    <%  
                     }
                }
				}
				else{
				%>
				         <tr>
							<td colspan="2" align="center" ><strong>No Records were found</strong></td>
							
						  </tr>
<%                
            }    %>  
			  </table>
			   </td>
		   </tr>
		 
		   <tr>
		   <td>&nbsp;</td>
		   </tr>
		   <!--
		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
		   -->
		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
           </tr>
	  </table>
	  </form>
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
