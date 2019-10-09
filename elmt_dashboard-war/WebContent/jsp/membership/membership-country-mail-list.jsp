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
 <%@ page import = "java.math.*"%>

<%@ page import="com.hlccountry.mail.util.*"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
</head>
<script language="javascript">
function myvalidate(){
if(document.myform.year.selectedIndex==0){
alert("Please select the membership year");
document.myform.year.focus();
return false;
}
return true;
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
				
<table  border="0" cellpadding="0" cellspacing="0"  align="center" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Country Mailing List</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
		The country mailing types are listed as follows. <br />
		<br />
		To edit a country mailing type, click on the <strong>'Edit'</strong> button beside it. To delete a record click on the <strong>'Delete'</strong> button.  <br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
	
		 <table width="523" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
<!--		  <tr>
			<td colspan="4">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th height="20" width="125" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
					<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendOne">&nbsp;</span> &nbsp;Delete
					</td>
				    <td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendTwo">&nbsp;</span> &nbsp;Edit.
					</td>
				  </tr>
				</table>
			</td>
		  </tr>-->
		  <%
			 String deleteStatus = (String)request.getAttribute("errStat");
				if(deleteStatus!=null && deleteStatus.equals("eConfirmDelete")){
				%>
				<tr>
				<td class="styleError" colspan="4">Cannot delete this record. its already in use</td>
				</tr>
				<%
			}
			%> 	  
		<!--  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>-->
		    <form name="myform" method="post" action="countrymail.do" onsubmit="return myvalidate();">
			<input type="hidden" name="countryProcess" value="listByYear">
			<tr>
			<td class="tableLeft">Membership Year:</td>					
			<td class="tableRight">
			<select name="year" id="year" class="selectboxOne" >
			<option selected="selected" value="Select One" >Select One</option>		
			<% String yearStr = (String) request.getAttribute("year");
			Calendar cal = Calendar.getInstance();
			for (int i= 2007;i<=(cal.get(Calendar.YEAR)+1);i++){
			if(yearStr != null && yearStr.equalsIgnoreCase(new Integer(i).toString())){
			%>
			<option selected="selected" value="<%=i%>"><%=i%></option>
			<%}else{%>
			<option value="<%=i%>"><%=i%></option>
			<%}
			}%>
			
			</select>&nbsp;<span class="asterisk">&nbsp;*</span>
			
			</td>
			<th class="tableSpan">
			<input type="submit" name="search" value="Search" class="gradBtn" /></th>
			</tr>
			
			</form>						 	   
		  <tr>
			<td width="125" height="27" class="tblRowHeadTwo">Country mail Type name </td>
			<td width="125" height="27" class="tblRowHeadTwo">Membership Type name </td>		
			<td width="119" class="tblRowHeadTwo">Amount($)</td>
			<td width="119" class="tblRowHeadTwo">Year</td>
			<td width="127" class="tblRowHeadTwo">Edit </td>
			<td width="100" class="tblRowHeadTwo">Delete</td>
		   </tr>
		   <%
		   ArrayList lst = (ArrayList) request.getAttribute("DisplayCountryDetails");
//		   out.println("Size in JSP is "+lst.size());
		   if(lst!=null && lst.size()!=0)
		   {
    	      Iterator itr = lst.iterator();

                      while(itr.hasNext()){
					  HLCCountryMailPriceMaster objCntMailList = (HLCCountryMailPriceMaster) itr.next();
					  String countryMailTypeName =  objCntMailList.getCountryMailTypeName();
					  String mailtypeId = objCntMailList.getCountryMailTypeId();
					  String membertypeId = objCntMailList.getMembershipTypeId();
					  String mailPrice = objCntMailList.getCountryMailPrice();
					  String year=objCntMailList.getYear();
		  
					float price = Float.parseFloat(mailPrice);	
					BigDecimal bdAmount = new BigDecimal(price);
					bdAmount = bdAmount.setScale(2,BigDecimal.ROUND_HALF_UP);	

				  	String memberName =objCntMailList.getMemberShipName();	
			%>
		   <form name="frmCntryMailList" method="post" action="countrymail.do">
		   <input type="hidden" name="countryProcess" value="manupPrice">
		    <input type="hidden" name="countrymailId" value="<%=mailtypeId%>">
		  <tr>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=countryMailTypeName%></th>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=memberName%></th>	
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=bdAmount%></th>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=year%></th>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="process" value="Edit" class="oneBtn" /></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="process" value="Delete" class="twoBtn" /></td>
		   </tr>
		  </form>
		    <%}	
					 }
					else {
					%>
					<tr>
					  <th height="25" colspan="4">There are no records available. </th>
					</tr>
					<% } %>
		<!--  <tr>
		  	<td height="25" colspan="4"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>-->
		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
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
