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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
 <script language="javascript">

//window.onload=call();

function selfClose(name,id) {
refreshProductList(name,id);
self.close();
}



function refreshProductList(name,id){
	var val=document.getElementById("reqVal").value;
	window.opener.document.frmWSArti.name.value=name;
	window.opener.document.frmWSArti.drop.value=val;
	window.opener.document.frmWSArti.id.value=id;

	window.opener.document.frmWSArti.submit();
}
 
 function call(){

	document.frmDrop.submit();
	window.close();
		
	       }


	 </script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
 
 
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
	  
		 <tr>
   <td>
 <%String name=(String)request.getAttribute("name");
 String artId=(String)request.getAttribute("artId");

%>
	<form name="frmDrop" action="SysMgmt.do" method="post">
	
	<table align="left">
	<tr>
	<td>
	
	Process Name : </td> <td><%=name%></td>
	
	</tr>
	<tr>
	<td>
   Select LifeCycle:
   </td>
   <td>
	<select name="reqVal" id="reqVal" class="selectboxOne">
									<option value="high">High</option>
								    <option value="medium">Medium</option>
								  <option value="low">Low</option>
							  
	</select>
</td>
</tr>
</table></br></br></br>
<table width="100" align="center">
<tr>

<input type="button" onclick="selfClose('<%=name%>','<%=artId%>')" value="OK"  align="middle"/>

</tr>
</table>

	
	</div>
	</form>
											
					</td>
		  </tr>
	  </table>
	
	</td>
  </tr>
 
</table>

</body>
</html>
