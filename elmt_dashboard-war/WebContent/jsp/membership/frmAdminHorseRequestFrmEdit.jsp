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
    <script src="javascripts/frmHorseReg.js" type="text/javascript" ></script>
    <script src="javascripts/cscombo_new.js" type="text/javascript"></script>
    <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<script language="javascript">
function rCheck(){
	var a = document.myform.hlcNo.value;
     if(a!="" && a.indexOf(' ')!=0){
		  riderDetails();
   }
     else{
	   	  clearRider();
     }
}
var arHttpRequest;
var req;
     function riderDetails(){
		 param = document.myform.hlcNo.value;
			var  url = null;
			if(param.length==0)
			return;
			url = "annualAjax.do?method=memberDetail&memberId="+escape(param);
			if (window.ActiveXObject){ 
				req = new ActiveXObject("Microsoft.XMLHTTP"); 
			} 
			else if (window.XMLHttpRequest){ 
				req = new XMLHttpRequest(); 
			} 
			req.onreadystatechange = processRider;         
			req.open("GET", url, true);
			req.send(null);  
} 
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
         function processRider(){ 
    			if (req.readyState == 4){ 
						   // alert(req.status);
							if(req.status == 200){ 
							//  alert(req.status);
								 var xmlDoc = req.responseXML.documentElement;
								  //  var userNoId =xmlDoc.getElementsByTagName('userNo')[0].childNodes[0].nodeValue;
									var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
									var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
 									var prefix =xmlDoc.getElementsByTagName('prefix')[0].childNodes[0].nodeValue; 
									var emailId =xmlDoc.getElementsByTagName('emailId')[0].childNodes[0].nodeValue; 
									var address1 =xmlDoc.getElementsByTagName('address1')[0].childNodes[0].nodeValue; 
 									var city =xmlDoc.getElementsByTagName('city')[0].childNodes[0].nodeValue; 
									var state =xmlDoc.getElementsByTagName('state')[0].childNodes[0].nodeValue; 
									var country =xmlDoc.getElementsByTagName('country')[0].childNodes[0].nodeValue; 
									var zip =xmlDoc.getElementsByTagName('zip')[0].childNodes[0].nodeValue; 
									var mobile =xmlDoc.getElementsByTagName('mobile')[0].childNodes[0].nodeValue; 
									
								 
									var firstNameObj = document.getElementById("riderFname_id");
									var lastNameObj = document.getElementById("riderLname_id");
									var phoneObj = document.getElementById("riderPhone_id"); 
									var prefixObj = document.getElementById("rprefix");  
									var emailIdObj = document.getElementById("rEmail");  
									var address1Obj = document.getElementById("rStreet");  
									var cityObj = document.getElementById("rCity");  
									var stateObj = document.getElementById("rState");  
									var zipObj = document.getElementById("rZipcode");  
									var mobileObj = document.getElementById("rCell");  
									
									firstNameObj.value =  firstName;
									lastNameObj.value = lastName ;
									phoneObj.value = phone ; 
									prefixObj.value =  prefix;  
									emailIdObj.value = emailId ;  
									address1Obj.value =  address1;  
									cityObj.value = city ;  
									stateObj.value = state ;  
									zipObj.value = zip ;  
									mobileObj.value = mobile ;  
							} 
											if(req.status==500) {
												clearRider();
												alert("Membership Id is not valid");
												document.myform.riderUseaNo_id.focus();
											}
											else{ 
												
											} 
						}	
						
					} 
function clearRider(){
				
		document.getElementById("riderUseaNo_id").value = "";
		document.getElementById("riderFname_id").value = "";
		document.getElementById("riderLname_id").value = "";
		document.getElementById("riderPhone_id").value = "";
		document.getElementById("rprefix").value = "";
		document.getElementById("rEmail").value = ""; 
		document.getElementById("rStreet").value = "";
		document.getElementById("rCity").value = "";
		document.getElementById("rState").value = ""; 
		document.getElementById("rZipcode").value = "";  
		document.getElementById("rCell").value = ""; 
}
 
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
									var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
									var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
									var dob =xmlDoc.getElementsByTagName('dob')[0].childNodes[0].nodeValue; 
									var age =xmlDoc.getElementsByTagName('age')[0].childNodes[0].nodeValue; 
									var firstNameObj = document.getElementById("ownerFname2_id");
									var lastNameObj = document.getElementById("ownerLname2_id");
									var phoneObj = document.getElementById("ownerPhone2_id"); 
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
											  clearFields();
											  document.myform.ownerUseaNo2_id.focus();
											  return;
											}
											else{ 
												
											} 
						}	
						
					} 
		 
 function clearFields() {
		document.getElementById("ownerUseaNo2_id").value = "";
		document.getElementById("ownerFname2_id").value = "";
		document.getElementById("ownerLname2_id").value = "";
 		document.getElementById("ownerPhone2_id").value = "";
		document.getElementById("addUserDetId").value = "";
		 
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
									
							} 
							if(req.status==500) {
							  alert("userName does'nt exists");
							  clear();
							  document.myform.userNameId3.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
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
							  alert("Company Name does'nt exists");
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
						document.getElementById("existAddCompNameId1").value = "";
						document.getElementById("cpAddfirstNameId1").value = "";
						document.getElementById("cpAddlastNameId1").value = "";
						document.getElementById("ecmpAddPhoneId1").value = "";
						document.getElementById("addUserDetId").value = "";
					}					
					
