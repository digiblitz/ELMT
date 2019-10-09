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
<%@ page import="java.sql.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!--/*  Program Name    : frmAdvDimensionDetailsEdit.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.10
 *  CopyrightInformation:
    (c)2006,digiBlitz Technologies Inc/digiBlitz Technologies (P) Ltd. All rights reserved.
    916 W. Broad Street Suite 205, FallsChurch, VA 22046.
    
    This document is protected by copyright. No part of this document may be reproduced in any form by any means without
    prior written authorization of Sun and its licensors. if any.
 
    The information described in this document may be protected by one or more U.S.patents.foreign patents,or
    pending applications.
 */
--> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmCreateDimenDetail.js" type="text/javascript" ></script>
<script src="javascripts/gen_validatorv2.js" type="text/javascript" ></script>

<script>
var req;
  
function retrieveURL(methodName,param) {  
  if(param.value.length>0 && param.value!="") {
 
 var paramName = param.name;
  var url = null;

  if(paramName=='selMediaType'){
    url = "dropDown.do?method="+escape(methodName)+"&mid="+escape(param.value);     
  }   else if (paramName=='selDisplayType') {
     url = "dropDown.do?method="+escape(methodName)+"&dispId="+escape(param.value);   
   } 

   
      if (window.XMLHttpRequest) {
           req = new XMLHttpRequest();
       } else if (window.ActiveXObject) {
           req = new ActiveXObject("Microsoft.XMLHTTP");
       }
       if(paramName=='selMediaType'){
            req.onreadystatechange = displayTypeChange;
       } else if (paramName=='selDisplayType') {
           req.onreadystatechange = displaySubTypeChange;
       }       
       req.open("GET", url, true);
       req.send(null);

    } else {       
        //setToDefault('selDisplayTypeId');
        //setToDefault('selDisplaySubTypeId');
    }
   
  }  
  
  function displayTypeChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
       var xmlDoc = req.responseXML.documentElement;
	   var xRows= xmlDoc.getElementsByTagName('entry');	   
		var objDDL = document.getElementById("selDisplayTypeId");          	          
		objDDL.innerHTML="";
		setToDefault('selDisplayTypeId');
		setToDefault('selDisplaySubTypeId');
	       for (var i=0; i<xRows.length; i++) {
				var nameNodes = xRows[i].getElementsByTagName("optionValue");
				var valueNodes = xRows[i].getElementsByTagName("optionText");
				if (nameNodes.length > 0 && valueNodes.length > 0) {
				  var theValue = nameNodes[0].firstChild.nodeValue;
				  var theText = valueNodes[0].firstChild.nodeValue;          
				}
				var option = new Option(theText,theValue);
				 try {
					objDDL.add(option,null);     
				 }catch(e){
					objDDL.add(option,-1);
				 }
          }
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

  
  function setToDefault(objName){
    var currObj = document.getElementById(objName);
    currObj.innerHTML="";
	var rootObj =  document.createElement("option");
	var attrib = document.createAttribute("value");
    attrib.value="";
	rootObj.setAttributeNode(attrib);
    newtext=document.createTextNode('Select One');
    rootObj.appendChild(newtext);
    currObj.appendChild(rootObj);

  }
  
  function displaySubTypeChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
       var xmlDoc = req.responseXML.documentElement;
	   var xRows= xmlDoc.getElementsByTagName('entry');
	    var objDDL = document.getElementById("selDisplaySubTypeId");  
	    objDDL.innerHTML="";	
	    for (var i=0; i<xRows.length; i++) {
			var nameNodes = xRows[i].getElementsByTagName("optionValue");
			var valueNodes = xRows[i].getElementsByTagName("optionText");
			if (nameNodes.length > 0 && valueNodes.length > 0) {
			  var theValue = nameNodes[0].firstChild.nodeValue;
			  var theText = valueNodes[0].firstChild.nodeValue;          
			}
			var option = new Option(theText,theValue);
			 try {
				objDDL.add(option,null);     
			 }catch(e){
				objDDL.add(option,-1);
			 }
		  }
		  setDimensionName();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

function setDimensionName(){   
  var dimTypeObj = document.getElementById("selDimensionNameId");
  var subType = document.getElementById("selDisplaySubTypeId");
  var dimName = document.getElementById("txtDimensionalNameId");
    dimName.value=subType.options[subType.selectedIndex].text+" "+dimTypeObj.options[dimTypeObj.selectedIndex].text;

}


function DoSelectBoxValidation() {

var mediaObj = document.getElementById("selMediaTypeId");
var dispTypeObj = document.getElementById("selDisplayTypeId");
var dispSubTypeObj = document.getElementById("selDisplaySubTypeId");
   if(mediaObj.options[mediaObj.selectedIndex].value=""||mediaObj.options[mediaObj.selectedIndex].value.length==0
	   || mediaObj.options[mediaObj.selectedIndex].value==null){
   
      alert(mediaObj.name+": Please Select one option ");
	  return false;
   } else  if(dispTypeObj.options[dispTypeObj.selectedIndex].value=""||dispTypeObj.options[dispTypeObj.selectedIndex].value.length==0
	   || dispTypeObj.options[dispTypeObj.selectedIndex].value==null){
   
      alert(dispTypeObj.name+": Please Select one option ");
	  return false;
   } else if(dispSubTypeObj.options[dispSubTypeObj.selectedIndex].value=""||dispSubTypeObj.options[dispSubTypeObj.selectedIndex].value.length==0
	   || dispSubTypeObj.options[dispSubTypeObj.selectedIndex].value==null){
   
      alert(dispSubTypeObj.name+": Please Select one option ");
	  return false;
   }

  return true;


}

