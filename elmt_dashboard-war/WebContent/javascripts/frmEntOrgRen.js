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
var httpRequest;

function change()
{
    var uid=document.frmAdvDispSubTypeList.media.value;
   /* This method is called when the author is selected It creates XMLHttpRequest object to communicate with the servlet */ 
            
        var url = "./AjaxActionAdvertisement.do?rid="+mid; 
        if (window.ActiveXObject){ 
            httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest){ 
            httpRequest = new XMLHttpRequest(); 
        } 
     
        httpRequest.open("POST", url, true); 
        httpRequest.onreadystatechange = function() {processRequest(); } ; 
        httpRequest.send(null); 
   } 
 /**This is the call back method If the call is completed when the readyState is 4 and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV */ 
    function processRequest(){ 
        if (httpRequest.readyState == 4) { 
            if(httpRequest.status == 200){ 
                //get the XML send by the servlet 
                 var salutationXML = httpRequest.responseXML.getElementsByTagName("salutation")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateHTML(salutationXML); 
            } 
            else{ 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateHTML(salutationXML){ 
        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
        var displayTypeDet=new Array();
        dispDet=salutationText.split("#");

      //  alert(salutationText);
	  for(int i=0; i<mid.length;i++){
		document.myform.dispType[i].value = displayTypeDet[0];
	  }
    } 

