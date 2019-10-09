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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">
function showList(){
	if(document.frmItemMaster.subItem.checked == true){
		document.frmItemMaster.subItemNo.disabled = false;
	}
	else{
		document.frmItemMaster.subItemNo.disabled = true;
	}
}

function hideList(){
	document.frmItemMaster.subItemNo.disabled =true;
}

function chkValid(){
	if(document.frmItemMaster.servMasterNo.value == "" || document.frmItemMaster.servMasterNo.value.indexOf(' ')==0){
		alert('Select Any one of the Service Type');
		document.frmItemMaster.servMasterNo.focus();
		return false;
	}
	
	if(document.frmItemMaster.itemNameNumber.value=="" || document.frmItemMaster.itemNameNumber.value.indexOf(' ')==0){
		alert('Item Name/Number cannot be empty');
		document.frmItemMaster.itemNameNumber.focus();
		return false;
	}
	
	/*	
	if(document.frmItemMaster.itemNameNumber.value!="" || document.frmItemMaster.itemNameNumber.value.indexOf(' ')!=0){
		if(itemNameNumberChk(document.frmItemMaster.itemNameNumber.value)){
			alert('Enter a valid Item Name/Number');
			document.frmItemMaster.itemNameNumber.focus();
			return false;		
		}
	}
	*/
	if(document.frmItemMaster.subItem.checked == true){
		if(document.frmItemMaster.subItemNo.value=="" || document.frmItemMaster.subItemNo.value.indexOf(' ')==0){
			alert('Select Any one of the Sub Item');
			document.frmItemMaster.subItemNo.focus();
			return false;					
		}
	}
	
	if(document.frmItemMaster.itemDescrption.value=="" || document.frmItemMaster.itemDescrption.value.indexOf(' ')==0){
		alert('Item Description Cannot be empty');
		document.frmItemMaster.itemDescrption.focus();
		return false;
	}	
	
	if(document.frmItemMaster.itemRate.value!="" || document.frmItemMaster.itemRate.value.indexOf(' ')!=0){
		if(isnotIntegerChk(document.frmItemMaster.itemRate.value)){
			alert('Enter Valid Rate');
			document.frmItemMaster.itemRate.focus();
			return false;
		}
	}
	
	if(document.frmItemMaster.AccountNo.value == "" || document.frmItemMaster.AccountNo.value.indexOf(' ')==0){
		alert('Select Any of the Account ');
		document.frmItemMaster.AccountNo.focus();
		return false;
	}		
}

