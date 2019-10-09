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
<script src="javascripts/HorseRegValidate.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmHorseRegister.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script type="text/javascript">
	/*function addiRider(){
	//alert("got in!");
		var domR = document.getElementById;
		var fomR = document.myform;
		if(fomR.arhlcNo.value != ""){
			domR('addRider').style.display = "block";
		}else{
			domR('addRider').style.display = "none";
		}
	}*/
	
	function resetRadioOne(){
		var chosen2="";
		len = document.myform.regFor.length ;
		for(i=0;i<len;i++){
			if(document.myform.regFor[i].checked){
			chosen2= document.myform.regFor[i].checked=false;
			
			document.getElementById('ridAddMemb').style.display = "none";
			document.getElementById('ridAddUser').style.display = "none";
			document.getElementById('accAddUser').style.display = "none";
			document.getElementById('newwAddUser').style.display = "none";
			document.getElementById('addOwn').style.display = "none";
			}
		}
	}
	
	/*function resetRadioTwo(){
		var chosen2="";
		len = document.myform.regFor.length ;
		for(i=0;i<len;i++){
			if(document.myform.regFor[i].checked){
			chosen2= document.myform.regFor[i].checked=false;
			}
		}
	}*/

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
			//}
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
								  //  var userNoId =xmlDoc.getElementsByTagName('userNo')[0].childNodes[0].nodeValue;
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

									var fNameObj = document.getElementById("firstNameId1");
									var lNameObj = document.getElementById("lastNameId1");
									var phObj = document.getElementById("phoneId1");  
									var usObj = document.getElementById("userDetId");  
									//var userDobObj = document.getElementById("dobId");
									//var userageObj = document.getElementById("ageId");
									 
									
									fNameObj.value= fName;
									lNameObj.value=lName;
									usObj.value=usId;
									//userDobObj.value=usrdob;
									//userageObj.value=usrage;
									//alert(firstNameObj.value);
									//alert(lastNameObj.value);
									
									
									//document.AnnualRegForm.newBadge.value=fName+" "+lName;
									
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
							  document.myform.userNameId1.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<div class="cmmnForm">
	<div class="colspan">
		<strong>Membership:</strong><span class="rowExpand"> Change Horse Owner/Rider/ Name Form</span></div>
    
    <form name="myform" id="myform" method="post" action="InsertHorseReg.do" onSubmit="return myValidate(this);">
<input type="hidden" name="ownerUserId" id="userDetId" />
<!--<input type="hidden" name="addOwnerUserId" id="addUserDetId" />
--><!-- **************************************** Part A of the form starts here *********************************************** -->

<div class="rowExpand" onclick="expandColl('parta');"> Change Horse Owner/Rider/ Name Form <span class="textOne"></span></div>
<div id="parta" class="formPart">

<div class="rowHead">
	<strong>Horse Information Section:</strong>
