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

//========================= Functions to be Used =========================
function getText(){
 
	var selObj = document.getElementById('selActivityId');
	var selIndex = selObj.selectedIndex;
	var selTxt = selObj.options[selIndex].text;
	//alert(selTxt);
	var selVal = selObj.options[selIndex].value;
	//alert(selVal);
	if (selTxt == "Other" ){
       document.frmMeeEduActivity.txtOthernot.disabled=false;
    }
	else{
       document.frmMeeEduActivity.txtOthernot.disabled=true;
	}
}
//=============================checked others=================================

function isOtherscheck(){
	if(document.frmMeeEduActivity.chkFacFour.checked){
		document.frmMeeEduActivity.txtOther.disabled=false;
	}
	else{
		document.frmMeeEduActivity.txtOther.disabled=true;
	}
}

function browseCheck(){
	if(document.frmMeeEduActivity.chkAddSites.checked){
		document.frmMeeEduActivity.txtUpload.disabled=false;
	}
	else{
		document.frmMeeEduActivity.txtUpload.disabled=true;
	}
}
	    

//-----------------------------function for zipcode--------------------------------------------
function isnotZipcode(str){
	stringZip="0123456789";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(stringZip.indexOf(str.charAt(j))!=-1){
			fzip=0;
		}
	}
	if(fzip==0){
		return false;
	}
	else{
		return true;
	}
}
//-----------------------------------------------------------------------------------------

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

function isAlpha1(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=~.,'`;:"+"\\"+"\'";
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

function popupCal(val) {
      if(val==1)
	      frmMeeEduActivity.txtActDate.value = "StartDate1";

      if(val==2)
         frmMeeEduActivity.txtChDate.value = "StartDate1";

      val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null) {
         alert("Plz Enable Site Popup Allowed");
      }
}

