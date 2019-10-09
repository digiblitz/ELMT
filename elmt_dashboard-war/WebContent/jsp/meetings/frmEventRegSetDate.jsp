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
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
function postData(season){
if(document.myform.selSeason.selectedIndex==0){
    alert("Select any one Season");
	document.myform.selSeason.focus();
	return false;
	}else{
var year=document.getElementById("selYear").value
location.href="eventRegPriceCRUD.do?cmd=dateDetails&season="+season+"&year="+year;
}
}

function myValidate(){
if(document.myform.selYear.selectedIndex==0){
	alert("Select any Year");
	document.myform.selYear.focus();
	return false;
}
if(document.myform.selSeason.value==""){
	alert("Select any one Season");
	document.myform.selSeason.focus();
	return false;
}
if(document.myform.txtDue.value==""){
	alert("Enter Due Date");
	document.myform.txtDue.focus();
	return false;
}
	if(document.myform.txtGrace.value==""){
	alert("Enter Grace Date");
	document.myform.txtGrace.focus();
	return false;
}
var stDate = document.myform.txtDue.value;
var enDate = document.myform.txtGrace.value;
var sDate = new Date();

sDate.setMonth(stDate.substring(0,2)-1);
sDate.setDate(stDate.substring(3,5));
sDate.setYear(stDate.substring(6,10));

var eDate = new Date();
eDate.setMonth(enDate.substring(0,2)-1);
eDate.setDate(enDate.substring(3,5));
eDate.setYear(enDate.substring(6,10));

var stTime = sDate.getTime();
var enTime = eDate.getTime();

var year=document.myform.selYear.value;
var year1=sDate.getYear(stDate.substring(6,10));

if(year!=stDate.substring(6,10)){
alert("Select valid Event Due Date");
document.myform.txtDue.focus();
return false;
}
if(year!=enDate.substring(6,10)){
alert("Select valid Event Grace Date");
document.myform.txtGrace.focus();
return false;
}
if(stTime>enTime){
	alert("Select valid Event Due Date & Grace Date");
	document.myform.txtDue.focus();
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
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%! String dateCheck(java.util.Date fieldName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
		String detValue = "";
        if(fieldName!=null){
            detValue = sdf1.format(fieldName);
        }
        return detValue;
    }
    %>
<%String seasonId=(String)request.getAttribute("issueId");%>
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
			<td width="230" height="119" class="menuTablePad">
		<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table  border="0" width="100%" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="13" class="tblMainHead">
	Meetings : <span class="styleBoldTwo">Insert Date Details</span></td>
  </tr>
  
 <tr>
 	<td>
		 <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
		  
		 <form name="myform" id="myform" action="eventRegPriceCRUD.do" method="post" onsubmit="return myValidate(this);">
		 <input type="hidden" name="cmd" value="insertDateDetails"/>
		 
		             <tr>
			<td class="tableLeft">Competition Year:</td>					
			<td class="tableRight">
			<select name="selYear" id="selYear" class="selectboxOne" >
			<option selected="selected" value="Select One" >Select One</option>		
			<% String yearStr = (String) request.getAttribute("year");
			Calendar cal = Calendar.getInstance();
			for (int i= 2007;i<=(cal.get(Calendar.YEAR)+1);i++){
			if(yearStr != null && yearStr.equalsIgnoreCase(new Integer(i).toString())){
			%>
			<option selected="selected" value="<%=i%>"><%=i%></option>
			<%}else{%>
			<option value="<%=i%>"><%=i%></option>
			<%}
			}%>
			
			</select>&nbsp;<span class="asterisk">&nbsp;*</span>
			</tr> 
		             <tr>
				     <td class="tableLeft">Season :</td>
				     <td class="tableRight">
					 
					 	<select name="selSeason" id="selSeason" class="selectboxOne" onchange="postData(this.value);">
										<option selected="selected" value="">Select One</option>
										<%
										ArrayList seasonDetails = (ArrayList)request.getAttribute("seasonList");
										if(seasonDetails!=null && seasonDetails.size()!=0){
										Iterator itr= seasonDetails.iterator();
										while(itr.hasNext()){
										String[] s = (String[]) itr.next();
										String seaId = s[0];
										String seaName = s[1];
										if(seasonId!=null && seasonId.equalsIgnoreCase(seaId)){
										%>
										<option value="<%=seaId%>" selected="selected" ><%=seaName%></option>
										<%}else{%>
										<option value="<%=seaId%>" ><%=seaName%></option>
										<%}}}%>
						</select>
					
							
				 	 <span class="asterisk">*</span>					 </td>
		     	  </tr>
		 			<%
		   		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					 ArrayList dateListView=(ArrayList)request.getAttribute("dateDetails");
					 if(dateListView!=null && dateListView.size()!=0){ 
					 Iterator itDate = dateListView.iterator();
					  while(itDate.hasNext()){
						    String [] dateDetail  = (String[]) itDate.next();
							String regDateId = dateDetail[0];
							String seaId = dateDetail[1];
							String seasonName = dateDetail[2];
							String stDueDate = dateDetail[3];
							String stGraceDate = dateDetail[4];
							String year = dateDetail[5];
							java.util.Date dueDate=null;
							java.util.Date graceDate=null;
							if(stDueDate!=null && stDueDate.trim().length()!=0){
							dueDate=(java.util.Date)sdf.parse(stDueDate);
							}if(stGraceDate!=null && stGraceDate.trim().length()!=0){
							graceDate=(java.util.Date)sdf.parse(stGraceDate);
							}
				
		             %>
					 <input type="hidden" name="regDateId" value="<%=regDateId%>"/>
					
		            <tr>
					<td class="tableLeft">Due Date :</td>
					<td class="tableRight">
                        <input name="txtDue" type="text" class="textboxOne" size="20" readonly="readonly" value="<%=dateCheck(dueDate)%>"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a>
                    	<span class="asterisk">*</span>
					</td>
				  </tr>
			      <tr>
					<td class="tableLeft">Grace Date :</td>
					<td class="tableRight">
					 <input name ="txtGrace" type="text" class="textboxOne" size="20" readonly="readonly" value="<%=dateCheck(graceDate)%>"/><a href="javascript:cal2.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a>
				 	 <span class="asterisk">*</span>
					</td>
				  </tr>						
					<%}}else{%>
					<tr>
					<td class="tableLeft">Due Date :</td>
					<td class="tableRight">
                        <input name="txtDue" type="text" class="textboxOne" size="20"  readonly="readonly"/><a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a>
                    	<span class="asterisk">*</span>
					</td>
				  </tr>
			      <tr>
					<td class="tableLeft">Grace Date :</td>
					<td class="tableRight">
					 <input name ="txtGrace" type="text" class="textboxOne" readonly="readonly" size="20" /><a href="javascript:cal2.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a>
				 	 <span class="asterisk">*</span>
					</td>
				  </tr>		
					<%}%>
						<tr>
						<td colspan="12" align="center">
						<input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn"  />	</td>
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
<script language="javascript">
    var cal1 = new calendar2(document.forms['myform'].elements['txtDue']);
	 cal1.year_scroll = true; 
	 cal1.time_comp = false;
	 
    var cal2= new calendar2(document.forms['myform'].elements['txtGrace']);
	cal2.year_scroll = true;
	cal2.time_comp = false;	
	
	
</script>
</body>
</html>
