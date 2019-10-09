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
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.HLCCalendarVO"%>
<%@ page import="com.hlcutil.HLCMemberVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><bean:message key="meetings.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript" type="text/javascript">


function focus_login()
{
	document.frmFinalEveCal.year.focus();
}	
</script>

<style type="text/css">

.styleNew {font-family: Arial, Helvetica, sans-serif;
         font-size:18px;}
.style2 {
	font-size: 16px;
	font-weight: bold;
}
.style3 {font-size: 12px}
.style9 {font-size: 24px}
.style12 {font-family: Arial, Helvetica, sans-serif; font-size: 16px; }
.style14 {font-family: Arial, Helvetica, sans-serif; font-size: 16px; font-weight: bold; }

</style>

</head>

<body onload="focus_login();">

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
       
        <td width="500" class="subDeptTablePad">
		<!-- CONTENTS START HERE -->
<table width="100%" cellpadding="0" cellspacing="1" border="0">				  <tr>
					<td width="481" colspan="5" class="tblMainHead">
						 Event Calendar: <span class="styleBoldTwo"></span>
					Select an Event </td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">
					</td>
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
						<table width="100%" cellpadding="0" cellspacing="1" border="0">
				
						
					
					<%
	
	String selArea = (String)request.getAttribute("area");
	String memberStatus = (String)request.getAttribute("isMember");
	HLCMemberVO memVO = new HLCMemberVO();
	java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
	boolean amaSt1=false;
		boolean amaSt2=false;
		String amStatus1="";
		String amStatus2="";
		memVO = (HLCMemberVO)request.getAttribute("memDetails");
		
		String memberId = memVO.getMemberId();
		String memberName = memVO.getMemberName();
		String age = dateFormat(memVO.getAge());
		String address = memVO.getAddress();
		String city = memVO.getCity();
		String state = memVO.getState();
		String zipcode = memVO.getZipcode();
		amaSt1 = memVO.isDecAmaStatus1();
		amaSt2 = memVO.isDecAmaStatus2();
		String membTypeName=memVO.getMembTypName();
		
		if(amaSt1==true) amStatus1="Yes";
		else amStatus1="No";
		
		if(amaSt2==true) amStatus2="Yes";
		else amStatus2="No";
		
		Calendar cal = Calendar.getInstance();
		
		long stTime = memVO.getAge().getTime();
		long eTime = tempDate.getTime();
		long diffTime = eTime - stTime;
		int noDays = (int)(diffTime/(1000*60*60*24));		
	

%>	 
<tr>
<td colspan="10">
<div align="center"><img src="images/usea_xntry_final.jpg" width="200" /></div>
 </td>
 </tr>

