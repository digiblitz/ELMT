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
<%@ page import="com.hlccommon.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.math.*"%>
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
 <style type="text/css">
<!--
.style1 {font-weight: bold}
-->
 </style>
</head>

<body>


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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			
<%
String OwnerCountry = "";
String OwnerState = "";

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
					
					<form name="frmMeeEduActivity" id="frmMeeEduActivity" action="eduActivity.do" method="post" enctype="multipart/form-data" onsubmit="return eduActivity();">
					<input type="hidden" name="activityProcess" value="updateDet">
					
							
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Sponsored Educational Activity Application</span></td>
						</tr>
						<!-- <tr>
							<td colspan="2" class="tblDescrp">
							<div id="commonBG" class="textCommon" style="height:100px;"> <img src="images/usea_logo.jpg" class="imgFloatLeft" /><strong><br />
					  				</strong><br />
							</div></td>
						</tr>-->
						<!--<tr>
							<td colspan="2">
								  TABS START HERE  										
								<table cellpadding="0" cellspacing="0" border="0" class="container">
									<tr>
										<td id="tabData1" class="tabHighlight" onclick="naviTab('1')">
											<a id="link1" href="javascript:void(0);" class="active"><span class="tabHead">Part A</span></a>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData2" class="tabLowlight" onclick="naviTab('2')">
											<a id="link2" href="javascript:void(0);" class="inactive"><span class="tabHead">Part B</span></a>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData3" class="tabLowlight" onclick="naviTab('3')">
											<a id="link3" href="javascript:void(0);" class="inactive"><span class="tabHead">Part C</span></a>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData4" class="tabLowlight" onclick="naviTab('4')">
											<a id="link4" href="javascript:void(0);" class="inactive"><span class="tabHead">Part D</span></a>
										</td>
									</tr>	
								</table>
								  TABS END HERE  
							</td>
						</tr>-->
						 
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
							<tr class="rowHead">
							<td colspan="2" class="tableSpan">Required fields are marked with an asterisk (<span class="asterisk">*</span>) </td>
							</tr>
							
							 	<%
									String publicationEmail = "";
									String mailingFormat =  "";
									String mailingBy = "";
									String mailingSortBy = "";
									String  noOfCopies = "";
								
									SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
									ArrayList dispDet = (ArrayList)request.getAttribute("OrgDetailsDisplay");
										HLCActivityOrganizerVO objActDet = new HLCActivityOrganizerVO();
										Vector publication = new Vector();
										String actMeetId ="";
												Iterator it1 = dispDet.iterator();
											if(it1.hasNext()){
											objActDet = (HLCActivityOrganizerVO)it1.next();
																				
											if(objActDet.getLandOwnerCountry()!=null)
											OwnerCountry = objActDet.getLandOwnerCountry();
											
											if(objActDet.getLandOwnerState()!=null)
											OwnerState = objActDet.getLandOwnerState();
											
											publication = (Vector)it1.next();
												actMeetId = objActDet.getActivityMeetingId();											
												Enumeration enumPub = publication.elements();
												if(enumPub.hasMoreElements()){
													publicationEmail = (String)enumPub.nextElement();
													mailingFormat =   (String)enumPub.nextElement(); 
													mailingBy =(String) enumPub.nextElement();
													mailingSortBy =(String) enumPub.nextElement();
													noOfCopies =(String)enumPub.nextElement(); 
												}
												%>
							
								 <tr> 
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Activity Information: </span></td>
							  </tr>
								  <tr>
								    <td class="tableLeft">Name of Activity </td>
								    <td class="tableRight"><label>
								      <input type="text" name="activityName" id="txtActName" class="textboxOne" value="<%=objActDet.getActivityName()%>" />
								      <span class="asterisk">*</span></label></td>
						      </tr>
								  <tr> 
  									<td class="tableLeft"> Date: </td>
									<td class="tableRight">
									<input type="text" class="textboxOne" readonly="true" name="activityDate" id="txtActDate"  value="<%=sdf.format(objActDet.getActivityDate())%>"/>
									<a href="javascript:cal1.popup();">
					  <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
									  <span class="asterisk"> * </span></td>
								  </tr>
								  <tr>
								    <td class="tableLeft">No of Days. </td>
								    <td class="tableRight"><label>
								      <select name="noOfDays" class="textboxOne" id="daysSelect" >
								        <option selected="selected" value="">Days</option>
										<%
											for(int i=1; i<=31; i++){
												int j = 0;
												if(objActDet.getNoOfDays()!=null){
													j = Integer.parseInt(objActDet.getNoOfDays());
												}
												
												if(i==j){
										%>
								        <option value="<%=i%>" selected="selected"><%=i%></option>
								       <%
									   }
									   else{
									   %>
									   <option value="<%=i%>" ><%=i%></option>
									   <%
									   }
									   }
									   %>
					                </select>
								      <span class="asterisk">*</span></label></td>
						      </tr>
							   
								  <tr> 
									<td class="tableLeft"> Area: </td>
									<td class="tableRight"><select name="hlcAreaId" class="textboxOne" id="txtUseaArea" >
                                      <option selected="selected" value="">Select One</option>
									  
                                     <% String areagetId = objActDet.getUseaAreaId();
										ArrayList areaDetails = (ArrayList)session.getAttribute("DispAreaDetails");
										if(areaDetails!=null && areaDetails.size()!=0){
										Iterator areaIt = areaDetails.iterator();
										while(areaIt.hasNext()){
										String[] areaDet =(String[])areaIt.next();
										String areaId = areaDet[0];
										String areaName = areaDet[2];
										String areaCode = areaDet[1];
										if(areaId.equals(areagetId)){
										%>
										
										<option value="<%=areaId%>" selected="selected"><%=areaName%></option>
									 <%}else{
									 %>
									  <option value="<%=areaId%>"> <%=areaName%></option>
									 <%
									 }
									 
							  }
							}
							  %>   
								 </select>
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Location:</td>
									<td class="tableRight"><input type="text" class="textboxOne" name="location" id="txtLocation"  value="<%=objActDet.getLocation()%>"/>
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> State:</td>
									<td class="tableRight"> <input type="text" class="textboxOne" name="state" id="txtState" value="<%=objActDet.getState()%>"/>
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">City:</td>
									<td class="tableRight"><input type="text" name="actCity" id="actCity" class="textboxOne"  size="15" /></td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Zip: </td>
									<td class="tableRight"><input type="text" name="actZipcode" id="actZipcode" class="textboxOne" size="8" /></td>
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
							<td class="tableLeft"> Memberl ID:</td>
							<td class="tableRight">
							<input name="hlcNo" type="text" class="readOnly" size="25" readonly="true" value="<%=memberId%>" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Organizer First Name:</td>
							<td class="tableRight">
							<input name="textbox62" type="text" class="readOnly" size="25" readonly="true" value="<%=first_name%>"/>
							</td>
						  </tr>
						    <tr>
							<td class="tableLeft"> Organizer Middle Name:</td>
							<td class="tableRight">
							<input name="textbox62" type="text" class="readOnly" size="25" readonly="true" value="<%=middle_name%>" />
							</td>
						  </tr>
						  
						    <tr>
							<td class="tableLeft"> Organizer Last Name:</td>
							<td class="tableRight">
							<input name="textbox62" type="text" class="readOnly" size="25" readonly="true" value="<%=last_name%>"/>
							</td>
						  </tr>
						  
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input name="textbox63" type="text" class="readOnly" readonly="true" size="25"value="<%=address1%>"/></td>
						  </tr>
							 <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input name="textbox63" type="text" class="readOnly" readonly="true" size="25" value="<%=address2%>"/></td>
						  </tr>						  
						  <tr> 
							<td class="tableLeft">Country:</td>
							<td class="tableRight"><input name="textbox32" type="text" class="readOnly" readonly="true" size="25"  value="<%=city%>"/></td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">State : </td>
							<td class="tableRight"><input name="textbox6321" type="text" class="readOnly" readonly="true" size="25" value="<%=state%>"/></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">City:</td>
							<td class="tableRight"><input name="textbox632" type="text" class="readOnly" readonly="true" size="25" value="<%=city%>"/></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Zip: </td>
							<td class="tableRight"><input name="textbox633" type="text" class="readOnly"  readonly="true" size="25" value="<%=zip%>"/></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Phone:</td>
							<td class="tableRight"><input name="textbox634" type="text" class="readOnly" readonly="true" size="25" value="<%=phone_no%>"/></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Fax: </td>
							<td class="tableRight"><input name="textbox635" type="text" class="readOnly" readonly="true" size="25" value="<%=fax_no%>"/></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Email ID: </td>
							<td class="tableRight"><input name="textbox635" type="text" class="readOnly" readonly="true" size="25" value="<%=email_id%>"/></td>
						  </tr>



							 </table>
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->
							</td>
						 </tr>
						 
						 <tr>
									<td class="tableLeft"> Type Of Activity : </td>
									<td class="tableRight">
									<select name="selActivityId" id="selActivityId" class="selectboxOne" onChange="getText();">
 									
  										<%
										String activityEdit = objActDet.getActivityTypeId();
										out.print("activityEdit" + activityEdit);
										ArrayList activityDetails = (ArrayList)session.getAttribute("DisplayActivityTypeDetails");
										if(activityDetails!=null && activityDetails.size()!=0){
										Iterator it = activityDetails.iterator();
										while(it.hasNext()){
										String[] eduDet =(String[])it.next();
										String id = eduDet[0];
										String activityName1 = eduDet[1];
										if(id.equals(activityEdit)){
										%>
													  <option value="<%=id%>" selected="selected"><%=activityName1%></option>
													  <%}else{
																						
																						 %>
													  <option value="<%=id%>"> <%=activityName1%></option>
													  <%}
																				}
																				}
																				  %>
													</select><span class="asterisk">*</span></td>
							  </tr>
							 <%
								 String otherActivity = objActDet.getOtherActivityType();
							 	if(otherActivity==null)
								{ otherActivity=""; }%>
								  <tr>


									<td class="tableLeft">If other, Specify:  </td>
									
									<td class="tableRight">
					<input type="text" class="textboxOne" name="otherActivityType" id="txtOthernot" value="<%=otherActivity%>" /></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Fee To Be Charged:  </td>
									<%
									String Amount=objActDet.getActivityFees();
									float pAmt = Float.parseFloat(Amount);
									java.math.BigDecimal bdAmount = new BigDecimal(pAmt);
									bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
									%>				
								<td class="tableRight">
								<strong>$</strong> 
								<input name="activityFees" id="txtFee" type="text" class="textboxOne" size="10" value="<%=bdAmount%>" /></td>
								</tr>
								  <tr>
									<td class="tableLeft"> Instructor(s)/Coach(es):  </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="instructorsCoaches" id="txtCoach" value="<%=objActDet.getInstructorsCoaches()%>" /></td>
								  </tr>
								  
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>
									<td class="tableRight">
										<%
											String facilites = objActDet.getFacilities();
											int lenVal = 0;
											String[] value=null;
											if(facilites!=null){
												value=facilites.split(",");
												//out.println("facility :"+facilites.length());
												//out.println("facility siz:"+value.length);
											lenVal = value.length;
											if(lenVal>=1){
												if(value[0]!=null || value[0].trim().length()!=0) {
												 %>
												<input type="checkbox" name="chkFac1" id="chkFacOne" checked="checked" value="Cross-Country Schooling"/>
												<%
												}
											}	
											else{
											%>
											<input type="checkbox" name="chkFac1" id="chkFacOne"  value="Cross-Country Schooling"/> 
											<%
											}
											%>
											Cross-Country Schooling
												<%
												if(lenVal>=2){
													if(value[1]!=null || value[1].trim().length()!=0) {
														 %>
														<input type="checkbox" name="chkFac2" id="chkFacTwo" checked="checked"  value="Riding Clinic" />
														<%
													}
												}
													else{
														%>
														<input type="checkbox" name="chkFac2" id="chkFacTwo" value="Riding Clinic" />
														<%
													}
												%>
											Riding Clinic
												<%if(lenVal>=3){
													if(value[2]!=null || value[2].trim().length()!=0) {
													%>
													<input type="checkbox" name="chkFac3" id="chkFacThree" checked="checked" value="Stablity" />
													<%
													}
												 }
												else{
													%>
													<input type="checkbox" name="chkFac3" id="chkFacThree" value="Stablity" />
													<%
												}
												%>
											Stablity
											<%
												if(lenVal>=4){
													if(value[3]!=null || value[3].trim().length()!=0) {
													%>
														<input type="checkbox" name="chkFac4" id="chkFacFour" checked="checked" value="Other" onClick="isOtherscheck(); " />	
													<%
													}
												}
												
												else{
													%>
													 <input type="checkbox" name="chkFac4" id="chkFacFour" value="Other" onClick="isOtherscheck();"/>
													<%
												}
											}
											%>
											Other 
									</td>
								</tr>
 
														  
							<tr>
								<td class="tableLeft"> If Other Specify:</td>
								<td class="tableRight">
											<%if(objActDet.getOtherFacilities()!=null){
												 
											%>
											<input type="text" class="textboxOne" name="otherFacilities" id="txtOther"  value="<%=objActDet.getOtherFacilities()%>" /> 
											<%}else{%>
											<input type="text" class="textboxOne" name="otherFacilities" id="txtOther" disabled="disabled"/>
											<%}%>
								</td>			
							</tr>
							<tr>
								<td class="tableLeft"> Do you want Mailing list and Publications? </td>
								<td class="tableRight">
									<input name="pmlRad" id="pmlRad" type="radio" value="yes" onclick="switchDiv('pubMailist');"/>Yes
									<input name="pmlRad" id="pmlRad" type="radio" value="no" onclick="showHideRadio('pmlRad','pubMailist','no');" />No
								</td>
							</tr>
							
							<tr id="pubMailist">
								<td colspan="2">
									<table width="100%" cellpadding="0" cellspacing="0" border="0">
										<tr>
											<td colspan="2" class="tblRowHead"> <span class="rowHead">Publicity And Mailing List: </span></td>
										  </tr>
											  <tr class="tblInnerContainer">
												<td colspan="2" class="tblDescrp">
												<div id="commonBG" class="textCommon" > <strong>User, please take your time and register with the avail the privileges that accompanies with it. <br />
										   
										  Note:<span class="textCommon" style="height:100px;">This registration process does not make you a member.</span><br />
										  You may become an Member by filling up the Membership Application form towards the end of this registration process.<br />
												</strong>            </div></td>
											  </tr>
											  <tr>
												<td class="tableLeft"> Email logo (JPEG): </td>
												<td class="tableRight"><input type="text" class="textboxOne" name="emailLogo" id="txtLogo" value="<%=publicationEmail%>" /> (For Publicity Use Only)</td>
											  </tr>
											  <tr>
												<td class="tableLeft"> Complimentary Mailing List Format: </td>
												<td class="tableRight">
													<%
														if(mailingFormat.equals("Electronic .csv file")){
													%>
													<input name="radioCompMail" type="radio" id="rdoButton" value="Electronic .csv file"  checked="checked"/>Electronic .csv file
													<%
													}
													else{
													%>
													<input name="radioCompMail" type="radio" id="rdoButton" value="Electronic .csv file" />Electronic .csv file
													<%
													}
													if(mailingFormat.trim().equals("Hard Copy")){
													%>	
													<input name="radioCompMail" type="radio" id="rdoButton" checked="checked" value="Hard Copy " />Hard Copy 
													<%
													}
													else{
													%>
													<input name="radioCompMail" type="radio" id="rdoButton" value="Hard Copy " />Hard Copy 
													<%
													}
													%>
												
												</td>
											  </tr>
											  <tr>
												<td height="27" class="tableLeft">Send Mailing List by :</td>
												<td class="tableRight">
													
													<%
														if(mailingBy.equals("Areas")){
													%>
													<input name="radioSendMail" type="radio" id="rdoButton1" checked="checked" value="Areas" onclick="switchDiv('enterAre'); showHideRadio('radioSendMail','enterSta','Areas');"/>Areas
													<%
													}
													else{
													%>
													<input name="radioSendMail" type="radio" id="rdoButton1" value="Areas" onclick="switchDiv('enterAre'); showHideRadio('radioSendMail','enterSta','Areas');"/>Areas
													<%
													}
													if(mailingBy.equals("States")){
													%>
													<input name="radioSendMail" type="radio" id="rdoButton1" checked="checked" value="States" onclick="switchDiv('enterSta'); showHideRadio('radioSendMail','enterAre','States');"/>States
													
													<%
													}
													else{
													%>
													<input name="radioSendMail" type="radio" id="rdoButton1" value="States" onclick="switchDiv('enterSta'); showHideRadio('radioSendMail','enterAre','States');"/>States
													<%
													}
													%>
													 									
												 </td>
											  </tr>
											  <tr id="enterAre">
													<td class="tableLeft"> Enter Areas:</td>
													<td class="tableRight"><input name="areaStatus" type="text" class="textboxOne" id="areaStatus" size="15" />
													<span class="asterisk">* </span>(E.g. 1,2,3,4) 
													</td>
											  </tr>
											  <tr id="enterSta">
													<td class="tableLeft"> Enter States:</td>
													<td class="tableRight"><input name="areaState" type="text" class="textboxOne" id="areaState" size="15" />
													  <span class="asterisk">* </span>(E.g. VA,MD,PA) 
													</td>
											  </tr>
											  <tr>
												<td class="tableLeft">Sort Mailing List by :</td>
												<td class="tableRight">
												<%
												if(mailingSortBy.equals("Zip")){
												%>	
												<input name="radioSortMail" type="radio" id="rdoButton2" checked="checked" value="Zip" />Zip
												<%
												}
												else{
												%>
												<input name="radioSortMail" type="radio" id="rdoButton2" value="Zip" />Zip
												<%
												}
												if(mailingSortBy.trim().equals("Alpha by last name")){
												%>
												<input name="radioSortMail" type="radio" id="rdoButton2" checked="checked" value="Alpha by last name " />Alpha by last name 
												<%
												}
												else{
												%>
												<input name="radioSortMail" type="radio" id="rdoButton2" value="Alpha by last name" />Alpha by last name 
												<%
												}
												%>
												
												</td>
											  </tr>
											  <tr>
												<td class="tableLeft">Number of copies of the colour brochure :</td>
												<td class="tableRight"><input type="text" class="textboxOne" name="noOfCopies" id="txtCopies" value="<%=noOfCopies%>" /></td>
											  </tr>
										</table>
									</td>
								</tr>
					  <tr id="part3" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 3 of the form starts here ++++++++++++++++++++++++++++++ -->	
						<table cellpadding="0" cellspacing="0" border="0" class="formLayout">

						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Land Owner Details : </span></td>
						  </tr>
						  <tr>
							<td class="tableLeft">Land Owner Name :</td>
							<td class="tableRight">
							<input type="text" class="textboxOne" name="landOwnerName" id="txtLandOwner" value="<%=objActDet.getLandOwnerName()%>" />
							  <span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerBusinessName"  value="<%=objActDet.getLandOwnerBusinessName()%>" id="txtBusiName" />
							  <span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerAddress" id="txtLandAddress" value="<%=objActDet.getLandOwnerAddress()%>" />
							  <span class="asterisk">*</span></td>
						  </tr>
						     <%
							 if (OwnerCountry == "") {
								OwnerCountry = "USA";
							}
							 if (OwnerState == "") {
								OwnerState = "Washington D.C";
							}
							%>
						  
						  <tr>
							<td height="27" class="tableLeft">Country: </td>
							<td class="tableRight">
							 <select name="landOwnerCountry" id="landOwnerCountry" class="selectboxOne" onChange="FillState(document.frmMeeEduActivity.landOwnerCountry, document.frmMeeEduActivity.landOwnerState, '');"> 
							 <option value="<%=OwnerCountry%>" selected="selected"><%=OwnerCountry%></option>	
								</select>
							 <span class="asterisk">*</span> </td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight">
							<select name="landOwnerState" id="landOwnerState" class="selectboxOne">
							 <option value="<%=OwnerState%>" selected="selected"><%=OwnerState%></option>	
							  </select>
							<span class="asterisk">*</span> </td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerCity" id="txtLandCity" value="<%=objActDet.getLandOwnerCity()%>" />
							  <span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Zip Code: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerZip" id="txtLandZip" value="<%=objActDet.getLandOwnerZip()%>" />
							  <span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Telephone: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="landOwnerPhone" id="txtLandPhone" value="<%=objActDet.getLandOwnerPhone()%>">
							  <span class="asterisk">*</span></td>
						  </tr>
						 
						    <tr>
						  <td colspan="2" height="27" class="tableSpan">
						   <%
							boolean addSites = objActDet.isAdditionalSites();
							//out.println(addSites);
							 if(addSites){%>
							 <input type="checkbox" name="additionalSites" id="chkAddSites" checked="checked" value="yes" onclick="browseCheck();" />
							<% }else{
							 %>
							<input type="checkbox" name="additionalSites" id="chkAddSites" value="yes" onclick="browseCheck();" />
							
							<%}%>
							Check if Additional Sites are to be used.</td>
						  </tr> 
						  <tr>
							<td class="tableLeft style1">Additional Site Information: </td>
							<td class="tableRight">
						  <%
						   	 String addPath = objActDet.getAdditionalSitesPath();
						     if(addPath!=null){%>
						     
						 		<textarea name="comments" id="txtUpload" class="textAreaOne" rows="5"><%=addPath%></textarea>
							 <%}
							 else{
							 %>
							  <textarea name="comments" id="txtUpload" class="textAreaOne" disabled="disabled" rows="5"></textarea>
							<% }%></td>
						  </tr>
						    <input type="hidden" name="actMeetId" value="<%=actMeetId%>">
						  <%
						  }
						  %>
						
						  <tr> 
							<td colspan="2" class="alignCenter"><input type="submit" class="gradBtn" value="Submit" />							 </td>
						  </tr>
						</table>
						<!--++++++++++++++++++++ Part 3 of the form ends here ++++++++++++++++++++++++++++++ -->		
					
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
	FillCountry(document.frmMeeEduActivity.landOwnerCountry, document.frmMeeEduActivity.landOwnerState, '<%=OwnerCountry%>');
	FillState(document.frmMeeEduActivity.landOwnerCountry, document.frmMeeEduActivity.landOwnerState, '<%=OwnerState%>');
</script>
		
<script>
	var cal1 = new calendar2(document.forms['frmMeeEduActivity'].elements['txtActDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;

</script>
</html>
