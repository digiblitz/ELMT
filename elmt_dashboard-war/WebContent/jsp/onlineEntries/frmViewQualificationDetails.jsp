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
<%@ page import="com.hlcmrm.util.HLCDonationVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>

<script language="javascript" type="text/javascript">
function myValidate(){
	if(document.frmSelEvents.eventType.value==""){
		alert("Select an Event Type");
		document.frmSelEvents.eventType.focus();
		return false;
	}
	
	if(document.frmSelEvents.userType.value==""){
		alert("Select an User Type");
		document.frmSelEvents.userType.focus();
		return false;
	}

	if(document.frmSelEvents.selArea.value!="" && document.frmSelEvents.chmpStatus.value==""){		
		alert("Select a Championship Status");
		document.frmSelEvents.chmpStatus.focus();
		return false;			
	}		
	return true;
}
</script>

<link href="css/core-ie.css" rel="stylesheet" type="text/css" />
</head>

<body>

<%! 
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>

<%! 
String intCheck(int fieldName){
	String retValue = "";
		if(fieldName==0){
		retValue = "";
		}
	return retValue;
}

%>

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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
<!--			<td width="230" class="menuTablePad">
-->			<!-- LEFT MENU STARTS HERE -->
			<%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

<!--		    </td>
-->			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" width="100%" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="13" class="tblMainHead">
	Online Entries : <span class="styleBoldTwo">View Qualification Details</span></td>
  </tr>
  <%
		Vector eventTypeDetails = (Vector)request.getAttribute("eventTypeDetails");
		ArrayList userTypeDetails = (ArrayList)request.getAttribute("userTypeDetails");
		ArrayList areaList = (ArrayList)request.getAttribute("areaDetails");
		ArrayList divisionTypeDetails = (ArrayList)request.getAttribute("divisionTypeDetails");
		System.out.println("divisionTypeDetails in jsp: "+divisionTypeDetails);
		
		String eventTypeId = (String)request.getAttribute("eventTypeId");
		String userTypeId = (String)request.getAttribute("userTypeId");
		String divisionId = (String)request.getAttribute("divisionId");
		System.out.println("divisionId in jsp: "+divisionId);
		String areaId = (String)request.getAttribute("areaId");
		String chmpStat = (String)request.getAttribute("chmpStatus");
		
  %>
 <tr>
 	<td>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		 <form name="frmSelEvents" id="frmSelEvents" action="OEEValidateView.do" method="post" onsubmit="return myValidate();">
		 <input type="hidden" name="method" value="qualificationView" />
		 
		 <tr>
		 <td class="alignCenter" colspan="13">Event Type:&nbsp;
		 <select name="eventType" id="eventType" class="selectboxOne">
		 	<option value="" selected="selected">Select One</option>
			<%
				if(eventTypeDetails!=null && eventTypeDetails.size()!=0){
					Enumeration itr= (Enumeration)eventTypeDetails.elements();
					while(itr.hasMoreElements()){
						String[] s = (String[]) itr.nextElement();
						String typeId = s[0];
						String typeName = s[1];
						if(eventTypeId!=null && eventTypeId.equalsIgnoreCase(typeId)){
			%>
			<option value="<%=typeId%>" selected="selected" ><%=typeName%></option>
			<%}else{%>
			<option value="<%=typeId%>" ><%=typeName%></option>
			<%}}}%>
		 </select>&nbsp;<span class="asterisk">*</span>User Type:&nbsp;
		 <select name="userType" id="userType" class="selectboxOne">
		 	<option value="" selected="selected">Select One</option>
			<%
				if(userTypeDetails!=null && userTypeDetails.size()!=0){
					Iterator it = userTypeDetails.iterator();
					while(it.hasNext()){
						String [] uDet = (String[])it.next();
						String uTypeId = uDet[0];
						String uTypeName = uDet[1];
						if(userTypeId!=null && userTypeId.equalsIgnoreCase(uTypeId)){
			%>
			<option value="<%=uTypeId%>" selected="selected" ><%=uTypeName%></option>
			<%}else{%>
			<option value="<%=uTypeId%>" ><%=uTypeName%></option>
			<%}}}%>
		 </select><span class="asterisk">*</span>Division:&nbsp;&nbsp;
		 <select name="division" id="division" class="selectboxOne">
					 	<option value="" selected="selected">Select One</option>
						<%
							if(divisionTypeDetails!=null && divisionTypeDetails.size()!=0){
								Iterator ite = divisionTypeDetails.iterator();
									while(ite.hasNext()){
										String [] divDet = (String[])ite.next();
										String divId = divDet[0];
										String divName = divDet[1];
							if(divisionId.trim().equalsIgnoreCase(divId)){
						%>
						<option  value="<%=divId%>" selected="selected"><%=divName%></option>
							<%
											}
											else{
									%>
							<option  value="<%=divId%>"><%=divName%></option>
							<%

							}
							}
						}					
						%>   </select>&nbsp;Area:&nbsp;
		 <select name="selArea" id="selArea" class="selectboxOne">
		 	  <option selected="selected" value="">Select One</option>
						<%
		             if(areaList!=null&& areaList.size()!=0){
						Iterator areaLst = areaList.iterator();
						while(areaLst.hasNext()){
								String AreaLstArr[] = (String [])areaLst.next();
								String area_id = AreaLstArr[0];
								String area_code = AreaLstArr[1];
								String area_name = AreaLstArr[2];
								out.print("area_id "+area_id);
								if(areaId.trim().equalsIgnoreCase(area_id)){
						%>
							<option  value="<%=area_id%>" selected="selected"><%=area_name%></option>
							<%
											}
											else{
									%>
							<option  value="<%=area_id%>"><%=area_name%></option>
							<%

							}
							}
						}
					
						%>
                      </select>
		 &nbsp;Championship Status:&nbsp;
		 <select name="chmpStatus" id="chmpStatus" class="selectboxOne" >
			<option selected="selected" value="" >Select One</option>
		<%	
		String[] status = new String[]{"Yes","No"};
			for(int k =0; k<status.length; k++){
		if(chmpStat!=null && chmpStat.equalsIgnoreCase(status[k])){
		%>
		<option value="<%=status[k]%>" selected="selected" ><%=status[k]%></option>
		<%}else{%>
		<option value="<%=status[k]%>"><%=status[k]%></option>
			<%}}%>
		</select>&nbsp;&nbsp; 
		 <input name="submit" type="submit" value="Submit" class="gradBtn" />		 </td>
		 </tr>
		 </form>
		 
		 <tr>
			<td class="tblRowHeadTwo">Event Level</td>			
			<td class="tblRowHeadTwo">Rank </td>
			<td class="tblRowHeadTwo">Minimum Age</td>
			<td class="tblRowHeadTwo">Maximun Age</td>
			<td class="tblRowHeadTwo">Qual Period</td>
			<td class="tblRowHeadTwo">Minimum Rank</td>
			<td class="tblRowHeadTwo">Minimum Count</td>
			<td class="tblRowHeadTwo">Maximum Rank</td>
			<td class="tblRowHeadTwo">Penalty:Jump</td>
			<td class="tblRowHeadTwo">Penalty:Time</td>
			<td class="tblRowHeadTwo">Registration Level</td>
			<td class="tblRowHeadTwo">Amateur Status</td>
		   </tr>
		   <%
				HLCValidationVO valVO = new HLCValidationVO();
				boolean champStatus=false;
				boolean amaStatus=false;
				String chStatus="";
				String amStatus="";
				ArrayList areaChairList = (ArrayList)request.getAttribute("viewEventDetails");
				if(areaChairList!=null && areaChairList.size()!=0){
					Iterator itr = areaChairList.iterator();
					while(itr.hasNext()){
						valVO = (HLCValidationVO)itr.next();
						String eventLevelName = valVO.getEventLevelNames();
						champStatus = valVO.getChampStatus();
						if(champStatus==true){
						chStatus="Yes";
						}else{
						chStatus="No";
						}
						String divisionName = valVO.getDivisionName();
						String minRank = valVO.getMinRank();
						int minAge = valVO.getMinAge();
						int maxAge = valVO.getMaxAge();
						String qualPeriod = valVO.getQualicPeriod();
						String rank = valVO.getEveLevelRank();
						String maxRank = valVO.getMaxRank();
						int minCount = valVO.getMinCount();
						String jump = valVO.getMaxXcJmpenal();
						String time = valVO.getMaxXcTimepenal();
						String regLevel = valVO.getMembTypeName();
					    amaStatus = valVO.getAmateurStatus();
						if(amaStatus==true){
						amStatus="Yes";
						}else{
						amStatus="No";
						}
			%>
			<tr>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(eventLevelName)%></td>			
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(rank)%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=minAge%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=maxAge%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(qualPeriod)%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(minRank)%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=minCount%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(maxRank)%></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(jump)%></a></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(time)%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(regLevel)%></td>	
			<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(amStatus)%></td>	
			
		   </tr>
		   <%
			}%>
		   <tr>
			<td colspan="13" align="center">
			<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"/>
			<input type="button" name="process" value="Edit" class="gradBtn" onclick="location.href='OEEValidate.do?cmd=validDetails&eventType=<%=eventTypeId%>&userType=<%=userTypeId%>&division=<%=divisionId%>&selArea=<%=areaId%>&chmpStatus=<%=chmpStat%>'" />
			</td>
		  </tr>
			
			<%}else{
			%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="13" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <tr>
			<td colspan="13" align="center">
			<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"/>
			</td>
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
