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
<%@ page import="java.lang.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="js/basic.js" type="text/javascript" ></script>
<script src="js/frmMeeEduActRecap.js" type="text/javascript" ></script>
<script src="js/frmrRoleMEditValidate.js" type="text/javascript" ></script>
<script src="js/frmrRolePrevilageValidate.js" type="text/javascript" ></script>
<script>
    function cancelEditArtifact(mainArtiId)
    {
        if(confirm("Do you want to Cancel and go back to List Page?"))
	{
        strURL = "./artifact.do?process=artifactList&mapId="+mainArtiId;
	window.location.href = strURL;
        }
       else
	{
		return;
	}
    }
	function clearField(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			obj.subArtifact.value = "";
		}

		
	}
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
		<%@ include file = "../../include/infobar.jsp" %>
		<!-- INFO BAR ENDS HERE -->
	</td>
  </tr>
  <tr>
    <td class="tableCommonBg">

		<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">

		  <tr>
                   
<td width="230" class="menuTablePad">
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->
</td>
		  
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="1" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				   <tr>
					<td colspan="2"  class="tblMainHead">
						 Maintain Artifact: <span  class="styleBoldTwo">Edit </span></td>

				  </tr>
				 
				  <tr>
					<td colspan="2" class="tblDescrp">
					You can <strong>Edit/Update</strong> the <span class="tblMainHead">Artifact</span><strong> </strong>created by you as given below. <br />
					</td>
				  </tr>

				  <tr>
					<td>
                                            
					  <%
							

							String subArtifact = (String)request.getAttribute("subArtifact");
							String mainArtifactName = (String)request.getAttribute("mainArtifactName");
							String mainArtiId = (String)request.getAttribute("mainArtiId");
							String artifactId = (String)request.getAttribute("artifactId");
							System.out.print("subArtifact:" + subArtifact);
							System.out.print("mainArtifactName:" + mainArtifactName);
							
						%>

                 
				<form name="frmRoleMgtRoleEdit" id="frmRoleMgtRoleEdit" method="post" action="artifact.do" onsubmit="return formValidate(this);">
                    
						<input type="hidden" name="process"	value="editArtifact"/>
						<input type="hidden" name="artifactId" value="<%=artifactId%>"/>
						<input type="hidden" name="mainArtiId" value="<%=mainArtiId%>"/>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

						    
                                                    <tr>
							<td  colspan="2" class="tblDescrp">
								Fields marked with an asterisk (<span class="asterisk">*</span>) are required.
							</td>
						</tr>
						<%
						  	String err = request.getParameter("err");
						  	if(err!=null) {
							//String subArtifact=(String)request.getAttribute("subArtifact");
						  %>
						  <tr>
							<td class="styleError" colspan="2"><strong>Sub Artifact already exist</strong></td>
 						  </tr>
						  <%
							  
						  }
				         %>
							<tr>
							<td class="tableLeft">Main Artifact :</td>
							<td class="tableRight">
			<input name="mainArtifact" id="mainArtifact" type="text" class="readOnly" value="<%=mainArtifactName%>" size="25" maxlength = "100" readOnly="true"/>  </td>
							
							</tr>
                                                        
                                                    <tr>
                                                             <td class="tableLeft">Sub Artifact :</td>
						                                     <td class="tableRight">
						
							<input name="subArtifact" id="subArtifact" type="text" class="textboxOne" value="<%=subArtifact%>" size="25" maxlength = "100"/>  <span class="asterisk">*</span></td>
							</tr>
                                       
						  <tr>
						  
							<td colspan="2" style="text-align:center" height="25">
							<input type="submit" value="Update" class="gradBtn" />&nbsp;
						
							<input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearField(this.form)"/>&nbsp;
							
						    <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelEditArtifact('<%=mainArtiId%>')"/></td>
						  </tr>
						 
						</table>
						</form>
					</td>
				  </tr>
                 
				</table>
			<!-- CONTENTS END HERE -->
			</td>
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
