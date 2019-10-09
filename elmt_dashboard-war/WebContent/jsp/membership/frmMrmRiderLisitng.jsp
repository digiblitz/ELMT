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
<%@ page import = "java.text.*" %>
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
<script language="javascript">
function postData(stat)
{
if(stat!=null)
{
	document.frmHorseMemberList.action="RegHorseListing.do?statSelect="+stat;
	document.frmHorseMemberList.submit();
}
else
{
	document.frmHorseMemberList.action="RegHorseListing.do?process=null";
	document.frmHorseMemberList.submit();
}
}
</script>
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
			<td width="230" class="menuTablePad">
			<!-- LEFT MENU STARTS HERE -->
				<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				
				
					<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
					  <tr>
						<td colspan="5" class="tblMainHead">
						Membership: <span class="styleBoldTwo">User Horse  Registration Listing </span></td>
					  </tr>
					  <tr>
						<td colspan="5" class="tblDescrp">
							The Horses registered with listed as follows. <br />
							<br />
							To View Individual details click on the <strong>'</strong> <strong>Horse name<strong>'</strong></strong>,<strong>'</strong> <strong>Owner Name<strong>'</strong></strong>,<strong> <strong>'</strong>RiderID</strong><strong>'</strong>.</td>
					  </tr>
					 
					 <tr>
						<td>
						
							 <table width="523" border="0" align="center" cellpadding="3" cellspacing="1" class="formLayout">
							  <tr>
								<td height="25" colspan="7"  bgcolor="#ffffff" class="alignLeft">&nbsp;</td>
							   </tr>
						  <tr>
								<td width="121" height="27" class="tblRowHeadTwo">Horse Name  </td>
								<td width="88" class="tblRowHeadTwo">Membership Name </td>
								<td width="73" class="tblRowHeadTwo">Registered By </td>								
								<td width="104" class="tblRowHeadTwo">Date Of Registration </td>
								<td width="104" class="tblRowHeadTwo">Status </td>
							   </tr>
						      
						<%
							ArrayList userInfo = (ArrayList) request.getAttribute("riderHorseList");
							if(userInfo!=null && userInfo.size()!=0){
							Iterator itr = 	userInfo.iterator();
							while(itr.hasNext()) {				   
								HLCHorseRegListVO AppHrListVO = (HLCHorseRegListVO) itr.next();								
								String horseMemberId = AppHrListVO.getHorseMemberId();
								String horseName = AppHrListVO.getHorseName();
								String riderID = AppHrListVO.getRiderID();
								String firstName = AppHrListVO.getFirstName();
								String lastName = AppHrListVO.getLastName();
								String reqStatus = AppHrListVO.getReqStatus();
								String ownerId = AppHrListVO.getOwnerId();
								Date dte = AppHrListVO.getAddDate();
								String regBy = AppHrListVO.getRegisteredBy();
								String memberType = AppHrListVO.getMembershipTypeName();
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
								String date = "NG";
								if(dte!=null){
									date = sdf.format(dte);
								}
								 
						%>
						<form name="Approvedfrm" method="post" >
						  <tr>
								<td width="121" bgcolor="#E3E1D2" class="alignCenter" ><%=horseName%>  </td>
								<td width="88" bgcolor="#E3E1D2" class="alignCenter" ><%=memberType%> </td>
								<td width="73" bgcolor="#E3E1D2" class="alignCenter"><%=regBy%> </td>								
								<td width="104" bgcolor="#E3E1D2" class="alignCenter"><%=date%> </td>
								<td width="104" bgcolor="#E3E1D2" class="alignCenter"><%=reqStatus%></td>
							   </tr>
						  </form>
								<%	}
								}
								else{
								%>
								<tr class="tblMainHead">
								<td width="121" height="27" colspan="5" align="center" >No records found.</td>
							   </tr>
								<%
								} 
								%>
							  <tr>
								<td height="19" colspan="7">&nbsp;</td>
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
