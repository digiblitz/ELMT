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
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
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
<%!
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
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
		  <form name="frmTransacDet" id="frmTransacDet" method="post" action="acctrans.do" onSubmit="sumUp(); return myvalidate();">		<!--return myvalidate();-->
		  <input type="hidden" name="process" value="holdTrans" />
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				 <%  
				 HLCPaymentDetails objPayment = (HLCPaymentDetails) request.getAttribute("objPayment");
				 ArrayList subTransac = (ArrayList) request.getAttribute("subTransac");
	
				float totAmt = 0.0f;
				float paidAmt = 0.0f;
				float initAmtPaid = 0.0f;
				float totSubPay = 0.0f;
				ArrayList itrAmount = new ArrayList();
				ArrayList chkDet = new ArrayList();
				boolean active_status=false;
				String payId= "";
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
							
							if(payMode.equalsIgnoreCase("check")){
								if(actAmt>0){
									totSubPay += chkAmt;
									String newEntry = chkAmt+"#"+subObjPay.getPaymentStatus()+"#"+payMode+"#"+chkNo+"#"+nmeOnChk+"#"+dateCheck(chkDte)+"#"+bankName;
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
					 payId = objPayment.getPaymentId();
					 initAmtPaid = Float.parseFloat(String.valueOf(actualAmt))-(totSubPay+pendAmt);
				 %>
				 <tr>
				 <td>
				 <table class="tblInnerContainer" id="paymentTable" border="0" cellpadding="0" cellspacing="1">
				 <th>
				 <td colspan="3" class="alignLeft"><br /><span class="labelOne">History Of Payment</span><br /></td>
				 </th>
				 <tr>
					  <td colspan="5" class="tableLeft">Actual Amount To Pay </td><td class="tableRight"><span class="styleBoldOne"><%=actualAmt%></span></td>
				 </tr>		 	
<!--				 <tr>
					<td colspan="5" class="tableLeft">Initial Amount Paid </td><td class="tableRight"><span class="styleBoldOne">< %=initAmtPaid%></span>< %=payModde%></td>
				 </tr>-->
				 <tr>
					  <td  class="tableLeft" colspan="5">Pending Amount to be paid </td><td class="tableRight"> <span class="styleBoldOne"><%=pendAmt%></span></td>
				 </tr>	
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
													  <td class="tableRight"><span class="styleBoldOne">&nbsp;<%=dateFormat(val_mode[5].toString())%></span>&nbsp;</td>
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
					<th height="26" class="tblRowHeadTwo">Transaction Mode</th>	
					<th height="26" class="tblRowHeadTwo">Payment Mode</th>																	
			  </tr>
				<% int reconCount = 1;
					int recon =0;
					ArrayList entryList = (ArrayList)request.getAttribute("transacEntries");
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
						
						while(itr.hasNext()){
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
							active_status = accTxnVO.isActive_status();
							
							amtName = "amtName"+indx;
							payType = "payType"+indx;
							ccType = "ccType"+indx;
							transId = "transId"+indx;
							actualAmount = "actualAmount"+indx;
							String divValue = accDesc+"#"+accTransId+"#"+(indx+1);
							indx++;
					%>	
				  <tr>
					<td height="26" width="350" bgcolor="#E3E1D2" class="alignLeft"><%=accDesc%></td>
					<td height="26" width="50" bgcolor="#E3E1D2" class="alignCenter"><%=accAmt%></td>
					<td height="26" width="50" bgcolor="#E3E1D2" class="alignCenter"><%=accReconcileAmt%></td>
					<td height="26" width="50" bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(accTransMode)%></td>
					<td height="26" width="50" bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(accPayMode)%></td>
				  </tr>
             <%       } 
			 }	%>
			 <tr>
				 <td><br /><strong>Hold Transaction</strong></td>			 
			 <%
			 if(active_status==true){
			 %>
				 <td colspan="4"><br /><span class="styleBoldOne">&nbsp;Transaction is Active</span></td>	 
			<%
			 }
			 else{
			 %>
				 <td colspan="4"><br /><span class="styleBoldOne">&nbsp;Transaction is On Hold</span></td>	 
			 <%
			 }
			 %>
			 </tr>
				<tr>
					<td>&nbsp;<br /><strong>Transaction Status</strong></span></td>
					<td><span class="styleBoldOne">&nbsp;<br /><input type="radio" name="radioValue" value="yes" />Yes</span></td>
					<td><span class="styleBoldOne">&nbsp;<br /><input type="radio" name="radioValue" value="no" />No</span></td>
				</tr>
				<tr>
				<td class="alignRight"><span class="styleBoldOne">&nbsp;<br /><input type="submit" value="Submit" class="gradBtn" /></span></td>
				<td class="alignLeft"><span class="styleBoldOne">&nbsp;<br /><input type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);"/></span></td>
				</tr>
			<% }%>
				</table>
				</td>
				</tr>
			</table>
			<input type="hidden" name="paymentId" value="<%=payId%>" />
  </form>
	</td>
  </tr>

</table>
<tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>

		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
			<!-- CONTENTS END HERE -->		
			
	  </table>
	
	</td>
  </tr>
 
</table>

</body>
</html>
