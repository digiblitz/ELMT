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
<%@ page import="com.hlcutil.HLCMemberVO"%>
<%@ page import="com.hlcutil.HLCCalendarVO"%>
<%@ page import="com.hlcutil.HLCHorseRegListVO"%>
<%@ page import="com.hlccommon.util.*"%>
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
function checkValidity(horseMemberId){
	var horseIds = document.getElementById('horseIds').value;
	
	if(horseIds.indexOf(horseMemberId)!=-1){
		alert("Selected Non-Human Already Registered");
		return false;
	}
	return true;
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
<body>
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
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        
			<td width="500" class="subDeptTablePad">
		<!-- CONTENTS START HERE -->
             
				 			
		<form name="frmDemographyDetList" id="frmDemographyDetList" action="OEEDemo.do" >
		<table width="100%" cellpadding="0" cellspacing="1" border="0">
		 <tr>
					<td>
						<table width="100%" cellpadding="0" cellspacing="1" border="0">
						
			 <tr>
					<td colspan="10" class="tblMainHead">
						HLC X-Entry: <span class="styleBoldTwo"></span>Non-Human Selection Screen					</td>
				  </tr>	
				  
				  <%String horseCheck=(String)request.getAttribute("valStat");%>
				  
				  		
		<% 
		
		
		
		String tmpVal="";
		String eveTyp2="";
		String eveDiv1="";
		ArrayList eventLevelDetails = (ArrayList)request.getAttribute("eventLevelDetails");
		System.out.println(	"eventLevelDetails: "+eventLevelDetails);
		
		if(eventLevelDetails!=null && eventLevelDetails.size()!=0){
		Iterator itrSub = eventLevelDetails.iterator();
		while(itrSub.hasNext()){
		String[] subEntry = (String[])itrSub.next();
		String eveCode = subEntry[2];
		 eveTyp2 = subEntry[3];
		 eveDiv1 = subEntry[7];
		System.out.println("tmpVal"+eveCode);
		if(tmpVal==""){
		tmpVal = eveCode+"("+eveDiv1+")";		
		}else{	
		tmpVal = tmpVal + ","+ eveCode+"("+eveDiv1+")";	
		}														
		}								
		}
		
		ArrayList selectedHorseMemberIds = (ArrayList)session.getAttribute("selectedHorseMemberIds");
		String orgFirstName = (String)session.getAttribute("orgFirstName");
		String orgLastName = (String)session.getAttribute("orgLastName");
		
		String eventTypeId = (String)session.getAttribute("eventTypeId");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");					   
		java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
		String currentDate=String.valueOf(tempDate);
		String attactDate = String.valueOf(tempDate).substring(5,7)+"/"+String.valueOf(tempDate).substring(8,10)+"/"+String.valueOf(tempDate).substring(0,4);
		String memberStatus = (String)request.getAttribute("isMember");
		HLCMemberVO memVO = new HLCMemberVO();
		boolean amaSt1=false;
		boolean amaSt2=false;
		String amStatus1="";
		String amStatus2="";
		memVO = (HLCMemberVO)request.getAttribute("memDetails");
		String compYear = (String)session.getAttribute("compYear");
		
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
<td colspan="10" style="background:#666666;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" style="background-color: #F0F0F0" /></td>
</tr>

	  
	<tr>
    <td colspan="5" valign="middle"><div align="center"><span class="styleNew style11"><strong>Selected for your Entry:</strong></span></div></td>

</tr>
<tr>
<td colspan="10" style="background:#666666;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" style="background-color: #F0F0F0" /></td>
</tr>
<tr>
							<input type="hidden" name="eventTypeId" id="eventTypeId" value="<%=eventTypeId%>" />
						
							</tr>

<tr>	

<td align="center" valign="middle">      <div align="center" style="width:85px;background:#006600;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, Helvetica, sans-serif;font-weight:bold;">Selected<br />
    Rider</div></td>


  <td width="460" colspan="5" valign="middle" class="styleNew" style="padding:3px"><span class="style3"><strong><%=memberName%> (ID#<%=memberId%>) &nbsp;&nbsp;Age: <%=(noDays/365)%></strong><br />
  Membership Level: &nbsp;<%=membTypeName%> &nbsp;Amateur (<%=amStatus2%>)
<br />

  Address: &nbsp;<%=address%> / <%=city+"/"+state%> &nbsp;<%=zipcode%>
  </span>
  </td>

</tr>

<tr>
<td colspan="10" style="background:#dddddd;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" style="background-color: #F0F0F0" /></td>
</tr>

 <tr>
    <td align="center" valign="middle"><div align="center" style="width:85px;background:#006600;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, Helvetica, sans-serif;font-weight:bold;">Selected<br />
    Event</div></td>

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
								String areaCode=calVO.getAreaCode();
								String areaName=calVO.getAreaName();
								String stateName=calVO.getStateName();
								String areaChApprovStat=calVO.getOrgApprovalStatus();
								String status=calVO.getStatus();
								
								    
						  %>

 			    <td style="padding-bottom:10px;padding-top:10px;" colspan="2" valign="middle"><span class="styleNew"><span class="style2">Event:</span> <span class="style3"><strong><%=eventTitle%> (<%=areaCode%>) &nbsp;<%=stateName%></strong> | <a href="http://dashboard.useventing.com/resources/scripts/omnibus_preview/index.asp?event_id=<%=eventId%>" target="_blank">Omni Listing</a><br>
		Event Date: <%=beginDate%> to <%=endDate%><br/>		
		Open Date:	<%=calVO.getEntryStrtDate()%> | Close Date: <%=calVO.getEntryEndDate()%><br/>
		Event Levels Offered: <%=tmpVal%>		
			</span></td>

						
<%}}else{%>	
</tr>

<tr>
<td colspan="10">
		<strong>No records are available.</strong>
		</td>
		</tr>	
		<%}%>		

	 <tr>
