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
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeInsureICPDetails.js" type="text/javascript" ></script>
 <link href="../../css/core-ie.css" type="text/css" rel="stylesheet" /> 
<script language="javascript">
function check()
{
	if((document.frmMeeInsureICPDetails.commentarea.value=="")||(document.frmMeeInsureICPDetails.commentarea.value.indexOf(' ')==0)){
		alert('Enter Comment on Approval/Rejection');
		document.frmMeeInsureICPDetails.commentarea.focus();
		return false;
	}
	if(document.frmMeeInsureICPDetails.commentarea.value.length>1024){
				alert("Comments is out of range");
				document.frmMeeInsureICPDetails.commentarea.focus();
				return false;
				}
}
</script>
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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldOne">Organizer</span> <span class="styleBoldTwo">Approve Insurance  Release Request- ICP Activity </span>
					</td>
				  </tr>
				  <tr>

					<td colspan="2" class="tblDescrp" style="padding:8px;">
						You are viewing the <strong>Insurance Release Registration details</strong> of the user you had selected. 
						Mark your approval status below after your review.
					</td>
				  </tr>
				  <tr>
						<td>
							<form name="frmMeeInsureICPDetails"   action="IcpOrgRegList.do" onsubmit="return check();">
						
							<input type="hidden" name="process"  value="Approve">	
							<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
							  <tr> 
								<td colspan="2" class="tblRowHead"> Activity Details: </td>
							  </tr>
							<% Vector orgDet=new Vector(); 
							   orgDet=(Vector)request.getAttribute("orgDet");
							   
															
								if(orgDet!=null && orgDet.size()!=0)
								{
								System.out.println("size in jsp :"+orgDet.size());
								   Enumeration en=orgDet.elements();    
																
								while(en.hasMoreElements())
								{
										String[] icpVal = (String[])en.nextElement();
										String dat1=icpVal[1];
										String[] dat=dat1.split(" ");
								%>
	
							  <tr> 
								<input name="relId" type="hidden" value="<%=icpVal[19]%>"/>
								<td class="tableLeft">Name of Activity:</td>
								<th class="tableRight"><%=icpVal[0]%></th>
							  </tr>
							  <tr> 
								<td class="tableLeft">Area:</td>
								<td class="tableRight"><%=icpVal[3]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">Date(s)to be held:</td>
								<th class="tableRight"> <%=dat[0]%></th>
							  </tr>
							  <tr> 
								<td class="tableLeft">Location:</td>
								<td class="tableRight"><%=icpVal[4]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">State:</td>
								<td class="tableRight"><%=icpVal[9]%></td>
							  </tr>
							  
							  
							  <tr> 
								<td colspan="2" class="tblRowHead"> Participant&#8217;s Details: </td>
							  </tr>
							  <tr> 
								<td height="27" class="tableLeft">Participant&#8217;s 
									Name:</td>
								<td class="tableRight"><%=icpVal[5]+icpVal[6]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">Address:</td>
								<td class="tableRight"><%=icpVal[7]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">Country:</td>
								<td class="tableRight"><%=icpVal[8]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">State:</td>
								<td class="tableRight"><%=icpVal[9]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">City:</td>
								<td class="tableRight"><%=icpVal[18]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">Zip Code:</td>
								<td class="tableRight"><%=icpVal[10]%></td>
							  </tr>
							  <tr> 
								<td class="tableLeft">Fax:</td>
								<td class="tableRight"><%=icpVal[12]%>&nbsp;</td>
							  </tr>
							  <tr> 
								<td class="tableLeft">E-mail Id:</td>
								<td class="tableRight"><%=icpVal[17]%>&nbsp;</td>
							  </tr>
							  <tr> 
								<td colspan="2" class="tblRowHead"> Other Information: </td>
							  </tr>
							   <%
							  String sta ="";
							  String memStat="";
							  String memNo = icpVal[13];
							  if(memNo.equals("1")){
							  sta = "Yes";
								if((memStat!=null) ||(memStat.trim().length()!=0)){
									memStat=icpVal[14];
								}							  
							  }
							  else{
							  sta = "No";
							  memStat="Not a Member";
							  }
							String comments = icpVal[20];
								if((comments==null ) ||(comments.trim().length()==0)){
									comments="";
								}							 
							  %>
							  <tr> 
								<td class="tableLeft">Are You a member?</td>
								<th class="tableRight"><%=sta%></th>
							  </tr>
							   
							  <tr> 
								<td class="tableLeft">If yes membership number:</td>
								<th class="tableRight"><%=memStat%></th>
							  </tr>
							   <tr> 
								<td colspan="2" class="tblRowHead"> Approval Status: </td>
							  </tr>
								 <%
								 String stat = icpVal[16];
								 if(stat.equalsIgnoreCase("Pending")){
								 %>
						  <tr>
								<td class="tableLeftTxtArea">Comments:</td>
														
								<td class="tableRight"><textarea name="commentarea" rows="5" class="textAreaOne"></textarea>&nbsp;<span class="asterisk">*</span></td>
							</tr>								 
						  <tr> 
							<td class="tableLeft">Status:</td>
							<td class="tableRight">
								<input name="usrStat" type="radio" value="Approved" checked="checked"/>
							  	Approve
							  	<input name="usrStat" type="radio" value="Rejected" />
								Reject
							</td>
						  </tr>
						  <tr> 
							<td colspan="2" class="alignCenter">
							<input name="process" type="submit" class="gradBtn" value="Submit" />	
							<input name="back" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" />	
							</td>
						  </tr>
							<%	}	
							else{	%>
							<tr>
								<td class="tableLeft style1">Status: </td>
								<td class="tableRight"><%=icpVal[16]%></td>
							</tr>
							<tr>
								<td class="tableLeft style1">Comments: </td>
								<td class="tableRight"><%=icpVal[20]%></td>
							</tr>							
							<tr>
								<td colspan="2" class="alignCenter"><input name="back" type="button" class="gradBtn" value="Cancel" onclick="javascript:history.back(-1);" /></td>
							</tr>
							<% }	%>							 
							</table>								
							</form>
						</td>
						<%}}else{%>
						  <td class="alignCenter" colspan="2">
					    </td>
						 <% }%>
				  <tr>
					<td height="20">&nbsp;  
					  <!-- DO NOT DELETE THIS ROW -->
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
