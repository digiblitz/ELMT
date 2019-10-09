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
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">
function getsubArrays(){
	var accType = document.mainfrm.accountType.value;
	document.mainfrm.method="post";
	document.mainfrm.action="./ChartAccMaster.do?process=listOnAccType&accType="+accType;
	document.mainfrm.submit();
}
</script>
</head>
<body>
<%!
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
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
					<td colspan="5" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Account List  Master </span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">To edit a Account List Item click on <strong>'Edit'</strong> button. </td>
				  </tr>

				 <tr>
					<td>

						 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
							<%
							String prevType = null;
							if(request.getParameter("accType")!=null){
								prevType = request.getParameter("accType");
							}
							else{
								prevType = "";
							}
							%>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th colspan="2" width="125" height="20" valign="top" class="alignLeft">Account Type:&nbsp; </th>
									<td colspan="3" valign="middle" class="alignLeft">
									<form name="mainfrm">
									<select name="accountType" class="textboxOne" onchange="getsubArrays();">
									<option value="">Select One</option>
									<%
										ArrayList accTypeMaster=(ArrayList)request.getAttribute("accTypeMaster");
										if(accTypeMaster!=null && accTypeMaster.size()!=0){
										Iterator accEvent = accTypeMaster.iterator();
											while(accEvent.hasNext()){
												String accTypeArray[] = (String[]) accEvent.next();
												String accountTypeId = accTypeArray[0];
												String accountTypeName = accTypeArray[1];
													if(prevType.trim().equalsIgnoreCase(accountTypeName)){
														%>
														<option value="<%=accountTypeName%>" selected="selected"><%=accountTypeName%></option>
												<% 	}
													else{
													%>
														<option value="<%=accountTypeName%>"><%=accountTypeName%></option>
												<%	}
											}
										}
										%>
									</select>
									</form>	</td>
									</tr>
								</table>

							</td>
						 </tr>
						  <tr>
						    <td width="122" height="27" class="tblRowHead">Account Name</td>
							<td width="114" height="27" class="tblRowHead">Account Number</td>
							<td width="105" height="27" class="tblRowHead">Account Description </td>
							<td width="100" height="27" class="tblRowHead">Bank Acc No</td>
							<td width="53" class="tblRowHead">Edit</td>
						   </tr>
					<%
							ArrayList subAccountLists =(ArrayList)request.getAttribute("subAccountLists");
							if(subAccountLists!=null && subAccountLists.size()!=0){
							Iterator subAccList = subAccountLists.iterator();
								while(subAccList.hasNext()){
									String subAccArray[] = (String[]) subAccList.next();
									String accountId = subAccArray[0];
									String accountType = subAccArray[1];
									String accountNo = subAccArray[2];
									String accountName = subAccArray[3];
									String parent_accountNo = subAccArray[4];
									String accountDesc = subAccArray[5];
									String bankAccountNo = subAccArray[6];
						%>
							<form name="frmAccountListMaster" method="post" action="./ChartAccMaster.do">
							<input name="process" type="hidden" value="editAccMaster"/>
							<input name="accId" type="hidden" value="<%=accountId%>"/>
							   <tr>
								<td height="26" class="listCellBg"><a href="./ChartAccMaster.do?process=viewSingleAccDet&accId=<%=accountId%>"><%=accountName%></a></td>
								<td height="26" class="listCellBg"><%=accountNo%></td>
								<td height="26" class="listCellBg"><%=nullCheck(accountDesc)%></td>
								<td height="26" class="listCellBg"><%=nullCheck(bankAccountNo)%></td>
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
