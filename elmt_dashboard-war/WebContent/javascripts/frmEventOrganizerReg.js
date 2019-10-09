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
//------------------------------ MRO Organizer Registration Ajax Script -------------------------

function initCondition(){
	document.getElementById('parta').style.display = "block";
	document.getElementById('partb').style.display = "block";
	document.getElementById('partc').style.display = "block";
	document.getElementById('partd').style.display = "block";
	document.getElementById('parte').style.display = "block";
}

function expandColl(divId){
	if(document.getElementById(divId).style.display == "none"){
		document.getElementById(divId).style.display = "block";
	}
	else{
		document.getElementById(divId).style.display = "block";
	}
}
