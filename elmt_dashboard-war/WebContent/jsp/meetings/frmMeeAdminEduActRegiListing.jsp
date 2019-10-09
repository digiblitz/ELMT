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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>
<script language="javascript">

function postData(){
   
	frmMeeDispLst.meeProcess.value = "showStatus";
    frmMeeDispLst.method="post";
    frmMeeDispLst.action="./meeting.do";
    frmMeeDispLst.submit();
}
</script>

<%
String  status = (String)request.getAttribute("status");
if(status==null || status.equals("")){
	status="";
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
						Meetings: <span class="styleBoldTwo">Educational Activity  Registration Listing</span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp"> 
					Educational Activity Registrations placed by organizers are listed below. To view the details,
					click on the <strong>'View'</strong> button beside each record.
					</td>
				  </tr>
	<%
String memberId = (String)session.getAttribute("memberId");
if(memberId==null)
memberId = "Memebr Id Not Exist";
ArrayList listContact = (ArrayList) session.getAttribute("DisplaymemberDetails");

String email_id = "";
if(listContact !=null && listContact.size()!=0){
		Iterator it = listContact.iterator();
		while(it.hasNext()){
email_id  = (String)it.next();
if(email_id==null)
email_id = "";
}
}
%>			 
				
						  <tr>
		   	<td colspan="5" class="tableSpan">
			<form name="frmMeeDispLst" >
			<input name="meeProcess" type="hidden" value="">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
					  <th width="48" height="20" valign="top" class="alignLeft"> Status </th>
					  <td width="206" colspan="3" valign="middle" class="alignLeft">
					  <select name="status" class="selectboxOne" onchange="postData();">
                        <option selected="selected" value="">Select One</option>
						<%
							if(status.equals("Pending")){
						%>
                        <option value="Pending" selected="selected" >Pending</option>
						<%
							}
							else{
						%>
						 	<option value="Pending" >Pending</option>
						<%
							}
							if(status.equals("Approved")){
						%>
						<option value="Approved" selected="selected" >Approved</option>
						
						<%
						}
						else{
						%>
							<option value="Approved" >Approved</option>
						<%
						}
						if(status.equals("Rejected")){
						%>
						
						<option value="Rejected" selected="selected" >Rejected</option>
						<%
						}
						else{
						%>
						<option value="Rejected" >Rejected</option>
						<%
						}
						%>
                      </select></td>
				  </tr>
				</table>
			  </form>
			</td>
		   </tr>
						  
						  
						  
						  
						  <tr>
							<td width="60" height="27" class="tblRowHead">Member ID </td>
							<td width="80" class="tblRowHead">Activity Name </td>
							<td width="80" class="tblRowHead">Date of Activity </td>
							<td width="88" class="tblRowHead">Date Of Registration </td>
							<td width="65" class="tblRowHead">View</td>
						  </tr>
						  
 <%
		           
          	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
         	String meetingId ="";
		    String activityDate ="";
			String requestDate ="";
			String meetingName ="";
			String activityName = "";
			ArrayList vPendingAds = (ArrayList)request.getAttribute("displayStatusDetails");
			if(vPendingAds!=null && vPendingAds.size()!=0){
						Iterator e = vPendingAds.iterator();
						ArrayList finalList = new ArrayList();
						while(e.hasNext()){
						 ArrayList exaVal = (ArrayList)e.next();
						 Iterator enumPub1 = exaVal.iterator();
						 while(enumPub1.hasNext()){
								HLCActivityOrganizerVO objActDet = (HLCActivityOrganizerVO)enumPub1.next();
									Vector publication = (Vector)enumPub1.next();
									 meetingName = objActDet.getActivityName();							
									 
									 if(objActDet.getActivityMeetingId()!=null){
									 	meetingId =	objActDet.getActivityMeetingId();
									 }
									 if(objActDet.getActivityDate()!=null){
									 	activityDate = sdf.format(objActDet.getActivityDate());
									 }
									 if(objActDet.getAddDate()!=null){
									 	requestDate =  sdf.format(objActDet.getAddDate());
									 }
									 activityName = objActDet.getActivityName();	
					
					%>
				
						  
					<form name="frmMeeReqView" action="meeting.do" method="post" />
					<input name="meeProcess" type="hidden" value="showDet">
					<input name="meetingId" value="<%=meetingId%>" type="hidden" />
						  <tr>
							<td height="26" class="listCellBg"><%=meetingId%></td>
							<td height="26" class="listCellBg"><%=activityName%></td>
							<td class="listCellBg"><%=activityDate%></td>
							<td class="listCellBg"><%=requestDate%></td>
							<td class="listCellBg"><input  type="submit" class="oneBtn" value="view" /></td>
						  </tr>
						  </form>
						   <%}	
					 }
					 }
					else {
					%>
					<tr>
					  <th height="25" colspan="5" class="alignCenter">No records are available</th>
					</tr>
					<%}%> 
						  <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
						   </tr>-->
						  <tr>
							<td height="19" colspan="6">&nbsp;</td>
					      </tr>
					  </table>
					</td>
				</tr>  
				</table>
			<!-- CONTENTS END HERE -->		
		 
	
	</td>
  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
</body>
</html>
