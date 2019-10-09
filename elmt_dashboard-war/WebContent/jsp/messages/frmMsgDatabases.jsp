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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.message.actionform.MessageDBActionForm"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<script>
var req;
  
function fireURL(param) {  

  if(param.value.length>0 && param.value!="") {	
	  var url="memberInfo.do?method=getStateZip&hlcAreaId="+escape(param.value); 
      if (window.XMLHttpRequest) {
           req = new XMLHttpRequest();		   
       } else if (window.ActiveXObject) {
           req = new ActiveXObject("Microsoft.XMLHTTP");
       }
     
       req.onreadystatechange = displayTypeChange;
      
       req.open("GET", url, true);
       req.send(null);
    } 
  }
  
   function displayTypeChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
       var xmlDoc = req.responseXML.documentElement;
	   var xRows= xmlDoc.getElementsByTagName('entry');
	   	var stateId = document.getElementById("stateId");
		var zipId   = document.getElementById("zipId"); 
		setToDefault('stateId','Select State');
		setToDefault('zipId','Select Zip Code');
	       for (var i=0; i<xRows.length; i++) {
				var stateIds = xRows[i].getElementsByTagName("stateId");
				var stateNames = xRows[i].getElementsByTagName("stateName");
				var stateCodes = xRows[i].getElementsByTagName("stateCode");
				var zipCodeFroms = xRows[i].getElementsByTagName("zipCodeFrom");
				var zipCodeTos = xRows[i].getElementsByTagName("zipCodeTo");
				var theStateValue = stateIds[0].firstChild.nodeValue;
				var theStateText = stateNames[0].firstChild.nodeValue;  
				var option = new Option(theStateText,theStateValue);
				 try {
					stateId.add(option,null);     
				 }catch(e){
					stateId.add(option,-1);
				 }
				var zipText =(zipCodeFroms[0].firstChild.nodeValue+"-"+zipCodeTos[0].firstChild.nodeValue);
                var option = new Option(zipText,zipText);
				 try {
					zipId.add(option,null);     
				 }catch(e){
					zipId.add(option,-1);
				 }
          }
       }
      }
    }
 
 function setToDefault(objName,nodeName){
    var currObj = document.getElementById(objName);
    currObj.innerHTML="";
	var rootObj =  document.createElement("option");
	var attrib = document.createAttribute("value");
    attrib.value="";
	rootObj.setAttributeNode(attrib);
    newtext=document.createTextNode(nodeName);
    rootObj.appendChild(newtext);
    currObj.appendChild(rootObj);

  }

function Validate(){
	var areaObj = document.getElementById("hlcAreaId");
	var stateObj = document.getElementById("stateId");
	var zipObj   = document.getElementById("zipId");
	var mTypeObj = document.getElementById("memberTypeId");
	var mStatusObj = document.getElementById("statusId");
	if(areaObj.options[areaObj.options.selectedIndex].value=="") {
		alert("Select the Area");
		areaObj.focus();
		return false;
	}
	if(stateObj.options[stateObj.options.selectedIndex].value=="") {
		alert("Select the State");
		stateObj.focus();
		return false;
	}
	if(zipObj.options[zipObj.options.selectedIndex].value=="") {
		alert("Select the zip code");
		zipObj.focus();
		return false;
	}
	if(mTypeObj.options[mTypeObj.options.selectedIndex].value=="") {
		alert("Select the Member Type");
		mTypeObj.focus();
		return false;
	}
	if(mStatusObj.options[mStatusObj.options.selectedIndex].value=="") {
		alert("Select the Membership Status");
		mStatusObj.focus();
		return false;
	}

	return true;
}
</script>
</head>

<body>
<table width="760" border="0" align="center" cellpadding="0" cellspacing="0" id="mainTab">
  <tr>
    <td class="alignTop"><!-- HEADER STARTS HERE -->
    <%@ include file = "../../include/header.jsp" %>
        <!-- HEADER ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="infoBar"><!-- INFO BAR STARTS HERE -->
        <%@ include file = "../../include/infobar.jsp" %>
        <!-- INFO BAR ENDS HERE -->
    </td>
  </tr>
  <tr>
    <td class="tableCommonBg">
	<table width="760" border="0" align="center" cellpadding="0" cellspacing="5" id="centerTab">
      <tr>
        <td width="230" class="menuTablePad"><!-- LEFT MENU STARTS HERE -->
              <!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			  <%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
              <!-- LEFT MENU ENDS HERE -->
        </td>
        <td width="500" class="subDeptTablePad"><!-- CONTENTS START HERE -->
              <table  border="0" cellpadding="0" cellspacing="0"  align="center" class="formLayout">
                <tr>
                  <td colspan="5" class="tblMainHead"> Messages: <span class="styleBoldTwo">Search &amp; List Database Addresses   </span></td>
                </tr>
                <tr>
                  <td colspan="5" class="tblDescrp">                  <strong>STEP <span class="styleBoldOne">1</span> OF <span class="styleBoldOne">2</span></strong><br />
