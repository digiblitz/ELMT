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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title><%=(String)session.getAttribute("title")%></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <script src="javascripts/basic.js" type="text/javascript" ></script>
    <script src="javascripts/calendar2.js" type="text/javascript"></script>
	 <script src="javascripts/frmAccTransaction.js" type="text/javascript"></script>
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
				
				
					<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
						<span class="styleBoldTwo">Transaction Details. </span></td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp">&nbsp;							</td>
					  </tr>
					 
					 <tr>
					   <td>
					   <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                         <form name="myform" id="myform" method="post" action="acctrans.do" onsubmit="return onValidate();" >
                           <input type="hidden" name="process" value="getTrans" />
                            <tr >
					 <%
					   String transactDate = (String)request.getAttribute("transDate");
					   SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
					   
					     if(transactDate==null){ 
						  java.sql.Date tempDate = new java.sql.Date((new java.util.Date()).getTime());
			              transactDate = String.valueOf(tempDate).substring(5,7)+"/"+String.valueOf(tempDate).substring(8,10)+"/"+String.valueOf(tempDate).substring(0,4); 
						  }%>
					<td  class=" ">Transaction Date:</td>
					<td  class=" " colspan="4">
					<input type="text" name="transDate" id="transDate" size="16" <%if(transactDate!=null)%> value="<%=transactDate%>"  />
					<span class="formX">
	          
	          <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0"></a> 	</span>
					</td>
					</tr>
					<tr >
					 
					<td  class="tableLeft">&nbsp; </td>
					<td  class="tableRight" colspan="4">
					<input type="submit" value="Submit" class="gradBtn" />&nbsp; 
					 </td>
					</tr>
                         </form>
					    <%
ArrayList arrList = (ArrayList)request.getAttribute("objTransDetails");
System.out.print("arrList size is:" + arrList);
if(arrList!=null && arrList.size()!=0){
System.out.print("dasfdasf" + arrList);
%>
<div class="rowExpand"><strong>Transaction Details:</strong></div>
 
</table>
 <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
                               
                                    <tr>
                                        <td width="60"  height="27" class="tblRowHeadTwo">Acc
                                          Type </td>
                                        <td width="75"  class="tblRowHeadTwo">Account_No</td>
                                        <td  width="171" class="tblRowHeadTwo">Description</td>
                                        <td  width="94" class="tblRowHead">Trans Date </td>
                                        <td width="94"  class="tblRowHead">Amount($)</td>
                                    </tr>
<%
 
							Iterator itr = 	arrList.iterator();
							while(itr.hasNext()) {				   
								HLCAccTransactionVO objAccVO = (HLCAccTransactionVO) itr.next();
								String accNo = objAccVO.getAccount_no();
								String  accType= objAccVO.getAccount_type();
								String accSubNo = objAccVO.getSub_account_no();//null
								String accDesc = objAccVO.getDescription();//null
								String accClass = objAccVO.getClass_Typ();//null
								int accQty = objAccVO.getQuantity();//null
								float accAmt = objAccVO.getAmount();//null
								Date transDate = objAccVO.getTransaction_date();
								Date syncDate = objAccVO.getSync_date();
								String userId = objAccVO.getUser_id();
								String ip = objAccVO.getIp_address();
								boolean syncStatus  = objAccVO.isSync_status(); 
								boolean activestatus  = objAccVO.isActive_status(); 
								String tmpAccNo = "";
								if(accSubNo!=null ){
								  tmpAccNo  = accNo + "-" +accSubNo; 
								}
								else{
								 tmpAccNo = accNo;
								}
								
								

%>
                                   <form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="QBXMLRequest.do" >
								   <
									<input type="hidden" name="transDate" value="<%=transactDate%>">
                                        
                                        <!-- For the Repayment Option  -->
                                        <tr>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=accType%></td>								
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=tmpAccNo%></td>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=accDesc%></td>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=transDate%></td>
                                            <td bgcolor="#E3E1D2" class="alignCenter"><%=accAmt%></td>
                                        </tr>
										
					
                                  
								<%
								
			}
			%>
			    <tr>					 
					<td  colspan="5" class="alignCenter">
					<input type="submit" value="Synchronize" class="gradBtn" />&nbsp; 
					 </td>
					</tr>
					  </form>
			<%
			 }
			 else{
			%>	
			<td  colspan="5" bgcolor="#E3E1D2" class="alignCenter">
					<strong>No Records Are found</strong>&nbsp; 
					 </td>
			<%
			}
			%>
			   
                          
					      
                       </table></td>
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
	var cal1 = new calendar2(document.forms['myform'].elements['transDate']);
	 cal1.year_scroll = true;
	 cal1.time_comp = false;
</script>

</html>
