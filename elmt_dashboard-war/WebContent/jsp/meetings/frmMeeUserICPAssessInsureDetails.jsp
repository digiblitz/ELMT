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
<%@ page import="com.hlcmeeting.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<script src="javascripts/frmMeeICPAssessment.js" type="text/javascript" ></script>
<!-- <link href="css/core-ie.css" type="text/css" rel="stylesheet" /> -->

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
				<table border="0" cellpadding="0" align="center" cellspacing="0" class="tblInnerContainer">
				  <tr>
					<td colspan="2" class="tblMainHead">
					<strong>Meetings: </strong> <span class="styleBoldTwo">Insurance Release Details - ICP Activity </span>
					</td>
				  </tr>
				<!--  <tr>
					<td colspan="2" class="tblDescrp"><div id="commonBG" class="textCommon" style="height:100px;"> 
						<img src="images/usea_logo.jpg" class="imgFloatLeft" /><br />
					</div></td>
				  </tr>-->
				  <tr>
					<td>
					
						<form name="frmMeeInsureICPDetails" id="myform" action="">
					
							
						<table border="0" cellpadding="0" align="center" cellspacing="0" class="formLayout">
						  <tr> 
							<td colspan="2" class="tblRowHead"> Activity Details: </td>
						  </tr>
						  <tr> 
							<td colspan=2>&nbsp; </td>
						  </tr>
                                                  <%
                                                   Vector usrDesc=new Vector();
                                                   usrDesc=(Vector)request.getAttribute("usrDesc");     
                                                   if(usrDesc!=null && usrDesc.size()!=0)
							{
                                                       
                                                       Enumeration en=usrDesc.elements();
                                                       while(en.hasMoreElements())
                                                       {
                                                           String[] userDet=(String[])en.nextElement();
                                                           
                                                           String[] dat=userDet[1].split(" ");
                                                        %>
						  <tr> 
							<td class="tableLeft">Name of Activity:</td>
							<th class="tableRight"><%=userDet[0]%></th>
						  </tr>
						  <tr> 
							<td class="tableLeft">Area:</td>
							<td class="tableRight"><%=userDet[3]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Date(s)to be held:</td>
							<th class="tableRight"><%=dat[0]%></th>
						  </tr>
						  <tr> 
							<td class="tableLeft">Location:</td>
							<td class="tableRight"><%=userDet[4]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">State:</td>
							<td class="tableRight"><%=userDet[9]%></td>
						  </tr>
						  
						  
						  <tr> 
							<td colspan="2" class="tblRowHead"> Participant&#8217;s Details: </td>
						  </tr>
						  <tr> 
							<td height="27" class="tableLeft">Participant&#8217;s 
								Name:</td>
							<td class="tableRight"><%=userDet[5]+userDet[6]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Address:</td>
							<td class="tableRight"><%=userDet[7]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Country:</td>
							<td class="tableRight"><%=userDet[8]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">State:</td>
							<td class="tableRight"><%=userDet[9]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">City:</td>
							<td class="tableRight"><%=userDet[18]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Zip Code:</td>
							<td class="tableRight"><%=userDet[10]%></td>
						  </tr>
						  <tr> 
							<td class="tableLeft">Fax:</td>
							<td class="tableRight"><%=userDet[12]%>&nbsp;</td>
						  </tr>
						  <tr> 
							<td class="tableLeft">E-mail Id:</td>
							<td class="tableRight"><%=userDet[17]%></td>
						  </tr>
						  <tr> 
							<td colspan="2" class="tblRowHead"> Other Information: </td>
						  </tr>
						  <%
						  String sta ="";
						  String memNo = userDet[13];
						  String memStat = userDet[14];
						  if(memNo.equals("1")){
						  sta = "Yes";
						  }
						  else{
						  sta = "No";
						  memStat="Not a Member";
						  }
						  %>
						  <tr> 
							<td class="tableLeft">Are You a member?:</td>
							<th class="tableRight"><%=sta%></th>
						  </tr>
						  <%					  
						        //String memStat = userDet[14];
								String numMe = "";
								if((memStat==null ) ||(memStat.trim().length()==0)){
									numMe="Not a Member";
								}
								else{
									numMe=memStat;
								}
								 
						  %>
						  <tr> 
							<td class="tableLeft">If yes membership number:</td>
							<th class="tableRight"> <%=numMe%></th>
						  </tr>
						   <tr> 
							<td colspan="2" class="tblRowHead"> Approval Status: </td>
						  </tr>
						  
						  <tr> 
							<td class="tableLeft">Status:</td>
							<th class="tableRight"><span class="styleBoldOne"><%=userDet[16]%></span></th>
						  </tr>
						<%}}%>
						  <tr> 
							<td colspan="2" class="alignCenter"><input name="button2" type="button" class="gradBtn" value="Back" onclick="javascript:history.back(-1);" />							 </td>
						  </tr>
						</table>
							
						</form>
					</td>
				  </tr>
				  
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