function setMethod() {
  var canObj = document.getElementById("MethodId");
  canObj.value="updateCancel";
 
  document.forms[0].submit();
}


</script>


<!--=====================================================================
-->
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">Edit Dimension Details </span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
    <strong>
	         Please create the required Dimension Details for all the advertisements that are to be placed in all the events . </strong><br />
		<br />
		Select a Dimension Name given below and fill-in the relevant dimension details for the same depending on the media type. <br />
	<br /></td>
  </tr>
  <tr>
  	<td>

<html:form method="post" action="/advertise" enctype="multipart/form-data">

<html:hidden property="dimensionId"/>

	<INPUT TYPE="hidden" name="method" value="update" id="MethodId"/>


			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				<tr>
				  <td colspan="2" class="tblRowHead">Required fields are marked with an asterisk*</td>
				</tr>
				  <tr>
					<td colspan="2" class="tblRowHead">Create A Dimension Detail:</td>
				  </tr>
				  <tr>
				     <td class="tableLeft">Select A Media Type:</td>
				     <td class="tableRight">

					 <html:select property="selMediaType" styleId="selMediaType" styleClass="selectboxOne" 
                          onchange="retrieveURL('advType',this);">
						   <option selected="selected">Select One</option>
	                   <html:options collection="DisplayMediaDetails" property="value" labelProperty="label" />
			         </html:select>					
				 	 <span class="asterisk">*</span>
					 </td>
		     	  </tr>
				   
				   <tr>
					<td class="tableLeft">Advertisement Type:</td>
					<td class="tableRight">
			<html:select property="selDisplayType" styleId="selDisplayTypeId" styleClass="selectboxOne" onchange="retrieveURL('advSubType',this);">
			   
				<%if(request.getAttribute("DisplayTypeDetails")!=null){%>
				    <html:options collection="DisplayTypeDetails" property="value" labelProperty="label" />
				<%} else {%>
                                  <option selected="selected">Select One</option>	
                                <%}%>
			</html:select>			
				 	 <span class="asterisk">*</span>					</td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Advertisement Sub-Type:</td>
					<td class="tableRight">
                        <html:select property="selDisplaySubType" styleId="selDisplaySubTypeId" styleClass="selectboxOne" onchange="setDimensionName();">
			  	
				<%if(request.getAttribute("DisplaySubTypeDetails")!=null){%>
				    <html:options collection="DisplaySubTypeDetails" property="value" labelProperty="label" />
				<%} else {%>
                                  <option selected="selected">Select One</option>	
                                <%}%>
			</html:select>	                
                                        
					
				 	 <span class="asterisk">*</span>					</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Dimension Type:</td>
					<td class="tableRight">
                  	<html:select property="selDimensionType" styleId="selDimensionNameId" styleClass="selectboxOne" onchange="setDimensionName();">
					<%if(request.getAttribute("DimensionDetails")!=null){%>
					   <html:options collection="DimensionDetails" property="value" labelProperty="label" />
                     <%}%>
					</html:select>
				 	 <span class="asterisk">*</span>					</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Dimensional Name:</td>
					<td class="tableRight">
					<html:text property="txtDimensionalName" styleId="txtDimensionalNameId" styleClass="textboxOne" 
					size="20" readonly="true" /></td>
				  </tr>
				  
				  <tr>
					<td class="tableLeft">Advertisement Height:</td>
					<td class="tableRight">
					<html:text property="txtAdvHeight" styleClass="textboxOne" maxlength="20" styleId="txtAdvHeight" size="25" />
				 	 <span class="asterisk">*</span>					</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Advertisement Width:</td>
					<td class="tableRight">
					<html:text property="txtAdvWidth" styleClass="textboxOne" maxlength="20" size="25" />
				    <span class="asterisk">*</span>					</td>
				  </tr>
				  <tr>
					<td class="tableLeft">Dimensional Unit:</td>
					<td class="tableRight">
					<html:text property="txtDimensionalUnit" styleId="txtDimensionalUnit" maxlength="20" styleClass="textboxOne" size="25"  />
                    <span class="asterisk">*</span></td>
				  </tr>
				  <tr>
					<td class="tableLeft">Upload Dimension Sample:</td>
					<td class="tableRight">
					<html:file property="fileUpload"  styleClass="textboxOne" size="25" />
				  </td>
				  </tr>
			      <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" name="method" value="update" class="gradBtn" />&nbsp;
					<input type="button"  value="Cancel" class="gradBtn" onClick="window.location='advSearch.do?method=initSearch'"/>					</td>
				  </tr>
			</table>
			
</html:form>
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


�

</body>
</html>
<script language="JavaScript" type="text/javascript">
var frmvalidator = new Validator("formAdvertisement");
frmvalidator.addValidation("txtAdvHeight","req","Please enter the Advertisement height"); 
frmvalidator.addValidation("txtAdvHeight","maxlen=20");
// frmvalidator.addValidation("txtAdvHeight","numeric");
frmvalidator.addValidation("txtAdvWidth","req","Please enter the Advertisement width"); 
frmvalidator.addValidation("txtAdvWidth","maxlen=20");
// frmvalidator.addValidation("txtAdvWidth","numeric");
frmvalidator.addValidation("txtDimensionalUnit","req","Please enter the dimension unit");  
frmvalidator.addValidation("txtDimensionalUnit","maxlen=20");
// frmvalidator.addValidation("txtDimensionalUnit","numeric");
frmvalidator.addValidation("selMediaType","dontselect=0","Select the Media Type");
frmvalidator.addValidation("selDisplayType","dontselect=0","Select the Advertisement Type");
frmvalidator.addValidation("selDisplaySubType","dontselect=0","Select Advertisement Sub-Type");
</script>