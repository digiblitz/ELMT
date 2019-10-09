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
<%@ page import = "javax.sql.*" %>
<%@ page import = "java.util.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script>
function Trim(sString) 
{
	while (sString.substring(0,1) == ' '){
	sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' '){
	sString = sString.substring(0,sString.length-1);
	}
return sString;
}

function myValidate(){
		if(document.myform.selEventType.value==""){
			alert("Please select any one Event Type.");
			document.myform.selEventType.focus();
			return false;
		}
		
		if(Trim(document.myform.txtSpec.value)==""){
			alert("Please enter a Specification name.");
			document.myform.txtSpec.focus();
			return false;
		}
		/*if(document.myform.selEventType.value==""){
			alert("Please select any one User Type.");
			document.myform.selEventType.focus();
			return false;
		}*/
		

		return true;
	}

</script>




<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> --> 
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
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">


  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Specification Detail Master  </span></td>
  </tr>
 
  <tr>
  	<td>
	
<form name="myform" id="myform" action="specification.do" method="post" onsubmit="return myValidate();"  >
	<input type="hidden" name="specProcess" value="insertSpec">


	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">Add A SpecificationDetail:</td>
				  </tr>
				  <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong>Specification Name already exists.</strong></td>
					</tr>
					<%
					}
					%>
				  
				   <tr>
				     <td class="tableLeft">Event Type :</td>
				     <td class="tableRight">
						<select name="selEventType" id="selEventType" class="selectboxOne" >
										<option selected="selected" value="">Select One</option>
										<%
										Vector activityDetails = (Vector)request.getAttribute("eventObj");
										if(activityDetails!=null && activityDetails.size()!=0){
										Enumeration it = activityDetails.elements();
										while(it.hasMoreElements()){
										String[] eduDet =(String[])it.nextElement();
										String id = eduDet[0];
										String activityName1 = eduDet[1];
										
										%>
										
										<option value="<%=id%>"><%=activityName1%></option>
									 <%
							  }
							}
							  %>
									  </select>
				 	 <span class="asterisk">*</span>
					 </td>
		     	  </tr>
				  <tr>
				     <td class="tableLeft">Specification:</td>
				     <td class="tableRight">
					 <input name="txtSpec" type="text" id="txtSpec" class="textboxOne" size="32" />
				 	 <span class="asterisk">*</span>	
				 					 </td>
		     	  </tr>

				
				  <tr>
					<td class="tableLeft">Description:</td>
					<td class="tableRight"><textArea name="txtDesc"  id="txtDesc" rows="3" size="30" /></textarea>
                    	
					</td>
				  </tr>
			   

			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" value="Add" class="gradBtn"  />
					&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="location.href='index.jsp'" />					</td>
				  </tr>
			</table>
			
		</form>
	</td>
  </tr>
  
  <tr>
    <td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
