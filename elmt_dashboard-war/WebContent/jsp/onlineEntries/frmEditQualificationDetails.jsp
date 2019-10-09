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
<script src="javascripts/frmInsertQualificationDetails.js" type="text/javascript" ></script>
<script language="javascript">
function postData(userType){
	document.frmSelEvents.method="post";
	document.frmSelEvents.action="./OEEValidate.do?cmd=editRegLevel&usrTyp="+userType;
	document.frmSelEvents.submit();
	return true;
}

function myValidate(){
	if(document.frmSelEvents.eventType.value==""){
		alert("Select an Event Type");
		document.frmSelEvents.eventType.focus();
		return false;
	}
	if(document.frmSelEvents.userType.value==""){
		alert("Select a User Type");
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
String nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>

<%! 
	String zeroCheck(int fieldName){
		String zeroValue = "";
		if(fieldName!=0){
			zeroValue = ""+fieldName;
		}
	return zeroValue;
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
	Online Entries : <span class="styleBoldTwo">Edit Qualification Details</span></td>
  </tr>
  <%
		Vector eventTypeDetails = (Vector)request.getAttribute("eventTypeDetails");
		ArrayList userTypeDetails = (ArrayList)request.getAttribute("userTypeDetails");
		ArrayList divisionTypeDetails = (ArrayList)request.getAttribute("divisionTypeDetails");
		ArrayList membershipTypeDetails = (ArrayList)request.getAttribute("membershipTypeDetails");
		ArrayList areaList = (ArrayList)request.getAttribute("areaDetails");
		
		String chmpStat = (String)request.getAttribute("chmpStat1");
		String eventTypeId = (String)request.getAttribute("eventTypeId");
		String userTypeId = (String)request.getAttribute("userTypeId");
		String areaId = (String)request.getAttribute("areaId");
		String divisionId = (String)request.getAttribute("divisionId");
		
		String updateStatus = (String)request.getAttribute("updateStatus");
		String iStatus = "";
	if(updateStatus!=null && updateStatus.equalsIgnoreCase("success")) iStatus = "Qualifications Details Updated Successfully";
		else if(updateStatus!=null && updateStatus.equalsIgnoreCase("failed")) iStatus = "Qualifications Details Not Updated";
		
  %>
 <tr>

 	<td>
		 <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
		 <%
		 	if(updateStatus!=null && updateStatus.trim().length()!=0){
		 %>
		 <tr>
		 <td class="styleError" colspan="12"><%=iStatus%></td>
		 </tr>
		 <%}%> 
		 
		 
		 <form name="frmSelEvents" id="frmSelEvents" action="OEEValidate.do" method="post" onsubmit="return myValidate();">
		 <input type="hidden" name="cmd" value="validDetails" />
		 
		 <tr>
		 <td class="alignCenter" colspan="12">Event Type:&nbsp;&nbsp;
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
		 </select>&nbsp;&nbsp;<span class="asterisk">&nbsp;*</span> User Type:&nbsp;&nbsp;
		 <select name="userType" id="userType" class="selectboxOne" onchange="postData(this.value);">
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
		 </select>&nbsp;&nbsp;<span class="asterisk">&nbsp;*</span>Division:&nbsp;&nbsp;
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
						%>   </select>Area:&nbsp;&nbsp;
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
		 &nbsp; &nbsp; Championship Status:&nbsp;&nbsp;
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
		
		  <form name="frmUpdateValidation" id="frmUpdateValidation" action="OEEValidate.do" method="post" onsubmit="return editValidate();">
		  <input type="hidden" name="cmd" value="update"/>
  		  
			<input type="hidden" name="inEventId" id="inEventId" value="<%=eventTypeId%>" />
			<input type="hidden" name="inUserId" id="inUserId" value="<%=userTypeId%>" />
			<input type="hidden" name="indivisionId" id="indivisionId" value="<%=divisionId%>" />
			<input type="hidden" name="inAreaId" id="inAreaId" value="<%=areaId%>" />
			<input type="hidden" name="inChmp" id="inChmp" value="<%=chmpStat%>" />
			
		 <tr>
			<td class="tblRowHeadTwo">Event Level</td>			
			<td class="tblRowHeadTwo">Event Level Rank </td>
			<td class="tblRowHeadTwo">Min. Age</td>
			<td class="tblRowHeadTwo">Max. Age</td>
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
				
				boolean amaStatus=false;
				
				String amStatus="";
				ArrayList validList = (ArrayList)request.getAttribute("viewValidationDetails");
	if(validList!=null && validList.size()!=0){

	%>
	 <input type="hidden" name="qualiSize" id="qualiSize" value="<%=validList.size()%>"/>
		   <%
				Iterator itr = validList.iterator();
					int i=0;
					while(itr.hasNext()){
						valVO = (HLCValidationVO)itr.next();
						String qualificationId=valVO.getQualificationId();
						String eventLevelName = valVO.getEventLevelNames();						
						boolean champStatus = valVO.getChampStatus();
						String divisionName = valVO.getDivisionName();						
						String eventLevelId = valVO.getEventLevelId();
						String minRank = valVO.getMinRank();
						int minAge = valVO.getMinAge();
						int maxAge = valVO.getMaxAge();
						String qualPeriod = valVO.getQualicPeriod();
						String rank = valVO.getEveLevelRank();
						String maxRank = valVO.getMaxRank();
						int minCount = valVO.getMinCount();
						String jump = valVO.getMaxXcJmpenal();
						String time = valVO.getMaxXcTimepenal();
						String membName1 = valVO.getMembTypeName();
						String membTypId = valVO.getMemTypId();
					    amaStatus = valVO.getAmateurStatus();
						if(amaStatus==true){
						amStatus="checked";
						}else{
						amStatus="";
						}
						String forCheckBox = "";
						if((rank!=null && rank.trim().length()!=0)||(minAge!=0)||(maxAge!=0)||(qualPeriod!=null && qualPeriod.trim().length()!=0)||
							(minRank!=null && minRank.trim().length()!=0)||(minCount!=0)||(jump!=null && jump.trim().length()!=0)||
							(time!=null && time.trim().length()!=0)||(membTypId!=null && membTypId.trim().length()!=0)||(amStatus.equalsIgnoreCase("checked"))){
							forCheckBox = "checked";
						}else{
							forCheckBox = "";
						}
			%>
			<input type="hidden" name="qualificationId<%=i%>" id="qualificationId<%=i%>" value="<%=qualificationId%>"/> 
			<tr>
			<td ><input type="checkbox" <%=forCheckBox%> name="eventLevelId<%=i%>" id="eventLevelId<%=i%>" value="<%=eventLevelId%>"/><%=eventLevelName%></td>
			
<td class="alignCenter"><input type="text" name="levelRank<%=i%>" id="levelRank<%=i%>" class="textboxOne" size="5" value="<%=nullCheck(rank)%>" /></td>
<td class="alignCenter"><input type="text" name="minAge<%=i%>" id="minAge<%=i%>" class="textboxOne" size="5" value="<%=zeroCheck(minAge)%>" /></td>
<td class="alignCenter"><input type="text" name="maxAge<%=i%>" id="maxAge<%=i%>" class="textboxOne" size="5" value="<%=zeroCheck(maxAge)%>" /></td>
<td class="alignCenter"><input type="text" name="qPeriod<%=i%>" id="qPeriod<%=i%>" class="textboxOne" size="5" value="<%=nullCheck(qualPeriod)%>" /></td>
<td class="alignCenter"><input type="text" name="minRank<%=i%>" id="minRank<%=i%>" class="textboxOne" size="5" value="<%=nullCheck(minRank)%>" /></td>
<td class="alignCenter"><input type="text" name="minCount<%=i%>" id="minCount<%=i%>" class="textboxOne" size="5" value="<%=zeroCheck(minCount)%>" /></td>
<td class="alignCenter"><input type="text" name="maxRank<%=i%>" id="maxRank<%=i%>" class="textboxOne" size="5" value="<%=nullCheck(maxRank)%>" /></td>
<td class="alignCenter"><input type="text" name="jumpPenalties<%=i%>" id="jumpPenalties<%=i%>" class="textboxOne" size="5" value="<%=nullCheck(jump)%>" /></td>	
<td class="alignCenter"><input type="text" name="timePenalties<%=i%>" id="timePenalties<%=i%>" class="textboxOne" size="5" value="<%=nullCheck(time)%>" /></td>	
			<td class="alignCenter">
			<select name="membershipLevel<%=i%>" id="membershipLevel<%=i%>" class="selectboxOne">
					 	<option value="" selected="selected">Select One</option>
						<%
							if(membershipTypeDetails!=null && membershipTypeDetails.size()!=0){
								Iterator iter = membershipTypeDetails.iterator();
								while(iter.hasNext()){
									String [] membDet = (String[])iter.next();
										String membId = membDet[0];
										String membName = membDet[1];
				if(membTypId!=null && membTypId.equalsIgnoreCase(membId)){
			%>
			<option value="<%=membId%>#<%=membName%>" selected="selected" ><%=membName%></option>
			<%}else{%>
			<option value="<%=membId%>#<%=membName%>" ><%=membName%></option>
			<%}}}%>
		 </select>
			<td class="alignCenter"><input type="checkbox" <%=amStatus%> name="amateurStatus<%=i%>" id="amateurStatus<%=i%>" value="<%=amaStatus%>"/></td>
		   </tr>
   
		   <%
		   i++;		  
		   }
			}else{%>
			<tr>
<th height="25" colspan="12"><center>No records are available</center> </th>
						</tr>
						  
						  <%
						  }
						  %>

				     <tr>
					<td colspan="12" align="center">
					<input type="submit" name="appButton" id="appButton" value="Update" class="gradBtn"  />	</td>
				  </tr>
				   </form>
				   
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
