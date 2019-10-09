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
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.hlcaccounts.util.*"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmAccMstrVali.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">
var httpRequest;
	function populateSubItem(val){
		if(val!="" && val.length!=0){
			//alert(document.frmItemMaster.servMasterNo.value);
		   //var servMasterNo=document.frmItemMaster.servMasterNo.value;
			
				url= "SubItemAjaxAction.do?process=subAccdisp&accType="+val; 
				
				if (window.ActiveXObject){ 
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
				} 
				else if (window.XMLHttpRequest){ 
					httpRequest = new XMLHttpRequest(); 
				}

				httpRequest.onreadystatechange =function(){
					if (httpRequest.readyState == 4) { // Complete
						if (httpRequest.status == 200) { // OK response

							var xmlDoc = httpRequest.responseXML.documentElement;
							var xRows= xmlDoc.getElementsByTagName('subAccList');
							var objDDL = document.getElementById('subItemNo');
							objDDL.innerHTML="";

							if(xRows.length==0){
								//setToDefault();
							}
						
							var rootObj =  document.createElement("option");
							var attrib = document.createAttribute("value");
							attrib.value="";
							rootObj.setAttributeNode(attrib);
							newtext=document.createTextNode('Select One');
							rootObj.appendChild(newtext);
							objDDL.appendChild(rootObj);
							//objDDL.add(rootObj,null);
							
							for (var i=0; i<xRows.length; i++) {
								var accountId = xRows[i].getElementsByTagName("accountId");
								var accountType = xRows[i].getElementsByTagName("accountType");
								var accountNo = xRows[i].getElementsByTagName("accountNo");
								var accountName = xRows[i].getElementsByTagName("accountName");
								var parent_accountNo = xRows[i].getElementsByTagName("parent_accountNo");
								var accountDesc = xRows[i].getElementsByTagName("accountDesc");
								
								var theValue =null;
								var theText = null;
								
								if (accountName.length > 0 && accountNo.length > 0) {
									theValue = accountNo[0].firstChild.nodeValue;
									theText = accountName[0].firstChild.nodeValue;
								}

								
								var option = new Option( theValue + " - "+ theText,theValue,false,true);
								
								try {
									objDDL.add(option,null);
								}catch(e){
									objDDL.add(option,-1);
								}
							}
						} else {
							alert("Problem: " + httpRequest.statusText);
						}
				} 
				//httpRequest.send(null); 
		 }
		httpRequest.open("GET", url, true);
		httpRequest.send(null);
	}
	 else {
			document.getElementById('subItemNo').value =val;
			//setToDefault(relOpt, hrMemId);
		}
}

function setToDefault(){
	var currObj = document.getElementById('subItemNo');
	currObj.innerHTML="";
	var rootObj =  document.createElement("option");
	var attrib = document.createAttribute("value");
	attrib.value="";
	rootObj.setAttributeNode(attrib);
	newtext=document.createTextNode('Select One');
	rootObj.appendChild(newtext);
	currObj.appendChild(rootObj);
	//document.getElementById(hrMemId).value = "";
}

	function populateSelectedSubItem(val,parVal){
		if(val!="" && val.length!=0){
			//alert(document.frmItemMaster.servMasterNo.value);
		   //var servMasterNo=document.frmItemMaster.servMasterNo.value;
				url= "SubItemAjaxAction.do?process=subAccdisp&accType="+val; 
				
				if (window.ActiveXObject){ 
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP"); 
				} 
				else if (window.XMLHttpRequest){ 
					httpRequest = new XMLHttpRequest(); 
				}

				httpRequest.onreadystatechange =function(){
					if (httpRequest.readyState == 4) { // Complete
						if (httpRequest.status == 200) { // OK response

							var xmlDoc = httpRequest.responseXML.documentElement;
							var xRows= xmlDoc.getElementsByTagName('subAccList');
							var objDDL = document.getElementById('subItemNo');
							objDDL.innerHTML="";

							if(xRows.length==0){
								//setToDefault();
							}
						
							var rootObj =  document.createElement("option");
							var attrib = document.createAttribute("value");
							attrib.value="";
							rootObj.setAttributeNode(attrib);
							newtext=document.createTextNode('Select One');
							rootObj.appendChild(newtext);
							objDDL.appendChild(rootObj);
							//objDDL.add(rootObj,null);
							
							for (var i=0; i<xRows.length; i++) {
								
								var accountId = xRows[i].getElementsByTagName("accountId");
								var accountType = xRows[i].getElementsByTagName("accountType");
								var accountNo = xRows[i].getElementsByTagName("accountNo");
								var accountName = xRows[i].getElementsByTagName("accountName");
								var parent_accountNo = xRows[i].getElementsByTagName("parent_accountNo");
								var accountDesc = xRows[i].getElementsByTagName("accountDesc");
								
								var theValue =null;
								var theText = null;
								var theparentNo = null;
								if (accountNo.length > 0 && accountName.length > 0) {
									theValue = accountNo[0].firstChild.nodeValue;
									theText = accountName[0].firstChild.nodeValue;
									theparentNo = parent_accountNo[0].firstChild.nodeValue;
								}								
								
								if(parVal==theValue){
									var option = new Option( theValue + " - "+ theText,theValue,false,true);
								}
								else{
									var option = new Option( theValue + " - "+ theText,theValue);
								}
								try {
									objDDL.add(option,null);
								}catch(e){
									objDDL.add(option,-1);
								}
							}
						} else {
							alert("Problem: " + httpRequest.statusText);
						}
				} 
		 }
		httpRequest.open("GET", url, true);
		httpRequest.send(null);
	}
	 else {
			document.getElementById('subItemNo').value =val;
			//setToDefault(relOpt, hrMemId);
		}
}


