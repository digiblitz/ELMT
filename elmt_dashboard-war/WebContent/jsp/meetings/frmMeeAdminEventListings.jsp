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
<%@ page import="java.text.SimpleDateFormat"%>
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

<script language="javascript">

function postData(){
	document.frmRewalList.eventProcess.value = "eventViewAdminStaus";
	//alert(frmRewalList.eventProcess.value);
    document.frmRewalList.method="post";
    document.frmRewalList.action="EventOrgRenewal.do";
    document.frmRewalList.submit();
}
</script>


</head>

<body>

<%
String  requestStatus = (String)request.getAttribute("requestStatus");
if(requestStatus==null || requestStatus.equals("")){
	requestStatus="";
}
%>
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
						Meetings: <span class="styleBoldTwo">Endorsement Application  Listings </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
					<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--></td>
				  </tr>
				 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
				
						  <tr>
							<td colspan="7" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  
								</table>							</td>
						  </tr>
						  
						  <form name="frmRewalList" id="frmRewalList">
						   <input type="hidden" name="eventProcess" value="" />
						  <tr>
					  <th width="52" height="20" valign="top" class="alignLeft"> Status </th>
					  <td colspan="3" valign="middle" class="alignLeft">
					  <select name="requestStatus" class="selectboxOne" onChange="postData();">
                        <option selected="selected" value="">Select One</option>
						<%
							if(requestStatus.equals("Pending")){
						%>
                        <option value="Pending" selected="selected" >Pending</option>
						<%
							}
							else{
						%>
						 	<option value="Pending" >Pending</option>
						<%
							}
							if(requestStatus.equals("Approved")){
						%>
						<option value="Approved" selected="selected" >Approved</option>
						
						<%
						}
						else{
						%>
							<option value="Approved" >Approved</option>
						<%
						}
						if(requestStatus.equals("Rejected")){
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
				  </form>
						 <!-- <tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							  <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
						   </tr>-->
						  <tr>
							<td width="52" class="tblRowHead">Event ID </td>
							<td width="77" class="tblRowHead">Organizer ID </td>
							<td width="81" class="tblRowHead">Event Title </td>
							<td width="86" class="tblRowHead">Date of Registration </td>
							<td align="center" width="63" class="tblRowHead"> View</td>
							<td width="93" class="tblRowHead">Approve</td>
						  </tr>
						  
						  <%
						  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						  			String eventId =  "";							
									String organizerId =  "";	
									String eventTitle =  "";	
									String requestDate =  "";	
									String status =  "";	
						  ArrayList eventList = (ArrayList) request.getAttribute("displayAllEventDet");
						  
						  if(eventList!=null)
						  {
						  	System.out.print(" eventList.size() :"+ eventList.size());
							
						  }
						  else
						  {
						  	System.out.print(" empty ");						  
						  }
						  
						  
						  if(eventList!=null){
							  Iterator itEvent = eventList.iterator();
							  while(itEvent.hasNext()){
							        	HLCEventDetailsVO objEventDet = (HLCEventDetailsVO)itEvent.next();
									  eventId =  objEventDet.getEventId();									
									  organizerId =  objEventDet.getOrganizeId();
									  eventTitle =  objEventDet.getEventTitle();
									  if(objEventDet.getAddDate()!=null)
									  {
										  requestDate =  sdf.format(objEventDet.getAddDate());
									  }
									  else
									  {
									  	requestDate = "";
									  }
									  
									  status =  objEventDet.getStatusId();
									if(status==null)
										status = "";
						  
						  %>
						  <form name="eventList" method="post" action="EventOrgRenewal.do" />
						  
						  <tr>
							<td class="listCellBg"><%=eventId%></td>
							<td class="listCellBg"><%=organizerId%></td>
							<td class="listCellBg"><%=eventTitle%></td>
							<td class="listCellBg"><%=requestDate%></td>
							<td  align="center" class="listCellBg"><input name="Approve" type="button" class="twoBtn" value="View" onclick="location.href='editEventDetails.do?process=view&eventId=<%=eventId%>'" /></td>
							<td class="listCellBg"><input name="Approve" type="button" class="twoBtn" value="Approve" onclick="location.href='EventOrgRenewal.do?eventProcess=approve&eventId=<%=eventId%>'" /></td>
						  </tr>
						</form>	
						  <%
						  }
						  }
						  else{
						  %>
						<tr>
							<th height="25" colspan="6">No records are available. </th>
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
							<td height="19" colspan="8">&nbsp;</td>
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
