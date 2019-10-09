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
<script src="javascripts/frmMsgDistListValidate.js" type="text/javascript" ></script>
<link href="../../css/core-ie.css" type="text/css" rel="stylesheet" />
<!--<link href="css/core-ie.css" type="text/css" rel="stylesheet" />-->
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
					<td colspan="2" class="tblMainHead"><strong></strong> Messages: <span class="styleBoldTwo">Distribution Lists  - View Distribution Listings </span></td>
				  </tr>
				  <tr>
					<td colspan="2" class="tblDescrp"><br />
						<span class="msgHead">Distribution List   </span>  - <span class="styleBoldOne">View Distribution Lists </span> <br />
						<br />
						You are viewing a list of <strong>Distribution Lists</strong> created by you. To view the details of individual lists click on their names. To edit a list click on the <strong>'Edit'</strong> button.  <br />
						
					</td>
				  </tr>
						  <tr>
							<td>
							<form name="dlist" id="dlist" action="./AddrDistList.do" method="post" onsubmit="return DelAll();"/>
							<input type="hidden" name="dlistct" id="dlistct" value=""/>
							<input type="hidden" name="process" value="Delete"/>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer" id="mainTbl">
								  <tr>
									<td class="tblRowHead">
									
									<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
										
									  <tr>
                                       
										<td width="14%"><span class="disclaimer">(select All)</span></td>
										<td width="12%" class="alignRight"><input type="button" name="Create" value="Create" class="gradTwoBtn" onClick="location.href='AddrDistList.do?process=Create'"/></td>
										<td width="13%" class="alignRight"><input type="submit" value="Delete" class="gradTwoBtn" /></td>
										<td width="14%" class="alignCenter"><input type="button" value="Reset" name="Cancel" class="gradTwoBtn" onclick="resetAll();"/></td>
										<td width="24%" class="textCommon"><strong>Contacts</strong></td>
										<!--<td width="23%"><a href="#" class="linkFour">&laquo; Prev</a><span class="divider"> | </span> <a href="#" class="linkFour">Next &raquo;</a> </td>-->

                                         <!--</form>-->                                        
									  </tr>
                                                                          
									</table>
									</td>
								  </tr>
								  
								  <tr>
									<td height="25" >
									<!--onsubmit="return checkedvalues();" -->
										<table width="100%" border="0" cellspacing="1" cellpadding="0">
										  <tr>
											<td width="6%" height="25" class="tblMainHead"><input  type="checkbox" name="chkAll"  id="chkAll" value="ChkAll" alt="Select or Deselect All" onClick="checkAll();" ></td>
											<td width="52%" class="tblMainHead">Distribution List name </td>
											<td width="22%" class="tblMainHead">Members</td>
											<td width="19%" class="tblMainHead">Edit</td>
											<td width="19%" class="tblMainHead">View</td>
										  </tr>
											<%
													Vector listDistList=new Vector();
													listDistList=(Vector)request.getAttribute("listDistList");
													Enumeration en=listDistList.elements();
													int ct=0;	
													Vector listDistList1=new Vector();
													listDistList1=(Vector)request.getAttribute("listDistList");
													int s=0;
													if(listDistList1.size()!=0)
													s=listDistList1.size();
																							   											
												   if(listDistList.size()!=0){
													   while(en.hasMoreElements()){
															String[] distlist=(String[])en.nextElement();
															//String cbxname="dlistnam"+Integer.toString(ct);
                                             %>
											
											  <tr>
                                                <td class="listCellBg"><input name="checkbox<%=ct%>" id="checkbox<%=ct%>" type="checkbox" value="<%=distlist[0]%>" /></td>
						<input type="hidden" name="dlstsize" id="dlstsize" value="<%=s%>" />
						
						</form>
						 					<form name="subform<%=ct%>" action="./AddrDistList.do" method="post">
											<input type="hidden" name="process" value="MsgDistListView"/>	
												<td class="listCellBg"><%=distlist[0]%></td>
												<td class="listCellBg"><%=distlist[1]%></td>
												<input type="hidden" name="dlistName" value="<%=distlist[0]%>"/>
												<td class="listCellBg"><input type="submit" name="status" value="Edit" class="twoBtn"  /></td>
												<td class="listCellBg"><input type="submit" name="status" value="View" class="twoBtn" /></td>
												<!--<input type="hidden" name="dlistName" value="< %=distlist[0]%>" />-->
												</form>
											  </tr>
											
										<%
													ct++;
													}
													}
													else{
										%>
											<td colspan="5" class="listCellBg"><center>
											  No Distribution Lists Found !!
											</center></td>
										<%			}		%>
                                           
										</table>
										
									</td>
								  </tr>
								  <tr>
									<td class="tblRowHead">
										<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" id="tableHead">
											 
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
