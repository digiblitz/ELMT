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

<!--/*  Program Name    : frmAdvDimensionDetailsList.jsp
 *  Created Date    : September 4, 2006, 4:24 PM
 *  Author          : Punitha.R
 *  Version         : 1.5
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
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmSponsReg.js" type="text/javascript" ></script>
<script src="javascripts/ajax.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->
<script>
var req;
var delObj;
  
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
        setToDefault('selDisplayTypeId');
    }
   
  }  
  
  function displayTypeChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
       var xmlDoc = req.responseXML.documentElement;
	   var xRows= xmlDoc.getElementsByTagName('entry');	   
		var objDDL = document.getElementById("selDisplayTypeId");          	          
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
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }



function deleteURL(param,rowObj) { 	
 if (confirm("Are you sure you want to delete the dimension?")==true){
      var url = "advertise.do?method=delete&dimId="+escape(param); 
	  delObj =  rowObj;
      if (window.XMLHttpRequest) {
           req = new XMLHttpRequest();
       } else if (window.ActiveXObject) {
           req = new ActiveXObject("Microsoft.XMLHTTP");
       }      
       req.onreadystatechange = deleteStateChange;      
       req.open("GET", url, true);
       req.send(null);
 }
}

function deleteStateChange() {
  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
		/* src refers to the input button that was clicked.	
			   to get a reference to the containing <tr> element,
			   get the parent of the parent (in this case case <tr>)
			*/			
			var oRow = delObj.parentElement.parentElement;		

			//once the row reference is obtained, delete it passing in its rowIndex			
			document.all("searchTableId").deleteRow(oRow.rowIndex);		
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>

			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<html:form action="/advSearch.do">

<INPUT TYPE="hidden" name="method" value="search"/>

<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead"><strong></strong> Advertisement: <span class="styleBoldTwo">View Dimension Details </span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The Advertisement dimension details are available are as follows: <br />
	</td>
  </tr>
 
 <tr>
 	<td>
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		   <!--DWLayoutTable-->
		  <tr>
		  <td>
		  <table width=100%>
		  <tr>
		  	<th width="187" height="20" valign="top" class="alignLeft">&nbsp;Color Legends: </th>
			<td colspan="3" valign="middle" class="alignLeft">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
					  <!--
						<td width="50%"><span class="legendOne">&nbsp;</span> &nbsp;Pending.</td>
						-->
					    <td width="50%"><span class="legendTwo">&nbsp;</span></td>
					  </tr>
					</table>			</td>
					</tr>
					</table>
					</td>
		   </tr>
		  <tr>
			<td>
			<table width=100%>
			<tr>		 
		  	<td width="76" height="25" >Media Type:</td>
		    <td width="111" height="25" >

				 <html:select property="selMediaType" styleId="selMediaTypeId" styleClass="selectboxOne" 
                          onchange="retrieveURL('advType',this);" >
						   <option value="">Select One</option>
	                   <html:options collection="DisplayMediaDetails" property="value" labelProperty="label" />
			         </html:select>	
		
				    </td>
			<td width="91" height="25" >Ad Type:</td>
		    <td width="96" height="25" >
		      <html:select property="selDisplayType" styleId="selDisplayTypeId" styleClass="selectboxOne" >
			    <option value="">Select One</option>	
				<%if(request.getAttribute("DisplayTypeDetails")!=null){%>
				    <html:options collection="DisplayTypeDetails" property="value" labelProperty="label" />
				<%}%>
			</html:select>	
		     </td>
		    <td width="96" rowspan="2" ><span class="alignCenter">
		      <input type="submit" name="Submit3" value="Search" class="gradBtn" />
		    </span></td>
			</tr>
			</table>
			</td>
		  </tr>
		   <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<!-- <strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>		 --></td>
		   </tr>
		   <tr>
		   	 <td colspan="8">
			    <table id="searchTableId" width="100%" border="0" align="center" cellpadding="3" cellspacing="1" class="tableInner">
			     <tr>
					<td height="27" class="tblRowHeadTwo">Dimension Name </td>
					<td height="27" class="tblRowHeadTwo">Unit</td>
					<td  width="130" class="tblRowHeadTwo">Edit </td>
					<td width="101" class="tblRowHeadTwo">Delete</td>
			      </tr>
                              
	           <logic:present name="searchAdvtForm" property="results">
                 <bean:size id="size" name="searchAdvtForm" property="results"/>
                 <logic:equal name="size" value="0">
                    <tr>
                       <td colspan="4"  align="center"><B>No Records Found</B></td>
                    </tr>
                 </logic:equal>                 
                 <logic:greaterThan name="size" value="0">
                <logic:iterate id="searchList" name="searchAdvtForm" property="results">
                            <tr>
                            <th height="26" bgcolor="#E3E1D2" class="alignCenter"><a href='advertise.do?method=advDisplayDetails&dimId=<bean:write name="searchList" property="dimensionId"/>'>
                            <bean:write name="searchList" property="dimensionalName"/>
                            </a> </th>
                            <th height="26" bgcolor="#E3E1D2" class="alignCenter"><bean:write name="searchList" property="dimensionalUnit"/> </th>
                            <td bgcolor="#E3E1D2" class="alignCenter">
                             	<input name="editName" type="button" 
		onClick="window.location='advertise.do?method=advEditDetails&dimId=<bean:write name="searchList" property="dimensionId"/>'" class="oneBtn" value="Edit" />	
                            </td>
                            <td bgcolor="#E3E1D2" class="alignCenter">
                          <input type="button" name="deleteName"
                              onClick="window.location='advertise.do?method=advDeleteDetails&dimId=<bean:write name="searchList" property="dimensionId"/>'" value="Delete" class="twoBtn" />		    </td>
                               </tr>	
                    </logic:iterate>                    
               </logic:greaterThan>     
	    </logic:present>
				 
			 </table>		 	</td>
		  </tr>
		  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<!-- <strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>	-->		</td>
		   </tr>
		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
           </tr>
	  </table>
	</td>
</tr>  
</table>
</html:form>
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
