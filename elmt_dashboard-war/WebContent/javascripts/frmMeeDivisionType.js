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
function myvalidate(){
	s = document.getElementById("divisionEvent").value;
	if(s.indexOf("  ")!=-1 || s.indexOf(' ')==0 || s==""){
		alert("Enter an Event Division ");
		document.getElementById("divisionEvent").focus();
		return false;
	}
	if(s.length>80){
		alert("Enter a valid Event Division");
		document.getElementById("divisionEvent").focus();
		return false;
	}
	return true;
}
