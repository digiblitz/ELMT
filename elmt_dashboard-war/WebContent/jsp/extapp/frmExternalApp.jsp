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

<%@ page import="java.io.*"%>

<%@ page import="java.util.Date"%>
<%@ page import="java.sql.*"%>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<script src="javascripts/basic.js" type="text/javascript" ></script>
 <script src="http://code.jquery.com/jquery-latest.js"></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<link href="ttip2.css" rel="stylesheet" type="text/css"/>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<script language="javascript">

 $(document).ready(     function() { 
	   var url1=document.getElementById("url1").value;
		/*https://98.172.19.186:8443/partymgr/control/main
		https://98.172.19.186:8443/projectmgr/control/main
		https://98.172.19.186:8443/myportal/control/main
		https://98.172.19.186:8443/finance/control/main
		https://98.172.19.186:8443/humanres/control/main
		var url1="https://98.172.19.186:8443/projectmgr/control/login?USERNAME=:1&PASSWORD=:2";*/
		//alert(url1);
		 if(url1.indexOf(":1")!==-1){
		
        var username=document.getElementById("username").value;
        var password=document.getElementById("userpassword").value;
       url1= url1.replace(":1",username);
       url1 =url1.replace(":2",password);
      
       url=encodeURI(url1);
	 
	    //document.getElementById("ifrm").height = ($(window).height() * 2);
		//document.documentElement.style.overflow = 'hidden';
		document.getElementById("ifrm").height = "550";
        document.getElementById("ifrm").src=url;
		
        //document.getElementById("ifrm").height = document.frames[ifr].document.body.scrollHeight;
       }
        else{
        	//  document.getElementById("ifrm").height = ($(window).height() * 2);
			//alert($(window).height());
			document.documentElement.style.overflow = 'hidden';
        	 url=encodeURI(url1);
             document.getElementById("ifrm").src=url;
        }
     
       
    }); 

</script>
</head>
<%String url1=(String)request.getAttribute("url");
//String userName1=(String)session.getAttribute("userName");
//String userPassword1=(String)session.getAttribute("userPassword");
String perName=request.getParameter("app");

System.out.println("url1=================>"+url1);
%>


<body>



<table width="1024" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
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
		<%@ include file = "../../include/infobar.jsp"  %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">
		

<input type="hidden" value=<%=userName %> id="username"/>
<input type="hidden" value=<%=userPassword %> id="userpassword"/>	
<input type="hidden" value=<%=url1%> id="url1"/>

<!-- %if(perName!=null && perName.equalsIgnoreCase("FM")){%-->
	<!-- iframe src="https://localhost:7443/myportal/control/login?USERNAME=<%=session.getAttribute("userName")%>&PASSWORD=<%=session.getAttribute("userPassword")%>" height="300" width="1024" id="ifrm" name="ifrm" frameborder="0">
</iframe-->
	<!-- %
				}else{%-->

<iframe src="" height="300" width="1024" id="ifrm" name="ifrm" frameborder="0">
</iframe>
<!-- %} %-->

	</td>
  </tr>
  
 

  <%if(perName.equalsIgnoreCase("AnalyzeBP")){%>
<tr>
<td>
<table align="center" width="400" border="1px">
<tr>
<td>
Approved</br></br>
<input type="button" value="Yes"/><input type="button" value="No"/>

</td>
<td >
<table>
<tr><td>
Comments
</td>
<td>
<textarea ></textarea>
</td>
</tr>
</table>
</td>
</tr>
</table>
</td>
</tr>


	<%}%>

	 <%if(perName.equalsIgnoreCase("DeployBP")){%>
<tr>
<td>

<table align="center">
<tr><td>
Comments
</td>
<td>
<textarea ></textarea>
</td>
</tr>
</table>
</td>
</tr>



	<%}%>
	
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
