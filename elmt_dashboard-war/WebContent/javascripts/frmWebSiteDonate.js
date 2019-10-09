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
// JavaScript Document

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
		alert("Enter a valid day in Check Date")
		return false
	}
   if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Enter a valid Check Date ")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear ){
		alert("Enter a valid Year in Check Date ")
		return false
	}
	
return true
}
//---------------
//------------------for chk date------------------------------------------------------------------------

function isnotAlphaNumeric1(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?=~-`.,;:][{}"+"\\";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}

//-----------------------
function isnotIntegerDecimal(str){
		stringIntCheck="0123456789.";
		f2=1;
		for(j=0;j<str.length;j++)
		{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
		 {f2=0;}}
		if(f2==0)
		{return true;} else { return false;}
		}

//-------------------------for Total Amount field-----------------------------
function donaName(){
		var chosen="";
		len1 = document.frmDonateProg.addr.length ;
		for(i=0;i<len1;i++){
		if(document.frmDonateProg.addr[i].checked){
		chosen= document.frmDonateProg.addr[i].value;	
		}
		}
		//alert(" chosen:"+chosen);	
		if(chosen == "No"){
		var fName=document.frmDonateProg.txtFirstName.value;
		var lName=document.frmDonateProg.txtLastName.value;
		var fullName=fName+" " +lName;
		//alert("fullName 1:"+fullName);
		//document.frmDonateProg.donNametbname.value=fullName;
		}else{
		var fName=document.frmDonateProg.txtFName.value;
		var lName=document.frmDonateProg.txtLName.value;
		var fullName=fName+" " +lName;
		//alert("fullName 2:"+fullName);
		//document.frmDonateProg.donNametbname.value=fullName;
		} 
		
	  chbxnum2=document.frmDonateProg.donCbxCt.value;
	  var userNam=fullName;
	  
	  for(i=0;i<chbxnum2;i++)	{ 
		chbxname = "donCb"+i;
		txtname="donTb"+i;
		dontxtname="donNametb"+i;
//		alert('usrnme'+usrnme);	  
		//alert(chbxname); 
		  if(document.getElementById(''+chbxname).checked){ 
			document.getElementById(''+dontxtname).value=userNam;
		  }
		  else{
			document.getElementById(''+dontxtname).value="";
		  }
	  }
}

var sum;
function Sumup(){
	sum=0;
	document.getElementById('totalAmount').value=sum;
	
	chbxnum2=document.frmDonateProg.donCbxCt.value;
	
	for(i=0;i<chbxnum2;i++)
	{ chbxname = "donCb"+i;
	  txtname="donTb"+i;
	  //alert(chbxname); 
	  if(document.getElementById(''+chbxname).checked)

	  {  	 
	     var txtval= document.getElementById(''+txtname).value;
		 if(isnotIntegerDecimal(txtval) == false)
		 {
	     	sum= Number(txtval) +sum;
			
			 document.getElementById('totalAmount1').value = sum;
			 document.getElementById('totalAmount').value=format(sum,2);
		 }
		 else
		 {
			alert("Enter a valid Donation Amount");
			document.getElementById(''+txtname).focus();
			return false;
		 }
	  }
	}	
	
}

 
//-------------------function for character validation--------------------
function isnotAlpha(str){
stringCheck="!@#$%^&*()_+|<>?/=-~.,`0123456789;:][{}"+"\\"+"\'";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//---------------------------------------------------------------------------------------	
//------------------------------------------------------------------------------------------
//------------------------function for Alpha Numeric
function isnotAlphaNumeric(str){
stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//-----------------------function for pure Integer Numbers
function isnotInteger(str){
stringIntCheck="0123456789";
f2=1;
for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{return true;} else { return false;}
}
//------------------------DOSPACE--------------------------------------------------------------------------
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}
//--------------------------------------isnotAlpha1---------------------------------------------------------------
function isnotAlpha1(str){
stringCheck="!@#$%^&*()+|<>?/=-~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//--------------------------------------isnotAlpha2--------------------------------------------------------
function isnotAlpha2(str){
stringCheck="!@#$%^&*()+|<>?/=_~,`0123456789;:][{}"+"\\"+"\"";
f1=1;
for(j=0;j<str.length;j++)
{ if(stringCheck.indexOf(str.charAt(j))!=-1)
   { f1=0;}}
if(f1==0)
{ return true; }else {return false;}
}
//-------------------------function for Special Character
function isnotSpecial(str){
stringSpecialCheck="!#$%^&*()+|<>?/=~,;:][{}"+"\\"+"\'";
f4=1;
for(j=0;j<str.length;j++)
{if(stringSpecialCheck.indexOf(str.charAt(j))!=-1)
{f4=0;}}
if(f4==0)
{return true;}else{return false;}
}
//----------------------------for Payment Option----------------------------
function myValidate(){
//------------------------------for Donation Amount
	
	 var chosen="";
	len1 = document.frmDonateProg.addr.length ;
	for(i=0;i<len1;i++){
		if(document.frmDonateProg.addr[i].checked){
			chosen= document.frmDonateProg.addr[i].value;	
			}
	}
	if(chosen==""){
		alert("Check any of HLC Member Option.");
		return false; 
	}
	
	if(document.frmDonateProg.addr[0].checked==true){
		
			if(document.frmDonateProg.hlcNo.value=="" ){
			alert("Enter HLC MemberID" );
			document.frmDonateProg.hlcNo.focus();
			return false;
			}
			//for first name
			/*if(document.frmDonateProg.txtFName.value=="")
			{alert(" First Name cannot be empty ");
			document.frmDonateProg.txtFName.focus();
			return false; }
			if(document.frmDonateProg.txtFName.value.indexOf(' ')==0)
			{alert("Enter First Name ");
			document.frmDonateProg.txtFName.focus();
			return false; }
			
			if(isnotAlpha1(document.frmDonateProg.txtFName.value))
			{ alert("Enter Valid First Name ");
			document.frmDonateProg.txtFName.focus();
			return false; }
			if(Dospace(document.frmDonateProg.txtFName.value))
			{ alert("Enter Valid First Name ");
			document.frmDonateProg.txtFName.focus();
			return false; }
			if( document.frmDonateProg.txtFName.value.length>80 )
			{ alert("Enter within 80 characters for First Name" );
			document.frmDonateProg.txtFName.focus();
			return false; }
			
			//for Last Name
			if(document.frmDonateProg.txtLName.value=="")
			{alert(" Last Name cannot be empty ");
			document.frmDonateProg.txtLName.focus();
			return false; }
			if(document.frmDonateProg.txtLName.value.indexOf(' ')==0)
			{alert("Enter Last Name ");
			document.frmDonateProg.txtLName.focus();
			return false; }
			
			if(isnotAlpha1(document.frmDonateProg.txtLName.value))
			{ alert("Enter Valid Last Name ");
			document.frmDonateProg.txtLName.focus();
			return false; }
			if(Dospace(document.frmDonateProg.txtLName.value))
			{ alert("Enter Valid Last Name ");
			document.frmDonateProg.txtLName.focus();
			return false; }
			if( document.frmDonateProg.txtLName.value.length>80 )
			{ alert("Enter within 80 characters for Last Name" );
			document.frmDonateProg.txtLName.focus();
			return false; }
			
			//for city
			if(document.frmDonateProg.txtCity.value=="")
			{alert(" Enter City Name ");
			document.frmDonateProg.txtCity.focus();
			return false; }
			
			if(document.frmDonateProg.txtCity.value.indexOf(' ')==0)
			{alert("Enter City ");
			document.frmDonateProg.txtCity.focus();
			return false; }
			
			if(isnotAlpha2(document.frmDonateProg.txtCity.value))
			{ alert("Enter Valid City ");
			document.frmDonateProg.txtCity.focus();
			return false; }
			if(Dospace(document.frmDonateProg.txtCity.value))
			{ alert("Enter Valid City ");
			document.frmDonateProg.txtCity.focus();
			return false; }
			if( document.frmDonateProg.txtCity.value.length>80 )
			{ alert("Enter within 80 characters for City " );
			document.frmDonateProg.txtCity.focus();
			return false; }
			
			//for state
			if(document.frmDonateProg.txtState.value=="")
			{alert(" Enter State Name ");
			document.frmDonateProg.txtState.focus();
			return false; }
			if(document.frmDonateProg.txtState.value.indexOf(' ')==0)
			{alert("Enter State ");
			document.frmDonateProg.txtState.focus();
			return false; }
			
			if(isnotAlpha2(document.frmDonateProg.txtState.value))
			{ alert("Enter Valid State ");
			document.frmDonateProg.txtState.focus();
			return false; }
			if(Dospace(document.frmDonateProg.txtState.value))
			{ alert("Enter Valid State");
			document.frmDonateProg.txtState.focus();
			return false; }
			if( document.frmDonateProg.txtState.value.length>80 )
			{ alert("Enter within 80 characters for State " );
			document.frmDonateProg.txtState.focus();
			return false; }*/
	}else{
		if(document.frmDonateProg.txtPrefix.value.indexOf(' ')==0)
		{alert("Enter Valid Salutation ");
		document.frmDonateProg.txtPrefix.focus();
		return false; }
		if(isnotAlphaNumeric(document.frmDonateProg.txtPrefix.value))
		{ alert("Enter Valid Salutation ");
		document.frmDonateProg.txtPrefix.focus();
		return false; }
		if(Dospace(document.frmDonateProg.txtPrefix.value))
		{ alert("Enter Valid Salutation ");
		document.frmDonateProg.txtPrefix.focus();
		return false; }
		if( document.frmDonateProg.txtPrefix.value.length>5 )
		{ alert("Enter within 5 characters for Salutation" );
		document.frmDonateProg.txtPrefix.focus();
		return false; }
		
		
		//for first name
		if(document.frmDonateProg.txtFirstName.value=="")
		{alert(" First Name cannot be empty ");
		document.frmDonateProg.txtFirstName.focus();
		return false; }
		if(document.frmDonateProg.txtFirstName.value.indexOf(' ')==0)
		{alert("Enter First Name ");
		document.frmDonateProg.txtFirstName.focus();
		return false; }
		
		if(isnotAlpha1(document.frmDonateProg.txtFirstName.value))
		{ alert("Enter Valid First Name ");
		document.frmDonateProg.txtFirstName.focus();
		return false; }
		if(Dospace(document.frmDonateProg.txtFirstName.value))
		{ alert("Enter Valid First Name ");
		document.frmDonateProg.txtFirstName.focus();
		return false; }
		if( document.frmDonateProg.txtFirstName.value.length>80 )
		{ alert("Enter within 80 characters for First Name" );
		document.frmDonateProg.txtFirstName.focus();
		return false; }
		
		//for Last Name
		if(document.frmDonateProg.txtLastName.value=="")
		{alert(" Last Name cannot be empty ");
		document.frmDonateProg.txtLastName.focus();
		return false; }
		if(document.frmDonateProg.txtLastName.value.indexOf(' ')==0)
		{alert("Enter Last Name ");
		document.frmDonateProg.txtLastName.focus();
		return false; }
		
		if(isnotAlpha1(document.frmDonateProg.txtLastName.value))
		{ alert("Enter Valid Last Name ");
		document.frmDonateProg.txtLastName.focus();
		return false; }
		if(Dospace(document.frmDonateProg.txtLastName.value))
		{ alert("Enter Valid Last Name ");
		document.frmDonateProg.riderLname_id.focus();
		return false; }
		if( document.frmDonateProg.txtLastName.value.length>80 )
		{ alert("Enter within 80 characters for Last Name" );
		document.frmDonateProg.txtLastName.focus();
		return false; }
//for address1		
		if(document.frmDonateProg.sadd_txt.value=="")
		{alert(" Enter Address1 ");
		document.frmDonateProg.sadd_txt.focus();
		return false;}
		if(document.frmDonateProg.sadd_txt.value.length>255)
		{alert("Enter Address1 within 255 characters");
		document.frmDonateProg.sadd_txt.focus();
		return false;}
		if(Dospace(document.frmDonateProg.sadd_txt.value)){
		alert("Enter Valid Address1 ");
		document.frmDonateProg.sadd_txt.focus();
		return false;
		}
		if(document.frmDonateProg.sadd_txt.value.indexOf(" ")==0){
		alert("Enter Valid Address1");
		document.frmDonateProg.sadd_txt.focus();
		return false; 
		}
//for address2		
		if(document.frmDonateProg.sadd_txt1.value.length>255)
		{alert("Enter Address2 within 255 characters");
		document.frmDonateProg.sadd_txt1.focus();
		return false;}
		if(document.frmDonateProg.sadd_txt1.value.indexOf(" ")==0){
		alert("Enter Valid Address2");
		document.frmDonateProg.sadd_txt1.focus();
		return false; 
		}
//__________________________________________city___________________________________________________________________
		
		if(document.frmDonateProg.scity_txt.value=="")
		{alert(" City cannot be empty");
		document.frmDonateProg.scity_txt.focus();
		return false; }
		if(document.frmDonateProg.scity_txt.value.indexOf(' ')==0)
		{alert("Enter valid City");
		document.frmDonateProg.scity_txt.focus();
		return false; }
		
		if(isnotAlpha2(document.frmDonateProg.scity_txt.value))
		{ alert("Enter Valid City");
		document.frmDonateProg.scity_txt.focus();
		return false; }
		if(Dospace(document.frmDonateProg.scity_txt.value))
		{ alert("Enter  Valid City");
		document.frmDonateProg.scity_txt.focus();
		return false; }
		if( document.frmDonateProg.scity_txt.value.length>80 )
		{ alert("Enter within 80 characters for City" );
		document.frmDonateProg.scity_txt.focus();
		return false; }		
//___________________________________________________ZipCode_________________________________________________________
		if(document.frmDonateProg.szip_txt.value==""){
		alert("Enter Zipcode");
		document.frmDonateProg.szip_txt.focus();
		return false;
		}
		
		if((document.frmDonateProg.szip_txt.value.length <3&& document.frmDonateProg.sCountry_sel.value!="USA")){
		alert("Enter Valid Zipcode");
		document.frmDonateProg.szip_txt.focus();
		return false;
		}
		
		if((document.frmDonateProg.szip_txt.value.length >20 && document.frmDonateProg.sCountry_sel.value!="USA")){
		alert("Enter Valid Zipcode");
		document.frmDonateProg.szip_txt.focus();
		return false;
		}
		if((document.frmDonateProg.szip_txt.value.length !=5&& document.frmDonateProg.sCountry_sel.value=="USA")){
		alert("Enter Valid Zipcode");
		document.frmDonateProg.szip_txt.focus();
		return false;
		}
		
		if(Dospace(document.frmDonateProg.szip_txt.value)){
		alert("Enter Valid Zipcode");
		document.frmDonateProg.szip_txt.focus();
		return false;
		}	
		if(document.frmDonateProg.szip_txt.value.indexOf(" ")==0){
		alert("Enter Valid Zipcode");
		document.frmDonateProg.szip_txt.focus();
		return false; 
		}		
//___________________________________________________Country______________________________________________________
		if(document.frmDonateProg.sCountry_sel.selectedIndex==0)
		{alert(" Country cannot be empty");
		document.frmDonateProg.sCountry_sel.focus();
		return false; }

//------------------------------------------------State-------------------------------------------------------------	 
		if(document.frmDonateProg.sState_sel.selectedIndex==0)
		{alert(" State cannot be empty");
		document.frmDonateProg.sState_sel.focus();
		return false; }		
//----------------------IF ENTER EITHER Secondary Phone Number OR Secondary mobile Number
		/*if(document.frmDonateProg.sphone_txt.value=="")
		{ alert("Enter Phone Number");
		document.frmDonateProg.sphone_txt.focus();
		return false;}*/
		if(Dospace(document.frmDonateProg.sphone_txt.value)){
		alert("Enter Valid Phone Number");
		document.frmDonateProg.sphone_txt.focus();
		return false;
		}
		if(document.frmDonateProg.sphone_txt.value.indexOf(" ")==0){
		alert("Enter Valid Phone Number");
		document.frmDonateProg.sphone_txt.focus();
		return false; 
		}		
//--------------------------------Secondary Phone Number---------------------------------------
		if(document.frmDonateProg.sphone_txt.value!=""){		  
		len7=document.frmDonateProg.sphone_txt.value.length;
		strnum = document.frmDonateProg.sphone_txt.value;
		var GoodChars = "0123456789()- ";
		valid = 1;
		for(j=0;j<len7;j++){
		if(GoodChars.indexOf(strnum.charAt(j))==-1){
		valid=0;
		}
		}
		if(valid!=1){
		alert("Enter valid Phone Number");
		document.frmDonateProg.sphone_txt.focus();
		return false;
		}
		if(document.frmDonateProg.sphone_txt.value.length>40){
		alert("Enter valid Phone Number");
		document.frmDonateProg.sphone_txt.focus();
		return false;
		}
		}
//----------------------------------Secondary MOBILE---------------------------------------
		if(document.frmDonateProg.smob_txt.value!="")
		{		
		len7=document.frmDonateProg.smob_txt.value.length;
		//alert("len7"+len7);
		strnum = document.frmDonateProg.smob_txt.value;
		var GoodChars = "0123456789()-+ ";
		valid = 1;
		for(j=0;j<len7;j++)
		{ if(GoodChars.indexOf(strnum.charAt(j))==-1)
		{ valid=0;} }
		if(valid!=1)
		{alert("Enter valid mobile Number");
		document.frmDonateProg.smob_txt.focus();
		return false;}
		
		if(document.frmDonateProg.smob_txt.value.length>40){
		alert("Enter valid mobile Number");
		document.frmDonateProg.smob_txt.focus();
		return false;
		}
		}	
//for email		
		if(document.frmDonateProg.email.value=="")
		{alert("Enter your Email ID");
		document.frmDonateProg.email.focus();
		return false;}
		
		if(document.frmDonateProg.email.value.indexOf(" ")==0)
		{alert("Enter a Valid Email ID");
		document.frmDonateProg.email.focus();
		return false;}
		
		if(!(document.frmDonateProg.email.value== ""))
		{ strmail=document.frmDonateProg.email.value;
		firstat = strmail.indexOf("@");
		lastat = strmail.lastIndexOf("@");
		strmain=strmail.substring(0,firstat);
		strsub=strmail.substring(firstat,document.frmDonateProg.email.value.length);
		if(strmail.length>120)
		{alert("Email is out of range");
		document.frmDonateProg.email.focus();
		return false;}
		if(strmain.indexOf('  ')!=-1 || firstat==0 || strsub.indexOf(' ')!=-1 )
		{alert("Enter valid Email ");
		document.frmDonateProg.email.focus();
		return false;}
		if(isnotSpecial(strmain) || isnotSpecial(strsub))
		{alert("Enter valid Email ");
		document.frmDonateProg.email.focus();
		return false;}
		k=0;
		strlen=strsub.length;
		for(i=0;i<strlen-1;i++)
		{ if(strsub.charAt(i)=='.')
		{k=k+1;}}
		if(k>3)
		{alert("Enter valid Email ");
		document.frmDonateProg.email.focus();
		return false;}
		if(firstat==-1 || lastat==-1)
		{alert("Enter valid Email" );
		document.frmDonateProg.email.focus();
		return false;}
		if(Number(strmain))
		{alert("Enter valid Email ");
		document.frmDonateProg.email.focus();
		return false;}
		if(firstat != lastat )
		{alert("Enter valid Email ");
		document.frmDonateProg.email.focus();
		return false;}
		firstdot=strmail.indexOf(".",firstat);
		lastdot=strmail.lastIndexOf(".");
		if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
		{alert("Enter valid Email ");
		document.frmDonateProg.email.focus();
		return false;}
		}
		
 } 
 

	chbxnum2=document.frmDonateProg.donCbxCt.value;
    //alert(chbxnum2);
	for(i=0;i<chbxnum2;i++){ 
	  chbxname = "donCb"+i;
	  txtname="donTb"+i;
	  dontxtname="donNametb"+i;
	  
	  //alert(chbxname); 
	  if(document.getElementById(''+chbxname).checked){ 
		  var txtval= document.getElementById(''+txtname).value;
			 if(txtval=="" ||txtval==0){
				 alert("Enter the Donation Amount");
				 document.getElementById(''+txtname).focus();
				 return false;
			 }
			  //alert(chbxname);
			 if(!Number(txtval)){
				alert("Enter valid Donation Amount");
				document.getElementById(''+txtname).focus();
				return false;
			 }
			if(txtval.indexOf('.')>8){
				alert("Enter valid Donation Amount");
				document.getElementById(''+txtname).focus();;
				return false;
			}
			if((txtval.indexOf('.'))==-1){
				if(txtval.length>8){
					alert("Enter valid Donation Amount");
					document.getElementById(''+txtname).focus();;
					return false;
				}
			}
			
			if(!(txtval=="")){
				if((txtval.indexOf('.'))!=-1){
					fstr=txtval;
					fstr1=txtval.lastIndexOf('.');
					mm = (fstr.substring(fstr1,txtval.length));
					str=(Number(mm.length));
					if((str)>3){
						alert("Donation Amount is not valid");
						document.getElementById(''+txtname).focus();;
						return false;
					}
				}
			}
			if(document.getElementById(''+dontxtname).value=="" || document.getElementById(''+dontxtname).value.indexOf(" ")==0){
				alert("Enter the Donation Name");
				document.getElementById(''+dontxtname).focus();
				return false;
			}
	  }
	  // return false;
	  
	  if(document.getElementById('totalAmount').value==0)
	  {
		alert("Please Make Donation");
		return false;
	  }
	}

//================================== For Card Details ========================================

 		if(document.frmDonateProg.ccTypeId.selectedIndex == 0 ){
			alert("Please select card type." );
			document.frmDonateProg.ccTypeId.focus();
			return false;
		}
//================================== For Card Number =========================================
		if(document.frmDonateProg.cardNo.value==""){
			alert("Enter a Card Number in the Card Details");
			document.frmDonateProg.cardNo.focus();
			return false;
		}
		if(!Number(document.frmDonateProg.cardNo.value)){
			alert("Enter valid Card Number in the Card Details");
			document.frmDonateProg.cardNo.focus();
			return false;
		}
		if(document.frmDonateProg.ccTypeId.selectedIndex == 3 ){
			if(document.frmDonateProg.cardNo.value.length!=15 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmDonateProg.cardNo.focus();
				return false;
			}
		}
		if(document.frmDonateProg.ccTypeId.selectedIndex == 1 || document.frmDonateProg.ccTypeId.selectedIndex == 2 ){
			if(document.frmDonateProg.cardNo.value.length!=16 ){
				alert("Enter valid Card Number in the Card Details");
				document.frmDonateProg.cardNo.focus();
				return false;
			}
		}
			if(document.frmDonateProg.cardNo.value.indexOf('.')!=-1){
				alert("Enter Valid Card Number in the Card Details.");
				document.frmDonateProg.cardNo.focus();
				return false;
			}
	
//================================== For CVV Number =========================================
	
		if(document.frmDonateProg.txtCvvId.value!=""){
				
		if(document.frmDonateProg.txtCvvId.value.indexOf(" ")==0 ){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		if(document.frmDonateProg.txtCvvId.value.indexOf('  ')!=-1){
			alert("Empty space(s) are not allowed for CVV Number");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		if ( document.frmDonateProg.ccTypeId.selectedIndex == 1 || document.frmDonateProg.ccTypeId.selectedIndex == 2 ){
			if(document.frmDonateProg.txtCvvId.value.length !=3){
			alert("Enter valid CVV Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		    }
		}
		if ( document.frmDonateProg.ccTypeId.selectedIndex == 3 ){
			if(document.frmDonateProg.txtCvvId.value.length !=4){
			alert("Enter valid CVV Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
			}
		}
		
		if(document.frmDonateProg.txtCvvId.value.indexOf('.')!=-1){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}
		
		if(isnotInteger(document.frmDonateProg.txtCvvId.value)){
			alert("Enter Valid Cvv Number in Card Details");
			document.frmDonateProg.txtCvvId.focus();
			return false;
		}	
		}
	
//================================= For Print Name on Card =================================	
		if(document.frmDonateProg.printName.value==""){
			alert("Enter Print Name in Card Details.");
			document.frmDonateProg.printName.focus();
			return false;
		}
	
		if(document.frmDonateProg.printName.value.length>80){
			alert("Entered Print Name in Card Details is out of Range.");
			document.frmDonateProg.printName.focus();
			return false;
		}
	
		if(isnotAlpha(document.frmDonateProg.printName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmDonateProg.printName.focus();
			return false;
		}
	
		if(Number(document.frmDonateProg.printName.value)){
			alert("Enter a valid Print Name in Card Details.");
			document.frmDonateProg.printName.focus();
			return false;
		}
	
		if(document.frmDonateProg.printName.value.indexOf(' ')==0){
			alert("Enter a Valid Print Name in Card Details.");
			document.frmDonateProg.printName.focus();
			return false;
		}
	
		if(document.frmDonateProg.printName.value.indexOf('  ')!==-1){
			alert("Enter Valid Print Name in Card Details.");
			document.frmDonateProg.printName.focus();
			return false;
		}

//================================= For Expiry Month =================================	
	
		var rightNow=new Date();
		var theYear=rightNow.getYear();
		var tMonth=rightNow.getMonth();
		var theMonth=1+tMonth;
		var theDate=rightNow.getDate();
	
		if ( document.frmDonateProg.selExpMonthId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration Month." );
			document.frmDonateProg.selExpMonthId.focus();
			return false;
		}

	
		if (document.frmDonateProg.selExpYearId.value==theYear){
			 if(document.frmDonateProg.selExpMonthId.value<theMonth){
				alert("Please Select Valid Card Expiration Month.");
				document.frmDonateProg.selExpMonthId.focus();
				return(false);   
			}
		}
		
//================================= For Expiry Year =================================	
	
		if (document.frmDonateProg.selExpYearId.selectedIndex == 0 ){
			alert ( "Please select a Card Expiration year." );
			document.frmDonateProg.selExpYearId.focus();
			return false;
		}
	
		if (document.frmDonateProg.selExpYearId.value<theYear){
			alert("Please Select Valid Card Expiration Year.");
			document.frmDonateProg.selExpYearId.focus();
			return false;   
		}
	

//---------------------------------------------------------------------------------
	
return true;
}

function format(obj, decimal) {
	//alert("obj" +obj);
 //decimal  - the number of decimals after the digit from 0 to 3
//-- Returns the passed number as a string in the xxx,xxx.xx format.
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

	  
	//You could include a dollar sign in the return value.
	  //retval =  "$"+retval
	  //alert("retval" + retval);
	  return retval;
 }
