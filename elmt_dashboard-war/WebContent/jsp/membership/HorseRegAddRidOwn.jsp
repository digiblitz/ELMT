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
 <%@ page import="com.hlcmrm.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/HorseRegValidateAdd.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmHorseRidOwnAdd.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script type="text/javascript">
function dispAmt(){

	if(document.myform.status.value=="yes"){
	    sum=document.myform.totalAmount.value; 
		chkLen = document.myform.phoneCharge.value;
		
		if(document.myform.phoneCharge.checked){
			sum = Number(chkLen) + Number(sum);
			document.myform.tempAmount.value = sum;
	 		document.myform.totalAmount.value= sum;
			
			 }

			 else{
				document.myform.totalAmount.value = document.myform.totalAmount.value - document.myform.phoneCharge.value;
				document.myform.totalAmount.value = document.myform.totalAmount.value;
 			}
			
			var s = document.getElementById('totalAmount').value;
	  if(s.indexOf('.')==-1){
		s= s+".00";
		document.getElementById('totalAmount').value=s;
	  }
	  if(s.indexOf('.')!=-1){
		var t = s.substring(s.indexOf('.'));
			//alert(t.length);
			if(t.length==2){
			s=s+"0";
			document.getElementById('totalAmount').value=s;
			}
	  }
			
	}
	
}
	function amt(){
	 	var domR = document.getElementById;
		var fomR = document.myform;
		if(fomR.arhlcNo.value != ""){
			domR('addRider').style.display = "block";
		}else{
			domR('addRider').style.display = "none";
		}
	}
	
	function resetRadioOne(){
		var chosen2="";
		len = document.myform.regAddFor.length ;
		for(i=0;i<len;i++){
			if(document.myform.regAddFor[i].checked){
			chosen2= document.myform.regAddFor[i].checked=false;
			
			document.getElementById('ridAddMemb').style.display = "none";
			document.getElementById('ridAddUser').style.display = "none";
			document.getElementById('accAddUser').style.display = "none";
			document.getElementById('newwAddUser').style.display = "none";
			document.getElementById('addOwn').style.display = "none";
			}
		}
	}
	
	function resetRadioTwo(){
		var chosen2="";
		len = document.myform.regFor.length ;
		for(i=0;i<len;i++){
			if(document.myform.regFor[i].checked){
			chosen2= document.myform.regFor[i].checked=false;
			}
		}
	}
