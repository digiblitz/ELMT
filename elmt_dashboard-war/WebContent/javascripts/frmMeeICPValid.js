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
function isnotInteger(str){
stringIntCheck="0123456789";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}

function onradioclear(){
	document.frmMeeInsureEduAct.memberShipNo.value=="";	
}

//============================for Special char=============================

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

function myvalidate(){
  if(document.frmMeeUserICPAssessInsure.chkAccept.checked==false){
		alert("Please accept the Terms and Conditions.");
		document.frmMeeUserICPAssessInsure.chkAccept.focus();
		return false;
	}

chosen3="";
	len3 = document.frmMeeUserICPAssessInsure.hlcmemb.length ;
	
	for(i3=0;i3<len3;i3++){
		if(document.frmMeeUserICPAssessInsure.hlcmemb[i3].checked){
			chosen3= document.frmMeeUserICPAssessInsure.hlcmemb[i3].value;
		}
	}
	if((document.frmMeeUserICPAssessInsure.membno.value=="")&&(chosen3=="yes")){
		alert("Membership Number is Empty");
		document.frmMeeUserICPAssessInsure.membno.focus();
		return false;
	}	
	if(isAlpha(document.frmMeeUserICPAssessInsure.membno.value)){
		alert("Membership Number is not valid");
		document.frmMeeUserICPAssessInsure.membno.focus();
		return false;
	}
	if((document.frmMeeUserICPAssessInsure.membno.value.indexOf('  ')!=-1)||(document.frmMeeUserICPAssessInsure.membno.value.indexOf(' ')==0)){
		alert("Membership Number is not valid");
		document.frmMeeUserICPAssessInsure.membno.focus();
		return false;
	}
	if(!(document.frmMeeUserICPAssessInsure.membno.value=="")&&(chosen3=="no")){
		document.frmMeeUserICPAssessInsure.membno.value="";
		return false;
	}	

return true;
}

function isRadio(){
	
//==================================== For Radio Button ==============================	
	chosen4="";
	len4 = document.frmMeeUserICPAssessInsure.hlcmemb.length ;
	
	for(i4=0;i4<len4;i4++){
		if(document.frmMeeUserICPAssessInsure.hlcmemb[i4].checked){
			chosen4= document.frmMeeUserICPAssessInsure.hlcmemb[i4].value;
			
		}
	}

	if(chosen4=="yes"){
		document.frmMeeUserICPAssessInsure.membno.disabled=false;
		
	}
	if(chosen4=="no"){
	document.frmMeeUserICPAssessInsure.membno.disabled="disabled";	
	document.frmMeeUserICPAssessInsure.membno.value="";	
	}	
	
		 
}

var arHttpRequest;

function HLCMemberDetails(param){
    var memberid=param.value;
	var chkStat = document.frmMeeUserICPAssessInsure.hlcmemb[0].value;
	 
		 
        var url = "./RiderInfoAjaxAction.do?memberid="+ memberid; 
        if (window.ActiveXObject){ 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest){ 
            arHttpRequest = new XMLHttpRequest(); 
        } 
        arHttpRequest.open("POST", url, true); 
        arHttpRequest.onreadystatechange = membStatus; 
        arHttpRequest.send(null); 
  
    
}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function membStatus(){ 
   
        if (arHttpRequest.readyState == 4){ 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
                if(arnameText=="false"){    
                alert("Member Id Doesn't Exists!"); 
				document.frmMeeUserICPAssessInsure.membno.value="";
            	document.frmMeeUserICPAssessInsure.membno.focus();
                return;
                }    
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 

