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
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script>
function fire(){
  window.location="groupAction.do?method=initSearch";
  return;
}
function myvalidate(){
	if((document.groupForm.groupName.value=="") || (document.groupForm.groupName.value.indexOf(' ')==0)){
		alert("Group Name Cannot Be Empty");
		document.groupForm.groupName.focus();
		return false;	
	}
}
</script>
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<html:form method="post" action="/groupAction" onsubmit="return myvalidate();">
				<input type="hidden" name="method" value="edit"/>
				<input type="hidden" name="groupId" value='<bean:write name="groupForm" property="groupId"/>'/>
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">Edit Groups</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
					  You can edit the details of this Group started by you right here!
					  </td>
				  </tr>
				  <tr>
						<td>
						
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									<td class="tblRowHead">
									  Edit/Update This Group
									</td>
								 </tr>
								  
								  <tr>
									<td>
									
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
										  <tr>
											<th width="42%" height="25" class="tableLeft">Group Name</th>
											<td width="30%" class="tableRight"><html:text property="groupName" 
											styleClass="textboxOne" size="45"/>&nbsp;<span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<th height="25" class="tableLeft">Group Category</th>
											<th class="tableRight"><bean:write name="groupForm" property="categoryName"/></th>
										  </tr>
										  <tr>
											<th height="25" class="tableLeft">Choose A Group Type</th>
											<th class="tableRight"><bean:write name="groupForm" property="groupType"/></th>
										  </tr>
										  <tr>
											<th height="25" class="tableLeftTxtArea">Describe Your Group </th>
											<td class="tableRight"><html:textarea property="groupDesc" rows="5" styleClass="textAreaOne"></html:textarea></td>
											</tr>
										</table>					</td>
								  </tr>
								 
								  <tr>
									<th colspan="2" height="35" class="alignCenter">
									<input type="submit" value="Update" class="gradBtn" />
									<input type="button" name="cancel" value="Cancel" class="gradBtn" onClick="javascript:history.back(-1)"/>				</th>
								  </tr>
								</table>
							</td>
						</tr>
					   <tr>
						<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
