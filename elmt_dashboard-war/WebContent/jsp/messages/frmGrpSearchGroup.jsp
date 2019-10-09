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
function fire(a)
{
	if(a.value=='Edit') {	
	   document.forms[1].method.value="initEdit";
	} 
	document.forms[1].submit();
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
				<html:form method="post" action="/groupAction.do">
			   <input type="hidden" name="method" value="search"/>

				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Groups: <span class="styleBoldTwo">Search A Group</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><!--<img src="images/usea_logo.jpg" width="100" height="99" />--></td>
				  </tr>
			
				  <tr>
					<td colspan="2" class="tblRowHead">Find a Group </td>
				  </tr>
				  <tr>
					<td width="22%" class="tableLeft">Keyword:</td>
					<td width="77%" class="tableRight">
					 <html:text property="keyword" styleClass="textboxOne" size="20" />
					 </tr>
				  <tr>
					<td width="22%" class="tableLeft">Category: </td>
					<td width="77%" class="tableRight">
					<html:select property="categoryType" styleId="cat_sel_id" styleClass="selectboxOne" onchange="">
						  <option selected="selected" value=""/> Select One</option>
					      <html:options collection="CATEGORY" property="value" labelProperty="label" />
					</html:select>
					  
					 </td>
				  </tr>
				  <tr>
					<td height="30" colspan="2" class="alignCenter">
					  <input name="Submit" type="submit" class="gradBtn" value="Search"  />
					</td>
			  </tr>
             
				 <!-- <tr>
					<td height="30" colspan="2">&nbsp;&nbsp;<strong>(OR)</strong></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblRowHead">Create Your own HLCGroup</td>
				  </tr>-->
				  
				  <tr>
					<td height="20" colspan="2">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
				  </tr>
				  <tr>
					<td colspan="2" height="25" >
					
																	
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
						 <logic:present name="groupForm" property="results">
								 <bean:size id="size" name="groupForm" property="results"/>
								 <logic:equal name="size" value="0">
						  <tr>
							<td class="tblMainHead">Group Name</td>
							<td class="tblMainHead">Category Name</td>
							<td class="tblMainHead">Group Type</td>	
							<td  class="tblMainHead">Edit</td>	
							<td class="tblMainHead">Delete</td>	
						  </tr>
						 
							  
										<tr>
										   <td colspan="5"  align="center"><B>No Records Found</B></td>
										   <tr>
					<td height="25" colspan="5" align="center"><a href="groupAction.do?method=initAdd" class="linkFour">START YOUR OWN Group</a></td>
				  							</tr>
										</tr>
								 </logic:equal>                 
								 <logic:greaterThan name="size" value="0">
								 <tr>
							<td class="tblMainHead">Group Name</td>
							<td class="tblMainHead">Category Name</td>
							<td class="tblMainHead">Group Type</td>	
							<td  class="tblMainHead">Edit</td>	
							<td class="tblMainHead">Delete</td>	
						  </tr>
									<logic:iterate id="searchList" name="groupForm" property="results">
												<tr>								
												<td class="listCellBg"><bean:write name="searchList" property="groupName"/></td>
												<td class="listCellBg"><bean:write name="searchList" property="categoryName"/></td>
												<td class="listCellBg"><bean:write name="searchList" property="groupType"/></td>
												<td class="listCellBg"><input type="button" name="editName" onClick="location.href='./groupAction.do?method=initEdit&groupId=<bean:write name="searchList" property="groupId"/>'" class="oneBtn" value="Edit">																								
												<td class="listCellBg"><input name="deleteGroup" type="button" onClick="location.href='./groupAction.do?method=delete&groupId=<bean:write name="searchList" property="groupId"/>'" class="twoBtn" value='Delete' />													
												</td>												
												</tr>
										</logic:iterate>                    
								   </logic:greaterThan>     
								</logic:present>								
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
