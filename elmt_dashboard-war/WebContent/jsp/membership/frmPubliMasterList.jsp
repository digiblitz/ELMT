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
<%@ page import="com.hlcmrm.util.HLCPublicationVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
	Membership: <span class="styleBoldTwo">Publication Type</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The Publication types are listed as follows. <br />
		<br />
		To edit a Publication type, click on the <strong>'Edit'</strong> button beside it. <br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
	
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		  <%
			 String deleteStatus = (String)request.getAttribute("errStat");
				if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
				%>
				<tr>
				<td class="styleError" colspan="4">Cannot delete this record. It is already in use</td>
				</tr>
				<%
			}
			%> 	  
		<!--  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>-->
		  <tr>
			<td width="413" height="22" class="tblRowHeadTwo">Publication name </td>
			<td width="72" class="tblRowHeadTwo">Edit</td>
            <!--			<td width="127" class="tblRowHeadTwo">Edit </td>
			<td width="100" class="tblRowHeadTwo">Delete</td>		-->
		   </tr>
		   <%
		  ArrayList lst = (ArrayList) session.getAttribute("DispPubDetails");
		  //out.print("lst" + lst);
		   if(lst.size()!=0)
		   {
    	      Iterator itr = lst.iterator();

                      while(itr.hasNext()){
					 HLCPublicationVO donObj = (HLCPublicationVO) itr.next();
					String publicationId = donObj.getPublicationId();
					String publicationName = donObj.getPublicationName();
					 
		%>
		   <form name="frmPubliMasterEdit" method="post" action="Publication.do">
		   <input type="hidden" name="process" value="editPub">
		    <input type="hidden" name="publicationId" value="<%=publicationId%>"/>
			 
		  <tr>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=publicationName%></th>
<!--			<th height="26" bgcolor="#E3E1D2" class="alignCenter">< %=donationPrice%></th>	-->
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" value="Edit" class="oneBtn" /></td>
<!--<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="process" value="Delete" class="twoBtn" /></td>-->
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
		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
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