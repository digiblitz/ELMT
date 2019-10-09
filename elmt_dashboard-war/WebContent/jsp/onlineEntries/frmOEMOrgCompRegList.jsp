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
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.*"%>
<%@ page import="com.hlcmrm.util.HLCDonationVO"%>
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
	if(document.myform.selYear.value==""){
		alert("Select a Year");
		document.myform.selYear.focus();
		return false;
	}
	if(document.myform.selTitle.value==""){
		alert("Select Event Title");
		document.myform.selTitle.focus();
		return false;
	}
	return true;
}
function postData(year){
location.href="OEMOrgCompReg.do?cmd=initEventTitleList&year="+year;
}	
</script>
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
  <tr><h1></h1>
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
 			
<table  border="0" width="100%" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Online Entries : <span class="styleBoldTwo">Organizer List</span></td>
  </tr>
  
  <%
	java.util.Date orgDate = new java.util.Date();
	int currentYear = 1900+orgDate.getYear();
	String selYear = (String)request.getAttribute("tempYear");
	String acStatus = (String)request.getAttribute("status");
	String eventID=(String)request.getAttribute("eventID");
	System.out.println("selYear : "+selYear);
	System.out.println("acStatus : "+acStatus);
	System.out.println("eventID : "+eventID);
	int selectedYear = 0;
	if(selYear!=null && selYear.trim().length()!=0){
		selectedYear = Integer.parseInt(selYear);
	}
	String approveStatus = (String)request.getAttribute("approveStatus");
	String dispAppStatus = "";	
	if(approveStatus!=null && approveStatus.equalsIgnoreCase("approveSuccess")){
		dispAppStatus = "Approval Status Changed Successfully";
	} else if(approveStatus!=null && approveStatus.equalsIgnoreCase("approveFailed")){
		dispAppStatus = "Approval Status Cannot be Changed";
	}
	
%>	
 <tr>
 	<td>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		<form name="myform" id="myform" action="OEMOrgCompReg.do" method="post" onsubmit="return onValidate();">
		<input type="hidden" name="cmd" value="ListDetails" />
		<%if(approveStatus!=null && approveStatus.trim().length()!=0){%>
		<tr>
		  <td height="25" colspan="8" class="styleError"><%=dispAppStatus%></td>
		</tr>
		<%}%>
		<tr>
		<td colspan="8" align="center"><strong>Year:</strong>
		<select name="selYear" id="selYear" class="selectboxOne" onchange="postData(this.value);" >
		<option selected="selected" value="" >Select One</option>
		<%
			for(int i=2000; i<(currentYear+2); i++){
				if(selectedYear!=0 && selectedYear==i){
		%>
		<option value="<%=i%>" selected="selected"><%=i%></option>
		<%}else{%>
		<option value="<%=i%>"><%=i%></option>
		<%}}%>
		</select><span class="asterisk">&nbsp;*</span>
		<strong>Event Title:</strong> 
					  <select name="selTitle" id="selTitle" class="selectboxOne">
                        <option selected="selected" value="">Select One</option>
						<%
						ArrayList eventTitles = (ArrayList)request.getAttribute("eventTitles");
						if(eventTitles!=null&& eventTitles.size()!=0){
						Iterator list = eventTitles.iterator();
						while(list.hasNext()){
								String str[] = (String [])list.next();
								String eventId = str[0];
								String eventTitle = str[1];
								if(eventId.trim().equalsIgnoreCase(eventID)){
						%>
							<option  value="<%=eventId%>" selected="selected"><%=eventTitle%></option>
							<%}else{%>
							<option  value="<%=eventId%>"><%=eventTitle%></option>
							<%}}}%>
							</select>
				<strong>Status:</strong> 
							<select name="acStatus" id="acStatus" class="selectboxOne" >
								<option selected="selected" value="" >Select One</option>
								<%
									String[] status = new String[]{"Approved","Pending","Rejected"};
									for(int k =0; k<status.length; k++){
										if(acStatus!=null && acStatus.equalsIgnoreCase(status[k])){
								%>
								<option value="<%=status[k]%>" selected="selected" ><%=status[k]%></option>
								<%}else{%>
								<option value="<%=status[k]%>"><%=status[k]%></option>
								<%}}%>
							</select>
		<input type="submit" name="submit" value="Submit" class="oneBtn"/>				</td>
			</tr>
			</form>
		  <tr>
			<td class="tblRowHeadTwo">RiderId</td>
			<td class="tblRowHeadTwo">HorseId</td>
			<td class="tblRowHeadTwo">Event Type </td>
			<td class="tblRowHeadTwo">Event Level</td>
			<td class="tblRowHeadTwo">Division</td>
			<td class="tblRowHeadTwo">View</td>
			<td class="tblRowHeadTwo">Approve</td>
			
		   </tr>
			<%
				HLCCompRegistrationVO compVO = new HLCCompRegistrationVO();
				ArrayList compRegList = (ArrayList)request.getAttribute("compRegDetails");
				if(compRegList!=null && compRegList.size()!=0){
					Iterator itr = compRegList.iterator();
					while(itr.hasNext()){
						compVO = (HLCCompRegistrationVO)itr.next();
						String registrationId = compVO.getRegistrationId();
						String horseMemberId = compVO.getHorseMemberId();
						String riderId = compVO.getRiderMemberId();
						String eventType = compVO.getEventType();
						String eventLevel = compVO.getEventLevel();
						String division = compVO.getEventDivision();
								
			%>
		  <tr>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=riderId%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=horseMemberId%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=eventType%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=eventLevel%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=division%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="process" value="View" class="oneBtn" onclick="location.href='OEMOrgCompReg.do?cmd=compRegView&registrationId=<%=registrationId%>'" /></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="process"  value="Approve" class="oneBtn" onclick="location.href='OEMOrgCompReg.do?cmd=initEventApprove&registrationId=<%=registrationId%>'" /></td>
		
		   </tr>
			<%
				}
			}else{
			%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>
			
	  </table>		  </form>
	  
	
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
</html>
