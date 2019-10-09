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

function clearNewdetails1(){
	document.myform.addUserDetId.value="";
	
}

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

function popupCal(val) {
      if(val==1)
	      document.myform.dateImported.value = "StartDate1";

      if(val==2)
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
chosen="";
	len=document.myform.regAddFor.length;
	for(i=0;i<len;i++){
		if(document.myform.regAddFor[i].checked){
			chosen= document.myform.regAddFor[i].value;
		}
	}
    if(document.myform.addR_num.value == "" && chosen == "" ){
		alert("Please select any one of the options");
		return false;
	}
		
	if(isnotInteger(document.myform.addR_num.value)){
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
			if((document.myform.newAddPhoneIdComp.value.indexOf('  ')!=-1)||(document.myform.newAddPhoneIdComp.value.indexOf(' ')==0)){
				alert("Enter valid Phone Number in Owner Information Section");
				document.myform.newAddPhoneIdComp.focus();
				return false;
			}
			if(isPhone(document.myform.newAddPhoneIdComp.value)){
				alert("Enter Valid Phone Number");
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
					alert("Enter Valid Fax Number");
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
	return true;
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
