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

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.hlcmeeting.util.HLCEventRegPendingVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
</head>
<%! 
	DecimalFormat result  = new DecimalFormat("0.00");
	String amtFormat(String amtVal){
	String amountValue = "0.00";
		if(amtVal!=null && amtVal.trim().length()!=0){
		System.out.print(Double.parseDouble(amtVal));
		amountValue = result.format(Double.parseDouble(amtVal));
		}
	return amountValue;
}

%>
<%! 
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
String  nullCheck(String fieldName){
	String retValue = "N/A";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
<%! String dateCheck(Date fieldName){
	String detValue = "N/A";
		if(fieldName!=null){
		 detValue = sdf.format(fieldName);
		}
	return detValue;
}

%>
<%! float floatCheck(float fieldName){
	float floateValue = 0;
		if(fieldName!=0.0){
		 floateValue = fieldName;
		}
	return floateValue;
}
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<td width="260" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
	
				
		<div class="cmmnForm">
		<div class="colspan">
			<strong>Meeting: </strong><span class="styleBoldTwo">Event Registration Payment Failed Details </span>		</div>

		    <div id="parta" class="formPart">
 	 	</div>
		<div class="rowHead">Event Registration Details:</div>
						
        <%
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 	HLCEventRegPendingVO evePendDets = (HLCEventRegPendingVO) request.getAttribute("objPendingVO");
	String eveId=evePendDets.getEventId();
                String orgId=evePendDets.getOrgId();
                String compName=evePendDets.getCompName();                                           
                String compLocat=evePendDets.getCompLocation();
                String compCity=evePendDets.getCompCity();
                String compStat=evePendDets.getCompState();
                String compCont=evePendDets.getCompCountry();
                String compZip=evePendDets.getCompZip();
                String compAr=evePendDets.getCompArea();
                String payId=evePendDets.getPaymentId();
                String ccName=evePendDets.getCcName();
                String ccTyp=evePendDets.getCcType();
                String ccNumb=evePendDets.getCcNumber();
                int expMonth=evePendDets.getCc_exp_Month();
                int expYear=evePendDets.getCc_exp_Year();
                int ccVVid=evePendDets.getCc_CvvId();             
                float totAmount=evePendDets.getAmt();
                Date payDt=evePendDets.getPaymentDate();
                String payStat=evePendDets.getPaymentStatus();              
                String ssTxnId=evePendDets.getSslTxnId();
                String ssEmail=evePendDets.getSslEmail();                
                String statName=evePendDets.getStatName();
                String areaName=evePendDets.getAreaName();
				String sslResultMsg=evePendDets.getSslResultMessage();

	%>
				<div class="row">
					<span class="label">Event Id :</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(eveId)%></span></span>
			  	</div>
				<div class="row">
					<span class="label">Event Title :</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(compName)%></span></span>
			  	</div>
			    						
				<div class="row">
					<span class="label">Organizer Id: </span>
					<span class="formX"><strong><%=nullCheck(orgId)%></strong></span>
			  	</div>
				<div class="row">
					<span class="label">City:</span>
					<span class="formX"><%=nullCheck(compCity)%></span>
				</div>
				<div class="row">
					<span class="label">State:</span>
					<span class="formX"><span class="styleBoldOne"><%=nullCheck(statName)%></span></span>
				</div>	
				<div class="row">
					<span class="label">Zipcode:</span>
					<span class="formX"><%=nullCheck(compZip)%></span>
				</div>	
				<div class="row">
					<span class="label">Country: </span>
					<span class="formX"><strong><%=nullCheck(compCont)%></strong></span>
				</div>
				<div class="row">
					<span class="label">Area:</span>
					<span class="formX"><%=nullCheck(areaName)%></span>
				</div>
									
				<div class="row">
				
				<div class="rowHead">Pending Payment  Details:</div>
								
					<div class="row">
							<span class="label">Credit Card Type:</span>
							<span class="formX"><%=nullCheck(ccTyp)%></span>
					</div>
					<div class="row">
							<span class="label">Name on Credit Card :</span>
							<span class="formX"><%=nullCheck(ccName)%></span>
					</div>
					<div class="row">
							<span class="label">Card Status:</span>
							<span class="formX"><%=nullCheck(sslResultMsg)%></span>
					</div>						
					<div class="row">
							<span class="label">Transaction Id:</span>
							<span class="formX"><%=nullCheck(ssTxnId)%></span>
					</div>
					<div class="row">
							<span class="label">Pending Amount ($):</span>
							<span class="formX"><%=totAmount%></span>
					</div>												
  					<form name="myForm" id="myForm" action="eventPendPayment.do" method="post">
					<input type="hidden" name="cmd" value="initPayment"  />
					<input type="hidden" name="eveId" value="<%=eveId%>" />
					<input type="hidden" name="pendAmount" value="<%=totAmount%>" />
					<input type="hidden" name="tempPayId" value="<%=payId%>" />									
					<div class="row">
						<span class="label">&nbsp;</span>
						<span class="formX"> <input type="submit" value="Pay Amount" name="back" class="gradBtn" /></span>
			  		</div>
					</form></tr>
						 
						 
		 
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
