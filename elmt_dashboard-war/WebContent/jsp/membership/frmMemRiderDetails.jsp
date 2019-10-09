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
<%@ page import="com.hlccommon.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<!-- <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> -->

</head>
	</tr>
<%
	ArrayList listContact = (ArrayList) request.getAttribute("ownerDetails");
	
		String prefix1 = "";
		String first_name = "";
		String middle_name = "";
		String last_name = "";
		String sufix = "";
		String email_id = "";
		String suite = "";
		String address1 = "";
		String address2 = "";
		String city = "";
		String state = "";
		String country = "";
		String zip = "";
		String phone_no = "";
		String mobile_no = "";
		String fax_no = "";
		
		if(listContact !=null && listContact.size()!=0){
			Iterator it = listContact.iterator();
			while(it.hasNext()){
				prefix1 = (String)it.next();
				if(prefix1==null)
				prefix1 = "";
				first_name  = (String)it.next();
				if(first_name==null)
				first_name = "";
				middle_name  = (String)it.next();
				if(middle_name==null || middle_name.equals(""))
				middle_name = "N/A";
				last_name = (String)it.next();
				if(last_name==null)
				last_name = "";
				sufix =  (String)it.next();
				if(sufix==null)
				sufix = "";
				email_id  = (String)it.next();
				if(email_id==null)
				email_id = "";
				suite =  (String)it.next();
				if(suite==null)
				suite = "";
				address1 =  (String)it.next();
				if(address1==null)
				address1 = "";
				address2 = (String)it.next();
				if(address2==null || address2.equals(""))
				address2 = "N/A";
				city = (String)it.next();
				if(city==null)
				city = "";
				state =  (String)it.next();
				if(state==null)
				state = "";
				country = (String)it.next();
				if(country==null)
				country = "";
				zip = (String)it.next();
				if(zip==null)
				zip = "";
				phone_no = (String)it.next();
				if(phone_no==null)
				phone_no = "";
				mobile_no = (String)it.next();
				if(mobile_no==null)
				mobile_no = "";
				fax_no = (String)it.next();
				if(fax_no==null || fax_no.equals(""))
				fax_no = "N/A";
			}
		}
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
			
			<!-- LEFT MENU ENDS HERE -->
<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td>
					
					<table width="100%" border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
						<td class="tblMainHead">Membership: Rider Details </td>
						</tr>
						 <tr id="part1" class="holderDivOne" >
						 	<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
							<tr>
							<td colspan="2" class="tblRowHead">Rider  Information</td>
						  <tr>
							<td class="tableLeft"> First Name:</td>
							<td class="tableRight"><%=first_name%>&nbsp;</td>
						  </tr>
						    <tr>
							<td class="tableLeft"> Middle Name:</td>
							<td class="tableRight"><%=middle_name%>&nbsp;</td>
						  </tr>
						  
						    <tr>
							<td class="tableLeft">  Last Name:</td>
							<td class="tableRight"><%=last_name%>&nbsp;</td>
						  </tr>
						  
						  <tr>
							<td class="tableLeft"> Address1:</td>
							<td class="tableRight"><%=address1%>&nbsp;</td>
						  </tr>
							 <tr>
							<td class="tableLeft"> Address2:</td>
							<td class="tableRight"><%=address2%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">City:</td>
							<td class="tableRight"><%=city%>&nbsp;</td>
						  </tr>
						   <tr> 
							<td height="27" class="tableLeft">State : </td>
							<td class="tableRight"><%=state%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Zip: </td>
							<td class="tableRight"><%=zip%>&nbsp;</td>
						  </tr>						  
						  <tr> 
							<td class="tableLeft">Country:</td>
							<td class="tableRight"><%=country%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft"> Phone:</td>
							<td class="tableRight"><%=phone_no%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Fax: </td>
							<td class="tableRight"><%=fax_no%>&nbsp;</td>
						  </tr>
						  <tr>
							<td class="tableLeft"> Email ID: </td>
							<td class="tableRight"><%=email_id%>&nbsp;</td>
						  </tr>
							 </table>
			 </tr>
						 
						 <tr id="part4" class="holderDivTwo">
						<td>
								<tr>
									<td class="alignCenter">
									<input name="Back" type="button" value="Back" class="gradBtn"  onclick="javascript:history.back(-1);"/></td>
							   </tr>
							</table>
						</td>
					 </tr>
				
					  </table>
					 
					  
					</td>
				  </tr>
				  <tr>
						<td>&nbsp; 
					   <!-- DO NOT DELETE THIS ROW -->
					   </td>
				  </tr>
				</table>
			<!-- CONTENTS END HERE -->		
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
