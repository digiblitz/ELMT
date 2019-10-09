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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmEventRequestReg.js" type="text/javascript"></script>

<script>
function Trim(sString) 
{
	while (sString.substring(0,1) == ' '){
	sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' '){
	sString = sString.substring(0,sString.length-1);
	}
return sString;
}
function Dospace(str){
if(str.indexOf("  ")!=-1)
{return true;}
else {return false;}
}

function myvalidate(){
	if(document.frmEventRequestReg.eventTitle.value==""){
	alert(" Event Title cannot be empty");
	document.frmEventRequestReg.eventTitle.focus();
	return false; 
	}
	if(document.frmEventRequestReg.eventTitle.value.indexOf(' ')==0){
	alert("Enter Event Title ");
	document.frmEventRequestReg.eventTitle.focus();
	return false;
	}	
	if(Dospace(document.frmEventRequestReg.eventTitle.value)){
	alert("Enter Valid Event Title ");
	document.frmEventRequestReg.eventTitle.focus();
	return false;
	}
	if(document.frmEventRequestReg.eventTitle.value.length>255){
	alert("Enter within 255 characters for Event Title" );
	document.frmEventRequestReg.eventTitle.focus();
	return false; 
	}
	if(document.frmEventRequestReg.selState.selectedIndex ==0){
	alert("Select any State");
	document.frmEventRequestReg.selState.focus();
	return false;
	}
	if(document.frmEventRequestReg.txtCity.value==""){
	alert("City can not be empty");
	document.frmEventRequestReg.txtCity.focus();
	return false;
	}
	if(document.frmEventRequestReg.txtCity.value.indexOf(' ')==0){
	alert("Enter City ");
	document.frmEventRequestReg.txtCity.focus();
	return false;
	}	
	if(Dospace(document.frmEventRequestReg.txtCity.value)){
	alert("Enter Valid City ");
	document.frmEventRequestReg.txtCity.focus();
	return false;
	}
	if(Trim(document.frmEventRequestReg.zipCode.value)==""){
	alert("Enter ZipCode");
	document.frmEventRequestReg.zipCode.focus();
	return false;
	}
	if(document.frmEventRequestReg.txtLocation.value==""){
	alert("Location can not be empty");
	document.frmEventRequestReg.txtLocation.focus();
	return false;
	}
	if(document.frmEventRequestReg.txtLocation.value.indexOf(' ')==0){
	alert("Enter Location");
	document.frmEventRequestReg.txtLocation.focus();
	return false;
	}	
	if(Dospace(document.frmEventRequestReg.txtLocation.value)){
	alert("Enter Valid Location");
	document.frmEventRequestReg.txtLocation.focus();
	return false;
	}
	
	if(document.frmEventRequestReg.beginDate.value==""){
	alert("Select Begin Date of the Event");
	document.frmEventRequestReg.beginDate.focus();
	return false;
	}
	var retStr = document.frmEventRequestReg.beginDate.value;
	var currTime = new Date();
	var startTime = new Date();
	retMonth = retStr.substring(0,2);
	retDay = retStr.substring(3,5);
	retYear = retStr.substring(6,retStr.length);
	startTime.setMonth(retMonth-1);
	startTime.setYear(retYear);
	startTime.setDate(retDay);
	if(Number(currTime.getTime()-startTime.getTime())>0){
	alert("Select a future Date for Event Begin Date");
	document.frmEventRequestReg.beginDate.focus();
	return false;
	}
	if(document.frmEventRequestReg.endDate.value==""){
	alert("Select End Date of the Event");
	document.frmEventRequestReg.endDate.focus();
	return false;
	}
	
	var endStr = document.frmEventRequestReg.endDate.value;
	var endTime = new Date();
	endMonth = endStr.substring(0,2);
	endDay = endStr.substring(3,5);
	endYear = endStr.substring(6,retStr.length);
	endTime.setMonth(endMonth-1);
	endTime.setYear(endYear);
	endTime.setDate(endDay);
	if(Number(endTime.getTime()-startTime.getTime())<0){
	alert("Select a future Date for Event End Date");
	document.frmEventRequestReg.beginDate.focus();
	return false;
	}
	
	if(endStr.substring(6,10)!=retYear){
	alert("Enter valid End Date");
	document.frmEventRequestReg.endDate.focus();
	return false;
}
	if(Trim(document.frmEventRequestReg.orgnaizerId.value)==""){
	alert("Enter an Organizer ID");
	document.frmEventRequestReg.orgnaizerId.focus();
	return false;
	}	
	
	/*if(document.frmEventRequestReg.eventType.selectedIndex ==0){
	alert("Select any of the Event Type for the Event");
	document.frmEventRequestReg.eventType.focus();
	return false;
	var retTime = new Date();
	retMonth = retStr.substring(
	retTime.setMonth(retMonth-1);
	retTime.setYear(retYear);
	retTime.setDate(retDay);
	var depDiffTime = depTime.getTime();
	var retDiffTime = retTime.getTime();
	var nowDiffTime = new Date().getTime();
	var diff = Number(retDiffTime - depDiffTime);
	var pastDiff = Number(depDiffTime - nowDiffTime);

	}*/
	var levelCount = document.frmEventRequestReg.levelCount.value;
	var checkSelected = true;
	for (i = 1;  i <= Number(levelCount);i++){
	
	  if (document.getElementById('eventLevels'+i).checked == true){
		checkSelected = false;
	  }
	}
	if (checkSelected){
	  alert("Please select any of the Event Levels");
	  return (false);
	}	
	
return true;
}
</script>
<title><%=(String)session.getAttribute("title")%></title>
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
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">


  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Request for Event Registration  </span></td>
  </tr>
 
  <tr>
  	<td>
	
