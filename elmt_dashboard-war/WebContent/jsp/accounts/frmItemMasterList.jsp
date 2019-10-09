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
<%@ page import="java.text.*" %>
<%@ page import="com.hlcaccounts.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function getsubArrays(){
	var serviceType = document.mainfrm.serviceType.value;
	document.mainfrm.method="post";
	document.mainfrm.action="./ItemMaster.do?process=listSubitems&servType="+serviceType;
	document.mainfrm.submit();	
}
</script>
</head>
<body>
<%! 
	DecimalFormat result  = new DecimalFormat("0.00");
	String amtFormat(String amtVal){
	String amountValue = "0.00";
		if(amtVal!=null && amtVal.trim().length()!=0){
		//System.out.print(Double.parseDouble(amtVal));
		amountValue = result.format(Double.parseDouble(amtVal));
		}
	return amountValue;
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
					<td colspan="5" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Item List  Master </span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">To edit a Item List click on <strong>'Edit'</strong> button. </td>
				  </tr>
				 
				 <tr>
					<td>
					
						 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
							<%
							String prevType =null;
							
							if(request.getParameter("servType")!=null){
								prevType = request.getParameter("servType");
							//	prevType = (String) session.getAttribute("serviceType");
							}
							else{
								prevType = "";
							}
/*							if(session.getAttribute("accountType")!=null){
								prevType = (String) session.getAttribute("serviceType");
							}*/
							%>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th colspan="2" width="125" height="20" valign="top" class="alignLeft">Item Type:&nbsp; </th>
									<td colspan="3" valign="middle" class="alignLeft">
										<form name="mainfrm">
										<select name="serviceType" class="textboxOne" onchange="getsubArrays();">
										<option value="">Select One</option>
										<%
											ArrayList serviceMaster=(ArrayList)request.getAttribute("serviceMaster");
											if(serviceMaster!=null && serviceMaster.size()!=0){ 
												Iterator itemEvent = serviceMaster.iterator();
													while(itemEvent.hasNext()){
														String serviceTypeArray[] = (String[])itemEvent.next();
														String serviceTypeId= serviceTypeArray[0];
														String serviceTypeName = serviceTypeArray[1];
															if(prevType.trim().equalsIgnoreCase(serviceTypeName)){		%>
																<option value="<%=serviceTypeName%>" selected="selected"><%=serviceTypeName%></option>
												<%			}
															else{
															%>
																<option value="<%=serviceTypeName%>"><%=serviceTypeName%></option>
												<%	}
											}
											}
										%>
										</select>
										</form>	
									</td>
							      </tr>
								</table>
								
							</td>
						 </tr>
						  <tr>
							<td width="123" height="27" class="tblRowHead">Item Number</td>
							<td width="123" height="27" class="tblRowHead">Item Description </td>
							<td width="123" height="27" class="tblRowHead">Item Rate</td>
							<td width="54" class="tblRowHead">Edit</td>
						   </tr>
					<%
							ArrayList subitemType =(ArrayList)request.getAttribute("subitemType");
							if(subitemType!=null && subitemType.size()!=0){ 
							Iterator subItemList = subitemType.iterator();
								while(subItemList.hasNext()){
									String subItemArray[] = (String[]) subItemList.next();
									String itemId = subItemArray[0];
									String serviceTypeName = subItemArray[1];
									String itemNo = subItemArray[2];
									String parentItemNo = subItemArray[3];
									String itemDesc = subItemArray[4];
									String accountNo = subItemArray[5];
									String rate = subItemArray[6];
						%>
							<form name="frmItemListMaster" method="post" action="./ItemMaster.do">	
							<input name="process" type="hidden" value="editListMaster"/>	
							<input name="itemId" type="hidden" value="<%=itemId%>"/>
							   <tr>
								<td height="26" class="listCellBg"><%=itemNo%></td>
								<td height="26" class="listCellBg"><%=itemDesc%></td>				
								<td height="26" class="listCellBg"><%=amtFormat(rate)%></td>			   
								<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="Edit" /></td>
							   </tr>
							</form>
					      <%
						  	}
									}else {
								%>
								<tr>
								  <th height="25" class="alignCenter" colspan="6">No Records are available. </th>
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
