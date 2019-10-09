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
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmro.util.*"%>
<%@ page import ="com.hlcmro.util.HLCEventRequestVO"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />

<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmEvntOrgRenewal.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script language="JavaScript" src="javascripts/calendar2.js" type="text/javascript"></script>

<script language="JavaScript">
var arHttpRequest;

function HLCMemberDetails(){

if(document.frmEvntOrgRenewal.managerUseaMemberId.value!="" && document.frmEvntOrgRenewal.managerUseaMemberId.value.indexOf(" ")!=0)
	{
	    var memberid=document.frmEvntOrgRenewal.managerUseaMemberId.value;
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 
        if (window.ActiveXObject){ 
           arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
         } 
        else if (window.XMLHttpRequest){ 
            arHttpRequest = new XMLHttpRequest(); 
        } 
        arHttpRequest.open("POST", url, true); 
        arHttpRequest.onreadystatechange = function() {membStatus(); } ; 
        arHttpRequest.send(null); 
   } 
   }

    function membStatus(){ 
   
        if (arHttpRequest.readyState == 4){ 
            if(arHttpRequest.status == 200) { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
		         if(arnameText=="false"){    
                alert("Member Id Doesn't Exists!"); 
				document.frmEvntOrgRenewal.managerUseaMemberId.value="";
				document.frmEvntOrgRenewal.managerUseaMemberId.focus();
                return false;
                }    
            } 
            else { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 

</script>
<%! String dateCheck(java.util.Date fieldName){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String detValue = "";
        if(fieldName!=null){
            detValue = sdf.format(fieldName);
        }
        return detValue;
    }
    %>
<%
ArrayList listContact = (ArrayList) session.getAttribute("dynamicOrgDetails");
String memberId =(String)session.getAttribute("memberId");
if(memberId==null){
memberId="Not exists";
}
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null)
				middle_name = "";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null)
				address2 = "";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();

				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null)
				fax_no = "";
			}
		}
%>
<% 
	
			HLCEventRequestVO objEventReqVO = new HLCEventRequestVO();
			objEventReqVO = (HLCEventRequestVO)request.getAttribute("EVENT_REQUEST_VO");		
				String event_id = "";
				String organizer_id = "";
				String event_title = "";			
				String competition_location = "";
				String competition_city = "";
				String competition_state = "";
				String competition_country = "";
				String competition_zip = "";
				String championship_area = "";
				String status_id = "";
			 ArrayList mappingTypeIds = new ArrayList();
			 
			 String eventSubTypes = "";
			 java.util.Date eventBeginDate=null;
			 java.util.Date eventEndDate=null;
			 
			 if(objEventReqVO!= null ){
			  event_id = objEventReqVO.getEvent_id();
              organizer_id = objEventReqVO.getOrganizer_id();
              event_title = objEventReqVO.getEvent_title();
              eventBeginDate = objEventReqVO.getEveBegDate();		 
			
              eventEndDate = objEventReqVO.getEveEndDate();	
              competition_location = objEventReqVO.getCompetition_location();
              competition_city = objEventReqVO.getCompetition_city();
              competition_state = objEventReqVO.getCompetition_state();
              competition_country = objEventReqVO.getCompetition_country();
              competition_zip = objEventReqVO.getCompetition_zip();
              championship_area = objEventReqVO.getChampionship_area();
              status_id = objEventReqVO.getStatus_id();
			  mappingTypeIds = objEventReqVO.getMaping_type_id();
			  	if(mappingTypeIds!= null && mappingTypeIds.size()!=0){	  
                	Iterator itrMapTypeIds = mappingTypeIds.iterator();
                
	                while(itrMapTypeIds.hasNext()){
                    String mappingTypeId = (String)itrMapTypeIds.next();
                    eventSubTypes = mappingTypeId+"#"+eventSubTypes;
    	            }
				}
			}
			String areaId = "";
			String areaStr = "";
			String[] areaDetails = (String []) request.getAttribute("AREA_DETAILS");
			if(areaDetails != null){
			 areaId = areaDetails[0];
			 areaStr = areaDetails[1];
			 }
				%>
