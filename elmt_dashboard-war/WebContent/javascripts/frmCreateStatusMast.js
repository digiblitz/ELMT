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



//-------------------------------------Status name------------------------------------------------------------

if(document.frmCreateStatusMast.stName.value=="")
   {alert(" Status name cannot be empty ");
     document.frmCreateStatusMast.stName.focus();
    return false; }
	
	if((document.frmCreateStatusMast.stName.value.length >80 ))
 {alert("Status name exceeds the maximum of 80 characters");
  document.frmCreateStatusMast.stName.focus();
 return false;}
 
 //----------------------------------------Description---------------------------------------------------------------------
 
if(document.frmCreateStatusMast.desArea.value=="")
   {alert("Description cannot be empty ");
     document.frmCreateStatusMast.desArea.focus();
    return false; }
	
	if((document.frmCreateStatusMast.desArea.value.length >1024 ))
 {alert("Description exceeds the maximum of 1024 characters");
  document.frmCreateStatusMast.desArea.focus();
 return false;}			
    return true;
    }
	
