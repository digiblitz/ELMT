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
//------------------------------ MRO Organizer Registration Member Details Ajax Script -------------------------

var arHttpRequest;

function HLCMemberDetails()
{
	
	if(document.myform.eventsecid.value!="" && document.myform.eventsecid.value.indexOf(" ")!=0)
	{
    var memberid=document.myform.eventsecid.value;

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
					document.myform.eventsecid.value="";
					document.myform.eventsecid.focus();
					
					document.myform.hlcstaffname.value="";
					document.myform.address.value="";
					document.myform.city.value="";
					document.myform.phone.value="";
					document.myform.fax.value="";
					document.myform.email.value="";
										
					return false;
                }
				else
				{					
					document.myform.hlcstaffname.value="";
					document.myform.address.value="";
					document.myform.city.value="";
					document.myform.phone.value="";
					document.myform.fax.value="";
					document.myform.email.value="";
										
					details();
				}

            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
	
//----------------------------------------------

var httpRequestDet;

function details()
{

if(document.myform.eventsecid.value!="" && document.myform.eventsecid.value.indexOf(' ')!=0)
	{

   rid=document.myform.eventsecid.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "RiderInfoAjaxAction.do?rid="+rid; 

        if (window.ActiveXObject) 
        { 
            httpRequestDet = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequestDet = new XMLHttpRequest(); 
        } 
     
        httpRequestDet.open("GET", url, true); 
        
        httpRequestDet.onreadystatechange =processRequest; 
        httpRequestDet.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest() 
    { 
   
        if (httpRequestDet.readyState == 4) 
        { 
            if(httpRequestDet.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequestDet.responseXML.getElementsByTagName("salutation")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateDet(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequestDet.status +":"+ httpRequestDet.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateDet(salutationXML) 
    { 

        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
        var rdetails=new Array();
        rdetails=null;
        rdetails=salutationText.split("#");
      
	  // alert("rdetails[6] :"+rdetails[6]);
	  		
	    if(rdetails[1]==NaN || rdetails[1]==undefined || rdetails[1]==null || rdetails[1]=="null")
        {
        document.myform.hlcstaffname.value="NA";
        }else
        document.myform.hlcstaffname.value=rdetails[1];

        if(rdetails[5]==NaN || rdetails[5]==undefined || rdetails[5]==null || rdetails[5].length==0 || rdetails[5]=="null")
        {
        document.myform.address.value="NA";
        }else
        document.myform.address.value=rdetails[5];

        if(rdetails[7]==NaN || rdetails[7]==undefined || rdetails[7]==null || rdetails[7]=="null")
        {
        document.myform.city .value="NA";
        }else
        document.myform.city .value=rdetails[7]; 

        if(rdetails[10]==NaN || rdetails[10]==undefined || rdetails[10]==null || rdetails[10]=="null")
        {
        document.myform.phone.value="NA";
        }else
        document.myform.phone.value=rdetails[10];

        if(rdetails[12]==NaN || rdetails[12]==undefined || rdetails[12]==null || rdetails[12]=="null")
        {
        document.myform.email.value="NA";
        }else
        document.myform.email.value=rdetails[12];
		
		if(rdetails[13]==NaN || rdetails[13]==undefined || rdetails[13]==null || rdetails[13]=="null" || rdetails[13]=="")
        {
        document.myform.fax.value="NA";
        }else
        document.myform.fax.value=rdetails[13];

    } 

//------------------------------ MRO Organizer Registration Ajax Script -------------------------

var arHttpRequest;

function secdetails()
{

	if(document.myform.eventsecid.value!="" && document.myform.eventsecid.value.indexOf(" ")!=0)
	{
	
    var secId=document.myform.eventsecid.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./OrgRegFrmAjax.do?secId="+secId; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {secRequest(); } ; 
        arHttpRequest.send(null); 
   } 
}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function secRequest() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
		 var arnameText=null;
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("secretary")[0]; 
                 arnameText = arnameXML.childNodes[0].nodeValue; 
                 
        var secdetails=new Array();
        secdetails=null;
        secdetails=arnameText.split("#");
		
        if(secdetails[0]==NaN||secdetails[0]==undefined||secdetails[0]==null||secdetails[0]==""||secdetails[0].length==0)
        {
        document.myform.hlcstaffname.value="";
        }
        else
        document.myform.hlcstaffname.value=secdetails[0];

        if(secdetails[1]==NaN||secdetails[1]==undefined||secdetails[1]==null||secdetails[1]==""||secdetails[1].length==0)
        {
        document.myform.address.value="";
        }
        else
        document.myform.address.value=secdetails[1];

        if(secdetails[0]==NaN||secdetails[0]==undefined||secdetails[0]==null||secdetails[0]==""||secdetails[0].length==0)
        {
        document.myform.city.value="";
        }
        else
        document.myform.city.value=secdetails[2];
		
        if(secdetails[0]==NaN||secdetails[0]==undefined||secdetails[0]==null||secdetails[0]==""||secdetails[0].length==0)
        {
        document.myform.phone.value="";
        }
        else
        document.myform.phone.value=secdetails[3];

       if(secdetails[0]==NaN||secdetails[0]==undefined||secdetails[0]==null||secdetails[0]==""||secdetails[0].length==0)
        {
        document.myform.fax.value="";
        }
        else
        document.myform.fax.value=secdetails[4];

       if(secdetails[0]==NaN||secdetails[0]==undefined||secdetails[0]==null||secdetails[0]==""||secdetails[0].length==0)
        {
        document.myform.email.value="";
        }
        else
        document.myform.email.value=secdetails[5];

          if(secdetails[0]==""&&secdetails[1]==""&&secdetails[2]==""&&secdetails[3]==""&&secdetails[4]==""&&secdetails[5]=="")
          {
            alert("Event Secretary ID Doesn't Exist !");
            document.myform.eventsecid.value="";
            document.myform.eventsecid.focus();
          }    
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 


//--------------------------------------------------------------------------------------

function isnotAlphaNumeric(str){
	stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
function isnotAlpNum(str){
	stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\";
f3=1;
for(j=0;j<str.length;j++)
{if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1)
{f3=0;}}
if(f3==0)
{return true;}else{return false;}
}
//---------------------------------------------------------------------------------------
//--------------------------------Calendar function---------------------------------------
function popupCal(val){
     
    if(val==1)
         document.myform.DateImported1.value = "StartDate1";
		 
	  if(val==2)
         document.myform.DateImported2.value = "StartDate1";
		
	  if(val==3)
         document.myform.DateImported3.value = "StartDate1";
		 
	 if(val==4)
	      document.myform.dateAvailable.value = "StartDate1";

	  if(val==5)
         document.myform.date1.value = "StartDate1";
		
	  if(val==6)
         document.myform.todate.value = "StartDate1";
		 
	 if(val==7)
         document.myform.CrossCountryDate.value = "StartDate1";
		
	  if(val==1)
         document.myform.BirthDate.value = "StartDate1";
		
		 
	 
		
	

      val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null){
         alert("Plz Enable Site Popup Allowed");
		
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

//-----------------------function for pure Integer Numbers----------------------------
function isnotInteger(str){
	stringIntCheck="+-()0123456789.";
	f2=1;
	for(j=0;j<str.length;j++){
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{
		return false;
	}
}
//------------------------------------------------------------------------------------------
//-----------------------function for pure Integer Numbers----------------------------
function isnotPhone(str){
	stringIntCheck="0123456789";
	f2=1;
	for(j=0;j<str.length;j++){
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{
		return false;
	}
}
//-------------------------------------------------------------------------
//-----------------------function for pure Integer Numbers----------------------------
function isnotInteger1(str){
	stringIntCheck="+-()0123456789";
	f2=1;
	for(j=0;j<str.length;j++){
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{
		return false;
	}
}
//------------------------------------------------------------------------------------------


//-----------------------function for validating Phone Numbers is not working ---------------------------
function isDate(str){
	f2=1;
	var todayDate=new Date();
	var nowDate=todayDate.getDate();
	var nowYear=todayDate.getYear();
	var nowMonth=todayDate.getMonth();

	if(nowDate<10){
		nowDate="0"+nowDate;
	}

	if(nowMonth<10){
		nowMonth="0"+nowMonth;
	}

	strdate=str;
	mm = Number(strdate.substring(0,2));
	dd = Number(strdate.substring(3,5));
	yyyy=(strdate.substring(6,str.length));
	yyyy1=(Number(yyyy.length));

	if(yyyy<nowYear){
		f2=0;
	}

	if((mm<=nowMonth)&&(yyyy==nowYear)){
		f2=0;
	}

	else if (mm>=12){
		f2=0;
	}

	if((dd<nowDate)&&(mm==nowMonth)){
		f2=0;
	}

	else if(dd>=31){
		f2=0;
	}

	if(yyyy1>4){
		f2=0;
	}
	if((str.indexOf('\\'))!=-1){
		f2=0;
	}
	if(f1==0){
		return true;
	}
	else{
		return false;
	}
}

//------------------------------------------------------------------------------------------
function showLevels(chkBxNam,divId){
	if(document.myform.elements[chkBxNam].checked == true){
		document.getElementById(divId).style.display = "block";
	}
    else{
        document.getElementById(divId).style.display = "none";
	}
}

function showHide(chkBxNam,divId){
	if(document.myform.elements[chkBxNam].checked == true){
		document.getElementById(divId).style.display = "block";
	}
	else{
		document.getElementById(divId).style.display = "none";
	}
}

function initCondition(){
	document.getElementById('parta').style.display = "block";
	document.getElementById('partb').style.display = "none";
	document.getElementById('partc').style.display = "none";
	document.getElementById('partd').style.display = "none";
	document.getElementById('parte').style.display = "none";
}

function expandColl(divId){
	if(document.getElementById(divId).style.display == "none"){
		document.getElementById(divId).style.display = "block";
	}
	else{
		document.getElementById(divId).style.display = "none";
	}
}

function showHideRadio(radioNam,divId,radVal){
	if(document.myform.elements[radioNam].value = radVal){
		document.getElementById(divId).style.display = "none";
	}
	else{
		document.getElementById(divId).style.display = "block";
	}
}

function showHideRadio1(radioNam,divId,radVal){
	if(document.myform.elements[radioNam].value = radVal){
		document.getElementById(divId).style.display = "none";
	}
	else{
		document.getElementById(divId).style.display = "block";
	}
}

function switchDiv(divID){
	var styleObj = getStyleObject(divID);
	if (styleObj){
		//hideAll();
		changeObjectDisplay(divID, "block");
	}
	else{
		document.getElementById(divId).style.display = "none";
		alert("sorry, this only works in browsers that do Dynamic HTML");
	}
}

function getStyleObject(objectId){
	// checkW3C DOM, then MSIE 4, then NN 4.
	//
	if(document.getElementById && document.getElementById(objectId)){
		return document.getElementById(objectId).style;
   }
   else if (document.all && document.all(objectId)){
		return document.all(objectId).style;
   }
   else if (document.layers && document.layers[objectId]) {
		return document.layers[objectId];
   }
   else{
		return false;
	}
}

function changeObjectDisplay(objectId, newDisplay){
	// first get the objects stylesheet
	var styleObject = getStyleObject(objectId);
	// then if we find a stylesheet, set its visibility
	// as requested
	//
	if (styleObject) {
		styleObject.display = newDisplay;
		return true;
	}
	else {
		return false;
	}
}

// For Entries in form B

function dblEntryclear(){
	document.myform.dblEntry.value="";
}

function lpOneclear(){
	document.myform.lpOne.value="";
}

function lpTwoclear(){
	document.myform.lpTwo.value="";
}

function lpOtherclear(){
	document.myform.lpOther.value="";
}

function nonRefclear(){
	document.myform.nonRef.value="";
}

function offFeeclear(){
	document.myform.offFee1.value="";
}

function waitListclear(){
	document.myform.waitList.value="";
}

function compFeeclear(){
	document.myform.compFee.value="";
}

function nctReq1clear(){
	document.myform.nctInState.value="";
	document.myform.nctOutState.value="";
}

function nctStateclear(){
	document.myform.nctReq1.value="";
}

function nctReqNotclear(){
	document.myform.nctReq1.value="";
	document.myform.nctInState.value="";
	document.myform.nctOutState.value="";
}

function nctReqOthclear(){
	document.myform.nctReq1.value="";
	document.myform.nctInState.value="";
	document.myform.nctOutState.value="";
}

function clearone(){
	document.myform.qtyTwo.value="";
	document.myform.amountnew.value="";
	document.myform.timenew.value="";
	document.myform.qTwo1.value="";
}

function cleartwo(){
	document.myform.qtyOne.value="";
	document.myform.amountnew.value="";
	document.myform.timenew.value="";
	document.myform.qTwo1.value="";
}

function clearthree(){
	document.myform.qtyOne.value="";
	document.myform.qtyTwo.value="";
	document.myform.qTwo1.value="";
}

function clearfour(){
	document.myform.qtyOne.value="";
	document.myform.qtyTwo.value="";
	document.myform.amountnew.value="";
	document.myform.timenew.value="";
	document.myform.qTwo1.value="";
}

function clearfive(){
	document.myform.qtyOne.value="";
	document.myform.qtyTwo.value="";
	document.myform.amountnew.value="";
	document.myform.timenew.value="";
	document.myform.qTwo1.value="";
}

function clearsix(){
	document.myform.qtyOne.value="";
	document.myform.qtyTwo.value="";
	document.myform.amountnew.value="";
	document.myform.timenew.value="";
}

function awrdOthclear(){
	document.myform.awrdOth.value="";
}

function awrdOthclear4(){
	document.myform.dateDivOth.value="";
}

function awrdclear(){
	document.myform.dateDivOth.value="";
}

function awrdOthclear1(){
	document.myform.stablingtime1.value="";
	document.myform.stablingminute1.value="";
	document.myform.stablingformat1.value="";
	document.myform.stablingtime2.value="";
	document.myform.stablingminute2.value="";
	document.myform.stablingformat2.value="";
}

function awrdOthclear2(){
	document.myform.perStallOne1.value="";
	document.myform.amount1.value="";
	document.myform.stablingtime1.value="";
	document.myform.date1.value="";
	document.myform.todate.value="";
}

function ribbonsclear(){
	document.myform.ribbons.value="";
}

//Comment the PartA begin

//checking the Event Secretary ID properities

function check1(){
	if(document.myform.eventsecid.value==""){
		alert("Event Secretary ID is empty");
		//onBlur="alert('Event Secretary ID is empty')";
		document.myform.eventsecid.focus();
		return false;
	}
	if(!(document.myform.eventsecid.value=="")){
		if(document.myform.eventsecid.value.length>=20){
			alert("Event Secretary ID is Limited by 20 char");
			document.myform.eventsecid.focus();
			return false;
		}
		if((document.myform.eventsecid.value.indexOf('!'))!=-1||(document.myform.eventsecid.value.indexOf('@'))!=-1||(document.myform.eventsecid.value.indexOf('#'))!=-1||(document.myform.eventsecid.value.indexOf('$'))!=-1||(document.myform.eventsecid.value.indexOf('%'))!=-1||(document.myform.eventsecid.value.indexOf('^'))!=-1||(document.myform.eventsecid.value.indexOf('&'))!=-1||(document.myform.eventsecid.value.indexOf('*'))!=-1||(document.myform.eventsecid.value.indexOf('('))!=-1||(document.myform.eventsecid.value.indexOf(')'))!=-1||(document.myform.eventsecid.value.indexOf('_'))!=-1||(document.myform.eventsecid.value.indexOf('-'))!=-1||(document.myform.eventsecid.value.indexOf('+'))!=-1||(document.myform.eventsecid.value.indexOf('='))!=-1||(document.myform.eventsecid.value.indexOf('|'))!=-1||(document.myform.eventsecid.value.indexOf('/'))!=-1||(document.myform.eventsecid.value.indexOf('?'))!=-1||(document.myform.eventsecid.value.indexOf('>'))!=-1||(document.myform.eventsecid.value.indexOf('<'))!=-1||(document.myform.eventsecid.value.indexOf(';'))!=-1||(document.myform.eventsecid.value.indexOf(','))!=-1||(document.myform.eventsecid.value.indexOf('{'))!=-1||(document.myform.eventsecid.value.indexOf('}'))!=-1||(document.myform.eventsecid.value.indexOf(']'))!=-1||(document.myform.eventsecid.value.indexOf('['))!=-1||(document.myform.eventsecid.value.indexOf('~'))!=-1||(document.myform.eventsecid.value.indexOf('`'))!=-1||(document.myform.eventsecid.value.indexOf('"'))!=-1||(document.myform.eventsecid.value.indexOf(':'))!=-1||(document.myform.eventsecid.value.indexOf('.'))!=-1||(document.myform.eventsecid.value.indexOf('\''))!=-1||(document.myform.eventsecid.value.indexOf('\\'))!=-1||(document.myform.eventsecid.value.indexOf('  '))!=-1||(document.myform.eventsecid.value.indexOf(' '))==0){
			alert("Event Secretary ID is not valid");
			document.myform.eventsecid.focus();
			return false;
		}
	}
}

//Commenting for the database Access

function check(){
	if(document.myform.eventtitle.value==""){
		alert("Event Title is empty");
		document.myform.eventtitle.focus();
		return false;
	}
	if(isnotAlpNum(document.myform.eventtitle.value)){
		alert("Event Title is not valid");
		document.myform.eventtitle.focus();
		return false;
	}
	if(document.myform.eventtitle.value.lengtrh>80){
		alert("Event Title is out of range");
		document.myform.eventtitle.focus();
		return false;
	}	
	if(document.myform.eventsecid.value==""){
		alert("Event Secretary ID is empty");
		//onBlur="alert('Event Secretary ID is empty')";
		document.myform.eventsecid.focus();
		return false;
	}
	
/*
	if(!Number(document.myform.eventsecid.value)){
		alert("Event Secretary ID should be a number");
	 	document.myform.eventsecid.focus();
	 	return false;
	 }

	if(document.myform.hlcstaffname.value==""){
		alert("Enter the User Name");
		document.myform.hlcstaffname.focus();
		return false;
	}

	if(document.myform.address.value==""){
		alert("Enter the address");
		document.myform.address.focus();
		return false;
	}
	
	if(document.myform.city.value==""){
		alert("Enter the city");
		document.myform.city.focus();
		return false;
	}

	if(document.myform.phone.value==""){
		alert("Enter the Telephone Number");
		document.myform.phone.focus();
		return false;
	}

	if(document.myform.fax.value==""){
		alert("Enter the fax");
		document.myform.fax.focus();
		return false;
	}

	if(document.myform.email.value==""){
		alert("Enter the email");
		document.myform.email.focus();
		return false;
	}
*/

	//End of Database Access

//------------------------------------------------------------------------------
 //for officials
	num=document.myform.offct.value;
	
	chkname="";
	var chckstr="";
for(i=0;i<num;i++)
{   chkname="official"+i;
    txtname="officialtxt"+i;
	
	if(document.getElementById(''+chkname).checked)
	 { chckstr= document.getElementById(''+txtname).value;
	   //alert(chckstr);
	   if(chckstr=="")
	   {alert("Enter a Official name");
	    document.getElementById(''+txtname).focus();
		return false;}
	   if(isnotAlpha(chckstr))
	   {alert("Enter a valid Official Name");
	    document.getElementById(''+txtname).focus();
		return false;}
	   if(chckstr.length>80)
	   {alert("Official Name is out of range");
	    document.getElementById(''+txtname).focus();
		return false;}
		 //alert(chkname);
		 //return false;
	  }
	 //return false;
}

 /*checking the Technical Delegate properities

	if(document.myform.techdelegate.value==""){
		alert("Technical Delegate Name is empty");
		document.myform.techdelegate.focus();
		return false;
	}
	if(document.myform.techdelegate.value.length >=80){
		alert("Technical Delegate Name is Limited by 80 Char ");
		document.myform.techdelegate.focus();
		return false;
	}
	if(isnotAlpha(document.myform.techdelegate.value)){
		alert("Technical Delegate Name is not valid");
		document.myform.techdelegate.focus();
		return false;
	}
	if((document.myform.techdelegate.value.indexOf('!'))!=-1||(document.myform.techdelegate.value.indexOf('@'))!=-1||(document.myform.techdelegate.value.indexOf('#'))!=-1||(document.myform.techdelegate.value.indexOf('$'))!=-1||(document.myform.techdelegate.value.indexOf('%'))!=-1||(document.myform.techdelegate.value.indexOf('^'))!=-1||(document.myform.techdelegate.value.indexOf('&'))!=-1||(document.myform.techdelegate.value.indexOf('*'))!=-1||(document.myform.techdelegate.value.indexOf('('))!=-1||(document.myform.techdelegate.value.indexOf(')'))!=-1||(document.myform.techdelegate.value.indexOf('_'))!=-1||(document.myform.techdelegate.value.indexOf('-'))!=-1||(document.myform.techdelegate.value.indexOf('+'))!=-1||(document.myform.techdelegate.value.indexOf('='))!=-1||(document.myform.techdelegate.value.indexOf('|'))!=-1||(document.myform.techdelegate.value.indexOf('/'))!=-1||(document.myform.techdelegate.value.indexOf('?'))!=-1||(document.myform.techdelegate.value.indexOf('>'))!=-1||(document.myform.techdelegate.value.indexOf('<'))!=-1||(document.myform.techdelegate.value.indexOf(';'))!=-1||(document.myform.techdelegate.value.indexOf(','))!=-1||(document.myform.techdelegate.value.indexOf('{'))!=-1||(document.myform.techdelegate.value.indexOf('}'))!=-1||(document.myform.techdelegate.value.indexOf(']'))!=-1||(document.myform.techdelegate.value.indexOf('['))!=-1||(document.myform.techdelegate.value.indexOf('~'))!=-1||(document.myform.techdelegate.value.indexOf('`'))!=-1||(document.myform.techdelegate.value.indexOf('"'))!=-1||(document.myform.techdelegate.value.indexOf(':'))!=-1||(document.myform.techdelegate.value.indexOf('.'))!=-1||(document.myform.techdelegate.value.indexOf('0'))!=-1||(document.myform.techdelegate.value.indexOf('1'))!=-1||(document.myform.techdelegate.value.indexOf('2'))!=-1||(document.myform.techdelegate.value.indexOf('3'))!=-1||(document.myform.techdelegate.value.indexOf('4'))!=-1||(document.myform.techdelegate.value.indexOf('5'))!=-1||(document.myform.techdelegate.value.indexOf('6'))!=-1||(document.myform.techdelegate.value.indexOf('7'))!=-1||(document.myform.techdelegate.value.indexOf('8'))!=-1||(document.myform.techdelegate.value.indexOf('9'))!=-1||(document.myform.techdelegate.value.indexOf('\''))!=-1||(document.myform.techdelegate.value.indexOf('\\'))!=-1||(document.myform.techdelegate.value.indexOf('  '))!=-1||(document.myform.techdelegate.value.indexOf(' '))==0){
		alert("Technical Delegate Name is not valid ");
		document.myform.techdelegate.focus();
		return false;
	}

 //checking the Ground jury properities

	if(document.myform.groundjury.value==""){
		alert("Ground jury Name is empty");
		document.myform.groundjury.focus();
		return false;
	}

	if(document.myform.groundjury.value.length >=80){
		alert("Ground jury Name is Limited by 80 Char ");
		document.myform.groundjury.focus();
		return false;
	}
	if(!(document.myform.groundjury.value=="")){
		if((document.myform.groundjury.value.indexOf('!'))!=-1||(document.myform.groundjury.value.indexOf('@'))!=-1||(document.myform.groundjury.value.indexOf('#'))!=-1||(document.myform.groundjury.value.indexOf('$'))!=-1||(document.myform.groundjury.value.indexOf('%'))!=-1||(document.myform.groundjury.value.indexOf('^'))!=-1||(document.myform.groundjury.value.indexOf('&'))!=-1||(document.myform.groundjury.value.indexOf('*'))!=-1||(document.myform.groundjury.value.indexOf('('))!=-1||(document.myform.groundjury.value.indexOf(')'))!=-1||(document.myform.groundjury.value.indexOf('_'))!=-1||(document.myform.groundjury.value.indexOf('-'))!=-1||(document.myform.groundjury.value.indexOf('+'))!=-1||(document.myform.groundjury.value.indexOf('='))!=-1||(document.myform.groundjury.value.indexOf('|'))!=-1||(document.myform.groundjury.value.indexOf('/'))!=-1||(document.myform.groundjury.value.indexOf('?'))!=-1||(document.myform.groundjury.value.indexOf('>'))!=-1||(document.myform.groundjury.value.indexOf('<'))!=-1||(document.myform.groundjury.value.indexOf(';'))!=-1||(document.myform.groundjury.value.indexOf(','))!=-1||(document.myform.groundjury.value.indexOf('{'))!=-1||(document.myform.groundjury.value.indexOf('}'))!=-1||(document.myform.groundjury.value.indexOf(']'))!=-1||(document.myform.groundjury.value.indexOf('['))!=-1||(document.myform.groundjury.value.indexOf('~'))!=-1||(document.myform.groundjury.value.indexOf('`'))!=-1||(document.myform.groundjury.value.indexOf('"'))!=-1||(document.myform.groundjury.value.indexOf(':'))!=-1||(document.myform.groundjury.value.indexOf('.'))!=-1||(document.myform.groundjury.value.indexOf('0'))!=-1||(document.myform.groundjury.value.indexOf('1'))!=-1||(document.myform.groundjury.value.indexOf('2'))!=-1||(document.myform.groundjury.value.indexOf('3'))!=-1||(document.myform.groundjury.value.indexOf('4'))!=-1||(document.myform.groundjury.value.indexOf('5'))!=-1||(document.myform.groundjury.value.indexOf('6'))!=-1||(document.myform.groundjury.value.indexOf('7'))!=-1||(document.myform.groundjury.value.indexOf('8'))!=-1||(document.myform.groundjury.value.indexOf('9'))!=-1||(document.myform.groundjury.value.indexOf('\''))!=-1||(document.myform.groundjury.value.indexOf('\\'))!=-1||(document.myform.groundjury.value.indexOf('  '))!=-1||(document.myform.groundjury.value.indexOf(' '))==0){
			alert("Ground jury Name  is not valid ");
			document.myform.groundjury.focus();
			return false;
		}
	}


 //checking the Judge properities

	if(document.myform.judge1.value==""){
		alert("Judge Name is empty");
		document.myform.judge1.focus();
		return false;
	}
	if(document.myform.judge1.value.length >=80){
		alert("Judge Name is Limited by 80 Char ");
		document.myform.techdelegate.focus();
		return false;
	}
	if((document.myform.judge1.value.indexOf('!'))!=-1||(document.myform.judge1.value.indexOf('@'))!=-1||(document.myform.judge1.value.indexOf('#'))!=-1||(document.myform.judge1.value.indexOf('$'))!=-1||(document.myform.judge1.value.indexOf('%'))!=-1||(document.myform.judge1.value.indexOf('^'))!=-1||(document.myform.judge1.value.indexOf('&'))!=-1||(document.myform.judge1.value.indexOf('*'))!=-1||(document.myform.judge1.value.indexOf('('))!=-1||(document.myform.judge1.value.indexOf(')'))!=-1||(document.myform.judge1.value.indexOf('_'))!=-1||(document.myform.judge1.value.indexOf('-'))!=-1||(document.myform.judge1.value.indexOf('+'))!=-1||(document.myform.judge1.value.indexOf('='))!=-1||(document.myform.judge1.value.indexOf('|'))!=-1||(document.myform.judge1.value.indexOf('/'))!=-1||(document.myform.judge1.value.indexOf('?'))!=-1||(document.myform.judge1.value.indexOf('>'))!=-1||(document.myform.judge1.value.indexOf('<'))!=-1||(document.myform.judge1.value.indexOf(';'))!=-1||(document.myform.judge1.value.indexOf(','))!=-1||(document.myform.judge1.value.indexOf('{'))!=-1||(document.myform.judge1.value.indexOf('}'))!=-1||(document.myform.judge1.value.indexOf(']'))!=-1||(document.myform.judge1.value.indexOf('['))!=-1||(document.myform.judge1.value.indexOf('~'))!=-1||(document.myform.judge1.value.indexOf('`'))!=-1||(document.myform.judge1.value.indexOf('"'))!=-1||(document.myform.judge1.value.indexOf(':'))!=-1||(document.myform.judge1.value.indexOf('.'))!=-1||(document.myform.judge1.value.indexOf('0'))!=-1||(document.myform.judge1.value.indexOf('1'))!=-1||(document.myform.judge1.value.indexOf('2'))!=-1||(document.myform.judge1.value.indexOf('3'))!=-1||(document.myform.judge1.value.indexOf('4'))!=-1||(document.myform.judge1.value.indexOf('5'))!=-1||(document.myform.judge1.value.indexOf('6'))!=-1||(document.myform.judge1.value.indexOf('7'))!=-1||(document.myform.judge1.value.indexOf('8'))!=-1||(document.myform.judge1.value.indexOf('9'))!=-1||(document.myform.judge1.value.indexOf('\''))!=-1||(document.myform.judge1.value.indexOf('\\'))!=-1||(document.myform.judge1.value.indexOf('  '))!=-1||(document.myform.judge1.value.indexOf(' '))==0){
		alert("Judge Name is not valid ");
		document.myform.judge1.focus();
		return false;
	}
	if(document.myform.judge2.value==""){
		alert("Judge Name is empty");
		document.myform.judge2.focus();
		return false;
	}
	if(document.myform.judge2.value.length>=80){
		alert("Judge Name is Limited by 80 Char ");
		document.myform.judge2.focus();
		return false;
	}
	if((document.myform.judge2.value.indexOf('!'))!=-1||(document.myform.judge2.value.indexOf('@'))!=-1||(document.myform.judge2.value.indexOf('#'))!=-1||(document.myform.judge2.value.indexOf('$'))!=-1||(document.myform.judge2.value.indexOf('%'))!=-1||(document.myform.judge2.value.indexOf('^'))!=-1||(document.myform.judge2.value.indexOf('&'))!=-1||(document.myform.judge2.value.indexOf('*'))!=-1||(document.myform.judge2.value.indexOf('('))!=-1||(document.myform.judge2.value.indexOf(')'))!=-1||(document.myform.judge2.value.indexOf('_'))!=-1||(document.myform.judge2.value.indexOf('-'))!=-1||(document.myform.judge2.value.indexOf('+'))!=-1||(document.myform.judge2.value.indexOf('='))!=-1||(document.myform.judge2.value.indexOf('|'))!=-1||(document.myform.judge2.value.indexOf('/'))!=-1||(document.myform.judge2.value.indexOf('?'))!=-1||(document.myform.judge2.value.indexOf('>'))!=-1||(document.myform.judge2.value.indexOf('<'))!=-1||(document.myform.judge2.value.indexOf(';'))!=-1||(document.myform.judge2.value.indexOf(','))!=-1||(document.myform.judge2.value.indexOf('{'))!=-1||(document.myform.judge2.value.indexOf('}'))!=-1||(document.myform.judge2.value.indexOf(']'))!=-1||(document.myform.judge2.value.indexOf('['))!=-1||(document.myform.judge2.value.indexOf('~'))!=-1||(document.myform.judge2.value.indexOf('`'))!=-1||(document.myform.judge2.value.indexOf('"'))!=-1||(document.myform.judge2.value.indexOf(':'))!=-1||(document.myform.judge2.value.indexOf('.'))!=-1||(document.myform.judge2.value.indexOf('0'))!=-1||(document.myform.judge2.value.indexOf('1'))!=-1||(document.myform.judge2.value.indexOf('2'))!=-1||(document.myform.judge2.value.indexOf('3'))!=-1||(document.myform.judge2.value.indexOf('4'))!=-1||(document.myform.judge2.value.indexOf('5'))!=-1||(document.myform.judge2.value.indexOf('6'))!=-1||(document.myform.judge2.value.indexOf('7'))!=-1||(document.myform.judge2.value.indexOf('8'))!=-1||(document.myform.judge2.value.indexOf('9'))!=-1||(document.myform.judge2.value.indexOf('\''))!=-1||(document.myform.judge2.value.indexOf('\\'))!=-1||(document.myform.judge2.value.indexOf('  '))!=-1||(document.myform.judge2.value.indexOf(' '))==0){
		alert("Judge Name is not valid  ");
		document.myform.judge2.focus();
		return false;
	}
	if(document.myform.judge3.value==""){
		alert("Judge Name is empty");
		document.myform.judge3.focus();
		return false;
	}
	if(document.myform.judge3.value.length>=80){
		alert("Judge Name is Limited by 80 Char");
		document.myform.judge3.focus();
		return false;
	}
	if((document.myform.judge3.value.indexOf('!'))!=-1||(document.myform.judge3.value.indexOf('@'))!=-1||(document.myform.judge3.value.indexOf('#'))!=-1||(document.myform.judge3.value.indexOf('$'))!=-1||(document.myform.judge3.value.indexOf('%'))!=-1||(document.myform.judge3.value.indexOf('^'))!=-1||(document.myform.judge3.value.indexOf('&'))!=-1||(document.myform.judge3.value.indexOf('*'))!=-1||(document.myform.judge3.value.indexOf('('))!=-1||(document.myform.judge3.value.indexOf(')'))!=-1||(document.myform.judge3.value.indexOf('_'))!=-1||(document.myform.judge3.value.indexOf('-'))!=-1||(document.myform.judge3.value.indexOf('+'))!=-1||(document.myform.judge3.value.indexOf('='))!=-1||(document.myform.judge3.value.indexOf('|'))!=-1||(document.myform.judge3.value.indexOf('/'))!=-1||(document.myform.judge3.value.indexOf('?'))!=-1||(document.myform.judge3.value.indexOf('>'))!=-1||(document.myform.judge3.value.indexOf('<'))!=-1||(document.myform.judge3.value.indexOf(';'))!=-1||(document.myform.judge3.value.indexOf(','))!=-1||(document.myform.judge3.value.indexOf('{'))!=-1||(document.myform.judge3.value.indexOf('}'))!=-1||(document.myform.judge3.value.indexOf(']'))!=-1||(document.myform.judge3.value.indexOf('['))!=-1||(document.myform.judge3.value.indexOf('~'))!=-1||(document.myform.judge3.value.indexOf('`'))!=-1||(document.myform.judge3.value.indexOf('"'))!=-1||(document.myform.judge3.value.indexOf(':'))!=-1||(document.myform.judge3.value.indexOf('.'))!=-1||(document.myform.judge3.value.indexOf('0'))!=-1||(document.myform.judge3.value.indexOf('1'))!=-1||(document.myform.judge3.value.indexOf('2'))!=-1||(document.myform.judge3.value.indexOf('3'))!=-1||(document.myform.judge3.value.indexOf('4'))!=-1||(document.myform.judge3.value.indexOf('5'))!=-1||(document.myform.judge3.value.indexOf('6'))!=-1||(document.myform.judge3.value.indexOf('7'))!=-1||(document.myform.judge3.value.indexOf('8'))!=-1||(document.myform.judge3.value.indexOf('9'))!=-1||(document.myform.judge3.value.indexOf('\''))!=-1||(document.myform.judge3.value.indexOf('\\'))!=-1||(document.myform.judge3.value.indexOf('  '))!=-1||(document.myform.judge3.value.indexOf(' '))==0){
		alert("Judge Name is not valid");
		document.myform.judge3.focus();
		return false;
	}


//checking the Jumpjudge properities

	if(document.myform.jumpjudge.value==""){
		alert("Jumping Judge Name is empty");
		document.myform.jumpjudge.focus();
		return false;
	}
	if(document.myform.jumpjudge.value.length >=80){
		alert("Jumping Judge Name is Limited by 80 Char");
		document.myform.jumpjudge.focus();
		return false;
	}
	if((document.myform.jumpjudge.value.indexOf('!'))!=-1||(document.myform.jumpjudge.value.indexOf('@'))!=-1||(document.myform.jumpjudge.value.indexOf('#'))!=-1||(document.myform.jumpjudge.value.indexOf('$'))!=-1||(document.myform.jumpjudge.value.indexOf('%'))!=-1||(document.myform.jumpjudge.value.indexOf('^'))!=-1||(document.myform.jumpjudge.value.indexOf('&'))!=-1||(document.myform.jumpjudge.value.indexOf('*'))!=-1||(document.myform.jumpjudge.value.indexOf('('))!=-1||(document.myform.jumpjudge.value.indexOf(')'))!=-1||(document.myform.jumpjudge.value.indexOf('_'))!=-1||(document.myform.jumpjudge.value.indexOf('-'))!=-1||(document.myform.jumpjudge.value.indexOf('+'))!=-1||(document.myform.jumpjudge.value.indexOf('='))!=-1||(document.myform.jumpjudge.value.indexOf('|'))!=-1||(document.myform.jumpjudge.value.indexOf('/'))!=-1||(document.myform.jumpjudge.value.indexOf('?'))!=-1||(document.myform.jumpjudge.value.indexOf('>'))!=-1||(document.myform.jumpjudge.value.indexOf('<'))!=-1||(document.myform.jumpjudge.value.indexOf(';'))!=-1||(document.myform.jumpjudge.value.indexOf(','))!=-1||(document.myform.jumpjudge.value.indexOf('{'))!=-1||(document.myform.jumpjudge.value.indexOf('}'))!=-1||(document.myform.jumpjudge.value.indexOf(']'))!=-1||(document.myform.jumpjudge.value.indexOf('['))!=-1||(document.myform.jumpjudge.value.indexOf('~'))!=-1||(document.myform.jumpjudge.value.indexOf('`'))!=-1||(document.myform.jumpjudge.value.indexOf('"'))!=-1||(document.myform.jumpjudge.value.indexOf(':'))!=-1||(document.myform.jumpjudge.value.indexOf('.'))!=-1||(document.myform.jumpjudge.value.indexOf('0'))!=-1||(document.myform.jumpjudge.value.indexOf('1'))!=-1||(document.myform.jumpjudge.value.indexOf('2'))!=-1||(document.myform.jumpjudge.value.indexOf('3'))!=-1||(document.myform.jumpjudge.value.indexOf('4'))!=-1||(document.myform.jumpjudge.value.indexOf('5'))!=-1||(document.myform.jumpjudge.value.indexOf('6'))!=-1||(document.myform.jumpjudge.value.indexOf('7'))!=-1||(document.myform.jumpjudge.value.indexOf('8'))!=-1||(document.myform.jumpjudge.value.indexOf('9'))!=-1||(document.myform.jumpjudge.value.indexOf('\''))!=-1||(document.myform.jumpjudge.value.indexOf('\\'))!=-1||(document.myform.jumpjudge.value.indexOf('  '))!=-1||(document.myform.jumpjudge.value.indexOf(' '))==0){
		alert("Jumping Judge Name not valid");
		document.myform.jumpjudge.focus();
		return false;
	}

 //checking the Coursedesign properities

	if(document.myform.coursedesign.value==""){
		alert("Course Designer Name is empty");
		document.myform.coursedesign.focus();
		return false;
	}
	if(document.myform.coursedesign.value.length>=80){
		alert("Course Designer Name is Limited by 80 Char");
		document.myform.coursedesign.focus();
		return false;
	}
	if((document.myform.coursedesign.value.indexOf('!'))!=-1||(document.myform.coursedesign.value.indexOf('@'))!=-1||(document.myform.coursedesign.value.indexOf('#'))!=-1||(document.myform.coursedesign.value.indexOf('$'))!=-1||(document.myform.coursedesign.value.indexOf('%'))!=-1||(document.myform.coursedesign.value.indexOf('^'))!=-1||(document.myform.coursedesign.value.indexOf('&'))!=-1||(document.myform.coursedesign.value.indexOf('*'))!=-1||(document.myform.coursedesign.value.indexOf('('))!=-1||(document.myform.coursedesign.value.indexOf(')'))!=-1||(document.myform.coursedesign.value.indexOf('_'))!=-1||(document.myform.coursedesign.value.indexOf('-'))!=-1||(document.myform.coursedesign.value.indexOf('+'))!=-1||(document.myform.coursedesign.value.indexOf('='))!=-1||(document.myform.coursedesign.value.indexOf('|'))!=-1||(document.myform.coursedesign.value.indexOf('/'))!=-1||(document.myform.coursedesign.value.indexOf('?'))!=-1||(document.myform.coursedesign.value.indexOf('>'))!=-1||(document.myform.coursedesign.value.indexOf('<'))!=-1||(document.myform.coursedesign.value.indexOf(';'))!=-1||(document.myform.coursedesign.value.indexOf(','))!=-1||(document.myform.coursedesign.value.indexOf('{'))!=-1||(document.myform.coursedesign.value.indexOf('}'))!=-1||(document.myform.coursedesign.value.indexOf(']'))!=-1||(document.myform.coursedesign.value.indexOf('['))!=-1||(document.myform.coursedesign.value.indexOf('~'))!=-1||(document.myform.coursedesign.value.indexOf('`'))!=-1||(document.myform.coursedesign.value.indexOf('"'))!=-1||(document.myform.coursedesign.value.indexOf(':'))!=-1||(document.myform.coursedesign.value.indexOf('.'))!=-1||(document.myform.coursedesign.value.indexOf('0'))!=-1||(document.myform.coursedesign.value.indexOf('1'))!=-1||(document.myform.coursedesign.value.indexOf('2'))!=-1||(document.myform.coursedesign.value.indexOf('3'))!=-1||(document.myform.coursedesign.value.indexOf('4'))!=-1||(document.myform.coursedesign.value.indexOf('5'))!=-1||(document.myform.coursedesign.value.indexOf('6'))!=-1||(document.myform.coursedesign.value.indexOf('7'))!=-1||(document.myform.coursedesign.value.indexOf('8'))!=-1||(document.myform.coursedesign.value.indexOf('9'))!=-1||(document.myform.coursedesign.value.indexOf('\''))!=-1||(document.myform.coursedesign.value.indexOf('\\'))!=-1||(document.myform.coursedesign.value.indexOf('  '))!=-1||(document.myform.coursedesign.value.indexOf(' '))==0){
		alert("Course Designer Name is not valid");
		document.myform.coursedesign.focus();
		return false;
	}

 //checking the JumpCoursedesign properities

	if(document.myform.jumpcoursedesign.value==""){
		alert("Jump Course Designer Name is empty");
		document.myform.jumpcoursedesign.focus();
		return false;
	}
	if(document.myform.jumpcoursedesign.value.length>=80){
		alert("Jump Course Designer Name is Limited by 80 char");
		document.myform.jumpcoursedesign.focus();
		return false;
	}

	if((document.myform.jumpcoursedesign.value.indexOf('!'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('@'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('#'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('$'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('%'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('^'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('&'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('*'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('('))!=-1||(document.myform.jumpcoursedesign.value.indexOf(')'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('_'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('-'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('+'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('='))!=-1||(document.myform.jumpcoursedesign.value.indexOf('|'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('/'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('?'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('>'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('<'))!=-1||(document.myform.jumpcoursedesign.value.indexOf(';'))!=-1||(document.myform.jumpcoursedesign.value.indexOf(','))!=-1||(document.myform.jumpcoursedesign.value.indexOf('{'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('}'))!=-1||(document.myform.jumpcoursedesign.value.indexOf(']'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('['))!=-1||(document.myform.jumpcoursedesign.value.indexOf('~'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('`'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('"'))!=-1||(document.myform.jumpcoursedesign.value.indexOf(':'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('.'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('0'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('1'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('2'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('3'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('4'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('5'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('6'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('7'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('8'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('9'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('\''))!=-1||(document.myform.jumpcoursedesign.value.indexOf('\\'))!=-1||(document.myform.jumpcoursedesign.value.indexOf('  '))!=-1||(document.myform.jumpcoursedesign.value.indexOf(' '))==0){
		alert("JumpCourse name is not valid");
		document.myform.jumpcoursedesign.focus();
		return false;
	}
//****************************part A coding Completed*********************

//*************************************** Part B Coding Started *************************/

//fees
	if(document.myform.fees.value==""){
		alert("The Fees column can't be blank");
		document.myform.fees.focus();
		return false;
	}
	if(!(document.myform.fees.value=="")){
		if((document.myform.fees.value.indexOf('.'))!=-1){
			fstr=document.myform.fees.value;
			fstr1=document.myform.fees.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.myform.fees.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Fees is not valid ");
				document.myform.fees.focus();
				return false;
			}
		}
	}

	if((!Number(document.myform.fees.value))||(document.myform.fees.value.indexOf('-'))!=-1){
		alert("Enter valid number in Fees");
		document.myform.fees.focus();
		return false;
	}

	if(document.myform.fees.value>9999.99){
		alert("Fees should not exceed more than $9999.99 ");
		document.myform.fees.focus();
		return false;
	}

//others
	if(!(document.myform.others.value=="")){
		if(!Number(document.myform.others.value)){
			alert("Enter valid number");
			document.myform.others.focus();
			return false;
		}
		if(document.myform.others.value>9999.99){
			alert("Other should not exceed more than $9999.99 ");
			document.myform.others.focus();
			return false;
		}
		if(document.myform.others.value.length>255){
			alert("Other should not exceed more than 255 char ");
			document.myform.others.focus();
			return false;
		}
	}


//yes and no

	var choosen="";
	len1 = document.myform.dblE.length ;
	for(i=0; i<len1; i++){
		if(document.myform.dblE[i].checked){
			choosen=document.myform.dblE[i].value;
		}
	}
	if(choosen==""){
		alert("Double Entry Option is empty");
		return false;
	}
	if(choosen=="yes"){
		if(document.myform.dblEntry.value==""){
			alert("Double Entry is empty");
			document.myform.dblEntry.focus();
			return false;
		}

		if(!(document.myform.dblEntry.value=="")){
			if((document.myform.dblEntry.value.indexOf('.'))!=-1){
				fstr=document.myform.dblEntry.value;
				fstr1=document.myform.dblEntry.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.dblEntry.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Double Entry is not valid ");
					document.myform.dblEntry.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.dblEntry.value))||(document.myform.dblEntry.value.indexOf('-'))!=-1){
			alert("Double Entry should be a number");
			document.myform.dblEntry.focus();
			return false;
		}
		if(document.myform.dblEntry.value>999.99){
			alert("Double Entry should not exceed more than $999.99");
			document.myform.dblEntry.focus();
			return false;
		}
	}
//Draw order check
	if(document.myform.drawCheck.value==""){
		alert("Draw check to the order is empty");
		document.myform.drawCheck.focus();
		return false;
	}
	if(isnotAlphaNumeric(document.myform.drawCheck.value)){
		alert("Draw order check is not valid");
		document.myform.drawCheck.focus();
		return false;
	}
	if((document.myform.drawCheck.value.indexOf(' '))==0){
		alert("Draw order check is not valid");
		document.myform.drawCheck.focus();
		return false;
	}
	if(document.myform.drawCheck.value>255){
			alert("Draw order check is not exceed more than 255 char ");
			document.myform.drawCheck.focus();
			return false;
		}

//payment details
	var choosen1="";
	len2 = document.myform.r1.length ;
	for(i=0; i<len2; i++){
		if(document.myform.r1[i].checked){
			choosen1=document.myform.r1[i].value;
		}
	}
	if(choosen1==""){
		alert("Payment check details is empty");
		return false;
	}
	if(Number(choosen1)==0){
		if(document.myform.lpOne.value==""){
			alert("Charge for lost pinny is empty");
			document.myform.lpOne.focus();
			return false;
		}
		if(!(document.myform.lpOne.value=="")){
			if((document.myform.lpOne.value.indexOf('.'))!=-1){
				fstr=document.myform.lpOne.value;
				fstr1=document.myform.lpOne.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.lpOne.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Charge for lost pinny is not valid ");
					document.myform.lpOne.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.lpOne.value))||(document.myform.lpOne.value.indexOf('-'))!=-1){
			alert("Charge should be a number");
			document.myform.lpOne.focus();
			return false;
		}
		if(document.myform.lpOne.value>999.99){
			alert("Charge for lost pinny should not exceed more than $999.99");
			document.myform.lpOne.focus();
			return false;
		}
	}

//Charge for last pinny
	if(Number(choosen1)==1){
		if(document.myform.lpTwo.value==""){
			alert("Charge for lost pinny is empty");
			document.myform.lpTwo.focus();
			return false;
		}
		if(!(document.myform.lpTwo.value=="")){
			if((document.myform.lpTwo.value.indexOf('.'))!=-1){
				fstr=document.myform.lpTwo.value;
				fstr1=document.myform.lpTwo.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.lpTwo.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Charge for lost pinny is not Valid");
					document.myform.lpTwo.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.lpTwo.value))||(document.myform.lpTwo.value.indexOf('-'))!=-1){
			alert("Charge should be a number");
			document.myform.lpTwo.focus();
			return false;
		}
		if(document.myform.lpTwo.value>999.99){
			alert("Charge for lost pinny should not exceed more than $999.99");
			document.myform.lpTwo.focus();
			return false;
		}
	}

//Charge for others
	if(Number(choosen1)==2){
		if(document.myform.lpOther.value==""){
			alert("Charge for lost pinny others is empty");
			document.myform.lpOther.focus();
			return false;
		}
		if(!(document.myform.lpOther.value=="")){
			if((document.myform.lpOther.value.indexOf('.'))!=-1){
				fstr=document.myform.lpOther.value;
				fstr1=document.myform.lpOther.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.lpOther.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Charge for lost pinny other value is not valid");
					document.myform.lpOther.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.lpOther.value))||(document.myform.lpOther.value.indexOf('-'))!=-1){
			alert("Charge for lost pinny others should be a number");
			document.myform.lpOther.focus();
			return false;
		}
		if(document.myform.lpOther.value>999.99){
			alert("Charge for lost pinny others should not exceed more than $999.99");
			document.myform.lpOther.focus();
			return false;
		}
	}
/*Before closing date
	var choosen2="";
	len3 = document.myform.radioset2.length ;
	for(i=0; i<len3; i++){
		if(document.myform.radioset2[i].checked){
			choosen2=document.myform.radioset2[i].value;
		}
	}
	if(choosen2==""){
		alert("Select the mode of refund for Before closing Date ");
		return false;
	}

	if(choosen2=="lessRef"){
		if(document.myform.nonRef.value==""){
			alert("Less non-refundable office fee is empty");
			document.myform.nonRef.focus();
			return false;
		}
		if(!(document.myform.nonRef.value=="")){
			if((document.myform.nonRef.value.indexOf('.'))!=-1){
				fstr=document.myform.nonRef.value;
				fstr1=document.myform.nonRef.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.nonRef.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Less non-refundable office fee is not valid ");
					document.myform.nonRef.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.nonRef.value))||(document.myform.nonRef.value.indexOf('-'))!=-1){
			alert("Less non-refundable office fee should be a number");
			document.myform.nonRef.focus();
			return false;
		}
		if(document.myform.nonRef.value>999.99){
			alert("Less non-refundable office fee should not exceed more than $999.99");
			document.myform.nonRef.focus();
			return false;
		}
	}
//Refund After Closing date
	var choosen3="";
	len4 = document.myform.radioset3.length ;
	for(i=0; i<len4; i++){
		if(document.myform.radioset3[i].checked){
			choosen3=document.myform.radioset3[i].value;
			}
		}
		if(choosen3==""){
			alert("Select the mode of Refund for Refunds After Closing Date");
			return false;
		}
		if(choosen3=="refFeeTwo"){
			if(document.myform.offFee1.value==""){
				alert(" Refunded less office fee is empty");
				document.myform.offFee1.focus();
				return false;
			}
		if(!(document.myform.offFee1.value=="")){
			if((document.myform.offFee1.value.indexOf('.'))!=-1){
				fstr=document.myform.offFee1.value;
				fstr1=document.myform.offFee1.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.offFee1.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert(" Refunded less office fee value is not valid ");
					document.myform.offFee1.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.offFee1.value))||(document.myform.offFee1.value.indexOf('-'))!=-1){
			alert(" Refunded less office fee should be a number");
			document.myform.offFee1.focus();
			return false;
		}
		if(document.myform.offFee1.value>999.99){
			alert(" Refunded less office fee should should not exceed more than $999.99");
			document.myform.offFee1.focus();
			return false;
		}
	}
	if(choosen3=="refFeeThree"){
		if(document.myform.waitList.value==""){
			alert("Refunded less office fee is empty");
			document.myform.waitList.focus();
			return false;
		}
		if(!(document.myform.waitList.value=="")){
			if((document.myform.waitList.value.indexOf('.'))!=-1){
				fstr=document.myform.waitList.value;
				fstr1=document.myform.waitList.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.waitList.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Refunded less office fee value is not valid ");
					document.myform.waitList.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.waitList.value))||(document.myform.waitList.value.indexOf('-'))!=-1){
			alert("Refunded less office fee should be a number");
			document.myform.waitList.focus();
			return false;
		}
		if(document.myform.waitList.value>999.99){
			alert("Refunded less office fee should not exceed more than $999.99");
			document.myform.waitList.focus();
			return false;
		}
	}
//Competition Cancellation
	var choosen4="";
	len5 = document.myform.r4.length ;
	for(i=0; i<len5; i++){
		if(document.myform.r4[i].checked){
			choosen4=document.myform.r4[i].value;
		}
	}
	if(choosen4==""){
		alert("Select the mode of Refund for Competition Cancellation ");
		return false;
	}
	if(choosen4=="refFeeSix"){
		if(document.myform.compFee.value==""){
			alert("Refunded less office fee is empty");
			document.myform.compFee.focus();
			return false;
		}
		if(!(document.myform.compFee.value=="")){
			if((document.myform.compFee.value.indexOf('.'))!=-1){
				fstr=document.myform.compFee.value;
				fstr1=document.myform.compFee.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.compFee.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Refunded less office fee value is not valid ");
					document.myform.compFee.focus();
					return false;
				}
			}
		}
		if((!Number(document.myform.compFee.value))||(document.myform.compFee.value.indexOf('-'))!=-1){
			alert("Refunded less office fee should be a number");
			document.myform.compFee.focus();
			return false;
		}
		if(document.myform.compFee.value>999.99){
			alert("Refunded less office fee should not exceed more than $999.99");
			document.myform.compFee.focus();
			return false;
		}
	}
*/
// for Refund Section
num2=document.myform.rulecbxct.value;
flag = true;
for(i=0;i<num2;i++)
{   chkname2="refund"+i;
    if(document.getElementById(''+chkname2).checked)
	{flag=false;}}
 if(flag)
 {alert("Select any of the options in Refund Section in Part B");
  return false;}
	
	
	
	

	//Negative Coggins Test
	var choosen5="";
	len6 = document.myform.r5.length ;
	for(i=0; i<len6; i++){
		if(document.myform.r5[i].checked){
			choosen5=document.myform.r5[i].value;
		}
	}
	if(choosen5==""){
		alert("Select the mode for Refund Negative Coggins Test ");
		return false;
	}

	if(choosen5=="nctRef"){
		if(document.myform.nctReq1.value==""){
			alert("Enter the Month");
			document.myform.nctReq1.focus();
			return false;
		}
		if(!(document.myform.nctReq1.value=="")){
			if((!Number(document.myform.nctReq1.value))||(document.myform.dblEntry.value.indexOf('-'))!=-1){
				alert("Enter valid Month");
				document.myform.nctReq1.focus();
				return false;
			}
			if(document.myform.nctReq1.value>240){
				alert("Enter valid Month");
				document.myform.nctReq1.focus();
				return false;
			}
			if((document.myform.nctReq1.value.indexOf('.'))!=-1){
				alert("Months should be Integer");
				document.myform.nctReq1.focus();
				return false;
			}
		}
	}
	if(choosen5=="nctRef1"){
		if(document.myform.nctInState.value==""){
			alert("Months-in-state is empty");
			document.myform.nctInState.focus();
			return false;
		}
		if(document.myform.nctOutState.value==""){
			alert(" Months-out-state is empty");
			document.myform.nctOutState.focus();
			return false;
		}
		
		if(!(document.myform.nctInState.value=="")){
			if(!Number(document.myform.nctInState.value)){
				alert("Months-in-state should be a number");
				document.myform.nctInState.focus();
				return false;
			}
			if(document.myform.nctInState.value >= 240){
				alert("Moths-in-state not valid");
				document.myform.nctInState.focus();
				return false;
			}
			if((document.myform.nctInState.value.indexOf('.'))!=-1){
				alert("Months-in-state should be Integer");
				document.myform.nctInState.focus();
				return false;
			}
		}
		if(!(document.myform.nctOutState.value=="")){
			if(!Number(document.myform.nctOutState.value)){
				alert("Months-Out-State should be a number");
				document.myform.nctOutState.focus();
				return false;
			}
			if(document.myform.nctOutState.value >240){
				alert("Moths-Out-State not Valid");
				document.myform.nctOutState.focus();
				return false;
			}
			if((document.myform.nctOutState.value.indexOf('.'))!=-1){
				alert("Months-Out-state should be Integer");
				document.myform.nctOutState.focus();
				return false;
			}
			

		}
				var sm=document.myform.nctInState.value;
				var em=document.myform.nctOutState.value;
				
			if(sm > em){
					
				alert("Month-in-State is not valid");
				document.myform.nctInState.focus();
				return false;
			}
			if((sm>=100)&&(em<=100)){
				alert("Month-in-State is not valid");
				document.myform.nctInState.focus();
				return false;
			}
			if(sm==em){
				alert("Month-in-State is not valid");
				document.myform.nctInState.focus();
				return false;
			}
			
			
		
	}


//*************************************** Part B Coding Completed *************************

//***************************Part C Coding Started *****************************************

//Date Checking
	if(document.myform.DateImported1.value==""){
		alert("Select the time in Tentative time schedule ");
		document.myform.DateImported1.focus();
		return false;
	}

	if((document.myform.DateImported1.value=="")){
		alert("Date is empty");
		document.myform.DateImported1.focus();
		return false;
	}

	if(!(document.myform.DateImported1.value=="")){
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

		if(!(document.myform.DateImported1.value=="")){
			strdate=document.myform.DateImported1.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.DateImported1.value.length));
			yyyy1=(Number(yyyy.length));

			if(yyyy<nowYear){
				alert("Enter valid Date");
				document.myform.DateImported1.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter valid Date");
				document.myform.DateImported1.focus();
				return false;
			}

			if (mm>12){
				alert("Enter valid Date");
				document.myform.DateImported1.focus();
				return false;
			}

			if((dd<nowDate)&&(mm==nowMonth)){
				alert("Enter valid Date");
				document.myform.DateImported1.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter valid Date");
				document.myform.DateImported1.focus();
				return false;
			}
			if((document.myform.DateImported1.value.indexOf('\\'))!=-1||(document.myform.DateImported1.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.DateImported1.focus();
				return false;
			}
		}
		if((document.myform.PhaseImported1.value=="")){
			alert("Phase is empty");
			document.myform.PhaseImported1.focus();
			return false;
		}
		if(document.myform.PhaseImported1.value.length>255){
				alert("Phase should be limited by 255 char");
				document.myform.PhaseImported1.focus();
				return false;
			}
		if(isnotAlphaNumeric(document.myform.PhaseImported1.value)){
			alert("Phase is not valid");
			document.myform.PhaseImported1.focus();
			return false;
		}
		if((document.myform.PhaseImported1.value.indexOf(' '))==0){
			alert("Phase is not valid");
			document.myform.PhaseImported1.focus();
			return false;
		}
		if((document.myform.TimeImported1.value=="")||(document.myform.MinuteImported1.value=="")||(document.myform.FormatImported1.value=="")){
			alert("Time is not valid ");
			document.myform.TimeImported1.focus();
			return false;
		}
	}
	if(!(document.myform.PhaseImported1.value=="")){
		if((document.myform.DateImported1.value=="")||(document.myform.TimeImported1.value=="")||(document.myform.MinuteImported1.value=="")||(document.myform.FormatImported1.value=="")){
			alert("Date and Time is empty");
			document.myform.DateImported1.focus();
			return false;
		}
	}
	if(!(document.myform.TimeImported1.value=="")||(document.myform.MinuteImported1.value=="")||(document.myform.FormatImported1.value=="")){
		if((document.myform.DateImported1.value=="")||(document.myform.PhaseImported1.value=="")){
			alert("Date and Phase is empty");
			document.myform.DateImported1.focus();
			return false;
		}
	}


//Checking the DateFormat2
	if(!(document.myform.DateImported2.value=="")){
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

		if(!(document.myform.DateImported2.value=="")){
			strdate=document.myform.DateImported2.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.DateImported2.value.length));
			yyyy1=(Number(yyyy.length));

			if(yyyy<nowYear){
				alert("Enter valid Date");
				document.myform.DateImported2.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter valid Date");
				document.myform.DateImported2.focus();
				return false;
			}

			if (mm>12){
				alert("Enter valid Date");
				document.myform.DateImported2.focus();
				return false;
			}

			if((dd<nowDate)&&(mm==nowMonth)){
				alert("Enter valid Date");
				document.myform.DateImported2.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter valid Date");
				document.myform.DateImported2.focus();
				return false;
			}
			if((document.myform.DateImported2.value.indexOf('\\'))!=-1||(document.myform.DateImported2.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.DateImported2.focus();
				return false;
			}
		}
		if((document.myform.PhaseImported2.value=="")){
			alert("Phase is empty");
			document.myform.PhaseImported2.focus();
			return false;
		}
		if(document.myform.PhaseImported2.value.length>255){
				alert("Phase should be limited by 255 char");
				document.myform.PhaseImported2.focus();
				return false;
			}
		if(isnotAlphaNumeric(document.myform.PhaseImported2.value)){
			alert("Phase is not valid");
			document.myform.PhaseImported2.focus();
			return false;
		}
		if((document.myform.PhaseImported2.value.indexOf(' '))==0){
			alert("Phase is not valid");
			document.myform.PhaseImported2.focus();
			return false;
		}
		if((document.myform.TimeImported2.value=="")||(document.myform.MinuteImported2.value=="")||(document.myform.FormatImported2.value=="")){
			alert("Time is not valid");
			document.myform.TimeImported2.focus();
			return false;
		}
	}
	if(!(document.myform.PhaseImported2.value=="")){
		if((document.myform.DateImported2.value=="")||(document.myform.TimeImported2.value=="")||(document.myform.MinuteImported2.value=="")||(document.myform.FormatImported2.value=="")){
			alert("Date and Time is empty");
			document.myform.DateImported2.focus();
			return false;
		}
	}
	if(!(document.myform.TimeImported2.value=="")||(!(document.myform.MinuteImported2.value==""))||(!(document.myform.FormatImported2.value==""))){
		if((document.myform.DateImported2.value=="")||(document.myform.PhaseImported2.value=="")){
			alert("Date and Phase is empty");
			document.myform.DateImported2.focus();
			return false;
		}
	}
//Checking the date3
	if(!(document.myform.DateImported3.value=="")){
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

		if(!(document.myform.DateImported3.value=="")){
			strdate=document.myform.DateImported3.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.DateImported3.value.length));
			yyyy1=(Number(yyyy.length));

			if(yyyy<nowYear){
				alert("Enter valid Date");
				document.myform.DateImported3.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter valid Date");
				document.myform.DateImported3.focus();
				return false;
			}

			if (mm>12){
				alert("Enter valid Date");
				document.myform.DateImported3.focus();
				return false;
			}

			if((dd<nowDate)&&(mm==nowMonth)){
				alert("Enter valid Date");
				document.myform.DateImported3.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter valid Date");
				document.myform.DateImported3.focus();
				return false;
			}
			if((document.myform.DateImported3.value.indexOf('\\'))!=-1||(document.myform.DateImported3.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.DateImported3.focus();
				return false;
			}
		}
//
		if((document.myform.PhaseImported3.value=="")){
			alert("Phase is empty");
			document.myform.PhaseImported3.focus();
			return false;
		}
		if(document.myform.PhaseImported3.value.length>255){
				alert("Phase should be limited by 255 char");
				document.myform.PhaseImported3.focus();
				return false;
			}
		if(isnotAlphaNumeric(document.myform.PhaseImported3.value)){
			alert("Phase is not valid");
			document.myform.PhaseImported3.focus();
			return false;
		}
		if((document.myform.PhaseImported3.value.indexOf(' '))==0){
			alert("Phase is not valid");
			document.myform.PhaseImported3.focus();
			return false;
		}
		if((document.myform.TimeImported3.value=="")||(document.myform.MinuteImported3.value=="")||(document.myform.FormatImported3.value=="")){
			alert("Time is not valid");
			document.myform.TimeImported3.focus();
			return false;
		}
	}

	if(!(document.myform.PhaseImported3.value=="")){
		if((document.myform.DateImported3.value=="")||(document.myform.TimeImported3.value=="")||(document.myform.MinuteImported3.value=="")||(document.myform.FormatImported3.value=="")){
			alert("Date and Time is empty");
			document.myform.DateImported3.focus();
			return false;
		}
	}
	if(!(document.myform.TimeImported3.value=="")||(!(document.myform.MinuteImported3.value==""))||(!(document.myform.FormatImported3.value==""))){
		if((document.myform.DateImported3.value=="")||(document.myform.PhaseImported3.value=="")){
			alert("Date and Phase is empty");
			document.myform.DateImported3.focus();
			return false;
		}
	}

//Awards Checking Ribbons and Checking other
	var choosen="";
	len7 = document.myform.r6.length ;
	for(i=0; i<len7; i++){
		if(document.myform.r6[i].checked){
			choosen=document.myform.r6[i].value;
		}
	}
	if(choosen==""){
		alert("Select the mode for Awards ");
		return false;
	}

	if(choosen=="awardOne"){
		if(document.myform.ribbons.value==""){
			alert("Trophy & Ribbons is empty");
			document.myform.ribbons.focus();
			return false;
		}
		if(!Number(document.myform.ribbons.value)){
			alert("Trophy & Ribbons Should be number");
			document.myform.ribbons.focus();
			return false;
		}
		if((document.myform.ribbons.value.indexOf('.'))!=-1){
			alert("Trophy & Ribbons Should be Integer number");
			document.myform.ribbons.focus();
			return false;
		}
	}
	if(choosen=="awardThree"){
		if(document.myform.awrdOth.value==""){
			alert("Award mode others option is empty");
			document.myform.awrdOth.focus();
			return false;
		}
		if(Number(document.myform.awrdOth.value)){
			alert("Award mode others option should not be a number");
			document.myform.awrdOth.focus();
			return false;
		}
		if((document.myform.awrdOth.value.indexOf(' '))==0){
			alert("Award mode other is not valid");
			document.myform.awrdOth.focus();
			return false;
		}
		if(document.myform.awrdOth.value.length>255){
			alert("Award mode other is not exceed more than 255 char");
			document.myform.awrdOth.focus();
			return false;
		}
	}

//Date Available Checking

	if((document.myform.dateAvailable.value=="")){
		alert("Date is empty");
		document.myform.dateAvailable.focus();
		return false;
	}
	if(!(document.myform.dateAvailable.value=="")){
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

		if(!(document.myform.dateAvailable.value=="")){
			strdate=document.myform.dateAvailable.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.dateAvailable.value.length));
			yyyy1=(Number(yyyy.length));
	
			if(yyyy<nowYear){
				alert("Enter valid Date");
				document.myform.dateAvailable.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter valid Date1");
				document.myform.dateAvailable.focus();
				return false;
			}

			if (mm>12){
				alert("Enter valid Date");
				document.myform.dateAvailable.focus();
				return false;
			}

			if((dd<nowDate)&&(mm==nowMonth)){
				alert("Enter valid Date");
				document.myform.dateAvailable.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter valid Date");
				document.myform.dateAvailable.focus();
				return false;
			}
			if((document.myform.dateAvailable.value.indexOf('\\'))!=-1||(document.myform.dateAvailable.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.date1.focus();
				return false;
			}
		}
	}


//Checking the Available form
	var choosen1="";
	len8 = document.myform.r7.length ;
	for(i=0; i<len8; i++){
		if(document.myform.r7[i].checked){
			choosen1=document.myform.r7[i].value;
		}
	}
	if(choosen1==""){
		alert("Select the option for Available from ");
		return false;
	}
	if(choosen1=="dateThree"){
		if(document.myform.dateDivOth.value==""){
			alert("Available mode other is empty");
			document.myform.dateDivOth.focus();
			return false;
		}
		if(document.myform.dateDivOth.value.length>255){
			alert("Available mode other should not exceed 255 char");
			document.myform.dateDivOth.focus();
			return false;
		}
		if((document.myform.dateDivOth.value.indexOf(' '))==0){
			alert("Phase is not valid");
			document.myform.dateDivOth.focus();
			return false;
		}
	}

//Stabling Stall Info:
	var choosen2="";
	len9 = document.myform.r8.length ;
	
	for(i=0; i<len9; i++){
		if(document.myform.r8[i].checked){
		    choosen2=document.myform.r8[i].value;
		}
	}
	if(choosen2==""){
		alert("Select the mode for Stabling stall info");
		return false;
	}
	if((choosen2=="stableOne")||(choosen2=="stableTwo")){
//Charges per stall per night. Per stall on a given time & date.
		var choosen3="";
		len10 = document.myform.r81.length ;
		for(i=0; i<len10; i++){
			if(document.myform.r81[i].checked){
				choosen3=document.myform.r81[i].value;
			}
		}
		if(choosen3==""){
			alert("Select the mode for Stallone ");
			return false;
		}
		if(choosen3=="unlStbOne"){
			if(document.myform.perStallOne1.value==""){
				alert("Charges per stall per night is empty");
				document.myform.perStallOne1.focus();
				return false;
			}
			if(((!Number(document.myform.perStallOne1.value))||(document.myform.perStallOne1.value.indexOf('-'))!=-1)){
				alert("Charges per stall per night Should be a number");
				document.myform.perStallOne1.focus();
				return false;
			}
			if(document.myform.perStallOne1.value>999.99){
				alert("Charges per stall per night amount should not exceed more than $999.99");
				document.myform.perStallOne1.focus();
				return false;
			}
		}

//Charges per stall per night. Per stall on a given time & date.
		if(choosen3=="unlStbTwo"){
			if(document.myform.amount1.value==""){
				alert(" Per stall on a given time & date amount is empty");
				document.myform.amount1.focus();
				return false;
			}
			if((!Number(document.myform.amount1.value))||(document.myform.amount1.value.indexOf('-'))!=-1){
				alert(" Per stall on a given time & date amount should be a number");
				document.myform.amount1.focus();
				return false;
			}
			if(document.myform.amount1.value>999.99){
				alert(" Per stall on a given time & date amount should not exceed more than $999.99");
				document.myform.amount1.focus();
				return false;
			}
			if((document.myform.amount1.value.indexOf('.'))!=-1){
				fstr=document.myform.amount1.value;
				fstr1=document.myform.amount1.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.amount1.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert(" Per stall on a given time & date amount is not valid ");
					document.myform.amount1.focus();
					return false;
				}
			}
			if((document.myform.stablingtime1.value=="")||(document.myform.stablingminute1.value=="")||(document.myform.stablingformat1.value=="")){
				alert("From time is empty");
				document.myform.stablingtime1.focus();
				return false;
			}
//-----------------------Start Date Checking----------------------------
			if(document.myform.date1.value==""){
			alert("Select a Start Date in stabling details");
			document.myform.date1.focus();
			return false;
		}

	if(!(document.myform.date1.value=="")){
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

		if(!(document.myform.date1.value=="")){
			strdate=document.myform.date1.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.date1.value.length));
			yyyy1=(Number(yyyy.length));

			if(yyyy<nowYear){
				alert("Enter valid Start Date");
				document.myform.date1.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter valid Start Date");
				document.myform.date1.focus();
				return false;
			}

			if (mm>12){
				alert("Enter valid Start Date");
				document.myform.date1.focus();
				return false;
			}

			if((dd<nowDate)&&(mm==nowMonth)){
				alert("Enter valid Start Date");
				document.myform.date1.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter valid Start Date");
				document.myform.date1.focus();
				return false;
			}
			if((document.myform.date1.value.indexOf('\\'))!=-1||(document.myform.date1.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.date1.focus();
				return false;
			}
		}

//------------Stabling Time Checking------------------------

		if((document.myform.stablingtime2.value=="")||(document.myform.stablingminute2.value=="")||(document.myform.stablingformat2.value=="")){
			alert("To time is empty");
			document.myform.stablingtime2.focus();
			return false;
		}

//------------------Compare the time--------------------------------------------
		if(document.myform.date1.value==document.myform.todate.value){
			if((document.myform.stablingtime1.value!=="")||(document.myform.stablingminute1.value!=="")||(document.myform.stablingformat1.value!=="")){
				str1=document.myform.stablingtime1.value;
				str2=document.myform.stablingminute1.value;
				str3=document.myform.stablingformat1.value;
			}
			if((document.myform.stablingtime2.value!=="")||(document.myform.stablingminute2.value!=="")||(document.myform.stablingformat2.value!=="")){
				sstr1=document.myform.stablingtime2.value;
				sstr2=document.myform.stablingminute2.value;
				sstr3=document.myform.stablingformat2.value;
			}
//Checking the P.M and A.M
			if(str3=="p.m"){
				if(sstr3=="a.m"){
					alert("Enter valid time duration")
					document.myform.stablingtime1.focus();
					return false;
				}
			}
//Checking the A.M and A.M

			if((str3=="a.m")&&(sstr3=="a.m")){
				if((str1>sstr1)||(str2>sstr2)){
					alert("Enter valid time duration")
					document.myform.stablingtime1.focus();
					return false;
				}
			if((str1==sstr1)||(str2==sstr2)){
				alert("Enter valid time duration")
				document.myform.stablingtime1.focus();
				return false;
			}
		}
	}

//----------------End Date isChecking------------------------

	if(document.myform.todate.value==""){
		alert("Select a End Date in stabling details ");
		document.myform.todate.focus();
		return false;
	}

	if(!(document.myform.todate.value=="")){
		strdate1=document.myform.date1.value;
		mm1 = Number(strdate1.substring(0,2));
		dd1 = Number(strdate1.substring(3,5));
		yyy=(strdate1.substring(6,document.myform.date1.value.length));
		yyy1=(Number(yyy.length));
		
		strdate2=document.myform.todate.value;
		mm2 = Number(strdate2.substring(0,2));
		dd2 = Number(strdate2.substring(3,5));
		yy=(strdate2.substring(6,document.myform.todate.value.length));
		yy1=(Number(yy.length));
		/*alert("startdate:"+strdate1);
		alert("Enddate:"+strdate2);
		alert("syy:"+yyy);
		alert("Eyy:"+yy);*/

		if(yy<yyy){
			alert("Enter valid End Date");
			document.myform.todate.focus();
			return false;
		}
		if((yy==yyy)&&(mm2<mm1)){
			alert("Enter valid End Date");
			document.myform.todate.focus();
			return false;
		}
		if (mm1>12){
			alert("Enter valid End Date");
			document.myform.todate.focus();
			return false;
		}
		if((yy==yyy)&&(mm2==mm1)&&(dd2<dd1)){
			alert("Enter valid End Date");
			document.myform.todate.focus();
			return false;
		}
		else if(dd1>31){
			alert("Enter valid Date");
			document.myform.todate.focus();
			return false;
			}
		}
	}
}

}

//************************ part c Coding complete*********************************

//*************************************** Part D Coding Started *************************
//Type of stabling:
	var choosen6="";
	len7 = document.myform.r9.length ;
	for(i=0; i<len7; i++){
		if(document.myform.r9[i].checked){
			choosen6=document.myform.r9[i].value;
		}
	}
	if(choosen6==""){
		alert("Select the type of Stabling ");
		return false;
	}
	if(choosen6=="typStableOne"){
		if(document.myform.qtyOne.value==""){
			alert("Quantity is empty");
			document.myform.qtyOne.focus();
			return false;
		}
		if(!Number(document.myform.qtyOne.value)){
			alert("Quantity should be a number");
			document.myform.qtyOne.focus();
			return false;
		}
		if((document.myform.qtyOne.value.indexOf('.'))!=-1){
			alert("Quantity should be Integer");
			document.myform.qtyOne.focus();
			return false;
		}
		if(document.myform.qtyOne.value.length>20){
				alert("Quality should be limited by 20 digit");
				document.myform.qtyOne.focus();
				return false;
			}
	}
	if(choosen6=="typStableTwo"){
		if(document.myform.qtyTwo.value==""){
			alert("Quantity is empty");
			document.myform.qtyTwo.focus();
			return false;
		}
		if(!Number(document.myform.qtyTwo.value)){
			alert("Quantity should be a number");
			document.myform.qtyTwo.focus();
			return false;
		}
		if((document.myform.qtyTwo.value.indexOf('.'))!=-1){
			alert("Quantity should be Integer");
			document.myform.qtyTwo.focus();
			return false;
		}
		if(document.myform.qtyTwo.value.length>20){
				alert("Quality should be limited by 20 digit");
				document.myform.qtyTwo.focus();
				return false;
		}
	}
	if(choosen6=="typStableThree"){
		if(document.myform.amountnew.value==""){
			alert("Quantity is empty");
			document.myform.amountnew.focus();
			return false;
		}
		if(!Number(document.myform.amountnew.value)){
			alert("Quantity should be a number");
			document.myform.amountnew.focus();
			return false;
		}
		if(document.myform.amountnew.value.length>20){
				alert("Quality should be limited by 20 digit");
				document.myform.amountnew.focus();
				return false;
		}
		if((document.myform.amountnew.value.indexOf('.'))!=-1){
			alert("Quantity should be Integer");
			document.myform.amountnew.focus();
			return false;
		}
		if(document.myform.timenew.value==""){
			alert("Miles  is empty");
			document.myform.timenew.focus();
			return false;
		}
		if(document.myform.timenew.value.length>20){
				alert("Miles should be limited by 20 digit");
				document.myform.timenew.focus();
				return false;
			}
		if(!Number(document.myform.timenew.value)){
			alert("Miles should be a number");
			document.myform.timenew.focus();
			return false;
		}
		if((document.myform.timenew .value.indexOf('.'))!=-1){
			fstr=document.myform.timenew.value;
			fstr1=document.myform.timenew.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.myform.timenew.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Enter valid Miles ");
				document.myform.timenew.focus();
				return false;
			}
		}
	}
	if(choosen6=="typStableSix"){
		if(document.myform.qTwo1.value==""){
			alert("Stabling mode other type is empty");
			document.myform.qTwo1.focus();
			return false;
		}
		if(Number(document.myform.qTwo1.value)){
			alert("Stabling mode other type should not be a string");
			document.myform.qTwo1.focus();
			return false;
		}
		if((document.myform.qTwo1.value.indexOf('.'))!=-1){
			alert("Stabling mode other type should be Integer");
			document.myform.qTwo1.focus();
			return false;
		}
		if((document.myform.qTwo1.value.indexOf(' '))==0){
			alert("Stabling mode other type is not valid");
			document.myform.qTwo1.focus();
			return false;
		}
		if(document.myform.qTwo1.value.length>255){
			alert("Stabling mode other type should not exceed more than 255 char");
			document.myform.qTwo1.focus();
			return false;
		}
	}
