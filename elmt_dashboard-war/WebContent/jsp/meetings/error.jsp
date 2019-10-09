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
<%@ page language="java" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> --> 
--></head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="tableCommonBg">
	<!-- CENTER TABLE STARTS HERE -->	
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
		  <tr>
			<td colspan="2"  class="deptTablePad"><table width="100%"  border="0" align="center" cellpadding="3" cellspacing="2" class="sat-middle">
              <tr bgcolor="#F0F2F7">
                <td height="25" class="sat-bigText3" ><div align="left" class="sat-SubHeadings3">Sorry for the Inconvinence ! </div></td>
              </tr>
              <tr>
                <td ><div class="sat-bigText3">Exception Class:</div>
                    <div class="sat-asterix"><%=exception.getClass()%></div></td>
              </tr>
              <tr>
                <td ><div class="sat-bigText3">Message:</div>
                    <div class="sat-asterix"><%=exception.getMessage()%></div></td>
              </tr>
              <tr>
                <td ><div class="sat-asterix">
                    <input name="redir" type="button" class="gradBtn" style="cursor:hand;" onclick="window.location.href='index.jsp';" value="Back To Home page"/>
                </div></td>
              </tr>
            </table></td>
		  </tr>
	  </table>
	<!-- CENTER TABLE ENDS HERE -->
	</td>
  </tr>
</table>
 

</body>
</html>
