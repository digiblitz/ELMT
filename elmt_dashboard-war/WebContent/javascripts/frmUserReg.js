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
/*  Javascript Document 
******************************************************************************************************************
	Product Name: Integrated Enterprise Dashboard - Association / Club V0.5
	Organization: digiBlitz Technologies (P) Ltd.
    Created by: K.N.Sathish
    Created Date: 07/08/2006  
*****************************************************************************************************************
*/

var part = new Array("A", "B", "C", "D", "E", "F");
var dom = document.getElementById;
function famAddOnInit(){
	for(i=1; i<=6; i++){
		dom("tabData" + i).style.display = "none";
		dom(i).innerHTML = "PART " + part[i-1];
		if(i==1){
			dom("tabData1").style.display = "block";
		}
	dom("tabData6").style.display = "block";
	dom(6).innerHTML = "PART " + part[1];
	}
}

function famAddOnFilter(){
	for(i=0; i<=6; i++){
		if(document.frmUserSignup.famAdd_sel.value == 1){
			dom("tabData2").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[2];
		}else{
			famAddOnInit();
			dom(6).innerHTML = "PART " + part[1];
		}
		if(document.frmUserSignup.famAdd_sel.value == 2){
			dom("tabData2").style.display = "block";
			dom("tabData3").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[3];
		}else{
			famAddOnInit();
			dom("tabData2").style.display = "block";
			dom(6).innerHTML = "PART " + part[2];
		}
		if(document.frmUserSignup.famAdd_sel.value == 3){
			dom("tabData2").style.display = "block";
			dom("tabData3").style.display = "block";
			dom("tabData4").style.display = "block";
			dom("tabData6").style.display = "block";
			document.getElementById(6).innerHTML = "PART " + part[4];
		}
		if(document.frmUserSignup.famAdd_sel.value == 4){
			dom("tabData2").style.display = "block";
			dom("tabData3").style.display = "block";
			dom("tabData4").style.display = "block";
			dom("tabData5").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[5];
		}
		if(document.frmUserSignup.famAdd_sel.value == 0){
			famAddOnInit();
			dom("tabData1").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[1];
		}
	}
}

			
function initLife() {
			document.getElementById("forLife").style.display = "none";
}

function showHideLife() {
	if ((document.getElementById("one").selected == true)||(document.getElementById("two").selected == true)) {
		//alert("Passed!");
		document.getElementById("forLife").style.display = "block";
	}else{
		document.getElementById("forLife").style.display = "none";
	}
}

function showHideFam() {
	if ((document.getElementById("three").selected == true)) {
		//alert("Passed!");
		document.getElementById("famAddOn").style.display = "block";
	}else{
		document.getElementById("famAddOn").style.display = "none";
	}
}

	/*
function selShowHide(selBx){

		var sBx = selBx;
		var el = document.forms[0].elements;
			for (i=0; i < el.length; i++) {
					if (el[i].name == sBx) {
						if (sBx.options[sBx.selectedIndex].text == "Life Membership") {
							alert("passed!");
							document.getElementById("forFam").style.display = "block";
							}
							else 
							{
							document.getElementById("forFam").style.display = "none";	
							}
					}
					
			}
			
}
*/
function showLevels(chkBxNam,divId){
		if(document.frmUserSignup.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmUserSignup.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmUserSignup.elements[chkBxId].checked == true){
				document.frmUserSignup.elements[txtBxId].disabled = false;	
		}else {
				document.frmUserSignup.elements[txtBxId].disabled = "disabled";	
		} 
}

function disEnTxt(val){
	//alert("got in!");
	var elDoc = document.frmUserSignup.donAmt_sel;
		for(var i=0; i<=elDoc.length; i++){
			//alert("got in!");
			if ( elDoc[i].value != val ){
				 document.frmUserSignup.donAmnt_txt.disabled = "disabled";
			}else {
				 document.frmUserSignup.donAmnt_txt.disabled = "none";
			}
		}	
}

function checkAvailability(){
	if(document.frmUserSignup.chsUserName.length != 0){
		document.frmUserSignup.chkAvail_id.disabled = false; 
	}else{
		document.frmUserSignup.chkAvail_id.disabled = true; 
	}
}

function hide_switchDiv(){
	if(document.frmUserSignup.secAdd_cbx.checked == false){
		document.getElementById('sAdd').style.display = "none";
		document.frmUserSignup.comAdd_sel.value = 1;
	}else{
		document.getElementById('sAdd').style.display = "block";
		document.frmUserSignup.comAdd_sel.value = 2;
	}
	return true;
}

function hideTwo_switchDiv(){
	if(document.frmUserSignup.comAdd_sel.value == 2){
		document.getElementById('sAdd').style.display = "block";
		document.frmUserSignup.secAdd_cbx.checked = true;
	}else{
		document.getElementById('sAdd').style.display = "none";
		document.frmUserSignup.secAdd_cbx.checked = false;
	}
	return true;
}

