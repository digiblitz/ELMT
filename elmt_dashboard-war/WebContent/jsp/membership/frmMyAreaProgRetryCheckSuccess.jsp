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
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="membership.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /></head> 

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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
                    
                    <%
                       
                       String actStat=(String)request.getAttribute("actStat");
                       String regStatus="";
                       
                       if(actStat!=null)
                       {
                           System.out.println(" request.getAttribute(actStat) in JSP :"+actStat);
                           regStatus="Active";
                       }
                       else
                       {
                           regStatus="Pending until payment processed";
                       }
                       
                    %>
                    
                    
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
		USEA : <span class="styleBoldTwo">Retry Area Member Program Confirmation	</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp" valign="top" style="padding:8px;">
	<form name="confirmation" method="post" action="MemberLogin.do" />
	<%
		String redirurl = "index.jsp";
		
		String arName = (String)request.getAttribute("areaName");					
		String progName=(String)request.getAttribute("areaRidProg");
		String areaRidProgDesc=(String)request.getAttribute("areaRidProgDesc");
		String progYr=(String)request.getAttribute("progYear"); 
		String amt=(String)request.getAttribute("amount");           
  
	%>
	
      You have successfully joined your Area Rider Program and the details are as follows:<br />
      <br />
      <strong>Area Name</strong> <span class="styleBoldOne"><%=arName%></span> <br />
      <strong>Area Rider Program Name</strong> <span class="styleBoldOne"> <%=progName%> (<%=areaRidProgDesc%>)</span> <br />
      <strong>Program Year</strong> <span class="styleBoldOne"> <%=progYr%> </span> <br />
      <strong>Registration Status</strong> <span class="styleBoldOne"><%=regStatus%></span><br />
      <br />
      
	  If paying by check (payable to &quot;USEA&quot;), please mail your payment of <strong> $ <%=amt%>(U.S. Dollars) </strong> to:
	  <br />
	  <br />
	  
      <strong>USEA<br />
      Membership Registration<br />
      525 Old Waterford Road NW<br />
      Leesburg, VA 20176<br />
      <br />
	  </strong>Please include member's name and USEA No. on check!
      <br />
      <br />
   
      <input name="button" type="button" class="gradBtn" value="Back" onclick="location.href='<%=redirurl%>'" />
      <input name="button" type="button" class="gradBtn" value="Print This Page" onclick="window.print();" />
      <br />
	  </form>
	
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



