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
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<script language="javascript">

/*function postData(){
	frmRewalList.eventProcess.value = "show";
	//alert(frmRewalList.eventProcess.value);
    frmRewalList.method="post";
    frmRewalList.action="EventOrgRenewal.do";
    frmRewalList.submit();
}*/
function postData(status){
location.href="EventOrgRenewal.do?eventProcess=show&status="+status;

}	
</script>
<%
String  status = (String)request.getAttribute("status");
if(status==null || status.equals("")){
	status="";
}
%>

<%@ page import="java.util.*" %> 
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
						Meetings: <span class="styleBoldTwo">List of Endorsed Form </span>
					</td>
				  </tr>
				  
				 
				 <tr>
					<td>
						<table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						 
						   <form name="frmRewalList" id="frmRewalList">
						   <input type="hidden" name="eventProcess" value="" />
						  <tr>
					  <th width="48" height="20" valign="top" class="alignLeft"> Status </th>
					  <td width="206" colspan="3" valign="middle" class="alignLeft">
					  <select name="status" class="selectboxOne" onChange="postData(this.value);">
                        <option selected="selected" value="">Select One</option>
						<%
							if(status.equals("Pending")){
						%>
                        <option value="Pending" selected="selected" >Pending</option>
						<%
							}
							else{
						%>
						 	<option value="Pending" >Pending</option>
						<%
							}
							if(status.equals("Approved")){
						%>
						<option value="Approved" selected="selected" >Approved</option>
						
						<%
						}
						else{
						%>
							<option value="Approved" >Approved</option>
						<%
						}
						if(status.equals("Rejected")){
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
				 
						  <tr>
							<td width="80" height="27" class="tblRowHead">Event  ID </td>
							<td width="122" class="tblRowHead">Compition Name </td>
							<!--<td width="86" class="tblRowHead">Compition Location</td>-->
							<td width="86" class="tblRowHead">Compition  to be Held </td>
							<td width="86" class="tblRowHead">View</td>
							<td width="86" class="tblRowHead">Approve</td>
						  </tr>
						 
							<%
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
					String renewalId ="";
					String eventId ="";
					String compName ="";
					String compLoc ="";
					String comDate ="";
					ArrayList renewalList = (ArrayList)session.getAttribute("DisplayRenewalDetails");
					if(renewalList!=null && renewalList.size()!=0){
						Iterator e = renewalList.iterator();
						 while(e.hasNext()){
								HLCRenewalOrganizerDetails objRenewalDet = (HLCRenewalOrganizerDetails)e.next();
								    renewalId = objRenewalDet.getRenewalId();
									if(objRenewalDet.getRenewalId()!=null){
									  	  renewalId = objRenewalDet.getRenewalId();
										}
									  if(objRenewalDet.getEventId()!=0){
									  		eventId = String.valueOf(objRenewalDet.getEventId());
									  }
									   if(objRenewalDet.getCompetitionDate()!=null){
									     comDate = sdf.format(objRenewalDet.getCompetitionDate());
									   }
									   compName =objRenewalDet.getCompetitionName();
									   //compLoc =objRenewalDet.getCompetitionLocation();
						%>
					   
					   <form name="frmRewalListDet" id="myform" method="post" class="formcss" action="EventOrgRenewal.do">
						   <input type="hidden" name="eventProcess" value="display" />
						    <input type="hidden" name="eventId" value="<%=eventId%>" />
						 <input type="hidden" name="renewalId" value="<%=renewalId%>" />
						  <tr>
							<td height="26" class="listCellBg"><%=eventId%></td>
							<td class="listCellBg"><%=compName%></td>
							<!--<td class="listCellBg"><%//=compLoc%></td>-->
							<td class="listCellBg"><%=comDate%></td>							
							<td class="listCellBg"><input name="butName" type="submit" class="oneBtn" value="view" /></td>
							<td class="listCellBg"><input name="butName" type="button" class="oneBtn" value="Approve" onclick="location.href='EventOrgRenewal.do?eventProcess=initApprove&eventId=<%=eventId%>&renewalId=<%=renewalId%>'"/></td>
							
						  </tr>
                       </form>	
  
                                           
							<%
							}
							}
							else {
					%>
					<tr>
					  <th height="25" colspan="5" align="center">No records are available. </th>
					</tr>
					<%}%> 
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

