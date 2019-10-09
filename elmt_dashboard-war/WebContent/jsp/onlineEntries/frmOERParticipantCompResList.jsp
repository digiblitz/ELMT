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
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.*"%>
<%@ page import="com.hlcutil.HLCCompResultVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript" type="text/javascript">
function onValidate(){
	if(document.myform.year.value==""){
		alert("Select a Year");
		document.myform.year.focus();
		return false;
	}
	if(document.myform.selTitle.value==""){
		alert("Select Event Title");
		document.myform.selTitle.focus();
		return false;
	}
	
	return true;
}
function postData(year){
location.href="ParticipantResult.do?cmd=eveTitleList&year="+year;

}	

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
  <tr><h1></h1>
    <td class="tableCommonBg">
		
		<table  width="0" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
 			
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Online Entries : <span class="styleBoldTwo">Participant Result List</span></td>
  </tr>
  
  <%
	java.util.Date orgDate = new java.util.Date();
	int currentYear = 1900+orgDate.getYear();
	String yearStr = (String)request.getAttribute("year");	
	System.out.println("yearStr : "+yearStr);
	String eventId=(String)request.getAttribute("tempeventId");	
	System.out.println("eventId in jsp  : "+eventId);
	String eveTypeId=(String)request.getAttribute("eveTypeId");	
	int selectedYear = 0;
	if(yearStr!=null && yearStr.trim().length()!=0){
		selectedYear = Integer.parseInt(yearStr);
	}
		
%>	
 <tr>
 	<td>
		  <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		
		<form name="myform" id="myform" action="ParticipantResult.do" method="post" onsubmit="return onValidate();">
		<input type="hidden" name="cmd" value="resultList" />
		<%--<input type="hidden" name="initEventId" value="<%=//eventId%>" />
		<input type="hidden" name="initYear" value="<%=//yearStr%>" />	--%>
		
		<tr>
		<td colspan="12" align="center"><strong>Year:</strong>
						<select name="year" id="year" class="selectboxOne" onchange="postData(this.value);" >
							<option selected="selected" value="" >Select One</option>
							<%
							for(int i=2000; i<(currentYear+2); i++){
							if(selectedYear!=0 && selectedYear==i){
							%>
							<option value="<%=i%>" selected="selected"><%=i%></option>
							<%}else{%>
							<option value="<%=i%>"><%=i%></option>
							<%}}%>
						</select><span class="asterisk">&nbsp;*</span>
		<strong>Event Title:</strong> 
						<select name="selTitle" id="selTitle" class="selectboxOne">
							<option selected="selected" value="">Select One</option>
							<%
							ArrayList myCompRegDetails = (ArrayList)request.getAttribute("myCompRegDetails");
							if(myCompRegDetails!=null&& myCompRegDetails.size()!=0){
							Iterator list = myCompRegDetails.iterator();
							while(list.hasNext()){							
							HLCCompResultVO objCalVO = (HLCCompResultVO) list.next();
							String eventId1 = objCalVO.getEventId();
							String eventTitle1 = objCalVO.getEventTitle();
							String eveTypName1 = objCalVO.getEventTypeName();
							
							if(eventId1!=null && eventId1.equalsIgnoreCase(eventId)){
			%>
			<option value="<%=eventId1%>" selected="selected" ><%=eventTitle1%></option>
			<%}else{%>
			<option value="<%=eventId1%>" ><%=eventTitle1%></option>
			<%}}}%>
							
						</select><span class="asterisk">&nbsp;*</span>	
		<input type="submit" name="submit" value="Submit" class="oneBtn"/>
		    </td>
			</tr>
			</form>
		
	  <tr>
			
			<td class="tblRowHeadTwo">Event Id </td>
			<td class="tblRowHeadTwo">Event Type </td>
			<td class="tblRowHeadTwo">Event Title </td>
			<td class="tblRowHeadTwo">HorseId</td>
			<td class="tblRowHeadTwo">Horse Name</td>
			<td class="tblRowHeadTwo">View</td>					
		   </tr>
		<%
				HLCCompResultVO compVO = new HLCCompResultVO();
				ArrayList eventcompResList = (ArrayList)request.getAttribute("eventcompResList");
				if(eventcompResList!=null && eventcompResList.size()!=0){
					Iterator itr = eventcompResList.iterator();
					while(itr.hasNext()){
						compVO = (HLCCompResultVO)itr.next();
						String compResultId = compVO.getCompResultId();
						System.out.println("compResultId in jsp"+compResultId);
						String eveId = compVO.getEventId();
						String eveType = compVO.getEventTypeName();
						String eveTitle = compVO.getEventTitle();
						String riderMemId = compVO.getRiderMemberId();
						String rFName = compVO.getRiderFirstName();
						String rLName = compVO.getRiderLastName();
						String horseId = compVO.getHorseMemberId();
						String hName = compVO.getHorseName();
													
			%>

	 <tr>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=eveId%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=eveType%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=eveTitle%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=horseId%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=hName%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="process" value="View" class="oneBtn" onclick="location.href='ParticipantResult.do?cmd=myCompResultView&compResultId=<%=compResultId%>'" /></td>			
		   </tr>
			<%
				}
			}else{
			%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>
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
