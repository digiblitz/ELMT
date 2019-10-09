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
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script language="javascript">
function onValidate(){
	if(document.frmListEvent.selStatus.value==""){
		alert("Select a Status");
		document.frmListEvent.selStatus.focus();
		return false;
	}
	if(document.frmListEvent.year.value==""){
		alert("Select a Year");
		document.frmListEvent.year.focus();
		return false;
	}
	return true;
}
</script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<%! 
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%!				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
					String dateFormat(String fieldName){					
						java.util.Date clDate = null;
						Calendar cal = Calendar.getInstance();
						GregorianCalendar gc = new GregorianCalendar();
						try{
							clDate = sd.parse(fieldName);
						}catch(Exception e){
							System.out.println("Error While Parsing Date: "+e);
						}
						
						gc.setTime(clDate);
						cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						String dispDate = "N/A";
						if(clDate!=null ){
						dispDate = sd1.format(cal.getTime());
						}
						return dispDate;
					}
  %>
 
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
  <%
  String approveStatus = (String)request.getAttribute("approveStatus");
	String dispAppStatus = "";
	if(approveStatus!=null && approveStatus.equalsIgnoreCase("approveSuccess")){
		dispAppStatus = "Approval Status Changed Successfully";
	} else if(approveStatus!=null && approveStatus.equalsIgnoreCase("approveFailed")){
		dispAppStatus = "Approval Status Cannot be Changed";
	}
	
	 String updateStatus = (String)request.getAttribute("updateStatus");
	String tempUpdtStatus = "";
	if(updateStatus!=null && updateStatus.equalsIgnoreCase("updateSuccess")){
		tempUpdtStatus = "Event Registration Details Updated Successfully";
	} else if(updateStatus!=null && updateStatus.equalsIgnoreCase("updateFailed")){
		tempUpdtStatus = "Event Registration Details not Updated Successfully";
	}
  %>
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
				
				
					<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
						Meetings: <span class="styleBoldTwo">Listing Event Registration Entries</span></td>
					  </tr>
					 
					 <tr>
						<td>
		 <table width="530" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">					 
		<form name="frmListEvent" id="frmListEvent" method="post" action="eventRegList.do" onsubmit="return onValidate();">
		<input type="hidden" name="cmd" value="eventList"/>
							 
		<%if(approveStatus!=null && approveStatus.trim().length()!=0){%>
		<tr>
		  <td height="25" colspan="8" class="styleError"><%=dispAppStatus%></td>
		</tr>
		<%}%>
		
		<%if(updateStatus!=null && updateStatus.trim().length()!=0){%>
		<tr>
		  <td height="25" colspan="8" class="styleError"><%=tempUpdtStatus%></td>
		</tr>
		<%}%>							 
							
								  <tr>
								<td height="25" colspan="2"  bgcolor="#ffffff" class="alignLeft">&nbsp;&nbsp;<strong>Status:</strong>&nbsp;&nbsp;
								  <%	
									String status = (String) request.getAttribute("status");
									String year = (String)request.getAttribute("year");
									int selYear =0;
									if(year != null && year.trim().length()!=0){
									 selYear = Integer.parseInt(year);
									}									
								%>
										<select name="selStatus" id="selStatus" class="selectboxOne" > 
										<option value="" selected="selected">Select One</option>
								<%	String [] statStr = {"Approved","Pending","Rejected"};
								for(int i = 0 ; i< 3 ;i++){
								if(status!= null && status.equalsIgnoreCase(statStr[i])){%>
													<option  value="<%=statStr[i]%>" selected="selected"><%=statStr[i]%></option>
												<%
												}
												else{
												%>
													<option  value="<%=statStr[i]%>"><%=statStr[i]%></option>
												<%	}
								}								
                                 %>
                                  </select>
										<span class="asterisk">*</span>
								&nbsp;&nbsp;<strong>Competition Year:</strong>&nbsp;&nbsp;
								<select name="year" id="year" class="selectboxOne" > 


										<option value="" selected="selected">Select One</option>
								<%	java.util.Date orgDate = new java.util.Date();
									int currentYear = 1900+orgDate.getYear();
									for(int i = 2000;i<=(currentYear+2);i++){
								if(year!=null && selYear==i ){%>
													<option  value="<%=i%>" selected="selected"><%=i%></option>
												<%
												}
												else{
												%>
													<option  value="<%=i%>"><%=i%></option>
												<% }	
											}									
                                 %>
                                  </select><span class="asterisk">*</span>		
								  <input type="submit" name="submit" value="Submit" class="gradBtn"/>					 
								</td>
								  </tr>
 									</form>
								</table>
						   
						  <tr>
						  <td>
							  <table border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							  <tr>
									<td width="72" class="tblRowHeadTwo">Event ID </td>	
									<td width="114" class="tblRowHeadTwo">Event Title </td>
									<td width="72" class="tblRowHeadTwo">Begin Date </td>
									<td width="73" class="tblRowHeadTwo">End Date </td>
									<td width="68" class="tblRowHeadTwo">Edit </td>
									<td width="94" class="tblRowHeadTwo">Approve</td>
							   </tr>
							<%
								ArrayList ValueList = (ArrayList)request.getAttribute("EVENT_REG_LIST");
							
								if((ValueList!=null && ValueList.size()!=0)){
									Iterator itr = 	ValueList.iterator();
										while(itr.hasNext()) {	
											String[] HLCEventDetails = (String [] ) itr.next();
										//State Masters
											String eventId  = HLCEventDetails[0];
											String eventTitle  = HLCEventDetails[1];
											String eventBeginDate = HLCEventDetails[2];
											String eventEndDate  = HLCEventDetails[3];
											
										
							%>
								
							  <tr>
							  <form name="frmListEventReg" id="frmListEventReg" method="post" action="eventRegList.do">
							 <input type="hidden" name="cmd" value="singleEventDetail"/>
							  <input type="hidden" name="eventId" id="eventId" value="<%=eventId%>" />
									<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(eventId)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=nullCheck(eventTitle)%></td>	
									<td height="26" bgcolor="#E3E1D2" class="alignCenter"><%=dateFormat(eventBeginDate)%></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><%=dateFormat(eventEndDate)%></td>
									<td height="26" bgcolor="#E3E1D2" class="alignCenter"><input type="submit" value="Edit" name="type" class="oneBtn"  /></td>
									<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" value="Approve" name="type" class="oneBtn"  /></td></form>								
							  </tr>
								<%
											}
								} 
								else{
							   %>
								<tr>
									<td height="25" colspan="7" class="alignCenter"><strong>No Records Found!</strong></td>
								</tr>
							<% } %>
   								</table>
							</td>
							</tr>							
						  </table>
			</td>
					</tr>  
					</table>
					
					<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	 
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
