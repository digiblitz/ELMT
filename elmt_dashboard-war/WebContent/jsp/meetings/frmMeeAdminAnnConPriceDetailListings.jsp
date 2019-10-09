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
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<script language="javascript">


var req;
  
function retrieveURL(methodName,param) {  
  if(param.value.length>0 && param.value!="") {
 
 var paramName = param.name;
  var url = null;

  if(paramName=='eventTypeId'){
    url = "meeAjaxPriceMasterList.do?method="+escape(methodName)+"&meeCatagoryTyp="+escape(param.value);     
  } /*  else if (paramName=='selDisplayType') {
     url = "dropDown.do?method="+escape(methodName)+"&dispId="+escape(param.value);   
   } */
		  if (window.XMLHttpRequest) {
			   req = new XMLHttpRequest();
			   
		   } else if (window.ActiveXObject) {
			   req = new ActiveXObject("Microsoft.XMLHTTP");
		   }
		   
		   if(paramName=='eventTypeId'){
				req.onreadystatechange = displayTypeChange;
		   } //else if (paramName=='selDisplayType') {
			 //  req.onreadystatechange = displaySubTypeChange;
		  // }       
		   req.open("GET", url, true);
		   req.send(null);

    } else {       
        setToDefault('specId');
       // setToDefault('selDisplaySubTypeId');
    }
  }  
  
  function displayTypeChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response  
       var xmlDoc = req.responseXML.documentElement;
	   var xRows= xmlDoc.getElementsByTagName('entry');	   
		var objDDL = document.getElementById("specId");          	          
		objDDL.innerHTML="";
		setToDefault('specId');
		//setToDefault('selDisplaySubTypeId');
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
  

	function validatePriceList(){
		if(document.frmMeePriceMaster.eventTypeId.value==""){
			alert("Please select any one Event Type.");
			document.frmMeePriceMaster.eventTypeId.focus();
			return false;
		}
		
		if(document.frmMeePriceMaster.specId.value==""){
			alert("Please select any one Specification.");
			document.frmMeePriceMaster.specId.focus();
			return false;
		}

		
		return true;
	}

</script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 

