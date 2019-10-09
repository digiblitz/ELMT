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
<%@ page import="com.hlccommon.util.*" %>
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlcmeeting.util.HLCAnnualDetVO"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmrm.util.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!--<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegValidate.js" type="text/javascript" ></script>-->
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script src="javascripts/calendar2.js" type="text/javascript"></script>
</head>
 <%! 
    DecimalFormat result  = new DecimalFormat("0.00");
    String amtFormat(String amtVal){
        String amountValue = "0.00";
        if(amtVal!=null && amtVal.trim().length()!=0){
             amountValue = result.format(Double.parseDouble(amtVal));
        }
        return amountValue;
    }
    
    %>

	 
<body>
<!-- <body onload="famAddOnInit(), initLife();">-->
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
				
						<div class="colspan"><strong>Membership:</strong> <span class="styleBoldTwo">Membership Payment Information</span> </div>
						<div id="commonBG" class="textCommon" >You are viewing your Membership <span class="rowHead">Payment Information.</span></div>
																					
															  	<table width="100%" cellpadding="0" cellspacing="1" border="0">
																 
																  <tr>
																	<td colspan="2" class="tblRowHead">Membership Registration Payment Information </td>
																  </tr>
																 
																																 
																 <%
                                                                                                                                 
                                                                 String memId1=(String)session.getAttribute("memberId");
																 session.setAttribute("status_membId",memId1);
                                                                                                                                 
																 HLCPaymentDetails objPayment = new HLCPaymentDetails();
																 
																 objPayment=(HLCPaymentDetails)request.getAttribute("objPayment");
																 System.out.println("objPayment jsp :"+objPayment.toString());
																 boolean status=false;
																  
																 if(objPayment != null)
																 {
																 
																if(objPayment.getPaymentStatus()!=null)
																{
																if(objPayment.getPaymentStatus().equalsIgnoreCase("card"))
																{
																%>
																
																  
																  <%
                                                                                                                                  if(objPayment.getSslResult()!=null && objPayment.getSslResult().trim().length()!=0 )
                                                                                                                                      {
                                                                                                                                if(objPayment.getSslResult().equalsIgnoreCase("1"))
                                                                                                                                    {
     
status=true;	                                                                                                                                    session.setAttribute("amt",String.valueOf(objPayment.getAmount()));
                                                                                                                                       %>
      <tr>
                                                                                                                                            <td class="tableLeft"><span class="styleBoldOne">Retry Declined Payment - Memership Registration</span> </td>
                                                                                                                                            <th class="tableRight"><span class="styleBoldOne"><a href="./AdminMembPayment.do?process=disp&usr=user&cardStatus=null&pid=<%=objPayment.getPaymentId()%>">Retry Payment</a></span></th>
                                                                  </tr>
                                                                                                                                    
                                                                                                                                    <%}															  
																 }}else{
																 
																 try
																 {
																 	 
                                                                                                                                                        
                                                                                                                                                        float pendAmt = objPayment.getPendingAmount();                     System.out.println("Pending Amt in JSP :"+pendAmt);
					 if(objPayment.getPendingAmount()>0){
					 status = true;
                     session.setAttribute("amt",String.valueOf(pendAmt));
																	%>
						  <tr>
							<td class="tableLeft">Balance Amount Due - Membership Registration</td>
<th class="tableRight"><span class="styleBoldOne">
<a href="./AdminMembPayment.do?process=disp&usr=user&cardStatus=null&defStat=yes&pid=<%=objPayment.getPaymentId()%>">Complete Payment</a></span>
</th>
						 </tr>
																  																																	
						<%}}
						catch(Exception e)
						{
							System.out.println(" error in jsp :"+e);
						}
						%>
						 <%}}}
																 
					 System.out.print("status :"+status);
					 if(status==false)
					{%>
<tr>
<td class="tableSpan" colspan="2"><span class="styleBoldOne">You Dont Have any Membership Registration Payments in Due !</span>
</td>        
					
					</tr>
					<tr>
					<td>
				 </td>
				 </tr>					                                                                                                                                   <%}%>
			</table>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
						  <tr>
								<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						  </tr>
						  <tr>
								<td width="140"  class="tblRowHeadTwo">NSF Charge Amount</td>						  
								<td width="140"  height="27" class="tblRowHeadTwo">Payment Raised</td>
								<td width="140"  height="27" class="tblRowHeadTwo">Staff Comment On NSF</td>
								<td width="100" height="23"  class="tblRowHeadTwo">Payment</td>								
						</tr>
						<%
					ArrayList chargeInfo = (ArrayList) request.getAttribute("NSFcharge");
					if(chargeInfo!=null){
					if(chargeInfo.size()!=0){
							System.out.print("chargeInfo value"+chargeInfo.size());
							if(chargeInfo!=null && chargeInfo.size()!=0){
								Iterator itr = 	chargeInfo.iterator();
								while(itr.hasNext()) {		
									HLCPaymentDetails objPayDet = (HLCPaymentDetails)itr.next();	
									Date payDte = objPayDet.getPaymentDate();
									String totAmt = Double.toString(objPayDet.getAmount());								
									String paymentId = objPayDet.getPaymentId();
									String parentPayId = objPayDet.getParentPaymentId();
									float pendingAmount = objPayDet.getPendingAmount();
									String userComments = objPayDet.getUser_comments();
									if(pendingAmount > 0){
						%>
						<form name="NSFChargeForm" method="post" action="NSFChargePayment.do?">
							<input type="hidden" name="process" value="NSFPayProceed" />
							<input type="hidden" name="parentPayId" value="<%=parentPayId%>" />
							<input type="hidden" name="paymentId" value="<%=paymentId%>" />
							<tr>
								<td class="listCellBg" align="center"><%=totAmt%></td>
								<td class="listCellBg" align="center"><%=payDte%></td>
								<td class="listCellBg" align="center"><%=userComments%></td>
								<td class="listCellBg" align="center"><input type="submit" name="payProceed" value="Make Payment" class="oneBtn"/></td>
							</tr>
					   </form>
						 <% 	}// Pending amount > 0
						 } //while loop%>
						
						 <%	} %>

						<% } } %>	
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have any NSF Payments Pending!</span></td>        
						  </tr>						
			  </table>
						<div class="colspan"><span class="styleBoldTwo">Annual Registration Pending Payment Information</span> </div>
						    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						    </tr>
						    <tr>
								<td width="80"  class="tblRowHeadTwo">Meeting Id</td>
								<td width="150"  class="tblRowHeadTwo">Badge Name</td>	
								<td width="125"  class="tblRowHeadTwo">Days Applied</td>								
								<td width="125"  class="tblRowHeadTwo">Total Amount </td>
 								<td width="68"  height="23" class="tblRowHeadTwo">Status </td>
								<td width="100" height="23"  class="tblRowHeadTwo"> Payment</td>    
							</tr>
						      
						<%
							ArrayList annualPend = (ArrayList) request.getAttribute("annualPend");
							System.out.print("annualPend" + annualPend);
							float totAmt =0;
							if(annualPend!=null && annualPend.size()!=0){
							Iterator itr = 	annualPend.iterator();
								while(itr.hasNext()) {	
									HLCAnnualDetVO detVO = (HLCAnnualDetVO) itr.next();
								
									String badgeName = detVO.getBadge_name();
									String daysApplied = detVO.getDays_applied();
									String MeetingId = detVO.getMeetId();
									String registId = detVO.getRegistrat_id();
									String status1 = detVO.getRequest_status();	
									totAmt = detVO.getTotal_amount();
									String paymentID= detVO.getPayment_id();
									
									%>
							<form name="Approvedfrm4" method="post" action="AnnualPendDet.do">
							<input type="hidden" name="execute" value="listDetails"  />
								 <tr>
									<td height="26" class="listCellBg"><%=MeetingId%></td>
									<td class="listCellBg"><%=badgeName%></td>
									<%
									if(daysApplied==null || daysApplied.trim().length()==0){
										daysApplied = "Full Registration";
									}
									%>									
									<td class="listCellBg"><%=daysApplied%></td>								
									<td class="listCellBg"><%=totAmt%></td>
									<td class="listCellBg"><span class="styleBoldOne"><%=status1%></span></td>
									<td class="listCellBg"><input type="submit" name="click2" value="Make Payment" class="oneBtn"/>
									</td>
									<input type="hidden" name="registr_id" value="<%=registId%>"/>
									<input type="hidden" name="pa_id" value="<%=paymentID%>"/>
									<input type="hidden" name="meet_id" value="<%=MeetingId%>"/>
								</tr>
								<tr>
								<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
							 </tr>
							 </form>
							   <%
							   }//while
						   }
						   else{
							%>
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have Annual Registration Payments in Due !</span></td>        
						  </tr> 
						  <% }
						   %>
						  </table>
					  </div>
					  
					  <div class="colspan"><span class="styleBoldTwo">Event Registration Pending Payment Information</span> </div>
						    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						    </tr>
						    <tr>
								<td width="80"  class="tblRowHeadTwo">Event Id</td>
								<td width="150"  class="tblRowHeadTwo">Event Title</td>	
								<td width="125"  class="tblRowHeadTwo">OrganizerId</td>								
								<td width="125"  class="tblRowHeadTwo">Amount </td>							
								<td width="100" height="23"  class="tblRowHeadTwo"> Payment</td>    
							</tr>
						<%
							ArrayList eventPendPay = (ArrayList) request.getAttribute("eventPendPay");
							System.out.print("eventPendPay" + eventPendPay);
							//float pendamt =0;
							if(eventPendPay!=null && eventPendPay.size()!=0){
							System.out.print("eventPendPay&&&" + eventPendPay.size());
							Iterator itr1 = eventPendPay.iterator();
								while(itr1.hasNext()) {	
									String [] evedetVO = (String[]) itr1.next();								
									String eveId = evedetVO[0];
									System.out.print("eveId" + eveId);
									String usrId=evedetVO[1];
									String payId= evedetVO[2];
									String eveTitle = evedetVO[3];
									String orgId = evedetVO[4];
									String cc_name = evedetVO[5];
                                    String cc_type = evedetVO[6];
                                    String ssl_txn_id =evedetVO[7];
                                    String pendamt =evedetVO[8];																	
									
			
									
									%>
							<form name="myform1" id="myform1" method="post" action="eventPendPayment.do">
							<input type="hidden" name="cmd" value="viewPaymentDets"  />
								 <tr>																					
									<td class="listCellBg"><%=eveId%></td>								
									<td class="listCellBg"><%=eveTitle%></td>
									<td class="listCellBg"><%=orgId%></td>
									<td class="listCellBg"><%=pendamt%></td>								
									<td class="listCellBg"><input type="submit" name="click2" value="Make Payment" class="oneBtn"/>
									</td>									
									<input type="hidden" name="paymentId" value="<%=payId%>"/>
									<input type="hidden" name="eveId" value="<%=eveId%>"/>
									<input type="hidden" name="usrId" value="<%=usrId%>"/>
								</tr>
								<tr>
								<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
							 </tr>
							 </form>
							   <%
							   }//while
						   }
						   else{
							%>
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have Event Registration Payments in Due !</span></td>        
						  </tr> 
						  <% }
						   %>
						  </table>
					  </div>	
	
					<div class="spacer">&nbsp;</div>
																
							<div class="colspan"><span class="styleBoldTwo">Horse Registration / Upgrade Declined Payment Information</span> </div>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						    </tr>
						    <tr>
								<td width="65"  height="27" class="tblRowHeadTwo">Horse Member Id</td>
								
								<td width="103"  class="tblRowHeadTwo">Horse Name</td>
								<td width="95"  class="tblRowHeadTwo">Actual Amount </td>
								<td width="64"  class="tblRowHeadTwo">Balance Amount </td>
								<!--<td  class="tblRowHeadTwo">Registered By </td>	-->							
								<!--<td width="86"  class="tblRowHeadTwo">Date </td>-->
								<td width="63"  class="tblRowHeadTwo">Status </td>
								<td width="103" height="23"  class="tblRowHeadTwo"> Payment</td>									
								<!--
								<td width="112" class="tblRowHeadTwo">DownGrade</td>
								<td width="91" class="tblRowHeadTwo">Renew</td>
								-->
						      </tr>
						      
						<%
							ArrayList userInfo = (ArrayList) request.getAttribute("pendingInfo");
							System.out.print("userInfo value" +userInfo.size());
							if(userInfo!=null && userInfo.size()!=0){
							Iterator itr = 	userInfo.iterator();
							while(itr.hasNext()) {				   
								HLCHorseRegListVO objHrListVO = (HLCHorseRegListVO)itr.next();								
									String horseMemberId =  objHrListVO.getHorseMemberId();
									String horseName = objHrListVO.getHorseName();
									Date addDate = objHrListVO.getAddDate();
									String membershipTypeName = objHrListVO.getMembershipTypeName();
									String statusName = objHrListVO.getStatusName();
									float amount = objHrListVO.getAmount();
									//float checkAmount = objHrListVO.getCheckAmount();
									float pendingAmount = objHrListVO.getPendingAmount();
									String paymentId = objHrListVO.getPaymentId();
								%>
						<form name="Approvedfrm" method="post" action="RegHorseListing.do?">
						<input type="hidden" value="PayProceed" name="process" />
							 <tr>
								<td height="26" class="listCellBg"><a href="./RegHorseListing.do?process=chngdesc&memid=<%=horseMemberId%>"><%=horseMemberId%></a>  </td>
								<td class="listCellBg"><%=horseName%></td>
								<td class="listCellBg"><%=amount%></td>
								<% 
								float pay =0.0f;
								if (pendingAmount<=0){
									pay = amount;
								}
								else{
									pay = pendingAmount;
								} %>
								<td class="listCellBg"><%=pay%></td>
								<!--<td class="listCellBg">< %=addDate%></td>-->
								<td class="listCellBg"><span class="styleBoldOne"><%=statusName%></span></td>
								<td class="listCellBg"><input type="submit" name="click2" value="Make Payment" class="oneBtn"/></td>
								<input type="hidden" name="memid" value="<%=horseMemberId%>"/>
							</tr>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						 </tr>
						 </form>
						   <%
						   }}
						   else{%>
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have any Horse Registration Payment in Due !</span></td>        
						  </tr> 
						  <% }
						   %>
						  </table>
				  
				  
				   <!-- Horse Due Pending Section -->
							<div class="colspan"><span class="styleBoldTwo">Horse Registration / Upgrade Due Payment Information</span> </div>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						    </tr>
						    <tr>
								<td width="68"  height="27" class="tblRowHeadTwo">Horse Member Id</td>
								
								<td width="150"  class="tblRowHeadTwo">Horse Name</td>	
								<td width="125"  class="tblRowHeadTwo">Actual Amount </td>
								<td width="75"  class="tblRowHeadTwo">Balance Amount </td>
								<!--<td  class="tblRowHeadTwo">Registered By </td>	-->							
								<!--<td width="86"  class="tblRowHeadTwo">Date </td>-->
								<td width="68"  class="tblRowHeadTwo">Status </td>
								<td width="100" height="23"  class="tblRowHeadTwo"> Payment</td>									
								 
						      </tr>
						      
						<%
							ArrayList horseDuePendInfo = (ArrayList) request.getAttribute("horseDuePendInfo");
							if(horseDuePendInfo!=null && horseDuePendInfo.size()!=0){
							Iterator itr = 	horseDuePendInfo.iterator();
							while(itr.hasNext()) {				   
									HLCHorseRegListVO objHrListVO = (HLCHorseRegListVO)itr.next();								
									String horseMemberId =  objHrListVO.getHorseMemberId();
									String horseName = objHrListVO.getHorseName();
									Date addDate = objHrListVO.getAddDate();
									String membershipTypeName = objHrListVO.getMembershipTypeName();
									String statusName = objHrListVO.getStatusName();
									float amount = objHrListVO.getAmount();
									//float checkAmount = objHrListVO.getCheckAmount();
									float pendingAmount = objHrListVO.getPendingAmount();
									String paymentId = objHrListVO.getPaymentId();
								if(pendingAmount>0){
								%>
						<form name="Approvedfrm1" method="post" action="RegHorseListing.do?">
						<input type="hidden" value="DuePayProceed" name="process" />
							 <tr>
								<td height="26" class="listCellBg"><a href="./RegHorseListing.do?process=chngdesc&memid=<%=horseMemberId%>"><%=horseMemberId%></a>  </td>
								<td class="listCellBg"><%=horseName%></td>
								<td class="listCellBg"><%=amount%>&nbsp;</td>
								<td class="listCellBg"><%=pendingAmount%>&nbsp;</td>
								<!--<td class="listCellBg">< %=addDate%>&nbsp;</td>-->
								<td class="listCellBg"><span class="styleBoldOne"><%=statusName%>&nbsp;</span></td>
								<td class="listCellBg"><input type="submit" name="click2" value="Make Payment" class="oneBtn"/>
								</td> 
								<input type="hidden" name="memid" value="<%=horseMemberId%>"/>
								<input type="hidden" name="paymentId" value="<%=paymentId%>"/>
								<input type="hidden" name="totalAmount" value="<%=pendingAmount%>"/>
								<input type="hidden" name="amount" value="<%=amount%>"/>
								<input type="hidden" name="competitionName" value="<%=horseName%>"/>
							</tr>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						 </tr>
						 </form>
						   <%
						   }//if
						   }//while
						   }//if
						   else{%>
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have any Due in Payment !</span></td>        
						  </tr> 
						  <% }
						   %>
						  </table>	
				  

				  
						  <!-- Changed for Add Horse / Change Horse --> 
 							<div class="colspan"><span class="styleBoldTwo">Add-Rider/Owner &amp; Change Horse/Rider/Owner Declined Payment  Information</span></div>
						    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						    </tr>
						    <tr>
								<!--<td width="104"  height="27" class="tblRowHeadTwo">Member Id</td>-->
								
								<td width="63"  class="tblRowHeadTwo"> Horse Member Id </td>
								<td width="102"  class="tblRowHeadTwo">Horse Name</td>	
								<td width="100"  class="tblRowHeadTwo">Actual Amount </td>
								<!--<td  class="tblRowHeadTwo">Registered By </td>	-->							
								<td width="66"  class="tblRowHeadTwo">Balance Amount </td>
								<td width="59"  class="tblRowHeadTwo">Status </td>
								<td width="103" height="23"  class="tblRowHeadTwo"> Payment</td>								
								<!--
								<td width="112" class="tblRowHeadTwo">DownGrade</td>
								<td width="91" class="tblRowHeadTwo">Renew</td>
								-->
						      </tr>
						      
						<%
							ArrayList horsependInfo = (ArrayList) request.getAttribute("horsependInfo");
							if(horsependInfo!=null && horsependInfo.size()!=0){
							Iterator itr = 	horsependInfo.iterator();
							while(itr.hasNext()) {	
								String[] payInfo =	(String[]) itr.next();
								String hrMemId = payInfo[0];
								String paymentId = payInfo[1];
								String amount = payInfo[2];
								String checkAmount = payInfo[3];
								String pendingAmount = payInfo[4];	
								String horseName = payInfo[5];
								String memStatus = payInfo[6];
							    float balAmt = Float.parseFloat(amount);
								%>
						<form name="Approvedfrm2" method="post" action="RegHorseListing.do?">
						<input type="hidden" value="AddChngPayProceed" name="process" />
							 <tr>
								 <!--<td class="listCellBg">< %=hrMemId%></td>-->
			<td height="26" class="listCellBg"><a href="./RegHorseListing.do?process=chngdesc&memid=<%=hrMemId%>"><%=hrMemId%></a>  </td>
								<td class="listCellBg"><%=horseName%></td>
								<td class="listCellBg"><%=amtFormat(amount)%></td>
								<% if((pendingAmount!=null)&&(pendingAmount.trim().length()!=0)){
									if(Float.parseFloat(pendingAmount)>0){
										balAmt = Float.parseFloat(amount)-Float.parseFloat(pendingAmount);
									}
								}
								%>
								<td class="listCellBg"><%=amtFormat(pendingAmount)%></td>
								<td class="listCellBg"><span class="styleBoldOne"><%=memStatus%></span></td>
								<td class="listCellBg"><input type="submit" name="click2" value="Make Payment" class="oneBtn" />
								</td>
						
					  <input type="hidden" name="memid" value="<%=hrMemId%>"/>
					   <input type="hidden" name="totalAmount" value="<%=amount%>" />
					  <input type="hidden" name="paymentId" value="<%=paymentId%>" />
								
								
							</tr>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						 </tr>
						 </form>
						   <%
						   }}
						   else{%>
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have Add/Change/Horse Pending Payment in Due !</span></td>        
						  </tr> 
						  <% }
						   %>
						  </table>
 <!-- Payments In Due for Horse Registeration and Horse Upgrade --> 
 							<div class="colspan"><span class="styleBoldTwo">Add-Rider/Owner & Change Horse/Rider/Owner Due Payment  Information</span> </div>
						    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						    </tr>
						    <tr>
								<td width="80"  class="tblRowHeadTwo">Horse Member Id</td>
								
								<td width="150"  class="tblRowHeadTwo">Horse Name</td>	
								<td width="125"  class="tblRowHeadTwo">Actual Amount </td>
								<!--<td  class="tblRowHeadTwo">Registered By </td>	-->							
								<td width="75"  class="tblRowHeadTwo">Balance Amount </td>
								<td width="68"  height="23" class="tblRowHeadTwo">Status </td>
								<td width="100" height="23"  class="tblRowHeadTwo"> Payment</td>								
								<!--
								<td width="112" class="tblRowHeadTwo">DownGrade</td>
								<td width="91" class="tblRowHeadTwo">Renew</td>
								-->
						      </tr>
						      
						<%
							ArrayList horseChngPendInfo = (ArrayList) request.getAttribute("horseChngPendInfo");
							System.out.print("horseChngPendInfo" + horseChngPendInfo);
							String pendingAmount ="";
							String horseRidOwnStatus ="";
							String requestId ="";
							if(horseChngPendInfo!=null && horseChngPendInfo.size()!=0){
							Iterator itr = 	horseChngPendInfo.iterator();
							while(itr.hasNext()) {	
								String[] payInfo =	(String[]) itr.next();
								String hrMemId = payInfo[0];
								String paymentId = payInfo[1];
								String amount = payInfo[2];
								String checkAmount = payInfo[3];
		  					    pendingAmount = payInfo[4];	
								String horseName = payInfo[5];
								String memStatus = payInfo[6];
								horseRidOwnStatus = payInfo[7];
								requestId = payInfo[8];
								
								if(Float.parseFloat(pendingAmount)>0){	   
								%>
						<form name="Approvedfrm4" method="post" action="RegHorseListing.do">
						<input type="hidden" value="AddChngDuePayProceed" name="process" />
							 <tr>
<td height="26" class="listCellBg"><a href="./RegHorseListing.do?process=chngdesc&memid=<%=hrMemId%>"><%=hrMemId%></a>  </td>
								<td class="listCellBg"><%=horseName%></td>
								<td class="listCellBg"><%=amtFormat(amount)%></td>
								<td class="listCellBg"><%=amtFormat(pendingAmount)%></td>
								 
								<td class="listCellBg"><span class="styleBoldOne"><%=memStatus%></span></td>
								<td class="listCellBg"><input type="submit" name="click2" value="Make Payment" class="oneBtn"/>
								</td>
								<input type="hidden" name="memid" value="<%=hrMemId%>"/>
								<input type="hidden" name="paymentId" value="<%=paymentId%>"/>
								<input type="hidden" name="requestId" value="<%=requestId%>"/>
								<input type="hidden" name="horseRidOwnStatus" value="<%=horseRidOwnStatus%>"/>
								 
							</tr>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						 </tr>
						 </form>
						   <%
						   }//If
						   }//while
						   }
						         
								System.out.print("requestId" + requestId);//If
						   if(pendingAmount.equals("0") || horseChngPendInfo.size()==0){%>
						  <tr>
							<td class="tableSpan" align="center" colspan="6"><span class="styleBoldOne">You Dont Have Add/Change/Horse Payments in Due !</span></td>        
						  </tr> 
						  <% }
						   %>
						  </table>
					  </div>
					         
							 
							 
							                                                            
					  <div class="spacer">&nbsp;</div>                                                      
					  
					<div class="alignCenter">
				<input type="button" value=" Back " class="gradBtn" onclick="javascript: history.go(-1);" />
																										
					</div>	
					
					<div class="spacer">&nbsp;</div>  
					
					</div>                                                                	
					 <%
                    String memberId=(String)session.getAttribute("memberId");   
					                                                 
                     %>
                                                                                                                                         
							<input type="hidden" value="<%=memberId%>" name="memberId"/>
						 
					</div>
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
