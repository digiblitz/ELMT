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
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.hlcmrm.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="javascripts/calendar2.js" type="text/javascript"></script>
<script src="javascripts/ChangehorseFailPay.js" type="text/javascript"></script>
<script src="javascripts/cscombo_new.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 

</head>
<body onload="cardClear();" >

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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
		   <td width="260" class="menuTablePad">
					<!-- LEFT MENU STARTS HERE -->
					<%@ include file = "../../include/menu-roles-leftmenu.jsp" %> 
					<!-- LEFT MENU ENDS HERE -->
		
					</td>
					<td width="500" class="subDeptTablePad">
						<!-- CONTENTS START HERE -->
				 
				
 		  </span>	</div>
	<div class="colspan">
		<strong>Membership:</strong> <span class="styleBoldTwo">Non-Human Payment Form </span>	</div>
			<%
					 
					String tempAmount = (String) request.getAttribute("totalAmount");
					String horseMemberId = (String) request.getAttribute("horseMemberId");
					String paymentId = (String) request.getAttribute("paymentId");	
					String horseName = (String) request.getAttribute("horseName");
                                        System.out.println("Payment id in jsp..........."+paymentId);
                                        String horseName1=(String) request.getAttribute("horseName1");
                                        String riderId =(String) session.getAttribute("riderId");
                                        String OwnerMailId=(String)request.getAttribute("OwnerMailId");
                                        String riderEmailId =(String)request.getAttribute("riderEmailId");
                                        String competitionName=(String)request.getAttribute("competitionName");
                                        String membershipName1=(String)request.getAttribute("membershipName1");
                                        String tempOwnerName=(String)request.getAttribute("tempOwnerName");
                                        String tempRiderName=(String)request.getAttribute("tempRiderName");
					
					System.out.print("totalAmount in change repay" + tempAmount);  
					System.out.print("horseMemberId in change repay" + horseMemberId); 
					System.out.print("paymentId in change repay" + paymentId); 
					System.out.print("horseName in change repay" + horseName); 
					  
					  			
            %>
