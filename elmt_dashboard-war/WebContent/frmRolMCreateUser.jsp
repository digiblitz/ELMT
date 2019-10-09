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
<link href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/ddlevelsmenu-base.css" />
<link rel="stylesheet" type="text/css" href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/ddlevelsmenu-topbar.css" />
<link rel="stylesheet" type="text/css" href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/ddlevelsmenu-sidebar.css" />


<link rel="stylesheet" type="text/css" href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/EAframe.css" />
<link rel="stylesheet" type="text/css" href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/Usermenu.css" />
<link rel="stylesheet" type="text/css" href="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/css/table.css" />


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
	 <td height="330">
	 <table width="165" height="330" border="0" cellpadding="0" cellspacing="0" class="text3" valign="top">
       <tr>
         <td width="165" height="330" valign="top">
		 <!-- code for side bar starts here -->
	   <%@ include file = "ADV_UserMgmtLeft.html" %>
	     <!-- code for side bar starts here -->		 
		 </td>
       </tr>
     </table></td>
  </tr>
 </table>   </td>
   
 <td width="50%">
  <table height="330" width="100%" valign="top">
   <tr>
    <td align="center">
	<table bgcolor="#999999" width="332" height="200" cellpadding="1" cellspacing="0" align="center">
	  <tr>
	   <td>
                <form action="../../../../jboss-4.0.4.GA/server/default/tmp/deploy/tmp8074ied_mfg.ear-contents/ied_mfg-war-exp.war/jsp/roles/roles.do">
                    <input type="hidden" name="roleProcess" value="creatUser">
	    <table bgcolor="#FFFFFF" width="330" height="247" valign="top">
		 <tr>
		  <td width="330" class="text8" colspan="2">User Registration</td>
         </tr>
		 <tr>
          <td width="130" height="30" class="text9" > Username </td>
		  <td width="200"><input type="text" name="userId" size="20" style="width:120px"/>		  </td>
		 </tr>
		 <tr>
          <td width="130" height="57" class="text9" > Password </td>
          <td width="200"><input type="password" name="password" size="20" style="width:120px"/>		  </td>



		
         <tr>
		  <td width="330" height="30" >
		  <input type="submit" value="Submit" name="Submit" class="text1"/> </td>
          <td><input type="reset" value="Cancel" class="text1"name="undo"/>		   </td>
		  </tr>
		  <tr>
		   <td class="text4" colspan="2">&nbsp;		   </td>
		  </tr>
		</table>	
             </form>
                 </td>
	  </tr>
	 </table>
 </td>
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