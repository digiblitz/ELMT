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
function checkForDate(){
	if((document.myform.transDate.value!="")||(document.myform.transDate.value.indexOf(' ')==0)){
	
			dtStr = document.myform.transDate.value;
			var daysInMonth = DaysArray(12);
			var pos1=dtStr.indexOf(dtCh);
			var pos2=dtStr.indexOf(dtCh,pos1+1);
			var strMonth=dtStr.substring(0,pos1);
			var strDay=dtStr.substring(pos1+1,pos2);
			var strYear=dtStr.substring(pos2+1);
			strYr=strYear;
			if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
			if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
			for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
			}
			month=parseInt(strMonth);
			day=parseInt(strDay);
			year=parseInt(strYr);
			if (pos1==-1 || pos2==-1){
			alert("The date format should be : mm/dd/yyyy");
			document.myform.transDate.focus();
			return false;
			}
			if (strMonth.length<1 || month<1 || month>12){
			alert("Please enter a valid month");
			document.myform.transDate.focus();
			return false;
			}
			if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			alert("Please enter a valid day");
			document.myform.transDate.focus();
			return false;
			}
			if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			alert("Please enter a valid 4 digit Year");
			document.myform.transDate.focus();
			return false;
			}
			if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			alert("Please enter a valid date");
			document.myform.transDate.focus();
			return false;
			}
	}
}

function onValidate(){
	if((document.myform.transDate.value=="" || document.myform.transDate.value.indexOf(' ')==0 ) 
			&& (document.myform.CheckNumber.value=="" || document.myform.CheckNumber.value.indexOf(' ')==0)){
		alert("Enter either Transaction Date Or Check Number to proceed");
		document.myform.transDate.focus();
		return false;
	}
   
   if(document.myform.transDate.value!="" || document.myform.transDate.value.indexOf(' ')!=0){
	checkForDate();
   }
   
  /* if(document.myform.paymentMode.selectedIndex==0){
 	alert("Please select any of the Payment Method");
	document.myform.paymentMode.focus();
	return false;
   }*/
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
						<td colspan="2" class="tblMainHead">
						Accounts: <span class="styleBoldTwo">Transaction Details</span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp"><strong>Click on the Check Number which you want to Hold/Unhold a transaction.</strong> <br />
						</td>
					  </tr>
					  
					 <tr>
					   <td>
					   <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                         <form name="myform" id="myform" method="post" action="acctrans.do" onsubmit="return onValidate();" >
                           <input type="hidden" name="process" value="listTransDet" />
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
						String checkNo="";
						if(request.getParameter("CheckNumber")!=null){
							checkNo = request.getParameter("CheckNumber");
						}
						String transDte = "";
						if(request.getParameter("transDate")!=null){
							transDte = request.getParameter("transDate");
						}
						
					%>
					<td width="74"  class=" "><div align="right">Check Date:</div></td>
					<td width="162"  class=" ">
					  <div align="left">
					  <% if(transDte.trim().length()==0){
					  %>
					    <input type="text" name="transDate" id="transDate" size="16" value="<%=formatter.format(today)%>"  />
					  <%
					  }
					  else{ 
					  %>
					  <input type="text" name="transDate" id="transDate" size="16" value="<%=transDte%>"  />
					  <%
					  }
					  %>
					      <span class="formX">
					        
			        <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> </span> </div></td>
					<td width="78"  class=" "><div align="right">Check No :</div></td>
					<td width="115"  class=" ">
					  <div align="left">
					    <input type="text" name="CheckNumber" class="textboxOne" value="<%=checkNo%>" />
					      </div></td>
                           <td width="65"  class=" "><span class="tableRight">
                            <input name="submit" type="submit" class="gradBtn" value="Submit" />
                           </span></td>
                           </tr>
                         </form>
					</table>
 <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                               
				<tr>
					<td width="70"  height="27" class="tblRowHeadTwo">Item No </td>
					<td width="72"  class="tblRowHeadTwo">Description</td>
					<td  width="78" class="tblRowHeadTwo">Total Amount ($) </td>
					<td width="87"  class="tblRowHead">Check Amount ($) </td>
					<!--<td  width="81" class="tblRowHead">Account NO </td> -->
					<td width="80" bgcolor="#E3E1D2" class="tblRowHead">Check Number </td>
					<td width="80" bgcolor="#E3E1D2" class="tblRowHead">Name On Check</td>
				</tr>
					<div class="rowExpand"><strong>Transaction Details:</strong></div>				
					<%
					ArrayList arrList = (ArrayList)request.getAttribute("objMembershipTrans");
					if(arrList!=null && arrList.size()!=0){

							Iterator itr = 	arrList.iterator();
							while(itr.hasNext()) {
								HLCMemberShipTransactionVO objAccTransVO = (HLCMemberShipTransactionVO) itr.next();
									String itemNo = objAccTransVO.getItemNo();
									String description = objAccTransVO.getDesc();
									double totalAmount = objAccTransVO.getAmount();
									double checkAmount = objAccTransVO.getCheck_amount();									
									String checkNumber = objAccTransVO.getCheck_number();
									String checkName = objAccTransVO.getCheck_name();
									String paymentId = objAccTransVO.getPayment_id();
					%>
						<form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="QBXMLRequest.do" >
						<input type="hidden" name="process" value="salesReceiptAdd" />
						<input type="hidden" name="paymentMode" value="<%=paymentMode%>"/>
						<input type="hidden" name="transDate" value="<%=formatter.format(today)%>">
							<!-- For the Repayment Option  -->
							<tr>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=itemNo%></td>								
								<td bgcolor="#E3E1D2" class="alignCenter"><%=description%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=totalAmount%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=checkAmount%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><a href="./acctrans.do?process=detailTrans&pId=<%=paymentId%>"><%=checkNumber%></a></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=checkName%></td>
							</tr>
					  </form>							
					<%
					 	}
					 }
					 else{
					%>	
						<td  colspan="6" bgcolor="#E3E1D2" class="alignCenter">
						<strong>No Records Are found</strong>&nbsp; 
						</td>
					<%
					}
					%>
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
<script language="javascript">
	var cal1 = new calendar2(document.forms['myform'].elements['transDate']);
	 cal1.year_scroll = true;
	 cal1.time_comp = false;
</script>

</html>
