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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAddNonHumanDemographic.js" type="text/javascript" ></script>

<script>
    function cancelAddNonHumanDemo()
    {
        if(confirm("Do you want to Cancel and go back to List Page?"))
	{
	if(document.frmMyForm.selSpecies!=null){
	var speciesId = document.frmMyForm.speciesId.value;
	
        strURL = "./Demographic.do?horseprocess=reqList&selSpecies="+speciesId;
	window.location.href = strURL;
	}
        }
	else
	{
		return;
	}
    }
	
	
function clearFields(obj)
{

	for(var i=0;i<obj.elements.length;i++)
    {
		if(obj.elements[i].type=='select-one')
		{
	        obj.selSpecies.value = "";	
		}

		if(obj.elements[i].type=='text')
		{

			obj.elements[i].value = "";
		}
		if(obj.elements[i].type=='select-one')
		{
	        obj.selTyp.value = "";	
		}

		if(obj.elements[i].type=='radio')
		{
			obj.elements[i].checked = false;
		}
		
		
	}
}	
</script>

</head>

<body>
<!--
Demographic Category: Non Human Dhivya Here-->

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
			
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				   <tr>
					<td  colspan="2"  class="tblMainHead">
						 Demographic Categories - Non-human: <span  class="styleBoldTwo"> Create</span></td>
				  </tr>
				   <tr>
					<td colspan="2" class="tblDescrp">
<!--					<img src="images/usea_logo.jpg" class="imgFloatLeft"/>-->You can <strong>Create</strong> a <strong>New Demographic Category - Non-human</strong> for all members and non-members Online Services Dashboard, right here!

					<br />					</td>
				  </tr>
				  <tr>
					<td>
			
<%

ArrayList typeList = (ArrayList)request.getAttribute("typeList");
ArrayList speciesList = (ArrayList)request.getAttribute("speciesList");
String speciesId =(String)request.getAttribute("speciesId");

%>
					
						<form name="frmMyForm" id="frmMyForm" action="Demographic.do" onsubmit="return myValidate(this);">
							<input type="hidden" name="horseprocess" id="horseprocess" value="addNonHDemo"/>
							
							<input type="hidden" name="speciesId" id="speciesId" value="<%=speciesId%>"/>
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

						  
                                                    <tr>
							<td  colspan="2" class="tblDescrp">
								Fields marked with an asterisk (<span class="asterisk">*</span>) are required.
							</td>
						</tr>
						<%
						  	String err =(String)request.getAttribute("err");
							System.out.println("err"+err);
						  	if(err!=null && err.equalsIgnoreCase("st")) {


						  %>
						  <tr> 
							<td class="styleError" colspan="4"><strong>Demographic already exist</strong></td>
 						  </tr>
						  <%
						  }
						  %>
						
					 <tr>
				     <td class="tableLeft">Species:</td>
				     <td class="tableRight">
				<select name="selSpecies" id="selSpecies" class="selectboxOne" >
					<option selected="selected" value="">Select One</option>
				
							<%
				if(speciesList!=null && speciesList.size()!=0){
					Iterator it = speciesList.iterator();
					while(it.hasNext()){
						String [] dDet = (String[])it.next();
						String userTypId = dDet[0];
						String userTypName = dDet[1];
				
				if(speciesId!=null && speciesId.equalsIgnoreCase(userTypId)){
			%>
			<option value="<%=userTypId%>" selected="selected"><%=userTypName%></option>
			
			<%} else{%>
			
			<option value="<%=userTypId%>"><%=userTypName%></option>
		
			<%}}}%>			
									  </select><span class="asterisk">*</span>
					 </td>
		     	  </tr>	
						
						  <tr> 
							<td class="tableLeft"> Demographic:</td>
							<td class="tableRight">
							
<input name="txtDemo" id="txtDemo" type="text" class="textboxOne" size="25" maxlength="80"/><span class="asterisk">*</span> </td>
							

						  </tr>
                                                  
                                                  <tr>
							<td class="tableLeft"> Description:</td>
							<td class="tableRight">
							
						<!--<textarea name="txtDemoDesc"  id="txtDemoDesc" rows="3" size="30" /></textarea>-->
						
						  <input name="txtDemoDesc" id="txtDemoDesc" type="text" class="textboxOne" size="25" maxlength ="100"/><span class="asterisk">*</span></td>
						
						  </tr>
						  				  	
					 <tr>
				     <td class="tableLeft">Type:</td>
				     <td class="tableRight">
						<select name="selTyp" id="selTyp" class="selectboxOne" >
						<option selected="selected" value="">Select One</option>
							<%
				if(typeList!=null && typeList.size()!=0){
					Iterator it1 = typeList.iterator();
					while(it1.hasNext()){
						String [] dDets = (String[])it1.next();
						String typeId = dDets[0];
						String typeName = dDets[1];
				
			%>
			<option value="<%=typeId%>"><%=typeName%></option>
			<%}}%>
									  </select>
				 	 <span class="asterisk">*</span>
					 </td>
		     	  </tr>	
                                                  <tr>
							<td class="tableLeft">Status</td>
							<td class="tableRight" >
			
            <input type="radio" name="demoStat" value="true"  id="demoStat"/>Active <input type="radio" name="demoStat" value="false"  id="demoStat" />Inactive   <span class="asterisk">*</span></td>

						  </tr>

						  <tr align="center"><!--<td class="tableLeft"> -->
							<td colspan="2" style="text-align:center" height="25">
							<input type="submit" value="Create" class="gradBtn" />&nbsp;&nbsp;
							<input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
                                                        <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelAddNonHumanDemo()"/></td>
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
