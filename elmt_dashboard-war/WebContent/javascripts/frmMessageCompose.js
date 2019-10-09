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

//======================== Functions Used =============================

function isnotSpecial(str){
	stringSpecialCheck="!#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'";
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

function isMessage(str){
	message="abcdefghijklmnopqrstuvwxyz0123456789!#$%^&*()+|<>?/=-~,;:][{}"+"\\"+"\'";
	fzip=1;
	for(j=0;j<str.length;j++){
		if(message.indexOf(str.charAt(j))!=-1){
			fzip=0;
		}
	}
	if(fzip==0){
		return false;
	}
	else{
		return true;
	}
}

function composeOnCheck(){

//======================== For To Address =============================

	if(document.frmMsgCompose.txtEmail.value==""){
		alert("To Address cannot be Empty");
		document.frmMsgCompose.txtEmail.focus();
		return false;
	}
	
	if(document.frmMsgCompose.txtEmail.value.indexOf(' ')==0){
		alert("Enter valid Email Address");
		document.frmMsgCompose.txtEmail.focus();
		return false;
	}
	
	if(!(document.frmMsgCompose.txtEmail.value== "")){
		strmail1=document.frmMsgCompose.txtEmail.value;
		firstat1 = strmail1.indexOf("@");
		lastat1 = strmail1.lastIndexOf("@");
		strmain1=strmail1.substring(0,firstat1);
		strsub1=strmail1.substring(firstat1,document.frmMsgCompose.txtEmail.value.length);
		if(strmail1.length>120){
			alert("To Address is out of range.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		if(strmain1.indexOf('  ')!=-1 || firstat1==0 || strsub1.indexOf('  ')!=-1 ){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		if(isnotSpecial(strmain1) || isnotSpecial(strsub1)){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		k1=0;
		strlen1=strsub1.length;
		for(i1=0;i1<strlen1-1;i1++){
			if(strsub1.charAt(i1)=='.'){
				k1=k1+1;
			}
		}
		if(k1>2){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		if(firstat1==-1 || lastat1==-1){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		if(Number(strmain1)){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		if(firstat1 != lastat1){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
		firstdot1=strmail1.indexOf(".",firstat1);
		lastdot1=strmail1.lastIndexOf(".");
		if(firstdot1 == -1 || lastdot1 == -1 || lastdot1==strmail1.length-1){
			alert("Enter valid To Address.");
			document.frmMsgCompose.txtEmail.focus();
			return false;
		}
	}

//============================== For Subject ==================================

	if(!(document.frmMsgCompose.txtSubject.value=="")){
		if(document.frmMsgCompose.txtSubject.value.length>255){
			alert("Entered Subject Field is out of range.");
			document.frmMsgCompose.txtSubject.focus();
			return false;
		}
		if(isMessage(document.frmMsgCompose.txtSubject.value)){
			alert("Enter valid Subject");
			document.frmMsgCompose.txtSubject.focus();
			return false;
		}
		if(document.frmMsgCompose.txtSubject.value.indexOf(' ')==0){
			alert("Enter valid Subject");
			document.frmMsgCompose.txtSubject.focus();
			return false;
		}
	}
//============================ For Message ====================================

	if(!(document.frmMsgCompose.txtMessage.value=="")){
		if(isMessage(document.frmMsgCompose.txtMessage.value)){
			alert("Enter valid Message");
			document.frmMsgCompose.txtMessage.focus();
			return false;
		}
		if(document.frmMsgCompose.txtMessage.value.indexOf(' ')==0){
			alert("Enter valid Message");
			document.frmMsgCompose.txtMessage.focus();
			return false;
		}
	}
	var path = document.frmMsgCompose.attachFile.value;
	if(path != ""){
		if(navigator.platform == "Win32" || navigator.platform == "Windows" ){		
			if((path.charAt(0) != "\\" && path.charAt(1) != "\\") && (path.charAt(0) != "/" && path.charAt(1) != "/")){
				
					alert("Enter Valid File location");
					
				}
				if(path.charAt(1) == "" ||!path.charAt(1).match(/^[:]/) || !path.charAt(2).match(/^[\/\\]/)){
					alert("Enter Valid File location");
					return false;
				}
			}
		}
		else if(navigator.platform == "Linux"){
			if(path.charAt(0) != "/"){
				alert("Enter Valid File location");
				return false;
			}
			if(path.charAt(0) == "/" && path.charAt(1) == "/"){
				alert("Enter Valid File location");
				return false;
			}
		}
	}
	return true;
}
