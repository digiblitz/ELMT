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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>

<script language="javascript">

//refreshProductList();

function selfClose(uRole,uLogin) {

if(confirm("Do you want to Active '"+ uRole +"' role to the '"+ uLogin +"' ?"))
	{
 document.frmRoleEntMapApp.submit();
 window.opener.document.frmSearchEmp.submit();
self.close();
	}
	else
	{
		return;
	}
}
function selfCloseWithoutOper() {


 //document.frmRoleEntMapApp.submit();
 //window.opener.document.frmSearchEmp.submit();
self.close();
}


/*function refreshProductList(){
window.opener.document.frmProductList.submit();
}*/

</script>
<body>

<%
	String roleRoleId = (String) request.getAttribute("roleId");
	if(roleRoleId==null){
		roleRoleId = "";
	}
	//String privId = "";

%>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">

  <tr>
    <td class="tableCommonBg">
		<%
		String uName=(String)request.getParameter("uLogin");      
        String uEmail=(String)request.getParameter("uEmail");
        String roleName=(String)request.getParameter("uRole");  
		String uId=(String)request.getParameter("uId");   
		String supName=(String)request.getParameter("supName");  
		String roleId=(String)request.getParameter("roleId");
		
        
      
        
        %>
		
	     
		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			
		   	<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2">
						 Roles Approve: <span class="styleBoldTwo"> Module Screen </span>
					</td>
				  </tr>
				  
				  <tr>
					<td>
					  <form name="frmRoleEntMapApp" id="frmRoleEntMapApp" action="roles.do"> 
					  <input type="hidden" name="roleProcess" value="chgUserStat"/>

						  <input type="hidden" name="uId" value="<%=uId%>"/>
						  <input type="hidden" name="uEmail" value="<%=uEmail%>"/>
						  <input type="hidden" name="uLogin" value="<%=uName%>"/>
						
						  <input type="hidden" name="uRole" value="<%=roleName%>"/>
						  <input type="hidden" name="roleId" value="<%=roleId%>"/>
						    <input type="hidden" name="supName" value="<%=supName%>"/>
						
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Map Role to User: </td>
						  </tr>
						  
						  <tr>
						    <td class="tableLeft">User Name</td>
						    <td class="tableRight"><span class="alignLeft">					      
							<%=uName%>
						    </span></td>
					      </tr>
					      
					      <tr>
						    <td class="tableLeft">Assigned Role</td>
						    <td class="tableRight"><span class="alignLeft">					      
							<%=roleName%>
						    </span></td>
					      </tr>
   <tr>
						    <td class="tableLeft">Assigned Module(s)</td>
						    <td class="tableRight"><span class="alignLeft">		
							<br>
						     <%	ArrayList disEntPriPermList = (ArrayList)request.getAttribute("disEntPriPermList");
		  
		                      String status="\n";
							  int count=0;
									if(disEntPriPermList!=null && disEntPriPermList.size()!=0){
										Iterator itEntList = disEntPriPermList.iterator();
										while(itEntList.hasNext()){
											String[] entList = (String [])itEntList.next();
											//String[] entList = {entityId, entityName};
											//String distroleName = entList[0];
											String distentityName = entList[0];
											if(count==0){
											%>

									      
							<%=distentityName%>
							<% count++;}
                                          else
											{%>
                                            <br><%=distentityName%>
											<%}
										}

							}%>
						    </span></td>
							<br>
					      </tr>

						   <tr>
						    <td class="tableLeft">Assigned Activitie(s)</td>
						    <td class="tableRight"><span class="alignLeft">		
							<br>
						     <%	ArrayList entPriPermList = (ArrayList)request.getAttribute("entPriPermList");
		  
		                      String status1="\n";
							  String temp="";
							  int count1=0;
									if(entPriPermList!=null && entPriPermList.size()!=0){
										Iterator itEntPrivList = entPriPermList.iterator();
										while(itEntPrivList.hasNext()){
											String[] entPriList = (String [])itEntPrivList.next();
											//String[] entList = {entityId, entityName};
											//String distroleName = entList[0];
											String privName = entPriList[0];
											if(count1==0){
												temp=privName;
											%>

									      
							<%=privName%>
							<% count1++;}
                                          else if(!temp.equalsIgnoreCase(privName))
											{%>
                                            <br><%=privName%>
											<%}
										}

							}%>
						    </span></td>
						
					      </tr>

					        <tr> 
							<td colspan="2" class="alignCenter">
						  <input name="button" type="button" class="gradBtn" value="Activate" onclick="selfClose('<%=roleName%>','<%=uName%>')"/>
						    <input name="button" type="button" class="gradBtn" value="Close" onclick="selfCloseWithoutOper()"/>
						  </td>
						  </tr>
						
						  
					      	</form>
						</table>
						
					</td>
				  </tr>
				  
				 
				</table>
			<!-- CONTENTS END HERE -->		
			</td>
		
		  </tr>

	  </table>
	
	</td>
  </tr>

</table>


 

</body>
</html>
