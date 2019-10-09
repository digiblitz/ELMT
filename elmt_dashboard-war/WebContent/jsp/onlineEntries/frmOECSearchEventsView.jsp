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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
</head>
<%! 

String  nullCheck(String fieldName){
	String retValue = "<b>N/A</b>";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>

  <%!				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" width="100%" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		Online Entries : <span class="styleBoldTwo">Event Details </span>
	</td>
  </tr>
  <tr>
  	<td>
			<table border="0" width="100%" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			
  <%
  		String eventTypeName=(String)request.getAttribute("eventTypeNames");
		String eventLevelName=(String)request.getAttribute("eventLevelNames");
		System.out.println("eventTypeName :"+eventTypeName);
		System.out.println("eventLevelName :"+eventLevelName);
		String fstName=(String)session.getAttribute("orgFirstName");
		String lstName=(String)session.getAttribute("orgLastName");
		String firstName=(String)request.getAttribute("oFirstName");
		String lastName=(String)request.getAttribute("oLastName");
		HLCCalendarVO calVO = new HLCCalendarVO();
		
		calVO = (HLCCalendarVO)request.getAttribute("objCalVO");
		
		String eventId = calVO.getEventId();
		System.out.println("eventId in jsp"+eventId);
		String beginDate=dateFormat(calVO.getBeginDate());
		String endDate=dateFormat(calVO.getEndDate());
		String eventTitle=calVO.getEventTitle();
		String orgId=calVO.getOrganizerId();
		String areaCode=calVO.getAreaCode();
		String areaName=calVO.getAreaName();
		String stateName=calVO.getStateName();
		String hlcApprovStat=calVO.getStatus();
		String orgStatus=calVO.getOrgApprovalStatus();
		String eventType=calVO.getEventTypeNames();
		String eventLevel=calVO.getEventLevel();
		
		long stTime = calVO.getBeginDate().getTime();
		long eTime = calVO.getEndDate().getTime();
		long diffTime = eTime - stTime;
		int noDays = (int)(diffTime/(1000*60*60*24));
		int tempNo=noDays+1;
  %>
			
				  <tr>
					<td colspan="2" class="tblRowHead">View Event Details:</td>
				  </tr>
				  <tr>
					<td width="55%" class="tableLeft">Event Title:</td>
					<td width="45%" class="tableRight"><%=nullCheck(eventTitle)%></td>
				  </tr>
				   <tr>
				     <td class="tableLeft">Event ID: </td>
				     <td class="tableRight"><%=nullCheck(eventId)%></td>
		      </tr>	
				  
				  <tr>
					<td class="tableLeft">Start Date:</td>
					<td class="tableRight"><%=beginDate%></td>
				  </tr>
				  <tr>
					<td class="tableLeft">End Date:</td>
					<td class="tableRight"><%=endDate%></td>
				  </tr>				  
				   <tr>
				     <td class="tableLeft">Number Of Days: </td>
				     <td class="tableRight"><%=tempNo%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Area Name: </td>
				     <td class="tableRight"><%=nullCheck(areaName)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Location: </td>
				     <td class="tableRight"><%=nullCheck(stateName)%></td>
		      </tr>
			  
			   <tr>
				     <td class="tableLeft">Event Type Name(s): </td>
				     <td class="tableRight"><%=nullCheck(eventType)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Event Level Name(s): </td>
				     <td class="tableRight"><%=nullCheck(eventLevel)%></td>
		      </tr>
			 
			   <tr>
				     <td class="tableLeft">Organizer ID: </td>
				     <td class="tableRight"><%=nullCheck(orgId)%></td>
		      </tr>
			  <%if(fstName!=null && fstName.trim().length()!=0 && lstName!=null && lstName.trim().length()!=0){%>
			   <tr>
				     <td class="tableLeft">Organizer Name: </td>
				     <td class="tableRight"><%=nullCheck(fstName+" "+lstName)%></td>
		      </tr>
			  <%}%>
			  <%if(firstName!=null && firstName.trim().length()!=0 && lastName!=null && lastName.trim().length()!=0){%>
			   <tr>
				     <td class="tableLeft">Organizer Name: </td>
				     <td class="tableRight"><%=nullCheck(firstName+" "+lastName)%></td>
		      </tr>
			  <%}%>
			  <tr>
				     <td class="tableLeft">Organizer Status: </td>
				     <td class="tableRight"><%=nullCheck(orgStatus)%></td>
		      </tr>
			
				   <tr>
					<td colspan="2" align="center">&nbsp;<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"  />
					</td>
				  </tr>
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