function userDetails1(param){
			var  url = null;
			if(param.value.length==0)
			return;
			url = "annualAjax.do?method=memberDetail&memberId="+escape(param.value);
			if (window.ActiveXObject){ 
				req = new ActiveXObject("Microsoft.XMLHTTP"); 
			} 
			else if (window.XMLHttpRequest){ 
				req = new XMLHttpRequest(); 
			} 
			req.onreadystatechange = processRequest11;         
			req.open("GET", url, true);
			req.send(null);  
} 
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
         function processRequest11(){ 
    			if (req.readyState == 4){ 
						   
							if(req.status == 200){ 
							 
								 var xmlDoc = req.responseXML.documentElement;
									var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
									var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
									 
								 
									var firstNameObj = document.getElementById("firstNameId2");
								    var lastNameObj = document.getElementById("lastNameId2");
									var phoneObj = document.getElementById("phoneId2"); 
							        var userObj = document.getElementById("addUserDetId"); 
								 
									
									firstNameObj.value=firstName;
									lastNameObj.value=lastName;
										 if(phone=="null"){
										 phone = "xxxx";
										 }
									 
										var phLen = phone.length;
										var minPhLen = phLen - 4;
										phoneObj.value=phone.substring(minPhLen,phLen);
										userObj.value=userId;
								 
									
							} 
											if(req.status==500) {
											  alert("MembershipId does'nt exists");
											  clearFields2();
											  document.myform.userNameId2.focus();
											  return;
											}
											else{ 
												
											} 
						}	
						
					} 
		 
 function clearFields2() {
		document.getElementById("userNameId2").value = "";
		document.getElementById("firstNameId2").value = "";
		document.getElementById("lastNameId2").value = "";
 		document.getElementById("phoneId2").value = "";
		document.getElementById("addUserDetId").value = "";
		 
 }
 	
    function nonUserDetails1(param){
	
						if(param.value.length==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						 

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 
							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest22;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest22(){ 
						if (req.readyState == 4){ 
							if(req.status == 200){ 
									//clearDetails();
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
								
									var fNameObj = document.getElementById("cpAddfirstNameId1");
									var lNameObj = document.getElementById("cpAddlastNameId1");
									var phObj = document.getElementById("ecmpAddPhoneId1");  
									var usObj = document.getElementById("addUserDetId");  
								
									fNameObj.value= fName;
									lNameObj.value=lName;
									usObj.value=usId;
									if(ph=="null"){
									 	ph = "xxxx";
									 }
									 
										var phLen = ph.length;
										var minPhLen = phLen - 4;
										phObj.value=ph.substring(minPhLen,phLen);
							} 
							if(req.status==500) {
							  alert("companyName does'nt exists");
							  clear2();
							  document.myform.existAddCompNameId1.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
							} 
							  
						}	
					} 


		          function clear2() {
						document.getElementById("cpAddfirstNameId1").value = "";
						document.getElementById("cpAddlastNameId1").value = "";
						document.getElementById("ecmpAddPhoneId1").value = "";
						document.getElementById("addUserDetId").value = "";
						document.getElementById("existAddCompNameId1").value = "";
					}					
					
	function nonUserDetails(param){
	  //alert('hi');
					//alert(param.value);
						if(param.value.length==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						 

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 

							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest33;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest33(){ 
						if (req.readyState == 4){ 
						   //alert(req.readyState);
							if(req.status == 200){ 
									//clearDetails();
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
									//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 

									var fNameObj = document.getElementById("firstNameId3");
									var lNameObj = document.getElementById("lastNameId3");
									var phObj = document.getElementById("phoneId3");  
									var usObj = document.getElementById("addUserDetId");  
									 
									fNameObj.value= fName;
									lNameObj.value=lName;
									usObj.value=usId;
									 
									
								 
									
									if(ph=="null"){
									 	ph = "xxxx";
									 }
									 
										var phLen = ph.length;
										var minPhLen = phLen - 4;
										phObj.value=ph.substring(minPhLen,phLen);
									//showAge();
							} 
							if(req.status==500) {
							  alert("userName does'nt exists");
							  clear();
							  document.myform.userNameId3.focus();
							  return;
							}
							else{ 
								
							} 
							  
						}	
					} 
		          function clear() {
						document.getElementById("userNameId3").value = "";
						document.getElementById("firstNameId3").value = "";
						document.getElementById("lastNameId3").value = "";
				
						document.getElementById("phoneId3").value = "";
						document.getElementById("addUserDetId").value = "";
					
					}			
   
 
</script>

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<body >

<%
String sessionInvoiceId = "1";
            session.setAttribute("sessionInvoiceId", sessionInvoiceId);  
String horseMemberId = (String)request.getAttribute("horseMemberId");
  System.out.print(horseMemberId);
  String amount ="";
  String amount1 = (String)request.getAttribute("amount");
  DecimalFormat myfor = new DecimalFormat("####.00");
  double finalAmt1 = Double.parseDouble(amount1);
   //String amount = (String)request.getAttribute("amount");
   System.out.print("finalAmt" + finalAmt1);
   amount = myfor.format(finalAmt1);
  System.out.print(amount);
  String serTypId = (String)request.getAttribute("serTypId");
  System.out.print("serTypId :"+serTypId);
  
  String horseRegType=(String)request.getAttribute("horseTypeName");
  String competitionName=(String)request.getAttribute("competitionName");
  
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
		    <td class="menuTablePad">&nbsp;</td>
		    <td class="subDeptTablePad">&nbsp;</td>
	      </tr>
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<div class="cmmnForm">
	<div class="colspan">
		<strong>Membership:</strong> <span class="styleBoldTwo"> Additional Non-Human Owner/Rider Form </span>	</div>
		<div id="commonBG" class="textCommon" style="height:20px;">
        <span class="asterisk">IMPORTANT </span>--The only accepts payment by credit card for services purchased online. If you prefer to pay by check, please complete the appropriate <a href="http://useventing.com/start.php?section=docs" target="_blank" >form </a>and mail with your check to the office.</div> 
	
        <form name="myform" id="myform" method="post" action="addOwnRidPay.do" onSubmit="return myValidate();">
 <input type="hidden" name="process" value="horseReg" />
<input type="hidden" name="addOwnerUserId" id="addUserDetId" />
  <input type="hidden" name="horseMemberId" value="<%=horseMemberId%>">
 <div class="rowExpand" > Addtional Non-Human Owner/Rider Form: </div>
 <input type="hidden" name="serTypId" value="<%=serTypId%>">
 <input type="hidden" name="addAmt" value="<%=amount%>">
 
 <input type="hidden" value="<%=horseRegType%>" name="horseRegType"/>
<input type="hidden" value="<%=competitionName%>" name="competitionName"/>
 
 <div class="rowHead"><strong>Rider Information Section:</strong></div>
		 
	<div class="row">
				<span class="label">No.:</span>
					<span class="formX">
					<input type="text" class="textboxOne" name="arhlcNo" id="addR_num" size="10" onblur="arCheck();resetRiderOwner();" />
					
					</span>		
				</div>
			 

					<div class="row">
						<span class="label">Salutation:</span>
						<span class="formX">
						<input type="text" name="addRprefix" id="addRprefix" readonly="true" class="readOnly" size="10" />
						</span>
					</div>
					<div class="row">
						<span class="label">First Name:</span>
							<span class="formX">
							<input type="text" readonly="true" class="readOnly" name="additionalRider" id="additionalRider" size="20" />
							</span>					
					</div>
					<div class="row">
						<span class="label">Last Name:</span>
							<span class="formX">
							<input type="text" readonly="true" class="readOnly" name="lastRider" id="lastRider" size="20" />
							</span>	
					</div>
					<div class="row">
						<span class="label">Street:</span>
						<span class="formX">
						<input type="text"  class="readOnly"  readonly="true" name="addRStreet" id="addRStreet" size="20" />
						</span>
					</div>
					<div class="row">
						<span class="label">City:</span>
						<span class="formX">
						<input type="text" class="readOnly" readonly="true" name="addRCity" id="addRCity" size="15" />
						</span>	
				    </div>
					<div class="row">
						<span class="label">State:</span>
						<span class="formX">
						<input type="text" class="readOnly" readonly="true" name="addRState" id="addRState" size="20" />
						</span>
					</div>
					<div class="row">
						<span class="label">Zipcode:</span>
						<span class="formX">
						<input type="text" class="readOnly" readonly="true" name="addRZipcode" id="addRZipcode" size="8" />
						</span>
				   </div>
					<div class="row">
						<span class="label">Phone:</span>
						<span class="formX">
						<input type="text" class="readOnly" readonly="true" name="addRPhone" id="addRPhone" size="15" />
						</span>
					</div>
					<div class="row">
						<span class="label">Cell:</span>
						<span class="formX">
						<input type="text" class="readOnly" readonly="true" name="addRCell" id="addRCell" size="15" />
						</span>
					</div>
					<div class="row">
						<span class="label">Email:</span>
						<span class="formX">
						<input type="text" class="readOnly" readonly="true" name="addREmail" id="addREmail" size="20" />
						</span>
					</div>
	
	<div class="rowHead"><strong>Owner Information Section:</strong>	</div>
		<div>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
					<tr>
							<td class="tableLeftTxtArea">Choose one option that apply: </td>
								<td class="tableRight">
									 <input type="radio" size="10" name="regAddFor" id="regAddFor" value="mem1" onClick="copyRiderOwner(), showHideRadio('regAddFor','ridAddMemb','mem1'), showHideRadio('regAddFor','accAddUser','mem1'), showHideRadio('regAddFor','companyAddUser','mem1'), showHideRadio('regAddFor','existAddComp','mem1'), showHideRadio('regAddFor','compAddQuest','mem1'), clearHLCdetails1(); clearNotHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1(); " />
									The owner is the rider. <br />
									<input type="radio" size="10" name="regAddFor" id="regAddFor" value="rid" onClick="switchDiv('ridAddMemb'),showHideRadio('regAddFor','ridAddUser','rid'), showHideRadio('regAddFor','accAddUser','rid'), showHideRadio('regAddFor','companyAddUser','rid'), showHideRadio('regAddFor','existAddComp','rid'), showHideRadio('regAddFor','compAddQuest','rid'), clearNotHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1();" />
									Owner is Member. <br />
									<input type="radio" size="10" name="regAddFor" id="regAddFor" value="acc" onClick="switchDiv('accAddUser'),showHideRadio('regAddFor','ridAddUser','acc'), showHideRadio('regAddFor','companyAddUser','acc'), showHideRadio('regAddFor','ridAddMemb','acc'), showHideRadio('regAddFor','existAddComp','acc'), showHideRadio('regAddFor','compAddQuest','acc'), clearHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1();clear2();" />
									Owner has a dashboard user name (Login Id).  <br />
																	
									<input type="radio" size="10" name="regAddFor" id="regAddFor" value="cmp" onClick="switchDiv('compAddQuest'),showHideRadio('regAddFor','ridAddUser','cmp'), showHideRadio('regAddFor','ridAddMemb','cmp'), showHideRadio('regAddFor','accAddUser','cmp'), showHideRadio('regAddFor','companyAddUser','cmp'), showHideRadio('regAddFor','existAddComp','cmp'), clearHLCdetails1(); clearNotHLCdetails1(); " />
									The owner is a Company.
								</td>
				    </tr>
					<tr>
							<td colspan="2">
							<div id="ridAddUser">
								  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr> 
									  <td colspan="2" class="tblMainHead"> Owner - Rider </td>
									</tr>
									<tr>
									  <td class="tableLeft">Member ID:</td>
									  <th class="tableRight"><input name="ownerUseaNoTwo" id="ownerUseaNo2_id" class="readOnly" readonly="readonly" size="20" /></th>
									</tr>
									<tr> 
										<td class="tableLeft">First Name:</td>
										<th class="tableRight"><input name="ownerFNameTwo" id="ownerFname2_id" class="readOnly" readonly="readonly"  size="20" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Last Name:</td>
										<th class="tableRight"><input name="ownerLNameTwo" id="ownerLname2_id" class="readOnly" readonly="readonly"  size="20" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Phone:</td>
										<th class="tableRight"><input name="ownerPhoneTwo" id="ownerPhone2_id" class="readOnly" readonly="readonly"  size="8" /></th>
									</tr>
								 </table>
								 </div>
							</td>
				    </tr> 
					   <tr>
						   <td colspan="2"> 
					<div id="compAddQuest">	   
						   <table width="100%" border="0" cellspacing="0" cellpadding="0">
						   <tr>
							<td class="tableLeft">Is the company already registered </td>
							<td class="tableRight">
								<input type="radio" size="10" name="ecmp1" id="ecmp1" value="yes" onClick="switchDiv('existAddComp'); showHideRadio('ecmp1','companyAddUser','yes'); comNewClear1();"> Yes
								<input type="radio" size="10" name="ecmp1" id="ecmp1" value="no"  onClick="switchDiv('companyAddUser'); showHideRadio('ecmp1','existAddComp','no'); comRegClear1();"> No							</td>
						  </tr>
</table>
</div>
</td></tr>
						  <tr>
							<td colspan="2">
							<div id="ridAddMemb">
								  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr> 
									  <td colspan="2" class="tblMainHead"> Owner Information - Member</td>
									</tr>
									<tr>
									  <td class="tableLeft">Member ID:</td>
									  <th class="tableRight"><input name="userNameId2" id="userNameId2" class="textboxOne" size="20" onblur="userDetails1(this);"/> <span class="asterisk">*</span> </th>
									</tr>
									<tr> 
										<td class="tableLeft">First Name:</td>
										<th class="tableRight"><input name="firstNameId2" class="textboxOne" id="firstNameId2" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Last Name:</td>
										<th class="tableRight"><input name="lastNameId2" class="textboxOne" id="lastNameId2" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Phone: (Last 4 digits)</td>
										<th class="tableRight">xxx-xxx-<input name="phoneId2" class="textboxOne" id="phoneId2" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
								 </table></div>								 </td>
						  </tr>						  
						  <tr>
							<td colspan="2">
							<div id="accAddUser">
							   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr> 
									  <td colspan="2" class="tblMainHead"> Owner Information - Web User </td>
									</tr>
									<tr>
									  <td class="tableLeft">Username:</td>
									  <th class="tableRight"><input name="userNameId3" id="userNameId3" class="textboxOne" size="20" onblur="nonUserDetails(this);" /> <span class="asterisk">*</span> </th>
									</tr>
									<tr> 
										<td class="tableLeft">First Name:</td>
										<th class="tableRight"><input name="firstNameId3" class="textboxOne" id="firstNameId3" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Last Name:</td>
										<th class="tableRight"><input name="lastNameId3" class="textboxOne" id="lastNameId3" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Phone: (Last 4 digits)</td>
										<th class="tableRight">xxx-xxx-<input name="phoneId3" id="phoneId3" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" readonly="readonly" size="8" /></th>
									</tr>
							  </table></div>								</td>
						  </tr>
						  
						  <tr>
							<td colspan="2">
							<div id="existAddComp">
							   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr> 
									  <td colspan="2" class="tblMainHead"> Owner Information - Company </td>
									</tr>
									
									<tr>
									  <td class="tableLeft">Company Name:</td>
			<th class="tableRight"><input name="existAddCompNameId1" id="existAddCompNameId1" class="textboxOne" size="20" onblur="nonUserDetails1(this);"  /> <span class="asterisk">*</span> </th>
									</tr>
									<tr> 
										<td class="tableLeft">Contact Person First Name:</td>
										<th class="tableRight"><input name="cpAddfirstNameId1" class="textboxOne" id="cpAddfirstNameId1" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Contact Person Last Name:</td>
										<th class="tableRight"><input name="cpAddlastNameId1" class="textboxOne" id="cpAddlastNameId1" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
									</tr>
									<tr>
										<td class="tableLeft">Phone: (Last 4 digits)</td>
										<th class="tableRight">xxx-xxx-<input name="ecmpAddPhoneId1" id="ecmpAddPhoneId1" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" readonly="readonly" size="8" /></th>
									</tr>
							  </table></div>							</td>
						  </tr>
						  
						  <tr>
						<td colspan="2">
						<div id="companyAddUser">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								  
								  <tr> 
								  <td colspan="2" class="tblMainHead"> Owner Information - New Company </td>
								</tr>
								  <tr>
									<td class="tableLeft"> Company Name:</td>
									<td class="tableRight"><input name="newAddFirstNameIdComp" id="newAddFirstNameIdComp" class="textboxOne" size="20" onblur="usrStat();"/>
									<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Address</td>
									<td colspan="-1" class="tableRight">
										<input name="newAddAddressIdComp" id="newAddAddressIdComp" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> City</td>
									<td colspan="-1" class="tableRight">
										<input name="newAddCityIdComp" id="newAddCityIdComp" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								   <tr> 
									<td class="tableLeft"> State</td>
									<td colspan="-1" class="tableRight"> <span class="row"><span class="formX"> 
									 <SELECT name="newAddStateIdComp" id="newAddStateIdComp" class="selectboxOne" ></SELECT>
									  <span class="asterisk">*</span> </span></span></td>
								  </tr>
								   <tr> 
									<td class="tableLeft"> Zip Code</td>
									<td colspan="-1" class="tableRight">
										<input name="newAddZipIdComp" id="newAddZipIdComp" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Country</td>
									<td colspan="-1" class="tableRight">

									<SELECT name="newAddCountryIdComp" id="newAddCountryIdComp" class="selectboxOne"  onChange="FillState(document.myform.newAddCountryIdComp, document.myform.newAddStateIdComp, '--Select One--');">
									</SELECT>
									<span class="asterisk">*</span> </td>
								  </tr>
			
								   <tr> 
									<td class="tableLeft">EMail-ID</td>
									<td colspan="-1" class="tableRight">
									   <input name="newAddEmailIdComp" id="newAddEmailIdComp" class="textboxOne" size="20" />
									   <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Phone</td>
									<td colspan="-1" class="tableRight">
									   <input name="newAddPhoneIdComp" id="newAddPhoneIdComp" class="textboxOne" size="20" />
									   <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Fax</td>
									<td colspan="-1" class="tableRight">
									   <input name="newAddFaxIdComp" id="newAddFaxIdComp" class="textboxOne" size="20" /></td>
								  </tr>
						  	      <tr> 
									<td class="tableLeft">Contact Person First Name</td>
									<td colspan="-1" class="tableRight">
									   <input name="cpAddFirstNameIdComp" id="cpAddFirstNameIdComp" class="textboxOne" size="20" />
                                       <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Contact Person Last Name</td>
									<td colspan="-1" class="tableRight">
									   <input name="cpAddLastNameIdComp" id="cpAddLastNameIdComp" class="textboxOne" size="20" />
                                       <span class="asterisk">*</span></td>
								  </tr>
								  
						  </table>	</div>					</td>
				  </tr>
 	
			 
		</table>
		</div>
		
		<%
		System.out.print("loggedBy in horse registration:" + session.getAttribute("loggedBy"));
		
 
int si=0;
String logBy="user";
if(session.getAttribute("loggedBy")!=null){
String loggedBy="";
loggedBy=(String)session.getAttribute("loggedBy");
System.out.print("loggedBy" + loggedBy);
logBy=loggedBy;
if(loggedBy.equalsIgnoreCase("Admin") || loggedBy.equalsIgnoreCase("HLC Staff")){
            
 	%>
	 <div id="phoneReg">
		<div class="rowHead">Phone Registration Information:</div>	
				<%  
				 
				String horsesertypVect[] =(String[])request.getAttribute("horsesertypVect");
				DecimalFormat myFormatter = new DecimalFormat("######.00");
				String typeId ="";
				String typeName ="";
				String typePrice ="";
				System.out.print("horsesertypVect" + horsesertypVect);
				String output ="";
				if(horsesertypVect!=null){
						  typeId = horsesertypVect[0];
						  typeName = horsesertypVect[1];
						  typePrice = horsesertypVect[2];
						double finalAmt = Double.parseDouble(typePrice);
						System.out.print("typeName" + typeName);
						   output = myFormatter.format(finalAmt);
				}
 				%>
   
<div class="row">
<span class="floatLeft">
<input type="hidden" name="status" value="yes">
<input type="hidden" name="servicePrice" value="<%=typeId%>" >
<input type="checkbox" name="phoneCharge" id="cn" value="<%=typePrice%>" size="10" onClick="dispAmt();"> </span>
<span class="floatLeftRadio"><%=typeName%>
</span>
<span class="floatRight"><strong><%=output%></strong>&nbsp;&nbsp;</span>            </div>
<% }
else {
%>
 <input type="hidden" name="status" value="no">	
<% }
}%>
</div>
		 
		 <div class="rowHead">
		Payment Information:	</div>
		<div>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">			 
				  <tr>
					<td width="209" class="tableLeft"><strong>Total Amount:</strong></td>
					<td width="291" class="tableRight"> <strong>$</strong> 
					<input type="hidden" name="tempAmount" readonly="true" class="readOnly" size="10" value="" />
					<input type="text" name="totalAmount" readonly="true" class="readOnly" size="10" value="<%=amount%>" />					&nbsp; U.S. Dollars	 </td>				 
				  </tr>
					<%	if(session.getAttribute("loggedBy")!=null){%>
					<tr>
					<td class="tableLeft">
						<input type="radio" size="10" name="r11" id="r11" value="check" onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'), hideImgDiv('imgId'), checkClear();cardClear();"/> Check enclosed.				 	</td>
					<td class="tableRight">
						<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'), showHideRadio('r11','chkEncAcm','card'), showImgDiv('imgId'), cardClear();checkClear();"/> Please charge my card.					</td>
				  </tr>
				  <input type="hidden" name="hidVal" value="adminLogin"/>	
				  <%}else{%>
				  <tr>
				  <td class="tableLeft" colspan="3">
						<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'),cardClear();"/> Please charge my card.</td>
				  </tr>
				  <input type="hidden" name="hidVal" value="userLogin"/>	
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
							If paying by check , please mail your payment to: 
								<br />
								<br />
								<strong>
								<br />
								Additional Owner/Rider Registration
								<br />
								525 Old Waterford Road NW
								<br />
								Leesburg, VA 20176												    </strong><br />
								<br />
								<strong>Note:</strong><span class="styleBoldOne"> Your registration status will be pending until check is processed.		</span> <br />
						<br /></td>
						  </tr>
					<%
					System.out.print("loggedBy:" + session.getAttribute("loggedBy"));
					if(session.getAttribute("loggedBy")!=null){
     			%>
				 <input type="hidden" name="roughVal" value="yes">
			<tr>
			   
			  <td class="tableLeft">Check Amount:</td>
							  <td class="tableRight"><strong>$</strong> 
							 
							  <input type="text" name="chkBalAmt" id="chkBalAmt" class="textboxOne" size="16" maxlength="10" />
								 &nbsp; U.S. Dollars			  </td>
			</tr>
			<%
			}else{%>
			 <input type="hidden" name="roughVal" value="no">
			<%}
			 %>
							<tr>
							  <td class="tableLeft">Check No:</td>
							  <td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
							
						<%	if(session.getAttribute("loggedBy")!=null){%>
						<input type="hidden" name="roughValue" value="yes">	

						
					<tr>
						<td class="tableLeft">Check Date:</td>
						<td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" size= "11" maxlength="13" />

							   <a href="javascript:cal1.popup();">
				   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					  <span class="asterisk">*</span>												  </td>
						  </tr>
							
						<%}
						else{%>
						<input type="hidden" name="roughValue" value="no">
						<tr>
							  <td class="tableLeft">Check Date:</td>
							  <td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" readonly="true" size="11" />

							   <a href="javascript:cal1.popup();">
				   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					  <span class="asterisk">*</span>												  </td>
						  </tr>
							<%}%>	
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
						</table></div>					</td>
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
									<select name="ccType" id="ccType" class="selectboxOne" >
									  <option selected="selected" value="">Select One</option>
									  <option value="Visa">Visa</option>
									  <option value="MasterCard">Master Card</option>

									  <option value="American Express">AmEx</option>
									</select>
									<span class="asterisk">*</span>								</td>
							</tr>
							
							<tr>
								<td class="tableLeft">Card No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="16" />
								  <span class="asterisk">*</span></td>
							</tr>
							<%	if(session.getAttribute("loggedBy")!=null){%>
							<tr>
								<td class="tableLeft">Card CVV No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" /> 
							  &nbsp;(see details below)							  </td>
							</tr>
							<%}else{%>
							<tr>
								<td class="tableLeft">Card CVV No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
							  <span class="asterisk">*</span> &nbsp;(see details below)							  </td>
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
					 </table></div>								</td>
			</tr>
			 <tr>
				<td>&nbsp;</td>
				</tr>
			          <tr>
				<td colspan="2">
			<center> <input name="annButName" type="submit" class="gradBtn" value="Submit" />&nbsp;&nbsp;
			<input name="annButName" type="button" class="gradBtn" value="Cancel"  onclick="javascript:history.back(-1);"/>	
			</center>				  </td>
			</tr>
			<tr> 
					<td colspan="2">
						<div id="imgId">
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
				<tr>
				<td>&nbsp;</td>
				</tr>
					
				<tr>
					<td><center><img src="images/cvv_graphic.jpg" /></center></td></tr>
					</table>
					</div>
					</td>
					</tr>
									
				</table>
				
			</div>
		 
			
						
	
	<div id="spacer">&nbsp;</div>

</form>
</div>

	<!-- CONTENTS END HERE -->			</td>
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

	FillCountry(document.myform.newAddCountryIdComp, document.myform.newAddStateIdComp, 'USA');
	FillState(document.myform.newAddCountryIdComp, document.myform.newAddStateIdComp, '---Select---');
	
	var cal1 = new calendar2(document.forms['myform'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
</script>
</html>
