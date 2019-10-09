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

function myValidate(){

	var eventLvlValue=document.getElementById('noChampSize').value;
	//alert(eventLvlValue);
	if(eventLvlValue!=0){
	var flag=true;
for(var k=1;k<=eventLvlValue;k++){
	var chName = "txtDue"+k;
	var afterdueDt = "txtAfterDue"+k;
	var depAmt = "txtDepAmt"+k;
		if(document.getElementById(chName).value!=""){
			flag=false;
		}
		if(document.getElementById(chName).value!=""){
		if(!Number(document.getElementById(chName).value)){
			alert("Enter Valid Due Date Price");
			document.getElementById(chName).focus();
return false;

		}
		}
		if(document.getElementById(chName).value!=""){
		if(document.getElementById(afterdueDt).value==""){
		alert("Enter After Due Date Price");
		document.getElementById(afterdueDt).focus();
return false;

		}
		}	
		if(document.getElementById(afterdueDt).value!=""){
		if(!Number(document.getElementById(afterdueDt).value)){
			alert("Enter Valid After Due Date Price");
			document.getElementById(afterdueDt).focus();
return false;

		}
		}
		
		if(document.getElementById(depAmt).value!=""){
		if(!Number(document.getElementById(depAmt).value)){
			alert("Enter Valid Deposit Amount");
			document.getElementById(depAmt).focus();
return false;

		}
		}
	}
if(flag){
alert("Enter Any one Due Date Price");
document.getElementById(chName).focus();
return false;
}
}

var champVal=document.getElementById('champSize').value;
	//alert(champVal);
	if(champVal!=0){
	var flag=true;
for(var k=1;k<=champVal;k++){
	var chName1 = "txtDueC"+k;
	var afterdueDt1 = "txtAfterDueC"+k;
	var depAmt1 = "txtDepAmtC"+k;
		if(document.getElementById(chName1).value!=""){
			flag=false;
		}
		if(document.getElementById(chName1).value!=""){
		if(!Number(document.getElementById(chName1).value)){
			alert("Enter Valid Due Date Price in Championship Details");
			document.getElementById(chName1).focus();
return false;

		}
		}
		if(document.getElementById(chName1).value!=""){
		if(document.getElementById(afterdueDt1).value==""){
			alert("Enter After Due Date Price in Championship Details");
			document.getElementById(afterdueDt1).focus();
return false;

		}
		}
		if(document.getElementById(afterdueDt1).value!=""){
		if(!Number(document.getElementById(afterdueDt1).value)){
			alert("Enter Valid After Due Date Price in Championship Details");
			document.getElementById(afterdueDt1).focus();
return false;

		}
		}
		
		if(document.getElementById(depAmt1).value!=""){
		if(!Number(document.getElementById(depAmt1).value)){
			alert("Enter Valid Deposit Amount in Championship Details");
			document.getElementById(depAmt1).focus();
return false;

		}
		}
	}
if(flag){
alert("Enter Any one Due Date Price in Championship Details");
document.getElementById(chName1).focus();
return false;
}

}
	return true;
}

