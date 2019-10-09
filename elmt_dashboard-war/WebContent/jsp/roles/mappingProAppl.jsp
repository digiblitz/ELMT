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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Business Service Center</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeEduActRecap.js" type="text/javascript" ></script>
<script src="javascripts/frmrRolePrevilageValidate.js" type="text/javascript" ></script>
 <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> 
 <style type="text/css">
<!--
.style1 {
	color: #FF0000;
	font-weight: bold;
}
-->
 </style>
</head>
<%
	String roleRoleId = (String) request.getAttribute("roleId");
	if(roleRoleId==null){
		roleRoleId = "";
	}
	//String privId = "";

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
		<table width="760" border="0" cellpadding="0" cellspacing="0" id="infoBarTab">

			  <tr>
				
				<td class="infoTopPad" width="2%">				</td>
				<td class="infoTopPad" width="90%" height="25"><span class="styleBoldTwo">Admin &gt; Role Management &gt; Map Roles </span></td>
				<td class="infoTopPad" width="8%" align="center">
	</td>
				
			  </tr>
	  </table>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE --></td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						 
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					Please Click here to
					 <span class="tabHead"> Create Role</span>  with respect to<span class="tabHead"> Entity, Previledge, Domain, Process Group, Application Groupe :</span> 
					
					<br />					</td>
				  </tr>
				  <tr>
					<td>
					
						<form name="frmRoleMgtRolePrev" id="frmRoleMgtRolePrev" action="roles.do" onsubmit="return myvalidate();">
							<input type="hidden" name="roleProcess" value="mapRoleEntityPrivDomainProccessApp"/>
							
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="3" class="tblRowHead"> Please select Any One from the dropdown lists below: </td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Role Name :</td>
							<td class="tableRight"><span class="alignLeft">
							  <select name="roleId" id="select" class="selectboxOne" onchange="postData('initSelectRoleEnt');">
                                <option selected="selected" value="">Select One</option>
                                <%
									ArrayList arrayRoleList = (ArrayList)request.getAttribute("roleDetails");
									if(arrayRoleList!=null && arrayRoleList.size()!=0){
										Iterator itRoleList = arrayRoleList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String[])itRoleList.next().toString().split("}");;
											//String[] roleList = {roleId, roleName};
											String rRolId = rRoleList[0];
											String roleRoleName = rRoleList[1];
											
											if(rRolId.equals(roleRoleId)){
											%>
                                <option value="<%=rRolId%>}<%=roleRoleName%>" selected="selected"><%=roleRoleName%></option>
								
                                <%
								
											 }
											 
											 else{
											 %>
                                <option value="<%=rRolId%>}<%=roleRoleName%>"><%=roleRoleName%></option>
                                <%
											 }
										}
									}
							%>
                              </select>
							</span></td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft"> Entity Name :</td>
							<td class="tableRight"><span class="alignLeft">
						    <select name="entityId" id="roleId" class="selectboxOne" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
                                                                            <%
									ArrayList arrayentityList = (ArrayList)request.getAttribute("entityDetails");
									if(arrayentityList!=null && arrayentityList.size()!=0){
										Iterator itRoleList = arrayentityList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String[])itRoleList.next().toString().split("}");;
											//String[] roleList = {roleId, roleName};
											String rEntityId = rRoleList[0];
											String roleEntityName = rRoleList[1];
                                                                                        
											if(rEntityId.equals(roleRoleId)){
											%>
                                <option value="<%=rEntityId%>}<%=roleEntityName%>" selected="selected"><%=roleEntityName%></option>
                                <%
								
								
											 }
											 
											 else{
											 %>
                                <option value="<%=rEntityId%>}<%=roleEntityName%>"><%=roleEntityName%></option>
                                <%
											 }
										}
									}
							%>
                                                         
                                                  </select>
							</span>							</td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft"> Previlege Name :</td>
							<td class="tableRight"><span class="alignLeft">
						    <select name="privilegeId" id="roleId" class="selectboxOne" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
                                                                             <%
									ArrayList arrayprivilegeList = (ArrayList)request.getAttribute("PrivilegeDetails");
									if(arrayprivilegeList!=null && arrayprivilegeList.size()!=0){
										Iterator itRoleList = arrayprivilegeList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String[])itRoleList.next().toString().split("}");;
											//String[] roleList = {roleId, roleName};
											String rPrevilegeId = rRoleList[0];
											String rolePrevilegeName = rRoleList[1];
                                                                                       
											if(rPrevilegeId.equals(roleRoleId)){
											%>
                                <option value="<%=rPrevilegeId%>}<%=rolePrevilegeName%>" selected="selected"><%=rolePrevilegeName%></option>
                                <%
											 }
											 
											 else{
											 %>
                                <option value="<%=rPrevilegeId%>}<%=rolePrevilegeName%>"><%=rolePrevilegeName%></option>
                                <%
											 }
										}
									}
							%>
                                                         
							</select>
							</span>							</td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft"> Domain Name :</td>
							<td class="tableRight"><span class="alignLeft">
						    <select name="domainId" id="roleId" class="selectboxOne" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
                                                         
                                                                                           <%
									ArrayList arraydomainList = (ArrayList)request.getAttribute("domainDetails");
									if(arraydomainList!=null && arraydomainList.size()!=0){
										Iterator itRoleList = arraydomainList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String[])itRoleList.next().toString().split("}");
											//String[] roleList = {roleId, roleName};
											String rDomainId = rRoleList[0];
											String roleDomainName = rRoleList[1];
                                                                                        
                                                                                        
											if(rDomainId.equals(roleRoleId)){
											%>
                                <option value="<%=rDomainId%>}<%=roleDomainName%>" selected="selected"><%=roleDomainName%></option>
                                <%
											 }
											 
											 else{
											 %>
                                <option value="<%=rDomainId%>}<%=roleDomainName%>"><%=roleDomainName%></option>
								
                                <%
											 }
										}
									}
							%> 
                                                         
                                                         
                                                         
							</select>
							</span>							</td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft"> Process Group :</td>
							<td class="tableRight"><span class="alignLeft">
						    <select name="processId" id="roleId" class="selectboxOne" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
                                                                                            <%
									ArrayList arrayprocessList = (ArrayList)request.getAttribute("ProcessDetails");
									if(arrayprocessList!=null && arrayprocessList.size()!=0){
										Iterator itRoleList = arrayprocessList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String[])itRoleList.next().toString().split("}");;
											//String[] roleList = {roleId, roleName};
											String rProcessId = rRoleList[0];
											String roleProcessName = rRoleList[1];
                                                                                      
											if(rProcessId.equals(roleRoleId)){
											%>
                                <option value="<%=rProcessId%>}<%=roleProcessName%>" selected="selected"><%=roleProcessName%></option>
                                <%
											 }
											 
											 else{
											 %>
                                <option value="<%=rProcessId%>}<%=roleProcessName%>"><%=roleProcessName%></option>
                                <%
											 }
										}
									}
							%>
                                                         
							</select>
							</span>							
							</td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft"> Application Group :</td>
							<td class="tableRight"><span class="alignLeft">
						    <select name="applicationId" id="roleId" class="selectboxOne" onchange="postData('initSelectRoleEnt');">
							<option selected="selected" value="">Select One</option>
                                                                                            <%
									ArrayList arrayapplicationList = (ArrayList)request.getAttribute("applicationDetails");
									if(arrayapplicationList!=null && arrayapplicationList.size()!=0){
										Iterator itRoleList = arrayapplicationList.iterator();
										while(itRoleList.hasNext()){
											String[] rRoleList = (String[])itRoleList.next().toString().split("}");;
											//String[] roleList = {roleId, roleName};
											String rApplicationId = rRoleList[0];
											String roleApplicationName = rRoleList[1];
                                                                                        
											if(rApplicationId.equals(roleRoleId)){
											%>
                                <option value="<%=rApplicationId%>}<%=roleApplicationName%>" selected="selected"><%=roleApplicationName%></option>
                                <%
											 }
											 
											 else{
											 %>
                                <option value="<%=rApplicationId%>}<%=roleApplicationName%>"><%=roleApplicationName%></option>
                                <%
											 }
										}
									}
							%>
                                                         
							</select>
							</span>							</td>
						  </tr>
						  
						  <tr> 
							<td colspan="3" class="alignCenter"><input name="submit" type="submit" class="gradBtn" 
							value="Submit" />						    </td>
						  </tr>
						  <tr> 
							<td colspan="3" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>
						</form>
					</td>
				  </tr>
				  
				 
				</table>
			<!-- CONTENTS END HERE -->			</td>
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
