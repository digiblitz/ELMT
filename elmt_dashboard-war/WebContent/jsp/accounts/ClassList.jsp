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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="accounts.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	USEA Membership: <span class="styleBoldTwo">Class List</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The Class Names are listed as follows. 
		<br />
		&nbsp;&nbsp;&nbsp;To edit a Class Name, click on the <strong>'Edit'</strong> button beside it.</td>
  </tr>
 <tr>
 	<td>
	<table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
		  
			
		  <tr>
			<td width="125" height="27" class="tblRowHeadTwo">Class Name</td>			
			<td width="127" class="tblRowHeadTwo">Edit </td>
		   </tr>
		   <%
		   ArrayList lst = (ArrayList) request.getAttribute("list");
		   if(lst.size()!=0){
    	      Iterator itr = lst.iterator();

                 while(itr.hasNext()){
					String arr[]=(String [])itr.next();
                    String classId=arr[0];
                    String className=arr[1];
					%>
				   <form name="classList" method="post" action="ClassAdd.do">
				   <input type="hidden" name="process" value="edit">	
				  <tr>
					<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=className%></th>		
					<input type="hidden" value="<%=classId%>" name="classId">
					<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="btnSubmit" value="Edit" class="oneBtn"/></td>
				  </tr>
				  </form>
			<%  }
			}
					else {
					%>
					<tr>
					  <th height="25" colspan="4">No records are available. </th>
					</tr>
					<%}%>
		<!--  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>-->
<!--		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
           </tr>-->
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
