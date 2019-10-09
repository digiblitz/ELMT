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
<%@ page import="com.hlcmro.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<script language="javascript">

	function validateMap(){
		
		if(document.frmMeeMapACAreaMaster.areaId.value==""){
			alert("Select any one Area");
			document.frmMeeMapACAreaMaster.areaId.focus();
			return false;
		}
		
		if(document.frmMeeMapACAreaMaster.areaChairId.value==""){
			alert("Enter Area Chair Code.");
			document.frmMeeMapACAreaMaster.areaChairId.focus();
			return false;
		}
	
		return true;
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
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
					
						<form name="frmMeeMapACAreaMaster" id="frmMeeMapACAreaMaster" method="post" action="EventOrgRenewal.do" onsubmit="return validateMap();">
						<input type="hidden" name="eventProcess" value="mapAreaChairAreaPerson">
							
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Meetings:  <span class="styleBoldTwo">Map Area Chair To An Area</span></td>
						</tr>
						
						  <tr> 
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Mapping Information</span></td>
						  </tr>
						    <tr>
							<td colspan="2" class="tblRowHead">Required fields are marked with an asterisk* </td>
							</tr>
						    <%
							  String salespersonCodeErr = (String)request.getAttribute("err");
							  if(salespersonCodeErr!=null){
							  %>
							 
							   <tr>
								<td colspan="2" class="asterisk">
									<%=salespersonCodeErr%> Area Chair ID Does not exist
								</td>
							  </tr>
							  <%
							  }
							  %>
						  
						  <tr> 
							<td width="300" class="tableLeft">Area: </td>
							<td width="297" class="tableRight">
							<select name="areaId" id="areaId" class="selectboxOne" >
							<option selected="selected" value="">Select One</option>
							<%
									ArrayList arrayAreaList = (ArrayList)request.getAttribute("allAreaList");
									if(arrayAreaList!=null && arrayAreaList.size()!=0){
										Iterator itAreaList = arrayAreaList.iterator();
										while(itAreaList.hasNext()){
											String[] strAreaList = (String [])itAreaList.next(); 
											//String[] strAreaList = {area_id, area_code, area_name};
											String areaId = strAreaList[0];
											String areaCode = strAreaList[1];
											String areaName = strAreaList[2];
											%>
											<option value="<%=areaId%>" ><%=areaName%></option>
											 <%
										}
									}
							%>
							</select>
							<span class="asterisk">&nbsp;*</span> </td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Area Chair ID:</td>
							<td class="tableRight">
							<input name="areaChairId" type="text" class="textboxOne" />
							<span class="asterisk">&nbsp;*</span>							</td>
						  </tr>
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
						<input name="Submit" type="submit" class="gradBtn" value="Assign" />
						<input name="Submit" type="reset" class="gradBtn" value="Reset" />
						</td>
						  </tr>
					  </table>
					  </form>
				  <tr>
						<td >&nbsp; 
					   <!-- DO NOT DELETE THIS ROW -->
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
