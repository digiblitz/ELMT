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
function postData()
{

                var typId = document.myform.uType.value ;
                //var memType = document.frmAddBreedDet.membTxt.value;
				var radCharId = document.frmRoleMgtListRole.radCharId.value ;

 if(typId!="" && radCharId==""){
                strURL = "./BreedDetails.do?breedProcess=breedCategoryDetsList&uTypeId="+typId+"&refcnt=0&radCharId="+null;
                window.location.href = strURL;
                   }else{
				   
		  strURL = "./BreedDetails.do?breedProcess=breedCategoryDetsList&uTypeId="+typId+"&refcnt=0&radCharId="+radCharId;
                window.location.href = strURL;		   
				   }

            


 }


 function submitform(paramValue)
{

var str = paramValue;
//alert(str);
var splitstr = str.split("#");

var charId= splitstr[0];
var speciesId= splitstr[1];
var radCharId=splitstr[0];

//alert(charId);
//alert(speciesId);

	strURL = "./BreedDetails.do?breedProcess=breedCategoryDets&charId="+charId+"&uTypeId="+speciesId+"&refcnt=1&radCharId="+radCharId;
	window.location.href = strURL;
	
	
if(document.getElementById('characteristic')!=null){
		if(document.getElementById('characteristic').value!="null"){
	document.getElementById('showBreed').style.display="block";
	document.getElementById('showBreedHead').style.display="block";
		}
}else{
document.getElementById('showBreed').style.display="none";
document.getElementById('showBreedHead').style.display="none";

}	
}

function breedSec(){


if(document.getElementById('radSize')!=null){
	
	var radLen=document.getElementById('radSize').value;
	//alert(radLen);
 var typId = document.myform.uType.value;
 	var charId=document.getElementById('retnCharIds').value;
		var radCharId = document.getElementById('radCharIds').value;
		var characteristicNames = document.getElementById('characteristicNames').value;
		//alert("a"+charId);		
     //alert("b"+radCharId);
if(document.getElementById('retnCharIds').value!="null" && document.getElementById('radCharIds').value!="null"){	 
for(var i=1;i<=radLen;i++){
	
	if(document.frmRoleMgtListRole.radSel[i].checked==true){
	//alert(2);
	strURL = "./BreedDetails.do?breedProcess=breedCategoryDets&charId="+charId+"&refcnt=1&uTypeId="+typId+"&radCharId="+radCharId;
	window.location.href = strURL;	
		
	document.getElementById('showBreed').style.display="block";
document.getElementById('showBreedHead').style.display="block";	

	}
		
}
}
else if(document.getElementById('retnCharIds').value=="null" && document.getElementById('radCharIds').value=="null"){
document.getElementById('showBreed').style.display="none";
document.getElementById('showBreedHead').style.display="none";	
}
}else{
//alert(4);
document.getElementById('showBreed').style.display="none";
document.getElementById('showBreedHead').style.display="none";	
}

}

function addRow() {
                var typId = document.myform.uType.value ;
				// var retCharId = document.frmBreedCharDetList.retCharId.value;
				 // var characteristicName = document.frmRoleMgtListRole.characteristicName.value;
							
           strURL = "./BreedDetails.do?breedProcess=initCharView&uTypeId="+typId+"&reDirectVal=frmMgt";
                window.location.href = strURL;

            }

function editRow(tableID)

            {
                var charId;
                var chkCnt=0;
                var vFlag = false;
                var chk=document.getElementById('chk');
                if(chk==null)
                {
                    alert("No Records Found");
                    return;
                }
                var chkBoxCnt = document.frmRoleMgtListRole.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmRoleMgtListRole.chk[i].checked==true)
                        {
                            charId = document.frmRoleMgtListRole.chk[i].value;
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
                            charId = document.frmRoleMgtListRole.chk.value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmRoleMgtListRole.chk.checked = false;
                        }
                }
                if(chkCnt==0){

                    alert("Please check one record to proceed");
                    return;
                }

                if(vFlag == true)
                {
                    alert("Only one record can be edited");
                    return;
                }
				//var characteristicName = document.frmRoleMgtListRole.characteristicName.value;
				var typId = document.myform.uType.value ;

                strURL = "./BreedDetails.do?charId="+charId+"&breedProcess=SpeciesEdit&Submit2=Edit&reDirectVal=frmMgt&uTypeId="+typId;
                window.location.href = strURL;


            }
       
