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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeAnnualConvRegister.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" /> 

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
					
						<form name="frmMeeAnnualConvRegister" id="myform" action="./ViewAnnualConRegDetailList.do">
					
						<% 
						String basicInfo [] = new String [20];
						
						Vector memberDispVect=(Vector)request.getAttribute("viewMember");
						System.out.println("vector Element in Member Detail: "+memberDispVect);
						System.out.println("vector Element SIZE : "+memberDispVect.size());
						if (  memberDispVect.elementAt(0) != null && memberDispVect.size() !=0) {
						   basicInfo   = (String[]) memberDispVect.elementAt(0);
						}	   
						
						%>

						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="formLayout">
							<tr>
								<td colspan="2" class="tblMainHead"><strong>sss</strong> Meetings:  <span class="styleBoldTwo">Annual Meeting &amp; Convention Registration Details</span></td>
							</tr>
							<tr>
								<td colspan="2" class="tblDescrp">
								<img src="images/usea_logo.jpg" width="100" height="99" class="imgFloatLeft"/>
								<br />
								<strong>You are viewing the details of the Annual Convention Registration you had applied for.</strong> 
								<br /><br />
								Your approval status is shown below.
								</td>
							</tr>

							<tr> 
								<td colspan="2" class="tblRowHead"> <span class="rowHead">Mailing Information</span></td>
							</tr>
							<tr> 
								<td class="tableLeft">Membership No.:</td>
								<th class="tableRight"><%=basicInfo[20] %></th>
							</tr>
							<tr>
								<td class="tableLeft"> Email ID:</td>
								<td class="tableRight"><%=basicInfo[6] %></td>
							</tr>
						  
						  <tr>
							<td class="tableLeft"> First Name:</td>
							<td class="tableRight"><%=basicInfo[4] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Last Name:</td>
							<td class="tableRight"><%=basicInfo[5] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><%=basicInfo[11]+" "+basicInfo[12]  %> </td>
						  </tr>
