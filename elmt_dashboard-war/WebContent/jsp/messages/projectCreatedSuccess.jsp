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
<%-- 
    Document   : createEditProject
    Created on : Dec 12, 2008, 3:06:51 PM
    Author     : Prateek
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMessageCompose.js" type="text/javascript"></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <!-- <tr>
					<td colspan="2" class="tblMainHead"><strong>Create</strong> Project: <span class="styleBoldTwo">Compose a Message </span></td>
				  </tr> -->
				  <tr>
					
                                            <td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
						<span class="msgHead">Project Created Successfully. </span>
						<br /><br />
						
						
						</td>
                                       
                                          
				  </tr>
						  <tr>
							<td>
							
						
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								 
								   <tr>
									<td height="25" >
										
										<table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
										 
										 <!--
										  <tr class="tblMainHead">
											<td height="25" colspan="2" class="textCommon">&nbsp;
												<a href="#" class="linkFour">Insert Address</a> |
												<a href="#" class="linkFour">Add Cc</a> | 
												<a href="#" class="linkFour">Add BCc</a>
												
											</td>
										  </tr>
										  -->
	
                             
									       <tr>
											<td colspan="2" class="tblRowHead">
											
												<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
												  <tr>
													<td width="11%"  >
         <input type="button" value="Create Another" name="Create"  class="gradTwoBtn" onclick="location.href='project.do'"/>                                                                                            
  <input type="button" value="List All Projects" name="List"  class="gradTwoBtn" onclick="location.href='listProjects.do'"/>  </td>																										 
																										 
																										 
													
												  </tr>
												</table>
											</td>
										  </tr>
									</table>
									</td>
								  </tr>
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
