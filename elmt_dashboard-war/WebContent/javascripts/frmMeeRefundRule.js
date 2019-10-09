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
//--------------------------------------------------------------------------------
//-------------function for doublespace validation -----------------------------

function Dospace(str){
if(str.indexOf("  ")!=-1 || str.indexOf(' ')==0)
{return true;}
else {return false;}
}
//----------------------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------

function myvalidate(){
	var s = document.getElementById("refundType").value;
	
	if(s==""){
		alert("Enter Refund Type Name");
		document.getElementById("refundType").focus();
		return false;
	}
	if(Dospace(s)){
		alert("Enter valid Refund Type Name");
		document.getElementById("refundType").focus();
		return false;
	}
	if(s.length>80){
		alert("Enter a valid Refund Type Name");
		document.getElementById("refundType").focus();
		return false;
	}
	return true;
	
}

	
