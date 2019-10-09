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
	document.frmSponsBillingManual.specificAmount1.disabled=true;
	document.frmSponsBillingManual.specificBillDate1.disabled=true;
	document.frmSponsBillingManual.specificAmount2.disabled=true;
	document.frmSponsBillingManual.specificBillDate2.disabled=true;
	document.frmSponsBillingManual.specificAmount3.disabled=true;
	document.frmSponsBillingManual.specificBillDate3.disabled=true;
	document.frmSponsBillingManual.specificAmount4.disabled=true;
	document.frmSponsBillingManual.specificBillDate4.disabled=true;
}

function reccuringClear(){
	document.frmSponsBillingManual.manualAmount.disabled=true;
	document.frmSponsBillingManual.manualDate.disabled=true;
	document.frmSponsBillingManual.recurringAmount.disabled=false;
	document.frmSponsBillingManual.recurringFrequency.disabled=false;
	document.frmSponsBillingManual.recurringBillDate.disabled=false;
	document.frmSponsBillingManual.specificAmount1.disabled=true;
	document.frmSponsBillingManual.specificBillDate1.disabled=true;
	document.frmSponsBillingManual.specificAmount2.disabled=true;
	document.frmSponsBillingManual.specificBillDate2.disabled=true;
	document.frmSponsBillingManual.specificAmount3.disabled=true;
	document.frmSponsBillingManual.specificBillDate3.disabled=true;
	document.frmSponsBillingManual.specificAmount4.disabled=true;
	document.frmSponsBillingManual.specificBillDate4.disabled=true;
}
	