//Validating
function fillValues(){
	var servMasterNo=document.frmAccMaster.accType.value;
	var parVal = document.frmAccMaster.parentAccVO.value;
	populateSelectedSubItem(servMasterNo,parVal);
	var tempValue = document.frmAccMaster.subItemNo.value;
	//setToSelected(tempValue);
}
</script>

</head>
<body onload="hideList();fillValues();">
<%! 

String  nullCheck(String fieldName){
	String retValue = "";
		if(fieldName!=null && fieldName.trim().length()!=0){
		retValue = fieldName;
		}
	return retValue;
}

%>
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
				<table width="100" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2">
		
					<form name="frmAccMaster" method="post" action="ChartAccMaster.do" onsubmit="return chkValid();" onmousemove="enableCombo();"> <!---->
					<input type="hidden" name="process" value="updteAccMaster">		
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Account Type Master (Edit / Update) </span></td>
						</tr>
						<%
						String errStat = (String) request.getAttribute("errStat");
						if(!errStat.equalsIgnoreCase("none")){
						%>
						<tr>
							<td colspan="2" class="styleError"><strong><%=errStat%></strong></td>
						</tr>
						<%
						}
						 HLCAccountsMasterVO accountVO = ( HLCAccountsMasterVO ) request.getAttribute("accMasterVO");
							String accIdVO = null;
							String accTypVO = null;
							String accountNoVO = null;
							String accNameVO = null;
							String parentAccVO = null;
							String accDescVO = null;
							String bankAccVO = null;	 
						 if(accountVO!=null){
							accIdVO = accountVO.getAccount_id();
							accTypVO = accountVO.getAccount_type();
							accountNoVO = accountVO.getAccount_no();
							accNameVO = accountVO.getAccount_name();
							parentAccVO = accountVO.getParent_account_no();
							accDescVO = accountVO.getAccount_desc();
							bankAccVO = accountVO.getBank_account_no();						 	
						 }
						
						%>
						  <tr> 
							<td class="tableLeft">Account Type :</td>
							<td width="297" class="tableRight">
							<input type="hidden" name="parentAccVO" value="<%=parentAccVO%>" />
							<input type="hidden" name="accId" value="<%=accIdVO%>" />
								<select name="accType" class="selectboxOne" onchange="populateSubItem(this.value);">
								<option value="" selected="selected">Select One</option>
								<%
								ArrayList accTypeMaster = (ArrayList) request.getAttribute("accTypeMaster");
								if(accTypeMaster!=null){
								Iterator itr = accTypeMaster.iterator();
								while(itr.hasNext()){
									String accTypeArray[] = (String[]) itr.next();
									String accountTypeId = accTypeArray[0];
									String accountTypeName =accTypeArray[1];
									if(accTypVO.equalsIgnoreCase(accountTypeName)){
									%>
										<option value="<%=accountTypeName%>" selected="selected"><%=accountTypeName%></option>
										<%}
										else {
										%>
										<option value="<%=accountTypeName%>"><%=accountTypeName%></option>
									<%	}
									}
								}
								%>
								</select>&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>						
						  <tr> 
							<td class="tableLeft">Account Number : </td>
							<td width="297" class="tableRight">
								<input name="accNumber" type="text" class="textboxOne" id="accNumber" maxlength="50" value="<%=accountNoVO%>"  />
								&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>						
						  <tr> 
							<td class="tableLeft">Account Name : </td>
							<td width="297" class="tableRight">
								<input name="accName" type="text" class="textboxOne" id="accName" maxlength="75" value="<%=accNameVO%>"  />
								&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Sub Account Of: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <%
							  System.out.print("parentAccVO " +parentAccVO);
							if(parentAccVO!=null && parentAccVO.trim().length()>0){
							System.out.print("Enabled");
							%>
							<input type="checkbox" checked="checked" name="subItem" onclick="showList();"/>
							<% }
							else{
							System.out.print("Dis - abled");							
							%>
							<input type="checkbox" name="subItem" onclick="showList();"/>
							<% } %></td>
								<td width="297" class="tableRight" id="subItemList">
									<select name="subItemNo" id="subItemNo" class="selectboxOne">
									<option selected="selected" value="">Select One</option>
									</select>
								</td>							
						  </tr>
						  <tr> 
							<td class="tableLeft">Description:</td>
								<td width="297" class="tableRight">
								<input name="accDescrption" type="text" class="textboxOne" id="accDescrption" maxlength="150" value="<%=nullCheck(accDescVO)%>"/></td>								
						  </tr>
						  <tr> 
							<td class="tableLeft">Bank A/C No :</td>
								<td width="297" class="tableRight"><input name="accBank" type="text" class="textboxOne" id="accBank" maxlength="10" value="<%=nullCheck(bankAccVO)%>">
							</td>								
						  </tr>		  	  						  
			  			
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
								<input  type="submit" class="gradBtn" value="Update" />
								<input type="reset" class="gradBtn" value="Cancel" onclick="javascript:location.href('./ChartAccMaster.do?process=listAccMaster');" />
							</td>
						  </tr>
					  </table>
					  </form>
				  <tr>
						<td >&nbsp; 
					   <!-- DO NOT DELETE THIS ROW -->
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
