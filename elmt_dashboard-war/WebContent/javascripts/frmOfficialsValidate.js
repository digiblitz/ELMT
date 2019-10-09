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
//-------------function for doublespace validation -----------------------------

function Dospace(str){
	if(str.indexOf("  ")!=-1 || str.indexOf(' ')==0){
		return true;
	}
	else{
		return false;
	}
}
function offValidate(){
	var s = document.getElementById("judgeName").value;
	
	if(Dospace(s)|| s==""){
		alert("Enter Judge Type Name");
		document.getElementById("judgeName").focus();
		return false;
	}
	if(s.length>80){
		alert("Enter valid Judge Type Name");
		document.getElementById("judgeName").focus();
		return false;
	}
	return true;
	
}
