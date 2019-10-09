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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMessageCreateDistriList.js" type="text/javascript" ></script>

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
				
<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Distribution Lists  - Create  List </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><span class="msgHead">Distribution List   </span>  - <span class="styleBoldOne">Create A List </span> <br />
	    <br />
		You can create a distribution list by defining a <strong>Distribution Name</strong> and adding members to that new list from your <strong>Address Book</strong>. <br />
		<br />
		<br />
		<br />
	</td>
  </tr>
		  <tr>
			<td>
			<form method="post" name="frmMessageCreateDistriList" id="frmMessageCreateDistriList" action="./AddrDistList.do" onsubmit="return myvalidate();" >

			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
				  <tr>
					<td class="tblRowHead">Create A Distribution List </td>
				  </tr>
				  <tr>
				    <td height="25" >
					
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<th width="28%" class="alignRight">Distribution List Name:&nbsp; </th>
							<td width="72%" class="alignLeft"><input name="listName" id="txtDisList"type="text" class="textboxOne" size="35" /></td>
						  </tr>
						</table>
					<input type="hidden" value="createDList" name="process" />
					</td>
		      	 </tr>
				  <tr>
					<td height="25" >
					
					
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
							<td class="tblMainHead">Members in your Address Book</td>
							<td class="tblMainHead">&nbsp;</td>
							<td class="tblMainHead">Members Added to your List </td>
						  </tr>
						  <tr>
							<th rowspan="2">
							<select name="addsel" id="selSource" size="6" multiple="multiple" class="textAreaOne">
							<%
							Vector addVect=new Vector();
							addVect=(Vector)request.getAttribute("addrList");
                                                        Enumeration en=addVect.elements(); 
                                                        if(addVect !=null && addVect.size()!=0)
                                                        {
                                                        while(en.hasMoreElements())
                                                        {
							String[] addrList=(String[])en.nextElement();
							%>
							
														
								<option value="<%=addrList[0]%>"><%=addrList[1]+" "+addrList[3]%></option>
							
                                                          		
                                                        <%}}else{%>
                                                            <option>No Address Available</option>
							
                                                        <%}%>
						  </select>
							</th>
							<td height="28"><input type="button" name="add" value="&raquo;&raquo;" class="gradTwoBtn" onClick="addSrcToDestList();"/></td>
							<td rowspan="2">
								<select name="distsel" id="selDest" size="6" multiple="multiple" class="textAreaOne">
								 
								</select>
							</td>
						  </tr>
						  <tr>
							<td valign="top"><input type="button" name="remove" value="&laquo;&laquo;" class="gradTwoBtn" onclick="deleteFromDestList();"/></td>
						  </tr>
						</table>
					
					</td>
				  </tr>
				  <tr>
					<td height="30" class="alignCenter">
						<input type="submit" name="create" class="gradBtn" />
						<input type="reset" name="Cancel" class="gradBtn" />
					</td>
				  </tr>
				   <tr>
					<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
				  </tr>
		</table>
	
	</form>
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
