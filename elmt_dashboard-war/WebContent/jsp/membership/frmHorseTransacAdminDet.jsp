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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function sumUp(){
	var indx = document.frmTransacDet.listSz.value;
	var totamt = 0;
	for(i=0;i<indx;i++){
		var amtName = "amtName"+i;
		var payType	= "payType"+i;
		var ccType = "ccType"+i;
		
		if(document.getElementById(amtName).value!=""){
		        if(document.getElementById(amtName).value>0){
				tempval = document.getElementById(amtName).value;
					totamt = totamt + Number(tempval);
				}
		}

			chkDiv = "div"+(i+1);
			index = chkDiv.length;
			initPos = 0;
			endPos = index;
			for(j=0;j<index;j++){
				pos = IsNumeric(chkDiv.charAt(j));
				if(pos==true){
					initPos = j;
				}
			}
		
		ind = chkDiv.substring(initPos,endPos);

			if(document.getElementById(chkDiv).checked==true){	
				itemNme = "divNewItem"+ind;
				divtransId = "divTransId"+ind;
				transMode = "divTransMode"+ind;
				payMode = "payMode"+ind;
				newAmt = "newAmt"+ind;		
				if(document.getElementById(newAmt).value>0){
					tempVal = document.getElementById(newAmt).value;
					totamt = totamt + Number(tempVal);
				}
			}
		}
		document.frmTransacDet.totalAmt.value=totamt;
}
			//add a new row to the table
			function addRow(val)
			{
				//alert('Value is '+val);
				//alert('document.frmTransacDet.listSz.value'+document.frmTransacDet.listSz.value);
				var sZ = document.frmTransacDet.listSz.value;
			 	//var nameChkBox = "div"+i;
					
					if(val!=""){
						value = val.split("#");
						itemName = value[0];
						transId = value[1];
						indx = value[2];					
						chkId = "div"+indx;
						divId = "division"+indx;						
						
						//alert('chkId'+chkId);
						if(document.getElementById(chkId).checked == true){
							itemNme = "divNewItem"+indx;
							divtransId = "divTransId"+indx;
							transMode = "divTransMode"+indx;
							payMode = "payMode"+indx;
							newAmt = "newAmt"+indx;

							//alert(newAmt);
							//add a row to the rows collection and get a reference to the newly added row
							
							var newRow = document.all("NewContentTable").insertRow();
							var oCell = newRow.insertCell();
	
							var cou = eval(document.frmTransacDet.count.value) + 1;
	
							var oCell = newRow.insertCell();
							oCell.innerHTML = "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><input type='hidden' id='"+itemNme+"' name='"+itemNme+"'/>"+itemName+"</div>";
							 
							oCell = newRow.insertCell();
							oCell.innerHTML = "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><span class='label'><input id='"+divtransId+"' value='"+transId+"' type='hidden' name='"+divtransId+"'/>0.0</span></div>";
							
							oCell = newRow.insertCell();
							oCell.innerHTML= "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2' colspan='2'><input type='hidden' id='count' name='count' value='2'/> <input onblur='sumUp();' class='textboxOne' id='"+newAmt+"' type='text' name='"+newAmt+"' /></div>";
	
							
							oCell = newRow.insertCell();
							oCell.innerHTML= "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><select id='"+transMode+"' name='"+transMode+"' id='select' class='selectboxOne'><option selected='selected' value=''>Select One</option><option value='Partial Payment'>Partial Payment</option><option value='Accept as Full'>Accept as Full</option> <option value='Void'>Void</option></select></div>";			
	
							oCell = newRow.insertCell();
							oCell.innerHTML= "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><select id='"+payMode+"' name='"+payMode+"' id='select' class='selectboxOne' ><option selected='selected' value=''>Select One</option><option value='Check'>Check</option><option value='Visa'>Visa</option> <option value='MasterCard'>Master Card</option><option value='American Express'>Amex</option></select></div>";			
							
/*							oCell = newRow.insertCell();
							oCell.innerHTML = "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><input class='gradBtn' type='button' value='Delete' onclick='removeRow(this);' /></div>";
	
							
							oCell = newRow.insertCell();
							oCell.innerHTML = "<td  class='row'><input type='submit' class='gradBtn' value='Submit' name='validate'/></td></tr>";			
	*/						
							//document.newTestFrm.count.value = cou;
							//break;
						}
						else{
							var objVal = document.getElementById(divId);
							removeRow(objVal);
						}
					}

				//alert(document.frmstuWork.count.value);
			}
			
			//deletes the specified row from the table
			function removeRow(src)
			{
				/* src refers to the input button that was clicked.	
				   to get a reference to the containing <tr> element,
				   get the parent of the parent (in this case case <tr>)*/
							
				var oRow = src.parentElement.parentElement;		
				//alert('oRow.rowIndex' +oRow.rowIndex);
				//once the row reference is obtained, delete it passing in its rowIndex			
				document.all("NewContentTable").deleteRow(oRow.rowIndex);		
			}