//Veterinarian Info:

	if(document.myform.vetName.value==""){
		alert("Veterinarian Name is empty");
		document.myform.vetName.focus();
		return false;
	}

	if(isnotAlphaNumeric(document.myform.vetName.value)){
		alert("Veterinarian Name is not valid");
		document.myform.vetName.focus();
		return false;
	}
	if((document.myform.vetName.value.indexOf(' '))==0){
		alert("Veterinarian Name is not valid");
		document.myform.vetName.focus();
		return false;
	}
	if(document.myform.vetName.value.length>255){
		alert("Veterinarian Name should not exceed more than 255 char");
		document.myform.vetName.focus();
		return false;
	}

//verifying the phone number

	if(document.myform.phNum.value==""){
		alert("Phone number is empty");
		document.myform.phNum.focus();
		return false;
	}
/*	if(document.myform.phNum.value.length>20){
		alert("Phone number should not exceed more than 20 digit");
		document.myform.phNum.focus();
		return false;
	}*/
	if(isnotInteger1(document.myform.phNum.value)){
		alert("Phone number is not valid");
		document.myform.phNum.focus();
		return false;
	}
	if(!(document.myform.phNum.value=="")){
		str=document.myform.phNum.value;
		s1=str.indexOf('(');
		s2=str.indexOf(')');
		ds1=str.indexOf('+');
		ds2=str.lastIndexOf('+');
		s3=s1+1;
		s4=s2+1;
		if(s3==s2){
			alert("Phone number is not valid");
			document.myform.phNum.focus();
			return false;
		}
		if(s4==s1){
			alert("Phone number is not valid");
			document.myform.phNum.focus();
			return false;
		}
		if(ds1!==ds2){
			alert("Phone number is not valid");
			document.myform.phNum.focus();
			return false;
		}
	}

