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

 
/*function chk(){
			
				if(document.frmMeeAnnualConvRegister.comments.value==""){
				alert("Enter Comments");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}
				
				if(document.frmMeeAnnualConvRegister.comments.value.length>1024){
				alert("Comments is out of range");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}
				if(document.frmMeeAnnualConvRegister.comments.value.indexOf('  ')!==-1){
				alert("Please avoid unwanted white space");
				document.frmMeeAnnualConvRegister.comments.focus();
				return false;
				}


	return true;
}*/
	
</script>
<%! 
	String  nullCheck(String fieldName){
	String retValue = "NA";
	if(fieldName!=null && fieldName.trim().length()!=0){
	retValue = fieldName;
	}
	return retValue;
	}
	%>
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
						  try
						  {
						  
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
								 String reqStatus = objAnnualRegDet.getRequestStatus();
								 String comments = objAnnualRegDet.getComments();
								 Date addDate = objAnnualRegDet.getAddDate();
								 String checkNumber = objAnnualRegDet.getCheckNumber();
								 String paymentMode = objAnnualRegDet.getPaymentMode();
								 String checkName = objAnnualRegDet.getPaymentBy();
								 String txId = objAnnualRegDet.getTransactionId();
								 String bankName = objAnnualRegDet.getBankName();
								 String paymentId = objAnnualRegDet.getPaymentId();
								 System.out.println("getPaymentId :"+paymentId);
                    			 Date checkDate = objAnnualRegDet.getCheckDate();
								 String checkDateValue = "N/A";
								 
								  if(comments==null || comments.trim().length()==0)  comments = " ";
								 
								 if(emailId==null || emailId.trim().length()==0)  emailId = "N/A";
								 if(fName==null || fName.trim().length()==0)  fName = "N/A";
								 if(lastname==null || lastname.trim().length()==0)  lastname = "N/A";
								 if(checkNumber==null || checkNumber.trim().length()==0)  checkNumber = "N/A";
								 if(checkName==null || checkName.trim().length()==0)  checkName = "N/A";
								  if(bankName==null || bankName.trim().length()==0)  bankName = "N/A";
								   
								// String ardID  = objAnnualRegDet.getRequestStatus();
								 //String ardID  = objAnnualRegDet.getRemarks();
								// String ardID  = objAnnualRegDet.getAddDate();
						
						%>
						  <tr>
							<td class="tableLeft"> First Name:</td>
							<td class="tableRight"><%=fName%></td>
						  </tr>
						   <tr>
							<td class="tableLeft"> Last Name:</td>
							<td class="tableRight"><%=lastname%></td>
						  </tr>
						 
						  <tr>
							<td class="tableLeft"> Email:</td>
							<td class="tableRight"><%=emailId%></td>
						  </tr>
						
						  <%if(reqStatus!=null && reqStatus.equals("Pending")){%>
									<tr> 
										<td class="tableLeftTxtArea">Payment Mode:</td>
										<td class="tableLeftTxtArea"><%=paymentMode%>&nbsp; </td>
									</tr>
						<%}else{%>
								  <tr> 
									<td class="tableLeftTxtArea">Request Status:</td>
									<td class="tableLeftTxtArea"><%=reqStatus%></td>
									</tr>
							    <tr> 
									<td class="tableLeftTxtArea">Payment Mode:</td>
									<td class="tableLeftTxtArea"><%=paymentMode%>&nbsp; </td>
								</tr>
								    						  
						
							<%
							}
							%>
							<%if(paymentMode!=null)
							{
							if(paymentMode.equals("Check")){%>
						  <tr>
							<td class="tableLeft"> Check Number:</td>
							<td class="tableRight"><%=checkNumber%></td>
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
						  
						  <tr>
						     <td class="tableLeft">Bank Name: </td>
						     <td class="tableRight"><%=bankName%></td>
					      </tr>
						   <tr>
							<td class="tableLeft">  Name on Check:</td>
							<td class="tableRight"><%=checkName%></td>
						  </tr>
						 
							<%}
							else{%>
							
						   <tr>
							<td class="tableLeft">Transaction Id:</td>
							<td class="tableRight"><%=nullCheck(txId)%></td>
						  </tr>
							<%}%>
						 <tr>
							<td class="tableLeft"> Amount:</td>
							<td class="tableRight"><strong>$ <%=amount%></strong> </td>
						  </tr>	
						  
						
						   <tr>
							<td colspan="3">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" >
								  <tr>
								<td width="107" class="tblRowHead">Badge Name </td>
								<td width="106" class="tblRowHead">Full Name </td>
								<td width="93" class="tblRowHead">Member Type </td>
								<td width="93" class="tblRowHead">Request Status </td>
								<td width="90" class="tblRowHead">Request Date</td>
							   </tr>
								  <%
					
						 ArrayList viewAllUserDetails=(ArrayList)request.getAttribute("viewAllUserDetails");
						 if(viewAllUserDetails!=null && viewAllUserDetails.size()!=0){ 
							Iterator  enm1 = viewAllUserDetails.iterator(); 
								while(enm1.hasNext()){
								HLCAnnualRegistrationDetailVO objRegDet = (HLCAnnualRegistrationDetailVO)enm1.next();
								
								String requestStatus = objRegDet.getRequestStatus();
								Date date = objRegDet.getAddDate();
								String remarks = objRegDet.getRemarks();
								String lName = objRegDet.getLastName();
								String firstName="";
								String memTypeName="";
								String badgeName="";
								//String annualMeetingId ="";
								//String ardId  = "";
								//ardId = objRegDet.getArdId();
								firstName = objRegDet.getFirstName();
								memTypeName = objRegDet.getMemTypeName();
								badgeName = objRegDet.getBadgeName();
								
			   %>
									 <tr>
									<td class="listCellBg"><%=badgeName%> </td>
									<td class="listCellBg"><strong><%=firstName%>&nbsp;<%=lName%></strong> </td>
									<td class="listCellBg"><%=memTypeName%></td>
									<td class="listCellBg"><span class="styleBoldOne"><%=requestStatus%></span></td>
									<td class="listCellBg"><%=sdf.format(date)%></td>
								   </tr>
								   <%
								   }
								   }
								   }
								   %>
								</table>
							</td>
						  </tr>	
						  
						        
								  <tr>
								  <td class="alignCenter" colspan="2">	
								   <form name="frmMeeAnnualConvRegister" method="post" action="AnnualRegList.do" onsubmit="return chk();">
						<input type="hidden"  name="annualMeetingId" value="<%=annualMeetingId%>" />
						<input type="hidden" name="paymentId" value="<%=paymentId%>" />
								<input type="hidden"  name="memProcess" value="massUpdateStatus" />	
	 
								<tr>
									<td class="tableLeft">Do you want Approve or Reject:</td>
									  <td>	
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
									%>
									</td>
									
								  </tr>	  
								  <tr> 
									<td class="tableLeftTxtArea">Comments:</td>
									<td class="tableRight"><textarea name="comments" class="textAreaOne" rows="5"></textarea>
								    </td>
								  </tr>
								  <tr>
								  <td class="alignCenter" colspan="2">	
								    <input type="submit" value="Submit" class="gradBtn" />&nbsp;&nbsp;&nbsp;
									<input type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);"/>&nbsp;</td></tr>
								<tr> 
									
								  </tr>
						  </form>
									</td></tr>
						
						  <%
						  }
						  }
								   catch(Exception e)
								   {
								   		System.out.print("Error in JSP :");
										e.printStackTrace();
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