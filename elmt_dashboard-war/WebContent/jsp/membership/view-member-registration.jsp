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
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="css/core-mff.css" type="text/css" rel="stylesheet" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<%@ page import="java.util.*;" %>
</head>

<body onload="initConditionTwo();">
 
<div class="cmmnForm">
	<div class="colspan">
		<strong>Membership: <span class="styleBoldTwo">Application Form</span> </strong> 
	</div>
    <div id="commonBG" class="textCommon" style="height:100px;"><strong>Membership valid from December 1 of the current year through Novenber 30 of the subsequent year.</strong> <br />
  </div>
<div class="tabOne" id="container-1">
	<ul> &nbsp;<span class="labelTabHead"> Form </span>
		<li id="li1" class="selected"><a href="javascript:void(0);" onclick="switchDiv('parta'),showHideTabs('partb','partc','partd');">Part A</a></li>
		<li id="li2"><a href="javascript:void(0);" onclick="switchDiv('partb'),showHideTabs('parta','partc','partd');">Part B</a></li>
		<li id="li3"><a href="javascript:void(0);" onclick="switchDiv('partc'),showHideTabs('parta','partb','partd');">Part C</a></li>
		<li id="li4"><a href="javascript:void(0);" onclick="switchDiv('partd'),showHideTabs('partb','partc','parta');">Part D</a></li>
	</ul>      
