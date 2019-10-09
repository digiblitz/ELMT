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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">
function validate()
{
	if(document.frmMeeAnnuTicketAdd.act_typ.options.selected=="Select One")
	{
	alert('Select Any of the Activity Type');
	document.frmMeeAnnuTicketAdd.act_typ.focus();
	return false;
	}
	if(document.frmMeeAnnuTicketAdd.act_typ.selected == 0)
	{
	alert('Select Any of the Activity Type');
	document.frmMeeAnnuTicketAdd.act_typ.focus();
	return false;
	}
	if((document.frmMeeAnnuTicketAdd.ticket.value=="")||(document.frmMeeAnnuTicketAdd.ticket.value.indexOf(' ')==0))
	{
	alert('Ticket Cannot be empty');
	document.frmMeeAnnuTicketAdd.ticket.focus();
	return false;
	}
}
function fill(val)
{
document.frmMeeAnnuTicketAdd.act_typ.value=val;
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
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
		
	<form name="frmMeeAnnuTicketAdd" method="post" action="./AnnualTcktDetails.do" onsubmit="return validate();">
							
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Meetings:  <span class="styleBoldTwo">New Annual Ticket </span></td>
						</tr>
						<%
						String errSt = (String)request.getAttribute("err");
						if(errSt.equals("st")){
						
						%>
						  <tr> 
							<td height="25" colspan="2" class="styleError">Ticket you try to avail is already booked!</td>
						  </tr>
						  <%
						  }
						  %>
						   <tr> 
							<td colspan="2" class="tblRowHead"> <div class="rowHead">
		<span class="asterisk">*</span> Fields mark with asterisk(*) are mandatory
	</div></td>
						  </tr>
			  	<input type="hidden" name="process" value="insert">
						  <tr> 
							<td width="300" class="tableLeft">Activity Type : </td>
							<td width="297" class="tableRight">	
				<select name="act_type" id="act_type" class="selectboxOne" onchange="fill(this.value);">
				<option selected="selected" value="Select One" >Select One</option>
				<%		   		ArrayList detail = (ArrayList) session.getAttribute("details");
								System.out.println("Array List Size is"+detail.size());  
								Iterator itr = detail.iterator();
								while(itr.hasNext())
								{
								String[] s= (String [])itr.next();
								String annualTId = s[0];
								String availTicket = s[1];
								String meeId  = s[2];
								String meeName = s[3];
				%>
				<option value="<%=annualTId%>"><%= meeName%></option>	
							 <% } %>		
				</select><span class="asterisk">&nbsp;*</span></td>
				<input type="hidden" name="act_typ"/>
						  </tr>
						  <tr>
                            <td class="tableLeft">Total Tickets </td>
						    <td class="tableRight"><input name="ticket" id="ticket" type="text" class="textboxOne"/>
					        <span class="asterisk">&nbsp;*</span></td>
					      </tr>
						  
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
						<input  type="submit" class="gradBtn" value="Submit" />
						<input type="reset" class="gradBtn" value="Reset" /></td>
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