//vet number checking
	if(document.myform.vetNo.value==""){
		alert("Enter the place where the Veterinarian number will be posted at event");
		document.myform.vetNo.focus();
		return false;
	}
	/*if(Number(document.myform.vetNo.value)){
		alert("Enter valid name in Veterinarian number");
		document.myform.vetNo.focus();
		return false;
	}

	if(isnotAlpha(document.myform.vetNo.value)){
		alert("Enter valid name in Veterinarian number");
		document.myform.vetNo.focus();
		return false;
	}
	if((document.myform.vetNo.value.indexOf(' '))==0){
		alert("Enter Veterinarian number");
		document.myform.vetNo.focus();
		return false;
	}
	if(document.myform.vetNo.value.length>255){
		alert("Veterinarian number should not exceed more than 255 char");
		document.myform.vetNo.focus();
		return false;
	}*/

//To Check Accodomations

	if(!(document.myform.hotel1.value)==""){
		if(isnotAlpha(document.myform.hotel1.value)){
			alert("Hotel name is not valid");
			document.myform.hotel1.focus();
			return false;
		}
		if((document.myform.hotel1.value.indexOf(' '))==0){
			alert("Hotel1 is not valid");
			document.myform.hotel1.focus();
			return false;
		}
		if(document.myform.hotel1.value.length>255){
			alert("Hotel1 should not exceed more than 255 char");
			document.myform.hotel1.focus();
			return false;
		}
		if(document.myform.phone1.value==""){
			alert("Phone Number Field is empty");
			document.myform.phone1.focus();
			return false;
		}
		if(isnotInteger1(document.myform.phone1.value)){
			alert("Phone number is not valid");
			document.myform.phone1.focus();
			return false;
		}
		if(document.myform.phone1.value.length>20){
				alert("Phone should be limited by 20 digit");
				document.myform.phone1.focus();
				return false;
			}
		if(!(document.myform.phone1.value=="")){
			str=document.myform.phone1.value;
			s1=str.indexOf('(');
			s2=str.indexOf(')');
			s3=s1+1;
			if(s3==s2){
				alert("Phone number is not valid");
				document.myform.phone1.focus();
				return false;
			}
		}
		if(document.myform.mile1.value==""){
			alert("Miles Fileld is empty");
			document.myform.mile1.focus();
			return false;
		}
		if(document.myform.mile1.value.length>20){
				alert("Mile should be limited by 20 digit");
				document.myform.mile1.focus();
				return false;
			}
		if(isnotInteger(document.myform.mile1.value)){
			alert("Mile field is not valid");
			document.myform.mile1.focus();
			return false;
		}
		if((document.myform.mile1 .value.indexOf('.'))!=-1){
			fstr=document.myform.mile1.value;
			fstr1=document.myform.mile1.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.myform.mile1.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Miles is not valid ");
				document.myform.mile1.focus();
				return false;
			}
		}
		if(!(document.myform.phone1.value=="")){
			if((document.myform.hotel1.value=="")||(document.myform.mile1.value=="")){
				alert("Hotel and Miles is empty");
				document.myform.hotel1.focus();
				return false;
			}
		}
		if(!(document.myform.mile1.value=="")){
			if((document.myform.hotel1.value=="")||(document.myform.phone1.value=="")){
				alert("Hotel and phone is empty");
				document.myform.hotel1.focus();
				return false;
			}
		}

