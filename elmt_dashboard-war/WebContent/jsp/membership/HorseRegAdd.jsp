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
<script src="javascripts/frmHorseRegisterAdd.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script type="text/javascript">
	
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
  String horseMemberId = (String)request.getAttribute("horseMemberId");
  String cardStatus =(String)request.getAttribute("CardStatus");
  String requestId =(String)request.getAttribute("requestId");
 
  System.out.print("RequestId in HorseRegAdd.jsp :"  + requestId);
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
		<strong>Membership:</strong> <span class="styleBoldTwo">Add Horse Rider/Owner Form: </span>	</div>
     
	
<form name="myform" id="myform" method="post" action="horserRidOwnAdd.do" onSubmit="return myValidate();">
 <input type="hidden" name="process" value="horseReg" />
<input type="hidden" name="addOwnerUserId" id="addUserDetId" />
<input type="hidden" name="cardStatus" value="<%=cardStatus%>"/>
<input type="hidden" name="requestId" value="<%=requestId%>"/>
  <input type="hidden" name="horseMemberId" value="<%=horseMemberId%>">
 
 
<div class="rowHead">
<strong>Rider Information Section:</strong>
</div>
		 
	<div class="row">
				<span class="label">No.:</span>
					<span class="formX">
					<input type="text" class="textboxOne" name="arhlcNo" id="addR_num" size="10" onblur="arCheck(); resetRiderOwner();" />
					
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
	
	<div class="rowHead">
				<strong>Owner Information Section:</strong>	</div>
	<div>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
					<tr>
							<td class="tableLeftTxtArea">Choose one option that apply: </td>
								<td class="tableRight">
									 <input type="radio" size="10" name="regAddFor" id="regAddFor" value="mem1" onClick="copyRiderOwner(), showHideRadio('regAddFor','ridAddMemb','mem1'), showHideRadio('regAddFor','accAddUser','mem1'),  showHideRadio('regAddFor','companyAddUser','mem1'), showHideRadio('regAddFor','existAddComp','mem1'), showHideRadio('regAddFor','compAddQuest','mem1'), clearHLCdetails1(); clearNotHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1(); " />
									The owner is the rider. <br />
									<input type="radio" size="10" name="regAddFor" id="regAddFor" value="rid" onClick="switchDiv('ridAddMemb'),showHideRadio('regAddFor','ridAddUser','rid'), showHideRadio('regAddFor','accAddUser','rid'),  showHideRadio('regAddFor','companyAddUser','rid'), showHideRadio('regAddFor','existAddComp','rid'), showHideRadio('regAddFor','compAddQuest','rid'), clearNotHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1();" />
									Owner is Member. <br />
									<input type="radio" size="10" name="regAddFor" id="regAddFor" value="acc" onClick="switchDiv('accAddUser'),showHideRadio('regAddFor','ridAddUser','acc'), showHideRadio('regAddFor','companyAddUser','acc'), showHideRadio('regAddFor','ridAddMemb','acc'), showHideRadio('regAddFor','existAddComp','acc'), showHideRadio('regAddFor','compAddQuest','acc'), clearHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1();clear2();" />
									The Owner has a Dashboard Username (Login ID)<br />
								<input type="radio" size="10" name="regAddFor" id="rself1" value="cmp" onClick="switchDiv('compAddQuest'),showHideRadio('regAddFor','ridAddUser','cmp'), showHideRadio('regAddFor','ridAddMemb','cmp'), showHideRadio('regAddFor','accAddUser','cmp'), showHideRadio('regAddFor','companyAddUser','cmp'), showHideRadio('regAddFor','existAddComp','cmp'), clearHLCdetails1(); clearNotHLCdetails1();" />
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
    <td class="tableLeft">Is the company already registered </span>
							<td class="tableRight">
								<input type="radio" size="10" name="ecmp1" id="ecmp1" value="yes" onClick="switchDiv('existAddComp'); showHideRadio('ecmp1','companyAddUser','yes'); comNewClear1();"> Yes
								<input type="radio" size="10" name="ecmp1" id="ecmp1" value="no"  onClick="switchDiv('companyAddUser'); showHideRadio('ecmp1','existAddComp','no'); comRegClear1();"> No							</td>
  </tr>
</table>
</div>

						   </td>
					 </tr>
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
								 </table>
								 </div>								 </td>
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
							  </table>
							  </div>								</td>
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
							  </table>
							  </div>							</td>
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
									<td class="tableRight"><input name="newAddFirstNameIdComp" id="newAddFirstNameIdComp" class="textboxOne" size="20"  onblur="usrStat();"/>
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
						  </table>
						  </div>						</td>
				  </tr>
				  <tr>
							  <td colspan="2" class="tblDescrp">
								    
									    
													<input  type="submit" class="gradBtn" value="Submit" />
													 
													<input  type="button" class="gradBtn" value="Cancel" onclick="location.href='index.jsp'"/>
													 	
									 
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
</script>
</html>
