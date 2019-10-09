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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcform.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmAreaProgValidate.js" type="text/javascript"></script>

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>

<script type="text/javascript">
function radio_button_checker()
{
// set var radio_choice to false
var radio_choice = false;

// Loop from zero to the one minus the number of radio button selections
for (counter = 0; counter < document.frmMyAreaRidProgram.areaMembTypId.length; counter++)
{
// If a radio button has been selected it will return true
// (If not it will return false)
if(document.frmMyAreaRidProgram.areaMembTypId[counter].checked)
radio_choice = true; 
}

return (true);
}

function format(obj, decimal) {
	//alert("obj" +obj);
 //decimal  - the number of decimals after the digit from 0 to 3
//-- Returns the passed number as a string in the xxx,xxx.xx format.
	   anynum=eval(obj);
	   divider =10;
	   switch(decimal){
			case 0:
				divider =1;
				break;
			case 1:
				divider =10;
				break;
			case 2:
				divider =100;
				break;
			default:  	 //for 3 decimal places
				divider =1000;
		}

	   workNum=Math.abs((Math.round(anynum*divider)/divider));

	   workStr=""+workNum

	   if (workStr.indexOf(".")==-1){workStr+="."}

	   dStr=workStr.substr(0,workStr.indexOf("."));dNum=dStr-0
	   pStr=workStr.substr(workStr.indexOf("."))

	   while (pStr.length-1< decimal){pStr+="0"}

	   if(pStr =='.') pStr ='';

	   //--- Adds a comma in the thousands place.    
	   /*if (dNum>=1000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000))+","+dStr.substring(dLen-3,dLen)
	   }

	   //-- Adds a comma in the millions place.
	   if (dNum>=1000000) {
		  dLen=dStr.length
		  dStr=parseInt(""+(dNum/1000000))+","+dStr.substring(dLen-7,dLen)
	   }*/
	   retval = dStr + pStr
	   //-- Put numbers in parentheses if negative.
	   if (anynum<0) {retval="("+retval+")";}

	  
	//You could include a dollar sign in the return value.
	  //retval =  "$"+retval
	  //alert("retval" + retval);
	  return retval;
 }


function fillAmt1(progAmt){
//alert(progAmt);

if(progAmt=="0"){
sum1 =0;
var sum1=0;
finalAreaAmt = format(sum1,2);
document.frmMyAreaRidProgram.totalAmount.value = finalAreaAmt;	
}else{
sum =0;
var tempAmt=document.frmMyAreaRidProgram.tempAreaAmt1.value;

var sum=progAmt-tempAmt;
finalAreaAmt = format(sum,2);
	document.frmMyAreaRidProgram.totalAmount.value=0;
	document.frmMyAreaRidProgram.totalAmount.value = finalAreaAmt;	
	}
}

function fillAmt(progAmt){
//alert(progAmt);
sum =0;
var sum=progAmt;
finalAreaAmt1 = format(sum,2);
	document.frmMyAreaRidProgram.totalAmount.value=0;
	document.frmMyAreaRidProgram.totalAmount.value = finalAreaAmt1;	
}



function showHideRadio(radioNam,divId,radVal){
	//alert(radVal);
		if(document.frmMyAreaRidProgram.elements[radioNam].value == radVal){
				document.getElementById(divId).style.display = "block";	
		}
		else {
				document.getElementById(divId).style.display = "none";	
		} 
}

function showHideAreaSection(){		
aaaa="";
aaaa=document.frmMyAreaRidProgram.tempSec.value;
if(aaaa=="2"){	
tempSection="";
tempSection= document.frmMyAreaRidProgram.cc11.value;
//alert(tempSection);
if(tempSection=="usrsec2"){
document.getElementById('img').style.display="block";
}
else if(tempSection=="adsec1"){
//alert("admin" +tempSec);
	
chosenrd="";
	lenrd = document.frmMyAreaRidProgram.r11.length ;
	
	for(ird=0;ird<lenrd;ird++){
		if(document.frmMyAreaRidProgram.r11[ird].checked){
			chosenrd= document.frmMyAreaRidProgram.r11[ird].value;
		}
	}

	if(chosenrd=="card"){
		document.getElementById('img').style.display = "block";	
	}
	else{
	document.getElementById('img').style.display = "none";	
	}	
	}
else{
document.getElementById('img').style.display = "none";
}	
}

if(aaaa=="1"){
document.getElementById('img').style.display = "none";
}
	
}