//hotel checking
		if(!(document.myform.hotel2.value)==""){
			if(isnotAlpha(document.myform.hotel2.value)){
				alert("Hotel name is not valid");
				document.myform.hotel2.focus();
				return false;
			}
			if((document.myform.hotel2.value.indexOf(' '))==0){
				alert("Hotel is not valid");
				document.myform.hotel2.focus();
				return false;
			}
			if(document.myform.hotel2.value.length>255){
			alert("Hotel1 should not exceed more than 255 char");
			document.myform.hotel2.focus();
			return false;
			}
			if(document.myform.phone2.value==""){
				alert("Phone Number Field is empty");
				document.myform.phone2.focus();
				return false;
			}
			if(document.myform.phone2.value.length>20){
				alert("Phone should be limited by 20 digit");
				document.myform.phone2.focus();
				return false;
			}
			if(isnotInteger1(document.myform.phone2.value)){
				alert("Phone number is not valid");
				document.myform.phone2.focus();
				return false;
			}
			if(!(document.myform.phone2.value=="")){
				str=document.myform.phone2.value;
				s1=str.indexOf('(');
				s2=str.indexOf(')');
				s3=s1+1;
				if(s3==s2){
					alert("Phone number is not valid");
					document.myform.phone2.focus();
					return false;
				}
			}
			if(document.myform.mile2.value==""){
				alert("Miles Fileld is empty");
				document.myform.mile2.focus();
				return false;
			}
			if(document.myform.mile2.value.length>20){
				alert("Mile should be limited by 20 digit");
				document.myform.mile2.focus();
				return false;
			}
			if(isnotInteger(document.myform.mile2.value)){
				alert("Mile field is not valid");
				document.myform.mile2.focus();
				return false;
			}
			if((document.myform.mile2.value.indexOf('.'))!=-1){
				fstr=document.myform.mile2.value;
				fstr1=document.myform.mile2.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.mile2.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Mile is not valid ");
					document.myform.mile2.focus();
					return false;
				}
			}
		}

		if(!(document.myform.phone2.value=="")){
			if((document.myform.hotel2.value=="")||(document.myform.mile2.value=="")){
				alert("Hotel and Miles is empty");
				document.myform.hotel2.focus();
				return false;
			}
		}
		if(!(document.myform.mile2.value=="")){
			if((document.myform.hotel2.value=="")||(document.myform.phone2.value=="")){
				alert("Hotel and phone is empty");
				document.myform.hotel2.focus();
				return false;
			}
		}

