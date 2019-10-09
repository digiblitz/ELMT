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
 
  function approved(url) {
	  
	  strURL = "./SysMgmt.do?process=approved";
      window.location.href = strURL;
		
	}
 
  function denied(url) {
		
	  strURL = "./SysMgmt.do?process=denied";
      window.location.href = strURL;
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
		
		<!-- INFO BAR ENDS HERE -->	
	</td>
  </tr>
  
		 
   <tr>
   <td><table width="100%" border="1" align="center" cellpadding="" cellspacing="" id="centerTab">
   <tr>
    
 <form name="artifactlist" action="SysMgmt.do">
 
  
   
 
			<td>   <table  border="1" cellpadding="0" cellspacing="0" width="100%" align="left" > <td colspan="6" class="tblMainHead" >
	</td>
	 <tr>

                           <td width="30" height="27" class="tblRowHeadTwo">Serial Id</td>
                       <td width="90" height="27" class="tblRowHeadTwo">Artifact Name</td>
                       <td width="120" height="27" class="tblRowHeadTwo">Artifact Description</td>
		               <td width="180" height="27" class="tblRowHeadTwo">Date of Request Raised</td>
                       <td width="150" height="27" class="tblRowHeadTwo">Stage Name</td>
					   <td width="200" height="27" class="tblRowHeadTwo">Actions</td>
					   <td width="200" height="27" class="tblRowHeadTwo">Comments</td>
				
		     
            </tr>
              <%
   Map <String,String[]> artifactlist=(HashMap)request.getAttribute("votinglist");
    //System.out.println  ("artifactData"+artifactData.size());                                           
                                                 
   if (artifactlist != null && artifactlist.size() != 0) {  
                                                            
   Iterator iter = artifactlist.entrySet().iterator();
                                                                //String [] userType = {ID, name };
                                                                while (iter.hasNext()) {
                                                                	Map.Entry mEntry = (Map.Entry) iter.next();
                                                                	
                                                                	String [] artiType=(String[])mEntry.getValue();
                                                                	String id1=artiType[0];
                                                                	String id=artiType[1];
                                                                	String name=artiType[2]; 
                                                                	String version=artiType[3];
                                                                	String category=artiType[4];
                                                                	String status=artiType[5];
                                                                	

                                                %>
                                                 <tr><td><%=id1%></td><td><%=id%></td><td align="left"><%=name%></td><td align="left"><%=version%></td><td><%=category%></td><td><input type="button" value="Approved" onclick="approved()"/><input type="button" value="Denied" onclick="denied()"/></td>
                                                 <td><%=status%></td></tr>
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
				</form>
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
