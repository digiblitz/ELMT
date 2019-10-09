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
<%@ page import="java.util.*;" %>
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
<%@ include file="ADV_header.jsp" %> 
<!-- header ends here --> 
 </td> 
 </tr>
 
  
  <tr>
  <td align="right" bgcolor="#ffffff" class="text">&nbsp;  </td>
 </tr>
  <tr>
    <td bgcolor="#eeeeee" class="bg" height="25">
	 <span class="text8">Admin &gt; User Management &gt; Create User</span>	</td>
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
	     <!-- code for side bar starts here -->		 
		 </td>
       </tr>
     </table></td>
  </tr>
 </table>   </td>
   
 <td width="50%">
  <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
                
                <tr>
                  <td colspan="5" class=""><br />
                  You are viewing the list of <strong>Roles</strong> created by you. To	edit	a	role	click on the <strong>Edit</strong> button beside each record. To deactivate a role click on the <strong>'Deactivate'</strong> button beside the corresponding record. </td>
                </tr>
                <tr>
                  <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                      
                      <tr>
                        <td width="278" height="27" class="tblRowHead">Name of Role </td>
                        <td width="93" colspan="2" class="tblRowHead">Edit</td>
                      </tr>
                      
					   <%  
						 ArrayList roleList = (ArrayList) request.getAttribute("allRoleList");
      					 if(roleList!=null && roleList.size()!=0){
							Iterator it = roleList.iterator();
							while(it.hasNext()){
								String[] s = (String[])it.next();
								String roleId = s[0];
								String roleName = s[1];
		                %>
						<form name="frmRoleMgtListRole" id="frmRoleMgtListRole" action="roles.do" >
						<input type="hidden" name="roleId" value="<%=roleId%>" />
						<input type="hidden" name="roleProcess" value="initeditRole" />
						<tr>
							<td height="26" class="listCellBg"><%=roleName%></td>
							<td colspan="2" class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="Edit" /></td>
							<!--<td class="listCellBg"><input name="Submit2" type="submit" class="twoBtn" value="Deactivate" /></td> -->
        	              </tr>
				    </form>
						<% }
					 }
					  else {%>
							 <tr>
							<td colspan="2" class="tblRowHead"><center>No Records are found</center></td>
							</tr>
					<% } %>
                      
                      	<!--
					  		<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
						   </tr>
						 -->
                      <tr>
                        <td height="19" colspan="5">&nbsp;</td>
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