<body onload="amtFocus()">


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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
		<%
		ArrayList priceDets = (ArrayList) request.getAttribute("priceDetails");
		System.out.println("priceDets in jsp"+priceDets.size());
		String omnitempDuePri="";			
			String omitempAftDuePri="";
			String tempDuePri ="";
			String tempAftDuePri ="";
			String DueDt ="";
			String GrcDt ="";
			String priTypeName="";
		if(priceDets!=null && priceDets.size()!=0){
		 Iterator itr = priceDets.iterator();
			//int i=0;
		%>
	
				<div class="cmmnForm">
	<div class="colspan">
		<strong>Meetings: <span class="styleBoldTwo">Event Registration USEF Endorsed Competition</span></strong>
	</div>
  <div id="commonBG" class="textCommon">
  		All Competitions must complete Section One (page 1).
		Endorsed Competitions/Levels that are Recognized/USEF Endorsed
		events offering Training, Novice, Beginner Novice and Tests) must
		complete sections Two and Three (pages 2 & 3).

		<br />
			
		<%
		while(itr.hasNext()){
			String[] priList = (String[])itr.next();
			String priIssId = priList[0];
			String priIssName = priList[1];
			 DueDt = priList[2];
			 GrcDt = priList[3];
			String priId = priList[4];
			String priDueDtPrice = priList[5];
			String priAftDueDtPrice = priList[6];
			 priTypeName = priList[7];					
			%>						
			<%			
			if(priTypeName.equalsIgnoreCase("Event Registration Fees")){
			System.out.println("priTypeName in jsp 111111" + priTypeName);			
			tempDuePri = priDueDtPrice;
			System.out.println("tempDuePri in jsp" + tempDuePri); 
			tempAftDuePri = priAftDueDtPrice;			 
			System.out.println("tempAftDuePri in jsp" + tempAftDuePri);
					
			%>
			<strong>By the following date - note fee structure below:</strong>
		<%=DueDt%> (grace period to avoid the late fee is thru <%=GrcDt%>)
		<strong><%=priIssName%> </strong> Events. <br />		
			Event Registration Fee:($<%=tempDuePri%>) per event - postmark/fax/email prior or on <%=DueDt%>	
			<%
			}else if(priTypeName.equalsIgnoreCase("Omnibus Listing Fees")){
			System.out.println("priTypeName in jsp 222222" + priTypeName);
			omnitempDuePri = priDueDtPrice;
			System.out.println("omnitempDuePri in jsp" + omnitempDuePri); 
			omitempAftDuePri = priAftDueDtPrice;
			System.out.println("omitempAftDuePri in jsp" + omitempAftDuePri); 						
			%>
			<br />	
            Omnibus Listing Fee:($<%=omnitempDuePri%>) per event - postmark/fax/email prior or on <%=DueDt%>.	
			<%		
			}				
			%>
					
			<%
			//i++;
			}
			float omniDue=0;float omniAftDue=0;
			float eveRegDue=Float.parseFloat(tempDuePri);
			if(omnitempDuePri.equals(""))
				 omniDue=0;
			else 
			  omniDue =Float.parseFloat(omnitempDuePri);
			float totalFees= eveRegDue+omniDue;
			System.out.println("totalFees &&& in jsp" + totalFees);	
			float eveRegAftDue=Float.parseFloat(tempAftDuePri);
			if(omitempAftDuePri.equals(""))
			    omniAftDue=0;
			else
               omniAftDue=Float.parseFloat(omitempAftDuePri);
			float lateFees= eveRegAftDue+omniAftDue;	
			System.out.println("lateFees &&& in jsp" + lateFees);				
			float dddd= totalFees +lateFees;
			System.out.println("dddd &&& in jsp" + dddd);		
			%>			
			<br />
			
<strong>Fees:</strong>	$<%=totalFees%>.
		
			<br />	
<strong>Late fees:</strong>	$<%=lateFees%> per event - postmark/fax/email after <%=GrcDt%>.			
			<%								
			}		
			%>
			</div>
	<%
	String sessionInvoiceId = "1";
        session.setAttribute("sessionInvoiceId", sessionInvoiceId);	
        String eventId = (String) request.getAttribute("eventId");
		
		//eventId
		if(eventId==null){
			response.sendRedirect("index.jsp");
		}
		
	%>
	
