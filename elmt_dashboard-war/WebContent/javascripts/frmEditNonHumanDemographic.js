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

function myValidate(obj)
{
	//--------------------------------Species--------------------------------
	if(document.frmMyForm.selSpecies.selectedIndex == 0 ){
        alert ("Please select valid Species" );
		document.frmMyForm.selSpecies.focus();
        return false;
    }

	
	//----------------------------------------------Demographic Description-------------------------------
	
	if(document.frmMyForm.txtDemoDesc.value==""){
		alert("Please enter valid Description");
		document.frmMyForm.txtDemoDesc.focus();
		return false;
	}


//=======================================Type===============================================
	
	if(document.frmMyForm.selTyp.selectedIndex == 0 ){
         alert ("Please select valid Type" );
		document.frmMyForm.selTyp.focus();
        return false;
    }
	
//===========================================================================================
	 vFlag = false;

	for(var i=0;i<obj.elements.length;i++)
          {
		if(obj.elements[i].type=='text')
		{
			if((obj.elements[i].value.indexOf('	') == 0) || (obj.elements[i].value.lastIndexOf('	') == (obj.elements[i].value.length-1)) ||
				(obj.elements[i].value.indexOf(' ') == 0) || (obj.elements[i].value.lastIndexOf(' ') == (obj.elements[i].value.length-1)))
			{
				obj.elements[i].value = obj.elements[i].value.trim();
				vFlag = true;
			}
		}
	}

	if(vFlag==true)
	{
		alert("Leading and Trailing spaces will be trimmed. Please submit again");
		return false;
	}

//=======================================Status======================================================	
	
	chosen="";
len = document.frmMyForm.demoStat.length ;
for(i=0;i<len;i++)
{if(document.frmMyForm.demoStat[i].checked)
  { chosen= document.frmMyForm.demoStat[i].value; }
}
if(chosen=="")
{alert ("Please choose the Status" );
 
 return false;
}
	
	
	
	
	
	
	
	
	
	
	
	

	
return true;
}

		