<form name="frmFinalEveCal" id="frmFinalEveCal" action="entrylist.do" onsubmit="return myValidate()">
				<input type="hidden" name="process" value="finalEveCal" />		
						   
					<tr>
					<td colspan="10" valign="middle">
					 &nbsp;&nbsp;<strong>Area:</strong> &nbsp;
					  <select name="selArea" id="selArea" class="selectboxOne">
                        <option selected="selected" value="">Select One</option>
						<option value="">All</option>
						<%						
						ArrayList allAreaList = (ArrayList)request.getAttribute("allAreaList");
						if(allAreaList!=null&& allAreaList.size()!=0){
						Iterator areaLst = allAreaList.iterator();
						while(areaLst.hasNext()){
								String AreaLstArr[] = (String [])areaLst.next();
								String area_id = AreaLstArr[0];
								String area_code = AreaLstArr[1];
								String area_name = AreaLstArr[2];
								out.print("area_id in jsp"+area_id);
								if(area_id.trim().equalsIgnoreCase(selArea)){
						%>
							<option  value="<%=area_id%>" 
selected="selected"><%=area_name%></option>
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
						
                      </select>&nbsp;&nbsp;
			          <input type="submit" name="submit" value="Submit" class="oneBtn"/>
					  </td>
					  </tr>
				  </form> 		
		
			

<tr>
<td colspan="10" style="background:#666666;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" 
style="background-color: #F0F0F0" /></td>
</tr>
<%
 ArrayList objProCalList = (ArrayList)request.getAttribute("objAppEvent");
								if(objProCalList!=null && objProCalList.size()!=0){
								   Iterator itr =objProCalList.iterator();%>
	 <tr>
    <td colspan="3" valign="middle" align="center" style="padding:12px;">
      <span class="styleNew style9"><strong>Select an Event</strong></span><br />
        <span 
class="style12">&nbsp;Events highlighted in Blue are open for registration. </span>

      </td>
	  
	
	  
  </tr>
	
	
	
	<tr>
<td colspan="5" style="background:#666666;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" 
style="background-color: #F0F0F0" /></td>

</tr>

 
  <%
  
						  HLCCalendarVO calVO = new HLCCalendarVO();
						 
        
							while(itr.hasNext()){
								String entryStartDate="";
								String entryCloseDate="";
								calVO =(HLCCalendarVO)itr.next();
								String provisionId = calVO.getCalenderId();
								//System.out.println("provisionId in jsp"+provisionId);
								String beginDate=dateFormat(calVO.getBeginDate());
								String endDate=dateFormat(calVO.getEndDate());
								String eventTitle=calVO.getEventTitle();
								String eventId = calVO.getEventId();
								String orgId=calVO.getOrganizerId();
								String firstName=calVO.getOrgFName();
								String lastName=calVO.getOrgLName();
								String areaCode=calVO.getAreaCode();
								String areaName=calVO.getAreaName();
								String stateName=calVO.getStateName();
								String areaChApprovStat=calVO.getOrgApprovalStatus();
								
				String tmpVal="";
		String eveTyp2="";
		ArrayList objEveLevels = calVO.getEveLevels();
		//System.out.println(	"objEveLevels: "+objEveLevels.size());
		
		if(objEveLevels!=null && objEveLevels.size()!=0){
		Iterator itrSub = objEveLevels.iterator();
		while(itrSub.hasNext()){
		String[] subEntry = (String[])itrSub.next();
		String eveCode = subEntry[0]; 
		String eveDiv = subEntry[2]; 
		//System.out.println("tmpVal"+eveCode);
		if(tmpVal==""){
		tmpVal = eveCode+"("+eveDiv+")";	
		}else{	
		tmpVal = tmpVal + ","+ eveCode+"("+eveDiv+")";		
		}														
		}								
		}				    
						  %>


  <tr>
  
  <%if(calVO.getEntryStrtDate()!=null && calVO.getEntryEndDate()!=null){
							Date today=new Date();                 	 
      						Date entryStartDate1=calVO.getEntryStrtDate();
							Date entryEndDate1=calVO.getEntryEndDate();
							
				if(today.compareTo(entryStartDate1)<0){						
							%>
    <td height="75" align="center" valign="middle"><div align="center" 
style="width:85px;background:#aaaaaa;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, Helvetica, 
sans-serif;font-weight:bold;">Not Open for Entry</div></td>

	<%}else if(today.compareTo(entryEndDate1)>0){%>
	
	    <td height="75" align="center" valign="middle"><div align="center" 
style="width:85px;background:#aaaaaa;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, Helvetica, 
sans-serif;font-weight:bold;">Closed Event</div></td>	

	<%}else{%>
	
	 <td height="75" align="center" valign="middle">
	 <a 
href="<%=request.getContextPath()%>/OEEDemo.do?process=initDemoList&eventTypeId=<%=eventId%>&compYear=<%=beginDate.substring(
6,10)%>" style="text-decoration:none;">
      <div align="center" style="width:85px;background:#006699;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, 
Helvetica, sans-serif;font-weight:bold;">Select Event</div>
    </a></td>
	
	<%}}else{%>
	
 <td height="75" align="center" valign="middle"><div align="center" 
style="width:85px;background:#aaaaaa;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, Helvetica, 
sans-serif;font-weight:bold;">Select Event</div></td>		
	<%}%>
	
	
   <td style="padding-bottom:10px;padding-top:10px;" colspan="2" valign="middle"><span class="styleNew"><span 
class="style2">Event:</span> <span class="style3"><strong><%=eventTitle%> (<%=areaName%>)  <%=stateName%></strong> | <!-- <a 
href="http://dashboard.useventing.com/resources/scripts/omnibus_preview/index.asp?event_id=<%=eventId%>" target="_blank">Omni 
Listing</a>--><br>Event Date: <%=beginDate%> to <%=endDate%><br />

	<% if(calVO.getEntryStrtDate()!=null)
							{
							entryStartDate=sd1.format(calVO.getEntryStrtDate());
							//entryStartDate=dateFormat(calVO.getEntryStrtDate());
							%>Open Date: <%=entryStartDate%> <%}else{
							%>Open Date: <%=calVO.getEntryStrtDate()%> <%}%>| 
<%if(calVO.getEntryEndDate()!=null)
							{
							entryCloseDate=sd1.format(calVO.getEntryEndDate());
							%>					
							Close Date: <%=entryCloseDate%> <%}else{
							%>Close Date: <%=calVO.getEntryEndDate()%> <%}%>  <br />
Event Levels Offered: <%=tmpVal%></span></span>   </td>
  </tr>


<tr>
<td colspan="5" style="background:#dddddd;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" 
style="background-color: #F0F0F0" /></td>
</tr>



	
	<%}}else{%>
	
	
	<tr>
<td colspan="10">
		<strong>No records are available.</strong>
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
