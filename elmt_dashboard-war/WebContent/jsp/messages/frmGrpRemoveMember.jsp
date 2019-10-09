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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="../../javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />

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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<html:form  method="post" action="/myMsgAction">
				<input type="hidden"name="method" value="delete" >
				 <html:hidden property="groupId"/>

				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				   <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">Remove Members From Group</span></td>
				  </tr>
				  <tr>
						<td colspan="2" class="tblDescrp">
							<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />-->
						  
						</td>
				  </tr>
				  <tr>
						 <td colspan="4">
							 <table width="100%" border="0" cellspacing="0" cellpadding="0">
							   
								   <tr>
										<td class="tblRowHead" colspan="2">Remove Members From This Group</td>
								  </tr>
								   <tr>
										<td colspan="4">
											<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableInner">
											  <tr>
												<th width="50%" height="25" class="alignLeft">&nbsp;Group name: <span class="styleBoldOne"><bean:write name="myMessageForm" property="groupName"/></span> </th>
												<td width="50%" class="alignRight">
													<a href="javascript:history.back(-1);" class="linkFour"> &laquo Back To My Groups </a>
												</td>
											  </tr>
											</table>
										</td>
								  </tr>
								  <tr class="tblDescrp">
										<td colspan="2"><p>Enter the Email address of the member    you would like to remove from the Group.</p></td>
								  </tr>
								  <tr>
										<td class="tableLeft">Enter Email ID: </td>
										<td class="tableRight"><html:text property="emailId" styleClass="textboxOne" size="35" /></td>
									  </tr>
								  <tr>
										<td colspan="2" height="30" class="alignCenter"><input name="submit" type="submit" class="gradBtn" value="Remove Member" /> <input name="Submit2" type="button" class="gradBtn" value="Cancel" /></td>
								  </tr>
								  <tr>
									<td colspan="2" height="20">&nbsp;<!-- DO NOT DELETE THIS ROW --></td>
								  </tr>
							</table>
						</td>
				</tr>
			</table>
             </html:form>

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
