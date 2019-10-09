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
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->
  <script language="javascript">
function postData(dispId){
	strURL = "./Donationdetails.do?process=lst&status="+dispId;
	window.location.href = strURL;
}
function editBtn(donId){
	strURL3 = "./Donationdetails.do?process=edit&donationId="+donId;
	window.location.href = strURL3;
}
function listDeactive(don_status){
	donstat = don_status.split("#");

	strURL1 = "./Donationdetails.do?process=deactivate&donationId="+donstat[0]+"&status="+donstat[1];
	window.location.href = strURL1;
}
function listActivate(don_status){
	donstat = don_status.split("#");
	strURL2 = "./Donationdetails.do?process=activate&donationId="+donstat[0]+"&status="+donstat[1];
	window.location.href = strURL2;
}
</script></head>

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
	Membership: <span class="styleBoldTwo">Donation List</span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    
		The Donation types are listed as follows. <br />
		<br />
		To edit a Donation type, click on the <strong>'Edit'</strong> button beside it.To ' <strong>Activate'</strong> or <strong>'Deactivate'</strong> Donation type, click on the' <strong>Activate'</strong> or <strong>'Deactivate'</strong><strong> </strong>button beside it.<br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
 <form name="frmDonationList" method="post" action="Donationdetails.do">
		 <table border="0" cellpadding="3" align="center" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="4">
<!--				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th height="20" width="125" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
					<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendOne">&nbsp;</span> &nbsp;Delete
					</td>
				    <td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendTwo">&nbsp;</span> &nbsp;Edit.
					</td>
				  </tr>
				</table>-->
			</td>
		  </tr>
		<tr>
		<td class="alignRight">Status:</td> <td>
		<select name="status" onchange="postData(this.value);" class="selectboxOne" >
		  <%ArrayList lst = (ArrayList) request.getAttribute("list");
				if(request.getAttribute("list")==null){	%>
						<option value="Select" selected="selected">Select One</option>
						<option value="Activate">Active</option>
						<option value="Deactivate">Deactive</option>
				<%}
				else{
					String status = (String) request.getParameter("status");
					if(status.equalsIgnoreCase("Activate")){%>
						<option value="Select">Select One</option>
						<option value="Activate" selected="selected">Active</option>
						<option value="Deactivate">Deactive</option>
				<%	}
					else if(status.equalsIgnoreCase("Deactivate")){%>
						<option value="Select">Select One</option>
						<option value="Activate">Active</option>
						<option value="Deactivate" selected="selected">Deactive</option>
				<% }
					else{	%>
						<option value="Select" selected="selected">Select One</option>
						<option value="Activate">Active</option>
						<option value="Deactivate">Deactive</option>
				<% 	lst.clear();	}	
				}	 %>
		</select>
				</td></tr>
		  <tr>
			<td width="125" height="27" class="tblRowHeadTwo">Donation Type name </td>
			<td width="125" height="27" class="tblRowHeadTwo">Donation Price</td>
			<td width="70" class="tblRowHeadTwo">Priority</td>
			<td width="70" class="tblRowHeadTwo">Edit</td>
			<td width="70" class="tblRowHeadTwo">Change Status</td>
			
            <!--			<td width="127" class="tblRowHeadTwo">Edit </td>
			<td width="100" class="tblRowHeadTwo">Delete</td>		-->
		   </tr>
		   <%
		   if(lst!=null){	
		  // ArrayList lst = (ArrayList) request.getAttribute("list");
		   if(lst.size()!=0)
		   {
    	      Iterator itr = lst.iterator();

                      while(itr.hasNext()){
						HLCDonationVO donObj = (HLCDonationVO) itr.next();
						String donationId = donObj.getDonationId();
						String donationName = donObj.getDonationName();
						String donationPrice = donObj.getDonationPrice();
						boolean status = donObj.isActiveStatus();
						String id_status = donationId+"#"+status;
						String priorityValue=String.valueOf(donObj.getPriorityValue());
		%>
		  <tr>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=donationName%></th>
			<% if((donationPrice==null)||(donationPrice.trim().length()==0)){	donationPrice="0";	}	%>
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=donationPrice%></th>	
			<th height="26" bgcolor="#E3E1D2" class="alignCenter"><%=priorityValue%></th>	
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="process" value="Edit" class="oneBtn"
			onclick="editBtn('<%=donationId%>');" /></td>
			<% 
			if(status==true){
			%>	<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="process" value="Deactivate" class="oneBtn" 
			onclick="listDeactive('<%=id_status%>');" /></td>
			<%}
			else{
			%><td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="process" value="Activate" class="twoBtn" 
			onclick="listActivate('<%=id_status%>');" /></td>
			<% } %>
		   </tr>

		    <%  }
				  }
					}
					else {
					%>
					<tr>
					  <th height="25" colspan="4" class="alignCenter">There are no records available. </th>
					</tr>
					<%}%>
		  <tr>
			<td height="19" colspan="4">&nbsp;</td>
           </tr>
	  </table>		  </form>
	  
	
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
