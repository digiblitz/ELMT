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
<%@ page import="java.util.*"%>
<%@ page import="com.hlcmee.ann.*"%>
<%@ page import="com.hlcmeeting.util.*"%>
<%@ page import="java.math.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

  <%
HLCAnnualMeetingStore sessRegList = (HLCAnnualMeetingStore)session.getAttribute("regList");
//out.print(regList);
if(sessRegList==null || sessRegList.getSize()==0){
//out.print("No records are found");
	response.sendRedirect("annualConRegAction.do?annProcess=initAnnual");
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeAnnualConvRegister.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeAnnConFinalize.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/gen_validatorv2.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function clearTkts(){
	if(document.getElementById('addTktRegistrarName').value!=""){
		var len = document.getElementById('priceIdLen').value;
		for(i=1;i<=len;i++){
			var actCheckBox = "actCheckBox"+i;
			var noOfTkt = "noofticket"+i;
			var indivAmt = "indivAmt"+i;
			var calculAmt = "calculAmt"+i;
						
			if(document.getElementById(actCheckBox).checked==true){
				document.getElementById(actCheckBox).checked =false;
				document.getElementById(noOfTkt).value=0;
				document.getElementById(indivAmt).value="0.00";
				document.getElementById(calculAmt).value="0.00";
			}
		}
	}
}
	function clearBasicLists(){
		var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
		for (i=1;i<=priceLen;i++){
			var noOfTkt = "noofticket"+i;
			var indivAmt = "indivAmt"+i;
			var calculAmt = "calculAmt"+i;
			
			document.getElementById(noOfTkt).value=0;
			document.getElementById(indivAmt).value="0.00";
			document.getElementById(calculAmt).value="0.00";
		}
		document.frmAnnualConvRegFinalize.activityAmount.value = "0.00";
	}

		function checkAll() {
			 el = document.forms['frmAnnualConvRegFinalize'].elements;
			 for (i=0; i < el.length; i++) {
			   if(el[i].type == "checkbox") {
				  void(el[i].checked=0);
			   }
			 }
		 }
		 
		 

		function resetAmount(){
			document.frmAnnualConvRegFinalize.totalAmount.value = document.frmAnnualConvRegFinalize.stTotal.value ;
		}

		function setTotalAmount(){
			var amt1 = document.getElementById("stTotal").value;
			var amt2 = document.getElementById("activityAmount").value;
			document.frmAnnualConvRegFinalize.totalAmount.value = (parseFloat(amt1) + parseFloat(amt2)).toFixed(2);
		}

		function clearAmtValues(){
			var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
			for (i=1;i<=priceLen;i++){		
				var noOfTkt = "noofticket"+i;
				var indivAmt = "indivAmt"+i;
				var calculAmt = "calculAmt"+i;
				var actCheckBox ="actCheckBox"+i;			
					if(document.getElementById(actCheckBox).checked==false){
						document.getElementById(noOfTkt).value=0;
						document.getElementById(indivAmt).value="0.00";
						document.getElementById(calculAmt).value="0.00";
					}
			}
		}

		function setAcAmount(ticketObj){
			var ticketObjName = "";
			var chkBoxName;
			
			var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
			for (i=1;i<=priceLen;i++){
				var noOfTkt = "noofticket"+i;
				var indivAmt = "indivAmt"+i;
				var calculAmt = "calculAmt"+i;
				var actCheckBox ="actCheckBox"+i;
				
				if(document.getElementById(actCheckBox).checked==true){
					var tkt = Number(document.getElementById(noOfTkt).value);
					var tktVal = Number(document.getElementById(indivAmt).value);
					var amt = (parseFloat(tkt)*parseFloat(tktVal)).toFixed(2);
					document.getElementById(calculAmt).value = amt;
					calculateAmount();
					setTotalAmount();
					//fillCalcAmt();
				}
				else{
					calculateAmount();	
					setTotalAmount();
				}
					clearAmtValues();
				/*
				else{
					document.getElementById(noOfTkt).value=0;
					document.getElementById(indivAmt).value="0.00";
					document.getElementById(calculAmt).value="0.00";
					calculateAmount();	
					setTotalAmount();				
				}*/
			}
		}

		function calculateAmount(){
			var priceLen = document.frmAnnualConvRegFinalize.priceIdLen.value;
			var amt=0;
			var tempAmt = 0;
			for (i=1;i<=priceLen;i++){
				var noOfTkt = "noofticket"+i;
				var indivAmt = "indivAmt"+i;
				var calculAmt = "calculAmt"+i;
				var actCheckBox ="actCheckBox"+i;
				
				if(document.getElementById(actCheckBox).checked==true){
					//amt = Number(amt) + Number(document.getElementById(indivAmt).value);
					var tkts = Number(document.getElementById(noOfTkt).value);
					var indAmt = Number(document.getElementById(indivAmt).value);
					tempAmt = Number((parseFloat(tkts)*parseFloat(indAmt)).toFixed(2)).toFixed(2);
					amt = Number(amt) + Number(tempAmt);
					document.frmAnnualConvRegFinalize.activityAmount.value = amt;
					document.getElementById(calculAmt).value = tempAmt;
				}
			}
			document.frmAnnualConvRegFinalize.activityAmount.value = Number(amt).toFixed(2);		
			setTotalAmount();	

		}



var httpRequest;
var gindivAmt;
var gcalculAmt;
var gtransId;
var gpriceId;
var gamtBx;
var gnoOfTkt;
	function getActivityAmount(param,paramthis,paramname){
					var  url = null;
				
					var memId = document.frmAnnualConvRegFinalize.memberShipTypeID.value;
					   
			 		url = "annualAjax.do?method=calcAmount&userTypeId="+escape(memId)+"&specId="+escape(param);

					if(paramthis.checked==true){
						if (window.ActiveXObject){ 
							httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
						} 
						else if (window.XMLHttpRequest){ 
							httpRequest = new XMLHttpRequest(); 
						}
						
						gindivAmt = "";
						gcalculAmt = "";
						gPriceId = "";
						gtransId = "";
						gamtBx = "";
						gpriceId="";
						
						gindivAmt = "indivAmt"+paramname;
						gcalculAmt = "calculAmt"+paramname;
						gtransId = "trans"+paramname;
						gpriceId = "priceId"+paramname;
						gnoOfTkt = "noofticket"+paramname;
						
						httpRequest.onreadystatechange = setOtherAmountonName;
						httpRequest.open("GET", url, true);
						httpRequest.send(null);
						
					}
					else{					
						/*document.getElementById(gindivAmt).value = "0.00";
						document.getElementById(gcalculAmt).value = "0.00";
						document.getElementById(gnoOfTkt).value = 0;
						calculateAmount();*/
					}
					setAcAmount(gnoOfTkt);
					setTotalAmount();
				   } 

		function setOtherAmountonName(){
		
			if(httpRequest.readyState==1){
				return;
			}
			if (httpRequest.readyState == 4){
				if(httpRequest.status == 200){
					var xmlDoc = httpRequest.responseXML;
					var priceVal = xmlDoc.getElementsByTagName('priceId')[0].childNodes[0].nodeValue;
					var regAmnt = xmlDoc.getElementsByTagName('amount')[0].childNodes[0].nodeValue;	
					if(priceVal==null && regAmnt==0){
						amntObj = 0.0;
						priceVal = "";
					}
					else{
						amntObj = eval(regAmnt).toFixed(2);
						priceIdField = priceVal;
					}
					document.getElementById(gindivAmt).value = amntObj;
					document.getElementById(gpriceId).value = priceIdField;
					document.getElementById(gnoOfTkt).value = 1;
					calculateAmount();
				}
				if(httpRequest.status==500) {
					amntObj.value="";
					return; 
				}
			}
			else{
			//alert("Error loading page\n");
			}
		}				
				  
		function denyBack(){
			window.history.clear();
			window.history.forward(1);
		}
				  
</script>
</head>


<body onload="cardClear();">

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
					<td width="260" class="menuTablePad">
					<!-- LEFT MENU STARTS HERE -->
					<%@ include file = "../../include/menu-roles-leftmenu.jsp" %> 
					<!-- LEFT MENU ENDS HERE -->
		
					</td>
					<td width="500" class="subDeptTablePad">
						<!-- CONTENTS START HERE -->
					
				 
					
						<table width="500" border="0" align="center" cellpadding="0" cellspacing="0"  class="formLayout">
							<tr>
								<td colspan="2" class="tblMainHead"><strong></strong> Meetings:  <span class="styleBoldTwo">Annual Meeting & Convention Registration</span></td>
							</tr>
									  <%
									 String sessionInvoiceId = "1";
                                                                           session.setAttribute("sessionInvoiceId", sessionInvoiceId);		
                                                                          double totalAmount = 0.00d;
										BigDecimal rAmt = null;
										
									  	HLCAnnualMeetingStore regList = (HLCAnnualMeetingStore)session.getAttribute("regList");
										if(regList!=null){
										Enumeration e = regList.getEnumeration();
										while(e.hasMoreElements()){
											HLCAnnualUserVO tempObjAnnualUser = (HLCAnnualUserVO)e.nextElement();
											String delUserId = tempObjAnnualUser.getUserId();
											//String firstName = tempObjAnnualUser.getFirstName();
											String badgeName = tempObjAnnualUser.getBadgeInfo();
											double regAmount = tempObjAnnualUser.getRegAmount();
											double otherAmount = tempObjAnnualUser.getOtherActAmount();
											totalAmount +=(regAmount+otherAmount);
											
											String tempTotalAmount = String.valueOf(totalAmount);
											
											rAmt = new BigDecimal(Float.parseFloat(tempTotalAmount));
											rAmt = rAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
											
											String memberType = tempObjAnnualUser.getMemberTypeName();
									  %>
									<%
									}
									}
									%>
						 
						  <form name="frmAnnualConvRegFinalize" id="frmAnnualConvRegFinalize" method="post" action="AnnRegPayment.do" onsubmit="return onPayCheck();">
						  <input type="hidden" name="annProcess" value="saveAnnualDetail"/>
							  <tr>
								<td colspan="2" class="tableSpan">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
							  </tr>
							  <tr> 
								<td colspan="2" class="tblRowHead"> <span class="rowHead">Membership Information</span></td>
							  </tr>
							   <tr>
								<td class="tableLeftTxtArea"> Do you want any additional tickets?</td>
								<td class="tableRight">
								<input type="radio" size="10" name="rself" value="yes" onclick="switchDiv('member');checkAll();resetAmount();clearBasicLists();" />Yes 
								<input type="radio" size="10" name="rself" value="no" checked="checked" onclick="showHideRadio('rself','member','web');resetAmount();checkAll();clearBasicLists()" />No</td>
							  </tr>
								<!--
								<tr> 
								  <td colspan="2" height="15" class="styleError">Sorry, the option you have selected does not match your profile.</td>
								</tr>
								-->
							  <tr>
								<td colspan="2">
								
								
								
								
								
								
								<div id="member">
									  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
										<tr> 
										  <td class="tblMainHead">Ticket Information </td>
										</tr>
										<tr>
								<td>
									<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
									  <tr>
										<td colspan="3" class="tableSpan">
										 <%
							  				Vector otherType = (Vector)request.getAttribute("OTHER_ACTIVITY");
										  //out.print(regType.size());
										  String priceIdLen = "0";
										  
										  if(otherType!=null){
										  	priceIdLen = String.valueOf(otherType.size());
										  }
										  
											String memberShipTypeID = (String)request.getAttribute("memberShipTypeID");
											if(memberShipTypeID==null)
											memberShipTypeID = "";
											 
										  %>
										 
									   
									   <input id="priceIdLen" type="hidden" name="priceIdLen" value="<%=priceIdLen%>" />
										<input type="hidden" name="memberShipTypeID" value="<%=memberShipTypeID%>" />
									   
											<table width="100%" border="0" cellspacing="1" cellpadding="0">
											
											<tr>
												<td width="51%" class="tblRowHead">Addtional Tickets Registered for:</td>
												<td width="51%" colspan="3"class="tblRowHead">
											<select id="addTktRegistrarName" name="addTktRegistrarName" class="textboxOne" onchange="clearTkts();">
												<option value="">Select One</option>
												<%
													if(sessRegList!=null){
													Enumeration e = regList.getEnumeration();
													while(e.hasMoreElements()){
														HLCAnnualUserVO tempObjAnnualUser = (HLCAnnualUserVO)e.nextElement();
														String firstName = tempObjAnnualUser.getFirstName();
														String lastName = tempObjAnnualUser.getLastName();
														String uId = tempObjAnnualUser.getUserId();
														out.println("UserId:" + tempObjAnnualUser.getUserId());
											%>
													<option value="<%=uId%>"><%=firstName%>&nbsp;<%=lastName%></option>
													<%
													}
													}
													%>
												</select>
												</td>
											</tr>
											
											<tr>
												<td width="51%" class="tblRowHead">Other Activity Name</td>
											    <td width="18%" class="tblRowHead">No. of tickets</td>
											    <td width="15%" class="tblRowHead">Amount ($)</td>											                                                
										      	<td width="16%" class="tblRowHead">Total Amount ($)</td>
											</tr>
											 <%
											if(otherType!=null && otherType.size()!=0){
											Enumeration otherEnum = otherType.elements();
											%>
											<input type="hidden" name="actSize" value="<%=otherType.size()%>">
											<%
											int txtCount = 0;
											while(otherEnum.hasMoreElements()){
											String[] s = (String [])otherEnum.nextElement();
											txtCount ++;
											//out.print("id:" + s[0]);
											String spId = s[0];
											String spTransId = s[2];
							     			%>
											<input type="hidden" name="tktactivityId" id="tktactivityId" value="<%=spTransId%>" />
									<tr>
												<td width="51%" class="listCellBg">
									  <input type="checkbox" name="actCheckBox<%=txtCount%>" id="actCheckBox<%=txtCount%>" value="<%=spId%>" onclick="getActivityAmount('<%=spId%>',this,<%=txtCount%>);"/><%=s[1]%></td>
											    <td width="18%" class="listCellBg"><span class="tableRight">
											
											    <input name="noofticket<%=txtCount%>" id="noofticket<%=txtCount%>" value="" maxlength="4" class="textboxOne" size="10" onblur="setAcAmount(this);"/>
								      </span></td>
												<input type="hidden" name="actVal<%=txtCount%>"  value="<%=s[1]%>">
											    <td width="15%" class="listCellBg">
												<input type="hidden" name="priceId<%=txtCount%>" id = "priceId<%=txtCount%>" value="" />
									  <input name="indivAmt<%=txtCount%>" class="textboxOne" id="indivAmt<%=txtCount%>" readonly style="background-color:#EEEBE3; font-weight:bold; border:0px;" value="0.00" size="10" />											    </td>
								      <td width="16%" class="listCellBg">
									  <input name="calculAmt<%=txtCount%>" class="textboxOne" id="calculAmt<%=txtCount%>" readonly="readonly" style="background-color:#EEEBE3; font-weight:bold; border:0px;" value="0.00" size="10" /></td>
									</tr>
									<%
									 
									}
								 }
								 %> 
									</table>										</td>
									  </tr>
									
									  <tr>
										<td class="tableLeft"> Other Activity Amount::</td>
										<td class="tableRight"><strong>$</strong>
										<input type="text" name="activityAmount" id="activityAmount" class="textboxOne" value="0.00"  size="15" readonly="true" /></td>
									  </tr>
									  
									</table>
									</td>
							  </tr>
									 </table>
									 </div>
							    </td>
							  </tr>						  
							 <tr>
												<td class="tableLeft"><strong>Activity Amount:</strong></td>
												<td class="tableRight"> <strong>$</strong> 
												<input type="text" id="stTotal" name="stTotal" readonly="true" class="textboxOne" size="10" value="<%=rAmt%>" />
												  <span class="asterisk">*</span></td>
						    </tr>
							<tr>
												<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
						    </tr>
										 
											  <tr>
												<td class="tableLeft"><strong>Total Amount:</strong></td>
												<td class="tableRight"> <strong>$</strong> 
												<input type="text" name="totalAmount" readonly="true" class="textboxOne" size="10" value="<%=rAmt%>" />
												  <span class="asterisk">*</span></td>
											  </tr>
		         <%if(session.getAttribute("loggedBy")!=null){%>							  
										<tr>
									  	<td class="tableLeft">
									 		<input type="radio" size="10" name="r11" id="r11" value="check"  onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'), cardClear(),hideImgDiv('cvvImg');"/> Check enclosed.				 	</td>
									 	<td class="tableRight">
									 		<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked" onclick="switchDiv('chrgCrdAcm'), showHideRadio('r11','chkEncAcm','card'), checkClear(),showImgDiv('cvvImg');"  /> Please charge my card.					</td>
									  </tr>
									  <input type="hidden" name="c11" id="c11" value="adminSec">
									  <%}else{%>
									  
						                 <tr>									  	
									 	<td class="tableRight" colspan="2">
									 		<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked" onclick="switchDiv('chrgCrdAcm'),showImgDiv('cvvImg');"  /> Please charge my card.					</td>
									  </tr>				  
								<input type="hidden" name="c11" id="c11" value="userSec">	  
									  <%}%>
									  
						  			  <tr>
										<td colspan="2">
	
										<div id="chkEncAcm">

											<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
												<tr>
													<td class="tblMainHead" colspan="3">Check Details:</td>
												</tr>
												<tr>
												  <td colspan="2" class="tableSpan">
												  If paying by check please mail your payment to:  <br />
												    <br />
												    <strong>
												    <br />
												    Annual Meeting Registration
												    <br />
												    525 Old Waterford Road NW
												    <br />
												    Leesburg, VA 20176												    </strong><br />
												    <br />
											        <strong>Note:</strong> Your registration status will be pending until check is processed.												  <br />
										          <br /></td>
											  </tr>
											<input type="hidden" name="status" value="no">
											<%
														int si=0;
														String logBy="user";
														if(session.getAttribute("loggedBy")!=null){
														String loggedBy="";
														loggedBy=(String)session.getAttribute("loggedBy");
														logBy=loggedBy;
														if(loggedBy.equalsIgnoreCase("Admin")){
											%>
														<input type="hidden" name="status" value="yes">
														<tr>
														  <td class="tableLeft">Check Amount:</td>
														  <td class="tableRight"><strong>$</strong> 
														  <input type="hidden" name="roughVal" value="yes">
														  <input type="text" name="chkAmt" id="chkAmt" class="textboxOne" size="16" maxlength="10" />&nbsp; U.S. Dollars	 
														 </td>
														</tr>
											<%
											}
											}
											%>											  
											  
												<tr>
												  <td class="tableLeft">Check No:</td>
												  <td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
												</tr>
												<tr>
												  <td class="tableLeft">Check Date:</td>
												  <td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" readonly="yes"size="16" />

												   <a href="javascript:cal1.popup();">
					                   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">												  </a>
										  <span class="asterisk">*</span>												  </td>
												</tr>
												<tr>
												  <td class="tableLeft">Bank Name :</td>
												  <td class="tableRight">
												  <input type="text" name="bankName" id="bankName" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
												</tr>
												<tr>
												  <td class="tableLeft">Name on Check:</td>
												  <td class="tableRight">
												  <input type="text" name="nameCheck" id="nameCheck" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
												</tr>
											</table>
											
											</div>
					</td>
									  </tr>
									  <tr>
										<td colspan="2">
	
										
<div id="chrgCrdAcm">
											<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
												
												<tr>
													<td class="tblMainHead" colspan="2">Card Details:</td>
												</tr>
												
													<tr>
													<td class="tableLeft">Card Type:</td>
												  <td class="tableRight">
														<select name="ccType" id="ccType" class="selectboxOne">
														  <option selected="selected" value="">Select One</option>
														  <option value="Visa">Visa</option>
														  <option value="MasterCard">Master Card</option>
														  <option value="AmEx">AmEx</option>
														</select>
					                                    <span class="asterisk">*</span>								</td>
												</tr>
												
												<tr>
													<td class="tableLeft">Card No.:</td>
												  <td class="tableRight">
												  <input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="16" />
					                                  <span class="asterisk">*</span></td>
												</tr>
												<%if(session.getAttribute("loggedBy")!=null){%>	
												<tr>
													<td class="tableLeft">Card CVV No.:</td>
												  <td class="tableRight">
												  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" maxlength="4"/>&nbsp;(see details below)</td>
												</tr>
												<%}else{%>
												<tr>
												<td class="tableLeft">Card CVV No.:</td>
												<td class="tableRight">
												<input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" maxlength="4"/><span class="asterisk">*</span>&nbsp;(see details below)</td>
												</tr>
												<%}%>
												<tr>
													<td class="tableLeft">Print Name On Card:</td>
												  <td class="tableRight">
												  <input type="text" name="ccName" id="ccName" class="textboxOne" size="25" />
					                                  <span class="asterisk">*</span></td>
												</tr>
												<tr>
													<td class="tableLeft">Expiry Date:</td>
												  <td class="tableRight">
														<select name="ccExpMonth" id="ccExpMonth" class="selectboxOne">
														  <option value="" selected="selected">Month</option>
														  <option value="01">January</option>
														  <option value="02">February</option>
														  <option value="03">March</option>
														  <option value="04">April</option>
														  <option value="05">May</option>
														  <option value="06">June</option>
														  <option value="07">July</option>
														  <option value="08">August</option>
														  <option value="09">September</option>
														  <option value="10">October</option>
														  <option value="11">November</option>
														  <option value="12">December</option>
														</select>
														<select name="ccExpYear" id="ccExpYear" class="selectboxOne">
															  <option value="" selected="selected" >Year</option>
															  <option  value="2006">2006</option>
															  <option  value="2007">2007</option>
															  <option  value="2008">2008</option>
															  <option  value="2009">2009</option>
															  <option  value="2010">2010</option>
															  <option  value="2011">2011</option>
															  <option  value="2012">2012</option>
															  <option  value="2013">2013</option>
															  <option  value="2014">2014</option>
															  <option  value="2015">2015</option>
                                                                                                                          <option  value="2016">2016</option>
                                                                                                                          <option  value="2017">2017</option>
                                                                                                                          <option  value="2018">2018</option>
                                                                                                                          <option  value="2019">2019</option>
														</select>
					                                    <span class="asterisk">*</span>																         </td>
								      </tr>
									 
								 </table>	
								 </div>
	
								 							</td>
							</tr>
							<tr><td>&nbsp;</td></tr>
							<tr>
							  <td colspan="2" class="alignCenter">
												<input type="submit" value="Submit Payment" class="gradBtn" />&nbsp;</td>
						    </tr>
							<tr><td>&nbsp;</td></tr>
							 <tr><td colspan="2" class="alignCenter"><div id="cvvImg"><img src="images/cvv_graphic.jpg" /></div></td></tr>
						  </form>
					  </table>
					<!-- CONTENTS START HERE -->
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
	var cal1 = new calendar2(document.forms['frmAnnualConvRegFinalize'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>
</html>
