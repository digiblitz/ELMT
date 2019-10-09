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
<%@ page import="java.util.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.common.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="meetings.title"/></title>
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
			  <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->


		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
						USEA Meetings: <span class="styleBoldTwo"> Organizer's Educational Activity Recap Registration Listings </span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">&nbsp;</td>
				  </tr>
				 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="left" cellspacing="1" class="formLayout">
				
						 <!--<tr>
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
						    <td width="90" height="27" class="tblRowHeadTwo">Event id </td>
							<td width="113" class="tblRowHeadTwo">Activity Name</td>
							<td width="89" class="tblRowHeadTwo">Date of Registration </td>
							<td width="99" class="tblRowHeadTwo">Registration Status</td>
							<td width="68" class="tblRowHeadTwo">View</td>
						  </tr>
						  	<%SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
								String addDate = "";
								ArrayList recapList = (ArrayList) session.getAttribute("DispAllOrgRecapDetails");
								if(recapList!=null && recapList.size()!=0){
									Iterator eRecap = recapList.iterator();
									while(eRecap.hasNext()){
										ArrayList listRecap = (ArrayList) eRecap.next();
											Iterator e = listRecap.iterator();
												while(e.hasNext()){
													String recapId = (String)e.next();
													String mettingId = (String)e.next();
													String activityName = (String)e.next();
													Date reqDate = (Date)e.next();
													if(reqDate!=null){
												//out.print("reqDate" + reqDate);
												addDate =  sdf.format(reqDate);
												}
													String requestStatus = (String)e.next();
								%>
								<form name="frmRecap" method="post" action="recap.do" />
								<input type="hidden" name="recapProcess" value="showMyRecapAct"/>
								<input type="hidden" name="orgRecapId" value="<%=recapId%>"/>							
							  <tr>
								<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=mettingId%> </td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=activityName%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=addDate%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><%=requestStatus%></td>
								<td bgcolor="#E3E1D2" class="alignCenter"><input name="Submit2" type="submit" class="oneBtn" value="View" /></td>
							  </tr>
								</form>
								  <%
								  }
							  }
							}
							else{
							%>
							
							 <tr>
								<td colspan="5" height="26" class="alignCenter">No  Records are available </td>
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
							<td height="19" colspan="5">&nbsp;</td>
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
