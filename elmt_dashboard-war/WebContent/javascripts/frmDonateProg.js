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
		if(document.frmDonateProg.famAdd_sel.value == 1){
			dom("tabData2").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[2];
		}else{
			famAddOnInit();
			dom(6).innerHTML = "PART " + part[1];
		}
		if(document.frmDonateProg.famAdd_sel.value == 2){
			dom("tabData2").style.display = "block";
			dom("tabData3").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[3];
		}else{
			famAddOnInit();
			dom("tabData2").style.display = "block";
			dom(6).innerHTML = "PART " + part[2];
		}
		if(document.frmDonateProg.famAdd_sel.value == 3){
			dom("tabData2").style.display = "block";
			dom("tabData3").style.display = "block";
			dom("tabData4").style.display = "block";
			dom("tabData6").style.display = "block";
			document.getElementById(6).innerHTML = "PART " + part[4];
		}
		if(document.frmDonateProg.famAdd_sel.value == 4){
			dom("tabData2").style.display = "block";
			dom("tabData3").style.display = "block";
			dom("tabData4").style.display = "block";
			dom("tabData5").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[5];
		}
		if(document.frmDonateProg.famAdd_sel.value == 0){
			famAddOnInit();
			dom("tabData1").style.display = "block";
			dom("tabData6").style.display = "block";
			dom(6).innerHTML = "PART " + part[1];
		}
	}
}


function showAddOn(){
		if(document.frmDonateProg.famAdd_sel.value == 0){
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "none";
			document.getElementById("part3").style.display = "none";
			document.getElementById("part4").style.display = "none";
			document.getElementById("part5").style.display = "none";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "hidden";
			document.getElementById("part3").style.visibility = "hidden";
			document.getElementById("part4").style.visibility = "hidden";
			document.getElementById("part5").style.visibility = "hidden";
		}
		if(document.frmDonateProg.famAdd_sel.value == 1){
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "block";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "visible";
		}else{
			document.getElementById("part2").style.display = "none";
			document.getElementById("part2").style.visibility = "hidden";
		}
		if(document.frmDonateProg.famAdd_sel.value == 2){
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "block";
			document.getElementById("part3").style.display = "block";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "visible";
			document.getElementById("part3").style.visibility = "visible";
		}else{
			document.getElementById("part3").style.display = "none";
			document.getElementById("part3").style.visibility = "hidden";
		}
		if(document.frmDonateProg.famAdd_sel.value == 3){
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "block";
			document.getElementById("part3").style.display = "block";
			document.getElementById("part4").style.display = "block";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "visible";
			document.getElementById("part3").style.visibility = "visible";
			document.getElementById("part4").style.visibility = "visible";
		}else{
			document.getElementById("part4").style.display = "none";
			document.getElementById("part4").style.visibility = "hidden";
		}
		if(document.frmDonateProg.famAdd_sel.value == 4){
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "block";
			document.getElementById("part3").style.display = "block";
			document.getElementById("part4").style.display = "block";
			document.getElementById("part5").style.display = "block";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "visible";
			document.getElementById("part3").style.visibility = "visible";
			document.getElementById("part4").style.visibility = "visible";
			document.getElementById("part5").style.visibility = "visible";
		}else{
			document.getElementById("part5").style.display = "none";
			document.getElementById("part5").style.visibility = "hidden";
		}
	
}

function txtBxDisabEnab(chkBoxId,txtBxId) {
	
	if(document.getElementById(txtBxId).disabled == true){
		if(document.getElementById(txtBxId).value == "Member ID"){
			document.getElementById(txtBxId).disabled = false;
			document.getElementById(txtBxId).value = "";
		}
	}else{
		document.getElementById(txtBxId).disabled = true;
		document.getElementById(txtBxId).value = "Member ID"
	}
}

function txtBxDisabEnab2(chkBoxId,txtBxId) {
	if(document.getElementById(chkBoxId).checked){
		document.getElementById(txtBxId).disabled = false;
	}
	else{
		document.getElementById(txtBxId).disabled = true;
	}
/*	if(document.getElementById(txtBxId).disabled == true){
		if(document.getElementById(txtBxId).value!=""){
			document.getElementById(txtBxId).disabled = false;
			//document.getElementById(txtBxId).value = "";
		}
	}else{
		document.getElementById(txtBxId).disabled = true;
		//document.getElementById(txtBxId).value = 0;
	}*/
}

function chkCB(txtBxId,chkBxId) {
	if(document.getElementById(chkBxId).checked == false){
		document.getElementById(txtBxId).value = 0;
		alert("Please check the corresponding checkbox to enter your Donation Amount.");
	}
}

function chkCBClick(txtBxId,chkBxId) {
	if(document.getElementById(chkBxId).checked == false){
		document.getElementById(txtBxId).value = 0;
	}
}

function chkCBMem(txtBxId,chkBxId) {
	if(document.getElementById(chkBxId).checked == false){
		document.getElementById(txtBxId).value = "";
		alert("Please check the corresponding checkbox to enter your Membership ID.");
	}
}

function chkCBMemClick(txtBxId,chkBxId) {
	if(document.getElementById(chkBxId).checked == false){
		document.getElementById(txtBxId).value = "";
	}
}


function defaultNo(){
	//chosen2="";
	len = document.frmDonateProg.periodicals.length ;
	//alert(len);
		for(i=0;i<len;i++){
			if(document.frmDonateProg.periodicals[i].checked){
				document.frmDonateProg.periodicals[0].checked = false;
				document.frmDonateProg.periodicals[1].checked = true;
				document.getElementById("mAddress").style.display = "none";
				//alert("chosen2:" +chosen2); 
			}
		}
}

function unhideMailOpts(divId,chkBxId) {
	if(document.getElementById(chkBxId).checked != false){
		document.getElementById(divId).style.display = "block";
	}else {
		document.getElementById(divId).style.display = "none";
	}
}

function initLife() {
			//alert("init Life");
			document.getElementById("forLife").style.display = "none";
}

