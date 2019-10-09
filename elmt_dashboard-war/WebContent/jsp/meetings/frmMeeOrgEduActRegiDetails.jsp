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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong> Meetings: </strong> <span class="styleBoldTwo">Organizer Educational Activity Registration Details</span></td>
				  </tr>
				   
				  <tr>
					<td>
	<%
//String memberId = (String)request.getAttribute("memberId");
//if(memberId==null)
//memberId = "Memebr Id Not Exist";
ArrayList listContact = (ArrayList)request.getAttribute("DisplaymemberDetails");
	
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
				prefix1 = "NA";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "NA";
				middle_name  = (String)it.next();
				if(middle_name==null || middle_name.equals("")) 
				middle_name = "NA";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "NA";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "NA";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "NA";
				suite =  (String)it.next();
				if(suite==null)
				suite = "NA";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "NA";
				address2 = (String)it.next();
				if(address2==null || address2.equals("")) 
				address2 = "NA";
				city = (String)it.next();
				if(city==null)
				city = "NA";
				state =  (String)it.next();
				if(state==null)
				state = "NA";
				country = (String)it.next();
				if(country==null)
				country = "NA";
				zip = (String)it.next();
				if(zip==null)
				zip = "NA";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "NA";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "NA";
				fax_no = (String)it.next();
				if(fax_no==null || fax_no.equals("")) 
				fax_no = "NA";
			}
		}
