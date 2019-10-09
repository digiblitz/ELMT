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
<%@ page import="com.hlcform.util.*" %>
<%@ page import="com.hlcmeeting.util.Debug" %>
<%@ page import="com.vo.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmUserReg.js" type="text/javascript" ></script>
<script src="javascripts/frmUserSignup.js" type="text/javascript" ></script>

<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "../../include/login_header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%//@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> User Registration: <span class="styleBoldTwo">Account Matching Result</span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp">&nbsp;
							
							</td>
					  </tr>
							  <tr>
								<td>
								
								<form name="frmUserSignup" method="post" id="myform" action="UsrSignupDetails.do" onsubmit="return validateUser();">		  
								<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
									  <tr>
										<td colspan="2" class="tblRowHead">&nbsp;Matching Results:</td>
									  </tr>
									  <input type="hidden" name="process" value="secQaVal" />
									  <tr>
										<td colspan="2" height="40" class="tableSpan"> <strong>Similar account(s) already exists. They are as such:</strong> <br /><br />
										Select the result that matches your profile to proceed with the sign up process.
										</td>
									  </tr>

									  <%
									  													HLCUsrSignUpVO usrVal=new HLCUsrSignUpVO();
                                                                                        usrVal=(HLCUsrSignUpVO)request.getAttribute("usrVal");
                                                                                        request.setAttribute("usrVal",usrVal);
                                                                                         System.out.println("usrVal in jsp :"+usrVal.toString());
											HLCUserMaster objUserMaster = new HLCUserMaster();
											int i=0;

											ArrayList usrDet=new ArrayList();
											usrDet=(java.util.ArrayList)request.getAttribute("usrDet");

											 if(usrDet!=null)
											{

											for(i=0;i<usrDet.size();i++)
											{
												objUserMaster=(com.hlcform.util.HLCUserMaster)usrDet.get(i);
										%>
											<tr>

											<td colspan="2" height="25" class="alignCenter">
											
											<table border="0" cellpadding="0" align="center" cellspacing="0" >
												<tr>
												<td class="tblMainHead" colspan="2">
												<input type="radio" name="info_rad" value="<%=objUserMaster.getUserId()%>" onclick="chstat(); showHideRadio('info_rad','existMemb','<%=objUserMaster.getUserId()%>'); showHideRadio('info_rad','existMembId','<%=objUserMaster.getUserId()%>'); showHideRadio('info_rad','secretQuest','<%=objUserMaster.getUserId()%>');"/>												
												<span class="styleBoldOne">Information <%=i+1%></span></td>
											  </tr>	
											  
											  <tr class="tableInner">
												<td class="tableLeft">First Name:</td>
												<td class="tableRight"><input name="firstName" id="firstNameId" class="textboxOne" value="<%=objUserMaster.getFirstName()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />
												 </td>
											  </tr>
											 
											 <tr class="tableInner">
												<td class="tableLeft">Last Name:</td>
												<td class="tableRight"><input name="lastName" id="lastNameId" class="textboxOne" value="<%=objUserMaster.getLastName()%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="20" readonly="readonly" />
												 </td>
											  </tr>

												<%

												String digno="";
												if(objUserMaster.getPhoneNo()!=null)
												{
				
													String ph=objUserMaster.getPhoneNo();
													int phlen=ph.length();

													if(phlen>4)
													{
														int rstsiz=phlen-4;
														digno=ph.substring(rstsiz,phlen);
													}
													else
													{
														digno=objUserMaster.getPhoneNo();
													}													
												}

												%>

											  <tr class="tableInner">
												<td class="tableLeft">Phone Number (Last 4 digits):</td>
												<th class="tableRight">xxx-xxx-<input name="phoneNumber" id="phoneNumberId" class="textboxOne" value="<%=digno%>" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="8" readonly="readonly" />
												  </th>
											</table> 
											
										</td>
									</tr>
												
											<%}
										 }
									  %>

									<tr>
										<td colspan="2" class="tableSpan">&nbsp;</td>
								   </tr> 
									<tr>
										<td class="tableLeft">Are you sure the selection matches your profile? </td>
										<td class="tableRight">
										<input type="radio" name="prof_rad" value="yes" onclick="switchDiv('existMemb'); showHideRadio('prof_rad','existMembId','yes');clrRad();" />
										Yes, it matches my profile.<br />
										<input type="radio" name="prof_rad" value="no" checked="checked" onclick="showHideRadio('prof_rad','existMemb','no'); showHideRadio('prof_rad','existMembId','no'); showHideRadio('prof_rad','secretQuest','no');" />
										No, it doesn't match my profile.
										</td>
								  </tr>
								  <tr>
										<td colspan="2" class="alignCenter" id="existMemb">
										   
										   <table width="100%" border="0" cellpadding="0" align="center" cellspacing="0" >
											  <tr>
												<td class="tblMainHead" colspan="2">Existing Member Confirmation:</td>
											  </tr>
											   <tr>
												<td class="tableLeftTxtArea">Are you an existing member?</td>
												<td class="tableRight">
													<input type="radio" name="memb_rad" value="yes" onclick="switchDiv('existMembId');showHideRadio('memb_rad','secretQuest','yes');selProf();" />
													Yes, I am an existing member.<br />
													<input type="radio" name="memb_rad" value="no" onclick="showHideRadio('memb_rad','existMembId','no'); switchDiv('secretQuest');secQa();" />
													No, I am not an existing member.
												</td>
											  </tr>
											</table> 
											
										</td>
								   </tr>
								   <tr>
									<tr id="existMembId">
										<td class="tableLeft">Existing Member ID:</td>
										<td class="tableRight"><input name="existMembId" id="existMemb_Id" class="textboxOne" size="20" onblur="HLCMemberDetails();"/></td>
									</tr>
									<tr>
										<td colspan="2" class="alignCenter" id="secretQuest">
										   
										   <table width="100%" border="0" cellpadding="0" align="center" cellspacing="0" >
											  <tr>
												<td class="tblMainHead" colspan="2">Your Secret Question:</td>
											  </tr>	
											  
											  <tr>
												<td class="tableSpan" colspan="2"><input name="secques" id="secques" class="textboxOne" style="background-color:#F4F4F4; font-weight:bold; border:0px;" size="40" readonly="readonly" /></td>
											  </tr>
											   <tr>
												<td class="tableLeft">Your Answer:</td>
												<td class="tableRight"><input name="answer" id="answerId" class="textboxOne" size="40" /></td>
											  </tr>
											</table> 
											
										</td>
								   </tr>
									<tr>
										<td colspan="2" class="alignCenter"><input type="Submit" value="Continue" class="gradBtn" /></td>
								   </tr>
								</table>
							</form>
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