<%//{annualMeetingId,userId,badgeInfo,totalAmount,firstName,lastName,emailId,areaName,requestStatus,
               // addDate,suite,address1,address2,city,state,country,zip,phoneNo,mobileNo,faxNo}; %>
						  <tr>
							<td class="tableLeft"> Country:</td>
							<td class="tableRight"><%=basicInfo[15] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight"><%=basicInfo[14] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> City:</td>
							<td class="tableRight"><%=basicInfo[13] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Zip Code:</td>
							<td class="tableRight"><%=basicInfo[16] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Phone:</td>
							<td class="tableRight"><%=basicInfo[17] %></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Fax:</td>
							<td class="tableRight"><%=basicInfo[19] %></td>
						  </tr>
							<tr> 
								<td class="tableLeft">Area: </td>
								<td class="tableRight"><%=basicInfo[7] %></td>
							</tr>
						  <tr>
							<td colspan="2" class="tblRowHead"> Badge Information:</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Name of Badge:</td>
							<td class="tableRight"><%=basicInfo[2] %></td>
						  </tr>
						  <tr>
							<td colspan="2" class="tblRowHead"> Registration Information:</td>
						  </tr>

						<%
		   		
					 Vector specificationDetail1=(Vector)request.getAttribute("viewSpecification");
  					 System.out.println("Inside JSP specificationDetail: "+specificationDetail1);
					 if(specificationDetail1!=null && specificationDetail1.size()!=0){ 
						 String spec1[] = (String [])specificationDetail1.elementAt(0);
						 //int len = spec1.length();
						 String daysApplied[] = new String [10];
							if (spec1.length > 3 && spec1[3] != null) {
								daysApplied = spec1[3].split("#");
							}
							System.out.print(" injsp specificationName:  "+spec1[0]);
							System.out.print(" userTypeName:  "+spec1[1]);
							System.out.print(" noOfTickets:  "+spec1[2]);
							System.out.print(" daysApplied:  "+spec1[3]);
//{specificationName,userTypeName,noOfTickets,daysApplied};
		   %>

						  <tr>
							<td class="tableLeft"> Registration Type:</td>
							<td class="tableRight"><span class="styleBoldOne"><%=spec1[0] %></span></td>
						  </tr>
						  <% if ( spec1[0].equalsIgnoreCase("Daily Basis") ) {%>
						 <tr>
						 	<td class="tableLeft"> Dates Opted For:</td>
							<th class="tableRight"><%=daysApplied[0] %></th>
						  </tr>
						   <% if (daysApplied.length > 1) { %>
						  <tr>
						  	<td class="tableLeft">&nbsp;</td>
							<th class="tableRight"><%=daysApplied[1] %></th>
						  </tr>
						   <% } if (daysApplied.length > 2) { %>
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<th class="tableRight"><%=daysApplied[2] %></th>
						  </tr>

						  <%  } if (daysApplied.length > 3) { %>
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<th class="tableRight"><%=daysApplied[3] %></th>
						  </tr>
						  <% } } %>
						  <tr>
							<td class="tableLeft"> Member Type:</td>
							<td class="tableRight"><span class="styleBoldOne"><%=spec1[1] %> </span></td>
						  </tr>
						  <% } %>
						  
						  <!--
						  <tr>
							<td colspan="2" class="tableLeft" id="price">
								<a href="#" class="linkFour">View Price Details</a> 
							</td>
						  </tr>
						  -->
						  <tr>
							<td colspan="2" class="tblRowHead"> Other Activities Information: <span class="styleBoldTwo">Activity Type</span> </td>
						  </tr>

						<%
		   		
					 Vector specificationDetail=(Vector)request.getAttribute("viewSpecification");
  					 System.out.println("Inside JSP specificationDetail: "+specificationDetail);
					 if(specificationDetail!=null && specificationDetail.size()!=0){ 
					  Enumeration enm1=specificationDetail.elements();
					  
					  while(enm1.hasMoreElements()){
							String[] spec = (String[])enm1.nextElement();
							for (int i=0;i<spec.length; i++) {
								System.out.println("Content : "+spec[i]);
							}
							
							//String daysApplied[] = spec[3].split("#");
							System.out.print(" injsp specificationName:  "+spec[0]);
							System.out.print(" userTypeName:  "+spec[1]);
							System.out.print(" noOfTickets:  "+spec[2]);
							System.out.print(" daysApplied:  "+spec[3]);
//{specificationName,userTypeName,noOfTickets,daysApplied};
		   %>

						  <tr>
						  	<td colspan="2">
								<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
								  <tr>
									<td class="tblMainHead" colspan="3"><%=spec[0] %></td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft"> Member Type:</td>
									<td class="tableRight"><%=spec[1] %> </td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Number of Tickets:</td>
									<td class="tableRight"><%=spec[2]+"  Nos." %> </td>
								  </tr>
						  		</table>
							</td>
						  </tr>

						  <%		 }
						}
					%>
						 <!-- <tr>
						  	<td colspan="2">
								<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
								  <tr>
									<td class="tblMainHead" colspan="3">
									 Attend the Evening Hall of Fame Dinner</td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft"> Member Type:</td>
									<td class="tableRight">Non-Member</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Number of Tickets:</td>
									<td class="tableRight">2 Nos.</td>
								  </tr>
						  		</table>
							</td>
						  </tr>
						  <tr>
						  	<td colspan="2">
								<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
								  <tr>
									<td class="tblMainHead" colspan="3">
									 Attend the Area IV Reception.</td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft"> Member Type:</td>
									<td class="tableRight">Junior Member</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Number of Tickets:</td>
									<td class="tableRight">1 Nos.</td>
								  </tr>
						  		</table>							</td>
						  </tr> -->
						
							<tr>
								<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
							</tr>  
								  
						  <tr>
							<td class="tableLeft"><strong>Total Amount:</strong></td>
							<td class="tableRight"> <strong>$</strong><span class="styleBoldOne"> <%=basicInfo[3] %></span> </td>
						  </tr>
						  <tr>
							<td colspan="2" class="tblRowHead"> Approval Status </td>
						  </tr>
						  <tr>
							<td class="tableLeft">Status:</td>
							<td class="tableRight"><span class="styleBoldOne"><%=basicInfo[8] %></span></td>
						  </tr>
						   <!--<tr> 
							<td class="tableLeftTxtArea">Comments:</td>
							<td class="tableRight">Comment goes here..Comment goes here..Comment goes here..Comment goes here..
							Comment goes here..Comment goes here..Comment goes here..Comment goes here..Comment goes here..Comment goes here..
							Comment goes here..Comment goes here..Comment goes here..Comment goes here..Comment goes here..Comment goes here..
							Comment goes here..Comment goes here..</td>
						   </tr> -->
						  <tr> 
							<td colspan="2" class="alignCenter"><input type="button" value=" Back " class="gradBtn" onclick="javascript:history.back(-1);" /></td>
						  </tr>
					  </table>
					  </form>
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
