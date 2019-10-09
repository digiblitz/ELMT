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
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>

<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
<script language="javascript">
function conf(){
	sure = confirm("Sure to delete the contact ?");
	return sure;
}
</script>
</head>

<%@ page import="java.util.*" %>
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
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Distribution Lists  - View Distribution List Details </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><span class="msgHead">Distribution List   </span>  - <span class="styleBoldOne">View List Details </span> <br />
						<br />
						You are viewing the details of <strong>Distribution List</strong> created by you. To delete a member from your list click on the <strong>'Delete'</strong> button. To delete the Distribution List as a whole, click on the <strong>'Delete List'</strong> button.   <br />
						
					</td>
				  </tr>
						  <tr>
							<td>
							
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead">
									<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
									  <tr>
										<form name="frmDetDistList" method="post" action="./AddrDistList.do" /> 
										<td width="14%"><span class="alignRight">
										  <input type="submit" name="process" value="Delete List" class="gradTwoBtn" />
										</span></td>
										<td width="12%" class="alignRight"><span class="alignCenter">
										  <input type="button" name="delete2" value="Cancel" class="gradTwoBtn" onclick="location.href='./AddrDistList.do?process=listDistList'"/>
										<% 
												String dlistName1=(String)request.getAttribute("dlistName");%>
														
										 <input type="hidden" name="dlistName1" value="<%=dlistName1%>" />
										</span></td>
										</form>
										<td width="13%" class="alignRight">&nbsp;</td>
										<td width="14%" class="alignCenter">&nbsp;</td>
										<td width="24%" class="textCommon">&nbsp;</td>
										<td width="23%">&nbsp;</td>
									  </tr>
									</table>
									</td>
								  </tr>
								   <tr>
									<td height="25" >
									
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
										  <tr>
											<th width="28%" class="alignRight">Distribution List Name:&nbsp; </th>
											<td width="72%" class="alignLeft"><span class="styleBoldOne"> <%=dlistName1%> </span></td>
										  </tr>
										</table>
									
									</td>
								 </tr>
								  <tr>
									<td height="25" >
									
										<table width="100%" border="0" cellspacing="1" cellpadding="0">
										  <tr>
											<td width="34%" height="25" class="tblMainHead">Member Name  </td>
											<td width="40%" class="tblMainHead">EMail ID </td>
											<td width="19%" class="tblMainHead">Delete</td>
										  </tr>
												<% 
													Vector viewDistList=new Vector();
													viewDistList=(Vector)request.getAttribute("viewDistList");
                                                                                                        Enumeration en=viewDistList.elements();
                                                                                                        String dlistName=(String)request.getAttribute("dlistName");
                                                                                                        
                                                                                                        if(viewDistList.size()!=0)
                                                                                                        {
                                                                                                        while(en.hasMoreElements())
                                                                                                        {
                                                                                                              String vDistList[]=(String[])en.nextElement(); 
                                                                                                %>    
                                              <form name="frmDetDistList" method="post" action="./AddrDistList.do" onsubmit="return conf();" />                                                     
											  <tr>
												<td class="listCellBg"><%=vDistList[1]+" "+vDistList[3]%> </td>
												<td class="listCellBg"><%=vDistList[4]%></td>
												<td class="listCellBg"><input type="submit" name="delete" value="Delete" class="twoBtn" /></td>
												<input type="hidden" name="process" value="delDistList" />
												<input type="hidden" name="contid" value="<%=vDistList[5]%>" />
                                                <input type="hidden" name="dlistName" value="<%=dlistName%>" />
											  </tr>
                                                                                          </form>
                                                                                          <%}}else{
																						  %>
                                                                                          <td colspan="3" class="alignCenter">No Records Found !!</td>
                                                                                          <%} %>
										</table>
									
									</td>
								  </tr>
								   <tr>
									<td height="20">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
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
