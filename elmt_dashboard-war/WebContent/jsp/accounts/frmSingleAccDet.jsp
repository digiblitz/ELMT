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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcaccounts.util.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAccMstrVali.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
		
					<form name="frmAccMaster" method="post" action="ChartAccMaster.do" onsubmit="return chkValid();"> <!---->
					<input type="hidden" name="process" value="updteAccMaster">		
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Account Type Master (Edit / Update) </span></td>
						</tr>
						<%
						 HLCAccountsMasterVO accountVO = ( HLCAccountsMasterVO ) request.getAttribute("accMasterVO");
							String accIdVO = null;
							String accTypVO = null;
							String accountNoVO = null;
							String accNameVO = null;
							String parentAccVO = null;
							String accDescVO = null;
							String bankAccVO = null;	 
						 if(accountVO!=null){
							accIdVO = accountVO.getAccount_id();
							accTypVO = accountVO.getAccount_type();
							accountNoVO = accountVO.getAccount_no();
							accNameVO = accountVO.getAccount_name();
							parentAccVO = accountVO.getParent_account_no();
							accDescVO = accountVO.getAccount_desc();
							bankAccVO = accountVO.getBank_account_no();						 	
						 }
						
						%>
						  <tr> 
							<td class="tableLeft">Account Type :</td>
							<input type="hidden" name="parentAccVO" value="<%=parentAccVO%>" />
							<input type="hidden" name="accId" value="<%=accIdVO%>" />
							<span class="labelOne"><%=accTypVO%></span>
							<td class="tableRight"><%=accTypVO%></td>
						  </tr>						
						  <tr> 
							<td class="tableLeft">Account Number : </td>
							<td width="297" class="tableRight"><%=nullCheck(accountNoVO)%>
							</td>
						  </tr>						
						  <tr> 
							<td class="tableLeft">Account Name : </td>
							<td width="297" class="tableRight"><%=nullCheck(accNameVO)%>				
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Sub Account Of:</td>							
							<td class="tableRight"><%=nullCheck(parentAccVO)%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Description:</td>
							<td width="297" class="tableRight"><%=nullCheck(accDescVO)%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Bank A/C No :</td>
							<td class="tableRight"><%=nullCheck(bankAccVO)%></td>								
						  </tr>		  	  						  
			  			
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
								<input type="button" class="gradBtn" value="Back" onclick="javascript:location.href('./ChartAccMaster.do?process=listAccMaster');" />
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
