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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessmentRegi.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
 <style type="text/css">
<!--
.style1 {font-weight: bold}
-->
 </style>
</head> 
 


<body onload="cardclear();">
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
					<strong>Meetings: </strong> <span class="styleBoldTwo">Application for ICP Assessment</span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">&nbsp;</td>
				  </tr>
				  <tr>
					<td>
	
					
					<form name="frmMeeICPAssessment" id="myform" method="post" action="IcpOrgRegFrm.do" onsubmit="return myvalidate();">
					
							
					<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
							<td colspan="2">
								<!-- TABS START HERE -->										
								<!--<table cellpadding="0" cellspacing="0" border="0" class="container">
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
								</table>-->
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
                                                                      <input type="hidden" name="process" value="insert" />
									<td class="tableLeft">Assessment Level:</td>
									<td class="tableRight"><input type="text" name="assesLevel" id="assesmant" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Date:</td>
									<td class="tableRight"><input name="dat" type="text" readonly="true" class="textboxOne" id="date" size="16" />
								    <span class="asterisk">*</span><a href="javascript:cal1.popup();"> <img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>	</td>
								  </tr>
								   <tr> 
									<td class="tableLeft">No. of Days:</td>
									<td class="tableRight">
									<select name="days" id="select71" class="selectboxOne" onchange="price();">
										<option selected="selected">Nos.</option>
										<option value="1">01</option>
										<option value="2">02</option>
										<option value="3">03</option>
										<option value="4">04</option>
										<option value="5">05</option>
										<option value="6">06</option>
										<option value="7">07</option>
										<option value="8">08</option>
										<option value="9">09</option>
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
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Area:</td>
									<td class="tableRight">
									<select name="hlcArea" id="select72" class="selectboxOne">
										<option selected="selected">---Select One---</option>
										<%
										Vector area=new Vector(); 
                                        area=(Vector)request.getAttribute("area");
                                        System.out.println("size :"+area.size());
                                                        
										if(area!=null && area.size()!=0)
										{
                                              Enumeration en=area.elements();    
                                                            
										while(en.hasMoreElements())
										{
                                               String[] icpVal = (String[])en.nextElement();
                                             
                                       %>
										 <option value="<%=icpVal[0]%>"><%=icpVal[2]%></option>								
									
									 <%}}%>
									</select>
									 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Location:</td>
									<td class="tableRight"><input type="text" name="location" id="location" class="textboxOne" size="25" /> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
								  <input type="hidden" name="fname" id="firstName"/>
								  <input type="hidden" name="phone" id="firstName"/>
								  <input type="hidden" name="lname" id="lastName"/>
								  <input name="addr" type="hidden" id="address1"/>
								  <input type="hidden" name="ctry" id="country"/>
								  <input type="hidden" name="state" id="state"/>
								  <input type="hidden" name="city" id="city"/>
								  <input name="zip" type="hidden"  id="zip"/> 
								  <input name="fax" type="hidden"  id="fax" /> 
								  <input name="email" type="hidden" id="email" /> 
									<!--<td class="tableLeft">Host ID:</td>
									<td class="tableRight"><input name="hostid" id="hostId" type="text" class="textboxOne" size="25"  onblur="details();" />
								
									
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">First Name:</td>
									<td class="tableRight"> 
									
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Last Name:</td>
									<td class="tableRight"> 
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Address:</td>
									<td class="tableRight">
								    <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Country:</td>
									<td class="tableRight">
									<span class="asterisk">*</span> </td>
								  </tr>
								  <tr> 
									<td height="24" class="tableLeft">State:</td>
									<td class="tableRight">
									<span class="asterisk">*</span>									</td>
								  </tr>
								  <tr> 
									<td class="tableLeft">City:</td>
									<td class="tableRight">
									  <span class="asterisk">*</span></td>
								  </tr>
								  <tr> 
									<td class="tableLeft">Zip:</td>
									<td class="tableRight">
								    <span class="asterisk">*</span></td>
								  </tr>-->
								  <tr>
							<td class="tableLeft"> Host Id(Member ID):</td>
							<td class="tableRight">
							<input type="text" class="textboxOne" name="hostid" id="hostid" onblur="details();" size="20" /><span class="asterisk">*</span>
						  </tr>
						   <tr>
							<td class="tableLeft"> Salutation:</td>
							<td class="tableRight"><input type="text" name="rprefix" id="rprefix" readonly="true" class="readOnly" size="10" /></td>
						  </tr>		
						  <tr>
							<td class="tableLeft"> Organizer First Name:</td>
							<td class="tableRight">
							<input type="text" class="readOnly" readonly="true"  name="riderFname_id" id="riderFname_id" size="20" />
							</td>
						  </tr>
						   <!-- <tr>
							<td class="tableLeft"> Organizer Middle Name:</td>
							<td class="tableRight">
							<input name="textbox62" type="text" class="textboxOne" size="25" readonly value=" " />
							</td>
						  </tr>-->
						  
						    <tr>
							<td class="tableLeft"> Organizer Last Name:</td>
							<td class="tableRight">
							<input type="text" class="readOnly" readonly="true"  name="riderLname_id" id="riderLname_id" size="20" />
							</td>
						  </tr>

						  
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input type="text"  class="readOnly"  readonly="true" name="rStreet" id="rStreet" size="20"/></td>
						  </tr>
											  
						  <tr> 
							<td class="tableLeft">Country:</td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="countryId" id="countryId" size="15" /></td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">State : </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rState" id="rState" size="20" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">City:</td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rCity" id="rCity" size="15" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Zip: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rZipcode" id="rZipcode" size="8" /></td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Phone:</td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="riderPhone_id" id="riderPhone_id" size="15" />
