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
	var className = document.mainfrm.listType.value;
	document.mainfrm.method="post";
	document.mainfrm.action="./TransactDet.do?process=listtransac&className="+className;
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
<%!
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	String dateCheck(Date fieldName){
	String detValue = "N/A";
		if(fieldName!=null){
		 detValue = sdf.format(fieldName);
		}
	return detValue;
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
					<td colspan="5" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Edit Transaction Master </span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">To edit a Transaction Details  click on <strong>'Edit'</strong> button. </td>
				  </tr>

				 <tr>
					<td>

						 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
							<%
							String prevType = "";
							if(session.getAttribute("className")!=null){
								prevType = (String) session.getAttribute("className");
							}
							%>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th colspan="2" height="20" valign="top" class="alignLeft">Class  :&nbsp; </th>
									<td width="408" colspan="3" valign="middle" class="alignLeft">
										<form name="mainfrm">
										<select name="listType" class="textboxOne" onchange="getsubArrays();">
										<option value="">Select One</option>
										<%
											ArrayList serviceMaster=(ArrayList)request.getAttribute("classMaster");
											System.out.print("serviceMaster " +serviceMaster.size());
											if(serviceMaster!=null && serviceMaster.size()!=0){
												Iterator itemEvent = serviceMaster.iterator();
													while(itemEvent.hasNext()){
														HLCClassDetailsVO classDet = (HLCClassDetailsVO) itemEvent.next();
														String classId = classDet.getClass_id();
														String className = classDet.getClass_name();
														String parenetName = classDet.getParent_class_name();
															if(prevType.trim().equalsIgnoreCase(className)){		%>
															<option value="<%=className%>" selected="selected"><%=className%></option>
											<%				}else{
													%>
															<option value="<%=className%>"><%=className%></option>
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
							<td width="123" height="27" class="tblRowHead">Transaction  Name</td>
							<td width="123" height="27" class="tblRowHead">Account Number</td>
							<td width="123" height="27" class="tblRowHead">Item Number </td>
							<td width="123" height="27" class="tblRowHead">Add Date </td>
							<td width="54" class="tblRowHead">Edit</td>
						   </tr>
					<%
							ArrayList listTransacItem =(ArrayList)request.getAttribute("listTransacItem");
							if(listTransacItem!=null && listTransacItem.size()!=0){
							Iterator listItems = listTransacItem.iterator();
								while(listItems.hasNext()){
									HLCAccTxnTypeDetailVO accTxnTypVO = (HLCAccTxnTypeDetailVO) listItems.next();
									String transId = accTxnTypVO.getTransaction_type_id();
									String transName = accTxnTypVO.getTransaction_name();
									String AccNo = accTxnTypVO.getAccount_no();
									String itemNo = accTxnTypVO.getItem_no();
									Date addDate = accTxnTypVO.getAdd_date();
						%>
							<form name="frmTransListMaster" method="post" action="./TransactDet.do">
							<input name="process" type="hidden" value="edittransac"/>
							<input name="transId" type="hidden" value="<%=transId%>"/>
							   <tr>
								<td height="26" class="listCellBg"><%=transName%></td>
								<td height="26" class="listCellBg"><%=AccNo%></td>
								<td height="26" class="listCellBg"><%=itemNo%></td>
								<td height="26" class="listCellBg"><%=dateCheck(addDate)%></td>
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
