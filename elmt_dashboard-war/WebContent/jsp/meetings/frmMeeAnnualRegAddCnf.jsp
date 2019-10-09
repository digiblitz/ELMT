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
<%@ page import="com.hlcmee.ann.*"%>
<%@ page import="com.hlcmeeting.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.math.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
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
				<td width="260" class="menuTablePad">
					<!-- LEFT MENU STARTS HERE -->
					<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
					<!-- LEFT MENU ENDS HERE -->
				</td>
				<td width="500" class="subDeptTablePad">
					<!-- CONTENTS START HERE -->
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
							  <tr>
								<td colspan="2" class="tblMainHead"><span class="styleBoldTwo">Annual Meeting  &amp; Convention Registration </span></td>
							  </tr>
							  <tr>
								<td colspan="2" class="alignCenter">
								
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
											<%
												double totalAmount = 0.00d;
												HLCAnnualMeetingStore regList = (HLCAnnualMeetingStore)session.getAttribute("regList");
												//out.print(regList);
												if(regList!=null && regList.getSize()!=0){
												//out.print("No records are found");
											%>
										<tr>
										  <td height="25" colspan="6"><strong>&nbsp;&nbsp;Note:</strong><span> If you want to <strong>Edit</strong> the user information, please delete the entry and re-enter.</span></td>
										</tr>
										<tr>
										  <td colspan="6" class="tblRowHead">Completed Registrations for the Annual Meeting &amp; Convention</td>
										</tr>
										<tr>
										  <td colspan="6" valign="top" style="padding-bottom:8px;">
										   <%
																			Enumeration e = regList.getEnumeration();
																			while(e.hasMoreElements()){
																				HLCAnnualUserVO tempObjAnnualUser = (HLCAnnualUserVO)e.nextElement();
																				String delUserId = tempObjAnnualUser.getUserId();
																				String firstName = tempObjAnnualUser.getFirstName();
																				String lastName = tempObjAnnualUser.getLastName();
																				String badgeName = tempObjAnnualUser.getBadgeInfo();
																				double regAmount = tempObjAnnualUser.getRegAmount();
																				double otherAmount = tempObjAnnualUser.getOtherActAmount();
																				totalAmount +=(regAmount+otherAmount);
																				ArrayList priceList = tempObjAnnualUser.getPriceList();
																				
																				String tempRegAmt = String.valueOf(regAmount);
																				String tempOtherAmount = String.valueOf(otherAmount);
									
																				String memberType = tempObjAnnualUser.getMemberTypeName();
																				


																				BigDecimal rAmt = new BigDecimal(Float.parseFloat(tempRegAmt));
																				rAmt = rAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
																				
																				BigDecimal oAmt = new BigDecimal(Float.parseFloat(tempOtherAmount));
																				oAmt = oAmt.setScale(2, BigDecimal.ROUND_HALF_UP);
																		  %>
																		  
										
											  <form name="frmAnnual" id="frmAnnual" method="post" action="annConRegInsert.do">
												<input type="hidden" name="annProcess" value="remove"/>
												<input type="hidden" name="deluserId" value="<%=delUserId%>"/>
											    <table width="100%" cellpadding="0" cellspacing="1" border="0" style="padding:0px; border:3px solid #E3E3E3;">
                                                  <tr>
                                                    <td colspan="3" class="listCellBgTwo"><strong>Full Name: </strong><span class="styleBoldOne"><%=firstName%>&nbsp;<%=lastName%></span></td>
                                                    <td width="16%" class="listCellBgTwo">
                                                      <input name="Submit2" type="submit" class="oneBtn" value=" Delete " />
                                                    </td>
                                                  </tr>
												  <tr>
                                                    <td height="25" colspan="4" valign="middle" class="tblRowHeadThree">&nbsp;Registration Detail</td>
                                                  </tr>
                                                  <tr>
                                                    <!--<td width="6%" class="tblMainHead">&nbsp;</td>-->
                                                    <td width="45%" class="tblMainHead">Badge Name</td>
                                                    <td width="31%" class="tblMainHead">Registered Amount	($) </td>
                                                    <td colspan="2" class="tblMainHead">Member Type </td>
                                                  </tr>
                                                  <tr>
                                                    <!--<td class="listCellBg"><input type="checkbox" name="checkbox8" value="checkbox" /></td>-->
                                                    <td width="45%" class="listCellBg" style="border-bottom:3px solid #E3E3E3;"><%=badgeName%></td>
                                                    <td width="31%" class="listCellBg" style="border-bottom:3px solid #E3E3E3;"><%=rAmt%></td>
                                                    <td colspan="2" class="listCellBg" style="border-bottom:3px solid #E3E3E3;"><%=memberType%></td>
                                                  </tr>
                                                  <tr>
                                                    <td height="25" colspan="4" valign="middle" class="tblRowHeadThree">&nbsp;Other activities they opted for </td>
                                                  </tr>
                                                  <tr>
                                                    <td colspan="3" class="tblMainHead">Activity Name </td>
                                                    <td class="tblMainHead">Amount ($) </td>
                                                  </tr>
												  <%
												  if(priceList!=null && priceList.size()!=0){
													Iterator itPriceList = priceList.iterator();
													while(itPriceList.hasNext()){
													String[] strPrice = (String [])itPriceList.next();
													 String priceId = strPrice[0];
                                            		String priceAmount = strPrice[1];
													String actName = strPrice[2];
													 if(priceId!=null && priceId.trim().length()!=0){
												  %>
                                                  <tr>
                                                    <td colspan="3" class="listCellBg"><%=actName%></td>
                                                    <td class="listCellBg"><%=priceAmount%></td>
                                                  </tr>
												  <%
												  	}
												  	}
													%>
													 <tr>
                                                    <td colspan="3" class="listCellBg"><span class="styleBoldTwo">Total Other Activity Amount</span></td>
                                                    <td class="listCellBg"><%=oAmt%></td>
                                                  </tr>
												  <%
													}
													else{
													%>
													 <tr>
                                                    <td colspan="4" class="listCellBg">No other activities are registered</td>
                                                  </tr>
													<%
													}
													%>
                                                </table>
											  </form>
											 <%
											  }
												}
												 %>										  </td>
										</tr>
									</table>
									
								
								</td>
							  </tr>
							  
						
							<tr>
							  <td colspan="2" class="tblDescrp">
								  <form name="frmAnnual" id="frmAnnual" method="post" action="annConRegInsert.do">
									<input type="hidden" name="annProcess" value="payment" />
									  
									   You have successfully <strong>registered</strong> a participant(s) for the Annual Meeting &amp; Convention.
										Would you like to register for another person? <br />
													<br />
													<input name="annButName" type="button" class="gradBtn" value="Yes, Register Another" onClick="javascript:location.href='annualConRegAction.do?annProcess=initAnnual'" />
													<%
													if(regList.getSize()!=0) {
												%>
													<input name="annButName" type="submit" class="gradBtn" value="No, Proceed to Payment"/>
													<%
													}
												%>			
									</form>
							  </td>
							</tr>
						
						  
						  
						  <tr>
							<td height="20">&nbsp;
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
</html>