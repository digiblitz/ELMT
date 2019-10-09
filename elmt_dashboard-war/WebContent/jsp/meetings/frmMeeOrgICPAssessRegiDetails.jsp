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
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<bean:define id="orgDetailDisplay" name="ViewOrgDetails" scope="request"/>
<bean:define id="res" name="orgDetailDisplay" property="facilities" type="java.lang.String"/>
<bean:define id="payMode" name="orgDetailDisplay" property="paymentStatus" type="java.lang.String"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldTwo">Approve Organizer ICP Assessment Registration</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">&nbsp;</td>
				  </tr>

				  <tr>
					<td>
	
					
					<form name="frmMeeICPAssessment" id="myform" method="post" action="./IcpOrgRegFrm.do">
					
							
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						 
		
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								 <tr> 
									<td colspan="2" class="tblRowHead">ICP Assessment Details:</td>
								  </tr>
								   
								  <tr> 
									<td class="tableLeft">Assessment Level:</td>
									<th class="tableRight"><bean:write name="orgDetailDisplay" property="assesmentLevel"/></th>
								  </tr>
								  <tr> 
									<td class="tableLeft">Date:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="assesmentDate"/></td>
								  </tr>
								   <tr> 
									<td class="tableLeft">No. of Days:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="noOfDays"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Area:</td>
									<th class="tableRight"><bean:write name="orgDetailDisplay" property="areaName"/>&nbsp;</th>
								  </tr>
								  <tr> 
									<td class="tableLeft">Location:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="location"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Host ID:</td>
									<th class="tableRight"><bean:write name="orgDetailDisplay" property="hostMemberId"/></th>
								  </tr>
								  <tr> 
									<td class="tableLeft">First Name:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.firstName"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Last Name:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.lastName"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Address:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.address1"/><br/><bean:write name="orgDetailDisplay" property="memberContactDetails.address2"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Country:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.country"/></td>
								  </tr>
								  <tr> 
									<td height="24" class="tableLeft">State:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.state"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">City:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.city"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Zip:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.zip"/></td>
								  </tr>
							 </table>
							</td>
						 </tr>
						 
               <tr id="part2" >
						 <td colspan="2">							
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								  <tr> 
									<td class="tableLeft">Phone:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.phoneNo"/></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Fax:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.faxNo"/>&nbsp;</td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Email:</td>
									<th class="tableRight"><bean:write name="orgDetailDisplay" property="memberContactDetails.emailId"/>&nbsp;</th>
								  </tr>
								  <tr> 
									<td class="tableLeftTxtArea">Assessor Detail:</td>
									<td class="tableRight"><bean:write name="orgDetailDisplay" property="assessor"/>
									</td>
								  </tr>
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>

										<%
											String final_val ="";
											System.out.println("facility siz:"+res.length());
											String value[] = res.split("#");
											for(int i=0;i<value.length;i++)
											{
											if(!value[i].equalsIgnoreCase("null"))
											{
												final_val +=value[i]+","; 
											}
											}
										%>

									<td class="tableRight"><span class="styleBoldOne"><%=final_val%></span></td>
                                    
								  </tr>
								<!--  <tr>
									<td class="tableLeft"> If Other Specify:</td>
									<td class="tableRight">None</td>
								  </tr>
								  -->
							</table>
							
							</td>
						 </tr>
						  
					  	 <tr id="part3" >
						<td colspan="2">
						
						<table cellpadding="0" cellspacing="0" border="0" class="formLayout">

						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Land Owner Details : </span></td>
						  </tr>
						  <tr>
							<td class="tableLeft">Land Owner Name :</td>
							<th class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerName"/></th>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<th class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerBusinessName"/></th>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerAddress"/></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Country: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerCountry"/>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerState"/></td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerCity"/></td>
						  </tr>
						   <tr>
							<td class="tableLeft">Zip: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerZip"/></td>
						  </tr>
						  <tr>
							<td class="tableLeft">Phone: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerPhone"/></td>
						  </tr>						  
						  <tr>
							<td class="tableLeft style1">Fax: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="landOwnerFax"/>&nbsp;</td>
						  </tr><% out.print(""+payMode);
						  if(payMode.equalsIgnoreCase("check")){
						  %>
						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Transaction Details : </span></td>
						  </tr>
						   <tr>
							<td class="tableLeft style1">Check Number: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="checkNumber"/>&nbsp;</td>
						  </tr>
						   <tr>
							<td class="tableLeft style1">Check Date: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="checkDate"/>&nbsp;</td>
						  </tr>
						   <tr>
							<td class="tableLeft style1">Check Name: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="checkName"/>&nbsp;</td>
						  </tr>
						   <tr>
							<td class="tableLeft style1">Amount: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="amount"/>&nbsp;</td>
						  </tr>
						  <%}
						  else{
						   %>
						   <tr>
							<td class="tableLeft style1">Payment Date: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="paymentDate"/>&nbsp;</td>
						  </tr>
						   <tr>
							<td class="tableLeft style1">Amount: </td>
							<td class="tableRight"><bean:write name="orgDetailDisplay" property="amount"/>&nbsp;</td>
						  </tr>
						  <% } %>
						</table>
				
						</td>
					 </tr>
						 
						 <tr id="part4" class="holderDivTwo">
						<td colspan="2">
						
			   
					  <input type="hidden" name="process" value="edit"/>
				   
					  <input type="hidden" name="meetingId" value='<bean:write name="orgDetailDisplay" property="icpMeetingId"/>'/>
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">								 
								   <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Approval Status</td>
  							     </tr>
								  <tr>
									<td class="tableLeft">Status:</td>									
									<td class="tableRight">	<bean:write name="orgDetailDisplay" property="requestStatus"/>		
									</td>
								  </tr>	
								  <tr> 
									<td class="tableLeftTxtArea">Comments:</td>
									<td class="tableRight">
									<bean:write name="orgDetailDisplay" property="comments"/>&nbsp;
								  </tr>

								
							<logic:equal name="orgDetailDisplay" property="requestStatus" value="Rejected"> 

<!--		
<tr>
<td colspan="2" class="alignCenter"><input type="submit" value="Edit" class="gradBtn" /></td>
</tr>-->
							   
							   </logic:equal>
							<logic:notEqual name="orgDetailDisplay" property="requestStatus" value="Rejected"> 

							<tr>
									<td colspan="2" class="alignCenter"><input type="button" value="Back" class="gradBtn" onclick="javascript:history.back(-1);" /></td>
	

							   </tr></logic:notEqual>
							</table>
                      
						
						</td>
					 </tr>
				
					  </table>
					  </form>
					  
					</td>
				  </tr>
				  <tr>
						<td>&nbsp; 
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
</form>
 </body>
</html>
