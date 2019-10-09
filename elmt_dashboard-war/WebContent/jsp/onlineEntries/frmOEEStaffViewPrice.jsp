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

<link href="css/core-ie.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
function onValidate(){

	if(document.frmInitPrice.selItem.value==""){
		alert("Select a Item");
		document.frmInitPrice.selItem.focus();
		return false;
	}
	if(document.frmInitPrice.selType.value==""){
		alert("Select a Event Type");
		document.frmInitPrice.selType.focus();
		return false;
	}
	return true;
}
</script>
<body>

<%! 
String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%  	String eventTypeId = (String)request.getAttribute("eventTypeId");
		String itemID = (String)request.getAttribute("itemId");
		String areaID = (String)request.getAttribute("areaId");
		String enableButton = (String)request.getAttribute("enableButton");
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
			<td width="230" class="menuTablePad">
		<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" width="100%" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="13" class="tblMainHead">
	Online Entries : <span class="styleBoldTwo">View Price Details</span></td>
  </tr>
 
 <tr>
 	<td>
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		
		 <form name="frmInitPrice" id="frmInitPrice" action="OEEAddFixedPrice.do" method="post" onsubmit="return onValidate();">
		 <input type="hidden" name="cmd" value="staffViewPrice" />
		
		 <tr>
		 <td colspan="12">
		 Item Name:
		 <select name="selItem" id="selItem" class="selectboxOne">
		 	<option value="" selected="selected">Select One</option>
			<%	ArrayList itemDetails = (ArrayList)request.getAttribute("allItem");
				if(itemDetails!=null && itemDetails.size()!=0){
					Iterator itr= itemDetails.iterator();
					while(itr.hasNext()){
						String[] s = (String[]) itr.next();
						String itemId = s[0];
						String itemName1 = s[1];
						if(itemID!=null && itemID.equalsIgnoreCase(itemId)){
			%>
			<option value="<%=itemId%>" selected="selected" ><%=itemName1%></option>
			<%}else{%>
			<option value="<%=itemId%>" ><%=itemName1%></option>
			<%}}}%>
		 </select><span class="asterisk">*</span>
		Event Type:
		 <select name="selType" id="selType" class="selectboxOne">
		 	<option value="" selected="selected">Select One</option>
			<%  Vector eventTypeDetails = (Vector)request.getAttribute("allEventType"); 
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
		 </select><span class="asterisk">*</span>
		 <br/><br/>Area:
		 <select name="selArea" id="selArea" class="selectboxOne">
		 	<option value="" selected="selected">Select One</option>
			<%  ArrayList areaDetails = (ArrayList)request.getAttribute("allArea"); 
				if(areaDetails!=null && areaDetails.size()!=0){
					Iterator itr= areaDetails.iterator();
					while(itr.hasNext()){
						String[] s = (String[]) itr.next();
						String areaId = s[0];
						String areaName = s[2];
						if(areaID!=null && areaID.equalsIgnoreCase(areaId)){
			%>
			<option value="<%=areaId%>" selected="selected" ><%=areaName%></option>
			<%}else{%>
			<option value="<%=areaId%>" ><%=areaName%></option>
			<%}}}%>
		 </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 <input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn"  />		 </td>
		 
		 </tr> 
		 </form>
		
		  <form name="frmViewPrice" id="frmViewPrice" action="OEEAddFixedPrice.do" method="post" onsubmit="return onValidate();">
		  <input type="hidden" name="cmd" value="initEditPrice"/>
		<input type="hidden" name="itemID" value="<%=itemID%>" />
		<input type="hidden" name="eventTypeId" value="<%=eventTypeId%>" />
		<input type="hidden" name="areaID" value="<%=areaID%>" />	
		 
			
			<tr>
			<td class="tblRowHeadTwo">Event Level</td>
			<td class="tblRowHeadTwo">Due Date Price&nbsp;&nbsp;</td>
			<td class="tblRowHeadTwo">After-Due Date Price </td>
			<td class="tblRowHeadTwo">Deposit Amount</td>
			
			</tr>
			     		<%

						ArrayList eventLevel = (ArrayList)request.getAttribute("HLCEventDetails");
						HLCPriceMatrixVO priceVO = new HLCPriceMatrixVO();
						if(eventLevel!=null && eventLevel.size()!=0){
						Iterator it = eventLevel.iterator();
						
						while(it.hasNext()){
							priceVO = (HLCPriceMatrixVO)it.next();
							String eventTyId1 = priceVO.getEventTypeId();
							String eventTypeName1 =priceVO.getEventTypeName();
							String eventLeId = priceVO.getEventLevelId();
							String eventLevelName =priceVO.getEventLevelName();
							String duePrice=String.valueOf(priceVO.getDueDatePrice()); 
							String afterDuePrice=String.valueOf(priceVO.getAfterDueDatePrice()); 
							String depAmt=String.valueOf(priceVO.getDepositPrice()); 
							String appStatus=priceVO.getApproveStatus();
					
			%>
<tr>
<td bgcolor="#E3E1D2" class="alignCenter"><%=eventLevelName%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=duePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=afterDuePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=depAmt%></td>
	
 </tr>
   
		   <%}}else{%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>
	
	<tr>
	<td  colspan="14"class="tblMainHead">Championship Details :</td>
	</tr>
	
		 <tr>
			<td class="tblRowHeadTwo">Event Level</td>
			<td class="tblRowHeadTwo">Due Date Price&nbsp;&nbsp;</td>
			<td class="tblRowHeadTwo">After-Due Date Price </td>
			<td class="tblRowHeadTwo">Deposit Amount</td>
			
		   </tr>
		   
		 		<%
				ArrayList eventLevelChamp = (ArrayList)request.getAttribute("eventDetailsChamp");
				HLCPriceMatrixVO priceVO1 = new HLCPriceMatrixVO();
						if(eventLevelChamp!=null && eventLevelChamp.size()!=0){
						Iterator it = eventLevelChamp.iterator();
							while(it.hasNext()){
							priceVO1 = (HLCPriceMatrixVO)it.next();
							String areaId=priceVO1.getAreaId();
							String eventTyId1 = priceVO1.getEventTypeId();
							String eventTypeName1 =priceVO1.getEventTypeName();
							String eventLeId = priceVO1.getEventLevelId();
							String eventLevelName =priceVO1.getEventLevelName();
							String duePrice=String.valueOf(priceVO1.getDueDatePrice()); 
							String afterDuePrice=String.valueOf(priceVO1.getAfterDueDatePrice()); 
							String depAmt=String.valueOf(priceVO1.getDepositPrice()); 
						    String appStatus=priceVO1.getApproveStatus();
			%>

<tr>
<td bgcolor="#E3E1D2" class="alignCenter"><%=eventLevelName%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=duePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=afterDuePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=depAmt%></td>

 </tr>
		  <%}}else{%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>
				    <%
					if(eventLevel!=null && eventLevel.size()!=0 || eventLevelChamp!=null && eventLevelChamp.size()!=0){
					%>
				     <tr>
					<td colspan="12" align="center">
					<input type="submit" name="appButton" id="appButton" value="Update Price" class="gradBtn"  />	
					
					</td>
				  </tr>
				  <%}else if(eventLevel!=null && eventLevel.size()==0 || eventLevelChamp!=null && eventLevelChamp.size()==0){
				  
				  %>
				   
				  <%}%>
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
