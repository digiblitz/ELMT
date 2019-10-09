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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=(String)session.getAttribute("title")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="javascripts/basic.js" type="text/javascript" ></script>
<link href="css/core-ie.css" type="text/css" rel="stylesheet" />
</head>
<body>
<table width="545"  border="0"  align="center" cellpadding="0" cellspacing="0" class="tblInnerContainer">
  <tr>
    <td colspan="5" class="tblMainHead">
	Membership: <span class="styleBoldTwo">Horse Member Listing </span></td>
  </tr>
  <tr>
    <td colspan="5" class="tblDescrp">
	    <img src="images/usea_logo.jpg" class="imgFloatLeft" />
		The Horses registered are listed as follows: <br />
		<br />
		To Upgrade a horse's account, click on the <strong>'Upgrade'</strong> button beside it. To Downgrade, click on the <strong>'Downgrade'</strong> button. To Renew membership of Horses,   click on the <strong>'Renew'</strong> button.  <br />
	</td>
  </tr>
 
 <tr>
 	<td>
	
		 <table width="523" border="0" align="center" cellpadding="3" cellspacing="1" class="formLayout">
		  <tr>
			<td colspan="6">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
					<th height="20" width="125" valign="top" class="alignRight" style="border-bottom:1px solid #999;">&nbsp;Color Legends:&nbsp; </th>
					<td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendOne">&nbsp;</span> &nbsp;Renew					</td>
				    <td valign="middle" class="alignLeft" style="border-bottom:1px solid #999;">
						<span class="legendTwo">&nbsp;</span> &nbsp;Upgrade/Downgrade					</td>
				  </tr>
				</table>			
				</td>
		  </tr>
<!--		  <tr>
		  	<td height="25" colspan="6"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
		      <a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			 </td>
		   </tr>
-->		   
		  <tr>
			<td width="160" height="27" class="tblRowHeadTwo">Hourse Name  </td>
			<td width="55" class="tblRowHeadTwo">Member id </td>
			<td width="117" class="tblRowHeadTwo">Date Of Registration </td>
			<td width="95" class="tblRowHeadTwo">Upgrade </td>
			<td width="112" class="tblRowHeadTwo">DownGrade</td>
		    <td width="91" class="tblRowHeadTwo">Renew</td>
	       </tr>

		<%
			Vector v = (Vector)request.getAttribute("vObj");
			if(v!=null && v.size()!=0){
		%>

		<%
			Enumeration it = v.elements();
			while (it.hasMoreElements()) {
			String[] s = (String[]) it.nextElement();
			String registered_name= s[0];
			String horse_member_id= s[1];
			String add_date= s[2];
		%>		   
		  
		<tr>
		<form name="frmHorseRegList" method="post" action="./HorseRegList.do">
		<input name="HorsememId" type="hidden" value="<%=horse_member_id%>">
			<td height="26" bgcolor="#E3E1D2" class="alignCenter"><strong><%=registered_name%></strong></td>
			<td height="26" bgcolor="#E3E1D2" class="alignCenter"><strong><%=horse_member_id%></strong></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><strong><%=add_date%></strong></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="button" name="Submit2" value="Upgrade" class="oneBtn" /></td>
			<td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit5" value="Downgrade" class="oneBtn" /></td>
		    <td bgcolor="#E3E1D2" class="alignCenter"><input type="submit" name="Submit52" value="Renew" class="twoBtn" /></td>
	  	</form>
	    </tr>
		
		<%
		}
		%>
		<%}	
		else {
		%>
		<tr>
		  <td align="center" colspan="6"><strong>No Records Available </strong></td>
		</tr>
		<%}%>


<!--		  <tr>
		  	<td height="25" colspan="6"  bgcolor="#ffffff" class="alignRight">
				<strong>View Page(s): </strong> <span class="styleBoldOne">1 - 10</span>&nbsp;
			<a href="" class="linkThree">Prev</a> | <a href="" class="linkThree">Next</a>			</td>
		   </tr>
-->		  

		<tr>
			<td height="19" colspan="6">&nbsp; <!-- DO NOT DELETE THIS ROW --></td>
           </tr>
	  </table>
	</td>
</tr>  
</table>
</body>
</html>