</div>
			<%
			 		String horseName =(String)request.getAttribute("horseName");
                    String regName = (String)request.getAttribute("regName");
                    String baRegName = (String)request.getAttribute("baRegName");
                    String baPastName = (String)request.getAttribute("baPastName");
					//out.print("horseName:" + horseName);
					//out.print("regName:" + regName);
					//out.print("baRegName:" + baRegName);
					//out.print("baPastName:" + baPastName);
			%>
			
			<div class="row">
				<span class="label">Horse Name:</span>
				 
				<span class="formX"> <%=horseName%></span>
				 
				<span class="formX">
				 <input type="text" name="horseName" id="horseName" class="textboxOne" size="20" value="<%=horseName%>"/> </span>
				 (Competition Name)
				  <%}else{%>
				   <input type="text" name="horseName" class="textboxOne" size="20" value="" /> <%}%>
				 <span class="asterisk">*</span> </span>			</div>
			<div class="row">
				<span class="label">Registered Name:</span>
				<span class="formX">
				<%if(regName!=null && regName.trim().length()!=0){%>
				<input type="text" name="registeredName" id="registeredName" class="textboxOne" size="20" value="<%=regName%>" /> 
				(If different from above)
				 <%}else{%> 
				 <input type="text" name="registeredName" class="textboxOne" size="20" value=""/> <%}%>
				</span>			</div>
			<div class="row">
				<span class="label">Past Competition Name:</span>
				<span class="formX">
				<%if(baPastName!=null && baPastName.trim().length()!=0){%>
				<input type="text" name="pastName" id="pastName" class="textboxOne" size="20" value="<%=baPastName%>"/> 
				 <%}else{%> 
				 <input type="text" name="pastName" class="textboxOne" size="20" /> 
				 <%}%>
				</span>			</div>
			<div class="row">
				<span class="label">Breed Assoc. Horse is registered with:</span>
				<span class="formX">
				<%if(baRegName!=null && baRegName.trim().length()!=0){%>
				<input type="text" name="breedAssoc" id="breedAssoc" class="textboxOne" size="20" value="<%=baRegName%>"/> 
				 <%}else{%> 
				 <input type="text" name="breedAssoc" class="textboxOne" size="20" /> 
				 <%}%>
				</span>			</div>
			
			
	        <div class="rowHead">
		This form is for Horse Registration:	</div>
	<% 
                                     
			Vector horsememberVect=new Vector();
                   
			//horsememberVect=(Vector)session.getAttribute("horsememberVect");
                        horsememberVect=(Vector)request.getAttribute("horsememberVect");
			Enumeration itrators= (Enumeration)horsememberVect.elements();
                        int siz=horsememberVect.size();
                        %>
			<div class="row">
                            
                             
                            
				<span class="label">Horse Registration Type:</span>
				<span class="formX">
				<select name="feeDisp" id="horseSelect" class="selectboxOne" style="width:275px;" onChange="Dispall();" >
				  <option selected="selected" value="" >Select One</option>  
                                            
                     <% 
                     if(siz!=0)
                     {
                     while(itrators.hasMoreElements()){
                                
                                String[] sarray = (String[]) itrators.nextElement();
                                
                                String memberTypeId = sarray[0];
                                String membershipName = sarray[1];
                                String membershipAmount = sarray[2];
                                String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
                        %>
                                    
				   <option  value="<%=cnct%>"><%=membershipName%></option>
			<%}}%>	  <!-- end loop  -->
				</select>
			    <span class="asterisk">*</span> </span>			</div>
			<div class="row">
				<span class="label">Registration Amount:</span>
				<span class="formX">
					<strong>$</strong>&nbsp;
					 
					<input id="fee_txt" class="readOnly" type="text" name="fee_txt" size="10" readonly="true" value=""/>
					
				</span>			</div>
			
		 
			
			<!-- spacer starts-->
			<div class="spacer">&nbsp;</div>
			<!-- spacer ends-->
			
			


			<div class="rowHead">
				<strong>Rider Information Section:</strong>			</div>
			<div class="row">
				<span class="label">No.:</span>
				<span class="formX">
				 <input type="text" class="textboxOne" name="hlcNo" id="riderUseaNo_id" onblur="rCheck();resetRiderOwner();" size="20" />
				 <span class="asterisk">*</span>				 </span></div>
			<div class="row">
				<span class="label">Salutation:</span>
				<span class="formX">
				<input type="text" name="rprefix" id="rprefix" readonly="true" class="readOnly" size="10" />
				</span>			</div>
			<div class="row">
				<span class="label">First Name:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true"  name="riderFname_id" id="riderFname_id" size="20" /></span>			</div>
				<div class="row">
				<span class="label">Last Name:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true"  name="riderLname_id" id="riderLname_id" size="20" /></span>			</div>
			<div class="row">
				<span class="label">Street:</span>
				<span class="formX"><input type="text"  class="readOnly"  readonly="true" name="rStreet" id="rStreet" size="20" /></span>			</div>
			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true" name="rCity" id="rCity" size="15" /></span>			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true" name="rState" id="rState" size="20" /></span>			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true" name="rZipcode" id="rZipcode" size="8" /></span>			</div>
				<div class="row">
				<span class="label">Phone:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true" name="riderPhone_id" id="riderPhone_id" size="15" /></span>			</div>
			<div class="row">
				<span class="label">Cell:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true" name="rCell" id="rCell" size="15" /></span>			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX"><input type="text" class="readOnly" readonly="true" name="rEmail" id="rEmail" size="20" /></span>			</div>
			
<!-- **************************************** Part B of the form starts here *********************************************** -->

