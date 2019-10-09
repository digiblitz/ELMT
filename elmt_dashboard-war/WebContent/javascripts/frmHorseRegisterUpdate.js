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
//========================== function for character validation ================================
function isNameAndCity(str){
	check="`~!@#$%^&*()_+=|{}[];:1234567890,/<>?"+"\\"+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}
//=======================function for horse name======================
function isHorseName(str){
	check="`~!@#$%^*()_+=|{}[];:/<>?"+"\\"+"\""+"\"";
	f1=1;
	for(j=0;j<str.length;j++){
		if(check.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

//======================== function for two decimal points ======================================
function isnotValidDecimal(str){
	if((str.indexOf("."))!=-1){
		fr1=str.indexOf('.');
		mm = (str.substring(fr1,str.length));
		strnum=(Number(mm.length));
		if(strnum>3){
			return true;
		}
		else{
			return false;
		}
	}
}
//======================= function for Alpha Numeric ============================================
function isnotAlphaNumeric(str){
	stringAlphaNumCheck="!@#$%^&*()_+|<>?/=-~.,;:][{}"+"\\"+"\'";
	f3=1;
	for(j=0;j<str.length;j++){
		if(stringAlphaNumCheck.indexOf(str.charAt(j))!=-1){
			f3=0;
		}
	}
	if(f3==0){
		return true;
	}
	else{
		return false;
	}
}

function myValidate(){

	stringCheck="!@#$%^&*()_+`|<>?/=-~.,0123456789"+"\\";
	stringCheck2="!@#$%^&*()_+`|<>?/=-~.,"+"\\";

//================================ For Horse Color ===========================
	if(document.myform.colorSelectId.selectedIndex==0){
		alert("Please select Horse Color.");
		document.myform.colorSelectId.focus();
		return false;
	}

//============================== For Horse Gender ============================
	if ( document.myform.genderSelectId.value==""){
		alert ( "Please select Horse Gender");
		document.myform.genderSelectId.focus();
		return false;
	}
//================================ For Horse Height ====================================
	if((document.myform.height_id.value.indexOf('  ')!=-1)||(document.myform.height_id.value.indexOf(' ')==0)){
		alert("Enter valid Horse Height in the Horse Description");
		document.myform.height_id.focus();
		return false;
	}
	if(document.myform.height_id.value.length>20){
		alert("Horse Height should not exceed the maximum of 20 digits");
		document.myform.height_id.focus();
		return false;
	}
//===================================== For Year Foaled ============================
	today=new Date();
	var thisyear = today.getFullYear();
	if(document.myform.foaled_id.value!=""){
		if(!Number(document.myform.foaled_id.value)){
			alert("Enter numbers in the Year Foaled field.");
			document.myform.foaled_id.focus();
			return false;
		}
		if(document.myform.foaled_id.value.length!=4){
			alert("Enter valid year in the Year Foaled field.");
			document.myform.foaled_id.focus();
			return false;
		}
	}
	
	if(document.myform.foaled_id.value>thisyear){
		alert("Enter a valid year in year foaled field.");
		document.myform.foaled_id.focus();
		return false;
	}
	
	if(Number(document.myform.foaled_id.value)){
		curntAge = Number(thisyear)- Number(document.myform.foaled_id.value);
		
		var horseVal = document.myform.membertype.value;
		//alert("horseVal"+horseVal);
		var FEHMin = document.myform.FEHMin.value;
		var FEHMax = document.myform.FEHMax.value;
		var YEHMin = document.myform.YEHMin.value;
		var YEHMax = document.myform.YEHMax.value;

		//var splitVal = horseVal.split('#');
		if(horseVal=="Future Event Horse"){
			if(Number(curntAge)==0){
				var alrtMsg = "By rule, your horse will be turning 1 year old on January 1st of the upcoming year,\n"+
							  "and will be eligible to enter Future Event Horse competitions only by upcoming year";
				alert(alrtMsg);
			}
			if(Number(curntAge) >= Number(FEHMin) && Number(curntAge) < Number(FEHMax)){
				//alert('Eligible');
				//return true;
			}
			if(Number(curntAge) == Number(FEHMax)){
				var alrtMsg = "By rule, your horse will be turning 4 years old on January 1st of the upcoming year,\n "+
							  "and will be ineligible to enter Future Event Horse competitions. If you will not be \n"+
							  "competing this horse in FEH competition prior to November 30th of this year, you should \n"+
							  "register this horse at the Young Event Horse Level or above.";
				alert(alrtMsg);
				//return false;
			}
			if(Number(curntAge)>Number(FEHMax)){
				alert('By rule, horses under the age of 4 are only eligible for the Future Event Horse competitions.');
				return false;
			}
		}
		else if(horseVal=="Young Event Horse"){
			if(Number(curntAge)<Number(YEHMin)){
				//var alrtMsg2 = "By rule, horses under the age of 4 are only eligible for the Future Event Horse competitions.";
				var alrtMsg1 = "By rule, horses competing at the Young Event Horse level must be at least 4 years old.\n"+
								"If you will be competing this horse prior to November 30th of this year,\n"+
								"it can only be in Future Event Horse level competitions.";
				alert(alrtMsg1);
				//alert(alrtMsg2);
				//return true;
			}
			if(Number(curntAge) >= Number(YEHMin )&& Number(curntAge) < Number(YEHMax)){
				//alert('Eligible');
				//return true;
			}
			if(Number(curntAge) == Number(YEHMax)){
				var alrtMsg = "By rule, your horse will be 6 years old on January 1st of the upcoming year, and will be ineligible "+
							  "to enter Young Event Horse level competitions. If you will not be competing this horse in YEH competition "+
							  "prior to November 30th of this year, you should register this horse at the Limited level or above.";
				alert(alrtMsg);
				//return true;
			}
			if(Number(curntAge)>Number(YEHMax)){
				alert('Age of the horse is ineligible to compete for the level you had selected');
				return false;
			}
		}
		else{
			//alert(horseVal);
		}
	}


//============================== For Country Origin ================================
	if((document.myform.countryOrigin.value.indexOf('  ')!=-1)||(document.myform.countryOrigin.value.indexOf(' ')==0)){
		alert("Enter valid Country of Origin in the Horse Description");
		document.myform.countryOrigin.focus();
		return false;
	}
	if(document.myform.countryOrigin.value.length>80){
		alert("Country of Origin in the Horse Description should not exceed 80 characters");
		document.myform.countryOrigin.focus();
		return false;
	}
	if(isNameAndCity(document.myform.countryOrigin.value)){
		alert("Enter a valid Country of Origin in the Horse Description");
		document.myform.countryOrigin.focus();
		return false;
	}

//------------for Sire Name of Horse Description --
	if((document.myform.sireName.value.indexOf('  ')!=-1)||(document.myform.sireName.value.indexOf(' ')==0)){
		alert("Enter a valid name in the Sire Name field");
		document.myform.sireName.focus();
		return false;
	}
	if(document.myform.sireName.value.length>80){
		alert("Sire Name field should not exceed 80 characters");
		document.myform.sireName.focus();
		return false;
	}
	if(isHorseName(document.myform.sireName.value)){
		alert("Enter a valid name in the Sire Name field");
		document.myform.sireName.focus();
		return false;
	}

//------------for DamName in Horse Description --
	if((document.myform.damName.value.indexOf('  ')!=-1)||(document.myform.damName.value.indexOf(' ')==0)){
		alert("Enter a valid name in the Dam Name field");
		document.myform.damName.focus();
		return false;
	}
	if(document.myform.damName.value.length>80){
		alert("Dam Name in the Horse Description should not exceed 80 characters");
		document.myform.damName.focus();
		return false;
	}
	if(isHorseName(document.myform.damName.value)){
		alert("Enter a valid Dam Name in the Horse Description");
		document.myform.damName.focus();
		return false;
	}
//------------for Imported From in Horse Description --
	if((document.myform.impFrm_id.value.indexOf('  ')!=-1)||(document.myform.impFrm_id.value.indexOf(' ')==0)){
		alert("Enter valid Imported From in the Horse Description");
		document.myform.impFrm_id.focus();
		return false;
	}
	if(document.myform.impFrm_id.value.length>80){
		alert("Imported From in the Horse Description should not exceed 80 characters");
		document.myform.impFrm_id.focus();
		return false;
	}
	if(isNameAndCity(document.myform.impFrm_id.value)){
		alert("Enter valid Imported From in the Horse Description");
		document.myform.impFrm_id.focus();
		return false;
	}
//------------for Date Imported

if(!(document.myform.impDate_id.value=="")){
		var todayDate=new Date();
		var nowDate=todayDate.getDate();
		var nowYear=todayDate.getFullYear();
		var nowMonth1=todayDate.getMonth();
		var nowMonth=1+nowMonth1;
		
		if(nowDate<10){
			nowDate="0"+nowDate;
		}
	
		if(nowMonth<10){
			nowMonth="0"+nowMonth;
		}
	
		if(!(document.myform.impDate_id.value=="")){
			strdate=document.myform.impDate_id.value;
			mm = Number(strdate.substring(0,2));
			dd = Number(strdate.substring(3,5));
			yyyy=(strdate.substring(6,document.myform.impDate_id.value.length));
			yyyy1=(Number(yyyy.length));
	
			if(yyyy>nowYear){
				alert("Enter valid Date Imported in the Horse Description");
				document.myform.impDate_id.focus();
				return false;
			}

			if((yyyy==nowYear)&&(mm>nowMonth)){
				alert("Enter valid Date Imported in the Horse Description");
				document.myform.impDate_id.focus();
				return false;
			}
			if((yyyy==nowYear)&&(dd>nowDate)&&(mm==nowMonth)){
				alert("Enter valid Date Imported in the Horse Description");
				document.myform.impDate_id.focus();
				return false;
			}
		}
	}

 
//-----------for Foreign Grade of Horse Description--
if((document.myform.foreignGrd_id.value.indexOf('  ')!=-1)||(document.myform.foreignGrd_id.value.indexOf(' ')==0)){
		alert("Enter valid Foreign Grade");
		document.myform.foreignGrd_id.focus();
		return false;
	}
	if(isnotAlphaNumeric(document.myform.foreignGrd_id.value)){
		alert("Enter a valid Foreign Grade ");
		document.myform.foreignGrd_id.focus();
		return false;
	}
	if(document.myform.foreignGrd_id.value.length>20){
		alert("Foreign Grade should not exceed 20 characters");
		document.myform.foreignGrd_id.focus();
		return false;
	}

//---------for Foreign Points in HorseDescription------------------------------------
	if((document.myform.foreignPnt_id.value.indexOf('  ')!=-1)||(document.myform.foreignPnt_id.value.indexOf(' ')==0)){
		alert("Enter valid Foreign Points");
		document.myform.foreignPnt_id.focus();
		return false;
	}
	if((document.myform.foreignPnt_id.value!="")){
		if(!Number(document.myform.foreignPnt_id.value)){
			alert("Enter valid number in the Foreign Points of the Horse Description");
			document.myform.foreignPnt_id.focus();
			return false;
		}
	}
	if(isnotValidDecimal(document.myform.foreignPnt_id.value)){
		alert("Enter  valid number in the Foreign Points of the Horse Description");
		document.myform.foreignPnt_id.focus();
		return false;
	}

	return true;
}
