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
function disableNSF(){
		var indx1 = document.frmTransacDet.listSz.value;
		var totamt = 0;
		var tempAmt = 0;
		for(k=0;k<indx1;k++){
			var revBox = "revBox"+k;
			if(document.getElementById(revBox).checked==true){
				document.getElementById('NsfPay').disabled = false;	
				break;
			}
			else{
				document.getElementById('NsfPay').disabled = true;	
			}
		}
}


function sumUpRev(selChk){
	var revamt = 0;
	var tempval = 0;
	var amtName = "revamtName"+selChk;
	var revBox = "revBox"+selChk;
	var actualAmount = "actualAmount"+selChk;
	
	if(document.frmTransacDet.revAmt.value>0){
		revamt += Number(document.frmTransacDet.revAmt.value);
	}
	
	if(document.getElementById(revBox).checked==true){
		if(document.getElementById(amtName).value!=""){
			if(document.getElementById(amtName).value>0){
				tempval = document.getElementById(amtName).value;
				revamt = revamt + Number(tempval);
			}
			else{
				tempval = document.getElementById(actualAmount).value
				revamt = revamt + Number(tempval);				
			}
		}
	}
	else{
		if(document.getElementById(amtName).value!=""){
			if(document.getElementById(amtName).value>0){
				tempval = document.getElementById(amtName).value;
				revamt = revamt - Number(tempval);
			}
			else{
				tempval = document.getElementById(actualAmount).value
				revamt = revamt - Number(tempval);				
			}			
		}
	}
	
	if(document.frmTransacDet.revAmt.value>document.frmTransacDet.checkAmt.value){
		alert('Amount Reverse exceeds the pending amount to be paid');
		return false;
	}
	document.frmTransacDet.revAmt.value=revamt;
}   

function sumUp(){
	var indx = document.frmTransacDet.listSz.value;
	var totamt = 0;
	for(i=0;i<indx;i++){
		var amtName = "amtName"+i;
		var payType	= "payType"+i;
		var ccType = "ccType"+i;
		
		if(document.getElementById(amtName).value!=""){
		        if(document.getElementById(amtName).value>0){
				tempval = document.getElementById(amtName).value;
					totamt = totamt + Number(tempval);
				}
		}

			chkDiv = "div"+(i+1);
			index = chkDiv.length;
			initPos = 0;
			endPos = index;
			for(j=0;j<index;j++){
				pos = IsNumeric(chkDiv.charAt(j));
				if(pos==true){
					initPos = j;
				}
			}
		
		ind = chkDiv.substring(initPos,endPos);

			if(document.getElementById(chkDiv).checked==true){	
				itemNme = "divNewItem"+ind;
				divtransId = "divTransId"+ind;
				transMode = "divTransMode"+ind;
				payMode = "payMode"+ind;
				newAmt = "newAmt"+ind;		
				if(document.getElementById(newAmt).value>0){
					tempVal = document.getElementById(newAmt).value;
					totamt = totamt + Number(tempVal);
				}
			}
		}
		document.frmTransacDet.totalAmt.value=totamt;
}
			//add a new row to the table
			function addRow(val)
			{
				//alert('Value is '+val);
				//alert('document.frmTransacDet.listSz.value'+document.frmTransacDet.listSz.value);
				var sZ = document.frmTransacDet.listSz.value;
			 	//var nameChkBox = "div"+i;
					
					if(val!=""){
						value = val.split("#");
						itemName = value[0];
						transId = value[1];
						indx = value[2];					
						chkId = "div"+indx;
						divId = "division"+indx;						
						
						//alert('chkId'+chkId);
						if(document.getElementById(chkId).checked == true){
							itemNme = "divNewItem"+indx;
							divtransId = "divTransId"+indx;
							transMode = "divTransMode"+indx;
							payMode = "payMode"+indx;
							newAmt = "newAmt"+indx;

							//alert(newAmt);
							//add a row to the rows collection and get a reference to the newly added row
							
							var newRow = document.all("NewContentTable").insertRow();
							var oCell = newRow.insertCell();
	
							var cou = eval(document.frmTransacDet.count.value) + 1;
	
							var oCell = newRow.insertCell();
							oCell.innerHTML = "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><input type='hidden' id='"+itemNme+"' name='"+itemNme+"'/>"+itemName+"</div>";
							 
							oCell = newRow.insertCell();
							oCell.innerHTML = "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><span class='label'><input id='"+divtransId+"' value='"+transId+"' type='hidden' name='"+divtransId+"'/></span></div>";
							
							oCell = newRow.insertCell();
							oCell.innerHTML= "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2' colspan='2'><input type='hidden' id='count' name='count' value='2'/> <input onblur='sumUp();' class='textboxOne' id='"+newAmt+"' type='text' name='"+newAmt+"' /></div>";
	
							
							oCell = newRow.insertCell();
							oCell.innerHTML= "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><select id='"+transMode+"' name='"+transMode+"' id='select' class='selectboxOne'><option selected='selected' value=''>Select One</option><option value='Partial Payment'>Partial Payment</option><option value='Accept as Full'>Accept as Full</option> <option value='Void'>Void</option></select></div>";			
	
							oCell = newRow.insertCell();
							oCell.innerHTML= "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><select id='"+payMode+"' name='"+payMode+"' id='select' class='selectboxOne' ><option selected='selected' value=''>Select One</option><option value='Check'>Check</option><option value='Visa'>Visa</option> <option value='MasterCard'>Master Card</option><option value='American Express'>Amex</option></select></div>";			
							
/*							oCell = newRow.insertCell();
							oCell.innerHTML = "<div id='"+divId+"' height='26' width='350' bgcolor='#E3E1D2'><input class='gradBtn' type='button' value='Delete' onclick='removeRow(this);' /></div>";
	
							
							oCell = newRow.insertCell();
							oCell.innerHTML = "<td  class='row'><input type='submit' class='gradBtn' value='Submit' name='validate'/></td></tr>";			
	*/						
							//document.newTestFrm.count.value = cou;
							//break;
						}
						else{
							var objVal = document.getElementById(divId);
							removeRow(objVal);
						}
					}

				//alert(document.frmstuWork.count.value);
			}
			
			//deletes the specified row from the table
			function removeRow(src)
			{
				/* src refers to the input button that was clicked.	
				   to get a reference to the containing <tr> element,
				   get the parent of the parent (in this case case <tr>)*/
							
				var oRow = src.parentElement.parentElement;		
				//alert('oRow.rowIndex' +oRow.rowIndex);
				//once the row reference is obtained, delete it passing in its rowIndex			
				document.all("NewContentTable").deleteRow(oRow.rowIndex);		
			}