function isnotIntegerChk(str){

stringIntCheck="0123456789.-";
f2=1;

for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{
return true;
}
  else 
  { 
  return false;
  }
}

function myvalidate()
{
	var indx = document.frmTransacDet.listSz.value;

	for(i=0;i<indx;i++){
		var amtName = "amtName"+i;
		var payType	= "payType"+i;
		var ccType = "ccType"+i;
		var actualAmount = "actualAmount"+i;
		if(document.getElementById(amtName).value!=""){
			if(isnotIntegerChk(document.getElementById(amtName).value)){
				alert("Enter valid amount");
				document.getElementById(amtName).focus();
				return false;
			}
                    if(document.getElementById(amtName).value>0){
			if(document.getElementById(payType).value == "" || document.getElementById(payType).value.indexOf(" ")==0){
				alert("Transaction mode cannot be Empty");
				document.getElementById(payType).focus();
				return false;
			}
			
			if(document.getElementById(ccType).value == "" || document.getElementById(ccType).value.indexOf(" ")==0){
				alert("Payment Type cannot be Empty");
				document.getElementById(ccType).focus();
				return false;
			}		
                     }
		}
//Acutal Amount 		
		if(document.getElementById(actualAmount).value<0){
			if(document.getElementById(ccType).value == "" || document.getElementById(ccType).value.indexOf(" ")==0){
				alert("Payment Type cannot be Empty");
				document.getElementById(ccType).focus();
				return false;
			}		
		}
		
		chkDiv = "div"+(i+1);
		
		//alert('chkDiv  '+chkDiv);

		index = chkDiv.length;
		initPos = 0;
		endPos = index;
		for(j=0;j<index;j++){
			pos = IsNumeric(chkDiv.charAt(j));
			if(pos==true){
				initPos = j;
			}
		}
		
		ind = chkDiv.substring(initPos,endPos);

		if(document.getElementById(chkDiv).checked==true){	
		
			itemNme = "divNewItem"+ind;
			divtransId = "divTransId"+ind;
			transMode = "divTransMode"+ind;
			payMode = "payMode"+ind;
			newAmt = "newAmt"+ind;
			
			if(document.getElementById(newAmt).value!=""){
					if(isnotIntegerChk(document.getElementById(newAmt).value)){
						alert("Enter valid amount");
						document.getElementById(newAmt).focus();
						return false;
					}
					if(document.getElementById(newAmt).value>0){
						if(document.getElementById(transMode).value == "" || document.getElementById(transMode).value.indexOf(" ")==0){
							alert("Payment Type cannot be Empty");
							document.getElementById(transMode).focus();
							return false;
						}
					
						if(document.getElementById(payMode).value == "" || document.getElementById(payMode).value.indexOf(" ")==0){
							alert("Transaction mode cannot be Empty");
							document.getElementById(payMode).focus();
							return false;
						}		
				  }
				}
				//return false;
			}	
		}
		actualAmt = Number(document.frmTransacDet.actualAmt.value);
		tempAmt = Number(document.frmTransacDet.totalAmt.value);
		if(tempAmt>actualAmt){
			alert('Total Reconcile Amount should not exceed the actual amount to be paid');
			return false;
		}
	}

