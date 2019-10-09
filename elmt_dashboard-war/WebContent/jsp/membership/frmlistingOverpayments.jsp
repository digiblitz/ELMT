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
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import="com.hlcmrm.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script>
function postData(listValue){
	if(listValue!="" || listValue.value.indexOf(' ')!=0){
		strURL = "./overPaySection.do?process=listpay&statusList="+listValue;
		window.location.href = strURL;	
	}
	else{
		alert('Select any one of the status to list the values');
		document.statusForm.statusList.focus();
	}
}
    function submitForm(btnvalue) {
		strURL = "./overPaySection.do?process="+btnvalue;
		window.location.href = strURL;	
    }
</script>
<%! 

SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%! String dateCheck(Date fieldName){
	String detValue = "N/A";
		if(fieldName!=null){
		 detValue = sdf.format(fieldName);
		}
	return detValue;
}

%>
<%! float floatCheck(float fieldName){
	float floateValue = 0;
		if(fieldName!=0.0){
		 floateValue = fieldName;
		}
	return floateValue;
}

%>
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Refund Over Payment Listings </span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The Refund Over Payment Listings are listed as follows. <br /><br />
		To 'Approve' an application, click on the <strong>'Approve'</strong> button beside it. <br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="6">
				<form name="statusForm" action="/RefundList.do">
				<%
					String stat = request.getParameter("statusList");
					if(stat==null){
						stat ="";
					}
				%>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td colspan="3" width="200" valign="bottom" class="alignRight">&nbsp;<strong>Over Payment Status :</strong> </td>
						<td colspan="3" width="230" valign="bottom" class="alignLeft">
						<select class="selectboxOne" name="statusList" onchange="postData(this.value);">
						<%
						if(stat.equalsIgnoreCase("Pending")){
						%>
							<option value="">Select One</option>						
							<option selected="selected" value="Pending">Pending</option>
							<option value="Approved">Approved</option>
						<%
						}
						else if(stat.equalsIgnoreCase("Approved")){
						%>
							<option value="">Select One</option>						
							<option value="Pending">Pending</option>
							<option selected="selected" value="Approved">Approved</option>							
						<% }
						else{ %>
							<option  selected="selected" value="">Select One</option>						
							<option value="Pending">Pending</option>
							<option value="Approved">Approved</option>													
						<% } %>
						</select>
							</td>
					  </tr>
					</table>
				</form>
			</td>
		  </tr>		 
		  <!--
		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>
		  -->
		  <tr>
  			<td width="88" class="tblRowHeadTwo">Member ID </td>
			<td width="82" height="27" class="tblRowHeadTwo">First Name</td>
			<td width="83" class="tblRowHeadTwo">Last Name</td>
			<td width="75" class="tblRowHeadTwo">Excess Amount</td>
			<td width="82" class="tblRowHeadTwo">Payment Date</td>						
			<td width="83" class="tblRowHeadTwo">Approve </td>
		   </tr>
		  <tr>
		  
                   <%
                        int i=0;
						ArrayList reqList=new ArrayList();
						reqList = (ArrayList) request.getAttribute("RefundReq");
						
						if(reqList!=null && reqList.size()>0){
							Iterator iterator =  reqList.iterator();
							while(iterator.hasNext()){
								HLCMembershipRefundDetails refDetail = (HLCMembershipRefundDetails) iterator.next();
								
								String refundId = refDetail.getRefundId();
								double balAmt = refDetail.getBalanceAmount();
								String firstName = refDetail.getFirstName();
								String lastName = refDetail.getLastName();
								String memberId = refDetail.getMemberId();
								Date claimdte = refDetail.getClaimDate();

								%>
								<form name="frmOverPay" id="myform" method="post" action="./overPaySection.do">
								<input type="hidden" name="process" value="submt" />
								<tr>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(memberId)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(firstName)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(lastName)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=amtFormat(String.valueOf(balAmt))%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=dateCheck(claimdte)%></td>
									<input type="hidden" name="refundId" value="<%=refundId%>" />
									<input type="hidden" name="memberId" value="<%=memberId%>" />
									<input type="hidden" name="id" value="<%=i%>">
									<%
									if(stat.equalsIgnoreCase("Pending")){
										%>
										<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit2" value="Approve" class="oneBtn" /></td>
										<% 
									} 
									else{
										%>
										<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit2" value="View" class="oneBtn" /></td>									
										<%
									}
									%>
							   </tr>
							   </form>
							  <%                      
							}
						}
						else{%>
							<tr>
							  <th height="25" colspan="6"><center> No Over Payment Refunds Available !! </center></th>
							</tr>
						<%}%>
		  <tr>
			<td height="19" colspan="6">&nbsp;</td>
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