function showHideLife() {
//alert("Passed!");
	if ((document.getElementById("life member").selected == true)||(document.getElementById("full member").selected == true)) {
		//alert("Passed!");
		document.getElementById("famAddOn").style.display = "block";
	}else{
		document.getElementById("famAddOn").style.display = "none";
		
		document.getElementById("part1").style.display = "block";
		document.getElementById("part2").style.display = "none";
		document.getElementById("part3").style.display = "none";
		document.getElementById("part4").style.display = "none";
		document.getElementById("part5").style.display = "none";
		document.getElementById("part1").style.visibility = "visible";
		document.getElementById("part2").style.visibility = "hidden";
		document.getElementById("part3").style.visibility = "hidden";
		document.getElementById("part4").style.visibility = "hidden";
		document.getElementById("part5").style.visibility = "hidden";
	}
}

function showHideLifeRen() {
//alert("Hi!");

	if(document.getElementById("upDown").style.display == "block"){
		//alert("Passed 2");
		var selInd = document.getElementById("memTyp_sel").value;
		selIndObj = selInd.split("#");
		
		if ((selIndObj[1]=="Life Member")||(selIndObj[1]== "Full Member")) {
		//alert("got it");
			document.getElementById("famAddOn").style.display = "block";
		}else{
			document.getElementById("famAddOn").style.display = "none";
			
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "none";
			document.getElementById("part3").style.display = "none";
			document.getElementById("part4").style.display = "none";
			document.getElementById("part5").style.display = "none";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "hidden";
			document.getElementById("part3").style.visibility = "hidden";
			document.getElementById("part4").style.visibility = "hidden";
			document.getElementById("part5").style.visibility = "hidden";
		}
	}else{
		//alert("failed!");
		//alert(document.frmDonateProg.memTypsel.value);
	}
}

function showHideMailAddress() {
	if(document.getElementById("memTyp_sel").selectedIndex != 0){
		//alert(document.getElementById("memTyp_sel").selectedIndex);
		if ((document.getElementById("life member").selected == true)||(document.getElementById("family member").selected == true)) {
			//alert("Passed!");
			document.getElementById("publi").style.display = "none";
		}else{
			document.getElementById("publi").style.display = "block";
		}
	}else{
			document.getElementById("publi").style.display = "none";
	}
}

function showHideMailAddressRen() {
	if(document.getElementById("memTyp_sel").selectedIndex != 0){
		//alert(document.getElementById("memTyp_sel").selectedIndex);
		var selInd = document.getElementById("memTyp_sel").value;
		selIndObj = selInd.split("#");
		
		if ((selIndObj[1]=="Life Member")||(selIndObj[1] =="Family Member")) {
			//alert("Passed!");
			document.getElementById("publi").style.display = "none";
		}else{
			document.getElementById("publi").style.display = "block";
		}
	}else{
			document.getElementById("publi").style.display = "none";
	}
}

function dispMailOptions() {
	if(document.getElementById("memTyp_sel").selectedIndex != 0){
		//alert(document.getElementById("memTyp_sel").selectedIndex);
		var selInd = document.getElementById("memTyp_sel").value;
		selIndObj = selInd.split("#");
		//alert(selIndObj[1]);
		if ((selIndObj[1] =="Family Member")||(selIndObj[1]=="Subscribing Member")) {
			//alert(selIndObj[1] + " Hi");
			document.getElementById("mailOpt").style.display = "none";
		}else{
			document.getElementById("mailOpt").style.display = "block";
		}
	}else{
			//alert(document.getElementById("memTyp_sel").selectedIndex);
			document.getElementById("mailOpt").style.display = "none";
	}
}


function dispAmateurDiv() {
	if(document.getElementById("memTyp_sel").selectedIndex != 0){
		//alert(document.getElementById("memTyp_sel").selectedIndex);
		var selInd = document.getElementById("memTyp_sel").value;
		selIndObj = selInd.split("#");
		//alert(selIndObj[1]);
		if ((selIndObj[1] =="Non-Competing Member")||(selIndObj[1]=="Subscribing Member")) {
			//alert(selIndObj[1] + " Hi");
			document.getElementById("amateurCrd").style.display = "none";
		}else{
			document.getElementById("amateurCrd").style.display = "block";
		}
	}else{
			//alert(document.getElementById("memTyp_sel").selectedIndex);
			document.getElementById("amateurCrd").style.display = "none";
	}
}

function dispAmateurDivRen() {
	
	        var typ= document.frmDonateProg.memTypsel.value;
			//alert("typ :"+typ);
	
		if ((typ == "Non-Competing Member")||(typ == "Subscribing Member")) {
			//alert(selIndObj[1] + " Hi");
			document.getElementById("amateurCrd").style.display = "none";
		}else{
			document.getElementById("amateurCrd").style.display = "block";
		}
	
}


function showHideFam() {
	if ((document.getElementById("family member").selected == true)) {
		//alert("Passed!");
		document.getElementById("forLife").style.display = "block";
	}else{
		document.frmDonateProg.famAdd_sel.value = 0;
		//famAddOnInit();
		document.getElementById("forLife").style.display = "none";
	}
}

function showHideFamRen() {
	//alert(document.frmDonateProg.memTypsel.value);
	var selInd = document.getElementById("memTyp_sel").value;
	selIndObj = selInd.split("#");
	
	if ((selIndObj[1] == "Family Member")) {
		//alert("Passed!");
		document.getElementById("forLife").style.display = "block";
	}else{
		document.frmDonateProg.famAdd_sel.value = 0;
		//famAddOnInit();
		document.getElementById("forLife").style.display = "none";
	}
}

