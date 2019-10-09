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
<%@ page import = "java.text.*" %>
<%@ page import = "com.hlcmeeting.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script type="text/javascript" >

 
            function chk(){
				if(document.frmMeeAnnualConvRegister.comments.value==""){
				alert("Enter a comments");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}
				
				if(document.frmMeeAnnualConvRegister.comments.value.length>1024){
				alert("Comments is out of range");
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
								<td colspan="2" class="tblMainHead"><strong></strong> Meetings:  <span class="styleBoldTwo">Annual Meeting &amp; Convention  Details </span></td>
							</tr>
							<tr>
								<td colspan="2" class="tblRowHead">User Information</td>
							</tr>
						  <% 
						  	
							SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
							HLCAnnualRegisterVO  objAnnualRegDet = (HLCAnnualRegisterVO) request.getAttribute("viewMemberDetails");
							if(objAnnualRegDet!=null){
								 String annualMeetingId  =  objAnnualRegDet.getAnnualMeetingId();
								 String userId  = objAnnualRegDet.getAnnualMeetingId();
								 String fName  = objAnnualRegDet.getFirstName();
								 String lastname = objAnnualRegDet.getLastName();
								 String emailId = objAnnualRegDet.getEmail();
								 //String noOfAddTickets  = objAnnualRegDet.getNoOfAddTickets();
								 String amount  = objAnnualRegDet.getTotalAmount();
								// String reqStatus = objAnnualRegDet.getRequestStatus();
								 //String comments = objAnnualRegDet.getComments();
								 Date addDate = objAnnualRegDet.getAddDate();
								 String checkNumber = objAnnualRegDet.getCheckNumber();
								 String paymentMode = objAnnualRegDet.getPaymentMode();
								 String checkName = objAnnualRegDet.getPaymentBy();
								 String txId = objAnnualRegDet.getTransactionId();
								 String bankName = objAnnualRegDet.getBankName();
                    			 Date checkDate = objAnnualRegDet.getCheckDate();
								 
								 String checkDateValue = "";
								 
								 // if(comments==null || comments.trim().length()==0)  comments = "";
								 
								 if(emailId==null || emailId.trim().length()==0)  emailId = "N/A";
								 if(fName==null || fName.trim().length()==0)  fName = "N/A";
								 if(lastname==null || lastname.trim().length()==0)  lastname = "N/A";
								 if(checkNumber==null || checkNumber.trim().length()==0)  checkNumber = "N/A";
								 if(checkName==null || checkName.trim().length()==0)  checkName = "N/A";
								 if(bankName==null || bankName.trim().length()==0)  bankName = "N/A";
								 if(txId==null || txId.trim().length()==0)  txId = "N/A";
								 if(amount==null || amount.trim().length()==0)  amount = "0.00";
								 
								 				
						%>
						  <tr>
							<td class="tableLeft"> Registered By :</td>
							<td class="tableRight"><%=fName%> &nbsp;<%=lastname%></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Email:</td>
							<td class="tableRight"><%=emailId%></td>

						  </tr>
						  
						   <tr>
							<td class="tableLeft"> Registered Date:</td>
							<%
							if(addDate!=null && !addDate.equals(""))
							{
							%>
							   <td class="tableRight"><%=sdf.format(addDate)%></td>
							<%
							}
							else
							{
							%>
							   <td class="tableRight">N/A</td>		
 						    <%
							}
							%>
						  </tr>
					
							<%if(paymentMode!=null && !paymentMode.equals("") && paymentMode.equals("Check")){%>
						  <tr>
							<td class="tableLeft"> Check Number:</td>
							<td class="tableRight"><%=checkNumber%></td>
						  </tr>
						    <tr>
						     <td class="tableLeft">Bank Name: </td>
						     <td class="tableRight"><%=bankName%></td>
					      </tr>
						   <tr>
							<td class="tableLeft">  Name on Check:</td>
							<td class="tableRight"><%=checkName%></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Check Date:</td>
							<td class="tableRight">
							<%
							 if(checkDate==null) { checkDateValue = "N/A";
							%>
							<%=checkDateValue%>
							<%
							}
							else{
							%>
							<%=sdf.format(checkDate)%>
							<%
							}
							%>
							
							</td>
						  </tr>
						 
							<%}
							else{%>
							
						   <tr>
							<td class="tableLeft">Transaction Id:</td>
							<td class="tableRight"><%=txId%></td>
						  </tr>
							<%}%>
						 <tr>
							<td class="tableLeft"> Amount:</td>
							<td class="tableRight"><%=amount%></td>
						  </tr>	      
						  <%
						  }
						  %>
						  <!--
						  <tr>
							<td colspan="2" class="tableLeft" id="price">
								<a href="#" class="linkFour">View Price Details</a> 
							</td>
						  </tr>
						  -->
					  </table>
					  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="formLayout">
							<%
							float totAmt = 0.00f;
							HLCAnnualRegistrationDetailVO  objAnnualUserDet = (HLCAnnualRegistrationDetailVO) request.getAttribute("viewRegUserDetails");
							if(objAnnualRegDet!=null){
							if(objAnnualUserDet.getArdId()!=null){
							%>					  
							<tr>
								<td colspan="3" class="tblRowHead">User Information</td>
							</tr>
						  <% 

								 String ardID  = objAnnualUserDet.getArdId();
								 String meetingId  =  objAnnualUserDet.getAnnualMeetingId();
								 String userId  = objAnnualUserDet.getUserId();
								 String badgeName  = objAnnualUserDet.getBadgeName();
								 String fName  = objAnnualUserDet.getFirstName();
								 String memberName  = objAnnualUserDet.getMemTypeName();
								 String daysApplied  = objAnnualUserDet.getDaysApplied();
								 String amount  = objAnnualUserDet.getRegAmount();
								// boolean ponyStatus  = objAnnualRegDet.getPonyMemberStatus();
								 String poId  = objAnnualUserDet.getPonyMemId();
								 String poClubeName  = objAnnualUserDet.getPonyClubName();
								 boolean facStatus  = objAnnualUserDet.getAccomFaciStatus();
								 String AccomDet  = objAnnualUserDet.getAccomDetails();
								 String reqStatus = objAnnualUserDet.getRequestStatus();
								 String comments = objAnnualUserDet.getRemarks();
								 
								  if(daysApplied==null || daysApplied.trim().length()==0)  daysApplied = "N/A";
								 
								 if(poId==null || poId.trim().length()==0)  poId = "N/A";
								 if(poClubeName==null || poClubeName.trim().length()==0)  poClubeName = "N/A";
								 if(AccomDet==null || AccomDet.trim().length()==0)  AccomDet = "N/A";
								// String ardID  = objAnnualRegDet.getRequestStatus();
								 //String ardID  = objAnnualRegDet.getRemarks();
								// String ardID  = objAnnualRegDet.getAddDate();
						
						%>
						  <tr>
							<td width="35%" class="tableLeft"> First Name:</td>
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
							<%						  
							
							ArrayList priceDetails = (ArrayList)request.getAttribute("priceDetails");
							if(priceDetails!=null && priceDetails.size()!=0){
								Iterator it = priceDetails.iterator();
								%>
								<tr>
								<td class="tblRowHead"> Registerted Activities</td>
						  	    <td width="37%" class="tblRowHead">&nbsp;</td>
							    <td width="28%" class="tblRowHead">Amounts</td>
							</tr>
								<%
								while(it.hasNext()){
									HLCAnnualPriceDetailVO objPriceDet = (HLCAnnualPriceDetailVO) it.next();
									String activityName = objPriceDet.getSpecificationName();
									String amt = objPriceDet.getAmount();
									int totalTicket = objPriceDet.getTotalTicket();
									boolean addTkt = objPriceDet.isAddTktStaus();
									
									if(activityName==null) activityName = "";
									if(addTkt==false){
							%>
							 <tr>
							<td height="25" class="tableRight"><%=activityName%></td>
						  	<td height="25" class="tableRight">&nbsp;</td>
						    <td height="25" class="tableRight">$<%=amt%></td>
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
									HLCAnnualPriceDetailVO objPriceDet = (HLCAnnualPriceDetailVO) tempIt.next();
									String activityName = objPriceDet.getSpecificationName();
									String amt = objPriceDet.getAmount();
									int totalTicket = objPriceDet.getTotalTicket();
									boolean addTkt = objPriceDet.isAddTktStaus();
									
									if(activityName==null) activityName = "";
									if(addTkt==true){
									chkValue++;
									totAmt = totAmt + Float.parseFloat(amt);
							%>
							 <tr>
							<td height="25" class="tableRight"><%=activityName%></td>
						  	<td height="25" class="tableRight"><%=totalTicket%></td>
						    <td height="25" class="tableRight">$<%=amt%></td>
						    </tr>
							
							<%
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
						
							%>
							<tr>
								<td class="tblRowHead"> Total Amount </td>
						  	    <td class="tblRowHead">&nbsp;</td>
								<%
								//float Aamount = Float.parseFloat(plan_amt);
								
								
								float exactAmont = Float.parseFloat(amount);
								float totalActivityAmount = (exactAmont + totAmt);
								BigDecimal bdAmount = new BigDecimal(totalActivityAmount);
								bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);
								%>
							    <td class="tblRowHead">$<%=bdAmount%></td>
							</tr>
							
						
							<form name="frmMeeAnnualConvRegister" method="post" action="AnnualRegList.do" onsubmit="return chk();">
						<input type="hidden"  name="ardId" value="<%=ardID%>" />	
								<input type="hidden"  name="memProcess" value="updateStatus" />	
	 
								<tr>
									<td  class="tableLeft">Do you want Approve or Reject:</td>
									  <td colspan="2" class="tableLeftTxtArea" >	
									  <%
									  	if(reqStatus!=null && reqStatus.equals("Pending")){
									  %>
									<input  type="radio" value="Registered" name="requestStatus" checked="checked" />Approve &nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="requestStatus" value="Rejected" />Reject
									<%
									}
									else{
									%>
									<input type="radio" name="requestStatus" value="Registered" />Approve &nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="requestStatus" value="Rejected" checked="checked"  />Reject
									<%
									}
									if(comments==null) comments = "";
									%>
									</td>
								  </tr>	  
								  <tr> 
									<td class="tableLeftTxtArea">Comments:</td>
									<td colspan="2" class="tableRight"><textarea name="comments" class="textAreaOne" rows="5"><%=comments%></textarea>
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr>
								  <td class="alignCenter" colspan="2">	
								    <input type="submit" value="Submit" class="gradBtn" />&nbsp;&nbsp;&nbsp;
									<input type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);"/>&nbsp;</td></tr>
								<tr> 
									
								  </tr>
						  </form>
						  	<%
							}
						}
						}
							%>
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