<form name="frmEvntOrgRenewal"  method="post" class="formcss" action="EventOrgRenewal.do" onSubmit="return onEvnRenCheck();" >
<input type="hidden" name="eventProcess" value="insert">
<input type="hidden" name="eventId" value="<%=eventId%>" />
 <div class="row">
		Fields mark with asterisk(<span class="asterisk">*</span>) are required.
	</div>

  
	<div class="rowHead">
		Section One:
	</div>
			<div class="row">
				<span class="label">Name of Competition:</span>
				<span class="formX">
				<input type="text" name="competitionName" id="competitionName"  class="textboxOne" size="20" value="<%=event_title%>" readonly="readonly"/></span>
				
			</div>
			<div class="row">
				<span class="label">Location of Competition:</span>
				<span class="formX"><%=competition_location%></span>
				
			</div>

			<div class="row">
				<span class="label">Country:</span>
				<span class="formX">USA</span>
				
				
			</div>

			<div class="row">
				<span class="label">State:</span><span class="formX">
				<%
										ArrayList stateDetails = (ArrayList)request.getAttribute("stateDetails");
										if(stateDetails!=null && stateDetails.size()!=0){
										Iterator itr = stateDetails.iterator();
                
                						while(itr.hasNext()){
                						String[] stateDet = (String[])itr.next();
										String stateId = stateDet[0];
										String stateName = stateDet[1];
										String stateCode = stateDet[2];
										if(competition_state!=null && competition_state.equalsIgnoreCase(stateId)){
										%>
										<%=stateName%>
										<%}}}%></span>
				
				

			</div>


			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><%=competition_city%></span>
				
			</div>
			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><%=competition_zip%></span>
				
			</div>
			<div class="row">
				<span class="label">Date of Competition:</span>
				<span class="formX">
				<input type="text" name="competitionDate" id="competitionDate" readonly="true" class="textboxOne" size="15" value="<%=dateCheck(eventBeginDate)%>"/>
				</span>
			</div>
			<div class="row">
			<span class="label">Area Championship:</span> 
			<span class="formX"><%=areaStr%></span>
			
			</div>
			<div class="row">
				<span class="label"> Event Organiser Name:</span>
				<span class="formX">
				<input type="text"  readonly="true"  name="rName" value="<%=first_name%>" size="20" />&nbsp;
				 No.: <input type="text" class="textboxOne" name="hlcNo"  size="10" value="<%=(String)session.getAttribute("memberId")%>" readonly="true" /></span>
			</div>
			<div class="row">
				<span class="label">Street:</span>
				<span class="formX"><input type="text"  class="textboxOne" value="<%=address1%>"  readonly="true" name=rStreet size="20" /></span>
			</div>
			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><input type="text" class="textboxOne" value="<%=city%>" readonly="true" name=rCity size="15" /></span>
			</div>
			<div class="row">
				<span class="label">State:</span>
				<span class="formX"><input type="text" class="textboxOne" value="<%=state%>" readonly="true" name=rState size="20" /></span>
			</div>
			<div class="row">
				<span class="label">Country:</span>
				<span class="formX"><input type="text" class="textboxOne" value="<%=country%>" readonly="true" name=rState size="20" /></span>
			</div>
			<div class="row">
			<span class="label">Zipcode:</span><span class="formX">
			<input type="text" class="textboxOne" readonly="true" value="<%=zip%>" name=rZipcode size="8" />
			</span></div>
				<div class="row">
				<span class="label">Phone:</span>
				<span class="formX"><input type="text" class="textboxOne" value="<%=phone_no%>" readonly="true" name=rPhone size="15" /></span>
			</div>
			<div class="row">
				<span class="label">Cell:</span>
				<span class="formX"><input type="text" class="textboxOne" value="<%=mobile_no%>" readonly="true" name=rCell size="15" /></span>
			</div>
			<div class="row">
				<span class="label">Email:</span>
				<span class="formX"><input type="text" class="textboxOne"value="<%=email_id%>" readonly="true" name=rEmail size="20" /></span>
			</div>
	<div class="rowHead">
		Section Two:
	</div>
			<div id="commonBG" class="textCommon">
				<strong>USEF defines Competition Management as follows:</strong>  Shall be the party who enters
				 into the agreement with USEF and is therefore financially and otherwise responsible
				 (i.e., compliance with all USEF rules, policies and procedures) to USEF for the
				 licensed competition.
			</div>

			<div class="row">
				<span class="label">Name of Competition Management:</span>
				<span class="formX"><input type="text" name="comManagementName" id="comManagementName" class="textboxOne" size="20" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Address of principal place of business:</span>
				<span class="formX"><input type="text" name="comManagementAddress" id="comManagementAddress" class="textboxOne" size="30" />
				<span class="asterisk">*</span></span>
			</div>

			<div class="row">
										<span class="label">Country:</span>
										<span class="formX">
