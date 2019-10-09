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

	if(document.myform.acStatus.value==""){
		alert("Select a Status");
		document.myform.acStatus.focus();
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
						Meetings: <span class="styleBoldTwo">Organizer Endorsement  Application  Listings </span>
					</td>
				  </tr>
	<tr>
				<td class="listCellBg">
				Listed below are the current Provisional Calendar Events listing you as the Organizer.
				<P align="justify"> To review the Event details, click on the<strong> Event ID</strong>.To edit event divisions, click the <strong>Edit</strong> button</P>
				
<P align="justify">
Once payment is made, click <strong>Update</strong> button to create/update the Omnibus Listing
</P>
				
				</td>
				</tr>  			 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
				<form name="myform" id="myform" method="post" action="./EventOrgRenewal.do" onsubmit="return onValidate();">
				<!--<input name="eventProcess" type="hidden" value="listDetails">	-->					
				
				</form>
						 <!-- <tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							  <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
						   </tr>-->
						   <tr>
							<td width="57" class="tblRowHead">Event ID </td>
							<td width="61" class="tblRowHead">Event Tile </td>
							<td width="76" class="tblRowHead">Event Date </td>
							<td width="56" class="tblRowHead">Status</td>
							<!--<td width="65" class="tblRowHead">Edit</td>-->
							<td width="58" class="tblRowHead">Edit Event </td>
							<td width="96" class="tblRowHead">Register Event </td>
							<td width="106" class="tblRowHead">Omnibus Listing </td>
							
						
							<!--<td width="65" class="tblRowHead">Renew</td>-->
						  </tr>
						  
					    <%
						  String appStatus = "";
						  String reqAppStatus = "";	
						  String regStatus="";
						  String logBy="user";
						  String loggedBy="";
						if(session.getAttribute("loggedBy")!=null){
						
						loggedBy=(String)session.getAttribute("loggedBy");
						logBy=loggedBy;
						}
					//	System.out.println("logBy"+logBy);
							/*String loggedBy="";
							loggedBy=(String)session.getAttribute("loggedBy");
							String logBy=loggedBy;*/
						  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						  ArrayList eventList = (ArrayList) request.getAttribute("displayOrgAllEventDet");
						  
						 // out.print("MemberId:" + (String) session.getAttribute("memberId") + eventList.size());
						  
						  if(eventList!=null && eventList.size()!=0){
							  Iterator itEvent = eventList.iterator();
							  while(itEvent.hasNext()){
									HLCEventDetailsVO objEventDet = (HLCEventDetailsVO)itEvent.next();
									String eventId =  objEventDet.getEventId();									
									String organizerId =  objEventDet.getOrganizeId();
									String eventTitle =  objEventDet.getEventTitle();							
									String requestDate="N/G";
									String eveBegDt1="N/G";
									String paymentId=objEventDet.getPaymentId();
									if(objEventDet.getAddDate()!=null){
									    requestDate =  sdf.format(objEventDet.getAddDate());
										//System.out.println("requestDate in jsp"+requestDate);
									}
									String statusId =  objEventDet.getStatusId();
									String reqStatus=objEventDet.getRequestStatus();
									boolean payStat=objEventDet.isPayExist();
									String provisonalId =  objEventDet.getProvisId();
									boolean entryStat=objEventDet.isEntryFeeExist();
									
									//System.out.println("entryStat jsp"+entryStat);
									if(objEventDet.getEveBegDt()!=null){
									    eveBegDt1 =  sdf.format(objEventDet.getEveBegDt());
										//System.out.println("eveBegDt1 in jsp"+eveBegDt1);
									}
										
									if(statusId!=null && statusId.equalsIgnoreCase("Approved")) appStatus = "";
									else appStatus = "disabled";
									
									if(statusId!=null && statusId.equalsIgnoreCase("Approved") && paymentId!=null) regStatus = "";
									else regStatus = "disabled";
									
									if(reqStatus!=null && reqStatus.equalsIgnoreCase("Approved")) reqAppStatus = "";
									else reqAppStatus = "disabled";													
									%>
						  <form name="eventList" method="post" action="OrganizerUSEAEventReg.do" />
						  <input type="hidden" name="process" value="initUpdate"/>					 
						  <input type="hidden" name="eventId" value="<%=eventId%>"/>						 
						  <tr>
							<td class="listCellBg"><a href="eventRegList.do?cmd=viewNewEventReg&eventId=<%=eventId%>"><%=eventId%></a></td>
							<td class="listCellBg"><%=eventTitle%></td>
							<td class="listCellBg"><%=eveBegDt1%></td>
						     
							<td class="listCellBg"><%=statusId%></td>
	
	
	<%if(provisonalId!=null && !(provisonalId.equalsIgnoreCase(""))){%>
<td class="listCellBg" align="center"><input type="button" name="process" value="Edit" class="twoBtn" onclick="location.href='calender.do?method=initUpdate&provisionalId=<%=provisonalId%>'"/></td>	
<%}else{%>
<td class="listCellBg" align="center"><input type="button" name="process" value="Edit" class="twoBtn2" onclick="location.href='calender.do?method=initUpdate&provisionalId=<%=provisonalId%>'" disabled="disabled"/></td>	
<%}%>
	
<%if(payStat==true){%>

<td class="listCellBg" align="center"> <input name="button" type="button" class="twoBtn" value="View/Print" disabled="disabled" onclick="window.open('http://reports.useventing.com/ReportServer?/Public/USEF_endorsed_app&rs:Command=Render&rs:format=PDF&EVENTID=<%=eventId%>')" /></td>	
<%}else if(appStatus!=null && appStatus.equalsIgnoreCase("disabled")){%>						 
	<td class="listCellBg" align="center"><input name="Submit4" type="button" class="twoBtn2" value="Submit/Pay" disabled="disabled" onclick="location.href='EventOrgRenewal.do?eventProcess=newReq&eventId=<%=eventId%>&requestDate=<%=requestDate%>'" /></td>	
	<%}else{%>
<td class="listCellBg" align="center"><input name="Submit4" type="button" class="twoBtn" value="Submit/Pay" onclick="location.href='EventOrgRenewal.do?eventProcess=newReq&eventId=<%=eventId%>&requestDate=<%=requestDate%>'" /></td>	
<%}%>
<%if(regStatus!=null && regStatus.equalsIgnoreCase("disabled")){%>
<td class="listCellBg" align="center"><input name="Submit5" type="submit" value="Update" class="twoBtn2" disabled="disabled"/></td>
<%}else{%>
<td class="listCellBg" align="center"><input name="Submit5" type="submit" value="Update" class="twoBtn"/></td>
<%}%>
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
