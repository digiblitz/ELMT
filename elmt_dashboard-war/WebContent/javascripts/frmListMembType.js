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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function enableonHuman(Value){
	if(Value.toLowerCase()=="human"){
		document.getElementById('year').style.visibility='visible';
		document.getElementById('yearText').style.visibility='visible';
		document.frmCreateContactTypeList.year.disabled=false;
		document.frmCreateContactTypeList.membTxt.value="human";
	}
	else{
		//document.frmCreateContactTypeList.year.disabled=true;
		document.getElementById('year').style.visibility='hidden';
		document.getElementById('yearText').style.visibility='hidden';
		document.frmCreateContactTypeList.membTxt.value = "nonhuman";
	}
}

function addRow(tableID) {

            var table = document.getElementById(tableID);

            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            cell1.appendChild(element1);

            var cell2=row.insertCell(1);
			var element2=document.createElement("input");
			element2.type="text";
			cell2.appendChild(element2)

			var cell3=row.insertCell(2);
			var element3=document.createElement("input");
			element3.type="text";
			cell3.appendChild(element3);

			var cell4=row.insertCell(3);
                        cell4.innerHTML='<select name="durationNoDdl"'+ rowCount +' id="durationNoDdl"'+ rowCount +' class="textboxOne" >'+
                                        '<option value="1">1</option>'+
                                        '<option value="2">2</option>'+
                                        '<option value="3">3</option>'+
                                        '<option value="4">4</option>'+
                                        '<option value="5">5</option>'+
                                        '<option value="6">6</option>'+
                                        '<option value="7">7</option>'+
                                        '<option value="8">8</option>'+
                                        '<option value="9">9</option>'+
                                        '<option value="10">10</option>'+
                                        '<option value="999">999</option>'+
                            		'</select>';


                        var cell4=row.insertCell(4);
                        cell4.innerHTML='<select name="durationTypeDdl"'+ rowCount +' id="durationNoDdl"'+ rowCount +' class="textboxOne" >'+
                                        '<option value="year">year</option>'+
                                        '<option value="month">month</option>'+
                                        '</select>';
			//var cell5=row.insertCell(4);
			//var element5=document.createElement("select");
			//element5.name="durationNoDdl";
			//element5.Id="durationNoDdl";
			//element
			//cell5.appendChild(element5)

			var cell6=row.insertCell(5);
			var element6=document.createElement("input");
			element6.type="radio";
                        element6.name="rd"+rowCount;
                        element6.value="active";
			cell6.appendChild(element6);

			var cell7=row.insertCell(6);
			var element7=document.createElement("input");
			element7.type="radio";
                        element7.name="rd"+rowCount;
                        element7.value="inactive";
			cell7.appendChild(element7);
                        return""

        }


function rdselect()
{
    document.viewEdit.memId.value=getCheckedValue(document.viewEdit.select11);
    var chkbox=document.viewEdit.select11;
    if(chkbox.checked==false);
}

//Selected  When Add Button is Clicked

function getCheckedValue(radioObj) {
	if(!radioObj)
		return "";
	var radioLength = radioObj.length;
	if(radioLength == undefined)
		if(radioObj.checked)
			return radioObj.value;
		else
			return "";
	for(var i = 0; i < radioLength; i++) {

		if(radioObj[i].checked) {
			return radioObj[i].value;
		}
	}
	return "";
}

function postData(tableID){
               var table = document.getElementById(tableID);
            var rowCount = table.rows.length;

            for(var i=3; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];

               // var strmembershipId=new Array();
              //  var i=0;
                if(null != chkbox && true == chkbox.checked)
                    {
                        var memID=chkbox.value;
                        strURL = "./memberMaster.do?memProcess=memTyDelete&memId="+memID;
                         window.location.href = strURL;
                    }




}
}