<select name="comManagementCountry" id="comManagementCountry" class="selectboxOne" style="width:200px" width="200"  
onChange="FillState(document.frmEvntOrgRenewal.comManagementCountry, document.frmEvntOrgRenewal.comManagementState, '')">
										</select>
										<span class="asterisk">*</span></span>
	</div>
									<div class="row">
						<span class="label">State:</span>
										<span class="formX">
										<select name="comManagementState" id="comManagementState" class="selectboxOne" style="width:200px" width="200">

										</select>
										<span class="asterisk">*</span></span>
									</div>

			<div class="row">
				<span class="label">City:</span>
				<span class="formX"><input type="text" name="comManagementCity" id="comManagementCity" class="textboxOne" size="15" />
				<span class="asterisk">*</span></span>
			</div>

			<div class="row">
				<span class="label">Zipcode:</span>
				<span class="formX"><input type="text" name="comManagementZip"  id="comManagementZip"  class="textboxOne" size="8" />
				<span class="asterisk">*</span></span>
			</div>

			<div class="row">
				<span class="label">Telephone Number:</span>
				<span class="formX"><input type="text" name="comManagementPhone" id="comManagementPhone" class="textboxOne" size="15" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Fax Number:</span>
				<span class="formX"><input type="text" name="comManagementFax" id="comManagementFax" class="textboxOne" size="15" />
				</span>
			</div>
			<div class="colspan">
				<strong>Competition Manager/Organizer Information</strong>
			</div>
			<div class="row">
				<span class="label">Competition Manager/Organizer Name:</span>
				<span class="formX"><input type="text" name="managerName" id="managerName" class="textboxOne" size="30" />
				<span class="asterisk">*</span></span>
			</div>
			<div class="row">
				<span class="label">Check box if USEF member:</span>
				<span class="formX">
				<input type="checkbox" name="USEFMgrCheckValue" id="USEFMgrCheckValue" size="15" class="threepxfix" onclick="enabDisab('USEFMgrCheckValue','managerUsefMemberId');"/></span>
			</div>
			<div class="row">
				<span class="label">USEF Membership No.:</span>
				<span class="formX"><input type="text" name="managerUsefMemberId" id="managerUsefMemberId"  class="textboxOne" disabled="disabled"  size="30"/>
				</span>
			</div>
			<div class="row">
				<span class="label">Membership No.:</span>
				<span class="formX"><input type="text" name="managerUseaMemberId"  id="managerUseaMemberId"  class="textboxOne" size="30"  onblur="MemberDetails();"/>
				<span class="asterisk">*</span></span>
			</div>
			<div class="colspan">
			
				<strong>Member Secretary Information **</strong>
			</div>
			<div id="commonBG" class="textCommon">
				<strong>**</strong> A Secretary's information is only required if neither the Manager/Organizer nor Secretary is a USEF Senior Member.
			</div>
			<div class="row">
				<span class="label">Member Secretary Name:</span>
				<span class="formX"><input type="text" name="secretaryName"  id="secretaryName"  class="textboxOne" size="30" />
				</span>
			</div>
			<div class="row">
				<span class="label">Check box if USEF member:</span>
				<span class="formX"><input type="checkbox" id="USEFSecretaryCheckValue" name="USEFSecretaryCheckValue" size="15" class="threepxfix" onclick="enabDisab('USEFSecretaryCheckValue','secretaryUsefMemberId');"/></span>
			</div>
			<div class="row">
				<span class="label">USEF Membership No.:</span>
				<span class="formX"><input type="text" name="secretaryUsefMemberId" id="secretaryUsefMemberId" class="textboxOne" disabled="disabled"  size="30" />
				</span>
			</div>
		
	<div class="rowHead">
		Payments/General Information
	</div>
	<%
	    int feeAmount = 400;
		Date first = new Date("05/10/2006");
		Date second = new Date();
        long msPerDay = 1000 * 60 * 60 * 24;
        long diff =
            (first.getTime() / msPerDay) - (second.getTime() / msPerDay);
        Long convertLong = new Long(diff);
        //return convertLong.intValue();
      
	  int datDiff = convertLong.intValue();
	   //out.println(datDiff);
	  if(datDiff <= 1){
	  	feeAmount = 525;
	  }
	 String amt=(String)request.getAttribute("price"); 
	%>
	<div class="row"> <span class="label">Total Amount:</span> <span class="formX">
	  <input  type="text" name="amount" id="amount" class="textboxOne" size="10" readonly="true" value="<%=amt%>" />
    <strong>$</strong><span class="asterisk">*</span></span>  </div>
			<div class="row">
			  <span class="floatLeft">
			  <input type="radio" size="10" name="r11"  value="check" onclick="checkClear(),switchDiv('checkEncl'), showHideRadio('r11','chargeCard','check');" >
			  </span>
			  <span class="floatLeft">
				  Check enclosed.
			  </span>
			  <span class="floatLeft">
			  <input type="radio" size="10" name="r11" value="card" checked="checked" onclick="cardClear(),switchDiv('chargeCard'), showHideRadio('r11','checkEncl','card');" ></span>
			  <span class="floatLeft">
				  Please charge my card.
			  </span>
			</div>

					<div id="checkEncl">
							<div class="colspan">
								<strong>Check Details:</strong>
							</div>
							
							<div class="tblDescrp">
																				<p>
																					If paying by check, please mail your payment to:  <br /><br />
																					
																					<strong>
																					<br />
																					Event Organizer Registration
																					<br />
																					525 Old Waterford Road, NW
																					<br />
																					Leesburg, VA 20176
																					</strong><br /><br />
																					
																					<strong>Note:</strong> Your registration status will be pending until check is processed. <br /><br />
																				</p>
					  </div>
							
							<div class="row">
								<span class="label">Check No:</span>
								<span class="formX"><input type="text" name="checkNumber"  id="checkNumber" class="textboxOne" size="16" />
								<span class="asterisk">*</span></span>
							</div>
							
							<%
																		    String chkDat2="";
																		
																			java.util.Calendar c6 = java.util.Calendar.getInstance();
																			int dyChk = c6.get(Calendar.DATE);
																			int mnth = c6.get(Calendar.MONTH);
																			
																			int mnthChk = mnth+1;
																			int yrChk = c6.get(Calendar.YEAR);
																			
																			if(mnthChk<10)
																			{
																				chkDat2 = "0"+mnthChk+"/"+dyChk+"/"+yrChk;
																			}
																			else
																			{
																				
																				chkDat2 = mnthChk+"/"+dyChk+"/"+yrChk;
																			}
							%>
							
					  <div class="row"> <span class="label">Check Date:</span> <span class="formX">
					    <input type="text" name="checkDate"  id="checkDate" value="<%=chkDat2%>" readonly="true" class="textboxOne" size="10" /><a href="javascript:cal2.popup();">
					 <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
					</a>
				      <span class="asterisk">*</span>
					  </span>						</div>
							<div class="row">
								<span class="label">Bank Name:</span>
								<span class="formX"><input type="text" name="bankName" id="bankName" class="textboxOne" size="30" />
								<span class="asterisk">*</span></span>
							</div>
							
							<div class="row">
									<span class="label">Name on Check:</span>
									<span class="formX">
									<input name="nameOnchk" id="nameOnchk" type="text" class="textboxOne"  size="30" maxlength="25" /> <span class="asterisk">*</span>	</span>
							</div>
							
					</div>

					<div id="chargeCard">
							<div class="colspan">
								<strong>Card Details:</strong>
							</div>
							<div class="row">
								<span class="label">Card Type:</span>
								<span class="formX">
								<select name="ccType" id="ccType" class="selectboxOne">
								  <option value="" selected="selected" >Select One</option>
								  <option value="Visa">Visa</option>
								  <option value="MasterCard">Master Card</option>
								  <option value="AmEx">AmEx</option>
								</select>
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Card No:</span>
								<span class="formX"><input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="20" />
								<span class="asterisk">*</span></span>
							</div>

							<div class="row">
								<span class="label">CVV No.:</span>
								<span class="formX"><input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
								</span>
							</div>
							<div class="row">
								<span class="label">Print Name On Card:</span>
								<span class="formX"><input type="text" name="ccName" id="ccName" class="textboxOne" size="15"/>
								<span class="asterisk">*</span></span>
							</div>
							<div class="row">
								<span class="label">Expiry Date:</span>
								<span class="formX">
									<select name="ccExpMonth" id="ccExpMonth" class="selectboxOne">
									  <option value="" selected="selected">Month</option>
									  <option value="01">January</option>
									  <option value="02">February</option>
									  <option value="03">March</option>
									  <option value="04">April</option>
									  <option value="05">May</option>
									  <option value="06">June</option>
									  <option value="07">July</option>
									  <option value="08">August</option>
									  <option value="09">September</option>
									  <option value="10">October</option>
									  <option value="11">November</option>
									  <option value="12">December</option>
									</select>
									<select name="ccExpYear" id="ccExpYear" class="selectboxOne">
										  <option value="" selected="selected" >Year</option>
										  <option  value="2006">2006</option>
										  <option  value="2007">2007</option>
										  <option  value="2008">2008</option>
										  <option  value="2009">2009</option>
										  <option  value="2010">2010</option>
										  <option  value="2011">2011</option>
										  <option  value="2012">2012</option>
										  <option  value="2013">2013</option>
										  <option  value="2014">2014</option>
										  <option  value="2015">2015</option>
                                                                                   <option  value="2016">2016</option>
                                                                                   <option  value="2017">2017</option>
                                                                                   <option  value="2018">2018</option>
                                                                                   <option  value="2019">2019</option>
                                                                                   <option  value="2020">2020</option>
								</select>
								<span class="asterisk">*</span></span>
							</div>
					</div>
					
		<div class="row">
		<span class="label">&nbsp;</span>
		<span class="formX"><input type="submit" name="submit" value="Register Now" class="gradBtn"/></span>
		</div>
		<br/>
														<div id="cvvImg" class="alignCenter">
															<span class="label">&nbsp;</span>
														   <span class="formX">
																<img src="images/cvv_graphic.jpg" />																				
														  </span>
														</div>	
			
	<!--
	<div class="row">
		<span class="label">Comments:</span>
		<span class="formX"><textarea cols="25" rows="8">Go ahead - write something...</textarea></span>
	</div>
	-->
	<div id="spacer">&nbsp;</div>

</form>
</div>
				
				
				
				
				
				
			<!-- CONTENTS END HERE -->		
			</td>
		  </tr>
	  </table>
	
	</td>  </tr>
  <tr>
    <td class="footerBg">
		<!-- FOOTER STARTS HERE -->
			<%@ include file = "../../include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>


</body>
<script language="javascript">
	var cal2 = new calendar2(document.forms['frmEvntOrgRenewal'].elements['checkDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
	
</script>
<script>
	FillCountry(document.frmEvntOrgRenewal.competitionCountry, document.frmEvntOrgRenewal.competitionState, '---Select---');
	FillState(document.frmEvntOrgRenewal.competitionCountry, document.frmEvntOrgRenewal.competitionState, '');
</script>
<script>
	FillCountry(document.frmEvntOrgRenewal.comManagementCountry, document.frmEvntOrgRenewal.comManagementState, '---Select---');
	FillState(document.frmEvntOrgRenewal.comManagementCountry, document.frmEvntOrgRenewal.comManagementState, '');
</script>
</html>
