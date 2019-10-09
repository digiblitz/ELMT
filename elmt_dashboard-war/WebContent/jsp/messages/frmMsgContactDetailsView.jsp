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
<bean:define id="HLCContactDetails" name="contactForm" scope="request"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />

</head>

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
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<html:form method="post" action="/contactAction">
                 <input type="hidden" name="method" value="search"/>

				<table border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Address Book - View Contact Details </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
						<span class="msgHead">Address Book</span>  - <span class="styleBoldOne">View Contact Details </span><br />
						You are viewing tde details of tde contact you had seleted. <br />
						<br />
					</td>
				  </tr>
						  <tr>
							<td>
							
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead"> Contact Details </td>
								  </tr>
								  <tr>
									<td height="25" >
										
										<table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
										  
										  <tr>
											<td class="tableLeft">First Name: </td>
											<td class="tableRight"><bean:define id="fName" name="HLCContactDetails" property="firstname" type="java.lang.String" /><%=fName%></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Middle Name: </td>
											<td class="tableRight"><bean:write name="HLCContactDetails" property="middlename"   /></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Last Name: </td>
											<td class="tableRight"><bean:define id="lName" name="HLCContactDetails" property="lastname" type="java.lang.String" /><%=lName%></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Nick Name: </td>
											<td class="tableRight"><bean:define id="nName" name="HLCContactDetails" property="nickname" type="java.lang.String" /><%=nName%></td>
										  </tr>
										  <tr>
											<td class="tableLeft">EMail ID:</td>
											<td class="tableRight"><bean:define id="emailID" name="HLCContactDetails" property="emailid" type="java.lang.String" /><%=emailID%></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Alternate Email ID: </td>
											<td class="tableRight"><bean:write name="HLCContactDetails" property="alteremailid"   /></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Phone:</td>
											<td class="tableRight"><bean:define id="pphone" name="HLCContactDetails" property="phone" type="java.lang.String" /><%=pphone%></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Mobile:</td>
											<td class="tableRight"><bean:write name="HLCContactDetails" property="mobile"   /></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Fax:</td>
											<td class="tableRight"><bean:write name="HLCContactDetails" property="fax"   /></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Street:</td>
											<td class="tableRight"><bean:define id="pstreet" name="HLCContactDetails" property="street" type="java.lang.String" /><%=pstreet%></td>
										  </tr>
										  <tr class="formLayout">
											<td class="tableLeft">Country:</td>
											<td class="tableRight"><bean:define id="pcountry" name="HLCContactDetails" property="country" type="java.lang.String" /><%=pcountry%></td>
										  </tr>
										  <tr class="formLayout">
											<td class="tableLeft">State:</td>
											<td class="tableRight"><bean:define id="pstate" name="HLCContactDetails" property="state" type="java.lang.String" /><%=pstate%></td>
										  </tr>
										  
										  <tr>
											<td class="tableLeft">City:</td>
											<td class="tableRight"><bean:define id="pcity" name="HLCContactDetails" property="city" type="java.lang.String" /><%=pcity%></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Zipcode:</td>
											<td class="tableRight"><bean:define id="pzip" name="HLCContactDetails" property="zipcode" type="java.lang.String" /><%=pzip%></td>
										  </tr>
										</table>
									</td>
								  </tr>
								  
								  <tr>
									<td height="25" class="alignCenter"><input type="submit" name="addContact22" value="Back To Address Book" class="gradBtn" style="width:140px;" />					</td>
								  </tr>
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE tdIS ROW --></td>
								  </tr>
						</table>
					
					</td>
				  </tr>
				</table>
				</html:form>

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