</head>
<%
	String eventTypeIdVal = (String) request.getAttribute("eventTypeId");
	String specIdVal = (String) request.getAttribute("specId");
	if(eventTypeIdVal==null) eventTypeIdVal = "";
	if(specIdVal==null) specIdVal = "";
	

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
					<td colspan="5" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Price Detail Master </span></td>
				  </tr>
				  
				 
				 <tr>
					<td>
					
						 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
						  <tr>
							<td colspan="6">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								   <form name="frmMeePriceMaster" id="frmMeePriceMaster" action="addPriceMasterList.do" method="post" onsubmit="return validatePriceList();"  >
									<input type="hidden" name="priceMasterProcess" value="selectPriceList">
								   <tr>
									  <th width="90" height="25" class="tableSpan"><strong>Event Type:</strong>&nbsp;</th>
								    <th class="tableSpan" colspan="2"><span class="alignLeft">
	<select style="width:200px;" name="eventTypeId" id="eventTypeId" class="selectboxOne" onchange="retrieveURL('advType',this);" >
													<option selected="selected" value="">Select One</option>
													<%
													Vector eventTypeList = (Vector)request.getAttribute("eventTypeList");
													if(eventTypeList!=null && eventTypeList.size()!=0){
													Enumeration itEventType = eventTypeList.elements();
													while(itEventType.hasMoreElements()){
														String[] eduDet =(String[])itEventType.nextElement();
														String ETid = eduDet[0];
														String activityName1 = eduDet[1];
														if(eventTypeIdVal.equals(ETid)){
													
													%>
													
													<option value="<%=ETid%>" selected="selected"><%=activityName1%></option>
												 <%
												 }
												 else{
												 %>
												 <option value="<%=ETid%>"><%=activityName1%></option>
												 <%
												 }
										  }
										}
										  %>
								    </select></span><span class="asterisk">*</span>
									 </th>
								  </tr>
								   <tr>
								      <th width="90" height="25" class="tableSpan"><strong>Specification:</strong>&nbsp;</th>
									  <th width="337" class="tableSpan"><span class="alignLeft">
									  <select style="width:310px;" name="specId" id="specId" class="selectboxOne" >
									  	<option selected="selected" value="">Select One</option>
											<%
														Vector specList = (Vector)request.getAttribute("specList");
														if(specList!=null && specList.size()!=0){
														Enumeration it = specList.elements();
														while(it.hasMoreElements()){
														String[] speciList =(String[])it.nextElement();
														String specificationId = speciList[0];
														String specificationName = speciList[1];
														//String [] speciList  = {specificationId,specificationName};
														if(specIdVal.equals(specificationId)){
																				%>
														<option value="<%=specificationId%>" selected="selected"><%=specificationName%></option>
													 <%
													 }
													 else{
													 %>
													 <option value="<%=specificationId%>"><%=specificationName%></option>
													 <%
													 }
											  }
											}
											  %>							
									 </select>		</span>	<span class="asterisk">*</span>						  </th>
									  <th width="65" class="tableSpan">
									 <input type="submit" name="search" value="Search" class="gradBtn" /></th>
								     </tr>
								  </form>
								</table>							</td>
						 </tr>
						 <tr>
						 	<td colspan="6" style="border-bottom:1px solid #999;">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>
									
									<!--
									<td colspan="3" valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
									<span class="legendOne">&nbsp;</span> &nbsp;Delete.
									</td>
									-->
							      </tr>
								</table>
								
							</td>
						 </tr>
						  <tr>
							<td width="123" height="27" class="tblRowHead">User Type </td>
							<td width="102" class="tblRowHead">Normal Price </td>
							<td width="133" class="tblRowHead">After Due Date Price </td>
							<td width="54" class="tblRowHead">Edit</td>
							<td width="52" class="tblRowHead">View</td>
						   </tr>

					<%
		   		
					 ArrayList priceListView=(ArrayList)request.getAttribute("priceListView");
					 if(priceListView!=null && priceListView.size()!=0){ 
					 Iterator itPrice = priceListView.iterator();
					  while(itPrice.hasNext()){
						    String [] priceDetail  = (String[]) itPrice.next();
							String priceDetId = priceDetail[0];
							String dueDatePrice = priceDetail[1];
							String afterDueDatePrice = priceDetail[2];
							String userTypeName = priceDetail[3];
							
							//String [] priceDetail  = {priceDetId,dueDatePrice,afterDueDatePrice,userTypeName};
		   %>
							<form name="frmDisplayAdminList" method="post" action="addPriceMasterList.do">		
							<input name="priceMasterProcess" type="hidden" value="viewEditPriceList">
							<input name="priceDetId" type="hidden" value="<%=priceDetId%>">
						  <tr>
							<td height="26" class="listCellBg"><%=userTypeName%></td>
							<td class="listCellBg"><%=dueDatePrice%> </td>
							<td class="listCellBg"><%=afterDueDatePrice%> </td>
							<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="Edit" /></td>
							<td class="listCellBg"><input name="btnSubmit" type="submit" class="oneBtn" value="View" /></td>
						   </tr>
						</form>
		   
					      <%	}
									}else {
								%>
								<tr>
								  <th height="25" colspan="5" align="center">No Records are available. </th>
								</tr>
								<%}%>

						<!--  <tr>
							<td height="26" class="listCellBg"><a href="#" class="linkFive">DJango</a></td>
							<td class="listCellBg">Ben</td>
							<td class="listCellBg">Stephen</td>
							<td class="listCellBg">django@email.com</td>
							<td class="listCellBg"><input name="Submit3" type="submit" class="oneBtn" value="View" /></td>
						   </tr> -->
						  <!--<tr>
							<td height="25" colspan="5"  bgcolor="#ffffff" class="alignRight">
								<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
							<a href="#" class="linkThree">Prev</a> | <a href="#" class="linkThree">Next</a>			</td>
						   </tr>-->
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