</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Mobile No: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rCell" id="rCell" size="15" /></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Email ID: </td>
							<td class="tableRight"><input type="text" class="readOnly" readonly="true" name="rEmail" id="rEmail" size="20" /></td>
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
									<td class="tableLeftTxtArea">Assessor Detail:</td>
									<td class="tableRight"><span class="asterisk"> 
									  <textarea name="acessdet" id="txtarea"  class="textAreaOne" rows="5" ></textarea>
									  *</span></td>
								  </tr>
								  <tr>
									<td class="tableLeftTxtArea"> Facilities To Be Used Specifically:  </td>
									<td class="tableRight">
									<input type="checkbox" name="cctry" id="ctry" value="Cross-Country Schooling" />	Cross-Country Schooling
									<input type="checkbox" name="rclinic" id="rclinic" value="Riding Clinic" />	Riding Clinic
									<input type="checkbox" name="stab" id="stab" value="Stablity" />	Stablity
									<input type="checkbox" name="cbx_other" id="Otherchckbx" value="others" onChange="isOtherscheck();" />	Other<span class="asterisk">*</span></td>
								  </tr>
								  <tr>
									<td class="tableLeft"> If Other Specify:</td>
									<td class="tableRight"><input type="text" class="textboxOne" disabled="disabled" id="txtOther" name="others" /></td>
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
							<td class="tableRight"><input type="text" class="textboxOne" name="lownernam" id="name1" />
							<span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td height="27" class="tableLeft">Business Name : </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="busname" id="bizname"/>
							<span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Address:</td>
							<td class="tableRight"><input type="text" class="textboxOne" name="laddr" id="address" />
							<span class="asterisk">*</span></td>
						  </tr>
									  <tr>
										<td height="27" class="tableLeft">Country: </td>
										<td class="tableRight">
										 <select name="ctry_sel" id="select11" class="selectboxOne" onChange="FillState(document.frmMeeICPAssessment.select11, document.frmMeeICPAssessment.select21, '');">
											<option selected="selected">Select One</option>
										  </select>		<span class="asterisk">*</span>
										 </td>
									
									  </tr>
						  <tr>
							<td class="tableLeft"> State:</td>
							<td class="tableRight">
							<select name="stat_sel" id="select21" class="selectboxOne" > 
								<option selected="selected">Select One</option>
							  </select>			
							  <span class="asterisk">*</span></td>
						  </tr>
						  <tr>
							<td class="tableLeft">City: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="lcity" id="city2" />
							<span class="asterisk">*</span></td>
						  </tr>
						  <tr>
						  <tr>
							<td class="tableLeft">Zip: </td>
							<td class="tableRight"><input type="text" class="textboxOne" name="lzip" id="zip2" maxlength="10"/>
							<span class="asterisk">*</span></td>
						  </tr>
						  <tr>
                            <td class="tableLeft">Phone: </td>
                            <td class="tableRight"><input type="text" class="textboxOne" name="lph" id="phone2"/>
							<span class="asterisk">*</span></td>
						    </tr>
						  <tr>
                            <td class="tableLeft style1">Fax: </td>
                            <td class="tableRight"><input type="text" class="textboxOne" name="lfax" id="fax2" /></td>
						    </tr>

						  <tr>
						  
							<td colspan="2" > 
						 
							  <table width="100%" border="0">
							  <tr>
								<td class="tblDescrp">
								  <strong>ACCEPTANCE OF TERMS:</strong><br />
								   I have read the Requirements for the <strong>ICP Activity</strong> written on the reverse of this
								  Application, and the Release Form. <br />
								  I understand those Requirements and terms and the responsibility I accept as a
								  <strong>ICP organizer</strong>. I agree as a condition of registration of this activity to adhere to those conditions
								  and to pay the appropriate fees.							  	</td>
							  </tr>
								<tr>
								  <td class="alignLeft">       
									  <input type="checkbox" name="checkbox6" id="acceptterm" value="checkbox" /> I Accept the terms and conditions.
								 <span class="asterisk">*</span> </td>
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
									<td class="tableRight"> <strong>$</strong> <input name="tot_amt" type="text" readonly="true" id="txtamount" value="0" class="textboxOne" size="10" />
									  <span class="asterisk">*</span></td>
								  </tr>
								  
								  <tr>
									<td class="tableLeft">
										<input type="radio" size="10" name="payment" id="r11" value="check" onclick="switchDiv('chkEnc'), showHideRadio('r11','chrgCrd','check'), cardclear();" /> Check enclosed.				 	</td>
									<td class="tableRight">
										<input type="radio" size="10" name="payment" id="r11" value="card" checked="checked" onclick="switchDiv('chrgCrd'), showHideRadio('r11','chkEnc','card'), checkclear();" /> Please charge my card.					</td>
								  </tr>	

								  <tr id="chkEnc">
									<td colspan="2">
									
										<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
											<tr>
												<td class="tblMainHead" colspan="3">Check Details:</td>
											</tr>
											<tr>
												  <td colspan="2" class="tableSpan">
												  If paying by check please mail your payment to:  <br />
												    <br />
												    <strong>
												    <br />
												    ICP Registration
												    <br />
												    525 Old Waterford Road NW
												    <br />
												    Leesburg, VA 20176												    </strong><br />
												    <br />
											        <strong>Note:</strong> Your registration status will be pending until check is processed.												  <br />
										          <br /></td>
											  </tr>
											<tr>
											  <td class="tableLeft">Check No:</td>
											  <td class="tableRight"><input type="text" name="chkno" id="txtChNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
											</tr>
											<tr>
											  <td class="tableLeft">Check Date:</td>
											  <td class="tableRight"><input type="text" name="chkdat" id="checkDate" class="textboxOne" readonly="true" size="16" /> <span class="asterisk">*</span> <a href="javascript:cal2.popup();"> <img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>							  
											  </td>
											 
											</tr>
											<tr>
											  <td class="tableLeft">Bank Name:</td>
											  <td class="tableRight"><input type="text" name="infav" id="inFavorof" class="textboxOne" size="16" /> <span class="asterisk">*</span>	
											  
											   </td>
											</tr>
											<tr>
											  <td class="tableLeft">Name On Check:</td>
											  <td class="tableRight"><input type="text" name="nameCheck" id="nameCheck" class="textboxOne" size="16" /> <span class="asterisk">*</span>
											</td>
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
											  <td class="tableLeft">Card Type:</td>
											  <td class="tableRight">
													<select name="ctyp" id="ccTypeId" class="selectboxOne">
													  <option selected="selected">Select One</option>
													  <option value="Visa">Visa</option>
													  <option value="Master Card">Master Card</option>
													  <option value="AmEx">AmEx</option>
													</select>
													<span class="asterisk">*</span>											  </td>
											</tr>
											<tr>
												<td class="tableLeft">Card No.:</td>
											  <td class="tableRight"><input name="cardno" type="text" id="txtCard" class="textboxOne" size="16" />
										      <span class="asterisk">*</span></td>
											</tr>
											
											<tr>
												<td class="tableLeft">Card CVV No.:</td>
											  <td class="tableRight"><input type="text" name="cvvno" id="txtCvvId" class="textboxOne" size="5" />
												  <span class="asterisk">*</span></td>
											</tr>
											
											
											
											<tr>
											  <td class="tableLeft">Print Name On Card:</td>
											  <td class="tableRight"><input type="text" name="pname" id="txtPrName" class="textboxOne" size="25" />
												  <span class="asterisk">*</span></td>
											</tr>
											
											<tr>
											  <td class="tableLeft">Expiry Date:</td>
											  <td class="tableRight">
													<select name="expirymonth" id="selExpMonthId" class="selectboxOne">
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
													<select name="expiryyear" id="selExpYearId" class="selectboxOne">
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
													<span class="asterisk">*</span>											 </td>
										  </tr>
									 </table>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="alignCenter"><input type="submit" value="Submit" class="gradBtn" /></td>
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
							<%@ include file = "../../include/footer.jsp" %>
						<!-- FOOTER ENDS HERE -->
					</td>
				  </tr>
</table>


 

</body>
<script language="javascript">
	var cal1 = new calendar2(document.forms['frmMeeICPAssessment'].elements['date']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	var cal2 = new calendar2(document.forms['frmMeeICPAssessment'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>
<script>
	FillCountry(document.frmMeeICPAssessment.select11, document.frmMeeICPAssessment.select21, '---Select---');
	FillState(document.frmMeeICPAssessment.select11, document.frmMeeICPAssessment.select21, '');
</script>

</html>
 
