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
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<body>
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
		System.out.print(Double.parseDouble(amtVal));
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong> Membership</strong>: <span class="styleBoldTwo">Over Payment Approved Form</span> </td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">&nbsp;</td>
  </tr>
  <tr>
  	<td>
	
		<form name="frmAdvMag" id="myform" method="post" action="./AdminRefundStatusDisp.do?process=update" onsubmit="return myvalidate(this);">
        
		<%
				HLCMembershipRefundDetails objRefDetails = (HLCMembershipRefundDetails) request.getAttribute("memRefund");
				
                String memberId = objRefDetails.getMemberId();
                double claimAmt = objRefDetails.getClaimAmount();
                String firstName = objRefDetails.getFirstName();
                String lastName = objRefDetails.getLastName();
                String mailId = objRefDetails.getMailId();
                Date claimDte = objRefDetails.getClaimDate();
				String refundId = objRefDetails.getRefundId();
				String comments = objRefDetails.getComments();
				String bankName = objRefDetails.getBankName();
				Date refundDte = objRefDetails.getRefundDate();
				Date chequeDte = objRefDetails.getCheckDate();
				String refundBy = objRefDetails.getRefundedBy();
				int chqNo = objRefDetails.getCheckNumber();
				double balAmt = objRefDetails.getBalanceAmount();
        %>
		<input type="hidden" name="refundId" value="<%=refundId%>" />
        <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
          <tr> 
            <td colspan="2" class="tblRowHead">Approved Refund:</td>
          </tr>
		  <tr> 
            <td class="tableLeft">Date Of Request:</td>
            <th class="tableRight"><%=dateCheck(claimDte)%></th>
          </tr>
		  <tr>
            <td class="tableLeft">Member ID:</td>
            <th class="tableRight"><%=memberId%></th>
		  </tr>
          <tr> 
            <td class="tableLeft">First Name:</td>
            <th class="tableRight"><%=firstName%></th>
          </tr>
          <tr> 
            <td class="tableLeft">Last Name:</td>
            <th class="tableRight"><%=lastName%></th>
          </tr>
          <tr> 
            <td class="tableLeft">E-Mail ID: </td>
            <th class="tableRight"><%=mailId%></th>
          </tr>
          <tr> 
            <td class="tableLeft">Claim Amount:</td>
            <td class="tableRight"><span class="styleBoldOne"><%=balAmt%></span></td>
          </tr>
          <tr> 
            <td class="tableLeft">Comments: </td>
            <td class="tableRight"><%=nullCheck(comments)%></td>
          </tr>				
          <tr> 
            <td class="tableLeft">Refund Date: </td>
            <td class="tableRight"><%=dateCheck(refundDte)%></td>
          </tr>
          <tr> 
            <td class="tableLeft">Refunded By: </td>
            <td class="tableRight"><%=nullCheck(refundBy)%></td>
          </tr>
          <tr> 
            <td class="tableLeft">Cheque date: </td>
            <td class="tableRight"><%=dateCheck(chequeDte)%></td>
          </tr>
          <tr> 
            <td class="tableLeft">Cheque number: </td>
            <td class="tableRight"><%=chqNo%></td>
          </tr>
          <tr> 
            <td class="tableLeft">Bank name: </td>
            <td class="tableRight"><%=nullCheck(bankName)%></td>
          </tr>
          <tr id="chrgCrd"> 
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr> 
            <td class="tableRight">&nbsp;</td>
            <td class="tableRight"><input type="button" value="Back" class="gradBtn" onclick="window.location.href('./overPaySection.do?process=listpay');"/></td>
          </tr>
        </table>			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