</script>
<link href="css/core-ie.css" rel="stylesheet" type="text/css" />
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
	 Online Entries : <span class="styleBoldTwo">Insert Price Details</span></td>
  </tr>
  <%    
  		String eventTypeId = (String)request.getAttribute("eventTypeId");
		String itemID = (String)request.getAttribute("itemId");
		String areaID = (String)request.getAttribute("areaId");
		String enableButton = (String)request.getAttribute("enableButton");
		
		String insertStatus = (String)request.getAttribute("insertStatus");
		String existStatus = (String)request.getAttribute("AlreadyExist");
		String iStatus = "";
		String isStatus="";
	if(insertStatus!=null && insertStatus.equalsIgnoreCase("success")) iStatus = "Price Details Inserted Successfully";
		else if(insertStatus!=null && insertStatus.equalsIgnoreCase("failed")) iStatus = "Price Details Not Inserted";
	if(existStatus!=null && existStatus.equalsIgnoreCase("AlreadyExist")) isStatus="Price Details Already Exists.Try Again!";	
				
  %>
 <tr>
 	<td>
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		 <%
		 	if(insertStatus!=null && insertStatus.trim().length()!=0){
		 %>
		 <tr>
		 <td class="styleError" colspan="13"><%=iStatus%></td>
		 </tr>
		 <%}%> 
		 <%
		 	if(existStatus!=null && existStatus.trim().length()!=0){
		 %>
		 <tr>
		 <td class="styleError" colspan="13"><%=isStatus%></td>
		 </tr>
		 <%}%>
		 
		 <form name="frmInitPrice" id="frmInitPrice" action="OEEAddFixedPrice.do" method="post" onsubmit="return onValidate();">
		 <input type="hidden" name="cmd" value="initPriceDetails" />
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
		 </select>&nbsp;&nbsp;&nbsp;
		 <input type="submit" name="submit" value="Submit" class="oneBtn"/></td>
		 </tr>
		 </form>
		
		  <form name="frmInsertPrice" id="frmInsertPrice" action="OEEAddFixedPrice.do" method="post" onsubmit="return myValidate();">
		  <input type="hidden" name="cmd" value="insertPriceDetails"/>
			<input type="hidden" name="itemID" value="<%=itemID%>" />
			<input type="hidden" name="eventTypeId" value="<%=eventTypeId%>" />
			<input type="hidden" name="areaID" value="<%=areaID%>" />		 
		 
			<tr>
			<td class="tblRowHeadTwo">Event Level</td>
			<td class="tblRowHeadTwo">Due Date Price&nbsp;&nbsp;</td>
			<td class="tblRowHeadTwo">After-Due Date Price </td>
			<td class="tblRowHeadTwo">Deposit Amount</td>
			<!--<td class="tblRowHeadTwo">Type</td>-->
			</tr>
						<%
						
						ArrayList eventLevel = (ArrayList)request.getAttribute("eventLevelByType");
						int j=0;
						if(eventLevel!=null && eventLevel.size()!=0){
						Iterator it = eventLevel.iterator();
						
						while(it.hasNext()){
							String [] Det = (String[])it.next();
							String eventTypeId1 = Det[0];
							String eventLevelId = Det[1];
							String eventLevelName =Det[2];
							
							j++;
							%>
<input type="hidden" name="noChampSize" id="noChampSize" value="<%=eventLevel.size()%>"/>
<input type="hidden" name="eventLevelId<%=j%>" id="eventLevelId<%=j%>" value="<%=eventLevelId%>"/>
		<tr>
<td class="alignCenter"><%=eventLevelName%></td>
			
<td class="alignCenter"><input type="text" name="txtDue<%=j%>" id="txtDue<%=j%>" class="textboxOne" size="20"  /></td>

<td class="alignCenter"><input type="text" name="txtAfterDue<%=j%>" id="txtAfterDue<%=j%>" class="textboxOne" size="20"  /></td>
<td class="alignCenter"><input type="text" name="txtDepAmt<%=j%>" id="txtDepAmt<%=j%>" class="textboxOne" size="20" /></td>
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
				ArrayList eventLevelChamp = (ArrayList)request.getAttribute("eventLevelByArea");
				int m=0;
						if(eventLevelChamp!=null && eventLevelChamp.size()!=0){
						    Iterator it = eventLevelChamp.iterator();
							while(it.hasNext()){
							String [] Det = (String[])it.next();
							String areaId = Det[0];
							String CeventLevelId = Det[1];
							String CeventLevelName =Det[2];
							m++;
				%>
 <input type="hidden" name="champSize" id="champSize" value="<%=eventLevelChamp.size()%>"/>
 <input type="hidden" name="CeventLevelId<%=m%>" id="CeventLevelId<%=m%>" value="<%=CeventLevelId%>"/>
 <%System.out.println("CeventLevelId"+CeventLevelId);%> 
 <tr>

<td class="alignCenter"><%=CeventLevelName%></td>
			
<td class="alignCenter"><input type="text" name="txtDueC<%=m%>" id="txtDueC<%=m%>" class="textboxOne" size="20"  /></td>
	
<td class="alignCenter"><input type="text" name="txtAfterDueC<%=m%>" id="txtAfterDueC<%=m%>" class="textboxOne" size="20"  /></td>	

<td class="alignCenter"><input type="text" name="txtDepAmtC<%=m%>" id="txtDepAmtC<%=m%>" class="textboxOne" size="20"  /></td>	
		   </tr>
		  <%}}else{%>
	  		<tr>
			<td height="19" bgcolor="#E3E1D2" colspan="8" align="center"><strong>No Records Found</strong></td>
           </tr>
		   <%}%>
		   <%if(enableButton!=null){%>
				     <tr>
					<td colspan="12" align="center">
					<input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn"  />	</td>
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