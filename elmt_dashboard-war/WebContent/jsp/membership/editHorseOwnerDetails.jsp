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
<%@ page import="com.breed.DAO.*"%>
<%@ page import="com.color.DAO.*"%>
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
<script src="javascripts/frmHorseRegisterEdit.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script type="text/javascript">
var arHttpRequest;
var req;
     function userDetails(param){
	       
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
			req.onreadystatechange = processRequest1;         
			req.open("GET", url, true);
			req.send(null);  
} 
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
         function processRequest1(){ 
    			if (req.readyState == 4){ 
						   
							if(req.status == 200){ 
							 
								 var xmlDoc = req.responseXML.documentElement;
									var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
									var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
									var dob =xmlDoc.getElementsByTagName('dob')[0].childNodes[0].nodeValue; 
									var age =xmlDoc.getElementsByTagName('age')[0].childNodes[0].nodeValue; 
									var firstNameObj = document.getElementById("firstNameId");
								    var lastNameObj = document.getElementById("lastNameId");
									var phoneObj = document.getElementById("phoneId"); 
							        var userObj = document.getElementById("userDetId"); 
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
											  clearFields();
											  document.myform.userNameId.focus();
											  return;
											}
											else{ 
												
											} 
						}	
						
					} 
		 
 function clearFields() {
		document.getElementById("userNameId").value = "";
		document.getElementById("firstNameId").value = "";
		document.getElementById("lastNameId").value = "";
 		document.getElementById("phoneId").value = "";
		document.getElementById("userDetId").value = "";
 }
 function nonUserDetails(param){
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
 							if(req.status == 200){ 
 								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									var fNameObj = document.getElementById("firstNameId1");
									var lNameObj = document.getElementById("lastNameId1");
									var phObj = document.getElementById("phoneId1");  
									var usObj = document.getElementById("userDetId");  
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
							  alert("userName does'nt exists");
							  clear();
							  document.myform.userNameId1.focus();
							  return;
							}
							else{ 
								 
							} 
							  
						}	
					} 
		          function clear() {
						document.getElementById("userNameId1").value = "";
						document.getElementById("firstNameId1").value = "";
						document.getElementById("lastNameId1").value = "";
						document.getElementById("phoneId1").value = "";
						document.getElementById("userDetId").value = "";
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
									 
								 
									var firstNameObj = document.getElementById("ownerFname_id");
								    var lastNameObj = document.getElementById("ownerLname_id");
									var phoneObj = document.getElementById("ownerphone_id"); 
							        var userObj = document.getElementById("userDetId"); 
								 
									
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
											  alert("RiderId does'nt exists");
											  clearFields2();
											  document.myform.ownerUseaNo_id.focus();
											  return;
											}
											else{ 
												
											} 
						}	
						
					} 
		 
 function clearFields2() {
		document.getElementById("ownerFname_id").value = "";
		document.getElementById("ownerLname_id").value = "";
		document.getElementById("ownerUseaNo_id").value = "";
 		document.getElementById("ownerphone_id").value = "";
		document.getElementById("userDetId").value = "";
		 
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
								
									var fNameObj = document.getElementById("cpfirstNameId1");
									var lNameObj = document.getElementById("cplastNameId1");
									var phObj = document.getElementById("ecmpPhoneId1");  
									var usObj = document.getElementById("userDetId");  
								
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
							  document.myform.existCompNameId1.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
							} 
							  
						}	
					} 


		          function clear2() {
						document.getElementById("cpfirstNameId1").value = "";
						document.getElementById("cplastNameId1").value = "";
						document.getElementById("ecmpPhoneId1").value = "";
						document.getElementById("userDetId").value = "";
						document.getElementById("existCompNameId1").value = "";
					}					
							
	
</script>

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
 </head>
<%
String horseId =(String)request.getAttribute("horseId");
	ArrayList listContact = (ArrayList)request.getAttribute("DispOwnDetails");
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				first_name  = (String)it.next();
				middle_name  = (String)it.next();
				last_name = (String)it.next();
				sufix =  (String)it.next();
				email_id  = (String)it.next();
				suite =  (String)it.next();
				address1 =  (String)it.next();
				address2 = (String)it.next();
				city = (String)it.next();
				state =  (String)it.next();
				country = (String)it.next();
				zip = (String)it.next();
				phone_no = (String)it.next();
				mobile_no = (String)it.next();
				fax_no = (String)it.next();
			}
		}
