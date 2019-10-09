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
//--------------------------------Calendar function---------------------------------------
function popupCal(val){
     
    if(val==1)
         document.myform.issueType_sel.value = "issueType_sel";
		 
	  if(val==2)
         document.myform.advType_sel.value = "advType_sel";
		
	 	
	  if(val==1)
         document.myform.issueType_sel.value = "issueType_sel";
		 val = window.open("javascripts/Calendar.jsp?sDate=" + val ,'Calendar','width=170,height=190,menubar=no,toolbar=no,status=no,resizable=no,scrollbars=no,top=400,left=150');
      if(val == null){
         alert("Plz Enable Site Popup Allowed");
		
      }
}

function myvalidate(){




//==================================== For Meetings Type Category ==============================

	if(document.myform.eventRegMaster_sel.selectedIndex==0){
		alert("Please select the Meetings Type Category.");
		document.myform.eventRegMaster_sel.focus();
		return false;
	}
	
//==================================== Registration Date ==============================

if(document.myform.issueType_sel.value==""){
		alert("Enter Registration Date.");
		document.myform.issueType_sel.focus();
		return false;
	}

if(!(document.myform.issueType_sel.value=="")){
	
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

	if(!(document.myform.issueType_sel.value=="")){
		strdate=document.myform.issueType_sel.value;
		mm = Number(strdate.substring(0,2));
		dd = Number(strdate.substring(3,5));
		yyyy=(strdate.substring(6,document.myform.issueType_sel.value.length));
		yyyy1=(Number(yyyy.length));

			if(yyyy<nowYear){
				alert("Enter Valid Registration Date.");
				document.myform.issueType_sel.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm<nowMonth)){
				alert("Enter Valid Registration Date.");
				document.myform.issueType_sel.focus();
				return false;
			}

			if (mm>12){
				alert("Enter Valid Registration Date.");
				document.myform.issueType_sel.focus();
				return false;
			}

			if((yyyy==nowYear)&&(dd<nowDate)&&(mm==nowMonth)){
				alert("Enter Valid Registration Date.");
				document.myform.issueType_sel.focus();
				return false;
			}

			else if(dd>31){
				alert("Enter Valid Registration Date.");
				document.myform.issueType_sel.focus();
				return false;
			}
		}
	}
//-------------------------------------Meeting Date---------------------------------------

	if(document.myform.advType_sel.value==""){
		alert("Select Meeting Date.");
		document.myform.advType_sel.focus();
		return false;
	}

	if(!(document.myform.advType_sel.value=="")){
		strdate1=document.myform.advType_sel.value;
		mm1 = Number(strdate1.substring(0,2));
		dd1 = Number(strdate1.substring(3,5));
		yyy=(strdate1.substring(6,document.myform.advType_sel.value.length));
		yyy1=(Number(yyy.length));

			if(yyy<nowYear){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}

			if((yyy1==nowYear)&&(mm1<nowMonth)){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}

			if (mm1>12){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}

			if((dd1<nowDate)&&(mm1==nowMonth)){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}

			else if(dd1>31){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}
		}


	if(!(document.myform.advType_sel.value=="")){
		
		strdate2=document.myform.advType_sel.value;
		mm2 = Number(strdate2.substring(0,2));
		dd2 = Number(strdate2.substring(3,5));
		yy = Number(strdate2.substring(6,document.myform.advType_sel.value.length));
	
		strdate3=document.myform.issueType_sel.value;
		mm3 = Number(strdate3.substring(0,2));
		dd3 = Number(strdate3.substring(3,5));
		y = Number(strdate3.substring(6,document.myform.issueType_sel.value.length));
		
			if(yy<y){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}
			
			if((yy==y)&&(mm2<mm3)){
				alert("Enter Valid Meeting Date.");
				document.myform.advType_sel.focus();
				return false;
			}

			if((yy==y)&&(mm2==mm3)&&(dd2<dd3)){
				alert("Enter Valid Meeting Date..");
				document.myform.advType_sel.focus();
				return false;
			}
		}


//==================================== No. Of Days ==============================

if(document.myform.select7.selectedIndex==0){
		alert("Please select Number of Days.");
		document.myform.select7.focus();
		return false;
	}


	return true;
}