function defaultPriAdd(){
	if(document.frmUserSignup.priAdd_cbx.checked == false){
		alert("Sorry, you cannot uncheck this option. Primary address is a must.");
		document.frmUserSignup.priAdd_cbx.checked = true;
	}
}

function showHideTabs(){

	if(document.getElementById(divIdOne).style.display == "block"){
			document.getElementById(divIdTwo).style.display = "none";
			document.getElementById(divIdThree).style.display = "none";
			
	}
	if(document.getElementById(divIdTwo).style.display == "block"){
			document.getElementById(divIdThree).style.display = "none";
			document.getElementById(divIdOne).style.display = "none";
	}
	if(document.getElementById(divIdThree).style.display == "block"){
			document.getElementById(divIdOne).style.display = "none";	
			document.getElementById(divIdTwo).style.display = "none";
	}
}

function verify_address()
{
	if (document.frmUserSignup.priSecAdd_cbx.checked == false)
	{
		document.frmUserSignup.sAdd_txt.value = "";
		document.frmUserSignup.sCountry_txt.value = "";
		document.frmUserSignup.sState_txt.value = "";
		document.frmUserSignup.sCity_txt.value = "";
		document.frmUserSignup.sZip_txt.value = "";
	}
	else
	{
		document.frmUserSignup.sAdd_txt.value = document.frmUserSignup.pAdd_txt.value;
		document.frmUserSignup.sCountry_txt.value = document.frmUserSignup.pCountry_sel.value;
		document.frmUserSignup.sState_txt.value = document.frmUserSignup.pState_sel.value;
		document.frmUserSignup.sCity_txt.value = document.frmUserSignup.pCity_txt.value;
		document.frmUserSignup.sZip_txt.value = document.frmUserSignup.pZip_txt.value;

	}
	return true;
}

/*
function copyFieldSets(){
	
		var a,b,c,d,e;			
			a = document.frmUserSignup.sAdd_txt.value = frmUserSignup.pAdd_txt.value;
			b = document.frmUserSignup.sCountry_sel.value = frmUserSignup.pCountry_sel.value;
			c = document.frmUserSignup.sState_sel.value = frmUserSignup.pState_sel.value;
			d = document.frmUserSignup.sCity_txt.value = document.frmUserSignup.pCity_txt.value;
			d = document.frmUserSignup.sZip_txt.value = document.frmUserSignup.pZip_txt.value;
		
		if (a!= "" && b!= "" && c!= "" && d!= ""){
		  	if (document.frmUserSignup.priSecAdd_cbx.checked == true){
				document.frmUserSignup.sAdd_txt.value = document.frmUserSignup.pAdd_txt.value;
				document.frmUserSignup.sCountry_sel.value = document.frmUserSignup.pCountry_sel.value;
				document.frmUserSignup.sState_sel.value = document.frmUserSignup.pState_sel.value;
				document.frmUserSignup.sCity_txt.value = document.frmUserSignup.pCity_txt.value;
				document.frmUserSignup.sZip_txt.value = document.frmUserSignup.pZip_txt.value;
				
				for(var i=0; i<document.frmUserSignup.pCountry_sel.length; i++){
					if ( document.frmUserSignup.sCountry_sel[i].value == document.frmUserSignup.pCountry_sel.value ){
						 document.frmUserSignup.sCountry_sel[i].selected = true;
					}
				}
				
				for(var j=0; j<document.frmUserSignup.pState_sel.length; j++){
					if ( document.frmUserSignup.sState_sel[j].value == document.frmUserSignup.pState_sel.value ){
						 document.frmUserSignup.sState_sel[j].selected = true;
					}
				}
			
			} else {
				document.frmUserSignup.sAdd_txt.value = "";
				document.frmUserSignup.sCountry_sel[0].selected = true;
				document.frmUserSignup.sState_sel[0].selected = true;	
				document.frmUserSignup.sCity_txt.value = "";
				document.frmUserSignup.sZip_txt.value = "";
			} else {
				alert("All fields are mandatory.");	
		}

}
*/

function enableRad(radBtnId,txtBxId){
		if(document.frmUserSignup.elements[radBtnId].checked == true){
				document.frmUserSignup.elements[txtBxId].disabled = false;	
		}else{
				document.frmUserSignup.elements[txtBxId].disabled = "disabled";	
		} 
}
function disable(radBtnNewId,txtBxId){
		if(document.frmUserSignup.elements[radBtnNewId].checked == true){
				document.frmUserSignup.elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmUserSignup.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}


/*
function dispAmt(){
	var docForm = document.frmUserSignup;
		if(docForm.selDisp.value != null){
			docForm.amount.value = docForm.selDisp.value;
		}
}
*/



		