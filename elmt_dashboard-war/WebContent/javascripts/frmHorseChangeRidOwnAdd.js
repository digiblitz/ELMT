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
// JavaScript Document
function showLevels(chkBxNam,divId){
	if(document.forms['myform'].elements[chkBxNam].checked == true){
		document.getElementById(divId).style.display = "block";	
	}else {
		document.getElementById(divId).style.display = "none";	
	} 
}
				
function showHide(chkBxNam,divId){
	if(document.forms['myform'].elements[chkBxNam].checked == true){
		document.getElementById(divId).style.display = "block";	
	}else {
		document.getElementById(divId).style.display = "none";	
	} 
}

function enabDisab(chkBxId,txtBxId){
	if(document.forms['myform'].elements[chkBxId].checked == true){
		document.forms['myform'].elements[txtBxId].disabled = false;	
	}else {
		document.forms['myform'].elements[txtBxId].disabled = "disabled";	
	} 
}

function enabDisabPony(radioNam,txtBxId){
	if(document.forms['myform'].elements[radioNam].value == "yes"){
		document.forms['myform'].elements[txtBxId].disabled = false;	
	}else {
		document.forms['myform'].elements[txtBxId].disabled = "disabled";	
	} 
}

function enableRad(radBtnId,txtBxId){
	if(document.forms['myform'].elements[radBtnId].checked == true){
		document.forms['myform'].elements[txtBxId].disabled = false;	
	}else{
		document.forms['myform'].elements[txtBxId].disabled = "disabled";	
	} 
}

function disable(radBtnNewId,txtBxId){
	if(document.forms['myform'].elements[radBtnNewId].checked == true){
		document.forms['myform'].elements[txtBxId].disabled = "disabled";	
	}
}	
		
function showHideRadio(radioNam,divId,radVal){
	if(document.forms['myform'].elements[radioNam].value == radVal){
		document.getElementById(divId).style.display = "block";	
	}
	else {
		document.getElementById(divId).style.display = "none";	
	} 
}

//========================== function for character validation ================================

