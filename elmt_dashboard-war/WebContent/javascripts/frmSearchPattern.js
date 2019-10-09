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
/*  Javascript Document 
******************************************************************************************************************
	Product Name: Integrated Enterprise Dashboard - Association / Club V0.5
	Organization: digiBlitz Technologies (P) Ltd.
    Created by: K.N.Sathish
    Created Date: 07/08/2006  
*****************************************************************************************************************
*/


               
function srchDisp(){
	var selObj = document.getElementById('searchType_id');
	var selIndex = selObj.selectedIndex;
	//var selTxt = selObj.options[selIndex].text;
	//alert(selTxt);
	var selVal = selObj.options[selIndex].value;
	//alert(selVal);
	if (selVal == 0 ){
       document.getElementById('memberSrch').style.display="block";
	   document.getElementById('userSrch').style.display="none";
	   document.getElementById('generalSrch').style.display="none";
    }
	if (selVal == 1 ){
       document.getElementById('memberSrch').style.display="none";
	   document.getElementById('userSrch').style.display="block";
	   document.getElementById('generalSrch').style.display="none";
    }
	if (selVal == 2 ){
       document.getElementById('memberSrch').style.display="none";
	   document.getElementById('userSrch').style.display="none";
	   document.getElementById('generalSrch').style.display="block";
    }
}




