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
//---------------------------------------------Distribution List Name-----------------------------
if(document.frmMessageEditDistriList.txtDisList.value==""){
		alert("Distribution List Name cannot be empty");
		document.frmMessageEditDistriList.txtDisList.focus();
		return false;
	}

	if(document.frmMessageEditDistriList.txtDisList.value.length>80){
		alert("Distribution List Name exceeds the maximum of 80 characters");
		document.frmMessageEditDistriList.txtDisList.focus();
		return false;
	}

	
	if(document.frmMessageEditDistriList.txtDisList.value.indexOf(' ')==0){
		alert("Enter Valid Distribution List Name");
		document.frmMessageEditDistriList.txtDisList.focus();
		return false;
	}
	
	if(document.frmMessageEditDistriList.txtDisList.value.indexOf('  ')!==-1){
		alert("Enter Valid Distribution List Name");
		document.frmMessageEditDistriList.txtDisList.focus();
		return false;
	}
//---------------------------------------------------Destination List---------------------------
//alert(document.frmMessageEditDistriList.selDest.length);
	if(document.frmMessageEditDistriList.selDest.length==0){
//(document.frmMessageEditDistriList.selDest.length);
	        alert ( "Cannot make an empty list" );
			document.frmMessageEditDistriList.selDest.focus();
	        return false;
	    }

var len = document.frmMessageEditDistriList.selDest.options.length;
for(var i = 0; i < len; i++) {
document.frmMessageEditDistriList.selDest.options[i].selected=true;
}
	return true;
}

//=-------------------------------------------------------------------------------
// Add the selected items from the source to destination list

/*function addSrcToDestList() {
	for(var i = 0; i < document.frmMessageEditDistriList.selSource.length; i++) 
		{
			if((document.frmMessageEditDistriList.selSource.options[i]!=null)&&(document.frmMessageEditDistriList.selSource.options[i].selected)) 
			{
				var found = false;
				for(var count = 0; count < document.frmMessageEditDistriList.selDest.length; count++) 
					{
						if (document.frmMessageEditDistriList.selSource.options[i].text == document.frmMessageEditDistriList.selDest.options[count].text) 
						{
						found = true;
						}
					}
			}
		}
		if (found != true) 
			{
				for(var no=0;no<document.frmMessageEditDistriList.selSource.options.length;no++)
				{
					if(document.frmMessageEditDistriList.selSource.options[no].selected == true)
					{	
						var optn = document.createElement("OPTION");
						optn.text = frmMessageEditDistriList.selSource.options[no].text;
						optn.value = document.frmMessageEditDistriList.selSource.options[no].value;
						document.frmMessageEditDistriList.selDest.add(optn);
					}
				}
			}
			else
			{
						alert('Member is already added in your List ');
			}
}

function deleteFromDestList() 
{
var found=false;

for(var no=0;no<document.frmMessageEditDistriList.selDest.length;no++)
	{
		if(document.frmMessageEditDistriList.selDest.options[no].selected==true)
		{			found=true;			}
	}
if(found==false)
	{		
	alert('Select any member from the destination list');
	document.frmMessageEditDistriList.selDest.focus();
	clear();
	}
else
{
	for(var no=0;no<document.frmMessageEditDistriList.selDest.length;no++)
	{
		if(document.frmMessageEditDistriList.selDest.options[no].selected==true)
		{
			var optn = document.createElement("OPTION");
			optn.text = document.frmMessageEditDistriList.selDest.options[no].text;
			optn.value = document.frmMessageEditDistriList.selDest.options[no].value;
			document.frmMessageEditDistriList.selSource.add(optn);
			document.frmMessageEditDistriList.selDest.remove(no);
		}
	}
}
}
*/
function clear()
{
	for(var no=0;no<document.frmMessageEditDistriList.selDest.length;no++)
	{
		document.frmMessageEditDistriList.selDest.options[i].selected = false;
	}
	for(var no=0;no<document.frmMessageEditDistriList.selSource.length;no++)
	{
		document.frmMessageEditDistriList.selSource.options[i].selected = false;
	}
}


//=-------------------------------------------------------------------------------
// Add the selected items from the source to destination list
function addSrcToDestList() {
selDest = window.document.frmMessageEditDistriList.selDest;
selSource = window.document.frmMessageEditDistriList.selSource; 
var len = selDest.length;
for(var i = 0; i < selSource.length; i++) {
if ((selSource.options[i] != null) && (selSource.options[i].selected)) {
//Check if this value already exist in the destList or not
//if not then add it otherwise do not add it.
var found = false;
for(var count = 0; count < len; count++) {
if (selDest.options[count] != null) {
if (selSource.options[i].text == selDest.options[count].text) {
found = true;
break;
      }
   }
}
if (found != true) {
//for(var i = (len-1); i >= 0; i--){
selDest.options[len] = new Option(selSource.options[i].text,selSource.options[i].value); 
//selDest.options[len] = new Option(selSource.options[i].value); 
//selDest.options[len].selected=true; 
for(var lens= selSource.options.length - 1;i >= 0; i-- ){
//selDest.options[i].selected; 
if ((selSource.options[i] != null) && (selSource.options[i].selected == true)) {
selSource.options[i] = null;
}}
len++;
         }
/*for(var i=0;i<selSource.options.length;i++ ){
selDest.options[i].selected=true; 
}*/
      }
   }
}

//-------------------------------------------------
//Remove the selected items from the Destination to Source list
function deleteFromDestList() {
selDest = window.document.frmMessageEditDistriList.selDest;
selSource = window.document.frmMessageEditDistriList.selSource; 
var len = selSource.length;
for(var i = 0; i < selDest.length; i++) {
if ((selDest.options[i] != null) && (selDest.options[i].selected)) {
//Check if this value already exist in the destList or not
//if not then add it otherwise do not add it.
var found = false;
for(var count = 0; count < len; count++) {
if (selSource.options[count] != null) {
if (selDest.options[i].text == selSource.options[count].text) {
//selSource.options[i].text=="";

found = true;
break;
      }
   }
}
if (found != true) {
selSource.options[len] = new Option(selDest.options[i].text,selDest.options[i].value);
//selDest.options[i]=null; 
for(var lens= selDest.options.length - 1;i >= 0; i-- ){
if ((selDest.options[i] != null) && (selDest.options[i].selected == true)) {
selDest.options[i] = null;
}}
len++;
         }
      }
   }
}