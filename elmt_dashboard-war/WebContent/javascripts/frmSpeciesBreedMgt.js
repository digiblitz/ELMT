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
function breedSec(){
	
	var radLen=document.getElementById('radSize').value;
	//alert(radLen);

for(var i=0;i<radLen;i++){
	
	if(document.frmRoleMgtListRole.radSel[i].checked==true){
	
		var speciId=document.frmMyForm.uTypeId.value;
	strURL = "./BreedDetails.do?breedProcess=breedDets&speciesId="+speciId+"&refcnt=1";
	window.location.href = strURL;	
		
	document.getElementById('showBreed').style.display="block";
document.getElementById('showBreedHead').style.display="block";	
	}
	else{
	document.getElementById('showBreed').style.display="none";
document.getElementById('showBreedHead').style.display="none";	
	}
}

}


 function submitform(paramValue)
{
	strURL = "./BreedDetails.do?breedProcess=breedDets&speciesId="+paramValue+"&refcnt=1";
	window.location.href = strURL;
	
	if(document.getElementById('sel_species')!=null){
		if(document.getElementById('sel_species').value!="null"){
	document.getElementById('showBreed').style.display="block";
	document.getElementById('showBreedHead').style.display="block";
		}
}else{
document.getElementById('showBreed').style.display="none";
document.getElementById('showBreedHead').style.display="none";


}
		
}


function addBreedRow() {
                var retSpeciesId = document.frmMyForm.uTypeId.value ;
				 var retSpeciesName = document.frmMyForm.retSpeciesName.value ;
                strURL = "./BreedDetails.do?breedProcess=initBreedView&uTypeId="+retSpeciesId+"&reDirectVal=frmMgt&retSpeciesName="+retSpeciesName;
                window.location.href = strURL;
            
            }


function editBreedRow(tableID)

            {
                var breedId;
                var chkCnt=0;
                var vFlag = false;
				 var chk=document.getElementById('chk');
                if(chk==null)
                {
                    alert("No Records Found");
                    return;
                }
                var chkBoxCnt = document.frmMyForm.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmMyForm.chk[i].checked==true)
                        {
                            breedId = document.frmMyForm.chk[i].value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmMyForm.chk[i].checked = false;
                        }
                    }
                }
                else
                {
                       
                        if(document.frmMyForm.chk.checked==true)
                        {
                            breedId = document.frmMyForm.chk.value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmMyForm.chk.checked = false;
                        }
                }
                if(chkCnt==0){

                    alert("Please check one record to Proceed");
                    return;
                }

                if(vFlag == true)
                {
                    alert("Only one record can be edited");
                    return;
                }
				
				 var retSpeciesId = document.frmMyForm.uTypeId.value ;
				 var retSpeciesName = document.frmMyForm.retSpeciesName.value ;
              
                strURL = "./BreedDetails.do?breedId="+breedId+"&breedProcess=edit&Submit2=Edit&reDirectVal=frmMgt&uTypeId="+retSpeciesId+"&retSpeciesName="+retSpeciesName;
                window.location.href = strURL;
               
            
            }


 function deleteBreedRow(tableID) {

         
	 var breedId;
	 var chkCnt=0;
	 var vFlag = 0;
	 var cannotDelete=0;
	 var canDelete = 0;
	  var chk=document.getElementById('chk');
                if(chk==null)
                {
                    alert("No Records Found");
                    return;
                } 
	 var chkBoxCnt = document.frmMyForm.chk.length;

            if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
            {
                for(var i=0;i<chkBoxCnt;i++)
		{
                   
                	if(document.frmMyForm.chk[i].checked==true)
			{
                           
        
                          if(confirm(" The checked records will be deleted\n Are you sure you want to delete these Breed details?"))
		                {
			 document.frmMyForm.breedProcess.value = 'delete';
                         document.frmMyForm.uTypeId.value = document.frmMyForm.uTypeId.value;
						    document.frmMyForm.reDirectVal.value = document.frmMyForm.reDirectVal.value;
							  document.frmMyForm.retSpeciesName.value = document.frmMyForm.retSpeciesName.value;
			 document.frmMyForm.submit();
                        
		                }
                           return;
			}

                }
            }
            else
            {
                	if(document.frmMyForm.chk.checked==true)
			{
                            if(confirm(" The checked records will be deleted\n Are you sure you want to delete these Breed details?"))
		                {
			 document.frmMyForm.breedProcess.value = 'delete';
			   document.frmMyForm.uTypeId.value = document.frmMyForm.uTypeId.value;
                        document.frmMyForm.reDirectVal.value = document.frmMyForm.reDirectVal.value;
						  document.frmMyForm.retSpeciesName.value = document.frmMyForm.retSpeciesName.value;

			 document.frmMyForm.submit();

		                }
                            return;
			}

            }
         if(chkCnt==0)
               {

                    alert("Please check one record to Proceed");
                    return;
                }
  }


