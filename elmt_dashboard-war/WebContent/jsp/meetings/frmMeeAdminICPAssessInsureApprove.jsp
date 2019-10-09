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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function check()
{
	if((document.frmMeeICPAssessment.commentarea.value=="")||(document.frmMeeICPAssessment.commentarea.value.indexOf(' ')==0)){
		alert('Enter Comment on Approval/Rejection');
		document.frmMeeICPAssessment.commentarea.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.commentarea.value.length>1024){
		alert('Comments exceeds the maximum size');
		document.frmMeeICPAssessment.commentarea.focus();
		return false;
	}
	if(document.frmMeeICPAssessment.commentarea.value.indexOf('  ')!==-1){
	alert("Please avoid  unwanted white space");
	document.frmMeeICPAssessment.commentarea.focus();
	return false;
	}
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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldTwo">Approve Organizer ICP Assessment Registration</span></td>
				  </tr>
				  
				  <tr>
					<td>
	
					
					<form name="frmMeeICPAssessment" id="myform" method="post" action="./listAdminOrg.do" onsubmit="return check();">
					<%
					Vector orgDet = (Vector) request.getAttribute("ViewOrgDetails");
							 if(orgDet!=null && orgDet.size()!=0){ 
								Enumeration enm1=orgDet.elements();
								 while(enm1.hasMoreElements()){
									 String[] icpVal = (String[])enm1.nextElement();
					%>		
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								 <tr> 
									<td colspan="2" class="tblRowHead">ICP Assessment Details:</td>
								  </tr>
								   
								  <tr> 
									<td class="tableLeft">Assessment Level:</td>
									<th class="tableRight"><%=icpVal[1]%></th>
								  </tr>
								  <tr> 
									<td class="tableLeft">Date:</td>
									<td class="tableRight"><%=icpVal[2]%></td>
								  </tr>
								   <tr> 
									<td class="tableLeft">No. of Days:</td>
									<td class="tableRight"><%=icpVal[3]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Area:</td>
									<th class="tableRight"><%=icpVal[4]%></th>
								  </tr>
								  <tr> 
									<td class="tableLeft">Location:</td>
									<td class="tableRight"><%=icpVal[5]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Host ID:</td>
									<th class="tableRight"><%=icpVal[7]%></th>
								  </tr>
								  <tr> 
									<td class="tableLeft">First Name:</td>
									<td class="tableRight"><%=icpVal[27]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Last Name:</td>
									<td class="tableRight"><%=icpVal[28]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Address:</td>
									<td class="tableRight"><%=icpVal[30]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Country:</td>
									<td class="tableRight"><%=icpVal[31]%></td>
								  </tr>
								  <tr> 
									<td height="24" class="tableLeft">State:</td>
									<td class="tableRight"><%=icpVal[32]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">City:</td>
									<td class="tableRight"><%=icpVal[33]%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Zip:</td>
									<% String zip ="";
									if( icpVal[34]!=null)	{	zip = icpVal[34];	}	%>
									<td class="tableRight"><%=zip%></td>
								  </tr>
							 </table>
							</td>
						 </tr>
						 
               <tr id="part2" >
						 <td colspan="2">							
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								  <tr> 
									<td class="tableLeft">Phone:</td>
									<% String phno ="";
									if( icpVal[35]!=null)	{	phno = icpVal[35];	}%>									
									<td class="tableRight"><%=phno%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Fax:</td>
									<% String fax;
									if((icpVal[36]!=null)||(icpVal[36].trim().length()!=0))	{	fax="N.A";   }
									else	{   fax = icpVal[36];	}	%>									
									<td class="tableRight"><%=fax%></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Email:</td>
									<% String mail=icpVal[29];

									if((mail==null)||(mail.trim().length()==0)){
										mail = "N/A";
									}	%>
									<th class="tableRight"><%=mail%></th>
								  </tr>
<!--								  <tr> 
									<td class="tableLeftTxtArea">Assessor Detail:</td>
									<td class="tableRight">< %=icpVal[1]%></td>
								  </tr>
-->								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>
									<%String[] facil = icpVal[10].split("#");
									String facility="";
									for(int i=0;i<facil.length;i++){
									if(!facil[i].equals("null")){
											facility+=facil[i]+",";
										}
									}
									%>
									<td class="tableRight"><span class="styleBoldOne"><%=facility%></span></td>
                                    
								  </tr>
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
							<th class="tableRight"><%=icpVal[11]%></th>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<th class="tableRight"><%=icpVal[12]%></th>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><%=icpVal[13]%></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Country: </td>
							<td class="tableRight"><%=icpVal[16]%></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight"><%=icpVal[15]%></td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><%=icpVal[14]%></td>
						  </tr>
						   <tr>
							<td class="tableLeft">Zip: </td>
							<td class="tableRight"><%=icpVal[17]%></td>
						  </tr>
						  <tr>
							<td class="tableLeft">Phone: </td>
							<td class="tableRight"><%=icpVal[18]%></td>
						  </tr>						  
						  <tr>
							<td class="tableLeft style1">Fax: </td>
							<td class="tableRight"><%=icpVal[19]%></td>
						  </tr>
						</table>
						<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Transaction Details : </span></td>
						  </tr>
						  <% 
						  String paystat = icpVal[51];
						  if(paystat.equalsIgnoreCase("check")){
						  %>
						  <tr>
							<td class="tableLeft style1">Check Number:</td>
							<td class="tableRight"><%=icpVal[47]%></td>
						  </tr>
						  <tr>
							<td class="tableLeft style1">Check Name: </td>
							<td class="tableRight"><%=icpVal[48]%></td>
						  </tr>						  
						  <tr>
							<td class="tableLeft style1">Amount: </td>
							<td class="tableRight"><%=icpVal[49]%></td>
						  </tr>						  
						  <tr>
							<td class="tableLeft style1">Check Date: </td>
							<td class="tableRight"><%=icpVal[46]%></td>
						  </tr>						  						  
						  <tr>
							<td class="tableLeft style1">Bank Name: </td>
							<td class="tableRight"><%=icpVal[45]%></td>
						  </tr>	
						  <%	}
						  else{
						  %>					  
						  <tr>
							<td class="tableLeft style1">Transaction Id: </td>
							<td class="tableRight"><%=icpVal[54]%></td>
						  </tr>						  						  
						  <tr>
							<td class="tableLeft style1">Amount: </td>
							<td class="tableRight"><%=icpVal[49]%></td>
						  </tr>	
						  <% } %>
						  </table>						
					  <input type="hidden" name="icpMeetingId" value="<%=icpVal[0]%>"/>				
						</td>
					 </tr>
						 <tr id="part4" class="holderDivTwo">
						<td colspan="2">
						
			   
					  <input type="hidden" name="method" value="AdminupdateStatus"/>
				   

							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">								 
								   <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Approval Status</td>
  							     </tr>
								 <%
								 String stat = icpVal[25];
								 if(stat.equalsIgnoreCase("Pending")){
								 %>
						  <tr>
								<td class="tableLeftTxtArea">Comments:</td>
														
								<td class="tableRight"><textarea name="commentarea" rows="5" class="textAreaOne"></textarea>&nbsp;<span class="asterisk">*</span></td>
							</tr>								 
						  <tr> 
							<td class="tableLeft">Status:</td>
							<td class="tableRight">
								<input name="usrStat" type="radio" value="Approved" checked="checked"/>
							  	Approve
							  	<input name="usrStat" type="radio" value="Rejected" />
								Reject
							</td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							<input name="process" type="submit" class="gradBtn" value="Submit" />	
							<input name="back" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" />	
							</td>
						  </tr>
							<%	}	
							else{	%>
							<tr>
								<td class="tableLeft style1">Status: </td>
								<td class="tableRight"><%=icpVal[25]%></td>
							</tr>
							<tr>
								<td colspan="2" class="alignCenter"><input name="back" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>
							</tr>
							<% }													
							}	}%> 
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
<!--
 out.print(" 38 "+icpVal[38]+"<br>");
 out.print(" 39 "+icpVal[39]+"<br>");
 out.print(" 40 "+icpVal[40]+"<br>");
 out.print(" 41 "+icpVal[41]+"<br>");
 out.print(" 42 "+icpVal[42]+"<br>");									 									 
 out.print(" 43 "+icpVal[43]+"<br>");
 out.print(" 44 "+icpVal[44]+"<br>");									 									 									 									 
 out.print(" 45 "+icpVal[45]+"<br>");
 out.print(" 46 "+icpVal[46]+"<br>");
 out.print(" 47 "+icpVal[47]+"<br>");
 out.print(" 48 "+icpVal[48]+"<br>");
 out.print(" 49 "+icpVal[49]+"<br>");
 out.print(" 50 "+icpVal[50]+"<br>");
 out.print(" 51 "+icpVal[51]+"<br>");
 out.print(" 52 "+icpVal[52]+"<br>");
 out.print(" 53 "+icpVal[53]+"<br>");
 out.print(" 54 "+icpVal[54]+"<br>");
 out.print(" 55 "+icpVal[55]+"<br>");
 out.print(" 56 "+icpVal[56]+"<br>");
 out.print(" 57 "+icpVal[57]+"<br>");									 									 									 									 									 									 									 									 									 									 									 									 									 
 out.print(" 58 "+icpVal[58]+"<br>");
 out.print(" 59 "+icpVal[59]+"<br>");
 out.print(" 60 "+icpVal[60]+"<br>");							 									 									 									 									 									 									 									 									 									 									 									 									 
-->
