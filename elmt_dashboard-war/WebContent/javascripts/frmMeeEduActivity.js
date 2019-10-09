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

function naviTab(tab_id){
	  for(i=1; i<=4; i++){
			tdId =document.getElementById('tabData'+i);
			divId = document.getElementById('part'+i);
			linkId = document.getElementById('link'+i);
			
			if(tab_id==i){
			  tdId.className="tabHighlight";
			  linkId.className="active";
			  tdId.style.borderBottom="0px solid #FFFFFF";
			  divId.style["display"]="block";
			  divId.style["visibility"]="visible";
			}
			else{
			  tdId.className="tabLowlight";
			  linkId.className="inactive";
			  tdId.style.borderBottom="1px solid #999";
			  divId.style["display"]="none";
			  divId.style["visibility"]="hidden";
			}
	  }
}

 
function showLevels(chkBxNam,divId){
		if(document.frmMeeEduActivity.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmMeeEduActivity.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmMeeEduActivity.elements[chkBxId].checked == true){
				document.frmMeeEduActivity.elements[txtBxId].disabled = false;	
		}else {
				document.frmMeeEduActivity.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){
		if(document.frmMeeEduActivity.elements[radBtnId].checked == true){
				document.frmMeeEduActivity.elements[txtBxId].disabled = false;	
		}else{
				document.frmMeeEduActivity.elements[txtBxId].disabled = "disabled";	
		} 
}

function disable(radBtnNewId,txtBxId){
		if(document.frmMeeEduActivity.elements[radBtnNewId].checked == true){
				document.frmMeeEduActivity.elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmMeeEduActivity.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}


/*
function dispAmt(){
	var docForm = document.frmMeeEduActivity;
		if(docForm.selDisp.value != null){
			docForm.amount.value = docForm.selDisp.value;
		}
}
*/

var arHttpRequest;
var req;
     function riderDetails(){
		 param = document.frmMeeEduActivity.hlcNo.value;
		// alert(param);
		 if(param!="" && param.indexOf(' ')!=0){
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
		  else{
	   	  clearRider();
     }
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
 									var faxNo = xmlDoc.getElementsByTagName('faxNo')[0].childNodes[0].nodeValue;
									
								 
									var firstNameObj = document.getElementById("riderFname_id");
								    var lastNameObj = document.getElementById("riderLname_id");
									var phoneObj = document.getElementById("riderPhone_id"); 
							        var faxNoObj = document.getElementById("rFax"); 
									 var prefixObj = document.getElementById("rprefix");  
									var emailIdObj = document.getElementById("rEmail");  
									var address1Obj = document.getElementById("rStreet");  
								   // var address2Obj = document.getElementById("rStreet");  
									var cityObj = document.getElementById("rCity");  
									var stateObj = document.getElementById("rState");  
									var countryObj = document.getElementById("countryId");  
									var zipObj = document.getElementById("rZipcode");  
									var mobileObj = document.getElementById("rCell");  
								 
								 
									
								     firstNameObj.value =  firstName;
								     lastNameObj.value = lastName ;
									 phoneObj.value = phone ; 
							         faxNoObj.value =  faxNo; 
									 prefixObj.value =  prefix;  
									 emailIdObj.value = emailId ;  
									 address1Obj.value =  address1;  
									// address2Obj.value =  address2;  
									 cityObj.value = city ;  
									 stateObj.value = state ;  
									 countryObj.value = country;
									 zipObj.value = zip ;  
									 mobileObj.value = mobile ;  
									
									
							} 
											if(req.status==500) {
												clearRider();
												alert("Membership Id is not valid");
												//addiRider();
												document.frmMeeEduActivity.riderUseaNo_id.focus();
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
		document.getElementById("countryId").value = ""; 
		document.getElementById("rFax").value = "";
}
		
