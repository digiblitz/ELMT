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
<%@ page import="java.text.*"%>
<%@ page import ="com.hlcmro.util.HLCEventRequestVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmEditEventRequestReg.js" type="text/javascript"></script>

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

function onValidate(){
	if(document.frmEditEventRequestReg.eventTitle.value==""){
	alert(" Event Title cannot be empty");
	document.frmEditEventRequestReg.eventTitle.focus();
	return false; 
	}
	if(document.frmEditEventRequestReg.eventTitle.value.indexOf(' ')==0){
	alert("Enter Event Title ");
	document.frmEditEventRequestReg.eventTitle.focus();
	return false;
	}	
	if(Dospace(document.frmEditEventRequestReg.eventTitle.value)){
	alert("Enter Valid Event Title ");
	document.frmEditEventRequestReg.eventTitle.focus();
	return false;
	}
	if(document.frmEditEventRequestReg.eventTitle.value.length>255){
	alert("Enter within 255 characters for Event Title" );
	document.frmEditEventRequestReg.eventTitle.focus();
	return false; 
	}
	if(document.frmEditEventRequestReg.txtCity.value==""){
	alert("City can not be empty");
	document.frmEditEventRequestReg.txtCity.focus();
	return false;
	}
	if(document.frmEditEventRequestReg.txtCity.value.indexOf(' ')==0){
	alert("Enter City ");
	document.frmEditEventRequestReg.txtCity.focus();
	return false;
	}	
	if(Dospace(document.frmEditEventRequestReg.txtCity.value)){
	alert("Enter Valid City ");
	document.frmEditEventRequestReg.txtCity.focus();
	return false;
	}
	if(document.frmEditEventRequestReg.txtLocation.value==""){
	alert("Location can not be empty");
	document.frmEditEventRequestReg.txtLocation.focus();
	return false;
	}
	if(document.frmEditEventRequestReg.txtLocation.value.indexOf(' ')==0){
	alert("Enter Location");
	document.frmEditEventRequestReg.txtLocation.focus();
	return false;
	}	
	if(Dospace(document.frmEditEventRequestReg.txtLocation.value)){
	alert("Enter Valid Location");
	document.frmEditEventRequestReg.txtLocation.focus();
	return false;
	}
	
	if(document.frmEditEventRequestReg.beginDate.value==""){
	alert("Select a Begin Date of the Event");
	document.frmEditEventRequestReg.beginDate.focus();
	return false;
	}
	var retStr = document.frmEditEventRequestReg.beginDate.value;
	var currTime = new Date();
	var startTime = new Date();
	retMonth = retStr.substring(0,2);
	retDay = retStr.substring(3,5);
	retYear = retStr.substring(6,retStr.length);
	startTime.setMonth(retMonth-1);
	startTime.setYear(retYear);
	startTime.setDate(retDay);
	/*if(Number(currTime.getTime()-startTime.getTime())>0){
	alert("Select a future Date for Event Begin Date");
	document.frmEditEventRequestReg.beginDate.focus();
	return false;
	}*/
	var tempYrs = document.frmEditEventRequestReg.tempYr.value;
	
	if(retStr.substring(6,10)!=tempYrs){
	alert("Enter valid Begin Date");
	document.frmEditEventRequestReg.beginDate.focus();
	return false;
    }
	if(document.frmEditEventRequestReg.endDate.value==""){
	alert("Select a End Date of the Event");
	document.frmEditEventRequestReg.endDate.focus();
	return false;
	}
	
	var endStr = document.frmEditEventRequestReg.endDate.value;
	var endTime = new Date();
	endMonth = endStr.substring(0,2);
	endDay = endStr.substring(3,5);
	endYear = endStr.substring(6,retStr.length);
	endTime.setMonth(endMonth-1);
	endTime.setYear(endYear);
	endTime.setDate(endDay);
	if(Number(endTime.getTime()-startTime.getTime())<0){
	alert("Select a future Date for Event End Date");
	document.frmEditEventRequestReg.beginDate.focus();
	return false;
	}
	if(endStr.substring(6,10)!=retYear){
	alert("Enter valid End Date");
	document.frmEditEventRequestReg.endDate.focus();
	return false;
}
	if(Trim(document.frmEditEventRequestReg.orgnaizerId.value)==""){
	alert("Enter an Organizer ID");
	document.frmEditEventRequestReg.orgnaizerId.focus();
	return false;
	}	
	if(document.frmEditEventRequestReg.selIssue.selectedIndex ==0){
	alert("Select any Season");
	document.frmEditEventRequestReg.selIssue.focus();
	return false;
	}
	/*if(document.frmEditEventRequestReg.eventType.selectedIndex ==0){
	alert("Select any of the Event Type for the Event");
	document.frmEditEventRequestReg.eventType.focus();
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
	var levelCount = document.frmEditEventRequestReg.levelCount.value;
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
function focus_login()
{
	document.frmEditEventRequestReg.eventTitle.focus();
}	
</script>
<title>Integrated Enterprise Dashboard</title>
</head>

<body onload="HLCMemberDetails();">
<%! 
String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>

 <%! 				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM/dd/yyyy");
					
					String dateFormat(String fieldName){					
						java.util.Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
					%>
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
    <td colspan="2" class="tblMainHead"><strong>HLC</strong> Meetings:<span class="styleBoldTwo">Edit Event Registration  </span></td>
  </tr>
 
  <tr>
  	<td>
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" id="tblGrid">
				<tr>
					<td colspan="2" class="tblRowHead">Edit  Event Details:</td>
				</tr>
				
				<% 
				
				HLCEventRequestVO objEventReqVO = new HLCEventRequestVO();
				objEventReqVO = (HLCEventRequestVO)request.getAttribute("EVENT_REQUEST_VO");
				
			 String event_id = "";
             String organizer_id = "";
             String event_title = "";
           		 		 
             String competition_location = "";
             String competition_city = "";
             String competition_state = "";
             String competition_country = "";
             String competition_zip = "";
             String championship_area = "";
             String status_id = "";
			 String eveIssId="";
			 String eveIssName="";
			 int tempYr=0;
			 ArrayList mappingTypeIds = new ArrayList();
			 
			 String eventSubTypes = "";
			 java.util.Date eventBeginDate=null;
			 java.util.Date eventEndDate=null;
			 
			 if(objEventReqVO!= null ){
			  event_id = objEventReqVO.getEvent_id();
			  System.out.println("event_id in jsp" +event_id);
              organizer_id = objEventReqVO.getOrganizer_id();
			  System.out.println("organizer_id in jsp" +organizer_id);
              event_title = objEventReqVO.getEvent_title();
              eventBeginDate = objEventReqVO.getEveBegDate();		 
			
              eventEndDate = objEventReqVO.getEveEndDate();			 			
              competition_location = objEventReqVO.getCompetition_location();
			  System.out.println("competition_location in jsp" +competition_location);
              competition_city = objEventReqVO.getCompetition_city();
              competition_state = objEventReqVO.getCompetition_state();
              competition_country = objEventReqVO.getCompetition_country();
              competition_zip = objEventReqVO.getCompetition_zip();
              championship_area = objEventReqVO.getChampionship_area();
              status_id = objEventReqVO.getStatus_id();
			  eveIssId = objEventReqVO.getIssueId();
			  eveIssName = objEventReqVO.getIssueName();
			  tempYr = objEventReqVO.getCompYear();
			  System.out.println("tempYr in jsp" + tempYr);
			  mappingTypeIds = objEventReqVO.getMaping_type_id();
			  	if(mappingTypeIds!= null && mappingTypeIds.size()!=0){	  
                	Iterator itrMapTypeIds = mappingTypeIds.iterator();
                
	                while(itrMapTypeIds.hasNext()){
                    String mappingTypeId = (String)itrMapTypeIds.next();
                    eventSubTypes = mappingTypeId+"#"+eventSubTypes;
    	            }
				}
			}
			String areaId = "";
			String areaStr = "";
			String[] areaDetails = (String []) request.getAttribute("AREA_DETAILS");
			if(areaDetails != null){
			 areaId = areaDetails[0];
			 areaStr = areaDetails[1];
			 }
			
				%>
	<form name="frmEditEventRequestReg" id="frmEditEventRequestReg" action="./eventRegList.do" method="post" onsubmit="return onValidate();"  >
	<input type="hidden" name="cmd" value="updateEventRequestReg">
<input type="hidden" name="eventId" id="eventId" value="<%=event_id%>" />
<input type="hidden" name="tempYr" id="tempYr" value="<%=tempYr%>" />					
				<tr>
				     <td class="tableLeft">Event Title:</td>
				     <td class="tableRight">
					 <input name="eventTitle" type="text" id="eventTitle" class="textboxOne" size="30" maxlength="40" value="<%=event_title%>"/>
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
						<%
						boolean stateStatus = true;
						String stateId="";
										ArrayList stateDetails = (ArrayList)request.getAttribute("stateDetails");
										if(stateDetails!=null && stateDetails.size()!=0){
										Iterator itr = stateDetails.iterator();
                
                						while(itr.hasNext()){
                						String[] stateDet = (String[])itr.next();
										 stateId = stateDet[0];										
										String stateName = stateDet[1];
										String stateCode = stateDet[2];
										if(competition_state!=null && competition_state.equalsIgnoreCase(stateId)){
										stateStatus = false;
										%>										
				 <input type="text" name="selState"  id="selState" class="textboxOne" readonly="true" size="20" maxlength="20" value="<%=stateName%>"/>
				 <input type="hidden" name="stateId" id="stateId" value="<%=stateId%>" />
										<%}								
							  }
							} %>
					 </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight"><input type="text" name="txtCity" id="txtCity" class="textboxOne" size="20" value="<%=nullCheck(competition_city)%>" /><span class="asterisk">*</span></td>					
		     	</tr>
				
				<tr>
				     <td class="tableLeft">ZipCode:</td>
				     <td class="tableRight">
					 <input name="zipCode" type="text" id="zipCode" class="textboxOne" readonly="true" size="20" maxlength="20" value="<%=competition_zip%>"/>				 	 
				 					 </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">Area:</td>
				     <td class="tableRight"><input type="text" name="areaTxt"  id="areaTxt" class="textboxOne" readonly="true" size="20" maxlength="20" value="<%=areaStr%>"/>	</td>
					 <input type="hidden" name="areaId" id="areaId" value="<%=areaId%>" />
		     	</tr>
				<tr>
				     <td class="tableLeft">Location:</td>
				     <td class="tableRight"><input type="text" name="txtLocation" id="txtLocation" class="textboxOne" size="20" value="<%=nullCheck(competition_location)%>"/><span class="asterisk">*</span></td>
					
		     	</tr>
				<tr> 
            <td class="tableLeft">Begin Date: </td>
            <td class="tableRight"><input type="text" name="beginDate" id="beginDate" value="<%=dateFormat(eventBeginDate.toString())%>" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
          		</tr>
				 
				<tr> 
            <td class="tableLeft">End date: </td>
            <td class="tableRight"><input type="text" name="endDate" id="endDate" value="<%=dateFormat(eventEndDate.toString())%>" class="textboxOne" size="25" readonly="true"/><a href="javascript:cal2.popup();"><img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a><span class="asterisk">*</span></td>
          		</tr>
				
				<tr>
				     <td class="tableLeft">Organizer ID:</td>
				     <td class="tableRight">
					 <input name="orgnaizerId" type="text" id="orgnaizerId" value="<%=organizer_id%>" class="textboxOne" size="20" maxlength="20" onblur="HLCMemberDetails();"/>
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
				     <td class="tableLeft">Season:</td>
				     <td class="tableRight">
						<select name="selIssue" id="selIssue" class="selectboxOne" >
										<option selected="selected" value="">Select One</option>										
										<%
										ArrayList issDetails = (ArrayList)request.getAttribute("issueDetails");
										if(issDetails!=null && issDetails.size()!=0){
										Iterator itr = issDetails.iterator();               
                						while(itr.hasNext()){
                						String[] issueDet = (String[])itr.next();
										String issueId = issueDet[0];
										String issueName = issueDet[1];
									if(eveIssId!=null && eveIssId.equalsIgnoreCase(issueId)){	
								
										%>										
										<option selected="selected" value="<%=issueId%>"><%=issueName%></option>
									 <%
									 }else {
									 %>
									 <option value="<%=issueId%>"><%=issueName%></option>
									 <%
									 }
							  }
							}
							  %>
									  </select>
				 	 <span class="asterisk">*</span>
					 </td>
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
										System.out.println("event name"+eventTypeName);
										%>

				   <tr>
				     <td class="tableLeft"><strong><b><%=eventTypeName%></b></strong></div></td>
				     <td class="tableRight">&nbsp;&nbsp;&nbsp; </td>
		     	</tr>
					
										
										
                                     
											
					<%
										ArrayList subLevelMap = (ArrayList)eventTypeMap.get(eventTypeId);
					                    if(subLevelMap!=null && subLevelMap.size()!=0)
											{
					                    Iterator itrSub = subLevelMap.iterator();
                    					while(itrSub.hasNext()){
				                        String[] subEntry = (String[])itrSub.next();
                				        String mapId = subEntry[0];
				                        String levelId = subEntry[1];
                				        String levelCode = subEntry[2];
				                        String levelName = subEntry[3]; 
									    levelCount = levelCount + 1;
										String cbxName = "eventLevels"+levelCount;
										System.out.println("cbxName....."+cbxName);
										System.out.println("mapid....."+mapId);
										System.out.println("eventSubTypes"+eventSubTypes);
										System.out.println("levelCount......"+levelCount);

										boolean chkStatus = false;
										if(mapId!=null && eventSubTypes.contains(mapId)){
											chkStatus=true;
										}%>
										
										
                                     
											
								
				<tr>
<td class="tableLeft"><input type="checkbox" name="<%=cbxName%>" value="<%=mapId%>" id="<%=cbxName%>" <%if(chkStatus){%> checked="checked"<%}%>/>&nbsp;&nbsp;<%=levelCode%></td>
				     <td class="tableRight">&nbsp;&nbsp;&nbsp; </td>
		     	</tr>
					 			<%
											}
							  }
							}
										}
						  %>
									 
				 	
									 
				 	 <input type="hidden" name="levelCount" id="levelCount" value="<%=levelCount%>" />
							
				 
			    <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" value="Update" class="gradBtn"  />
					&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="location.href='eventRegList.do?cmd=eventReg'"/>					</td>
				</tr>
				</form>
			</table>
			
		
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
	var cal1 = new calendar2(document.forms['frmEditEventRequestReg'].elements['beginDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	
	var cal2 = new calendar2(document.forms['frmEditEventRequestReg'].elements['endDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
</script>
