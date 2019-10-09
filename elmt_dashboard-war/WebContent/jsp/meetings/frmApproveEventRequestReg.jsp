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

<script>
var arHttpRequest;

function HLCMemberDetails()
{
	
	if(document.frmAppEventRequestReg.orgnaizerId.value!="" && document.frmAppEventRequestReg.orgnaizerId.value.indexOf(" ")!=0)
	{
    var memberid=document.frmAppEventRequestReg.orgnaizerId.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
            
        var url = "./RiderInfoAjaxAction.do?memberid="+memberid; 

        if (window.ActiveXObject) 
        { 
            arHttpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
           
        } 
        else if (window.XMLHttpRequest) 
        { 
            arHttpRequest = new XMLHttpRequest(); 
        } 
     
        arHttpRequest.open("POST", url, true); 
        
        arHttpRequest.onreadystatechange = function() {membStatus(); } ; 
        arHttpRequest.send(null); 
   } 
	}
   
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function membStatus() 
    { 
   
        if (arHttpRequest.readyState == 4) 
        { 
            if(arHttpRequest.status == 200) 
            { 
                //get the XML send by the servlet 
                 var arnameXML = arHttpRequest.responseXML.getElementsByTagName("memberstatus")[0]; 
                 var arnameText = arnameXML.childNodes[0].nodeValue; 
				
				//alert
				
                if(arnameText=="false")
                {    
					alert("Member Id Doesn't Exists!"); 
					document.frmAppEventRequestReg.orgnaizerId.value="";
					document.frmAppEventRequestReg.orgnaizerId.focus();
					
					document.frmAppEventRequestReg.name.value="";
					document.frmAppEventRequestReg.address.value="";
					document.frmAppEventRequestReg.city.value="";
					document.frmAppEventRequestReg.phone.value="";
					document.frmAppEventRequestReg.fax.value="";
					document.frmAppEventRequestReg.email.value="";
										
					return false;
                }
				else
				{					
					document.frmAppEventRequestReg.name.value="";
					document.frmAppEventRequestReg.address.value="";
					document.frmAppEventRequestReg.city.value="";
					document.frmAppEventRequestReg.phone.value="";
					document.frmAppEventRequestReg.fax.value="";
					document.frmAppEventRequestReg.email.value="";
										
					details();
				}

            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequest.status +":"+ httpRequest.statusText); 
            } 
        } 
    } 
	
//----------------------------------------------

var httpRequestDet;

