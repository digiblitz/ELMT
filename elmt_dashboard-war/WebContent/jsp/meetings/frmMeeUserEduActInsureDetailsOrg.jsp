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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldTwo">Insurance Release Details - Educational Activity </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">&nbsp;
					 
					 </td>
				  </tr>
				  <tr>
					<td>
					
<%
String memberSesId = (String) session.getAttribute("memberId");
if(memberSesId==null)
memberSesId = "Memebr Id Not Exist";

	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
HLCActivityOrganizerVO objActivityDet = (HLCActivityOrganizerVO) session.getAttribute("ActivityOrganizerVODet");


	ArrayList listContact = (ArrayList) session.getAttribute("dynamicOrgDetails");
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null)
				middle_name = "";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null)
				address2 = "";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null)
				fax_no = "";
			}
		}
%>									
					
					
							
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Activity Details: </td>
						  </tr>
						 
						  <tr> 
							<td class="tableLeft">Name of Activity:</td>
							<th class="tableRight"><%=objActivityDet.getActivityName()%> &nbsp;</th>
						  </tr>
						   <%
						 				String finalArea ="";
										String areaName="";
										String getUseaAreaId = objActivityDet.getUseaAreaId();
										ArrayList areaDetails = (ArrayList)request.getAttribute("DispAreaDetails");
										if(areaDetails!=null && areaDetails.size()!=0){
										Iterator areaIt = areaDetails.iterator();
										while(areaIt.hasNext()){
										String[] areaDet =(String[])areaIt.next();
										String areaId = areaDet[0];
										 areaName = areaDet[2];
										if(areaId.equals(getUseaAreaId)){
										finalArea = areaName;
										
									}
							  }
							}
							  %>                                                                                 
						
								  <tr> 
									<td class="tableLeft">Area:</td>
									<th class="tableRight"><%=finalArea%>&nbsp;</th>
								  </tr>
								  <%
								  String actDate ="";
								  if(objActivityDet.getActivityDate()!=null){
								  actDate = sdf.format(objActivityDet.getActivityDate());
								  }
								  
								  %>
						
						  <tr> 
							<td class="tableLeft">Date(s)to be held:</td>
							<th class="tableRight"><%=actDate%>&nbsp;</th>
						  </tr>
						  <tr> 
							<td class="tableLeft">Location:</td>
							<td class="tableRight"><%=objActivityDet.getLocation()%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">State:</td>
							<td class="tableRight"><%=objActivityDet.getState()%>&nbsp;</td>
						  </tr>
							<tr> 
								<td colspan="2" class="tblRowHead"> Participant&#8217;s Details: </td>
							</tr>
							<tr> 
							<td class="tableLeft">Email Id:</td>
							<td   class="tableRight"><%=email_id%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">First 
								Name:</td>
							<td   class="tableRight"><%=first_name%>&nbsp;</td>
						  </tr>
						  <tr>
						    <td height="27" class="tableLeft">Middle Name: </td>
						    <td  class="tableRight">
						      <%=middle_name%>&nbsp;						    </td>
					      </tr>
						  <tr>
                            <td height="27" class="tableLeft">Last 
                              Name:</td>
						    <td  class="tableRight"><%=last_name%>&nbsp;</td>
					      </tr>
		
						  <tr> 
							<td class="tableLeft"> Address:</td>
							<td  class="tableRight"><%=address1%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Address2:</td>
							<td   class="tableRight"><%=address2%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Country:</td>
							<td   class="tableRight"><%=country%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> State</td>
							<td   class="tableRight"> <span class="row"><span class="formX"> 
							 <%=state%>&nbsp;
							  </span></span></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> City</td>
							<td  class="tableRight"><%=city%>&nbsp;</td>
						  </tr>

						  <tr> 
							<td class="tableLeft"> Zip Code</td>
							<td  class="tableRight"><%=zip%>&nbsp;</td>
						  </tr>
						  <tr>
                            <td class="tableLeft">Phone</td>
						    <td  class="tableRight"><%=phone_no%>&nbsp;</td>
					      </tr>
						  <tr> 
							<td class="tableLeft">Fax</td>
							<td class="tableRight"><%=fax_no%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td colspan="2" class="tblRowHead"> Other Information: </td>
						  </tr>
						<%  
						
  								String releaseId =  "";
								String activityMeetingId = "";
								String userIdVal =  "";
								String eventLevelId =  "";
								String memberId = "";
								String noOfHorses =  "";
								String reqStatus =  "";
								String reqDate =  "";
								HLCActivityUserVO objActUser = (HLCActivityUserVO)session.getAttribute("DispParticularUserDetail");
							   
								  releaseId =  objActUser.getReleaseId();
								  activityMeetingId = objActUser.getActivityMeetingId();
								  userIdVal =  objActUser.getUserId();
								  eventLevelId =  objActUser.getEventLevelId();
								  memberId =  objActUser.getMemberId();
								  noOfHorses =  objActUser.getNoOfHorses();
								  reqStatus =  objActUser.getRequestStatus();
								  if(objActUser.getAddDate()!=null){
								  	reqDate =  sdf.format(objActUser.getAddDate());
								  }
								  boolean memStatus = objActUser.isMembershipStatus();
									if(memberId==null){
									 memberId="Not a Member";
									}
									if(reqStatus==null){
										reqStatus="";
									}
						  
						  %>
						  	<form name="frmMeeInsureICPDetails" id="myform" action="meeting.do">
							<input type="hidden" name="meeProcess" value="userUpdateStatus" />
							<input type="hidden" name="releaseId" value="<%=releaseId%>" />
						  <tr> 
							<td class="tableLeft">Number of horses riding during activity:</td>
							<td class="tableRight"> <%=noOfHorses%>&nbsp;</td>
						  </tr>
						  <%
							String levelid="";
							String levelType="";
							String levelCode="";
							String finalLevel ="";
							Vector levels = (Vector)request.getAttribute("AllLevels");
							if(levels.size()!=0 && levels !=null){
								Enumeration enum = levels.elements();
								while(enum.hasMoreElements()){
									String[] levelNames = (String[])enum.nextElement();
									levelid = (String)levelNames[0];
									levelType = (String)levelNames[1];
									levelCode = (String)levelNames[2];
									if(levelid.equals(eventLevelId)){
									finalLevel= levelType +"-" +levelCode;
							}
							  }
							}
							  %>
						  <tr> 
							<td class="tableLeft">Level now riding:</td>
							<td  class="tableRight"> <%=finalLevel%>&nbsp;</td>
						  </tr>
						  <%
						  String statusMem ="";
						  if(memStatus){
							  statusMem ="Yes";
							  }
							  else{
							  statusMem ="No";
							  }
						  %>
						  <tr> 
							<td class="tableLeft"> Member:</td>
							<th class="tableRight"><%=statusMem%>&nbsp;</th>
						  </tr>
						  <tr> 
							<td class="tableLeft"> membership number:</td>
							<th class="tableRight"><%=memberId%>&nbsp;</th>
						  </tr>
						  <tr> 
							<td colspan="2" class="tblRowHead"> Approval Status: </td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft">Status:</td>
							<th class="tableRight"><%=reqStatus%>&nbsp;</th>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							<%
							if(reqStatus.equals("Approved")){
							%>
							<input type="submit" class="gradBtn" value="Rejected" name="userChangeStatus" />
							<%
							}
							else{
							%>
  							<input type="submit" class="gradBtn" value="Approve" name="userChangeStatus" />
							<%
							}
							%>
							<input type="button" class="gradBtn" value="Back" onclick="javascript:history.back(-1);" />							 </td>
						  </tr>
						  </form>
						</table>
							
						
					</td>
				  </tr>
						  <tr>
				  
					<td height="20">&nbsp;  
					  <!-- DO NOT DELETE THIS ROW -->
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
