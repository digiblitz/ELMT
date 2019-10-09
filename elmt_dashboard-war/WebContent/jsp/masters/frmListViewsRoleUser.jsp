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
<script language="javascript">
function listUsers(){
	var viewRoleId=document.frmListViewsRoleUser.viewRoleId.value;
	 strURL = "./viewMaster.do?process=viewRoles&viewRoleId="+viewRoleId;
	window.location.href = strURL;
}
function assignViewPoint(userId){
	var viewRoleId=document.frmListViewsRoleUser.viewRoleId.value;
	 strURL = "./viewMaster.do?process=LobViewpointMapping&viewRoleId="+viewRoleId+"&userId="+userId;
	 	window.location.href = strURL;
}
</script>
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
		     String viewRoleIdObj=(String)request.getAttribute("viewRoleId");
		     String viewLobIdObj=(String)request.getAttribute("viewLobId");
		%>
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
		
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table border="1" cellpadding="0" cellspacing="0" cellspacing="5" align="center" class="tblInnerContainer">
     <tr>
    <td colspan="5" class="tblMainHead">
	User LOB Level Map : <span class="styleBoldTwo"> Listings</span></td>
  </tr>

        <tr>
                  <td colspan="5" class="tblDescrp">
                  You are viewing the list of user's mapped with lob based on roles.</td>
</tr>
<tr><td>
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
<form name="frmListViewsRoleUser" id="frmListViewsRoleUser" method="post" action="viewMaster.do">
		<input type="hidden" name="process" value="insertLobViewpointMapping" />
  <tr>
		<td class="tableLeft" >Roles view:</td>
					<td class="tableRight" ><select name="viewRoleId" class="selectboxOne" onchange="listUsers(); ">
                                                <option  selected="selected" value="">Select One</option>

                                                <%
                                                ArrayList viewsRelatedRoleList = (ArrayList)request.getAttribute("viewsRelatedRoleList");
           									 if(viewsRelatedRoleList!=null && viewsRelatedRoleList.size()!=0){
           							Iterator itViewRole = viewsRelatedRoleList.iterator();
           							while(itViewRole.hasNext()){
           								String[] viewRoleList = (String[])itViewRole.next();
           								String role_id = viewRoleList[0];
           								String role_name = viewRoleList[1];
           							   if (role_id.equals(viewRoleIdObj)){
                                                                                            
										%>
										  <option  value="<%=role_id%>" selected="selected" ><%=role_name%></option>
										<%
											}
											else{
											%>
											 <option  value="<%=role_id%>" ><%=role_name%></option>
											<%
											}
										}
           									 }									
								%>
								</select>  <span class="asterisk">*</span></td>
  </tr>
  <tr>
		<td class="tableLeft" >Users :</td>
					<td class="tableRight" ><select name="userId" class="selectboxOne" >
                                                <option  selected="selected" value="">Select One</option>

                                                <%
                                                ArrayList viewRoleUserList = (ArrayList)request.getAttribute("viewRoleUserList");
           									 if(viewRoleUserList!=null && viewRoleUserList.size()!=0){
           							Iterator itViewLob = viewRoleUserList.iterator();
           							while(itViewLob.hasNext()){
           								String[] viewLobList = (String[])itViewLob.next();
           								String user_id = viewLobList[0];
           								String user_name = viewLobList[1];
           							
											%>
											 <option  value="<%=user_id%>" ><%=user_name%></option>
											<%
											
										}
           									 }									
								%>
								</select>  <span class="asterisk">*</span></td>
  </tr>
  <tr>
		<td class="tableLeft" >LOB :</td>
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
           							 
                                                                                            
										%>
										  <option  value="<%=lob_id%>"><%=lob_name%></option>
										<%
										}
           									 }									
								%>
								</select>  <span class="asterisk">*</span></td>
  </tr>
  <table align="center">
  <tr>
		 <td>	<input align="middle" type="submit" value="Submit" class="gradBtn"/> </td></tr>
		 </table>

<!--end of the file-->
</form>


<form name="frmUserLobList" action="">
 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
      <tr>
			
			<td width="103" class="tblRowHeadTwo">View</td>
			<td width="94" class="tblRowHeadTwo">Login Name</td>
			<td width="94" class="tblRowHeadTwo">Mapping LOB</td>
			
		   </tr>
		   <%ArrayList viewUserLobList = (ArrayList)request.getAttribute("viewUserLobList");
           									 if(viewUserLobList!=null && viewUserLobList.size()!=0){
           							Iterator itviewUserLob = viewUserLobList.iterator();
           							while(itviewUserLob.hasNext()){
           								String[] viewRoleUsers = (String[])itviewUserLob.next();
           								String view = viewRoleUsers[0];
           								String usrLogin = viewRoleUsers[1];
           								String mapLob = viewRoleUsers[2];
           								
		   %>
		   <tr>
		   <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=view%></td>
		    <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=usrLogin%></td>
			 <td  height="26" bgcolor="#E3E1D2" class="alignCenter"><%=mapLob%></td>

		   </tr>
   			<%}
           							}else{ %>	
           							<td height="26" colspan="3" bgcolor="#E3E1D2" class="alignCenter"><strong>No Records were Found !</strong></td>
 
           							<%} %>
		 
 </table>

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
