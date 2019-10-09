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
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import ="com.hlcmro.util.HLCEventRequestVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/frmEditEventRequestReg.js" type="text/javascript"></script>

<title><%=(String)session.getAttribute("title")%></title>
</head>
<%! 

String  nullCheck(String fieldName){
	String retValue = "<b>N/A</b>";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%! 				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sd1 = new SimpleDateFormat("MM/dd/yyyy");
					
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
<body onload="HLCMemberDetails();">
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

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">


  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">View Event Registration  </span></td>
  </tr>
 
  <tr>
  	<td>
	
<form name="frmEditEventRequestReg" id="frmEditEventRequestReg" action="./eventRegList.do" method="post"   >
	


	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" id="tblGrid">
				<tr>
					<td colspan="2" class="tblRowHead">View Event Details:</td>
				</tr>
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
				String issNm = "";
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
			  issNm = objEventReqVO.getIssueName();
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
				<tr>
				     <td class="tableLeft">Event Title:</td>
				     <td class="tableRight">
					 <%=nullCheck(event_title)%>
				 	
				 	 </td>
		     	</tr>
				<tr>
					<td colspan="2" class="tblRowHead">Event Location:</td>
				</tr>
				<tr>
				     <td class="tableLeft">Country:</td>
				     <td class="tableRight"><b><strong>USA</strong></b></td>
		     	</tr>
				<tr>
				     <td class="tableLeft">State :</td>
				     <td class="tableRight">
														
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
										<%}}}%>
									 
				 	 
					 </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight"><%=nullCheck(competition_city)%>
				     </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">Location:</td>
				     <td class="tableRight"><%=nullCheck(competition_location)%>
				     </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">ZipCode:</td>
				     <td class="tableRight">
					 <%=nullCheck(competition_zip)%>
				     </td>
		     	</tr>
				<tr>
				     <td class="tableLeft">Area:</td>
				     <td class="tableRight"><%=nullCheck(areaStr)%></td>
				</tr>
				<tr> 
            <td class="tableLeft">Begin Date: </td>
            <td class="tableRight"><%=dateFormat(eventBeginDate.toString())%></td>
          		</tr>
				<tr> 
            <td class="tableLeft">End date: </td>
            <td class="tableRight"><%=dateFormat(eventEndDate.toString())%></td>
          		</tr>
		  		<tr>
				     <td class="tableLeft">Organizer ID:</td>
				     <td class="tableRight">
					 <input name="orgnaizerId" type="text" id="orgnaizerId" value="<%=organizer_id%>" class="textboxOne" size="20" maxlength="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" onblur="HLCMemberDetails();"/>
				 	 
				</tr>
				<tr>
				     <td class="tableLeft"> Name:</td>
				     <td class="tableRight">
					 <input name="name" type="text" id="name" class="textboxOne" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Telephone:</td>
				     <td class="tableRight">
					 <input name="phone" type="text" id="phone" class="textboxOne" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Address:</td>
				     <td class="tableRight">
					 <input name="address" type="text" id="address" class="textboxOne" size="30" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="true"/></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight">
					 <input name="city" type="text" id="city" class="textboxOne" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="true"/></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">Fax:</td>
				     <td class="tableRight">
					 <input name="fax" type="text" id="fax" class="textboxOne" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Email:</td>
				     <td class="tableRight">
					 <input name="email" type="text" id="email" class="textboxOne" size="20" style="background-color:#F4F4F4; font-weight:bold; border:0px;" readonly="true"/></td>
				</tr>
<tr> 
            <td class="tableLeft">Season: </td>
            <td class="tableRight"><%=issNm%></td>
          		</tr>
				<tr>
					<td colspan="2" class="tblRowHead">Event Types and Levels:</td>
				</tr>
				<%
										Vector eventTypeDetails = (Vector)request.getAttribute("eventTypeDetails");
										HashMap eventTypeMap = (HashMap)request.getAttribute("eventTypeMap");
										int levelCount =0;
										if(eventTypeDetails!=null && eventTypeDetails.size()!=0){
										Enumeration it = eventTypeDetails.elements();
										while(it.hasMoreElements()){
										String[] eventDet =(String[])it.nextElement();
										String eventTypeId = eventDet[0];
										String eventTypeName= eventDet[1];
									%>
				
									<%  String tmpVal="";
										ArrayList subLevelMap = (ArrayList)eventTypeMap.get(eventTypeId);
					                    Iterator itrSub = subLevelMap.iterator();
                    					while(itrSub.hasNext()){
				                        String[] subEntry = (String[])itrSub.next();
                				        String mapId = subEntry[0];
				                        String levelId = subEntry[1];
                				        String levelCode = subEntry[2];
				                        String levelName = subEntry[3]; 
										levelCount = levelCount + 1;
										String cbxName = "eventLevels"+levelCount;
										boolean chkStatus = false;
										if(eventSubTypes.contains(mapId)){
										if(tmpVal==""){
										tmpVal = levelCode;	
										}else{	
										tmpVal = tmpVal + ","+ levelCode;	
										}														
										%>
				
					 			<%}
							  }%>
							 
							<tr>
							<td class="tableLeft" ><strong><%=eventTypeName%></strong></td>
							<%if(tmpVal!=""){%> 
							<td class="tableRight" ><%=tmpVal%></td>
							<%}else{%>
							<td class="tableRight" >N/A</td>
							</tr>
				            <%}%> 
							<%}
						}  %>
									 
				 	 <input type="hidden" name="levelCount" id="levelCount" value="<%=levelCount%>" />
					<input type="hidden" name="eventId" id="eventId" value="<%=event_id%>" />					
				 
			    <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					
					<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"  />					</td>
				</tr>
			</table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