--------------------------------------------------------------------------------------------------------------------------<br />
                  You can search for Addresses from our database and <strong>download</strong> them for your personal use. You would be charged an amount of <span class="styleBoldOne">$0.15 per address downloaded</span>. However, we would  send you an URL to your EMail address for downloading the database once your request has been verified and approved. </td>
                </tr>
				<tr>
                  <td colspan="5">
                  <html:form method="post" action="/msgDB.do" onsubmit="return Validate();">
				    <html:hidden property="method" value="viewDbList"/>
				  		<table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                            <tr>
                              <th height="20" colspan="5" valign="middle" class="tblRowHead">Select A Search Filter </th>
                            </tr>
                            <tr>
                              <th width="147" height="20" valign="middle" align="center" class="listCellBg">
                        <html:select property="useaArea" styleId="hlcAreaId" styleClass="selectboxOne" 
							   onchange="fireURL(this);">
						  <option selected="selected" value=""/>Select Area</option>
					      <html:options collection="HLC_AREA" property="value" labelProperty="label" />
						</html:select>                           </th>
                              <td colspan="3" valign="middle" align="center" class="listCellBg">
                                <select name="select2" id="stateId" class="selectboxOne">
                                  <option>Select State</option>
                                 </select>                             </td>
                              <td width="189" valign="middle" align="center" class="listCellBg">
                                <select name="zipCode" id="zipId" class="selectboxOne">
                                  <option value="">Select Zip Code</option>
								 </select>                              </td>
                            </tr>
							</table>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" align="center">
                            <tr>
                               <td colspan="3" valign="middle" align="center" class="listCellBg">
                     <html:select property="memberType" styleId="memberTypeId" styleClass="selectboxOne" onchange="">
						  <option selected="selected" value=""/>Member Type</option>
					      <html:options collection="MEMBER_SHIP" property="value" labelProperty="label" />
						</html:select>                                 </td>
                              <td width="50%" valign="middle" align="center" class="listCellBg">
                        <html:select property="status" styleId="statusId" styleClass="selectboxOne" onchange="">
						  <option selected="selected" value=""/>Membership Status</option>
					      <html:options collection="MEMBER_SHIP_STATUS" property="value" labelProperty="label" />
						</html:select>                               </td>
                            </tr>
                            
                            <tr>
                              <td height="30" colspan="5" align="center" valign="middle" class="listCellBg">
                                  <div align="center">
                                    <input name="Submit22" type="submit" class="gradTwoBtn" value="SEARCH" />                              
                                  </div></td>
                            </tr>
                        </table>
				  
				  </html:form>
				  
				  <table border="0" cellpadding="0" align="center" cellspacing="1" class="formLayout"> 
				   <tr>
				    <td colspan="5" class="tblMainHead">Search Results</td>
                   </tr>
				  <tr>
                       <td width="106" height="27" class="tblRowHead">First Name </td>
                        <td width="97" class="tblRowHead">Last Name </td>
                        <td width="150" class="tblRowHead">Address</td>
                        <td width="134" class="tblRowHead">City</td>
                    </tr>
					  <html:form method="post" action="/msgDB.do" >
						  <html:hidden property="method" value="showPayment"/>
                       <logic:present name="messageDBForm" property="dbLists">
							 <bean:size id="size" name="messageDBForm" property="dbLists"/>
							 <logic:equal name="size" value="0">
									<tr>
									   <td colspan="5"  align="center"><B>No Records Found</B></td>
									</tr>
						 <tr>
                        <td height="30" colspan="7">
                          <div align="center">
                            <input name="Submit222" type="submit" class="gradBtn" disabled="disabled" value="Request Download" style="width:115px;"/>
                            </div></td>
                      </tr>
							 </logic:equal>                 
							 <logic:greaterThan name="size" value="0">
								<logic:iterate id="dbList" name="messageDBForm" property="dbLists">								  
										  <input type="hidden" name="userId" value='<bean:write name="dbList" property="userId"/>'/>
											<tr>											
											<td class="listCellBg"><bean:write name="dbList" property="firstName"/></td>
											<td class="listCellBg"><bean:write name="dbList" property="lastName"/></td>
											<td class="listCellBg"><bean:write name="dbList" property="address"/></td>
											<td class="listCellBg"><bean:write name="dbList" property="city"/></td>	
											</tr>
										
									</logic:iterate>
						 <tr>
                        <td height="30" colspan="7">
                          <div align="center">
                            <input name="Submit222" type="submit" class="gradBtn" value="Request Download" style="width:115px;"/>
                            </div></td>
                      </tr>
							 </logic:greaterThan>  
							 <input type="hidden" name="recordCount" value='<%=size%>'/>
						</logic:present>
			
                 <%
				     StringBuffer buffer = new StringBuffer();
					 boolean checkFlag = false;
					 MessageDBActionForm msgForm = (MessageDBActionForm)request.getAttribute("messageDBForm");
				  
				    if(isNotNull(msgForm.getZipCode())) {
					     buffer.append("zipcode="+msgForm.getZipCode());
						 checkFlag = true;
					}
					if(isNotNull(msgForm.getMemberType())) {
						if(checkFlag){
						   buffer.append(","); 
						 }
					     buffer.append("memberType="+msgForm.getMemberType());
						 checkFlag = true;
					} 
					if(isNotNull(msgForm.getStatus())) {
                          if(checkFlag){
						   buffer.append(","); 
						 }
					     buffer.append("status="+msgForm.getStatus());
					 }
                 %>
				  <input type="hidden" name="query" value="<%=buffer%>"/> 
			</html:form>	
					 
                      <tr>
                        <td height="19" colspan="7">&nbsp;</td>
                      </tr>
                  </table>
				  
				  
              </table>
          <!-- CONTENTS END HERE -->
    </table></td>
  </tr>
  <tr>
    <td class="footerBg"><!-- FOOTER STARTS HERE -->
        <%@ include file = "../../include/footer.jsp" %>
        <!-- FOOTER ENDS HERE -->
    </td>
  </tr>
</table>
</body>
</html>
<%!

   private boolean isNotNull(String data) {
        return (data!=null && data.trim().length()>0) ? true :false;
    }

%>
