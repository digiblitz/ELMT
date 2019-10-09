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
<bean:define id="groupDetails" name="groupForm" scope="request"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="../../javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />

<script>
function fire(paramValue){
	if(paramValue=='Invite'){
	   document.gromCreate.action="inviteAction.do";
	   document.gromCreate.submit();
	  return; 
	} else {
		 document.gromCreate.action="groupAction.do";
		 document.gromCreate.groupName.value="";
		 document.gromCreate.submit();
	 return;
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<form name="gromCreate">
                 <input type="hidden" name="method" value="initAdd"/>

				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">Confirmation.</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><br />
					  <strong>You have successfully created a Networking Group</strong><br /> Your Group information
					  are as follows:
				    </td>
				  </tr>
				  <tr>
					<td colspan="2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td colspan="2" class="tblRowHead">Your Group Information</td>
						</tr>
						<input type="hidden" name="groupId" value='<bean:write name="groupDetails" property="groupId"/>'/>
	                    <input type="hidden" name="groupName" value='<bean:write name="groupDetails" property="groupName"/>'/>

					  <!-- this should taken from the value action class-->					 
					  <tr>
						<td class="tableLeft">Group Name: </td>
						<th class="tableRight"><bean:write name="groupDetails" property="groupName"/></th> 
					  </tr>
					  <tr>
						<td class="tableLeft">Group Type:  </td>
						<th class="tableRight"><bean:write name="groupDetails" property="groupType"/></th>
					  </tr>
					  <tr>
						<td colspan="2" height="30" class="alignCenter">
						<input name="Button" type="button" class="gradBtn" value="Invite People" onClick="fire('Invite');" /> 
						<input name="Button" type="button" class="gradBtn" value="Create Another HLCGroup" 
						onclick="fire('HLCGroup')" style="width:155px;" />
						</td>
					  </tr>
					</table></td>
				  </tr>
				  <tr>
					<td colspan="2">&nbsp;</td>
				  </tr>
				</table>
				</form>

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