function eduActivity(){
	
	
//============================ For Part A ================================
//============================ For Name of Activity ======================

	if(document.frmMeeEduActivity.txtActName.value==""){
		alert("Enter Activity Name.");
		document.frmMeeEduActivity.txtActName.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtActName.value.length>80){
		alert("Entered Activity Name is out of range.");
		document.frmMeeEduActivity.txtActName.focus();
		return false;
	}

	/*if(isnotAlpha(document.frmMeeEduActivity.txtActName.value)){
		alert("Enter valid Activity Name.");
		document.frmMeeEduActivity.txtActName.focus();
    	return false;
    }*/

	/*if(Number(document.frmMeeEduActivity.txtActName.value)){
		alert("Enter valid Activity Name.");
		document.frmMeeEduActivity.txtActName.focus();
		return false;
	}*/

	if(document.frmMeeEduActivity.txtActName.value.indexOf(' ')==0){
		alert("Enter valid Activity Name.");
		document.frmMeeEduActivity.txtActName.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtActName.value.indexOf('  ')!==-1){
		alert("Enter valid Activity Name.");
		document.frmMeeEduActivity.txtActName.focus();
		return false;
	}

//============================ For Date ============================
	
	if(document.frmMeeEduActivity.txtActDate.value==""){
        alert ( "Please Select a Date." );
		document.frmMeeEduActivity.txtActDate.focus();
        return false;
    }
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

	if(!(document.frmMeeEduActivity.txtActDate.value=="")){
		strdate=document.frmMeeEduActivity.txtActDate.value;
		mm = Number(strdate.substring(0,2));
		dd = Number(strdate.substring(3,5));
		yyyy=(strdate.substring(6,document.frmMeeEduActivity.txtActDate.value.length));
	
		if(yyyy<nowYear){
			alert("Enter valid Activity Date.");
			document.frmMeeEduActivity.txtActDate.focus();
			return false;
		}

		if((yyyy==nowYear)&&(mm<nowMonth)){
			alert("Enter valid Activity Date.");
			document.frmMeeEduActivity.txtActDate.focus();
			return false;
		}

		if((dd<nowDate)&&(mm==nowMonth)&&(yyyy==nowYear)){
			alert("Enter valid Activity Date.");
			document.frmMeeEduActivity.txtActDate.focus();
			return false;
		}
	}
	
//============================For Days====================================

	if(document.frmMeeEduActivity.daysSelect.selectedIndex==0){
        alert ( "Please Select Number of Days." );
		document.frmMeeEduActivity.daysSelect.focus();
        return false;
    }

//============================ For HLC Area =============================
	
	if(document.frmMeeEduActivity.txtUseaArea.selectedIndex==0){
        alert ( "Please Select HLC Area." );
		document.frmMeeEduActivity.txtUseaArea.focus();
        return false;
    }

//======================== For Location ===================================

	if(document.frmMeeEduActivity.txtLocation.value==""){
		alert("Enter Location.");
		document.frmMeeEduActivity.txtLocation.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLocation.value.length>255){
		alert("Entered Location Field is out of range.");
		document.frmMeeEduActivity.txtLocation.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLocation.value.indexOf(' ')==0){
		alert("Enter valid Location.");
		document.frmMeeEduActivity.txtLocation.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLocation.value.indexOf('  ')!==-1){
		alert("Enter valid Location.");
		document.frmMeeEduActivity.txtLocation.focus();
		return false;
	}

//=========================== For State ====================================

	if(document.frmMeeEduActivity.txtState.value==""){
		alert("Enter State Name.");
		document.frmMeeEduActivity.txtState.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtState.value.length>80){
		alert("Entered State Name is out of Range.");
		document.frmMeeEduActivity.txtState.focus();
		return false;
	}

	if(isnotAlpha(document.frmMeeEduActivity.txtState.value)){
		alert("Enter valid State Name.");
		document.frmMeeEduActivity.txtState.focus();
    	return false;
	}

	if(Number(document.frmMeeEduActivity.txtState.value)){
		alert("Enter valid State Name.");
		document.frmMeeEduActivity.txtState.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtState.value.indexOf(' ')==0){
		alert("Enter valid State Name.");
		document.frmMeeEduActivity.txtState.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtState.value.indexOf('  ')!==-1){
		alert("Enter valid State Name.");
		document.frmMeeEduActivity.txtState.focus();
		return false;
	}

//========================= For Organizer Member ID =========================

/*	if(document.frmMeeEduActivity.txtUseaId.value==""){
		alert("Enter the HLC Manager Id");
		document.frmMeeEduActivity.txtUseaId.focus();
		return false;
	}

	if(isAlpha1(document.frmMeeEduActivity.txtUseaId.value)){
		alert("Enter valid number in HLC ManagerID.");
		document.frmMeeEduActivity.txtUseaId.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtUseaId.value.indexOf('.')!=-1){
		alert("Enter valid number in HLC ID.");
		document.frmMeeEduActivity.txtUseaId.focus();
		return false;
	}
	*/
//================================== For Part B =====================================
//================================== For Type of Activity ===========================

	if (document.frmMeeEduActivity.selActivityId.selectedIndex == 0 ){
        alert ( "Please Select a Type of Activity." );
		document.frmMeeEduActivity.selActivityId.focus();
        return false;
    }

//=============================== If other's Specified ==============================

	if(document.frmMeeEduActivity.selActivityId.value=="Other"){
		if(document.frmMeeEduActivity.txtOthernot.value==""){
			alert("Enter Other Type of Activity.");
			document.frmMeeEduActivity.txtOthernot.focus();
			return false;
		}
		
		if(document.frmMeeEduActivity.txtOthernot.value.length>255){
			alert("Entered Type of Activity is out of range.");
			document.frmMeeEduActivity.txtOthernot.focus();
			return false;
		}
	}
//=============================== For Fee To be Charged =============================

	if(document.frmMeeEduActivity.txtFee.value==""){
		alert("Enter Fee to be Charged.");
		document.frmMeeEduActivity.txtFee.focus();
		return false;
	}

	if(!Number(document.frmMeeEduActivity.txtFee.value)){
		alert("Enter valid Fee.");
		document.frmMeeEduActivity.txtFee.focus();
		return false;
	}
	if(document.frmMeeEduActivity.txtFee.value.indexOf('.')>7){
		alert("Enter valid fees");
		document.frmMeeEduActivity.txtFee.focus();
		return false;
	}
	if((document.frmMeeEduActivity.txtFee.value.indexOf('.'))==-1){
		if(document.frmMeeEduActivity.txtFee.value.length>7){
			alert("Enter valid fees");
			document.frmMeeEduActivity.txtFee.focus();
			return false;
		}
	}

	if(!(document.frmMeeEduActivity.txtFee.value=="")){
		if((document.frmMeeEduActivity.txtFee.value.indexOf('.'))!=-1){
			fstr=document.frmMeeEduActivity.txtFee.value;
			fstr1=document.frmMeeEduActivity.txtFee.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmMeeEduActivity.txtFee.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Enter valid Fee.");
				document.frmMeeEduActivity.txtFee.focus();
				return false;
			}
		}
	}

