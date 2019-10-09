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
function myvalidate(obj){
	if(document.frmBreedEdit.charDesc.value==""){
		alert('Please enter valid Characteristic  ');
		document.frmBreedEdit.charDesc.focus();
		return false;
        }
     if(document.frmBreedEdit.charDesc.value.length>=30){

		           alert('Characteristic cannot have more than 30 characters ');
		           document.frmBreedEdit.charDesc.focus();
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
	       if(document.frmBreedEdit.charstatus[0].checked==false && document.frmBreedEdit.charstatus[1].checked==false)
		{
				alert("Please choose the Status");
                                return false;
		}
                
}
<!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
function clearFields(obj)
{

	for(var i=0;i<obj.elements.length;i++)
	{
		if(obj.elements[i].type=='text')
		{
			obj.elements[i].value = "";
		}

		if(obj.elements[i].type=='radio')
		{
			obj.elements[i].checked = false;
		}
	}
}
<!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
function cancelEditBreed()
    {
        
        var typId = document.frmBreedEdit.uTypeId.value ;
		//var retCharId = document.frmBreedEdit.charId.value ;
	        //var characteristicName = document.frmBreedEdit.characteristicName.value;
		 var reDirectVal = document.frmBreedEdit.reDirectVal.value;
                  if(confirm("Do you want to Cancel and go back to List Page?"))
		{
        
		if(reDirectVal!=null && reDirectVal!="null" && reDirectVal=="frmMgt"){
		
		   strURL = "./BreedDetails.do?breedProcess=charList&uTypeId="+typId+"&reDirectVal="+reDirectVal;

                window.location.href = strURL;
		}else{
                strURL = "./BreedDetails.do?breedProcess=charList&uTypeId="+typId;

                window.location.href = strURL;
    }
    }
		else
		{
			return;
		}
}
</script>
</head>

<!--Dhivya Here(Breed Characeristics Category and Detail mgt)-->
<%
String retCharId = (String)request.getAttribute("retCharId");
//String characteristicName = (String)request.getAttribute("characteristicName");
String reDirectVal = (String)request.getAttribute("reDirectVal");

%>
<!--End Here-->



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
		
		<table width="100%" border="1" align="center" cellpadding="0" cellspacing="5" id="centerTab">
		  
		  <tr>
			<!--<td width="230" class="menuTablePad">-->
			<!-- LEFT MENU STARTS HERE -->
			<!--<%//@ include file = "../../include/menu-roles-leftmenu.jsp" %>
                        </td>-->
			<!-- LEFT MENU ENDS HERE -->
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="1" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Breed Characteristics - Category: <span class="styleBoldTwo">Edit</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Breed Characteristic - Category</strong> created by you as given below. <br />
	</td>
  </tr>
  <tr>
  	<td>
	
		
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  
				<tr>
					  <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
				</tr>
				  <%  String chardesc=""; 
				  String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong> Characteristic already exists</strong></td>
					</tr>
					<%
						chardesc=(String)request.getAttribute("charDesc");
					}
					%>
<!--				  <tr>
					<td class="tableLeft">Breed  Name:</td>
					<td class="tableRight"><input name="breedName" type="text" class="textboxOne" /><span class="asterisk">*</span></td>
				  </tr>	-->
                                  <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
				  <%	
//				        String breedId = request.getParameter(breedId);	
						HLCBreedVO Vobj = (HLCBreedVO) request.getAttribute("Vobj");
						 String charId = Vobj.getBreedId();
						 String charStatus = Vobj.getBreedStatus();
						 String charDesc = Vobj.getBreedDesc();
                                                 String SpecieId = Vobj.getBreedSpecieId();
                                                 String uTypeId = Vobj.getBreedSpecieId();
                                                 String Species =Vobj.getUserTybeName();
                                                 //Debug.print("breedId ="+ breedId);
                                                // Debug.print("breedId ="+ Species);
                                                // Debug.print("breedStatus ="+ breedStatus);
                                                 //Debug.print("breedId ="+ uTypeId);
                                               //Debug.print("breedColor"+breedDesc);
                                             
		%>		  
				  <tr>
					<!--<td class="tableLeft">Breed  Code:</td>
					<td class="tableRight"><input name="breedCode" type="text" class="textboxOne" value="< %=breedCode%>"/><span class="asterisk">*</span></td>-->
				  </tr>
                                  <tr>
                                      <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                      <td class="tableLeft">Species:</td>
					<td class="tableRight"><input name="Species" type="text" class="readOnly" value="<%=Species%>"readonly="true" maxlength="120"/></td>
                                        
                                        <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                  </tr>
                                  <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                  <form name="frmBreedEdit" id="frmBreedEdit" method="post" action="BreedDetails.do" onSubmit="return myvalidate(this);">
		                  <input type="hidden" name="breedProcess" value="Charupdate" />
		<input type="hidden" name="reDirectVal" id="reDirectVal" value="<%=reDirectVal%>" />
                                  <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
				  <tr>
					<td class="tableLeft"> Characteristic:</td>
					<% if(chardesc==""){ %>
					<td class="tableRight"><input name="charDesc" type="text" class="textboxOne" value="<%=charDesc%>" maxlength="29"/>  <span class="asterisk">*</span></td>
					<% } else { %>
					<td class="tableRight"><input name="charDesc" type="text" class="textboxOne" value="<%=chardesc%>" maxlength="29"/>  <span class="asterisk">*</span></td>
					<% } %>
                                        <td ><input name="SpecieId" type="hidden" class="textboxOne" value="<%=SpecieId%>" maxlength="120"/>
					</td>
				  </tr>	
                                        <tr><!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
					<td class="tableLeft">Status:</td>

                                                                 <%       if(Integer.parseInt(charStatus)==1)
                                                                        {
                                                                            %>
                                                                            <td class="tableRight" ><input type="radio" name="charstatus" value="1" checked="true"  />Active
                                                                            <input type="radio" name="charstatus" value="0" />Inactive  <span class="asterisk">*</span></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td  class="tableRight" ><input type="radio" name="charstatus" value="1" />Active
                                                                            <input type="radio" name="charstatus" value="0" checked="true"/>Inactive  <span class="asterisk">*</span></td>
                                                                              <% } %>
				  </tr><!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->

                                   <input type="hidden" value="<%=retCharId%>" name="charId"/>
                                   <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
                                   <input type="hidden" value="<%=uTypeId%>" name="uTypeId"/>
				   <tr>
					 <!--<td class="tableLeft">&nbsp;</td>-->
                                         <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
					 <td colspan = "2" style="text-align:center" height="25"><input type="submit" class="gradBtn" value="Update" />&nbsp;&nbsp;
                                         <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
					 <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelEditBreed()" /></td>
                                         <!-- Start:Breed Mgt For breed  Editing changes dated 12-Mar-2011 -->
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
