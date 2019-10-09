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
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<%@ page import="java.util.*" %> 
<script language="javascript">
function postData(dispId){
	frmMeeOrgUserICPRegiListing.method="post";
	frmMeeOrgUserICPRegiListing.action="IcpOrgRegList.do?process=list&status="+dispId;
	frmMeeOrgUserICPRegiListing.submit();
}	
</script>

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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
						Meetings: <span class="styleBoldOne">Organizer</span> <span class="styleBoldTwo">Insurance  Release  ICP Registration Listing</span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
				 
					Insurance Release ICP Registerations placed by the <strong>users</strong> are listed below. To view the details,
					click on the <strong>'View'</strong> button beside each record.					</td>
				  </tr>
			 
				 <tr>
					<td>
					 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						 <tr>
							<td colspan="5" class="tableSpan">
								
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   <tr>
									  <th width="75" height="15" class="alignLeft">Status: </th>
									  <th width="405" class="alignLeft">
						<form name="frmMeeOrgUserICPRegiListing" method="post" action="IcpOrgRegList.do" />			 
						<select name="status" class="selectboxOne" onchange="postData(this.value);">
                        <option selected="selected" value="">Select One</option>
						<%
							if(status.equals("Pending")){
						%>
                        <option value="Pending" selected="selected" >Pending</option>
						<%
							}
							else{
						%>
						 	<option value="Pending" >Pending</option>
						<%
							}
							if(status.equals("Approved")){
						%>
						<option value="Approved" selected="selected" >Approved</option>
						
						<%
						}
						else{
						%>
							<option value="Approved" >Approved</option>
						<%
						}
						if(status.equals("Rejected")){
						%>
						
						<option value="Rejected" selected="selected" >Rejected</option>
						<%
						}
						else{
						%>
						<option value="Rejected" >Rejected</option>
						<%
						}
						%>
                      </select>
									 
							     </th>
									 
								  </tr>
								</table>
							</td>
						 </tr>
					
						  <tr>
							<td width="120" height="27" class="tblRowHead">First Name </td>
							<td width="114" class="tblRowHead">Last Name </td>
							<td width="150" class="tblRowHead">Date of Registration </td>
							<td width="87" class="tblRowHead">View</td>
						  </tr>
						<% Vector orgList=new Vector(); 
                           orgList=(Vector)request.getAttribute("orgList");
							if(orgList!=null && orgList.size()!=0){
								System.out.println("size in jsp :"+orgList.size());
								Enumeration en=orgList.elements();    
								while(en.hasMoreElements())
									{
											String[] icpVal = (String[])en.nextElement();
											String dat1=icpVal[6];
											String[] dat=dat1.split(" ");	%>
									<input type="hidden" name="process" value="view">	
									<input name="relId" type="hidden" value="<%=icpVal[9]%>" />
									  <tr>
										<td class="listCellBg"><%=icpVal[2]%></td>
										<td height="26" class="listCellBg"><%=icpVal[3]%></td>
										<td class="listCellBg"><%=dat[0]%></td>								
										<td class="listCellBg"><input name="process" type="button" class="oneBtn" value="view" onclick="javascript:location.href('IcpOrgRegList.do?process=view&relId=<%=icpVal[9]%>');"/></td>
									  </tr>
							<%}
							}else{%>
									  <td  colspan="5" class="alignCenter">No Records Found !</td>
						 <% }%>
							</form>
						  <tr>
							<td height="19" colspan="5">&nbsp;</td>
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
