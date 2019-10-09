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
<script language="javascript" type="text/javascript">
function onValidate(){
	if(document.approveOrgList.orgStatus.value==""){
		alert("Select any Approval Status");
		document.approveOrgList.orgStatus.focus();
		return false;
	}
	if(document.approveOrgList.orgComments.value.length>1024){
		alert("Comments cannot exceed 1024 characters");
		document.approveOrgList.orgComments.focus();
		return false;
	}
	return true;
}
</script>
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
		Online Entries Provisional Calendar: <span class="styleBoldTwo">Event Approval Details </span>
	</td>
  </tr>
  <tr>
  	<td>
			<table border="0" width="100%" cellpadding="0" align="center" cellspacing="0" class="formLayout">
  <%
		HLCCalendarVO calVO = new HLCCalendarVO();
		
		calVO = (HLCCalendarVO)request.getAttribute("singleEventDetails");
		
		String provisionId = calVO.getCalenderId();
		String startDate = dateFormat(calVO.getBeginDate());
		String endDate = dateFormat(calVO.getEndDate());
		Date regBeginDate = calVO.getEntryStrtDate();
		Date regEndDate = calVO.getEntryEndDate();
		String eventTitle = calVO.getEventTitle();
		String areaName = calVO.getAreaName();
		String stateName = calVO.getStateName();
		String orgStatus = calVO.getOrgApprovalStatus();
		String renewalStatus = calVO.getRenewStat().toString();
		String eventId = calVO.getEventId();
		String eventDesc = calVO.getEventDesc();
		String eventLevels = calVO.getEventLevel();
		String orgComments = calVO.getOrgComments();
		String acStatus = calVO.getArearChairApproStatus();
        String acComments = calVO.getAreaChairCommt();
        String usStatus = calVO.getApprovalStatus();
        String usComments = calVO.getStaffComments();
		String rStat = "";
		if(renewalStatus.equalsIgnoreCase("true"))	rStat = "No";
		else rStat = "Yes";
		if(orgComments!=null && orgComments.trim().length()!=0){
			orgComments = orgComments;
		}else{
			orgComments = "";
		}
		long stTime = calVO.getBeginDate().getTime();
		long eTime = calVO.getEndDate().getTime();
		long diffTime = eTime - stTime;
		int noDays = (int)(diffTime/(1000*60*60*24));
		int tempNo=noDays+1;
		String appStatus = "";
		if(regBeginDate!= null && regEndDate!=null) appStatus = "";
		else appStatus = "disabled";
  %>
			
				  <tr>
					<td colspan="2" class="tblRowHead">Approve  Event Details: </td>
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
					<td class="tableRight"><%=startDate%></td>
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
				     <td class="tableLeft">Event Description: </td>
				     <td class="tableRight"><%=nullCheck(eventDesc)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Event Levels: </td>
				     <td class="tableRight"><%=nullCheck(eventLevels)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Area Chair Approval Status: </td>
				     <td class="tableRight"><%=nullCheck(acStatus)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Area Chair Approval Comments: </td>
				     <td class="tableRight"><%=nullCheck(acComments)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Staff Approval Status: </td>
				     <td class="tableRight"><%=nullCheck(usStatus)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Staff Approval Comments: </td>
				     <td class="tableRight"><%=nullCheck(usComments)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Renewal Status: </td>
				     <td class="tableRight"><%=rStat%></td>
		      </tr>
			  <form name="approveOrgList" id="approveOrgList" action="OECalendar.do" method="post">
			  <input type="hidden" name="cmd" value="approveOrgDetails" />
			  <input type="hidden" name="provisionalId" value="<%=provisionId%>" />
			  <tr>
				     <td class="tableLeft">Approval Status: </td>
				     <td class="tableRight">
					 	<select name="orgStatus" id="orgStatus" class="selectboxOne" >
							<option selected="selected" value="" >Select One</option>
								<%	String[] status = new String[]{"Approved","Pending","Rejected"};
									for(int k =0; k<status.length; k++){
										if(orgStatus!=null && orgStatus.equalsIgnoreCase(status[k])){
								%>
							<option value="<%=status[k]%>" selected="selected" ><%=status[k]%></option>
								<%}else{%>
							<option value="<%=status[k]%>"><%=status[k]%></option>
								<%}}%>
						</select>
					</td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Approval Comments: </td>
				     <td class="tableRight"><textarea name="orgComments" id="orgComments"><%=orgComments%></textarea></td>
		      </tr>
			  
				   <tr>
					<td colspan="2" align="center">
					<input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn" <%=appStatus%>  />
					<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"  />
					</td>
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
