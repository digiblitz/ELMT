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
<jsp:root version="1.2" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:display="urn:jsptld:http://displaytag.sf.net/el"
 xmlns:c="urn:jsptld:http://java.sun.com/jstl/core">
  <jsp:directive.page contentType="text/html; charset=UTF-8" />
  <jsp:directive.page import="org.displaytag.sample.*" />
  <jsp:include page="../../inc/header.jsp" flush="true" />


 <jsp:scriptlet> request.setAttribute( "test", session.getAttribute("demo"));request.setAttribute("Start",session.getAttribute("StartDate")); request.setAttribute("End",session.getAttribute("EndDate")); </jsp:scriptlet>



<table align="center" bgcolor="#EEEEEE"  >
<tr>

<td valign="middle" align="center" >


      
  <display:table name="test" export="true" id="currentRowObject">
      <display:caption >Human Members - Membership Type Wise For <jsp:expression>session.getAttribute("status") </jsp:expression> Status<br/><br/>From Date:<jsp:scriptlet>out.println(request.getAttribute("Start"));</jsp:scriptlet>  To Date: <jsp:scriptlet>out.println(request.getAttribute("End")); </jsp:scriptlet></display:caption>
    <display:column property="year" title="MemberShip Type"> </display:column>
    <display:column property="count" title="Count"> </display:column>
   <display:setProperty name="export.pdf" value="true" />
  </display:table>

</td>
</tr>
</table>

</jsp:root>

