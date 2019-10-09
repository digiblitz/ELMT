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
<%@ page import = "java.math.*" %>

<%@ page import = "com.hlcmeeting.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script type="text/javascript" >

 
            function chk(){
	
//==================================== For Radio Button ==============================	
			
		 
				if(document.frmMeeAnnualConvRegister.comments.value==""){
				alert("Enter a comments");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}
				if(document.frmMeeAnnualConvRegister.comments.value.indexOf(' ')==0){
				alert("Please avoid  unwanted white space");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}
				if(document.frmMeeAnnualConvRegister.comments.value.length>1024){
				alert("Comments is out of range");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}
				if(document.frmMeeAnnualConvRegister.comments.value.indexOf('  ')!==-1){
				alert("Please avoid  unwanted white space");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}


	return true;
}
 
 


</script>
</head>

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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
					
						
					
							
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="formLayout">
							<tr>
								<td colspan="3" class="tblMainHead"><strong></strong> Meetings:  <span class="styleBoldTwo">Annual Meeting &amp; Convention  Details </span></td>
							</tr>
							<tr>
								<td colspan="3" class="tblRowHead">User Information</td>
							</tr>
						  <% 
						  try
						  {
						  float addAmt = 0.0f;
						  float fAmount = 0.00f;
							HLCAnnualRegistrationDetailVO  objAnnualRegDet = (HLCAnnualRegistrationDetailVO) request.getAttribute("viewMemberDetails");
							if(objAnnualRegDet!=null){
								 String ardID  = objAnnualRegDet.getArdId();
								 String meetingId  =  objAnnualRegDet.getAnnualMeetingId();
								 String userId  = objAnnualRegDet.getUserId();
								 String badgeName  = objAnnualRegDet.getBadgeName();
								 String fName  = objAnnualRegDet.getFirstName();
								 String memberName  = objAnnualRegDet.getMemTypeName();
								 String daysApplied  = objAnnualRegDet.getDaysApplied();
								 String amount  = objAnnualRegDet.getRegAmount();
								// boolean ponyStatus  = objAnnualRegDet.getPonyMemberStatus();
								 String poId  = objAnnualRegDet.getPonyMemId();
								 String poClubeName  = objAnnualRegDet.getPonyClubName();
								 boolean facStatus  = objAnnualRegDet.getAccomFaciStatus();
								 String AccomDet  = objAnnualRegDet.getAccomDetails();
								 String reqStatus = objAnnualRegDet.getRequestStatus();
								  String comments = objAnnualRegDet.getRemarks();
								 
								 if(memberName==null || memberName.trim().length()==0)  memberName = "N/A";
								 if(badgeName==null || badgeName.trim().length()==0)  badgeName = "N/A";
								 
								 if(daysApplied==null || daysApplied.trim().length()==0)  daysApplied = "N/A";
								 
								 if(poId==null || poId.trim().length()==0)  poId = "N/A";
								 if(poClubeName==null || poClubeName.trim().length()==0)  poClubeName = "N/A";
								 if(AccomDet==null || AccomDet.trim().length()==0)  AccomDet = "N/A";
								// String ardID  = objAnnualRegDet.getRequestStatus();
								 //String ardID  = objAnnualRegDet.getRemarks();
								// String ardID  = objAnnualRegDet.getAddDate();
						
						%>
						  <tr>
							<td class="tableLeft"> First Name:</td>
							<td colspan="2" class="tableRight"><%=fName%>&nbsp;</td>
						  </tr>
						   <tr>
							<td class="tableLeft"> Membership Name:</td>
							<td colspan="2" class="tableRight"><%=memberName%>&nbsp;</td>
						  </tr>
						 
						  <tr>
							<td class="tableLeft"> Name on Badge:</td>
							<td colspan="2" class="tableRight"><%=badgeName%>&nbsp;</td>
						  </tr>
						  
						   <tr>
							<td class="tableLeft"> Pony Club Id:</td>
							<td colspan="2" class="tableRight"><%=poId%>&nbsp;</td>
						  </tr>
						  
						    <tr>
							<td class="tableLeft"> Pony Club Name:</td>
							<td colspan="2" class="tableRight"><%=poClubeName%>&nbsp;</td>
						  </tr>
						  
						   <tr>
							<td class="tableLeft"> Accommodation Details:</td>
							<td colspan="2" class="tableRight"><%=AccomDet%></td>
						  </tr>
						  
						   <tr>
							<td class="tableLeft"> Days Applied:</td>
							<td colspan="2" class="tableRight"><%=daysApplied%>&nbsp;</td>
						  </tr>
						  
						     <%if(reqStatus!=null && reqStatus.equals("Pending")){%>
								<%}
							%>
						  
							<%
							
							
							ArrayList priceDetails = (ArrayList)request.getAttribute("priceDetails");
							if(priceDetails!=null && priceDetails.size()!=0){
								Iterator it = priceDetails.iterator();
								%>
								<tr>
								<td class="tblRowHead"> Registered Activities</td>
						  	    <td class="tblRowHead">&nbsp;</td>
							    <td class="tblRowHead">Amounts</td>
							</tr>
								<%
								while(it.hasNext()){
									HLCAnnualPriceDetailVO objPriceDet = (HLCAnnualPriceDetailVO) it.next();
									String activityName = objPriceDet.getSpecificationName();
									String amt1 = objPriceDet.getAmount();
									int totalTicket = objPriceDet.getTotalTicket();
									boolean addTkt = objPriceDet.isAddTktStaus();
									//amt1 = amt1 +Integer.parseInt(amt);
									if(activityName==null) activityName = "";
									if(addTkt==false){
							%>
							 <tr>
							<td height="25" class="tableRight"><%=activityName%></td>
						  	<td height="25" class="tableRight">&nbsp;</td>
						    <td height="25" class="tableRight">$<%=amt1%></td>
						    </tr>
							
							<%
							}
							}
							%>
							<tr>
								<td class="tblRowHead"> Addtional Tickets </td>
						  	    <td class="tblRowHead">No of Tickets </td>
							    <td class="tblRowHead">Amounts</td>
							</tr>
							<%
							
							if(priceDetails!=null && priceDetails.size()!=0){
								Iterator tempIt = priceDetails.iterator();
								int chkValue =0;
								
								
								while(tempIt.hasNext()){
									float totAmt = 0.00f;
									HLCAnnualPriceDetailVO objPriceDet = (HLCAnnualPriceDetailVO) tempIt.next();
									String activityName = objPriceDet.getSpecificationName();
									String amt = objPriceDet.getAmount();
									int totalTicket = objPriceDet.getTotalTicket();
									boolean addTkt = objPriceDet.isAddTktStaus();
									
									if(activityName==null) activityName = "";
									if(addTkt==true){
									chkValue++;
									
									if(amt!=null)
									{
										totAmt = totAmt + Float.parseFloat(amt);
									}
							%>
							 <tr>
							<td height="25" class="tableRight"><%=activityName%></td>
						  	<td height="25" class="tableRight"><%=totalTicket%></td>
						    <td height="25" class="tableRight">$<%=totAmt%></td>
						    </tr>
							
							<%
							fAmount = fAmount+totAmt;
							}

							}
							if(chkValue==0){
							%>
							<tr>
							<td colspan="3" height="25" class="tableRight"> No addtional tickets purchased.</td>
						    </tr>
							<%
							}
							}
							}
							%>
							<tr>

								<%
								//float Aamount = Float.parseFloat(plan_amt);
								
								System.out.print("amount in JSP : "+amount);
								float exactAmont = 0.00f;
								
								if(amount!=null)
							    {
									exactAmont = Float.parseFloat(amount);
								}
								
								float totalActivityAmount = (exactAmont + fAmount);
								BigDecimal bdAmount = new BigDecimal(totalActivityAmount);
								bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
								if(bdAmount.doubleValue()>0){
								%>
								<td class="tblRowHead"> Total Amount </td>
						  	    <td class="tblRowHead">&nbsp;</td>								
							    <td class="tblRowHead">$<%=bdAmount%></td>
								<%
								}
								else{
								%>
								<!--<td class="tblRowHead">$< %=addAmt%></td>-->
								<%
								}
								%>
							</tr>
							<%
							}
							}
							catch(Exception e)
							{
								System.out.print("Error in JSP : ");
								e.printStackTrace();
							}
							%>
							 <tr>
								  <td colspan="3" class="alignCenter">
									<input name="annButName" type="button" class="gradBtn" value="Back" onclick="javascript:history.back(-1);" />
&nbsp;&nbsp;									 </td>
								</tr>
					  </table>
				  <tr>
						<td >&nbsp; 
					   <!-- DO NOT DELETE THIS ROW -->
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
</body>
</html>
