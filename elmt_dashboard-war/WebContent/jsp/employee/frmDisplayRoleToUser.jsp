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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<script language="javascript">


function callMapEntPri(roleId,uName,roleName,empId,userId){

	strURL = "roles.do?roleProcess=initSupRoleEntPriv&roleId="+roleId+"&uName="+uName+"&roleName="+roleName+"&empId="+empId+"&userId="+userId;
	window.location.href = strURL;
}
function checkAll() {
    el = document.forms['frmRoleEnt'].elements;
    for (i=0; i < el.length; i++) {
      if(el[i].type == "checkbox") {
         if(el[i].checked== true && el[i].value== "ChkAll") {
           //alert("ok");
           for (j=0; j < el.length; j++) {
              if(el[j].type == "checkbox") {
                 void(el[j].checked=1);
              }
           }
           break;
         }
         if(el[i].checked== false && el[i].value== "ChkAll") {
           //alert("uncheck");
           for (j=0; j < el.length; j++) {
              if(el[j].type == "checkbox") {
                 void(el[j].checked=0);
              }
           }
         }   
      }
    }
}

function DelAll(){
	var chkValue = "";
	e = document.forms['frmRoleEnt'].elements;
	var count =0;
	for(i=0 ; i< e.length; i++){
		if(e[i].type == "checkbox"){
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 chkValue +=  e[i].value + "#";
			}
		}
	}
		//alert(chkValue);
		document.frmRoleEnt.entityIds.value = chkValue;
}

function entPrivValidate(){
	if(document.frmRoleEnt.roleId.value==""){
		alert("Select any one Role.");
		document.frmRoleEnt.roleId.focus();
		return false;
	}
	DelAll();
	return true;
}
	

</script>
<body>

<%
	String roleRoleId = (String) request.getAttribute("roleId");
	if(roleRoleId==null){
		roleRoleId = "";
	}
	//String privId = "";

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
		<%
		
        String uName=(String)request.getParameter("uName");       
        String deptName=(String)request.getParameter("deptName");
        String roleName=(String)request.getParameter("roleName");  
        
        String fName=(String)request.getAttribute("fName");
        String lName=(String)request.getAttribute("lName");
        String empId=(String)request.getAttribute("empId"); 
		 String chgStat=(String)request.getAttribute("chgStat");
		   String userId=(String)request.getParameter("userId");
        String roleId1=(String)request.getParameter("roleId");
		 System.out.println("chgStat=="+chgStat);
		 	 if(chgStat==null || chgStat=="")chgStat="";
		 	 if(uName==null || uName=="")uName=(String)request.getAttribute("uName");
		 	 if(roleName==null || roleName=="")roleName=(String)request.getAttribute("roleName");
		 	if(deptName==null || deptName=="")roleName=(String)request.getAttribute("deptName");
        
        %>
		
	     
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
		   	<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						 Roles &amp; Modules: <span class="styleBoldTwo"> Module User Role Mapping </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->
					You can map an<strong> Role </strong> to user as given below. <br />
					<br />
					<br />					</td>
				  </tr>
				  <tr>
					<td>
					
						
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Map Role to User: </td>
						  </tr>
						  
						  <tr>
						    <td class="tableLeft">User Name</td>
						    <td class="tableRight"><span class="alignLeft">					      
							<%=uName%>
						    </span></td>
					      </tr>
					       <tr>
						    <td class="tableLeft">Department Name</td>
						    <td class="tableRight"><span class="alignLeft">					      
							<%=deptName%>
						    </span></td>
					      </tr>
					      <tr>
						    <td class="tableLeft">Role Name</td>
						    <td class="tableRight"><span class="alignLeft">					      
							<%=roleName%>
						    </span></td>
					      </tr>
					      <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
					      <form name="frmRoleEnt" id="frmRoleEnt" onsubmit="return entPrivValidate();">
							<input type="hidden" name="roleProcess" value="mappingRoleEnt" />
							<input type="hidden" name="entityIds" value="sury">
							<input type="hidden" name="roleId" value="<%=roleRoleId%>">
							<input type="hidden" name="empMap" value="empMap">
							<input type="hidden" name="userId" value="<%=userId%>">
							<input type="hidden" name="roleId1" value="<%=roleId1%>">
							<input type="hidden" name="uName" value="<%=uName%>">
							<input type="hidden" name="roleName" value="<%=roleName%>">
							<input type="hidden" name="deptName" value="<%=deptName%>">
							<input type="hidden" name="empId" value="<%=empId%>">
							
						  <tr> 
							<td class="tableLeftTxtArea"> Accessible Entity(ies):</td>
							<td class="tableRight">
							
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td colspan="2">
									<input  type="checkbox" name="chkAll" value="ChkAll" alt="Select or Deselect All" onClick="checkAll();" > Select All 
									</td>
								</tr>
							<%
									ArrayList arrayEntityList = (ArrayList)request.getAttribute("enityDetails");
									if(arrayEntityList!=null && arrayEntityList.size()!=0){
										Iterator itEntList = arrayEntityList.iterator();
										while(itEntList.hasNext()){
											String[] entList = (String [])itEntList.next();
											//String[] entList = {entityId, entityName};
											String roleEntityId = entList[0];
											String roleEntityName = entList[1];
											boolean entIdStatus = false;
											ArrayList arrayMapRoleEntList = (ArrayList)request.getAttribute("mapDetails");
											if(arrayMapRoleEntList!=null && arrayMapRoleEntList.size()!=0){
												Iterator itRoleEntList = arrayMapRoleEntList.iterator();
												while(itRoleEntList.hasNext()){
													String[] mapEntList = (String [])itRoleEntList.next();
													//{mapEntityId, roleIdVal, entityId, roleName, entityName};
													String entId = mapEntList[2];
													if(roleEntityId.equals(entId)){
														entIdStatus = true;
														break;
													}
												}
											}
											if(entIdStatus==true){
											%>
											  <tr> 
												<td colspan="2" class="tableLeftTxtArea">
													<input type="checkbox" name="checkbox5" checked="checked" value="<%=roleEntityId%>"/><%=roleEntityName%>
												</td>
											</tr>
											<%
											 }
											 else{
											 %>
											 <tr> 
												<td colspan="2" class="tableLeftTxtArea">
													<input type="checkbox" name="checkbox5" value="<%=roleEntityId%>" /><%=roleEntityName%>
												</td>
											</tr>
											 <%
											 }
										}
									}
									%>

								</table>
							</td>
						  </tr>
						  <%if(chgStat==""){ %>
						  <tr> 
							<td colspan="2" class="alignCenter">
							
							
							
						    <input name="button" type="button" class="gradBtn" value="Back" onclick="javascript:location.href='SearchList.do?searchProcess=searchEmp'" />
						    <input type="submit" value="Submit" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Next" onclick="javascript:location.href='roles.do?roleProcess=initSupRoleEntPriv&roleId=<%=roleId1%>&uName=<%=uName%>&roleName=<%=roleName%>&userId=<%=userId%>&deptName=<%=deptName%>&empId=<%=empId%>'" />
						    
							
							</td>
						  </tr>
						  <%}else{%>
						  <tr> 
							<td colspan="2" class="alignCenter">
						  <input name="button" type="button" class="gradBtn" value="Proceed" onclick="callMapEntPri('<%=roleId1%>','<%=uName%>','<%=roleName%>','<%=empId%>','<%=userId%>')"/>
						  </td>
						  </tr>
						  <%} %>
						  
						  
						  </form>
						  </table>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
					      						
						
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
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
