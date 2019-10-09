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
<%@ page import="com.hlcmrm.util.*"%>
<%@ page import="com.hlcutil.HLCCalendarVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript" type="text/javascript">
function onValidate(){
	if(document.myform.divisionId.value==""){
		alert("Select any of the Event Divisions");
		document.myform.divisionId.focus();
		return false;
	}
	var choosen="";
	len = document.myform.evLevel.length ;
	for(i=0; i<len; i++){
		if(document.myform.evLevel[i].checked){
			choosen=document.myform.evLevel[i].value;
		}
	}
	if(choosen==""){
		alert("Select any one Event Type & Event Level");
		return false;
	}
	return true;
}
</script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<body >
<%!
  		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
		String dateFormat(java.util.Date fieldName){					
		Calendar cal = Calendar.getInstance();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fieldName);
		cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						
		int tempDay = gc.get(Calendar.DAY_OF_WEEK);
		String holiday = null;
			switch(tempDay){
			case Calendar.SUNDAY:
				holiday ="SUN";
				break;
			case Calendar.MONDAY:
				holiday ="MON";
				break;
			case Calendar.TUESDAY:
				holiday ="TUE";
				break;
			case Calendar.WEDNESDAY:
				holiday ="WED";
				break;
			case Calendar.THURSDAY:
				holiday ="THU";
				break;
			case Calendar.FRIDAY:
				holiday ="FRI";
				break;
			case Calendar.SATURDAY:
				holiday ="SAT";
				break;
			}
			String dispDate = "N/A";
			if(fieldName!=null ){
				dispDate = sd1.format(cal.getTime()).toString()+" ("+holiday+")";
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
		<%
			String orgFirstName = (String)session.getAttribute("orgFirstName");
			String orgLastName = (String)session.getAttribute("orgLastName");
			String eventId = (String)session.getAttribute("eventId");
			String riderId = (String)request.getAttribute("riderId");
			String horseMemeberId = (String)request.getAttribute("horseMemberId");
			String dateCheck = (String)request.getAttribute("dateCheck");
			String compYear = (String)session.getAttribute("compYear");
			String horseCheck = (String)request.getAttribute("horseCheck");
			String horseName = (String)request.getAttribute("horseName");
			
			ArrayList eventLevelDetails = (ArrayList)request.getAttribute("eventLevelDetails");
			ArrayList divisionDetails = (ArrayList)request.getAttribute("divisionDetails");
			ArrayList objProCalList = (ArrayList)request.getAttribute("selectEventDetails");
%>
     <form name="myform" id="myform" method="post" action="validation.do" onsubmit="return onValidate();" >
			<input type="hidden" name="cmd" value="startValidate" />
 			<input type="hidden" name="riderId" value="<%=riderId%>" />
			<input type="hidden" name="eventId" value="<%=eventId%>" />
			<input type="hidden" name="horseMemberId" value="<%=horseMemeberId%>" />
			<input type="hidden" name="compYear" value="<%=compYear%>" />
			<input type="hidden" name="horseName" value="<%=horseName%>" />
			
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
		<tr>
			<td colspan="7" class="tblMainHead">Selected Event Details:</td>
		</tr>
		<tr>
			<td class="tblRowHead">Begin Date </td>
			<td class="tblRowHead">End Date </td>
			<td class="tblRowHead">Event Title</td>
			<td class="tblRowHead">Organizer Name</td>
			<td class="tblRowHead">Area Code</td>
			<td class="tblRowHead">Location</td>
			<td class="tblRowHead">Status</td>
		</tr>
		<%
			HLCCalendarVO calVO = new HLCCalendarVO();
			if(objProCalList!=null && objProCalList.size()!=0){
			Iterator itr =objProCalList.iterator();
        		while(itr.hasNext()){
					calVO =(HLCCalendarVO)itr.next();
					String evId = calVO.getEventId();
					String beginDate=dateFormat(calVO.getBeginDate());
					String endDate=dateFormat(calVO.getEndDate());
					String eventTitle=calVO.getEventTitle();
					String orgId=calVO.getOrganizerId();
					String areaCode=calVO.getAreaCode();
					String areaName=calVO.getAreaName();
					String stateName=calVO.getStateName();
					String areaChApprovStat=calVO.getOrgApprovalStatus();
					String status=calVO.getStatus();
		%>
<tr>
							<td class="listCellBg"><%=beginDate%></td>
							<td class="listCellBg"><%=endDate%></td>
							<td class="listCellBg"><a href="calender.do?method=eventView&eventId=<%=eventId%>"><%=eventTitle%></a></td>
							<td class="listCellBg"><%=orgFirstName%>&nbsp;<%=orgLastName%></td>
							<td class="listCellBg"><%=areaCode%></td>
							<td class="listCellBg"><%=stateName%></td>
							<td class="listCellBg"><%=status%>
							</td>
							</tr>		<%}}else{%>
		<tr>
			<td colspan="7" class="listCellBg"><strong>No records are available.</strong></th>
		</tr>
		<%}%>
		<tr>
			<td colspan="3" class="tblMainHead">Horse Member ID:</td>
			<td colspan="4" class="tblMainHead"><%=horseMemeberId%></td>
		</tr>
		<tr>
			<td colspan="3" class="tblMainHead">Horse Name:</td>
			<td colspan="4" class="tblMainHead"><%=horseName%></td>
		</tr>
		</table>
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
		<%
			if(horseCheck!=null && horseCheck.equalsIgnoreCase("registered")){
		%>
		<tr>
			<td colspan="7" class="alignCenter"><strong>Selected Horse Already Registered For This Event. Please Select Any Other Horse.</strong></td>
		</tr>
		<tr>
			<td colspan="7" class="alignCenter"><input type="button" name="butt" value="Back To List" class="gradBtn" onclick="location.href='OEEDemo.do?process=UserListing'"/></td>
		</tr>
		<%}else{%>
		<tr>
			<td colspan="7" class="tblMainHead">Event Details & Division Details</td>
		</tr>
		<%
			if(dateCheck!=null && dateCheck.equalsIgnoreCase("before")){
		%>
		<tr>
			<td colspan="7" class="alignCenter"><strong>Registration Process For the Selected Event Not Yet Started. Please Select Any Other Event</strong></td>
		</tr>
		<tr>
			<td colspan="7" class="alignCenter"><input type="button" name="butt" value="Back To List" class="gradBtn" onclick="location.href='OEEDemo.do?process=UserListing'"/></td>
		</tr>
		
		<%}else{%>
		<tr>
			<td class="listCellBg"><strong>Event Type & Level</strong></td>
			<td class="listCellBg">
			<%
				if(eventLevelDetails!=null && eventLevelDetails.size()!=0){
					Iterator ite = eventLevelDetails.iterator();
					while(ite.hasNext()){
						String el[] = (String[])ite.next();
						String eventLTypeId = el[0];
						String eventLevelId = el[1];
						String eventLevelName = el[2];
						String eventLTypeName = el[3];
						String champStatus = el[4];
						String chStatus = "";
						if(champStatus.equalsIgnoreCase("1")){
						chStatus = "true";
			%>
				<input type="radio" name="evLevel" id="evLevel" value="<%=eventLevelId%>|<%=eventLTypeId%>:<%=chStatus%>" /> <%=eventLTypeName%>&nbsp;<strong>&</strong>&nbsp;<%=eventLevelName%> (CH)
			<%
				}else{
					chStatus = "false";
			%>
				<input type="radio" name="evLevel" id="evLevel" value="<%=eventLevelId%>|<%=eventLTypeId%>:<%=chStatus%>" /> <%=eventLTypeName%>&nbsp;<strong>&</strong>&nbsp;<%=eventLevelName%>
			<%}}}%>
				</td>
				</tr>
			<tr>
			<td class="listCellBg"><strong>Division</strong></td>
			<td class="listCellBg">
				<select name="divisionId" id="divisionId" class="selectboxOne">
					<option value="" selected="selected">Select One</option>
					<%
						if(divisionDetails!=null && divisionDetails.size()!=0){
							Iterator ite = divisionDetails.iterator();
								while(ite.hasNext()){
									String [] divDet = (String[])ite.next();
									String divId = divDet[0];
									String divName = divDet[1];
					%>
					<option value="<%=divId%>|<%=divName%>" ><%=divName%></option>
					<%}}%>
				</select>			
			</td>
			</tr>
			<tr>
			<td colspan="2" class="alignCenter"><input type="submit" name="submit" value="Submit" class="gradBtn" /></td>
			</tr>
			<%}}%>
		</table>
	<div id="spacer">&nbsp;</div>

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
</html>
