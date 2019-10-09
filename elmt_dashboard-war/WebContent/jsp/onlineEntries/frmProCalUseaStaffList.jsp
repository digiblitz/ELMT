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
	if(document.frmProCalUseaStaffList.year.value==""){
		alert("Select a Year");
		document.frmProCalUseaStaffList.year.focus();
		return false;
	}
	
	if(document.frmProCalUseaStaffList.selArea.value==""){
		alert("Select an Area");
		document.frmProCalUseaStaffList.selArea.focus();
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
	String year = (String)request.getAttribute("year");
	String selArea = (String)request.getAttribute("area");
	String fstName=(String)session.getAttribute("firstName");
	String lastName=(String)session.getAttribute("lastName");
	String approveStatus = (String)request.getAttribute("approveStatus");
	String dispAppStatus = "";
	if(approveStatus!=null && approveStatus.equalsIgnoreCase("approveSuccess")){
		dispAppStatus = "Approval Status Changed Successfully";
	} else if(approveStatus!=null && approveStatus.equalsIgnoreCase("approveFailed")){
		dispAppStatus = "Approval Status Cannot be Changed";
	}
	int selectedYear = 0;
	if(year!=null && year.trim().length()!=0){
		selectedYear = Integer.parseInt(year);
	}
%>	  
		<form name="frmProCalUseaStaffList" id="frmProCalUseaStaffList" action="entrylist.do" onsubmit="return myValidate()" >
						   <input type="hidden" name="process" value="list" />
						<%if(approveStatus!=null && approveStatus.trim().length()!=0){%>
		<tr>
		  <td height="25" colspan="8" class="styleError"><%=dispAppStatus%></td>
		</tr>
		<%}%>   
						   
					<tr>
		<td colspan="8" align="center"><strong>Year:</strong> &nbsp;
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
		</select>&nbsp;<span class="asterisk">&nbsp;*</span>							
			   
						   
						 
					 &nbsp;&nbsp;<strong>Area:</strong> &nbsp;
					  <select name="selArea" id="selArea" class="selectboxOne">
                        <option selected="selected" value="">Select One</option>
						<%
						//String areaId = (String) request.getAttribute("area_id");
						//if(areaId==null) areaId = "";
						ArrayList allAreaList = (ArrayList)request.getAttribute("allAreaList");
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
											}
											else{
									%>
							<option  value="<%=area_id%>"><%=area_name%></option>
							<%

							}
							}
						}
					
						%>
                      </select>&nbsp;&nbsp;<span class="asterisk">&nbsp;*</span>
			          <input type="submit" name="submit" value="Submit" class="oneBtn"/></td>
					  </tr>
				  </form>
						 
						 <tr>
							<td width="61" class="tblRowHead">Event Date </td>
							<td width="60" class="tblRowHead">Event Title</td>
							<td width="68" class="tblRowHead">Organizer Name</td>
							<td width="43" class="tblRowHead">Area Code</td>
							<td width="55" class="tblRowHead">Location</td>
							<td width="82" class="tblRowHead">Edit/Approve</td>
							<td width="76" class="tblRowHead">Omnibus Listing </td>
						  </tr>
						  
						  <%
						  HLCCalendarVO calVO = new HLCCalendarVO();
						  ArrayList objProCalList = (ArrayList)request.getAttribute("objProCalList");
								if(objProCalList!=null && objProCalList.size()!=0){
								   Iterator itr =objProCalList.iterator();
        
							while(itr.hasNext()){
								calVO =(HLCCalendarVO)itr.next();
								String provisionId = calVO.getCalenderId();
								System.out.println("provisionId in jsp"+provisionId);
								String beginDate=dateFormat(calVO.getBeginDate());
								String endDate=dateFormat(calVO.getEndDate());
								String eventTitle=calVO.getEventTitle();
								String orgId=calVO.getOrganizerId();
								String areaCode=calVO.getAreaCode();
								String areaName=calVO.getAreaName();
								String stateName=calVO.getStateName();
								String areaChApprovStat=calVO.getOrgApprovalStatus();
								String orgFirstName = calVO.getOrgFName();  
								String orgLastName = calVO.getOrgLName();  
								String eveId=calVO.getEventId();  
								boolean resStatus=calVO.isResStatus();   
						  %>
						 
						  <tr>
							<td class="listCellBg"><%=beginDate%></td>
							<td class="listCellBg"><a href="entrylist.do?process=eventView&provisionId=<%=provisionId%>"><%=eventTitle%></a></td>
							<td class="listCellBg"><%=orgFirstName+" "+orgLastName%></td>
							<td class="listCellBg"><%=areaName%></td>
							<td class="listCellBg"><%=stateName%></td>
							<td class="listCellBg">
							<input name="process" type="button" class="twoBtn" value="Edit/Approve" onclick="location.href='calender.do?method=initStaffUpdate&provisionalId=<%=provisionId%>&eveId=<%=eveId%>'"/></td>
							<td class="listCellBg">
							<%if(resStatus==true){%>
							<input type="button" name="process" value="Update" class="twoBtn" onclick="location.href='OrganizerUSEAEventReg.do?process=initUpdate&eventId=<%=eveId%>'" />
							<%}else{%>
<input type="button" name="process" value="Update" class="twoBtn2" onclick="location.href='OrganizerUSEAEventReg.do?process=initUpdate&eventId=<%=eveId%>'" disabled="disabled"/>												
							<%}%>
							</td>
						  </tr>
						</form>	
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