</script>
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
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    String  nullCheck(String fieldName){
        String retValue = "NG";
        if(fieldName!=null && fieldName.trim().length()!=0){
            retValue = fieldName;
        }
        return retValue;
    }
    
    %>
    <%! String dateCheck(Date fieldName){
        String detValue = "NG";
        if(fieldName!=null){
            detValue = sdf.format(fieldName);
        }
        return detValue;
    }
    
    %>
	<%!  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
        <%@ include file = "../../include/header.jsp"%>
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
        <!-- LEFT MENU ENDS HERE -->		    </td>
        <td width="500" class="subDeptTablePad">
        <!-- CONTENTS START HERE -->
		<form name="myform" method="post" action="AdminHorseReqEdit.do" onsubmit="return myValidate();">
        <div class="cmmnForm">
            <div class="colspan">
            <strong>Membership:</strong> <span class="styleBoldTwo">Horse Registration Details </span>	</div>
            <div id="commonBG" class="textCommon"> You are viewing	the	details	of the  registered horse. </div>
            <%
            HLCHorseRegisterationVO HorseDisp =(HLCHorseRegisterationVO) request.getAttribute("HorseDet");
            String horseMemberId = (String) HorseDisp.getHorseMemberId();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            
            String competitionName =(String) HorseDisp.getCompetitionName();
            String registeredName = (String) HorseDisp.getRegisteredName();
            String baRegisteredName = (String)HorseDisp.getBaRegisteredName();
            String baPastName = (String)HorseDisp.getBaPastName();
            String ownerId = (String)HorseDisp.getOwnerId();
            String riderMemberId = (String)HorseDisp.getRiderMemberId();
            String paymentId = (String)HorseDisp.getPaymentId();
            String requestStatus = (String)HorseDisp.getRequestStatus();
            String firstName = (String)HorseDisp.getFirstName();
            String lastName = (String)HorseDisp.getLastName();
            String comments =(String)HorseDisp.getComments();
            String color = (String)HorseDisp.getColor();
            String gender = (String)HorseDisp.getGender();
            String height = (String)HorseDisp.getHeight();
            String yearFoaled = (String)HorseDisp.getYearFoaled();
            String breed = (String)HorseDisp.getBreed();
            String country1 = (String)HorseDisp.getCountry();
            String sire = (String)HorseDisp.getSire();
            String sireBreed = (String)HorseDisp.getSireBreed();
            String dam = (String)HorseDisp.getDam();
            String damBreed = (String)HorseDisp.getDamBreed();
            String breed2 = (String)HorseDisp.getBreed2();
            String sireBreed2 = (String)HorseDisp.getSireBreed2();
            String damBreed2 = (String)HorseDisp.getDamBreed2();
            String importedFrom = (String)HorseDisp.getImportedFrom();
            String dstatusName = (String) HorseDisp.getStatusName();
            Date importDate = HorseDisp.getImportDate();
            Date actDate = HorseDisp.getActivationDate();
            Date upgraDate = HorseDisp.getUpgradationDate();
            String foreignGrade = (String)HorseDisp.getForeignGrade();
            float foreignPoints =(float)HorseDisp.getForeignPoints();
            String assignedGrade = (String)HorseDisp.getAssignedGrade();
            float assignedPoints = (float)HorseDisp.getAssignedPoints();
            String notes = (String)HorseDisp.getNotes();
            String splNotes = (String)HorseDisp.getSplNotes();
            String regstrBy = (String)HorseDisp.getRegisteredBy();
            String membertype=(String)HorseDisp.getMemberTypeName();
            String statusName = (String)HorseDisp.getStatusName();
            String regByuserId =(String)HorseDisp.getRegisterByUserId();
            %>
			<div class="row">
				<span class="label">Horse MemberId:</span>
				<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseMemberId)%></span></span>
			</div>
			
			<div class="row">
				<span class="label">Horse Registration Status:</span>
				<span class="formX"><span class="styleBoldOne"><%=nullCheck(statusName)%></span></span>
			</div>
			
			<div class="row">
				<span class="label">Horse Registration Type:</span>
				<span class="formX"><span class="styleBoldOne"><%=nullCheck(membertype)%></span></span>
			</div>
			<input type="hidden" name="membertype" value="<%=membertype%>">
			<div class="row">
				<span class="label">Activation Date :</span>
				<span class="formX"><span class="styleBoldOne"><%=dateCheck(actDate)%></span></span>	
			</div>
			
			<div class="row">
				<span class="label">Upgradation Date:</span>
				<span class="formX"><span class="styleBoldOne"><%=dateCheck(upgraDate)%></span></span>
			</div>
			<div class="rowHead">
			Horse Information Section:	
			</div>
			<div class="row">
				<span class="label">Horse Name: </span>
				<span class="formX"><strong><%=nullCheck(competitionName)%></strong></span>
			</div><h1></h1>
			<input type="hidden" name="hName" value="<%=competitionName%>" />
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
			<div class="rowHead">
			Horse Description:						</div>
			
			<div class="row">
				<span class="label">Color:</span>
				<span class="formX"><%=nullCheck(color)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Sex:</span>
				<span class="formX"><%=nullCheck(gender)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Height</span>
				<span class="formX"><%=nullCheck(height)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Year foaled:</span>
				<span class="formX"><%=nullCheck(yearFoaled)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Breed:</span>
				<span class="formX"><%=nullCheck(breed)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Breed Two:</span>
				<span class="formX"><%=nullCheck(breed2) %></span>		
			</div>	
			
			<div class="row">
				<span class="label">Country of origin:</span>
				<span class="formX"><%=nullCheck(country1) %></span>	
			</div>
			
			<div class="row">
				<span class="label">Sire Name:</span>
				<span class="formX"><%=nullCheck(sire) %></span>		
			</div>
			
			<div class="row">
				<span class="label">Sire Breed:</span>
				<span class="formX"><%=nullCheck(sireBreed)%></span>	
			</div> 
			
			<div class="row">
				<span class="label">Sire Breed Two:</span>
				<span class="formX"><%=nullCheck(sireBreed2) %></span>		
			</div>
			
			<div class="row">
				<span class="label">Dam Name:</span>
				<span class="formX"><%=nullCheck(dam)  %></span>	
			</div>
			
			<div class="row">
				<span class="label">Dam Breed:</span>
				<span class="formX"><%=nullCheck(damBreed) %></span>	
			</div>
			
			
			<div class="row">
				<span class="label">Dam Breed Two:</span>
				<span class="formX"><%=nullCheck(damBreed2)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Imported From:</span>
				<span class="formX"><%=nullCheck(importedFrom)  %></span>		
			</div>
			
			<div class="row">
				<span class="label">Date Imported:</span>
				<span class="formX"><%=dateCheck(importDate)%></span>		
			</div>
		
			<div class="row">
				<span class="label">Foreign Grade:</span>
				<span class="formX"><%=nullCheck(foreignGrade)%></span>	
			</div>
			
			<div class="row">
				<span class="label">Foreign Points:</span>
				<span class="formX"><%=foreignPoints %></span>		
			</div>
			
			<div class="row">
				<span class="label">Assigned Grade:</span>
				<span class="formX"><%=nullCheck(assignedGrade)%></span>	
			</div>
			<div class="row">
				<span class="label">Assigned Points:</span>
				<span class="formX"><%=assignedPoints%></span>								
			</div>
			<div class="row">
				<span class="label">Comments:</span>
				<span class="formX"><%=nullCheck(comments)%></span>	
			</div>
            <% 
            String payinfo[] =(String[])request.getAttribute("paydet");
            String checkNumber ="";
            String paymentStatus = "";
            String checkName ="";
            String sslTxnId = "";
            String bankName ="";
            String checkDate = "";
            String amount = "";
            String checkAmount ="";
            String tempPaymentId ="";
            String sslResult ="";
           
            String balAmount = "";
			 
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
                sslResult = payinfo[8];
                tempPaymentId = payinfo[9];
                balAmount = payinfo[10];
				cardName = payinfo[11];
				cardType = payinfo[12];
				cardStatus = payinfo[13];	
            }
            if(paymentStatus!=null && paymentStatus.equalsIgnoreCase("check")) {
            %>
            <div class="rowHead">
			Transaction Details:
			</div>
			<div class="row">
				<span class="label">Check Number:</span>
				<span class="formX"><%=nullCheck(checkNumber)%></span>	
			</div>
			<div class="row">
				<span class="label">Check Name:</span>
				<span class="formX"><%=nullCheck(checkName)%></span>
			</div>
			<div class="row">
				<span class="label">Amount:</span>
				<span class="formX"><%=amtFormat(amount)%></span>
			</div>
          
            <div class="row">
                <span class="label">Check Enclosed Amount:</span>
                <span class="formX"><%=amtFormat(checkAmount)%></span>
            </div>
           
            <div class="row">
                <span class="label">Pending Amount:</span>
                <span class="formX"><%=amtFormat(balAmount)%></span>
            </div>
			<%
				String chk_dte ="";
				if(checkDate!=null){
				chk_dte=checkDate.substring(0,10);	
				}
			%>
			<div class="row">
				<span class="label">Check Date:</span>
				<span class="formX"><%=dateFormat(chk_dte)%></span>	
			</div>
			<div class="row">
				<span class="label">Bank Name:</span>
				<span class="formX"><%=bankName%></span>	
			</div>
<%	
} 
else if(paymentStatus!=null && (paymentStatus.equalsIgnoreCase("credit card") || paymentStatus.equalsIgnoreCase("card"))) {  %>
			<div class="rowHead">
				Transaction Details:
			</div>
			<div class="row">
				<span class="label">Card Name:</span>
				<span class="formX"><%=cardName%></span>
			</div>
			<div class="row">
				<span class="label">Card Type:</span>
				<span class="formX"><%=cardType%></span>
			</div>
			<div class="row">
				<span class="label">Transaction Status:</span>
				<span class="formX"><%=cardStatus%></span>
			</div>
			<div class="row">
				<span class="label">Transaction Id:</span>
				<span class="formX"><%=nullCheck(sslTxnId)%>
			</span>
			</div>
			<div class="row">
				<span class="label">Amount:</span>
				<span class="formX"><%=amtFormat(amount)%></span>	
			</div>
<%
}	
            String reqpayinfo[] =(String[]) request.getAttribute("reqPaydet");
            String reqcheckNumber ="";
            String reqpaymentStatus = "";
            String reqcheckName ="";
            String reqsslTxnId = "";
            String reqbankName ="";
            String reqcheckDate = "";
            String reqamount = "";
            String reqcheckAmount = "";
            String reqtempPaymentId = "";
            String reqsslResult = "";
			String reqCardName  = ""; 
			String reqCardType  = "";
			String reqCardStatus =""; 

            String reqbalAmount = "";
            if(reqpayinfo!=null && reqpayinfo.length !=0){
                reqcheckNumber =  reqpayinfo[0];
                reqpaymentStatus =  reqpayinfo[1];
                reqcheckName =  reqpayinfo[2];
                reqsslTxnId = reqpayinfo[3];
                reqbankName =  reqpayinfo[4];
                reqcheckDate =  reqpayinfo[5];
                reqamount =  reqpayinfo[6];
                reqcheckAmount =  reqpayinfo[7];
                reqsslResult = reqpayinfo[8];
                reqtempPaymentId = reqpayinfo[9];
                reqbalAmount = reqpayinfo[10];
				reqCardName = reqpayinfo[11];
				reqCardType = reqpayinfo[12];
				reqCardStatus = reqpayinfo[13];	
            }
            if(reqpaymentStatus!=null && reqpaymentStatus.equalsIgnoreCase("check")) {
            %>
            <div class="rowHead">Request Transaction Details:</div>
			<div class="row">
				<span class="label">Check Name:</span>
				<span class="formX"><%=nullCheck(reqcheckName)%></span>
			</div>
            <div class="row">
                <span class="label">Check Number:</span>
                <span class="formX"><%=nullCheck(reqcheckNumber)%></span>
            </div>
            <div class="row">
                <span class="label">Amount:</span>
                <span class="formX"><%=amtFormat(reqamount)%></span>
            </div>
            <div class="row">
                <span class="label">Check Enclosed Amount:</span>
                <span class="formX"><%=amtFormat(reqcheckAmount)%></span>
            </div>
            <div class="row">
                <span class="label">Pending Amount:</span>
                <span class="formX"><%=amtFormat(reqbalAmount)%></span>
            </div>
			<% 
				String reqchk_dte ="";
				if(reqcheckDate!=null){
				reqchk_dte=reqcheckDate.substring(0,10);
				}
			%>
			<div class="row">
				<span class="label">Check Date:</span>
				<span class="formX"><%=dateFormat(reqchk_dte)%></span>
			</div>
			<div class="row">
				<span class="label">Bank Name:</span>
				<span class="formX"><%=reqbankName%></span>
			</div>
<%
}
else if(reqpaymentStatus!=null && (reqpaymentStatus.equalsIgnoreCase("card") || reqpaymentStatus.equalsIgnoreCase("credit card"))) {  %>
            <div class="rowHead">
				Request Transaction Details:
			</div>
			<div class="row">
				<span class="label">Card Name:</span>
				<span class="formX"><%=reqCardName%></span>
			</div>
			<div class="row">
				<span class="label">Card Type:</span>
				<span class="formX"><%=reqCardType%></span>
			</div>
			<div class="row">
				<span class="label">Transaction Status:</span>
				<span class="formX"><%=reqCardStatus%></span>
			</div>
			<div class="row">
				<span class="label">Transaction Id:</span>
				<span class="formX"><%=nullCheck(reqsslTxnId)%></span>
			</div>
			<div class="row">
				<span class="label">Amount:</span>
				<span class="formX"><%=amtFormat(reqamount)%></span>
			</div>
<%
}
ArrayList childPayment = (ArrayList)request.getAttribute("childPayment");
				System.out.print("childPayment value in jsp :" + childPayment);
				String checkNumber1 ="";
			    String paymentStatus1 = "";
				String checkName1 ="";
			    String sslTxnId1 = "";
				String bankName1 ="";
			    String checkDate1 = "";
				String amount1 = "";
				String checkAmount1 = "";
				String temAmt1 ="";
				String sslResult1= "";
				String tempPaymentId1 = "";
				String balAmt1 = "";
				String cardStatus1= "";
				String cardType1 = "";
				String cardName1 = "";
				String payId = "";
	
							if(childPayment!=null && childPayment.size()!=0){
							Iterator itr1 = 	childPayment.iterator();
							int i = 1;
							while(itr1.hasNext()) {				   
								String[] val1=(String[])itr1.next();
									checkNumber1 =  val1[0];
									paymentStatus1 =  val1[1];
									checkName1 =  val1[2];
									sslTxnId1 = val1[3];
									bankName1 =  val1[4];
									checkDate1 =  val1[5];
									amount1 =  val1[6];
									checkAmount1 = val1[7];
									sslResult1 = val1[8];
									tempPaymentId1 = val1[9];	
									balAmt1 = val1[10];
									cardName1 = val1[11];
									cardType1 = val1[12];
									cardStatus1 = val1[13];	
									payId = val1[14];
									System.out.print("payId :" + payId);
				if(paymentStatus1!=null && paymentStatus1.equalsIgnoreCase("check")){
				%>
				<div class="rowHead">Next Request Transaction Details:&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=i%></span></div>
				<div class="row">
						<span class="label">Check Number:</span>
						<span class="formX"><%=nullCheck(checkNumber1)%></span>
				</div>
				<div class="row">
						<span class="label">Check Name:</span>
			 
						<span class="formX"><%=nullCheck(checkName1)%></span>
				</div>
				<div class="row">
						<span class="label">Amount(<strong>$</strong>):</span>
						<span class="formX"><%=amtFormat(amount1)%></span>
				</div>
				<!--<div class="row">
				 
						<span class="label">Check Enclosed Amount(<strong>$</strong>):</span>
						<span class="formX"><%//=amtFormat(checkAmount1)%></span>
				</div>
				<div class="row">
				 
						<span class="label">Pending Amount(<strong>$</strong>):</span>
						<span class="formX"><%//=amtFormat(balAmt1)%></span>
				</div>-->
				<div class="row">
						<span class="label">Check Date:</span>
				<% String chk_dte1 ="";
						if(checkDate1!=null){ chk_dte1=checkDate1.substring(0,10);	}%>
 						<span class="formX"><%=dateFormat(chk_dte1)%></span>
				</div>
				<div class="row">
						<span class="label">Bank Name:</span>
						<span class="formX"><%=nullCheck(bankName1)%></span>
				</div>
						
				<%	}
				else if(paymentStatus1!=null && (paymentStatus1.equalsIgnoreCase("Card") || paymentStatus1.equalsIgnoreCase("credit card")))
					{   %>
					<div class="rowHead">Next Request Transaction Details:&nbsp;&nbsp;&nbsp;<span class="styleBoldOne"><%=i%></span></div>
					<div class="row">
				 		<span class="label">Card Name:</span>
						<span class="formX"><%=nullCheck(cardName1)%></span>
				</div>
				<div class="row">
						<span class="label">Card Type:</span>
						<span class="formX"><%=nullCheck(cardType1)%></span>
				</div>
				<div class="row">
				 		<span class="label">Card Status:</span>
						<span class="formX"><%=nullCheck(cardStatus1)%></span>
				</div>
				<div class="row">
						<span class="label">Transaction Id:</span>
					 
						<span class="formX"><%=nullCheck(sslTxnId1)%></span>
				</div>
				<div class="row">
						<span class="label">Amount(<strong>$</strong>):</span>
						<span class="formX"><%=amtFormat(amount1)%></span>
				</div>
				
				   
			<%
				}
			i++;
			}	
			} 
		 
