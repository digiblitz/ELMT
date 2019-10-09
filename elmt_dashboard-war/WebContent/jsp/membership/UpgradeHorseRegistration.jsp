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
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlccommon.util.HLCHorseRegisterationVO"%>
<%@ page import="com.hlchorse.form.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmHorseUpgrade.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript1.1">
function hideamtSect(){

	 sec="";
     sec =document.myform.c11.value;

	if(document.getElementById('amtSection').style.display == "block"){
		document.getElementById('amtSection').style.display = "none";
		document.getElementById('img').style.display = "none";
	}
	else{
		document.getElementById('amtSection').style.display = "none";
		document.getElementById('img').style.display = "none";
	}
		document.getElementById('amtSection').style.display = "none";
		
	
	if (sec=="userSec")	{
	
		document.getElementById('img').style.display = "block";
	}	else	{
		
		document.getElementById('img').style.display = "none";
	}
}
</script>
</head>
<%! 

SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%! String dateCheck(Date fieldName){
	String detValue = "N/A";
		if(fieldName!=null){
		 detValue = sdf.format(fieldName);
		}
	return detValue;
}

%>
<%! float floatCheck(float fieldName){
	float floateValue = 0;
		if(fieldName!=0.0){
		 floateValue = fieldName;
		}
	return floateValue;
}

%>
<%! 
	DecimalFormat result  = new DecimalFormat("0.00");
	String amtFormat(String amtVal){
	String amountValue = "0.00";
		if(amtVal!=null && amtVal.trim().length()!=0){
		System.out.print(Double.parseDouble(amtVal));
		amountValue = result.format(Double.parseDouble(amtVal));
		}
	return amountValue;
}

%>
<body onload="hideamtSect();">
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
					<div class="cmmnForm">
						<div class="colspan">
							<strong>Membership:</strong> <span class="styleBoldTwo">Upgrade Horse Registration Details </span>						</div>
							<div id="commonBG" class="textCommon" > <span class="asterisk"> IMPORTANT </span> --The only accepts payment by credit card for services purchased online. If you prefer to pay by check, please complete the appropriate <a href="http://useventing.com/start.php?section=docs" target="_blank">form</a> and mail with your check to the  office.
				  </div>
					
					<form name="myform" id="myform" method="post" action="ActionHorseUpgrade.do" onsubmit="return upgrdeval();">
                                        <input type="hidden" name="inVoiceId" id="inVoiceId" value="1"/>
					<input type="hidden" name="memberTypeId" id="memberTypeId" />
					<input type="hidden" name="memberUpgAmt" id="memberUpgAmt" />
					<input type="hidden" name="amountSect" id="amountSect" />
                                          <%
		ArrayList HorseRelDets = (ArrayList) request.getAttribute("HorseRelDets");
		String firstName1,last_name1,relationship_status1,relationship_type_id1,relationship_type_name1,relation_id="";				
		
		String ownerRel="";
		String ownFName="";
		String ownLtName="";			
			 if(HorseRelDets!=null && HorseRelDets.size()!=0){
                                        
				Iterator itr = 	HorseRelDets.iterator();
				while(itr.hasNext()) {
				String[] val=(String[])itr.next();
				firstName1 = val[0];
				last_name1 =  val[1];
				relationship_type_id1 = val[2];				
				relationship_type_name1 = val[3];				
				relationship_status1 =  val[4];
				relation_id = val[5];
if(firstName1!=null && last_name1!=null && relationship_type_name1.equalsIgnoreCase("Owner") && relationship_status1.equalsIgnoreCase("Active")){
				   ownerRel=relationship_type_name1;
				   ownFName=firstName1;
				   ownLtName=last_name1;
				}		
			}		
			}
			String sessionInvoiceId = "1";
                        session.setAttribute("sessionInvoiceId",sessionInvoiceId);
		%>
					<input type="hidden" name="relTypeName" id="relTypeName" value="<%=ownerRel%>"/>
		<input type="hidden" name="ownFirstName" id="ownFirstName" value="<%=ownFName%>"/>
		<input type="hidden" name="ownLastName" id="ownLastName" value="<%=ownLtName%>"/>
					<div class="rowExpand">Horse Registration Details:</div>
					
				
					
						<div class="rowHead" colspan="2">
							Horse Registration Details:	</div>
