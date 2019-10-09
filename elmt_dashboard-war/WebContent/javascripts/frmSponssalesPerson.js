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
function frmSalesCheck(){
	if(document.frmAssignSalesPerson.salesPersonId.value==""){
		alert("Enter Sales Person ID in Sales Person's Detail.");
		document.frmAssignSalesPerson.salesPersonId.focus();
		return false;
	}
	if(document.frmAssignSalesPerson.salesPersonId.value.indexOf(' ')==0){
		alert("Enter Valid Sales Person ID.");
		document.frmAssignSalesPerson.salesPersonId.focus();
		return false;
	}

	if(document.frmAssignSalesPerson.salesPersonId.value.length>80){
		alert("Entered Sales Person's ID is out of range.");
		document.frmAssignSalesPerson.salesPersonId.focus();
		return false;
	}

	if(document.frmAssignSalesPerson.salesPersonId.value.indexOf('  ')!==-1){
		alert("Enter Valid Sales Person ID.");
		document.frmAssignSalesPerson.salesPersonId.focus();
		return false;
	}
	return true;
}
