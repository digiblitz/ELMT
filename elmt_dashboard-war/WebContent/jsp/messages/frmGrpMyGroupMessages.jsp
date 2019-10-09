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
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">My Messages</span></td>
				  </tr>
				  <tr>
					  <td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
					  </td>
				  </tr>
				  <tr>
					<td colspan="2">
					 <html:form method="post" action="/myMsgAction.do">
					 <input type="hidden" name="method" value="message"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td colspan="2" class="tblRowHead">Messages You Received</td>
						  </tr>
						  <tr>
							<td colspan="2" class="tblDescrp">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   <tr class="alignLeft">
									 <td width="66%" class="alignLeft"><strong>Search:</strong> 
									   <html:text property="subject" styleClass="textboxOne" size="35" />
									 </td>
									 <td width="34%" align="left" class="alignLeft">
										<input name="search" type="submit" class="gradBtn" value="Search" />
									 </td>
								   </tr>
								 </table>
							 </td>
						 </tr>
						 <tr>
						 	 <td class="tableLeft">Group Name:</td>
							 <td class="tableRight">
							<html:select property="groupId" styleId="cat_sel_id" styleClass="selectboxOne" onchange="">
							  <option selected="selected" value="">Select One</option>
							  <html:options collection="GrpName" property="value" labelProperty="label" />
							</html:select>
							 </td>
						 </tr>
						  
						  <tr>
							<td colspan="2" class="alignLeft">								
								<table width="100%" border="0" cellspacing="1" cellpadding="0">
									  <tr class="tblMainHead">
										<td width="60%" height="25">Messages</td>
										<td width="19%">Author</td>
										<td width="21%">Date</td>
									  </tr>
									<logic:present name="myMessageForm" property="result"> 
										<bean:size id="size" name="myMessageForm" property="result"/>
										<logic:equal name="size" value="0">
											<tr>
											   <td colspan="5"  align="center"><B>No Records Found</B></td>
											</tr>
										</logic:equal>  
										 <logic:greaterThan name="size" value="0">
											 <logic:iterate id="searchList" name="myMessageForm" property="result">
											   <html:form method="post" action="/myMsgAction.do">
												  <html:hidden property="method" value="message"/>
													  <input type="hidden" name="groupId" value='<bean:write name="searchList" property="messageId"/>'/>
														<tr>
														<td class="listCellBg"><a href ='myMsgAction.do?method=myGrpDetail&messageId=<bean:write name="searchList" property="messageId"/>'><bean:write name="searchList" property="messageDesc"/></a></td>
														<td class="listCellBg"><bean:write name="searchList" property="author"/></td>
														<td class="listCellBg"><bean:write name="searchList" property="addDate"/></td>	
														</tr>
													</html:form>
												</logic:iterate>  
										  </logic:greaterThan>    
									</logic:present>
							  </table>
							</td>
						  </tr>
						  <tr>
							<td colspan="2" height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>
						</html:form>
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
