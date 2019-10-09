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
<title<%=(String)session.getAttribute("title")%></title>
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">My  Members</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
					  
					  </td>
				  </tr>
				  <tr>
					<td colspan="2">
							<html:form method="post" action="/myMsgAction.do">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
								  <tr>
									<td class="tblRowHead" colspan="4">Members Of My Group </td>
								  </tr>
								  <tr>
									<td colspan="2" class="tblDescrp">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
										   <tr class="alignLeft">
											 	<td width="60%" class="alignLeft"><strong>Search:</strong> 
											   	<html:text property="subject" styleClass="textboxOne" size="35" />
											   	</td>
											 	<td width="40%" align="left" class="alignLeft">
												<input name="Submit" type="submit" class="gradBtn" value="Search" />
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
							</table>
							<input type="hidden" name="method" value="myGroup"/>
							</html:form>
						</td>
					</tr>
					<!--this table for list the results--> 
					 <tr>
						<td colspan="4">
							<table width="100%" border="0" cellspacing="1" cellpadding="0" >
								<tr>
									<td width="40%" class="tblRowHead">Members</td>	
									<td width="15%" class="tblRowHead">Remove Member</td>
								</tr>
								<logic:present name="myMessageForm" property="grpResults">
								  <bean:size id="size" name="myMessageForm" property="grpResults"/>
									 <logic:equal name="size" value="0">
											<tr>
											   <td colspan="5"  align="center"><B>No Records Found</B></td>
											</tr>
									 </logic:equal>                 
									 <logic:greaterThan name="size" value="0">
										  <logic:iterate id="searchList" type="java.lang.String[]" name="myMessageForm" property="grpResults" >										
											  <html:form method="post" action="/myMsgAction.do">
												 <input type="hidden" name="method" value="showDelete" />	
												 <input type="hidden" name="groupId" value='<%=searchList[0]%>'/>
													<tr>
														<td class="listCellBg"><%=searchList[1]%></td>
														<td class="listCellBg"><input name="deleteButton" type="submit" class="oneBtn" value="Delete"/></td>
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
