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
function resetRadioOne(){
	var chosen2="";
	len = document.myform.regAddFor.length ;
	for(i=0;i<len;i++){
		if(document.myform.regAddFor[i].checked){
			chosen2= document.myform.regAddFor[i].checked=false;
			
			document.getElementById('ridAddMemb').style.display = "none";
			document.getElementById('ridAddUser').style.display = "none";
			document.getElementById('accAddUser').style.display = "none";
			document.getElementById('newwAddUser').style.display = "none";
			document.getElementById('addOwn').style.display = "none";
		}
	}
}

function resetRadioTwo(){
var chosen2="";
len = document.myform.regFor.length ;
	for(i=0;i<len;i++){
		if(document.myform.regFor[i].checked){
			chosen2= document.myform.regFor[i].checked=false;
		}
	}
}

function userDetailsT(param){
	var  url = null;
	if(param.value.length==0)
		return;
	url = "annualAjax.do?method=memberDetail&memberId="+escape(param.value);
	if (window.ActiveXObject){ 
		req = new ActiveXObject("Microsoft.XMLHTTP"); 
	} 
	else if (window.XMLHttpRequest){ 
		req = new XMLHttpRequest(); 
	} 
	req.onreadystatechange = processRequestT;         
	req.open("GET", url, true);
	req.send(null);  
} 
/** 
* This is the call back method 
* If the call is completed when the readyState is 4 
* and if the HTTP is successfull when the status is 200 
* update the profileSection DIV 
*/ 
function processRequestT(){ 
	if (req.readyState == 4){ 
	
		if(req.status == 200){ 
	
				var xmlDoc = req.responseXML.documentElement;
				var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
				var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
				var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
				var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
				
				
				var firstNameObj = document.getElementById("firstNameId2");
				var lastNameObj = document.getElementById("lastNameId2");
				var phoneObj = document.getElementById("phoneId2"); 
				var userObj = document.getElementById("addUserDetId"); 
	
	
				firstNameObj.value=firstName;
				lastNameObj.value=lastName;
				 if(phone=="null"){
					 phone = "xxxx";
				 }
	
				var phLen = phone.length;
				var minPhLen = phLen - 4;
				phoneObj.value=phone.substring(minPhLen,phLen);
				userObj.value=userId;
		} 
			if(req.status==500) {
			  alert("MembershipId does'nt exists");
			  clearFieldsT();
			  document.myform.userNameId2.focus();
			  return;
			}
			else{ 
	
			} 
		}	
} 

function clearFieldsT() {
	document.getElementById("userNameId2").value = "";
	document.getElementById("firstNameId2").value = "";
	document.getElementById("lastNameId2").value = "";
	document.getElementById("phoneId2").value = "";
	document.getElementById("addUserDetId").value = "";
}

function nonUserDetailsT(param){

		if(param.value.length==0)
			return;
		var  url = null;
		url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);

		if (window.ActiveXObject){ 
				req = new ActiveXObject("Microsoft.XMLHTTP"); 
		} 
		else if (window.XMLHttpRequest){ 
				req = new XMLHttpRequest(); 
		} 
		
		req.onreadystatechange = processRequestTT;         
		req.open("GET", url, true);
		req.send(null);  
} 

function processRequestTT(){ 
            if (req.readyState == 4){ 
                    if(req.status == 200){ 
						//clearDetails();
						var xmlDoc = req.responseXML.documentElement;
						var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
						var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
						var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
						var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
						
						var fNameObj = document.getElementById("firstNameId3");
						var lNameObj = document.getElementById("lastNameId3");
						var phObj = document.getElementById("phoneId3");  
						var usObj = document.getElementById("addUserDetId");  
						
						fNameObj.value= fName;
						lNameObj.value=lName;
						usObj.value=usId;
						if(ph=="null"){
							ph = "xxxx";
						}
						
						var phLen = ph.length;
						var minPhLen = phLen - 4;
						phObj.value=ph.substring(minPhLen,phLen);
                    } 
                    if(req.status==500) {
                      alert("userName does'nt exists");
                      clearTT();
                      document.myform.userNameId3.focus();
                      return;
                    }
                    else{ 
                            //alert("Error loading page\n"+ req.status +":"+ req.statusText); 
                    } 
            }	
    } 


function clearTT() {
            document.getElementById("userNameId3").value = "";
            document.getElementById("firstNameId3").value = "";
            document.getElementById("lastNameId3").value = "";
            document.getElementById("phoneId3").value = "";
            document.getElementById("addUserDetId").value = "";
    }					

function nonUserDetailsTT(param){
//alert('hi');
    //alert(param.value);
            if(param.value.length==0)
            return;
                    var  url = null;
                    url = "annualAjax.do?method=memDetails&nonmemberId="+escape(param.value);


                    if (window.ActiveXObject){ 
                            req = new ActiveXObject("Microsoft.XMLHTTP"); 
                    } 
                    else if (window.XMLHttpRequest){ 
                            req = new XMLHttpRequest(); 
                    } 

                    req.onreadystatechange = processRequestTTT;         
                    req.open("GET", url, true);
                    req.send(null);  
} 

