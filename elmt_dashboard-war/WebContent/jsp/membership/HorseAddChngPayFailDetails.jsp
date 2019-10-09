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

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlccommon.util.HLCHorseRegisterationVO"%>
<%@ page import="com.hlchorse.form.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmHorseReg.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<div class="rowHead">
							Owner Information:
				</div>	
				
			
				
		<div class="cmmnForm">
		<div class="colspan">
			<strong>Membership:</strong> <span class="styleBoldTwo">Horse Add/Change - Owner/Rider Payment Failed Details </span> 
		</div>
			<div id="commonBG" class="textCommon"> You	are	viewing	the	details	of the horse registered by you. </div>
			<div class="rowExpand" onclick="expandColl('parta');"> Horse Add/Change - Owner/Rider Failed Payment Details:</div>
		<div id="parta" class="formPart">
 	 	</div>
		<div class="rowHead">Horse Registration Details:</div>
						
<%
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 	HLCHorseRegisterationVO HorseDisp = (HLCHorseRegisterationVO) request.getAttribute("ListVO");
	String horseMemberId = (String) HorseDisp.getHorseMemberId();
    String competitionName =(String) HorseDisp.getCompetitionName();
    String registeredName = (String) HorseDisp.getRegisteredName();
    String baRegisteredName = (String)HorseDisp.getBaRegisteredName();
    String baPastName = (String)HorseDisp.getBaPastName();
	String ownerId = (String)HorseDisp.getOwnerId();	
   
	String paymentId = (String)HorseDisp.getPaymentId();

	String regstrBy = (String)HorseDisp.getRegisteredBy();
	String membertype=(String)HorseDisp.getMemberTypeName();
	String statusName = (String)HorseDisp.getStatusName();
	Date actDate = HorseDisp.getActivationDate();
	Date upgraDate = HorseDisp.getUpgradationDate();

	%>
				<div class="row">
					<span class="label">Horse Registration Status:</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(statusName)%></span></span>
			  	</div>
				<div class="row">
					<span class="label">Horse Registration Type:</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(membertype)%></span></span>
			  	</div>
			    <div class="row">
					<span class="label">Activation Date :</span>
					<span class="formX"><span class="styleBoldOne"><%=dateCheck(actDate)%></span></span>
	            </div>
				<div class="rowHead">Horse Information Section:</div>						
				<div class="row">
					<span class="label">Horse Member Id: </span>
					<span class="formX"><strong><%=nullCheck(horseMemberId)%></strong></span>
			  	</div>
				<div class="row">
					<span class="label">Horse Name: </span>
					<span class="formX"><strong><%=nullCheck(competitionName)%></strong></span>
				</div>
				<div class="row">
					<span class="label">Registered By:</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(regstrBy)%></span></span>
				</div>								
				<div class="row">
					<span class="label">Registered Name:</span>
					<span class="formX"><%=nullCheck(registeredName)%></span>
				</div>
				<div class="row">
					<span class="label">Past Name:</span>
					<span class="formX"><%=nullCheck(baPastName)%></span>
				</div>
				<div class="row">
					<span class="label">Breed Assoc. Horse is registered with:</span>
					<span class="formX"><%=nullCheck(baRegisteredName)%></span>
				</div>
						
				<div class="row">
				
				<div class="rowHead">Transaction Details:</div>
				<% 
				String payinfo[] =(String[]) request.getAttribute("paydet");
				String checkNumber ="";
			    String paymentStatus = "";
				String checkName ="";
			    String sslTxnId = "";
				String bankName ="";
			    String checkDate = "";
				String amount = "";
				String checkAmount = "";
				String temAmt ="";
				String check_date="";
				String check_amount="";
				String ssl_result="";
				String pending_amount="";
				String tempPaymentId="";
				float paidAmt=0.0f;
				String cardStatus= "";
				String cardType = "";
				String cardName = "";
				if(payinfo!=null && payinfo.length !=0){
				 checkNumber =  payinfo[0];
				 paymentStatus =  payinfo[1];
				 checkName =  payinfo[2];
				 sslTxnId = payinfo[3];
				 bankName =  payinfo[4];
				 checkDate =  payinfo[5];
				 amount =  payinfo[6];
				 checkAmount =  payinfo[7];
				 ssl_result = payinfo[8];
				 tempPaymentId = payinfo[9];
				 pending_amount = payinfo[10];
				 cardName = payinfo[11];
				 cardType = payinfo[12];
				 cardStatus = payinfo[13];
				}
				if(pending_amount!=null || pending_amount.trim().length()!=0){
					paidAmt= Float.parseFloat(amount)-Float.parseFloat(pending_amount);
				}
				String output2="";
								DecimalFormat myForm = new DecimalFormat("######.00");
								double finalAmt1 = 0.0f;
				if(paymentStatus.equalsIgnoreCase("check"))
				{
					String chk_dte ="";
					if(checkDate!=null){ 
						chk_dte=checkDate.substring(0,10);	
					}
				%>
				<div class="row">
						<span class="label">Check Number:</span>
						<span class="formX"><%=checkNumber%></span>
				</div>
				<div class="row">
						<span class="label">Name On Check:</span>
						<span class="formX"><%=nullCheck(checkName)%></span>
				</div>
				<div class="row">
						<span class="label">Check Date:</span>
 						<span class="formX"><%=chk_dte%></span>
				</div>
				<div class="row">
						<span class="label">Bank Name:</span>
						<span class="formX"><%=nullCheck(bankName)%></span>
				</div>
				<div class="row">
						<span class="label">Amount($):</span>
						<span class="formX"><%=nullCheck(amount)%></span>
				</div>
				<div class="row">
						<span class="label">Check Enclosed Amount($):</span>
						<span class="formX"><%=nullCheck(checkAmount)%></span>
				</div>
				<%	}
				else
					{ %>
					<div class="row">
							<span class="label">Credit Card Type:</span>
							<span class="formX"><%=nullCheck(cardType)%></span>
					</div>
					<div class="row">
							<span class="label">Name on Credit Card :</span>
							<span class="formX"><%=nullCheck(cardName)%></span>
					</div>
					<div class="row">
							<span class="label">Card Status:</span>
							<span class="formX"><%=nullCheck(cardStatus)%></span>
					</div>						
					<div class="row">
							<span class="label">Transaction Id:</span>
							<span class="formX"><%=nullCheck(sslTxnId)%></span>
					</div>
					<div class="row">
							<span class="label">Actual Amount To Pay($):</span>
							<span class="formX"><%=nullCheck(amount)%></span>
					</div>								
					<% 
					if (Float.parseFloat(pending_amount)==0){
					%>
					<div class="row">									
						<span class="label">Paid Amount($):</span>
						<span class="formX"><%=pending_amount%></span>
					</div>
					<div class="row">
						<span class="label">Balance Amount($):</span>
						<span class="formX"><%=paidAmt%></span>
					</div>
					<% }	
					}	 %>	
  					<form name="horsepaymentfaileddet" action="ChngeRePay.do" method="post">
					<input type="hidden" name="process" value="chngrepay"  />
					<input type="hidden" name="horseMemberId" value="<%=horseMemberId%>" />
					<input type="hidden" name="totalAmount" value="<%=amount%>" />
					<input type="hidden" name="paymentId" value="<%=tempPaymentId%>" />
					<input type="hidden" name="horseName" value="<%=competitionName%>"/>
					<div class="row">
						<span class="label">&nbsp;</span>
						<span class="formX"> <input type="submit" value="Pay Amount" name="back" class="gradBtn" /></span>
			  		</div>
					</form></tr>
						 
						 
		 
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