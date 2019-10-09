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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">
	function validateSpecList(){
		if(document.frmMeeSpecMaster.status.value==""){
			alert("Please select any one Status.");
			document.frmMeeSpecMaster.status.focus();
			return false;
		}
		return true;
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="213" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="532" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Specification Detail Master </span></td>
				  </tr>
				  
				 
				 <tr>
					<td>
					
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						  <tr>
							<td colspan="6">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   <form name="frmMeeSpecMaster" id="frmMeeSpecMaster" action="specification.do" method="post" onsubmit="return validateSpecList();"  >
									<input type="hidden" name="specProcess" value="listSpecMaster">
								   
								   <tr>
								      <th width="61" height="25" class="tableSpan"><strong>Status:</strong>&nbsp;</th>
									  <th width="105" class="tableSpan"><span class="alignLeft">
									  <select name="status" id="status" class="selectboxOne" >
									  	<option selected="selected" value="">Select One</option>
											<%			String statusStr = (String)request.getAttribute("status");
														String[] status = {"Active","Deactive"};
														String[] value = {"true","false"};
														for(int i=0;i<2;i++){
														if(statusStr!=null && statusStr.equalsIgnoreCase(value[i])){
																				%>
														<option value="<%=value[i]%>" selected="selected"><%=status[i]%></option>
													 <%
													 }
													 else{
													 %>
													 <option value="<%=value[i]%>"><%=status[i]%></option>
													 <%
													 }
											  }
											
											  %>							
									 </select>		</span>							  </th>
									  <th width="326" class="tableSpan">
									 <input type="submit" name="search" value="Submit" class="gradBtn" /></th>
								     </tr>
								  </form>
								</table>							</td>
						 </tr>
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">							
							</td>
						 </tr>
						  <% String error = (String)request.getAttribute("Error");
					if(error!=null && error.equals("Error")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong>Specification Type Name is already Processed .</strong></td>
					</tr>
					<%
					}
					%>
						  <tr>
							<td width="154" height="27" class="tblRowHead">Specification Type Name</td>
							<td width="117" class="tblRowHead">Specification Description </td>
							<td width="75" class="tblRowHead">Activity Type Name </td>
							<td width="66" class="tblRowHead">Edit</td>
							<td width="52" class="tblRowHead">Status</td>
						   </tr>

					<%
		   		
					 ArrayList objAllSpecDetails=(ArrayList)request.getAttribute("objAllSpecDetails");
					 if(objAllSpecDetails!=null && objAllSpecDetails.size()!=0){ 
					 Iterator itSpec = objAllSpecDetails.iterator();
					  while(itSpec.hasNext()){
						    String [] specDetail  = (String[]) itSpec.next();
							String specId = specDetail[0];
							String activityId = specDetail[1];
							String activityName = specDetail[2];
							String specName = specDetail[3];
							String specDesc = specDetail[4];
							String activeStatus = specDetail[5];
							String addDate = specDetail[6];
							boolean activeStat = Boolean.valueOf(activeStatus).booleanValue();
							String buttonValue ="";
							if(activeStat){
							 	buttonValue = "Deactivate";
							 }else{
							  	buttonValue = "Activate";
							 }
							 
							
							//String [] priceDetail  = {priceDetId,dueDatePrice,afterDueDatePrice,userTypeName};
		   %>
							<form name="frmDisplaySpecList" method="post" action="specification.do">		
							<input name="specProcess" type="hidden" value="selectSpecDet">
							<input name="specId" type="hidden" value="<%=specId%>">
							<input name="activityId" type="hidden" value="<%=activityId%>">
						  <tr>
							<td height="26" class="listCellBg"><%=specName%></td>
							<td class="listCellBg"><%=specDesc%> </td>
							<td class="listCellBg"><%=activityName%> </td>
							<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="Edit" /></td>
							<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="<%=buttonValue%>" /></td>
						   </tr>
						</form>
		   
					      <%	}
									}else {
								%>
								<tr>
								  <th height="25" colspan="5" align="center">No Records are available. </th>
								</tr>
								<%}%>

						
						  <tr>
							<td height="19" colspan="7">&nbsp;</td>
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
