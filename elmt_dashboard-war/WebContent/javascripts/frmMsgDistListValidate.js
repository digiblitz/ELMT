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
/*function checkAll() {
     el = document.forms['dlist'].elements;
     for (i=0; i < el.length; i++) {
       if(el[i].type == "checkbox") {
          if(el[i].checked== true && el[i].value== "ChkAll") {
            //alert("ok");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=1);
               }
            }
            break;
          }
          if(el[i].checked== false && el[i].value== "ChkAll") {
            //alert("uncheck");
            for (j=0; j < el.length; j++) {
               if(el[j].type == "checkbox") {
                  void(el[j].checked=0);
               }
            }
          }   
       }
     }
 }
 
function DelAll(){
	var chkValue = "";
	e = document.forms['dlist'].elements;
	//alert('Hai  '+document.dlist.dlstsize.value);
	var count =0;
	for(i=0 ; i< e.length; i++){
		if(e[i].type == "checkbox"){
			 if(e[i].checked == true && e[i].value != "ChkAll") {
				 count++;
				 chkValue +=  e[i].value + "#";
			}
		}
	}
	if(count == 0 ){
		if(document.dlist.dlstsize.value==0){
			alert('Distribution List is Empty');
			return false;
		}
		else{
			alert("Check any one of the List");
			return false;
		}
	}
	else{
		document.dlist.method = "post";
		document.dlist.process.value="Delete";
		arrayval = new Array();
		var num=document.dlist.dlstsize.value;
		j=0;
		chkname="";
		chkboxstr="";
		//The values are separated by # as delimiters......
		for(i=0;i<num;i++)
		{   
			chkname="dlistnam"+(j++);
			if(document.getElementById(''+chkname).checked){ 
				chkboxstr=chkboxstr+"#"+document.getElementById(''+chkname).value;
			}
			document.dlist.dlistct.value=chkboxstr;
		}
		document.dlist.action = "AddrDistList.do";
		document.dlist.submit();
	}
}*/
function checkAll(){
	//alert(document.dlist.dlstsize.value);
	var count = document.getElementById('dlstsize').value;
	//alert(count);
	if(document.getElementById('chkAll').checked == true){
		//alert('inside if');
		for (i=0; i < count; i++) {
			
		document.getElementById('checkbox'+i).checked = true;
		}
	}else{
		for (i=0; i < count; i++) {
		document.getElementById('checkbox'+i).checked = false;
		}
	}
}
function resetAll(){
	var count = document.getElementById('dlstsize').value;	
	document.getElementById('chkAll').checked = false;
	for (var i=0; i < count; i++) {
		document.getElementById('checkbox'+i).checked = false;
	}
}
function DelAll(){
	var count = document.getElementById('dlstsize').value;
	var flag = false;
	var checkCount = 0;
		for (i=0; i < count; i++) {
			if(document.getElementById('checkbox'+i).checked == true){
				flag = true;
				checkCount++;
			}
		}
		if(checkCount==0){
			if(document.dlist.dlstsize.value==0){
				alert('Distribution List is Empty');
				return false;
			}
			else{
				alert("Check any one of the List");
				return false;
			}
		}else{
			var num=document.getElementById('dlstsize').value;
			
			j=0;
			var chkname="";
			var chkboxstr="";
			//The values are separated by # as delimiters......
			for(i=0;i<num;i++)
			{   
				chkname="checkbox"+(j++);
				if(document.getElementById(''+chkname).checked){ 
					chkboxstr=chkboxstr+"#"+document.getElementById(''+chkname).value;
				}				
			}
			document.getElementById('dlistct').value=chkboxstr;
			document.dlist.method = "post";
			document.dlist.process.value="Delete";
			document.dlist.action = "AddrDistList.do";
			//alert(document.dlist.method);
			//alert(chkboxstr);
			document.dlist.submit();
			//return;
		}
	}
