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
<%@ page import="com.hlcmrm.util.HLCBreedVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<script language="javascript">
    <!-- Start:Breed Mgt For breed  Addition changes dated 10-Mar-2011 -->
function myvalidate(obj)
{
	//if((document.frmBreedAdd.breedDesc.value=="")||(document.frmBreedAdd.breedDesc.value.indexOf(' ')==0)){
if(document.frmBreedAdd.uType.selectedIndex==0)
{alert("Please select valid Species");
 document.frmBreedAdd.uType.focus();
 return false;
}
//Start:[BreedMgt] For breed Listing Addition, Editing and Deletion changes dated 10-Mar-2011
	if((document.frmBreedAdd.breedDesc.value=="")){
		alert('Please enter valid Breed');
		document.frmBreedAdd.breedDesc.focus();
		return false;
	}
	/*if(document.frmBreedAdd.breedDesc.value.indexOf('  ')!==-1){
		alert("Please Enter The Breed Description");
		document.frmBreedAdd.breedDesc.focus();
		return false;

	}*/
         vFlag = false;

	for(var i=0;i<obj.elements.length;i++)
          {
		if(obj.elements[i].type=='text')
		{
			if((obj.elements[i].value.indexOf('	') == 0) || (obj.elements[i].value.lastIndexOf('	') == (obj.elements[i].value.length-1)) ||
				(obj.elements[i].value.indexOf(' ') == 0) || (obj.elements[i].value.lastIndexOf(' ') == (obj.elements[i].value.length-1)))
			{
				obj.elements[i].value = obj.elements[i].value.trim();
				vFlag = true;
			}
		}
	}

	if(vFlag==true)
	{
		alert("Leading and Trailing spaces will be trimmed. Please Submit again");
		return false;
	}
        if(document.frmBreedAdd.status[0].checked==false && document.frmBreedAdd.status[1].checked==false)
		{
				alert("Please choose the Status");
                                return false;
		}

	return true;
}
<!-- Start:Breed Mgt For breed  Editing changes dated 10-Mar-2011 -->
function clearFields(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{

		if(obj.elements[i].type=='text')
		{

			obj.elements[i].value = "";
		}
		if(obj.elements[i].type=='select-one')
		{

			obj.elements[i].value = "";
		}

		if(obj.elements[i].type=='radio')
		{
			obj.elements[i].checked = false;
		}
	}
}
<!-- Start:Breed Mgt For breed  Editing changes dated 10-Mar-2011 -->
function cancelEditBreed()
    {
	var redirectVal=document.frmBreedAdd.reDirectVal.value;
        var typId = document.frmBreedAdd.speciesId.value ;
		var retSpeciesName = document.frmBreedAdd.retSpeciesName.value ;
                 if(confirm("Do you want to Cancel and go back to List Page?"))
	{
		
            if(redirectVal!=null && redirectVal!="null" && redirectVal=="frmMgt"){
                strURL = "./BreedDetails.do?breedProcess=BreedView&uTypeId="+typId+"&reDirectVal="+redirectVal+"&retSpeciesName="+retSpeciesName;
				}else{
				  strURL = "./BreedDetails.do?breedProcess=BreedView&uTypeId="+typId;
				}
                window.location.href = strURL;
                }
	else
	{
		return;
	}
    }
</script>
</head>
<!-- Start:Breed Mgt For breed  Addition changes dated 10-Mar-2011 -->
<%

String uTypeId = (String)request.getAttribute("uTypeId");
String reDirectVal = (String)request.getAttribute("reDirectVal");
String retSpeciesName = (String)request.getAttribute("retSpeciesName");


%>
<!-- Start:Breed Mgt For breed  Addition changes dated 10-Mar-2011 -->
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
		
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<!--<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<!--<%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                        </td>
			<!-- LEFT MENU ENDS HERE -->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Breeds: <span class="styleBoldTwo">Create</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">You can <strong>Create</strong> a <strong>New Breed</strong> for all members and non-members Online Services Dashboard, right here! <br />
	</td>
  </tr>
    
                                              

  <tr>
  	<td>
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
	<!-- Start:Breed Mgt For breed  Editing changes dated 10-Mar-2011 -->
		<form name="frmBreedAdd" id="frmBreedAdd" method="post" action="BreedDetails.do" onSubmit="return myvalidate(this);">
		<input type="hidden" name="breedProcess" value="insert" />
		<input type="hidden" name="reDirectVal" value="<%=reDirectVal%>" />
		<input type="hidden" name="retSpeciesName" value="<%=retSpeciesName%>" />
		
		<input type="hidden" name="speciesId" id="speciesId" value="<%=uTypeId%>"/>
	
				 <tr>
					  <td class="tblDescrp" colspan="2">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
					  </tr> 
			
				       <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					
					<tr>
					<td colspan="2" class="styleError"><strong>Breed already exists</strong></td>
					</tr>
					<%
					}
					%>
				  <tr>
					<!--<td class="tableLeft">Breed  Code:</td>
					<td class="tableRight"><input name="breedCode" type="text" class="textboxOne" /><span class="asterisk">*</span></td>-->
				  </tr>
                                   <tr><!-- Start:Breed Mgt For breed Addition, Editing and Deletion changes dated 12-Mar-2011 -->
					<td class="tableLeft">Species:</td>
					<td class="tableRight"><select name="uType" class="selectboxOne">
                                                <option  selected="selected" value="">Select One</option>

                                                <% 
									Vector vUType = (Vector)session.getAttribute("displayUserTypeDetails");
									if(vUType!=null && vUType.size()!=0){
										Enumeration eUtype = vUType.elements();
										 //String [] userType = {ID, name };
										while(eUtype.hasMoreElements()){
											String[] strType = (String [])eUtype.nextElement();
											String uTypeID = strType[0];
											String uTypeName = strType[1];
											if(uTypeID.equalsIgnoreCase(uTypeId)){
                                                                                           // System.out.println("k"+uTypeId);
                                                                                            // System.out.println("k"+uTypeID);
										%>
										  <option  value="<%=uTypeID%>" selected="selected" ><%=uTypeName%></option>
										<%
											}
											else{
											%>
											 <option  value="<%=uTypeID%>" ><%=uTypeName%></option>
											<%
											}
										}
										}
								%>
								</select>  <span class="asterisk">*</span></td>
					<!-- Start:Breed Mgt For breed  Editing  changes dated 12-Mar-2011 -->
				  </tr>

				  <tr>
					<td class="tableLeft">Breed:</td>
                                        <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
					<td class="tableRight"><input name="breedDesc" type="text" class="textboxOne"  maxlength="120" />  <span class="asterisk">*</span></td>
                                        <!-- Start:Breed Mgt For breed Addition, Editing and Deletion changes dated 12-Mar-2011 -->
					
				  </tr>	
				   <tr><!-- Start:Breed Mgt For breed  Addition changes dated 12-Mar-2011 -->
					<td class="tableLeft">Status:</td>
                                        <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                        <td class="tableRight"><input type="radio" name="status" value="1" />Active <input type="radio" name="status" value="0"  />InActive  <span class="asterisk">*</span></td>
				  </tr>	
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->
                                        <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
					<td colspan = "2" style="text-align:center" height="25"><input type="submit" class="gradBtn" value="Create" />&nbsp;&nbsp;
					<input name="Reset"  type = "button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
                                        <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelEditBreed()" /></td>
                                        <!-- Start:Breed Mgt For breed  Addition changes dated 12-Mar-2011 -->
				  </tr></form>
			</table>
			
		
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
