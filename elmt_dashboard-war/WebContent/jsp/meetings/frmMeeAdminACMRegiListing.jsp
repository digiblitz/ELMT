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
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import ="com.hlcmeeting.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">

function postData(){
	frmMeeDispLst.memProcess.value = "adminViewList";
    frmMeeDispLst.method="post";
    frmMeeDispLst.action="AnnualRegList.do";
    frmMeeDispLst.submit();
}
</script>

<%
String  status = (String)request.getAttribute("status");
if(status==null || status.equals("")){
	status="";
}
%>


</script>
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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
					<span class="styleBoldTwo"> Annual Meeting &amp; Convention List Page</span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">&nbsp;</td>
				  </tr>
				 <tr>
					<td valign="top">
					
						<table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" >
						 
						 		  <tr>
		   	<td colspan="6" class="tableSpan">
			<form name="frmMeeDispLst" >
			<input name="memProcess" type="hidden" value="">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
				   <tr>
					  <th width="48" height="20" valign="top" class="alignLeft"> Status </th>
					  <td width="206" colspan="3" valign="middle" class="alignLeft">
					  <select name="status" class="selectboxOne" onchange="postData();">
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
							if(status.equals("Registered")){
						%>
						<option value="Registered" selected="selected" >Registered</option>
						
						<%
						}
						else{
						%>
							<option value="Registered" >Registered</option>
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
				</table>
			  </form>			</td>
		   </tr>
						  <tr>
							<td width="121" class="tblRowHead">Name</td>
							<td width="81" class="tblRowHead">Request Date </td>
							<td width="77" class="tblRowHead"><strong>Registered By</strong></td>
							<td width="55" class="tblRowHead">Request Status </td>
							<td width="60" class="tblRowHead">Status Change </td>
							<td width="63" class="tblRowHead">View</td>
					      </tr>
						  
						 
							<%
							
							SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
							ArrayList userList = (ArrayList)request.getAttribute("AnnualList");
							if(userList!=null && userList.size()!=0){
								Iterator itUser = userList.iterator();
								while(itUser.hasNext()){
									String dispDate = "";
									HLCAnnualRegistrationDetailVO objAnnualRegDet = (HLCAnnualRegistrationDetailVO) itUser.next();
									String ardId = objAnnualRegDet.getArdId();
									String fName = objAnnualRegDet.getFirstName();
									String lName = objAnnualRegDet.getLastName();
									Date today = objAnnualRegDet.getAddDate();
									if(today!=null){
										dispDate = sdf.format(today);
									}
									String requestStatus = objAnnualRegDet.getRequestStatus();
									String registrarName = objAnnualRegDet.getRegistrarName();
									String annualMeetingId = objAnnualRegDet.getAnnualMeetingId();
									
							
							%>
							<form name="frmStatusChange" action="AnnualRegList.do">
							  <input type="hidden" name="memProcess" value="statusChange" />
							  <input type="hidden" name="ardId" value="<%=ardId%>" />
							  <input type="hidden" name="annualMeetingId" value="<%=annualMeetingId%>" />
                              <tr>
                                <td width="121" class="listCellBg"><span class="styleBoldOne"><%=fName%> &nbsp; <%=lName%></span></td>
                                <td width="81" class="listCellBg"><%=dispDate%></td>
                                <td width="77" class="listCellBg"><a href="AnnualRegList.do?annualMeetingId=<%=annualMeetingId%>&amp;memProcess=statusShow" class="linkFive" ><%=registrarName%></a></td>
                                <td width="55" class="listCellBg"><%=requestStatus%></td>
                                <td width="60" class="listCellBg">
								<span class="tblMainHead">
                                  <input name="btnSubmit" type="submit" class="twoBtn" value="Approve" />
                                </span></td>
                                <td width="63" class="listCellBg">
								<a href="AnnualRegList.do?ardId=<%=ardId%>&memProcess=detailedUserDetails"  class="linkFive" >View</a>								</td>
                              </tr>
							  </form>
							  <%
										}
										
									}
									else{
									%>
									<tr>
								  <th height="25" colspan="6" class="alignCenter">No Records are available. </th>
								</tr>
									<%
									}

							  %>

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
