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
<%@ page import="java.sql.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmArtifactMapList.js" type="text/javascript" ></script>



</head> 

<script type="text/javascript">
function loadSec(){

	document.getElementById('showLobs').style.display="none";
	document.getElementById('showViews').style.display="none";
	document.getElementById('showGrps').style.display="none";
	document.getElementById('showProDom').style.display="none";

	}
	
</script>
<body onload="loadSec();">


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
			
			<td width="853" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td colspan="5" class="tblMainHead">
	Manage Artifact Mapping:</td>
  </tr>

	 
	 
	 <%
	 String errorMsg="";
	 if(request.getAttribute("errorMsg")!=null){
	 
	  errorMsg="Error while Mapping";
	
	
	 
	 %>
	 
	  <tr>
    <td colspan="5" class="tblMainHead">
	<font color="#FF0000"><%=errorMsg%></font></td>
  </tr>
	 <%}%>
		<form name="frmArtifactMap" id="frmArtifactMap" action="artifactMapping.do" method="post" onsubmit="return myValidate();" enctype="multipart/form-data">	
			<input type="hidden" name="artiMapProcess" value="mapArtiDets">	  
	
  <tr>
		<td>
		
			
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  
			  <tr class="tableInner">
					<td class="tableLeft">View Points:</td>
					<td class="tableRight">
					<select name="viewPntId" id="viewPntId" class="selectboxOne" onChange="retrieveURL('lobsList',this);">
							<option selected="selected" value="">Select One</option>
							<%
									 ArrayList viewPntList = (ArrayList) request.getAttribute("viewPntList");
          					 if(viewPntList!=null && viewPntList.size()!=0){
							Iterator itvPnt = viewPntList.iterator();
							while(itvPnt.hasNext()){
								String[] sVPnt = (String[])itvPnt.next();
								String viewPntId = sVPnt[0];
								String viewPntName = sVPnt[1];		
								
											

											 %>
											 <option value="<%=viewPntId%>"><%=viewPntName%></option>
											 <%
											 }
										}
									


							%>
							</select></td>
							
							
				  </tr>
			
		
		 <tr class="tableInner" id="showLobs">
					<td class="tableLeft">LOBs:</td>
					<td class="tableRight">
					<select name="lobId" id="lobId" class="selectboxOne" onChange="retrieveURLs('viewGrpList',this);">
							<option selected="selected" value="">Select One</option>
							
							</select></td>
				  </tr>	
		
			
			
			 <tr class="tableInner" id="showViews">
					<td class="tableLeft">Views:</td>
					<td class="tableRight">
					<select name="viewId" id="viewId" class="selectboxOne">
							<option selected="selected" value="">Select One</option>
							
							</select></td>
				  </tr>	
				 
				  

				   <tr class="tableInner" id="showGrps">
					<td class="tableLeft">Groups:</td>
					<td class="tableRight">
					<select name="grpId" id="grpId" class="selectboxOne" onChange="retrieveURL('processDomainList',this);">
							<option selected="selected" value="">Select One</option>
							
							</select></td>
				  </tr>	
				 
				  
				 
				  
				   <tr class="tableInner" id="showProDom">
					<td class="tableLeft">Domains/Processes:</td>
					<td class="tableRight">
					<select name="domProcId" id="domProcId" class="selectboxOne">
							<option selected="selected" value="">Select One</option>
							
							</select></td>
				  </tr>	
			

				 	 

			  <tr class="tableInner">
					<td class="tableLeft">Artifacts:</td>
					<td class="tableRight">
					
							<%
									 ArrayList cntList = (ArrayList) request.getAttribute("cntList");
          					 if(cntList!=null && cntList.size()!=0){
							 int j=0;
							Iterator itcnt = cntList.iterator();
							while(itcnt.hasNext()){
								String[] scnt = (String[])itcnt.next();
								String layerCntId = scnt[0];
								String layerCntName = scnt[1];	
								String masterCntId = scnt[2];
								String masterCntName = scnt[3];		
					
											 %></br></br>
	<input type="checkbox" name="artiFact<%=j%>" id="artiFact<%=j%>" value="<%=layerCntId%>" onclick="retrieveURLD('MappingDetExists',this);"/><%=layerCntName%>
											
											  <input name="uploads[<%=j%>]" type="file" id="uploads<%=j%>" />
											 
											 <%
											 j++;}
										}
		
							%>
							<input type="hidden" name="artiCnt" id="artiCnt" value="<%=cntList.size()%>"/>	
							</td>
							
							
				  </tr>
				  
					<tr>
						<td colspan="5" class="alignCenter">
						<input align="middle" type="submit" value="Map Artifact" class="gradBtn" /></td>
				   </tr>
				   </table>
				  
				 		 
			  
					</td>
	</tr>
		
	   </form>
	   
	
			  </table>
			  
			  
			  
			  
			  
				 
		</td>
	</tr>
	
	
	
	
	
	
	
	
	
	
	
	
	
<!-- Based on id -->

<tr>
 	<td>
		 
	</td>
</tr>  

<!--end of the file-->
</table>
			<!-- CONTENTS END HERE -->		
	
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
