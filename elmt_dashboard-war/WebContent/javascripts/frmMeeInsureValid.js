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
//-----------------------function for pure Integer Numbers--------------------------------
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
//----------------------------------------------------------------------------------------------------
//--------------------------------function for Valid Email
function isnotVlaidEmail(str){
			 strmail=str;
			 firstat = strmail.indexOf("@");
			 lastat = strmail.lastIndexOf("@");
			 strmain=strmail.substring(0,firstat);
			 strsub=strmail.substring(firstat,str.length);
			 flag=false;
			 if(strmail.length>120)
			 { flag=true;alert("1");}
			 if(strmain.indexOf('  ')!=-1 || firstat==0 || firstat!=lastat || strsub.indexOf(' ')!=-1 )
			 {flag=true;}

			stringMailCheck1="!#$%^&*()_+|<>?/=-~,;:][{}"+"\\"+"\'"+"\"";
			f3=1;
			for(j=0;j<strsub.length;j++)
			{if(stringMailCheck1.indexOf(strsub.charAt(j))!=-1)
			{f3=0;}}
			if(f3==0)
			{flag=true;}

			stringMailCheck2="!@#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'"+"\"";
			f4=1;
			for(j=0;j<strmain.length;j++)
			{if(stringMailCheck2.indexOf(strmain.charAt(j))!=-1)
			{f4=0;}}
			if(f4==0)
			{flag=true;}
			
			 k=0;
			 strlen=strsub.length;
			 for(i=0;i<strlen-1;i++)
			 { if(strsub.charAt(i)=='.')
			 {k=k+1;}}
			 if(k>2)
			 { flag=true;}
			 if(firstat==-1 || lastat==-1)
			 {flag=true;}
			 if(Number(strmain))
			 {flag=true;}
			 if(firstat != lastat )
			 {flag=true;}
			 firstdot=strmail.indexOf(".",firstat);
			 lastdot=strmail.lastIndexOf(".");
			 if(firstdot == -1 || lastdot == -1 || lastdot==strmail.length-1)
			 {flag=true;}
			
			return flag;
}
//-----------------------------------------------------------------------------------------------

//================================For txtclear==========================

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

//==================================== For Acceptance of Terms ==============================

	if(document.frmMeeInsureEduAct.chkAccept.checked==false){
		alert("Please accept the Terms and Conditions.");
		document.frmMeeInsureEduAct.chkAccept.focus();
		return false;
	}

	
//====================================For Email check======================================

	if(document.frmMeeInsureEduAct.txtemail.value==""){
		alert("Enter the Email.");
		document.frmMeeInsureEduAct.txtemail.focus();
		return false;
	}

	if(isnotVlaidEmail(document.frmMeeInsureEduAct.txtemail.value)){
		alert("Enter the valid Email.");
		document.frmMeeInsureEduAct.txtemail.focus();
		return false;
	}
	
//==================================For Number of Horse=================================


	if(document.frmMeeInsureEduAct.horseNo.value==""){
		alert("Enter the Number of Horse");
		document.frmMeeInsureEduAct.horseNo.focus();
		return false;
	}
	if(!(document.frmMeeInsureEduAct.horseNo.value=="")){
		if(!Number(document.frmMeeInsureEduAct.horseNo.value)){
		alert("Enter the valid Number of Horse");
		document.frmMeeInsureEduAct.horseNo.focus();
		return false;
		}

		if(isnotInteger(document.frmMeeInsureEduAct.horseNo.value)){
		alert("Enter the valid Number of Horse");
		document.frmMeeInsureEduAct.horseNo.focus();
		return false;
		}
}
//===================================For selection option============================

if(document.frmMeeInsureEduAct.level_sel.value==""){
		alert("Select the level of Riding");
		document.frmMeeInsureEduAct.level_sel.focus();
		return false;
	}	
//===================================For Radio check===================================

	chosen3="";
	len3 = document.frmMeeInsureEduAct.radioyes.length ;
	
	for(i3=0;i3<len3;i3++){
		if(document.frmMeeInsureEduAct.radioyes[i3].checked){
			chosen3= document.frmMeeInsureEduAct.radioyes[i3].value;
		}
	}

	
	
//======================================for txtfield====================================

	if((document.frmMeeInsureEduAct.memberShipNo.value=="")&&(chosen3=="yes")){
		alert("Membership Number is Empty");
		document.frmMeeInsureEduAct.memberShipNo.focus();
		return false;
	}	
	if(isAlpha(document.frmMeeInsureEduAct.memberShipNo.value)){
		alert("Membership Number is not valid");
		document.frmMeeInsureEduAct.memberShipNo.focus();
		return false;
	}
	if((document.frmMeeInsureEduAct.memberShipNo.value.indexOf('  ')!=-1)||(document.frmMeeInsureEduAct.memberShipNo.value.indexOf(' ')==0)){
		alert("Membership Number is not valid");
		document.frmMeeInsureEduAct.memberShipNo.focus();
		return false;
	}
	if(!(document.frmMeeInsureEduAct.memberShipNo.value=="")&&(chosen3=="no")){
		document.frmMeeInsureEduAct.memberShipNo.value="";
		return false;
	}	

return true;
}

function isRadio(){
	
//==================================== For Radio Button ==============================	
	chosen4="";
	len4 = document.frmMeeInsureEduAct.radioyes.length ;
	
	for(i4=0;i4<len4;i4++){
		if(document.frmMeeInsureEduAct.radioyes[i4].checked){
			chosen4= document.frmMeeInsureEduAct.radioyes[i4].value;
			
		}
	}

	if(chosen4=="yes"){
		document.frmMeeInsureEduAct.memberShipNo.disabled=false;
		
	}
	if(chosen4=="no"){
	document.frmMeeInsureEduAct.memberShipNo.disabled="disabled";	
	document.frmMeeInsureEduAct.memberShipNo.value="";	
	}	
	
		 
}
//===============================End==========================================================

