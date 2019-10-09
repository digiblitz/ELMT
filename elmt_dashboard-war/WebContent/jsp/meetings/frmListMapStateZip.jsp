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
<%@ page import="com.hlcmro.util.HLCAreaStateZipVO"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Integrated Enterprise Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script language="javascript">
function postData(areaId){
	var form = document.getElementById('frmListMapStateZip'); 
if(areaId==""){
		alert("Select Any of the Area Name");
		document.frmListMapStateZip.areaSelect.focus();
		return false;
}
else{
	form.method="post";
	document.frmListMapStateZip.submit();
	return true;
	}
}
</script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
				
				
					<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
						HLC Meetings: <span class="styleBoldTwo">Listing Area State Zip Entries</span></td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp"> Zip Code  values  are listed here based on the Area Name: </td>
					  </tr>
					 <tr>
						<td>
							 
							 <form name="frmListMapStateZip" id="frmListMapStateZip" method="post" action="EventOrgRenewal.do" onsubmit="return myValidate();">
							 <table width="530" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
								  <tr>
								<td height="25" colspan="2"  bgcolor="#ffffff" class="alignLeft">&nbsp;&nbsp;<strong>Area Name:</strong>&nbsp;&nbsp;
								<%	
									String areaId = (String) request.getAttribute("area_id");
									if(areaId==null) areaId = "";

									ArrayList allAreaList = (ArrayList)request.getAttribute("allAreaList");
									if(allAreaList!=null&& allAreaList.size()!=0){
										Iterator areaLst = allAreaList.iterator();
								%>
										<select name="areaSelect" class="selectboxOne" onchange="return postData(this.value)"> 
										<option value="" selected="selected">Select One</option>
								<%	while(areaLst.hasNext()){
											String AreaLstArr[] = (String [])areaLst.next();
											String area_id = AreaLstArr[0];
											String area_code = AreaLstArr[1];
											String area_name = AreaLstArr[2];
											out.print("area_id "+area_id);
											if(area_id.trim().equalsIgnoreCase(areaId)){
												%>
													<option  value="<%=area_id%>" selected="selected"><%=area_name%></option>
												<%
												}
												else{
												%>
													<option  value="<%=area_id%>"><%=area_name%></option>
												<%

												}
										}
									}
                                 %>
                                  </select>
								<!--<input type="submit" name="click2" value="Submit" class="gradBtn"/>	-->							 
								</td>
								  </tr>
 								<input type="hidden" name="eventProcess" value="OnLstMapStateZip"/>
								</table>
						   	</form>
						  <tr>
						  <td>
							  <table border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							  <tr>
									<td width="112" class="tblRowHeadTwo">State Name</td>	
									<td width="70" class="tblRowHeadTwo">State Code</td>
									<td width="102" class="tblRowHeadTwo">Area Name</td>
									<!--<td width="50" class="tblRowHeadTwo">Area Code</td>-->
									<td width="108" class="tblRowHeadTwo">Zip Code From</td>
									<td width="102" class="tblRowHeadTwo">Zip Code To</td>
							   </tr>
							<%
								ArrayList ValueList = (ArrayList)request.getAttribute("ValueList");
							
								if((ValueList!=null && ValueList.size()!=0)){
									Iterator itr = 	ValueList.iterator();
										while(itr.hasNext()) {	
											HLCAreaStateZipVO objAreaSteVO = (HLCAreaStateZipVO) itr.next();
										//State Masters
											String area_code  = objAreaSteVO.getArea_code();
											String area_id  = objAreaSteVO.getArea_id();
											String area_name = objAreaSteVO.getArea_name();
											String map_zip_id  = objAreaSteVO.getMap_zip_id();
											String state_code = objAreaSteVO.getState_code();
											String state_id = state_id = objAreaSteVO.getState_id();
											String state_name = objAreaSteVO.getState_name();
											String zip_code_from = objAreaSteVO.getZip_code_from();
											String zip_code_to = objAreaSteVO.getZip_code_to();
										
							%>
								
							  <tr>
									<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(state_name)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(state_code)%></td>	
									<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(area_name)%></td>
									<!--<td bgcolor="#E3E1D2" class="alignCenter">< %=nullCheck(area_code)%></td>-->
									<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(zip_code_from)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(zip_code_to)%></td>								
							  </tr>
								<%
											}
								} 
								else{
							   %>
								<tr>
									<td height="25" colspan="7" class="alignCenter"><strong>No Records Found!</strong></td>
								</tr>
							<% } %>
   								</table>
							</td>
							</tr>							
						  </table>
			</td>
					</tr>  
					</table>
					
					<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	 
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