<td colspan="10" style="background:#666666;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" style="background-color: #F0F0F0" /></td>
</tr>						
												
<tr>
    <td colspan="5" valign="middle"align="center" style="padding:12px;">

     <span class="styleNew style9"><strong>Select a Non-Human</strong></span><br />
	 <%if(horseCheck!=null && horseCheck.equalsIgnoreCase("Non-Human Already Registered")){%>
    <span class="asterisk"> <%=horseCheck%></span>
	 <%}%>
      </div></td>
	  
	 
  </tr>
		
<tr>
<td colspan="10" style="background:#666666;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" style="background-color: #F0F0F0" /></td>
</tr>
		
		<%
						String titleEventTypeName="";
							String hId = "";
							ArrayList userInfo = (ArrayList) request.getAttribute("userInfo");
							if(userInfo!=null && userInfo.size()!=0){
							Iterator itr = 	userInfo.iterator();
							while(itr.hasNext()){
								HLCHorseRegListVO AppHrListVO = (HLCHorseRegListVO) itr.next();								
								String horseMemberId = AppHrListVO.getHorseMemberId();
								String horseName = AppHrListVO.getHorseName();
								String reqStatus = AppHrListVO.getStatusName();
					
								String ownerId = AppHrListVO.getOwnerId();
								java.util.Date dte = AppHrListVO.getAddDate();
								String memberType = AppHrListVO.getMembershipTypeName();
								String date  = "N/G";
								String yrFoaled=AppHrListVO.getYearFoaled();
								String horseURL = "OEEAddTrainer.do?process=initTrainer&horseMemberId="+horseMemberId+"&eventTypeId="+eventTypeId+"&compYear="+compYear+"&horseName="+horseName;
								if(selectedHorseMemberIds!=null && selectedHorseMemberIds.size()!=0){
									Iterator ish = selectedHorseMemberIds.iterator();
									while(ish.hasNext()){
										hId = hId+"#"+(String)ish.next();
									}
								}
								if(dte!=null){
									date = sdf.format(dte);
								}
								
								Calendar cal2 = Calendar.getInstance();
                  
                    int currentYear = cal2.get(Calendar.YEAR);
                    int horseYear = 0;
                    if(yrFoaled!=null && yrFoaled.trim().length()!=0) horseYear = Integer.parseInt(yrFoaled);
                   int horseAge = currentYear - horseYear;
					
								
								%>	   
						
		
						
	 <tr>
    <td height="75" align="center" valign="middle"><a href="<%=request.getContextPath()%>/OEEAddTrainer.do?process=initTrainer&horseMemberId=<%=horseMemberId%>&eventTypeId=<%=eventTypeId%>&compYear=<%=compYear%>&horseName=<%=horseName%>&horseAge=<%=horseAge%>&memberType=<%=memberType%>&date=<%=date%>&riderMemberId=<%=memberId%>" style="text-decoration:none;">
      <div align="center" style="width:85px;background:#006699;color:#FFFFFF;padding:3px;font-size:14px;font-family:Arial, Helvetica, sans-serif;font-weight:bold;">Select Non-Human</div>
    </a>
	
<td height="75" colspan="10" valign="middle"><p><span class="styleNew"><span class="style2"> <strong>Non-Human:</strong></span><span class="style3"><strong> <%=horseName%>  (ID# <a href="RegHorseListing.do?process=chngdesc&memid=<%=horseMemberId%>" onclick="return checkValidity(<%=horseMemberId%>)"><%=horseMemberId%></a>) Age: <%=horseAge%> </strong><br />
    </span><span class="style3">Registration Level: <%=memberType%> Registration Date: <%=date%></span></span></p>  
		
	</td>
	   
						  <% String eventId = (String)session.getAttribute("eventId"); %>
						<!--<input type="hidden" name="eventId" value="<%//=eventId%>" />
			<input type="hidden" name="horseMemberId" value="<%//=horseMemberId%>" />
			<input type="hidden" name="compYear" value="<%//=compYear%>" />
				<input type="hidden" name="process" value="UpgradeDet"/>
				<input type="hidden" name="memid" value="<%//=horseMemberId%>"/>
				<input type="hidden" name="horseAge" value="<%//=horseAge%>"/>
				<input type="hidden" name="memberType" value="<%//=memberType%>"/>
				<input type="hidden" name="date" value="<%//=date%>"/>-->
				  	
</tr>
<tr>
<td colspan="5" style="background:#dddddd;height:3px;padding:0px;"><img name="" src="" width="1" height="1" alt="" style="background-color: #F0F0F0" /></td>
</tr>

  <%         
								
								 
					 	}}%>	       
	
	 <tr>
	<td class="listCellBg" colspan="10" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" name="register" value="Search / Register New Non-Human" class="gradBtn" onclick="location.href='SearchHorse.do?searchProcess=initView&source=fromEventEntry'"/> </td>
								
								 <input type="hidden" name="horseIds" id="horseIds" value="<%=hId%>" />
								</tr>
	
	
	
							
					 </table>
					</td>
				</tr>  	
				
				</table>	
				  </form>
			
          <!-- CONTENTS END HERE -->
        </td>
      </tr>
	  
    </table>
	</td>
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
