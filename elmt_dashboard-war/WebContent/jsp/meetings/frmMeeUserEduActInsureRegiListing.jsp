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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
			
			<!-- LEFT MENU ENDS HERE -->
           <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
					 Meeetings: <span class="styleBoldTwo">Insurance Release Registration Listing-Educational Activity </span>
					 </td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp"> <img src="images/usea_logo.jpg" class="imgFloatLeft" /> 
					  <br />
					  Insurance Release Educational Activity Registrations placed by you are listed below. To view the details, 
					  click on the <strong>'MemberID'</strong> link and to <strong>Delete</strong> a record click on the  button beside each record.					</td>
				  </tr>
				 
				 <tr>
					<td>
					
					
						 
						<table width="523" border="0" align="center" cellpadding="3" cellspacing="1" class="formLayout">
						<tr>
							<td colspan="5" class="tableSpan">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th height="20" width="130" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
									
									<td valign="middle" class="alignLeft">
										<span class="legendTwo">&nbsp;</span> &nbsp;View</td>
									
									<!-- 
									<td valign="middle" class="alignLeft">
										<span class="legendTwo">&nbsp;</span> &nbsp;View					</td>
										-->
								  </tr>
								</table>			</td>
						  </tr>

						  <tr> 
							<td width="83" height="27" class="tblRowHead">Meeting ID</td>
							
							<td width="116" class="tblRowHead">Date of Registration </td>
							<td width="111" class="tblRowHead">Approval Status</td>
							<td width="61" class="tblRowHead">View</td>
						  </tr>
						  <%
						  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						   ArrayList al = (ArrayList)session.getAttribute("DispAllUserDetail");
						  // out.println("Size:" + al.size());
						   if(al!=null && al.size()!=0){
                          	Iterator it = al.iterator();
							while(it.hasNext()){
								HLCActivityUserVO objActUser = (HLCActivityUserVO) it.next();
							   
								String releaseId =  objActUser.getReleaseId();
								String activityMeetingId = objActUser.getActivityMeetingId();
								String userIdVal =  objActUser.getUserId();
								String eventLevelId =  objActUser.getEventLevelId();
								String memberId =  objActUser.getMemberId();
								String noOfHorses =  objActUser.getNoOfHorses();
								String reqStatus =  objActUser.getRequestStatus();
								//out.print("reqStatus" + reqStatus);
								String reqDate = "" ;
								//out.print(objActUser.getAddDate());
								if(objActUser.getAddDate()!=null){
									 reqDate =  sdf.format(objActUser.getAddDate());
								}
						  
						  %>
						  <form name="frmMeeInsureICPListing" method="post" action="meeting.do">
						 <input type="hidden" name="meeProcess" value="userDetail">
						  <input type="hidden" name="releaseId" value="<%=releaseId%>">
						   <input type="hidden" name="activityMeetingId" value="<%=activityMeetingId%>">
						  
						  <tr> 
							<td height="26"  class="listCellBg"><%=activityMeetingId%></td>
							<td height="26"  class="listCellBg"><span class="listCellBg"><%=reqDate%></span></td>
							
							<td  class="listCellBg"><span class="styleBoldTwo"><%=reqStatus%></span></td>
							<td  class="listCellBg"><input type="submit" value="View" class="oneBtn" /></td>
						  </tr>
						  </form>
						  <%	}
						  }
								else {
								%>
								<tr>
								  <th height="25" colspan="5" class="alignCenter">No records are available</th>
								</tr>
								<%}%> 
							<td height="19" colspan="6">&nbsp;</td>
						  </tr>
						</table>
					  
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
