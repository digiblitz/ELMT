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
<script src="javascripts/gen_validatorv2.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>
<script src="javascripts/contactForm.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script>
function fire()
{
  window.location="contactAction.do?method=initAdd";
  return;
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
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
  <form name="contactForm" method="post" action="contactAction.do" onsubmit="return myvalidate();">
				<input type="hidden" name="method" value="save"/>
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Address Book - Edit Contacts To Your Address Book </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />--><br />
						<span class="msgHead">Address Book</span>  - <span class="styleBoldOne">Add A Contact</span> <br />
						<br />
						You can <strong>Add </strong> a contact to your <strong>Address Book</strong> through the following interface. <br />
						<br />
					</td>
				  </tr>
						  <tr>
							<td>
							
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead">Add A Contact </td>
								  </tr>
								  <tr>
									<td height="25" >
										
										<table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
										  
										  <tr>
											<td class="tableLeft">First Name: </td>
											<td class="tableRight">  
											<input name="firstname" type="text" class="textboxOne" size="20" maxlength="25" />
											  <span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Middle Name: </td>
											<td class="tableRight">
											<input type="text" name="middlename" class="textboxOne"  size="20" />
											  <span class="asterisk"></span></td>
										  </tr>
										  <tr >
											<td class="tableLeft">Last Name: </td>
											<td class="tableRight">
											<input name="lastname" type="text" class="textboxOne"  size="20" maxlength="25" />
											 <span class="asterisk">*</span> </td>
										  </tr>
										  <tr>
											<td class="tableLeft">Nick Name: </td>
											<td class="tableRight">  
											<input name="nickname" type="text" class="textboxOne"  size="20" maxlength="25" />
											  <span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">EMail ID:</td>
											<td class="tableRight">
											<input type="text" name="emailid" class="textboxOne"size="20" />
											<span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Alternate Email ID: </td>
											<td class="tableRight">
											<input type="text" name="alteremailid" class="textboxOne"  size="20" />
										    <span class="asterisk"></span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Phone:</td>
											<td class="tableRight">
											<input type="text" name="phone" class="textboxOne"  size="20" />
											<span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Mobile:</td>
											<td class="tableRight">
										  <input type="text" class="textboxOne" name="mobile"  size="20" />
	 									  <span class="asterisk"></span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Fax:</td>
											<td class="tableRight">
											<input type="text" name="fax" class="textboxOne"  size="20" />
											<span class="asterisk"></span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Street:</td>
											<td class="tableRight">
											<input type="text" name="street" class="textboxOne"  size="20" />
										  <span class="asterisk">*</span></td>
										  </tr>
										  <tr class="formLayout">
											<td class="tableLeft">Country:</td>
											<td class="tableRight">
											<select name="country" id="country_sel" class="selectboxOne" onchange="FillState(document.contactForm.country_sel,document.contactForm.state_sel,'');">
												<option value="" selected="selected"></option>
											</select>
											<span class="asterisk">*</span> </td>
										  </tr>
										  <tr class="formLayout">
											<td class="tableLeft">State:</td>
											<td class="tableRight">
											<select name="state" id="state_sel" class="selectboxOne">
												<option value="" selected="selected"></option>
											</select>
												<span class="asterisk">*</span> </td>
										  </tr>
										  
										  <tr>
											<td class="tableLeft">City:</td>
											<td class="tableRight"> 
											<input name="city" type="text" class="textboxOne" size="20" maxlength="35" />
											  <span class="asterisk">*</span></td>
										  </tr>
										  <tr>
											<td class="tableLeft">Zipcode:</td>
											<td class="tableRight">
											<input type="text" name="zipcode" class="textboxOne" size="20" />
										  <span class="asterisk">*</span></td>
										  </tr>
										</table>
									</td>
								  </tr>
								  
								  <tr>
									<td height="25" class="alignCenter">
									  <input type="submit" name="addContact2" value="Add Contact" class="gradBtn" />
									<input type="button" name="cancel" value="Cancel" class="gradBtn" onClick="javascript:hsitory.back.(-1)"/>					</td>
								  </tr>
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
								  </tr>
						</table>
					
					</td>
				  </tr>
				</table>
				</form>

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
<script language="javascript">
	FillCountry(document.getElementById('country_sel'), document.getElementById('state_sel'), '' );
	FillState(document.getElementById('country_sel'), document.getElementById('state_sel'), '');
</script>
<!--
<script language="JavaScript" type="text/javascript">
var frmvalidator = new Validator("contactForm");
frmvalidator.addValidation("firstname","req","First Name cannot be empty"); 
frmvalidator.addValidation("nickname","req","Nick Name cannot be empty"); 
frmvalidator.addValidation("emailid","maxlen=50");
frmvalidator.addValidation("emailid","req");
frmvalidator.addValidation("emailid","email");
/*
frmvalidator.addValidation("alteremailid","maxlen=50");
frmvalidator.addValidation("alteremailid","req");
frmvalidator.addValidation("alteremailid","email");
frmvalidator.addValidation("phone","req");
frmvalidator.addValidation("phone","maxlen=30");
frmvalidator.addValidation("phone","numeric");
frmvalidator.addValidation("mobile","maxlen=30");
frmvalidator.addValidation("mobile","numeric");
frmvalidator.addValidation("mobile","req");
frmvalidator.addValidation("fax","maxlen=35");
frmvalidator.addValidation("fax","alphanumeric");
frmvalidator.addValidation("fax","req");
frmvalidator.addValidation("street","req","Street cannot be empty"); 
frmvalidator.addValidation("city","req","City cannot be empty"); 
frmvalidator.addValidation("zipcode","req");
frmvalidator.addValidation("zipcode","maxlen=10","Enter Valid ZipCode");
frmvalidator.addValidation("zipcode","numeric");
*/
</script>-->
