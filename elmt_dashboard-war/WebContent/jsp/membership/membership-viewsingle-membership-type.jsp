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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>I<%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmCreateMemb.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<%! 

String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%
String[] subType = (String[])session.getAttribute("displayEditMemDetails");

String membershipTypeId = "";
String membershipTypeName = "";
String membershipAmount = "";
String userTypeId = "";
String periodValue = "";
String mDate="";

if(subType!=null){
		membershipTypeId = subType[0];
		membershipTypeName = subType[1];
		userTypeId = subType[2];
		membershipAmount = subType[3];  
		mDate  = subType[4];
		periodValue = subType[5];
}

//	{membershipTypeId, membershipTypeName, membershipAmount, mDate, uTypeId};
%>
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
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		Membership: <span class="styleBoldTwo">Create Membership Type</span>
	</td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><strong> Membership Type Value Displayed </strong></td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmCreateMembType" id="frmCreateMembType" method="post" action="./memberMaster.do" >
		<input type="hidden" name="memProcess" value="memTyDelete"/>
		<input type="hidden" value="<%=membershipTypeId%>" name="memId" />
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">
						View Membership Type </td>
				  </tr>
				  <tr>
					<td class="tableLeft">User Type:</td>
					<td class="tableRight">
						<%
							Vector vUType = (Vector)session.getAttribute("displayUserTypeDetails");
							if(vUType!=null && vUType.size()!=0){
								Enumeration eUtype = vUType.elements();
								 //String [] userType = {ID, name };
								while(eUtype.hasMoreElements()){
									String[] strType = (String [])eUtype.nextElement();
									String uTypeID = strType[0];
									String uTypeName = strType[1];
									if(uTypeID.equals(userTypeId)){
											%><%=uTypeName%>
											<input type="hidden" name="uTypeId" value="<%=uTypeID%>" />
											<%
									}
								}
							}
							%>
					  </td>
				  </tr>
				  <tr>
					<td class="tableLeft">Membership Type Name:</td>
					<td class="tableRight">
					<%=membershipTypeName%></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Membership Year:</td>
					<td class="tableRight">
					<%
					String yr = (String) request.getAttribute("year");
					%>
					<%=yr%></td>
				  </tr>				  
				   <tr>
				     <td class="tableLeft">Membership Amount: </td>
				     <td class="tableRight">
					 <%=membershipAmount%></td>
		      </tr>
				   <tr>
				     <td class="tableLeft">Membership Priority: </td>
				     <td class="tableRight">
					 <%=nullCheck(periodValue)%></td>
		      </tr>	
<!--			  <tr>
				     <td class="tableLeft">QB Transaction Type: </td>
				     <td class="tableRight">
					 < %=periodValue%></td>
		      </tr>	-->		  		  
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">&nbsp;
					<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"  />
					</td>
				  </tr>
			</table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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