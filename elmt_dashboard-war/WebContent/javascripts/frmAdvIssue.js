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

//============================= For Advt Issue Add =================================

function advIssueAddCheck(){
	if(document.frmCreateIssueMast.mediaId.selectedIndex == 0 ){
        alert ( "Please select a Media Type." );
		document.frmCreateIssueMast.mediaId.focus();
        return false;
    }

	if(document.frmCreateIssueMast.txtIsssueName.value==""){
		alert("Issue Name cannot be empty.");
		document.frmCreateIssueMast.txtIsssueName.focus();
		return false;
	}

	if(document.frmCreateIssueMast.txtIsssueName.value.length>80){
		alert("Issue Name should not exceed 80 characters.");
		document.frmCreateIssueMast.txtIsssueName.focus();
		return false;
	}

	if(document.frmCreateIssueMast.txtIsssueName.value.indexOf(' ')==0){
		alert("Enter Valid Issue Name.");
		document.frmCreateIssueMast.txtIsssueName.focus();
		return false;
	}

	if(document.frmCreateIssueMast.txtIsssueName.value.indexOf('  ')!==-1){
		alert("Enter Valid Issue Name.");
		document.frmCreateIssueMast.txtIsssueName.focus();
		return false;
	}
	return true;
}

//======================== For Advt Issue Edit ==============================

function advIssueEditCheck(){
	if(document.frmEditIssueMast.mediaNewid.selectedIndex == 0 ){
        alert ( "Please select a Media Type." );
		document.frmEditIssueMast.mediaNewid.focus();
        return false;
    }

	if(document.frmEditIssueMast.txtIssueName.value==""){
		alert("Issue Name cannot be empty.");
		document.frmEditIssueMast.txtIssueName.focus();
		return false;
	}

	if(document.frmEditIssueMast.txtIssueName.value.length>80){
		alert("Issue Name should not exceed 80 characters.");
		document.frmEditIssueMast.txtIssueName.focus();
		return false;
	}

	if(document.frmEditIssueMast.txtIssueName.value.indexOf(' ')==0){
		alert("Enter Valid Issue Name.");
		document.frmEditIssueMast.txtIssueName.focus();
		return false;
	}

	if(document.frmEditIssueMast.txtIssueName.value.indexOf('  ')!==-1){
		alert("Enter Valid Issue Name.");
		document.frmEditIssueMast.txtIssueName.focus();
		return false;
	}
	return true;
}

	