<%
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 	HLCHorseRegisterationVO HorseDisp =(HLCHorseRegisterationVO) request.getAttribute("ListVO");
	if(HorseDisp==null)	out.print("HorseDisp is null");	
	String horseMemberId = (String) HorseDisp.getHorseMemberId();
	request.setAttribute("horseMemberId",horseMemberId);
    String competitionName = (String) HorseDisp.getCompetitionName();
    String registeredName = (String) HorseDisp.getRegisteredName();
    String baRegisteredName = (String)HorseDisp.getBaRegisteredName();
    String baPastName = (String)HorseDisp.getBaPastName();
    String riderMemberId = (String)HorseDisp.getRiderMemberId();
    String prevRiderMemberId = (String)HorseDisp.getPrevRiderMemberId();
    String addRiderMemberId = (String)HorseDisp.getAddRiderMemberId();
    String ownerId = (String)HorseDisp.getOwnerId();
    String prevOwnerId = (String)HorseDisp.getPrevOwnerId();
    String addOwnerId = (String)HorseDisp.getAddOwnerId();
    String prevOwnerName = (String)HorseDisp.getPrevOwnerName();
    String addOwnerName = (String)HorseDisp.getAddOwnerName();
    String paymentId = (String)HorseDisp.getPaymentId();
    String requestStatus = (String)HorseDisp.getRequestStatus();
    String firstName = (String)HorseDisp.getFirstName();
    String lastName = (String)HorseDisp.getLastName();
    String comments = (String)HorseDisp.getComments();
    String color = (String)HorseDisp.getColor();
    String gender = (String)HorseDisp.getGender();
    String height = (String)HorseDisp.getHeight();
    String yearFoaled = (String)HorseDisp.getYearFoaled();
    String breed = (String)HorseDisp.getBreed();
    String country1 = (String)HorseDisp.getCountry();
    String sire = (String)HorseDisp.getSire();
	String sireBreed = (String)HorseDisp.getSireBreed();
    String dam = (String)HorseDisp.getDam();
    String damBreed = (String)HorseDisp.getDamBreed();
    String breed2 = (String)HorseDisp.getBreed2();
    String sireBreed2 = (String)HorseDisp.getSireBreed2();
    String damBreed2 = (String)HorseDisp.getDamBreed2();
    String importedFrom = (String)HorseDisp.getImportedFrom();
    Date importDate = HorseDisp.getImportDate();
	Date actDate = HorseDisp.getActivationDate();
	Date upgraDate = HorseDisp.getUpgradationDate();
    String foreignGrade = (String)HorseDisp.getForeignGrade();
    float foreignPoints = (float)HorseDisp.getForeignPoints();
	String assignedGrade = (String)HorseDisp.getAssignedGrade();
    float assignedPoints = (float)HorseDisp.getAssignedPoints();
    String notes = (String)HorseDisp.getNotes();
    String splNotes = (String)HorseDisp.getSplNotes();
    String checkNumber = (String)HorseDisp.getCheckNumber();
    String paymentStatus = (String)HorseDisp.getPaymentStatus();
    String checkName = (String)HorseDisp.getCheckName();
    String sslTxnId = (String)HorseDisp.getSslTxnId();
    String bankName = (String)HorseDisp.getBankName();
    Date checkDate = HorseDisp.getCheckDate();
	String chk_dte = "-N/A-";	
	if(checkDate!=null){
		chk_dte = sdf.format(HorseDisp.getCheckDate());	
	}
    String amount = (String)HorseDisp.getAmount();
	String regstrBy = (String)HorseDisp.getRegisteredBy();
	String membertype= (String)HorseDisp.getMemberTypeName();
	String memberTypId= (String)HorseDisp.getMembershipTypeId();
	String priorityVal = (String)HorseDisp.getPriorityValue();
	String membAmount = (String)HorseDisp.getMembershipAmount();
	
	double new_amount=0.0f;
	double full_amount=0.0f;
	double limit_amount =0.0f;
	String existingMemAmt = "";
        
