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
//Validating
function chkValid(){
	if(document.frmTransactionEntry.transacName.value=="" || document.frmTransactionEntry.transacName.value.indexOf(' ')==0){
		alert('Transaction Name Cannot Be Empty');
		document.frmTransactionEntry.transacName.focus();
		return false;
	}
	if(document.frmTransactionEntry.transacAccNo.value.length>50){
		alert('Enter a valid Acccount Number');
		document.frmTransactionEntry.transacAccNo.focus();
		return false;
	}
	if(document.frmTransactionEntry.transacAccNo.selected == 0){
		alert('Select Any of the Acccount Number');
		document.frmTransactionEntry.transacAccNo.focus();
		return false;
	}	
	if(document.frmTransactionEntry.transacClass.value=="" || document.frmTransactionEntry.transacClass.value.indexOf(' ')==0){
		alert('Select Any of the Class');
		document.frmTransactionEntry.transacClass.focus();
		return false;
	}
	if(document.frmTransactionEntry.transacItemNo.value.length>10){
		alert('Enter a valid Item Number');
		document.frmTransactionEntry.transacItemNo.focus();
		return false;
	}	
	if(document.frmTransactionEntry.transacClass.selected == 0){
		alert('Select Any of the Class');
		document.frmTransactionEntry.transacClass.focus();
		return false;
	}		
}
//Fill Item Number 
function fillItemNo(value){
	if(value!= null && value!=""){
		splitstr = value.split('#');
		document.frmTransactionEntry.transacItemNo.value=splitstr[1];
	}
	else{
		document.frmTransactionEntry.transacItemNo.value="";
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
		
					<form name="frmTransactionEntry" method="post" action="TransactDet.do" onsubmit="return chkValid();"> <!---->
					<input type="hidden" name="process" value="updateMap">		
						<table width="597" border="0" align="center" cellpadding="0" cellspacing="0" class="formLayout">
						
						<%	String existStat = (String) request.getAttribute("existStat");
					if(existStat!=null)
					{
					if(existStat.equalsIgnoreCase("yes"))
						{%>	
					  <tr>
						  <td class="styleError" colspan="4">Transaction Type Details Already Exists !!</td>
					  </tr>
					<%	}	
						}	%>
						
						<tr>
						<td colspan="2" class="tblMainHead"><strong></strong> Membership: <span class="styleBoldTwo">Transaction Type Master </span></td>
						</tr>
						  <tr> 
							<td class="tableLeft">Transaction Name : </td>
							<td width="297" class="tableRight">
								<input name="transacName" type="text" class="textboxOne" id="transacName" maxlength="50"  />
								&nbsp;<span class="asterisk">*</span>
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Transaction Type :</td>
							<td width="297" class="tableRight">	
								<select name="transacType" class="selectboxOne" >
									<option value="Credit" selected="selected">Credit</option>
									<option value="Debit">Debit</option>								
								</select>
							</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Item Name :</td>
								<td width="297" class="tableRight">
							<%							
								ArrayList HLCItemMaster = (ArrayList) request.getAttribute("itemMaster");
								System.out.print("HLCItemMaster.size() :"+HLCItemMaster.size());
								
								HLCItemMaster itemVO = new HLCItemMaster();
								//String parentAccountNo=null;
								if(HLCItemMaster!=null){
									if(HLCItemMaster.size()>0){
							%>
								<select name="transacAccNo" class="selectboxOne" onchange="fillItemNo(this.value);" ><!--onchange="fillItemNo();"(this.value);-->
								<option selected="selected" value="">Select One</option>
							<%			
								Iterator it = HLCItemMaster.iterator();
								while(it.hasNext()){
												itemVO=(HLCItemMaster)it.next();
												
												String itemNo = itemVO.getItemNo();	
												String accountNo = itemVO.getAccountNo();	
												String itemDesc = itemVO.getItemDesc();	
												String optVal = itemNo+"#"+accountNo;
												String optName = itemNo+"-"+itemDesc;
																					
												/*String accountId = accDet.getAccount_id();
												String accountTyp = accDet.getAccount_type();
												String accountNo = accDet.getAccount_no();                
												String accountName = accDet.getAccount_name();
												parentAccountNo = accDet.getParent_account_no();
												String accountDesc = accDet.getAccount_desc();
												String bankAccNo = accDet.getBank_account_no();	
												String optVal = accountNo+"#"+parentAccountNo;*/
												
												
																					
							%>
										<option value="<%=optVal%>"><%=optName%></option>
							<%
										}
							%>
								</select>&nbsp;<span class="asterisk">*</span>								
							<%		}
								}
							%>								
							</td>		
						  </tr>
<!--						  <tr> 
							<td class="tableLeft">Sub-Account No :</td>
								<td width="297" class="tableRight">
								<input type="text" class="textboxOne" disabled="disabled" name="transacSubAccNo" id="transacSubAccNo"/>								
							</td>								
						  </tr>-->
						  <tr> 
							<td class="tableLeft">Account Number :</td>
								<td width="297" class="tableRight">
								<input name="transacItemNo" type="text" class="textboxOne" id="transacItemNo" maxlength="6"  />
								&nbsp;<span class="asterisk">*</span>								
							</td>								
						  </tr>
						  <tr> 
							<td class="tableLeft">Class :</td>
								<td width="297" class="tableRight">
								
							<%
								ArrayList classMaster = (ArrayList) request.getAttribute("classMaster");
								HLCClassDetailsVO classDet = new HLCClassDetailsVO();
								if(classMaster!=null){
									if(classMaster.size()>0){
							%>
								<select name="transacClass" class="selectboxOne">
								<option selected="selected" value="">Select One</option>		
							<%	
										Iterator it = classMaster.iterator();
										while(it.hasNext()){
												classDet=(HLCClassDetailsVO)it.next();	
												String classId = classDet.getClass_id();
												String className = classDet.getClass_name();
												String classParentName = classDet.getParent_class_name(); 
							%>
								<option value="<%=className%>"><%=className%></option>											
							<%			}		%>
								</select>&nbsp;<span class="asterisk">*</span>
							<%		}
								}
							%>								
																
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
