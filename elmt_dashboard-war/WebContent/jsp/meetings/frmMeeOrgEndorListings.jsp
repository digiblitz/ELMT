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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>


</head>

<body>
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
						 Meetings: <span class="styleBoldTwo">Organizer Endorsement  Application  Listings </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
					<img src="images/usea_logo.jpg" class="imgFloatLeft" /></td>
				  </tr>
				 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
				
						  <tr>
							<td colspan="5" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th height="20" width="130" valign="top" class="alignRight">&nbsp;Color Legends:&nbsp; </th>
									<!--
									<td valign="middle" class="alignLeft">
										<span class="legendOne">&nbsp;</span> &nbsp;Delete</td>
									-->
									<td valign="middle" class="alignLeft">
										<span class="legendTwo">&nbsp;</span> &nbsp;Edit.</td>
								  </tr>
								</table>			</td>
						  </tr>
						 <!-- <tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							  <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
						   </tr>-->
						  <tr>
							<td width="114" class="tblRowHead">Event ID </td>
							<td width="136" class="tblRowHead">Event Tile </td>
							<td width="153" class="tblRowHead">Date of Registration </td>
							<td width="63" class="tblRowHead">Edit</td>
						  </tr>
						  
						    <%
						  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						  ArrayList eventList = (ArrayList) session.getAttribute("displayOrgAllEventDet");
						  if(eventList!=null && eventList.size()!=0){
							  Iterator itEvent = eventList.iterator();
							  while(itEvent.hasNext()){
									HLCEventDetailsVO objEventDet = (HLCEventDetailsVO)itEvent.next();
									String eventId =  objEventDet.getEventId();									
									String organizerId =  objEventDet.getOrganizeId();
									String eventTitle =  objEventDet.getEventTitle();
									//activityDate = sdf.format(objEventDet.getActivityDate());
									String requestDate =  sdf.format(objEventDet.getAddDate());
						  
						  %>
						  <form name="eventList" method="post" action="EventOrgRenewal.do" />
						  <tr>
							<td class="listCellBg"><a href="meetings-events-view.html" class="linkFive"><%=eventId%></a></td>
							<td class="listCellBg"><%=eventTitle%></td>
							<td class="listCellBg"><%=requestDate%></td>
							<td class="listCellBg"><input name="Submit2" type="submit" class="oneBtn" value="View" /></td>
						  </tr>
						  </form>	
						  <%
						  }
						  }
						  else{
						  %>
						<tr>
							<th height="25" colspan="5">There are no records available. </th>
						</tr>
						  
						  <%
						  }
						  %>
						 
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
