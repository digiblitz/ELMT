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
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.hlcaccounts.util.*" %>
   <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
  <%@ page import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmAccTransaction.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script language="javascript">
function onValidate(){
 if(document.myform.transDate.value==""){
 	alert("Please select the Transaction Date");
	document.myform.transDate.focus();
	return false;
  }
  if(document.myform.paymentMode.selectedIndex==0){
 	alert("Please select any of the Payment Method");
	document.myform.paymentMode.focus();
	return false;
  }
 return true;
}

</script>
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
						<span class="styleBoldTwo">Transaction Details. </span></td>
					  </tr>
					 <tr>
					   <td>
					   <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                         <form name="myform" id="myform" method="post" action="acctrans.do" onsubmit="return onValidate();" >
                           <input type="hidden" name="process" value="listMembTrans" />
                            <tr >
					 <% 
						 //Date today = new Date();
						 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
					
					      Date today = (Date)request.getAttribute("transDate");
						  String paymentMode = (String) request.getAttribute("paymentMode");
						  if(paymentMode==null) paymentMode = "";
					   
					     if(today==null){ 
						 	today = new Date();
						  }
						  %>
					<td width="74"  class=" "><div align="right">Transaction Date:</div></td>
					<td width="162"  class=" ">
					  <div align="left">
					    <input type="text" name="transDate" id="transDate" size="16" readonly="" value="<%=formatter.format(today)%>"  />
					      <span class="formX">
					        
			                  <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> </span> </div></td>
					<td width="78"  class=" "><div align="right">Payment Method:</div></td>
					<td width="115"  class=" ">
					  <div align="left">
					    <select name="paymentMode" id="paymentMode" class="selectboxOne" >
					      <option selected="selected" value="">Select One</option>
						  <%
						  if(paymentMode.trim().length()!=0 && paymentMode.equalsIgnoreCase("Check")) {
						  %>
				      	<option value="Check" selected="selected">Cash/Check</option>
						<%
						}
						else{
						%>
						<option value="Check">Cash/Check</option>
						<%
						}
						if(paymentMode.trim().length()!=0 && paymentMode.equalsIgnoreCase("Visa, MasterCard")) {
						  %>
				      <option value="Visa, MasterCard" selected="selected">Visa/MasterCard</option>
						<%
						}
						else{
						%>
						<option value="Visa, MasterCard">Visa/MasterCard</option>
						<%
						}
						if(paymentMode.trim().length()!=0 && paymentMode.equalsIgnoreCase("American Express")) {
						  %>
				      <option value="American Express" selected="selected">American Express</option>
						<%
						}
						else{
						%>
						<option value="American Express">American Express</option>
						<%
						}
						%>
					      </select>
					      </div></td>
                           <td width="65"  class=" "><span class="tableRight">
                            <input name="submit" type="submit" class="gradBtn" value="Submit" />
                           </span></td>
                           </tr>
                         </form>
					    <%
					ArrayList arrList = (ArrayList)request.getAttribute("objMembershipTrans");
					if(arrList!=null && arrList.size()!=0){
%>
<div class="rowExpand"><strong>Transaction Details:</strong></div>
</table>
 <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                               
				<tr>
					<td width="70"  height="27" class="tblRowHeadTwo">Item NO </td>
					<td width="72"  class="tblRowHeadTwo">Payment Mode</td>
					<td  width="78" class="tblRowHeadTwo">Class</td>
					<td width="87"  class="tblRowHead">Amount($)</td>
					<!--<td  width="81" class="tblRowHead">Account NO </td> -->
					<td width="105" bgcolor="#E3E1D2" class="tblRowHead">Description</td>
				</tr>
<%
 
							Iterator itr = 	arrList.iterator();
							while(itr.hasNext()) {				   
								HLCMemberShipTransactionVO objAccTransVO = (HLCMemberShipTransactionVO) itr.next();
									  String itemNo = objAccTransVO.getItemNo();
									String tempPaymentMode = objAccTransVO.getPaymentMode();
									String accountClass = objAccTransVO.getAccountClass();
									double amount = objAccTransVO.getAmount();
									//Date transactionDate = objAccTransVO.getTransactionDate();
									//String accountNo = objAccTransVO.getAccountNo();
									String desc = objAccTransVO.getDesc();
								//accountNo

%>
			<form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="QBXMLRequest.do" >
				<input type="hidden" name="process" value="salesReceiptAdd" />
				<input type="hidden" name="paymentMode" value="<%=paymentMode%>"/>
				<input type="hidden" name="transDate" value="<%=formatter.format(today)%>">
                                        <!-- For the Repayment Option  -->
                                        <tr>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=itemNo%></td>								
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=tempPaymentMode%></td>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=accountClass%></td>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=amount%></td>
<!--                                            <td bgcolor="#E3E1D2" class="alignCenter"></td> -->
											<td bgcolor="#E3E1D2" class="alignCenter"><%=desc%></td>
                                        </tr>
								<%
			}
			%>
			    <tr>					 
					<td  colspan="6" class="alignCenter">
					<input type="button" value="Back" class="gradBtn" onclick="location.href='./acctrans.do?process=initSyncTrans'"/>&nbsp; 
					 </td>
					</tr>
					  </form>
			<%
			 }
			 else{
			%>	
			<td  colspan="6" bgcolor="#E3E1D2" class="alignCenter">
					<strong>No Records Are found</strong>&nbsp; 
					 </td>
			<%
			}
			%>
			   
                          
					      
                       </table></td>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['myform'].elements['transDate']);
	 cal1.year_scroll = true;
	 cal1.time_comp = false;
</script>

</html>