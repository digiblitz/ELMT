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

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 

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
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Division Event Detail Master </span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">To edit a Event Division name click on <strong>'Edit'</strong> button. To change the status click on the <strong>'Activate/Deactivate'</strong> button.					</td>
				  </tr>
				 
				 <tr>
					<td>
					
						 <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
<!--						  <tr>
							<td colspan="6">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   
								</table>							</td>
						 </tr>-->
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									<th colspan="2" width="125" height="20" valign="top" class="alignLeft">Event Divisions :&nbsp; </th>
									<td colspan="3" valign="middle" class="alignLeft">
									<!--<span class="legendTwo">&nbsp;</span> &nbsp;View.-->									</td>
									<!--
									<td colspan="3" valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
									<span class="legendOne">&nbsp;</span> &nbsp;Delete.
									</td>
									-->
							      </tr>
								</table>
								
							</td>
						 </tr>
						  <tr>
							<td width="123" height="27" class="tblRowHead">Event Divisions</td>
							<td width="133" class="tblRowHead">Status</td>
							<td width="54" class="tblRowHead">Edit</td>
							
						   </tr>

					<%
		   		
					 Vector eventListView=(Vector)request.getAttribute("EventAllList");
					 if(eventListView!=null && eventListView.size()!=0){ 
					 Enumeration itEvent = eventListView.elements();
					  while(itEvent.hasMoreElements()){
						    String [] eventTypeDetail  = (String[]) itEvent.nextElement();
							String eventId = eventTypeDetail[0];
							String eventName = eventTypeDetail[1];
							String eventStatus= eventTypeDetail[2];
							String eventDate = eventTypeDetail[3];
							String val = "";
				    %>
							<form name="frmMeeDivisionEventList" method="post" action="./actionDivisionEventType.do">	
							<input name="process" type="hidden" value="view">	

							<input name="eventId" type="hidden" value="<%=eventId%>">
							<input name="eventName" type="hidden" value="<%=eventName%>" >
						  <tr>
		<td height="26" class="listCellBg"><%=eventName%></td>
		 <td class="listCellBg">
		 <% if(eventStatus.equalsIgnoreCase("0")){ %>
		 <input name="btnSubmit" type="submit"  class="oneBtn" value="Activate" onclick="location.href='./actionDivisionEventType.do?process=activate&eventId=<%=eventId%>?eventName=<%=eventName%>'" />
		<%} else if(eventStatus.equalsIgnoreCase("1")){		%>
		<input name="btnSubmit" type="submit"  class="oneBtn" value="Deactivate" onclick="location.href='./actionDivisionEventType.do?process=deactivate&eventId=<%=eventId%>'" />
		 <% }%>
		</td>
		 
		 <td class="listCellBg"><input name="btnSubmit" type="button"  class="oneBtn" value="Edit" onclick="location.href='./actionDivisionEventType.do?process=edit&eventId=<%=eventId%>?eventName=<%=eventName%>'" /></td>
		 
						   </tr>
						</form>
		   
					      <%	}
									}else {
								%>
								<tr>
								  <th height="25" colspan="5">No Records are available. </th>
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
							<td height="19" colspan="7">&nbsp;</td>
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
