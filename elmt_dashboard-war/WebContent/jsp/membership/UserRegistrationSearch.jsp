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
<script src="javascripts/SearchuserRegistration.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
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
		<%//@ include file = "../../include/infobar.jsp" %>
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
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
<div class="cmmnForm">
	<div class="colspan">
		Membership:<span class="styleBoldTwo"> User Registration Form </span>	</div>
    <div id="commonBG" class="textCommon" style="height:100px;"><strong>User, please take your time and register to avail the privileges that accompanies with it. <br />
	    <br />
	    Note:<span class="textCommon" style="height:100px;">This registration process does not make you a member.</span><br />
	    You may   become an Member by filling up the Membership Application form towards the end of this registration process.</strong>	</div>

    <form name="frmMembRegi" id="frmMembRegi" method="post" action="SearchSignUp.do" class="formcss" onSubmit="return myvalidate(this);">
 <input type="hidden" value="register" name="process" />
            <div id="parta" >
                <!-- **************************************** Part A of the form starts here *********************************************** -->
 							<div class="rowHead">
								Basic Information:							</div>
									<div class="row">
										<span class="label">Salutation:</span>
									  <span class="formX">
										<select name="USelect" id="select" class="selectboxOne">
										  <option value="" selected="selected">Select</option>
										  <option value="Mr">Mr.</option>
										  <option value="Mrs">Mrs.</option>
										  <option value="Miss">Miss.</option>
										  <option value="Ms">Ms.</option>
										</select>
										<span class="asterisk">*</span>									  </span>			      </div>
									<div class="row">
										<span class="label">First Name:</span> 
										<span class="formX"><input type="text" name="fname" class="textboxOne" size="20"/><span class="asterisk">*</span>									  </span>			      </div>
									<div class="row">
										<span class="label">Middle Name:</span>
										<span class="formX"><input type="text" name="mname"class="textboxOne" size="20" /></span>									</div>
									<div class="row">
										<span class="label">Last Name:</span>									 
										 <span class="formX"><input type="text" name="lname" class="textboxOne" /><span class="asterisk">*</span>									  </span>			      </div>
									<div class="row">
				  					<div class="row">
										<span class="label"> Date of Birth:</span>
										<span class="formX">
											<select name="birthmonth" id="select" class="selectboxOne">
												  <option value="" selected="selected">Month</option>
												  <option value="01">January</option>
												  <option value="02">February</option>
												  <option value="03">March</option>
												  <option value="04">April</option>
												  <option value="05">May</option>
												  <option value="06">June</option>
												  <option value="07">July</option>
												  <option value="08">August</option>
												  <option value="09">September</option>
												  <option value="10">October</option>
												  <option value="11">November</option>
												  <option value="12">December</option>
										  </select>
										  <select name="birthday" id="select" class="selectboxOne">
												  <option value="" selected="selected">Day</option>
												  <option value="01">01</option>
												  <option value="02">02</option>
												  <option value="03">03</option>
												  <option value="04">04</option>
												  <option value="05">05</option>
												  <option value="06">06</option>
												  <option value="07">07</option>
												  <option value="08">08</option>
												  <option value="09">09</option>
												  <option value="10">10</option>
												  <option value="11">11</option>
												  <option value="12">12</option>
												  <option value="13">13</option>
												  <option value="14">14</option>
												  <option value="15">15</option>
												  <option value="16">16</option>
												  <option value="17">17</option>
												  <option value="18">18</option>
												  <option value="19">19</option>
												  <option value="20">20</option>
												  <option value="21">21</option>
												  <option value="22">22</option>
												  <option value="23">23</option>
												  <option value="24">24</option>
												  <option value="25">25</option>
												  <option value="26">26</option>
												  <option value="27">27</option>
												  <option value="28">28</option>
												  <option value="29">29</option>
												  <option value="30">30</option>
												  <option value="31">31</option>
											</select>
											<select name="birthyear" id="select" class="selectboxOne">
												  <option value="" selected="selected" >Year</option>
                                                                                                  
										  <%
										  java.util.Calendar c5 = java.util.Calendar.getInstance();
										  //int day = c.get(Calendar.DAY);
										  //int month = c.get(Calendar.MONTH);
										  int year5 = c5.get(java.util.Calendar.YEAR);
										  //String date = day+" / "+month+" / "+year;
										  //System.out.println("Current Date = "+year5);

										  for(int f=1900;f<=year5;f++) 
										  {%>
										  <option  value="<%=f%>"><%=f%></option>
										  <%}
										  %>
                                          </select><span class="asterisk">*</span>										  </span>								</div>
									<div class="row">
										<span class="label">Gender:</span>
									  <span class="floatLeft">
										<input type="radio" size="10" name="gender" value="male" /> Male
										<input type="radio" size="10" name="gender" value="female" /> Female										</span>
									    <span class="asterisk">*</span></div>
									<div class="row">
										<span class="label">EMail :</span>
									  <span class="formX"><input type="text" name="email" class="textboxOne" size="25" />
									     </span></div>
									  <div class="row">
										<span class="label">User Name (Login ID):</span>
										<span class="formX"><input type="text" name="usrname" class="textboxOne" size="25" onblur="usrStat();"/>
									     </span></div>
									  <div class="row">
										<span class="label">User Code :</span>
										<span class="formX"><input type="text" id="userCode" name="userCode" class="textboxOne" size="25" onblur="checkUserCode(this);"/>
									     </span></div>											 								 
							<div id="pAdd">
									<div class="colspan">
										<strong>Primary Address </strong>									</div>
											<div class="row">
											  <span class="label">Address 1:</span>
											  <span class="formX">
											   <input type="text" name="padd_txt" id="padd1_txt" value="" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span>											</div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" name="padd_txt2" id="padd2_txt" value="" class="textboxOne" size="35" /></span>											</div>
												<div class="row">
												<span class="label">City:</span>
											  <span class="formX">
												<input type="text" id="pcity_txt" name="pcity_txt" value="" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
												<div class="row">
												<span class="label">State:</span>
											  <span class="formX">
												<select name="pstate_sel" id="pstate_sel" class="selectboxOne">
												  <option selected="selected">Select One</option>
												  <option value="FL">FL</option>
												  <option value="VA">VA</option>
												</select>
											 
											    <span class="asterisk">*</span> </span></div>
												<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX">
											  <input type="text" name="pzip_txt" id="pzip_txt" size="8" class="textboxOne" />
											  <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX">
												<select name="pcountry_sel" id="pcountry_sel" class="selectboxOne" onChange="FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '');">
												  <option selected="selected">Select One</option>
												  <option value="United States of America">United States of America</option>
												</select>
											 
											    <span class="asterisk">*</span> </span></div>
											
											
											
											<div class="row">
												<span class="label">Phone:</span>
											  <span class="formX"><input type="text" name="pphone_txt"  id="pZip_txt" class="textboxOne" size="15" />
												(e.g. xxx yyy zzzz)<span class="asterisk"> *</span></span></div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" name="pmob_txt"  id="pZip_txt" class="textboxOne" size="15" /></span>											</div>
											<div class="row">
												<span class="label">Fax:</span>
												<span class="formX"><input type="text" name="pfax_txt"  id="pZip_txt" class="textboxOne" size="15" /></span>											</div>
							</div>
							<div class="row">
								<span class="label">Do you have secondary address ?</span>
								<span class="formX">
									<div class="floatLeft">