function checkAllBreed()
{
    

       var chkBoxCnt = document.frmMyForm.chk.length;
      
       if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
       {
            for(var i=0;i<chkBoxCnt;i++)
            {
               
                 if(document.frmMyForm.chkAll.checked==true)
                 {
                     
                         document.frmMyForm.chk[i].checked = true;
                 }
                 else
                 {
                        document.frmMyForm.chk[i].checked = false;
                 }
            }
       }
       else
       {
             if(document.frmMyForm.chkAll.checked==true)
             {
                
                     document.frmMyForm.chk.checked = true;
             }
             else
             {
                    document.frmMyForm.chk.checked = false;
             }
        }

}


 function addSpeciesRow() {
                
                strURL = "./BreedDetails.do?breedProcess=insertAddspecies&reDirectVal=frmMgt";
                window.location.href = strURL;
            
            }
	   

	
function editSpeciesRow(tableID)

            {
                var breedId;
                var chkCnt=0;
                var vFlag = false;
                var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmRoleMgtListRole.chk[i].checked==true)
                        {
                            breedId = document.frmRoleMgtListRole.chk[i].value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmRoleMgtListRole.chk[i].checked = false;
                        }
                    }
                }
                else
                {
                       
                        if(document.frmRoleMgtListRole.chk.checked==true)
                        {
                            breedId = document.frmRoleMgtListRole.chk.value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmRoleMgtListRole.chk.checked = false;
                        }
                }
                if(chkCnt==0){

                    alert("Please check one record to Proceed");
                    return;
                }

                if(vFlag == true)
                {
                    alert("Only one record can be edited");
                    return;
                }
              
                strURL = "./BreedDetails.do?speciesId="+breedId+"&breedProcess=editSpecies&Submit2=Edit&reDirectVal=frmMgt";
                window.location.href = strURL;
               
            
            }

	
 function deleteSpeciesRow(tableID) {


	var chkCnt=0;
	var vFlag = 0;
	var cannotDelete=0;
	var canDelete = 0;
	var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
	if(chkBoxCnt!=undefined && chkBoxCnt!='undefined' && chkBoxCnt > 1)
	{
		for(var i=0;i<chkBoxCnt;i++)
		{
			if(document.frmRoleMgtListRole.chk[i].checked==true)
			{
				   if(document.frmRoleMgtListRole.flag[i].value==0)
				   {
					cannotDelete++;
					document.frmRoleMgtListRole.chk[i].checked = false;
				   }

				   if(document.frmRoleMgtListRole.flag[i].value==1)
				   {
					canDelete++;
				   }
			}

			if(cannotDelete > 0 && canDelete > 0)
			{
							vFlag = 1;
			}
			else if(cannotDelete > 0 && canDelete == 0)
			{
							vFlag = 2;
			}
			else if(cannotDelete == 0 && canDelete == 0)
			{
							vFlag = 3;
			}
			else if(cannotDelete == 0 && canDelete > 0)
			{
							vFlag = 4;

			}
		}
	}
	else
	{
			if(document.frmRoleMgtListRole.chk.checked==true)
			{
				   if(document.frmRoleMgtListRole.flag.value==0)
				   {
					cannotDelete++;
					document.frmRoleMgtListRole.chk.checked = false;
				   }

				   if(document.frmRoleMgtListRole.flag.value==1)
				   {
					canDelete++;
				   }
			}

			if(cannotDelete > 0 && canDelete == 0)
			{
							vFlag = 2;
			}
			else if(cannotDelete == 0 && canDelete == 0)
			{
							vFlag = 3;
			}
			else if(cannotDelete == 0 && canDelete > 0)
			{
							vFlag = 4;

			}

	}

	if(vFlag == 1)
	{
		confirm("Only the checked records will be deleted. Click the Delete button again");
	}

	if(vFlag == 2)
	{
	   alert("The checked species cannot be deleted as it is assigned to users");
	}

	if(vFlag == 3)
	{
		alert("Please select the record to be deleted");
	}

	if(vFlag == 4)
	{
		if(confirm("Only the checked records will be deleted. \n Are you sure you want to delete these species?"))
		{
			document.frmRoleMgtListRole.breedProcess.value = 'deleteSpecies';
document.frmRoleMgtListRole.reDirectVal1.value = document.frmRoleMgtListRole.reDirectVal1.value;
			 document.frmRoleMgtListRole.submit();
		}
	}

}


function checkAllSpecies()
{
    

       var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
      
       if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
       {
            for(var i=0;i<chkBoxCnt;i++)
            {
               
                 if(document.frmRoleMgtListRole.chkAll.checked==true)
                 {
                     
                         document.frmRoleMgtListRole.chk[i].checked = true;
                 }
                 else
                 {
                        document.frmRoleMgtListRole.chk[i].checked = false;
                 }
            }
       }
       else
       {
             if(document.frmRoleMgtListRole.chkAll.checked==true)
             {
                
                     document.frmRoleMgtListRole.chk.checked = true;
             }
             else
             {
                    document.frmRoleMgtListRole.chk.checked = false;
             }
        }

}

            
	   