</div>&nbsp;
    <form name="frmMembRegi" id="myform" method="post" class="formcss" action="./InsertMembershipReg.do">

            <div id="parta" >
			<!--++++++++++++++++++++ Part A of the form starts here ++++++++++++++++++++++++++++++ -->

								<div class="rowHead">
									Membership Information:
								</div>
							
										<div class="row">
										
										<% 
                                             Vector dispMembershipTypeVect=new Vector();
                                             dispMembershipTypeVect=(Vector)session.getAttribute("dispMembershipTypeVect");
		                                     Enumeration itrators= (Enumeration)dispMembershipTypeVect.elements();
                                                                  
                                        %>
										
											<span class="label">Membership Type:</span>
											<span class="formX">
											<select name="selDisp" id="select" class="selectboxOne" onchange="dispVal('selDisp', 'amount_txt');">
											  <option selected="selected" value=" Amount">Select One</option>
                                                                                          
                                             <% while(itrators.hasMoreElements()){
                                                       String[] sarray = (String[]) itrators.nextElement();

                                                       String memberTypeId = sarray[0];
                                                       String membershipName = sarray[1];
                                                       String membershipAmount = sarray[2];
                                                       String cnct=memberTypeId+"#"+membershipName+"#"+membershipAmount;
                                             %>                                                                     
                                                        
                                             <option value="<%=cnct%>"><%=membershipName%></option>
											 
											 <%}%>
                                                                                         
											</select>
											&nbsp;
											<strong>$</strong>&nbsp;<input id="amount_txt" class="textboxOne" type="text" size="10" readonly="true" value=" Amount"/>
											</span>
										</div>
										
								  <div class="colspan">
											<strong>Are you a member of:</strong>
								  </div>
										
										<div class="row">
										
					<% 
                                     
                                            Vector areUAMemberVect=new Vector();
                                            areUAMemberVect=(Vector)session.getAttribute("areUAMemberVect");
		                                    Enumeration itr= (Enumeration)areUAMemberVect.elements();
                                                                                          
                                       %>
										
											<span class="label">Member Type:</span>
											<span class="formX">
											<select name="memType" id="select" class="selectboxOne">
											  <option selected="selected" value="Select One">Select One</option>
                                                                                          
                                                 <% while(itr.hasMoreElements()){
                                                    String[] strarray = (String[]) itr.nextElement();
                                                    String memTypeId = strarray[0];
                                                    String memName = strarray[1];
                                                                                              
                                                    String cncatin=memTypeId+"#"+memName;
  
                                                 %>  
                                                                                          
											  <option value="<%=cncatin%>"><%=memName%></option>
                                               <%}%>
			      							</select>
											</span>
										</div>
										<div class="row">
											<span class="label"> Member ID:</span>
											<span class="formX"> <input type="text" name="memId" class="textboxOne" size="25" /></span>
										</div>
								  <div class="colspan">
											<strong>Endowment Trust:</strong>
								  </div>
										  
										  <div id="commonBG" class="textCommon" style="height:85px;">
											<strong>To ensure the future of the sport of eventing , i would like to make a tax deductible
											donation Endowment Trust in the amount indicated.</strong> <br /><br />
											
											The Endowment Trust is organized and operated exclusively for charitable and educational purposes
											withing the terms of section 501(c)3 of the code of the Internal Revenue Service to benefit, perform
											the function of, or to carry out the Charitable Purposes Inc. 
										  </div>	
											
										
										<div class="row">
											<span class="label">Donation Amount:</span>
											 <span class="formX">
											<select id="donAmt_sel" class="selectboxOne" onchange="disEnTxt('5');">
											  <option selected="selected">Select One</option>
											  <option value="5">$5</option>
											  <option value="10">$10</option>
											  <option value="15">$15</option>
											  <option value="25">$25</option>
											  <option value="Others">Others</option>
											</select>
											</span>
										</div>
										<div class="row" id="donate">
											<span class="label">Specify other donation amount</span>
											<span class="formX"><input type="text" id="donAmnt_txt" class="textboxOne" size="20" disabled="disabled" value=" Other Amount"/> <strong>$</strong></span>
										</div>
										<div class="row">
										
										
										<% 
                                                  Vector membershipToVect=new Vector();
                                                  membershipToVect=(Vector)session.getAttribute("membershipToVect");
		
                                                  Enumeration itrat= (Enumeration)membershipToVect.elements();
                                                                                           
                                        %>
										
										
											<span class="label">For Memberships To Mailing Address:</span>
											<span class="formX">
											<select name="mailAddr" id="select" class="selectboxOne" onchange="dispVal('mailAddr','amountTwo_txt');">
											  <option selected="selected" value=" Amount">Select One</option>
                                                                                          
                                              <% while(itrat.hasMoreElements()){
                                
                                                     String[] starray = (String[]) itrat.nextElement();

                                                     String meTypeId = starray[0];
                                                     String meName = starray[1];
                                                     String meAmount = starray[2];
                                                                                                
                                                     String cncat=meTypeId+"#"+meName+"#"+meAmount;
                                               %>  
                                               					  
                                              <option value="<%=cncat%>"><%=meName%></option>
                                              <%}%>
											</select>
											&nbsp;
											<strong>$</strong>&nbsp;<input id="amountTwo_txt" class="textboxOne" type="text" size="5" readonly="true" value=" Amount"/>
											(Additional)
											</span>
										</div>
								
								<div id="forFam">		
							    <div class="colspan">
										<strong>Current Full / Life Member Info:</strong>
								</div>		
										<div class="row">
											<span class="label">Membership Number:</span>
											<span class="formX"><input type="text" name="memNo" class="textboxOne" size="15" /></span>
										</div>
								</div>		
									
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->	
							<div id="famAddOn">		
									<div class="colspan">
											<strong>Family Member Add On:</strong>
									</div>
										  <div id="commonBG" class="textCommon" style="height:35px;">
											I would like to add the following family member(s) to an existing Full or Life membership. Family 
											Memberships are available to novice and training level riders only.
										  </div>
										  <div class="row">
											<span class="label">How many family members are joining? </span>
											<span class="formX">
											<select name="famMemb" id="select" class="selectboxOne">
											  <option selected="selected">Select One</option>
											  <option value="One">One</option>
											  <option value="Two">Two</option>
											  <option value="Three">Three</option>
											  <option value="Four">Four</option>
											</select>
											&nbsp; (Maximum 4 Members)
											</span>
										  </div>
							 		
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
									<!-- spacer ends-->
									<!-- spacer starts-->
									<div class="spacer">&nbsp;</div>	
					      </div>				<!-- spacer ends-->	

			<!--++++++++++++++++++++ Part A of the form Ends here ++++++++++++++++++++++++++++++ -->
            </div>
			
			
             <div id="partb" >
			<!--++++++++++++++++++++ Part B of the form starts here ++++++++++++++++++++++++++++++ -->

             <!--// Family Memb One // -->						  
							<div>						
									<div class="colspan">
											<strong>Family Member One:</strong>
									</div>
									<div class="row">
											<span class="label">Salutation:</span>
											<span class="formX">
											<select name="select1" id="select" class="selectboxOne">
											  <option selected="selected">Select One</option>
											  <option>Mr.</option>
											  <option>Mrs.</option>
											  <option>Miss.</option>
											  <option>Ms.</option>
											</select>
											</span>
							  </div>
										<div class="row">
											<span class="label">First Name:</span>
											<span class="formX"><input type="text" name="fname1" class="textboxOne" size="20" /></span>
										</div>
										<div class="row">
											<span class="label">Middle Name:</span>
											<span class="formX"><input type="text" name="mname1" class="textboxOne" size="20" /></span>
										</div>
										<div class="row">
											<span class="label">Last Name:</span>
											<span class="formX"><input type="text" name="lname1" class="textboxOne" size="20" /></span>
										</div>
										<div class="row">
											<span class="label">Suffix:</span>
											<span class="formX"><input type="text" name="suffix1" class="textboxOne" size="20" /></span>
										</div>
										<div class="row">
											<span class="label">Date of Birth :</span>
											<span class="formX">
													<select name="birthmonth1" id="select" class="selectboxOne">
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
													<select name="birthday1" id="select" class="selectboxOne">
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
													<select name="birthyear1" id="select" class="selectboxOne">
														  <option value="" selected="selected" >Year</option>
														  <option  value="1960">1960</option>
														  <option  value="1961">1961</option>
														  <option  value="1962">1962</option>
														  <option  value="1963">1963</option>
														  <option  value="1964">1964</option>
														  <option  value="1965">1965</option>
														  <option  value="1966">1966</option>
														  <option  value="1967">1967</option>
														  <option  value="1968">1968</option>
														  <option  value="1969">1969</option>
														  <option  value="1969">1970</option>
													</select>
											</span>
										</div>
										<div class="row">
											<span class="label">Gender:</span>
											<span class="floatLeft">
											<input type="radio" size="10" name="gender1" value="male" /> Male
											<input type="radio" size="10" name="gender1" value="female" /> Female
											</span>
										</div>
										<div class="row">
											<span class="label">Phone:</span>
											<span class="formX"><input type="text" name="phone1" class="textboxOne" size="15" /></span>
										</div>
										<div class="row">
											<span class="label">Mobile:</span>
											<span class="formX"><input type="text" name="mobile1" class="textboxOne" size="15" /></span>
										</div>
										<div class="row">
											<span class="label">Fax:</span>
											<span class="formX"><input type="text" name="fax1" class="textboxOne" size="15" /></span>
										</div>
										<div class="row">
											<span class="label">EMail (Login ID):</span>
											<span class="formX"><input type="text" name="lid1" class="textboxOne" size="25" /></span>
										</div>
										<!-- spacer starts-->
										<div class="spacer">&nbsp;</div>	
										<!-- spacer ends-->
										<!-- spacer starts-->
										<div class="spacer">&nbsp;</div>	
										<!-- spacer ends-->	
										
							</div>
			<!--++++++++++++++++++++ Part B of the form Ends here ++++++++++++++++++++++++++++++ -->

            </div>
 			
			
			<div id="partc" >
			<!--++++++++++++++++++++ Part C of the form starts here ++++++++++++++++++++++++++++++ -->

				<!--// Family Memb Two // -->	
							<div>
									<div class="colspan">
											<strong>Family Member Two:</strong>
									</div>	
								
										<div class="row">
												<span class="label">Salutation:</span>
												<span class="formX">
												<select name="select" id="select" class="selectboxOne">
												  <option selected="selected">Select One</option>
												  <option>Mr.</option>
												  <option>Mrs.</option>
												  <option>Miss.</option>
												  <option>Ms.</option>
												</select>
												</span>
							  </div>
											<div class="row">
												<span class="label">First Name:</span>
												<span class="formX"><input type="text" class="textboxOne" size="20" /></span>
											</div>
											<div class="row">
												<span class="label">Middle Name:</span>
												<span class="formX"><input type="text" class="textboxOne" size="20" /></span>
											</div>
											<div class="row">
												<span class="label">Last Name:</span>
												<span class="formX"><input type="text" class="textboxOne" size="20" /></span>
											</div>
											<div class="row">
												<span class="label">Suffix:</span>
												<span class="formX"><input type="text" class="textboxOne" size="20" /></span>
											</div>
											<div class="row">
												<span class="label">Date of Birth :</span>
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
															  <option  value="1960">1960</option>
															  <option  value="1961">1961</option>
															  <option  value="1962">1962</option>
															  <option  value="1963">1963</option>
															  <option  value="1964">1964</option>
															  <option  value="1965">1965</option>
															  <option  value="1966">1966</option>
															  <option  value="1967">1967</option>
															  <option  value="1968">1968</option>
															  <option  value="1969">1969</option>
															  <option  value="1969">1970</option>
														</select>
												</span>
											</div>
											<div class="row">
												<span class="label">Gender:</span>
												<span class="floatLeft">
												<input type="radio" size="10" name="gender" value="male" /> Male
												<input type="radio" size="10" name="gender" value="female" /> Female
												</span>
											</div>
											<div class="row">
												<span class="label">Phone:</span>
												<span class="formX"><input type="text" class="textboxOne" size="15" /></span>
											</div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" class="textboxOne" size="15" /></span>
											</div>
											<div class="row">
												<span class="label">Fax:</span>
												<span class="formX"><input type="text" class="textboxOne" size="15" /></span>
											</div>
											<div class="row">
												<span class="label">EMail (Login ID):</span>
												<span class="formX"><input type="text" class="textboxOne" size="25" /></span>
											</div>
											
			  </div>		
									<!--// Family Memb Two // -->	
			<!--++++++++++++++++++++ Part C of the form Ends here ++++++++++++++++++++++++++++++ -->

            </div>						
			
			
			
			<div id="partd" >
			<!--++++++++++++++++++++ Part D of the form starts here ++++++++++++++++++++++++++++++ -->					
								<div class="rowHead">
									Payment Information:
								</div>
										<div class="row">
											<span class="label">Total Amount:</span>
											<span class="formX"><input type="text" name="totAmt" class="textboxOne" size="10" /> <strong>$</strong></span>
										</div>
										<div class="row">
											<span class="floatLeft">
											<input type="radio" size="10" name="r11" value="check" onclick="switchDiv('checkEncl'), showHideRadio('r11','chargeCard','check');" /> Check enclosed.
											<input type="radio" size="10" name="r11" value="card" onclick="switchDiv('chargeCard'), showHideRadio('r11','checkEncl','card');" /> Please charge my card.
											</span>
										</div>
										
												<div id="checkEncl">
														<div class="colspan">
															<strong>Check Details:</strong>
														</div>
														<div class="row">
															<span class="label">Check No:</span>
															<span class="formX"><input type="text" name="chkNo" class="textboxOne"  size="16" /></span>
														</div>
														<div class="row">
															<span class="label">Check Date:</span>
															<span class="formX"><input type="text" name="chkDat" class="textboxOne"  size="10" /></span>
														</div>
														<div class="row">
															<span class="label">In favor of:</span>
															<span class="formX"><input type="text" name="bank" class="textboxOne"  size="30" /></span>
														</div>
												</div>
												
												<div id="chargeCard">
														<div class="colspan">
															<strong>Card Details:</strong>
														</div>
														<div class="row">
															<span class="label">Card Type:</span>
															<span class="formX">
															<select name="ctypSelect" id="select" class="selectboxOne" >
															  <option selected="selected">Select One</option>
															  <option>Visa</option>
															  <option>Master Card</option>
															  <option>AmEx</option>
															</select>
															</span>
														</div>
														<div class="row">
															<span class="label">Card No:</span>
															<span class="formX"><input type="text" name="crdNo" size="20" class="textboxOne" /></span>
														</div>
													
														<div class="row">
															<span class="label">CVV No.:</span>
															<span class="formX"><input type="text" name="cvvNo" size="5" class="textboxOne" /></span>
														</div>
														<div class="row">
															<span class="label">Print Name On Card:</span>
															<span class="formX"><input type="text" name="prName" size="25" class="textboxOne" /></span>
														</div>
														<div class="row">
															<span class="label">Expiry Date:</span>
															<span class="formX">
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
															</span>
														</div>
										</div>			
										
										
								<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="Become A Member" class="gradBtn" /></span>
								</div>
			<!--++++++++++++++++++++ Part D of the form Ends here ++++++++++++++++++++++++++++++ -->

            </div>			


	</form>
</div>

</body>
</html>
