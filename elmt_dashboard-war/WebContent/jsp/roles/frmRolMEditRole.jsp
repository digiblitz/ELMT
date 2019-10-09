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
<%@ page import="java.lang.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeEduActRecap.js" type="text/javascript" ></script>
<script src="javascripts/frmrRoleMEditValidate.js" type="text/javascript" ></script>
<!--!Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
<script src="javascripts/frmrRolePrevilageValidate.js" type="text/javascript" ></script>
<!--!End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<!--!Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
<script>
    function cancelEditRole()
    {
        if(confirm("Do you want to Cancel and go back to List Page?"))
	{
        strURL = "./roles.do?roleProcess=roleList";
	window.location.href = strURL;
        }
       else
	{
		return;
	}
    }
	function clearField(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			obj.roledesc.value = "";
		}

		if(obj.elements[i].type=='radio')
		{
			obj.elements[i].checked = false;
		}
	}
}

</script>
<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
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
                      <!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                      <%--<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->

<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>--%>
                    <!--Ends:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="1" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2"  class="tblMainHead">
						 Maintain Roles: <span  class="styleBoldTwo">Edit </span></td>

				  </tr>
				 
				  <tr>
					<td colspan="2" class="tblDescrp">
					<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->You can <strong>Edit/Update</strong> the <strong> Role</strong> created by you as given below. <br />
								</td>
				  </tr>

				  <tr>
					<td>
                                            <%--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011--%>
					  <%
							String roleId = "";
							String roleName = "";
                                                        String roledesc = "";
                                                         String status = "";

							String[] s = (String[])request.getAttribute("roleDetails");
							System.out.print("Role Details:" + s);
							if(s!=null){
								roleId = s[0];
								roleName = s[1];
                                                                roledesc= s[2];
                                                                status =s[3];
							}
						%>

                      <!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
				<!--<form name="frmRoleMgtRoleEdit" id="frmRoleMgtRoleEdit" method="post" action="roles.do" onsubmit=" return myvalidate();">-->
				<form name="frmRoleMgtRoleEdit" id="frmRoleMgtRoleEdit" method="post" action="roles.do" onsubmit="return formValidate(this);">
                      <!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
						<input type="hidden" name="roleProcess"	value="editRole"/>
						<input type="hidden" name="roleId" value="<%=roleId%>"/>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

						    
                                                    <tr>
							<td  colspan="2" class="tblDescrp">
								Fields marked with an asterisk (<span class="asterisk">*</span>) are required.
							</td>
						</tr>
						<%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
						  %>
						  <tr>
							<td class="styleError" colspan="2"><strong>Role already exist</strong></td>
 						  </tr>
						  <%
							  roleName=(String)request.getAttribute("rolename");
						  }
				         %>
							<tr>
							<td class="tableLeft"> Role:</td>
							<td class="tableRight">
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<!--<input name="rolename" id="rolename" type="text" class="textboxOne" value="<%=roleName%>" size="25" /></td>-->
							<input name="rolename" id="rolename" type="text" class="readOnly" value="<%=roleName%>" size="25" maxlength = "100" readOnly="true"/>  <!--<span class="asterisk">*</span>--></td>
							<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							</tr>
                                                        <%--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011--%>
                                                    <tr>
                                                             <td class="tableLeft">Description:</td>
						<td class="tableRight">
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<!--<input name="roledesc" id="roledesc" type="text" class="textboxOne" value="<%=roledesc%>" size="25" /></td>-->
							<input name="roledesc" id="roledesc" type="text" class="textboxOne" value="<%=roledesc%>" size="25" maxlength = "100"/>  <span class="asterisk">*</span></td>
							<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							</tr>
                                                                  <tr>
                                               	<td class="tableLeft">Status:</td>
							 <%

                                                                        if(Integer.parseInt(status)==1)
                                                                        {
                                                                            %>
                                                                            <td  width="40" class="tableRight" ><input type="radio" name="status" id="status1" value="1" checked="true"  />Active
                                                                            <input type="radio" name="status" id="status2" value="0" />Inactive  <span class="asterisk">*</span></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td width="40" class="tableRight" ><input type="radio" name="status" id="status1" value="1" />Active
                                                                            <input type="radio" name="status" id="status2" value="0" checked="true"/>Inactive  <span class="asterisk">*</span></td>
                                                                              <% } %>
                                                                  </tr>
                                                                              <%--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011--%>
						  <tr>
						   <!--<td class="tableLeft"> &nbsp</td>-->
							<td colspan="2" style="text-align:center" height="25">
							<input type="submit" value="Update" class="gradBtn" />&nbsp;
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearField(this.form)"/>&nbsp;
							<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
						    <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelEditRole()"/></td>
						  </tr>
						 
						</table>
						</form>
					</td>
				  </tr>
                  <tr>
							<td height="20">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
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


�

</body>
</html>