function itemNameNumberChk(str){
	stringIntCheck="abcdefghijklmopqrstuvwxyz0123456789.-()";
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
</script>

<script language="javascript">
var httpRequest;
	function populateSubItem(val){
		if(val!="" && val.length!=0){
			//alert(document.frmItemMaster.servMasterNo.value);
		   //var servMasterNo=document.frmItemMaster.servMasterNo.value;
			
				url= "SubItemAjaxAction.do?process=subItemdisp&serviceName="+val; 
				
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
							var xRows= xmlDoc.getElementsByTagName('subList');
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
								var itemId = xRows[i].getElementsByTagName("itemId");
								var itemName = xRows[i].getElementsByTagName("serviceTypeName");
								var itemNumb = xRows[i].getElementsByTagName("itemNo");
								var parentitemNo = xRows[i].getElementsByTagName("parentItemNo");
								var itemDesc = xRows[i].getElementsByTagName("itemDesc");
								var accountNo = xRows[i].getElementsByTagName("accountNo");
								var theValue =null;
								var theText = null;
								
								if (itemName.length > 0 && itemName.length > 0) {
									theValue = itemNumb[0].firstChild.nodeValue;
									theText = itemDesc[0].firstChild.nodeValue;
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
		
					<form name="frmItemMaster" method="post" action="ItemMaster.do" onsubmit="return chkValid();"> <!---->
					<input type="hidden" name="process" value="saveListMaster">		
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Accounts: <span class="styleBoldTwo">Item List Master (Add) </span></td>
						</tr>
						  <tr> 
							<td class="tableLeft">Service : </td>
							<td width="297" class="tableRight">
							<select name="servMasterNo" class="selectboxOne" onchange="populateSubItem(this.value);"><!--   -->
							  <!--onchange="fillItemNo();"(this.value);-->
                              <option selected="selected" value="">Select One</option>
                              <%
							    ArrayList serviceMaster = (ArrayList) request.getAttribute("serviceMaster");
								Iterator it_master = serviceMaster.iterator();
								while(it_master.hasNext()){
												String serviceTypeArray[] = (String[]) it_master.next();
												String serviceTypeId = serviceTypeArray[0];
												String serviceTypeName = serviceTypeArray[1];
							%>
                              <option value="<%=serviceTypeName%>"><%=serviceTypeName%></option>
                              <%
										}
							%>
                            </select>
							  &nbsp;<span class="asterisk">*</span>							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Item Name / Number:</td>
							<td width="297" class="tableRight"><input name="itemNameNumber" type="text" class="textboxOne" id="itemNameNumber" maxlength="50"  /><span class="asterisk">*</span></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Sub Item Of: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="subItem" onclick="showList();"/></td><!-- -->
								<td width="297" class="tableRight" id="subItemList">
									<select name="subItemNo" id="subItemNo" class="selectboxOne">
									<option selected="selected" value="">Select One</option>
									</select>
							<%							
/*								ArrayList HLCItemMaster = (ArrayList) request.getAttribute("HLCItemMaster");
								HLCItemMaster itemVO = new HLCItemMaster();
								String parentAccountNo=null;
								if(HLCItemMaster!=null){
									if(HLCItemMaster.size()>0){*/
							%>

                              <%	/*
								Iterator it = HLCItemMaster.iterator();
								while(it.hasNext()){
										itemVO=(HLCItemMaster)it.next();	
										String itemId = itemVO.getItemId();
										String itemNo = itemVO.getItemNo();
										String itemParentNo = itemVO.getParentItemNo(); 
										String accountNo = itemVO.getAccountNo();
							%>
                              <option value="<%=itemId%>"><%=itemNo%></option>
                              <%			}		%>
                            </select>							
							<%		}
								}*/
							%>
							</td>
							</tr>						  
						  <tr> 
							<td class="tableLeft">Description:</td>
								<td width="297" class="tableRight">
								<input name="itemDescrption" type="text" class="textboxOne" id="itemDescrption" maxlength="150"  />
								&nbsp;<span class="asterisk">*</span>							</td>								
						  </tr>
						  <tr> 
							<td class="tableLeft">Rate :</td>
								<td width="297" class="tableRight"><input name="itemRate" type="text" class="textboxOne" id="itemRate" maxlength="6">
							</td>								
						  </tr>		  						  
							<tr>
							<td class="tableLeft">Account</td>
								<td width="297" class="tableRight">
							<%							
								ArrayList accMaster = (ArrayList) request.getAttribute("accMaster");
								HLCAccountsMasterVO accDet; 
								if(accMaster!=null){
									if(accMaster.size()>0){
							%>
							  <select name="AccountNo" class="selectboxOne">
                              <option selected="selected" value="">Select One</option>
                              <%	
										Iterator it = accMaster.iterator();
										while(it.hasNext()){
												accDet=(HLCAccountsMasterVO)it.next();	
												String accountId = accDet.getAccount_id();
												String accountType = accDet.getAccount_type();
												String accountNo = accDet.getAccount_no();
												String accountName = accDet.getAccount_name();
												String parentaccNo = accDet.getParent_account_no();
												String accountDesc = accDet.getAccount_desc();
												String bnkAccNo = accDet.getBank_account_no();												
							%>
                              <option value="<%=accountNo%>"><%=accountNo%> - <%=accountName%></option>
                              <%			}		%>
                            </select>							
							&nbsp;<span class="asterisk">*</span>								
							<%		}
								}
							%>
							</td>
						  <tr>
							<td class="tableLeft">&nbsp;</td>
							<td class="tableRight">
								<input  type="submit" class="gradBtn" value="Submit" />
								<input type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" />
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