function processRequestTTT(){ 
            if (req.readyState == 4){ 
               //alert(req.readyState);
                    if(req.status == 200){ 
						//clearDetails();
						var xmlDoc = req.responseXML.documentElement;
						var fName =xmlDoc.getElementsByTagName('userfirstName')[0].childNodes[0].nodeValue;
						var lName =xmlDoc.getElementsByTagName('userlastName')[0].childNodes[0].nodeValue; 
						var ph =xmlDoc.getElementsByTagName('userphone')[0].childNodes[0].nodeValue; 
						var usId =xmlDoc.getElementsByTagName('useruserId')[0].childNodes[0].nodeValue; 
						//var usrdob =xmlDoc.getElementsByTagName('userdob')[0].childNodes[0].nodeValue;
						//var usrage =xmlDoc.getElementsByTagName('userage')[0].childNodes[0].nodeValue; 
						
						var fNameObj = document.getElementById("cpAddfirstNameId1");
						var lNameObj = document.getElementById("cpAddlastNameId1");
						var phObj = document.getElementById("ecmpAddPhoneId1");  
						var usObj = document.getElementById("addUserDetId");  
						
						fNameObj.value= fName;
						lNameObj.value=lName;
						usObj.value=usId;

						if(ph=="null"){
								ph = "xxxx";
						 }

								var phLen = ph.length;
								var minPhLen = phLen - 4;
								phObj.value=ph.substring(minPhLen,phLen);
						//showAge();
                    } 
                    if(req.status==500) {
                      alert("companyName does'nt exists");
                      clearTTT();
                      document.myform.existAddCompNameId1.focus();
                      return;
                    }
                    else{ 

                    } 
            }	
} 
function clearTTT() {
		document.getElementById("existAddCompNameId1").value = "";
		document.getElementById("cpAddfirstNameId1").value = "";
		document.getElementById("cpAddlastNameId1").value = "";
		document.getElementById("ecmpAddPhoneId1").value = "";
		document.getElementById("addUserDetId").value = "";
}

 //-------------------------------- User status validation Ajax Script ------------------------------------------------
var httpRequest;
function usrStatT(){
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
        httpRequest.onreadystatechange =processUserT; 
        httpRequest.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processUserT(){ 
   
        if (httpRequest.readyState == 4){ 
            if(httpRequest.status == 200){ 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("userstatus")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
                 updateHTMLT(salutationXML); 
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
    function updateHTMLT(salutationXML){ 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
		if(salutationText != "false"){
			alert("Company Name already Exists Chose Another !");
			document.myform.newAddFirstNameIdComp.value="";
			document.myform.newAddFirstNameIdComp.focus();
		}
    }
function arCheck(){
    param  =document.getElementById('riderUseaNo_id').value
            
            var b = param;
    
    if(b=="" ){
        document.getElementById("ownerUseaNo2_id").value = "";
        document.getElementById("ownerFname2_id").value = "";
        document.getElementById("ownerLname2_id").value = "";
        document.getElementById("ownerPhone2_id").value = "";
        
    } else{
// addRiderClear();
        additionalRiderDetails(b);
        
        
    }
}
var req;
function additionalRiderDetails(riderId){
    param = riderId;
    
    var  url = null;
    if(param.length==0)
        return;
    url = "annualAjax.do?method=memberDetail&memberId="+escape(param);
    if (window.ActiveXObject){
        req = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest){
        req = new XMLHttpRequest();
    }
    req.onreadystatechange = processAddRider;
    req.open("GET", url, true);
    req.send(null);
}
/**
 * This is the call back method
 * If the call is completed when the readyState is 4
 * and if the HTTP is successfull when the status is 200
 * update the profileSection DIV
 */
function processAddRider(){
    if (req.readyState == 4){
// alert(req.status);
        if(req.status == 200){
            
            var xmlDoc = req.responseXML.documentElement;
            var userNoId =param;
            var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
            var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue;
            var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue;
            
            var addfirstNameObj = document.getElementById("ownerFname2_id");
            var addlastNameObj = document.getElementById("ownerLname2_id");
            var addphoneObj = document.getElementById("ownerPhone2_id");
            var userObj = document.getElementById("ownerUseaNo2_id");
            
            addfirstNameObj.value =  firstName;
            addlastNameObj.value = lastName ;
            addphoneObj.value = phone ;
            userObj.value =  userNoId;
            
            
        }
        if(req.status==500) {
            //addRiderClear();
            document.getElementById("ownerUseaNo2_id").value = "";
            document.getElementById("ownerFname2_id").value = "";
            document.getElementById("ownerLname2_id").value = "";
            document.getElementById("ownerUseaNo2_id").value = "";
            
            alert("Membership Id is not valid");
            document.myform.ownerUseaNo2_id.focus();
        } else{
            
        }
    }
    
}

function copyRiderTrainer(){
	if(document.getElementById('riderUseaNo_id').value == "" && document.myform.regAddFor[2].checked){
		document.getElementById('accAddUser').style.display = "none";
		alert("Please enter relevant Rider Information!");
		chosen="";
		len=document.myform.regFor.length;
		for(i=0;i<len;i++){
			if(document.myform.regFor[i].checked){
				document.myform.regFor[i].checked=false;
			}
		}
	}else{
		switchDiv('accAddUser');
		if(document.getElementById('regAddFor').value != 'mem1'){
			document.myform.userNameId3.value = "";
			document.myform.firstNameId3.value = "";
			document.myform.lastNameId3.value = "";
			document.myform.phoneId3.value = "";
		}
		else{	
			document.myform.userNameId3.value = document.myform.riderUseaNo_id.value;
			document.myform.firstNameId3.value = document.myform.riderFname_id.value;
			document.myform.lastNameId3.value = document.myform.riderLname_id.value;
			document.myform.phoneId3.value = document.myform.riderPhone_id.value;	
		}
	}
	return true;
}						