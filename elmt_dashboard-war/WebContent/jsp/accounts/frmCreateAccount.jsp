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
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">
function showList(){
	if(document.frmAccMaster.subItem.checked == true){
		document.frmAccMaster.subItemNo.disabled = false;
	}
	else{
		document.frmAccMaster.subItemNo.disabled = true;
	}
}

function hideList(){
	document.frmAccMaster.subItemNo.disabled =true;
}

function isSpecialCharacter(str){
	stringCheck="!@#$%^&*()_+|:;{}[]<>?/=-~.,'`;:"+"\\"+"\'";
	f1=1;
	for(j=0;j<str.length;j++){
		if(stringCheck.indexOf(str.charAt(j))!=-1){
			f1=0;}}
	if(f1==0){return true;}
	else{return false;}
}

function chkValid(){
	if(document.frmAccMaster.accType.value == "" || document.frmAccMaster.accType.value.indexOf(' ')==0){
		alert('Select Any one of the Account Type');
		document.frmAccMaster.accType.focus();
		return false;
	}
	
	if(document.frmAccMaster.accNumber.value=="" || document.frmAccMaster.accNumber.value.indexOf(' ')==0){
		alert('Account Number cannot be empty');
		document.frmAccMaster.accNumber.focus();
		return false;
	}
	
/*	if(document.frmAccMaster.accNumber.value!="" || document.frmAccMaster.accNumber.value.indexOf(' ')!=0){
		if(isnotIntegerChk(document.frmAccMaster.accNumber.value)){
			alert('Enter Valid Account Number');
			document.frmAccMaster.accNumber.focus();
			return false;			
		}
	} */
	
	if(document.frmAccMaster.accName.value=="" || document.frmAccMaster.accName.value.indexOf(' ')==0){
		alert('Account Name cannot be empty');
		document.frmAccMaster.accName.focus();
		return false;
	}
	
	if(document.frmAccMaster.accName.value!="" || document.frmAccMaster.accName.value.indexOf(' ')!=0){
		if(isSpecialCharacter(document.frmAccMaster.accName.value)){
			alert('Enter a valid Account Name');
			document.frmAccMaster.accName.focus();
			return false;		
		}
	}
	
	if(document.frmAccMaster.subItem.checked == true){
		if(document.frmAccMaster.subItemNo.value=="" || document.frmAccMaster.subItemNo.value.indexOf(' ')==0){
			alert('Select Any one of the Sub Item');
			document.frmAccMaster.subItemNo.focus();
			return false;					
		}
	}
}

function itemNameNumberChk(str){
	stringIntCheck="!@#$%^&*~`{}[]:;'<>?/.,";
	f2=1;
	
	for(j=0;j<str.length;j++)
	{ 
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return false;
	}
	else{ 
	  return true;
	}
}

function isnotIntegerChk(str){
	stringIntCheck="0123456789.";
	f2=1;
	
	for(j=0;j<str.length;j++)
	{ 
		if(stringIntCheck.indexOf(str.charAt(j))==-1){
			f2=0;
		}
	}
	if(f2==0){
		return true;
	}
	else{ 
	  return false;
	}
}

function enableCombo(){
	if(document.frmAccMaster.subItem.checked==true){
		document.frmAccMaster.subItemNo.disabled = false;
	}
}
</script>

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
								
								if (accountName.length > 0 && accountName.length > 0) {
									theValue = accountNo[0].firstChild.nodeValue;
									theText = accountName[0].firstChild.nodeValue;
								}
								
								var option = new Option( theValue + " - "+ theText,theValue);
								
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

//Validating

//Fill Item Number 
/*function fillItemNo(value){
	if(value!= null && value!=""){
		splitstr = value.split('#');
		document.frmItemMaster.transacItemNo.value=splitstr[0];
	}
	else{
		document.frmItemMaster.transacItemNo.value="";
	}
}*/
</script>

</head>
<body onload="hideList();">

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
		
					<form name="frmAccMaster" method="post" action="ChartAccMaster.do" onsubmit="return chkValid();"> <!---->
					<input type="hidden" name="process" value="saveAccMaster">		
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Account Type Master (Add) </span></td>
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
						%>
						  <tr> 
							<td class="tableLeft">Account Type :</td>
							<td width="297" class="tableRight">	
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
									%>
									<option value="<%=accountTypeName%>"><%=accountTypeName%></option>
								<%	}
								}
								%>
								</select>&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>						
						  <tr> 
							<td class="tableLeft">Account Number : </td>
							<td width="297" class="tableRight">
								<input name="accNumber" type="text" class="textboxOne" id="accNumber" maxlength="50"  />
								&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>						
						  <tr> 
							<td class="tableLeft">Account Name : </td>
							<td width="297" class="tableRight">
								<input name="accName" type="text" class="textboxOne" id="accName" maxlength="50"  />
								&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Sub Account Of: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							  <input type="checkbox" name="subItem" onclick="showList();"/></td>
								<td width="297" class="tableRight" id="subItemList">
									<select name="subItemNo" id="subItemNo" class="selectboxOne">
									<option selected="selected" value="">Select One</option>
									</select>
								</td>							
						  </tr>
						  <tr> 
							<td class="tableLeft">Description:</td>
								<td width="297" class="tableRight">
								<input name="accDescrption" type="text" class="textboxOne" id="accDescrption" maxlength="150"  /></td>								
						  </tr>
						  <tr> 
							<td class="tableLeft">Bank A/C No :</td>
								<td width="297" class="tableRight"><input name="accBank" type="text" class="textboxOne" id="accBank" maxlength="6">
							</td>								
						  </tr>		  	  						  
			  			
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
								<input  type="submit" class="gradBtn" value="Submit" />
								<input type="reset" class="gradBtn" value="Cancel" />
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