</script>
<body onload="showHideAreaSection();">
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
	<%@ include file = "../../include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
						  <tr>
							<td colspan="2" class="tblMainHead"><strong>HLC</strong> Membership: <span class="styleBoldTwo">Area Rider Program </span></td>
						  </tr>
						  <tr>
    <td colspan="2" class="tblDescrp"><p><strong>If you would  like to sign up and pay for your area rider program, please select one  program.&nbsp; Program options are based on  your zip code and birth date.</strong></p>						    </td>
						  </tr>
						<br/>
						   <tr>
							<td colspan="2" class="tblRowHead">Area Membership: </td>
							</tr>
										  
						  <tr>
							<td>
							
			<form name="frmMyAreaRidProgram" id="frmMyAreaRidProgram" action="./areaMember.do" onsubmit="return myvalidate(this);">
			<input type="hidden" name="cmd" value="insertAreaProg">
			<input type="hidden" name="inVoiceId" id="inVoiceId" value="1"/> 				
                         
									<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			
						<%
                                                String sessionInvoiceId = "1";
                                             session.setAttribute("sessionInvoiceId",sessionInvoiceId);
                                             session.setAttribute("inVoiceId1","1");
						String redirurl = "index.jsp";
					String status=(String)request.getAttribute("stat");
					if(status!=null){
				%>				  
				<br /><br />
				<p align="center">
	<center><strong><%=status%></strong></center></p>	
										  
				<p align="center"><span class="textCommon"><br />
	    <center>Do You Want To Become a Member ??</center></span></p>
    <p>
             <center><input name="button" type="button" class="gradBtn" value="Yes" onclick="location.href='./MembershipReg.do?process=reg'" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="button" type="button" class="gradBtn" value="No" onclick="location.href='./index.jsp'" /></center>						  
	<input type="hidden" name="tempSec" id="tempSec" value="1">									  	
										  
					<% }else {String purchaseMsg= (String)request.getAttribute("stat1");
					
					if(purchaseMsg!=null){				
					%>	
				  <tr>
                      <td colspan="4"><span class="asterisk"><%=purchaseMsg%></span></td>
                  </tr>
				  <tr>
						<td class="tableLeft">&nbsp;</td>
						<td width="291" class="tableRight">
	<input name="button" type="button" class="gradBtn" value="Back" onclick="location.href='<%=redirurl%>'" />&nbsp;</td>
						</tr>
						<input type="hidden" name="tempSec" id="tempSec" value="1">					
				<%		
					}}					  
	            %>
				
				
	            <%
						ArrayList areaProgList = (ArrayList)request.getAttribute("areaProgList");
						ArrayList progDetails = (ArrayList)request.getAttribute("progDetails");
						ArrayList areaProgListForUpgrdTrue = (ArrayList)request.getAttribute("areaProgListForUpgrdTrue");
						String upgradeStatus= (String)request.getAttribute("upgradeStatus");
						String areaMemTypId2= (String)request.getAttribute("areaMemTypId1");
						
						String areaName2= (String)request.getAttribute("areaName1");
						String progName2= (String)request.getAttribute("progName1");
						String progDesc2= (String)request.getAttribute("progDesc1");
						String note= (String)request.getAttribute("note");
						
						String areaMemTypId1="";
						System.out.println("progDetails in jsp" + progDetails);
						System.out.println("upgradeStatus in jsp" + upgradeStatus);
						System.out.println("areaProgList in jsp" + areaProgList);
						System.out.println("areaProgListForUpgrdTrue in jsp" + areaProgListForUpgrdTrue);
					%>
					
		<%
	if(progDetails==null && areaProgList==null && areaProgListForUpgrdTrue==null){
	//System.out.println("othProg2"+othProg2);
	
	%>
	
	<div>			
										
		<span class="styleBoldOne">No valid area's programs found for given zip code</span>
					</div>			
					
			<%}%>		
		
		      <% if(progDetails!=null && progDetails.size()==0){%>			
					<tr>
					<td class="tblMainHead" colspan="2">
					<input type="radio" name="defaultRadio" checked="checked"/>												
					<span class="styleBoldOne">Do not Join My Area Rider Program</span></td>
					</tr>
					<tr>
						<td class="tableLeft">&nbsp;</td>
						<td width="291" class="tableRight">
						<input name="button" type="button" class="gradBtn" value="Back" onclick="location.href='<%=redirurl%>'" />&nbsp;</td><input type="hidden" name="tempSec" id="tempSec" value="1">	
						</tr>		
						
				<%}else if(progDetails!=null && progDetails.size()!=0){%>	
				
					<%
						String membPrice3="";
						Iterator it = progDetails.iterator();
						int i=0;
						%>
						
						<tr>
					<td class="tblMainHead" colspan="2">
					<input type="radio" name="areaMembTypId" id="areaMembTypId" checked="checked" value="0" onclick="radio_button_checker();fillAmt(0)"/>												
					<span class="styleBoldOne">Do not Join My Area Rider Program</span></td>
					</tr>
						<%										
						while(it.hasNext()){
						i=i+1;
						String hrArray[] = (String[])it.next();					
						String areaMemTypId3=hrArray[2];
						String areaName3=hrArray[1];	
						String progName3=hrArray[6];
						String progDesc3=hrArray[3];
						String urlName3=hrArray[5];
						 membPrice3=hrArray[4];	
						String transacTypeId3=hrArray[8];
						 System.out.println("membPrice3"+membPrice3);					 											  
					%>			    		
				
					<tr>
							<td colspan="2" class="tableSpan">
											
<input type="radio" name="areaMembTypId" id="areaMembTypId" value="<%=areaMemTypId3+"#"+areaName3+"#"+progName3+"#"+transacTypeId3+"#"+membPrice3+"#"+progDesc3%>" onclick="radio_button_checker();fillAmt('<%=membPrice3%>')"/><input type="hidden" name="areaName" value="areaName3">
			<input type="hidden" name="tempSec" id="tempSec" value="2">	
				<%if(urlName3!=null){%>												
				<span class="styleBoldOne"><a href="<%=urlName3%>" target="_blank"><%=areaName3%>,&nbsp;<%=progName3%></a></span>
				<%}else{%>
				<span class="styleBoldOne"><%=areaName3%>,&nbsp;<%=progName3%></span>
				<%}%>
				<span>(<%=progDesc3%>)</span>
			
				</td>
											  </tr>				
				<%}i++;}%>	
			  								
					<%	
	
				if(areaProgList!=null && areaProgList.size()!=0 && upgradeStatus.equalsIgnoreCase("False")){
						System.out.println("areaProgList"+areaProgList);
						Iterator it = areaProgList.iterator();
						int i=0;										
						while(it.hasNext()){
						AreaProgramVO areaProgVO1 = (AreaProgramVO)it.next();
						
						 areaMemTypId1=areaProgVO1.getAreaMembTypId();
						String areaName1=areaProgVO1.getAreaName();	
						String progName1=areaProgVO1.getProgName();
						String progDesc1=areaProgVO1.getDescription();
						String urlName1=areaProgVO1.getUrl();
						String membPrice1=areaProgVO1.getAreaMembPrice();
						boolean upgrade=areaProgVO1.isUpgradable();
						
						%>
						
						<tr>
							<td colspan="2" class="tableSpan">
				         You are already signed up for 
						 
						 <%if(urlName1!=null){%>												
				<span class="styleBoldOne"><a href="<%=urlName1%>" target="_blank"><%=areaName1%>,&nbsp;<%=progName1%></a></span>
				<%}else{%>
				<span class="styleBoldOne"><%=areaName1%>,&nbsp;<%=progName1%></span>
				<%}%>						 						 
				              </td>
					</tr>
	<%
						
						}
			%>
					<tr>

						<td class="tableLeft">&nbsp;</td>
						<td width="291" class="tableRight">
						<input name="button" type="button" class="gradBtn" value="Back" onclick="location.href='<%=redirurl%>'" />&nbsp;</td><input type="hidden" name="tempSec" id="tempSec" value="1">	
						</tr>				
						
<% }else if(areaProgListForUpgrdTrue!=null && areaProgListForUpgrdTrue.size()!=0 && upgradeStatus!=null && upgradeStatus.equalsIgnoreCase("True")){
						System.out.println("areaProgListForUpgrdTrue"+areaProgListForUpgrdTrue);
						%>		
																					  
						<%
						String membPrice="";
						Iterator it = areaProgListForUpgrdTrue.iterator();
						int i=0;
						int amt=0;
						%>
						<tr>
					<td class="tblMainHead" colspan="2">
	<input type="radio" name="areaMembTypId" id="areaMembTypId" checked="checked" onclick="radio_button_checker();fillAmt1('<%=amt%>')"/>												
					<span class="styleBoldOne">Do not Join My Area Rider Program</span></td>
					</tr>
						<%										
						while(it.hasNext()){
						i=i+1;
						String hrArray[] = (String[])it.next();					
						String areaMemTypId=hrArray[2];
						String areaName=hrArray[1];	
						String progName=hrArray[6];
						String progDesc=hrArray[3];
						String urlName=hrArray[5];
						 membPrice=hrArray[4];
						 String transacTypeId=hrArray[8];	
						 System.out.println("membPrice"+membPrice);	
						 			 											  
								%>			    			
								<tr>
							<td colspan="2" class="tableSpan">						
						<%if(areaMemTypId2.equalsIgnoreCase(areaMemTypId)){%>	
							
<input type="radio" disabled="disabled" name="areaMembTypId" id="areaMembTypId"/>			
				<%if(urlName!=null){%>												
				<span class="styleBoldOne"><a href="<%=urlName%>" target="_blank"><%=areaName%>,&nbsp;<%=progName%></a></span>
				<%}else{%>
				<span class="styleBoldOne"><%=areaName%>,&nbsp;<%=progName%></span>
				<%}%>
				<span>(<%=progDesc%>)</span> - <span class="styleBoldOne"><%=note%></span>
				<input type="hidden" name="oldAreaProgram" id="oldAreaProgram" value="1">
				<input type="hidden" name="oldTransacTypId" id="oldTransacTypId" value="<%=transacTypeId%>">
				<input type="hidden" name="oldAreaPrice" id="oldAreaPrice" value="<%=membPrice%>">
				<input type="hidden" name="tempAreaAmt1" id="tempAreaAmt1" value="<%=membPrice%>">	
				<%}else{%>
	<input type="radio" name="areaMembTypId" id="areaMembTypId" value="<%=areaMemTypId+"#"+areaName+"#"+progName+"#"+transacTypeId+"#"+membPrice+"#"+progDesc%>" onclick="radio_button_checker();fillAmt1('<%=membPrice%>')"/>
	<input type="hidden" name="tempSec" id="tempSec" value="2">			
				<%if(urlName!=null){%>												
				<span class="styleBoldOne"><a href="<%=urlName%>" target="_blank"><%=areaName%>,&nbsp;<%=progName%></a></span>
				<%}else{%>
				<span class="styleBoldOne"><%=areaName%>,&nbsp;<%=progName%></span>
				<%}%>
				<span>(<%=progDesc%>)</span>
				<%}%>
				
				</td>
											  </tr>			

<%}i++;}%>

	
<%
if(progDetails!=null && progDetails.size()!=0 || areaProgListForUpgrdTrue!=null && areaProgListForUpgrdTrue.size()!=0){

if(session.getAttribute("loggedBy")!=null){

%>
				<tr>
				<td colspan="2" class="tblRowHead">Payment Information:</td>
				</tr>
							 
				  <tr>
					<td class="tableLeft"><strong>Total Amount:</strong></td>
					<td class="tableRight"> <strong>$</strong> 
					
					<input type="hidden" name="tempAmount" readonly="true" class="readOnly" size="10" value="" />
					 
	<input type="text" name="totalAmount" id="totalAmount" readonly="true" class="readOnly" size="10" value="0.00" />&nbsp; U.S. Dollars	 </td>				 
				  </tr>
				  
	<tr>
					<td class="tableLeft">
						<input type="radio" size="10" name="r11" id="r11" value="check"  onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'), hideImgDiv('img'),checkclear();cardclear();"/> Check enclosed.				 	</td>
					<td class="tableRight">
						<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'), showHideRadio('r11','chkEncAcm','card'), showImgDiv('img'), cardclear();checkclear();"/> Please charge my card.					</td>
				  </tr>			  

	<tr>
					<td colspan="2">
					<input type="hidden" name="cc11" id="cc11" value="adsec1"/>
<div id="chkEncAcm">
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
							<tr>
								<td class="tblMainHead" colspan="3">Check Details:</td>
							</tr>
							<tr>
							  <td colspan="2" class="tableSpan">
							If paying by check (payable in U.S dollars to "USEA"), please mail your payment to: 
								<br />
								<br />
								<strong>USEA
								<br />
								Horse  Registration
								<br />
								525 Old Waterford Road NW
								<br />
								Leesburg, VA 20176												    </strong><br />
								<br />
								<strong>Note:</strong><span class="styleBoldOne"> Your registration status will be pending until check is processed.		</span> <br />
						<br /></td>
						  </tr>

  
							<tr>
							  <td class="tableLeft">Check Amount:</td>
							  <td class="tableRight"><strong>$</strong>
							  <input type="text" name="chkBalAmt" id="chkBalAmt" class="textboxOne" size="16" />&nbsp; U.S. Dollars 							  </td>
							</tr>

							<tr>
							  <td class="tableLeft">Check No:</td> 
							  <td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
						<%
																		    String chkDat2="";
																		
																			java.util.Calendar c7 = java.util.Calendar.getInstance();
																			int dyChk = c7.get(Calendar.DATE);
																			int mnth = c7.get(Calendar.MONTH);
																			
																			int mnthChk = mnth+1;
																			int yrChk = c7.get(Calendar.YEAR);
																			
																			if(mnthChk<10)
																			{
																				chkDat2 = "0"+mnthChk+"/"+dyChk+"/"+yrChk;
																			}
																			else
																			{
																				
																				chkDat2 = mnthChk+"/"+dyChk+"/"+yrChk;
																			}
																			%>
<tr>
					    <td class="tableLeft">Check Date:</td>
					    <td class="tableRight">
					<input type="text" name="checkDate" id="checkDate" class="textboxOne"  size="16" value="<%=chkDat2%>" /> 	
					<a href="javascript:cal1.popup();">
					<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					<span class="asterisk">*</span></td>		
					</tr>

<tr>
<td class="tableLeft">Activation Date:</td>
<td class="tableRight"><input type="text" name="activeDate" id="activeDate" value="<%=chkDat2%>" class="textboxOne" size="16"/>

<a href="javascript:cal2.popup();">
<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
<span class="asterisk">*</span>												  </td>
</tr>	
							
													
							<tr>
							  <td class="tableLeft">Bank Name :</td>
							  <td class="tableRight">
							  <input type="text" name="bankName" id="bankName" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
							<tr>
							  <td class="tableLeft">Name on Check:</td>
							  <td class="tableRight">
							  <input type="text" name="nameCheck" id="nameCheck" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
						</table>
						</div>
   </td>
				  </tr>									
		
<tr>
				<td colspan="2">
				<div id="chrgCrdAcm">
						
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">							
							<tr>
								<td class="tblMainHead" colspan="2">Card Details:</td>
							</tr>
							
								<tr>
								<td class="tableLeft">Card Type:</td>
							  <td class="tableRight">
									<select name="ccType" id="ccType" class="selectboxOne" >
									  <option selected="selected" value="">Select One</option>
									  <option value="Visa">Visa</option>
									  <option value="MasterCard">Master Card</option>

									  <option value="Amex">AmEx</option>
									</select>
									<span class="asterisk">*</span>								</td>
							</tr>
							
							<tr>
								<td class="tableLeft">Card No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="16" />
								  <span class="asterisk">*</span></td>
							</tr>
							<tr>
								<td class="tableLeft">Card CVV No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
							  &nbsp;(See details below)							  </td>
							</tr>
						
							<tr>
								<td class="tableLeft">Print Name On Card:</td>
							  <td class="tableRight">
							  <input type="text" name="ccName" id="ccName" class="textboxOne" size="25" />
								  <span class="asterisk">*</span></td>
							</tr>
							<tr>
								<td class="tableLeft">Expiry Date:</td>
							  <td class="tableRight">
									<select name="ccExpMonth" id="ccExpMonth" class="selectboxOne">

									  <option value="" selected="selected">Month</option>
									  <option value="01">January</option>
									  <option value="02">February</option>
									  <option value="03">March</option>
									  <option value="04">April</option>
									  <option value="05">May</option>
									  <option value="06">June</option>
									  <option value="07">July</option>
									  <option value="08">August</option>
									  <option value="09">September</option>
									  <option value="10">October</option>

									  <option value="11">November</option>
									  <option value="12">December</option>
									</select>
									<select name="ccExpYear" id="ccExpYear" class="selectboxOne">
										  <option value="" selected="selected" >Year</option>
												<%
												java.util.Calendar c6 = java.util.Calendar.getInstance();
												int year5 = c6.get(Calendar.YEAR);
												System.out.println("Current Date = "+year5);
												for(int f=year5;f<=year5+15;f++) 
												{
												%>
												<option  value="<%=f%>"><%=f%></option>
												<%
												}
												%>				
									</select>
									<span class="asterisk">*</span>	
							
							</td>
							</tr>		
					<tr>
					    <td class="tableLeft">Activation Date:</td>
					    <td class="tableRight">
					<input type="text" name="activeDatVal" id="activeDatVal" class="textboxOne"  size="16" value="<%=chkDat2%>" /> 	
					<a href="javascript:cal3.popup();">
					<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					<span class="asterisk">*</span></td>		
					</tr>
					
              </table>
				</div>	
				</td>							
			</tr>

<%}else{%>
										  
		<tr>
				<td colspan="2" class="tblRowHead">Payment Information:</td>
				</tr>
				
			<tr>
					<td class="tableLeft"><strong>Total Amount:</strong></td>
					<td class="tableRight"> <strong>$</strong> 
					
					<input type="hidden" name="tempAmount" readonly="true" class="readOnly" size="10" value="" />
					 
					<input type="text" name="totalAmount" readonly="true" class="readOnly" size="10" value="0.00" />&nbsp; U.S. Dollars	 </td>				 
				  </tr>
				  
	<tr>
					<td class="tableLeft">
					<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm')"/> Please charge my card.
						</td>
					
				  </tr>
				  
				  
				  <tr>
				<td colspan="2">
				<input type="hidden" name="cc11" id="cc11" value="usrsec2"/>
				<div id="chrgCrdAcm">
						
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">							
							<tr>
								<td class="tblMainHead" colspan="2">Card Details:</td>
							</tr>
							
								<tr>
								<td class="tableLeft">Card Type:</td>
							  <td class="tableRight">
									<select name="ccType" id="ccType" class="selectboxOne" >
									  <option selected="selected" value="">Select One</option>
									  <option value="Visa">Visa</option>
									  <option value="MasterCard">Master Card</option>

									  <option value="Amex">AmEx</option>
									</select>
									<span class="asterisk">*</span>								</td>
							</tr>
							
							<tr>
								<td class="tableLeft">Card No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="16" />
								  <span class="asterisk">*</span></td>
							</tr>
							<tr>
								<td class="tableLeft">Card CVV No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" /><span class="asterisk">*</span>&nbsp;(See details below)							  </td>
							</tr>
						
							<tr>
								<td class="tableLeft">Print Name On Card:</td>
							  <td class="tableRight">
							  <input type="text" name="ccName" id="ccName" class="textboxOne" size="25" />
								  <span class="asterisk">*</span></td>
							</tr>
							<tr>
								<td class="tableLeft">Expiry Date:</td>
							  <td class="tableRight">
									<select name="ccExpMonth" id="ccExpMonth" class="selectboxOne">

									  <option value="" selected="selected">Month</option>
									  <option value="01">January</option>
									  <option value="02">February</option>
									  <option value="03">March</option>
									  <option value="04">April</option>
									  <option value="05">May</option>
									  <option value="06">June</option>
									  <option value="07">July</option>
									  <option value="08">August</option>
									  <option value="09">September</option>
									  <option value="10">October</option>

									  <option value="11">November</option>
									  <option value="12">December</option>
									</select>
									<select name="ccExpYear" id="ccExpYear" class="selectboxOne">
										  <option value="" selected="selected" >Year</option>
												<%
												java.util.Calendar c6 = java.util.Calendar.getInstance();
												int year5 = c6.get(Calendar.YEAR);
												System.out.println("Current Date = "+year5);
												for(int f=year5;f<=year5+10;f++) 
												{
												%>
												<option  value="<%=f%>"><%=f%></option>
												<%
												}
												%>				
									</select>
									<span class="asterisk">*</span>	
										
							</td>
							</tr>		
              </table>
				</div>	
				</td>							
			</tr>			
			  	
	<%}%>
										  <tr>
											<td class="tableLeft">&nbsp;</td>
											<td width="291" class="tableRight">
											<input type="submit" value="Join" class="gradBtn" />&nbsp;
	<input name="button" type="button" class="gradBtn" value="Back" onclick="location.href='<%=redirurl%>'" />&nbsp;
											</td>
										  </tr>	
										 
						
		 			
						
						                       	      
					
					<%}%>	
					
					
						</table>
					<%if(progDetails!=null && progDetails.size()!=0 || areaProgListForUpgrdTrue!=null && areaProgListForUpgrdTrue.size()!=0){%>	
					<div id="img" class="alignCenter">
															<span class="label">&nbsp;</span>
														   <span class="formX">
																<img src="images/cvv_graphic.jpg" />																				
						 </span>
					</div>		
							<%}%>					  				
							  </form>
							</td>
						  </tr>
						 
						  <tr>
							<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
						  </tr>
						</table>

			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>

</body>
<script language="javascript">
aaaa=document.frmMyAreaRidProgram.tempSec.value;

if(aaaa!="1"){
<%  if(session.getAttribute("loggedBy")!=null){	%>
if(document.getElementById('checkDate').value!=null)
	{
	var cal1 = new calendar2(document.forms['frmMyAreaRidProgram'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	}
	if(document.getElementById('activeDate').value!=null)
	{
	var cal2= new calendar2(document.forms['frmMyAreaRidProgram'].elements['activeDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
	}
if(document.getElementById('activeDatVal').value!=null)
	{
	var cal3= new calendar2(document.forms['frmMyAreaRidProgram'].elements['activeDatVal']);
	cal3.year_scroll = true;
	cal3.time_comp = false;
	}
	
	<%}%>
	}
	</script>
</html>
