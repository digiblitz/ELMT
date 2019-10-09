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
			String horseName = (String)request.getAttribute("horseName");
			String divisionId = (String)request.getAttribute("divisionId");
			String chStatus = (String)request.getAttribute("championshipStatus");
			
			String horseResult = (String)request.getAttribute("horseResult");
			String riderResult = (String)request.getAttribute("riderResult");
			
			String horseRecordCount = (String)request.getAttribute("horseRecordCount");
			String riderRecordCount = (String)request.getAttribute("riderRecordCount");
			
			ArrayList horseDetails = (ArrayList)request.getAttribute("horseDetails");
			ArrayList riderDetails = (ArrayList)request.getAttribute("riderDetails");
			
			ArrayList horseValidationDetails = (ArrayList)request.getAttribute("horseValidationDetails");
			ArrayList riderValidationDetails = (ArrayList)request.getAttribute("riderValidationDetails");
			
			ArrayList horseValidEventDetails = (ArrayList)request.getAttribute("horseValidEventDetails");
			ArrayList riderValidEventDetails = (ArrayList)request.getAttribute("riderValidEventDetails");
			ArrayList finalEventResults = (ArrayList)request.getAttribute("finalEventResults");
			
			ArrayList eventLevelDetails = (ArrayList)request.getAttribute("eventLevelDetails");
			ArrayList eventTypeDetails = (ArrayList)request.getAttribute("eventTypeDetails");
			ArrayList objProCalList = (ArrayList)request.getAttribute("selectEventDetails");
			
			String horseAge = (String)request.getAttribute("horseAge");
			request.setAttribute("horseAge",horseAge);
			String divisionName = (String)request.getAttribute("divisionName");
			String compYear = (String)session.getAttribute("compYear");
			String eventLevelName = (String)request.getAttribute("eventLevelName");
			String eventTypeName = (String)request.getAttribute("eventTypeName");

			String hResult = "";
			String rResult = "";
			
			if(horseResult!=null && horseResult.equalsIgnoreCase("Success")) hResult = "Yes";
			else hResult = "No";
			
			if(riderResult!=null && riderResult.equalsIgnoreCase("Success")) rResult = "Yes";
			else rResult = "No";
		%>
		
     <form name="myform" id="myform" method="post" action="validation.do" >
			<input type="hidden" name="cmd" value="startValidate" />
 			<input type="hidden" name="riderId" value="<%=riderId%>" />
			<input type="hidden" name="eventId" value="<%=eventId%>" />
			<input type="hidden" name="horseMemeberId" value="<%=horseMemeberId%>" />
			<input type="hidden" name="chStat" value="<%=chStatus%>" />
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
		</table>
			
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
		<tr>
			<td colspan="2" class="tblMainHead">Event Type:</td>
			<td colspan="3" class="tblMainHead"><%=eventTypeName%></td>
		</tr>
		<tr>
			<td colspan="2" class="tblMainHead">Event Level:</td>
			<td colspan="3" class="tblMainHead"><%=eventLevelName%></td>
		</tr>
		<tr>
			<td colspan="2" class="tblMainHead">Event Division:</td>
			<td colspan="3" class="tblMainHead"><%=divisionName%></td>
		</tr>
		<tr>
			<td colspan="2" class="tblMainHead">Horse Member ID:</td>
			<td colspan="3" class="tblMainHead"><%=horseMemeberId%></td>
		</tr>
		<tr>
			<td colspan="2" class="tblMainHead">Horse Name:</td>
			<td colspan="3" class="tblMainHead"><%=horseName%></td>
		</tr>
			<tr>
				<td colspan="2" class="tblMainHead"><strong>Horse Qualified:</strong></td>
				<td colspan="3" class="tblMainHead"><div class="labelTabHead"><%=hResult%></div></td>
			</tr>
			<tr>
				<td colspan="2" class="tblMainHead"><strong>Rider Qualified:</strong></td>
				<td colspan="3" class="tblMainHead"><div class="labelTabHead"><%=rResult%></div></td>
			</tr>
			
			<tr>
			<td colspan="5" class="tblRowHead">Validation Table</td>
			</tr>
			<tr>
			<td class="tblRowHead">Event Type</td>
			<td class="tblRowHead">Event Level</td>
			<td class="tblRowHead">Horse Qualified</td>
			<td class="tblRowHead">Rider Qualified</td>
			<td class="tblRowHead">Choose</td>
			</tr>
			<%
				if(finalEventResults!=null && finalEventResults.size()!=0){
					Iterator iter = finalEventResults.iterator();
					while(iter.hasNext()){
						String[] finalDet = (String[])iter.next();
						String eTId = finalDet[0];
						String eLId = finalDet[1];
						String eTName = finalDet[2];
						String eLName = finalDet[3];
						String horseValid = finalDet[4];
						String riderValid = finalDet[5];
						String cStat = finalDet[6];
						String choose = "";
						String chooseURL = "";
						if(horseValid.equalsIgnoreCase("Yes") && riderValid.equalsIgnoreCase("Yes")){
							choose = "Enter";
							chooseURL ="validation.do?cmd=startValidate&horseMemberId="+horseMemeberId+"&eventId="+eventId+"&memberId="+riderId+"&evLevel="+eLId+"|"+eTId+":"+cStat+"&divisionId="+divisionId+"&compYear="+compYear+"&horseName="+horseName;
						}else{
							choose = "View/Edit";
							chooseURL ="validation.do?cmd=viewEventValidationDetails&horseMemberId="+horseMemeberId+"&eventId="+eventId+"&memberId="+riderId+"&evLevel="+eLId+"|"+eTId+"&divisionId="+divisionId+"&compYear="+compYear+"&chStat="+cStat+"&horseName="+horseName;
						}
						
			%>
			<tr>
			<td class="tblMainHead"><%=eTName%></td>
			<td class="tblMainHead"><%=eLName%></td>
			<td class="tblMainHead"><%=horseValid%></td>
			<td class="tblMainHead"><%=riderValid%></td>
			<td class="tblMainHead"><input type="button" name="submit" value="<%=choose%>" class="twoBtn" onclick="location.href='<%=chooseURL%>'" /></td>
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
