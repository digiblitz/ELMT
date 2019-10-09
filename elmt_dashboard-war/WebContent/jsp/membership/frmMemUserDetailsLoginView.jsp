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
	ArrayList listContact = (ArrayList) request.getAttribute("userDetails");
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
        String address_status = "";
		String spl_notes = "";
		
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
				if(middle_name==null)
				middle_name = "";
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
				if(address2==null)
				address2 = "NA";
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
				if(fax_no==null)
				fax_no = "NA";
                address_status = (String)it.next();
				if(address_status==null)
				address_status ="false";
				spl_notes = (String)it.next();
				if(spl_notes==null)
				spl_notes = "";
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
					<form name="viewUsrServiceList" action="SearchList.do" method="post">
					<table width="100%" border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						<tr>
						<td class="tblMainHead">Membership: User Details </td>
						</tr>
						 <tr id="part1" class="holderDivOne" >
						 	<td>
							<!--++++++++++++++++++++ Part 1 of the form starts here ++++++++++++++++++++++++++++++ -->	
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
							<tr>
							<td colspan="2" class="tblRowHead">User  Information</td>
						  <tr>
							<td width="45%" class="tableLeft"> First Name:</td>
							<td width="55%" class="tableRight"><%=first_name%>&nbsp;</td>
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
							<!--++++++++++++++++++++ Part 1 of the form ends here ++++++++++++++++++++++++++++++ -->							</td>
						 </tr>
						 
						 <tr id="part4" class="holderDivTwo">
						<td>
						<%
							String userId = (String)request.getAttribute("logUserId");
						%>
<table width="100%" cellpadding="0" cellspacing="1" border="0">
<tr>
									<th class="tableRight" colspan="2"><span class="formX">
												
									<%if(address_status.equalsIgnoreCase("true")){%>
										<input type="checkbox" name="mailAddressStatus"  id="mailAddressStatus" value="true" checked/>&nbsp;Verify Mailing address  <br />	
										<%}else {%>
										<input type="checkbox" name="mailAddressStatus"  id="mailAddressStatus" value="true"/>&nbsp;Verify Mailing address  <br />	
										<%}%></span>
												</th>
											  </tr>
											  <tr>
												<td colspan="1" class="tblRowHead">Special Notes: </td>
										 		</tr>
											<tr>
										<td class="tableLeft" colspan="2">
										<span class="label"><textarea name="splNote" rows="5"/><%=spl_notes%></textarea></span>
											</td>
											</tr>	
                                                                                        
                                        
                                                                                        
										</table>	
						<!--++++++++++++++++++++ Part 4 of the form starts here ++++++++++++++++++++++++++++++ -->	
								<tr>
								
									<td class="alignCenter">
									
										<input type="hidden" name="searchProcess" value="loginProcess" />
										<input type="hidden" name="userId" value="<%=userId%>"/>
										<input name="Back" type="submit" value="Update" class="gradBtn" />
									</form>
									<form name="viewHrsServiceList" action="SearchList.do" method="post">
									<input type="hidden" name="searchProcess" value="loginProcess" />
										<input type="hidden" name="userId" value="<%=userId%>"/>
										<input name="Back" type="submit" value="Login" class="gradBtn" />&nbsp;
										<input name="Back" type="button" value="Back" class="gradBtn"  onclick="javascript:history.back(-1);"/>
									</form>
									</td>
							   </tr>
							</table>
							<!--++++++++++++++++++++ Part 4 of the form ends here ++++++++++++++++++++++++++++++ -->		
						
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