function details()
{

if(document.frmAppEventRequestReg.orgnaizerId.value!="" && document.frmAppEventRequestReg.orgnaizerId.value.indexOf(' ')!=0)
	{

   rid=document.frmAppEventRequestReg.orgnaizerId.value;

   /** 
    * This method is called when the author is selected 
    * It creates XMLHttpRequest object to communicate with the  
    * servlet  
    */ 
        url= "RiderInfoAjaxAction.do?rid="+rid; 

        if (window.ActiveXObject) 
        { 
            httpRequestDet = new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
        else if (window.XMLHttpRequest) 
        { 
            httpRequestDet = new XMLHttpRequest(); 
        } 
     
        httpRequestDet.open("GET", url, true); 
        
        httpRequestDet.onreadystatechange =processRequest; 
        httpRequestDet.send(null); 
   } 
   }
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
    function processRequest() 
    { 
   
        if (httpRequestDet.readyState == 4) 
        { 
            if(httpRequestDet.status == 200) 
            { 
                //get the XML send by the servlet 
                 var salutationXML = httpRequestDet.responseXML.getElementsByTagName("salutation")[0]; 
                 var salutationText = salutationXML.childNodes[0].nodeValue; 
     
                //Update the HTML 
                updateDet(salutationXML); 
            } 
            else 
            { 
                alert("Error loading page\n"+ httpRequestDet.status +":"+ httpRequestDet.statusText); 
            } 
        } 
    } 
        
   /** 
    * This function parses the XML and updates the  
    * HTML DOM by creating a new text node is not present 
    * or replacing the existing text node. 
    */ 
    function updateDet(salutationXML) 
    { 

        //The node valuse will give actual data 
        var salutationText = salutationXML.childNodes[0].nodeValue; 
        var rdetails=new Array();
        rdetails=null;
        rdetails=salutationText.split("#");
      
	  // alert("rdetails[6] :"+rdetails[6]);
	  		
	    if(rdetails[1]==NaN || rdetails[1]==undefined || rdetails[1]==null || rdetails[1]=="null")
        {
        document.frmAppEventRequestReg.name.value="NA";
        }else
        document.frmAppEventRequestReg.name.value=rdetails[1];

        if(rdetails[5]==NaN || rdetails[5]==undefined || rdetails[5]==null || rdetails[5].length==0 || rdetails[5]=="null")
        {
        document.frmAppEventRequestReg.address.value="NA";
        }else
        document.frmAppEventRequestReg.address.value=rdetails[5];

        if(rdetails[7]==NaN || rdetails[7]==undefined || rdetails[7]==null || rdetails[7]=="null")
        {
        document.frmAppEventRequestReg.city .value="NA";
        }else
        document.frmAppEventRequestReg.city .value=rdetails[7]; 

        if(rdetails[10]==NaN || rdetails[10]==undefined || rdetails[10]==null || rdetails[10]=="null")
        {
        document.frmAppEventRequestReg.phone.value="NA";
        }else
        document.frmAppEventRequestReg.phone.value=rdetails[10];

        if(rdetails[12]==NaN || rdetails[12]==undefined || rdetails[12]==null || rdetails[12]=="null")
        {
        document.frmAppEventRequestReg.email.value="NA";
        }else
        document.frmAppEventRequestReg.email.value=rdetails[12];
		
		if(rdetails[13]==NaN || rdetails[13]==undefined || rdetails[13]==null || rdetails[13]=="null" || rdetails[13]=="")
        {
        document.frmAppEventRequestReg.fax.value="NA";
        }else
        document.frmAppEventRequestReg.fax.value=rdetails[13];

    } 

function myvalidate(){
	
	if(document.frmAppEventRequestReg.selStatus.selectedIndex ==0){
	alert("Select any of the Status");
	document.frmAppEventRequestReg.selStatus.focus();
	return false;
	}
		
return true;
}
</script>
<title><%=(String)session.getAttribute("title")%></title>
</head>

<body onload="HLCMemberDetails();">

<%! 
String nullCheck(String fieldName){
	String retValue = "N/A";
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
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Request for Event Registration  </span></td>
  </tr>
 
  <tr>
  	<td>
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout" id="tblGrid">
				<tr>
					<td colspan="2" class="tblRowHead">Approve An Event:</td>
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
				<form name="frmAppEventRequestReg" id="frmAppEventRequestReg" action="./eventRegList.do" method="post" onsubmit="return myvalidate();"  >
	<input type="hidden" name="cmd" value="approveEventRequestReg">
<input type="hidden" name="eventId" id="eventId" value="<%=event_id%>" />	

				<tr>
				     <td class="tableLeft">Event Title:</td>
				     <td class="tableRight"><%=event_title%>		
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
																
										<%boolean stateStatus = true;
										ArrayList stateDetails = (ArrayList)request.getAttribute("stateDetails");
										if(stateDetails!=null && stateDetails.size()!=0){
										Iterator itr = stateDetails.iterator();
                
                						while(itr.hasNext()){
                						String[] stateDet = (String[])itr.next();
										String stateId = stateDet[0];
										String stateName = stateDet[1];
										String stateCode = stateDet[2];
										if(competition_state!=null && competition_state.equalsIgnoreCase(stateId)){
										stateStatus = false;
										%>										
										<%=stateName%>
										<%}								
							  }
							} if(stateStatus){
							  %><%="N/A"%>
							  <%}%>
									 
				 	 
					 </td>
		     	</tr>
				
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight"><%=competition_city%></td>
		     	</tr>
				<tr>
				     <td class="tableLeft">ZipCode:</td>
				     <td class="tableRight"><%=competition_zip%></td>
		     	</tr>
				<tr>
				     <td class="tableLeft">Area:</td>
				     <td class="tableRight"><%=areaStr%></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">Location:</td>
				     <td class="tableRight"><%=nullCheck(competition_location)%></td>
					 
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
					 <input name="orgnaizerId" type="text" id="orgnaizerId" style="background-color:#F4F4F4; font-weight:bold; border:0px;" value="<%=organizer_id%>" class="textboxOne" size="20" maxlength="20" readonly="true" />
				 	 </td>
				</tr>
				<tr>
				     <td class="tableLeft"> Name:</td>
				     <td class="tableRight">
					 <input name="name" type="text" id="name" style="background-color:#F4F4F4; font-weight:bold; border:0px;" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Telephone:</td>
				     <td class="tableRight">
					 <input name="phone" type="text" id="phone" style="background-color:#F4F4F4; font-weight:bold; border:0px;" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Address:</td>
				     <td class="tableRight">
					 <input name="address" type="text" id="address" style="background-color:#F4F4F4; font-weight:bold; border:0px;" class="textboxOne" size="30" readonly="true"/></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">City:</td>
				     <td class="tableRight">
					 <input name="city" type="text" id="city" style="background-color:#F4F4F4; font-weight:bold; border:0px;" class="textboxOne" size="20" readonly="true"/></td>
					 
		     	</tr>
				<tr>
				     <td class="tableLeft">Fax:</td>
				     <td class="tableRight">
					 <input name="fax" type="text" id="fax" style="background-color:#F4F4F4; font-weight:bold; border:0px;" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
				<tr>
				     <td class="tableLeft">Email:</td>
				     <td class="tableRight">
					 <input name="email" type="text" id="email" style="background-color:#F4F4F4; font-weight:bold; border:0px;" class="textboxOne" size="20" readonly="true"/></td>
				</tr>
<tr> 
            <td class="tableLeft">Season: </td>
            <td class="tableRight"><%=issNm%></td>
          		</tr>
				<tr>
					<td colspan="2" class="tblRowHead">Event Types & Event Levels:</td>
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
                                                                                if(subLevelMap!=null && subLevelMap.size()!=0)
											{
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
							 } }%>
							 
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

					<tr>
				     <td class="tableLeft">Approval Status:</td>
				     <td class="tableRight"><select name="selStatus" id="selStatus" class="selectboxOne" > 
										<option value="" selected="selected">Select One</option>
								<%	String [] statStr = {"Approved","Pending","Rejected"};
								for(int i = 0 ; i< 3 ;i++){
								if(status_id!= null && status_id.equalsIgnoreCase(statStr[i])){%>
													<option  value="<%=statStr[i]%>" selected="selected"><%=statStr[i]%></option>
												<%
												}
												else{
												%>
													<option  value="<%=statStr[i]%>"><%=statStr[i]%></option>
												<%	}
								}								
                                 %>
                                  </select><span class="asterisk">*</span>
					 </td>
				</tr>
				 
			    <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" value="Approve" class="gradBtn"  />
					&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="location.href='eventRegList.do?cmd=eventReg'"/>		</td>
				</tr>
				</form>
			</table>

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