%>
						<div class="row">
								<span class="label">Member ID:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(horseMemberId)%></span>
								</span>
						</div>	
						<div class="row">
								<span class="label">Current Membership Type:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(membertype)%></span>
								</span>
						</div>
						<%
							Vector horsememberVect1=new Vector();
							//horsememberVect=(Vector)session.getAttribute("horsememberVect");
							horsememberVect1=(Vector)request.getAttribute("horsememberVect");
							Enumeration itrators1= (Enumeration)horsememberVect1.elements();
							int siz1=horsememberVect1.size();
						%>
						<input type="hidden" value="<bean:message key='FEH.minage'/>" name="FEHMin"/>
						<input type="hidden" value="<bean:message key='FEH.maxage'/>" name="FEHMax"/>
						<input type="hidden" value="<bean:message key='YEH.minage'/>" name="YEHMin"/>
						<input type="hidden" value="<bean:message key='YEH.maxage'/>" name="YEHMax"/>
						<input type="hidden" name="priorityVal" value="<%=priorityVal%>" id="priorityVal" />
						<input type="hidden" name="membAmount" value="<%=membAmount%>" id="membAmount" />
						<input type="hidden" name="membertype" id="membertype" value="<%=membertype%>" />
						
						<div class="row">
								<span class="label">Upgrade To Membership Type:</span>
								<span class="formX">
								  <select name="feeDisp" id="horseSelect" class="selectboxOne" onChange="Dispall();" >
								  <option selected="selected" value="" >Select One</option>  
									 <% 
									 if(siz1!=0)
									 {
									 while(itrators1.hasMoreElements()){
												
												String[] sarray = (String[]) itrators1.nextElement();
												
												String memberTypeId = sarray[0];
												String membershipName = sarray[1];
												String membershipAmount = sarray[2];
												String priorityVal1 = sarray[3];
												if(membertype.trim().equalsIgnoreCase(membershipName)){
													existingMemAmt = membershipAmount;
												}
												String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;

											if(priorityVal!=null && priorityVal1!=null){
												int p = Integer.parseInt(priorityVal);
												int p1 = Integer.parseInt(priorityVal1);
												if(p1>p){
									%>
													   <option  value="<%=cnct%>"><%=membershipName%></option>
									<%  		} 
											}%>
							<%}}%>	  <!-- end loop  -->
								</select>
							<input type="hidden" name="currMemAmt" id = "currMemAmt" value="<%=existingMemAmt%>" />							
							</span>					  
						</div>
						<div class="row">
							<span class="label">Registration Amount:</span>
							<span class="formX">
								<strong>$</strong>&nbsp;<input id="fee_txt" class="readOnly" type="text" name="fee_txt" size="10" readonly="true" value=""/>
							</span>			
						</div>
						<div class="row"> 
								<span class="label">Payable Amount:</span>
								<span class="formX"><strong>$</strong>&nbsp;
						        <input id="amtPay" class="readOnly" type="text" name="amtPay" size="10" readonly="true" value=""/></span>
	                  </div>
						<div class="row">
								<span class="label">Activation Date :</span>
								<span class="formX"><span class="styleBoldOne"><%=dateCheck(actDate)%></span></span>
	                  </div>

						<div class="row">
								<span class="label">Upgradation Date:</span>
								<span class="formX"><span class="styleBoldOne"><%=dateCheck(upgraDate)%></span></span>

			            </div>		
						
						<div class="rowHead">
							Horse Information Section:						</div>
								<div class="row">
									<span class="label">Horse Name: </span>
									<span class="formX"><strong><%=nullCheck(competitionName)%></strong></span>								</div>
						<div class="row">
								<span class="label">Registered By:</span>
								<span class="formX"><span class="styleBoldOne"><%=nullCheck(regstrBy)%></span></span>					  </div>								
								<div class="row">
									<span class="label">Registered Name:</span>
									<span class="formX"><%=nullCheck(registeredName)%></span>								
								</div>
								<div class="row">
									<span class="label">Past Name:</span>
									<span class="formX"><%=nullCheck(baPastName)%></span>								
								</div>
								<div class="row">
									<span class="label">Breed Assoc. Horse is registered with:</span>
									<span class="formX"><%=nullCheck(baRegisteredName)%></span>								
								</div>
								<div class="rowHead">Horse Description:</div>
								<div class="row">
									<span class="label">Color:</span>
									<span class="formX"><%=nullCheck(color)%></span>								
								</div>								 
								<div class="row">
									<span class="label">Sex:</span>
									<span class="formX"><%=nullCheck(gender)%></span>								
								</div>
								<div class="row">
									<span class="label">Height</span>
									<span class="formX"><%=nullCheck(height)%></span>								
								</div>
								<div class="row">
									<span class="label">Year foaled:</span>
									<span class="formX"><%=nullCheck(yearFoaled)%></span>	
								<input type="hidden" name="yearFoaled" id="yearFoaled" value="<%=nullCheck(yearFoaled)%>" />							
								</div>
								<div class="row">
									<span class="label">Breed:</span>
									<span class="formX"><%=nullCheck(breed)%></span>								
								</div>				   
								<div class="row">
									<span class="label">Breed Two:</span>
									<span class="formX"><%=nullCheck(breed2)%></span>								
								</div>	
								<div class="row">
									<span class="label">Country of origin:</span>
									<span class="formX"><%=nullCheck(country1)%></span>								
								</div>
								<div class="row">
									<span class="label">Sire Name:</span>
									<span class="formX"><%=nullCheck(sire)%></span>								
								</div>
								<div class="row">
									<span class="label">Sire Breed:</span>
									<span class="formX"><%=nullCheck(sireBreed)%></span>								
								</div>							   	
								<div class="row">
									<span class="label">Sire Breed Two:</span>
									<span class="formX"><%=nullCheck(sireBreed2)%></span>								
								</div>
								<div class="row">
									<span class="label">Dam Name:</span>
									<span class="formX"><%=nullCheck(dam)%></span>								
								</div>
								<div class="row">
									<span class="label">Dam Breed:</span>
									<span class="formX"><%=nullCheck(damBreed)%></span>								
								</div>							
								<div class="row">
									<span class="label">Dam Breed Two:</span>
									<span class="formX"><%=nullCheck(damBreed2)%></span>								
								</div>
								<div class="row">
									<span class="label">Imported From:</span>
									<span class="formX"><%=nullCheck(importedFrom) %></span>								
								</div>								 
								<div class="row">
									<span class="label">Date Imported:</span>
									<span class="formX"><%=dateCheck(importDate)%></span>								
								</div>
								<div class="row">
									<span class="label">Foreign Grade:</span>
									<span class="formX"><%=nullCheck(foreignGrade)%></span>								
								</div>							
								<div class="row">
									<span class="label">Foreign Points:</span>
									<span class="formX"><%=floatCheck(foreignPoints)%></span>								
								</div>
								<div class="row">
									<span class="label">Assigned Grade:</span>
									<span class="formX"><%=nullCheck(assignedGrade)%></span>								
								</div>
								<div class="row">
									<span class="label">Assigned Points:</span>
									<span class="formX"><%=floatCheck(assignedPoints)%></span>								
								</div>
								<div class="row">
									<span class="label">Notes:</span>
									<span class="formX"><%=nullCheck(notes)%></span>								
								</div>
								<div class="row">
									<span class="label">Special Notes:</span>
									<span class="formX"><%=nullCheck(splNotes)%></span>								
								</div>
								<div class="row">
									<span class="label">Comments:</span>
									<span class="formX"><%=nullCheck(comments)%></span>								
								</div>
								
								
					
						<input type="hidden" name="process" value="updatestatus">
						<input type="hidden" name="prevMemTypeId" value="<%=memberTypId%>" />
						<input name="horseName" value="<%=competitionName%>" type="hidden"/>
						<input type="hidden" name="horseMemberId" value="<%=horseMemberId%>"/>
						<input type="hidden" name="parentPaymentId" value="<%=paymentId%>"/>
						<input type="hidden" name="regstrBy" value="<%=regstrBy%>"/>