//hotel checking

		if(!(document.myform.hotel3.value)==""){
			if(isnotAlpha(document.myform.hotel3.value)){
				alert("Hotel name is not valid");
				document.myform.hotel3.focus();
				return false;
			}
			if((document.myform.hotel3.value.indexOf(' '))==0){
				alert("Hotel is not valid");
				document.myform.hotel3.focus();
				return false;
			}
			if(document.myform.hotel3.value.length>255){
			alert("Hotel1 should not exceed more than 255 char");
			document.myform.hotel3.focus();
			return false;
			}
			if(document.myform.phone3.value==""){
				alert("Phone Number Field is empty");
				document.myform.phone3.focus();
				return false;
			}
			if(document.myform.phone3.value.length>20){
				alert("Phone should be limited by 20 digit");
				document.myform.phone3.focus();
				return false;
			}
			if(isnotInteger1(document.myform.phone3.value)){
				alert("Phone number is not valid");
				document.myform.phone3.focus();
				return false;
			}
			if(!(document.myform.phone3.value=="")){
				str=document.myform.phone3.value;
				s1=str.indexOf('(');
				s2=str.indexOf(')');
				s3=s1+1;
				if(s3==s2){
					alert("Phone number is not valid");
					document.myform.phone3.focus();
					return false;
				}
			}
			if(document.myform.mile3.value==""){
				alert("Miles Fileld is empty");
				document.myform.mile3.focus();
				return false;
			}
			if(document.myform.mile3.value.length>20){
				alert("Mile should be limited by 20 digit");
				document.myform.mile3.focus();
				return false;
			}
			if(isnotInteger(document.myform.mile3.value)){
				alert("Mile field is not valid");
				document.myform.mile3.focus();
				return false;
			}
			if((document.myform.mile3.value.indexOf('.'))!=-1){
				fstr=document.myform.mile3.value;
				fstr1=document.myform.mile3.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.mile3.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Mile is not valid ");
					document.myform.mile3.focus();
					return false;
				}
			}
		}
		if(!(document.myform.phone3.value=="")){
			if((document.myform.hotel3.value=="")||(document.myform.mile3.value=="")){
				alert("Hotel and Miles is empty");
				document.myform.hotel3.focus();
				return false;
			}
		}
		if(!(document.myform.mile3.value=="")){
			if((document.myform.hotel3.value=="")||(document.myform.phone3.value=="")){
				alert("Hotel and phone is empty");
				document.myform.hotel3.focus();
				return false;
			}
		}
	}
