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
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
</head>
<%
String email = (String)request.getAttribute("email");
String cardNo = (String)request.getAttribute("cardNo");
String expDate = (String)request.getAttribute("expDate");
String amount = (String)request.getAttribute("amount");
String chkDigit = (String)request.getAttribute("chkDigit");
System.out.println("Email:" + email);
System.out.println("cardNo:" + cardNo);
System.out.println("expDate:" + expDate);
System.out.println("amount:" + amount);
System.out.println("chkDigit:" + chkDigit);

%>
<script language="javascript">
function norightmenu(){
document.oncontextmenu = function(){return false}
}
</script>
<body onload="norightmenu();frmPayment.submit();">
<div class="loading" align="left" style="padding:10px;">Processing......Please allow transaction to complete. <br />
Clicking <strong>REFRESH or BACK </strong> in your browser could result in multiple payment requests being processed!</div>
 
<form name= "frmPayment" action="https://www.viaKLIX.com/process.asp" method="POST">
<input type="hidden" name="ssl_merchant_id" value="408871">
<input type="hidden" name="ssl_user_id" value="website">
<input type="hidden" name="ssl_pin" value="1JBJ01">
<input type="hidden" name="ssl_show_form" value="false">
<input type="hidden" name="ssl_test_mode" value="false">
<input type="hidden" name="ssl_email" value="<%=email%>">
<input type="hidden" name="ssl_card_number" value="<%=cardNo%>">
<input type="hidden" name="ssl_exp_date" value="<%=expDate%>">
<input type="hidden" name="ssl_result_format" value="HTML">
<input type="hidden" name="ssl_receipt_decl_method" value="REDG">
<input type="hidden" name="ssl_receipt_decl_get_url" value="https://dashboard.useventing.com:443/dashboard/noveRegEdit.do">
<input type="hidden" name="ssl_receipt_apprvl_method" value="REDG">
<input type="hidden" name="ssl_receipt_apprvl_get_url" value="https://dashboard.useventing.com:443/dashboard/noveRegEdit.do">
<input type="hidden" name="ssl_receipt_link_text" value="Continue">
<input type="hidden" name="ssl_amount" value="<%=amount%>">
<input type="hidden" name="ssl_cvv2" value="present"> <!--CVV2 Indicator --> 
<input type="hidden" name="ssl_cvv2cvc2" value="<%=chkDigit%>">
<!--
<input type="hidden" name="ssl_avs_address" value="123 Main St."> <!-AVS Postal Address -->
<!-- <input type="hidden" name="ssl_avs_zip" value="01234"> <!-AVS ZIP Code -->
</form>
</body>
</html>