function showHideFamRenLoad() {
	//alert(document.frmDonateProg.memTypsel.value);
	var selInd = document.getElementById("memTypsel").value;
	//selIndObj = selInd.split("#");
	
	if ((selInd == "Full Member" || selInd == "Life Member")) {
		//alert("Passed!");
		document.getElementById("famAddOn").style.display = "block";
		}else{
			document.getElementById("famAddOn").style.display = "none";
			
			document.getElementById("part1").style.display = "block";
			document.getElementById("part2").style.display = "none";
			document.getElementById("part3").style.display = "none";
			document.getElementById("part4").style.display = "none";
			document.getElementById("part5").style.display = "none";
			document.getElementById("part1").style.visibility = "visible";
			document.getElementById("part2").style.visibility = "hidden";
			document.getElementById("part3").style.visibility = "hidden";
			document.getElementById("part4").style.visibility = "hidden";
			document.getElementById("part5").style.visibility = "hidden";
		}
}

function showLevels(chkBxNam,divId){
		if(document.frmDonateProg.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmDonateProg.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmDonateProg.elements[chkBxId].checked == true){
				document.frmDonateProg.elements[txtBxId].disabled = false;	
		}else {
				document.frmDonateProg.elements[txtBxId].disabled = "disabled";	
		} 
}

function disEnTxt(val){
	//alert("got in!");
	var valu = val;
	var elDoc = document.frmDonateProg.donAmt_sel;
		for(var i=0; i<=elDoc.length; i++){
			if ( elDoc[i].value != valu ){
				 document.frmDonateProg.donAmnt_txt.disabled = "disabled";
					//break;
			}else {
				document.frmDonateProg.donAmnt_txt.disabled = false;
break;
			}
		}	

}
function enabSecAddress(){
	
		if(document.frmDonateProg.elements['secAdd_cbx'].checked == true){
				document.frmDonateProg.elements['priSecAdd_cbx'].disabled = false;
				document.frmDonateProg.elements['sadd_txt'].disabled = false;
				document.frmDonateProg.elements['scountry_txt'].disabled = false;
				document.frmDonateProg.elements['sstate_txt'].disabled = false;
				document.frmDonateProg.elements['scity_txt'].disabled = false;
				document.frmDonateProg.elements['szip_txt'].disabled = false;
		}else {
				document.frmDonateProg.elements['priSecAdd_cbx'].disabled = "disabled";
				document.frmDonateProg.elements['sadd_txt'].disabled = "disabled";
				document.frmDonateProg.elements['scountry_txt'].disabled = "disabled";
				document.frmDonateProg.elements['sstate_txt'].disabled = "disabled";
				document.frmDonateProg.elements['scity_txt'].disabled = "disabled";
				document.frmDonateProg.elements['szip_txt'].disabled = "disabled";
		}
}


function hide_switchDiv(){
	if(document.frmDonateProg.secAdd_cbx.checked == false){
		document.getElementById('sAdd').style.display = "none";
		document.frmDonateProg.comAdd_sel.value = "Primary";
	}else{
		document.getElementById('sAdd').style.display = "block";
	}
	return true;
}

function hideTwo_switchDiv(){
	if(document.frmDonateProg.comAdd_sel.value == 2){
		
		document.getElementById('sAdd').style.display = "block";
		document.frmDonateProg.secAdd_cbx.checked = true;
	}else{
		document.getElementById('sAdd').style.display = "block";
		document.frmDonateProg.secAdd_cbx.checked = true;
	}
	return true;
}

function defaultPriAdd(){
	if(document.frmDonateProg.priAdd_cbx.checked == false){
		alert("Sorry, you cannot uncheck this option. Primary address is a must.");
		document.frmDonateProg.priAdd_cbx.checked = true;
	}
}


function verify_address()
{
	if (document.frmDonateProg.priSecAdd_cbx.checked == false)
	{
		document.frmDonateProg.sadd_txt.value = "";
		document.frmDonateProg.sadd_txt1.value = "";
		document.frmDonateProg.scountry_txt.value = "";
		document.frmDonateProg.sstate_txt.value = "";
		document.frmDonateProg.scity_txt.value = "";
		document.frmDonateProg.szip_txt.value = "";
		document.frmDonateProg.sphone_txt.value = "";
		document.frmDonateProg.smob_txt.value = "";
		document.frmDonateProg.sfax_txt.value = "";
	}
	else
	{
		document.frmDonateProg.sadd_txt.value = document.frmDonateProg.padd_txt.value;
		document.frmDonateProg.sadd_txt1.value = document.frmDonateProg.padd_txt2.value;
		document.frmDonateProg.scountry_txt.value = document.frmDonateProg.pcountry_sel.value;
		document.frmDonateProg.sstate_txt.value = document.frmDonateProg.pstate_sel.value;
		document.frmDonateProg.scity_txt.value = document.frmDonateProg.pcity_txt.value;
		document.frmDonateProg.szip_txt.value = document.frmDonateProg.pzip_txt.value;
		document.frmDonateProg.sphone_txt.value = document.frmDonateProg.pphone_txt.value;
		document.frmDonateProg.smob_txt.value = document.frmDonateProg.pmob_txt.value;
		document.frmDonateProg.sfax_txt.value = document.frmDonateProg.pfax_txt.value;
		FillState(document.frmDonateProg.scountry_txt, document.frmDonateProg.sstate_txt, document.frmDonateProg.pstate_sel.value);
	}
	return true;
}

