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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script></head>

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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<div class="cmmnForm">
	<div class="colspan">
		<strong>Membership Refund Application Form</strong> 
	</div>
    <div id="commonBG" class="textCommon" style="height:100px;">
	    <img src="images/usea_logo.jpg" class="imgFloatLeft" /><strong>User, please take your time and register to avail the privileges that accompanies with it. <br />
	    <br />
	    Note:<span class="textCommon" style="height:100px;">This registration process does not make you a member .</span><br />
	    You may   become an Member by filling up the Membership Application form towards the end of this registration process.<br />
	    </strong><br />
    </div>

    <form name="frmMembRegi" id="myform" class="formcss" action="">

            <div id="parta" >	
                <!-- **************************************** Part A of the form starts here *********************************************** -->
						
						
							<div class="rowHead">
								Membership Refund Request: 
						    </div>
									
									
									
									<div class="row">
										<span class="label">Claim Amount:</span>
										<span class="formX"><input name="lname" type="text" class="textboxOne" size="20" /></span>
									</div>
									
									<!-- spacer starts-->
<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
							

								<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="Submit" class="gradBtn" /></span>
								</div>
							
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
							
						<!-- **************************************** Part A of the form Ends here *********************************************** -->	
            </div>

	</form>
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
