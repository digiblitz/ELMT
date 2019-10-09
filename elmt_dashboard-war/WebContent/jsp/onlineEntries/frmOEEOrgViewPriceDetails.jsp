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

<script language="javascript" type="text/javascript">
function myValidate(){
	if(document.frmInitPrice.selItem.value==""){
		alert("Select an Item");
		document.frmInitPrice.selItem.focus();
		return false;
	}
	return true;
}

</script>
</head>

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
<%  String itemID =(String)request.getAttribute("itemId");
	String eventId =(String)request.getAttribute("eventId");
	
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
		
		 <form name="frmInitPrice" id="frmInitPrice" action="OEEAddPrice.do" method="post" onsubmit="return myValidate();">
		 <input type="hidden" name="cmd" value="orgViewPrice" />
		 <input type="hidden" name="eventId" value="<%=eventId%>" />
		 <tr>
		 <td colspan="12">
		 Item Name:
		 <select name="selItem" id="selItem" class="selectboxOne">
		 	<option value="" selected="selected">Select One</option>
			<%  ArrayList itemDetails = (ArrayList)request.getAttribute("itemDetails");
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
		 </select>
		
		 <input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn" />
		 
		 
		 <a href="OEEAddPrice.do?cmd=initNewItem&eventId=<%=eventId%>" class="linkFive"> Create New Item!</a>		
		 
		 		 </td>
		 
		 </tr> 
		 </form>
		
		  <form name="frmViewPrice" id="frmViewPrice" action="OEEAddPrice.do" method="post" >
		  <input type="hidden" name="cmd" value="initOrgEditPrice"/>
		  <input type="hidden" name="eventId" value="<%=eventId%>" />
		  <%String itemId =(String)request.getAttribute("itemId");%>
		  <input type="hidden" name="itemId" value="<%=itemId%>" />
		 
			
			<tr>
			<td class="tblRowHeadTwo">Event Level</td>
			<td class="tblRowHeadTwo">Due Date Price&nbsp;&nbsp;</td>
			<td class="tblRowHeadTwo">After-Due Date Price </td>
			<td class="tblRowHeadTwo">Deposit Amount</td>
			<td class="tblRowHeadTwo">Area Chair  Status</td>
			
			</tr>
			<%
			
			ArrayList eventType = (ArrayList)request.getAttribute("eventType");
			if(eventType!=null && eventType.size()!=0){

			
			%>
		   <%   
				Iterator itr = eventType.iterator();
						while(itr.hasNext()){
						String [] uDet = (String[])itr.next();
						String eventTyId = uDet[0];
						String eventTypeName =uDet[1];
						
						%>
					<tr>
					<td  colspan="14"class="tblMainHead"><%=eventTypeName%></td>
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
						if(eventTyId1.equals(eventTyId)){
						
			%>
<tr>
<td bgcolor="#E3E1D2" class="alignCenter"><%=eventLevelName%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=duePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=afterDuePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=depAmt%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=appStatus%></td>
	
 </tr>
   
		   <%}}}}}else{%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>
	
	<tr>
	<td  colspan="14"class="tblMainHead"><strong>Championship Details :</strong></td>
	</tr>
	
		 <tr>
			<td class="tblRowHeadTwo">Event Level</td>
			<td class="tblRowHeadTwo">Due Date Price&nbsp;&nbsp;</td>
			<td class="tblRowHeadTwo">After-Due Date Price </td>
			<td class="tblRowHeadTwo">Deposit Amount</td>
			<td class="tblRowHeadTwo">Area Chair Status</td>
		   </tr>
		   
		   <%
		    
			ArrayList eventTypeChamp = (ArrayList)request.getAttribute("eventTypeChamp");
			if(eventTypeChamp!=null && eventTypeChamp.size()!=0){
			%>
			<%
				Iterator itr = eventTypeChamp.iterator();
				
				while(itr.hasNext()){
					String [] uDet = (String[])itr.next();
					String eventTyId = uDet[0];
					String eventTypeName =uDet[1];
				
				%>
				<tr>
				<td  colspan="14"class="tblMainHead"><%=eventTypeName%></td>
				</tr>
				<%
				ArrayList eventLevelChamp = (ArrayList)request.getAttribute("champDetails");
				HLCPriceMatrixVO priceVO = new HLCPriceMatrixVO();
						if(eventLevelChamp!=null && eventLevelChamp.size()!=0){
						Iterator it = eventLevelChamp.iterator();
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
						if(eventTyId1.equals(eventTyId)){
						
			%>

<tr>
<td bgcolor="#E3E1D2" class="alignCenter"><%=eventLevelName%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=duePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=afterDuePrice%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=depAmt%></td>
<td bgcolor="#E3E1D2" class="alignCenter"><%=appStatus%></td>
	
 </tr>
		  <%}}}}}else{%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>	   
		       <%
					if(eventType!=null && eventType.size()!=0 || eventTypeChamp!=null && eventTypeChamp.size()!=0){
					%>
				     <tr>
					<td colspan="12" align="center">
					<input type="submit" name="appButton" id="appButton" value="Update Price" class="gradBtn"  />	
					
					</td>
				  </tr>
				  <%}else if(eventType!=null && eventType.size()==0 || eventTypeChamp!=null && eventTypeChamp.size()==0){
				  
				  %>
				    <tr>
					<td colspan="12" align="center">
				  <input type="button" name="appButton" id="appButton" value="Set New Price" class="gradBtn"  onclick="location.href='OEEAddPrice.do?cmd=initPrice&eventId=<%=eventId%>&itemId=<%=itemId%>'" />
				  
				  	</td>
				  </tr>
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
