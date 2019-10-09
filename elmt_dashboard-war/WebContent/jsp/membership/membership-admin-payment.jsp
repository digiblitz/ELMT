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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="com.hlcmrm.util.HLCDonationVO"%>
<%@ page import="com.hlcmrm.util.*"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ page import="com.hlcform.util.*" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMembRegi.js" type="text/javascript" ></script>
<script src="javascripts/frmAdminPayment.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript"></script>

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
		
                        
			<%
                            String amt="";
                            amt=(String)request.getAttribute("amt");
                            String payId=(String)request.getAttribute("payId");
                            String usrStat=(String)request.getAttribute("usrStat");
                            String userId=(String)request.getAttribute("userId");
                            String defStat=(String)request.getAttribute("defStat");
							String singlePayId = (String) request.getAttribute("singlePayId");
							String cs="";
							String ccStatus=(String)request.getAttribute("cStatus");
							if(ccStatus!=null){
							cs=ccStatus;
							}else{
							cs="cs";
							}
							
							if(singlePayId==null || singlePayId.trim().length()==0){
								singlePayId = "";
							}
                            String pendAmt="";
							
							if(session.getAttribute("pendingAmt")!=null)
							{
								pendAmt=(String)session.getAttribute("pendingAmt");
							}
							
                            System.out.println("defeciency Payment Status :"+defStat);
                            
                            System.out.print("userId in Jsp :"+userId);
                            
                         %>
						<form name="frmMembRegi" id="myform" method="post" class="formcss" action="./AdminMembPayment.do" onsubmit="return myvalidate(this);">
									<input type="hidden" name="paymentId" value="<%=payId%>" />
									<input type="hidden" name="process" value="payment" />
									<input type="hidden" name="usrStat" value="<%=usrStat%>" />
									<input type="hidden" name="userId" value="<%=userId%>" />
									<input type="hidden" name="defStat" value="<%=defStat%>" />
									<input type="hidden" name="singlePayId" value="<%=singlePayId%>" />
									<input type="hidden" name="ccStatus" value="<%=ccStatus%>" />
																		
                                                                       
													<div class="rowHead">
														Payment Information:
													</div>
															<div class="row">
																<span class="label">Total Amount:</span>
																<span class="formX"><strong>$</strong> <input name="tot_amt" id="totalAmount" type="text" class="textboxOne" value="<%=amt%>" readonly size="10" />&nbsp; U.S. Dollars</span>
															</div>
															
															<%
															
															//out.print(" usrStat :"+usrStat);
															
																if(usrStat==null)
																{	
																
																   if(session.getAttribute("pendingAmt")!=null)
																   {
																	
															%>
															
															<div class="row">
																<span class="label">Pending Amount:</span>
																<span class="formX"><strong>$</strong> <%=pendAmt%>&nbsp; U.S. Dollars</span>
															</div>
															
															<%}}%>
															<%if(session.getAttribute("loggedBy")!=null || usrStat==null || cs.equalsIgnoreCase("card")){
															%>
															
															<div class="row">
																<span class="floatLeft">
																<input type="radio" size="10" name="r11" value="check" onclick="switchDiv('chkEnc'), showHideRadio('r11','chrgCrd','check'),clrRad(),hideImgDiv('cvvImg');" /> Check enclosed.
																<input type="radio" size="10" name="r11" value="card" checked="checked" onclick="switchDiv('chrgCrd'), showHideRadio('r11','chkEnc','card'),clrRad(),showImgDiv('cvvImg');" /> Please charge my card.
																</span>
															</div>
															 <input type="hidden" name="hidVal" value="adminLogin"/>
															<%}else{
															%>
															<div class="row">
																<span class="floatLeft">
																<input type="radio" size="10" name="r11" value="card" checked="checked" onclick="switchDiv('chrgCrd'),clrRad(),showImgDiv('cvvImg');" /> Please charge my card.
																</span>
															</div>
															<input type="hidden" name="hidVal" value="userLogin"/>	
															<%}%>
																	<div id="chkEnc">
																			
																			<div class="colspan">
																				<strong>Check Details:</strong>	
																			</div>
																			<div class="tblDescrp">
																				<p>
																					If paying by check, please mail your payment to:  <br /><br />
																					
																					<strong>
																					<br />
																					Member Registration
																					<br />
																					525 Old Waterford Road, NW
																					<br />
																					Leesburg, VA 20176
																					</strong><br /><br />
																					
																					<strong>Note:</strong> <span class="styleBoldOne">Your registration status will be pending until check is processed.</span> <br /><br />
																				</p>
																			</div>
                                                                                                                                                        
                                                                                                                                                        <%  if(usrStat==null){%>
																			<div class="row">
																				<span class="label">Check Amount :</span>
																				<span class="formX">
																				<input name="chckAmount" id="chckAmount" type="text" class="textboxOne"  size="30" maxlength="60" /> </span>
																			</div>
																			<% }%>
                                                                                                                                                        
																			<div class="row">
																				<span class="label">Check No:</span>
																				<span class="formX"><input name="checkNo" type="text" id="txtChNumber" class="textboxOne"  size="16" maxlength="25" /> <span class="asterisk">*</span>	</span>
																				
																				<input name="pendAmt" type="hidden" value="<%=pendAmt%>"/>
		
		<input name="totAmt" type="hidden" value="<%=amt%>"/>
																				
																			</div>
																			
																		<%
																		    String chkDat2="";
																		
																			java.util.Calendar c6 = java.util.Calendar.getInstance();
																			int dyChk = c6.get(Calendar.DATE);
																			int mnth = c6.get(Calendar.MONTH);
																			
																			int mnthChk = mnth+1;
																			int yrChk = c6.get(Calendar.YEAR);
																			
																			if(mnthChk<10)
																			{
																				chkDat2 = "0"+mnthChk+"/"+dyChk+"/"+yrChk;
																			}
																			else
																			{
																				
																				chkDat2 = mnthChk+"/"+dyChk+"/"+yrChk;
																			}
																			%>
																						
																			<div class="row">
																				<span class="label">Check Date:</span>
																				<span class="formX"><input name="checkDate" type="text" id="checkDate"  class="textboxOne" value="<%=chkDat2%>"  size="10" /><span class="asterisk">*</span><a href="javascript:cal1.popup();">
					 <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
					</a> </span></div>
                                        
																			<div class="row">
																				<span class="label">Bank Name :</span>
																				<span class="formX">
																				<input name="inFavorof" id="inFavorof" type="text" class="textboxOne"  size="30" maxlength="60" /> <span class="asterisk">*</span>	</span>
																			</div>
																			<div class="row">
																				<span class="label">Name on Check:</span>
																				<span class="formX">
																				<input name="nameOnchk" id="nameOnchk" type="text" class="textboxOne"  size="30" maxlength="25" /> <span class="asterisk">*</span>	</span>
																			</div>
																	</div>
																	
																	<div id="chrgCrd">
																			<div class="colspan">
																				<strong>Card Details:</strong>
																			</div>
																			<div class="row">
																				<span class="label">Card Type:</span>
																				<span class="formX">
																				<select name="cardselect" id="ccTypeId" class="selectboxOne" >
																				  <option selected="selected">Select One</option>
																				  <option value="Visa">Visa</option>
																				  <option value="MasterCard">Master Card</option>
																				  <option value="American Express">AmEx</option>
																				</select>
																				<span class="asterisk">*</span>	
																				</span>
																			</div>
																			<div class="row">
																				<span class="label">Card No:</span>
																				<span class="formX"><input name="cardNo" type="text" class="textboxOne" id="txtCard" size="20" maxlength="16" /> 
																				<span class="asterisk">*</span>	</span>
																			</div>
																		<%if(session.getAttribute("loggedBy")!=null || usrStat==null || cs.equalsIgnoreCase("card")){%>
																			<div class="row">
																				<span class="label">CVV No.:</span>
																				<span class="formX"><input name="cVVNo" type="text" class="textboxOne" id="txtCvvId" size="5" maxlength="4" /> &nbsp;(see details below)</span></div>
																			<%}else{%>
																			<div class="row">
																				<span class="label">CVV No.:</span>
																				<span class="formX"><input name="cVVNo" type="text" class="textboxOne" id="txtCvvId" size="5" maxlength="4" /> 
																				<span class="asterisk">*</span>&nbsp;(see details below)	</span>
																			</div>
																			<%}%>
																			<div class="row">
																				<span class="label">Print Name On Card:</span>
																				<span class="formX"><input name="printName" type="text" class="textboxOne" id="txtPrName" size="25" maxlength="40" /> 
																				<span class="asterisk">*</span>	</span>
																			</div>
																			<div class="row">
																				<span class="label">Expiry Date:</span>
																				<span class="formX">
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
																						<%
																				java.util.Calendar c5 = java.util.Calendar.getInstance();
																				//int day = c.get(Calendar.DAY);
																				//int month = c.get(Calendar.MONTH);
																				int year5 = c5.get(Calendar.YEAR);
																				//String date = day+" / "+month+" / "+year;
																				System.out.println("Current Date = "+year5);

																				for(int f=year5;f<=year5+15;f++) 
																				{%>
																					<option  value="<%=f%>"><%=f%></option>
																				<%}
																				%>												
																					</select>
																				<span class="asterisk">*</span>	
																				</span>
																				
																			</div>
																			
															</div>			
															
															
													<div class="alignCenter">
														<span class="formX">
													 	 <input type="submit" value="Make Payment" class="gradBtn" />
													 	</span>
								  					</div>
													<br/>
													<div id="cvvImg" class="alignCenter">
														<span class="label">&nbsp;</span>
														<span class="formX">
														<img src="images/cvv_graphic.jpg" />																				
														</span>
													</div>
													
								<!--++++++++++++++++++++ Part D of the form Ends here ++++++++++++++++++++++++++++++ -->
					
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

<script language="javascript">
	var cal1 = new calendar2(document.forms['myform'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>

</html>


