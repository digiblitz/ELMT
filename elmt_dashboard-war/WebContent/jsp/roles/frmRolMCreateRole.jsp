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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeEduActRecap.js" type="text/javascript" ></script>
<script src="javascripts/frmrRolePrevilageValidate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<!--//Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

<script>
    function cancelAddRole()
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
</script>
<!--//End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

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
						 Maintain Roles: <span  class="styleBoldTwo"> Create </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->
					You can <strong>create</strong> a <strong>New Roles</strong> for all members and non-members Online Services Dashboard, right here!

					<br />					</td>
				  </tr>
				  <tr>
					<td>
			<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

						<!--<form name="frmRoleMgtRolePrev" id="frmRoleMgtRolePrev" action="" onsubmit="return myvalidate();">-->
						<form name="frmRoleMgtRolePrev" id="frmRoleMgtRolePrev" action="" onsubmit="return formValidate(this);">
			<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<input type="hidden" name="roleProcess" value="createRole"/>
							
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
						  }
						  %>
						  <tr> 
							<td class="tableLeft"> Role:</td>
							<td class="tableRight">
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<!--<input name="rolename" id="rolename" type="text" class="textboxOne" size="25"/></td>-->
                                                        <input name="rolename" id="rolename" type="text" class="textboxOne" size="25" maxlength = "100"/>  <span class="asterisk">*</span> </td>
							<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

						  </tr>
                                                  <%--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011--%>

                                                  <tr>
							<td class="tableLeft">Description:</td>
							<td class="tableRight">
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<!--<input name="roledesc" id="roledesc" type="text" class="textboxOne" size="25" /></td>-->
							<input name="roledesc" id="roledesc" type="text" class="textboxOne" size="25" maxlength = "100"/>  <span class="asterisk">*</span></td>
							<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->

						  </tr>
                                                  <tr>
							<td class="tableLeft">Status:</td>
							<td td  width="40" class="tableRight" >
			<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
                                                            <input type="radio" name="status" value="1"  id="status1"/>Active <input type="radio" name="status" value="0"  id="status2" />Inactive  <span class="asterisk">*</span></td>
			<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
						  </tr>
                                                  <%--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011--%>
						  <tr><!--<td class="tableLeft"> &nbsp</td>-->
							<td colspan="2" colspan="2" style="text-align:center" height="25"><input type="submit" value="Create" class="gradBtn" />&nbsp;&nbsp;
							<!--Start:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
							<!--End:[RoleMgt] For Role Addition, Editing and Deletion changes dated 10-Mar-2011-->
							<input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelAddRole()" /></td>
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