//******************************part D coding completed*********************************8

//******************************** Part E coding Started *********************************
//for Advanced Level

num3 = document.myform.dtct.value;
flag2 = true;
for(i=0;i<num3;i++)
{   chkname3="advlevel"+i;
    if(document.getElementById(''+chkname3).checked)
	{flag2=false;}}
 if(flag2)
 {alert("Select any of the options in Advanced Level in Part E");
  return false;}
  
  
//Footing
		if(!(document.myform.footing.value=="")){
			if((document.myform.footing.value.length>255)){
				alert("Footing should not exceed more than 255 char");
				document.myform.footing.focus();
				return false;
			}
			if((document.myform.footing.value.indexOf(' '))==0){
				alert("Footing is not valid");
				document.myform.footing.focus();
				return false;
			}
		}
//Cross Country division
		if(document.myform.division1.value==""){
			alert("Cross Country division is empty");
			document.myform.division1.focus();
			return false;
		}

		if(!(document.myform.division1.value=="")){
			if(isnotAlpha(document.myform.division1.value)){
				alert("Division is not valid");
				document.myform.division1.focus();
				return false;
			}
			if((document.myform.division1.value.length>255)){
				alert("Division is limited be 255 char");
				document.myform.division1.focus();
				return false;
			}
			if((document.myform.division1.value.indexOf(' '))==0){
				alert("Division is not valid");
				document.myform.division1.focus();
				return false;
			}
			if((document.myform.length1.value=="")){
				alert("Length is empty");
				document.myform.length1.focus();
				return false;
			}
			
			if(!(document.myform.length1.value=="")){
				if(!Number(document.myform.length1.value)){
					alert(" Length Should be a number");
					document.myform.length1.focus();
					return false;
				}
			if(document.myform.length1.value.length>20){
					alert("Length should be limited by 20 digit");
					document.myform.length1.focus();
					return false;
				}
			if((document.myform.length1.value.indexOf('.'))!=-1){
				fstr=document.myform.length1.value;
				fstr1=document.myform.length1.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.length1.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Length is not valid ");
					document.myform.length1.focus();
					return false;
				}
			}
			if((document.myform.speed1.value=="")){
				alert("Speed is empty");
				document.myform.speed1.focus();
				return false;
			}
			
			if(!(document.myform.speed1.value=="")){
				if(!Number(document.myform.speed1.value)){
					alert(" Speed Should be a number");
					document.myform.speed1.focus();
					return false;
				}
			if(document.myform.speed1.value.length>20){
					alert("Speed should be limited by 20 digit");
					document.myform.speed1.focus();
					return false;
				}
			}

			if((document.myform.speed1.value.indexOf('.'))!=-1){
				alert("Speed is not valid ");
				document.myform.speed1.focus();
				return false;
			}
		}

	}

	if(!(document.myform.length1.value=="")){
		if((document.myform.division1.value=="")||(document.myform.speed1.value=="")){
			alert("Division and Speed is empty");
			document.myform.division1.focus();
			return false;
		}
	}
	if(!(document.myform.speed1.value=="")){
		if((document.myform.division1.value=="")||(document.myform.length1.value=="")){
			alert("Division and Length is empty");
			document.myform.division1.focus();
			return false;
		}
	}

