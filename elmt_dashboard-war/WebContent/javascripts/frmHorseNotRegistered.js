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


function copyRiderOwner(){
	if(document.getElementById('riderUseaNo_id').value == "" && document.myform.regFor[0].checked){
		document.getElementById('userDetId').value="";
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
		if(document.getElementById('rself1').value != 'rid'){
			document.myform.ownerUseaNoTwo1.value = "";
			document.myform.ownerFNameTwo1.value = "";
			document.myform.ownerLNameTwo1.value = "";
			document.myform.ownerPhoneTwo1.value = "";
		}
		else{	
			document.myform.ownerUseaNoTwo1.value = document.myform.riderUseaNo_id.value;
			document.myform.ownerFNameTwo1.value = document.myform.riderFname_id.value;
			document.myform.ownerLNameTwo1.value = document.myform.riderLname_id.value;
			document.myform.ownerPhoneTwo1.value = document.myform.riderPhone_id.value;	
		}
	}
	return true;
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
	check="`~!@#$%^*()_+=|{}1234567890[];:/<>?"+"\\"+"\"";
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
	if(document.myform.roughVal.value=="yes"){
		document.myform.chkBalAmt.value==""
	}
	
	 
}

function cardClear(){
	document.myform.ccNumber.value="";
	document.myform.ccCvvid.value="";
	document.myform.ccType.selectedIndex=0;
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

function clearRiderdetails(){
	document.myform.ownerUseaNo_id.value="";
	document.myform.ownerFname_id.value="";
	document.myform.ownerLname_id.value="";
	document.myform.ownerPhone_id.value="";
}

function clearHLCdetails(){
	document.myform.userNameId1.value="";
	document.myform.firstNameId1.value="";
	document.myform.lastNameId1.value="";
	document.myform.phoneId1.value="";
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

 
function comRegClear1(){
	document.myform.existAddCompNameId1.value="";
	document.myform.cpAddfirstNameId1.value="";
	document.myform.cpAddlastNameId1.value="";
	document.myform.ecmpAddPhoneId1.value="";
}

function comNewClear(){
	document.myform.newFirstNameIdComp.value="";
	document.myform.newAddressIdComp.value="";
	document.myform.newTCountryIdComp.value="USA";
	document.myform.newTStateIdComp.selectedIndex=0;
	document.myform.newCityIdComp.value="";
	document.myform.newZipIdComp.value="";
	document.myform.newEmailIdComp.value="";
	document.myform.newPhoneIdComp.value="";
	document.myform.newFaxIdComp.value="";
	document.myform.cpFirstNameIdComp.value="";
	document.myform.cpLastNameIdComp.value="";
}

function comTNewClear(){
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

function comRadio(){
	for(i=0;i<document.myform.rself1.length;i++){
		document.myform.rself1[i].checked=false;
	}
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

function Dispallsadas(){
	j=0;
	num1=0;
	num2=0;
	arrayval = new Array();
	sum=0;
	lenfee=document.myform.feeDisp.length;
	for (i = 0; i < lenfee; i++){
		if(document.myform.feeDisp[i].selected){
			chosenstr= document.myform.feeDisp[i].value;
			n = chosenstr.lastIndexOf("#");
			substr= chosenstr.substring((n+1),chosenstr.length);
			var chosenfee=Number(substr);
			
 			var temp =format(chosenfee,2);
			document.myform.fee_txt.value = temp;
			sum=chosenfee;
		}
	}
	for(i=1;i<arrayval.length;i++){
		num1=arrayval[i].lastIndexOf("#");
		num2=arrayval[i].length;
		str = arrayval[i].substring(num1+1,num2);
		num = Number(str);
		sum = sum + num; 
	}





	 
	document.myform.tempAmount.value = sum;
	document.myform.totalAmount.value= format(sum,2);
}
function Dispall(){
	j=0;
	num1=0;
	num2=0;
	arrayval = new Array();
	sum=0;
	lenfee=document.myform.feeDisp.length;
	for (i = 0; i < lenfee; i++){
		if(document.myform.feeDisp[i].selected){
			chosenstr= document.myform.feeDisp[i].value;
			n = chosenstr.lastIndexOf("#");
			substr= chosenstr.substring((n+1),chosenstr.length);
			var chosenfee=Number(substr);
 			var temp =format(chosenfee,2);
			document.myform.fee_txt.value = temp;
			sum=chosenfee;
		}
	}
	for(i=1;i<arrayval.length;i++){
		num1=arrayval[i].lastIndexOf("#");
		num2=arrayval[i].length;
		str = arrayval[i].substring(num1+1,num2);
		num = Number(str);
		sum = sum + num; 
	}
	if(document.myform.status.value=="yes"){
		chkLen = document.myform.phoneCharge.value;
		 
			 if(document.myform.phoneCharge.checked){
					 sum = Number(chkLen) + sum;
			 }
		 
	}

	if(document.myform.membAmount.value=="null"){
		temp1 = Number(document.myform.fee_txt.value);
		document.myform.totalAmount.value= format(temp1,2);
	}		
	else{
		tempAmt =Number(sum) - Number(document.myform.membAmount.value)
		document.myform.totalAmount.value= format(tempAmt,2);
	}
	
	document.myform.tempAmount.value = sum;
	document.myform.totalAmount.value= format(sum,2);
}
//---------------------------------id date fn-------------------------

//------------------------------for check date validation---------
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
//------------------------for check date---------
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
		alert("Enter a valid month in Check Date ")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Enter a valid day in Check Date ")
		return false
	}
   if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Enter a valid Check Date ")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear ){
		alert("Enter a valid Year in Check Date")
		return false
	}
	
return true
}

function isDateImported(dtStr){
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
		alert("Enter a valid month in Check Date ")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Enter a valid day in Check Date ")
		return false
	}
   if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Enter a valid Check Date ")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear ){
		alert("Enter a valid Year in Check Date")
		return false
	}
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getFullYear();
	var nowMonth1=todayDate.getMonth();
	var nowMonth=1+nowMonth1;
	if(nowDate<10){nowDate="0"+nowDate;}
	if(nowMonth<10){nowMonth="0"+nowMonth;}
	if(year>nowYear){
		alert("Enter valid Date Imported in the Horse Description");
		return false;
	}
	if((year==nowYear)&&(month>nowMonth)){
		alert("Enter valid Date Imported in the Horse Description");
		return false;
	}
	if((year==nowYear)&&(day>nowDate)&&(month==nowMonth)){
		alert("Enter valid Date Imported in the Horse Description");
		return false;
	}
	return true
}

