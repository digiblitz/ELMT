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
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
			<%
			try{
				String amt = (String)request.getAttribute("amount");					
				String membid=(String)session.getAttribute("memberId");
				String membTyp=(String)request.getAttribute("membTyp");
                Date expdate=(Date)session.getAttribute("exp_date");
				
			 String finalDate= "N/A";
			 if(expdate!=null){
				 String temp_date = expdate.toString();
				 String[] split_date = temp_date.split("-");
				 String yr = split_date[0];
				 String month = split_date[1];
				 String day = split_date[2];
				 finalDate = month+"-"+day+"-"+yr;
			 }                               
			 
				Calendar c = Calendar.getInstance();
				int day = c.get(Calendar.DATE);
				int month = c.get(Calendar.MONTH);
				int year = c.get(Calendar.YEAR);
                                month=month+1;
                                
				//String date = year+"-"+month+"-"+day;
				String date = month+"-"+day+"-"+year;
				
				System.out.println("Current Date = "+date);
			   
                String regDat="";
                                if(session.getAttribute("regStatus")!=null)
                                {
                                    regDat=(String)session.getAttribute("regStatus");
                                }
		
			%>
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
					  <tr>
						<td colspan="2" class="tblMainHead">
							<strong></strong>  <span class="styleBoldTwo">Member Registration Confirmation</span></td>
					  </tr>
					  <tr>
						<td colspan="2" class="tblDescrp"> 
							<p>You have successfully made a payment of<strong> $<%=amt%></strong> and registered as a Member. <br />
							    <br />
							    You will receive an email from <strong>info@digiblitz.com</strong> regarding this transaction.<br />
							    <br />
							    
								<strong>No.:</strong> <span class="styleBoldOne"><%=membid%></span> <br />
                                <strong>Member Type:</strong> <span class="styleBoldOne"><%=membTyp%></span> <br />
                                <strong>Expiry Date:</strong> <span class="styleBoldOne"> <%=finalDate%> </span> <br />
                                
                                <%SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
                                  session.setAttribute("exp_date",null);
                                  session.setAttribute("regStatus",null);
                                    																		
                                    //expdate.setDate(expdate.getDate() +1);%>
                                                                      
                                 <strong>Registration Date:</strong> <span class="styleBoldOne"> <%=date%> </span> <br />
                                 
                                <strong>Registration Status:</strong> <span class="styleBoldOne">Active</span> <br />
                                <br />
								<%
								}
								catch(Exception e)
								{
									System.out.print(" jsp catch :"+e);
								}
								%>
											  
						      <input name="button" type="button" class="gradBtn" value=" Back " onclick="location.href='./index.jsp';" />
                                                      <input name="button" type="button" class="gradBtn" value="Print This Page" onclick="window.print();" />
		                  </p>
						</td>
					  </tr>
					  <tr>
						<td>&nbsp;</td>
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



