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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcutil.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
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
				
<table border="0" width="100%" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		Online Entries : <span class="styleBoldTwo"> View Competition Results </span></td>
  </tr>
  <tr>
  	<td>
			<table border="0" width="100%" cellpadding="0" align="center" cellspacing="0" class="formLayout">
   <%
		HLCCompResultVO compResVO = new HLCCompResultVO();
		
		compResVO = (HLCCompResultVO)request.getAttribute("objRegVO");
		System.out.println("compVO in jsp"+compResVO);
					String compResultId = compResVO.getCompResultId();
					System.out.println("compResultId in jsp"+compResultId);
					String eveId = compResVO.getEventId();
					System.out.println("eveId in jsp"+eveId);
					String eveTitle = compResVO.getEventTitle();
					String eveType = compResVO.getEventTypeName();
					String riderMemId = compResVO.getRiderMemberId();
					String rFName = compResVO.getRiderFirstName();
					String rLName = compResVO.getRiderLastName();
					String horseId = compResVO.getHorseMemberId();
					String hName = compResVO.getHorseName();
					String divName = compResVO.getDivisionName();
					String eveLevel = compResVO.getEventLevel();
					String subDiv = compResVO.getEventSubDivision();
					String pinneyNum = compResVO.getPinneyNumber();
					String dressPenal = compResVO.getDressagePenal();
					String dressPlace = compResVO.getDressagePlace();
					String xc_Ph_jmp = compResVO.getXc_phase_jmpPenal();
					String xc_Ph_elapTime = compResVO.getXc_phase_elapsTime();
					String xc_Ph_time = compResVO.getXc_phase_timePenal();
					String shw_Jmp_Time = compResVO.getShow_jmp_timePenal();
					String shw_Jmp_Jmp = compResVO.getShow_jmp_jmpPenal();
					String dtPt = compResVO.getToDatePts();
					String dtPlace = compResVO.getToDatePlace();
					String dangerRidPenal = compResVO.getDangerRidPenal();
					String finPoint = compResVO.getFinalPoints();
					String finPlace = compResVO.getFinalPlace();
					String firstInspec = compResVO.getFirstInspec();
					String lastInspec = compResVO.getLastInspec();
					String phDInspec = compResVO.getPhase_DInspec();
					String roadTrackA = compResVO.getRdTrackA();
					String roadTrackC = compResVO.getRdTrackC();
					String steepleJmpPenal = compResVO.getSteepleCh_jmpPenal();
					String steepleTmPenal = compResVO.getSteepleCh_timePenal();
					String hlcPoints = compResVO.getUseaPoints();
					boolean excepStatus = compResVO.isExpectationStatus();
					
					
				
				
  %>
			
				  <tr>
					<td colspan="2" class="tblRowHead">View Competition Result Details: </td>
				  </tr>
				  
				 
				 
				   <tr>
				     <td class="tableLeft">Event ID: </td>
				     <td class="tableRight"><%=nullCheck(eveId)%></td>
		      </tr>
			   <tr>
					<td width="55%" class="tableLeft">Event Type:</td>
					<td width="45%" class="tableRight"><%=nullCheck(eveType)%></td>
				  </tr>	
			   <tr>
					<td width="55%" class="tableLeft">Event Title:</td>
					<td width="45%" class="tableRight"><%=nullCheck(eveTitle)%></td>
				  </tr>
			 			  
			  <tr>
				     <td class="tableLeft">Event Level : </td>
				     <td class="tableRight"><%=nullCheck(eveLevel)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Event Division : </td>
				     <td class="tableRight"><%=nullCheck(divName)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Event Sub Division : </td>
				     <td class="tableRight"><%=nullCheck(subDiv)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Horse ID : </td>
				     <td class="tableRight"><%=nullCheck(horseId)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Horse Name : </td>
				     <td class="tableRight"><%=nullCheck(hName)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Rider ID : </td>
				     <td class="tableRight"><%=nullCheck(riderMemId)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Rider Name : </td>
				     <td class="tableRight"><%=nullCheck(rFName+" "+rLName)%></td>
		      </tr>

              <tr>
				     <td class="tableLeft">Pinney Number : </td>
				     <td class="tableRight"><%=nullCheck(pinneyNum)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Dressage Penalites: </td>
				     <td class="tableRight"><%=nullCheck(dressPenal)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Dressage Place: </td>
				     <td class="tableRight"><%=nullCheck(dressPlace)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">XC Phase Jump Penalities: </td>
				     <td class="tableRight"><%=nullCheck(xc_Ph_jmp)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">XC Phase Elapsed Time: </td>
				     <td class="tableRight"><%=nullCheck(xc_Ph_elapTime)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">XC Phase Time Penalities: </td>
				     <td class="tableRight"><%=nullCheck(xc_Ph_time)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Show Jump Time Penalities: </td>
				     <td class="tableRight"><%=nullCheck(shw_Jmp_Time)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">Show Jump Jump Penalities: </td>
				     <td class="tableRight"><%=nullCheck(shw_Jmp_Jmp)%></td>
		      </tr>
			  <tr>
				     <td class="tableLeft">To Date Points: </td>
				     <td class="tableRight"><%=nullCheck(dtPt)%></td>
		      </tr>
			   <tr>
				     <td class="tableLeft">To Date Place: </td>
				     <td class="tableRight"><%=nullCheck(dtPlace)%></td>
		      </tr>
			   <tr>
				     <td class="tableLeft">Dangerous Riding Penalities: </td>
				     <td class="tableRight"><%=nullCheck(dangerRidPenal)%></td>
		      </tr>
			   <tr>
				     <td class="tableLeft">To Date Points: </td>
				     <td class="tableRight"><%=nullCheck(pinneyNum)%></td>
		      </tr>
			   <tr>
				     <td class="tableLeft">Final Points: </td>
				     <td class="tableRight"><%=nullCheck(finPoint)%></td>
		      </tr>
			   <tr>
				     <td class="tableLeft">Final Place: </td>
				     <td class="tableRight"><%=nullCheck(finPlace)%></td>
		      </tr>
			   <tr>
				     <td class="tableLeft">First Inspection: </td>
				     <td class="tableRight"><%=nullCheck(firstInspec)%></td>
		      </tr>
				  
				     <tr>
				     <td class="tableLeft">Last Inspection: </td>
				     <td class="tableRight"><%=nullCheck(lastInspec)%></td>
		      </tr>
				   
				     <tr>
				     <td class="tableLeft">PhaseD Inspection: </td>
				     <td class="tableRight"><%=nullCheck(phDInspec)%></td>
		      </tr>
				  
				     <tr>
				     <td class="tableLeft">Road and Track A: </td>
				     <td class="tableRight"><%=nullCheck(roadTrackA)%></td>
		      </tr>
				  
				     <tr>
				     <td class="tableLeft">Road and Track C: </td>
				     <td class="tableRight"><%=nullCheck(roadTrackC)%></td>
		      </tr>
				 
				     <tr>
				     <td class="tableLeft">SteepleChase Jump Penalities: </td>
				     <td class="tableRight"><%=nullCheck(steepleJmpPenal)%></td>
		      </tr>
				  
				     <tr>
				     <td class="tableLeft">SteepleChase Time Penalities: </td>
				     <td class="tableRight"><%=nullCheck(steepleTmPenal)%></td>
		      </tr>
				   <tr>
				     <tr>
				     <td class="tableLeft">Points: </td>
				     <td class="tableRight"><%=nullCheck(hlcPoints)%></td>
		      </tr>
				  
					    
				   <tr>
					<td colspan="2" align="center">&nbsp;<input type="button" onclick="javascript:history.back(-1);" value="Back" class="gradBtn"  />
					</td>
				  </tr>
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
