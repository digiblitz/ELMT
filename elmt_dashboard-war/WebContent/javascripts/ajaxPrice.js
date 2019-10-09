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
var req;
  
function retrieveURL(methodName,param) {  

 if(param.value.length>0 && param.value!="") {
 
 var paramName = param.name;
  var url = null;

  if(paramName=='selMediaType'){
    url = "dropDown.do?method="+escape(methodName)+"&mid="+escape(param.value);   
	  
  }   else if (paramName=='selDisplayType') {
     url = "dropDown.do?method="+escape(methodName)+"&dispId="+escape(param.value);   
   } 
  
   
      if (window.XMLHttpRequest) {
           req = new XMLHttpRequest();
       } else if (window.ActiveXObject) {
           req = new ActiveXObject("Microsoft.XMLHTTP");
       }
       if(paramName=='selMediaType'){
            req.onreadystatechange = displayTypeChange;
       } else if (paramName=='selDisplayType') {
           req.onreadystatechange = displaySubTypeChange;
       }  
       req.open("GET", url, true);
       req.send(null);

    } else {       
		setToDefault('selDisplayTypeId');
        setToDefault('selDisplaySubTypeId');
    }
   
  }  
  
  function displayTypeChange() {

    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
	  
       var xmlDoc = req.responseXML.documentElement;
	   var displayXmlDoc = xmlDoc.getElementsByTagName('displayType')[0];
	   fillDropDownDisplyTag(displayXmlDoc,'selDisplayTypeId');
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }


  

function fillDropDownDisplyTag(xmlDoc,objName){

        var xRows= xmlDoc.getElementsByTagName('entry');	   
		var objDDL = document.getElementById(objName);          	          
		objDDL.innerHTML="";	       
		setToDefault('selDisplaySubTypeId');		
	    for (var i=0; i<xRows.length; i++) {
			var nameNodes = xRows[i].getElementsByTagName("optionValue");
			var valueNodes = xRows[i].getElementsByTagName("optionText");
			if (nameNodes.length > 0 && valueNodes.length > 0) {
			  var theValue = nameNodes[0].firstChild.nodeValue;
			  var theText = valueNodes[0].firstChild.nodeValue;          
			}
			var option = new Option(theText,theValue);
			 try {
				objDDL.add(option,null);     
			 }catch(e){
				objDDL.add(option,-1);
			 }
      }
}
  
  function setToDefault(objName){
    var currObj = document.getElementById(objName);
    currObj.innerHTML="";
	var rootObj =  document.createElement("option");
	var attrib = document.createAttribute("value");
    attrib.value="";
	rootObj.setAttributeNode(attrib);
    newtext=document.createTextNode('Select One');
    rootObj.appendChild(newtext);
    currObj.appendChild(rootObj);

  }
  
  function displaySubTypeChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
       var xmlDoc = req.responseXML.documentElement;
	   var xRows= xmlDoc.getElementsByTagName('entry');
	    var objDDL = document.getElementById("selDisplaySubTypeId");  
	    objDDL.innerHTML="";	   
		for (var i=0; i<xRows.length; i++) {
			var nameNodes = xRows[i].getElementsByTagName("optionValue");
			var valueNodes = xRows[i].getElementsByTagName("optionText");
			if (nameNodes.length > 0 && valueNodes.length > 0) {
			  var theValue = nameNodes[0].firstChild.nodeValue;
			  var theText = valueNodes[0].firstChild.nodeValue;          
			}
			var option = new Option(theText,theValue);
			 try {
				objDDL.add(option,null);     
			 }catch(e){
				objDDL.add(option,-1);
			 }
      }
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

