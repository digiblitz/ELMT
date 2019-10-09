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
			//alert('Hi!');
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
	check="`~!@#$%^&*()_+=|{}[];:/<>?"+"\\"+"\""+"\"";
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

function checkClear(){
	document.myform.checkNumber.value="";
	document.myform.checkDate.value="";
	document.myform.bankName.value="";
	document.myform.nameCheck.value="";	
}

function cardClear(){
	document.myform.ccNumber.value="";
	document.myform.ccCvvid.value="";
	document.myform.ccType.selectedIndex=0;
	document.myform.ccName.value="";
	document.myform.ccExpMonth.selectedIndex=0;
	document.myform.ccExpYear.selectedIndex=0;
}

function clearRiderdetails(){
	document.myform.ownerUseaNo_id.value="";
	document.myform.ownerFname_id.value="";
	document.myform.ownerLname_id.value="";
	document.myform.ownerPhone_id.value="";
}

function clearHLCdetails(){
	document.myform.userNameId.value="";
	document.myform.firstNameId.value="";
	document.myform.lastNameId.value="";
	document.myform.phoneId.value="";
	document.myform.userDetId.value="";
}

function clearNotHLCdetails(){
	document.myform.userNameId1.value="";
	document.myform.firstNameId1.value="";
	document.myform.lastNameId1.value="";
	document.myform.phoneId1.value="";
	document.myform.userDetId.value="";
}

