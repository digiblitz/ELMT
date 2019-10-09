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
<%@ page import="com.hlcmro.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>


</head>
<script language="javascript">
function onValidate(){

	if(document.frmMeeUserRegList.selStatus.value==""){
		alert("Select a Status");
		document.frmMeeUserRegList.selStatus.focus();
		return false;
	}
	
	return true;
}
</script>
<body>
<%String status1 = (String)request.getAttribute("status");%>


<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop"><!-- HEADER STARTS HERE -->
        <%@ include file = "../../include/header.jsp" %>
        <!-- HEADER ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="infoBar"><!-- INFO BAR STARTS HERE -->
        <%@ include file = "../../include/infobar.jsp" %>
        <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg"><table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        <td width="230" class="menuTablePad"><!-- LEFT MENU STARTS HERE -->
              <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
              <!-- LEFT MENU ENDS HERE -->
        </td>
        <td width="500" class="subDeptTablePad">
		<!-- CONTENTS START HERE -->
              <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
						Meetings: <span class="styleBoldTwo">New Event Registration List </span>
					</td>
				  </tr>
				 
				 <tr>
					<td>
			 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
	<form name="frmMeeUserRegList" id="frmMeeUserRegList" method="post" action="./eventRegList.do" onsubmit="return onValidate();">
				<input name="cmd" type="hidden" value="userListDetails">						
				<tr>
				<td colspan="12">
				Status:
				<select name="selStatus" id="selStatus" class="selectboxOne" >
				<option selected="selected" value="" >Select One</option>
				<%
				String[] status = new String[]{"Pending","Approved","Rejected"};
				for(int k =0; k<status.length; k++){
				if(status1!=null && status1.equalsIgnoreCase(status[k])){
				%>
				<option value="<%=status[k]%>" selected="selected" ><%=status[k]%></option>
				<%}else{%>
				<option value="<%=status[k]%>"><%=status[k]%></option>
				<%}}%>
				</select><span class="asterisk">*</span>
				<input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn" />
				
				</td>
				</tr> 
				</form>
						
						  <tr>
							<td width="70" class="tblRowHead">Event ID </td>
							<td width="90" class="tblRowHead">Event Title </td>
							<td width="134" class="tblRowHead">Date of Registration </td>
							<td width="134" class="tblRowHead">Organizer ID</td>
							<td width="91" class="tblRowHead">View</td>						
						  </tr>
						  
						    <%
						 
						  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						  ArrayList userEventList = (ArrayList) request.getAttribute("userRegList");
						  
						 // out.print("MemberId:" + (String) session.getAttribute("memberId") + eventList.size());
						  
						  if(userEventList!=null && userEventList.size()!=0){
							  Iterator itEvent = userEventList.iterator();
							  while(itEvent.hasNext()){
									HLCEventDetailsVO objEventDet = (HLCEventDetailsVO)itEvent.next();
									String eventId =  objEventDet.getEventId();																	
									String eventTitle =  objEventDet.getEventTitle();
									//activityDate = sdf.format(objEventDet.getActivityDate());
									String requestDate="N/G";									
									if(objEventDet.getAddDate()!=null){
									    requestDate =  sdf.format(objEventDet.getAddDate());
										System.out.println("requestDate in jsp"+requestDate);
									}
									String orgId =  objEventDet.getOrganizeId();
									System.out.println("orgId in jsp"+orgId);
									
						  %>
						  <form name="userEventList" id="userEventList" method="post" action="eventRegList.do" />
						  <tr>
					        <td class="listCellBg"><%=eventId%></td>
							<td class="listCellBg"><%=eventTitle%></td>
							<td class="listCellBg"><%=requestDate%></td>
							<td class="listCellBg"><%=orgId%></td>
						     
					<td class="listCellBg"><input name="Submit3" type="button" class="oneBtn" value="View" onclick="location.href='eventRegList.do?cmd=viewNewEventReg&eventId=<%=eventId%>'" /></td> 							
						  </tr>
						  </form>	
						  <%
						  }
						  }
						  else{
						  %>
						<tr>
							<td align="center" height="25" colspan="6"><strong>No records are available. </strong></td>
						</tr>						  
						  <%
						  }
						  %>						 
						  <tr>
							<td height="19" colspan="7">&nbsp;</td>
						   </tr>
					  </table>
					</td>
				</tr>  
			</table>
          <!-- CONTENTS END HERE -->
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="footerBg"><!-- FOOTER STARTS HERE -->
        <%@ include file = "../../include/footer.jsp" %>
        <!-- FOOTER ENDS HERE -->
    </td>
  </tr>
</table>
</body>
</html>