/*
function copyFieldSets(){
	
		var a,b,c,d,e;			
			a = document.frmDonateProg.sadd_txt.value = frmDonateProg.padd_txt.value;
			b = document.frmDonateProg.scountry_sel.value = frmDonateProg.pcountry_sel.value;
			c = document.frmDonateProg.sstate_sel.value = frmDonateProg.pstate_sel.value;
			d = document.frmDonateProg.scity_txt.value = document.frmDonateProg.pcity_txt.value;
			d = document.frmDonateProg.szip_txt.value = document.frmDonateProg.pzip_txt.value;
		
		if (a!= "" && b!= "" && c!= "" && d!= ""){
		  	if (document.frmDonateProg.priSecAdd_cbx.checked == true){
				document.frmDonateProg.sadd_txt.value = document.frmDonateProg.pdd_txt.value;
				document.frmDonateProg.scountry_sel.value = document.frmDonateProg.pcountry_sel.value;
				document.frmDonateProg.sstate_sel.value = document.frmDonateProg.pstate_sel.value;
				document.frmDonateProg.scity_txt.value = document.frmDonateProg.pcity_txt.value;
				document.frmDonateProg.szip_txt.value = document.frmDonateProg.pzip_txt.value;
				
				for(var i=0; i<document.frmDonateProg.pCountry_sel.length; i++){
					if ( document.frmDonateProg.scountry_sel[i].value == document.frmDonateProg.pcountry_sel.value ){
						 document.frmDonateProg.scountry_sel[i].selected = true;
					}
				}
				
				for(var j=0; j<document.frmDonateProg.pstate_sel.length; j++){
					if ( document.frmDonateProg.sstate_sel[j].value == document.frmDonateProg.pstate_sel.value ){
						 document.frmDonateProg.sstate_sel[j].selected = true;
					}
				}
			
			} else {
				document.frmDonateProg.sadd_txt.value = "";
				document.frmDonateProg.scountry_sel[0].selected = true;
				document.frmDonateProg.sstate_sel[0].selected = true;	
				document.frmDonateProg.scity_txt.value = "";
				document.frmDonateProg.szip_txt.value = "";
			} else {
				alert("All fields are mandatory.");	
		}

}
*/

