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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmEvntOrgRenewal.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script language="JavaScript" src="javascripts/calendar2.js" type="text/javascript"></script>
<script language="JavaScript">
// JavaScript Document

<script language="JavaScript">
var req;
function userDetails(param){
      var  url = "memberInfo.do?method=userDetails&uid="+escape(param.value);
        if (window.ActiveXObject){ 
            req = new ActiveXObject("Microsoft.XMLHTTP"); 
       } 
        else if (window.XMLHttpRequest){ 
            req = new XMLHttpRequest(); 
        } 
		req.onreadystatechange = processRequest; 
         req.open("GET", url, true);
         req.send(null);  
   }  
     function processRequest(){ 
        if (req.readyState == 4){ 
            if(req.status == 200){ 
                //get the XML send by the servlet 
                 var xmlDoc = req.responseXML.documentElement;
                 var prefix =xmlDoc.getElementsByTagName('prefix')[0].childNodes[0].nodeValue; 
				 var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue; 
				 var middleName =xmlDoc.getElementsByTagName('middleName')[0].childNodes[0].nodeValue; 
				 var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
				 var suite =xmlDoc.getElementsByTagName('suite')[0].childNodes[0].nodeValue; 
				 var address1 =xmlDoc.getElementsByTagName('address1')[0].childNodes[0].nodeValue; 
				 var address2 =xmlDoc.getElementsByTagName('address2')[0].childNodes[0].nodeValue; 
				 var city =xmlDoc.getElementsByTagName('city')[0].childNodes[0].nodeValue; 
				 var state =xmlDoc.getElementsByTagName('state')[0].childNodes[0].nodeValue; 
				 var zip =xmlDoc.getElementsByTagName('zip')[0].childNodes[0].nodeValue; 
				 var phone =xmlDoc.getElementsByTagName('phone')[0].childNodes[0].nodeValue; 
				 var mobile =xmlDoc.getElementsByTagName('mobile')[0].childNodes[0].nodeValue; 
				 var emailId =xmlDoc.getElementsByTagName('emailId')[0].childNodes[0].nodeValue; 
				 var userId =xmlDoc.getElementsByTagName('userId')[0].childNodes[0].nodeValue;

              document.getElementById("participantId").value=firstName+' '+middleName+' '+lastName;
			  document.getElementById("addressId").value=address1+'\n'+address2;
			  document.getElementById("salutationId").value=prefix;
			  document.getElementById("cityId").value=city;
			  document.getElementById("stateId").value=state;
			  document.getElementById("zipId").value=zip;
			  document.getElementById("phoneId").value=phone;
			  document.getElementById("mobileId").value=mobile;		
			  document.getElementById("emailId").value=emailId;
			  document.getElementById("streetId").value=address2;	
               
            } 
            else{ 
                alert("Error loading page\n"+ req.status +":"+ req.statusText); 
            } 
        } 
    } 


</script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 

</head>
<body onload="amtFocus();">
<div class="cmmnForm">
	<div class="colspan">
		<strong>Event Registration USEF Endorsed Competition/Management Application </strong>
	</div>
  <div id="commonBG" class="textCommon">
  		All Competitions must complete Section One (page 1).
		Endorsed Competitions/Levels that are Recognized/USEF Endorsed
		events offering Training, Novice, Beginner Novice and Tests) must
		complete sections Two and Three (pages 2 & 3).

		<br />
		<strong>By the following date - note fee structure below:</strong>
		MAY 5, 2006 (grace period to avoid the late fee is thru May 10, 2006)
		Fall Events (September 4 through November 30). <br />
	  <strong>Fees:</strong> $400 ($150 event registration/$250 omnibus listing) per event - postmark/fax/email prior or on May 5, 2006.
		<br />
	  <strong>Late fees:</strong>	$525 ($125 assessed late fee) per event - postmark/fax/email after May 10, 2006.
		Registrations and Listings may not be accepted after May 19.


