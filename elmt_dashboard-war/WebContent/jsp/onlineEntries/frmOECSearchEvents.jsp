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
<%@ page import="com.hlccommon.util.*"%>
<%@ page import="com.hlcmro.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.HLCCalendarVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>

<script language="javascript" type="text/javascript">
function myValidate(){
		if((document.frmOECSearchEvents.txtEventId.value=="") && (document.frmOECSearchEvents.txtEventTitle.value=="") && (document.frmOECSearchEvents.selArea.selectedIndex==0) && (document.frmOECSearchEvents.year.selectedIndex==0)){
		alert("Please Provide Any One Of The Information");
		document.frmOECSearchEvents.txtEventId.focus();
		return false;
		}
		return true;
}
</script>
</head>

<body>

<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop"><!-- HEADER STARTS HERE -->
        <%@ include file = "../../include/header.jsp" %>
        <!-- HEADER ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="infoBar"><!-- INFO BAR STARTS HERE -->
        <%@ include file = "../../include/infobar.jsp" %>
        <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg"><table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        <td width="230" class="menuTablePad"><!-- LEFT MENU STARTS HERE -->
              <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
              <!-- LEFT MENU ENDS HERE -->
        </td>
        <td width="500" class="subDeptTablePad">
		<!-- CONTENTS START HERE -->
              <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead">
						 Online Event Entries: <span class="styleBoldTwo"></span>
					</td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
					<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--></td>
				  </tr>
				<%!
  		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
					
		String dateFormat(java.util.Date fieldName){					
		Calendar cal = Calendar.getInstance();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fieldName);
		cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
						
		int tempDay = gc.get(Calendar.DAY_OF_WEEK);
		String holiday = null;
			switch(tempDay){
			case Calendar.SUNDAY:
				holiday ="SUN";
				break;
			case Calendar.MONDAY:
				holiday ="MON";
				break;
			case Calendar.TUESDAY:
				holiday ="TUE";
				break;
			case Calendar.WEDNESDAY:
				holiday ="WED";
				break;
			case Calendar.THURSDAY:
				holiday ="THU";
				break;
			case Calendar.FRIDAY:
				holiday ="FRI";
				break;
			case Calendar.SATURDAY:
				holiday ="SAT";
				break;
			}
			String dispDate = "N/A";
			if(fieldName!=null ){
				dispDate = sd1.format(cal.getTime()).toString()+" ("+holiday+")";
			}
			return dispDate;
		}
  %> 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
			
		<%
		java.util.Date orgDate = new java.util.Date();
		int currentYear = 1900+orgDate.getYear();
		String eveId = (String)request.getAttribute("eventId");
		String eveTitle = (String)request.getAttribute("eventTitle");
		String selArea = (String)request.getAttribute("areaId");
		String fstName=(String)session.getAttribute("firstName");
		String lastName=(String)session.getAttribute("lastName");
		String compYear=(String)request.getAttribute("compYear");
		System.out.println("compYear"+compYear);
		int selectedYear = 0;
	if(compYear!=null && compYear.trim().length()!=0){
		selectedYear = Integer.parseInt(compYear);
	}
		
		%>	  
		<form name="frmOECSearchEvents" id="frmOECSearchEvents" action="calender.do" onsubmit="return myValidate()" >
						   <input type="hidden" name="method" value="search" />
						<%if(eveId!=null){%>
						<tr>
						<td class="tableLeft">Event Id :</td> 
						<td class="tableRight">
						<input  type="text" name="txtEventId" id="txtEventId"  class="textboxOne" value="<%=eveId%>" size="10"  /></td></tr>           
						<%}else{%>
						<tr>
						<td class="tableLeft">Event Id :</td> 
						<td class="tableRight">
						<input  type="text" name="txtEventId" id="txtEventId"  class="textboxOne" value="" size="10"  /></td></tr>           
						<%}%>
						<%if(eveTitle!=null){%>
						<tr><td class="tableLeft">Event Title :</td> 
						<td class="tableRight">
						<input  type="text" name="txtEventTitle" id="txtEventTitle"  class="textboxOne"  value="<%=eveTitle%>" size="10" /></td></tr>
						<%}else{%>
						<tr><td class="tableLeft">Event Title :</td> 
						<td class="tableRight">
						<input  type="text" name="txtEventTitle" id="txtEventTitle"  class="textboxOne"  value="" size="10" /></td></tr>      <%}%>
						<tr><td class="tableLeft">Area :</td> 
		            	<td class="tableRight">
					  <select name="selArea" id="selArea" class="selectboxOne">
                        <option selected="selected" value="">Select One</option>
						<%
						ArrayList allAreaList = (ArrayList)request.getAttribute("selectAreaDetails");
						if(allAreaList!=null&& allAreaList.size()!=0){
						Iterator areaLst = allAreaList.iterator();
						while(areaLst.hasNext()){
						String AreaLstArr[] = (String [])areaLst.next();
						String area_id = AreaLstArr[0];
						String area_code = AreaLstArr[1];
						String area_name = AreaLstArr[2];
						out.print("area_id "+area_id);
						if(area_id.trim().equalsIgnoreCase(selArea)){
						%>
						<option  value="<%=area_id%>" selected="selected"><%=area_name%></option>
						<%
						}else{
						%>
						<option  value="<%=area_id%>"><%=area_name%></option>
						<%}}}%>
					  </select>
			          </td>
					  </tr>
					<tr>
		<td class="tableLeft">Year:</td>
		<td class="tableRight">
		<select name="year" id="year" class="selectboxOne" >
		<option selected="selected" value="" >Select One</option>
		<%
			for(int i=2000; i<(currentYear+2); i++){
				if(selectedYear!=0 && selectedYear==i){
		%>
		<option value="<%=i%>" selected="selected"><%=i%></option>
		<%}else{%>
		<option value="<%=i%>"><%=i%></option>
		<%
			}
		}
		%>
		</select>  
			</td>
			</tr>		  
					<tr><td class="tableLeft"></td> 
					  <td class="tableRight">
					  <input type="submit" name="Search" value="Search" class="oneBtn"/></td></tr>
					   </form>
					  </table>
					  </td></tr>
				 
				 <tr>
					<td>
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">		 
						  <tr>
							<td width="69" class="tblRowHead">Event Date </td>
							<td width="64" class="tblRowHead">Event Title</td>
							<td width="101" class="tblRowHead">Organizer Name</td>
							<td width="40" class="tblRowHead">Area Code</td>
							<td width="67" class="tblRowHead">Location</td>
							<td width="38" class="tblRowHead">Status</td>
							
						  </tr>
						  
						  <%
						  HLCCalendarVO calVO = new HLCCalendarVO();
						  ArrayList objProCalList = (ArrayList)request.getAttribute("selectEventDetails");
								if(objProCalList!=null && objProCalList.size()!=0){
								   Iterator itr =objProCalList.iterator();
        
							while(itr.hasNext()){
								calVO =(HLCCalendarVO)itr.next();
								String eventId = calVO.getEventId();
								String beginDate=dateFormat(calVO.getBeginDate());
								String endDate=dateFormat(calVO.getEndDate());
								String eventTitle=calVO.getEventTitle();
								String orgId=calVO.getOrganizerId();
								String areaId=calVO.getAreaId();
								String areaName=calVO.getAreaName();
								String stateName=calVO.getStateName();
								String areaChApprovStat=calVO.getOrgApprovalStatus();
								String status=calVO.getStatus();
								String orgFirstName = calVO.getOrgFName();  
								String orgLastName = calVO.getOrgLName();
						  %>
						 
						  <tr>
							<td class="listCellBg"><%=beginDate%></td>
							<td class="listCellBg"><a href="calender.do?method=searchEventView&eventId=<%=eventId%>&orgId=<%=orgId%>"><%=eventTitle%></a></td>
							<td class="listCellBg"><%=orgFirstName+" "+orgLastName%></a></td>
							<td class="listCellBg"><%=areaName%></td>
							<td class="listCellBg"><%=stateName%></td>
							<td class="listCellBg"><%=status%>
							</td>
							
						  </tr>
						
						  <%
						  }
						  }
						  else{
						  %>
						<tr>
							<th height="25" colspan="6">No records are available. </th>
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
    </table></td>
  </tr>
  <tr>
    <td class="footerBg"><!-- FOOTER STARTS HERE -->
        <%@ include file = "../../include/footer.jsp" %>
        <!-- FOOTER ENDS HERE -->
    </td>
  </tr>
</table>
</body>
</html>
