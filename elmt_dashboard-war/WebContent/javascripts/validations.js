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

function check(){
		if(document.myform.hlcstaffname.value=="" || document.myform.hlcstaffname.value.indexOf(' ')==0){
			alert("Enter the User Name");
			document.myform.hlcstaffname.focus();
			return false;
			}
		if(document.myform.address.value=="" || document.myform.address.value.indexOf(' ')==0){
			alert("Enter the address");
			document.myform.address.focus();
			return false;
			}
		if(document.myform.city.value=="" || document.myform.city.value.indexOf(' ')==0){
			alert("Enter the city");
			document.myform.city.focus();
			return false;
			}
		if(document.myform.phone.value=="" || document.myform.phone.value.indexOf(' ')==0){
			alert("Enter the Telephone Number");
			document.myform.phone.focus();
			return false;
			}
		if(document.myform.fax.value=="" || document.myform.fax.value.indexOf(' ')==0){
			alert("Enter the fax");
			document.myform.fax.focus();
			return false;
			}
		if(document.myform.email.value=="" || document.myform.email.value.indexOf(' ')==0){
			alert("Enter the email");
			document.myform.email.focus();
			return false;
			}
 //for officials
 		if(document.myform.techdelegate.value=="" || document.myform.techdelegate.value.indexOf(' ')==0){
			alert("Technical Delegate Name is empty");
			document.myform.techdelegate.focus();
			return false;
			}
		if(document.myform.groundjury.value=="" || document.myform.groundjury.value.indexOf(' ')==0){
			alert("Ground jury Name is empty");
			document.myform.groundjury.focus();
			return false;
			}
		if(document.myform.judge1.value=="" || document.myform.judge1.value.indexOf(' ')==0){
			alert("Judge Name is Empty");
			document.myform.judge1.focus();
			return false;
			}
		if(document.myform.judge2.value=="" || document.myform.judge2.value.indexOf(' ')==0){
			alert("Judge Name is Empty");
			document.myform.judge2.focus();
			return false;
			}
		if(document.myform.judge3.value=="" || document.myform.judge3.value.indexOf(' ')==0){
			alert("Judge Name is Empty");
			document.myform.judge3.focus();
			return false;
			}
		if(document.myform.jumpjudge.value=="" || document.myform.jumpjudge.value.indexOf(' ')==0){
			alert("Jumping Judge Name is Empty");
			document.myform.jumpjudge.focus();
			return false;
			}
		if(document.myform.coursedesign.value=="" || document.myform.coursedesign.value.indexOf(' ')==0){
			alert("Course Designer Name is Empty");
			document.myform.coursedesign.focus();
			return false;
			}
			
		return true;
		}
