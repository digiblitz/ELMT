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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script language="javascript">
function validate()
{
	if((document.frmMeeAnnuTicketEdit.ticket.value=="")||(document.frmMeeAnnuTicketEdit.ticket.value.indexOf(' ')==0))
	{
	alert('Ticket Cannot be empty');
	document.frmMeeAnnuTicketEdit.ticket.focus();
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
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
<%
			String annualTId = "";
			String availTicket = "";
			String meeId = "";
			String meeName = "";
%>								
	<form name="frmMeeAnnuTicketEdit" method="post" action="./AnnualTcktDetails.do" onsubmit="return validate();">
	<input type="hidden" name="process" value="updateedit"/>
							
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Meetings:  <span class="styleBoldTwo">Edit Annual Ticket </span></td>
						</tr>
			<%
				String errSt = (String)request.getAttribute("err");
				if(errSt.equals("st")){
			%>						
						  <tr> 
							<td height="25" colspan="2" class="styleError">Tickets booked already!</td>
						  </tr>
					    <%
								  }
						%>						  
						   <tr> 
							<td colspan="2" class="tblRowHead"> <div class="rowHead">
		<span class="asterisk">*</span> Fields mark with asterisk(*) are mandatory
	</div></td>

<%
		String[] s =(String[])session.getAttribute("value");
		//out.print(s);
		if(s!=null && s.length!=0){
			  annualTId = s[0];
//			  out.print(annualTId);
			  availTicket = s[1];
			  meeId = s[2];
			  meeName = s[3];
				}
%>
						  </tr>
						  <tr> 
							<td width="300" class="tableLeft">Activity Type : </td>
							<td width="320" class="tableRight"><%=meeName%></td>
						  </tr>
						  <tr>
                            <td class="tableLeft">Total Tickets </td>
						    <td class="tableRight"><input name="ticket" type="text" class="textboxOne" value="<%=availTicket%>"/>
					        <span class="asterisk">*</span></td>
					      </tr>
					  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
						<input name="Submit" type="submit" class="gradBtn" value="Submit"/>
						<input name="Submit" type="button" class="gradBtn" value="Cancel"  onclick="javascript:history.back(-1);"/>						</td>
						  </tr>
					  </table>
					<input name="act_typ" type="hidden" value="<%=annualTId%>"/>
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
