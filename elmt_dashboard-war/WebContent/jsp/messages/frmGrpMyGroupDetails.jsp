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
<bean:define id="groupDetails" name="myMessageForm" property="groupMessage" />
<%
  String noMsg = "1";
%>
<logic:present name="myMessageForm" property="postedMsgs" >
    <bean:size id="size" name="myMessageForm" property="postedMsgs" />
	<% noMsg = String.valueOf(((Integer)size).intValue()+1);%>
</logic:present>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<script>
function fire(){
  window.location="groupAction.do?method=initAdd";
  return;
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
				<form name="postGroupForm" action="postAction.do" method="post">
				<input type="hidden" name="parentMsgId" value='<bean:write name="groupDetails" property="messageId"/>'/>  
				<input type="hidden" name="method" value="initAdd"/>
				<input type="hidden" name="groupName" value='<bean:write name="groupDetails" property="groupName"/>'/>  

				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">My Group Details</span></td>
				  </tr>
				  <tr>
						<td colspan="2" class="tblDescrp"><!--<img src="images/usea_logo.jpg" width="100" height="99" />--></td>
				  </tr>
			
				  <tr>
						<td colspan="2" height="25" class="tblRowHead">Welcome To <span class="styleBoldOne">
						<bean:write name="groupDetails" property="groupName"/>
						</span></td>
				  </tr>
				  <tr>
						<td height="25" class="tblMainHead">Group Type: <span class="styleBoldTwo">
						<bean:write name="groupDetails" property="groupType"/></span>
						</td>
						<td class="tblMainHead">
							<div class="alignRight"><input name="Submit" type="button" class="gradTwoBtn" value="Join This Group" />&nbsp;&nbsp;</div>
						</td>
				  </tr>
				  <tr>
						<td colspan="2">
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								   <tr>
										<td width="65%" height="30" class="tableLeft">Activities within <strong>7</strong> days: </td>
										<th width="35%" class="tableRight"><span class="styleBoldOne">
										</span><%=noMsg%> Messages</th>
								  </tr>
								  <tr>
										<td colspan="2" class="tblMainHead"> Description:</td>
								  </tr>
								   <tr>
										<td colspan="2" class="tblDescrp">
														 
										</td>
								  </tr>
								  <tr>
										<td colspan="2" class="tblMainHead"> Posted Message:</td>
								  </tr>
								   <tr>
										<td colspan="2" class="tblDescrp">
		                                    <bean:write name="groupDetails" property="messageDesc"/>
										</td>
								  </tr>
							</table>
						</td>
				  </tr>
			
				  <tr>
						<td colspan="2" class="alignLeft">
							
							<table width="100%" border="0" cellspacing="1" cellpadding="5">
								  <tr class="tblRowHead">
									<td colspan="3">Messages &amp; Replies </td>
								  </tr>
								  <!--tr>
									<td colspan="5" class="tableRight">
										<strong>View Message(s): </strong> <span class="styleBoldTwo">1 - 10</span>&nbsp;
									  <a href="#" class="linkFour">Prev</a> | <a href="#" class="linkFour">Next</a>	
									</td>
								  </tr-->
						<logic:present name="myMessageForm" property="postedMsgs" >
							 <bean:size id="size" name="myMessageForm" property="postedMsgs" />
							 <logic:equal name="size" value="0">
									<tr>
									   <td colspan="5"  align="center"><B>No Records Found</B></td>
									</tr>
							 </logic:equal>                 
							 <logic:greaterThan name="size" value="0">
								<logic:iterate id="posetdMessage" name="myMessageForm" property="postedMsgs" >

								 <tr class="listCellBg">
								<td colspan="2" valign="top">
								<span  class="styleBoldWel">Posted By:</span><br /><bean:write name="posetdMessage" property="postedBy"/><br /><br />  																
								</td>
								<td width="78%" valign="top"><br /><bean:write name="posetdMessage" property="messageDesc"/>
								  <br />
								  <strong>Posted date:</strong> <bean:write name="posetdMessage" property="addDate"/></td>
							  </tr>
							</logic:iterate>                    
						   </logic:greaterThan>     
						</logic:present>
								
								  <!--tr>
									<td colspan="5" class="tableRight">
										<strong>View Message(s): </strong> <span class="styleBoldTwo">1 - 10</span>&nbsp;
									  <a href="#" class="linkFour">Prev</a> | <a href="#" class="linkFour">Next</a>	
									</td>
								  </tr-->
						  </table>
						</td>
					</tr>  
					<tr>
						<td colspan="2" height="35" class="tableRight">
							<input name="Submit" type="submit" class="gradBtn" value="Post A Reply" /> 
							<input name="Submit" type="button" class="gradBtn" onClick="location.href='groupAction.do?method=initAdd'" value="My Groups &laquo" />
						</td>
				   </tr>
				   <tr>
					<td colspan="2" height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
				  </tr>
				</table>
			<!-- CONTENTS END HERE -->	
			</form>
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
