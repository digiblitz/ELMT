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
<%@ page import="com.hlcmrm.util.HLCDonationVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<script src="javascripts/frmCntryMailAmount.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<script language="javascript">

function myvalidate(){

//-------------------------------------Country Mail Type name------------------------------------------------------------

if((document.frmDonationDetails.DonationTypeName.value=="")||(document.frmDonationDetails.DonationTypeName.value.indexOf(' ')==0))
   {alert(" Donation Type name cannot be empty ");
     document.frmDonationDetails.DonationTypeName.focus();
    return false; }
	
	if((document.frmDonationDetails.DonationTypeName.value.length >80 ))
 {alert(" Donation  Type name exceeds the maximum of 80 characters");
  document.frmDonationDetails.DonationTypeName.focus();
 return false;}
 //-------------------------------------Amount--------------------------------------------------------------
 if(document.frmDonationDetails.DonationAmount.value=="")
   {alert(" Amount cannot be empty ");
     document.frmDonationDetails.DonationAmount.focus();
    return false; }
	
   if(!(document.frmDonationDetails.DonationAmount.value=="")){
   		if(Number(document.frmDonationDetails.DonationAmount.value)){
				document.frmDonationDetails.DonationAmount.focus();
			}
		if(isNaN(document.frmDonationDetails.DonationAmount.value)){
		 alert("Invalid Amount Entered.");
		 document.frmDonationDetails.DonationAmount.focus();
		 return false;
	   }
	    if(document.frmDonationDetails.DonationAmount.value.indexOf('.')!=-1){
			fstr=document.frmDonationDetails.DonationAmount.value;
			fstr1=document.frmDonationDetails.DonationAmount.value.indexOf('.');
			mm = (fstr.substring(fstr1,document.frmDonationDetails.DonationAmount.value.length));
			str=(Number(mm.length));
			if((str)>2){
				alert("Amount is not valid ");
				document.frmDonationDetails.DonationAmount.focus();
				return false;
			}
		}
	} 
	if(document.frmDonationDetails.transacType.selectedIndex==0){
		alert('Enter Valid Transaction Type ');
		document.frmDonationDetails.transacType.focus();
		return false;		
	}
	if(document.frmDonationDetails.txtPrior.value=="")
	{
		alert('Priority Value cannot be empty');
		document.frmDonationDetails.txtPrior.focus();
		return false;		
	}
	if(isNaN(document.frmDonationDetails.txtPrior.value)){
		 alert("Invalid Priority Value Entered.");
		 document.frmDonationDetails.txtPrior.focus();
		 return false;
	}		
}

</script>

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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="2" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Donation</span></td>
  </tr>
  <tr>
    <td colspan="2" class="tblDescrp">
	    <!--<img src="images/usea_logo.jpg" class="imgFloatLeft" />-->
		<strong>Fill in the following information to assign an amount for a given Donation Type.</strong> <br />
	</td>
  </tr>
  <tr>
  	<td><table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
			 <% String statuscheck = (String)request.getAttribute("err");
					if(statuscheck!=null && statuscheck.equals("st")){
					%>
					<tr>
					<td colspan="2" class="styleError"><strong>Donation Type Name Already Exists. Try it Again.</strong></td>
					</tr>
					<%
					}
					%>
			<%
		  			HLCDonationVO donObj = (HLCDonationVO) request.getAttribute("Vobj");
		   
					String donationId = donObj.getDonationId();
					String donationName = donObj.getDonationName();
					String donationPrice = donObj.getDonationPrice();
					boolean stat = donObj.isActiveStatus ();
					boolean pre_stat = donObj.isPrecheckStatus();
					String transacTypeId = donObj.getTransaction_type_id();
					String priorityValue=String.valueOf(donObj.getPriorityValue());
			%>
			
			<form name="frmDonationDetails"  method="post" action="Donationdetails.do" onSubmit="return myvalidate();">
			<input name="process" type="hidden" value="update" />	  
			<input type="hidden" name="status" value="<%=stat%>" />
			<input name="donationTypeId" type="hidden" value="<%=donationId%>" />
				  <tr>
					<td colspan="2" class="tblRowHead">
						Edit Donations </td>
				  </tr>
				 <tr>
					  <td colspan="2">Fields marked with an asterisk (*) are required.</td>
				 </tr>					  
				  <tr>
					<td class="tableLeft">Donation Type  name:</td>
					<td class="tableRight"><input name="DonationTypeName" type="text" value="<%=donationName%>" class="textboxOne" />&nbsp;<span class="asterisk">*</span></td>
				  </tr>
				  <tr>
					<td class="tableLeft"> Amount($):</td>
					<% if(( donationPrice==null)||(donationPrice.trim().length()==0)){	donationPrice="0";	}	%>
					<td class="tableRight"><input name="DonationAmount" type="text" value="<%=donationPrice%>" class="textboxOne" />&nbsp;<span class="asterisk">*</span></td>
				  </tr>	
				  <tr>
					<td class="tableLeft">Precheck Amount Status:</td>
					<td class="tableRight">
					<select name="preset" class="selectboxOne">
					<% if (pre_stat==true){	%>
						<option value="True"  selected="selected">True</option>
						<option value="False">False</option>
						<% } 
						else{%>
						<option value="True" >True</option>
						<option value="False" selected="selected">False</option>
						<% } %>
					</select>
					</td>
				  </tr>					  
			  <tr>
				     <td class="tableLeft">QB Transaction Type: </td>
				     <td class="tableRight">
						<select name="transacType" id="transacType" class="selectboxOne" >
						<option selected="selected" value="Select One">Select One</option>						
					  <%
					  ArrayList accDetails = (ArrayList) request.getAttribute("accDetails");
					  if(accDetails!=null && accDetails.size()!=0){
						Iterator itr = accDetails.iterator();
						while(itr.hasNext()){
							String txnDet[] = (String[]) itr.next();
							
								String txnId = txnDet[0];
								String txnName = txnDet[1];
						if(transacTypeId!=null){
									if(transacTypeId.equalsIgnoreCase(txnId)){
							  %>
									<option value="<%=txnId%>" selected="selected"><%=txnName%></option>
								<% } 
								else{ 	%>
									<option value="<%=txnId%>"><%=txnName%></option>
							  <%	}	 
						  }
						  else{
						  %>
						  <option value="<%=txnId%>"><%=txnName%></option>
						  <%}
						  }
					  }
					  %>
						</select>
				     <span class="asterisk">*</span></td>
		      </tr>	
			  <tr>
					<td class="tableLeft">Priority:</td>
					<td class="tableRight"><input name="txtPrior" type="text" class="textboxOne" value="<%=priorityValue%>" />&nbsp;<span class="asterisk">*</span></td>
			 </tr>						  
				   <tr>
					<td class="tableLeft">&nbsp;</td>
					<td class="tableRight">
					<input  type="submit"  class="gradBtn" value="Update"/>&nbsp;&nbsp;&nbsp;
					<input  type="button"  class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>
				  </tr>
				  		</form>
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
