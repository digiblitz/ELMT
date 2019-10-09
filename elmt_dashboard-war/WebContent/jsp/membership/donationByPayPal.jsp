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
<%@ page import="com.mrm.util.DonationVO"%>
<%@ page import="com.mrm.util.*"%>
<%@ page import="com.common.util.*"%>
<%@ page import="com.form.util.*" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="membership.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/calendar2.js" type="text/javascript" ></script>
<script src="javascripts/frmDonateProg.js" type="text/javascript" ></script>
<script src="javascripts/frmUserDonate.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">

function disableOthers(){
	var size = document.frmDonateProg.donCbxCt.value;
	if(document.frmDonateProg.endowmentChk.checked==true){
		for(i=0;i<size;i++)	{
			chbxname = "donCb"+i;
			txtname="donTb"+i;
			dontxtname="donNametb"+i;
			donNametbname="donNametb"+j;
			var checkboxName = document.getElementById(''+chbxname).value;
			var splited = checkboxName.split("#");
			
			if(splited[1]=="USEA Endowment Trust"){
				document.getElementById(''+chbxname).disabled=false;
				document.getElementById(''+txtname).disabled=false;
				document.getElementById(''+dontxtname).disabled=false;
				document.getElementById(''+donNametbname).disabled=false;
				document.getElementById(''+txtname).value=0.0;
				document.getElementById(''+chbxname).checked=true;
			}
			else{
				document.getElementById(''+chbxname).disabled=true;
				document.getElementById(''+txtname).disabled=true;
				document.getElementById(''+dontxtname).disabled=true;
				//document.getElementById(''+donNametbname).disabled=true;
				document.getElementById(''+txtname).value=0.0;
				document.getElementById(''+chbxname).checked=false;
			}
		}
		donaName();
		Sumup();
	}
	else{
		for(i=0;i<size;i++)	{
			chbxname = "donCb"+i;
			txtname="donTb"+i;
			dontxtname="donNametb"+i;
			donNametbname="donNametb"+j;
			document.getElementById(''+chbxname).disabled=false;
			document.getElementById(''+txtname).disabled=false;
			document.getElementById(''+dontxtname).disabled=false;
			document.getElementById(''+donNametbname).disabled=false;	
		}	
	}
}

function focus_login()
{
for(i=0;i==0;i++){
donNametb="donNametb"+i;
document.getElementById(donNametb).focus();	
}
}
</script>


</head>

<%
	String firstName = (String)session.getAttribute("firstName"); 
	String lastName= (String)session.getAttribute("lastName");
	String Usrname = firstName+" " +lastName;
	
	
	
	 session.removeAttribute("INVId");
	 System.out.println("1");
%>

