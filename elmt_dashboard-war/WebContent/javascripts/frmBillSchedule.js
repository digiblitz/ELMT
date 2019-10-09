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

function showLevels(chkBxNam,divId){
		if(document.frmBillingOpt.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
				
function showHide(chkBxNam,divId){
		if(document.frmBillingOpt.elements[chkBxNam].checked == true){
				document.getElementById(divId).style.display = "block";	
		}else {
				document.getElementById(divId).style.display = "none";	
		} 
}
function enabDisab(chkBxId,txtBxId){
	  
		if(document.frmBillingOpt.elements[chkBxId].checked == true){
				document.frmBillingOpt.elements[txtBxId].disabled = false;	
		}else {
				document.frmBillingOpt.elements[txtBxId].disabled = "disabled";	
		} 
}

function enableRad(radBtnId,txtBxId){
		if(document.frmBillingOpt.elements[radBtnId].checked == true){
				document.frmBillingOpt.elements[txtBxId].disabled = false;	
		}else{
				document.frmBillingOpt.elements[txtBxId].disabled = "disabled";	
		} 
}
function disable(radBtnNewId,txtBxId){
		if(document.frmBillingOpt.elements[radBtnNewId].checked == true){
				document.frmBillingOpt.elements[txtBxId].disabled = "disabled";	
		}
}	
		
function showHideRadio(radioNam,divId,radVal){
		if(document.frmBillingOpt.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "none";	
		}else {
				document.getElementById(divId).style.display = "block";	
		} 
}
//===================================================================================
function manualClear(){
	document.frmSponsBillingManual.manualAmount.disabled=false;
	document.frmSponsBillingManual.manualDate.disabled=false;
	document.frmSponsBillingManual.recurringAmount.disabled=true;
	document.frmSponsBillingManual.recurringFrequency.disabled=true;
	document.frmSponsBillingManual.recurringBillDate.disabled=true;
	document.frmSponsBillingManual.specificAmount.disabled=true;
	document.frmSponsBillingManual.specificBillDate.disabled=true;
	document.frmSponsBillingManual.specificAmount1.disabled=true;
	document.frmSponsBillingManual.specificBillDate1.disabled=true;

	document.frmSponsBillingManual.recurringAmount.value="";
	document.frmSponsBillingManual.recurringFrequency.value="";
	document.frmSponsBillingManual.recurringBillDate.value="";
	document.frmSponsBillingManual.specificAmount.value="";
	document.frmSponsBillingManual.specificBillDate.value="";
	document.frmSponsBillingManual.specificAmount1.value="";
	document.frmSponsBillingManual.specificBillDate1.value="";

}

function reccuringClear(){
	document.frmSponsBillingManual.manualAmount.disabled=true;
	document.frmSponsBillingManual.manualDate.disabled=true;
	document.frmSponsBillingManual.recurringAmount.disabled=false;
	document.frmSponsBillingManual.recurringFrequency.disabled=false;
	document.frmSponsBillingManual.specificAmount.disabled=true;
	document.frmSponsBillingManual.specificBillDate.disabled=true;
	document.frmSponsBillingManual.specificAmount1.disabled=true;
	document.frmSponsBillingManual.specificBillDate1.disabled=true;

	document.frmSponsBillingManual.manualAmount.value="";
	document.frmSponsBillingManual.manualDate.value="";
	document.frmSponsBillingManual.specificAmount.value="";
	document.frmSponsBillingManual.specificBillDate.value="";
	document.frmSponsBillingManual.specificAmount1.value="";
	document.frmSponsBillingManual.specificBillDate1.value="";
	
}
	
function specificClear(){
	document.frmSponsBillingManual.manualAmount.disabled=true;
	document.frmSponsBillingManual.manualDate.disabled=true;
	document.frmSponsBillingManual.recurringAmount.disabled=true;
	document.frmSponsBillingManual.recurringFrequency.disabled=true;
	document.frmSponsBillingManual.recurringBillDate.disabled=true;
	document.frmSponsBillingManual.specificAmount.disabled=false;
	document.frmSponsBillingManual.specificBillDate.disabled=false;
	document.frmSponsBillingManual.specificAmount1.disabled=false;
	document.frmSponsBillingManual.specificBillDate1.disabled=false;

	document.frmSponsBillingManual.manualAmount.value="";
	document.frmSponsBillingManual.manualDate.value="";
	document.frmSponsBillingManual.recurringAmount.value="";
	document.frmSponsBillingManual.recurringFrequency.value="";
	document.frmSponsBillingManual.recurringBillDate.value="";

}
function popupCal(val) {
      if(val==1)
	      frmSponsBillingManual.manualDate.value = "StartDate1";

      if(val==2)
         frmSponsBillingManual.resurringBillDate.value = "StartDate1";
		 
     if(val==3)
	      frmSponsBillingManual.specificBillDate.value = "StartDate1";

      if(val==4)
         frmSponsBillingManual.specificBillDate1.value = "StartDate1";

      val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null) {
         alert("Plz Enable Site Popup Allowed");
      }
}
function frmBillingCheck(){
	chosen="";
	len = document.frmSponsBillingManual.bs1.length ;
	for(i=0;i<len;i++){
		if(document.frmSponsBillingManual.bs1[i].checked){
			chosen= document.frmSponsBillingManual.bs1[i].value;
		}
	}
	if(chosen==""){
		alert("Check any of the Billing Option.");
		return false;
	}

	if(chosen=="Manual"){
		if(document.frmSponsBillingManual.manualAmount.value==""){
			alert("The Manual Amount Field can't be blank");
			document.frmSponsBillingManual.manualAmount.focus();
			return false;
		}

		if(!Number(document.frmSponsBillingManual.manualAmount.value)){
			alert("Enter valid Number in Manual Amount Field");
			document.frmSponsBillingManual.manualAmount.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.manualAmount.value=="")){
			if((document.frmSponsBillingManual.manualAmount.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.manualAmount.value;
				fstr1=document.frmSponsBillingManual.manualAmount.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.manualAmount.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Manual Amount is Not Valid.");
					document.frmSponsBillingManual.manualAmount.focus();
					return false;
				}
			}
		}
		
		if(document.frmSponsBillingManual.manualDate.value==""){
			alert("Select Manual Bill On Date.");
			document.frmSponsBillingManual.manualDate.focus();
			return false;
		}	

		if(!(document.frmSponsBillingManual.manualDate.value=="")){
			var todayDate=new Date();
			var nowDate=todayDate.getDate();
			var nowYear=todayDate.getYear();
			var nowMonth1=todayDate.getMonth();
			var nowMonth=1+nowMonth1;
		
			if(nowDate<10){
				nowDate="0"+nowDate;
			}
	
			if(nowMonth<10){
				nowMonth="0"+nowMonth;
			}

			if(!(document.frmSponsBillingManual.manualDate.value=="")){
				strdate=document.frmSponsBillingManual.manualDate.value;
				mm = Number(strdate.substring(0,2));
				dd = Number(strdate.substring(3,5));
				yyyy=(strdate.substring(6,document.frmSponsBillingManual.manualDate.value.length));
		
				if(yyyy<nowYear){
					alert("Enter valid Date in the Manual Bill On Date.");
					document.frmSponsBillingManual.manualDate.focus();
					return false;
				}

				if((yyyy==nowYear)&&(mm<nowMonth)){
					alert("Enter valid Date in the Manual Bill On Date.");
					document.frmSponsBillingManual.manualDate.focus();
					return false;
				}

				if((dd<nowDate)&&(mm==nowMonth)){
					alert("Enter valid date in the Manual Bill On Date.");
					document.frmSponsBillingManual.manualDate.focus();
					return false;
				}

				else if(dd>31){
					alert("Enter valid date in the Manual Bill On Date.");
					document.frmSponsBillingManual.manualDate.focus();
					return false;
				}
			}
		}
	}

	if (chosen=="Recurring"){
		if(document.frmSponsBillingManual.recurringAmount.value==""){
			alert("The Recurring Amount Field Can't Be Blank");
			document.frmSponsBillingManual.recurringAmount.focus();
			return false;
		}
	
		if(!Number(document.frmSponsBillingManual.recurringAmount.value)){
			alert("Enter valid Number in Recurring Amount Field");
			document.frmSponsBillingManual.recurringAmount.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.recurringAmount.value=="")){
			if((document.frmSponsBillingManual.recurringAmount.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.recurringAmount.value;
				fstr1=document.frmSponsBillingManual.recurringAmount.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.recurringAmount.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Recurring Amount is Not Valid.");
					document.frmSponsBillingManual.recurringAmount.focus();
					return false;
				}
			}
		}
			
		if ( document.frmSponsBillingManual.recurringFrequency.selectedIndex == 0 ){
			alert ( "Please select a Frequency For Recurring Billing Schedule." );
			document.frmSponsBillingManual.recurringFrequency.focus();
		   	return false;
		}

		if(document.frmSponsBillingManual.recurringBillDate.value==""){
			alert("Select Recurring Bill On Date.");
			document.frmSponsBillingManual.recurringBillDate.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.recurringBillDate.value=="")){
			var toDate=new Date();
			var noDate=toDate.getDate();
			var noYear=toDate.getYear();
			var noMonth1=toDate.getMonth();
			var noMonth=1+noMonth1;
		
			if(noDate<10){
				noDate="0"+noDate;
			}
		
			if(noMonth<10){
				noMonth="0"+noMonth;
			}
	
			if(!(document.frmSponsBillingManual.recurringBillDate.value=="")){
				strdate1=document.frmSponsBillingManual.recurringBillDate.value;
				mm1 = Number(strdate1.substring(0,2));
				dd1 = Number(strdate1.substring(3,5));
				yyy=(strdate1.substring(6,document.frmSponsBillingManual.recurringBillDate.value.length));
		
				if(yyy<noYear){
					alert("Enter valid Date in the Recurring Bill On Date.");
					document.frmSponsBillingManual.recurringBillDate.focus();
					return false;
				}

				if((yyy==noYear)&&(mm1<noMonth)){
					alert("Enter valid Date in the Recurring Bill On Date.");
					document.frmSponsBillingManual.recurringBillDate.focus();
					return false;
				}

				if((dd1<noDate)&&(mm==noMonth)){
					alert("Enter valid date in the Recurring Bill On Date.");
					document.frmSponsBillingManual.recurringBillDate.focus();
					return false;
				}

				else if(dd1>31){
					alert("Enter valid date in the Recurring Bill On Date.");
					document.frmSponsBillingManual.recurringBillDate.focus();
					return false;
				}
			}
		}
	}
	if (chosen=="Specific Billing"){
		if(document.frmSponsBillingManual.specificAmount.value==""){
			alert("The Specific Amount Field Can't Be Blank");
			document.frmSponsBillingManual.specificAmount.focus();
			return false;
		}

		if(!Number(document.frmSponsBillingManual.specificAmount.value)){
			alert("Enter valid Number in Specific Amount Field");
			document.frmSponsBillingManual.specificAmount.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificAmount.value=="")){
			if((document.frmSponsBillingManual.specificAmount.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.specificAmount.value;
				fstr1=document.frmSponsBillingManual.specificAmount.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.specificAmount.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Specific Amount is Not Valid.");
					document.frmSponsBillingManual.specificAmount.focus();
					return false;
				}
			}
		}
		if(document.frmSponsBillingManual.specificBillDate.value==""){
			alert("Select Specific Bill On Date.");
			document.frmSponsBillingManual.specificBillDate.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificBillDate.value=="")){
			var tDate=new Date();
			var nDate=tDate.getDate();
			var nYear=tDate.getYear();
			var nMonth1=tDate.getMonth();
			var nMonth=1+nMonth1;
		
			if(nDate<10){
				nDate="0"+nDate;
			}
	
			if(nMonth<10){
				nMonth="0"+nMonth;
			}

			if(!(document.frmSponsBillingManual.specificBillDate.value=="")){
				strdate2=document.frmSponsBillingManual.specificBillDate.value;
				mm2 = Number(strdate2.substring(0,2));
				dd2 = Number(strdate2.substring(3,5));
				yy=(strdate2.substring(6,document.frmSponsBillingManual.specificBillDate.value.length));
		
				if(yy<nYear){
					alert("Enter valid Date in Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate.focus();
					return false;
				}

				if((yy==nYear)&&(mm2<nMonth)){
					alert("Enter valid Date in the Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate.focus();
					return false;
				}

				if((dd2<nDate)&&(mm2==nMonth)&&(yy==nYear)){
					alert("Enter valid date in the Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate.focus();
					return false;
				}

				else if(dd2>31){
					alert("Enter valid date in the Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate.focus();
					return false;
				}
			}

		}
		if(document.frmSponsBillingManual.specificAmount1.value==""){
			alert("The Specific Amount Field Can't Be Blank");
			document.frmSponsBillingManual.specificAmount1.focus();
			return false;
		}

		if(!Number(document.frmSponsBillingManual.specificAmount1.value)){
			alert("Enter valid Number in Specific Amount Field");
			document.frmSponsBillingManual.specificAmount1.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificAmount1.value=="")){
			if((document.frmSponsBillingManual.specificAmount1.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.specificAmount1.value;
				fstr1=document.frmSponsBillingManual.specificAmount1.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.specificAmount1.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Specific Amount is Not Valid.");
					document.frmSponsBillingManual.specificAmount1.focus();
					return false;
				}
			}
		}
		if(document.frmSponsBillingManual.specificBillDate1.value==""){
			alert("Select Specific Bill On Date.");
			document.frmSponsBillingManual.specificBillDate1.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificBillDate1.value=="")){
			var t1Date=new Date();
			var n1Date=t1Date.getDate();
			var n1Year=t1Date.getYear();
			var n1Month1=t1Date.getMonth();
			var n1Month=1+n1Month1;
		
			if(n1Date<10){
				n1Date="0"+n1Date;
			}
	
			if(n1Month<10){
				n1Month="0"+n1Month;
			}

			if(!(document.frmSponsBillingManual.specificBillDate1.value=="")){
				strdate3=document.frmSponsBillingManual.specificBillDate1.value;
				mm3 = Number(strdate3.substring(0,2));
				dd3 = Number(strdate3.substring(3,5));
				y=(strdate3.substring(6,document.frmSponsBillingManual.specificBillDate1.value.length));
		
				if(y<n1Year){
					alert("Enter valid Date in Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}

				if((y==n1Year)&&(mm3<n1Month)){
					alert("Enter valid Date in Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}

				if((dd3<n1Date)&&(mm3==n1Month)&&(y==n1Year)){
					alert("Enter valid date in Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}

				else if(dd3>31){
					alert("Enter valid date in Specific Bill On Date.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}
			}

		}

	}
	return true;
}


		
