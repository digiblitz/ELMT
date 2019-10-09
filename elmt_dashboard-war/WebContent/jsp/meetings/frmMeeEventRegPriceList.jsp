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
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<script language="javascript">

	function validatePriceList(){
		if(document.frmMeePriceMaster.selPriceType.value==""){
			alert("Please select any one Price Type.");
			document.frmMeePriceMaster.selPriceType.focus();
			return false;
		}
		
		return true;
	}

</script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 

</head>
<%
	String priceTypeId1 = (String) request.getAttribute("priceTypeId");
	
	

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
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Price Details List </span></td>
				  </tr>
				  
				 
				 <tr>
					<td>
					
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						  <tr>
							<td colspan="6">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   <form name="frmMeePriceMaster" id="frmMeePriceMaster" action="eventRegPriceCRUD.do" method="post"  onsubmit="return validatePriceList(this);">
									<input type="hidden" name="cmd" value="listDetails">
								   <tr>
									  <th width="90" height="25" class="tableSpan"><strong>Price Type:</strong>&nbsp;</th>
								      <th class="tableSpan" colspan="2"><span class="alignLeft">
							<select name="selPriceType" id="selPriceType" class="selectboxOne">
										<option selected="selected" value="">Select One</option>
										<%
										ArrayList priceDetails = (ArrayList)request.getAttribute("priceList");
										if(priceDetails!=null && priceDetails.size()!=0){
										Iterator itr= priceDetails.iterator();
										while(itr.hasNext()){
										String[] s = (String[]) itr.next();
										String priceId = s[0];
										String priceName = s[1];
										if(priceId!=null && priceId.equalsIgnoreCase(priceTypeId1)){
										%>
										<option value="<%=priceId%>" selected="selected" ><%=priceName%></option>
										<%}else{%>
										<option value="<%=priceId%>" ><%=priceName%></option>
										<%}}}%>
						</select>
				 	  </span><span class="asterisk">*</span>
					  <input type="submit" name="submit" value="Submit" class="oneBtn"/>
									 </th>
								  </tr>
								   
								  </form>
								</table>							</td>
						 </tr>
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									
									<!--
									<td colspan="3" valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
									<span class="legendOne">&nbsp;</span> &nbsp;Delete.
									</td>
									-->
							      </tr>
								</table>
								
							</td>
						 </tr>
						  <tr>
							<td width="123" height="27" class="tblRowHead">Season </td>
							<td width="102" class="tblRowHead">Due Date Price </td>
							<td width="133" class="tblRowHead">After Due Date Price </td>
							<td width="54" class="tblRowHead">View</td>
							<td width="52" class="tblRowHead">Edit</td>
						   </tr>

					<%
		   		
					 ArrayList priceListView=(ArrayList)request.getAttribute("allPriceList");
					 if(priceListView!=null && priceListView.size()!=0){ 
					 Iterator itPrice = priceListView.iterator();
					  while(itPrice.hasNext()){
						    String [] priceDetail  = (String[]) itPrice.next();
							String priceId = priceDetail[0];
							String priceTypeId = priceDetail[1];
							String seasonName = priceDetail[2];
							String dueDatePrice = priceDetail[3];
							String afterDueDatePrice = priceDetail[4];
				
		             %>
							<form name="frmDisplayAdminList" method="post" action="eventRegPriceCRUD.do">		
							<input name="cmd" type="hidden" value="viewEditPriceList">
							<input name="priceId" type="hidden" value="<%=priceId%>">
						  <tr>
							<td height="26" class="listCellBg"><%=seasonName%></td>
							<td class="listCellBg"><%=dueDatePrice%> </td>
							<td class="listCellBg"><%=afterDueDatePrice%> </td>
							<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="Edit" /></td>
							<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="View" /></td>
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