function isnotIntegerChk(str){

stringIntCheck="0123456789.-";
f2=1;

for(j=0;j<str.length;j++)
{ if(stringIntCheck.indexOf(str.charAt(j))==-1)
 {f2=0;}}
if(f2==0)
{
return true;
}
  else 
  { 
  return false;
  }
}

function myvalidate()
{
	var indx = document.frmTransacDet.listSz.value;

	for(i=0;i<indx;i++){
		var amtName = "amtName"+i;
		var payType	= "payType"+i;
		var ccType = "ccType"+i;
		var actualAmount = "actualAmount"+i;
		
		if(document.getElementById(amtName).value!=""){
			if(isnotIntegerChk(document.getElementById(amtName).value)){
				alert("Enter valid amount");
				document.getElementById(amtName).focus();
				return false;
			}
                    if(document.getElementById(amtName).value>0){
			if(document.getElementById(payType).value == "" || document.getElementById(payType).value.indexOf(" ")==0){
				alert("Transaction mode cannot be Empty");
				document.getElementById(payType).focus();
				return false;
			}
			
			if(document.getElementById(ccType).value == "" || document.getElementById(ccType).value.indexOf(" ")==0){
				alert("Payment Type cannot be Empty");
				document.getElementById(ccType).focus();
				return false;
			}		
                     }
		}
//Acutal Amount 		
		if(document.getElementById(actualAmount).value<0){
			if(document.getElementById(ccType).value == "" || document.getElementById(ccType).value.indexOf(" ")==0){
				alert("Payment Type cannot be Empty");
				document.getElementById(ccType).focus();
				return false;
			}		
		}
		chkDiv = "div"+(i+1);
		
		//alert('chkDiv  '+chkDiv);

		index = chkDiv.length;
		initPos = 0;
		endPos = index;
		for(j=0;j<index;j++){
			pos = IsNumeric(chkDiv.charAt(j));
			if(pos==true){
				initPos = j;
			}
		}
		
		ind = chkDiv.substring(initPos,endPos);

		if(document.getElementById(chkDiv).checked==true){	
		
			itemNme = "divNewItem"+ind;
			divtransId = "divTransId"+ind;
			transMode = "divTransMode"+ind;
			payMode = "payMode"+ind;
			newAmt = "newAmt"+ind;
			
			if(document.getElementById(newAmt).value!=""){
					if(isnotIntegerChk(document.getElementById(newAmt).value)){
						alert("Enter valid amount");
						document.getElementById(newAmt).focus();
						return false;
					}
					if(document.getElementById(newAmt).value>0){
						if(document.getElementById(transMode).value == "" || document.getElementById(transMode).value.indexOf(" ")==0){
							alert("Payment Type cannot be Empty");
							document.getElementById(transMode).focus();
							return false;
						}
					
						if(document.getElementById(payMode).value == "" || document.getElementById(payMode).value.indexOf(" ")==0){
							alert("Transaction mode cannot be Empty");
							document.getElementById(payMode).focus();
							return false;
						}		
				  }
				}
				
			}	
		}
	if(Number(document.frmTransacDet.totalAmt.value) > Number(document.frmTransacDet.amount.value)){
			alert('Amount Reconciled exceeds the actual amount to be paid');
			return false;		
	}
	if(!document.getElementById('NsfPay').disabled){
		if(Number(document.frmTransacDet.NSFAmt.value)==0){
			alert('Reverse Entry Cannot be processed as no NSF amount found');
			return false;		
		}
	}
//NSF validation
	if(Number(document.frmTransacDet.NSFAmt.value)>0){
		if(!document.getElementById('NsfPay').disabled){
			if(Number(document.frmTransacDet.NSFAmt.value) !=(document.frmTransacDet.revAmt.value)){
				alert('NSF Amount is not same as Check Amount');
				return false;
			}
		}
	}
	//return false;
//End validation
	}

function IsNumeric(sText){
   var ValidChars = "0123456789.";
   var IsNumber=true;
   var Char;
    if (ValidChars.indexOf(sText) == -1) 
         {
         IsNumber = false;
         }
   return IsNumber;
}
