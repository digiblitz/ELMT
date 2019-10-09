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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlcaccounts.util.HLCAccTransactionVO"%>
<%@ page import="com.hlcform.util.*"%>
<%@ page import="com.hlcform.util.HLCPaymentDetailVO"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmTransAdmin.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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

<%! 				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){					
						Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
					%>

</head>

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
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Transaction Details </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp"><strong>Fill in the following information.</strong> <br />
	</td>
  </tr>
  <tr>
  	<td>
		  <form name="frmTransacDet" id="frmTransacDet" method="post" action="MemberRegiList.do" onSubmit="sumUp(); return myvalidate();">		<!--return myvalidate();-->
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				 <% 
				 boolean rev_status = false; 
				 HLCPaymentDetails objPayment = (HLCPaymentDetails) request.getAttribute("objPayment");
				 ArrayList subTransac = (ArrayList) request.getAttribute("subTransac");
	
				float totAmt = 0.0f;
				float paidAmt = 0.0f;
				float initAmtPaid = 0.0f;
				float totSubPay = 0.0f;
				ArrayList itrAmount = new ArrayList();
				ArrayList chkDet = new ArrayList();
				
				String firstNme = (String) request.getAttribute("fnme");
				String lastNme = (String) request.getAttribute("lnme");	
				String memberId = (String) request.getAttribute("mid");			                                
				if(firstNme==null){
					firstNme = "";
				}
				if(lastNme == null){
					lastNme = "";
				}
				if(memberId == null){
					memberId = "";
				}				
				if(subTransac!=null){
					Iterator itr = subTransac.iterator();
					while(itr.hasNext()){
							HLCPaymentDetails subObjPay = (HLCPaymentDetails) itr.next();
							float chkAmt = subObjPay.getCheckAmount();
							double actAmt = subObjPay.getAmount();
							float pendAmt = subObjPay.getPendingAmount();
							String payMode = subObjPay.getPaymentStatus();
							String bankName = subObjPay.getBankName();
							String nmeOnChk = subObjPay.getCheckName();
							String chkNo = subObjPay.getCheckNumber();
							Date chkDte = subObjPay.getCheckDate();
							boolean nsfStatus = subObjPay.isNsf_charge_status();
							if(payMode.equalsIgnoreCase("check")){
								if(actAmt>0){
									if(chkAmt>actAmt){
										totSubPay += actAmt;
									}
									else{
										totSubPay += chkAmt;
									}
									String newEntry = chkAmt+"#"+subObjPay.getPaymentStatus()+"#"+payMode+"#"+chkNo+"#"+nmeOnChk+"#"+chkDte+"#"+bankName;
									itrAmount.add(String.valueOf(newEntry));
								}
							}
							else{
									String tmp = String.valueOf(actAmt);
									totSubPay += Float.parseFloat(tmp);
									String newEntry = actAmt+"#"+subObjPay.getCcType()+"#"+payMode;
									itrAmount.add(String.valueOf(newEntry));
							}
						}
				}
				if(objPayment != null){
					 double actualAmt = objPayment.getAmount();
					 float chkAmt = objPayment.getCheckAmount();
					 float pendAmt = objPayment.getPendingAmount();
					 String payModde = objPayment.getPaymentStatus();
					 initAmtPaid = Float.parseFloat(String.valueOf(actualAmt))-(totSubPay+pendAmt);
				 %>
				 <tr>
				 <td><br /><br />
			  	 <span class="textBold">Member Name </span>&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=firstNme%> <%=lastNme%></span><br /><br />
				 <span class="textBold">Member ID </span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=memberId%></span>
				 <table class="tblInnerContainer" id="paymentTable" border="0" cellpadding="0" cellspacing="1">
				 <caption><strong>History Of Payment</strong></caption>
				 <tr>
					  <td colspan="3" class="tableLeft">Actual Amount To Pay &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><%=actualAmt%></strong></td>
				 </tr>		 	
				 <tr>
					  <td  class="tableLeft" colspan="3">Pending Amount to be paid &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <strong><%=pendAmt%></strong></td>
				 </tr>
				 <%
				 HLCPaymentDetailVO bounceDet = (HLCPaymentDetailVO) request.getAttribute("bounceDet");
				 if(bounceDet!=null && bounceDet.getNsfDate()!=null){
					float NFScheckAmt = bounceDet.getCheckAmount();
					String NFScheckNo = bounceDet.getCheckNumber();
					String NFSpayMode = bounceDet.getPaymentStatus();
					String NFScheckNme = bounceDet.getCheckName();
					Date NFScheckDte = bounceDet.getCheckDate();
					String NFSbankNme = bounceDet.getBankName();
	                float NFSpendingAmt = bounceDet.getPendingAmount();
                	String NFSPayId = bounceDet.getPaymentId();
                	String NFSParentPayId = bounceDet.getParentPaymentId();					
					Date NFSDte = bounceDet.getNsfDate();  
					rev_status = bounceDet.isReverse_entry_status();
				 %>				 
				 <tr>
				 <td>
					 <table class="tblInnerContainer" width="100%" id="NSFTable" border="0" cellpadding="0" cellspacing="1">
					 <caption><strong>NSF Check Details.</strong></caption>
						 <tr>
							 <td class="tblRowHeadTwo">Check Number</td>
							 <td class="tblRowHeadTwo">Check Amount</td>
							 <td class="tblRowHeadTwo">Check Date</td>
							 <td class="tblRowHeadTwo">Name On Check</td>
							 <td class="tblRowHeadTwo">Bank name</td>
							 <td class="tblRowHeadTwo">NSF Date</td>
						 </tr>
						 <tr>
							 <td class="tableRight"><%=NFScheckNo%></td>
							 <%
							 if(NFSParentPayId!=null){
								 %>
								 <td height="20" class="tableRight"><span class="styleBoldOne"><%=NFScheckAmt%></span></td>
								 <input name="NSFAmt" type="hidden" value="<%=NFScheckAmt%>" />
								 <%}
							 else{
								 %>
								 <td height="20" class="tableRight"><span class="styleBoldOne"><%=NFSpendingAmt%></span></td>
								 <input name="NSFAmt" type="hidden" value="<%=NFSpendingAmt%>" />
								 <%
							 }
							 %>
							 <td height="20" class="tableRight"><span class="styleBoldOne"><%=dateFormat(NFScheckDte.toString())%></span></td>
							 <td height="20" class="tableRight"><span class="styleBoldOne"><%=NFScheckNme%></span></td>
							 <td height="20" class="tableRight"><span class="styleBoldOne"><%=NFSbankNme%></span></td>
							 <td height="20" class="tableRight"><span class="styleBoldOne"><%=dateCheck(NFSDte)%></span></td>
							 <input type="hidden" name="NSFPayId" value="<%=NFSPayId%>" />
						 </tr>
					 </table>
				 </td>
				 </tr>				 	
				 <%
				 }else{
				 %>		
	 				 <input name="NSFAmt" type="hidden" value="0" />
				 <% 
				 } 
				 %>				 
				 <tr>
				 <table width="100%">	
					<tr>
						<td class="tblRowHeadTwo">Payment Iteration</td>
						<td class="tblRowHeadTwo">Amount Paid</td>
						<td class="tblRowHeadTwo">Payment Mode</td>
						<td class="tblRowHeadTwo">Check Number</td>
						<td class="tblRowHeadTwo">Name On Check</td>
						<td class="tblRowHeadTwo">Check Date</td>
						<td class="tblRowHeadTwo">Bank Name</td>
					</tr>	
						<tr class="tableLeft">
							<td class="tableRight">Initial Paid Amt</td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=initAmtPaid%></span></td>
							<% if(payModde.equalsIgnoreCase("check")){
							%>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=objPayment.getPaymentStatus()%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=objPayment.getCheckNumber()%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=objPayment.getCheckName()%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=dateFormat(objPayment.getCheckDate().toString())%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=objPayment.getBankName()%></span></td>
							<%}
							else{
							%>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=initAmtPaid%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=objPayment.getPaymentStatus()%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;<%=objPayment.getCcType()%></span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;</span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;</span></td>
							<td class="tableRight"><span class="styleBoldOne">&nbsp;</span></td>						
							<% } %>
						</tr>
						<%
						if(itrAmount!=null){
								if(itrAmount.size()>0){
								int j=1;
								Iterator itr = itrAmount.iterator();
									while(itr.hasNext()){
								%>
								<tr class="tableLeft">	
										<td class="tableRight">Payment <%=j%></td>
									<%
											String val  = (String) itr.next();	
											String[] val_mode = val.split("#");
											if(val_mode[2].equalsIgnoreCase("check")){
									%>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=val_mode[0]%></span></td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=val_mode[2]%></span></td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=val_mode[3]%></span>&nbsp;</td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=val_mode[4]%></span>&nbsp;</td>									  									  
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=dateFormat(val_mode[5])%></span>&nbsp;</td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=val_mode[6]%></span>&nbsp;</td>									  									  
									<%}
									else{ %>
													  <td class="tableRight"><span class="styleBoldOne"><%=val_mode[0]%></span>&nbsp;</td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=val_mode[2]%></span></td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;</span></td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;</span>&nbsp;</td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;</span>&nbsp;</td>									  									  
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;</span>&nbsp;</td>
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;</span>&nbsp;</td>									  									  
									<%}
									j++;
									%>
								</tr>
								<%	}
								%>
								<%}
							}
						 %>
						 
					</table>
					</tr>
					 </table>
					 <input type="hidden" name="amount" id="amount" value="<%=actualAmt%>" />
					 <%
				 }
				 %>
			</td>
			</tr>

			  <tr>
			  <td>
			  <table class="tblInnerContainer" id="contentTable" border="0" cellpadding="0" cellspacing="1">

			  <tr>
					<th height="26" width="350" class="tblRowHeadTwo">Item Name</th>
					<th height="26" class="tblRowHeadTwo">Amount</th>		
					<th height="26" class="tblRowHeadTwo">Reconcile Amount</th>		
					<th height="26" class="tblRowHeadTwo">Sub Payment</th>	
					<th height="26" class="tblRowHeadTwo">Transaction Mode</th>	
					<th height="26" class="tblRowHeadTwo">Payment Mode</th>
					<th height="26" class="tblRowHeadTwo">NSF</th>
			  </tr>
				<% int reconCount = 1;
					int recon =0;
					ArrayList entryList = (ArrayList)request.getAttribute("transacEntries");
					System.out.print("Inside entryList  "+entryList.size());
					if(entryList!=null){
					if(entryList.size()>0){
					System.out.print("Inside entryList");
						Iterator itr = entryList.iterator();
						int indx =0;
						int listSize = entryList.size();;
						String amtName;
						String payType; 
						String ccType; 
						String transId;
						String actualAmount;
						String revBox;
						String revamtName;
				%>
						
						<input type="hidden" value="<%=listSize%>" name="listSz" />
						<input type="hidden" value="1" name="count"> 
						
				<%		while(itr.hasNext()){
							HLCAccTransactionVO accTxnVO = (HLCAccTransactionVO) itr.next();
							
							String accType = accTxnVO.getAccount_type();
							String accNo = accTxnVO.getAccount_no();
							String subaccNo = accTxnVO.getSub_account_no();
							String accDesc = accTxnVO.getDescription();
							String accClassType = accTxnVO.getClass_Typ();
							int accQty = accTxnVO.getQuantity();
							float accAmt = accTxnVO.getAmount();
							Date accTransDte = accTxnVO.getTransaction_date();
							Date accSynDte = accTxnVO.getSync_date();
							boolean accSyncStat = accTxnVO.isSync_status();
							String accUserId = accTxnVO.getUser_id();
							boolean activeStat = accTxnVO.isActive_status();
							String accItemNo = accTxnVO.getItem_no();
							String accPayMode = accTxnVO.getPayment_mode();
							float accReconcileAmt = accTxnVO.getReconcile_amount();
							String accTransMode = accTxnVO.getTransaction_mode();
							boolean accReconcileStat = accTxnVO.isReconcile_status();
							String accTransId = accTxnVO.getTransaction_id();	
							amtName = "amtName"+indx;
							payType = "payType"+indx;
							ccType = "ccType"+indx;
							transId = "transId"+indx;
							revamtName = "revamtName"+indx;
							revBox = "revBox"+indx;							
							actualAmount = "actualAmount"+indx;
							String divValue = accDesc+"#"+accTransId+"#"+(indx+1);
							String revEntry = accType+"#"+accNo+"#"+subaccNo+"#"+accDesc+"#"+accClassType+"#"+accQty+"#"+accAmt+"#"+accUserId+"#"+accItemNo+"#"+accPayMode+"#"+accReconcileAmt+"#"+accTransMode+"#"+accTransId+"#"+objPayment.getPaymentId()+"#"+objPayment.getUserId();
							indx++;
					%>	
				
				<input type="hidden" name="transacId" value="<%=accTransId%>" />
				<input type="hidden" name="memRegiListProcess" value="adminEntry" />
				<input type="hidden" name="checkAmt" value="<%=objPayment.getCheckAmount()%>" />
				<input type="hidden" name="<%=actualAmount%>" id="<%=actualAmount%>" value="<%=accAmt%>" />
				
				  <tr>
					<td height="26" width="350" bgcolor="#E3E1D2" class="alignLeft"><%=accDesc%></td>
					<td height="26" width="50" bgcolor="#E3E1D2" class="alignCenter"><%=accAmt%></td>					
					<%
					if(accAmt<0){
					%>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignLeft"><input name="<%=amtName%>" type="text" id="<%=amtName%>" value="<%=accReconcileAmt%>" class="textboxOne" size="10" disabled="disabled" /></td>
					<% 
					}
					else{
						if(accDesc.equalsIgnoreCase("Over Payment")){					
						%>
						<td height="26" width="10" bgcolor="#E3E1D2" class="alignLeft"><input name="<%=amtName%>" type="text" id="<%=amtName%>" value="0" class="textboxOne" size="10" onblur="sumUp();" disabled="disabled"/></td>					

						<%
						}
						else{
						%>
						<td height="26" width="10" bgcolor="#E3E1D2" class="alignLeft"><input name="<%=amtName%>" type="text" id="<%=amtName%>" value="<%=accReconcileAmt%>" class="textboxOne" size="10" onblur="sumUp();"/>
									<input name="<%=revamtName%>" type="hidden" id="<%=revamtName%>" value="<%=accReconcileAmt%>" class="textboxOne" size="10" onblur="sumUp();"/></td>											
						<%}
					}
					if(accReconcileAmt==0 || accAmt<=0){
					%>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" value="snoVal" name="div<%=indx%>" id="div<%=indx%>" onclick="addRow('<%=divValue%>');" disabled="disabled"/></td>                                        					
					<%}
					else if (accTxnVO.isCheckStatus()==true){
					%>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" value="noVal" name="div<%=indx%>" id="div<%=indx%>" onclick="addRow('<%=divValue%>');" /></td>
					<% }
					else{ %>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" value="snoVal" name="div<%=indx%>" id="div<%=indx%>" onclick="addRow('<%=divValue%>');" disabled="disabled"/></td>                                        
					<% } 
                    %>
					<td height="26" width="25" bgcolor="#E3E1D2" class="alignLeft">
						<select name="<%=payType%>" id="<%=amtName%>" class="selectboxOne">
						<%
						if(accAmt<0){
						%>
						<option  selected="selected" value="Accept as Full" >Accept as Full</option>
						<%}
						else if(accDesc.equalsIgnoreCase("Over Payment")){
						%>
						<option  selected="selected" value="Accept as Full" >Accept as Full</option>
						<%
						}
						else if(accTransMode==null || accTransMode.trim().length()==0){%>
							<option selected="selected" value="" >Select One</option>
							<option value="Partial Payment">Partial Payment</option>
							<option value="Accept as Full">Accept as Full</option>
							<option value="Void">Void</option>						
						<% }
						else{
							if(accTransMode.equalsIgnoreCase("Partial Payment")){
							%>						
								<option value=""  selected="selected">Select One</option>
								<option  selected="selected" value="Partial Payment">Partial Payment</option>
								<option value="Accept as Full">Accept as Full</option>
								<option value="Void">Void</option>
							<% } 
							else if (accTransMode.equalsIgnoreCase("Accept as Full")){ %>
								<option value="">Select One</option>
								<option value="Partial Payment">Partial Payment</option>
								<option  selected="selected" value="Accept as Full">Accept as Full</option>
								<option value="Void">Void</option>
							<% } 
							else {
							%>
								<option value="" >Select One</option>
								<option value="Partial Payment">Partial Payment</option>
								<option value="Accept as Full">Accept as Full</option>
								<option selected="selected" value="Void">Void</option>
							<%}
						} %>
						</select>
					</td>
					<td height="26" width="25" bgcolor="#E3E1D2" class="alignLeft">
						<select name="<%=ccType%>" id="<%=ccType%>" class="selectboxOne" >

						<%
						if(accReconcileAmt!=0){
						if(accPayMode==null){
						%>
						  <option selected="selected" value="">Select One</option>
						  <option value="Check">Check</option>
						  <option value="Visa">Visa</option>
						  <option value="MasterCard">Master Card</option>					
						  <option value="American Express">AmEx</option>						
						<%
						}
						else{
							if(accPayMode.equalsIgnoreCase("check")){
							%>
							  <option  value="">Select One</option>
							  <option selected="selected" value="Check">Check</option>
							  <option value="Visa">Visa</option>
							  <option value="MasterCard">Master Card</option>					
							  <option value="American Express">AmEx</option>
							  <%
							  }
							  else if(accPayMode.equalsIgnoreCase("Visa")){
							  %>
							  <option value="">Select One</option>
							  <option value="Check">Check</option>
							  <option selected="selected" value="Visa">Visa</option>
							  <option value="MasterCard">Master Card</option>					
							  <option value="American Express">AmEx</option>
							  <%
							  }
							  else if(accPayMode.equalsIgnoreCase("MasterCard")){
							  %>
							  <option value="">Select One</option>
							  <option value="Check">Check</option>
							  <option value="Visa">Visa</option>
							  <option selected="selected" value="MasterCard">Master Card</option>					
							  <option value="American Express">AmEx</option>
							  <%
							  }
							  else{
							  %>
							  <option value="">Select One</option>
							  <option value="Check">Check</option>
							  <option value="Visa">Visa</option>
							  <option value="MasterCard">Master Card</option>					
							  <option selected="selected" value="American Express">AmEx</option>
							 <% }
							 }
							}
							else if(accAmt<0){
								if(accPayMode!=null){
									if(accPayMode.equalsIgnoreCase("check")){
							%>

								  <option  value="">Select One</option>
								  <option selected="selected" value="Check">Check</option>
								  <option value="Visa">Visa</option>
								  <option value="MasterCard">Master Card</option>					
								  <option value="American Express">AmEx</option>
								  <%
								  }
								  else if(accPayMode.equalsIgnoreCase("Visa")){
								  %>
								  <option value="">Select One</option>
								  <option value="Check">Check</option>
								  <option selected="selected" value="Visa">Visa</option>
								  <option value="MasterCard">Master Card</option>					
								  <option value="American Express">AmEx</option>
								  <%
								  }
								  else if(accPayMode.equalsIgnoreCase("MasterCard")){
								  %>
								  <option value="">Select One</option>
								  <option value="Check">Check</option>
								  <option value="Visa">Visa</option>
								  <option selected="selected" value="MasterCard">Master Card</option>					
								  <option value="American Express">AmEx</option>
								  <%
								  }
								  else{
								  %>
								  <option value="">Select One</option>
								  <option value="Check">Check</option>
								  <option value="Visa">Visa</option>
								  <option value="MasterCard">Master Card</option>					
								  <option selected="selected" value="American Express">AmEx</option>
								 <% }								
								}
								else{
								%>
								  <option selected="selected" value="">Select One</option>
								  <option value="Check">Check</option>
								  <option value="Visa">Visa</option>
								  <option value="MasterCard">Master Card</option>					
								  <option value="American Express">AmEx</option>
								<%
								}								
							}
							else{ 
							if(accDesc.equalsIgnoreCase("Over Payment")){
							%>
							  <option value="">Select One</option>
							  <option selected="selected" value="Check">Check</option>
							  <option value="Visa">Visa</option>
							  <option value="MasterCard">Master Card</option>
							  <option value="American Express">AmEx</option>
							<%	
							}else{
							%> 
							  <option selected="selected" value="">Select One</option>
							  <option value="Check">Check</option>
							  <option value="Visa">Visa</option>
							  <option value="MasterCard">Master Card</option>					
							  <option value="American Express">AmEx</option>			
						  <% }
						  } %>							
						</select>					
					</td>
					<td height="26" width="25" bgcolor="#E3E1D2" class="alignCenter">
					<%
					if(rev_status==true){
					%>
						<input type="checkbox" name="<%=revBox%>" id="<%=revBox%>" disabled="disabled" />
					<%
					}
					else if(accSyncStat==true){
					%>
						<input type="checkbox" name="<%=revBox%>" id="<%=revBox%>" value="<%=revEntry%>" onclick="disableNSF();sumUpRev(<%=indx-1%>);" />
					<%
					}
					else{
					%>
						<input type="checkbox" name="<%=revBox%>" id="<%=revBox%>" disabled="disabled" />
					<%}
					%>
					</td>
				  </tr>
				  <input type="hidden" name="<%=transId%>" value="<%=accTransId%>" />
				  
             <%       } }	}%>
				<tr>
				<td colspan="6">

				 <table id="NewContentTable" border="0" cellpadding="2" cellspacing="1">
			  	 </table>

				</td>
				</tr>			 
				 <%
					boolean stat = true;
					ArrayList entryList1 = (ArrayList)request.getAttribute("transacEntries");
					if(entryList1!=null){
					if(entryList1.size()>0){
						Iterator itr1 = entryList1.iterator();				 
						while(itr1.hasNext()){
							HLCAccTransactionVO accTxnVO1 = (HLCAccTransactionVO) itr1.next();
							boolean accReconcileStat1 = accTxnVO1.isReconcile_status();
							if(accReconcileStat1 == false){
								stat = false;
								break;
							}
						}	

					}
					}
					if(entryList1!=null){
					if(entryList1.size()>0){
					%>
					 <tr>
						 <td colspan="2" class="tableLeft" ><strong>Total Amount Entered&nbsp;&nbsp;</strong></td>
						 <td colspan="2" class="tableRight"><input type="text" class="textboxOne" name="totalAmt" id="totalAmt" value="0" readonly size="10" /> </td>
						 <td colspan="1" class="tableLeft" ><strong>Amount Reversed&nbsp;&nbsp;</strong></td>
						 <td colspan="3" class="tableRight"><input type="text" class="textboxOne" name="revAmt" id="revAmt" value="0" readonly size="10" /> </td>						 
					 </tr> 	
					 	<tr>
                    <%                 
						if(stat == true){
						%>
							<td colspan="2" class="tableRight"><input type="submit" class="gradBtn" name="submit" id="totsubmit" value="Submit" disabled="disabled" /></td>
						<% }
						else{
						%>
							<td colspan="2" class="tableRight"><input type="submit" class="gradBtn" name="submit" value="Submit" /></td>				 
						<% }
						%>
							<td colspan="2" class="tableRight"><input type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>									
							<td colspan="2" class="tableRight"><input type="submit" class="gradBtn" name="submit" id="NSFPay" value="NSFPay" disabled="disabled"/></td>
						</tr>
                                <% }
								
                                 else{%>
								 <tr>
								  <td class="alignCenter" height="25" colspan="7"><span class="textBold">No records are available.</span></td>
								  </tr>
                                 <%
                                 }
								 }
				 %>
						</table>
				</td>
				</tr>
			
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