%>
 <%! 
     String  nullCheck(String fieldName){
        String retValue = "NG";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    %>
	 <%! 
   
    String  nullEmpty(String fieldName){
        String retValue = "";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    %>
<body  >
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
				
<div class="cmmnForm">
	<div class="colspan">
		<strong>Membership:</strong> <span class="styleBoldTwo">Edit Horse Owner Information</span></div>
    <div id="commonBG" class="textCommon" style="height:20px;">
	<span>
			  You can <strong>Edit</strong> a horse owner's information right here.
	</span>	
</div>
	
    <form name="myform" id="myform" method="post" action="Owner.do" onSubmit="return myValidate(this);">
	<input type="hidden" name="changeProcess" value="updateOwner">
<input type="hidden" name="userDetId" id="userDetId" />
<input type="hidden" name="horseId" value="<%=horseId%>" />

 
<!-- **************************************** Part A of the form starts here *********************************************** -->


<div class="formPart">

			<div class="rowHead">
				<strong>Existing Owner Information:</strong>
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX"><%=nullEmpty(first_name)%>&nbsp;<%=nullEmpty(last_name)%>&nbsp;</span>
			</div>
			<div class="row">
				<span class="label">Address1:</span>
				<span class="formX"><%=nullEmpty(address1)%>&nbsp;<%=nullEmpty(address2)%>&nbsp; </span>
			</div>
			 
			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><%=nullCheck(city)%>&nbsp; </span>
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX"><%=nullCheck(state)%>&nbsp;</span>
			</div>
			
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><%=nullCheck(zip)%>&nbsp; </span>
			</div>
			<div class="row">
				<span class="label">Country:</span>
				<span class="formX"><%=nullCheck(country)%>&nbsp; </span>
			</div>
				<div class="row">
				<span class="label">Phone:</span>
				<span class="formX"><%=nullCheck(phone_no)%>&nbsp; </span>
			</div>
			
			<div class="row">
				<span class="label">Fax:</span>
				<span class="formX"><%=nullCheck(fax_no)%>&nbsp;</span>
			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX"><%=nullCheck(email_id)%>&nbsp; </span>
			</div>
			
			
</div>
<!-- **************************************** Part B of the form starts here *********************************************** -->


<div class="formPart">
	<div class="rowHead">
				<strong>New Owner Information Section:</strong>
	</div>
	<div>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
				<tr>
					<td class="tableLeftTxtArea">Choose one option that apply: </td>
					<td class="tableRight">
					<input type="radio" size="10" name="regFor" id="rself" value="mem1" onClick="switchDiv('ridUser'), showHideRadio('regFor','ridMemb','mem1'), showHideRadio('regFor','accUser','mem1'), showHideRadio('regFor','companyUser','mem1'), showHideRadio('regFor','existComp','mem1'), showHideRadio('regFor','compQuest','mem1'), clearHLCdetails(); clearNotHLCdetails();   comRegClear(); comNewClear(); comRadioClear(); "/>
					The owner is the rider. <br />
					<input type="radio" size="10" name="regFor" id="rself" value="rid1" onClick="switchDiv('ridMemb'), showHideRadio('regFor','accUser','rid1'), showHideRadio('regFor','companyUser','rid1'), showHideRadio('regFor','ridUser','rid1'), showHideRadio('regFor','existComp','rid1'), showHideRadio('regFor','compQuest','rid1'); clearRiderdetails(); clearNotHLCdetails();  comRegClear(); comNewClear(); comRadioClear();" />
					Owner is Member. <br />
					<input type="radio" size="10" name="regFor" id="rself" value="acc1" onClick="switchDiv('accUser'),  showHideRadio('regFor','companyUser','acc1'), showHideRadio('regFor','ridUser','acc1'), showHideRadio('regFor','ridMemb','acc1'), showHideRadio('regFor','existComp','acc1'), showHideRadio('regFor','companyUser','acc1'), showHideRadio('regFor','compQuest','acc1'), clearRiderdetails(); clearHLCdetails();comRegClear(); comNewClear(); comRadioClear();" />
					The owner has a Dashboard User Name(Login Id).  <br />
					 							
					<input type="radio" size="10" name="regFor" id="rself" value="cmp1" onClick="switchDiv('compQuest'), showHideRadio('regFor','ridUser','cmp1'), showHideRadio('regFor','ridMemb','cmp1'),  showHideRadio('regFor','accUser','cmp1'),  clearRiderdetails(); clearHLCdetails(); clearNotHLCdetails();" />
					The owner is a Company.					</td>
				  </tr>
					<!--
					<tr> 
					  <td colspan="2" height="15" class="styleError">Sorry, the option you have selected does not match your profile.</td>
					</tr>
					-->
				  <tr id="compQuest">
					<td class="tableLeft">Is the company already registered ?</span>
					<td class="tableRight">
						<input type="radio" size="10" name="ecmp" id="ecmp" value="yes" onClick="switchDiv('existComp'); showHideRadio('ecmp','companyUser','yes'); comNewClear();"> Yes
						<input type="radio" size="10" name="ecmp" id="ecmp" value="no"  onClick="switchDiv('companyUser'); showHideRadio('ecmp','existComp','no'); comRegClear();"> No					</td>
				  </tr>
				  <tr>
					<td colspan="2" id="ridUser">
						  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr> 
							  <td colspan="2" class="tblMainHead">Owner Information - Rider </td>
							</tr>
							<tr>
							  <td class="tableLeft">Rider ID:</td>
							  <th class="tableRight"><input name="userNameOne" id="ownerUseaNo_id" class="textboxOne"  size="20" onblur="userDetails1(this);"/></th>
							</tr>
							<tr> 
								<td class="tableLeft">First Name:</td>
								<th class="tableRight"><input name="firstNameOne" id="ownerFname_id" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /> </th>
							</tr>
							<tr>
								<td class="tableLeft">Last Name:</td>
								<th class="tableRight"><input name="lastNameOne" id="ownerLname_id" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /> </th>
							</tr>
							<tr>
								<td class="tableLeft">Phone: (Last 4 digits)</td>
								<th class="tableRight">xxx-xxx- <input name="phoneOne" id="ownerPhone_id" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="8" readonly="readonly" /> </th>
							</tr>
						 </table>					</td> 
				  </tr>	
				  <tr>
					<td colspan="2" id="ridMemb">
						  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr> 
							  <td colspan="2" class="tblMainHead">Owner Information - Members</td>
							</tr>
							
							<tr>
							  <td class="tableLeft">Member ID:</td>
							  <th class="tableRight"><input name="userName" id="userNameId" class="textboxOne" size="20" onblur="userDetails(this);" /> <span class="asterisk">*</span> </th>
							</tr>
							<tr> 
								<td class="tableLeft">First Name:</td>
								<th class="tableRight"><input name="firstName" class="textboxOne" id="firstNameId" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Last Name:</td>
								<th class="tableRight"><input name="lastName" class="textboxOne" id="lastNameId" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Phone: (Last 4 digits)</td>
								<th class="tableRight">xxx-xxx-<input name="phone" class="textboxOne" id="phoneId" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="8" readonly="readonly" /></th>
							</tr>
						 </table>								 </td>
				  </tr>						  
				  <tr>
					<td colspan="2" id="accUser">
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr> 
							  <td colspan="2" class="tblMainHead">Owner Information - Web User </td>
							</tr>
							
							<tr>
							  <td class="tableLeft">Username:</td>
							  <th class="tableRight"><input name="userNameId1" id="userNameId1" class="textboxOne" size="20" onblur="nonUserDetails(this);" /> <span class="asterisk">*</span> </th>
							</tr>
							<tr> 
								<td class="tableLeft">First Name:</td>
								<th class="tableRight"><input name="firstNameId1" class="textboxOne" id="firstNameId1" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Last Name:</td>
								<th class="tableRight"><input name="lastNameId1" class="textboxOne" id="lastNameId1" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Phone: (Last 4 digits)</td>
								<th class="tableRight">xxx-xxx-<input name="phoneId1" id="phoneId1" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" readonly="readonly" size="8" /></th>
							</tr>
					    </table>								</td>
				  </tr>
				  
				  <tr>
					<td colspan="2" id="existComp">
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr> 
							  <td colspan="2" class="tblMainHead">Owner Information - Company </td>
							</tr>
							
							<tr>
							  <td class="tableLeft">Company Name:</td>
							  <th class="tableRight"><input name="existCompNameId1" id="existCompNameId1" class="textboxOne" size="20" onblur="nonUserDetails1(this);"/> <span class="asterisk">*</span> </th>
							</tr>
							<tr> 
								<td class="tableLeft">Contact Person First Name:</td>
								<th class="tableRight"><input name="cpfirstNameId1" class="textboxOne" id="cpfirstNameId1" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Contact Person Last Name:</td>
								<th class="tableRight"><input name="cplastNameId1" class="textboxOne" id="cplastNameId1" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Phone: (Last 4 digits)</td>
								<th class="tableRight">xxx-xxx-<input name="ecmpPhoneId1" id="ecmpPhoneId1" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" readonly="readonly" size="8" /></th>
							</tr>
					    </table>								</td>
				  </tr>
				  
					 
				  <tr>
						<td colspan="2" id="companyUser">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								  
								  <tr> 
								  <td colspan="2" class="tblMainHead">Owner Information - New Company </td>
								</tr>
								  <tr>
									<td class="tableLeft"> Company Name:</td>
									<td class="tableRight"><input name="newFirstNameIdComp" id="newFirstNameIdComp" class="textboxOne" size="20" onblur="usrStat();" />
									<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Address</td>
									<td colspan="-1" class="tableRight">
										<input name="newAddressIdComp" id="newAddressIdComp" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								    <tr> 
									<td class="tableLeft"> City</td>
									<td colspan="-1" class="tableRight">
										<input name="newCityIdComp" id="newCityIdComp" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								   <tr> 
									<td class="tableLeft"> State</td>
									<td colspan="-1" class="tableRight"> <span class="row"><span class="formX"> 
									 <SELECT name="newStateIdComp" id="newStateIdComp" class="selectboxOne" style="width:200" width="200" ></SELECT>
									  <span class="asterisk">*</span> </span></span></td>
								  </tr>
								    <tr> 
									<td class="tableLeft"> Zip Code</td>
									<td colspan="-1" class="tableRight">
										<input name="newZipIdComp" id="newZipIdComp" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Country</td>
									<td colspan="-1" class="tableRight">
									<SELECT name="newCountryIdComp" id="newCountryIdComp" class="selectboxOne"  width="200" onChange="FillState(document.myform.newCountryIdComp, document.myform.newStateIdComp, '--Select One--');">
									</SELECT>
									<span class="asterisk">*</span> </td>
								  </tr>
								   <tr> 
									<td class="tableLeft">EMail-ID</td>
									<td colspan="-1" class="tableRight">
									   <input name="newEmailIdComp" id="newEmailIdComp" class="textboxOne" size="20" />
									   <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Phone</td>
									<td colspan="-1" class="tableRight">
									   <input name="newPhoneIdComp" id="newPhoneIdComp" class="textboxOne" size="20" />
									   <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Fax</td>
									<td colspan="-1" class="tableRight">
									   <input name="newFaxIdComp" id="newFaxIdComp" class="textboxOne" size="20" /></td>
								  </tr>
						  	      <tr> 
									<td class="tableLeft">Contact Person First Name</td>
									<td colspan="-1" class="tableRight">
									   <input name="cpFirstNameIdComp" id="cpFirstNameIdComp" class="textboxOne" size="20" /></td>

								  </tr>
								  <tr> 
									<td class="tableLeft">Contact Person Last Name</td>
									<td colspan="-1" class="tableRight">
									   <input name="cpLastNameIdComp" id="cpLastNameIdComp" class="textboxOne" size="20" /></td>
								  </tr>
						  </table>						</td>
				  </tr>
				<tr>
					<td colspan="2" class="alignCenter">
					<input type="submit" value="Submit" class="gradBtn" />&nbsp;
					<input type="button" value="Back" class="gradBtn" onclick="javascript:history.back(-1);" />
					</td>
				</tr>

		</table>
			</div>
	
			 
		 
		 
<div id="spacer">&nbsp;</div>
</div>
</form>
</div>

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
 
 	
	FillCountry(document.myform.newCountryIdComp, document.myform.newStateIdComp, 'USA');
	FillState(document.myform.newCountryIdComp, document.myform.newStateIdComp, '---Select---');
	 
</script>
</html>
