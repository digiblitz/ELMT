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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMsgRequestDatabases.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<script>

function showHideRadio(radioNam,divId,radVal){

		if(document.getElementById(radioNam).value == 'check'){
				document.getElementById("chkEnc").style.display = "block";
				document.getElementById("chrgCrd").style.display = "none";
		}
		else {
				document.getElementById("chkEnc").style.display = "none";
				document.getElementById("chrgCrd").style.display = "block";
		} 
}

function checkCardValidate(){

  if(IsNumeric(document.getElementById("checkNoId").value)) {
  
		  alert("Please select the check number");


     return false;
  }

  if(Trim(document.getElementById("favorOfId").value).length==0) {
  
   alert("Please select the favourOf");
     return false;
  }


if(!IsNumeric(document.getElementById("cardNoId").value)) {
  
     lert("Please select the card number");
	 return false;
  }


var monthObj =  document.getElementById("expiryMonthId");
if(monthObj.options[monthObj.options.selectedIndex].value=="") {
	
	  alert("Please select the expiry month");
	
	 return false;
	}  

var yearObj =  document.getElementById("expiryYearId");
if(yearObj.options[yearObj.options.selectedIndex].value=="") {
	
	  alert("Please select the expiry year");
	
	 return false;
	}  


var cardTypeObj =  document.getElementById("cardTypeId");
if(cardTypeObj.options[cardTypeObj.options.selectedIndex].value=="") {
	
	  alert("Please select the card type");
	
	 return false;
	}  




}
</script>
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				    <html:form method="post" action="/msgDB.do"  onsubmit="return checkCardValidate();" >	
	                 <html:hidden property="method" value="saveDbAmount"/>

				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
					  <tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Request A Database Download </span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp"> 
						<strong>STEP <span class="styleBoldOne">2</span> OF <span class="styleBoldOne">2</span></strong><br /> 
						--------------------------------------------------------------------------------------------------------------------------<br />
						You can request for all the databases selected for download, right here. </td>
					  </tr>
					  <tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								 <tr>
									<td colspan="2" class="tblRowHead">Download Information</td>
								 </tr> 
								 <tr>
									<td>
									
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												  <tr>
													<td width="42%" height="25" class="tableLeft">Number of addresses selected:</td>
													<td width="30%" class="tableRight"><span class="labelTabHead"><bean:write name="messageDBForm" property="recordCount"/></span> Nos. </td>
													<input type="hidden" name="recordCount" value='<bean:write name="messageDBForm" property="recordCount"/>'/>
													
												  </tr>
												   <tr>
														<th height="25" class="tableLeft">&nbsp;</th>
														<td class="tableRight">&nbsp;</td>
												  </tr>
											</table>								    </td>
									  </tr>
									 
									  <tr>
									    <td colspan="2" height="35">
										
												  
												<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">  
													  <tr>
														<td colspan="2" class="tblRowHead">&nbsp;Payment Information</td>
													  </tr>
													  
													  <tr>
														<td class="tableLeft"><strong>Total Amount:</strong></td>
														<td class="tableRight"> <strong>$</strong><bean:write name="messageDBForm" property="amount"/>
														<input type="hidden" name="amount" value='<bean:write name="messageDBForm" property="amount"/>'/>
														  <span class="asterisk">*</span></td>
													  </tr>
													  
													 	  <tr>
									  	<td class="tableLeft">
									 		<input type="radio" size="10" name="paymentType" id="payId" value="check"  onclick="switchDiv('chkEnc');showHideRadio('payId','chrgCrd','check');cardClear();"/> Check enclosed.				 	</td>
									 	<td class="tableRight">
									 		<input type="radio" size="10" name="paymentType" value="card" id="payId1" checked="checked"
                                    onclick="switchDiv('chrgCrd');showHideRadio('payId1','chkEnc','card');checkClear();"  /> Please charge my card.					</td>
									  </tr>
													  
													  
													  
													  <tr id="chkEnc">
														<td colspan="2">
														
															<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
																<tr>
																	<td class="tblMainHead" colspan="3">Check Details:</td>
																</tr>
																<tr>
																  <td class="tableLeft">Check No:</td>
																  <td class="tableRight"><html:text property="checkNo" styleId="checkNoId" styleClass="textboxOne" size="20" /> <span class="asterisk">*</span>							  </td>
																</tr>
																<tr>
																  <td class="tableLeft">Check Date:</td>
																  <td class="tableRight"><html:text property="checkDate" styleId="checkDateId" styleClass="textboxOne" size="20" /> <span class="asterisk">*</span>(MM/dd/yyyy)</td>
																</tr>
																<tr>
																  <td class="tableLeft">In Favor Of:</td>
																  <td class="tableRight"><html:text property="favourOf" styleId="favorOfId" styleClass="textboxOne" size="20" /><span class="asterisk">*</span>							  </td>
																</tr>
															</table>														</td>
													 </tr>
													  
													  
													  
													  <tr id="chrgCrd">
														<td colspan="2">
														
															<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
																<tr>
																	<td class="tblMainHead" colspan="2">Card Details:</td>
																</tr>
																
																<tr>
																	<td class="tableLeft">Card No.:</td>
																  <td class="tableRight"><html:text property="cardNo" styleId="cardNoId" styleClass="textboxOne" size="20" />
																	  <span class="asterisk">*</span></td>
																</tr>
																
																<tr>
																	<td class="tableLeft">Card CVV No.:</td>
																  <td class="tableRight"><html:text property="cardCvvNo" styleId="cardNoId" styleClass="textboxOne" size="20" />
																	  <span class="asterisk">*</span></td>
																</tr>
																
																<tr>
																  <td class="tableLeft">Card Type:</td>
																  <td class="tableRight">
																	<html:select property="ccType" styleId="cardTypeId" styleClass="selectboxOne">
													  <option selected="selected">Select One</option>
													  <option>Visa</option>
													  <option>Master Card</option>
													  <option>AmEx</option>
										</html:select>
																		<span class="asterisk">*</span>																  </td>
																</tr>
																
																<tr>
																  <td class="tableLeft">Print Name On Card:</td>
																  <td class="tableRight"><html:text property="ccName" styleId="cardNameId" styleClass="textboxOne" size="20" />
																	  <span class="asterisk">*</span></td>
																</tr>
																
																<tr>
																  <td class="tableLeft">Expiry Date:</td>
																  <td class="tableRight">
																	<html:select property="ccExpMonth" styleId="expiryMonthId" styleClass="selectboxOne">
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
											</html:select>
											<html:select property="ccExpYear" styleId="expiryYearId" styleClass="selectboxOne">
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
											</html:select>
																		<span class="asterisk">*</span>																 </td>
															  </tr>
														 </table>													  </td>
													</tr>
												</table>										</td>
						      		  </tr>
									  <tr>
										<th colspan="2" height="35" class="alignCenter">
										<input type="submit" value="Submit" class="gradBtn" />
										<input type="button" value="Back" class="gradBtn" onclick="javascript:history.back(-1);" />										</th>
									  </tr>
								</table>
						</td>
					  </tr>
					   <tr>
						<td colspan="2" height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
					  </tr>
				</table>
					</html:form>

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
