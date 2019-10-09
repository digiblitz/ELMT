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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeEduActivity.js" type="text/javascript" ></script>
<script src="javascripts/frmActivity.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
 	function calculateAmount(){
		var amount = 0.00;
		var days = document.frmMeeEduActivity.noOfDays.value;
		if(days==1){
			amount = 100;
		}
		else if(days>1 && days <5){
			amount = 150;
		}
		else if(days>4){
			tempAmount = 150;
			daysDiff = days-4;
			amount = eval(tempAmount) + eval(daysDiff*45);
		}
		document.frmMeeEduActivity.amount.value = amount;
	}
</script>
</head>
<body onload="cardClear();">
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
<%
String sessionInvoiceId = "1";
            session.setAttribute("sessionInvoiceId", sessionInvoiceId);
String memberId = (String)session.getAttribute("memberId");
if(memberId==null)
memberId = "Memebr Id Not Exist";
ArrayList listContact = (ArrayList) session.getAttribute("DisplaymemberDetails");

		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null)
				middle_name = "";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null)
				address2 = "";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null)
				fax_no = "";
			}
		}
%>			
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
			
			
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td>
			<form name="frmMeeEduActivity"  action="eduActivity.do" method="post"  onsubmit="return eduActivity();">
			<input type="hidden" name="activityProcess" value="insertEduDet">	
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong>HLC</strong> Meetings: <span class="styleBoldTwo">Sponsored Educational Activity Application</span></td>
						</tr>
						 <tr>
							<td colspan="2" class="tblDescrp">
						</tr>
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table width="98%" align="center" cellpadding="0" cellspacing="0" border="0">
							<tr class="rowHead">
								<td colspan="2" class="tableSpan">Required fields are marked with an asterisk <span class="asterisk">*</span></td>
							</tr>
							 <tr> 
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Activity Information: </span></td>
							  </tr>
								  <tr>
								    <td class="tableLeft">Name of Activity: </td>
								    <td class="tableRight"><label>
								        <input type="text" name="activityName" class="textboxOne" id="txtActName"/>
							        <span class="asterisk">*</span></label></td>
						      </tr>
								  <tr> 
  									<td class="tableLeft"> Date of Activity: </td>
									<td class="tableRight">
									<input type="text" class="textboxOne" readonly="true" name="activityDate" id="txtActDate" />
									<a href="javascript:cal1.popup();">
					  <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
									  <span class="asterisk"> * </span></td>
								  </tr>
								  <tr>
								    <td class="tableLeft">No of Days: </td>
								    <td class="tableRight"><label>
								      <select name="noOfDays" id="daysSelect" class="selectboxOne" onchange="calculateAmount();">
								        <option selected="selected" value="">Days</option>
								        <option value="1">01</option>
								        <option value="2">02</option>
								        <option value="3">03</option>
								        <option value="4">04</option>
								        <option value="5">05</option>
								        <option value="6">06</option>
								        <option value="7">07</option>
								        <option value="8">08</option>
								        <option value="9">09</option>
								        <option value="10">10</option>
								        <option value="11">11</option>
								        <option value="12">12</option>
								        <option value="13">13</option>
								        <option value="14">14</option>
								        <option value="15">15</option>
								        <option value="16">16</option>
								        <option value="17">17</option>
								        <option value="18">18</option>
								        <option value="19">19</option>
								        <option value="20">20</option>
								        <option value="21">21</option>
								        <option value="22">22</option>
								        <option value="23">23</option>
								        <option value="24">24</option>
								        <option value="25">25</option>
								        <option value="26">26</option>
								        <option value="27">27</option>
								        <option value="28">28</option>
								        <option value="29">29</option>
								        <option value="30">30</option>
								        <option value="31">31</option>
					                </select>
								      <span class="asterisk">*</span></label></td>
						      </tr>
							   
								  <tr> 
									<td class="tableLeft"> HLC Area: </td>
									<td class="tableRight">
									<select name="hlcAreaId" id="txtUseaArea" class="selectboxOne">
                                  		   <option selected="selected" value="">Select One</option>
                                     <%
										ArrayList areaDetails = (ArrayList)session.getAttribute("DispAreaDetails");
										if(areaDetails!=null && areaDetails.size()!=0){
										Iterator areaIt = areaDetails.iterator();
										while(areaIt.hasNext()){
										String[] areaDet =(String[])areaIt.next();
										String areaId = areaDet[0];
										String areaName = areaDet[2];
										String areaCode = areaDet[1];
									%>
										<option value="<%=areaId%>"><%=areaName%></option>
									<%
										  }
										}
									%>    
							   </select>
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
								    <td class="tableLeft">Activity Location:</td>
								    <td class="tableRight"><input type="text" name="location" id="location" class="textboxOne" /></td>
						      </tr>
								  <tr>
									<td class="tableLeft"> Country:</td>
									<td class="tableRight"> 
									<select name="txtLocation" id="txtLocation" class="selectboxOne" onChange="FillState(document.frmMeeEduActivity.txtLocation, document.frmMeeEduActivity.txtState, '');">
							  		</select>  
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> State:</td>
									<td class="tableRight"><select name="state" id="txtState" class="selectboxOne"></select>  
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">City:</td>
									<td class="tableRight"><input type="text" name="actCity" id="actCity" class="textboxOne"  size="15" /><span class="asterisk"> * </span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Zip: </td>
									<td class="tableRight"><input type="text" name="actZipcode" id="actZipcode" class="textboxOne" size="8" /><span class="asterisk"> * </span></td>
								  </tr>
								  <tr>
									<td colspan="2" height="27" class="tableSpan">
									<input type="checkbox" name="additionalSites" id="chkAddSites" value="yes" onclick="browseCheck();"/>
									Check if Additional Sites are to be used.</td>
								  </tr>
								  <tr>
									<td class="tableLeftTxtArea">Additional Site Information: </td>
									<td class="tableRight"> <textarea name="comments" id="txtUpload" class="textAreaOne" disabled="disabled" rows="5"></textarea></td>
								  </tr>
								  <tr>
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Organizer Information: </span></td>
								  </tr>
								  <tr>
									<td colspan="2" class="tblDescrp" style="padding:8px;"> 
										Organizer must be an <strong>individual</strong>, <strong>adult</strong>, <strong>current HLC member</strong>. Multiple or company organizers are not allowed.

										The organizer must be declared on this application in order to be covered by insurance.									</td>
								  </tr>
								
						  <tr>
							<td class="tableLeft"> HLC Member ID:</td>
							<td class="tableRight">
							<input type="text" class="textboxOne" name="hlcNo" id="riderUseaNo_id" onblur="riderDetails();" size="20" />
						 <span class="asterisk">*</span></tr>
						   <tr>
							<td class="tableLeft"> Salutation:</td>
							<td class="tableRight"><input type="text" name="rprefix" id="rprefix" readonly="true" class="readOnly" size="10" /></td>
						  </tr>		
						  <tr>
							<td class="tableLeft"> Organizer First Name:</td>
							<td class="tableRight">
							<input type="text" class="readOnly" readonly="true"  name="riderFname_id" id="riderFname_id" size="20" />							</td>
						  </tr>
						   <!-- <tr>
							<td class="tableLeft"> Organizer Middle Name:</td>
							<td class="tableRight">
							<input name="textbox62" type="text" class="textboxOne" size="25" readonly value=" " />
							</td>
						  </tr>-->
						  
						    <tr>
							<td class="tableLeft"> Organizer Last Name:</td>
							<td class="tableRight">
							<input type="text" class="readOnly" readonly="true"  name="riderLname_id" id="riderLname_id" size="20" />							</td>
						  </tr>

						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input type="text"  class="readOnly"  readonly="true" name="rStreet" id="rStreet" size="20"/></td>
						  </tr>
											  
						  <tr> 
							<td class="tableLeft">Country:</td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="countryId" id="countryId" size="15" /></td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">State : </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rState" id="rState" size="20" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">City:</td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rCity" id="rCity" size="15" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Zip: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rZipcode" id="rZipcode" size="8" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Phone:</td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="riderPhone_id" id="riderPhone_id" size="15" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Mobile No: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rCell" id="rCell" size="15" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Email ID: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rEmail" id="rEmail" size="20" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Fax: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rFax" id="rFax" size="20" /></td>
						  </tr>
						  <tr>
									<td class="tableLeft"> Type Of Activity : </td>
									<td class="tableRight"><span class="row"><span class="formX">
									  <select name="selActivityId" id="selActivityId" class="selectboxOne" onChange="getText();">
										<option selected="selected" value="">Select One</option>
										
										<%
										ArrayList activityDetails = (ArrayList)session.getAttribute("DisplayActivityTypeDetails");
										if(activityDetails!=null && activityDetails.size()!=0){
										Iterator it = activityDetails.iterator();
										while(it.hasNext()){
										String[] eduDet =(String[])it.next();
										String id = eduDet[0];
										String activityName1 = eduDet[1];
										 
										%>
										
										<option value="<%=id%>"><%=activityName1%></option>
										
									 <%
									 
							  }
							}
							  %>
									  </select>
									  <span class="asterisk">*</span></span></span></td>
							  </tr>
							 
								  <tr id="othTypAct">
									<td class="tableLeft">If other, Specify:  </td>
									<td class="tableRight">
									<input type="text" class="textboxOne" name="otherActivityType" id="txtOthernot" disabled="true"/>
									<span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Fee To Be Charged(<strong>$</strong>):  </td>
									<td class="tableRight"><input name="activityFees" id="txtFee" type="text" class="textboxOne" size="10" />
								    <span class="asterisk">*</span></td></tr>
								  <tr>
									<td class="tableLeft"> Instructor(s)/Coach(es):  </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="instructorsCoaches" id="txtCoach" />
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically For:  </td>
									<td class="tableRight">
										<input type="checkbox" name="chkFac1" id="chkFacOne"  value="Cross-Country Schooling"/>Cross-Country Schooling
										<input type="checkbox" name="chkFac2" id="chkFacTwo"  value="Riding Clinic" />	Riding Clinic
										<input type="checkbox" name="chkFac3" id="chkFacThree" value="Stablity" />
										Stabling
										<input type="checkbox" name="chkFac4" id="chkFacFour"  value="Other" onClick="isOtherscheck();" />Other<span class="asterisk">*</span></td>
								  </tr>
								<tr>
									<td class="tableLeft"> If Other Specify:</td>
									<td class="tableRight"><input type="text" class="textboxOne" name="otherFacilities" id="txtOther" disabled="disabled" /></td>
								</tr>
								<tr>
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Publicity And Mailing List: </span></td>
								</tr>
								<tr>
									<td class="tableLeft"> Do you want Mailing list and Publications? </td>
									<td class="tableRight">
										<input name="pmlRad" id="pmlRad" type="radio" value="yes" onclick="switchDiv('pubMailist');"/>Yes
										<input name="pmlRad" id="pmlRad" type="radio" value="no" onclick="showHideRadio('pmlRad','pubMailist','no'); radioClear();" />No									</td>
							   </tr>
							   <tr id="pubMailist">
									<td colspan="2">
										<table width="100%" cellpadding="0" cellspacing="0" border="0">
											<tr class="tblInnerContainer">
											<td colspan="2" class="tblDescrp" style="padding:8px;">
											When publication deadlines allow, registered educational activities will be listed in <strong>Eventing USA</strong> 
											and the <strong>Omnibus</strong>. Activities will be listed by name, date, and location with organizer's name and phone number. <br /><br />
		
											The <strong>HLC will provide mailing lists electronically as a .csv electronic file</strong> that can be used in Word or Excel. 
											If you cannot work with an electronic file, a hard copy list, on regular paper will be sent, which can then be 
											copied onto 5160 label sheets. Mailing list requests must be made at least six weeks in advance.
		
											 
											 <!-- <p><strong>User, please take your time and register with HLC to avail the privileges that accompanies with it. <br />
											  Note:<span class="textCommon" style="height:100px;">This registration process does not make you a member of HLC.</span><br />
											  You may become an HLC Member by filling up the Membership Application form towards the end of this registration process.<br />
												</strong> </p>-->											</td>
										  </tr>
										  <tr>
											<td class="tableLeft"> Email HLC Logo to:</td>
											<td class="tableRight"><input type="text" class="textboxOne" name="emailLogo" id="txtLogo" value="Enter e-mail address" /> 
											(For Publicity Use Only)</td>
										  </tr>
										  <tr>
											<td class="tableLeft"> Complimentary Mailing List Format: </td>
											<td class="tableRight"><span class="row"><span class="formX">
											<input name="radioCompMail" type="radio" id="rdoButton" value="Electronic .csv file" />Electronic .csv file
											<input name="radioCompMail" type="radio" id="rdoButton" value="Hard Copy " />
											Hard Copy</span></span></td>
										  </tr>
										  <tr>
											<td height="27" class="tableLeft"><span class="row"><span class="label">Send Mailing List by :</span></span></td>
											<td class="tableRight"><span class="row"><span class="formX">
											<input name="radioSendMail" type="radio" id="rdoButton1" value="Areas" onclick="switchDiv('enterAre'); showHideRadio('radioSendMail','enterSta','Areas');"/>Areas
											<input name="radioSendMail" type="radio" id="rdoButton1" value="States" onclick="switchDiv('enterSta'); showHideRadio('radioSendMail','enterAre','States');"/>States </span></span>									<span class="asterisk">*</span></td>
										  </tr>
										  <tr id="enterAre">
											<td class="tableLeft"> <span class="row"><span class="label">Enter Areas:</span></span></td>
											<td class="tableRight"><input name="areaStatus" type="text" class="textboxOne" id="areaStatus" size="15" />
											<span class="asterisk">* </span>(E.g. 1,2,3,4) </td>
										  </tr>
										  <tr id="enterSta">
											<td class="tableLeft"> <span class="row"><span class="label">Enter States:</span></span></td>
											<td class="tableRight"><input name="areaState" type="text" class="textboxOne" id="areaState" size="15" />
											  <span class="asterisk">* </span>(E.g. VA,MD,PA) </td>
										  </tr>
										  <tr>
											<td class="tableLeft"> <span class="row"><span class="label">Sort Mailing List by :</span></span></td>
											<td class="tableRight"><span class="row"><span class="formX">
											<input name="radioSortMail" type="radio" id="rdoButton2" value="Zip" />Zip
											<input name="radioSortMail" type="radio" id="rdoButton2" value="Alpha by last name " />Alpha by last name </span></span>
																				<span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<td class="tableLeft"> <span class="row"><span class="label">Number of copies of the HLC color brochure :</span></span></td>
											<td class="tableRight"><input type="text" class="textboxOne" name="noOfCopies" id="txtCopies" /></td>
										  </tr>
										</table>									</td>
								</tr>
								<tr>
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Land Owner Details : </span></td>
								  </tr>
								  <tr>
									<td class="tableLeft">Land Owner Name :</td>
									<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerName" id="txtLandOwner" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td height="27" class="tableLeft">Business Name : </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerBusinessName" id="txtBusiName" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Address:</td>
									<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerAddress" id="txtLandAddress" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td height="27" class="tableLeft">Country: </td>
									<td class="tableRight">
									 <select name="landOwnerCountry" id="landOwnerCountry" class="selectboxOne" width="200" onChange="FillState(document.frmMeeEduActivity.landOwnerCountry, document.frmMeeEduActivity.landOwnerState, '');"> 
										</select>
									 <span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft"> State:</td>
									<td class="tableRight">
									<select name="landOwnerState" id="landOwnerState" class="selectboxOne">
									  </select>
									<span class="asterisk">*</span> </td>
								  </tr>
								  <tr>
									<td class="tableLeft">City: </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerCity" id="txtLandCity" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Zip Code: </td>
									<td class="tableRight"><input name="landOwnerZip" type="text" class="textboxOne" id="txtLandZip" size="8" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Telephone: </td>
									<td class="tableRight"><input name="landOwnerPhone" type="text" class="textboxOne" id="txtLandPhone" size="15">
									  <span class="asterisk">*</span></td>
								  </tr>
                                                                   <tr>
									<td class="tableLeft">Fax: </td>
									<td class="tableRight"><input name="landOwnerFax" type="text" class="textboxOne" id="txtLandFax" size="15">
                                                                             <span class="asterisk">*</span>
									  </td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Email ID: </td>
									<td class="tableRight"><input name="landOwnerEmail" type="text" class="textboxOne" id="txtLandEmail" size="15">
                                                                             <span class="asterisk">*</span>
									  </td>
								  </tr>
								  
								  <tr>
									<td colspan="2" > 
								 
									  <table width="100%" border="0" cellpadding="0" cellspacing="0">
										  <tr>
											<td class="tblDescrp">
											  <strong>ACCEPTANCE OF TERMS:</strong><br />
											   I have read the <a href="" class="linkFive">Requirements for the HLC Educational Activity</a> written on the reverse of this
											  Application, and the HLC Release Form. <br />
											  <br />
											  I understand those Requirements and terms and the responsibility I accept as a
											  HLC educational activity organizer. I agree as a condition of HLC registration of this activity to adhere to those conditions
											  and to pay the appropriate fees.							  	</td>
										  </tr>
											<tr>
											  <td class="alignLeft">       
												  <input type="checkbox" name="approvedBy" value="checkbox" id="chkAccept" /> I Accept<span class="asterisk">*</span> </td>
											</tr>
									  </table>							 </td>
								</tr> 
							 </table>
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->
							</td>
						 </tr>
						  
											 
						<tr id="part4" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 4 of the form starts here ++++++++++++++++++++++++++++++ -->	
						
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								  <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
								  </tr>

								  <tr>
									<td class="tableLeft"><strong>Total Amount:</strong></td>
									<td class="tableRight"> <strong>$</strong>
									 <input type="text" name="amount" id="txtAmount" class="readOnly" size="10" readonly="true" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  
	<tr>
									  	<td class="tableLeft">
									 		<input type="radio" size="10" name="r11" id="r11" value="check"  onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'), cardClear(),hideImgDiv('cvvImg');"/> Check enclosed.				 	</td>
									 	<td class="tableRight">
									 		<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'), showHideRadio('r11','chkEncAcm','card'), checkClear(),showImgDiv('cvvImg');"  /> Please charge my card.					</td>
									  </tr>
									  
									  </table>					</td>
									  </tr>
						  			  <tr id="chkEncAcm">
										<td colspan="2">

											<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
												<tr>
													<td class="tblMainHead" colspan="3">Check Details:</td>
												</tr>
												<tr>
												  <td colspan="2" class="tableSpan">
												  If paying by check (payable to "HLC"), please mail your payment to:  <br />
												    <br />
												    <strong>HLC
												    <br />
												    Educational Activity Registration
												    <br />
												    525 Old Waterford Road NW
												    <br />
												    Leesburg, VA 20176												    </strong><br />
												    <br />
											        <strong>Note:</strong> Your registration status will be pending until check is processed.												  <br />
										          <br /></td>
											  </tr>
												<tr>
												  <td class="tableLeft">Check No:</td>
												  <td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
												</tr>
												<tr>
												  <td class="tableLeft">Check Date:</td>
												  <td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" readonly="yes"size="16" />



												  <a href="javascript:cal2.popup();">
					  <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
									  <span class="asterisk"> * </span>												  </td>
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
											</table>					</td>
									  </tr>
									  <tr id="chrgCrdAcm">
										<td colspan="2">

											<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">

												
												<tr>
													<td class="tblMainHead" colspan="2">Card Details:</td>
												</tr>
												
													<tr>
													<td class="tableLeft">Card Type:</td>
												  <td class="tableRight">
														<select name="ccType" id="ccType" class="selectboxOne">
														  <option selected="selected" value="">Select One</option>
														  <option value="Visa">Visa</option>
														  <option value="MasterCard">Master Card</option>
														  <option value="AmEx">AmEx</option>
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
					                                  <span class="asterisk">*</span></td>
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
                                                                                                                           <option  value="2020">2020</option>
														</select>
					                                    <span class="asterisk">*</span>																         </td>
								      </tr>
								 </table>								</td>
							</tr>
									<tr>
									<td colspan="2" class="alignCenter"><input type="submit" value="Submit" class="gradBtn" /></td>
							   </tr>
							</table>
							<!--++++++++++++++++++++ Part 4 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
						</td>
					 </tr>
				
					  </table>
					  </form>
					  
					</td>
				  </tr>
				  <tr>
						<td>&nbsp; 
					   <!-- DO NOT DELETE THIS ROW -->
					   </td>
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
<script>
	FillCountry(document.frmMeeEduActivity.landOwnerCountry, document.frmMeeEduActivity.landOwnerState, '---Select---');
	FillState(document.frmMeeEduActivity.landOwnerCountry, document.frmMeeEduActivity.landOwnerState, '---Select---');
</script>
<script>
     FillCountry(document.frmMeeEduActivity.txtLocation, document.frmMeeEduActivity.txtState, 'USA');
	 FillState(document.frmMeeEduActivity.txtLocation, document.frmMeeEduActivity.txtState, '---Select---');
	 
</script>

<script>
	var cal1 = new calendar2(document.forms['frmMeeEduActivity'].elements['txtActDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['frmMeeEduActivity'].elements['checkDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>
</html>