//division2

	if(!(document.myform.division2.value=="")){
		if(isnotAlpha(document.myform.division2.value)){
			alert("Division is not valid");
			document.myform.division2.focus();
			return false;
		}
		if((document.myform.division2.value.indexOf(' '))==0){
			alert("Division is not valid");
			document.myform.division2.focus();
			return false;
		}
		if((document.myform.division2.value.length>255)){
			alert("Division should not exceed more than 255 char");
			document.myform.division1.focus();
			return false;
		}
		if((document.myform.length2.value=="")){
			alert("Length is empty");
			document.myform.length2.focus();
			return false;
		}
		if(!(document.myform.length2.value=="")){
			if(!Number(document.myform.length2.value)){
				alert(" Length Should be a number");
				document.myform.length2.focus();
				return false;
			}
		if(document.myform.length2.value.length>20){
				alert("Length should be limited by 20 digit");
				document.myform.length2.focus();
				return false;
			}
			if((document.myform.length2.value.indexOf('.'))!=-1){
				fstr=document.myform.length2.value;
				fstr1=document.myform.length2.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.length2.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Length is not valid ");
					document.myform.length2.focus();
					return false;
				}
			}
			if((document.myform.speed2.value=="")){
				alert("Speed is empty");
				document.myform.speed2.focus();
				return false;
			}
			if(!(document.myform.speed2.value=="")){
				if(!Number(document.myform.speed2.value)){
					alert(" Speed Should be a number");
					document.myform.speed2.focus();
					return false;
				}
			if(document.myform.speed2.value.length>20){
					alert("Speed should be limited by 20 digit");
					document.myform.speed2.focus();
					return false;
				}
			}
			if((document.myform.speed2.value.indexOf('.'))!=-1){
				alert("Speed is not valid ");
				document.myform.speed2.focus();
				return false;
			}
		}
	}
	if(!(document.myform.length2.value=="")){
		if((document.myform.division2.value=="")||(document.myform.speed2.value=="")){
			alert("Division and Speed is empty");
			document.myform.division2.focus();
			return false;
		}
	}
	if(!(document.myform.speed2.value=="")){
		if((document.myform.division2.value=="")||(document.myform.length2.value=="")){
			alert("Division and Length is empty");
			document.myform.division2.focus();
			return false;
		}
	}