function enableRad(radBtnId,txtBxId){
		if(document.frmDonateProg.elements[radBtnId].checked == true){
				document.frmDonateProg.elements[txtBxId].disabled = false;	
		}else{
				document.frmDonateProg.elements[txtBxId].disabled = "disabled";	
		} 
}
function disable(radBtnNewId,txtBxId){
		if(document.frmDonateProg.elements[radBtnNewId].checked == true){
				document.frmDonateProg.elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
	//alert(radVal);
		if(document.frmDonateProg.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function showHideSearch(radioNam,divId,radVal){
	//alert(radVal);
		if(document.searchRadio.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}


		
function showHideRadioRenew(radioNam,divId,radVal){
		if(document.frmDonateProg.elements[radioNam].value != radVal){
				document.getElementById(divId).style.display = "none";	
		}
		else {
				document.getElementById(divId).style.display = "block";	
		} 
}

function clearMemAmt()
	{
		document.frmDonateProg.amount_txt.value=0;		
	}

/*
function dispAmt(){
	var docForm = document.frmDonateProg;
		if(docForm.selDisp.value != null){
			docForm.amount.value = docForm.selDisp.value;
		}
}
*/

function showHideTabs(){

	if(document.getElementById(divIdOne).style.display == "block"){
		document.getElementById('li1').className = "selected";
		document.getElementById('li2').className = "none";
		document.getElementById('li3').className = "none";
			document.getElementById(divIdTwo).style.display = "none";
			document.getElementById(divIdThree).style.display = "none";
			
	}
	if(document.getElementById(divIdTwo).style.display == "block"){
		document.getElementById('li2').className = "selected";
		document.getElementById('li1').className = "none";
		document.getElementById('li3').className = "none";
			document.getElementById(divIdThree).style.display = "none";
			document.getElementById(divIdOne).style.display = "none";
	}
	if(document.getElementById(divIdThree).style.display == "block"){
		document.getElementById('li3').className = "selected";
		document.getElementById('li1').className = "none";
		document.getElementById('li2').className = "none";
			document.getElementById(divIdOne).style.display = "none";	
			document.getElementById(divIdTwo).style.display = "none";
	}
}

//----------- show amount ------------------------

function Dispall()
{
 lenfee=document.frmDonateProg.selDisp.length;
//alert(lenfee);

  for (i = 0; i < lenfee; i++)
{
	if(document.frmDonateProg.selDisp[i].selected)

 {
   chosenstr= document.frmDonateProg.selDisp[i].value;
   n = chosenstr.lastIndexOf("#");
   substr= chosenstr.substring((n+1),chosenstr.length);
   chosenfee=Number(substr);
   document.frmDonateProg.amount_txt.value=chosenfee;
}

}
}

function Dispmail()
{

var ctrylen=document.frmDonateProg.mailAddr.length;

  for (i = 0; i < ctrylen; i++)
{
	if(document.frmDonateProg.mailAddr[i].selected)

 {
   chosenstr= document.frmDonateProg.mailAddr[i].value;
   n = chosenstr.lastIndexOf("#");
   substr= chosenstr.substring((n+1),chosenstr.length);
   chosenfee=Number(substr);
   document.frmDonateProg.amountTwo_txt.value=chosenfee;
}
}
}

//------------------------------ Member Registration Member Id Existance Validate Ajax Script -------------------------

var arHttpRequest;

function HLCMemberDetails()
{
	clr();
	
	if(document.frmDonateProg.memberid.value!="" && document.frmDonateProg.memberid.value.indexOf(" ")!=0)
	{
    var memberid=document.frmDonateProg.memberid.value;


   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {membStatus(); } ; 
        arHttpRequest.send(null); 
   } 
	}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function membStatus() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
                if(arnameText=="false")
                {    
					alert("Member Id Doesn't Exists!"); 
					document.frmDonateProg.memberid.value="";
					document.frmDonateProg.memberid.focus();
					return false;
                }
				else    
				{
					famMemberStatus();
				}

            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 


//------------------------------ Member Registration Family Member Parent MID Status Validate Ajax Script -------------------------

var msHttpRequest;
var memberid;

function famMemberStatus()
{

	if(document.frmDonateProg.memberid.value!="" && document.frmDonateProg.memberid.value.indexOf(" ")!=0)
	{
    memberid=document.frmDonateProg.memberid.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./MembStatusAjaxAction.do?&mid="+memberid; 

        if (window.ActiveXObject) 
        { 
            msHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            msHttpRequest = new XMLHttpRequest(); 
        } 
     
        msHttpRequest.open("POST", url, true); 
        
        msHttpRequest.onreadystatechange = function() {famStat(); } ; 
        msHttpRequest.send(null); 
   } 
	}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function famStat() 
    { 
   
        if (msHttpRequest.readyState == 4) 
        { 
            if(msHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = msHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
 		var mdetails=new Array();
        mdetails=null;
        mdetails=arnameText.split("#");
        var status="";
		var mtyp="";
		var addct="";
		
        if(mdetails[0]==NaN||mdetails[0]==undefined||mdetails[0]==null||mdetails[0]==""||mdetails[0].length==0)
        {
        	status="";
        }
        else
		{
        	status=mdetails[0];
		}

 		if(mdetails[1]==NaN||mdetails[1]==undefined||mdetails[1]==null||mdetails[1]==""||mdetails[1].length==0)
        {
        	mtyp="";
        }
        else
		{
        	mtyp=mdetails[1];
		}
		
		if(mdetails[2]==NaN||mdetails[2]==undefined||mdetails[2]==null||mdetails[2]==""||mdetails[2].length==0)
        {
        	addct="";
        }
        else
		{
        	addct=mdetails[2];
		}

		//alert("status"+status);alert("mtyp"+mtyp);

                if(status != "Active")
                {
						/*if(mtyp != "Full Member" && mtyp != "Life Member")
						{*/
							alert("The Parent Member Id is not Active !"); 
							document.frmDonateProg.memberid.value="";
							document.frmDonateProg.memberid.focus();							
							return false;
               			//}  
				}
	 
				else if(mtyp != "Full Member" && mtyp != "Life Member")
				{
						alert("The Parent Member Id is not valid !"); 
						document.frmDonateProg.memberid.value="";
						document.frmDonateProg.memberid.focus();
						return false;
               	}  
				
				else if(addct>=4)
				{
						alert("The Parent Member Id already have 4 Members !"); 
						document.frmDonateProg.memberid.value="";
						document.frmDonateProg.memberid.focus();
						return false;
               	}
				else
				{
					//alert(memberid);
					details(memberid);
				}
 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 



//------------------------------ Family add-on mail id existance Ajax Script -------------------------


var adHttpRequest1;

function addOne()
{
	
       var mailId=null;
    if(document.frmDonateProg.loginName1.value!="" && document.frmDonateProg.loginName1.value.indexOf(" ")!=0)
	{
       mailId=document.frmDonateProg.loginName1.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+mailId; 

        if (window.ActiveXObject) 
        { 
            adHttpRequest1 = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            adHttpRequest1 = new XMLHttpRequest(); 
        } 
     
        adHttpRequest1.open("POST", url, true); 
        
        adHttpRequest1.onreadystatechange = function() {emailStatus1(); } ; 
        adHttpRequest1.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailStatus1() 
    { 
   
        if (adHttpRequest1.readyState == 4) 
        { 
            if(adHttpRequest1.status == 200) 
            { 
                //get the XML send by the servlet 
                 var adnameXML = adHttpRequest1.responseXML.getElementsByTagName("email")[0]; 
                 var adnameText= adnameXML.childNodes[0].nodeValue; 
                  //alert
                if(adnameText!=null)
                {    
                alert("User Name Exists! Choose Other Name"); 
            	document.frmDonateProg.loginName1.focus();
				document.frmDonateProg.loginName1.value="";
                return false;
                }    
                
            } 
            else 
            { 
                alert("Error loading page\n"+ adHttpRequest1.status +":"+ adHttpRequest1.statusText); 
            } 
        } 
    } 

//---------------------

var adHttpRequest2;

function addTwo()
{
     var mailId=null;
	if(document.frmDonateProg.loginName2.value!="" && document.frmDonateProg.loginName2.value.indexOf(" ")!=0)
	{
     mailId=document.frmDonateProg.loginName2.value;
   


   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+mailId; 

        if (window.ActiveXObject) 
        { 
            adHttpRequest2 = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            adHttpRequest2 = new XMLHttpRequest(); 
        } 
     
        adHttpRequest2.open("POST", url, true); 
        
        adHttpRequest2.onreadystatechange = function() {emailStatus2(); } ; 
        adHttpRequest2.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailStatus2() 
    { 
   
        if (adHttpRequest2.readyState == 4) 
        { 
            if(adHttpRequest2.status == 200) 
            { 
                //get the XML send by the servlet 
                 var adnameXML = adHttpRequest2.responseXML.getElementsByTagName("email")[0]; 
                 var adnameText= adnameXML.childNodes[0].nodeValue; 
                 
               //alert
                if(adnameText!=null)
                {    
                alert("User Name Exists! Choose Other Name"); 
            	document.frmDonateProg.loginName2.focus();
				document.frmDonateProg.loginName2.value="";
                return false;
                }    
               
            } 
            else 
            { 
                alert("Error loading page\n"+ adHttpRequest2.status +":"+ adHttpRequest2.statusText); 
            } 
        } 
    } 
     

//-------------------------------

var adHttpRequest3;

function addThree()
{
     var mailId=null;
	if(document.frmDonateProg.loginName3.value!="" && document.frmDonateProg.loginName3.value.indexOf(" ")!=0)
	{
     mailId=document.frmDonateProg.loginName3.value;
    

/** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+mailId; 

        if (window.ActiveXObject) 
        { 
            adHttpRequest3 = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            adHttpRequest3 = new XMLHttpRequest(); 
        } 
     
        adHttpRequest3.open("POST", url, true); 
        
        adHttpRequest3.onreadystatechange = function() {emailStatus3(); } ; 
        adHttpRequest3.send(null); 
    }
	}
/** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailStatus3() 
    { 
   
        if (adHttpRequest3.readyState == 4) 
        { 
            if(adHttpRequest3.status == 200) 
            { 
                //get the XML send by the servlet 
                 var adnameXML = adHttpRequest3.responseXML.getElementsByTagName("email")[0]; 
                 var adnameText= adnameXML.childNodes[0].nodeValue; 
                 
               //alert
                if(adnameText!=null)
                {    
                alert("User Name Exists! Choose Other Name"); 
            	document.frmDonateProg.loginName3.focus();
				document.frmDonateProg.loginName3.value="";
                return false;
                }    
				
               
            } 
            else 
            { 
                alert("Error loading page\n"+ adHttpRequest3.status +":"+ adHttpRequest3.statusText); 
            } 
        } 
    } 

  
//----------------------

var adHttpRequest4;

function addFour()
{
     var mailId=null;
	if(document.frmDonateProg.loginName4.value!="" && document.frmDonateProg.loginName4.value.indexOf(" ")!=0)
	{

     mailId=document.frmDonateProg.loginName4.value;
     
/** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userName="+mailId; 

        if (window.ActiveXObject) 
        { 
            adHttpRequest4 = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            adHttpRequest4 = new XMLHttpRequest(); 
        } 
     
        adHttpRequest4.open("POST", url, true); 
        
        adHttpRequest4.onreadystatechange = function() {emailStatus4(); } ; 
        adHttpRequest4.send(null); 
    }
}
/** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function emailStatus4() 
    { 
   
        if (adHttpRequest4.readyState == 4) 
        { 
            if(adHttpRequest4.status == 200) 
            { 
                //get the XML send by the servlet 
                 var adnameXML = adHttpRequest4.responseXML.getElementsByTagName("email")[0]; 
                 var adnameText= adnameXML.childNodes[0].nodeValue; 
                 
                 //alert
                if(adnameText!=null)
                {    
                alert("User Name Exists! Choose Other Name"); 
            	document.frmDonateProg.loginName4.focus();
				document.frmDonateProg.loginName4.value="";
                return false;
                }    
				
               
            } 
            else 
            { 
                alert("Error loading page\n"+ adHttpRequest4.status +":"+ adHttpRequest4.statusText); 
            } 
        } 
    } 


               
function getTxt_Stat()
{
 //alert("inside");
	var selObj = document.getElementById('donAmt_sel');
	var selIndex = selObj.selectedIndex;
	var selTxt = selObj.options[selIndex].text;
	//alert(selTxt);
	var selVal = selObj.options[selIndex].value;
	//alert(selVal);
	if (selTxt == "Others" ){
       document.frmDonateProg.otherdon.disabled=false;
    }
	else{
       document.frmDonateProg.otherdon.disabled=true;
	}
}



// =============================================

 //================== add on 1 ==================================
 
  function nonUserDetails(param){
	  //alert('hi');
//					alert(param.value);
						if(param.value.length==0 || param.value.indexOf(" ")==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						//alert(url);

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 
							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest(){ 
						if (req.readyState == 4){ 
						   //alert(req.readyState);
							if(req.status == 200){ 
							//alert(req.status);
								//clearFields();
								//get the XML send by the servlet 
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									//var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
									//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 

									var fNameObj = document.getElementById("firstNameId1");
									var lNameObj = document.getElementById("lastNameId1");
									var phObj = document.getElementById("phoneId1");  
									
									fNameObj.value= fName;
									lNameObj.value=lName;
									
									//alert(firstNameObj.value);
									//alert(lastNameObj.value);
									//document.AnnualRegForm.newBadge.value=fName+" "+lName;
									var phLen = ph.length;
									var minPhLen = phLen - 4;
									phObj.value=ph.substring(minPhLen,phLen);
									
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
						
					}
					
	//================== add on 2 ==================================
 
  function nonUserDetails2(param){
	  //alert('hi');
//					alert(param.value);
						if(param.value.length==0 || param.value.indexOf(" ")==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						//alert(url);

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 
							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest2;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest2(){ 
						if (req.readyState == 4){ 
						   //alert(req.readyState);
							if(req.status == 200){ 
							//alert(req.status);
								//clearFields();
								//get the XML send by the servlet 
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									//var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
									//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 

									var fNameObj = document.getElementById("firstNameId2");
									var lNameObj = document.getElementById("lastNameId2");
									var phObj = document.getElementById("phoneId2");  
									
									fNameObj.value= fName;
									lNameObj.value=lName;
									
									//alert(firstNameObj.value);
									//alert(lastNameObj.value);
									//document.AnnualRegForm.newBadge.value=fName+" "+lName;
									var phLen = ph.length;
									var minPhLen = phLen - 4;
									phObj.value=ph.substring(minPhLen,phLen);
									
							} 
							if(req.status==500) {
							  alert("userName does'nt exists");
							  clear2();
							  document.myform.userNameId2.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
							} 
							  
						}	
					} 
		          function clear2() {
						document.getElementById("userNameId2").value = "";
						document.getElementById("firstNameId2").value = "";
						document.getElementById("lastNameId2").value = "";
						document.getElementById("phoneId2").value = "";
						
					}
 
 
 //================== add on 3 ==================================
 
  function nonUserDetails3(param){
	  //alert('hi');
//					alert(param.value);
						if(param.value.length==0 || param.value.indexOf(" ")==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						//alert(url);

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 
							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest3;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest3(){ 
						if (req.readyState == 4){ 
						   //alert(req.readyState);
							if(req.status == 200){ 
							//alert(req.status);
								//clearFields();
								//get the XML send by the servlet 
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									//var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
									//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 

									var fNameObj = document.getElementById("firstNameId3");
									var lNameObj = document.getElementById("lastNameId3");
									var phObj = document.getElementById("phoneId3");  
									
									fNameObj.value= fName;
									lNameObj.value=lName;
									
									//alert(firstNameObj.value);
									//alert(lastNameObj.value);
									//document.AnnualRegForm.newBadge.value=fName+" "+lName;
									var phLen = ph.length;
									var minPhLen = phLen - 4;
									phObj.value=ph.substring(minPhLen,phLen);
									
							} 
							if(req.status==500) {
							  alert("userName does'nt exists");
							  clear3();
							  document.myform.userNameId3.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
							} 
							  
						}	
					} 
		          function clear3() {
						document.getElementById("userNameId3").value = "";
						document.getElementById("firstNameId3").value = "";
						document.getElementById("lastNameId3").value = "";
						document.getElementById("phoneId3").value = "";
						
					}
 
 
 //================== add on 4 ==================================
 
  function nonUserDetails4(param){
	  //alert('hi');
//					alert(param.value);
						if(param.value.length==0 || param.value.indexOf(" ")==0)
						return;
							var  url = null;
							url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);
						//alert(url);

							if (window.ActiveXObject){ 
								req = new ActiveXObject("Microsoft.XMLHTTP"); 
							} 
							else if (window.XMLHttpRequest){ 
								req = new XMLHttpRequest(); 
							} 
						
							req.onreadystatechange = processRequest4;         
							req.open("GET", url, true);
							req.send(null);  
				} 
				   
                     function processRequest4(){ 
						if (req.readyState == 4){ 
						   //alert(req.readyState);
							if(req.status == 200){ 
							//alert(req.status);
								//clearFields();
								//get the XML send by the servlet 
								
       								userMembExist();
									
								    var xmlDoc = req.responseXML.documentElement;
									var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
									var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
									var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
									//var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
									//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
									//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 

									var fNameObj = document.getElementById("firstNameId4");
									var lNameObj = document.getElementById("lastNameId4");
									var phObj = document.getElementById("phoneId4");  
									
									fNameObj.value= fName;
									lNameObj.value=lName;
									
									//alert(firstNameObj.value);
									//alert(lastNameObj.value);
									//document.AnnualRegForm.newBadge.value=fName+" "+lName;
									var phLen = ph.length;
									var minPhLen = phLen - 4;
									phObj.value=ph.substring(minPhLen,phLen);
									
							} 
							if(req.status==500) {
							  alert("userName does'nt exists");
							  clear4();
							  document.myform.userNameId4.focus();
							  return;
							}
							else{ 
								//alert("Error loading page\n"+ req.status +":"+ req.statusText); 
							} 
							  
						}	
					} 
		          function clear4() {
						document.getElementById("userNameId4").value = "";
						document.getElementById("firstNameId4").value = "";
						document.getElementById("lastNameId4").value = "";
						document.getElementById("phoneId4").value = "";
						
					}


//================== display parent member details =======================

var httpRequest;

function details(rid)
{
//alert(rid);


  // rid=//document.myform.hlcNo.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "RiderInfoAjaxAction.do?rid="+rid; 

        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processRequest10; 
        httpRequest.send(null); 
   } 
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest10() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("salutation")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML10(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML10(salutationXML) 
    { 

        

        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
        var rdetails=new Array();
        rdetails=null;
        rdetails=salutationText.split("#");
        
        clr();
		
        if(rdetails[1]==NaN || rdetails[1]==undefined || rdetails[1]==null || rdetails[1]=="null" || rdetails[1]=="")
        {
        document.myform.firstNameId5.value="NA";
        }else
        document.myform.firstNameId5.value=rdetails[1];

		 if(rdetails[3]==NaN || rdetails[3]==undefined || rdetails[3]==null || rdetails[3]=="null" || rdetails[3]=="")
        {
        document.myform.lastNameId5.value="NA";
        }else
        document.myform.lastNameId5.value=rdetails[3];


        if(rdetails[10]==NaN || rdetails[10]==undefined || rdetails[10]==null || rdetails[10]=="null" || rdetails[10]=="")
        {
        	document.myform.phoneId5.value="NA";
        }else
		{
			if(rdetails[10].length>4)
			{
				var phLen = rdetails[10].length;
				var minPhLen = phLen - 4;
				document.myform.phoneId5.value=rdetails[10].substring(minPhLen,phLen);
			}
			else
			{
				document.myform.phoneId5.value=rdetails[10];
			}
	    } 

	}
function clr()
{
	document.myform.firstNameId5.value="";
	document.myform.lastNameId5.value="";
	document.myform.phoneId5.value="";
}


//================== display Country Price =======================

function ctryPrize()
{
//alert(rid);

   var memTypSel=document.myform.memTyp_sel.value
   memTypId=memTypSel.split("#");
   var ctry=document.myform.country.value;
   
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "./MembCtryPrizeAjax.do?ctryName="+ctry+"&memTypId="+memTypId[0]; 
//alert(url);
        if (window.ActiveXObject) 
        { 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("GET", url, true); 
        
        httpRequest.onreadystatechange =processRequest11; 
        httpRequest.send(null); 
   } 
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest11() 
    { 
   
        if (httpRequest.readyState == 4) 
        { 
            if(httpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("ctryPrice")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
                //Update the HTML 
                updateHTML11(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML11(salutationXML) 
    { 

        

        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
       
        
        
		 if(salutationText==undefined || salutationText==null || salutationText=="null" || salutationText=="")
        {
        document.myform.ctryAmt.value="0";
        }else
        document.myform.ctryAmt.value=salutationText;
	Sumup();

	}


//----------------------------------------------

var mtypHttpRequest;

function memberTyp()
{
	
    var memberid=document.frmDonateProg.memberid.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 

        if (window.ActiveXObject) 
        { 
            mtypHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");   
        } 
        else if (window.XMLHttpRequest) 
        { 
            mtypHttpRequest = new XMLHttpRequest(); 
        } 
     
        mtypHttpRequest.open("POST", url, true); 
        
        mtypHttpRequest.onreadystatechange = function() {
			memTypStatus(); 
			}  
        mtypHttpRequest.send(null); 
   
}

/** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function memTypStatus() 
    { 
   
        if (mtypHttpRequest.readyState == 4) 
        { 
            if(mtypHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = mtypHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
		 alert(arnameText);		
			
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 


//------------------------------ Member Existance Based on User Name1 Ajax Script -------------------------

var usrHttpRequest;

function userMembExist()
{
//alert(document.frmDonateProg.userNameId1.value);
	if(document.frmDonateProg.userNameId1.value!="" && document.frmDonateProg.userNameId1.value.indexOf(' ')!=0)
	{

    var userNameId1=document.frmDonateProg.userNameId1.value;
    
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userMember="+userNameId1; 

        if (window.ActiveXObject) 
        { 
            usrHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            usrHttpRequest = new XMLHttpRequest(); 
        } 
     
        usrHttpRequest.open("POST", url, true); 
        
        usrHttpRequest.onreadystatechange = function() {userMembRequest(); } ; 
        usrHttpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function userMembRequest() 
    { 
   
        if (usrHttpRequest.readyState == 4) 
        { 
            if(usrHttpRequest.status == 200) 
            { 

                //get the XML send by the servlet 
                 var prnameXML = usrHttpRequest.responseXML.getElementsByTagName("existance")[0]; 
                 var prnameText = prnameXML.childNodes[0].nodeValue; 
                 // alert(prnameText);               
				if(prnameText == "true")
				{
					alert("The User is Already A Member !!");
					clear();
					document.frmDonateProg.userNameId1.focus();
					return false;
				}
				 
            } 
            else 
            { 
                alert("Error loading page\n"+ usrHttpRequest.status +":"+ usrHttpRequest.statusText); 
            } 
        } 
    } 

//------------------------------ Member Existance Based on User Name2 Ajax Script -------------------------

var usr2HttpRequest;

function userMembExist2()
{
//alert(document.frmDonateProg.userNameId1.value);
	if(document.frmDonateProg.userNameId2.value!="" && document.frmDonateProg.userNameId2.value.indexOf(' ')!=0)
	{

    var userNameId1=document.frmDonateProg.userNameId2.value;
    
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userMember="+userNameId1; 

        if (window.ActiveXObject) 
        { 
            usr2HttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            usr2HttpRequest = new XMLHttpRequest(); 
        } 
     
        usr2HttpRequest.open("POST", url, true); 
        
        usr2HttpRequest.onreadystatechange = function() {userMembRequest2(); } ; 
        usr2HttpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function userMembRequest2() 
    { 
   
        if (usr2HttpRequest.readyState == 4) 
        { 
            if(usr2HttpRequest.status == 200) 
            { 

                //get the XML send by the servlet 
                 var prnameXML = usr2HttpRequest.responseXML.getElementsByTagName("existance")[0]; 
                 var prnameText = prnameXML.childNodes[0].nodeValue; 
                 // alert(prnameText);               
				if(prnameText == "true")
				{
					alert("The User is Already A Member !!");
					clear2();
					document.frmDonateProg.userNameId2.focus();
					return false;
				}
				 
            } 
            else 
            { 
                alert("Error loading page\n"+ usr2HttpRequest.status +":"+ usr2HttpRequest.statusText); 
            } 
        } 
    } 
	
	//------------------------------ Member Existance Based on User Name3 Ajax Script -------------------------

var usr3HttpRequest;

function userMembExist3()
{
//alert(document.frmDonateProg.userNameId1.value);
	if(document.frmDonateProg.userNameId3.value!="" && document.frmDonateProg.userNameId3.value.indexOf(' ')!=0)
	{

    var userNameId1=document.frmDonateProg.userNameId3.value;
    
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userMember="+userNameId1; 

        if (window.ActiveXObject) 
        { 
            usr3HttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            usr3HttpRequest = new XMLHttpRequest(); 
        } 
     
        usr3HttpRequest.open("POST", url, true); 
        
        usr3HttpRequest.onreadystatechange = function() {userMembRequest3(); } ; 
        usr3HttpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function userMembRequest3() 
    { 
   
        if (usr3HttpRequest.readyState == 4) 
        { 
            if(usr3HttpRequest.status == 200) 
            { 

                //get the XML send by the servlet 
                 var prnameXML = usr3HttpRequest.responseXML.getElementsByTagName("existance")[0]; 
                 var prnameText = prnameXML.childNodes[0].nodeValue; 
                 // alert(prnameText);               
				if(prnameText == "true")
				{
					alert("The User is Already A Member !!");
					clear3();
					document.frmDonateProg.userNameId3.focus();
					return false;
				}
				 
            } 
            else 
            { 
                alert("Error loading page\n"+ usr3HttpRequest.status +":"+ usr3HttpRequest.statusText); 
            } 
        } 
    } 
	
	//------------------------------ Member Existance Based on User Name4 Ajax Script -------------------------

var usr4HttpRequest;

function userMembExist4()
{
//alert(document.frmDonateProg.userNameId1.value);
	if(document.frmDonateProg.userNameId4.value!="" && document.frmDonateProg.userNameId4.value.indexOf(' ')!=0)
	{

    var userNameId1=document.frmDonateProg.userNameId4.value;
    
   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?userMember="+userNameId1; 

        if (window.ActiveXObject) 
        { 
            usr4HttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            usr4HttpRequest = new XMLHttpRequest(); 
        } 
     
        usr4HttpRequest.open("POST", url, true); 
        
        usr4HttpRequest.onreadystatechange = function() {userMembRequest4(); } ; 
        usr4HttpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function userMembRequest4() 
    { 
   
        if (usr4HttpRequest.readyState == 4) 
        { 
            if(usr4HttpRequest.status == 200) 
            { 

                //get the XML send by the servlet 
                 var prnameXML = usr4HttpRequest.responseXML.getElementsByTagName("existance")[0]; 
                 var prnameText = prnameXML.childNodes[0].nodeValue; 
                 // alert(prnameText);               
				if(prnameText == "true")
				{
					alert("The User is Already A Member !!");
					clear4();
					document.frmDonateProg.userNameId4.focus();
					return false;
				}
				 
            } 
            else 
            { 
                alert("Error loading page\n"+ usr4HttpRequest.status +":"+ usr4HttpRequest.statusText); 
            } 
        } 
    } 
