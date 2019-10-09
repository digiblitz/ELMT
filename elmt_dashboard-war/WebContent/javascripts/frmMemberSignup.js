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
function validateUser(){

var lenquest = document.frmUserSignup.info_rad.length;
var chosenquest="";
//alert("inside");
//salert(lenquest);
if(lenquest==undefined)
{
	if(!document.frmUserSignup.info_rad.checked)
	{
		alert("please choose one of profile");
		return false;
	}
	
}
else
{
//alert(lenquest);
for(i=0;i<lenquest;i++)
{
if(document.frmUserSignup.info_rad[i].checked)
 { chosenquest= document.frmUserSignup.info_rad[i].value; }

}
if(chosenquest=="")
{
	alert("please choose one of profile");
	return false;
}
}

  
return true;
}









