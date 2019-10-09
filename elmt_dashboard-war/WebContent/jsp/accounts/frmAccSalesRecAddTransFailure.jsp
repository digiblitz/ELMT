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
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 
<head>
 <title><%=(String)session.getAttribute("title")%></title>

	<%
	String errMsg = (String)request.getAttribute("errMsg");
	if(errMsg==null || errMsg.trim().length()==0){
	%>
		<meta http-equiv="refresh" content="25">	
	<%
	}
%>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <script src="javascripts/basic.js" type="text/javascript" ></script>
   
    <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<%
SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

Date transDate = (Date)request.getAttribute("transDate");
String paymentMode = (String) request.getAttribute("paymentMode");
if(paymentMode==null) paymentMode = "";

if(transDate==null){
	request.setAttribute("transDate", null);
}
else {
	request.setAttribute("transDate", formatter.format(transDate));
}

request.setAttribute("paymentMode", paymentMode);

%>

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
						<span class="styleBoldTwo">Transaction Status Details. </span></td>
					  </tr>
						<form name="frmViewHorseReg" id="frmViewHorseReg" method="post" class="formcss" action="" >
							 <%
								//String errMsg = (String)request.getAttribute("errMsg");
								if(errMsg!=null && errMsg.trim().length()!=0){
							 %>
								<!-- For the Repayment Option  -->
								<tr>
									<td  colspan="5" class="labelOne"><%=errMsg%></td>								
								</tr>
								 <tr>
					<td  class="alignCenter" colspan="5">
<input type="button" value="Try Again" class="gradBtn" onclick="location.href('acctrans.do?process=initMemberTrans');" />
&nbsp; 
					 </td>
					</tr>
								<%
								}
								else{
								%>
								<tr>
									<td  colspan="5" class="alignCenter">
									  <div class="alignCenter">
									    <!--Please wait QB Synchronizing is going on..
	<input type="button" value="Refresh" class="gradBtn" onclick="location.href('QBXMLResponse.do?process=QBSARes');" />
	-->
									    
									    <img src="images/qbsync.gif"  alt="Dashboard - QuickBooks Synchronization" />
							          </div></td>
								</tr>
							<%
							}
							%>
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