<form name="frmEventRequestReg" id="frmEventRequestReg" action="./OrganizerHLCEventReg.do" method="post" onsubmit="return myvalidate();"  >
	<input type="hidden" name="process" value="reqInsert">


	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" id="tblGrid">
				<tr>
					<td colspan="2" class="tblRowHead">Register a Event:</td>
				</tr>
				<tr>
				     <td class="tableLeft">Event Title:</td>
				     <td class="tableRight">
					 <input name="eventTitle" type="text" id="eventTitle" class="textboxOne" size="30" maxlength="40" />
				 	 <span class="asterisk">*</span>	
				 	 </td>
		     	</tr>
				<tr>
					<td colspan="2" class="tblRowHead">Event Location:</td>
				</tr>
				<tr>
				     <td class="tableLeft">Country:</td>
				     <td class="tableRight"><b><strong>USA</strong></b></td>
		     	</tr>
				<tr>
				     <td class="tableLeft">State :</td>
				     <td class="tableRight">
						<select name="selState" id="setState" class="selectboxOne" onchange="areaDetials();">
										<option selected="selected" value="">Select One</option>										
										<%
										ArrayList stateDetails = (ArrayList)request.getAttribute("stateDetails");
										if(stateDetails!=null && stateDetails.size()!=0){
										Iterator itr = stateDetails.iterator();
                
                						while(itr.hasNext()){
                						String[] stateDet = (String[])itr.next();
										String stateId = stateDet[0];
										String stateName = stateDet[1];
										String stateCode = stateDet[2];
										
										%>
										
										<option value="<%=stateId%>"><%=stateName%></option>
									 <%
							  }
							}
							  %>
									  </select>
				 	 <span class="asterisk">*</span>
					 </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight"><input type="text" name="txtCity" id="txtCity" class="textboxOne" size="20"/>
					 <span class="asterisk">*</span></td>
					
		     	</tr>
				<tr>
				     <td class="tableLeft">ZipCode:</td>
				     <td class="tableRight">
					 <input name="zipCode" type="text" id="zipCode" class="textboxOne" size="20" maxlength="20" onblur="areaDetials();"/>
				 	 <span class="asterisk">*</span>	
				 					 </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">Area:</td>
				     <td class="tableRight"><input type="text" name="areaTxt" id="areaTxt" readonly="true" class="textboxOne"  size="20"/></td>
					 <input type="hidden" name="areaId" id="areaId" />
		     	</tr>
				<tr>
				     <td class="tableLeft">Location:</td>
				     <td class="tableRight"><input type="text" name="txtLocation" id="txtLocation" class="textboxOne"  size="20"/><span class="asterisk">*</span></td>
					
		     	</tr>				
				<tr> 
            <td class="tableLeft">Begin Date: </td>
            <td class="tableRight"><input type="text" name="beginDate" id="beginDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
          		</tr>
				<tr> 
            <td class="tableLeft">End date: </td>
            <td class="tableRight"><input type="text" name="endDate" id="endDate" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
          		</tr>
		  		<tr>
				     <td class="tableLeft">Organizer ID:</td>
				     <td class="tableRight">
					 <input name="orgnaizerId" type="text" id="orgnaizerId" class="textboxOne" size="20" maxlength="20" onblur="HLCMemberDetails();"/>
				 	 <span class="asterisk">*</span></td>
				</tr>
				<tr>
				     <td class="tableLeft"> Name:</td>
				     <td class="tableRight">
					 <input name="name" type="text" id="name" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Telephone:</td>
				     <td class="tableRight">
					 <input name="phone" type="text" id="phone" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Address:</td>
				     <td class="tableRight">
					 <input name="address" type="text" id="address" class="textboxOne" size="20" readonly="true"/></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight">
					 <input name="city" type="text" id="city" class="textboxOne" size="20" readonly="true"/></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">Fax:</td>
				     <td class="tableRight">
					 <input name="fax" type="text" id="fax" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Email:</td>
				     <td class="tableRight">
					 <input name="email" type="text" id="email" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				

		<tr>
					<td colspan="2" class="tblRowHead">Event Types &amp; Event Levels :<span class="asterisk">*</span></td>
				</tr>
				<%
										Vector eventTypeDetails = (Vector)request.getAttribute("eventTypeDetails");
										HashMap eventTypeMap = (HashMap)request.getAttribute("eventTypeMap");
										int levelCount =0;
										if(eventTypeDetails!=null && eventTypeDetails.size()!=0){
										Enumeration it = eventTypeDetails.elements();
										while(it.hasMoreElements()){
										String[] eventDet =(String[])it.nextElement();
										String eventTypeId = eventDet[0];
										String eventTypeName = eventDet[1];
									%>
				<tr>
				     <td class="tableLeft"><strong><b><%=eventTypeName%></b></strong></div></td>
				     <td class="tableRight">&nbsp;&nbsp;&nbsp; </td>
		     	</tr>
									<%
										ArrayList subLevelMap = (ArrayList)eventTypeMap.get(eventTypeId);
					                    Iterator itrSub = subLevelMap.iterator();
                    					while(itrSub.hasNext()){
				                        String[] subEntry = (String[])itrSub.next();
                				        String mapId = subEntry[0];
				                        String levelId = subEntry[1];
                				        String levelCode = subEntry[2];
				                        String levelName = subEntry[3]; 
										levelCount = levelCount + 1;
										String cbxName = "eventLevels"+levelCount;					
										%>
				<tr>
					<!--<td class="tableLeft"><input type="checkbox" name="<%=cbxName%>" value="<%=mapId%>" id="<%=cbxName%>" />&nbsp;&nbsp;<%=levelCode%></td>-->
				     <td class="tableLeft"><input type="checkbox" name="<%=cbxName%>" value="<%=eventTypeId+"#"+levelId%>" id="<%=cbxName%>" />&nbsp;&nbsp;<%=levelCode%></td>
				     <td class="tableRight">&nbsp;&nbsp;&nbsp; </td>
		     	</tr>
					 			<%
							  }
							}
						}  %>
									 
				 	 <input type="hidden" name="levelCount" id="levelCount" value="<%=levelCount%>" />
					
				 
			    <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" value="Register" class="gradBtn"  />
					&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="location.href='index.jsp'" />					</td>
				</tr>
			</table>
			
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
</html>
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmEventRequestReg'].elements['beginDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['frmEventRequestReg'].elements['endDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>
