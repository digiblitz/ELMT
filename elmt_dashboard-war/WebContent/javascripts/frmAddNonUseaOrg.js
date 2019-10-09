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
function myvalidate(){

//-------------------------------------Non-HLC Organization Name------------------------------------------------------------
//document.frmAddNonUseaOrg.txtorgname.value==""||document.frmAddNonUseaOrg.txtorgname.value==" "
if(document.frmAddNonUseaOrg.txtorgname.value=="" || document.frmAddNonUseaOrg.txtorgname.value.indexOf(' ')==0)
   {
	 alert(" Non-HLC Organization Name cannot be empty ");
     document.frmAddNonUseaOrg.txtorgname.focus();
     return false; 
	}

 if((document.frmAddNonUseaOrg.txtorgname.value.length >80 ))
 {
  alert("Non-HLC Organization Name exceeds the maximum of 80 characters");
  document.frmAddNonUseaOrg.txtorgname.focus();
  return false;
 }
 return true;
}
