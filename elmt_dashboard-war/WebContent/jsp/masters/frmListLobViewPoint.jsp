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
<%@ page import="com.hlcform.util.HLCUserSearchResultVO" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>

<!-- <script src="javascripts/frmSearchPattern.js" type="text/javascript" ></script>
--> <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/search_header.jsp" %>
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
		
		<%
			System.out.print("session.getAttribute(sessionId) in search page :"+session.getAttribute("sessionId")); 
		     String viewLobIdObj=(String)request.getAttribute("viewLobId");
		     String viewRoleIdObj=(String)request.getAttribute("viewRoleId");
		     String viewUserId=(String)request.getAttribute("viewUserId");
		%>
		
		<input type="hidden" name="viewId" value="<%=viewRoleIdObj%>"/>
		<input type="hidden" name="userId" value="<%=viewUserId%>"/>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
<form name="frmListLobViewPoint" id="frmListLobViewPoint" method="post" action="viewMaster.do">
		<input type="hidden" name="process" value="insertLobViewpointMapping" />
  <tr>
		<td class="tableLeft" >:</td>
					<td class="tableRight" ><select name="lobId" class="selectboxOne" >
                                                <option  selected="selected" value="">Select One</option>

                                                <%
                                                ArrayList viewRoleLOBList = (ArrayList)request.getAttribute("viewRoleLOBList");
           									 if(viewRoleLOBList!=null && viewRoleLOBList.size()!=0){
           							Iterator itViewLob = viewRoleLOBList.iterator();
           							while(itViewLob.hasNext()){
           								String[] viewLobList = (String[])itViewLob.next();
           								String lob_id = viewLobList[0];
           								String lob_name = viewLobList[1];
           							   if (lob_id.equals(viewLobIdObj)){
                                                                                            
										%>
										  <option  value="<%=lob_id%>" selected="selected" ><%=lob_name%></option>
										<%
											}
											else{
											%>
											 <option  value="<%=lob_id%>" ><%=lob_name%></option>
											<%
											}
										}
           									 }									
								%>
								</select>  <span class="asterisk">*</span></td>
  </tr>
  
    <tr>
    <td colspan="2" class="tblMainHead">
	 <span class="styleBoldTwo">Views related User List</span>	</td>
  </tr>

<tr>
 	<td>
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		
 		
		
		   <%ArrayList viewRoleViewpointList = (ArrayList)request.getAttribute("viewRoleViewpointList");
           									 if(viewRoleViewpointList!=null && viewRoleViewpointList.size()!=0){
           							Iterator itViewPoint = viewRoleViewpointList.iterator();
           							while(itViewPoint.hasNext()){
           								String[] viewPointList = (String[])itViewPoint.next();
           								String viewPointId = viewPointList[0];
           								String viewPointName = viewPointList[1];
           								
		   %>
		   <tr>
		    <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" name="viewPointId" value="<%=viewPointId%>" /></td>
		    <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=viewPointName%></td>
		   </tr>
   			<%}
           							}else{ %>	
           							<td height="26" colspan="2" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
 
           							<%} %>
		 <tr>
		 <td>	<input align="middle" type="submit" value="Submit" class="gradBtn"/> </td></tr>
	  </table>	</td>
</tr>  

<!--end of the file-->
</form>
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
