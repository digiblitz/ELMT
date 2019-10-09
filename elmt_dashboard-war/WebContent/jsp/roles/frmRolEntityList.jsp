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
<%@ page import="com.hlccommon.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<link href="css/style.css" type="text/css" rel="stylesheet" /> 
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
	<!-- CENTER TABLE STARTS HERE -->
        
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
		  <tr>
			<td colspan="2" class="cenTablePad">
			<!-- Welcome Tab Starts Here -->	
				
			<!-- Welcome Tab Ends Here -->
			</td>
		  </tr>
		  <tr>
			<td width="400" class="deptTablePad">
			  <table width="396" border="0" align="center" cellpadding="0" cellspacing="0" id="deptTab">
				  <tr>
					<td class="deptTabHead2">&nbsp;</td>
				  </tr>
				  <tr>
				  	<td class="deptTabList2">
					<!-- Department List Starts Here -->
						<table width="396" border="0" cellspacing="0" cellpadding="0">
						
						<%
							ArrayList entityList = (ArrayList)session.getAttribute("DBEntityList");
							if(entityList!=null && entityList.size()!=0){
								Iterator itEntList = entityList.iterator();
								while(itEntList.hasNext()){
									String strRolelist[]= (String[])itEntList.next();
									String roleRoleId = strRolelist[1];
									String roleRoleName = strRolelist[3];
									String roleRntityId = strRolelist[2];
									String roleEntityName = strRolelist[4];
									
 								 // String[] entMapList = {mapEntityId, roleIdVal, entityId, roleName, entityName};
					%>
						   <tr>
							<td class="deptTabListOth2"><a href="roles.do?roleProcess=selectEntPrivePermDashboard&headEntityId=<%=roleRntityId%>&headRoleId=<%=roleRoleId%>" class="linkFour">&raquo; <%=roleEntityName%></a></td>
						  </tr>
						  <%
						  }
						  }
						  else{
						  %>
						   <tr>
							<td class="deptTabListOth2">No Enity has been Assigned for this Role</td>
						  </tr>
						  <%
						  }
						  %>						  
						</table>
					<!-- Department List Ends Here -->
					</td>
				  </tr>
				  <tr>
					<td class="deptTabFoot2">&nbsp;</td>
				  </tr>
			  </table>
			</td>
			<td width="340" class="subDeptTablePadHome">

				<table width="337" border="0" align="center" cellpadding="0" cellspacing="0" id="subDeptTab">
				  <tr>
					<td class="subDeptTabHead">Available Roles and Privileges</td>
				  </tr>
				  <tr>
					<td class="subDeptListBg">
							
					<!-- Sub-Department List Starts Here -->
					<div class="subDeptDiv">
			<table width="320" border="0" align="left" cellpadding="2" cellspacing="1">
				<%
				ArrayList genealRoleList = (ArrayList)session.getAttribute("DBGeneralPrivList");
				if(genealRoleList!=null && genealRoleList.size()!=0){
					Iterator itEntList = genealRoleList.iterator();
						while(itEntList.hasNext()){
							ArrayList privilegeList = (ArrayList)itEntList.next();
							if(privilegeList!=null && privilegeList.size()!=0){
							  Iterator itPrivRoleList = privilegeList.iterator();
							  while(itPrivRoleList.hasNext()){
								 String strPrivilegeList[]= (String[])itPrivRoleList.next();
								  String privilegeName = strPrivilegeList[1];
									%>
								  <tr>
									<td class="subDeptTabList">&bull; <a href="#" class="linkThree"><%=privilegeName%></a></td>
								  </tr>
									 <%
									 ArrayList menuList = (ArrayList) itPrivRoleList.next();
									 Iterator itMenu = menuList.iterator();
											while(itMenu.hasNext()){
												HLCMenuListVO menuVO = (HLCMenuListVO ) itMenu.next();
												if(menuVO.getRoleId()!=null && menuVO.getEntityId()!=null){
													//String PermissionName = menuVO.getPermissionName();
													String accessName = menuVO.getAccessName();
													String accessURL = menuVO.getAccessUrl();	
													if(accessName!=null && accessName.trim().length()!=0){																							
													  %>
													  <tr>
													  <td class="subDeptTabList" style="padding-left:35px;"><a href="<%=accessURL%>" class="linkOne"><%=accessName%></a></td>
													  </tr>
													  <%
													  }
										  		}
											}
									 
									 
									 }
								}
						 	}
						 }
						 else{
						 	%>
							<tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr>
						 <tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr><tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr><tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr><tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr><tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr><tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr><tr>
							<td class="subDeptTabList">&nbsp;</td>
						  </tr>
							<%
						 }
						 %>
						  
					  </table>
					 </div>
					<!-- Sub-Department List Ends Here -->
					
					</td>
				  </tr>
				  <tr>
					<td class="subDeptTabFoot">&nbsp;</td>
				  </tr>
			  </table>

			</td>
		  </tr>
		</table>
	<!-- CENTER TABLE ENDS HERE -->
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