//================================= For Instructor(s)/Coach(es) ==========================

	if(document.frmMeeEduActivity.txtCoach.value==""){
		alert("Enter Instructor(s)/Coach(es) Name.");
		document.frmMeeEduActivity.txtCoach.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtCoach.value.length>80){
		alert("Entered Instructor(s)/Coach(es) Name is out of range.");
		document.frmMeeEduActivity.txtCoach.focus();
		return false;
	}

	if(isnotAlpha(document.frmMeeEduActivity.txtCoach.value)){
		alert("Enter valid Instructor(s)/Coach(es) Name.");
		document.frmMeeEduActivity.txtCoach.focus();
    	return false;
    }

	if(Number(document.frmMeeEduActivity.txtCoach.value)){
		alert("Enter valid Instructor(s)/Coach(es) Name.");
		document.frmMeeEduActivity.txtCoach.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtCoach.value.indexOf(' ')==0){
		alert("Enter valid Instructor(s)/Coach(es) Name.");
		document.frmMeeEduActivity.txtCoach.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtCoach.value.indexOf('  ')!==-1){
		alert("Enter valid Instructor(s)/Coach(es) Name.");
		document.frmMeeEduActivity.txtCoach.focus();
		return false;
	}

//================================= For Facilities To Be Used ========================

	if(!((document.frmMeeEduActivity.chkFacOne.checked)||(document.frmMeeEduActivity.chkFacTwo.checked)||(document.frmMeeEduActivity.chkFacThree.checked)||(document.frmMeeEduActivity.chkFacFour.checked))){
		alert("Please Select any Facilities to be used Option.");
		return false;
	}

//================for check box==================================================
	
		if (document.frmMeeEduActivity.chkFacFour.checked == true){
			if(document.frmMeeEduActivity.txtOther.value==""){
			alert("Others Filed is empty");
			document.frmMeeEduActivity.txtOther.focus();
			return false;
		}
		if(document.frmMeeEduActivity.txtOther.value.length>255){
			alert("Entered Others Field is out of range.");
			document.frmMeeEduActivity.txtOther.focus();
			return false;
		}
	}
