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
<script src="javascripts/frmGrpUserCheck.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>


<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<body onload="switchDiv('accUser'), showHideRadio('regFor','nonAcc','acc1'), clearUserdetails(); clearNewUserdetails();">
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
					<td colspan="2" class="tblMainHead"><strong>Join Group</td>
				  </tr>
				 
						  <tr>
							<td>
<%
	String ownerId = (String)request.getAttribute("oId");
	String groupId = (String)request.getAttribute("gId");
	
	System.out.println("In JSP: "+ownerId);
	System.out.println("In JSP: "+groupId);

%>							
		  				<form name="myform" id="myform" method="post" action="joingroup.do" onsubmit="return myValidate();">		       
							<input type="hidden" name="process" value="join" />
							<input type="hidden" name="oId" value="<%=ownerId%>" />
							<input type="hidden" name="gId" value="<%=groupId%>" />
							
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								 
								  									
								<tr>
					<td class="tableLeftTxtArea">Are You an Existing user </td>
					<td class="tableRight">
					<input type="radio" size="10" name="regFor" id="regFor" checked="checked" value="acc1" onClick="switchDiv('accUser'), showHideRadio('regFor','nonAcc','acc1'), clearUserdetails(); clearNewUserdetails();" />
					Yes. <br />
					
				<input type="radio" size="10" name="regFor" id="regFor" value="acc2" onClick="switchDiv('nonAcc'), showHideRadio('regFor','accUser','acc2'), clearUserdetails(); clearNewUserdetails();"/>
				No. <br />	
					
				</td>
					</tr>
					
			<tr>
			    
				
					<td colspan="2"><div id="accUser">
					   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					   <input type="hidden" name="loginCheck" value="yes">
							<tr> 
							  <td colspan="2" class="tblMainHead">Login Detail</td>
							</tr>
							
							<tr>
							  <td class="tableLeft">Login Name:</td>
							  <th class="tableRight"><input name="loginName" id="loginName" class="textboxOne" size="20" onblur="loginStat(this);" /> <span class="asterisk">*</span> </th>
							</tr>	
							
						<!--	<tr>
							  <td class="tableLeft">Password:</td>
							  <th class="tableRight"><input name="txtpassword" id="txtpassword" class="Password" size="20" /> <span class="asterisk">*</span> </th>
							</tr>		-->
					
					 </table>
						 </div>								</td>
				  </tr>
					
					  <tr>
					<td colspan="2">
					<div id="nonAcc">
						  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<div class="tblMainHead">
							  <span class="label">New User Signup</span>
							</div>
					 <input type="hidden" name="loginCheck" value="no">
						<div class="row">
						
										<span class="label">Salutation:</span>
									  <span class="formX">
										<select name="USelect" id="USelect" class="selectboxOne">
                                    <option value="" selected="selected">Select One</option>
										  <option value="Mr">Mr.</option>
										  <option value="Mrs">Mrs.</option>
										  <option value="Miss">Miss.</option>
										  <option value="Ms">Ms.</option>
										</select>&nbsp;<span class="asterisk">*</span>
								</span>			      </div>
									<div class="row">
										<span class="label">First Name:</span>                                      <span class="formX"><input type="text" name="fname" class="textboxOne" size="20" />&nbsp;<span class="asterisk">*</span></span>			      </div>
									<div class="row">
										<span class="label">Middle Name:</span>
										<span class="formX"><input type="text" name="mname"class="textboxOne" size="20" /></span>									</div>
									<div class="row">
										<span class="label">Last Name:</span>									  <span class="formX"><input type="text" name="lname" class="textboxOne" size="20" />&nbsp;<span class="asterisk">*</span></span>			      </div>
									<div class="row">
										<span class="label">Suffix:</span>
										<span class="formX"><input type="text" name="sname" class="textboxOne" size="20" /></span>									</div>
										
									<div class="row">
										<span class="label">Date of Birth :</span>	<span class="formX">
										<select name="birthmonth" id="birthmonth" class="selectboxOne">
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
												<select name="birthday" id="birthday" class="selectboxOne">
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
												<select name="birthyear" id="birthyear" class="selectboxOne">
												  <option value="" selected="selected" >Year</option>
                                                                                                  
                                                                                                  <%
                                                                                                  java.util.Calendar c5 = java.util.Calendar.getInstance();
                                                                                                  //int day = c.get(Calendar.DAY);
                                                                                                  //int month = c.get(Calendar.MONTH);
                                                                                                  int year5 = c5.get(java.util.Calendar.YEAR);
                                                                                                  //String date = day+" / "+month+" / "+year;
                                                                                                  System.out.println("Current Date = "+year5);

                                                                                                  for(int f=1900;f<=year5;f++) 
                                                                                                  {%>
                                                                                                  <option  value="<%=f%>"><%=f%></option>
                                                                                                  <%}
                                                                                                  %>
                                                                                                  
                                        </select>&nbsp;<span class="asterisk">*</span>(e.g. MM/DD/YYYY)
									  </span>			      </div>
									<div class="row">
										<span class="label">Gender:</span>
									  <span class="floatLeft">
										<input type="radio" size="10" name="gender" value="male" /> Male
										<input type="radio" size="10" name="gender" value="female" /> Female										</span>
									    <span class="asterisk">*</span></div>
									
									  <div class="row">
										<span class="label">User Name (Login ID):</span>
										<span class="formX"><input type="text" name="usrname" class="textboxOne" size="20" onblur="usrStat();" />&nbsp;<span class="asterisk">*</span></span></div>

									<div class="row">
										<span class="label">EMail :</span>
									  <span class="formX"><input type="text" name="email" class="textboxOne" size="20" />
									     <span class="asterisk">*</span></span></div>
									
									<div class="row">
										<span class="label">Choose Your Password:</span>
									  <span class="formX"><input type="Password" name="pwd" class="textboxOne" size="10" />
									  &nbsp; (Minimum 6 Characters) <span class="asterisk">*</span></span></div>
									<div class="row">
										<span class="label">Re-Type Password:</span>
									  <span class="formX"><input type="Password"name="cpwd" class="textboxOne" size="10" />
									  &nbsp; <span class="asterisk">*</span></span></div>
									<div class="row">
										<span class="label">Choose A Secret Question:</span>
									  <span class="formX">
										<select name="QSelect" id="QSelect" class="selectboxOne">
										  <option selected="selected">Select One</option>
										  <option value="What is your favorite passtime?">What is your favorite passtime?</option>
										  <option value="What is your pet's name?">What is your pet's name?</option>
										  <option value="What was your first car?">What was your first car?</option>
										  <option value="What is your mother's first name?">What is your mother's first name?</option>
										  <option value="Which is your favorite vacation spot?">Which is your favorite vacation spot?</option>
										</select>
									  &nbsp; <span class="asterisk">*</span></span></div>
									<div class="row">
										<span class="label">Your Answer To This Question:</span>
									  <span class="formX"><input type="text"name="ans" class="textboxOne" size="30" />
									  <span class="asterisk">*</span></span>									</div>
									

							<div id="pAdd">

									<div class="colspan">
										<strong>Primary Address </strong>									</div>
											<div class="row">
											  <span class="label">Address 1:</span>
											  <span class="formX">
											   <input type="text" name="pAdd1_txt" id="pAdd1_txt" value="" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span>											</div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" name="pAdd2_txt" id="pAdd2_txt" value="" class="textboxOne" size="35" /></span>											</div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX">
												<select name="pCountry_sel" id="pCountry_sel" class="selectboxOne" onChange="FillState(document.myform.pCountry_sel, document.myform.pState_sel, '');">
												  <option selected="selected">Select One</option>
												  <option value="United States of America">United States of America</option>
												</select>
											 
											    <span class="asterisk">*</span> </span></div>
											<div class="row">
												<span class="label">State:</span>
											  <span class="formX">
												<select name="pState_sel" id="pState_sel" class="selectboxOne">
												  <option selected="selected">Select One</option>
												  <option value="FL">FL</option>
												  <option value="VA">VA</option>
												</select>
											 
											    <span class="asterisk">*</span> </span></div>
											<div class="row">
												<span class="label">City:</span>
											  <span class="formX">
												<input type="text" id="pCity_txt" name="pcity_txt" value="" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX">
											  <input type="text" name="pzip_txt" id="pzip_txt" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Phone:</span>
											  <span class="formX"><input type="text" name="pphone_txt"  id="pphone_txt" class="textboxOne" size="15" />
												(e.g. xxx yyy zzzz)<span class="asterisk"> *</span></span></div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" name="pmob_txt"  id="pmob_txt" class="textboxOne" size="15" /></span>											</div>
											<div class="row">
												<span class="label">Fax:</span>
												<span class="formX"><input type="text" name="pfax_txt"  id="pfax_txt" class="textboxOne" size="15" /></span>											</div>
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
											  <input type="text" value="" name="sadd_txt" id="sadd_txt" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" value="" name="sadd_txt1" id="sadd_txt1" class="textboxOne" size="35" /></span>											</div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX"><select name="sCountry_sel" id="sCountry_sel" class="selectboxOne" onChange="FillState(document.myform.sCountry_sel, document.myform.sstate_txt,'');" >
                                                    </select><!--<input type="text" value="" name="scountry_txt" id="sCountry_txt" class="textboxOne" size="30" />-->
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">State:</span>
											  <span class="formX"><select name="sstate_txt" id="sstate_txt" class="selectboxOne">
												    </select><!--<input type="text" value="" name="sstate_txt" id="sState_txt" class="textboxOne" size="25" />-->
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">City:</span>
											  <span class="formX"><input type="text" value="" name="scity_txt"  id="sCity_txt" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX"><input type="text" value="" name="szip_txt"  id="sZip_txt" class="textboxOne" size="8" />
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
												<select name="myselect" id="myselect" class="selectboxOne">
												  <option selected="selected" value="Primary">Primary Address</option>
												  <option value="Secondary">Secondary Address</option>
												</select>
												</span>											</div>
						
							
						 </table>
						 </div>
						 
					    </td>
				  </tr>						  
								<tr>
									<td colspan="2" class="alignCenter"><input type="submit" value="Submit" class="gradBtn"  /></td>
							   </tr>				 
								<tr>
									<td colspan="2" height="25" class="alignCenter">&nbsp;</td>
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

<script language="javascript">
	FillCountry(document.myform.pCountry_sel, document.myform.pState_sel, 'USA' );
	FillState(document.myform.pCountry_sel, document.myform.pState_sel, '');

	FillCountry(document.myform.sCountry_sel, document.myform.sstate_txt, '');
	FillState(document.myform.sCountry_sel, document.myform.sstate_txt, '');

</script>
