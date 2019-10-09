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
function ValidateForm(){
	var emailID=document.contactForm.mailID;
	
	if ((emailID.value==null)||(emailID.value=="")){
		alert("Please Enter your Email ID")
		emailID.focus();
		return false;
	}
	if (echeck(emailID.value)==false){
		emailID.value=""
		emailID.focus();
		return false;
	}
	return true;
}

//------------------------DOSPACE--------------------------------------------------------------------------
function Dospace(str){
	if(str.indexOf("  ")!=-1){
		return true;
	}
	else {
		return false;
	}
}

//-------------------------function for Special Character
function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
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

//-----------------------------------is not alpha1----------------------------------------------------------------

function isnotAlpha1(str){
stringCheck="!@#$%^&*|<>?/=~.,`;:][{}"+"\\"+"\'"+"\"";
f1=1;
	for(j=0;j<str.length;j++)
	{ 
		if(stringCheck.indexOf(str.charAt(j))!=-1){ 
			f1=0;
			}
	}
	if(f1==0){ 
		return true; 
	}
	else {
		return false;
	}
}


function myvalidate(){
	if((document.contactForm.firstname.value=="")||(document.contactForm.firstname.value.indexOf(' ')==0)){
			alert('First Name cannot be empty');
			document.contactForm.firstname.focus();
			return false;
		}
	if(document.contactForm.firstname.value.length > 80){
		alert("First Name should be less than 80 characters");
		document.contactForm.firstname.focus();
		return false;
	}
	if((document.contactForm.lastname.value=="")||(document.contactForm.lastname.value.indexOf(' ')==0)){
			alert('Last Name cannot be empty');
			document.contactForm.lastname.focus();
			return false;
		}
	if(document.contactForm.lastname.value.length > 80){
		alert("Last Name should be less than 80 characters");
		document.contactForm.lastname.focus();
		return false;
	}		
	if((document.contactForm.nickname.value=="")||(document.contactForm.nickname.value.indexOf(' ')==0)){
			alert('Nick Name cannot be empty');
			document.contactForm.nickname.focus();
			return false;
	}
	if(document.contactForm.nickname.value.length > 80){
		alert("Nick Name should be less than 80 characters");
		document.contactForm.nickname.focus();
		return false;
	}		
//Email Validation
	if((document.contactForm.emailid.value=="")||(document.contactForm.emailid.value.indexOf(' ')==0)) {
		alert("Email ID cannot be empty");
		document.contactForm.emailid.focus();
		return false;	
	}
   if(!(document.contactForm.emailid.value== "")){ 
	strmail=document.contactForm.emailid.value;
	firstat = strmail.indexOf("@");
	lastat = strmail.lastIndexOf("@");
	strmain=strmail.substring(0,firstat);
	strsub=strmail.substring(firstat,document.contactForm.emailid.value.length);
	if(strmail.length>120){
		alert("Email is out of range");
		document.contactForm.emailid.focus();
		return false;
	}
	if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
		alert("Enter valid Email ");
		document.contactForm.emailid.focus();
		return false;
	}
	if(isnotSpecial(strmain) || isnotSpecial(strsub)){
		alert("Enter valid Email ");
		document.contactForm.emailid.focus();
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
		document.contactForm.emailid.focus();
		return false;
	}
	if(firstat==-1 || lastat==-1){
		alert("Enter valid Email" );
		document.contactForm.emailid.focus();
		return false;
	}
	if(Number(strmain)){
		alert("Enter valid Email ");
		document.contactForm.emailid.focus();
		return false;
	}
	if(firstat != lastat ){
		alert("Enter valid Email ");
		document.contactForm.emailid.focus();
		return false;
	}
	firstdot=strmail.indexOf(".",firstat);
	lastdot=strmail.lastIndexOf(".");
	
	if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
		alert("Enter valid Email ");
		document.contactForm.emailid.focus();
		return false;
	}
}
//Alternate Email Validation
 if(!(document.contactForm.alteremailid.value== "")){ 
		strmail=document.contactForm.alteremailid.value;
		firstat = strmail.indexOf("@");
		lastat = strmail.lastIndexOf("@");
		strmain=strmail.substring(0,firstat);
		strsub=strmail.substring(firstat,document.contactForm.alteremailid.value.length);
		if(strmail.length>120){
			alert("Email is out of range");
			document.contactForm.alteremailid.focus();
			return false;
		}
		if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 ){
			alert("Enter valid Email ");
			document.contactForm.alteremailid.focus();
			return false;
		}
		if(isnotSpecial(strmain) || isnotSpecial(strsub)){
			alert("Enter valid Email ");
			document.contactForm.alteremailid.focus();
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
			document.contactForm.alteremailid.focus();
			return false;
		}
		if(firstat==-1 || lastat==-1){
			alert("Enter valid Email" );
			document.contactForm.alteremailid.focus();
			return false;
		}
		if(Number(strmain)){
			alert("Enter valid Email ");
			document.contactForm.alteremailid.focus();
			return false;
		}
		if(firstat != lastat ){
			alert("Enter valid Email ");
			document.contactForm.alteremailid.focus();
			return false;
		}
		firstdot=strmail.indexOf(".",firstat);
		lastdot=strmail.lastIndexOf(".");
		
		if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1){
			alert("Enter valid Email ");
			document.contactForm.alteremailid.focus();
			return false;
		}
	}
