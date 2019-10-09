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
function arCheck(){
	
	var a = document.myform.hlcNo.value;
	var b = document.myform.arhlcNo.value;
	var c = document.myform.prhlcNo.value;
    if(b!="" && b.indexOf(' ')!=0){
		if(b==a || b==c){
			alert("Rider, Additional Rider & Previous Rider Information cannot be the same.");
			document.myform.arhlcNo.value="";
			document.myform.arhlcNo.focus();
			addiRider();
		}
	 
	   else{
		   addiRider();
		   additionalRiderDetails();
	   }
	 }
	 else{
			// addRiderClear();
											document.getElementById("addR_num").value = "";
											document.getElementById("additionalRider").value = "";
											document.getElementById("lastRider").value = "";
											document.getElementById("addRPhone").value = "";
											document.getElementById("addRprefix").value = "";
											document.getElementById("addREmail").value = ""; 
											document.getElementById("addRStreet").value = "";
											document.getElementById("addRCity").value = "";
											document.getElementById("addRState").value = ""; 
											document.getElementById("addRZipcode").value = "";  
											document.getElementById("addRCell").value = ""; 

			addiRider();
	 }
}
function rCheck(){
	 
	var a = document.myform.hlcNo.value;
	var b = document.myform.arhlcNo.value;
	var c = document.myform.prhlcNo.value;
	//alert(a.indexOf(' '));
     if(a!="" && a.indexOf(' ')!=0){
		if(a==b || a==c){
			alert("Rider, Additional Rider & Previous Rider Information cannot be the same.");
				document.getElementById("riderUseaNo_id").value = "";
				 clearRider();
				document.myform.hlcNo.focus();
				 
			}
  //}
	   else{
		  riderDetails();
	   }
   }
     else{
	   	  clearRider();
     }
}
function prCheck(){
    var c = document.myform.prhlcNo.value;
	var d = document.myform.hlcNo.value;
	var e = document.myform.arhlcNo.value;
  if(c!="" && c.indexOf(' ')!=0){

	if(c==d || c==e){
	alert("Rider, Additional Rider & Previous Rider Information cannot be the same.");
	document.myform.prhlcNo.value="";
	document.myform.previousRider.value="";
	// addiRider();
   	}
   else{
	   
	 PrevRiderDetails();
   }
 }
}


var arHttpRequest;
var req;
     function riderDetails(){
		 param = document.myform.hlcNo.value;
		// alert(param);
		 
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
									
								 
									var firstNameObj = document.getElementById("riderFname_id");
								    var lastNameObj = document.getElementById("riderLname_id");
									var phoneObj = document.getElementById("riderPhone_id"); 
							      //  var userObj = document.getElementById("userDetId"); 
									 var prefixObj = document.getElementById("rprefix");  
									var emailIdObj = document.getElementById("rEmail");  
									var address1Obj = document.getElementById("rStreet");  
								   // var address2Obj = document.getElementById("rStreet");  
									var cityObj = document.getElementById("rCity");  
									var stateObj = document.getElementById("rState");  
									/*var countryObj = document.getElementById("userDetId");  */
									var zipObj = document.getElementById("rZipcode");  
									var mobileObj = document.getElementById("rCell");  
								 
								 
									
								     firstNameObj.value =  firstName;
								     lastNameObj.value = lastName ;
									 phoneObj.value = phone ; 
							         //userObj.value =  ; 
									prefixObj.value =  prefix;  
									 emailIdObj.value = emailId ;  
									 address1Obj.value =  address1;  
									// address2Obj.value =  address2;  
									 cityObj.value = city ;  
									 stateObj.value = state ;  
									/* countryObj.value = document.getElementById("userDetId");  */
									 zipObj.value = zip ;  
									 mobileObj.value = mobile ;  
									
									
							} 
											if(req.status==500) {
												clearRider();
												
												alert("Membership Id is not valid");
												addiRider();
												document.myform.riderUseaNo_id.focus();
												
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
}
//================================================================================================================================
//=============================================Additinal Rider details======================================================

//================================================================================================================================
  
