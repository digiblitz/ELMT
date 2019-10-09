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
	
 
var chosen2="";
var len2 = document.frmUserSignup.info_rad.length ;
 for(i=0;i<len2;i++){
	 if(document.frmUserSignup.info_rad[i].checked){
	 chosen2= document.frmUserSignup.info_rad[i].value;
 }

 
	
 	
lenquest = document.frmUserSignup.quest_rad.length;
chosenquest="";
for(i=0;i<lenquest;i++)
{if(document.frmUserSignup.quest_rad[i].checked)
  { chosenquest= document.frmUserSignup.quest_rad[i].value; }
}
if(chosenquest=="")
{alert("Select any of the option in yes / no of the same person");
 return false;
}  
if(chosenquest=="yes"){
		if(chosen2==""){
			alert("Please select any profile to proceed.");
		}
   return false;
  }
 
return true;
}

function chstat()
{
var lenques=document.frmUserSignup.info_rad.length;

if(lenques==undefined)
{
	if(document.frmUserSignup.info_rad.checked){
		document.frmUserSignup.quest_rad[0].checked=false;
		document.frmUserSignup.secques.value="";
	}
	
}
else
{

for(i=0;i<lenques;i++)
{
if(document.frmUserSignup.info_rad[i].checked)
 { chosenquest= document.frmUserSignup.info_rad[i].value; }

}
if(chosenquest!="")
{
	document.frmUserSignup.quest_rad[0].checked=false;
	 
}
}

}
