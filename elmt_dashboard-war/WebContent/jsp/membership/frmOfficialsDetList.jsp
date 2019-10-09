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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script language="javascript">
function postData(dispId){
 
	loopfrm.method="post";
	loopfrm.action="./OfficialsDet.do?offProcess=list&status="+dispId;
	loopfrm.submit();
}	
</script>

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>

<body>
<%
String  status = (String)request.getAttribute("status");
if(status==null || status.equals("")){
	status="";
}
%>

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
	Meetings: <span class="styleBoldTwo">Officials Details Listing</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The  Officials Names are listed below: <br />
		<br />
		To edit a Judge Type Name, click on the <strong>'Edit'</strong> button beside it. To Change the activity status, click on the <strong>'Activate' </strong> or <strong>'Deactivate' </strong> button. <br />
	</td>
  </tr>
 
				 <tr>
					<td>
					 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						 <tr>
							<td colspan="5" class="tableSpan">
								
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   <tr>
								   <form name="loopfrm" method="post" action="./OfficialsDet.do" /> 		 
								  <th width="230" height="15" class="alignLeft">Status: 
							    <select name="status" class="selectboxOne" onchange="postData(this.value);">
								<%
							if(status.equals("")){
						%>
                                          <option value="" selected="selected" >Select One</option>
                                          <%
							}
							else{
						%>
                                          <option value="" >Select One</option>
                                          <%}%>
							
							<%
							if(status.equals("activate")){
						%>
                                          <option value="activate" selected="selected" >Activated</option>
                                          <%
							}
							else{
						%>
                                          <option value="activate" >Activated</option>
                                          <%
							}
							if(status.equals("deactivate")){
						%>
                                          <option value="deactivate" selected="selected" >Deactivated</option>
                                          <%
						}
						else{
						%>
                                          <option value="deactivate" >Deactivated</option>
                                          <%
						}
						%>
                                        </select></th>
									  <th width="411" class="alignLeft">
						</th>
								  </tr>
								</table>							</td>
						 </tr>
						 </form>
				
						  <tr>
						    <td width="123" height="27" class="tblRowHead">Judge Type Name</td>
						    <td width="133" class="tblRowHead"><div align="center">Edit</div></td>
						    <td width="54" class="tblRowHead">Status</td>
						  </tr>
		<%
			Vector judVect = (Vector)request.getAttribute("DispOfficialDet");
			if(judVect!=null && judVect.size()!=0){
			Enumeration enm = judVect.elements();
			while (enm.hasMoreElements()) {
				String[] s = (String[]) enm.nextElement();
				String judgetypeId= s[0];
				String judgeTypeName= s[1];
				String judgeStatus=s[2];		%>
		<form action="./OfficialsDet.do" name="frm" method="post">

		<tr>
			
			<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=judgeTypeName%></td>
			<input type="hidden" name="judgeTypeName" value="<%=judgeTypeName%>">
			<input type="hidden" name="judgetypeId" value="<%=judgetypeId%>">			
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="offProcess" value="Edit" class="oneBtn" /></td>
			<% if (judgeStatus.equalsIgnoreCase("0")){%>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="offProcess" value="Activate" class="twoBtn" /></td>
			<%} else if(judgeStatus.equalsIgnoreCase("1")){ %>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="offProcess" value="Deactivate" class="twoBtn" /></td>
			<%}%></tr>
			</form>
					
			<%	}
			}
		else {
		%>
		<tr>
		  <td align="center" colspan="3"><strong>No Records are Available </strong></td>
		</tr>
		<%	}	%>
		   		

<!--		  <tr>
		  	<td height="25" colspan="3"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
 -->		  <tr>
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
