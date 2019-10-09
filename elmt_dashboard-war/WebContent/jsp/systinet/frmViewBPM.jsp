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
 
 
  function callEdit(id,artiUid,lifecycleId,processname,proceDesc,BPversion){
	 
	 if(id!='Pending'){

	 if(confirm('This is a governed Business Process. \n Please raise a request approval for the stage.'))
	 {
	strURL = "./SysMgmt.do?process=callBPMEdit&id="+id+"&artUid="+artiUid+"&lifecycleId="+lifecycleId+"&artifactName="+processname+"&description="+proceDesc+"&version="+BPversion;
		window.location.href = strURL;
	 }
	 else
	 {
		 return;
	 }
	 }
	 else
		 {
		 alert ("This process has a request in pending. Please wait until the current request is processed.");
		 
		 }
		
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
    <td>
		<!-- INFO BAR STARTS HERE -->
		<h3>View Business Process </h3>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  
		<%String success=(String)request.getAttribute("success");
  
  if(success!=null&&success.equalsIgnoreCase("success")){%>
  <tr>
    <td>
		<!-- INFO BAR STARTS HERE -->
		<h3><font color="blue">Request Submitted Successfully to the Systinet.</font></h3>
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  <%}%>
		  
   <tr>
   <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
   <tr>
    
 
			<td>   <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="left" > <td colspan="7" class="tblMainHead" >
	</td>
	 <tr>

     
                       <td width="30" height="27" class="tblRowHeadTwo">Id</td>
                       <td width="120" height="27" class="tblRowHeadTwo">Name</td>
		               <td width="30" height="27" class="tblRowHeadTwo">Version</td>
                       <td width="50" height="27" class="tblRowHeadTwo">Category</td>
		               <td width="50" height="27" class="tblRowHeadTwo">Production Status</td>
		               <td width="50" height="27" class="tblRowHeadTwo">Governance Status</td>
		               <td width="50" height="27" class="tblRowHeadTwo">Action</td>
            </tr>
             <%
   ArrayList bpmData=(ArrayList)request.getAttribute("bpmData");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (bpmData != null && bpmData.size() != 0) {  
                                                            
   Iterator iter = bpmData.iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	
                                                                	
                                                                	String [] artiType=(String[])iter.next();
                                                                	String id=artiType[0]; 
                                                                	String processname=artiType[1]; 
                                                                	String BPversion=artiType[2];
                                                                	String status=artiType[3];
                                                                	String artiUid=artiType[4];
                                                                	String lifecycleId=artiType[5];
																	String proceDesc=artiType[6];
                                                                	

                                                %>
                                                 <tr><td height="25"><%=id%></td><td align="left" height="25"><%=processname%></td><td align="left" height="25"><%=BPversion%></td><td height="25">business process</td>
                                                 <td height="25">Active</td><%if(status.equalsIgnoreCase("Pending")){%><td height="25">Modify Request <%=status%></td><%}else{%><td height="25">Active</td><%}%><td height="25"><a href="#" onclick="callEdit('<%=status%>','<%=artiUid%>','<%=lifecycleId%>','<%=processname%>','<%=proceDesc%>','<%=BPversion%>')">Move Lifecycle Stage</a></td></tr>
                                                <%
                                                 
                                               
                                                                   
                                                                }
   }
   else{
	   %>
	   <tr><td colspan="6">No DATA </td></tr>
                                                <%
   }
                                                %>

				<!-- CONTENTS START HERE -->
</table>
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
