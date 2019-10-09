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

function checkAll() {
     el = document.forms['frmRoleUserMap'].elements;
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
	var chkValue = "";var chk="";
	e = document.forms['frmRoleUserMap'].elements;
	var count =0;
        //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
        if(e.length!=undefined && e.length!='undefined' && e.length > 1)
	{
        //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
            for(i=0 ; i< e.length; i++){
		//Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
                //if(e[i].type == "checkbox"){
                  if(e[i].type == "radio" && e[i].name == "role"){
                //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
                                 //chkValue +=  e[i].value + "#";chk+=e[i]+",";
                                 chkValue +=  e[i].value;
                                 //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011

			}
		}
            }
        //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
        }
        //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
		document.frmRoleUserMap.roleIds.value = chkValue;
                 //Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
                 //document.frmRoleUserMap.roleIdValue.value = chk;
                 //End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011
}


function cancelEditRole()
    {
        strURL = "./roles.do?roleProcess=roleList";
	window.location.href = strURL;
    }

</script>
<body>

<%
	
	String userId = (String) request.getAttribute("userId");
	
	String usrCrit = (String) request.getAttribute("usrCrit");
	if(userId==null){
		userId = "";
	}
	
ArrayList listContact = (ArrayList) request.getAttribute("userContactDetails");
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null)
				middle_name = "";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null)
				address2 = "";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null)
				fax_no = "";
			}
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                    <td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
                    <!--Ends:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						 Roles &amp; Privileges: <span class="styleBoldTwo"> User Role Mapping </span>					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->Check the required <strong>'Role'</strong> to be assigned to a selected <strong>'User</strong>'.
                   </td>
				  </tr>
				  <tr>
					<td>
					<%String empScreen = (String)request.getAttribute("empMapScr");%>
					
					<form name="frmRoleUserMap" id="frmRoleUserMap" action="roles.do" onsubmit="DelAll();">
					<input type="hidden" name="roleProcess" value="mapUserRoles" />
					<input type="hidden" name="roleIds" value="sury" />
					<input type="hidden" name="empScreen" value="<%=empScreen%>"/>
					
                                         <input type="hidden" name="roleIdValue"/>
                                         <input type="hidden" name="userId" value="<%=userId%>" />
                                         <input type="hidden" name="lastName" value="<%=last_name%>" />
					 <input type="hidden" name="firstName" value="<%=first_name%>" />
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="11" class="tblRowHead"> Assign  Role To User: </td>
						  </tr>
						  
						  <tr>
						    <td class="tableLeft">User Name :</td>
						    <td class="tableLeft">
							
								<%=first_name%>&nbsp;<%=last_name%>
						    
							
							</td>
							
							
					      </tr>
						  
						
						  
						  <tr>
						    <td colspan="2" class="tableSpan">
							
								<table width="70%" border="0" cellspacing="1" cellpadding="0" class="tableInner">
								
                                                                   
							<%
									ArrayList arrayRoleList = (ArrayList)request.getAttribute("roleDetails");
									if(arrayRoleList!=null && arrayRoleList.size()!=0){
										Iterator itRoleList = arrayRoleList.iterator();
										while(itRoleList.hasNext()){
											String[] roleList = (String [])itRoleList.next(); //{privilegeId, privilegeName, privilegePath};
											String roleId = roleList[0];
											String roleName = roleList[1];
											boolean roleIdStatus = false;
											ArrayList arrayMapUSerRoleList = (ArrayList)request.getAttribute("userRoleDetails");
											if(arrayMapUSerRoleList!=null && arrayMapUSerRoleList.size()!=0){
												Iterator itUserRoleList = arrayMapUSerRoleList.iterator();
												while(itUserRoleList.hasNext()){
													String[] mapUserRoleList = (String [])itUserRoleList.next(); //{privilegeId, privilegeName, privilegePath};
													String rolId = mapUserRoleList[2];
													if(roleId.equals(rolId)){
														roleIdStatus = true;
														break;
													}
													//{mapPrivilegeId, entityIdVal, privilegeId, entityName, privilegeName};
												}
											}
											if(roleIdStatus==true){
											%>
											<tr> 
                                                                                           <!--Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
                                                                                            <%--<td colspan="2" class="tableLeftTxtArea">
													<input type="checkbox" class="tblMainHead" name="checkbox5" value="<%=roleId%>" checked="checked" />
													<span class="tblMainHead"><%=roleName%></span>
												</td>--%>
												
                         <!--Start: Dhivya Here(Included the checked option)-->                                                               
                                                                                                <td>
                     <input type="radio" name="role" checked="checked" value="<%=roleId%>"/><span class="tblMainHead"><%=roleName%></span>
                                                                                                </td>
																								
							<!--End:Divya Here-->
																								
																								
                                                                                          <!--End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
											</tr>
											 <%
											 }
											 else{
											 %>
											 <tr> 
                                                                                             <!--Start:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->
                                                                                             <%--<td colspan="2" class="tableLeftTxtArea">
													<input type="checkbox" class="tblMainHead" name="checkbox5" value="<%=roleId%>" />
													<span class="tblMainHead"><%=roleName%></span>
												</td>--%>
                                                                                                 <td>
                                                                                                    <input type="radio" name="role" value="<%=roleId%>"/><span class="tblMainHead"><%=roleName%></span>
                                                                                                </td>
                                                                                             <!--End:[RoleMgt-AsgnRole] For Single Role to Single User Assignment changes dated 24-Mar-2011-->

											</tr>
											 <%
											 }
										}
									}
							%>
						  <tr> 
						    	</table>
							
							</td>
					      </tr>
						  
						  <!--tr>
					<td class="tblRowHead" colspan="9">View Points:</td>
					</tr> 
					<tr>
					<td class="tableLeft" colspan="9">User Criteria :
				
								<font color="#FF0000"><strong><%=usrCrit%></strong></font>
					
							</td>
					</tr>
					
							<%
									 ArrayList viewPoint = (ArrayList) request.getAttribute("viewPoint");
									 ArrayList usrViewPnt = (ArrayList) request.getAttribute("usrViewPnt");
									 
          					 if(viewPoint!=null && viewPoint.size()!=0){
							 int j=0;
							Iterator itview = viewPoint.iterator();
							while(itview.hasNext()){
								String[] sview = (String[])itview.next();
								String viewId = sview[0];
								String viewName = sview[1];		
									boolean usrViewStatus=false;
					if(usrViewPnt!=null && usrViewPnt.size()!=0){
							
							Iterator itUsrview = usrViewPnt.iterator();
							while(itUsrview.hasNext()){
								String[] sUsrview = (String[])itUsrview.next();
								String usrViewPntId = sUsrview[0];
								String usrViewPntName = sUsrview[1];
								if(viewId.equals(usrViewPntId)){
														usrViewStatus = true;
														break;
													}
								}
								}
								if(usrViewStatus==true){		
											 %>
											 <tr>
											 <td class="tableRight" colspan="9">
											<input checked="checked" type="checkbox" name="viewPnt<%=j%>" id="viewPnt<%=j%>" value="<%=viewId%>"/><%=viewName%>
										</td>
											</tr>
											<%}else{%>
										 <tr>
											 <td class="tableRight" colspan="9">
											<input type="checkbox" name="viewPnt<%=j%>" id="viewPnt<%=j%>" value="<%=viewId%>"/><%=viewName%>
										</td>
											</tr>	
											
											 <%
											 }
											j++; }
										}
									


							%>
							<input type="hidden" name="viewPntCnt" id="viewPntCnt" value="<%=viewPoint.size()%>">	
							
							<input type="hidden" name="usrCrit" id="usrCrit" value="<%=usrCrit%>">	
							
				  
						  
						  
						  
						  
						  
						  
						  
						  <tr> 
							<td colspan="2" class="alignCenter">
							<input type="submit" value="Assign" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" onClick="history.back();"/></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --><!--/td>
						  </tr!-->
						    <tr> 
							<td colspan="2" class="alignCenter">
							<input type="submit" value="Assign" class="gradBtn" />
						    <input name="button" type="button" class="gradBtn" value="Cancel" onClick="history.back();"/></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
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
