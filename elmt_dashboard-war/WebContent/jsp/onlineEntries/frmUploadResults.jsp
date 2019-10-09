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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/uploadHorseComp.js" type="text/javascript" ></script>

<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				<tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Membership: <span class="styleBoldTwo">Upload Results</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><br />
						
					You can upload your competition result excel files here . 
					<br />
					<br />
					<span class="styleBoldOne">Please make sure weather the source document is formatted as per the standards before uploading.</span>
					</td>
				  </tr>
				<tr>
				<td>
			
		 <html:form method="post" action="/upload" enctype="multipart/form-data" onsubmit="return myValidate();">
         
				<input type="hidden" name="cmd" value="upload" />
				<input type="hidden" name="eventId" value="<%=(String)session.getAttribute("eventId")%>" />
				
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			  		
								<%
									Vector eventDetail = new Vector();
									eventDetail = (Vector) request.getAttribute("HLCEventDetails");
								%>
									
								<tr>
									 <td class="tableLeft">Event Type:</td>
									 <td class="tableRight">
									 <select name="eventType" id="eventType" class="selectboxOne">
										<option value="" selected="selected">Select One</option>
										<%
											if(eventDetail!=null && eventDetail.size()!=0){
												Enumeration itr= (Enumeration)eventDetail.elements();
												while(itr.hasMoreElements()){
													String[] s = (String[]) itr.nextElement();
													String typeId = s[0];
													String typeName = s[1];
										%>
										<option value="<%=typeId%>"><%=typeName%></option>
										<%}}%>
									 </select>
								 </td>
								  </tr>
					
								 <tr>
									 <td class="tableLeft">Event Id:</td>
									 <td class="tableRight">
									    <input type="text" name="upEventId" id="upEventId" class="textboxOne" size="18" />
								        
									 </td>
								  </tr>
								  <tr>
									 <td class="tableLeft">Event Name:</td>
									 <td class="tableRight">
										<input type="text" name="eventName" id="eventName" class="textboxOne" size="18" />	
										
									 </td>
								  </tr>
								  <tr>
									 <td class="tableLeft">Competition Result File:</td>
									 <td class="tableRight">
										<html:file  property="fileUpload" styleClass="textboxOne" size="18" />	
										<span class="asterisk">*</span>
									 </td>
								  </tr>
								  <tr>
									<td class="tableLeft">&nbsp;</td>
									<td class="tableRight">
									  <input type="submit" value="Upload" class="gradBtn"/>
									</td>
								 </tr>						  
					</table>
					
			  </html:form> 
			  
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
