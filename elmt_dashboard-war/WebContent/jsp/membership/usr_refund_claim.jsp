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
<%@page import="java.text.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAppRefundval.js" type="text/javascript" ></script>
<script src="javascripts/frmSponsReg.js" type="text/javascript" ></script>
<script src="javascripts/MemberRefund.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
<%!
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sd1 = new SimpleDateFormat("MM-dd-yyyy");
	
	String dateFormat(String fieldName){					
		Date clDate = null;
		Calendar cal = Calendar.getInstance();
		GregorianCalendar gc = new GregorianCalendar();
		try{
			clDate = sd.parse(fieldName);
		}catch(Exception e){
			System.out.println("Error While Parsing Date: "+e);
		}
		
		gc.setTime(clDate);
		cal.set(gc.get(Calendar.YEAR),gc.get(Calendar.MONTH),gc.get(Calendar.DAY_OF_MONTH),0,0,0);
		String dispDate = "N/A";
		if(clDate!=null ){
		dispDate = sd1.format(cal.getTime());
		}
		return dispDate;
	}
%>
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong>Membership</strong>:<span class="styleBoldTwo"> Refund Application Form</span> </td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	Please indicate how disperse your approved refund.
Select one or more  <strong>Refund Type</strong> categories below, and enter dollar amounts up to the Balance Amount shown. 

If you choose Check/Credit Card Refund, then amount entered will be refunded based on the original payment method.	</td>
  </tr>
  <tr>
  	<td>
	<%		
		 String[] claimdet=(String[])session.getAttribute("refunddet");
                 Vector refundTyp=new Vector();
                 refundTyp=(Vector)request.getAttribute("refundTyp");
                
                // session.setAttribute("claimdet",claimdet);
	%>
	
	<form name="frmAdvAppRefund" id="frmAdvAppRefund" method="post" action="./UsrRefClaim.do" onSubmit= "return Dispall();">
			
        <table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
          <tr> 
            <td colspan="2" class="tblRowHead">Apply Refund:</td>
          </tr>
		   <tr> 
            <td class="tableLeft">Date Of Approval:</td>
            <td class="tableRight"><%=dateFormat(claimdet[0])%></td>
          </tr>
          <tr> 
            <td class="tableLeft">Balance Amount:</td>
            <th class="tableRight">$<%=claimdet[1]%></th>
          </tr>
		  <tr> 
            <td class="tableLeft">Requested Amount:</td>
            <th class="tableRight">$<%=claimdet[2]%></th>
          </tr>
          <tr> 
            <td class="tableLeft">Approved Amount: </td>
            <td class="tableRight"><span class="styleBoldOne">$<%=claimdet[4]%></span></td>
          <input type="hidden" name="ridId" value="<%=claimdet[5]%>"/>
            
            <input type="hidden" name="cbxct" value="<%=refundTyp.size()%>"/>

             <input name="approveamt" type="hidden" value="<%=claimdet[4]%>" />
				
          </tr>
        
			<tr> 
					  <td class="tableLeftTxtArea"> Refund Type: </td>
				<td class="tableRight">
         <table width="283" border="0" cellpadding="0" align="left" cellspacing="0">
		<% Enumeration en=refundTyp.elements();
                                int chksfx=0;
				while(en.hasMoreElements()) 
			{
				 String[] refundDet=(String[])en.nextElement();
				
				  String cbx="refundcb";
				  String tb="refundtb";
				  String cbxname=cbx+Integer.toString(chksfx);
				  String tbname=tb+Integer.toString(chksfx);
			%>
			
			  <tr> 
				  <td width="173">
					<input type="checkbox" name="<%=cbxname%>" value="<%=refundDet[0]%>" />
					<%=refundDet[1]%>
				  </td>
				  <td width="110">	
					<strong>$</strong>
					<input type="text" name="<%=tbname%>" class="textboxOne" size="10" />
				  </td>
			 </tr>
			
				 <%chksfx++;
			}%>	</table>
			  <input name="balamt" type="hidden" value="<%=claimdet[1]%>" />
          <tr> 
              <td class="tableLeft">&nbsp;</td>
              <td class="tableRight"><input name="subsub" type="submit" class="gradBtn" value="Submit" /></td>
          </tr>
        </table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