function IsNumeric(sText){
   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char;
    if (ValidChars.indexOf(sText) == -1) 
         {
         IsNumber = false;
         }
   return IsNumber;
}

</script>
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
		  <form name="frmTransacDet" id="frmTransacDet" method="post" action="RegHorseListing.do" onSubmit="sumUp();return myvalidate();">		<!--return myvalidate();-->
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				 <%  
				String[] payDet = (String[]) request.getAttribute("payDet");
				ArrayList subTransac = (ArrayList) request.getAttribute("subTransac");
				float totAmt = 0.0f;
				float paidAmt = 0.0f;
				float chkAmt =0.0f;
				float pendAmt =0.0f;
				double actAmt =0.0f;
				ArrayList itrAmount = new ArrayList();
                                
				if(subTransac!=null){
					Iterator itr = subTransac.iterator();
					while(itr.hasNext()){
							HLCPaymentDetails subObjPay = (HLCPaymentDetails) itr.next();
							 chkAmt = subObjPay.getCheckAmount();
							 actAmt = subObjPay.getAmount();
							 pendAmt = subObjPay.getPendingAmount();
							String payMode = subObjPay.getPaymentStatus();
							
							if(payMode.equalsIgnoreCase("check")){
								if(actAmt>0){
									String newEntry = actAmt+"#"+subObjPay.getPaymentStatus();
									itrAmount.add(String.valueOf(newEntry));
								}
							}
							else{
									String newEntry = actAmt+"#"+subObjPay.getCcType();
									itrAmount.add(String.valueOf(newEntry));
							}
						}
				}
				
				 if(payDet!=null && payDet.length !=0){
                     String paymentStatus = payDet[1];
                     String amount = payDet[6];
				     String checkAmount =payDet[7];
					 String balAmt = payDet[10];					 
				 	 String cardType = payDet[12];
				 %>
	 			<input type="hidden" name="actualAmt" value="<%=amount%>" />
				 <tr>
				 <td>
				 <table class="tblInnerContainer" id="paymentTable" border="0" cellpadding="0" cellspacing="1">
				 <th>
				 <td colspan="3"><span class="bulletOne">History Of Payment</span></td>
				 </th>
				 <tr>
					  <td colspan="2" class="tableLeft">Actual Amount To Pay </td><td class="tableRight"><span class="styleBoldOne"><%=amount%></span></td>
				 </tr>		 	
				 <tr>
					<td colspan="2" class="tableLeft">Initial Amount Paid </td><td class="tableRight"><span class="styleBoldOne"><%=checkAmount%></span></td>
				 </tr>
						<%
							if(itrAmount.size()>0){
							int j=1;
							Iterator itr = itrAmount.iterator();
								while(itr.hasNext()){
									String val  = (String) itr.next();	
									String[] val_mode = val.split("#");
									
							%>
							 <tr>
								  <td class="tableLeft" colspan="2">Sub Payment <%=j%></td> <td class="tableRight"><span class="styleBoldOne"><%=val_mode[0]%></span>&nbsp;<strong>(<%=val_mode[1]%>)</strong></td>
							 </tr>									
							<%
								j++;
								}
							}
						 %>
					 <tr>
						  <td  class="tableLeft" colspan="2">Pending Amount to be paid </td><td class="tableRight"> <span class="styleBoldOne"><%=pendAmt%></span></td>
					 </tr>		
					 </table>		
					<input type="hidden" name="amount" id="amount" value="<%=amount%>" /> 				 
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
			  </tr>
				<% 
					ArrayList entryList = (ArrayList)request.getAttribute("transacEntries");
					if(entryList!=null){
						Iterator itr = entryList.iterator();
						int indx =0;
						int listSize = entryList.size();;
						String amtName;
						String payType; 
						String ccType; 
						String transId;
						String actualAmount;
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
							actualAmount = "actualAmount"+indx;
							String divValue = accDesc+"#"+accTransId+"#"+(indx+1);
							indx++;
					%>	

				<input type="hidden" name="transacId" value="<%=accTransId%>" />
				<input type="hidden" name="process" value="adminHorseDetUpdate" />
				<input type="hidden" name="<%=actualAmount%>" id="<%=actualAmount%>" value="<%=accAmt%>" />
				  <tr>
					<td height="26" width="350" bgcolor="#E3E1D2" class="alignLeft"><%=accDesc%></td>
					<td height="26" width="50" bgcolor="#E3E1D2" class="alignCenter"><%=accAmt%></td>	
									
					<%
					if(accAmt<0){
					%>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignLeft"><input name="<%=amtName%>" type="text" id="<%=amtName%>" value="<%=accReconcileAmt%>" class="textboxOne" size="10" disabled="disabled"  /></td>
					<%}
					else{
					%>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignLeft"><input name="<%=amtName%>" type="text" id="<%=amtName%>" value="<%=accReconcileAmt%>" class="textboxOne" size="10" onblur="sumUp();"/></td>					
					<%
					}
					if (accReconcileAmt==0 || accAmt<=0){
					%>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" value="noVal" name="div<%=indx%>" id="div<%=indx%>" onclick="addRow('<%=divValue%>');" disabled="disabled"/></td>
					<% }
					else if(accTxnVO.isCheckStatus()==true){ %>
					<td height="26" width="10" bgcolor="#E3E1D2" class="alignCenter"><input type="checkbox" value="snoVal" name="div<%=indx%>" id="div<%=indx%>" onclick="addRow('<%=divValue%>');" /></td>                                        
					<% } 
                    else{%>
                    <td height="26" width="10" bgcolor="#E3E1D2" class="alignCenter"><input value="snoVal" type="checkbox" name="div<%=indx%>" id="div<%=indx%>" onclick="addRow('<%=divValue%>');" disabled="disabled"/></td>
                    <%
                    } 
                    %>
					<td height="26" width="25" bgcolor="#E3E1D2" class="alignLeft">
						<select name="<%=payType%>" id="<%=amtName%>" class="selectboxOne">
						<%
						if(accAmt<0){
						%>
						<option  selected="selected" value="Accept as Full" >Accept as Full</option>
						<%}
						else if(accTransMode==null){%>
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
						<%}
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
							else{  %>
							  <option selected="selected" value="">Select One</option>
							  <option value="Check">Check</option>
							  <option value="Visa">Visa</option>
							  <option value="MasterCard">Master Card</option>					
							  <option value="American Express">AmEx</option>			
						  <% } %>							
						</select>					
					</td>
				  </tr>
				  <input type="hidden" name="<%=transId%>" value="<%=accTransId%>" />
				  
             <%       }%>
				<tr>
				<td colspan="6">

				 <table id="NewContentTable" border="0" cellpadding="2" cellspacing="1">
			  	 </table>

				</td>
				</tr>	
				 <%
					boolean stat = true;
					ArrayList entryList1 = (ArrayList)request.getAttribute("transacEntries");
					if(entryList!=null){
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
					if(entryList.size()>0){
					%>
					 <tr>
						 <td colspan="2" class="tableLeft" ><strong>Total Amount Entered&nbsp;&nbsp;</strong></td>
						 <td colspan="4" class="tableRight"><input type="text" class="textboxOne" name="totalAmt" id="totalAmt" value="0" readonly size="10" /> </td>
					 </tr> 						
                    <% 					
                                     if(stat == true){
                                     %>
									 <tr>
                                     <td colspan="3" class="tableRight"><input type="submit" class="gradBtn" name="submit" value="Submit" disabled="disabled" /></td>
									<td colspan="3" class="tableRight"><input type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>
									</tr>
                                     <% }
                                     else{
                                     %>
									 <tr>
                                     <td colspan="3" class="tableRight"><input type="submit" class="gradBtn" name="submit" value="Submit" /></td>				 
									<td colspan="3" class="tableRight"><input type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>
									</tr>									 
                                     <% } 
									%>
                                <% }
                                 else{%>
								 <tr>
								  <td class="textCommon" height="25" colspan="6"><strong>No records are available.</strong> </td>
								  </tr>
                                 <%
                                 }
								 }
				 %>
		
						</table>
				</td>
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