</div>
<form name="frmEvntOrgRenewal"  method="post" class="formcss" action="EventOrgRenewal.do" onSubmit="return onEvnRenCheck();" >
<input type="hidden" name="eventProcess" value="insert">

    <div class="rowHead">
		<span class="asterisk">*</span> Fields mark with asterisk(*) are mandatory
	</div>

	<div class="rowHead">
		Payments /General Information
	</div>
	<div class="row"> <span class="label">Total Amount:</span> <span class="formX">
	  <input  type="text" name="amount" id="amount" class="textboxOne" size="10" />
    <strong>$</strong><span class="asterisk">*</span></span>  </div>
			<div class="row">
			  <span class="floatLeft">
			  <input type="radio" size="10" name="r11"  value="check" onclick="checkClear(),switchDiv('checkEncl'), showHideRadio('r11','chargeCard','check');" >
			  </span>
			  <span class="floatLeft">
				  Check enclosed.
			  </span>
			  <span class="floatLeft">
			  <input type="radio" size="10" name="r11" value="card" onclick="cardClear(),switchDiv('chargeCard'), showHideRadio('r11','checkEncl','card');" ></span>
			  <span class="floatLeft">
				  Please charge my card.
			  </span>
			</div>

					<div id="checkEncl">
							<div class="colspan">
								<strong>Check Details:</strong>
							</div>
							<div class="row">
								<span class="label">Check No:</span>
								<span class="formX"><input type="text" name="checkNumber"  id="checkNumber" class="textboxOne" size="16" />
								<span class="asterisk">*</span></span>
							</div>
					  <div class="row"> <span class="label">Check Date:</span> <span class="formX">
					    <input type="text" name="checkDate"  id="checkDate" readonly="true" class="textboxOne" size="10" />
				      <a href="javascript:cal1.popup();">
					  <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">					  </a><span class="asterisk">*</span>
					  </span>						</div>
							<div class="row">
								<span class="label">Bank Name:</span>
								<span class="formX"><input type="text" name="bankName" id="bankName" class="textboxOne" size="30" />
								<span class="asterisk">*</span></span>
							</div>
					</div>

					<div id="chargeCard">
							<div class="colspan">
								<strong>Card Details:</strong>
							</div>
							<div class="row">
								<span class="label">Card Type:</span>
								<span class="formX">
								<select name="ccType" id="ccType" class="sat-SearchBox">
								  <option value="" selected="selected" >Select One</option>
								  <option value="Visa">Visa</option>
								  <option value="Master Card">Master Card</option>
								  <option value="AmEx">AmEx</option>
								</select>
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Card No:</span>
								<span class="formX"><input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="20" />
								<span class="asterisk">*</span></span>
							</div>

							<div class="row">
								<span class="label">CVV No.:</span>
								<span class="formX"><input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Print Name On Card:</span>
								<span class="formX"><input type="text" name="ccName" id="ccName" class="textboxOne" size="15"/>
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Expiry Date:</span>
								<span class="formX">
									<select name="ccExpMonth" id="ccExpMonth" class="sat-SearchBox">
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
									<select name="ccExpYear" id="ccExpYear" class="sat-SearchBox">
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
								</select>
								<span class="asterisk">*</span></span>
							</div>
					</div>
	<div class="rowHead">
		Section One:
	</div>
			<div class="row">
				<span class="label">Name of Competition:</span>
				<span class="formX">
				<input type="text" name="competitionName" id="competitionName"  class="textboxOne" size="45" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Location of Competition:</span>
				<span class="formX"><input type="text" name="competitionLocation" id="competitionLocation" class="textboxone" size="20" />
				<span class="asterisk">*</span></span>
			</div>
            <div class="row">
				<span class="label">City:</span>
				<span class="formX"><input type="text" name="competitionCity" id="competitionCity" class="textboxOne"  size="15" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">State</span>
				<span class="formX">
				<SELECT name="competitionState" id="competitionState" class="selectboxOne" style="width:200" width="200" >
				</SELECT>
				<span class="asterisk">*</span></span>

			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><input type="text" name="competitionZip" id="competitionZip" class="textboxOne" size="8" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Country</span>
				<span class="formX">
				<SELECT name="competitionCountry" id="competitionCountry" class="selectboxOne"  width="200" 					onChange="FillState(document.frmEvntOrgRenewal.competitionCountry, document.frmEvntOrgRenewal.competitionState, '');">
				</SELECT>
				<span class="asterisk">*</span></span>
			</div>

			<div class="row">
				<span class="label">Date of Competition:</span>
				<span class="formX">
				<input type="text" name="competitionDate" id="competitionDate" readonly="true" class="textboxOne" size="15" />
				<a href="javascript:cal2.popup();">
				<img src="images/calendar.jpg"   alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">				</a><span class="asterisk">*</span>				</span>			</div>
			<div class="row">
			<span class="label">Area Championship:</span><span class="formX">
			<select name="championshipArea" id="championshipArea" class="selectboxOne" >
              <option value="" selected="selected">Select One</option>
              <option value="">Area One</option>
              <option value="">Area Two</option>
            </select>
			<span class="asterisk">*</span></span></div>
			<div class="row">
				<span class="label">Salutation:</span>
				<span class="formX">
				<input type="text" class="textboxOne" name=rprefix readonly="true" size="10" />
				</span>
			</div>
			<div class="row">
				<span class="label"> Event Organiser Name:</span>
				<span class="formX">
				<input type="text"  readonly="true"  name=rName size="20" />&nbsp;
				 No.: <input type="text" class="textboxOne" name=hlcNo onblur="details();" size="10" /></span>
			</div>
			<div class="row">
				<span class="label">Street:</span>
				<span class="formX"><input type="text"  class="textboxOne"  readonly="true" name=rStreet size="20" /></span>
			</div>
			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><input type="text" class="textboxOne" readonly="true" name=rCity size="15" /></span>
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX"><input type="text" class="textboxOne" readonly="true" name=rState size="20" /></span>
			</div>
			<div class="row">
			<span class="label">Zipcode:</span><span class="formX">
			<input type="text" class="textboxOne" readonly="true" name=rZipcode size="8" />
			</span></div>
				<div class="row">
				<span class="label">Phone:</span>
				<span class="formX"><input type="text" class="textboxOne" readonly="true" name=rPhone size="15" /></span>
			</div>
			<div class="row">
				<span class="label">Cell:</span>
				<span class="formX"><input type="text" class="textboxOne" readonly="true" name=rCell size="15" /></span>
			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX"><input type="text" class="textboxOne" readonly="true" name=rEmail size="20" /></span>
			</div>
	<div class="rowHead">
		Section Two:
	</div>
			<div id="commonBG" class="textCommon">
				<strong>USEF defines Competition Management as follows:</strong>  Shall be the party who enters
				 into the agreement with USEF and is therefore financially and otherwise responsible
				 (i.e., compliance with all USEF rules, policies and procedures) to USEF for the
				 licensed competition.
			</div>

			<div class="row">
				<span class="label">Name of Competition Management:</span>
				<span class="formX"><input type="text" name="comManagementName" id="comManagementName" class="textboxOne" size="20" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Address of principal place of business:</span>
				<span class="formX"><input type="text" name="comManagementAddress" id="comManagementAddress" class="textboxOne" size="30" />
				<span class="asterisk">*</span></span>
			</div>
               <div class="row">
				<span class="label">City:</span>
				<span class="formX"><input type="text" name="myCity" id="myCity" class="textboxOne" size="15" />
				<span class="asterisk">*</span></span>
			</div>
		<div class="row">
			<span class="label">State:</span>
			<span class="formX">
			<select name="myState" id="myState" class="selectboxOne" style="width:200" width="200">
			</select>
			<span class="asterisk">*</span></span>
			</div>
		<div class="row">
			<span class="label">Zipcode:</span>
			<span class="formX"><input type="text" name="comManagementZip"  id="comManagementZip"  class="textboxOne" size="8" />
			<span class="asterisk">*</span></span>
			</div>
		<div class="row">
		<span class="label">Country:</span>
		<span class="formX">
		<select name="myCountry" id="myCountry" class="selectboxOne" style="width:200" width="200"  
		onChange="FillState(document.frmEvntOrgRenewal.myCountry, document.frmEvntOrgRenewal.myState, '')">
		</select>
		<span class="asterisk">*</span></span>
        </div>
	
			<div class="row">
				<span class="label">Telephone Number:</span>
				<span class="formX"><input type="text" name="comManagementPhone" id="comManagementPhone" class="textboxOne" size="15" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Fax Number:</span>
				<span class="formX"><input type="text" name="comManagementFax" id="comManagementFax" class="textboxOne" size="15" />
				</span>
			</div>
			<div class="colspan">
				<strong>Competition Manager/Organizer Information</strong>
			</div>
			<div class="row">
				<span class="label">Competition Manager/Organizer Name:</span>
				<span class="formX"><input type="text" name="managerName" id="managerName" class="textboxOne" size="30" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Check box if USEF member:</span>
				<span class="formX">
				<input type="checkbox" name="USEFMgrCheckValue" id="USEFMgrCheckValue" size="15" class="threepxfix" onclick="enabDisab('USEFMgrCheckValue','managerUsefMemberId');"/></span>
			</div>
			<div class="row">
				<span class="label">USEF Membership No.:</span>
				<span class="formX"><input type="text" name="managerUsefMemberId" id="managerUsefMemberId"  class="textboxOne" disabled="disabled"  size="30"/>
				</span>
			</div>
			<div class="row">
				<span class="label">Membership No.:</span>
				<span class="formX"><input type="text" name="managerUseaMemberId"  id="managerUseaMemberId"  class="textboxOne" size="30" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="colspan">
				<strong>Member Secretary Information **</strong>
			</div>
			<div id="commonBG" class="textCommon">
				<strong>**</strong> A Secretary's information is only required if neither the Manager/Organizer nor Secretary is a USEF Senior Member.
			</div>
			<div class="row">
				<span class="label">Member Secretary Name:</span>
				<span class="formX"><input type="text" name="secretaryName"  id="secretaryName"  class="textboxOne" size="30" />
				</span>
			</div>
			<div class="row">
				<span class="label">Check box if USEF member:</span>
				<span class="formX"><input type="checkbox" id="USEFSecretaryCheckValue" name="USEFSecretaryCheckValue" size="15" class="threepxfix" onclick="enabDisab('USEFSecretaryCheckValue','secretaryUsefMemberId');"/></span>
			</div>
			<div class="row">
				<span class="label">USEF Membership No.:</span>
				<span class="formX"><input type="text" name="secretaryUsefMemberId" id="secretaryUsefMemberId" class="textboxOne" disabled="disabled"  size="30" />
				</span>
			</div>
		<div class="row">
		<span class="label">&nbsp;</span>
		<span class="formX"><input type="submit" name="submit" value="Register Now" class="gradBtn" /></span>
		</div>

	<!--
	<div class="row">
		<span class="label">Comments:</span>
		<span class="formX"><textarea cols="25" rows="8">Go ahead - write something...</textarea></span>
	</div>
	-->
	<div id="spacer">&nbsp;</div>

</form>
</div>
</body>
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmEvntOrgRenewal'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['frmEvntOrgRenewal'].elements['competitionDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>

<script>
	FillCountry(document.frmEvntOrgRenewal.competitionCountry, document.frmEvntOrgRenewal.competitionState, '---Select---');
	FillState(document.frmEvntOrgRenewal.competitionCountry, document.frmEvntOrgRenewal.competitionState, '');
</script>

<script>
	FillCountry(document.frmEvntOrgRenewal.myCountry, document.frmEvntOrgRenewal.myState, '---Select---');
	FillState(document.frmEvntOrgRenewal.myCountry, document.frmEvntOrgRenewal.myState, '');
</script>
</html>