function specificClear(){
	document.frmSponsBillingManual.manualAmount.disabled=true;
	document.frmSponsBillingManual.manualDate.disabled=true;
	document.frmSponsBillingManual.recurringAmount.disabled=true;
	document.frmSponsBillingManual.recurringFrequency.disabled=true;
	document.frmSponsBillingManual.recurringBillDate.disabled=true;
	document.frmSponsBillingManual.specificAmount1.disabled=false;
	document.frmSponsBillingManual.specificBillDate1.disabled=false;
	document.frmSponsBillingManual.specificAmount2.disabled=false;
	document.frmSponsBillingManual.specificBillDate2.disabled=false;
	document.frmSponsBillingManual.specificAmount3.disabled=false;
	document.frmSponsBillingManual.specificBillDate3.disabled=false;
	document.frmSponsBillingManual.specificAmount4.disabled=false;
	document.frmSponsBillingManual.specificBillDate4.disabled=false;
}
function popupCal(val) {
		if(val==1)
		frmSponsBillingManual.manualDate.value = "StartDate1";
		
		if(val==2)
		frmSponsBillingManual.recurringBillDate.value = "StartDate1";
		
		if(val==3)
		frmSponsBillingManual.specificBillDate1.value = "StartDate1";
		
		if(val==4)
		frmSponsBillingManual.specificBillDate2.value = "StartDate1";
		
		if(val==5)
		frmSponsBillingManual.specificBillDate3.value = "StartDate1";
		
		if(val==6)
		frmSponsBillingManual.specificBillDate4.value = "StartDate1";

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

		if((document.frmSponsBillingManual.specificAmount1.value=="")&&(document.frmSponsBillingManual.specificBillDate1.value!="")){
			alert("Enter Specific Amount 1.");
			document.frmSponsBillingManual.specificAmount1.focus();
			return false;
		}
		if((document.frmSponsBillingManual.specificAmount1.value!="")&&(document.frmSponsBillingManual.specificBillDate1.value=="")){
			alert("Enter Specific Bill Date 1.");
			document.frmSponsBillingManual.specificAmount1.focus();
			return false;
		}
		
		if(document.frmSponsBillingManual.specificAmount1.value!=0){
		
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
			alert("Select Date for Specific Billing schedule one.");
			document.frmSponsBillingManual.specificBillDate1.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificBillDate1.value=="")){
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

			if(!(document.frmSponsBillingManual.specificBillDate1.value=="")){
				strdate2=document.frmSponsBillingManual.specificBillDate1.value;
				mm2 = Number(strdate2.substring(0,2));
				dd2 = Number(strdate2.substring(3,5));
				yy=(strdate2.substring(6,document.frmSponsBillingManual.specificBillDate1.value.length));
		
				if(yy<nYear){
					alert("Enter valid Date in Specific Billing schedule one.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}

				if((yy==nYear)&&(mm2<nMonth)){
					alert("Enter valid Date in Specific Billing schedule one.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}

				if((dd2<=nDate)&&(mm2==nMonth)&&(yy==nYear)){
					alert("Enter valid Date in Specific Billing schedule one.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}

				else if(dd2>31){
					alert("Enter valid Date in Specific Billing schedule one.");
					document.frmSponsBillingManual.specificBillDate1.focus();
					return false;
				}
			}
			}
		}
//second bill		

		if((document.frmSponsBillingManual.specificAmount2.value=="")&&(document.frmSponsBillingManual.specificBillDate2.value!="")){
			alert("Enter Specific Amount 2.");
			document.frmSponsBillingManual.specificAmount2.focus();
			return false;
		}
		if((document.frmSponsBillingManual.specificAmount2.value!="")&&(document.frmSponsBillingManual.specificBillDate2.value=="")){
			alert("Enter Specific Bill Date 2.");
			document.frmSponsBillingManual.specificAmount2.focus();
			return false;
		}

		if(document.frmSponsBillingManual.specificAmount2.value!=0){
		if(!Number(document.frmSponsBillingManual.specificAmount2.value)){
			alert("Enter valid Number in Specific Amount Field");
			document.frmSponsBillingManual.specificAmount2.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificAmount2.value=="")){
			if((document.frmSponsBillingManual.specificAmount2.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.specificAmount2.value;
				fstr1=document.frmSponsBillingManual.specificAmount2.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.specificAmount2.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Specific Amount is Not Valid.");
					document.frmSponsBillingManual.specificAmount2.focus();
					return false;
				}
			}
		}
		if(document.frmSponsBillingManual.specificBillDate2.value==""){
			alert("Select Date for Specific Billing schedule one.");
			document.frmSponsBillingManual.specificBillDate2.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificBillDate2.value=="")){
			var t1Date=document.frmSponsBillingManual.specificBillDate1.value;
			var n1Date=t1Date.substring(3,5);
			var n1Year=t1Date.substring(6,10);
			var n1Month=t1Date.substring(0,2);
			
			
		
			if(n1Date<10){
				n1Date="0"+n1Date;
			}
	
			if(n1Month<10){
				n1Month="0"+n1Month;
			}

			if(!(document.frmSponsBillingManual.specificBillDate2.value=="")){
				strdate3=document.frmSponsBillingManual.specificBillDate2.value;
				mm3 = Number(strdate3.substring(0,2));
				
				dd3 = Number(strdate3.substring(3,5));
				y=(strdate3.substring(6,document.frmSponsBillingManual.specificBillDate2.value.length));
				if(y<n1Year){
					alert("Enter Valid Date for Specific Billing schedule two.");
					document.frmSponsBillingManual.specificBillDate2.focus();
					return false;
				}

				if((y==n1Year)&&(mm3<n1Month)){
					 
					alert("Enter Valid Date for Specific Billing schedule two.");
					document.frmSponsBillingManual.specificBillDate2.focus();
					return false;
				}

				if((dd3<=n1Date)&&(mm3==n1Month)&&(y==n1Year)){
					alert("Enter Valid Date for Specific Billing schedule two.");
					document.frmSponsBillingManual.specificBillDate2.focus();
					return false;
				}

				else if(dd3>31){
					alert("Enter Valid Date for Specific Billing schedule two.");
					document.frmSponsBillingManual.specificBillDate2.focus();
					return false;
				}
			}

		}
		}
		//third bill
		if((document.frmSponsBillingManual.specificAmount3.value=="")&&(document.frmSponsBillingManual.specificBillDate3.value!="")){
			alert("Enter Specific Amount 3.");
			document.frmSponsBillingManual.specificAmount3.focus();
			return false;
		}
		if((document.frmSponsBillingManual.specificAmount3.value!="")&&(document.frmSponsBillingManual.specificBillDate3.value=="")){
			alert("Enter Specific Bill Date 3.");
			document.frmSponsBillingManual.specificAmount3.focus();
			return false;
		}
		
		
		if(document.frmSponsBillingManual.specificAmount3.value!=0){
		if(!Number(document.frmSponsBillingManual.specificAmount3.value)){
			alert("Enter valid Number in Specific Amount3 Field");
			document.frmSponsBillingManual.specificAmount3.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificAmount3.value=="")){
			if((document.frmSponsBillingManual.specificAmount3.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.specificAmount3.value;
				fstr1=document.frmSponsBillingManual.specificAmount3.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.specificAmount3.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Specific Amount is Not Valid.");
					document.frmSponsBillingManual.specificAmount3.focus();
					return false;
				}
			}
		}
		if(document.frmSponsBillingManual.specificBillDate3.value==""){
			alert("Select Specific Bill On Date.");
			document.frmSponsBillingManual.specificBillDate3.focus();
			return false;
		}
		if(!(document.frmSponsBillingManual.specificBillDate3.value=="")){
			var t1Date=document.frmSponsBillingManual.specificBillDate2.value;
			var n1Date=t1Date.substring(3,5);
			var n1Year=t1Date.substring(6,10);
			var n1Month=t1Date.substring(0,2);
			
		
			if(n1Date<10){
				n1Date="0"+n1Date;
			}
	
			if(n1Month<10){
				n1Month="0"+n1Month;
			}

			if(!(document.frmSponsBillingManual.specificBillDate3.value=="")){
				strdate3=document.frmSponsBillingManual.specificBillDate3.value;
				mm3 = Number(strdate3.substring(0,2));
				dd3 = Number(strdate3.substring(3,5));
				y=(strdate3.substring(6,document.frmSponsBillingManual.specificBillDate3.value.length));
		
				if(y<n1Year){
					alert("Enter Valid Date for Specific Billing schedule three.");
					document.frmSponsBillingManual.specificBillDate3.focus();
					return false;
				}

				if((y==n1Year)&&(mm3<n1Month)){
					alert("Enter Valid Date for Specific Billing schedule three.");
					document.frmSponsBillingManual.specificBillDate3.focus();
					return false;
				}

				if((dd3<=n1Date)&&(mm3==n1Month)&&(y==n1Year)){
					alert("Enter Valid Date for Specific Billing schedule three.");
					document.frmSponsBillingManual.specificBillDate3.focus();
					return false;
				}

				else if(dd3>31){
					alert("Enter Valid Date for Specific Billing schedule three.");
					document.frmSponsBillingManual.specificBillDate3.focus();
					return false;
				}
			}

		}
		}
		//fourth billing..................
		if((document.frmSponsBillingManual.specificAmount4.value=="")&&(document.frmSponsBillingManual.specificBillDate4.value!="")){
			alert("Enter Specific Amount 4.");
			document.frmSponsBillingManual.specificAmount4.focus();
			return false;
		}
		if((document.frmSponsBillingManual.specificAmount4.value!="")&&(document.frmSponsBillingManual.specificBillDate4.value=="")){
			alert("Enter Specific Bill Date 4.");
			document.frmSponsBillingManual.specificAmount4.focus();
			return false;
		}
		
				
		if(document.frmSponsBillingManual.specificAmount4.value!=0){
		if(!Number(document.frmSponsBillingManual.specificAmount4.value)){
			alert("Enter valid Number in Specific Amount Field");
			document.frmSponsBillingManual.specificAmount4.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificAmount4.value=="")){
			if((document.frmSponsBillingManual.specificAmount4.value.indexOf('.'))!=-1){
				fstr=document.frmSponsBillingManual.specificAmount4.value;
				fstr1=document.frmSponsBillingManual.specificAmount4.value.indexOf('.');
				mm = (fstr.substring(fstr1,document.frmSponsBillingManual.specificAmount4.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Specific Amount is Not Valid.");
					document.frmSponsBillingManual.specificAmount4.focus();
					return false;
				}
			}
		}
		if(document.frmSponsBillingManual.specificBillDate4.value==""){
			alert("Select Specific Bill On Date.");
			document.frmSponsBillingManual.specificBillDate4.focus();
			return false;
		}

		if(!(document.frmSponsBillingManual.specificBillDate4.value=="")){
			var t1Date=document.frmSponsBillingManual.specificBillDate3.value;
			var n1Date=t1Date.substring(3,5);
			var n1Year=t1Date.substring(6,10);
			var n1Month=t1Date.substring(0,2);
			
		
			if(n1Date<10){
				n1Date="0"+n1Date;
			}
	
			if(n1Month<10){
				n1Month="0"+n1Month;
			}

			if(!(document.frmSponsBillingManual.specificBillDate4.value=="")){
				strdate3=document.frmSponsBillingManual.specificBillDate4.value;
				mm3 = Number(strdate3.substring(0,2));
				dd3 = Number(strdate3.substring(3,5));
				y=(strdate3.substring(6,document.frmSponsBillingManual.specificBillDate4.value.length));
		
				if(y<n1Year){
					alert("Enter Valid Date for Specific Billing schedule four.");
					document.frmSponsBillingManual.specificBillDate4.focus();
					return false;
				}

				if((y==n1Year)&&(mm3<n1Month)){
					alert("Enter Valid Date for Specific Billing schedule four.");
					document.frmSponsBillingManual.specificBillDate4.focus();
					return false;
				}

				if((dd3<=n1Date)&&(mm3==n1Month)&&(y==n1Year)){
					alert("Enter Valid Date for Specific Billing schedule four.");
					document.frmSponsBillingManual.specificBillDate4.focus();
					return false;
				}

				else if(dd3>31){
					alert("Enter Valid Date for Specific Billing schedule four.");
					document.frmSponsBillingManual.specificBillDate4.focus();
					return false;
				}
			}

		}
		}
		//alert('hi');
		var amount1 = document.frmSponsBillingManual.specificAmount1.value;
			//alert(amount1);
		var amount2 = document.frmSponsBillingManual.specificAmount2.value;
			//alert(amount2);
		var amount3 = document.frmSponsBillingManual.specificAmount3.value;
			//alert(amount3);
		var amount4 = document.frmSponsBillingManual.specificAmount4.value;
			//alert(amount4);
		var totAmt = document.frmSponsBillingManual.totalAmt.value;
			//alert(totAmt);
		var checkAmt = "";
		if(amount1=="")
			amount1=0;
		if(amount2=="")
			amount2=0;
		if(amount3=="")
			amount3=0;
		if(amount4=="")
			amount4=0;
		checkAmt = eval(amount1) + eval(amount2) + eval(amount3) + eval(amount4);
		//alert(checkAmt);
		if(checkAmt==""){
			alert("Amount cannot be nil");
			return false;
		}
		if(checkAmt > totAmt || checkAmt < totAmt) {
			alert("Amount should not succeed or preceed");
			return false;
		}
	}	
	
	return true;
}


		