var req;
     function additionalRiderDetails(){
		 param = document.myform.arhlcNo.value;
		// alert(param);
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
								  //  var userNoId =xmlDoc.getElementsByTagName('userNo')[0].childNodes[0].nodeValue;
									var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
									var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
									var dob =xmlDoc.getElementsByTagName('dob')[0].childNodes[0].nodeValue; 
									var prefix =xmlDoc.getElementsByTagName('prefix')[0].childNodes[0].nodeValue; 
									var emailId =xmlDoc.getElementsByTagName('emailId')[0].childNodes[0].nodeValue; 
									var address1 =xmlDoc.getElementsByTagName('address1')[0].childNodes[0].nodeValue; 
									//var address2 =xmlDoc.getElementsByTagName('address2')[0].childNodes[0].nodeValue; 
									var city =xmlDoc.getElementsByTagName('city')[0].childNodes[0].nodeValue; 
									var state =xmlDoc.getElementsByTagName('state')[0].childNodes[0].nodeValue; 
									 
									var zip =xmlDoc.getElementsByTagName('zip')[0].childNodes[0].nodeValue; 
									var mobile =xmlDoc.getElementsByTagName('mobile')[0].childNodes[0].nodeValue; 
									//var faxNo =xmlDoc.getElementsByTagName('faxNo')[0].childNodes[0].nodeValue; 
									
								 
									var addfirstNameObj = document.getElementById("additionalRider");
								    var addlastNameObj = document.getElementById("lastRider");
									var addphoneObj = document.getElementById("addRPhone"); 
							      //  var userObj = document.getElementById("userDetId"); 
									var addprefixObj = document.getElementById("addRprefix");  
									var addemailIdObj = document.getElementById("addREmail");  
									var addaddress1Obj = document.getElementById("addRStreet");  
								 
									var addcityObj = document.getElementById("addRCity");  
									var addstateObj = document.getElementById("addRState");  
									 
									var addzipObj = document.getElementById("addRZipcode");  
									var addmobileObj = document.getElementById("addRCell");  
								 
								 
									
								     addfirstNameObj.value =  firstName;
								     addlastNameObj.value = lastName ;
									 addphoneObj.value = phone ; 
							      
									 addprefixObj.value =  prefix;  
									 addemailIdObj.value = emailId ;  
									 addaddress1Obj.value =  address1;  
									// address2Obj.value =  address2;  
									 addcityObj.value = city ;  
									 addstateObj.value = state ;  
									 
									 addzipObj.value = zip ;  
									 addmobileObj.value = mobile ;  
									
									
							} 
											if(req.status==500) {
											//addRiderClear();
											document.getElementById("addR_num").value = "";
											document.getElementById("additionalRider").value = "";
											document.getElementById("lastRider").value = "";
											document.getElementById("addRPhone").value = "";
											document.getElementById("addRprefix").value = "";
											document.getElementById("addREmail").value = ""; 
											document.getElementById("addRStreet").value = "";
											document.getElementById("addRCity").value = "";
											document.getElementById("addRState").value = ""; 
											document.getElementById("addRZipcode").value = "";  
											document.getElementById("addRCell").value = ""; 
											alert("Membership Id is not valid");
											document.myform.arhlcNo.focus();
											}
											else{ 
												
											} 
						}	
						
					} 
		 
/*  fuction addRiderClear(){
		document.getElementById("addR_num").value = "";
		document.getElementById("additionalRider").value = "";
		document.getElementById("lastRider").value = "";
		document.getElementById("addRPhone").value = "";
		document.getElementById("addRprefix").value = "";
		document.getElementById("addREmail").value = ""; 
		document.getElementById("addRStreet").value = "";
		document.getElementById("addRCity").value = "";
		document.getElementById("addRState").value = ""; 
		document.getElementById("addRZipcode").value = "";  
		document.getElementById("addRCell").value = ""; 
  }*/
//================================================================================================================================
//=============================================Previous Rider details======================================================

//================================================================================================================================
  
var req;
     function PrevRiderDetails(){
		 param = document.myform.prhlcNo.value;
		// alert(param);
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
			req.onreadystatechange = processPrevRider;         
			req.open("GET", url, true);
			req.send(null);  
} 
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
         function processPrevRider(){ 
    			if (req.readyState == 4){ 
						  // alert(req.status);
							if(req.status == 200){ 
							 
								 var xmlDoc = req.responseXML.documentElement;
									var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue; 
															 
									var addfirstNameObj = document.getElementById("previousRider");
								    addfirstNameObj.value =  firstName +" "+lastName;
							} 
											if(req.status==500){
											//addRiderClear();
											document.myform.prhlcNo.value = "";
											document.myform.previousRider.value = "";
											alert("Membership Id is not valid");
											document.myform.prhlcNo.focus();
											}
											else{ 
												
											} 
						}	
						
					} 
		 
 
