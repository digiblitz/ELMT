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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Business Service Center</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="css/ddlevelsmenu-sidebar.css" />


<link rel="stylesheet" type="text/css" href="css/EAframe.css" />
<link rel="stylesheet" type="text/css" href="css/Usermenu.css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />


</head>

<body>
<!-- Code starts for setting width and border of Web Page -->
<table align="center" width="100%" cellpadding="0" bgcolor="#CCCCCC">
<tr>
<td>
<table align="center"  width="950" bgcolor="#FFFFFF"  >
<tr>
<td>
<!-- Code Ends -->

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
 <tr>
 <td>
 <!-- header starts here -->
<%@ include file="ADV_header.jsp" %> <!-- header ends here --> 
 </td> 
 </tr>
 
  
  <tr>
  <td align="right" bgcolor="#ffffff" class="text">&nbsp;  </td>
 </tr>
  <tr>
    <td bgcolor="#eeeeee" class="bg" height="25">
	 <span class="text8">Admin &gt; Role Management &gt; Edit Roles</span>	</td>
  </tr>
 
 <tr>
  <td valign="top">
   <table width="100%" align="center" height="350">
    <tr>
	 <td width="50%">
	 <table width="100%">
	 <tr>
	 <td height="330"><table width="165" height="330" border="0" cellpadding="0" cellspacing="0" class="text3" valign="top">
       <tr>
         <td width="165" height="330" valign="top">
		 <!-- code for side bar starts here -->
	   <%@ include file = "ADV_RoleMgmtLeftbar.html" %>
	     <!-- code for side bar starts here -->		 </td>
       </tr>
     </table></td>
  </tr>
 </table>   </td>
   
 <td width="50%">
  <table height="330" width="100%" valign="top">
   <tr>
    <td align="center">
 <table bgcolor="#999999" width="332" height="262" cellpadding="1" cellspacing="0" valign="top">
	  <tr>
	   <td>
	    <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead">
						MFG Roles &amp; Privileges: <span class="styleBoldTwo"> Edit Roles </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
					<img src="images/usea_logo.jpg" class="imgFloatLeft" />
					You can <strong>Edit/Update</strong> the <strong> Role</strong> created by you as given below. <br />
					<br />					</td>
				  </tr>
				  <tr>
					<td>
					  <%  
							String roleId = "";
							String roleName = ""; 
							String[] s = (String[])request.getAttribute("roleDetails");
							//out.print("Role Details:" + s);
							if(s!=null){
								roleId = s[0];
								roleName = s[1]; 
							}
						%>
					
				<form name="frmRoleMgtRoleEdit" id="frmRoleMgtRoleEdit" method="post" action="roles.do" onsubmit="return myvalidate();">
						<input type="hidden" name="roleProcess"	value="editRole"/>	
						<input type="hidden" name="roleId" value="<%=roleId%>"/>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Edit This Role: </td>
						  </tr>
						    <%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
						  %>
						  <tr> 
							<td colspan="2" class="tableLeft">This Role Name Already Exist</td>
 						  </tr>
						  <%
						  }
						  %>
								
							<tr> 
							<td class="tableLeft"> Role Name:</td>
							<td class="tableRight">
							<input name="rolename" id="rolename" type="text" class="textboxOne" value="<%=roleName%>" size="25" /></td>
							</tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							<input type="submit" value="Update" class="gradBtn" />
						    <input name="button" type="reset" class="gradBtn" value="Cancel" /></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>
						</form>
					</td>
				  </tr>
				  
				 
				</table>		</td> 
	  </tr>
	 </table>  </td>
 </tr>
</table>	</td>
   </table>  </td>
  </tr>
	 </table>  
	  
  <!-- Code starts for setting width and border of Web Page -->
</td>
</tr>
</table>
</td>
</tr>
</table>
<!-- Code Ends -->

 

</body>
</html>