function clearNewdetails(){
	document.myform.newFirstNameId.value="";
	document.myform.newLastNameId.value="";
	document.myform.newBirthmonthId.value="";
	document.myform.newBirthdayId.value="";
	document.myform.newBirthyearId.value="";
	document.myform.sexId[0].checked=false;
	document.myform.sexId[1].checked=false;
	document.myform.newAddressId.value="";
	document.myform.newStateId.selectedIndex=0;
	document.myform.newCityId.value="";
	document.myform.newZipId.value="";
	document.myform.newEmailId.value="";
	document.myform.newPhoneId.value="";
	document.myform.newFaxId.value="";
	document.myform.userDetId.value="";
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

function clearNewdetails1(){
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
}

function comRegClear(){
	document.myform.existCompNameId1.value="";
	document.myform.cpfirstNameId1.value="";
	document.myform.cplastNameId1.value="";
	document.myform.ecmpPhoneId1.value="";
}

function comRegClear1(){
	document.myform.existAddCompNameId1.value="";
	document.myform.cpAddfirstNameId1.value="";
	document.myform.cpAddlastNameId1.value="";
	document.myform.ecmpAddPhoneId1.value="";
}

function comNewClear(){
	document.myform.newFirstNameIdComp.value="";
	document.myform.newAddressIdComp.value="";
	document.myform.newCountryIdComp.value="USA";
	document.myform.newStateIdComp.selectedIndex=0;
	document.myform.newCityIdComp.value="";
	document.myform.newZipIdComp.value="";
	document.myform.newEmailIdComp.value="";
	document.myform.newPhoneIdComp.value="";
	document.myform.newFaxIdComp.value="";
	document.myform.cpFirstNameIdComp.value="";
	document.myform.cpLastNameIdComp.value="";
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

function comRadioClear(){
	for(i=0;i<document.myform.ecmp.length;i++){
		document.myform.ecmp[i].checked=false;
	}
}

function comRadioClear1(){
	for(i=0;i<document.myform.ecmp1.length;i++){
		document.myform.ecmp1[i].checked=false;
	}
}

 
function myValidate(){

	stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
	stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";

//======================= Owner Information Section =========================================
	chosen="";
	len=document.myform.rself.length;
	for(i=0;i<len;i++){
		if(document.myform.rself[i].checked){
			chosen= document.myform.rself[i].value;
		}
	}
	if(chosen==""){
		alert("Check any of the Trainer Information Section");
		return false;
	}
	
	if(chosen=="mem1"){
		if(document.myform.ownerUseaNo_id.value==""){
			alert("Enter Rider Id in Trainer Information Section");
			document.myform.ownerUseaNo_id.focus();
			return false;
		}
		 
		 if(isnotSpecial(document.myform.ownerUseaNo_id.value)){
			alert("Enter valid Rider Id in Trainer Information Section");
			document.myform.ownerUseaNo_id.focus();
			return false;
		}
		if((document.myform.ownerUseaNo_id.value.indexOf('  ')!=-1)||(document.myform.ownerUseaNo_id.value.indexOf(' ')==0)){
			alert("Enter valid Rider Id in Trainer Information Section");
			document.myform.ownerUseaNo_id.focus();
			return false;
		}
		if(document.myform.ownerUseaNo_id.value.length > 80){
			alert("Enter valid Rider Id in Trainer Information Section");
			document.myform.ownerUseaNo_id.focus();
			return false;
		}
	}
	
	if(chosen=="rid1"){
		if(document.myform.userNameId.value==""){
			alert("Enter Member Id in Trainer Information Section");
			document.myform.userNameId.focus();
			return false;
		}
		if(isnotSpecial(document.myform.userNameId.value)){
			alert("Enter valid Member Id in Trainer Information Section");
			document.myform.userNameId.focus();
			return false;
		}
		if((document.myform.userNameId.value.indexOf('  ')!=-1)||(document.myform.userNameId.value.indexOf(' ')==0)){
			alert("Enter valid Member Id in Trainer Information Section");
			document.myform.userNameId.focus();
			return false;
		}
		if(document.myform.userNameId.value.length > 80){
			alert("Enter valid Member Id in Trainer Information Section");
			document.myform.userNameId.focus();
			return false;
		}
	}
	if(chosen=="acc1"){
		if(document.myform.userNameId1.value==""){
			alert("Enter User Name in Trainer Information Section");
			document.myform.userNameId1.focus();
			return false;
		}
		if(isHorseName(document.myform.userNameId1.value)){
			alert("Enter valid User Name in Trainer Information Section");
			document.myform.userNameId1.focus();
			return false;
		}
		if((document.myform.userNameId1.value.indexOf('  ')!=-1)||(document.myform.userNameId1.value.indexOf(' ')==0)){
			alert("Enter valid User Name in Trainer Information Section");
			document.myform.userNameId1.focus();
			return false;
		}
		if(document.myform.userNameId1.value.length>80){
			alert("Enter valid User Name in Trainer Information Section");
			document.myform.userNameId1.focus();
			return false;
		}
	}

	  
//========================== For Company Information =========================================	
	if(chosen=="cmp1"){
		chose="";
		le=document.myform.ecmp.length ;
		for(i=0;i<le;i++){
			if(document.myform.ecmp[i].checked){
				chose= document.myform.ecmp[i].value; 
			}
		}
		if(chose==""){
			alert("Select any of the options in Company Information Details");
			return false;
		}

//=============================== For Registered Company User ==============================
		if(chose=="yes"){
			if(document.myform.existCompNameId1.value==""){
				alert("Enter Company Name in Trainer Information Section");
				document.myform.existCompNameId1.focus();
				return false;
			}
			if(isNameAndCity(document.myform.existCompNameId1.value)){
				alert("Enter valid Company Name in Trainer Information Section");
				document.myform.existCompNameId1.focus();
				return false;
			}
			if((document.myform.existCompNameId1.value.indexOf('  ')!=-1)||(document.myform.existCompNameId1.value.indexOf(' ')==0)){
				alert("Enter valid Company Name in Trainer Information Section");
				document.myform.existCompNameId1.focus();
				return false;
			}	
			if(document.myform.existCompNameId1.value.length > 80){
				alert("Company Name in Trainer Information Section should not exceed 80 characters");
				document.myform.existCompNameId1.focus();
				return false;
			}
		}

//=============================== For Nonregistered Company User =============================
		if(chose=="no"){

//=============================== For Company Name ============================================
			if(document.myform.newFirstNameIdComp.value==""){
				alert("Enter Company Name in Trainer Information Section");
				document.myform.newFirstNameIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.newFirstNameIdComp.value)){
				alert("Enter valid Company Name in Trainer Information Section");
				document.myform.newFirstNameIdComp.focus();
				return false;
			}
			if((document.myform.newFirstNameIdComp.value.indexOf('  ')!=-1)||(document.myform.newFirstNameIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Company Name in Trainer Information Section");
				document.myform.newFirstNameIdComp.focus();
				return false;
			}	
			if(document.myform.newFirstNameIdComp.value.length > 80){
				alert("Company Name in Trainer Information Section should not exceed 80 characters");
				document.myform.newFirstNameIdComp.focus();
				return false;
			}

//=============================== For Company Address ============================================
			if(document.myform.newAddressIdComp.value==""){
				alert("Enter Company Address in Owner Information Section");
				document.myform.newAddressIdComp.focus();
				return false;
			}
			if((document.myform.newAddressIdComp.value.indexOf('  ')!=-1)||(document.myform.newAddressIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Company Address in Trainer Information Section");
				document.myform.newAddressIdComp.focus();
				return false;
			}	
			if(document.myform.newAddressIdComp.value.length > 255){
				alert("Company Address in Trainer Information Section should not exceed 255 characters");
				document.myform.newAddressIdComp.focus();
				return false;
			}

//================================= For Country ===================================
			var cdln = "";
			if(typeof(window.document.myform.newCountryIdComp) == 'object'){
				if (window.document.myform.newCountryIdComp.value != ""){
					var clid;
					clid = window.document.myform.newCountryIdComp[window.document.myform.newCountryIdComp.selectedIndex].value;
					cdln = clid;
				}
				if(cdln == "Select One"){
					alert("Please select the Country");
					window.document.myform.newCountryIdComp.focus();
					return false;
				}
			}
			if(document.myform.newCountryIdComp.selectedIndex == 0 ){
				alert ( "Please select a Country in Company Details." );
				document.myform.newCountryIdComp.focus();
				return false;
			}

//================================ For State ====================================
			var edln = "";
			if(typeof(window.document.myform.newStateIdComp) == 'object'){
				if (window.document.myform.newStateIdComp.value != ""){
					var lid;
					lid = window.document.myform.newStateIdComp[window.document.myform.newStateIdComp.selectedIndex].value;
					edln = lid;
				}
				if(edln.length == 1){
					alert("Please select the State.");
					window.document.myform.newStateIdComp.focus();
					return false;
				}
			} 
			if ( document.myform.newStateIdComp.selectedIndex == 0 ){
				alert ( "Please select a State in Company details" );
				document.myform.newStateIdComp.focus();
				return false;
			}
//__________________________________________city___________________________________________________________________
			if(document.myform.newCityIdComp.value==""){
				alert("Enter City Name in Trainer Information section");
				document.myform.newCityIdComp.focus();
				return false; 
			}
			if((document.myform.newCityIdComp.value.indexOf('  ')!=-1)||(document.myform.newCityIdComp.value.indexOf(' ')==0)){
				alert("Enter valid City Name in Trainer Information Section");
				document.myform.newCityIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.newCityIdComp.value)){
				alert("Enter valid City Name in Trainer Information Section");
				document.myform.newCityIdComp.focus();
				return false; 
			}
			if( document.myform.newCityIdComp.value.length > 80 ){
				alert("City Name in Trainer Information Section should not exceed 80 characters");
				document.myform.newCityIdComp.focus();
				return false; 
			}

//___________________________________________________ZipCode_________________________________________________________

			if(document.myform.newZipIdComp.value==""){
				alert("Enter Zipcode");
				document.myform.newZipIdComp.focus();
				return false;
			}
			if(isZipcode(document.myform.newZipIdComp.value)){
				alert("Enter Valid Zipcode");
				document.myform.newZipIdComp.focus();
				return false;
			}
			if(document.myform.newCountryIdComp.selectedIndex==239){
				if(document.myform.newZipIdComp.value.length!=5){
					alert("Enter valid Zip code.");
					document.myform.newZipIdComp.focus();
					return false;
				}
				if(document.myform.newZipIdComp.value.indexOf(' ')!=-1){
					alert("Enter valid Zip code.");
					document.myform.newZipIdComp.focus();
					return false;
				}
			}
			if((document.myform.newZipIdComp.value.length<2)||(document.myform.newZipIdComp.value.length>20)){
				alert("Zipcode must be above 2 digits and below 20 digits.");
				document.myform.newZipIdComp.focus();
				return false;
			}
			if(document.myform.newZipIdComp.value.indexOf('.')!=-1){
				alert("Enter valid Zip code.");
				document.myform.newZipIdComp.focus();
				return false;
			}
//--------------------------------------EMAIL---------------------------------------
			if(document.myform.newEmailIdComp.value==""){
				alert("Enter Email ID");
				document.myform.newEmailIdComp.focus();
				return false;
			}
			if(!(document.myform.newEmailIdComp.value== "")){
				strmail=document.myform.newEmailIdComp.value;
				firstat = strmail.indexOf("@");
				lastat = strmail.lastIndexOf("@");
				strmain=strmail.substring(0,firstat);
				strsub=strmail.substring(firstat,document.myform.newEmailIdComp.value.length);
				if(strmail.length>120){
					alert("Email ID should not exceed 120 characters");
					document.myform.newEmailIdComp.focus();
					return false;
				}
				if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
					alert("Enter valid Email ");
					document.myform.newEmailIdComp.focus();
					return false;
				}
				if(isnotSpecial(strmain) || isnotSpecial(strsub)){
					alert("Enter valid Email ");
					document.myform.newEmailIdComp.focus();
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
					document.myform.newEmailIdComp.focus();
					return false;
				}
				if(firstat==-1 || lastat==-1){
					alert("Enter valid Email" );
					document.myform.newEmailIdComp.focus();
					return false;
				}
				if(Number(strmain)){
					alert("Enter valid Email ");
					document.myform.newEmailIdComp.focus();
					return false;
				}
				if(firstat != lastat ){
					alert("Enter valid Email ");
					document.myform.newEmailIdComp.focus();
					return false;
				}
				firstdot=strmail.indexOf(".",firstat);
				lastdot=strmail.lastIndexOf(".");
				if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
					alert("Enter valid Email ");
					document.myform.newEmailIdComp.focus();
					return false;
				}
			}

//----------------------IF ENTER PHONE ---------------------------------------
			if(document.myform.newPhoneIdComp.value==""){
				alert("Enter Phone Number");
				document.myform.newPhoneIdComp.focus();
				return false;
			}
			
			if((document.myform.newPhoneIdComp.value.indexOf('  ')!=-1)||(document.myform.newPhoneIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Phone Number in Owner Information Section");
				document.myform.newPhoneIdComp.focus();
				return false;
			}	
			if(isPhone(document.myform.newPhoneIdComp.value)){
				alert("Enter Valid Phone Number");
				document.myform.newPhoneIdComp.focus();
				return false;
			}
			if(document.myform.newPhoneIdComp.value!=""){
				if(document.myform.newPhoneIdComp.value.length>40){
					alert("Phone Number should not exceed 40 characters.");
					document.myform.newPhoneIdComp.focus();
					return false;
				}
			}

//-----------------------------------------FAX--------------------------------
			if(document.myform.newFaxIdComp.value!=""){
				if(isPhone(document.myform.newFaxIdComp.value)){
					alert("Enter Valid Fax Number");
					document.myform.newFaxIdComp.focus();
					return false;
				}
				if(document.myform.newFaxIdComp.value.length>40){
					alert("Enter valid Fax");
					document.myform.newFaxIdComp.focus();
					return false;
				}
			}
//=============================== For Company Contact Person First Name ============================================
			if(document.myform.cpFirstNameIdComp.value==""){
				alert("Enter Contact Person First Name in Trainer Information Section");
				document.myform.cpFirstNameIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.cpFirstNameIdComp.value)){
				alert("Enter valid Contact Person First Name in Trainer Information Section");
				document.myform.cpFirstNameIdComp.focus();
				return false;
			}
			if((document.myform.cpFirstNameIdComp.value.indexOf('  ')!=-1)||(document.myform.cpFirstNameIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Contact Person First Name in Trainer Information Section");
				document.myform.cpFirstNameIdComp.focus();
				return false;
			}	
			if(document.myform.cpFirstNameIdComp.value.length > 80){
				alert("Contact Person First Name in Trainer Information Section should not exceed 80 characters");
				document.myform.cpFirstNameIdComp.focus();
				return false;
			}

//=============================== For Company Contact Person Last Name ============================================
			if(document.myform.cpLastNameIdComp.value==""){
				alert("Enter Contact Person Last Name in Trainer Information Section");
				document.myform.cpLastNameIdComp.focus();
				return false;
			}
			if(isNameAndCity(document.myform.cpLastNameIdComp.value)){
				alert("Enter valid Contact Person Last Name in Trainer Information Section");
				document.myform.cpLastNameIdComp.focus();
				return false;
			}
			if((document.myform.cpLastNameIdComp.value.indexOf('  ')!=-1)||(document.myform.cpLastNameIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Contact Person Last in Trainer Information Section");
				document.myform.cpLastNameIdComp.focus();
				return false;
			}	
			if(document.myform.cpLastNameIdComp.value.length > 80){
				alert("Contact Person Last in Trainer Information Section should not exceed 80 characters");
				document.myform.cpLastNameIdComp.focus();
				return false;
			}
		}
	}
	return true;
}


//-------------------------------- User status validation Ajax Script ------------------------------------------------
var httpRequest;
function usrStat(){
if(document.myform.newFirstNameIdComp.value!="" && document.myform.newFirstNameIdComp.value.indexOf(' ')!=0){
   var chsUserName=document.myform.newFirstNameIdComp.value;
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "UsrSignupAjax.do?process=checkusrnam&chsUserName="+chsUserName; 
        if (window.ActiveXObject){ 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest){ 
            httpRequest = new XMLHttpRequest(); 
        } 
        httpRequest.open("GET", url, true); 
        httpRequest.onreadystatechange =processUser; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUser(){ 
   
        if (httpRequest.readyState == 4){ 
            if(httpRequest.status == 200){ 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
                 updateHTML(salutationXML); 
            } 
            else{ 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML){ 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
		if(salutationText != "false"){
			alert("Company Name already Exists Chose Another !");
			document.myform.newFirstNameIdComp.value="";
			document.myform.newFirstNameIdComp.focus();
		}
    }  

 