%>
					
					<form name="frmMeeICPAssessment" action="">
					
							
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<!--<tr>
							<td colspan="2">-->
								<!-- TABS START HERE -->										
								<!--<table cellpadding="0" cellspacing="0" border="0" class="container">
									<tr>
										<td id="tabData1" class="tabHighlight" onclick="naviTab('1')">
											<a id="link1" href="javascript:void(0);" class="active"><span class="tabHead">Part A</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData2" class="tabLowlight" onclick="naviTab('2')">
											<a id="link2" href="javascript:void(0);" class="inactive"><span class="tabHead">Part B</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData3" class="tabLowlight" onclick="naviTab('3')">
											<a id="link3" href="javascript:void(0);" class="inactive"><span class="tabHead">Part C</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData4" class="tabLowlight" onclick="naviTab('4')">
											<a id="link4" href="javascript:void(0);" class="inactive"><span class="tabHead">Part D</span></A>
										</td>
									</tr>	
								</table>-->
								<!-- TABS END HERE -->
						<!--	</td>
						</tr>-->
						 
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								 <tr> 
									<td colspan="2" class="tblRowHead">Educational Activity Details:</td>
								  </tr>
								   	<%
									SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
									ArrayList dispDet = (ArrayList)request.getAttribute("OrgDetailsDisplay");
										HLCActivityOrganizerVO objActDet = new HLCActivityOrganizerVO();
										Vector publication = new Vector();
										String actMeetId ="";
										String status="";
										String comments ="";
											Iterator it = dispDet.iterator();
											if(it.hasNext()){
											objActDet = (HLCActivityOrganizerVO)it.next();
											publication = (Vector)it.next();
												actMeetId = objActDet.getActivityMeetingId();											
												Enumeration enumPub = publication.elements();
												if(enumPub.hasMoreElements()){
													String publicationEmail = (String)enumPub.nextElement();
													String mailingFormat =   (String)enumPub.nextElement(); 
													String mailingBy =(String) enumPub.nextElement();
													String mailingSortBy =(String) enumPub.nextElement();
													String noOfCopies =(String)enumPub.nextElement(); 
													String Status =(String)enumPub.nextElement(); 
												}
												%>
								  <tr>
								    <td class="tableLeft">Name of Activity </td>
								    <td class="tableRight"><%=objActDet.getActivityName()%>&nbsp;</td>
						      </tr>
							  <%
							  String activityDate="";
							  if(objActDet.getActivityDate()!=null){
							  activityDate =sdf.format(objActDet.getActivityDate());
							  }
							%>
							  
								  <tr> 
									<td class="tableLeft">Date:</td>
									<td class="tableRight"><%=activityDate%>&nbsp;</td>
								  </tr>
								   <tr> 
									<td class="tableLeft">No. of Days:</td>
									<td class="tableRight"><%=objActDet.getNoOfDays()%>&nbsp;</td>
								  </tr>
								   <%
						 				String finalArea ="";
										String areaName="";
										String getUseaAreaId = objActDet.getUseaAreaId();
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
									<td class="tableLeft"> Area:</td>
									<th class="tableRight"><%=finalArea%>&nbsp;</th>
								  </tr>
								  <tr> 
									<td class="tableLeft">Location:</td>
									<td class="tableRight"><%=objActDet.getLocation()%>&nbsp;</td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Country:</td>
									<td class="tableRight"><%=objActDet.getCountry()%>&nbsp;</td>
								  </tr>
								 <tr>
								    <td class="tableLeft">State:</td>
								    <td class="tableRight"><%=objActDet.getState()%>&nbsp;</td>
						         </tr>
								 <tr>
								    <td class="tableLeft">City:</td>
								    <td class="tableRight"><%=objActDet.getCity()%>&nbsp;</td>
						         </tr>
								 <tr>
								    <td class="tableLeft">ZipCode:</td>
								    <td class="tableRight"><%=objActDet.getZip()%>&nbsp;</td>
						         </tr>
								  <tr>
									<td colspan="2" class="tblRowHead">
									 <span class="rowHead">Organizer Information: </span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Member ID:</td>
									<th class="tableRight"><%=objActDet.getActivityOrganizerId()%>&nbsp;</th>
								  </tr>
								  <tr> 
									<td class="tableLeft">First Name:</td>
									<td class="tableRight"><%=first_name%>&nbsp;</td>
								  </tr>
								    <tr>
									<td class="tableLeft">Middle Name:</td>
									<td class="tableRight"><%=middle_name%>&nbsp;</td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Last Name:</td>
									<td class="tableRight"><%=last_name%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Address1:</td>
									<td class="tableRight"><%=address1%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Address2:</td>
									<td class="tableRight"><%=address2%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Country:</td>
									<td class="tableRight"><%=country%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> State:</td>
									<td class="tableRight"><%=state%>&nbsp;</td>
								  </tr>          
								  <tr>
									<td class="tableLeft"> City:</td>
									<td class="tableRight"><%=city%>&nbsp;</td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Zip:</td>
									<td class="tableRight"><%=zip%>&nbsp;</td>
								  </tr>
								
							 </table>
							
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->
							</td>
						 </tr>
						 
						 <tr id="part2" class="holderDivTwo">
						 	<td colspan="2">
							
							<!--++++++++++++++++++++ Part 2 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								  <tr> 
									<td height="27" class="tableLeft">Phone: </td>
									<td class="tableRight"><%=phone_no%>&nbsp;</td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> Fax: </td>
									<td class="tableRight"><%=fax_no%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> E-Mail: </td>
									<td class="tableRight"><%=email_id%>&nbsp;</td>
								  </tr>
							<%
								String facilites=objActDet.getFacilities();
							    String tmpFacilities ="";
								if(facilites!=null){
								  tmpFacilities=objActDet.getFacilities();
								}
							    String otherfacilites = objActDet.getOtherFacilities();
								if(otherfacilites==null || otherfacilites.equals("")){
									otherfacilites ="NA";
								}
																	 

							    %>	 
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>
									<td class="tableRight"><%=tmpFacilities%>&nbsp;</td>
								  </tr>
								   <tr>
									<td class="tableLeftTxtArea"> Other Facilities:  </td>
									<th class="tableRight"><%=otherfacilites%>&nbsp;</th>
								  </tr>
								  <tr>
									<td class="tableLeft"> Type Of Activity : </td>
															  										
										<%
										String activityTypeId = objActDet.getActivityTypeId();
										String activityName1 ="";
										String finalActivity="";
										String id ="";
										ArrayList activityDetails = (ArrayList)request.getAttribute("DisplayActivityTypeDetails");
										if(activityDetails!=null && activityDetails.size()!=0){
										Iterator it1 = activityDetails.iterator();
										while(it1.hasNext()){
										String[] eduDet =(String[])it1.next();
										id = eduDet[0];
										activityName1 = eduDet[1];
										if(id.equals(activityTypeId)){
										  finalActivity = activityName1;
										}
										
																
							  }
							}
							  %>
								<td class="tableRight"><%=finalActivity%>&nbsp;</td>
							  </tr>
							 <%
							 String otherAct ="";	  
							  if(objActDet.getOtherActivityType()!=null){
								  otherAct = objActDet.getOtherActivityType();
							  }
							  else{
									otherAct ="NA";
							  }
							  %>
								  <tr>
									<td class="tableLeft">Other Specific Activity </td>
									<th class="tableRight"><%=otherAct%>&nbsp;
									</th>
								  </tr>
								  <tr>
									<td class="tableLeft"> Fee To Be Charged(<strong>$</strong>):  </td>
									<td class="tableRight"><%=objActDet.getActivityFees()%>
								</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Instructor(s)/Coach(es):  </td>
									<td class="tableRight"><%=objActDet.getInstructorsCoaches()%>&nbsp;</td>
								  </tr>
								                                
								  <!--<tr>
									<td class="tableLeft"> If Other Specify:</td>
									<td class="tableRight"><%//=objActDet.getOtherFacilities()%>&nbsp;</td>
								  </tr>-->
								  
							</table>
							<!--++++++++++++++++++++ Part 2 of the form ends here ++++++++++++++++++++++++++++++ -->		
							
							</td>
						 </tr>
						  
					  	 <tr id="part3" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 3 of the form starts here ++++++++++++++++++++++++++++++ -->	
						<table cellpadding="0" cellspacing="0" border="0" class="formLayout">

						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Land Owner Details : </span></td>
						  </tr>
						  <tr>
							<td class="tableLeft">Land Owner Name :</td>
							<th class="tableRight"><%=objActDet.getLandOwnerName()%>&nbsp;</th>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<th class="tableRight"><%=objActDet.getLandOwnerBusinessName()%>&nbsp;</th>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><%=objActDet.getLandOwnerAddress()%>&nbsp;</td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Country: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerCountry()%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight"><%=objActDet.getLandOwnerState()%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerCity()%>&nbsp;</td>
						  </tr>
						    <tr>
							<td class="tableLeft">Zip: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerZip()%></td>
						  </tr>
						    <tr>
							<td class="tableLeft"> Tel: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerPhone()%></td>
						  </tr>
						 
						</table>
						<!--++++++++++++++++++++ Part 3 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
						</td>
					 </tr>
						 
						 <tr id="part4" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 4 of the form starts here ++++++++++++++++++++++++++++++ -->	
						
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								 
								   <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Approval Status</td>
								  </tr>
								  <%
									status = objActDet.getRequestStatus();
									if(status==null){
										status = "";
									}
								  %>
								  <tr>
									<td class="tableLeft">Status:</td>
									<td class="tableRight"><%=status%>&nbsp;</td>
								  </tr>	
								 <%
								comments = objActDet.getComments();
								 if(comments==null){
										comments = "Not Given";
									}
								  %>
								  <tr> 
									<td class="tableLeftTxtArea">Comments:</td>
									<td class="tableRight"><%=comments%></td>
								  </tr>
  <%}%>
							
							  
								<tr>
									<td colspan="2" class="alignCenter"><input type="button" value="Back" class="gradBtn" onclick="javascript:history.back(-1);"/></td>
							   </tr>
							  
							</table>
							<!--++++++++++++++++++++ Part 4 of the form ends here ++++++++++++++++++++++++++++++ -->		

						</td>
					 </tr>
				
					  </table>
					  </form>
					  
					</td>
				  </tr>
				  <tr>
						<td>&nbsp; 
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
