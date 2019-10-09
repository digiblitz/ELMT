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
function chk(){
	
//==================================== For Radio Button ==============================	
		 
		if(document.frmMeeAdminEduActRegiApprove.comments.value==""){
			alert("Enter a comments");
			document.frmMeeAdminEduActRegiApprove.comments.focus();
			return false;
		}
		if(document.frmMeeAdminEduActRegiApprove.comments.value.indexOf(' ')==0){
		alert("Please avoid  unwanted white space");
		document.frmMeeAdminEduActRegiApprove.comments.focus();
		return false;
	      }
		if(document.frmMeeAdminEduActRegiApprove.comments.value.length>1024){
			alert("Comments is out of range");
			document.frmMeeAdminEduActRegiApprove.comments.focus();
			return false;
		}
		if(document.frmMeeAdminEduActRegiApprove.comments.value.indexOf('  ')!==-1){
		alert("Please avoid  unwanted white space");
		document.frmMeeAdminEduActRegiApprove.comments.focus();
		return false;
	   }
	return true;
	}
 
 
