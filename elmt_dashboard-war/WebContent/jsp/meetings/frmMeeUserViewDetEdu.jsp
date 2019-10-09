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
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>
								  </tr>
<%
String memberId = (String) session.getAttribute("memberId");
if(memberId==null)
memberId = "NA";

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
				address2 = "NA";
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
				fax_no = "NA";
			}
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
			
			<!-- LEFT MENU ENDS HERE -->
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td>

					<form name="frmMeeUserEduAct"  action="meeting.do">
					
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Approve Sponsored Educational Activity Registration</span></td>
						</tr>
						 <tr>
							<td colspan="2" class="tblDescrp"><div id="commonBG" class="textCommon" style="height:100px;"> <img src="images/usea_logo.jpg" class="imgFloatLeft" /><strong><br />
					  </strong><br />
					</div></td>
						</tr>
						<!--<tr>
							<td colspan="2">
								 TABS START HERE 										
								<table cellpadding="0" cellspacing="0" border="0" class="container">
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
								</table>
							 TABS END HERE 
							</td>
						</tr>
						 -->
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								 <tr> 
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Activity Information: </span></td>
								  </tr>
								  	<%
									SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
									ArrayList dispDet = (ArrayList)request.getAttribute("displayFULLMeeDetails");
									
										if(dispDet==null && dispDet.size()==0){
										out.print("No records are available");
										}
									HLCActivityOrganizerVO objActDet = new HLCActivityOrganizerVO();
									Vector publication = new Vector();
									String tmp ="";
									String actMeetId ="";
									String otherSpecific ="";
									String otherFacilities ="";
											Iterator it = dispDet.iterator();
											while(it.hasNext()){
											objActDet = (HLCActivityOrganizerVO)it.next();
											publication = (Vector)it.next();
												actMeetId = objActDet.getActivityMeetingId();
 												if(objActDet.getActivityDate()!=null){
													tmp = sdf.format(objActDet.getActivityDate());
												}
												otherSpecific = objActDet.getOtherActivityType();
												if(otherSpecific==null || otherSpecific.equals("")){
												otherSpecific = "NA";
												}

												otherFacilities= objActDet.getOtherFacilities();
												if(otherFacilities==null || otherFacilities.equals("")){
												otherFacilities = "NA";
												}

		 
 												Enumeration enumPub = publication.elements();
												while(enumPub.hasMoreElements()){
												
												String publicationEmail = (String)enumPub.nextElement();
												String mailingFormat =   (String)enumPub.nextElement(); 
												String mailingBy =(String) enumPub.nextElement();
												String mailingSortBy =(String) enumPub.nextElement();
												String  noOfCopies =(String)enumPub.nextElement(); 
												String mailStatus = (String)enumPub.nextElement();
												%>
 <tr> 
									<td width="209" class="tableLeft">Activity Name :</td>
									<th width="291" class="tableRight"><%=objActDet.getActivityName()%></th>
								  </tr>
								 
								  <tr> 
									<td width="209" class="tableLeft">Date:</td>
									<th width="291" class="tableRight"><%=tmp%></th>
								  </tr>
								    <%
						 				String finalArea ="";
										String areaName="";
										String getUseaAreaId = objActDet.getUseaAreaId();
										//out.print( "getUseaAreaId" + getUseaAreaId);
										ArrayList areaDetails = (ArrayList)session.getAttribute("DispAreaDetails");
										if(areaDetails!=null && areaDetails.size()!=0){
										Iterator areaIt = areaDetails.iterator();
										while(areaIt.hasNext()){
										String[] areaDet =(String[])areaIt.next();
										String areaId = areaDet[0];
										//out.print("areaId" +areaId);
										 areaName = areaDet[2];
										 //out.print("areaName" +areaName);
										if(areaId.equals(getUseaAreaId)){
										finalArea = areaName;
										//out.print("finalArea" + finalArea);
									}
							  }
							}
							  %>                                                                                 
						
								  <tr> 
									<td class="tableLeft">Area:</td>
									<th class="tableRight"><%=finalArea%>&nbsp;</th>
								  </tr>
								  
								  <tr>
									<td class="tableLeft"> Location:</td>
									<td class="tableRight"><%=objActDet.getLocation()%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft"> State:</td>
									<td class="tableRight"><%=objActDet.getState()%></td>
					  
						<tr>
							<td colspan="2" class="tblRowHead">Organizer Information</td>
						  <tr>
							<td class="tableLeft"> Memberl ID:</td>
							<td class="tableRight">
						<%=memberId%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Organizer First Name:</td>
							<td class="tableRight"><%=first_name%>&nbsp;</td>
						  </tr>
						    <tr>
							<td class="tableLeft"> Organizer Middle Name:</td>
							<td class="tableRight"><%=middle_name%>&nbsp;</td>
						  </tr>
						  
						    <tr>
							<td class="tableLeft"> Organizer Last Name:</td>
							<td class="tableRight"><%=last_name%>&nbsp;</td>
						  </tr>
						  
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><%=address1%>&nbsp;</td>
						  </tr>
							 <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><%=address2%>&nbsp;</td>
						  </tr>						  
						  <tr> 
							<td class="tableLeft">Country:</td>
							<td class="tableRight"><%=country%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">State : </td>
							<td class="tableRight"><%=state%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">City:</td>
							<td class="tableRight"><%=city%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Zip: </td>
							<td class="tableRight"><%=zip%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Phone:</td>
							<td class="tableRight"><%=phone_no%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Fax: </td>
							<td class="tableRight"><%=fax_no%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Email ID: </td>
							<td class="tableRight"><%=email_id%>&nbsp;</td>
						  </tr>
							 </table>
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->
							</td>
						 </tr>
						 
						 <tr id="part2" class="holderDivTwo">
						 	<td colspan="2">
							
							<!--++++++++++++++++++++ Part 2 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
						<%
										String activityTypeId = objActDet.getActivityTypeId();
										String activityName1 ="";
										String finalActivity="";
										String id ="";
										ArrayList activityDetails = (ArrayList)session.getAttribute("DisplayActivityTypeDetails");
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
								   <tr>
									<td class="tableLeft"> Type Of Activity : </td>
									<td class="tableRight"><%=finalActivity%>&nbsp;</td>
								  	</tr>
								 
								
								  <tr>
									<td class="tableLeft">Other Specific Id</td>
									<td class="tableRight"><%=otherSpecific%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Fee To Be Charged:  </td>
									<td class="tableRight"><strong>$</strong><%=objActDet.getActivityFees()%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Instructor(s)/Coach(es):  </td>
									<td class="tableRight"><%=objActDet.getInstructorsCoaches()%>&nbsp;</td>
								  </tr>
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>
									<td class="tableRight"><%=objActDet.getFacilities()%>&nbsp;</td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft">Other Facilities:</td>
									<td class="tableRight"><%=otherFacilities%>&nbsp;</td>
								  </tr>
								  <tr>
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Publicity And Mailing List: </span></td>
								  </tr>
								
								  <tr>
									<td class="tableLeft"> Email logo (JPEG): </td>
									<td class="tableRight"><%=publicationEmail%></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Complimentary Mailing List Format: </td>
									<th class="tableRight"> <%=mailingFormat%></th>
								  </tr>
								  <tr>
									<td height="27" class="tableLeft"><span class="row"><span class="label">Send Mailing List by :</span></span></td>
									<td class="tableRight"><%=mailingBy%></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> <span class="row"><span class="label">Sort Mailing List by :</span></span></td>
									<td class="tableRight"><%=mailingSortBy%></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> <span class="row"><span class="label">Number of copies of the colour brochure :</span></span></td>
									<th class="tableRight"><%=noOfCopies%></th>
								  </tr>
								   <tr>
									<td class="tableLeft"> <span class="row"><span class="label">Mailing Status By Area or States:</span></span></td>
									<th class="tableRight"><%=mailStatus%></th>
								  </tr>
								  <%}%>
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
							<th class="tableRight"><%=objActDet.getLandOwnerName()%></th>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<th class="tableRight"><%=objActDet.getLandOwnerBusinessName()%></th>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><%=objActDet.getLandOwnerAddress()%></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Country: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerCountry()%></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight"><%=objActDet.getLandOwnerState()%></td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerCity()%></td>
						  </tr>
						   <tr>
							<td class="tableLeft">Zip: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerZip()%></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Tel: </td>
							<td class="tableRight"><%=objActDet.getLandOwnerPhone()%></td>
						  </tr>
						   <tr>
							<td class="tableLeft">Upload Additional Site Information: </td>
							<td class="tableRight"><%=objActDet.isAdditionalSites()%></td>
						  </tr>
							<input type="hidden" name="orgMeetingId" value="<%=actMeetId%>">
						  <%}%>
						</table>
						<!--++++++++++++++++++++ Part 3 of the form ends here ++++++++++++++++++++++++++++++ -->		
		
						</td>
					 </tr>
						 
						 <tr id="part4" class="holderDivTwo">
						<td colspan="2">
						

						<!--++++++++++++++++++++ Part 4 of the form starts here ++++++++++++++++++++++++++++++ -->	
							
							
									<input type="hidden" name="meeProcess" value="showOrgMee">	
									
								<tr>
									<td colspan="2" class="alignCenter">
									<input name="butName" type="submit" value="apply" class="gradBtn" />&nbsp;&nbsp;
									<input name="button" type="button" value="cancel" class="gradBtn"  onclick="javascript:history.back(-1);"/></td>
							   </tr>
							   
							</table>
						</form>
							<!--++++++++++++++++++++ Part 4 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
						</td>
					 </tr>
				
					  </table>
					 
					  
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