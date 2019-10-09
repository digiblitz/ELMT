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
<script src="javascripts/calendar2.js" type="text/javascript"></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />

<script language="javascript">
    
function callpopup() {

	document.getElementById("pop").style.display = 'block'; 
	document.getElementById("popDoc").style.display = 'none'; 
	
}
function goBack(){
	strURL = "./SysMgmt.do?process=callArtifactBPList";
	window.location.href = strURL;
}

function callpopupDoc() {

	document.getElementById("pop").style.display = 'none'; 
	document.getElementById("popDoc").style.display = 'block'; 
	
}
function validate()
{
	if(document.requestIns.name.value==""){
		alert("Please enter a name");
		document.requestIns.name.focus();
		return false;
		}
	return true;
}

function submit(){
	
	 document.forms["requestIns"].submit();


}
     </script>
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
		<%@ include file = "../../include/infobar.jsp"  %>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
 
  <tr>
    <td class="tableCommonBg">
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="centerTab">
	  
		

  
   <tr>
   <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
   <tr>
    
 
			<td>
				<!-- CONTENTS START HERE -->
<div class="cmmnForm">
	<div class="colspan">
	Business Process:<span class="styleBoldTwo"> Initiate Governance for Business Process</span>	
	</div>
   	

   	<%
   	String id=(String)request.getParameter("id");
			String Name=(String)request.getParameter("bpName");
			String Version=(String)request.getParameter("bpVersion");
		String govStatus=(String)request.getParameter("govStatus");	

    %>

    <form name="requestIns" id="requestIns"    method="post" action="SysMgmt.do?process=busineessProceSubmit" class="formcss">
 
<input type="hidden" name="id" value="<%=id%>"/>
<input type="hidden" name="gov" value="stopGov"/>
<input type="hidden" name="govStatus" value="<%=govStatus%>"/>

							<div class="rowHead">Business process:</div>
										<div class="row">
											<span class="label">Name:<span class="asterisk">*</span></span> <span class="formX"><input
												type="text" name="name" id="name" class="textboxOne" size="20" value ="<%=Name%>" readonly/></span>
										
								
									
										</div>
										<div class="row">
											<span class="label">Description:</span> <span ><textarea rows="4" cols="2" name="desc"></textarea></span>
										
										</div>
										
										
										<div class="row">
											<span class="label">Version:</span> <span class="formX"><input
												type="text" name="version"  class="textboxOne" size="20" value ="<%=Version%>"  readonly/></span>
										
										</div>
										
									
								
										<div class="row">
										<span class="label">Criticality:</span>                                      <span class="formX"><select  id="select" name="criticality" class="selectboxOne">
										<option selected="selected" value="">Select One</option>
										  <option value="high">High</option>

										    <option value="medium">Medium</option>
													  <option value="low">Low</option></select>&nbsp;
										 </span>			      </div>
									<div class="row">
											<span class="label">Consumable:</span> <span class="formX"><input
												type="checkbox" name="cons" class="textboxOne" size="20" /></span>
										
										</div>
										<div class="row">
									<span class="label"><input type="button" value="Add BPEL" class="gradBtn" name="method"  onclick="callpopup()"/>&nbsp;&nbsp;
									<input type="button" value="Add Document" class="gradBtn" name="method"  onclick="callpopupDoc()"/>
									</span>
									<span class="formX">
									</span>								</div>
									
										<div id="pop" style="display:none;">
	
 

							
										<div class="row">
											<span class="label">File Name:</span> <span class="formX"><input
												type="text" name="bpname"  class="textboxOne" size="20" /></span>
										
								
									
										</div>
										<div class="row">
											<span class="label">Path:</span> <span class="formX"><input
												type="text" name="bppath"  class="textboxOne" size="20" /></span>
										
										</div>
										
										<div class="row">
											<span class="label">Description:</span> <span class="formX"><input
												type="text" name="bpdesc"  class="textboxOne" size="20" /></span>
										
										</div>
										
										
										
										
									
								
										<div class="row">
										<span class="label">Criticality:</span>                                      <span class="formX"><select  id="select" name="bpcriticality" class="selectboxOne">
										<option selected="selected" value="">Select One</option>
										  <option value="high">High</option>

										    <option value="medium">Medium</option>
													  <option value="low">Low</option></select>&nbsp;
										 </span>			      </div>
										 <div class="row">
											<span class="label">Version:</span> <span class="formX"><input
												type="text" name="bpversion"  class="textboxOne" size="20" /></span>
										
										</div>
										<div class="row">
											<span class="label">Target Namespace:</span> <span class="formX"><input
												type="text" name="bptrname"  class="textboxOne" size="20" /></span>
										
										</div>
										<div class="row">
											<span class="label">Location:</span> <span class="formX"><input
												type="text" name="bplocation"  class="textboxOne" size="20" /></span>
										
										</div>
									
																
											</div>

								<div id="popDoc" style="display:none;">
	
 

							
										<div class="row">
											<span class="label">Document Name:</span> <span class="formX"><input
												type="text" name="docname"  class="textboxOne" size="20" /></span>																						
										</div>

										<div class="row">
											<span class="label">Description:</span> <span class="formX"><input
												type="text" name="docdesc"  class="textboxOne" size="20" /></span>
										
										</div>
										<div class="row">
											<span class="label">From Path:</span> <span class="formX"><input
												type="text" name="docpath"  class="textboxOne" size="20" /></span>
										
										</div>
										
										<div class="row">
											<span class="label">Location (To Path)</span> <span class="formX"><input
												type="text" name="doclocat"  class="textboxOne" size="20" /></span>
										
										</div>
									</div>
	
	
									
										<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="button" value="Save" class="gradBtn" name="method" onclick="validate();submit()" />
									 <input type="button" value="Cancel" class="gradBtn" onclick="goBack();"><br></span>								</div>
										

         
	</form>
	
</div>
</div>
</td>
</tr>
</table>
			<!-- CONTENTS END HERE -->			</td>
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
