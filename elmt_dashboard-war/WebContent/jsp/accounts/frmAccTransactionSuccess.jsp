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
<%@ page import="com.hlcaccounts.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="java.text.*"%>
<%! public String emptyCheck(String val){
	if(val==null) val = "-";
	return val;
}%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title><%=(String)session.getAttribute("title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <script src="javascripts/basic.js" type="text/javascript" ></script>
   
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
				
				
					 
 <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                               
                                    <tr>
						<td colspan="5" class="tblMainHead">
						<span class="styleBoldTwo">SalesReceipt Transaction Details. </span></td>
					  </tr>
                                    <form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="" >
							 
                                        <%
										String errMsg = (String)request.getAttribute("errMsg");
										if(errMsg!=null&&errMsg.trim().length()!=0){
										%>
                                        <!-- For the Repayment Option  -->
                                        <tr>
                                            <td  colspan="5" class="labelOne">Error Message:<%=errMsg%></td>								
                                             
                                        </tr>
						
						<%
						}
						HLCSalesReceiptResponseVO salesResVO = (HLCSalesReceiptResponseVO)request.getAttribute("salesReceiptAddDetails");
						//System.out.println("Object Value:" + salesResVO);
							if(salesResVO!=null){
								%>
								 <tr>
                                            <td  colspan="5" class="labelOne">You have SuccessFully Synchronized data with Quick Books</td>								
                                             
                                        </tr>
								<tr>
								<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
							<tr>
							<td colspan="2" class="tblRowHead">SalesReceipt Information</td>
						  <tr>
							<td class="tableLeft"> Transaction NO:</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getTxnNumber())%>&nbsp;</td>
						  </tr>
						    <tr>
							<td class="tableLeft"> Transaction Date:</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getTxnDate())%>&nbsp;</td>
						  </tr>
						  
						    <tr>
							<td class="tableLeft">  Sale NO:</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getSalRefNumber())%>&nbsp;</td>
						  </tr>
						  
						   <tr>
							<td class="tableLeft"> Payment Mode:</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getPaymentMethodFullName())%>&nbsp;</td>
						  </tr>
						  
						  <tr>
							<td class="tableLeft"> Total Amount:</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getTotalAmount())%>&nbsp;</td>
						  </tr>
						  <tr>
							<td colspan="2" class="tblRowHead">Customer Information</td>
						  <tr>
							<td class="tableLeft"> Customer Name:</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getCustomerRefFullName())%>&nbsp;</td>
						  </tr>						  
						  <tr> 
							<td class="tableLeft">Customer Address :</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getBillAddressAddr1())%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Customer City :</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getBillAddressCity())%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Customer State :</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getBillAddressState())%>&nbsp;</td>
						  </tr>
						    <tr> 
							<td class="tableLeft">Customer Zipcode :</td>
							<td class="tableRight"><%=emptyCheck(salesResVO.getBillAddressPostalCode())%>&nbsp;</td>
						  </tr>
						   <tr>
							<td colspan="2" class="tblRowHead">Items Information</td>
							</tr>
							<tr>
							<td colspan="2">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
								<tr>
								<td height="27" class="tblRowHead">Item NO</td>
								<td height="27" class="tblRowHead">Item Description</td>
								<td height="27" class="tblRowHead">Item Class</td>
								<td height="27" class="tblRowHead">Item Amount</td>
								</tr>
								<%
									//String salesResRet[] = salesResVO.getSalesReceiptLineRet();
									//String salesResRet[] = {txtLineId, itemRefName, itemDesc, itemAmount,  itemClassName};
									
                                   // objSalesResVO.setSalesReceiptLineRet(salesResRet);
   									String txtLineId = "";
                                    String itemRefName = "";
                                    String itemDesc = "";
                                    String itemAmount = "";
                                    String itemClassName = "";
									
									
										ArrayList arrSalesResRet = salesResVO.getSalesReceiptLineRet();
										if(arrSalesResRet!=null && arrSalesResRet.size()!=0){
											Iterator itSalRes = arrSalesResRet.iterator();
											while(itSalRes.hasNext()){
												String salesResRet[] = (String[])itSalRes.next();
												   if(salesResRet.length!=0){
														itemRefName = salesResRet[1];
														itemDesc = salesResRet[2];
														itemAmount = salesResRet[3];
														itemClassName = salesResRet[4];
													}
												%>
												<tr>
												<td height="27" class="tableRight"><%=itemRefName%></td>
												<td height="27" class="tableRight"><%=itemDesc%></td>
												<td height="27" class="tableRight"><%=itemClassName%></td>
												<td height="27" class="tableRight"><%=emptyCheck(itemAmount)%></td>
												</tr>
												<%
												}
										}
										else{
										%>
										<tr>
												<td height="27" colspan="4" class="tableRight">Items Are Not Available.</td>
										</tr>
										<%
										}
								%>
								</table>
						  	</td>
						  </tr>
							
							 </table>
								</td>
								</tr>
								<%
								}
								%>				
					 <tr>
					<td  class="alignCenter" colspan="5">
					<input type="button" value="Back To Listing" class="gradBtn" onclick="location.href('acctrans.do?process=initMemberTrans');" />&nbsp; 
					 </td>
					</tr>
                                
					  </form>
			 
                          
					      
                       </table></td>
					</tr>  
					</table>
					
					<!-- CONTENTS END HERE -->		
			 
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