function myValidate(){
	//alert(document.myform.userDetId.value);
	//alert(document.myform.addUserDetId.value);
	
	if(document.myform.horseSelect.selectedIndex==0){
		alert("Select any Horse Registration Type");
		document.myform.horseSelect.focus();
		return false;
	}
//===================================== For Horse Name ========================================
	hName=document.myform.competitionName1.value;
	if(hName==""){
		alert("Enter Horse Name in Horse Information Section");
		document.myform.competitionName1.focus();
		return false;
	}
	if(isHorseName(hName)){
		alert("Enter valid Horse Name in Horse Information Section");
		document.myform.competitionName1.focus();
		return false;
	}
	if((hName.indexOf('  ')!=-1)||(hName.indexOf(' ')==0)){
		alert("Enter valid Horse Name in Horse Information Section");
		document.myform.competitionName1.focus();
		return false;
	}
	if(hName.length>80){
		alert("Horse Name in Horse Information Section should not exceed 80 characters");
		document.myform.competitionName1.focus();
		return false;
	}
	
//================================ For Past Name ================================================
	regByName=document.myform.regstrBy.value;
	if(isHorseName(regByName)){
		alert("Enter valid RegisterBy Name in Horse Information Section");
		document.myform.regstrBy.focus();
		return false;
	}
	if((regByName.indexOf('  ')!=-1)||(regByName.indexOf(' ')==0)){
		alert("Enter valid RegisterBy Name in Horse Information Section");
		document.myform.regstrBy.focus();
		return false;
	}
	if(regByName.length>80){
		alert("RegisterBy Name in Horse Information Section should not exceed 80 characters");
		document.myform.regstrBy.focus();
		return false;
	}

//====================================== For Registered Name ===========================================
	regName=document.myform.registeredName1.value;
	if(isHorseName(regName)){
		alert("Enter valid Registered Name in Horse Information Section");
		document.myform.registeredName1.focus();
		return false;
	}
	if((regName.indexOf('  ')!=-1)||(regName.indexOf(' ')==0)){
		alert("Enter valid Registered Name in Horse Information Section");
		document.myform.registeredName1.focus();
		return false;
	}
	if(regName.length>80){
		alert("Registered Name in Horse Information Section should not exceed 80 characters");
		document.myform.registeredName1.focus();
		return false;
	}

//================================ For Past Name ================================================
	pasName=document.myform.baPastName1.value;
	if(isHorseName(pasName)){
		alert("Enter valid Past Name in Horse Information Section");
		document.myform.baPastName1.focus();
		return false;
	}
	if((pasName.indexOf('  ')!=-1)||(pasName.indexOf(' ')==0)){
		alert("Enter valid Past Name in Horse Information Section");
		document.myform.baPastName1.focus();
		return false;
	}
	if(pasName.length>80){
		alert("Past Name in Horse Information Section should not exceed 80 characters");
		document.myform.baPastName1.focus();
		return false;
	}

//============================= For Breed Association ============================================
	brAssoc=document.myform.breename1.value;
	if(isHorseName(brAssoc)){
		alert("Enter valid Breed Association in Horse Information Section");
		document.myform.breename1.focus();
		return false;
	}

	if((brAssoc.indexOf('  ')!=-1)||(brAssoc.indexOf(' ')==0)){
		alert("Enter valid Breed Association in Horse Information Section");
		document.myform.breename1.focus();
		return false;
	}
	if(brAssoc.length>80){
		alert("Breed Association in Horse Information Section should not exceed 80 characters");
		document.myform.breename1.focus();
		return false;
	}
	
//============================= Rider Information Section HLC No. ==============================
	if(document.myform.riderUseaNo_id.value==""){
		alert("Enter HLC Number in Rider Information Section");
		document.myform.riderUseaNo_id.focus();
		return false;
	}
	if(isnotInteger(document.myform.riderUseaNo_id.value)){
		alert("Enter valid HLC Number in Rider Information Section");
		document.myform.riderUseaNo_id.focus();
		return false;
	}
	if(document.myform.riderUseaNo_id.value.length>20){
		alert("HLC Number in Rider Information Section should not exceed 20 characters");
		document.myform.riderUseaNo_id.focus();
		return false;
	}

//======================== To Edit the Owner ==========================
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
		if(chosen=="rid"){
			if(document.myform.ownerUseaNoTwo1.value==""){
				alert("Enter Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo1.focus();
				return false;
			}
			if(isnotSpecial(document.myform.ownerUseaNoTwo1.value)){
				alert("Enter valid Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo1.focus();
				return false;
			}
			if((document.myform.ownerUseaNoTwo1.value.indexOf('  ')!=-1)||(document.myform.ownerUseaNoTwo1.value.indexOf(' ')==0)){
				alert("Enter valid Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo1.focus();
				return false;
			}
			if(document.myform.ownerUseaNoTwo1.value.length > 80){
				alert("Enter valid Rider Id in Owner Information Section");
				document.myform.ownerUseaNoTwo1.focus();
				return false;
			}
		}
		if(chosen=="mem"){
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
	}
	
//=========================== Trainer information	
	
		chose="";
		le=document.myform.regFor.length;
		for(i=0;i<le;i++){
			if(document.myform.regFor[i].checked){
				chose= document.myform.regFor[i].value;
			}
		}
		if(chose==""){
			alert("Check any of the Trainer Information Option");
			return false;
		}
		if(chose=="rid1"){
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
		if(chose=="acc1"){
			if(document.myform.userNameId1.value==""){
				alert("Enter User Name in Trainer Information Section");
				document.myform.userNameId1.focus();
				return false;
			}
			if(isnotSpecial(document.myform.userNameId1.value)){
				alert("Enter valid User Name in Trainer Information Section");
				document.myform.userNameId1.focus();
				return false;
			}
			if((document.myform.userNameId1.value.indexOf('  ')!=-1)||(document.myform.userNameId1.value.indexOf(' ')==0)){
				alert("Enter valid User Name in Trainer Information Section");
				document.myform.userNameId1.focus();
				return false;
			}
			if(document.myform.userNameId1.value.length > 80){
				alert("Enter valid User Name in Trainer Information Section");
				document.myform.userNameId1.focus();
				return false;
			}
		}

//========================== For Company Information =========================================	
		if(chose=="cmp1"){
			cho="";
			leng=document.myform.ecmp.length ;
			for(i=0;i<leng;i++){

				if(document.myform.ecmp[i].checked){
					cho= document.myform.ecmp[i].value; 
				}
			}
			if(cho==""){
				alert("Select any of the options in Company Information Details");
				return false;
			}

//=============================== For Registered Company User ==============================
			if(cho=="yes"){
				
				if(document.myform.existCompNameIdT1.value==""){
					alert("Enter Company Name in Trainer Information Section");
					document.myform.existCompNameIdT1.focus();
					return false;
				}
				if(isNameAndCity(document.myform.existCompNameIdT1.value)){
					alert("Enter valid Company Name in Trainer Information Section");
					document.myform.existCompNameIdT1.focus();
					return false;
				}
				if((document.myform.existCompNameIdT1.value.indexOf('  ')!=-1)||(document.myform.existCompNameIdT1.value.indexOf(' ')==0)){
					alert("Enter valid Company Name in Trainer Information Section");
					document.myform.existCompNameIdT1.focus();
					return false;
				}	
				if(document.myform.existCompNameIdT1.value.length > 80){
					alert("Company Name in Trainer Information Section should not exceed 80 characters");
					document.myform.existCompNameIdT1.focus();
					return false;
				}
			}

//=============================== For Nonregistered Company User =============================
			if(cho=="no"){

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
					alert("Enter Company Address in Trainer Information Section");
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
	//================================ For State ====================================
				var edln = "";
				if(typeof(window.document.myform.newTStateIdComp) == 'object'){
					if (window.document.myform.newTStateIdComp.value != ""){
						var lid;
						lid = window.document.myform.newTStateIdComp[window.document.myform.newTStateIdComp.selectedIndex].value;
						edln = lid;
					}
					if(edln.length == 1){
						alert("Please select the State.");
						window.document.myform.newTStateIdComp.focus();
						return false;
					}
				} 
				if ( document.myform.newTStateIdComp.selectedIndex == 0 ){
					alert ( "Please select a State in Company details" );
					document.myform.newTStateIdComp.focus();
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
			if(document.myform.newTCountryIdComp.selectedIndex==239){
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
		
	//================================= For Country ===================================
				var cdln = "";
				if(typeof(window.document.myform.newTCountryIdComp) == 'object'){
					if (window.document.myform.newTCountryIdComp.value != ""){
						var clid;
						clid = window.document.myform.newTCountryIdComp[window.document.myform.newTCountryIdComp.selectedIndex].value;
						cdln = clid;
					}
					if(cdln == "Select One"){
						alert("Please select the Country");
						window.document.myform.newTCountryIdComp.focus();
						return false;
					}
				}
				if(document.myform.newTCountryIdComp.selectedIndex == 0 ){
					alert ( "Please select a Country in Company Details." );
					document.myform.newTCountryIdComp.focus();
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
					alert("Enter valid Phone Number in Trainer Information Section");
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
	
//================================ For Horse Color ===========================
	if ( document.myform.colorSelectId.selectedIndex == 0 ){
		alert ( "Please select Horse Color.");
		document.myform.colorSelectId.focus();
		return false;
	}

//============================== For Horse Gender ============================
	if ( document.myform.genderSelectId.selectedIndex == 0 ){
		alert ( "Please select Horse Gender.");
		document.myform.genderSelectId.focus();
		return false;
	}
//================================ For Horse Height ====================================	
	if(document.myform.height.value.length>20){
		alert("Horse Height should not exceed the maximum of 20 digits");
		document.myform.height.focus();
		return false;
	}
//===================================== For Year Foaled ============================
	if(document.myform.foaled.value=="" || document.myform.foaled.value.indexOf(' ')==0){
			alert("Year Foaled cannot be empty.");
			document.myform.foaled.focus();
			return false;		
	}
	var thisyear = (new Date()).getFullYear();
	if(document.myform.foaled_id.value!=""){
		if(!Number(document.myform.foaled_id.value)){
			alert("Enter numbers in the Year Foaled field.");
			document.myform.foaled_id.focus();
			return false;
		}
		if(document.myform.foaled_id.value.length !=4){
			alert("Enter valid year in the Year Foaled field.");
			document.myform.foaled_id.focus();
			return false;
		}
	}
	if(document.myform.foaled_id.value>thisyear){
		alert("Enter a  valid year in year foaled field.");
		document.myform.foaled_id.focus();
		return false;
	}
	if(Number(document.myform.foaled_id.value)){
		curntAge = Number(thisyear)- Number(document.myform.foaled_id.value);
		
		var horseVal = document.myform.feeDisp.value;
		var FEHMin = document.myform.FEHMin.value;
		var FEHMax = document.myform.FEHMax.value;
		var YEHMin = document.myform.YEHMin.value;
		var YEHMax = document.myform.YEHMax.value;

		var splitVal = horseVal.split('#');
		if(splitVal[1]=="Future Event Horse"){
			if(Number(curntAge)==0){
				var alrtMsg = "By rule, your horse will be turning 1 year old on January 1st of the upcoming year,\n"+
							  "and will be eligible to enter Future Event Horse competitions only by upcoming year";
				alert(alrtMsg);
			}
			if(Number(curntAge) >= Number(FEHMin) && Number(curntAge) < Number(FEHMax)){
				//return true;
			}
			if(Number(curntAge) == Number(FEHMax)){
				var alrtMsg = "By rule, your horse will be turning 4 years old on January 1st of the upcoming year,\n "+
							  "and will be ineligible to enter Future Event Horse competitions. If you will not be \n"+
							  "competing this horse in FEH competition prior to November 30th of this year, you should \n"+
							  "register this horse at the Young Event Horse Level or above.";
				alert(alrtMsg);
				//return false;
			}
			if(Number(curntAge)>Number(FEHMax)){
				alert('The age of this horse disqualifies it for competition at the selected Horse Registration level.\n'+
					  'Please select Young Event Horse (5 or 6 year olds), Limited or Full registration level');
				document.myform.horseSelect.focus();
				return false;
			}
		}
		else if(splitVal[1]=="Young Event Horse"){
			if(Number(curntAge)<Number(YEHMin)){
				//var alrtMsg2 = "By rule, horses under the age of 4 are only eligible for the Future Event Horse competitions.";
				var alrtMsg1 = "By rule, horses competing at the Young Event Horse level must be at least 4 years old.\n"+
								"If you will be competing this horse prior to November 30th of this year,\n"+
								"it can only be in Future Event Horse level competitions.";
				alert(alrtMsg1);
				//alert(alrtMsg2);
				//return true;
			}
			if(Number(curntAge) >= Number(YEHMin )&& Number(curntAge) < Number(YEHMax)){
				//return true;
			}
			if(Number(curntAge) == Number(YEHMax)){
				var alrtMsg = "By rule, your horse will be 6 years old on January 1st of the upcoming year, and will be ineligible "+
							  "to enter Young Event Horse level competitions. If you will not be competing this horse in YEH competition "+
							  "prior to November 30th of this year, you should register this horse at the Limited level or above.";
				//document.myform.horseSelect.focus();
				alert(alrtMsg);
				//return true;
			}
			if(Number(curntAge)>Number(YEHMax)){
				alert('The age of this horse disqualifies it for competition at the selected Horse Registration level.\n'+
					  'Please select Limited or Full registration level.');
				document.myform.horseSelect.focus();
				return false;
			}
		}
		else{
			//alert(horseVal);
		}
	}
//============================== For Country Origin ================================
	if((document.myform.countryOrigin.value.indexOf('  ')!=-1)||(document.myform.countryOrigin.value.indexOf(' ')==0)){
		alert("Enter valid Country of Origin in the Horse Description");
		document.myform.countryOrigin.focus();
		return false;
	}
	if(document.myform.countryOrigin.value.length>80){
		alert("Country of Origin in the Horse Description should not exceed 80 characters");
		document.myform.countryOrigin.focus();
		return false;
	}
	if(isNameAndCity(document.myform.countryOrigin.value)){
		alert("Enter a valid Country of Origin in the Horse Description");
		document.myform.countryOrigin.focus();
		return false;
	}
//------------for Sire Name of Horse Description --
	if((document.myform.sireName.value.indexOf('  ')!=-1)||(document.myform.sireName.value.indexOf(' ')==0)){
		alert("Enter a valid name in the Sire Name field");
		document.myform.sireName.focus();
		return false;
	}
	if(document.myform.sireName.value.length>80){
		alert("Sire Name field should not exceed 80 characters");
		document.myform.sireName.focus();
		return false;
	}
	if(isHorseName(document.myform.sireName.value)){
		alert("Enter a valid name in the Sire Name field");
		document.myform.sireName.focus();
		return false;
	}
//------------for DamName in Horse Description --
	if((document.myform.damName.value.indexOf('  ')!=-1)||(document.myform.damName.value.indexOf(' ')==0)){
		alert("Enter a valid name in the Dam Name field");
		document.myform.damName.focus();
		return false;
	}
	if(document.myform.damName.value.length>80){
		alert("Dam Name in the Horse Description should not exceed 80 characters");
		document.myform.damName.focus();
		return false;
	}
	if(isHorseName(document.myform.damName.value)){
		alert("Enter a valid Dam Name in the Horse Description");
		document.myform.damName.focus();
		return false;
	}
//------------for Imported From in Horse Description --
	if((document.myform.impFrm_id.value.indexOf('  ')!=-1)||(document.myform.impFrm_id.value.indexOf(' ')==0)){
		alert("Enter valid Imported From in the Horse Description");
		document.myform.impFrm_id.focus();
		return false;
	}
	if(document.myform.impFrm_id.value.length>80){
		alert("Imported From in the Horse Description should not exceed 80 characters");
		document.myform.impFrm_id.focus();
		return false;
	}
	if(isNameAndCity(document.myform.impFrm_id.value)){
		alert("Enter valid Imported From in the Horse Description");
		document.myform.impFrm_id.focus();
		return false;
	}
//------------for Date Imported
	if(document.myform.dateImported.value!=""){
		if(isDateImported(document.myform.dateImported.value)==false){
			document.myform.dateImported.focus();
			return false;
		}
	}
//================================== For Check & Card Option =======================

     sec="";
     sec =document.myform.c11.value;
if(sec=="adminSec"){
	chosen="";
	len = document.myform.r11.length ;
	for(i=0;i<len;i++){
		if(document.myform.r11[i].checked){
			chosen= document.myform.r11[i].value;
		}
	}
	if(chosen==""){
		alert("Check any of the Payment Option.");
		return false;
	}
//--------------------------Check  Number ---------------------------
	if(chosen=="check"){
		if(document.myform.roughVal.value=='yes'){
			if(document.myform.chkBalAmt.value!=""){
			if(!Number(document.myform.chkBalAmt.value)){
				alert("Enter valid Check Amount");
				document.myform.chkBalAmt.focus();
				return false;
			}
			
			if((document.myform.chkBalAmt.value.indexOf('+')!=-1)||(document.myform.chkBalAmt.value.indexOf('-')!=-1)){
				alert("Enter valid Check Amount");
				document.myform.chkBalAmt.focus();
				return false;
			}
		
			if(!(document.myform.chkBalAmt.value=="")){
				if((document.myform.chkBalAmt.value.indexOf('.'))!=-1){
					fstr=document.myform.chkBalAmt.value;
					fstr1=document.myform.chkBalAmt.value.indexOf('.');
					mm = (fstr.substring(fstr1,document.myform.chkBalAmt.value.length));
					str=(Number(mm.length));
					if((str)>3){
						alert("Enter valid Check Amount");
						document.myform.chkBalAmt.focus();
						return false;
					}
				}
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
			alert("Enter Valid check number.");
			document.myform.checkNumber.focus();
			return false;
		}
	 
//=============================== For Check Date ========================
		if(document.myform.chkdate.value=="yes"){
			if(document.myform.checkDate.value==""){
				alert("Select Date in Check Details");
				document.myform.checkDate.focus();
				return false;
			}
			if (isDate(document.myform.checkDate.value)==false){
				document.myform.checkDate.focus()
				return false; 
			}
		}
		if(document.myform.chkdate.value=="no"){
			if(document.myform.checkDate.value==""){
				alert("Select Date in Check Details");
				document.myform.checkDate.focus();
				return false;
			}
		}
		
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
		if(Number(document.myform.bankName.value)){
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
		if(Number(document.myform.nameCheck.value)){
			alert("Enter valid Name on Check");
			document.myform.nameCheck.focus();
			return false;
		}
	}
}
//===========================card details=========================
// ======================= For Card No =============================
if(sec=="userSec" || sec=="adminSec"){
	//alert(sec);
	//if(chosen=="card"){
		if (document.myform.ccType.selectedIndex==0 ){
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
		if(document.myform.ccNumber.value.indexOf('.')!=-1){
			alert("Enter Valid Card Number");
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

		if(sec=="userSec"){
					if (document.myform.ccCvvid.value==""){
				alert("Enter CVV Number in Card Details");
				document.myform.ccCvvid.focus();
				return false;
							}
				}
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
				alert("Enter Valid CVV Number");
				document.myform.ccCvvid.focus();
				return false;
			}			
			if(isnotInteger(document.myform.ccCvvid.value)){
				alert("Enter Valid CVV Number");
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
	
		if(Number(document.myform.ccName.value)){
			alert("Enter valid card name");
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
	//}
}
	
//------------------for activation date-------------------
	if(document.myform.chkact.value=="yes"){
		if(document.myform.activeDatVal.value==""){
			alert("Select Date in Activation Details");
			document.myform.activeDatVal.focus();
			return false;
		}
		if (isDate(document.myform.activeDatVal.value)==false){
			document.myform.activeDatVal.focus()
			return false; 
		}
	}
	//return false;
}
function format(obj, decimal) {
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
 
	  return retval;
 }
 
 //---------------------------------------
 
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