function deleteRow(tableID) {


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
	   alert("Checked records cannot be deleted as it is being referred or mapped.");

	}

	if(vFlag == 3)
	{
		alert("Please select the record to be deleted");
	}

	if(vFlag == 4)
	{
		if(confirm("Only the checked records will be deleted. \n Are you sure you want to delete these Breed Characteristic?"))
		{
			document.frmRoleMgtListRole.breedProcess.value = 'deleteChar';
                         document.frmRoleMgtListRole.uType.value = document.myform.uType.value;
						 document.frmRoleMgtListRole.reDirectVal.value = document.frmRoleMgtListRole.reDirectVal.value;
			document.frmRoleMgtListRole.characteristicName.value = document.frmRoleMgtListRole.characteristicName.value;
						 document.frmRoleMgtListRole.retnCharId.value = document.frmRoleMgtListRole.retnCharId.value;
			 document.frmRoleMgtListRole.submit();
		}
	}

}

function checkAll()
{

if(document.frmRoleMgtListRole.chk!=null){
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

}

//=======================Breed Characteristics (Details)=======================================

function addCharDetRow() {

                 var uTypeId = document.frmBreedCharDetList.userTypeId.value ;
				 var retCharId = document.frmBreedCharDetList.characId.value;
				  var characteristicName = document.frmBreedCharDetList.characterisName.value;
				   var radCharId = document.frmBreedCharDetList.radCharacId.value;
//alert("radCharId value "+radCharId);
              
                if(uTypeId=="" && retCharId==""){
                strURL = "./BreedDetails.do?breedProcess=initInsert&reDirectVal=frmMgt";
                window.location.href = strURL;
                }
                else{
                   if(uTypeId!="" && retCharId==""){
                       strURL = "./BreedDetails.do?breedProcess=initInsert&uTypeId="+uTypeId+"&charId="+null+"&reDirectVal=frmMgt";
                       window.location.href = strURL;
                   }
                   else{
                      strURL = "./BreedDetails.do?breedProcess=initInsert&uTypeId="+uTypeId+"&charId="+retCharId+"&reDirectVal=frmMgt&characteristicName="+characteristicName+"&radCharId="+radCharId;
                      window.location.href = strURL;
                   }
                }
            }
			
			
function checkCharDetAll()
{
       var chkBoxCnt = document.frmBreedCharDetList.chk.length;
       if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
       {
            for(var i=0;i<chkBoxCnt;i++)
            {
                  if(document.frmBreedCharDetList.chkAll.checked==true)
                 {
                        document.frmBreedCharDetList.chk[i].checked = true;
                 }
                 else
                 {
                        document.frmBreedCharDetList.chk[i].checked = false;
                 }
            }
       }
       else
       {
             if(document.frmBreedCharDetList.chkAll.checked==true)
             {
                    document.frmBreedCharDetList.chk.checked = true;
             }
             else
             {
                    document.frmBreedCharDetList.chk.checked = false;
             }
        }

}			
			
function editCharDetRow(tableID)

            {
                var characterdetId;
                var uTypeId = document.frmBreedCharDetList.userTypeId.value ;
                var charId = document.frmBreedCharDetList.characId.value ;
                if(uTypeId==""){
                    alert("Select any one Species");
                }
		if(charId=="" && uTypeId!=""){
		    alert("Select any one Characterisic");
		}
                var chkCnt=0;
                var vFlag = false;
                var chk=document.getElementById('chk');
                if(chk==null && uTypeId!="" && charId!=""){
		  alert("No records found");
	         }
	        else{
                var chkBoxCnt = document.frmBreedCharDetList.chk.length;
                if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
                {
                    for(var i=0;i<chkBoxCnt;i++)
                    {
                        if(document.frmBreedCharDetList.chk[i].checked==true)
                        {
                            characterdetId = document.frmBreedCharDetList.chk[i].value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmBreedCharDetList.chk[i].checked = false;
                        }
                    }
                }
                else
                {
                        if(document.frmBreedCharDetList.chk.checked==true)
                        {
                            characterdetId = document.frmBreedCharDetList.chk.value;
                            chkCnt++;
                        }

                        if(chkCnt>1)
                        {
                            vFlag = true;
                            document.frmBreedCharDetList.chk.checked = false;
                        }
                }
                if(chkCnt==0){

                    alert("Please check one record to edit");
                    return;
                }

                if(vFlag == true)
                {
                    alert("Only one record can be edited");
                    return;
                }
               
			   var characteristicName = document.frmBreedCharDetList.characterisName.value;
				var typId = document.frmBreedCharDetList.userTypeId.value;
				var retCharId = document.frmBreedCharDetList.characId.value;
				 var radCharId = document.frmBreedCharDetList.radCharacId.value;
				
                strURL = "./BreedDetails.do?characterdetId="+characterdetId+"&breedProcess=editCharDet&characteristicName="+characteristicName+"&reDirectVal=frmMgt&uTypeId="+typId+"&retCharId="+retCharId+"&radCharId="+radCharId;
		window.location.href = strURL;
          }
  }			
		
		
	function deleteCharDetRow(tableID) {


	// var characterdetId;
         var uTypeId = document.frmBreedCharDetList.userTypeId.value ;
                var charId = document.frmBreedCharDetList.characId.value ;
                if(uTypeId==""){
                    alert("Select any one Species");
                }
		if(charId=="" && uTypeId!=""){
		    alert("Select any one Characterisic");
		}
	 var chkCnt=0;
	 var vFlag = 0;
	 var cannotDelete=0;
	 var canDelete = 0;
         var chk=document.getElementById('chk');
         if(chk==null && uTypeId!="" && charId!=""){
		  alert("No records found");
	  }
	  else{
	 
	 var chkBoxCnt = document.frmBreedCharDetList.chk.length;

            if(chkBoxCnt!=undefined && chkBoxCnt!='undefined')
            {
                for(var i=0;i<chkBoxCnt;i++)
		{

                	if(document.frmBreedCharDetList.chk[i].checked==true)
			{


                          if(confirm(" The checked records will be deleted\n Are you sure you want to delete these Breed Character details?"))
		                {
						//alert(44444);
			 document.frmBreedCharDetList.breedProcess.value = 'deleteBrdChar';
                         document.frmBreedCharDetList.userTypeId.value = document.frmBreedCharDetList.userTypeId.value;
                          document.frmBreedCharDetList.characId.value = document.frmBreedCharDetList.characId.value;
						    document.frmBreedCharDetList.reDirectVal1.value = document.frmBreedCharDetList.reDirectVal1.value;
			document.frmBreedCharDetList.characterisName.value = document.frmBreedCharDetList.characterisName.value;
			                document.frmBreedCharDetList.radCharacId.value = document.frmBreedCharDetList.radCharacId.value;

			 document.frmBreedCharDetList.submit();

		                }
                           return;
			}

                }
            }
            else
            {
                	if(document.frmBreedCharDetList.chk.checked==true)
			{
                            if(confirm(" All the checked records will be deleted\n Are you sure you want to delete these Breed Character details?"))
		                {
			 document.frmBreedCharDetList.breedProcess.value = 'deleteBrdChar';
                         document.frmBreedCharDetList.userTypeId.value = document.frmBreedCharDetList.userTypeId.value;
                         document.frmBreedCharDetList.characId.value = document.frmBreedCharDetList.characId.value;
						   document.frmBreedCharDetList.reDirectVal1.value = document.frmBreedCharDetList.reDirectVal1.value;
			document.frmBreedCharDetList.characterisName.value = document.frmBreedCharDetList.characterisName.value;
			 document.frmBreedCharDetList.radCharacId.value = document.frmBreedCharDetList.radCharacId.value;
			 document.frmBreedCharDetList.submit();

		                }
                            return;
			}

            }
         if(chkCnt==0)
               {

                    alert("Please check record(s) to proceed");
                    return;
                }
          }
  }


	
		
			
