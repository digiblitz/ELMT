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
 	frmDressageSz.method="post";
	frmDressageSz.action="./DressageDet.do?process=lstsize&status="+dispId;
	frmDressageSz.submit();
}	
</script>
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
	Membership: <span class="styleBoldTwo">Arena Size  Listing</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The  Officials Names are listed below: <br />
		<br />
		To edit a Arena Size  master, click on the <strong>'Edit'</strong> button beside it. To Activate or Deactivate  a record click on the <strong>'Activate / Deactivate'</strong> button. <br />
	</td>
  </tr>
 
 <tr>
 	<td>
		  	<form  name="frmDressageSz" action="DressageDet.do" name="loopfrm" method="post">	
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
  <tr>
  <tr>
  <td width="230" height="15" class="alignLeft">Status: </td>
  <td>  	
		<select name="status" onchange="postData(this.value);" class="selectboxOne" >
		  <%Vector lst = (Vector) request.getAttribute("sizeList");
				if(request.getAttribute("sizeList")==null){	%>
						<option value="Select" selected="selected">Select One</option>
						<option value="Activate">Active</option>
						<option value="Deactivate">Deactive</option>
				<%}
				else{
					String status = (String) request.getParameter("status");
					if(status.equalsIgnoreCase("Activate")){%>
						<option value="Select">Select One</option>
						<option value="Activate" selected="selected">Active</option>
						<option value="Deactivate">Deactive</option>
				<%	}
					else if(status.equalsIgnoreCase("Deactivate")){%>
						<option value="Select">Select One</option>
						<option value="Activate">Active</option>
						<option value="Deactivate" selected="selected">Deactive</option>
				<% }
					else{	%>
						<option value="Select" selected="selected">Select One</option>
						<option value="Activate">Active</option>
						<option value="Deactivate">Deactive</option>
				<% 	lst.clear();	}	
				}	 %>
		</select>
	</td></tr>
		<tr>
			<td width="100" height="27" class="tblRowHeadTwo">Arena Size </td>
			<td width="45" class="tblRowHeadTwo">Edit </td>
			<td width="100" class="tblRowHeadTwo">Status</td>
		   </tr>   
		<%
		String arenaSizeId = "";
		String sizeName ="";
		String status ="";
			//Vector sizeList = (Vector)request.getAttribute("sizeList");
			if(lst!=null){
			if(lst.size()!=0)
			{
			Enumeration it = lst.elements();
            while (it.hasMoreElements()) {
                String[] s = (String[]) it.nextElement();
                Debug.print(" List Content...\n");
                for(int i=0;i<s.length;i++){
			        System.out.println(i+" " + s[i]);
				}
					arenaSizeId = s[0];
					sizeName = s[1];
					status = s[2];
		%>

	    <input type="hidden" name="process" value="editsize">			
	    <input type="hidden" name="arenaSizeId" value="<%=arenaSizeId%>">	
		<input type="hidden" name="status" value="<%=status%>" />		
		<tr>
			<th height="26" bgcolor="#E3E1D2" class="alignLeft"><%=sizeName%></th>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="butValue" value="Edit" class="oneBtn"
							onclick="javascript:location.href('DressageDet.do?process=editsize&sizeId=<%=arenaSizeId%>');" /></td>
		<%
			if(status.equals("1")){	%>
				<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="butValue" value="Deactivate" class="twoBtn" 
							onclick="javascript:location.href('DressageDet.do?process=evntSizeDeactivate&sizeId=<%=arenaSizeId%>&status=<%=status%>');" /></td>
			<%}
			else{	%>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="butValue" value="Activate" class="twoBtn" 
							onclick="javascript:location.href('DressageDet.do?process=evntSizeActivate&sizeId=<%=arenaSizeId%>&status=<%=status%>');" /></td>
			<% } %>
		</tr>			
		
	<%	}}}
		else {
		%>
		<tr>
		  <td align="center" colspan="5"><strong>No Records are Available </strong></td>
		</tr>
		<%	}	%>
  	<tr>
			<td height="19" colspan="5">&nbsp;</td>
           </tr>
	  </table></form>
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