//division3

	if(!(document.myform.division3.value=="")){
		if(isnotAlpha(document.myform.division3.value)){
			alert("Division is not valid");
			document.myform.division3.focus();
			return false;
		}
		if((document.myform.division3.value.indexOf(' '))==0){
			alert("Division is not valid");
			document.myform.division3.focus();
			return false;
		}
		if((document.myform.division3.value.length>255)){
			alert("Division should not exceed more than 255 char");
			document.myform.division3.focus();
			return false;
		}
		if((document.myform.length3.value=="")){
			alert("Length is empty");
			document.myform.length3.focus();
			return false;
		}
		if(!(document.myform.length3.value=="")){
			if(!Number(document.myform.length3.value)){
				alert(" Length Should be a number");
				document.myform.length3.focus();
				return false;
			}
		if(document.myform.length3.value.length>20){
				alert("Length should be limited by 20 digit");
				document.myform.length3.focus();
				return false;
			}
			if((document.myform.length3.value.indexOf('.'))!=-1){
				fstr=document.myform.length3.value;
				fstr1=document.myform.length3.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.myform.length3.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Length is not valid ");
					document.myform.length3.focus();
					return false;
				}
			}
		if((document.myform.speed3.value=="")){
			alert("Speed is empty");
			document.myform.speed3.focus();
			return false;
		}
		if(!(document.myform.speed3.value=="")){
			if(!Number(document.myform.speed3.value)){
				alert(" Speed Should be a number");
				document.myform.speed3.focus();
				return false;
			}
		if(document.myform.speed3.value.length>20){
				alert("Speed should be limited by 20 digit");
				document.myform.speed3.focus();
				return false;
			}
		}
		if((document.myform.speed3.value.indexOf('.'))!=-1){
			alert("Speed is not valid ");
			document.myform.speed3.focus();
			return false;
		}
	}
}
if(!(document.myform.length3.value=="")){
	if((document.myform.division3.value=="")||(document.myform.speed3.value=="")){
		alert("Division and Speed is empty");
		document.myform.division3.focus();
		return false;
	}
}
if(!(document.myform.speed3.value=="")){
	if((document.myform.division3.value=="")||(document.myform.length3.value=="")){
		alert("Division and Length is empty");
		document.myform.division3.focus();
		return false;
	}
}

//courses checking
	var choosen11="";
	len11 = document.myform.r10.length ;
	for(i=0; i<len11; i++){
		if(document.myform.r10[i].checked){
			choosen11=document.myform.r10[i].value;
		}
	}
	if(choosen11==""){
		alert("Select the description for your Course");
		return false;
	}
	if((document.myform.CrossCountryDate.value=="")){
		alert("Cross-Country-Course is empty");
		document.myform.CrossCountryDate.focus();
		return false;
	}
	if(!(document.myform.CrossCountryDate.value=="")){
		if(nowDate<10){
			nowDate="0"+nowDate;
		}

		if(nowMonth<10){
			nowMonth="0"+nowMonth;
		}

		if(!(document.myform.CrossCountryDate.value=="")){
			strdate=document.myform.CrossCountryDate.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.CrossCountryDate.value.length));
			yyyy1=(Number(yyyy.length));

			if(yyyy>2100){
				alert("Enter valid Date ");
				document.myform.CrossCountryDate.focus();
				return false;
			}
	
			if(mm>12){
				alert("Enter valid Date ");
				document.myform.CrossCountryDate.focus();
				return false;
			}

			if(dd>31){
				alert("Enter valid date ");
				document.myform.CrossCountryDate.focus();
				return false;
			}

			if(yyyy1>4){
				alert("Year is not valid");
				document.myform.CrossCountryDate.focus();
				return false;
			}
			if((document.myform.CrossCountryDate.value.indexOf('\\'))!=-1||(document.myform.CrossCountryDate.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.CrossCountryDate.focus();
				return false;
			}
		}
	}

//Entry Limit

	if((!(document.myform.RiderLimit1.value==""))&&(!(document.myform.RiderLimit2.value==""))){
		alert("Both should not be filled");
		document.myform.RiderLimit1.focus();
		return false;
	}

	/*if(!(document.myform.EntryLimit.value=="")){
		if(isnotAlpha(document.myform.EntryLimit.value)){
			alert("EntryLimit is not Valid");
			document.myform.EntryLimit.focus();
			return false;
		}
		if(document.myform.EntryLimit.value.length>30){
			alert("Entrylimit should not exceed more than 30 char");
			document.myform.EntryLimit.focus();
			return false;
		}
	}*/

//Rider limit 1 and Rider limit2

	/*if(!(document.myform.RiderLimit1.value=="")){
		if(isnotAlpha(document.myform.RiderLimit1.value)){
			alert("RiderLimit is not Valid");
			document.myform.RiderLimit1.focus();
			return false;
		}
		if((document.myform.RiderLimit1.value.indexOf(' '))==0){
			alert("RiderLimit is not valid");
			document.myform.RiderLimit1.focus();
			return false;
		}
		if(document.myform.RiderLimit1.value.length>30){
			alert("RiderLimit should not exceed more than 30 char");
			document.myform.RiderLimit1.focus();
			return false;
		}
	}*/

//Rider Limit2

	/*if(!(document.myform.RiderLimit2.value=="")){
		if(isnotAlpha(document.myform.RiderLimit2.value)){
			alert("RiderLimit is not Valid");
			document.myform.RiderLimit2.focus();
			return false;
		}
		if(document.myform.RiderLimit2.value.length>30){
			alert("RiderLimit should not exceed more than 30 char");
			document.myform.EntryLimit2.focus();
			return false;
		}
		if((document.myform.RiderLimit2.value.indexOf(' '))==0){
			alert("RiderLimit is not valid");
			document.myform.RiderLimit2.focus();
			return false;
		}
	}
*/
//--------------------Birth Checking------------------

	if(!(document.myform.BirthDate.value=="")){
		var todayDate=new Date();
		var nowDate=todayDate.getDate();
		var nowYear=todayDate.getYear();
		var nMonth1=todayDate.getMonth();
		var nowMonth=1+nMonth1;
		if(nowDate<10){
			nowDate="0"+nowDate;
		}

		if(nowMonth<10){
			nowMonth="0"+nowMonth;
		}

		if(!(document.myform.BirthDate.value=="")){
			strdate=document.myform.BirthDate.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.BirthDate.value.length));
			yyyy1=(Number(yyyy.length));
	
			if((yyyy>nowYear)||(yyyy<1900)){
				alert("Enter valid Date ");
				document.myform.BirthDate.focus();
				return false;
			}

			if((mm>nowMonth)&&(yyyy==nowYear)){
				alert("Enter valid Date");
				document.myform.BirthDate.focus();
				return false;
			}

			else if (mm>12){
				alert("Enter valid date ");
				document.myform.BirthDate.focus();
				return false;
			}

			if((dd>nowDate)&&(mm==nowMonth)){
				alert("Enter valid date ");
				document.myform.BirthDate.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter valid date ");
				document.myform.BirthDate.focus();
				return false;
			}
			if(yyyy1>4){
				alert("year is not valid");
				document.myform.BirthDate.focus();
				return false;
			}
			if((document.myform.BirthDate.value.indexOf('\\'))!=-1||(document.myform.BirthDate.value.indexOf('-'))!=-1){
				alert("Date is not valid");
				document.myform.BirthDate.focus();
				return false;
			}
		}
	}

	var choosen12="";
	len12 = document.myform.r11.length ;
	for(i=0; i<len12; i++){
		if(document.myform.r11[i].checked){
			choosen12=document.myform.r11[i].value;
		}
	}

//Per head
	if((!(document.myform.PerHead.value==""))&&(!(document.myform.TeamTotal.value==""))){
		alert("Both PerTeamMember-TeamTotal is not to be entered");
		document.myform.PerHead.focus();
		return false;
	}

	if(!(document.myform.PerHead.value=="")){
		if(!Number(document.myform.PerHead.value)){
			alert(" PerHead Name Should be a number");
			document.myform.PerHead.focus();
			return false;
		}
	if(document.myform.PerHead.value>9999.99){
		alert(" PerHead Should not exceed more than $9999.99");
		document.myform.PerHead.focus();
		return false;
	}
	
	if((document.myform.PerHead.value.indexOf('.'))!=-1){
		fstr=document.myform.PerHead.value;
		fstr1=document.myform.PerHead.value.indexOf('.');
		mm = (fstr.substring(fstr1,document.myform.PerHead.value.length));
		str=(Number(mm.length));
		if((str)>3){
			alert("PerHead value is not valid ");
			document.myform.PerHead.focus();
			return false;
		}
	}
}
else if (!(document.myform.TeamTotal.value=="")){
	if (isnotAlpha(document.myform.TeamTotal.value)){
		alert(" TeamTotal Name is not valid");
		document.myform.TeamTotal.focus();
		return false;
	}
	if(document.myform.TeamTotal.value.length>30){
			alert("TeamTotal should not exceed more than 30 char");
			document.myform.TeamTotal.focus();
			return false;
		}
}
//Party code

if(!(document.myform.Party.value=="")){
	if (Number(document.myform.Party.value)){
		alert(" Party Name Should not be a number");
		document.myform.Party.focus();
		return false;
	}
if(document.myform.Party.value.length>30){
	alert(" Party Name Should Limited be 30 char");
	document.myform.Party.focus();
	return false;
}

if(isnotAlpha(document.myform.Party.value)){
	alert(" Enter valid Party name");
	document.myform.Party.focus();
	return false;
}
if((document.myform.Party.value.indexOf(' '))==0){
	alert("Party is not valid");
	document.myform.Party.focus();
	return false;
}
if(document.myform.Party.value.length > 80){
			alert("Party Name should not exceed more than 80 char");
			document.myform.RiderLimit2.focus();
			return false;
		}

}
return true;
}