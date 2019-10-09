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

function myvalidate(obj)
{

if(document.frmBreedCharDetAdd.uTypeId.selectedIndex==0)
{alert("Please  select valid Species");
 document.frmBreedCharDetAdd.uTypeId.focus();
 return false;
}
if(document.frmBreedCharDetAdd.charId.selectedIndex==0)
{alert("Please  select valid Characteristic ");
 document.frmBreedCharDetAdd.charId.focus();
 return false;
}
if((document.frmBreedCharDetAdd.charDet.value=="")){
	alert('Please enter valid Detail');
	document.frmBreedCharDetAdd.charDet.focus();
	return false;
}

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
if(document.frmBreedCharDetAdd.status[0].checked==false && document.frmBreedCharDetAdd.status[1].checked==false)
	{
			alert("Please choose the Status");
            return false;
	}

	return true;
}

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

function postData(bProcess){
	//if(document.frmBreedCharDetAdd.uTypeId.value!=""){
		uTypeId=document.frmBreedCharDetAdd.usrTypeId.value;
		charId=document.frmBreedCharDetAdd.retCharacId.value;
		//characteristicName=document.frmBreedCharDetAdd.characteristicName.value;
		reDirectVal=document.frmBreedCharDetAdd.reDirectVal.value;
		radCharId=document.frmBreedCharDetAdd.radCharId.value;
		document.frmBreedCharDetAdd.breedProcess.value = bProcess;	
		document.frmBreedCharDetAdd.method="post";
		document.frmBreedCharDetAdd.action="BreedDetails.do";		
		document.frmBreedCharDetAdd.submit();
	//}
}
function cancel(bProcess){
	      if(confirm("Do you want to Cancel and go back to List Page?"))
		{
		document.frmBreedCharDetAdd.breedProcess.value = bProcess;	
		document.frmBreedCharDetAdd.method="post";
		document.frmBreedCharDetAdd.action="BreedDetails.do";		
		document.frmBreedCharDetAdd.submit();
		 }
		else
		{
			return;
		}
	
}
</script>
</head>
<%

String uTypeId = (String)request.getAttribute("uTypeId");
String retCharId=(String)request.getAttribute("retCharId");

String olduTypeId = (String)request.getAttribute("olduTypeId");
String oldretCharId=(String)request.getAttribute("oldretCharId");


String characteristicName = (String)request.getAttribute("characteristicName");
String reDirectVal = (String)request.getAttribute("reDirectVal");
String radCharId = (String)request.getAttribute("radCharId");


System.out.println("uTypeId ^^^^"+uTypeId);
System.out.println("retCharId ^^^^"+retCharId);

System.out.println("characteristicName ^^^^"+characteristicName);

System.out.println("reDirectVal ^^^ "+reDirectVal);

System.out.println("radCharId ^^^^"+radCharId);


%>

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
			
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->

<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Breed Characteristics - Details: <span class="styleBoldTwo">Create</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">You can <strong>Create</strong> a <strong>New Breed Characteristic - Detail</strong> for all members and non-members Online Services Dashboard, right here!<br />
	</td>
  </tr>



  <tr>
  	<td>
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">

		<form name="frmBreedCharDetAdd" id="frmBreedCharDetAdd" method="post" action="BreedDetails.do" onSubmit="return myvalidate(this);">
		<input type="hidden" name="breedProcess" value="insertCharDet" />
		<input type="hidden" name="characteristicName" id="characteristicName" value="<%=characteristicName%>" />
		<input type="hidden" name="reDirectVal" id="reDirectVal" value="<%=reDirectVal%>" />
		<input type="hidden" name="usrTypeId" id="usrTypeId" value="<%=uTypeId%>" />
		<input type="hidden" name="retCharacId" id="retCharacId" value="<%=retCharId%>" />
		<input type="hidden" name="radCharId" id="radCharId" value="<%=radCharId%>" />

         
				 <tr>
					  <td class="tblDescrp" colspan="2">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
					  </tr>

				       <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>

					<tr>
					<td colspan="2" class="styleError"><strong> Detail already exist </strong></td>
					</tr>
					<%
					}
					%>
				  <tr>
                  <input type="hidden" name="olduTypeId" id="olduTypeId" value="<%=olduTypeId%>" />
		          <input type="hidden" name="oldretCharId" id="oldretCharId" value="<%=oldretCharId%>" />
					<td class="tableLeft">Species:</td>
					<td class="tableRight"><select name="uTypeId" class="selectboxOne" onchange="postData('iniInsert');" >
                                                <option  selected="selected" value="">Select One</option>

                                                <%
									Vector vUType = (Vector)request.getAttribute("displayUserTypeDetails");
									if(vUType!=null && vUType.size()!=0){
										Enumeration eUtype = vUType.elements();
										 //String [] userType = {ID, name };
										while(eUtype.hasMoreElements()){
											String[] strType = (String [])eUtype.nextElement();
											String uTypeID = strType[0];
											String uTypeName = strType[1];
											if(uTypeID.equals(uTypeId)){
                                                                                            
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

				  </tr>

											<tr><td class="tableLeft">Characteristic:</td>
														<td class="tableRight">
							<select name="charId" class="selectboxOne" >
                                <option selected="selected" value="">Select One</option>
								<%
									Vector brdCharList = (Vector)request.getAttribute("displayBreedCharacters");
									if(brdCharList!=null && brdCharList.size()!=0){
                                                                            System.out.println("size"+brdCharList.size());
										Iterator itBrdCharList = brdCharList.iterator();
										while(itBrdCharList.hasNext()){
											String[] charList = (String [])itBrdCharList.next();
											String charID = charList[0];
											String character = charList[1];
											if(retCharId.equals(charID)){
												%>
													<option value="<%=charID%>" selected="selected"><%=character%></option>
												<%
												}
												else{
												%>
													<option value="<%=charID%>"><%=character%></option>
												<%
											}
										}
									}
								%>
								</select>  <span class="asterisk">*
							</span></td>
						  </tr>

				  <tr>
					<td class="tableLeft">Detail:</td>

					<td class="tableRight"><input name="charDet" type="text" class="textboxOne"  maxlength="120" />  <span class="asterisk">*</span></td>

				  </tr>
				   <tr>
					<td class="tableLeft">Status:</td>

                                        <td class="tableRight"><input type="radio" name="status" value="1" />Active <input type="radio" name="status" value="0"  />Inactive <span class="asterisk">*</span></td>
				  </tr>
				   <tr>
					<!--<td class="tableLeft">&nbsp;</td>-->

					<td colspan="2" style="text-align:center" height="25"><input type="submit" class="gradBtn" value="Create" />&nbsp;&nbsp;
					<input name="Clear"  type = "button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
                    <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancel('listBreedCharDetails');" /></td>

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
