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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import ="com.hlcmeeting.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script>
<%
String statusName = (String) request.getAttribute("statusName");
if(statusName==null) statusName="";
%>
</script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
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
				<table width="100%"  border="0"  align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
					Meetings: <span class="styleBoldTwo">Annual Meeting &amp; Convention List Page </span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">&nbsp;</td>
				  </tr>
				 
				 <tr>
					<td>
					
						 <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" class="formLayout">
						 <tr>
						 	<td colspan="7" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								</table>							</td>
						 </tr>
						  <tr>
							<td width="101" class="tblRowHead">Badge Name </td>
							<td width="94" class="tblRowHead">Full Name </td>
							<td width="119" class="tblRowHead">Member Type </td>
							<td width="75" class="tblRowHead">Request Status </td>
							<td width="70" class="tblRowHead">View</td>
						   </tr>

					<%
		   		
					 ArrayList memberDetail=(ArrayList)request.getAttribute("viewMemberDetails");
					 String firstName="";
							String memTypeName="";
							String badgeName="";
							String annualMeetingId ="";
							 String ardId  = "";
							 
							 			
					 if(memberDetail!=null && memberDetail.size()!=0){ 
						Iterator  enm1 = memberDetail.iterator(); 
							while(enm1.hasNext()){
							HLCAnnualRegistrationDetailVO objRegDet = (HLCAnnualRegistrationDetailVO)enm1.next();
							
							String reqStatus = objRegDet.getRequestStatus();
                    		Date date = objRegDet.getAddDate();
							String remarks = objRegDet.getRemarks();
							String lName = objRegDet.getLastName();
                    
                    
					 
							
							  ardId = objRegDet.getArdId();
							if(annualMeetingId!=null){
							  firstName = objRegDet.getFirstName();
							  memTypeName = objRegDet.getMemTypeName();
							  badgeName = objRegDet.getBadgeName();
							}  
							 if(memTypeName==null || memTypeName.trim().length()==0)  memTypeName = "N/A";
							 if(badgeName==null || badgeName.trim().length()==0)  badgeName = "N/A";							
		   %>
		   <!-- {annualMeetingId,badgeInfo,firstName,lastName,areaName,requestStatus,addDate,email};  -->

							<form name="frmDisplayAdminList" method="post" action="AnnualRegList.do">		
							<input name="memProcess" type="hidden" value="detailedUserDetails">
							<input name="ardId" type="hidden" value="<%=ardId%>">
						  <tr>
							<td class="listCellBg"><%=badgeName%> </td>
							<td class="listCellBg"><%=firstName%>&nbsp;<%=lName%> </td>
							<td class="listCellBg"><%=memTypeName%></td>
							<td class="listCellBg"><%=reqStatus%></td>
							<td class="listCellBg">
							<input name="Submit2" type="submit" class="oneBtn" value="View" />							</td>
						   </tr>
						</form>
		   
					      <%	}
									}else {
								%>
								<tr>
								  <th height="25" colspan="6">No Records are available. </th>
								</tr>
								<%}%>

						<!--  <tr>
							<td height="26" class="listCellBg"><a href="#" class="linkFive">DJango</a></td>
							<td class="listCellBg">Ben</td>
							<td class="listCellBg">Stephen</td>
							<td class="listCellBg">django@email.com</td>
							<td class="listCellBg"><input name="Submit3" type="submit" class="oneBtn" value="View" /></td>
						   </tr> -->
						  <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
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