<input type="radio" value="Primary" name="addr" size="10" onclick="switchDiv('sAdd');" />Yes
<input type="radio" value="Secondary" name="addr" size="10" checked="checked" onClick="showHideRadio('addr','sAdd','Secondary');" /> No									</div>
								</span>							</div>

							<div id="sAdd">

									<div class="colspan">
										<strong>Secondary Address </strong>									</div>
									
											<div class="row">
												<span class="label">Address 1:</span>
											  <span class="formX"> 
											  <input type="text" value="" name="sadd_txt" id="sAdd1_txt" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" name="sadd_txt1" id="sadd2_txt" class="textboxOne" size="35" /></span>											</div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX"><select name="sCountry_txt" id="sCountry_sel" class="selectboxOne" onChange="FillState(document.frmMembRegi.sCountry_txt, document.frmMembRegi.sState_txt,'');" >
                                                    </select><!--<input type="text" value="" name="sCountry_txt" id="sCountry_txt" class="textboxOne" size="30" />-->
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">State:</span>
											  <span class="formX"><select name="sState_txt" id="sState_sel" class="selectboxOne">
												    </select><!--<input type="text" value="" name="sstate_txt" id="sState_txt" class="textboxOne" size="25" />-->
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">City:</span>
											  <span class="formX"><input type="text" name="scity_txt"  id="scity_txt" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX"><input type="text" name="szip_txt"  id="szip_txt" class="textboxOne" size="8" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Phone:</span>
											  <span class="formX"><input type="text" name="sphone_txt"  id="sZip_txt" class="textboxOne" size="15" />
												(e.g. xxx yyy zzzz)<span class="asterisk"> *</span></span></div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" name="smob_txt"  id="sZip_txt" class="textboxOne" size="15" /></span>											</div>
											<div class="row">
												<span class="label">Fax:</span>
												<span class="formX"><input type="text" name="sfax_txt"  id="sZip_txt" class="textboxOne" size="15" /></span>											</div>
											<div class="row">
												<span class="label">preferred Communication Address:</span>
												<span class="formX">
												<select name="myselect" id="comAdd_sel" class="selectboxOne">
												  <option selected="selected" value="Primary">Primary Address</option>
												  <option value="Secondary">Secondary Address</option>
												</select>
												</span>											</div>
							</div>
<!--							<div class="colspan">
								<strong>Contact Options</strong>
							</div>
									<div id="commonBG" class="textCommon" style="height:40px;">
										The HLC does provide mail/email information to other /equestrian/ affliates, organizations, sponsors 
										and vendors on a limited basis. You may choose to not receive these mailings by selecting the 
										appropriate options below:
									</div>
									<div id="commonBG" class="textCommon" style="height:40px;">
										<input type="checkbox" name="nonUseaEmail"  id="nonUseaEmail_id" value="true"/> Do not release my email address for non-HLC specific use. <br />
										<input type="checkbox" name="nonUseaMail"  id="nonUseaAdd_id" value="true"/> Do not release my mailing address for non-HLC specific use.
									</div>
-->
									
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>
									<!-- spacer ends-->


								<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="Register Now" class="gradBtn" /></span>								</div>

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

			<!-- CONTENTS END HERE -->			</td>
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
	FillCountry(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, 'USA' );
	FillState(document.frmMembRegi.pcountry_sel, document.frmMembRegi.pstate_sel, '');

	FillCountry(document.frmMembRegi.sCountry_txt, document.frmMembRegi.sState_txt, 'USA' );
	FillState(document.frmMembRegi.sCountry_txt, document.frmMembRegi.sState_txt, '');

</script>