<body onload="Sumup();donaName();focus_login();">
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
			  <form name="frmDonateProg" id="frmDonateProg" method="post" action="./DonationAction.do" onsubmit="return myValidate();"> 
			   <input type="hidden" name="donationProcess" value="insertPay"/>
			   <input type="hidden" name="inVoiceId" id="inVoiceId" value="1"/>	 
			   <div class="cmmnForm">
			   <div class="colspan">
		USEA Membership:<span class="styleBoldTwo"> Please Support these USEA Programs </span>	</div>	
						<div id="commonBG" class="textCommon" style="padding:8px;">
						The USEA only accepts payment by credit card for services purchased online. If you prefer to pay by check, please complete the appropriate <a href="http://useventing.com/start.php?section=docs" target="_blank" >form </a>and mail with your check to the USEA office.
						<br/><br/>
						A suggested donation to USEA, Inc. has been added. Please uncheck the box to remove this donation, or change the amount if desired. To designate this donation to another, enter text in the corresponding name field (ie: In memory of.., Anonymous, Bob and Sarah Smith, etc.) The USEA is a 501 (c) (3) educational organization and all donations are fully tax deductible. Donations made on this page are considered restricted and will be use only toward the selected categories. </div>
						<div class="row">
							
		 
									   <%
									   ArrayList donDet = new ArrayList();
									   donDet =(ArrayList) request.getAttribute("donDet");
											 if(donDet!=null)
											 {
												String doncbname;
												String dontbname;
												String donNametbname;
												String donChked="";
												
												int donsiz=donDet.size();
												
												for(int j=0;j<donDet.size();j++){
														doncbname="donCb"+j;
														dontbname="donTb"+j;
														donNametbname="donNametb"+j;
														
														DonationVO donvo=new DonationVO();
														donvo=(DonationVO)donDet.get(j);
														 
														String donId=donvo.getDonationId();
														String donName=donvo.getDonationName();
														String donAmt=donvo.getDonationPrice();
														
														DecimalFormat myFormatter = new DecimalFormat("######.00");
														double finalAmt = Double.parseDouble(donAmt);
														System.out.print("finalAmt" + finalAmt);
														
														String output  = myFormatter.format(finalAmt);
														 System.out.print("output" + output);
														String doncnct=donId+"#"+donName+"#"+donAmt;
														System.out.println("doncnct :"+doncnct);
													
														 String preChk="";
																	    String dis_stat="";
																	 
																	 if(donvo.isPrecheckStatus())
																	 {
																	 	preChk="checked";
																	 	System.out.println("preChk :"+preChk);
																	 }
																	 else
																	 {
																	 	dis_stat="disabled";
																	 }
										%>
							
																	<span class="formXThree">
																	<input type="checkbox" <%=preChk%> name="<%=doncbname%>" id="<%=doncbname%>" value="<%=doncnct%>" onclick="txtBxDisabEnab2('<%=doncbname%>','<%=dontbname%>');donaName();Sumup();"/>	<%=donName%>
																	</span>
																	<span>&nbsp;<strong>$</strong> <input type="text" name="<%=dontbname%>" id="<%=dontbname%>" class="textboxOne" <%=dis_stat%> value="<%=donAmt%>" size="10" onblur="Sumup();" /> &nbsp;Name: &nbsp;
																	<%
																	if(preChk.equalsIgnoreCase("checked")){%>
																	<input type="text" name="<%=donNametbname%>" id="<%=donNametbname%>" class="textboxOne" size="10" value="<%=firstName%>&nbsp;<%=lastName%>" /><br /><br />																	
																	<% } 
																	else{%>
																	<input type="text" name="<%=donNametbname%>" id="<%=donNametbname%>" class="textboxOne" size="10" /><br /><br />																	
																	<% } %>
																	</span>
																	
																	<%}%>
																	<input type="hidden" name="donCbxCt" value="<%=donsiz%>" />
																	<%}%>
																					
															</div>
															<%
															if(session.getAttribute("loggedBy")!=null){
															%>
															<div class="row">
																<span class="formXThree"><input type="checkbox" name="endowmentChk" id="endowmentChk" onclick="disableOthers();"/><strong>Endowment Account</strong></span>
															</div>
														<div class="row">
														
														<span class="label">Total Amount:</span>
														
														<span class="formX"><strong>$</strong><input name="tot_amt1" id="totalAmount1" type="hidden" class="readOnly" value="" readonly="true" size="10" />
														<input name="tot_amt" id="totalAmount" type="text" class="readOnly" value="" readonly="true" size="10" /> U.S dollars</span>									</div>
														
														<div class="rowHead">Payment Information:	</div>
														
														<div class="row">
														<span class="floatLeft">
														<input type="radio" size="10" name="r11" value="check" onclick="switchDiv('chkEnc'), showHideRadio('r11','chrgCrd','check'),checkclear(),hideImgDiv('cvvImg');" /> 
														Check enclosed.
														<input type="radio" size="10" name="r11" value="card" checked="checked" onclick="switchDiv('chrgCrd'), showHideRadio('r11','chkEnc','card'),cardclear(),showImgDiv('cvvImg');" /> Please charge my card.									  </span>				</div>
														<input type="hidden" name="hidVal" value="adminLogin"/>	
															<%
															}else{
															%>
															<div class="row">
														
														<span class="label">Total Amount:</span>
														
														<span class="formX"><strong>$</strong><input name="tot_amt1" id="totalAmount1" type="hidden" class="readOnly" value="" readonly="true" size="10" />
														<input name="tot_amt" id="totalAmount" type="text" class="readOnly" value="" readonly="true" size="10" /> U.S dollars</span>									</div>
														
														<div class="rowHead">Payment Information:	</div>
															<div class="row">
															<span class="floatLeft">
															<input type="radio" size="10" name="r11" value="card" checked="checked" onclick="switchDiv('chrgCrd'), showHideRadio('r11','chkEnc','card'),cardclear();" /> Please charge my card.	</span>											</div>
															<input type="hidden" name="hidVal" value="userLogin"/>	 
															<%}%>
									<input type="hidden" name="userNam" value="<%=Usrname%>" />

																<div id="chkEnc">
													
													<div class="colspan">
														<strong>Check Details:</strong>													</div>
													<div class="tblDescrp">
														<p>
															If paying by check (payable in U.S dollars to "USEA"), please mail your payment to:  <br /><br />
															
															<strong>USEA
															<br />
															Donation
															<br />
															525 Old Waterford Road, NW
															<br />
															Leesburg, VA 20176															</strong><br /><br />
															
															
														</p>
													</div>
													<div class="row">
														<span class="label">Check No:</span>
														<span class="formX"><input name="checkNo" type="text" id="txtChNumber" class="textboxOne"  size="16" maxlength="25" /> <span class="asterisk">*</span>	</span>													</div>
													
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
														<span class="formX"><input name="checkDate" type="text" id="checkDate"  class="textboxOne" value="<%=chkDat2%>"  size="10" />
														<span class="asterisk">*</span><a href="javascript:cal1.popup();">
					 <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0">
					</a> </span>	</div>
													<div class="row">
														<span class="label">Bank Name :</span>

															<span class="formX">
															<input name="inFavorof" id="inFavorof" type="text" class="textboxOne"  size="30" maxlength="60" /> <span class="asterisk">*</span>
															</span>	
													</div>
													<div class="row">
														<span class="label">Name on Check:</span>
														<span class="formX">
														<input name="nameOnchk" id="nameOnchk" type="text" class="textboxOne"  size="30" maxlength="25" /> <span class="asterisk">*</span>	</span>													</div>
				</div>
				
								
											<div id="chrgCrd">
													<div class="colspan">
														<strong>Card Details:</strong>													</div>
													<div class="row">
														<span class="label">Card Type:</span>
														<span class="formX">
														<select name="cardselect" id="ccTypeId" class="selectboxOne" >
														  <option selected="selected">Select One</option>
														  <option value="Visa">Visa</option>
														  <option value="MasterCard">MasterCard</option>
														  <option value="Amex">AmEx</option>
														</select>
														<span class="asterisk">*</span>														</span>													</div>
													<div class="row">
														<span class="label">Card No:</span>
														<span class="formX"><input name="cardNo" type="text" class="textboxOne" id="txtCard" size="20" maxlength="16" /> 
														<span class="asterisk">*</span>	</span>													</div>
													<%if(session.getAttribute("loggedBy")!=null){%>
													<div class="row">
														<span class="label">CVV No.:</span>
														<span class="formX"><input name="cVVNo" type="text" class="textboxOne" id="txtCvvId" size="5" maxlength="4" />&nbsp;(see details below) </span></div>
														<%}else{%>
													<div class="row">
														<span class="label">CVV No.:</span>
														<span class="formX"><input name="cVVNo" type="text" class="textboxOne" id="txtCvvId" size="5" maxlength="4" /> <span class="asterisk">*</span>&nbsp;(see details below)	</span></div><%}%>	
													<div class="row">
														<span class="label">Print Name On Card:</span>
														<span class="formX"><input name="printName" type="text" class="textboxOne" id="txtPrName" size="25" maxlength="40" /> 
														<span class="asterisk">*</span>	</span>													</div>
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

														for(int f=year5;f<=year5+10;f++) 
														{%>
															<option  value="<%=f%>"><%=f%></option>
														<%}
														%>												
															</select>
														<span class="asterisk">*</span>														</span>													</div>
														
									</div>			
									
									
							<div class="alignCenter">
								<span class="formX">
								 <input type="submit" value="Donate" class="gradBtn" />&nbsp;
								 <input type="button" value="Cancel" class="gradBtn" onclick="window.history.back(-1);"/>
								</span>							</div>
								<br/>
								<div id="cvvImg" class="alignCenter">
									<span class="label">&nbsp;</span>
									<span class="formX">
									<img src="images/cvv_graphic.jpg" />																				
									</span>
								</div>	
							<!-- spacer starts-->
							<div class="spacer">&nbsp;</div>	
							<!-- spacer ends-->
			  </form>
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

<script language="javascript">
	var cal1 = new calendar2(document.forms['frmDonateProg'].elements['checkDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;
	</script>

</html>

