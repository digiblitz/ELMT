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
<script language="javascript" type="text/javascript">
function postData(userType){
//alert("dfdf"+userType);
	document.frmSelEvents.method="post";
	document.frmSelEvents.action="./OEEValidate.do?cmd=regLevel&usrTyp="+userType;
	document.frmSelEvents.submit();	
	return true;
}	
</script>

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
<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
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
		
		<table  width="0" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			
	<td width="768" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="15" class="tblMainHead">
	Online Entries: <span class="styleBoldTwo">Insert Qualification Details</span></td>
  </tr>
  <%
		Vector eventTypeDetails = (Vector)request.getAttribute("eventTypeDetails");		
		ArrayList userTypeDetails = (ArrayList)request.getAttribute("userTypeDetails");
		System.out.println("userTypeDetails in jsp: "+userTypeDetails);		
		ArrayList areaList = (ArrayList)request.getAttribute("areaDetails");
		System.out.println("areaList in jsp: "+areaList);
		 ArrayList divisionTypeDetails = (ArrayList)request.getAttribute("divisionTypeDetails");
		System.out.println("divisionTypeDetails in jsp: "+divisionTypeDetails);
				
		String chmpStat = (String)request.getAttribute("chmpStat");
		System.out.println("chmpStat in jsp: "+chmpStat);
		String recExists = (String)request.getAttribute("recordExists");
		System.out.println("recExists in jsp: "+recExists);
		String eventTypeId = (String)request.getAttribute("eventTypeId");
		String userTypeId = (String)request.getAttribute("userTypeId");
		String areaId = (String)request.getAttribute("areaId");
		System.out.println("areaId in jsp: "+areaId);
		String divisionId = (String)request.getAttribute("divisionId");
		System.out.println("divisionId in jsp: "+divisionId);
		String insertStatus = (String)request.getAttribute("insertStatus");
		String iStatus = "";
	if(insertStatus!=null && insertStatus.equalsIgnoreCase("success")) iStatus = "Qualifications Details Inserted Successfully";
		else if(insertStatus!=null && insertStatus.equalsIgnoreCase("failed")) iStatus = "Qualifications Details Not Inserted";
  %>

 <tr>

 	<td>
		 <table  width="760" border="0" cellpadding="0" align="center" cellspacing="0" >
		 <%
		 	if(insertStatus!=null && insertStatus.trim().length()!=0){
		 %>
		 <tr>
		 <td class="styleError" colspan="12"><%=iStatus%></td>
		 </tr>
		 <%}%>
		 <form name="frmSelEvents" id="frmSelEvents" action="OEEValidate.do" method="post" onsubmit="return myValidate();">
		 <input type="hidden" name="cmd" value="selEventLevels" />
		 <tr>
		 <td class="alignCenter" colspan="12">Event Type:
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
		 </select>
		 &nbsp;&nbsp;<span class="asterisk">*</span>User Type
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
		 </select>&nbsp;&nbsp;<span class="asterisk">*</span> Division:&nbsp;&nbsp;
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
		 &nbsp;Championship Status:&nbsp;&nbsp;
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
	  <%
		 if(recExists!=null && recExists.equalsIgnoreCase("no")){%>
		  <tr>
			<td colspan="13" class="tblRowHeadTwo">Record Already Exists. <a class="linkFive" href="OEEValidate.do?cmd=validDetails&eventType=<%=eventTypeId%>&userType=<%=userTypeId%>&division=<%=divisionId%>&selArea=<%=areaId%>&chmpStatus=<%=chmpStat%>">Click to Edit</a></td>
		   </tr>
		 <%}	 
		 
	%>
	<%
		
		ArrayList membershipTypeDetails = (ArrayList)request.getAttribute("membershipTypeDetails");
		 ArrayList eventLevelDetails = (ArrayList)request.getAttribute("eventLevelDetails");
		System.out.println("eventLevelDetails in jsp: "+eventLevelDetails);		 
		 if(eventLevelDetails!=null && eventLevelDetails.size()!=0){
		 %>
		  <form name="frmInsertValidation" id="frmInsertValidation" action="OEEValidate.do" method="post" onsubmit="return insertValidate();">
		  <input type="hidden" name="cmd" value="insertDetails"  />
  		   <input type="hidden" name="eventSize" id="eventSize" value="<%=eventLevelDetails.size()%>" />
			<input type="hidden" name="inEventId" id="inEventId" value="<%=eventTypeId%>" />
			<input type="hidden" name="inUserId" id="inUserId" value="<%=userTypeId%>" />
			<input type="hidden" name="indivisionId" id="indivisionId" value="<%=divisionId%>" />
			<input type="hidden" name="inAreaId" id="inAreaId" value="<%=areaId%>" />
			<input type="hidden" name="inChmp" id="inChmp" value="<%=chmpStat%>" />
		  <tr>
			<td width="58" rowspan="2" class="tblRowHeadTwo">Event Level</td>
			<td width="58" rowspan="2" class="tblRowHeadTwo">Event Level Rank</td>
			<td width="42" rowspan="2" class="tblRowHeadTwo">Min. Age</td>
			<td width="45" rowspan="2" class="tblRowHeadTwo">Max. Age</td>
			<td width="55" rowspan="2" class="tblRowHeadTwo">Qual. Period</td>
			<td width="39" rowspan="2" class="tblRowHeadTwo">Min. Rank</td>
			<td width="55" rowspan="2" class="tblRowHeadTwo">Min. Count</td>
			<td width="49" rowspan="2" class="tblRowHeadTwo">Max Rank</td>
			<td colspan="2" height="15" class="tblRowHeadTwo">Penalties</td>
			<td width="100" rowspan="2" class="tblRowHeadTwo">Reg Level</td>
			<td width="56" rowspan="2" class="tblRowHeadTwo">Amateur Status</td>
		   </tr>
		  <tr>
		    <td width="48" height="19" class="tblRowHeadTwo">Jump</td>
		    <td width="55" class="tblRowHeadTwo">Time</td>
		  </tr>
			<%
			 	Iterator itl = eventLevelDetails.iterator();
				int i=0;
				while(itl.hasNext()){
					String [] levelDet = (String [])itl.next();
					String evId = levelDet[0];
					String evLevelId = levelDet[1];
					String evLevelCode = levelDet[2];
			%>
		  <tr>
			<td class="alignLeft"><input type="checkbox" name="tmpEventLevel<%=i%>" id="tmpEventLevel<%=i%>" value="<%=evLevelId%>" /><%=evLevelCode%></td>
			
			<input type="hidden" name="eventLevel<%=i%>" id="eventLevel<%=i%>" value="<%=evLevelId%>" />
					
	<td class="alignCenter"><input type="text" name="levelRank<%=i%>" id="levelRank<%=i%>" class="textboxOne" size="5" /></td>	
	<td class="alignCenter"><input type="text" name="minAge<%=i%>" id="minAge<%=i%>" class="textboxOne" size="5" /></td>
	<td class="alignCenter"><input type="text" name="maxAge<%=i%>" id="maxAge<%=i%>" class="textboxOne" size="5" /></td>	
	<td class="alignCenter"><input type="text" name="qPeriod<%=i%>" id="qPeriod<%=i%>" class="textboxOne" size="5" /></td>	
	<td class="alignCenter"><input type="text" name="minRank<%=i%>" id="minRank<%=i%>" class="textboxOne" size="5" /></td>
	<td class="alignCenter"><input type="text" name="minCount<%=i%>" id="minCount<%=i%>" class="textboxOne" size="5" /></td>
	<td class="alignCenter"><input type="text" name="maxRank<%=i%>" id="maxRank<%=i%>" class="textboxOne" size="5" /></td>
	<td class="alignCenter"><input type="text" name="jumpPenalties<%=i%>" id="jumpPenalties<%=i%>" class="textboxOne" size="5" /></td>
			<td class="alignCenter"><input type="text" name="timePenalties<%=i%>" id="timePenalties<%=i%>" class="textboxOne" size="5" /></td>
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
						%>
						<option value="<%=membId%>#<%=membName%>" ><%=membName%></option>
						<%}}%>
					</select>			</td>
			<td class="alignCenter"><input type="checkbox" name="amateurStatus<%=i%>" id="amateurStatus<%=i%>" value="yes" /></td>	
		   </tr>

		 
		   <%i++;}%>
				   <tr>
					<td colspan="12" align="center">
					<input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn"  />					</td>
				  </tr>
				  </form>
		   <%}else{%>
				   <tr>
					<td colspan="12" class="alignCenter" ><div align="center">No Records Found</div></td>
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
