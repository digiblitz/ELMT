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
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%@ include file = "include/header.jsp" %>
		<!-- HEADER ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="infoBar">
		<!-- INFO BAR STARTS HERE -->
		<%@ include file = "include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldTwo">Application for ICP Assessment</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"> <img src="images/usea_logo.jpg" class="imgFloatLeft" /> 
					</td>
				  </tr>
				  <tr>
					<td>
	
					
					<form name="frmMeeICPAssessment" id="myform" action="">
					
							
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
							<td colspan="2">
								<!-- TABS START HERE -->										
								<table cellpadding="0" cellspacing="0" border="0" class="container">
									<tr>
										<td id="tabData1" class="tabHighlight" onclick="naviTab('1')">
											<a id="link1" href="javascript:void(0);" class="active"><span class="tabHead">Part A</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData2" class="tabLowlight" onclick="naviTab('2')">
											<a id="link2" href="javascript:void(0);" class="inactive"><span class="tabHead">Part B</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData3" class="tabLowlight" onclick="naviTab('3')">
											<a id="link3" href="javascript:void(0);" class="inactive"><span class="tabHead">Part C</span></A>
										</td>
										<td width="1" style="border-bottom:1px solid #999;">&nbsp;</td>
										<td id="tabData4" class="tabLowlight" onclick="naviTab('4')">
											<a id="link4" href="javascript:void(0);" class="inactive"><span class="tabHead">Part D</span></A>
										</td>
									</tr>	
								</table>
								<!-- TABS END HERE -->
							</td>
						</tr>
						 
						 <tr id="part1" class="holderDivOne" >
						 	<td colspan="2">
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
								 <tr> 
									<td colspan="2" class="tblRowHead">ICP Assessment Details:</td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Assesmant Level:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Area:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Location:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Host IP:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Host Number:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Address:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Country:</td>
									<td class="tableRight"> <select name="select5" id="select7" class="selectboxOne" onchange="dispVal('planDisp', 'amount_txt');">
										<option selected="selected">Select One</option>
										<option>Four-Color Ads</option>
										<option>Black &amp; White Ads</option>
									  </select> <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td height="24" class="tableLeft">State:</td>
									<td class="tableRight"> <select name="select6" id="select8" class="selectboxOne" onchange="dispVal('planDisp', 'amount_txt');">
										<option selected="selected">Select One</option>
										<option>Four-Color Ads</option>
										<option>Black &amp; White Ads</option>
									  </select> <span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td class="tableLeft">City:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="20" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Zip:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Phone:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Fax:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">EMail:</td>
									<td class="tableRight"><input type="text" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Asses Detail:</td>
									<td class="tableRight"><span class="asterisk"> 
									  <textarea name="textfield" class="textAreaOne"></textarea>
									  *</span></td>
								  </tr>
							 </table>
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->
							</td>
						 </tr>
						 
						 <tr id="part2" class="holderDivTwo">
						 	<td colspan="2">
							
							<!--++++++++++++++++++++ Part 2 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table cellpadding="0" cellspacing="0" border="0" class="formLayout">
							
								 <tr>
									<td class="tableLeft"> Type Of Activity : </td>
									<td class="tableRight"><span class="row"><span class="formX">
									  <select name="select7" id="select2" class="selectboxOne">
										<option selected="selected">Select One</option>
										<option value="FL">FL</option>
										<option value="VA">VA</option>
									  </select>
									</span></span></td>
								  </tr>
								  <tr>
									<td class="tableLeft">If other, Specify:  </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="textfield374" /></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Fee To Be Charged:  </td>
									<td class="tableRight"><strong>$</strong> <input name="textfield372" type="text" class="textboxOne" size="18" /></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Instructor(s)/Coach(es):  </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="textfield374" /></td>
								  </tr>
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>
									<td class="tableRight">
									<input type="checkbox" name="checkbox2" value="checkbox" />	Cross-Country Schooling
									<input type="checkbox" name="checkbox3" value="checkbox" />	Riding Clinic
									<input type="checkbox" name="checkbox4" value="checkbox" />	Stablity
									<input type="checkbox" name="checkbox5" value="checkbox" />	Other			</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> If Other Specify:</td>
									<td class="tableRight"><input type="text" class="textboxOne" name="textfield374" /></td>
								  </tr>
								  <tr>
									<td colspan="2" class="tblRowHead"> <span class="rowHead">Publicity And Mailing List: </span></td>
								  </tr>
								  <tr class="tblInnerContainer">
									<td colspan="2" class="tblDescrp">
									<div id="commonBG" class="textCommon" > <strong>User, please take your time and register to avail the privileges that accompanies with it. <br />
							   
							  Note:<span class="textCommon" style="height:100px;">This registration process does not make you a member.</span><br />
							  You may become an Member by filling up the Membership Application form towards the end of this registration process.<br />
									</strong>            </div></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Email logo (JPEG): </td>
									<td class="tableRight"><input type="text" class="textboxOne" name="textfield33" /> (For Publicity Use Only)</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> Complimentary Mailing List Format: </td>
									<td class="tableRight"><span class="row"><span class="formX">
									<input name="radiobutton" type="radio" value="radiobutton" />Electronic .csv file
									<input name="radiobutton" type="radio" value="radiobutton" />Hard Copy </span></span>
									</td>
								  </tr>
								  <tr>
									<td height="27" class="tableLeft"><span class="row"><span class="label">Send Mailing List by :</span></span></td>
									<td class="tableRight"><span class="row"><span class="formX">
									<input name="radiobutton" type="radio" value="radiobutton" />Areas
									<input name="radiobutton" type="radio" value="radiobutton" />States </span></span>
									</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> <span class="row"><span class="label">Sort Mailing List by :</span></span></td>
									<td class="tableRight"><span class="row"><span class="formX">
									<input name="radiobutton" type="radio" value="radiobutton" />Zip
									<input name="radiobutton" type="radio" value="radiobutton" />Alpha by last name </span></span>
									</td>
								  </tr>
								  <tr>
									<td class="tableLeft"> <span class="row"><span class="label">Number of copies of the colour brochure :</span></span></td>
									<td class="tableRight"><input type="text" class="textboxOne" name="textfield37" /></td>
								  </tr>
							</table>
							<!--++++++++++++++++++++ Part 2 of the form ends here ++++++++++++++++++++++++++++++ -->		
							
							</td>
						 </tr>
						  
					  	 <tr id="part3" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 3 of the form starts here ++++++++++++++++++++++++++++++ -->	
						<table cellpadding="0" cellspacing="0" border="0" class="formLayout">

						  <tr>
							<td colspan="2" class="tblRowHead"> <span class="rowHead">Land Owner Details : </span></td>
						  </tr>
						  <tr>
							<td class="tableLeft">Land Owner Name :</td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield34" /></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield35" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield36" /></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Country: </td>
							<td class="tableRight">
							 <select name="select2" id="select" class="selectboxOne">
								<option selected="selected">Select One</option>
								<option>Country one</option>
								<option>Country Two</option>
								<option>Country Three</option>
								<option>Country Four</option>
							  </select>			</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight">
							<select name="select2" id="select" class="selectboxOne">
								<option selected="selected">Select One</option>
								<option>State one</option>
								<option>State Two</option>
								<option>State Three</option>
								<option>State Four</option>
							  </select>			</td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield37" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Tel: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield36" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft style1">Fax: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield37"></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Telephone: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield37"></td>
						  </tr>
						  <tr>
							<td class="tableLeft style1">Fax: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="textfield37"></td>
						  </tr>
						  <tr>
							<td colspan="2" height="27" class="tableSpan"><input type="checkbox" name="checkbox" value="checkbox" />
							Check if Additional Sites are to be used.
							</td>
						  </tr> 
						   <tr>
							<td class="tableLeft style1">Upload Additional Site Information: </td>
							<td class="tableRight"><input type="file" name="file" class="textboxOne" />  </td>
						  </tr>
				
						  <tr>
						  
							<td colspan="2" > 
						 
							  <table width="100%" border="0">
							  <tr>
								<td class="tblDescrp">
								  <strong>ACCEPTANCE OF TERMS:</strong><br />
								   I have read the Requirements for the Educational Activity written on the reverse of this
								  Application, and the Release Form. <br />
								  I understand those Requirements and terms and the responsibility I accept as a
								  educational activity organizer. I agree as a condition of registration of this activity to adhere to those conditions
								  and to pay the appropriate fees.
							  	</td>
							  </tr>
								<tr>
								  <td class="alignLeft">       
									  <input type="checkbox" name="checkbox6" value="checkbox" /> I Accept
								  </td>
								</tr>
							  </table>
							 </td>
						</tr>
						</table>
						<!--++++++++++++++++++++ Part 3 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
						</td>
					 </tr>
						 
						 <tr id="part4" class="holderDivTwo">
						<td colspan="2">
						
						<!--++++++++++++++++++++ Part 4 of the form starts here ++++++++++++++++++++++++++++++ -->	
						
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
								  <tr>
									<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
								  </tr>

								  <tr>
									<td class="tableLeft"><strong>Total Amount:</strong></td>
									<td class="tableRight"> <strong>$</strong> <input type="text" class="textboxOne" size="10" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft">
										<input type="radio" size="10" name="amt" value="check" checked="checked" onclick="switchDiv('chkEnc'), showHideRadio('amt','chrgCrd','check');" /> Check enclosed.				 	</td>
									<td class="tableRight">
										<input type="radio" size="10" name="amt" value="card" onclick="switchDiv('chrgCrd'), showHideRadio('amt','chkEnc','card');" /> Please charge my card.					</td>
								  </tr>	

								  <tr id="chkEnc">
									<td colspan="2">
									
										<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
											<tr>
												<td class="tblMainHead" colspan="3">Check Details:</td>
											</tr>
											<tr>
											  <td class="tableLeft">Check No:</td>
											  <td class="tableRight"><input type="text" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
											</tr>
											<tr>
											  <td class="tableLeft">Check Date:</td>
											  <td class="tableRight"><input type="text" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
											</tr>
											<tr>
											  <td class="tableLeft">In Favor Of:</td>
											  <td class="tableRight"><input type="text" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
											</tr>
										</table>
									</td>
								 </tr>

								  <tr id="chrgCrd">
									<td colspan="2">
									
										<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
											<tr>
												<td class="tblMainHead" colspan="2">Card Details:</td>
											</tr>
											
											<tr>
												<td class="tableLeft">Card No.:</td>
											  <td class="tableRight"><input type="text" class="textboxOne" size="16" />
												  <span class="asterisk">*</span></td>
											</tr>
											
											<tr>
												<td class="tableLeft">Card CVV No.:</td>
											  <td class="tableRight"><input type="text" class="textboxOne" size="5" />
												  <span class="asterisk">*</span></td>
											</tr>
											
											<tr>
											  <td class="tableLeft">Card Type:</td>
											  <td class="tableRight">
													<select name="select" id="select" class="selectboxOne">
													  <option selected="selected">Select One</option>
													  <option>Visa</option>
													  <option>Master Card</option>
													  <option>AmEx</option>
													</select>
													<span class="asterisk">*</span>	
											  </td>
											</tr>
											
											<tr>
											  <td class="tableLeft">Print Name On Card:</td>
											  <td class="tableRight"><input type="text" class="textboxOne" size="25" />
												  <span class="asterisk">*</span></td>
											</tr>
											
											<tr>
											  <td class="tableLeft">Expiry Date:</td>
											  <td class="tableRight">
													<select name="expirymonth" id="select" class="selectboxOne">
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
													<select name="expiryyear" id="select" class="selectboxOne">
														  <option value="" selected="selected" >Year</option>
														  <option  value="2006">2006</option>
														  <option  value="2007">2007</option>
														  <option  value="2008">2008</option>
														  <option  value="2009">2009</option>
														  <option  value="2010">2010</option>
														  <option  value="2011">2011</option>
														  <option  value="2012">2012</option>
														  <option  value="2013">2013</option>
														  <option  value="2014">2014</option>
														  <option  value="2015">2015</option>
													</select>
													<span class="asterisk">*</span>	
											 </td>
										  </tr>
									 </table>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="alignCenter"><input type="button" value="Submit" class="gradBtn" /></td>
							   </tr>
							</table>
							<!--++++++++++++++++++++ Part 4 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
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
			<%@ include file = "include/footer.jsp" %>
		<!-- FOOTER ENDS HERE -->
	</td>
  </tr>
</table>


 

</body>
</html>
