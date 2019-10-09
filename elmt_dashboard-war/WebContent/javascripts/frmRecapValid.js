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
//---------------------------------------------------------------------------------------
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
//-----------------------------------------------------------------------------------------------
//--------------------------function for two decimal points
function isnotValidDecimal(str){
if((str.indexOf("."))!=-1)
{ fr1=str.indexOf('.');
  mm = (str.substring(fr1,str.length));
  strnum=(Number(mm.length));
  if(strnum>3)
  {return true;}else {return false;}}
}
//------------------------------------------------------------------------------------------

function myvalidate(){
	
	if(document.frmMeeEduActRecap.selActivityId.selectedIndex==0)
	{ alert("Select a Name of Activity");
	  document.frmMeeEduActRecap.selActivityId.focus();
	  return false;  }
//---------------------------------------------------------------------------------------------
//-----------------------------for Data Change Radio Buttons------------------------------
	chosen1="";
	len1 = document.frmMeeEduActRecap.radio1.length ;
	for(i1=0;i1<len1;i1++){
		if(document.frmMeeEduActRecap.radio1[i1].checked){
			chosen1= document.frmMeeEduActRecap.radio1[i1].value;
		}
	}

	if(chosen1==""){
		alert("Check any of the Option for Data Change.");
		return false;
	}
//--------------------------------------------------------------------------------------------
//--------------------------------for integer check  Activity  Information--------
//-------------------------------------------for Total Number of riders Participating---------
       if(document.frmMeeEduActRecap.ridernum.value==""){
			alert("Enter a Total Number of riders Participating");
			document.frmMeeEduActRecap.ridernum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.ridernum.value)){
			alert("Enter a valid Total Number of riders Participating");
			document.frmMeeEduActRecap.ridernum.focus();
			return false;
		}
		
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of Instructors---------
		if(document.frmMeeEduActRecap.instnum.value==""){
			alert("Enter a Total Number of Instructors");
			document.frmMeeEduActRecap.instnum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.instnum.value)){
			alert("Enter a valid Total Number of Instructors");
			document.frmMeeEduActRecap.instnum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of Current HLC members---------
if(document.frmMeeEduActRecap.uesanum.value==""){
			alert("Enter a Total Number of Current HLC members");
			document.frmMeeEduActRecap.uesanum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.uesanum.value)){
			alert("Enter a valid Total Number of Current HLC members");
			document.frmMeeEduActRecap.uesanum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of new HLC Members---------
if(document.frmMeeEduActRecap.newhlcnum.value==""){
			alert("Enter a Total Number of new HLC members");
			document.frmMeeEduActRecap.newhlcnum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.newhlcnum.value)){
			alert("Enter a valid Total Number of new HLC members");
			document.frmMeeEduActRecap.newhlcnum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of renewing HLC Numbers---------
if(document.frmMeeEduActRecap.renewhlcnum.value==""){
			alert("Enter a Total Number of renewing HLC members");
			document.frmMeeEduActRecap.renewhlcnum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.renewhlcnum.value)){
			alert("Enter a valid Total Number of renewing HLC members");
			document.frmMeeEduActRecap.renewhlcnum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of riders Participating---------
if(document.frmMeeEduActRecap.hlcfullnum.value==""){
			alert("Enter a Number of HLC individual full members");
			document.frmMeeEduActRecap.hlcfullnum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.hlcfullnum.value)){
			alert("Enter a valid Number of HLC individual full members");
			document.frmMeeEduActRecap.hlcfullnum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of riders Participating---------
if(document.frmMeeEduActRecap.hlcjunum.value==""){
			alert("Enter a Number of HLC junior members");
			document.frmMeeEduActRecap.hlcjunum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.hlcjunum.value)){
			alert("Enter a valid Number of HLC junior members");
			document.frmMeeEduActRecap.hlcjunum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of riders Participating---------
if(document.frmMeeEduActRecap.noncomnum.value==""){
			alert("Enter a Number of HLC noncompeting members");
			document.frmMeeEduActRecap.noncomnum.focus();
			return false;
		}

		if(isnotInteger(document.frmMeeEduActRecap.noncomnum.value)){
			alert("Enter a valid Number of HLC noncompeting members");
			document.frmMeeEduActRecap.noncomnum.focus();
			return false;
		}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
//-------------------------------------------for Total Number of riders Participating---------
		if(document.frmMeeEduActRecap.totamt.value==""){
			alert("Enter a Total Amount ");
			document.frmMeeEduActRecap.totamt.focus();
			return false;
		}

		if(!Number(document.frmMeeEduActRecap.totamt.value)){
			alert("Enter a valid Total Amount");
			document.frmMeeEduActRecap.totamt.focus();
			return false;
		}
		if(isnotValidDecimal(document.frmMeeEduActRecap.totamt.value)){
			alert("Enter a valid Total Amount");
			document.frmMeeEduActRecap.totamt.focus();
			return false;
		}
	
	if(document.frmMeeEduActRecap.totamt.value.indexOf('.')>7){
		alert("Enter a valid Total Amount");
		document.frmMeeEduActRecap.totamt.focus();
		return false;
	}
	if((document.frmMeeEduActRecap.totamt.value.indexOf('.'))==-1){
		if(document.frmMeeEduActRecap.totamt.value.length>7){
			alert("Enter a valid Total Amount");
			document.frmMeeEduActRecap.totamt.focus();
			return false;
		}
	}
		
	if(!(document.frmMeeEduActRecap.totamt.value=="")){
		if((document.frmMeeEduActRecap.totamt.value.indexOf('.'))!=-1){
			fstr=document.frmMeeEduActRecap.totamt.value;
			fstr1=document.frmMeeEduActRecap.totamt.value.lastIndexOf('.');
			mm = (fstr.substring(fstr1,document.frmMeeEduActRecap.totamt.value.length));
			str=(Number(mm.length));
			if((str)>3){
				alert("Enter a valid Total Amount");
				document.frmMeeEduActRecap.totamt.focus();
				return false;
			}
		}
	}
		
	
		if(document.frmMeeEduActRecap.comments.value==""){
			alert("Enter a comments");
			document.frmMeeEduActRecap.comments.focus();
			return false;
		}
		if(document.frmMeeEduActRecap.comments.value.indexOf(' ')==0){
		alert("Please avoid  unwanted white space");
		document.frmMeeEduActRecap.comments.focus();
		return false;
			}
		if(document.frmMeeEduActRecap.comments.value.length>1024){
			alert("Comments is out of range");
			document.frmMeeEduActRecap.comments.focus();
			return false;
		}
		if(document.frmMeeEduActRecap.comments.value.indexOf('  ')!==-1){
		alert("Please avoid  unwanted white space");
		document.frmMeeEduActRecap.comments.focus();
		return false;
	}
	chosen2="";
	len2 = document.frmMeeEduActRecap.radio2.length ;
	for(i2=0;i2<len1;i2++){
		if(document.frmMeeEduActRecap.radio2[i2].checked){
			chosen2= document.frmMeeEduActRecap.radio2[i2].value;
		}
	}

	if(chosen2==""){
		alert("Check any of the Option for Publishing all or part of your Comments.");
		return false;
	}

	
	if(document.frmMeeEduActRecap.suggestions.value==""){
			alert("Enter a suggestions");
			document.frmMeeEduActRecap.suggestions.focus();
			return false;
		}
	if(document.frmMeeEduActRecap.suggestions.value.indexOf(' ')==0){
		alert("Please avoid  unwanted white space");
		document.frmMeeEduActRecap.suggestions.focus();
		return false;
	}
		if(document.frmMeeEduActRecap.suggestions.value.length>1024){
			alert("suggestions is out of range");
			document.frmMeeEduActRecap.suggestions.focus();
			return false;
		}
		if(document.frmMeeEduActRecap.suggestions.value.indexOf('  ')!==-1){
		alert("Please avoid  unwanted white space");
		document.frmMeeEduActRecap.suggestions.focus();
		return false;
	}
	
//----------------------------------------------------------------------------------------------------
	
	return true;
	
}
	
