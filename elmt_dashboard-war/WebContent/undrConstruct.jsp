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
<jsp:directive.page import="java.util.*"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        
        <title>Enterprise Integration Dashboard</title>
        
        <script src="javascripts/basic.js" type="text/javascript" ></script>
        <script src="javascripts/Login.js" type="text/javascript" ></script>
	

        <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> 
		
		
		<%
		
		String sessLogId = (String) session.getAttribute("sessionId");
		
		//System.out.println("sessLogId"+sessLogId);
		
		if(sessLogId!=null){
		 session.removeAttribute("sessionId");
            session.removeAttribute("userId");
            session.removeAttribute("userCode");
            session.removeAttribute("firstName");
            session.removeAttribute("userTypeName");
            session.removeAttribute("emailId");
            session.removeAttribute("memberId");
		response.sendRedirect("login.jsp");	
		}
		
		
		%>
        <script language="javascript">
            function focus_login()
            {
                document.form1.textfield.focus();
            }
			
			function MM_openBrWindow(theURL,winName,features) { 
  window.open(theURL,winName,features);
}
        </script>
    </head>
    
    <body onLoad="focus_login();">
        <table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
		
            <tr>
                <td class="tableCommonBg">
                    <!-- HEADER STARTS HERE -->
                 <%@ include file = "include/login_header.jsp"%> 
                    <!-- HEADER ENDS HERE -->
                </td>
            </tr>
            <tr>
                <td class="tableCommonBg">
                    <!-- CENTER TABLE STARTS HERE -->		
				
			
		  
                    <table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="tableCommonBg">
                        <tr>
                            <td colspan="2" class="tableCommonBg">
                                <!-- Welcome Tab Starts Here -->	
                              	<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				
				  <tr>
					<td colspan="2" class="tableCommonBg" height="125"><br />
						
				
					<span class="tableCommonBg"><strong>This page is under construction. Sorry for the inconvinience.</strong></span>
					</td>
				  </tr>
				  </table>
				  
                                <!-- Welcome Tab Ends Here -->
                            </td>
                        </tr>
                        
                    </table>
                    <!-- CENTER TABLE ENDS HERE -->
                </td>
            </tr>
            <tr>
                <td class="footerBg">
                    <!-- FOOTER STARTS HERE -->
                         <%@ include file = "include/footer.jsp" %>
                    <!-- FOOTER ENDS HERE -->
					</td>
				</tr>	
					
                </td>
            </tr>
        </table>
         
        
    </body>
</html>
