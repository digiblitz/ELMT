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
<script src="javascripts/frmGrossAmtMast.js" type="text/javascript" ></script>
<script>

function validatePriceList(){
						
		if(document.frmMeePriceMaster.duePrice.value==""){
			alert("The Due Amount Field can't be blank");
			document.frmMeePriceMaster.duePrice.focus();
			return false;
		}
	
		if(!Number(document.frmMeePriceMaster.duePrice.value)){
			alert("Enter valid Due Amount");
			document.frmMeePriceMaster.duePrice.focus();
			return false;
		}
		
if((document.frmMeePriceMaster.duePrice.value.indexOf('+')!=-1)||(document.frmMeePriceMaster.duePrice.value.indexOf('-')!=-1)){
				alert("Enter valid Due Amount");
				document.frmMeePriceMaster.duePrice.focus();
				return false;
			}
		if(document.frmMeePriceMaster.duePrice.value.indexOf('.')>7){
			alert("Enter valid Due Amount");
			document.frmMeePriceMaster.duePrice.focus();
			return false;
		}
		if((document.frmMeePriceMaster.duePrice.value.indexOf('.'))==-1){
			if(document.frmMeePriceMaster.duePrice.value.length>7){
				alert("Enter valid Due Amount");
				document.frmMeePriceMaster.duePrice.focus();
				return false;
			}
		}
			
		if(!(document.frmMeePriceMaster.duePrice.value=="")){
			if((document.frmMeePriceMaster.duePrice.value.indexOf('.'))!=-1){
				fstr=document.frmMeePriceMaster.duePrice.value;
				fstr1=document.frmMeePriceMaster.duePrice.value.lastIndexOf('.');
				mm = (fstr.substring(fstr1,document.frmMeePriceMaster.duePrice.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("Due Amount is not valid");
					document.frmMeePriceMaster.duePrice.focus();
					return false;
				}
			}
		}


		if(document.frmMeePriceMaster.afterPrice.value==""){
			alert("The After Due Amount Field can't be blank");
			document.frmMeePriceMaster.afterPrice.focus();
			return false;
		}
	
		if(!Number(document.frmMeePriceMaster.afterPrice.value)){
			alert("Enter valid After Due Amount");
			document.frmMeePriceMaster.afterPrice.focus();
			return false;
		}
		if(document.frmMeePriceMaster.afterPrice.value.indexOf('.')>7){
			alert("Enter valid After Due Amount");
			document.frmMeePriceMaster.afterPrice.focus();
			return false;
		}
		
if((document.frmMeePriceMaster.afterPrice.value.indexOf('+')!=-1)||(document.frmMeePriceMaster.afterPrice.value.indexOf('-')!=-1)){
				alert("Enter valid After Due Amount");
				document.frmMeePriceMaster.afterPrice.focus();
				return false;
			}
		if((document.frmMeePriceMaster.afterPrice.value.indexOf('.'))==-1){
			if(document.frmMeePriceMaster.afterPrice.value.length>7){
				alert("Enter valid After Due Amount");
				document.frmMeePriceMaster.afterPrice.focus();
				return false;
			}
		}
			
		if(!(document.frmMeePriceMaster.afterPrice.value=="")){
			if((document.frmMeePriceMaster.afterPrice.value.indexOf('.'))!=-1){
				fstr=document.frmMeePriceMaster.afterPrice.value;
				fstr1=document.frmMeePriceMaster.afterPrice.value.lastIndexOf('.');
				mm = (fstr.substring(fstr1,document.frmMeePriceMaster.afterPrice.value.length));
				str=(Number(mm.length));
				if((str)>3){
					alert("After Due Amount is not valid");
					document.frmMeePriceMaster.afterPrice.focus();
					return false;
				}
			}
		}
		return true;
	}

</script>




<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> --> 
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

<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">


  <tr>
    <td colspan="2" class="tblMainHead"><strong></strong> Meetings: <span class="styleBoldTwo">Event Registration Price Details</span></td>
  </tr>
 
  <tr>
  	<td>
	                <%	   		
					 ArrayList priceListView=(ArrayList)request.getAttribute("singlePriceDetail");
					 if(priceListView!=null && priceListView.size()!=0){ 
					 Iterator itPrice = priceListView.iterator();
					  while(itPrice.hasNext()){
						    String [] priceDetail  = (String[]) itPrice.next();
							String priceId = priceDetail[0];
							String priceTypeId = priceDetail[1];
							String priceTypeName = priceDetail[2];
							String seasonId = priceDetail[3];
							String seasonName = priceDetail[4];
							String dueDatePrice = priceDetail[5];
							String afterDueDatePrice = priceDetail[6];
							
				     %>     
<form name="frmMeePriceMaster" id="frmMeePriceMaster" action="eventRegPriceCRUD.do" method="post" onsubmit="return validatePriceList();"  >
	<input type="hidden" name="cmd" value="updatePrice">
    <input type="hidden" name="priceId" value="<%=priceId%>">
            
	
			<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblRowHead">Add A Price Detail:</td>
				  </tr>
								  
				   <tr>
				     <td class="tableLeft">Price Type :</td>
				     <td class="tableRight">
						
										
										<%
										ArrayList priceDetails = (ArrayList)request.getAttribute("priceList");
										if(priceDetails!=null && priceDetails.size()!=0){
										Iterator itr= priceDetails.iterator();
										while(itr.hasNext()){
										String[] s = (String[]) itr.next();
										String priceTypeId1 = s[0];
										String priceName = s[1];
										if(priceTypeId!=null && priceTypeId.equalsIgnoreCase(priceTypeId1)){
										%>
										<%=priceName%>
										
										<input type="hidden" name="selPriceType" value="<%=priceTypeId1%>">
					                    <%}}}%>
				  </td>
		     	  </tr>
				  <tr>
				     <td class="tableLeft">Season :</td>
				     <td class="tableRight">
					 
					 	
										<%
										ArrayList seasonDetails = (ArrayList)request.getAttribute("seasonList");
										if(seasonDetails!=null && seasonDetails.size()!=0){
										Iterator itr= seasonDetails.iterator();
										while(itr.hasNext()){
										String[] s = (String[]) itr.next();
										String seaId = s[0];
										String seaName = s[1];
										if(seasonId!=null && seasonId.equalsIgnoreCase(seaId)){
										%>
										<%=seaName%>
										
					                    <input type="hidden" name="selSeason" value="<%=seaId%>">
				  </tr>					<%}}}%>
				  <tr>
					<td class="tableLeft">Due Date Price :</td>
					<td class="tableRight"><strong>$</strong>
                        <input name="duePrice" type="text" class="textboxOne" value="<%=dueDatePrice%>" size="8" />
                    	<span class="asterisk">*</span>
					</td>
				  </tr>
			      <tr>
					<td class="tableLeft">After Due Date Price :</td>
					<td class="tableRight">
					<strong>$</strong> <input name ="afterPrice" type="text" value="<%=afterDueDatePrice%>" class="textboxOne" size="8" />
				 	 <span class="asterisk">*</span>
					</td>
				  </tr>
 <%}}%>
			      <tr>
					<td align="center">&nbsp;</td>
					<td class="tableRight">
					<input type="submit" value="Update" class="gradBtn"  />
					&nbsp;
					<input type="button" value="Cancel" class="gradBtn" onclick="javascript:history.back(-1);" />					</td>
				  </tr>
			</table>
			
		</form>
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
