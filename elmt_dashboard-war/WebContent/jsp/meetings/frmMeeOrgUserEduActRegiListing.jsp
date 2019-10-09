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
<%@ page import="com.common.util.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="meetings.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<script language="javascript">


function chkSelect(){

	if(document.frmMeeDispLst.meetingId.value=="" ){
		alert("Please select any one Meeting");
		document.frmMeeDispLst.meetingId.focus();
		return false;
	}
	if(document.frmMeeDispLst.selUserStatus.value=="" ){
		alert("Please select any one Status");
		document.frmMeeDispLst.selUserStatus.focus();
		return false;
	}
	return true;

}

 
</script>
<%
String  status = (String)request.getAttribute("selUserStatus");
String  meetingId = (String)request.getAttribute("meetingId");
if(status==null || status.equals("")){
	status="";
}
if(meetingId==null || meetingId.equals("")){
	meetingId="";
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
						USEA Meetings: <span class="styleBoldOne">Organizer</span> <span class="styleBoldTwo">Insurance  Release  Educational Activity Registration Listing</span>					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
					 
					Insurance Release Educational Activity Registerations placed by the <strong>users</strong> are listed below. To view the details,
					click on the <strong>'View'</strong> button beside each record.					</td>
				  </tr>
				 
				 <tr>
					<td colspan="5">
			<form name="frmMeeDispLst" method="post" action="meeting.do" onsubmit="return chkSelect();">
			<input name="meeProcess" type="hidden" value="userShowStatus">
					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				   <tr>
					  <th width="55" height="20" valign="top" class="alignLeft">Activity Name </th>
					  <td colspan="3" valign="middle" class="alignLeft">
					   <select name="meetingId" class="selectboxOne">
                        <option selected="selected" value="">Select One</option>
					  <%
					  ArrayList orgDet = (ArrayList)session.getAttribute("dispOwnOrgDetails");
						if(orgDet!=null && orgDet.size()!=0){
							Iterator e = orgDet.iterator();
							ArrayList finalList = new ArrayList();
							while(e.hasNext()){
								 ArrayList exaVal = (ArrayList)e.next();
								 Iterator enumPub1 = exaVal.iterator();
								 while(enumPub1.hasNext()){
										HLCActivityOrganizerVO objActDet = (HLCActivityOrganizerVO)enumPub1.next();
										Vector publication = (Vector)enumPub1.next();
										String  meetingName =objActDet.getActivityName();							
										 String meetingIdVal =	objActDet.getActivityMeetingId();
										 if(meetingId.equals(meetingIdVal)){
										 %>
										 <option value="<%=meetingIdVal%>" selected="selected"><%=meetingName%></option>
										 <%
										 }
										 else{
										 %>
										 <option value="<%=meetingIdVal%>"><%=meetingName%></option>
										 <%
										 }
								}
							}
						}
						
						
						 
					  %>
					  </select>					  </td>
					  <th width="46">Status	</th>
					  <td width="102">
					 <select name="selUserStatus" class="selectboxOne">
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
					  
				      <td width="183"><span class="listCellBg">
				        <input name="Submit22" type="submit" class="gradBtn" value="Search" />
				      </span></td>
				   </tr>
				</table>	
				 </form>						</td>
				  </tr>
						  
						<!-- <tr>
							<td colspan="4" class="tableSpan">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th height="20" width="130" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
									 
									<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
										<span class="legendOne">&nbsp;</span> &nbsp;Delete					</td>
									 
									<td valign="middle" class="alignLeft">
										<span class="legendTwo">&nbsp;</span> &nbsp;View					</td>
								  </tr>
								</table>			</td>
				  </tr>-->
						  <tr>
							<td width="120" height="27" class="tblRowHead">First Name </td>
							<td width="114" class="tblRowHead">No of Horses </td>
							<td width="150" class="tblRowHead">Date of Registration </td>
							<td width="87" class="tblRowHead">View</td>
						  </tr>
						  <%
						  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						   ArrayList al = (ArrayList)session.getAttribute("DispUserDetails");
						   String regDate ="";
						   if(al!=null && al.size()!=0){
                          	 Iterator it = al.iterator();
							while(it.hasNext()){
							   	ArrayList tempList = (ArrayList)it.next();
    	                      	Iterator itTemp = tempList.iterator();
								while(itTemp.hasNext()){
									String releaseId = (String)itTemp.next();
									String meetingIdVal = (String)itTemp.next();
									String uId = (String)itTemp.next();
									String uName = (String)itTemp.next();
									Date rDate =(Date)itTemp.next();
									if(rDate!=null){
									regDate = sdf.format(rDate);
									}
									String noHorse = (String)itTemp.next();
						  %>
						  <form name="frmMee" method="post" action="meeting.do">
						 <input type="hidden" name="meeProcess" value="userDetailOrg"/>
						<input type="hidden" name="releaseId" value="<%=releaseId%>" />
						<input type="hidden" name="uId" value="<%=uId%>" />
						<input type="hidden" name="activityMeetingId" value="<%=meetingIdVal%>" />
						  <tr>
							<td height="26" class="listCellBg"><%=uName%></td>
							<td class="listCellBg"><%=noHorse%></td>
							<td class="listCellBg"><%=regDate%></td>
							<td class="listCellBg"><input type="submit" class="oneBtn" value="view" /></td>
						  </tr>
						  </form>
						  <%}
						  }
						  }
						  else{%>
						   <tr>
							<td height="19" colspan="5">No records are available</td>
						   </tr>
						   <%}%>
						  <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
						   </tr>-->
						  <tr>
							<td height="19" colspan="5">&nbsp;</td>
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
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>
</body>
</html>
