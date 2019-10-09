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
function rCheck(){
	 
	var a = document.myform.useaNo.value;
	 
     if(a!="" && a.indexOf(' ')!=0){
		 
		  riderDetails();
	    
   }
     else{
	   	  clearRider();
     }
}
var arHttpRequest;
var req;
     function riderDetails(){
		 param = document.myform.useaNo.value;
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
 
  
 
         