//Phone Number Validation	
	if(document.contactForm.phone.value=="" || document.contactForm.phone.value.indexOf(' ')==0){
		alert('Phone Number cannot be empty');
		document.contactForm.phone.focus();
		return false;
	}
	
	if(document.contactForm.phone.value!=""){	
		len7=document.contactForm.phone.value.length;
		strnum = document.contactForm.phone.value;
		var GoodChars = "0123456789()-+ ";
		valid = 1;
		for(j=0;j<len7;j++){ 
			if(GoodChars.indexOf(strnum.charAt(j))==-1){ 
				valid=0;
			} 
		}
		if(valid!=1){
			alert("Enter valid Phone Number");
			document.contactForm.phone.focus();
			return false;
		}
		if(document.contactForm.phone.value.length>40){
			alert("Enter valid Phone Number");
			document.contactForm.phone.focus();
			return false;
		}
		if(isnotAlpha1(document.contactForm.phone.value)){
			alert("Enter Valid Phone Number ");
			document.contactForm.phone.focus();
			return false; 
		}
	}
//Mobile Number Validation	
	if(document.contactForm.mobile.value!=""){	
		len7=document.contactForm.mobile.value.length;
		strnum = document.contactForm.mobile.value;
		var GoodChars = "0123456789()-+ ";
		valid = 1;
		for(j=0;j<len7;j++){ 
			if(GoodChars.indexOf(strnum.charAt(j))==-1){ 
				valid=0;
			} 
		}
		if(valid!=1){
			alert("Enter valid Mobile Number");
			document.contactForm.mobile.focus();
			return false;
		}
		if(document.contactForm.mobile.value.length>40){
			alert("Enter valid Mobile Number");
			document.contactForm.mobile.focus();
			return false;
		}
		if(isnotAlpha1(document.contactForm.mobile.value)){
			alert("Enter Valid Mobile Number ");
			document.contactForm.mobile.focus();
			return false; 
		}
	}	
//FAX Number Validation	
	if(document.contactForm.fax.value!=""){	
		len7=document.contactForm.fax.value.length;
		strnum = document.contactForm.fax.value;
		var GoodChars = "0123456789()-+ ";
		valid = 1;
		for(j=0;j<len7;j++){ 
			if(GoodChars.indexOf(strnum.charAt(j))==-1){ 
				valid=0;
			} 
		}
		if(valid!=1){
			alert("Enter valid FAX Number");
			document.contactForm.fax.focus();
			return false;
		}
		if(document.contactForm.fax.value.length>40){
			alert("Enter valid FAX Number");
			document.contactForm.fax.focus();
			return false;
		}
		if(isnotAlpha1(document.contactForm.fax.value)){
			alert("Enter Valid FAX Number ");
			document.contactForm.fax.focus();
			return false; 
		}
	}	