function isNameAndCity(str){
	check="`~!@#$%^&*()_+=|{}[];:1234567890,/<>?"+"\\"+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

function isHorseName(str){
	check="`~!@#$%^*()_+=|{}[];:/<>?"+"\\"+"\""+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

function isOwnerName(str){
	check="`~!@#$%^&*()_+=|{}1234567890[];:/<>?"+"\\"+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

function isPaymentName(str){
	check="`~!@#$%^*()_+=|{}1234567890[];,:/<>?"+"\\"+"\""+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

function isZipcode(str){
	zip="0123456789";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(zip.indexOf(str.charAt(j))!=-1){
			fzip=0;}}
	if(fzip==0){return false;}
	else{return true;}
}

function isPhone(str){
	check="`~!@#$%^&*_+=abcdefghijklmnopqrstuvwxyz|;:,./<>?"+"\\"+"\""+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

//========================= function for pure Integer Numbers ===================================
function isnotInteger(str){
	stringIntCheck="0123456789";
	f2=1;
	for(j=0;j<str.length;j++){
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{
		return false;
	}
}

//======================== function for two decimal points ======================================
function isnotValidDecimal(str){
	if((str.indexOf("."))!=-1){
		fr1=str.indexOf('.');
		mm = (str.substring(fr1,str.length));
		strnum=(Number(mm.length));
		if(strnum>3){
			return true;
		}
		else{
			return false;
		}
	}
}

//========================== function for Special Character =====================================
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\";
	f4=1;
	for(j=0;j<str.length;j++){
		if(stringSpecialCheck.indexOf(str.charAt(j))!=-1){
			f4=0;
		}
	}
	if(f4==0){
		return true;
	}
	else{
		return false;
	}
}

function isSpecialCharacter(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`;:"+"\\"+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;
		}
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

function clearHLCdetails1(){
	document.myform.userNameId2.value="";
	document.myform.firstNameId2.value="";
	document.myform.lastNameId2.value="";
	document.myform.phoneId2.value="";
	document.myform.addUserDetId.value="";
}

function clearNotHLCdetails1(){
	document.myform.userNameId3.value="";
	document.myform.firstNameId3.value="";
	document.myform.lastNameId3.value="";
	document.myform.phoneId3.value="";
	document.myform.addUserDetId.value="";
}

/*function clearNewdetails1(){
	document.myform.addUserDetId.value="";
	document.myform.newFirstNameId1.value="";
	document.myform.newLastNameId1.value="";
	document.myform.newBirthmonthId1.value="";
	document.myform.newBirthdayId1.value="";
	document.myform.newBirthyearId1.value="";
	document.myform.sexId1[0].checked=false;
	document.myform.sexId1[1].checked=false;
	document.myform.newAddressId1.value="";
	document.myform.newStateId1.selectedIndex=0;
	document.myform.newCityId1.value="";
	document.myform.newZipId1.value="";
	document.myform.newEmailId1.value="";
	document.myform.newPhoneId1.value="";
	document.myform.newFaxId1.value="";
}*/

function comRegClear1(){
	document.myform.existAddCompNameId1.value="";
	document.myform.cpAddfirstNameId1.value="";
	document.myform.cpAddlastNameId1.value="";
	document.myform.ecmpAddPhoneId1.value="";
}

function comNewClear1(){
	document.myform.newAddFirstNameIdComp.value="";
	document.myform.newAddAddressIdComp.value="";
	document.myform.newAddCountryIdComp.value="USA";
	document.myform.newAddStateIdComp.selectedIndex=0;
	document.myform.newAddCityIdComp.value="";
	document.myform.newAddZipIdComp.value="";
	document.myform.newAddEmailIdComp.value="";
	document.myform.newAddPhoneIdComp.value="";
	document.myform.newAddFaxIdComp.value="";
	document.myform.cpAddFirstNameIdComp.value="";
	document.myform.cpAddLastNameIdComp.value="";
}

function comRadioClear1(){
	for(i=0;i<document.myform.ecmp1.length;i++){
		document.myform.ecmp1[i].checked=false;
	}
}

function checkClear(){
	if(document.myform.roughVal.value=='yes'){
		document.myform.chkBalAmt.value="";
	}
	document.myform.checkNumber.value="";
	document.myform.checkDate.value="";
	document.myform.bankName.value="";	
	document.myform.nameCheck.value="";
	
}

function cardClear(){
	 
	document.myform.ccType.selectedIndex=0;
	document.myform.ccNumber.value="";
	document.myform.ccCvvid.value="";
	document.myform.ccName.value="";
	document.myform.ccExpMonth.selectedIndex=0;
	document.myform.ccExpYear.selectedIndex=0;
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getFullYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	document.myform.checkDate.value = nowMonth+"/"+nowDate+"/"+nowYear;
}

function popupCal(val) {
      if(val==1)
	      document.myform.checkDate.value = "StartDate1";
      val = window.open("javascripts/calendar2.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null) {
         alert("Plz Enable Site Popup Allowed");
      }
}

function myValidate(){
	stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
	stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";

//============================= For Part A ======================================================
	if(document.myform.horseName.value.length>20){
		alert("Enter valid horseName");
		document.myform.horseName.focus();
		return false;
	}
	if(isHorseName(document.myform.horseName.value.length>20)){
		alert("Enter valid horseName");
		document.myform.horseName.focus();
		return false;
	}
	if(document.myform.horseRegisterName.value.length>20){
		alert("Enter valid horseRegisterName");
		document.myform.horseRegisterName.focus();
		return false;
	}
	if(isHorseName(document.myform.addR_num.value)){
		alert("Enter valid HLC Number in Additional Rider Information Section");
		document.myform.addR_num.focus();
		return false;
	}
	if(document.myform.addR_num.value.length>20){
		alert("HLC Number in Additional Rider Information Section should not exceed 20 characters");
		document.myform.addR_num.focus();
		return false;
	}

//================================ For Part B ===============================================
//======================= Owner Information Section =========================================
	chosen="";
	len=document.myform.regAddFor.length;
	for(i=0;i<len;i++){
		if(document.myform.regAddFor[i].checked){
			chosen= document.myform.regAddFor[i].value;
		}
	}
	 
	if(chosen=="" && document.myform.addR_num.value=="" && document.myform.horseRegisterName.value=="" && document.myform.horseName.value==""){
		alert("Check any one of the Information");
		return false;
	}
	if(chosen=="rid"){
		if(document.myform.userNameId2.value==""){
			alert("Enter Member Id in Owner Information Section");
			document.myform.userNameId2.focus();
			return false;
		}
		if(isnotSpecial(document.myform.userNameId2.value)){
			alert("Enter valid Member Id in Owner Information Section");
			document.myform.userNameId2.focus();
			return false;
		}
		if((document.myform.userNameId2.value.indexOf('  ')!=-1)||(document.myform.userNameId2.value.indexOf(' ')==0)){
			alert("Enter valid Member Id in Owner Information Section");
			document.myform.userNameId2.focus();
			return false;
		}
		if(document.myform.userNameId2.value.length > 80){
			alert("Enter valid Member Id in Owner Information Section");
			document.myform.userNameId2.focus();
			return false;
		}
	}
	if(chosen=="acc"){
		if(document.myform.userNameId3.value==""){
			alert("Enter User Name in Owner Information Section");
			document.myform.userNameId3.focus();
			return false;
		}
		if(isNameAndCity(document.myform.userNameId3.value)){
			alert("Enter valid User Name in Owner Information Section");
			document.myform.userNameId3.focus();
			return false;
		}
		if((document.myform.userNameId3.value.indexOf('  ')!=-1)||(document.myform.userNameId3.value.indexOf(' ')==0)){
			alert("Enter valid User Name in Owner Information Section");
			document.myform.userNameId3.focus();
			return false;
		}
		if(document.myform.userNameId3.value.length>80){
			alert("Enter valid User Name in Owner Information Section");
			document.myform.userNameId3.focus();
			return false;
		}
	}
/*	if(chosen=="new"){
		if(document.myform.newFirstNameId1.value==""){
			alert("Enter First Name in Owner Information Section");
			document.myform.newFirstNameId1.focus();
			return false;
		}
		if(isnotAlpha(document.myform.newFirstNameId1.value)){
			alert("Enter valid First Name in Owner Information Section");
			document.myform.newFirstNameId1.focus();
			return false;
		}
		if((document.myform.newFirstNameId1.value.indexOf('  ')!=-1)||(document.myform.newFirstNameId1.value.indexOf(' ')==0)){
			alert("Enter valid First Name in Owner Information Section");
			document.myform.newFirstNameId1.focus();
			return false;
		}	
		if(document.myform.newFirstNameId1.value.length > 80){
			alert("First Name in Owner Information Section should not exceed 80 characters");
			document.myform.newFirstNameId1.focus();
			return false;
		}
//=====================================for Last Name ============================================
		if(document.myform.newLastNameId1.value==""){
			alert("Enter Last Name in Owner Information Section");
			document.myform.newLastNameId1.focus();
			return false;
		}
		if(isnotAlpha(document.myform.newLastNameId1.value)){
			alert("Enter valid Last Name in Owner Information Section");
			document.myform.newLastNameId1.focus();
			return false;
		}
		if((document.myform.newLastNameId1.value.indexOf('  ')!=-1)||(document.myform.newLastNameId1.value.indexOf(' ')==0)){
			alert("Enter valid Last Name in Owner Information Section");
			document.myform.newLastNameId1.focus();
			return false;
		}	
		if(document.myform.newLastNameId1.value.length > 80){
			alert("Last Name in Owner Information Section should not exceed 80 characters");
			document.myform.newLastNameId1.focus();
			return false;
		}
		
//============================= DOB ========================================================
		if(document.myform.newBirthmonthId1.value==""){
			alert(" Select Month in Date of Birth ");
			document.myform.newBirthmonthId1.focus();
			return false;
		}
		if(!(document.myform.newBirthmonthId1.value=="")){
			leno=document.myform.newBirthmonthId1.length;
			for(i=0;i<leno;i++){
				if(document.myform.newBirthmonthId1[i].selected){
					choseno=document.myform.newBirthmonthId1[i].value ;
				}
			}
		}

//------month checking----
		var lyear=document.myform.newBirthyearId1.value;
		var lcheck=(lyear%4);
		if((document.myform.newBirthmonthId1.selectedIndex== 2)&&(document.myform.newBirthdayId1.value >29)&&(lcheck=="0")){
			alert ("Date of Birth is not valid") ;
			document.myform.newBirthdayId1.focus();
			return false;
		}
		var lyear1=document.myform.newBirthyearId1.value;
		var lcheck1=(lyear1%4);
		if((document.myform.newBirthmonthId1.selectedIndex== 2)&&(document.myform.newBirthdayId1.value >28)&&(lcheck1>0)){
			alert ("Date of Birth is not valid") ;
			document.myform.newBirthdayId1.focus();
			return false;
		}
		if((document.myform.newBirthmonthId1.selectedIndex== 4)&&(document.myform.newBirthdayId1.value=="31")){
			alert ("Date of Birth is not valid") ;
			document.myform.newBirthdayId1.focus();
			return false;
		}
		if((document.myform.newBirthmonthId1.selectedIndex== 6)&&(document.myform.newBirthdayId1.value=="31")){
			alert ("Date of Birth is not valid") ;
			document.myform.newBirthdayId1.focus();
			return false;
		}
		if((document.myform.newBirthmonthId1.selectedIndex== 9)&&(document.myform.newBirthdayId1.value=="31")){
			alert ("Date of Birth is not valid") ;
			document.myform.newBirthdayId1.focus();
			return false;
		}
		if((document.myform.newBirthmonthId1.selectedIndex== 11)&&(document.myform.newBirthdayId1.value=="31")){
			alert ("Date of Birth is not valid") ;
			document.myform.newBirthdayId1.focus();
			return false;
		}
		if(document.myform.newBirthdayId1.value==""){
			alert(" Select Day in Date of Birth");
			document.myform.newBirthdayId1.focus();
			return false;
		}
		if(!(document.myform.newBirthdayId1.value=="")){
			leno=document.myform.newBirthdayId1.length;
			for(i=0;i<leno;i++){
				if(document.myform.newBirthdayId1[i].selected){
					choseno=document.myform.newBirthdayId1[i].value ;
				}
			}
		}
		if(document.myform.newBirthyearId1.value==""){
			alert(" Select Year in Date of Birth");
			document.myform.newBirthyearId1.focus();
			return false;
		}
		if(!(document.myform.newBirthyearId1.value=="")){
			leno=document.myform.newBirthyearId1.length;
			for(i=0;i<leno;i++){ 
				if(document.myform.newBirthyearId1[i].selected){
					choseno=document.myform.newBirthyearId1[i].value ;
				}
			}
		}

//============================for Gender===========================================
		chosen="";
		len = document.myform.sexId1.length ;
		for(i=0;i<len;i++){
			if(document.myform.sexId1[i].checked){
				chosen= document.myform.sexId1[i].value; 
			}
		}
		if(chosen==""){
			alert("Select the Gender");
			return false;
		}
//==============================For Address =================================
		if(document.myform.newAddressId1.value==""){
			alert("Enter Address in Owner Information Section");
			document.myform.newAddressId1.focus();
			return false;
		}
		if((document.myform.newAddressId1.value.indexOf('  ')!=-1)||(document.myform.newAddressId1.value.indexOf(' ')==0)){
			alert("Enter valid Address in Owner Information Section");
			document.myform.newAddressId1.focus();
			return false;
		}	
		if(document.myform.newAddressId1.value.length > 255){
			alert("Address in Owner Information Section should not exceed 255 characters");
			document.myform.newAddressId1.focus();
			return false;
		}

//================================= For Country ===================================
		var cdln = "";
		if(typeof(window.document.myform.newCountryId1) == 'object'){
			if (window.document.myform.newCountryId1.value != ""){
				var clid;
				clid = window.document.myform.newCountryId1[window.document.myform.newCountryId1.selectedIndex].value;
				cdln = clid;
			}
			if(cdln == "Select One"){
				alert("Please select the Country");
				window.document.myform.newCountryId1.focus();
				return false;
			}
		}
		if(document.myform.newCountryId1.selectedIndex == 0 ){
			alert ( "Please select the Country." );
			document.myform.newCountryId1.focus();
			return false;
		}

//================================ For State ====================================
		var edln = "";
		if(typeof(window.document.myform.newStateId1) == 'object'){
			if (window.document.myform.newStateId1.value != ""){
				var lid;
				lid = window.document.myform.newStateId1[window.document.myform.newStateId1.selectedIndex].value;
				edln = lid;
			}
			if(edln.length == 1){
				alert("Please select the State.");
				window.document.myform.newStateId1.focus();
				return false;
			}
		} 
		if ( document.myform.newStateId1.selectedIndex == 0 ){
			alert ( "Please select the State" );
			document.myform.newStateId1.focus();
			return false;
		}
		
//__________________________________________city___________________________________________________________________
		if(document.myform.newCityId1.value==""){
			alert("Enter City Name in Owner Information section");
			document.myform.newCityId1.focus();
			return false; 
		}
		if((document.myform.newCityId1.value.indexOf('  ')!=-1)||(document.myform.newCityId1.value.indexOf(' ')==0)){
			alert("Enter valid City Name in Owner Information Section");
			document.myform.newCityId1.focus();
			return false;
		}
		if(isnotAlpha1(document.myform.newCityId1.value)){
			alert("Enter valid City Name in Owner Information Section");
			document.myform.newCityId1.focus();
			return false; 
		}
		if( document.myform.newCityId1.value.length > 80 ){
			alert("City Name in Owner Information Section should not exceed 80 characters");
			document.myform.newCityId1.focus();
			return false; 
		}

//___________________________________________________ZipCode_________________________________________________________
		if(document.myform.newZipId1.value==""){
			alert("Enter Zipcode");
			document.myform.newZipId1.focus();
			return false;
		}
		if((document.myform.newZipId1.value.length<2)||(document.myform.newZipId1.value.length>20)){
			alert("Zipcode must be above 2 digits and below 20 digits.");
			document.myform.newZipId1.focus();
			return false;
		}
		if(document.myform.newZipId1.value.indexOf('.')!=-1){
			alert("Enter valid Zip code.");
			document.myform.newZipId1.focus();
			return false;
		}

//--------------------------------------EMAIL---------------------------------------
		if(document.myform.newEmailId1.value==""){
			alert("Enter Email ID");
			document.myform.newEmailId1.focus();
			return false;
		}
		if(!(document.myform.newEmailId1.value== "")){
			strmail=document.myform.newEmailId1.value;
			firstat = strmail.indexOf("@");
			lastat = strmail.lastIndexOf("@");
			strmain=strmail.substring(0,firstat);
			strsub=strmail.substring(firstat,document.myform.newEmailId1.value.length);
			if(strmail.length>120){
				alert("Email ID should not exceed 120 characters");
				document.myform.newEmailId1.focus();
				return false;
			}
			if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
				alert("Enter valid Email ");
				document.myform.newEmailId1.focus();
				return false;
			}
			if(isnotSpecial(strmain) || isnotSpecial(strsub)){
				alert("Enter valid Email ");
				document.myform.newEmailId1.focus();
				return false;
			}
			k=0;
			strlen=strsub.length;
			for(i=0;i<strlen-1;i++){
				if(strsub.charAt(i)=='.'){
					k=k+1;
				}
			}
			if(k>3){
				alert("Enter valid Email ");
				document.myform.newEmailId1.focus();
				return false;
			}
			if(firstat==-1 || lastat==-1){
				alert("Enter valid Email" );
				document.myform.newEmailId1.focus();
				return false;
			}
			if(Number(strmain)){
				alert("Enter valid Email ");
				document.myform.newEmailId1.focus();
				return false;
			}
			if(firstat != lastat ){
				alert("Enter valid Email ");
				document.myform.newEmailId1.focus();
				return false;
			}
			firstdot=strmail.indexOf(".",firstat);
			lastdot=strmail.lastIndexOf(".");
			if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
				alert("Enter valid Email ");
				document.myform.newEmailId1.focus();
				return false;
			}
		}

//----------------------IF ENTER PHONE ---------------------------------------
		if(document.myform.newPhoneId1.value==""){
			alert("Enter Phone Number");
			document.myform.newPhoneId1.focus();
			return false;
		}
		if(document.myform.newPhoneId1.value!=""){
			var s1=document.myform.newPhoneId1.value.indexOf('(');
			var s2=document.myform.newPhoneId1.value.indexOf(')');
			var s5=document.myform.newPhoneId1.value.indexOf('+');
			var s6=document.myform.newPhoneId1.value.lastIndexOf('+');
			var s7=document.myform.newPhoneId1.value.indexOf('-');
			var s8=document.myform.newPhoneId1.value.lastIndexOf('-');
			var s3=1+s2;
			var s4=1+s1;
			if(s1==s3){
				alert("Enter valid Phone Number");
				document.myform.newPhoneId1.focus();
				return false;
			}
			if(s2==s4){
				alert("Enter valid Phone Number");
				document.myform.newPhoneId1.focus();
				return false;
			}
			if(s5!=s6){
				alert("Enter valid Phone Number");
				document.myform.newPhoneId1.focus();
				return false;
			}
			if(s7!=s8){
				alert("Enter valid Phone Number");
				document.myform.newPhoneId1.focus();
				return false;
			}
			len7=document.myform.newPhoneId1.value.length;
			strnum = document.myform.newPhoneId1.value;
			var GoodChars = "0123456789 ";
			valid = 1;
			for(j=0;j<len7;j++){
				if(GoodChars.indexOf(strnum.charAt(j))==-1){
					valid=0;
				}
			}
			if(valid!=1){
				alert("Enter valid Phone Number");
				document.myform.newPhoneId1.focus();
				return false;
			}
			if(document.myform.newPhoneId1.value.length>40){
				alert("Phone Number should not exceed 40 characters.");
				document.myform.newPhoneId1.focus();
				return false;
			}
		}

//-----------------------------------------FAX--------------------------------
		if(document.myform.newFaxId1.value!=""){
			var s1=document.myform.newFaxId1.value.indexOf('(');
			var s2=document.myform.newFaxId1.value.indexOf(')');
			var s5=document.myform.newFaxId1.value.indexOf('+');
			var s6=document.myform.newFaxId1.value.lastIndexOf('+');
			var s7=document.myform.newFaxId1.value.indexOf('-');
			var s8=document.myform.newFaxId1.value.lastIndexOf('-');
			var s3=1+s2;
			var s4=1+s1;
			if(s1==s3){
				alert("Enter valid fax");
				document.myform.newFaxId1.focus();
				return false;
			}
			if(s2==s4){
				alert("Enter valid fax");
				document.myform.newFaxId1.focus();
				return false;
			}
			if(s5!==s6){
				alert("Enter valid fax");
				document.myform.newFaxId1.focus();
				return false;
			}
			if(s7!==s8){
				alert("Enter valid fax");
				document.myform.newFaxId1.focus();
				return false;
			}
			len7=document.myform.newFaxId1.value.length;
			strnum = document.myform.newFaxId1.value;
			var GoodChars = "0123456789 ";

			valid = 1;
			for(j=0;j<len7;j++){
				if(GoodChars.indexOf(strnum.charAt(j))==-1){
					valid=0;
				}
			}
			if(valid!=1){
				alert("Enter valid fax");
				document.myform.newFaxId1.focus();
				return false;
			}
			if(document.myform.newFaxId1.value.length>40){
				alert("Enter valid Fax");
				document.myform.newFaxId1.focus();
				return false;
			}
		}
	}*/
//========================== For Company Information =========================================	
	if(chosen=="cmp"){
		chose="";
		le=document.myform.ecmp1.length ;
		for(i=0;i<le;i++){
			if(document.myform.ecmp1[i].checked){
				chose= document.myform.ecmp1[i].value; 
			}
		}
		if(chose==""){
			alert("Select any of the Company Information Details");
			return false;
		}

//=============================== For Registered Company User ==============================
		if(chose=="yes"){
			if(document.myform.existAddCompNameId1.value==""){
				alert("Enter Company Name in Owner Information Section");
				document.myform.existAddCompNameId1.focus();
				return false;
			}
			if(isNameAndCity(document.myform.existAddCompNameId1.value)){
				alert("Enter valid Company Name in Owner Information Section");
				document.myform.existAddCompNameId1.focus();
				return false;
			}
			if((document.myform.existAddCompNameId1.value.indexOf('  ')!=-1)||(document.myform.existAddCompNameId1.value.indexOf(' ')==0)){
				alert("Enter valid Company Name in Owner Information Section");
				document.myform.existAddCompNameId1.focus();
				return false;
			}	
			if(document.myform.existAddCompNameId1.value.length > 80){
				alert("Company Name in Owner Information Section should not exceed 80 characters");
				document.myform.existAddCompNameId1.focus();
				return false;
			}
		}

//=============================== For Nonregistered Company User =============================
		if(chose=="no"){

//=============================== For Company Name ============================================
			if(document.myform.newAddFirstNameIdComp.value==""){
				alert("Enter Company Name in Owner Information Section");
				document.myform.newAddFirstNameIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.newAddFirstNameIdComp.value)){
				alert("Enter valid Company Name in Owner Information Section");
				document.myform.newAddFirstNameIdComp.focus();
				return false;
			}
			if((document.myform.newAddFirstNameIdComp.value.indexOf('  ')!=-1)||(document.myform.newAddFirstNameIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Company Name in Owner Information Section");
				document.myform.newAddFirstNameIdComp.focus();
				return false;
			}	
			if(document.myform.newAddFirstNameIdComp.value.length > 80){
				alert("Company Name in Owner Information Section should not exceed 80 characters");
				document.myform.newAddFirstNameIdComp.focus();
				return false;
			}

//=============================== For Company Address ============================================
			if(document.myform.newAddAddressIdComp.value==""){
				alert("Enter Company Address in Owner Information Section");
				document.myform.newAddAddressIdComp.focus();
				return false;
			}
			if((document.myform.newAddAddressIdComp.value.indexOf('  ')!=-1)||(document.myform.newAddAddressIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Company Address in Owner Information Section");
				document.myform.newAddAddressIdComp.focus();
				return false;
			}	
			if(document.myform.newAddAddressIdComp.value.length > 255){
				alert("Company Address in Owner Information Section should not exceed 255 characters");
				document.myform.newAddAddressIdComp.focus();
				return false;
			}
//__________________________________________city___________________________________________________________________
			if(document.myform.newAddCityIdComp.value==""){
				alert("Enter City Name in Owner Information section");
				document.myform.newAddCityIdComp.focus();
				return false; 
			}
			if((document.myform.newAddCityIdComp.value.indexOf('  ')!=-1)||(document.myform.newAddCityIdComp.value.indexOf(' ')==0)){
				alert("Enter valid City Name in Owner Information Section");
				document.myform.newAddCityIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.newAddCityIdComp.value)){
				alert("Enter valid City Name in Owner Information Section");
				document.myform.newAddCityIdComp.focus();
				return false; 
			}
			if( document.myform.newAddCityIdComp.value.length > 80 ){
				alert("City Name in Owner Information Section should not exceed 80 characters");
				document.myform.newAddCityIdComp.focus();
				return false; 
			}

//___________________________________________________ZipCode_________________________________________________________

			if(document.myform.newAddZipIdComp.value==""){
				alert("Enter Zipcode");
				document.myform.newAddZipIdComp.focus();
				return false;
			}
			if(isZipcode(document.myform.newAddZipIdComp.value)){
				alert("Enter Valid Zipcode");
				document.myform.newAddZipIdComp.focus();
				return false;
			}
			if(document.myform.newAddCountryIdComp.selectedIndex==239){
				if(document.myform.newAddZipIdComp.value.length!=5){
					alert("Enter valid Zip code.");
					document.myform.newAddZipIdComp.focus();
					return false;
				}
				if(document.myform.newAddZipIdComp.value.indexOf(' ')!=-1){
					alert("Enter valid Zip code.");
					document.myform.newAddZipIdComp.focus();
					return false;
				}
			}
			if((document.myform.newAddZipIdComp.value.length<2)||(document.myform.newAddZipIdComp.value.length>20)){
				alert("Zipcode must be above 2 digits and below 20 digits.");
				document.myform.newAddZipIdComp.focus();
				return false;
			}
			if(document.myform.newAddZipIdComp.value.indexOf('.')!=-1){
				alert("Enter valid Zip code.");
				document.myform.newAddZipIdComp.focus();
				return false;
			}

//================================= For Country ===================================
			var cdln = "";
			if(typeof(window.document.myform.newAddCountryIdComp) == 'object'){
				if (window.document.myform.newAddCountryIdComp.value != ""){
					var clid;
					clid = window.document.myform.newAddCountryIdComp[window.document.myform.newAddCountryIdComp.selectedIndex].value;
					cdln = clid;
				}
				if(cdln == "Select One"){
					alert("Please select the Country");
					window.document.myform.newAddCountryIdComp.focus();
					return false;
				}
			}
			if(document.myform.newAddCountryIdComp.selectedIndex == 0 ){
				alert ( "Please select a Country in Company Details." );
				document.myform.newAddCountryIdComp.focus();
				return false;
			}

//================================ For State ====================================
			var edln = "";
			if(typeof(window.document.myform.newAddStateIdComp) == 'object'){
				if (window.document.myform.newAddStateIdComp.value != ""){
					var lid;
					lid = window.document.myform.newAddStateIdComp[window.document.myform.newAddStateIdComp.selectedIndex].value;
					edln = lid;
				}
				if(edln.length == 1){
					alert("Please select the State.");
					window.document.myform.newAddStateIdComp.focus();
					return false;
				}
			} 
			if ( document.myform.newAddStateIdComp.selectedIndex == 0 ){
				alert ( "Please select a State in Company details" );
				document.myform.newAddStateIdComp.focus();
				return false;
			}

//--------------------------------------EMAIL---------------------------------------
			if(document.myform.newAddEmailIdComp.value==""){
				alert("Enter Email ID");
				document.myform.newAddEmailIdComp.focus();
				return false;
			}
			if(!(document.myform.newAddEmailIdComp.value== "")){
				strmail=document.myform.newAddEmailIdComp.value;
				firstat = strmail.indexOf("@");
				lastat = strmail.lastIndexOf("@");
				strmain=strmail.substring(0,firstat);
				strsub=strmail.substring(firstat,document.myform.newAddEmailIdComp.value.length);
				if(strmail.length>120){
					alert("Email ID should not exceed 120 characters");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
					alert("Enter valid Email ");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				if(isnotSpecial(strmain) || isnotSpecial(strsub)){
					alert("Enter valid Email ");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				k=0;
				strlen=strsub.length;
				for(i=0;i<strlen-1;i++){
					if(strsub.charAt(i)=='.'){
						k=k+1;
					}
				}
				if(k>3){
					alert("Enter valid Email ");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				if(firstat==-1 || lastat==-1){
					alert("Enter valid Email" );
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				if(Number(strmain)){
					alert("Enter valid Email ");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				if(firstat != lastat ){
					alert("Enter valid Email ");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
				firstdot=strmail.indexOf(".",firstat);
				lastdot=strmail.lastIndexOf(".");
				if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
					alert("Enter valid Email ");
					document.myform.newAddEmailIdComp.focus();
					return false;
				}
			}

//----------------------IF ENTER PHONE ---------------------------------------
			if(document.myform.newAddPhoneIdComp.value==""){
				alert("Enter Phone Number");
				document.myform.newAddPhoneIdComp.focus();
				return false;
			}
			if(isPhone(document.myform.newAddPhoneIdComp.value)){
				alert("Enter valid Phone Number");
				document.myform.newAddPhoneIdComp.focus();
				return false;
			}
			if(document.myform.newAddPhoneIdComp.value!=""){
				if(document.myform.newAddPhoneIdComp.value.length>40){
					alert("Phone Number should not exceed 40 characters.");
					document.myform.newAddPhoneIdComp.focus();
					return false;
				}
			}

//-----------------------------------------FAX--------------------------------
			if(document.myform.newAddFaxIdComp.value!=""){
				if(isPhone(document.myform.newAddFaxIdComp.value)){
					alert("Enter valid Fax Number");
					document.myform.newAddFaxIdComp.focus();
					return false;
				}
				if(document.myform.newAddFaxIdComp.value.length>40){
					alert("Enter valid Fax");
					document.myform.newAddFaxIdComp.focus();
					return false;
				}
			}
//=============================== For Company Contact Person First Name ============================================
			if(document.myform.cpAddFirstNameIdComp.value==""){
				alert("Enter Contact Person First Name in Owner Information Section");
				document.myform.cpAddFirstNameIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.cpAddFirstNameIdComp.value)){
				alert("Enter valid Contact Person First Name in Owner Information Section");
				document.myform.cpAddFirstNameIdComp.focus();
				return false;
			}
			if((document.myform.cpAddFirstNameIdComp.value.indexOf('  ')!=-1)||(document.myform.cpAddFirstNameIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Contact Person First Name in Owner Information Section");
				document.myform.cpAddFirstNameIdComp.focus();
				return false;
			}	
			if(document.myform.cpAddFirstNameIdComp.value.length > 80){
				alert("Contact Person First Name in Owner Information Section should not exceed 80 characters");
				document.myform.cpAddFirstNameIdComp.focus();
				return false;
			}

//=============================== For Company Contact Person Last Name ============================================
			if(document.myform.cpAddLastNameIdComp.value==""){
				alert("Enter Contact Person Last Name in Owner Information Section");
				document.myform.cpAddLastNameIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.cpAddLastNameIdComp.value)){
				alert("Enter valid Contact Person Last Name in Owner Information Section");
				document.myform.cpAddLastNameIdComp.focus();
				return false;
			}
			if((document.myform.cpAddLastNameIdComp.value.indexOf('  ')!=-1)||(document.myform.cpAddLastNameIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Contact Person Last in Owner Information Section");
				document.myform.cpAddLastNameIdComp.focus();
				return false;
			}	
			if(document.myform.cpAddLastNameIdComp.value.length > 80){
				alert("Contact Person Last in Owner Information Section should not exceed 80 characters");
				document.myform.cpAddLastNameIdComp.focus();
				return false;
			}
		}
	}

//================================== For Check & Card Option =======================
	if(document.myform.totalAmount.value==""){
		alert("Enter the Amount");
		document.myform.totalAmount.focus();
		return false;
	}
	chosen="";
	len = document.myform.r11.length ;
	for(i=0;i<len;i++){
		if(document.myform.r11[i].checked){
			chosen= document.myform.r11[i].value;
		}
	}
	/*if(chosen==""){
		alert("Check any of the Payment Option.");
		return false;
	}*/
	
//--------------------------Check  Number ---------------------------
	if(chosen=="check"){
		if(document.myform.roughVal.value=='yes'){
			if(document.myform.chkBalAmt.value!="" && !Number(document.myform.chkBalAmt.value)){
				//alert('1');
				alert("Enter valid Check enclosed amount in Check Details");
				document.myform.chkBalAmt.focus();
				return false;
			}
			
			if((document.myform.chkBalAmt.value.indexOf('+')!=-1)||(document.myform.chkBalAmt.value.indexOf('-')!=-1)){
				alert("Enter valid Check Amount");
				document.myform.chkBalAmt.focus();
				return false;
			}
			
			if(document.myform.chkBalAmt.value.indexOf('.')>7){
				alert("Enter valid Check enclosed amount in Check Details");
				document.myform.chkBalAmt.focus();
				return false;
			}
			if((document.myform.chkBalAmt.value.indexOf('.'))==-1){
				if(document.myform.chkBalAmt.value.length>7){
					alert("Enter valid Check enclosed amount in Check Details");
					document.myform.chkBalAmt.focus();
					return false;
				}
			}
		}
		if(document.myform.checkNumber.value==""){
			alert("Check Number in Check Details cannot be empty");
			document.myform.checkNumber.focus();
			return false;
		}
		if(document.myform.checkNumber.value.length>16){
			alert("Check Number exceeds the maximum of 16 characters.");
			document.myform.checkNumber.focus();
			return false;
		}
		if((document.myform.checkNumber.value.indexOf('  ')!=-1)||(document.myform.checkNumber.value.indexOf(' ')==0)){
			alert("Enter Valid check number");
			document.myform.checkNumber.focus();
			return false;
		}	
		if(document.myform.checkNumber.value.indexOf('.')!=-1){
			alert("Enter Valid check number");
			document.myform.checkNumber.focus();
			return false;
		}
	 
//=============================== For Check Date ========================
        if(document.myform.roughVal.value=="yes"){
		if(document.myform.checkDate.value==""){
			alert("Select Date in Check Details");
			document.myform.checkDate.focus();
			return false;
		}
		
		var dt=document.myform.checkDate;
		if (isDate(dt.value)==false){
			dt.focus()
			return false;
		}
		
	   }
	   
	  if(document.myform.roughVal.value=="no"){
	if(document.myform.checkDate.value==""){
		alert("Select Date in Check Details");
		document.myform.checkDate.focus();
		return false;
	}
	}
	
	//-----------------------------------------------Check for Previous Date(user)--------------------------------- 
	/*if(document.myform.roughVal.value=="no"){
	if(!(document.myform.checkDate.value=="")){
		var todayDate=new Date();
		var nowDate=todayDate.getDate();
		var nowYear=todayDate.getYear();
		var nowMonth1=todayDate.getMonth();
		var nowMonth=1+nowMonth1;
		
		if(nowDate<10){
			nowDate="0"+nowDate;
		}
	
		if(nowMonth<10){
			nowMonth="0"+nowMonth;
		}
	
		//if(!(document.myform.checkDate.value=="")){
			strdate=document.myform.checkDate.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.checkDate.value.length));
			yyyy1=(Number(yyyy.length));
	
			if(yyyy<nowYear){
				alert("Enter valid Check Date");
				document.myform.checkDate.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter valid Check Date");
				document.myform.checkDate.focus();
				return false;
			}
			if((yyyy==nowYear)&&(dd<nowDate)&&(mm==nowMonth)){
				alert("Enter valid Check Date");
				document.myform.checkDate.focus();
				return false;
			}
		//}
	}	
	}*/
	//===================================In Favor Of====================================

		if(document.myform.bankName.value==""){
			alert("Bank Name cannot be empty.");
			document.myform.bankName.focus();
			return false;
		}
		if(document.myform.bankName.value.length>80){
			alert("Bank Name exceeds the range");
			document.myform.bankName.focus();
			return false;
		}
		if(isPaymentName(document.myform.bankName.value)){
			alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;
		}
		if(document.myform.bankName.value.indexOf(' ')==0){
				alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;
		}
		if(document.myform.bankName.value.indexOf('  ')!==-1){
			alert("Enter valid Bank Name");
			document.myform.bankName.focus();
			return false;

		}

//===================================Name on Check====================================
		if(document.myform.nameCheck.value==""){
			alert("Name on Check cannot be empty.");
			document.myform.nameCheck.focus();
			return false;
		}
		if(document.myform.nameCheck.value.length>80){
			alert("Name on Check exceeds the range");
			document.myform.nameCheck.focus();
			return false;
		}
		if(isPaymentName(document.myform.nameCheck.value)){
			alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
		if(document.myform.nameCheck.value.indexOf(' ')==0){
				alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
		if(document.myform.nameCheck.value.indexOf('  ')!==-1){
				alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
	}
//===========================card details=========================
// ======================= For Card No =============================
	if(chosen=="card"){
		if (document.myform.ccType.selectedIndex == 0 ){
			alert ( "Please select card type." );
			document.myform.ccType.focus();
			return false;
		}
		if(document.myform.ccNumber.value==""){
			alert("Enter Credit Card Number ");
			document.myform.ccNumber.focus();
			return false;
		}
		if(document.myform.ccNumber.value.indexOf(' ')==0){
			alert("Empty space(s) are not allowed for Card Number.");
			document.myform.ccNumber.focus();

			return false;
		}
		if(document.myform.ccNumber.value.indexOf('  ')!==-1){
			alert("Empty space(s) are not allowed for Card Number.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(!isPaymentName(document.myform.ccNumber.value)){
			alert("Alphabets are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(isSpecialCharacter(document.myform.ccNumber.value)){
			alert("Special Characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(!Number(document.myform.ccNumber.value)){
			alert("Alphabets and Special Characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		
//===================== For card type ================
		if(document.myform.ccType.value=="American Express"){
			if(document.myform.ccNumber.value.length!=15){
				alert("Credit card number should be 15 Character");
				document.myform.ccNumber.focus();
				return false;
			}
	     }
		 else{
			if(document.myform.ccNumber.value.length!=16){
				alert("Credit card number should be 16 Character");
				document.myform.ccNumber.focus();
				return false;
			}
		 }
		if(document.myform.ccNumber.value.indexOf('.')!=-1){
			alert("Alphabets and special characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}

//--------for CVV Number -------------------------------------
		if(document.myform.ccCvvid.value!=""){
			if(document.myform.ccCvvid.value.indexOf(' ')==0){
				alert("Empty space(s) are not allowed for CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccCvvid.value.indexOf('  ')!==-1){
				alert("Empty space(s) are not allowed for CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccCvvid.value.indexOf('.')!=-1){
				alert("Enter Valid Cvv Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(isnotInteger(document.myform.ccCvvid.value)){
				alert("Enter Valid Cvv Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccType.value=="American Express"){
			if(document.myform.ccCvvid.value.length!=4){
				alert("Enter valid CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			}
			if(document.myform.ccType.value=="Visa" || document.myform.ccType.value=="MasterCard"){
			if(document.myform.ccCvvid.value.length!=3){
				alert("Enter valid CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			}			
			
		}
//----for Print Name on Card ----------------------------------------
		if(document.myform.ccName.value==""){
			alert("Print Name cannot be empty");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.length>80){
			alert("Print Name exceeds the range");
			document.myform.ccName.focus();
			return false;
		}
	
		if(isPaymentName(document.myform.ccName.value)){
			alert("Enter valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.indexOf(' ')==0){
			alert("Enter Valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.indexOf('  ')!==-1){
			alert("Enter Valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}

//---for Expiry month drop down --------------------------------

		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.myform.ccExpMonth.selectedIndex == 0 ){
			alert ( "Please select a Month." );
			document.myform.ccExpMonth.focus();
			return false;
		}
	
		if (document.myform.ccExpYear.value==theYear){
			 if(document.myform.ccExpMonth.value<theMonth){
				alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
				document.myform.ccExpMonth.focus();
				return(false);   
			}
		}

//-----------for Expiry Year drop down
		if(document.myform.ccExpYear.selectedIndex == 0 ){
			alert ( "Please select a year." );
			document.myform.ccExpYear.focus();
			return false;
		}
		if (document.myform.ccExpYear.value<theYear){
			alert("You have selected an Expired Year in card details. Please Select Valid Expiration Year.");
			document.myform.ccExpYear.focus();
			return false;   
		}
	}
	if(document.myform.hidVal.value=="userLogin"){
	if (document.myform.ccType.selectedIndex == 0 ){
			alert ( "Please select card type." );
			document.myform.ccType.focus();
			return false;
		}
		if(document.myform.ccNumber.value==""){
			alert("Enter Credit Card Number ");
			document.myform.ccNumber.focus();
			return false;
		}
		if(document.myform.ccNumber.value.indexOf(' ')==0){
			alert("Empty space(s) are not allowed for Card Number.");
			document.myform.ccNumber.focus();

			return false;
		}
		if(document.myform.ccNumber.value.indexOf('  ')!==-1){
			alert("Empty space(s) are not allowed for Card Number.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(!isPaymentName(document.myform.ccNumber.value)){
			alert("Alphabets are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(isSpecialCharacter(document.myform.ccNumber.value)){
			alert("Special Characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		if(!Number(document.myform.ccNumber.value)){
			alert("Alphabets and Special Characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}
		
//===================== For card type ================
		if(document.myform.ccType.value=="American Express"){
			if(document.myform.ccNumber.value.length!=15){
				alert("Credit card number should be 15 Character");
				document.myform.ccNumber.focus();
				return false;
			}
	     }
		 else{
			if(document.myform.ccNumber.value.length!=16){
				alert("Credit card number should be 16 Character");
				document.myform.ccNumber.focus();
				return false;
			}
		 }
		if(document.myform.ccNumber.value.indexOf('.')!=-1){
			alert("Alphabets and special characters are not allowed.");
			document.myform.ccNumber.focus();
			return false;
		}

//--------for CVV Number -------------------------------------
		
			if(document.myform.ccCvvid.value==""){
				alert("Enter CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccType.value=="American Express"){
			if(document.myform.ccCvvid.value.length!=4){
				alert("Enter valid CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			}
			if(document.myform.ccType.value=="Visa" || document.myform.ccType.value=="MasterCard"){
			if(document.myform.ccCvvid.value.length!=3){
				alert("Enter valid CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			}
			if(document.myform.ccCvvid.value.indexOf(' ')==0){
				alert("Enter Valid Cvv Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccCvvid.value.indexOf('  ')!==-1){
				alert("Enter Valid Cvv Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(document.myform.ccCvvid.value.indexOf('.')!=-1){
				alert("Enter Valid Cvv Number");
				document.myform.ccCvvid.focus();
				return false;
			}
			if(isnotInteger(document.myform.ccCvvid.value)){
				alert("Enter Valid Cvv Number");
				document.myform.ccCvvid.focus();
				return false;
			}
		
//----for Print Name on Card ----------------------------------------
		if(document.myform.ccName.value==""){
			alert("Print Name cannot be empty");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.length>80){
			alert("Print Name exceeds the range");
			document.myform.ccName.focus();
			return false;
		}
	
		if(isPaymentName(document.myform.ccName.value)){
			alert("Enter valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.indexOf(' ')==0){
			alert("Enter Valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}
	
		if(document.myform.ccName.value.indexOf('  ')!==-1){
			alert("Enter Valid Card Name.");
			document.myform.ccName.focus();
			return false;
		}

//---for Expiry month drop down --------------------------------

		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.myform.ccExpMonth.selectedIndex == 0 ){
			alert ( "Please select a Month." );
			document.myform.ccExpMonth.focus();
			return false;
		}
	
		if (document.myform.ccExpYear.value==theYear){
			 if(document.myform.ccExpMonth.value<theMonth){
				alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
				document.myform.ccExpMonth.focus();
				return(false);   
			}
		}

//-----------for Expiry Year drop down
		if(document.myform.ccExpYear.selectedIndex == 0 ){
			alert ( "Please select a year." );
			document.myform.ccExpYear.focus();
			return false;
		}
		if (document.myform.ccExpYear.value<theYear){
			alert("You have selected an Expired Year in card details. Please Select Valid Expiration Year.");
			document.myform.ccExpYear.focus();
			return false;   
		}	
	}
	return true;
}

function copyRiderOwner(){
	if(document.getElementById('addR_num').value == "" && document.myform.regAddFor[0].checked){
		document.getElementById('ridAddUser').style.display = "none";
		alert("Please enter relevant Rider Information!");
		chosen="";
		len=document.myform.regAddFor.length;
		for(i=0;i<len;i++){
			if(document.myform.regAddFor[i].checked){
				document.myform.regAddFor[i].checked=false;
			}
		}
	}else{
		switchDiv('ridAddUser');
		if(document.getElementById('regAddFor').value != 'mem1'){
			document.myform.ownerUseaNo2_id.value = "";
			document.myform.ownerFname2_id.value = "";
			document.myform.ownerLname2_id.value = "";
			document.myform.ownerPhone2_id.value = "";
		}
		else{	
			document.myform.ownerUseaNo2_id.value = document.myform.addR_num.value;
			document.myform.ownerFname2_id.value = document.myform.additionalRider.value;
			document.myform.ownerLname2_id.value = document.myform.lastRider.value;
			document.myform.ownerPhone2_id.value = document.myform.addRPhone.value;	
		}
	}
}
 
function resetRiderOwner(){
	if(document.getElementById('addR_num').value == "" && document.myform.regAddFor[0].checked){
		document.getElementById('ridAddUser').style.display = "none";
		chosen="";
		len=document.myform.regAddFor.length;
		for(i=0;i<len;i++){
			if(document.myform.regAddFor[i].checked){
				document.myform.regAddFor[i].checked=false;
			}
		}
	}
	else if(document.getElementById('addR_num').value != "" && document.myform.regAddFor[0].checked){
		    document.myform.ownerUseaNo2_id.value = document.myform.addR_num.value;
			document.myform.ownerFname2_id.value = document.myform.additionalRider.value;
			document.myform.ownerLname2_id.value = document.myform.lastRider.value;
			document.myform.ownerPhone2_id.value = document.myform.addRPhone.value;	
	}
}


/**
 * DHTML date validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strMonth=dtStr.substring(0,pos1)
	var strDay=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("The date format should be : mm/dd/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date")
		return false
	}
return true
}


 