<form name="myform" id="myform" method="post" class="formcss" action="ChngeRePay.do" onsubmit="return myValidate();">
						<input type="hidden" name="process" value="updatePayment" />
						<input type="hidden" name="paymentId" value="<%=paymentId%>" />
						<input type="hidden" name="horseMemberId" value="<%=horseMemberId%>" />
						<input type="hidden" name="horseName" value="<%=horseName%>" />
						<input type="hidden" name="tempAmount" value="<%=tempAmount%>" />
                                                 <input type="hidden" name="horseName1" value="<%=horseName1%>" />
                                                  <input type="hidden" name="riderId" value="<%=riderId%>" />
                                                  <input type="hidden" name="OwnerMailId" value="<%=OwnerMailId%>" />
                                                  <input type="hidden" name="riderEmailId" value="<%=riderEmailId%>" />					  
                                                  <input type="hidden" name="competitionName" value="<%=competitionName%>" />
                                                  <input type="hidden" name="membershipName1" value="<%=membershipName1%>" />
                                                  <input type="hidden" name="tempOwnerName" value="<%=tempOwnerName%>" />
                                                  <input type="hidden" name="tempRiderName" value="<%=tempRiderName%>" />					  
	    <div class="rowHead">
		Payment Information:</div>
		<div>
			<table width="500" cellpadding="0" cellspacing="0" border="0" class="formLayout">			 
				  <tr>
					<td class="tableLeft"><strong>Total Amount:</strong></td>
					<td class="tableRight"> <strong>$</strong> 
					 
					<input type="text" name="totalAmount" size="10" value="<%=tempAmount%>"  readonly="true" />&nbsp; U.S. Dollars	 </td>				 
				  </tr>
								  
				  <%	if(session.getAttribute("loggedBy")!=null){%>
					<tr>
					<td class="tableLeft">
						<input type="radio" size="10" name="r11" id="r11" value="check"  onclick="switchDiv('chkEncAcm'), showHideRadio('r11','chrgCrdAcm','check'), hideImgDiv('imgId'), checkClear();cardClear();"/> Check enclosed.				 	</td>
					<td class="tableRight">
						<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'), showHideRadio('r11','chkEncAcm','card'), showImgDiv('imgId'), cardClear();checkClear();"/> Please charge my card.					</td>
				  </tr>
				  <input type="hidden" name="hidVal" value="adminLogin"/>	
				  <%}else{%>
				  <tr>
				  <td class="tableLeft" colspan="2">
						<input type="radio" size="10" name="r11" id="r11" value="card" checked="checked"
onclick="switchDiv('chrgCrdAcm'),cardClear();"/> Please charge my card.</td>
<td class="tableRight"></td>
				  </tr>
				  <input type="hidden" name="hidVal" value="userLogin"/>	
				  <%}%>
				  <tr>
				  
					<td colspan="2">
						<div id="chkEncAcm">
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
							<tr>
								<td class="tblMainHead" colspan="3">Check Details:</td>
							</tr>
							<tr>
							  <td colspan="2" class="tableSpan">
							If paying by check , please mail your payment to: 
								<br />
								<br />
								<strong>
								<br />
								Horse / Rider / Owner  Request 
								<br />
								525 Old Waterford Road NW
								<br />
								Leesburg, VA 20176												    </strong><br />
								<br />
								<strong>Note:</strong><span class="styleBoldOne"> Your request will be pending until check is processed.		</span> <br />
						<br /></td>
						  </tr>
							<tr>
							  <td class="tableLeft">Check No:</td>
							  <td class="tableRight"><input type="text" name="checkNumber" id="checkNumber" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
								<%	if(session.getAttribute("loggedBy")!=null){%>
						<input type="hidden" name="chkdate" value="yes">
							<tr>
							  <td class="tableLeft">Check Date:</td>
							  <td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" size="11" maxlength="13"/>

							   <a href="javascript:cal2.popup();">
				   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					  <span class="asterisk">*</span>												  </td>
							</tr>
							<%}
							else{%>
							<input type="hidden" name="chkdate" value="no">
							<tr>
							  <td class="tableLeft">Check Date:</td>
							  <td class="tableRight"><input type="text" name="checkDate" id="checkDate" class="textboxOne" readonly="true" size="11" />

							   <a href="javascript:cal2.popup();">
				   <img src="images/calendar.jpg"  alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a>
					  <span class="asterisk">*</span>												  </td>
							</tr>
<%							}%>	
							<tr>
							  <td class="tableLeft">Bank Name :</td>
							  <td class="tableRight">
							  <input type="text" name="bankName" id="bankName" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
							<tr>
							  <td class="tableLeft">Name on Check:</td>
							  <td class="tableRight">
							  <input type="text" name="nameCheck" id="nameCheck" class="textboxOne" size="16" /> <span class="asterisk">*</span>							  </td>
							</tr>
						</table></div>					</td>
				  </tr>
				  <tr>
					<td colspan="2">
						<div id="chrgCrdAcm">
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
							
							<tr>
								<td class="tblMainHead" colspan="2">Card Details:</td>
							</tr>
							
								<tr>
								<td class="tableLeft">Card Type:</td>
							  <td class="tableRight">
									<select name="ccType" id="ccType" class="selectboxOne" >
									  <option selected="selected" value="">Select One</option>
									  <option value="Visa">Visa</option>
									  <option value="MasterCard">Master Card</option>
									  <option value="American Express">AmEx</option>
									</select>
									<span class="asterisk">*</span>								</td>
							</tr>
							
							<tr>
								<td class="tableLeft">Card No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccNumber" id="ccNumber" class="textboxOne" size="16" />
								  <span class="asterisk">*</span></td>
							</tr>
							<tr>
								<td class="tableLeft">Card CVV No.:</td>
							  <td class="tableRight">
							  <input type="text" name="ccCvvid" id="ccCvvid" class="textboxOne" size="5" />
								  <%	if(session.getAttribute("loggedBy")==null){%>
								   <span class="asterisk">*</span>
								   <%
								   }
								   %>
								   &nbsp;(see details below)								  </td>
							</tr>
						
							<tr>
								<td class="tableLeft">Print Name On Card:</td>
							  <td class="tableRight">
							  <input type="text" name="ccName" id="ccName" class="textboxOne" size="25" />
								  <span class="asterisk">*</span></td>
							</tr>
							<tr>
								<td class="tableLeft">Expiry Date:</td>
							  <td class="tableRight">
									<select name="ccExpMonth" id="ccExpMonth" class="selectboxOne">

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
									<select name="ccExpYear" id="ccExpYear" class="selectboxOne">
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
									<span class="asterisk">*</span>																         </td>
						  </tr>
						  
					 </table>	</div>							</td>
			</tr>
			 <tr>
				<td>&nbsp;</td>
				</tr>
			<tr>
			  <td colspan="2" class="alignCenter">
					<input type="submit" value="Submit Payment" class="gradBtn" />&nbsp;			  </td>
			</tr>
			<tr> 
					<td colspan="2">
						<div id="imgId">
						<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" class="tableInner">
				<tr>
				<td>&nbsp;</td>
				</tr>
					
				<tr>
					<td><center><img src="images/cvv_graphic.jpg" /></center></td></tr>
					</table>
					</div>
					</td>
					</tr>
		</table>
		</div>
			
		
	<div id="spacer">&nbsp;</div>
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
<script language="javascript">
 	var cal2 = new calendar2(document.forms['myform'].elements['checkDate']);
	cal2.year_scroll = true;
	cal2.time_comp = false;
 </script>
</html>