<div class="rowExpand" onclick="expandColl('partb');"> Change Horse Owner/Rider/ Name Form</div>
<div id="partb" class="formPart">
	<div class="rowHead">
				<strong>Owner Information Section:</strong>	</div>
	<div>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
				<tr>
					<td class="tableLeftTxtArea">Choose one option that apply: </td>
					<td class="tableRight">
					<input type="radio" size="10" name="regFor" id="rself" value="mem1" onClick="copyRiderOwner(), showHideRadio('regFor','ridMemb','mem1'), showHideRadio('regFor','accUser','mem1'), showHideRadio('regFor','newwUser','mem1'), showHideRadio('regFor','companyUser','mem1'), showHideRadio('regFor','existComp','mem1'), showHideRadio('regFor','compQuest','mem1'), clearHLCdetails(); clearNotHLCdetails(); clearNewdetails(); comRegClear(); comNewClear(); comRadioClear(); clearFields();" />
					The owner is the rider. <br />
					<input type="radio" size="10" name="regFor" id="rself" value="rid1" onClick="switchDiv('ridMemb'), showHideRadio('regFor','accUser','rid1'), showHideRadio('regFor','newwUser','rid1'), showHideRadio('regFor','companyUser','rid1'), showHideRadio('regFor','ridUser','rid1'), showHideRadio('regFor','existComp','rid1'), showHideRadio('regFor','companyUser','rid1'), showHideRadio('regFor','compQuest','rid1'); clearRiderdetails(); clearNotHLCdetails(); clearNewdetails(); comRegClear(); comNewClear(); comRadioClear();" />
					Owner is Member. <br />
					<input type="radio" size="10" name="regFor" id="rself" value="acc1" onClick="switchDiv('accUser'), showHideRadio('regFor','newwUser','acc1'), showHideRadio('regFor','companyUser','acc1'), showHideRadio('regFor','ridUser','acc1'), showHideRadio('regFor','ridMemb','acc1'), showHideRadio('regFor','existComp','acc1'), showHideRadio('regFor','companyUser','acc1'), showHideRadio('regFor','compQuest','acc1'), clearRiderdetails(); clearHLCdetails(); clearNewdetails(); comRegClear(); comNewClear(); comRadioClear();" />
					The owner has a login account.  <br />
					<input type="radio" size="10" name="regFor" id="rself" value="new1" onClick="switchDiv('newwUser'), showHideRadio('regFor','companyUser','new1'), showHideRadio('regFor','ridUser','new1'), showHideRadio('regFor','ridMemb','new1'), showHideRadio('regFor','accUser','new1'), showHideRadio('regFor','existComp','new1'), showHideRadio('regFor','companyUser','new1'), showHideRadio('regFor','compQuest','new1'), clearRiderdetails(); clearHLCdetails(); clearNotHLCdetails(); comRegClear(); comNewClear(); comRadioClear();" />
					The owner is a new user. <br />								
					<input type="radio" size="10" name="regFor" id="rself" value="cmp1" onClick="switchDiv('compQuest'), showHideRadio('regFor','ridUser','cmp1'), showHideRadio('regFor','ridMemb','cmp1'),  showHideRadio('regFor','accUser','cmp1'), showHideRadio('regFor','newwUser','cmp1'), clearRiderdetails(); clearHLCdetails(); clearNotHLCdetails(); clearNewdetails();" />
					The owner is a Company.
					</td>
				  </tr>
					<!--
					<tr> 
					  <td colspan="2" height="15" class="styleError">Sorry, the option you have selected does not match your profile.</td>
					</tr>
					-->
				  <tr id="compQuest">
					<td class="tableLeft">Is the company already registered </span>
					<td class="tableRight">
						<input type="radio" size="10" name="ecmp" id="ecmp" value="yes" onClick="switchDiv('existComp'); showHideRadio('ecmp','companyUser','yes'); comNewClear();"> Yes
						<input type="radio" size="10" name="ecmp" id="ecmp" value="no"  onClick="switchDiv('companyUser'); showHideRadio('ecmp','existComp','no'); comRegClear();"> No				
					</td>
				  </tr>
				  <tr>
					<td colspan="2" id="ridUser">
						  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr> 
							  <td colspan="2" class="tblMainHead">Owner Information - Rider </td>
							</tr>
							<tr>
							  <td class="tableLeft">Member ID:</td>
							  <th class="tableRight"><input name="userNameOne" id="ownerUseaNo_id" class="readOnly" readonly="readonly" size="20" /></th>
							</tr>
							<tr> 
								<td class="tableLeft">First Name:</td>
								<th class="tableRight"><input name="firstNameOne" id="ownerFname_id" class="readOnly" readonly="readonly"  size="20" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Last Name:</td>
								<th class="tableRight"><input name="lastNameOne" id="ownerLname_id" class="readOnly" readonly="readonly" size="20" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Phone:</td>
								<th class="tableRight"><input name="phoneOne" id="ownerPhone_id" class="readOnly" readonly="readonly" size="20" /></th>
							</tr>
						 </table>
					</td>
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
							  <th class="tableRight"><input name="existCompNameId1" id="existCompNameId1" class="textboxOne" size="20" onblur="nonUserDetails1(this);" /> <span class="asterisk">*</span> </th>
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
						<td colspan="2" id="newwUser">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								  
								  <tr> 
								  <td colspan="2" class="tblMainHead">Owner Information - New User </td>
								</tr>
								  <tr>
									<td class="tableLeft"> First Name:</td>
									<td class="tableRight"><input name="newFirstNameId" id="newFirstNameId" class="textboxOne" size="20" />
									<span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Last Name:</td>
									<td class="tableRight"><input name="newLastNameId" id="newLastNameId" class="textboxOne" size="20" />
									<span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Date of Birth:</td>
									<td class="tableRight">
										<select name="newBirthmonthId" id="newBirthmonthId" class="selectboxOne">
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
									  <select name="newBirthdayId" id="newBirthdayId" class="selectboxOne">
											  <option value="" selected="selected">Day</option>
											  <option value="01">01</option>
											  <option value="02">02</option>
											  <option value="03">03</option>
											  <option value="04">04</option>
											  <option value="05">05</option>
											  <option value="06">06</option>
											  <option value="07">07</option>

											  <option value="08">08</option>
											  <option value="09">09</option>
											  <option value="10">10</option>
											  <option value="11">11</option>
											  <option value="12">12</option>
											  <option value="13">13</option>
											  <option value="14">14</option>
											  <option value="15">15</option>
											  <option value="16">16</option>
											  <option value="17">17</option>
											  <option value="18">18</option>
											  <option value="19">19</option>
											  <option value="20">20</option>
											  <option value="21">21</option>
											  <option value="22">22</option>
											  <option value="23">23</option>
											  <option value="24">24</option>
											  <option value="25">25</option>
											  <option value="26">26</option>
											  <option value="27">27</option>
											  <option value="28">28</option>
											  <option value="29">29</option>
											  <option value="30">30</option>
											  <option value="31">31</option>
									  </select>
										<select name="newBirthyearId" id="newBirthyearId" class="selectboxOne">
											  <option value="" selected="selected" >Year</option>
												<%
												for(int i=1900;i<=2015;i++)
												{%>
												  <option  value="<%=i%>"><%=i%></option>
																									
												<%}%>
										</select>
									<span class="asterisk">*</span>												</td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Gender</td>
									<td colspan="-1" class="tableRight">
										<input type="radio" size="10" name="sexId" id="sexId" value="male"> Male
										<input type="radio" size="10" name="sexId" id="sexId" value="female"> Female
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Address</td>
									<td colspan="-1" class="tableRight">
										<input name="newAddressId" id="newAddressId" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> City</td>
									<td colspan="-1" class="tableRight">
										<input name="newCityId" id="newCityId" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								   <tr> 
									<td class="tableLeft"> State</td>
									<td colspan="-1" class="tableRight"> <span class="row"><span class="formX"> 
									 <SELECT name="newStateId" id="newStateId" class="selectboxOne" style="width:200" width="200" ></SELECT>
									  <span class="asterisk">*</span> </span></span></td>
								  </tr>
								   <tr> 
									<td class="tableLeft"> Zip Code</td>
									<td colspan="-1" class="tableRight">
										<input name="newZipId" id="newZipId" class="textboxOne" size="20" />
										<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Country</td>
									<td colspan="-1" class="tableRight">
									<SELECT name="newCountryId" id="newCountryId" class="selectboxOne"  width="200" 	onChange="FillState(document.myform.newCountryId, document.myform.newStateId, '--Select One--');">
									</SELECT>
									<span class="asterisk">*</span> </td>
								  </tr>								 
								   <tr> 
									<td class="tableLeft">EMail-ID</td>
									<td colspan="-1" class="tableRight">
									   <input name="newEmailId" id="newEmailId" class="textboxOne" size="20" />
									   <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Phone</td>
									<td colspan="-1" class="tableRight">
									   <input name="newPhoneId" id="newPhoneId" class="textboxOne" size="20" />
									   <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Fax</td>
									<td colspan="-1" class="tableRight">
									   <input name="newFaxId" id="newFaxId" class="textboxOne" size="20" /></td>
								  </tr>
						  </table>									</td>
				  </tr>
				  <tr>
						<td colspan="2" id="companyUser">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
								  
								  <tr> 
								  <td colspan="2" class="tblMainHead">Owner Information - New Company </td>
								</tr>
								  <tr>
									<td class="tableLeft"> Company Name:</td>
									<td class="tableRight"><input name="newFirstNameIdComp" id="newFirstNameIdComp" class="textboxOne" size="20" />
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
									   <input name="cpFirstNameIdComp" id="cpFirstNameIdComp" class="textboxOne" size="20" />
									   <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Contact Person Last Name</td>
									<td colspan="-1" class="tableRight">
									   <input name="cpLastNameIdComp" id="cpLastNameIdComp" class="textboxOne" size="20" />
									   <span class="asterisk">*</span></td>
								  </tr>
						  </table>	
						</td>
				  </tr>
		</table>
	</div>
	
			 
			 



	<div class="rowHead">
		Horse Description:	</div>
 
 
			<div class="row">
				<span class="label">Color:</span>
				
				<span class="formX">
				<select name="colorselect" id="colorSelectId" class="selectboxOne">
				  <option selected="selected" value="">Select One</option>
						<%
						ArrayList lst = (ArrayList) request.getAttribute("DisplayColorDetails");
						if(lst.size()!=0){
							Iterator itr = lst.iterator();
							while(itr.hasNext()){
									HLCHorseColorVO objColorVO = (HLCHorseColorVO)itr.next();
									String colorId = objColorVO.getColorId();
									String colorCode = objColorVO.getColorCode();
									String colorDesc = objColorVO.getColorDesc();
						%>
						<option value="<%=colorId%>"><%=colorDesc%></option>
						<%
							}
						}
						%>  
			    </select>
				<span class="asterisk">*</span>				</span> </div>
			<div class="row">
				<span class="label">Sex:</span>
				<span class="formX">
				<select name="genderselect" id="genderSelectId" class="selectboxOne">
				  <option selected="selected" value="">Select One</option>
				  <option value="Gelding">Gelding</option>
				  <option value="Stallion">Stallion</option>
				  <option value="Mare">Mare</option>
			    </select>
				<span class="asterisk">*</span>				</span>			</div>
			<div class="row">
				<span class="label">Height</span>
				<span class="formX"><input type="text" class="textboxOne" name="height" id="height" size="10" />
				</span>			</div>
			<div class="row">
				<span class="label">Year foaled:</span>
				<span class="formX"><input type="text" class="textboxOne" name="yearfoaled" id="yearfoaled" size="10" />
				(yyyy)</span></div>
			<div class="row">
				<span class="label">Primary Breed:</span>
				<span class="formX">
				<select name="breed" id="breed" class="selectboxOne">
				  <option selected="selected" value="">Select One</option>
				  <%
						ArrayList breed = (ArrayList) request.getAttribute("DisplayBreedDetails");
						if(breed.size()!=0){
							Iterator itr = breed.iterator();
							while(itr.hasNext()){
									HLCBreedVO objBreedVO = (HLCBreedVO) itr.next();
									String breedId = objBreedVO.getBreedId();
									String breedCode = objBreedVO.getBreedCode();
									String breedDesc =  objBreedVO.getBreedDesc();
						%> 
                    
                   
						<option value="<%=breedId%>"><%=breedDesc%></option>
						<%
							}
						}
						%>  
			    </select></span>			</div>
			<div class="row">
				<span class="label">Secondary/Cross Breed:</span>
				<span class="formX">
				<select name="breedTwo" id="breedTwo" class="selectboxOne">
				  <option selected="selected" value="">None</option>
				    <%
						ArrayList breed1 = (ArrayList) request.getAttribute("DisplayBreedDetails");
						if(breed1.size()!=0){
							Iterator itr1 = breed1.iterator();
							while(itr1.hasNext()){
									HLCBreedVO objBreedVO1 = (HLCBreedVO) itr1.next();
									String breedId1 = objBreedVO1.getBreedId();
									String breedCode1 = objBreedVO1.getBreedCode();
									String breedDesc1 =  objBreedVO1.getBreedDesc();
						%> 
                    
                   
						<option value="<%=breedId1%>"><%=breedDesc1%></option>
						<%
							}
						}
						%>  
			    </select>
				 </span>			</div>
			<div class="row">
				<span class="label">Country of origin:</span>
				<span class="formX"><input type="text" class="textboxOne" name="countryOrigin" id="countryOrigin" size="15" /> (If not USA)</span>			</div>

			<div class="row">
				<span class="label">Sire Name:</span>
				<span class="formX"><input type="text" class="textboxOne" name="sireName" id="sireName" size="15" /></span>			</div>
			<div class="row">
				<span class="label">Sire Primary Breed:</span>
				<span class="formX">
				<select name="sireBreed" id="sireBreed" class="selectboxOne">
				  <option selected="selected" value="">Select One</option>
				  <%
						ArrayList breed3 = (ArrayList) request.getAttribute("DisplayBreedDetails");
						if(breed3.size()!=0){
							Iterator itr3 = breed3.iterator();
							while(itr3.hasNext()){
									HLCBreedVO objBreedVO3 = (HLCBreedVO) itr3.next();
									String breedId3 = objBreedVO3.getBreedId();
									String breedCode3 = objBreedVO3.getBreedCode();
									String breedDesc3 =  objBreedVO3.getBreedDesc();
						%> 
                    
                   
						<option value="<%=breedId3%>"><%=breedDesc3%></option>
						<%
							}
						}
						%>  
			    </select></span>			</div>
			<div class="row">
				<span class="label">Sire Secondary/Cross Breed:</span>
				<span class="formX">
				<select name="sireBreedTwo" id="sireBreedTwo" class="selectboxOne">
				  <option selected="selected" value="">None</option>
				  <%
						ArrayList breed2 = (ArrayList) request.getAttribute("DisplayBreedDetails");
						if(breed2.size()!=0){
							Iterator itr2 = breed2.iterator();
							while(itr2.hasNext()){
									HLCBreedVO objBreedVO2 = (HLCBreedVO) itr2.next();
									String breedId2 = objBreedVO2.getBreedId();
									String breedCode2 = objBreedVO2.getBreedCode();
									String breedDesc2 =  objBreedVO2.getBreedDesc();
						%> 
                    
                   
						<option value="<%=breedId2%>"><%=breedDesc2%></option>
						<%
							}
						}
						%>  
			    </select></span>			</div>
			<div class="row">
				<span class="label">Dam Name:</span>
				<span class="formX"><input type="text" class="textboxOne" name=damName id="damName" size="15" /></span>			</div>
			<div class="row">
				<span class="label">Dam Primary Breed:</span>
				<span class="formX">
				<select name="damBreed" id="damBreed" class="selectboxOne">
				  <option selected="selected" value="">Select One</option>
				   <%
						ArrayList breed4 = (ArrayList) request.getAttribute("DisplayBreedDetails");
						if(breed4.size()!=0){
							Iterator itr4 = breed4.iterator();
							while(itr4.hasNext()){
									HLCBreedVO objBreedVO4 = (HLCBreedVO) itr4.next();
									String breedId4 = objBreedVO4.getBreedId();

									String breedCode4 = objBreedVO4.getBreedCode();
									String breedDesc4 =  objBreedVO4.getBreedDesc();
						%> 
                    
                   
						<option value="<%=breedId4%>"><%=breedDesc4%></option>
						<%
							}
						}
						%>  
			    </select></span>			</div>
			<div class="row">
				<span class="label">Dam Secondary/Cross Breed:</span>
				<span class="formX">
				<select name="damBreedTwo" id="damBreedTwo" class="selectboxOne">
				  <option selected="selected" value="">None</option>
				  <%
						ArrayList breed5 = (ArrayList) request.getAttribute("DisplayBreedDetails");
						if(breed5.size()!=0){
							Iterator itr5 = breed5.iterator();
							while(itr5.hasNext()){
									HLCBreedVO objBreedVO5 = (HLCBreedVO) itr5.next();
									String breedId5 = objBreedVO5.getBreedId();
									String breedCode5 = objBreedVO5.getBreedCode();
									String breedDesc5 =  objBreedVO5.getBreedDesc();
						%> 
                    
                   
						<option value="<%=breedId5%>"><%=breedDesc5%></option>
						<%
							}
						}
						%>  
			    </select></span>			</div>
			<div class="row">

				<span class="label">Imported From:</span>
				<span class="formX"><input type="text" class="textboxOne" name=importedFrom id="importedFrom" size="25" /></span>			</div>
			<div class="row">
				<span class="label">Date Imported:</span>
				<span class="formX"><input type="text" class="textboxOne" name=dateImported id="dateImported" size="15" readonly /><a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a></span>			</div>
		<!--	<div class="row">
				<span class="label">Foreign Grade:</span>
				<span class="formX"><input type="text" class="textboxOne" name=foreignGrade id="foreignGrade" size="12" /></span>			</div>
			<div class="row">
				<span class="label">Foreign Points:</span>
				<span class="formX"><input type="text" class="textboxOne" name=foreignPoints id="foreignPoints" size="10" /></span>			</div>-->
		<!--	<div class="row">
				<span class="label">Assigned Grade:</span>
				<span class="formX"><input type="text" class="textboxOne" name=assignedGrade id="assignedGrade" size="12" /></span>			</div>
			<div class="row">
				<span class="label">Assigned Points:</span>
				<span class="formX"><input type="text" class="textboxOne" name=assignedPoints id="assignedPoints" size="10" /></span>			</div>
			<div class="row">
				<span class="label">Notes:</span>
				<span class="formX">
					<textarea  name="notes" id="notes" rows="5" cols="25"></textarea>
				</span>			</div>
			<div class="row">
				<span class="label">Special Notes:</span>
				<span class="formX">
					<textarea name="specialNotes" id="specialNotes" rows="5" cols="25"></textarea>
				</span>			</div>-->
