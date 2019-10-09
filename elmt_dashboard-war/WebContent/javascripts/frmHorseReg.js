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
var str="";

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

function isZipcode(str){
	zip1="0123456789";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(zip1.indexOf(str.charAt(j))!=-1){
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

//========================== function for Special Character =====================================
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'";
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

 
function format(obj, decimal) {
	//alert("obj" +obj);
 //decimal  - the number of decimals after the digit from 0 to 3
//-- Returns the passed number as a string in the xxx,xxx.xx format.
	   anynum=eval(obj);
	   divider =10;
	   switch(decimal){
			case 0:
				divider =1;
				break;
			case 1:
				divider =10;
				break;
			case 2:
				divider =100;
				break;
			default:  	 //for 3 decimal places
				divider =1000;
		}

	   workNum=Math.abs((Math.round(anynum*divider)/divider));

	   workStr=""+workNum

	   if (workStr.indexOf(".")==-1){workStr+="."}

	   dStr=workStr.substr(0,workStr.indexOf("."));dNum=dStr-0
	   pStr=workStr.substr(workStr.indexOf("."))

	   while (pStr.length-1< decimal){pStr+="0"}

	   if(pStr =='.') pStr ='';

	   //--- Adds a comma in the thousands place.    
	   if (dNum>=1000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000))+","+dStr.substring(dLen-3,dLen)
	   }

	   //-- Adds a comma in the millions place.
	   if (dNum>=1000000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000000))+","+dStr.substring(dLen-7,dLen)
	   }
	   retval = dStr + pStr
	   //-- Put numbers in parentheses if negative.
	   if (anynum<0) {retval="("+retval+")";}

	  
	//You could include a dollar sign in the return value.
	  //retval =  "$"+retval
	  //alert("retval" + retval);
	  return retval;
 }


function myValidate(){

	stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
	stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";
	
//===================================== For Horse Name ========================================
 
	  
	 if(document.myform.horseStat.value=="yes"){
		  
			if(document.myform.ownerName.value.length>80){
				alert("Horse Name in Horse Information Section should not exceed 80 characters");
				document.myform.ownerName.focus();
				return false;
			}
			if(isHorseName(document.myform.ownerName.value)){
				alert("Enter valid Horse Name in Horse Information Section");
				document.myform.ownerName.focus();
				return false;
			}
	 
 
//====================================== For Registered Name ===========================================
	 
	if(document.myform.state2.value.length>80){
		alert("Registered Name in Horse Information Section should not exceed 80 characters");
		document.myform.state2.focus();
		return false;
	}
	if(isHorseName(document.myform.state2.value)){
		alert("Enter valid Registered Name in Horse Information Section");
		document.myform.state2.focus();
		return false;
	}
	}
	 
 if(document.myform.reqVal.value=="yes"){
	chosen1="";
	len1=document.myform.adoId.length;
	
	for(i=0;i<len1;i++){
		if(document.myform.adoId[i].checked){
			chosen1= document.myform.adoId[i].value;
		}
	} 
	if(chosen1=="yes"){
		chosen="";
		len=document.myform.rself1.length;
		for(i=0;i<len;i++){
			if(document.myform.rself1[i].checked){
				chosen= document.myform.rself1[i].value;
			}
		}
		if(chosen==""){
			alert("Check any of the Owner Information Section");
			return false;
		}
		if(chosen=="mem"){
			if(document.myform.ownerUseaNoTwo.value==""){
				alert("Enter Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo.focus();
				return false;
			}
			if(isnotSpecial(document.myform.ownerUseaNoTwo.value)){
				alert("Enter valid Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo.focus();
				return false;
			}
			if((document.myform.ownerUseaNoTwo.value.indexOf('  ')!=-1)||(document.myform.ownerUseaNoTwo.value.indexOf(' ')==0)){
				alert("Enter valid Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo.focus();
				return false;
			}
			if(document.myform.ownerUseaNoTwo.value.length > 80){
				alert("Enter valid Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo.focus();
				return false;
			}
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
			if(isnotSpecial(document.myform.userNameId3.value)){
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
			if(document.myform.newAddPhoneIdComp.value.length>40){
				alert("Phone Number should not exceed 40 characters.");
				document.myform.newAddPhoneIdComp.focus();
				return false;
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
	}
	 
  
	}
	return true;
}

function resetRiderOwner(){
	//alert(document.getElementById('regFor').value);
	if(document.getElementById('riderUseaNo_id').value == "" && document.myform.rself[0].checked){
		document.getElementById('ridUser').style.display = "none";
		chosen="";
		len=document.myform.rself.length;
		for(i=0;i<len;i++){
			if(document.myform.rself[i].checked){
				document.myform.rself[i].checked=false;
			}
		}
	}
	else if(document.getElementById('riderUseaNo_id').value != "" && document.myform.rself[0].checked){
		//alert(document.myform.rself[0].checked);
		document.myform.ownerUseaNo_id.value = document.myform.riderUseaNo_id.value;
		document.myform.ownerFname_id.value = document.myform.riderFname_id.value;
		document.myform.ownerLname_id.value = document.myform.riderLname_id.value;
		document.myform.ownerPhone_id.value = document.myform.riderPhone_id.value;	
	}
}
 

function copyRiderOwnerTwo(){
	if(document.getElementById('riderUseaNo_id').value == ""){
		document.getElementById('ridAddUser').style.display = "none";
		alert("Please enter relevant Rider Information!");
	}
	else{
		switchDiv('companyUser');
		if(document.getElementById('regFor').value != 'mem'){
			document.myform.ownerUseaNo2_id.value = "";
			document.myform.ownerFname2_id.value = "";
			document.myform.ownerLname2_id.value = "";
			document.myform.ownerPhone2_id.value = "";
		}
		else{
			document.myform.ownerUseaNo2_id.value = document.myform.riderUseaNo_id.value;
			document.myform.ownerFname2_id.value = document.myform.riderFname_id.value;
			document.myform.ownerLname2_id.value = document.myform.riderLname_id.value;
			document.myform.ownerPhone2_id.value = document.myform.riderPhone_id.value;
		}		
	}
	return true;
}

//-------------------------------- User status validation Ajax Script ------------------------------------------------
var httpRequest;
function usrStat(){
if(document.myform.newAddFirstNameIdComp.value!="" && document.myform.newAddFirstNameIdComp.value.indexOf(' ')!=0){
   var chsUserName=document.myform.newAddFirstNameIdComp.value;
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
			document.myform.newAddFirstNameIdComp.value="";
			document.myform.newAddFirstNameIdComp.focus();
		}
    } 