//================================= For HLC Logo ====================================

	if(document.frmMeeEduActivity.txtLogo.value==""){
		alert("Email ID cannot be empty");
		document.frmMeeEduActivity.txtLogo.focus();
		return false;
	}

	if(!(document.frmMeeEduActivity.txtLogo.value== "")){
		strmail1=document.frmMeeEduActivity.txtLogo.value;
		firstat1 = strmail1.indexOf("@");
		lastat1 = strmail1.lastIndexOf("@");
		strmain1=strmail1.substring(0,firstat1);
		strsub1=strmail1.substring(firstat1,document.frmMeeEduActivity.txtLogo.value.length);
		if(strmail1.length>120){
			alert("Email ID is out of range.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		if(strmain1.indexOf('  ')!=-1 || firstat1==0 || strsub1.indexOf('  ')!=-1 ){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		if(isnotSpecial(strmain1) || isnotSpecial(strsub1)){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		k1=0;
		strlen1=strsub1.length;
		for(i1=0;i1<strlen1-1;i1++){
			if(strsub1.charAt(i1)=='.'){
				k1=k1+1;
			}
		}
		if(k1>2){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		if(firstat1==-1 || lastat1==-1){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		if(Number(strmain1)){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		if(firstat1 != lastat1){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
		firstdot1=strmail1.indexOf(".",firstat1);
		lastdot1=strmail1.lastIndexOf(".");
		if(firstdot1 == -1 || lastdot1 == -1 || lastdot1==strmail1.length-1){
			alert("Enter valid Email ID.");
			document.frmMeeEduActivity.txtLogo.focus();
			return false;
		}
	}

//=============================== For Mailing List Format ====================================

	chosen="";
	len = document.frmMeeEduActivity.rdoButton.length ;
	for(i=0;i<len;i++){
		if(document.frmMeeEduActivity.rdoButton[i].checked){
			chosen= document.frmMeeEduActivity.rdoButton[i].value;
		}
	}

	if(chosen==""){
		alert("Select any of the Mailing List Format.");
		return false;
	}

//=============================== For Mailing List ============================================

	chosen1="";
	len1 = document.frmMeeEduActivity.rdoButton1.length ;
	for(i1=0;i1<len1;i1++){
		if(document.frmMeeEduActivity.rdoButton1[i1].checked){
			chosen1= document.frmMeeEduActivity.rdoButton1[i1].value;
		}
	}

	if(chosen1==""){
		alert("Select any of the Mailing List Sent Option.");
		return false;
	}

//============================= For Mailing List Sort =========================================

	chosen2="";
	len2 = document.frmMeeEduActivity.rdoButton2.length ;
	for(i2=0;i2<len2;i2++){
		if(document.frmMeeEduActivity.rdoButton2[i2].checked){
			chosen2= document.frmMeeEduActivity.rdoButton2[i2].value;
		}
	}

	if(chosen2==""){
		alert("Select any of the Mailing List Sort Option.");
		return false;
	}

//============================= For Number of Copies ===========================================

	if(document.frmMeeEduActivity.txtCopies.value==""){
		alert("Enter No. of Brouchure Copies.");
		document.frmMeeEduActivity.txtCopies.focus();
		return false;
	}

	if(!Number(document.frmMeeEduActivity.txtCopies.value)){
		alert("Enter valid No. of Brouchure Copies.");
		document.frmMeeEduActivity.txtCopies.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtCopies.value.length>10){
		alert("Entered valid No. of Brouchure Copies.");
		document.frmMeeEduActivity.txtCopies.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtCopies.value.indexOf('.')!=-1){
		alert("Enter valid No. of Brouchure Copies.");
		document.frmMeeEduActivity.txtCopies.focus();
		return false;
	}

//============================= For Part C ======================================================
//============================== For Land Owner Name ============================================

	if(document.frmMeeEduActivity.txtLandOwner.value==""){
		alert("Enter Land Owner Name.");
		document.frmMeeEduActivity.txtLandOwner.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandOwner.value.length>80){
		alert("Entered Land Owner Name is out of range.");
		document.frmMeeEduActivity.txtLandOwner.focus();
		return false;
	}

	if(isnotAlpha(document.frmMeeEduActivity.txtLandOwner.value)){
		alert("Enter a valid Land Owner Name.");
		document.frmMeeEduActivity.txtLandOwner.focus();
    	return false;
    }

	if(Number(document.frmMeeEduActivity.txtLandOwner.value)){
		alert("Enter valid Land Owner Name.");
		document.frmMeeEduActivity.txtLandOwner.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandOwner.value.indexOf(' ')==0){
		alert("Enter valid Land Owner Name.");
		document.frmMeeEduActivity.txtLandOwner.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandOwner.value.indexOf('  ')!==-1){
		alert("Enter valid Land Owner Name.");
		document.frmMeeEduActivity.txtLandOwner.focus();
		return false;
	}

//============================== For Land Owner Business Name ===============================

	if(document.frmMeeEduActivity.txtBusiName.value==""){
		alert("Enter Land Owner Business Name.");
		document.frmMeeEduActivity.txtBusiName.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtBusiName.value.length>80){
		alert("Entered Land Owner Business Name is out of range.");
		document.frmMeeEduActivity.txtBusiName.focus();
		return false;
	}

	if(isAlpha(document.frmMeeEduActivity.txtBusiName.value)){
		alert("Enter valid Land Owner Business Name.");
		document.frmMeeEduActivity.txtBusiName.focus();
    	return false;
    }

	if(document.frmMeeEduActivity.txtBusiName.value.indexOf(' ')==0){
		alert("Enter valid Land Owner Business Name.");
		document.frmMeeEduActivity.txtBusiName.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtBusiName.value.indexOf('  ')!==-1){
		alert("Enter valid Land Owner Business Name.");
		document.frmMeeEduActivity.txtBusiName.focus();
		return false;
	}

//=================================== For Land Owner Address ===============================

	if(document.frmMeeEduActivity.txtLandAddress.value==""){
		alert("Enter Land Owner Address.");
		document.frmMeeEduActivity.txtLandAddress.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandAddress.value.length>255){
		alert("Entered Land Owner Address is out of range.");
		document.frmMeeEduActivity.txtLandAddress.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandAddress.value.indexOf(' ')==0){
		alert("Enter valid Land Owner Address.");
		document.frmMeeEduActivity.txtLandAddress.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandAddress.value.indexOf('  ')!==-1){
		alert("Enter valid Land Owner Address.");
		document.frmMeeEduActivity.txtLandAddress.focus();
		return false;
	}

//================================= For Land Owner Country ===================================
 
	var cdln = "";
	if(typeof(window.document.frmMeeEduActivity.landOwnerCountry) == 'object'){
		if (window.document.frmMeeEduActivity.landOwnerCountry.value != ""){
			var clid;
			clid = window.document.frmMeeEduActivity.landOwnerCountry[window.document.frmMeeEduActivity.landOwnerCountry.selectedIndex].value;
			cdln = clid;
		}
		if(cdln == "Select One"){
			alert("Please select a Land Owner Country");
			window.document.frmMeeEduActivity.landOwnerCountry.focus();
			return false;
		}
	}

	if(document.frmMeeEduActivity.landOwnerCountry.selectedIndex == 0 ){
        alert ( "Please select a Land Owner Country." );
		document.frmMeeEduActivity.landOwnerCountry.focus();
        return false;
    }

//================================ For Land Owner State ====================================

	var edln = "";
	if(typeof(window.document.frmMeeEduActivity.landOwnerState) == 'object'){
		if (window.document.frmMeeEduActivity.landOwnerState.value != ""){
			var lid;
			lid = window.document.frmMeeEduActivity.landOwnerState[window.document.frmMeeEduActivity.landOwnerState.selectedIndex].value;
			edln = lid;
		}
		if(edln.length == 1){
			alert("Please select Land Owner State.");
			window.document.frmMeeEduActivity.landOwnerState.focus();
			return false;
		}
	} 

	if ( document.frmMeeEduActivity.landOwnerState.selectedIndex == 0 ){
        alert ( "Please select a Land Owner State Name." );
		document.frmMeeEduActivity.landOwnerState.focus();
        return false;
    }

//=============================== For Land Owner City ==========================================

	if(document.frmMeeEduActivity.txtLandCity.value==""){
		alert("Enter Land Owner City Name.");
		document.frmMeeEduActivity.txtLandCity.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandCity.value.length>80){
		alert("Entered Land Owner City Name is out of range.");
		document.frmMeeEduActivity.txtLandCity.focus();
		return false;
	}

	if(isnotAlpha(document.frmMeeEduActivity.txtLandCity.value)){
		alert("Enter valid Land Owner City Name.");
		document.frmMeeEduActivity.txtLandCity.focus();
    	return false;
    }

	if(Number(document.frmMeeEduActivity.txtLandCity.value)){
		alert("Enter valid Land Owner City Name.");
		document.frmMeeEduActivity.txtLandCity.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandCity.value.indexOf(' ')==0){
		alert("Enter valid Land Owner City Name.");
		document.frmMeeEduActivity.txtLandCity.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandCity.value.indexOf('  ')!==-1){
		alert("Enter valid Land Owner City Name.");
		document.frmMeeEduActivity.txtLandCity.focus();
		return false;
	}

//========================= For Organizer Zipcode =============================

	if(document.frmMeeEduActivity.txtLandZip.value==""){
		alert("Enter Zipcode.");
		document.frmMeeEduActivity.txtLandZip.focus();
		return false;
	}

	if(isnotZipcode(document.frmMeeEduActivity.txtLandZip.value)){
		alert("Enter valid Zipcode.");
		document.frmMeeEduActivity.txtLandZip.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandZip.value.length>10){
		alert("Entered Zipcode is out of Range.");
		document.frmMeeEduActivity.txtLandZip.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandZip.value.length<2){
		alert("Entered Zipcode is below Range.");
		document.frmMeeEduActivity.txtLandZip.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandZip.value.indexOf('  ')!=-1){
		alert("Enter valid Zipcode.");
		document.frmMeeEduActivity.txtLandZip.focus();
		return false;
	}

//========================== For Organizer Phone Number ========================

	stringCheck1="`~!@#$%^&*|:;{}[]<>?/=~.,'`;:"+"\\"+"\'";
	if(document.frmMeeEduActivity.txtLandPhone.value==""){
		alert("Land Owner Phone Field Can't Be Blank");
		document.frmMeeEduActivity.txtLandPhone.focus();
		return false;
	}

	if(document.frmMeeEduActivity.txtLandPhone.value!=""){
		len8=document.frmMeeEduActivity.txtLandPhone.value.length;
		strnum8 = document.frmMeeEduActivity.txtLandPhone.value;
		var GoodChars8 = "0123456789()- + ";
		var ls1=document.frmMeeEduActivity.txtLandPhone.value.indexOf('(');
		var ls2=document.frmMeeEduActivity.txtLandPhone.value.indexOf(')');
		var ls5=document.frmMeeEduActivity.txtLandPhone.value.indexOf('+');
		var ls6=document.frmMeeEduActivity.txtLandPhone.value.lastIndexOf('+');
		var ls7=document.frmMeeEduActivity.txtLandPhone.value.indexOf('-');
		var ls8=document.frmMeeEduActivity.txtLandPhone.value.lastIndexOf('-');
		var ls3=1+ls2;
		var ls4=1+ls1;
		if(ls1==ls3){
			alert("Enter valid Land Owner Phone Number");
			document.frmMeeEduActivity.txtLandPhone.focus();
			return false;
		}
		if(ls2==ls4){
			alert("Enter valid Land Owner Phone Number");
			document.frmMeeEduActivity.txtLandPhone.focus();
			return false;
		}
		if(ls5!==ls6){
			alert("Enter valid Land Owner Phone Number");
			document.frmMeeEduActivity.txtLandPhone.focus();
			return false;
		}
		
		if(ls7!==ls8){
			alert("Enter valid Land Owner Phone Number");
			document.frmMeeEduActivity.txtLandPhone.focus();
			return false;
		}

		valid = 1;
		for(j=0;j<len8-1;j++){
			if(GoodChars8.indexOf(strnum8.charAt(j))==-1){
				valid=0;
			}
		}
		if(valid!=1){
			alert("Enter valid Land Owner Phone Number");
			document.frmMeeEduActivity.txtLandPhone.focus();
			return false;
		}
	}

	st8=document.frmMeeEduActivity.txtLandPhone.value;
	fi8=1;
	for(j=0;j<document.frmMeeEduActivity.txtLandPhone.value.length-1;j++){
		if(stringCheck1.indexOf(st8.charAt(j))==-1){
			fi8=0;
		}
	}
	if(fi8!=0){
		alert("Enter valid Land Owner Phone Number.");
		document.frmMeeEduActivity.txtLandPhone.focus();
		return false;
	}
	if(document.frmMeeEduActivity.txtLandPhone.value.indexOf('  ')!==-1){
		alert("Enter valid Land Owner Phone Number.");
		document.frmMeeEduActivity.txtLandPhone.focus();
		return false;
	}

//==================================== For Additional Sites =================================

	if(document.frmMeeEduActivity.chkAddSites.checked==true){
	 
		if(document.frmMeeEduActivity.txtUpload.value==""){
		alert("Please Enter Additional Site Information");
		document.frmMeeEduActivity.txtUpload.focus();
		return false;
		}
		
		if(document.frmMeeEduActivity.txtUpload.value.indexOf(' ')==0){
		alert("Enter valid Additional Site Information");
		document.frmMeeEduActivity.txtUpload.focus();
		return false;
		}
		
		if(document.frmMeeEduActivity.txtUpload.value.length>1024){
		alert("Additional Site Information is out of Range");
		document.frmMeeEduActivity.txtUpload.focus();
		return false;
		}
		
		if(document.frmMeeEduActivity.txtUpload.value.indexOf('  ')!==-1){
		alert("Please avoid white spaces");
		document.frmMeeEduActivity.txtUpload.focus();
		return false;
		}

	}
	
//==================================== For Acceptance of Terms ==============================

	if(document.frmMeeEduActivity.chkAccept.checked==false){
		alert("Please Read and Accept the terms and Conditions by Clicking the Checkbox.");
		document.frmMeeEduActivity.chkAccept.focus();
		return false;
	}


	return true;
}