<!--
			<div class="colspan">
				<span class="floatLeftRadio">
				  <strong>&nbsp;&nbsp;&nbsp;&nbsp;Miscellaneous Services </strong>
			   </span>
				<span class="floatRight"><strong>Fees</strong>&nbsp;&nbsp;&nbsp;</span>
			</div>

                        
                                                 
                        <%/*   
                                int chksfx=0;
                                String cbx="classification";
                                Vector horsesertypVect = new Vector();
                              //  horsesertypVect =(Vector)session.getAttribute("horsesertypVect");
                                horsesertypVect =(Vector)request.getAttribute("horsesertypVect");
                                 int si=horsesertypVect.size();
                        %>
                        
                       			 <input type="hidden" name="cbxct" value="<%=si%>">
                                 <%
                                    if(si!=0)
                                    {
                                 Enumeration itrate= (Enumeration)horsesertypVect.elements();
                                 while(itrate.hasMoreElements()){
                            
                                String[] sarray1 = (String[]) itrate.nextElement();
                                String memberTypeId1 = sarray1[0];
                                String membershipName1 = sarray1[1];
                                String membershipAmount1 = sarray1[2];
                                String cn=memberTypeId1+"#"+membershipName1+"#"+membershipAmount1;
                                
                                String cbxname=cbx+Integer.toString(chksfx);
                               
                         %>
                        
			<div class="row">
			  <span class="floatLeft"><input type="checkbox" name="<%=cbxname%>" value="<%=cn%>" size="10" onClick="Dispall();"> </span>
			  <span class="floatLeftRadio">
				  <strong><%=membershipName1%></strong>
			  </span>
			  <span class="floatRight"><strong><%=membershipAmount1%></strong>&nbsp;&nbsp;</span>
            </div>
                         
                         <%chksfx++;%>
                         
                          <%}} */%>
                          

			<div class="rowHghtOne" id="horseServ">
				<span class="floatLeft"><strong>Imported Horses:</strong>  In order for us to record your foreign points and determine your horse's grade, please provide the HLC with a copy of your
					horse's past performance records when registering your horse.
				</span>
			</div>
	-->
	<%
	String userName =(String)request.getAttribute("userName");
	System.out.print("userName:"+ userName);
	   if(userName!=null && userName.equalsIgnoreCase("HLC Staff")){
	
	%>
	 <div id="phoneReg">
		<div class="rowHead">
		Phone Registration Information:		</div>	
		<%  
                                int chksfx=0;
                                String cbx="classification";
                                Vector horsesertypVect = new Vector();
                              // horsesertypVect =(Vector)session.getAttribute("horsesertypVect");
                                 horsesertypVect =(Vector)request.getAttribute("horsesertypVect");
                                 int si=horsesertypVect.size();
                        %>
                        
                       			 <input type="hidden" name="cbxct" value="<%=si%>">
                                 <%
                                    if(si!=0)
                                    {
                                 Enumeration itrate= (Enumeration)horsesertypVect.elements();
                                 while(itrate.hasMoreElements()){
                            
                                String[] sarray1 = (String[]) itrate.nextElement();
                                String memberTypeId1 = sarray1[0];
                                String membershipName1 = sarray1[1];
                                String membershipAmount1 = sarray1[2];
                                String cn=memberTypeId1+"#"+membershipName1+"#"+membershipAmount1;
                                String cbxname=cbx+Integer.toString(chksfx);
                               
                         %>
                        
			<div class="row">
			  <span class="floatLeft"><input type="checkbox" name="<%=cbxname%>" value="<%=cn%>" size="10" onClick="Dispall();"> </span>
			  <span class="floatLeftRadio">
				  <strong><%=membershipName1%></strong>			  </span>
			  <span class="floatRight"><strong><%=membershipAmount1%></strong>&nbsp;&nbsp;</span>            </div>
                         
                         <%chksfx++;%>
                         
                          <%}}   %>
		    
				<!--<div class="row">
						<span class="label">Registration Charges:</span>
						<span class="formX">
							<strong>$</strong><input type="text" name="phoneCharge" id="PhoneCharge_id" class="textboxOne" size="10" />
						</span>
				</div>	-->	
	</div>
	<input type="hidden" name="status" value="yes">
		<%  }else{%>
		 <input type="hidden" name="status" value="no">	
		<% }%>
		
		
	    <div class="rowHead">
		Payment Information:	</div>
		<div>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">			 
				  <tr>
					<td class="tableLeft"><strong>Total Amount:</strong></td>
					<td class="tableRight"> <strong>$</strong> 
					<input type="hidden" name="tempAmount" readonly="true" class="readOnly" size="10" value="" />
					 
					<input type="text" name="totalAmount" readonly="true" class="readOnly" size="10" value="" />&nbsp; U.S. Dollars	 </td>				 
				  </tr>
					<tr>
					<td class="tableLeft">
						<input type="radio" size="10" name="r11" id="r11" value="check"  onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'), checkClear();cardClear();"/> Check enclosed.				 	</td>
					<td class="tableRight">
						<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'), showHideRadio('r11','chkEncAcm','card'), cardClear();checkClear();"/> Please charge my card.					</td>
				  </tr>
				  <tr id="chkEncAcm">
					<td colspan="2">

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
								Horse  Registration
								<br />
								525 Old Waterford Road NW
								<br />
								Leesburg, VA 20176												    </strong><br />
								<br />
								<strong>Note:</strong><span class="styleBoldOne"> Your registration status will be pending until check is processed.		</span> <br />
						<br /></td>
						  </tr>
							<tr>
							  <td class="tableLeft">Check No:</td>
							  <td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
							<tr>
							  <td class="tableLeft">Check Date:</td>
							  <td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" readonly="yes"size="16" />

							   <a href="javascript:cal2.popup();">
				   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
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
						</table>					</td>
				  </tr>
				  <tr id="chrgCrdAcm">
					<td colspan="2">

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
									  <option value="Master Card">Master Card</option>

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
							<tr>
								<td class="tableLeft">Card CVV No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
								  <span class="asterisk">*</span></td>
							</tr>
						
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
									</select>
									<span class="asterisk">*</span>																         </td>
						  </tr>
					 </table>								</td>
			</tr>
			<tr>
			  <td colspan="2" class="alignCenter">
					<input type="submit" value="Submit Payment" class="gradBtn" />&nbsp;			  </td>
			</tr>
		</table>
		</div>
			
		
	<div id="spacer">&nbsp;</div>
</div>
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
	var cal1 = new calendar2(document.forms['myform'].elements['dateImported']);
	 cal1.year_scroll = true;
	 cal1.time_comp = false;
  
	var cal2 = new calendar2(document.forms['myform'].elements['checkDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
	
	FillCountry(document.myform.newCountryId, document.myform.newStateId, 'USA');
	FillState(document.myform.newCountryId, document.myform.newStateId, '---Select---');
	

	FillCountry(document.myform.newCountryIdComp, document.myform.newStateIdComp, 'USA');
	FillState(document.myform.newCountryIdComp, document.myform.newStateIdComp, '---Select---');
</script>
</html>