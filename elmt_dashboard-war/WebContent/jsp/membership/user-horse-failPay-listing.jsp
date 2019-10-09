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
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.hlccommon.util.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/validate.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
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
			<td width="250" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				
					<table width="100%"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
					        Membership: <span class="styleBoldTwo">User Horse  Registration Listing </span></td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp">
							The Horses registered are listed as follows. <br />
							<br />
							To View Individual details click on the <strong>'Horse Member </strong><strong>ID'</strong>.</td>
					  </tr>
						<tr>
						<td height="25" colspan="2" class="tableSpan"><span class="styleBoldTwo">Note:</span><span class="styleBoldOne"> DOR</span> <strong>- Date Of Registration </strong></td>
						</tr> 
					 <tr>
						<td valign="top">
						
							 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formLayout">
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						 </tr>
						  <tr>
								<td width="58"  height="27" class="tblRowHeadTwo">Member Id</td>
								
								<td width="51"  class="tblRowHeadTwo">Horse Name</td>
								<td width="87"  class="tblRowHeadTwo">Membership Type </td>
								<!--<td  class="tblRowHeadTwo">Registered By </td>	-->							
								<td width="73"  class="tblRowHeadTwo">DOR </td>
								<td width="48"  class="tblRowHeadTwo">Status </td>
								<td width="78"  class="tblRowHeadTwo">Proceed Payment</td>								
								<!--
								<td width="112" class="tblRowHeadTwo">DownGrade</td>
								<td width="91" class="tblRowHeadTwo">Renew</td>
								-->
							   </tr>
						      
						<%
							SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
							ArrayList userInfo = (ArrayList) request.getAttribute("pendingInfo");
							if(userInfo!=null && userInfo.size()!=0){
							Iterator itr = 	userInfo.iterator();
							while(itr.hasNext()) {				   
								HLCHorseRegListVO AppHrListVO = (HLCHorseRegListVO) itr.next();								

								String horseMemberId = AppHrListVO.getHorseMemberId();
								String horseName = AppHrListVO.getHorseName();
								String riderID = AppHrListVO.getRiderID();
								String firstName = AppHrListVO.getFirstName();
								String lastName = AppHrListVO.getLastName();
								String reqStatus = AppHrListVO.getStatusName();
					
								String ownerId = AppHrListVO.getOwnerId();
								Date dte = AppHrListVO.getAddDate();
								String regBy = AppHrListVO.getRegisteredBy();
								String memberType = AppHrListVO.getMembershipTypeName();
								String date  = ""; 
								
								if(dte!=null){
									date = sdf.format(dte);
								}
								%>
						<form name="Approvedfrm" method="post" action="RegHorseListing.do?">
						<input type="hidden" value="PayProceed" name="process" />
							 <tr>
								<td height="26" class="listCellBg"><a href="./RegHorseListing.do?process=chngdesc&memid=<%=horseMemberId%>"><%=horseMemberId%></a>  </td>
								<td class="listCellBg"><%=horseName%></td>
								<td class="listCellBg"><%=memberType%></td>
								<td class="listCellBg"><%=date%></td>
								<td class="listCellBg"><span class="styleBoldOne"><%=reqStatus%></span></td>
								<td class="listCellBg"><input type="submit" name="click2" value="Click To Pay" class="oneBtn"/></td>
								<input type="hidden" name="memid" value="<%=horseMemberId%>"/>
							</tr>
						</form>
							<tr>
							<td  colspan="8"  bgcolor="#ffffff" class="alignRight"> </td>
						 </tr>
						   <%
						   }}
						   %>
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
