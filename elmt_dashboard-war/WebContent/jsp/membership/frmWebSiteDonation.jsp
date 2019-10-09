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
<script src="javascripts/calendar2.js" type="text/javascript" ></script>
<script src="javascripts/cscombo_new.js" type="text/javascript" ></script>
<script src="javascripts/frmDonateProg.js" type="text/javascript" ></script>
<script src="javascripts/frmWebSiteDonate.js" type="text/javascript" ></script>
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
			
			if(splited[1]=="HLC Endowment Trust"){
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
function rCheck(){
	var a = document.frmDonateProg.hlcNo.value;
	if(a!="" && a.indexOf(' ')!=0){
	riderDetails();
	}
    else{
	clearRider();
    }
}
var arHttpRequest;
var req;
     function riderDetails(){
		 param = document.frmDonateProg.hlcNo.value;
			var  url = null;
			if(param.length==0)
			return;
			url = "annualAjax.do?method=memberDetail&memberId="+escape(param);
			if (window.ActiveXObject){ 
				req = new ActiveXObject("Microsoft.XMLHTTP"); 
			} 
			else if (window.XMLHttpRequest){ 
				req = new XMLHttpRequest(); 
			} 
			req.onreadystatechange = processRider;         
			req.open("GET", url, true);
			req.send(null);  
} 
   /** 
    * This is the call back method 
    * If the call is completed when the readyState is 4 
    * and if the HTTP is successfull when the status is 200 
    * update the profileSection DIV 
    */ 
         function processRider(){ 
    			if (req.readyState == 4){ 
						   // alert(req.status);
							if(req.status == 200){ 
							//  alert(req.status);
								 var xmlDoc = req.responseXML.documentElement;
								  	var firstName =xmlDoc.getElementsByTagName('firstName')[0].childNodes[0].nodeValue;
									var lastName =xmlDoc.getElementsByTagName('lastName')[0].childNodes[0].nodeValue; 
									var city =xmlDoc.getElementsByTagName('city')[0].childNodes[0].nodeValue; 
									var state =xmlDoc.getElementsByTagName('state')[0].childNodes[0].nodeValue; 
									var fuName=firstName + " " +lastName;
									
								 
									var firstNameObj = document.getElementById("txtFName");
								    var lastNameObj = document.getElementById("txtLName");
									var cityObj = document.getElementById("txtCity");  
									var stateObj = document.getElementById("txtState"); 
									 
									
									chbxnum2=document.frmDonateProg.donCbxCt.value;
									for(i=0;i<chbxnum2;i++)	{ 
									chbxname = "donCb"+i;
									txtname="donTb"+i;
									dontxtname="donNametb"+i;
									
									if(document.getElementById(''+chbxname).checked){ 
									var fullNameObj= document.getElementById(''+dontxtname);
									}
									else{
									document.getElementById(''+dontxtname).value="";
									}
									}
									
																	 
								     firstNameObj.value =  firstName;
								     lastNameObj.value = lastName ;
									 cityObj.value = city ;  
									 stateObj.value = state ; 
									 fullNameObj.value=fuName ;
									
							} 
											if(req.status==500) {
												clearRider();
												alert("Membership Id is not valid");
												document.frmDonateProg.hlcNo.focus();
											}
											else{ 
											} 
						}	
				} 
function clearRider(){
				
		document.getElementById("hlcNo").value = "";
		document.getElementById("txtFName").value = "";
		document.getElementById("txtLName").value = "";
		document.getElementById("txtCity").value = "";
		document.getElementById("txtState").value = "";
		document.getElementById("txtState").value = "";  
		chbxnum2=document.frmDonateProg.donCbxCt.value;
			for(i=0;i<chbxnum2;i++)	{ 
			chbxname = "donCb"+i;
			txtname="donTb"+i;
			dontxtname="donNametb"+i;
			
			if(document.getElementById(''+chbxname).checked){ 
			document.getElementById(''+dontxtname).value="";
			}
			else{
			document.getElementById(''+dontxtname).value="";
			}
			}
}
function clearUserDetails(){
	document.frmDonateProg.txtPrefix.value="";
	document.frmDonateProg.txtFirstName.value="";
	document.frmDonateProg.txtLastName.value="";
	document.frmDonateProg.sadd_txt.value="";
	document.frmDonateProg.sadd_txt1.value="";
	document.frmDonateProg.scity_txt.value="";
	//document.frmDonateProg.sCountry_sel.value="USA";
	document.frmDonateProg.sState_sel.value="--Select--";
	document.frmDonateProg.szip_txt.value="";
	document.frmDonateProg.sphone_txt.value="";
	document.frmDonateProg.smob_txt.value="";
	document.frmDonateProg.email.value="";
} 
function clearMemberDetails(){
	document.frmDonateProg.hlcNo.value="";
	document.frmDonateProg.txtFName.value="";
	document.frmDonateProg.txtLName.value="";
	document.frmDonateProg.txtCity.value="";
	document.frmDonateProg.txtState.value="";
}			
function userClick(){
var chosen="";
	len1 = document.frmDonateProg.addr.length ;
	for(i=0;i<len1;i++){
		if(document.frmDonateProg.addr[i].checked){
			chosen= document.frmDonateProg.addr[i].value;	
			}
	}
//alert(" chosen:"+chosen);	
if(chosen == "No"){
var fName=document.frmDonateProg.txtFirstName.value;
var lName=document.frmDonateProg.txtLastName.value;
var fullName=fName+" " +lName;
//alert("fullName 1:"+fullName);
chbxnum2=document.frmDonateProg.donCbxCt.value;
 var userNam=fullName;
for(i=0;i<chbxnum2;i++)	{ 
		chbxname = "donCb"+i;
		txtname="donTb"+i;
		dontxtname="donNametb"+i;

		  if(document.getElementById(''+chbxname).checked){ 
			document.getElementById(''+dontxtname).value=userNam;
		  }
		  else{
			document.getElementById(''+dontxtname).value="";
		  }
	  }


//document.frmDonateProg.donNametbname.value=fullName;
}/*else{
var fName=document.frmDonateProg.txtFName.value;
var lName=document.frmDonateProg.txtLName.value;
var fullName=fName+" " +lName;
alert("fullName 2:"+fullName);
chbxnum2=document.frmDonateProg.donCbxCt.value;
 var userNam=fullName;
for(i=0;i<chbxnum2;i++)	{ 
		chbxname = "donCb"+i;
		txtname="donTb"+i;
		dontxtname="donNametb"+i;

		  if(document.getElementById(''+chbxname).checked){ 
			document.getElementById(''+dontxtname).value=userNam;
		  }
		  else{
			document.getElementById(''+dontxtname).value="";
		  }
	  }
}*/ 
}         
</script>


</head>

<%
	//String firstName = " ";//(String)session.getAttribute("firstName"); 
	//String lastName= " ";//(String)session.getAttribute("lastName");
	//String Usrname = firstName+" " +lastName;
%>

<body onload="Sumup();donaName();switchDiv('sAdd');showHideRadio('addr','pAdd','Primary');">
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop">
		<!-- HEADER STARTS HERE -->
		<%//@ include file = "../../include/header.jsp" %>
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
		
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
			  <form name="frmDonateProg" id="frmDonateProg" method="post" action="./webSiteDonation.do" onsubmit="return myValidate();"> 
			   <input type="hidden" name="donationProcess" value="insert"/>
			   <div class="cmmnForm">
	<div class="colspan">
		Membership:<span class="styleBoldTwo"> Please Support these Programs </span>	</div>
    <div id="commonBG" class="textCommon" style="padding:8px;">
	 A suggested donation, Inc. has been added. Please uncheck the box to remove this donation, or change the amount if desired. To designate this donation to another, enter text in the corresponding name field (ie: In memory of.., Anonymous, Bob and Sarah Smith, etc.) This is a 501 (c) (3) educational organization and all donations are fully tax deductible. Donations made on this page are considered restricted and will be use only toward the selected categories.</div>
			
			<div class="row">
			<span class="label">Member:</span>
			<span class="formX">
			<input type="radio" value="Yes" name="addr" size="10"  onClick="switchDiv('pAdd');showHideRadio('addr','sAdd','Secondary');clearUserDetails();" />Yes
			<input type="radio" value="No" name="addr" size="10" checked="checked" onclick="switchDiv('sAdd');showHideRadio('addr','pAdd','Primary');clearMemberDetails();" /> No or Not Sure of My ID Number									
			</span>	</div>	
			
					<div id="pAdd">
					<div class="row">
					<span class="label">MemberId:</span>
					<span class="formX">
					<input type="text" class="textboxOne" name="hlcNo" id="hlcNo" onblur="rCheck();" size="20" />
					<span class="asterisk">*</span>				 </span></div>
					<div class="row">
					<span class="label">First Name:</span>
					<span class="formX"><input type="text"  class="textboxOne" name="txtFName" id="txtFName" size="20" readonly="readonly" style="background-color:#F4F4F4; font-weight:bold; border:0px;"/></span>			</div>
					<div class="row">
					<span class="label">Last Name:</span>
					<span class="formX"><input type="text"  class="textboxOne" name="txtLName" id="txtLName" size="20" readonly="readonly" style="background-color:#F4F4F4; font-weight:bold; border:0px;" /></span>			</div>
					<div class="row">
					<span class="label">City:</span>
					<span class="formX"><input type="text"  class="textboxOne" name="txtCity" id="txtCity" size="15" readonly="readonly" style="background-color:#F4F4F4; font-weight:bold; border:0px;"/></span>			</div>
					<div class="row">
					<span class="label">State:</span>
					<span class="formX"><input type="text"  class="textboxOne" name="txtState" id="txtState" size="20" readonly="readonly" style="background-color:#F4F4F4; font-weight:bold; border:0px;"/></span>			</div>
					</div>
				 				<div id="sAdd">
											<div class="row">
											<span class="label">Salutation:</span>
											<span class="formX"><input type="text"  class="textboxOne" name="txtPrefix" id="txtPrefix" size="20" /></span>			</div>
											<div class="row">
											<span class="label">First Name:</span>
											<span class="formX"><input type="text"  class="textboxOne" name="txtFirstName" id="txtFirstName" size="20" /><span class="asterisk">*</span></span>			</div>
											<div class="row">
											<span class="label">Last Name:</span>
											<span class="formX"><input type="text"  class="textboxOne" name="txtLastName" id="txtLastName" size="20" onblur="userClick();"/><span class="asterisk">*</span></span>			</div>
											
											<div class="row">
												<span class="label">Address 1:</span>
											  <span class="formX"> 
											  <input type="text" value="" name="sadd_txt" id="sadd_txt" class="textboxOne" size="35" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Address 2:</span>
												<span class="formX"> <input type="text" value="" name="sadd_txt1" id="sadd_txt1" class="textboxOne" size="35" /></span>											</div>
												<div class="row">
												<span class="label">City:</span>
											  <span class="formX"><input type="text" value="" name="scity_txt"  id="sCity_txt" class="textboxOne" size="20" />
											    <span class="asterisk">*</span></span></div>
												<div class="row">
												<span class="label">State:</span>
											  <span class="formX"><select name="sstate_txt" id="sState_sel" class="selectboxOne">
												    </select><!--<input type="text" value="" name="sstate_txt" id="sState_txt" class="textboxOne" size="25" />-->
											    <span class="asterisk">*</span></span></div>
												<div class="row">
												<span class="label">Zipcode:</span>
											  <span class="formX"><input type="text" value="" name="szip_txt"  id="sZip_txt" class="textboxOne" size="8" />
											    <span class="asterisk">*</span></span></div>
											<div class="row">
												<span class="label">Country:</span>
											  <span class="formX"><select name="scountry_txt" id="sCountry_sel" class="selectboxOne" onChange="FillState(document.frmDonateProg.scountry_txt, document.frmDonateProg.sstate_txt,'');" >
                                                    </select><!--<input type="text" value="" name="scountry_txt" id="sCountry_txt" class="textboxOne" size="30" />-->
											    <span class="asterisk">*</span></span></div>								
											<div class="row">
												<span class="label">Phone:</span>
											  <span class="formX"><input type="text" name="sphone_txt"  id="sphone_txt" class="textboxOne" size="15" />
												(e.g. xxx yyy zzzz)</span></div>
											<div class="row">
												<span class="label">Mobile:</span>
												<span class="formX"><input type="text" name="smob_txt"  id="smob_txt" class="textboxOne" size="15" /></span>											</div>
											<div class="row">
											<span class="label">Email:</span>
											<span class="formX"><input type="text"  class="textboxOne" name="email" id="email" size="20" /><span class="asterisk">*</span></span>			</div>
								</div>
		
			
			<br/>
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
														
														HLCDonationVO donvo=new HLCDonationVO();
														donvo=(HLCDonationVO)donDet.get(j);
														 
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
																	<input type="text" name="<%=donNametbname%>" id="<%=donNametbname%>" class="textboxOne" size="10" /><br /><br />																	
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
															<% }
															%>
									<!--<input type="hidden" name="userNam" value="<%//=Usrname%>" />-->

																<div class="row">
																
										<span class="label">Total Amount:</span>
										 
										<span class="formX"><strong>$</strong><input name="tot_amt1" id="totalAmount1" type="hidden" class="readOnly" value="" readonly="true" size="10" />
										 <input name="tot_amt" id="totalAmount" type="text" class="readOnly" value="" readonly="true" size="10" /> U.S dollars</span>									</div>
								
									<div class="rowHead">Payment Information:	</div>
												
														<div class="colspan">
														<strong>Card Details:</strong>													</div>
													<div class="row">
														<span class="label">Card Type:</span>
														<span class="formX">
														<select name="cardselect" id="ccTypeId" class="selectboxOne" >
														  <option selected="selected">Select One</option>
														  <option value="Visa">Visa</option>
														  <option value="MasterCard">Master Card</option>
														  <option value="American Express">AmEx</option>
														</select>
														<span class="asterisk">*</span>														</span>													</div>
													<div class="row">
														<span class="label">Card No:</span>
														<span class="formX"><input name="cardNo" type="text" class="textboxOne" id="txtCard" size="20" maxlength="16" /> 
														<span class="asterisk">*</span>	</span>													</div>
												
													<div class="row">
														<span class="label">CVV No.:</span>
														<span class="formX"><input name="cVVNo" type="text" class="textboxOne" id="txtCvvId" size="5" maxlength="4" /> 
														</span>													</div>
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

														for(int f=year5;f<=year5+15;f++) 
														{%>
															<option  value="<%=f%>"><%=f%></option>
														<%}
														%>												
															</select>
														<span class="asterisk">*</span>														</span>													</div>
											
									
									
							<div class="alignCenter">
								<span class="formX">
								 <input type="submit" value="Donate" class="gradBtn" />&nbsp;

								 <input type="button" value="Cancel" class="gradBtn" onclick="window.history.back(-1);"/>
								</span>							</div>
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
	FillCountry(document.frmDonateProg.scountry_txt, document.frmDonateProg.sstate_txt, 'USA' );
	FillState(document.frmDonateProg.scountry_txt, document.frmDonateProg.sstate_txt, '');

</script>
</html>

