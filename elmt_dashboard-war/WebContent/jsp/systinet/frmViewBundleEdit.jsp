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
     function callUni(){
    	 var val= Math.random().toString();
         var ch=val.substring(2,val.length);
   document.getElementById("reqId").value=ch;  


	  
}
     function validate()
     {
     	if(document.requestIns.reqId.value==""){
     		alert("Id cannot be empty. Please click on the 'Generate ID' button");
     		document.requestIns.reqId.focus();
     		return false;
     		}
     	else
     
     	return true;
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
 <% String artUid=(String)request.getAttribute("artUid");
 String lifecycleId=(String)request.getAttribute("lifecycleId");
 
 String artifactName=(String)request.getAttribute("artifactName");
 String description=(String)request.getAttribute("description");
 
 
 %>
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
		 Request Status :<span class="styleBoldTwo"> Modify Request Status</span>	
	</div>
   	

    <form name="requestIns" id="requestIns" method="post" action="SysMgmt.do?process=subRequest" class="formcss" >
 <input type="hidden" name="method" value="insertrequest"/>
 <input type="hidden" name="artifactName" id="artifactName" value="<%=artifactName%>"/>
 <input type="hidden" name="description" id="description" value="<%=description%>"/>


							<div class="rowHead">Request Status Information:</div>
										<div class="row">
											<span class="label">Request ID:<span class="asterisk">*</span></span> <span class="formX"><input
												type="text" name="reqId" id="reqId" class="textboxOne" size="20" readonly/><input type="button" value="Generate ID"   size="20" onclick="callUni(); " /></span>
										
								
									
										</div>
										<div class="row">
											<span class="label">Request Title:<span class="asterisk">*</span></span> <span class="formX"><input
												type="text" name="reqTitle" class="textboxOne" size="20" value="<%=artifactName%>" readonly/></span>
										
										</div>
										
										
										
										<div class="row">
											<span class="label">Date of the WS in :</span> <span class="formX"><input
												type="text" name="date1" class="textboxOne" size="20" />
												  <a href="javascript:cal1.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a></span>
										
										</div>
										<div class="row">
											<span class="label">Date by when approval is required :</span> <span class="formX"><input
												type="text" name="date2" class="textboxOne" size="20" />
												  <a href="javascript:cal2.popup();"><img src="images/calendar.jpg" alt="Click To View Calendar" width="20" height="20" style="vertical-align:bottom;" border="0" /></a></span>
										
										</div>
										
										
										 <%
   ArrayList Stagename=(ArrayList)request.getAttribute("Stagename");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (Stagename != null && Stagename.size() != 0) {  
                                                            
   Iterator iter = Stagename.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	
                                                                	
                                                                	String [] artiType=(String[])iter.next();
                                                                	String stagename=artiType[0]; 
                                                                	String Stagevalue=artiType[1]; 
                                                                	
                                                                	 %>
                                                                     <div class="row">
											<span class="label">Stage Name:</span> <span class="formX"><input
												type="text" name="stagename" value="<%=stagename %>" class="textboxOne" size="20" /><input
												type="hidden" name="Stagevalue" value="<%=Stagevalue %>" class="textboxOne" size="20" /><input
												type="hidden" name="artifactId" value="<%=artUid %>" class="textboxOne" size="20" /><input
												type="hidden" name="lifecycleId" value="<%=lifecycleId %>" class="textboxOne" size="20" /></span>
										
										</div>
                                                                    <%
                                                                }
   }
                                                %>
										
										<div class="row">
											<span class="label">Current Version:</span> <span class="formX"><input
												type="text" name="curVersion" class="textboxOne" size="20" /></span>
										
										</div>
										<div class="row">
											<span class="label">Modified Version:</span> <span class="formX"><input
												type="text" name="modVersion" class="textboxOne" size="20" /></span>
										
										</div>
								
										<div class="row">
										<span class="label">Request Priority:</span>                                      <span class="formX"><select name="reqPri" id="select" name="reqPriority" class="selectboxOne">
										
										  <option value="high">High</option>

										    <option value="medium">Medium</option>
													  <option value="low">Low</option></select>&nbsp;
										 </span>			      </div>
									<div class="row">
											<span class="label">Request Description:</span> <span class="formX"><input
												type="text" name="reqDesc" class="textboxOne" size="20" /></span>
										
										</div>
										<div class="row">
									<span class="label">&nbsp;</span>
									<span class="formX"><input type="submit" value="Submit" class="gradBtn" name="method" onclick="validate();"/></span>								</div>
										

         
	</form>
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
<script language="javascript">
    var cal1 = new calendar2(document.forms['requestIns'].elements['date1']);
     cal1.year_scroll = true;
     cal1.time_comp = false;

   var cal2 = new calendar2(document.forms['requestIns'].elements['date2']);
     cal2.year_scroll = true;
     cal2.time_comp = false;




</script>

</body>
</html>
