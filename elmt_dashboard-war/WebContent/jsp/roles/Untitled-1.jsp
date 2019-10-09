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
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
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




<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<script language="javascript">

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
	if(document.frmRoleEnt.userId.value==""){
		alert("Select any one User.");
		document.frmRoleEnt.userId.focus();
		return false;
	}
	DelAll();
	return true;
}
	
	
function postData(){
	if(frmRoleEntSelect.userId.value!=""){
		frmRoleEntSelect.roleProcess.value = "userRoleSelect";
		//alert(frmRewalList.eventProcess.value);
		frmRoleEntSelect.method="post";
		frmRoleEntSelect.action="roles.do";
		frmRoleEntSelect.submit();
	}
	else{
		alert("Select any one User");
		frmRoleEntSelect.userId.focus();
	}
}

</script>
<body>

<!-- Code starts for setting width and border of Web Page -->
<table align="center" width="100%" cellpadding="0" bgcolor="#CCCCCC">
<tr>
<td>
<table align="center"  width="950" bgcolor="#FFFFFF"  >
<tr>
<td>
<!-- Code Ends -->

<%
	String roleRoleId = (String) request.getAttribute("userId");
	if(roleRoleId==null){
		roleRoleId = "";
	}
	//String privId = "";

%>
<table width="950" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
<%@ include file = "ADV_header.jsp" %> 
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
  <td align="right" bgcolor="#ffffff" class="text">&nbsp;
  </td>
 </tr>
  <tr>
    <td bgcolor="#eeeeee" class="bg" height="25">
	 <span class="text8">Admin &gt; Mapping Management &gt; Mapping Roles to User </span></td>
  </tr>
  
  
    <td class="tableCommonBg">
		
		<table width="950" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad" valign="top">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "ADV_MappingRoleLeftBar.html" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead">
						MFG Roles &amp; Privileges: <span class="styleBoldTwo"> Confirmation</span>
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><br />
					  <strong>You have successfully Assigned a role to the member! </strong><br />
					  <br /></td>
				  </tr>
				  <tr>
					<td colspan="2">&nbsp;</td>
				  </tr>
				  <tr>
					<td colspan="2">&nbsp;</td>
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
		
		<!-- FOOTER ENDS HERE -->
	</td>
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