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
<!--
* Program Name     :   frmMemEditSpecies.jsp
 * Created Date     :   April 8, 2011
 * Author           :   Kalai
 * Version          :   1.0
This JSP file is used to Edit the new Species
-->
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
	if(document.frmBreedEdit.SpeciesName.value==""){
		alert('Please enter valid Species');
		document.frmBreedEdit.SpeciesName.focus();
		return false;
        }
                /* if(document.frmBreedEdit.speciesStatus[0].checked==false && document.frmBreedEdit.speciesStatus[1].checked==false)
		{
				alert("Please choose Status");
                                return false;
		}
                 vFlag = false;*/

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
	                 if(document.frmBreedEdit.speciesStatus[0].checked==false && document.frmBreedEdit.speciesStatus[1].checked==false)
		{
				alert("Please choose the Status");
                                return false;
		}
}

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

function cancelEditBreed()
    {
        
       var redirectVal=document.frmBreedEdit.reDirectVal.value;
       if(confirm("Do you want to Cancel and go back to List Page?"))
	{
   if(redirectVal!=null && redirectVal!="null" && redirectVal=="frmMgt"){     
               strURL = "./BreedDetails.do?breedProcess=speciesView&redirectVal="+redirectVal;
}else{
 strURL = "./BreedDetails.do?breedProcess=speciesView";
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
	Species: <span class="styleBoldTwo"> Edit</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">You can <strong>Edit/Update</strong> the <strong>Species</strong> created by you as given below. <br />
	</td>
  </tr>
  <tr>
  	<td>
	
		
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  
				<tr>
					  <td colspan="2" class="tblDescrp">Fields marked with an asterisk (<span class="asterisk">*</span>) are required.</td>
				</tr>
				  <%  String speciename="";
				  String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong>Species already exists</strong></td>
					</tr>
					<%
						speciename=(String)request.getAttribute("SpeciesName"); 
					}
					%>
				  
                                 
				  <%	
//				        String breedId = request.getParameter(breedId);	
						
                                              String reDirectVal = (String)request.getAttribute("reDirectVal");

                                                    HLCBreedVO donObj = (HLCBreedVO) request.getAttribute("Vobj");
                                                    System.out.println("voj"+donObj);

                                                    String speciesId = donObj.getUserTypeId();
                                                    System.out.println("speciesId"+speciesId);
                                                    String SpeciesName = donObj.getUserTybeName();
                                                    System.out.println("SpeciesName"+SpeciesName);
                                                    String speciesStatus = donObj.getUserTypeStatus();
                                                     //Debug.print("breedId ="+ breedId);
                                                    System.out.println("speciesStatus"+speciesStatus);
                                                    // Debug.print("breedId ="+ Species);
                                                    // Debug.print("breedStatus ="+ breedStatus);
                                                     //Debug.print("breedId ="+ uTypeId);
                                                   //Debug.print("breedColor"+breedDesc);HLCBreedVO Vobj = (HLCBreedVO) request.getAttribute("Vobj");
                                           

                                             
		%>		  
				 
                                 
                                  <form name="frmBreedEdit" id="frmBreedEdit" method="post" action="BreedDetails.do" onSubmit="return myvalidate(this);">
		                  <input type="hidden" name="breedProcess" value="updateSpecies" />
						  <input type="hidden" name="reDirectVal" value="<%=reDirectVal%>" />
                                 
                                  <input name="SpeciesId" type="hidden" class="textboxOne" value="<%=speciesId%>" maxlength="120"/>
				  <tr>
					<td class="tableLeft">Species:</td>
					<% if(speciename==""){ %>
					<td class="tableRight"><input name="SpeciesName" type="text" class="textboxOne" value="<%=SpeciesName%>" maxlength="50"/>  <span class="asterisk">*</span></td>
					<% }
					else{ %>
					<td class="tableRight"><input name="SpeciesName" type="text" class="textboxOne" value="<%=speciename%>" maxlength="50"/>  <span class="asterisk">*</span></td>
					<% } %>
				  </tr>	
                                        <tr>
					<td class="tableLeft">Status:</td>

                                                                 <%       if(Integer.parseInt(speciesStatus)==1)
                                                                        {
                                                                            %>
                                                                            <td class="tableRight" ><input type="radio" name="speciesStatus" value="1" checked="true"  />Active
                                                                            <input type="radio" name="speciesStatus" value="0" />Inactive  <span class="asterisk">*</span></td>
                                                                               <%
                                                                            }
                                                                else
                                                                    {
                                                                    %>
                                                                    <td  class="tableRight" ><input type="radio" name="speciesStatus" value="1" />Active
                                                                            <input type="radio" name="speciesStatus" value="0" checked="true"/>Inactive  <span class="asterisk">*</span></td>
                                                                              <% } %>
				  </tr>

                                   <input type="hidden" value="<%=speciesId%>" name="speciesId"/>
                                   
                                 
				   <tr>
					 <!--<td class="tableLeft">&nbsp;</td>-->
                                         
					 <td colspan = "2" style="text-align:center" height="25"><input type="submit" class="gradBtn" value="Update" />&nbsp;&nbsp;
                                         <input name="button" type="button" class="gradBtn" value="Clear" onClick = "clearFields(this.form)"/>&nbsp;&nbsp;
					 <input name="button" type="button" class="gradBtn" value="Cancel" onClick ="cancelEditBreed()" /></td>
                                         
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