%>
            <div class="rowHead">
            	Existing Rider Information Section:
			</div>
			<div class="row">
				<span class="label">No.:</span>
				<span class="formX">
				<input type="text" name="existUseaNo" id="riderUseaNo_id" class="readOnly" readonly="true"  value="<%=riderMemberId%>" size="20" />
				</span>
			</div>
            <%
            ArrayList riderInfoDetails = (ArrayList) request.getAttribute("riderInfoDetails");
            String rid_prefix1="";
            String rid_first_name = "";
            String rid_middle_name = "";
            String rid_last_name = "";
            String rid_sufix = "";
            String rid_email_id = "";
            String rid_suite = "";
            String rid_address1 = "";
            String rid_address2 = "";
            String rid_city = "";
            String rid_state = "";
            String rid_country = "";
            String rid_zip = "";
            String rid_phone_no = "";
            String rid_mobile_no = "";
            String rid_fax_no = "";
            if(riderInfoDetails !=null && riderInfoDetails.size()!=0){
                Iterator it = riderInfoDetails.iterator();
                while(it.hasNext()){
                    rid_prefix1 = (String)it.next();
                    rid_first_name  = (String)it.next();
                    rid_middle_name  = (String)it.next();
                    rid_last_name = (String)it.next();
                    rid_sufix =  (String)it.next();
                    rid_email_id  = (String)it.next();
                    rid_suite =  (String)it.next();
                    rid_address1 =  (String)it.next();
                    rid_address2 = (String)it.next();
                    rid_city = (String)it.next();
                    rid_state =  (String)it.next();
                    rid_country = (String)it.next();
                    rid_zip = (String)it.next();
                    rid_phone_no = (String)it.next();
                    rid_mobile_no = (String)it.next();
                    rid_fax_no = (String)it.next();
              
                }
            }
            %>			
            
			<div class="row">
				<span class="label">First Name:</span>
				<span class="formX">
				<input type="text" class="readOnly" readonly="true"  name="rName"   size="20" value="<%=nullCheck(rid_first_name)%>" />
				</span>	
			</div>
			<div class="row">
				<span class="label">Last Name:</span>
				<span class="formX">
				<input type="text" class="readOnly" readonly="true"  name="lName"   size="20" value="<%=nullCheck(rid_last_name)%>"/>
				</span>			
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX">
				<input type="text" class="readOnly" readonly="true" name="rState1" size="20" value="<%=nullCheck(rid_state)%>" />
				</span>	
			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX">
				<input type="text" class="readOnly" readonly="true" name="rZipcode1" size="10"  value="<%=nullCheck(rid_zip)%>"/>
				</span>		
			</div>
			<div class="row">
				<span class="label">Phone:</span>
				<span class="formX">
				<input type="text" class="readOnly" readonly="true" name="rPhone"   size="15" value="<%=nullCheck(rid_phone_no)%>" />
				</span>	
			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX">
				<input type="text" class="readOnly" readonly="true" name="rEmailid" size="30" value="<%=nullCheck(rid_email_id)%>" />
				</span>		
			</div>
            <%
            
            ArrayList listContact = (ArrayList) request.getAttribute("ownerDetails");
            String prefix1="";
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
			
			
			<div class="rowHead">
				Existing Owner Information:
			</div>
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="ownerName" id="ownerName"  value="<%=first_name%>&nbsp;<%=last_name%>" size="20" />
				</span>	
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="state" id="state"  value="<%=nullCheck(state)%>"  size="20" />
				</span>	
			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="zip" id="zip"  value="<%=nullCheck(zip)%>"   size="20" />
				</span>	
			</div>
			<div class="row">
				<span class="label">Phone Number:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="phone_no" id="phone_no"  value="<%=nullCheck(phone_no)%>"   size="20" />
				</span>	
			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="email_id" id="email_id"  value="<%=nullCheck(email_id)%>"   size="30" />
				</span>	
			</div>
			<input type="hidden" name="existiongOwnerEmail" value="<%=email_id%>">
            <%
            HLCRequestHorseDetVO objReqDet = (HLCRequestHorseDetVO)request.getAttribute("objReqDet");
            String reqId = objReqDet.getRequestId();
            String reqHrMemberId = objReqDet.getHorseMemberId();
            Date reqDate = objReqDet.getReqDate();
            String reqBy = objReqDet.getRequestedBy();
            String reqOwnerId = objReqDet.getReqOwnerId();
            String reqId2 = objReqDet.getReqRiderId();
            String reqHrName = objReqDet.getReqHorseName();
            String reqHrRegName = objReqDet.getReqHorseRegName();
            float reqId5 = objReqDet.getReqAmount();
            boolean reqStatus = objReqDet.isReqStatus();
            String reqPaymentId = objReqDet.getPaymentId();
            if(reqHrName==null) reqHrName = "";
            if(reqHrRegName==null) reqHrRegName = "";										
            %>
            
                <input type="hidden" name="process" value="horseReg">
                <input type="hidden" name="horseMemberId" value="<%=reqHrMemberId%>">
                <input type="hidden" name="relationId" value="<%=reqId%>">
                <input type="hidden" name="reqOwnerId" value="<%=reqOwnerId%>">
                <input type="hidden" name="reqId2" value="<%=reqId2%>">
                <input type="hidden" name="addOwnerUserId" id="addUserDetId" />
                
                <input type="hidden" name="horseStat" value="no" />
                <%
                if(objReqDet!=null){
                if(((reqHrName!=null)&&(reqHrName.trim().length()!=0))||((reqHrRegName!=null)&&(reqHrRegName.trim().length()!=0))){
                %>
                <input type="hidden" name="horseStat" value="yes" />
				<div class="rowHead">
				 Requested Horse Details:  
				</div>
				<div class="row">
					<span class="label">Requested Horse Name:</span>
					<span class="formX">
					<input type="text" name="reqHrName" class="textboxOne" id="ownerName"  value="<%=reqHrName%>" size="20" maxlength="80" />
					</span>
				</div>
				<div class="row">
					<span class="label">Requested Registered Name:</span>
					<span class="formX">
					<input type="text" name="reqHrRegName"  class="textboxOne" id="state2"  value="<%=reqHrRegName%>"  size="20" maxlength="80"/>
					</span>
				</div>
				<div class="row">
					<span class="label">Requested By:</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(reqBy)%></span>
					</span>	
				</div>
<%
}
}
            ArrayList reqRiderInfoDetails = (ArrayList) request.getAttribute("reqRiderDet");
            String rid = (String)request.getAttribute("rid");
            String rid_prefix11="";
            String rid_first_name1 = "";
            String rid_middle_name1 = "";
            String rid_last_name1 = "";
            String rid_sufix1 = "";
            String rid_email_id1 = "";
            String rid_suite1 = "";
            String rid_address11 = "";
            String rid_address21 = "";
            String rid_city1 = "";
            String rid_state1 = "";
            String rid_country1 = "";
            String rid_zip1 = "";
            String rid_phone_no1 = "";
            String rid_mobile_no1 = "";
            String rid_fax_no1 = "";
            if(reqRiderInfoDetails !=null && reqRiderInfoDetails.size()!=0){
                Iterator itRider = reqRiderInfoDetails.iterator();
                while(itRider.hasNext()){
                    rid_prefix11 = (String)itRider.next();
                    rid_first_name1  = (String)itRider.next();
                    rid_middle_name1  = (String)itRider.next();
                    rid_last_name1 = (String)itRider.next();
                    rid_sufix1 =  (String)itRider.next();
                    rid_email_id1  = (String)itRider.next();
                    rid_suite1 =  (String)itRider.next();
                    rid_address11 =  (String)itRider.next();
                    rid_address21 = (String)itRider.next();
                    rid_city1 = (String)itRider.next();
                    rid_state1 =  (String)itRider.next();
                    rid_country1 = (String)itRider.next();
                    rid_zip1 = (String)itRider.next();
                    rid_phone_no1 = (String)itRider.next();
                    rid_mobile_no1 = (String)itRider.next();
                    rid_fax_no1 = (String)itRider.next();
                    
                }
               %>			
                <div class="rowHead">
				Requested Rider Information Section:
				</div>
				<div class="row">
					<span class="label">No.:</span>
					<span class="formX">
					<input type="text" class="textboxOne" name="hlcNo" id="riderUseaNo_id" value="<%=rid%>" onblur="rCheck();" size="20" />
					</span>
				</div>
				<div class="row">
					<span class="label">Salutation:</span>
					<span class="formX">
					<input type="text" name="rprefix" id="rprefix" readonly="true" class="readOnly" value="<%=nullCheck(rid_prefix11)%>" size="10" />
					</span>	
				</div>
				<div class="row">
					<span class="label">First Name:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true"  name="riderFname_id" id="riderFname_id" value="<%=nullCheck(rid_first_name1)%>" size="20" />
					</span>				
				</div>
				<div class="row">
					<span class="label">Last Name:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true"  name="riderLname_id" id="riderLname_id" value="<%=nullCheck(rid_last_name1)%>" size="20" />
					</span>				
				</div>
				<div class="row">
					<span class="label">Street:</span>
					<span class="formX">
					<input type="text"  class="readOnly"  readonly="true" name="rStreet" id="rStreet" size="20" value="<%=nullCheck(rid_address11)%>" />
					</span>				
				</div>
				<div class="row">
					<span class="label">City:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true" name="rCity" id="rCity" size="15" value="<%=nullCheck(rid_city1)%>" />
					</span>			
				</div>
				<div class="row">
					<span class="label">State:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true" name="rState" id="rState" value="<%=nullCheck(rid_state1)%>" size="20" />
					</span>			
				</div>
				<div class="row">
					<span class="label">Zipcode:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true" name="rZipcode" id="rZipcode" value="<%=nullCheck(rid_zip1)%>" size="8" />
					</span>			
				</div>
				<div class="row">
					<span class="label">Phone:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true" name="riderPhone_id" id="riderPhone_id" value="<%=nullCheck(rid_phone_no1)%>" size="15" />
					</span>			
				</div>
				<div class="row">
					<span class="label">Cell:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true" name="rCell" id="rCell" size="15" value="<%=nullCheck(rid_mobile_no1)%>"/>
					</span>		
				</div>
				<div class="row">
					<span class="label">Email:</span>
					<span class="formX">
					<input type="text" class="readOnly" readonly="true" name="rEmail" id="rEmail" value="<%=nullCheck(rid_email_id1)%>" size="20" />
					</span>	
					<input type="hidden" name="requestedRiderMail" value="<%=rid_email_id1%>"/>	
				</div>
<%
}
            ArrayList reqListContact = (ArrayList) request.getAttribute("reqOwnerDetails");
            prefix1="";
            first_name = "";
            middle_name = "";
            last_name = "";
            sufix = "";
            email_id = "";
            suite = "";
            address1 = "";
            address2 = "";
            city = "";
            state = "";
            country = "";
            zip = "";
            phone_no = "";
            mobile_no = "";
            fax_no = "";
            if(reqListContact !=null && reqListContact.size()!=0){
                Iterator itOwner = reqListContact.iterator();
                while(itOwner.hasNext()){
                    prefix1 = (String)itOwner.next();
                    first_name  = (String)itOwner.next();
                    middle_name  = (String)itOwner.next();
                    last_name = (String)itOwner.next();
                    sufix =  (String)itOwner.next();
                    email_id  = (String)itOwner.next();
                    suite =  (String)itOwner.next();
                    address1 =  (String)itOwner.next();
                    address2 = (String)itOwner.next();
                    city = (String)itOwner.next();
                    state =  (String)itOwner.next();
                    country = (String)itOwner.next();
                    zip = (String)itOwner.next();
                    phone_no = (String)itOwner.next();
                    mobile_no = (String)itOwner.next();
                    fax_no = (String)itOwner.next();
}
%>	
                
                <input type="hidden" name="reqVal" value="yes">
                
                <div class="rowHead">
                	Requested  Owner Information Section:
				</div>
				<div class="row">
				<span class="label">Do you want to edit the owner?</span>
					<span class="formX">
					<input type="radio" size="10" name="ado" id="adoId" value="yes" onclick="switchDiv('addOwn');showHideRadio('ado','existOwn','no')" /> Yes
					<input type="radio" size="10" name="ado" id="adoId" value="no" checked="checked" onClick="switchDiv('existOwn');showHideRadio('ado','addOwn','no'), showHideRadio('ado','companyAddUser','no'), showHideRadio('ado','existAddComp','no'), showHideRadio('ado','compAddQuest','no');"> No
					</span>	

				</div>
<div id="addOwn">
                <table width="100%" cellpadding="0" cellspacing="1" border="0">
                <tr>
                    <td class="tableLeftTxtArea">Choose one option that apply:</td>
                    <td class="tableRight">
                        <input type="radio" size="10" name="regAddFor" id="rself1" value="mem" onClick="switchDiv('ridAddUser'),showHideRadio('regAddFor','ridAddMemb','mem'), showHideRadio('regAddFor','accAddUser','mem'),  showHideRadio('regAddFor','companyAddUser','mem'), showHideRadio('regAddFor','existAddComp','mem'), showHideRadio('regAddFor','compAddQuest','mem'),clearNotHLCdetails1();" />
                        The owner is the rider. <br /> 
                        <input type="radio" size="10" name="regAddFor" id="rself1" value="rid" onClick="switchDiv('ridAddMemb'),showHideRadio('regAddFor','ridAddUser','rid'), showHideRadio('regAddFor','accAddUser','rid'),  showHideRadio('regAddFor','companyAddUser','rid'),showHideRadio('regAddFor','compAddQuest','rid'), showHideRadio('regAddFor','existAddComp','rid');comRegClear1(); comNewClear1(); comRadioClear1();" />
                        Owner is Member. <br />
                        <input type="radio" size="10" name="regAddFor" id="rself1" value="acc" onClick="switchDiv('accAddUser'),showHideRadio('regAddFor','ridAddUser','acc'),  showHideRadio('regAddFor','companyAddUser','acc'),showHideRadio('regAddFor','compAddQuest','acc'), 
                        showHideRadio('regAddFor','ridAddMemb','acc'), showHideRadio('regAddFor','existAddComp','acc'), clearHLCdetails1(); comRegClear1(); comNewClear1(); comRadioClear1();clear2();" />
                        The owner has a Dashboard User Name(Login Id).  <br />
                        <input type="radio" size="10" name="regAddFor" id="rself1" value="cmp" onClick="switchDiv('compAddQuest'), showHideRadio('regAddFor','ridAddUser','cmp'),showHideRadio('regAddFor','ridAddMemb','cmp'), showHideRadio('regAddFor','accAddUser','cmp'),  showHideRadio('regAddFor','companyAddUser','cmp'), showHideRadio('regAddFor','existAddComp','cmp'), clearHLCdetails1(); clearNotHLCdetails1();" />
                    	The owner is a Company.
					</td>
                </tr>
					<tr>
					<td colspan="2" id="ridAddUser">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr> 
								<td colspan="2" class="tblMainHead">Owner Information</td>
							</tr>
							<tr>
								<td class="tableLeft">Member ID:</td>
								<th class="tableRight"><input name="ownerUseaNoTwo" id="ownerUseaNo2_id" class="textboxOne" size="20" onblur="userDetails(this);" /> <span class="asterisk">*</span></th>
							</tr>
							<tr> 
								<td class="tableLeft">First Name:</td>
								<th class="tableRight"><input name="ownerFNameTwo" id="ownerFname2_id" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly"  size="20" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Last Name:</td>
								<th class="tableRight"><input name="ownerLNameTwo" id="ownerLname2_id" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly"  size="20" /></th>
							</tr>
							<tr>
								<td class="tableLeft">Phone:</td>
								<th class="tableRight">xxx-xxx-<input name="ownerPhoneTwo" id="ownerPhone2_id" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="readonly"  size="8" /></th>
							</tr>
						</table>
					</td>
					</tr> 
				<tr id="compAddQuest">
					<td class="tableLeft">Is the company already registered ?</td>
					<td class="tableRight">
					<input type="radio" size="10" name="ecmp1" id="ecmp" value="yes" onClick="switchDiv('existAddComp'); showHideRadio('ecmp1','companyAddUser','yes'); comNewClear1();"> Yes
					<input type="radio" size="10" name="ecmp1" id="ecmp" value="no"  onClick="switchDiv('companyAddUser'); showHideRadio('ecmp1','existAddComp','no'); comRegClear1();"> No	
					</td>
				</tr>
				<tr>
				<td colspan="2" id="ridAddMemb">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr> 
							<td colspan="2" class="tblMainHead">Owner Information - Member</td>
						</tr>
						<tr>
							<td class="tableLeft">Member ID:</td>
							<th class="tableRight">
							<input name="userNameId2" id="userNameId2" class="textboxOne" size="20" onblur="userDetails1(this);"/> <span class="asterisk">*</span> 
							</th>
						</tr>
						<tr> 
							<td class="tableLeft">First Name:</td>
							<th class="tableRight">
							<input name="firstNameId2" class="textboxOne" id="firstNameId2" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" />
							</th>
						</tr>
						<tr>
							<td class="tableLeft">Last Name:</td>
							<th class="tableRight">
							<input name="lastNameId2" class="textboxOne" id="lastNameId2" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" />
							</th>
						</tr>
						<tr>
							<td class="tableLeft">Phone: (Last 4 digits)</td>
							<th class="tableRight">
							xxx-xxx-<input name="phoneId2" class="textboxOne" id="phoneId2" style="background-color:#F4F4F4; font-weight:bold; border:<h1></h1>0px;" value="" size="20" readonly="readonly" />
							</th>
						</tr>
					</table>
				</td>
				</tr>						  
                <tr>
                    <td colspan="2" id="accAddUser">
                        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr> 
                                <td colspan="2" class="tblMainHead">Owner Information - Web User </td>
                            </tr>
                            <tr>
                                <td class="tableLeft">Username:</td>
								<th class="tableRight">
								<input name="userNameId3" id="userNameId3" class="textboxOne" size="20" onblur="nonUserDetails(this);" /> 
								<span class="asterisk">*</span>
								</th>
                            </tr>
                            <tr> 
                                <td class="tableLeft">First Name:</td>
                                <th class="tableRight">
								<input name="firstNameId3" class="textboxOne" id="firstNameId3" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" />
								</th>
                            </tr>
                            <tr>
                                <td class="tableLeft">Last Name:</td>
                                <th class="tableRight">
								<input name="lastNameId3" class="textboxOne" id="lastNameId3" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" size="20" readonly="readonly" />
								</th>
                            </tr>
                            <tr>
                                <td class="tableLeft">Phone: (Last 4 digits)</td>
                                <th class="tableRight">
								xxx-xxx-<input name="phoneId3" id="phoneId3" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="" readonly="readonly" size="8" />
								</th>
                            </tr>
						</table>		
					</td>
				</tr>
                
                <tr>
                    <td colspan="2" id="existAddComp">
                        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr> 
                                <td colspan="2" class="tblMainHead">Owner Information - Company </td>
                            </tr>
                            
                            <tr>
                                <td class="tableLeft">Company Name:</td>
                                <th class="tableRight"><input name="existAddCompNameId1" id="existAddCompNameId1" class="textboxOne" size="20" onblur="nonUserDetails1(this);" /> <span class="asterisk">*</span> </th>
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
                    </table>							</td>
                </tr>
                
                
                <tr>
                    <td colspan="2" id="companyAddUser">
                        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                            
                            <tr> 
                                <td colspan="2" class="tblMainHead">Addtional Owner Information - New Company </td>
                            </tr>
                            <tr>
                                <td class="tableLeft"> Company Name:</td>
                                <td class="tableRight">
								<input name="newAddFirstNameIdComp" id="newAddFirstNameIdComp" class="textboxOne" size="20" onblur="usrStat();"/>
                                <span class="asterisk">*</span>
							  </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft"> Address</td>
                                <td class="tableRight">
                                <input name="newAddAddressIdComp" id="newAddAddressIdComp" class="textboxOne" size="20" />
                                <span class="asterisk">*</span>
							  </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft"> Country</td>
                                <td  class="tableRight">
                                <SELECT name="newAddCountryIdComp" id="newAddCountryIdComp" class="selectboxOne"  onChange="FillState(document.myform.newAddCountryIdComp, document.myform.newAddStateIdComp, '--Select One--');">
                                </SELECT>
                                <span class="asterisk">*</span>
							  </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft"> State</td>
                                <td class="tableRight">  
                                <SELECT name="newAddStateIdComp" id="newAddStateIdComp" class="selectboxOne" ></SELECT>
                                <span class="asterisk">*</span>   </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft"> City</td>
                                <td class="tableRight">
                                 <input name="newAddCityIdComp" id="newAddCityIdComp" class="textboxOne" size="20" />
                                <span class="asterisk">*</span> </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft"> Zip Code</td>
                                <td class="tableRight">
                                    <input name="newAddZipIdComp" id="newAddZipIdComp" class="textboxOne" size="20" />
                                <span class="asterisk">*</span> </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft">EMail-ID</td>
                                <td class="tableRight">
                                    <input name="newAddEmailIdComp" id="newAddEmailIdComp" class="textboxOne" size="20" />
                                <span class="asterisk">*</span> </td>
                            </tr>
                            <tr> 
                                <td class="tableLeft">Phone</td>
                                <td class="tableRight">
                                    <input name="newAddPhoneIdComp" id="newAddPhoneIdComp" class="textboxOne" size="20" />
                                <span class="asterisk">*</span>
								</td>
                            </tr>
                            <tr> 
                                <td class="tableLeft">Fax</td>
                                <td class="tableRight">
                                <input name="newAddFaxIdComp" id="newAddFaxIdComp" class="textboxOne" size="20" />
								</td>
                            </tr>
							<tr> 
								<td class="tableLeft">Contact Person First Name</td>
								<td class="tableRight">
								<input name="cpAddFirstNameIdComp" id="cpAddFirstNameIdComp" class="textboxOne" size="20" />
								<span class="asterisk">*</span>
								</td>								  
							</tr>
							<tr> 
								<td class="tableLeft">Contact Person Last Name</td>
								<td class="tableRight">
								<input name="cpAddLastNameIdComp" id="cpAddLastNameIdComp" class="textboxOne" size="20" />
								<span class="asterisk">*</span>
								</td>	
							</tr>
                   
					</table>
					</td>
                </tr>
            </table>
        </div>
<div id="existOwn">
			<div class="row">
				<span class="label">Name:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="ownerName2" id="ownerName2"  value="<%=first_name%>&nbsp;<%=last_name%>" size="20" />
				</span> 
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="state" id="state"  value="<%=nullCheck(state)%>"  size="20" />
				</span>								
			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="zip" id="zip"  value="<%=nullCheck(zip)%>"   size="20" />
				</span>								
			</div>
			<div class="row">
				<span class="label">Phone Number:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="phone_no" id="phone_no"  value="<%=nullCheck(phone_no)%>"   size="20" />
				</span>								
			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX">
				<input type="text"  readonly="true" class="readOnly" name="email_id" id="email_id"  value="<%=nullCheck(email_id)%>"   size="30" />
				</span>								
			</div>
			<input type="hidden" name="requestedOwnerMail" value="<%=email_id%>"/>								
</div>
        
        <%
        }
        else{
        %>
        <input type="hidden" name="reqVal" value="no">
        <%
        }
        
        
        if(reqStatus!=true){
        System.out.print("reqcheckAmount" + reqcheckAmount);
        if(reqPaymentId!=null && reqPaymentId.trim().length()!=0){
        if(reqpaymentStatus!=null && reqpaymentStatus.equalsIgnoreCase("check")){
        if(reqcheckAmount!=null){
        float tempCheck = Float.parseFloat(reqcheckAmount);
        float tempAmount = Float.parseFloat(reqamount);
        if(tempCheck<tempAmount){
        %>
        
        <div class="rowHead">
            Edit Payment Information:
        </div>
        
        <div class="row">
            <span class="label"><span class="styleBoldOne">Change Payment For Pending Amount</span> </span>
            <span class="formX"><a href='chngHorseName.do?process=chngpay'>Click Here To Change Payment</a></span>
        </div>
        <%}
        }
        }
        
        else{
        if(reqsslResult!=null && reqsslResult.trim().length()!=0){                                 			
        if(reqsslResult.equalsIgnoreCase("1")){
        %>  
        <div class="rowHead">
            Edit Payment Information:
        </div>
        
        <div class="row">
            <span class="label"><span class="styleBoldOne">Change Payment For Pending Amount</span> </span>
            <span class="formX"><a href='chngHorseName.do?process=chngpay'>Click Here To Change Payment</a></span>
        </div>
        <%}}} 
        
        }
        
        } 
        session.setAttribute("horseMemberId",horseMemberId);
        session.setAttribute("competitionName",competitionName);
        session.setAttribute("paymentIdVal",reqtempPaymentId);
        
        session.setAttribute("regByuserId",regByuserId);
        session.setAttribute("successStatus","ChangeReq");
        
        %>
        
        <div class="alignCenter">
            <span>
                <input type="submit" value="Submit" class="gradBtn"/> &nbsp; &nbsp; &nbsp;  
                <input type="button" value="Cancel" name="back" class="gradBtn" onclick="javascript:history.back(-1);"/>
            </span>			
        </div>
    </form>
<!-- CONTENTS END HERE -->	</div>	 </td>
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
 
<% 
	
	if(reqListContact !=null && reqListContact.size()!=0){
    %>
	FillCountry(document.myform.newAddCountryIdComp, document.myform.newAddStateIdComp, 'USA');
	FillState(document.myform.newAddCountryIdComp, document.myform.newAddStateIdComp, '---Select---');
<%	}%>
</script>
</html>