//Street 	
	if(document.contactForm.street.value=="" || document.contactForm.street.value.indexOf(' ')==0){
		alert("Street value cannot be empty");
		document.contactForm.street.focus();
		return false;
	}		
	if(document.contactForm.street.value.length > 120){
		alert("Street Address Exceeds Its Limit");
		document.contactForm.street.focus();
		return false;
	}		

		var cdln = "";
		if(typeof(window.document.getElementById('country_sel')) == 'object'){
			if (window.document.getElementById('country_sel').value != ""){
				var cemail;
				cemail = window.document.getElementById('country_sel')[window.document.getElementById('country_sel').selectedIndex].value;
				cdln = cemail;
			}
			if(cdln == "---Select---"){
				alert("Please select Country Name in Address");
				window.document.getElementById('country_sel').focus();
				return false;
			}
		}
	
		if ( document.getElementById('country_sel').selectedIndex == 0 ){
			 alert ( "Please select Country Name in Address" );
			document.getElementById('country_sel').focus();
			 return false;
		 }
	
	
		if ( document.getElementById('state_sel').selectedIndex == 0 ){
			 alert ( "Please select State in Address" );
			document.getElementById('state_sel').focus();
			 return false;
		 }
		var cdln1 = "";
		if(typeof(window.document.getElementById('country_sel')) == 'object'){
			if (window.document.getElementById('country_sel').value != ""){
				var cemail;
				cemail = window.document.getElementById('country_sel')[window.document.getElementById('country_sel').selectedIndex].value;
				cdln1 = cemail;
			}
			if(cdln1 == "---Select---"){
				alert("Please select Country in Address");
				window.document.getElementById('country_sel').focus();
				return false;
			}
		}

		if((document.contactForm.city.value=="")||(document.contactForm.city.value.indexOf(' ')==0)){
			alert('City cannot be empty');
			document.contactForm.city.focus();
			return false;
		}
		if((document.contactForm.zipcode.value=="")||(document.contactForm.zipcode.value.indexOf(' ')==0)){
			alert("Zipcode cannot be empty");
			document.contactForm.zipcode.focus();
			return false;
	    }
 		if(document.contactForm.zipcode.value!=""){
			len7=document.contactForm.zipcode.value.length;
			strnum = document.contactForm.zipcode.value;
			var GoodChars = "0123456789()-+ ";
			valid = 1;
			for(j=0;j<len7;j++){ 
				if(GoodChars.indexOf(strnum.charAt(j))==-1){ 
					valid=0;
				} 
			}
			if(valid!=1){
				alert("Enter valid Zip Code");
				document.contactForm.zipcode.focus();
				return false;
			}
    	if((document.contactForm.zipcode.value.length <3&& document.contactForm.country_sel.value!="USA")){
			alert("Enter valid Zipcode");
			document.contactForm.zipcode.focus();
			return false;
		}
 
    	if((document.contactForm.zipcode.value.length >20 && document.contactForm.country_sel.value!="USA")){
			alert("Enter Valid Zipcode");
			document.contactForm.zipcode.focus();
			return false;
		}
		 if((document.contactForm.zipcode.value.length !=5&& document.contactForm.country_sel.value=="USA")){
			alert("Enter Valid Zipcode");
			document.contactForm.zipcode.focus();
			return false;
		}
		if(Dospace(document.contactForm.zipcode.value)){
			alert("Enter Valid Zipcode value");
	     	document.contactForm.zipcode.focus();
	  		return false;
			}
		}		
		if(document.contactForm.zipcode.value.indexOf(" ")==0){
			alert("Enter Valid Zipcode");
			document.contactForm.zipcode.focus();
			return false; 
		}
}