<!--						<div class="rowHead">Upgrade Information:</div>
						<div class="row">
							<span class="floatLeft">Existing Membership Amount</span>
							<span class="formX">< %= amount %></span>
						</div>
						<div class="row">
							<span class="floatLeft">New Membership Amount</span>
							<span class="formX">< %= amount %></span>						
						</div>
						<input type="hidden" name="fullAmt" value="< %=fullAmount%>" />						
						<input type="hidden" name="limitedAmount" value="< %=limitedAmount%>" />
						<input type="hidden" name="new_amount" value="< %=new_amount%>"/>
						<span class="floatRight">< % = output1%></span></div>-->
<table width="100%" cellpadding="0" cellspacing="0" align="left" border="0">
        
        <tr>
        <td colspan="2">
		<div id="amtSection">
			<%
			//System.out.print("newRoleName in horse registration:" + newRoleName);
			if(session.getAttribute("loggedBy")==null){%>
				<input type="hidden" name="status" value="no">	
			<%}
				int si=0;
				String logBy="user";
				if(session.getAttribute("loggedBy")!=null){
				String loggedBy="";
				loggedBy=(String)session.getAttribute("loggedBy");
				logBy=loggedBy;
			if(loggedBy.equalsIgnoreCase("Admin")){
			%>
			<div id="phoneReg"><div class="rowHead">Phone Registration Information:		</div>
			<%  
			String horsesertypVect[] =(String[])request.getAttribute("horsesertypVect");
			DecimalFormat myFormatter = new DecimalFormat("######.00");
			String typeId ="";
			String typeName ="";
			String typePrice ="";
			
			String output ="";
			if(horsesertypVect!=null){
			typeId = horsesertypVect[0];
			typeName = horsesertypVect[1];
			typePrice = horsesertypVect[2];
			double finalAmt = Double.parseDouble(typePrice);
			//System.out.print("finalAmt" + finalAmt);
			output = myFormatter.format(finalAmt);
			}
			%>
			
			<div class="row">
			<span class="floatLeft">
			<input type="hidden" name="status" value="yes">
			<input type="hidden" name="servicePrice" value="<%=typeId%>" >
			<input type="checkbox" name="phoneCharge" id="phoneCharge" value="<%=typePrice%>" size="10" onClick="Disp();"> </span>
			<span class="floatLeftRadio"><%=typeName%>
			</span>
			<span class="floatRight"><strong><%=output%></strong>&nbsp;&nbsp;</span>            </div>
			<% }
			else {
			%>
			<input type="hidden" name="status" value="no">	
			<% }
			}%>
			</div>		
        <table width="100%" cellpadding="0" cellspacing="0" border="0">
        <tr>
        <td colspan="2" class="tblRowHead"> Payment Information</td>
        </tr>
        <tr>
        <td class="tableLeft"><strong>Total Amount:</strong></td>
        <td class="tableRight"> <strong>$</strong>
        <%
        
        %>
        <input type="text" name="totalAmount" readonly="true" class="readOnly" size="10" value="<%//=output1%>" />&nbsp; U.S. Dollars					 </td>
                </tr>
				<%if(session.getAttribute("loggedBy")!=null){%>
                <tr>
                <td class="tableLeft">
                <input type="radio" size="10" name="r11" id="radio" value="check"  onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'),hideImgDiv('img'), checkClear();cardClear();"/>
                Check enclosed.				 	</td>
                <td class="tableRight">
                <input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
                onclick="switchDiv('chrgCrdAcm'), showImgDiv('img'),showHideRadio('r11','chkEncAcm','card'), cardClear();checkClear();"/> Please charge my card.					</td>
                </tr>
				<input type="hidden" name="c11" id="c11" value="adminSec">
				<%}else{%>
				<tr>
               
                <td class="tableRight">
                <input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
                onclick="switchDiv('chrgCrdAcm')"/> Please charge my card.					</td>
                </tr>
				<input type="hidden" name="c11" id="c11" value="userSec">
				<%}%>
                <tr>
                <td colspan="2">
                <div id="chkEncAcm">
                <table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
					<tr>
					<td class="tblMainHead" colspan="3">Check Details:</td>
					</tr>
					<tr>
					<td colspan="2" class="tableSpan">
					
					
					If paying by check, please mail your payment to:  <br />
					<br />
					<strong>
					<br />
					Horse Registration <br />
					525 Old Waterford Road NW
					<br />
					Leesburg, VA 20176												    </strong><br />
					<br />
					<strong>Note:</strong><span class="styleBoldOne"> Your registration status will be pending until check is processed.	</span>  <br />
					<br /></td>
					</tr>
					<%
					//System.out.print("newRoleName in horse registration:" + newRoleName);
					String finalDate="";
					
					
					java.util.Calendar c5 = java.util.Calendar.getInstance();
					int day = c5.get(Calendar.DATE);
					int month1 = c5.get(Calendar.MONTH);
					
					int month=month1+1;
					int year = c5.get(Calendar.YEAR);
					String date="";
					String chkDat="";
					
					if(month<10) {
					date = year+"-"+"0"+month+"-"+day;
					
					chkDat = "0"+month+"/"+day+"/"+year;
					} else {
					date = year+"-"+month+"-"+day;
					chkDat = month+"/"+day+"/"+year;
					}
					if(session.getAttribute("loggedBy")!=null){
					
					%>
					<tr>
					
					<td class="tableLeft">Check Amount:</td>
					<td class="tableRight"><strong>$</strong>
					<input type="hidden" name="roughVal" value="yes">
					<input type="text" name="chkBalAmt" id="chkBalAmt" class="textboxOne" size="16" maxlength="10" />
					&nbsp; U.S. Dollars
					</td>
					</tr>
					<%
					} else{%>
					<input type="hidden" name="roughVal" value="no">
					<%}
					%>
					<tr>
					<td class="tableLeft">Check No:</td>
					<td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /><span class="asterisk">*</span>							  </td>
					</tr>
					<%	if(session.getAttribute("loggedBy")!=null){%>
					<input type="hidden" name="chkdate" value="yes">
					<tr>
					<td class="tableLeft">Check Date:</td>
					<td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" size="11" maxlength="13"/>
					
					<a href="javascript:cal2.popup();">
					<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					<span class="asterisk">*</span>												  </td>
					</tr>
					<%} else{%>
					<input type="hidden" name="chkdate" value="no">
					<tr>
					<td class="tableLeft">Check Date:</td>
					<td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" readonly="true" size="11" />
					
					<a href="javascript:cal2.popup();">
					<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					<span class="asterisk">*</span>												  </td>
					</tr>
					<%	}	%>
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
					<input type="hidden" name="logby" value="no" />
					<tr>
					<td class="tableLeft">Card Type:</td>
						<td class="tableRight">
							<select name="ccType" id="ccType" class="selectboxOne" >
								<option selected="selected" value="">Select One</option>
								<option value="Visa">Visa</option>
								<option value="MasterCard">Master Card</option>
								<option value="American Express">AmEx</option>
							</select>
							<span class="asterisk">*</span>
						</td>
					</tr>
					<tr>
						<td class="tableLeft">Card No.:</td>
						<td class="tableRight"><input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="16" /><span class="asterisk">*</span></td>
					</tr>
					<%if(session.getAttribute("loggedBy")!=null){%>
					<tr>
						<td class="tableLeft">Card CVV No.:</td>
						<td class="tableRight"><input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />&nbsp;(See details below)</td>
					</tr>
					<%}else{%>
					<tr>
						<td class="tableLeft">Card CVV No.:</td>
						<td class="tableRight"><input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" /><span class="asterisk">*</span>&nbsp;(See details below)</td>
					</tr>
					<%}%>
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
							<option  value="2006">2006</option>
							<option  value="2007">2007</option>
							<option  value="2008">2008</option>
							<option  value="2009">2009</option>
							<option  value="2010">2010</option>
							<option  value="2011">2011</option>
							<option  value="2012">2012</option>
							<option  value="2013">2013</option>
							<option  value="2014">2014</option>
							<option  value="2015">2015</option>
                                                         <option  value="2016">2016</option>
                                                         <option  value="2017">2017</option>
                                                         <option  value="2018">2018</option>
                                                         <option  value="2019">2019</option>
                                                         ]<option  value="2020">2020</option>
						</select>
						<span class="asterisk">*</span>	
			            </td>
					</tr>
					<%
			String logby = null;
			if(session.getAttribute("loggedBy")!=null){
			logby="yes";
			%>
			<tr>
				<td class="tableLeft">&nbsp;Activation Date:</td>
				<td class="tableLeft"><span class="tableRight">
				  <input type="text" name="activeDatVal" id="activeDatVal" class="textboxOne" size="16" value="<%=chkDat%>" /></span>
					<a href="javascript:cal3.popup();">
					<img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					<span class="asterisk">*</span>
				</td>
			</tr>
			<% } %>   
				</table>
				</div>												
				</td>
			</tr>
			                                     
             <input type="hidden" name="logby" value=<%=logby%> />
			 
				
             </table>
			</div>		<!-- amtSection -->
		</td>
		</tr>
			<tr>
			<td colspan="2" class="alignCenter">
			<div id="spacer">&nbsp;</div>
				<input type="submit" value="Upgrade" class="gradBtn" />&nbsp;
				<input type="button" name="Cancel" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);"/>								  </td>
			</tr>										
		</table>
		<div id="spacer">&nbsp;</div>
			
			<div class="alignCenter" id="img">
				<span class="label">&nbsp;</span>
				<span class="formX">
				<img src="images/cvv_graphic.jpg" />																				
				</span>
			</div>
				</form>
			   <!-- CONTENTS END HERE -->			</td>
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
var cal2 = new calendar2(document.forms['myform'].elements['checkDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
<%  if(session.getAttribute("loggedBy")!=null){%>
		var cal3 = new calendar2(document.forms['myform'].elements['activeDatVal']);
			cal3.year_scroll = true;
			cal3.time_comp = false;	
	<% }%>	
</script>	
</html>
