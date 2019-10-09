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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
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
	 Membership: <span class="styleBoldTwo">Non Usea Organization Listing</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->
		The  Status Masters are listed as follows. <br />
		<br />
		To edit a status master type, click on the <strong>'Edit'</strong> button beside it. To detele a record click on the <strong>'Delete'</strong> button. <br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
	       <%
                   Vector nonUseaList=new Vector();
                   nonUseaList=(Vector)request.getAttribute("nonUseaList");
                   String status=(String)request.getAttribute("status");
                                     
                   session.setAttribute("nonUseaEdit",nonUseaList);
                %>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="4">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				 <!-- <tr>
					<th height="20" width="125" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
					<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendOne">&nbsp;</span> &nbsp;Delete
					</td>
				    <td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendTwo">&nbsp;</span> &nbsp;Edit.
					</td>
				  </tr>-->
				</table>
			</td>
		  </tr>
		
                  <%
                  if(status!=null)
                  {
                  if(status.equalsIgnoreCase("false"))
                  {%>
		  		<tr>
				<td class="styleError" colspan="4">Cannot delete this record. its already in use</td>
				</tr>
                   <%}}%>
                  
		  <tr>
			<td width="282" height="27" class="tblRowHeadTwo">Organization name </td>
			<td width="91" class="tblRowHeadTwo">Edit </td>
			<td width="105" class="tblRowHeadTwo">Delete</td>
		   </tr>
                    <%
                       int i=0;
                       Enumeration en=nonUseaList.elements();
                       while(en.hasMoreElements())
                       {
                            String[] hlcList=(String[])en.nextElement();
                    %>
			<form name="frmStatusMastList" method="post" id="myform" action="./nonhlc.do" >
		  <tr>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=hlcList[1]%></th>
			<input type="hidden" name="hlcid" value="<%=i%>"/>
                        <input type="hidden" name="orgid" value="<%=hlcList[0]%>"/>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="process" value="Edit" class="oneBtn" /></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="process" value="Delete" class="twoBtn" /></td>
		   </tr></form>
		  <%i++;}%>
		  <tr>
		  	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
		  <tr>
			<td height="19" colspan="3">&nbsp;</td>
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
