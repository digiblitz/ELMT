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
<%@ page import="com.hlccommon.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
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
			<!--< %@ include file = "../../include/menu-messages-member.jsp" % >-->
			<%@ include file = "../../include/menu-roles-leftmenu.jsp" %>
			<!-- LEFT MENU ENDS HERE -->

		    </td>
			<td width="500" class="subDeptTablePad">
				<!-- CONTENTS START HERE -->
				<table width="70%" border="0" cellpadding="0" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">View Message </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp">
						<br />
						<span class="msgHead">Message</span>
						- <span class="styleBoldOne">View Message </span><br />
						You are viewing the information of the message you had selected. </td>
				  </tr>
				  
				  <%
						HLCMessageVO msgVO = (HLCMessageVO) request.getAttribute("viewMessageDetail");
						
							String firstName =  "";
							String lastName = "";
							Date postDate = null;
							String subject =  "";
							String messageId = "";
							String fromMailId = "";
							String message = "";
							String filePath="";
							if(msgVO!=null){
								firstName =  msgVO.getFirstName();
								lastName = msgVO.getLastName();
								postDate = msgVO.getPostDate();
								subject =  msgVO.getSubject();
								messageId = msgVO.getMessageId();
								fromMailId = msgVO.getFromEmailId();
								message =  msgVO.getMessage();
								filePath = msgVO.getFilePath();
							}
							
				  %>
						  <tr>
							<td>
							<form id="form1" name="form1" method="post" action="messages.do">
							<input type="hidden" name="msgProcess" value="deleteMsg" />
							<input type="hidden" name="messageId" value="<%=messageId%>" />
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead">
									
										<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
										  <tr>
											<td width="11%" class="alignRight">
											<input type="button" name="addContact" value="Inbox" class="gradTwoBtn" onclick="location.href='messages.do?msgProcess=inbox';"/></td>
											<td width="17%" class="alignCenter">
											<input type="submit" name="delete" value="Delete" class="gradTwoBtn" /></td>
											<%if(filePath!=null && filePath.trim().length()!=0){%>
											<td width="42%" class="textCommon">
											<input name="button" type="button" class="gradTwoBtn"  value="Download" onClick="location.href='messages.do?msgProcess=uplView&fPath=<%=filePath%>'"/>
											</td>
											<%}else{%>
											<td width="42%" class="textCommon">&nbsp;</td>
											<%}%>
											<td width="30%">&nbsp;</td>
										  </tr>
										</table>					</td>
								  </tr>
								  <tr>
									<td height="25" >
										
										<table width="100%" border="0" cellpadding="0" cellspacing="1" id="inboxTbl">
					
										  <tr>
											<th width="19%" height="25" class="listCellBg">From:</th>
											<td width="81%" class="listCellBg"><%=fromMailId%></td>
										  </tr>
										  <tr>
											<th height="25" class="listCellBg">Subject:</th>
											<td height="25" class="listCellBg"><%=subject%></td>
										  </tr>
				
										  <tr>
											<td colspan="2" height="25" class="tblMainHead"> &nbsp;Message:</td>
										  </tr>
										  <tr>
											<td height="142" colspan="2" valign="top" style="padding:10px;" class="tblDescrp">
											<%=message%>
											</td>
										  </tr>
										  <tr>
											<td colspan="2" class="tblRowHead"><table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
											  <tr>
												<td width="11%" class="alignRight">
												<input type="button" name="addContact" value="Inbox" class="gradTwoBtn" onclick="location.href='messages.do?msgProcess=inbox';" /></td>
												<td width="17%" class="alignCenter">
												<input type="submit" name="delete" value="Delete" class="gradTwoBtn" /></td>
											<%if(filePath!=null && filePath.trim().length()!=0){%>
											<td width="42%" class="textCommon">
											<input name="button" type="button" class="gradTwoBtn"  value="Download" onClick="javascript:location.href('messages.do?msgProcess=uplView&fPath=<%=filePath%>')"/>
											</td>
											<%}else{%>
											<td width="42%" class="textCommon">&nbsp;</td>
											<%}%>
												<td width="30%">&nbsp;</td>
											  </tr>
											</table></td>
										  </tr>
									</table>
									</td>
								  </tr>
								  
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
								  </tr>
						</table>
						</form>
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
