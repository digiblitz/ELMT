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
<%@ page import="java.util.*" %> 

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
 
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 

</head>
<script language="javascript">


function onValidate(){

	if(document.myform.acStatus.value==""){
		alert("Select a Status");
		document.myform.acStatus.focus();
		return false;
	}
	
	return true;
}
</script>

<body>
<%
String status1 = (String)request.getAttribute("status");%>

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
				<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
				  <tr>
					<td colspan="5" class="tblMainHead"><strong>OnLine Event Entries </strong>:Item List <span class="styleBoldTwo"> </span></td>
				  </tr>
				  <tr>
					<td colspan="5" class="tblDescrp">&nbsp;
					To edit a Item Name click on <strong>'Edit'</strong> button. To change the status click on the <strong>'Activate/Deactivate'</strong> button.  <br />
	<br />
					</td>
				  </tr>
				 
			 <tr>
			<td>
			<table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout">
				<form name="myform" id="myform" method="post" action="./OEEPriceItem.do" onsubmit="return onValidate();">
				<input name="cmd" type="hidden" value="listDetails">						
				<tr>
				<td colspan="12">
				Status:
				<select name="acStatus" id="acStatus" class="selectboxOne" >
				<option selected="selected" value="" >Select One</option>
				<%
				String[] status = new String[]{"Activate","Deactivate"};
				for(int k =0; k<status.length; k++){
				if(status1!=null && status1.equalsIgnoreCase(status[k])){
				%>
				<option value="<%=status[k]%>" selected="selected" ><%=status[k]%></option>
				<%}else{%>
				<option value="<%=status[k]%>"><%=status[k]%></option>
				<%}}%>
				</select><span class="asterisk">*</span>
				<input type="submit" name="appButton" id="appButton" value="Submit" class="gradBtn"  />
				
				</td>
				</tr> 
				</form>
				  <tr>
							<td width="123" height="27" class="tblRowHead">Item  Name</td>
							<td width="123" height="27" class="tblRowHead">Organizer Edit Status</td>
							<td width="133" class="tblRowHead">Status</td>
							<td width="54" class="tblRowHead">Edit</td>
				   </tr>

					<%
					 ArrayList itemDetails=(ArrayList)request.getAttribute("itemList");
					 if(itemDetails!=null && itemDetails.size()!=0){ 
					 Iterator it = itemDetails.iterator();
					  while(it.hasNext()){
						    String [] itemDetail  = (String[])it.next();
							String itemId = itemDetail[0];
							String itemName = itemDetail[1];
							boolean orgStatus = Boolean.parseBoolean(itemDetail[2]);
							String itemStatus= itemDetail[3];
							String orgEditstatus="";
							if(orgStatus==true){
							orgEditstatus="Yes";
							}else{
							orgEditstatus="No";
							}
					%>
							<form name="myform" method="post" action="./OEEPriceItem.do">	
							<input name="cmd" type="hidden" value="initUpdateItem">	
							<input name="itemId" type="hidden" value="<%=itemId%>">
							<input name="itemName" type="hidden" value="<%=itemName%>">
							<input name="orgStatus" type="hidden" value="<%=orgStatus%>">
							<input name="itemStatus" type="hidden" value="<%=itemStatus%>">
						  <tr>
		<td height="26" class="listCellBg"><%=itemName%></td>
		<td height="26" class="listCellBg"><%=orgEditstatus%></td>
		<% if(itemStatus.equalsIgnoreCase("false")){ %>
		 <td class="listCellBg"><input name="btnSubmit" type="button"  class="oneBtn" value="Activate" onclick="location.href='OEEPriceItem.do?cmd=activate&itemId=<%=itemId%>'" /></td>
		<%} else if(itemStatus.equalsIgnoreCase("true")){		%>
		<td class="listCellBg"><input name="btnSubmit" type="button"  class="oneBtn" value="Deactivate" onclick="location.href='OEEPriceItem.do?cmd=deActivate&itemId=<%=itemId%>'" /></td> 
		 <% }%>
		 <td class="listCellBg"><input name="btnSubmit" type="submit"  class="oneBtn" value="Edit"  /></td>
		 
						   </tr>
						</form>
		   
					      <%	}
									}else {
								%>
								<tr>
								  <th height="25" colspan="5">No Records are available. </th>
								</tr>
								<%}%>

						  <tr>
							<td height="19" colspan="7">&nbsp;</td>
						   </tr>
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
