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
function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmAdvMag.elements[chkBxId].checked == true){
				document.frmAdvMag.elements[txtBxId].disabled = false;	
		}else {
				document.frmAdvMag.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){	
		if(document.frmAdvMag.elements[radBtnId].checked == true){
				document.frmAdvMag.elements[txtBxId].disabled = false;	
		}else{
				document.frmAdvMag.elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.frmAdvMag.elements[radBtnNewId].checked == true){
				document.frmAdvMag.elements[txtBxId].disabled = "disabled";	
		}
}
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmAdvMag.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function isAlpha(str){
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

function isnotAlpha(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`0123456789;:"+"\\"+"\'";
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


function phoneCheck(str1){
	stringCheck1="`~!@#$%^&*|:;{}[]<>?/=~.,'`;:"+"\\"+"\'";
	f2=1;
	for(j=0;j<str1.length;j++){
		if(stringCheck1.indexOf(str1.charAt(j))!=-1){
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
function checkClear(){
	document.frmAdvMag.ccNumber.disabled=false;
	document.frmAdvMag.ccCvvid.disabled=false;
	document.frmAdvMag.ccType.disabled=false;
	document.frmAdvMag.ccName.disabled=false;
	document.frmAdvMag.ccExpMonth.disabled=false;
	document.frmAdvMag.ccExpYear.disabled=false;
	document.frmAdvMag.checkNumber.disabled=true;
	document.frmAdvMag.checkDate.disabled=true;
	document.frmAdvMag.bankName.disabled=true;
	
	document.frmAdvMag.checkNumber.value="";
	document.frmAdvMag.checkDate.value="";
	document.frmAdvMag.bankName.value="";	

}

function cardClear(){
	document.frmAdvMag.ccNumber.disabled=true;
	document.frmAdvMag.ccCvvid.disabled=true;
	document.frmAdvMag.ccType.disabled=true;
	document.frmAdvMag.ccName.disabled=true;
	document.frmAdvMag.ccExpMonth.disabled=true;
	document.frmAdvMag.ccExpYear.disabled=true;
	document.frmAdvMag.checkNumber.disabled=false;
	document.frmAdvMag.checkDate.disabled=false;
	document.frmAdvMag.bankName.disabled=false;	
	
	document.frmAdvMag.ccNumber.value="";
	document.frmAdvMag.ccCvvid.value="";
	document.frmAdvMag.ccType.selectedIndex=0;
	document.frmAdvMag.ccName.value="";
	document.frmAdvMag.ccExpMonth.selectedIndex=0;
	document.frmAdvMag.ccExpYear.selectedIndex=0;

}

function onPayCheck(){
//================================== For Check & Card Option =======================

	chosen2="";
	len2 = document.frmAdvMag.r11.length ;
	for(i=0;i<len2;i++){
		if(document.frmAdvMag.r11[i].checked){
			chosen2= document.frmAdvMag.r11[i].value;
		}
	}

	if(chosen2==""){
		alert("Check any of the Payment Option.");
		return false;
	}

//--------------------------Check  Number ---------------------------

	if(chosen2=="check"){
		if(document.frmAdvMag.checkNumber.value==""){
			alert("Check Number in Check Details cannot be empty");
			document.frmAdvMag.checkNumber.focus();
			return false;
		}
	if(isAlpha(document.frmAdvMag.checkNumber.value)){
		alert("Enter valid Check Number.");
		document.frmAdvMag.checkNumber.focus();
    	return false;
	}

	if(document.frmAdvMag.checkNumber.value.indexOf('.')!=-1){
		alert("Enter Valid check number.");
		document.frmAdvMag.checkNumber.focus();
		return false;
	}
//=============================== For Check Date ========================
	if(document.frmAdvMag.checkDate.value==""){
		alert("Select Date in Check Details");
		document.frmAdvMag.checkDate.focus();
		return false;
	}


	if(!(document.frmAdvMag.checkDate.value=="")){
	
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

	if(!(document.frmAdvMag.checkDate.value=="")){
		strdate=document.frmAdvMag.checkDate.value;
		mm = Number(strdate.substring(0,2));
		dd = Number(strdate.substring(3,5));
		yyyy=(strdate.substring(6,document.frmAdvMag.checkDate.value.length));

		var now = new Date();
		var months1 = now.getMonth();
		var years=now.getYear();
		var months = 7+months1;
		year=1+years;
		
		if((months<12)&&(mm>months)){
			alert("Enter valid Date in Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((months1==6)&&(mm>1)&&(mm<6)){
			alert("Enter valid Date in Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}
		
		if((months1==7)&&(mm>2)&&(mm<7)){
			alert("Enter valid Date in Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((months1==8)&&(mm>3)&&(mm<8)){
			alert("Enter valid Date in Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((months1==9)&&(mm>4)&&(mm<9)){
			alert("Enter a valid Date in Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((months1==10)&&(mm>5)&&(mm<10)){
			alert("Enter a valid Date in Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((months1==11)&&(mm>6)&&(mm<11)){
			alert("Enter valid Date in the Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if(yyyy<nowYear){
			alert("Enter valid Date in the Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((yyyy==nowYear)&&(mm<nowMonth)){
			alert("Enter valid Date in the Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if((dd<nowDate)&&(mm==nowMonth)){
			alert("Enter valid date in the Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}

		if(dd>31){
			alert("Enter valid date in the Payment Details");
			document.frmAdvMag.checkDate.focus();
			return false;
		}
	}
}

//===================================In Favor Of====================================

if(document.frmAdvMag.bankName.value==""){
		alert("Bank Name cannot be empty.");
		document.frmAdvMag.bankName.focus();
		return false;
	}

	if(document.frmAdvMag.bankName.value.length>80){
		alert("Bank Name exceeds the range");
		document.frmAdvMag.bankName.focus();
		return false;
	}

	if(isnotAlpha(document.frmAdvMag.bankName.value)){
		alert("Enter valid Bank Name");
		document.frmAdvMag.bankName.focus();
    	return false;
	}

	if(document.frmAdvMag.bankName.value.indexOf(' ')==0){
			alert("Enter valid Bank Name");
		document.frmAdvMag.bankName.focus();
		return false;
	}

	if(document.frmAdvMag.bankName.value.indexOf('  ')!==-1){
			alert("Enter valid Bank Name");
		document.frmAdvMag.bankName.focus();
		return false;
	}

	if(Number(document.frmAdvMag.bankName.value)){
			alert("Enter valid Bank Name");
		document.frmAdvMag.bankName.focus();
		return false;
	}
}




//===========================card details=========================
		
// ======================= For Card No =============================

		if(chosen2=="card"){

		if(document.frmAdvMag.ccNumber.value==""){
			alert("Enter Credit Card Number ");
			document.frmAdvMag.ccNumber.focus();
			return false;
		}

		if(!Number(document.frmAdvMag.ccNumber.value)){
			alert("Enter valid Credit Card Number");
			document.frmAdvMag.ccNumber.focus();
			return false;
		}

		if(document.frmAdvMag.ccNumber.value.length!=16 ){
			alert("Credit Card Number should be 16 charecters");
			document.frmAdvMag.ccNumber.focus();
			return false;
		}

		if(document.frmAdvMag.ccNumber.value.indexOf('.')!=-1){
			alert("Enter valid Credit Card Number");
			document.frmAdvMag.ccNumber.focus();
			return false;
		}

//--------for CVV Number -------------------------------------

	if(document.frmAdvMag.ccCvvid.value==""){
		alert("Enter valid CVV Number");
		document.frmAdvMag.ccCvvid.focus();
		return false;
	}

	if(document.frmAdvMag.ccCvvid.value.length>4){
		alert("Enter valid CVV Number");
		document.frmAdvMag.ccCvvid.focus();
		return false;
	}

	if(document.frmAdvMag.ccCvvid.value.length<3){
		alert("Enter valid CVV Number");
		document.frmAdvMag.ccCvvid.focus();
		return false;
	}

	if(document.frmAdvMag.ccCvvid.value.indexOf('.')!=-1){
		alert("Enter Valid Cvv Number");
		document.frmAdvMag.ccCvvid.focus();
		return false;
	}
	
	
	//===================== For card type ================
		
if ( document.frmAdvMag.ccType.selectedIndex == 0 ){
    alert ( "Please select card type." );
	document.frmAdvMag.ccType.focus();
    return false;
 	}
//----for Print Name on Card ----------------------------------------
	if(document.frmAdvMag.ccName.value==""){
		alert("Print Name cannot be empty");
		document.frmAdvMag.ccName.focus();
		return false;
	}

	if(document.frmAdvMag.ccName.value.length>80){
		alert("Print Name exceeds the range");
		document.frmAdvMag.ccName.focus();
		return false;
	}

	if(isnotAlpha(document.frmAdvMag.ccName.value)){
		alert("Enter valid Card Name.");
		document.frmAdvMag.ccName.focus();
	    return false;
    }

	if(Number(document.frmAdvMag.ccName.value)){
		alert("Enter valid card name");
		document.frmAdvMag.ccName.focus();
		return false;
	}

	if(document.frmAdvMag.ccName.value.indexOf(' ')==0){
		alert("Enter Valid Card Name.");
		document.frmAdvMag.ccName.focus();
		return false;
	}

	if(document.frmAdvMag.ccName.value.indexOf('  ')!==-1){
		alert("Enter Valid Card Name.");
		document.frmAdvMag.ccName.focus();
		return false;
	}

//---for Expiry month drop down --------------------------------

	var rightNow=new Date();
	var theYear=rightNow.getYear();
	var tMonth=rightNow.getMonth();
	var theMonth=1+tMonth;
	var theDate=rightNow.getDate();

	if ( document.frmAdvMag.ccExpMonth.selectedIndex == 0 ){
		alert ( "Please select a Month." );
		document.frmAdvMag.ccExpMonth.focus();
    	return false;
	}

	if (document.frmAdvMag.ccExpYear.value==theYear){
		 if(document.frmAdvMag.ccExpMonth.value<theMonth){
			alert("You have selected an Expired Month in card details. Please Select Valid Expiration Month.");
			document.frmAdvMag.ccExpMonth.focus();
			return(false);   
		}
	}

//-----------for Expiry Year drop down

	if ( document.frmAdvMag.ccExpYear.selectedIndex == 0 ){
		alert ( "Please select a year." );
		document.frmAdvMag.ccExpYear.focus();
    	return false;
	}


	if (document.frmAdvMag.ccExpYear.value<theYear){
		alert("You have selected an Expired Year in card details. Please Select Valid Expiration Year.");
		document.frmAdvMag.ccExpYear.focus();
		return false;   
	}


		}

	return true;
}
	
